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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import de.dlr.sc.virsat.model.concept.types.structural.IBeanStructuralElementInstance;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.extension.tests.model.AConceptTestCase;
import de.dlr.sc.virsat.model.extension.tests.model.TestStructuralElement;

/**
 * Test class for {@link HierarchyLevelChecker}
 */
public class HierarchyLevelCheckerTest extends AConceptTestCase {

	private Concept concept;

	private IHierarchyLevel a;
	private IHierarchyLevel b;
	private IHierarchyLevel c;
	private IHierarchyLevel an;
	private IHierarchyLevel bn;
	private IHierarchyLevel cn;
	private IHierarchyLevel ao;
	private IHierarchyLevel bo;
	private IHierarchyLevel co;
	private IHierarchyLevel ano;

	@Before
	public void setup() {
		concept = loadConceptFromPlugin();

		a = createNameMatchingLevel("a", false, false);
		b = createNameMatchingLevel("b", false, false);
		c = createNameMatchingLevel("c", false, false);
		an = createNameMatchingLevel("a", true, false);
		bn = createNameMatchingLevel("b", true, false);
		cn = createNameMatchingLevel("c", true, false);
		ao = createNameMatchingLevel("a", false, true);
		bo = createNameMatchingLevel("b", false, true);
		co = createNameMatchingLevel("c", false, true);
		ano = createNameMatchingLevel("a", true, true);
	}

	@Test
	public void testSingleBean() {
		HierarchyLevelChecker checker = getChecker(a);
		IBeanStructuralElementInstance bean = createBean("a");

		assertEquals(expected(a), checker.getApplicableLevels(bean));
		assertTrue(checker.validateApplicableLevel(bean));
	}

	@Test
	public void testSingleBean2() {
		HierarchyLevelChecker checker = getChecker(a);
		IBeanStructuralElementInstance bean = createBean("x");

		assertEquals(expected(a), checker.getApplicableLevels(bean));
		assertTrue(checker.validateApplicableLevel(bean));
	}

	@Test
	public void testSingleBeanTwoLevels() {
		HierarchyLevelChecker checker = getChecker(a, b);
		IBeanStructuralElementInstance bean = createBean("x");

		assertEquals(expected(a, b), checker.getApplicableLevels(bean));
	}

	@Test
	public void testTwoBeans() {
		HierarchyLevelChecker checker = getChecker(a, b);
		IBeanStructuralElementInstance parent = createBean("a");
		IBeanStructuralElementInstance child = createBean("x");
		parent.add(child);

		assertEquals(expected(a), checker.getApplicableLevels(parent));
		assertEquals(expected(b), checker.getApplicableLevels(child));

		assertFalse(checker.checkApplicable(child, a));
		assertTrue(checker.checkApplicable(child, b));
	}

	@Test
	public void testTwoBeansNesting() {
		HierarchyLevelChecker checker = getChecker(an, b);
		IBeanStructuralElementInstance parent = createBean("a");
		IBeanStructuralElementInstance child = createBean("x");
		parent.add(child);

		assertEquals(expected(an), checker.getApplicableLevels(parent));
		assertEquals(expected(an, b), checker.getApplicableLevels(child));
	}

	@Test
	public void testOptionalParent() {
		HierarchyLevelChecker checker = getChecker(ao, bn);
		IBeanStructuralElementInstance parent = createBean("x");
		IBeanStructuralElementInstance child = createBean("b");
		parent.add(child);

		assertEquals(expected(ao, bn), checker.getApplicableLevels(parent));
	}

	@Test
	public void testOptionalParentPresent() {
		HierarchyLevelChecker checker = getChecker(ao, b);
		IBeanStructuralElementInstance parent = createBean("a");
		IBeanStructuralElementInstance child = createBean("x");
		parent.add(child);

		assertEquals(expected(b), checker.getApplicableLevels(child));
	}

	@Test
	public void testOptionalNested() {
		HierarchyLevelChecker checker = getChecker(ano, b);
		IBeanStructuralElementInstance parent = createBean("a");
		IBeanStructuralElementInstance child = createBean("x");
		parent.add(child);

		assertEquals(expected(ano, b), checker.getApplicableLevels(child));
	}

	@Test
	public void testOptionalParents() {
		HierarchyLevelChecker checker = getChecker(ao, bo, c);
		IBeanStructuralElementInstance parent = createBean("x");
		IBeanStructuralElementInstance child = createBean("c");
		parent.add(child);

		assertEquals(expected(ao, bo), checker.getApplicableLevels(parent));
	}

	@Test
	public void testOptionalChildren() {
		HierarchyLevelChecker checker = getChecker(a, bo, co);
		IBeanStructuralElementInstance parent = createBean("a");
		IBeanStructuralElementInstance child = createBean("x");
		parent.add(child);

		assertEquals(expected(bo, co), checker.getApplicableLevels(child));
	}

	@Test
	public void testMiddleLevel() {
		HierarchyLevelChecker checker = getChecker(a, b, c);
		IBeanStructuralElementInstance parent = createBean("a");
		IBeanStructuralElementInstance child = createBean("x");
		IBeanStructuralElementInstance grandchild = createBean("c");
		parent.add(child);
		child.add(grandchild);

		assertEquals(expected(b), checker.getApplicableLevels(child));
	}

	@Test
	public void testMiddleLevelNested() {
		HierarchyLevelChecker checker = getChecker(an, b, cn);
		IBeanStructuralElementInstance parent = createBean("a");
		IBeanStructuralElementInstance child = createBean("x");
		IBeanStructuralElementInstance grandchild = createBean("c");
		parent.add(child);
		child.add(grandchild);

		assertEquals(expected(b), checker.getApplicableLevels(child));
	}

