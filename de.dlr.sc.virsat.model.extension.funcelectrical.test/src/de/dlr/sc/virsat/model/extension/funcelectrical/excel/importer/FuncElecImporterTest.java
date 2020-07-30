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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.eclipse.core.runtime.CoreException;
import org.junit.Before;
import org.junit.Test;

import de.dlr.sc.virsat.excel.fault.Fault;
import de.dlr.sc.virsat.model.concept.types.util.BeanCategoryAssignmentHelper;
import de.dlr.sc.virsat.model.dvlm.types.impl.VirSatUuid;
import de.dlr.sc.virsat.model.extension.funcelectrical.Activator;
import de.dlr.sc.virsat.model.extension.funcelectrical.model.InterfaceEnd;
import de.dlr.sc.virsat.model.extension.funcelectrical.test.ExcelTestCase;

/**
 * Test Case for Importing from Excel
 */
public class FuncElecImporterTest extends ExcelTestCase {

	@Before
	public void setUp() throws CoreException {
		super.setUp();
		iface.getTypeInstance().setUuid(new VirSatUuid("dc928367-36b7-479c-8aaf-38291f394902"));
		elementConf.add(iface2);
	}

	@Test
	public void testImportInterfaceTypeCollection() throws IOException {
		InputStream iStream = Activator.getResourceContentAsString("/resources/ImportInterfaceTypeCollectionTest.xlsx");
		XSSFWorkbook wb = new XSSFWorkbook(iStream);

		FuncElecImporter feImporter = new FuncElecImporter();

		List<Fault> faults = feImporter.validate(ifaceTypeCollection.getStructuralElementInstance(), wb);
		assertTrue("Succesfully called the validator", faults.isEmpty());

		feImporter.importExcel(ifaceTypeCollection.getStructuralElementInstance(), repository, wb);
		assertEquals(NUMBER_OF_INTERFACES, ifaceTypeCollection.getStructuralElementInstance().getCategoryAssignments().size());
		assertEquals("FILL", ifaceTypeCollection.getStructuralElementInstance().getCategoryAssignments().get(0).getName());
		assertEquals("HILL", ifaceTypeCollection.getStructuralElementInstance().getCategoryAssignments().get(1).getName());
		assertEquals("PILL", ifaceTypeCollection.getStructuralElementInstance().getCategoryAssignments().get(2).getName());
	}

	@Test
	public void testImportInterfaceEnds() throws IOException {
		InputStream iStream = Activator.getResourceContentAsString("/resources/ImportElementDefinitionTest.xlsx");
		XSSFWorkbook wb = new XSSFWorkbook(iStream);

		repository.getRootEntities().add(ifaceTypeCollection.getStructuralElementInstance());
		FuncElecImporter feImporter = new FuncElecImporter();

		List<Fault> faults = feImporter.validate(elementDef.getStructuralElementInstance(), wb);
		assertTrue("Succesfully called the validator", faults.isEmpty());

		feImporter.importExcel(elementDef.getStructuralElementInstance(), repository, wb);
		BeanCategoryAssignmentHelper bCaHelper = new BeanCategoryAssignmentHelper();
		List<InterfaceEnd> seiInterfaceEnds = bCaHelper.getAllBeanCategories(elementDef.getStructuralElementInstance(), InterfaceEnd.class);
		assertEquals(NUMBER_OF_INTERFACES, elementDef.getStructuralElementInstance().getCategoryAssignments().size());

		assertEquals("POW_SOMETHING", seiInterfaceEnds.get(0).getName());
		assertEquals("POW_OUT_C", seiInterfaceEnds.get(1).getName());
		assertEquals("POW_NEW", seiInterfaceEnds.get(2).getName());
		assertEquals("KILL", seiInterfaceEnds.get(0).getType().getName());
		assertEquals("HILL", seiInterfaceEnds.get(1).getType().getName());
		assertEquals("KILL", seiInterfaceEnds.get(2).getType().getName());
	}

	@Test
	public void testImportInterfaces() throws IOException {
		InputStream iStream = Activator.getResourceContentAsString("/resources/ImportElementConfigurationTest.xlsx");
		XSSFWorkbook wb = new XSSFWorkbook(iStream);

		FuncElecImporter feImporter = new FuncElecImporter();

		List<Fault> faults = feImporter.validate(elementConf.getStructuralElementInstance(), wb);
		assertTrue("Succesfully called the validator", faults.isEmpty());

		feImporter.importExcel(elementConf.getStructuralElementInstance(), repository, wb);

		assertEquals(2, elementConf.getStructuralElementInstance().getCategoryAssignments().size());
		assertEquals("Interface2", elementConf.getStructuralElementInstance().getCategoryAssignments().get(0).getName());
		assertEquals("Interface3", elementConf.getStructuralElementInstance().getCategoryAssignments().get(1).getName());
	}
}
