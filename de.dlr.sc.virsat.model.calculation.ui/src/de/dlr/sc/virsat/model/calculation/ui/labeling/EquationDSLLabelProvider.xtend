/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.calculation.ui.labeling

import com.google.inject.Inject
import de.dlr.sc.virsat.model.calculation.compute.ExpressionHelper
import de.dlr.sc.virsat.model.dvlm.calculation.AdditionAndSubtraction
import de.dlr.sc.virsat.model.dvlm.calculation.Equation
import de.dlr.sc.virsat.model.dvlm.calculation.EquationIntermediateResult
import de.dlr.sc.virsat.model.dvlm.calculation.IEquationResult
import de.dlr.sc.virsat.model.dvlm.calculation.MultiplicationAndDivision
import de.dlr.sc.virsat.model.dvlm.calculation.NumberLiteral
import de.dlr.sc.virsat.model.dvlm.calculation.Parenthesis
import de.dlr.sc.virsat.model.dvlm.calculation.PowerFunction
import de.dlr.sc.virsat.model.dvlm.calculation.ReferencedInput
import de.dlr.sc.virsat.model.dvlm.calculation.SetFunction
import de.dlr.sc.virsat.model.dvlm.calculation.TypeInstanceResult
import de.dlr.sc.virsat.model.dvlm.calculation.ValueE
import de.dlr.sc.virsat.model.dvlm.calculation.ValuePi
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.APropertyInstance
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider
import org.eclipse.xtext.ui.label.DefaultEObjectLabelProvider

/**
 * Provides labels for EObjects.
 * 
 * See https://www.eclipse.org/Xtext/documentation/304_ide_concepts.html#label-provider
 */
class EquationDSLLabelProvider extends DefaultEObjectLabelProvider {

	@Inject
	new(AdapterFactoryLabelProvider delegate) {
		super(delegate);
	}
	
	new() {

	}
	
	val expressionHelper = new ExpressionHelper()

	def text(AdditionAndSubtraction ele) {
		ele.operator.literal + " (= " + expressionHelper.evaluate(ele) + ")"
	}
	
	def text(MultiplicationAndDivision ele) {
		ele.operator.literal + " (= " + expressionHelper.evaluate(ele) + ")"
	}
	
	def text(PowerFunction ele) {
		ele.operator.literal
	}
	
	def text(NumberLiteral ele) {
		ele.value
	}
	
	def text(ValuePi ele) {
		"pi"
	}
	
	def text(ValueE ele) {
		"e"
	}
	
	def text(ReferencedInput ele) {
		expressionHelper.getCompleteExpression(ele)
	}
	
	def text(SetFunction ele) {
		expressionHelper.getCompleteExpression(ele) + "(=" + expressionHelper.evaluate(ele) + ")"
	}
	
	def text(Parenthesis ele) {
		"(...)"
	}
	
	def text(IEquationResult ele) {
		if (ele instanceof EquationIntermediateResult) {
			return ele.name
		} else if (ele instanceof TypeInstanceResult) {
			val ref = ele.reference
			if (ref instanceof APropertyInstance) {
				return ref.type.name
			} else {
				return ref.styledText.convertToString
			}
		}
	}
	
	def text(Equation ele) {
		ele.result.text.concat(" = ").concat(expressionHelper.getCompleteExpression(ele.expression))
	}
}

