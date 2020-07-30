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
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.net.URISyntaxException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import de.dlr.sc.virsat.server.configuration.RepositoryConfiguration;
import de.dlr.sc.virsat.server.repository.RepoRegistry;
import de.dlr.sc.virsat.server.repository.ServerRepository;
import de.dlr.sc.virsat.team.VersionControlSystem;

public class RepoManagementControllerTest {

	private static final String TEST_REPOSITORY_NAME = "testRepositoryName";
	
	private RepoManagementController repoManagemantController;
	private RepositoryConfiguration testRepositoryConfiguration;
	
	@Before
	public void setUp() {
		repoManagemantController = new RepoManagementController();
		testRepositoryConfiguration = new RepositoryConfiguration();
		testRepositoryConfiguration.setProjectName(TEST_REPOSITORY_NAME);
		testRepositoryConfiguration.setBackend(VersionControlSystem.GIT);
		testRepositoryConfiguration.setRemoteUri("test.uri");
	}
	
	@After
	public void tearDown() {
		RepoRegistry.getInstance().getRepositories().clear();
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
	
	@Test
	public void testGetAllProjectNames() throws URISyntaxException {
		assertTrue("No projects registered initially", repoManagemantController.getAllProjectNames().isEmpty());

		repoManagemantController.addNewRepository(testRepositoryConfiguration);

		assertEquals("One project registered", 1, repoManagemantController.getAllProjectNames().size());
		assertTrue("Correct project name", repoManagemantController.getAllProjectNames().contains(TEST_REPOSITORY_NAME));
		
		//Register second project
		final String PROJECT_NAME_2 = "Project2";
		testRepositoryConfiguration.setProjectName(PROJECT_NAME_2);
		repoManagemantController.addNewRepository(testRepositoryConfiguration);
		
		assertEquals("Two projects registered", 2, repoManagemantController.getAllProjectNames().size());
		assertTrue("Correct project name", repoManagemantController.getAllProjectNames().contains(TEST_REPOSITORY_NAME));
		assertTrue("Correct project name", repoManagemantController.getAllProjectNames().contains(PROJECT_NAME_2));
	}

	@Test
	public void testAddOrUpdateRepository() throws URISyntaxException {
		assertTrue(repoManagemantController.getAllProjectNames().isEmpty());
		repoManagemantController.addOrUpdateRepository(testRepositoryConfiguration);
		assertEquals("Project is added", 1, repoManagemantController.getAllProjectNames().size());

		final String newPassword = "naw password";
		testRepositoryConfiguration.setFunctionalAccountPassword(newPassword);
		repoManagemantController.addOrUpdateRepository(testRepositoryConfiguration);
		String updatedPassword = repoManagemantController.getRepository(TEST_REPOSITORY_NAME).getRepositoryConfiguration().getFunctionalAccountPassword();
		assertEquals("No project added", 1, repoManagemantController.getAllProjectNames().size());
		assertEquals("Project was updated", newPassword, updatedPassword);
	}
}
