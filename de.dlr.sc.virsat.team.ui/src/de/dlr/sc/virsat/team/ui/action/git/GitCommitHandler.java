/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.team.ui.action.git;

import java.util.Set;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.egit.ui.internal.credentials.EGitCredentialsProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.handlers.HandlerUtil;

import de.dlr.sc.virsat.project.editingDomain.VirSatEditingDomainRegistry;
import de.dlr.sc.virsat.project.editingDomain.VirSatTransactionalEditingDomain;
import de.dlr.sc.virsat.project.ui.navigator.util.VirSatSelectionHelper;
import de.dlr.sc.virsat.team.IVirSatVersionControlBackend;
import de.dlr.sc.virsat.team.git.VirSatGitVersionControlBackend;
import de.dlr.sc.virsat.team.ui.dialog.CommitMessageDialog;
import de.dlr.sc.virsat.team.ui.util.svn.VersionControlJob;

/**
 * This class performs a git commit
 */
@SuppressWarnings("restriction")
public class GitCommitHandler extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		CommitMessageDialog commitMessageDialog = new CommitMessageDialog(Display.getDefault().getActiveShell(),
				"Commit Message", "Please enter a commit message describing your changes", "");

		if (commitMessageDialog.open() != Window.OK) {
			// Commit canceled
			return null;
		}

		IStructuredSelection selection = HandlerUtil.getCurrentStructuredSelection(event);		
		
		VirSatSelectionHelper selectionHelper = new VirSatSelectionHelper(selection);
		Set<IProject> selectedProjects = selectionHelper.getAllProjectResouces();
		
		for (IProject project : selectedProjects) {
			VirSatTransactionalEditingDomain ed = VirSatEditingDomainRegistry.INSTANCE.getEd(project);
			ed.saveAll();
		}
		
		IVirSatVersionControlBackend gitBackend = new VirSatGitVersionControlBackend(new EGitCredentialsProvider());

		Job job = new VersionControlJob("Virtual Satellite Git Commit", selectedProjects) {
			
			@Override
			protected void executeBackendOperation(IProject project, IProgressMonitor monitor) throws Exception {
				gitBackend.commit(project, commitMessageDialog.getCommitMessage(), monitor);
			}
		};
		
		job.schedule();
		
		return null;
	}

}
