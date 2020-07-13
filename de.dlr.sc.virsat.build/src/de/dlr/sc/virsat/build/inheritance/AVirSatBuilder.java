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
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.ISchedulingRule;

import de.dlr.sc.virsat.project.Activator;
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

	protected String builderName;
	
	/**
	 * Constructor for the VirSatBuilder 
	 * @param vpmHelper The generic VirSatProblemMarker
	 */
	public AVirSatBuilder(String builderName, VirSatProblemMarkerHelper vpmHelper) {
		super();
		this.vpmHelper = vpmHelper;
		this.builderName = builderName;
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
	protected IProject[] build(int kind, Map<String, String> args, IProgressMonitor monitor) {
		try {
			Activator.getDefault().getLog().log(new Status(Status.INFO, Activator.getPluginId(), "VirSatBuilder: <" + builderName + "> Try to trigger custom build"));
			
			IProject project = getVirSatProject();
			IResourceDelta delta = getDelta(project);
	
			if (!getResourceSet().isOpen()) {
				Activator.getDefault().getLog().log(new Status(Status.INFO, Activator.getPluginId(), "VirSatBuilder: <" + builderName + "> Project Closed or not Transactional Editing Domain - no build"));
				return null;
			}
			
			switch (kind) {  
				case FULL_BUILD:
					Activator.getDefault().getLog().log(new Status(Status.INFO, Activator.getPluginId(), "VirSatBuilder: <" + builderName + "> Performing full build - full build"));
					fullBuild(monitor);
					Activator.getDefault().getLog().log(new Status(Status.INFO, Activator.getPluginId(), "VirSatBuilder: <" + builderName + "> Done full build - full build"));
					break;  
				case INCREMENTAL_BUILD:
				case AUTO_BUILD:
					if (delta == null) {
						Activator.getDefault().getLog().log(new Status(Status.INFO, Activator.getPluginId(), "VirSatBuilder: <" + builderName + "> Performing auto or incremental build - full build"));
						fullBuild(monitor);
						Activator.getDefault().getLog().log(new Status(Status.INFO, Activator.getPluginId(), "VirSatBuilder: <" + builderName + "> Done auto or incremental build - full build"));
					} else {
						Activator.getDefault().getLog().log(new Status(Status.INFO, Activator.getPluginId(), "VirSatBuilder: <" + builderName + "> Performing auto or incremental build - incremental build"));
						incrementalBuild(delta, monitor);
						Activator.getDefault().getLog().log(new Status(Status.INFO, Activator.getPluginId(), "VirSatBuilder: <" + builderName + "> Done auto or incremental build - incremental build"));
					}
					break;
				default:
					Activator.getDefault().getLog().log(new Status(Status.INFO, Activator.getPluginId(), "VirSatBuilder: <" + builderName + "> Unknown build mode"));
					break;  
			}
			 
			Activator.getDefault().getLog().log(new Status(Status.INFO, Activator.getPluginId(), "VirSatBuilder: <" + builderName + "> Finished Custom build"));
		} catch (Exception e) {
			Activator.getDefault().getLog().log(new Status(Status.INFO, Activator.getPluginId(), "VirSatBuilder: <" + builderName + "> Errored", e));
		}
		return null; 
	}
	
	@Override
	public ISchedulingRule getRule(int kind, Map<String, String> args) {
		return getProject();
	}
}