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
 * Class for traversing a tree of structural element instances and calling
 * callback functions of a given matcher for matching nodes
 */
public class BeanStructuralTreeTraverser {
	
	private IBeanStructuralTreeTraverserMatcher matcher;
	
	/**
	 * Traverses the tree starting from the given root
	 * calling {@link IBeanStructuralTreeTraverserMatcher#isMatching(IBeanStructuralElementInstance)}
	 * on all nodes and {@link IBeanStructuralTreeTraverserMatcher#processMatch(IBeanStructuralElementInstance, IBeanStructuralElementInstance)}
	 * on all found matches
	 * @param root traverse the subtree of this bean
	 * @param matcher matcher for callbacks
	 */
	public void traverse(IBeanStructuralElementInstance root, IBeanStructuralTreeTraverserMatcher matcher) {
		this.matcher = matcher;
		traverseRecursive(root, null);
	}

	/**
	 * Traverse a subtree of a given node
	 * @param node the node from where to start traversing
	 * @param matchingParent the closest matching parent of node (or null)
	 */
	private void traverseRecursive(IBeanStructuralElementInstance node, IBeanStructuralElementInstance matchingParent) {
		// Remember the parent of the previous recursion
		IBeanStructuralElementInstance nextParent = matchingParent;
		
		// First check if the traverser found a match.
		// if yes start processing it, by handing over the node as well as its parent.
		// This is e.g. needed by the visualization to create the tree nodes for the
		// protobuf data model which can be transmitted to another visualization client.
		// then remember the current node as the next parent to hand over the correct parent
		// when processing the children.
		boolean isMatching = matcher.isMatching(node);
		if (isMatching) {
			matcher.processMatch(node, matchingParent);
			nextParent = node;
		}
		
		// Now check if the children should be processed.
		// if yes recursively loop over all of them.
		if (matcher.continueTraverseChildren(node, isMatching)) {
			for (IBeanStructuralElementInstance child : node.getChildren(IBeanStructuralElementInstance.class)) {
				traverseRecursive(child, nextParent);
			}
		}
	}
}
