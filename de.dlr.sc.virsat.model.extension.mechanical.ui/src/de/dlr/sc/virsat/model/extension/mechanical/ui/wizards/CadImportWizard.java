/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.mechanical.ui.wizards;

import java.io.IOException;
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
import org.eclipse.emf.common.command.Command;
import org.eclipse.jface.dialogs.DialogSettings;
import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchWizard;
import org.eclipse.ui.statushandlers.StatusManager;

import com.github.cliftonlabs.json_simple.JsonException;
import com.github.cliftonlabs.json_simple.JsonObject;

import de.dlr.sc.virsat.model.concept.types.structural.BeanStructuralElementInstance;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import de.dlr.sc.virsat.model.extension.mechanical.cad.CadFileHandler;
import de.dlr.sc.virsat.model.extension.mechanical.cad.CadImporter;
import de.dlr.sc.virsat.model.extension.mechanical.ui.Activator;
import de.dlr.sc.virsat.project.editingDomain.VirSatEditingDomainRegistry;
import de.dlr.sc.virsat.project.editingDomain.VirSatTransactionalEditingDomain;

/**
 * 
 *
 */
public class CadImportWizard extends Wizard implements IWorkbenchWizard {

	public static final String ID = "de.dlr.sc.virsat.model.extension.mechanical.ui.wizards.cadImport";

	private CadImportPage page;
	private IContainer model;
	private CadImporter importer = new CadImporter();
	private static final int NUMBER_PROGRESS_TICKS = 3;
	
	/**
	 * Default constructor
	 */
	public CadImportWizard() {
		super();
		setWindowTitle("Cad JSON Import (Beta)");

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
	}

	@Override
	public boolean performFinish() {
		StructuralElementInstance sei = (StructuralElementInstance) page.getSelection();
		BeanStructuralElementInstance productRoot = new BeanStructuralElementInstance(sei);

		String inputJsonFilePath = page.getDestination();
		CadFileHandler fileHandler = new CadFileHandler();
		
		Job exportJob = new Job("Performing Cad JSON Import") {
			@Override
			protected IStatus run(IProgressMonitor monitor) {
				SubMonitor importSubMonitor = SubMonitor.convert(monitor, NUMBER_PROGRESS_TICKS);
				try {
					JsonObject jsonContent = fileHandler.readJsonFile(inputJsonFilePath);
					importSubMonitor.worked(1);
					
					VirSatTransactionalEditingDomain editingDomain = VirSatEditingDomainRegistry.INSTANCE.getEd(sei);
					Map<String, StructuralElementInstance> mapping = 
							importer.mapJsonUuidToSEI(jsonContent, productRoot);

					Command importCommnd = importer.transform(editingDomain, jsonContent, mapping);
					if (!importCommnd.canExecute()) {
						Status status = new Status(Status.ERROR, Activator.getPluginId(),
								"CadImportWizard: The import command is not exectuable!");
						StatusManager.getManager().handle(status, StatusManager.LOG | StatusManager.SHOW);
						return Status.CANCEL_STATUS;
					}
					editingDomain.getCommandStack().execute(importCommnd);
					importSubMonitor.worked(1);
					
					editingDomain.saveAll();
					ResourcesPlugin.getWorkspace().getRoot().refreshLocal(IResource.DEPTH_INFINITE, null);
					importSubMonitor.worked(1);
					
					return Status.OK_STATUS;
				} catch (JsonException | IOException | CoreException e) {
					Status status = new Status(Status.ERROR, Activator.getPluginId(),
							"CadImportWizard: Failed to perform import!", e);
					StatusManager.getManager().handle(status, StatusManager.LOG | StatusManager.SHOW);
					return Status.CANCEL_STATUS;
				}
			}
		};
		exportJob.schedule();

		return true;
	}

	@Override
	public void addPages() {
		page = new CadImportPage(model);
		addPage(page);
	}

}
