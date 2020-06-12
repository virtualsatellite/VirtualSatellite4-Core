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
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.eclipse.emf.common.command.Command;
import org.junit.Before;
import org.junit.Test;

import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ComposedPropertyInstance;
import de.dlr.sc.virsat.model.dvlm.categories.util.CategoryAssignmentHelper;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.extension.tests.model.AConceptTestCase;
import de.dlr.sc.virsat.model.extension.tests.model.TestCategoryAllProperty;
import de.dlr.sc.virsat.model.extension.tests.model.TestCategoryComposition;

public class BeanPropertyComposedTest extends AConceptTestCase {

	private TestCategoryAllProperty testCategoryAllProperty;
	private TestCategoryComposition testCategoryComposition;
	private BeanPropertyComposed<TestCategoryAllProperty> beanPropertyComposed;
	private ComposedPropertyInstance cpiToCategory;

	@Before
	public void setUp() throws Exception {
		prepareEditingDomain();

		Concept concept = loadConceptFromPlugin();

		testCategoryComposition = new TestCategoryComposition(concept);
		
		CategoryAssignment testCategoryCompositionCa = testCategoryComposition.getTypeInstance();
		CategoryAssignmentHelper caHelper = new CategoryAssignmentHelper(testCategoryCompositionCa);

		// Create a bean for the cpi with a ca
		cpiToCategory = (ComposedPropertyInstance) caHelper.getPropertyInstance(TestCategoryComposition.PROPERTY_TESTSUBCATEGORY);
		beanPropertyComposed = new BeanPropertyComposed<>(cpiToCategory);
		testCategoryAllProperty = testCategoryComposition.getTestSubCategory();
	}

	@Test
	public void testSetValue() {
		assertNotNull("Initial a composed ca is set", testCategoryComposition.getTestSubCategory());
		
		// This should have no effect
		beanPropertyComposed.setValue(null);
		
		assertEquals("The composed ca didn't change", testCategoryAllProperty, testCategoryComposition.getTestSubCategory());
	}

	@Test(expected = UnsupportedOperationException .class)
	public void testSetValueEditingDomain() {
		Command setCommand = beanPropertyComposed.setValue(editingDomain, null);
		assertFalse("Can't execute the command", setCommand.canExecute());
		
		// Should always throw UnsupportedOperationException
		setCommand.execute();
	}

	@Test
	public void testGetValue() {
		assertEquals("Got the expected value", testCategoryAllProperty, beanPropertyComposed.getValue());
	}

	@Test
	public void testIsSet() {
		assertTrue("A ca is set", beanPropertyComposed.isSet());
		
		cpiToCategory.setTypeInstance(null);
		
		assertFalse("No ca is set", beanPropertyComposed.isSet());
	}

	@Test
	public void testUnset() {
		assertTrue("A ca is set", beanPropertyComposed.isSet());
		
		// This should have no effect
		beanPropertyComposed.unset();
		
		assertTrue("A ca is still set", beanPropertyComposed.isSet());
	}

}
