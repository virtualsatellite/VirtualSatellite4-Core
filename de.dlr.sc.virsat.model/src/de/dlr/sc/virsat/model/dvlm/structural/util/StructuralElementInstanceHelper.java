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
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import de.dlr.sc.virsat.model.dvlm.categories.ICategoryAssignmentContainer;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElement;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;


/**
 * Class to provide useful methods for StructuralElementInstances
 * 
 * @author bell_er
 *
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
	
	/**
	 * Gets the deep children of a structural element instance until a given depth
	 * @param sei the structural element instance
	 * @param depth the max depth
	 * @param currentDepth the current depth
	 * @return a list of deeply nested children up to the level of the given depth
	 */
	public static List<StructuralElementInstance> getDeepChildren(StructuralElementInstance sei, int depth, int currentDepth) {
		List<StructuralElementInstance> deepChildren = new ArrayList<>();

		if (depth == -1 || currentDepth < depth) {
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
}
