/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
grammar InternalEquationDSL;

options {
	superClass=AbstractInternalAntlrParser;
}

@lexer::header {
package de.dlr.sc.virsat.model.calculation.parser.antlr.internal;

// Hack: Use our own Lexer superclass by means of import. 
// Currently there is no other way to specify the superclass for the lexer.
import org.eclipse.xtext.parser.antlr.Lexer;
}

@parser::header {
package de.dlr.sc.virsat.model.calculation.parser.antlr.internal;

import org.eclipse.xtext.*;
import org.eclipse.xtext.parser.*;
import org.eclipse.xtext.parser.impl.*;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.common.util.Enumerator;
import org.eclipse.xtext.parser.antlr.AbstractInternalAntlrParser;
import org.eclipse.xtext.parser.antlr.XtextTokenStream;
import org.eclipse.xtext.parser.antlr.XtextTokenStream.HiddenTokens;
import org.eclipse.xtext.parser.antlr.AntlrDatatypeRuleToken;
import de.dlr.sc.virsat.model.calculation.services.EquationDSLGrammarAccess;

}

@parser::members {

 	private EquationDSLGrammarAccess grammarAccess;

    public InternalEquationDSLParser(TokenStream input, EquationDSLGrammarAccess grammarAccess) {
        this(input);
        this.grammarAccess = grammarAccess;
        registerRules(grammarAccess.getGrammar());
    }

    @Override
    protected String getFirstRuleName() {
    	return "EquationSection";
   	}

   	@Override
   	protected EquationDSLGrammarAccess getGrammarAccess() {
   		return grammarAccess;
   	}

}

@rulecatch {
    catch (RecognitionException re) {
        recover(input,re);
        appendSkippedTokens();
    }
}

// Entry rule entryRuleEquationSection
entryRuleEquationSection returns [EObject current=null]:
	{ newCompositeNode(grammarAccess.getEquationSectionRule()); }
	iv_ruleEquationSection=ruleEquationSection
	{ $current=$iv_ruleEquationSection.current; }
	EOF;

// Rule EquationSection
ruleEquationSection returns [EObject current=null]
@init {
	enterRule();
}
@after {
	leaveRule();
}:
	(
		(
			(
				{
					newCompositeNode(grammarAccess.getEquationSectionAccess().getImportsImportParserRuleCall_0_0());
				}
				lv_imports_0_0=ruleImport
				{
					if ($current==null) {
						$current = createModelElementForParent(grammarAccess.getEquationSectionRule());
					}
					add(
						$current,
						"imports",
						lv_imports_0_0,
						"de.dlr.sc.virsat.model.calculation.EquationDSL.Import");
					afterParserOrEnumRuleCall();
				}
			)
		)*
		(
			(
				{
					newCompositeNode(grammarAccess.getEquationSectionAccess().getEquationsEquationParserRuleCall_1_0());
				}
				lv_equations_1_0=ruleEquation
				{
					if ($current==null) {
						$current = createModelElementForParent(grammarAccess.getEquationSectionRule());
					}
					add(
						$current,
						"equations",
						lv_equations_1_0,
						"de.dlr.sc.virsat.model.calculation.EquationDSL.Equation");
					afterParserOrEnumRuleCall();
				}
			)
		)*
	)
;

// Entry rule entryRuleImport
entryRuleImport returns [EObject current=null]:
	{ newCompositeNode(grammarAccess.getImportRule()); }
	iv_ruleImport=ruleImport
	{ $current=$iv_ruleImport.current; }
	EOF;

// Rule Import
ruleImport returns [EObject current=null]
@init {
	enterRule();
}
@after {
	leaveRule();
}:
	(
		otherlv_0='Import:'
		{
			newLeafNode(otherlv_0, grammarAccess.getImportAccess().getImportKeyword_0());
		}
		(
			(
				{
					if ($current==null) {
						$current = createModelElement(grammarAccess.getImportRule());
					}
				}
				{
					newCompositeNode(grammarAccess.getImportAccess().getImportedNamespaceIInstanceCrossReference_1_0());
				}
				ruleQualifiedName
				{
					afterParserOrEnumRuleCall();
				}
			)
		)
		otherlv_2=';'
		{
			newLeafNode(otherlv_2, grammarAccess.getImportAccess().getSemicolonKeyword_2());
		}
	)
;

// Entry rule entryRuleEquation
entryRuleEquation returns [EObject current=null]:
	{ newCompositeNode(grammarAccess.getEquationRule()); }
	iv_ruleEquation=ruleEquation
	{ $current=$iv_ruleEquation.current; }
	EOF;

// Rule Equation
ruleEquation returns [EObject current=null]
@init {
	enterRule();
}
@after {
	leaveRule();
}:
	(
		(
			(
				{
					newCompositeNode(grammarAccess.getEquationAccess().getResultEquationResultParserRuleCall_0_0());
				}
				lv_result_0_0=ruleEquationResult
				{
					if ($current==null) {
						$current = createModelElementForParent(grammarAccess.getEquationRule());
					}
					set(
						$current,
						"result",
						lv_result_0_0,
						"de.dlr.sc.virsat.model.calculation.EquationDSL.EquationResult");
					afterParserOrEnumRuleCall();
				}
			)
		)
		otherlv_1='='
		{
			newLeafNode(otherlv_1, grammarAccess.getEquationAccess().getEqualsSignKeyword_1());
		}
		(
			(
				{
					newCompositeNode(grammarAccess.getEquationAccess().getExpressionAdditionAndSubtractionParserRuleCall_2_0());
				}
				lv_expression_2_0=ruleAdditionAndSubtraction
				{
					if ($current==null) {
						$current = createModelElementForParent(grammarAccess.getEquationRule());
					}
					set(
						$current,
						"expression",
						lv_expression_2_0,
						"de.dlr.sc.virsat.model.calculation.EquationDSL.AdditionAndSubtraction");
					afterParserOrEnumRuleCall();
				}
			)
		)
		otherlv_3=';'
		{
			newLeafNode(otherlv_3, grammarAccess.getEquationAccess().getSemicolonKeyword_3());
		}
	)
;

// Entry rule entryRuleEquationResult
entryRuleEquationResult returns [EObject current=null]:
	{ newCompositeNode(grammarAccess.getEquationResultRule()); }
	iv_ruleEquationResult=ruleEquationResult
	{ $current=$iv_ruleEquationResult.current; }
	EOF;

