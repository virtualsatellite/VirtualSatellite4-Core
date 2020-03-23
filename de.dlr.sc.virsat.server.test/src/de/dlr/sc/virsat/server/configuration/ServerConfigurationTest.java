/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.server.configuration;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;

import org.junit.Test;

public class ServerConfigurationTest {

	@Test
	public void testDefaultProperties() throws IOException {
		String expectedDefaultPropertiesFilePath = "/resources/default_configuration.properties";
		assertEquals(expectedDefaultPropertiesFilePath, ServerConfiguration.getPropertiesFilePath());
	}

	@Test
	public void testLoadProperties() throws IOException {
		final String testKey = "testKey";
		final String testValue = "testValue";
		
		Path propertiesFile = Files.createTempFile("testProperties", ".properties");
		Files.write(propertiesFile, (testKey + ":" + testValue).getBytes());
		
		ServerConfiguration.loadProperties(propertiesFile.toString());
		Properties properties = ServerConfiguration.getProperties();
		assertEquals(1, properties.size());
		assertEquals(testValue, properties.getProperty(testKey));
		assertEquals(propertiesFile.toString(), ServerConfiguration.getPropertiesFilePath());
	}
}
