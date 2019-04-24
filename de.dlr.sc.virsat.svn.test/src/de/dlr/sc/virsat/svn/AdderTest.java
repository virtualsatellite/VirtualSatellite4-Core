/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.svn;

import static org.junit.Assert.assertArrayEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * the test class to test the Adder
 * @author scha_vo
 *
 */

public class AdderTest {

	@Before
	public void setUp() throws Exception {
		Adder myAdderObject = new Adder();
		//CHECKSTYLE:OFF
		myAdderObject.setNumberOne(12);
		myAdderObject.setNumberTwo(14);
		
		assertArrayEquals(new int[] {26}, new int[] {myAdderObject.sum()});
		// CHECKSTYLE:ON
	}
	@Test
	public final void testAdd() {
		
	}
	
	@After
	public void tearDown() throws Exception {
	}

}
