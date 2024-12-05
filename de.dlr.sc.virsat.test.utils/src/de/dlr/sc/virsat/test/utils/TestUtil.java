/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.test.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;

import org.junit.Assert;

public class TestUtil {
	
	private TestUtil() {
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
	public static void assertEqualContent(String actual, String fragementId, String expectedFilePath) throws IOException {
	
		if (ENV_VARIABLE_FLUSH_ACTUAL_TRUE.equalsIgnoreCase(System.getenv(ENV_VARIABLE_FLUSH_ACTUAL))) {
			String fileName = System.getenv(ENV_VARIABLE_FLUSH_PATH) + expectedFilePath.substring(expectedFilePath.lastIndexOf('/'), expectedFilePath.length());
			
			PrintWriter writer = new PrintWriter(fileName, "UTF-8");
			writer.println(actual);
			writer.close();
			return;
		}
		
		String expectedString = getResourceContentAsString(fragementId, expectedFilePath);
		String expectedNoWs = expectedString.replaceAll("\\s+", "");
		String actualNoWs = actual.replaceAll("\\s+", "");
		
		if (!expectedNoWs.equals(actualNoWs)) {
			Assert.assertEquals("File content for " + expectedFilePath + " is correct", expectedString, actual);
		}
	}
	
	/**
	 * Method to access the fragments contents from the resource folder and to hand it back as string
	 * @param resourcePath the path to the resource starting with "resource/"
	 * @return the content of the resource as string
	 * @throws IOException throws
	 */
	public static String getResourceContentAsString(String fragmentId, String resourcePath) throws IOException {
		URL url = new URL("platform:/plugin/" + fragmentId + resourcePath);
		InputStream inputStream = url.openConnection().getInputStream();

		StringBuilder fileContent = new StringBuilder();
		try (BufferedReader in = new BufferedReader(new InputStreamReader(inputStream))) {
			String inputLine;
	
			while ((inputLine = in.readLine()) != null) {
				fileContent.append(inputLine);
				fileContent.append(System.lineSeparator());
			}
		}
		
		return fileContent.toString();
	}
}
