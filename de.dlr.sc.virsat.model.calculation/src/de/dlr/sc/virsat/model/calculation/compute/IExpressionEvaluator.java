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

import java.util.List;

import de.dlr.sc.virsat.model.dvlm.calculation.AAdvancedFunction;
import de.dlr.sc.virsat.model.dvlm.calculation.ALeftOpRightExpression;
import de.dlr.sc.virsat.model.dvlm.calculation.AOpRightExpression;

/**
 * Interface for Expression Evaluators. Expression Evaluators define how to apply operators
 * to objects from the calculation domain.
 * @author muel_s8
 *
 */

public interface IExpressionEvaluator  {
	
	/**
	 * Calculates (operation A)
	 * @param object An expression representing an operation with a single parameter
	 * @param right The parameter for the passed operation
	 * @return The resulting expression result of the evaluation
	 */
	IExpressionResult caseAOpRightExpression(AOpRightExpression object, IExpressionResult right);
	
	/**
	 * Calculates (A operation B)
	 * @param object An expression representing an operation with two paramters
	 * @param left The left side parameter
	 * @param right The right side parameter
	 * @return The resulting expression of the evaluation
	 */
	IExpressionResult caseALeftOpRightExpression(ALeftOpRightExpression object, IExpressionResult left, IExpressionResult right);
	
	/**
	 * Calculates (operation Set A)
	 * @param object the advanced function
	 * @param set the set on which the function will operate
	 * @return the result of the function call
	 */
	IExpressionResult caseAAdvancedFunction(AAdvancedFunction object, List<IExpressionResult> set);
}
