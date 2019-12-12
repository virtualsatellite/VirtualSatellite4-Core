/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.build.inheritance;

import java.util.Map;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IncrementalProjectBuilder;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Status;

import de.dlr.sc.virsat.project.Activator;
import de.dlr.sc.virsat.project.editingDomain.VirSatEditingDomainRegistry;
import de.dlr.sc.virsat.project.editingDomain.VirSatTransactionalEditingDomain;
import de.dlr.sc.virsat.project.markers.VirSatProblemMarkerHelper;
import de.dlr.sc.virsat.project.resources.VirSatResourceSet;

/**
 * Very simple abstract builder class, that implements basic functionality for a builder
 *
 */
public abstract class AVirSatBuilder extends IncrementalProjectBuilder {

	protected VirSatProblemMarkerHelper vpmHelper;

	/**
	 * Method to perform the full build
	 * @param monitor the monitor for progress reporting
	 */
	protected abstract void fullBuild(IProgressMonitor monitor);

	/**
	 * Method for performing the incremental build
	 * @param delta the resource delta to be processed
	 * @param monitor the monitor for progress reporting
	 */
	protected abstract void incrementalBuild(IResourceDelta delta, IProgressMonitor monitor);

	/**
	 * Constructor for the VirSatBuilder 
	 * @param vpmHelper The generic VirSatProblemMarker
	 */
	public AVirSatBuilder(VirSatProblemMarkerHelper vpmHelper) {
		super();
		this.vpmHelper = vpmHelper;
	}

	/**
	 * Facade method to be able to override getProject method
	 * for the test cases of this tester
	 * @return hands back the current project the builder is triggered on
	 */
	protected IProject getVirSatProject() {
		return getProject();
	}

	/**
	 * Overrideable method to provide the resource set for the use of non transactional testers
	 * @return gets the resource set this builder operates on
	 */
	protected VirSatResourceSet getResourceSet() {
		return VirSatResourceSet.getResourceSet(getVirSatProject());
	}

	@Override
	protected IProject[] build(int kind, Map<String, String> args, IProgressMonitor monitor) throws CoreException {
		Activator.getDefault().getLog().log(new Status(Status.INFO, Activator.getPluginId(), Status.OK, "VirSatBuilder: Try to trigger custom build", null));
		
		IProject project = getVirSatProject();
		VirSatTransactionalEditingDomain virSatTed = VirSatEditingDomainRegistry.INSTANCE.getEd(project);
		IResourceDelta delta = getDelta(project);

		if (virSatTed == null || !getResourceSet().isOpen()) {
			return null;
		}
		
		switch (kind) {  
			case FULL_BUILD:
				fullBuild(monitor);
				break;  
			case INCREMENTAL_BUILD:
				if (delta == null) {
					Activator.getDefault().getLog().log(new Status(Status.INFO, Activator.getPluginId(), Status.OK, "VirSatBuilder: Performing full build", null));
					fullBuild(monitor);
				} else {
					Activator.getDefault().getLog().log(new Status(Status.INFO, Activator.getPluginId(), Status.OK, "VirSatBuilder: Performing incremental build", null));
					incrementalBuild(delta, monitor);
				}
				break;
			case AUTO_BUILD:
				if (delta == null) {
					Activator.getDefault().getLog().log(new Status(Status.INFO, Activator.getPluginId(), Status.OK, "VirSatBuilder: Performing full build", null));
					fullBuild(monitor);
				} else {
					Activator.getDefault().getLog().log(new Status(Status.INFO, Activator.getPluginId(), Status.OK, "VirSatBuilder: Performing incremental build", null));
					incrementalBuild(delta, monitor);
				}
				break;
			default:
				break;  
		}
		
		Activator.getDefault().getLog().log(new Status(Status.INFO, Activator.getPluginId(), Status.OK, "VirSatBuilder: Finished Custom build", null));
		return null; //new IProject[]{project};
	}
}