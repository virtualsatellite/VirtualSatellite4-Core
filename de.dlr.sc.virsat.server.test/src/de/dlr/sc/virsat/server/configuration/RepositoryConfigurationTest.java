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

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import org.junit.Test;

import de.dlr.sc.virsat.team.VersionControlSystem;

public class RepositoryConfigurationTest {
	
	@Test
	public void testLoadProperties() throws IOException {
				
		//Test data
		final String TEST_REMOTE = "gitlab.dlr.de/fancy-project";
		final String TEST_BACKEND = "GIT";
		final String TEST_USER = "TestUser";
		final String TEST_PASSWORD = "TestPassword";
		
		String testConfigFileString = RepositoryConfiguration.REMOTE_URL_KEY + ":" + TEST_REMOTE + "\n" 
				+ RepositoryConfiguration.BACKEND_KEY + ":" + TEST_BACKEND + "\n" 
				+ RepositoryConfiguration.FUNCTIONAL_ACCOUNT_NAME_KEY + ":" + TEST_USER + "\n" 
				+ RepositoryConfiguration.FUNCTIONAL_ACCOUNT_PASSWORD_KEY + ":" + TEST_PASSWORD;
		InputStream inputStream = new ByteArrayInputStream(testConfigFileString.getBytes(StandardCharsets.UTF_8));
		
		// Check that all values are loaded
		RepositoryConfiguration configuration = new RepositoryConfiguration(inputStream);
		assertEquals("Remote loaded", TEST_REMOTE, configuration.getRemoteUri());
		assertEquals("Backend loaded", VersionControlSystem.GIT, configuration.getBackend());
		assertEquals("Users loaded", TEST_USER, configuration.getFunctionalAccountName());
		assertEquals("Password laoded",	TEST_PASSWORD, configuration.getFunctionalAccountPassword());
	}

}
