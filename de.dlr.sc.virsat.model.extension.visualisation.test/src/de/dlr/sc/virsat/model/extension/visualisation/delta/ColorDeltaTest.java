/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.visualisation.delta;



import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * This test checks the java bean for a Clone Shape
 * @author fisc_ph
 *
 */
public class ColorDeltaTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testEqualsObject() {
		//CHECKSTYLE:OFF
		ColorDelta delta1 = new ColorDelta("1234", 0xFFFFFF);
		ColorDelta delta2 = new ColorDelta("1234", 0xFFFF00);
		ColorDelta delta3 = new ColorDelta("1234", 0xFFFFFF);
		ColorDelta delta4 = new ColorDelta("1235", 0xFFFFFF);
		//CHECKSTYLE:ON
		
		assertFalse("They should not match", delta1.equals(delta2));
		assertFalse("They should not match", delta1.equals(delta4));
		assertTrue("They should not match", delta1.equals(delta3));
		
		assertEquals("Same HashCode", delta1.hashCode(), delta3.hashCode());
	}

	@Test
	public void testToString() {
		final int TEST_COLOR = 0xFFFFFF;
		ColorDelta delta1 = new ColorDelta("1234", TEST_COLOR);
		final String EXPECTED_STRING = "UUID: 1234 - Color: 0xFFFFFF";
		final String EXPECTED_STRING2 = "UUID: No UUID - Color: 0xFFFFFF";
		
		String colorString = delta1.toString();
		assertEquals("Got correct String from Delta", EXPECTED_STRING, colorString);

		String colorString2 =  new ColorDelta(null, TEST_COLOR).toString();
		assertEquals("Got correct String from Delta", EXPECTED_STRING2, colorString2);

	}
	
}
