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
import org.eclipse.core.runtime.Status;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import de.dlr.sc.virsat.server.Activator;

/**
 * This class implements VirSat Style Git Access, which basically
 * pulls and fetches as well as commits and pushes.
 * Implementation is based purely on jGit and not eGit 
 *
 */
public class VirSatGitAccess {

	public static final String STATUS_OK = "ok";
	
	private static VirSatGitAccess gitAccess = null;

	private VirSatGitAccess() {
	}

	public static VirSatGitAccess getInstance() {
		if (gitAccess == null) {
			gitAccess = new VirSatGitAccess();
		}
		return gitAccess;
	}

	/**
	 * Clones a git repository from the given uri to a local repository.
	 * @param uri git uri to clone from
	 * @param localRoot directory where the cloned repository should be put below
	 * @return local directory where the git repository was cloned to, if successful; error message otherwise
	 */
	public String cloneRepository(String uri, String localRoot) {
		String result = localRoot;
		
		try {
			Git.cloneRepository()
				.setURI(uri)
				.setDirectory(new File(localRoot))
				.call();
		} catch (GitAPIException e) {
			result = e.getMessage();
			Activator.getDefault().getLog().log(new Status(Status.WARNING, Activator.getPluginId(), "Failed to git clone" + e.getMessage()));
		}

		return result;
	}

	/**
	 * Commits the changes of the local directory of the repository belonging to the given URI.
	 * Three commands are executed: add all files, commit (with message), push.
	 * @param localDirectory directory of local repository
	 * @param message commit message
	 * @return "ok" if everything was ok; exception message if there was an exception
	 */
	public String commit(String localDirectory, String message) {
		String result = STATUS_OK;

		try {
			Git git = Git.open(new File(localDirectory));
			git.add()
				.addFilepattern(".")
				.call();
			git.commit()
				.setAll(true)
				.setMessage(message)
				.call();
			git.push()
				.call();
		} catch (IOException | GitAPIException e) {
			result = e.getMessage();
			Activator.getDefault().getLog().log(new Status(Status.WARNING, Activator.getPluginId(), "Failed to git commit" + e.getMessage()));
		} 
		return result;
	}

	/**
	 * 
	 * @param localDirectory directory of local repository
	 * @return
	 */
	public String update(String localDirectory) {
		String result = STATUS_OK;
		Git git;
		try {
			git = Git.open(new File(localDirectory));
			git.pull()
				.call();
		} catch (IOException | GitAPIException e) {
			result = e.getMessage();
			Activator.getDefault().getLog().log(new Status(Status.WARNING, Activator.getPluginId(), "Failed to git update" + e.getMessage()));
		} 
		
		return result;
	}
}
