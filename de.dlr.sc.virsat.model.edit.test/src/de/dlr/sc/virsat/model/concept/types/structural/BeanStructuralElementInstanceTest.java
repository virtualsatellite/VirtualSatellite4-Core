/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.concept.types.structural;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.eclipse.emf.common.command.Command;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import de.dlr.sc.virsat.model.dvlm.roles.UserRegistry;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElement;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralFactory;

/**
 * test cases for the generic bean object that encapsulates SEI from the emf model
 * @author fisc_ph
 *
 */
public class BeanStructuralElementInstanceTest extends ABeanStructuralElementInstanceTest {

	private ABeanStructuralElementInstance aBeanSei;
	private StructuralElementInstance sei;
	
	@Before
	public void setUp() throws Exception {
		super.setUp();
		sei = StructuralFactory.eINSTANCE.createStructuralElementInstance();
		aBeanSei = new BeanStructuralElementInstance(sei);
		
		UserRegistry.getInstance().setSuperUser(true);
	}
	

	@After
	public void tearDown() throws Exception {
		UserRegistry.getInstance().setSuperUser(false);
	}
	
	@Test
	public void testEqualsObject() {
		StructuralElementInstance sei1 = StructuralFactory.eINSTANCE.createStructuralElementInstance();
		StructuralElementInstance sei2 = StructuralFactory.eINSTANCE.createStructuralElementInstance();
		
		BeanStructuralElementInstance aBeanSeEq1 = new BeanStructuralElementInstance(sei1);
		BeanStructuralElementInstance aBeanSeEq2 = new BeanStructuralElementInstance(sei1);
		BeanStructuralElementInstance aBeanSeDiff = new BeanStructuralElementInstance(sei2);
		
		assertEquals("Objects are Equal", aBeanSeEq1, aBeanSeEq2);
		assertEquals("Objects are Equal hash", aBeanSeEq1.hashCode(), aBeanSeEq2.hashCode());
		assertNotSame("Objects are Equal", aBeanSeEq1, aBeanSeEq2);
		assertNotSame("Objects are Equal", aBeanSeEq1, aBeanSeDiff);
	}

	@Test
	public void testGetName() {
		final String TEST_NAME_GET = "ExampleName";
		sei.setName(TEST_NAME_GET);

		String beanGetName = aBeanSei.getName();
		
		assertEquals("Got correct name", TEST_NAME_GET, beanGetName);
	}

	@Test
	public void testSetNameString() {
		final String TEST_NAME_SET = "ExampleName";
		aBeanSei.setName(TEST_NAME_SET);
		String caGetName = sei.getName();
		
		assertEquals("Got correct name", TEST_NAME_SET, caGetName);
	}

	@Test
	public void testSetNameEditingDomainString() {
		final String TEST_NAME_SET = "ExampleName";
		Command setCommand = aBeanSei.setName(ed, TEST_NAME_SET);

		// The command is not yet executed, the name of the CA should not have changed
		String caGetNameNull = sei.getName();
		assertNull("Command was not yet executed", caGetNameNull);
		
		// Now we execute the command and the name should be correctly set
		setCommand.execute();
		String caGetName = sei.getName();
		assertEquals("Name is correctly set", TEST_NAME_SET, caGetName);
	}
	
	@Test
	public void testGetUuid() {
		String beanUuid = aBeanSei.getUuid();
		String seiUuid = sei.getUuid().toString();
		assertEquals("Got correct UUID of bean", seiUuid, beanUuid);
	}
	
	@Test
	public void testCanBeRoot() {
		StructuralElement se = StructuralFactory.eINSTANCE.createStructuralElement();
		sei = StructuralFactory.eINSTANCE.createStructuralElementInstance();
		aBeanSei = new BeanStructuralElementInstance(sei);
		
		// Make the SE a root object
		se.setIsRootStructuralElement(true);
		
		assertNull("Make sure there is no SE yet assigned", sei.getType());
		assertFalse("There is no SE yet assigned to the Sei", aBeanSei.canBeRoot());
		
		// Now assign the SE and check the bean tells it is a root
		sei.setType(se);
		assertTrue("now the bean can be a Root SEI", aBeanSei.canBeRoot());
		
		// Now change the SE state
		se.setIsRootStructuralElement(false);
		assertFalse("The bean is not a sei of a root se anymore", aBeanSei.canBeRoot());
		
		// remove the SEI to see if we avoid a NPE
		aBeanSei.setStructuralElementInstance(null);
		assertFalse("A false is expected but not NPE", aBeanSei.canBeRoot());
	}
	
	// ------------------------------------------------------------------
	// Further Properties of the Bean Class are tested in the TestConcept
	// ------------------------------------------------------------------
	
}
