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
import org.eclipse.core.resources.IncrementalProjectBuilder;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
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
		SubMonitor subMonitor = SubMonitor.convert(monitor, selectedProjects.size() * 2);
		List<IStatus> status = new ArrayList<>();
		boolean autoBuildEnabled = isAutoBuildEnabled();
		
		// disable auto building to avoid file changes during the commit operation
		if (autoBuildEnabled) {
			setAutoBuild(false);
		}

		for (IProject project : selectedProjects) {
			try {
				// trigger a build to ensure a consistent data model
				project.build(IncrementalProjectBuilder.INCREMENTAL_BUILD, subMonitor.split(1));
				
				VirSatTransactionalEditingDomain ed = VirSatEditingDomainRegistry.INSTANCE.getEd(project);
				try {
					ed.runExclusive(() -> {
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
			} catch (CoreException e) {
				status.add(new Status(Status.ERROR, Activator.getPluginId(), "Error during build", e));
			}

		}
		if (!status.isEmpty()) {
			MultiStatus multiStatus = new MultiStatus(Activator.getPluginId(), Status.ERROR, status.toArray(new Status[] {}), "Errors during update", status.get(0).getException());
			StatusManager.getManager().handle(multiStatus, StatusManager.LOG | StatusManager.SHOW);
			return Status.CANCEL_STATUS;
		}
		
		if (autoBuildEnabled) {
			setAutoBuild(true);
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
	
	private static boolean isAutoBuildEnabled() {
		return ResourcesPlugin.getWorkspace().getDescription().isAutoBuilding();
	}
	
	/**
	 * Enables or disables auto building of the workspace.
	 */
	private static void setAutoBuild(boolean enable) {
		var workspace = ResourcesPlugin.getWorkspace();
		var desc = workspace.getDescription();

		desc.setAutoBuilding(enable);
		try {
			workspace.setDescription(desc);
		} catch (CoreException e) {
			Activator.getDefault().getLog().log(new Status(IStatus.ERROR, Activator.getPluginId(), e.getMessage()));
		}
	}

}
