/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.thermal.ui.wizards;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.dialogs.DialogSettings;
import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchWizard;

import de.dlr.sc.virsat.model.extension.thermal.Activator;

/**
 * Export wizard for thermal CAD data
 */
public class CadThermalImportResultsWizard extends Wizard implements IWorkbenchWizard {
	
	public static final String ID = "de.dlr.sc.virsat.model.extension.thermal.ui.wizards.cadThermalImportResults";

	private CadThermalImportResultsPage page;
	private IContainer model;
	
	/**
	 * Create the wizard object
	 */
	public CadThermalImportResultsWizard() {
		super();
		setWindowTitle("CAD Thermal Import Results (Beta)");
		
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
//		CategoryAssignment selectedCA = (CategoryAssignment) page.getSelection();
//		String sourceFilePath = page.getDestination();
		
		Job exportJob = new Job("Performing Thermal CAD Export") {

			@Override
			protected IStatus run(IProgressMonitor monitor) {
				// TODO Auto-generated method stub
				return null;
			}

		};
		exportJob.schedule();

		return true;
	}
	
	
	
    @Override
    public void addPages() {
        page = new CadThermalImportResultsPage(model);
        addPage(page);
    }
}
