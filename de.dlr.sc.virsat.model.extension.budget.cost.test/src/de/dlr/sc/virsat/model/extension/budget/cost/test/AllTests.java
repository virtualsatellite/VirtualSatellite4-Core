/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.budget.cost.test;

// *****************************************************************
// * Import Statements
// *****************************************************************

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import de.dlr.sc.virsat.model.extension.budget.cost.excel.exporter.CostExporterTest;
import de.dlr.sc.virsat.model.extension.budget.cost.unit.EuroUnitCreatorTest;
import de.dlr.sc.virsat.model.extension.budget.cost.util.CostConceptHelperTest;
import junit.framework.JUnit4TestAdapter;

/**
 * 
 */
@RunWith(Suite.class)

@SuiteClasses({ 
	EuroUnitCreatorTest.class, 
	CostConceptHelperTest.class,
	CostExporterTest.class
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
	 * 
	 * @return Executable JUnit Tests
	 */
	public static junit.framework.Test suite() {
		return new JUnit4TestAdapter(AllTests.class);
	}
}
