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

import java.util.List;
import java.util.Objects;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.eclipse.emf.ecore.EObject;

import de.dlr.sc.virsat.excel.fault.Fault;
import de.dlr.sc.virsat.excel.importer.ExcelImportHelper;
import de.dlr.sc.virsat.excel.importer.IImport;
import de.dlr.sc.virsat.model.dvlm.Repository;
import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.extension.statemachines.excel.AExcelStatIO;
import de.dlr.sc.virsat.model.extension.statemachines.model.State;
import de.dlr.sc.virsat.model.extension.statemachines.model.StateMachine;
import de.dlr.sc.virsat.model.extension.statemachines.model.Transition;

/**
 * Class for Importing Excel files.
 */
public class SMImporter implements IImport {
	private XSSFWorkbook wb;
	private StateMachine stateMaschine;
	private Concept concept;

	@Override
	public void importExcel(EObject eObject, Repository repository, XSSFWorkbook wb) {
		init(eObject, repository, wb);
		importStates();
		importTransitions();
	}

	/**
	 * imports the transitions
	 */
	private void importTransitions() {
		final Sheet sheet = wb.getSheet(AExcelStatIO.TEMPLATE_SHEETNAME_TRANSITIONS);

		if (sheet == null) {
			return;
		}
		// go through each row to find out what to do

		for (int i = AExcelStatIO.COMMON_ROW_START_TABLE; i < sheet.getLastRowNum(); i++) {
			if (ExcelImportHelper.isEmpty(i, sheet, AExcelStatIO.TRANSITION_COLUMN_TRANSITION_TO + 1)) {
				continue;
			}
			Row row = sheet.getRow(i);
			List<State> states = stateMaschine.getStates();
			List<Transition> transitions = stateMaschine.getTransitions();
			// Get the UUID of the first transition
			String tempUUID = Objects.toString(row.getCell(AExcelStatIO.COMMON_COLUMN_UUID), "");
			// figure out if we are creating a new Transition

			if ("".equals(tempUUID)) {
				//create new transition and add it to our State Machine
				Transition transition = new Transition(concept);
				transition.setName(row.getCell(AExcelStatIO.TRANSITION_COLUMN_TRANSITION_NAME).toString());
				int check = ExcelImportHelper.containsABeanCategoryAssignmentName(row.getCell(AExcelStatIO.TRANSITION_COLUMN_TRANSITION_FROM).toString(), states);
				transition.setStateFrom((states.get(check)));
				check = ExcelImportHelper.containsABeanCategoryAssignmentName(row.getCell(AExcelStatIO.TRANSITION_COLUMN_TRANSITION_TO).toString(), states);
				transition.setStateTo(states.get(check));
				transitions.add(transition);
				// creation is done, continue the import with the next row in excel
			} else {
				// Control the delete column if element is deleted move to the next row
				int check = ExcelImportHelper.containsABeanCategoryAssignmentUUID(tempUUID, transitions);
				String tempDelete = Objects.toString(row.getCell(AExcelStatIO.COMMON_COLUMN_DELETE), "");
				if (tempDelete.toString().contains(AExcelStatIO.COMMON_DELETEMARK_VALUE)) {
					transitions.remove(check);
				} else {
					// Change the Transition name by controlling if it is empty or not
					String tempName = Objects.toString(row.getCell(AExcelStatIO.TRANSITION_COLUMN_TRANSITION_NAME), "");
					transitions.get(check).setName(tempName);
					// Change the From State 
					String tempStateFrom = Objects.toString(row.getCell(AExcelStatIO.TRANSITION_COLUMN_TRANSITION_FROM), "");
					int check2 = ExcelImportHelper.containsABeanCategoryAssignmentName(tempStateFrom, states);
					transitions.get(check).setStateFrom(states.get(check2));
					// Change the To State
					String tempInterfaceTo = Objects.toString(row.getCell(AExcelStatIO.TRANSITION_COLUMN_TRANSITION_TO), "");
					check2 = ExcelImportHelper.containsABeanCategoryAssignmentName(tempInterfaceTo, states);
					transitions.get(check).setStateTo(states.get(check2));
				}
			}
		}
	}

	/**
	 * Acts like a constructor
	 * @param eObject Object to import
	 * @param repository the repository
	 * @param wb XSSFWorkbook
	 */
	private void init(EObject eObject, Repository repository, XSSFWorkbook wb) {
		if (eObject instanceof CategoryAssignment) {
			CategoryAssignment ca = (CategoryAssignment) eObject;
			this.stateMaschine = new StateMachine(ca);
			this.wb = wb;
			this.concept = stateMaschine.getConcept();
		}
	}
	
	/**
	 * Imports the states
	 */
	private void importStates() {
		final Sheet sheet = wb.getSheet(AExcelStatIO.TEMPLATE_SHEETNAME_STATES);
		if (sheet == null) {
			return;
		}
		// go through each row to find out what to do
		for (int i = AExcelStatIO.COMMON_ROW_START_TABLE; i < sheet.getLastRowNum(); i++) {
			if (ExcelImportHelper.isEmpty(i, sheet, AExcelStatIO.STATE_COLUMN_STATE_NAME + 1)) {
				continue;
			}
			Row row = sheet.getRow(i);
			List<State> states = stateMaschine.getStates();
			// Get the UUID of the first state
			String tempUUID = Objects.toString(row.getCell(AExcelStatIO.COMMON_COLUMN_UUID), "");
			// figure out if we are creating a new State
			if ("".equals(tempUUID)) {
				State state = new State(concept);
				// change the name if it is not empty , if it is empty throw a fault
				state.setName(row.getCell(AExcelStatIO.STATE_COLUMN_STATE_NAME).toString());
				states.add(state);
			} else {
				int check = ExcelImportHelper.containsABeanCategoryAssignmentUUID(tempUUID, states);
				// Control the delete column if element is deleted move to the next row
				String tempDelete = Objects.toString(row.getCell(AExcelStatIO.COMMON_COLUMN_DELETE), "");
				if (tempDelete.contains(AExcelStatIO.COMMON_DELETEMARK_VALUE)) {
					states.remove(check);
				} else {
					// change the name if it is not empty , if it is empty throw a fault
					String tempStateName = Objects.toString(row.getCell(AExcelStatIO.STATE_COLUMN_STATE_NAME).toString(), "");
					states.get(check).setName(tempStateName);
				}
			}
		}
	}

	@Override
	public boolean canImport(EObject object) {
		return object instanceof CategoryAssignment;
	}

	@Override
	public List<Fault> validate(EObject object, XSSFWorkbook wb) {
		ImportValidator iValidator = new ImportValidator(object, wb);
		return iValidator.validate();
	}
}
