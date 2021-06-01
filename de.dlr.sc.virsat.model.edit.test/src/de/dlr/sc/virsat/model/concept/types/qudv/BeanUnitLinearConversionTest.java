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
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.eclipse.emf.common.command.Command;
import org.junit.Before;
import org.junit.Test;

import de.dlr.sc.virsat.model.dvlm.qudv.LinearConversionUnit;
import de.dlr.sc.virsat.model.dvlm.qudv.QudvFactory;

public class BeanUnitLinearConversionTest extends ABeanUnitTest {

	private LinearConversionUnit linearConversionUnit;
	private BeanUnitLinearConversion linearConversionBeanUnit;
	private static final double TEST_FACTOR = 20.56;
	private static final double EPSILON = 0.001;

	@Before
	public void setUp() throws Exception {
		super.setUp();
		
		linearConversionUnit = QudvFactory.eINSTANCE.createLinearConversionUnit();
		linearConversionBeanUnit = new BeanUnitLinearConversion();
		linearConversionBeanUnit.setUnit(linearConversionUnit);
		
		aUnit = linearConversionUnit;
		aBeanUnit = linearConversionBeanUnit;
	}
	
	@Test
	public void getFactor() {
		linearConversionUnit.setFactor(TEST_FACTOR);
		assertEquals("Got right value", TEST_FACTOR, linearConversionBeanUnit.getFactor(), EPSILON);
	}
	
	@Test
	public void testSetFactor() {
		assertEquals("Initial value", 0.0, linearConversionUnit.getFactor(), EPSILON);
		linearConversionBeanUnit.setFactor(TEST_FACTOR);
		
		assertEquals("Value correctly set", TEST_FACTOR, linearConversionUnit.getFactor(), EPSILON);
	}
	
	@Test
	public void testSetFactorEditingDomain() {
		assertEquals("Initial value", 0.0, linearConversionUnit.getFactor(), EPSILON);
		Command cmd = linearConversionBeanUnit.setFactor(ed, TEST_FACTOR);
		cmd.execute();
		
		assertEquals("Value correctly set", TEST_FACTOR, linearConversionUnit.getFactor(), EPSILON);
	}
	
	@Test
	public void getIsInvertable() {
		linearConversionUnit.setIsInvertible(true);
		assertEquals("Got right value", linearConversionUnit.isIsInvertible(), linearConversionBeanUnit.getIsInvertible());
	}
	
	@Test
	public void testSetIsInvertable() {
		assertFalse("Is false initial", linearConversionUnit.isIsInvertible());
		linearConversionBeanUnit.setIsInvertible(true);
		
		assertTrue("Value correctly set", linearConversionUnit.isIsInvertible());
	}
	
	@Test
	public void testSetIsInvertableEditingDomain() {
		assertFalse("Is false initial", linearConversionUnit.isIsInvertible());
		Command cmd = linearConversionBeanUnit.setIsInvertible(ed, true);
		cmd.execute();
		
		assertTrue("Value correctly set", linearConversionUnit.isIsInvertible());
	}
}
