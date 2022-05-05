/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.project.resources;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;

/**
 * This class is needed for the navigator to not display
 * an eclipse resource in the navigator. This would enable all
 * the IResource/IProject related UI which we don't want to have
 * in the VirSat Navigator
 * @author scha_vo
 *
 */
public class VirSatProjectResource implements IVirsatWrappedResource {

	private IProject wrappedProject;
	
	/**
	 * public constructor to create our special {@link VirSatProjectResource}
	 * @param wrappedProject the Eclipse project to be wrapped
	 */
	public VirSatProjectResource(IProject wrappedProject) {
		this.wrappedProject = wrappedProject;
	}

	/**
	 * getter method to return the wrapped project of our {@link VirSatProjectResource} 
	 * @return IProject the wrapped project
	 */
	public IProject getWrappedProject() {
		return wrappedProject;
	}

	/**
	 * setter method to explicitly set the wrapped project 
	 * @param wrappedProject the project to be wrapped by our {@link VirSatProjectResource}
	 */
	public void setWrappedProject(IProject wrappedProject) {
		this.wrappedProject = wrappedProject;
	}
	
	/**
	 * a method to get a list of wrapped projects 
	 * @param virSatProjects the projects to process when looking for wrapped projects
	 * @param mapWrappedProjectResource a map of resources to virsat wrapped resources
	 * @return {@code List<IVirsatWrappedResource>} a list of wrapped resources
	 */
	public static List<IVirsatWrappedResource> getWrappedProjects(List<IProject> virSatProjects, Map<IResource, IVirsatWrappedResource> mapWrappedProjectResource) {
		List<IVirsatWrappedResource> virsatWrappedProjects = new ArrayList<>();
		for (IProject project : virSatProjects) {
			IVirsatWrappedResource wrappedResource = mapWrappedProjectResource.get(project);
			if (wrappedResource == null) {
				wrappedResource = new VirSatProjectResource(project);
				mapWrappedProjectResource.put(project, wrappedResource);
			}
			virsatWrappedProjects.add(wrappedResource);
		}
		return virsatWrappedProjects;
	}
}
