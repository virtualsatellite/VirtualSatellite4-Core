/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.project.structure;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IResourceDeltaVisitor;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.resources.WorkspaceJob;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;

import de.dlr.sc.virsat.project.Activator;
import de.dlr.sc.virsat.project.editingDomain.VirSatEditingDomainRegistry;
import de.dlr.sc.virsat.project.editingDomain.VirSatTransactionalEditingDomain;

/**
 * A resource change listener that identifies which DVLM resources have been changed
 * and keeps track of them it uses the editing domain to exclusively executed the handler methods
 * @author fisc_ph
 *
 */
public abstract class AVirSatProjectResourceChangeListener implements IResourceChangeListener {

	private IProject virSatProject;
	private boolean closedOrDeletedProject;
	private int counter;
	
	/**
	 * Constructor for the listener
	 * @param ed The editingDomain that will be used for executing the handlers
	 * @param virSatProject The virsatproject resource this listener should listen to
	 */
	public AVirSatProjectResourceChangeListener(IProject virSatProject) {
		this.virSatProject = virSatProject;
		this.counter = 0;
	}
	
	@Override
	public void resourceChanged(IResourceChangeEvent event) {
		if (event.getType() != IResourceChangeEvent.POST_CHANGE) {
			return;
		}
		
		Activator.getDefault().getLog().log(new Status(Status.INFO, Activator.getPluginId(), "VirSatProjectResourceChangeListener: Detected a Post Change event on the workspace"));
		
		IResourceDelta rootDelta = event.getDelta();
		IPath path = virSatProject.getFullPath();
		IResourceDelta projectDelta = rootDelta.findMember(path);

		// Only obey changes of the project
		if (projectDelta != null) {
			Activator.getDefault().getLog().log(new Status(Status.INFO, Activator.getPluginId(), "VirSatProjectResourceChangeListener: Detected deltas in the project: " + virSatProject.getName()));
			
			final List<IResource> addedResources = new ArrayList<>();
			final List<IResource> changedResources = new ArrayList<>();
			final List<IResource> removedResources = new ArrayList<>();
			
			closedOrDeletedProject = false;
			
			IResourceDeltaVisitor dvlmResourceVisitor = new IResourceDeltaVisitor() {
				public boolean visit(IResourceDelta delta) {
					// Check if the changed resource is a closed project
					if (delta.getResource() == virSatProject && !virSatProject.isOpen()) {
						closedOrDeletedProject = true;
					}
					IResource resource = delta.getResource();
					
					// In case it is a change on a file within our resource set, then remember it
					VirSatTransactionalEditingDomain ed = VirSatEditingDomainRegistry.INSTANCE.getEd(virSatProject);
					boolean isInResourceSet = ed != null && ed.getResourceSet().getResource(resource, false) != null;
					
					if (isInResourceSet) {
						int kind = delta.getKind();
						if (kind == IResourceDelta.REMOVED) {
							removedResources.add(resource);
						} else if (kind == IResourceDelta.ADDED) {
							addedResources.add(resource);							
						} else if ((kind == IResourceDelta.CHANGED) && ((delta.getFlags() & IResourceDelta.CONTENT) != 0)) {
							changedResources.add(resource);							
						}
					}
					return true;
				}
			};

			// Now call the delta visitor to find all dvlm files that had a
			// change in the workspace
			try {
				projectDelta.accept(dvlmResourceVisitor);
			} catch (CoreException e) {
				Activator.getDefault().getLog().log(new Status(Status.ERROR, Activator.getPluginId(),
						"Could not listen to workspace changes" + e.getMessage()));
			}

			// Make the synchronizer stop if the project is closed
			if (closedOrDeletedProject) {
				Activator.getDefault().getLog().log(new Status(Status.INFO, Activator.getPluginId(), "VirSatProjectResourceChangeListener: The project (" + virSatProject.getName() + ") seems to be closed."));
				return;
			}
			
			// run a workspace job as we will need the editing domain to handle resource changes.
			// This way the current job calling us can safely finish and the new job
			// will handle the actual changes.
			if (!addedResources.isEmpty() || !removedResources.isEmpty() || !changedResources.isEmpty()) {
				Job job = new WorkspaceSynchronizerJob(addedResources, removedResources, changedResources, counter++);
				String changeList = printLists(addedResources, removedResources, changedResources);
				Activator.getDefault().getLog().log(new Status(Status.INFO, Activator.getPluginId(), "VirSatProjectResourceChangeListener: Scheduling " + job.getName() + ". \n" + changeList));
				job.schedule();
			} else {
				Activator.getDefault().getLog().log(new Status(Status.INFO, Activator.getPluginId(), "VirSatProjectResourceChangeListener: No relevant change, nothing to be scheduled"));
			}
		}
	}
	
