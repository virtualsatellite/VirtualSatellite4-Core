/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.dvlm.util;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import de.dlr.sc.virsat.model.dvlm.structural.StructuralElement;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralFactory;
import de.dlr.sc.virsat.model.dvlm.structural.util.StructuralInstantiator;

/**
 * This Test Class checks the applicable for paradigm with its different ways of dealing
 * with which object can be added to which other one. such as which category can be added to which
 * Structural Element etc.
 * @author fisc_ph
 *
 */
public class DVLMCanInheritFromCheckTest {

	private StructuralElement seBase1;
	private StructuralElement seBase2;

	private StructuralElement seInherits1;
	
	@Before
	public void setUp() throws Exception {
		seBase1 = StructuralFactory.eINSTANCE.createStructuralElement();
		seBase2 = StructuralFactory.eINSTANCE.createStructuralElement();

		seInherits1 = StructuralFactory.eINSTANCE.createStructuralElement();
		
		// Only the first one is based on a StructuralElement the second instance can accept all inheritances
		seInherits1.getCanInheritFrom().add(seBase1);
	}

	@Test
	public void testApplicableForWithCollection() {
		StructuralElement seBase3 = StructuralFactory.eINSTANCE.createStructuralElement();

		seInherits1.getCanInheritFrom().add(seBase2);
		
		StructuralElementInstance seiBase1 = new StructuralInstantiator().generateInstance(seBase1, "Base1");
		StructuralElementInstance seiBase2 = new StructuralInstantiator().generateInstance(seBase2, "Base2");
		StructuralElementInstance seiBase3 = new StructuralInstantiator().generateInstance(seBase3, "Base3");
		StructuralElementInstance seiInherits1 = new StructuralInstantiator().generateInstance(seInherits1, "Inherits1");

		ADVLMExtendedModelCapabilityCheck applicableForCheck = new DVLMCanInheritFromCheck(seiInherits1, true);
		
		List<StructuralElementInstance> listWithFail = new ArrayList<>();
		listWithFail.add(seiBase1);
		listWithFail.add(seiBase2);
		
		assertTrue("Object can be assigned", applicableForCheck.isValidObjectCollection(listWithFail));

		listWithFail.add(seiBase3);
		assertFalse("Object can not be assigned", applicableForCheck.isValidObjectCollection(listWithFail));
	}

	@Test
	public void testApplicableForWithStructralElements() {
		StructuralElementInstance seiBase1 = new StructuralInstantiator().generateInstance(seBase1, "Base1");
		StructuralElementInstance seiBase2 = new StructuralInstantiator().generateInstance(seBase2, "Base2");
		StructuralElementInstance seiInherits1 = new StructuralInstantiator().generateInstance(seInherits1, "Inherits1");

		ADVLMExtendedModelCapabilityCheck applicableForCheck = new DVLMCanInheritFromCheck(seiInherits1, true);
		
		assertTrue("Inherits1 can inherit from Base1", applicableForCheck.isValidObject(seiBase1));
		assertFalse("Inherits1 can not inherit from Base2", applicableForCheck.isValidObject(seiBase2));
	}
}
