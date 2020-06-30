/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.calculation.services;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import java.util.List;
import org.eclipse.xtext.Action;
import org.eclipse.xtext.Alternatives;
import org.eclipse.xtext.Assignment;
import org.eclipse.xtext.CrossReference;
import org.eclipse.xtext.EnumLiteralDeclaration;
import org.eclipse.xtext.EnumRule;
import org.eclipse.xtext.Grammar;
import org.eclipse.xtext.GrammarUtil;
import org.eclipse.xtext.Group;
import org.eclipse.xtext.Keyword;
import org.eclipse.xtext.ParserRule;
import org.eclipse.xtext.RuleCall;
import org.eclipse.xtext.TerminalRule;
import org.eclipse.xtext.common.services.TerminalsGrammarAccess;
import org.eclipse.xtext.service.AbstractElementFinder;
import org.eclipse.xtext.service.GrammarProvider;

@Singleton
public class EquationDSLGrammarAccess extends AbstractElementFinder.AbstractGrammarElementFinder {
	
	public class EquationSectionElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "de.dlr.sc.virsat.model.calculation.EquationDSL.EquationSection");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final Assignment cImportsAssignment_0 = (Assignment)cGroup.eContents().get(0);
		private final RuleCall cImportsImportParserRuleCall_0_0 = (RuleCall)cImportsAssignment_0.eContents().get(0);
		private final Assignment cEquationsAssignment_1 = (Assignment)cGroup.eContents().get(1);
		private final RuleCall cEquationsEquationParserRuleCall_1_0 = (RuleCall)cEquationsAssignment_1.eContents().get(0);
		
		//EquationSection Calc::EquationSection:
		//	imports+=Import*
		//	equations+=Equation*;
		@Override public ParserRule getRule() { return rule; }
		
		//imports+=Import* equations+=Equation*
		public Group getGroup() { return cGroup; }
		
		//imports+=Import*
		public Assignment getImportsAssignment_0() { return cImportsAssignment_0; }
		
		//Import
		public RuleCall getImportsImportParserRuleCall_0_0() { return cImportsImportParserRuleCall_0_0; }
		
		//equations+=Equation*
		public Assignment getEquationsAssignment_1() { return cEquationsAssignment_1; }
		
		//Equation
		public RuleCall getEquationsEquationParserRuleCall_1_0() { return cEquationsEquationParserRuleCall_1_0; }
	}
	public class ImportElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "de.dlr.sc.virsat.model.calculation.EquationDSL.Import");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final Keyword cImportKeyword_0 = (Keyword)cGroup.eContents().get(0);
		private final Assignment cImportedNamespaceAssignment_1 = (Assignment)cGroup.eContents().get(1);
		private final CrossReference cImportedNamespaceIInstanceCrossReference_1_0 = (CrossReference)cImportedNamespaceAssignment_1.eContents().get(0);
		private final RuleCall cImportedNamespaceIInstanceQualifiedNameParserRuleCall_1_0_1 = (RuleCall)cImportedNamespaceIInstanceCrossReference_1_0.eContents().get(1);
		private final Keyword cSemicolonKeyword_2 = (Keyword)cGroup.eContents().get(2);
		
		//Import Calc::Import:
		//	'Import:' importedNamespace=[General::IInstance|QualifiedName] ';';
		@Override public ParserRule getRule() { return rule; }
		
		//'Import:' importedNamespace=[General::IInstance|QualifiedName] ';'
		public Group getGroup() { return cGroup; }
		
		//'Import:'
		public Keyword getImportKeyword_0() { return cImportKeyword_0; }
		
		//importedNamespace=[General::IInstance|QualifiedName]
		public Assignment getImportedNamespaceAssignment_1() { return cImportedNamespaceAssignment_1; }
		
		//[General::IInstance|QualifiedName]
		public CrossReference getImportedNamespaceIInstanceCrossReference_1_0() { return cImportedNamespaceIInstanceCrossReference_1_0; }
		
		//QualifiedName
		public RuleCall getImportedNamespaceIInstanceQualifiedNameParserRuleCall_1_0_1() { return cImportedNamespaceIInstanceQualifiedNameParserRuleCall_1_0_1; }
		
		//';'
		public Keyword getSemicolonKeyword_2() { return cSemicolonKeyword_2; }
	}
	public class EquationElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "de.dlr.sc.virsat.model.calculation.EquationDSL.Equation");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final Assignment cResultAssignment_0 = (Assignment)cGroup.eContents().get(0);
		private final RuleCall cResultEquationResultParserRuleCall_0_0 = (RuleCall)cResultAssignment_0.eContents().get(0);
		private final Keyword cEqualsSignKeyword_1 = (Keyword)cGroup.eContents().get(1);
		private final Assignment cExpressionAssignment_2 = (Assignment)cGroup.eContents().get(2);
		private final RuleCall cExpressionAdditionAndSubtractionParserRuleCall_2_0 = (RuleCall)cExpressionAssignment_2.eContents().get(0);
		private final Keyword cSemicolonKeyword_3 = (Keyword)cGroup.eContents().get(3);
		
		//Equation Calc::Equation:
		//	result=EquationResult '=' expression=AdditionAndSubtraction ';';
		@Override public ParserRule getRule() { return rule; }
		
		//result=EquationResult '=' expression=AdditionAndSubtraction ';'
		public Group getGroup() { return cGroup; }
		
		//result=EquationResult
		public Assignment getResultAssignment_0() { return cResultAssignment_0; }
		
		//EquationResult
		public RuleCall getResultEquationResultParserRuleCall_0_0() { return cResultEquationResultParserRuleCall_0_0; }
		
		//'='
		public Keyword getEqualsSignKeyword_1() { return cEqualsSignKeyword_1; }
		
		//expression=AdditionAndSubtraction
		public Assignment getExpressionAssignment_2() { return cExpressionAssignment_2; }
		
		//AdditionAndSubtraction
		public RuleCall getExpressionAdditionAndSubtractionParserRuleCall_2_0() { return cExpressionAdditionAndSubtractionParserRuleCall_2_0; }
		
		//';'
		public Keyword getSemicolonKeyword_3() { return cSemicolonKeyword_3; }
	}
	public class EquationResultElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "de.dlr.sc.virsat.model.calculation.EquationDSL.EquationResult");
		private final Alternatives cAlternatives = (Alternatives)rule.eContents().get(1);
		private final RuleCall cTypeInstanceResultParserRuleCall_0 = (RuleCall)cAlternatives.eContents().get(0);
		private final RuleCall cEquationIntermediateResultParserRuleCall_1 = (RuleCall)cAlternatives.eContents().get(1);
		
		//EquationResult Calc::IEquationResult:
		//	TypeInstanceResult | EquationIntermediateResult;
		@Override public ParserRule getRule() { return rule; }
		
		//TypeInstanceResult | EquationIntermediateResult
		public Alternatives getAlternatives() { return cAlternatives; }
		
		//TypeInstanceResult
		public RuleCall getTypeInstanceResultParserRuleCall_0() { return cTypeInstanceResultParserRuleCall_0; }
		
		//EquationIntermediateResult
		public RuleCall getEquationIntermediateResultParserRuleCall_1() { return cEquationIntermediateResultParserRuleCall_1; }
	}
	public class TypeInstanceResultElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "de.dlr.sc.virsat.model.calculation.EquationDSL.TypeInstanceResult");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final Action cTypeInstanceResultAction_0 = (Action)cGroup.eContents().get(0);
		private final Keyword cRefKeyword_1 = (Keyword)cGroup.eContents().get(1);
		private final Assignment cReferenceAssignment_2 = (Assignment)cGroup.eContents().get(2);
		private final CrossReference cReferenceATypeInstanceCrossReference_2_0 = (CrossReference)cReferenceAssignment_2.eContents().get(0);
		private final RuleCall cReferenceATypeInstanceQualifiedNameParserRuleCall_2_0_1 = (RuleCall)cReferenceATypeInstanceCrossReference_2_0.eContents().get(1);
		
		//TypeInstanceResult Calc::IEquationResult:
		//	{Calc::TypeInstanceResult}
		//	'Ref:' reference=[Categories::ATypeInstance|QualifiedName];
		@Override public ParserRule getRule() { return rule; }
		
		//{Calc::TypeInstanceResult} 'Ref:' reference=[Categories::ATypeInstance|QualifiedName]
		public Group getGroup() { return cGroup; }
		
		//{Calc::TypeInstanceResult}
		public Action getTypeInstanceResultAction_0() { return cTypeInstanceResultAction_0; }
		
		//'Ref:'
		public Keyword getRefKeyword_1() { return cRefKeyword_1; }
		
		//reference=[Categories::ATypeInstance|QualifiedName]
		public Assignment getReferenceAssignment_2() { return cReferenceAssignment_2; }
		
		//[Categories::ATypeInstance|QualifiedName]
		public CrossReference getReferenceATypeInstanceCrossReference_2_0() { return cReferenceATypeInstanceCrossReference_2_0; }
		
		//QualifiedName
		public RuleCall getReferenceATypeInstanceQualifiedNameParserRuleCall_2_0_1() { return cReferenceATypeInstanceQualifiedNameParserRuleCall_2_0_1; }
	}
	public class EquationIntermediateResultElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "de.dlr.sc.virsat.model.calculation.EquationDSL.EquationIntermediateResult");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final Action cEquationIntermediateResultAction_0 = (Action)cGroup.eContents().get(0);
		private final Keyword cCalcKeyword_1 = (Keyword)cGroup.eContents().get(1);
		private final Assignment cNameAssignment_2 = (Assignment)cGroup.eContents().get(2);
		private final RuleCall cNameIDTerminalRuleCall_2_0 = (RuleCall)cNameAssignment_2.eContents().get(0);
		
		//EquationIntermediateResult Calc::IEquationResult:
		//	{Calc::EquationIntermediateResult}
		//	'Calc:' name=ID;
		@Override public ParserRule getRule() { return rule; }
		
		//{Calc::EquationIntermediateResult} 'Calc:' name=ID
		public Group getGroup() { return cGroup; }
		
		//{Calc::EquationIntermediateResult}
		public Action getEquationIntermediateResultAction_0() { return cEquationIntermediateResultAction_0; }
		
		//'Calc:'
		public Keyword getCalcKeyword_1() { return cCalcKeyword_1; }
		
		//name=ID
		public Assignment getNameAssignment_2() { return cNameAssignment_2; }
		
		//ID
		public RuleCall getNameIDTerminalRuleCall_2_0() { return cNameIDTerminalRuleCall_2_0; }
	}
	public class AdditionAndSubtractionElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "de.dlr.sc.virsat.model.calculation.EquationDSL.AdditionAndSubtraction");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final RuleCall cMultiplicationAndDivisionParserRuleCall_0 = (RuleCall)cGroup.eContents().get(0);
		private final Group cGroup_1 = (Group)cGroup.eContents().get(1);
		private final Action cAdditionAndSubtractionLeftAction_1_0 = (Action)cGroup_1.eContents().get(0);
		private final Assignment cOperatorAssignment_1_1 = (Assignment)cGroup_1.eContents().get(1);
		private final Alternatives cOperatorAlternatives_1_1_0 = (Alternatives)cOperatorAssignment_1_1.eContents().get(0);
		private final RuleCall cOperatorOperatorPlusEnumRuleCall_1_1_0_0 = (RuleCall)cOperatorAlternatives_1_1_0.eContents().get(0);
		private final RuleCall cOperatorOperatorMinusEnumRuleCall_1_1_0_1 = (RuleCall)cOperatorAlternatives_1_1_0.eContents().get(1);
		private final Assignment cRightAssignment_1_2 = (Assignment)cGroup_1.eContents().get(2);
		private final RuleCall cRightMultiplicationAndDivisionParserRuleCall_1_2_0 = (RuleCall)cRightAssignment_1_2.eContents().get(0);
		
		//AdditionAndSubtraction Calc::AExpression:
		//	MultiplicationAndDivision ({Calc::AdditionAndSubtraction.left=current} operator=(OperatorPlus | OperatorMinus)
		//	right=MultiplicationAndDivision)*;
		@Override public ParserRule getRule() { return rule; }
		
		//MultiplicationAndDivision ({Calc::AdditionAndSubtraction.left=current} operator=(OperatorPlus | OperatorMinus)
		//right=MultiplicationAndDivision)*
		public Group getGroup() { return cGroup; }
		
		//MultiplicationAndDivision
		public RuleCall getMultiplicationAndDivisionParserRuleCall_0() { return cMultiplicationAndDivisionParserRuleCall_0; }
		
		//({Calc::AdditionAndSubtraction.left=current} operator=(OperatorPlus | OperatorMinus) right=MultiplicationAndDivision)*
		public Group getGroup_1() { return cGroup_1; }
		
		//{Calc::AdditionAndSubtraction.left=current}
		public Action getAdditionAndSubtractionLeftAction_1_0() { return cAdditionAndSubtractionLeftAction_1_0; }
		
		//operator=(OperatorPlus | OperatorMinus)
		public Assignment getOperatorAssignment_1_1() { return cOperatorAssignment_1_1; }
		
		//(OperatorPlus | OperatorMinus)
		public Alternatives getOperatorAlternatives_1_1_0() { return cOperatorAlternatives_1_1_0; }
		
		//OperatorPlus
		public RuleCall getOperatorOperatorPlusEnumRuleCall_1_1_0_0() { return cOperatorOperatorPlusEnumRuleCall_1_1_0_0; }
		
		//OperatorMinus
		public RuleCall getOperatorOperatorMinusEnumRuleCall_1_1_0_1() { return cOperatorOperatorMinusEnumRuleCall_1_1_0_1; }
		
		//right=MultiplicationAndDivision
		public Assignment getRightAssignment_1_2() { return cRightAssignment_1_2; }
		
		//MultiplicationAndDivision
		public RuleCall getRightMultiplicationAndDivisionParserRuleCall_1_2_0() { return cRightMultiplicationAndDivisionParserRuleCall_1_2_0; }
	}
	public class MultiplicationAndDivisionElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "de.dlr.sc.virsat.model.calculation.EquationDSL.MultiplicationAndDivision");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final RuleCall cPowerFunctionParserRuleCall_0 = (RuleCall)cGroup.eContents().get(0);
		private final Group cGroup_1 = (Group)cGroup.eContents().get(1);
		private final Action cMultiplicationAndDivisionLeftAction_1_0 = (Action)cGroup_1.eContents().get(0);
		private final Assignment cOperatorAssignment_1_1 = (Assignment)cGroup_1.eContents().get(1);
		private final Alternatives cOperatorAlternatives_1_1_0 = (Alternatives)cOperatorAssignment_1_1.eContents().get(0);
		private final RuleCall cOperatorOperatorMultiplyEnumRuleCall_1_1_0_0 = (RuleCall)cOperatorAlternatives_1_1_0.eContents().get(0);
		private final RuleCall cOperatorOperatorDivideEnumRuleCall_1_1_0_1 = (RuleCall)cOperatorAlternatives_1_1_0.eContents().get(1);
		private final Assignment cRightAssignment_1_2 = (Assignment)cGroup_1.eContents().get(2);
		private final RuleCall cRightPowerFunctionParserRuleCall_1_2_0 = (RuleCall)cRightAssignment_1_2.eContents().get(0);
		
		//MultiplicationAndDivision Calc::AExpression:
		//	PowerFunction ({Calc::MultiplicationAndDivision.left=current} operator=(OperatorMultiply | OperatorDivide)
		//	right=PowerFunction)*;
		@Override public ParserRule getRule() { return rule; }
		
		//PowerFunction ({Calc::MultiplicationAndDivision.left=current} operator=(OperatorMultiply | OperatorDivide)
		//right=PowerFunction)*
		public Group getGroup() { return cGroup; }
		
		//PowerFunction
		public RuleCall getPowerFunctionParserRuleCall_0() { return cPowerFunctionParserRuleCall_0; }
		
		//({Calc::MultiplicationAndDivision.left=current} operator=(OperatorMultiply | OperatorDivide) right=PowerFunction)*
		public Group getGroup_1() { return cGroup_1; }
		
		//{Calc::MultiplicationAndDivision.left=current}
		public Action getMultiplicationAndDivisionLeftAction_1_0() { return cMultiplicationAndDivisionLeftAction_1_0; }
		
		//operator=(OperatorMultiply | OperatorDivide)
		public Assignment getOperatorAssignment_1_1() { return cOperatorAssignment_1_1; }
		
		//(OperatorMultiply | OperatorDivide)
		public Alternatives getOperatorAlternatives_1_1_0() { return cOperatorAlternatives_1_1_0; }
		
		//OperatorMultiply
		public RuleCall getOperatorOperatorMultiplyEnumRuleCall_1_1_0_0() { return cOperatorOperatorMultiplyEnumRuleCall_1_1_0_0; }
		
		//OperatorDivide
		public RuleCall getOperatorOperatorDivideEnumRuleCall_1_1_0_1() { return cOperatorOperatorDivideEnumRuleCall_1_1_0_1; }
		
		//right=PowerFunction
		public Assignment getRightAssignment_1_2() { return cRightAssignment_1_2; }
		
		//PowerFunction
		public RuleCall getRightPowerFunctionParserRuleCall_1_2_0() { return cRightPowerFunctionParserRuleCall_1_2_0; }
	}
	public class PowerFunctionElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "de.dlr.sc.virsat.model.calculation.EquationDSL.PowerFunction");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final RuleCall cAExpressionParserRuleCall_0 = (RuleCall)cGroup.eContents().get(0);
		private final Group cGroup_1 = (Group)cGroup.eContents().get(1);
		private final Action cPowerFunctionLeftAction_1_0 = (Action)cGroup_1.eContents().get(0);
		private final Assignment cOperatorAssignment_1_1 = (Assignment)cGroup_1.eContents().get(1);
		private final RuleCall cOperatorOperatorPowerEnumRuleCall_1_1_0 = (RuleCall)cOperatorAssignment_1_1.eContents().get(0);
		private final Assignment cRightAssignment_1_2 = (Assignment)cGroup_1.eContents().get(2);
		private final RuleCall cRightAExpressionParserRuleCall_1_2_0 = (RuleCall)cRightAssignment_1_2.eContents().get(0);
		
		//PowerFunction Calc::AExpression:
		//	AExpression ({Calc::PowerFunction.left=current} operator=OperatorPower right=AExpression)*;
		@Override public ParserRule getRule() { return rule; }
		
		//AExpression ({Calc::PowerFunction.left=current} operator=OperatorPower right=AExpression)*
		public Group getGroup() { return cGroup; }
		
		//AExpression
		public RuleCall getAExpressionParserRuleCall_0() { return cAExpressionParserRuleCall_0; }
		
		//({Calc::PowerFunction.left=current} operator=OperatorPower right=AExpression)*
		public Group getGroup_1() { return cGroup_1; }
		
		//{Calc::PowerFunction.left=current}
		public Action getPowerFunctionLeftAction_1_0() { return cPowerFunctionLeftAction_1_0; }
		
		//operator=OperatorPower
		public Assignment getOperatorAssignment_1_1() { return cOperatorAssignment_1_1; }
		
		//OperatorPower
		public RuleCall getOperatorOperatorPowerEnumRuleCall_1_1_0() { return cOperatorOperatorPowerEnumRuleCall_1_1_0; }
		
		//right=AExpression
		public Assignment getRightAssignment_1_2() { return cRightAssignment_1_2; }
		
		//AExpression
		public RuleCall getRightAExpressionParserRuleCall_1_2_0() { return cRightAExpressionParserRuleCall_1_2_0; }
	}
	public class ParenthesisElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "de.dlr.sc.virsat.model.calculation.EquationDSL.Parenthesis");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final Action cParenthesisAction_0 = (Action)cGroup.eContents().get(0);
		private final Assignment cOperatorAssignment_1 = (Assignment)cGroup.eContents().get(1);
		private final RuleCall cOperatorOperatorMinusEnumRuleCall_1_0 = (RuleCall)cOperatorAssignment_1.eContents().get(0);
		private final Keyword cLeftParenthesisKeyword_2 = (Keyword)cGroup.eContents().get(2);
		private final Assignment cRightAssignment_3 = (Assignment)cGroup.eContents().get(3);
		private final RuleCall cRightAdditionAndSubtractionParserRuleCall_3_0 = (RuleCall)cRightAssignment_3.eContents().get(0);
		private final Keyword cRightParenthesisKeyword_4 = (Keyword)cGroup.eContents().get(4);
		
		//Parenthesis Calc::AOpRightExpression:
		//	{Calc::Parenthesis} operator=OperatorMinus? '(' right=AdditionAndSubtraction ')';
		@Override public ParserRule getRule() { return rule; }
		
		//{Calc::Parenthesis} operator=OperatorMinus? '(' right=AdditionAndSubtraction ')'
		public Group getGroup() { return cGroup; }
		
		//{Calc::Parenthesis}
		public Action getParenthesisAction_0() { return cParenthesisAction_0; }
		
		//operator=OperatorMinus?
		public Assignment getOperatorAssignment_1() { return cOperatorAssignment_1; }
		
		//OperatorMinus
		public RuleCall getOperatorOperatorMinusEnumRuleCall_1_0() { return cOperatorOperatorMinusEnumRuleCall_1_0; }
		
		//'('
		public Keyword getLeftParenthesisKeyword_2() { return cLeftParenthesisKeyword_2; }
		
		//right=AdditionAndSubtraction
		public Assignment getRightAssignment_3() { return cRightAssignment_3; }
		
		//AdditionAndSubtraction
		public RuleCall getRightAdditionAndSubtractionParserRuleCall_3_0() { return cRightAdditionAndSubtractionParserRuleCall_3_0; }
		
		//')'
		public Keyword getRightParenthesisKeyword_4() { return cRightParenthesisKeyword_4; }
	}
	public class AExpressionElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "de.dlr.sc.virsat.model.calculation.EquationDSL.AExpression");
		private final Alternatives cAlternatives = (Alternatives)rule.eContents().get(1);
		private final RuleCall cALiteralParserRuleCall_0 = (RuleCall)cAlternatives.eContents().get(0);
		private final RuleCall cReferencedInputParserRuleCall_1 = (RuleCall)cAlternatives.eContents().get(1);
		private final RuleCall cParenthesisParserRuleCall_2 = (RuleCall)cAlternatives.eContents().get(2);
		private final RuleCall cFunctionParserRuleCall_3 = (RuleCall)cAlternatives.eContents().get(3);
		private final RuleCall cAAdvancedFunctionParserRuleCall_4 = (RuleCall)cAlternatives.eContents().get(4);
		
		//AExpression Calc::AExpression:
		//	ALiteral | ReferencedInput | Parenthesis | Function | AAdvancedFunction;
		@Override public ParserRule getRule() { return rule; }
		
		//ALiteral | ReferencedInput | Parenthesis | Function | AAdvancedFunction
		public Alternatives getAlternatives() { return cAlternatives; }
		
		//ALiteral
		public RuleCall getALiteralParserRuleCall_0() { return cALiteralParserRuleCall_0; }
		
		//ReferencedInput
		public RuleCall getReferencedInputParserRuleCall_1() { return cReferencedInputParserRuleCall_1; }
		
		//Parenthesis
		public RuleCall getParenthesisParserRuleCall_2() { return cParenthesisParserRuleCall_2; }
		
		//Function
		public RuleCall getFunctionParserRuleCall_3() { return cFunctionParserRuleCall_3; }
		
		//AAdvancedFunction
		public RuleCall getAAdvancedFunctionParserRuleCall_4() { return cAAdvancedFunctionParserRuleCall_4; }
	}
	public class ReferencedInputElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "de.dlr.sc.virsat.model.calculation.EquationDSL.ReferencedInput");
		private final Assignment cReferenceAssignment = (Assignment)rule.eContents().get(1);
		private final CrossReference cReferenceIEquationInputCrossReference_0 = (CrossReference)cReferenceAssignment.eContents().get(0);
		private final RuleCall cReferenceIEquationInputQualifiedNameParserRuleCall_0_1 = (RuleCall)cReferenceIEquationInputCrossReference_0.eContents().get(1);
		
		//ReferencedInput Calc::ReferencedInput:
		//	reference=[Calc::IEquationInput|QualifiedName];
		@Override public ParserRule getRule() { return rule; }
		
		//reference=[Calc::IEquationInput|QualifiedName]
		public Assignment getReferenceAssignment() { return cReferenceAssignment; }
		
		//[Calc::IEquationInput|QualifiedName]
		public CrossReference getReferenceIEquationInputCrossReference_0() { return cReferenceIEquationInputCrossReference_0; }
		
		//QualifiedName
		public RuleCall getReferenceIEquationInputQualifiedNameParserRuleCall_0_1() { return cReferenceIEquationInputQualifiedNameParserRuleCall_0_1; }
	}
	public class ALiteralElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "de.dlr.sc.virsat.model.calculation.EquationDSL.ALiteral");
		private final Alternatives cAlternatives = (Alternatives)rule.eContents().get(1);
		private final RuleCall cNumberLiteralParserRuleCall_0 = (RuleCall)cAlternatives.eContents().get(0);
		private final RuleCall cValueEParserRuleCall_1 = (RuleCall)cAlternatives.eContents().get(1);
		private final RuleCall cValuePiParserRuleCall_2 = (RuleCall)cAlternatives.eContents().get(2);
		
		//ALiteral Calc::ALiteral:
		//	NumberLiteral | ValueE | ValuePi;
		@Override public ParserRule getRule() { return rule; }
		
		//NumberLiteral | ValueE | ValuePi
		public Alternatives getAlternatives() { return cAlternatives; }
		
		//NumberLiteral
		public RuleCall getNumberLiteralParserRuleCall_0() { return cNumberLiteralParserRuleCall_0; }
		
		//ValueE
		public RuleCall getValueEParserRuleCall_1() { return cValueEParserRuleCall_1; }
		
		//ValuePi
		public RuleCall getValuePiParserRuleCall_2() { return cValuePiParserRuleCall_2; }
	}
	public class FunctionElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "de.dlr.sc.virsat.model.calculation.EquationDSL.Function");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final Action cFunctionAction_0 = (Action)cGroup.eContents().get(0);
		private final Assignment cOperatorAssignment_1 = (Assignment)cGroup.eContents().get(1);
		private final Alternatives cOperatorAlternatives_1_0 = (Alternatives)cOperatorAssignment_1.eContents().get(0);
		private final RuleCall cOperatorOperatorCosEnumRuleCall_1_0_0 = (RuleCall)cOperatorAlternatives_1_0.eContents().get(0);
		private final RuleCall cOperatorOperatorSinEnumRuleCall_1_0_1 = (RuleCall)cOperatorAlternatives_1_0.eContents().get(1);
		private final RuleCall cOperatorOperatorTanEnumRuleCall_1_0_2 = (RuleCall)cOperatorAlternatives_1_0.eContents().get(2);
		private final RuleCall cOperatorOperatorAtanEnumRuleCall_1_0_3 = (RuleCall)cOperatorAlternatives_1_0.eContents().get(3);
		private final RuleCall cOperatorOperatorAcosEnumRuleCall_1_0_4 = (RuleCall)cOperatorAlternatives_1_0.eContents().get(4);
		private final RuleCall cOperatorOperatorAsinEnumRuleCall_1_0_5 = (RuleCall)cOperatorAlternatives_1_0.eContents().get(5);
		private final RuleCall cOperatorOperatorSqrtEnumRuleCall_1_0_6 = (RuleCall)cOperatorAlternatives_1_0.eContents().get(6);
		private final RuleCall cOperatorOperatorLogEnumRuleCall_1_0_7 = (RuleCall)cOperatorAlternatives_1_0.eContents().get(7);
		private final RuleCall cOperatorOperatorLnEnumRuleCall_1_0_8 = (RuleCall)cOperatorAlternatives_1_0.eContents().get(8);
		private final RuleCall cOperatorOperatorLdEnumRuleCall_1_0_9 = (RuleCall)cOperatorAlternatives_1_0.eContents().get(9);
		private final RuleCall cOperatorOperatorExpEnumRuleCall_1_0_10 = (RuleCall)cOperatorAlternatives_1_0.eContents().get(10);
		private final Keyword cLeftParenthesisKeyword_2 = (Keyword)cGroup.eContents().get(2);
		private final Assignment cRightAssignment_3 = (Assignment)cGroup.eContents().get(3);
		private final RuleCall cRightAdditionAndSubtractionParserRuleCall_3_0 = (RuleCall)cRightAssignment_3.eContents().get(0);
		private final Keyword cRightParenthesisKeyword_4 = (Keyword)cGroup.eContents().get(4);
		
		//Function Calc::AExpression:
		//	{Calc::Function} operator=(OperatorCos | OperatorSin | OperatorTan | OperatorAtan | OperatorAcos | OperatorAsin |
		//	OperatorSqrt | OperatorLog | OperatorLn | OperatorLd | OperatorExp) '(' right=AdditionAndSubtraction ')';
		@Override public ParserRule getRule() { return rule; }
		
		//{Calc::Function} operator=(OperatorCos | OperatorSin | OperatorTan | OperatorAtan | OperatorAcos | OperatorAsin |
		//OperatorSqrt | OperatorLog | OperatorLn | OperatorLd | OperatorExp) '(' right=AdditionAndSubtraction ')'
		public Group getGroup() { return cGroup; }
		
		//{Calc::Function}
		public Action getFunctionAction_0() { return cFunctionAction_0; }
		
		//operator=(OperatorCos | OperatorSin | OperatorTan | OperatorAtan | OperatorAcos | OperatorAsin | OperatorSqrt |
		//OperatorLog | OperatorLn | OperatorLd | OperatorExp)
		public Assignment getOperatorAssignment_1() { return cOperatorAssignment_1; }
		
		//(OperatorCos | OperatorSin | OperatorTan | OperatorAtan | OperatorAcos | OperatorAsin | OperatorSqrt | OperatorLog |
		//OperatorLn | OperatorLd | OperatorExp)
		public Alternatives getOperatorAlternatives_1_0() { return cOperatorAlternatives_1_0; }
		
		//OperatorCos
		public RuleCall getOperatorOperatorCosEnumRuleCall_1_0_0() { return cOperatorOperatorCosEnumRuleCall_1_0_0; }
		
		//OperatorSin
		public RuleCall getOperatorOperatorSinEnumRuleCall_1_0_1() { return cOperatorOperatorSinEnumRuleCall_1_0_1; }
		
		//OperatorTan
		public RuleCall getOperatorOperatorTanEnumRuleCall_1_0_2() { return cOperatorOperatorTanEnumRuleCall_1_0_2; }
		
		//OperatorAtan
		public RuleCall getOperatorOperatorAtanEnumRuleCall_1_0_3() { return cOperatorOperatorAtanEnumRuleCall_1_0_3; }
		
		//OperatorAcos
		public RuleCall getOperatorOperatorAcosEnumRuleCall_1_0_4() { return cOperatorOperatorAcosEnumRuleCall_1_0_4; }
		
		//OperatorAsin
		public RuleCall getOperatorOperatorAsinEnumRuleCall_1_0_5() { return cOperatorOperatorAsinEnumRuleCall_1_0_5; }
		
		//OperatorSqrt
		public RuleCall getOperatorOperatorSqrtEnumRuleCall_1_0_6() { return cOperatorOperatorSqrtEnumRuleCall_1_0_6; }
		
		//OperatorLog
		public RuleCall getOperatorOperatorLogEnumRuleCall_1_0_7() { return cOperatorOperatorLogEnumRuleCall_1_0_7; }
		
		//OperatorLn
		public RuleCall getOperatorOperatorLnEnumRuleCall_1_0_8() { return cOperatorOperatorLnEnumRuleCall_1_0_8; }
		
		//OperatorLd
		public RuleCall getOperatorOperatorLdEnumRuleCall_1_0_9() { return cOperatorOperatorLdEnumRuleCall_1_0_9; }
		
		//OperatorExp
		public RuleCall getOperatorOperatorExpEnumRuleCall_1_0_10() { return cOperatorOperatorExpEnumRuleCall_1_0_10; }
		
		//'('
		public Keyword getLeftParenthesisKeyword_2() { return cLeftParenthesisKeyword_2; }
		
		//right=AdditionAndSubtraction
		public Assignment getRightAssignment_3() { return cRightAssignment_3; }
		
		//AdditionAndSubtraction
		public RuleCall getRightAdditionAndSubtractionParserRuleCall_3_0() { return cRightAdditionAndSubtractionParserRuleCall_3_0; }
		
		//')'
		public Keyword getRightParenthesisKeyword_4() { return cRightParenthesisKeyword_4; }
	}
	public class AAdvancedFunctionElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "de.dlr.sc.virsat.model.calculation.EquationDSL.AAdvancedFunction");
		private final Alternatives cAlternatives = (Alternatives)rule.eContents().get(1);
		private final RuleCall cAdvancedFunctionParserRuleCall_0 = (RuleCall)cAlternatives.eContents().get(0);
		private final RuleCall cSetFunctionParserRuleCall_1 = (RuleCall)cAlternatives.eContents().get(1);
		
		//AAdvancedFunction Calc::AExpression:
		//	AdvancedFunction | SetFunction;
		@Override public ParserRule getRule() { return rule; }
		
		//AdvancedFunction | SetFunction
		public Alternatives getAlternatives() { return cAlternatives; }
		
		//AdvancedFunction
		public RuleCall getAdvancedFunctionParserRuleCall_0() { return cAdvancedFunctionParserRuleCall_0; }
		
		//SetFunction
		public RuleCall getSetFunctionParserRuleCall_1() { return cSetFunctionParserRuleCall_1; }
	}
	public class AdvancedFunctionElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "de.dlr.sc.virsat.model.calculation.EquationDSL.AdvancedFunction");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final Action cAdvancedFunctionAction_0 = (Action)cGroup.eContents().get(0);
		private final Assignment cOperatorAssignment_1 = (Assignment)cGroup.eContents().get(1);
		private final RuleCall cOperatorIDTerminalRuleCall_1_0 = (RuleCall)cOperatorAssignment_1.eContents().get(0);
		private final Keyword cLeftParenthesisKeyword_2 = (Keyword)cGroup.eContents().get(2);
		private final Assignment cInputsAssignment_3 = (Assignment)cGroup.eContents().get(3);
		private final RuleCall cInputsAdditionAndSubtractionParserRuleCall_3_0 = (RuleCall)cInputsAssignment_3.eContents().get(0);
		private final Group cGroup_4 = (Group)cGroup.eContents().get(4);
		private final Keyword cCommaKeyword_4_0 = (Keyword)cGroup_4.eContents().get(0);
		private final Assignment cInputsAssignment_4_1 = (Assignment)cGroup_4.eContents().get(1);
		private final RuleCall cInputsAdditionAndSubtractionParserRuleCall_4_1_0 = (RuleCall)cInputsAssignment_4_1.eContents().get(0);
		private final Keyword cRightParenthesisKeyword_5 = (Keyword)cGroup.eContents().get(5);
		
		//AdvancedFunction Calc::AExpression:
		//	{Calc::AdvancedFunction} operator=ID '(' inputs+=AdditionAndSubtraction ("," inputs+=AdditionAndSubtraction)* ')';
		@Override public ParserRule getRule() { return rule; }
		
		//{Calc::AdvancedFunction} operator=ID '(' inputs+=AdditionAndSubtraction ("," inputs+=AdditionAndSubtraction)* ')'
		public Group getGroup() { return cGroup; }
		
		//{Calc::AdvancedFunction}
		public Action getAdvancedFunctionAction_0() { return cAdvancedFunctionAction_0; }
		
		//operator=ID
		public Assignment getOperatorAssignment_1() { return cOperatorAssignment_1; }
		
		//ID
		public RuleCall getOperatorIDTerminalRuleCall_1_0() { return cOperatorIDTerminalRuleCall_1_0; }
		
		//'('
		public Keyword getLeftParenthesisKeyword_2() { return cLeftParenthesisKeyword_2; }
		
		//inputs+=AdditionAndSubtraction
		public Assignment getInputsAssignment_3() { return cInputsAssignment_3; }
		
		//AdditionAndSubtraction
		public RuleCall getInputsAdditionAndSubtractionParserRuleCall_3_0() { return cInputsAdditionAndSubtractionParserRuleCall_3_0; }
		
		//("," inputs+=AdditionAndSubtraction)*
		public Group getGroup_4() { return cGroup_4; }
		
		//","
		public Keyword getCommaKeyword_4_0() { return cCommaKeyword_4_0; }
		
		//inputs+=AdditionAndSubtraction
		public Assignment getInputsAssignment_4_1() { return cInputsAssignment_4_1; }
		
		//AdditionAndSubtraction
		public RuleCall getInputsAdditionAndSubtractionParserRuleCall_4_1_0() { return cInputsAdditionAndSubtractionParserRuleCall_4_1_0; }
		
		//')'
		public Keyword getRightParenthesisKeyword_5() { return cRightParenthesisKeyword_5; }
	}
	public class SetFunctionElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "de.dlr.sc.virsat.model.calculation.EquationDSL.SetFunction");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final Action cSetFunctionAction_0 = (Action)cGroup.eContents().get(0);
		private final Assignment cOperatorAssignment_1 = (Assignment)cGroup.eContents().get(1);
		private final RuleCall cOperatorIDTerminalRuleCall_1_0 = (RuleCall)cOperatorAssignment_1.eContents().get(0);
		private final Keyword cLeftCurlyBracketKeyword_2 = (Keyword)cGroup.eContents().get(2);
		private final Assignment cTypeDefinitionAssignment_3 = (Assignment)cGroup.eContents().get(3);
		private final CrossReference cTypeDefinitionATypeDefinitionCrossReference_3_0 = (CrossReference)cTypeDefinitionAssignment_3.eContents().get(0);
		private final RuleCall cTypeDefinitionATypeDefinitionQualifiedNameParserRuleCall_3_0_1 = (RuleCall)cTypeDefinitionATypeDefinitionCrossReference_3_0.eContents().get(1);
		private final Group cGroup_4 = (Group)cGroup.eContents().get(4);
		private final Keyword cCommaKeyword_4_0 = (Keyword)cGroup_4.eContents().get(0);
		private final Assignment cDepthAssignment_4_1 = (Assignment)cGroup_4.eContents().get(1);
		private final RuleCall cDepthINTTerminalRuleCall_4_1_0 = (RuleCall)cDepthAssignment_4_1.eContents().get(0);
		private final Group cGroup_5 = (Group)cGroup.eContents().get(5);
		private final Keyword cCommaKeyword_5_0 = (Keyword)cGroup_5.eContents().get(0);
		private final Assignment cFilterNameAssignment_5_1 = (Assignment)cGroup_5.eContents().get(1);
		private final RuleCall cFilterNameIDTerminalRuleCall_5_1_0 = (RuleCall)cFilterNameAssignment_5_1.eContents().get(0);
		private final Keyword cRightCurlyBracketKeyword_6 = (Keyword)cGroup.eContents().get(6);
		
		//SetFunction Calc::AExpression:
		//	{Calc::SetFunction} operator=ID '{' typeDefinition=[Categories::ATypeDefinition|QualifiedName] (',' depth=INT)? (','
		//	filterName=ID)? '}';
		@Override public ParserRule getRule() { return rule; }
		
		//{Calc::SetFunction} operator=ID '{' typeDefinition=[Categories::ATypeDefinition|QualifiedName] (',' depth=INT)? (','
		//filterName=ID)? '}'
		public Group getGroup() { return cGroup; }
		
		//{Calc::SetFunction}
		public Action getSetFunctionAction_0() { return cSetFunctionAction_0; }
		
		//operator=ID
		public Assignment getOperatorAssignment_1() { return cOperatorAssignment_1; }
		
		//ID
		public RuleCall getOperatorIDTerminalRuleCall_1_0() { return cOperatorIDTerminalRuleCall_1_0; }
		
		//'{'
		public Keyword getLeftCurlyBracketKeyword_2() { return cLeftCurlyBracketKeyword_2; }
		
		//typeDefinition=[Categories::ATypeDefinition|QualifiedName]
		public Assignment getTypeDefinitionAssignment_3() { return cTypeDefinitionAssignment_3; }
		
		//[Categories::ATypeDefinition|QualifiedName]
		public CrossReference getTypeDefinitionATypeDefinitionCrossReference_3_0() { return cTypeDefinitionATypeDefinitionCrossReference_3_0; }
		
		//QualifiedName
		public RuleCall getTypeDefinitionATypeDefinitionQualifiedNameParserRuleCall_3_0_1() { return cTypeDefinitionATypeDefinitionQualifiedNameParserRuleCall_3_0_1; }
		
		//(',' depth=INT)?
		public Group getGroup_4() { return cGroup_4; }
		
		//','
		public Keyword getCommaKeyword_4_0() { return cCommaKeyword_4_0; }
		
		//depth=INT
		public Assignment getDepthAssignment_4_1() { return cDepthAssignment_4_1; }
		
		//INT
		public RuleCall getDepthINTTerminalRuleCall_4_1_0() { return cDepthINTTerminalRuleCall_4_1_0; }
		
		//(',' filterName=ID)?
		public Group getGroup_5() { return cGroup_5; }
		
		//','
		public Keyword getCommaKeyword_5_0() { return cCommaKeyword_5_0; }
		
		//filterName=ID
		public Assignment getFilterNameAssignment_5_1() { return cFilterNameAssignment_5_1; }
		
		//ID
		public RuleCall getFilterNameIDTerminalRuleCall_5_1_0() { return cFilterNameIDTerminalRuleCall_5_1_0; }
		
		//'}'
		public Keyword getRightCurlyBracketKeyword_6() { return cRightCurlyBracketKeyword_6; }
	}
	public class NumberLiteralElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "de.dlr.sc.virsat.model.calculation.EquationDSL.NumberLiteral");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final Action cNumberLiteralAction_0 = (Action)cGroup.eContents().get(0);
		private final Assignment cValueAssignment_1 = (Assignment)cGroup.eContents().get(1);
		private final RuleCall cValueNumberLiteralStringParserRuleCall_1_0 = (RuleCall)cValueAssignment_1.eContents().get(0);
		
		//NumberLiteral Calc::NumberLiteral:
		//	{Calc::NumberLiteral} value=NumberLiteralString;
		@Override public ParserRule getRule() { return rule; }
		
		//{Calc::NumberLiteral} value=NumberLiteralString
		public Group getGroup() { return cGroup; }
		
		//{Calc::NumberLiteral}
		public Action getNumberLiteralAction_0() { return cNumberLiteralAction_0; }
		
		//value=NumberLiteralString
		public Assignment getValueAssignment_1() { return cValueAssignment_1; }
		
		//NumberLiteralString
		public RuleCall getValueNumberLiteralStringParserRuleCall_1_0() { return cValueNumberLiteralStringParserRuleCall_1_0; }
	}
	public class QualifiedNameElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "de.dlr.sc.virsat.model.calculation.EquationDSL.QualifiedName");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final RuleCall cIDTerminalRuleCall_0 = (RuleCall)cGroup.eContents().get(0);
		private final Group cGroup_1 = (Group)cGroup.eContents().get(1);
		private final Keyword cFullStopKeyword_1_0 = (Keyword)cGroup_1.eContents().get(0);
		private final RuleCall cIDTerminalRuleCall_1_1 = (RuleCall)cGroup_1.eContents().get(1);
		
		//QualifiedName:
		//	ID ('.' ID)*;
		@Override public ParserRule getRule() { return rule; }
		
		//ID ('.' ID)*
		public Group getGroup() { return cGroup; }
		
		//ID
		public RuleCall getIDTerminalRuleCall_0() { return cIDTerminalRuleCall_0; }
		
		//('.' ID)*
		public Group getGroup_1() { return cGroup_1; }
		
		//'.'
		public Keyword getFullStopKeyword_1_0() { return cFullStopKeyword_1_0; }
		
		//ID
		public RuleCall getIDTerminalRuleCall_1_1() { return cIDTerminalRuleCall_1_1; }
	}
	public class NumberLiteralStringElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "de.dlr.sc.virsat.model.calculation.EquationDSL.NumberLiteralString");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final Keyword cHyphenMinusKeyword_0 = (Keyword)cGroup.eContents().get(0);
		private final RuleCall cINTTerminalRuleCall_1 = (RuleCall)cGroup.eContents().get(1);
		private final Group cGroup_2 = (Group)cGroup.eContents().get(2);
		private final Keyword cFullStopKeyword_2_0 = (Keyword)cGroup_2.eContents().get(0);
		private final RuleCall cINTTerminalRuleCall_2_1 = (RuleCall)cGroup_2.eContents().get(1);
		
		//NumberLiteralString:
		//	'-'? INT ('.' INT)?;
		@Override public ParserRule getRule() { return rule; }
		
		//'-'? INT ('.' INT)?
		public Group getGroup() { return cGroup; }
		
		//'-'?
		public Keyword getHyphenMinusKeyword_0() { return cHyphenMinusKeyword_0; }
		
		//INT
		public RuleCall getINTTerminalRuleCall_1() { return cINTTerminalRuleCall_1; }
		
		//('.' INT)?
		public Group getGroup_2() { return cGroup_2; }
		
		//'.'
		public Keyword getFullStopKeyword_2_0() { return cFullStopKeyword_2_0; }
		
		//INT
		public RuleCall getINTTerminalRuleCall_2_1() { return cINTTerminalRuleCall_2_1; }
	}
	public class ValuePiElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "de.dlr.sc.virsat.model.calculation.EquationDSL.ValuePi");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final Action cValuePiAction_0 = (Action)cGroup.eContents().get(0);
		private final Keyword cPiKeyword_1 = (Keyword)cGroup.eContents().get(1);
		
		//ValuePi Calc::ValuePi:
		//	{Calc::ValuePi}
		//	'pi';
		@Override public ParserRule getRule() { return rule; }
		
		//{Calc::ValuePi} 'pi'
		public Group getGroup() { return cGroup; }
		
		//{Calc::ValuePi}
		public Action getValuePiAction_0() { return cValuePiAction_0; }
		
		//'pi'
		public Keyword getPiKeyword_1() { return cPiKeyword_1; }
	}
	public class ValueEElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "de.dlr.sc.virsat.model.calculation.EquationDSL.ValueE");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final Action cValueEAction_0 = (Action)cGroup.eContents().get(0);
		private final Keyword cEKeyword_1 = (Keyword)cGroup.eContents().get(1);
		
		//ValueE Calc::ValueE:
		//	{Calc::ValueE}
		//	'e';
		@Override public ParserRule getRule() { return rule; }
		
		//{Calc::ValueE} 'e'
		public Group getGroup() { return cGroup; }
		
		//{Calc::ValueE}
		public Action getValueEAction_0() { return cValueEAction_0; }
		
		//'e'
		public Keyword getEKeyword_1() { return cEKeyword_1; }
	}
	
	public class OperatorPlusElements extends AbstractElementFinder.AbstractEnumRuleElementFinder {
		private final EnumRule rule = (EnumRule) GrammarUtil.findRuleForName(getGrammar(), "de.dlr.sc.virsat.model.calculation.EquationDSL.OperatorPlus");
		private final EnumLiteralDeclaration cPLUSEnumLiteralDeclaration = (EnumLiteralDeclaration)rule.eContents().get(1);
		private final Keyword cPLUSPlusSignKeyword_0 = (Keyword)cPLUSEnumLiteralDeclaration.eContents().get(0);
		
		//enum OperatorPlus returns Calc::MathOperator:
		//	PLUS='+';
		public EnumRule getRule() { return rule; }
		
		//PLUS='+'
		public EnumLiteralDeclaration getPLUSEnumLiteralDeclaration() { return cPLUSEnumLiteralDeclaration; }
		
		//'+'
		public Keyword getPLUSPlusSignKeyword_0() { return cPLUSPlusSignKeyword_0; }
	}
	public class OperatorMinusElements extends AbstractElementFinder.AbstractEnumRuleElementFinder {
		private final EnumRule rule = (EnumRule) GrammarUtil.findRuleForName(getGrammar(), "de.dlr.sc.virsat.model.calculation.EquationDSL.OperatorMinus");
		private final EnumLiteralDeclaration cMINUSEnumLiteralDeclaration = (EnumLiteralDeclaration)rule.eContents().get(1);
		private final Keyword cMINUSHyphenMinusKeyword_0 = (Keyword)cMINUSEnumLiteralDeclaration.eContents().get(0);
		
		//enum OperatorMinus returns Calc::MathOperator:
		//	MINUS='-';
		public EnumRule getRule() { return rule; }
		
		//MINUS='-'
		public EnumLiteralDeclaration getMINUSEnumLiteralDeclaration() { return cMINUSEnumLiteralDeclaration; }
		
		//'-'
		public Keyword getMINUSHyphenMinusKeyword_0() { return cMINUSHyphenMinusKeyword_0; }
	}
	public class OperatorMultiplyElements extends AbstractElementFinder.AbstractEnumRuleElementFinder {
		private final EnumRule rule = (EnumRule) GrammarUtil.findRuleForName(getGrammar(), "de.dlr.sc.virsat.model.calculation.EquationDSL.OperatorMultiply");
		private final EnumLiteralDeclaration cMULTIPLYEnumLiteralDeclaration = (EnumLiteralDeclaration)rule.eContents().get(1);
		private final Keyword cMULTIPLYAsteriskKeyword_0 = (Keyword)cMULTIPLYEnumLiteralDeclaration.eContents().get(0);
		
		//enum OperatorMultiply returns Calc::MathOperator:
		//	MULTIPLY='*';
		public EnumRule getRule() { return rule; }
		
		//MULTIPLY='*'
		public EnumLiteralDeclaration getMULTIPLYEnumLiteralDeclaration() { return cMULTIPLYEnumLiteralDeclaration; }
		
		//'*'
		public Keyword getMULTIPLYAsteriskKeyword_0() { return cMULTIPLYAsteriskKeyword_0; }
	}
	public class OperatorDivideElements extends AbstractElementFinder.AbstractEnumRuleElementFinder {
		private final EnumRule rule = (EnumRule) GrammarUtil.findRuleForName(getGrammar(), "de.dlr.sc.virsat.model.calculation.EquationDSL.OperatorDivide");
		private final EnumLiteralDeclaration cDIVIDEEnumLiteralDeclaration = (EnumLiteralDeclaration)rule.eContents().get(1);
		private final Keyword cDIVIDESolidusKeyword_0 = (Keyword)cDIVIDEEnumLiteralDeclaration.eContents().get(0);
		
		//enum OperatorDivide returns Calc::MathOperator:
		//	DIVIDE='/';
		public EnumRule getRule() { return rule; }
		
		//DIVIDE='/'
		public EnumLiteralDeclaration getDIVIDEEnumLiteralDeclaration() { return cDIVIDEEnumLiteralDeclaration; }
		
		//'/'
		public Keyword getDIVIDESolidusKeyword_0() { return cDIVIDESolidusKeyword_0; }
	}
	public class OperatorPowerElements extends AbstractElementFinder.AbstractEnumRuleElementFinder {
		private final EnumRule rule = (EnumRule) GrammarUtil.findRuleForName(getGrammar(), "de.dlr.sc.virsat.model.calculation.EquationDSL.OperatorPower");
		private final EnumLiteralDeclaration cPOWEREnumLiteralDeclaration = (EnumLiteralDeclaration)rule.eContents().get(1);
		private final Keyword cPOWERCircumflexAccentKeyword_0 = (Keyword)cPOWEREnumLiteralDeclaration.eContents().get(0);
		
		//enum OperatorPower returns Calc::MathOperator:
		//	POWER='^';
		public EnumRule getRule() { return rule; }
		
		//POWER='^'
		public EnumLiteralDeclaration getPOWEREnumLiteralDeclaration() { return cPOWEREnumLiteralDeclaration; }
		
		//'^'
		public Keyword getPOWERCircumflexAccentKeyword_0() { return cPOWERCircumflexAccentKeyword_0; }
	}
	public class OperatorCosElements extends AbstractElementFinder.AbstractEnumRuleElementFinder {
		private final EnumRule rule = (EnumRule) GrammarUtil.findRuleForName(getGrammar(), "de.dlr.sc.virsat.model.calculation.EquationDSL.OperatorCos");
		private final EnumLiteralDeclaration cCOSEnumLiteralDeclaration = (EnumLiteralDeclaration)rule.eContents().get(1);
		private final Keyword cCOSCosKeyword_0 = (Keyword)cCOSEnumLiteralDeclaration.eContents().get(0);
		
		//enum OperatorCos returns Calc::MathOperator:
		//	COS='cos';
		public EnumRule getRule() { return rule; }
		
		//COS='cos'
		public EnumLiteralDeclaration getCOSEnumLiteralDeclaration() { return cCOSEnumLiteralDeclaration; }
		
		//'cos'
		public Keyword getCOSCosKeyword_0() { return cCOSCosKeyword_0; }
	}
	public class OperatorSinElements extends AbstractElementFinder.AbstractEnumRuleElementFinder {
		private final EnumRule rule = (EnumRule) GrammarUtil.findRuleForName(getGrammar(), "de.dlr.sc.virsat.model.calculation.EquationDSL.OperatorSin");
		private final EnumLiteralDeclaration cSINEnumLiteralDeclaration = (EnumLiteralDeclaration)rule.eContents().get(1);
		private final Keyword cSINSinKeyword_0 = (Keyword)cSINEnumLiteralDeclaration.eContents().get(0);
		
		//enum OperatorSin returns Calc::MathOperator:
		//	SIN='sin';
		public EnumRule getRule() { return rule; }
		
		//SIN='sin'
		public EnumLiteralDeclaration getSINEnumLiteralDeclaration() { return cSINEnumLiteralDeclaration; }
		
		//'sin'
		public Keyword getSINSinKeyword_0() { return cSINSinKeyword_0; }
	}
	public class OperatorTanElements extends AbstractElementFinder.AbstractEnumRuleElementFinder {
		private final EnumRule rule = (EnumRule) GrammarUtil.findRuleForName(getGrammar(), "de.dlr.sc.virsat.model.calculation.EquationDSL.OperatorTan");
		private final EnumLiteralDeclaration cTANEnumLiteralDeclaration = (EnumLiteralDeclaration)rule.eContents().get(1);
		private final Keyword cTANTanKeyword_0 = (Keyword)cTANEnumLiteralDeclaration.eContents().get(0);
		
		//enum OperatorTan returns Calc::MathOperator:
		//	TAN='tan';
		public EnumRule getRule() { return rule; }
		
		//TAN='tan'
		public EnumLiteralDeclaration getTANEnumLiteralDeclaration() { return cTANEnumLiteralDeclaration; }
		
		//'tan'
		public Keyword getTANTanKeyword_0() { return cTANTanKeyword_0; }
	}
	public class OperatorAtanElements extends AbstractElementFinder.AbstractEnumRuleElementFinder {
		private final EnumRule rule = (EnumRule) GrammarUtil.findRuleForName(getGrammar(), "de.dlr.sc.virsat.model.calculation.EquationDSL.OperatorAtan");
		private final EnumLiteralDeclaration cATANEnumLiteralDeclaration = (EnumLiteralDeclaration)rule.eContents().get(1);
		private final Keyword cATANAtanKeyword_0 = (Keyword)cATANEnumLiteralDeclaration.eContents().get(0);
		
		//enum OperatorAtan returns Calc::MathOperator:
		//	ATAN='atan';
		public EnumRule getRule() { return rule; }
		
		//ATAN='atan'
		public EnumLiteralDeclaration getATANEnumLiteralDeclaration() { return cATANEnumLiteralDeclaration; }
		
		//'atan'
		public Keyword getATANAtanKeyword_0() { return cATANAtanKeyword_0; }
	}
	public class OperatorAcosElements extends AbstractElementFinder.AbstractEnumRuleElementFinder {
		private final EnumRule rule = (EnumRule) GrammarUtil.findRuleForName(getGrammar(), "de.dlr.sc.virsat.model.calculation.EquationDSL.OperatorAcos");
		private final EnumLiteralDeclaration cACOSEnumLiteralDeclaration = (EnumLiteralDeclaration)rule.eContents().get(1);
		private final Keyword cACOSAcosKeyword_0 = (Keyword)cACOSEnumLiteralDeclaration.eContents().get(0);
		
		//enum OperatorAcos returns Calc::MathOperator:
		//	ACOS='acos';
		public EnumRule getRule() { return rule; }
		
		//ACOS='acos'
		public EnumLiteralDeclaration getACOSEnumLiteralDeclaration() { return cACOSEnumLiteralDeclaration; }
		
		//'acos'
		public Keyword getACOSAcosKeyword_0() { return cACOSAcosKeyword_0; }
	}
	public class OperatorAsinElements extends AbstractElementFinder.AbstractEnumRuleElementFinder {
		private final EnumRule rule = (EnumRule) GrammarUtil.findRuleForName(getGrammar(), "de.dlr.sc.virsat.model.calculation.EquationDSL.OperatorAsin");
		private final EnumLiteralDeclaration cASINEnumLiteralDeclaration = (EnumLiteralDeclaration)rule.eContents().get(1);
		private final Keyword cASINAsinKeyword_0 = (Keyword)cASINEnumLiteralDeclaration.eContents().get(0);
		
		//enum OperatorAsin returns Calc::MathOperator:
		//	ASIN='asin';
		public EnumRule getRule() { return rule; }
		
		//ASIN='asin'
		public EnumLiteralDeclaration getASINEnumLiteralDeclaration() { return cASINEnumLiteralDeclaration; }
		
		//'asin'
		public Keyword getASINAsinKeyword_0() { return cASINAsinKeyword_0; }
	}
	public class OperatorSqrtElements extends AbstractElementFinder.AbstractEnumRuleElementFinder {
		private final EnumRule rule = (EnumRule) GrammarUtil.findRuleForName(getGrammar(), "de.dlr.sc.virsat.model.calculation.EquationDSL.OperatorSqrt");
		private final EnumLiteralDeclaration cSQRTEnumLiteralDeclaration = (EnumLiteralDeclaration)rule.eContents().get(1);
		private final Keyword cSQRTSqrtKeyword_0 = (Keyword)cSQRTEnumLiteralDeclaration.eContents().get(0);
		
		//enum OperatorSqrt returns Calc::MathOperator:
		//	SQRT='sqrt';
		public EnumRule getRule() { return rule; }
		
		//SQRT='sqrt'
		public EnumLiteralDeclaration getSQRTEnumLiteralDeclaration() { return cSQRTEnumLiteralDeclaration; }
		
		//'sqrt'
		public Keyword getSQRTSqrtKeyword_0() { return cSQRTSqrtKeyword_0; }
	}
	public class OperatorLogElements extends AbstractElementFinder.AbstractEnumRuleElementFinder {
		private final EnumRule rule = (EnumRule) GrammarUtil.findRuleForName(getGrammar(), "de.dlr.sc.virsat.model.calculation.EquationDSL.OperatorLog");
		private final EnumLiteralDeclaration cLOGEnumLiteralDeclaration = (EnumLiteralDeclaration)rule.eContents().get(1);
		private final Keyword cLOGLogKeyword_0 = (Keyword)cLOGEnumLiteralDeclaration.eContents().get(0);
		
		//enum OperatorLog returns Calc::MathOperator:
		//	LOG='log';
		public EnumRule getRule() { return rule; }
		
		//LOG='log'
		public EnumLiteralDeclaration getLOGEnumLiteralDeclaration() { return cLOGEnumLiteralDeclaration; }
		
		//'log'
		public Keyword getLOGLogKeyword_0() { return cLOGLogKeyword_0; }
	}
	public class OperatorLnElements extends AbstractElementFinder.AbstractEnumRuleElementFinder {
		private final EnumRule rule = (EnumRule) GrammarUtil.findRuleForName(getGrammar(), "de.dlr.sc.virsat.model.calculation.EquationDSL.OperatorLn");
		private final EnumLiteralDeclaration cLNEnumLiteralDeclaration = (EnumLiteralDeclaration)rule.eContents().get(1);
		private final Keyword cLNLnKeyword_0 = (Keyword)cLNEnumLiteralDeclaration.eContents().get(0);
		
		//enum OperatorLn returns Calc::MathOperator:
		//	LN='ln';
		public EnumRule getRule() { return rule; }
		
		//LN='ln'
		public EnumLiteralDeclaration getLNEnumLiteralDeclaration() { return cLNEnumLiteralDeclaration; }
		
		//'ln'
		public Keyword getLNLnKeyword_0() { return cLNLnKeyword_0; }
	}
	public class OperatorExpElements extends AbstractElementFinder.AbstractEnumRuleElementFinder {
		private final EnumRule rule = (EnumRule) GrammarUtil.findRuleForName(getGrammar(), "de.dlr.sc.virsat.model.calculation.EquationDSL.OperatorExp");
		private final EnumLiteralDeclaration cEXPEnumLiteralDeclaration = (EnumLiteralDeclaration)rule.eContents().get(1);
		private final Keyword cEXPExpKeyword_0 = (Keyword)cEXPEnumLiteralDeclaration.eContents().get(0);
		
		//enum OperatorExp returns Calc::MathOperator:
		//	EXP='exp';
		public EnumRule getRule() { return rule; }
		
		//EXP='exp'
		public EnumLiteralDeclaration getEXPEnumLiteralDeclaration() { return cEXPEnumLiteralDeclaration; }
		
		//'exp'
		public Keyword getEXPExpKeyword_0() { return cEXPExpKeyword_0; }
	}
	public class OperatorLdElements extends AbstractElementFinder.AbstractEnumRuleElementFinder {
		private final EnumRule rule = (EnumRule) GrammarUtil.findRuleForName(getGrammar(), "de.dlr.sc.virsat.model.calculation.EquationDSL.OperatorLd");
		private final EnumLiteralDeclaration cLDEnumLiteralDeclaration = (EnumLiteralDeclaration)rule.eContents().get(1);
		private final Keyword cLDLdKeyword_0 = (Keyword)cLDEnumLiteralDeclaration.eContents().get(0);
		
		//enum OperatorLd returns Calc::MathOperator:
		//	LD='ld';
		public EnumRule getRule() { return rule; }
		
		//LD='ld'
		public EnumLiteralDeclaration getLDEnumLiteralDeclaration() { return cLDEnumLiteralDeclaration; }
		
		//'ld'
		public Keyword getLDLdKeyword_0() { return cLDLdKeyword_0; }
	}
	
	private final EquationSectionElements pEquationSection;
	private final ImportElements pImport;
	private final EquationElements pEquation;
	private final EquationResultElements pEquationResult;
	private final TypeInstanceResultElements pTypeInstanceResult;
	private final EquationIntermediateResultElements pEquationIntermediateResult;
	private final AdditionAndSubtractionElements pAdditionAndSubtraction;
	private final MultiplicationAndDivisionElements pMultiplicationAndDivision;
	private final PowerFunctionElements pPowerFunction;
	private final ParenthesisElements pParenthesis;
	private final AExpressionElements pAExpression;
	private final ReferencedInputElements pReferencedInput;
	private final ALiteralElements pALiteral;
	private final FunctionElements pFunction;
	private final AAdvancedFunctionElements pAAdvancedFunction;
	private final AdvancedFunctionElements pAdvancedFunction;
	private final SetFunctionElements pSetFunction;
	private final NumberLiteralElements pNumberLiteral;
	private final QualifiedNameElements pQualifiedName;
	private final NumberLiteralStringElements pNumberLiteralString;
	private final ValuePiElements pValuePi;
	private final ValueEElements pValueE;
	private final OperatorPlusElements eOperatorPlus;
	private final OperatorMinusElements eOperatorMinus;
	private final OperatorMultiplyElements eOperatorMultiply;
	private final OperatorDivideElements eOperatorDivide;
	private final OperatorPowerElements eOperatorPower;
	private final OperatorCosElements eOperatorCos;
	private final OperatorSinElements eOperatorSin;
	private final OperatorTanElements eOperatorTan;
	private final OperatorAtanElements eOperatorAtan;
	private final OperatorAcosElements eOperatorAcos;
	private final OperatorAsinElements eOperatorAsin;
	private final OperatorSqrtElements eOperatorSqrt;
	private final OperatorLogElements eOperatorLog;
	private final OperatorLnElements eOperatorLn;
	private final OperatorExpElements eOperatorExp;
	private final OperatorLdElements eOperatorLd;
	
	private final Grammar grammar;
	
	private final TerminalsGrammarAccess gaTerminals;

	@Inject
	public EquationDSLGrammarAccess(GrammarProvider grammarProvider,
			TerminalsGrammarAccess gaTerminals) {
		this.grammar = internalFindGrammar(grammarProvider);
		this.gaTerminals = gaTerminals;
		this.pEquationSection = new EquationSectionElements();
		this.pImport = new ImportElements();
		this.pEquation = new EquationElements();
		this.pEquationResult = new EquationResultElements();
		this.pTypeInstanceResult = new TypeInstanceResultElements();
		this.pEquationIntermediateResult = new EquationIntermediateResultElements();
		this.pAdditionAndSubtraction = new AdditionAndSubtractionElements();
		this.pMultiplicationAndDivision = new MultiplicationAndDivisionElements();
		this.pPowerFunction = new PowerFunctionElements();
		this.pParenthesis = new ParenthesisElements();
		this.pAExpression = new AExpressionElements();
		this.pReferencedInput = new ReferencedInputElements();
		this.pALiteral = new ALiteralElements();
		this.pFunction = new FunctionElements();
		this.pAAdvancedFunction = new AAdvancedFunctionElements();
		this.pAdvancedFunction = new AdvancedFunctionElements();
		this.pSetFunction = new SetFunctionElements();
		this.pNumberLiteral = new NumberLiteralElements();
		this.pQualifiedName = new QualifiedNameElements();
		this.pNumberLiteralString = new NumberLiteralStringElements();
		this.pValuePi = new ValuePiElements();
		this.pValueE = new ValueEElements();
		this.eOperatorPlus = new OperatorPlusElements();
		this.eOperatorMinus = new OperatorMinusElements();
		this.eOperatorMultiply = new OperatorMultiplyElements();
		this.eOperatorDivide = new OperatorDivideElements();
		this.eOperatorPower = new OperatorPowerElements();
		this.eOperatorCos = new OperatorCosElements();
		this.eOperatorSin = new OperatorSinElements();
		this.eOperatorTan = new OperatorTanElements();
		this.eOperatorAtan = new OperatorAtanElements();
		this.eOperatorAcos = new OperatorAcosElements();
		this.eOperatorAsin = new OperatorAsinElements();
		this.eOperatorSqrt = new OperatorSqrtElements();
		this.eOperatorLog = new OperatorLogElements();
		this.eOperatorLn = new OperatorLnElements();
		this.eOperatorExp = new OperatorExpElements();
		this.eOperatorLd = new OperatorLdElements();
	}
	
	protected Grammar internalFindGrammar(GrammarProvider grammarProvider) {
		Grammar grammar = grammarProvider.getGrammar(this);
		while (grammar != null) {
			if ("de.dlr.sc.virsat.model.calculation.EquationDSL".equals(grammar.getName())) {
				return grammar;
			}
			List<Grammar> grammars = grammar.getUsedGrammars();
			if (!grammars.isEmpty()) {
				grammar = grammars.iterator().next();
			} else {
				return null;
			}
		}
		return grammar;
	}
	
	@Override
	public Grammar getGrammar() {
		return grammar;
	}
	
	
	public TerminalsGrammarAccess getTerminalsGrammarAccess() {
		return gaTerminals;
	}

	
	//EquationSection Calc::EquationSection:
	//	imports+=Import*
	//	equations+=Equation*;
	public EquationSectionElements getEquationSectionAccess() {
		return pEquationSection;
	}
	
	public ParserRule getEquationSectionRule() {
		return getEquationSectionAccess().getRule();
	}
	
	//Import Calc::Import:
	//	'Import:' importedNamespace=[General::IInstance|QualifiedName] ';';
	public ImportElements getImportAccess() {
		return pImport;
	}
	
	public ParserRule getImportRule() {
		return getImportAccess().getRule();
	}
	
	//Equation Calc::Equation:
	//	result=EquationResult '=' expression=AdditionAndSubtraction ';';
	public EquationElements getEquationAccess() {
		return pEquation;
	}
	
	public ParserRule getEquationRule() {
		return getEquationAccess().getRule();
	}
	
	//EquationResult Calc::IEquationResult:
	//	TypeInstanceResult | EquationIntermediateResult;
	public EquationResultElements getEquationResultAccess() {
		return pEquationResult;
	}
	
	public ParserRule getEquationResultRule() {
		return getEquationResultAccess().getRule();
	}
	
	//TypeInstanceResult Calc::IEquationResult:
	//	{Calc::TypeInstanceResult}
	//	'Ref:' reference=[Categories::ATypeInstance|QualifiedName];
	public TypeInstanceResultElements getTypeInstanceResultAccess() {
		return pTypeInstanceResult;
	}
	
	public ParserRule getTypeInstanceResultRule() {
		return getTypeInstanceResultAccess().getRule();
	}
	
	//EquationIntermediateResult Calc::IEquationResult:
	//	{Calc::EquationIntermediateResult}
	//	'Calc:' name=ID;
	public EquationIntermediateResultElements getEquationIntermediateResultAccess() {
		return pEquationIntermediateResult;
	}
	
	public ParserRule getEquationIntermediateResultRule() {
		return getEquationIntermediateResultAccess().getRule();
	}
	
	//AdditionAndSubtraction Calc::AExpression:
	//	MultiplicationAndDivision ({Calc::AdditionAndSubtraction.left=current} operator=(OperatorPlus | OperatorMinus)
	//	right=MultiplicationAndDivision)*;
	public AdditionAndSubtractionElements getAdditionAndSubtractionAccess() {
		return pAdditionAndSubtraction;
	}
	
	public ParserRule getAdditionAndSubtractionRule() {
		return getAdditionAndSubtractionAccess().getRule();
	}
	
	//MultiplicationAndDivision Calc::AExpression:
	//	PowerFunction ({Calc::MultiplicationAndDivision.left=current} operator=(OperatorMultiply | OperatorDivide)
	//	right=PowerFunction)*;
	public MultiplicationAndDivisionElements getMultiplicationAndDivisionAccess() {
		return pMultiplicationAndDivision;
	}
	
	public ParserRule getMultiplicationAndDivisionRule() {
		return getMultiplicationAndDivisionAccess().getRule();
	}
	
	//PowerFunction Calc::AExpression:
	//	AExpression ({Calc::PowerFunction.left=current} operator=OperatorPower right=AExpression)*;
	public PowerFunctionElements getPowerFunctionAccess() {
		return pPowerFunction;
	}
	
	public ParserRule getPowerFunctionRule() {
		return getPowerFunctionAccess().getRule();
	}
	
	//Parenthesis Calc::AOpRightExpression:
	//	{Calc::Parenthesis} operator=OperatorMinus? '(' right=AdditionAndSubtraction ')';
	public ParenthesisElements getParenthesisAccess() {
		return pParenthesis;
	}
	
	public ParserRule getParenthesisRule() {
		return getParenthesisAccess().getRule();
	}
	
	//AExpression Calc::AExpression:
	//	ALiteral | ReferencedInput | Parenthesis | Function | AAdvancedFunction;
	public AExpressionElements getAExpressionAccess() {
		return pAExpression;
	}
	
	public ParserRule getAExpressionRule() {
		return getAExpressionAccess().getRule();
	}
	
	//ReferencedInput Calc::ReferencedInput:
	//	reference=[Calc::IEquationInput|QualifiedName];
	public ReferencedInputElements getReferencedInputAccess() {
		return pReferencedInput;
	}
	
	public ParserRule getReferencedInputRule() {
		return getReferencedInputAccess().getRule();
	}
	
	//ALiteral Calc::ALiteral:
	//	NumberLiteral | ValueE | ValuePi;
	public ALiteralElements getALiteralAccess() {
		return pALiteral;
	}
	
	public ParserRule getALiteralRule() {
		return getALiteralAccess().getRule();
	}
	
	//Function Calc::AExpression:
	//	{Calc::Function} operator=(OperatorCos | OperatorSin | OperatorTan | OperatorAtan | OperatorAcos | OperatorAsin |
	//	OperatorSqrt | OperatorLog | OperatorLn | OperatorLd | OperatorExp) '(' right=AdditionAndSubtraction ')';
	public FunctionElements getFunctionAccess() {
		return pFunction;
	}
	
	public ParserRule getFunctionRule() {
		return getFunctionAccess().getRule();
	}
	
	//AAdvancedFunction Calc::AExpression:
	//	AdvancedFunction | SetFunction;
	public AAdvancedFunctionElements getAAdvancedFunctionAccess() {
		return pAAdvancedFunction;
	}
	
	public ParserRule getAAdvancedFunctionRule() {
		return getAAdvancedFunctionAccess().getRule();
	}
	
	//AdvancedFunction Calc::AExpression:
	//	{Calc::AdvancedFunction} operator=ID '(' inputs+=AdditionAndSubtraction ("," inputs+=AdditionAndSubtraction)* ')';
	public AdvancedFunctionElements getAdvancedFunctionAccess() {
		return pAdvancedFunction;
	}
	
	public ParserRule getAdvancedFunctionRule() {
		return getAdvancedFunctionAccess().getRule();
	}
	
	//SetFunction Calc::AExpression:
	//	{Calc::SetFunction} operator=ID '{' typeDefinition=[Categories::ATypeDefinition|QualifiedName] (',' depth=INT)? (','
	//	filterName=ID)? '}';
	public SetFunctionElements getSetFunctionAccess() {
		return pSetFunction;
	}
	
	public ParserRule getSetFunctionRule() {
		return getSetFunctionAccess().getRule();
	}
	
	//NumberLiteral Calc::NumberLiteral:
	//	{Calc::NumberLiteral} value=NumberLiteralString;
	public NumberLiteralElements getNumberLiteralAccess() {
		return pNumberLiteral;
	}
	
	public ParserRule getNumberLiteralRule() {
		return getNumberLiteralAccess().getRule();
	}
	
	//QualifiedName:
	//	ID ('.' ID)*;
	public QualifiedNameElements getQualifiedNameAccess() {
		return pQualifiedName;
	}
	
	public ParserRule getQualifiedNameRule() {
		return getQualifiedNameAccess().getRule();
	}
	
	//NumberLiteralString:
	//	'-'? INT ('.' INT)?;
	public NumberLiteralStringElements getNumberLiteralStringAccess() {
		return pNumberLiteralString;
	}
	
	public ParserRule getNumberLiteralStringRule() {
		return getNumberLiteralStringAccess().getRule();
	}
	
	//ValuePi Calc::ValuePi:
	//	{Calc::ValuePi}
	//	'pi';
	public ValuePiElements getValuePiAccess() {
		return pValuePi;
	}
	
	public ParserRule getValuePiRule() {
		return getValuePiAccess().getRule();
	}
	
	//ValueE Calc::ValueE:
	//	{Calc::ValueE}
	//	'e';
	public ValueEElements getValueEAccess() {
		return pValueE;
	}
	
	public ParserRule getValueERule() {
		return getValueEAccess().getRule();
	}
	
	//enum OperatorPlus returns Calc::MathOperator:
	//	PLUS='+';
	public OperatorPlusElements getOperatorPlusAccess() {
		return eOperatorPlus;
	}
	
	public EnumRule getOperatorPlusRule() {
		return getOperatorPlusAccess().getRule();
	}
	
	//enum OperatorMinus returns Calc::MathOperator:
	//	MINUS='-';
	public OperatorMinusElements getOperatorMinusAccess() {
		return eOperatorMinus;
	}
	
	public EnumRule getOperatorMinusRule() {
		return getOperatorMinusAccess().getRule();
	}
	
	//enum OperatorMultiply returns Calc::MathOperator:
	//	MULTIPLY='*';
	public OperatorMultiplyElements getOperatorMultiplyAccess() {
		return eOperatorMultiply;
	}
	
	public EnumRule getOperatorMultiplyRule() {
		return getOperatorMultiplyAccess().getRule();
	}
	
	//enum OperatorDivide returns Calc::MathOperator:
	//	DIVIDE='/';
	public OperatorDivideElements getOperatorDivideAccess() {
		return eOperatorDivide;
	}
	
	public EnumRule getOperatorDivideRule() {
		return getOperatorDivideAccess().getRule();
	}
	
	//enum OperatorPower returns Calc::MathOperator:
	//	POWER='^';
	public OperatorPowerElements getOperatorPowerAccess() {
		return eOperatorPower;
	}
	
	public EnumRule getOperatorPowerRule() {
		return getOperatorPowerAccess().getRule();
	}
	
	//enum OperatorCos returns Calc::MathOperator:
	//	COS='cos';
	public OperatorCosElements getOperatorCosAccess() {
		return eOperatorCos;
	}
	
	public EnumRule getOperatorCosRule() {
		return getOperatorCosAccess().getRule();
	}
	
	//enum OperatorSin returns Calc::MathOperator:
	//	SIN='sin';
	public OperatorSinElements getOperatorSinAccess() {
		return eOperatorSin;
	}
	
	public EnumRule getOperatorSinRule() {
		return getOperatorSinAccess().getRule();
	}
	
	//enum OperatorTan returns Calc::MathOperator:
	//	TAN='tan';
	public OperatorTanElements getOperatorTanAccess() {
		return eOperatorTan;
	}
	
	public EnumRule getOperatorTanRule() {
		return getOperatorTanAccess().getRule();
	}
	
	//enum OperatorAtan returns Calc::MathOperator:
	//	ATAN='atan';
	public OperatorAtanElements getOperatorAtanAccess() {
		return eOperatorAtan;
	}
	
	public EnumRule getOperatorAtanRule() {
		return getOperatorAtanAccess().getRule();
	}
	
	//enum OperatorAcos returns Calc::MathOperator:
	//	ACOS='acos';
	public OperatorAcosElements getOperatorAcosAccess() {
		return eOperatorAcos;
	}
	
	public EnumRule getOperatorAcosRule() {
		return getOperatorAcosAccess().getRule();
	}
	
	//enum OperatorAsin returns Calc::MathOperator:
	//	ASIN='asin';
	public OperatorAsinElements getOperatorAsinAccess() {
		return eOperatorAsin;
	}
	
	public EnumRule getOperatorAsinRule() {
		return getOperatorAsinAccess().getRule();
	}
	
	//enum OperatorSqrt returns Calc::MathOperator:
	//	SQRT='sqrt';
	public OperatorSqrtElements getOperatorSqrtAccess() {
		return eOperatorSqrt;
	}
	
	public EnumRule getOperatorSqrtRule() {
		return getOperatorSqrtAccess().getRule();
	}
	
	//enum OperatorLog returns Calc::MathOperator:
	//	LOG='log';
	public OperatorLogElements getOperatorLogAccess() {
		return eOperatorLog;
	}
	
	public EnumRule getOperatorLogRule() {
		return getOperatorLogAccess().getRule();
	}
	
	//enum OperatorLn returns Calc::MathOperator:
	//	LN='ln';
	public OperatorLnElements getOperatorLnAccess() {
		return eOperatorLn;
	}
	
	public EnumRule getOperatorLnRule() {
		return getOperatorLnAccess().getRule();
	}
	
	//enum OperatorExp returns Calc::MathOperator:
	//	EXP='exp';
	public OperatorExpElements getOperatorExpAccess() {
		return eOperatorExp;
	}
	
	public EnumRule getOperatorExpRule() {
		return getOperatorExpAccess().getRule();
	}
	
	//enum OperatorLd returns Calc::MathOperator:
	//	LD='ld';
	public OperatorLdElements getOperatorLdAccess() {
		return eOperatorLd;
	}
	
	public EnumRule getOperatorLdRule() {
		return getOperatorLdAccess().getRule();
	}
	
	//terminal ID:
	//	'^'? ('a'..'z' | 'A'..'Z' | '_') ('a'..'z' | 'A'..'Z' | '_' | '0'..'9')*;
	public TerminalRule getIDRule() {
		return gaTerminals.getIDRule();
	}
	
	//terminal INT returns ecore::EInt:
	//	'0'..'9'+;
	public TerminalRule getINTRule() {
		return gaTerminals.getINTRule();
	}
	
	//terminal STRING:
	//	'"' ('\\' . | !('\\' | '"'))* '"' |
	//	"'" ('\\' . | !('\\' | "'"))* "'";
	public TerminalRule getSTRINGRule() {
		return gaTerminals.getSTRINGRule();
	}
	
	//terminal ML_COMMENT:
	//	'/*'->'*/';
	public TerminalRule getML_COMMENTRule() {
		return gaTerminals.getML_COMMENTRule();
	}
	
	//terminal SL_COMMENT:
	//	'//' !('\n' | '\r')* ('\r'? '\n')?;
	public TerminalRule getSL_COMMENTRule() {
		return gaTerminals.getSL_COMMENTRule();
	}
	
	//terminal WS:
	//	' ' | '\t' | '\r' | '\n'+;
	public TerminalRule getWSRule() {
		return gaTerminals.getWSRule();
	}
	
	//terminal ANY_OTHER:
	//	.;
	public TerminalRule getANY_OTHERRule() {
		return gaTerminals.getANY_OTHERRule();
	}
}
