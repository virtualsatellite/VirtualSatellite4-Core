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
	superClass=AbstractInternalContentAssistParser;
}

@lexer::header {
package de.dlr.sc.virsat.model.calculation.ide.contentassist.antlr.internal;

// Hack: Use our own Lexer superclass by means of import. 
// Currently there is no other way to specify the superclass for the lexer.
import org.eclipse.xtext.ide.editor.contentassist.antlr.internal.Lexer;
}

@parser::header {
package de.dlr.sc.virsat.model.calculation.ide.contentassist.antlr.internal;

import java.io.InputStream;
import org.eclipse.xtext.*;
import org.eclipse.xtext.parser.*;
import org.eclipse.xtext.parser.impl.*;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.parser.antlr.XtextTokenStream;
import org.eclipse.xtext.parser.antlr.XtextTokenStream.HiddenTokens;
import org.eclipse.xtext.ide.editor.contentassist.antlr.internal.AbstractInternalContentAssistParser;
import org.eclipse.xtext.ide.editor.contentassist.antlr.internal.DFA;
import de.dlr.sc.virsat.model.calculation.services.EquationDSLGrammarAccess;

}
@parser::members {
	private EquationDSLGrammarAccess grammarAccess;

	public void setGrammarAccess(EquationDSLGrammarAccess grammarAccess) {
		this.grammarAccess = grammarAccess;
	}

	@Override
	protected Grammar getGrammar() {
		return grammarAccess.getGrammar();
	}

	@Override
	protected String getValueForTokenName(String tokenName) {
		return tokenName;
	}
}

// Entry rule entryRuleEquationSection
entryRuleEquationSection
:
{ before(grammarAccess.getEquationSectionRule()); }
	 ruleEquationSection
{ after(grammarAccess.getEquationSectionRule()); } 
	 EOF 
;

