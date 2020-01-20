/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.team.ui.action.svn;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.internal.resources.Resource;
import org.eclipse.core.internal.resources.mapping.SimpleResourceMapping;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.team.svn.core.operation.CompositeOperation;
import org.eclipse.team.svn.core.utility.FileUtility;
import org.eclipse.team.svn.core.utility.SVNUtility;
import org.eclipse.team.svn.ui.SVNTeamUIPlugin;
import org.eclipse.team.svn.ui.SVNUIMessages;
import org.eclipse.team.svn.ui.action.local.CommitAction;
import org.eclipse.team.svn.ui.dialog.TagModifyWarningDialog;
import org.eclipse.team.svn.ui.preferences.SVNTeamPreferences;
import org.eclipse.team.svn.ui.synchronize.SVNChangeSetCapability;
import org.eclipse.team.svn.ui.utility.CommitActionUtility;
import org.eclipse.ui.handlers.HandlerUtil;

import de.dlr.sc.virsat.project.editingDomain.VirSatTransactionalEditingDomain;
import de.dlr.sc.virsat.project.ui.Activator;
import de.dlr.sc.virsat.project.ui.navigator.util.VirSatSelectionHelper;
import de.dlr.sc.virsat.team.ui.dialog.CommitMessageDialog;
import de.dlr.sc.virsat.team.ui.util.svn.VirSatSvnRevisionStatusUtil;

/**
 * a class to define the commit action
 * @author scha_vo
 *
 */

@SuppressWarnings("restriction")
public class SVNCommitAction extends CommitAction {

	@Override
	public void selectionChanged(IAction action, ISelection selection) {
		super.selectionChanged(action, newStructuredSelection);
	}
	
	private IStructuredSelection newStructuredSelection;
	
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		ISelection eventSelection = HandlerUtil.getCurrentSelection(event);
		VirSatSelectionHelper selectionHelper = new VirSatSelectionHelper(eventSelection);
		IProject selectedProject = selectionHelper.getProjectResource();
		VirSatTransactionalEditingDomain ed = selectionHelper.getEditingDomain();

		try {
			ResourcesPlugin.getWorkspace().run((monitor) -> {
				// Store it through the editing domain so it keeps track of further
				// further changes. This is important to have exact knowledge about the
				// resources dirty state.
				ed.saveAll();
				ed.getCommandStack().flush();
					
				// Call the proper SVN commandHandler/action from Subversive
				if (selectedProject != null) {
					newStructuredSelection = new StructuredSelection(new SimpleResourceMapping(selectedProject));
					selectionChanged(null, newStructuredSelection);
					checkSelection(newStructuredSelection);
					try {
						Activator.getDefault().getLog()
								.log(new Status(IStatus.INFO, Activator.getPluginId(),
										"SVN: Before commit on:\n" + new VirSatSvnRevisionStatusUtil()
												.getWorkspaceChangedStatus(selectedProject)));

						super.execute(event);

						Activator.getDefault().getLog()
								.log(new Status(IStatus.INFO, Activator.getPluginId(),
										"SVN: After commit on:\n" + new VirSatSvnRevisionStatusUtil()
												.getWorkspaceChangedStatus(selectedProject)));
					} catch (Exception e) {
						Activator.getDefault().getLog().log(new Status(Status.ERROR, Activator.getPluginId(),
								"SVN: Failed to execute call to SVN in Custom Commit! " + e.getMessage()));
					}
				}
			}, ResourcesPlugin.getWorkspace().getRoot(), IWorkspace.AVOID_UPDATE, null);
		} catch (CoreException e) {
			Activator.getDefault().getLog().log(new Status(Status.ERROR, Activator.getPluginId(), "Failed to execute Custom Commit! " + e.getMessage()));
		}
	
		return null;
	}
	
	@Override
	public void runImpl(IAction action) {
		CommitActionUtility commitUtility = new CommitActionUtility(this);
        IResource [] allResources = commitUtility.getAllResources();
        
        IProject[] tagOperatedProjects = SVNUtility.getTagOperatedProjects(allResources);
        if (tagOperatedProjects.length != 0) {
        	TagModifyWarningDialog dlg = new TagModifyWarningDialog(this.getShell(), tagOperatedProjects);
        	if (dlg.open() != 0) {
        		return;
        	}
        }
	    String proposedComment = SVNChangeSetCapability.getProposedComment(commitUtility.getAllResources());

        // compared to the original code here, we have replaced the CommitPanel with a very simple dialog just asking for the comment
	    // all other settings use a default value
	    CommitMessageDialog commitMessageDialog = new CommitMessageDialog(this.getShell(), SVNUIMessages.CommitPanel_Title, "Please enter a commit message describing your changes", proposedComment);
        
	    IPreferenceStore store = SVNTeamUIPlugin.instance().getPreferenceStore();
	    String[] templates = FileUtility.decodeStringToArray(SVNTeamPreferences.getCommentTemplatesString(store, SVNTeamPreferences.COMMENT_TEMPLATES_LIST_NAME));
	    commitMessageDialog.setTemplates(templates);
		
        if (commitMessageDialog.open() == 0) {
        	//we trick the commit operation with provide twice an empty array of resources for the nonSelectedResources and the treatAsEdit entry
        	IResource[] emptyResourcesArray = new Resource[0];
        	CompositeOperation op = commitUtility.getCompositeCommitOperation(this.getSelectedResources(), emptyResourcesArray, emptyResourcesArray, commitMessageDialog.getCommitMessage(), false, this.getShell(), this.getTargetPart(), true);			

        	this.runScheduled(op);
			
		}
	}
}
