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

public class AnyTypeAdapterTest {

	private AnyTypeAdapter adapter;
	private Object testObject;
	
	@Before
	public void setUp() throws Exception {
		adapter = new AnyTypeAdapter();
		testObject = new Object();
	}

	@Test
	public void testUnmarshalObject() throws Exception {
		Object marshalledObject = adapter.marshal(testObject);
		assertEquals(testObject, marshalledObject);
	}

	@Test
	public void testMarshalObject() throws Exception {
		Object unmarshalledObject = adapter.unmarshal(testObject);
		assertEquals(testObject, unmarshalledObject);
	}

}
