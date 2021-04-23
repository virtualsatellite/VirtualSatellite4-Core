/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/

package de.dlr.sc.virsat.model.dvlm.tree;

import java.util.Collection;


/**
 * Interface for plugging different logic into {@link TreeTraverser}
 * For example for building a tree consisting only of nodes with a certain Category attached
 * @param <TYPE> The type for which the matcher should apply
 */
public interface ITreeTraverserMatcher<TYPE> {

	/**
	 * Checks if the passed treeNode bean matches the desired criteria
	 * @param treeNode node bean to check
	 * @return true if this node matches, false otherwise
	 */
	boolean isMatching(TYPE treeNode);
	
	/**
	 * Processes the found match
	 * @param treeNode a matching tree node
	 * @param matchingParent the closest parent of the node that also matches the criteria.
	 *        Can be null if the node does not have a matching parent
	 */
	void processMatch(TYPE treeNode, TYPE matchingParent);
	
	/**
	 * This method tells if the children of the current node should be processed or not.
	 * 
	 * Default behavior is that all children are processed.
	 * @param treeNode the node of which the children should be processed or not.
	 * @param treeLevel represents the level of nesting of the tree for the current node.
	 * @param matchedLevel represent the level of nested matches already identified up to the current node. 
	 * @param isMatching the matching result which the traverser evaluated before.
	 * @return true in case the children should be processed. false in case not.
	 */
	default boolean continueTraverseChildren(TYPE treeNode, boolean isMatching, int treeLevel, int matchedLevel) {
		return true;
	}
	
	/**
	 * Method that implements a getChildren functionality for the given object. It is supposed to hand back the logical
	 * direct children which will be processed next with the TreeTraverser
	 * @param treeNode The node for which to get children
	 * @return A collection containing the children
	 */
	Collection<? extends TYPE> getChildren(TYPE treeNode);
}
