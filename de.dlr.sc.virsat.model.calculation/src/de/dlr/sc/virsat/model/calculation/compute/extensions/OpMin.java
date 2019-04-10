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

import org.apache.commons.math3.stat.descriptive.rank.Min;

import de.dlr.sc.virsat.model.calculation.compute.AAdvancedFunctionOp;

/**
 * Operation that sums all the inputs
 * @author muel_s8
 *
 */

public class OpMin extends AAdvancedFunctionOp {

	@Override
	public double apply(double[] inputs) {
		Min min = new Min();
		return min.evaluate(inputs);
	}
}
