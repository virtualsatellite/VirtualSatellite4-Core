/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.funcelectrical.excel.importer;

import java.util.List;
import java.util.Objects;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.eclipse.emf.ecore.EObject;

import de.dlr.sc.virsat.excel.AExcelIo;
import de.dlr.sc.virsat.excel.fault.Fault;
import de.dlr.sc.virsat.excel.importer.ExcelImportHelper;
import de.dlr.sc.virsat.excel.importer.IImport;
import de.dlr.sc.virsat.model.concept.types.structural.BeanStructuralElementInstance;
import de.dlr.sc.virsat.model.concept.types.util.BeanCategoryAssignmentHelper;
import de.dlr.sc.virsat.model.dvlm.Repository;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.dvlm.concepts.util.ActiveConceptHelper;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import de.dlr.sc.virsat.model.dvlm.structural.util.StructuralElementInstanceHelper;
import de.dlr.sc.virsat.model.extension.funcelectrical.Activator;
import de.dlr.sc.virsat.model.extension.funcelectrical.model.Interface;
import de.dlr.sc.virsat.model.extension.funcelectrical.model.InterfaceEnd;
import de.dlr.sc.virsat.model.extension.funcelectrical.model.InterfaceType;
import de.dlr.sc.virsat.model.extension.funcelectrical.model.InterfaceTypeCollection;
import de.dlr.sc.virsat.model.extension.funcelectrical.model.util.FuncElectricalArchitectureHelper;
import de.dlr.sc.virsat.model.extension.ps.model.ElementConfiguration;
import de.dlr.sc.virsat.model.extension.ps.model.ElementDefinition;
import de.dlr.sc.virsat.model.extension.ps.model.ElementOccurence;
import de.dlr.sc.virsat.model.extension.ps.model.ElementRealization;




/**
 * Class for Importing Excel files.
 * 
 * @author bell_er
 *
 */
public class FuncElecImporter implements IImport {

	private StructuralElementInstance sc;
	private XSSFWorkbook wb;
	private List<InterfaceType> ifTypes;
	private List<InterfaceEnd> seiInterfaceEnds;
	private List<InterfaceEnd> ecInterfaceEnds;
	private List<InterfaceType> itcTypes;
	private List<Interface> ifaces;
	private Concept concept;

	@Override
	public void importExcel(EObject eObject, Repository repository, XSSFWorkbook wb) {
		init(eObject, repository, wb);
		// !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		// On import and export we should decide on
		// applicability if we should export or
		// import a certain type
		// This needs to be refactored
		// !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		String exportSeiTypeName = sc.getType().getName();

		if (exportSeiTypeName.equals(InterfaceTypeCollection.class.getSimpleName())) {
			importInterfaceTypes();
		} else if (exportSeiTypeName.equals(ElementDefinition.class.getSimpleName())
				|| exportSeiTypeName.equals(ElementRealization.class.getSimpleName())) {
			importInterfaceEnds();
		} else if (exportSeiTypeName.equals(ElementConfiguration.class.getSimpleName())
				|| exportSeiTypeName.equals(ElementOccurence.class.getSimpleName())) {
			importInterfaceEnds();
			importInterfaces();
		}

	}
	/**
	 * Acts like a constructor
	 * @param eObject StructuralElementInstance
	 * @param repository the repository
	 * @param wb XSSFWorkbook
	 *
	 */
	private void init(EObject eObject, Repository repository, XSSFWorkbook wb) {
		this.sc = (StructuralElementInstance) eObject;
		this.wb = wb;
		BeanCategoryAssignmentHelper bCaHelper = new BeanCategoryAssignmentHelper();
		FuncElectricalArchitectureHelper feaHelper = new FuncElectricalArchitectureHelper();
		StructuralElementInstanceHelper seiHelper = new StructuralElementInstanceHelper(sc);
		ActiveConceptHelper acHelper = new ActiveConceptHelper(repository);
		concept = acHelper.getConcept(Activator.getPluginId());
		
		seiInterfaceEnds = bCaHelper.getAllBeanCategories(sc, InterfaceEnd.class);
		itcTypes = bCaHelper.getAllBeanCategories(sc, InterfaceType.class);	
		ifTypes = feaHelper.getAllInterfaceTypes(repository);	
		ifaces = bCaHelper.getAllBeanCategories(sc, Interface.class);
		ecInterfaceEnds =  bCaHelper.getAllBeanCategoriesFromRoot(seiHelper.getRoot(), InterfaceEnd.class);
	}
	
