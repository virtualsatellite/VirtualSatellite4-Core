/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.ext.core.model;

// *****************************************************************
// * Class Declaration
// *****************************************************************

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import de.dlr.sc.virsat.model.dvlm.categories.CategoriesFactory;
import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;

// *****************************************************************
// * Import Statements
// *****************************************************************


import de.dlr.sc.virsat.model.dvlm.concepts.Concept;

/**
 * Auto Generated Abstract Generator Gap Class
 * 
 * Don't Manually modify this class
 * 
 * Implicit super category of all categories
 * 
 */	
public class GenericCategoryTest {
	
	protected Concept concept;
	
	@Before
	public void setUp() throws Exception {
		String conceptXmiPluginPath = "de.dlr.sc.virsat.model.ext.core/concept/concept.xmi";
		concept = de.dlr.sc.virsat.concept.unittest.util.ConceptXmiLoader.loadConceptFromPlugin(conceptXmiPluginPath);
	}

	@After
	public void tearDown() throws Exception {
	}

	
	// *****************************************************************
	// * Constructor Test Cases
	// *****************************************************************
	
	@Test
	public void testGenericCategory() {
		GenericCategory testGenericCategory = new GenericCategoryBeanMockupImpl();
	
		assertNull("There is no internal DVLM object", testGenericCategory.getTypeInstance());
	}
	
	@Test
	public void testGenericCategoryConcept() {
		GenericCategory testGenericCategory = new GenericCategoryBeanMockupImpl(concept);
		
		assertNull("Should not be able to instance it", testGenericCategory.getATypeInstance());
	}
	
	@Test
	public void testGenericCategoryCategoryAssignment() {
		CategoryAssignment testCa = CategoriesFactory.eINSTANCE.createCategoryAssignment();
		GenericCategory testGenericCategory = new GenericCategoryBeanMockupImpl(testCa);
		
		assertEquals("DVLM object has been set as specified", testCa, testGenericCategory.getTypeInstance());
	}
}
