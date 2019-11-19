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

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.AbortedByHookException;
import org.eclipse.jgit.api.errors.ConcurrentRefUpdateException;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.NoHeadException;
import org.eclipse.jgit.api.errors.NoMessageException;
import org.eclipse.jgit.api.errors.UnmergedPathsException;
import org.eclipse.jgit.api.errors.WrongRepositoryStateException;
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
	 * @param username Username for login at Git instance
	 * @param password Password for login at Git instance
	 * @return local directory where the git repository was cloned to
	 */
	public String cloneRepository(String uri, String username, String password) {
		String directory = extractDirectoryFromUri(uri);
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
	
	/**
	 * 
	 * @param uri
	 * @param message
	 * @param username
	 * @param password
	 * @return
	 */
	public String commitChanges(String uri, String message, String username, String password) {
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
			   .setCredentialsProvider(new UsernamePasswordCredentialsProvider(username, password))
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
	 * Takes from the URI the last part, which is the directory of the repository
	 * @param uri URI to get directory from
	 * @return directory part from the URI
	 */
	private String extractDirectoryFromUri(String uri) {
		URL url = null;
		String directory = "";
		try {
			url = new URL(uri);
			directory = url.getFile(); // TODO: better extraction
		} catch (MalformedURLException e1) {
			e1.printStackTrace();
		}
		
		return directory;
	}
}
