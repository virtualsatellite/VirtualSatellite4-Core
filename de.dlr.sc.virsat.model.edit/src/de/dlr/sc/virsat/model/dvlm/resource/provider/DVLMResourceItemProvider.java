/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.dvlm.resource.provider;

import java.util.Collection;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.UnexecutableCommand;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.CommandParameter;
import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.provider.resource.ResourceItemProvider;

import de.dlr.sc.virsat.model.dvlm.roles.IUserContext;
import de.dlr.sc.virsat.model.dvlm.roles.RoleManagementCheckCommand;
import de.dlr.sc.virsat.model.dvlm.roles.UserRegistry;

/**
 * This item provider makes sure to override the create command correctly
 * With this item provider we can now delete and remove items from a resources content using
 * the whole EMF command framework
 * @author fisc_ph
 *
 */
public class DVLMResourceItemProvider extends ResourceItemProvider {

	/**
	 * this class constructor is an instance of the factory and notifier 
	 * @param adapterFactory 
	 */
	public DVLMResourceItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}
	
	@Override
	public Command createCommand(Object object, EditingDomain domain, Class<? extends Command> commandClass, CommandParameter commandParameter) {
		
		// Set the UserContext either from the SystemUserRegistry or
		// from the Domain if it exists
		IUserContext userContext = UserRegistry.getInstance();
		if (domain instanceof IUserContext) {
			userContext = (IUserContext) domain;
		}
		
		// For all other commands get the original one
		Command originalCommand = doCreateCommand(object, domain, commandClass, commandParameter);
				
		// A RolemanagementCheckCommand should not necessarily be wrapped into another RoleManagementCheck Command
		if (originalCommand instanceof RoleManagementCheckCommand) {
			return originalCommand;
		} else {
			// And wrap it into our command checking for the proper access rights
			return new RoleManagementCheckCommand(originalCommand, commandParameter, userContext);	
		}
	}

	/**
	 * Delegated Method to create a command on a resource
	 * @param object as with all other commands
	 * @param domain as with all other commands
	 * @param commandClass as with all other commands
	 * @param commandParameter as with all other commands
	 * @return a Command working on the resource
	 */
	protected Command doCreateCommand(Object object, EditingDomain domain, Class<? extends Command> commandClass, CommandParameter commandParameter) {
		// Create a command to add an EObject to a Resource
		if (commandClass == AddCommand.class) {
			Command cmd;
			Object owner =  commandParameter.getOwner();
			if (owner instanceof Resource) {
				Resource resource = (Resource) owner;
				Collection<?> addObjects = commandParameter.getCollection();
				cmd = new AddCommand(domain, resource.getContents(), addObjects);
			} else {
				cmd = UnexecutableCommand.INSTANCE;
			}
			return cmd;
		} else if (commandClass == RemoveCommand.class) {
			// Create a Command to remove an Eobject from a Resource
			Command cmd;
			Object owner =  commandParameter.getOwner();
			if (owner instanceof Resource) {
				Resource resource = (Resource) owner;
				Collection<?> removeObjects = commandParameter.getCollection();
				cmd = new RemoveCommand(domain, resource.getContents(), removeObjects);
			} else {
				cmd = UnexecutableCommand.INSTANCE;
			}
			return cmd;
		} else {
			// The rest can be done in the standard way
			return super.createCommand(object, domain, commandClass, commandParameter);
		}
	}
}
