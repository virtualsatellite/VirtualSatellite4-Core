/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.budget.cost.model;

// *****************************************************************
// * Import Statements
// *****************************************************************
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralFactory;
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
 * A Container to summarize all CostTypes
 * 
 */	
public abstract class ACostTypesCollectionTest {

	protected Concept concept;
	
	@Before
	public void setUp() throws Exception {
		String conceptXmiPluginPath = "de.dlr.sc.virsat.model.extension.budget.cost/concept/concept.xmi";
		concept = de.dlr.sc.virsat.concept.unittest.util.ConceptXmiLoader.loadConceptFromPlugin(conceptXmiPluginPath);
	}

	@After
	public void tearDown() throws Exception {
	}
	
		
	// *****************************************************************
	// * Constructor Test Cases
	// *****************************************************************
	
	@Test
	public void testCostTypesCollection() {
		CostTypesCollection testCostTypesCollection = new CostTypesCollection();
	
		assertNull("There is no internal DVLM object", testCostTypesCollection.getStructuralElementInstance());
	}
	
	@Test
	public void testCostTypesCollectionConcept() {
		CostTypesCollection testCostTypesCollection = new CostTypesCollection(concept);
		
		assertNotNull("There is an internal DVLM object", testCostTypesCollection.getStructuralElementInstance());
	}
	
	@Test
	public void testCostTypesCollectionStructuralElementInstance() {
		StructuralElementInstance testSei = StructuralFactory.eINSTANCE.createStructuralElementInstance();
		CostTypesCollection testCostTypesCollection = new CostTypesCollection(testSei);
		
		assertEquals("DVLM object has been set as specified", testSei, testCostTypesCollection.getStructuralElementInstance());
	}
	
}
