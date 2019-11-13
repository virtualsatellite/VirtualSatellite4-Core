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
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import org.eclipse.emf.common.command.Command;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import de.dlr.sc.virsat.model.concept.list.IBeanList;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.PropertyinstancesFactory;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ReferencePropertyInstance;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;

/**
 * test case for the array capabilities on intrinsic properties in the beans model
 * @author fisc_ph
 *
 */
public class TestCategoryReferenceCategoryArrayDynamicTest extends AConceptTestCase {

	private TestCategoryReferenceArray tcIntrinsicArray;
	private IBeanList<TestCategoryAllProperty> arrayDynamic;
	private Concept concept;
	
	/**
	 * Method to create a test property
	 * @return a new BeanPropertyString
	 */
	private TestCategoryAllProperty createNewCategoryBean() {
		TestCategoryAllProperty newCategoryBean = new TestCategoryAllProperty(concept);
		return newCategoryBean;
	}

	@Before
	public void setup() {
		// Load the concept to create the test object
		concept = loadConceptFromPlugin();
		tcIntrinsicArray = new TestCategoryReferenceArray(concept);
		arrayDynamic = tcIntrinsicArray.getTestCategoryReferenceArrayDynamic();
		prepareEditingDomain();
	}

	@After
	public void tearDown() throws Exception {
	}
	
	private static final int LIST_WITH_THREE_ITEMS = 3;
	private static final int LIST_WITH_TWO_ITEMS = 2;
	private static final int LIST_WITH_ONE_ITEMS = 1;
	private static final int THREE = 3;
	
	@Test
	public void testAddAllIntCollectionOfQextendsBeansType() {
		TestCategoryAllProperty property1 = createNewCategoryBean();
		TestCategoryAllProperty property2 = createNewCategoryBean();
		TestCategoryAllProperty property3 = createNewCategoryBean();

		assertTrue("List is empty", arrayDynamic.isEmpty());
		
		arrayDynamic.add(property1);

		assertEquals("List has one items", LIST_WITH_ONE_ITEMS, arrayDynamic.size());
		
		List<TestCategoryAllProperty> addBeans = new ArrayList<>();
		addBeans.add(property2);
		addBeans.add(property3);
		
		arrayDynamic.addAll(addBeans);

		assertEquals("List has one item left ", LIST_WITH_THREE_ITEMS, arrayDynamic.size());
	}

	@Test
	public void testAddBeanType() {
		TestCategoryAllProperty property1 = createNewCategoryBean();
		TestCategoryAllProperty property2 = createNewCategoryBean();

		assertTrue("List is empty", arrayDynamic.isEmpty());
		
		arrayDynamic.add(property1);
		arrayDynamic.add(property2);
		
		assertEquals("List has two items", LIST_WITH_TWO_ITEMS, arrayDynamic.size());
	}
	
	@Test
	public void testAddBeanTypeCommand() {
		TestCategoryAllProperty property1 = createNewCategoryBean();
		TestCategoryAllProperty property2 = createNewCategoryBean();

		assertTrue("List is empty", arrayDynamic.isEmpty());
		
		Command command1 = arrayDynamic.add(editingDomain, property1);
		Command command2 = arrayDynamic.add(editingDomain, property2);
		
		assertTrue("Can execute command1", command1.canExecute());
		assertTrue("Can execute command2", command2.canExecute());
		
		command1.execute();
		command2.execute();
		
		assertEquals("List has two items", LIST_WITH_TWO_ITEMS, arrayDynamic.size());
	}

	@Test
	public void testAddIntBeanType() {
		TestCategoryAllProperty property1 = createNewCategoryBean();
		TestCategoryAllProperty property2 = createNewCategoryBean();
		TestCategoryAllProperty property3 = createNewCategoryBean();

		assertTrue("List is empty", arrayDynamic.isEmpty());
		
		arrayDynamic.add(property1);
		arrayDynamic.add(property2);
		
		assertEquals("List has two items", LIST_WITH_TWO_ITEMS, arrayDynamic.size());
		arrayDynamic.add(1, property3);
	
		assertEquals("List has now three items", LIST_WITH_THREE_ITEMS, arrayDynamic.size());
		
		assertEquals("Property3 got placed correctly", property3, arrayDynamic.get(1));
		assertEquals("Property2 got Shifted correctly", property2, arrayDynamic.get(2));
	}

	@Test
	public void testContains() {
		TestCategoryAllProperty property1 = createNewCategoryBean();
		TestCategoryAllProperty property2 = createNewCategoryBean();
		TestCategoryAllProperty property3 = createNewCategoryBean();

		assertTrue("List is empty", arrayDynamic.isEmpty());
		
		arrayDynamic.add(property1);
		arrayDynamic.add(property2);

		assertTrue("List still contains property 1", arrayDynamic.contains(property1));		
		assertTrue("List still contains property 2", arrayDynamic.contains(property2));		
		assertFalse("List does not contain property 3", arrayDynamic.contains(property3));		
	}

