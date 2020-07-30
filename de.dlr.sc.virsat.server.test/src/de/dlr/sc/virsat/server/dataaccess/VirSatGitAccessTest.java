/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.server.dataaccess;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Spliterator;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.apache.commons.io.FileUtils;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.NoHeadException;
import org.eclipse.jgit.revwalk.RevCommit;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import de.dlr.sc.virsat.server.test.AGitAndJettyServerTest;

public class VirSatGitAccessTest {

	private File absolutePathToTempUpstreamRepository;
	private File relativePathToTempLocalRepository1;
	private File relativePathToTempLocalRepository2;
	private Git gitUpstream;
	private File workspaceRoot;

	@Before
	public void setUp() throws Exception {
		workspaceRoot = ResourcesPlugin.getWorkspace().getRoot().getLocation().toFile();
		
		absolutePathToTempUpstreamRepository = new File(workspaceRoot, "VirSatUpstreamRepo");
		relativePathToTempLocalRepository1 = new File("VirSatLocalRepo1");
		relativePathToTempLocalRepository2 = new File("VirSatLocalRepo2");
		
		FileUtils.deleteQuietly(makeAbsolute(relativePathToTempLocalRepository1));
		FileUtils.deleteQuietly(makeAbsolute(relativePathToTempLocalRepository2));
		Files.createDirectory(makeAbsolute(relativePathToTempLocalRepository1).toPath());
		Files.createDirectory(makeAbsolute(relativePathToTempLocalRepository2).toPath());

		gitUpstream = Git.init().setDirectory(absolutePathToTempUpstreamRepository).call();
	}

	private File makeAbsolute(File relativePath) throws IOException {
		return AGitAndJettyServerTest.makeAbsolute(relativePath);
	}

	@After
	public void tearDown() throws Exception {
		gitUpstream.close();
		FileUtils.deleteQuietly(absolutePathToTempUpstreamRepository);
		FileUtils.deleteQuietly(makeAbsolute(relativePathToTempLocalRepository1));
		FileUtils.deleteQuietly(makeAbsolute(relativePathToTempLocalRepository2));
	}

	@Test
	public void testCloneRepository() {
		// Try to clone a repo which does not exist
		String failPath = absolutePathToTempUpstreamRepository.toString() + "/Fail/";
		String resultFail = new VirSatGitAccess(workspaceRoot).cloneRepository(
			failPath, relativePathToTempLocalRepository1.toString()
		);
		assertEquals("Failed to clone upstream repsoitory", VirSatGitAccess.STATUS_FAIL_CLONE + relativePathToTempLocalRepository1.toString(), resultFail);
		
		// Try to clone into a local repo which is not relative
		String resultFail2 = new VirSatGitAccess(workspaceRoot).cloneRepository(
				absolutePathToTempUpstreamRepository.toString(),
				relativePathToTempLocalRepository1.getAbsolutePath().toString()
		);
		assertEquals("Failed to clone upstream repsoitory", VirSatGitAccess.STATUS_FAIL_PATH + relativePathToTempLocalRepository1.getAbsolutePath().toString(), resultFail2);
		
		// Now try to clone the correct Repository
		String resultSuccess = new VirSatGitAccess(workspaceRoot).cloneRepository(
			absolutePathToTempUpstreamRepository.toString(),
			relativePathToTempLocalRepository1.toString()
		);
		assertEquals("Succeded to clone upstream repsoitory", VirSatGitAccess.STATUS_OK, resultSuccess);
	}

