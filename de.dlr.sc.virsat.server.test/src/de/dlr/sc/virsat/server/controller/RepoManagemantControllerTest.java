/*******************************************************************************
 * Copyright (c) 2020 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.server.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.io.File;
import java.net.URISyntaxException;

import org.junit.Before;
import org.junit.Test;

import de.dlr.sc.virsat.server.configuration.RepositoryConfiguration;
import de.dlr.sc.virsat.server.repository.RepoRegistry;
import de.dlr.sc.virsat.server.repository.ServerRepository;
import de.dlr.sc.virsat.team.VersionControlSystem;

public class RepoManagemantControllerTest {

	private static final String TEST_REPOSITORY_NAME = "testRepositoryName";
	
	private RepoManagemantController repoManagemantController;
	private RepositoryConfiguration testRepositoryConfiguration;
	
	@Before
	public void setUp() {
		repoManagemantController = new RepoManagemantController();
		testRepositoryConfiguration = new RepositoryConfiguration();
		testRepositoryConfiguration.setProjectName(TEST_REPOSITORY_NAME);
		testRepositoryConfiguration.setBackend(VersionControlSystem.GIT);
		testRepositoryConfiguration.setRemoteUri("test.uri");
	}
	
	@Test
	public void testGetRepository() throws URISyntaxException {
		ServerRepository serverRepository = repoManagemantController.getRepository(TEST_REPOSITORY_NAME);
		
		assertNull("Initially no repository is found", serverRepository);
		
		ServerRepository testServerRepository = new ServerRepository(new File(""), testRepositoryConfiguration);
		ServerRepository testServerRepositoryOther = new ServerRepository(new File(""), testRepositoryConfiguration);
		RepoRegistry.getInstance().addRepository(TEST_REPOSITORY_NAME, testServerRepository);
		RepoRegistry.getInstance().addRepository(TEST_REPOSITORY_NAME + "Other", testServerRepositoryOther);
		serverRepository = repoManagemantController.getRepository("testRepositoryName");
		
		assertEquals("Found the correct repository", testServerRepository, serverRepository);
	}

	@Test
	public void testAddNewRepository() throws URISyntaxException {
		repoManagemantController.addNewRepository(testRepositoryConfiguration);
		
		ServerRepository serverRepository = RepoRegistry.getInstance().getRepository(TEST_REPOSITORY_NAME);
		assertEquals("Correctly added the server repository", serverRepository.getRepositoryConfiguration(), testRepositoryConfiguration);
	}

	@Test
	public void testDeleteRepository() throws URISyntaxException {
		repoManagemantController.addNewRepository(testRepositoryConfiguration);
		
		ServerRepository serverRepository = RepoRegistry.getInstance().getRepository(TEST_REPOSITORY_NAME);
		assertNotNull("Initially the server repository exists", serverRepository);
		
		repoManagemantController.deleteRepository(TEST_REPOSITORY_NAME);
		
		serverRepository = RepoRegistry.getInstance().getRepository(TEST_REPOSITORY_NAME);
		assertNull("Correctly deleted the server repository", serverRepository);
	}
	
	@Test
	public void testUpdateRepository() throws URISyntaxException {
		repoManagemantController.addNewRepository(testRepositoryConfiguration);
		ServerRepository serverRepository = RepoRegistry.getInstance().getRepository(TEST_REPOSITORY_NAME);
		
		assertEquals("Initially the server repository is not updated", serverRepository.getRepositoryConfiguration().getRemoteUri(), testRepositoryConfiguration.getRemoteUri());
		
		RepositoryConfiguration repositoryConfigurationNew = new RepositoryConfiguration();
		repositoryConfigurationNew.setProjectName(TEST_REPOSITORY_NAME);
		repositoryConfigurationNew.setRemoteUri("new");
		repoManagemantController.updateRepository(repositoryConfigurationNew);
		
		assertEquals("Correctly caused an updated on the server repository", serverRepository.getRepositoryConfiguration().getRemoteUri(), repositoryConfigurationNew.getRemoteUri());
	}
}
