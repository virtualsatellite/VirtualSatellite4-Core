/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.dvlm.list;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import de.dlr.sc.virsat.model.dvlm.structural.StructuralElement;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralFactory;
import de.dlr.sc.virsat.model.dvlm.structural.util.StructuralInstantiator;

/**
 * This class tests the list that is capable of understanding the ApplicableFor paradigm
 * that is used in the DVLM model. The list will be tested by the generated classes,
 * since the generator has been adapted to directly use the special type of EList within
 * a StructuralElementInstance
 * @author fisc_ph
 *
 */
public class DVLMFilteredContainingResolvingEListTest {

	private StructuralElement seAddAccept;
	private StructuralElement seAddFail;
	
	private StructuralElementInstance seiRoot;
	private StructuralElementInstance seiAccept;
	private StructuralElementInstance seiFail;

	
	@Before
	public void setUp() throws Exception {
		seAddAccept = StructuralFactory.eINSTANCE.createStructuralElement();
		seAddFail = StructuralFactory.eINSTANCE.createStructuralElement();
		
		seAddAccept.getApplicableFor().add(seAddAccept);
		seAddFail.getApplicableFor().add(seAddFail);
		
		seiRoot = new StructuralInstantiator().generateInstance(seAddAccept, "Se_Root");
		seiAccept = new StructuralInstantiator().generateInstance(seAddAccept, "Se_Child_Accept");
		seiFail = new StructuralInstantiator().generateInstance(seAddFail, "Se_Child_Fail");
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testAddDVLMOBJECT() {
		assertTrue("Object has been accepted", seiRoot.getChildren().add(seiAccept));
		assertFalse("Object has not been accepted", seiRoot.getChildren().add(seiFail));
		
		assertThat("Sub Elements contain expected element", seiRoot.getChildren(), hasItem(seiAccept));
		assertThat("Sub Elements contain expected element", seiRoot.getChildren(), not(hasItem(seiFail)));
	}

	@Test
	public void testAddIntDVLMOBJECT() {
		int i = 2;
		
		while (i < 3) {
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		seiRoot.getChildren().add(0, seiAccept);
		seiRoot.getChildren().add(0, seiFail);
		
		assertThat("Sub Elements contain expected element", seiRoot.getChildren(), hasItem(seiAccept));
		assertThat("Sub Elements contain expected element", seiRoot.getChildren(), not(hasItem(seiFail)));
	}

	@Test
	public void testAddAllCollectionOfQextendsDVLMOBJECT() {
		StructuralElementInstance seiAccept2 = new StructuralInstantiator().generateInstance(seAddAccept, "Se_Child_Accept");
		
		List<StructuralElementInstance> listWithFail = new ArrayList<>();
		listWithFail.add(seiFail);
		listWithFail.add(seiAccept);
		listWithFail.add(seiAccept2);
		
		assertFalse("We should not be able to add this list", seiRoot.getChildren().addAll(listWithFail));
		assertTrue("We still ahve not subelements attached", seiRoot.getChildren().isEmpty());
		
		List<StructuralElementInstance> listAccept = new ArrayList<>();
		listAccept.add(seiAccept);
		listAccept.add(seiAccept2);
		
		assertTrue("Should be be able to add this list", seiRoot.getChildren().addAll(listAccept));
		assertThat("Sub Elements contain expected elements", seiRoot.getChildren(), hasItems(seiAccept, seiAccept2));
	}

	@Test
	public void testAddAllIntCollectionOfQextendsDVLMOBJECT() {
		StructuralElementInstance seiAccept2 = new StructuralInstantiator().generateInstance(seAddAccept, "Se_Child_Accept");
		
		List<StructuralElementInstance> listWithFail = new ArrayList<>();
		listWithFail.add(seiFail);
		listWithFail.add(seiAccept);
		listWithFail.add(seiAccept2);
		
		assertFalse("We should not be able to add this list", seiRoot.getChildren().addAll(0, listWithFail));
		assertTrue("We still ahve not subelements attached", seiRoot.getChildren().isEmpty());
		
		List<StructuralElementInstance> listAccept = new ArrayList<>();
		listAccept.add(seiAccept);
		listAccept.add(seiAccept2);
		
		assertTrue("Should be be able to add this list", seiRoot.getChildren().addAll(0, listAccept));
		assertThat("Sub Elements contain expected elements", seiRoot.getChildren(), hasItems(seiAccept, seiAccept2));
	}

	@Test
	public void testSetIntDVLMOBJECT() {
		seiRoot.getChildren().add(seiAccept);
		
		assertEquals("Cannot set object with wrong element", seiFail, seiRoot.getChildren().set(0, seiFail));
		
		assertThat("Sub Elements contain expected element", seiRoot.getChildren(), hasItem(seiAccept));
		assertThat("Sub Elements contain expected element", seiRoot.getChildren(), not(hasItem(seiFail)));

		StructuralElementInstance seiAccept2 = new StructuralInstantiator().generateInstance(seAddAccept, "Se_Child_Accept");
		
		assertEquals("Try to set new acceptable object", seiAccept, seiRoot.getChildren().set(0, seiAccept2));
		assertThat("New Object is placed", seiRoot.getChildren(), hasItem(seiAccept2));
		assertThat("Old object ahs been removed", seiRoot.getChildren(), not(hasItem(seiAccept)));
	}
}
