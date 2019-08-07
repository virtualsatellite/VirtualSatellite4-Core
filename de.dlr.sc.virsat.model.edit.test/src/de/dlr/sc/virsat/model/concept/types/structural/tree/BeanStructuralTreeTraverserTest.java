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

import org.junit.BeforeClass;
import org.junit.Test;

import de.dlr.sc.virsat.model.concept.types.structural.BeanStructuralElementInstance;
import de.dlr.sc.virsat.model.concept.types.structural.IBeanStructuralElementInstance;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElement;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralFactory;

/**
 * Test class for BeanStructuralTreeTraverser
 */
public class BeanStructuralTreeTraverserTest {

	private static StructuralElement se;
	
	@BeforeClass
	public static void setUpClass() {
		se = StructuralFactory.eINSTANCE.createStructuralElement();
		se.setIsApplicableForAll(true);
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
		
		final int THREE = 3;
		assertEquals(THREE, matcher.foundMatches.size());
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
		
		final int THREE = 3;
		assertEquals(THREE, matcher.foundMatches.size());
		assertEquals(null, matcher.foundMatches.get(n2));
		assertEquals(null, matcher.foundMatches.get(n1));
		assertEquals(n1, matcher.foundMatches.get(n12));
	}
	
	/**
	 * @param name 
	 * @return a new bean with a given name
	 */
	private IBeanStructuralElementInstance createBean(String name) {
		StructuralElementInstance sei = StructuralFactory.eINSTANCE.createStructuralElementInstance();
		sei.setType(se);
		sei.setName(name);
		return new BeanStructuralElementInstance(sei);
	}
}
