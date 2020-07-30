/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.dvlm.command;

import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.edit.command.DeleteCommand;
import org.eclipse.emf.edit.domain.EditingDomain;

import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ArrayInstance;
import de.dlr.sc.virsat.model.ecore.VirSatEcoreUtil;

/**
 * This class is a base class for making custom cascade delete commands that delete
 * referencing objects along with the actual object to delete
 * @author muel_s8
 *
 */

public abstract class CreateDeleteWithReferencingCategoryAssignmentsCommand {
	
	/**
	 * Gets all referencing instances. Since property instances itself are not a target for deletion,
	 * we need also need to figure out the actual object to delete.
	 * @param object the object to delete
	 * @param resSet the resource set
	 * @return the set of referencing instances
	 */
	
	public Set<EObject> getReferencingInstances(CategoryAssignment object, ResourceSet resSet) {
		Map<EObject, Set<EObject>> mapDeletedObjectReferencedBy = VirSatEcoreUtil.getReferencingObjectsForDelete(Collections.singleton(object), resSet);
		
		// Grab all the referencing objects and figure out the object that actually needs to be deleted
		Set<EObject> toDelete = new HashSet<>();
		for (Entry<EObject, Set<EObject>> entry : mapDeletedObjectReferencedBy.entrySet()) {
			Set<EObject> referencingObjects = entry.getValue();
			for (EObject referencingObject : referencingObjects) {
				EObject actualObjectToDelete = getActualObjectToDelete(referencingObject);
				if (actualObjectToDelete != null) {
					toDelete.add(actualObjectToDelete);
				}
			}
		}
		
		return toDelete;
	}
	
	/**
	 * Gets the actual object that should be deleted from the referencing object. Note that it may not always make sense
	 * to directly delete the referencing object. For example in the case reference property instances we dont want to
	 * delete the reference but rather the category assignment containing it.
	 * 
	 * Clients should override and customize this method to filter out objects that shouldnt be deleted.
	 * @param referencingObject the referencing object
	 * @return Returns null if no object should be deleted, otherwise the object that will be deleted
	 */
	protected EObject getActualObjectToDelete(EObject referencingObject) {
		if (referencingObject.eContainer() instanceof ArrayInstance) {
			return referencingObject;
		} 
		
		CategoryAssignment referencingCa = VirSatEcoreUtil.getEContainerOfClass(referencingObject, CategoryAssignment.class);
		return referencingCa;
	}

	
	/**
	 * Creates the delete command
	 * @param ed the editing domain
	 * @param eObject the object to delete
	 * @return the delete command
	 */
	public Command create(EditingDomain ed, CategoryAssignment eObject) {
		
		DeleteCommand deleteCommand = (DeleteCommand) DeleteCommand.create(ed, eObject);
		
		Set<EObject> referencingInstances = getReferencingInstances(eObject, ed.getResourceSet());
		for (EObject referencingInstance : referencingInstances) {
			deleteCommand.append(DeleteCommand.create(ed, referencingInstance));
		}
		
		return deleteCommand;
	}
}
