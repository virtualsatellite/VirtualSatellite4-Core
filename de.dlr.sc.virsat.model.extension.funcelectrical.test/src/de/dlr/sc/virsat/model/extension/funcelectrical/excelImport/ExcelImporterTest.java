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

import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.command.Command;
import org.junit.Before;
import org.junit.Test;

import de.dlr.sc.virsat.model.concept.types.util.BeanCategoryAssignmentHelper;
import de.dlr.sc.virsat.model.dvlm.types.impl.VirSatUuid;
import de.dlr.sc.virsat.model.extension.funcelectrical.Activator;
import de.dlr.sc.virsat.model.extension.funcelectrical.excelImport.FuncElecImporter;
import de.dlr.sc.virsat.model.extension.funcelectrical.model.InterfaceEnd;
import de.dlr.sc.virsat.model.extension.funcelectrical.test.ExcelTestCase;
import de.dlr.sc.virsat.project.structure.command.CreateAddSeiWithFileStructureCommand;


/**
 * Test Case for Importing from Excel
 * @author bell_er
 *
 */
public class ExcelImporterTest extends ExcelTestCase {
	
	
	@Before
	public void setUp() throws CoreException {
		super.setUp();
		iface.getTypeInstance().setUuid(new VirSatUuid("dc928367-36b7-479c-8aaf-38291f394902"));
        ec.add(iface2);
	}
	@Test
	public void test()  { 
		XSSFWorkbook wb = null;
		InputStream is;
		try {
			is = Activator.getResourceContentAsString("/resources/ImportInterfaceTypeCollectionTest.xlsx");
			
			wb = new XSSFWorkbook(is);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
					
		FuncElecImporter ei = new FuncElecImporter();
		ei.importExcel(itc.getStructuralElementInstance(), repository, wb);
		assertEquals(THREE, itc.getStructuralElementInstance().getCategoryAssignments().size());
		assertEquals("FILL", itc.getStructuralElementInstance().getCategoryAssignments().get(0).getName());
		assertEquals("HILL", itc.getStructuralElementInstance().getCategoryAssignments().get(1).getName());
		assertEquals("PILL", itc.getStructuralElementInstance().getCategoryAssignments().get(2).getName());
		
	}
	@Test
	public void test2()  { 
		XSSFWorkbook wb = null;
		InputStream is;
		try {
			is = Activator.getResourceContentAsString("/resources/ImportElementDefinitionTest.xlsx");
			
			wb = new XSSFWorkbook(is);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Command command = CreateAddSeiWithFileStructureCommand.create(editingDomain, repository, itc.getStructuralElementInstance());
	    editingDomain.getCommandStack().execute(command);
		FuncElecImporter ei = new FuncElecImporter();
		ei.importExcel(ed.getStructuralElementInstance(), repository, wb);
		BeanCategoryAssignmentHelper bCaHelper = new BeanCategoryAssignmentHelper();
		List<InterfaceEnd> seiInterfaceEnds = bCaHelper.getAllBeanCategories(ed.getStructuralElementInstance(), InterfaceEnd.class);
		assertEquals(THREE, ed.getStructuralElementInstance().getCategoryAssignments().size());
	
		assertEquals("POW_SOMETHING", ed.getStructuralElementInstance().getCategoryAssignments().get(0).getName());
		assertEquals("POW_OUT_C", ed.getStructuralElementInstance().getCategoryAssignments().get(1).getName());
		assertEquals("POW_NEW", ed.getStructuralElementInstance().getCategoryAssignments().get(2).getName());
		assertEquals("KILL", seiInterfaceEnds.get(0).getType().getName());
		assertEquals("HILL", seiInterfaceEnds.get(1).getType().getName());
		assertEquals("KILL", seiInterfaceEnds.get(2).getType().getName());

		
	}
	@Test
	public void test3()  { 
		XSSFWorkbook wb = null;
		InputStream is;
		try {
			is = Activator.getResourceContentAsString("/resources/ImportElementConfigurationTest.xlsx");
			
			wb = new XSSFWorkbook(is);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
					
		FuncElecImporter ei = new FuncElecImporter();
		ei.importExcel(ec.getStructuralElementInstance(), repository, wb);
		
		assertEquals(2, ec.getStructuralElementInstance().getCategoryAssignments().size());
		assertEquals("Interface2", ec.getStructuralElementInstance().getCategoryAssignments().get(0).getName());
	 	assertEquals("Interface3", ec.getStructuralElementInstance().getCategoryAssignments().get(1).getName());
	}
}
