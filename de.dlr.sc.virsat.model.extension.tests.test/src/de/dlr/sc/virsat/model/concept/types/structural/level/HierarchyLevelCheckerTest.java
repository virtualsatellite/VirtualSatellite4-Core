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
 * 
 * In these tests we define simple hierarchies of levels e.g.
 * a
 * |---b
 *     |---c
 * 
 * And create trees of beans matching or not matching level criteria e.g.
 * parent_bean_on_level_a
 *          |---------child_on_unknown_level
 *
 * We assert that HierarchyLevelChecker correctly deduces that the child can be only on level b
 * 
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

		//Plain name-matching levels
		a = createNameMatchingLevel("a", false, false);
		b = createNameMatchingLevel("b", false, false);
		c = createNameMatchingLevel("c", false, false);

		//Levels that allow nesting
		an = createNameMatchingLevel("a", true, false);
		bn = createNameMatchingLevel("b", true, false);
		cn = createNameMatchingLevel("c", true, false);

		//Optional levels
		ao = createNameMatchingLevel("a", false, true);
		bo = createNameMatchingLevel("b", false, true);
		co = createNameMatchingLevel("c", false, true);
		ano = createNameMatchingLevel("a", true, true);
	}

	@Test
	public void testSingleBeanOnCorrectLevel() {
		HierarchyLevelChecker checker = createCheckerForHierarchy(a);
		IBeanStructuralElementInstance bean = createBean("a");

		assertEquals(expectedApplicableLevels(a), checker.getApplicableLevels(bean));
		assertFalse("No level conflicts", checker.beanHasInapplicableLevel(bean));
	}

	@Test
	public void testSingleBeanWithoutLevel() {
		HierarchyLevelChecker checker = createCheckerForHierarchy(a);
		IBeanStructuralElementInstance bean = createBean("x");

		assertEquals(expectedApplicableLevels(a), checker.getApplicableLevels(bean));
		assertFalse("No level conflicts", checker.beanHasInapplicableLevel(bean));
	}

	@Test
	public void testSingleBeanTwoPossibleLevels() {
		HierarchyLevelChecker checker = createCheckerForHierarchy(a, b);
		IBeanStructuralElementInstance bean = createBean("x");

		assertEquals("If elements are not in hierarchy then total tree depth level is considered", 
				expectedApplicableLevels(a), checker.getApplicableLevels(bean));
	}

	@Test
	public void testChildApplicabilityFiltered() {
		HierarchyLevelChecker checker = createCheckerForHierarchy(a, b);
		IBeanStructuralElementInstance parent = createBean("a");
		IBeanStructuralElementInstance child = createBean("x");
		parent.add(child);

		assertEquals(expectedApplicableLevels(a), checker.getApplicableLevels(parent));
		assertEquals(expectedApplicableLevels(b), checker.getApplicableLevels(child));

		assertFalse(checker.checkApplicable(child, a));
		assertTrue(checker.checkApplicable(child, b));
	}

	@Test
	public void testTwoBeansNesting() {
		HierarchyLevelChecker checker = createCheckerForHierarchy(an, b);
		IBeanStructuralElementInstance parent = createBean("a");
		IBeanStructuralElementInstance child = createBean("x");
		parent.add(child);

		assertEquals(expectedApplicableLevels(an), checker.getApplicableLevels(parent));
		assertEquals(expectedApplicableLevels(an, b), checker.getApplicableLevels(child));
	}
	
	@Test
	public void testThreeNewBeans() {
		HierarchyLevelChecker checker = createCheckerForHierarchy(a, b, c);
		IBeanStructuralElementInstance parent = createBean("x");
		IBeanStructuralElementInstance child = createBean("y");
		IBeanStructuralElementInstance grandChild = createBean("z");
		parent.add(child);
		child.add(grandChild);
		
		assertEquals(expectedApplicableLevels(a), checker.getApplicableLevels(parent));
		assertEquals(expectedApplicableLevels(a, b), checker.getApplicableLevels(child));
		assertEquals(expectedApplicableLevels(a, b, c), checker.getApplicableLevels(grandChild));
		
	}

	@Test
	public void testOptionalParent() {
		HierarchyLevelChecker checker = createCheckerForHierarchy(ao, bn);
		IBeanStructuralElementInstance parent = createBean("x");
		IBeanStructuralElementInstance child = createBean("b");
		parent.add(child);

		assertEquals(expectedApplicableLevels(ao, bn), checker.getApplicableLevels(parent));
	}

	@Test
	public void testOptionalParentPresent() {
		HierarchyLevelChecker checker = createCheckerForHierarchy(ao, b);
		IBeanStructuralElementInstance parent = createBean("a");
		IBeanStructuralElementInstance child = createBean("x");
		parent.add(child);

		assertEquals(expectedApplicableLevels(b), checker.getApplicableLevels(child));
	}

	@Test
	public void testOptionalNested() {
		HierarchyLevelChecker checker = createCheckerForHierarchy(ano, b);
		IBeanStructuralElementInstance parent = createBean("a");
		IBeanStructuralElementInstance child = createBean("x");
		parent.add(child);

		assertEquals(expectedApplicableLevels(ano, b), checker.getApplicableLevels(child));
	}

	@Test
	public void testOptionalParents() {
		HierarchyLevelChecker checker = createCheckerForHierarchy(ao, bo, c);
		IBeanStructuralElementInstance parent = createBean("x");
		IBeanStructuralElementInstance child = createBean("c");
		parent.add(child);

		assertEquals(expectedApplicableLevels(ao, bo), checker.getApplicableLevels(parent));
	}

	@Test
	public void testOptionalChildren() {
		HierarchyLevelChecker checker = createCheckerForHierarchy(a, bo, co);
		IBeanStructuralElementInstance parent = createBean("a");
		IBeanStructuralElementInstance child = createBean("x");
		parent.add(child);

		assertEquals(expectedApplicableLevels(bo, co), checker.getApplicableLevels(child));
	}

	@Test
	public void testMiddleLevel() {
		HierarchyLevelChecker checker = createCheckerForHierarchy(a, b, c);
		IBeanStructuralElementInstance parent = createBean("a");
		IBeanStructuralElementInstance child = createBean("x");
		IBeanStructuralElementInstance grandchild = createBean("c");
		parent.add(child);
		child.add(grandchild);

		assertEquals(expectedApplicableLevels(b), checker.getApplicableLevels(child));
	}

	@Test
	public void testMiddleLevelNested() {
		HierarchyLevelChecker checker = createCheckerForHierarchy(an, b, cn);
		IBeanStructuralElementInstance parent = createBean("a");
		IBeanStructuralElementInstance child = createBean("x");
		IBeanStructuralElementInstance grandchild = createBean("c");
		parent.add(child);
		child.add(grandchild);

		assertEquals(expectedApplicableLevels(b), checker.getApplicableLevels(child));
	}

	@Test
	public void testMiddleLevelDeep() {
		HierarchyLevelChecker checker = createCheckerForHierarchy(an, b, cn);
		IBeanStructuralElementInstance b1 = createBean("a");
		IBeanStructuralElementInstance b2 = createBean("x");
		IBeanStructuralElementInstance b3 = createBean("x");
		IBeanStructuralElementInstance b4 = createBean("x");
		IBeanStructuralElementInstance b5 = createBean("c");
		b1.add(b2);
		b2.add(b3);
		b3.add(b4);
		b4.add(b5);

		assertEquals(expectedApplicableLevels(an, b, cn), checker.getApplicableLevels(b3));
	}

	@Test
	public void testDifferentChildren() {
		HierarchyLevelChecker checker = createCheckerForHierarchy(a, b, c);
		IBeanStructuralElementInstance parent = createBean("x");
		IBeanStructuralElementInstance child1 = createBean("b");
		IBeanStructuralElementInstance child2 = createBean("c");
		parent.add(child1);
		parent.add(child2);

		assertTrue(checker.getApplicableLevels(parent).isEmpty());
	}

	@Test
	public void testDifferentChildrenOptional() {
		HierarchyLevelChecker checker = createCheckerForHierarchy(a, bo, c);
		IBeanStructuralElementInstance parent = createBean("x");
		IBeanStructuralElementInstance child1 = createBean("b");
		IBeanStructuralElementInstance child2 = createBean("c");
		parent.add(child1);
		parent.add(child2);

		assertEquals(expectedApplicableLevels(a), checker.getApplicableLevels(parent));
	}

	@Test
	public void testDifferentDeepChildren() {
		HierarchyLevelChecker checker = createCheckerForHierarchy(a, b, c);
		IBeanStructuralElementInstance parent = createBean("x");
		IBeanStructuralElementInstance child1 = createBean("x");
		IBeanStructuralElementInstance child11 = createBean("b");
		IBeanStructuralElementInstance child2 = createBean("x");
		IBeanStructuralElementInstance child21 = createBean("c");
		parent.add(child1);
		parent.add(child2);
		child1.add(child11);
		child2.add(child21);

		assertEquals(expectedApplicableLevels(a), checker.getApplicableLevels(parent));
		assertEquals(expectedApplicableLevels(a), checker.getApplicableLevels(child1));
		assertEquals(expectedApplicableLevels(b), checker.getApplicableLevels(child2));
	}

	@Test
	public void testDifferentDeepChildrenNested() {
		HierarchyLevelChecker checker = createCheckerForHierarchy(an, bn, cn);
		IBeanStructuralElementInstance parent = createBean("x");
		IBeanStructuralElementInstance child1 = createBean("x");
		IBeanStructuralElementInstance child11 = createBean("b");
		IBeanStructuralElementInstance child2 = createBean("x");
		IBeanStructuralElementInstance child21 = createBean("c");
		parent.add(child1);
		parent.add(child2);
		child1.add(child11);
		child2.add(child21);

		assertEquals(expectedApplicableLevels(an, bn), checker.getApplicableLevels(parent));
		assertEquals(expectedApplicableLevels(an, bn), checker.getApplicableLevels(child1));
		assertEquals(expectedApplicableLevels(bn, cn), checker.getApplicableLevels(child2));
	}

	@Test
	public void testInvalidNestedLevel() {
		HierarchyLevelChecker checker = createCheckerForHierarchy(a, b);
		IBeanStructuralElementInstance parent = createBean("a");
		IBeanStructuralElementInstance child = createBean("a");
		parent.add(child);

		assertTrue(checker.beanHasInapplicableLevel(parent));
		assertTrue(checker.beanHasInapplicableLevel(child));
	}

	@Test
	public void testInvalidLowerLevelBetweenHigherLevels() {
		HierarchyLevelChecker checker = createCheckerForHierarchy(a, b);
		IBeanStructuralElementInstance parent = createBean("a");
		IBeanStructuralElementInstance child = createBean("b");
		IBeanStructuralElementInstance grandchild = createBean("a");
		parent.add(child);
		child.add(grandchild);

		assertTrue(checker.beanHasInapplicableLevel(parent));
		assertTrue(checker.beanHasInapplicableLevel(child));
		assertTrue(checker.beanHasInapplicableLevel(grandchild));
	}
	
	@Test
	public void testInvalidLevelWrongOrder() {
		HierarchyLevelChecker checker = createCheckerForHierarchy(a, b);
		IBeanStructuralElementInstance parent = createBean("b");
		IBeanStructuralElementInstance child = createBean("a");
		parent.add(child);

		assertTrue(checker.beanHasInapplicableLevel(parent));
		assertTrue(checker.beanHasInapplicableLevel(child));
	}

	@Test
	public void testInvalidLevelWrongTreeDistance() {
		HierarchyLevelChecker checker = createCheckerForHierarchy(a, b, c);
		IBeanStructuralElementInstance parent = createBean("a");
		IBeanStructuralElementInstance child = createBean("c");
		parent.add(child);

		assertTrue("Middle level is missing", checker.beanHasInapplicableLevel(parent));
		assertTrue("Middle level is missing", checker.beanHasInapplicableLevel(child));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testInvalidElementMultipleLevels() {
		HierarchyLevelChecker checker = createCheckerForHierarchy(a, b);
		IBeanStructuralElementInstance bean = createBean("ab");

		assertTrue("Name matches both levels a and b", checker.beanHasAmbiguousLevel(bean));

		// Should raise an exception
		checker.getApplicableLevels(bean);
	}

	/**
	 * @param levels list of levels defining a hierarchy
	 * @return HierarchyLevelChecker for given hierarchy
	 */
	private HierarchyLevelChecker createCheckerForHierarchy(IHierarchyLevel... levels) {
		return new HierarchyLevelChecker(Arrays.asList(levels));
	}

	/**
	 * Creates a level which matches beans that have a given substring in their name
	 * @param name the substring to match against. E.g. level with name "a" will match a bean with name "abc"
	 * @param allowNesting controls if this level can be nested
	 * @param optional controls if this level can be skipped
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
	 * Helper method for assertions
	 * @param levels expected applicable levels
	 * @return Set containing given levels
	 */
	private Set<IHierarchyLevel> expectedApplicableLevels(IHierarchyLevel... levels) {
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
