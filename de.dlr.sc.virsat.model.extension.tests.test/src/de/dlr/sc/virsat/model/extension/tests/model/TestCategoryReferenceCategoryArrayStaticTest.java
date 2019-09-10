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
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

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

	@After
	public void tearDown() throws Exception {
	}
	
	private static final int LIST_WITH_STATIC_SIZE = 4;
	
	@Rule
	public final ExpectedException exception = ExpectedException.none();

	
	@Test
	public void testAddAllIntCollectionOfQextendsBeansType() {
		TestCategoryAllProperty property1 = createNewReferencedCategory();
		TestCategoryAllProperty property2 = createNewReferencedCategory();
		TestCategoryAllProperty property3 = createNewReferencedCategory();

		assertEquals("List has one items", LIST_WITH_STATIC_SIZE, arrayStatic.size());
		
		List<TestCategoryAllProperty> addBeans = new ArrayList<>();
		addBeans.add(property1);
		addBeans.add(property2);
		addBeans.add(property3);
		
		exception.expect(UnsupportedOperationException.class);
		arrayStatic.addAll(addBeans);
	}

	@Test
	public void testAddBeanType() {
		TestCategoryAllProperty property1 = createNewReferencedCategory();
		
		assertEquals("List has one items", LIST_WITH_STATIC_SIZE, arrayStatic.size());
			
		exception.expect(UnsupportedOperationException.class);
		arrayStatic.add(property1);		
	}
	
	@Test
	public void testAddBeanTypeCommand() {
		TestCategoryAllProperty property1 = createNewReferencedCategory();
		
		assertEquals("List has one items", LIST_WITH_STATIC_SIZE, arrayStatic.size());
			
		Command command = arrayStatic.add(editingDomain, property1);	
		assertEquals("Command is unexecutable", UnexecutableCommand.INSTANCE, command);
	}

	@Test
	public void testAddIntBeanType() {
		TestCategoryAllProperty property1 = createNewReferencedCategory();

		assertEquals("List has one items", LIST_WITH_STATIC_SIZE, arrayStatic.size());
		
		exception.expect(UnsupportedOperationException.class);
		arrayStatic.add(1, property1);
	}

	@Test
	public void testRemoveAll() {
		TestCategoryAllProperty property1 = createNewReferencedCategory();
		TestCategoryAllProperty property3 = createNewReferencedCategory();

		assertEquals("List has one items", LIST_WITH_STATIC_SIZE, arrayStatic.size());
		
		List<TestCategoryAllProperty> removeBeans = new ArrayList<>();
		removeBeans.add(property1);
		removeBeans.add(property3);
		
		exception.expect(UnsupportedOperationException.class);
		arrayStatic.removeAll(removeBeans);
	}

	@Test
	public void testRemoveInt() {
		exception.expect(UnsupportedOperationException.class);
		arrayStatic.remove(1);
	}

	@Test
	public void testRemoveObject() {
		TestCategoryAllProperty propertyOne = createNewReferencedCategory();

		exception.expect(UnsupportedOperationException.class);
		arrayStatic.remove(propertyOne);
	}
	
	@Test
	public void testRemoveObjectCommand() {
		TestCategoryAllProperty propertyOne = createNewReferencedCategory();

		Command command = arrayStatic.remove(editingDomain, propertyOne);
		assertEquals("Command is unexecutable", UnexecutableCommand.INSTANCE, command);
	}

	@Test
	public void testClear() {
		exception.expect(UnsupportedOperationException.class);
		arrayStatic.clear();
	}
	
	@Test
	public void testRetainAll() {
		TestCategoryAllProperty property1 = createNewReferencedCategory();
		TestCategoryAllProperty property3 = createNewReferencedCategory();
		
		List<TestCategoryAllProperty> retainBeans = new ArrayList<>();
		retainBeans.add(property1);
		retainBeans.add(property3);
		
		exception.expect(UnsupportedOperationException.class);
		arrayStatic.retainAll(retainBeans);
	}

	@Test
	public void testSetIntBeanType() {
		TestCategoryAllProperty property3 = createNewReferencedCategory();

		exception.expect(UnsupportedOperationException.class);
		arrayStatic.set(1, property3);
	}
}
