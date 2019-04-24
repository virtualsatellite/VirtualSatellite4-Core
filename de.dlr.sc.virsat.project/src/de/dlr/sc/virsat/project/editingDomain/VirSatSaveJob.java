/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.project.editingDomain;

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.resources.WorkspaceJob;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.emf.transaction.RollbackException;
import org.eclipse.emf.transaction.impl.InternalTransaction;

import de.dlr.sc.virsat.project.Activator;

/**
 * This job performs a save a save all operation.
 * 
 * Doing the save in a workspace job is intended, to not let
 * the automatic builders jump on these files in between and
 * to not block the currently ongoing thread because saving
 * requires the full workspace to continue.
 * @author muel_s8
 *
 */

public class VirSatSaveJob extends WorkspaceJob {

	public static final String SAVE_JOB_NAME = "Saving";
	
	private VirSatTransactionalEditingDomain editingDomain;
	private boolean supressRemoveDanglingReferences;
	
	/**
	 * Standard constructor
	 * @param editingDomain the editing domain
	 * @param supressRemoveDanglingReferences supresses removal of dangling references for the builder
	 */
	public VirSatSaveJob(VirSatTransactionalEditingDomain editingDomain, boolean supressRemoveDanglingReferences) {
		super(SAVE_JOB_NAME);
		this.editingDomain = editingDomain;
		this.supressRemoveDanglingReferences = supressRemoveDanglingReferences;
		setRule(ResourcesPlugin.getWorkspace().getRoot());
	}
	
	/**
	 * Constructor with the default option of removing dangling references
	 * @param editingDomain the editing domain
	 */
	public VirSatSaveJob(VirSatTransactionalEditingDomain editingDomain) {
		this(editingDomain, false);
	}

	@Override
	public IStatus runInWorkspace(IProgressMonitor monitor) throws CoreException {
		// Store it through the editing domain so it keeps track of further
		// further changes. This is important to have exact knowledge about the
		// resources dirty state.
		
		if (monitor.isCanceled()) {
			return Status.CANCEL_STATUS;
		}
		
		try {
			// Bundle all transactions such as removing dangling references during the save
			// into one big transaction. Also check for active transactions so we
			// don't cause any deadlocks
			if (editingDomain.getActiveTransaction() == null) {
				if (supressRemoveDanglingReferences) {
					editingDomain.saveAll(false, supressRemoveDanglingReferences);
				} else {
					InternalTransaction tx = editingDomain.startTransaction(false, null);
					editingDomain.saveAll(false, supressRemoveDanglingReferences);
					tx.commit();
				}
			} else {
				schedule();
			}
			
		} catch (InterruptedException | RollbackException e) {
			Activator.getDefault().getLog().log(new Status(Status.ERROR, Activator.getPluginId(), "VirSatSaveJob: Saving failed", e));
		}
		
		return Status.OK_STATUS;
	}
	
	@Override
	public boolean belongsTo(Object family) {
		return getName().equals(family);
	}
	
	/**
	 * Schedules this save job only if there is no other save job pending.
	 */
	public void scheduleIfNoSaveJobPending() {
		if (Job.getJobManager().find(VirSatSaveJob.SAVE_JOB_NAME).length == 0) {
			schedule();
		}
	}

}
