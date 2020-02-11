/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/

package de.dlr.sc.virsat.model.extension.funcelectrical.excel.importer;

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
import de.dlr.sc.virsat.model.dvlm.types.impl.VirSatUuid;
import de.dlr.sc.virsat.model.extension.funcelectrical.Activator;
import de.dlr.sc.virsat.model.extension.funcelectrical.excel.AExcelFuncIO;
import de.dlr.sc.virsat.model.extension.funcelectrical.test.ExcelTestCase;

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
	public void testValidateInterfaceEndSheet() throws IOException {
		InputStream iStream = Activator.getResourceContentAsString("/resources/ElementDefinitionTest.xlsx");
		XSSFWorkbook wb = new XSSFWorkbook(iStream);

		int headerSheetIndex = wb.getSheetIndex(AExcelFuncIO.TEMPLATE_SHEETNAME_HEADER);
		int interfaceEndSheetIndex = wb.getSheetIndex(AExcelFuncIO.TEMPLATE_SHEETNAME_INTERFACEENDS);

		ArrayList<Fault> expectedFaults = new ArrayList<Fault>();
		expectedFaults.add(new Fault(FaultType.STRUCTURAL_ELEMENT_UUIDS_DO_NOT_MATCH, headerSheetIndex, AExcelFuncIO.COMMON_ROW_START_TABLE));
		expectedFaults.add(new Fault(FaultType.STRUCTURAL_ELEMENT_NAMES_DO_NOT_MATCH, headerSheetIndex, AExcelFuncIO.COMMON_ROW_START_TABLE + 1));
		expectedFaults.add(new Fault(FuncFaultType.INTERFACE_END_UUID_NOT_FOUND, interfaceEndSheetIndex, AExcelFuncIO.COMMON_ROW_START_TABLE));
		expectedFaults.add(new Fault(FuncFaultType.INTERFACE_TYPE_DOES_NOT_EXIST, interfaceEndSheetIndex, AExcelFuncIO.COMMON_ROW_START_TABLE + 1));
		expectedFaults.add(new Fault(FaultType.DELETE_COLUMN_CAN_BE_EMPTY_OR_1, interfaceEndSheetIndex, AExcelFuncIO.COMMON_ROW_START_TABLE + 2));
		expectedFaults.add(new Fault(FuncFaultType.CANT_DELETE_NON_EXISTING_INTERFACE_END, interfaceEndSheetIndex, AExcelFuncIO.COMMON_ROW_START_TABLE + NUMBER_OF_INTERFACES));
		expectedFaults.add(new Fault(FuncFaultType.INTERFACE_END_NAME_IS_NOT_SET, interfaceEndSheetIndex, AExcelFuncIO.COMMON_ROW_START_TABLE + NUMBER_OF_INTERFACES + 1));

		ImportValidator iValidator = new ImportValidator(elementDef.getStructuralElementInstance(), wb);
		ArrayList<Fault> faults = (ArrayList<Fault>) iValidator.validate();
		assertEquals("Faults were correctly detected", expectedFaults, faults);
	}

	@Test
	public void testValidateEmptyInterfaceTypeSheet() throws IOException {
		InputStream iStream = Activator.getResourceContentAsString("/resources/InterfaceTypeCollectionTest.xlsx");
		XSSFWorkbook wb = new XSSFWorkbook(iStream);

		int headerSheetIndex = wb.getSheetIndex(AExcelFuncIO.TEMPLATE_SHEETNAME_HEADER);
		int interfaceTypeSheetIndex = wb.getSheetIndex(AExcelFuncIO.TEMPLATE_SHEETNAME_INTERFACETYPES);

		ArrayList<Fault> expectedFault = new ArrayList<Fault>();
		expectedFault.add(new Fault(FaultType.STRUCTURAL_ELEMENT_UUIDS_DO_NOT_MATCH, headerSheetIndex, AExcelFuncIO.COMMON_ROW_START_TABLE));
		expectedFault.add(new Fault(FaultType.STRUCTURAL_ELEMENT_NAMES_DO_NOT_MATCH, headerSheetIndex, AExcelFuncIO.COMMON_ROW_START_TABLE + 1));
		expectedFault.add(new Fault(FuncFaultType.INTERFACE_TYPE_UUID_NOT_FOUND, interfaceTypeSheetIndex, AExcelFuncIO.COMMON_ROW_START_TABLE));
		expectedFault.add(new Fault(FaultType.DELETE_COLUMN_CAN_BE_EMPTY_OR_1, interfaceTypeSheetIndex, AExcelFuncIO.COMMON_ROW_START_TABLE + 1));
		expectedFault.add(new Fault(FuncFaultType.CANT_DELETE_NON_EXISTING_INTERFACE_TYPE, interfaceTypeSheetIndex, AExcelFuncIO.COMMON_ROW_START_TABLE + NUMBER_OF_INTERFACES));
		expectedFault.add(new Fault(FuncFaultType.INTERFACE_TYPE_NAME_IS_NOT_SET, interfaceTypeSheetIndex, AExcelFuncIO.COMMON_ROW_START_TABLE + NUMBER_OF_INTERFACES));
		ImportValidator iValidator = new ImportValidator(ifaceTypeCollection.getStructuralElementInstance(), wb);
		ArrayList<Fault> fault = (ArrayList<Fault>) iValidator.validate();
		assertEquals(expectedFault, fault);
	}

	@Test
	public void testValidateInterfaceTypeSheet() throws IOException { 
		// Set a mismatching UUID
		elementConf.getStructuralElementInstance().setUuid(new VirSatUuid("2c325de4-0000-467c-8867-bdddda7723f6"));

		InputStream iStream = Activator.getResourceContentAsString("/resources/ElementConfigurationTest.xlsx");
		XSSFWorkbook wb = new XSSFWorkbook(iStream);

		int headerSheetIndex = wb.getSheetIndex(AExcelFuncIO.TEMPLATE_SHEETNAME_HEADER);
		int interfaceTypeSheetIndex = wb.getSheetIndex(AExcelFuncIO.TEMPLATE_SHEETNAME_INTERFACES);

		ArrayList<Fault> expectedFault = new ArrayList<Fault>();
		expectedFault.add(new Fault(FaultType.STRUCTURAL_ELEMENT_UUIDS_DO_NOT_MATCH, headerSheetIndex, AExcelFuncIO.COMMON_ROW_START_TABLE));
		expectedFault.add(new Fault(FuncFaultType.INTERFACE_UUID_NOT_FOUND, interfaceTypeSheetIndex, AExcelFuncIO.COMMON_ROW_START_TABLE));

		expectedFault.add(new Fault(FaultType.DELETE_COLUMN_CAN_BE_EMPTY_OR_1, interfaceTypeSheetIndex, AExcelFuncIO.COMMON_ROW_START_TABLE));
		expectedFault.add(new Fault(FuncFaultType.CANT_DELETE_NON_EXISTING_INTERFACE, interfaceTypeSheetIndex, AExcelFuncIO.COMMON_ROW_START_TABLE + 1));
		expectedFault.add(new Fault(FuncFaultType.INTERFACE_NAME_IS_NOT_SET, interfaceTypeSheetIndex, AExcelFuncIO.COMMON_ROW_START_TABLE + 1));
		expectedFault.add(new Fault(FuncFaultType.FROM_INTERFACE_END_NOT_FOUND, interfaceTypeSheetIndex, AExcelFuncIO.COMMON_ROW_START_TABLE + 1));
		expectedFault.add(new Fault(FuncFaultType.TO_INTERFACE_END_NOT_FOUND, interfaceTypeSheetIndex, AExcelFuncIO.COMMON_ROW_START_TABLE + 1));
		ImportValidator iValidator = new ImportValidator(elementConf.getStructuralElementInstance(), wb);

		ArrayList<Fault> fault = (ArrayList<Fault>) iValidator.validate();
		assertEquals(expectedFault, fault);
	}
}
