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

import org.eclipse.emf.common.util.URI;
import org.junit.Before;
import org.junit.Test;

public class UriAdapterTest {

	private UriAdapter adapter;
	private URI testUri;
	private static final String TEST_RESOURCE = "resources/file[1].xls";
	private static final String TEST_RESOURCE_STRING = "/" + TEST_RESOURCE;
	
	@Before
	public void setUp() throws Exception {
		adapter = new UriAdapter();
		testUri = URI.createPlatformPluginURI(TEST_RESOURCE, false);
	}

	@Test
	public void testUnmarshalString() throws Exception {
		URI unmarshalledUri = adapter.unmarshal(null);
		assertNull(unmarshalledUri);
		
		unmarshalledUri = adapter.unmarshal(TEST_RESOURCE);
		assertEquals(testUri, unmarshalledUri);
	}

	@Test
	public void testMarshalURI() throws Exception {
		String marshalledUriString = adapter.marshal(null);
		assertNull(marshalledUriString);
		
		marshalledUriString = adapter.marshal(testUri);
		assertEquals(TEST_RESOURCE_STRING, marshalledUriString);
	}

}
