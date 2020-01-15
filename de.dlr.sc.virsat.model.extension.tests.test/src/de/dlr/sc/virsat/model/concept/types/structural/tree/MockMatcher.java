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

import static org.junit.Assert.assertFalse;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import de.dlr.sc.virsat.model.concept.types.structural.IBeanStructuralElementInstance;

/**
 * Mock matcher for test cases
 */
public class MockMatcher implements IBeanStructuralTreeTraverserMatcher {

	private Function<IBeanStructuralElementInstance, Boolean> matchingCriteria;
	Map<IBeanStructuralElementInstance, IBeanStructuralElementInstance> foundMatches = new HashMap<>();
	
	/**
	 * Creates a mock matcher with the given criteria
	 * @param matchingCriteria 
	 */
	public MockMatcher(Function<IBeanStructuralElementInstance, Boolean> matchingCriteria) {
		this.matchingCriteria = matchingCriteria;
	}

	@Override
	public void processMatch(IBeanStructuralElementInstance treeNode, IBeanStructuralElementInstance matchingParent) {
		assertFalse(foundMatches.containsKey(treeNode));
		foundMatches.put(treeNode, matchingParent);
	}

	@Override
	public boolean isMatching(IBeanStructuralElementInstance treeNode) {
		return matchingCriteria.apply(treeNode);
	}

}
