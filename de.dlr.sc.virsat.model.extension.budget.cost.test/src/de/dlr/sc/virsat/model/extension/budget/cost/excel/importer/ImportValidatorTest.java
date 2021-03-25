/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/

package de.dlr.sc.virsat.model.extension.budget.cost.excel.importer;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.eclipse.core.runtime.CoreException;
import org.junit.Before;
import org.junit.Test;

import de.dlr.sc.virsat.excel.fault.Fault;
import de.dlr.sc.virsat.excel.fault.FaultType;
import de.dlr.sc.virsat.model.extension.budget.cost.activator.Activator;
import de.dlr.sc.virsat.model.extension.budget.cost.excel.AExcelCostIO;
import de.dlr.sc.virsat.model.extension.budget.cost.test.ExcelTestCase;

/**
 * Test Case for Importing from Excel
 */
public class ImportValidatorTest extends ExcelTestCase {

	@Before
	public void setUp() throws CoreException {
		super.setUp();
		addElementsToRepository();
	}

	@Test
	public void testValidateCostEquipmentSheet() throws IOException {
		InputStream iStream = Activator.getResourceContentAsString("/resources/ElementDefinitionTest.xlsx");
		XSSFWorkbook wb = new XSSFWorkbook(iStream);

		int headerSheetIndex = wb.getSheetIndex(AExcelCostIO.TEMPLATE_SHEETNAME_HEADER);
		int costEquipmentSheetIndex = wb.getSheetIndex(AExcelCostIO.TEMPLATE_SHEETNAME_COSTEQUIPMENTS);

		ArrayList<Fault> expectedFaults = new ArrayList<Fault>();
		expectedFaults.add(new Fault(FaultType.STRUCTURAL_ELEMENT_UUIDS_DO_NOT_MATCH, headerSheetIndex, AExcelCostIO.COMMON_ROW_START_TABLE));
		expectedFaults.add(new Fault(FaultType.STRUCTURAL_ELEMENT_NAMES_DO_NOT_MATCH, headerSheetIndex, AExcelCostIO.COMMON_ROW_START_TABLE + 1));
		expectedFaults.add(new Fault(CostFaultType.COST_EQUIPMENT_UUID_NOT_FOUND, costEquipmentSheetIndex, AExcelCostIO.COMMON_ROW_START_TABLE));
		expectedFaults.add(new Fault(CostFaultType.COST_TYPE_DOES_NOT_EXIST, costEquipmentSheetIndex, AExcelCostIO.COMMON_ROW_START_TABLE + 1));
		expectedFaults.add(new Fault(FaultType.DELETE_COLUMN_CAN_BE_EMPTY_OR_X, costEquipmentSheetIndex, AExcelCostIO.COMMON_ROW_START_TABLE + 2));
		expectedFaults.add(new Fault(CostFaultType.CANT_DELETE_NON_EXISTING_COST_EQUIPMENT, costEquipmentSheetIndex, AExcelCostIO.COMMON_ROW_START_TABLE + NUMBER_OF_COST_TYPES));
		expectedFaults.add(new Fault(CostFaultType.COST_EQUIPMENT_NAME_IS_NOT_SET, costEquipmentSheetIndex, AExcelCostIO.COMMON_ROW_START_TABLE + NUMBER_OF_COST_TYPES + 1));

		ImportValidator iValidator = new ImportValidator(elementDef.getStructuralElementInstance(), wb);
		ArrayList<Fault> faults = (ArrayList<Fault>) iValidator.validate();
		assertEquals("Faults were correctly detected", expectedFaults, faults);
		
	}

	@Test
	public void testValidateEmptyCostTypeSheet() throws IOException {
		InputStream iStream = Activator.getResourceContentAsString("/resources/CostTypeCollectionTest.xlsx");
		XSSFWorkbook wb = new XSSFWorkbook(iStream);

		int headerSheetIndex = wb.getSheetIndex(AExcelCostIO.TEMPLATE_SHEETNAME_HEADER);
		int interfaceTypeSheetIndex = wb.getSheetIndex(AExcelCostIO.TEMPLATE_SHEETNAME_COSTTYPES);

		ArrayList<Fault> expectedFault = new ArrayList<Fault>();
		expectedFault.add(new Fault(FaultType.STRUCTURAL_ELEMENT_UUIDS_DO_NOT_MATCH, headerSheetIndex, AExcelCostIO.COMMON_ROW_START_TABLE));
		expectedFault.add(new Fault(FaultType.STRUCTURAL_ELEMENT_NAMES_DO_NOT_MATCH, headerSheetIndex, AExcelCostIO.COMMON_ROW_START_TABLE + 1));
		expectedFault.add(new Fault(CostFaultType.COST_TYPE_UUID_NOT_FOUND, interfaceTypeSheetIndex, AExcelCostIO.COMMON_ROW_START_TABLE));
		expectedFault.add(new Fault(FaultType.DELETE_COLUMN_CAN_BE_EMPTY_OR_X, interfaceTypeSheetIndex, AExcelCostIO.COMMON_ROW_START_TABLE + 1));
		expectedFault.add(new Fault(CostFaultType.CANT_DELETE_NON_EXISTING_COST_TYPE, interfaceTypeSheetIndex, AExcelCostIO.COMMON_ROW_START_TABLE + NUMBER_OF_COST_TYPES));
		expectedFault.add(new Fault(CostFaultType.COST_TYPE_NAME_IS_NOT_SET, interfaceTypeSheetIndex, AExcelCostIO.COMMON_ROW_START_TABLE + NUMBER_OF_COST_TYPES));
		ImportValidator iValidator = new ImportValidator(costTypesCollection.getStructuralElementInstance(), wb);
		ArrayList<Fault> fault = (ArrayList<Fault>) iValidator.validate();
		assertEquals(expectedFault, fault);
	}
}
