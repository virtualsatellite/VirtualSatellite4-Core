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
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;

import de.dlr.sc.virsat.project.ui.Activator;
import de.dlr.sc.virsat.team.IVirSatVersionControlBackend;
import de.dlr.sc.virsat.team.svn.VirSatSvnVersionControlBackend;
import de.dlr.sc.virsat.team.ui.handler.AVersionControlUpdateHandler;
import de.dlr.sc.virsat.team.ui.util.svn.VirSatSvnRevisionStatusUtil;

/**
 * This class performs an SVN update
 *
 */
public class SvnUpdateHandler extends AVersionControlUpdateHandler {

	@Override
	protected IVirSatVersionControlBackend createVersionControlBackend() {
		return new VirSatSvnVersionControlBackend();
	}
	
	@Override
	protected void doUpdate(IProject project, IProgressMonitor monitor) throws Exception {
		Activator.getDefault().getLog().log(new Status(IStatus.INFO, Activator.getPluginId(), "SVN: Before update on:\n"
				+ new VirSatSvnRevisionStatusUtil().getWorkspaceChangedStatus(project)));
		
		super.doUpdate(project, monitor);
		
		Activator.getDefault().getLog().log(new Status(IStatus.INFO, Activator.getPluginId(), "SVN: After update on:\n"
				+ new VirSatSvnRevisionStatusUtil().getWorkspaceChangedStatus(project)));
	}
}
