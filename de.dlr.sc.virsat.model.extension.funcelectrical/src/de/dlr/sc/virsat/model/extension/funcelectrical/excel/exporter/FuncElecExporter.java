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

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.ui.statushandlers.StatusManager;

import de.dlr.sc.virsat.excel.exporter.ExcelExportHelper;
import de.dlr.sc.virsat.excel.exporter.IExport;
import de.dlr.sc.virsat.model.concept.types.util.BeanCategoryAssignmentHelper;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import de.dlr.sc.virsat.model.extension.funcelectrical.Activator;
import de.dlr.sc.virsat.model.extension.funcelectrical.excel.AExcelFuncIO;
import de.dlr.sc.virsat.model.extension.funcelectrical.model.Interface;
import de.dlr.sc.virsat.model.extension.funcelectrical.model.InterfaceEnd;
import de.dlr.sc.virsat.model.extension.funcelectrical.model.InterfaceType;
import de.dlr.sc.virsat.model.extension.funcelectrical.model.InterfaceTypeCollection;
import de.dlr.sc.virsat.model.extension.ps.model.ElementConfiguration;
import de.dlr.sc.virsat.model.extension.ps.model.ElementDefinition;
import de.dlr.sc.virsat.model.extension.ps.model.ElementOccurence;
import de.dlr.sc.virsat.model.extension.ps.model.ElementRealization;

/**
 * Class for exporting excel
 */
public class FuncElecExporter implements IExport {
	
	private static final String DEFAULT_TEMPLATE_PATH = "/resources/ExcelExportTemplate.xlsx";
	
	private static final String[] EXPORTABLE_SEIS = {
			ElementDefinition.class.getSimpleName(),
			ElementConfiguration.class.getSimpleName(),
			InterfaceTypeCollection.class.getSimpleName(),
			ElementRealization.class.getSimpleName(),
			ElementOccurence.class.getSimpleName()
	};
	
	protected LocalDateTime localDateTime;
	private ExcelExportHelper helper;
	
	public FuncElecExporter() {
		this(LocalDateTime.now());
	}
	
	public FuncElecExporter(LocalDateTime localDateTime) {
		this.localDateTime = localDateTime;
	}

	@Override
	public void export(EObject eObject, String path, boolean useDefaultTemplate, String templatePath) {
		StructuralElementInstance parentSc = (StructuralElementInstance) eObject;
		List<StructuralElementInstance> seis = new ArrayList<>();
		seis.add(parentSc);
		seis.addAll(parentSc.getDeepChildren());
		for (StructuralElementInstance sei : seis) {
			String selectedSEIType = sei.getType().getName();
			if (Stream.of(EXPORTABLE_SEIS).anyMatch(exportable -> exportable.equals(selectedSEIType))) {
				try {
					InputStream iStream = useDefaultTemplate 
							?	Activator.getResourceContentAsString(DEFAULT_TEMPLATE_PATH)
							: 	new FileInputStream(templatePath);
								
					export(sei, iStream);
					File file = new File(path + "/" + sei.getFullQualifiedInstanceName() + ".xlsx");
					FileOutputStream out = new FileOutputStream(file);
					helper.getWb().write(out);
				} catch (IOException e) {
					Status status = new Status(Status.ERROR, Activator.getPluginId(), "Failed to perform an export operation!" + System.lineSeparator() + e.getMessage(), e);
					StatusManager.getManager().handle(status, StatusManager.LOG | StatusManager.SHOW);
				}
			}
		}
	}

	/**
	 * Exports depending on the type of the Structural element
	 * @param sei StructuralElementInstance to be exported
	 * @param iStream the output
	 */
	protected void export(StructuralElementInstance sei, InputStream iStream) {
		helper = new ExcelExportHelper();
		helper.setWb(iStream);
		helper.writeHeaderSheet(sei, localDateTime);
		exportData(sei);
	}

