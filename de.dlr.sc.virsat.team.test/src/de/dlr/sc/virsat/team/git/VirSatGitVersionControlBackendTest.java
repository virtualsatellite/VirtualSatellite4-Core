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
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.Iterator;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Status;
import org.eclipse.egit.core.internal.util.ResourceUtil;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.revwalk.RevCommit;
import org.junit.Before;

import de.dlr.sc.virsat.commons.file.VirSatFileUtils;
import de.dlr.sc.virsat.team.AVirSatVersionControlBackendTest;
import de.dlr.sc.virsat.team.Activator;

@SuppressWarnings("restriction")
public class VirSatGitVersionControlBackendTest extends AVirSatVersionControlBackendTest {
	
	@Before
	public void setUp() throws CoreException {
		try {
			pathRepoRemote = VirSatFileUtils.createAutoDeleteTempDirectory("VirtualSatelliteGitRemote_");
			File fileGitRemoteRepo = pathRepoRemote.toFile();
			Git.init().setDirectory(fileGitRemoteRepo).setBare(true).call();

			URI uriToRemoteRepoPath = pathRepoRemote.toUri();
			pathRepoLocal1 = VirSatFileUtils.createAutoDeleteTempDirectory("VirtualSatelliteGitLocal1_");
			File filePathToProject = pathRepoLocal1.toFile();
			Git.cloneRepository().setURI(uriToRemoteRepoPath.toString()).setDirectory(filePathToProject).call();

			pathRepoLocal2 = VirSatFileUtils.createAutoDeleteTempDirectory("VirtualSatelliteGitLocal2_");
			filePathToProject = pathRepoLocal2.toFile();
			Git.cloneRepository().setURI(uriToRemoteRepoPath.toString()).setDirectory(filePathToProject).call();
		} catch (IOException | GitAPIException e) {
			throw new CoreException(new Status(Status.ERROR, Activator.getPluginId(),
					"Error during temp directory creation", e));
		}

		super.setUp();
		
		backend = new VirSatGitVersionControlBackend(null);
	}
	
	@Override
	protected void checkRemoteForCommitMessage(String message) {
		try {
			Iterator<RevCommit> commitIterator = Git.open(pathRepoRemote.toFile()).log().call().iterator();
			assertTrue("Remote Rpeository has a commit message", commitIterator.hasNext());
			assertEquals("Remote commit has expected commit message", message, commitIterator.next().getShortMessage());
		} catch (GitAPIException | IOException e) {
			fail("Failed to check interim state of remote repository: " + e.getMessage());
		}
	}
	
	@Override
	protected IProject createTestProject(String projectName) throws CoreException {
		testProject = super.createTestProject(projectName);
		
		// Try to ramp up the egit plugins as early as possible. Thus the RepositoryMapping
		// starts working and tries to map new git repositories with newly created projects
		// in the workspace. If this line is missing, the waitForProjectMapping is likely to
		// stall all tests.
		ResourceUtil.isSharedWithGit(testProject);
		return testProject;
	}
	
	private static final int WAIT_FOR_REPO_DETECTION_TIMESPAN = 10;
	
	@Override
	protected void waitForProjectToRepoMapping(IProject project) throws CoreException {
		super.waitForProjectToRepoMapping(project);
		try {
			while (!ResourceUtil.isSharedWithGit(project)) {
				Thread.sleep(WAIT_FOR_REPO_DETECTION_TIMESPAN);
			}
		} catch (InterruptedException e) {
		}
	}
}
