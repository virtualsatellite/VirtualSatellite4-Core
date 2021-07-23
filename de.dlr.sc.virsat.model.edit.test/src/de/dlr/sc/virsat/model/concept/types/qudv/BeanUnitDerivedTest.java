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

import de.dlr.sc.virsat.model.dvlm.qudv.DerivedUnit;
import de.dlr.sc.virsat.model.dvlm.qudv.QudvFactory;
import de.dlr.sc.virsat.model.dvlm.qudv.UnitFactor;

public class BeanUnitDerivedTest extends ABeanUnitTest {

	private DerivedUnit derivedUnit;
	private BeanUnitDerived derivedBeanUnit;
	private UnitFactor factor;
	private BeanFactorUnit beanFactor;

	@Before
	public void setUp() throws Exception {
		super.setUp();
		
		derivedUnit = QudvFactory.eINSTANCE.createDerivedUnit();
		derivedBeanUnit = new BeanUnitDerived();
		derivedBeanUnit.setUnit(derivedUnit);
		
		aUnit = derivedUnit;
		aBeanUnit = derivedBeanUnit;
		
		factor = QudvFactory.eINSTANCE.createUnitFactor();
		beanFactor = new BeanFactorUnit(factor);
	}
	
	@Test
	public void testGetFactors() {
		assertTrue("Initial no factors", derivedBeanUnit.getFactorBeans().isEmpty());
		derivedUnit.getFactor().add(factor);
		
		List<BeanFactorUnit> beanFactors = derivedBeanUnit.getFactorBeans();
		assertEquals("Right amount of elements", 1, beanFactors.size());
		assertEquals("Right element found", factor, beanFactors.get(0).getFactor());
	}
	
	@Test
	public void testSetFactors() {
		assertTrue("Initial no factors", derivedBeanUnit.getFactorBeans().isEmpty());
		derivedBeanUnit.setFactorBeans(Arrays.asList(beanFactor));
		
		List<UnitFactor> factors = derivedUnit.getFactor();
		assertEquals("Right amount of elements", 1, factors.size());
		assertEquals("Right element found", factor, factors.get(0));
	}
	
	@Test
	public void testAddFactor() {
		assertTrue("Initial no factors", derivedBeanUnit.getFactorBeans().isEmpty());
		derivedBeanUnit.addFactor(beanFactor);
		
		List<UnitFactor> factors = derivedUnit.getFactor();
		assertEquals("Right amount of elements", 1, factors.size());
		assertEquals("Right element found", factor, factors.get(0));
	}
	
	@Test
	public void testAddFactorEditingDomain() {
		assertTrue("Initial no factors", derivedBeanUnit.getFactorBeans().isEmpty());
		Command cmd = derivedBeanUnit.addFactor(ed, beanFactor);
		cmd.execute();
		
		List<UnitFactor> factors = derivedUnit.getFactor();
		assertEquals("Right amount of elements", 1, factors.size());
		assertEquals("Right element found", factor, factors.get(0));
	}
	
	@Test
	public void testRemoveFactor() {
		derivedUnit.getFactor().add(factor);
		assertEquals("Initial one element", 1, derivedUnit.getFactor().size());
		derivedBeanUnit.removeFactor(beanFactor);
		
		assertTrue("Factor removed", derivedBeanUnit.getFactorBeans().isEmpty());
	}
	
	@Test
	public void testRemoveFactorEditingDomain() {
		derivedUnit.getFactor().add(factor);
		assertEquals("Initial one element", 1, derivedUnit.getFactor().size());
		Command cmd = derivedBeanUnit.removeFactor(ed, beanFactor);
		cmd.execute();
		
		assertTrue("Factor removed", derivedBeanUnit.getFactorBeans().isEmpty());
	}
}
