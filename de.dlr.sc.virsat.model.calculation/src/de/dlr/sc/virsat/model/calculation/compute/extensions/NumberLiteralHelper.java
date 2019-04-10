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

import de.dlr.sc.virsat.model.dvlm.calculation.NumberLiteral;

/**
 * Helper class for applying operations to number literals
 * @author muel_s8
 *
 */

public class NumberLiteralHelper {

	private NumberLiteral numberLiteral;
	
	/**
	 * Create a new helper
	 * @param numberLiteral The number literal this helper will be used for
	 */
	
	public NumberLiteralHelper(NumberLiteral numberLiteral) {
		this.numberLiteral = numberLiteral;
	}
	
	/**
	 * Get the value of the literal, while respecting the case of being not a number
	 * @return value of the literal
	 */
	public double getValue() {
		try {
			double doubleValue = Double.parseDouble(getNumberLiteral().getValue());
			return doubleValue;
		} catch (NullPointerException e) {
			return Double.NaN;
		} catch (NumberFormatException e) {
			return Double.NaN;
		}
	}
	
	/**
	 * Gets the encapsulated number literal
	 * @return the encapsulated number literal
	 */
	public NumberLiteral getNumberLiteral() {
		return numberLiteral;
	}
}
