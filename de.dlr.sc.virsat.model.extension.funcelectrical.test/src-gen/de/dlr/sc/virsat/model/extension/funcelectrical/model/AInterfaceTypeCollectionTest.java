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
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralFactory;
import java.lang.Exception;
import de.dlr.sc.virsat.model.extension.funcelectrical.model.InterfaceTypeCollection;


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
 * A Container to summarize all InterfaceTypes
 * 
 */	
public abstract class AInterfaceTypeCollectionTest {

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
	public void testInterfaceTypeCollection() {
		InterfaceTypeCollection testInterfaceTypeCollection = new InterfaceTypeCollection();
	
		assertNull("There is no internal DVLM object", testInterfaceTypeCollection.getStructuralElementInstance());
	}
	
	@Test
	public void testInterfaceTypeCollectionConcept() {
		InterfaceTypeCollection testInterfaceTypeCollection = new InterfaceTypeCollection(concept);
		
		assertNotNull("There is an internal DVLM object", testInterfaceTypeCollection.getStructuralElementInstance());
	}
	
	@Test
	public void testInterfaceTypeCollectionStructuralElementInstance() {
		StructuralElementInstance testSei = StructuralFactory.eINSTANCE.createStructuralElementInstance();
		InterfaceTypeCollection testInterfaceTypeCollection = new InterfaceTypeCollection(testSei);
		
		assertEquals("DVLM object has been set as specified", testSei, testInterfaceTypeCollection.getStructuralElementInstance());
	}
	
}
