/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.funcelectrical.excelExport;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.swt.widgets.Display;

import de.dlr.sc.virsat.excel.AExcelIo;
import de.dlr.sc.virsat.excel.ExcelExportHelper;
import de.dlr.sc.virsat.excel.IExport;
import de.dlr.sc.virsat.model.concept.types.util.BeanCategoryAssignmentHelper;
import de.dlr.sc.virsat.model.dvlm.provider.DVLMEditPlugin;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import de.dlr.sc.virsat.model.extension.funcelectrical.Activator;
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
 * @author bell_er
 *
 */
public class FuncElecExporter implements IExport {
	private final String defaultTemplatePath = "/resources/ExcelExportTemplate.xlsx";
	private ExcelExportHelper helper;

	public static final String[] EXPORTABLE_SEIS = { 
			ElementDefinition.class.getSimpleName(),
			ElementConfiguration.class.getSimpleName(), 
			InterfaceTypeCollection.class.getSimpleName(), 
			ElementRealization.class.getSimpleName(),
			ElementOccurence.class.getSimpleName()
	};
	@Override
	public void export(EObject eObject, String path, boolean useDefaultTemplate, String templatePath) {
		StructuralElementInstance parentSc = (StructuralElementInstance) eObject;
		ArrayList <StructuralElementInstance> allSei = new ArrayList<StructuralElementInstance>();
		allSei.add(parentSc);
		allSei.addAll(parentSc.getDeepChildren());
		for (StructuralElementInstance sc : allSei) {
			String selectedSEIType = sc.getType().getName();
			if (Stream.of(EXPORTABLE_SEIS).anyMatch(exportable -> exportable.equals(selectedSEIType))) {
				InputStream is = null;
				try {
					if (useDefaultTemplate) {
						is = Activator.getResourceContentAsString(defaultTemplatePath);
					} else {
						is = Activator.getResourceContentAsString(templatePath);
					}
				} catch (IOException e) {
					Activator.getDefault().getLog().log(new Status(Status.WARNING, Activator.getPluginId(), "Could not locate the template file"));
				}
				export(sc, is);

				File file = new File(path + "/" + sc.getFullQualifiedInstanceName() + ".xlsx");
				try {
					FileOutputStream out = new FileOutputStream(file);
					helper.getWb().write(out);
				} catch (IOException e) {
					Status status = new Status(Status.ERROR, Activator.getPluginId(), "Failed to perform an export operation! ", e);
					DVLMEditPlugin.getPlugin().getLog().log(status);
					ErrorDialog.openError(Display.getDefault().getActiveShell(), "Excel IO Failed", "Export failed", status);
				}
			}
		}
	}
	/**
	 * Exports depending on the type of the Structural element
	 * @param sc StructuralElementInstance to be exported
	 * @param fis the output
	 * @author  Bell_er
	 */
	public void export(StructuralElementInstance sc, InputStream fis) {
		helper = new ExcelExportHelper();
		helper.setWb(fis);
		helper.setHeaders(sc);
		exportData(sc);
	}

	@Override
	public boolean canExport(Object selection) {
		if (selection instanceof StructuralElementInstance) {
			return true;
		}
		return false;	
	}
	/**
	 * Exports depending on the type of the Structural element
	 * @param exportSei Structural element instance to be exported
	 * @author  Bell_er
	 * 
	 */
	public void exportData(StructuralElementInstance exportSei) {
		String exportSeiTypeName = exportSei.getType().getName();
		if (exportSeiTypeName.equals(InterfaceTypeCollection.class.getSimpleName())) {
			// when exporting interface Type collection those sheets are not needed
			removeSheets(AExcelIo.TEMPLATE_SHEETNAME_INTERFACEENDS, AExcelIo.TEMPLATE_SHEETNAME_INTERFACES);
			createDataSheetInterfaceTypes(exportSei);	
		} else if (exportSeiTypeName.equals(ElementDefinition.class.getSimpleName())
				|| exportSeiTypeName.equals(ElementRealization.class.getSimpleName())) {
			// when exporting ElementDefinition or ElementRealization those sheets are not needed
			removeSheets(AExcelIo.TEMPLATE_SHEETNAME_INTERFACETYPES, AExcelIo.TEMPLATE_SHEETNAME_INTERFACES);
			createDataSheetInterfaceEnds(exportSei);
		} else if (exportSeiTypeName.equals(ElementConfiguration.class.getSimpleName())
				|| exportSeiTypeName.equals(ElementOccurence.class.getSimpleName())) {
			// when exporting ElementConfiguration or ElementOccurence that sheet is not needed
			removeSheets(AExcelIo.TEMPLATE_SHEETNAME_INTERFACETYPES);
			createDataSheetInterfaceEnds(exportSei);
			createDataSheetInterfaces(exportSei);
		}
	}

	/**
	 * Removes the sheets
	 * @param values the sheets to be removed
	 */
	private void removeSheets(String... values) {
		for (String a:values) {
			int sheetIndex  =  helper.getWb().getSheetIndex(a);
			if (sheetIndex >= 0) {
				helper.getWb().removeSheetAt(helper.getWb().getSheetIndex(a));
			}
		}
	}

