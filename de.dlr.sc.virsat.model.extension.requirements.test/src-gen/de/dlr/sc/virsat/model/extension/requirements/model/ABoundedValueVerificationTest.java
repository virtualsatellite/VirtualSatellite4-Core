/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.requirements.model;

// *****************************************************************
// * Import Statements
// *****************************************************************


import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.dvlm.categories.CategoriesFactory;
import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;
import java.lang.Exception;


// *****************************************************************
// * Class Declaration
// *****************************************************************

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Auto Generated Abstract Generator Gap Class
 * 
 * Don't Manually modify this class
 * 
 * 
 * 
 */	
public abstract class ABoundedValueVerificationTest {
	
	protected Concept concept;
	
	@Before
	public void setUp() throws Exception {
		String conceptXmiPluginPath = "de.dlr.sc.virsat.model.extension.requirements/concept/concept.xmi";
		concept = de.dlr.sc.virsat.concept.unittest.util.ConceptXmiLoader.loadConceptFromPlugin(conceptXmiPluginPath);
	}

	@After
	public void tearDown() throws Exception {
	}

	
	// *****************************************************************
	// * Constructor Test Cases
	// *****************************************************************
	
	@Test
	public void testBoundedValueVerification() {
		BoundedValueVerification testBoundedValueVerification = new BoundedValueVerification();
	
		assertNull("There is no internal DVLM object", testBoundedValueVerification.getTypeInstance());
	}
	
	@Test
	public void testBoundedValueVerificationConcept() {
		BoundedValueVerification testBoundedValueVerification = new BoundedValueVerification(concept);
		
		assertNotNull("There is an internal DVLM object", testBoundedValueVerification.getATypeInstance());
	}
	
	@Test
	public void testBoundedValueVerificationCategoryAssignment() {
		CategoryAssignment testCa = CategoriesFactory.eINSTANCE.createCategoryAssignment();
		BoundedValueVerification testBoundedValueVerification = new BoundedValueVerification(testCa);
		
		assertEquals("DVLM object has been set as specified", testCa, testBoundedValueVerification.getTypeInstance());
	}
}
