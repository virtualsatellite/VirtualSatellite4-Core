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


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.junit.Before;
import org.junit.Test;

import de.dlr.sc.virsat.model.dvlm.structural.StructuralElement;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralFactory;

public class TreeTraverserTest {

	StructuralElementInstance seiRoot;
	StructuralElementInstance seiChild1;
	StructuralElementInstance seiChild1Child;
	StructuralElementInstance seiChild1ChildChild;
	StructuralElementInstance seiChild2;
	StructuralElementInstance seiChild2Child;
	StructuralElementInstance seiChild2ChildChild;
	
	public static final String NO_NAME = "NoSpecificName";
	
	@Before
	public void setUp() throws Exception {
		StructuralElement se = StructuralFactory.eINSTANCE.createStructuralElement();
		
		seiRoot = StructuralFactory.eINSTANCE.createStructuralElementInstance();
		seiChild1 = StructuralFactory.eINSTANCE.createStructuralElementInstance();
		seiChild1Child = StructuralFactory.eINSTANCE.createStructuralElementInstance();
		seiChild1ChildChild = StructuralFactory.eINSTANCE.createStructuralElementInstance();
		seiChild2 = StructuralFactory.eINSTANCE.createStructuralElementInstance();
		seiChild2Child = StructuralFactory.eINSTANCE.createStructuralElementInstance();
		seiChild2ChildChild = StructuralFactory.eINSTANCE.createStructuralElementInstance();
		
		se.setIsApplicableForAll(true);
		
		seiRoot.setType(se);
		seiChild1.setType(se);
		seiChild1Child.setType(se);
		seiChild1ChildChild.setType(se);
		seiChild2.setType(se);
		seiChild2Child.setType(se);
		seiChild2ChildChild.setType(se);
		
		seiRoot.getChildren().add(seiChild1);
		seiRoot.getChildren().add(seiChild2);
		seiChild1.getChildren().add(seiChild1Child);
		seiChild2.getChildren().add(seiChild2Child);
		seiChild1Child.getChildren().add(seiChild1ChildChild);
		seiChild2Child.getChildren().add(seiChild2ChildChild);
		
		// Now set names for the 2 level and for the 3 level,
		// Try to find them and check if the traverser reacts as expected
		
		seiChild1.setName(NO_NAME);
		seiChild2.setName(NO_NAME);
		seiRoot.setName("Root");
		seiChild1Child.setName("ChildOf1");
		seiChild2Child.setName("ChildOf2");
		seiChild1ChildChild.setName("DoubleChildOf1");
		seiChild2ChildChild.setName("DoubleChildOf2");
	}

	/**
	 * Class to capture the results from the tree traversing
	 */
	static class Result {

		Result(StructuralElementInstance sei, int processedLevel, int matchedLevel) {
			this.sei = sei;
			this.nestingLevel = processedLevel;
			this.matchingLevel = matchedLevel;
		}

		StructuralElementInstance sei;
		int nestingLevel;
		int matchingLevel;
		
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + Objects.hash(matchingLevel, nestingLevel, sei);
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (obj instanceof Result) {
				Result other = (Result) obj;
				return matchingLevel == other.matchingLevel && nestingLevel == other.nestingLevel && Objects.equals(sei, other.sei);
			} else {
				return false;
			}
		}
	}
	
	@Test
	public void testTraverse() {
		List<Result> traverseResults = new LinkedList<>();
		Map<StructuralElementInstance, StructuralElementInstance> mapToParentResults = new HashMap<>();
		
		TreeTraverser<StructuralElementInstance> traverser = new TreeTraverser<>();
		
		traverser.traverse(seiRoot, new IStructuralElementInstanceTreeTraverserMatcher() {
			@Override
			public void processMatch(StructuralElementInstance treeNode, StructuralElementInstance matchingParent) {
				mapToParentResults.put(treeNode, matchingParent);
			}
			
			@Override
			public boolean isMatching(StructuralElementInstance treeNode) {
				return !NO_NAME.equals(treeNode.getName());
			}
			
			@Override
			public boolean continueTraverseChildren(StructuralElementInstance treeNode, boolean isMatching, int nestingLevel, int matchingLevel) {
				traverseResults.add(new Result(treeNode, nestingLevel, matchingLevel));
				
				// Try to make sure that the seiChild2ChildChild which is theoretically correct, still does not get found
				boolean isChild2Child = treeNode == seiChild2Child;

				// Also call the default implementation of the interface to have it included in the test.
				return !isChild2Child && IStructuralElementInstanceTreeTraverserMatcher.super.continueTraverseChildren(treeNode, isMatching, nestingLevel, matchingLevel);
			}
		});
		
		assertTrue("Identified matching parent of root, it is null but should be in the list of keys", mapToParentResults.keySet().contains(seiRoot));
		assertFalse("There are no matches identified for the direct child1 and child2 thus no keys", mapToParentResults.keySet().contains(seiChild1));
		assertFalse("There are no matches identified for the direct child1 and child2 thus no keys", mapToParentResults.keySet().contains(seiChild2));
		assertFalse("There are no matches identified for the double Child in branch 2", mapToParentResults.keySet().contains(seiChild2ChildChild));
		
		assertEquals("Identified matching parent of root, which is null", null, mapToParentResults.get(seiRoot));
		assertEquals("Identified matching parent of child1Child, which is root", seiRoot, mapToParentResults.get(seiChild1Child));
		assertEquals("Identified matching parent of child2Child, which is root", seiRoot, mapToParentResults.get(seiChild2Child));
		assertEquals("Identified matching parent of child1ChildChild, which is child1Child", seiChild1Child, mapToParentResults.get(seiChild1ChildChild));
		
		//CHECKSTYLE:OFF
		assertThat("Found all correct Items with correct counting", traverseResults, contains(
			new Result(seiRoot, 1, 1),
			new Result(seiChild1, 2, 1),
			new Result(seiChild1Child, 3, 2),
			new Result(seiChild1ChildChild, 4, 3),
			new Result(seiChild2, 2, 1),
			new Result(seiChild2Child, 3, 2)
		));
		//CHECKSTYLE:ON
	}
}
