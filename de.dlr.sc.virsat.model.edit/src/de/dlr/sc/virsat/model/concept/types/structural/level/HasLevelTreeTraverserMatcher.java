/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.concept.types.structural.level;

import java.util.ArrayList;
import java.util.List;

import de.dlr.sc.virsat.model.concept.types.structural.IBeanStructuralElementInstance;
import de.dlr.sc.virsat.model.concept.types.structural.tree.IBeanStructuralTreeTraverserMatcher;

/**
 * 
 *
 */
public class HasLevelTreeTraverserMatcher implements IBeanStructuralTreeTraverserMatcher {

	private List<IBeanStructuralElementInstance> levelElements = new ArrayList<>();
	private List<IHierarchyLevel> levels;
	
	/**
	 * Constructor
	 * @param levels levels to search for
	 */
	public HasLevelTreeTraverserMatcher(List<IHierarchyLevel> levels) {
		this.levels = levels;
	}
	
	
	@Override
	public boolean isMatching(IBeanStructuralElementInstance treeNode) {
		return hasLevel(treeNode);
	}

	@Override
	public void processMatch(IBeanStructuralElementInstance treeNode, IBeanStructuralElementInstance matchingParent) {
		levelElements.add(treeNode);
	}

	/**
	 * Check if the bean belongs to a level
	 * 
	 * @param bean
	 *            the bean to get level from
	 * @return the level
	 */
	private boolean hasLevel(IBeanStructuralElementInstance bean) {
		for (IHierarchyLevel level : levels) {
			if (level.isOnLevel(bean)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Return the found matches
	 * @return a list of all elements with level
	 */
	public List<IBeanStructuralElementInstance> getElementsWithLevel() {
		return levelElements;
	}
	
}
