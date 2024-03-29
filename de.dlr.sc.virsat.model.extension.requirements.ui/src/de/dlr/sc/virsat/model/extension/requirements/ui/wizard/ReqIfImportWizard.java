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

import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.core.runtime.jobs.Job;
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
	private static final int NUMBER_PROGRESS_TICKS_IMPORT = 5;
	private static final int NUMBER_PROGRESS_TICKS_REIMPORT = 3;

	private ReqIfFileConfigurationSelectionPage importPage;
	private ReqIfMappingPage mappingPage;
	private ReqIfTypeSelectionPage typeSelectionPage;
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
		final RequirementsConfiguration typeContainer = typeSelectionPage.getRequirementTypeContainer();
		final List<String> requirementTypeList = typeSelectionPage.getListOfRequirementTypeKeys();
		final boolean groupSupport = mappingPage.getGroupSupport();

		// Do the import
		Job importJob = new Job("Performing Requirements ReqIF Import") {
			@Override
			protected IStatus run(IProgressMonitor monitor) {
				
				VirSatTransactionalEditingDomain editingDomain = VirSatEditingDomainRegistry.INSTANCE
						.getEd(reqConfiguration);
				ReqIF reqIfContent = mappingPage.getReqIfContent();
				
				if (reqConfiguration instanceof StructuralElementInstance) {
					doImport(editingDomain, reqIfContent, specMapping, new RequirementsConfigurationCollection((StructuralElementInstance) reqConfiguration), typeContainer, requirementTypeList, groupSupport, monitor);
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
	 * @param groupSupport If true using RequirementGroups for requirement objects with children
	 * @param monitor the progress monitor
	 */
	public void doImport(EditingDomain editingDomain, 
			ReqIF reqIfContent, 
			Map<Specification, StructuralElementInstance> specMapping, 
			RequirementsConfigurationCollection configurationContainer, 
			RequirementsConfiguration typeContainer, 
			List<String> requirementTypeList, 
			boolean groupSupport, 
			IProgressMonitor monitor) {
		SubMonitor importSubMonitor = SubMonitor.convert(monitor, NUMBER_PROGRESS_TICKS_IMPORT);
		
		importer.init(reqIfContent, configurationContainer);
		
		// Preparation
		editingDomain.getCommandStack().execute(importer.persistSpecificationMapping(editingDomain, specMapping, reqIfContent, requirementTypeList, groupSupport, configurationContainer));
		importSubMonitor.worked(1);
		editingDomain.getCommandStack().execute(importer.persistRequirementTypeContainer(editingDomain, typeContainer));
		importSubMonitor.worked(1);
		
		// Created required types
		editingDomain.getCommandStack().execute(importer.importRequirementTypes(editingDomain, reqIfContent));
		importSubMonitor.worked(1);
		
		// Do the actual imports
		editingDomain.getCommandStack().execute(importer.importRequirements(editingDomain, reqIfContent));
		importSubMonitor.worked(1);
		
		//Import the requirement links
		editingDomain.getCommandStack().execute(importer.importRequirementLinks(editingDomain, reqIfContent));
		importSubMonitor.worked(1);
	}
	
	/**
	 * Do a re-import in if an import configuration already exists. Existing requirement values are overwritten.
	 * 
	 * @param editingDomain the editing domain
	 * @param reqIfContent the content to import
	 * @param importConfiguration the existing configuration element
	 * @param monitor the progress monitor
	 */
	public void doReimport(EditingDomain editingDomain, ReqIF reqIfContent, ImportConfiguration importConfiguration, IProgressMonitor monitor) {
		SubMonitor importSubMonitor = SubMonitor.convert(monitor, NUMBER_PROGRESS_TICKS_REIMPORT);
		
		importer.init(reqIfContent, importConfiguration);
		
		// Created required types
		editingDomain.getCommandStack().execute(importer.importRequirementTypes(editingDomain, reqIfContent));
		importSubMonitor.worked(1);
				
		// Do the actual imports
		editingDomain.getCommandStack().execute(importer.importRequirements(editingDomain, reqIfContent));
		importSubMonitor.worked(1);
		
		//Import the requirement links
		editingDomain.getCommandStack().execute(importer.importRequirementLinks(editingDomain, reqIfContent));
		importSubMonitor.worked(1);
	}

	@Override
	public void addPages() {
		typeSelectionPage = new ReqIfTypeSelectionPage();
		mappingPage = new ReqIfMappingPage(typeSelectionPage);
		importPage = new ReqIfFileConfigurationSelectionPage(model, mappingPage);
		addPage(importPage);
		addPage(mappingPage);
		addPage(typeSelectionPage);
	}

}
