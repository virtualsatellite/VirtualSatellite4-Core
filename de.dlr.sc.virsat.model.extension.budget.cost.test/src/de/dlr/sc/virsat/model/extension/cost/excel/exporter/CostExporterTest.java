/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/

package de.dlr.sc.virsat.model.extension.cost.excel.exporter;

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
import de.dlr.sc.virsat.model.extension.budget.cost.excel.exporter.CostExporter;
import de.dlr.sc.virsat.model.extension.cost.excel.AExcelCostIO;

/**
 * Test Case for Exporting to Excel
 */
public class CostExporterTest extends de.dlr.sc.virsat.model.extension.budget.cost.test.ExcelTestCase {

	protected LocalDateTime localDateTime;
	
	@Override
	public void setUp() throws CoreException {
		super.setUp();
		//CHECKSTYLE:OFF
		localDateTime = LocalDateTime.now();
		//CHECKSTYLE:ON
	}
	
	@Test
	public void canExport() {
		CostExporter costExporter = new CostExporter(localDateTime);
		assertEquals(true, costExporter.canExport(elementConf.getStructuralElementInstance()));
	}

	@Test
	public void testExportCostTypes() { 		
		StructuralElementInstance sei = costTypesCollection.getStructuralElementInstance();
		CostExporter costExporter = new CostExporter(localDateTime);
		costExporter.export(costTypesCollection.getStructuralElementInstance(), System.getProperty("java.io.tmpdir"), true, "");
		XSSFWorkbook wb = costExporter.getWb();

		Sheet sheet = wb.getSheet(AExcelCostIO.TEMPLATE_SHEETNAME_COSTTYPES);
		for (int i = 0; i < sei.getCategoryAssignments().size(); ++i) {
			CategoryAssignment interfaceType = sei.getCategoryAssignments().get(i);
			Row row = sheet.getRow(AExcelCostIO.COMMON_ROW_START_TABLE + i);
			Cell cell = row.getCell(AExcelCostIO.COSTTYPES_COLUMN_COSTTYPE_NAME);
			assertEquals("Type " + i + "exported correctly", interfaceType.getName(), cell.toString());
		}
	}

	@Test
	public void testExportCostEquipment() throws IOException {
		InputStream iStream = de.dlr.sc.virsat.model.extension.budget.cost.test.TestActivator.getResourceContentAsString("/resources/SampleTest.xlsx");
		StructuralElementInstance sei = elementDef.getStructuralElementInstance();

		CostExporter costExporter = new CostExporter(localDateTime);
		costExporter.export(sei, iStream);
		Workbook wb = costExporter.getWb();

		Sheet sheet = wb.getSheet(AExcelCostIO.TEMPLATE_SHEETNAME_COSTEQUIPMENTS);
		for (int i = 0; i < sei.getCategoryAssignments().size(); ++i) {
			CategoryAssignment interfaceEnd = sei.getCategoryAssignments().get(i);
			Row row = sheet.getRow(AExcelCostIO.COMMON_ROW_START_TABLE + i);
			Cell cell = row.getCell(AExcelCostIO.COSTEQUIPMENT_COLUMN_COSTEQUIPMENT_NAME);
			assertEquals("CostEquipment name exported correctly", interfaceEnd.getName(), cell.toString());
			cell = row.getCell(AExcelCostIO.COSTEQUIPMENT_COLUMN_COSTEQUIPMENT_FQN);
			assertEquals("CostEquipment fqn exported correctly", interfaceEnd.getFullQualifiedInstanceName(), cell.toString());
		}
		Row row = sheet.getRow(AExcelCostIO.COMMON_ROW_START_TABLE + sei.getCategoryAssignments().size());
		assertNull("Line after alle entries correctly empty", row);
	}

	@Test
	public void testExportCostTypesIntoEmptyWorkbook() throws IOException {
		InputStream iStream = de.dlr.sc.virsat.model.extension.budget.cost.test.TestActivator.getResourceContentAsString("/resources/SampleTestWithoutPages.xlsx");

		StructuralElementInstance sei = costTypesCollection.getStructuralElementInstance();

		CostExporter costExporter = new CostExporter(localDateTime);
		costExporter.export(sei, iStream);
		Workbook wb = costExporter.getWb();

		Sheet sheet = wb.getSheet(AExcelCostIO.TEMPLATE_SHEETNAME_COSTTYPES);

		for (int i = 0; i < sei.getCategoryAssignments().size(); ++i) {
			CategoryAssignment interfaceType = sei.getCategoryAssignments().get(i);
			Row row = sheet.getRow(AExcelCostIO.COMMON_ROW_START_TABLE + i);
			Cell cell = row.getCell(AExcelCostIO.COSTTYPES_COLUMN_COSTTYPE_NAME);
			assertEquals("Type " + i + "exported correctly", interfaceType.getName(), cell.toString());
		}
		assertNull("Line after all entries correctly empty", sheet.getRow(AExcelCostIO.COMMON_ROW_START_TABLE + sei.getCategoryAssignments().size()));
	}

	@Test
	public void testExportCostEquipmentsIntoEmptyWorkbook() throws IOException {
		InputStream iStream = de.dlr.sc.virsat.model.extension.budget.cost.test.TestActivator.getResourceContentAsString("/resources/SampleTestWithoutPages.xlsx");

		StructuralElementInstance sei = elementConf.getStructuralElementInstance();

		CostExporter costExporter = new CostExporter(localDateTime);
		costExporter.export(sei, iStream);
		Workbook wb = costExporter.getWb();

		Sheet sheet = wb.getSheet(AExcelCostIO.TEMPLATE_SHEETNAME_COSTEQUIPMENTS);
		for (int i = 0; i < sei.getCategoryAssignments().size(); ++i) {
			CategoryAssignment interfaceEnd = sei.getCategoryAssignments().get(i);
			Row row = sheet.getRow(AExcelCostIO.COMMON_ROW_START_TABLE + i);
			Cell cell = row.getCell(AExcelCostIO.COSTEQUIPMENT_COLUMN_COSTEQUIPMENT_NAME);
			assertEquals("CostEquipment name exported correctly", interfaceEnd.getName(), cell.toString());
			cell = row.getCell(AExcelCostIO.COSTEQUIPMENT_COLUMN_COSTEQUIPMENT_FQN);
			assertEquals("CostEquipment fqn exported correctly", interfaceEnd.getFullQualifiedInstanceName(), cell.toString());
		}
		assertNull("Line after all entries correctly empty", sheet.getRow(AExcelCostIO.COMMON_ROW_START_TABLE + sei.getCategoryAssignments().size()));
	}
}
