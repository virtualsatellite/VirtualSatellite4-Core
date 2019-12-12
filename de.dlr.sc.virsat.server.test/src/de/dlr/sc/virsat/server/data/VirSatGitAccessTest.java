/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.server.data;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.hasItem;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Spliterator;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.apache.commons.io.FileUtils;
import org.eclipse.core.runtime.Status;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.NoHeadException;
import org.eclipse.jgit.revwalk.RevCommit;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import de.dlr.sc.virsat.server.Activator;

public class VirSatGitAccessTest {

	private File pathToTempUpstreamRepository;
	private File pathToTempLocalRepository1;
	private File pathToTempLocalRepository2;
	private Git gitUpstream;
	
	@Before
	public void setUp() throws Exception {
		pathToTempUpstreamRepository = File.createTempFile("VirSatUpstreamRepo_", "");
		pathToTempLocalRepository1 = File.createTempFile("VirSatLocalRepo1_", "");
		pathToTempLocalRepository2 = File.createTempFile("VirSatLocalRepo2_", "");
		Files.delete(pathToTempUpstreamRepository.toPath());
		Files.delete(pathToTempLocalRepository1.toPath());
		Files.delete(pathToTempLocalRepository2.toPath());
		Files.createDirectory(pathToTempLocalRepository1.toPath());
		Files.createDirectory(pathToTempLocalRepository2.toPath());

		gitUpstream = Git.init().setDirectory(pathToTempUpstreamRepository).call();
	}

	@After
	public void tearDown() throws Exception {
		try {
			FileUtils.deleteDirectory(pathToTempUpstreamRepository);
			FileUtils.deleteDirectory(pathToTempLocalRepository1);
			FileUtils.deleteDirectory(pathToTempLocalRepository2);
		} catch (Exception e) {
			Activator.getDefault().getLog().log(new Status(Status.INFO, Activator.getPluginId(), "Failed to remove temp directory in test" + e.getMessage()));
		}
	}

	@Test
	public void testCloneRepository() {
		// Try to clone a repo which does not exist
		File failPath = new File(pathToTempUpstreamRepository.getAbsolutePath().toString() + "/Fail/");
		String resultFail = VirSatGitAccess.getInstance().cloneRepository(
			failPath.getAbsolutePath().toString(),
			pathToTempLocalRepository1.getAbsolutePath().toString()
		);
		
		assertEquals("Failed to clone upstream repsoitory", "Invalid remote: origin", resultFail);
		
		// Now try to clone the correct Repository
		String resultSuccess = VirSatGitAccess.getInstance().cloneRepository(
			pathToTempUpstreamRepository.getAbsolutePath().toString(),
			pathToTempLocalRepository1.getAbsolutePath().toString()
		);
			
		assertEquals("Succeded to clone upstream repsoitory", pathToTempLocalRepository1.getAbsolutePath().toString(), resultSuccess);
	}

	@Test
	public void testCommit() throws IOException, NoHeadException, GitAPIException {
		// Now try to clone the correct Repository
		VirSatGitAccess.getInstance().cloneRepository(
			pathToTempUpstreamRepository.getAbsolutePath().toString(),
			pathToTempLocalRepository1.getAbsolutePath().toString()
		);

		// now add a file to the local repository
		File newFile = new File(pathToTempLocalRepository1.getAbsolutePath().toString() + "/test.dat");
		Files.createFile(newFile.toPath());
		
		final String COMMIT_MESSAGE = "Firsat Commit";
		
		// now try to commit the new file to the wrong repository
		File failPath = new File(pathToTempUpstreamRepository.getAbsolutePath().toString() + "/Fail");
		String resultFail = VirSatGitAccess.getInstance().commit(failPath.getAbsolutePath().toString(), COMMIT_MESSAGE);

		assertEquals("Failed to commit to upstream repo", "repository not found: " + failPath.getAbsolutePath().toString(), resultFail);
	
		// now try to commit from the correct repository and check that the commit arrived on the remote repository
		String resultSuccess = VirSatGitAccess.getInstance().commit(pathToTempLocalRepository1.getAbsolutePath().toString(), COMMIT_MESSAGE);

		assertEquals("Sucessfully to commit to upstream repo", VirSatGitAccess.STATUS_OK, resultSuccess);
		
		Spliterator<RevCommit> spliterator = gitUpstream.log().all().call().spliterator();
		List<String> commitMessages = StreamSupport.stream(spliterator, false).map(RevCommit::getShortMessage).collect(Collectors.toList());

		assertThat("Commit Message arrived at upstream Repository", commitMessages, hasItem(COMMIT_MESSAGE));
	}

	@Test
	public void testUpdate() throws IOException, NoHeadException, GitAPIException {
		// Now try to clone the correct Repository
		VirSatGitAccess.getInstance().cloneRepository(
			pathToTempUpstreamRepository.getAbsolutePath().toString(),
			pathToTempLocalRepository1.getAbsolutePath().toString()
		);

		// Clone the second repository and make sure that there is no commit message jet
		VirSatGitAccess.getInstance().cloneRepository(
			pathToTempUpstreamRepository.getAbsolutePath().toString(),
			pathToTempLocalRepository2.getAbsolutePath().toString()
		);
		
		final String COMMIT_MESSAGE = "Second Commit";

		// now add a file to the local repository
		File newFile = new File(pathToTempLocalRepository1.getAbsolutePath().toString() + "/test.dat");
		Files.createFile(newFile.toPath());
		
		String resultSuccess = VirSatGitAccess.getInstance().commit(pathToTempLocalRepository1.getAbsolutePath().toString(), COMMIT_MESSAGE);
		assertEquals("Sucessfully to commit to upstream repo", VirSatGitAccess.STATUS_OK, resultSuccess);

		// now do the first update to a false location
		File failPath = new File(pathToTempUpstreamRepository.getAbsolutePath().toString() + "/Fail");
		String resultFail = VirSatGitAccess.getInstance().update(failPath.getAbsolutePath().toString());
		assertEquals("Failed to update from repo", "repository not found: " + failPath.getAbsolutePath().toString(), resultFail);

		// now do the correct update
		resultSuccess = VirSatGitAccess.getInstance().update(pathToTempLocalRepository2.getAbsolutePath().toString());
		assertEquals("Failed to update from repo", VirSatGitAccess.STATUS_OK, resultSuccess);

		// and see if the commit message arrived
		Spliterator<RevCommit> spliterator = Git.open(pathToTempLocalRepository2).log().all().call().spliterator();
		List<String> commitMessages = StreamSupport.stream(spliterator, false).map(RevCommit::getShortMessage).collect(Collectors.toList());
		assertThat("Commit Message arrived at second local repository", commitMessages, hasItem(COMMIT_MESSAGE));
	}
}