	@Test
	public void testContainsAll() {
		TestCategoryAllProperty property1 = createNewCategoryBean();
		TestCategoryAllProperty property2 = createNewCategoryBean();
		TestCategoryAllProperty property3 = createNewCategoryBean();

		assertTrue("List is empty", arrayDynamic.isEmpty());
		
		arrayDynamic.add(property1);
		arrayDynamic.add(property2);
		
		List<TestCategoryAllProperty> conatinsBeans = new ArrayList<>();
		conatinsBeans.add(property1);
		conatinsBeans.add(property2);

		assertTrue("List still contains all property ", arrayDynamic.containsAll(conatinsBeans));		

		conatinsBeans.add(property3);

		assertFalse("List not contains all property ", arrayDynamic.containsAll(conatinsBeans));		
	}
	
	@Test
	public void testClear() {
		TestCategoryAllProperty property1 = createNewCategoryBean();
		TestCategoryAllProperty property2 = createNewCategoryBean();
	
		assertTrue("List is empty", arrayDynamic.isEmpty());
		
		arrayDynamic.add(property1);
		arrayDynamic.add(property2);
		
		assertFalse("List is not Empty", arrayDynamic.isEmpty());

		arrayDynamic.clear();
		assertTrue("List is empty", arrayDynamic.isEmpty());
	}
	
	@Test
	public void testGet() {
		TestCategoryAllProperty property1 = createNewCategoryBean();
		TestCategoryAllProperty property2 = createNewCategoryBean();

		assertTrue("List is empty", arrayDynamic.isEmpty());
		
		arrayDynamic.add(property1);
		arrayDynamic.add(property2);
		
		assertEquals("List has two items", LIST_WITH_TWO_ITEMS, arrayDynamic.size());
	
		assertEquals("Item at correct Position", property1, arrayDynamic.get(0));
		assertEquals("Item at correct Position", property2, arrayDynamic.get(1));
	}

	@Test
	public void testIndexOf() {
		TestCategoryAllProperty property1 = createNewCategoryBean();
		TestCategoryAllProperty property2 = createNewCategoryBean();

		assertTrue("List is empty", arrayDynamic.isEmpty());
		
		arrayDynamic.add(property1);
		arrayDynamic.add(property2);
		
		assertEquals("List has two items", LIST_WITH_TWO_ITEMS, arrayDynamic.size());
	
		assertEquals("Item at correct Position", 0, arrayDynamic.indexOf(property1));
		assertEquals("Item at correct Position", 1, arrayDynamic.indexOf(property2));
	}

	@Test
	public void testIterator() {
		TestCategoryAllProperty property1 = createNewCategoryBean();
		TestCategoryAllProperty property2 = createNewCategoryBean();
		
		arrayDynamic.add(property1);
		arrayDynamic.add(property2);
		
		Iterator<TestCategoryAllProperty> iterator = arrayDynamic.iterator();
		
		assertEquals("Iterator has correct item", property1, iterator.next());
		assertEquals("Iterator has correct item", property2, iterator.next());
	}

	@Test
	public void testLastIndexOf() {
		TestCategoryAllProperty property1 = createNewCategoryBean();
		TestCategoryAllProperty property2 = createNewCategoryBean();

		assertTrue("List is empty", arrayDynamic.isEmpty());
		
		arrayDynamic.add(property1);
		arrayDynamic.add(property2);
		arrayDynamic.add(property1);

		// One and the same property can be referenced a couple of times
		assertEquals("List has two items", LIST_WITH_THREE_ITEMS, arrayDynamic.size());
	}

	@Test
	public void testListIterator() {
		TestCategoryAllProperty property1 = createNewCategoryBean();
		TestCategoryAllProperty property2 = createNewCategoryBean();
		
		arrayDynamic.add(property1);
		arrayDynamic.add(property2);
		
		ListIterator<TestCategoryAllProperty> iterator = arrayDynamic.listIterator();
		
		assertEquals("Iterator has correct item", property1, iterator.next());
		assertEquals("Iterator has correct item", property2, iterator.next());
	}

	@Test
	public void testListIteratorInt() {
		TestCategoryAllProperty property1 = createNewCategoryBean();
		TestCategoryAllProperty property2 = createNewCategoryBean();
		
		arrayDynamic.add(property1);
		arrayDynamic.add(property2);
		
		ListIterator<TestCategoryAllProperty> iterator = arrayDynamic.listIterator(1);
		
		assertEquals("Iterator has correct item", property2, iterator.next());
	}

