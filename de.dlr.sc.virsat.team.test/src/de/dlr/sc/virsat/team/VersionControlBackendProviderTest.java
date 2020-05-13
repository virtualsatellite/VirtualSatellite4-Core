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
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.team.svn.core.resource.IRepositoryResource;
import org.eclipse.team.svn.core.utility.SVNUtility;
import org.junit.Test;

import de.dlr.sc.virsat.commons.file.VirSatFileUtils;
import de.dlr.sc.virsat.team.git.VirSatGitVersionControlBackend;
import de.dlr.sc.virsat.team.svn.VirSatSvnVersionControlBackend;
import de.dlr.sc.virsat.team.test.CreateSvnServerOperation;

public class VersionControlBackendProviderTest {
	
	@Test
	public void testCreateBackendImplementationGit() {
		VersionControlBackendProvider backendProvider = new VersionControlBackendProvider(
				VersionControlSystem.GIT, 
				URI.create("testUri"), 
				"abc", "123");
		
		IVirSatVersionControlBackend backend = backendProvider.createBackendImplementation();
		assertTrue("A git backend was instantiated", backend instanceof VirSatGitVersionControlBackend);
	}

	@Test
	public void testCreateBackendImplementationSvn() throws IOException, CoreException {
		Path pathRepoRemote = VirSatFileUtils.createAutoDeleteTempDirectory("VirtualSatelliteSvnRemote_");
		URI uriToRemoteRepoPath = pathRepoRemote.toUri();
		String remoteRepoFilePath = pathRepoRemote.toString();
		
		IRepositoryResource remoteRepo = SVNUtility.asRepositoryResource(uriToRemoteRepoPath.toString(), true);
		
		assertNull("Initially no username is set", remoteRepo.getRepositoryLocation().getUsername());
		assertNull("Initially no password is set", remoteRepo.getRepositoryLocation().getPassword());
		
		CreateSvnServerOperation createRemoteRepoOp = new CreateSvnServerOperation(remoteRepoFilePath);
		createRemoteRepoOp.runWithExceptionChecking(new NullProgressMonitor());
		
		final String USERNAME = "abc";
		final String PASSWORD = "123";
		
		VersionControlBackendProvider backendProvider = new VersionControlBackendProvider(
				VersionControlSystem.SVN, 
				uriToRemoteRepoPath, 
				USERNAME, PASSWORD);
		
		IVirSatVersionControlBackend backend = backendProvider.createBackendImplementation();
		assertTrue("A svn backend was instantiated", backend instanceof VirSatSvnVersionControlBackend);
		
		// Rebuild the repository resource, it should now have the credentials associated with it
		remoteRepo = SVNUtility.asRepositoryResource(uriToRemoteRepoPath.toString(), true);
		
		assertEquals("Username has been set correctly", USERNAME, remoteRepo.getRepositoryLocation().getUsername());
		// The password is saved encrypted so instead of directly comparing them we just check that a password has been set
		assertNotNull("A password has been set", remoteRepo.getRepositoryLocation().getPassword());
		
		// Cleanup
		Files.walk(pathRepoRemote).sorted(Comparator.reverseOrder()).map(Path::toFile).filter(File::exists).forEach(File::delete);
	}
	
}
