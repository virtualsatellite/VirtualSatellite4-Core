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

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import de.dlr.sc.virsat.model.concept.list.AArrayInstanceList;
import de.dlr.sc.virsat.model.concept.list.IBeanList;
import de.dlr.sc.virsat.model.concept.list.TypeSafeComposedPropertyInstanceList;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.ComposedProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.PropertydefinitionsFactory;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ArrayInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ComposedPropertyInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.PropertyinstancesFactory;
import de.dlr.sc.virsat.model.dvlm.categories.util.CategoryInstantiator;

/**
 * test case for the array capabilities on intrinsic properties in the beans model
 * @author fisc_ph
 *
 */
public class TestCategoryCompositionArrayDynamicTest extends AConceptTestCase {

	private TestCategoryCompositionArray tcIntrinsicArray;
	private IBeanList<TestCategoryAllProperty> arrayDynamic;
	private ArrayInstance ai;
	private CategoryInstantiator ci;
	
	/**
	 * Method to create a test property
	 * @return a new BeanPropertyString
	 */
	private TestCategoryAllProperty createNewComposedTypeProperty() {
		ComposedPropertyInstance cpi = (ComposedPropertyInstance) ci.generateInstance(ai);
		TestCategoryAllProperty newBeanProperty = new TestCategoryAllProperty();
		newBeanProperty.setTypeInstance((CategoryAssignment) cpi.getTypeInstance());
		return newBeanProperty;
	}

	@Before
	public void setup() {
		// Load the concept to create the test object
		Concept concept = loadConceptFromPlugin();
		tcIntrinsicArray = new TestCategoryCompositionArray(concept);
		arrayDynamic = tcIntrinsicArray.getTestCompositionArrayDynamic();
		ai = arrayDynamic.getArrayInstance();
		ci = new CategoryInstantiator(); 
	}

	@After
	public void tearDown() throws Exception {
	}
	
	private static final int LIST_WITH_THREE_ITEMS = 3;
	private static final int LIST_WITH_TWO_ITEMS = 2;
	private static final int LIST_WITH_ONE_ITEMS = 1;

	@Test
	public void testTypeSafeComposedPropertyInstanceList() {
		ComposedProperty cp = PropertydefinitionsFactory.eINSTANCE.createComposedProperty();
		ArrayInstance ai = PropertyinstancesFactory.eINSTANCE.createArrayInstance();
		ai.setType(cp);
		arrayDynamic = new TypeSafeComposedPropertyInstanceList<>(TestCategoryAllProperty.class, ai);
		
		@SuppressWarnings("rawtypes")
		Class typeClass = ((TypeSafeComposedPropertyInstanceList) arrayDynamic).getBeanClazz();
		assertEquals("Class TYpe has been well set", TestCategoryAllProperty.class, typeClass);
		assertEquals("Array Instance got well set", ai, arrayDynamic.getArrayInstance());
	}
	
	@Test
	public void testAddAllIntCollectionOfQextendsBeansType() {
		TestCategoryAllProperty property1 = createNewComposedTypeProperty();
		TestCategoryAllProperty property2 = createNewComposedTypeProperty();
		TestCategoryAllProperty property3 = createNewComposedTypeProperty();

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
		TestCategoryAllProperty property1 = createNewComposedTypeProperty();
		TestCategoryAllProperty property2 = createNewComposedTypeProperty();

		assertTrue("List is empty", arrayDynamic.isEmpty());
		
		arrayDynamic.add(property1);
		arrayDynamic.add(property2);
		
		assertEquals("List has two items", LIST_WITH_TWO_ITEMS, arrayDynamic.size());
	}

