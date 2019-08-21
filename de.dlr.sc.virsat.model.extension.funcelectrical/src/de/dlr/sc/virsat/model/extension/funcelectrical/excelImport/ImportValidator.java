/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.funcelectrical.excelImport;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.eclipse.emf.ecore.EObject;
import de.dlr.sc.virsat.model.concept.types.util.BeanCategoryAssignmentHelper;
import de.dlr.sc.virsat.model.dvlm.Repository;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import de.dlr.sc.virsat.model.dvlm.structural.util.StructuralElementInstanceHelper;
import de.dlr.sc.virsat.model.extension.funcelectrical.model.Interface;
import de.dlr.sc.virsat.model.extension.funcelectrical.model.InterfaceEnd;
import de.dlr.sc.virsat.model.extension.funcelectrical.model.InterfaceType;
import de.dlr.sc.virsat.model.extension.funcelectrical.model.InterfaceTypeCollection;
import de.dlr.sc.virsat.model.extension.funcelectrical.model.util.FuncElectricalArchitectureHelper;
import de.dlr.sc.virsat.model.extension.ps.model.ElementConfiguration;
import de.dlr.sc.virsat.model.extension.ps.model.ElementDefinition;
import de.dlr.sc.virsat.model.extension.ps.model.ElementOccurence;
import de.dlr.sc.virsat.model.extension.ps.model.ElementRealization;
import de.dlr.sc.virsat.project.editingDomain.VirSatEditingDomainRegistry;
import de.dlr.sc.virsat.project.editingDomain.VirSatTransactionalEditingDomain;





/**
 * Class for checking the validity of an excel file before importing it. 
 * @author bell_er
 *
 */
public class ImportValidator {

	// attributes
	private XSSFWorkbook wb;
	private VirSatTransactionalEditingDomain domain;
	private Repository repository;
	private StructuralElementInstance importSei;
	private List<InterfaceType> itcTypes;
	private List<Interface> ifaces;
	private BeanCategoryAssignmentHelper bCaHelper;
	private FuncElectricalArchitectureHelper feaHelper;
	private StructuralElementInstanceHelper seiHelper;
	private List<InterfaceType> ifTypes;
	private List<InterfaceEnd> seiInterfaceEnds;
	private List<InterfaceEnd> ecInterfaceEnds;
	private List<Fault> faultList;

	/**
	 * Create a new validator
	 * 
	 * @param object
	 *            will be used as the root object for integrating the data
	 * @param wb
	 *            the excel file

	 */
	public ImportValidator(EObject object, XSSFWorkbook wb) {
		if (object instanceof StructuralElementInstance) {
			importSei = (StructuralElementInstance) object;
		}
		this.domain = VirSatEditingDomainRegistry.INSTANCE.getEd(importSei);
		this.wb = wb;
		bCaHelper = new BeanCategoryAssignmentHelper();
		feaHelper = new FuncElectricalArchitectureHelper();
		repository = this.domain.getResourceSet().getRepository();
		seiHelper = new StructuralElementInstanceHelper(importSei);
		seiInterfaceEnds = bCaHelper.getAllBeanCategories(importSei, InterfaceEnd.class);
		itcTypes = bCaHelper.getAllBeanCategories(importSei, InterfaceType.class);
		ifTypes = feaHelper.getAllInterfaceTypes(repository);
		faultList = new ArrayList<Fault>();
		ifaces = bCaHelper.getAllBeanCategories(importSei, Interface.class);
		ecInterfaceEnds =  bCaHelper.getAllBeanCategoriesFromRoot(seiHelper.getRoot(), InterfaceEnd.class);
	}	
	
