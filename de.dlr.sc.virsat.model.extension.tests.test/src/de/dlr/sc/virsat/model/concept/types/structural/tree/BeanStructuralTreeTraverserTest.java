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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.hasItems;

import org.junit.Before;
import org.junit.Test;

import de.dlr.sc.virsat.model.concept.types.structural.IBeanStructuralElementInstance;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.extension.tests.model.AConceptTestCase;
import de.dlr.sc.virsat.model.extension.tests.model.TestStructuralElement;

/**
 * Test class for BeanStructuralTreeTraverser
 */
public class BeanStructuralTreeTraverserTest extends AConceptTestCase {

	private Concept concept;

	@Before
	public void setup() {
		concept = loadConceptFromPlugin();
	}

	@Test
	public void testNoMatch() {
		IBeanStructuralElementInstance parent = createBean("A");
		IBeanStructuralElementInstance child = createBean("B");
		IBeanStructuralElementInstance grandChild = createBean("C");
		parent.add(child);
		child.add(grandChild);
		
		MockMatcher matcher = new MockMatcher(node -> false);
		BeanStructuralTreeTraverser traverser = new BeanStructuralTreeTraverser();
		traverser.traverse(parent, matcher);
		assertTrue(matcher.foundMatches.isEmpty());
	}

	@Test
	public void testAllMatch() {
		IBeanStructuralElementInstance parent = createBean("A");
		IBeanStructuralElementInstance child = createBean("B");
		IBeanStructuralElementInstance grandChild = createBean("C");
		parent.add(child);
		child.add(grandChild);
		
		MockMatcher matcher = new MockMatcher(node -> true);
		BeanStructuralTreeTraverser traverser = new BeanStructuralTreeTraverser();
		traverser.traverse(parent, matcher);
		
		final int EXPECTED_MATCHES = 3;
		assertEquals(EXPECTED_MATCHES, matcher.foundMatches.size());
		assertEquals(null, matcher.foundMatches.get(parent));
		assertEquals(parent, matcher.foundMatches.get(child));
		assertEquals(child, matcher.foundMatches.get(grandChild));
	}

	@Test
	public void testSomeMatch() {
		IBeanStructuralElementInstance n = createBean("n");
		IBeanStructuralElementInstance n1 = createBean("n1_matches");
		IBeanStructuralElementInstance n11 = createBean("n11");
		IBeanStructuralElementInstance n12 = createBean("n12_matches");
		IBeanStructuralElementInstance n2 = createBean("n2_matches");
		n.add(n1);
		n.add(n2);
		n1.add(n11);
		n1.add(n12);
		
		MockMatcher matcher = new MockMatcher(node -> node.getName().endsWith("matches"));
		BeanStructuralTreeTraverser traverser = new BeanStructuralTreeTraverser();
		traverser.traverse(n, matcher);
		
		final int EXPECTED_MATCHES = 3;
		// Check that the correct amount of items are found and that the correct items are found as well
		assertEquals(EXPECTED_MATCHES, matcher.foundMatches.size());
		assertThat("Found correct content", matcher.foundMatches.keySet(), hasItems(n1, n12, n2));
		
		// There is no closest matching parent identified for n1 and n2 since there is
		// no match closer to the root from their point in the tree
		assertEquals(null, matcher.foundMatches.get(n2));
		assertEquals(null, matcher.foundMatches.get(n1));
		
		// n12 has a closest matching parent which is n1
		assertEquals(n1, matcher.foundMatches.get(n12));
	}
	
	@Test
	public void testSomeMatchWithLimitedRecusion() {
		IBeanStructuralElementInstance n = createBean("n");
		IBeanStructuralElementInstance n1 = createBean("n1_matches");
		IBeanStructuralElementInstance n11 = createBean("n11");
		IBeanStructuralElementInstance n12 = createBean("n12_matches");
		IBeanStructuralElementInstance n2 = createBean("n2_matches");
		n.add(n1);
		n.add(n2);
		n1.add(n11);
		n1.add(n12);
		
		MockMatcher matcher = new MockMatcher(node -> node.getName().endsWith("matches")) {
			@Override
			public boolean continueTraverseChildren(IBeanStructuralElementInstance treeNode, boolean isMatching, int treeLevel, int matchingLevel) {
				return !(treeNode.equals(n1));
			}
		};
		BeanStructuralTreeTraverser traverser = new BeanStructuralTreeTraverser();
		traverser.traverse(n, matcher);
		
		final int EXPECTED_MATCHES = 2;
		assertEquals(EXPECTED_MATCHES, matcher.foundMatches.size());
		assertThat("Found correct content", matcher.foundMatches.keySet(), hasItems(n1, n2));
	}

	/**
	 * @param name 
	 * @return a new bean with a given name
	 */
	private IBeanStructuralElementInstance createBean(String name) {
		TestStructuralElement bean = new TestStructuralElement(concept);
		bean.setName(name);
		return bean;
	}
}
