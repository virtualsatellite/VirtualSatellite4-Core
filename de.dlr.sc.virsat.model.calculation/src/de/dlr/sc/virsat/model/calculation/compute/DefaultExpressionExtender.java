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

import java.util.Arrays;
import java.util.List;

import de.dlr.sc.virsat.model.calculation.compute.extensions.EnumPropertySetter;
import de.dlr.sc.virsat.model.calculation.compute.extensions.NumberLiteralEvaluator;
import de.dlr.sc.virsat.model.calculation.compute.extensions.NumberLiteralGetter;
import de.dlr.sc.virsat.model.calculation.compute.extensions.NumberLiteralSetter;
import de.dlr.sc.virsat.model.calculation.compute.extensions.UnresolvedExpressionEvaluator;
import de.dlr.sc.virsat.model.calculation.compute.extensions.ValuePropertyGetter;

/**
 * This class provides the default extensions for handling expressions.
 * That is, it provides the necessary capabilities for dealing with numbers, value property instances,
 * enums and unresolved expressions.
 * @author muel_s8
 *
 */

public class DefaultExpressionExtender implements IExpressionExtender {

	@Override
	public List<IResultSetter> getTypeInstanceSetters() {
		return Arrays.asList(new NumberLiteralSetter(), new EnumPropertySetter());
	}

	@Override
	public List<IInputGetter> getInputGetters() {
		return Arrays.asList(new NumberLiteralGetter(), new ValuePropertyGetter());
	}

	@Override
	public List<IExpressionEvaluator> getExpressionEvaluators() {
		return Arrays.asList(new NumberLiteralEvaluator(), new UnresolvedExpressionEvaluator());
	}

}
