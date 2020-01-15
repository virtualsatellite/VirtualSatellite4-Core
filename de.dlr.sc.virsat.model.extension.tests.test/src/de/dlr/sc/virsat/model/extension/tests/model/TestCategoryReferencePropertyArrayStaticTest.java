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
import de.dlr.sc.virsat.model.concept.types.property.BeanPropertyString;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;

/**
 * test case for the array capabilities on intrinsic properties in the beans model
 * @author fisc_ph
 *
 */
public class TestCategoryReferencePropertyArrayStaticTest extends AConceptTestCase {

	private TestCategoryReferenceArray tcIntrinsicArray;
	private IBeanList<BeanPropertyString> arrayStatic;
	private Concept concept;
	private TestCategoryAllProperty referencedCategory;
	
	/**
	 * Method to create a test property
	 * @return a new BeanPropertyString
	 */
	private BeanPropertyString createNewReferencedTypeProperty() {
		return referencedCategory.getTestStringBean();
	}

	@Before
	public void setup() {
		// Load the concept to create the test object
		prepareEditingDomain();
		concept = loadConceptFromPlugin();
		tcIntrinsicArray = new TestCategoryReferenceArray(concept);
		arrayStatic = tcIntrinsicArray.getTestPropertyReferenceArrayStatic();
		referencedCategory = new TestCategoryAllProperty(concept);
	}

	@After
	public void tearDown() throws Exception {
	}
	
	private static final int LIST_WITH_STATIC_SIZE = 4;
	
	@Rule
	public final ExpectedException exception = ExpectedException.none();

	
	@Test
	public void testAddAllIntCollectionOfQextendsBeansType() {
		BeanPropertyString property1 = createNewReferencedTypeProperty();
		BeanPropertyString property2 = createNewReferencedTypeProperty();
		BeanPropertyString property3 = createNewReferencedTypeProperty();

		assertEquals("List has one items", LIST_WITH_STATIC_SIZE, arrayStatic.size());
		
		List<BeanPropertyString> addBeans = new ArrayList<>();
		addBeans.add(property1);
		addBeans.add(property2);
		addBeans.add(property3);
		
		exception.expect(UnsupportedOperationException.class);
		arrayStatic.addAll(addBeans);
	}

	@Test
	public void testAddBeanType() {
		BeanPropertyString property1 = createNewReferencedTypeProperty();
		
		assertEquals("List has one items", LIST_WITH_STATIC_SIZE, arrayStatic.size());
			
		exception.expect(UnsupportedOperationException.class);
		arrayStatic.add(property1);		
	}
	
	@Test
	public void testAddBeanTypeCommand() {
		BeanPropertyString property1 = createNewReferencedTypeProperty();
		
		assertEquals("List has one items", LIST_WITH_STATIC_SIZE, arrayStatic.size());
		Command command = arrayStatic.add(editingDomain, property1);	
		assertEquals("Command is unexecutabled", UnexecutableCommand.INSTANCE, command);
	}

	@Test
	public void testAddIntBeanType() {
		BeanPropertyString property1 = createNewReferencedTypeProperty();

		assertEquals("List has one items", LIST_WITH_STATIC_SIZE, arrayStatic.size());
		
		exception.expect(UnsupportedOperationException.class);
		arrayStatic.add(1, property1);
	}
	
	@Test
	public void testClear() {
		exception.expect(UnsupportedOperationException.class);
		arrayStatic.clear();
	}

	@Test
	public void testRemoveAll() {
		BeanPropertyString property1 = createNewReferencedTypeProperty();
		BeanPropertyString property3 = createNewReferencedTypeProperty();

		assertEquals("List has one items", LIST_WITH_STATIC_SIZE, arrayStatic.size());
		
		List<BeanPropertyString> removeBeans = new ArrayList<>();
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
		BeanPropertyString propertyOne = createNewReferencedTypeProperty();

		exception.expect(UnsupportedOperationException.class);
		arrayStatic.remove(propertyOne);
	}

	@Test
	public void testRemoveObjectCommand() {
		BeanPropertyString propertyOne = createNewReferencedTypeProperty();
		
		Command command = arrayStatic.remove(editingDomain, propertyOne);
		assertEquals("Command is unexecutabled", UnexecutableCommand.INSTANCE, command);
	}
	
	@Test
	public void testRetainAll() {
		BeanPropertyString property1 = createNewReferencedTypeProperty();
		BeanPropertyString property3 = createNewReferencedTypeProperty();
		
		List<BeanPropertyString> retainBeans = new ArrayList<>();
		retainBeans.add(property1);
		retainBeans.add(property3);
		
		exception.expect(UnsupportedOperationException.class);
		arrayStatic.retainAll(retainBeans);
	}

	@Test
	public void testSetIntBeanType() {
		BeanPropertyString property3 = createNewReferencedTypeProperty();

		exception.expect(UnsupportedOperationException.class);
		arrayStatic.set(1, property3);
	}
}
