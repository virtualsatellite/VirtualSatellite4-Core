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

	private static final int USER_VALIDITY = 255;
	private static final String USER_NAME = "Hans";
	
	private UserRegistry ur = null;
	
	@Before
	public void setUp() throws Exception {
		ur = UserRegistry.getInstance();
		ur.setSuperUser(false);
	}
	
	@After
	public void tearDown() throws Exception {
		ur.setSuperUser(false);
	}
	
	@Test
	public void testGetInstance() {
		
		// The PlugIn is a singleton - Verify this
		IUserContext ur1 = UserRegistry.getInstance();
		IUserContext ur2 = UserRegistry.getInstance();

		// Make sure both instances are not null
		assertNotNull(ur1);
		assertNotNull(ur2);

		// Check both are the same
		assertEquals(ur1, ur2);
	}
	
	@Test
	public void testSetUser() {	
		ur.setUser(USER_NAME, USER_VALIDITY);
	
		assertEquals(USER_NAME, ur.getUserName());
		assertEquals(USER_VALIDITY, ur.getUserValidity());					
	}
	
	@Test
	public void testIsSuperUser() {
		ur.setUser(USER_NAME, USER_VALIDITY);
	
		//default case is that the user is no super user
		assertFalse(ur.isSuperUser());
		
		//now explicitly set it to true
		ur.setSuperUser(true);
		assertTrue(ur.isSuperUser());					
	}
}