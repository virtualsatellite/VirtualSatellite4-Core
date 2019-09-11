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
import de.dlr.sc.virsat.model.dvlm.categories.ATypeInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.EnumProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.EnumValueDefinition;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.EnumUnitPropertyInstance;
import de.dlr.sc.virsat.model.dvlm.qudv.AQuantityKind;
import de.dlr.sc.virsat.model.dvlm.qudv.AUnit;
import de.dlr.sc.virsat.model.dvlm.qudv.util.QudvUnitHelper;

/**
 * A setter that sets the value of an enum property
 * by matching the incoming value to its definition values.
 * @author muel_s8
 *
 */

public class EnumPropertySetter implements IResultSetter {

	public static final double EPSILON = 0.000001;
	
	@Override
	public List<EvaluationProblem> set(ATypeInstance instance, IExpressionResult result) {
		if (instance instanceof EnumUnitPropertyInstance && result instanceof NumberLiteralResult) {
			return set((EnumUnitPropertyInstance) instance, (NumberLiteralResult) result);
		}
		
		return Collections.singletonList(new UnknownExpressionProblem(instance, result));
	}
	
	/**
	 * Handles the cases that we want to assign the number literal to a ValuePropertyInstance
	 * @param instance the ValuePropertyInstance
	 * @param result The result we want to assign
	 * @return a set of problems that has occurred when trying to perform the set, empty if none occurred
	 */
	
	public List<EvaluationProblem> set(EnumUnitPropertyInstance instance, NumberLiteralResult result) {
		List<EvaluationProblem> setProblems = new ArrayList<>();
		
		// Convert the value to the target unit
		
		AUnit targetUnit = ((EnumUnitPropertyInstance) instance).getUnit();
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
	
		double resultValue = Double.valueOf(result.getNumberLiteral().getValue());
		
		EnumProperty enumProperty = (EnumProperty) instance.getType();
		for (EnumValueDefinition evd : enumProperty.getValues()) {
			double value = Double.valueOf(evd.getValue());
			if (Math.abs(value - resultValue) < EPSILON) {
				instance.setValue(evd);
				break;
			}
		}
		
		return setProblems;
	}
	@Override
	public boolean isApplicableFor(ATypeInstance instance) {
		return instance instanceof EnumUnitPropertyInstance;
	}

	@Override
	public List<ATypeInstance> getAffectedTypeInstances(ATypeInstance instance) {
		return Collections.singletonList(instance);
	}

}
