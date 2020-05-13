/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.dvlm.structural.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;

import de.dlr.sc.virsat.model.dvlm.categories.ICategoryAssignmentContainer;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElement;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;


/**
 * Class to provide useful methods for StructuralElementInstances
 */
public class StructuralElementInstanceHelper {
	
	private StructuralElementInstance sei;
	
	/**
	 * Constructor
	 * 
	 * @param sei The Structural Element Instance this helper should work on.
	 */
	public StructuralElementInstanceHelper(StructuralElementInstance sei) {
		this.sei = sei;
	}

	/**
	 * Hand back the root CategoryAssignmentContainer 
	 * 
	 * @return the structural element instance as the root
	 */
	public ICategoryAssignmentContainer getRoot() {
		StructuralElementInstance root = sei;
		while (root.getParent() != null) {
			root = root.getParent();
		}	
		return root;
	}
	
	/**
	 * Method to get all children contained in a SEI of a specific Type, that is specified by the type's ID
	 * @param sei the SEI in which to search for the specific SEIs
	 * @param seId the type ID as FQN
	 * @return a List with the child SEIs that have the given type with the given FQN
	 */
	public static List<StructuralElementInstance> getStructuralElementInstances(StructuralElementInstance sei, String seId) {
		List<StructuralElementInstance> childSeis = new LinkedList<>();
		
		for (StructuralElementInstance childSei :  sei.getChildren()) {
			StructuralElement se = childSei.getType();
			if (se != null) {
				if (seId.equals(se.getFullQualifiedName())) {
					childSeis.add(childSei);
				}
			}
		}
		
		return childSeis;
	}
	
	public static final int DEPTH_INFINITE = -1;
	
	/**
	 * Gets the deep children of a structural element instance until a given depth
	 * @param sei the structural element instance
	 * @param depth the max depth
	 * @param currentDepth the current depth
	 * @return a list of deeply nested children up to the level of the given depth
	 */
	public static List<StructuralElementInstance> getDeepChildren(StructuralElementInstance sei, int depth, int currentDepth) {
		List<StructuralElementInstance> deepChildren = new ArrayList<>();

		if (depth == DEPTH_INFINITE || currentDepth < depth) {
			for (StructuralElementInstance structuralElementInstance : sei.getChildren()) {
				deepChildren.add(structuralElementInstance);
				List<StructuralElementInstance> nestedChildren = getDeepChildren(structuralElementInstance, depth, currentDepth + 1);
				deepChildren.addAll(nestedChildren);
			}
		}
		
		return deepChildren;
	}

	/**
	 * @param sei 
	 * @return direct and indirect super SEIs of the passed sei
	 */
	public static Set<StructuralElementInstance> getAllSuperSeis(StructuralElementInstance sei) {
		Set<StructuralElementInstance> superSeis = new HashSet<>();
		for (StructuralElementInstance superSei: sei.getSuperSeis()) {
			superSeis.add(superSei);
			superSeis.addAll(getAllSuperSeis(superSei));
		}
		return superSeis;
	}
	
	/**
	 * Creates a new list which does not contain SEIs that are marked for deletion whose parents are also marked for deletion
	 * for example, A contains B, and both are selected for deletion. In this case we need to remove B as it will be deleted anyway with A
	 * @param seisToDelete Collection of SEIs that are selected for deletion
	 * @return list of SEIs without children that will be deleted anyway
	 */
	public static ArrayList<StructuralElementInstance> cleanFromIndirectSelectedChildren(Collection<StructuralElementInstance> seisToDelete) {
		Set<EObject> selectedToBeDeleted = new HashSet<>(seisToDelete);
		ArrayList<StructuralElementInstance> seisToDeleteWithoutDuplicateChildren = new ArrayList<>();
		
		// Loop over all SEIs and see if it is indirectly selected by one of its parents.
		for (StructuralElementInstance sei : seisToDelete) {
			
			// Now find a parent to the SEI, which is not yet selected for being deleted
			// Basically this means, check if the current selection is already indirectly selected by a parent
			// If yes it does not need to be added to the actual list of SEIs that should be deleted
			// since it will be deleted by the parent as well.
			EObject parent = sei.eContainer();
			while (parent instanceof StructuralElementInstance && !selectedToBeDeleted.contains(parent)) {
				parent = parent.eContainer();
			}
			
			// Now if the selected SEI does not have a parent which is also selected,
			// than it has to be explicitly be deleted.
			if (!(parent instanceof StructuralElementInstance)) {
				seisToDeleteWithoutDuplicateChildren.add(sei); 
			}
		}
		return seisToDeleteWithoutDuplicateChildren;
	}
}
