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
public class DVLMFilteredResolvingEListTest {

	private StructuralElement seBase1;
	private StructuralElement seBase2;

	private StructuralElement seInherits1;
	
	private StructuralElementInstance seiBase1;
	private StructuralElementInstance seiBase2;
	private StructuralElementInstance seiInherits1;
	
	@Before
	public void setUp() throws Exception {
		seBase1 = StructuralFactory.eINSTANCE.createStructuralElement();
		seBase2 = StructuralFactory.eINSTANCE.createStructuralElement();

		seInherits1 = StructuralFactory.eINSTANCE.createStructuralElement();
		
		// Only the first one is based on a StructuralElement the second instance can accept all inheritances
		seInherits1.getCanInheritFrom().add(seBase1);

		seiBase1 = new StructuralInstantiator().generateInstance(seBase1, "Base1");
		seiBase2 = new StructuralInstantiator().generateInstance(seBase2, "Base2");
		seiInherits1 = new StructuralInstantiator().generateInstance(seInherits1, "Inherits1");
	}
	
	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testAddDVLMOBJECT() {
		assertTrue("Object has been accepted", seiInherits1.getSuperSeis().add(seiBase1));
		assertFalse("Object has not been accepted", seiInherits1.getSuperSeis().add(seiBase2));
		
		assertThat("Sub Elements contain expected element", seiInherits1.getSuperSeis(), hasItem(seiBase1));
		assertThat("Sub Elements contain expected element", seiInherits1.getSuperSeis(), not(hasItem(seiBase2)));
	}

	@Test
	public void testAddIntDVLMOBJECT() {
		seiInherits1.getSuperSeis().add(0, seiBase1);
		seiInherits1.getSuperSeis().add(0, seiBase2);
		
		assertThat("Sub Elements contain expected element", seiInherits1.getSuperSeis(), hasItem(seiBase1));
		assertThat("Sub Elements contain expected element", seiInherits1.getSuperSeis(), not(hasItem(seiBase2)));
	}

	@Test
	public void testAddAllCollectionOfQextendsDVLMOBJECT() {
		
		List<StructuralElementInstance> list = new ArrayList<>();
		list.add(seiBase1);
		list.add(seiBase2);
		
		assertFalse("We should not be able to add this list", seiInherits1.getSuperSeis().addAll(list));
		assertTrue("We still ahve not subelements attached", seiInherits1.getSuperSeis().isEmpty());
		
		seInherits1.getCanInheritFrom().add(seBase2);
		
		assertTrue("Should be be able to add this list", seiInherits1.getSuperSeis().addAll(list));
		assertThat("Sub Elements contain expected elements", seiInherits1.getSuperSeis(), hasItems(seiBase1, seiBase2));
	}

	@Test
	public void testAddAllIntCollectionOfQextendsDVLMOBJECT() {
		List<StructuralElementInstance> list = new ArrayList<>();
		list.add(seiBase1);
		list.add(seiBase2);
		
		assertFalse("We should not be able to add this list", seiInherits1.getSuperSeis().addAll(0, list));
		assertTrue("We still ahve not subelements attached", seiInherits1.getSuperSeis().isEmpty());
		
		seInherits1.getCanInheritFrom().add(seBase2);
		
		assertTrue("Should be be able to add this list", seiInherits1.getSuperSeis().addAll(0, list));
		assertThat("Sub Elements contain expected elements", seiInherits1.getSuperSeis(), hasItems(seiBase1, seiBase2));
	}

	@Test
	public void testSetIntDVLMOBJECT() {
		seiInherits1.getSuperSeis().add(seiBase1);
		
		assertEquals("Cannot set object with wrong element", seiBase2, seiInherits1.getSuperSeis().set(0, seiBase2));
		
		assertThat("Sub Elements contain expected element", seiInherits1.getSuperSeis(), hasItem(seiBase1));
		assertThat("Sub Elements contain expected element", seiInherits1.getSuperSeis(), not(hasItem(seiBase2)));

		seInherits1.getCanInheritFrom().add(seBase2);
		
		assertEquals("Try to set new acceptable object",  seiBase1, seiInherits1.getSuperSeis().set(0, seiBase2));
		assertThat("New Object is placed", seiInherits1.getSuperSeis(), hasItem(seiBase2));
		assertThat("Old object ahs been removed",  seiInherits1.getSuperSeis(), not(hasItem(seiBase1)));
	}
}