// Rule EquationResult
ruleEquationResult returns [EObject current=null]
@init {
	enterRule();
}
@after {
	leaveRule();
}:
	(
		{
			newCompositeNode(grammarAccess.getEquationResultAccess().getTypeInstanceResultParserRuleCall_0());
		}
		this_TypeInstanceResult_0=ruleTypeInstanceResult
		{
			$current = $this_TypeInstanceResult_0.current;
			afterParserOrEnumRuleCall();
		}
		    |
		{
			newCompositeNode(grammarAccess.getEquationResultAccess().getEquationIntermediateResultParserRuleCall_1());
		}
		this_EquationIntermediateResult_1=ruleEquationIntermediateResult
		{
			$current = $this_EquationIntermediateResult_1.current;
			afterParserOrEnumRuleCall();
		}
	)
;

// Entry rule entryRuleTypeInstanceResult
entryRuleTypeInstanceResult returns [EObject current=null]:
	{ newCompositeNode(grammarAccess.getTypeInstanceResultRule()); }
	iv_ruleTypeInstanceResult=ruleTypeInstanceResult
	{ $current=$iv_ruleTypeInstanceResult.current; }
	EOF;

// Rule TypeInstanceResult
ruleTypeInstanceResult returns [EObject current=null]
@init {
	enterRule();
}
@after {
	leaveRule();
}:
	(
		(
			{
				$current = forceCreateModelElement(
					grammarAccess.getTypeInstanceResultAccess().getTypeInstanceResultAction_0(),
					$current);
			}
		)
		otherlv_1='Ref:'
		{
			newLeafNode(otherlv_1, grammarAccess.getTypeInstanceResultAccess().getRefKeyword_1());
		}
		(
			(
				{
					if ($current==null) {
						$current = createModelElement(grammarAccess.getTypeInstanceResultRule());
					}
				}
				{
					newCompositeNode(grammarAccess.getTypeInstanceResultAccess().getReferenceATypeInstanceCrossReference_2_0());
				}
				ruleQualifiedName
				{
					afterParserOrEnumRuleCall();
				}
			)
		)
	)
;

// Entry rule entryRuleEquationIntermediateResult
entryRuleEquationIntermediateResult returns [EObject current=null]:
	{ newCompositeNode(grammarAccess.getEquationIntermediateResultRule()); }
	iv_ruleEquationIntermediateResult=ruleEquationIntermediateResult
	{ $current=$iv_ruleEquationIntermediateResult.current; }
	EOF;

// Rule EquationIntermediateResult
ruleEquationIntermediateResult returns [EObject current=null]
@init {
	enterRule();
}
@after {
	leaveRule();
}:
	(
		(
			{
				$current = forceCreateModelElement(
					grammarAccess.getEquationIntermediateResultAccess().getEquationIntermediateResultAction_0(),
					$current);
			}
		)
		otherlv_1='Calc:'
		{
			newLeafNode(otherlv_1, grammarAccess.getEquationIntermediateResultAccess().getCalcKeyword_1());
		}
		(
			(
				lv_name_2_0=RULE_ID
				{
					newLeafNode(lv_name_2_0, grammarAccess.getEquationIntermediateResultAccess().getNameIDTerminalRuleCall_2_0());
				}
				{
					if ($current==null) {
						$current = createModelElement(grammarAccess.getEquationIntermediateResultRule());
					}
					setWithLastConsumed(
						$current,
						"name",
						lv_name_2_0,
						"org.eclipse.xtext.common.Terminals.ID");
				}
			)
		)
	)
;

// Entry rule entryRuleAdditionAndSubtraction
entryRuleAdditionAndSubtraction returns [EObject current=null]:
	{ newCompositeNode(grammarAccess.getAdditionAndSubtractionRule()); }
	iv_ruleAdditionAndSubtraction=ruleAdditionAndSubtraction
	{ $current=$iv_ruleAdditionAndSubtraction.current; }
	EOF;

// Rule AdditionAndSubtraction
ruleAdditionAndSubtraction returns [EObject current=null]
@init {
	enterRule();
}
@after {
	leaveRule();
}:
	(
		{
			newCompositeNode(grammarAccess.getAdditionAndSubtractionAccess().getMultiplicationAndDivisionParserRuleCall_0());
		}
		this_MultiplicationAndDivision_0=ruleMultiplicationAndDivision
		{
			$current = $this_MultiplicationAndDivision_0.current;
			afterParserOrEnumRuleCall();
		}
		(
			(
				{
					$current = forceCreateModelElementAndSet(
						grammarAccess.getAdditionAndSubtractionAccess().getAdditionAndSubtractionLeftAction_1_0(),
						$current);
				}
			)
			(
				(
					(
						{
							newCompositeNode(grammarAccess.getAdditionAndSubtractionAccess().getOperatorOperatorPlusEnumRuleCall_1_1_0_0());
						}
						lv_operator_2_1=ruleOperatorPlus
						{
							if ($current==null) {
								$current = createModelElementForParent(grammarAccess.getAdditionAndSubtractionRule());
							}
							set(
								$current,
								"operator",
								lv_operator_2_1,
								"de.dlr.sc.virsat.model.calculation.EquationDSL.OperatorPlus");
							afterParserOrEnumRuleCall();
						}
						    |
						{
							newCompositeNode(grammarAccess.getAdditionAndSubtractionAccess().getOperatorOperatorMinusEnumRuleCall_1_1_0_1());
						}
						lv_operator_2_2=ruleOperatorMinus
						{
							if ($current==null) {
								$current = createModelElementForParent(grammarAccess.getAdditionAndSubtractionRule());
							}
							set(
								$current,
								"operator",
								lv_operator_2_2,
								"de.dlr.sc.virsat.model.calculation.EquationDSL.OperatorMinus");
							afterParserOrEnumRuleCall();
						}
					)
				)
			)
			(
				(
					{
						newCompositeNode(grammarAccess.getAdditionAndSubtractionAccess().getRightMultiplicationAndDivisionParserRuleCall_1_2_0());
					}
					lv_right_3_0=ruleMultiplicationAndDivision
					{
						if ($current==null) {
							$current = createModelElementForParent(grammarAccess.getAdditionAndSubtractionRule());
						}
						set(
							$current,
							"right",
							lv_right_3_0,
							"de.dlr.sc.virsat.model.calculation.EquationDSL.MultiplicationAndDivision");
						afterParserOrEnumRuleCall();
					}
				)
			)
		)*
	)
;

// Entry rule entryRuleMultiplicationAndDivision
entryRuleMultiplicationAndDivision returns [EObject current=null]:
	{ newCompositeNode(grammarAccess.getMultiplicationAndDivisionRule()); }
	iv_ruleMultiplicationAndDivision=ruleMultiplicationAndDivision
	{ $current=$iv_ruleMultiplicationAndDivision.current; }
	EOF;

