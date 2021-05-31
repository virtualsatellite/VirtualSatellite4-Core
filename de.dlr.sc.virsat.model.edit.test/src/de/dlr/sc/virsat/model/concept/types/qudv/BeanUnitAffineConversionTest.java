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

import org.eclipse.emf.common.command.Command;
import org.junit.Before;
import org.junit.Test;

import de.dlr.sc.virsat.model.dvlm.qudv.AffineConversionUnit;
import de.dlr.sc.virsat.model.dvlm.qudv.QudvFactory;

public class BeanUnitAffineConversionTest extends ABeanUnitTest {

	private AffineConversionUnit affineConversionUnit;
	private BeanUnitAffineConversion affineConversionBeanUnit;
	private static final double TEST_FACTOR = 20.56;
	private static final double TEST_OFFSET = 42.69;
	private static final double EPSILON = 0.001;
	
	@Before
	public void setUp() throws Exception {
		super.setUp();
		
		affineConversionUnit = QudvFactory.eINSTANCE.createAffineConversionUnit();
		affineConversionBeanUnit = new BeanUnitAffineConversion();
		affineConversionBeanUnit.setUnit(affineConversionUnit);
		
		aUnit = affineConversionUnit;
		aBeanUnit = affineConversionBeanUnit;
	}
	
	@Test
	public void getFactor() {
		affineConversionUnit.setFactor(TEST_FACTOR);
		assertEquals("Got right value", TEST_FACTOR, affineConversionBeanUnit.getFactor(), EPSILON);
	}
	
	@Test
	public void testSetFactor() {
		assertEquals("Initial value", 0.0, affineConversionUnit.getFactor(), EPSILON);
		affineConversionBeanUnit.setFactor(TEST_FACTOR);
		
		assertEquals("Value correctly set", TEST_FACTOR, affineConversionUnit.getFactor(), EPSILON);
	}
	
	@Test
	public void testSetFactorEditingDomain() {
		assertEquals("Initial value", 0.0, affineConversionUnit.getFactor(), EPSILON);
		Command cmd = affineConversionBeanUnit.setFactor(ed, TEST_FACTOR);
		cmd.execute();
		
		assertEquals("Value correctly set", TEST_FACTOR, affineConversionUnit.getFactor(), EPSILON);
	}
	
	@Test
	public void getOffset() {
		affineConversionUnit.setOffset(TEST_OFFSET);
		assertEquals("Got right value", TEST_OFFSET, affineConversionBeanUnit.getOffset(), EPSILON);
	}
	
	@Test
	public void testSetOffset() {
		assertEquals("Initial value", 0.0, affineConversionUnit.getOffset(), EPSILON);
		affineConversionBeanUnit.setOffset(TEST_OFFSET);
		
		assertEquals("Value correctly set", TEST_OFFSET, affineConversionUnit.getOffset(), EPSILON);
	}
	
	@Test
	public void testSetOffsetEditingDomain() {
		assertEquals("Initial value", 0.0, affineConversionUnit.getOffset(), EPSILON);
		Command cmd = affineConversionBeanUnit.setOffset(ed, TEST_OFFSET);
		cmd.execute();
		
		assertEquals("Value correctly set", TEST_OFFSET, affineConversionUnit.getOffset(), EPSILON);
	}
}