	@Override
	public boolean canExport(Object selection) {
		return selection instanceof StructuralElementInstance;	
	}
	/**
	 * Exports depending on the type of the Structural element
	 * @param exportSei Structural element instance to be exported
	 */
	private void exportData(StructuralElementInstance exportSei) {
		String exportSeiTypeName = exportSei.getType().getName();
		if (exportSeiTypeName.equals(InterfaceTypeCollection.class.getSimpleName())) {
			// when exporting interface Type collection those sheets are not needed
			removeSheets(AExcelFuncIO.TEMPLATE_SHEETNAME_INTERFACEENDS, AExcelFuncIO.TEMPLATE_SHEETNAME_INTERFACES);
			createDataSheetInterfaceTypes(exportSei);	
		} else if (exportSeiTypeName.equals(ElementDefinition.class.getSimpleName())
				|| exportSeiTypeName.equals(ElementRealization.class.getSimpleName())) {
			// when exporting ElementDefinition or ElementRealization those sheets are not needed
			removeSheets(AExcelFuncIO.TEMPLATE_SHEETNAME_INTERFACETYPES, AExcelFuncIO.TEMPLATE_SHEETNAME_INTERFACES);
			createDataSheetInterfaceEnds(exportSei);
		} else if (exportSeiTypeName.equals(ElementConfiguration.class.getSimpleName())
				|| exportSeiTypeName.equals(ElementOccurence.class.getSimpleName())) {
			// when exporting ElementConfiguration or ElementOccurence that sheet is not needed
			removeSheets(AExcelFuncIO.TEMPLATE_SHEETNAME_INTERFACETYPES);
			createDataSheetInterfaceEnds(exportSei);
			createDataSheetInterfaces(exportSei);
		}
	}

	/**
	 * Removes the sheets
	 * @param values the sheets to be removed
	 */
	private void removeSheets(String... values) {
		for (String number : values) {
			int sheetIndex = helper.getWb().getSheetIndex(number);
			if (sheetIndex >= 0) {
				helper.getWb().removeSheetAt(helper.getWb().getSheetIndex(number));
			}
		}
	}

	/**
	 * Creates the data sheet for interface and populates it with the data
	 * @param exportSei Structural element instance to be exported
	 */
	private void createDataSheetInterfaces(StructuralElementInstance exportSei) {
		XSSFSheet sheet = createSheetIfNeeded(AExcelFuncIO.TEMPLATE_SHEETNAME_INTERFACES);
		BeanCategoryAssignmentHelper bCaHelper = new BeanCategoryAssignmentHelper();
		List<Interface> seiInterfaces = bCaHelper.getAllBeanCategories(exportSei, Interface.class);	
		helper.instantiateCells(sheet, seiInterfaces.size() + AExcelFuncIO.COMMON_ROW_START_TABLE, AExcelFuncIO.INTERFACE_COLUMN_INTERFACE_TO + 1);
		int i = AExcelFuncIO.COMMON_ROW_START_TABLE;
		for (Interface iface : seiInterfaces) {
			Row row = sheet.getRow(i);
			row.getCell(AExcelFuncIO.COMMON_COLUMN_UUID).setCellValue(helper.getCreationHelper().createRichTextString(iface.getTypeInstance().getUuid().toString()));
			row.getCell(AExcelFuncIO.INTERFACE_COLUMN_INTERFACE_NAME).setCellValue(helper.getCreationHelper().createRichTextString(iface.getName()));
			if (iface.getInterfaceEndFrom() != null) {
				row.getCell(AExcelFuncIO.INTERFACE_COLUMN_INTERFACE_FROM).setCellValue(helper.getCreationHelper().createRichTextString(iface.getInterfaceEndFrom().getTypeInstance().getFullQualifiedInstanceName()));
			}
			if (iface.getInterfaceEndTo() != null) {
				row.getCell(AExcelFuncIO.INTERFACE_COLUMN_INTERFACE_TO).setCellValue(helper.getCreationHelper().createRichTextString(iface.getInterfaceEndTo().getTypeInstance().getFullQualifiedInstanceName()));	
			}
			i++;
		}
	}

