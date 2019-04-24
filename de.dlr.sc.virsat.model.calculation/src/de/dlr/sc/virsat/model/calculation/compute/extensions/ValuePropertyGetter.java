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

import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;

import de.dlr.sc.virsat.model.calculation.compute.IExpressionResult;
import de.dlr.sc.virsat.model.calculation.compute.IInputGetter;
import de.dlr.sc.virsat.model.dvlm.calculation.CalculationFactory;
import de.dlr.sc.virsat.model.dvlm.calculation.NumberLiteral;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.EnumValueDefinition;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.EnumUnitPropertyInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.UnitValuePropertyInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ValuePropertyInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.util.PropertyinstancesSwitch;
import de.dlr.sc.virsat.model.dvlm.qudv.AQuantityKind;
import de.dlr.sc.virsat.model.dvlm.qudv.AUnit;
import de.dlr.sc.virsat.model.dvlm.qudv.util.QudvUnitHelper;

/**
 * Implements a getter for ValueProperties
 * @author muel_s8
 *
 */

public class ValuePropertyGetter implements IInputGetter {

	@Override
	public IExpressionResult get(EObject input) {
		return new PropertyinstancesSwitch<IExpressionResult>() {
			@Override
			public IExpressionResult caseValuePropertyInstance(ValuePropertyInstance vpi) {
				NumberLiteral resultLiteral = CalculationFactory.eINSTANCE.createNumberLiteral();
				resultLiteral.setValue(vpi.getValue());
				
				// Now check if this is not just a ValuePropertyInstance but also
				// a UnitValuePropertyInstance with an assigned unit. If so we need
				// to convert the value to its corresponding value in SI units
				
				Map<AQuantityKind, Double> baseQuantityKinds = new HashMap<>();
				
				if (vpi instanceof UnitValuePropertyInstance) {
					AUnit unit = ((UnitValuePropertyInstance) vpi).getUnit();
					if (unit != null) {
						NumberLiteralHelper nlh = new NumberLiteralHelper(resultLiteral);
						double baseValue = QudvUnitHelper.getInstance().convertFromSourceUnitToBaseUnit(unit, nlh.getValue());
						resultLiteral.setValue(String.valueOf(baseValue));
						
						baseQuantityKinds = QudvUnitHelper.getInstance().getBaseQuantityKinds(unit.getQuantityKind());
					}
				}
				
				return new NumberLiteralResult(resultLiteral, baseQuantityKinds);
			};
			
			public IExpressionResult caseEnumUnitPropertyInstance(EnumUnitPropertyInstance epi) {
				NumberLiteral resultLiteral = CalculationFactory.eINSTANCE.createNumberLiteral();
				EnumValueDefinition evd = epi.getValue();
				
				Map<AQuantityKind, Double> baseQuantityKinds = new HashMap<>();
				
				if (evd != null) {
					resultLiteral.setValue(epi.getValue().getValue());
					
					AUnit unit = epi.getUnit();
					if (unit != null) {
						NumberLiteralHelper nlh = new NumberLiteralHelper(resultLiteral);
						double baseValue = QudvUnitHelper.getInstance().convertFromSourceUnitToBaseUnit(unit, nlh.getValue());
						resultLiteral.setValue(String.valueOf(baseValue));
						
						baseQuantityKinds = QudvUnitHelper.getInstance().getBaseQuantityKinds(unit.getQuantityKind());
					}
					
				} else {
					resultLiteral.setValue(String.valueOf(Double.NaN));
				}
				
				return new NumberLiteralResult(resultLiteral, baseQuantityKinds);
			};
		}.doSwitch(input);
	}

}
