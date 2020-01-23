/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.uiengine.ui.excel;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.time.LocalDateTime;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeColumn;
import org.eclipse.swt.widgets.TreeItem;

import de.dlr.sc.virsat.model.dvlm.provider.DVLMEditPlugin;
import de.dlr.sc.virsat.model.dvlm.roles.UserRegistry;
import de.dlr.sc.virsat.uieingine.ui.DVLMEditorPlugin;


/**
 * Class for exporting excel
 * @author bell_er
 *
 */
public class ExcelExporter {
	private static final int FOUR = 4;
	private static final int FIVE = 5;
	private static final int ADDZEROIFLESS = 10;
	private static final int FONTSIZE = 12;
	private static final String DEFAULT_TEMPLATE_PATH = "/resources/TableViewTemplate.xlsx";
	/**
	 * Default constructor
	 */
	public ExcelExporter() {

	}
	/**
	 * Export method calls extension points to do the job
	 * @param columnViewer tableViewer to be exported
	 * @param path where to export
	 * @param type of the tableViewer to be exported
	 * @throws CoreException 
	 *
	 */
	public void export(ColumnViewer columnViewer, String path, String type) throws CoreException {
		File file = new File(path);
		try (FileOutputStream out = new FileOutputStream(file)) {
			XSSFWorkbook wb = createWorkbookFromTable(columnViewer, type);
			wb.write(out);
			
			DVLMEditPlugin.getPlugin().getLog().log(new Status(Status.INFO, "Excel IO",
					"Successfully exported to excel file to " + file.getAbsolutePath()));
		} catch (Exception e) {
			Status status = new Status(Status.ERROR, "de.dlr.sc.virsat.uiengine.ui", "Failed to perform an export operation! ", e);
			DVLMEditPlugin.getPlugin().getLog().log(status);
			ErrorDialog.openError(Display.getDefault().getActiveShell(), "Excel IO Failed", "Export failed", status);
		} 
	}
	/**
	 * creates the workbook
	 * @param columnViewer tableViewer to be exported
	 * @param type of the tableViewer to be exported
	 * @return the workbook
	 * @throws IOException 
	 *
	 */
	private XSSFWorkbook createWorkbookFromTable(ColumnViewer columnViewer, String type) throws IOException {

		// create a workbook
		InputStream is = getResourceContentAsString(DEFAULT_TEMPLATE_PATH);
		XSSFWorkbook wb = new XSSFWorkbook(is);

		// rename the worksheet
		wb.setSheetName(0, type);
		XSSFSheet sheet = wb.getSheetAt(0);

		// set username and export time 
		XSSFRow row = sheet.getRow(FOUR);
		
		row.getCell(0).setCellValue(UserRegistry.getInstance().getUserName());
		

		LocalDateTime ldt = LocalDateTime.now();	
		row.getCell(1).setCellValue(ldt.getDayOfMonth() + "/" + ldt.getMonthValue() + "/" + ldt.getYear());
	
		if (ldt.getMinute() < ADDZEROIFLESS) {
			row.getCell(2).setCellValue(ldt.getHour() + ":0" + ldt.getMinute());
		} else {
			row.getCell(2).setCellValue(ldt.getHour() + ":" + ldt.getMinute());
		}

		// shade the background of the header row
		XSSFCellStyle headerStyle = wb.createCellStyle();
		headerStyle.setBorderTop(CellStyle.BORDER_THIN);
		headerStyle.setBorderBottom(CellStyle.BORDER_THIN);
		headerStyle.setBorderLeft(CellStyle.BORDER_THIN);
		headerStyle.setBorderRight(CellStyle.BORDER_THIN);
		headerStyle.setAlignment(HorizontalAlignment.LEFT);
		
		XSSFFont headerFont = wb.createFont();
		headerFont.setFontHeightInPoints((short) FONTSIZE);
		headerFont.setFontName("Calibri");
		headerFont.setColor(IndexedColors.BLACK.getIndex());
		headerStyle.setFillPattern(CellStyle.NO_FILL);
		headerFont.setBold(true);
		headerFont.setItalic(false);
		headerStyle.setFont(headerFont);
		
		XSSFCellStyle dataStyle = wb.createCellStyle();	
		dataStyle.setBorderTop(CellStyle.BORDER_THIN);
		dataStyle.setBorderBottom(CellStyle.BORDER_THIN);
		dataStyle.setBorderLeft(CellStyle.BORDER_THIN);
		dataStyle.setBorderRight(CellStyle.BORDER_THIN);
		dataStyle.setAlignment(HorizontalAlignment.LEFT);
		dataStyle.setVerticalAlignment(VerticalAlignment.BOTTOM);
		// add header row
		int columnLenght = 0;
		if (columnViewer instanceof TableViewer) {
			Table table = ((TableViewer) columnViewer).getTable();
			TableColumn[] columns = table.getColumns();
			int rowIndex = FIVE;
			int cellIndex = 0;
			XSSFRow header = sheet.createRow((short) rowIndex++);
			for (TableColumn column : columns) {
				XSSFCell cell = header.createCell(cellIndex++);
				cell.setCellValue(column.getText());
				cell.setCellStyle(headerStyle);
			}
			// add data rows
			TableItem[] items = ((TableViewer) columnViewer).getTable().getItems();
			for (TableItem item : items) {
				// create a new row
				row = sheet.createRow((short) rowIndex++);
				cellIndex = 0;

				for (int i = 0; i < columns.length; i++) {
					// create a new cell
					XSSFCell cell = row.createCell(cellIndex++);
					cell.setCellStyle(dataStyle);
					// set the cell's value
					String text = item.getText(i);
					cell.setCellValue(text);
				}
			}
			columnLenght = columns.length;
		} else if (columnViewer instanceof TreeViewer) {
			Tree a = ((TreeViewer) columnViewer).getTree();
			TreeColumn[] columns = a.getColumns();
			int rowIndex = FIVE;
			int cellIndex = 0;
			XSSFRow header = sheet.createRow((short) rowIndex++);
			for (TreeColumn column : columns) {
				XSSFCell cell = header.createCell(cellIndex++);
				cell.setCellValue(column.getText());
				cell.setCellStyle(headerStyle);
			}
			// add data rows
			Tree tree = ((TreeViewer) columnViewer).getTree();
			TreeItem[] items = tree.getItems();
			for (TreeItem item : items) {
				rowIndex = addItem(item, sheet, columns, dataStyle, rowIndex);
			}
			columnLenght = columns.length;
		}
		// auto fit the columns
		for (int i = 0; i < columnLenght; i++) {
			sheet.autoSizeColumn((short) i);
		}
		
		return wb;
	}
	