	/**
	* Imports the interface Ends to Element Configuration
	* 
	* 
	* @author  Bell_er
	*/
	private void importInterfaceEnds() {
		BeanStructuralElementInstance ec = new BeanStructuralElementInstance(sc);
		
		final Sheet sheet = wb.getSheet(AExcelIo.TEMPLATE_SHEETNAME_INTERFACEENDS);
		
		if (sheet == null) {
			return;
		}
		
		// go through each row to find out what to do
		for (int i = AExcelIo.COMMON_ROW_START_TABLE; i < sheet.getLastRowNum(); i++) {
			if (ExcelImportHelper.isEmpty(i, sheet, AExcelIo.INTERFACEEND_COLUMN_INTERFACEEND_TYPE + 1)) {
				continue;
			}
			
			Row row = sheet.getRow(i);
			// Get the UUID of the first interface end
			String tempUUID =   Objects.toString(row.getCell(AExcelIo.COMMON_COLUMN_UUID), "");
			// figure out if we are creating a new InterfaceEnd
			if ("".equals(tempUUID)) {
				InterfaceEnd ie = new InterfaceEnd(concept);
				// change the name if it is not empty , if it is empty throw a fault
				ie.setName(row.getCell(AExcelIo.INTERFACEEND_COLUMN_INTERFACEEND_NAME).toString());			
				// if interfaceType exists, set it, if it does not exist throw a fault
				int interfaceTypeIndex = ExcelImportHelper.containsABeanCategoryAssignmentName(row.getCell(AExcelIo.INTERFACEEND_COLUMN_INTERFACEEND_TYPE).toString(), ifTypes);
				ie.setType(ifTypes.get(interfaceTypeIndex));				
				ec.add(ie);
			} else {
				int check = ExcelImportHelper.containsABeanCategoryAssignmentUUID(tempUUID, seiInterfaceEnds);
				// Control the delete column if element is deleted move to the next row
				String tempDelete = Objects.toString(row.getCell(AExcelIo.COMMON_COLUMN_DELETE), "");
				if (tempDelete.contains(AExcelIo.COMMON_DELETEMARK_VALUE)) {
					ec.remove(seiInterfaceEnds.get(check));
				} else {
					// change the name if it is not empty , if it is empty throw a fault
					String tempInterfaceEndName = Objects.toString(row.getCell(AExcelIo.INTERFACEEND_COLUMN_INTERFACEEND_NAME).toString(), "");				
					seiInterfaceEnds.get(check).setName(tempInterfaceEndName);
					// if type exists change the type, if not return a fault
					int interfaceTypeIndex = ExcelImportHelper.containsABeanCategoryAssignmentName(row.getCell(AExcelIo.INTERFACEEND_COLUMN_INTERFACEEND_TYPE).toString(), ifTypes);
					seiInterfaceEnds.get(check).setType(ifTypes.get(interfaceTypeIndex));
				}
			}
		}
	}

