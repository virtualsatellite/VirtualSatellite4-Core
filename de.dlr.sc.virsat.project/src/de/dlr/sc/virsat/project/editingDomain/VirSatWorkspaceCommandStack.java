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

import java.util.Map;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.operations.IOperationHistory;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.RollbackException;
import org.eclipse.emf.transaction.impl.InternalTransaction;
import org.eclipse.emf.workspace.EMFCommandOperation;
import org.eclipse.emf.workspace.impl.EMFOperationTransaction;
import org.eclipse.emf.workspace.impl.WorkspaceCommandStackImpl;

import de.dlr.sc.virsat.model.dvlm.util.command.VirSatRecordingCommand;
import de.dlr.sc.virsat.project.Activator;

/**
 * VirSat Command Stack which delegates to a WorkspaceCommandStack
 * but offers the possibility to execute commands without undo. It 
 * also provides special treatment of recording commands which may call
 * extra logic such as removing the file of a SEI
 *
 */
public class VirSatWorkspaceCommandStack extends WorkspaceCommandStackImpl {

	private VirSatTransactionalEditingDomain editingDomain;
	
	private boolean triggerSave;
	
	/**
	 * Constructor to the Command Stack with the OperationsHistory
	 * @param history The operation history to be used with this domain
	 */
	public VirSatWorkspaceCommandStack(IOperationHistory history) {
		super(history);
	}
	
	/**
	 * Method to execute a command and directly take it out of the
	 * history, so it cannot be undone.
	 * @param command The command to be executed.
	 */
	public void executeNoUndo(Command command) {
		// Check if the command is execute able and prepare it (happens in canExecute)
		if (command.canExecute()) {
			// Wrap it into an EMF operation and execute the command ourselves without
			// using the history to prevent the operation from being listed in there
			EMFCommandOperation operation = new EMFCommandOperation(getDomain(), command);
			try {
				operation.execute(null, null);
			} catch (ExecutionException e) {
				Activator.getDefault().getLog().log(new Status(Status.ERROR, Activator.getPluginId(), "VirSatWorkspaceCommandStack: Failed to execute command without undo ", e));
			}
		}
	}
	
	@Override
	public void execute(Command command) {
		execute(command, null);
	}
	
	@Override
	public void execute(Command command, Map<?, ?> options) {
		// In case the command that shall be executed is a Recording Command,
		// it will be wrapped into VirSatRecordingCommand. This command can be 
		// easily distinguished in the stack as a command that was processed here.
		if (command instanceof RecordingCommand) {
			command = new VirSatRecordingCommand(command);
		}
		
		triggerSave = false;
		try {
			// Try to see if there is already a transaction going on in the editing domain,
			// which is associated with this Command Stack.
			InternalTransaction activeTransaction = getDomain().getActiveTransaction();
			
			// In case it exists, we want to now check if it is reusable for us. Which means
			// like if a recording command is executed, which also tries to remove a file from a SEI,
			// These commands shall be executed in one atomic transaction. This is implemented for making 
			// Graphiti Framework work for us. Important is, that a transaction can only be rentered by
			// the same thread. Thus commands within a recording command can be placed into the same transaction
			// A command from a different thread can not be placed into the already open transaction.
			boolean usableTransactionExists = activeTransaction != null && !activeTransaction.isReadOnly()
					&& activeTransaction.getOwner().equals(Thread.currentThread());
			
			// The standard case, is that there is a new command which should be executed.
			// Since there is usually no transaction yet, it will be forwarded to the Standard Workspace
			// Command Stack which opens a transaction and starts executing the command. Or in case there is a transaction
			// it will be just placed on the stack as the next command.
			if (!usableTransactionExists) {
				// No active transaction means we have to execute the command as a
				// top-level command
				super.execute(command, options);
			} else {
				// now where a transaction for the same thread exists, the command itself will be executed
				// within exactly that transaction. In case that the command which is already executed by the 
				// transaction is a VirtualSatelliteRecordCommand, the executed command will be chained to it.
				// This way it looks to the CommandStack as if only the VirSatRecordingCommand was executed.
				// Now when e.g. an undo is triggered, the chained commands will be undone as well within the
				// same atomic transaction.
				if (command != null && command.canExecute()) {
					command.execute();
					if (getActiveCommand() instanceof VirSatRecordingCommand) {
						getActiveCommand().chain(command);
					}
				}
			}
		} catch (InterruptedException | RollbackException e) {
			Activator.getDefault().getLog().log(new Status(IStatus.ERROR, Activator.getPluginId(), "Failed to execute command", e));
		}
		checkTriggerSaveAll();
	}
	
	@Override
	public void undo() {
		triggerSave = false;
		super.undo();
		checkTriggerSaveAll();
	}
	
	@Override
	public void redo() {
		triggerSave = false;
		super.redo();
		checkTriggerSaveAll();
	}
	
	/**
	 * Gets the active transition and returns its associated command (if there is any)
	 * @return the currently executed command or null if there is none
	 */
	public Command getActiveCommand() {
		InternalTransaction activeTransaction = getDomain().getActiveTransaction();
		boolean usableTransactionExists = activeTransaction != null && !activeTransaction.isReadOnly()
				&& activeTransaction.getOwner().equals(Thread.currentThread());
		
		if (usableTransactionExists && activeTransaction.getRoot() instanceof EMFOperationTransaction) {
			return ((EMFOperationTransaction) activeTransaction.getRoot()).getCommand();
		}
		
		return null;
	}
	
	/**
	 * Sets the editing domain for this command stack
	 * @param editingDomain the editing domain of this command stack
	 */
	public void setEditingDomain(VirSatTransactionalEditingDomain editingDomain) {
		this.editingDomain = editingDomain;
	}

	/**
	 * Trigger a save all upon completion of the executing command
	 */
	public void triggerSaveAll() {
		triggerSave = true;
	}
	
	/**
	 * Check if the triggerSave flag has been set. If so, ask the editing domain to save everything
	 * and reset the flag.
	 */
	private void checkTriggerSaveAll() {
		if (triggerSave) {
			Activator.getDefault().getLog().log(new Status(IStatus.INFO, Activator.getPluginId(), "Executed command triggered save all on editing domain"));
			VirSatSaveJob saveJob = new VirSatSaveJob(editingDomain, true);
			saveJob.scheduleIfNoSaveJobPending();
			triggerSave = false;
		}
	}
	
}
