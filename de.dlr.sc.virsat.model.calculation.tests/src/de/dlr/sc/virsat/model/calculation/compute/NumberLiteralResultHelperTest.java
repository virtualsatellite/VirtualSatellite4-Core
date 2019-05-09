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
import static org.junit.Assert.assertNull;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import de.dlr.sc.virsat.model.calculation.compute.extensions.NumberLiteralResult;
import de.dlr.sc.virsat.model.calculation.compute.extensions.NumberLiteralResultHelper;
import de.dlr.sc.virsat.model.dvlm.calculation.CalculationFactory;
import de.dlr.sc.virsat.model.dvlm.calculation.MathOperator;
import de.dlr.sc.virsat.model.dvlm.calculation.NumberLiteral;
import de.dlr.sc.virsat.model.dvlm.qudv.AQuantityKind;
import de.dlr.sc.virsat.model.dvlm.qudv.util.QudvUnitHelper;

/**
 * This class tests the number literal result helper
 * @author muel_s8
 *
 */
public class NumberLiteralResultHelperTest {

	@Test
	public void testApplyMathOperator() {
		final double EPSILON = 0.000001;
		final double TEST_VALUE_LHS = 40;
		final double TEST_VALUE_RHS = 20;
		
		NumberLiteral numberLiteralLHS = CalculationFactory.eINSTANCE.createNumberLiteral();
		NumberLiteral numberLiteralRHS = CalculationFactory.eINSTANCE.createNumberLiteral();
		
		numberLiteralLHS.setValue(Double.toString(TEST_VALUE_LHS));
		numberLiteralRHS.setValue(Double.toString(TEST_VALUE_RHS));
		
		AQuantityKind qk = QudvUnitHelper.getInstance().createSimpleQuantityKind("A", "a", "", "");
		
		Map<AQuantityKind, Double> quantityKindsLHS = new HashMap<>();
		quantityKindsLHS.put(qk, 1d);
		
		Map<AQuantityKind, Double> quantityKindsRHS = new HashMap<>();
		quantityKindsRHS.put(qk, 2d);
		
		NumberLiteralResult numberLiteralResultLHS = new NumberLiteralResult(numberLiteralLHS, quantityKindsLHS);
		NumberLiteralResult numberLiteralResultRHS = new NumberLiteralResult(numberLiteralRHS, quantityKindsRHS);
		
		NumberLiteralResultHelper numLitResultHelperLHS = new NumberLiteralResultHelper(numberLiteralResultLHS);
		
		NumberLiteralResult resultPlus = numLitResultHelperLHS.applyMathOperator(MathOperator.PLUS, numberLiteralResultRHS);
		NumberLiteralResult resultMinus = numLitResultHelperLHS.applyMathOperator(MathOperator.MINUS, numberLiteralResultRHS);
		NumberLiteralResult resultMultiply = numLitResultHelperLHS.applyMathOperator(MathOperator.MULTIPLY, numberLiteralResultRHS);
		NumberLiteralResult resultDivide = numLitResultHelperLHS.applyMathOperator(MathOperator.DIVIDE, numberLiteralResultRHS);
		NumberLiteralResult resultSqrt = numLitResultHelperLHS.applyMathOperator(MathOperator.SQRT, numberLiteralResultRHS);
		
		assertEquals("Value Computation correct", "60.0", resultPlus.getNumberLiteral().getValue());
		assertEquals("Value Computation correct", "20.0", resultMinus.getNumberLiteral().getValue());
		assertEquals("Value Computation correct", "800.0", resultMultiply.getNumberLiteral().getValue());
		assertEquals("Value Computation correct", "2.0", resultDivide.getNumberLiteral().getValue());
		
		assertEquals("Quantity Kind Compuation correct", 1d, resultPlus.getQuantityKinds().get(QudvUnitHelper.getInstance().getUndefinedQK()), EPSILON);
		assertNull("Quantity Kind Compuation correct", resultPlus.getQuantityKinds().get(qk));
		assertEquals("Quantity Kind Compuation correct", 1d, resultMinus.getQuantityKinds().get(QudvUnitHelper.getInstance().getUndefinedQK()), EPSILON);
		assertNull("Quantity Kind Compuation correct", resultMinus.getQuantityKinds().get(qk));
		final double EXPECTED_QUANTITY_KINDS_POTENCY = 3d;
		assertEquals("Quantity Kind Compuation correct", EXPECTED_QUANTITY_KINDS_POTENCY, resultMultiply.getQuantityKinds().get(qk), EPSILON);
		assertEquals("Quantity Kind Compuation correct", -1d, resultDivide.getQuantityKinds().get(qk), EPSILON);
		assertEquals("Quantity Kind Compuation correct", 1, resultSqrt.getQuantityKinds().get(qk), EPSILON);
		
		quantityKindsRHS.put(qk, 1d);
		resultPlus = numLitResultHelperLHS.applyMathOperator(MathOperator.PLUS, numberLiteralResultRHS);
		resultMinus = numLitResultHelperLHS.applyMathOperator(MathOperator.MINUS, numberLiteralResultRHS);
		assertEquals("Quantity Kind Compuation correct", 1d, resultPlus.getQuantityKinds().get(qk), EPSILON);
		assertEquals("Quantity Kind Compuation correct", 1d, resultMinus.getQuantityKinds().get(qk), EPSILON);
	}
}
