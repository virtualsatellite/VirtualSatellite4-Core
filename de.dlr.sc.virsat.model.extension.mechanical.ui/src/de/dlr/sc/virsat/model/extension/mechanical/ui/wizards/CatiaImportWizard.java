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
import de.dlr.sc.virsat.model.extension.mechanical.catia.CatiaFileHandler;
import de.dlr.sc.virsat.model.extension.mechanical.catia.CatiaImporter;
import de.dlr.sc.virsat.model.extension.mechanical.ui.Activator;
import de.dlr.sc.virsat.project.editingDomain.VirSatEditingDomainRegistry;
import de.dlr.sc.virsat.project.editingDomain.VirSatTransactionalEditingDomain;

/**
 * 
 *
 */
public class CatiaImportWizard extends Wizard implements IWorkbenchWizard {

	public static final String ID = "de.dlr.sc.virsat.model.extension.mechanical.ui.wizards.catiaImport";

	private CatiaImportPage page;
	private IContainer model;
	private CatiaImporter importer = new CatiaImporter();
	
	/**
	 * Default constructor
	 */
	public CatiaImportWizard() {
		super();
		setWindowTitle("Catia JSON Import");

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
		CatiaFileHandler fileHandler = new CatiaFileHandler();
		
		Job exportJob = new Job("Performing Catia JSON Import") {
			@Override
			protected IStatus run(IProgressMonitor monitor) {
				try {
					JsonObject jsonContent = fileHandler.readJsonFile(inputJsonFilePath);
					
					VirSatTransactionalEditingDomain editingDomain = VirSatEditingDomainRegistry.INSTANCE.getEd(sei);
					Map<String, StructuralElementInstance> mapping = 
							importer.mapJsonUuidToSEI(jsonContent, productRoot);
					// TODO handle unmapped elements with importer.getUnmappedElements(...)
					Command importCommnd = importer.transform(editingDomain, jsonContent, mapping);
					editingDomain.getCommandStack().execute(importCommnd);
					editingDomain.saveAll();
					ResourcesPlugin.getWorkspace().getRoot().refreshLocal(IResource.DEPTH_INFINITE, null);
					
					return Status.OK_STATUS;
				} catch (JsonException | IOException | CoreException e) {
					Status status = new Status(Status.ERROR, Activator.getPluginId(),
							"CatiaImportWizard: Failed to perform import!", e);
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
		page = new CatiaImportPage(model);
		addPage(page);
		//TODO page for mapping of new elements
	}

}
