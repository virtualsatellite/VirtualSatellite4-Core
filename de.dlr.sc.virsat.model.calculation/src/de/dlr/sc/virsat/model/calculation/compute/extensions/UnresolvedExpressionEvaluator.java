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

import java.util.List;

import de.dlr.sc.virsat.model.calculation.compute.IExpressionEvaluator;
import de.dlr.sc.virsat.model.calculation.compute.IExpressionResult;
import de.dlr.sc.virsat.model.dvlm.calculation.AAdvancedFunction;
import de.dlr.sc.virsat.model.dvlm.calculation.ALeftOpRightExpression;
import de.dlr.sc.virsat.model.dvlm.calculation.AOpRightExpression;

/**
 * Passes through unresolved expression results
 * @author muel_s8
 *
 */

public class UnresolvedExpressionEvaluator implements IExpressionEvaluator {

	@Override
	public IExpressionResult caseAOpRightExpression(AOpRightExpression object, IExpressionResult right) {
		if (right instanceof UnresolvedExpressionResult) {
			return right;
		}
		
		return null;
	}

	@Override
	public IExpressionResult caseALeftOpRightExpression(ALeftOpRightExpression object, IExpressionResult left, IExpressionResult right) {
		if (left instanceof UnresolvedExpressionResult) {
			return left;
		} else if (right instanceof UnresolvedExpressionResult) {
			return right;
		}
		return null;
	}
	
	@Override
	public IExpressionResult caseAAdvancedFunction(AAdvancedFunction object, List<IExpressionResult> set) {		
		
		// Only apply this if there is at least 1 UnresolvedExpressionResult in the input
		for (IExpressionResult result : set) {
			if (result instanceof UnresolvedExpressionResult) {
				AdvancedFunctionHelper setHelper = new AdvancedFunctionHelper(object.getOperator());
				return setHelper.handleUnresolved(set);
			}
		}
		
		return null;
	}

}
