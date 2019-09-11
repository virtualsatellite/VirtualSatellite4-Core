/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.excel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;


/**
 * This class tests the Fault class.
 * @author muel_s8
 *
 */

public class FaultTest {

	@Test
	public void testToString() {
		Fault fault = new Fault(FaultType.FROM_INTERFACE_END_NOT_FOUND, 1, 1);
		String toString = fault.toString();
		assertEquals("String output is correct", "Type: FROM_INTERFACE_END_NOT_FOUND, Sheet: 1, Line: 1", toString);
	}

	@Test
	public void testEquals() {
		Fault fault1 = new Fault(FaultType.FROM_INTERFACE_END_NOT_FOUND, 1, 1);
		Fault fault2 = new Fault(FaultType.FROM_INTERFACE_END_NOT_FOUND, 2, 1);
		assertNotEquals("Comparing two faults that are not equal", fault1, fault2);
		Fault fault3 = new Fault(FaultType.FROM_INTERFACE_END_NOT_FOUND, 1, 1);
		assertEquals("Comparing two faults that are equal", fault1, fault3);
	}
	
}