	/**
	 * Print a list of resources which have been detected for changes.
	 * @param addedDvlmResources The list of added resources
	 * @param removedDvlmResources list of removed resources
	 * @param changedDvlmResources list of changed resources
	 * @return a pretty printed string for debugging giving reasonable information about resource changes and status.
	 */
	private String printLists(List<IResource> addedDvlmResources, List<IResource> removedDvlmResources, List<IResource> changedDvlmResources) {
		Set<IResource> allResources = new HashSet<>();
		allResources.addAll(addedDvlmResources);
		allResources.addAll(removedDvlmResources);
		allResources.addAll(changedDvlmResources);
		
		StringBuilder printList = new StringBuilder();
		printList.append("Detected by the Change Listener\n");
		printList.append("-------------------------------\n");
		
		for (IResource resource: allResources) {
			boolean isRemoved = removedDvlmResources.contains(resource);
			boolean isChanged = changedDvlmResources.contains(resource);
			boolean isAdded = addedDvlmResources.contains(resource);
			
			printList.append("Change (");
			printList.append(isAdded ? "A" : "-");
			printList.append(isChanged ? "C" : "-");
			printList.append(isRemoved ? "R" : "-");
			printList.append(") on Resource: " + resource.getLocationURI().toString()); 
			printList.append("\n");
		}
		
		return printList.toString();
	}
	
	/**
	 * This job takes care of handling a change in the workspace DVLM resources.
	 * @author muel_s8
	 *
	 */
	private class WorkspaceSynchronizerJob extends WorkspaceJob {
		private List<IResource> addedDvlmResources;
		private List<IResource> removedDvlmResources;
		private List<IResource> changedDvlmResources;
		
		/**
		 * Default constructor
		 * @param addedDvlmResources the added dvlm resources
		 * @param removedDvlmResources the removed dvlm resources
		 * @param changedDvlmResources the changed dvlm resources
		 * @param counter 
		 */
		WorkspaceSynchronizerJob(List<IResource> addedDvlmResources, List<IResource> removedDvlmResources, List<IResource> changedDvlmResources, int counter) {
			super("Workspace Synchronizer (" + counter + ")");
			this.addedDvlmResources = addedDvlmResources;
			this.removedDvlmResources = removedDvlmResources;
			this.changedDvlmResources = changedDvlmResources;
			setRule(ResourcesPlugin.getWorkspace().getRoot());
		}
		
		@Override
		public IStatus runInWorkspace(IProgressMonitor monitor) throws CoreException {
			Activator.getDefault().getLog().log(new Status(Status.INFO, Activator.getPluginId(), "VirSatProjectResourceChangeListener: Synchronization " + this.getName() + " is executed now."));
			handleChanges(addedDvlmResources, removedDvlmResources, changedDvlmResources);
			return Status.OK_STATUS;
		}
	}
	
	/**
	 * Handles the actual changes of resources
	 * @param addedDvlmResources the list of added dvlm resources
	 * @param removedDvlmResources the list of removed dvlm resources
	 * @param changedDvlmResources the list of changed dvlm resources
	 */
	private void handleChanges(List<IResource> addedDvlmResources, List<IResource> removedDvlmResources, List<IResource> changedDvlmResources) {	
		handlePreCondition();
		
		if (!addedDvlmResources.isEmpty()) {
			handleAddedDvlmResources(addedDvlmResources);
		}

		if (!removedDvlmResources.isEmpty()) {
			handleRemovedDvlmResources(removedDvlmResources);
		}
		
		if (!changedDvlmResources.isEmpty()) {
			handleChangedDvlmResources(changedDvlmResources);
		}
		
		handlePostCondition();
	}

	/**
	 * First method to be called exclusively by the ED
	 */
	public abstract void handlePreCondition();

	/**
	 * Last method to be called exclusively by the ED
	 */
	public abstract void handlePostCondition();

	/**
	 * Method to handle all newly added DVLM resources
	 * @param addedDvlmResources a list of DVLM workspace resources
	 */
	public abstract void handleAddedDvlmResources(List<IResource> addedDvlmResources);

	/**
	 * Method to handle all just removed DVLM resources
	 * @param removedDvlmResources a list of DVLM workspace resources
	 */
	public abstract void handleRemovedDvlmResources(List<IResource> removedDvlmResources);

	/**
	 * Method to handle all changed DVLM resources
	 * @param changedDvlmResources a list of DVLM workspace resources
	 */
	public abstract void handleChangedDvlmResources(List<IResource> changedDvlmResources);
}