// Rule MultiplicationAndDivision
ruleMultiplicationAndDivision returns [EObject current=null]
@init {
	enterRule();
}
@after {
	leaveRule();
}:
	(
		{
			newCompositeNode(grammarAccess.getMultiplicationAndDivisionAccess().getPowerFunctionParserRuleCall_0());
		}
		this_PowerFunction_0=rulePowerFunction
		{
			$current = $this_PowerFunction_0.current;
			afterParserOrEnumRuleCall();
		}
		(
			(
				{
					$current = forceCreateModelElementAndSet(
						grammarAccess.getMultiplicationAndDivisionAccess().getMultiplicationAndDivisionLeftAction_1_0(),
						$current);
				}
			)
			(
				(
					(
						{
							newCompositeNode(grammarAccess.getMultiplicationAndDivisionAccess().getOperatorOperatorMultiplyEnumRuleCall_1_1_0_0());
						}
						lv_operator_2_1=ruleOperatorMultiply
						{
							if ($current==null) {
								$current = createModelElementForParent(grammarAccess.getMultiplicationAndDivisionRule());
							}
							set(
								$current,
								"operator",
								lv_operator_2_1,
								"de.dlr.sc.virsat.model.calculation.EquationDSL.OperatorMultiply");
							afterParserOrEnumRuleCall();
						}
						    |
						{
							newCompositeNode(grammarAccess.getMultiplicationAndDivisionAccess().getOperatorOperatorDivideEnumRuleCall_1_1_0_1());
						}
						lv_operator_2_2=ruleOperatorDivide
						{
							if ($current==null) {
								$current = createModelElementForParent(grammarAccess.getMultiplicationAndDivisionRule());
							}
							set(
								$current,
								"operator",
								lv_operator_2_2,
								"de.dlr.sc.virsat.model.calculation.EquationDSL.OperatorDivide");
							afterParserOrEnumRuleCall();
						}
					)
				)
			)
			(
				(
					{
						newCompositeNode(grammarAccess.getMultiplicationAndDivisionAccess().getRightPowerFunctionParserRuleCall_1_2_0());
					}
					lv_right_3_0=rulePowerFunction
					{
						if ($current==null) {
							$current = createModelElementForParent(grammarAccess.getMultiplicationAndDivisionRule());
						}
						set(
							$current,
							"right",
							lv_right_3_0,
							"de.dlr.sc.virsat.model.calculation.EquationDSL.PowerFunction");
						afterParserOrEnumRuleCall();
					}
				)
			)
		)*
	)
;

// Entry rule entryRulePowerFunction
entryRulePowerFunction returns [EObject current=null]:
	{ newCompositeNode(grammarAccess.getPowerFunctionRule()); }
	iv_rulePowerFunction=rulePowerFunction
	{ $current=$iv_rulePowerFunction.current; }
	EOF;

// Rule PowerFunction
rulePowerFunction returns [EObject current=null]
@init {
	enterRule();
}
@after {
	leaveRule();
}:
	(
		{
			newCompositeNode(grammarAccess.getPowerFunctionAccess().getAExpressionParserRuleCall_0());
		}
		this_AExpression_0=ruleAExpression
		{
			$current = $this_AExpression_0.current;
			afterParserOrEnumRuleCall();
		}
		(
			(
				{
					$current = forceCreateModelElementAndSet(
						grammarAccess.getPowerFunctionAccess().getPowerFunctionLeftAction_1_0(),
						$current);
				}
			)
			(
				(
					{
						newCompositeNode(grammarAccess.getPowerFunctionAccess().getOperatorOperatorPowerEnumRuleCall_1_1_0());
					}
					lv_operator_2_0=ruleOperatorPower
					{
						if ($current==null) {
							$current = createModelElementForParent(grammarAccess.getPowerFunctionRule());
						}
						set(
							$current,
							"operator",
							lv_operator_2_0,
							"de.dlr.sc.virsat.model.calculation.EquationDSL.OperatorPower");
						afterParserOrEnumRuleCall();
					}
				)
			)
			(
				(
					{
						newCompositeNode(grammarAccess.getPowerFunctionAccess().getRightAExpressionParserRuleCall_1_2_0());
					}
					lv_right_3_0=ruleAExpression
					{
						if ($current==null) {
							$current = createModelElementForParent(grammarAccess.getPowerFunctionRule());
						}
						set(
							$current,
							"right",
							lv_right_3_0,
							"de.dlr.sc.virsat.model.calculation.EquationDSL.AExpression");
						afterParserOrEnumRuleCall();
					}
				)
			)
		)*
	)
;

// Entry rule entryRuleParenthesis
entryRuleParenthesis returns [EObject current=null]:
	{ newCompositeNode(grammarAccess.getParenthesisRule()); }
	iv_ruleParenthesis=ruleParenthesis
	{ $current=$iv_ruleParenthesis.current; }
	EOF;

// Rule Parenthesis
ruleParenthesis returns [EObject current=null]
@init {
	enterRule();
}
@after {
	leaveRule();
}:
	(
		(
			{
				$current = forceCreateModelElement(
					grammarAccess.getParenthesisAccess().getParenthesisAction_0(),
					$current);
			}
		)
		(
			(
				{
					newCompositeNode(grammarAccess.getParenthesisAccess().getOperatorOperatorMinusEnumRuleCall_1_0());
				}
				lv_operator_1_0=ruleOperatorMinus
				{
					if ($current==null) {
						$current = createModelElementForParent(grammarAccess.getParenthesisRule());
					}
					set(
						$current,
						"operator",
						lv_operator_1_0,
						"de.dlr.sc.virsat.model.calculation.EquationDSL.OperatorMinus");
					afterParserOrEnumRuleCall();
				}
			)
		)?
		otherlv_2='('
		{
			newLeafNode(otherlv_2, grammarAccess.getParenthesisAccess().getLeftParenthesisKeyword_2());
		}
		(
			(
				{
					newCompositeNode(grammarAccess.getParenthesisAccess().getRightAdditionAndSubtractionParserRuleCall_3_0());
				}
				lv_right_3_0=ruleAdditionAndSubtraction
				{
					if ($current==null) {
						$current = createModelElementForParent(grammarAccess.getParenthesisRule());
					}
					set(
						$current,
						"right",
						lv_right_3_0,
						"de.dlr.sc.virsat.model.calculation.EquationDSL.AdditionAndSubtraction");
					afterParserOrEnumRuleCall();
				}
			)
		)
		otherlv_4=')'
		{
			newLeafNode(otherlv_4, grammarAccess.getParenthesisAccess().getRightParenthesisKeyword_4());
		}
	)
