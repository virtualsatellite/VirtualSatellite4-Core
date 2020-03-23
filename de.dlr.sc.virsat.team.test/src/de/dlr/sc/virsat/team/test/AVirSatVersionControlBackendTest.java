package de.dlr.sc.virsat.team.test;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;

import org.eclipse.core.internal.resources.Resource;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.egit.core.internal.util.ResourceUtil;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralFactory;
import de.dlr.sc.virsat.project.resources.VirSatResourceSet;
import de.dlr.sc.virsat.project.test.AProjectTestCase;
import de.dlr.sc.virsat.team.Activator;
import de.dlr.sc.virsat.team.IVirSatVersionControlBackend;

@SuppressWarnings("restriction")
public class AVirSatVersionControlBackendTest extends AProjectTestCase {

	protected IProject createTestProject(String projectName, URI fsProjectLocation, boolean waitForMapping)
			throws CoreException {
		IProjectDescription projectDescription = ResourcesPlugin.getWorkspace().newProjectDescription(projectName);
		projectDescription.setLocationURI(fsProjectLocation);
		return createTestProject(projectName, projectDescription, waitForMapping);
	}

	/**
	 * This method is used to create a project on a given descriptor which is
	 * supposed to have a location into a repository. The project creation waits
	 * until the Project is mapped as Repository
	 * 
	 * @param projectName        Name of the project to be created
	 * @param projectDescription descriptor pointing to the repository
	 * @return an IProject which is mapped to the repository
	 * @throws CoreException
	 */
	protected IProject createTestProject(String projectName, IProjectDescription projectDescription,
			boolean waitForMapping) throws CoreException {
		IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject(projectName);
		testProjects.add(project);
		if (!project.exists()) {
			project.create(projectDescription, null);
			project.open(null);
			Activator.getDefault().getLog().log(
					new Status(Status.INFO, Activator.getPluginId(), "Created new test project " + project.getName()));
		}

		if (waitForMapping) {
			waitForProjectToRepoMapping(project);
		}
		return project;
	}

	private static final int WAIT_FOR_REPO_DETECTION_TIMESPAN = 10;
	
	protected void waitForProjectToRepoMapping(IProject project) throws CoreException {
		project.refreshLocal(Resource.DEPTH_INFINITE, null);
		try {
			while (!ResourceUtil.isSharedWithGit(project)) {
				Thread.sleep(WAIT_FOR_REPO_DETECTION_TIMESPAN);
			}
		} catch (InterruptedException e) {
		}
	}

	protected Path pathRepoRemote;
	protected Path pathRepoLocal1;
	protected Path pathRepoLocal2;

	protected IProject projectRepoLocal1;
	protected IProject projectRepoLocal2;

	protected IVirSatVersionControlBackend backend;
	
	@Before
	public void setUp() throws CoreException {
		super.setUp();
		
		projectRepoLocal1 = createTestProject("VirSatRepoLocal1", pathRepoLocal1.toUri(), true);
		projectRepoLocal2 = createTestProject("VirSatRepoLocal2", pathRepoLocal2.toUri(), true);
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

	@After
	public void tearDown() throws CoreException {
		try {
			Files.delete(pathRepoRemote);
			Files.delete(pathRepoLocal1);
			Files.delete(pathRepoLocal2);
		} catch (IOException e) {
			Activator.getDefault().getLog().log(new Status(Status.ERROR, Activator.getPluginId(),
					"Error during temp remote directory creation", e));
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

		// Commit repository and SEI files with VirtualSatellite Backend
		backend.commit(projectRepoLocal1, "First Simple Commit", new NullProgressMonitor());

		// Checkout to local2 and see if SEI file has been transferred
		backend.update(projectRepoLocal2, new NullProgressMonitor());

		File seiInLocal2 = new File(pathRepoLocal2.toFile(),
				seiFile.getFullPath().removeFirstSegments(1).toOSString());
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
		Path pathRepoCheckout = Files.createTempDirectory("VirtualSatelliteCheckOut_");
		IProjectDescription projectDescription = ResourcesPlugin.getWorkspace()
				.newProjectDescription("VirSatRepoLocalCheckout");
		projectDescription.setLocationURI(pathRepoCheckout.toUri());

		// Execute the checkout
		backend.checkout(projectDescription, pathRepoRemote.toUri().toString(), new NullProgressMonitor());

		// Create the project and wait until it is mapped with the Providers
		IProject projectCheckout = createTestProject("VirSatRepoLocalCheckout", projectDescription, true);

		// Now check that the SEI has been well checked out in the project and on the
		// file system
		File seiInLocalCheckout = new File(pathRepoCheckout.toFile(),
				seiFile.getFullPath().removeFirstSegments(1).toOSString());
		assertTrue("File also exists in local2 after pull", seiInLocalCheckout.exists());

		IFile seiInLocalCheckoutWorkspace = projectCheckout.getFile(seiFile.getFullPath().removeFirstSegments(1));
		assertTrue("File also exists in workspace", seiInLocalCheckoutWorkspace.exists());
	}

	@Test
	public void testCheckin() throws Exception {
		// Create a new full fledged Repository and try to check it into Repository
		Path pathRepoCheckin = Files.createTempDirectory("VirtualSatelliteCheckIn_");
		IProject projectCheckin = createTestProject("VirSatRepoCheckin", pathRepoCheckin.toUri(), false);
		addResourceSetAndRepository(projectCheckin);
		StructuralElementInstance sei1 = StructuralFactory.eINSTANCE.createStructuralElementInstance();
		rs.getStructuralElementInstanceResource(sei1);
		IFile seiFile = projectCommons.getStructuralElementInstanceFile(sei1);

		// Now checkin the project and transmit it to the remote bare repository
		backend.checkin(projectCheckin, pathRepoRemote.toUri().toString(), new NullProgressMonitor());
		waitForProjectToRepoMapping(projectCheckin);
		backend.commit(projectCheckin, "Initial Commit", new NullProgressMonitor());

		// Now update the project as Repo Local1
		backend.update(projectRepoLocal1, new NullProgressMonitor());

		// And check if the file is present
		File seiInLocalRepo1 = new File(pathRepoLocal1.toFile(),
				seiFile.getFullPath().removeFirstSegments(1).toOSString());
		assertTrue("File also exists in local1 after update", seiInLocalRepo1.exists());

		projectRepoLocal1.refreshLocal(Resource.DEPTH_INFINITE, null);
		IFile seiInLocalRepo1Workspace = projectRepoLocal1.getFile(seiFile.getFullPath().removeFirstSegments(1));
		assertTrue("File also exists in workspace", seiInLocalRepo1Workspace.exists());
	}
}
