/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.requirements.test;

// *****************************************************************
// * Import Statements
// *****************************************************************

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import junit.framework.JUnit4TestAdapter;

import de.dlr.sc.virsat.model.extension.requirements.model.RequirementGroupTest;
import de.dlr.sc.virsat.model.extension.requirements.migrator.Migrator0v1Test;
import de.dlr.sc.virsat.model.extension.requirements.model.EnumerationLiteralTest;
import de.dlr.sc.virsat.model.extension.requirements.model.RequirementsConfigurationCollectionTest;
import de.dlr.sc.virsat.model.extension.requirements.model.RequirementTypeTest;
import de.dlr.sc.virsat.model.extension.requirements.migrator.Migrator0v2Test;
import de.dlr.sc.virsat.model.extension.requirements.model.RequirementAttributeTest;
import de.dlr.sc.virsat.model.extension.requirements.model.AttributeValueTest;
import de.dlr.sc.virsat.model.extension.requirements.model.RequirementTest;
import de.dlr.sc.virsat.model.extension.requirements.model.RequirementsSpecificationTest;
import de.dlr.sc.virsat.model.extension.requirements.validator.RequirementsValidatorTest;
import de.dlr.sc.virsat.model.extension.requirements.model.RequirementsConfigurationTest;
import de.dlr.sc.virsat.model.extension.requirements.model.EnumerationDefinitionTest;

/**
 * 
 */
@RunWith(Suite.class)

@SuiteClasses({
	RequirementsSpecificationTest.class,
	RequirementsConfigurationTest.class,
	RequirementGroupTest.class,
	RequirementTest.class,
	AttributeValueTest.class,
	RequirementTypeTest.class,
	RequirementAttributeTest.class,
	EnumerationDefinitionTest.class,
	EnumerationLiteralTest.class,
	RequirementsConfigurationCollectionTest.class,
	Migrator0v1Test.class,
	Migrator0v2Test.class,
	RequirementsValidatorTest.class,
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
