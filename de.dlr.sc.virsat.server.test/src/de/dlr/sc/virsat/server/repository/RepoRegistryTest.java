/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.server.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.net.URISyntaxException;

import org.junit.After;
import org.junit.Test;

import de.dlr.sc.virsat.server.configuration.RepositoryConfiguration;
import de.dlr.sc.virsat.team.VersionControlSystem;

public class RepoRegistryTest {
	
	private static final String REPO_NAME = "TestRepository";
	
	@After
	public void tearDown() {
		RepoRegistry.getInstance().getRepositories().clear();
	}
	
	@Test
	public void testBasicAddGet() throws URISyntaxException {
		//Create basic test repsoitory 
		RepositoryConfiguration config = new RepositoryConfiguration();
		config.setBackend(VersionControlSystem.GIT);
		config.setRemoteUri("");
		final ServerRepository TEST_REPO = new ServerRepository(new File(""), config);
		
		assertTrue("There is not Repository yet", RepoRegistry.getInstance().getRepositories().isEmpty());
		
		RepoRegistry.getInstance().addRepository(REPO_NAME, TEST_REPO);
		
		ServerRepository returnedFromRegistry = RepoRegistry.getInstance().getRepository(REPO_NAME);
		
		assertEquals("Object should be the same as the one added", TEST_REPO, returnedFromRegistry);
	}
	
	@Test
	public void testGetRepositories() throws URISyntaxException {
		//Create basic test repsoitory 
		RepositoryConfiguration config = new RepositoryConfiguration();
		config.setBackend(VersionControlSystem.GIT);
		config.setRemoteUri("");
		final ServerRepository TEST_REPO = new ServerRepository(new File(""), config);
		
		RepoRegistry.getInstance().getRepositories().put(REPO_NAME, TEST_REPO);
		
		assertEquals("COntains exactly one repository", 1, RepoRegistry.getInstance().getRepositories().size());
		
		ServerRepository returnedFromRegistry = RepoRegistry.getInstance().getRepositories().get(REPO_NAME);
		
		assertEquals("Object should be the same as the one added", TEST_REPO, returnedFromRegistry);
	}
}
