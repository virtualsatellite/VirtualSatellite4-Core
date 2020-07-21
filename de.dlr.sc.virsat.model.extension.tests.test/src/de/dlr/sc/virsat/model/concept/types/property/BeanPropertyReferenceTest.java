/*******************************************************************************
 * Copyright (c) 2020 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
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
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.eclipse.emf.common.command.Command;
import org.junit.Before;
import org.junit.Test;

import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ReferencePropertyInstance;
import de.dlr.sc.virsat.model.dvlm.categories.util.CategoryAssignmentHelper;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.extension.tests.model.AConceptTestCase;
import de.dlr.sc.virsat.model.extension.tests.model.TestCategoryAllProperty;
import de.dlr.sc.virsat.model.extension.tests.model.TestCategoryReference;

public class BeanPropertyReferenceTest extends AConceptTestCase {

	private TestCategoryAllProperty testCategoryAllProperty;
	private TestCategoryReference testCategoryReference;
	
	private ReferencePropertyInstance rpiToCategory;
	private BeanPropertyReference<TestCategoryAllProperty> beanTestRefCategory;
	
	private ReferencePropertyInstance rpiToProperty;
	BeanPropertyReference<BeanPropertyString> beanTestRefProperty;
	BeanPropertyString beanTestString;

	@Before
	public void setup() {
		prepareEditingDomain();

		Concept concept = loadConceptFromPlugin();
	
		testCategoryAllProperty = new TestCategoryAllProperty(concept);
		testCategoryReference = new TestCategoryReference(concept);
		
		CategoryAssignment testCategoryRefernceCa = testCategoryReference.getTypeInstance();
		CategoryAssignmentHelper caHelper = new CategoryAssignmentHelper(testCategoryRefernceCa);
		
		// Create a bean for the rpi to a ca
		rpiToCategory = (ReferencePropertyInstance) caHelper.getPropertyInstance(TestCategoryReference.PROPERTY_TESTREFCATEGORY);
		beanTestRefCategory = new BeanPropertyReference<>(rpiToCategory);
		
		// Create a bean for the rpi to a String property
		rpiToProperty = (ReferencePropertyInstance) caHelper.getPropertyInstance(TestCategoryReference.PROPERTY_TESTREFPROPERTY);
		beanTestRefProperty = new BeanPropertyReference<>(rpiToProperty);
		
		beanTestString = testCategoryAllProperty.getTestStringBean();
	}
	
	@Test
	public void testSetValueToCategoryAssignment() {
		assertNull("No reference is set", testCategoryReference.getTestRefCategory());
		
		beanTestRefCategory.setValue(testCategoryAllProperty);
		
		assertEquals("Correct reference has been set", rpiToCategory.getReference(), testCategoryAllProperty.getATypeInstance());
		assertEquals("Correct category assignment has been set", testCategoryAllProperty, testCategoryReference.getTestRefCategory());
		
		beanTestRefCategory.setValue(null);
		
		assertNull("No reference is set", testCategoryReference.getTestRefCategory());
	}

	@Test
	public void testSetValueWithEditingDomainToCategoryAssignment() {
		assertNull("No reference is set", testCategoryReference.getTestRefCategory());
		
		Command setCommand = beanTestRefCategory.setValue(editingDomain, testCategoryAllProperty);
		editingDomain.getCommandStack().execute(setCommand);
		
		assertEquals("Correct reference has been set", rpiToCategory.getReference(), testCategoryAllProperty.getATypeInstance());
		assertEquals("Correct category assignment has been set", testCategoryAllProperty, testCategoryReference.getTestRefCategory());
		
		Command setNullCommand = beanTestRefCategory.setValue(editingDomain, null);
		editingDomain.getCommandStack().execute(setNullCommand);
		
		assertNull("No reference is set", testCategoryReference.getTestRefCategory());
	}
	
	@Test
	public void testSetValueToAPropertyInstance() {
		assertNull("No reference is set", testCategoryReference.getTestRefProperty());
		
		beanTestRefProperty.setValue(beanTestString);
		
		assertEquals("Correct reference has been set", rpiToProperty.getReference(), beanTestString.getATypeInstance());
		assertEquals("Correct property has been set", beanTestString, testCategoryReference.getTestRefProperty());
	}

	@Test
	public void testSetValueWithEditingDomainToAPropertyInstance() {
		assertNull("No reference is set", testCategoryReference.getTestRefProperty());
		
		Command setCommand = beanTestRefProperty.setValue(editingDomain, beanTestString);
		editingDomain.getCommandStack().execute(setCommand);
		
		assertEquals("Correct reference has been set", rpiToProperty.getReference(), beanTestString.getATypeInstance());
		assertEquals("Correct property has been set", beanTestString, testCategoryReference.getTestRefProperty());
	}

	@Test
	public void testGetValueCategoryAssignment() {
		assertNull("No value set initially", beanTestRefCategory.getValue());
		
		beanTestRefCategory.setValue(testCategoryAllProperty);

		assertEquals("Correct reference to ca returned", testCategoryAllProperty, beanTestRefCategory.getValue());
	}
	
	@Test
	public void testGetValueAPropertyInstance() {
		assertNull("No value set initially", beanTestRefProperty.getValue());

		beanTestRefProperty.setValue(beanTestString);
		
		assertEquals("Correct reference to property returned", beanTestString, beanTestRefProperty.getValue());
	}

	@Test
	public void testIsSet() {
		assertFalse("No value set initially", beanTestRefCategory.isSet());
		
		beanTestRefCategory.setValue(testCategoryAllProperty);
		
		assertTrue("Now a value is set", beanTestRefCategory.isSet());
	}

	@Test
	public void testUnset() {
		beanTestRefCategory.setValue(testCategoryAllProperty);
		assertTrue("A value is set", beanTestRefCategory.isSet());
		
		beanTestRefCategory.unset();
		
		assertFalse("No value is set", beanTestRefCategory.isSet());
	}

}