;

// Entry rule entryRuleAExpression
entryRuleAExpression returns [EObject current=null]:
	{ newCompositeNode(grammarAccess.getAExpressionRule()); }
	iv_ruleAExpression=ruleAExpression
	{ $current=$iv_ruleAExpression.current; }
	EOF;

// Rule AExpression
ruleAExpression returns [EObject current=null]
@init {
	enterRule();
}
@after {
	leaveRule();
}:
	(
		{
			newCompositeNode(grammarAccess.getAExpressionAccess().getALiteralParserRuleCall_0());
		}
		this_ALiteral_0=ruleALiteral
		{
			$current = $this_ALiteral_0.current;
			afterParserOrEnumRuleCall();
		}
		    |
		{
			newCompositeNode(grammarAccess.getAExpressionAccess().getReferencedInputParserRuleCall_1());
		}
		this_ReferencedInput_1=ruleReferencedInput
		{
			$current = $this_ReferencedInput_1.current;
			afterParserOrEnumRuleCall();
		}
		    |
		{
			newCompositeNode(grammarAccess.getAExpressionAccess().getParenthesisParserRuleCall_2());
		}
		this_Parenthesis_2=ruleParenthesis
		{
			$current = $this_Parenthesis_2.current;
			afterParserOrEnumRuleCall();
		}
		    |
		{
			newCompositeNode(grammarAccess.getAExpressionAccess().getFunctionParserRuleCall_3());
		}
		this_Function_3=ruleFunction
		{
			$current = $this_Function_3.current;
			afterParserOrEnumRuleCall();
		}
		    |
		{
			newCompositeNode(grammarAccess.getAExpressionAccess().getAAdvancedFunctionParserRuleCall_4());
		}
		this_AAdvancedFunction_4=ruleAAdvancedFunction
		{
			$current = $this_AAdvancedFunction_4.current;
			afterParserOrEnumRuleCall();
		}
	)
;

// Entry rule entryRuleReferencedInput
entryRuleReferencedInput returns [EObject current=null]:
	{ newCompositeNode(grammarAccess.getReferencedInputRule()); }
	iv_ruleReferencedInput=ruleReferencedInput
	{ $current=$iv_ruleReferencedInput.current; }
	EOF;

// Rule ReferencedInput
ruleReferencedInput returns [EObject current=null]
@init {
	enterRule();
}
@after {
	leaveRule();
}:
	(
		(
			{
				if ($current==null) {
					$current = createModelElement(grammarAccess.getReferencedInputRule());
				}
			}
			{
				newCompositeNode(grammarAccess.getReferencedInputAccess().getReferenceIEquationInputCrossReference_0());
			}
			ruleQualifiedName
			{
				afterParserOrEnumRuleCall();
			}
		)
	)
;

// Entry rule entryRuleALiteral
entryRuleALiteral returns [EObject current=null]:
	{ newCompositeNode(grammarAccess.getALiteralRule()); }
	iv_ruleALiteral=ruleALiteral
	{ $current=$iv_ruleALiteral.current; }
	EOF;

// Rule ALiteral
ruleALiteral returns [EObject current=null]
@init {
	enterRule();
}
@after {
	leaveRule();
}:
	(
		{
			newCompositeNode(grammarAccess.getALiteralAccess().getNumberLiteralParserRuleCall_0());
		}
		this_NumberLiteral_0=ruleNumberLiteral
		{
			$current = $this_NumberLiteral_0.current;
			afterParserOrEnumRuleCall();
		}
		    |
		{
			newCompositeNode(grammarAccess.getALiteralAccess().getValueEParserRuleCall_1());
		}
		this_ValueE_1=ruleValueE
		{
			$current = $this_ValueE_1.current;
			afterParserOrEnumRuleCall();
		}
		    |
		{
			newCompositeNode(grammarAccess.getALiteralAccess().getValuePiParserRuleCall_2());
		}
		this_ValuePi_2=ruleValuePi
		{
			$current = $this_ValuePi_2.current;
			afterParserOrEnumRuleCall();
		}
	)
;

// Entry rule entryRuleFunction
entryRuleFunction returns [EObject current=null]:
	{ newCompositeNode(grammarAccess.getFunctionRule()); }
	iv_ruleFunction=ruleFunction
	{ $current=$iv_ruleFunction.current; }
	EOF;

