/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.requirements.ui.wizard;

import java.util.Map;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.dialogs.DialogSettings;
import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.rmf.reqif10.ReqIF;
import org.eclipse.rmf.reqif10.Specification;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchWizard;
import org.eclipse.ui.statushandlers.StatusManager;

import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import de.dlr.sc.virsat.model.extension.requirements.model.ImportConfiguration;
import de.dlr.sc.virsat.model.extension.requirements.model.RequirementsConfiguration;
import de.dlr.sc.virsat.model.extension.requirements.model.RequirementsConfigurationCollection;
import de.dlr.sc.virsat.model.extension.requirements.reqif.ReqIfImporter;
import de.dlr.sc.virsat.model.extension.requirements.ui.Activator;
import de.dlr.sc.virsat.project.editingDomain.VirSatEditingDomainRegistry;
import de.dlr.sc.virsat.project.editingDomain.VirSatTransactionalEditingDomain;

/**
 * A wizard for the import of requirements from a CSV file
 *
 */
public class ReqIfImportWizard extends Wizard implements IWorkbenchWizard {

	public static final String ID = "de.dlr.sc.virsat.model.extension.requirements.ui.wizard.reqIFImport";

	private ReqIfFileConfigurationSelectionPage importPage;
	private ReqIfMappingPage mappingPage;
	private IContainer model;
	private ReqIfImporter importer = new ReqIfImporter();


	/**
	 * Default constructor
	 */
	public ReqIfImportWizard() {
		super();

		// Setup persistency
		IDialogSettings pluginSettings = Activator.getDefault().getDialogSettings();
		IDialogSettings wizardSettings = pluginSettings.getSection(ID);
		if (wizardSettings == null) {
			wizardSettings = new DialogSettings(ID);
			pluginSettings.addSection(wizardSettings);
		}
		setDialogSettings(wizardSettings);
	}

	@Override
	public void init(IWorkbench workbench, IStructuredSelection selection) {
		this.model = ResourcesPlugin.getWorkspace().getRoot();
		setNeedsProgressMonitor(true);
	}

	@Override
	public boolean canFinish() {
		return super.canFinish() || importPage.canFinish();
	}
	
	@Override
	public boolean performFinish() {

		final EObject reqConfiguration = (EObject) importPage.getSelection();
		final Map<Specification, StructuralElementInstance> specMapping = mappingPage.getSpecificationMapping();
		final RequirementsConfiguration typeContainer = mappingPage.getRequirementTypeContainer();

		// Do the import
		Job importJob = new Job("Performing Requirements ReqIF Import") {
			@Override
			protected IStatus run(IProgressMonitor monitor) {
				
				VirSatTransactionalEditingDomain editingDomain = VirSatEditingDomainRegistry.INSTANCE
						.getEd(reqConfiguration);
				ReqIF reqIfContent = mappingPage.getReqIfContent();
				
				if (reqConfiguration instanceof StructuralElementInstance) {
					doImport(editingDomain, reqIfContent, specMapping, new RequirementsConfigurationCollection((StructuralElementInstance) reqConfiguration), typeContainer, monitor);
				} else  {
					doReimport(editingDomain, reqIfContent, new ImportConfiguration((CategoryAssignment) reqConfiguration), monitor);
				}
				
				// Update workspace
				try {
					ResourcesPlugin.getWorkspace().getRoot().refreshLocal(IResource.DEPTH_INFINITE, null);
				} catch (CoreException e) {
					Status status = new Status(Status.ERROR, Activator.getPluginId(),
							"ReqIFImportWizard: Failed to refresh the workspace!", e);
					StatusManager.getManager().handle(status, StatusManager.LOG | StatusManager.SHOW);
					return Status.CANCEL_STATUS;
				}
				return Status.OK_STATUS;
			}
		};
		importJob.schedule();

		return true;
	}

	/**
	 * Do an initial import, this method also creates a mapping of specifications and configures the import configuration
	 * 
	 * @param editingDomain the editing domain
	 * @param reqIfContent the content to import
	 * @param specMapping the mapping as map
	 * @param configurationContainer the container in which the import configuration shall be created
	 * @param typeContainer the container element for new requirement types imported from ReqIF
	 * @param monitor the progress monitor
	 */
	public void doImport(EditingDomain editingDomain, ReqIF reqIfContent, Map<Specification, StructuralElementInstance> specMapping, RequirementsConfigurationCollection configurationContainer, RequirementsConfiguration typeContainer, IProgressMonitor monitor) {

		importer.init(reqIfContent, configurationContainer);
		
		// Preparation
		CompoundCommand preparationCommand = new CompoundCommand();
		preparationCommand.append(importer.persistSpecificationMapping(editingDomain, specMapping, reqIfContent, configurationContainer));
		preparationCommand.append(importer.persistRequirementTypeContainer(editingDomain, typeContainer));
		editingDomain.getCommandStack().execute(preparationCommand);
		
		// Created required types
		editingDomain.getCommandStack().execute(importer.importRequirementTypes(editingDomain, reqIfContent));
		
		// Do the actual imports
		editingDomain.getCommandStack().execute(importer.importRequirements(editingDomain, reqIfContent));
		
		//Import the requirement links
		editingDomain.getCommandStack().execute(importer.importRequirementLinks(editingDomain, reqIfContent));
	}
	
	/**
	 * Do a re-import in a import configuration already exists. Existing requirement values are overwritten.
	 * 
	 * @param editingDomain the editing domain
	 * @param reqIfContent the content to import
	 * @param importConfiguration the existing configuration element
	 * @param monitor the progress monitor
	 */
	public void doReimport(EditingDomain editingDomain, ReqIF reqIfContent, ImportConfiguration importConfiguration, IProgressMonitor monitor) {
		
		importer.init(reqIfContent, importConfiguration);
		
		// Created required types
		editingDomain.getCommandStack().execute(importer.importRequirementTypes(editingDomain, reqIfContent));
				
		// Do the actual imports
		editingDomain.getCommandStack().execute(importer.importRequirements(editingDomain, reqIfContent));
		
		//Import the requirement links
		editingDomain.getCommandStack().execute(importer.importRequirementLinks(editingDomain, reqIfContent));
	}

	@Override
	public void addPages() {
		mappingPage = new ReqIfMappingPage();
		importPage = new ReqIfFileConfigurationSelectionPage(model, mappingPage);
		addPage(importPage);
		addPage(mappingPage);
	}

}
