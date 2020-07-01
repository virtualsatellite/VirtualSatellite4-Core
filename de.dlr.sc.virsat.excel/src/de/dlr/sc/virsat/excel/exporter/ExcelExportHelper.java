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

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFCreationHelper;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.eclipse.core.runtime.Status;

import de.dlr.sc.virsat.excel.AExcelIo;
import de.dlr.sc.virsat.model.dvlm.provider.DVLMEditPlugin;
import de.dlr.sc.virsat.model.dvlm.roles.UserRegistry;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;

/**
 * Class for common code to export excel
 */
public class ExcelExportHelper {
	
	protected static final int ADDZEROIFLESS = 10;
	protected XSSFWorkbook wb;

	/**
	* Simple constructor
	*/
	public ExcelExportHelper() {
	}

	/**
	* returns the workbook
	* @return wb returns the workbook
	*/
	public XSSFWorkbook getWb() {
		return wb;
	}

	/**
	 * gets the creation helper
	 * @return the creation helper
	 */
	public XSSFCreationHelper getCreationHelper() {
		return wb.getCreationHelper();
	}

	/**
	* sets the workbook
	* @param wb the workbook
	*/
	public void setWb(XSSFWorkbook wb) {
		this.wb = wb;
	}

	/**
	* sets the workbook
	* @param iStream input stream for the template or default template
	*/
	public void setWb(InputStream iStream) {

		try {
			wb = new XSSFWorkbook(iStream);
		} catch (IOException e) {
			Status status = new Status(Status.ERROR, "de.dlr.sc.virsat.excel", "Failed to create the workbook ", e);
			DVLMEditPlugin.getPlugin().getLog().log(status);
		}
	}

	/**
	* Creates the header page for all
	* @param exportSei Structural element instance to be exported
	*/
	public void writeHeaderSheet(StructuralElementInstance exportSei, LocalDateTime localDateTime) {
		final int datacell = 1;
		XSSFSheet headerSheet = wb.getSheet(AExcelIo.TEMPLATE_SHEETNAME_HEADER);

		if (headerSheet == null) {
			headerSheet = wb.createSheet(AExcelIo.TEMPLATE_SHEETNAME_HEADER);
		}

		// First of all check if the header has enough rows,
		// if not create them.
		for (int i = 1; i <= AExcelIo.HEADER_ROW_DATE + 1; i++) {
			Row row = headerSheet.getRow(i);
			if (row == null) {
				row = headerSheet.createRow(i);
			}
			Cell cell = row.getCell(0);
			if (cell == null) {
				cell = row.createCell(0);
			}
			cell = row.getCell(1);
			if (cell == null) {
				cell = row.createCell(1);
			}
		}

		// Just write the values, the property names should be in the header already
		Row row = headerSheet.getRow(AExcelIo.HEADER_ROW_STRUCTURALELEMENTUUID);
		row.getCell(1).setCellValue(getCreationHelper().createRichTextString(exportSei.getUuid().toString()));

		row = headerSheet.getRow(AExcelIo.HEADER_ROW_STRUCTURALELEMENTNAME);
		row.getCell(datacell).setCellValue(getCreationHelper().createRichTextString(exportSei.getName()));

		row = headerSheet.getRow(AExcelIo.HEADER_ROW_STRUCTURALELEMENTTYPE);
		row.getCell(datacell).setCellValue(getCreationHelper().createRichTextString(exportSei.getType().getName()));

		row = headerSheet.getRow(AExcelIo.HEADER_ROW_USER);
		row.getCell(datacell).setCellValue(UserRegistry.getInstance().getUserName());

		row = headerSheet.getRow(AExcelIo.HEADER_ROW_DATE);
		row.getCell(datacell).setCellValue(localDateTime.getDayOfMonth() + "/" + localDateTime.getMonthValue() + "/" + localDateTime.getYear());

		row = headerSheet.getRow(AExcelIo.HEADER_ROW_TIME);
		if (localDateTime.getMinute() < ADDZEROIFLESS) {
			row.getCell(datacell).setCellValue(localDateTime.getHour() + ":0" + localDateTime.getMinute());
		} else {
			row.getCell(datacell).setCellValue(localDateTime.getHour() + ":" + localDateTime.getMinute());
		}
	}

	/**
	* Checks the user template, if there are some rows or cells not created by the user ( or in the template) it creates them to ppulate them with data
	* @param sheet the excel data sheet
	* @param rowCount expected rows in this sheet
	* @param cellCount expected cell number in each row
	*/
	public void instantiateCells(Sheet sheet, int rowCount, int cellCount) {

		for (int i = 0; i < rowCount; i++) {
			Row row = sheet.getRow(i);
			if (row == null) {
				row = sheet.createRow(i);
			}
			for (int j = 0; j < cellCount; j++) {
				Cell cell = row.getCell(j);
				if (cell == null) {
					cell = row.createCell(j);
				}
			}
		}
	}
}
