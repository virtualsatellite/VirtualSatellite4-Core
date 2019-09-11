/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.concept.types.util;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import de.dlr.sc.virsat.model.dvlm.categories.CategoriesFactory;
import de.dlr.sc.virsat.model.dvlm.categories.Category;
import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import de.dlr.sc.virsat.model.extension.tests.model.AConceptTestCase;
import de.dlr.sc.virsat.model.extension.tests.model.TestCategoryAllProperty;
import de.dlr.sc.virsat.model.extension.tests.model.TestCategoryBeanA;
import de.dlr.sc.virsat.model.extension.tests.model.TestCategoryBeanAbstract;
import de.dlr.sc.virsat.model.extension.tests.model.TestCategoryBeanB;
import de.dlr.sc.virsat.model.extension.tests.model.TestCategoryBeanConcrete;
import de.dlr.sc.virsat.model.extension.tests.model.TestCategoryCompositionArray;
import de.dlr.sc.virsat.model.extension.tests.model.TestStructuralElement;

/**
 * this class handles the test cases for the BeanCategoryAssignemtn Helper. It needs a concept to be 
 * executed correctly. For hits reason the test code resides here rather than in the model.edit.test plugin which
 * should be its primary place of storage.
 * @author fisc_ph
 *
 */
public class BeanCategoryAssignmentHelperTest extends AConceptTestCase {

	private Concept concept;
	
