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
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertThrows;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.UnexecutableCommand;
import org.junit.Before;
import org.junit.Test;

import de.dlr.sc.virsat.model.concept.list.IBeanList;
import de.dlr.sc.virsat.model.concept.types.property.BeanPropertyString;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.APropertyInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ArrayInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ValuePropertyInstance;
import de.dlr.sc.virsat.model.dvlm.categories.util.CategoryInstantiator;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;

/**
 * test case for the array capabilities on intrinsic properties in the beans model
 */
public class TestCategoryIntrinsicArrayStaticTest extends AConceptTestCase {

	private TestCategoryIntrinsicArray tcIntrinsicArray;
	private IBeanList<BeanPropertyString> arrayStatic;
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
		prepareEditingDomain();
		// Load the concept to create the test object
		Concept concept = loadConceptFromPlugin();
		tcIntrinsicArray = new TestCategoryIntrinsicArray(concept);
		arrayStatic = tcIntrinsicArray.getTestStringArrayStaticBean();
		ai = arrayStatic.getArrayInstance();
		ci = new CategoryInstantiator(); 
	}
	
	private static final int LIST_WITH_STATIC_SIZE = 4;
	
	@Test(expected = UnsupportedOperationException.class)
	public void testAddAllIntCollectionOfQextendsBeansType() {
		BeanPropertyString property1 = createNewStringProperty();
		BeanPropertyString property2 = createNewStringProperty();
		BeanPropertyString property3 = createNewStringProperty();

		assertEquals("List has four items", LIST_WITH_STATIC_SIZE, arrayStatic.size());
		
		List<BeanPropertyString> addBeans = new ArrayList<>();
		addBeans.add(property1);
		addBeans.add(property2);
		addBeans.add(property3);
		
		arrayStatic.addAll(addBeans);
	}

	@Test
	public void testAddBeanType() {
		BeanPropertyString property1 = createNewStringProperty();
		BeanPropertyString property2 = createNewStringProperty();
		BeanPropertyString property3 = createNewStringProperty();
		
		assertEquals("List has four items", LIST_WITH_STATIC_SIZE, arrayStatic.size());
		
		assertNotEquals(property1.getTypeInstance(), arrayStatic.get(0).getTypeInstance());
		arrayStatic.add(property1);
		assertEquals("Added to the correct default pointer of 0", property1.getTypeInstance(), arrayStatic.get(0).getTypeInstance());
		
		assertThrows("Can't add a duplicate", IllegalArgumentException.class, () -> {
			arrayStatic.add(property1);
		});
		
		int lastIndex = LIST_WITH_STATIC_SIZE - 1;
		assertNotEquals(property2.getTypeInstance(), arrayStatic.get(lastIndex).getTypeInstance());
		arrayStatic.setPointer(lastIndex);
		arrayStatic.add(property2);
		assertEquals("Added to the correct pointer", property2.getTypeInstance(), arrayStatic.get(lastIndex).getTypeInstance());
		
		arrayStatic.setPointer(LIST_WITH_STATIC_SIZE);
		assertThrows("A fifth element does not exist", UnsupportedOperationException.class, () -> {
			arrayStatic.add(property3);
		});
	}

	@Test
	public void testAddBeanTypeCommand() {
		BeanPropertyString property1 = createNewStringProperty();
		
		assertEquals("List has four items", LIST_WITH_STATIC_SIZE, arrayStatic.size());
			
		Command command = arrayStatic.add(editingDomain, property1);
		assertEquals("Command cannot be executed", UnexecutableCommand.INSTANCE, command);
	}
	
	@Test(expected = UnsupportedOperationException.class)
	public void testAddIntBeanType() {
		BeanPropertyString property1 = createNewStringProperty();

		assertEquals("List has four items", LIST_WITH_STATIC_SIZE, arrayStatic.size());
		
		arrayStatic.add(1, property1);
	}
	
	@Test(expected = UnsupportedOperationException.class)
	public void testClear() {
		arrayStatic.clear();
	}

	@Test(expected = UnsupportedOperationException.class)
	public void testRemoveAll() {
		BeanPropertyString property1 = createNewStringProperty();
		BeanPropertyString property3 = createNewStringProperty();

		assertEquals("List has four items", LIST_WITH_STATIC_SIZE, arrayStatic.size());
		
		List<BeanPropertyString> removeBeans = new ArrayList<>();
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
		BeanPropertyString propertyOne = createNewStringProperty();

		arrayStatic.remove(propertyOne);
	}
	
	@Test
	public void testRemoveObjectCommand() {
		BeanPropertyString property1 = createNewStringProperty();
		
		assertEquals("List has four items", LIST_WITH_STATIC_SIZE, arrayStatic.size());
			
		Command command = arrayStatic.remove(editingDomain, property1);
		assertEquals("Command cannot be executed", UnexecutableCommand.INSTANCE, command);
	}

	@Test(expected = UnsupportedOperationException.class)
	public void testRetainAll() {
		BeanPropertyString property1 = createNewStringProperty();
		BeanPropertyString property3 = createNewStringProperty();
		
		List<BeanPropertyString> retainBeans = new ArrayList<>();
		retainBeans.add(property1);
		retainBeans.add(property3);
		
		arrayStatic.retainAll(retainBeans);
	}

	@Test
	public void testSetIntBeanType() {
		BeanPropertyString property3 = createNewStringProperty();
		
		arrayStatic.set(1, property3);
		assertEquals("Property set correctly", property3, arrayStatic.get(1));
		
		arrayStatic.set(1, property3);
		assertEquals("Property reset correctly", property3, arrayStatic.get(1));
		
		assertThrows("Can't set an element that is already in the list", IllegalArgumentException.class, () -> {
			arrayStatic.set(0, property3);
		});
		
		assertThrows("A fifth element does not exist", IndexOutOfBoundsException.class, () -> {
			arrayStatic.set(LIST_WITH_STATIC_SIZE, property3);
		});
	}
}
