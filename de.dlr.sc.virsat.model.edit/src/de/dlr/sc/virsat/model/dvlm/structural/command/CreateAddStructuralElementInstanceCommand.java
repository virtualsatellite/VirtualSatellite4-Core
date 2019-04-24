/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.dvlm.structural.command;

import org.eclipse.emf.edit.domain.EditingDomain;

import de.dlr.sc.virsat.model.dvlm.DVLMPackage;
import de.dlr.sc.virsat.model.dvlm.Repository;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralPackage;

import java.util.Collection;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.UnexecutableCommand;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.ecore.EObject;

/**
 * generic method to create commands for adding new types of structural elements
 * this class takes care of the instantiation as well
 * @author fisc_ph
 *
 */
public class CreateAddStructuralElementInstanceCommand {
	
	/**
	 * Hidden constructor
	 */
	protected CreateAddStructuralElementInstanceCommand() {
	}
	
	/**
	 * Call this Method to create a command to add a new structural element to the model.
	 * The structural element will be initialized as StructuralElementInstance 
	 * @param editingDomain The Editing Domain in which to create the command
	 * @param owner the owner which is usually another StructuralELementInstance or a Repository
	 * @param sei The StructuralElementInstance that should be added
	 * @return The Command which initializes and nests the new StructuralELement
	 */
	public static Command create(EditingDomain editingDomain, EObject owner, StructuralElementInstance sei) {
		if (owner instanceof StructuralElementInstance) {
			return AddCommand.create(editingDomain, owner, StructuralPackage.Literals.STRUCTURAL_ELEMENT_INSTANCE__CHILDREN, sei);
		} else if (owner instanceof Repository) {
			return AddCommand.create(editingDomain, owner, DVLMPackage.Literals.REPOSITORY__ROOT_ENTITIES, sei);
		}
		
		return UnexecutableCommand.INSTANCE;
	}

	/**
	 * Call this Method to create a command to add a new structural element to the model.
	 * The structural element will be initialized as StructuralElementInstance 
	 * @param editingDomain The Editing Domain in which to create the command
	 * @param owner the owner which is usually another StructuralELementInstance or a Repository
	 * @param collection a collection of StructuralElementInstances that should be added
	 * @return The Command which initializes and nests the new StructuralELement
	 */
	public static Command create(EditingDomain editingDomain, EObject owner, Collection<?> collection) {
		if (owner instanceof StructuralElementInstance) {
			return AddCommand.create(editingDomain, owner, StructuralPackage.Literals.STRUCTURAL_ELEMENT_INSTANCE__CHILDREN, collection);
		} else if (owner instanceof Repository) {
			return AddCommand.create(editingDomain, owner, DVLMPackage.Literals.REPOSITORY__ROOT_ENTITIES, collection);
		}
		
		return UnexecutableCommand.INSTANCE;
	}
}
