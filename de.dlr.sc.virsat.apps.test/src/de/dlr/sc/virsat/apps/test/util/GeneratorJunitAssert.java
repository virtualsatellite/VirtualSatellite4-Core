/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.apps.test.util;

import java.io.IOException;
import java.io.PrintWriter;

import org.junit.Assert;

import de.dlr.sc.virsat.apps.test.TestActivator;


/**
 * Some special Assert Statements to check the code generators with
 * reference content stored in the plugin/ test fragment resources
 * @author fisc_ph
 *
 */
public class GeneratorJunitAssert {

	/**
	 * Private constructor since this is just a util class
	 */
	private GeneratorJunitAssert() {
	}

	/**
	 * Set this flag to output the actual content to the console
	 */
	
	private static final String ENV_VARIABLE_FLUSH_ACTUAL = "flushActualGeneratorFiles";
	private static final String ENV_VARIABLE_FLUSH_ACTUAL_TRUE = "true";
	private static final String ENV_VARIABLE_FLUSH_PATH = "flushActualPath";
		
	
	/**
	 * Method to check content against a reference from the resources folder
	 * @param actual the current content as string
	 * @param expectedFilePath the path to the file containing the expected content
	 * @throws IOException in case the expected content  file could not be read
	 */
	public static void assertEqualContent(String actual, String expectedFilePath) throws IOException {
	
		if (ENV_VARIABLE_FLUSH_ACTUAL_TRUE.equalsIgnoreCase(System.getenv(ENV_VARIABLE_FLUSH_ACTUAL))) {
			String fileName = System.getenv(ENV_VARIABLE_FLUSH_PATH) + expectedFilePath.substring(expectedFilePath.lastIndexOf('/'), expectedFilePath.length());
			
			PrintWriter writer = new PrintWriter(fileName, "UTF-8");
			writer.println(actual);
			writer.close();
			return;
		}
		
		String expectedString = TestActivator.getResourceContentAsString(expectedFilePath);
		String expectedNoWs = expectedString.replaceAll("\\s+", "");
		String actualNoWs = actual.replaceAll("\\s+", "");
		
		if (!expectedNoWs.equals(actualNoWs)) {
			Assert.assertEquals("File content for " + expectedFilePath + " is correct", expectedString, actual);
		}
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
