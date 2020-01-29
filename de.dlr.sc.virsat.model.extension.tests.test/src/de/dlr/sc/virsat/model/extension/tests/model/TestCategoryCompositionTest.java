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

import org.junit.Before;
import org.junit.Test;

import de.dlr.sc.virsat.model.dvlm.concepts.Concept;

/**
 * 
 * @author fisc_ph
 *
 */
public class TestCategoryCompositionTest extends AConceptTestCase {

	private TestCategoryComposition tcComposition;
	
	@Before
	public void setup() {
		// Load the concept to create the test object
		Concept concept = loadConceptFromPlugin();
		tcComposition = new TestCategoryComposition(concept);
	}

	@Test
	public void testStringProperty() {
		final String TEST_VALUE_ONE = "ValOne";
		TestCategoryAllProperty tcAllProperty = tcComposition.getTestSubCategory();
		tcAllProperty.setTestString(TEST_VALUE_ONE);
		
		String vpiValue = tcComposition.getTestSubCategory().getTestStringBean().getTypeInstance().getValue();
		String beanValue = tcComposition.getTestSubCategory().getTestString();
		
		assertEquals("SetValue send information correctly into the ValuePropertyInstance", TEST_VALUE_ONE, vpiValue);
		assertEquals("GetValue is receiving correct Value", TEST_VALUE_ONE, beanValue);
	}
}
