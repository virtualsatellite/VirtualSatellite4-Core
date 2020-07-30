/*******************************************************************************
 * Copyright (c) 2020 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.team;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.net.URI;
import java.nio.file.Path;

import org.eclipse.core.internal.resources.Resource;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.egit.core.project.RepositoryMapping;
import org.junit.Before;
import org.junit.Test;

import de.dlr.sc.virsat.commons.file.VirSatFileUtils;
import de.dlr.sc.virsat.model.dvlm.roles.UserRegistry;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralFactory;
import de.dlr.sc.virsat.model.dvlm.types.impl.VirSatUuid;
import de.dlr.sc.virsat.project.resources.VirSatResourceSet;
import de.dlr.sc.virsat.project.structure.VirSatProjectCommons;
import de.dlr.sc.virsat.project.test.AProjectTestCase;

@SuppressWarnings("restriction")
public abstract class AVirSatVersionControlBackendTest extends AProjectTestCase {

	private static final String PROJECT_CHECKIN_NAME = "VirSatRepoCheckin";
	private static final String PROJECT_LOCAL_NAME = "VirSatRepoLocal";
	
	protected Path pathRepoRemote;
	protected Path pathRepoLocal1;
	protected Path pathRepoLocal2;

	protected IProject projectRepoLocal1;

	protected IVirSatVersionControlBackend backend;
	
	public static final int ASSERT_RETRY_COUNT = 4;
	public static final int ASSERT_RETRY_TIME = 250;
	
	/**
	 * This method is used to create a project 
	 * in a sub folder of a repository at given file system location.
	 * The project creation waits until the Project is mapped as Repository.
	 * 
	 * @param projectName    name of the project to be created
	 * @param fsRepoLocation Path of the repository in the file system
	 * @return an IProject which is mapped to the repository
	 * @throws CoreException
	 */
	protected IProject createTestProject(String projectName, Path fsRepoLocation, boolean waitForMapping) throws CoreException {
		IProjectDescription projectDescription = ResourcesPlugin.getWorkspace().newProjectDescription(projectName);
		projectDescription.setLocationURI(fsRepoLocation.toUri());
		return createTestProject(projectName, projectDescription, waitForMapping);
	}

	/**
	 * This method is used to create a project 
	 * in a sub folder of a repository at the location
	 * specified by the given project descriptor.
	 * The project creation waits until the Project is mapped as Repository.
	 * 
	 * @param projectName        name of the project to be created
	 * @param projectDescription descriptor pointing to the repository
	 * @return an IProject which is mapped to the repository
	 * @throws CoreException
	 */
	protected IProject createTestProject(String projectName, IProjectDescription projectDescription, boolean waitForMapping) throws CoreException {
		// Create a new sub folder with the project name in the repository
		URI uriProjectLocal = projectDescription.getLocationURI().resolve(projectDescription.getName());
		projectDescription.setLocationURI(uriProjectLocal);
		
		// If the project does not exists already, create it
		IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject(projectName);
		testProjects.add(project);
		if (!project.exists()) {
			project.create(projectDescription, null);
			project.open(null);
			Activator.getDefault().getLog().log(
					new Status(Status.INFO, Activator.getPluginId(), "Created new test project " + project.getName()));
		}

		// Wait for the mapping to complete
		if (waitForMapping) {
			waitForProjectToRepoMapping(project);
		}
		return project;
	}
	
	/**
	 * Method to ensure the project is mapped to the repository.
	 * Can be overwritten by the derived classes for specific handling.
	 * @param project to be mapped
	 * @throws CoreException
	 */
	protected void waitForProjectToRepoMapping(IProject project) throws CoreException {
		project.refreshLocal(Resource.DEPTH_INFINITE, null);
	}
	
	@Before
	public void setUp() throws CoreException {
		super.setUp();
		
		projectRepoLocal1 = createTestProject(PROJECT_LOCAL_NAME, pathRepoLocal1, true);
		
		assertTrue("Project exsists in workspace", projectRepoLocal1.exists());
		assertTrue("Project exsists on filesystem", projectRepoLocal1.getRawLocation().toFile().exists());
		projectCommons = new VirSatProjectCommons(projectRepoLocal1);
	}

	/**
	 * This method creates an unmanaged ResourceSet which is non transactional. The
	 * method also creates a repository
	 */
	protected void addResourceSetAndRepository(IProject project) {
		rs = VirSatResourceSet.createUnmanagedResourceSet(project);
		rs.initializeModelsAndResourceSet();
		repository = rs.getRepository();
	}

	@Test
	public void testSimpleCommitAndUpdate() throws Exception {
		// Create a new SEI on the file system
		addResourceSetAndRepository(projectRepoLocal1);
		StructuralElementInstance sei1 = StructuralFactory.eINSTANCE.createStructuralElementInstance();
		rs.getStructuralElementInstanceResource(sei1);

		IFile seiFile = projectCommons.getStructuralElementInstanceFile(sei1);
		assertTrue("File exsists in workspace", seiFile.exists());
		assertTrue("File exsists on filesystem", seiFile.getRawLocation().toFile().exists());
		
		// Commit repository and SEI files with VirtualSatellite Backend
		backend.commit(projectRepoLocal1, "First Simple Commit", new NullProgressMonitor());
		
		checkRemoteForCommitMessage("First Simple Commit");
		
		// Create the project again using another file system repository
		projectRepoLocal1.delete(true, null);
		IProject projectRepoLocal2 = createTestProject(PROJECT_LOCAL_NAME, pathRepoLocal2, true);

		// Checkout to local2 and see if SEI file has been transferred
		backend.update(projectRepoLocal2, new NullProgressMonitor());

		File seiInLocal2 = new File(pathRepoLocal2.toFile(), seiFile.getFullPath().toOSString());
		assertRetry(
			ASSERT_RETRY_COUNT,
			ASSERT_RETRY_TIME,
			() -> assertTrue("File also exists in local2 after pull", seiInLocal2.exists())
		);
	}
	
	protected void checkRemoteForCommitMessage(String message) {
	}

	@Test
	public void testUpdateWithConflict() throws Exception {
		// Create a new SEI on the file system
		addResourceSetAndRepository(projectRepoLocal1);
		StructuralElementInstance seiUpstream = StructuralFactory.eINSTANCE.createStructuralElementInstance();
		seiUpstream.setName("UPSTREAM_NAME");
		VirSatUuid uuidSeiUpstream = seiUpstream.getUuid();
		org.eclipse.emf.ecore.resource.Resource seiUpstreamResource = rs.getStructuralElementInstanceResource(seiUpstream);
		seiUpstreamResource.getContents().add(seiUpstream);
		rs.saveAllResources(null, UserRegistry.getInstance());
		
		IFile seiUpstreamFile = projectCommons.getStructuralElementInstanceFile(seiUpstream);
		assertTrue("File exsists in workspace", seiUpstreamFile.exists());
		assertTrue("File exsists on filesystem", seiUpstreamFile.getRawLocation().toFile().exists());
		
		// Commit repository and SEI files with VirtualSatellite Backend
		backend.commit(projectRepoLocal1, "First Commit", new NullProgressMonitor());
		
		// Create the project again using another file system repository
		projectRepoLocal1.delete(true, null);
		IProject projectRepoLocal2 = createTestProject(PROJECT_LOCAL_NAME, pathRepoLocal2, true);
		projectCommons = new VirSatProjectCommons(projectRepoLocal2);
		addResourceSetAndRepository(projectRepoLocal2);
		
		// Recreate the upstream sei locally but with a different name to create a conflict
		StructuralElementInstance seiDownstream = StructuralFactory.eINSTANCE.createStructuralElementInstance();
		seiDownstream.setName("DOWNSTREAM_NAME");
		seiDownstream.setUuid(uuidSeiUpstream);
		org.eclipse.emf.ecore.resource.Resource seiDownstreamResource = rs.getStructuralElementInstanceResource(seiDownstream);
		seiDownstreamResource.getContents().add(seiDownstream);
		rs.saveAllResources(null, UserRegistry.getInstance());
		
		IFile seiDownstreamFile = projectCommons.getStructuralElementInstanceFile(seiDownstream);
		assertTrue("File exsists in workspace", seiDownstreamFile.exists());
		assertTrue("File exsists on filesystem", seiDownstreamFile.getRawLocation().toFile().exists());
		
		// Perform potentially backend dependent prep on the repo to ensure that a conflict occurs
		ensureFileCanConflict(seiDownstreamFile);
		
		// Also create a new second local, non-conflicting sei that should be untouched by the update operation
		StructuralElementInstance seiNewDownstream = StructuralFactory.eINSTANCE.createStructuralElementInstance();
		rs.getStructuralElementInstanceResource(seiNewDownstream);
		IFile seiNewDownstreamFile = projectCommons.getStructuralElementInstanceFile(seiNewDownstream);
		assertTrue("File exsists in workspace", seiNewDownstreamFile.exists());
		assertTrue("File exsists on filesystem", seiNewDownstreamFile.getRawLocation().toFile().exists());
		
		// Update project in local2
		backend.update(projectRepoLocal2, new NullProgressMonitor());

		// Reload the downstream sei which should now have the name of the upstream sei
		rs.reloadResource(seiDownstreamResource);
		StructuralElementInstance seiDownstreamAfterUpdate = (StructuralElementInstance) seiDownstreamResource.getContents().get(0);
		assertEquals("Replaced conflicting local with repo version", seiUpstream.getName(), seiDownstreamAfterUpdate.getName());
		
		// Check that the locally created sei still exists and was not removed during the merge
		seiNewDownstreamFile = projectCommons.getStructuralElementInstanceFile(seiNewDownstream);
		assertTrue("File exsists in workspace", seiNewDownstreamFile.exists());
		assertTrue("File exsists on filesystem", seiNewDownstreamFile.getRawLocation().toFile().exists());
	}
	
	/**
	 * Method that allows backend implementations to ensure that the given file is correctly
	 * staged so that a conflict can be recognized.
	 * @param file the file that should have a conflict
	 */
	protected void ensureFileCanConflict(IFile file) {
		// Override in concrete backend tests
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
		Path pathRepositoryHome = VirSatFileUtils.createAutoDeleteTempDirectory("VirtualSatelliteCheckOut_");
		File pathRepoLocal = new File(pathRepositoryHome.toFile(), "repoLocal");
		Path pathRepoCheckout = new File(pathRepoLocal, PROJECT_LOCAL_NAME).toPath();
		
		IProjectDescription projectDescription = ResourcesPlugin.getWorkspace().newProjectDescription(PROJECT_LOCAL_NAME);
		projectDescription.setLocationURI(pathRepoCheckout.toUri());
		
		IProject projectRepoCheckout = ResourcesPlugin.getWorkspace().getRoot().getProject(PROJECT_LOCAL_NAME);
		projectRepoCheckout.delete(true, true, null);
		assertFalse("Repo does not yet exist", projectRepoCheckout.exists());
		
		// Execute the checkout
		IProject projectCheckout = backend.checkout(projectDescription, pathRepoLocal, pathRepoRemote.toUri().toString(), new NullProgressMonitor());
		assertTrue("Checked out project exists", projectCheckout.exists());
		assertTrue("Checked out project is open", projectCheckout.isOpen());

		// Now check that the SEI has been well checked out in the project and on the
		// file system
		File seiInLocalCheckout = new File(pathRepoLocal, seiFile.getFullPath().toOSString());
		assertTrue("File also exists in local2 after checkout", seiInLocalCheckout.exists());

		IFile seiInLocalCheckoutWorkspace = projectCheckout.getFile(seiFile.getFullPath().removeFirstSegments(1));
		assertTrue("File also exists in workspace", seiInLocalCheckoutWorkspace.exists());
	}

	@Test
	public void testCheckin() throws Exception {
		// Create a new full fledged Repository and try to check it into Repository
		Path pathRepoCheckin = VirSatFileUtils.createAutoDeleteTempDirectory("VirtualSatelliteCheckIn_");
		IProject projectCheckin = createTestProject(PROJECT_CHECKIN_NAME, pathRepoCheckin, false);
		addResourceSetAndRepository(projectCheckin);
		StructuralElementInstance sei1 = StructuralFactory.eINSTANCE.createStructuralElementInstance();
		rs.getStructuralElementInstanceResource(sei1);
		projectCommons = new VirSatProjectCommons(projectCheckin);
		IFile seiFile = projectCommons.getStructuralElementInstanceFile(sei1);

		assertNull("Project does not yet have a git mapping", RepositoryMapping.getMapping(projectCheckin));
		
		// Now checkin the project and transmit it to the remote bare repository
		Path pathRepoLocal = VirSatFileUtils.createAutoDeleteTempDirectory("VirtualSatelliteLocalCheckin_");
		backend.checkin(projectCheckin, pathRepoLocal.toFile(), pathRepoRemote.toUri().toString(), new NullProgressMonitor());
		waitForProjectToRepoMapping(projectCheckin);
		backend.commit(projectCheckin, "Initial Commit", new NullProgressMonitor());

		// Create the project again using another file system repository
		projectCheckin.delete(true, null);
		projectRepoLocal1 = createTestProject(PROJECT_CHECKIN_NAME, pathRepoLocal1, true);
		
		// Now update the project as Repo Local1
		backend.update(projectRepoLocal1, new NullProgressMonitor());

		// And check if the file is present
		File seiInLocalRepo1 = new File(pathRepoLocal1.toFile(),
				seiFile.getFullPath().toOSString());
		assertTrue("File also exists in local1 after update", seiInLocalRepo1.exists());

		projectRepoLocal1.refreshLocal(Resource.DEPTH_INFINITE, null);
		IFile seiInLocalRepo1Workspace = projectRepoLocal1.getFile(seiFile.getFullPath().removeFirstSegments(1));
		assertTrue("File also exists in workspace", seiInLocalRepo1Workspace.exists());
	}
}
