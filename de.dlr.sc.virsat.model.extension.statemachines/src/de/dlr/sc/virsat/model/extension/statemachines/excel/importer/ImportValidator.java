/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.statemachines.excel.importer;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.eclipse.emf.ecore.EObject;

import de.dlr.sc.virsat.excel.fault.Fault;
import de.dlr.sc.virsat.excel.fault.FaultType;
import de.dlr.sc.virsat.excel.importer.ExcelImportHelper;
import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import de.dlr.sc.virsat.model.extension.statemachines.excel.AExcelStatIO;
import de.dlr.sc.virsat.model.extension.statemachines.model.State;
import de.dlr.sc.virsat.model.extension.statemachines.model.StateMachine;
import de.dlr.sc.virsat.model.extension.statemachines.model.Transition;

/**
 * Class for checking the validity of an excel file before importing it.
 */
public class ImportValidator {
	private XSSFWorkbook wb;
	private StructuralElementInstance importSei;
	private List<State> states;
	private List<Transition> transitions;
	private List<Fault> faultList;

	/**
	 * Create a new validator
	 * @param object will be used as the root object for integrating the data
	 * @param wb the excel file
	 */
	public ImportValidator(EObject object, XSSFWorkbook wb) {
		if (object instanceof CategoryAssignment) {
			CategoryAssignment ca = (CategoryAssignment) object;
			StateMachine stateMaschine = new StateMachine(ca);
			importSei = (StructuralElementInstance) stateMaschine.getTypeInstance().eContainer();
			states = stateMaschine.getStates();
			transitions = stateMaschine.getTransitions();
			this.wb = wb;
			faultList = new ArrayList<Fault>();
		}
	}

	/**
	 * Validates depending on the type of the Structural element
	 * @return faultList the list of faults
	 */
	public List<Fault> validate() {
		validateHeaders();
		validateStates();
		validateTransitions();
		return faultList;
	}

	/**
	 * Validates the transitions when importing a state machine
	 */
	private void validateTransitions() {
		final XSSFSheet sheet = wb.getSheet(AExcelStatIO.TEMPLATE_SHEETNAME_TRANSITIONS);

		if (sheet == null) {
			return;
		}

		final int sheetIndex = wb.getSheetIndex(AExcelStatIO.TEMPLATE_SHEETNAME_TRANSITIONS);

		// Travel through all rows to find a fault
		for (int i = AExcelStatIO.COMMON_ROW_START_TABLE; i < sheet.getLastRowNum(); i++) {
			// if the row is empty move on the next rows
			if (ExcelImportHelper.isEmpty(i, sheet, AExcelStatIO.TRANSITION_COLUMN_TRANSITION_TO + 1)) {
				continue;
			}
			Row row = sheet.getRow(i);
			String tempUUID = Objects.toString(row.getCell(AExcelStatIO.COMMON_COLUMN_UUID), "");
			String tempDelete = Objects.toString(row.getCell(AExcelStatIO.COMMON_COLUMN_DELETE), "");
			if ("".equals(tempUUID)) {

				if (tempDelete.equals(AExcelStatIO.COMMON_DELETEMARK_VALUE)) {
					faultList.add(new Fault(StatFaultType.CANT_DELETE_NON_EXISTING_TRANSITION, sheetIndex, i));
				}
			} else {
				int check = ExcelImportHelper.containsABeanCategoryAssignmentUUID(tempUUID, transitions);
				if (check < 0) {
					faultList.add(new Fault(StatFaultType.TRANSITION_UUID_NOT_FOUND, sheetIndex, i));
				}
			}
			// control the delete column, value of this column can be 1 or nothing	
			if (!(tempDelete.equals(AExcelStatIO.COMMON_DELETEMARK_VALUE) || tempDelete.equals(""))) {
				faultList.add(new Fault(FaultType.DELETE_COLUMN_CAN_BE_EMPTY_OR_1, sheetIndex, i));
			}
			// Control if the FromState does exist or not
			String tempTransitionFrom = Objects.toString(row.getCell(AExcelStatIO.TRANSITION_COLUMN_TRANSITION_FROM), "");
			int check = ExcelImportHelper.containsABeanCategoryAssignmentName(tempTransitionFrom, states);
			if (check < 0) {
				faultList.add(new Fault(StatFaultType.FROM_STATE_NOT_FOUND, sheetIndex, i));
			}
			// Control if the ToState does exist or not
			String tempTransitionTo = Objects.toString(row.getCell(AExcelStatIO.TRANSITION_COLUMN_TRANSITION_TO), "");
			check = ExcelImportHelper.containsABeanCategoryAssignmentName(tempTransitionTo, states);
			if (check < 0) {
				faultList.add(new Fault(StatFaultType.TO_STATE_NOT_FOUND, sheetIndex, i));
			}
		}
	}