	/**
	 * Validates depending on the type of the Structural element
	 * 
	 * @author  Bell_er
	 * @return faultList the list of faults
	 * 
	 */
	public List<Fault> validate() {	
		String importSeiTypeName = importSei.getType().getName();
		if (importSeiTypeName.equals(InterfaceTypeCollection.class.getSimpleName())) {
			validateHeaders();
			validateIntefaceTypes();
		} else if (importSeiTypeName.equals(ElementDefinition.class.getSimpleName())
				|| importSeiTypeName.equals(ElementRealization.class.getSimpleName())) {
			validateHeaders();
			validateInterfaceEnds();
		} else if (importSeiTypeName.equals(ElementConfiguration.class.getSimpleName())
				|| importSeiTypeName.equals(ElementOccurence.class.getSimpleName())) {
			validateHeaders();
			validateInterfaces();
			validateInterfaceEnds();
		} else {
			faultList.add(new Fault(FaultType.CAN_ONLY_IMPORT_ELEMENT_DEFINITON_OR_INTERFACE_TYPE_COLLECTION, -1, -1));
		}
		
		return faultList;
	}

	
	/**
	 * Validates the interface ends  when importing in element configuration or element definition
	 * 
	 * @author  Bell_er
	 */
	private void validateInterfaceEnds() {
		
		final XSSFSheet sheet = wb.getSheet(AExcelIo.TEMPLATE_SHEETNAME_INTERFACEENDS);
		
		if (sheet == null) {
			return;
		}
		
		final int sheetIndex = wb.getSheetIndex(AExcelIo.TEMPLATE_SHEETNAME_INTERFACEENDS);
		
		// Travel through all rows to find a fault
		for (int i = AExcelIo.COMMON_ROW_START_TABLE; i < sheet.getLastRowNum(); i++) {
			// if the row is empty move on the next rows
			if (ExcelImportHelper.isEmpty(i, sheet, AExcelIo.INTERFACEEND_COLUMN_INTERFACEEND_TYPE + 1)) {
				continue;
			}
			Row row = sheet.getRow(i);
			String tempUUID =   Objects.toString(row.getCell(AExcelIo.COMMON_COLUMN_UUID), "");
			String tempDelete = Objects.toString(row.getCell(AExcelIo.COMMON_COLUMN_DELETE), "");
			if ("".equals(tempUUID)) {
				if (tempDelete.equals(AExcelIo.COMMON_DELETEMARK_VALUE)) {
					faultList.add(new Fault(FaultType.CANT_DELETE_NON_EXISTING_INTERFACE_END, sheetIndex, i));
				}
			} else {
				int check = ExcelImportHelper.containsABeanCategoryAssignmentUUID(tempUUID, seiInterfaceEnds);
				if (check < 0) { 
					faultList.add(new Fault(FaultType.INTERFACE_END_UUID_NOT_FOUND, sheetIndex, i));
				}
			}
			// control the delete column, value of this column can be 1 or nothing			
			if (!(tempDelete.equals(AExcelIo.COMMON_DELETEMARK_VALUE) || tempDelete.equals(""))) {
				faultList.add(new Fault(FaultType.DELETE_COLUMN_CAN_BE_EMPTY_OR_1, sheetIndex, i));
			}
			//Interface_end_name cannot be null
			String tempInterfaceEndName =  Objects.toString(row.getCell(AExcelIo.INTERFACEEND_COLUMN_INTERFACEEND_NAME), "");
			if ("".equals(tempInterfaceEndName)) {
				faultList.add(new Fault(FaultType.INTERFACE_END_NAME_IS_NOT_SET, sheetIndex, i));
			} 
			// control if the interface Type Exists
			String type  = Objects.toString(row.getCell(AExcelIo.INTERFACEEND_COLUMN_INTERFACEEND_TYPE), "");
			int interfaceTypeIndex = ExcelImportHelper.containsABeanCategoryAssignmentName(type, ifTypes);
			if (interfaceTypeIndex < 0) {
				faultList.add(new Fault(FaultType.INTERFACE_TYPE_DOES_NOT_EXIST, sheetIndex, i));
			}
		}
	}

