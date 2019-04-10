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

import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.util.ITypeInstanceSetterProvider;

/**
 * Interface for registering new extensions to the calculation engine
 * @author muel_s8
 *
 */

public interface IExpressionExtender extends ITypeInstanceSetterProvider<IResultSetter> {
	
	/**
	 * Gets all input getters provided by this extender
	 * @return a list of input getters
	 */
	List<IInputGetter> getInputGetters();
	
	/**
	 * Gets all expression evaluators by this extender
	 * @return a list of expression evaluators
	 */
	List<IExpressionEvaluator> getExpressionEvaluators();
}
