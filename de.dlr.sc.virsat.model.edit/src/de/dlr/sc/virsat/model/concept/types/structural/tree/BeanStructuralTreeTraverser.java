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
	IBeanStructuralTreeTraverserMatcher matcher;

	/**
	 * Setter
	 * @param matcher new matcher
	 */
	public void setMatcher(IBeanStructuralTreeTraverserMatcher matcher) {
		this.matcher = matcher;
	}

	/**
	 * Traverses the tree starting from the given root
	 * calling {@link IBeanStructuralTreeTraverserMatcher#isMatching(IBeanStructuralElementInstance)}
	 * on all nodes and {@link IBeanStructuralTreeTraverserMatcher#foundMatch(IBeanStructuralElementInstance, IBeanStructuralElementInstance)}
	 * on all found matches
	 * @param root root of the tree to traverse
	 */
	public void traverse(IBeanStructuralElementInstance root) {
		
	}
}
