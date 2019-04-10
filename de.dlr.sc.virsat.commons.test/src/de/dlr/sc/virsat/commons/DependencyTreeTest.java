/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.commons;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import de.dlr.sc.virsat.commons.datastructures.DependencyTree;

/**
 * Test cases for the dependency tree.
 * @author muel_s8
 *
 */

public class DependencyTreeTest {
	
	private DependencyTree<String> tree;
	
	@Before
	public void setUp() throws Exception {
		tree = new DependencyTree<>();
		tree.addDependencies("summary(massWithMargin)", new String[] { "massWithMargin" });
		tree.addDependencies("massWithMargin", new String[] { "mass", "margin" });
		tree.addDependencies("massMargin", new String[] { "summary(massWithMargin)", "mass" });
	}

	@Test
	public void testHasCycle() {
		// Check cycle free correct
		assertEquals("Cycle free dependency tree correct", null, tree.hasCycle());
		
		// Check cyclic dependency correct
		tree.addDependencies("mass", new String[] { "summary(massWithMargin)" });
		assertTrue("Cycle free dependency tree correct", tree.hasCycle() != null);
	}

	@Test
	public void testGetLinearOrder() {
		List<String> linear = tree.getLinearOrder();
		
		int indexMass = linear.indexOf("mass");
		int indexMargin = linear.indexOf("margin");
		int indexMassWithMargin = linear.indexOf("massWithMargin");
		int indexSummary = linear.indexOf("summary(massWithMargin)");
		int indexMassMargin = linear.indexOf("massMargin");
		
		assertTrue("massWithMargin after mass and margin", indexMassWithMargin > indexMass && indexMassWithMargin > indexMargin);
		assertTrue("summary after massWithMargin", indexSummary > indexMassWithMargin);
		assertTrue("massMargin after summary and mass", indexMassMargin > indexSummary && indexMassMargin > indexMass);
	}
	
	@Test
	public void testTrim() {
		tree.addDependencies("density", new String[] { "5" });
		tree.addDependencies("volume", new String[] { "mass, density" });
		
		tree.trim(Arrays.asList("margin"));
		
		Set<String> expectedNodesAfterTrimming = new HashSet<>();
		expectedNodesAfterTrimming.add("summary(massWithMargin)");
		expectedNodesAfterTrimming.add("massWithMargin");
		expectedNodesAfterTrimming.add("margin");
		expectedNodesAfterTrimming.add("mass");
		expectedNodesAfterTrimming.add("massMargin");
		
		Set<String> nodesAfterTrimming = tree.getNodes();
		
		assertEquals("Trimming is correct", expectedNodesAfterTrimming, nodesAfterTrimming);
	}
	
	@Test
	public void testToDot() {
		String dot = tree.toDot("dependencyGraph");
		final String EXPECTED = 
				"digraph dependencyGraph {\n"
			+	"0 [label=\"margin\"]\n" 
			+	"1 [label=\"mass\"]\n" 
			+	"2 [label=\"massWithMargin\"]\n"
			+	"3 [label=\"summary(massWithMargin)\"]\n" 
			+	"4 [label=\"massMargin\"]\n" 
			+	"1->2\n"
			+	"0->2\n"
			+	"2->3\n"
			+	"3->4\n"
			+	"1->4\n"
			+	"}";
		
		assertEquals("Correct dot output", EXPECTED, dot);
	}

}
