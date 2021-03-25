/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.budget.cost.excel.importer;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.eclipse.emf.ecore.EObject;

import de.dlr.sc.virsat.excel.fault.Fault;
import de.dlr.sc.virsat.excel.fault.FaultType;
import de.dlr.sc.virsat.excel.importer.ExcelImportHelper;
import de.dlr.sc.virsat.model.concept.types.util.BeanCategoryAssignmentHelper;
import de.dlr.sc.virsat.model.dvlm.Repository;
import de.dlr.sc.virsat.model.dvlm.concepts.util.ActiveConceptHelper;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import de.dlr.sc.virsat.model.extension.budget.cost.excel.AExcelCostIO;
import de.dlr.sc.virsat.model.extension.budget.cost.model.CostEquipment;
import de.dlr.sc.virsat.model.extension.budget.cost.model.CostType;
import de.dlr.sc.virsat.model.extension.budget.cost.model.CostTypesCollection;
import de.dlr.sc.virsat.model.extension.budget.cost.model.util.CostArchitectureHelper;
import de.dlr.sc.virsat.model.extension.ps.model.ElementConfiguration;
import de.dlr.sc.virsat.model.extension.ps.model.ElementDefinition;
import de.dlr.sc.virsat.model.extension.ps.model.ElementOccurence;
import de.dlr.sc.virsat.model.extension.ps.model.ElementRealization;

/**
 * Class for checking the validity of an excel file before importing it.
 */
public class ImportValidator {

	// attributes
	private XSSFWorkbook wb;
	private StructuralElementInstance importSei;
	private List<CostType> itcTypes;
	private List<CostType> costTypes;
	private List<CostEquipment> seiCostEquipments;
	private List<Fault> faultList;

	/**
	 * Create a new validator
	 * @param object will be used as the root object for integrating the data
	 * @param wb the excel file
	 */
	public ImportValidator(EObject object, XSSFWorkbook wb) {
		if (object instanceof StructuralElementInstance) {
			importSei = (StructuralElementInstance) object;
		}

		this.wb = wb;
		BeanCategoryAssignmentHelper bCaHelper = new BeanCategoryAssignmentHelper();
		CostArchitectureHelper costArchiHelper = new CostArchitectureHelper();
		seiCostEquipments = bCaHelper.getAllBeanCategories(importSei, CostEquipment.class);
		itcTypes = bCaHelper.getAllBeanCategories(importSei, CostType.class);
		// Grab the repository using the concept
		Repository repository = (Repository) ActiveConceptHelper.getConcept(importSei.getType()).eContainer();
		costTypes = costArchiHelper.getAllInterfaceTypes(repository);
		faultList = new ArrayList<Fault>();
	}

	/**
	 * Validates depending on the type of the Structural element
	 * @return faultList the list of faults
	 */
	public List<Fault> validate() {
		String importSeiTypeName = importSei.getType().getName();
		if (importSeiTypeName.equals(CostTypesCollection.class.getSimpleName())) {
			validateHeaders();
			validateCostTypes();
		} else if (importSeiTypeName.equals(ElementDefinition.class.getSimpleName())
				|| importSeiTypeName.equals(ElementRealization.class.getSimpleName())) {
			validateHeaders();
			validateCostEquipments();
		} else if (importSeiTypeName.equals(ElementConfiguration.class.getSimpleName())
				|| importSeiTypeName.equals(ElementOccurence.class.getSimpleName())) {
			validateHeaders();
			validateCostEquipments();
		} else {
			faultList.add(new Fault(CostFaultType.CAN_ONLY_IMPORT_ELEMENT_DEFINITON_OR_COST_TYPE_COLLECTION, -1, -1));
		}

		return faultList;
	}

	/**
	 * Validates the interface ends when importing in element configuration or element definition
	 */
	private void validateCostEquipments() {

		final XSSFSheet sheet = wb.getSheet(AExcelCostIO.TEMPLATE_SHEETNAME_COSTEQUIPMENTS);

		if (sheet == null) {
			return;
		}

		final int sheetIndex = wb.getSheetIndex(AExcelCostIO.TEMPLATE_SHEETNAME_COSTEQUIPMENTS);

		// Travel through all rows to find a fault
		for (int i = AExcelCostIO.COMMON_ROW_START_TABLE; i < sheet.getLastRowNum(); i++) {
			// if the row is empty move on the next rows
			if (ExcelImportHelper.isEmpty(i, sheet, AExcelCostIO.COSTEQUIPMENT_COLUMN_COSTEQUIPMENT_TYPE + 1)) {
				continue;
			}
			Row row = sheet.getRow(i);
			String tempUUID = Objects.toString(row.getCell(AExcelCostIO.COMMON_COLUMN_UUID), "");
			String tempDelete = Objects.toString(row.getCell(AExcelCostIO.COMMON_COLUMN_DELETE), "");
			if ("".equals(tempUUID)) {
				if (tempDelete.equals(AExcelCostIO.COMMON_DELETEMARK_VALUE)) {
					faultList.add(new Fault(CostFaultType.CANT_DELETE_NON_EXISTING_COST_EQUIPMENT, sheetIndex, i));
				}
			} else {
				int check = ExcelImportHelper.containsABeanCategoryAssignmentUUID(tempUUID, seiCostEquipments);
				if (check < 0) {
					faultList.add(new Fault(CostFaultType.COST_EQUIPMENT_UUID_NOT_FOUND, sheetIndex, i));
				}
			}
			// control the delete column, value of this column can be 1 or nothing
			if (!(tempDelete.equals(AExcelCostIO.COMMON_DELETEMARK_VALUE) || tempDelete.equals(""))) {
				faultList.add(new Fault(FaultType.DELETE_COLUMN_CAN_BE_EMPTY_OR_1, sheetIndex, i));
			}
			//Cost_Equipment_Name cannot be null
			String tempInterfaceEndName = Objects.toString(row.getCell(AExcelCostIO.COSTEQUIPMENT_COLUMN_COSTEQUIPMENT_NAME), "");
			if ("".equals(tempInterfaceEndName)) {
				faultList.add(new Fault(CostFaultType.COST_EQUIPMENT_NAME_IS_NOT_SET, sheetIndex, i));
			}
			// control if the interface Type Exists
			String type = Objects.toString(row.getCell(AExcelCostIO.COSTEQUIPMENT_COLUMN_COSTEQUIPMENT_TYPE), "");
			if (!type.equals("")) {
				int interfaceTypeIndex = ExcelImportHelper.containsABeanCategoryAssignmentName(type, costTypes);
				if (interfaceTypeIndex < 0) {
					faultList.add(new Fault(CostFaultType.COST_TYPE_DOES_NOT_EXIST, sheetIndex, i));
				}
			}
		}
	}

