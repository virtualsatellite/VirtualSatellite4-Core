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

import org.eclipse.emf.common.command.UnexecutableCommand;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import de.dlr.sc.virsat.model.concept.list.AArrayInstanceList;
import de.dlr.sc.virsat.model.concept.list.IBeanList;
import de.dlr.sc.virsat.model.concept.list.TypeSafeArrayInstanceList;
import de.dlr.sc.virsat.model.concept.types.property.BeanPropertyInt;
import de.dlr.sc.virsat.model.concept.types.property.BeanPropertyString;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.PropertydefinitionsFactory;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.StringProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.APropertyInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ArrayInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.PropertyinstancesFactory;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ValuePropertyInstance;
import de.dlr.sc.virsat.model.dvlm.categories.util.CategoryInstantiator;

/**
 * test case for the array capabilities on intrinsic properties in the beans model
 * @author fisc_ph
 *
 */
public class TestCategoryIntrinsicArrayDynamicTest extends AConceptTestCase {

	private TestCategoryIntrinsicArray tcIntrinsicArray;
	private IBeanList<BeanPropertyString> arrayDynamic;
	private ArrayInstance ai;
	private CategoryInstantiator ci;
	
	/**
	 * Method to create a test property
	 * @return a new BeanPropertyString
	 */
	private BeanPropertyString createNewStringProperty() {
		APropertyInstance pi = ci.generateInstance(ai);
		BeanPropertyString newBeanProperty = new BeanPropertyString();
		newBeanProperty.setTypeInstance((ValuePropertyInstance) pi);
		return newBeanProperty;
	}

