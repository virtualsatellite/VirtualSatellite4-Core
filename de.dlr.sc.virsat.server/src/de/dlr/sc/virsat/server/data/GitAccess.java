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
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.io.FilenameUtils;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.AbortedByHookException;
import org.eclipse.jgit.api.errors.ConcurrentRefUpdateException;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.NoHeadException;
import org.eclipse.jgit.api.errors.NoMessageException;
import org.eclipse.jgit.api.errors.UnmergedPathsException;
import org.eclipse.jgit.api.errors.WrongRepositoryStateException;

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
	 * @return local directory where the git repository was cloned to, if successful; error message otherwise
	 */
	public String cloneRepository(String uri) {
		String directory = extractDirectoryFromUri(uri);
		String result = directory;
		try {
			Git.cloneRepository()
				.setURI(uri)
				.setDirectory(new File(directory))
				.call();
		} catch (GitAPIException e) {
			result = e.getMessage();
			e.printStackTrace();
		}

		return result;
	}

	/**
	 * Commits the changes of the local directory of the repository belonging to the given URI.
	 * Three commands are executed: add all files, commit (with message), push.
	 * @param uri git URI for the repository
	 * @param message commit message
	 * @return "ok" if everything was ok; exception message if there was an exception
	 */
	public String commit(String uri, String message) {
		String result = "ok";
		String directory = extractDirectoryFromUri(uri);

		try {
			Git git = Git.open(new File(directory));
			git.add()
				.addFilepattern(".")
				.call();
			git.commit()
				.setAll(true)
				.setMessage(message)
				.call();
			git.push()
				.call();
		} catch (NoHeadException e) {
			result = e.getMessage();
			e.printStackTrace();
		} catch (NoMessageException e) {
			result = e.getMessage();
			e.printStackTrace();
		} catch (UnmergedPathsException e) {
			result = e.getMessage();
			e.printStackTrace();
		} catch (ConcurrentRefUpdateException e) {
			result = e.getMessage();
			e.printStackTrace();
		} catch (WrongRepositoryStateException e) {
			result = e.getMessage();
			e.printStackTrace();
		} catch (AbortedByHookException e) {
			result = e.getMessage();
			e.printStackTrace();
		} catch (GitAPIException e) {
			result = e.getMessage();
			e.printStackTrace();
		} catch (IOException e) {
			result = e.getMessage();
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 
	 * @param uri
	 * @return
	 */
	public String update(String uri) {
		String result = "ok";
		String directory = extractDirectoryFromUri(uri);

		Git git;
		try {
			git = Git.open(new File(directory));
			git.pull()
				.call();
		} catch (IOException e) {
			result = e.getMessage();
			e.printStackTrace();
		} catch (GitAPIException e) {
			result = e.getMessage();
			e.printStackTrace();
		}
		
		return result;
	}

	/** 
	 * Takes from the URI the last part, which is the directory of the repository
	 * @param uri URI to get directory from
	 * @return directory part from the URI
	 */
	private String extractDirectoryFromUri(String uri) {
		URL url = null;
		String directory = "";
		try {
			url = new URL(uri);
			directory = FilenameUtils.getBaseName(url.getPath());
		} catch (MalformedURLException e1) {
			e1.printStackTrace();
		}

		return directory;
	}
}
