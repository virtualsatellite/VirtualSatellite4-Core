/*******************************************************************************
 * Copyright (c) 2020 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.team.ui.handler.svn;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.team.svn.core.utility.FileUtility;
import org.eclipse.team.svn.ui.SVNTeamUIPlugin;
import org.eclipse.team.svn.ui.preferences.SVNTeamPreferences;
import org.eclipse.team.svn.ui.synchronize.SVNChangeSetCapability;

import de.dlr.sc.virsat.project.ui.Activator;
import de.dlr.sc.virsat.team.IVirSatVersionControlBackend;
import de.dlr.sc.virsat.team.svn.VirSatSvnVersionControlBackend;
import de.dlr.sc.virsat.team.ui.dialog.CommitMessageDialog;
import de.dlr.sc.virsat.team.ui.handler.AVersionControlCommitHandler;
import de.dlr.sc.virsat.team.ui.util.svn.VirSatSvnRevisionStatusUtil;

/**
 * 
 * This class performs an SVN Commit
 *
 */
public class SvnCommitHandler extends AVersionControlCommitHandler {

	@Override
	protected IVirSatVersionControlBackend createVersionControlBackend() {
		return new VirSatSvnVersionControlBackend();
	}
	
	@Override
	protected String getProposedComment() {
		return SVNChangeSetCapability.getProposedComment(selectedProjects.toArray(new IResource[0]));
	}
	
	@Override
	protected CommitMessageDialog createCommitMessageDialog() {
		CommitMessageDialog commitMessageDialog = super.createCommitMessageDialog();
		
		IPreferenceStore store = SVNTeamUIPlugin.instance().getPreferenceStore();
		String[] templates = FileUtility.decodeStringToArray(SVNTeamPreferences.getCommentTemplatesString(store, SVNTeamPreferences.COMMENT_TEMPLATES_LIST_NAME));
		commitMessageDialog.setTemplates(templates);
		
		return commitMessageDialog;
	}
	
	@Override
	protected void doCommit(IProject project, String message, IProgressMonitor monitor) throws Exception {
		Activator.getDefault().getLog().log(new Status(IStatus.INFO, Activator.getPluginId(), "SVN: Before commit on:\n"
				+ new VirSatSvnRevisionStatusUtil().getWorkspaceChangedStatus(project)));
		
		super.doCommit(project, message, monitor);
		
		Activator.getDefault().getLog().log(new Status(IStatus.INFO, Activator.getPluginId(), "SVN: After commit on:\n"
				+ new VirSatSvnRevisionStatusUtil().getWorkspaceChangedStatus(project)));
	}
	
}
