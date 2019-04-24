/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/

package de.dlr.sc.virsat.project.structure.command;

import java.util.Collection;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.domain.EditingDomain;

import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import de.dlr.sc.virsat.model.dvlm.structural.command.CreateAddStructuralElementInstanceCommand;
import de.dlr.sc.virsat.project.editingDomain.VirSatTransactionalEditingDomain;
import de.dlr.sc.virsat.project.resources.VirSatResourceSet;
import de.dlr.sc.virsat.project.resources.command.CreateSeiResourceAndFileCommand;

/**
 * Class that creates a command to add a StructuralElementInstance to
 * parent and create file structure for it in case it is possible.
 */
public class CreateAddSeiWithFileStructureCommand {

	private EObject owner;
	private EditingDomain editingDomain;
	private VirSatResourceSet virSatResourceSet;
	private CompoundCommand command;

	/**
	 * Hidden constructor for creating the Command. The Command adds a SEI as child to
	 * another one, and creates the necessary resources in the workspace if possible.
	 * @param editingDomain The EditingDomain in which the command will act. In case the EditingDomain
	 *  is of Type VirSatTransactionalEditingDomain, the file resources will be automatically generated
	 *  as well. With all other EditingDomains they won't.
	 * @param owner The EObject to add a new SEI into. Can be a Repository or another SEI
	 */
	private CreateAddSeiWithFileStructureCommand(EditingDomain editingDomain, EObject owner) {
		this.owner = owner;
		this.editingDomain = editingDomain;
		
		// Try to get the VirSat ResourceSet in case we are dealing with the correct EditingDomain
		if (editingDomain instanceof VirSatTransactionalEditingDomain) {
			virSatResourceSet = (VirSatResourceSet) editingDomain.getResourceSet();
		}
	}

	/**
	 * Creates a command that adds seiToAdd and all its children to owner
	 * @param seiToAdd 
	 * @return command that adds seiToAdd into owner
	 */
	private CompoundCommand createAddSeiWithFileStructureCommand(StructuralElementInstance seiToAdd) {
		command = new CompoundCommand();
		
		// In Case we know the VirSatResourceSet it can be used to actually create the workspace resources (files)
		// for the new StructuralElementInstance as well. In other cases only add the SEI as a new child to the given parent.
		if (virSatResourceSet != null) {
			command.append(new CreateSeiResourceAndFileCommand(virSatResourceSet, seiToAdd));
			for (StructuralElementInstance nestedSei : seiToAdd.getDeepChildren()) {
				command.append(new CreateSeiResourceAndFileCommand(virSatResourceSet, nestedSei));
			}
		}
		
		// Create the Command to actually add the new SEI to its parent which can either be a Repository or another SEI
		Command commandAddStructuralElementInstance = CreateAddStructuralElementInstanceCommand.create(editingDomain, owner, seiToAdd);
		command.append(commandAddStructuralElementInstance);
		
		return command;
	}
	
	/**
	 * Creates a command to add a given StructuralElementInstance to parent and create file structure for it if possible.
	 * @param editingDomain The EditingDomain in which the command will act. In case the EditingDomain
	 *  is of Type VirSatTransactionalEditingDomain, the file resources will be automatically generated
	 *  as well. With all other EditingDomains they won't.
	 * @param owner object to add StructuralElementInstance into
	 * @param seiToAdd StructuralElementInstance to add
	 * @return Command that adds seiToAdd into owner and generates file structure for seiToAdd
	 */
	public static Command create(EditingDomain editingDomain, EObject owner, StructuralElementInstance seiToAdd) {
		CreateAddSeiWithFileStructureCommand commandCreator = new CreateAddSeiWithFileStructureCommand(editingDomain, owner);
		CompoundCommand addSeiCommand = commandCreator.createAddSeiWithFileStructureCommand(seiToAdd);	
		return addSeiCommand;
	}
	
	/**
	 * Creates a command to add a given StructuralElementInstance to parent and create file structure for it if possible.
	 * @param editingDomain The EditingDomain in which the command will act. In case the EditingDomain
	 *  is of Type VirSatTransactionalEditingDomain, the file resources will be automatically generated
	 *  as well. With all other EditingDomains they won't.
	 * @param owner object to add StructuralElementInstance into
	 * @param seisToAdd collection SEIs that will be added to the given parent
	 * @return Command that adds seiToAdd into owner and generates file structure for seiToAdd
	 */
	public static Command create(EditingDomain editingDomain, EObject owner, Collection<?> seisToAdd) {
		CreateAddSeiWithFileStructureCommand commandCreator = new CreateAddSeiWithFileStructureCommand(editingDomain, owner);
		CompoundCommand collectionOfSeisToAddCommand = new CompoundCommand();
		
		seisToAdd.forEach((object) -> {
			if (object instanceof StructuralElementInstance) {
				StructuralElementInstance seiToAdd = (StructuralElementInstance) object;
				collectionOfSeisToAddCommand.append(commandCreator.createAddSeiWithFileStructureCommand(seiToAdd));
			}
		});
		
		return collectionOfSeisToAddCommand;	
	}
}
