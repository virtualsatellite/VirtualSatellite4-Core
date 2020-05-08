/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.uiengine.ui.mat;

import java.io.IOException;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.dialogs.DialogSettings;
import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.statushandlers.StatusManager;

import de.dlr.sc.virsat.model.dvlm.mat.MatExporter;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import de.dlr.sc.virsat.uieingine.ui.DVLMEditorPlugin;
import us.hebi.matlab.mat.format.Mat5;
import us.hebi.matlab.mat.types.MatFile;

/**
 * Wizard for exporting data into a .matfile
 *
 */

public class MatExportWizard extends Wizard implements INewWizard {
	
	public static final String ID = "de.dlr.sc.virsat.uiengine.ui.wizards.matExport";

	MatExporter matExporter = new MatExporter();
	private MatExportPage page;
	private IContainer model;
	
	/**
	 * Default constructor
	 */
	public MatExportWizard() {
		super();
		setWindowTitle("Mat JSON Export (Beta)");
		
		// Setup persistency
		IDialogSettings pluginSettings = DVLMEditorPlugin.getPlugin().getDialogSettings();
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
		String outputMatFilePath = page.getDestination();
		
		Job exportJob = new Job("Performing Mat Export") {
			@Override
			protected IStatus run(IProgressMonitor monitor) {
				try {
					MatFile matFile = matExporter.exportSei(sei);
					Mat5.writeToFile(matFile, outputMatFilePath);
					return Status.OK_STATUS;
				} catch (IOException e) {
					Status status = new Status(Status.ERROR, DVLMEditorPlugin.getPlugin().getSymbolicName(), 
							"MatExportWizard: Failed to perform export!", e);
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
        page = new MatExportPage(model);
        addPage(page);
    }
}
