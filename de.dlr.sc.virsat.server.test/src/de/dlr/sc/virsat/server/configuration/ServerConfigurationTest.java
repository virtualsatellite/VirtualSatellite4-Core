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
import static org.junit.Assert.assertFalse;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

import org.junit.Test;

import de.dlr.sc.virsat.server.Activator;

public class ServerConfigurationTest {

	@Test
	public void testDefaultProperties() throws IOException {
		String expectedDefaultPropertiesFilePath = "resources/server.properties";
		assertEquals(expectedDefaultPropertiesFilePath, Activator.getDefault().getPropertiesFilePath());
		assertFalse("There are some configurations in the default configuration file", ServerConfiguration.getProperties().isEmpty());
	}

	@Test
	public void testLoadProperties() throws IOException {
		final String testKey = "testKey";
		final String testValue = "testValue";
		
		String testConfigFileString = testKey + ":" + testValue;
		InputStream inputStream = new ByteArrayInputStream(testConfigFileString.getBytes(StandardCharsets.UTF_8));
		
		ServerConfiguration.loadProperties(inputStream);
		Properties properties = ServerConfiguration.getProperties();
		assertEquals(1, properties.size());
		assertEquals(testValue, properties.getProperty(testKey));
	}
}
