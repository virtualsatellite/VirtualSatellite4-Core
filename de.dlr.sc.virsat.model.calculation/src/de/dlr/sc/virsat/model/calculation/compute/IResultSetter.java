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

import java.util.List;

import de.dlr.sc.virsat.model.calculation.compute.problem.EvaluationProblem;
import de.dlr.sc.virsat.model.dvlm.categories.ATypeInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.util.ITypeInstanceSetter;

/**
 * An interface for setting the results of an equation to an object not from
 * the calculation domain.
 * @author muel_s8
 *
 */

public interface IResultSetter extends ITypeInstanceSetter {
	
	/**
	 * Creates a command to perform the actual set operation
	 * @param instance The object not from the calculation domain
	 * @param result The result of some equation
	 * @return a list of problems that as occured during the set, empty if no problem has occured
	 */
	List<EvaluationProblem> set(ATypeInstance instance, IExpressionResult result);
}
