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
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.jobs.Job;

import de.dlr.sc.virsat.team.IVirSatVersionControlBackend;
import de.dlr.sc.virsat.team.ui.util.svn.VersionControlJob;

public abstract class AVersionControlUpdateHandler extends AVersionControlHandler {

	protected abstract IVirSatVersionControlBackend createVersionControlBackend();
	
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		
		super.execute(event);
		
		Job job = new VersionControlJob("Virtual Satellite Update", selectedProjects) {
			
			@Override
			protected void executeBackendOperation(IProject project, IProgressMonitor monitor) throws Exception {
				backend.update(project, monitor);
			}
		};
		
		job.schedule();
		
		return null;
	}

}
