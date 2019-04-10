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
 * Base class for handling errors and warnings that occured during the calculation process
 * @author muel_s8
 *
 */
public class EvaluationProblem {
	private final EObject problematicObject;

	/**
	 * Default constructor
	 * @param problematicObject the problematic object
	 */
	public EvaluationProblem(EObject problematicObject) {
		this.problematicObject = problematicObject;
	}
	
	/**
	 * Gets the problematic object
	 * @return the problematic object
	 */
	public EObject getProblematicObject() {
		return problematicObject;
	}
	
	/**
	 * Gets the ID of the marker created from this evaluation problem
	 * @return the marker id
	 */
	public String getMarkerID() {
		return VirSatEquationMarkerHelper.ID_EVALUATION_MARKER;
	}
}