// Rule Function
ruleFunction returns [EObject current=null]
@init {
	enterRule();
}
@after {
	leaveRule();
}:
	(
		(
			{
				$current = forceCreateModelElement(
					grammarAccess.getFunctionAccess().getFunctionAction_0(),
					$current);
			}
		)
		(
			(
				(
					{
						newCompositeNode(grammarAccess.getFunctionAccess().getOperatorOperatorCosEnumRuleCall_1_0_0());
					}
					lv_operator_1_1=ruleOperatorCos
					{
						if ($current==null) {
							$current = createModelElementForParent(grammarAccess.getFunctionRule());
						}
						set(
							$current,
							"operator",
							lv_operator_1_1,
							"de.dlr.sc.virsat.model.calculation.EquationDSL.OperatorCos");
						afterParserOrEnumRuleCall();
					}
					    |
					{
						newCompositeNode(grammarAccess.getFunctionAccess().getOperatorOperatorSinEnumRuleCall_1_0_1());
					}
					lv_operator_1_2=ruleOperatorSin
					{
						if ($current==null) {
							$current = createModelElementForParent(grammarAccess.getFunctionRule());
						}
						set(
							$current,
							"operator",
							lv_operator_1_2,
							"de.dlr.sc.virsat.model.calculation.EquationDSL.OperatorSin");
						afterParserOrEnumRuleCall();
					}
					    |
					{
						newCompositeNode(grammarAccess.getFunctionAccess().getOperatorOperatorTanEnumRuleCall_1_0_2());
					}
					lv_operator_1_3=ruleOperatorTan
					{
						if ($current==null) {
							$current = createModelElementForParent(grammarAccess.getFunctionRule());
						}
						set(
							$current,
							"operator",
							lv_operator_1_3,
							"de.dlr.sc.virsat.model.calculation.EquationDSL.OperatorTan");
						afterParserOrEnumRuleCall();
					}
					    |
					{
						newCompositeNode(grammarAccess.getFunctionAccess().getOperatorOperatorAtanEnumRuleCall_1_0_3());
					}
					lv_operator_1_4=ruleOperatorAtan
					{
						if ($current==null) {
							$current = createModelElementForParent(grammarAccess.getFunctionRule());
						}
						set(
							$current,
							"operator",
							lv_operator_1_4,
							"de.dlr.sc.virsat.model.calculation.EquationDSL.OperatorAtan");
						afterParserOrEnumRuleCall();
					}
					    |
					{
						newCompositeNode(grammarAccess.getFunctionAccess().getOperatorOperatorAcosEnumRuleCall_1_0_4());
					}
					lv_operator_1_5=ruleOperatorAcos
					{
						if ($current==null) {
							$current = createModelElementForParent(grammarAccess.getFunctionRule());
						}
						set(
							$current,
							"operator",
							lv_operator_1_5,
							"de.dlr.sc.virsat.model.calculation.EquationDSL.OperatorAcos");
						afterParserOrEnumRuleCall();
					}
					    |
					{
						newCompositeNode(grammarAccess.getFunctionAccess().getOperatorOperatorAsinEnumRuleCall_1_0_5());
					}
					lv_operator_1_6=ruleOperatorAsin
					{
						if ($current==null) {
							$current = createModelElementForParent(grammarAccess.getFunctionRule());
						}
						set(
							$current,
							"operator",
							lv_operator_1_6,
							"de.dlr.sc.virsat.model.calculation.EquationDSL.OperatorAsin");
						afterParserOrEnumRuleCall();
					}
					    |
					{
						newCompositeNode(grammarAccess.getFunctionAccess().getOperatorOperatorSqrtEnumRuleCall_1_0_6());
					}
					lv_operator_1_7=ruleOperatorSqrt
					{
						if ($current==null) {
							$current = createModelElementForParent(grammarAccess.getFunctionRule());
						}
						set(
							$current,
							"operator",
							lv_operator_1_7,
							"de.dlr.sc.virsat.model.calculation.EquationDSL.OperatorSqrt");
						afterParserOrEnumRuleCall();
					}
					    |
					{
						newCompositeNode(grammarAccess.getFunctionAccess().getOperatorOperatorLogEnumRuleCall_1_0_7());
					}
					lv_operator_1_8=ruleOperatorLog
					{
						if ($current==null) {
							$current = createModelElementForParent(grammarAccess.getFunctionRule());
						}
						set(
							$current,
							"operator",
							lv_operator_1_8,
							"de.dlr.sc.virsat.model.calculation.EquationDSL.OperatorLog");
						afterParserOrEnumRuleCall();
					}
					    |
					{
						newCompositeNode(grammarAccess.getFunctionAccess().getOperatorOperatorLnEnumRuleCall_1_0_8());
					}
					lv_operator_1_9=ruleOperatorLn
					{
						if ($current==null) {
							$current = createModelElementForParent(grammarAccess.getFunctionRule());
						}
						set(
							$current,
							"operator",
							lv_operator_1_9,
							"de.dlr.sc.virsat.model.calculation.EquationDSL.OperatorLn");
						afterParserOrEnumRuleCall();
					}
					    |
					{
						newCompositeNode(grammarAccess.getFunctionAccess().getOperatorOperatorLdEnumRuleCall_1_0_9());
					}
					lv_operator_1_10=ruleOperatorLd
					{
						if ($current==null) {
							$current = createModelElementForParent(grammarAccess.getFunctionRule());
						}
						set(
							$current,
							"operator",
							lv_operator_1_10,
							"de.dlr.sc.virsat.model.calculation.EquationDSL.OperatorLd");
						afterParserOrEnumRuleCall();
					}
					    |
					{
						newCompositeNode(grammarAccess.getFunctionAccess().getOperatorOperatorExpEnumRuleCall_1_0_10());
					}
					lv_operator_1_11=ruleOperatorExp
					{
						if ($current==null) {
							$current = createModelElementForParent(grammarAccess.getFunctionRule());
						}
						set(
							$current,
							"operator",
							lv_operator_1_11,
							"de.dlr.sc.virsat.model.calculation.EquationDSL.OperatorExp");
						afterParserOrEnumRuleCall();
					}
				)
			)
		)
		otherlv_2='('
		{
			newLeafNode(otherlv_2, grammarAccess.getFunctionAccess().getLeftParenthesisKeyword_2());
		}
		(
			(
				{
					newCompositeNode(grammarAccess.getFunctionAccess().getRightAdditionAndSubtractionParserRuleCall_3_0());
				}
				lv_right_3_0=ruleAdditionAndSubtraction
				{
					if ($current==null) {
						$current = createModelElementForParent(grammarAccess.getFunctionRule());
					}
					set(
						$current,
						"right",
						lv_right_3_0,
						"de.dlr.sc.virsat.model.calculation.EquationDSL.AdditionAndSubtraction");
					afterParserOrEnumRuleCall();
				}
			)
		)
		otherlv_4=')'
		{
			newLeafNode(otherlv_4, grammarAccess.getFunctionAccess().getRightParenthesisKeyword_4());
		}
	)
;

// Entry rule entryRuleAAdvancedFunction
entryRuleAAdvancedFunction returns [EObject current=null]:
	{ newCompositeNode(grammarAccess.getAAdvancedFunctionRule()); }
	iv_ruleAAdvancedFunction=ruleAAdvancedFunction
	{ $current=$iv_ruleAAdvancedFunction.current; }
	EOF;

// Rule AAdvancedFunction
ruleAAdvancedFunction returns [EObject current=null]
@init {
	enterRule();
}
@after {
	leaveRule();
}:
	(
		{
			newCompositeNode(grammarAccess.getAAdvancedFunctionAccess().getAdvancedFunctionParserRuleCall_0());
		}
		this_AdvancedFunction_0=ruleAdvancedFunction
		{
			$current = $this_AdvancedFunction_0.current;
			afterParserOrEnumRuleCall();
		}
		    |
		{
			newCompositeNode(grammarAccess.getAAdvancedFunctionAccess().getSetFunctionParserRuleCall_1());
		}
		this_SetFunction_1=ruleSetFunction
		{
			$current = $this_SetFunction_1.current;
			afterParserOrEnumRuleCall();
		}
	)
;

// Entry rule entryRuleAdvancedFunction
entryRuleAdvancedFunction returns [EObject current=null]:
	{ newCompositeNode(grammarAccess.getAdvancedFunctionRule()); }
	iv_ruleAdvancedFunction=ruleAdvancedFunction
	{ $current=$iv_ruleAdvancedFunction.current; }
	EOF;