	/**
	 * Validates the states when importing a state machine
	 */
	private void validateStates() {
		final XSSFSheet sheet = wb.getSheet(AExcelStatIO.TEMPLATE_SHEETNAME_STATES);

		if (sheet == null) {
			return;
		}

		final int sheetIndex = wb.getSheetIndex(AExcelStatIO.TEMPLATE_SHEETNAME_STATES);

		// Travel through all rows to find a fault
		for (int i = AExcelStatIO.COMMON_ROW_START_TABLE; i < sheet.getLastRowNum(); i++) {
			// if the row is empty move on the next rows
			if (ExcelImportHelper.isEmpty(i, sheet, AExcelStatIO.STATE_COLUMN_STATE_NAME + 1)) {
				continue;
			}
			Row row = sheet.getRow(i);
			String tempUUID = Objects.toString(row.getCell(AExcelStatIO.COMMON_COLUMN_UUID), "");
			String tempDelete = Objects.toString(row.getCell(AExcelStatIO.COMMON_COLUMN_DELETE), "");
			if ("".equals(tempUUID)) {
				if (tempDelete.equals(AExcelStatIO.COMMON_DELETEMARK_VALUE)) {
					faultList.add(new Fault(StatFaultType.CANT_DELETE_NON_EXISTING_STATE, sheetIndex, i));
				}
			} else {
				int check = ExcelImportHelper.containsABeanCategoryAssignmentUUID(tempUUID, states);
				if (check < 0) {
					faultList.add(new Fault(StatFaultType.STATE_UUID_NOT_FOUND, sheetIndex, i));
				}
			}
			// control the delete column, value of this column can be 1 or nothing
			if (!(tempDelete.equals(AExcelStatIO.COMMON_DELETEMARK_VALUE) || tempDelete.equals(""))) {
				faultList.add(new Fault(FaultType.DELETE_COLUMN_CAN_BE_EMPTY_OR_1, sheetIndex, i));
			}
			//State_name cannot be null
			String tempStateName = Objects.toString(row.getCell(AExcelStatIO.STATE_COLUMN_STATE_NAME), "");
			if ("".equals(tempStateName)) {
				faultList.add(new Fault(StatFaultType.STATE_NAME_IS_NOT_SET, sheetIndex, i));
			}
		}
	}

	/**
	 * Validates the Header Pages for all
	 */
	public void validateHeaders() {
		final XSSFSheet sheet = wb.getSheet(AExcelStatIO.TEMPLATE_SHEETNAME_HEADER);
		final int sheetIndex = wb.getSheetIndex(AExcelStatIO.TEMPLATE_SHEETNAME_HEADER);

		// Control if we are importing the correct Structural element by comparing UUIDs
		String tempUUID = Objects.toString(sheet.getRow(AExcelStatIO.HEADER_ROW_STRUCTURALELEMENT_UUID).getCell(1), "");
		if (!(importSei.getUuid().toString().equals(tempUUID))) {
			faultList.add(new Fault(FaultType.STRUCTURAL_ELEMENT_UUIDS_DO_NOT_MATCH, sheetIndex, AExcelStatIO.HEADER_ROW_STRUCTURALELEMENT_UUID));
		}
		// Control if we are importing the correct Structural element by comparing NAMEs
		String tempName = Objects.toString(sheet.getRow(AExcelStatIO.HEADER_ROW_STRUCTURALELEMENT_NAME).getCell(1), "");
		if (!(importSei.getName().equals(tempName))) {
			faultList.add(new Fault(FaultType.STRUCTURAL_ELEMENT_NAMES_DO_NOT_MATCH, sheetIndex, AExcelStatIO.HEADER_ROW_STRUCTURALELEMENT_NAME));
		}
	}
}
