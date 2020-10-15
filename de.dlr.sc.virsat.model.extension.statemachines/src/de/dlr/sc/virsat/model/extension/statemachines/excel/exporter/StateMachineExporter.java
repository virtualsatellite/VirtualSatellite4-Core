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
import java.time.LocalDateTime;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.ui.statushandlers.StatusManager;

import de.dlr.sc.virsat.excel.exporter.ExcelExportHelper;
import de.dlr.sc.virsat.excel.exporter.IExport;
import de.dlr.sc.virsat.model.concept.list.IBeanList;
import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import de.dlr.sc.virsat.model.extension.statemachines.Activator;
import de.dlr.sc.virsat.model.extension.statemachines.excel.AExcelStatIO;
import de.dlr.sc.virsat.model.extension.statemachines.model.State;
import de.dlr.sc.virsat.model.extension.statemachines.model.StateMachine;
import de.dlr.sc.virsat.model.extension.statemachines.model.Transition;

/**
 * Class for exporting excel
 */
public class StateMachineExporter implements IExport {
	
	private static final String DEFAULT_TEMPLATE_PATH = "/resources/StateMachineExportTemplate.xlsx";
	
	protected LocalDateTime localDateTime;
	protected ExcelExportHelper helper = new ExcelExportHelper();
	private CategoryAssignment exportCa;
	
	public StateMachineExporter() {
		this(LocalDateTime.now());
	}
	
	public StateMachineExporter(LocalDateTime localDateTime) {
		this.localDateTime = localDateTime;
	}

	@Override
	public void export(EObject eObject, String path, boolean useDefaultTemplate, String templatePath) {
		if (eObject instanceof CategoryAssignment) {
			CategoryAssignment ca = (CategoryAssignment) eObject;
			// find the export template
			try {
				InputStream iStream = null;
				if (useDefaultTemplate) {
					iStream = Activator.getResourceContentAsString(DEFAULT_TEMPLATE_PATH);
				} else {
					iStream = new FileInputStream(templatePath);
				}
				helper.setWb(new XSSFWorkbook(iStream));
				exportData(ca);
				// find the export destination
				String newPath = path + "/" + ca.getFullQualifiedInstanceName() + ".xlsx";
				// and write the results
				File file = new File(newPath);
				try (FileOutputStream out = new FileOutputStream(file)) {
					helper.getWb().write(out);
				}
			} catch (IOException e) {
				Status status = new Status(Status.ERROR, Activator.getPluginId(), "Failed to perform an export operation!" + System.lineSeparator() + e.getMessage(), e);
				StatusManager.getManager().handle(status, StatusManager.LOG | StatusManager.SHOW);
			}
		}
	}

	/**
	 * Exports the state machine
	 * @param ca object to be exported
	 */
	protected void exportData(CategoryAssignment ca) {
		exportCa = ca;
		StructuralElementInstance exportSei = (StructuralElementInstance) exportCa.eContainer();
		// Create the header sheet
		helper.writeHeaderSheet(exportSei, localDateTime);
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
	*/
	private void createDataSheetStates() {
		XSSFSheet sheet = helper.getWb().getSheet(AExcelStatIO.TEMPLATE_SHEETNAME_STATES);
		if (sheet == null) {
			sheet = helper.getWb().createSheet(AExcelStatIO.TEMPLATE_SHEETNAME_STATES);
		}
		StateMachine stateMaschine = new StateMachine(exportCa);
		IBeanList<State> states = stateMaschine.getStates();
		helper.instantiateCells(sheet, states.size() + AExcelStatIO.COMMON_ROW_START_TABLE, AExcelStatIO.TRANSITION_COLUMN_TRANSITION_FROM + 1);
		// for each interface end, fill out a row
		int i = AExcelStatIO.COMMON_ROW_START_TABLE;
		for (State state : states) {
			Row row = sheet.getRow(i);
			row.getCell(AExcelStatIO.COMMON_COLUMN_UUID).setCellValue(helper.getCreationHelper().createRichTextString(state.getTypeInstance().getUuid().toString()));
			row.getCell(AExcelStatIO.TRANSITION_COLUMN_TRANSITION_NAME).setCellValue(helper.getCreationHelper().createRichTextString(state.getName()));
			i++;
		}
	}

	/**
	* Creates the data sheet for Transitions and populates it with the data
	*/
	private void createDataSheetTransitions() {
		XSSFSheet sheet = helper.getWb().getSheet(AExcelStatIO.TEMPLATE_SHEETNAME_TRANSITIONS);
		if (sheet == null) {
			sheet = helper.getWb().createSheet(AExcelStatIO.TEMPLATE_SHEETNAME_TRANSITIONS);
		}

		StateMachine stateMaschine = new StateMachine(exportCa);
		IBeanList<Transition> transitions = stateMaschine.getTransitions();
		helper.instantiateCells(sheet, transitions.size() + AExcelStatIO.COMMON_ROW_START_TABLE, AExcelStatIO.TRANSITION_COLUMN_TRANSITION_TO + 1);
		int i = AExcelStatIO.COMMON_ROW_START_TABLE;

		for (Transition transition : transitions) {
			Row row = sheet.getRow(i);
			row.getCell(AExcelStatIO.COMMON_COLUMN_UUID).setCellValue(helper.getCreationHelper().createRichTextString(transition.getTypeInstance().getUuid().toString()));
			row.getCell(AExcelStatIO.TRANSITION_COLUMN_TRANSITION_NAME).setCellValue(helper.getCreationHelper().createRichTextString(transition.getName()));
			if (transition.getStateFrom() != null) {
				row.getCell(AExcelStatIO.TRANSITION_COLUMN_TRANSITION_FROM).setCellValue(helper.getCreationHelper().createRichTextString(transition.getStateFrom().getName()));
			}
			if (transition.getStateTo() != null) {
				row.getCell(AExcelStatIO.TRANSITION_COLUMN_TRANSITION_TO).setCellValue(helper.getCreationHelper().createRichTextString(transition.getStateTo().getName()));
			}
			i++;
		}
	}
}
