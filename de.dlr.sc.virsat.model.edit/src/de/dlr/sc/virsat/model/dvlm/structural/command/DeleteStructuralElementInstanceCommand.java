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

import java.util.Collection;
import java.util.Collections;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.command.DeleteCommand;
import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.emf.edit.domain.EditingDomain;

import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.APropertyInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ArrayInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ComposedPropertyInstance;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;

/**
 * This class helps to create a command that knows exactly how to remove/delete a SEI
 * from a Project. Rather than the standard EMF Delete Command this one knows that the SEI
 * is contained in two places, the resource and another SEI e.g. its parent. This is a special
 * feature of EMF which the standard Delete does not handle correctly. 
 * @author fisc_ph
 *
 */
public class DeleteStructuralElementInstanceCommand extends DeleteCommand {

	
	/**
	 * Helper method to create a DeleteCommand to delete a SEI
	 * @param domain the editing domain to remove the SEI
	 * @param object The SEI to be deleted
	 * @return The correct Command to delete the SYEI
	 */
	public static DeleteStructuralElementInstanceCommand create(EditingDomain domain, Object object) {
		return new DeleteStructuralElementInstanceCommand(domain, Collections.singleton(object));
	}
	
	/**
	 * Constructor to remove a SEI or several SEIs in a collection
	 * @param domain the domain in which to delete the SEIs
	 * @param collection the SEIs to be deleted
	 */
	public DeleteStructuralElementInstanceCommand(EditingDomain domain, Collection<?> collection) {
		super(domain, collection);
	}
	
	@Override
	protected void prepareCommand() {
		super.prepareCommand();
		
		// Append a Command to remove the SEI from the Resource for each SEI that should be deleted
		// This is needed because we use cross resource containments! E.g. a Configuration Element is nested
		// in another CE which contains the other one as its child. It is not just referenced as EMF default settings suggest.
		// In this case the CE are contained twice, first by their parent, and second by their resource. The standard delete
		// command just deletes the containment from the other EObject / CE. As a consequence this Delete Command clears
		// the resource containment as well!
		collection.forEach((object) -> {
			if (object instanceof StructuralElementInstance) {
				StructuralElementInstance sei = (StructuralElementInstance) object;
				Resource resource = sei.eResource();
				EObject parentObject = sei.eContainer();
				if (parentObject != null) {
					Command removeSeiFromResource = RemoveCommand.create(domain, resource, Resource.RESOURCE__CONTENTS, sei);
					append(removeSeiFromResource);
				}
			}
			
			// If we are deleting a category assignment we need to check if it is contained in a composed property instance.
			// If that is the case we also need to delete that composed property instance.
			if (object instanceof CategoryAssignment) {
				CategoryAssignment sei = (CategoryAssignment) object;
				EObject parentObject = sei.eContainer();
				if (parentObject instanceof ComposedPropertyInstance) {
					Command deleteContainingCPI = DeleteCommand.create(domain, parentObject);
					append(deleteContainingCPI);
				}
			}
		});
	}
	
	@Override
	public boolean canExecute() {
		// the command should not execute if a Property Instance is selected,
		// otherwise it could happen that a PI is removed from a CA which does not make sense
		boolean containsNoPi = collection.stream().filter((obj) -> {
			if (obj instanceof APropertyInstance) {
				APropertyInstance pi = (APropertyInstance) obj;
				EObject container = pi.eContainer();
				return !(container instanceof ArrayInstance);
			}
			return false;
		}).count() == 0;
		return containsNoPi && super.canExecute(); 
	}
	
}