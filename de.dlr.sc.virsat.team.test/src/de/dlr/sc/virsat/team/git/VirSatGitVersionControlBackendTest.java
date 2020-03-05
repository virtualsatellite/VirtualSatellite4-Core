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

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.NoFilepatternException;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.revwalk.RevCommit;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class VirSatGitVersionControlBackendTest extends AProjectTestCase {

	@Override
	protected IProject createTestProject(String projectName) throws CoreException {
		
		IProjectDescription projectDescription = ResourcesPlugin.getWorkspace().newProjectDescription(getProjectName());
		projectDescription.setLocationURI(pathGitRepoLocal1.toUri());
		
		IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject(getProjectName());
		
		
		testProjects.add(project);
		if (!project.exists()) {
			project.create(projectDescription, null);
			String location = project.getLocationURI().toString();
			project.open(null);
			Activator.getDefault().getLog().log(new Status(Status.INFO, Activator.getPluginId(), "Created new test project " +  project.getName()));
		}
		return project;
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

		final String TEST_FILE_NAME = "testFileA.txt";
		
		File fileInRemote = new File(pathGitRepoRemote.toString(), TEST_FILE_NAME);
		fileInRemote.createNewFile();
		
		Git.wrap(gitRepoRemote).add().addFilepattern(".").call();
		Git.wrap(gitRepoRemote).commit().setMessage("Inital Commit in Remote").call();

		File fileInLocal = new File(testProject.getProject().getFullPath().toFile(), TEST_FILE_NAME);
		
		assertFalse("File does not yet exist in local", fileInLocal.exists());
		
		new VirSatGitVersionControlBackend(null).update(testProject, new NullProgressMonitor());
		
		assertTrue("File appeared from Remote repository in local one", fileInLocal.exists());

	}

	@Test
	public void testCheckout() {
		fail("Not yet implemented");
	}

	@Test
	public void testCheckin() {
		fail("Not yet implemented");
	}

}
