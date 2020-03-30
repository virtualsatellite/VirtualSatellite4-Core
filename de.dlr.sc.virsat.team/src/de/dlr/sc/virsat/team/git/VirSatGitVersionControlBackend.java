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

import java.io.File;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.egit.core.EclipseGitProgressTransformer;
import org.eclipse.egit.core.op.ConnectProviderOperation;
import org.eclipse.egit.core.project.RepositoryMapping;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.lib.ProgressMonitor;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.merge.MergeStrategy;
import org.eclipse.jgit.transport.CredentialsProvider;

import de.dlr.sc.virsat.team.IVirSatVersionControlBackend;

@SuppressWarnings("restriction")
public class VirSatGitVersionControlBackend implements IVirSatVersionControlBackend {

	private CredentialsProvider credentialsProvider;
	
	public VirSatGitVersionControlBackend(CredentialsProvider credentialsProvider) {
		this.credentialsProvider = credentialsProvider;
	}
	
	public static final int PROGRESS_INDEX_COMMIT_UPDATE_STEPS = 2;
	public static final int PROGRESS_INDEX_COMMIT_CHECKIN_STEPS = 4;
	public static final int PROGRESS_INDEX_COMMIT_CHECKOUT_STEPS = 1;
	public static final int PROGRESS_INDEX_DO_COMMIT_STEPS = 2;
	
	public static final int GIT_REMOTE_TIMEOUT = 30;
	
	@Override
	public void commit(IProject project, String message, IProgressMonitor monitor) throws Exception {
		SubMonitor pushAndCommitMonitor = SubMonitor.convert(monitor, "Virtual Satellite git push and commit", PROGRESS_INDEX_COMMIT_UPDATE_STEPS);
		
		// Get the repository mapped to the project
		Repository gitRepository = RepositoryMapping.getMapping(project).getRepository();
		// Stage and commit all changes
		doCommit(gitRepository, message, pushAndCommitMonitor.split(1));

		ProgressMonitor gitPushMonitor = new EclipseGitProgressTransformer(pushAndCommitMonitor.split(1));
		// Push the commit
		Git.wrap(gitRepository).push()
			.setCredentialsProvider(credentialsProvider)
			.setProgressMonitor(gitPushMonitor)
			.setTimeout(GIT_REMOTE_TIMEOUT)
			.call();
	}

	/**
	 * Method to add all unstaged files and commit them to the local repository
	 * @param gitRepository the repository on which to perform the commit
	 * @param message a message to be used for the commit
	 * @param pushAndCommitMonitor A SubMonitor for progress Reporting. 
	 * @throws Exception 
	 */
	protected void doCommit(Repository gitRepository, String message, SubMonitor monitor) throws Exception {
		SubMonitor pushAndCommitMonitor = SubMonitor.convert(monitor, "Virtual Satellite git commit", PROGRESS_INDEX_DO_COMMIT_STEPS);
		
		pushAndCommitMonitor.split(1).subTask("Adding new files to index");
		Git.wrap(gitRepository).add()
			.addFilepattern(".")
			.call();		
		
		pushAndCommitMonitor.split(1).subTask("Commiting files");
		Git.wrap(gitRepository).commit()
			.setAll(true)
			.setMessage(message)
			.call();
	}

	@Override
	public void update(IProject project,  IProgressMonitor monitor) throws Exception {
		SubMonitor commitAndPullMonitor = SubMonitor.convert(monitor, "Virtual Satellite git commit and pull", PROGRESS_INDEX_COMMIT_UPDATE_STEPS);
		
		// Get the repository mapped to the project
		Repository gitRepository = RepositoryMapping.getMapping(project).getRepository();
		// Stage and commit all changes
		doCommit(gitRepository, "Local commit before pull", commitAndPullMonitor.split(1));

		ProgressMonitor gitMonitor = new EclipseGitProgressTransformer(commitAndPullMonitor.split(1));
		// Pull from origin
		Git.wrap(gitRepository).pull()
			.setCredentialsProvider(credentialsProvider)
			.setProgressMonitor(gitMonitor)
			.setTimeout(GIT_REMOTE_TIMEOUT)
			.setStrategy(MergeStrategy.THEIRS)
			.call();
	}

	@Override
	public void checkout(IProjectDescription projectDescription, String remoteUri, IProgressMonitor monitor) throws Exception {
		SubMonitor checkoutMonitor = SubMonitor.convert(monitor, "Virtual Satellite git clone", PROGRESS_INDEX_COMMIT_CHECKOUT_STEPS);
		
		File pathRepoLocal = new File(projectDescription.getLocationURI());
		
		checkoutMonitor.split(1).subTask("Cloning remote Repository");
		// Clone into the location specified by the project description
		Git.cloneRepository()
			.setCredentialsProvider(credentialsProvider)
			.setURI(remoteUri)
			.setDirectory(pathRepoLocal)
			.call();
	}

	@Override
	public void checkin(IProject project, File pathRepoLocal, String remoteUri, IProgressMonitor monitor) throws Exception {
		SubMonitor checkInMonitor = SubMonitor.convert(monitor, "Virtual Satellite git init", PROGRESS_INDEX_COMMIT_CHECKIN_STEPS);
		
		checkInMonitor.split(1).subTask("Cloning remote Repository");
		// Clone into the location specified by the project description
		Repository repo = Git.cloneRepository()
			.setCredentialsProvider(credentialsProvider)
			.setURI(remoteUri)
			.setDirectory(pathRepoLocal)
			.call()
			.getRepository();
		
		checkInMonitor.split(1).subTask("Moving project into local Repository");
		// Changing the project to the target location lets eclipse know where we want to move the project
		IProjectDescription projectDescription = ResourcesPlugin.getWorkspace().newProjectDescription(project.getName());
		projectDescription.setLocationURI(pathRepoLocal.toURI().resolve(project.getName()));
		project.move(projectDescription, true, monitor);
		
		// Stage and commit all changes
		doCommit(repo, "Initial commit to local repository", checkInMonitor.split(1));
		
		checkInMonitor.split(1).subTask("Mapping Repository to Project");
		// Connect Eclipse to the created (existing) Git repository
		// By associating the .git file of the new repository explicit with the project
		File pathRepoLocalGit = new File(pathRepoLocal.toURI().resolve(".git"));
		ConnectProviderOperation connectOperation = new ConnectProviderOperation(project, pathRepoLocalGit);
		connectOperation.execute(null);
	}
}