	@Test
	public void testMiddleLevelDeep() {
		HierarchyLevelChecker checker = getChecker(an, b, cn);
		IBeanStructuralElementInstance b1 = createBean("a");
		IBeanStructuralElementInstance b2 = createBean("x");
		IBeanStructuralElementInstance b3 = createBean("x");
		IBeanStructuralElementInstance b4 = createBean("x");
		IBeanStructuralElementInstance b5 = createBean("c");
		b1.add(b2);
		b2.add(b3);
		b3.add(b4);
		b4.add(b5);

		assertEquals(expected(an, b, cn), checker.getApplicableLevels(b3));
	}

	@Test
	public void testDifferentChildren() {
		HierarchyLevelChecker checker = getChecker(a, b, c);
		IBeanStructuralElementInstance parent = createBean("x");
		IBeanStructuralElementInstance child1 = createBean("b");
		IBeanStructuralElementInstance child2 = createBean("c");
		parent.add(child1);
		parent.add(child2);

		assertTrue(checker.getApplicableLevels(parent).isEmpty());
	}

	@Test
	public void testDifferentChildrenOptional() {
		HierarchyLevelChecker checker = getChecker(a, bo, c);
		IBeanStructuralElementInstance parent = createBean("x");
		IBeanStructuralElementInstance child1 = createBean("b");
		IBeanStructuralElementInstance child2 = createBean("c");
		parent.add(child1);
		parent.add(child2);

		assertEquals(expected(a), checker.getApplicableLevels(parent));
	}

	@Test
	public void testDifferentDeepChildren() {
		HierarchyLevelChecker checker = getChecker(a, b, c);
		IBeanStructuralElementInstance parent = createBean("x");
		IBeanStructuralElementInstance child1 = createBean("x");
		IBeanStructuralElementInstance child11 = createBean("b");
		IBeanStructuralElementInstance child2 = createBean("x");
		IBeanStructuralElementInstance child21 = createBean("c");
		parent.add(child1);
		parent.add(child2);
		child1.add(child11);
		child2.add(child21);

		assertEquals(expected(a), checker.getApplicableLevels(parent));
		assertEquals(expected(a), checker.getApplicableLevels(child1));
		assertEquals(expected(b), checker.getApplicableLevels(child2));
	}

	@Test
	public void testDifferentDeepChildrenNested() {
		HierarchyLevelChecker checker = getChecker(an, bn, cn);
		IBeanStructuralElementInstance parent = createBean("x");
		IBeanStructuralElementInstance child1 = createBean("x");
		IBeanStructuralElementInstance child11 = createBean("b");
		IBeanStructuralElementInstance child2 = createBean("x");
		IBeanStructuralElementInstance child21 = createBean("c");
		parent.add(child1);
		parent.add(child2);
		child1.add(child11);
		child2.add(child21);

		assertEquals(expected(an, bn), checker.getApplicableLevels(parent));
		assertEquals(expected(an, bn), checker.getApplicableLevels(child1));
		assertEquals(expected(bn, cn), checker.getApplicableLevels(child2));
	}

	@Test
	public void testInvalidNestedLevel() {
		HierarchyLevelChecker checker = getChecker(a, b);
		IBeanStructuralElementInstance parent = createBean("a");
		IBeanStructuralElementInstance child = createBean("a");
		parent.add(child);

		assertFalse(checker.validateApplicableLevel(parent));
		assertFalse(checker.validateApplicableLevel(child));
	}

	@Test
	public void testInvalidLevelWrongOrder() {
		HierarchyLevelChecker checker = getChecker(a, b);
		IBeanStructuralElementInstance parent = createBean("b");
		IBeanStructuralElementInstance child = createBean("a");
		parent.add(child);

		assertFalse(checker.validateApplicableLevel(parent));
		assertFalse(checker.validateApplicableLevel(child));
	}

	@Test
	public void testInvalidLevelWrongTreeDistance() {
		HierarchyLevelChecker checker = getChecker(a, b, c);
		IBeanStructuralElementInstance parent = createBean("a");
		IBeanStructuralElementInstance child = createBean("c");
		parent.add(child);

		assertFalse(checker.validateApplicableLevel(parent));
		assertFalse(checker.validateApplicableLevel(child));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testInvalidElementMultipleLevels() {
		HierarchyLevelChecker checker = getChecker(a, b);
		IBeanStructuralElementInstance bean = createBean("ab");

		assertFalse(checker.validateUniqueLevel(bean));

		// Should raise an exception
		checker.getApplicableLevels(bean);
	}

	/**
	 * @param levels 
	 * @return LevelChecker with given levels
	 */
	private HierarchyLevelChecker getChecker(IHierarchyLevel... levels) {
		return new HierarchyLevelChecker(Arrays.asList(levels));
	}

	/**
	 * @param name 
	 * @param allowNesting 
	 * @param optional 
	 * @return level implementation that matches bean name with the given name
	 */
	private IHierarchyLevel createNameMatchingLevel(String name, boolean allowNesting, boolean optional) {
		return new IHierarchyLevel() {
			@Override
			public boolean isOnLevel(IBeanStructuralElementInstance bean) {
				return bean.getName().contains(name);
			}

			@Override
			public boolean canBeNested() {
				return allowNesting;
			}

			@Override
			public boolean isOptional() {
				return optional;
			}

			@Override
			public String toString() {
				return "Level " + name + (allowNesting ? " nested" : "") + (optional ? " optional" : "");
			}
		};
	}

	/**
	 * @param levels 
	 * @return set of given levels
	 */
	private Set<IHierarchyLevel> expected(IHierarchyLevel... levels) {
		return new HashSet<>(Arrays.asList(levels));
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
