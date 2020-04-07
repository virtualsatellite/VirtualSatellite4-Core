/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.uiengine.ui.excel;

import java.io.File;
import java.io.FileOutputStream;

import org.eclipse.core.runtime.Status;
import org.eclipse.jface.dialogs.DialogSettings;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;

import de.dlr.sc.virsat.model.dvlm.provider.DVLMEditPlugin;
import de.dlr.sc.virsat.uieingine.ui.DVLMEditorPlugin;

/**
 * Wizard for exporting Excel files.
 * 
 * @author bell_Er
 *
 */

public class ExcelExportWizard extends Wizard implements INewWizard {

	private static final String ID = "de.dlr.sc.virsat.uiengine.ui";
	private ColumnViewer columnViewer;
	private ExcelExportPage page;
	private String type;
	/**
	 * Create a new export wizard
	 * @param columnViewer tableViewer to be exported
	 * @param type of the tableViewer to be exported
	 */

	public ExcelExportWizard(ColumnViewer columnViewer, String type) {
		super();
		setWindowTitle("Export to Excel");
		this.columnViewer = columnViewer;
		this.type = type;
		// Setup persistency if necessary
        IDialogSettings pluginSettings = de.dlr.sc.virsat.project.ui.Activator.getDefault().getDialogSettings();
        IDialogSettings wizardSettings = pluginSettings.getSection(ID);
        if (wizardSettings == null) {
            wizardSettings = new DialogSettings(ID);
            pluginSettings.addSection(wizardSettings);
        }
        setDialogSettings(wizardSettings);
	}

	@Override
	public void addPages() {
		super.addPages();
		page = new ExcelExportPage();
		addPage(page);
	}
	@Override
	public void init(IWorkbench workbench, IStructuredSelection selection) {
	}

	/**
	 * Perform Finish
	 * @return return true
	 */
	public boolean performFinish() {
		// Grab the necessary context information
		String path = page.getDestination();

		// Do the actual exporting

		File file = new File(path);
		
		try (FileOutputStream out = new FileOutputStream(file)) {
			ExcelExporter ee = new ExcelExporter();
			ee.export(columnViewer, path, type);
			
			DVLMEditPlugin.getPlugin().getLog().log(new Status(Status.INFO, "Excel IO",
					"Successfully exported to excel file to " + file.getAbsolutePath()));
		} catch (Exception e) {
			Status status = new Status(Status.ERROR, DVLMEditorPlugin.getPlugin().getSymbolicName(), "Failed to perform an export operation! ", e);
			DVLMEditPlugin.getPlugin().getLog().log(status);
			ErrorDialog.openError(Display.getDefault().getActiveShell(), "Excel IO Failed", "Export failed", status);
			return false;
		} 
		
		return true;
	}
	
}

