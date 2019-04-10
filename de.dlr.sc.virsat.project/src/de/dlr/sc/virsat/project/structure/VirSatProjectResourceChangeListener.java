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
import java.util.List;

import org.eclipse.core.resources.IFile;
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
import de.dlr.sc.virsat.project.editingDomain.VirSatSaveJob;
import de.dlr.sc.virsat.project.editingDomain.VirSatTransactionalEditingDomain;

/**
 * A resource change listener that identifies which DVLM resources have been changed
 * and keeps track of them it uses the editing domain to exclusively executed the handler methods
 * @author fisc_ph
 *
 */
public abstract class VirSatProjectResourceChangeListener implements IResourceChangeListener {

	private IProject virSatProject;
	private VirSatTransactionalEditingDomain ed;
	private boolean closedOrDeletedProject;
	
	/**
	 * Constructor for the listener
	 * @param ed The editingDomain that will be used for executing the handlers
	 * @param virSatProject The virsatproject resource this listener should listen to
	 */
	public VirSatProjectResourceChangeListener(VirSatTransactionalEditingDomain ed, IProject virSatProject) {
		this.virSatProject = virSatProject;
		this.ed = ed;
	}
	
	@Override
	public void resourceChanged(IResourceChangeEvent event) {
		if (event.getType() != IResourceChangeEvent.POST_CHANGE) {
			return;
		}

		// Our own changes (e.g. the builders) are not external changes but managed by us,
		// so we dont need to fire any synchronization jobs
		Job currentJob = Job.getJobManager().currentJob();
		if (currentJob == null || currentJob.getName().equals("Building workspace")) {
			return;
		}
		
        IResourceDelta rootDelta = event.getDelta();
        IPath path = virSatProject.getFullPath();
        IResourceDelta projectDelta = rootDelta.findMember(path);
        
		// Only obey changes of the project
		if (projectDelta != null) {
			
			final List<IResource> addedDvlmResources = new ArrayList<>();
			final List<IResource> changedDvlmResources = new ArrayList<>();
			final List<IResource> removedDvlmResources = new ArrayList<>();
			
			closedOrDeletedProject = false;
			
			IResourceDeltaVisitor dvlmResourceVisitor = new IResourceDeltaVisitor() {
				public boolean visit(IResourceDelta delta) {
					// Check if the changed resource is a closed project
					if (delta.getResource() == virSatProject && !virSatProject.isOpen()) {
						closedOrDeletedProject = true;
					}
					IResource resource = delta.getResource();
					// In case it is a change on a DVLM file, than remember it
					if ((resource instanceof IFile) && VirSatProjectCommons.isDvlmFile((IFile) resource)) {
						int kind = delta.getKind();
						if (kind == IResourceDelta.REMOVED) {
							removedDvlmResources.add(resource);
						} else if (kind == IResourceDelta.ADDED) {
							addedDvlmResources.add(resource);							
						} else if ((kind == IResourceDelta.CHANGED) && ((delta.getFlags() & IResourceDelta.CONTENT) != 0)) {
							changedDvlmResources.add(resource);							
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
				return;
			}
			
			// If there already is an ongoing transaction, we make a new
			// workspace job as we will need the editing domain to handle resource changes.
			// This way the current job calling us can safely finish and the new job
			// will handle the actual changes.
			Job job = new WorkspaceSynchronizerJob(addedDvlmResources, removedDvlmResources, changedDvlmResources);
			job.schedule();
        }
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
		 */
		WorkspaceSynchronizerJob(List<IResource> addedDvlmResources, List<IResource> removedDvlmResources, List<IResource> changedDvlmResources) {
			super("Workspace Synchronizer");
			this.addedDvlmResources = addedDvlmResources;
			this.removedDvlmResources = removedDvlmResources;
			this.changedDvlmResources = changedDvlmResources;
			setRule(ResourcesPlugin.getWorkspace().getRoot());
		}
		
		@Override
		public IStatus runInWorkspace(IProgressMonitor monitor) throws CoreException {
			// Make it so the synchronization job is extecuted after the save job
			if (Job.getJobManager().find(VirSatSaveJob.SAVE_JOB_NAME).length == 0 && ed.getActiveTransaction() == null) {
				handleChanges(addedDvlmResources, removedDvlmResources, changedDvlmResources);
			} else {
				schedule();
			}
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
