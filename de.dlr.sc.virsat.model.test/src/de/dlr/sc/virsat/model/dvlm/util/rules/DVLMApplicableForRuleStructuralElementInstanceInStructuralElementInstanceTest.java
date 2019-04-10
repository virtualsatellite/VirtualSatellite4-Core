/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.dvlm.util.rules;


import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import de.dlr.sc.virsat.model.dvlm.structural.StructuralElement;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralFactory;
import de.dlr.sc.virsat.model.dvlm.structural.util.StructuralInstantiator;

/**
 * This class tests the nesting of StructuralElementInstances in StructuralElementInstances
 * @author fisc_ph
 *
 */
public class DVLMApplicableForRuleStructuralElementInstanceInStructuralElementInstanceTest {

	private StructuralElement seAccept;
	private StructuralElement seFail;
	
	private StructuralElementInstance seiRoot;
	
	@Before
	public void setUp() throws Exception {
		seAccept = StructuralFactory.eINSTANCE.createStructuralElement();
		seFail = StructuralFactory.eINSTANCE.createStructuralElement();
		
		seAccept.getApplicableFor().add(seAccept);
		seFail.getApplicableFor().add(seFail);
		
		seiRoot = new StructuralInstantiator().generateInstance(seAccept, "Se_Root");
	}
	
	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testCanExecute() {
		StructuralElement seForAccept = StructuralFactory.eINSTANCE.createStructuralElement();
		StructuralElement seForFail = StructuralFactory.eINSTANCE.createStructuralElement();
		StructuralElement seForAll = StructuralFactory.eINSTANCE.createStructuralElement();
		StructuralElement seForNone = StructuralFactory.eINSTANCE.createStructuralElement();
		
		seForAccept.getApplicableFor().add(seAccept);
		seForFail.getApplicableFor().add(seFail);
		seForAll.setIsApplicableForAll(true);
		
		StructuralElementInstance seiForAccept = new StructuralInstantiator().generateInstance(seForAccept, "RelAccept");
		StructuralElementInstance seiForFail = new StructuralInstantiator().generateInstance(seForFail, "relFail");
		StructuralElementInstance seiForAll = new StructuralInstantiator().generateInstance(seForAll, "RelAcceptAll");
		StructuralElementInstance seiForNone = new StructuralInstantiator().generateInstance(seForNone, "RelAcceptNone");
		
		DVLMApplicableForRuleStructuralElementInstanceInStructuralElementInstance rule = new DVLMApplicableForRuleStructuralElementInstanceInStructuralElementInstance();
		
		assertTrue("Object can be assigned", rule.isValid(seiRoot, seiForAccept));
		assertTrue("Object can be assigned", rule.isValid(seiRoot, seiForAll));
		assertFalse("Object can not be assigned", rule.isValid(seiRoot, seiForFail));
		assertFalse("Object can not be assigned", rule.isValid(seiRoot, seiForNone));
	}

	@Test
	public void testIsValid() {
		StructuralElementInstance sei = StructuralFactory.eINSTANCE.createStructuralElementInstance(); 
		Object wrongObject = new Object();

		DVLMApplicableForRuleStructuralElementInstanceInStructuralElementInstance rule = new DVLMApplicableForRuleStructuralElementInstanceInStructuralElementInstance();
		
		assertTrue("This rule is well called and works on containments", rule.canExecute(seiRoot, sei, true));
		
		assertFalse("This rule is is not needed for non containments", rule.canExecute(seiRoot, sei, false));
		assertFalse("This rule is is for nested StructuralElementInstances  only ", rule.canExecute(seiRoot, wrongObject, true));
		assertFalse("This rule is is for StructuralElementInstances only ", rule.canExecute(wrongObject, sei, true));
	}
}
