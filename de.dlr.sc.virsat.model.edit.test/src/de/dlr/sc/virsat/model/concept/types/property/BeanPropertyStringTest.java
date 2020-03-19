/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.concept.types.property;


import static org.junit.Assert.assertEquals;

import org.eclipse.emf.common.command.Command;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.PropertyinstancesFactory;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ValuePropertyInstance;
import de.dlr.sc.virsat.model.dvlm.roles.UserRegistry;

/**
 * Test cases for the string bean
 * @author fisc_ph
 *
 */
public class BeanPropertyStringTest extends ABeanPropertyTest {

	private BeanPropertyString beanProperty;
	private ValuePropertyInstance vpi;
	
	@Before
	public void setUp() throws Exception {
		super.setUp();
		vpi = PropertyinstancesFactory.eINSTANCE.createValuePropertyInstance();
		beanProperty = new BeanPropertyString();
		beanProperty.setTypeInstance(vpi);
		UserRegistry.getInstance().setSuperUser(true);
	}

	@After
	public void tearDown() throws Exception {
		UserRegistry.getInstance().setSuperUser(false);
	}

	@Test
	public void testSetValueEditingDomainLong() {
		final String TEST_STRING = "22.56";
		
		Command cmd = beanProperty.setValue(ed, TEST_STRING);
		cmd.execute();
		
		assertEquals("Value correctly set", TEST_STRING, vpi.getValue());
	}

	@Test
	public void testSetValueLong() {
		final String TEST_STRING = "20.56";
		
		beanProperty.setValue(TEST_STRING);
		
		assertEquals("Value correctly set", TEST_STRING, vpi.getValue());
	}

	@Test
	public void testGetValue() {
		final String TEST_STRING = "21.56";
		
		vpi.setValue(TEST_STRING);
		
		assertEquals("Value correctly set", TEST_STRING, beanProperty.getValue());
	}
	
	@Test
	public void testConstructor() {
		BeanPropertyString beanProperty2 = new BeanPropertyString(vpi);
		assertEquals("Beans are identical", beanProperty2, beanProperty);
	}
}
