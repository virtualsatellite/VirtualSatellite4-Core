/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.build.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import de.dlr.sc.virsat.build.inheritance.VirSatInheritanceBuilderTest;
import de.dlr.sc.virsat.build.marker.util.VirSatInheritanceMarkerHelperTest;
import de.dlr.sc.virsat.build.marker.util.VirSatValidationMarkerHelperTest;
import de.dlr.sc.virsat.build.validator.VirSatValidatorBuilderTest;
import de.dlr.sc.virsat.build.validator.core.DvlmSEIsUniqeNameValidatorTest;
import de.dlr.sc.virsat.build.validator.core.DvlmMissingResourcePropertyValidatorTest;
import de.dlr.sc.virsat.build.validator.core.DvlmNamingConventionValidatorTest;
import de.dlr.sc.virsat.build.validator.core.DvlmRepositoryDefaultQuantityKindValidatorTest;
import de.dlr.sc.virsat.build.validator.core.DvlmRepositoryUniqeNameValidatorTest;
import de.dlr.sc.virsat.build.validator.core.DvlmResolvedStructuralElementInstanceValidatorTest;
import junit.framework.JUnit4TestAdapter;

/**
 * 
 */
@RunWith(Suite.class)

@SuiteClasses({
	DvlmNamingConventionValidatorTest.class,
	DvlmMissingResourcePropertyValidatorTest.class,
	DvlmSEIsUniqeNameValidatorTest.class,
	DvlmRepositoryUniqeNameValidatorTest.class,
	VirSatValidationMarkerHelperTest.class,
	VirSatInheritanceMarkerHelperTest.class,
	VirSatValidatorBuilderTest.class,
	VirSatInheritanceBuilderTest.class,
	DvlmResolvedStructuralElementInstanceValidatorTest.class,
	DvlmRepositoryDefaultQuantityKindValidatorTest.class
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