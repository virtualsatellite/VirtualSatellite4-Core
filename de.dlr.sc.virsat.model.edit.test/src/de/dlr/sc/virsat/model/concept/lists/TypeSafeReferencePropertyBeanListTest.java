/*******************************************************************************
 * Copyright (c) 2020 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.concept.lists;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import de.dlr.sc.virsat.model.concept.list.TypeSafeReferencePropertyBeanList;
import de.dlr.sc.virsat.model.concept.types.category.IBeanCategoryAssignment;
import de.dlr.sc.virsat.model.concept.types.property.BeanPropertyReference;
import de.dlr.sc.virsat.model.dvlm.categories.CategoriesFactory;
import de.dlr.sc.virsat.model.dvlm.categories.Category;
import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.ComposedProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.PropertydefinitionsFactory;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ArrayInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.PropertyinstancesFactory;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ReferencePropertyInstance;
import de.dlr.sc.virsat.model.dvlm.categories.util.CategoryAssignmentHelper;
import de.dlr.sc.virsat.model.dvlm.categories.util.CategoryInstantiator;

public class TypeSafeReferencePropertyBeanListTest {

	private ComposedProperty testProperty;
	private BeanPropertyReference<IBeanCategoryAssignment> bean;
	private ReferencePropertyInstance rpi;
	private TypeSafeReferencePropertyBeanList<IBeanCategoryAssignment> beanList;
	private static final String PROPERTY_NAME = "testProperty";

	@Before
	public void setUp() throws Exception {
		// Create test category
		Category testCategory = CategoriesFactory.eINSTANCE.createCategory();
		testProperty = PropertydefinitionsFactory.eINSTANCE.createComposedProperty();
		testProperty.setName(PROPERTY_NAME);
		testProperty.setArrayModifier(PropertydefinitionsFactory.eINSTANCE.createDynamicArrayModifier());
		testCategory.getProperties().add(testProperty);
		
		// Create test ca
		CategoryInstantiator categoryInstantiator = new CategoryInstantiator();
		CategoryAssignment testCA = categoryInstantiator.generateInstance(testCategory, "testCA");
		CategoryAssignmentHelper helper = new CategoryAssignmentHelper(testCA);

		// Create test reference property instance and bean
		rpi = PropertyinstancesFactory.eINSTANCE.createReferencePropertyInstance();
		rpi.setType(testProperty);
		bean = new BeanPropertyReference<IBeanCategoryAssignment>(rpi);
		
		// Create test list
		beanList = new TypeSafeReferencePropertyBeanList<IBeanCategoryAssignment>();
		beanList.setArrayInstance((ArrayInstance) helper.getPropertyInstance(PROPERTY_NAME));
	}

	@Test
	public void testConstructor() {
		assertEquals("The list is correctly typed", beanList.getBeanClazz(), BeanPropertyReference.class);
	}
	
	@Test
	public void testAdd() {
		assertTrue("The list is empty", beanList.getArrayInstance().getArrayInstances().isEmpty());
		
		beanList.add(bean);
		
		assertEquals("Now the list contains one element", beanList.getArrayInstance().getArrayInstances().size(), 1);
	}

	@Test
	public void testGet() {
		beanList.add(bean);
		
		assertEquals("The list contains the bean", bean, beanList.get(0));
	}

	@Test
	public void testSet() {
		beanList.add(bean);
		
		ReferencePropertyInstance rpi2 = PropertyinstancesFactory.eINSTANCE.createReferencePropertyInstance();
		rpi2.setType(testProperty);
		BeanPropertyReference<IBeanCategoryAssignment> bean2 = new BeanPropertyReference<IBeanCategoryAssignment>(rpi2);
		
		beanList.set(0, bean2);
		
		assertEquals("The list contains the new bean", bean2, beanList.get(0));
	}
	
	@Test
	public void testRemove() {
		beanList.add(bean);
		
		beanList.remove(bean);
		
		assertTrue("The list is empty again", beanList.getArrayInstance().getArrayInstances().isEmpty());
	}
	
}
