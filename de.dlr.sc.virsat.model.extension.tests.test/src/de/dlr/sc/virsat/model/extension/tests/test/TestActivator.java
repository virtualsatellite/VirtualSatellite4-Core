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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import org.junit.Assert;

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
		URL url = new URL("platform:/plugin/" + FRAGMENT_ID + resourcePath);
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
	
	/**
	 * Assert a message after removing new line at end of file
	 * @param message
	 * @param expected
	 * @param actual
	 */
	public static void assertEqualsNoWs(String message, String expected, String actual) {
		String expectedNoWs = expected.replaceAll("\\s+", "");
		String actualNoWs = actual.replaceAll("\\s+", "");
		
		if (!expectedNoWs.equals(actualNoWs)) {	
			Assert.assertEquals(message, expected, actual);
		}
	}
}
