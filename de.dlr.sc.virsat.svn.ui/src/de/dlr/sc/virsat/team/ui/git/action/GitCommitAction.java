/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.team.ui.git.action;

import java.util.Collection;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.egit.core.Activator;
import org.eclipse.egit.core.internal.indexdiff.IndexDiffCacheEntry;
import org.eclipse.egit.core.internal.indexdiff.IndexDiffData;
import org.eclipse.egit.core.op.AddToIndexOperation;
import org.eclipse.egit.core.op.CommitOperation;
import org.eclipse.egit.core.project.RepositoryMapping;
import org.eclipse.egit.ui.internal.commit.CommitHelper;
import org.eclipse.egit.ui.internal.credentials.EGitCredentialsProvider;
import org.eclipse.egit.ui.internal.push.PushOperationUI;
import org.eclipse.egit.ui.internal.push.SimpleConfigurePushDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.window.Window;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.transport.RemoteConfig;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.ui.statushandlers.StatusManager;

import de.dlr.sc.virsat.project.ui.navigator.util.VirSatSelectionHelper;
import de.dlr.sc.virsat.svn.ui.dialog.CommitMessageDialog;

/**
 * This class performs a git commit + push.
 * The commit is only performed if there are unstaged changes.
 */
@SuppressWarnings("restriction")
public class GitCommitAction extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		ISelection eventSelection = HandlerUtil.getCurrentSelection(event);
		VirSatSelectionHelper selectionHelper = new VirSatSelectionHelper(eventSelection);
		IProject selectedProject = selectionHelper.getProjectResource();
		
		Repository gitRepository = RepositoryMapping.getMapping(selectedProject).getRepository();
    	IndexDiffCacheEntry diffCacheEntry = Activator.getDefault()
				.getIndexDiffCache().getIndexDiffCacheEntry(gitRepository);
    	IndexDiffData indexDiff = diffCacheEntry.getIndexDiff();
    	Collection<IResource> changedResources = indexDiff.getChangedResources();
		
		try {
			// Check if there are local changes. If so, then we need to commit all changes and then push them. 
			// If not, we only need to push.
			if (!changedResources.isEmpty()) {
				CommitMessageDialog commitMessageDialog = new CommitMessageDialog(Display.getDefault().getActiveShell(), 
						 "Commit Message", "Please enter a commit message describing your changes", "");
				
				int status = commitMessageDialog.open();
				if (status != Window.OK) {
					// Commit canceled
					return null;
				}
				
        		// Add all changed files to index
	        	AddToIndexOperation addToIndexOperation = new AddToIndexOperation(changedResources);
	        	addToIndexOperation.execute(new NullProgressMonitor());
	        	
	        	// Commit all changes
	        	CommitHelper commitHelper = new CommitHelper(gitRepository);
	        	CommitOperation commitOperation = new CommitOperation(gitRepository, commitHelper.getAuthor(), commitHelper.getCommitter(), commitMessageDialog.getCommitMessage());
				commitOperation.setCommitAll(true);
				commitOperation.execute(new NullProgressMonitor());
			}
			
			// Push commits to remote
			RemoteConfig rc = SimpleConfigurePushDialog.getConfiguredRemote(gitRepository);
			PushOperationUI push = new PushOperationUI(gitRepository, rc.getName(), false);
			push.setCredentialsProvider(new EGitCredentialsProvider());
			push.start();
		} catch (CoreException e) {
			Status status = new Status(Status.ERROR, Activator.getPluginId(), "Failed to execute Git Commit! " + e.getMessage());
			StatusManager.getManager().handle(status, StatusManager.LOG | StatusManager.SHOW);
		} 
		 
		return null;
	}
}
