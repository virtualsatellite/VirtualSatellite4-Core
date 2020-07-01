/*******************************************************************************
 * Copyright (c) 2020 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.project.structure.operation;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.operations.AbstractOperation;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubMonitor;

import de.dlr.sc.virsat.commons.Activator;

/**
 * Very simple operation to delete a resource within the eclipse workspace.
 * This operation does not use the undo capabilities of the eclipse
 * refactoring framework and is therefore free of UI code dependencies
 */
public class NoUndoDeleteResourceOperation extends AbstractOperation {

	public static final String OPERATION_LABEL = "Simple Resource delete operation";
	public static final String OPERATION_MONITOR_LABEL = "Deleting Resource";
	public static final String OPERATION_STATUS_UNDO = "Operation does not support undo";
	public static final String OPERATION_STATUS_REDO = "Operation does not support redo";
	public static final String OPERATION_STATUS_EXEC = "Operation failed during execution";
	
	protected IResource resource;
	protected boolean forceDelete;
	
	/**
	 * Creates an operation for deleting the given resource.
	 * @param resource the resource to be deleted.
	 * @param force set to true if delete should be forced.
	 */
	public NoUndoDeleteResourceOperation(IResource resource, boolean force) {
		super(String.format("%s : %s", OPERATION_LABEL, resource.getRawLocation().toOSString()));
		this.resource = resource;
		this.forceDelete = force;
	}

	@Override
	public IStatus execute(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {
		String monitorLabel = String.format("%s : %s", OPERATION_MONITOR_LABEL, resource.getRawLocation().toOSString());
		SubMonitor subMonitor = SubMonitor.convert(monitor, monitorLabel, 2);
		try {
			resource.delete(forceDelete, subMonitor.split(1));
		} catch (CoreException e) {
			return new Status(Status.ERROR, Activator.getPluginId(), OPERATION_STATUS_EXEC, e);
		}
		return Status.OK_STATUS;
	}

	@Override
	public IStatus redo(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {
		return new Status(Status.ERROR, Activator.getPluginId(), OPERATION_STATUS_REDO);
	}

	@Override
	public IStatus undo(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {
		return new Status(Status.ERROR, Activator.getPluginId(), OPERATION_STATUS_UNDO);
	}
	
	@Override
	public boolean canRedo() {
		return false;
	}
	
	@Override
	public boolean canUndo() {
		return false;
	}
}
