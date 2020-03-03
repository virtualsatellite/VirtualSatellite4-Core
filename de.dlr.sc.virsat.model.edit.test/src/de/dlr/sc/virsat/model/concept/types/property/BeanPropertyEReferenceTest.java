/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.concept.types.property;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import de.dlr.sc.virsat.model.dvlm.categories.CategoriesFactory;
import de.dlr.sc.virsat.model.dvlm.categories.Category;
import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.EReferenceProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.PropertydefinitionsFactory;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ArrayInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.EReferencePropertyInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.PropertyinstancesFactory;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.dvlm.concepts.ConceptsFactory;

public class BeanPropertyEReferenceTest {
	
	private Concept testConcept;
	private Category testCategory;
	private EReferenceProperty testProperty;
	private static final String PROPERTY_NAME = "testProperty";
	
	private CategoryAssignment testCA;
	private ArrayInstance propertyInstance;
	
	private BeanPropertyEReference<CategoryAssignment> testBean;
	
	private CategoryAssignment testPropertyValue;
	
	@Before
	public void setUp() throws Exception {
		testConcept = ConceptsFactory.eINSTANCE.createConcept();
		testCategory = CategoriesFactory.eINSTANCE.createCategory();
		testProperty = PropertydefinitionsFactory.eINSTANCE.createEReferenceProperty();
		testProperty.setName(PROPERTY_NAME);
		testProperty.setArrayModifier(PropertydefinitionsFactory.eINSTANCE.createDynamicArrayModifier());
		
		testConcept.getCategories().add(testCategory);
		testCategory.getProperties().add(testProperty);
		
		testCA = CategoriesFactory.eINSTANCE.createCategoryAssignment();
		propertyInstance = PropertyinstancesFactory.eINSTANCE.createArrayInstance();
		propertyInstance.setType(testProperty);
		testProperty.setReferenceType(testCA.eClass());
		testCA.getPropertyInstances().add(propertyInstance);
		
		testBean = new BeanPropertyEReference<CategoryAssignment>();
		EReferencePropertyInstance eReferencePropertyInstance = PropertyinstancesFactory.eINSTANCE.createEReferencePropertyInstance();
		eReferencePropertyInstance.setType(testProperty);
		testBean.setTypeInstance(eReferencePropertyInstance);
		
		testPropertyValue = CategoriesFactory.eINSTANCE.createCategoryAssignment();
	}
	
	@Test
	public void testValueHandling() {
		assertFalse("No value should be set at this point", testBean.isSet());
		
		testBean.setValue(testPropertyValue);
		assertTrue("Now a value should be set", testBean.isSet());
		assertEquals("Value should be the one as set before", testPropertyValue, testBean.getTypeInstance().getReference());
		assertEquals("Value should be the one as set before", testPropertyValue, testBean.getValue());
		
		testBean.unset();
		assertFalse("No value should have been unset", testBean.isSet());
	}
	
	@Test
	public void testConstructor() {
		EReferencePropertyInstance eRPI = PropertyinstancesFactory.eINSTANCE.createEReferencePropertyInstance();
		BeanPropertyEReference<CategoryAssignment> beanProperty = new BeanPropertyEReference<>();
		beanProperty.setTypeInstance(eRPI);
		BeanPropertyEReference<CategoryAssignment> beanProperty2 = new BeanPropertyEReference<>(eRPI);
		assertEquals("Beans are identical", beanProperty2, beanProperty);
	}

}