	@Test
	public void testRemoveAll() {
		TestCategoryAllProperty property1 = createNewCategoryBean();
		TestCategoryAllProperty property2 = createNewCategoryBean();
		TestCategoryAllProperty property3 = createNewCategoryBean();

		assertTrue("List is empty", arrayDynamic.isEmpty());
		
		arrayDynamic.add(property1);
		arrayDynamic.add(property2);
		arrayDynamic.add(property3);

		assertEquals("List has three items", LIST_WITH_THREE_ITEMS, arrayDynamic.size());
		
		List<TestCategoryAllProperty> removeBeans = new ArrayList<>();
		removeBeans.add(property1);
		removeBeans.add(property3);
		
		arrayDynamic.removeAll(removeBeans);

		assertEquals("List has one item left ", LIST_WITH_ONE_ITEMS, arrayDynamic.size());
		assertTrue("List still contains property 2", arrayDynamic.contains(property2));
	}

	@Test
	public void testRemoveInt() {
		TestCategoryAllProperty property1 = createNewCategoryBean();
		TestCategoryAllProperty property2 = createNewCategoryBean();
		TestCategoryAllProperty property3 = createNewCategoryBean();

		assertTrue("List is empty", arrayDynamic.isEmpty());
		
		arrayDynamic.add(property1);
		arrayDynamic.add(property2);
		arrayDynamic.add(property3);

		final int NO_ITEMS_FILLED_LIST = 3;
		assertEquals("List has three items", NO_ITEMS_FILLED_LIST, arrayDynamic.size());
		
		arrayDynamic.remove(1);

		assertEquals("List has two item left ", 2, arrayDynamic.size());
		assertTrue("List still contains property 1", arrayDynamic.contains(property1));		
		assertTrue("List still contains property 1", arrayDynamic.contains(property3));
	}

	@Test
	public void testRemoveObject() {
		TestCategoryAllProperty propertyOne = createNewCategoryBean();
		TestCategoryAllProperty propertyTwo = createNewCategoryBean();
		
		arrayDynamic.add(propertyOne);
		arrayDynamic.add(propertyTwo);
		
		assertTrue("propertyTwo is stored in the List", arrayDynamic.contains(propertyTwo));
		
		arrayDynamic.remove(propertyTwo);
		
		assertFalse("propertyTwo is removed from the List", arrayDynamic.contains(propertyTwo));
	}
	
	@Test
	public void testRemoveObjectCommand() {
		TestCategoryAllProperty propertyOne = createNewCategoryBean();
		TestCategoryAllProperty propertyTwo = createNewCategoryBean();
		
		arrayDynamic.add(propertyOne);
		arrayDynamic.add(propertyTwo);
		
		assertTrue("propertyTwo is stored in the List", arrayDynamic.contains(propertyTwo));
		
		Command command = arrayDynamic.remove(editingDomain, propertyTwo);
		assertTrue("Command can be executed", command.canExecute());
		
		command.execute();
		
		assertFalse("propertyTwo is removed from the List", arrayDynamic.contains(propertyTwo));
	}

	@Test
	public void testRetainAll() {
		TestCategoryAllProperty property1 = createNewCategoryBean();
		TestCategoryAllProperty property2 = createNewCategoryBean();
		TestCategoryAllProperty property3 = createNewCategoryBean();

		assertTrue("List is empty", arrayDynamic.isEmpty());
		
		arrayDynamic.add(property1);
		arrayDynamic.add(property2);
		arrayDynamic.add(property3);

		final int NO_ITEMS_FILLED_LIST = 3;
		assertEquals("List has three items", NO_ITEMS_FILLED_LIST, arrayDynamic.size());
		
		List<TestCategoryAllProperty> retainBeans = new ArrayList<>();
		retainBeans.add(property1);
		retainBeans.add(property3);
		
		arrayDynamic.retainAll(retainBeans);

		assertEquals("List has two item left ", 2, arrayDynamic.size());
		assertTrue("List still contains property 1", arrayDynamic.contains(property1));		
		assertTrue("List still contains property 1", arrayDynamic.contains(property3));
	}

	@Test
	public void testSetIntBeanType() {
		TestCategoryAllProperty property1 = createNewCategoryBean();
		TestCategoryAllProperty property2 = createNewCategoryBean();
		TestCategoryAllProperty property3 = createNewCategoryBean();

		assertTrue("List is empty", arrayDynamic.isEmpty());
		
		arrayDynamic.add(property1);
		arrayDynamic.add(property2);
		
		assertEquals("List has two items", LIST_WITH_TWO_ITEMS, arrayDynamic.size());
		assertEquals("Property2 got Shifted correctly", property2, arrayDynamic.get(1));
		
		arrayDynamic.set(1, property3);
		assertEquals("List has two items", LIST_WITH_TWO_ITEMS, arrayDynamic.size());
		assertEquals("Property3 got Shifted correctly", property3, arrayDynamic.get(1));

	}

