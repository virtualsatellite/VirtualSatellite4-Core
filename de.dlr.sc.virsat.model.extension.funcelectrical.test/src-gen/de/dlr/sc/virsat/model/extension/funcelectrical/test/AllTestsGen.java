/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.funcelectrical.test;

// *****************************************************************
// * Import Statements
// *****************************************************************

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import junit.framework.JUnit4TestAdapter;

import de.dlr.sc.virsat.model.extension.funcelectrical.model.PowerInterfaceTypeTest;
import de.dlr.sc.virsat.model.extension.funcelectrical.migrator.Migrator2v1Test;
import de.dlr.sc.virsat.model.extension.funcelectrical.model.FixedVoltageDefinitionTest;
import de.dlr.sc.virsat.model.extension.funcelectrical.model.DataInterfaceTypeTest;
import de.dlr.sc.virsat.model.extension.funcelectrical.model.InterfaceTypeCollectionTest;
import de.dlr.sc.virsat.model.extension.funcelectrical.model.InterfaceEndTest;
import de.dlr.sc.virsat.model.extension.funcelectrical.model.RangedVoltageDefinitionTest;
import de.dlr.sc.virsat.model.extension.funcelectrical.model.InterfaceTest;
import de.dlr.sc.virsat.model.extension.funcelectrical.model.ThermalReferencePointTest;
import de.dlr.sc.virsat.model.extension.funcelectrical.migrator.Migrator1v0Test;
import de.dlr.sc.virsat.model.extension.funcelectrical.migrator.Migrator2v0Test;
import de.dlr.sc.virsat.model.extension.funcelectrical.validator.FuncelectricalValidatorTest;
import de.dlr.sc.virsat.model.extension.funcelectrical.model.InterfaceTypeTest;

/**
 * 
 */
@RunWith(Suite.class)

@SuiteClasses({
	InterfaceTypeTest.class,
	PowerInterfaceTypeTest.class,
	FixedVoltageDefinitionTest.class,
	RangedVoltageDefinitionTest.class,
	DataInterfaceTypeTest.class,
	ThermalReferencePointTest.class,
	InterfaceEndTest.class,
	InterfaceTest.class,
	InterfaceTypeCollectionTest.class,
	Migrator1v0Test.class,
	Migrator2v0Test.class,
	Migrator2v1Test.class,
	FuncelectricalValidatorTest.class,
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
