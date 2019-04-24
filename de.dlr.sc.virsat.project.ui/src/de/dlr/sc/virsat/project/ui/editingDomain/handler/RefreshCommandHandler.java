/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.project.ui.editingDomain.handler;

import java.util.List;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRunnable;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import de.dlr.sc.virsat.project.Activator;
import de.dlr.sc.virsat.project.editingDomain.VirSatEditingDomainRegistry;
import de.dlr.sc.virsat.project.editingDomain.VirSatTransactionalEditingDomain;
import de.dlr.sc.virsat.project.structure.VirSatProjectCommons;

/**
 * Handler to execute a functionality on the transactional editing domain
 *  to trigger a reload of all resources
 * @author fisc_ph
 *
 */
public class RefreshCommandHandler extends AbstractHandler implements IHandler {
	
	@Override
	public boolean isEnabled() {
		IWorkspace workspace = ResourcesPlugin.getWorkspace();
		List<IProject> virSatProjects = VirSatProjectCommons.getAllVirSatProjects(workspace);
		return !virSatProjects.isEmpty();
	}

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		IWorkspaceRunnable runnable = new IWorkspaceRunnable() {
			@Override
			public void run(IProgressMonitor monitor) throws CoreException {
				// Try to get all VirSat projects and add them to the content
				IWorkspace workspace = ResourcesPlugin.getWorkspace();
				workspace.getRoot().refreshLocal(IResource.DEPTH_INFINITE, monitor);
				List<IProject> virSatProjects = VirSatProjectCommons.getAllVirSatProjects(workspace);
				virSatProjects.forEach((virSatProject) -> {
					VirSatTransactionalEditingDomain virSatEd = VirSatEditingDomainRegistry.INSTANCE.getEd(virSatProject);
					if (virSatEd != null) {
						virSatEd.reloadAll();
					}
 				});
			}
		};
		
		try {
			ResourcesPlugin.getWorkspace().run(runnable, null);
		} catch (CoreException e) {
			Activator.getDefault().getLog().log(new Status(IStatus.ERROR, Activator.getPluginId(), "Cannot refresh whole editing domain", e));
		}
		
		return null;
	}
}
