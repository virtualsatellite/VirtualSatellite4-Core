/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.ext.core.core;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

/**
 *
 */
public class CorePackageTest {
	
	@Test
	public void testCoreFactory() {

		// Simply initialize generated package and factory and check that there are no issues
		assertNotNull("Package should be initialized now", CorePackage.eINSTANCE);
		assertNotNull("Package should be initialized now", CoreFactory.eINSTANCE.getCorePackage());

	}

}
