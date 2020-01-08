/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/

package de.dlr.sc.virsat.model.extension.funcelectrical.excel.exporter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

import de.dlr.sc.virsat.excel.AExcelIo;
import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import de.dlr.sc.virsat.model.extension.funcelectrical.excel.exporter.FuncElecExporter;
import de.dlr.sc.virsat.model.extension.funcelectrical.test.ExcelTestCase;
import de.dlr.sc.virsat.model.extension.funcelectrical.test.TestActivator;


/**
 * Test Case for Exporting to Excel
 * @author bell_er
 *
 */
public class FuncElecExporterTest extends ExcelTestCase {
	
	@Test
	public void canExport()  { 	
		FuncElecExporter fe = new FuncElecExporter();
		assertEquals(true, fe.canExport(ec.getStructuralElementInstance()));
	}
	
	@Test
	public void testExportTypes()  { 		
		StructuralElementInstance sei = itc.getStructuralElementInstance();
		FuncElecExporter fe = new FuncElecExporter();
		fe.export(itc.getStructuralElementInstance(), System.getProperty("java.io.tmpdir"), true, "");
		XSSFWorkbook wb = fe.getWb();

		Sheet sheet = wb.getSheet(AExcelIo.TEMPLATE_SHEETNAME_INTERFACETYPES);

		for (int i = 0; i < sei.getCategoryAssignments().size(); ++i) {
			CategoryAssignment interfaceType = sei.getCategoryAssignments().get(i);
			Cell cell = sheet.getRow(AExcelIo.COMMON_ROW_START_TABLE + i).getCell(AExcelIo.INTERFACETYPES_COLUMN_INTERFACETYPE_NAME);
			assertEquals("Type " + i + "exported correctly", interfaceType.getName(), cell.toString());
		}
	}
	
	@Test
	public void testExportInterfaceEnds() throws IOException  { 		
		InputStream is = TestActivator.getResourceContentAsString("/resources/SampleTest.xlsx");
		StructuralElementInstance sei = ed.getStructuralElementInstance();
		
		FuncElecExporter fe = new FuncElecExporter();
		fe.export(sei, is);
		Workbook wb = fe.getWb();

		Sheet sheet = wb.getSheet(AExcelIo.TEMPLATE_SHEETNAME_INTERFACEENDS);
		for (int i = 0; i < sei.getCategoryAssignments().size(); ++i) {
			Cell cell =  sheet.getRow(AExcelIo.COMMON_ROW_START_TABLE + i).getCell(AExcelIo.INTERFACEEND_COLUMN_INTERFACEEND_NAME);
			assertEquals("Interface end exported correctly", sei.getCategoryAssignments().get(i).getName(), cell.toString());
		}
		Row row = sheet.getRow(AExcelIo.COMMON_ROW_START_TABLE + sei.getCategoryAssignments().size());
		assertNull("Line after alle entries correctly empty", row);
	}
	
	@Test
	public void testExportInterfaces() throws IOException  { 	
		InputStream is = TestActivator.getResourceContentAsString("/resources/SampleTest.xlsx");
		
		StructuralElementInstance sei = ec.getStructuralElementInstance();
		
		FuncElecExporter fe = new FuncElecExporter();
		fe.export(sei, is);
		Workbook wb = fe.getWb();
			
		Sheet sheet = wb.getSheet(AExcelIo.TEMPLATE_SHEETNAME_INTERFACES);
		for (int i = 0; i < sei.getCategoryAssignments().size(); ++i) {
			Cell cell =  sheet.getRow(AExcelIo.COMMON_ROW_START_TABLE + i).getCell(AExcelIo.INTERFACE_COLUMN_INTERFACE_NAME);
			assertEquals("Interface exported correctly", sei.getCategoryAssignments().get(i).getName(), cell.toString());
		}
		assertNull("Line after all entries correctly empty", sheet.getRow(AExcelIo.COMMON_ROW_START_TABLE + sei.getCategoryAssignments().size()));
	}
	
	@Test
	public void testExportTypesIntoEmptyWorkbook() throws IOException  { 		
		InputStream is = TestActivator.getResourceContentAsString("/resources/SampleTestWithoutPages.xlsx");
		
		StructuralElementInstance sei = itc.getStructuralElementInstance();
	
		FuncElecExporter fe = new FuncElecExporter();
		fe.export(sei, is);
		Workbook wb = fe.getWb();
			
		Sheet sheet = wb.getSheet(AExcelIo.TEMPLATE_SHEETNAME_INTERFACETYPES);
		
		for (int i = 0; i < sei.getCategoryAssignments().size(); ++i) {
			CategoryAssignment interfaceType = sei.getCategoryAssignments().get(i);
			Cell cell = sheet.getRow(AExcelIo.COMMON_ROW_START_TABLE + i).getCell(AExcelIo.INTERFACETYPES_COLUMN_INTERFACETYPE_NAME);
			assertEquals("Type " + i + "exported correctly", interfaceType.getName(), cell.toString());
		}
		assertNull("Line after all entries correctly empty", sheet.getRow(AExcelIo.COMMON_ROW_START_TABLE + sei.getCategoryAssignments().size()));
	}
	
	@Test
	public void testExportInterfacesIntoEmptyWorkbook() throws IOException  { 
		InputStream is = TestActivator.getResourceContentAsString("/resources/SampleTestWithoutPages.xlsx");
		
		StructuralElementInstance sei = ed.getStructuralElementInstance();
		
		FuncElecExporter fe = new FuncElecExporter();
		fe.export(sei, is);
		Workbook wb = fe.getWb();

		Sheet sheet = wb.getSheet(AExcelIo.TEMPLATE_SHEETNAME_INTERFACEENDS);
		for (int i = 0; i < sei.getCategoryAssignments().size(); ++i) {
			Cell cell =  sheet.getRow(AExcelIo.COMMON_ROW_START_TABLE + i).getCell(AExcelIo.INTERFACEEND_COLUMN_INTERFACEEND_NAME);
			assertEquals("Interface end exported correctly", sei.getCategoryAssignments().get(i).getName(), cell.toString());
		}
		assertNull("Line after all entries correctly empty", sheet.getRow(AExcelIo.COMMON_ROW_START_TABLE + sei.getCategoryAssignments().size()));
	}
	
	@Test
	public void testExportInterfaceEndsIntoEmptyWorkbook() throws IOException  { 
		InputStream is = TestActivator.getResourceContentAsString("/resources/SampleTestWithoutPages.xlsx");
		
		StructuralElementInstance sei = ec2.getStructuralElementInstance();
		
		FuncElecExporter fe = new FuncElecExporter();
		fe.export(sei, is);
		Workbook wb = fe.getWb();

		Sheet sheet = wb.getSheet(AExcelIo.TEMPLATE_SHEETNAME_INTERFACEENDS);
		for (int i = 0; i < sei.getCategoryAssignments().size(); ++i) {
			Cell cell =  sheet.getRow(AExcelIo.COMMON_ROW_START_TABLE + i).getCell(AExcelIo.INTERFACEEND_COLUMN_INTERFACEEND_NAME);
			assertEquals("Interface end exported correctly", sei.getCategoryAssignments().get(i).getName(), cell.toString());
		}
		assertNull("Line after all entries correctly empty", sheet.getRow(AExcelIo.COMMON_ROW_START_TABLE + sei.getCategoryAssignments().size()));
	}
}