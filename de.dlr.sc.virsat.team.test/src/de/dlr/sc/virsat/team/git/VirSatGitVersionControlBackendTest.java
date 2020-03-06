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

import static org.junit.Assert.assertTrue;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;

import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralFactory;
import de.dlr.sc.virsat.project.resources.VirSatResourceSet;
import de.dlr.sc.virsat.project.test.AProjectTestCase;
import de.dlr.sc.virsat.team.Activator;
import de.dlr.sc.virsat.team.IVirSatVersionControlBackend;

import org.eclipse.core.internal.resources.Resource;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.egit.core.internal.util.ResourceUtil;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

@SuppressWarnings("restriction")
public class VirSatGitVersionControlBackendTest extends AProjectTestCase {
	
	protected IProject createTestProject(String projectName, URI fsProjectLocation, boolean waitForMapping) throws CoreException {
		IProjectDescription projectDescription = ResourcesPlugin.getWorkspace().newProjectDescription(projectName);
		projectDescription.setLocationURI(fsProjectLocation);
		return createTestProject(projectName, projectDescription, waitForMapping);
	}
		
	/**
	 * This method is used to create a project on a given descriptor
	 * which is supposed to have a location into a Git repository.
	 * The project creation waits until the Project is mapped as Git Repository
	 * @param projectName Name of the project to be created
	 * @param projectDescription descriptor pointing to the Git repository
	 * @return an IProject which is mapped to the Git repository
	 * @throws CoreException
	 */
	protected IProject createTestProject(String projectName, IProjectDescription projectDescription, boolean waitForMapping) throws CoreException {
		IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject(projectName);
		testProjects.add(project);
		if (!project.exists()) {
			project.create(projectDescription, null);
			project.open(null);
			Activator.getDefault().getLog().log(new Status(Status.INFO, Activator.getPluginId(), "Created new test project " +  project.getName()));
		}
		
		if (waitForMapping) {
			waitForProjectToGitRepoMapping(project);
		}
		return project;
	}

	private static final int WAIT_FOR_REPO_DETECTION_TIMESPAN = 10;
	