	/**
	 * Creates a corresponding row for the given item and its children (recursively)
	 * @param item the tree item
	 * @param sheet the sheet
	 * @param columns the columns
	 * @param dataStyle the style
	 * @param rowIndex the base row index
	 * @return the new row index after adding the data of the given item and its children
	 */
	private int addItem(TreeItem item, XSSFSheet sheet, TreeColumn[] columns, XSSFCellStyle dataStyle, int rowIndex) {
		// create a new row
		XSSFRow row = sheet.createRow((short) rowIndex++);
		
		for (int i = 0; i < columns.length; i++) {
			// create a new cell
			XSSFCell cell = row.createCell(i);
			cell.setCellStyle(dataStyle);
			// set the cell's value
			String text = item.getText(i);
			cell.setCellValue(text);
		}
		
		TreeItem[] children = item.getItems();
		for (TreeItem child : children) {
			rowIndex = addItem(child, sheet, columns, dataStyle, rowIndex);
		}
		
		return rowIndex;
	}

	/**
	 * Method to access the fragments contents from the resource folder and to hand it back as string
	 * @param resourcePath the path to the resource starting with "resource/"
	 * @return the content of the resource as string
	 * @throws IOException throws
	 */
	public static InputStream getResourceContentAsString(String resourcePath) throws IOException {
		URL url;
		url = new URL("platform:/plugin/" + DVLMEditorPlugin.ID + resourcePath);
		InputStream inputStream = url.openConnection().getInputStream();

		return inputStream;
	}

}
