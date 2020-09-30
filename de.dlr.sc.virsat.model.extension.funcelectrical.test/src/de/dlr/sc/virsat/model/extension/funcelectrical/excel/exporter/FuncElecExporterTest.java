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
import java.time.LocalDateTime;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.eclipse.core.runtime.CoreException;
import org.junit.Test;

import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import de.dlr.sc.virsat.model.extension.funcelectrical.excel.AExcelFuncIO;
import de.dlr.sc.virsat.model.extension.funcelectrical.test.ExcelTestCase;
import de.dlr.sc.virsat.model.extension.funcelectrical.test.TestActivator;

/**
 * Test Case for Exporting to Excel
 */
public class FuncElecExporterTest extends ExcelTestCase {

	protected LocalDateTime localDateTime;
	
	@Override
	public void setUp() throws CoreException {
		super.setUp();
		//CHECKSTYLE:OFF
		localDateTime = LocalDateTime.of(2020, 04, 21, 12, 24);
		//CHECKSTYLE:ON
	}
	
	@Test
	public void canExport() {
		FuncElecExporter feExporter = new FuncElecExporter(localDateTime);
		assertEquals(true, feExporter.canExport(elementConf.getStructuralElementInstance()));
	}

	@Test
	public void testExportTypes() { 		
		StructuralElementInstance sei = ifaceTypeCollection.getStructuralElementInstance();
		FuncElecExporter feExporter = new FuncElecExporter(localDateTime);
		feExporter.export(ifaceTypeCollection.getStructuralElementInstance(), System.getProperty("java.io.tmpdir"), true, "");
		XSSFWorkbook wb = feExporter.getWb();

		Sheet sheet = wb.getSheet(AExcelFuncIO.TEMPLATE_SHEETNAME_INTERFACETYPES);
		for (int i = 0; i < sei.getCategoryAssignments().size(); ++i) {
			CategoryAssignment interfaceType = sei.getCategoryAssignments().get(i);
			Row row = sheet.getRow(AExcelFuncIO.COMMON_ROW_START_TABLE + i);
			Cell cell = row.getCell(AExcelFuncIO.INTERFACETYPES_COLUMN_INTERFACETYPE_NAME);
			assertEquals("Type " + i + "exported correctly", interfaceType.getName(), cell.toString());
		}
	}

	@Test
	public void testExportInterfaceEnds() throws IOException {
		InputStream iStream = TestActivator.getResourceContentAsString("/resources/SampleTest.xlsx");
		StructuralElementInstance sei = elementDef.getStructuralElementInstance();

		FuncElecExporter feExporter = new FuncElecExporter(localDateTime);
		feExporter.export(sei, iStream);
		Workbook wb = feExporter.getWb();

		Sheet sheet = wb.getSheet(AExcelFuncIO.TEMPLATE_SHEETNAME_INTERFACEENDS);
		for (int i = 0; i < sei.getCategoryAssignments().size(); ++i) {
			CategoryAssignment interfaceEnd = sei.getCategoryAssignments().get(i);
			Row row = sheet.getRow(AExcelFuncIO.COMMON_ROW_START_TABLE + i);
			Cell cell = row.getCell(AExcelFuncIO.INTERFACEEND_COLUMN_INTERFACEEND_NAME);
			assertEquals("Interface end name exported correctly", interfaceEnd.getName(), cell.toString());
			cell = row.getCell(AExcelFuncIO.INTERFACEEND_COLUMN_INTERFACEEND_FQN);
			assertEquals("Interface end fqn exported correctly", interfaceEnd.getFullQualifiedInstanceName(), cell.toString());
		}
		Row row = sheet.getRow(AExcelFuncIO.COMMON_ROW_START_TABLE + sei.getCategoryAssignments().size());
		assertNull("Line after alle entries correctly empty", row);
	}

	@Test
	public void testExportInterfaces() throws IOException {
		InputStream iStream = TestActivator.getResourceContentAsString("/resources/SampleTest.xlsx");

		StructuralElementInstance sei = elementConf.getStructuralElementInstance();

		FuncElecExporter feExporter = new FuncElecExporter(localDateTime);
		feExporter.export(sei, iStream);
		Workbook wb = feExporter.getWb();

		Sheet sheet = wb.getSheet(AExcelFuncIO.TEMPLATE_SHEETNAME_INTERFACES);
		for (int i = 0; i < sei.getCategoryAssignments().size(); ++i) {
			Row row = sheet.getRow(AExcelFuncIO.COMMON_ROW_START_TABLE + i);
			Cell cell = row.getCell(AExcelFuncIO.INTERFACE_COLUMN_INTERFACE_NAME);
			assertEquals("Interface exported correctly", sei.getCategoryAssignments().get(i).getName(), cell.toString());
		}
		assertNull("Line after all entries correctly empty", sheet.getRow(AExcelFuncIO.COMMON_ROW_START_TABLE + sei.getCategoryAssignments().size()));
	}