// Rule EquationSection
ruleEquationSection 
	@init {
		int stackSize = keepStackSize();
	}
	:
	(
		{ before(grammarAccess.getEquationSectionAccess().getGroup()); }
		(rule__EquationSection__Group__0)
		{ after(grammarAccess.getEquationSectionAccess().getGroup()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

// Entry rule entryRuleImport
entryRuleImport
:
{ before(grammarAccess.getImportRule()); }
	 ruleImport
{ after(grammarAccess.getImportRule()); } 
	 EOF 
;

// Rule Import
ruleImport 
	@init {
		int stackSize = keepStackSize();
	}
	:
	(
		{ before(grammarAccess.getImportAccess().getGroup()); }
		(rule__Import__Group__0)
		{ after(grammarAccess.getImportAccess().getGroup()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

// Entry rule entryRuleEquation
entryRuleEquation
:
{ before(grammarAccess.getEquationRule()); }
	 ruleEquation
{ after(grammarAccess.getEquationRule()); } 
	 EOF 
;

// Rule Equation
ruleEquation 
	@init {
		int stackSize = keepStackSize();
	}
	:
	(
		{ before(grammarAccess.getEquationAccess().getGroup()); }
		(rule__Equation__Group__0)
		{ after(grammarAccess.getEquationAccess().getGroup()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

// Entry rule entryRuleEquationResult
entryRuleEquationResult
:
{ before(grammarAccess.getEquationResultRule()); }
	 ruleEquationResult
{ after(grammarAccess.getEquationResultRule()); } 
	 EOF 
;

// Rule EquationResult
ruleEquationResult 
	@init {
		int stackSize = keepStackSize();
	}
	:
	(
		{ before(grammarAccess.getEquationResultAccess().getAlternatives()); }
		(rule__EquationResult__Alternatives)
		{ after(grammarAccess.getEquationResultAccess().getAlternatives()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

// Entry rule entryRuleTypeInstanceResult
entryRuleTypeInstanceResult
:
{ before(grammarAccess.getTypeInstanceResultRule()); }
	 ruleTypeInstanceResult
{ after(grammarAccess.getTypeInstanceResultRule()); } 
	 EOF 
;

// Rule TypeInstanceResult
ruleTypeInstanceResult 
	@init {
		int stackSize = keepStackSize();
	}
	:
	(
		{ before(grammarAccess.getTypeInstanceResultAccess().getGroup()); }
		(rule__TypeInstanceResult__Group__0)
		{ after(grammarAccess.getTypeInstanceResultAccess().getGroup()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

// Entry rule entryRuleEquationIntermediateResult
entryRuleEquationIntermediateResult
:
{ before(grammarAccess.getEquationIntermediateResultRule()); }
	 ruleEquationIntermediateResult
{ after(grammarAccess.getEquationIntermediateResultRule()); } 
	 EOF 
;

// Rule EquationIntermediateResult
ruleEquationIntermediateResult 
	@init {
		int stackSize = keepStackSize();
	}
	:
	(
		{ before(grammarAccess.getEquationIntermediateResultAccess().getGroup()); }
		(rule__EquationIntermediateResult__Group__0)
		{ after(grammarAccess.getEquationIntermediateResultAccess().getGroup()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

// Entry rule entryRuleAdditionAndSubtraction
entryRuleAdditionAndSubtraction
:
{ before(grammarAccess.getAdditionAndSubtractionRule()); }
	 ruleAdditionAndSubtraction
{ after(grammarAccess.getAdditionAndSubtractionRule()); } 
	 EOF 
;

// Rule AdditionAndSubtraction
ruleAdditionAndSubtraction 
	@init {
		int stackSize = keepStackSize();
	}
	:
	(
		{ before(grammarAccess.getAdditionAndSubtractionAccess().getGroup()); }
		(rule__AdditionAndSubtraction__Group__0)
		{ after(grammarAccess.getAdditionAndSubtractionAccess().getGroup()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

// Entry rule entryRuleMultiplicationAndDivision
entryRuleMultiplicationAndDivision
:
{ before(grammarAccess.getMultiplicationAndDivisionRule()); }
	 ruleMultiplicationAndDivision
{ after(grammarAccess.getMultiplicationAndDivisionRule()); } 
	 EOF 
;

// Rule MultiplicationAndDivision
ruleMultiplicationAndDivision 
	@init {
		int stackSize = keepStackSize();
	}
	:
	(
		{ before(grammarAccess.getMultiplicationAndDivisionAccess().getGroup()); }
		(rule__MultiplicationAndDivision__Group__0)
		{ after(grammarAccess.getMultiplicationAndDivisionAccess().getGroup()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

// Entry rule entryRulePowerFunction
entryRulePowerFunction
:
{ before(grammarAccess.getPowerFunctionRule()); }
	 rulePowerFunction
{ after(grammarAccess.getPowerFunctionRule()); } 
	 EOF 
;

// Rule PowerFunction
rulePowerFunction 
	@init {
		int stackSize = keepStackSize();
	}
	:
	(
		{ before(grammarAccess.getPowerFunctionAccess().getGroup()); }
		(rule__PowerFunction__Group__0)
		{ after(grammarAccess.getPowerFunctionAccess().getGroup()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

// Entry rule entryRuleParenthesis
entryRuleParenthesis
:
{ before(grammarAccess.getParenthesisRule()); }
	 ruleParenthesis
{ after(grammarAccess.getParenthesisRule()); } 
	 EOF 
;

// Rule Parenthesis
ruleParenthesis 
	@init {
		int stackSize = keepStackSize();
	}
	:
	(
		{ before(grammarAccess.getParenthesisAccess().getGroup()); }
		(rule__Parenthesis__Group__0)
		{ after(grammarAccess.getParenthesisAccess().getGroup()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

// Entry rule entryRuleAExpression
entryRuleAExpression
:
{ before(grammarAccess.getAExpressionRule()); }
	 ruleAExpression
{ after(grammarAccess.getAExpressionRule()); } 
	 EOF 
;

// Rule AExpression
ruleAExpression 
	@init {
		int stackSize = keepStackSize();
	}
	:
	(
		{ before(grammarAccess.getAExpressionAccess().getAlternatives()); }
		(rule__AExpression__Alternatives)
		{ after(grammarAccess.getAExpressionAccess().getAlternatives()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

// Entry rule entryRuleReferencedInput
entryRuleReferencedInput
:
{ before(grammarAccess.getReferencedInputRule()); }
	 ruleReferencedInput
{ after(grammarAccess.getReferencedInputRule()); } 
	 EOF 
;

// Rule ReferencedInput
ruleReferencedInput 
	@init {
		int stackSize = keepStackSize();
	}
	:
	(
		{ before(grammarAccess.getReferencedInputAccess().getReferenceAssignment()); }
		(rule__ReferencedInput__ReferenceAssignment)
		{ after(grammarAccess.getReferencedInputAccess().getReferenceAssignment()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

// Entry rule entryRuleALiteral
entryRuleALiteral
:
{ before(grammarAccess.getALiteralRule()); }
	 ruleALiteral
{ after(grammarAccess.getALiteralRule()); } 
	 EOF 
;

// Rule ALiteral
ruleALiteral 
	@init {
		int stackSize = keepStackSize();
	}
	:
	(
		{ before(grammarAccess.getALiteralAccess().getAlternatives()); }
		(rule__ALiteral__Alternatives)
		{ after(grammarAccess.getALiteralAccess().getAlternatives()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

// Entry rule entryRuleFunction
entryRuleFunction
:
{ before(grammarAccess.getFunctionRule()); }
	 ruleFunction
{ after(grammarAccess.getFunctionRule()); } 
	 EOF 
;

// Rule Function
ruleFunction 
	@init {
		int stackSize = keepStackSize();
	}
	:
	(
		{ before(grammarAccess.getFunctionAccess().getGroup()); }
		(rule__Function__Group__0)
		{ after(grammarAccess.getFunctionAccess().getGroup()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

// Entry rule entryRuleAAdvancedFunction
entryRuleAAdvancedFunction
:
{ before(grammarAccess.getAAdvancedFunctionRule()); }
	 ruleAAdvancedFunction
{ after(grammarAccess.getAAdvancedFunctionRule()); } 
	 EOF 
;

// Rule AAdvancedFunction
ruleAAdvancedFunction 
	@init {
		int stackSize = keepStackSize();
	}
	:
	(
		{ before(grammarAccess.getAAdvancedFunctionAccess().getAlternatives()); }
		(rule__AAdvancedFunction__Alternatives)
		{ after(grammarAccess.getAAdvancedFunctionAccess().getAlternatives()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

// Entry rule entryRuleAdvancedFunction
entryRuleAdvancedFunction
:
{ before(grammarAccess.getAdvancedFunctionRule()); }
	 ruleAdvancedFunction
{ after(grammarAccess.getAdvancedFunctionRule()); } 
	 EOF 
;

// Rule AdvancedFunction
ruleAdvancedFunction 
	@init {
		int stackSize = keepStackSize();
	}
	:
	(
		{ before(grammarAccess.getAdvancedFunctionAccess().getGroup()); }
		(rule__AdvancedFunction__Group__0)
		{ after(grammarAccess.getAdvancedFunctionAccess().getGroup()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

// Entry rule entryRuleSetFunction
entryRuleSetFunction
:
{ before(grammarAccess.getSetFunctionRule()); }
	 ruleSetFunction
{ after(grammarAccess.getSetFunctionRule()); } 
	 EOF 
;

// Rule SetFunction
ruleSetFunction 
	@init {
		int stackSize = keepStackSize();
	}
	:
	(
		{ before(grammarAccess.getSetFunctionAccess().getGroup()); }
		(rule__SetFunction__Group__0)
		{ after(grammarAccess.getSetFunctionAccess().getGroup()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

// Entry rule entryRuleNumberLiteral
entryRuleNumberLiteral
:
{ before(grammarAccess.getNumberLiteralRule()); }
	 ruleNumberLiteral
{ after(grammarAccess.getNumberLiteralRule()); } 
	 EOF 
;

// Rule NumberLiteral
ruleNumberLiteral 
	@init {
		int stackSize = keepStackSize();
	}
	:
	(
		{ before(grammarAccess.getNumberLiteralAccess().getGroup()); }
		(rule__NumberLiteral__Group__0)
		{ after(grammarAccess.getNumberLiteralAccess().getGroup()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

// Entry rule entryRuleQualifiedName
entryRuleQualifiedName
:
{ before(grammarAccess.getQualifiedNameRule()); }
	 ruleQualifiedName
{ after(grammarAccess.getQualifiedNameRule()); } 
	 EOF 
;

// Rule QualifiedName
ruleQualifiedName 
	@init {
		int stackSize = keepStackSize();
	}
	:
	(
		{ before(grammarAccess.getQualifiedNameAccess().getGroup()); }
		(rule__QualifiedName__Group__0)
		{ after(grammarAccess.getQualifiedNameAccess().getGroup()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

// Entry rule entryRuleNumberLiteralString
entryRuleNumberLiteralString
:
{ before(grammarAccess.getNumberLiteralStringRule()); }
	 ruleNumberLiteralString
{ after(grammarAccess.getNumberLiteralStringRule()); } 
	 EOF 
;

// Rule NumberLiteralString
ruleNumberLiteralString 
	@init {
		int stackSize = keepStackSize();
	}
	:
	(
		{ before(grammarAccess.getNumberLiteralStringAccess().getGroup()); }
		(rule__NumberLiteralString__Group__0)
		{ after(grammarAccess.getNumberLiteralStringAccess().getGroup()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

// Entry rule entryRuleValuePi
entryRuleValuePi
:
{ before(grammarAccess.getValuePiRule()); }
	 ruleValuePi
{ after(grammarAccess.getValuePiRule()); } 
	 EOF 
;

// Rule ValuePi
ruleValuePi 
	@init {
		int stackSize = keepStackSize();
	}
	:
	(
		{ before(grammarAccess.getValuePiAccess().getGroup()); }
		(rule__ValuePi__Group__0)
		{ after(grammarAccess.getValuePiAccess().getGroup()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

// Entry rule entryRuleValueE
entryRuleValueE
:
{ before(grammarAccess.getValueERule()); }
	 ruleValueE
{ after(grammarAccess.getValueERule()); } 
	 EOF 
;

// Rule ValueE
ruleValueE 
	@init {
		int stackSize = keepStackSize();
	}
	:
	(
		{ before(grammarAccess.getValueEAccess().getGroup()); }
		(rule__ValueE__Group__0)
		{ after(grammarAccess.getValueEAccess().getGroup()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

// Rule OperatorPlus
ruleOperatorPlus
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getOperatorPlusAccess().getPLUSEnumLiteralDeclaration()); }
		('+')
		{ after(grammarAccess.getOperatorPlusAccess().getPLUSEnumLiteralDeclaration()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

// Rule OperatorMinus
ruleOperatorMinus
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getOperatorMinusAccess().getMINUSEnumLiteralDeclaration()); }
		('-')
		{ after(grammarAccess.getOperatorMinusAccess().getMINUSEnumLiteralDeclaration()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

// Rule OperatorMultiply
ruleOperatorMultiply
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getOperatorMultiplyAccess().getMULTIPLYEnumLiteralDeclaration()); }
		('*')
		{ after(grammarAccess.getOperatorMultiplyAccess().getMULTIPLYEnumLiteralDeclaration()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

// Rule OperatorDivide
ruleOperatorDivide
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getOperatorDivideAccess().getDIVIDEEnumLiteralDeclaration()); }
		('/')
		{ after(grammarAccess.getOperatorDivideAccess().getDIVIDEEnumLiteralDeclaration()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

// Rule OperatorPower
ruleOperatorPower
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getOperatorPowerAccess().getPOWEREnumLiteralDeclaration()); }
		('^')
		{ after(grammarAccess.getOperatorPowerAccess().getPOWEREnumLiteralDeclaration()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

// Rule OperatorCos
ruleOperatorCos
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getOperatorCosAccess().getCOSEnumLiteralDeclaration()); }
		('cos')
		{ after(grammarAccess.getOperatorCosAccess().getCOSEnumLiteralDeclaration()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

// Rule OperatorSin
ruleOperatorSin
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getOperatorSinAccess().getSINEnumLiteralDeclaration()); }
		('sin')
		{ after(grammarAccess.getOperatorSinAccess().getSINEnumLiteralDeclaration()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

// Rule OperatorTan
ruleOperatorTan
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getOperatorTanAccess().getTANEnumLiteralDeclaration()); }
		('tan')
		{ after(grammarAccess.getOperatorTanAccess().getTANEnumLiteralDeclaration()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

// Rule OperatorAtan
ruleOperatorAtan
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getOperatorAtanAccess().getATANEnumLiteralDeclaration()); }
		('atan')
		{ after(grammarAccess.getOperatorAtanAccess().getATANEnumLiteralDeclaration()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

// Rule OperatorAcos
ruleOperatorAcos
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getOperatorAcosAccess().getACOSEnumLiteralDeclaration()); }
		('acos')
		{ after(grammarAccess.getOperatorAcosAccess().getACOSEnumLiteralDeclaration()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

// Rule OperatorAsin
ruleOperatorAsin
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getOperatorAsinAccess().getASINEnumLiteralDeclaration()); }
		('asin')
		{ after(grammarAccess.getOperatorAsinAccess().getASINEnumLiteralDeclaration()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

// Rule OperatorSqrt
ruleOperatorSqrt
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getOperatorSqrtAccess().getSQRTEnumLiteralDeclaration()); }
		('sqrt')
		{ after(grammarAccess.getOperatorSqrtAccess().getSQRTEnumLiteralDeclaration()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

// Rule OperatorLog
ruleOperatorLog
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getOperatorLogAccess().getLOGEnumLiteralDeclaration()); }
		('log')
		{ after(grammarAccess.getOperatorLogAccess().getLOGEnumLiteralDeclaration()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

// Rule OperatorLn
ruleOperatorLn
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getOperatorLnAccess().getLNEnumLiteralDeclaration()); }
		('ln')
		{ after(grammarAccess.getOperatorLnAccess().getLNEnumLiteralDeclaration()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

// Rule OperatorExp
ruleOperatorExp
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getOperatorExpAccess().getEXPEnumLiteralDeclaration()); }
		('exp')
		{ after(grammarAccess.getOperatorExpAccess().getEXPEnumLiteralDeclaration()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

// Rule OperatorLd
ruleOperatorLd
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getOperatorLdAccess().getLDEnumLiteralDeclaration()); }
		('ld')
		{ after(grammarAccess.getOperatorLdAccess().getLDEnumLiteralDeclaration()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__EquationResult__Alternatives
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getEquationResultAccess().getTypeInstanceResultParserRuleCall_0()); }
		ruleTypeInstanceResult
		{ after(grammarAccess.getEquationResultAccess().getTypeInstanceResultParserRuleCall_0()); }
	)
	|
	(
		{ before(grammarAccess.getEquationResultAccess().getEquationIntermediateResultParserRuleCall_1()); }
		ruleEquationIntermediateResult
		{ after(grammarAccess.getEquationResultAccess().getEquationIntermediateResultParserRuleCall_1()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__AdditionAndSubtraction__OperatorAlternatives_1_1_0
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getAdditionAndSubtractionAccess().getOperatorOperatorPlusEnumRuleCall_1_1_0_0()); }
		ruleOperatorPlus
		{ after(grammarAccess.getAdditionAndSubtractionAccess().getOperatorOperatorPlusEnumRuleCall_1_1_0_0()); }
	)
	|
	(
		{ before(grammarAccess.getAdditionAndSubtractionAccess().getOperatorOperatorMinusEnumRuleCall_1_1_0_1()); }
		ruleOperatorMinus
		{ after(grammarAccess.getAdditionAndSubtractionAccess().getOperatorOperatorMinusEnumRuleCall_1_1_0_1()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__MultiplicationAndDivision__OperatorAlternatives_1_1_0
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getMultiplicationAndDivisionAccess().getOperatorOperatorMultiplyEnumRuleCall_1_1_0_0()); }
		ruleOperatorMultiply
		{ after(grammarAccess.getMultiplicationAndDivisionAccess().getOperatorOperatorMultiplyEnumRuleCall_1_1_0_0()); }
	)
	|
	(
		{ before(grammarAccess.getMultiplicationAndDivisionAccess().getOperatorOperatorDivideEnumRuleCall_1_1_0_1()); }
		ruleOperatorDivide
		{ after(grammarAccess.getMultiplicationAndDivisionAccess().getOperatorOperatorDivideEnumRuleCall_1_1_0_1()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__AExpression__Alternatives
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getAExpressionAccess().getALiteralParserRuleCall_0()); }
		ruleALiteral
		{ after(grammarAccess.getAExpressionAccess().getALiteralParserRuleCall_0()); }
	)
	|
	(
		{ before(grammarAccess.getAExpressionAccess().getReferencedInputParserRuleCall_1()); }
		ruleReferencedInput
		{ after(grammarAccess.getAExpressionAccess().getReferencedInputParserRuleCall_1()); }
	)
	|
	(
		{ before(grammarAccess.getAExpressionAccess().getParenthesisParserRuleCall_2()); }
		ruleParenthesis
		{ after(grammarAccess.getAExpressionAccess().getParenthesisParserRuleCall_2()); }
	)
	|
	(
		{ before(grammarAccess.getAExpressionAccess().getFunctionParserRuleCall_3()); }
		ruleFunction
		{ after(grammarAccess.getAExpressionAccess().getFunctionParserRuleCall_3()); }
	)
	|
	(
		{ before(grammarAccess.getAExpressionAccess().getAAdvancedFunctionParserRuleCall_4()); }
		ruleAAdvancedFunction
		{ after(grammarAccess.getAExpressionAccess().getAAdvancedFunctionParserRuleCall_4()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__ALiteral__Alternatives
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getALiteralAccess().getNumberLiteralParserRuleCall_0()); }
		ruleNumberLiteral
		{ after(grammarAccess.getALiteralAccess().getNumberLiteralParserRuleCall_0()); }
	)
	|
	(
		{ before(grammarAccess.getALiteralAccess().getValueEParserRuleCall_1()); }
		ruleValueE
		{ after(grammarAccess.getALiteralAccess().getValueEParserRuleCall_1()); }
	)
	|
	(
		{ before(grammarAccess.getALiteralAccess().getValuePiParserRuleCall_2()); }
		ruleValuePi
		{ after(grammarAccess.getALiteralAccess().getValuePiParserRuleCall_2()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__Function__OperatorAlternatives_1_0
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getFunctionAccess().getOperatorOperatorCosEnumRuleCall_1_0_0()); }
		ruleOperatorCos
		{ after(grammarAccess.getFunctionAccess().getOperatorOperatorCosEnumRuleCall_1_0_0()); }
	)
	|
	(
		{ before(grammarAccess.getFunctionAccess().getOperatorOperatorSinEnumRuleCall_1_0_1()); }
		ruleOperatorSin
		{ after(grammarAccess.getFunctionAccess().getOperatorOperatorSinEnumRuleCall_1_0_1()); }
	)
	|
	(
		{ before(grammarAccess.getFunctionAccess().getOperatorOperatorTanEnumRuleCall_1_0_2()); }
		ruleOperatorTan
		{ after(grammarAccess.getFunctionAccess().getOperatorOperatorTanEnumRuleCall_1_0_2()); }
	)
	|
	(
		{ before(grammarAccess.getFunctionAccess().getOperatorOperatorAtanEnumRuleCall_1_0_3()); }
		ruleOperatorAtan
		{ after(grammarAccess.getFunctionAccess().getOperatorOperatorAtanEnumRuleCall_1_0_3()); }
	)
	|
	(
		{ before(grammarAccess.getFunctionAccess().getOperatorOperatorAcosEnumRuleCall_1_0_4()); }
		ruleOperatorAcos
		{ after(grammarAccess.getFunctionAccess().getOperatorOperatorAcosEnumRuleCall_1_0_4()); }
	)
	|
	(
		{ before(grammarAccess.getFunctionAccess().getOperatorOperatorAsinEnumRuleCall_1_0_5()); }
		ruleOperatorAsin
		{ after(grammarAccess.getFunctionAccess().getOperatorOperatorAsinEnumRuleCall_1_0_5()); }
	)
	|
	(
		{ before(grammarAccess.getFunctionAccess().getOperatorOperatorSqrtEnumRuleCall_1_0_6()); }
		ruleOperatorSqrt
		{ after(grammarAccess.getFunctionAccess().getOperatorOperatorSqrtEnumRuleCall_1_0_6()); }
	)
	|
	(
		{ before(grammarAccess.getFunctionAccess().getOperatorOperatorLogEnumRuleCall_1_0_7()); }
		ruleOperatorLog
		{ after(grammarAccess.getFunctionAccess().getOperatorOperatorLogEnumRuleCall_1_0_7()); }
	)
	|
	(
		{ before(grammarAccess.getFunctionAccess().getOperatorOperatorLnEnumRuleCall_1_0_8()); }
		ruleOperatorLn
		{ after(grammarAccess.getFunctionAccess().getOperatorOperatorLnEnumRuleCall_1_0_8()); }
	)
	|
	(
		{ before(grammarAccess.getFunctionAccess().getOperatorOperatorLdEnumRuleCall_1_0_9()); }
		ruleOperatorLd
		{ after(grammarAccess.getFunctionAccess().getOperatorOperatorLdEnumRuleCall_1_0_9()); }
	)
	|
	(
		{ before(grammarAccess.getFunctionAccess().getOperatorOperatorExpEnumRuleCall_1_0_10()); }
		ruleOperatorExp
		{ after(grammarAccess.getFunctionAccess().getOperatorOperatorExpEnumRuleCall_1_0_10()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__AAdvancedFunction__Alternatives
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getAAdvancedFunctionAccess().getAdvancedFunctionParserRuleCall_0()); }
		ruleAdvancedFunction
		{ after(grammarAccess.getAAdvancedFunctionAccess().getAdvancedFunctionParserRuleCall_0()); }
	)
	|
	(
		{ before(grammarAccess.getAAdvancedFunctionAccess().getSetFunctionParserRuleCall_1()); }
		ruleSetFunction
		{ after(grammarAccess.getAAdvancedFunctionAccess().getSetFunctionParserRuleCall_1()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__EquationSection__Group__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__EquationSection__Group__0__Impl
	rule__EquationSection__Group__1
;
finally {
	restoreStackSize(stackSize);
}

rule__EquationSection__Group__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getEquationSectionAccess().getImportsAssignment_0()); }
	(rule__EquationSection__ImportsAssignment_0)*
	{ after(grammarAccess.getEquationSectionAccess().getImportsAssignment_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__EquationSection__Group__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__EquationSection__Group__1__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__EquationSection__Group__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getEquationSectionAccess().getEquationsAssignment_1()); }
	(rule__EquationSection__EquationsAssignment_1)*
	{ after(grammarAccess.getEquationSectionAccess().getEquationsAssignment_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__Import__Group__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__Import__Group__0__Impl
	rule__Import__Group__1
;
finally {
	restoreStackSize(stackSize);
}

rule__Import__Group__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getImportAccess().getImportKeyword_0()); }
	'Import:'
	{ after(grammarAccess.getImportAccess().getImportKeyword_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__Import__Group__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__Import__Group__1__Impl
	rule__Import__Group__2
;
finally {
	restoreStackSize(stackSize);
}

rule__Import__Group__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getImportAccess().getImportedNamespaceAssignment_1()); }
	(rule__Import__ImportedNamespaceAssignment_1)
	{ after(grammarAccess.getImportAccess().getImportedNamespaceAssignment_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__Import__Group__2
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__Import__Group__2__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__Import__Group__2__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getImportAccess().getSemicolonKeyword_2()); }
	';'
	{ after(grammarAccess.getImportAccess().getSemicolonKeyword_2()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__Equation__Group__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__Equation__Group__0__Impl
	rule__Equation__Group__1
;
finally {
	restoreStackSize(stackSize);
}

rule__Equation__Group__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getEquationAccess().getResultAssignment_0()); }
	(rule__Equation__ResultAssignment_0)
	{ after(grammarAccess.getEquationAccess().getResultAssignment_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__Equation__Group__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__Equation__Group__1__Impl
	rule__Equation__Group__2
;
finally {
	restoreStackSize(stackSize);
}

rule__Equation__Group__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getEquationAccess().getEqualsSignKeyword_1()); }
	'='
	{ after(grammarAccess.getEquationAccess().getEqualsSignKeyword_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__Equation__Group__2
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__Equation__Group__2__Impl
	rule__Equation__Group__3
;
finally {
	restoreStackSize(stackSize);
}

rule__Equation__Group__2__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getEquationAccess().getExpressionAssignment_2()); }
	(rule__Equation__ExpressionAssignment_2)
	{ after(grammarAccess.getEquationAccess().getExpressionAssignment_2()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__Equation__Group__3
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__Equation__Group__3__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__Equation__Group__3__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getEquationAccess().getSemicolonKeyword_3()); }
	';'
	{ after(grammarAccess.getEquationAccess().getSemicolonKeyword_3()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__TypeInstanceResult__Group__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__TypeInstanceResult__Group__0__Impl
	rule__TypeInstanceResult__Group__1
;
finally {
	restoreStackSize(stackSize);
}

rule__TypeInstanceResult__Group__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getTypeInstanceResultAccess().getTypeInstanceResultAction_0()); }
	()
	{ after(grammarAccess.getTypeInstanceResultAccess().getTypeInstanceResultAction_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__TypeInstanceResult__Group__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__TypeInstanceResult__Group__1__Impl
	rule__TypeInstanceResult__Group__2
;
finally {
	restoreStackSize(stackSize);
}

rule__TypeInstanceResult__Group__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getTypeInstanceResultAccess().getRefKeyword_1()); }
	'Ref:'
	{ after(grammarAccess.getTypeInstanceResultAccess().getRefKeyword_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__TypeInstanceResult__Group__2
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__TypeInstanceResult__Group__2__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__TypeInstanceResult__Group__2__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getTypeInstanceResultAccess().getReferenceAssignment_2()); }
	(rule__TypeInstanceResult__ReferenceAssignment_2)
	{ after(grammarAccess.getTypeInstanceResultAccess().getReferenceAssignment_2()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__EquationIntermediateResult__Group__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__EquationIntermediateResult__Group__0__Impl
	rule__EquationIntermediateResult__Group__1
;
finally {
	restoreStackSize(stackSize);
}

rule__EquationIntermediateResult__Group__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getEquationIntermediateResultAccess().getEquationIntermediateResultAction_0()); }
	()
	{ after(grammarAccess.getEquationIntermediateResultAccess().getEquationIntermediateResultAction_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__EquationIntermediateResult__Group__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__EquationIntermediateResult__Group__1__Impl
	rule__EquationIntermediateResult__Group__2
;
finally {
	restoreStackSize(stackSize);
}

rule__EquationIntermediateResult__Group__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getEquationIntermediateResultAccess().getCalcKeyword_1()); }
	'Calc:'
	{ after(grammarAccess.getEquationIntermediateResultAccess().getCalcKeyword_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__EquationIntermediateResult__Group__2
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__EquationIntermediateResult__Group__2__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__EquationIntermediateResult__Group__2__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getEquationIntermediateResultAccess().getNameAssignment_2()); }
	(rule__EquationIntermediateResult__NameAssignment_2)
	{ after(grammarAccess.getEquationIntermediateResultAccess().getNameAssignment_2()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__AdditionAndSubtraction__Group__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__AdditionAndSubtraction__Group__0__Impl
	rule__AdditionAndSubtraction__Group__1
;
finally {
	restoreStackSize(stackSize);
}

rule__AdditionAndSubtraction__Group__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getAdditionAndSubtractionAccess().getMultiplicationAndDivisionParserRuleCall_0()); }
	ruleMultiplicationAndDivision
	{ after(grammarAccess.getAdditionAndSubtractionAccess().getMultiplicationAndDivisionParserRuleCall_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__AdditionAndSubtraction__Group__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__AdditionAndSubtraction__Group__1__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__AdditionAndSubtraction__Group__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getAdditionAndSubtractionAccess().getGroup_1()); }
	(rule__AdditionAndSubtraction__Group_1__0)*
	{ after(grammarAccess.getAdditionAndSubtractionAccess().getGroup_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__AdditionAndSubtraction__Group_1__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__AdditionAndSubtraction__Group_1__0__Impl
	rule__AdditionAndSubtraction__Group_1__1
;
finally {
	restoreStackSize(stackSize);
}

rule__AdditionAndSubtraction__Group_1__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getAdditionAndSubtractionAccess().getAdditionAndSubtractionLeftAction_1_0()); }
	()
	{ after(grammarAccess.getAdditionAndSubtractionAccess().getAdditionAndSubtractionLeftAction_1_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__AdditionAndSubtraction__Group_1__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__AdditionAndSubtraction__Group_1__1__Impl
	rule__AdditionAndSubtraction__Group_1__2
;
finally {
	restoreStackSize(stackSize);
}

rule__AdditionAndSubtraction__Group_1__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getAdditionAndSubtractionAccess().getOperatorAssignment_1_1()); }
	(rule__AdditionAndSubtraction__OperatorAssignment_1_1)
	{ after(grammarAccess.getAdditionAndSubtractionAccess().getOperatorAssignment_1_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__AdditionAndSubtraction__Group_1__2
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__AdditionAndSubtraction__Group_1__2__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__AdditionAndSubtraction__Group_1__2__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getAdditionAndSubtractionAccess().getRightAssignment_1_2()); }
	(rule__AdditionAndSubtraction__RightAssignment_1_2)
	{ after(grammarAccess.getAdditionAndSubtractionAccess().getRightAssignment_1_2()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__MultiplicationAndDivision__Group__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__MultiplicationAndDivision__Group__0__Impl
	rule__MultiplicationAndDivision__Group__1
;
finally {
	restoreStackSize(stackSize);
}

rule__MultiplicationAndDivision__Group__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getMultiplicationAndDivisionAccess().getPowerFunctionParserRuleCall_0()); }
	rulePowerFunction
	{ after(grammarAccess.getMultiplicationAndDivisionAccess().getPowerFunctionParserRuleCall_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__MultiplicationAndDivision__Group__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__MultiplicationAndDivision__Group__1__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__MultiplicationAndDivision__Group__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getMultiplicationAndDivisionAccess().getGroup_1()); }
	(rule__MultiplicationAndDivision__Group_1__0)*
	{ after(grammarAccess.getMultiplicationAndDivisionAccess().getGroup_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__MultiplicationAndDivision__Group_1__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__MultiplicationAndDivision__Group_1__0__Impl
	rule__MultiplicationAndDivision__Group_1__1
;
finally {
	restoreStackSize(stackSize);
}

rule__MultiplicationAndDivision__Group_1__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getMultiplicationAndDivisionAccess().getMultiplicationAndDivisionLeftAction_1_0()); }
	()
	{ after(grammarAccess.getMultiplicationAndDivisionAccess().getMultiplicationAndDivisionLeftAction_1_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__MultiplicationAndDivision__Group_1__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__MultiplicationAndDivision__Group_1__1__Impl
	rule__MultiplicationAndDivision__Group_1__2
;
finally {
	restoreStackSize(stackSize);
}

rule__MultiplicationAndDivision__Group_1__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getMultiplicationAndDivisionAccess().getOperatorAssignment_1_1()); }
	(rule__MultiplicationAndDivision__OperatorAssignment_1_1)
	{ after(grammarAccess.getMultiplicationAndDivisionAccess().getOperatorAssignment_1_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__MultiplicationAndDivision__Group_1__2
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__MultiplicationAndDivision__Group_1__2__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__MultiplicationAndDivision__Group_1__2__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getMultiplicationAndDivisionAccess().getRightAssignment_1_2()); }
	(rule__MultiplicationAndDivision__RightAssignment_1_2)
	{ after(grammarAccess.getMultiplicationAndDivisionAccess().getRightAssignment_1_2()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__PowerFunction__Group__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__PowerFunction__Group__0__Impl
	rule__PowerFunction__Group__1
;
finally {
	restoreStackSize(stackSize);
}

rule__PowerFunction__Group__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getPowerFunctionAccess().getAExpressionParserRuleCall_0()); }
	ruleAExpression
	{ after(grammarAccess.getPowerFunctionAccess().getAExpressionParserRuleCall_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__PowerFunction__Group__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__PowerFunction__Group__1__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__PowerFunction__Group__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getPowerFunctionAccess().getGroup_1()); }
	(rule__PowerFunction__Group_1__0)*
	{ after(grammarAccess.getPowerFunctionAccess().getGroup_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__PowerFunction__Group_1__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__PowerFunction__Group_1__0__Impl
	rule__PowerFunction__Group_1__1
;
finally {
	restoreStackSize(stackSize);
}

rule__PowerFunction__Group_1__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getPowerFunctionAccess().getPowerFunctionLeftAction_1_0()); }
	()
	{ after(grammarAccess.getPowerFunctionAccess().getPowerFunctionLeftAction_1_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__PowerFunction__Group_1__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__PowerFunction__Group_1__1__Impl
	rule__PowerFunction__Group_1__2
;
finally {
	restoreStackSize(stackSize);
}

rule__PowerFunction__Group_1__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getPowerFunctionAccess().getOperatorAssignment_1_1()); }
	(rule__PowerFunction__OperatorAssignment_1_1)
	{ after(grammarAccess.getPowerFunctionAccess().getOperatorAssignment_1_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__PowerFunction__Group_1__2
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__PowerFunction__Group_1__2__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__PowerFunction__Group_1__2__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getPowerFunctionAccess().getRightAssignment_1_2()); }
	(rule__PowerFunction__RightAssignment_1_2)
	{ after(grammarAccess.getPowerFunctionAccess().getRightAssignment_1_2()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__Parenthesis__Group__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__Parenthesis__Group__0__Impl
	rule__Parenthesis__Group__1
;
finally {
	restoreStackSize(stackSize);
}

rule__Parenthesis__Group__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getParenthesisAccess().getParenthesisAction_0()); }
	()
	{ after(grammarAccess.getParenthesisAccess().getParenthesisAction_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__Parenthesis__Group__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__Parenthesis__Group__1__Impl
	rule__Parenthesis__Group__2
;
finally {
	restoreStackSize(stackSize);
}

rule__Parenthesis__Group__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getParenthesisAccess().getOperatorAssignment_1()); }
	(rule__Parenthesis__OperatorAssignment_1)?
	{ after(grammarAccess.getParenthesisAccess().getOperatorAssignment_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__Parenthesis__Group__2
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__Parenthesis__Group__2__Impl
	rule__Parenthesis__Group__3
;
finally {
	restoreStackSize(stackSize);
}

rule__Parenthesis__Group__2__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getParenthesisAccess().getLeftParenthesisKeyword_2()); }
	'('
	{ after(grammarAccess.getParenthesisAccess().getLeftParenthesisKeyword_2()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__Parenthesis__Group__3
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__Parenthesis__Group__3__Impl
	rule__Parenthesis__Group__4
;
finally {
	restoreStackSize(stackSize);
}

rule__Parenthesis__Group__3__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getParenthesisAccess().getRightAssignment_3()); }
	(rule__Parenthesis__RightAssignment_3)
	{ after(grammarAccess.getParenthesisAccess().getRightAssignment_3()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__Parenthesis__Group__4
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__Parenthesis__Group__4__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__Parenthesis__Group__4__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getParenthesisAccess().getRightParenthesisKeyword_4()); }
	')'
	{ after(grammarAccess.getParenthesisAccess().getRightParenthesisKeyword_4()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__Function__Group__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__Function__Group__0__Impl
	rule__Function__Group__1
;
finally {
	restoreStackSize(stackSize);
}

rule__Function__Group__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getFunctionAccess().getFunctionAction_0()); }
	()
	{ after(grammarAccess.getFunctionAccess().getFunctionAction_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__Function__Group__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__Function__Group__1__Impl
	rule__Function__Group__2
;
finally {
	restoreStackSize(stackSize);
}

rule__Function__Group__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getFunctionAccess().getOperatorAssignment_1()); }
	(rule__Function__OperatorAssignment_1)
	{ after(grammarAccess.getFunctionAccess().getOperatorAssignment_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__Function__Group__2
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__Function__Group__2__Impl
	rule__Function__Group__3
;
finally {
	restoreStackSize(stackSize);
}

rule__Function__Group__2__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getFunctionAccess().getLeftParenthesisKeyword_2()); }
	'('
	{ after(grammarAccess.getFunctionAccess().getLeftParenthesisKeyword_2()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__Function__Group__3
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__Function__Group__3__Impl
	rule__Function__Group__4
;
finally {
	restoreStackSize(stackSize);
}

rule__Function__Group__3__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getFunctionAccess().getRightAssignment_3()); }
	(rule__Function__RightAssignment_3)
	{ after(grammarAccess.getFunctionAccess().getRightAssignment_3()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__Function__Group__4
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__Function__Group__4__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__Function__Group__4__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getFunctionAccess().getRightParenthesisKeyword_4()); }
	')'
	{ after(grammarAccess.getFunctionAccess().getRightParenthesisKeyword_4()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__AdvancedFunction__Group__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__AdvancedFunction__Group__0__Impl
	rule__AdvancedFunction__Group__1
;
finally {
	restoreStackSize(stackSize);
}

rule__AdvancedFunction__Group__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getAdvancedFunctionAccess().getAdvancedFunctionAction_0()); }
	()
	{ after(grammarAccess.getAdvancedFunctionAccess().getAdvancedFunctionAction_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__AdvancedFunction__Group__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__AdvancedFunction__Group__1__Impl
	rule__AdvancedFunction__Group__2
;
finally {
	restoreStackSize(stackSize);
}

rule__AdvancedFunction__Group__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getAdvancedFunctionAccess().getOperatorAssignment_1()); }
	(rule__AdvancedFunction__OperatorAssignment_1)
	{ after(grammarAccess.getAdvancedFunctionAccess().getOperatorAssignment_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__AdvancedFunction__Group__2
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__AdvancedFunction__Group__2__Impl
	rule__AdvancedFunction__Group__3
;
finally {
	restoreStackSize(stackSize);
}

rule__AdvancedFunction__Group__2__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getAdvancedFunctionAccess().getLeftParenthesisKeyword_2()); }
	'('
	{ after(grammarAccess.getAdvancedFunctionAccess().getLeftParenthesisKeyword_2()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__AdvancedFunction__Group__3
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__AdvancedFunction__Group__3__Impl
	rule__AdvancedFunction__Group__4
;
finally {
	restoreStackSize(stackSize);
}

rule__AdvancedFunction__Group__3__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getAdvancedFunctionAccess().getInputsAssignment_3()); }
	(rule__AdvancedFunction__InputsAssignment_3)
	{ after(grammarAccess.getAdvancedFunctionAccess().getInputsAssignment_3()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__AdvancedFunction__Group__4
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__AdvancedFunction__Group__4__Impl
	rule__AdvancedFunction__Group__5
;
finally {
	restoreStackSize(stackSize);
}

rule__AdvancedFunction__Group__4__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getAdvancedFunctionAccess().getGroup_4()); }
	(rule__AdvancedFunction__Group_4__0)*
	{ after(grammarAccess.getAdvancedFunctionAccess().getGroup_4()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__AdvancedFunction__Group__5
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__AdvancedFunction__Group__5__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__AdvancedFunction__Group__5__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getAdvancedFunctionAccess().getRightParenthesisKeyword_5()); }
	')'
	{ after(grammarAccess.getAdvancedFunctionAccess().getRightParenthesisKeyword_5()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__AdvancedFunction__Group_4__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__AdvancedFunction__Group_4__0__Impl
	rule__AdvancedFunction__Group_4__1
;
finally {
	restoreStackSize(stackSize);
}

rule__AdvancedFunction__Group_4__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getAdvancedFunctionAccess().getCommaKeyword_4_0()); }
	','
	{ after(grammarAccess.getAdvancedFunctionAccess().getCommaKeyword_4_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__AdvancedFunction__Group_4__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__AdvancedFunction__Group_4__1__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__AdvancedFunction__Group_4__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getAdvancedFunctionAccess().getInputsAssignment_4_1()); }
	(rule__AdvancedFunction__InputsAssignment_4_1)
	{ after(grammarAccess.getAdvancedFunctionAccess().getInputsAssignment_4_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__SetFunction__Group__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__SetFunction__Group__0__Impl
	rule__SetFunction__Group__1
;
finally {
	restoreStackSize(stackSize);
}

rule__SetFunction__Group__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getSetFunctionAccess().getSetFunctionAction_0()); }
	()
	{ after(grammarAccess.getSetFunctionAccess().getSetFunctionAction_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__SetFunction__Group__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__SetFunction__Group__1__Impl
	rule__SetFunction__Group__2
;
finally {
	restoreStackSize(stackSize);
}

rule__SetFunction__Group__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getSetFunctionAccess().getOperatorAssignment_1()); }
	(rule__SetFunction__OperatorAssignment_1)
	{ after(grammarAccess.getSetFunctionAccess().getOperatorAssignment_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__SetFunction__Group__2
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__SetFunction__Group__2__Impl
	rule__SetFunction__Group__3
;
finally {
	restoreStackSize(stackSize);
}

rule__SetFunction__Group__2__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getSetFunctionAccess().getLeftCurlyBracketKeyword_2()); }
	'{'
	{ after(grammarAccess.getSetFunctionAccess().getLeftCurlyBracketKeyword_2()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__SetFunction__Group__3
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__SetFunction__Group__3__Impl
	rule__SetFunction__Group__4
;
finally {
	restoreStackSize(stackSize);
}

rule__SetFunction__Group__3__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getSetFunctionAccess().getTypeDefinitionAssignment_3()); }
	(rule__SetFunction__TypeDefinitionAssignment_3)
	{ after(grammarAccess.getSetFunctionAccess().getTypeDefinitionAssignment_3()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__SetFunction__Group__4
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__SetFunction__Group__4__Impl
	rule__SetFunction__Group__5
;
finally {
	restoreStackSize(stackSize);
}

rule__SetFunction__Group__4__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getSetFunctionAccess().getGroup_4()); }
	(rule__SetFunction__Group_4__0)?
	{ after(grammarAccess.getSetFunctionAccess().getGroup_4()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__SetFunction__Group__5
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__SetFunction__Group__5__Impl
	rule__SetFunction__Group__6
;
finally {
	restoreStackSize(stackSize);
}

rule__SetFunction__Group__5__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getSetFunctionAccess().getGroup_5()); }
	(rule__SetFunction__Group_5__0)?
	{ after(grammarAccess.getSetFunctionAccess().getGroup_5()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__SetFunction__Group__6
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__SetFunction__Group__6__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__SetFunction__Group__6__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getSetFunctionAccess().getRightCurlyBracketKeyword_6()); }
	'}'
	{ after(grammarAccess.getSetFunctionAccess().getRightCurlyBracketKeyword_6()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__SetFunction__Group_4__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__SetFunction__Group_4__0__Impl
	rule__SetFunction__Group_4__1
;
finally {
	restoreStackSize(stackSize);
}

rule__SetFunction__Group_4__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getSetFunctionAccess().getCommaKeyword_4_0()); }
	','
	{ after(grammarAccess.getSetFunctionAccess().getCommaKeyword_4_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__SetFunction__Group_4__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__SetFunction__Group_4__1__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__SetFunction__Group_4__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getSetFunctionAccess().getDepthAssignment_4_1()); }
	(rule__SetFunction__DepthAssignment_4_1)
	{ after(grammarAccess.getSetFunctionAccess().getDepthAssignment_4_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__SetFunction__Group_5__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__SetFunction__Group_5__0__Impl
	rule__SetFunction__Group_5__1
;
finally {
	restoreStackSize(stackSize);
}

rule__SetFunction__Group_5__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getSetFunctionAccess().getCommaKeyword_5_0()); }
	','
	{ after(grammarAccess.getSetFunctionAccess().getCommaKeyword_5_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__SetFunction__Group_5__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__SetFunction__Group_5__1__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__SetFunction__Group_5__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getSetFunctionAccess().getFilterNameAssignment_5_1()); }
	(rule__SetFunction__FilterNameAssignment_5_1)
	{ after(grammarAccess.getSetFunctionAccess().getFilterNameAssignment_5_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__NumberLiteral__Group__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__NumberLiteral__Group__0__Impl
	rule__NumberLiteral__Group__1
;
finally {
	restoreStackSize(stackSize);
}

rule__NumberLiteral__Group__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getNumberLiteralAccess().getNumberLiteralAction_0()); }
	()
	{ after(grammarAccess.getNumberLiteralAccess().getNumberLiteralAction_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__NumberLiteral__Group__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__NumberLiteral__Group__1__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__NumberLiteral__Group__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getNumberLiteralAccess().getValueAssignment_1()); }
	(rule__NumberLiteral__ValueAssignment_1)
	{ after(grammarAccess.getNumberLiteralAccess().getValueAssignment_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__QualifiedName__Group__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__QualifiedName__Group__0__Impl
	rule__QualifiedName__Group__1
;
finally {
	restoreStackSize(stackSize);
}

rule__QualifiedName__Group__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getQualifiedNameAccess().getIDTerminalRuleCall_0()); }
	RULE_ID
	{ after(grammarAccess.getQualifiedNameAccess().getIDTerminalRuleCall_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__QualifiedName__Group__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__QualifiedName__Group__1__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__QualifiedName__Group__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getQualifiedNameAccess().getGroup_1()); }
	(rule__QualifiedName__Group_1__0)*
	{ after(grammarAccess.getQualifiedNameAccess().getGroup_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__QualifiedName__Group_1__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__QualifiedName__Group_1__0__Impl
	rule__QualifiedName__Group_1__1
;
finally {
	restoreStackSize(stackSize);
}

rule__QualifiedName__Group_1__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getQualifiedNameAccess().getFullStopKeyword_1_0()); }
	'.'
	{ after(grammarAccess.getQualifiedNameAccess().getFullStopKeyword_1_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__QualifiedName__Group_1__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__QualifiedName__Group_1__1__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__QualifiedName__Group_1__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getQualifiedNameAccess().getIDTerminalRuleCall_1_1()); }
	RULE_ID
	{ after(grammarAccess.getQualifiedNameAccess().getIDTerminalRuleCall_1_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__NumberLiteralString__Group__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__NumberLiteralString__Group__0__Impl
	rule__NumberLiteralString__Group__1
;
finally {
	restoreStackSize(stackSize);
}

rule__NumberLiteralString__Group__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getNumberLiteralStringAccess().getHyphenMinusKeyword_0()); }
	('-')?
	{ after(grammarAccess.getNumberLiteralStringAccess().getHyphenMinusKeyword_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__NumberLiteralString__Group__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__NumberLiteralString__Group__1__Impl
	rule__NumberLiteralString__Group__2
;
finally {
	restoreStackSize(stackSize);
}

rule__NumberLiteralString__Group__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getNumberLiteralStringAccess().getINTTerminalRuleCall_1()); }
	RULE_INT
	{ after(grammarAccess.getNumberLiteralStringAccess().getINTTerminalRuleCall_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__NumberLiteralString__Group__2
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__NumberLiteralString__Group__2__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__NumberLiteralString__Group__2__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getNumberLiteralStringAccess().getGroup_2()); }
	(rule__NumberLiteralString__Group_2__0)?
	{ after(grammarAccess.getNumberLiteralStringAccess().getGroup_2()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__NumberLiteralString__Group_2__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__NumberLiteralString__Group_2__0__Impl
	rule__NumberLiteralString__Group_2__1
;
finally {
	restoreStackSize(stackSize);
}

rule__NumberLiteralString__Group_2__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getNumberLiteralStringAccess().getFullStopKeyword_2_0()); }
	'.'
	{ after(grammarAccess.getNumberLiteralStringAccess().getFullStopKeyword_2_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__NumberLiteralString__Group_2__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__NumberLiteralString__Group_2__1__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__NumberLiteralString__Group_2__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getNumberLiteralStringAccess().getINTTerminalRuleCall_2_1()); }
	RULE_INT
	{ after(grammarAccess.getNumberLiteralStringAccess().getINTTerminalRuleCall_2_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__ValuePi__Group__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__ValuePi__Group__0__Impl
	rule__ValuePi__Group__1
;
finally {
	restoreStackSize(stackSize);
}

rule__ValuePi__Group__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getValuePiAccess().getValuePiAction_0()); }
	()
	{ after(grammarAccess.getValuePiAccess().getValuePiAction_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__ValuePi__Group__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__ValuePi__Group__1__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__ValuePi__Group__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getValuePiAccess().getPiKeyword_1()); }
	'pi'
	{ after(grammarAccess.getValuePiAccess().getPiKeyword_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__ValueE__Group__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__ValueE__Group__0__Impl
	rule__ValueE__Group__1
;
finally {
	restoreStackSize(stackSize);
}

rule__ValueE__Group__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getValueEAccess().getValueEAction_0()); }
	()
	{ after(grammarAccess.getValueEAccess().getValueEAction_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__ValueE__Group__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__ValueE__Group__1__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__ValueE__Group__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getValueEAccess().getEKeyword_1()); }
	'e'
	{ after(grammarAccess.getValueEAccess().getEKeyword_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__EquationSection__ImportsAssignment_0
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getEquationSectionAccess().getImportsImportParserRuleCall_0_0()); }
		ruleImport
		{ after(grammarAccess.getEquationSectionAccess().getImportsImportParserRuleCall_0_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__EquationSection__EquationsAssignment_1
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getEquationSectionAccess().getEquationsEquationParserRuleCall_1_0()); }
		ruleEquation
		{ after(grammarAccess.getEquationSectionAccess().getEquationsEquationParserRuleCall_1_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__Import__ImportedNamespaceAssignment_1
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getImportAccess().getImportedNamespaceIInstanceCrossReference_1_0()); }
		(
			{ before(grammarAccess.getImportAccess().getImportedNamespaceIInstanceQualifiedNameParserRuleCall_1_0_1()); }
			ruleQualifiedName
			{ after(grammarAccess.getImportAccess().getImportedNamespaceIInstanceQualifiedNameParserRuleCall_1_0_1()); }
		)
		{ after(grammarAccess.getImportAccess().getImportedNamespaceIInstanceCrossReference_1_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__Equation__ResultAssignment_0
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getEquationAccess().getResultEquationResultParserRuleCall_0_0()); }
		ruleEquationResult
		{ after(grammarAccess.getEquationAccess().getResultEquationResultParserRuleCall_0_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__Equation__ExpressionAssignment_2
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getEquationAccess().getExpressionAdditionAndSubtractionParserRuleCall_2_0()); }
		ruleAdditionAndSubtraction
		{ after(grammarAccess.getEquationAccess().getExpressionAdditionAndSubtractionParserRuleCall_2_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__TypeInstanceResult__ReferenceAssignment_2
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getTypeInstanceResultAccess().getReferenceATypeInstanceCrossReference_2_0()); }
		(
			{ before(grammarAccess.getTypeInstanceResultAccess().getReferenceATypeInstanceQualifiedNameParserRuleCall_2_0_1()); }
			ruleQualifiedName
			{ after(grammarAccess.getTypeInstanceResultAccess().getReferenceATypeInstanceQualifiedNameParserRuleCall_2_0_1()); }
		)
		{ after(grammarAccess.getTypeInstanceResultAccess().getReferenceATypeInstanceCrossReference_2_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__EquationIntermediateResult__NameAssignment_2
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getEquationIntermediateResultAccess().getNameIDTerminalRuleCall_2_0()); }
		RULE_ID
		{ after(grammarAccess.getEquationIntermediateResultAccess().getNameIDTerminalRuleCall_2_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__AdditionAndSubtraction__OperatorAssignment_1_1
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getAdditionAndSubtractionAccess().getOperatorAlternatives_1_1_0()); }
		(rule__AdditionAndSubtraction__OperatorAlternatives_1_1_0)
		{ after(grammarAccess.getAdditionAndSubtractionAccess().getOperatorAlternatives_1_1_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__AdditionAndSubtraction__RightAssignment_1_2
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getAdditionAndSubtractionAccess().getRightMultiplicationAndDivisionParserRuleCall_1_2_0()); }
		ruleMultiplicationAndDivision
		{ after(grammarAccess.getAdditionAndSubtractionAccess().getRightMultiplicationAndDivisionParserRuleCall_1_2_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__MultiplicationAndDivision__OperatorAssignment_1_1
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getMultiplicationAndDivisionAccess().getOperatorAlternatives_1_1_0()); }
		(rule__MultiplicationAndDivision__OperatorAlternatives_1_1_0)
		{ after(grammarAccess.getMultiplicationAndDivisionAccess().getOperatorAlternatives_1_1_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__MultiplicationAndDivision__RightAssignment_1_2
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getMultiplicationAndDivisionAccess().getRightPowerFunctionParserRuleCall_1_2_0()); }
		rulePowerFunction
		{ after(grammarAccess.getMultiplicationAndDivisionAccess().getRightPowerFunctionParserRuleCall_1_2_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__PowerFunction__OperatorAssignment_1_1
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getPowerFunctionAccess().getOperatorOperatorPowerEnumRuleCall_1_1_0()); }
		ruleOperatorPower
		{ after(grammarAccess.getPowerFunctionAccess().getOperatorOperatorPowerEnumRuleCall_1_1_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__PowerFunction__RightAssignment_1_2
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getPowerFunctionAccess().getRightAExpressionParserRuleCall_1_2_0()); }
		ruleAExpression
		{ after(grammarAccess.getPowerFunctionAccess().getRightAExpressionParserRuleCall_1_2_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__Parenthesis__OperatorAssignment_1
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getParenthesisAccess().getOperatorOperatorMinusEnumRuleCall_1_0()); }
		ruleOperatorMinus
		{ after(grammarAccess.getParenthesisAccess().getOperatorOperatorMinusEnumRuleCall_1_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__Parenthesis__RightAssignment_3
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getParenthesisAccess().getRightAdditionAndSubtractionParserRuleCall_3_0()); }
		ruleAdditionAndSubtraction
		{ after(grammarAccess.getParenthesisAccess().getRightAdditionAndSubtractionParserRuleCall_3_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__ReferencedInput__ReferenceAssignment
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getReferencedInputAccess().getReferenceIEquationInputCrossReference_0()); }
		(
			{ before(grammarAccess.getReferencedInputAccess().getReferenceIEquationInputQualifiedNameParserRuleCall_0_1()); }
			ruleQualifiedName
			{ after(grammarAccess.getReferencedInputAccess().getReferenceIEquationInputQualifiedNameParserRuleCall_0_1()); }
		)
		{ after(grammarAccess.getReferencedInputAccess().getReferenceIEquationInputCrossReference_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__Function__OperatorAssignment_1
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getFunctionAccess().getOperatorAlternatives_1_0()); }
		(rule__Function__OperatorAlternatives_1_0)
		{ after(grammarAccess.getFunctionAccess().getOperatorAlternatives_1_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__Function__RightAssignment_3
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getFunctionAccess().getRightAdditionAndSubtractionParserRuleCall_3_0()); }
		ruleAdditionAndSubtraction
		{ after(grammarAccess.getFunctionAccess().getRightAdditionAndSubtractionParserRuleCall_3_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__AdvancedFunction__OperatorAssignment_1
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getAdvancedFunctionAccess().getOperatorIDTerminalRuleCall_1_0()); }
		RULE_ID
		{ after(grammarAccess.getAdvancedFunctionAccess().getOperatorIDTerminalRuleCall_1_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__AdvancedFunction__InputsAssignment_3
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getAdvancedFunctionAccess().getInputsAdditionAndSubtractionParserRuleCall_3_0()); }
		ruleAdditionAndSubtraction
		{ after(grammarAccess.getAdvancedFunctionAccess().getInputsAdditionAndSubtractionParserRuleCall_3_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__AdvancedFunction__InputsAssignment_4_1
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getAdvancedFunctionAccess().getInputsAdditionAndSubtractionParserRuleCall_4_1_0()); }
		ruleAdditionAndSubtraction
		{ after(grammarAccess.getAdvancedFunctionAccess().getInputsAdditionAndSubtractionParserRuleCall_4_1_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__SetFunction__OperatorAssignment_1
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getSetFunctionAccess().getOperatorIDTerminalRuleCall_1_0()); }
		RULE_ID
		{ after(grammarAccess.getSetFunctionAccess().getOperatorIDTerminalRuleCall_1_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__SetFunction__TypeDefinitionAssignment_3
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getSetFunctionAccess().getTypeDefinitionATypeDefinitionCrossReference_3_0()); }
		(
			{ before(grammarAccess.getSetFunctionAccess().getTypeDefinitionATypeDefinitionQualifiedNameParserRuleCall_3_0_1()); }
			ruleQualifiedName
			{ after(grammarAccess.getSetFunctionAccess().getTypeDefinitionATypeDefinitionQualifiedNameParserRuleCall_3_0_1()); }
		)
		{ after(grammarAccess.getSetFunctionAccess().getTypeDefinitionATypeDefinitionCrossReference_3_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__SetFunction__DepthAssignment_4_1
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getSetFunctionAccess().getDepthINTTerminalRuleCall_4_1_0()); }
		RULE_INT
		{ after(grammarAccess.getSetFunctionAccess().getDepthINTTerminalRuleCall_4_1_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__SetFunction__FilterNameAssignment_5_1
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getSetFunctionAccess().getFilterNameIDTerminalRuleCall_5_1_0()); }
		RULE_ID
		{ after(grammarAccess.getSetFunctionAccess().getFilterNameIDTerminalRuleCall_5_1_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__NumberLiteral__ValueAssignment_1
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getNumberLiteralAccess().getValueNumberLiteralStringParserRuleCall_1_0()); }
		ruleNumberLiteralString
		{ after(grammarAccess.getNumberLiteralAccess().getValueNumberLiteralStringParserRuleCall_1_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

RULE_ID : '^'? ('a'..'z'|'A'..'Z'|'_') ('a'..'z'|'A'..'Z'|'_'|'0'..'9')*;

RULE_INT : ('0'..'9')+;

RULE_STRING : ('"' ('\\' .|~(('\\'|'"')))* '"'|'\'' ('\\' .|~(('\\'|'\'')))* '\'');

RULE_ML_COMMENT : '/*' ( options {greedy=false;} : . )*'*/';

RULE_SL_COMMENT : '//' ~(('\n'|'\r'))* ('\r'? '\n')?;

RULE_WS : (' '|'\t'|'\r'|'\n')+;

RULE_ANY_OTHER : .;
