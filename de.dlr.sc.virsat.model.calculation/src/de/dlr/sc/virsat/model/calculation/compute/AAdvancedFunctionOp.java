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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.dlr.sc.virsat.model.calculation.compute.extensions.UnresolvedExpressionResult;
import de.dlr.sc.virsat.model.dvlm.calculation.AAdvancedFunction;
import de.dlr.sc.virsat.model.dvlm.calculation.CalculationPackage;
import de.dlr.sc.virsat.model.dvlm.qudv.AQuantityKind;

/**
 * Interface that can be extended to add additional advanced functions to the calculation engine.
 * @author muel_s8
 *
 */

public abstract class AAdvancedFunctionOp {
	
	public static final String EXTENSION_POINT_ID = "de.dlr.sc.virsat.model.calculation.setFunction";
	
	/**
	 * Apply this operation to the passed inputs
	 * @param inputs the inputs for the computation
	 * @return result of applying the operation to he inputs
	 */
	public abstract double apply(double[] inputs);
	
	/**
	 * Apply this operation to the quantity kinds of the passed inputs
	 * Standard implementation: Take the first quantity kind map
	 * 
	 * Clients should override this method for their specific calculations!
	 * 
	 * @param advancedFunction the advanced function
	 * @param inputQuantityKinds the input quantity kinds
	 * @return a map of base quantities derived from the input quantity kinds
	 */
	public Map<AQuantityKind, Double> applyOnQuantityKinds(AAdvancedFunction advancedFunction, Map<AQuantityKind, Double>[] inputQuantityKinds) {
		return (inputQuantityKinds.length > 0) ? inputQuantityKinds[0] : new HashMap<>();
	}
	
	/**
	 * Apply this operation to a set of inputs containing an element that cannot be resolved.
	 * @param inputs number of inputs
	 * @return UnresolvedExpressionResult if this set operation cannot handle this, some expression result otherwise
	 * (e.g. a count operation can return the input number)
	 */
	public IExpressionResult handleUnresolved(List<IExpressionResult> inputs) {
		for (IExpressionResult input : inputs) {
			if (input instanceof UnresolvedExpressionResult) {
				return input;
			}
		}
		
		return new UnresolvedExpressionResult();
	}
	
	public static final int DEPTH_INFINITE = Integer.parseInt(CalculationPackage.eINSTANCE.getSetFunction_Depth().getDefaultValueLiteral());
}
