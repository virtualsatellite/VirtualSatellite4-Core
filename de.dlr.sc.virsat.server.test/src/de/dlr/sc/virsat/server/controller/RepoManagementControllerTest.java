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
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.eclipse.core.runtime.CoreException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import de.dlr.sc.virsat.commons.file.VirSatFileUtils;
import de.dlr.sc.virsat.server.configuration.RepositoryConfiguration;
import de.dlr.sc.virsat.server.configuration.ServerConfiguration;
import de.dlr.sc.virsat.server.repository.RepoRegistry;
import de.dlr.sc.virsat.server.repository.ServerRepository;
import de.dlr.sc.virsat.team.VersionControlSystem;

public class RepoManagementControllerTest {

	private static final String TEST_REPOSITORY_NAME = "testRepositoryName";
	
	private RepoManagementController repoManagemantController;
	private RepositoryConfiguration testRepositoryConfiguration;
	private Path configFilePath;
	
	@Before
	public void setUp() throws IOException {
		// Create temporary dir for config files
		Path configsDir = VirSatFileUtils.createAutoDeleteTempDirectory("test_repo_configs");
		Path projectsDir = VirSatFileUtils.createAutoDeleteTempDirectory("test_project");
		
		// Overwrite path to config files
		ServerConfiguration.setRepositoryConfigurationsDir(configsDir.toString());
		ServerConfiguration.setProjectRepositoriesDir(projectsDir.toString());
		
		repoManagemantController = new RepoManagementController();
		testRepositoryConfiguration = new RepositoryConfiguration();
		testRepositoryConfiguration.setProjectName(TEST_REPOSITORY_NAME);
		testRepositoryConfiguration.setBackend(VersionControlSystem.GIT);
		testRepositoryConfiguration.setRemoteUri("test.uri");
		
		String expectedFileName = testRepositoryConfiguration.getProjectName() + ".properties";
		configFilePath = configsDir.resolve(expectedFileName);
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
	public void testAddNewRepository() throws URISyntaxException, IOException {
		repoManagemantController.addNewRepository(testRepositoryConfiguration);
		
		ServerRepository serverRepository = RepoRegistry.getInstance().getRepository(TEST_REPOSITORY_NAME);
		assertEquals("Correctly added the server repository", serverRepository.getRepositoryConfiguration(), testRepositoryConfiguration);
		assertTrue("Config file created", Files.exists(configFilePath));
	}

	@Test
	public void testDeleteRepository() throws URISyntaxException, IOException {
		repoManagemantController.addNewRepository(testRepositoryConfiguration);
		
		ServerRepository serverRepository = RepoRegistry.getInstance().getRepository(TEST_REPOSITORY_NAME);
		assertNotNull("Initially the server repository exists", serverRepository);
		assertTrue("Config file exists", Files.exists(configFilePath));
		
		repoManagemantController.deleteRepository(TEST_REPOSITORY_NAME);
		
		serverRepository = RepoRegistry.getInstance().getRepository(TEST_REPOSITORY_NAME);
		assertNull("Correctly deleted the server repository", serverRepository);
		assertFalse("Config file deleted", Files.exists(configFilePath));
	}
	
	@Test
	public void testUpdateRepository() throws URISyntaxException, IOException, CoreException {
		repoManagemantController.addNewRepository(testRepositoryConfiguration);
		ServerRepository serverRepository = RepoRegistry.getInstance().getRepository(TEST_REPOSITORY_NAME);
		
		final String NEW_URI = "new";
		final String ACC_NAME = "name";
		final String ACC_PW = "secure";
		
		assertNotEquals("Initially the server repository is not updated", serverRepository.getRepositoryConfiguration().getRemoteUri(), NEW_URI);
		assertNotEquals("Initially the server repository is not updated", serverRepository.getRepositoryConfiguration().getFunctionalAccountName(), ACC_NAME);
		assertNotEquals("Initially the server repository is not updated", serverRepository.getRepositoryConfiguration().getFunctionalAccountPassword(), ACC_PW);
		
		RepositoryConfiguration repositoryConfigurationNew = new RepositoryConfiguration();
		repositoryConfigurationNew.setProjectName(TEST_REPOSITORY_NAME);
		repositoryConfigurationNew.setRemoteUri(NEW_URI);
		repositoryConfigurationNew.setFunctionalAccountName(ACC_NAME);
		repositoryConfigurationNew.setFunctionalAccountPassword(ACC_PW);
		repoManagemantController.updateRepository(repositoryConfigurationNew);
		
		assertEquals("Correctly caused an update on the server repository", serverRepository.getRepositoryConfiguration().getRemoteUri(), NEW_URI);
		assertEquals("Correctly caused an update on the server repository", serverRepository.getRepositoryConfiguration().getFunctionalAccountName(), ACC_NAME);
		assertEquals("Correctly caused an update on the server repository", serverRepository.getRepositoryConfiguration().getFunctionalAccountPassword(), ACC_PW);
	}
	
	@Test
	public void testGetAllProjectNames() throws URISyntaxException, IOException {
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
	public void testAddOrUpdateRepository() throws URISyntaxException, IOException, CoreException {
		assertTrue(repoManagemantController.getAllProjectNames().isEmpty());
		repoManagemantController.addOrUpdateRepository(testRepositoryConfiguration);
		assertEquals("Project is added", 1, repoManagemantController.getAllProjectNames().size());

		final String newPassword = "new password";
		testRepositoryConfiguration.setFunctionalAccountPassword(newPassword);
		repoManagemantController.addOrUpdateRepository(testRepositoryConfiguration);
		String updatedPassword = repoManagemantController.getRepository(TEST_REPOSITORY_NAME).getRepositoryConfiguration().getFunctionalAccountPassword();
		assertEquals("No project added", 1, repoManagemantController.getAllProjectNames().size());
		assertEquals("Project was updated", newPassword, updatedPassword);
	}
}
