/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.budget.power.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
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
import de.dlr.sc.virsat.model.extension.budget.power.model.PowerEquipment;
import de.dlr.sc.virsat.model.extension.budget.power.model.PowerSummary;

/**
 * Test Cases for the Power Concept Helper
 * @author fisc_ph
 *
 */
public class PowerConceptHelperTest {

	private Concept concept;
	
	@Before
	public void setUp() throws Exception {
		String conceptXmiPluginPath = "de.dlr.sc.virsat.model.extension.budget.power/concept/concept.xmi";
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
		
		PowerConceptHelper pcHelper = new PowerConceptHelper();
		assertTrue("Found a Bean Sei", pcHelper.initBeansForObject(beanSei));
		assertNull("But there is no Power Equipment", pcHelper.powerEquipment);
		assertNull("But there is no Power Summary", pcHelper.powerSummary);
		
		PowerEquipment pwrEqp = new PowerEquipment(concept);
		PowerSummary pwrSum = new PowerSummary(concept);
		
		beanSei.add(pwrSum);
		beanSei.add(pwrEqp);
		
		assertTrue("Found a Bean Sei", pcHelper.initBeansForObject(beanSei));
		assertEquals("Found correct Bean", pwrSum, pcHelper.powerSummary);
		assertEquals("Found correct Bean", pwrEqp, pcHelper.powerEquipment);

		assertFalse("This is not a Bean Sei", pcHelper.initBeansForObject(new Object()));
		assertNull("But there is no Power Equipment", pcHelper.powerEquipment);
		assertNull("But there is no Power Summary", pcHelper.powerSummary);

		assertTrue("Also works with the inner instance", pcHelper.initBeansForObject(beanSei.getStructuralElementInstance()));
		assertEquals("Found correct Bean", pwrSum, pcHelper.powerSummary);
		assertEquals("Found correct Bean", pwrEqp, pcHelper.powerEquipment);
	}

	@Test
	public void testGetPowerParameters() {
		StructuralElement se = StructuralFactory.eINSTANCE.createStructuralElement();
		StructuralElementInstance sei = StructuralFactory.eINSTANCE.createStructuralElementInstance();
		sei.setType(se);
		BeanStructuralElementInstance beanSei = new BeanStructuralElementInstance();
		beanSei.setStructuralElementInstance(sei);
		
		PowerConceptHelper pcHelper = new PowerConceptHelper();
		assertTrue("Found a Bean Sei", pcHelper.initBeansForObject(beanSei));
		assertNull("But there is no Power Equipment", pcHelper.powerEquipment);
		assertNull("But there is no Power Summary", pcHelper.powerSummary);
		
		PowerEquipment pwrEqp = new PowerEquipment(concept);
		PowerSummary pwrSum = new PowerSummary(concept);

		// None
		pcHelper.initBeansForObject(beanSei);
		assertNull("Found correct Bean",  pcHelper.getPowerParameters());
		
		// Only the sum
		beanSei.add(pwrSum);
		pcHelper.initBeansForObject(beanSei);
		assertEquals("Found correct Bean", pwrSum, pcHelper.getPowerParameters());
		
		// Only the equipment
		beanSei.remove(pwrSum);
		beanSei.add(pwrEqp);
		pcHelper.initBeansForObject(beanSei);
		assertEquals("Found correct Bean", pwrEqp, pcHelper.getPowerParameters());

		// Both Categories Sum overrules
		beanSei.add(pwrSum);
		beanSei.add(pwrEqp);
		pcHelper.initBeansForObject(beanSei);
		assertEquals("Found correct Bean", pwrSum, pcHelper.getPowerParameters());
	}
}