	@Test
	public void testAddIntBeanType() {
		TestCategoryAllProperty property1 = createNewComposedTypeProperty();
		TestCategoryAllProperty property2 = createNewComposedTypeProperty();
		TestCategoryAllProperty property3 = createNewComposedTypeProperty();

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
	public void testAddAll() {
		TestCategoryAllProperty property1 = createNewComposedTypeProperty();
		TestCategoryAllProperty property2 = createNewComposedTypeProperty();
		TestCategoryAllProperty property3 = createNewComposedTypeProperty();

		assertTrue("List is empty", arrayDynamic.isEmpty());

		arrayDynamic.add(property1);
		
		List<TestCategoryAllProperty> col = new ArrayList<>();
		col.add(property2);
		col.add(property3);
		
		arrayDynamic.addAll(1, col);
		
		TestCategoryAllProperty[] array = arrayDynamic.toArray(new TestCategoryAllProperty[0]);
		assertEquals("Correct properties are placed", property1, array[0]);
		assertEquals("Correct properties are placed", property2, array[1]);
		assertEquals("Correct properties are placed", property3, array[2]);
	}
	
	@Test
	public void testContains() {
		TestCategoryAllProperty property1 = createNewComposedTypeProperty();
		TestCategoryAllProperty property2 = createNewComposedTypeProperty();
		TestCategoryAllProperty property3 = createNewComposedTypeProperty();

		assertTrue("List is empty", arrayDynamic.isEmpty());
		
		arrayDynamic.add(property1);
		arrayDynamic.add(property2);

		assertTrue("List still contains property 1", arrayDynamic.contains(property1));		
		assertTrue("List still contains property 2", arrayDynamic.contains(property2));		
		assertFalse("List does not contain property 3", arrayDynamic.contains(property3));	
		assertFalse("Does not contain unkown Object", arrayDynamic.contains(new Object()));
	}

	@Test
	public void testContainsAll() {
		TestCategoryAllProperty property1 = createNewComposedTypeProperty();
		TestCategoryAllProperty property2 = createNewComposedTypeProperty();
		TestCategoryAllProperty property3 = createNewComposedTypeProperty();

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
		TestCategoryAllProperty property1 = createNewComposedTypeProperty();
		TestCategoryAllProperty property2 = createNewComposedTypeProperty();
	
		assertTrue("List is empty", arrayDynamic.isEmpty());
		
		arrayDynamic.add(property1);
		arrayDynamic.add(property2);
		
		assertFalse("List is not Empty", arrayDynamic.isEmpty());

		arrayDynamic.clear();
		assertTrue("List is empty", arrayDynamic.isEmpty());
	}
	
	@Test
	public void testGet() {
		TestCategoryAllProperty property1 = createNewComposedTypeProperty();
		TestCategoryAllProperty property2 = createNewComposedTypeProperty();

		assertTrue("List is empty", arrayDynamic.isEmpty());
		
		arrayDynamic.add(property1);
		arrayDynamic.add(property2);
		
		assertEquals("List has two items", LIST_WITH_TWO_ITEMS, arrayDynamic.size());
	
		assertEquals("Item at correct Position", property1, arrayDynamic.get(0));
		assertEquals("Item at correct Position", property2, arrayDynamic.get(1));
	}

	@Test
	public void testIndexOf() {
		TestCategoryAllProperty property1 = createNewComposedTypeProperty();
		TestCategoryAllProperty property2 = createNewComposedTypeProperty();

		assertTrue("List is empty", arrayDynamic.isEmpty());
		
		arrayDynamic.add(property1);
		arrayDynamic.add(property2);
		
		assertEquals("List has two items", LIST_WITH_TWO_ITEMS, arrayDynamic.size());
	
		assertEquals("Item at correct Position", 0, arrayDynamic.indexOf(property1));
		assertEquals("Item at correct Position", 1, arrayDynamic.indexOf(property2));
		assertEquals("Hand back proper index of unkwon object", AArrayInstanceList.INDEX_DOES_NOT_EXIST, arrayDynamic.indexOf(new Object()));
	}

	@Test
	public void testIterator() {
		TestCategoryAllProperty property1 = createNewComposedTypeProperty();
		TestCategoryAllProperty property2 = createNewComposedTypeProperty();
		
		arrayDynamic.add(property1);
		arrayDynamic.add(property2);
		
		Iterator<TestCategoryAllProperty> iterator = arrayDynamic.iterator();
		
		assertEquals("Iterator has correct item", property1, iterator.next());
		assertEquals("Iterator has correct item", property2, iterator.next());
	}

	@Test
	public void testLastIndexOf() {
		TestCategoryAllProperty property1 = createNewComposedTypeProperty();
		TestCategoryAllProperty property2 = createNewComposedTypeProperty();
	
		assertTrue("List is empty", arrayDynamic.isEmpty());
		
		arrayDynamic.add(property1);
		arrayDynamic.add(property2);
		
		// our lists are unique therefore one element cannot be stored twice.
		assertEquals("Last index of property1 is correct", 0, arrayDynamic.lastIndexOf(property1));
		assertEquals("Last index of property1 is correct", 1, arrayDynamic.lastIndexOf(property2));
		
		assertEquals("Hand back proper index of unkwon object", AArrayInstanceList.INDEX_DOES_NOT_EXIST, arrayDynamic.indexOf(new Object()));
	}

	@Test
	public void testListIterator() {
		TestCategoryAllProperty property1 = createNewComposedTypeProperty();
		TestCategoryAllProperty property2 = createNewComposedTypeProperty();
		
		arrayDynamic.add(property1);
		arrayDynamic.add(property2);
		
		ListIterator<TestCategoryAllProperty> iterator = arrayDynamic.listIterator();
		
		assertEquals("Iterator has correct item", property1, iterator.next());
		assertEquals("Iterator has correct item", property2, iterator.next());
	}

	@Test
	public void testListIteratorInt() {
		TestCategoryAllProperty property1 = createNewComposedTypeProperty();
		TestCategoryAllProperty property2 = createNewComposedTypeProperty();
		
		arrayDynamic.add(property1);
		arrayDynamic.add(property2);
		
		ListIterator<TestCategoryAllProperty> iterator = arrayDynamic.listIterator(1);
		
		assertEquals("Iterator has correct item", property2, iterator.next());
	}

	@Test
	public void testRemoveAll() {
		TestCategoryAllProperty property1 = createNewComposedTypeProperty();
		TestCategoryAllProperty property2 = createNewComposedTypeProperty();
		TestCategoryAllProperty property3 = createNewComposedTypeProperty();

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
		TestCategoryAllProperty property1 = createNewComposedTypeProperty();
		TestCategoryAllProperty property2 = createNewComposedTypeProperty();
		TestCategoryAllProperty property3 = createNewComposedTypeProperty();

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
	public void testRemoveObjectDynamic() {
		TestCategoryAllProperty propertyOne = createNewComposedTypeProperty();
		TestCategoryAllProperty propertyTwo = createNewComposedTypeProperty();

		boolean retVal;
		
		arrayDynamic.add(propertyOne);
		arrayDynamic.add(propertyTwo);
		
		assertTrue("propertyTwo is stored in the List", arrayDynamic.contains(propertyTwo));
		
		retVal = arrayDynamic.remove(propertyTwo);
		
		assertTrue("remove states succesafuly removal", retVal);
		assertFalse("propertyTwo is removed from the List", arrayDynamic.contains(propertyTwo));
		
		retVal = arrayDynamic.remove(propertyTwo);
		assertFalse("remove states failed removal", retVal);
		
		retVal = arrayDynamic.remove(new Object());
		assertFalse("remove failed on unkown object", retVal);
	}

	@SuppressFBWarnings(
			justification = "The Testcase actually needs to make sure that notrhing happens on the data structure",
			value = "DMI_VACUOUS_SELF_COLLECTION_CALL"
	)
	@Test
	public void testRetainAll() {
		TestCategoryAllProperty property1 = createNewComposedTypeProperty();
		TestCategoryAllProperty property2 = createNewComposedTypeProperty();
		TestCategoryAllProperty property3 = createNewComposedTypeProperty();

		
		assertFalse("There is no change on the list", arrayDynamic.retainAll(arrayDynamic));
		
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
		TestCategoryAllProperty property1 = createNewComposedTypeProperty();
		TestCategoryAllProperty property2 = createNewComposedTypeProperty();
		TestCategoryAllProperty property3 = createNewComposedTypeProperty();

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
		TestCategoryAllProperty property1 = createNewComposedTypeProperty();
		TestCategoryAllProperty property2 = createNewComposedTypeProperty();
		TestCategoryAllProperty property3 = createNewComposedTypeProperty();

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
		TestCategoryAllProperty property1 = createNewComposedTypeProperty();
		TestCategoryAllProperty property2 = createNewComposedTypeProperty();
		TestCategoryAllProperty property3 = createNewComposedTypeProperty();

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
		TestCategoryAllProperty property1 = createNewComposedTypeProperty();
		TestCategoryAllProperty property2 = createNewComposedTypeProperty();
		TestCategoryAllProperty property3 = createNewComposedTypeProperty();

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
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Test
	public void testSetBeanClazz() {
		((TypeSafeComposedPropertyInstanceList) arrayDynamic).setBeanClazz(TestCategoryReference.class);
		
		Class<?> clazzType = ((TypeSafeComposedPropertyInstanceList) arrayDynamic).getBeanClazz();
		assertEquals("Correct bean retrieved", TestCategoryReference.class, clazzType);
	}
	
	@Test
	public void testGetBeanClazz() {
		@SuppressWarnings("rawtypes")
		Class<?> clazzType = ((TypeSafeComposedPropertyInstanceList) arrayDynamic).getBeanClazz();
		assertEquals("Correct bean retrieved", TestCategoryAllProperty.class, clazzType);
	}
}
