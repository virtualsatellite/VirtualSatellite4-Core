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

import de.dlr.sc.virsat.model.dvlm.DVLMFactory;
import de.dlr.sc.virsat.model.dvlm.Repository;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElement;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralFactory;
import de.dlr.sc.virsat.model.dvlm.structural.util.StructuralInstantiator;

/**
 * This class tests the rules for nesting a StructuralElementInstance into a repository
 * @author fisc_ph
 *
 */
public class DVLMApplicableForRuleStructuralElementInstanceInRepositoryTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testIsValid() {
		StructuralElement seForAccept = StructuralFactory.eINSTANCE.createStructuralElement();
		StructuralElement seForFail = StructuralFactory.eINSTANCE.createStructuralElement();
		
		seForAccept.setIsRootStructuralElement(true);
		seForFail.setIsRootStructuralElement(false);
		
		StructuralElementInstance seiForAccept = new StructuralInstantiator().generateInstance(seForAccept, "RelAccept");
		StructuralElementInstance seiForFail = new StructuralInstantiator().generateInstance(seForFail, "relFail");
	
		Repository testRepo = DVLMFactory.eINSTANCE.createRepository();

	    DVLMApplicableForRuleStructuralElementInstanceInRepository rule = new DVLMApplicableForRuleStructuralElementInstanceInRepository();
		
		assertTrue("Object can be assigned", rule.isValid(testRepo, seiForAccept));
		assertFalse("Object can not be assigned", rule.isValid(testRepo, seiForFail));
	}

	@Test
	public void testCanExecute() {
		StructuralElementInstance sei = StructuralFactory.eINSTANCE.createStructuralElementInstance(); 
		Repository testRepo = DVLMFactory.eINSTANCE.createRepository();
		Object wrongObject = new Object();

		DVLMApplicableForRuleStructuralElementInstanceInRepository rule = new DVLMApplicableForRuleStructuralElementInstanceInRepository();
		
		assertTrue("This rule is well called and works on non containments", rule.canExecute(testRepo, sei, false));
		
		assertFalse("This repository does not have containments", rule.canExecute(testRepo, sei, true));
		assertFalse("This rule is is for nested StructuralElementInstances  only ", rule.canExecute(testRepo, wrongObject, false));
		assertFalse("This rule is is for Repositories only ", rule.canExecute(wrongObject, sei, false));
	}
}
