/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.excel.exporter;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import java.time.LocalDateTime;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

import de.dlr.sc.virsat.model.dvlm.roles.Discipline;
import de.dlr.sc.virsat.model.dvlm.roles.RolesFactory;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElement;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralFactory;

public class ExcelExportHelperTest {

	@Test
	public void testGetWb() {
		ExcelExportHelper excelExportHelper = new ExcelExportHelper();
	
		XSSFWorkbook workbook = new XSSFWorkbook();

		excelExportHelper.setWb(workbook);
		XSSFWorkbook getWb = excelExportHelper.getWb();
	
		assertEquals("Got Workbook which was set before", workbook, getWb);
	}

	@Test
	public void testWriteHeaderSheet() {

		Discipline discipline = RolesFactory.eINSTANCE.createDiscipline();
		StructuralElement se = StructuralFactory.eINSTANCE.createStructuralElement();
		StructuralElementInstance sei = StructuralFactory.eINSTANCE.createStructuralElementInstance();
		
		discipline.setName("Discipline");
		discipline.setUser("user_me");
		
		se.setName("SeType");
		sei.setName("SeiName");
		
		sei.setAssignedDiscipline(discipline);
		sei.setType(se);
	
		XSSFWorkbook workbook = new XSSFWorkbook();

		ExcelExportHelper excelExportHelper = new ExcelExportHelper();
		excelExportHelper.setWb(workbook);
		
		//CHECKSTYLE:OFF
		LocalDateTime localDateTime05Min = LocalDateTime.of(2020, 04, 21, 13, 5);
		LocalDateTime localDateTime15Min = LocalDateTime.of(2020, 04, 21, 13, 15);
		
		excelExportHelper.writeHeaderSheet(sei, localDateTime05Min);
		
		assertEquals("Correct amount of sheets", 1, workbook.getNumberOfSheets());
		XSSFSheet sheet = workbook.getSheetAt(0);
		
		assertNotNull("New sheet has been attached", sheet);
		assertEquals("Name of Sei exists", "SeiName", sheet.getRow(5).getCell(1).getRichStringCellValue().toString());
		assertEquals("Got correct time string", "13:05", sheet.getRow(9).getCell(1).getRichStringCellValue().toString());

		excelExportHelper.writeHeaderSheet(sei, localDateTime15Min);
	
		assertEquals("Correct amount of sheets", 1, workbook.getNumberOfSheets());
		sheet = workbook.getSheetAt(0);
		
		assertNotNull("New sheet has been attached", sheet);
		assertEquals("Name of Sei exists", "SeiName", sheet.getRow(5).getCell(1).getRichStringCellValue().toString());
		assertEquals("Got correct time string", "13:15", sheet.getRow(9).getCell(1).getRichStringCellValue().toString());
		//CHECKSTYLE:ON
	}

	@Test
	public void testInstantiateCells() {
		@SuppressWarnings("resource")
		XSSFWorkbook workbook = new XSSFWorkbook();

		XSSFSheet sheet = workbook.createSheet("TestSheet");
		
		assertNull("Row Still null", sheet.getRow(1));
		assertNull("Row Still null", sheet.getRow(1));
		assertNull("Row Still null", sheet.getRow(2));
		assertNull("Row Still null", sheet.getRow(2));
		
		ExcelExportHelper excelExportHelper = new ExcelExportHelper();
		
		excelExportHelper.instantiateCells(sheet, 2, 2);
		
		assertNotNull("Cell not null", sheet.getRow(0).getCell(0));
		assertNotNull("Cell not null", sheet.getRow(0).getCell(1));
		assertNotNull("Cell not null", sheet.getRow(1).getCell(0));
		assertNotNull("Cell not null", sheet.getRow(1).getCell(1));
		assertNull("Cell still null", sheet.getRow(1).getCell(2));
		assertNull("Row2 still null", sheet.getRow(2));
	}
}
