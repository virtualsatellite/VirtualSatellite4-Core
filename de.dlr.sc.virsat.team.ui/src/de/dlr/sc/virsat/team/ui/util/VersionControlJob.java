/*******************************************************************************
 * Copyright (c) 2020 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.team.ui.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.ui.statushandlers.StatusManager;

import de.dlr.sc.virsat.project.editingDomain.VirSatEditingDomainRegistry;
import de.dlr.sc.virsat.project.editingDomain.VirSatTransactionalEditingDomain;
import de.dlr.sc.virsat.team.ui.Activator;

/**
 * This job handles preparatory work for the execution of some version control action
 * and deals with error handling.
 */
public abstract class VersionControlJob extends Job {

	private Set<IProject> selectedProjects;
	
	public VersionControlJob(String name, Set<IProject> selectedProjects) {
		super(name);
		this.selectedProjects = selectedProjects;
		setUser(true);
	}

	@Override
	protected IStatus run(IProgressMonitor monitor) {
		SubMonitor subMonitor = SubMonitor.convert(monitor, selectedProjects.size());
		List<IStatus> status = new ArrayList<>();
		
		for (IProject project : selectedProjects) {
			VirSatTransactionalEditingDomain ed = VirSatEditingDomainRegistry.INSTANCE.getEd(project);
			try {
				ed.writeExclusive(() -> {
					ed.getCommandStack().flush();
					try {
						executeBackendOperation(project, subMonitor.split(1));
					} catch (Exception e) {
						status.add(new Status(Status.ERROR, Activator.getPluginId(), "Error during update", e));
					}
				});
			} catch (InterruptedException e) {
				status.add(new Status(Status.ERROR, Activator.getPluginId(), "Transaction interruption during update", e));
			}
		}
		if (!status.isEmpty()) {
			MultiStatus multiStatus = new MultiStatus(Activator.getPluginId(), Status.ERROR, status.toArray(new Status[] {}), "Errors during update", status.get(0).getException());
			StatusManager.getManager().handle(multiStatus, StatusManager.LOG | StatusManager.SHOW);
			return Status.CANCEL_STATUS;
		}
		return Status.OK_STATUS;
	}
	
	/**
	 * Override to apply the actual backend operation
	 * @param project the project to operate on
	 * @param monitor a progress monitor
	 * @throws Exception
	 */
	protected abstract void executeBackendOperation(IProject project, IProgressMonitor monitor) throws Exception;

}
