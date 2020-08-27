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
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.command.AbstractCommand;
import org.eclipse.emf.common.command.Command;

import de.dlr.sc.virsat.model.dvlm.roles.IUserContext;
import de.dlr.sc.virsat.project.Activator;
import de.dlr.sc.virsat.project.editingDomain.VirSatEditingDomainRegistry;
import de.dlr.sc.virsat.project.editingDomain.VirSatTransactionalEditingDomain;
import de.dlr.sc.virsat.project.markers.VirSatProblemMarkerHelper;

/**
 * This class implements an incremental builder compatible to
 * Virtual Satellites TransactionalEditing Framework.
 * The build processes take care that the commands for applying model
 * changes are only executed in case the Workspace Command Stack has no transaction
 * going on. Otherwise the builder will be remembered for later execution.
 */
public abstract class AVirSatTransactionalBuilder extends AVirSatBuilder {

	protected boolean redirectIncrementalToAutoBuild;
	protected boolean dvlmOnly;
	
	protected VirSatTransactionalEditingDomain virSatTed;
	
	/**
	 * Constructor of the abstract transactional builder
	 * @param vpmHelper the OProblemMarkerHelper to be used with the builder
	 * @param redirectIncrementalToAutoBuild set to true in case the incremental build should be decided in the auto_build functionality
	 * @param dvlmOnly Only handles DVLM resources 
	 */
	public AVirSatTransactionalBuilder(String builderName, VirSatProblemMarkerHelper vpmHelper, boolean redirectIncrementalToAutoBuild, boolean dvlmOnly) {
		super(builderName, vpmHelper);
		this.redirectIncrementalToAutoBuild = redirectIncrementalToAutoBuild;
		this.dvlmOnly = dvlmOnly;
	}

	public void initVirSatTransactionalEditingDomain() {
		this.virSatTed = VirSatEditingDomainRegistry.INSTANCE.getEd(getVirSatProject());
	}

	public IUserContext getUserContext() {
		return virSatTed;
	}
	
	/**
	 * Implement this method for the actual incremental build.
	 * @param delta The delta that should be build
	 * @param monitor the monitor to be used for communicating progress
	 */
	@Override
	protected abstract void incrementalBuild(IResourceDelta delta, IProgressMonitor monitor);

	/**
	 * Implement this method for a full build
	 * @param monitor the monitor to be used for communicating progress
	 */
	@Override
	protected abstract void fullBuild(IProgressMonitor monitor);

	@Override
	protected IProject[] build(int kind, Map<String, String> args, IProgressMonitor monitor) {
		try {
			// The builder is supposed to build the project in an unmanaged resourceSet
			// but since we can have inconsistencies between the resource on the eclipse workspace and the in memory representation
			// of EMF currently displayed by the managed ResourceSet in the navigator, it can happen that newly added elements disappear because the resources in the WS doesn't knwo references
			// and stores them once the builder is finished. This behavior is prevented by first asking the projects managed resource set
			Activator.getDefault().getLog().log(new Status(Status.INFO, Activator.getPluginId(), "VirSatTransactionalBuilder: <" + builderName + "> Try to trigger build"));
			
			IProject project = getVirSatProject();
			initVirSatTransactionalEditingDomain();
			IResourceDelta delta = getDelta(project);
	
			switch (kind) {  
				case FULL_BUILD:
					Activator.getDefault().getLog().log(new Status(Status.INFO, Activator.getPluginId(), "VirSatTransactionalBuilder: <" + builderName + "> Performing full build - full build"));
					transactionalFullBuild(monitor);
					Activator.getDefault().getLog().log(new Status(Status.INFO, Activator.getPluginId(), "VirSatTransactionalBuilder: <" + builderName + "> Done full build - full build"));
					break;  
				case INCREMENTAL_BUILD:
					if (!redirectIncrementalToAutoBuild) {
						Activator.getDefault().getLog().log(new Status(Status.INFO, Activator.getPluginId(), "VirSatTransactionalBuilder: <" + builderName + "> Performing incremental build - incremental build"));
						transactionalIncrementalBuild(delta, monitor);
						Activator.getDefault().getLog().log(new Status(Status.INFO, Activator.getPluginId(), "VirSatTransactionalBuilder: <" + builderName + "> Done incremental build - incremental build"));
						break;
					}
				case AUTO_BUILD:
					if (delta == null) {
						Activator.getDefault().getLog().log(new Status(Status.INFO, Activator.getPluginId(), "VirSatTransactionalBuilder: <" + builderName + "> Performing (auto) full build"));
						transactionalFullBuild(monitor);
						Activator.getDefault().getLog().log(new Status(Status.INFO, Activator.getPluginId(), "VirSatTransactionalBuilder: <" + builderName + "> Done (auto) full build"));
					} else {
						Activator.getDefault().getLog().log(new Status(Status.INFO, Activator.getPluginId(), "VirSatTransactionalBuilder: <" + builderName + "> Performing (auto) incremental build"));
						transactionalIncrementalBuild(delta, monitor);
						Activator.getDefault().getLog().log(new Status(Status.INFO, Activator.getPluginId(), "VirSatTransactionalBuilder: <" + builderName + "> Done (auto) incremental build"));
					}
					break;
				default:
					Activator.getDefault().getLog().log(new Status(Status.INFO, Activator.getPluginId(), "VirSatTransactionalBuilder: <" + builderName + "> Unknown build type"));
					break;  
			}
			
			Activator.getDefault().getLog().log(new Status(Status.INFO, Activator.getPluginId(), "VirSatTransactionalBuilder: <" + builderName + "> Finished build"));
		} catch (Exception e) {
			Activator.getDefault().getLog().log(new Status(Status.ERROR, Activator.getPluginId(), "VirSatTransactionalBuilder: <" + builderName + "> Errored", e));
		}
		return null; 
	}

