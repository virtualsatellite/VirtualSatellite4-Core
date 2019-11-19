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

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;

public class GitAccess {

	private static GitAccess gitAccess = null;

	private GitAccess() {
	}

	public static GitAccess getInstance() {
		if (gitAccess == null) {
			gitAccess = new GitAccess();
		}

		return gitAccess;
	}

	/**
	 * Clones a git repository from the given uri to a local repository.
	 * @param uri git uri to clone from
	 * @return local directory where the git repository was cloned to
	 */
	public String cloneRepository(String uri, String username, String password) {
		URL url = null;
		String directory = "";
		try {
			url = new URL(uri);
			directory = url.getFile(); // TODO: better extraction
		} catch (MalformedURLException e1) {
			e1.printStackTrace();
		}
		try {
			Git.cloneRepository()
			   .setURI(uri)
			   .setDirectory(new File(directory))
			   .setCredentialsProvider(new UsernamePasswordCredentialsProvider(username, password))
			    .call();
		} catch (GitAPIException e) {
			e.printStackTrace();
		}

		return directory;
	}
}
