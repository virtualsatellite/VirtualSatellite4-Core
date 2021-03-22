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

import java.util.List;
import java.util.Objects;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.eclipse.emf.ecore.EObject;

import de.dlr.sc.virsat.excel.fault.Fault;
import de.dlr.sc.virsat.excel.importer.ExcelImportHelper;
import de.dlr.sc.virsat.excel.importer.IImport;
import de.dlr.sc.virsat.model.concept.types.structural.BeanStructuralElementInstance;
import de.dlr.sc.virsat.model.concept.types.util.BeanCategoryAssignmentHelper;
import de.dlr.sc.virsat.model.dvlm.Repository;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.dvlm.concepts.util.ActiveConceptHelper;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import de.dlr.sc.virsat.model.extension.budget.cost.activator.Activator;
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
 * Class for Importing Excel files.
 */
public class CostImporter implements IImport {
	
	protected static final int ONE_HUNDRED = 100;

	private StructuralElementInstance sei;
	private XSSFWorkbook wb;
	private List<CostType> costTypes;
	private List<CostEquipment> seiCostEquipments;
	private List<CostType> costCTypes;
	private Concept concept;

	@Override
	public void importExcel(EObject eObject, Repository repository, XSSFWorkbook wb) {
		init(eObject, repository, wb);
		String exportSeiTypeName = sei.getType().getName();
		if (exportSeiTypeName.equals(CostTypesCollection.class.getSimpleName())) {
			importCostTypes();
		} else if (exportSeiTypeName.equals(ElementDefinition.class.getSimpleName())
				|| exportSeiTypeName.equals(ElementRealization.class.getSimpleName())) {
			importCostEquipments();
		} else if (exportSeiTypeName.equals(ElementConfiguration.class.getSimpleName())
				|| exportSeiTypeName.equals(ElementOccurence.class.getSimpleName())) {
			importCostEquipments();
		}
	}

	/**
	 * Acts like a constructor
	 * @param eObject StructuralElementInstance
	 * @param repository the repository
	 * @param wb XSSFWorkbook
	 */
	private void init(EObject eObject, Repository repository, XSSFWorkbook wb) {
		this.sei = (StructuralElementInstance) eObject;
		this.wb = wb;
		BeanCategoryAssignmentHelper bCaHelper = new BeanCategoryAssignmentHelper();
		CostArchitectureHelper costArchiHelper = new CostArchitectureHelper();
		ActiveConceptHelper acHelper = new ActiveConceptHelper(repository);
		concept = acHelper.getConcept(Activator.getPluginId());

		seiCostEquipments = bCaHelper.getAllBeanCategories(sei, CostEquipment.class);
		costCTypes = bCaHelper.getAllBeanCategories(sei, CostType.class);
		costTypes = costArchiHelper.getAllInterfaceTypes(repository);
	}

