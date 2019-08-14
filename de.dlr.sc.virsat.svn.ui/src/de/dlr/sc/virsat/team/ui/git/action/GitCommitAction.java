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

import java.io.ByteArrayInputStream;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceVisitor;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.egit.core.Activator;
import org.eclipse.egit.core.internal.indexdiff.IndexDiffCache;
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

import de.dlr.sc.virsat.project.editingDomain.VirSatTransactionalEditingDomain;
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
		
		// Save all in-memory changes
		VirSatTransactionalEditingDomain ed = selectionHelper.getEditingDomain();
		ed.saveAll();
		ed.getCommandStack().flush();
		
		// Grab the git index
		Repository gitRepository = RepositoryMapping.getMapping(selectedProject).getRepository();
    	IndexDiffCache diffCache = Activator.getDefault().getIndexDiffCache();
		IndexDiffCacheEntry diffCacheEntry = diffCache.getIndexDiffCacheEntry(gitRepository);
    	IndexDiffData indexDiff = diffCacheEntry.getIndexDiff();
		
    	try {
    		createEmptyObjectsForEmptyFolders(indexDiff.getUntrackedFolders());
	    	
			// Make sure the index is up-to date
			Job refreshJob = diffCacheEntry.createRefreshResourcesAndIndexDiffJob();
			refreshJob.schedule();
			refreshJob.join();
			
			// Re-get the index to get all updates of the refresh
			indexDiff = diffCacheEntry.getIndexDiff();
			
			// Check if there are local changes. If so, then we need to commit all changes and then push them. 
			// If not, we only need to push.
			if (indexDiff.hasChanges()) {
				CommitMessageDialog commitMessageDialog = new CommitMessageDialog(Display.getDefault().getActiveShell(), 
						 "Commit Message", "Please enter a commit message describing your changes", "");
				
				int status = commitMessageDialog.open();
				if (status != Window.OK) {
					// Commit canceled
					return null;
				}
				
				gitTrackFiles(indexDiff.getUntracked());
				gitCommit(gitRepository, commitMessageDialog.getCommitMessage());
			}
			
			// Push commits to remote
			gitPush(gitRepository);
		} catch (CoreException | InterruptedException e) {
			Status status = new Status(Status.ERROR, Activator.getPluginId(), "Failed to execute Git Commit!", e);
			StatusManager.getManager().handle(status, StatusManager.LOG | StatusManager.SHOW);
		} 
		 
		return null;
	}
	
	/**
	 * Creates .empty files within all untracked empty folders.
	 * To find the empty folders we traverse the file tree of all untracked folders.
	 * @param untrackedFolders the folders not tracked by git
	 * @throws CoreException 
	 */
	private void createEmptyObjectsForEmptyFolders(Set<String> untrackedFolders) throws CoreException {
    	for (String untrackedFolder : untrackedFolders) {
			IFolder folder = ResourcesPlugin.getWorkspace().getRoot().getFolder(new Path(untrackedFolder));
			if (folder.exists()) {
				folder.accept(new IResourceVisitor() {
					@Override
					public boolean visit(IResource resource) throws CoreException {
						if (resource instanceof IFolder) {
							IFolder subFolder = (IFolder) resource;
							boolean isEmptyFolder = subFolder.members().length == 0;
		    				if (isEmptyFolder) {
		    					IFile emptyFile = subFolder.getFile(".empty");
			    				emptyFile.create(new ByteArrayInputStream(new byte[0]), IResource.HIDDEN, null);
		    				}
						}
						return true;
					}
				});
			}
    	}
	}
	
	/**
	 * Tells git to track all passed file paths by adding them to the git index
	 * @param filePaths the paths of all files to be tracked
	 * @throws CoreException 
	 */
	private void gitTrackFiles(Set<String> filePaths) throws CoreException {
		Set<IResource> resources = new HashSet<>();
		for (String filePath : filePaths) {
			IResource resource = ResourcesPlugin.getWorkspace().getRoot().getFile(new Path(filePath));
			resources.add(resource);
		}
    	AddToIndexOperation addToIndexOperation = new AddToIndexOperation(resources);
    	addToIndexOperation.execute(new NullProgressMonitor());
	}
	
	/**
	 * Performs a git commit
	 * @param gitRepository the repository to commit
	 * @param commitMessage the commit message
	 * @throws CoreException 
	 */
	private void gitCommit(Repository gitRepository, String commitMessage) throws CoreException {
		CommitHelper commitHelper = new CommitHelper(gitRepository);
    	CommitOperation commitOperation = new CommitOperation(gitRepository, commitHelper.getAuthor(), commitHelper.getCommitter(), commitMessage);
		commitOperation.setCommitAll(true);
		commitOperation.execute(new NullProgressMonitor());
	}
	
	/**
	 * Performs a git push
	 * @param gitRepository the repository to push
	 */
	private void gitPush(Repository gitRepository) {
		RemoteConfig rc = SimpleConfigurePushDialog.getConfiguredRemote(gitRepository);
		PushOperationUI push = new PushOperationUI(gitRepository, rc.getName(), false);
		push.setCredentialsProvider(new EGitCredentialsProvider());
		push.start();
	}
}
