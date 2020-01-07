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

import de.dlr.sc.virsat.excel.AExcelIo;
import de.dlr.sc.virsat.excel.fault.Fault;
import de.dlr.sc.virsat.excel.fault.FaultType;
import de.dlr.sc.virsat.model.dvlm.types.impl.VirSatUuid;
import de.dlr.sc.virsat.model.extension.funcelectrical.Activator;
import de.dlr.sc.virsat.model.extension.funcelectrical.excelImport.ImportValidator;
import de.dlr.sc.virsat.model.extension.funcelectrical.test.ExcelTestCase;




/**
 * Test Case for Importing from Excel
 * @author bell_er
 *
 */

public class ImportValidatorTest extends ExcelTestCase {
	
	@Before
	public void setUp() throws CoreException {
		super.setUp();
		addElementsToRepository();
	}
	
	@Test
	public void testValidateInterfaceEndSheet() throws IOException  { 
		InputStream is = Activator.getResourceContentAsString("/resources/ElementDefinitionTest.xlsx");
		XSSFWorkbook wb = new XSSFWorkbook(is);
			
		int headerSheetIndex = wb.getSheetIndex(AExcelIo.TEMPLATE_SHEETNAME_HEADER);
		int interfaceEndSheetIndex = wb.getSheetIndex(AExcelIo.TEMPLATE_SHEETNAME_INTERFACEENDS);
		
		ArrayList<Fault> expectedFaults = new ArrayList<Fault>();
		expectedFaults.add(new Fault(FaultType.STRUCTURAL_ELEMENT_UUIDS_DO_NOT_MATCH, headerSheetIndex, AExcelIo.COMMON_ROW_START_TABLE));
		expectedFaults.add(new Fault(FaultType.STRUCTURAL_ELEMENT_NAMES_DO_NOT_MATCH, headerSheetIndex, AExcelIo.COMMON_ROW_START_TABLE + 1));
		expectedFaults.add(new Fault(FaultType.INTERFACE_END_UUID_NOT_FOUND, interfaceEndSheetIndex, AExcelIo.COMMON_ROW_START_TABLE));
		expectedFaults.add(new Fault(FaultType.INTERFACE_TYPE_DOES_NOT_EXIST, interfaceEndSheetIndex, AExcelIo.COMMON_ROW_START_TABLE + 1));
		expectedFaults.add(new Fault(FaultType.DELETE_COLUMN_CAN_BE_EMPTY_OR_1, interfaceEndSheetIndex, AExcelIo.COMMON_ROW_START_TABLE + 2));
		expectedFaults.add(new Fault(FaultType.CANT_DELETE_NON_EXISTING_INTERFACE_END, interfaceEndSheetIndex, AExcelIo.COMMON_ROW_START_TABLE + THREE));
		expectedFaults.add(new Fault(FaultType.INTERFACE_END_NAME_IS_NOT_SET, interfaceEndSheetIndex, AExcelIo.COMMON_ROW_START_TABLE + FOUR));
					
		ImportValidator iv = new ImportValidator(ed.getStructuralElementInstance(), wb);
		ArrayList<Fault> faults = (ArrayList<Fault>) iv.validate();	
		assertEquals("Faults were correctly detected", expectedFaults, faults);
	}
	