	@Before
	public void setup() {
		// Load the concept to create the test object
		concept = loadConceptFromPlugin();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetBeanCategory() {
		TestCategoryBeanA testCaBeanA = new TestCategoryBeanA(concept);
		CategoryAssignment testCaA = testCaBeanA.getTypeInstance();
		
		BeanCategoryAssignmentHelper bCaHelper = new BeanCategoryAssignmentHelper();
		TestCategoryBeanA testCaBeanA2 = bCaHelper.getBeanCategory(testCaA, TestCategoryBeanA.class);
		
		assertEquals("WrappedCorrectly", testCaA, testCaBeanA2.getATypeInstance());
		
		TestCategoryBeanB testCaBeanB = bCaHelper.getBeanCategory(testCaA, TestCategoryBeanB.class);
		assertNull("Could not wrap CA incompatible type", testCaBeanB);
	}
	
	@Test
	public void testGetParentCaBeanOfClass() {
		Category cat = CategoriesFactory.eINSTANCE.createCategory();
		CategoryAssignment ca = CategoriesFactory.eINSTANCE.createCategoryAssignment();
		ca.setType(cat);
		
		TestCategoryCompositionArray testCaBeanParent = new TestCategoryCompositionArray(concept);
		TestCategoryAllProperty testCaBeanContained = new TestCategoryAllProperty(concept);

		testCaBeanParent.getTestCompositionArrayDynamic().add(testCaBeanContained);

		BeanCategoryAssignmentHelper bCaHelper = new BeanCategoryAssignmentHelper();
		
		TestCategoryCompositionArray returnedBean = bCaHelper.getParentCaBeanOfClass(testCaBeanContained.getTypeInstance(), TestCategoryCompositionArray.class);
		
		assertEquals("Returned correct bean", testCaBeanParent, returnedBean);
	}
	
	@Test
	public void testGetAllBeanCategories() {
		TestStructuralElement testSe = new TestStructuralElement(concept);
		StructuralElementInstance sei = testSe.getStructuralElementInstance();
		
		TestCategoryBeanA testCaBeanA1 = new TestCategoryBeanA(concept);
		TestCategoryBeanA testCaBeanA2 = new TestCategoryBeanA(concept);
		TestCategoryBeanA testCaBeanA3 = new TestCategoryBeanA(concept);
		TestCategoryBeanA testCaBeanA4 = new TestCategoryBeanA(concept);
		
		TestCategoryBeanB testCaBeanB1 = new TestCategoryBeanB(concept);
		TestCategoryBeanB testCaBeanB2 = new TestCategoryBeanB(concept);
		
		TestCategoryBeanConcrete testCaBeanConcrete = new TestCategoryBeanConcrete(concept);
		
		testSe.add(testCaBeanA1);
		testSe.add(testCaBeanA2);
		testSe.add(testCaBeanA3);
		testSe.add(testCaBeanA4);
		
		testSe.add(testCaBeanB1);
		testSe.add(testCaBeanB2);
		testSe.add(testCaBeanConcrete);
		
		BeanCategoryAssignmentHelper bCaHelper = new BeanCategoryAssignmentHelper();
		
		List<TestCategoryBeanA> catBeansA = bCaHelper.getAllBeanCategories(sei, TestCategoryBeanA.class);
		List<TestCategoryBeanB> catBeansB = bCaHelper.getAllBeanCategories(sei, TestCategoryBeanB.class);
		List<TestCategoryBeanAbstract> catBeansAbstract = bCaHelper.getAllBeanCategories(sei, TestCategoryBeanAbstract.class);
		
		//CHECKSTYLE:OFF
		assertEquals("CategoryTypeA is assigned 4 times", 4, catBeansA.size());
		assertEquals("CategoryTypeB is assigned 2 times", 2, catBeansB.size());
		assertEquals("CategoryTypeAbstract is assigned 1 times", 1, catBeansAbstract.size());
		
		assertThat("CatA Assignemnts are well contained", catBeansA, hasItems(
		      testCaBeanA1,
			  testCaBeanA2,
			  testCaBeanA3,
			  testCaBeanA4));
		
		assertThat("CatB Assignemnts are well contained", catBeansB, hasItems(
			      testCaBeanB1,
				  testCaBeanB2));
			
		assertThat("CatAbstract Assignemnts are well contained", catBeansAbstract, hasItems(testCaBeanConcrete));
		//CHECKSTYLE:ON
	}
	
	@Test
	public void testGetAllBeanCategoriesFromRoot() {
		TestStructuralElement testSeRoot = new TestStructuralElement(concept);
		TestStructuralElement testSeChild = new TestStructuralElement(concept);
		testSeRoot.add(testSeChild);
		StructuralElementInstance seiRoot = testSeRoot.getStructuralElementInstance();
		
		TestCategoryBeanA testCaBeanA1 = new TestCategoryBeanA(concept);
		TestCategoryBeanA testCaBeanA2 = new TestCategoryBeanA(concept);
		TestCategoryBeanA testCaBeanA3 = new TestCategoryBeanA(concept);
		TestCategoryBeanA testCaBeanA4 = new TestCategoryBeanA(concept);
		
		TestCategoryBeanB testCaBeanB1 = new TestCategoryBeanB(concept);
		TestCategoryBeanB testCaBeanB2 = new TestCategoryBeanB(concept);
		
		testSeRoot.add(testCaBeanA1);
		testSeRoot.add(testCaBeanA2);
		testSeChild.add(testCaBeanA3);
		testSeChild.add(testCaBeanA4);
		
		testSeRoot.add(testCaBeanB1);
		testSeChild.add(testCaBeanB2);
		
		BeanCategoryAssignmentHelper bCaHelper = new BeanCategoryAssignmentHelper();
		
		List<TestCategoryBeanA> catBeansA = bCaHelper.getAllBeanCategoriesFromRoot(seiRoot, TestCategoryBeanA.class);
		List<TestCategoryBeanB> catBeansB = bCaHelper.getAllBeanCategoriesFromRoot(seiRoot, TestCategoryBeanB.class);

		//CHECKSTYLE:OFF
		assertEquals("CategoryTypeA is assigned 4 times", 4, catBeansA.size());
		assertEquals("CategoryTypeB is assigned 2 times", 2, catBeansB.size());
		
		assertThat("CatA Assignemnts are well contained", catBeansA, hasItems(
		      testCaBeanA1,
			  testCaBeanA2,
			  testCaBeanA3,
			  testCaBeanA4));
		
		assertThat("CatB Assignemnts are well contained", catBeansB, hasItems(
			      testCaBeanB1,
				  testCaBeanB2));
		//CHECKSTYLE:ON
	}
	
	@Test
	public void testGetFirstBeanCategory() {
		TestStructuralElement testSe = new TestStructuralElement(concept);
		StructuralElementInstance sei = testSe.getStructuralElementInstance();
		
		TestCategoryBeanA testCaBeanA1 = new TestCategoryBeanA(concept);
		TestCategoryBeanA testCaBeanA2 = new TestCategoryBeanA(concept);
		TestCategoryBeanA testCaBeanA3 = new TestCategoryBeanA(concept);
		TestCategoryBeanA testCaBeanA4 = new TestCategoryBeanA(concept);
		
		testSe.add(testCaBeanA1);
		testSe.add(testCaBeanA2);
		testSe.add(testCaBeanA3);
		testSe.add(testCaBeanA4);

		BeanCategoryAssignmentHelper bCaHelper = new BeanCategoryAssignmentHelper();
		
		TestCategoryBeanA catBeanA = bCaHelper.getFirstBeanCategory(sei, TestCategoryBeanA.class);
		TestCategoryBeanB catBeanB = bCaHelper.getFirstBeanCategory(sei, TestCategoryBeanB.class);
		
		assertEquals("CategoryTypeA is assigned found as expected", testCaBeanA1, catBeanA);
		assertNull("CategoryTypeB is assigned 2 times", catBeanB);
	}
}