	@Before
	public void setup() {
		// Load the concept to create the test object
		prepareEditingDomain();
		Concept concept = loadConceptFromPlugin();
		tcIntrinsicArray = new TestCategoryIntrinsicArray(concept);
		arrayDynamic = tcIntrinsicArray.getTestStringArrayDynamic();
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
	public void testTypeSafeArrayInstanceList() {
		StringProperty stringProperty = PropertydefinitionsFactory.eINSTANCE.createStringProperty();
		ArrayInstance ai = PropertyinstancesFactory.eINSTANCE.createArrayInstance();
		ai.setType(stringProperty);
		arrayDynamic = new TypeSafeArrayInstanceList<>(BeanPropertyString.class, ai);
		
		@SuppressWarnings("rawtypes")
		Class typeClass = ((TypeSafeArrayInstanceList) arrayDynamic).getBeanClazz();
		assertEquals("Class TYpe has been well set", BeanPropertyString.class, typeClass);
		assertEquals("Array Instance got well set", ai, arrayDynamic.getArrayInstance());
	}
	
	@Test
	public void testAddAllIntCollectionOfQextendsBeansType() {
		BeanPropertyString property1 = createNewStringProperty();
		BeanPropertyString property2 = createNewStringProperty();
		BeanPropertyString property3 = createNewStringProperty();

		assertTrue("List is empty", arrayDynamic.isEmpty());
		
		arrayDynamic.add(property1);

		assertEquals("List has one items", LIST_WITH_ONE_ITEMS, arrayDynamic.size());
		
		List<BeanPropertyString> addBeans = new ArrayList<>();
		addBeans.add(property2);
		addBeans.add(property3);
		
		arrayDynamic.addAll(addBeans);

		assertEquals("List has one item left ", LIST_WITH_THREE_ITEMS, arrayDynamic.size());
	}

	@Test
	public void testAddBeanType() {
		BeanPropertyString property1 = createNewStringProperty();
		BeanPropertyString property2 = createNewStringProperty();

		assertTrue("List is empty", arrayDynamic.isEmpty());
		
		arrayDynamic.add(property1);
		arrayDynamic.add(property2);
		
		assertEquals("List has two items", LIST_WITH_TWO_ITEMS, arrayDynamic.size());
	}
	
	@Test
	public void testAddBeanTypeCommand() {
		BeanPropertyString property1 = createNewStringProperty();
		BeanPropertyString property2 = createNewStringProperty();

		assertTrue("List is empty", arrayDynamic.isEmpty());
		
		arrayDynamic.add(editingDomain, property1).execute();
		arrayDynamic.add(editingDomain, property2).execute();
		
		assertEquals("List has two items", LIST_WITH_TWO_ITEMS, arrayDynamic.size());
	}

	@Test
	public void testAddIntBeanType() {
		BeanPropertyString property1 = createNewStringProperty();
		BeanPropertyString property2 = createNewStringProperty();
		BeanPropertyString property3 = createNewStringProperty();

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
		BeanPropertyString property1 = createNewStringProperty();
		BeanPropertyString property2 = createNewStringProperty();
		BeanPropertyString property3 = createNewStringProperty();

		assertTrue("List is empty", arrayDynamic.isEmpty());
		
		arrayDynamic.add(property1);
		arrayDynamic.add(property2);

		assertTrue("List still contains property 1", arrayDynamic.contains(property1));		
		assertTrue("List still contains property 2", arrayDynamic.contains(property2));		
		assertFalse("List does not contain property 3", arrayDynamic.contains(property3));
		assertFalse("List does not contain an unknown object", arrayDynamic.contains(new Object()));		
	}
	
	@Test
	public void testContainsAll() {
		BeanPropertyString property1 = createNewStringProperty();
		BeanPropertyString property2 = createNewStringProperty();
		BeanPropertyString property3 = createNewStringProperty();

		assertTrue("List is empty", arrayDynamic.isEmpty());
		
		arrayDynamic.add(property1);
		arrayDynamic.add(property2);
		
		List<BeanPropertyString> conatinsBeans = new ArrayList<>();
		conatinsBeans.add(property1);
		conatinsBeans.add(property2);

		assertTrue("List still contains all property ", arrayDynamic.containsAll(conatinsBeans));		

		conatinsBeans.add(property3);

		assertFalse("List not contains all property ", arrayDynamic.containsAll(conatinsBeans));		
	}
	
	@Test
	public void testClear() {
		BeanPropertyString property1 = createNewStringProperty();
		BeanPropertyString property2 = createNewStringProperty();
	
		assertTrue("List is empty", arrayDynamic.isEmpty());
		
		arrayDynamic.add(property1);
		arrayDynamic.add(property2);
		
		assertFalse("List is not Empty", arrayDynamic.isEmpty());

		arrayDynamic.clear();
		assertTrue("List is empty", arrayDynamic.isEmpty());
	}
	
	@Test
	public void testGet() {
		BeanPropertyString property1 = createNewStringProperty();
		BeanPropertyString property2 = createNewStringProperty();

		assertTrue("List is empty", arrayDynamic.isEmpty());
		
		arrayDynamic.add(property1);
		arrayDynamic.add(property2);
		
		assertEquals("List has two items", LIST_WITH_TWO_ITEMS, arrayDynamic.size());
	
		assertEquals("Item at correct Position", property1, arrayDynamic.get(0));
		assertEquals("Item at correct Position", property2, arrayDynamic.get(1));
	}

	@Test
	public void testIndexOf() {
		BeanPropertyString property1 = createNewStringProperty();
		BeanPropertyString property2 = createNewStringProperty();

		assertTrue("List is empty", arrayDynamic.isEmpty());
		
		arrayDynamic.add(property1);
		arrayDynamic.add(property2);
		
		assertEquals("List has two items", LIST_WITH_TWO_ITEMS, arrayDynamic.size());
	
		assertEquals("Item at correct Position", 0, arrayDynamic.indexOf(property1));
		assertEquals("Item at correct Position", 1, arrayDynamic.indexOf(property2));
		
		assertEquals("Handle unkown objects correctly", AArrayInstanceList.INDEX_DOES_NOT_EXIST, arrayDynamic.indexOf(new Object()));
	}

	@Test
	public void testIterator() {
		BeanPropertyString property1 = createNewStringProperty();
		BeanPropertyString property2 = createNewStringProperty();
		
		arrayDynamic.add(property1);
		arrayDynamic.add(property2);
		
		Iterator<BeanPropertyString> iterator = arrayDynamic.iterator();
		
		assertEquals("Iterator has correct item", property1, iterator.next());
		assertEquals("Iterator has correct item", property2, iterator.next());
	}

	@Test
	public void testLastIndexOf() {
		BeanPropertyString property1 = createNewStringProperty();
		BeanPropertyString property2 = createNewStringProperty();

		assertTrue("List is empty", arrayDynamic.isEmpty());
		
		arrayDynamic.add(property1);
		arrayDynamic.add(property2);
		
		// our lists are unique therefore one element cannot be stored twice.
		assertEquals("Last index of property1 is correct", 0, arrayDynamic.lastIndexOf(property1));
		assertEquals("Last index of property1 is correct", 1, arrayDynamic.lastIndexOf(property2));
		assertEquals("Handle unkown objects correctly", AArrayInstanceList.INDEX_DOES_NOT_EXIST, arrayDynamic.lastIndexOf(new Object()));
	}

	@Test
	public void testListIterator() {
		BeanPropertyString property1 = createNewStringProperty();
		BeanPropertyString property2 = createNewStringProperty();
		
		arrayDynamic.add(property1);
		arrayDynamic.add(property2);
		
		ListIterator<BeanPropertyString> iterator = arrayDynamic.listIterator();
		
		assertEquals("Iterator has correct item", property1, iterator.next());
		assertEquals("Iterator has correct item", property2, iterator.next());
	}

	@Test
	public void testListIteratorInt() {
		BeanPropertyString property1 = createNewStringProperty();
		BeanPropertyString property2 = createNewStringProperty();
		
		arrayDynamic.add(property1);
		arrayDynamic.add(property2);
		
		ListIterator<BeanPropertyString> iterator = arrayDynamic.listIterator(1);
		
		assertEquals("Iterator has correct item", property2, iterator.next());
	}

	@Test
	public void testRemoveAll() {
		BeanPropertyString property1 = createNewStringProperty();
		BeanPropertyString property2 = createNewStringProperty();
		BeanPropertyString property3 = createNewStringProperty();

		assertTrue("List is empty", arrayDynamic.isEmpty());
		
		arrayDynamic.add(property1);
		arrayDynamic.add(property2);
		arrayDynamic.add(property3);

		assertEquals("List has three items", LIST_WITH_THREE_ITEMS, arrayDynamic.size());
		
		List<BeanPropertyString> removeBeans = new ArrayList<>();
		removeBeans.add(property1);
		removeBeans.add(property3);
		
		arrayDynamic.removeAll(removeBeans);

		assertEquals("List has one item left ", LIST_WITH_ONE_ITEMS, arrayDynamic.size());
		assertTrue("List still contains property 2", arrayDynamic.contains(property2));
	}

	@Test
	public void testRemoveInt() {
		BeanPropertyString property1 = createNewStringProperty();
		BeanPropertyString property2 = createNewStringProperty();
		BeanPropertyString property3 = createNewStringProperty();

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
		BeanPropertyString propertyOne = createNewStringProperty();
		BeanPropertyString propertyTwo = createNewStringProperty();
		
		boolean retVal;
		
		arrayDynamic.add(propertyOne);
		arrayDynamic.add(propertyTwo);
		
		assertTrue("propertyTwo is stored in the List", arrayDynamic.contains(propertyTwo));
		
		retVal = arrayDynamic.remove(propertyTwo);
		
		assertFalse("propertyTwo is removed from the List", arrayDynamic.contains(propertyTwo));
		assertTrue("Object was removed is stated positive", retVal);
		
		retVal = arrayDynamic.remove(propertyTwo);
		assertFalse("Calling it a second time is stated negative", retVal);

		retVal = arrayDynamic.remove(new Object());
		assertFalse("Removing an unknown object is stated negative", retVal);
	}
	
	@Test
	public void testRemoveObjectCommand() {
		BeanPropertyString propertyOne = createNewStringProperty();
		BeanPropertyString propertyTwo = createNewStringProperty();
		
		arrayDynamic.add(propertyOne);
		arrayDynamic.add(propertyTwo);
		
		assertTrue("propertyTwo is stored in the List", arrayDynamic.contains(propertyTwo));
		
		arrayDynamic.remove(editingDomain, propertyTwo).execute();
		
		assertFalse("propertyTwo is removed from the List", arrayDynamic.contains(propertyTwo));
		assertTrue("propertyTwo is removed from the List", arrayDynamic.contains(propertyOne));
		
		assertEquals("Cannot remove a second time", UnexecutableCommand.INSTANCE, arrayDynamic.remove(editingDomain, propertyTwo));
		assertEquals("Cannot remove a random object", UnexecutableCommand.INSTANCE, arrayDynamic.remove(editingDomain, new Object()));
	}

	@SuppressFBWarnings(
			justification = "The Testcase actually needs to make sure that notrhing happens on the data structure",
			value = "DMI_VACUOUS_SELF_COLLECTION_CALL"
	)
	@Test
	public void testRetainAll() {
		BeanPropertyString property1 = createNewStringProperty();
		BeanPropertyString property2 = createNewStringProperty();
		BeanPropertyString property3 = createNewStringProperty();

		assertTrue("List is empty", arrayDynamic.isEmpty());
		
		arrayDynamic.add(property1);
		arrayDynamic.add(property2);
		arrayDynamic.add(property3);

		// All objects should stay in the list
		assertFalse("List stayed unchanged", arrayDynamic.retainAll(arrayDynamic));
		
		final int NO_ITEMS_FILLED_LIST = 3;
		assertEquals("List has three items", NO_ITEMS_FILLED_LIST, arrayDynamic.size());
		
		List<Object> retainBeans = new ArrayList<>();
		retainBeans.add(property1);
		retainBeans.add(property3);
		retainBeans.add(new Object()); // to catch if case in retainAll method
		
		arrayDynamic.retainAll(retainBeans);

		assertEquals("List has two item left ", 2, arrayDynamic.size());
		assertTrue("List still contains property 1", arrayDynamic.contains(property1));		
		assertTrue("List still contains property 1", arrayDynamic.contains(property3));
	}

	@Test
	public void testSetIntBeanType() {
		BeanPropertyString property1 = createNewStringProperty();
		BeanPropertyString property2 = createNewStringProperty();
		BeanPropertyString property3 = createNewStringProperty();

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
		BeanPropertyString property1 = createNewStringProperty();
		BeanPropertyString property2 = createNewStringProperty();
		BeanPropertyString property3 = createNewStringProperty();

		assertTrue("List is empty", arrayDynamic.isEmpty());
		
		arrayDynamic.add(property1);
		arrayDynamic.add(property2);
		arrayDynamic.add(property3);
		
		List<BeanPropertyString> subList = arrayDynamic.subList(0, 2);

		assertEquals("List has two items", LIST_WITH_TWO_ITEMS, subList.size());
		assertTrue("SubList contains property 1", subList.contains(property1));		
		assertTrue("SubList contains property 2", subList.contains(property2));		
	}

	@Test
	public void testToArray() {
		BeanPropertyString property1 = createNewStringProperty();
		BeanPropertyString property2 = createNewStringProperty();
		BeanPropertyString property3 = createNewStringProperty();

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
		BeanPropertyString property1 = createNewStringProperty();
		BeanPropertyString property2 = createNewStringProperty();
		BeanPropertyString property3 = createNewStringProperty();

		assertTrue("List is empty", arrayDynamic.isEmpty());
		
		arrayDynamic.add(property1);
		arrayDynamic.add(property2);
		arrayDynamic.add(property3);
		
		BeanPropertyString[] array = arrayDynamic.toArray(new BeanPropertyString[0]);
		
		assertEquals("Array Size is correct", LIST_WITH_THREE_ITEMS, array.length);
		assertEquals("Correct properties are placed", property1, array[0]);
		assertEquals("Correct properties are placed", property2, array[1]);
		assertEquals("Correct properties are placed", property3, array[2]);
	}
	
	@Test
	public void testAddAll() {
		BeanPropertyString property1 = createNewStringProperty();
		BeanPropertyString property2 = createNewStringProperty();
		BeanPropertyString property3 = createNewStringProperty();

		assertTrue("List is empty", arrayDynamic.isEmpty());

		arrayDynamic.add(property1);
		
		List<BeanPropertyString> col = new ArrayList<>();
		col.add(property2);
		col.add(property3);
		
		arrayDynamic.addAll(1, col);
		
		BeanPropertyString[] array = arrayDynamic.toArray(new BeanPropertyString[0]);
		assertEquals("Correct properties are placed", property1, array[0]);
		assertEquals("Correct properties are placed", property2, array[1]);
		assertEquals("Correct properties are placed", property3, array[2]);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Test
	public void testSetBeanClazz() {
		((TypeSafeArrayInstanceList) arrayDynamic).setBeanClazz(BeanPropertyInt.class);
		
		Class<?> clazzType = ((TypeSafeArrayInstanceList) arrayDynamic).getBeanClazz();
		assertEquals("Correct bean retrieved", BeanPropertyInt.class, clazzType);
	}
	
	@Test
	public void testGetBeanClazz() {
		@SuppressWarnings("rawtypes")
		Class<?> clazzType = ((TypeSafeArrayInstanceList) arrayDynamic).getBeanClazz();
		assertEquals("Correct bean retrieved", BeanPropertyString.class, clazzType);
	}
	
	
}
