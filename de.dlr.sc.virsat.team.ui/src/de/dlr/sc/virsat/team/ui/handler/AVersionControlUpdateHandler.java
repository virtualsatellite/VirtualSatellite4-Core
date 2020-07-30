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

import de.dlr.sc.virsat.team.ui.util.VersionControlJob;

/**
 * 
 * This class handles common logic for performing an update with some backend
 *
 */
public abstract class AVersionControlUpdateHandler extends AVersionControlHandler {
	
	protected void doUpdate(IProject project, IProgressMonitor monitor) throws Exception {
		backend.update(project, monitor);
	}
	
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		
		super.execute(event);
		
		Job job = new VersionControlJob("Virtual Satellite Update", selectedProjects) {
			
			@Override
			protected void executeBackendOperation(IProject project, IProgressMonitor monitor) throws Exception {
				SubMonitor subMonitor = SubMonitor.convert(monitor, 2);
				doUpdate(project, subMonitor.split(1));
				project.refreshLocal(IResource.DEPTH_INFINITE, subMonitor.split(1));
			}
		};
		
		job.schedule();
		
		return null;
	}

}
