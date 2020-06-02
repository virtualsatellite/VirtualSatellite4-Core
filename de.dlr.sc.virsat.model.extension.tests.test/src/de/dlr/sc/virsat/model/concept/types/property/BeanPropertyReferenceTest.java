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
import org.junit.After;
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

	private Concept concept;
	private TestCategoryAllProperty testCategoryAllProperty;
	private TestCategoryReference testCategoryReference;
	private CategoryAssignment testCategoryRefernceCa;
	private CategoryAssignmentHelper caHelper;
	private ReferencePropertyInstance rpi;
	private BeanPropertyReference<TestCategoryAllProperty> beanTestRefCategory;

	@Before
	public void setup() {
		prepareEditingDomain();

		concept = loadConceptFromPlugin();
	
		testCategoryAllProperty = new TestCategoryAllProperty(concept);
		testCategoryReference = new TestCategoryReference(concept);
		
		testCategoryRefernceCa = testCategoryReference.getTypeInstance();
		caHelper = new CategoryAssignmentHelper(testCategoryRefernceCa);
		rpi = (ReferencePropertyInstance) caHelper.getPropertyInstance(TestCategoryReference.PROPERTY_TESTREFCATEGORY);

		beanTestRefCategory = new BeanPropertyReference<>(rpi);
	}
	
	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testSetValueType() {
		assertNull("No reference is set", testCategoryReference.getTestRefCategory());

		beanTestRefCategory.setValue(testCategoryAllProperty);

		// TODO: remove one?
		assertEquals("Correct reference has been set", rpi.getReference(), testCategoryAllProperty.getATypeInstance());
		assertEquals("Correct Category assignment has been set", testCategoryAllProperty, testCategoryReference.getTestRefCategory());
	}

	@Test
	public void testSetValueEditingDomainType() {
		assertNull("No reference is set", testCategoryReference.getTestRefCategory());
		
		Command setCommand = beanTestRefCategory.setValue(editingDomain, testCategoryAllProperty);
		editingDomain.getCommandStack().execute(setCommand);

		// TODO: remove one?
		assertEquals("Correct reference has been set", rpi.getReference(), testCategoryAllProperty.getATypeInstance());
		assertEquals("Correct Category assignment has been set", testCategoryAllProperty, testCategoryReference.getTestRefCategory());
	}

	@Test
	public void testGetValue() {
		assertNull("No value set initially", beanTestRefCategory.getValue());
		
		beanTestRefCategory.setValue(testCategoryAllProperty);

		assertEquals("Correct reference to ca returned", testCategoryAllProperty, beanTestRefCategory.getValue());
		
		ReferencePropertyInstance rpiToProperty = (ReferencePropertyInstance) caHelper.getPropertyInstance(TestCategoryReference.PROPERTY_TESTREFPROPERTY);
		BeanPropertyReference<BeanPropertyString> beanTestRefProperty = new BeanPropertyReference<>(rpiToProperty);
		
		BeanPropertyString beanTestString = testCategoryAllProperty.getTestStringBean();
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
		assertEquals("Correct reference has been set", testCategoryAllProperty.getATypeInstance(), rpi.getReference());
		
		beanTestRefCategory.unset();
		
		assertNull("Reference has been unset", rpi.getReference());
	}

}