	/**
	 * Creates the data sheet for interface type collection and populates it with the data
	 * @param exportSei Structural element instance to be exported
	 */
	private void createDataSheetInterfaceTypes(StructuralElementInstance exportSei) {
		XSSFSheet sheet = createSheetIfNeeded(AExcelFuncIO.TEMPLATE_SHEETNAME_INTERFACETYPES);
		// get all the interface types from the model
		BeanCategoryAssignmentHelper bCaHelper = new BeanCategoryAssignmentHelper();
		List<InterfaceType> seiInterfaceTypes = bCaHelper.getAllBeanCategories(exportSei, InterfaceType.class);

		helper.instantiateCells(sheet, seiInterfaceTypes.size() + AExcelFuncIO.COMMON_ROW_START_TABLE, AExcelFuncIO.INTERFACETYPES_COLUMN_INTERFACETYPE_NAME + 1);
		int i = AExcelFuncIO.COMMON_ROW_START_TABLE;
		for (InterfaceType ifaceType : seiInterfaceTypes) {
			Row row = sheet.getRow(i);
			row.getCell(AExcelFuncIO.COMMON_COLUMN_UUID).setCellValue(helper.getCreationHelper().createRichTextString(ifaceType.getTypeInstance().getUuid().toString()));
			row.getCell(AExcelFuncIO.INTERFACETYPES_COLUMN_INTERFACETYPE_NAME).setCellValue(helper.getCreationHelper().createRichTextString(ifaceType.getName()));
			i++;
		}
	}

	/**
	 * Creates the data sheet for Element Definition and populates it with the data
	 * @param exportSei Structural element instance to be exported
	 */
	private void createDataSheetInterfaceEnds(StructuralElementInstance exportSei) {
		XSSFSheet sheet = createSheetIfNeeded(AExcelFuncIO.TEMPLATE_SHEETNAME_INTERFACEENDS);
		// get all the interface ends
		BeanCategoryAssignmentHelper bCaHelper = new BeanCategoryAssignmentHelper();
		List<InterfaceEnd> seiInterfaceEnds = bCaHelper.getAllBeanCategories(exportSei, InterfaceEnd.class);
		helper.instantiateCells(sheet, seiInterfaceEnds.size() + AExcelFuncIO.COMMON_ROW_START_TABLE, AExcelFuncIO.INTERFACEEND_COLUMN_INTERFACEEND_FQN + 1);

		// for each interface end, fill out a row
		int i = AExcelFuncIO.COMMON_ROW_START_TABLE;
		for (InterfaceEnd ifaceEnd : seiInterfaceEnds) {
			Row row = sheet.getRow(i);
			row.getCell(AExcelFuncIO.COMMON_COLUMN_UUID).setCellValue(helper.getCreationHelper().createRichTextString(ifaceEnd.getTypeInstance().getUuid().toString()));
			row.getCell(AExcelFuncIO.INTERFACEEND_COLUMN_INTERFACEEND_NAME).setCellValue(helper.getCreationHelper().createRichTextString(ifaceEnd.getName()));
			row.getCell(AExcelFuncIO.INTERFACEEND_COLUMN_INTERFACEEND_TYPE).setCellValue(helper.getCreationHelper().createRichTextString(""));
			row.getCell(AExcelFuncIO.INTERFACEEND_COLUMN_INTERFACEEND_TYPE).setCellValue(helper.getCreationHelper().createRichTextString(getIfeTypeName(ifaceEnd)));
			row.getCell(AExcelFuncIO.INTERFACEEND_COLUMN_INTERFACEEND_FQN).setCellValue(helper.getCreationHelper().createRichTextString(ifaceEnd.getTypeInstance().getFullQualifiedInstanceName()));
			i++;
		}
	}
	
	/**
	 * Creates the sheet if it is not already there
	 * @param sheetName the name of the sheet
	 * @return sheet the created sheet
	 */
	private XSSFSheet createSheetIfNeeded(String sheetName) {
		XSSFSheet sheet = helper.getWb().getSheet(sheetName);
		if (sheet == null) {
			sheet = helper.getWb().createSheet(sheetName);
		}
		return sheet;
	}
	
	/**
	 * returns the type of an interface end if exists
	 * @param ifaceEnd interface end object
	 * @return interface type name if exists else ""
	 */
	private String getIfeTypeName(InterfaceEnd ifaceEnd) {
		InterfaceType ifaceType = ifaceEnd.getType();
		if (ifaceType == null) {
			return "";
		} else {
			return Objects.toString(ifaceType.getName(), "");
		}
	}
	
	/**
	 * gets the workbook from helper
	 * @return the workbook
	 */
	protected XSSFWorkbook getWb() {
		return helper.getWb();
	}
}
