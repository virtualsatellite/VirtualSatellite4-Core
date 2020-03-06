/*******************************************************************************
 * Copyright (c) 2020 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.team.git;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;

import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralFactory;
import de.dlr.sc.virsat.project.test.AProjectTestCase;
import de.dlr.sc.virsat.team.Activator;

import org.eclipse.core.internal.resources.Resource;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.egit.core.GitProvider;
import org.eclipse.egit.core.internal.util.ResourceUtil;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.NoFilepatternException;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.team.core.RepositoryProvider;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.omg.PortableInterceptor.SUCCESSFUL;

public class VirSatGitVersionControlBackendTest extends AProjectTestCase {

	@Override
	protected IProject createTestProject(String projectName) throws CoreException {
		IProjectDescription projectDescription = ResourcesPlugin.getWorkspace().newProjectDescription(projectName);
		projectDescription.setLocationURI(pathGitRepoLocal1.toUri());
		return createTestProject(projectName, projectDescription, true);
	}
	
	/**
	 * This method is used to create a project on a given descriptor
	 * which is supposed to have a location into a git repository.
	 * The project creation waits until the Project is mapped as git Repository
	 * @param projectName Name of the project to be created
	 * @param projectDescription decriptor pointing to the git repository
	 * @return an iproject which is mapped to the git repository
	 * @throws CoreException
	 */
	@SuppressWarnings("restriction")
	protected IProject createTestProject(String projectName, IProjectDescription projectDescription, boolean waitForMapping) throws CoreException {
		IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject(projectName);
		testProjects.add(project);
		if (!project.exists()) {
			project.create(projectDescription, null);
			project.open(null);
			project.refreshLocal(Resource.DEPTH_INFINITE, null);
			Activator.getDefault().getLog().log(new Status(Status.INFO, Activator.getPluginId(), "Created new test project " +  project.getName()));
		}
		
		if (waitForMapping) {
			waitForProjectToGitRepoMapping(project);
		}
		return project;
	}

	private static final int WAIT_FOR_REPO_DETECTION_TIMESPAN = 10;
	
	@SuppressWarnings("restriction")
	protected void waitForProjectToGitRepoMapping(IProject project) {
		try {
			while (!ResourceUtil.isSharedWithGit(project)) {
				Thread.sleep(WAIT_FOR_REPO_DETECTION_TIMESPAN);
			}
		} catch (InterruptedException e) {
		}
	}
	
	private Path pathGitRepoRemote;
	private Path pathGitRepoLocal1;
	private Path pathGitRepoLocal2;
	private Repository gitRepoRemote;
	private Repository gitRepoLocal1;
	private Repository gitRepoLocal2;
	
	@Before
	public void setUp() throws CoreException  {
		try {
			pathGitRepoRemote = Files.createTempDirectory("VirtualSatelliteGitRemote_");
			File fileGitRemoteRepo = pathGitRepoRemote.toFile();
			fileGitRemoteRepo.mkdir();
			gitRepoRemote = Git.init()
					.setDirectory(fileGitRemoteRepo)
					.setBare(true)
					.call()
					.getRepository();
			
			URI uriToRemoteRepoPath = pathGitRepoRemote.toUri();
			pathGitRepoLocal1 = Files.createTempDirectory("VirtualSatelliteGitLocal1_");
			File filePathToProject = pathGitRepoLocal1.toFile();
			gitRepoLocal1 = Git.cloneRepository()
					.setURI(uriToRemoteRepoPath.toString())
					.setDirectory(filePathToProject)
					.call()
					.getRepository();

			uriToRemoteRepoPath = pathGitRepoRemote.toUri();
			pathGitRepoLocal2 = Files.createTempDirectory("VirtualSatelliteGitLocal2_");
			filePathToProject = pathGitRepoLocal2.toFile();
			gitRepoLocal2 = Git.cloneRepository()
					.setURI(uriToRemoteRepoPath.toString())
					.setDirectory(filePathToProject)
					.call()
					.getRepository();
		} catch (IOException | GitAPIException e) {
			Activator.getDefault().getLog().log(new Status(
					Status.ERROR,
					Activator.getPluginId(),
					"Error during temp remote directory creation",
					e
			));
		}
		
		super.setUp();
		
		addResourceSetAndRepository();
	}

	@After
	public void tearDown() throws CoreException {
		try {
			Files.delete(pathGitRepoRemote);
			Files.delete(pathGitRepoLocal1);
			Files.delete(pathGitRepoLocal2);
		} catch (IOException e) {
			Activator.getDefault().getLog().log(new Status(
					Status.ERROR,
					Activator.getPluginId(),
					"Error during temp remote directory creation",
					e
			));
		}
		super.tearDown();
	}

	@Test
	public void testCommit() throws Exception {
		// Create a new SEI on the file system
		StructuralElementInstance sei1 = StructuralFactory.eINSTANCE.createStructuralElementInstance();
		rs.getStructuralElementInstanceResource(sei1);
		
		IFile seiFile = projectCommons.getStructuralElementInstanceFile(sei1);
		
		final String COMMIT_MESSAGE = "Commit Message";
		
		// Commit repository and SEI files with VirtualSatellite Git Backend
		new VirSatGitVersionControlBackend(null).commit(testProject, COMMIT_MESSAGE, new NullProgressMonitor());

		// Get Commits on remote repo to check new commit has been received
		RevCommit remoteCommit = Git.wrap(gitRepoRemote).log().setMaxCount(1).call().iterator().next();
		assertEquals("Commit has correct message in remote repository", COMMIT_MESSAGE, remoteCommit.getFullMessage());

		// Checkout to local2 and see if SEI file has been transferred
		Git.wrap(gitRepoLocal2).pull().call();
		
		File seiInLocal2 = new File(pathGitRepoLocal2.toFile(), seiFile.getFullPath().removeFirstSegments(1).toOSString());
		
		assertTrue("File also exists in local2 after pull", seiInLocal2.exists());
	}

	@Test
	public void testUpdate() throws Exception {

	}

	@Test
	public void testCheckout() throws Exception {
		// prepare Repository in localRepo1 and commit the files
		StructuralElementInstance sei1 = StructuralFactory.eINSTANCE.createStructuralElementInstance();
		rs.getStructuralElementInstanceResource(sei1);
		IFile seiFile = projectCommons.getStructuralElementInstanceFile(sei1);
		new VirSatGitVersionControlBackend(null).commit(testProject, "Initial Commit", new NullProgressMonitor());
		
		// Now prepare the checkout into another project with another local repository
		String projectName = getProjectName() + "CheckOut";
		Path pathGitRepoCheckout = Files.createTempDirectory("VirtualSatelliteGitCheckOut_");
		IProjectDescription projectDescription = ResourcesPlugin.getWorkspace().newProjectDescription(projectName);
		projectDescription.setLocationURI(pathGitRepoCheckout.toUri());
		
		// Execute the checkout
		new VirSatGitVersionControlBackend(null).checkout(projectDescription, pathGitRepoRemote.toUri().toString(), new NullProgressMonitor());

		// Create the project and wait until it is mapped with the Git Providers
		IProject projectCheckout = createTestProject(projectName, projectDescription, true);
		
		// Now check that the SEI has been well checked out in the project and on the file system
		File seiInLocalCheckout = new File(pathGitRepoCheckout.toFile(), seiFile.getFullPath().removeFirstSegments(1).toOSString());
		assertTrue("File also exists in local2 after pull", seiInLocalCheckout.exists());
	
		IFile seiInLocalCheckoutWorkspace = projectCheckout.getFile(seiFile.getFullPath().removeFirstSegments(1));
		assertTrue("File also exists in workspace", seiInLocalCheckoutWorkspace.exists());
	}

	@Test
	public void testCheckin() {
		
	}

}
