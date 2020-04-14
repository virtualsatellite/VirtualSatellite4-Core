/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.ext.core.core.util;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.XMIResource;
import org.junit.Test;

/**
 *
 */
public class CoreResourceFactoryImplTest {
	
	@Test
	public void testCoreResourceCreation() {
		
		CoreResourceFactoryImpl resourceFactory = new CoreResourceFactoryImpl();
		Resource coreResource = resourceFactory.createResource(URI.createFileURI("test.test"));
		assertNotNull("new resource should not be null", coreResource);
		assertTrue("Should be a XMI resource", coreResource instanceof XMIResource);
	}

}
