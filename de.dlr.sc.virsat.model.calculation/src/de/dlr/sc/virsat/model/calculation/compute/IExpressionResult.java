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

/**
 * Contains the result of an expression
 * @author muel_s8
 *
 */

public interface IExpressionResult {
	
	/**
	 * Compares two results with each other while respecting an epsilon level of precision
	 * @param obj the objects to comapre
	 * @param eps the precision level
	 * @return true iff the results are equal up to epsilon
	 */
	boolean equals(IExpressionResult obj, double eps);
}
