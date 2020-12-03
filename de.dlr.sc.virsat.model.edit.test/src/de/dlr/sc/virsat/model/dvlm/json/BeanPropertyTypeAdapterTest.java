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
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;

import org.junit.Before;
import org.junit.Test;

import de.dlr.sc.virsat.model.concept.types.property.BeanPropertyType;

public class BeanPropertyTypeAdapterTest {
	
	private BeanPropertyTypeAdapter adapter;
	
	@Before
	public void setUp() {
		adapter = new BeanPropertyTypeAdapter();
	}

	@Test
	public void testUnmarshal() throws Exception {
		BeanPropertyType unmarshalledType = adapter.unmarshal(null);
		assertNull(unmarshalledType);
		
		unmarshalledType = adapter.unmarshal(BeanPropertyType.BOOLEAN.toString());
		assertEquals(BeanPropertyType.BOOLEAN, unmarshalledType);
		
		assertThrows("The string should be valid",
			IllegalArgumentException.class, () -> {
				adapter.unmarshal("");
			}
		);
	}

	@Test
	public void testMarshal() throws Exception {
		String marshalledString = adapter.marshal(null);
		assertNull(marshalledString);
		
		marshalledString = adapter.marshal(BeanPropertyType.BOOLEAN);
		assertEquals(BeanPropertyType.BOOLEAN.toString().toLowerCase(), marshalledString);
	}

}
