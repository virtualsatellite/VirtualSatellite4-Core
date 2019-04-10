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

import org.eclipse.emf.ecore.EObject;

/**
 * Interface for input getters. Input getters define the sementical meaning for
 * EObjects in the context of calculuations.
 * @author muel_s8
 *
 */

public interface IInputGetter {
	
	/**
	 * Get the expression result representing a get on an object not from the calculation domain
	 * @param input Some object not from the calculation domain
	 * @return The input object respresented as an expression result within the calculation domain
	 */
	IExpressionResult get(EObject input);
}
