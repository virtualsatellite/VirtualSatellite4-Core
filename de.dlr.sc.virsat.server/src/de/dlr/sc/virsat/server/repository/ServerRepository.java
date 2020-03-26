/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.server.repository;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.IWorkspaceRunnable;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;

import de.dlr.sc.virsat.project.editingDomain.VirSatEditingDomainRegistry;
import de.dlr.sc.virsat.project.editingDomain.VirSatTransactionalEditingDomain;
import de.dlr.sc.virsat.project.resources.VirSatResourceSet;
import de.dlr.sc.virsat.project.structure.VirSatProjectCommons;
import de.dlr.sc.virsat.server.Activator;
import de.dlr.sc.virsat.server.configuration.RepositoryConfiguration;

/**
 * Entry point to the eclipse project
 */
public class ServerRepository {
	
	private RepositoryConfiguration repositoryConfiguration;
	private IProject project;
	private VirSatResourceSet resourceSet;
	private VirSatTransactionalEditingDomain ed;
	
	public ServerRepository(RepositoryConfiguration repositoryConfiguration) {
		this.repositoryConfiguration = repositoryConfiguration;
		
		//checkout the project to workspace
	}
	
	public RepositoryConfiguration getRepositoryConfiguration() {
		return repositoryConfiguration;
	}

	public void updateOrCheckoutProject() {
		retrieveProjectFromConfiguration();
		
		// Depending on if the project exists or not either
		// Check-it out from the configuration or maybe just update it
		if (project.exists()) {
			updateProject();
		} else {
			createNewProjectByCheckout();
		}
	}
	
	public void retrieveProjectFromConfiguration() {
		String projectName = repositoryConfiguration.getProjectName();
		project = ResourcesPlugin.getWorkspace().getRoot().getProject(projectName);
	}
	
	
	public void retrieveEdAndResurceSetFromConfiguration() {
		retrieveProjectFromConfiguration();
		
		resourceSet = VirSatResourceSet.getResourceSet(project);
		ed = VirSatEditingDomainRegistry.INSTANCE.getEd(resourceSet);
	}
	
	public void createNewProjectAndCheckIn() {
		runInWorkspace((progress) -> {
			// Create a new project
			project.create(progress);
			
			// Do the Virtual Satellite Specific Settings
			VirSatProjectCommons.createNewProjectRunnable(project).run(progress);
		
			// TODO: Checkin Project
			
			retrieveEdAndResurceSetFromConfiguration();
		});	
	}
	
	public void createNewProjectByCheckout() {
		runInWorkspace((progress) -> {
			// TODO: Call backend for checkout
			
			retrieveProjectFromConfiguration();
			retrieveEdAndResurceSetFromConfiguration();
		});	
	}
	
	public void updateProject() {
		runInWorkspace((progress) -> {
			// TODO: Call BackEnd for Update
			
		});
	}
	
	public void removeProject() {
		runInWorkspace((progress) -> {
			project.delete(true, true, progress);
			
			project = null;
			resourceSet = null;
			ed = null;
		});
	}
	
	private void runInWorkspace(IWorkspaceRunnable runnable) {
		IWorkspaceRoot wsRoot = ResourcesPlugin.getWorkspace().getRoot();
		try {
			ResourcesPlugin.getWorkspace().run(runnable, wsRoot, 0, null);
		} catch (CoreException e) {
			Activator.getDefault().getLog().log(
				new Status(
					IStatus.ERROR,
					Activator.getPluginId(),
					"Server Repository: Failed to execute runnable",
					e
				)
			);
		}
	}

	public IProject getProject() {
		return project;
	}

	public VirSatResourceSet getResourceSet() {
		return resourceSet;
	}

	public VirSatTransactionalEditingDomain getEd() {
		return ed;
	}
}