	@Test
	public void testCommit() throws IOException, NoHeadException, GitAPIException {
		final String COMMIT_MESSAGE = "Some Commit Message";

		// Now try to clone the correct Repository
		new VirSatGitAccess(workspaceRoot).cloneRepository(
			absolutePathToTempUpstreamRepository.toString(),
			relativePathToTempLocalRepository1.toString()
		);

		// now add a file to the local repository
		File newFile = makeAbsolute(new File(relativePathToTempLocalRepository1 + "/test.dat"));
		newFile.createNewFile();
		
		// now try to commit the new file to the wrong repository 2 and not 1
		String resultFail = new VirSatGitAccess(workspaceRoot).commit(relativePathToTempLocalRepository2.toString(), COMMIT_MESSAGE);
		assertEquals("Failed to commit to upstream repo", VirSatGitAccess.STATUS_FAIL_COMMIT + relativePathToTempLocalRepository2.toString(), resultFail);

		// Try to commit to absolute path
		String resultFail2 = new VirSatGitAccess(workspaceRoot).commit(relativePathToTempLocalRepository1.getAbsolutePath().toString(), COMMIT_MESSAGE);
		assertEquals("Failed to commit to upstream repo", VirSatGitAccess.STATUS_FAIL_PATH + relativePathToTempLocalRepository1.getAbsolutePath().toString(), resultFail2);

		// now try to commit from the correct repository and check that the commit arrived on the remote repository
		String resultSuccess = new VirSatGitAccess(workspaceRoot).commit(relativePathToTempLocalRepository1.toString(), COMMIT_MESSAGE);
		assertEquals("Sucessfully to commit to upstream repo", VirSatGitAccess.STATUS_OK, resultSuccess);

		// now check the received commit messages
		Spliterator<RevCommit> spliterator = gitUpstream.log().all().call().spliterator();
		List<String> commitMessages = StreamSupport.stream(spliterator, false).map(RevCommit::getShortMessage).collect(Collectors.toList());
		assertThat("Commit Message arrived at upstream Repository", commitMessages, hasItem(COMMIT_MESSAGE));
	}

	@Test
	public void testUpdate() throws IOException, NoHeadException, GitAPIException {
		final String COMMIT_MESSAGE = "Commit and Update";

		// Now try to clone the correct Repository
		new VirSatGitAccess(workspaceRoot).cloneRepository(
			absolutePathToTempUpstreamRepository.toString(),
			relativePathToTempLocalRepository1.toString()
		);

		// Clone the second repository and make sure that there is no commit message jet
		new VirSatGitAccess(workspaceRoot).cloneRepository(
			absolutePathToTempUpstreamRepository.toString(),
			relativePathToTempLocalRepository2.toString()
		);

		// now add a file to the local repository
		File newFile = makeAbsolute(new File(relativePathToTempLocalRepository1 + "/test.dat"));
		newFile.createNewFile();

		// Commit the file
		String resultSuccess = new VirSatGitAccess(workspaceRoot).commit(relativePathToTempLocalRepository1.toString(), COMMIT_MESSAGE);
		assertEquals("Sucessfully to commit to upstream repo", VirSatGitAccess.STATUS_OK, resultSuccess);

		// now do the first update on false location the "FailRepository"
		File failPath = new File("FailRepo");
		String resultFail = new VirSatGitAccess(workspaceRoot).update(failPath.toString());
		assertEquals("Failed to update from repo", VirSatGitAccess.STATUS_FAIL_UPDATE + failPath.toString(), resultFail);

		// now update with an incorrect relative path
		String resultFail2 = new VirSatGitAccess(workspaceRoot).update(relativePathToTempLocalRepository2.getAbsolutePath().toString());
		assertEquals("Failed to update from repo",  VirSatGitAccess.STATUS_FAIL_PATH + relativePathToTempLocalRepository2.getAbsolutePath().toString(), resultFail2);
		
		// now do the correct update
		resultSuccess = new VirSatGitAccess(workspaceRoot).update(relativePathToTempLocalRepository2.toString());
		assertEquals("Succeeded to update from repo", VirSatGitAccess.STATUS_OK, resultSuccess);

		// and see if the commit message arrived
		Git gitRepo2 = Git.open(makeAbsolute(relativePathToTempLocalRepository2));
		Spliterator<RevCommit> spliterator = gitRepo2.log().all().call().spliterator();
		List<String> commitMessages = StreamSupport.stream(spliterator, false).map(RevCommit::getShortMessage).collect(Collectors.toList());
		assertThat("Commit Message arrived at second local repository", commitMessages, hasItem(COMMIT_MESSAGE));
		gitRepo2.close();
	}
}
