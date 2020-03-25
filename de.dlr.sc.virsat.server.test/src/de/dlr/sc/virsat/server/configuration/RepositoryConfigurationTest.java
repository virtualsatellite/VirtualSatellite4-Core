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
import java.nio.charset.StandardCharsets;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

public class RepositoryConfigurationTest {
	
	//Test data
	private static final String TEST_REMOTE = "gitlab.dlr.de/fancy-project";
	private static final String TEST_USER = "TestUser";
	private static final String TEST_PASSWORD = "TestPassword";
	
	@Test
	public void testLoadProperties() throws IOException {
		
		//The keys - only defined as private in the configuration itself; no need to expose
		final String REMOTE_URL_KEY = "repository.remoteURI";
		final String ACCOUNT_NAME_KEY = "repository.credentials.username";
		final String ACCOUNT_PASSWORD_KEY = "repository.credentials.password";
		
		String testConfigFileString = REMOTE_URL_KEY + ":" + TEST_REMOTE + "\n" 
				+ ACCOUNT_NAME_KEY + ":" + TEST_USER + "\n" 
				+ ACCOUNT_PASSWORD_KEY + ":" + TEST_PASSWORD;
		InputStream inputStream = new ByteArrayInputStream(testConfigFileString.getBytes(StandardCharsets.UTF_8));
		
		// Check that all values are loaded
		RepositoryConfiguration configuration = new RepositoryConfiguration(inputStream);
		assertEquals("Remote loaded", TEST_REMOTE, configuration.getRemoteUri());
		assertEquals("Users loaded", TEST_USER, configuration.getFunctionalAccountName());
		assertEquals("Password laoded",	TEST_PASSWORD, configuration.getFunctionalAccountPassword());
	}
	
	@Test
	public void testSaveProperties() throws IOException {
		
		final String TEST_FILE_NAME = "test.properties";
		
		RepositoryConfiguration configuration = new RepositoryConfiguration(TEST_REMOTE, null, TEST_USER, TEST_PASSWORD);
		OutputStream outputStream = new FileOutputStream(new File(TEST_FILE_NAME));
		configuration.saveProperties(outputStream);
		
		InputStream inputStream = new FileInputStream(new File(TEST_FILE_NAME));
		String stringFromInputStream = IOUtils.toString(inputStream, "UTF-8");
		assertTrue("Contains value", stringFromInputStream.contains(TEST_REMOTE));
		assertTrue("Contains value", stringFromInputStream.contains(TEST_USER));
		assertTrue("Contains value", stringFromInputStream.contains(TEST_PASSWORD));
		
		InputStream loadStream = new FileInputStream(new File(TEST_FILE_NAME));
		RepositoryConfiguration importedConfiguration = new RepositoryConfiguration(loadStream);
		assertEquals("Remote loaded", TEST_REMOTE, importedConfiguration.getRemoteUri());
		assertEquals("Users loaded", TEST_USER, importedConfiguration.getFunctionalAccountName());
		assertEquals("Password laoded",	TEST_PASSWORD, importedConfiguration.getFunctionalAccountPassword());
		
	}

}
