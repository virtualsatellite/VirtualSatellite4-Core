/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.dvlm.categories.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import org.junit.Before;
import org.junit.Test;

import de.dlr.sc.virsat.model.dvlm.DVLMFactory;
import de.dlr.sc.virsat.model.dvlm.Repository;
import de.dlr.sc.virsat.model.dvlm.categories.CategoriesFactory;
import de.dlr.sc.virsat.model.dvlm.categories.Category;
import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.IntProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.PropertydefinitionsFactory;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.StringProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.APropertyInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.PropertyinstancesFactory;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ValuePropertyInstance;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.dvlm.concepts.ConceptsFactory;
import de.dlr.sc.virsat.model.dvlm.qudv.SystemOfUnits;
import de.dlr.sc.virsat.model.dvlm.qudv.util.QudvUnitHelper;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElement;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralFactory;
import de.dlr.sc.virsat.model.dvlm.units.UnitManagement;
import de.dlr.sc.virsat.model.dvlm.units.UnitsFactory;

/**
 * 
 * @author fisc_ph
 *
 */
public class CategoryAssignmentHelperTest {

	private Repository repo;
	private Concept concept;
	private UnitManagement unitManagement;
	private SystemOfUnits sou;
	
	private Category testCategory;
	private CategoryAssignment ca;
	private CategoryAssignmentHelper caHelper;
	
	private IntProperty propertyOne;

	private static final String TEST_PROPERTY_NAME_ONE = "p1";
	private static final String TEST_PROPERTY_NAME_TWO = "p2";

	@Before
	public void setUp() throws Exception {
		testCategory = CategoriesFactory.eINSTANCE.createCategory();

		propertyOne = PropertydefinitionsFactory.eINSTANCE.createIntProperty();
		propertyOne.setName(TEST_PROPERTY_NAME_ONE);
		testCategory.getProperties().add(propertyOne);
		testCategory.setIsApplicableForAll(true);

		StringProperty propertyTwo = PropertydefinitionsFactory.eINSTANCE.createStringProperty();
		propertyTwo.setName(TEST_PROPERTY_NAME_TWO);
		testCategory.getProperties().add(propertyTwo);
		testCategory.setName("testCategory");

		ca = new CategoryInstantiator().generateInstance(testCategory, "TestInstanceName");
		caHelper = new CategoryAssignmentHelper(ca);

		repo = DVLMFactory.eINSTANCE.createRepository();
		concept = ConceptsFactory.eINSTANCE.createConcept();
		unitManagement = UnitsFactory.eINSTANCE.createUnitManagement();
		
		sou = QudvUnitHelper.getInstance().initializeSystemOfUnits("SystemOfUnits", "SoU", "the system of Units", "http://the.system.of.units.de");
		
		unitManagement.setSystemOfUnit(sou);
		repo.setUnitManagement(unitManagement);
		concept.getCategories().add(testCategory);
		concept.setName("testConcept");
		repo.getActiveConcepts().add(concept);
	}

	@Test
	public void testGetValidPropertyInstanceByName() {
		APropertyInstance propertyInstance = caHelper.getPropertyInstance(TEST_PROPERTY_NAME_ONE);
		assertEquals("the property instance is typed by the correct property", propertyOne, propertyInstance.getType());
		
		APropertyInstance propertyInstanceFqn = caHelper.getPropertyInstance("testConcept.testCategory.p1");
		assertEquals("the property instance is typed by the correct property", propertyOne, propertyInstanceFqn.getType());
	}

	@Test(expected = RuntimeException.class)
	public void testGetInvalidPropertyInstanceByName() {
		caHelper.getPropertyInstance("invalidName");
	}
	
	@SuppressWarnings("static-access")
	@Test
	public void testGetTypeByName() {
		String validTypeName = "CaTypeName"; 
		ca.getType().setName(validTypeName);
		
		assertEquals("the category helper returns the correct type name", validTypeName, caHelper.getTypeName(ca));
	}
	
	@Test
	public void testSetCategoryAssignment() {
		CategoryAssignmentHelper returnedCaHelper = caHelper.setCategoryAssignment(ca);
		assertEquals("set the category assignemt correctly", caHelper, returnedCaHelper);	
	}
	
