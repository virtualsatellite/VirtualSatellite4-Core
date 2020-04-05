/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.tests.test;

// *****************************************************************
// * Import Statements
// *****************************************************************

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import junit.framework.JUnit4TestAdapter;

import de.dlr.sc.virsat.model.extension.tests.model.TestCategoryCompositionTest;
import de.dlr.sc.virsat.model.extension.tests.model.TestCategoryReferenceTest;
import de.dlr.sc.virsat.model.extension.tests.model.TestParameterTest;
import de.dlr.sc.virsat.model.extension.tests.model.TestMassParametersTest;
import de.dlr.sc.virsat.model.extension.tests.model.TestStructuralElementTest;
import de.dlr.sc.virsat.model.extension.tests.model.TestStructuralElementOtherTest;
import de.dlr.sc.virsat.model.extension.tests.model.TestCategoryExtendsTest;
import de.dlr.sc.virsat.model.extension.tests.model.TestCategoryBeanATest;
import de.dlr.sc.virsat.model.extension.tests.model.TestCategoryCompositionArrayTest;
import de.dlr.sc.virsat.model.extension.tests.model.TestCrossLinkedParametersWithCalculationTest;
import de.dlr.sc.virsat.model.extension.tests.migrator.Migrator1v1Test;
import de.dlr.sc.virsat.model.extension.tests.model.TestCategoryAllPropertyTest;
import de.dlr.sc.virsat.model.extension.tests.model.TestCategoryReferenceArrayTest;
import de.dlr.sc.virsat.model.extension.tests.model.TestCategoryIntrinsicArrayTest;
import de.dlr.sc.virsat.model.extension.tests.model.TestCategoryBeanBTest;
import de.dlr.sc.virsat.model.extension.tests.validator.TestsValidatorTest;
import de.dlr.sc.virsat.model.extension.tests.model.TestCategoryBeanConcreteTest;
import de.dlr.sc.virsat.model.extension.tests.model.EReferenceTestTest;
import de.dlr.sc.virsat.model.extension.tests.migrator.Migrator1v0Test;
import de.dlr.sc.virsat.model.extension.tests.model.TestCategoryBaseTest;

/**
 * 
 */
@RunWith(Suite.class)

@SuiteClasses({
	TestCategoryAllPropertyTest.class,
	TestCategoryCompositionTest.class,
	TestCategoryReferenceTest.class,
	TestCategoryIntrinsicArrayTest.class,
	TestCategoryCompositionArrayTest.class,
	TestCategoryReferenceArrayTest.class,
	TestCategoryBeanATest.class,
	TestCategoryBeanBTest.class,
	TestCategoryBeanConcreteTest.class,
	TestCategoryBaseTest.class,
	TestCategoryExtendsTest.class,
	TestParameterTest.class,
	TestMassParametersTest.class,
	TestCrossLinkedParametersWithCalculationTest.class,
	EReferenceTestTest.class,
	TestStructuralElementTest.class,
	TestStructuralElementOtherTest.class,
	Migrator1v0Test.class,
	Migrator1v1Test.class,
	TestsValidatorTest.class,
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
