/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.tests.test;

import de.dlr.sc.virsat.test.utils.TestUtil;
import java.io.IOException;

public class TestActivator {
	/**
	 * Hidden construcotr of activator class
	 */
	private TestActivator() {
	}
	
	public static final String FRAGMENT_ID = "de.dlr.sc.virsat.model.extension.tests.test";

	/**
	 * Method to access the fragments contents from the resource folder and to ahnd it back as string
	 * @param resourcePath the path to the resource starting with "resource/"
	 * @return the content of the resource as string
	 * @throws IOException throws
	 */
	public static String getResourceContentAsString(String resourcePath) throws IOException {
		return TestUtil.getResourceContentAsString(FRAGMENT_ID, resourcePath);
	}
}
