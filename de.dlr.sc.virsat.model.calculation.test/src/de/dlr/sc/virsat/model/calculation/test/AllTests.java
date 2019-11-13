/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.calculation.test;


import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import de.dlr.sc.virsat.model.calculation.compute.EquationHelperTest;
import de.dlr.sc.virsat.model.calculation.compute.ExpressionHelperTest;
import de.dlr.sc.virsat.model.calculation.compute.IncrementalEquationBuilderTest;
import de.dlr.sc.virsat.model.calculation.compute.NumberLiteralResultHelperTest;
import de.dlr.sc.virsat.model.calculation.compute.NumberLiteralSetterTest;
import de.dlr.sc.virsat.model.calculation.compute.UnresolvedSetFunctionHelperTest;
import de.dlr.sc.virsat.model.calculation.marker.VirSatEquationMarkerHelperTest;
import de.dlr.sc.virsat.model.calculation.serializer.SafeEquationDSLSemanticSequencerTest;
import de.dlr.sc.virsat.model.calculation.validator.UnitPropertyValueValidatorTest;
import junit.framework.JUnit4TestAdapter;

/**
 * 
 * @author scha_vo
 *
 */
@RunWith(Suite.class)

@SuiteClasses({
				ExpressionHelperTest.class, 
				NumberLiteralResultHelperTest.class,
				UnresolvedSetFunctionHelperTest.class,
				EquationHelperTest.class,
				IncrementalEquationBuilderTest.class,
				VirSatEquationMarkerHelperTest.class,
				UnitPropertyValueValidatorTest.class,
				NumberLiteralSetterTest.class,
				SafeEquationDSLSemanticSequencerTest.class
			  })

public class AllTests {
	
	/**
	 * Constructor
	 */
	private AllTests() {
	}

	/**
	 * Static entry point to test suite
	 * @return the jUnit test suite
	 */
	public static junit.framework.Test suite() {
		return new JUnit4TestAdapter(AllTests.class);
	}	
}