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
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
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
	
	@Test
	public void testCleanFromIndirectSelectedChildren() {
		// Create some SEIs of which some are children to the others
		// Pick some of them and try to clean them for their distinct parents
		StructuralElement se = StructuralFactory.eINSTANCE.createStructuralElement();
		
		se.setIsApplicableForAll(true);
		
		StructuralElementInstance seiA1 = StructuralFactory.eINSTANCE.createStructuralElementInstance();
		StructuralElementInstance seiA2 = StructuralFactory.eINSTANCE.createStructuralElementInstance();
		StructuralElementInstance seiA3 = StructuralFactory.eINSTANCE.createStructuralElementInstance();
		StructuralElementInstance seiA4 = StructuralFactory.eINSTANCE.createStructuralElementInstance();
	
		StructuralElementInstance seiB1 = StructuralFactory.eINSTANCE.createStructuralElementInstance();
		StructuralElementInstance seiB2 = StructuralFactory.eINSTANCE.createStructuralElementInstance();
		StructuralElementInstance seiB3 = StructuralFactory.eINSTANCE.createStructuralElementInstance();
		StructuralElementInstance seiB4 = StructuralFactory.eINSTANCE.createStructuralElementInstance();
	
		seiA1.setType(se);
		seiA2.setType(se);
		seiA3.setType(se);
		seiA4.setType(se);
		
		seiB1.setType(se);
		seiB2.setType(se);
		seiB3.setType(se);
		seiB4.setType(se);
		
		seiA1.getChildren().add(seiA2);
		seiA2.getChildren().add(seiA3);
		seiA3.getChildren().add(seiA4);
		
		seiB1.getChildren().add(seiB2);
		seiB2.getChildren().add(seiB3);
		seiB3.getChildren().add(seiB4);
		
		List<StructuralElementInstance> selectedSeis = new ArrayList<>();
		selectedSeis.add(seiA1);
		selectedSeis.add(seiA2);
		selectedSeis.add(seiA4);
		selectedSeis.add(seiB2);
		selectedSeis.add(seiB3);
		
		List<StructuralElementInstance> cleanedSeiSelection = StructuralElementInstanceHelper.cleanFromIndirectSelectedChildren(selectedSeis);
		
		assertThat("The filtered list only contains the parents", cleanedSeiSelection, hasItems(seiA1, seiB2));
		assertThat("The filtered list has exactly two entries", cleanedSeiSelection, hasSize(2));
		
		// Now check that the order of selection is not relevant for the result
		List<StructuralElementInstance> selectedSeis2 = new ArrayList<>();
		selectedSeis2.add(seiA2);
		selectedSeis2.add(seiA4);
		
		List<StructuralElementInstance> cleanedSeiSelection2 = StructuralElementInstanceHelper.cleanFromIndirectSelectedChildren(selectedSeis2);
		assertThat("The filtered list only contains A2", cleanedSeiSelection2, hasItem(seiA2));
		assertThat("The filtered list does not contains A4", cleanedSeiSelection2, not(hasItem(seiA4)));
		assertThat("The filtered list has exactly one entries", cleanedSeiSelection2, hasSize(1));
		
		List<StructuralElementInstance> selectedSeis2Reverse = new ArrayList<>();
		selectedSeis2Reverse.add(seiA4);
		selectedSeis2Reverse.add(seiA2);
		
		List<StructuralElementInstance> cleanedSeiSelection2Reverse = StructuralElementInstanceHelper.cleanFromIndirectSelectedChildren(selectedSeis2Reverse);
		assertThat("The filtered list only contains A2", cleanedSeiSelection2Reverse, hasItem(seiA2));
		assertThat("The filtered list does not contains A4", cleanedSeiSelection2Reverse, not(hasItem(seiA4)));
		assertThat("The filtered list has exactly one entries", cleanedSeiSelection2Reverse, hasSize(1));
 	}
}
