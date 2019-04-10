/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.project.editingDomain.commands;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import de.dlr.sc.virsat.model.dvlm.Repository;
import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.APropertyInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ArrayInstance;
import de.dlr.sc.virsat.model.dvlm.roles.Discipline;
import de.dlr.sc.virsat.model.dvlm.roles.RoleManagement;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;

/**
 * A Helper Class for determining which objects are allowed to be selected
 * as a source and target for Copy Paste and Cut Commands
 * @author fisc_ph
 *
 */
public class VirSatClipboardCommandHelper {

	/** 
	 * Hidden Constructor
	 */
	private VirSatClipboardCommandHelper() {
	}
	
	/**
	 * Method to check if for a valid Collection for Cut or Copy Commands
	 * @param collection The Collection to be checked
	 * @return true in case it can be copied
	 */
	public static boolean isValidCollection(Collection<?> collection) {
		if (collection == null || collection.isEmpty()) {
			return false;
		}
		
		Set<Class<?>> classes = new HashSet<>();
		// Check all objects
		for (Object obj : collection) {
			// Check that the types we want to handle with the commands are supported
			if (!isValidSource(obj)) {
				return false;				
			}
			
			// And check that we only have one type of objects in the list, 
			// to make our life not more complicated than needed
			classes.add(obj.getClass());
			if (classes.size() > 1) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Check for a single object if it can deal as source for Copy and
	 * Cut Actions
	 * @param owner the object to be checked
	 * @return true in case it is valid
	 */
	public static boolean isValidSource(Object owner) {
		return ((owner instanceof StructuralElementInstance)
				|| (owner instanceof CategoryAssignment)
				|| ((owner instanceof APropertyInstance) && !(owner instanceof ArrayInstance))
				|| (owner instanceof Discipline));
	}
	
	/**
	 * Method to check if an object is suitable as target
	 * for a paste command
	 * @param owner the object to be checked
	 * @return true in case it can be used for paste comamnds
	 */
	public static boolean isValidTarget(Object owner) {
		if (owner instanceof APropertyInstance) {
			APropertyInstance pi = (APropertyInstance) owner;
			owner = pi.eContainer();
		}
		
		return ((owner instanceof Repository)
			|| (owner instanceof RoleManagement)
			|| (owner instanceof StructuralElementInstance)
			|| (owner instanceof CategoryAssignment)
			|| (owner instanceof ArrayInstance));
	}
	
	
	/**
	 * Method to check if a collection contains only SEI objects
	 * @param collection the collection to be checked
	 * @return true in case it is
	 */
	public static boolean isCollectionPi(Collection<?> collection) {
		return isCollectionOfType(collection, APropertyInstance.class);
	}
	
	/**
	 * Method to check if a collection contains only SEI objects
	 * @param collection the collection to be checked
	 * @return true in case it is
	 */
	public static boolean isCollectionSei(Collection<?> collection) {
		return isCollectionOfType(collection, StructuralElementInstance.class);
	}

	/**
	 * Method to check if a collection contains only Discipline objects
	 * @param collection the collection to be checked
	 * @return true in case it is
	 */
	public static boolean isCollectionDiscipline(Collection<?> collection) {
		return isCollectionOfType(collection, Discipline.class);
	}
	
	/**
	 * Method to check if a collection contains only CategoryAssignment objects
	 * @param collection the collection to be checked
	 * @return true in case it is
	 */
	public static boolean isCollectionCategoryAssignment(Collection<?> collection) {
		return isCollectionOfType(collection, CategoryAssignment.class);
	}

	/**
	 * Method to check if a collection contains only objects of a specific type 
	 * @param clazz The type of object that should be contained int the collection
	 * @param collection the collection to be checked
	 * @return true in case it is
	 */
	public static boolean isCollectionOfType(Collection<?> collection, Class<?> clazz) {
		for (Object obj : collection) {
			if (!clazz.isAssignableFrom(obj.getClass())) {
				return false;
			}
		}
		return true;
	}
}
