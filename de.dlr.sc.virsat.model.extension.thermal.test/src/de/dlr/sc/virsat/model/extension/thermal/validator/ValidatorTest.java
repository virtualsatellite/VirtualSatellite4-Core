/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.thermal.validator;

import java.io.IOException;
import org.eclipse.core.runtime.CoreException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import de.dlr.sc.virsat.model.dvlm.roles.UserRegistry;

/**
 * Sample test case for thermalent template
 * @author fisc_ph
 *
 */
public class ValidatorTest {

	@Before
	public void setUp() throws CoreException, IOException {
		UserRegistry.getInstance().setSuperUser(true);
	}
	
	@After
	public void tearDown() {
		UserRegistry.getInstance().setSuperUser(false);
	}
	
	@Test
	public void testCase1() throws Exception {

	}
}