	@SuppressWarnings("restriction")
	protected void waitForProjectToGitRepoMapping(IProject project) throws CoreException {
		project.refreshLocal(Resource.DEPTH_INFINITE, null);
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
	
	private IProject projectRepoLocal1;
	private IProject projectRepoLocal2;
	
	private IVirSatVersionControlBackend backend;
	
	@Before
	public void setUp() throws CoreException  {
		try {
			pathGitRepoRemote = Files.createTempDirectory("VirtualSatelliteGitRemote_");
			File fileGitRemoteRepo = pathGitRepoRemote.toFile();
			fileGitRemoteRepo.mkdir();
			Git.init()
				.setDirectory(fileGitRemoteRepo)
				.setBare(true)
				.call();
			
			URI uriToRemoteRepoPath = pathGitRepoRemote.toUri();
			pathGitRepoLocal1 = Files.createTempDirectory("VirtualSatelliteGitLocal1_");
			File filePathToProject = pathGitRepoLocal1.toFile();
			Git.cloneRepository()
				.setURI(uriToRemoteRepoPath.toString())
				.setDirectory(filePathToProject)
				.call();

			uriToRemoteRepoPath = pathGitRepoRemote.toUri();
			pathGitRepoLocal2 = Files.createTempDirectory("VirtualSatelliteGitLocal2_");
			filePathToProject = pathGitRepoLocal2.toFile();
			Git.cloneRepository()
				.setURI(uriToRemoteRepoPath.toString())
				.setDirectory(filePathToProject)
				.call();
		} catch (IOException | GitAPIException e) {
			Activator.getDefault().getLog().log(new Status(
					Status.ERROR,
					Activator.getPluginId(),
					"Error during temp remote directory creation",
					e
			));
		}
		
		super.setUp();
		
		projectRepoLocal1 = createTestProject("VirSatRepoLocal1", pathGitRepoLocal1.toUri(), true);
		projectRepoLocal2 = createTestProject("VirSatRepoLocal2", pathGitRepoLocal2.toUri(), true);
		
		backend = new VirSatGitVersionControlBackend(null);
	}
	
	/**
	 * This method creates an unmanaged ResourceSet which is non transactional.
	 * The method also creates a repository
	 */
	protected void addResourceSetAndRepository(IProject project) {
		rs = VirSatResourceSet.createUnmanagedResourceSet(project);
		rs.initializeModelsAndResourceSet();
		repository = rs.getRepository();
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
	public void testSimpleCommitAndUpdate() throws Exception {
		
		// Create a new SEI on the file system
		addResourceSetAndRepository(projectRepoLocal1);
		StructuralElementInstance sei1 = StructuralFactory.eINSTANCE.createStructuralElementInstance();
		rs.getStructuralElementInstanceResource(sei1);
		
		IFile seiFile = projectCommons.getStructuralElementInstanceFile(sei1);
		
		// Commit repository and SEI files with VirtualSatellite Git Backend
		backend.commit(projectRepoLocal1, "First Simple Commit", new NullProgressMonitor());

		// Checkout to local2 and see if SEI file has been transferred
		backend.update(projectRepoLocal2, new NullProgressMonitor());
		
		File seiInLocal2 = new File(pathGitRepoLocal2.toFile(), seiFile.getFullPath().removeFirstSegments(1).toOSString());
		assertTrue("File also exists in local2 after pull", seiInLocal2.exists());
	}

	@Test
	public void testCheckout() throws Exception {
		// prepare Repository in localRepo1 and commit the files
		addResourceSetAndRepository(projectRepoLocal1);
		StructuralElementInstance sei1 = StructuralFactory.eINSTANCE.createStructuralElementInstance();
		rs.getStructuralElementInstanceResource(sei1);
		IFile seiFile = projectCommons.getStructuralElementInstanceFile(sei1);
		backend.commit(projectRepoLocal1, "Initial Commit", new NullProgressMonitor());
		
		// Now prepare the checkout into another project with another local repository
		Path pathGitRepoCheckout = Files.createTempDirectory("VirtualSatelliteGitCheckOut_");
		IProjectDescription projectDescription = ResourcesPlugin.getWorkspace().newProjectDescription("VirSatRepoLocalCheckout");
		projectDescription.setLocationURI(pathGitRepoCheckout.toUri());
		
		// Execute the checkout
		backend.checkout(projectDescription, pathGitRepoRemote.toUri().toString(), new NullProgressMonitor());

		// Create the project and wait until it is mapped with the Git Providers
		IProject projectCheckout = createTestProject("VirSatRepoLocalCheckout", projectDescription, true);
		
		// Now check that the SEI has been well checked out in the project and on the file system
		File seiInLocalCheckout = new File(pathGitRepoCheckout.toFile(), seiFile.getFullPath().removeFirstSegments(1).toOSString());
		assertTrue("File also exists in local2 after pull", seiInLocalCheckout.exists());
	
		IFile seiInLocalCheckoutWorkspace = projectCheckout.getFile(seiFile.getFullPath().removeFirstSegments(1));
		assertTrue("File also exists in workspace", seiInLocalCheckoutWorkspace.exists());
	}

	@Test
	public void testCheckin() throws Exception {
		// Create a new full fledged Repository and try to check it into a Git Repository
		Path pathGitRepoCheckin = Files.createTempDirectory("VirtualSatelliteGitCheckIn_");
		IProject projectCheckin = createTestProject("VirSatRepoCheckin", pathGitRepoCheckin.toUri(), false);
		addResourceSetAndRepository(projectCheckin);
		StructuralElementInstance sei1 = StructuralFactory.eINSTANCE.createStructuralElementInstance();
		rs.getStructuralElementInstanceResource(sei1);
		IFile seiFile = projectCommons.getStructuralElementInstanceFile(sei1);
		
		// Now checkin the project and transmit it to the remote bare repository
		backend.checkin(projectCheckin, pathGitRepoRemote.toUri().toString(), new NullProgressMonitor());
		waitForProjectToGitRepoMapping(projectCheckin);
		backend.commit(projectCheckin, "Initial Commit", new NullProgressMonitor());
		
		// Now update the project as Repo Local1
		backend.update(projectRepoLocal1, new NullProgressMonitor());
		
		// And check if the file is present
		File seiInLocalRepo1 = new File(pathGitRepoLocal1.toFile(), seiFile.getFullPath().removeFirstSegments(1).toOSString());
		assertTrue("File also exists in local1 after update", seiInLocalRepo1.exists());
	
		projectRepoLocal1.refreshLocal(Resource.DEPTH_INFINITE, null);
		IFile seiInLocalRepo1Workspace = projectRepoLocal1.getFile(seiFile.getFullPath().removeFirstSegments(1));
		assertTrue("File also exists in workspace", seiInLocalRepo1Workspace.exists());
	}
}
