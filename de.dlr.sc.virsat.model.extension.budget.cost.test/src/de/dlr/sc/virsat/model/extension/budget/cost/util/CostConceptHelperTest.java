/*******************************************************************************
 * Copyright (c) 2008-2022 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.budget.cost.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import de.dlr.sc.virsat.model.concept.types.structural.BeanStructuralElementInstance;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElement;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralFactory;
import de.dlr.sc.virsat.model.extension.budget.cost.model.CostEquipment;
import de.dlr.sc.virsat.model.extension.budget.cost.model.CostSummary;

/**
 * Provides test cases for the cost concept helper
 *
 */

public class CostConceptHelperTest {

	private Concept concept;
	
	@Before
	public void setUp() throws Exception {
		String conceptXmiPluginPath = "de.dlr.sc.virsat.model.extension.budget.cost/concept/concept.xmi";
		concept = de.dlr.sc.virsat.concept.unittest.util.ConceptXmiLoader.loadConceptFromPlugin(conceptXmiPluginPath);
	}
	
	@Test
	public void testInitBeansForObject() {
		StructuralElement se = StructuralFactory.eINSTANCE.createStructuralElement();
		StructuralElementInstance sei = StructuralFactory.eINSTANCE.createStructuralElementInstance();
		sei.setType(se);
		BeanStructuralElementInstance beanSei = new BeanStructuralElementInstance();
		beanSei.setStructuralElementInstance(sei);
		
		CostSummary costSummary = new CostSummary(concept);
		beanSei.add(costSummary);
		
		CostConceptHelper cch = new CostConceptHelper();
		
		boolean result = cch.initBeansForObject(new Object());
		assertFalse("Not a SEI object", result);
		assertNotNull("There is always a bean", cch.bSei);
		assertNull("But it may be empty", cch.bSei.getStructuralElementInstance());
		
		result = cch.initBeansForObject(sei);
		assertTrue("Found Sei Object", result);
		assertNotNull("bean Detected", cch.bSei);
		assertEquals("The bSei has the correct TI", sei, cch.bSei.getStructuralElementInstance());
		assertNotNull("Found Cost Summary CA", cch.costSummary);
		assertNull("No bean detected", cch.costEquipment);
		
		CostEquipment costEquipment = new CostEquipment(concept);
		beanSei.add(costEquipment);
		
		result = cch.initBeansForObject(sei);
		assertTrue("Found Sei Object", result);
		assertNotNull("bean Detected", cch.bSei);
		assertEquals("The bSei has the correct TI", sei, cch.bSei.getStructuralElementInstance());
		assertNotNull("Found Cost Summary CA", cch.costSummary);
		assertNotNull("Found Cost Equipment CA", cch.costEquipment);
	}

}
