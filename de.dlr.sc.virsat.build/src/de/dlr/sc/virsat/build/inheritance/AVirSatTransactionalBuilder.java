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
import org.eclipse.emf.common.command.AbstractCommand;
import org.eclipse.emf.common.command.Command;

import de.dlr.sc.virsat.project.Activator;
import de.dlr.sc.virsat.project.editingDomain.VirSatEditingDomainRegistry;
import de.dlr.sc.virsat.project.editingDomain.VirSatTransactionalEditingDomain;
import de.dlr.sc.virsat.project.markers.VirSatProblemMarkerHelper;
import de.dlr.sc.virsat.project.resources.VirSatResourceSet;

/**
 * This class implements an incremental builder compatible to
 * Virtual Satellites TransactionalEditing Framework.
 * The build processes take care that the commands for applying model
 * changes are only executed in case the Workspace Command Stack has no transaction
 * going on. Otherwise the builder will be remembered for later execution.
 */
public abstract class AVirSatTransactionalBuilder extends IncrementalProjectBuilder {

	protected VirSatProblemMarkerHelper vpmHelper;
	protected boolean redirectIncrementalToAutoBuild;
	
	/**
	 * Constructor of the abstract transactional builder
	 * @param vpmHelper the OProblemMarkerHelper to be used with the builder
	 * @param redirectIncrementalToAutoBuild set to true in case the incremental build should be decided in the auto_build functionality
	 */
	public AVirSatTransactionalBuilder(VirSatProblemMarkerHelper vpmHelper, boolean redirectIncrementalToAutoBuild) {
		super();
		this.vpmHelper = vpmHelper;
		this.redirectIncrementalToAutoBuild = redirectIncrementalToAutoBuild;
	}

	/**
	 * Implement this method for the actual incremental build.
	 * @param delta The delta that should be build
	 * @param monitor the monitor to be used for communicating progress
	 */
	protected abstract void incrementalBuild(IResourceDelta delta, IProgressMonitor monitor);

	/**
	 * Implement this method for a full build
	 * @param monitor the monitor to be used for communicating progress
	 */
	protected abstract void fullBuild(IProgressMonitor monitor);

	@Override
	protected IProject[] build(int kind, Map<String, String> args, IProgressMonitor monitor) throws CoreException {
		// The builder is supposed to build the project in an unmanaged resourceSet
		// but since we can have inconsistencies between the resource on the eclipse workspace and the in memory representation
		// of EMF currently displayed by the managed ResourceSet in the navigator, it can happen that newly added elements disappear because the resources in the WS doesn't knwo references
		// and stores them once the builder is finished. This behavior is prevented by first asking the projects managed resource set
		Activator.getDefault().getLog().log(new Status(Status.INFO, Activator.getPluginId(), Status.OK, "VirSatTransactionalBuilder: Try to trigger build", null));
		
		if (isInterrupted() || monitor.isCanceled()) {
			rememberLastBuiltState();
			return null;
		}
		
		IProject project = getVirSatProject();
		IResourceDelta delta = getDelta(project);

		switch (kind) {  
			case FULL_BUILD:
				Activator.getDefault().getLog().log(new Status(Status.INFO, Activator.getPluginId(), Status.OK, "VirSatTransactionalBuilder: Performing full build", null));
				transactionalFullBuild(monitor);
				break;  
			case INCREMENTAL_BUILD:
				if (!redirectIncrementalToAutoBuild) {
					transactionalIncrementalBuild(delta, monitor);
					break;
				}
			case AUTO_BUILD:
				if (delta == null) {
					Activator.getDefault().getLog().log(new Status(Status.INFO, Activator.getPluginId(), Status.OK, "VirSatTransactionalBuilder: Performing (auto) full build", null));
					transactionalFullBuild(monitor);
				} else {
					Activator.getDefault().getLog().log(new Status(Status.INFO, Activator.getPluginId(), Status.OK, "VirSatTransactionalBuilder: Performing (auto) incremental build", null));
					transactionalIncrementalBuild(delta, monitor);
				}
				break;
			default:
				break;  
		}
		
		Activator.getDefault().getLog().log(new Status(Status.INFO, Activator.getPluginId(), Status.OK, "VirSatTransactionalBuilder: Finished build", null));
		
		return null; 
	}

	/**
	 * the transactionalfullBuild method is executed to trigger a build with all necessary steps using an editing domain
	 * @param monitor monitor the progress
	 * @param inheritanceCopier  the copier to be actually used
	 */
	protected void transactionalFullBuild(IProgressMonitor monitor) {
		VirSatTransactionalEditingDomain virSatTed = VirSatEditingDomainRegistry.INSTANCE.getEd(getVirSatProject());
		
		if (virSatTed == null) {
			return;
		}
		
		Command cmd = new WrappingBuilderCommand() {
			@Override
			public void execute() {
				fullBuild(monitor);
				virSatTed.saveAll(true, true);
			}
		};
		
		if (virSatTed.getActiveTransaction() != null) {
			rememberLastBuiltState();
			return;
		}
		
		transactionalFullBuildRemoveProblemMarkers();
		virSatTed.getVirSatCommandStack().executeNoUndo(cmd);
		transactionalFullBuildUpdateProblemMarkers();
	}

	/**
	 * Intended to remove all ProblemMarkers of relevance during a full build
	 */
	protected void transactionalFullBuildRemoveProblemMarkers() {
		vpmHelper.deleteAllMarkersInWorkspace();
	}
	
	/**
	 * Intended to update all ProblemMarkers of relevance during a full build
	 */
	protected void transactionalFullBuildUpdateProblemMarkers() {
	}
	
	/**
	 * Method for incremental build using an editing domain
	 * @param delta the delta from the BUilderManager 
	 * @param monitor a progress monitor to track what is going on
	 * @param inheritanceCopier  the copier to be actually used
	 */
	protected void transactionalIncrementalBuild(IResourceDelta delta, IProgressMonitor monitor) {
		VirSatTransactionalEditingDomain virSatTed = VirSatEditingDomainRegistry.INSTANCE.getEd(getVirSatProject());
		
		if (virSatTed == null) {
			return;
		}
	
		Command cmd = new WrappingBuilderCommand() {
			@Override
			public void execute() {
				incrementalBuild(delta, monitor);
				virSatTed.saveAll(true, true);
			}
		};
		
		if (virSatTed.getActiveTransaction() != null) {
			rememberLastBuiltState();
			return;
		}
		
		transactionalIncrementalBuildRemoveProblemMarkers();
		virSatTed.getVirSatCommandStack().executeNoUndo(cmd);
		transactionalIncrementalBuildUpdateProblemMarkers();
	}
	
	/**
	 * Intended to remove all ProblemMarkers of relevance during an incremental build
	 */
	protected void transactionalIncrementalBuildRemoveProblemMarkers() {
	}
	
	/**
	 * Intended to update all ProblemMarkers of relevance during an incremental build
	 */
	protected void transactionalIncrementalBuildUpdateProblemMarkers() {
	}

	/**
	 * A wrapping command that can be used by the transactional full and incremental build.
	 * Implements all needed general functionality and settings.
	 */
	protected abstract class WrappingBuilderCommand extends AbstractCommand {
		@Override
		public void redo() {
		}
		
		@Override
		public boolean canUndo() {
			return true;
		}
		
		@Override
		public boolean canExecute() {
			return true;
		}
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

}