	/**
	* Imports the Cost Equipments to Element Configuration
	*/
	private void importCostEquipments() {
		BeanStructuralElementInstance beanSei = new BeanStructuralElementInstance(sei);

		final Sheet sheet = wb.getSheet(AExcelCostIO.TEMPLATE_SHEETNAME_COSTEQUIPMENTS);

		if (sheet == null) {
			return;
		}

		// go through each row to find out what to do
		for (int i = AExcelCostIO.COMMON_ROW_START_TABLE; i < sheet.getLastRowNum(); i++) {
			if (ExcelImportHelper.isEmpty(i, sheet, AExcelCostIO.COSTEQUIPMENT_COLUMN_COSTEQUIPMENT_TYPE + 1)) {
				continue;
			}

			Row row = sheet.getRow(i);
			// Get the UUID of the first cost equipment
			String tempUUID = Objects.toString(row.getCell(AExcelCostIO.COMMON_COLUMN_UUID), "");
			// Get the Cost of the first cost equipment
			Double tempCost = Double.parseDouble(row.getCell(AExcelCostIO.COSTEQUIPMENT_CULUMN_COSTEQUIPMENT_COST).toString());
			// Get the Margin of the first cost equipment
			Double tempMargin = Double.parseDouble(row.getCell(AExcelCostIO.COSTEQUIPMENT_CULUMN_COSTEQUIPMENT_MARGIN).toString());
			// Updated the CostMargin
			Double tempCostMargin = (tempCost / ONE_HUNDRED) * tempMargin;
			// Update the CostWithMargin
			Double tempCostWithMargin = tempCost + tempCostMargin;
			// figure out if we are creating a new CostEquipment
			if ("".equals(tempUUID)) {
				CostEquipment costEquipment = new CostEquipment(concept);
				// change the name if it is not empty, if it is empty throw a fault
				costEquipment.setName(row.getCell(AExcelCostIO.COSTEQUIPMENT_COLUMN_COSTEQUIPMENT_NAME).toString());
				// change the cost if it is not empty, if it is empty throw a fault
				costEquipment.setCost(tempCost);
				// change the margin if it is not empty, if it is empty throw a fault
				costEquipment.setMargin(tempMargin);
				// change the cost margin if it is not empty, if it is empty throw a fault
				costEquipment.setCostMargin(tempCostMargin);
				// change the cost with margin if it is not empty, if it is empty throw a fault
				costEquipment.setCostWithMargin(tempCostWithMargin);
				// if costType exists, set it, if it does not exist throw a fault
				int interfaceTypeIndex = ExcelImportHelper.containsABeanCategoryAssignmentName(row.getCell(AExcelCostIO.COSTEQUIPMENT_COLUMN_COSTEQUIPMENT_TYPE).toString(), costTypes);
				costEquipment.setType(costTypes.get(interfaceTypeIndex));
				beanSei.add(costEquipment);
			} else {
				int check = ExcelImportHelper.containsABeanCategoryAssignmentUUID(tempUUID, seiCostEquipments);
				// Control the delete column if element is deleted move to the next row
				String tempDelete = Objects.toString(row.getCell(AExcelCostIO.COMMON_COLUMN_DELETE), "");
				if (tempDelete.contains(AExcelCostIO.COMMON_DELETEMARK_VALUE)) {
					beanSei.remove(seiCostEquipments.get(check));
				} else {
					// change the name if it is not empty , if it is empty throw a fault
					String tempCostEquipmentName = Objects.toString(row.getCell(AExcelCostIO.COSTEQUIPMENT_COLUMN_COSTEQUIPMENT_NAME).toString(), "");
					seiCostEquipments.get(check).setName(tempCostEquipmentName);
					// change the cost if it is not empty , if it is empty throw a fault
					seiCostEquipments.get(check).setCost(tempCost);
					// change the margin if it is not empty , if it is empty throw a fault
					seiCostEquipments.get(check).setMargin(tempMargin);
					// change the cost margin if it is not empty , if it is empty throw a fault
					seiCostEquipments.get(check).setCostMargin(tempCostMargin);
					// change the cost with margin if it is not empty , if it is empty throw a fault
					seiCostEquipments.get(check).setCostWithMargin(tempCostWithMargin);
					// if type exists change the type, if not return a fault
					int costTypeIndex = ExcelImportHelper.containsABeanCategoryAssignmentName(row.getCell(AExcelCostIO.COSTEQUIPMENT_COLUMN_COSTEQUIPMENT_TYPE).toString(), costTypes);
					CostType costType = (costTypeIndex >= 0) ? costTypes.get(costTypeIndex) : null;
					seiCostEquipments.get(check).setType(costType);
				}
			}
		}
	}

	/**
	* Imports the Cost Type Collection
	*/
	private void importCostTypes() {
		final Sheet sheet = wb.getSheet(AExcelCostIO.TEMPLATE_SHEETNAME_COSTTYPES);

		if (sheet == null) {
			return;
		}
		// set our root element to a cost type collection
		CostTypesCollection costTypeCollection = new CostTypesCollection(sei);
		// go through each row to find out what to do
		for (int i = AExcelCostIO.COMMON_ROW_START_TABLE; i < sheet.getLastRowNum(); i++) {
			if (ExcelImportHelper.isEmpty(i, sheet, AExcelCostIO.COSTTYPES_COLUMN_COSTTYPE_NAME + 1)) {
				continue;
			}
			Row row = sheet.getRow(i);
			// Get the UUID of the first cost type
			String tempUUID = Objects.toString(row.getCell(AExcelCostIO.COMMON_COLUMN_UUID), "");
			// figure out if we are creating a new cost type
			if ("".equals(tempUUID)) {
				//create new cost type and add it to our cost type collection
				CostType costTyp = new CostType(concept);
				costTyp.setName(row.getCell(AExcelCostIO.COSTTYPES_COLUMN_COSTTYPE_NAME).toString());
				costTypeCollection.add(costTyp);
				// creation is done, continue the import with the next row in excel
			} else {
				int check = ExcelImportHelper.containsABeanCategoryAssignmentUUID(tempUUID, costCTypes);
				// Control the delete column if element is deleted move to the next row
				String tempDelete = Objects.toString(row.getCell(AExcelCostIO.COMMON_COLUMN_DELETE), "");
				if (tempDelete.contains(AExcelCostIO.COMMON_DELETEMARK_VALUE)) {
					costTypeCollection.remove(costCTypes.get(check));
				} else {
					// Change the CostTypeName by controlling if it is empty or not
					String tempCostType = Objects.toString(row.getCell(AExcelCostIO.COSTTYPES_COLUMN_COSTTYPE_NAME), "");
					costCTypes.get(check).setName(tempCostType);
				}
			}
		}
	}

	@Override
	public boolean canImport(EObject object) {
		return object instanceof StructuralElementInstance;
	}

	@Override
	public List<Fault> validate(EObject object, XSSFWorkbook wb) {
		ImportValidator iValidator = new ImportValidator(object, wb);
		return iValidator.validate();
	}
}
