/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.funcelectrical.model;

// *****************************************************************
// * Import Statements
// *****************************************************************


import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.dvlm.categories.CategoriesFactory;
import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;
import de.dlr.sc.virsat.model.extension.funcelectrical.model.InterfaceType;
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
 * InterfaceTypes such as MIL or CAN to type communication of InterfaceEnds
 * 
 */	
public abstract class AInterfaceTypeTest {
	
	protected Concept concept;
	
	@Before
	public void setUp() throws Exception {
		String conceptXmiPluginPath = "de.dlr.sc.virsat.model.extension.funcelectrical/concept/concept.xmi";
		concept = de.dlr.sc.virsat.concept.unittest.util.ConceptXmiLoader.loadConceptFromPlugin(conceptXmiPluginPath);
	}

	@After
	public void tearDown() throws Exception {
	}

	
	// *****************************************************************
	// * Constructor Test Cases
	// *****************************************************************
	
	@Test
	public void testInterfaceType() {
		InterfaceType testInterfaceType = new InterfaceType();
	
		assertNull("There is no internal DVLM object", testInterfaceType.getTypeInstance());
	}
	
	@Test
	public void testInterfaceTypeConcept() {
		InterfaceType testInterfaceType = new InterfaceType(concept);
		
		assertNotNull("There is an internal DVLM object", testInterfaceType.getATypeInstance());
	}
	
	@Test
	public void testInterfaceTypeCategoryAssignment() {
		CategoryAssignment testCa = CategoriesFactory.eINSTANCE.createCategoryAssignment();
		InterfaceType testInterfaceType = new InterfaceType(testCa);
		
		assertEquals("DVLM object has been set as specified", testCa, testInterfaceType.getTypeInstance());
	}
}
