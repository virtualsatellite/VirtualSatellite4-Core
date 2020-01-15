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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.extension.tests.model.AConceptTestCase;
import de.dlr.sc.virsat.model.extension.tests.model.TestCategoryAllProperty;
import de.dlr.sc.virsat.model.extension.tests.model.TestStructuralElement;
import de.dlr.sc.virsat.model.extension.tests.model.TestStructuralElementOther;

import static org.hamcrest.CoreMatchers.hasItems;

/**
 * This class handles the test cases for BeanStructuralElementInstance Helper. Since it needs a test concept to execute the code
 * for testing the class resides here rather than model.edit.test plugin which
 * should be its primary place of storage.
 * @author fisc_ph
 *
 */

public class BeanStructuralElementInstanceHelperTest extends AConceptTestCase {

	private Concept concept;
	
	@Before
	public void setup() {
		// Load the concept to create the test object
		concept = loadConceptFromPlugin();
	}
	
	@Test
	public void testGetAllSubBeanSeis() {
		TestStructuralElement rootElement = new TestStructuralElement(concept);
		TestStructuralElement child1 = new TestStructuralElement(concept);
		TestStructuralElement child2 = new TestStructuralElement(concept);
		TestStructuralElementOther childOther1 = new TestStructuralElementOther(concept);
		TestStructuralElementOther childOther2 = new TestStructuralElementOther(concept);
		
		rootElement.add(child1);
		rootElement.add(child2);
		rootElement.add(childOther1);
		rootElement.add(childOther2);
		
		BeanStructuralElementInstanceHelper bseiHelper = new BeanStructuralElementInstanceHelper();
		List<TestStructuralElement> filteredChildren = bseiHelper.getAllSubBeanSeis(rootElement.getStructuralElementInstance(), TestStructuralElement.class);
		List<TestStructuralElementOther> filteredChildrenOther = bseiHelper.getAllSubBeanSeis(rootElement.getStructuralElementInstance(), TestStructuralElementOther.class);
		
		assertEquals("Correct number of elements", 2, filteredChildren.size());
		assertThat("Correct elements", filteredChildren, hasItems(child1, child2));
		assertThat("Correct elements", filteredChildrenOther, hasItems(childOther1, childOther2));
	}
	
	@Test
	public void testWrapAllBeanSeisOfType() {
		TestStructuralElement rootElement = new TestStructuralElement(concept);
		TestStructuralElement child1 = new TestStructuralElement(concept);
		TestStructuralElement child2 = new TestStructuralElement(concept);
		TestStructuralElementOther childOther1 = new TestStructuralElementOther(concept);
		TestStructuralElementOther childOther2 = new TestStructuralElementOther(concept);
		
		rootElement.add(child1);
		rootElement.add(child2);
		rootElement.add(childOther1);
		rootElement.add(childOther2);
		
		BeanStructuralElementInstanceHelper bseiHelper = new BeanStructuralElementInstanceHelper();
		List<TestStructuralElement> filteredChildren = bseiHelper.wrapAllBeanSeisOfType(rootElement.getStructuralElementInstance().getChildren(), TestStructuralElement.class);
		List<TestStructuralElementOther> filteredChildrenOther = bseiHelper.wrapAllBeanSeisOfType(rootElement.getStructuralElementInstance().getChildren(), TestStructuralElementOther.class);
		
		assertEquals("Correct number of elements", 2, filteredChildren.size());
		assertThat("Correct elements", filteredChildren, hasItems(child1, child2));
		assertThat("Correct elements", filteredChildrenOther, hasItems(childOther1, childOther2));
	}
	
