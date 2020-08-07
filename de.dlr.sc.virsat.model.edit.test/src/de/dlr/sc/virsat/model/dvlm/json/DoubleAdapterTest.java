/*******************************************************************************
 * Copyright (c) 2020 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.dvlm.json;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class DoubleAdapterTest {

	private DoubleAdapter adapter;
	private Double testDouble;

	@Before
	public void setUp() throws Exception {
		adapter = new DoubleAdapter();
		testDouble = Double.NaN;
	}

	@Test
	public void testUnmarshalDouble() throws Exception {
		Double unmarshalledDouble = adapter.unmarshal(testDouble);
		assertEquals(testDouble, unmarshalledDouble);
	}

	@Test
	public void testMarshalDouble() throws Exception {
		Double marshalledDouble = adapter.marshal(testDouble);
		assertEquals(null, marshalledDouble);
		
		testDouble = 0.0;
		marshalledDouble = adapter.marshal(testDouble);
		assertEquals(testDouble, marshalledDouble);
	}

}
