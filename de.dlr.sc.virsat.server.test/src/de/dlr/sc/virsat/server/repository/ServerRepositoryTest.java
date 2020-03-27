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

import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Status;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.revwalk.RevCommit;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import de.dlr.sc.virsat.project.structure.VirSatProjectCommons;
import de.dlr.sc.virsat.project.test.AProjectTestCase;
import de.dlr.sc.virsat.server.configuration.RepositoryConfiguration;
import de.dlr.sc.virsat.team.Activator;
import de.dlr.sc.virsat.team.VersionControlSystem;

public class ServerRepositoryTest extends AProjectTestCase {

	private RepositoryConfiguration testRepoConfig;
	private Path pathRepoRemote;
	private File localRepoHome;
	
	private static final String TEST_PROJECT_NAME = "LokalVirSatProject";
	
	@Before
	public void setUp() throws CoreException {
		super.setUp();
		try {
			pathRepoRemote = Files.createTempDirectory("VirtualSatelliteGitRemote_");
			localRepoHome = Files.createTempDirectory("VirtualSatelliteLocalRepoHome_").toFile();
			File fileGitRemoteRepo = pathRepoRemote.toFile();
			Git.init().setDirectory(fileGitRemoteRepo).setBare(true).call();
		} catch (IOException | IllegalStateException | GitAPIException e) {
			Activator.getDefault().getLog().log(new Status(Status.ERROR, Activator.getPluginId(),
					"Error during repository setup", e));
		}
		
		testRepoConfig = new RepositoryConfiguration(
				TEST_PROJECT_NAME,
				"",
				pathRepoRemote.toUri().toString(),
				VersionControlSystem.GIT,
				"",
				""
		); 
	}

	@After
	public void tearDown() throws CoreException {
		try {
			Files.walk(pathRepoRemote).sorted(Comparator.reverseOrder()).map(Path::toFile).forEach(File::delete);
			Files.walk(localRepoHome.toPath()).sorted(Comparator.reverseOrder()).map(Path::toFile).forEach(File::delete);
			ResourcesPlugin.getWorkspace().getRoot().getProject(TEST_PROJECT_NAME).delete(true,  true, null);
		} catch (IOException e) {
			Activator.getDefault().getLog().log(new Status(Status.ERROR, Activator.getPluginId(),
					"Error during temp remote directory creation", e));
		}
		super.tearDown();
	}

	@Test
	public void testRetrieveProjectFromConfiguration() throws Exception {
		ServerRepository testServerRepository = new ServerRepository(localRepoHome, testRepoConfig);
		
		testServerRepository.checkoutRepository();
		
		File localRepositoryFolder = testServerRepository.getLocalRepositoryPath();
		File localRepositoryGitFolder = new File(localRepositoryFolder, ".git/");
		
		assertTrue("Local Repository Got Checked out", localRepositoryGitFolder.exists());
		
		IProject checkedOutProject = ResourcesPlugin.getWorkspace().getRoot().getProject(testRepoConfig.getProjectName());
		assertTrue("The checked Out Project exists", checkedOutProject.exists());
	}
	
	@Test

	public void testRetrieveEdAndResurceSetFromConfiguration() throws URISyntaxException {
		ServerRepository testServerRepository = new ServerRepository(localRepoHome, testRepoConfig);
		
		assertNull("No project retrieved yet", testServerRepository.getProject());
		testServerRepository.retrieveProjectFromConfiguration();
		assertNotNull("Project Exists", testServerRepository.getProject());
	}
	
	@Test 
	public void testCreateVirSatProjectIfNeeded() throws CoreException, URISyntaxException {
		ServerRepository testServerRepository = new ServerRepository(localRepoHome, testRepoConfig);
		testServerRepository.retrieveProjectFromConfiguration();
		IProject createdProject = testServerRepository.getProject();
		
		assertFalse("Project is not yet created", createdProject.exists());
		createdProject.create(null);
		createdProject.open(null);
		
		assertFalse("Project is not yet a virsat project", VirSatProjectCommons.getAllVirSatProjects(ResourcesPlugin.getWorkspace()).contains(createdProject));
		
		testServerRepository.createVirSatProjectIfNeeded();
	
		assertTrue("Project is a virsat project now", VirSatProjectCommons.getAllVirSatProjects(ResourcesPlugin.getWorkspace()).contains(createdProject));
	}
	
	@Test
	public void testSyncProject() throws Exception {
		ServerRepository testServerRepository = new ServerRepository(localRepoHome, testRepoConfig);
		testServerRepository.checkoutRepository();

		IProject createdProject = testServerRepository.getProject();		
		assertTrue("Project is a virsat project now", VirSatProjectCommons.getAllVirSatProjects(ResourcesPlugin.getWorkspace()).contains(createdProject));

		testServerRepository.syncRepository();
		
		boolean logEntriesExist = Git.open(pathRepoRemote.toFile()).log().call().iterator().hasNext();
		assertTrue("There are logs now", logEntriesExist);
	
		RevCommit logAfterSync = Git.open(pathRepoRemote.toFile()).log().call().iterator().next();
		
		assertThat("remote repo does not yet have a commit as expected", logAfterSync.getFullMessage(), containsString(""));
	}
}
