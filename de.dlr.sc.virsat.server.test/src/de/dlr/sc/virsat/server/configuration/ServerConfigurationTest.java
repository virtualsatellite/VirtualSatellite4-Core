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
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

import de.dlr.sc.virsat.commons.file.VirSatFileUtils;
import de.dlr.sc.virsat.server.Activator;

public class ServerConfigurationTest {
	
	@Test
	public void testDefaultProperties() throws IOException {
		String expectedDefaultPropertiesFilePath = "resources/server.properties";
		assertEquals(expectedDefaultPropertiesFilePath, Activator.getDefault().getPropertiesFilePath());
		assertFalse("There are some configurations in the default configuration file", ServerConfiguration.getProperties().isEmpty());
		assertFalse(ServerConfiguration.getRepositoryConfigurationsDir().isEmpty());
		assertFalse(ServerConfiguration.getLoginServiceClass().isEmpty());
		assertFalse(ServerConfiguration.getAuthPropertiesFile().isEmpty());
	}

	@Test
	public void testLoadProperties() throws IOException {
		final String testKey = "testKey";
		final String testValue = "testValue";
		
		String testConfigFileString = testKey + ":" + testValue;
		InputStream inputStream = new ByteArrayInputStream(testConfigFileString.getBytes(StandardCharsets.UTF_8));
		
		ServerConfiguration.loadProperties(inputStream);
		Properties properties = ServerConfiguration.getProperties();
		assertFalse(properties.isEmpty());
		assertEquals(testValue, properties.getProperty(testKey));
	}
	
	@Test
	public void testSaveProperties() throws IOException {
		
		final String REPOSITORY_CONFIGURATIONS_DIR = "some/dir";
		final String TEST_FILE_NAME = "test.properties";
		
		// Prepare Temporary Folder
		File tempPath =  VirSatFileUtils.createAutoDeleteTempDirectory("ServerConfigTest").toFile();
				
		OutputStream outputStream = new FileOutputStream(new File(tempPath, TEST_FILE_NAME));
		ServerConfiguration.setRepositoryConfigurationsDir(REPOSITORY_CONFIGURATIONS_DIR);
		ServerConfiguration.saveProperties(outputStream);
		
		InputStream inputStream = new FileInputStream(new File(tempPath, TEST_FILE_NAME));
		String stringFromInputStream = IOUtils.toString(inputStream, "UTF-8");
		assertTrue("Contains value", stringFromInputStream.contains(REPOSITORY_CONFIGURATIONS_DIR));
		
		InputStream loadStream = new FileInputStream(new File(tempPath, TEST_FILE_NAME));
		ServerConfiguration.loadProperties(loadStream);
		assertEquals("Remote loaded", REPOSITORY_CONFIGURATIONS_DIR, ServerConfiguration.getRepositoryConfigurationsDir());
	}
}