	/**
	 * Validates the Interface Type Collection
	 */
	public void validateCostTypes() {
		final XSSFSheet sheet = wb.getSheet(AExcelCostIO.TEMPLATE_SHEETNAME_COSTTYPES);

		if (sheet == null) {
			return;
		}

		final int sheetIndex = wb.getSheetIndex(AExcelCostIO.TEMPLATE_SHEETNAME_COSTTYPES);

		// Travel through all rows to find a fault
		for (int i = AExcelCostIO.COMMON_ROW_START_TABLE; i < sheet.getLastRowNum(); i++) {
			// if the row is empty move on the next rows
			if (ExcelImportHelper.isEmpty(i, sheet, AExcelCostIO.COSTTYPES_COLUMN_COSTTYPE_NAME + 1)) {
				continue;
			}
			Row row = sheet.getRow(i);
			// Get the UUID of the first element in excel
			String tempUUID = Objects.toString(row.getCell(AExcelCostIO.COMMON_COLUMN_UUID), "");
			String tempDelete = Objects.toString(row.getCell(AExcelCostIO.COMMON_COLUMN_DELETE), "");
			if ("".equals(tempUUID)) {
				if (tempDelete.equals(AExcelCostIO.COMMON_DELETEMARK_VALUE)) {
					faultList.add(new Fault(CostFaultType.CANT_DELETE_NON_EXISTING_COST_TYPE, sheetIndex, i));
				}
			} else {
				int check = ExcelImportHelper.containsABeanCategoryAssignmentUUID(tempUUID, itcTypes);
				if (check < 0) {
					faultList.add(new Fault(CostFaultType.COST_TYPE_UUID_NOT_FOUND, sheetIndex, i));
				}
			}
			// control the delete column, value of this column can be 1 or nothing
			if (!(tempDelete.equals(AExcelCostIO.COMMON_DELETEMARK_VALUE) || tempDelete.equals(""))) {
				faultList.add(new Fault(FaultType.DELETE_COLUMN_CAN_BE_EMPTY_OR_1, sheetIndex, i));
			}
			// Control if the interfaceType is empty or not
			String tempInterfaceType = Objects.toString(row.getCell(AExcelCostIO.COSTTYPES_COLUMN_COSTTYPE_NAME), "");
			if ("".equals(tempInterfaceType)) {
				faultList.add(new Fault(CostFaultType.COST_TYPE_NAME_IS_NOT_SET, sheetIndex, i));
			}
		}
	}

	/**
	 * Validates the Header Pages for all
	 */
	public void validateHeaders() {

		final XSSFSheet sheet = wb.getSheet(AExcelCostIO.TEMPLATE_SHEETNAME_HEADER);
		final int sheetIndex = wb.getSheetIndex(AExcelCostIO.TEMPLATE_SHEETNAME_HEADER);

		// Control if we are importing the correct Structural element by comparing UUIDs
		String tempUUID = Objects.toString(sheet.getRow(AExcelCostIO.HEADER_ROW_STRUCTURALELEMENT_UUID).getCell(1), "");
		if (!(importSei.getUuid().toString().equals(tempUUID))) {
			faultList.add(new Fault(FaultType.STRUCTURAL_ELEMENT_UUIDS_DO_NOT_MATCH, sheetIndex, AExcelCostIO.HEADER_ROW_STRUCTURALELEMENT_UUID));
		}
		// Control if we are importing the correct Structural element by comparing NAMEs
		String tempName = Objects.toString(sheet.getRow(AExcelCostIO.HEADER_ROW_STRUCTURALELEMENT_NAME).getCell(1), "");
		if (!(importSei.getName().toString().equals(tempName))) {
			faultList.add(new Fault(FaultType.STRUCTURAL_ELEMENT_NAMES_DO_NOT_MATCH, sheetIndex, AExcelCostIO.HEADER_ROW_STRUCTURALELEMENT_NAME));
		}
	}
}
