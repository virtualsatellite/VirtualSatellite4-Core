/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.dvlm.roles;

import java.util.Collection;

import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.command.AbstractCommand;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.command.CommandParameter;
import org.eclipse.emf.edit.command.RemoveCommand;

import de.dlr.sc.virsat.model.dvlm.provider.DVLMEditPlugin;

/**
 * This class represents an EMF Command to wrap others EMF Commands. It checks
 * the associated rights to the object which will be altered by the original EMF
 * command and decide if this command can be executed or not.
 * 
 */
public class RoleManagementCheckCommand extends AbstractCommand implements Command {

	protected Command wrappedCommand;
	protected CommandParameter commandParameter;

	/**
	 * constructor for the role management check command
	 * 
	 * @param command
	 *            the command of the role management check
	 * @param commandParameter
	 *            the parameter of the role management check command
	 */
	public RoleManagementCheckCommand(Command command, CommandParameter commandParameter, IUserContext userContext) {
		super();
		this.wrappedCommand = command;
		this.commandParameter = commandParameter;
		this.userContext = userContext;
	}
	
	IUserContext userContext;
	
	/**
	 * hands back the command that is wrapped by this RMC
	 * @return the original command
	 */
	public Command getWrappedCommand() {
		return wrappedCommand;
	}

	@Override
	public boolean canExecute() {
		if (canBeExecutedByCurrentUser()) {
			return wrappedCommand.canExecute();
		} else {
			return false;
		}
	}

	@Override
	public void execute() {
		if (canBeExecutedByCurrentUser()) {
			DVLMEditPlugin.getPlugin().getLog()
					.log(new Status(Status.INFO, "DVLMEditPlugin", "RoleManagementCheckCommand: Executing with write Permission"));
			wrappedCommand.execute();
		} else {
			DVLMEditPlugin.getPlugin().getLog()
					.log(new Status(Status.INFO, "DVLMEditPlugin", "RoleManagementCheckCommand: No privileges to change data model"));
			throw new UserHasNoRightsException(
					"Current user does not have the rights to execute command: " + wrappedCommand);
		}
	}

	@Override
	public boolean canUndo() {
		return canBeExecutedByCurrentUser() && wrappedCommand.canUndo();
	}

	/**
	 * Check that current user has the rights to manipulate the object on which
	 * the command is acting
	 * 
	 * @return true or false
	 */
	private boolean canBeExecutedByCurrentUser() {
		EObject eObject = commandParameter.getEOwner();
		if (eObject != null && !RightsHelper.hasWritePermission(eObject, userContext)) {
			return false;
		}

		if (wrappedCommand instanceof RemoveCommand) {
			// For a remove operation the commandParameter.getEOwner() is the
			// parent of the object we are removing
			// Therefore we additionally need to check permissions for the
			// objects we are removing
			Collection<?> objectsToRemove = commandParameter.collection;
			for (Object obj : objectsToRemove) {
				if (!RightsHelper.hasWritePermission((EObject) obj, userContext)) {
					return false;
				}
			}
		}

		return true;
	}

	@Override
	public void undo() {
		wrappedCommand.undo();
	}

	@Override
	public void redo() {
		wrappedCommand.redo();
	}

	@Override
	public Collection<?> getResult() {
		return wrappedCommand.getResult();
	}

	@Override
	public Collection<?> getAffectedObjects() {
		return wrappedCommand.getAffectedObjects();
	}

	@Override
	public String getLabel() {
		return wrappedCommand.getLabel();
	}

	@Override
	public String getDescription() {
		return wrappedCommand.getDescription();
	}

	@Override
	public void dispose() {
		wrappedCommand.dispose();
		super.dispose();
	}
}