	@Test
	public void testGetParentOfClass() {
		TestStructuralElement rootElement = new TestStructuralElement(concept);
		TestStructuralElement child1 = new TestStructuralElement(concept);
		TestStructuralElement child2 = new TestStructuralElement(concept);
		TestStructuralElementOther childOther1 = new TestStructuralElementOther(concept);
		TestStructuralElementOther childOfOther2 = new TestStructuralElementOther(concept);
		
		rootElement.add(child1);
		rootElement.add(child2);
		rootElement.add(childOther1);
		childOther1.add(childOfOther2);
		
		BeanStructuralElementInstanceHelper bseiHelper = new BeanStructuralElementInstanceHelper();
		
		assertNull("The root has no parent", rootElement.getParentOfClass(TestStructuralElement.class));
		assertNull("Child 1 has no parent of type SEOther", rootElement.getParentOfClass(TestStructuralElementOther.class));
		assertEquals("Child 1 has no parent of type SE, the root", rootElement, child1.getParentOfClass(TestStructuralElement.class));
		
		assertEquals("ChildChild has a direct parent of type SEOther", childOther1, childOfOther2.getParentOfClass(TestStructuralElementOther.class));
		assertEquals("ChildChild has a direct parent of type SEOther", rootElement, childOfOther2.getParentOfClass(TestStructuralElement.class));

		TestCategoryAllProperty testCa = new TestCategoryAllProperty(concept);
		childOfOther2.add(testCa);

		assertEquals("ChildChild has a direct parent of type SEOther", childOfOther2, bseiHelper.getParentOfClass(testCa.getTestBoolBean().getTypeInstance(), TestStructuralElementOther.class));
		assertEquals("ChildChild has a direct parent of type SEOther", rootElement, bseiHelper.getParentOfClass(testCa.getTestBoolBean().getTypeInstance(), TestStructuralElement.class));
	}

	@Test
	public void testGetParent() {
		TestStructuralElement rootElement = new TestStructuralElement(concept);
		TestStructuralElementOther child = new TestStructuralElementOther(concept);
		TestStructuralElementOther grandChild = new TestStructuralElementOther(concept);
		
		rootElement.add(child);
		child.add(grandChild);
		
		assertNull("The root has no parent", rootElement.getParentSeiBean());
		assertEquals("The child has proper parent", rootElement.getStructuralElementInstance(), child.getParentSeiBean().getStructuralElementInstance());
		assertEquals("The grandchild has proper parent", child.getStructuralElementInstance(), grandChild.getParentSeiBean().getStructuralElementInstance());
	}

	@Test
	public void testGetSuperBeanSeisList() {
		TestStructuralElement rootElement = new TestStructuralElement(concept);
		TestStructuralElement super1 = new TestStructuralElement(concept);
		TestStructuralElement super2 = new TestStructuralElement(concept);
		TestStructuralElement superSuper = new TestStructuralElement(concept);
		TestStructuralElementOther superOther1 = new TestStructuralElementOther(concept);
		TestStructuralElementOther superOther2 = new TestStructuralElementOther(concept);

		rootElement.addSuperSei(super1);
		rootElement.addSuperSei(super2);
		rootElement.addSuperSei(superOther1);
		rootElement.addSuperSei(superOther2);
		super1.addSuperSei(superSuper);
		
		BeanStructuralElementInstanceHelper bseiHelper = new BeanStructuralElementInstanceHelper();
		List<TestStructuralElement> filteredSupers = bseiHelper.getSuperBeanSeis(rootElement.getStructuralElementInstance(), TestStructuralElement.class);
		List<TestStructuralElementOther> filteredSuperOther = bseiHelper.getSuperBeanSeis(rootElement.getStructuralElementInstance(), TestStructuralElementOther.class);
		
		assertEquals("Correct number of elements", 2, filteredSupers.size());
		assertThat("Correct elements", filteredSupers, hasItems(super1, super2));
		assertThat("Correct elements", filteredSuperOther, hasItems(superOther1, superOther2));
	}
	
	@Test
	public void testGetAllSuperBeanSeisSet() {
		TestStructuralElement rootElement = new TestStructuralElement(concept);
		TestStructuralElement super1 = new TestStructuralElement(concept);
		TestStructuralElement super2 = new TestStructuralElement(concept);
		TestStructuralElementOther superOther1 = new TestStructuralElementOther(concept);
		TestStructuralElementOther superOther2 = new TestStructuralElementOther(concept);

		rootElement.addSuperSei(super1);
		rootElement.addSuperSei(superOther2);
		super1.addSuperSei(super2);
		super1.addSuperSei(superOther1);
		
		BeanStructuralElementInstanceHelper bseiHelper = new BeanStructuralElementInstanceHelper();
		Set<TestStructuralElement> filteredSupers = bseiHelper.getAllSuperBeanSeis(rootElement.getStructuralElementInstance(), TestStructuralElement.class);
		Set<TestStructuralElementOther> filteredSuperOther = bseiHelper.getAllSuperBeanSeis(rootElement.getStructuralElementInstance(), TestStructuralElementOther.class);
		
		assertEquals("Correct number of elements", 2, filteredSupers.size());
		assertThat("Correct elements", filteredSupers, hasItems(super1, super2));
		assertThat("Correct elements", filteredSuperOther, hasItems(superOther1, superOther2));
	}
}
