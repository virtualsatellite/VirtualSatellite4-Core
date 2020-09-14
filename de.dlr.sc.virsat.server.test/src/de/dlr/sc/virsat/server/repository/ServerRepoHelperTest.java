/*******************************************************************************
 * Copyright (c) 2020 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.server.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.team.svn.core.utility.SVNUtility;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import de.dlr.sc.virsat.commons.file.VirSatFileUtils;
import de.dlr.sc.virsat.server.configuration.RepositoryConfiguration;
import de.dlr.sc.virsat.server.configuration.ServerConfiguration;
import de.dlr.sc.virsat.team.VersionControlSystem;
import de.dlr.sc.virsat.team.test.CreateSvnServerOperation;

public class ServerRepoHelperTest {

	private Path configsDir;
	private Path svnPathRepoRemote;
	private URI svnUriToRemoteRepoPath;
	private static final String PROJECT_NAME = "testProject";
	private static final String URI = "test.uri";
	private Path configFilePath;
	private RepositoryConfiguration config;
	
	@Before
	public void setUp() throws IOException, CoreException {
		// Create temporary dir for repo config files
		configsDir = VirSatFileUtils.createAutoDeleteTempDirectory("test_repo_configs");
		
		// Overwrite path to repo config files
		ServerConfiguration.setRepositoryConfigurationsDir(configsDir.toString());

		RepoRegistry.getInstance().getRepositories().clear();
		
		svnPathRepoRemote = VirSatFileUtils.createAutoDeleteTempDirectory("VirtualSatelliteSvnRemote_");
		svnUriToRemoteRepoPath = svnPathRepoRemote.toUri();
		String remoteRepoFilePath = svnPathRepoRemote.toString();
		
		SVNUtility.asRepositoryResource(svnUriToRemoteRepoPath.toString(), true);
		
		CreateSvnServerOperation createRemoteRepoOp = new CreateSvnServerOperation(remoteRepoFilePath);
		createRemoteRepoOp.runWithExceptionChecking(new NullProgressMonitor());
		
		config = new RepositoryConfiguration();
		config.setProjectName(PROJECT_NAME);
		config.setRemoteUri(URI);
		config.setBackend(VersionControlSystem.GIT);
		
		String expectedFileName = PROJECT_NAME + ".properties";
		configFilePath = configsDir.resolve(expectedFileName);
	}
	
	@After
	public void tearDown() throws IOException {
		RepoRegistry.getInstance().getRepositories().clear();
	}

	@Test
	public void testLoadRepositoryConfigurations() throws IOException {
		String svnProjectName = "SvnProject1";
		String gitProjectName = "GitProject2";
		createTempRepoConfigFile(configsDir, svnProjectName, VersionControlSystem.SVN);
		createTempRepoConfigFile(configsDir, gitProjectName, VersionControlSystem.GIT);

		RepoRegistry repoRegistry = RepoRegistry.getInstance();
		assertTrue("Repo registry is empty initially", repoRegistry.getRepositories().isEmpty());

		ServerRepoHelper.initRepoRegistry();
		
		assertEquals(2, repoRegistry.getRepositories().size());
		assertEquals(VersionControlSystem.SVN, repoRegistry.getRepository(svnProjectName).getRepositoryConfiguration().getBackend());
		assertEquals(VersionControlSystem.GIT, repoRegistry.getRepository(gitProjectName).getRepositoryConfiguration().getBackend());
	}
	
	@Test
	public void testSaveConfiguration() throws FileNotFoundException, IOException, URISyntaxException {
		assertFalse("Config file doesn't exist initially", Files.exists(configFilePath));

		ServerRepoHelper.saveRepositoryConfiguration(config);
		assertTrue("Config file created after saving", Files.exists(configFilePath));
		
		RepositoryConfiguration loadedConfig = new RepositoryConfiguration(Files.newInputStream(configFilePath));
		assertEquals("Saved file contains correct name", PROJECT_NAME, loadedConfig.getProjectName());
		assertEquals("Saved file contains correct URI", URI, loadedConfig.getRemoteUri());
		
		// Check overwriting
		String newUri = "new.test.uri";
		config.setRemoteUri(newUri);
		ServerRepoHelper.saveRepositoryConfiguration(config);
		loadedConfig = new RepositoryConfiguration(Files.newInputStream(configFilePath));
		assertEquals("Saved file contains correct name", PROJECT_NAME, loadedConfig.getProjectName());
		assertEquals("Saved file contains new URI", newUri, loadedConfig.getRemoteUri());
	}
	
	@Test
	public void testDeleteRepositoryConfiguration() throws IOException, URISyntaxException {
		createTempRepoConfigFile(configsDir, PROJECT_NAME, VersionControlSystem.GIT);

		ServerRepoHelper.initRepoRegistry();
		ServerRepoHelper.saveRepositoryConfiguration(config);
		assertTrue("Config file exists", Files.exists(configFilePath));
		assertFalse("Config registered", RepoRegistry.getInstance().getRepositories().isEmpty());
		
		ServerRepoHelper.deleteRepositoryConfiguration(PROJECT_NAME);
		assertFalse("Config file deleted", Files.exists(configFilePath));
		assertTrue("Config not registered", RepoRegistry.getInstance().getRepositories().isEmpty());
	}
	
	@Test
	public void testUpdateRepositoryConfiguration() throws IOException, URISyntaxException, CoreException {		
		ServerRepoHelper.registerRepositoryConfiguration(config);
		
		ServerRepository repo = RepoRegistry.getInstance().getRepository(PROJECT_NAME);
		assertEquals(VersionControlSystem.GIT, repo.getRepositoryConfiguration().getBackend());
		
		config.setBackend(VersionControlSystem.SVN);
		ServerRepoHelper.updateRepositoryConfiguration(config);
		
		assertEquals("Updated successful", VersionControlSystem.SVN, repo.getRepositoryConfiguration().getBackend());
	}
	
	/**
	 * Not creating all fields since we are not testing individual RepositoryConfiguration serialization here
	 * @throws IOException 
	 */
	private void createTempRepoConfigFile(Path parentDir, String projectName, VersionControlSystem backend) throws IOException {
		String fileContents = RepositoryConfiguration.PROJECT_NAME_KEY + ":" + projectName + System.lineSeparator()
				+ RepositoryConfiguration.BACKEND_KEY + ":" + backend + System.lineSeparator()
				+ RepositoryConfiguration.REMOTE_URL_KEY + ":" + svnUriToRemoteRepoPath.toString();
		Path tempFile = Files.createTempFile(parentDir, projectName, ".properties");
		Files.write(tempFile, fileContents.getBytes());
	}
}
