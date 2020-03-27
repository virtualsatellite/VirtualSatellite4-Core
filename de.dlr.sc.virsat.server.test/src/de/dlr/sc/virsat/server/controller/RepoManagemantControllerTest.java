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
		testRepositoryConfiguration = new RepositoryConfiguration("", VersionControlSystem.GIT, "", "", TEST_REPOSITORY_NAME);
	}
	
	@Test
	public void testGetRepository() {
		ServerRepository serverRepository = repoManagemantController.getRepository(TEST_REPOSITORY_NAME);
		
		assertNull("Initially no repository is found", serverRepository);
		
		ServerRepository testServerRepository = new ServerRepository(testRepositoryConfiguration);
		ServerRepository testServerRepositoryOther = new ServerRepository(testRepositoryConfiguration);
		RepoRegistry.getInstance().addRepository(TEST_REPOSITORY_NAME, testServerRepository);
		RepoRegistry.getInstance().addRepository(TEST_REPOSITORY_NAME + "Other", testServerRepositoryOther);
		serverRepository = repoManagemantController.getRepository("testRepositoryName");
		
		assertEquals("Found the correct repository", testServerRepository, serverRepository);
	}

	@Test
	public void testAddNewRepository() {
		repoManagemantController.addNewRepository(testRepositoryConfiguration);
		
		ServerRepository serverRepository = RepoRegistry.getInstance().getRepository(TEST_REPOSITORY_NAME);
		assertEquals("Correctly added the server repository", serverRepository.getRepositoryConfiguration(), testRepositoryConfiguration);
	}

	@Test
	public void testDeleteRepository() {
		repoManagemantController.addNewRepository(testRepositoryConfiguration);
		
		ServerRepository serverRepository = RepoRegistry.getInstance().getRepository(TEST_REPOSITORY_NAME);
		assertNotNull("Initially the server repository exists", serverRepository);
		
		repoManagemantController.deleteRepository(TEST_REPOSITORY_NAME);
		
		serverRepository = RepoRegistry.getInstance().getRepository(TEST_REPOSITORY_NAME);
		assertNull("Correctly deleted the server repository", serverRepository);
	}
	
	@Test
	public void testUpdateRepository() {
		repoManagemantController.addNewRepository(testRepositoryConfiguration);
		ServerRepository serverRepository = RepoRegistry.getInstance().getRepository(TEST_REPOSITORY_NAME);
		
		assertEquals("Initially the server repository is not updated", serverRepository.getRepositoryConfiguration().getRemoteUri(), testRepositoryConfiguration.getRemoteUri());
		
		RepositoryConfiguration repositoryConfigurationNew = new RepositoryConfiguration("new", VersionControlSystem.GIT, "", "", TEST_REPOSITORY_NAME);
		repoManagemantController.updateRepository(repositoryConfigurationNew);
		
		assertEquals("Correctly caused an updated on the server repository", serverRepository.getRepositoryConfiguration().getRemoteUri(), repositoryConfigurationNew.getRemoteUri());
	}
}
