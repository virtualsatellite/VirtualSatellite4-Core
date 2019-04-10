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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.dlr.sc.virsat.model.calculation.compute.AAdvancedFunctionOp;
import de.dlr.sc.virsat.model.dvlm.calculation.AAdvancedFunction;
import de.dlr.sc.virsat.model.dvlm.calculation.CalculationFactory;
import de.dlr.sc.virsat.model.dvlm.calculation.NumberLiteral;
import de.dlr.sc.virsat.model.dvlm.qudv.AQuantityKind;

/**
 * Helper function for applying set functions
 * @author muel_s8
 *
 */

public class NumberLiteralSetFunctionHelper {
	
	private List<NumberLiteralResult> numberLiteralResults;
	
	/**
	 * Constructor taking the input for a set function
	 * @param numberLiteralResults the input
	 */
	public NumberLiteralSetFunctionHelper(List<NumberLiteralResult> numberLiteralResults) {
		this.numberLiteralResults = numberLiteralResults;
	}
	
	/**
	 * Applies a set function
	 * @param advancedFunction the advanced function operator
	 * @return the result of the operation
	 */
	public NumberLiteralResult applySetOperator(AAdvancedFunction advancedFunction) {
		double[] inputs = new double[numberLiteralResults.size()];
		
		@SuppressWarnings("unchecked")
		Map<AQuantityKind, Double>[] inputQuantityKinds = (HashMap<AQuantityKind, Double>[]) new HashMap[numberLiteralResults.size()];
		
		for (int i = 0; i < numberLiteralResults.size(); ++i) {
			NumberLiteralResult input = numberLiteralResults.get(i);
			inputs[i] = new NumberLiteralHelper(input.getNumberLiteral()).getValue();
			inputQuantityKinds[i] = input.getQuantityKinds();
		}
		
		String setOperator = advancedFunction.getOperator();
		AAdvancedFunctionOp op = new AdvancedFunctionHelper(setOperator).getSetFunctionOp();
		double doubleResult = op.apply(inputs);
		Map<AQuantityKind, Double> quantityKindResults = op.applyOnQuantityKinds(advancedFunction, inputQuantityKinds);
		
		NumberLiteral resultLiteral = CalculationFactory.eINSTANCE.createNumberLiteral();
		resultLiteral.setValue(Double.toString(doubleResult));
		
		return new NumberLiteralResult(resultLiteral, quantityKindResults);
	}
}
