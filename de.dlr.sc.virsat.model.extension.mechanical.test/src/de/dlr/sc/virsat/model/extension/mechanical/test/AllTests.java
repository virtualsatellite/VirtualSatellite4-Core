/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.mechanical.test;

// *****************************************************************
// * Import Statements
// *****************************************************************

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import de.dlr.sc.virsat.model.extension.mechanical.cad.CadExporterTest;
import de.dlr.sc.virsat.model.extension.mechanical.cad.CadFileHandlerTest;
import de.dlr.sc.virsat.model.extension.mechanical.cad.CadImporterTest;
import de.dlr.sc.virsat.model.extension.mechanical.cad.command.CopyResourceCommandTest;
import de.dlr.sc.virsat.model.extension.mechanical.cad.util.CadHelperTest;
import de.dlr.sc.virsat.model.extension.mechanical.validator.MechanicalValidatorTest;
import junit.framework.JUnit4TestAdapter;


/**
 * 
 */
@RunWith(Suite.class)

@SuiteClasses({
	MechanicalValidatorTest.class,
	CadExporterTest.class,
	CadFileHandlerTest.class,
	CadImporterTest.class,
	CadHelperTest.class,
	CopyResourceCommandTest.class
				})

/**
 * 
 * Test Collection
 *
 */
public class AllTests {

	/**
	 * Constructor
	 */
	private AllTests() {
	}
	
	/**
	 * Test Adapter
	 * @return Executable JUnit Tests
	 */
	public static junit.framework.Test suite() {
		return new JUnit4TestAdapter(AllTests.class);
	}	
}
