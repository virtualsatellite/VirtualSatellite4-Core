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

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;

import de.dlr.sc.virsat.project.ui.Activator;
import de.dlr.sc.virsat.team.IVirSatVersionControlBackend;
import de.dlr.sc.virsat.team.svn.VirSatSvnVersionControlBackend;
import de.dlr.sc.virsat.team.ui.handler.AVersionControlCommitHandler;
import de.dlr.sc.virsat.team.ui.util.svn.VirSatSvnRevisionStatusUtil;

public class SvnCommitHandler extends AVersionControlCommitHandler {

	@Override
	protected IVirSatVersionControlBackend createVersionControlBackend() {
		return new VirSatSvnVersionControlBackend();
	}

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		try {
			for (IProject selectedProject : selectedProjects) {
				Activator.getDefault().getLog().log(new Status(IStatus.INFO, Activator.getPluginId(), "SVN: Before commit on:\n"
								+ new VirSatSvnRevisionStatusUtil().getWorkspaceChangedStatus(selectedProject)));
			}

			super.execute(event);

			for (IProject selectedProject : selectedProjects) {
				Activator.getDefault().getLog().log(new Status(IStatus.INFO, Activator.getPluginId(), "SVN: After commit on:\n"
								+ new VirSatSvnRevisionStatusUtil().getWorkspaceChangedStatus(selectedProject)));
			}
		} catch (CoreException e) {
			Activator.getDefault().getLog().log(new Status(Status.ERROR, Activator.getPluginId(), "SVN commit failed", e));
		}

		return null;
	}
	
}
