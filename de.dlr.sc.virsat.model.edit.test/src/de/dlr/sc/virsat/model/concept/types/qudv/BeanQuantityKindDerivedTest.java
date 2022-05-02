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

import java.util.Arrays;
import java.util.List;

import org.eclipse.emf.common.command.Command;
import org.junit.Before;
import org.junit.Test;

import de.dlr.sc.virsat.model.dvlm.qudv.DerivedQuantityKind;
import de.dlr.sc.virsat.model.dvlm.qudv.QuantityKindFactor;
import de.dlr.sc.virsat.model.dvlm.qudv.QudvFactory;

public class BeanQuantityKindDerivedTest extends ABeanQuantityKindTest {

	private DerivedQuantityKind derivedQuantityKind;
	private BeanQuantityKindDerived derivedBeanQuantityKind;
	private QuantityKindFactor factor;
	private BeanFactorQuantityKind beanFactor;

	@Before
	public void setUp() throws Exception {
		super.setUp();
		
		derivedQuantityKind = QudvFactory.eINSTANCE.createDerivedQuantityKind();
		derivedBeanQuantityKind = new BeanQuantityKindDerived();
		derivedBeanQuantityKind.setQuantityKind(derivedQuantityKind);
		
		aQuantityKind = derivedQuantityKind;
		aBeanQuantityKind = derivedBeanQuantityKind;
		
		factor = QudvFactory.eINSTANCE.createQuantityKindFactor();
		beanFactor = new BeanFactorQuantityKind(factor);
	}
	
	@Test
	public void testGetFactors() {
		assertTrue("Initial no factors", derivedBeanQuantityKind.getFactorBeans().isEmpty());
		derivedQuantityKind.getFactor().add(factor);
		
		List<BeanFactorQuantityKind> beanFactors = derivedBeanQuantityKind.getFactorBeans();
		assertEquals("Right amount of elements", 1, beanFactors.size());
		assertEquals("Right element found", factor, beanFactors.get(0).getFactor());
	}
	
	@Test
	public void testSetFactors() {
		assertTrue("Initial no factors", derivedBeanQuantityKind.getFactorBeans().isEmpty());
		derivedBeanQuantityKind.setFactorBeans(Arrays.asList(beanFactor));
		
		List<QuantityKindFactor> factors = derivedQuantityKind.getFactor();
		assertEquals("Right amount of elements", 1, factors.size());
		assertEquals("Right element found", factor, factors.get(0));
	}
	
	@Test
	public void testAddFactor() {
		assertTrue("Initial no factors", derivedBeanQuantityKind.getFactorBeans().isEmpty());
		derivedBeanQuantityKind.addFactor(beanFactor);
		
		List<QuantityKindFactor> factors = derivedQuantityKind.getFactor();
		assertEquals("Right amount of elements", 1, factors.size());
		assertEquals("Right element found", factor, factors.get(0));
	}
	
	@Test
	public void testAddFactorEditingDomain() {
		assertTrue("Initial no factors", derivedBeanQuantityKind.getFactorBeans().isEmpty());
		Command cmd = derivedBeanQuantityKind.addFactor(ed, beanFactor);
		cmd.execute();
		
		List<QuantityKindFactor> factors = derivedQuantityKind.getFactor();
		assertEquals("Right amount of elements", 1, factors.size());
		assertEquals("Right element found", factor, factors.get(0));
	}
	
	@Test
	public void testRemoveFactor() {
		derivedQuantityKind.getFactor().add(factor);
		assertEquals("Initial one element", 1, derivedQuantityKind.getFactor().size());
		derivedBeanQuantityKind.removeFactor(beanFactor);
		
		assertTrue("Factor removed", derivedBeanQuantityKind.getFactorBeans().isEmpty());
	}
	
	@Test
	public void testRemoveFactorEditingDomain() {
		derivedQuantityKind.getFactor().add(factor);
		assertEquals("Initial one element", 1, derivedQuantityKind.getFactor().size());
		Command cmd = derivedBeanQuantityKind.removeFactor(ed, beanFactor);
		cmd.execute();
		
		assertTrue("Factor removed", derivedBeanQuantityKind.getFactorBeans().isEmpty());
	}
}
