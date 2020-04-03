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
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import org.apache.commons.io.IOUtils;
import org.junit.Before;
import org.junit.Test;

import de.dlr.sc.virsat.commons.file.VirSatFileUtils;
import de.dlr.sc.virsat.team.VersionControlSystem;

public class RepositoryConfigurationTest {
	
	//Test data
	private static final String TEST_PATH = "local/In/Repo";
	private static final String TEST_REMOTE = "gitlab.dlr.de/fancy-project";
	private static final String TEST_USER = "TestUser";
	private static final String TEST_PASSWORD = "TestPassword";
	private static final String TEST_PROJECT = "TestProject";
	private static final String TEST_BACKEND = "GIT";
	
	private RepositoryConfiguration testConfiguration;
	
	@Before
	public void setUp() {
		testConfiguration = new RepositoryConfiguration(
				TEST_PROJECT,
				TEST_PATH,
				TEST_REMOTE,
				VersionControlSystem.GIT,
				TEST_USER,
				TEST_PASSWORD
		);
	}
	
	@Test
	public void testLoadProperties() throws IOException, URISyntaxException {
		String testConfigFileString = RepositoryConfiguration.REMOTE_URL_KEY + ":" + TEST_REMOTE + "\n" 
				+ RepositoryConfiguration.PROJECT_NAME_KEY + ":" + TEST_PROJECT + "\n" 
				+ RepositoryConfiguration.BACKEND_KEY + ":" + TEST_BACKEND + "\n" 
				+ RepositoryConfiguration.FUNCTIONAL_ACCOUNT_NAME_KEY + ":" + TEST_USER + "\n" 
				+ RepositoryConfiguration.LOCAL_PATH_KEY + ":" + TEST_PATH + "\n" 
				+ RepositoryConfiguration.FUNCTIONAL_ACCOUNT_PASSWORD_KEY + ":" + TEST_PASSWORD;
		
		try (InputStream inputStream = new ByteArrayInputStream(testConfigFileString.getBytes(StandardCharsets.UTF_8))) {
			RepositoryConfiguration configuration = new RepositoryConfiguration(inputStream);
			assertEquals("Configuration properly loaded", testConfiguration, configuration);
		}
	}
	
	@Test
	public void testSaveProperties() throws IOException, URISyntaxException {
		final String TEST_FILE_NAME = "test.properties";
		
		// Prepare Temporary Folder
		File tempPath =  VirSatFileUtils.createAutoDeleteTempDirectory("RepoConfigTest").toFile();
		
		try (OutputStream outputStream = new FileOutputStream(new File(tempPath, TEST_FILE_NAME))) {
			testConfiguration.saveProperties(outputStream);
		}
		
		try (InputStream inputStream = new FileInputStream(new File(tempPath, TEST_FILE_NAME))) {
			String stringFromInputStream = IOUtils.toString(inputStream, "UTF-8");
			assertTrue("Contains value", stringFromInputStream.contains(TEST_REMOTE));
			assertTrue("Contains value", stringFromInputStream.contains(TEST_USER));
			assertTrue("Contains value", stringFromInputStream.contains(TEST_PASSWORD));
			assertTrue("Contains value", stringFromInputStream.contains(TEST_PROJECT));
			assertTrue("Contains value", stringFromInputStream.contains(TEST_BACKEND));
		}
		
		try (InputStream loadStream = new FileInputStream(new File(tempPath, TEST_FILE_NAME))) {
			RepositoryConfiguration importedConfiguration = new RepositoryConfiguration(loadStream);
			assertEquals("Saving and loading produces the same configuration", testConfiguration, importedConfiguration);
		}
	}
	
	@Test
	public void testUpdate() throws URISyntaxException {
		RepositoryConfiguration updatedConfiguration = new RepositoryConfiguration();
		assertNotEquals("Configurations different before update", testConfiguration, updatedConfiguration);
		
		updatedConfiguration.update(testConfiguration);
		assertEquals("Configurations equal after update", testConfiguration, updatedConfiguration);
	}
	
	@Test
	public void testIsValid() throws URISyntaxException {
		assertTrue(testConfiguration.isValid());

		RepositoryConfiguration invalidConfig;
		invalidConfig = new RepositoryConfiguration(testConfiguration);
		invalidConfig.setRemoteUri("");
		assertFalse(invalidConfig.isValid());
		
		invalidConfig = new RepositoryConfiguration(testConfiguration);
		invalidConfig.setProjectName("");
		assertFalse(invalidConfig.isValid());
	}
}
