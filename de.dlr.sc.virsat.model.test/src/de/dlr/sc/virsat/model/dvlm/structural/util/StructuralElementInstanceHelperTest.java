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


import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import de.dlr.sc.virsat.model.dvlm.structural.StructuralElement;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralFactory;

/**
 * Test Cases for The Structural Element Instance Helper
 * @author fisc_ph
 *
 */
public class StructuralElementInstanceHelperTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetRoot() {
		
		StructuralElement seA = StructuralFactory.eINSTANCE.createStructuralElement();
		StructuralElement seB = StructuralFactory.eINSTANCE.createStructuralElement();
		StructuralElement seC = StructuralFactory.eINSTANCE.createStructuralElement();
		
		seA.setName("seA");
		seB.setName("seB");
		seC.setName("seC");
		
		seB.setIsApplicableForAll(true);
		seC.setIsApplicableForAll(true);

		StructuralElementInstance seiA = StructuralFactory.eINSTANCE.createStructuralElementInstance();
		StructuralElementInstance seiB = StructuralFactory.eINSTANCE.createStructuralElementInstance();
		StructuralElementInstance seiC1 = StructuralFactory.eINSTANCE.createStructuralElementInstance();
		StructuralElementInstance seiC2 = StructuralFactory.eINSTANCE.createStructuralElementInstance();
		
		seiA.setType(seA);
		seiB.setType(seB);
		seiC1.setType(seC);
		seiC2.setType(seC);
		
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
		StructuralElement seA = StructuralFactory.eINSTANCE.createStructuralElement();
		StructuralElement seB = StructuralFactory.eINSTANCE.createStructuralElement();
		StructuralElement seC = StructuralFactory.eINSTANCE.createStructuralElement();
		
		seA.setName("seA");
		seB.setName("seB");
		seC.setName("seC");
		
		seB.setIsApplicableForAll(true);
		seC.setIsApplicableForAll(true);

		StructuralElementInstance seiA = StructuralFactory.eINSTANCE.createStructuralElementInstance();
		StructuralElementInstance seiB = StructuralFactory.eINSTANCE.createStructuralElementInstance();
		StructuralElementInstance seiC1 = StructuralFactory.eINSTANCE.createStructuralElementInstance();
		StructuralElementInstance seiC2 = StructuralFactory.eINSTANCE.createStructuralElementInstance();
		
		seiA.setType(seA);
		seiB.setType(seB);
		seiC1.setType(seC);
		seiC2.setType(seC);
		
		seiA.getChildren().add(seiB);
		seiA.getChildren().add(seiC1);
		seiA.getChildren().add(seiC2);
		
		List<StructuralElementInstance> listB = StructuralElementInstanceHelper.getStructuralElementInstances(seiA, "seB");
		List<StructuralElementInstance> listC = StructuralElementInstanceHelper.getStructuralElementInstances(seiA, "seC");
		
		assertThat("ListB has correct contents", listB, allOf(hasItems(seiB), not(hasItems(seiC1, seiC2))));
		assertThat("ListC has correct contents", listC, allOf(not(hasItems(seiB)), hasItems(seiC1, seiC2)));
	}
}