	@Test
	public void testValidateEmptyInterfaceTypeSheet() throws IOException  { 
		InputStream is = Activator.getResourceContentAsString("/resources/InterfaceTypeCollectionTest.xlsx");
		XSSFWorkbook wb = new XSSFWorkbook(is);
		
		int headerSheetIndex = wb.getSheetIndex(AExcelIo.TEMPLATE_SHEETNAME_HEADER);
		int interfaceTypeSheetIndex = wb.getSheetIndex(AExcelIo.TEMPLATE_SHEETNAME_INTERFACETYPES);
		
		ArrayList<Fault> expectedFault = new ArrayList<Fault>();
		expectedFault.add(new Fault(FaultType.STRUCTURAL_ELEMENT_UUIDS_DO_NOT_MATCH, headerSheetIndex, AExcelIo.COMMON_ROW_START_TABLE));
		expectedFault.add(new Fault(FaultType.STRUCTURAL_ELEMENT_NAMES_DO_NOT_MATCH, headerSheetIndex, AExcelIo.COMMON_ROW_START_TABLE + 1));
		expectedFault.add(new Fault(FaultType.INTERFACE_TYPE_UUID_NOT_FOUND, interfaceTypeSheetIndex, AExcelIo.COMMON_ROW_START_TABLE));
		expectedFault.add(new Fault(FaultType.DELETE_COLUMN_CAN_BE_EMPTY_OR_1, interfaceTypeSheetIndex, AExcelIo.COMMON_ROW_START_TABLE + 1));
		expectedFault.add(new Fault(FaultType.CANT_DELETE_NON_EXISTING_INTERFACE_TYPE, interfaceTypeSheetIndex, AExcelIo.COMMON_ROW_START_TABLE + THREE));
		expectedFault.add(new Fault(FaultType.INTERFACE_TYPE_NAME_IS_NOT_SET, interfaceTypeSheetIndex, AExcelIo.COMMON_ROW_START_TABLE + THREE));
		ImportValidator iv = new ImportValidator(itc.getStructuralElementInstance(), wb);
		ArrayList<Fault> fault = (ArrayList<Fault>) iv.validate();
		assertEquals(expectedFault, fault);
	}
	
	@Test
	public void testValidateInterfaceTypeSheet() throws IOException  { 
		// Set a missmatching UUID
		ec.getStructuralElementInstance().setUuid(new VirSatUuid("2c325de4-0000-467c-8867-bdddda7723f6"));
		
		InputStream is = Activator.getResourceContentAsString("/resources/ElementConfigurationTest.xlsx");
		XSSFWorkbook wb = new XSSFWorkbook(is);
		
		int headerSheetIndex = wb.getSheetIndex(AExcelIo.TEMPLATE_SHEETNAME_HEADER);
		int interfaceTypeSheetIndex = wb.getSheetIndex(AExcelIo.TEMPLATE_SHEETNAME_INTERFACES);
		
		ArrayList<Fault> expectedFault = new ArrayList<Fault>();
		expectedFault.add(new Fault(FaultType.STRUCTURAL_ELEMENT_UUIDS_DO_NOT_MATCH, headerSheetIndex, AExcelIo.COMMON_ROW_START_TABLE));
		expectedFault.add(new Fault(FaultType.INTERFACE_UUID_NOT_FOUND, interfaceTypeSheetIndex, AExcelIo.COMMON_ROW_START_TABLE));
		
		expectedFault.add(new Fault(FaultType.DELETE_COLUMN_CAN_BE_EMPTY_OR_1, interfaceTypeSheetIndex, AExcelIo.COMMON_ROW_START_TABLE));
		expectedFault.add(new Fault(FaultType.CANT_DELETE_NON_EXISTING_INTERFACE, interfaceTypeSheetIndex, AExcelIo.COMMON_ROW_START_TABLE + 1));
		expectedFault.add(new Fault(FaultType.INTERFACE_NAME_IS_NOT_SET, interfaceTypeSheetIndex, AExcelIo.COMMON_ROW_START_TABLE + 1));
		expectedFault.add(new Fault(FaultType.FROM_INTERFACE_END_NOT_FOUND, interfaceTypeSheetIndex, AExcelIo.COMMON_ROW_START_TABLE + 1));
		expectedFault.add(new Fault(FaultType.TO_INTERFACE_END_NOT_FOUND, interfaceTypeSheetIndex, AExcelIo.COMMON_ROW_START_TABLE + 1));
		ImportValidator iv = new ImportValidator(ec.getStructuralElementInstance(), wb);
		
		ArrayList<Fault> fault = (ArrayList<Fault>) iv.validate();
		assertEquals(expectedFault, fault);
	}
	
}
