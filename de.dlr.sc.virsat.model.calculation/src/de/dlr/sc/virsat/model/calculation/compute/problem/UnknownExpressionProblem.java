/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.calculation.compute.problem;

import org.eclipse.emf.ecore.EObject;

import de.dlr.sc.virsat.model.calculation.compute.IExpressionResult;

/**
 * This equation evaluation problem represents that a result setter was called
 * but it couldnt handle the passed expression result.
 * @author muel_s8
 *
 */

public class UnknownExpressionProblem extends EvaluationProblem {

	private final String resultString;
	
	/**
	 * Default constructor
	 * @param problematicObject the problematic object that we tried to set
	 * @param result the unhandled expression result
	 */
	public UnknownExpressionProblem(EObject problematicObject, IExpressionResult result) {
		super(problematicObject);
		this.resultString = result.toString();
	}
	
	@Override
	public String toString() {
		return "Computed result is of incompatible type, cannot assign " + resultString;
	}

}
