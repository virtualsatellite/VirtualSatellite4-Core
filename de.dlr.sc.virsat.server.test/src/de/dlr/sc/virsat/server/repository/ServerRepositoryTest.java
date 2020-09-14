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

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Status;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.revwalk.RevCommit;
import org.junit.Before;
import org.junit.Test;

import de.dlr.sc.virsat.commons.file.VirSatFileUtils;
import de.dlr.sc.virsat.project.structure.VirSatProjectCommons;
import de.dlr.sc.virsat.project.test.AProjectTestCase;
import de.dlr.sc.virsat.server.configuration.RepositoryConfiguration;
import de.dlr.sc.virsat.team.Activator;
import de.dlr.sc.virsat.team.VersionControlSystem;
import de.dlr.sc.virsat.team.git.VirSatGitVersionControlBackend;

public class ServerRepositoryTest extends AProjectTestCase {

	private RepositoryConfiguration testRepoConfig;
	private Path pathRepoRemote;
	private File localRepoHome;
	
	private static final String TEST_PROJECT_NAME = "LokalVirSatProject";
	
	@Before
	public void setUp() throws CoreException {
		super.setUp();
		try {
			pathRepoRemote = VirSatFileUtils.createAutoDeleteTempDirectory("VirtualSatelliteGitRemote_");
			localRepoHome = VirSatFileUtils.createAutoDeleteTempDirectory("VirtualSatelliteLocalRepoHome_").toFile();
			File fileGitRemoteRepo = pathRepoRemote.toFile();
			Git.init().setDirectory(fileGitRemoteRepo).setBare(true).call();
		} catch (IOException | IllegalStateException | GitAPIException e) {
			Activator.getDefault().getLog().log(
				new Status(
					Status.ERROR,
					Activator.getPluginId(),
					"Error during repository setup",
					e
				)
			);
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

	@Test
	public void testCheckoutRepository() throws Exception {
		ServerRepository testServerRepository = new ServerRepository(localRepoHome, testRepoConfig);
		
		File localRepositoryFolder = testServerRepository.getLocalRepositoryPath();
		File localRepositoryGitFolder = new File(localRepositoryFolder, ".git/");
		
		assertTrue("Local Repository folder exists", localRepositoryFolder.exists());

		testServerRepository.checkoutRepository();
		
		assertTrue("Local Repository Got Checked out", localRepositoryGitFolder.exists());
		
		IProject checkedOutProject = ResourcesPlugin.getWorkspace().getRoot().getProject(testRepoConfig.getProjectName());
		assertTrue("The checked Out Project exists", checkedOutProject.exists());
	}
	
	@Test
	public void testCheckoutTwoProjectsFromOneRepository() throws Exception {
		// Create two configurations with two Projects but both in their specific subfolder
		// and the same remote repository
		final String TEST_PROJECT_A = "testProjectA";
		final String TEST_PROJECT_B = "testProjectB";
		RepositoryConfiguration testRepoConfigA = testRepoConfig;
		RepositoryConfiguration testRepoConfigB = new RepositoryConfiguration(testRepoConfig);
		testRepoConfigA.setProjectName(TEST_PROJECT_A);
		testRepoConfigB.setProjectName(TEST_PROJECT_B);
		testRepoConfigA.setLocalPath(TEST_PROJECT_A);
		testRepoConfigB.setLocalPath(TEST_PROJECT_B);
	
		ServerRepository testServerRepositoryA = new ServerRepository(localRepoHome, testRepoConfigA);
		ServerRepository testServerRepositoryB = new ServerRepository(localRepoHome, testRepoConfigB);
		
		testServerRepositoryA.checkoutRepository();
		
		// Make sure there is no .git file in the repo local home
		assertFalse("there is no .git in repo home", new File(localRepoHome, ".git/").exists());
		
		// make sure that the parent to the project folder has the .git
		File testProjectPathA = testServerRepositoryA.getProject().getRawLocation().toFile();
		File testProjectRepoA = testProjectPathA.getParentFile();
		assertFalse("there is no .git in project", new File(testProjectPathA, ".git/").exists());
		assertTrue("there is .git in repo", new File(testProjectRepoA, ".git/").exists());
		
		// Checkin projectA to make sure there are two projects when actually checking out B
		testServerRepositoryA.syncRepository();
		
		testServerRepositoryB.checkoutRepository();
		File testProjectPathB = testServerRepositoryB.getProject().getRawLocation().toFile();
		File testProjectRepoB = testProjectPathB.getParentFile();
		assertNotEquals("Path of Project A is different from Project B", testProjectPathA, testProjectPathB);
		assertFalse("there is no .git in project", new File(testProjectPathB, ".git/").exists());
		assertTrue("there is .git in repo", new File(testProjectRepoB, ".git/").exists());
		
		// Files of ProjectA will also exist in repo of ProjectB
		assertTrue("ProjectA got checked out in ProjectB", new File(testProjectRepoB, TEST_PROJECT_A).exists());
		
		// now sync project B to send changes to the server, check that they are not yet in A, sync a and show project B appears there
		testServerRepositoryB.syncRepository();
		assertFalse("ProjectB is not yet in ProjectA", new File(testProjectRepoA, TEST_PROJECT_B).exists());
		
		testServerRepositoryA.syncRepository();
		assertTrue("ProjectB is now in ProjectA", new File(testProjectRepoA, TEST_PROJECT_B).exists());
	}
	
	@Test
	public void testRetrieveEdAndResurceSetFromConfiguration() throws URISyntaxException, CoreException {
		ServerRepository testServerRepository = new ServerRepository(localRepoHome, testRepoConfig);
		
		assertNull("No project retrieved yet", testServerRepository.getProject());
		testServerRepository.retrieveProjectFromConfiguration();
		assertNotNull("Project Exists", testServerRepository.getProject());
		
		testServerRepository.retrieveEdAndResurceSetFromConfiguration();
		
		assertNotNull("EditingDomain got set", testServerRepository.getEd());
		assertNotNull("ResourceSet got set", testServerRepository.getResourceSet());
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
		
		assertThat("Commit before pull message expected", logAfterSync.getFullMessage(),
				containsString(VirSatGitVersionControlBackend.BACKEND_REPOSITORY_COMMIT_PULL_MESSAGE));
	}
	
	@Test
	public void testUpdateOrCheckoutProject() throws Exception {
		ServerRepository testServerRepository = new ServerRepository(localRepoHome, testRepoConfig);
		testServerRepository.updateOrCheckoutProject();

		IProject createdProject = testServerRepository.getProject();		
		assertTrue("Project is a virsat project now", VirSatProjectCommons.getAllVirSatProjects(ResourcesPlugin.getWorkspace()).contains(createdProject));
		assertNotNull("Project has an ed", testServerRepository.getEd());
		assertNotNull("Project has a resource set", testServerRepository.getResourceSet());
		assertNotNull("Project has a repository", testServerRepository.getResourceSet().getRepository());

		ArrayList<RevCommit> commitList1 = StreamSupport
			.stream(
				Git.open(pathRepoRemote.toFile()).log().call().spliterator(),
				false
			).collect(Collectors.toCollection(() -> new ArrayList<>()));
		
		assertThat("Commit List has expected size", commitList1, hasSize(1));
		
		// Add another file to the project when reconnecting, this file should create a new commit on the repository
		testServerRepository.getProject().getFile("newTempFile.txt").create(new ByteArrayInputStream("test".getBytes()), true, null);
		
		// now call the method a second time, the project is already created thus it should only do the update
		ServerRepository testServerRepository2 = new ServerRepository(localRepoHome, testRepoConfig);
		testServerRepository2.updateOrCheckoutProject();
		
		assertTrue("Project is a virsat project now", VirSatProjectCommons.getAllVirSatProjects(ResourcesPlugin.getWorkspace()).contains(createdProject));
		assertNotNull("Project has an ed", testServerRepository2.getEd());
		assertNotNull("Project has a resource set", testServerRepository2.getResourceSet());
		assertNotNull("Project has a repository", testServerRepository2.getResourceSet().getRepository());

		ArrayList<RevCommit> commitList2 = StreamSupport
			.stream(
				Git.open(pathRepoRemote.toFile()).log().call().spliterator(),
				false
			).collect(Collectors.toCollection(() -> new ArrayList<>()));
		
		// CHECKSTYLE:OFF
		assertThat("Commit List has expected size", commitList2, hasSize(2));
		// CHECKSTYLE:ON
	}
	
	@Test
	public void testRemoveProject() throws Exception {
		ServerRepository testServerRepository = new ServerRepository(localRepoHome, testRepoConfig);
		
		File localRepositoryFolder = testServerRepository.getLocalRepositoryPath();
		assertTrue("Local Repository folder exists", localRepositoryFolder.exists());

		testServerRepository.checkoutRepository();
		testServerRepository.removeRepository();

		assertFalse("Local Repository folder got deleted", localRepositoryFolder.exists());
	}
}
