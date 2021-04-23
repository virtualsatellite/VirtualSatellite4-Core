/*******************************************************************************
 * Copyright (c) 2020 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.server.test;

import java.io.File;
import java.nio.file.Path;

import org.eclipse.jgit.api.Git;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

import de.dlr.sc.virsat.commons.file.VirSatFileUtils;
import de.dlr.sc.virsat.server.configuration.RepositoryConfiguration;
import de.dlr.sc.virsat.server.repository.RepoRegistry;
import de.dlr.sc.virsat.server.repository.ServerRepository;
import de.dlr.sc.virsat.team.VersionControlSystem;

public abstract class AServerRepositoryTest extends AJettyServerTest {

	protected static ServerRepository testServerRepository;
	protected static String projectName;
	protected static Path pathRepoRemote; 

	@BeforeClass
	public static void setUpClass() throws Exception {
		AJettyServerTest.setUpClass();
		
		pathRepoRemote = VirSatFileUtils.createAutoDeleteTempDirectory("VirtualSatelliteGitRemote_");
		File localRepoHome = VirSatFileUtils.createAutoDeleteTempDirectory("VirtualSatelliteLocalRepoHome_").toFile();
		File fileGitRemoteRepo = pathRepoRemote.toFile();
		Git.init().setDirectory(fileGitRemoteRepo).setBare(true).call();

		// Create a repository configuration
		projectName = "testProject";
		String uri = pathRepoRemote.toUri().toString();
		VersionControlSystem backend = VersionControlSystem.GIT;
		String username = "";
		String password = "";
		String localPath = projectName;

		RepositoryConfiguration testProjectConfiguration = new RepositoryConfiguration(projectName, localPath, uri, backend, username, password);

		testServerRepository = new ServerRepository(
			 localRepoHome, 
			 testProjectConfiguration);

		testServerRepository.checkoutRepository();
	}

	@Before
	public void setUp() throws Exception {
		// AGitAndJettyServerTest clears the RepoRegistry in the @After method so we have to create the mapping every time
		RepoRegistry.getInstance().addRepository(projectName, testServerRepository);
	}
	
	@AfterClass
	public static void teadDownClass() throws Exception {
		AJettyServerTest.tearDownClass();
		
		testServerRepository.removeRepository();
	}

}
