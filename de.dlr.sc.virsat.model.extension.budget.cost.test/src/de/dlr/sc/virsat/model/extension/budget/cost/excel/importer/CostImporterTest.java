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
import de.dlr.sc.virsat.model.extension.budget.cost.activator.Activator;
import de.dlr.sc.virsat.model.extension.budget.cost.model.CostEquipment;
import de.dlr.sc.virsat.model.extension.budget.cost.test.ExcelTestCase;

/**
 * Test Case for Importing from Excel
 */
public class CostImporterTest extends ExcelTestCase {

	@Before
	public void setUp() throws CoreException {
		super.setUp();

	}

	@Test
	public void testImportCostTypeCollection() throws IOException {
		InputStream iStream = Activator.getResourceContentAsString("/resources/ImportCostTypeCollectionTest.xlsx");
		XSSFWorkbook wb = new XSSFWorkbook(iStream);

		CostImporter costImporter = new CostImporter();

		List<Fault> faults = costImporter.validate(costTypesCollection.getStructuralElementInstance(), wb);
		assertTrue("Succesfully called the validator", faults.isEmpty());

		costImporter.importExcel(costTypesCollection.getStructuralElementInstance(), repository, wb);
		assertEquals(NUMBER_OF_COST_TYPES, costTypesCollection.getStructuralElementInstance().getCategoryAssignments().size());
		assertEquals("Personal", costTypesCollection.getStructuralElementInstance().getCategoryAssignments().get(0).getName());
		assertEquals("Test", costTypesCollection.getStructuralElementInstance().getCategoryAssignments().get(1).getName());
		assertEquals("Demo", costTypesCollection.getStructuralElementInstance().getCategoryAssignments().get(2).getName());
	}

	@Test
	public void testImportCostEquipments() throws IOException {
		InputStream iStream = Activator.getResourceContentAsString("/resources/ImportElementDefinitionTest.xlsx");
		XSSFWorkbook wb = new XSSFWorkbook(iStream);

		repository.getRootEntities().add(costTypesCollection.getStructuralElementInstance());
		CostImporter costImporter = new CostImporter();

		List<Fault> faults = costImporter.validate(elementDef.getStructuralElementInstance(), wb);
		assertTrue("Succesfully called the validator", faults.isEmpty());

		costImporter.importExcel(elementDef.getStructuralElementInstance(), repository, wb);
		BeanCategoryAssignmentHelper bCaHelper = new BeanCategoryAssignmentHelper();
		List<CostEquipment> seiCostEquipments = bCaHelper.getAllBeanCategories(elementDef.getStructuralElementInstance(), CostEquipment.class);
		assertEquals(NUMBER_OF_COST_TYPES, elementDef.getStructuralElementInstance().getCategoryAssignments().size());

		assertEquals("CostPersonal", seiCostEquipments.get(0).getName());
		assertEquals("CostTest", seiCostEquipments.get(1).getName());
		assertEquals("CostMaterial", seiCostEquipments.get(2).getName());
		assertEquals("Personal", seiCostEquipments.get(0).getType().getName());
		assertEquals("Test", seiCostEquipments.get(1).getType().getName());
		assertEquals("Material", seiCostEquipments.get(2).getType().getName());
	}
}

