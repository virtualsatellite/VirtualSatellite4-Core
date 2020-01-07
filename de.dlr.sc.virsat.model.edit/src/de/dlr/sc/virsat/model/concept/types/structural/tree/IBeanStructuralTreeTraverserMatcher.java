/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/

package de.dlr.sc.virsat.model.concept.types.structural.tree;

import de.dlr.sc.virsat.model.concept.types.structural.IBeanStructuralElementInstance;

/**
 * Interface for plugging different logic into {@link BeanStructuralTreeTraverser}
 * For example for building a tree consisting only of nodes with a certain Category attached
 */
public interface IBeanStructuralTreeTraverserMatcher {

	/**
	 * Checks if the passed treeNode bean matches the desired criteria
	 * @param treeNode node bean to check
	 * @return true if this node matches, false otherwise
	 */
	boolean isMatching(IBeanStructuralElementInstance treeNode);
	
	/**
	 * Processes the found match
	 * @param treeNode a matching tree node
	 * @param matchingParent the closest parent of the node that also matches the criteria.
	 *        Can be null if the node does not have a matching parent
	 */
	void processMatch(IBeanStructuralElementInstance treeNode, IBeanStructuralElementInstance matchingParent);
	
	/**
	 * This method tells if the children of the current node should be processed or not.
	 * 
	 * Default behavior is that all children are processed.
	 * @param treeNode the node of which the children should be processed or not.
	 * @param isMatching the matching result which the traverser evaluated before.
	 * @return true in case the children should be processed. false in case not.
	 */
	default boolean processChildren(IBeanStructuralElementInstance treeNode, boolean isMatching) {
		return true;
	}
}
