/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.excel.ui.wizards;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.dialogs.DialogSettings;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;

import de.dlr.sc.virsat.excel.exporter.ExcelExporter;
import de.dlr.sc.virsat.model.dvlm.provider.DVLMEditPlugin;
import de.dlr.sc.virsat.project.ui.Activator;

/**
 * Wizard for exporting Excel files.
 */
public class ExportWizard extends Wizard implements INewWizard {

	private static final String ID = "de.dlr.sc.virsat.excel.ui.export";

	private ExportPage page;
	private IContainer model;
	private ISelection selection;
	
	/**
	 * Create a new export wizard
	 */
	public ExportWizard() {
		super();
		setWindowTitle("Export to Excel");

		// Setup persistency if necessary
        IDialogSettings pluginSettings = Activator.getDefault().getDialogSettings();
        IDialogSettings wizardSettings = pluginSettings.getSection(ID);
        if (wizardSettings == null) {
            wizardSettings = new DialogSettings(ID);
            pluginSettings.addSection(wizardSettings);
        }
        setDialogSettings(wizardSettings);
	}

	/**
	 * Set the root element for the element selection
	 * 
	 * @param iContainer root element for the export selection
	 */
	public void setModel(IContainer iContainer) {
		this.model = iContainer;
	}

	/**
	 * Set the selection for the new window
	 * @param selection to be set
	 */
	public void setSelection(ISelection selection) {
		this.selection = selection;
	}

	@Override
	public void init(IWorkbench workbench, IStructuredSelection selection) {
		setModel(ResourcesPlugin.getWorkspace().getRoot());
	}

	/**
	 * Perform Finish
	 * @return return true
	 */
	public boolean performFinish() {
		// Grab the necessary context information
		String path = page.getDestination();
		getDialogSettings().put(ExportPage.DESTINATION_FILE_KEY, path);
		
		EObject eObject = (EObject) page.getSelection();

		// Do the actual exporting
		
		try {
			ExcelExporter ee = new ExcelExporter();
			ee.export(eObject, path, page.getUseDefaultTemplate(), page.getTemplate());

			DVLMEditPlugin.getPlugin().getLog().log(new Status(Status.INFO, "Excel IO",
					"Successfully exported to excel file to " + path));
		} catch (Exception e) {
			Status status = new Status(Status.ERROR, "de.dlr.sc.virsat.excel.ui", "Failed to perform an export operation! ", e);
			DVLMEditPlugin.getPlugin().getLog().log(status);
			ErrorDialog.openError(Display.getDefault().getActiveShell(), "Excel IO Failed", "Export failed", status);
			return false;
		}
		return true;
	}
	
	@Override
	public void addPages() {
		super.addPages();
		page = new ExportPage(model, selection);
		addPage(page);
	}
	
	@Override
	public boolean canFinish() {
		ExcelExporter ee = new ExcelExporter();
		boolean canExport = false;
		try {
			canExport = ee.canExport(page.getSelection());
		} catch (CoreException e) {
			Status status = new Status(Status.ERROR, "de.dlr.sc.virsat.excel.ui", "Failed to perform an export operation! ", e);
			DVLMEditPlugin.getPlugin().getLog().log(status);
			ErrorDialog.openError(Display.getDefault().getActiveShell(), "Excel IO Failed", "Export failed", status);
		}
		return (canExport && page.isComplete());
	}
}
