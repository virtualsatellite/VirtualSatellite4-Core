/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.budget.mass.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import de.dlr.sc.virsat.model.concept.types.structural.BeanStructuralElementInstance;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElement;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralFactory;
import de.dlr.sc.virsat.model.extension.budget.mass.model.MassEquipment;
import de.dlr.sc.virsat.model.extension.budget.mass.model.MassSummary;

/**
 * test Cases for Mass Concept Helper
 * @author fisc_ph
 *
 */
public class MassConceptHelperTest {

	private Concept concept;
	
	@Before
	public void setUp() throws Exception {
		String conceptXmiPluginPath = "de.dlr.sc.virsat.model.extension.budget.mass/concept/concept.xmi";
		concept = de.dlr.sc.virsat.concept.unittest.util.ConceptXmiLoader.loadConceptFromPlugin(conceptXmiPluginPath);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testInitBeansForObject() {
		StructuralElement se = StructuralFactory.eINSTANCE.createStructuralElement();
		StructuralElementInstance sei = StructuralFactory.eINSTANCE.createStructuralElementInstance();
		sei.setType(se);
		BeanStructuralElementInstance beanSei = new BeanStructuralElementInstance();
		beanSei.setStructuralElementInstance(sei);
		
		MassSummary massSummary = new MassSummary(concept);
		beanSei.add(massSummary);
		
		MassConceptHelper mch = new MassConceptHelper();
		
		boolean result = mch.initBeansForObject(new Object());
		assertFalse("Not a SEI object", result);
		assertNotNull("There is always a bean", mch.getbSei());
		assertNull("But it may be empty", mch.getbSei().getStructuralElementInstance());
		
		result = mch.initBeansForObject(sei);
		assertTrue("Found Sei Object", result);
		assertNotNull("bean Detected", mch.getbSei());
		assertEquals("The bSei has the correct TI", sei, mch.getbSei().getStructuralElementInstance());
		assertNotNull("Found Mass Summary CA", mch.getMassSummary());
		assertNull("No bean detected", mch.getMassEquipment());
		
		MassEquipment massEquipment = new MassEquipment(concept);
		beanSei.add(massEquipment);
		
		result = mch.initBeansForObject(sei);
		assertTrue("Found Sei Object", result);
		assertNotNull("bean Detected", mch.getbSei());
		assertEquals("The bSei has the correct TI", sei, mch.getbSei().getStructuralElementInstance());
		assertNotNull("Found Mass Summary CA", mch.getMassSummary());
		assertNotNull("Found Mass Equipment CA", mch.getMassEquipment());
	}
}
