/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.tests.model;


import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.UnexecutableCommand;
import org.junit.Before;
import org.junit.Test;

import de.dlr.sc.virsat.model.concept.list.IBeanList;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;

/**
 * test case for the array capabilities on intrinsic properties in the beans model
 * @author fisc_ph
 *
 */
public class TestCategoryReferenceCategoryArrayStaticTest extends AConceptTestCase {

	private TestCategoryReferenceArray tcIntrinsicArray;
	private IBeanList<TestCategoryAllProperty> arrayStatic;
	private Concept concept;
	
	/**
	 * Method to create a test property
	 * @return a new BeanPropertyString
	 */
	private TestCategoryAllProperty createNewReferencedCategory() {
		TestCategoryAllProperty newBeanProperty = new TestCategoryAllProperty(concept);
		return newBeanProperty;
	}

	@Before
	public void setup() {
		// Load the concept to create the test object
		concept = loadConceptFromPlugin();
		tcIntrinsicArray = new TestCategoryReferenceArray(concept);
		arrayStatic = tcIntrinsicArray.getTestCategoryReferenceArrayStatic();
		prepareEditingDomain();
	}

	private static final int LIST_WITH_STATIC_SIZE = 4;
	
	@Test(expected = UnsupportedOperationException.class)
	public void testAddAllIntCollectionOfQextendsBeansType() {
		TestCategoryAllProperty property1 = createNewReferencedCategory();
		TestCategoryAllProperty property2 = createNewReferencedCategory();
		TestCategoryAllProperty property3 = createNewReferencedCategory();

		assertEquals("List has one items", LIST_WITH_STATIC_SIZE, arrayStatic.size());
		
		List<TestCategoryAllProperty> addBeans = new ArrayList<>();
		addBeans.add(property1);
		addBeans.add(property2);
		addBeans.add(property3);
		
		arrayStatic.addAll(addBeans);
	}

	@Test(expected = UnsupportedOperationException.class)
	public void testAddBeanType() {
		TestCategoryAllProperty property1 = createNewReferencedCategory();
		
		assertEquals("List has one items", LIST_WITH_STATIC_SIZE, arrayStatic.size());
		
		arrayStatic.add(property1);		
	}
	
	@Test
	public void testAddBeanTypeCommand() {
		TestCategoryAllProperty property1 = createNewReferencedCategory();
		
		assertEquals("List has one items", LIST_WITH_STATIC_SIZE, arrayStatic.size());
			
		Command command = arrayStatic.add(editingDomain, property1);	
		assertEquals("Command is unexecutable", UnexecutableCommand.INSTANCE, command);
	}

	@Test(expected = UnsupportedOperationException.class)
	public void testAddIntBeanType() {
		TestCategoryAllProperty property1 = createNewReferencedCategory();

		assertEquals("List has one items", LIST_WITH_STATIC_SIZE, arrayStatic.size());
		
		arrayStatic.add(1, property1);
	}

	@Test(expected = UnsupportedOperationException.class)
	public void testRemoveAll() {
		TestCategoryAllProperty property1 = createNewReferencedCategory();
		TestCategoryAllProperty property3 = createNewReferencedCategory();

		assertEquals("List has one items", LIST_WITH_STATIC_SIZE, arrayStatic.size());
		
		List<TestCategoryAllProperty> removeBeans = new ArrayList<>();
		removeBeans.add(property1);
		removeBeans.add(property3);
		
		arrayStatic.removeAll(removeBeans);
	}

	@Test(expected = UnsupportedOperationException.class)
	public void testRemoveInt() {
		arrayStatic.remove(1);
	}

	@Test(expected = UnsupportedOperationException.class)
	public void testRemoveObject() {
		TestCategoryAllProperty propertyOne = createNewReferencedCategory();

		arrayStatic.remove(propertyOne);
	}
	
	@Test
	public void testRemoveObjectCommand() {
		TestCategoryAllProperty propertyOne = createNewReferencedCategory();

		Command command = arrayStatic.remove(editingDomain, propertyOne);
		assertEquals("Command is unexecutable", UnexecutableCommand.INSTANCE, command);
	}

	@Test(expected = UnsupportedOperationException.class)
	public void testClear() {
		arrayStatic.clear();
	}
	
	@Test(expected = UnsupportedOperationException.class)
	public void testRetainAll() {
		TestCategoryAllProperty property1 = createNewReferencedCategory();
		TestCategoryAllProperty property3 = createNewReferencedCategory();
		
		List<TestCategoryAllProperty> retainBeans = new ArrayList<>();
		retainBeans.add(property1);
		retainBeans.add(property3);
		
		arrayStatic.retainAll(retainBeans);
	}

	@Test(expected = UnsupportedOperationException.class)
	public void testSetIntBeanType() {
		TestCategoryAllProperty property3 = createNewReferencedCategory();

		arrayStatic.set(1, property3);
	}
}
