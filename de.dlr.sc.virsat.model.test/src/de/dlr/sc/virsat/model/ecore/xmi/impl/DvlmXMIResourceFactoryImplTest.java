/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.ecore.xmi.impl;

import static org.junit.Assert.assertTrue;

import org.eclipse.emf.ecore.resource.Resource;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Test Case for the DvlmXMI Resource Factory
 * @author fisc_ph
 *
 */
public class DvlmXMIResourceFactoryImplTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testCreateResourceURI() {
		DvlmXMIResourceFactoryImpl dvlmXmiResourceFactory = new DvlmXMIResourceFactoryImpl();
		
		Resource resource = dvlmXmiResourceFactory.createResource(null);
		
		assertTrue("Is ocrrect instance", resource instanceof DvlmXMIResourceImpl);
	}

}
