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
public class DVLMCanInheritFromRuleStructuralElementInstanceInStructuralElementInstanceTest {

	private StructuralElement seBase1;
	private StructuralElement seBase2;

	private StructuralElement seInherits1;
	private StructuralElement seInherits2;
	
	private StructuralElementInstance seiRoot;
	
	
	@Before
	public void setUp() throws Exception {
		seBase1 = StructuralFactory.eINSTANCE.createStructuralElement();
		seBase2 = StructuralFactory.eINSTANCE.createStructuralElement();
		
		seInherits1 = StructuralFactory.eINSTANCE.createStructuralElement();
		seInherits2 = StructuralFactory.eINSTANCE.createStructuralElement();
		
		// Only the first one is based on a StructuralElement the second instance can accept all inheritances
		seInherits1.getCanInheritFrom().add(seBase1);
		seInherits2.setIsCanInheritFromAll(true);
		
		seiRoot = new StructuralInstantiator().generateInstance(seBase1, "Se_Root");
	}
	
	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testCanExecute() {
		
		StructuralElementInstance seiBase1 = new StructuralInstantiator().generateInstance(seBase1, "Base1");
		StructuralElementInstance seiBase2 = new StructuralInstantiator().generateInstance(seBase2, "Base2");
		StructuralElementInstance seiInherits1 = new StructuralInstantiator().generateInstance(seInherits1, "Inherits1");
		StructuralElementInstance seiInherits2 = new StructuralInstantiator().generateInstance(seInherits2, "Inherits2");

		DVLMCanInheritFromRuleStructuralElementInstanceToStructuralElementInstance rule = new DVLMCanInheritFromRuleStructuralElementInstanceToStructuralElementInstance();
		
		assertTrue("Inherits1 can inherit from Base1", rule.isValid(seiInherits1, seiBase1));
		assertTrue("Inherits2 can inherit from Base2", rule.isValid(seiInherits2, seiBase2));
		assertTrue("Inherits2 can inherit from Base1", rule.isValid(seiInherits2, seiBase1));
		assertFalse("Inherits1 can not inherit from Base2", rule.isValid(seiInherits1, seiBase2));
	}

	@Test
	public void testIsValid() {
		StructuralElementInstance sei = StructuralFactory.eINSTANCE.createStructuralElementInstance(); 
		Object wrongObject = new Object();

		DVLMCanInheritFromRuleStructuralElementInstanceToStructuralElementInstance rule = new DVLMCanInheritFromRuleStructuralElementInstanceToStructuralElementInstance();

		assertTrue("This rule is well called and works on containments", rule.canExecute(seiRoot, sei, true));
		assertTrue("This rule is is not needed for non containments", rule.canExecute(seiRoot, sei, false));
		assertFalse("This rule is is for StructuralElementInstances only ", rule.canExecute(wrongObject, sei, true));
	}
}