// Rule AdvancedFunction
ruleAdvancedFunction returns [EObject current=null]
@init {
	enterRule();
}
@after {
	leaveRule();
}:
	(
		(
			{
				$current = forceCreateModelElement(
					grammarAccess.getAdvancedFunctionAccess().getAdvancedFunctionAction_0(),
					$current);
			}
		)
		(
			(
				lv_operator_1_0=RULE_ID
				{
					newLeafNode(lv_operator_1_0, grammarAccess.getAdvancedFunctionAccess().getOperatorIDTerminalRuleCall_1_0());
				}
				{
					if ($current==null) {
						$current = createModelElement(grammarAccess.getAdvancedFunctionRule());
					}
					setWithLastConsumed(
						$current,
						"operator",
						lv_operator_1_0,
						"org.eclipse.xtext.common.Terminals.ID");
				}
			)
		)
		otherlv_2='('
		{
			newLeafNode(otherlv_2, grammarAccess.getAdvancedFunctionAccess().getLeftParenthesisKeyword_2());
		}
		(
			(
				{
					newCompositeNode(grammarAccess.getAdvancedFunctionAccess().getInputsAdditionAndSubtractionParserRuleCall_3_0());
				}
				lv_inputs_3_0=ruleAdditionAndSubtraction
				{
					if ($current==null) {
						$current = createModelElementForParent(grammarAccess.getAdvancedFunctionRule());
					}
					add(
						$current,
						"inputs",
						lv_inputs_3_0,
						"de.dlr.sc.virsat.model.calculation.EquationDSL.AdditionAndSubtraction");
					afterParserOrEnumRuleCall();
				}
			)
		)
		(
			otherlv_4=','
			{
				newLeafNode(otherlv_4, grammarAccess.getAdvancedFunctionAccess().getCommaKeyword_4_0());
			}
			(
				(
					{
						newCompositeNode(grammarAccess.getAdvancedFunctionAccess().getInputsAdditionAndSubtractionParserRuleCall_4_1_0());
					}
					lv_inputs_5_0=ruleAdditionAndSubtraction
					{
						if ($current==null) {
							$current = createModelElementForParent(grammarAccess.getAdvancedFunctionRule());
						}
						add(
							$current,
							"inputs",
							lv_inputs_5_0,
							"de.dlr.sc.virsat.model.calculation.EquationDSL.AdditionAndSubtraction");
						afterParserOrEnumRuleCall();
					}
				)
			)
		)*
		otherlv_6=')'
		{
			newLeafNode(otherlv_6, grammarAccess.getAdvancedFunctionAccess().getRightParenthesisKeyword_5());
		}
	)
;

// Entry rule entryRuleSetFunction
entryRuleSetFunction returns [EObject current=null]:
	{ newCompositeNode(grammarAccess.getSetFunctionRule()); }
	iv_ruleSetFunction=ruleSetFunction
	{ $current=$iv_ruleSetFunction.current; }
	EOF;

// Rule SetFunction
ruleSetFunction returns [EObject current=null]
@init {
	enterRule();
}
@after {
	leaveRule();
}:
	(
		(
			{
				$current = forceCreateModelElement(
					grammarAccess.getSetFunctionAccess().getSetFunctionAction_0(),
					$current);
			}
		)
		(
			(
				lv_operator_1_0=RULE_ID
				{
					newLeafNode(lv_operator_1_0, grammarAccess.getSetFunctionAccess().getOperatorIDTerminalRuleCall_1_0());
				}
				{
					if ($current==null) {
						$current = createModelElement(grammarAccess.getSetFunctionRule());
					}
					setWithLastConsumed(
						$current,
						"operator",
						lv_operator_1_0,
						"org.eclipse.xtext.common.Terminals.ID");
				}
			)
		)
		otherlv_2='{'
		{
			newLeafNode(otherlv_2, grammarAccess.getSetFunctionAccess().getLeftCurlyBracketKeyword_2());
		}
		(
			(
				{
					if ($current==null) {
						$current = createModelElement(grammarAccess.getSetFunctionRule());
					}
				}
				{
					newCompositeNode(grammarAccess.getSetFunctionAccess().getTypeDefinitionATypeDefinitionCrossReference_3_0());
				}
				ruleQualifiedName
				{
					afterParserOrEnumRuleCall();
				}
			)
		)
		(
			otherlv_4=','
			{
				newLeafNode(otherlv_4, grammarAccess.getSetFunctionAccess().getCommaKeyword_4_0());
			}
			(
				(
					lv_depth_5_0=RULE_INT
					{
						newLeafNode(lv_depth_5_0, grammarAccess.getSetFunctionAccess().getDepthINTTerminalRuleCall_4_1_0());
					}
					{
						if ($current==null) {
							$current = createModelElement(grammarAccess.getSetFunctionRule());
						}
						setWithLastConsumed(
							$current,
							"depth",
							lv_depth_5_0,
							"org.eclipse.xtext.common.Terminals.INT");
					}
				)
			)
		)?
		(
			otherlv_6=','
			{
				newLeafNode(otherlv_6, grammarAccess.getSetFunctionAccess().getCommaKeyword_5_0());
			}
			(
				(
					lv_filterName_7_0=RULE_ID
					{
						newLeafNode(lv_filterName_7_0, grammarAccess.getSetFunctionAccess().getFilterNameIDTerminalRuleCall_5_1_0());
					}
					{
						if ($current==null) {
							$current = createModelElement(grammarAccess.getSetFunctionRule());
						}
						setWithLastConsumed(
							$current,
							"filterName",
							lv_filterName_7_0,
							"org.eclipse.xtext.common.Terminals.ID");
					}
				)
			)
		)?
		otherlv_8='}'
		{
			newLeafNode(otherlv_8, grammarAccess.getSetFunctionAccess().getRightCurlyBracketKeyword_6());
		}
	)
;

// Entry rule entryRuleNumberLiteral
entryRuleNumberLiteral returns [EObject current=null]:
	{ newCompositeNode(grammarAccess.getNumberLiteralRule()); }
	iv_ruleNumberLiteral=ruleNumberLiteral
	{ $current=$iv_ruleNumberLiteral.current; }
	EOF;

