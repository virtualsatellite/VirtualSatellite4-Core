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

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.dialogs.DialogSettings;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;

import de.dlr.sc.virsat.excel.commands.ImportCommand;
import de.dlr.sc.virsat.excel.fault.Fault;
import de.dlr.sc.virsat.excel.importer.ExcelImporter;
import de.dlr.sc.virsat.model.dvlm.provider.DVLMEditPlugin;
import de.dlr.sc.virsat.project.editingDomain.VirSatEditingDomainRegistry;
import de.dlr.sc.virsat.project.ui.Activator;

/**
 * Wizard for importing Excel files.
 */
public class ImportWizard extends Wizard implements INewWizard {

	private static final String ID = "de.dlr.sc.virsat.excel.ui.import";

	private ImportPage page;
	private IContainer model;
	private ISelection selection;

	/**
	 * Create a new import wizard
	 */
	public ImportWizard() {
		super();
		setWindowTitle("Import from Excel");

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
	 * Set the root element for the import selection
	 * @param iContainer root element for the import selection
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

	@Override
	public boolean performFinish() {
		EObject eObject = (EObject) page.getSelection();

		// Read the file
		String path = page.getDestination();
		File file = new File(path);
		XSSFWorkbook wb = null;
		try {
			wb = new XSSFWorkbook(new FileInputStream(file));
		} catch (IOException e) {
			Status status = new Status(Status.ERROR, "de.dlr.sc.virsat.excel.ui", "Failed to create the workbook ", e);
			DVLMEditPlugin.getPlugin().getLog().log(status);
		}
		TransactionalEditingDomain ed = VirSatEditingDomainRegistry.INSTANCE.getEd(eObject);
		try {
			ExcelImporter ei = new ExcelImporter();
			List<Fault> faultList = ei.validate(eObject, wb);
			if (faultList.isEmpty()) {
				ImportCommand importCommand = new ImportCommand(eObject, wb, ed);
				ed.getCommandStack().execute(importCommand);
				DVLMEditPlugin.getPlugin().getLog().log(new Status(Status.INFO, "Excel IO", "Successfully imported excel file " + file.getAbsolutePath()));
				return true;
			}
			StringBuilder errorMessage = new StringBuilder();
			for (int i = 0; i < faultList.size(); i++) {
				errorMessage.append(faultList.get(i).getFaultType().toString() + " in sheet " + faultList.get(i).getSheetNumber() + " in line " + faultList.get(i).getLineNumber() + "\n");	     		
			}
			page.setErrorMessage(errorMessage.toString());
		} catch (Exception e) {
			Status status = new Status(Status.ERROR, "de.dlr.sc.virsat.excel.ui", "Failed to perform an import operation! ", e);
			DVLMEditPlugin.getPlugin().getLog().log(status);
			ErrorDialog.openError(Display.getDefault().getActiveShell(), "Excel IO Failed", "Import failed", status);
			return false;
		}
		return false;
	}

	@Override
	public void addPages() {
		super.addPages();
		page = new ImportPage(model, selection);
		addPage(page);
	}
}
