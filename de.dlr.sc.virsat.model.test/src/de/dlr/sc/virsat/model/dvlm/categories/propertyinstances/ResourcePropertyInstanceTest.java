/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.dvlm.categories.propertyinstances;

import static org.junit.Assert.assertEquals;

import org.eclipse.emf.common.util.URI;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Test Case for checking that the URI is well handled
 * @author fisc_ph
 *
 */
public class ResourcePropertyInstanceTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetResourceUri() {
		final String TEST_URI_1 = "resources/File[2].pdf";
		
		ResourcePropertyInstance rpi = PropertyinstancesFactory.eINSTANCE.createResourcePropertyInstance();
		URI platFormUri = URI.createPlatformResourceURI(TEST_URI_1, false);
		
		rpi.setUri(platFormUri);
		String resourceUri = rpi.getResourceUri();

		assertEquals("Uri has been correctly Tarnsfored to resourceUri", "/resources/File[2].pdf", resourceUri);
		
		URI rpiUri = rpi.getUri();
		assertEquals("The Uri is the same", platFormUri, rpiUri);
	}
}
