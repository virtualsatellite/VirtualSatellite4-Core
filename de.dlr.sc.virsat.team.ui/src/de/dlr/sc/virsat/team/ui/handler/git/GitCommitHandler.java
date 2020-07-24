/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.team.ui.handler.git;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.egit.core.op.PushOperationResult;
import org.eclipse.egit.core.project.RepositoryMapping;
import org.eclipse.egit.ui.internal.credentials.EGitCredentialsProvider;
import org.eclipse.egit.ui.internal.push.PushMode;
import org.eclipse.egit.ui.internal.push.ShowPushResultAction;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.transport.PushResult;
import org.eclipse.swt.widgets.Display;

import de.dlr.sc.virsat.team.IVirSatVersionControlBackend;
import de.dlr.sc.virsat.team.git.VirSatGitVersionControlBackend;
import de.dlr.sc.virsat.team.ui.handler.AVersionControlCommitHandler;

/**
 * This class performs a git commit
 */
@SuppressWarnings("restriction")
public class GitCommitHandler extends AVersionControlCommitHandler {

	@Override
	protected IVirSatVersionControlBackend createVersionControlBackend() {
		return new VirSatGitVersionControlBackend(new EGitCredentialsProvider());
	}
	
	@Override
	protected void doCommit(IProject project, String message, IProgressMonitor monitor) throws Exception {
		super.doCommit(project, message, monitor);
		
		VirSatGitVersionControlBackend gitBackend = (VirSatGitVersionControlBackend) backend;
		
		// Create an interim push operation result object for passing on to egit
		PushOperationResult pushOperationResult = new PushOperationResult();
		Iterable<PushResult> lastPushResults = gitBackend.getLastPushResults();
		for (PushResult pushResult : lastPushResults) {
			pushOperationResult.addOperationResult(pushResult.getURI(), pushResult);
		}
		
		// Create the actual egit dialog for showing push results
		Repository gitRepository = RepositoryMapping.getMapping(project).getRepository();
		String destination = lastPushResults.iterator().next().getURI().toString();
		ShowPushResultAction showPushResultAction = 
				new ShowPushResultAction(gitRepository, pushOperationResult, destination, false, PushMode.UPSTREAM);
		
		// Run it in the display thread since we will be showing UI
		Display.getDefault().asyncExec(() -> showPushResultAction.run());
	}
}
