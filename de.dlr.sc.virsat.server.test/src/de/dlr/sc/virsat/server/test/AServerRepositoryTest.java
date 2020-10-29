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
import java.io.IOException;
import java.nio.file.Path;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.jgit.api.Git;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

import de.dlr.sc.virsat.commons.file.VirSatFileUtils;
import de.dlr.sc.virsat.server.configuration.RepositoryConfiguration;
import de.dlr.sc.virsat.server.repository.RepoRegistry;
import de.dlr.sc.virsat.server.repository.ServerRepository;
import de.dlr.sc.virsat.team.VersionControlSystem;

public abstract class AServerRepositoryTest extends AGitAndJettyServerTest {

	protected static ServerRepository testServerRepository;
	protected static String projectName;
	protected static Path pathRepoRemote; 

	@BeforeClass
	public static void addServerRepository() throws Exception {
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
		String localPath = "";

		RepositoryConfiguration testProjectConfiguration = new RepositoryConfiguration(projectName, localPath, uri, backend, username, password);

		testServerRepository = new ServerRepository(
			 localRepoHome, 
			 testProjectConfiguration);

		testServerRepository.checkoutRepository();
	}

	@Before
	public void addRepoToRegistry() {
		// AGitAndJettyServerTest clears the RepoRegistry in the @After method so we have to create the mapping every time
		RepoRegistry.getInstance().addRepository(projectName, testServerRepository);
	}
	
	@AfterClass
	public static void cleanUpServerRepository() throws CoreException, IOException {
		testServerRepository.removeRepository();
	}

}
