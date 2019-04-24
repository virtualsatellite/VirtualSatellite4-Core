/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.dvlm.roles;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


/**
 * test class to test the user registry
 *
 */
public class UserRegistryTest {

	private final int userValidity = 255;
	private final String userName = "Hans";
	
	private UserRegistry ur = null;
	
	@Before
	public void setUp() throws Exception {
		ur = UserRegistry.getInstance();
		ur.setSuperUser(false);
	}
	
	@After
	public void tearDown() throws Exception {
		ur = null;
	}
	
	
	@Test
	public void testGetInstance() {
		
		// The PlugIn is a singleton - Verify this
		UserRegistry ur1 = UserRegistry.getInstance();
		UserRegistry ur2 = UserRegistry.getInstance();

		// Make sure both instances are not null
		assertNotNull(ur1);
		assertNotNull(ur2);

		// Check both are the same
		assertEquals(ur1, ur2);
	}
	
	@Test
	public void testSetUser() {	
		ur.setUser(userName, userValidity);
	
		assertEquals(userName, ur.getUserName());
		assertEquals(userValidity, ur.getUserValidity());					
	}
	
	@Test
	public void testIsSuperUser() {
		ur.setUser(userName, userValidity);
	
		//default case is that the user is no super user
		assertFalse(ur.isSuperUser());
		
		//now explicitly set it to true
		ur.setSuperUser(true);
		assertTrue(ur.isSuperUser());					
	}
}