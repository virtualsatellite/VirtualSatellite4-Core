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
import java.util.Map;

import de.dlr.sc.virsat.model.calculation.compute.IExpressionResult;
import de.dlr.sc.virsat.model.dvlm.calculation.NumberLiteral;
import de.dlr.sc.virsat.model.dvlm.qudv.AQuantityKind;
import de.dlr.sc.virsat.model.dvlm.qudv.util.QudvUnitHelper;

/**
 * Implements a numberliteral result container.
 * @author muel_s8
 *
 */

public class NumberLiteralResult implements IExpressionResult {
	private final NumberLiteral numberLiteral;
	private final Map<AQuantityKind, Double> baseQuantityKinds;
	
	/**
	 * A number literal result with no specified quantity kinds
	 * @param numberLiteral The resulting number literal
	 */
	public NumberLiteralResult(NumberLiteral numberLiteral) {
		this.numberLiteral = numberLiteral;
		this.baseQuantityKinds = new HashMap<>();
	}
	
	/**
	 * A number literal result
	 * @param numberLiteral The resulting number literal
	 * @param quantityKinds The quantity kinds of the number literal
	 */
	public NumberLiteralResult(NumberLiteral numberLiteral, Map<AQuantityKind, Double> quantityKinds) {
		this.numberLiteral = numberLiteral;
		this.baseQuantityKinds = quantityKinds;
	}
	
	/**
	 * Get the number literal associated with this result
	 * @return The number literal associated with this result
	 */
	public NumberLiteral getNumberLiteral() {
		return numberLiteral;
	}
	
	/**
	 * Get the quantity kinds associated with the result
	 * @return The quantity kinds associated with this result
	 */
	public Map<AQuantityKind, Double> getQuantityKinds() {
		return baseQuantityKinds;
	}
	
	@Override
	public String toString() {
		return String.valueOf(new NumberLiteralHelper(numberLiteral).getValue());
	}
	
	@Override
	public boolean equals(IExpressionResult obj, double eps) {
		if (obj instanceof NumberLiteralResult) {
			NumberLiteralResult nlr = (NumberLiteralResult) obj;
			double value1 = new NumberLiteralHelper(numberLiteral).getValue();
			double value2 = new NumberLiteralHelper(nlr.getNumberLiteral()).getValue();
			
			boolean equalValue = Math.abs(value1 - value2) <= eps * Math.max(Math.abs(value1), Math.abs(value2));
			boolean equalQuantityKinds = QudvUnitHelper.getInstance().haveSameQuantityKind(getQuantityKinds(), nlr.getQuantityKinds());
			
			return equalValue && equalQuantityKinds;
		}
		
		return false;
	}
}
