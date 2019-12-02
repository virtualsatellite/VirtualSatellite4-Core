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
import java.util.concurrent.locks.ReentrantLock;

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
 * but offers the possibility to execute commands without undo
 * @author fisc_ph
 *
 */
public class VirSatWorkspaceCommandStack extends WorkspaceCommandStackImpl {

	private VirSatTransactionalEditingDomain editingDomain;
	private ReentrantLock transactionLock = new ReentrantLock();
	
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
		transactionLock.lock();
		
		if (command instanceof RecordingCommand) {
			command = new VirSatRecordingCommand(command);
		}
		
		triggerSave = false;
		try {
			InternalTransaction activeTransaction = getDomain().getActiveTransaction();
			boolean usableTransactionExists = activeTransaction != null && !activeTransaction.isReadOnly()
					&& activeTransaction.getOwner().equals(Thread.currentThread());
			
			if (!usableTransactionExists) {
				// No active transaction means we have to execute the command as a
				// top-level command
				super.execute(command, options);
			} else {
				// An active transaction already exists, execute the command within its scope
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
		
		transactionLock.unlock();
	}
	
	@Override
	public void undo() {
		transactionLock.lock();
		
		triggerSave = false;
		super.undo();
		checkTriggerSaveAll();
	
		transactionLock.unlock();
	}
	
	@Override
	public void redo() {
		transactionLock.lock();
		
		triggerSave = false;
		super.redo();
		checkTriggerSaveAll();
	
		transactionLock.unlock();
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
	
	public ReentrantLock getTransactionLock() {
		return transactionLock;
	}
}
