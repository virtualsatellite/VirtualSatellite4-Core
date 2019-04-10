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
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNull;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.util.URI;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.PropertyinstancesFactory;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ResourcePropertyInstance;
import de.dlr.sc.virsat.model.dvlm.roles.UserRegistry;

/**
 * Test cases for the resource bean
 * @author fisc_ph
 *
 */
public class BeanPropertyResourceTest extends ABeanPropertyTest {

	private BeanPropertyResource beanProperty;
	private ResourcePropertyInstance rpi;
	
	@Before
	public void setUp() throws Exception {
		super.setUp();
		rpi = PropertyinstancesFactory.eINSTANCE.createResourcePropertyInstance();
		beanProperty = new BeanPropertyResource();
		beanProperty.setTypeInstance(rpi);
		UserRegistry.getInstance().setSuperUser(true);
	}

	@After
	public void tearDown() throws Exception {
		UserRegistry.getInstance().setSuperUser(false);
	}

	@Test
	public void testSetValueEditingDomainLong() {
		final URI TEST_URI =  URI.createPlatformResourceURI("resources/file[1].xls", false);
		final String TEST_STRING = "/resources/file[1].xls";
		
		Command cmd = beanProperty.setValue(ed, TEST_URI);
		cmd.execute();
		
		assertEquals("Value correctly set", TEST_STRING, rpi.getResourceUri());
	}
	
	@Test
	public void testSetValueEditingDomainNull() {
		final URI TEST_URI_NOT_NULL =  URI.createPlatformResourceURI("resources/file[1].xls", false);
		final URI TEST_URI_NULL =  null;
		
		Command cmdSetToNotNull = beanProperty.setValue(ed, TEST_URI_NOT_NULL);
		cmdSetToNotNull.execute();
		
		assertNotNull("Value correctly set", rpi.getResourceUri());
		
		Command cmdSetToNull = beanProperty.setValue(ed, TEST_URI_NULL);
		cmdSetToNull.execute();
		
		assertNull("Value correctly set", rpi.getResourceUri());
	}

	@Test
	public void testSetValueLong() {
		final URI TEST_URI =  URI.createPlatformResourceURI("resources/file[2].xls", false);
		final String TEST_STRING = "/resources/file[2].xls";
		
		beanProperty.setValue(TEST_URI);
		
		assertEquals("Value correctly set", TEST_STRING, rpi.getResourceUri());
	}

	@Test
	public void testGetValue() {
		final URI TEST_URI =  URI.createPlatformResourceURI("resources/file[3].xls", false);
		final String TEST_STRING = "/resources/file[3].xls";
		
		rpi.setResourceUri(TEST_STRING);
		
		assertEquals("Value correctly set", TEST_URI, beanProperty.getValue());
	}

	@Test
	public void testHashCode() {
		assertEquals("HashCodes are identical", rpi.hashCode(), beanProperty.hashCode());
		
		ResourcePropertyInstance vpi2 = PropertyinstancesFactory.eINSTANCE.createResourcePropertyInstance();
		BeanPropertyResource beanProperty2 = new BeanPropertyResource();
		beanProperty2.setTypeInstance(vpi2);
		
		assertNotSame("Hash Codes are not the same", beanProperty2.hashCode(), beanProperty.hashCode());
		
		beanProperty2.setTypeInstance(rpi);
		assertEquals("HashCodes are identical", beanProperty2.hashCode(), beanProperty.hashCode());
	}

	@Test
	public void testEqualsObject() {
		ResourcePropertyInstance vpi2 = PropertyinstancesFactory.eINSTANCE.createResourcePropertyInstance();
		BeanPropertyResource beanProperty2 = new BeanPropertyResource();
		beanProperty2.setTypeInstance(vpi2);

		assertNotSame("Beans are not the same", beanProperty2, beanProperty);
		
		beanProperty2.setTypeInstance(rpi);
		assertEquals("Beans are identical", beanProperty2, beanProperty);
	}
}