	/**
	* Imports the interfaces to Element Configuration
	* 
	* 
	* @author  Bell_er
	*/
	private void importInterfaces() {		
		final Sheet sheet = wb.getSheet(AExcelIo.TEMPLATE_SHEETNAME_INTERFACES);
		
		if (sheet == null) {
			return;
		}
		// go through each row to find out what to do
		for (int i = AExcelIo.COMMON_ROW_START_TABLE; i < sheet.getLastRowNum(); i++) {
			if (ExcelImportHelper.isEmpty(i, sheet, AExcelIo.INTERFACE_COLUMN_INTERFACE_TO + 1)) {
				continue;
			} 


			Row row = sheet.getRow(i);
			// set our root element to a element configuration
			BeanStructuralElementInstance ec = new BeanStructuralElementInstance(sc);
			// Get the UUID of the first interface 
			String tempUUID = Objects.toString(row.getCell(AExcelIo.COMMON_COLUMN_UUID), "");
			// figure out if we are creating a new interface type
			if ("".equals(tempUUID)) {
				//create new interface and add it to our interface collection
				Interface iface = new Interface(concept);
				iface.setName(row.getCell(AExcelIo.INTERFACE_COLUMN_INTERFACE_NAME).toString());
				int check = ExcelImportHelper.containsABeanCategoryAssignmentFullQualifiedInstanceName(row.getCell(AExcelIo.INTERFACE_COLUMN_INTERFACE_FROM).toString(), ecInterfaceEnds);
				iface.setInterfaceEndFrom(ecInterfaceEnds.get(check));
				check = ExcelImportHelper.containsABeanCategoryAssignmentFullQualifiedInstanceName(row.getCell(AExcelIo.INTERFACE_COLUMN_INTERFACE_TO).toString(), ecInterfaceEnds);
				iface.setInterfaceEndTo(ecInterfaceEnds.get(check));
				ec.add(iface);
				// creation is done, continue the import with the next row in excel
			} else {
				// Control the delete column if element is deleted move to the next row
				int check = ExcelImportHelper.containsABeanCategoryAssignmentUUID(tempUUID, ifaces);
				String tempDelete = Objects.toString(row.getCell(AExcelIo.COMMON_COLUMN_DELETE), "");
				if (tempDelete.toString().contains(AExcelIo.COMMON_DELETEMARK_VALUE)) {
					ec.remove(ifaces.get(check));
				} else {
					// Change the InterfaceName by controlling if it is empty or not
					String tempInterfaceName = Objects.toString(row.getCell(AExcelIo.INTERFACE_COLUMN_INTERFACE_NAME), "");
					ifaces.get(check).setName(tempInterfaceName);	
					// Change the From interface end 
					String tempInterfaceFrom = Objects.toString(row.getCell(AExcelIo.INTERFACE_COLUMN_INTERFACE_FROM), "");
					int check2 = ExcelImportHelper.containsABeanCategoryAssignmentFullQualifiedInstanceName(tempInterfaceFrom, ecInterfaceEnds);
					ifaces.get(check).setInterfaceEndFrom(ecInterfaceEnds.get(check2));
					// Change the To interface end 
					String tempInterfaceTo = Objects.toString(row.getCell(AExcelIo.INTERFACE_COLUMN_INTERFACE_TO), "");
					check2 = ExcelImportHelper.containsABeanCategoryAssignmentFullQualifiedInstanceName(tempInterfaceTo, ecInterfaceEnds);
					ifaces.get(check).setInterfaceEndTo(ecInterfaceEnds.get(check2));
				}
			}
		}
	}
	
	/**
	* Imports the Interface Type Collection
	* 
	* @author  Bell_er
	*/
	private void importInterfaceTypes() {
		final Sheet sheet = wb.getSheet(AExcelIo.TEMPLATE_SHEETNAME_INTERFACETYPES);
		
		if (sheet == null) {
			return;
		}
		// set our root element to a interface type collection
		InterfaceTypeCollection itc = new InterfaceTypeCollection(sc);	
		// go through each row to find out what to do
		for (int i = AExcelIo.COMMON_ROW_START_TABLE; i < sheet.getLastRowNum(); i++) {
			if (ExcelImportHelper.isEmpty(i, sheet, AExcelIo.INTERFACETYPES_COLUMN_INTERFACETYPE_NAME + 1)) {
				continue;
			}
			Row row = sheet.getRow(i);
			// Get the UUID of the first interface type
			String tempUUID = Objects.toString(row.getCell(AExcelIo.COMMON_COLUMN_UUID), "");
			// figure out if we are creating a new interface type
			if ("".equals(tempUUID)) {
				//create new interface type and add it to our interface type collection
				InterfaceType it = new InterfaceType(concept);
				it.setName(row.getCell(AExcelIo.INTERFACETYPES_COLUMN_INTERFACETYPE_NAME).toString());
				itc.add(it);
				// creation is done, continue the import with the next row in excel
			} else {		
				int check = ExcelImportHelper.containsABeanCategoryAssignmentUUID(tempUUID, itcTypes);
				// Control the delete column if element is deleted move to the next row
				String tempDelete = Objects.toString(row.getCell(AExcelIo.COMMON_COLUMN_DELETE), "");
				if (tempDelete.contains(AExcelIo.COMMON_DELETEMARK_VALUE)) {
					itc.remove(itcTypes.get(check));
				} else {
					// Change the InterfaceTypeName by controlling if it is empty or not
					String tempInterfaceType = Objects.toString(row.getCell(AExcelIo.INTERFACETYPES_COLUMN_INTERFACETYPE_NAME), "");
					itcTypes.get(check).setName(tempInterfaceType);	
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
		ImportValidator iv = new ImportValidator(object, wb);
		return iv.validate();
	}
}

