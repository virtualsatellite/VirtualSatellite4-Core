/*******************************************************************************
 * Copyright (c) 2020 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
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
import org.eclipse.egit.core.project.RepositoryMapping;
import org.eclipse.egit.ui.internal.credentials.EGitCredentialsProvider;
import org.eclipse.egit.ui.internal.pull.PullResultDialog;
import org.eclipse.jgit.api.PullResult;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.swt.widgets.Display;

import de.dlr.sc.virsat.team.IVirSatVersionControlBackend;
import de.dlr.sc.virsat.team.git.VirSatGitVersionControlBackend;
import de.dlr.sc.virsat.team.ui.handler.AVersionControlUpdateHandler;


/**
 * This class performs a git update
 */
@SuppressWarnings("restriction")
public class GitUpdateHandler extends AVersionControlUpdateHandler {
	
	@Override
	protected IVirSatVersionControlBackend createVersionControlBackend() {
		return new VirSatGitVersionControlBackend(new EGitCredentialsProvider());
	}

	@Override
	protected void doUpdate(IProject project, IProgressMonitor monitor) throws Exception {
		super.doUpdate(project, monitor);
		
		VirSatGitVersionControlBackend gitBackend = (VirSatGitVersionControlBackend) backend;
		PullResult pullResult = gitBackend.getLastPullResult();
		
		Repository gitRepository = RepositoryMapping.getMapping(project).getRepository();

		// Build the dialog in the UI thread
		Display.getDefault().asyncExec(() -> {
			PullResultDialog pullResultDialog = 
					new PullResultDialog(Display.getDefault().getActiveShell(), gitRepository, pullResult);
			pullResultDialog.open();
		});
	}
}
