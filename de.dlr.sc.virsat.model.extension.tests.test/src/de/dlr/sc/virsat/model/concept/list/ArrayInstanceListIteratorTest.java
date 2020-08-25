/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.concept.list;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ListIterator;
import java.util.NoSuchElementException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import de.dlr.sc.virsat.model.concept.types.property.BeanPropertyString;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.APropertyInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ArrayInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ValuePropertyInstance;
import de.dlr.sc.virsat.model.dvlm.categories.util.CategoryInstantiator;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.extension.tests.model.AConceptTestCase;
import de.dlr.sc.virsat.model.extension.tests.model.TestCategoryIntrinsicArray;

/**
 * Test Cases for the List iterators of the ArrayInstanceLists
 * @author fisc_ph
 *
 */
public class ArrayInstanceListIteratorTest extends AConceptTestCase {

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

	private BeanPropertyString property1;
	private BeanPropertyString property2;
	
	private ListIterator<BeanPropertyString> iterator;
	
	@Before
	public void setup() {
		// Load the concept to create the test object
		Concept concept = loadConceptFromPlugin();
		tcIntrinsicArray = new TestCategoryIntrinsicArray(concept);
		arrayDynamic = tcIntrinsicArray.getTestStringArrayDynamicBean();
		ai = arrayDynamic.getArrayInstance();
		ci = new CategoryInstantiator(); 
		
		property1 = createNewStringProperty();
		property2 = createNewStringProperty();
		
		arrayDynamic.add(property1);
		arrayDynamic.add(property2);
		
		iterator = arrayDynamic.listIterator();
	}
	
	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void testHasNext() {
		assertTrue("Iterator has first item", iterator.hasNext());
		
		iterator.next();
		assertTrue("Iterator has second item", iterator.hasNext());

		iterator.next();
		assertFalse("Iterator has no third item", iterator.hasNext());
	}

	@Test(expected = NoSuchElementException.class)
	public void testNext() {
		assertEquals("Next returns correct item", property1, iterator.next());
		assertEquals("Next returns correct item", property2, iterator.next());
		
		iterator.next();
	}

	@Test
	public void testHasPrevious() {
		iterator = arrayDynamic.listIterator(2);

		assertTrue("Iterator has first item", iterator.hasPrevious());
		
		iterator.previous();
		assertTrue("Iterator has second item", iterator.hasPrevious());

		iterator.previous();
		assertFalse("Iterator has no third item", iterator.hasPrevious());
	}

	@Test(expected = NoSuchElementException.class)
	public void testPrevious() {
		iterator = arrayDynamic.listIterator(2);
		
		assertEquals("Next returns correct item", property2, iterator.previous());
		assertEquals("Next returns correct item", property1, iterator.previous());
		
		iterator.previous();
	}
	
	@Test
	public void testPreviousNext() {
		assertEquals("Next returns correct item", property1, iterator.next());
		assertEquals("Next returns correct item", property1, iterator.previous());
		assertEquals("Next returns correct item", property1, iterator.next());
		assertEquals("Next returns correct item", property1, iterator.previous());
	}

	@Test
	public void testNextIndex() {
		assertEquals("Iterator starts at index 0", 0, iterator.nextIndex());
		
		iterator.next();
		iterator.next();
		
		assertEquals("iterator should have list size", 2, iterator.nextIndex());
	}

	@Test
	public void testPreviousIndex() {
		iterator = arrayDynamic.listIterator(2);
		assertEquals("Iterator starts at index 1", 1, iterator.previousIndex());

		iterator.previous();
		iterator.previous();
		
		assertEquals("Iterator at start returns -1", -1, iterator.previousIndex());
	}

	@Test(expected = IllegalStateException.class)
	public void testRemove() {
		BeanPropertyString property = iterator.next();
		
		iterator.remove();
		assertFalse("The property is not part of the list anymore", arrayDynamic.contains(property));
		assertEquals("Index ahs not moved", 0, iterator.nextIndex());
		
		iterator.remove();
	}

	@Test(expected = IllegalStateException.class)
	public void testSet() {
		BeanPropertyString oldProperty = iterator.next();
		BeanPropertyString newProperty = createNewStringProperty();
		
		iterator.set(newProperty);
		assertFalse("The property is not part of the list anymore", arrayDynamic.contains(oldProperty));
		assertTrue("The new property is now part of the list", arrayDynamic.contains(newProperty));
		assertEquals("Index ahs not moved", 0, iterator.nextIndex());
		assertEquals("The list did not grow", 2, arrayDynamic.size());
		
		iterator.set(newProperty);
	}

	@Test(expected = IllegalStateException.class)
	public void testAdd() {
		BeanPropertyString oldProperty = iterator.next();
		BeanPropertyString newProperty = createNewStringProperty();
		
		iterator.add(newProperty);
		
		assertEquals("Calling next returns the next element proeprty 2", property2, iterator.next());
		
		// Calling now two times previous should hand back the newly added item
		iterator.previous();
		assertEquals("Calling next returns the new property", newProperty, iterator.previous());
		assertTrue("The old property is not replaced", arrayDynamic.contains(oldProperty));

		iterator.add(createNewStringProperty());
		iterator.add(createNewStringProperty());
	}
}
