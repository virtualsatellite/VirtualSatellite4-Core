/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.excel.importer;

import java.util.List;
import java.util.Objects;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import de.dlr.sc.virsat.model.concept.types.category.ABeanCategoryAssignment;

/**
 * Class for common code to import excel
 */
public class ExcelImportHelper {

	/**
	* Checks the row, if all of the fields are empty or not
	* @param rowNumber the row number which we are checking
	* @param sheet the excel data sheet
	* @param cellCount expected cell number in each row
	* @return if the row is empty true, else false
	*/
	public static boolean isEmpty(int rowNumber, Sheet sheet, int cellCount) {
		Row row = sheet.getRow(rowNumber);
		if (row == null) {
			return true;
		}
		for (int i = 0; i < cellCount; i++) {
			String cell = Objects.toString(row.getCell(i), "");
			if (!("".equals(cell) || row.getCell(i) == null)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * helper for importing
	 */
	private ExcelImportHelper() {
	}

	/**
	* Searches the given UUID in the list to know if it is contained the list
	* @param tempUUID given UUID
	* @param aBeanCategoryAssignmentList list to be searched for
	* @return the index of the given UUID if it is contained in the list. Returns -1 if it does not contained in the list
	*/
	public static int containsABeanCategoryAssignmentUUID(String tempUUID, List<? extends ABeanCategoryAssignment> aBeanCategoryAssignmentList) {
		for (int i = 0; i < aBeanCategoryAssignmentList.size(); i++) {
			if (aBeanCategoryAssignmentList.get(i).getUuid().equals(tempUUID)) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * Searches the given fullQualifiedInstanceName in the given list
	 * @param fullQualifiedInstanceName given fullQualifiedInstanceName
	 * @param aBeanCategoryAssignmentList list to be searched
	 * @return the index of the given fullQualifiedInstanceName if it is contained in the List. Returns -1 if it does not contained in the List
	 */
	public static int containsABeanCategoryAssignmentFullQualifiedInstanceName(String fullQualifiedInstanceName, List<? extends ABeanCategoryAssignment> aBeanCategoryAssignmentList) {
		for (int i = 0; i < aBeanCategoryAssignmentList.size(); i++) {
			if (aBeanCategoryAssignmentList.get(i).getTypeInstance().getFullQualifiedInstanceName().equals(fullQualifiedInstanceName)) {
				return i;
			}
		}
		return -1;
	}

	/**
	* Searches the given name in the list to know if it is contained the list
	* @param name given name
	* @param aBeanCategoryAssignmentList list to be searched for
	* @return the index of the given UUID if it is contained in the list. Returns -1 if it does not contained in the list
	*/
	public static int containsABeanCategoryAssignmentName(String name, List<? extends ABeanCategoryAssignment> aBeanCategoryAssignmentList) {
		for (int i = 0; i < aBeanCategoryAssignmentList.size(); i++) {
			if (aBeanCategoryAssignmentList.get(i).getName().equals(name)) {
				return i;
			}
		}
		return -1;
	}
}
