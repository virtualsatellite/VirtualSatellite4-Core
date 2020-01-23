/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.statemachines.excel.exporter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.swt.widgets.Display;

import de.dlr.sc.virsat.excel.AExcelIo;
import de.dlr.sc.virsat.excel.exporter.ExcelExportHelper;
import de.dlr.sc.virsat.excel.exporter.IExport;
import de.dlr.sc.virsat.model.concept.list.IBeanList;
import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import de.dlr.sc.virsat.model.extension.statemachines.Activator;
import de.dlr.sc.virsat.model.extension.statemachines.model.State;
import de.dlr.sc.virsat.model.extension.statemachines.model.StateMachine;
import de.dlr.sc.virsat.model.extension.statemachines.model.Transition;




/**
 * Class for exporting excel
 * @author bell_er
 *
 */
public class StateMachineExporter extends ExcelExportHelper implements IExport {
	
	CategoryAssignment exportCa;
	
	@Override
	public void export(EObject eObject, String path, boolean useDefaultTemplate, String templatePath) {
		if (eObject instanceof CategoryAssignment) {		
			CategoryAssignment ca = (CategoryAssignment) eObject;
			final String defaultTemplatePath = "/resources/StateMachineExportTemplate.xlsx";
			// find the export template
			try {
				InputStream is = null;
				if (useDefaultTemplate) {
					is = Activator.getResourceContentAsString(defaultTemplatePath);
				} else {
					is = new FileInputStream(templatePath);
				}
				wb = new XSSFWorkbook(is);
			} catch (IOException e) {
				Status status = new Status(Status.ERROR, Activator.getPluginId(), "Failed to find the Template!", e);
				Activator.getDefault().getLog().log(status);
			}
			
			exportData(ca);
			String newPath = path + "/" + ca.getFullQualifiedInstanceName() + ".xlsx";
			// and write the results	
			File file = new File(newPath);
			try (FileOutputStream out = new FileOutputStream(file)) {
				wb.write(out);
			} catch (IOException e) {
				Status status = new Status(Status.ERROR, Activator.getPluginId(), "Failed to perform an export operation!", e);
				Activator.getDefault().getLog().log(status);
				ErrorDialog.openError(Display.getDefault().getActiveShell(), "Excel IO Failed", "Export failed", status);
			}
		}
	}
	
	/**
	 * Exports the state machine
	 * @param ca object to be exported
	 * @author  Bell_er
	 * 
	 */
	public void exportData(CategoryAssignment ca) {
		exportCa = ca;
		StructuralElementInstance exportSei = (StructuralElementInstance) exportCa.eContainer();
		// Create the header sheet
		setHeaders(exportSei);
		// create the state sheet
		createDataSheetStates();
		// create the transition sheet
		createDataSheetTransitions();	
	}

	@Override
	public boolean canExport(Object selection) {
		if (selection instanceof CategoryAssignment) {
			CategoryAssignment ca = (CategoryAssignment) selection;
			if (ca.getType().getName().equals(StateMachine.class.getSimpleName())) {
				return true;
			}
		}
		return false;
	}

	/**
	* Creates the data sheet for States and populates it with the data
	* 
	* @author  Bell_er
	* 
	*/
	private void createDataSheetStates() {
		XSSFSheet sheet = wb.getSheet(AExcelIo.TEMPLATE_SHEETNAME_STATES);
		if (sheet == null) {
			sheet = wb.createSheet(AExcelIo.TEMPLATE_SHEETNAME_STATES);
		}
		StateMachine sm = new StateMachine(exportCa);
		IBeanList<State> states =  sm.getStates();
		nullChecker(states.size() + AExcelIo.COMMON_ROW_START_TABLE, sheet, AExcelIo.INTERFACEEND_COLUMN_INTERFACEEND_TYPE + 1);
		// for each interface end, fill out a row
		int i = AExcelIo.COMMON_ROW_START_TABLE;
		for (State s : states) {
			Row row = sheet.getRow(i);
			row.getCell(AExcelIo.COMMON_COLUMN_UUID).setCellValue(getCreationHelper().createRichTextString(s.getTypeInstance().getUuid().toString()));
			row.getCell(AExcelIo.INTERFACEEND_COLUMN_INTERFACEEND_NAME).setCellValue(getCreationHelper().createRichTextString(s.getName()));
			i++;
		}
	}
	
	/**
	* Creates the data sheet for Transitions and populates it with the data
	* 
	* @author  Bell_er
	* 
	*/
	private void createDataSheetTransitions() {
		XSSFSheet sheet = wb.getSheet(AExcelIo.TEMPLATE_SHEETNAME_TRANSITIONS);
		if (sheet == null) {
			sheet = wb.createSheet(AExcelIo.TEMPLATE_SHEETNAME_TRANSITIONS);
		}	
	
		StateMachine sm = new StateMachine(exportCa);
		IBeanList<Transition> transitions =  sm.getTransitions();
		nullChecker(transitions.size() + AExcelIo.COMMON_ROW_START_TABLE, sheet, AExcelIo.INTERFACE_COLUMN_INTERFACE_TO + 1);
		int i = AExcelIo.COMMON_ROW_START_TABLE;
		
		for (Transition t : transitions) {
			Row row = sheet.getRow(i);
			row.getCell(AExcelIo.COMMON_COLUMN_UUID).setCellValue(getCreationHelper().createRichTextString(t.getTypeInstance().getUuid().toString()));
			row.getCell(AExcelIo.TRANSITION_COLUMN_TRANSITION_NAME).setCellValue(getCreationHelper().createRichTextString(t.getName()));
			if (t.getStateFrom() != null) {
				row.getCell(AExcelIo.TRANSITION_COLUMN_TRANSITION_FROM).setCellValue(getCreationHelper().createRichTextString(t.getStateFrom().getName()));
			}
			if (t.getStateTo() != null) {
				row.getCell(AExcelIo.TRANSITION_COLUMN_TRANSITION_TO).setCellValue(getCreationHelper().createRichTextString(t.getStateTo().getName()));
			}	
			i++;
		}
	}
}
