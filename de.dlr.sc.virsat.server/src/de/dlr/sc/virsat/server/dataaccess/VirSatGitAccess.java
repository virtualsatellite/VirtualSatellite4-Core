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

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FilenameUtils;
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
	public static final String STATUS_FAIL_CLONE = "Failed to clone repository: ";
	public static final String STATUS_FAIL_COMMIT = "Failed to commit repository: ";
	public static final String STATUS_FAIL_UPDATE = "Failed to update repository: ";
	public static final String STATUS_FAIL_PATH = "Repository path is not relative: : ";
	
	private File workspaceRoot;
	
	public VirSatGitAccess(File workspaceRoot) {
		this.workspaceRoot = workspaceRoot;
	}

	
	/**
	 * Clones a git repository from the given uri to a local repository.
	 * @param remoteUri git uri to clone from
	 * @param localRelativePath directory where the cloned repository should be put below
	 * @return "ok" in case things go ok, otherwise a message indicating the problem
	 */
	public String cloneRepository(String remoteUri, String localRelativePath) {
		String result = STATUS_OK;
		try {
			File absolutePath = createAndCheckAbsolutePath(new File(localRelativePath));
		
			Git.cloneRepository()
				.setURI(remoteUri)
				.setDirectory(absolutePath)
				.call()
				.close();
		} catch (PathException e) {
			result = STATUS_FAIL_PATH + localRelativePath;
			Activator.getDefault().getLog().log(new Status(Status.WARNING, Activator.getPluginId(), STATUS_FAIL_PATH + e.getMessage()));
		} catch (GitAPIException | IOException e) {
			result = STATUS_FAIL_CLONE + localRelativePath;
			Activator.getDefault().getLog().log(new Status(Status.WARNING, Activator.getPluginId(), STATUS_FAIL_CLONE + e.getMessage()));
		}

		return result;
	}

	/**
	 * Commits the changes of the local directory of the repository belonging to the given URI.
	 * Three commands are executed: add all files, commit (with message), push.
	 * @param localRelativePath directory of local repository
	 * @param message commit message
	 * @return "ok" if everything was ok; exception message if there was an exception
	 */
	public String commit(String localRelativePath, String message) {
		String result = STATUS_OK;
		try {
			File absolutePath = createAndCheckAbsolutePath(new File(localRelativePath));

			Git git = Git.open(absolutePath);
			git.add()
				.addFilepattern(".")
				.call();
			git.commit()
				.setAll(true)
				.setMessage(message)
				.call();
			git.push()
				.call();
			git.close();
		} catch (PathException e) {
			result = STATUS_FAIL_PATH + localRelativePath;
			Activator.getDefault().getLog().log(new Status(Status.WARNING, Activator.getPluginId(), STATUS_FAIL_PATH + e.getMessage()));
		} catch (IOException | GitAPIException e) {
			result = STATUS_FAIL_COMMIT + localRelativePath;
			Activator.getDefault().getLog().log(new Status(Status.WARNING, Activator.getPluginId(), STATUS_FAIL_COMMIT + e.getMessage()));
		} 
		return result;
	}

	/**
	 * This method updates the git repository in the Virtual satellite style.
	 * This means it fetches all changes from the upstream and applies them
	 * to the local working copy
	 * @param localRelativePath the relative path to the repository
	 * @return "ok" in case things go ok, otherwise a message indicating the problem
	 */
	public String update(String localRelativePath) {
		String result = STATUS_OK;
		try {
			File absolutePath = createAndCheckAbsolutePath(new File(localRelativePath));
			Git git = Git.open(absolutePath);
			git.pull()
				.call();
			git.close();
		} catch (PathException e) {
			result = STATUS_FAIL_PATH + localRelativePath;
			Activator.getDefault().getLog().log(new Status(Status.WARNING, Activator.getPluginId(), STATUS_FAIL_PATH + e.getMessage()));
		} catch (IOException | GitAPIException e) {
			result = STATUS_FAIL_UPDATE + localRelativePath;
			Activator.getDefault().getLog().log(new Status(Status.WARNING, Activator.getPluginId(), STATUS_FAIL_UPDATE + e.getMessage()));
		} 
		
		return result;
	}
	
	/**
	 * This method checks the access to the path
	 * @param localRelativePath the relative path of the repository
	 * @return the absolute to the repository which is combined with the workspaceRoot
	 * @throws IOException in case that there seems to be something wrong with the given path
	 */
	private File createAndCheckAbsolutePath(File localRelativePath) throws IOException {
		if (localRelativePath.isAbsolute()) {
			throw new PathException(STATUS_FAIL_PATH + localRelativePath.toString());
		}
		
		File absolutePath = new File(FilenameUtils.concat(workspaceRoot.toString(), localRelativePath.toString()));
		return absolutePath;
	}
	
	protected static class PathException extends IOException {

		private static final long serialVersionUID = -450544137199653642L;

		public PathException(String message) {
			super(message);
		}
	}
}