	@Test
	public void testGetPropertyInstance() {
		
		IntProperty testProperty = PropertydefinitionsFactory.eINSTANCE.createIntProperty();
		APropertyInstance returnedNull = caHelper.getPropertyInstance(testProperty);
		assertNull("the test property is not part of the category assignment", returnedNull);
		
		APropertyInstance returnedPropInstance = caHelper.getPropertyInstance(propertyOne);
		assertEquals("returned the correct property instance of the property", ca.getPropertyInstances().get(0), returnedPropInstance);
	}
	
	@Test
	public void testGetCategoryAssignmentsOfType() {
		StructuralElement se = StructuralFactory.eINSTANCE.createStructuralElement();
		StructuralElementInstance sei = StructuralFactory.eINSTANCE.createStructuralElementInstance();
		sei.setType(se);
		
		final String TEST_ID_1 = "CatIdOne";
		final String TEST_ID_2 = "CatIdTwo";
		
		Category c1 = CategoriesFactory.eINSTANCE.createCategory();
		c1.setIsApplicableForAll(true);
		c1.setName(TEST_ID_1);

		Category c2 = CategoriesFactory.eINSTANCE.createCategory();
		c2.setIsApplicableForAll(true);
		c2.setName(TEST_ID_2);
		
		CategoryInstantiator ci =  new CategoryInstantiator();

		sei.getCategoryAssignments().add(ci.generateInstance(c1, "InstC1_1"));
		sei.getCategoryAssignments().add(ci.generateInstance(c1, "InstC1_2"));
		sei.getCategoryAssignments().add(ci.generateInstance(c1, "InstC1_3"));

		sei.getCategoryAssignments().add(ci.generateInstance(c2, "InstC2_1"));
		sei.getCategoryAssignments().add(ci.generateInstance(c2, "InstC2_2"));
		
		//CHECKSTYLE:OFF
		assertEquals("C1 has been assigned 3 times", 3, CategoryAssignmentHelper.getCategoryAssignmentsOfType(sei, TEST_ID_1).size());
		assertEquals("C2 has been assigned 2 times", 2, CategoryAssignmentHelper.getCategoryAssignmentsOfType(sei, TEST_ID_2).size());
		//CHECKSTYLE:ON;
	}

	@Test
	public void testGetCategoryAssignmentsOfSuperType() {
		StructuralElement se = StructuralFactory.eINSTANCE.createStructuralElement();
		StructuralElementInstance sei = StructuralFactory.eINSTANCE.createStructuralElementInstance();
		sei.setType(se);
		
		final String TEST_ID_S = "CatIdSuper";
		final String TEST_ID_1 = "CatIdOne";
		final String TEST_ID_2 = "CatIdTwo";

		Category cs = CategoriesFactory.eINSTANCE.createCategory();
		cs.setIsApplicableForAll(true);
		cs.setName(TEST_ID_S);

		
		Category c1 = CategoriesFactory.eINSTANCE.createCategory();
		c1.setIsApplicableForAll(true);
		c1.setExtendsCategory(cs);
		c1.setName(TEST_ID_1);

		Category c2 = CategoriesFactory.eINSTANCE.createCategory();
		c2.setIsApplicableForAll(true);
		c2.setExtendsCategory(cs);
		c2.setName(TEST_ID_2);
		
		CategoryInstantiator ci =  new CategoryInstantiator();

		sei.getCategoryAssignments().add(ci.generateInstance(c1, "InstC1_1"));
		sei.getCategoryAssignments().add(ci.generateInstance(c1, "InstC1_2"));
		sei.getCategoryAssignments().add(ci.generateInstance(c1, "InstC1_3"));

		sei.getCategoryAssignments().add(ci.generateInstance(c2, "InstC2_1"));
		sei.getCategoryAssignments().add(ci.generateInstance(c2, "InstC2_2"));
		
		//CHECKSTYLE:OFF
		assertEquals("C1 has been assigned 3 times", 3, CategoryAssignmentHelper.getCategoryAssignmentsOfSuperType(sei, TEST_ID_1).size());
		assertEquals("C2 has been assigned 2 times", 2, CategoryAssignmentHelper.getCategoryAssignmentsOfSuperType(sei, TEST_ID_2).size());
		assertEquals("CS has been assigned 5 times", 5, CategoryAssignmentHelper.getCategoryAssignmentsOfSuperType(sei, TEST_ID_S).size());
		//CHECKSTYLE:ON;
	}

	
	@Test
	public void testGetFirstCategoryAssignments() {
		StructuralElement se = StructuralFactory.eINSTANCE.createStructuralElement();
		StructuralElementInstance sei = StructuralFactory.eINSTANCE.createStructuralElementInstance();
		sei.setType(se);
		
		final String TEST_ID_1 = "CatIdOne";
		final String TEST_ID_2 = "CatIdTwo";
		
		Category c1 = CategoriesFactory.eINSTANCE.createCategory();
		c1.setIsApplicableForAll(true);
		c1.setName(TEST_ID_1);

		Category c2 = CategoriesFactory.eINSTANCE.createCategory();
		c2.setIsApplicableForAll(true);
		c2.setName(TEST_ID_2);
		
		CategoryInstantiator ci =  new CategoryInstantiator();

		CategoryAssignment caFirstInstance = ci.generateInstance(c1, "InstC1_1");
		
		sei.getCategoryAssignments().add(caFirstInstance);
		sei.getCategoryAssignments().add(ci.generateInstance(c1, "InstC1_2"));
		sei.getCategoryAssignments().add(ci.generateInstance(c1, "InstC1_3"));
		
		//CHECKSTYLE:OFF
		assertEquals("C1 has been assigned 3 times and succesfully returned the first one", caFirstInstance, CategoryAssignmentHelper.getFirstCategoryAssignment(sei, TEST_ID_1));
		assertNull("C2 has not been assigned", CategoryAssignmentHelper.getFirstCategoryAssignment(sei, TEST_ID_2));
		//CHECKSTYLE:ON;		
	}
	