// Rule NumberLiteral
ruleNumberLiteral returns [EObject current=null]
@init {
	enterRule();
}
@after {
	leaveRule();
}:
	(
		(
			{
				$current = forceCreateModelElement(
					grammarAccess.getNumberLiteralAccess().getNumberLiteralAction_0(),
					$current);
			}
		)
		(
			(
				{
					newCompositeNode(grammarAccess.getNumberLiteralAccess().getValueNumberLiteralStringParserRuleCall_1_0());
				}
				lv_value_1_0=ruleNumberLiteralString
				{
					if ($current==null) {
						$current = createModelElementForParent(grammarAccess.getNumberLiteralRule());
					}
					set(
						$current,
						"value",
						lv_value_1_0,
						"de.dlr.sc.virsat.model.calculation.EquationDSL.NumberLiteralString");
					afterParserOrEnumRuleCall();
				}
			)
		)
	)
;

// Entry rule entryRuleQualifiedName
entryRuleQualifiedName returns [String current=null]:
	{ newCompositeNode(grammarAccess.getQualifiedNameRule()); }
	iv_ruleQualifiedName=ruleQualifiedName
	{ $current=$iv_ruleQualifiedName.current.getText(); }
	EOF;

// Rule QualifiedName
ruleQualifiedName returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()]
@init {
	enterRule();
}
@after {
	leaveRule();
}:
	(
		this_ID_0=RULE_ID
		{
			$current.merge(this_ID_0);
		}
		{
			newLeafNode(this_ID_0, grammarAccess.getQualifiedNameAccess().getIDTerminalRuleCall_0());
		}
		(
			kw='.'
			{
				$current.merge(kw);
				newLeafNode(kw, grammarAccess.getQualifiedNameAccess().getFullStopKeyword_1_0());
			}
			this_ID_2=RULE_ID
			{
				$current.merge(this_ID_2);
			}
			{
				newLeafNode(this_ID_2, grammarAccess.getQualifiedNameAccess().getIDTerminalRuleCall_1_1());
			}
		)*
	)
;

// Entry rule entryRuleNumberLiteralString
entryRuleNumberLiteralString returns [String current=null]:
	{ newCompositeNode(grammarAccess.getNumberLiteralStringRule()); }
	iv_ruleNumberLiteralString=ruleNumberLiteralString
	{ $current=$iv_ruleNumberLiteralString.current.getText(); }
	EOF;

// Rule NumberLiteralString
ruleNumberLiteralString returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()]
@init {
	enterRule();
}
@after {
	leaveRule();
}:
	(
		(
			kw='-'
			{
				$current.merge(kw);
				newLeafNode(kw, grammarAccess.getNumberLiteralStringAccess().getHyphenMinusKeyword_0());
			}
		)?
		this_INT_1=RULE_INT
		{
			$current.merge(this_INT_1);
		}
		{
			newLeafNode(this_INT_1, grammarAccess.getNumberLiteralStringAccess().getINTTerminalRuleCall_1());
		}
		(
			kw='.'
			{
				$current.merge(kw);
				newLeafNode(kw, grammarAccess.getNumberLiteralStringAccess().getFullStopKeyword_2_0());
			}
			this_INT_3=RULE_INT
			{
				$current.merge(this_INT_3);
			}
			{
				newLeafNode(this_INT_3, grammarAccess.getNumberLiteralStringAccess().getINTTerminalRuleCall_2_1());
			}
		)?
	)
;

// Entry rule entryRuleValuePi
entryRuleValuePi returns [EObject current=null]:
	{ newCompositeNode(grammarAccess.getValuePiRule()); }
	iv_ruleValuePi=ruleValuePi
	{ $current=$iv_ruleValuePi.current; }
	EOF;

// Rule ValuePi
ruleValuePi returns [EObject current=null]
@init {
	enterRule();
}
@after {
	leaveRule();
}:
	(
		(
			{
				$current = forceCreateModelElement(
					grammarAccess.getValuePiAccess().getValuePiAction_0(),
					$current);
			}
		)
		otherlv_1='pi'
		{
			newLeafNode(otherlv_1, grammarAccess.getValuePiAccess().getPiKeyword_1());
		}
	)
;

// Entry rule entryRuleValueE
entryRuleValueE returns [EObject current=null]:
	{ newCompositeNode(grammarAccess.getValueERule()); }
	iv_ruleValueE=ruleValueE
	{ $current=$iv_ruleValueE.current; }
	EOF;

// Rule ValueE
ruleValueE returns [EObject current=null]
@init {
	enterRule();
}
@after {
	leaveRule();
}:
	(
		(
			{
				$current = forceCreateModelElement(
					grammarAccess.getValueEAccess().getValueEAction_0(),
					$current);
			}
		)
		otherlv_1='e'
		{
			newLeafNode(otherlv_1, grammarAccess.getValueEAccess().getEKeyword_1());
		}
	)
;

// Rule OperatorPlus
ruleOperatorPlus returns [Enumerator current=null]
@init {
	enterRule();
}
@after {
	leaveRule();
}:
	(
		enumLiteral_0='+'
		{
			$current = grammarAccess.getOperatorPlusAccess().getPLUSEnumLiteralDeclaration().getEnumLiteral().getInstance();
			newLeafNode(enumLiteral_0, grammarAccess.getOperatorPlusAccess().getPLUSEnumLiteralDeclaration());
		}
	)
;

// Rule OperatorMinus
ruleOperatorMinus returns [Enumerator current=null]
@init {
	enterRule();
}
@after {
	leaveRule();
}:
	(
		enumLiteral_0='-'
		{
			$current = grammarAccess.getOperatorMinusAccess().getMINUSEnumLiteralDeclaration().getEnumLiteral().getInstance();
			newLeafNode(enumLiteral_0, grammarAccess.getOperatorMinusAccess().getMINUSEnumLiteralDeclaration());
		}
	)
;

// Rule OperatorMultiply
ruleOperatorMultiply returns [Enumerator current=null]
@init {
	enterRule();
}
@after {
	leaveRule();
}:
	(
		enumLiteral_0='*'
		{
			$current = grammarAccess.getOperatorMultiplyAccess().getMULTIPLYEnumLiteralDeclaration().getEnumLiteral().getInstance();
			newLeafNode(enumLiteral_0, grammarAccess.getOperatorMultiplyAccess().getMULTIPLYEnumLiteralDeclaration());
		}
	)
;

// Rule OperatorDivide
ruleOperatorDivide returns [Enumerator current=null]
@init {
	enterRule();
}
@after {
	leaveRule();
}:
	(
		enumLiteral_0='/'
		{
			$current = grammarAccess.getOperatorDivideAccess().getDIVIDEEnumLiteralDeclaration().getEnumLiteral().getInstance();
			newLeafNode(enumLiteral_0, grammarAccess.getOperatorDivideAccess().getDIVIDEEnumLiteralDeclaration());
		}
	)
;