	/**
	 * Creates the data sheet for interface and populates it with the data
	 * @param exportSei Structural element instance to be exported
	 * @author  Bell_er
	 * 
	 */
	private void createDataSheetInterfaces(StructuralElementInstance exportSei) {
		XSSFSheet sheet = createSheetIfNeeded(AExcelIo.TEMPLATE_SHEETNAME_INTERFACES);
		BeanCategoryAssignmentHelper bCaHelper = new BeanCategoryAssignmentHelper();
		List<Interface> seiInterfaces = bCaHelper.getAllBeanCategories(exportSei, Interface.class);	
		helper.nullChecker(seiInterfaces.size() + AExcelIo.COMMON_ROW_START_TABLE, sheet, AExcelIo.INTERFACE_COLUMN_INTERFACE_TO + 1);
		int i = AExcelIo.COMMON_ROW_START_TABLE;
		for (Interface iface : seiInterfaces) {
			Row row = sheet.getRow(i);
			row.getCell(AExcelIo.COMMON_COLUMN_UUID).setCellValue(helper.getCreationHelper().createRichTextString(iface.getTypeInstance().getUuid().toString()));
			row.getCell(AExcelIo.INTERFACE_COLUMN_INTERFACE_NAME).setCellValue(helper.getCreationHelper().createRichTextString(iface.getName()));
			if (iface.getInterfaceEndFrom() != null) {
				row.getCell(AExcelIo.INTERFACE_COLUMN_INTERFACE_FROM).setCellValue(helper.getCreationHelper().createRichTextString(iface.getInterfaceEndFrom().getTypeInstance().getFullQualifiedInstanceName()));
			}
			if (iface.getInterfaceEndTo() != null) {
				row.getCell(AExcelIo.INTERFACE_COLUMN_INTERFACE_TO).setCellValue(helper.getCreationHelper().createRichTextString(iface.getInterfaceEndTo().getTypeInstance().getFullQualifiedInstanceName()));	
			}
			i++;
		}
	}

	/**
	 * Creates the data sheet for interface type collection and populates it with the data
	 * @param exportSei Structural element instance to be exported
	 * @author  Bell_er
	 * 
	 */
	private void createDataSheetInterfaceTypes(StructuralElementInstance exportSei) {
		XSSFSheet sheet = createSheetIfNeeded(AExcelIo.TEMPLATE_SHEETNAME_INTERFACETYPES);
		// get all the interface types from the model
		BeanCategoryAssignmentHelper bCaHelper = new BeanCategoryAssignmentHelper();
		List<InterfaceType> seiInterfaceTypes = bCaHelper.getAllBeanCategories(exportSei, InterfaceType.class);

		helper.nullChecker(seiInterfaceTypes.size() + AExcelIo.COMMON_ROW_START_TABLE, sheet, AExcelIo.INTERFACETYPES_COLUMN_INTERFACETYPE_NAME + 1);
		int i = AExcelIo.COMMON_ROW_START_TABLE;
		for (InterfaceType ift : seiInterfaceTypes) {
			Row row = sheet.getRow(i);
			row.getCell(AExcelIo.COMMON_COLUMN_UUID).setCellValue(helper.getCreationHelper().createRichTextString(ift.getTypeInstance().getUuid().toString()));
			row.getCell(AExcelIo.INTERFACETYPES_COLUMN_INTERFACETYPE_NAME).setCellValue(helper.getCreationHelper().createRichTextString(ift.getName()));

			i++;
		}
	}

	/**
	 * Creates the data sheet for Element Definition and populates it with the data
	 * @param exportSei Structural element instance to be exported
	 * @author  Bell_er
	 * 
	 */
	private void createDataSheetInterfaceEnds(StructuralElementInstance exportSei) {
		XSSFSheet sheet = createSheetIfNeeded(AExcelIo.TEMPLATE_SHEETNAME_INTERFACEENDS);
		// get all the interface ends
		BeanCategoryAssignmentHelper bCaHelper = new BeanCategoryAssignmentHelper();
		List<InterfaceEnd> seiInterfaceEnds = bCaHelper.getAllBeanCategories(exportSei, InterfaceEnd.class);
		helper.nullChecker(seiInterfaceEnds.size() + AExcelIo.COMMON_ROW_START_TABLE, sheet, AExcelIo.INTERFACEEND_COLUMN_INTERFACEEND_TYPE + 1);

		// for each interface end, fill out a row
		int i = AExcelIo.COMMON_ROW_START_TABLE;
		for (InterfaceEnd ife : seiInterfaceEnds) {
			Row row = sheet.getRow(i);
			row.getCell(AExcelIo.COMMON_COLUMN_UUID).setCellValue(helper.getCreationHelper().createRichTextString(ife.getTypeInstance().getUuid().toString()));
			row.getCell(AExcelIo.INTERFACEEND_COLUMN_INTERFACEEND_NAME).setCellValue(helper.getCreationHelper().createRichTextString(ife.getName()));
			row.getCell(AExcelIo.INTERFACEEND_COLUMN_INTERFACEEND_TYPE).setCellValue(helper.getCreationHelper().createRichTextString(""));
			row.getCell(AExcelIo.INTERFACEEND_COLUMN_INTERFACEEND_TYPE).setCellValue(helper.getCreationHelper().createRichTextString(getIfeTypeName(ife)));
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
	 * @author  Bell_er
	 * @param ife interface end object
	 * @return interface type name if exists else ""
	 */
	private String getIfeTypeName(InterfaceEnd ife) {
		InterfaceType ift = ife.getType();
		if (ift == null) {
			return "";
		} else {
			return Objects.toString(ift.getName(), "");
		}
	}
	
	/**
	 * gets the workbook from helper
	 * @return the workbook
	 */
	public XSSFWorkbook getWb() {
		return helper.getWb();
	}
}