	@Test
	public void testGetContainerFor() {
		StructuralElement se = StructuralFactory.eINSTANCE.createStructuralElement();
		StructuralElementInstance sei = StructuralFactory.eINSTANCE.createStructuralElementInstance();
		sei.setType(se);
		
		APropertyInstance pi1 = ca.getPropertyInstances().get(0);
		APropertyInstance pi2 = ca.getPropertyInstances().get(1);
		
		assertNull("CA is not yet contained", CategoryAssignmentHelper.getContainerFor(ca));
		assertNull("CA is not yet contained", CategoryAssignmentHelper.getContainerFor(pi1));
		assertNull("CA is not yet contained", CategoryAssignmentHelper.getContainerFor(pi2));
		
		sei.getCategoryAssignments().add(ca);
		
		assertEquals("Ca is now contained", sei, CategoryAssignmentHelper.getContainerFor(ca));
		assertEquals("Ca is now contained", sei, CategoryAssignmentHelper.getContainerFor(pi1));
		assertEquals("Ca is now contained", sei, CategoryAssignmentHelper.getContainerFor(pi2));
	}
	
	@Test
	public void testGetRepository() {
		Repository repoCA = CategoryAssignmentHelper.getRepository(ca);
		Repository repoProp = CategoryAssignmentHelper.getRepository(ca.getPropertyInstances().get(0));
		
		assertEquals("Repos are equal", repo, repoCA);
		assertEquals("Repos are equal", repo, repoProp);
		
		ValuePropertyInstance vpi = PropertyinstancesFactory.eINSTANCE.createValuePropertyInstance();
		assertNull("No type information is set, hence we cannot find the repo", CategoryAssignmentHelper.getRepository(vpi));

		// Give the vpi a type but still don't have it in a concept. thus we should not get the repo
		// since we try to find it via the concept. This may change in the future.
		IntProperty ip = PropertydefinitionsFactory.eINSTANCE.createIntProperty();
		vpi.setType(ip);
		
		assertNull("No concept attached to the property, hence we cannot find the repo", CategoryAssignmentHelper.getRepository(vpi));
	}
	
	@Test
	public void testGetSystemOfUnits() {
		SystemOfUnits souCA = CategoryAssignmentHelper.getSystemOfUnits(ca);
		SystemOfUnits souProp = CategoryAssignmentHelper.getSystemOfUnits(ca.getPropertyInstances().get(0));
		
		assertEquals("SOUs are equal", sou, souCA);
		assertEquals("SOUs are equal", sou, souProp);
	}
}
