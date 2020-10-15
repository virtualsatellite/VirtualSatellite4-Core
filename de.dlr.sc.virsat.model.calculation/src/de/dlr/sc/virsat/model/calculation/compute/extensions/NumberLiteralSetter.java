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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import de.dlr.sc.virsat.model.calculation.compute.IExpressionResult;
import de.dlr.sc.virsat.model.calculation.compute.IResultSetter;
import de.dlr.sc.virsat.model.calculation.compute.problem.EvaluationProblem;
import de.dlr.sc.virsat.model.calculation.compute.problem.IncompatibleQuantityKindsProblem;
import de.dlr.sc.virsat.model.calculation.compute.problem.UnknownExpressionProblem;
import de.dlr.sc.virsat.model.dvlm.calculation.CalculationFactory;
import de.dlr.sc.virsat.model.dvlm.calculation.NumberLiteral;
import de.dlr.sc.virsat.model.dvlm.categories.ATypeDefinition;
import de.dlr.sc.virsat.model.dvlm.categories.ATypeInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.IntProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.UnitValuePropertyInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ValuePropertyInstance;
import de.dlr.sc.virsat.model.dvlm.qudv.AQuantityKind;
import de.dlr.sc.virsat.model.dvlm.qudv.AUnit;
import de.dlr.sc.virsat.model.dvlm.qudv.util.QudvUnitHelper;

/**
 * Implements the setter for assigning number literals
 * @author muel_s8
 *
 */

public class NumberLiteralSetter implements IResultSetter {
	
	@Override
	public List<EvaluationProblem> set(ATypeInstance instance, IExpressionResult result) {
		if (instance instanceof ValuePropertyInstance && result instanceof NumberLiteralResult) {
			return set((ValuePropertyInstance) instance, (NumberLiteralResult) result);
		}
		
		return Collections.singletonList(new UnknownExpressionProblem(instance, result));
	}
	
	/**
	 * Handles the cases that we want to assign the number literal to a ValuePropertyInstance
	 * @param instance the ValuePropertyInstance
	 * @param result The result we want to assign
	 * @return a set of problems that has occurred when trying to perform the set, empty if none occurred
	 */
	
	public  List<EvaluationProblem> set(ValuePropertyInstance instance, NumberLiteralResult result) {
		List<EvaluationProblem> setProblems = new ArrayList<>();
		
		// Now check if this is not just a ValuePropertyInstance but also
		// a UnitValuePropertyInstance with an assigned unit. If so we need
		// to convert the value from the base unit to the unit of the instance
		
		if (instance instanceof UnitValuePropertyInstance) {
			AUnit targetUnit = ((UnitValuePropertyInstance) instance).getUnit();
			if (targetUnit != null) {
				
				Map<AQuantityKind, Double> targetBaseQuantityKinds = QudvUnitHelper.getInstance().getBaseQuantityKinds(targetUnit.getQuantityKind());
				boolean compatibleQuantityKinds = QudvUnitHelper.getInstance().haveSameQuantityKind(targetBaseQuantityKinds, result.getQuantityKinds());
				if (!compatibleQuantityKinds) {
					IncompatibleQuantityKindsProblem incompatibleQuantityKindsProblem = new IncompatibleQuantityKindsProblem(instance, targetBaseQuantityKinds, result.getQuantityKinds());
					setProblems.add(incompatibleQuantityKindsProblem);
				}
				
				NumberLiteralHelper nlh = new NumberLiteralHelper(result.getNumberLiteral());
				double value = QudvUnitHelper.getInstance().convertFromBaseUnitToTargetUnit(targetUnit, nlh.getValue());
				NumberLiteral numberLiteral = CalculationFactory.eINSTANCE.createNumberLiteral();
				numberLiteral.setValue(String.valueOf(value));
				result = new NumberLiteralResult(numberLiteral);
			}
		}
		
		ATypeDefinition type = instance.getType();
		if (type instanceof IntProperty) {
			// Check if the target instance is a integer property and if so, cut off the fractionals
			NumberLiteralHelper nlh = new NumberLiteralHelper(result.getNumberLiteral());
			int integerValue = (int) nlh.getValue();
			instance.setValue(String.valueOf(integerValue));
		} else {
			// Otherwise we can directly set the value
			instance.setValue(result.toString());
		}
		
		return setProblems;
	}

	@Override
	public boolean isApplicableFor(ATypeInstance instance) {
		return instance instanceof ValuePropertyInstance;
	}
	
	@Override
	public List<ATypeInstance> getAffectedTypeInstances(ATypeInstance instance) {
		return Collections.singletonList(instance);
	}
}
