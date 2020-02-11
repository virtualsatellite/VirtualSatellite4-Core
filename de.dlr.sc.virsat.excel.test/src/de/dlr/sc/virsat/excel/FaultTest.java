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

import de.dlr.sc.virsat.excel.fault.Fault;
import de.dlr.sc.virsat.excel.fault.FaultType;

/**
 * This class tests the Fault class.
 */
public class FaultTest {

	@Test
	public void testToString() {
		Fault fault = new Fault(FaultType.STRUCTURAL_ELEMENT_UUIDS_DO_NOT_MATCH, 1, 1);
		String toString = fault.toString();
		assertEquals("String output is correct", "Type: STRUCTURAL_ELEMENT_UUIDS_DO_NOT_MATCH, Sheet: 1, Line: 1", toString);
	}

	@Test
	public void testEquals() {
		Fault fault1 = new Fault(FaultType.STRUCTURAL_ELEMENT_UUIDS_DO_NOT_MATCH, 1, 1);
		Fault fault2 = new Fault(FaultType.STRUCTURAL_ELEMENT_UUIDS_DO_NOT_MATCH, 2, 1);
		assertNotEquals("Comparing two faults that are not equal", fault1, fault2);
		Fault fault3 = new Fault(FaultType.STRUCTURAL_ELEMENT_UUIDS_DO_NOT_MATCH, 1, 1);
		assertEquals("Comparing two faults that are equal", fault1, fault3);
	}
}
