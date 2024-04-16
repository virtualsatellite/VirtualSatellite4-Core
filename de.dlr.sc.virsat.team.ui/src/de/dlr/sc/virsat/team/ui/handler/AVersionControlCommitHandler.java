/*******************************************************************************
 * Copyright (c) 2020 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.team.ui.handler;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Display;

import de.dlr.sc.virsat.team.ui.dialog.CommitMessageDialog;
import de.dlr.sc.virsat.team.ui.util.VersionControlJob;

/**
 * This class handles common logic for performing a commit with some backend.
 *
 */
public abstract class AVersionControlCommitHandler extends AVersionControlHandler {
	
	public static final int NUMBEROFWORK = 3;
	/**
	 * Override this method to provide some default proposed comment in the commit message dialog.
	 * The default implementation just gives the empty string.
	 * @return
	 */
	protected String getProposedComment() {
		return "";
	}
	
	/**
	 * Creates the default commit message dialog.
	 * Override for customization.
	 * @return
	 */
	protected CommitMessageDialog createCommitMessageDialog() {
		return new CommitMessageDialog(Display.getDefault().getActiveShell(),
				"Commit Message", "Please enter a commit message describing your changes", getProposedComment());
	}
	
	
	protected void doCommit(IProject project, String message, IProgressMonitor monitor) throws Exception {
		backend.commit(project, message, monitor);
	}
	
	
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		super.execute(event);
		
		CommitMessageDialog commitMessageDialog = createCommitMessageDialog();

		if (commitMessageDialog.open() != Window.OK) {
			// Commit canceled
			return null;
		}

		Job job = new VersionControlJob("Virtual Satellite Commit", selectedProjects) {
			
			@Override
			protected void executeBackendOperation(IProject project, IProgressMonitor monitor) throws Exception {
				SubMonitor subMonitor = SubMonitor.convert(monitor, NUMBEROFWORK);
			    
			    doUpdate(project, subMonitor.split(1));
			    doCommit(project, commitMessageDialog.getCommitMessage(), subMonitor.split(1));
			    project.refreshLocal(IResource.DEPTH_INFINITE, subMonitor.split(1));
			}
		};
		
		job.schedule();
		
		return null;
	}

	protected void doUpdate(IProject project, IProgressMonitor monitor) throws Exception {
        backend.update(project, monitor);
        
    }
}