// Rule OperatorPower
ruleOperatorPower returns [Enumerator current=null]
@init {
	enterRule();
}
@after {
	leaveRule();
}:
	(
		enumLiteral_0='^'
		{
			$current = grammarAccess.getOperatorPowerAccess().getPOWEREnumLiteralDeclaration().getEnumLiteral().getInstance();
			newLeafNode(enumLiteral_0, grammarAccess.getOperatorPowerAccess().getPOWEREnumLiteralDeclaration());
		}
	)
;

// Rule OperatorCos
ruleOperatorCos returns [Enumerator current=null]
@init {
	enterRule();
}
@after {
	leaveRule();
}:
	(
		enumLiteral_0='cos'
		{
			$current = grammarAccess.getOperatorCosAccess().getCOSEnumLiteralDeclaration().getEnumLiteral().getInstance();
			newLeafNode(enumLiteral_0, grammarAccess.getOperatorCosAccess().getCOSEnumLiteralDeclaration());
		}
	)
;

// Rule OperatorSin
ruleOperatorSin returns [Enumerator current=null]
@init {
	enterRule();
}
@after {
	leaveRule();
}:
	(
		enumLiteral_0='sin'
		{
			$current = grammarAccess.getOperatorSinAccess().getSINEnumLiteralDeclaration().getEnumLiteral().getInstance();
			newLeafNode(enumLiteral_0, grammarAccess.getOperatorSinAccess().getSINEnumLiteralDeclaration());
		}
	)
;

// Rule OperatorTan
ruleOperatorTan returns [Enumerator current=null]
@init {
	enterRule();
}
@after {
	leaveRule();
}:
	(
		enumLiteral_0='tan'
		{
			$current = grammarAccess.getOperatorTanAccess().getTANEnumLiteralDeclaration().getEnumLiteral().getInstance();
			newLeafNode(enumLiteral_0, grammarAccess.getOperatorTanAccess().getTANEnumLiteralDeclaration());
		}
	)
;

// Rule OperatorAtan
ruleOperatorAtan returns [Enumerator current=null]
@init {
	enterRule();
}
@after {
	leaveRule();
}:
	(
		enumLiteral_0='atan'
		{
			$current = grammarAccess.getOperatorAtanAccess().getATANEnumLiteralDeclaration().getEnumLiteral().getInstance();
			newLeafNode(enumLiteral_0, grammarAccess.getOperatorAtanAccess().getATANEnumLiteralDeclaration());
		}
	)
;

// Rule OperatorAcos
ruleOperatorAcos returns [Enumerator current=null]
@init {
	enterRule();
}
@after {
	leaveRule();
}:
	(
		enumLiteral_0='acos'
		{
			$current = grammarAccess.getOperatorAcosAccess().getACOSEnumLiteralDeclaration().getEnumLiteral().getInstance();
			newLeafNode(enumLiteral_0, grammarAccess.getOperatorAcosAccess().getACOSEnumLiteralDeclaration());
		}
	)
;

// Rule OperatorAsin
ruleOperatorAsin returns [Enumerator current=null]
@init {
	enterRule();
}
@after {
	leaveRule();
}:
	(
		enumLiteral_0='asin'
		{
			$current = grammarAccess.getOperatorAsinAccess().getASINEnumLiteralDeclaration().getEnumLiteral().getInstance();
			newLeafNode(enumLiteral_0, grammarAccess.getOperatorAsinAccess().getASINEnumLiteralDeclaration());
		}
	)
;

// Rule OperatorSqrt
ruleOperatorSqrt returns [Enumerator current=null]
@init {
	enterRule();
}
@after {
	leaveRule();
}:
	(
		enumLiteral_0='sqrt'
		{
			$current = grammarAccess.getOperatorSqrtAccess().getSQRTEnumLiteralDeclaration().getEnumLiteral().getInstance();
			newLeafNode(enumLiteral_0, grammarAccess.getOperatorSqrtAccess().getSQRTEnumLiteralDeclaration());
		}
	)
;

// Rule OperatorLog
ruleOperatorLog returns [Enumerator current=null]
@init {
	enterRule();
}
@after {
	leaveRule();
}:
	(
		enumLiteral_0='log'
		{
			$current = grammarAccess.getOperatorLogAccess().getLOGEnumLiteralDeclaration().getEnumLiteral().getInstance();
			newLeafNode(enumLiteral_0, grammarAccess.getOperatorLogAccess().getLOGEnumLiteralDeclaration());
		}
	)
;

// Rule OperatorLn
ruleOperatorLn returns [Enumerator current=null]
@init {
	enterRule();
}
@after {
	leaveRule();
}:
	(
		enumLiteral_0='ln'
		{
			$current = grammarAccess.getOperatorLnAccess().getLNEnumLiteralDeclaration().getEnumLiteral().getInstance();
			newLeafNode(enumLiteral_0, grammarAccess.getOperatorLnAccess().getLNEnumLiteralDeclaration());
		}
	)
;

// Rule OperatorExp
ruleOperatorExp returns [Enumerator current=null]
@init {
	enterRule();
}
@after {
	leaveRule();
}:
	(
		enumLiteral_0='exp'
		{
			$current = grammarAccess.getOperatorExpAccess().getEXPEnumLiteralDeclaration().getEnumLiteral().getInstance();
			newLeafNode(enumLiteral_0, grammarAccess.getOperatorExpAccess().getEXPEnumLiteralDeclaration());
		}
	)
;

// Rule OperatorLd
ruleOperatorLd returns [Enumerator current=null]
@init {
	enterRule();
}
@after {
	leaveRule();
}:
	(
		enumLiteral_0='ld'
		{
			$current = grammarAccess.getOperatorLdAccess().getLDEnumLiteralDeclaration().getEnumLiteral().getInstance();
			newLeafNode(enumLiteral_0, grammarAccess.getOperatorLdAccess().getLDEnumLiteralDeclaration());
		}
	)
;

RULE_ID : '^'? ('a'..'z'|'A'..'Z'|'_') ('a'..'z'|'A'..'Z'|'_'|'0'..'9')*;

RULE_INT : ('0'..'9')+;

RULE_STRING : ('"' ('\\' .|~(('\\'|'"')))* '"'|'\'' ('\\' .|~(('\\'|'\'')))* '\'');

RULE_ML_COMMENT : '/*' ( options {greedy=false;} : . )*'*/';

RULE_SL_COMMENT : '//' ~(('\n'|'\r'))* ('\r'? '\n')?;

RULE_WS : (' '|'\t'|'\r'|'\n')+;

RULE_ANY_OTHER : .;
