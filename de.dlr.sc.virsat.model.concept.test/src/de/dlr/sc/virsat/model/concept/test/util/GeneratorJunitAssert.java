/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.concept.test.util;

import java.io.IOException;
import de.dlr.sc.virsat.model.concept.test.TestActivator;
import de.dlr.sc.virsat.test.utils.TestUtil;

/**
 * Some special Assert Statements to check the code generators with
 * reference content stored in the plugin/ test fragment resources
 */
public class GeneratorJunitAssert {

	/**
	 * Private constructor since this is just a util class
	 */
	private GeneratorJunitAssert() {
	}

	/**
	 * Method to check content against a reference from the resources folder
	 * @param actual the current content as string
	 * @param expectedFilePath the path to the file containing the expected content
	 * @throws IOException in case the expected content  file could not be read
	 */
	public static void assertEqualContent(String actual, String expectedFilePath) throws IOException {
		TestUtil.assertEqualContent(actual, TestActivator.FRAGMENT_ID, expectedFilePath);
	}
	
	/**
	 * Method to check content against a reference from the resources folder
	 * @param actual the current content as CharSequence
	 * @param expectedFilePath the path to the file containing the expected content
	 * @throws IOException in case the expected content  file could not be read
	 */
	public static void assertEqualContent(CharSequence actual, String expectedFilePath) throws IOException {
		String actualString = actual.toString();
		assertEqualContent(actualString, expectedFilePath);
	}
}
