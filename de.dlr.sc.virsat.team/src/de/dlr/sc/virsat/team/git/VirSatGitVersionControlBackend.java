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
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.egit.core.EclipseGitProgressTransformer;
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
	
	public static final int PROGRESS_INDEX_COMMIT_PUSH_STEPS = 3;
	
	public static final int GIT_REMOTE_TIMEOUT = 30;
	
	@Override
	public void commit(IProject project, String message, IProgressMonitor monitor) throws Exception {
		SubMonitor pushAndCommitMonitor = SubMonitor.convert(monitor, "Virtual Satellite git push and commit", PROGRESS_INDEX_COMMIT_PUSH_STEPS);
		
		Repository gitRepository = RepositoryMapping.getMapping(project).getRepository();
		
		pushAndCommitMonitor.split(1).subTask("adding new files to index");
		Git.wrap(gitRepository).add()
			.addFilepattern(".")
			.call();		
		
		pushAndCommitMonitor.split(1).subTask("commiting files");
		Git.wrap(gitRepository).commit()
			.setAll(true)
			.setMessage(message)
			.call();

		ProgressMonitor gitPushMonitor = new EclipseGitProgressTransformer(pushAndCommitMonitor.split(1));
		Git.wrap(gitRepository).push()
			.setCredentialsProvider(credentialsProvider)
			.setProgressMonitor(gitPushMonitor)
			.setTimeout(GIT_REMOTE_TIMEOUT)
			.call();
	}

	@Override
	public void update(IProject project,  IProgressMonitor monitor) throws Exception {
		Repository gitRepository = RepositoryMapping.getMapping(project).getRepository();
	
		ProgressMonitor gitMonitor = new EclipseGitProgressTransformer(monitor);
		
		Git.wrap(gitRepository).pull()
			.setCredentialsProvider(credentialsProvider)
			.setProgressMonitor(gitMonitor)
			.setTimeout(GIT_REMOTE_TIMEOUT)
			.setStrategy(MergeStrategy.THEIRS)
			.call();
	}

	@Override
	public void checkout(IProjectDescription projectDescription, String remoteUri, IProgressMonitor monitor) throws Exception {
		File pathRepoLocal = new File(projectDescription.getLocationURI());
		
		Git.cloneRepository()
			.setCredentialsProvider(credentialsProvider)
			.setURI(remoteUri)
			.setDirectory(pathRepoLocal)
			.call();
	}

	@Override
	public void checkin(IProject project, String uri, IProgressMonitor monitor) throws Exception {
	}

}
