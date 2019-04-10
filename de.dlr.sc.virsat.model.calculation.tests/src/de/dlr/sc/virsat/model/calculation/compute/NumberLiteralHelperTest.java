/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.calculation.compute;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import de.dlr.sc.virsat.model.calculation.compute.extensions.NumberLiteralHelper;
import de.dlr.sc.virsat.model.dvlm.calculation.CalculationFactory;
import de.dlr.sc.virsat.model.dvlm.calculation.NumberLiteral;

/**
 * This class tests the number literal helper
 * @author muel_s9
 *
 */
public class NumberLiteralHelperTest {

	@Test
	public void testGetValue() {
		final double TEST_PRECISION = 0.0001;
		final double TEST_VALUE_1 = 20;
		final double TEST_VALUE_2 = 20.5;
		final double TEST_VALUE_3 = -10;
		final double TEST_VALUE_4 = -304.3234;
		
		NumberLiteral numberLiteralTest1 = CalculationFactory.eINSTANCE.createNumberLiteral();
		NumberLiteralHelper numLitHelper = new NumberLiteralHelper(numberLiteralTest1);
		
		numberLiteralTest1.setValue(Double.toString(TEST_VALUE_1));
		assertEquals(TEST_VALUE_1, numLitHelper.getValue(), TEST_PRECISION);
		
		numberLiteralTest1.setValue(Double.toString(TEST_VALUE_2));
		assertEquals(TEST_VALUE_2, numLitHelper.getValue(), TEST_PRECISION);
	
		numberLiteralTest1.setValue(Double.toString(TEST_VALUE_3));
		assertEquals(TEST_VALUE_3, numLitHelper.getValue(), TEST_PRECISION);
	
		numberLiteralTest1.setValue(Double.toString(TEST_VALUE_4));
		assertEquals(TEST_VALUE_4, numLitHelper.getValue(), TEST_PRECISION);
	}

}