	/**
	 * the transactionalfullBuild method is executed to trigger a build with all necessary steps using an editing domain
	 * @param monitor monitor the progress
	 * @param inheritanceCopier  the copier to be actually used
	 */
	protected void transactionalFullBuild(IProgressMonitor monitor) {
		if (virSatTed == null) {
			Activator.getDefault().getLog().log(new Status(Status.INFO, Activator.getPluginId(), Status.OK, "VirSatTransactionalBuilder: <" + builderName + "> transactionalFullBuild exited because of no transactional editing domain", null));
			return;
		}
		
		Command cmd = new WrappingBuilderCommand() {
			@Override
			public void execute() {
				fullBuild(monitor);
				virSatTed.saveAll(true, dvlmOnly);
			}
		};
		
		Activator.getDefault().getLog().log(new Status(Status.INFO, Activator.getPluginId(), Status.OK, "VirSatTransactionalBuilder: <" + builderName + "> Cleaning Markers before Command execution", null));
		transactionalFullBuildRemoveProblemMarkers(); 
		Activator.getDefault().getLog().log(new Status(Status.INFO, Activator.getPluginId(), Status.OK, "VirSatTransactionalBuilder: <" + builderName + "> Executing command", null));
		virSatTed.getVirSatCommandStack().executeNoUndo(cmd, virSatTed, false);
		Activator.getDefault().getLog().log(new Status(Status.INFO, Activator.getPluginId(), Status.OK, "VirSatTransactionalBuilder: <" + builderName + "> Cleaning Markers after Command execution", null));
		transactionalFullBuildUpdateProblemMarkers();
		Activator.getDefault().getLog().log(new Status(Status.INFO, Activator.getPluginId(), Status.OK, "VirSatTransactionalBuilder: <" + builderName + "> Done", null));
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
	
	private boolean saveAfterIncrementalBuild;
	
	/**
	 * Call this method during an incremental build to
	 * make save all dvlm resource. this method should usually be called
	 * in case a SEI has been processed. It should not be called when other
	 * files outside the dvlm mdoel have been processed by a builder.
	 */
	protected void triggerSaveAfterIncrementalBuild() {
		saveAfterIncrementalBuild = true;
	}
	
	/**
	 * Method for incremental build using an editing domain
	 * @param delta the delta from the BUilderManager 
	 * @param monitor a progress monitor to track what is going on
	 * @param inheritanceCopier  the copier to be actually used
	 */
	protected void transactionalIncrementalBuild(IResourceDelta delta, IProgressMonitor monitor) {
		if (virSatTed == null) {
			Activator.getDefault().getLog().log(new Status(Status.INFO, Activator.getPluginId(), Status.OK, "VirSatTransactionalBuilder: <" + builderName + "> transactionalIncrementalBuild exited because of no transactional editing domain", null));
			return;
		}
	
		Command cmd = new WrappingBuilderCommand() {
			@Override
			public void execute() {
				saveAfterIncrementalBuild = false;
				incrementalBuild(delta, monitor);
				
				// Only save if an implementing builder has set the flag
				if (saveAfterIncrementalBuild) {
					virSatTed.saveAll(true, dvlmOnly);
				}
			}
		};
		
		Activator.getDefault().getLog().log(new Status(Status.INFO, Activator.getPluginId(), Status.OK, "VirSatTransactionalBuilder: <" + builderName + "> Cleaning Markers before Command execution", null));
		transactionalFullBuildRemoveProblemMarkers();
		Activator.getDefault().getLog().log(new Status(Status.INFO, Activator.getPluginId(), Status.OK, "VirSatTransactionalBuilder: <" + builderName + "> Executing command", null));
		virSatTed.getVirSatCommandStack().executeNoUndo(cmd, virSatTed, false);
		Activator.getDefault().getLog().log(new Status(Status.INFO, Activator.getPluginId(), Status.OK, "VirSatTransactionalBuilder: <" + builderName + "> Cleaning Markers after Command execution", null));
		transactionalFullBuildUpdateProblemMarkers();
		Activator.getDefault().getLog().log(new Status(Status.INFO, Activator.getPluginId(), Status.OK, "VirSatTransactionalBuilder: <" + builderName + "> Done", null));
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

}