	@Test
	public void testSubList() {
		TestCategoryAllProperty property1 = createNewCategoryBean();
		TestCategoryAllProperty property2 = createNewCategoryBean();
		TestCategoryAllProperty property3 = createNewCategoryBean();

		assertTrue("List is empty", arrayDynamic.isEmpty());
		
		arrayDynamic.add(property1);
		arrayDynamic.add(property2);
		arrayDynamic.add(property3);
		
		List<TestCategoryAllProperty> subList = arrayDynamic.subList(0, 2);

		assertEquals("List has two items", LIST_WITH_TWO_ITEMS, subList.size());
		assertTrue("SubList contains property 1", subList.contains(property1));		
		assertTrue("SubList contains property 2", subList.contains(property2));		
	}

	@Test
	public void testToArray() {
		TestCategoryAllProperty property1 = createNewCategoryBean();
		TestCategoryAllProperty property2 = createNewCategoryBean();
		TestCategoryAllProperty property3 = createNewCategoryBean();

		assertTrue("List is empty", arrayDynamic.isEmpty());
		
		arrayDynamic.add(property1);
		arrayDynamic.add(property2);
		arrayDynamic.add(property3);
		
		Object[] array = arrayDynamic.toArray();
		
		assertEquals("Array Size is correct", LIST_WITH_THREE_ITEMS, array.length);
		assertEquals("Correct properties are placed", property1, array[0]);
		assertEquals("Correct properties are placed", property2, array[1]);
		assertEquals("Correct properties are placed", property3, array[2]);
	}	

	@Test
	public void testToArrayTArray() {
		TestCategoryAllProperty property1 = createNewCategoryBean();
		TestCategoryAllProperty property2 = createNewCategoryBean();
		TestCategoryAllProperty property3 = createNewCategoryBean();

		assertTrue("List is empty", arrayDynamic.isEmpty());
		
		arrayDynamic.add(property1);
		arrayDynamic.add(property2);
		arrayDynamic.add(property3);
		
		TestCategoryAllProperty[] array = arrayDynamic.toArray(new TestCategoryAllProperty[0]);
		
		assertEquals("Array Size is correct", LIST_WITH_THREE_ITEMS, array.length);
		assertEquals("Correct properties are placed", property1, array[0]);
		assertEquals("Correct properties are placed", property2, array[1]);
		assertEquals("Correct properties are placed", property3, array[2]);
	}
	
	@Test
	public void testArrayContainsAndIndexOf() {
		TestCategoryAllProperty category1 = createNewCategoryBean();
		TestCategoryAllProperty category2 = createNewCategoryBean();
		TestCategoryAllProperty category3 = createNewCategoryBean();
		Object invalidObject = new Object();

		assertTrue("List is empty", arrayDynamic.isEmpty());
		assertFalse("Empty List does not contain null", arrayDynamic.contains(null));
		assertEquals("Empty List does not contain null", -1, arrayDynamic.indexOf(null));
		assertFalse("Empty List does not contain valid category", arrayDynamic.contains(category1));
		assertEquals("Empty List does not contain valid category", -1, arrayDynamic.indexOf(category1));
		assertFalse("Empty List does not contain random object", arrayDynamic.contains(invalidObject));
		assertEquals("Empty List does not contain random object", -1, arrayDynamic.indexOf(invalidObject));
		
		arrayDynamic.add(category1);
		arrayDynamic.add(category2);
		
		assertFalse("List does not contain null", arrayDynamic.contains(null));
		assertEquals("List does not contain null", -1, arrayDynamic.indexOf(null));

		ReferencePropertyInstance nullReference = PropertyinstancesFactory.eINSTANCE.createReferencePropertyInstance();
		nullReference.setType(arrayDynamic.getArrayInstance().getType());
		nullReference.setReference(null);
		arrayDynamic.getArrayInstance().getArrayInstances().add(nullReference);

		assertTrue("List contains null", arrayDynamic.contains(null));
		assertEquals("List contains null", 2, arrayDynamic.indexOf(null));

		assertTrue("List contains category", arrayDynamic.contains(category1));
		assertEquals("List contains category", 0, arrayDynamic.indexOf(category1));
		assertTrue("List contains category", arrayDynamic.contains(category2));
		assertEquals("List contains category", 1, arrayDynamic.indexOf(category2));
		assertFalse("List does not contain category", arrayDynamic.contains(category3));
		assertEquals("List does not contain category", -1, arrayDynamic.indexOf(category3));

		assertFalse("List does not contain random object", arrayDynamic.contains(invalidObject));
		assertEquals("List does not contain random object", -1, arrayDynamic.indexOf(invalidObject));
		
		arrayDynamic.add(category1);
		
		assertEquals("First indexOf correct", 0, arrayDynamic.indexOf(category1));
		assertEquals("lastIndexOf correct", THREE, arrayDynamic.lastIndexOf(category1));
	}
}
