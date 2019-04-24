/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.calculation.compute.extensions;

import java.util.ArrayList;
import java.util.List;

import de.dlr.sc.virsat.model.calculation.compute.IExpressionEvaluator;
import de.dlr.sc.virsat.model.calculation.compute.IExpressionResult;
import de.dlr.sc.virsat.model.dvlm.calculation.AAdvancedFunction;
import de.dlr.sc.virsat.model.dvlm.calculation.ALeftOpRightExpression;
import de.dlr.sc.virsat.model.dvlm.calculation.AOpRightExpression;

/**
 * Evaluator implementation for NumberLiterals
 * @author muel_s8
 *
 */

public class NumberLiteralEvaluator implements IExpressionEvaluator {

	@Override
	public IExpressionResult caseAOpRightExpression(AOpRightExpression object, IExpressionResult right) {
		if (right instanceof NumberLiteralResult) {
			return caseAOpRightExpression(object, (NumberLiteralResult) right);
		}
		return null;
	}

	@Override
	public IExpressionResult caseALeftOpRightExpression(ALeftOpRightExpression object, IExpressionResult left, IExpressionResult right) {
		if (left instanceof NumberLiteralResult && right instanceof NumberLiteralResult) {
			return caseALeftOpRightExpression(object, (NumberLiteralResult) left, (NumberLiteralResult) right);
		}
		return null;
	}
	
	@Override
	public IExpressionResult caseAAdvancedFunction(AAdvancedFunction object, List<IExpressionResult> set) {
		
		List<NumberLiteralResult> numberLiteralResults = new ArrayList<>();
		for (IExpressionResult input : set) {
			if (!(input instanceof NumberLiteralResult)) {
				return null;
			} else {
				numberLiteralResults.add(((NumberLiteralResult) input));
			}
		}
		
		NumberLiteralSetFunctionHelper setHelper = new NumberLiteralSetFunctionHelper(numberLiteralResults);
		NumberLiteralResult result = setHelper.applySetOperator(object);
		return result;
	}
	
	/**
	 * Handles the cases (operation numberLiteral)
	 * @param object The operation
	 * @param right The number literal
	 * @return the result of the calculation
	 */
	public IExpressionResult caseAOpRightExpression(AOpRightExpression object, NumberLiteralResult right) {
		NumberLiteralResultHelper numbHelper = new NumberLiteralResultHelper(right);
		NumberLiteralResult result = numbHelper.applyMathOperator(object.getOperator());
		
		return result;
	}
	
	/**
	 * Handles the case (numberLiteral operation numberLiteral)
	 * @param object The operation
	 * @param left leftside number literal
	 * @param right rightside number literal
	 * @return the result of the calculation
	 */
	public IExpressionResult caseALeftOpRightExpression(ALeftOpRightExpression object, NumberLiteralResult left, NumberLiteralResult right) {
		NumberLiteralResultHelper numbHelper = new NumberLiteralResultHelper(left);
		NumberLiteralResult result = numbHelper.applyMathOperator(object.getOperator(), right);

		return result;
	}

}