	/**
	 * Validates the interfaces when importing in element configuration
	 * 
	 * @author  Bell_er
	 */
	private void validateInterfaces() {
		final XSSFSheet sheet = wb.getSheet(AExcelIo.TEMPLATE_SHEETNAME_INTERFACES);
		
		if (sheet == null) {
			return;
		}
		final int sheetIndex = wb.getSheetIndex(AExcelIo.TEMPLATE_SHEETNAME_INTERFACES);

		// Travel through all rows to find a fault
		for (int i = AExcelIo.COMMON_ROW_START_TABLE; i < sheet.getLastRowNum(); i++) {
			// if the row is empty move on the next rows
			if (ExcelImportHelper.isEmpty(i, sheet, AExcelIo.INTERFACE_COLUMN_INTERFACE_TO + 1)) {
				continue;
			}
			Row row = sheet.getRow(i);
			String tempUUID = Objects.toString(row.getCell(AExcelIo.COMMON_COLUMN_UUID), "");
			String tempDelete = Objects.toString(row.getCell(AExcelIo.COMMON_COLUMN_DELETE), "");
			if ("".equals(tempUUID)) {

				if (tempDelete.equals(AExcelIo.COMMON_DELETEMARK_VALUE)) {
					faultList.add(new Fault(FaultType.CANT_DELETE_NON_EXISTING_INTERFACE, sheetIndex, i));
				}
			} else {
				int check = ExcelImportHelper.containsABeanCategoryAssignmentUUID(tempUUID, ifaces);
				if (check < 0) { 
					faultList.add(new Fault(FaultType.INTERFACE_UUID_NOT_FOUND, sheetIndex, i));
				}
			}
			// control the delete column, value of this column can be 1 or nothing	
			if (!(tempDelete.equals(AExcelIo.COMMON_DELETEMARK_VALUE) || tempDelete.equals(""))) {
				faultList.add(new Fault(FaultType.DELETE_COLUMN_CAN_BE_EMPTY_OR_1, sheetIndex, i));
			}
			// Control if the interfaceType is empty or not
			String tempInterfaceName = Objects.toString(row.getCell(AExcelIo.INTERFACE_COLUMN_INTERFACE_NAME), "");
			if ("".equals(tempInterfaceName)) {
				faultList.add(new Fault(FaultType.INTERFACE_NAME_IS_NOT_SET, sheetIndex, i));
			}
			// Control if the FromInterfaceEnd does exist or not 
			String tempInterfaceFrom = Objects.toString(row.getCell(AExcelIo.INTERFACE_COLUMN_INTERFACE_FROM), "");
			int check = ExcelImportHelper.containsABeanCategoryAssignmentFullQualifiedInstanceName(tempInterfaceFrom, ecInterfaceEnds); 
			if (check < 0) { 
				faultList.add(new Fault(FaultType.FROM_INTERFACE_END_NOT_FOUND, sheetIndex, i));
			}
			// Control if the ToInterfaceEnd does exist or not 
			String tempInterfaceTo = Objects.toString(row.getCell(AExcelIo.INTERFACE_COLUMN_INTERFACE_TO), "");
			check = ExcelImportHelper.containsABeanCategoryAssignmentFullQualifiedInstanceName(tempInterfaceTo, ecInterfaceEnds); 
			if (check < 0) { 
				faultList.add(new Fault(FaultType.TO_INTERFACE_END_NOT_FOUND, sheetIndex, i));
			}
		}
	}
	/**
	 * Validates the Interface Type Collection
	 * 
	 * @author  Bell_er
	 */
	public void validateIntefaceTypes() {
		final XSSFSheet sheet = wb.getSheet(AExcelIo.TEMPLATE_SHEETNAME_INTERFACETYPES);
		
		if (sheet == null) {
			return;
		}
		
		final int sheetIndex = wb.getSheetIndex(AExcelIo.TEMPLATE_SHEETNAME_INTERFACETYPES);
		
		// Travel through all rows to find a fault
		for (int i = AExcelIo.COMMON_ROW_START_TABLE; i < sheet.getLastRowNum(); i++) {
			// if the row is empty move on the next rows
			if (ExcelImportHelper.isEmpty(i, sheet, AExcelIo.INTERFACETYPES_COLUMN_INTERFACETYPE_NAME + 1)) {
				continue;
			}
			Row row = sheet.getRow(i);
			// Get the UUID of the first element in excel
			String tempUUID = Objects.toString(row.getCell(AExcelIo.COMMON_COLUMN_UUID), "");
			String tempDelete = Objects.toString(row.getCell(AExcelIo.COMMON_COLUMN_DELETE), "");
			if ("".equals(tempUUID)) {
				if (tempDelete.equals(AExcelIo.COMMON_DELETEMARK_VALUE)) {
					faultList.add(new Fault(FaultType.CANT_DELETE_NON_EXISTING_INTERFACE_TYPE, sheetIndex, i));
				}
			} else {
				int check = ExcelImportHelper.containsABeanCategoryAssignmentUUID(tempUUID, itcTypes);
				if (check < 0) { 
					faultList.add(new Fault(FaultType.INTERFACE_TYPE_UUID_NOT_FOUND, sheetIndex, i));
				}
			}
			// control the delete column, value of this column can be 1 or nothing
			if (!(tempDelete.equals(AExcelIo.COMMON_DELETEMARK_VALUE) || tempDelete.equals(""))) {
				faultList.add(new Fault(FaultType.DELETE_COLUMN_CAN_BE_EMPTY_OR_1, sheetIndex, i));
			}
			// Control if the interfaceType is empty or not
			String tempInterfaceType = Objects.toString(row.getCell(AExcelIo.INTERFACETYPES_COLUMN_INTERFACETYPE_NAME), "");
			if ("".equals(tempInterfaceType)) {
				faultList.add(new Fault(FaultType.INTERFACE_TYPE_NAME_IS_NOT_SET, sheetIndex, i));
			}
		}
	}	
	/**
	 * Validates the Header Pages for all
	 * 
	 * @author  Bell_er
	 */
	public void validateHeaders() {
		
		final XSSFSheet sheet = wb.getSheet(AExcelIo.TEMPLATE_SHEETNAME_HEADER);
		final int sheetIndex = wb.getSheetIndex(AExcelIo.TEMPLATE_SHEETNAME_HEADER);
		
		// Control if we are importing the correct Structural element by comparing UUIDs
		String tempUUID =  Objects.toString(sheet.getRow(AExcelIo.HEADER_ROW_STRUCTURALELEMENTUUID).getCell(1), "");
		if (!(importSei.getUuid().toString().equals(tempUUID))) {
			faultList.add(new Fault(FaultType.STRUCTURAL_ELEMENT_UUIDS_DO_NOT_MATCH, sheetIndex, AExcelIo.HEADER_ROW_STRUCTURALELEMENTUUID));
		}
		// Control if we are importing the correct Structural element by comparing NAMEs
		String tempName = Objects.toString(sheet.getRow(AExcelIo.HEADER_ROW_STRUCTURALELEMENTNAME).getCell(1), ""); 
		if (!(importSei.getName().toString().equals(tempName))) {
			faultList.add(new Fault(FaultType.STRUCTURAL_ELEMENT_NAMES_DO_NOT_MATCH, sheetIndex, AExcelIo.HEADER_ROW_STRUCTURALELEMENTNAME));
		}
	}
}

