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

import org.eclipse.emf.ecore.EObject;

import de.dlr.sc.virsat.model.calculation.compute.IExpressionResult;
import de.dlr.sc.virsat.model.calculation.compute.IInputGetter;
import de.dlr.sc.virsat.model.dvlm.calculation.CalculationFactory;
import de.dlr.sc.virsat.model.dvlm.calculation.NumberLiteral;
import de.dlr.sc.virsat.model.dvlm.calculation.ValueE;
import de.dlr.sc.virsat.model.dvlm.calculation.ValuePi;
import de.dlr.sc.virsat.model.dvlm.calculation.util.CalculationSwitch;

/**
 * Getter implementation for number literals
 * @author muel_s8
 *
 */

public class NumberLiteralGetter implements IInputGetter {
	
	@Override
	public IExpressionResult get(EObject input) {
		CalculationSwitch<IExpressionResult> calcSwitch = new CalculationSwitch<IExpressionResult>() {
			public IExpressionResult caseNumberLiteral(NumberLiteral object) {
				return new NumberLiteralResult(object);
			}
			
			public IExpressionResult caseValuePi(ValuePi object) {
				String pi = Double.toString(Math.PI);
				NumberLiteral resultLiteral = CalculationFactory.eINSTANCE.createNumberLiteral();
				resultLiteral.setValue(pi);
				return new NumberLiteralResult(resultLiteral);
			}
			
			public IExpressionResult caseValueE(ValueE object) {
				String e = Double.toString(Math.E);
				NumberLiteral resultLiteral = CalculationFactory.eINSTANCE.createNumberLiteral();
				resultLiteral.setValue(e);
				return new NumberLiteralResult(resultLiteral);
			}
		};
		return calcSwitch.doSwitch(input);
	}

}
