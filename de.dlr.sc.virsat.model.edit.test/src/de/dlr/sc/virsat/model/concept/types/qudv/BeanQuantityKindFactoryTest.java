/*******************************************************************************
 * Copyright (c) 2021 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.concept.types.qudv;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import de.dlr.sc.virsat.model.concept.types.factory.BeanQuantityKindFactory;
import de.dlr.sc.virsat.model.dvlm.qudv.AQuantityKind;
import de.dlr.sc.virsat.model.dvlm.qudv.DerivedQuantityKind;
import de.dlr.sc.virsat.model.dvlm.qudv.QudvFactory;
import de.dlr.sc.virsat.model.dvlm.qudv.SimpleQuantityKind;

public class BeanQuantityKindFactoryTest {

	@Test
	public void testBeanQuantityKindSimpleFromFactory() {
		SimpleQuantityKind simpleQuantityKind = QudvFactory.eINSTANCE.createSimpleQuantityKind();
		
		BeanQuantityKindFactory beanQuantityKindFactory = new BeanQuantityKindFactory();
		IBeanQuantityKind<? extends AQuantityKind> beanQuantityKindSimple = beanQuantityKindFactory.getInstanceFor(simpleQuantityKind);
		
		assertTrue(beanQuantityKindSimple instanceof BeanQuantityKindSimple);
		assertEquals(simpleQuantityKind, beanQuantityKindSimple.getQuantityKind());
	}
	
	@Test
	public void testBeanQuantityKindDerivedFromFactory() {
		DerivedQuantityKind derivedQuantityKind = QudvFactory.eINSTANCE.createDerivedQuantityKind();
		
		BeanQuantityKindFactory beanQuantityKindFactory = new BeanQuantityKindFactory();
		IBeanQuantityKind<? extends AQuantityKind> beanQuantityKindDerived = beanQuantityKindFactory.getInstanceFor(derivedQuantityKind);
		
		assertTrue(beanQuantityKindDerived instanceof BeanQuantityKindDerived);
		assertEquals(derivedQuantityKind, beanQuantityKindDerived.getQuantityKind());
	}
}
