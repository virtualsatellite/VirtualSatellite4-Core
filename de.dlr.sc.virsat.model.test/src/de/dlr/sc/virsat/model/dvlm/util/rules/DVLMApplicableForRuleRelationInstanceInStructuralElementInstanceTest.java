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

import de.dlr.sc.virsat.model.dvlm.structural.GeneralRelation;
import de.dlr.sc.virsat.model.dvlm.structural.RelationInstance;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElement;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralFactory;
import de.dlr.sc.virsat.model.dvlm.structural.util.StructuralInstantiator;

/**
 * This class tests the applicableFor rule for the RelationInstances being nested to StructuralElementInstances
 * @author fisc_ph
 *
 */
public class DVLMApplicableForRuleRelationInstanceInStructuralElementInstanceTest {

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
		GeneralRelation relationForAccept = StructuralFactory.eINSTANCE.createGeneralRelation();
		GeneralRelation relationForFail = StructuralFactory.eINSTANCE.createGeneralRelation();
		GeneralRelation relationForAll = StructuralFactory.eINSTANCE.createGeneralRelation();
		
		relationForAccept.getApplicableFor().add(seAccept);
		relationForFail.getApplicableFor().add(seFail);
		relationForAll.setIsApplicableForAll(true);
		
		RelationInstance riForAccept = new StructuralInstantiator().generateInstance(relationForAccept, "RelAccept");
		RelationInstance riForFail = new StructuralInstantiator().generateInstance(relationForFail, "relFail");
		RelationInstance riForAll = new StructuralInstantiator().generateInstance(relationForAll, "RelAcceptAll");
		
		DVLMApplicableForRuleRelationInstanceInStructuralElementInstance rule = new DVLMApplicableForRuleRelationInstanceInStructuralElementInstance();
		
		assertTrue("Object can be assigned", rule.isValid(seiRoot, riForAccept));
		assertTrue("Object can be assigned", rule.isValid(seiRoot, riForAll));
		assertFalse("Object can not be assigned", rule.isValid(seiRoot, riForFail));
	}

	@Test
	public void testIsValid() {
		RelationInstance ri = StructuralFactory.eINSTANCE.createRelationInstance(); 
		Object wrongObject = new Object();

		DVLMApplicableForRuleRelationInstanceInStructuralElementInstance rule = new DVLMApplicableForRuleRelationInstanceInStructuralElementInstance();
		
		assertTrue("This rule is well called and works on containments", rule.canExecute(seiRoot, ri, true));
		
		assertFalse("This rule is is not needed for non containments", rule.canExecute(seiRoot, ri, false));
		assertFalse("This rule is is for relation instances  only ", rule.canExecute(seiRoot, wrongObject, true));
		assertFalse("This rule is is for StructuralElementInstances only ", rule.canExecute(wrongObject, ri, true));
	}
}
