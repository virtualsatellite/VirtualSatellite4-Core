package de.dlr.sc.virsat.team.ui.git.action;

import java.util.Collection;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.egit.core.Activator;
import org.eclipse.egit.core.internal.indexdiff.IndexDiffCacheEntry;
import org.eclipse.egit.core.internal.indexdiff.IndexDiffData;
import org.eclipse.egit.core.op.AddToIndexOperation;
import org.eclipse.egit.core.op.CommitOperation;
import org.eclipse.egit.core.project.RepositoryMapping;
import org.eclipse.egit.ui.internal.credentials.EGitCredentialsProvider;
import org.eclipse.egit.ui.internal.push.PushOperationUI;
import org.eclipse.egit.ui.internal.push.SimpleConfigurePushDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.window.Window;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.transport.RemoteConfig;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.handlers.HandlerUtil;

import de.dlr.sc.virsat.project.ui.navigator.util.VirSatSelectionHelper;
import de.dlr.sc.virsat.svn.ui.dialog.CommitMessageDialog;

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
					return null;
				}
				
        		// Add all changed files to index
	        	AddToIndexOperation addToIndexOperation = new AddToIndexOperation(changedResources);
	        	addToIndexOperation.execute(new NullProgressMonitor());
	        	
	        	// Commit all changes
				CommitOperation commitOperation = new CommitOperation(gitRepository, "author {author_name} <{author_email}>", "author {author_name} <{author_email}>", commitMessageDialog.getCommitMessage());
				commitOperation.setCommitAll(true);
				commitOperation.execute(new NullProgressMonitor());
			}
			
			// Push commits to remote
			RemoteConfig rc = SimpleConfigurePushDialog.getConfiguredRemote(gitRepository);
			PushOperationUI push = new PushOperationUI(gitRepository, rc.getName(), false);
			push.setCredentialsProvider(new EGitCredentialsProvider());
			push.start();
		} catch (CoreException e) {
			e.printStackTrace();
		} 
		 
		return null;
	}
}
