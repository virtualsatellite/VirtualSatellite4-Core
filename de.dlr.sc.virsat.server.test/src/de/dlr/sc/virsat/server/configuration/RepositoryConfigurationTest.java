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
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;

import org.apache.commons.io.IOUtils;
import org.eclipse.core.resources.ResourcesPlugin;
import org.junit.Test;

import de.dlr.sc.virsat.team.VersionControlSystem;

public class RepositoryConfigurationTest {
	
	//Test data
	private static final String TEST_PATH = "local/In/Repo";
	private static final String TEST_REMOTE = "gitlab.dlr.de/fancy-project";
	private static final String TEST_USER = "TestUser";
	private static final String TEST_PASSWORD = "TestPassword";
	private static final String TEST_PROJECT = "TestProject";
	private static final String TEST_BACKEND = "GIT";
	
	@Test
	public void testLoadProperties() throws IOException, URISyntaxException {
		
		String testConfigFileString = RepositoryConfiguration.REMOTE_URL_KEY + ":" + TEST_REMOTE + "\n" 
				+ RepositoryConfiguration.PROJECT_NAME_KEY + ":" + TEST_PROJECT + "\n" 
				+ RepositoryConfiguration.BACKEND_KEY + ":" + TEST_BACKEND + "\n" 
				+ RepositoryConfiguration.FUNCTIONAL_ACCOUNT_NAME_KEY + ":" + TEST_USER + "\n" 
				+ RepositoryConfiguration.LOCAL_PATH_KEY + ":" + TEST_PATH + "\n" 
				+ RepositoryConfiguration.FUNCTIONAL_ACCOUNT_PASSWORD_KEY + ":" + TEST_PASSWORD;
		InputStream inputStream = new ByteArrayInputStream(testConfigFileString.getBytes(StandardCharsets.UTF_8));
		
		// Check that all values are loaded
		RepositoryConfiguration configuration = new RepositoryConfiguration(inputStream);
		assertEquals("Remote loaded", new URI(TEST_REMOTE), configuration.getRemoteUri());
		assertEquals("Backend loaded", VersionControlSystem.GIT, configuration.getBackend());
		assertEquals("Users loaded", TEST_USER, configuration.getFunctionalAccountName());
		assertEquals("Password loaded",	TEST_PASSWORD, configuration.getFunctionalAccountPassword());
		assertEquals("Project loaded",	TEST_PROJECT, configuration.getProjectName());
		assertEquals("Path loaded", new File(TEST_PATH), configuration.getLocalPath());
	}
	
	@Test
	public void testSaveProperties() throws IOException, URISyntaxException {
		
		File wsRootFile = ResourcesPlugin.getWorkspace().getRoot().getRawLocation().toFile();
		final String TEST_FILE_NAME = "test.properties";
		RepositoryConfiguration configuration = new RepositoryConfiguration(
				TEST_PROJECT,
				new File(TEST_PATH),
				new URI(TEST_REMOTE),
				VersionControlSystem.GIT,
				TEST_USER,
				TEST_PASSWORD
		);
		OutputStream outputStream = new FileOutputStream(new File(wsRootFile, TEST_FILE_NAME));
		configuration.saveProperties(outputStream);
		
		InputStream inputStream = new FileInputStream(new File(wsRootFile, TEST_FILE_NAME));
		String stringFromInputStream = IOUtils.toString(inputStream, "UTF-8");
		assertTrue("Contains value", stringFromInputStream.contains(TEST_REMOTE));
		assertTrue("Contains value", stringFromInputStream.contains(TEST_USER));
		assertTrue("Contains value", stringFromInputStream.contains(TEST_PASSWORD));
		assertTrue("Contains value", stringFromInputStream.contains(TEST_PROJECT));
		assertTrue("Contains value", stringFromInputStream.contains(TEST_BACKEND));
		
		InputStream loadStream = new FileInputStream(new File(wsRootFile, TEST_FILE_NAME));
		RepositoryConfiguration importedConfiguration = new RepositoryConfiguration(loadStream);
		assertEquals("Remote loaded", new URI(TEST_REMOTE), importedConfiguration.getRemoteUri());
		assertEquals("Users loaded", TEST_USER, importedConfiguration.getFunctionalAccountName());
		assertEquals("Password laoded",	TEST_PASSWORD, importedConfiguration.getFunctionalAccountPassword());
		assertEquals("Project laoded",	TEST_PROJECT, importedConfiguration.getProjectName());
		assertEquals("Backend loaded", VersionControlSystem.GIT, importedConfiguration.getBackend());
		assertEquals("Path loaded", new File(TEST_PATH), importedConfiguration.getLocalPath());
	}
	
	@Test
	public void testUpdate() throws URISyntaxException {
		RepositoryConfiguration configuration = new RepositoryConfiguration(
				TEST_PROJECT,
				new File(TEST_PATH),
				new URI(TEST_REMOTE),
				VersionControlSystem.GIT,
				TEST_USER,
				TEST_PASSWORD
		);
		
		RepositoryConfiguration updatedConfiguration = new RepositoryConfiguration(
				"",
				new File(""),
				new URI(""),
				VersionControlSystem.SVN,
				"",
				""
		);
		
		updatedConfiguration.update(configuration);
		
		assertEquals("Remote loaded", new URI(TEST_REMOTE), updatedConfiguration.getRemoteUri());
		assertEquals("Users loaded", TEST_USER, updatedConfiguration.getFunctionalAccountName());
		assertEquals("Password laoded",	TEST_PASSWORD, updatedConfiguration.getFunctionalAccountPassword());
		assertEquals("Project laoded",	TEST_PROJECT, updatedConfiguration.getProjectName());
		assertEquals("Backend loaded", VersionControlSystem.GIT, updatedConfiguration.getBackend());
		assertEquals("Path loaded", new File(TEST_PATH), updatedConfiguration.getLocalPath());
	}
}
