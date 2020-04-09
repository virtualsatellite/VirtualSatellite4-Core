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
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
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
	 * This workspace is used as lock to synchronize the workspace operations and
	 * the transactions on the editing domain. E.g. The builder starts and locks the
	 * workspace, then it tries to place a non undoable command into the stack. in the meantime
	 * a user operation created a new SEI, this has been placed as a command into the stack in between.
	 * This command will try to write to the workspace which is locked. The builder will get stuck
	 * because it tries to execute a new command on the stack but cannot acquire the transaction.
	 * 
	 * As a way out, this lock will be used to first synchronize all operations executing a command.
	 * This ensures that a command is always executed after another one. Second every command will first try
	 * to lock the workspace. Then it will try to get the transaction. Commands which are already in a locked 
	 * workspace will reenter the lock.
	 */
	private IWorkspace wsProject;
	
	/**
	 * Constructor to the Command Stack with the OperationsHistory
	 * @param history The operation history to be used with this domain
	 */
	public VirSatWorkspaceCommandStack(IOperationHistory history) {
		super(history);
		wsProject = ResourcesPlugin.getWorkspace();
	}
	
	/**
	 * Call this method from the calls for executing a command. This method maintains the order
	 * of executing the command as a wrapped workspace operation as well as saving the files in the
	 * right point of time.
	 * @param runnable the runnable that implements the call to the execution of the command
	 */
	protected synchronized void executeInWorkspaceWithSaveCheck(Runnable runnable) {
		Activator.getDefault().getLog().log(new Status(Status.INFO, Activator.getPluginId(), "VirSatWorkspaceCommandStack: Starting to execute command as workspace operation"));

		try {
			triggerSave = false;
			// Run all execute, undo, et.c in a workspace operation. This way deadlocks can be avoided,
			// since no two commands can run at the same time. There used to be deadlocks with the builders
			// which were obtaining locks in the opposite order. E.g. when creating a SEI first the Command was executed
			// obtaining a lock on the Editing domain, then obtaining a lock on the Workspace. Meanwhile the builder could start
			// obtaining a lock on the workspace, then executing a command obtaining a lock on the ED.
			// Now both, first have to get the Lock on the Workspace, then on the Editing domain.
			wsProject.run(action -> runnable.run(), null);
			
			// now check if something asked in between to issue a save operation on all resources
			// this call cannot be placed into the Workspace Operation, WWorkspace operations scheduled by the 
			// command execution need to be able to execute first. 
			checkTriggerSaveAll();
		} catch (CoreException e) {
			Activator.getDefault().getLog().log(new Status(Status.ERROR, Activator.getPluginId(), "VirSatWorkspaceCommandStack: Failed to execute command as workspace operation", e));
		}
		Activator.getDefault().getLog().log(new Status(Status.INFO, Activator.getPluginId(), "VirSatWorkspaceCommandStack: Finished to execute command as workspace operation"));
	}
	
	/**
	 * Method to execute a command and directly take it out of the
	 * history, so it cannot be undone. The Transaction.OPTION_NO_UNDO 
	 * seems to be not implemented.
	 * @param command The command to be executed.
	 */
	public void executeNoUndo(Command command) {
		Activator.getDefault().getLog().log(new Status(Status.INFO, Activator.getPluginId(), "VirSatWorkspaceCommandStack: Execute Command with no undo"));
		
		editingDomain.executeInWorkspace(() -> {
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
		});

		Activator.getDefault().getLog().log(new Status(Status.INFO, Activator.getPluginId(), "VirSatWorkspaceCommandStack: Finished execute Command with no undo"));
	}
	
	@Override
	public void execute(Command command) {
		execute(command, null);
	}
	
	@Override
	public void execute(Command command, Map<?, ?> options) {
		Activator.getDefault().getLog().log(new Status(Status.INFO, Activator.getPluginId(), "VirSatWorkspaceCommandStack: Execute Command"));

		// In case the command that shall be executed is a Recording Command,
		// it will be wrapped into VirSatRecordingCommand. This command can be 
		// easily distinguished in the stack as a command that was processed here.
		if (command instanceof RecordingCommand) {
			command = new VirSatRecordingCommand(command);
		}
		
		final Command executeCommand = command;

		executeInWorkspaceWithSaveCheck(() -> {
			try {
				// Try to see if there is already a transaction going on in the editing domain,
				// which is associated with this Command Stack.
				InternalTransaction activeTransaction = getDomain().getActiveTransaction();
				
				// In case it exists, we want to now check if it is reusable for us. Which means
				// like if a recording command is executed, which also tries to remove a file from a SEI,
				// These commands shall be executed in one atomic transaction. This is implemented for making 
				// Graphitti Framework work for us. Important is, that a transaction can only be rentered by
				// the same thread. Thus commands within a recording command can be placed into the same transaction
				// A command from a different thread can not be placed into the already open transaction.
				boolean usableTransactionExists = activeTransaction != null && !activeTransaction.isReadOnly()
						&& activeTransaction.getOwner().equals(Thread.currentThread());
				
				// The standard case, is that there is a new command which should be executed.
				// Since there is usually no transaction yet, it will be forwarded to the Standard Workspace
				// Command Stack which opens a transaction and starts executing the command. Or in case there is a transaction
				// it will be just placed on the stack as the next command.
				if (!usableTransactionExists) {
					Activator.getDefault().getLog().log(new Status(Status.INFO, Activator.getPluginId(), "VirSatWorkspaceCommandStack: Execute command to execute in its own transaction"));
					// No active transaction means we have to execute the command as a
					// top-level command
					super.execute(executeCommand, options);
				} else {
					// now where a transaction for the same thread exists, the command itself will be executed
					// within exactly that transaction. In case that the command which is already executed by the 
					// transaction is a VirtualSatelliteRecordCommand, the executed command will be chained to it.
					// This way it looks to the CommandStack as if only the VirSatRecordingCommand was executed.
					// Now when e.g. an undo is triggered, the chained commands will be undone as well within the
					// same atomic transaction.
					if (executeCommand != null && executeCommand.canExecute()) {
						Activator.getDefault().getLog().log(new Status(Status.INFO, Activator.getPluginId(), "VirSatWorkspaceCommandStack: Execute Command within existing write transaction"));
						executeCommand.execute();
						if (getActiveCommand() instanceof VirSatRecordingCommand) {
							Activator.getDefault().getLog().log(new Status(Status.INFO, Activator.getPluginId(), "VirSatWorkspaceCommandStack: Chaining command to VirSatRecordingCommand"));
							getActiveCommand().chain(executeCommand);
						}
					}
				}
			} catch (InterruptedException | RollbackException e) {
				Activator.getDefault().getLog().log(new Status(IStatus.ERROR, Activator.getPluginId(), "Failed to execute command", e));
			}
		});
	}
	
	@Override
	public void undo() {
		Activator.getDefault().getLog().log(new Status(Status.INFO, Activator.getPluginId(), "VirSatWorkspaceCommandStack: Undo Command"));
		executeInWorkspaceWithSaveCheck(() -> {
			super.undo();
		});
	}
	
	@Override
	public void redo() {
		Activator.getDefault().getLog().log(new Status(Status.INFO, Activator.getPluginId(), "VirSatWorkspaceCommandStack: Redo Command"));
		executeInWorkspaceWithSaveCheck(() -> {
			super.redo();
		});
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
	public synchronized void triggerSaveAll() {
		Activator.getDefault().getLog().log(new Status(Status.INFO, Activator.getPluginId(), "VirSatWorkspaceCommandStack: Save all Triggered by Thread: " + Thread.currentThread().getName()));
		triggerSave = true;
	}
	
	/**
	 * Check if the triggerSave flag has been set. If so, ask the editing domain to save everything
	 * and reset the flag.
	 */
	private synchronized void checkTriggerSaveAll() {
		Activator.getDefault().getLog().log(new Status(Status.INFO, Activator.getPluginId(), "VirSatWorkspaceCommandStack: Check for save trigger"));
		if (triggerSave) {
			Activator.getDefault().getLog().log(new Status(IStatus.INFO, Activator.getPluginId(), "VirSatWorkspaceCommandStack: Save tiggered Scheduling Save Job."));
			// The trigger boolean to save all may be set from a command that requires saving. e.g. creating a new SEI.
			// When this command is executed, it will be given to the command stack wrapping it into a workspace
			// locked operation and running it in executeInWorkspaceWithSaveCheck. This method will:
			// 1. set the boolean to false
			// 2. execute the commands which may set the trigger
			// 3. call the save all
			// Synchronizing all involved methods ensures that no other command can be executed. In consequence the
			// triggerSave variable cannot be altered unexpectedly between executing a command and saving.
			editingDomain.saveAll(true, false);
		}
	}
}
