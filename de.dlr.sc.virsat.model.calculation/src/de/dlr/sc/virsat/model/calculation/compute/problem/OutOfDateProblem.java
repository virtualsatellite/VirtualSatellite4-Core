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

import de.dlr.sc.virsat.model.calculation.marker.VirSatEquationMarkerHelper;

/**
 * Class representing a problem that the equation helper failed to update a problem
 * (for example due to lack of rights)
 * @author muel_s8
 *
 */
public class OutOfDateProblem extends EvaluationProblem {
	private final String expectedValue;
	
	/**
	 * Default constructor
	 * @param problematicObject the problematic object
	 * @param expectedValue the expected value
	 */
	public OutOfDateProblem(EObject problematicObject, String expectedValue) {
		super(problematicObject);
		this.expectedValue = expectedValue;
	}
	
	@Override
	public String toString() {
		return "Computed value is out of date; other disciplines need to update. Value should be " +  expectedValue + ".";
	}
	
	@Override
	public String getMarkerID() {
		return VirSatEquationMarkerHelper.ID_EVALUATION_OUT_OF_DATE_MARKER;
	}
}
