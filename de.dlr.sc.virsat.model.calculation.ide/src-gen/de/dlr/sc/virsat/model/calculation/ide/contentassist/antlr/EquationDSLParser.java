/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.calculation.ide.contentassist.antlr;

import com.google.common.collect.ImmutableMap;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import de.dlr.sc.virsat.model.calculation.ide.contentassist.antlr.internal.InternalEquationDSLParser;
import de.dlr.sc.virsat.model.calculation.services.EquationDSLGrammarAccess;
import java.util.Map;
import org.eclipse.xtext.AbstractElement;
import org.eclipse.xtext.ide.editor.contentassist.antlr.AbstractContentAssistParser;

public class EquationDSLParser extends AbstractContentAssistParser {

	@Singleton
	public static final class NameMappings {
		
		private final Map<AbstractElement, String> mappings;
		
		@Inject
		public NameMappings(EquationDSLGrammarAccess grammarAccess) {
			ImmutableMap.Builder<AbstractElement, String> builder = ImmutableMap.builder();
			init(builder, grammarAccess);
			this.mappings = builder.build();
		}
		
		public String getRuleName(AbstractElement element) {
			return mappings.get(element);
		}
		
		private static void init(ImmutableMap.Builder<AbstractElement, String> builder, EquationDSLGrammarAccess grammarAccess) {
			builder.put(grammarAccess.getEquationResultAccess().getAlternatives(), "rule__EquationResult__Alternatives");
			builder.put(grammarAccess.getAdditionAndSubtractionAccess().getOperatorAlternatives_1_1_0(), "rule__AdditionAndSubtraction__OperatorAlternatives_1_1_0");
			builder.put(grammarAccess.getMultiplicationAndDivisionAccess().getOperatorAlternatives_1_1_0(), "rule__MultiplicationAndDivision__OperatorAlternatives_1_1_0");
			builder.put(grammarAccess.getAExpressionAccess().getAlternatives(), "rule__AExpression__Alternatives");
			builder.put(grammarAccess.getALiteralAccess().getAlternatives(), "rule__ALiteral__Alternatives");
			builder.put(grammarAccess.getFunctionAccess().getOperatorAlternatives_1_0(), "rule__Function__OperatorAlternatives_1_0");
			builder.put(grammarAccess.getAAdvancedFunctionAccess().getAlternatives(), "rule__AAdvancedFunction__Alternatives");
			builder.put(grammarAccess.getEquationSectionAccess().getGroup(), "rule__EquationSection__Group__0");
			builder.put(grammarAccess.getImportAccess().getGroup(), "rule__Import__Group__0");
			builder.put(grammarAccess.getEquationAccess().getGroup(), "rule__Equation__Group__0");
			builder.put(grammarAccess.getTypeInstanceResultAccess().getGroup(), "rule__TypeInstanceResult__Group__0");
			builder.put(grammarAccess.getEquationIntermediateResultAccess().getGroup(), "rule__EquationIntermediateResult__Group__0");
			builder.put(grammarAccess.getAdditionAndSubtractionAccess().getGroup(), "rule__AdditionAndSubtraction__Group__0");
			builder.put(grammarAccess.getAdditionAndSubtractionAccess().getGroup_1(), "rule__AdditionAndSubtraction__Group_1__0");
			builder.put(grammarAccess.getMultiplicationAndDivisionAccess().getGroup(), "rule__MultiplicationAndDivision__Group__0");
			builder.put(grammarAccess.getMultiplicationAndDivisionAccess().getGroup_1(), "rule__MultiplicationAndDivision__Group_1__0");
			builder.put(grammarAccess.getPowerFunctionAccess().getGroup(), "rule__PowerFunction__Group__0");
			builder.put(grammarAccess.getPowerFunctionAccess().getGroup_1(), "rule__PowerFunction__Group_1__0");
			builder.put(grammarAccess.getParenthesisAccess().getGroup(), "rule__Parenthesis__Group__0");
			builder.put(grammarAccess.getFunctionAccess().getGroup(), "rule__Function__Group__0");
			builder.put(grammarAccess.getAdvancedFunctionAccess().getGroup(), "rule__AdvancedFunction__Group__0");
			builder.put(grammarAccess.getAdvancedFunctionAccess().getGroup_4(), "rule__AdvancedFunction__Group_4__0");
			builder.put(grammarAccess.getSetFunctionAccess().getGroup(), "rule__SetFunction__Group__0");
			builder.put(grammarAccess.getSetFunctionAccess().getGroup_4(), "rule__SetFunction__Group_4__0");
			builder.put(grammarAccess.getSetFunctionAccess().getGroup_5(), "rule__SetFunction__Group_5__0");
			builder.put(grammarAccess.getNumberLiteralAccess().getGroup(), "rule__NumberLiteral__Group__0");
			builder.put(grammarAccess.getQualifiedNameAccess().getGroup(), "rule__QualifiedName__Group__0");
			builder.put(grammarAccess.getQualifiedNameAccess().getGroup_1(), "rule__QualifiedName__Group_1__0");
			builder.put(grammarAccess.getNumberLiteralStringAccess().getGroup(), "rule__NumberLiteralString__Group__0");
			builder.put(grammarAccess.getNumberLiteralStringAccess().getGroup_2(), "rule__NumberLiteralString__Group_2__0");
			builder.put(grammarAccess.getValuePiAccess().getGroup(), "rule__ValuePi__Group__0");
			builder.put(grammarAccess.getValueEAccess().getGroup(), "rule__ValueE__Group__0");
			builder.put(grammarAccess.getEquationSectionAccess().getImportsAssignment_0(), "rule__EquationSection__ImportsAssignment_0");
			builder.put(grammarAccess.getEquationSectionAccess().getEquationsAssignment_1(), "rule__EquationSection__EquationsAssignment_1");
			builder.put(grammarAccess.getImportAccess().getImportedNamespaceAssignment_1(), "rule__Import__ImportedNamespaceAssignment_1");
			builder.put(grammarAccess.getEquationAccess().getResultAssignment_0(), "rule__Equation__ResultAssignment_0");
			builder.put(grammarAccess.getEquationAccess().getExpressionAssignment_2(), "rule__Equation__ExpressionAssignment_2");
			builder.put(grammarAccess.getTypeInstanceResultAccess().getReferenceAssignment_2(), "rule__TypeInstanceResult__ReferenceAssignment_2");
			builder.put(grammarAccess.getEquationIntermediateResultAccess().getNameAssignment_2(), "rule__EquationIntermediateResult__NameAssignment_2");
			builder.put(grammarAccess.getAdditionAndSubtractionAccess().getOperatorAssignment_1_1(), "rule__AdditionAndSubtraction__OperatorAssignment_1_1");
			builder.put(grammarAccess.getAdditionAndSubtractionAccess().getRightAssignment_1_2(), "rule__AdditionAndSubtraction__RightAssignment_1_2");
			builder.put(grammarAccess.getMultiplicationAndDivisionAccess().getOperatorAssignment_1_1(), "rule__MultiplicationAndDivision__OperatorAssignment_1_1");
			builder.put(grammarAccess.getMultiplicationAndDivisionAccess().getRightAssignment_1_2(), "rule__MultiplicationAndDivision__RightAssignment_1_2");
			builder.put(grammarAccess.getPowerFunctionAccess().getOperatorAssignment_1_1(), "rule__PowerFunction__OperatorAssignment_1_1");
			builder.put(grammarAccess.getPowerFunctionAccess().getRightAssignment_1_2(), "rule__PowerFunction__RightAssignment_1_2");
			builder.put(grammarAccess.getParenthesisAccess().getOperatorAssignment_1(), "rule__Parenthesis__OperatorAssignment_1");
			builder.put(grammarAccess.getParenthesisAccess().getRightAssignment_3(), "rule__Parenthesis__RightAssignment_3");
			builder.put(grammarAccess.getReferencedInputAccess().getReferenceAssignment(), "rule__ReferencedInput__ReferenceAssignment");
			builder.put(grammarAccess.getFunctionAccess().getOperatorAssignment_1(), "rule__Function__OperatorAssignment_1");
			builder.put(grammarAccess.getFunctionAccess().getRightAssignment_3(), "rule__Function__RightAssignment_3");
			builder.put(grammarAccess.getAdvancedFunctionAccess().getOperatorAssignment_1(), "rule__AdvancedFunction__OperatorAssignment_1");
			builder.put(grammarAccess.getAdvancedFunctionAccess().getInputsAssignment_3(), "rule__AdvancedFunction__InputsAssignment_3");
			builder.put(grammarAccess.getAdvancedFunctionAccess().getInputsAssignment_4_1(), "rule__AdvancedFunction__InputsAssignment_4_1");
			builder.put(grammarAccess.getSetFunctionAccess().getOperatorAssignment_1(), "rule__SetFunction__OperatorAssignment_1");
			builder.put(grammarAccess.getSetFunctionAccess().getTypeDefinitionAssignment_3(), "rule__SetFunction__TypeDefinitionAssignment_3");
			builder.put(grammarAccess.getSetFunctionAccess().getDepthAssignment_4_1(), "rule__SetFunction__DepthAssignment_4_1");
			builder.put(grammarAccess.getSetFunctionAccess().getFilterNameAssignment_5_1(), "rule__SetFunction__FilterNameAssignment_5_1");
			builder.put(grammarAccess.getNumberLiteralAccess().getValueAssignment_1(), "rule__NumberLiteral__ValueAssignment_1");
		}
	}
	
	@Inject
	private NameMappings nameMappings;

	@Inject
	private EquationDSLGrammarAccess grammarAccess;

	@Override
	protected InternalEquationDSLParser createParser() {
		InternalEquationDSLParser result = new InternalEquationDSLParser(null);
		result.setGrammarAccess(grammarAccess);
		return result;
	}

	@Override
	protected String getRuleName(AbstractElement element) {
		return nameMappings.getRuleName(element);
	}

	@Override
	protected String[] getInitialHiddenTokens() {
		return new String[] { "RULE_WS", "RULE_ML_COMMENT", "RULE_SL_COMMENT" };
	}

	public EquationDSLGrammarAccess getGrammarAccess() {
		return this.grammarAccess;
	}

	public void setGrammarAccess(EquationDSLGrammarAccess grammarAccess) {
		this.grammarAccess = grammarAccess;
	}
	
	public NameMappings getNameMappings() {
		return nameMappings;
	}
	
	public void setNameMappings(NameMappings nameMappings) {
		this.nameMappings = nameMappings;
	}
}
