/*******************************************************************************
 * Copyright (c) 2021 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.server.test;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.NoHeadException;
import org.eclipse.jgit.revwalk.RevCommit;

public class VersionControlTestHelper {
	
	private VersionControlTestHelper() { }

	/**
	 * Open a repository and count the commits
	 * @param repoFile the repository to open
	 * @return number of commits in remote
	 * @throws IOException
	 * @throws NoHeadException
	 * @throws GitAPIException
	 * @throws InterruptedException
	 */
	public static int countCommits(File repoFile) throws NoHeadException, GitAPIException, IOException {
		Git git = Git.open(repoFile);
		Iterator<RevCommit> iterator = git.log().call().iterator();
		int commits = 0;
		while (iterator.hasNext()) {
			iterator.next();
			commits++;
		}
		git.close();
		return commits;
	}
}