	@Test
	public void testExportTypesIntoEmptyWorkbook() throws IOException {
		InputStream iStream = TestActivator.getResourceContentAsString("/resources/SampleTestWithoutPages.xlsx");

		StructuralElementInstance sei = ifaceTypeCollection.getStructuralElementInstance();

		FuncElecExporter feExporter = new FuncElecExporter(localDateTime);
		feExporter.export(sei, iStream);
		Workbook wb = feExporter.getWb();

		Sheet sheet = wb.getSheet(AExcelFuncIO.TEMPLATE_SHEETNAME_INTERFACETYPES);

		for (int i = 0; i < sei.getCategoryAssignments().size(); ++i) {
			CategoryAssignment interfaceType = sei.getCategoryAssignments().get(i);
			Row row = sheet.getRow(AExcelFuncIO.COMMON_ROW_START_TABLE + i);
			Cell cell = row.getCell(AExcelFuncIO.INTERFACETYPES_COLUMN_INTERFACETYPE_NAME);
			assertEquals("Type " + i + "exported correctly", interfaceType.getName(), cell.toString());
		}
		assertNull("Line after all entries correctly empty", sheet.getRow(AExcelFuncIO.COMMON_ROW_START_TABLE + sei.getCategoryAssignments().size()));
	}

	@Test
	public void testExportInterfacesIntoEmptyWorkbook() throws IOException {
		InputStream iStream = TestActivator.getResourceContentAsString("/resources/SampleTestWithoutPages.xlsx");

		StructuralElementInstance sei = elementDef.getStructuralElementInstance();

		FuncElecExporter feExporter = new FuncElecExporter(localDateTime);
		feExporter.export(sei, iStream);
		Workbook wb = feExporter.getWb();

		Sheet sheet = wb.getSheet(AExcelFuncIO.TEMPLATE_SHEETNAME_INTERFACEENDS);
		for (int i = 0; i < sei.getCategoryAssignments().size(); ++i) {
			CategoryAssignment interfaceEnd = sei.getCategoryAssignments().get(i);
			Row row = sheet.getRow(AExcelFuncIO.COMMON_ROW_START_TABLE + i);
			Cell cell = row.getCell(AExcelFuncIO.INTERFACEEND_COLUMN_INTERFACEEND_NAME);
			assertEquals("Interface end name exported correctly", interfaceEnd.getName(), cell.toString());
			cell = row.getCell(AExcelFuncIO.INTERFACEEND_COLUMN_INTERFACEEND_FQN);
			assertEquals("Interface end fqn exported correctly", interfaceEnd.getFullQualifiedInstanceName(), cell.toString());
		}
		assertNull("Line after all entries correctly empty", sheet.getRow(AExcelFuncIO.COMMON_ROW_START_TABLE + sei.getCategoryAssignments().size()));
	}

	@Test
	public void testExportInterfaceEndsIntoEmptyWorkbook() throws IOException {
		InputStream iStream = TestActivator.getResourceContentAsString("/resources/SampleTestWithoutPages.xlsx");

		StructuralElementInstance sei = elementConf2.getStructuralElementInstance();

		FuncElecExporter feExporter = new FuncElecExporter(localDateTime);
		feExporter.export(sei, iStream);
		Workbook wb = feExporter.getWb();

		Sheet sheet = wb.getSheet(AExcelFuncIO.TEMPLATE_SHEETNAME_INTERFACEENDS);
		for (int i = 0; i < sei.getCategoryAssignments().size(); ++i) {
			CategoryAssignment interfaceEnd = sei.getCategoryAssignments().get(i);
			Row row = sheet.getRow(AExcelFuncIO.COMMON_ROW_START_TABLE + i);
			Cell cell = row.getCell(AExcelFuncIO.INTERFACEEND_COLUMN_INTERFACEEND_NAME);
			assertEquals("Interface end name exported correctly", interfaceEnd.getName(), cell.toString());
			cell = row.getCell(AExcelFuncIO.INTERFACEEND_COLUMN_INTERFACEEND_FQN);
			assertEquals("Interface end fqn exported correctly", interfaceEnd.getFullQualifiedInstanceName(), cell.toString());
		}
		assertNull("Line after all entries correctly empty", sheet.getRow(AExcelFuncIO.COMMON_ROW_START_TABLE + sei.getCategoryAssignments().size()));
	}
}
