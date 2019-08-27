/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.statemachines.excelImport;

import java.util.List;
import java.util.Objects;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.eclipse.emf.ecore.EObject;

import de.dlr.sc.virsat.excel.AExcelIo;
import de.dlr.sc.virsat.excel.ExcelImportHelper;
import de.dlr.sc.virsat.excel.Fault;
import de.dlr.sc.virsat.excel.IImport;
import de.dlr.sc.virsat.model.dvlm.Repository;
import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.extension.statemachines.model.State;
import de.dlr.sc.virsat.model.extension.statemachines.model.StateMachine;
import de.dlr.sc.virsat.model.extension.statemachines.model.Transition;




/**
 * Class for Importing Excel files.
 * 
 * @author bell_er
 *
 */
public class SMImporter implements IImport {
	private XSSFWorkbook wb;
	private StateMachine sm;
	private Concept concept;
	
	@Override
	public void importExcel(EObject eObject, Repository repository, XSSFWorkbook wb) {
		init(eObject, repository, wb);
		importStates();
		importTransitions();
	}
	
	/**
	 * imports the transitions 
	 * 
	 * @author  Bell_er
	 */	private void importTransitions() {
		final Sheet sheet = wb.getSheet(AExcelIo.TEMPLATE_SHEETNAME_TRANSITIONS);

		if (sheet == null) {
			return;
		}
		// go through each row to find out what to do
		
		for (int i = AExcelIo.COMMON_ROW_START_TABLE; i < sheet.getLastRowNum(); i++) {
			if (ExcelImportHelper.isEmpty(i, sheet, AExcelIo.TRANSITION_COLUMN_TRANSITION_TO + 1)) {
				continue;
			}
			Row row = sheet.getRow(i);
			List<State> states = sm.getStates();
			List<Transition> transitions = sm.getTransitions();
			// Get the UUID of the first transition
			String tempUUID = Objects.toString(row.getCell(AExcelIo.COMMON_COLUMN_UUID), "");
			// figure out if we are creating a new Transition

			if ("".equals(tempUUID)) {
				//create new transition and add it to our State Machine
				Transition t = new Transition(concept);
				t.setName(row.getCell(AExcelIo.TRANSITION_COLUMN_TRANSITION_NAME).toString());
				int check = ExcelImportHelper.containsABeanCategoryAssignmentName(row.getCell(AExcelIo.TRANSITION_COLUMN_TRANSITION_FROM).toString(), states);
				t.setStateFrom((states.get(check)));
				check = ExcelImportHelper.containsABeanCategoryAssignmentName(row.getCell(AExcelIo.TRANSITION_COLUMN_TRANSITION_TO).toString(), states);
				t.setStateTo(states.get(check));
				transitions.add(t);
				// creation is done, continue the import with the next row in excel
			} else {
				// Control the delete column if element is deleted move to the next row
				int check = ExcelImportHelper.containsABeanCategoryAssignmentUUID(tempUUID, transitions);
				String tempDelete = Objects.toString(row.getCell(AExcelIo.COMMON_COLUMN_DELETE), "");
				if (tempDelete.toString().contains(AExcelIo.COMMON_DELETEMARK_VALUE)) {
					transitions.remove(check);
				} else {
					// Change the Transition name by controlling if it is empty or not
					String tempName = Objects.toString(row.getCell(AExcelIo.TRANSITION_COLUMN_TRANSITION_NAME), "");
					transitions.get(check).setName(tempName);
					// Change the From  State 
					String tempStateFrom = Objects.toString(row.getCell(AExcelIo.TRANSITION_COLUMN_TRANSITION_FROM), "");
					int check2 = ExcelImportHelper.containsABeanCategoryAssignmentName(tempStateFrom, states);
					transitions.get(check).setStateFrom(states.get(check2));
					// Change the To State 
					String tempInterfaceTo = Objects.toString(row.getCell(AExcelIo.TRANSITION_COLUMN_TRANSITION_TO), "");
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
	 *
	 */
	private void init(EObject eObject, Repository repository, XSSFWorkbook wb) {	
		if (eObject instanceof CategoryAssignment) {
			CategoryAssignment ca = (CategoryAssignment) eObject;
			this.sm = new StateMachine(ca);
			this.wb = wb;
			this.concept = sm.getConcept();
		}

	}
	
	/**
	 *Imports the states
	 */
	private void importStates() {
		final Sheet sheet = wb.getSheet(AExcelIo.TEMPLATE_SHEETNAME_STATES);
		if (sheet == null) {
			return;
		}
		// go through each row to find out what to do
		for (int i = AExcelIo.COMMON_ROW_START_TABLE; i < sheet.getLastRowNum(); i++) {
			if (ExcelImportHelper.isEmpty(i, sheet, AExcelIo.STATE_COLUMN_STATE_NAME + 1)) {
				continue;
			}
			Row row = sheet.getRow(i);
			List<State> states = sm.getStates();
			// Get the UUID of the first state
			String tempUUID =   Objects.toString(row.getCell(AExcelIo.COMMON_COLUMN_UUID), "");
			// figure out if we are creating a new State
			if ("".equals(tempUUID)) {
				State s = new State(concept);
				// change the name if it is not empty , if it is empty throw a fault
				s.setName(row.getCell(AExcelIo.STATE_COLUMN_STATE_NAME).toString());						
				states.add(s);
			} else {
				int check = ExcelImportHelper.containsABeanCategoryAssignmentUUID(tempUUID, states);
				// Control the delete column if element is deleted move to the next row
				String tempDelete = Objects.toString(row.getCell(AExcelIo.COMMON_COLUMN_DELETE), "");
				if (tempDelete.contains(AExcelIo.COMMON_DELETEMARK_VALUE)) {
					states.remove(check);
				}	else {
					// change the name if it is not empty , if it is empty throw a fault
					String tempStateName =  Objects.toString(row.getCell(AExcelIo.STATE_COLUMN_STATE_NAME).toString(), "");				
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
		ImportValidator iv = new ImportValidator(object, wb);
		return iv.validate();
	}
}
