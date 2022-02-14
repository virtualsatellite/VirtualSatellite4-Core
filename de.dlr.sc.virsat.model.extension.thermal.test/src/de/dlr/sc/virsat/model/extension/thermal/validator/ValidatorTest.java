/**
 * This file is part of the VirSat project.
 *
 * Copyright (c) 2008-2016
 * German Aerospace Center (DLR), Simulation and Software Technology, Germany
 * All rights reserved
 *
 */
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
