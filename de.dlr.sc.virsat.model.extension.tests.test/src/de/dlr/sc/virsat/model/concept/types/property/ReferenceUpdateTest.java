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

import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.extension.tests.model.AConceptTestCase;
import de.dlr.sc.virsat.model.extension.tests.model.TestCategoryAllProperty;
import de.dlr.sc.virsat.model.extension.tests.model.TestCategoryBase;
import de.dlr.sc.virsat.model.extension.tests.model.TestCategoryReference;
import de.dlr.sc.virsat.model.extension.tests.model.TestCategoryReferenceArray;

public class ReferenceUpdateTest extends AConceptTestCase {

	Concept concept;
	
	@Before
	public void setup() {
		concept = loadConceptFromPlugin();
	}

	@Test
	public void testRereferencing() {
		final int FIRST_VALUE = 1;
		final int SECOND_VALUE = 2;
		
		TestCategoryReference root = new TestCategoryReference(concept);

		TestCategoryAllProperty firstReference = new TestCategoryAllProperty(concept);
		firstReference.setTestInt(FIRST_VALUE);
		
		TestCategoryAllProperty secondReference = new TestCategoryAllProperty(concept);
		secondReference.setTestInt(SECOND_VALUE);
		
		root.setTestRefCategory(firstReference);
		assertEquals("First value is correctly set", FIRST_VALUE, root.getTestRefCategory().getTestInt());
		
		root.setTestRefCategory(secondReference);
		assertEquals("Value is changed after rereferencing", SECOND_VALUE, root.getTestRefCategory().getTestInt());
	}
	
	@Test
	public void testRereferencingArray() {
		TestCategoryBase root = new TestCategoryBase(concept);

		TestCategoryBase empty = new TestCategoryBase(concept);
		assertTrue(empty.getTestArray().isEmpty());
		
		TestCategoryBase nonEmpty = new TestCategoryBase(concept);
		nonEmpty.getTestArray().add(new TestCategoryBase(concept));
		assertFalse(nonEmpty.getTestArray().isEmpty());
		
		root.setTestReference(empty);
		assertTrue("Reference points to an object with empty array", root.getTestReference().getTestArray().isEmpty());
		
		root.setTestReference(nonEmpty);
		assertFalse("Referenced points to an object with non-empty array", root.getTestReference().getTestArray().isEmpty());
	}
	
	@Test
	public void testReferencedPropertyInReferenceArrayUpdatesAfterChange() {
		TestCategoryReferenceArray root = new TestCategoryReferenceArray(concept);
		
		TestCategoryAllProperty property = new TestCategoryAllProperty(concept);
		assertFalse(property.getTestBool());
		
		TestCategoryAllProperty propertyChanged = new TestCategoryAllProperty(concept);
		propertyChanged.setTestBool(true);
		assertTrue(propertyChanged.getTestBool());
		
		root.getTestCategoryReferenceArrayDynamic().add(property);
		assertEquals("Array contains one element", 1, root.getTestCategoryReferenceArrayDynamic().size());
		assertFalse("Reference points to an object with false boolean", 
			root.getTestCategoryReferenceArrayDynamic()
				.get(0)
				.getTestBool());
		
		root.getTestCategoryReferenceArrayDynamic().set(0, propertyChanged);
		assertTrue("Reference points to an object with true boolean", 
				root.getTestCategoryReferenceArrayDynamic()
					.get(0)
					.getTestBool());
	}
}
