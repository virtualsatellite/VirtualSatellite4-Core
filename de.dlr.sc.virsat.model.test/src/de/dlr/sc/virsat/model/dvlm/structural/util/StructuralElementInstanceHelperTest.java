/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.dvlm.structural.util;


import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import de.dlr.sc.virsat.model.dvlm.structural.StructuralElement;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralFactory;

/**
 * Test Cases for The Structural Element Instance Helper
 */
public class StructuralElementInstanceHelperTest {

	private StructuralElementInstance seiA;
	private StructuralElementInstance seiB;
	private StructuralElementInstance seiC1;
	private StructuralElementInstance seiC2;

	@Before
	public void setUp() throws Exception {
		StructuralElement seA = StructuralFactory.eINSTANCE.createStructuralElement();
		StructuralElement seB = StructuralFactory.eINSTANCE.createStructuralElement();
		StructuralElement seC = StructuralFactory.eINSTANCE.createStructuralElement();
		
		seA.setName("seA");
		seB.setName("seB");
		seC.setName("seC");
		
		seA.setIsApplicableForAll(true);
		seB.setIsApplicableForAll(true);
		seC.setIsApplicableForAll(true);

		seA.setIsCanInheritFromAll(true);
		seB.setIsCanInheritFromAll(true);
		seC.setIsCanInheritFromAll(true);

		seiA = StructuralFactory.eINSTANCE.createStructuralElementInstance();
		seiB = StructuralFactory.eINSTANCE.createStructuralElementInstance();
		seiC1 = StructuralFactory.eINSTANCE.createStructuralElementInstance();
		seiC2 = StructuralFactory.eINSTANCE.createStructuralElementInstance();
		
		seiA.setType(seA);
		seiB.setType(seB);
		seiC1.setType(seC);
		seiC2.setType(seC);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetRoot() {
		seiA.getChildren().add(seiB);
		seiA.getChildren().add(seiC1);
		seiC1.getChildren().add(seiC2);
		
		assertEquals("Found correct Root", seiA, new StructuralElementInstanceHelper(seiA).getRoot());
		assertEquals("Found correct Root", seiA, new StructuralElementInstanceHelper(seiB).getRoot());
		assertEquals("Found correct Root", seiA, new StructuralElementInstanceHelper(seiC1).getRoot());
		assertEquals("Found correct Root", seiA, new StructuralElementInstanceHelper(seiC2).getRoot());
	}

	@Test
	public void testGetStructuralElementInstances() {
		seiA.getChildren().add(seiB);
		seiA.getChildren().add(seiC1);
		seiA.getChildren().add(seiC2);
		
		List<StructuralElementInstance> listB = StructuralElementInstanceHelper.getStructuralElementInstances(seiA, "seB");
		List<StructuralElementInstance> listC = StructuralElementInstanceHelper.getStructuralElementInstances(seiA, "seC");
		
		assertThat("ListB has correct contents", listB, allOf(hasItems(seiB), not(hasItems(seiC1, seiC2))));
		assertThat("ListC has correct contents", listC, allOf(not(hasItems(seiB)), hasItems(seiC1, seiC2)));
	}
	
	@Test
	public void testGetAllSuperSeis() {
		Set<StructuralElementInstance> superSeis = StructuralElementInstanceHelper.getAllSuperSeis(seiA);
		assertTrue(superSeis.isEmpty());
		
		seiA.getSuperSeis().add(seiB);

		superSeis = StructuralElementInstanceHelper.getAllSuperSeis(seiA);
		assertEquals(1, superSeis.size());
		assertThat(superSeis, hasItem(seiB));
		
		seiB.getSuperSeis().add(seiC1);
		
		superSeis = StructuralElementInstanceHelper.getAllSuperSeis(seiA);
		assertEquals(2, superSeis.size());
		assertThat(superSeis, hasItems(seiB, seiC1));

		seiB.getSuperSeis().add(seiC2);
		
		superSeis = StructuralElementInstanceHelper.getAllSuperSeis(seiB);
		assertEquals(2, superSeis.size());
		assertThat(superSeis, hasItems(seiC1, seiC2));
	}
	
	@Test
	public void testGetDeepSubStructuralElementInstances() {
		StructuralElement se = StructuralFactory.eINSTANCE.createStructuralElement();
		se.setIsApplicableForAll(true);
		
		StructuralElementInstance seiRoot = new StructuralInstantiator().generateInstance(se, "SeiRoot");
		StructuralElementInstance seiLevel11 = new StructuralInstantiator().generateInstance(se, "SeiLevelOne");
		StructuralElementInstance seiLevel12 = new StructuralInstantiator().generateInstance(se, "SeiLevelOne");
		StructuralElementInstance seiLevel21 = new StructuralInstantiator().generateInstance(se, "SeiLevel2");
		StructuralElementInstance seiLevel22 = new StructuralInstantiator().generateInstance(se, "SeiLevel2");

		seiRoot.getChildren().add(seiLevel11);
		seiRoot.getChildren().add(seiLevel12);
		seiLevel11.getChildren().add(seiLevel21);
		seiLevel11.getChildren().add(seiLevel22);
		
		List<StructuralElementInstance> searchResult;
		
		searchResult = StructuralElementInstanceHelper.getDeepChildren(seiRoot, 0, 0);
		assertThat("Root itself is not a child, thus it is not in the list", searchResult, empty());

		searchResult = StructuralElementInstanceHelper.getDeepChildren(seiRoot, 1, 0);
		assertThat("Only the ones on level one should be in the list", searchResult, hasItems(seiLevel11, seiLevel12));

		searchResult = StructuralElementInstanceHelper.getDeepChildren(seiRoot, 2, 0);
		assertThat("All children are in the list the list", searchResult, hasItems(seiLevel11, seiLevel12, seiLevel21, seiLevel22));

		searchResult = StructuralElementInstanceHelper.getDeepChildren(seiRoot, StructuralElementInstanceHelper.DEPTH_INFINITE, 0);
		assertThat("All children are in the list the list", searchResult, hasItems(seiLevel11, seiLevel12, seiLevel21, seiLevel22));
	}
}
