/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.visualisation.test;

// *****************************************************************
// * Import Statements
// *****************************************************************

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import junit.framework.JUnit4TestAdapter;

import de.dlr.sc.virsat.model.extension.visualisation.migrator.Migrator1v2Test;
import de.dlr.sc.virsat.model.extension.visualisation.model.VisualisationTest;
import de.dlr.sc.virsat.model.extension.visualisation.migrator.Migrator1v3Test;
import de.dlr.sc.virsat.model.extension.visualisation.migrator.Migrator1v1Test;
import de.dlr.sc.virsat.model.extension.visualisation.migrator.Migrator1v0Test;
import de.dlr.sc.virsat.model.extension.visualisation.validator.VisualisationValidatorTest;
import de.dlr.sc.virsat.model.extension.visualisation.migrator.Migrator1v4Test;

/**
 * 
 */
@RunWith(Suite.class)

@SuiteClasses({
	VisualisationTest.class,
	Migrator1v0Test.class,
	Migrator1v1Test.class,
	Migrator1v2Test.class,
	Migrator1v3Test.class,
	Migrator1v4Test.class,
	VisualisationValidatorTest.class,
				})

/**
 * 
 * Test Collection
 *
 */
public class AllTestsGen {

	/**
	 * Constructor
	 */
	private AllTestsGen() {
	}
	
	/**
	 * Test Adapter
	 * @return Executable JUnit Tests
	 */
	public static junit.framework.Test suite() {
		return new JUnit4TestAdapter(AllTests.class);
	}	
}
