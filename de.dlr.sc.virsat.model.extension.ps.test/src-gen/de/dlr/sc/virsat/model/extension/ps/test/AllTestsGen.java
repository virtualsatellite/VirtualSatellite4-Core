/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.ps.test;

// *****************************************************************
// * Import Statements
// *****************************************************************

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import junit.framework.JUnit4TestAdapter;

import de.dlr.sc.virsat.model.extension.ps.model.ElementConfigurationTest;
import de.dlr.sc.virsat.model.extension.ps.model.ElementRealizationTest;
import de.dlr.sc.virsat.model.extension.ps.model.ProductTreeDomainTest;
import de.dlr.sc.virsat.model.extension.ps.migrator.Migrator1v1Test;
import de.dlr.sc.virsat.model.extension.ps.model.ElementDefinitionTest;
import de.dlr.sc.virsat.model.extension.ps.migrator.Migrator1v3Test;
import de.dlr.sc.virsat.model.extension.ps.model.DocumentTest;
import de.dlr.sc.virsat.model.extension.ps.model.ConfigurationTreeTest;
import de.dlr.sc.virsat.model.extension.ps.model.ProductStorageDomainTest;
import de.dlr.sc.virsat.model.extension.ps.model.ProductStorageTest;
import de.dlr.sc.virsat.model.extension.ps.validator.PsValidatorTest;
import de.dlr.sc.virsat.model.extension.ps.model.AssemblyTreeTest;
import de.dlr.sc.virsat.model.extension.ps.migrator.Migrator1v0Test;
import de.dlr.sc.virsat.model.extension.ps.migrator.Migrator1v2Test;
import de.dlr.sc.virsat.model.extension.ps.model.ElementOccurenceTest;
import de.dlr.sc.virsat.model.extension.ps.model.ProductTreeTest;

/**
 * 
 */
@RunWith(Suite.class)

@SuiteClasses({
	DocumentTest.class,
	ProductTreeTest.class,
	ProductTreeDomainTest.class,
	ElementDefinitionTest.class,
	ConfigurationTreeTest.class,
	ElementConfigurationTest.class,
	AssemblyTreeTest.class,
	ElementOccurenceTest.class,
	ProductStorageTest.class,
	ProductStorageDomainTest.class,
	ElementRealizationTest.class,
	Migrator1v0Test.class,
	Migrator1v1Test.class,
	Migrator1v2Test.class,
	Migrator1v3Test.class,
	PsValidatorTest.class,
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
