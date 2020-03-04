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
import static org.junit.Assert.assertNotSame;
import org.eclipse.emf.common.command.Command;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.PropertyinstancesFactory;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ValuePropertyInstance;
import de.dlr.sc.virsat.model.dvlm.roles.UserRegistry;

/**
 * Test cases for the boolean bean
 * @author fisc_ph
 *
 */
public class BeanPropertyBooleanTest extends ABeanPropertyTest {

	private BeanPropertyBoolean beanProperty;
	private ValuePropertyInstance vpi;
	
	@Before
	public void setUp() throws Exception {
		super.setUp();
		vpi = PropertyinstancesFactory.eINSTANCE.createValuePropertyInstance();
		beanProperty = new BeanPropertyBoolean();
		beanProperty.setTypeInstance(vpi);
		UserRegistry.getInstance().setSuperUser(true);
	}

	@After
	public void tearDown() throws Exception {
		UserRegistry.getInstance().setSuperUser(false);
	}

	@Test
	public void testSetValueEditingDomainLong() {
		final String TEST_STRING = Boolean.TRUE.toString();
		
		Command cmd = beanProperty.setValue(ed, true);
		cmd.execute();
		
		assertEquals("Value correctly set", TEST_STRING, vpi.getValue());
	}

	@Test
	public void testSetValueLong() {
		final String TEST_STRING = Boolean.TRUE.toString();
		
		beanProperty.setValue(true);
		
		assertEquals("Value correctly set", TEST_STRING, vpi.getValue());
	}

	@Test
	public void testGetValue() {
		final String TEST_STRING = Boolean.FALSE.toString();
		
		vpi.setValue(TEST_STRING);
		
		assertEquals("Value correctly set", false, beanProperty.getValue());
	}

	@Test
	public void testHashCode() {
		assertEquals("HashCodes are identical", vpi.hashCode(), beanProperty.hashCode());
		
		ValuePropertyInstance vpi2 = PropertyinstancesFactory.eINSTANCE.createValuePropertyInstance();
		BeanPropertyBoolean beanProperty2 = new BeanPropertyBoolean();
		beanProperty2.setTypeInstance(vpi2);
		
		assertNotSame("Hash Codes are not the same", beanProperty2.hashCode(), beanProperty.hashCode());
		
		beanProperty2.setTypeInstance(vpi);
		assertEquals("HashCodes are identical", beanProperty2.hashCode(), beanProperty.hashCode());
	}

	@Test
	public void testEqualsObject() {
		ValuePropertyInstance vpi2 = PropertyinstancesFactory.eINSTANCE.createValuePropertyInstance();
		BeanPropertyBoolean beanProperty2 = new BeanPropertyBoolean();
		beanProperty2.setTypeInstance(vpi2);
		
		assertNotSame("Beans are not the same", beanProperty2, beanProperty);
		
		beanProperty2.setTypeInstance(vpi);
		assertEquals("Beans are identical", beanProperty2, beanProperty);
	}

	@Test
	public void testConstructor() {
		BeanPropertyBoolean beanProperty2 = new BeanPropertyBoolean(vpi);
		assertEquals("Beans are identical", beanProperty2, beanProperty);
	}
}
