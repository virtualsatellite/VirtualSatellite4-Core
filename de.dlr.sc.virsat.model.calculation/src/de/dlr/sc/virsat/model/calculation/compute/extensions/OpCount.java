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

import de.dlr.sc.virsat.model.calculation.compute.IExpressionResult;
import de.dlr.sc.virsat.model.calculation.compute.AAdvancedFunctionOp;
import de.dlr.sc.virsat.model.dvlm.calculation.CalculationFactory;
import de.dlr.sc.virsat.model.dvlm.calculation.NumberLiteral;

/**
 * Operation that sums all the inputs
 * @author muel_s8
 *
 */

public class OpCount extends AAdvancedFunctionOp {

	@Override
	public double apply(double[] inputs) {
		return inputs.length;
	}

	@Override
	public IExpressionResult handleUnresolved(List<IExpressionResult> inputs) {
		NumberLiteral number = CalculationFactory.eINSTANCE.createNumberLiteral();
		number.setValue(String.valueOf(inputs.size()));
		return new NumberLiteralResult(number);
	}
	
}
