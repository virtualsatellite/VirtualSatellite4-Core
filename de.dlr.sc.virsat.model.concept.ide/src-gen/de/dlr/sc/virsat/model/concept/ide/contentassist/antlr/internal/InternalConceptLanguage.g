/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
grammar InternalConceptLanguage;

options {
	superClass=AbstractInternalContentAssistParser;
}

@lexer::header {
package de.dlr.sc.virsat.model.concept.ide.contentassist.antlr.internal;

// Hack: Use our own Lexer superclass by means of import. 
// Currently there is no other way to specify the superclass for the lexer.
import org.eclipse.xtext.ide.editor.contentassist.antlr.internal.Lexer;
}

@parser::header {
package de.dlr.sc.virsat.model.concept.ide.contentassist.antlr.internal;

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
import de.dlr.sc.virsat.model.concept.services.ConceptLanguageGrammarAccess;

}
@parser::members {
	private ConceptLanguageGrammarAccess grammarAccess;

	public void setGrammarAccess(ConceptLanguageGrammarAccess grammarAccess) {
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

// Entry rule entryRuleConcept
entryRuleConcept
:
{ before(grammarAccess.getConceptRule()); }
	 ruleConcept
{ after(grammarAccess.getConceptRule()); } 
	 EOF 
;

// Rule Concept
ruleConcept 
	@init {
		int stackSize = keepStackSize();
	}
	:
	(
		{ before(grammarAccess.getConceptAccess().getGroup()); }
		(rule__Concept__Group__0)
		{ after(grammarAccess.getConceptAccess().getGroup()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

// Entry rule entryRuleStructuralElement
entryRuleStructuralElement
:
{ before(grammarAccess.getStructuralElementRule()); }
	 ruleStructuralElement
{ after(grammarAccess.getStructuralElementRule()); } 
	 EOF 
;

// Rule StructuralElement
ruleStructuralElement 
	@init {
		int stackSize = keepStackSize();
	}
	:
	(
		{ before(grammarAccess.getStructuralElementAccess().getGroup()); }
		(rule__StructuralElement__Group__0)
		{ after(grammarAccess.getStructuralElementAccess().getGroup()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

// Entry rule entryRuleGeneralRelation
entryRuleGeneralRelation
:
{ before(grammarAccess.getGeneralRelationRule()); }
	 ruleGeneralRelation
{ after(grammarAccess.getGeneralRelationRule()); } 
	 EOF 
;

// Rule GeneralRelation
ruleGeneralRelation 
	@init {
		int stackSize = keepStackSize();
	}
	:
	(
		{ before(grammarAccess.getGeneralRelationAccess().getGroup()); }
		(rule__GeneralRelation__Group__0)
		{ after(grammarAccess.getGeneralRelationAccess().getGroup()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

// Entry rule entryRuleARelation
entryRuleARelation
:
{ before(grammarAccess.getARelationRule()); }
	 ruleARelation
{ after(grammarAccess.getARelationRule()); } 
	 EOF 
;

// Rule ARelation
ruleARelation 
	@init {
		int stackSize = keepStackSize();
	}
	:
	(
		{ before(grammarAccess.getARelationAccess().getGeneralRelationParserRuleCall()); }
		ruleGeneralRelation
		{ after(grammarAccess.getARelationAccess().getGeneralRelationParserRuleCall()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

// Entry rule entryRuleCategory
entryRuleCategory
:
{ before(grammarAccess.getCategoryRule()); }
	 ruleCategory
{ after(grammarAccess.getCategoryRule()); } 
	 EOF 
;

// Rule Category
ruleCategory 
	@init {
		int stackSize = keepStackSize();
	}
	:
	(
		{ before(grammarAccess.getCategoryAccess().getGroup()); }
		(rule__Category__Group__0)
		{ after(grammarAccess.getCategoryAccess().getGroup()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

// Entry rule entryRuleConceptImport
entryRuleConceptImport
:
{ before(grammarAccess.getConceptImportRule()); }
	 ruleConceptImport
{ after(grammarAccess.getConceptImportRule()); } 
	 EOF 
;

// Rule ConceptImport
ruleConceptImport 
	@init {
		int stackSize = keepStackSize();
	}
	:
	(
		{ before(grammarAccess.getConceptImportAccess().getGroup()); }
		(rule__ConceptImport__Group__0)
		{ after(grammarAccess.getConceptImportAccess().getGroup()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

// Entry rule entryRuleEcoreImport
entryRuleEcoreImport
:
{ before(grammarAccess.getEcoreImportRule()); }
	 ruleEcoreImport
{ after(grammarAccess.getEcoreImportRule()); } 
	 EOF 
;

// Rule EcoreImport
ruleEcoreImport 
	@init {
		int stackSize = keepStackSize();
	}
	:
	(
		{ before(grammarAccess.getEcoreImportAccess().getGroup()); }
		(rule__EcoreImport__Group__0)
		{ after(grammarAccess.getEcoreImportAccess().getGroup()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

// Entry rule entryRuleAProperty
entryRuleAProperty
:
{ before(grammarAccess.getAPropertyRule()); }
	 ruleAProperty
{ after(grammarAccess.getAPropertyRule()); } 
	 EOF 
;

// Rule AProperty
ruleAProperty 
	@init {
		int stackSize = keepStackSize();
	}
	:
	(
		{ before(grammarAccess.getAPropertyAccess().getAlternatives()); }
		(rule__AProperty__Alternatives)
		{ after(grammarAccess.getAPropertyAccess().getAlternatives()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

// Entry rule entryRuleArrayModifier
entryRuleArrayModifier
:
{ before(grammarAccess.getArrayModifierRule()); }
	 ruleArrayModifier
{ after(grammarAccess.getArrayModifierRule()); } 
	 EOF 
;

// Rule ArrayModifier
ruleArrayModifier 
	@init {
		int stackSize = keepStackSize();
	}
	:
	(
		{ before(grammarAccess.getArrayModifierAccess().getAlternatives()); }
		(rule__ArrayModifier__Alternatives)
		{ after(grammarAccess.getArrayModifierAccess().getAlternatives()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

// Entry rule entryRuleDynmaicArrayModifier
entryRuleDynmaicArrayModifier
:
{ before(grammarAccess.getDynmaicArrayModifierRule()); }
	 ruleDynmaicArrayModifier
{ after(grammarAccess.getDynmaicArrayModifierRule()); } 
	 EOF 
;

// Rule DynmaicArrayModifier
ruleDynmaicArrayModifier 
	@init {
		int stackSize = keepStackSize();
	}
	:
	(
		{ before(grammarAccess.getDynmaicArrayModifierAccess().getGroup()); }
		(rule__DynmaicArrayModifier__Group__0)
		{ after(grammarAccess.getDynmaicArrayModifierAccess().getGroup()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

// Entry rule entryRuleStaticArrayModifier
entryRuleStaticArrayModifier
:
{ before(grammarAccess.getStaticArrayModifierRule()); }
	 ruleStaticArrayModifier
{ after(grammarAccess.getStaticArrayModifierRule()); } 
	 EOF 
;

// Rule StaticArrayModifier
ruleStaticArrayModifier 
	@init {
		int stackSize = keepStackSize();
	}
	:
	(
		{ before(grammarAccess.getStaticArrayModifierAccess().getGroup()); }
		(rule__StaticArrayModifier__Group__0)
		{ after(grammarAccess.getStaticArrayModifierAccess().getGroup()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

// Entry rule entryRuleComposedProperty
entryRuleComposedProperty
:
{ before(grammarAccess.getComposedPropertyRule()); }
	 ruleComposedProperty
{ after(grammarAccess.getComposedPropertyRule()); } 
	 EOF 
;

// Rule ComposedProperty
ruleComposedProperty 
	@init {
		int stackSize = keepStackSize();
	}
	:
	(
		{ before(grammarAccess.getComposedPropertyAccess().getGroup()); }
		(rule__ComposedProperty__Group__0)
		{ after(grammarAccess.getComposedPropertyAccess().getGroup()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

// Entry rule entryRuleIntProperty
entryRuleIntProperty
:
{ before(grammarAccess.getIntPropertyRule()); }
	 ruleIntProperty
{ after(grammarAccess.getIntPropertyRule()); } 
	 EOF 
;

// Rule IntProperty
ruleIntProperty 
	@init {
		int stackSize = keepStackSize();
	}
	:
	(
		{ before(grammarAccess.getIntPropertyAccess().getGroup()); }
		(rule__IntProperty__Group__0)
		{ after(grammarAccess.getIntPropertyAccess().getGroup()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

// Entry rule entryRuleFloatProperty
entryRuleFloatProperty
:
{ before(grammarAccess.getFloatPropertyRule()); }
	 ruleFloatProperty
{ after(grammarAccess.getFloatPropertyRule()); } 
	 EOF 
;

// Rule FloatProperty
ruleFloatProperty 
	@init {
		int stackSize = keepStackSize();
	}
	:
	(
		{ before(grammarAccess.getFloatPropertyAccess().getGroup()); }
		(rule__FloatProperty__Group__0)
		{ after(grammarAccess.getFloatPropertyAccess().getGroup()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

// Entry rule entryRuleStringProperty
entryRuleStringProperty
:
{ before(grammarAccess.getStringPropertyRule()); }
	 ruleStringProperty
{ after(grammarAccess.getStringPropertyRule()); } 
	 EOF 
;

// Rule StringProperty
ruleStringProperty 
	@init {
		int stackSize = keepStackSize();
	}
	:
	(
		{ before(grammarAccess.getStringPropertyAccess().getGroup()); }
		(rule__StringProperty__Group__0)
		{ after(grammarAccess.getStringPropertyAccess().getGroup()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

// Entry rule entryRuleBooleanProperty
entryRuleBooleanProperty
:
{ before(grammarAccess.getBooleanPropertyRule()); }
	 ruleBooleanProperty
{ after(grammarAccess.getBooleanPropertyRule()); } 
	 EOF 
;

// Rule BooleanProperty
ruleBooleanProperty 
	@init {
		int stackSize = keepStackSize();
	}
	:
	(
		{ before(grammarAccess.getBooleanPropertyAccess().getGroup()); }
		(rule__BooleanProperty__Group__0)
		{ after(grammarAccess.getBooleanPropertyAccess().getGroup()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

// Entry rule entryRuleEnumProperty
entryRuleEnumProperty
:
{ before(grammarAccess.getEnumPropertyRule()); }
	 ruleEnumProperty
{ after(grammarAccess.getEnumPropertyRule()); } 
	 EOF 
;

// Rule EnumProperty
ruleEnumProperty 
	@init {
		int stackSize = keepStackSize();
	}
	:
	(
		{ before(grammarAccess.getEnumPropertyAccess().getGroup()); }
		(rule__EnumProperty__Group__0)
		{ after(grammarAccess.getEnumPropertyAccess().getGroup()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

// Entry rule entryRuleEnumValueDefinition
entryRuleEnumValueDefinition
:
{ before(grammarAccess.getEnumValueDefinitionRule()); }
	 ruleEnumValueDefinition
{ after(grammarAccess.getEnumValueDefinitionRule()); } 
	 EOF 
;

// Rule EnumValueDefinition
ruleEnumValueDefinition 
	@init {
		int stackSize = keepStackSize();
	}
	:
	(
		{ before(grammarAccess.getEnumValueDefinitionAccess().getGroup()); }
		(rule__EnumValueDefinition__Group__0)
		{ after(grammarAccess.getEnumValueDefinitionAccess().getGroup()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

// Entry rule entryRuleReferenceProperty
entryRuleReferenceProperty
:
{ before(grammarAccess.getReferencePropertyRule()); }
	 ruleReferenceProperty
{ after(grammarAccess.getReferencePropertyRule()); } 
	 EOF 
;

// Rule ReferenceProperty
ruleReferenceProperty 
	@init {
		int stackSize = keepStackSize();
	}
	:
	(
		{ before(grammarAccess.getReferencePropertyAccess().getGroup()); }
		(rule__ReferenceProperty__Group__0)
		{ after(grammarAccess.getReferencePropertyAccess().getGroup()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

// Entry rule entryRuleEReferenceProperty
entryRuleEReferenceProperty
:
{ before(grammarAccess.getEReferencePropertyRule()); }
	 ruleEReferenceProperty
{ after(grammarAccess.getEReferencePropertyRule()); } 
	 EOF 
;

// Rule EReferenceProperty
ruleEReferenceProperty 
	@init {
		int stackSize = keepStackSize();
	}
	:
	(
		{ before(grammarAccess.getEReferencePropertyAccess().getGroup()); }
		(rule__EReferenceProperty__Group__0)
		{ after(grammarAccess.getEReferencePropertyAccess().getGroup()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

// Entry rule entryRuleResourceProperty
entryRuleResourceProperty
:
{ before(grammarAccess.getResourcePropertyRule()); }
	 ruleResourceProperty
{ after(grammarAccess.getResourcePropertyRule()); } 
	 EOF 
;

// Rule ResourceProperty
ruleResourceProperty 
	@init {
		int stackSize = keepStackSize();
	}
	:
	(
		{ before(grammarAccess.getResourcePropertyAccess().getGroup()); }
		(rule__ResourceProperty__Group__0)
		{ after(grammarAccess.getResourcePropertyAccess().getGroup()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

// Entry rule entryRuleEquationDefinition
entryRuleEquationDefinition
:
{ before(grammarAccess.getEquationDefinitionRule()); }
	 ruleEquationDefinition
{ after(grammarAccess.getEquationDefinitionRule()); } 
	 EOF 
;

// Rule EquationDefinition
ruleEquationDefinition 
	@init {
		int stackSize = keepStackSize();
	}
	:
	(
		{ before(grammarAccess.getEquationDefinitionAccess().getGroup()); }
		(rule__EquationDefinition__Group__0)
		{ after(grammarAccess.getEquationDefinitionAccess().getGroup()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

// Entry rule entryRuleEquationDefinitionResult
entryRuleEquationDefinitionResult
:
{ before(grammarAccess.getEquationDefinitionResultRule()); }
	 ruleEquationDefinitionResult
{ after(grammarAccess.getEquationDefinitionResultRule()); } 
	 EOF 
;

// Rule EquationDefinitionResult
ruleEquationDefinitionResult 
	@init {
		int stackSize = keepStackSize();
	}
	:
	(
		{ before(grammarAccess.getEquationDefinitionResultAccess().getAlternatives()); }
		(rule__EquationDefinitionResult__Alternatives)
		{ after(grammarAccess.getEquationDefinitionResultAccess().getAlternatives()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

// Entry rule entryRuleTypeDefinitionResult
entryRuleTypeDefinitionResult
:
{ before(grammarAccess.getTypeDefinitionResultRule()); }
	 ruleTypeDefinitionResult
{ after(grammarAccess.getTypeDefinitionResultRule()); } 
	 EOF 
;

// Rule TypeDefinitionResult
ruleTypeDefinitionResult 
	@init {
		int stackSize = keepStackSize();
	}
	:
	(
		{ before(grammarAccess.getTypeDefinitionResultAccess().getGroup()); }
		(rule__TypeDefinitionResult__Group__0)
		{ after(grammarAccess.getTypeDefinitionResultAccess().getGroup()); }
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

// Entry rule entryRuleReferencedDefinitionInput
entryRuleReferencedDefinitionInput
:
{ before(grammarAccess.getReferencedDefinitionInputRule()); }
	 ruleReferencedDefinitionInput
{ after(grammarAccess.getReferencedDefinitionInputRule()); } 
	 EOF 
;

// Rule ReferencedDefinitionInput
ruleReferencedDefinitionInput 
	@init {
		int stackSize = keepStackSize();
	}
	:
	(
		{ before(grammarAccess.getReferencedDefinitionInputAccess().getReferenceAssignment()); }
		(rule__ReferencedDefinitionInput__ReferenceAssignment)
		{ after(grammarAccess.getReferencedDefinitionInputAccess().getReferenceAssignment()); }
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

// Entry rule entryRuleVersion
entryRuleVersion
:
{ before(grammarAccess.getVersionRule()); }
	 ruleVersion
{ after(grammarAccess.getVersionRule()); } 
	 EOF 
;

// Rule Version
ruleVersion 
	@init {
		int stackSize = keepStackSize();
	}
	:
	(
		{ before(grammarAccess.getVersionAccess().getGroup()); }
		(rule__Version__Group__0)
		{ after(grammarAccess.getVersionAccess().getGroup()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

// Entry rule entryRuleIntLiteralString
entryRuleIntLiteralString
:
{ before(grammarAccess.getIntLiteralStringRule()); }
	 ruleIntLiteralString
{ after(grammarAccess.getIntLiteralStringRule()); } 
	 EOF 
;

// Rule IntLiteralString
ruleIntLiteralString 
	@init {
		int stackSize = keepStackSize();
	}
	:
	(
		{ before(grammarAccess.getIntLiteralStringAccess().getGroup()); }
		(rule__IntLiteralString__Group__0)
		{ after(grammarAccess.getIntLiteralStringAccess().getGroup()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

// Entry rule entryRuleFloatLiteralString
entryRuleFloatLiteralString
:
{ before(grammarAccess.getFloatLiteralStringRule()); }
	 ruleFloatLiteralString
{ after(grammarAccess.getFloatLiteralStringRule()); } 
	 EOF 
;

// Rule FloatLiteralString
ruleFloatLiteralString 
	@init {
		int stackSize = keepStackSize();
	}
	:
	(
		{ before(grammarAccess.getFloatLiteralStringAccess().getGroup()); }
		(rule__FloatLiteralString__Group__0)
		{ after(grammarAccess.getFloatLiteralStringAccess().getGroup()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

// Entry rule entryRuleBooleanLiteralString
entryRuleBooleanLiteralString
:
{ before(grammarAccess.getBooleanLiteralStringRule()); }
	 ruleBooleanLiteralString
{ after(grammarAccess.getBooleanLiteralStringRule()); } 
	 EOF 
;

// Rule BooleanLiteralString
ruleBooleanLiteralString 
	@init {
		int stackSize = keepStackSize();
	}
	:
	(
		{ before(grammarAccess.getBooleanLiteralStringAccess().getAlternatives()); }
		(rule__BooleanLiteralString__Alternatives)
		{ after(grammarAccess.getBooleanLiteralStringAccess().getAlternatives()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

// Entry rule entryRuleEString
entryRuleEString
:
{ before(grammarAccess.getEStringRule()); }
	 ruleEString
{ after(grammarAccess.getEStringRule()); } 
	 EOF 
;

// Rule EString
ruleEString 
	@init {
		int stackSize = keepStackSize();
	}
	:
	(
		{ before(grammarAccess.getEStringAccess().getSTRINGTerminalRuleCall()); }
		RULE_STRING
		{ after(grammarAccess.getEStringAccess().getSTRINGTerminalRuleCall()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

// Entry rule entryRuleQualifiedNameWithWildcard
entryRuleQualifiedNameWithWildcard
:
{ before(grammarAccess.getQualifiedNameWithWildcardRule()); }
	 ruleQualifiedNameWithWildcard
{ after(grammarAccess.getQualifiedNameWithWildcardRule()); } 
	 EOF 
;

// Rule QualifiedNameWithWildcard
ruleQualifiedNameWithWildcard 
	@init {
		int stackSize = keepStackSize();
	}
	:
	(
		{ before(grammarAccess.getQualifiedNameWithWildcardAccess().getGroup()); }
		(rule__QualifiedNameWithWildcard__Group__0)
		{ after(grammarAccess.getQualifiedNameWithWildcardAccess().getGroup()); }
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

rule__StructuralElement__Alternatives_5_1_2
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getStructuralElementAccess().getGroup_5_1_2_0()); }
		(rule__StructuralElement__Group_5_1_2_0__0)
		{ after(grammarAccess.getStructuralElementAccess().getGroup_5_1_2_0()); }
	)
	|
	(
		{ before(grammarAccess.getStructuralElementAccess().getIsCanInheritFromAllAssignment_5_1_2_1()); }
		(rule__StructuralElement__IsCanInheritFromAllAssignment_5_1_2_1)
		{ after(grammarAccess.getStructuralElementAccess().getIsCanInheritFromAllAssignment_5_1_2_1()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__StructuralElement__Alternatives_5_2_2
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getStructuralElementAccess().getGroup_5_2_2_0()); }
		(rule__StructuralElement__Group_5_2_2_0__0)
		{ after(grammarAccess.getStructuralElementAccess().getGroup_5_2_2_0()); }
	)
	|
	(
		{ before(grammarAccess.getStructuralElementAccess().getIsApplicableForAllAssignment_5_2_2_1()); }
		(rule__StructuralElement__IsApplicableForAllAssignment_5_2_2_1)
		{ after(grammarAccess.getStructuralElementAccess().getIsApplicableForAllAssignment_5_2_2_1()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__GeneralRelation__Alternatives_9_2
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getGeneralRelationAccess().getGroup_9_2_0()); }
		(rule__GeneralRelation__Group_9_2_0__0)
		{ after(grammarAccess.getGeneralRelationAccess().getGroup_9_2_0()); }
	)
	|
	(
		{ before(grammarAccess.getGeneralRelationAccess().getIsApplicableForAllAssignment_9_2_1()); }
		(rule__GeneralRelation__IsApplicableForAllAssignment_9_2_1)
		{ after(grammarAccess.getGeneralRelationAccess().getIsApplicableForAllAssignment_9_2_1()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__Category__Alternatives_5_1_2
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getCategoryAccess().getGroup_5_1_2_0()); }
		(rule__Category__Group_5_1_2_0__0)
		{ after(grammarAccess.getCategoryAccess().getGroup_5_1_2_0()); }
	)
	|
	(
		{ before(grammarAccess.getCategoryAccess().getIsApplicableForAllAssignment_5_1_2_1()); }
		(rule__Category__IsApplicableForAllAssignment_5_1_2_1)
		{ after(grammarAccess.getCategoryAccess().getIsApplicableForAllAssignment_5_1_2_1()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__AProperty__Alternatives
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getAPropertyAccess().getComposedPropertyParserRuleCall_0()); }
		ruleComposedProperty
		{ after(grammarAccess.getAPropertyAccess().getComposedPropertyParserRuleCall_0()); }
	)
	|
	(
		{ before(grammarAccess.getAPropertyAccess().getIntPropertyParserRuleCall_1()); }
		ruleIntProperty
		{ after(grammarAccess.getAPropertyAccess().getIntPropertyParserRuleCall_1()); }
	)
	|
	(
		{ before(grammarAccess.getAPropertyAccess().getFloatPropertyParserRuleCall_2()); }
		ruleFloatProperty
		{ after(grammarAccess.getAPropertyAccess().getFloatPropertyParserRuleCall_2()); }
	)
	|
	(
		{ before(grammarAccess.getAPropertyAccess().getStringPropertyParserRuleCall_3()); }
		ruleStringProperty
		{ after(grammarAccess.getAPropertyAccess().getStringPropertyParserRuleCall_3()); }
	)
	|
	(
		{ before(grammarAccess.getAPropertyAccess().getBooleanPropertyParserRuleCall_4()); }
		ruleBooleanProperty
		{ after(grammarAccess.getAPropertyAccess().getBooleanPropertyParserRuleCall_4()); }
	)
	|
	(
		{ before(grammarAccess.getAPropertyAccess().getEnumPropertyParserRuleCall_5()); }
		ruleEnumProperty
		{ after(grammarAccess.getAPropertyAccess().getEnumPropertyParserRuleCall_5()); }
	)
	|
	(
		{ before(grammarAccess.getAPropertyAccess().getReferencePropertyParserRuleCall_6()); }
		ruleReferenceProperty
		{ after(grammarAccess.getAPropertyAccess().getReferencePropertyParserRuleCall_6()); }
	)
	|
	(
		{ before(grammarAccess.getAPropertyAccess().getEReferencePropertyParserRuleCall_7()); }
		ruleEReferenceProperty
		{ after(grammarAccess.getAPropertyAccess().getEReferencePropertyParserRuleCall_7()); }
	)
	|
	(
		{ before(grammarAccess.getAPropertyAccess().getResourcePropertyParserRuleCall_8()); }
		ruleResourceProperty
		{ after(grammarAccess.getAPropertyAccess().getResourcePropertyParserRuleCall_8()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__ArrayModifier__Alternatives
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getArrayModifierAccess().getDynmaicArrayModifierParserRuleCall_0()); }
		ruleDynmaicArrayModifier
		{ after(grammarAccess.getArrayModifierAccess().getDynmaicArrayModifierParserRuleCall_0()); }
	)
	|
	(
		{ before(grammarAccess.getArrayModifierAccess().getStaticArrayModifierParserRuleCall_1()); }
		ruleStaticArrayModifier
		{ after(grammarAccess.getArrayModifierAccess().getStaticArrayModifierParserRuleCall_1()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__EnumValueDefinition__ValueAlternatives_2_0
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getEnumValueDefinitionAccess().getValueEStringParserRuleCall_2_0_0()); }
		ruleEString
		{ after(grammarAccess.getEnumValueDefinitionAccess().getValueEStringParserRuleCall_2_0_0()); }
	)
	|
	(
		{ before(grammarAccess.getEnumValueDefinitionAccess().getValueFloatLiteralStringParserRuleCall_2_0_1()); }
		ruleFloatLiteralString
		{ after(grammarAccess.getEnumValueDefinitionAccess().getValueFloatLiteralStringParserRuleCall_2_0_1()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__EquationDefinitionResult__Alternatives
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getEquationDefinitionResultAccess().getTypeDefinitionResultParserRuleCall_0()); }
		ruleTypeDefinitionResult
		{ after(grammarAccess.getEquationDefinitionResultAccess().getTypeDefinitionResultParserRuleCall_0()); }
	)
	|
	(
		{ before(grammarAccess.getEquationDefinitionResultAccess().getEquationIntermediateResultParserRuleCall_1()); }
		ruleEquationIntermediateResult
		{ after(grammarAccess.getEquationDefinitionResultAccess().getEquationIntermediateResultParserRuleCall_1()); }
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
		{ before(grammarAccess.getAExpressionAccess().getReferencedDefinitionInputParserRuleCall_1()); }
		ruleReferencedDefinitionInput
		{ after(grammarAccess.getAExpressionAccess().getReferencedDefinitionInputParserRuleCall_1()); }
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

rule__BooleanLiteralString__Alternatives
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getBooleanLiteralStringAccess().getTrueKeyword_0()); }
		'true'
		{ after(grammarAccess.getBooleanLiteralStringAccess().getTrueKeyword_0()); }
	)
	|
	(
		{ before(grammarAccess.getBooleanLiteralStringAccess().getFalseKeyword_1()); }
		'false'
		{ after(grammarAccess.getBooleanLiteralStringAccess().getFalseKeyword_1()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__Concept__Group__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__Concept__Group__0__Impl
	rule__Concept__Group__1
;
finally {
	restoreStackSize(stackSize);
}

rule__Concept__Group__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getConceptAccess().getConceptAction_0()); }
	()
	{ after(grammarAccess.getConceptAccess().getConceptAction_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__Concept__Group__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__Concept__Group__1__Impl
	rule__Concept__Group__2
;
finally {
	restoreStackSize(stackSize);
}

rule__Concept__Group__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getConceptAccess().getConceptKeyword_1()); }
	'Concept'
	{ after(grammarAccess.getConceptAccess().getConceptKeyword_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__Concept__Group__2
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__Concept__Group__2__Impl
	rule__Concept__Group__3
;
finally {
	restoreStackSize(stackSize);
}

rule__Concept__Group__2__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getConceptAccess().getNameAssignment_2()); }
	(rule__Concept__NameAssignment_2)
	{ after(grammarAccess.getConceptAccess().getNameAssignment_2()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__Concept__Group__3
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__Concept__Group__3__Impl
	rule__Concept__Group__4
;
finally {
	restoreStackSize(stackSize);
}

rule__Concept__Group__3__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getConceptAccess().getUnorderedGroup_3()); }
	(rule__Concept__UnorderedGroup_3)
	{ after(grammarAccess.getConceptAccess().getUnorderedGroup_3()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__Concept__Group__4
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__Concept__Group__4__Impl
	rule__Concept__Group__5
;
finally {
	restoreStackSize(stackSize);
}

rule__Concept__Group__4__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getConceptAccess().getLeftCurlyBracketKeyword_4()); }
	'{'
	{ after(grammarAccess.getConceptAccess().getLeftCurlyBracketKeyword_4()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__Concept__Group__5
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__Concept__Group__5__Impl
	rule__Concept__Group__6
;
finally {
	restoreStackSize(stackSize);
}

rule__Concept__Group__5__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getConceptAccess().getUnorderedGroup_5()); }
	(rule__Concept__UnorderedGroup_5)
	{ after(grammarAccess.getConceptAccess().getUnorderedGroup_5()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__Concept__Group__6
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__Concept__Group__6__Impl
	rule__Concept__Group__7
;
finally {
	restoreStackSize(stackSize);
}

rule__Concept__Group__6__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getConceptAccess().getStructuralElementsAssignment_6()); }
	(rule__Concept__StructuralElementsAssignment_6)*
	{ after(grammarAccess.getConceptAccess().getStructuralElementsAssignment_6()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__Concept__Group__7
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__Concept__Group__7__Impl
	rule__Concept__Group__8
;
finally {
	restoreStackSize(stackSize);
}

rule__Concept__Group__7__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getConceptAccess().getRelationsAssignment_7()); }
	(rule__Concept__RelationsAssignment_7)*
	{ after(grammarAccess.getConceptAccess().getRelationsAssignment_7()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__Concept__Group__8
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__Concept__Group__8__Impl
	rule__Concept__Group__9
;
finally {
	restoreStackSize(stackSize);
}

rule__Concept__Group__8__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getConceptAccess().getCategoriesAssignment_8()); }
	(rule__Concept__CategoriesAssignment_8)*
	{ after(grammarAccess.getConceptAccess().getCategoriesAssignment_8()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__Concept__Group__9
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__Concept__Group__9__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__Concept__Group__9__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getConceptAccess().getRightCurlyBracketKeyword_9()); }
	'}'
	{ after(grammarAccess.getConceptAccess().getRightCurlyBracketKeyword_9()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__Concept__Group_3_0__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__Concept__Group_3_0__0__Impl
	rule__Concept__Group_3_0__1
;
finally {
	restoreStackSize(stackSize);
}

rule__Concept__Group_3_0__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getConceptAccess().getDisplaynameKeyword_3_0_0()); }
	'displayname'
	{ after(grammarAccess.getConceptAccess().getDisplaynameKeyword_3_0_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__Concept__Group_3_0__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__Concept__Group_3_0__1__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__Concept__Group_3_0__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getConceptAccess().getDisplayNameAssignment_3_0_1()); }
	(rule__Concept__DisplayNameAssignment_3_0_1)
	{ after(grammarAccess.getConceptAccess().getDisplayNameAssignment_3_0_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__Concept__Group_3_1__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__Concept__Group_3_1__0__Impl
	rule__Concept__Group_3_1__1
;
finally {
	restoreStackSize(stackSize);
}

rule__Concept__Group_3_1__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getConceptAccess().getVersionKeyword_3_1_0()); }
	'version'
	{ after(grammarAccess.getConceptAccess().getVersionKeyword_3_1_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__Concept__Group_3_1__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__Concept__Group_3_1__1__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__Concept__Group_3_1__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getConceptAccess().getVersionAssignment_3_1_1()); }
	(rule__Concept__VersionAssignment_3_1_1)
	{ after(grammarAccess.getConceptAccess().getVersionAssignment_3_1_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__Concept__Group_3_3__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__Concept__Group_3_3__0__Impl
	rule__Concept__Group_3_3__1
;
finally {
	restoreStackSize(stackSize);
}

rule__Concept__Group_3_3__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getConceptAccess().getDescriptionKeyword_3_3_0()); }
	'description'
	{ after(grammarAccess.getConceptAccess().getDescriptionKeyword_3_3_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__Concept__Group_3_3__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__Concept__Group_3_3__1__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__Concept__Group_3_3__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getConceptAccess().getDescriptionAssignment_3_3_1()); }
	(rule__Concept__DescriptionAssignment_3_3_1)
	{ after(grammarAccess.getConceptAccess().getDescriptionAssignment_3_3_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__StructuralElement__Group__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__StructuralElement__Group__0__Impl
	rule__StructuralElement__Group__1
;
finally {
	restoreStackSize(stackSize);
}

rule__StructuralElement__Group__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getStructuralElementAccess().getStructuralElementAction_0()); }
	()
	{ after(grammarAccess.getStructuralElementAccess().getStructuralElementAction_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__StructuralElement__Group__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__StructuralElement__Group__1__Impl
	rule__StructuralElement__Group__2
;
finally {
	restoreStackSize(stackSize);
}

rule__StructuralElement__Group__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getStructuralElementAccess().getStructuralElementKeyword_1()); }
	'StructuralElement'
	{ after(grammarAccess.getStructuralElementAccess().getStructuralElementKeyword_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__StructuralElement__Group__2
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__StructuralElement__Group__2__Impl
	rule__StructuralElement__Group__3
;
finally {
	restoreStackSize(stackSize);
}

rule__StructuralElement__Group__2__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getStructuralElementAccess().getNameAssignment_2()); }
	(rule__StructuralElement__NameAssignment_2)
	{ after(grammarAccess.getStructuralElementAccess().getNameAssignment_2()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__StructuralElement__Group__3
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__StructuralElement__Group__3__Impl
	rule__StructuralElement__Group__4
;
finally {
	restoreStackSize(stackSize);
}

rule__StructuralElement__Group__3__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getStructuralElementAccess().getUnorderedGroup_3()); }
	(rule__StructuralElement__UnorderedGroup_3)
	{ after(grammarAccess.getStructuralElementAccess().getUnorderedGroup_3()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__StructuralElement__Group__4
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__StructuralElement__Group__4__Impl
	rule__StructuralElement__Group__5
;
finally {
	restoreStackSize(stackSize);
}

rule__StructuralElement__Group__4__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getStructuralElementAccess().getLeftCurlyBracketKeyword_4()); }
	'{'
	{ after(grammarAccess.getStructuralElementAccess().getLeftCurlyBracketKeyword_4()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__StructuralElement__Group__5
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__StructuralElement__Group__5__Impl
	rule__StructuralElement__Group__6
;
finally {
	restoreStackSize(stackSize);
}

rule__StructuralElement__Group__5__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getStructuralElementAccess().getUnorderedGroup_5()); }
	(rule__StructuralElement__UnorderedGroup_5)
	{ after(grammarAccess.getStructuralElementAccess().getUnorderedGroup_5()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__StructuralElement__Group__6
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__StructuralElement__Group__6__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__StructuralElement__Group__6__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getStructuralElementAccess().getRightCurlyBracketKeyword_6()); }
	'}'
	{ after(grammarAccess.getStructuralElementAccess().getRightCurlyBracketKeyword_6()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__StructuralElement__Group_3_0__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__StructuralElement__Group_3_0__0__Impl
	rule__StructuralElement__Group_3_0__1
;
finally {
	restoreStackSize(stackSize);
}

rule__StructuralElement__Group_3_0__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getStructuralElementAccess().getShortnameKeyword_3_0_0()); }
	'shortname'
	{ after(grammarAccess.getStructuralElementAccess().getShortnameKeyword_3_0_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__StructuralElement__Group_3_0__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__StructuralElement__Group_3_0__1__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__StructuralElement__Group_3_0__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getStructuralElementAccess().getShortNameAssignment_3_0_1()); }
	(rule__StructuralElement__ShortNameAssignment_3_0_1)
	{ after(grammarAccess.getStructuralElementAccess().getShortNameAssignment_3_0_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__StructuralElement__Group_3_1__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__StructuralElement__Group_3_1__0__Impl
	rule__StructuralElement__Group_3_1__1
;
finally {
	restoreStackSize(stackSize);
}

rule__StructuralElement__Group_3_1__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getStructuralElementAccess().getDescriptionKeyword_3_1_0()); }
	'description'
	{ after(grammarAccess.getStructuralElementAccess().getDescriptionKeyword_3_1_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__StructuralElement__Group_3_1__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__StructuralElement__Group_3_1__1__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__StructuralElement__Group_3_1__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getStructuralElementAccess().getDescriptionAssignment_3_1_1()); }
	(rule__StructuralElement__DescriptionAssignment_3_1_1)
	{ after(grammarAccess.getStructuralElementAccess().getDescriptionAssignment_3_1_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__StructuralElement__Group_5_0__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__StructuralElement__Group_5_0__0__Impl
	rule__StructuralElement__Group_5_0__1
;
finally {
	restoreStackSize(stackSize);
}

rule__StructuralElement__Group_5_0__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getStructuralElementAccess().getIsRootStructuralElementAssignment_5_0_0()); }
	(rule__StructuralElement__IsRootStructuralElementAssignment_5_0_0)
	{ after(grammarAccess.getStructuralElementAccess().getIsRootStructuralElementAssignment_5_0_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__StructuralElement__Group_5_0__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__StructuralElement__Group_5_0__1__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__StructuralElement__Group_5_0__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getStructuralElementAccess().getSemicolonKeyword_5_0_1()); }
	';'
	{ after(grammarAccess.getStructuralElementAccess().getSemicolonKeyword_5_0_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__StructuralElement__Group_5_1__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__StructuralElement__Group_5_1__0__Impl
	rule__StructuralElement__Group_5_1__1
;
finally {
	restoreStackSize(stackSize);
}

rule__StructuralElement__Group_5_1__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getStructuralElementAccess().getInheritsKeyword_5_1_0()); }
	'Inherits'
	{ after(grammarAccess.getStructuralElementAccess().getInheritsKeyword_5_1_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__StructuralElement__Group_5_1__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__StructuralElement__Group_5_1__1__Impl
	rule__StructuralElement__Group_5_1__2
;
finally {
	restoreStackSize(stackSize);
}

rule__StructuralElement__Group_5_1__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getStructuralElementAccess().getFromKeyword_5_1_1()); }
	'From'
	{ after(grammarAccess.getStructuralElementAccess().getFromKeyword_5_1_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__StructuralElement__Group_5_1__2
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__StructuralElement__Group_5_1__2__Impl
	rule__StructuralElement__Group_5_1__3
;
finally {
	restoreStackSize(stackSize);
}

rule__StructuralElement__Group_5_1__2__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getStructuralElementAccess().getAlternatives_5_1_2()); }
	(rule__StructuralElement__Alternatives_5_1_2)
	{ after(grammarAccess.getStructuralElementAccess().getAlternatives_5_1_2()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__StructuralElement__Group_5_1__3
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__StructuralElement__Group_5_1__3__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__StructuralElement__Group_5_1__3__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getStructuralElementAccess().getSemicolonKeyword_5_1_3()); }
	';'
	{ after(grammarAccess.getStructuralElementAccess().getSemicolonKeyword_5_1_3()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__StructuralElement__Group_5_1_2_0__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__StructuralElement__Group_5_1_2_0__0__Impl
	rule__StructuralElement__Group_5_1_2_0__1
;
finally {
	restoreStackSize(stackSize);
}

rule__StructuralElement__Group_5_1_2_0__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getStructuralElementAccess().getLeftSquareBracketKeyword_5_1_2_0_0()); }
	'['
	{ after(grammarAccess.getStructuralElementAccess().getLeftSquareBracketKeyword_5_1_2_0_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__StructuralElement__Group_5_1_2_0__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__StructuralElement__Group_5_1_2_0__1__Impl
	rule__StructuralElement__Group_5_1_2_0__2
;
finally {
	restoreStackSize(stackSize);
}

rule__StructuralElement__Group_5_1_2_0__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getStructuralElementAccess().getCanInheritFromAssignment_5_1_2_0_1()); }
	(rule__StructuralElement__CanInheritFromAssignment_5_1_2_0_1)
	{ after(grammarAccess.getStructuralElementAccess().getCanInheritFromAssignment_5_1_2_0_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__StructuralElement__Group_5_1_2_0__2
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__StructuralElement__Group_5_1_2_0__2__Impl
	rule__StructuralElement__Group_5_1_2_0__3
;
finally {
	restoreStackSize(stackSize);
}

rule__StructuralElement__Group_5_1_2_0__2__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getStructuralElementAccess().getGroup_5_1_2_0_2()); }
	(rule__StructuralElement__Group_5_1_2_0_2__0)*
	{ after(grammarAccess.getStructuralElementAccess().getGroup_5_1_2_0_2()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__StructuralElement__Group_5_1_2_0__3
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__StructuralElement__Group_5_1_2_0__3__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__StructuralElement__Group_5_1_2_0__3__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getStructuralElementAccess().getRightSquareBracketKeyword_5_1_2_0_3()); }
	']'
	{ after(grammarAccess.getStructuralElementAccess().getRightSquareBracketKeyword_5_1_2_0_3()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__StructuralElement__Group_5_1_2_0_2__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__StructuralElement__Group_5_1_2_0_2__0__Impl
	rule__StructuralElement__Group_5_1_2_0_2__1
;
finally {
	restoreStackSize(stackSize);
}

rule__StructuralElement__Group_5_1_2_0_2__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getStructuralElementAccess().getCommaKeyword_5_1_2_0_2_0()); }
	','
	{ after(grammarAccess.getStructuralElementAccess().getCommaKeyword_5_1_2_0_2_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__StructuralElement__Group_5_1_2_0_2__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__StructuralElement__Group_5_1_2_0_2__1__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__StructuralElement__Group_5_1_2_0_2__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getStructuralElementAccess().getCanInheritFromAssignment_5_1_2_0_2_1()); }
	(rule__StructuralElement__CanInheritFromAssignment_5_1_2_0_2_1)
	{ after(grammarAccess.getStructuralElementAccess().getCanInheritFromAssignment_5_1_2_0_2_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__StructuralElement__Group_5_2__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__StructuralElement__Group_5_2__0__Impl
	rule__StructuralElement__Group_5_2__1
;
finally {
	restoreStackSize(stackSize);
}

rule__StructuralElement__Group_5_2__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getStructuralElementAccess().getApplicableKeyword_5_2_0()); }
	'Applicable'
	{ after(grammarAccess.getStructuralElementAccess().getApplicableKeyword_5_2_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__StructuralElement__Group_5_2__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__StructuralElement__Group_5_2__1__Impl
	rule__StructuralElement__Group_5_2__2
;
finally {
	restoreStackSize(stackSize);
}

rule__StructuralElement__Group_5_2__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getStructuralElementAccess().getForKeyword_5_2_1()); }
	'For'
	{ after(grammarAccess.getStructuralElementAccess().getForKeyword_5_2_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__StructuralElement__Group_5_2__2
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__StructuralElement__Group_5_2__2__Impl
	rule__StructuralElement__Group_5_2__3
;
finally {
	restoreStackSize(stackSize);
}

rule__StructuralElement__Group_5_2__2__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getStructuralElementAccess().getAlternatives_5_2_2()); }
	(rule__StructuralElement__Alternatives_5_2_2)
	{ after(grammarAccess.getStructuralElementAccess().getAlternatives_5_2_2()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__StructuralElement__Group_5_2__3
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__StructuralElement__Group_5_2__3__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__StructuralElement__Group_5_2__3__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getStructuralElementAccess().getSemicolonKeyword_5_2_3()); }
	';'
	{ after(grammarAccess.getStructuralElementAccess().getSemicolonKeyword_5_2_3()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__StructuralElement__Group_5_2_2_0__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__StructuralElement__Group_5_2_2_0__0__Impl
	rule__StructuralElement__Group_5_2_2_0__1
;
finally {
	restoreStackSize(stackSize);
}

rule__StructuralElement__Group_5_2_2_0__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getStructuralElementAccess().getLeftSquareBracketKeyword_5_2_2_0_0()); }
	'['
	{ after(grammarAccess.getStructuralElementAccess().getLeftSquareBracketKeyword_5_2_2_0_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__StructuralElement__Group_5_2_2_0__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__StructuralElement__Group_5_2_2_0__1__Impl
	rule__StructuralElement__Group_5_2_2_0__2
;
finally {
	restoreStackSize(stackSize);
}

rule__StructuralElement__Group_5_2_2_0__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getStructuralElementAccess().getApplicableForAssignment_5_2_2_0_1()); }
	(rule__StructuralElement__ApplicableForAssignment_5_2_2_0_1)
	{ after(grammarAccess.getStructuralElementAccess().getApplicableForAssignment_5_2_2_0_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__StructuralElement__Group_5_2_2_0__2
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__StructuralElement__Group_5_2_2_0__2__Impl
	rule__StructuralElement__Group_5_2_2_0__3
;
finally {
	restoreStackSize(stackSize);
}

rule__StructuralElement__Group_5_2_2_0__2__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getStructuralElementAccess().getGroup_5_2_2_0_2()); }
	(rule__StructuralElement__Group_5_2_2_0_2__0)*
	{ after(grammarAccess.getStructuralElementAccess().getGroup_5_2_2_0_2()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__StructuralElement__Group_5_2_2_0__3
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__StructuralElement__Group_5_2_2_0__3__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__StructuralElement__Group_5_2_2_0__3__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getStructuralElementAccess().getRightSquareBracketKeyword_5_2_2_0_3()); }
	']'
	{ after(grammarAccess.getStructuralElementAccess().getRightSquareBracketKeyword_5_2_2_0_3()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__StructuralElement__Group_5_2_2_0_2__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__StructuralElement__Group_5_2_2_0_2__0__Impl
	rule__StructuralElement__Group_5_2_2_0_2__1
;
finally {
	restoreStackSize(stackSize);
}

rule__StructuralElement__Group_5_2_2_0_2__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getStructuralElementAccess().getCommaKeyword_5_2_2_0_2_0()); }
	','
	{ after(grammarAccess.getStructuralElementAccess().getCommaKeyword_5_2_2_0_2_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__StructuralElement__Group_5_2_2_0_2__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__StructuralElement__Group_5_2_2_0_2__1__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__StructuralElement__Group_5_2_2_0_2__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getStructuralElementAccess().getApplicableForAssignment_5_2_2_0_2_1()); }
	(rule__StructuralElement__ApplicableForAssignment_5_2_2_0_2_1)
	{ after(grammarAccess.getStructuralElementAccess().getApplicableForAssignment_5_2_2_0_2_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__StructuralElement__Group_5_3__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__StructuralElement__Group_5_3__0__Impl
	rule__StructuralElement__Group_5_3__1
;
finally {
	restoreStackSize(stackSize);
}

rule__StructuralElement__Group_5_3__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getStructuralElementAccess().getCardinalityKeyword_5_3_0()); }
	'Cardinality'
	{ after(grammarAccess.getStructuralElementAccess().getCardinalityKeyword_5_3_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__StructuralElement__Group_5_3__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__StructuralElement__Group_5_3__1__Impl
	rule__StructuralElement__Group_5_3__2
;
finally {
	restoreStackSize(stackSize);
}

rule__StructuralElement__Group_5_3__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getStructuralElementAccess().getCardinalityAssignment_5_3_1()); }
	(rule__StructuralElement__CardinalityAssignment_5_3_1)
	{ after(grammarAccess.getStructuralElementAccess().getCardinalityAssignment_5_3_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__StructuralElement__Group_5_3__2
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__StructuralElement__Group_5_3__2__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__StructuralElement__Group_5_3__2__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getStructuralElementAccess().getSemicolonKeyword_5_3_2()); }
	';'
	{ after(grammarAccess.getStructuralElementAccess().getSemicolonKeyword_5_3_2()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__GeneralRelation__Group__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__GeneralRelation__Group__0__Impl
	rule__GeneralRelation__Group__1
;
finally {
	restoreStackSize(stackSize);
}

rule__GeneralRelation__Group__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getGeneralRelationAccess().getGeneralRelationAction_0()); }
	()
	{ after(grammarAccess.getGeneralRelationAccess().getGeneralRelationAction_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__GeneralRelation__Group__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__GeneralRelation__Group__1__Impl
	rule__GeneralRelation__Group__2
;
finally {
	restoreStackSize(stackSize);
}

rule__GeneralRelation__Group__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getGeneralRelationAccess().getGeneralRelationKeyword_1()); }
	'GeneralRelation'
	{ after(grammarAccess.getGeneralRelationAccess().getGeneralRelationKeyword_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__GeneralRelation__Group__2
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__GeneralRelation__Group__2__Impl
	rule__GeneralRelation__Group__3
;
finally {
	restoreStackSize(stackSize);
}

rule__GeneralRelation__Group__2__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getGeneralRelationAccess().getNameAssignment_2()); }
	(rule__GeneralRelation__NameAssignment_2)
	{ after(grammarAccess.getGeneralRelationAccess().getNameAssignment_2()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__GeneralRelation__Group__3
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__GeneralRelation__Group__3__Impl
	rule__GeneralRelation__Group__4
;
finally {
	restoreStackSize(stackSize);
}

rule__GeneralRelation__Group__3__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getGeneralRelationAccess().getGroup_3()); }
	(rule__GeneralRelation__Group_3__0)?
	{ after(grammarAccess.getGeneralRelationAccess().getGroup_3()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__GeneralRelation__Group__4
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__GeneralRelation__Group__4__Impl
	rule__GeneralRelation__Group__5
;
finally {
	restoreStackSize(stackSize);
}

rule__GeneralRelation__Group__4__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getGeneralRelationAccess().getLeftCurlyBracketKeyword_4()); }
	'{'
	{ after(grammarAccess.getGeneralRelationAccess().getLeftCurlyBracketKeyword_4()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__GeneralRelation__Group__5
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__GeneralRelation__Group__5__Impl
	rule__GeneralRelation__Group__6
;
finally {
	restoreStackSize(stackSize);
}

rule__GeneralRelation__Group__5__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getGeneralRelationAccess().getReferencedKeyword_5()); }
	'Referenced'
	{ after(grammarAccess.getGeneralRelationAccess().getReferencedKeyword_5()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__GeneralRelation__Group__6
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__GeneralRelation__Group__6__Impl
	rule__GeneralRelation__Group__7
;
finally {
	restoreStackSize(stackSize);
}

rule__GeneralRelation__Group__6__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getGeneralRelationAccess().getTypeKeyword_6()); }
	'Type'
	{ after(grammarAccess.getGeneralRelationAccess().getTypeKeyword_6()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__GeneralRelation__Group__7
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__GeneralRelation__Group__7__Impl
	rule__GeneralRelation__Group__8
;
finally {
	restoreStackSize(stackSize);
}

rule__GeneralRelation__Group__7__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getGeneralRelationAccess().getReferencedTypeAssignment_7()); }
	(rule__GeneralRelation__ReferencedTypeAssignment_7)
	{ after(grammarAccess.getGeneralRelationAccess().getReferencedTypeAssignment_7()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__GeneralRelation__Group__8
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__GeneralRelation__Group__8__Impl
	rule__GeneralRelation__Group__9
;
finally {
	restoreStackSize(stackSize);
}

rule__GeneralRelation__Group__8__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getGeneralRelationAccess().getSemicolonKeyword_8()); }
	';'
	{ after(grammarAccess.getGeneralRelationAccess().getSemicolonKeyword_8()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__GeneralRelation__Group__9
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__GeneralRelation__Group__9__Impl
	rule__GeneralRelation__Group__10
;
finally {
	restoreStackSize(stackSize);
}

rule__GeneralRelation__Group__9__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getGeneralRelationAccess().getGroup_9()); }
	(rule__GeneralRelation__Group_9__0)?
	{ after(grammarAccess.getGeneralRelationAccess().getGroup_9()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__GeneralRelation__Group__10
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__GeneralRelation__Group__10__Impl
	rule__GeneralRelation__Group__11
;
finally {
	restoreStackSize(stackSize);
}

rule__GeneralRelation__Group__10__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getGeneralRelationAccess().getGroup_10()); }
	(rule__GeneralRelation__Group_10__0)?
	{ after(grammarAccess.getGeneralRelationAccess().getGroup_10()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__GeneralRelation__Group__11
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__GeneralRelation__Group__11__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__GeneralRelation__Group__11__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getGeneralRelationAccess().getRightCurlyBracketKeyword_11()); }
	'}'
	{ after(grammarAccess.getGeneralRelationAccess().getRightCurlyBracketKeyword_11()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__GeneralRelation__Group_3__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__GeneralRelation__Group_3__0__Impl
	rule__GeneralRelation__Group_3__1
;
finally {
	restoreStackSize(stackSize);
}

rule__GeneralRelation__Group_3__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getGeneralRelationAccess().getDescriptionKeyword_3_0()); }
	'description'
	{ after(grammarAccess.getGeneralRelationAccess().getDescriptionKeyword_3_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__GeneralRelation__Group_3__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__GeneralRelation__Group_3__1__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__GeneralRelation__Group_3__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getGeneralRelationAccess().getDescriptionAssignment_3_1()); }
	(rule__GeneralRelation__DescriptionAssignment_3_1)
	{ after(grammarAccess.getGeneralRelationAccess().getDescriptionAssignment_3_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__GeneralRelation__Group_9__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__GeneralRelation__Group_9__0__Impl
	rule__GeneralRelation__Group_9__1
;
finally {
	restoreStackSize(stackSize);
}

rule__GeneralRelation__Group_9__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getGeneralRelationAccess().getApplicableKeyword_9_0()); }
	'Applicable'
	{ after(grammarAccess.getGeneralRelationAccess().getApplicableKeyword_9_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__GeneralRelation__Group_9__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__GeneralRelation__Group_9__1__Impl
	rule__GeneralRelation__Group_9__2
;
finally {
	restoreStackSize(stackSize);
}

rule__GeneralRelation__Group_9__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getGeneralRelationAccess().getForKeyword_9_1()); }
	'For'
	{ after(grammarAccess.getGeneralRelationAccess().getForKeyword_9_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__GeneralRelation__Group_9__2
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__GeneralRelation__Group_9__2__Impl
	rule__GeneralRelation__Group_9__3
;
finally {
	restoreStackSize(stackSize);
}

rule__GeneralRelation__Group_9__2__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getGeneralRelationAccess().getAlternatives_9_2()); }
	(rule__GeneralRelation__Alternatives_9_2)
	{ after(grammarAccess.getGeneralRelationAccess().getAlternatives_9_2()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__GeneralRelation__Group_9__3
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__GeneralRelation__Group_9__3__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__GeneralRelation__Group_9__3__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getGeneralRelationAccess().getSemicolonKeyword_9_3()); }
	';'
	{ after(grammarAccess.getGeneralRelationAccess().getSemicolonKeyword_9_3()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__GeneralRelation__Group_9_2_0__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__GeneralRelation__Group_9_2_0__0__Impl
	rule__GeneralRelation__Group_9_2_0__1
;
finally {
	restoreStackSize(stackSize);
}

rule__GeneralRelation__Group_9_2_0__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getGeneralRelationAccess().getLeftSquareBracketKeyword_9_2_0_0()); }
	'['
	{ after(grammarAccess.getGeneralRelationAccess().getLeftSquareBracketKeyword_9_2_0_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__GeneralRelation__Group_9_2_0__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__GeneralRelation__Group_9_2_0__1__Impl
	rule__GeneralRelation__Group_9_2_0__2
;
finally {
	restoreStackSize(stackSize);
}

rule__GeneralRelation__Group_9_2_0__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getGeneralRelationAccess().getApplicableForAssignment_9_2_0_1()); }
	(rule__GeneralRelation__ApplicableForAssignment_9_2_0_1)
	{ after(grammarAccess.getGeneralRelationAccess().getApplicableForAssignment_9_2_0_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__GeneralRelation__Group_9_2_0__2
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__GeneralRelation__Group_9_2_0__2__Impl
	rule__GeneralRelation__Group_9_2_0__3
;
finally {
	restoreStackSize(stackSize);
}

rule__GeneralRelation__Group_9_2_0__2__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getGeneralRelationAccess().getGroup_9_2_0_2()); }
	(rule__GeneralRelation__Group_9_2_0_2__0)*
	{ after(grammarAccess.getGeneralRelationAccess().getGroup_9_2_0_2()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__GeneralRelation__Group_9_2_0__3
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__GeneralRelation__Group_9_2_0__3__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__GeneralRelation__Group_9_2_0__3__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getGeneralRelationAccess().getRightSquareBracketKeyword_9_2_0_3()); }
	']'
	{ after(grammarAccess.getGeneralRelationAccess().getRightSquareBracketKeyword_9_2_0_3()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__GeneralRelation__Group_9_2_0_2__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__GeneralRelation__Group_9_2_0_2__0__Impl
	rule__GeneralRelation__Group_9_2_0_2__1
;
finally {
	restoreStackSize(stackSize);
}

rule__GeneralRelation__Group_9_2_0_2__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getGeneralRelationAccess().getCommaKeyword_9_2_0_2_0()); }
	','
	{ after(grammarAccess.getGeneralRelationAccess().getCommaKeyword_9_2_0_2_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__GeneralRelation__Group_9_2_0_2__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__GeneralRelation__Group_9_2_0_2__1__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__GeneralRelation__Group_9_2_0_2__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getGeneralRelationAccess().getApplicableForAssignment_9_2_0_2_1()); }
	(rule__GeneralRelation__ApplicableForAssignment_9_2_0_2_1)
	{ after(grammarAccess.getGeneralRelationAccess().getApplicableForAssignment_9_2_0_2_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__GeneralRelation__Group_10__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__GeneralRelation__Group_10__0__Impl
	rule__GeneralRelation__Group_10__1
;
finally {
	restoreStackSize(stackSize);
}

rule__GeneralRelation__Group_10__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getGeneralRelationAccess().getCardinalityKeyword_10_0()); }
	'Cardinality'
	{ after(grammarAccess.getGeneralRelationAccess().getCardinalityKeyword_10_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__GeneralRelation__Group_10__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__GeneralRelation__Group_10__1__Impl
	rule__GeneralRelation__Group_10__2
;
finally {
	restoreStackSize(stackSize);
}

rule__GeneralRelation__Group_10__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getGeneralRelationAccess().getCardinalityAssignment_10_1()); }
	(rule__GeneralRelation__CardinalityAssignment_10_1)
	{ after(grammarAccess.getGeneralRelationAccess().getCardinalityAssignment_10_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__GeneralRelation__Group_10__2
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__GeneralRelation__Group_10__2__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__GeneralRelation__Group_10__2__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getGeneralRelationAccess().getSemicolonKeyword_10_2()); }
	';'
	{ after(grammarAccess.getGeneralRelationAccess().getSemicolonKeyword_10_2()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__Category__Group__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__Category__Group__0__Impl
	rule__Category__Group__1
;
finally {
	restoreStackSize(stackSize);
}

rule__Category__Group__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getCategoryAccess().getCategoryAction_0()); }
	()
	{ after(grammarAccess.getCategoryAccess().getCategoryAction_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__Category__Group__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__Category__Group__1__Impl
	rule__Category__Group__2
;
finally {
	restoreStackSize(stackSize);
}

rule__Category__Group__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getCategoryAccess().getCategoryKeyword_1()); }
	'Category'
	{ after(grammarAccess.getCategoryAccess().getCategoryKeyword_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__Category__Group__2
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__Category__Group__2__Impl
	rule__Category__Group__3
;
finally {
	restoreStackSize(stackSize);
}

rule__Category__Group__2__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getCategoryAccess().getNameAssignment_2()); }
	(rule__Category__NameAssignment_2)
	{ after(grammarAccess.getCategoryAccess().getNameAssignment_2()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__Category__Group__3
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__Category__Group__3__Impl
	rule__Category__Group__4
;
finally {
	restoreStackSize(stackSize);
}

rule__Category__Group__3__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getCategoryAccess().getUnorderedGroup_3()); }
	(rule__Category__UnorderedGroup_3)
	{ after(grammarAccess.getCategoryAccess().getUnorderedGroup_3()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__Category__Group__4
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__Category__Group__4__Impl
	rule__Category__Group__5
;
finally {
	restoreStackSize(stackSize);
}

rule__Category__Group__4__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getCategoryAccess().getLeftCurlyBracketKeyword_4()); }
	'{'
	{ after(grammarAccess.getCategoryAccess().getLeftCurlyBracketKeyword_4()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__Category__Group__5
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__Category__Group__5__Impl
	rule__Category__Group__6
;
finally {
	restoreStackSize(stackSize);
}

rule__Category__Group__5__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getCategoryAccess().getUnorderedGroup_5()); }
	(rule__Category__UnorderedGroup_5)
	{ after(grammarAccess.getCategoryAccess().getUnorderedGroup_5()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__Category__Group__6
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__Category__Group__6__Impl
	rule__Category__Group__7
;
finally {
	restoreStackSize(stackSize);
}

rule__Category__Group__6__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getCategoryAccess().getPropertiesAssignment_6()); }
	(rule__Category__PropertiesAssignment_6)*
	{ after(grammarAccess.getCategoryAccess().getPropertiesAssignment_6()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__Category__Group__7
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__Category__Group__7__Impl
	rule__Category__Group__8
;
finally {
	restoreStackSize(stackSize);
}

rule__Category__Group__7__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getCategoryAccess().getEquationDefinitionsAssignment_7()); }
	(rule__Category__EquationDefinitionsAssignment_7)*
	{ after(grammarAccess.getCategoryAccess().getEquationDefinitionsAssignment_7()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__Category__Group__8
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__Category__Group__8__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__Category__Group__8__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getCategoryAccess().getRightCurlyBracketKeyword_8()); }
	'}'
	{ after(grammarAccess.getCategoryAccess().getRightCurlyBracketKeyword_8()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__Category__Group_3_0__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__Category__Group_3_0__0__Impl
	rule__Category__Group_3_0__1
;
finally {
	restoreStackSize(stackSize);
}

rule__Category__Group_3_0__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getCategoryAccess().getExtendsKeyword_3_0_0()); }
	'extends'
	{ after(grammarAccess.getCategoryAccess().getExtendsKeyword_3_0_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__Category__Group_3_0__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__Category__Group_3_0__1__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__Category__Group_3_0__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getCategoryAccess().getExtendsCategoryAssignment_3_0_1()); }
	(rule__Category__ExtendsCategoryAssignment_3_0_1)
	{ after(grammarAccess.getCategoryAccess().getExtendsCategoryAssignment_3_0_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__Category__Group_3_1__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__Category__Group_3_1__0__Impl
	rule__Category__Group_3_1__1
;
finally {
	restoreStackSize(stackSize);
}

rule__Category__Group_3_1__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getCategoryAccess().getShortnameKeyword_3_1_0()); }
	'shortname'
	{ after(grammarAccess.getCategoryAccess().getShortnameKeyword_3_1_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__Category__Group_3_1__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__Category__Group_3_1__1__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__Category__Group_3_1__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getCategoryAccess().getShortNameAssignment_3_1_1()); }
	(rule__Category__ShortNameAssignment_3_1_1)
	{ after(grammarAccess.getCategoryAccess().getShortNameAssignment_3_1_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__Category__Group_3_2__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__Category__Group_3_2__0__Impl
	rule__Category__Group_3_2__1
;
finally {
	restoreStackSize(stackSize);
}

rule__Category__Group_3_2__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getCategoryAccess().getDescriptionKeyword_3_2_0()); }
	'description'
	{ after(grammarAccess.getCategoryAccess().getDescriptionKeyword_3_2_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__Category__Group_3_2__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__Category__Group_3_2__1__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__Category__Group_3_2__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getCategoryAccess().getDescriptionAssignment_3_2_1()); }
	(rule__Category__DescriptionAssignment_3_2_1)
	{ after(grammarAccess.getCategoryAccess().getDescriptionAssignment_3_2_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__Category__Group_5_0__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__Category__Group_5_0__0__Impl
	rule__Category__Group_5_0__1
;
finally {
	restoreStackSize(stackSize);
}

rule__Category__Group_5_0__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getCategoryAccess().getIsAbstractAssignment_5_0_0()); }
	(rule__Category__IsAbstractAssignment_5_0_0)
	{ after(grammarAccess.getCategoryAccess().getIsAbstractAssignment_5_0_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__Category__Group_5_0__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__Category__Group_5_0__1__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__Category__Group_5_0__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getCategoryAccess().getSemicolonKeyword_5_0_1()); }
	';'
	{ after(grammarAccess.getCategoryAccess().getSemicolonKeyword_5_0_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__Category__Group_5_1__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__Category__Group_5_1__0__Impl
	rule__Category__Group_5_1__1
;
finally {
	restoreStackSize(stackSize);
}

rule__Category__Group_5_1__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getCategoryAccess().getApplicableKeyword_5_1_0()); }
	'Applicable'
	{ after(grammarAccess.getCategoryAccess().getApplicableKeyword_5_1_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__Category__Group_5_1__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__Category__Group_5_1__1__Impl
	rule__Category__Group_5_1__2
;
finally {
	restoreStackSize(stackSize);
}

rule__Category__Group_5_1__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getCategoryAccess().getForKeyword_5_1_1()); }
	'For'
	{ after(grammarAccess.getCategoryAccess().getForKeyword_5_1_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__Category__Group_5_1__2
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__Category__Group_5_1__2__Impl
	rule__Category__Group_5_1__3
;
finally {
	restoreStackSize(stackSize);
}

rule__Category__Group_5_1__2__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getCategoryAccess().getAlternatives_5_1_2()); }
	(rule__Category__Alternatives_5_1_2)
	{ after(grammarAccess.getCategoryAccess().getAlternatives_5_1_2()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__Category__Group_5_1__3
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__Category__Group_5_1__3__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__Category__Group_5_1__3__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getCategoryAccess().getSemicolonKeyword_5_1_3()); }
	';'
	{ after(grammarAccess.getCategoryAccess().getSemicolonKeyword_5_1_3()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__Category__Group_5_1_2_0__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__Category__Group_5_1_2_0__0__Impl
	rule__Category__Group_5_1_2_0__1
;
finally {
	restoreStackSize(stackSize);
}

rule__Category__Group_5_1_2_0__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getCategoryAccess().getLeftSquareBracketKeyword_5_1_2_0_0()); }
	'['
	{ after(grammarAccess.getCategoryAccess().getLeftSquareBracketKeyword_5_1_2_0_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__Category__Group_5_1_2_0__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__Category__Group_5_1_2_0__1__Impl
	rule__Category__Group_5_1_2_0__2
;
finally {
	restoreStackSize(stackSize);
}

rule__Category__Group_5_1_2_0__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getCategoryAccess().getApplicableForAssignment_5_1_2_0_1()); }
	(rule__Category__ApplicableForAssignment_5_1_2_0_1)
	{ after(grammarAccess.getCategoryAccess().getApplicableForAssignment_5_1_2_0_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__Category__Group_5_1_2_0__2
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__Category__Group_5_1_2_0__2__Impl
	rule__Category__Group_5_1_2_0__3
;
finally {
	restoreStackSize(stackSize);
}

rule__Category__Group_5_1_2_0__2__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getCategoryAccess().getGroup_5_1_2_0_2()); }
	(rule__Category__Group_5_1_2_0_2__0)*
	{ after(grammarAccess.getCategoryAccess().getGroup_5_1_2_0_2()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__Category__Group_5_1_2_0__3
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__Category__Group_5_1_2_0__3__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__Category__Group_5_1_2_0__3__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getCategoryAccess().getRightSquareBracketKeyword_5_1_2_0_3()); }
	']'
	{ after(grammarAccess.getCategoryAccess().getRightSquareBracketKeyword_5_1_2_0_3()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__Category__Group_5_1_2_0_2__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__Category__Group_5_1_2_0_2__0__Impl
	rule__Category__Group_5_1_2_0_2__1
;
finally {
	restoreStackSize(stackSize);
}

rule__Category__Group_5_1_2_0_2__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getCategoryAccess().getCommaKeyword_5_1_2_0_2_0()); }
	','
	{ after(grammarAccess.getCategoryAccess().getCommaKeyword_5_1_2_0_2_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__Category__Group_5_1_2_0_2__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__Category__Group_5_1_2_0_2__1__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__Category__Group_5_1_2_0_2__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getCategoryAccess().getApplicableForAssignment_5_1_2_0_2_1()); }
	(rule__Category__ApplicableForAssignment_5_1_2_0_2_1)
	{ after(grammarAccess.getCategoryAccess().getApplicableForAssignment_5_1_2_0_2_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__Category__Group_5_2__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__Category__Group_5_2__0__Impl
	rule__Category__Group_5_2__1
;
finally {
	restoreStackSize(stackSize);
}

rule__Category__Group_5_2__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getCategoryAccess().getCardinalityKeyword_5_2_0()); }
	'Cardinality'
	{ after(grammarAccess.getCategoryAccess().getCardinalityKeyword_5_2_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__Category__Group_5_2__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__Category__Group_5_2__1__Impl
	rule__Category__Group_5_2__2
;
finally {
	restoreStackSize(stackSize);
}

rule__Category__Group_5_2__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getCategoryAccess().getCardinalityAssignment_5_2_1()); }
	(rule__Category__CardinalityAssignment_5_2_1)
	{ after(grammarAccess.getCategoryAccess().getCardinalityAssignment_5_2_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__Category__Group_5_2__2
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__Category__Group_5_2__2__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__Category__Group_5_2__2__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getCategoryAccess().getSemicolonKeyword_5_2_2()); }
	';'
	{ after(grammarAccess.getCategoryAccess().getSemicolonKeyword_5_2_2()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__ConceptImport__Group__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__ConceptImport__Group__0__Impl
	rule__ConceptImport__Group__1
;
finally {
	restoreStackSize(stackSize);
}

rule__ConceptImport__Group__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getConceptImportAccess().getConceptImportAction_0()); }
	()
	{ after(grammarAccess.getConceptImportAccess().getConceptImportAction_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__ConceptImport__Group__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__ConceptImport__Group__1__Impl
	rule__ConceptImport__Group__2
;
finally {
	restoreStackSize(stackSize);
}

rule__ConceptImport__Group__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getConceptImportAccess().getImportKeyword_1()); }
	'Import'
	{ after(grammarAccess.getConceptImportAccess().getImportKeyword_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__ConceptImport__Group__2
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__ConceptImport__Group__2__Impl
	rule__ConceptImport__Group__3
;
finally {
	restoreStackSize(stackSize);
}

rule__ConceptImport__Group__2__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getConceptImportAccess().getImportedNamespaceAssignment_2()); }
	(rule__ConceptImport__ImportedNamespaceAssignment_2)
	{ after(grammarAccess.getConceptImportAccess().getImportedNamespaceAssignment_2()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__ConceptImport__Group__3
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__ConceptImport__Group__3__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__ConceptImport__Group__3__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getConceptImportAccess().getSemicolonKeyword_3()); }
	';'
	{ after(grammarAccess.getConceptImportAccess().getSemicolonKeyword_3()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__EcoreImport__Group__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__EcoreImport__Group__0__Impl
	rule__EcoreImport__Group__1
;
finally {
	restoreStackSize(stackSize);
}

rule__EcoreImport__Group__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getEcoreImportAccess().getEcoreImportAction_0()); }
	()
	{ after(grammarAccess.getEcoreImportAccess().getEcoreImportAction_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__EcoreImport__Group__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__EcoreImport__Group__1__Impl
	rule__EcoreImport__Group__2
;
finally {
	restoreStackSize(stackSize);
}

rule__EcoreImport__Group__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getEcoreImportAccess().getEImportKeyword_1()); }
	'EImport'
	{ after(grammarAccess.getEcoreImportAccess().getEImportKeyword_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__EcoreImport__Group__2
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__EcoreImport__Group__2__Impl
	rule__EcoreImport__Group__3
;
finally {
	restoreStackSize(stackSize);
}

rule__EcoreImport__Group__2__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getEcoreImportAccess().getImportedNsURIAssignment_2()); }
	(rule__EcoreImport__ImportedNsURIAssignment_2)
	{ after(grammarAccess.getEcoreImportAccess().getImportedNsURIAssignment_2()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__EcoreImport__Group__3
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__EcoreImport__Group__3__Impl
	rule__EcoreImport__Group__4
;
finally {
	restoreStackSize(stackSize);
}

rule__EcoreImport__Group__3__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getEcoreImportAccess().getGroup_3()); }
	(rule__EcoreImport__Group_3__0)?
	{ after(grammarAccess.getEcoreImportAccess().getGroup_3()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__EcoreImport__Group__4
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__EcoreImport__Group__4__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__EcoreImport__Group__4__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getEcoreImportAccess().getSemicolonKeyword_4()); }
	';'
	{ after(grammarAccess.getEcoreImportAccess().getSemicolonKeyword_4()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__EcoreImport__Group_3__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__EcoreImport__Group_3__0__Impl
	rule__EcoreImport__Group_3__1
;
finally {
	restoreStackSize(stackSize);
}

rule__EcoreImport__Group_3__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getEcoreImportAccess().getGenModelKeyword_3_0()); }
	'genModel'
	{ after(grammarAccess.getEcoreImportAccess().getGenModelKeyword_3_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__EcoreImport__Group_3__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__EcoreImport__Group_3__1__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__EcoreImport__Group_3__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getEcoreImportAccess().getImportedGenModelAssignment_3_1()); }
	(rule__EcoreImport__ImportedGenModelAssignment_3_1)
	{ after(grammarAccess.getEcoreImportAccess().getImportedGenModelAssignment_3_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__DynmaicArrayModifier__Group__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__DynmaicArrayModifier__Group__0__Impl
	rule__DynmaicArrayModifier__Group__1
;
finally {
	restoreStackSize(stackSize);
}

rule__DynmaicArrayModifier__Group__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getDynmaicArrayModifierAccess().getDynamicArrayModifierAction_0()); }
	()
	{ after(grammarAccess.getDynmaicArrayModifierAccess().getDynamicArrayModifierAction_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__DynmaicArrayModifier__Group__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__DynmaicArrayModifier__Group__1__Impl
	rule__DynmaicArrayModifier__Group__2
;
finally {
	restoreStackSize(stackSize);
}

rule__DynmaicArrayModifier__Group__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getDynmaicArrayModifierAccess().getLeftSquareBracketKeyword_1()); }
	'['
	{ after(grammarAccess.getDynmaicArrayModifierAccess().getLeftSquareBracketKeyword_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__DynmaicArrayModifier__Group__2
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__DynmaicArrayModifier__Group__2__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__DynmaicArrayModifier__Group__2__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getDynmaicArrayModifierAccess().getRightSquareBracketKeyword_2()); }
	']'
	{ after(grammarAccess.getDynmaicArrayModifierAccess().getRightSquareBracketKeyword_2()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__StaticArrayModifier__Group__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__StaticArrayModifier__Group__0__Impl
	rule__StaticArrayModifier__Group__1
;
finally {
	restoreStackSize(stackSize);
}

rule__StaticArrayModifier__Group__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getStaticArrayModifierAccess().getLeftSquareBracketKeyword_0()); }
	'['
	{ after(grammarAccess.getStaticArrayModifierAccess().getLeftSquareBracketKeyword_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__StaticArrayModifier__Group__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__StaticArrayModifier__Group__1__Impl
	rule__StaticArrayModifier__Group__2
;
finally {
	restoreStackSize(stackSize);
}

rule__StaticArrayModifier__Group__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getStaticArrayModifierAccess().getArraySizeAssignment_1()); }
	(rule__StaticArrayModifier__ArraySizeAssignment_1)
	{ after(grammarAccess.getStaticArrayModifierAccess().getArraySizeAssignment_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__StaticArrayModifier__Group__2
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__StaticArrayModifier__Group__2__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__StaticArrayModifier__Group__2__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getStaticArrayModifierAccess().getRightSquareBracketKeyword_2()); }
	']'
	{ after(grammarAccess.getStaticArrayModifierAccess().getRightSquareBracketKeyword_2()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__ComposedProperty__Group__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__ComposedProperty__Group__0__Impl
	rule__ComposedProperty__Group__1
;
finally {
	restoreStackSize(stackSize);
}

rule__ComposedProperty__Group__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getComposedPropertyAccess().getComposedPropertyAction_0()); }
	()
	{ after(grammarAccess.getComposedPropertyAccess().getComposedPropertyAction_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__ComposedProperty__Group__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__ComposedProperty__Group__1__Impl
	rule__ComposedProperty__Group__2
;
finally {
	restoreStackSize(stackSize);
}

rule__ComposedProperty__Group__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getComposedPropertyAccess().getTypeKeyword_1()); }
	'Type'
	{ after(grammarAccess.getComposedPropertyAccess().getTypeKeyword_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__ComposedProperty__Group__2
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__ComposedProperty__Group__2__Impl
	rule__ComposedProperty__Group__3
;
finally {
	restoreStackSize(stackSize);
}

rule__ComposedProperty__Group__2__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getComposedPropertyAccess().getNameAssignment_2()); }
	(rule__ComposedProperty__NameAssignment_2)
	{ after(grammarAccess.getComposedPropertyAccess().getNameAssignment_2()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__ComposedProperty__Group__3
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__ComposedProperty__Group__3__Impl
	rule__ComposedProperty__Group__4
;
finally {
	restoreStackSize(stackSize);
}

rule__ComposedProperty__Group__3__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getComposedPropertyAccess().getArrayModifierAssignment_3()); }
	(rule__ComposedProperty__ArrayModifierAssignment_3)?
	{ after(grammarAccess.getComposedPropertyAccess().getArrayModifierAssignment_3()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__ComposedProperty__Group__4
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__ComposedProperty__Group__4__Impl
	rule__ComposedProperty__Group__5
;
finally {
	restoreStackSize(stackSize);
}

rule__ComposedProperty__Group__4__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getComposedPropertyAccess().getOfKeyword_4()); }
	'of'
	{ after(grammarAccess.getComposedPropertyAccess().getOfKeyword_4()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__ComposedProperty__Group__5
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__ComposedProperty__Group__5__Impl
	rule__ComposedProperty__Group__6
;
finally {
	restoreStackSize(stackSize);
}

rule__ComposedProperty__Group__5__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getComposedPropertyAccess().getCategoryKeyword_5()); }
	'Category'
	{ after(grammarAccess.getComposedPropertyAccess().getCategoryKeyword_5()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__ComposedProperty__Group__6
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__ComposedProperty__Group__6__Impl
	rule__ComposedProperty__Group__7
;
finally {
	restoreStackSize(stackSize);
}

rule__ComposedProperty__Group__6__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getComposedPropertyAccess().getTypeAssignment_6()); }
	(rule__ComposedProperty__TypeAssignment_6)
	{ after(grammarAccess.getComposedPropertyAccess().getTypeAssignment_6()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__ComposedProperty__Group__7
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__ComposedProperty__Group__7__Impl
	rule__ComposedProperty__Group__8
;
finally {
	restoreStackSize(stackSize);
}

rule__ComposedProperty__Group__7__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getComposedPropertyAccess().getUnorderedGroup_7()); }
	(rule__ComposedProperty__UnorderedGroup_7)
	{ after(grammarAccess.getComposedPropertyAccess().getUnorderedGroup_7()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__ComposedProperty__Group__8
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__ComposedProperty__Group__8__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__ComposedProperty__Group__8__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getComposedPropertyAccess().getSemicolonKeyword_8()); }
	';'
	{ after(grammarAccess.getComposedPropertyAccess().getSemicolonKeyword_8()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__ComposedProperty__Group_7_0__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__ComposedProperty__Group_7_0__0__Impl
	rule__ComposedProperty__Group_7_0__1
;
finally {
	restoreStackSize(stackSize);
}

rule__ComposedProperty__Group_7_0__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getComposedPropertyAccess().getDescriptionKeyword_7_0_0()); }
	'description'
	{ after(grammarAccess.getComposedPropertyAccess().getDescriptionKeyword_7_0_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__ComposedProperty__Group_7_0__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__ComposedProperty__Group_7_0__1__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__ComposedProperty__Group_7_0__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getComposedPropertyAccess().getDescriptionAssignment_7_0_1()); }
	(rule__ComposedProperty__DescriptionAssignment_7_0_1)
	{ after(grammarAccess.getComposedPropertyAccess().getDescriptionAssignment_7_0_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__ComposedProperty__Group_7_1__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__ComposedProperty__Group_7_1__0__Impl
	rule__ComposedProperty__Group_7_1__1
;
finally {
	restoreStackSize(stackSize);
}

rule__ComposedProperty__Group_7_1__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getComposedPropertyAccess().getQuantityKindKeyword_7_1_0()); }
	'quantityKind'
	{ after(grammarAccess.getComposedPropertyAccess().getQuantityKindKeyword_7_1_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__ComposedProperty__Group_7_1__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__ComposedProperty__Group_7_1__1__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__ComposedProperty__Group_7_1__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getComposedPropertyAccess().getQuantityKindNameAssignment_7_1_1()); }
	(rule__ComposedProperty__QuantityKindNameAssignment_7_1_1)
	{ after(grammarAccess.getComposedPropertyAccess().getQuantityKindNameAssignment_7_1_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__ComposedProperty__Group_7_2__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__ComposedProperty__Group_7_2__0__Impl
	rule__ComposedProperty__Group_7_2__1
;
finally {
	restoreStackSize(stackSize);
}

rule__ComposedProperty__Group_7_2__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getComposedPropertyAccess().getUnitKeyword_7_2_0()); }
	'unit'
	{ after(grammarAccess.getComposedPropertyAccess().getUnitKeyword_7_2_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__ComposedProperty__Group_7_2__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__ComposedProperty__Group_7_2__1__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__ComposedProperty__Group_7_2__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getComposedPropertyAccess().getUnitNameAssignment_7_2_1()); }
	(rule__ComposedProperty__UnitNameAssignment_7_2_1)
	{ after(grammarAccess.getComposedPropertyAccess().getUnitNameAssignment_7_2_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__IntProperty__Group__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__IntProperty__Group__0__Impl
	rule__IntProperty__Group__1
;
finally {
	restoreStackSize(stackSize);
}

rule__IntProperty__Group__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getIntPropertyAccess().getIntPropertyAction_0()); }
	()
	{ after(grammarAccess.getIntPropertyAccess().getIntPropertyAction_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__IntProperty__Group__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__IntProperty__Group__1__Impl
	rule__IntProperty__Group__2
;
finally {
	restoreStackSize(stackSize);
}

rule__IntProperty__Group__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getIntPropertyAccess().getIntPropertyKeyword_1()); }
	'IntProperty'
	{ after(grammarAccess.getIntPropertyAccess().getIntPropertyKeyword_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__IntProperty__Group__2
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__IntProperty__Group__2__Impl
	rule__IntProperty__Group__3
;
finally {
	restoreStackSize(stackSize);
}

rule__IntProperty__Group__2__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getIntPropertyAccess().getNameAssignment_2()); }
	(rule__IntProperty__NameAssignment_2)
	{ after(grammarAccess.getIntPropertyAccess().getNameAssignment_2()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__IntProperty__Group__3
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__IntProperty__Group__3__Impl
	rule__IntProperty__Group__4
;
finally {
	restoreStackSize(stackSize);
}

rule__IntProperty__Group__3__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getIntPropertyAccess().getArrayModifierAssignment_3()); }
	(rule__IntProperty__ArrayModifierAssignment_3)?
	{ after(grammarAccess.getIntPropertyAccess().getArrayModifierAssignment_3()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__IntProperty__Group__4
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__IntProperty__Group__4__Impl
	rule__IntProperty__Group__5
;
finally {
	restoreStackSize(stackSize);
}

rule__IntProperty__Group__4__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getIntPropertyAccess().getUnorderedGroup_4()); }
	(rule__IntProperty__UnorderedGroup_4)
	{ after(grammarAccess.getIntPropertyAccess().getUnorderedGroup_4()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__IntProperty__Group__5
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__IntProperty__Group__5__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__IntProperty__Group__5__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getIntPropertyAccess().getSemicolonKeyword_5()); }
	';'
	{ after(grammarAccess.getIntPropertyAccess().getSemicolonKeyword_5()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__IntProperty__Group_4_0__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__IntProperty__Group_4_0__0__Impl
	rule__IntProperty__Group_4_0__1
;
finally {
	restoreStackSize(stackSize);
}

rule__IntProperty__Group_4_0__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getIntPropertyAccess().getDescriptionKeyword_4_0_0()); }
	'description'
	{ after(grammarAccess.getIntPropertyAccess().getDescriptionKeyword_4_0_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__IntProperty__Group_4_0__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__IntProperty__Group_4_0__1__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__IntProperty__Group_4_0__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getIntPropertyAccess().getDescriptionAssignment_4_0_1()); }
	(rule__IntProperty__DescriptionAssignment_4_0_1)
	{ after(grammarAccess.getIntPropertyAccess().getDescriptionAssignment_4_0_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__IntProperty__Group_4_1__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__IntProperty__Group_4_1__0__Impl
	rule__IntProperty__Group_4_1__1
;
finally {
	restoreStackSize(stackSize);
}

rule__IntProperty__Group_4_1__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getIntPropertyAccess().getDefaultKeyword_4_1_0()); }
	'default'
	{ after(grammarAccess.getIntPropertyAccess().getDefaultKeyword_4_1_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__IntProperty__Group_4_1__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__IntProperty__Group_4_1__1__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__IntProperty__Group_4_1__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getIntPropertyAccess().getDefaultValueAssignment_4_1_1()); }
	(rule__IntProperty__DefaultValueAssignment_4_1_1)
	{ after(grammarAccess.getIntPropertyAccess().getDefaultValueAssignment_4_1_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__IntProperty__Group_4_2__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__IntProperty__Group_4_2__0__Impl
	rule__IntProperty__Group_4_2__1
;
finally {
	restoreStackSize(stackSize);
}

rule__IntProperty__Group_4_2__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getIntPropertyAccess().getQuantityKindKeyword_4_2_0()); }
	'quantityKind'
	{ after(grammarAccess.getIntPropertyAccess().getQuantityKindKeyword_4_2_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__IntProperty__Group_4_2__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__IntProperty__Group_4_2__1__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__IntProperty__Group_4_2__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getIntPropertyAccess().getQuantityKindNameAssignment_4_2_1()); }
	(rule__IntProperty__QuantityKindNameAssignment_4_2_1)
	{ after(grammarAccess.getIntPropertyAccess().getQuantityKindNameAssignment_4_2_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__IntProperty__Group_4_3__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__IntProperty__Group_4_3__0__Impl
	rule__IntProperty__Group_4_3__1
;
finally {
	restoreStackSize(stackSize);
}

rule__IntProperty__Group_4_3__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getIntPropertyAccess().getUnitKeyword_4_3_0()); }
	'unit'
	{ after(grammarAccess.getIntPropertyAccess().getUnitKeyword_4_3_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__IntProperty__Group_4_3__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__IntProperty__Group_4_3__1__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__IntProperty__Group_4_3__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getIntPropertyAccess().getUnitNameAssignment_4_3_1()); }
	(rule__IntProperty__UnitNameAssignment_4_3_1)
	{ after(grammarAccess.getIntPropertyAccess().getUnitNameAssignment_4_3_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__FloatProperty__Group__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__FloatProperty__Group__0__Impl
	rule__FloatProperty__Group__1
;
finally {
	restoreStackSize(stackSize);
}

rule__FloatProperty__Group__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getFloatPropertyAccess().getFloatPropertyAction_0()); }
	()
	{ after(grammarAccess.getFloatPropertyAccess().getFloatPropertyAction_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__FloatProperty__Group__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__FloatProperty__Group__1__Impl
	rule__FloatProperty__Group__2
;
finally {
	restoreStackSize(stackSize);
}

rule__FloatProperty__Group__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getFloatPropertyAccess().getFloatPropertyKeyword_1()); }
	'FloatProperty'
	{ after(grammarAccess.getFloatPropertyAccess().getFloatPropertyKeyword_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__FloatProperty__Group__2
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__FloatProperty__Group__2__Impl
	rule__FloatProperty__Group__3
;
finally {
	restoreStackSize(stackSize);
}

rule__FloatProperty__Group__2__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getFloatPropertyAccess().getNameAssignment_2()); }
	(rule__FloatProperty__NameAssignment_2)
	{ after(grammarAccess.getFloatPropertyAccess().getNameAssignment_2()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__FloatProperty__Group__3
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__FloatProperty__Group__3__Impl
	rule__FloatProperty__Group__4
;
finally {
	restoreStackSize(stackSize);
}

rule__FloatProperty__Group__3__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getFloatPropertyAccess().getArrayModifierAssignment_3()); }
	(rule__FloatProperty__ArrayModifierAssignment_3)?
	{ after(grammarAccess.getFloatPropertyAccess().getArrayModifierAssignment_3()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__FloatProperty__Group__4
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__FloatProperty__Group__4__Impl
	rule__FloatProperty__Group__5
;
finally {
	restoreStackSize(stackSize);
}

rule__FloatProperty__Group__4__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getFloatPropertyAccess().getUnorderedGroup_4()); }
	(rule__FloatProperty__UnorderedGroup_4)
	{ after(grammarAccess.getFloatPropertyAccess().getUnorderedGroup_4()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__FloatProperty__Group__5
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__FloatProperty__Group__5__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__FloatProperty__Group__5__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getFloatPropertyAccess().getSemicolonKeyword_5()); }
	';'
	{ after(grammarAccess.getFloatPropertyAccess().getSemicolonKeyword_5()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__FloatProperty__Group_4_0__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__FloatProperty__Group_4_0__0__Impl
	rule__FloatProperty__Group_4_0__1
;
finally {
	restoreStackSize(stackSize);
}

rule__FloatProperty__Group_4_0__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getFloatPropertyAccess().getDescriptionKeyword_4_0_0()); }
	'description'
	{ after(grammarAccess.getFloatPropertyAccess().getDescriptionKeyword_4_0_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__FloatProperty__Group_4_0__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__FloatProperty__Group_4_0__1__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__FloatProperty__Group_4_0__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getFloatPropertyAccess().getDescriptionAssignment_4_0_1()); }
	(rule__FloatProperty__DescriptionAssignment_4_0_1)
	{ after(grammarAccess.getFloatPropertyAccess().getDescriptionAssignment_4_0_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__FloatProperty__Group_4_1__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__FloatProperty__Group_4_1__0__Impl
	rule__FloatProperty__Group_4_1__1
;
finally {
	restoreStackSize(stackSize);
}

rule__FloatProperty__Group_4_1__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getFloatPropertyAccess().getDefaultKeyword_4_1_0()); }
	'default'
	{ after(grammarAccess.getFloatPropertyAccess().getDefaultKeyword_4_1_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__FloatProperty__Group_4_1__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__FloatProperty__Group_4_1__1__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__FloatProperty__Group_4_1__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getFloatPropertyAccess().getDefaultValueAssignment_4_1_1()); }
	(rule__FloatProperty__DefaultValueAssignment_4_1_1)
	{ after(grammarAccess.getFloatPropertyAccess().getDefaultValueAssignment_4_1_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__FloatProperty__Group_4_2__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__FloatProperty__Group_4_2__0__Impl
	rule__FloatProperty__Group_4_2__1
;
finally {
	restoreStackSize(stackSize);
}

rule__FloatProperty__Group_4_2__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getFloatPropertyAccess().getQuantityKindKeyword_4_2_0()); }
	'quantityKind'
	{ after(grammarAccess.getFloatPropertyAccess().getQuantityKindKeyword_4_2_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__FloatProperty__Group_4_2__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__FloatProperty__Group_4_2__1__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__FloatProperty__Group_4_2__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getFloatPropertyAccess().getQuantityKindNameAssignment_4_2_1()); }
	(rule__FloatProperty__QuantityKindNameAssignment_4_2_1)
	{ after(grammarAccess.getFloatPropertyAccess().getQuantityKindNameAssignment_4_2_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__FloatProperty__Group_4_3__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__FloatProperty__Group_4_3__0__Impl
	rule__FloatProperty__Group_4_3__1
;
finally {
	restoreStackSize(stackSize);
}

rule__FloatProperty__Group_4_3__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getFloatPropertyAccess().getUnitKeyword_4_3_0()); }
	'unit'
	{ after(grammarAccess.getFloatPropertyAccess().getUnitKeyword_4_3_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__FloatProperty__Group_4_3__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__FloatProperty__Group_4_3__1__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__FloatProperty__Group_4_3__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getFloatPropertyAccess().getUnitNameAssignment_4_3_1()); }
	(rule__FloatProperty__UnitNameAssignment_4_3_1)
	{ after(grammarAccess.getFloatPropertyAccess().getUnitNameAssignment_4_3_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__StringProperty__Group__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__StringProperty__Group__0__Impl
	rule__StringProperty__Group__1
;
finally {
	restoreStackSize(stackSize);
}

rule__StringProperty__Group__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getStringPropertyAccess().getStringPropertyAction_0()); }
	()
	{ after(grammarAccess.getStringPropertyAccess().getStringPropertyAction_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__StringProperty__Group__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__StringProperty__Group__1__Impl
	rule__StringProperty__Group__2
;
finally {
	restoreStackSize(stackSize);
}

rule__StringProperty__Group__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getStringPropertyAccess().getStringPropertyKeyword_1()); }
	'StringProperty'
	{ after(grammarAccess.getStringPropertyAccess().getStringPropertyKeyword_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__StringProperty__Group__2
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__StringProperty__Group__2__Impl
	rule__StringProperty__Group__3
;
finally {
	restoreStackSize(stackSize);
}

rule__StringProperty__Group__2__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getStringPropertyAccess().getNameAssignment_2()); }
	(rule__StringProperty__NameAssignment_2)
	{ after(grammarAccess.getStringPropertyAccess().getNameAssignment_2()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__StringProperty__Group__3
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__StringProperty__Group__3__Impl
	rule__StringProperty__Group__4
;
finally {
	restoreStackSize(stackSize);
}

rule__StringProperty__Group__3__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getStringPropertyAccess().getArrayModifierAssignment_3()); }
	(rule__StringProperty__ArrayModifierAssignment_3)?
	{ after(grammarAccess.getStringPropertyAccess().getArrayModifierAssignment_3()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__StringProperty__Group__4
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__StringProperty__Group__4__Impl
	rule__StringProperty__Group__5
;
finally {
	restoreStackSize(stackSize);
}

rule__StringProperty__Group__4__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getStringPropertyAccess().getUnorderedGroup_4()); }
	(rule__StringProperty__UnorderedGroup_4)
	{ after(grammarAccess.getStringPropertyAccess().getUnorderedGroup_4()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__StringProperty__Group__5
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__StringProperty__Group__5__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__StringProperty__Group__5__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getStringPropertyAccess().getSemicolonKeyword_5()); }
	';'
	{ after(grammarAccess.getStringPropertyAccess().getSemicolonKeyword_5()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__StringProperty__Group_4_0__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__StringProperty__Group_4_0__0__Impl
	rule__StringProperty__Group_4_0__1
;
finally {
	restoreStackSize(stackSize);
}

rule__StringProperty__Group_4_0__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getStringPropertyAccess().getDescriptionKeyword_4_0_0()); }
	'description'
	{ after(grammarAccess.getStringPropertyAccess().getDescriptionKeyword_4_0_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__StringProperty__Group_4_0__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__StringProperty__Group_4_0__1__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__StringProperty__Group_4_0__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getStringPropertyAccess().getDescriptionAssignment_4_0_1()); }
	(rule__StringProperty__DescriptionAssignment_4_0_1)
	{ after(grammarAccess.getStringPropertyAccess().getDescriptionAssignment_4_0_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__StringProperty__Group_4_1__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__StringProperty__Group_4_1__0__Impl
	rule__StringProperty__Group_4_1__1
;
finally {
	restoreStackSize(stackSize);
}

rule__StringProperty__Group_4_1__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getStringPropertyAccess().getDefaultKeyword_4_1_0()); }
	'default'
	{ after(grammarAccess.getStringPropertyAccess().getDefaultKeyword_4_1_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__StringProperty__Group_4_1__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__StringProperty__Group_4_1__1__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__StringProperty__Group_4_1__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getStringPropertyAccess().getDefaultValueAssignment_4_1_1()); }
	(rule__StringProperty__DefaultValueAssignment_4_1_1)
	{ after(grammarAccess.getStringPropertyAccess().getDefaultValueAssignment_4_1_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__BooleanProperty__Group__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__BooleanProperty__Group__0__Impl
	rule__BooleanProperty__Group__1
;
finally {
	restoreStackSize(stackSize);
}

rule__BooleanProperty__Group__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getBooleanPropertyAccess().getBooleanPropertyKeyword_0()); }
	'BooleanProperty'
	{ after(grammarAccess.getBooleanPropertyAccess().getBooleanPropertyKeyword_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__BooleanProperty__Group__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__BooleanProperty__Group__1__Impl
	rule__BooleanProperty__Group__2
;
finally {
	restoreStackSize(stackSize);
}

rule__BooleanProperty__Group__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getBooleanPropertyAccess().getNameAssignment_1()); }
	(rule__BooleanProperty__NameAssignment_1)
	{ after(grammarAccess.getBooleanPropertyAccess().getNameAssignment_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__BooleanProperty__Group__2
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__BooleanProperty__Group__2__Impl
	rule__BooleanProperty__Group__3
;
finally {
	restoreStackSize(stackSize);
}

rule__BooleanProperty__Group__2__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getBooleanPropertyAccess().getArrayModifierAssignment_2()); }
	(rule__BooleanProperty__ArrayModifierAssignment_2)?
	{ after(grammarAccess.getBooleanPropertyAccess().getArrayModifierAssignment_2()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__BooleanProperty__Group__3
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__BooleanProperty__Group__3__Impl
	rule__BooleanProperty__Group__4
;
finally {
	restoreStackSize(stackSize);
}

rule__BooleanProperty__Group__3__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getBooleanPropertyAccess().getUnorderedGroup_3()); }
	(rule__BooleanProperty__UnorderedGroup_3)
	{ after(grammarAccess.getBooleanPropertyAccess().getUnorderedGroup_3()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__BooleanProperty__Group__4
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__BooleanProperty__Group__4__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__BooleanProperty__Group__4__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getBooleanPropertyAccess().getSemicolonKeyword_4()); }
	';'
	{ after(grammarAccess.getBooleanPropertyAccess().getSemicolonKeyword_4()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__BooleanProperty__Group_3_0__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__BooleanProperty__Group_3_0__0__Impl
	rule__BooleanProperty__Group_3_0__1
;
finally {
	restoreStackSize(stackSize);
}

rule__BooleanProperty__Group_3_0__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getBooleanPropertyAccess().getDescriptionKeyword_3_0_0()); }
	'description'
	{ after(grammarAccess.getBooleanPropertyAccess().getDescriptionKeyword_3_0_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__BooleanProperty__Group_3_0__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__BooleanProperty__Group_3_0__1__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__BooleanProperty__Group_3_0__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getBooleanPropertyAccess().getDescriptionAssignment_3_0_1()); }
	(rule__BooleanProperty__DescriptionAssignment_3_0_1)
	{ after(grammarAccess.getBooleanPropertyAccess().getDescriptionAssignment_3_0_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__BooleanProperty__Group_3_1__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__BooleanProperty__Group_3_1__0__Impl
	rule__BooleanProperty__Group_3_1__1
;
finally {
	restoreStackSize(stackSize);
}

rule__BooleanProperty__Group_3_1__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getBooleanPropertyAccess().getDefaultKeyword_3_1_0()); }
	'default'
	{ after(grammarAccess.getBooleanPropertyAccess().getDefaultKeyword_3_1_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__BooleanProperty__Group_3_1__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__BooleanProperty__Group_3_1__1__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__BooleanProperty__Group_3_1__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getBooleanPropertyAccess().getDefaultValueAssignment_3_1_1()); }
	(rule__BooleanProperty__DefaultValueAssignment_3_1_1)
	{ after(grammarAccess.getBooleanPropertyAccess().getDefaultValueAssignment_3_1_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__EnumProperty__Group__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__EnumProperty__Group__0__Impl
	rule__EnumProperty__Group__1
;
finally {
	restoreStackSize(stackSize);
}

rule__EnumProperty__Group__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getEnumPropertyAccess().getEnumPropertyKeyword_0()); }
	'EnumProperty'
	{ after(grammarAccess.getEnumPropertyAccess().getEnumPropertyKeyword_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__EnumProperty__Group__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__EnumProperty__Group__1__Impl
	rule__EnumProperty__Group__2
;
finally {
	restoreStackSize(stackSize);
}

rule__EnumProperty__Group__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getEnumPropertyAccess().getNameAssignment_1()); }
	(rule__EnumProperty__NameAssignment_1)
	{ after(grammarAccess.getEnumPropertyAccess().getNameAssignment_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__EnumProperty__Group__2
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__EnumProperty__Group__2__Impl
	rule__EnumProperty__Group__3
;
finally {
	restoreStackSize(stackSize);
}

rule__EnumProperty__Group__2__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getEnumPropertyAccess().getArrayModifierAssignment_2()); }
	(rule__EnumProperty__ArrayModifierAssignment_2)?
	{ after(grammarAccess.getEnumPropertyAccess().getArrayModifierAssignment_2()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__EnumProperty__Group__3
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__EnumProperty__Group__3__Impl
	rule__EnumProperty__Group__4
;
finally {
	restoreStackSize(stackSize);
}

rule__EnumProperty__Group__3__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getEnumPropertyAccess().getUnorderedGroup_3()); }
	(rule__EnumProperty__UnorderedGroup_3)
	{ after(grammarAccess.getEnumPropertyAccess().getUnorderedGroup_3()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__EnumProperty__Group__4
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__EnumProperty__Group__4__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__EnumProperty__Group__4__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getEnumPropertyAccess().getSemicolonKeyword_4()); }
	';'
	{ after(grammarAccess.getEnumPropertyAccess().getSemicolonKeyword_4()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__EnumProperty__Group_3_0__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__EnumProperty__Group_3_0__0__Impl
	rule__EnumProperty__Group_3_0__1
;
finally {
	restoreStackSize(stackSize);
}

rule__EnumProperty__Group_3_0__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getEnumPropertyAccess().getDescriptionKeyword_3_0_0()); }
	'description'
	{ after(grammarAccess.getEnumPropertyAccess().getDescriptionKeyword_3_0_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__EnumProperty__Group_3_0__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__EnumProperty__Group_3_0__1__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__EnumProperty__Group_3_0__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getEnumPropertyAccess().getDescriptionAssignment_3_0_1()); }
	(rule__EnumProperty__DescriptionAssignment_3_0_1)
	{ after(grammarAccess.getEnumPropertyAccess().getDescriptionAssignment_3_0_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__EnumProperty__Group_3_1__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__EnumProperty__Group_3_1__0__Impl
	rule__EnumProperty__Group_3_1__1
;
finally {
	restoreStackSize(stackSize);
}

rule__EnumProperty__Group_3_1__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getEnumPropertyAccess().getQuantityKindKeyword_3_1_0()); }
	'quantityKind'
	{ after(grammarAccess.getEnumPropertyAccess().getQuantityKindKeyword_3_1_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__EnumProperty__Group_3_1__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__EnumProperty__Group_3_1__1__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__EnumProperty__Group_3_1__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getEnumPropertyAccess().getQuantityKindNameAssignment_3_1_1()); }
	(rule__EnumProperty__QuantityKindNameAssignment_3_1_1)
	{ after(grammarAccess.getEnumPropertyAccess().getQuantityKindNameAssignment_3_1_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__EnumProperty__Group_3_2__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__EnumProperty__Group_3_2__0__Impl
	rule__EnumProperty__Group_3_2__1
;
finally {
	restoreStackSize(stackSize);
}

rule__EnumProperty__Group_3_2__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getEnumPropertyAccess().getUnitKeyword_3_2_0()); }
	'unit'
	{ after(grammarAccess.getEnumPropertyAccess().getUnitKeyword_3_2_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__EnumProperty__Group_3_2__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__EnumProperty__Group_3_2__1__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__EnumProperty__Group_3_2__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getEnumPropertyAccess().getUnitNameAssignment_3_2_1()); }
	(rule__EnumProperty__UnitNameAssignment_3_2_1)
	{ after(grammarAccess.getEnumPropertyAccess().getUnitNameAssignment_3_2_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__EnumProperty__Group_3_3__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__EnumProperty__Group_3_3__0__Impl
	rule__EnumProperty__Group_3_3__1
;
finally {
	restoreStackSize(stackSize);
}

rule__EnumProperty__Group_3_3__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getEnumPropertyAccess().getValuesKeyword_3_3_0()); }
	'values'
	{ after(grammarAccess.getEnumPropertyAccess().getValuesKeyword_3_3_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__EnumProperty__Group_3_3__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__EnumProperty__Group_3_3__1__Impl
	rule__EnumProperty__Group_3_3__2
;
finally {
	restoreStackSize(stackSize);
}

rule__EnumProperty__Group_3_3__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getEnumPropertyAccess().getLeftSquareBracketKeyword_3_3_1()); }
	'['
	{ after(grammarAccess.getEnumPropertyAccess().getLeftSquareBracketKeyword_3_3_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__EnumProperty__Group_3_3__2
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__EnumProperty__Group_3_3__2__Impl
	rule__EnumProperty__Group_3_3__3
;
finally {
	restoreStackSize(stackSize);
}

rule__EnumProperty__Group_3_3__2__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getEnumPropertyAccess().getValuesAssignment_3_3_2()); }
	(rule__EnumProperty__ValuesAssignment_3_3_2)
	{ after(grammarAccess.getEnumPropertyAccess().getValuesAssignment_3_3_2()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__EnumProperty__Group_3_3__3
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__EnumProperty__Group_3_3__3__Impl
	rule__EnumProperty__Group_3_3__4
;
finally {
	restoreStackSize(stackSize);
}

rule__EnumProperty__Group_3_3__3__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getEnumPropertyAccess().getGroup_3_3_3()); }
	(rule__EnumProperty__Group_3_3_3__0)*
	{ after(grammarAccess.getEnumPropertyAccess().getGroup_3_3_3()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__EnumProperty__Group_3_3__4
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__EnumProperty__Group_3_3__4__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__EnumProperty__Group_3_3__4__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getEnumPropertyAccess().getRightSquareBracketKeyword_3_3_4()); }
	']'
	{ after(grammarAccess.getEnumPropertyAccess().getRightSquareBracketKeyword_3_3_4()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__EnumProperty__Group_3_3_3__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__EnumProperty__Group_3_3_3__0__Impl
	rule__EnumProperty__Group_3_3_3__1
;
finally {
	restoreStackSize(stackSize);
}

rule__EnumProperty__Group_3_3_3__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getEnumPropertyAccess().getCommaKeyword_3_3_3_0()); }
	','
	{ after(grammarAccess.getEnumPropertyAccess().getCommaKeyword_3_3_3_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__EnumProperty__Group_3_3_3__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__EnumProperty__Group_3_3_3__1__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__EnumProperty__Group_3_3_3__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getEnumPropertyAccess().getValuesAssignment_3_3_3_1()); }
	(rule__EnumProperty__ValuesAssignment_3_3_3_1)
	{ after(grammarAccess.getEnumPropertyAccess().getValuesAssignment_3_3_3_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__EnumProperty__Group_3_4__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__EnumProperty__Group_3_4__0__Impl
	rule__EnumProperty__Group_3_4__1
;
finally {
	restoreStackSize(stackSize);
}

rule__EnumProperty__Group_3_4__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getEnumPropertyAccess().getDefaultKeyword_3_4_0()); }
	'default'
	{ after(grammarAccess.getEnumPropertyAccess().getDefaultKeyword_3_4_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__EnumProperty__Group_3_4__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__EnumProperty__Group_3_4__1__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__EnumProperty__Group_3_4__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getEnumPropertyAccess().getDefaultValueAssignment_3_4_1()); }
	(rule__EnumProperty__DefaultValueAssignment_3_4_1)
	{ after(grammarAccess.getEnumPropertyAccess().getDefaultValueAssignment_3_4_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__EnumValueDefinition__Group__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__EnumValueDefinition__Group__0__Impl
	rule__EnumValueDefinition__Group__1
;
finally {
	restoreStackSize(stackSize);
}

rule__EnumValueDefinition__Group__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getEnumValueDefinitionAccess().getNameAssignment_0()); }
	(rule__EnumValueDefinition__NameAssignment_0)
	{ after(grammarAccess.getEnumValueDefinitionAccess().getNameAssignment_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__EnumValueDefinition__Group__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__EnumValueDefinition__Group__1__Impl
	rule__EnumValueDefinition__Group__2
;
finally {
	restoreStackSize(stackSize);
}

rule__EnumValueDefinition__Group__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getEnumValueDefinitionAccess().getEqualsSignKeyword_1()); }
	'='
	{ after(grammarAccess.getEnumValueDefinitionAccess().getEqualsSignKeyword_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__EnumValueDefinition__Group__2
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__EnumValueDefinition__Group__2__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__EnumValueDefinition__Group__2__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getEnumValueDefinitionAccess().getValueAssignment_2()); }
	(rule__EnumValueDefinition__ValueAssignment_2)
	{ after(grammarAccess.getEnumValueDefinitionAccess().getValueAssignment_2()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__ReferenceProperty__Group__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__ReferenceProperty__Group__0__Impl
	rule__ReferenceProperty__Group__1
;
finally {
	restoreStackSize(stackSize);
}

rule__ReferenceProperty__Group__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getReferencePropertyAccess().getReferenceKeyword_0()); }
	'Reference'
	{ after(grammarAccess.getReferencePropertyAccess().getReferenceKeyword_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__ReferenceProperty__Group__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__ReferenceProperty__Group__1__Impl
	rule__ReferenceProperty__Group__2
;
finally {
	restoreStackSize(stackSize);
}

rule__ReferenceProperty__Group__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getReferencePropertyAccess().getNameAssignment_1()); }
	(rule__ReferenceProperty__NameAssignment_1)
	{ after(grammarAccess.getReferencePropertyAccess().getNameAssignment_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__ReferenceProperty__Group__2
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__ReferenceProperty__Group__2__Impl
	rule__ReferenceProperty__Group__3
;
finally {
	restoreStackSize(stackSize);
}

rule__ReferenceProperty__Group__2__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getReferencePropertyAccess().getArrayModifierAssignment_2()); }
	(rule__ReferenceProperty__ArrayModifierAssignment_2)?
	{ after(grammarAccess.getReferencePropertyAccess().getArrayModifierAssignment_2()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__ReferenceProperty__Group__3
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__ReferenceProperty__Group__3__Impl
	rule__ReferenceProperty__Group__4
;
finally {
	restoreStackSize(stackSize);
}

rule__ReferenceProperty__Group__3__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getReferencePropertyAccess().getOfKeyword_3()); }
	'of'
	{ after(grammarAccess.getReferencePropertyAccess().getOfKeyword_3()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__ReferenceProperty__Group__4
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__ReferenceProperty__Group__4__Impl
	rule__ReferenceProperty__Group__5
;
finally {
	restoreStackSize(stackSize);
}

rule__ReferenceProperty__Group__4__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getReferencePropertyAccess().getTypeKeyword_4()); }
	'Type'
	{ after(grammarAccess.getReferencePropertyAccess().getTypeKeyword_4()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__ReferenceProperty__Group__5
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__ReferenceProperty__Group__5__Impl
	rule__ReferenceProperty__Group__6
;
finally {
	restoreStackSize(stackSize);
}

rule__ReferenceProperty__Group__5__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getReferencePropertyAccess().getReferenceTypeAssignment_5()); }
	(rule__ReferenceProperty__ReferenceTypeAssignment_5)
	{ after(grammarAccess.getReferencePropertyAccess().getReferenceTypeAssignment_5()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__ReferenceProperty__Group__6
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__ReferenceProperty__Group__6__Impl
	rule__ReferenceProperty__Group__7
;
finally {
	restoreStackSize(stackSize);
}

rule__ReferenceProperty__Group__6__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getReferencePropertyAccess().getGroup_6()); }
	(rule__ReferenceProperty__Group_6__0)?
	{ after(grammarAccess.getReferencePropertyAccess().getGroup_6()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__ReferenceProperty__Group__7
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__ReferenceProperty__Group__7__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__ReferenceProperty__Group__7__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getReferencePropertyAccess().getSemicolonKeyword_7()); }
	';'
	{ after(grammarAccess.getReferencePropertyAccess().getSemicolonKeyword_7()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__ReferenceProperty__Group_6__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__ReferenceProperty__Group_6__0__Impl
	rule__ReferenceProperty__Group_6__1
;
finally {
	restoreStackSize(stackSize);
}

rule__ReferenceProperty__Group_6__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getReferencePropertyAccess().getDescriptionKeyword_6_0()); }
	'description'
	{ after(grammarAccess.getReferencePropertyAccess().getDescriptionKeyword_6_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__ReferenceProperty__Group_6__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__ReferenceProperty__Group_6__1__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__ReferenceProperty__Group_6__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getReferencePropertyAccess().getDescriptionAssignment_6_1()); }
	(rule__ReferenceProperty__DescriptionAssignment_6_1)
	{ after(grammarAccess.getReferencePropertyAccess().getDescriptionAssignment_6_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__EReferenceProperty__Group__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__EReferenceProperty__Group__0__Impl
	rule__EReferenceProperty__Group__1
;
finally {
	restoreStackSize(stackSize);
}

rule__EReferenceProperty__Group__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getEReferencePropertyAccess().getEReferenceKeyword_0()); }
	'EReference'
	{ after(grammarAccess.getEReferencePropertyAccess().getEReferenceKeyword_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__EReferenceProperty__Group__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__EReferenceProperty__Group__1__Impl
	rule__EReferenceProperty__Group__2
;
finally {
	restoreStackSize(stackSize);
}

rule__EReferenceProperty__Group__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getEReferencePropertyAccess().getNameAssignment_1()); }
	(rule__EReferenceProperty__NameAssignment_1)
	{ after(grammarAccess.getEReferencePropertyAccess().getNameAssignment_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__EReferenceProperty__Group__2
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__EReferenceProperty__Group__2__Impl
	rule__EReferenceProperty__Group__3
;
finally {
	restoreStackSize(stackSize);
}

rule__EReferenceProperty__Group__2__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getEReferencePropertyAccess().getArrayModifierAssignment_2()); }
	(rule__EReferenceProperty__ArrayModifierAssignment_2)?
	{ after(grammarAccess.getEReferencePropertyAccess().getArrayModifierAssignment_2()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__EReferenceProperty__Group__3
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__EReferenceProperty__Group__3__Impl
	rule__EReferenceProperty__Group__4
;
finally {
	restoreStackSize(stackSize);
}

rule__EReferenceProperty__Group__3__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getEReferencePropertyAccess().getOfKeyword_3()); }
	'of'
	{ after(grammarAccess.getEReferencePropertyAccess().getOfKeyword_3()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__EReferenceProperty__Group__4
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__EReferenceProperty__Group__4__Impl
	rule__EReferenceProperty__Group__5
;
finally {
	restoreStackSize(stackSize);
}

rule__EReferenceProperty__Group__4__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getEReferencePropertyAccess().getTypeKeyword_4()); }
	'Type'
	{ after(grammarAccess.getEReferencePropertyAccess().getTypeKeyword_4()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__EReferenceProperty__Group__5
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__EReferenceProperty__Group__5__Impl
	rule__EReferenceProperty__Group__6
;
finally {
	restoreStackSize(stackSize);
}

rule__EReferenceProperty__Group__5__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getEReferencePropertyAccess().getReferenceTypeAssignment_5()); }
	(rule__EReferenceProperty__ReferenceTypeAssignment_5)
	{ after(grammarAccess.getEReferencePropertyAccess().getReferenceTypeAssignment_5()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__EReferenceProperty__Group__6
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__EReferenceProperty__Group__6__Impl
	rule__EReferenceProperty__Group__7
;
finally {
	restoreStackSize(stackSize);
}

rule__EReferenceProperty__Group__6__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getEReferencePropertyAccess().getGroup_6()); }
	(rule__EReferenceProperty__Group_6__0)?
	{ after(grammarAccess.getEReferencePropertyAccess().getGroup_6()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__EReferenceProperty__Group__7
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__EReferenceProperty__Group__7__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__EReferenceProperty__Group__7__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getEReferencePropertyAccess().getSemicolonKeyword_7()); }
	';'
	{ after(grammarAccess.getEReferencePropertyAccess().getSemicolonKeyword_7()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__EReferenceProperty__Group_6__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__EReferenceProperty__Group_6__0__Impl
	rule__EReferenceProperty__Group_6__1
;
finally {
	restoreStackSize(stackSize);
}

rule__EReferenceProperty__Group_6__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getEReferencePropertyAccess().getDescriptionKeyword_6_0()); }
	'description'
	{ after(grammarAccess.getEReferencePropertyAccess().getDescriptionKeyword_6_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__EReferenceProperty__Group_6__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__EReferenceProperty__Group_6__1__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__EReferenceProperty__Group_6__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getEReferencePropertyAccess().getDescriptionAssignment_6_1()); }
	(rule__EReferenceProperty__DescriptionAssignment_6_1)
	{ after(grammarAccess.getEReferencePropertyAccess().getDescriptionAssignment_6_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__ResourceProperty__Group__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__ResourceProperty__Group__0__Impl
	rule__ResourceProperty__Group__1
;
finally {
	restoreStackSize(stackSize);
}

rule__ResourceProperty__Group__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getResourcePropertyAccess().getResourceKeyword_0()); }
	'Resource'
	{ after(grammarAccess.getResourcePropertyAccess().getResourceKeyword_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__ResourceProperty__Group__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__ResourceProperty__Group__1__Impl
	rule__ResourceProperty__Group__2
;
finally {
	restoreStackSize(stackSize);
}

rule__ResourceProperty__Group__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getResourcePropertyAccess().getNameAssignment_1()); }
	(rule__ResourceProperty__NameAssignment_1)
	{ after(grammarAccess.getResourcePropertyAccess().getNameAssignment_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__ResourceProperty__Group__2
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__ResourceProperty__Group__2__Impl
	rule__ResourceProperty__Group__3
;
finally {
	restoreStackSize(stackSize);
}

rule__ResourceProperty__Group__2__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getResourcePropertyAccess().getArrayModifierAssignment_2()); }
	(rule__ResourceProperty__ArrayModifierAssignment_2)?
	{ after(grammarAccess.getResourcePropertyAccess().getArrayModifierAssignment_2()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__ResourceProperty__Group__3
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__ResourceProperty__Group__3__Impl
	rule__ResourceProperty__Group__4
;
finally {
	restoreStackSize(stackSize);
}

rule__ResourceProperty__Group__3__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getResourcePropertyAccess().getGroup_3()); }
	(rule__ResourceProperty__Group_3__0)?
	{ after(grammarAccess.getResourcePropertyAccess().getGroup_3()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__ResourceProperty__Group__4
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__ResourceProperty__Group__4__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__ResourceProperty__Group__4__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getResourcePropertyAccess().getSemicolonKeyword_4()); }
	';'
	{ after(grammarAccess.getResourcePropertyAccess().getSemicolonKeyword_4()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__ResourceProperty__Group_3__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__ResourceProperty__Group_3__0__Impl
	rule__ResourceProperty__Group_3__1
;
finally {
	restoreStackSize(stackSize);
}

rule__ResourceProperty__Group_3__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getResourcePropertyAccess().getDescriptionKeyword_3_0()); }
	'description'
	{ after(grammarAccess.getResourcePropertyAccess().getDescriptionKeyword_3_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__ResourceProperty__Group_3__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__ResourceProperty__Group_3__1__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__ResourceProperty__Group_3__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getResourcePropertyAccess().getDescriptionAssignment_3_1()); }
	(rule__ResourceProperty__DescriptionAssignment_3_1)
	{ after(grammarAccess.getResourcePropertyAccess().getDescriptionAssignment_3_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__EquationDefinition__Group__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__EquationDefinition__Group__0__Impl
	rule__EquationDefinition__Group__1
;
finally {
	restoreStackSize(stackSize);
}

rule__EquationDefinition__Group__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getEquationDefinitionAccess().getResultAssignment_0()); }
	(rule__EquationDefinition__ResultAssignment_0)
	{ after(grammarAccess.getEquationDefinitionAccess().getResultAssignment_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__EquationDefinition__Group__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__EquationDefinition__Group__1__Impl
	rule__EquationDefinition__Group__2
;
finally {
	restoreStackSize(stackSize);
}

rule__EquationDefinition__Group__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getEquationDefinitionAccess().getEqualsSignKeyword_1()); }
	'='
	{ after(grammarAccess.getEquationDefinitionAccess().getEqualsSignKeyword_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__EquationDefinition__Group__2
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__EquationDefinition__Group__2__Impl
	rule__EquationDefinition__Group__3
;
finally {
	restoreStackSize(stackSize);
}

rule__EquationDefinition__Group__2__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getEquationDefinitionAccess().getExpressionAssignment_2()); }
	(rule__EquationDefinition__ExpressionAssignment_2)
	{ after(grammarAccess.getEquationDefinitionAccess().getExpressionAssignment_2()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__EquationDefinition__Group__3
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__EquationDefinition__Group__3__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__EquationDefinition__Group__3__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getEquationDefinitionAccess().getSemicolonKeyword_3()); }
	';'
	{ after(grammarAccess.getEquationDefinitionAccess().getSemicolonKeyword_3()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__TypeDefinitionResult__Group__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__TypeDefinitionResult__Group__0__Impl
	rule__TypeDefinitionResult__Group__1
;
finally {
	restoreStackSize(stackSize);
}

rule__TypeDefinitionResult__Group__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getTypeDefinitionResultAccess().getTypeDefinitionResultAction_0()); }
	()
	{ after(grammarAccess.getTypeDefinitionResultAccess().getTypeDefinitionResultAction_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__TypeDefinitionResult__Group__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__TypeDefinitionResult__Group__1__Impl
	rule__TypeDefinitionResult__Group__2
;
finally {
	restoreStackSize(stackSize);
}

rule__TypeDefinitionResult__Group__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getTypeDefinitionResultAccess().getRefKeyword_1()); }
	'Ref:'
	{ after(grammarAccess.getTypeDefinitionResultAccess().getRefKeyword_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__TypeDefinitionResult__Group__2
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__TypeDefinitionResult__Group__2__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__TypeDefinitionResult__Group__2__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getTypeDefinitionResultAccess().getReferenceAssignment_2()); }
	(rule__TypeDefinitionResult__ReferenceAssignment_2)
	{ after(grammarAccess.getTypeDefinitionResultAccess().getReferenceAssignment_2()); }
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


rule__Version__Group__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__Version__Group__0__Impl
	rule__Version__Group__1
;
finally {
	restoreStackSize(stackSize);
}

rule__Version__Group__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getVersionAccess().getINTTerminalRuleCall_0()); }
	RULE_INT
	{ after(grammarAccess.getVersionAccess().getINTTerminalRuleCall_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__Version__Group__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__Version__Group__1__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__Version__Group__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getVersionAccess().getGroup_1()); }
	(rule__Version__Group_1__0)*
	{ after(grammarAccess.getVersionAccess().getGroup_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__Version__Group_1__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__Version__Group_1__0__Impl
	rule__Version__Group_1__1
;
finally {
	restoreStackSize(stackSize);
}

rule__Version__Group_1__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getVersionAccess().getFullStopKeyword_1_0()); }
	'.'
	{ after(grammarAccess.getVersionAccess().getFullStopKeyword_1_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__Version__Group_1__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__Version__Group_1__1__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__Version__Group_1__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getVersionAccess().getINTTerminalRuleCall_1_1()); }
	RULE_INT
	{ after(grammarAccess.getVersionAccess().getINTTerminalRuleCall_1_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__IntLiteralString__Group__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__IntLiteralString__Group__0__Impl
	rule__IntLiteralString__Group__1
;
finally {
	restoreStackSize(stackSize);
}

rule__IntLiteralString__Group__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getIntLiteralStringAccess().getHyphenMinusKeyword_0()); }
	('-')?
	{ after(grammarAccess.getIntLiteralStringAccess().getHyphenMinusKeyword_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__IntLiteralString__Group__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__IntLiteralString__Group__1__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__IntLiteralString__Group__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getIntLiteralStringAccess().getINTTerminalRuleCall_1()); }
	(RULE_INT)?
	{ after(grammarAccess.getIntLiteralStringAccess().getINTTerminalRuleCall_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__FloatLiteralString__Group__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__FloatLiteralString__Group__0__Impl
	rule__FloatLiteralString__Group__1
;
finally {
	restoreStackSize(stackSize);
}

rule__FloatLiteralString__Group__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getFloatLiteralStringAccess().getHyphenMinusKeyword_0()); }
	('-')?
	{ after(grammarAccess.getFloatLiteralStringAccess().getHyphenMinusKeyword_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__FloatLiteralString__Group__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__FloatLiteralString__Group__1__Impl
	rule__FloatLiteralString__Group__2
;
finally {
	restoreStackSize(stackSize);
}

rule__FloatLiteralString__Group__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getFloatLiteralStringAccess().getINTTerminalRuleCall_1()); }
	RULE_INT
	{ after(grammarAccess.getFloatLiteralStringAccess().getINTTerminalRuleCall_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__FloatLiteralString__Group__2
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__FloatLiteralString__Group__2__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__FloatLiteralString__Group__2__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getFloatLiteralStringAccess().getGroup_2()); }
	(rule__FloatLiteralString__Group_2__0)?
	{ after(grammarAccess.getFloatLiteralStringAccess().getGroup_2()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__FloatLiteralString__Group_2__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__FloatLiteralString__Group_2__0__Impl
	rule__FloatLiteralString__Group_2__1
;
finally {
	restoreStackSize(stackSize);
}

rule__FloatLiteralString__Group_2__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getFloatLiteralStringAccess().getFullStopKeyword_2_0()); }
	'.'
	{ after(grammarAccess.getFloatLiteralStringAccess().getFullStopKeyword_2_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__FloatLiteralString__Group_2__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__FloatLiteralString__Group_2__1__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__FloatLiteralString__Group_2__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getFloatLiteralStringAccess().getINTTerminalRuleCall_2_1()); }
	RULE_INT
	{ after(grammarAccess.getFloatLiteralStringAccess().getINTTerminalRuleCall_2_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__QualifiedNameWithWildcard__Group__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__QualifiedNameWithWildcard__Group__0__Impl
	rule__QualifiedNameWithWildcard__Group__1
;
finally {
	restoreStackSize(stackSize);
}

rule__QualifiedNameWithWildcard__Group__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getQualifiedNameWithWildcardAccess().getQualifiedNameParserRuleCall_0()); }
	ruleQualifiedName
	{ after(grammarAccess.getQualifiedNameWithWildcardAccess().getQualifiedNameParserRuleCall_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__QualifiedNameWithWildcard__Group__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__QualifiedNameWithWildcard__Group__1__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__QualifiedNameWithWildcard__Group__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getQualifiedNameWithWildcardAccess().getFullStopAsteriskKeyword_1()); }
	('.*')?
	{ after(grammarAccess.getQualifiedNameWithWildcardAccess().getFullStopAsteriskKeyword_1()); }
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


rule__Concept__UnorderedGroup_3
	@init {
		int stackSize = keepStackSize();
		getUnorderedGroupHelper().enter(grammarAccess.getConceptAccess().getUnorderedGroup_3());
	}
:
	rule__Concept__UnorderedGroup_3__0
	?
;
finally {
	getUnorderedGroupHelper().leave(grammarAccess.getConceptAccess().getUnorderedGroup_3());
	restoreStackSize(stackSize);
}

rule__Concept__UnorderedGroup_3__Impl
	@init {
		int stackSize = keepStackSize();
		boolean selected = false;
	}
:
		(
		( 
			{getUnorderedGroupHelper().canSelect(grammarAccess.getConceptAccess().getUnorderedGroup_3(), 0)}?=>(
				{
					getUnorderedGroupHelper().select(grammarAccess.getConceptAccess().getUnorderedGroup_3(), 0);
				}
				{
					selected = true;
				}
				(
					{ before(grammarAccess.getConceptAccess().getGroup_3_0()); }
					(rule__Concept__Group_3_0__0)
					{ after(grammarAccess.getConceptAccess().getGroup_3_0()); }
				)
			)
		)|
		( 
			{getUnorderedGroupHelper().canSelect(grammarAccess.getConceptAccess().getUnorderedGroup_3(), 1)}?=>(
				{
					getUnorderedGroupHelper().select(grammarAccess.getConceptAccess().getUnorderedGroup_3(), 1);
				}
				{
					selected = true;
				}
				(
					{ before(grammarAccess.getConceptAccess().getGroup_3_1()); }
					(rule__Concept__Group_3_1__0)
					{ after(grammarAccess.getConceptAccess().getGroup_3_1()); }
				)
			)
		)|
		( 
			{getUnorderedGroupHelper().canSelect(grammarAccess.getConceptAccess().getUnorderedGroup_3(), 2)}?=>(
				{
					getUnorderedGroupHelper().select(grammarAccess.getConceptAccess().getUnorderedGroup_3(), 2);
				}
				{
					selected = true;
				}
				(
					{ before(grammarAccess.getConceptAccess().getBetaAssignment_3_2()); }
					(rule__Concept__BetaAssignment_3_2)
					{ after(grammarAccess.getConceptAccess().getBetaAssignment_3_2()); }
				)
			)
		)|
		( 
			{getUnorderedGroupHelper().canSelect(grammarAccess.getConceptAccess().getUnorderedGroup_3(), 3)}?=>(
				{
					getUnorderedGroupHelper().select(grammarAccess.getConceptAccess().getUnorderedGroup_3(), 3);
				}
				{
					selected = true;
				}
				(
					{ before(grammarAccess.getConceptAccess().getGroup_3_3()); }
					(rule__Concept__Group_3_3__0)
					{ after(grammarAccess.getConceptAccess().getGroup_3_3()); }
				)
			)
		)|
		( 
			{getUnorderedGroupHelper().canSelect(grammarAccess.getConceptAccess().getUnorderedGroup_3(), 4)}?=>(
				{
					getUnorderedGroupHelper().select(grammarAccess.getConceptAccess().getUnorderedGroup_3(), 4);
				}
				{
					selected = true;
				}
				(
					{ before(grammarAccess.getConceptAccess().getDMFAssignment_3_4()); }
					(rule__Concept__DMFAssignment_3_4)
					{ after(grammarAccess.getConceptAccess().getDMFAssignment_3_4()); }
				)
			)
		)
		)
;
finally {
	if (selected)
		getUnorderedGroupHelper().returnFromSelection(grammarAccess.getConceptAccess().getUnorderedGroup_3());
	restoreStackSize(stackSize);
}

rule__Concept__UnorderedGroup_3__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__Concept__UnorderedGroup_3__Impl
	rule__Concept__UnorderedGroup_3__1?
;
finally {
	restoreStackSize(stackSize);
}

rule__Concept__UnorderedGroup_3__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__Concept__UnorderedGroup_3__Impl
	rule__Concept__UnorderedGroup_3__2?
;
finally {
	restoreStackSize(stackSize);
}

rule__Concept__UnorderedGroup_3__2
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__Concept__UnorderedGroup_3__Impl
	rule__Concept__UnorderedGroup_3__3?
;
finally {
	restoreStackSize(stackSize);
}

rule__Concept__UnorderedGroup_3__3
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__Concept__UnorderedGroup_3__Impl
	rule__Concept__UnorderedGroup_3__4?
;
finally {
	restoreStackSize(stackSize);
}

rule__Concept__UnorderedGroup_3__4
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__Concept__UnorderedGroup_3__Impl
;
finally {
	restoreStackSize(stackSize);
}


rule__Concept__UnorderedGroup_5
	@init {
		int stackSize = keepStackSize();
		getUnorderedGroupHelper().enter(grammarAccess.getConceptAccess().getUnorderedGroup_5());
	}
:
	rule__Concept__UnorderedGroup_5__0
	?
;
finally {
	getUnorderedGroupHelper().leave(grammarAccess.getConceptAccess().getUnorderedGroup_5());
	restoreStackSize(stackSize);
}

rule__Concept__UnorderedGroup_5__Impl
	@init {
		int stackSize = keepStackSize();
		boolean selected = false;
	}
:
		(
		( 
			{getUnorderedGroupHelper().canSelect(grammarAccess.getConceptAccess().getUnorderedGroup_5(), 0)}?=>(
				{
					getUnorderedGroupHelper().select(grammarAccess.getConceptAccess().getUnorderedGroup_5(), 0);
				}
				{
					selected = true;
				}
				(
					(
						{ before(grammarAccess.getConceptAccess().getImportsAssignment_5_0()); }
						(rule__Concept__ImportsAssignment_5_0)
						{ after(grammarAccess.getConceptAccess().getImportsAssignment_5_0()); }
					)
					(
						{ before(grammarAccess.getConceptAccess().getImportsAssignment_5_0()); }
						((rule__Concept__ImportsAssignment_5_0)=>rule__Concept__ImportsAssignment_5_0)*
						{ after(grammarAccess.getConceptAccess().getImportsAssignment_5_0()); }
					)
				)
			)
		)|
		( 
			{getUnorderedGroupHelper().canSelect(grammarAccess.getConceptAccess().getUnorderedGroup_5(), 1)}?=>(
				{
					getUnorderedGroupHelper().select(grammarAccess.getConceptAccess().getUnorderedGroup_5(), 1);
				}
				{
					selected = true;
				}
				(
					(
						{ before(grammarAccess.getConceptAccess().getEcoreImportsAssignment_5_1()); }
						(rule__Concept__EcoreImportsAssignment_5_1)
						{ after(grammarAccess.getConceptAccess().getEcoreImportsAssignment_5_1()); }
					)
					(
						{ before(grammarAccess.getConceptAccess().getEcoreImportsAssignment_5_1()); }
						((rule__Concept__EcoreImportsAssignment_5_1)=>rule__Concept__EcoreImportsAssignment_5_1)*
						{ after(grammarAccess.getConceptAccess().getEcoreImportsAssignment_5_1()); }
					)
				)
			)
		)
		)
;
finally {
	if (selected)
		getUnorderedGroupHelper().returnFromSelection(grammarAccess.getConceptAccess().getUnorderedGroup_5());
	restoreStackSize(stackSize);
}

rule__Concept__UnorderedGroup_5__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__Concept__UnorderedGroup_5__Impl
	rule__Concept__UnorderedGroup_5__1?
;
finally {
	restoreStackSize(stackSize);
}

rule__Concept__UnorderedGroup_5__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__Concept__UnorderedGroup_5__Impl
;
finally {
	restoreStackSize(stackSize);
}


rule__StructuralElement__UnorderedGroup_3
	@init {
		int stackSize = keepStackSize();
		getUnorderedGroupHelper().enter(grammarAccess.getStructuralElementAccess().getUnorderedGroup_3());
	}
:
	rule__StructuralElement__UnorderedGroup_3__0
	?
;
finally {
	getUnorderedGroupHelper().leave(grammarAccess.getStructuralElementAccess().getUnorderedGroup_3());
	restoreStackSize(stackSize);
}

rule__StructuralElement__UnorderedGroup_3__Impl
	@init {
		int stackSize = keepStackSize();
		boolean selected = false;
	}
:
		(
		( 
			{getUnorderedGroupHelper().canSelect(grammarAccess.getStructuralElementAccess().getUnorderedGroup_3(), 0)}?=>(
				{
					getUnorderedGroupHelper().select(grammarAccess.getStructuralElementAccess().getUnorderedGroup_3(), 0);
				}
				{
					selected = true;
				}
				(
					{ before(grammarAccess.getStructuralElementAccess().getGroup_3_0()); }
					(rule__StructuralElement__Group_3_0__0)
					{ after(grammarAccess.getStructuralElementAccess().getGroup_3_0()); }
				)
			)
		)|
		( 
			{getUnorderedGroupHelper().canSelect(grammarAccess.getStructuralElementAccess().getUnorderedGroup_3(), 1)}?=>(
				{
					getUnorderedGroupHelper().select(grammarAccess.getStructuralElementAccess().getUnorderedGroup_3(), 1);
				}
				{
					selected = true;
				}
				(
					{ before(grammarAccess.getStructuralElementAccess().getGroup_3_1()); }
					(rule__StructuralElement__Group_3_1__0)
					{ after(grammarAccess.getStructuralElementAccess().getGroup_3_1()); }
				)
			)
		)
		)
;
finally {
	if (selected)
		getUnorderedGroupHelper().returnFromSelection(grammarAccess.getStructuralElementAccess().getUnorderedGroup_3());
	restoreStackSize(stackSize);
}

rule__StructuralElement__UnorderedGroup_3__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__StructuralElement__UnorderedGroup_3__Impl
	rule__StructuralElement__UnorderedGroup_3__1?
;
finally {
	restoreStackSize(stackSize);
}

rule__StructuralElement__UnorderedGroup_3__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__StructuralElement__UnorderedGroup_3__Impl
;
finally {
	restoreStackSize(stackSize);
}


rule__StructuralElement__UnorderedGroup_5
	@init {
		int stackSize = keepStackSize();
		getUnorderedGroupHelper().enter(grammarAccess.getStructuralElementAccess().getUnorderedGroup_5());
	}
:
	rule__StructuralElement__UnorderedGroup_5__0
	?
;
finally {
	getUnorderedGroupHelper().leave(grammarAccess.getStructuralElementAccess().getUnorderedGroup_5());
	restoreStackSize(stackSize);
}

rule__StructuralElement__UnorderedGroup_5__Impl
	@init {
		int stackSize = keepStackSize();
		boolean selected = false;
	}
:
		(
		( 
			{getUnorderedGroupHelper().canSelect(grammarAccess.getStructuralElementAccess().getUnorderedGroup_5(), 0)}?=>(
				{
					getUnorderedGroupHelper().select(grammarAccess.getStructuralElementAccess().getUnorderedGroup_5(), 0);
				}
				{
					selected = true;
				}
				(
					{ before(grammarAccess.getStructuralElementAccess().getGroup_5_0()); }
					(rule__StructuralElement__Group_5_0__0)
					{ after(grammarAccess.getStructuralElementAccess().getGroup_5_0()); }
				)
			)
		)|
		( 
			{getUnorderedGroupHelper().canSelect(grammarAccess.getStructuralElementAccess().getUnorderedGroup_5(), 1)}?=>(
				{
					getUnorderedGroupHelper().select(grammarAccess.getStructuralElementAccess().getUnorderedGroup_5(), 1);
				}
				{
					selected = true;
				}
				(
					{ before(grammarAccess.getStructuralElementAccess().getGroup_5_1()); }
					(rule__StructuralElement__Group_5_1__0)
					{ after(grammarAccess.getStructuralElementAccess().getGroup_5_1()); }
				)
			)
		)|
		( 
			{getUnorderedGroupHelper().canSelect(grammarAccess.getStructuralElementAccess().getUnorderedGroup_5(), 2)}?=>(
				{
					getUnorderedGroupHelper().select(grammarAccess.getStructuralElementAccess().getUnorderedGroup_5(), 2);
				}
				{
					selected = true;
				}
				(
					{ before(grammarAccess.getStructuralElementAccess().getGroup_5_2()); }
					(rule__StructuralElement__Group_5_2__0)
					{ after(grammarAccess.getStructuralElementAccess().getGroup_5_2()); }
				)
			)
		)|
		( 
			{getUnorderedGroupHelper().canSelect(grammarAccess.getStructuralElementAccess().getUnorderedGroup_5(), 3)}?=>(
				{
					getUnorderedGroupHelper().select(grammarAccess.getStructuralElementAccess().getUnorderedGroup_5(), 3);
				}
				{
					selected = true;
				}
				(
					{ before(grammarAccess.getStructuralElementAccess().getGroup_5_3()); }
					(rule__StructuralElement__Group_5_3__0)
					{ after(grammarAccess.getStructuralElementAccess().getGroup_5_3()); }
				)
			)
		)
		)
;
finally {
	if (selected)
		getUnorderedGroupHelper().returnFromSelection(grammarAccess.getStructuralElementAccess().getUnorderedGroup_5());
	restoreStackSize(stackSize);
}

rule__StructuralElement__UnorderedGroup_5__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__StructuralElement__UnorderedGroup_5__Impl
	rule__StructuralElement__UnorderedGroup_5__1?
;
finally {
	restoreStackSize(stackSize);
}

rule__StructuralElement__UnorderedGroup_5__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__StructuralElement__UnorderedGroup_5__Impl
	rule__StructuralElement__UnorderedGroup_5__2?
;
finally {
	restoreStackSize(stackSize);
}

rule__StructuralElement__UnorderedGroup_5__2
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__StructuralElement__UnorderedGroup_5__Impl
	rule__StructuralElement__UnorderedGroup_5__3?
;
finally {
	restoreStackSize(stackSize);
}

rule__StructuralElement__UnorderedGroup_5__3
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__StructuralElement__UnorderedGroup_5__Impl
;
finally {
	restoreStackSize(stackSize);
}


rule__Category__UnorderedGroup_3
	@init {
		int stackSize = keepStackSize();
		getUnorderedGroupHelper().enter(grammarAccess.getCategoryAccess().getUnorderedGroup_3());
	}
:
	rule__Category__UnorderedGroup_3__0
	?
;
finally {
	getUnorderedGroupHelper().leave(grammarAccess.getCategoryAccess().getUnorderedGroup_3());
	restoreStackSize(stackSize);
}

rule__Category__UnorderedGroup_3__Impl
	@init {
		int stackSize = keepStackSize();
		boolean selected = false;
	}
:
		(
		( 
			{getUnorderedGroupHelper().canSelect(grammarAccess.getCategoryAccess().getUnorderedGroup_3(), 0)}?=>(
				{
					getUnorderedGroupHelper().select(grammarAccess.getCategoryAccess().getUnorderedGroup_3(), 0);
				}
				{
					selected = true;
				}
				(
					{ before(grammarAccess.getCategoryAccess().getGroup_3_0()); }
					(rule__Category__Group_3_0__0)
					{ after(grammarAccess.getCategoryAccess().getGroup_3_0()); }
				)
			)
		)|
		( 
			{getUnorderedGroupHelper().canSelect(grammarAccess.getCategoryAccess().getUnorderedGroup_3(), 1)}?=>(
				{
					getUnorderedGroupHelper().select(grammarAccess.getCategoryAccess().getUnorderedGroup_3(), 1);
				}
				{
					selected = true;
				}
				(
					{ before(grammarAccess.getCategoryAccess().getGroup_3_1()); }
					(rule__Category__Group_3_1__0)
					{ after(grammarAccess.getCategoryAccess().getGroup_3_1()); }
				)
			)
		)|
		( 
			{getUnorderedGroupHelper().canSelect(grammarAccess.getCategoryAccess().getUnorderedGroup_3(), 2)}?=>(
				{
					getUnorderedGroupHelper().select(grammarAccess.getCategoryAccess().getUnorderedGroup_3(), 2);
				}
				{
					selected = true;
				}
				(
					{ before(grammarAccess.getCategoryAccess().getGroup_3_2()); }
					(rule__Category__Group_3_2__0)
					{ after(grammarAccess.getCategoryAccess().getGroup_3_2()); }
				)
			)
		)
		)
;
finally {
	if (selected)
		getUnorderedGroupHelper().returnFromSelection(grammarAccess.getCategoryAccess().getUnorderedGroup_3());
	restoreStackSize(stackSize);
}

rule__Category__UnorderedGroup_3__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__Category__UnorderedGroup_3__Impl
	rule__Category__UnorderedGroup_3__1?
;
finally {
	restoreStackSize(stackSize);
}

rule__Category__UnorderedGroup_3__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__Category__UnorderedGroup_3__Impl
	rule__Category__UnorderedGroup_3__2?
;
finally {
	restoreStackSize(stackSize);
}

rule__Category__UnorderedGroup_3__2
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__Category__UnorderedGroup_3__Impl
;
finally {
	restoreStackSize(stackSize);
}


rule__Category__UnorderedGroup_5
	@init {
		int stackSize = keepStackSize();
		getUnorderedGroupHelper().enter(grammarAccess.getCategoryAccess().getUnorderedGroup_5());
	}
:
	rule__Category__UnorderedGroup_5__0
	?
;
finally {
	getUnorderedGroupHelper().leave(grammarAccess.getCategoryAccess().getUnorderedGroup_5());
	restoreStackSize(stackSize);
}

rule__Category__UnorderedGroup_5__Impl
	@init {
		int stackSize = keepStackSize();
		boolean selected = false;
	}
:
		(
		( 
			{getUnorderedGroupHelper().canSelect(grammarAccess.getCategoryAccess().getUnorderedGroup_5(), 0)}?=>(
				{
					getUnorderedGroupHelper().select(grammarAccess.getCategoryAccess().getUnorderedGroup_5(), 0);
				}
				{
					selected = true;
				}
				(
					{ before(grammarAccess.getCategoryAccess().getGroup_5_0()); }
					(rule__Category__Group_5_0__0)
					{ after(grammarAccess.getCategoryAccess().getGroup_5_0()); }
				)
			)
		)|
		( 
			{getUnorderedGroupHelper().canSelect(grammarAccess.getCategoryAccess().getUnorderedGroup_5(), 1)}?=>(
				{
					getUnorderedGroupHelper().select(grammarAccess.getCategoryAccess().getUnorderedGroup_5(), 1);
				}
				{
					selected = true;
				}
				(
					{ before(grammarAccess.getCategoryAccess().getGroup_5_1()); }
					(rule__Category__Group_5_1__0)
					{ after(grammarAccess.getCategoryAccess().getGroup_5_1()); }
				)
			)
		)|
		( 
			{getUnorderedGroupHelper().canSelect(grammarAccess.getCategoryAccess().getUnorderedGroup_5(), 2)}?=>(
				{
					getUnorderedGroupHelper().select(grammarAccess.getCategoryAccess().getUnorderedGroup_5(), 2);
				}
				{
					selected = true;
				}
				(
					{ before(grammarAccess.getCategoryAccess().getGroup_5_2()); }
					(rule__Category__Group_5_2__0)
					{ after(grammarAccess.getCategoryAccess().getGroup_5_2()); }
				)
			)
		)
		)
;
finally {
	if (selected)
		getUnorderedGroupHelper().returnFromSelection(grammarAccess.getCategoryAccess().getUnorderedGroup_5());
	restoreStackSize(stackSize);
}

rule__Category__UnorderedGroup_5__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__Category__UnorderedGroup_5__Impl
	rule__Category__UnorderedGroup_5__1?
;
finally {
	restoreStackSize(stackSize);
}

rule__Category__UnorderedGroup_5__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__Category__UnorderedGroup_5__Impl
	rule__Category__UnorderedGroup_5__2?
;
finally {
	restoreStackSize(stackSize);
}

rule__Category__UnorderedGroup_5__2
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__Category__UnorderedGroup_5__Impl
;
finally {
	restoreStackSize(stackSize);
}


rule__ComposedProperty__UnorderedGroup_7
	@init {
		int stackSize = keepStackSize();
		getUnorderedGroupHelper().enter(grammarAccess.getComposedPropertyAccess().getUnorderedGroup_7());
	}
:
	rule__ComposedProperty__UnorderedGroup_7__0
	?
;
finally {
	getUnorderedGroupHelper().leave(grammarAccess.getComposedPropertyAccess().getUnorderedGroup_7());
	restoreStackSize(stackSize);
}

rule__ComposedProperty__UnorderedGroup_7__Impl
	@init {
		int stackSize = keepStackSize();
		boolean selected = false;
	}
:
		(
		( 
			{getUnorderedGroupHelper().canSelect(grammarAccess.getComposedPropertyAccess().getUnorderedGroup_7(), 0)}?=>(
				{
					getUnorderedGroupHelper().select(grammarAccess.getComposedPropertyAccess().getUnorderedGroup_7(), 0);
				}
				{
					selected = true;
				}
				(
					{ before(grammarAccess.getComposedPropertyAccess().getGroup_7_0()); }
					(rule__ComposedProperty__Group_7_0__0)
					{ after(grammarAccess.getComposedPropertyAccess().getGroup_7_0()); }
				)
			)
		)|
		( 
			{getUnorderedGroupHelper().canSelect(grammarAccess.getComposedPropertyAccess().getUnorderedGroup_7(), 1)}?=>(
				{
					getUnorderedGroupHelper().select(grammarAccess.getComposedPropertyAccess().getUnorderedGroup_7(), 1);
				}
				{
					selected = true;
				}
				(
					{ before(grammarAccess.getComposedPropertyAccess().getGroup_7_1()); }
					(rule__ComposedProperty__Group_7_1__0)
					{ after(grammarAccess.getComposedPropertyAccess().getGroup_7_1()); }
				)
			)
		)|
		( 
			{getUnorderedGroupHelper().canSelect(grammarAccess.getComposedPropertyAccess().getUnorderedGroup_7(), 2)}?=>(
				{
					getUnorderedGroupHelper().select(grammarAccess.getComposedPropertyAccess().getUnorderedGroup_7(), 2);
				}
				{
					selected = true;
				}
				(
					{ before(grammarAccess.getComposedPropertyAccess().getGroup_7_2()); }
					(rule__ComposedProperty__Group_7_2__0)
					{ after(grammarAccess.getComposedPropertyAccess().getGroup_7_2()); }
				)
			)
		)
		)
;
finally {
	if (selected)
		getUnorderedGroupHelper().returnFromSelection(grammarAccess.getComposedPropertyAccess().getUnorderedGroup_7());
	restoreStackSize(stackSize);
}

rule__ComposedProperty__UnorderedGroup_7__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__ComposedProperty__UnorderedGroup_7__Impl
	rule__ComposedProperty__UnorderedGroup_7__1?
;
finally {
	restoreStackSize(stackSize);
}

rule__ComposedProperty__UnorderedGroup_7__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__ComposedProperty__UnorderedGroup_7__Impl
	rule__ComposedProperty__UnorderedGroup_7__2?
;
finally {
	restoreStackSize(stackSize);
}

rule__ComposedProperty__UnorderedGroup_7__2
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__ComposedProperty__UnorderedGroup_7__Impl
;
finally {
	restoreStackSize(stackSize);
}


rule__IntProperty__UnorderedGroup_4
	@init {
		int stackSize = keepStackSize();
		getUnorderedGroupHelper().enter(grammarAccess.getIntPropertyAccess().getUnorderedGroup_4());
	}
:
	rule__IntProperty__UnorderedGroup_4__0
	?
;
finally {
	getUnorderedGroupHelper().leave(grammarAccess.getIntPropertyAccess().getUnorderedGroup_4());
	restoreStackSize(stackSize);
}

rule__IntProperty__UnorderedGroup_4__Impl
	@init {
		int stackSize = keepStackSize();
		boolean selected = false;
	}
:
		(
		( 
			{getUnorderedGroupHelper().canSelect(grammarAccess.getIntPropertyAccess().getUnorderedGroup_4(), 0)}?=>(
				{
					getUnorderedGroupHelper().select(grammarAccess.getIntPropertyAccess().getUnorderedGroup_4(), 0);
				}
				{
					selected = true;
				}
				(
					{ before(grammarAccess.getIntPropertyAccess().getGroup_4_0()); }
					(rule__IntProperty__Group_4_0__0)
					{ after(grammarAccess.getIntPropertyAccess().getGroup_4_0()); }
				)
			)
		)|
		( 
			{getUnorderedGroupHelper().canSelect(grammarAccess.getIntPropertyAccess().getUnorderedGroup_4(), 1)}?=>(
				{
					getUnorderedGroupHelper().select(grammarAccess.getIntPropertyAccess().getUnorderedGroup_4(), 1);
				}
				{
					selected = true;
				}
				(
					{ before(grammarAccess.getIntPropertyAccess().getGroup_4_1()); }
					(rule__IntProperty__Group_4_1__0)
					{ after(grammarAccess.getIntPropertyAccess().getGroup_4_1()); }
				)
			)
		)|
		( 
			{getUnorderedGroupHelper().canSelect(grammarAccess.getIntPropertyAccess().getUnorderedGroup_4(), 2)}?=>(
				{
					getUnorderedGroupHelper().select(grammarAccess.getIntPropertyAccess().getUnorderedGroup_4(), 2);
				}
				{
					selected = true;
				}
				(
					{ before(grammarAccess.getIntPropertyAccess().getGroup_4_2()); }
					(rule__IntProperty__Group_4_2__0)
					{ after(grammarAccess.getIntPropertyAccess().getGroup_4_2()); }
				)
			)
		)|
		( 
			{getUnorderedGroupHelper().canSelect(grammarAccess.getIntPropertyAccess().getUnorderedGroup_4(), 3)}?=>(
				{
					getUnorderedGroupHelper().select(grammarAccess.getIntPropertyAccess().getUnorderedGroup_4(), 3);
				}
				{
					selected = true;
				}
				(
					{ before(grammarAccess.getIntPropertyAccess().getGroup_4_3()); }
					(rule__IntProperty__Group_4_3__0)
					{ after(grammarAccess.getIntPropertyAccess().getGroup_4_3()); }
				)
			)
		)
		)
;
finally {
	if (selected)
		getUnorderedGroupHelper().returnFromSelection(grammarAccess.getIntPropertyAccess().getUnorderedGroup_4());
	restoreStackSize(stackSize);
}

rule__IntProperty__UnorderedGroup_4__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__IntProperty__UnorderedGroup_4__Impl
	rule__IntProperty__UnorderedGroup_4__1?
;
finally {
	restoreStackSize(stackSize);
}

rule__IntProperty__UnorderedGroup_4__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__IntProperty__UnorderedGroup_4__Impl
	rule__IntProperty__UnorderedGroup_4__2?
;
finally {
	restoreStackSize(stackSize);
}

rule__IntProperty__UnorderedGroup_4__2
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__IntProperty__UnorderedGroup_4__Impl
	rule__IntProperty__UnorderedGroup_4__3?
;
finally {
	restoreStackSize(stackSize);
}

rule__IntProperty__UnorderedGroup_4__3
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__IntProperty__UnorderedGroup_4__Impl
;
finally {
	restoreStackSize(stackSize);
}


rule__FloatProperty__UnorderedGroup_4
	@init {
		int stackSize = keepStackSize();
		getUnorderedGroupHelper().enter(grammarAccess.getFloatPropertyAccess().getUnorderedGroup_4());
	}
:
	rule__FloatProperty__UnorderedGroup_4__0
	?
;
finally {
	getUnorderedGroupHelper().leave(grammarAccess.getFloatPropertyAccess().getUnorderedGroup_4());
	restoreStackSize(stackSize);
}

rule__FloatProperty__UnorderedGroup_4__Impl
	@init {
		int stackSize = keepStackSize();
		boolean selected = false;
	}
:
		(
		( 
			{getUnorderedGroupHelper().canSelect(grammarAccess.getFloatPropertyAccess().getUnorderedGroup_4(), 0)}?=>(
				{
					getUnorderedGroupHelper().select(grammarAccess.getFloatPropertyAccess().getUnorderedGroup_4(), 0);
				}
				{
					selected = true;
				}
				(
					{ before(grammarAccess.getFloatPropertyAccess().getGroup_4_0()); }
					(rule__FloatProperty__Group_4_0__0)
					{ after(grammarAccess.getFloatPropertyAccess().getGroup_4_0()); }
				)
			)
		)|
		( 
			{getUnorderedGroupHelper().canSelect(grammarAccess.getFloatPropertyAccess().getUnorderedGroup_4(), 1)}?=>(
				{
					getUnorderedGroupHelper().select(grammarAccess.getFloatPropertyAccess().getUnorderedGroup_4(), 1);
				}
				{
					selected = true;
				}
				(
					{ before(grammarAccess.getFloatPropertyAccess().getGroup_4_1()); }
					(rule__FloatProperty__Group_4_1__0)
					{ after(grammarAccess.getFloatPropertyAccess().getGroup_4_1()); }
				)
			)
		)|
		( 
			{getUnorderedGroupHelper().canSelect(grammarAccess.getFloatPropertyAccess().getUnorderedGroup_4(), 2)}?=>(
				{
					getUnorderedGroupHelper().select(grammarAccess.getFloatPropertyAccess().getUnorderedGroup_4(), 2);
				}
				{
					selected = true;
				}
				(
					{ before(grammarAccess.getFloatPropertyAccess().getGroup_4_2()); }
					(rule__FloatProperty__Group_4_2__0)
					{ after(grammarAccess.getFloatPropertyAccess().getGroup_4_2()); }
				)
			)
		)|
		( 
			{getUnorderedGroupHelper().canSelect(grammarAccess.getFloatPropertyAccess().getUnorderedGroup_4(), 3)}?=>(
				{
					getUnorderedGroupHelper().select(grammarAccess.getFloatPropertyAccess().getUnorderedGroup_4(), 3);
				}
				{
					selected = true;
				}
				(
					{ before(grammarAccess.getFloatPropertyAccess().getGroup_4_3()); }
					(rule__FloatProperty__Group_4_3__0)
					{ after(grammarAccess.getFloatPropertyAccess().getGroup_4_3()); }
				)
			)
		)
		)
;
finally {
	if (selected)
		getUnorderedGroupHelper().returnFromSelection(grammarAccess.getFloatPropertyAccess().getUnorderedGroup_4());
	restoreStackSize(stackSize);
}

rule__FloatProperty__UnorderedGroup_4__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__FloatProperty__UnorderedGroup_4__Impl
	rule__FloatProperty__UnorderedGroup_4__1?
;
finally {
	restoreStackSize(stackSize);
}

rule__FloatProperty__UnorderedGroup_4__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__FloatProperty__UnorderedGroup_4__Impl
	rule__FloatProperty__UnorderedGroup_4__2?
;
finally {
	restoreStackSize(stackSize);
}

rule__FloatProperty__UnorderedGroup_4__2
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__FloatProperty__UnorderedGroup_4__Impl
	rule__FloatProperty__UnorderedGroup_4__3?
;
finally {
	restoreStackSize(stackSize);
}

rule__FloatProperty__UnorderedGroup_4__3
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__FloatProperty__UnorderedGroup_4__Impl
;
finally {
	restoreStackSize(stackSize);
}


rule__StringProperty__UnorderedGroup_4
	@init {
		int stackSize = keepStackSize();
		getUnorderedGroupHelper().enter(grammarAccess.getStringPropertyAccess().getUnorderedGroup_4());
	}
:
	rule__StringProperty__UnorderedGroup_4__0
	?
;
finally {
	getUnorderedGroupHelper().leave(grammarAccess.getStringPropertyAccess().getUnorderedGroup_4());
	restoreStackSize(stackSize);
}

rule__StringProperty__UnorderedGroup_4__Impl
	@init {
		int stackSize = keepStackSize();
		boolean selected = false;
	}
:
		(
		( 
			{getUnorderedGroupHelper().canSelect(grammarAccess.getStringPropertyAccess().getUnorderedGroup_4(), 0)}?=>(
				{
					getUnorderedGroupHelper().select(grammarAccess.getStringPropertyAccess().getUnorderedGroup_4(), 0);
				}
				{
					selected = true;
				}
				(
					{ before(grammarAccess.getStringPropertyAccess().getGroup_4_0()); }
					(rule__StringProperty__Group_4_0__0)
					{ after(grammarAccess.getStringPropertyAccess().getGroup_4_0()); }
				)
			)
		)|
		( 
			{getUnorderedGroupHelper().canSelect(grammarAccess.getStringPropertyAccess().getUnorderedGroup_4(), 1)}?=>(
				{
					getUnorderedGroupHelper().select(grammarAccess.getStringPropertyAccess().getUnorderedGroup_4(), 1);
				}
				{
					selected = true;
				}
				(
					{ before(grammarAccess.getStringPropertyAccess().getGroup_4_1()); }
					(rule__StringProperty__Group_4_1__0)
					{ after(grammarAccess.getStringPropertyAccess().getGroup_4_1()); }
				)
			)
		)
		)
;
finally {
	if (selected)
		getUnorderedGroupHelper().returnFromSelection(grammarAccess.getStringPropertyAccess().getUnorderedGroup_4());
	restoreStackSize(stackSize);
}

rule__StringProperty__UnorderedGroup_4__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__StringProperty__UnorderedGroup_4__Impl
	rule__StringProperty__UnorderedGroup_4__1?
;
finally {
	restoreStackSize(stackSize);
}

rule__StringProperty__UnorderedGroup_4__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__StringProperty__UnorderedGroup_4__Impl
;
finally {
	restoreStackSize(stackSize);
}


rule__BooleanProperty__UnorderedGroup_3
	@init {
		int stackSize = keepStackSize();
		getUnorderedGroupHelper().enter(grammarAccess.getBooleanPropertyAccess().getUnorderedGroup_3());
	}
:
	rule__BooleanProperty__UnorderedGroup_3__0
	?
;
finally {
	getUnorderedGroupHelper().leave(grammarAccess.getBooleanPropertyAccess().getUnorderedGroup_3());
	restoreStackSize(stackSize);
}

rule__BooleanProperty__UnorderedGroup_3__Impl
	@init {
		int stackSize = keepStackSize();
		boolean selected = false;
	}
:
		(
		( 
			{getUnorderedGroupHelper().canSelect(grammarAccess.getBooleanPropertyAccess().getUnorderedGroup_3(), 0)}?=>(
				{
					getUnorderedGroupHelper().select(grammarAccess.getBooleanPropertyAccess().getUnorderedGroup_3(), 0);
				}
				{
					selected = true;
				}
				(
					{ before(grammarAccess.getBooleanPropertyAccess().getGroup_3_0()); }
					(rule__BooleanProperty__Group_3_0__0)
					{ after(grammarAccess.getBooleanPropertyAccess().getGroup_3_0()); }
				)
			)
		)|
		( 
			{getUnorderedGroupHelper().canSelect(grammarAccess.getBooleanPropertyAccess().getUnorderedGroup_3(), 1)}?=>(
				{
					getUnorderedGroupHelper().select(grammarAccess.getBooleanPropertyAccess().getUnorderedGroup_3(), 1);
				}
				{
					selected = true;
				}
				(
					{ before(grammarAccess.getBooleanPropertyAccess().getGroup_3_1()); }
					(rule__BooleanProperty__Group_3_1__0)
					{ after(grammarAccess.getBooleanPropertyAccess().getGroup_3_1()); }
				)
			)
		)
		)
;
finally {
	if (selected)
		getUnorderedGroupHelper().returnFromSelection(grammarAccess.getBooleanPropertyAccess().getUnorderedGroup_3());
	restoreStackSize(stackSize);
}

rule__BooleanProperty__UnorderedGroup_3__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__BooleanProperty__UnorderedGroup_3__Impl
	rule__BooleanProperty__UnorderedGroup_3__1?
;
finally {
	restoreStackSize(stackSize);
}

rule__BooleanProperty__UnorderedGroup_3__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__BooleanProperty__UnorderedGroup_3__Impl
;
finally {
	restoreStackSize(stackSize);
}


rule__EnumProperty__UnorderedGroup_3
	@init {
		int stackSize = keepStackSize();
		getUnorderedGroupHelper().enter(grammarAccess.getEnumPropertyAccess().getUnorderedGroup_3());
	}
:
	rule__EnumProperty__UnorderedGroup_3__0
	{getUnorderedGroupHelper().canLeave(grammarAccess.getEnumPropertyAccess().getUnorderedGroup_3())}?
;
finally {
	getUnorderedGroupHelper().leave(grammarAccess.getEnumPropertyAccess().getUnorderedGroup_3());
	restoreStackSize(stackSize);
}

rule__EnumProperty__UnorderedGroup_3__Impl
	@init {
		int stackSize = keepStackSize();
		boolean selected = false;
	}
:
		(
		( 
			{getUnorderedGroupHelper().canSelect(grammarAccess.getEnumPropertyAccess().getUnorderedGroup_3(), 0)}?=>(
				{
					getUnorderedGroupHelper().select(grammarAccess.getEnumPropertyAccess().getUnorderedGroup_3(), 0);
				}
				{
					selected = true;
				}
				(
					{ before(grammarAccess.getEnumPropertyAccess().getGroup_3_0()); }
					(rule__EnumProperty__Group_3_0__0)
					{ after(grammarAccess.getEnumPropertyAccess().getGroup_3_0()); }
				)
			)
		)|
		( 
			{getUnorderedGroupHelper().canSelect(grammarAccess.getEnumPropertyAccess().getUnorderedGroup_3(), 1)}?=>(
				{
					getUnorderedGroupHelper().select(grammarAccess.getEnumPropertyAccess().getUnorderedGroup_3(), 1);
				}
				{
					selected = true;
				}
				(
					{ before(grammarAccess.getEnumPropertyAccess().getGroup_3_1()); }
					(rule__EnumProperty__Group_3_1__0)
					{ after(grammarAccess.getEnumPropertyAccess().getGroup_3_1()); }
				)
			)
		)|
		( 
			{getUnorderedGroupHelper().canSelect(grammarAccess.getEnumPropertyAccess().getUnorderedGroup_3(), 2)}?=>(
				{
					getUnorderedGroupHelper().select(grammarAccess.getEnumPropertyAccess().getUnorderedGroup_3(), 2);
				}
				{
					selected = true;
				}
				(
					{ before(grammarAccess.getEnumPropertyAccess().getGroup_3_2()); }
					(rule__EnumProperty__Group_3_2__0)
					{ after(grammarAccess.getEnumPropertyAccess().getGroup_3_2()); }
				)
			)
		)|
		( 
			{getUnorderedGroupHelper().canSelect(grammarAccess.getEnumPropertyAccess().getUnorderedGroup_3(), 3)}?=>(
				{
					getUnorderedGroupHelper().select(grammarAccess.getEnumPropertyAccess().getUnorderedGroup_3(), 3);
				}
				{
					selected = true;
				}
				(
					{ before(grammarAccess.getEnumPropertyAccess().getGroup_3_3()); }
					(rule__EnumProperty__Group_3_3__0)
					{ after(grammarAccess.getEnumPropertyAccess().getGroup_3_3()); }
				)
			)
		)|
		( 
			{getUnorderedGroupHelper().canSelect(grammarAccess.getEnumPropertyAccess().getUnorderedGroup_3(), 4)}?=>(
				{
					getUnorderedGroupHelper().select(grammarAccess.getEnumPropertyAccess().getUnorderedGroup_3(), 4);
				}
				{
					selected = true;
				}
				(
					{ before(grammarAccess.getEnumPropertyAccess().getGroup_3_4()); }
					(rule__EnumProperty__Group_3_4__0)
					{ after(grammarAccess.getEnumPropertyAccess().getGroup_3_4()); }
				)
			)
		)
		)
;
finally {
	if (selected)
		getUnorderedGroupHelper().returnFromSelection(grammarAccess.getEnumPropertyAccess().getUnorderedGroup_3());
	restoreStackSize(stackSize);
}

rule__EnumProperty__UnorderedGroup_3__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__EnumProperty__UnorderedGroup_3__Impl
	rule__EnumProperty__UnorderedGroup_3__1?
;
finally {
	restoreStackSize(stackSize);
}

rule__EnumProperty__UnorderedGroup_3__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__EnumProperty__UnorderedGroup_3__Impl
	rule__EnumProperty__UnorderedGroup_3__2?
;
finally {
	restoreStackSize(stackSize);
}

rule__EnumProperty__UnorderedGroup_3__2
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__EnumProperty__UnorderedGroup_3__Impl
	rule__EnumProperty__UnorderedGroup_3__3?
;
finally {
	restoreStackSize(stackSize);
}

rule__EnumProperty__UnorderedGroup_3__3
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__EnumProperty__UnorderedGroup_3__Impl
	rule__EnumProperty__UnorderedGroup_3__4?
;
finally {
	restoreStackSize(stackSize);
}

rule__EnumProperty__UnorderedGroup_3__4
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__EnumProperty__UnorderedGroup_3__Impl
;
finally {
	restoreStackSize(stackSize);
}


rule__Concept__NameAssignment_2
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getConceptAccess().getNameQualifiedNameParserRuleCall_2_0()); }
		ruleQualifiedName
		{ after(grammarAccess.getConceptAccess().getNameQualifiedNameParserRuleCall_2_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__Concept__DisplayNameAssignment_3_0_1
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getConceptAccess().getDisplayNameEStringParserRuleCall_3_0_1_0()); }
		ruleEString
		{ after(grammarAccess.getConceptAccess().getDisplayNameEStringParserRuleCall_3_0_1_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__Concept__VersionAssignment_3_1_1
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getConceptAccess().getVersionVersionParserRuleCall_3_1_1_0()); }
		ruleVersion
		{ after(grammarAccess.getConceptAccess().getVersionVersionParserRuleCall_3_1_1_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__Concept__BetaAssignment_3_2
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getConceptAccess().getBetaBetaKeyword_3_2_0()); }
		(
			{ before(grammarAccess.getConceptAccess().getBetaBetaKeyword_3_2_0()); }
			'beta'
			{ after(grammarAccess.getConceptAccess().getBetaBetaKeyword_3_2_0()); }
		)
		{ after(grammarAccess.getConceptAccess().getBetaBetaKeyword_3_2_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__Concept__DescriptionAssignment_3_3_1
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getConceptAccess().getDescriptionEStringParserRuleCall_3_3_1_0()); }
		ruleEString
		{ after(grammarAccess.getConceptAccess().getDescriptionEStringParserRuleCall_3_3_1_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__Concept__DMFAssignment_3_4
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getConceptAccess().getDMFHasDMFKeyword_3_4_0()); }
		(
			{ before(grammarAccess.getConceptAccess().getDMFHasDMFKeyword_3_4_0()); }
			'hasDMF'
			{ after(grammarAccess.getConceptAccess().getDMFHasDMFKeyword_3_4_0()); }
		)
		{ after(grammarAccess.getConceptAccess().getDMFHasDMFKeyword_3_4_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__Concept__ImportsAssignment_5_0
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getConceptAccess().getImportsConceptImportParserRuleCall_5_0_0()); }
		ruleConceptImport
		{ after(grammarAccess.getConceptAccess().getImportsConceptImportParserRuleCall_5_0_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__Concept__EcoreImportsAssignment_5_1
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getConceptAccess().getEcoreImportsEcoreImportParserRuleCall_5_1_0()); }
		ruleEcoreImport
		{ after(grammarAccess.getConceptAccess().getEcoreImportsEcoreImportParserRuleCall_5_1_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__Concept__StructuralElementsAssignment_6
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getConceptAccess().getStructuralElementsStructuralElementParserRuleCall_6_0()); }
		ruleStructuralElement
		{ after(grammarAccess.getConceptAccess().getStructuralElementsStructuralElementParserRuleCall_6_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__Concept__RelationsAssignment_7
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getConceptAccess().getRelationsARelationParserRuleCall_7_0()); }
		ruleARelation
		{ after(grammarAccess.getConceptAccess().getRelationsARelationParserRuleCall_7_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__Concept__CategoriesAssignment_8
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getConceptAccess().getCategoriesCategoryParserRuleCall_8_0()); }
		ruleCategory
		{ after(grammarAccess.getConceptAccess().getCategoriesCategoryParserRuleCall_8_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__StructuralElement__NameAssignment_2
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getStructuralElementAccess().getNameIDTerminalRuleCall_2_0()); }
		RULE_ID
		{ after(grammarAccess.getStructuralElementAccess().getNameIDTerminalRuleCall_2_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__StructuralElement__ShortNameAssignment_3_0_1
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getStructuralElementAccess().getShortNameIDTerminalRuleCall_3_0_1_0()); }
		RULE_ID
		{ after(grammarAccess.getStructuralElementAccess().getShortNameIDTerminalRuleCall_3_0_1_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__StructuralElement__DescriptionAssignment_3_1_1
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getStructuralElementAccess().getDescriptionEStringParserRuleCall_3_1_1_0()); }
		ruleEString
		{ after(grammarAccess.getStructuralElementAccess().getDescriptionEStringParserRuleCall_3_1_1_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__StructuralElement__IsRootStructuralElementAssignment_5_0_0
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getStructuralElementAccess().getIsRootStructuralElementIsRootStructuralElementKeyword_5_0_0_0()); }
		(
			{ before(grammarAccess.getStructuralElementAccess().getIsRootStructuralElementIsRootStructuralElementKeyword_5_0_0_0()); }
			'IsRootStructuralElement'
			{ after(grammarAccess.getStructuralElementAccess().getIsRootStructuralElementIsRootStructuralElementKeyword_5_0_0_0()); }
		)
		{ after(grammarAccess.getStructuralElementAccess().getIsRootStructuralElementIsRootStructuralElementKeyword_5_0_0_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__StructuralElement__CanInheritFromAssignment_5_1_2_0_1
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getStructuralElementAccess().getCanInheritFromStructuralElementCrossReference_5_1_2_0_1_0()); }
		(
			{ before(grammarAccess.getStructuralElementAccess().getCanInheritFromStructuralElementQualifiedNameParserRuleCall_5_1_2_0_1_0_1()); }
			ruleQualifiedName
			{ after(grammarAccess.getStructuralElementAccess().getCanInheritFromStructuralElementQualifiedNameParserRuleCall_5_1_2_0_1_0_1()); }
		)
		{ after(grammarAccess.getStructuralElementAccess().getCanInheritFromStructuralElementCrossReference_5_1_2_0_1_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__StructuralElement__CanInheritFromAssignment_5_1_2_0_2_1
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getStructuralElementAccess().getCanInheritFromStructuralElementCrossReference_5_1_2_0_2_1_0()); }
		(
			{ before(grammarAccess.getStructuralElementAccess().getCanInheritFromStructuralElementQualifiedNameParserRuleCall_5_1_2_0_2_1_0_1()); }
			ruleQualifiedName
			{ after(grammarAccess.getStructuralElementAccess().getCanInheritFromStructuralElementQualifiedNameParserRuleCall_5_1_2_0_2_1_0_1()); }
		)
		{ after(grammarAccess.getStructuralElementAccess().getCanInheritFromStructuralElementCrossReference_5_1_2_0_2_1_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__StructuralElement__IsCanInheritFromAllAssignment_5_1_2_1
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getStructuralElementAccess().getIsCanInheritFromAllAllKeyword_5_1_2_1_0()); }
		(
			{ before(grammarAccess.getStructuralElementAccess().getIsCanInheritFromAllAllKeyword_5_1_2_1_0()); }
			'All'
			{ after(grammarAccess.getStructuralElementAccess().getIsCanInheritFromAllAllKeyword_5_1_2_1_0()); }
		)
		{ after(grammarAccess.getStructuralElementAccess().getIsCanInheritFromAllAllKeyword_5_1_2_1_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__StructuralElement__ApplicableForAssignment_5_2_2_0_1
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getStructuralElementAccess().getApplicableForStructuralElementCrossReference_5_2_2_0_1_0()); }
		(
			{ before(grammarAccess.getStructuralElementAccess().getApplicableForStructuralElementQualifiedNameParserRuleCall_5_2_2_0_1_0_1()); }
			ruleQualifiedName
			{ after(grammarAccess.getStructuralElementAccess().getApplicableForStructuralElementQualifiedNameParserRuleCall_5_2_2_0_1_0_1()); }
		)
		{ after(grammarAccess.getStructuralElementAccess().getApplicableForStructuralElementCrossReference_5_2_2_0_1_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__StructuralElement__ApplicableForAssignment_5_2_2_0_2_1
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getStructuralElementAccess().getApplicableForStructuralElementCrossReference_5_2_2_0_2_1_0()); }
		(
			{ before(grammarAccess.getStructuralElementAccess().getApplicableForStructuralElementQualifiedNameParserRuleCall_5_2_2_0_2_1_0_1()); }
			ruleQualifiedName
			{ after(grammarAccess.getStructuralElementAccess().getApplicableForStructuralElementQualifiedNameParserRuleCall_5_2_2_0_2_1_0_1()); }
		)
		{ after(grammarAccess.getStructuralElementAccess().getApplicableForStructuralElementCrossReference_5_2_2_0_2_1_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__StructuralElement__IsApplicableForAllAssignment_5_2_2_1
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getStructuralElementAccess().getIsApplicableForAllAllKeyword_5_2_2_1_0()); }
		(
			{ before(grammarAccess.getStructuralElementAccess().getIsApplicableForAllAllKeyword_5_2_2_1_0()); }
			'All'
			{ after(grammarAccess.getStructuralElementAccess().getIsApplicableForAllAllKeyword_5_2_2_1_0()); }
		)
		{ after(grammarAccess.getStructuralElementAccess().getIsApplicableForAllAllKeyword_5_2_2_1_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__StructuralElement__CardinalityAssignment_5_3_1
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getStructuralElementAccess().getCardinalityINTTerminalRuleCall_5_3_1_0()); }
		RULE_INT
		{ after(grammarAccess.getStructuralElementAccess().getCardinalityINTTerminalRuleCall_5_3_1_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__GeneralRelation__NameAssignment_2
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getGeneralRelationAccess().getNameIDTerminalRuleCall_2_0()); }
		RULE_ID
		{ after(grammarAccess.getGeneralRelationAccess().getNameIDTerminalRuleCall_2_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__GeneralRelation__DescriptionAssignment_3_1
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getGeneralRelationAccess().getDescriptionEStringParserRuleCall_3_1_0()); }
		ruleEString
		{ after(grammarAccess.getGeneralRelationAccess().getDescriptionEStringParserRuleCall_3_1_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__GeneralRelation__ReferencedTypeAssignment_7
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getGeneralRelationAccess().getReferencedTypeStructuralElementCrossReference_7_0()); }
		(
			{ before(grammarAccess.getGeneralRelationAccess().getReferencedTypeStructuralElementQualifiedNameParserRuleCall_7_0_1()); }
			ruleQualifiedName
			{ after(grammarAccess.getGeneralRelationAccess().getReferencedTypeStructuralElementQualifiedNameParserRuleCall_7_0_1()); }
		)
		{ after(grammarAccess.getGeneralRelationAccess().getReferencedTypeStructuralElementCrossReference_7_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__GeneralRelation__ApplicableForAssignment_9_2_0_1
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getGeneralRelationAccess().getApplicableForStructuralElementCrossReference_9_2_0_1_0()); }
		(
			{ before(grammarAccess.getGeneralRelationAccess().getApplicableForStructuralElementQualifiedNameParserRuleCall_9_2_0_1_0_1()); }
			ruleQualifiedName
			{ after(grammarAccess.getGeneralRelationAccess().getApplicableForStructuralElementQualifiedNameParserRuleCall_9_2_0_1_0_1()); }
		)
		{ after(grammarAccess.getGeneralRelationAccess().getApplicableForStructuralElementCrossReference_9_2_0_1_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__GeneralRelation__ApplicableForAssignment_9_2_0_2_1
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getGeneralRelationAccess().getApplicableForStructuralElementCrossReference_9_2_0_2_1_0()); }
		(
			{ before(grammarAccess.getGeneralRelationAccess().getApplicableForStructuralElementQualifiedNameParserRuleCall_9_2_0_2_1_0_1()); }
			ruleQualifiedName
			{ after(grammarAccess.getGeneralRelationAccess().getApplicableForStructuralElementQualifiedNameParserRuleCall_9_2_0_2_1_0_1()); }
		)
		{ after(grammarAccess.getGeneralRelationAccess().getApplicableForStructuralElementCrossReference_9_2_0_2_1_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__GeneralRelation__IsApplicableForAllAssignment_9_2_1
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getGeneralRelationAccess().getIsApplicableForAllAllKeyword_9_2_1_0()); }
		(
			{ before(grammarAccess.getGeneralRelationAccess().getIsApplicableForAllAllKeyword_9_2_1_0()); }
			'All'
			{ after(grammarAccess.getGeneralRelationAccess().getIsApplicableForAllAllKeyword_9_2_1_0()); }
		)
		{ after(grammarAccess.getGeneralRelationAccess().getIsApplicableForAllAllKeyword_9_2_1_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__GeneralRelation__CardinalityAssignment_10_1
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getGeneralRelationAccess().getCardinalityINTTerminalRuleCall_10_1_0()); }
		RULE_INT
		{ after(grammarAccess.getGeneralRelationAccess().getCardinalityINTTerminalRuleCall_10_1_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__Category__NameAssignment_2
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getCategoryAccess().getNameIDTerminalRuleCall_2_0()); }
		RULE_ID
		{ after(grammarAccess.getCategoryAccess().getNameIDTerminalRuleCall_2_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__Category__ExtendsCategoryAssignment_3_0_1
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getCategoryAccess().getExtendsCategoryCategoryCrossReference_3_0_1_0()); }
		(
			{ before(grammarAccess.getCategoryAccess().getExtendsCategoryCategoryQualifiedNameParserRuleCall_3_0_1_0_1()); }
			ruleQualifiedName
			{ after(grammarAccess.getCategoryAccess().getExtendsCategoryCategoryQualifiedNameParserRuleCall_3_0_1_0_1()); }
		)
		{ after(grammarAccess.getCategoryAccess().getExtendsCategoryCategoryCrossReference_3_0_1_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__Category__ShortNameAssignment_3_1_1
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getCategoryAccess().getShortNameIDTerminalRuleCall_3_1_1_0()); }
		RULE_ID
		{ after(grammarAccess.getCategoryAccess().getShortNameIDTerminalRuleCall_3_1_1_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__Category__DescriptionAssignment_3_2_1
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getCategoryAccess().getDescriptionEStringParserRuleCall_3_2_1_0()); }
		ruleEString
		{ after(grammarAccess.getCategoryAccess().getDescriptionEStringParserRuleCall_3_2_1_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__Category__IsAbstractAssignment_5_0_0
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getCategoryAccess().getIsAbstractIsAbstractKeyword_5_0_0_0()); }
		(
			{ before(grammarAccess.getCategoryAccess().getIsAbstractIsAbstractKeyword_5_0_0_0()); }
			'IsAbstract'
			{ after(grammarAccess.getCategoryAccess().getIsAbstractIsAbstractKeyword_5_0_0_0()); }
		)
		{ after(grammarAccess.getCategoryAccess().getIsAbstractIsAbstractKeyword_5_0_0_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__Category__ApplicableForAssignment_5_1_2_0_1
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getCategoryAccess().getApplicableForStructuralElementCrossReference_5_1_2_0_1_0()); }
		(
			{ before(grammarAccess.getCategoryAccess().getApplicableForStructuralElementQualifiedNameParserRuleCall_5_1_2_0_1_0_1()); }
			ruleQualifiedName
			{ after(grammarAccess.getCategoryAccess().getApplicableForStructuralElementQualifiedNameParserRuleCall_5_1_2_0_1_0_1()); }
		)
		{ after(grammarAccess.getCategoryAccess().getApplicableForStructuralElementCrossReference_5_1_2_0_1_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__Category__ApplicableForAssignment_5_1_2_0_2_1
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getCategoryAccess().getApplicableForStructuralElementCrossReference_5_1_2_0_2_1_0()); }
		(
			{ before(grammarAccess.getCategoryAccess().getApplicableForStructuralElementQualifiedNameParserRuleCall_5_1_2_0_2_1_0_1()); }
			ruleQualifiedName
			{ after(grammarAccess.getCategoryAccess().getApplicableForStructuralElementQualifiedNameParserRuleCall_5_1_2_0_2_1_0_1()); }
		)
		{ after(grammarAccess.getCategoryAccess().getApplicableForStructuralElementCrossReference_5_1_2_0_2_1_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__Category__IsApplicableForAllAssignment_5_1_2_1
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getCategoryAccess().getIsApplicableForAllAllKeyword_5_1_2_1_0()); }
		(
			{ before(grammarAccess.getCategoryAccess().getIsApplicableForAllAllKeyword_5_1_2_1_0()); }
			'All'
			{ after(grammarAccess.getCategoryAccess().getIsApplicableForAllAllKeyword_5_1_2_1_0()); }
		)
		{ after(grammarAccess.getCategoryAccess().getIsApplicableForAllAllKeyword_5_1_2_1_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__Category__CardinalityAssignment_5_2_1
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getCategoryAccess().getCardinalityINTTerminalRuleCall_5_2_1_0()); }
		RULE_INT
		{ after(grammarAccess.getCategoryAccess().getCardinalityINTTerminalRuleCall_5_2_1_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__Category__PropertiesAssignment_6
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getCategoryAccess().getPropertiesAPropertyParserRuleCall_6_0()); }
		ruleAProperty
		{ after(grammarAccess.getCategoryAccess().getPropertiesAPropertyParserRuleCall_6_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__Category__EquationDefinitionsAssignment_7
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getCategoryAccess().getEquationDefinitionsEquationDefinitionParserRuleCall_7_0()); }
		ruleEquationDefinition
		{ after(grammarAccess.getCategoryAccess().getEquationDefinitionsEquationDefinitionParserRuleCall_7_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__ConceptImport__ImportedNamespaceAssignment_2
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getConceptImportAccess().getImportedNamespaceQualifiedNameWithWildcardParserRuleCall_2_0()); }
		ruleQualifiedNameWithWildcard
		{ after(grammarAccess.getConceptImportAccess().getImportedNamespaceQualifiedNameWithWildcardParserRuleCall_2_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__EcoreImport__ImportedNsURIAssignment_2
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getEcoreImportAccess().getImportedNsURISTRINGTerminalRuleCall_2_0()); }
		RULE_STRING
		{ after(grammarAccess.getEcoreImportAccess().getImportedNsURISTRINGTerminalRuleCall_2_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__EcoreImport__ImportedGenModelAssignment_3_1
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getEcoreImportAccess().getImportedGenModelSTRINGTerminalRuleCall_3_1_0()); }
		RULE_STRING
		{ after(grammarAccess.getEcoreImportAccess().getImportedGenModelSTRINGTerminalRuleCall_3_1_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__StaticArrayModifier__ArraySizeAssignment_1
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getStaticArrayModifierAccess().getArraySizeINTTerminalRuleCall_1_0()); }
		RULE_INT
		{ after(grammarAccess.getStaticArrayModifierAccess().getArraySizeINTTerminalRuleCall_1_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__ComposedProperty__NameAssignment_2
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getComposedPropertyAccess().getNameIDTerminalRuleCall_2_0()); }
		RULE_ID
		{ after(grammarAccess.getComposedPropertyAccess().getNameIDTerminalRuleCall_2_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__ComposedProperty__ArrayModifierAssignment_3
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getComposedPropertyAccess().getArrayModifierArrayModifierParserRuleCall_3_0()); }
		ruleArrayModifier
		{ after(grammarAccess.getComposedPropertyAccess().getArrayModifierArrayModifierParserRuleCall_3_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__ComposedProperty__TypeAssignment_6
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getComposedPropertyAccess().getTypeCategoryCrossReference_6_0()); }
		(
			{ before(grammarAccess.getComposedPropertyAccess().getTypeCategoryQualifiedNameParserRuleCall_6_0_1()); }
			ruleQualifiedName
			{ after(grammarAccess.getComposedPropertyAccess().getTypeCategoryQualifiedNameParserRuleCall_6_0_1()); }
		)
		{ after(grammarAccess.getComposedPropertyAccess().getTypeCategoryCrossReference_6_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__ComposedProperty__DescriptionAssignment_7_0_1
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getComposedPropertyAccess().getDescriptionEStringParserRuleCall_7_0_1_0()); }
		ruleEString
		{ after(grammarAccess.getComposedPropertyAccess().getDescriptionEStringParserRuleCall_7_0_1_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__ComposedProperty__QuantityKindNameAssignment_7_1_1
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getComposedPropertyAccess().getQuantityKindNameEStringParserRuleCall_7_1_1_0()); }
		ruleEString
		{ after(grammarAccess.getComposedPropertyAccess().getQuantityKindNameEStringParserRuleCall_7_1_1_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__ComposedProperty__UnitNameAssignment_7_2_1
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getComposedPropertyAccess().getUnitNameEStringParserRuleCall_7_2_1_0()); }
		ruleEString
		{ after(grammarAccess.getComposedPropertyAccess().getUnitNameEStringParserRuleCall_7_2_1_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__IntProperty__NameAssignment_2
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getIntPropertyAccess().getNameIDTerminalRuleCall_2_0()); }
		RULE_ID
		{ after(grammarAccess.getIntPropertyAccess().getNameIDTerminalRuleCall_2_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__IntProperty__ArrayModifierAssignment_3
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getIntPropertyAccess().getArrayModifierArrayModifierParserRuleCall_3_0()); }
		ruleArrayModifier
		{ after(grammarAccess.getIntPropertyAccess().getArrayModifierArrayModifierParserRuleCall_3_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__IntProperty__DescriptionAssignment_4_0_1
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getIntPropertyAccess().getDescriptionEStringParserRuleCall_4_0_1_0()); }
		ruleEString
		{ after(grammarAccess.getIntPropertyAccess().getDescriptionEStringParserRuleCall_4_0_1_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__IntProperty__DefaultValueAssignment_4_1_1
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getIntPropertyAccess().getDefaultValueIntLiteralStringParserRuleCall_4_1_1_0()); }
		ruleIntLiteralString
		{ after(grammarAccess.getIntPropertyAccess().getDefaultValueIntLiteralStringParserRuleCall_4_1_1_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__IntProperty__QuantityKindNameAssignment_4_2_1
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getIntPropertyAccess().getQuantityKindNameEStringParserRuleCall_4_2_1_0()); }
		ruleEString
		{ after(grammarAccess.getIntPropertyAccess().getQuantityKindNameEStringParserRuleCall_4_2_1_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__IntProperty__UnitNameAssignment_4_3_1
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getIntPropertyAccess().getUnitNameEStringParserRuleCall_4_3_1_0()); }
		ruleEString
		{ after(grammarAccess.getIntPropertyAccess().getUnitNameEStringParserRuleCall_4_3_1_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__FloatProperty__NameAssignment_2
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getFloatPropertyAccess().getNameIDTerminalRuleCall_2_0()); }
		RULE_ID
		{ after(grammarAccess.getFloatPropertyAccess().getNameIDTerminalRuleCall_2_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__FloatProperty__ArrayModifierAssignment_3
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getFloatPropertyAccess().getArrayModifierArrayModifierParserRuleCall_3_0()); }
		ruleArrayModifier
		{ after(grammarAccess.getFloatPropertyAccess().getArrayModifierArrayModifierParserRuleCall_3_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__FloatProperty__DescriptionAssignment_4_0_1
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getFloatPropertyAccess().getDescriptionEStringParserRuleCall_4_0_1_0()); }
		ruleEString
		{ after(grammarAccess.getFloatPropertyAccess().getDescriptionEStringParserRuleCall_4_0_1_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__FloatProperty__DefaultValueAssignment_4_1_1
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getFloatPropertyAccess().getDefaultValueFloatLiteralStringParserRuleCall_4_1_1_0()); }
		ruleFloatLiteralString
		{ after(grammarAccess.getFloatPropertyAccess().getDefaultValueFloatLiteralStringParserRuleCall_4_1_1_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__FloatProperty__QuantityKindNameAssignment_4_2_1
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getFloatPropertyAccess().getQuantityKindNameEStringParserRuleCall_4_2_1_0()); }
		ruleEString
		{ after(grammarAccess.getFloatPropertyAccess().getQuantityKindNameEStringParserRuleCall_4_2_1_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__FloatProperty__UnitNameAssignment_4_3_1
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getFloatPropertyAccess().getUnitNameEStringParserRuleCall_4_3_1_0()); }
		ruleEString
		{ after(grammarAccess.getFloatPropertyAccess().getUnitNameEStringParserRuleCall_4_3_1_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__StringProperty__NameAssignment_2
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getStringPropertyAccess().getNameIDTerminalRuleCall_2_0()); }
		RULE_ID
		{ after(grammarAccess.getStringPropertyAccess().getNameIDTerminalRuleCall_2_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__StringProperty__ArrayModifierAssignment_3
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getStringPropertyAccess().getArrayModifierArrayModifierParserRuleCall_3_0()); }
		ruleArrayModifier
		{ after(grammarAccess.getStringPropertyAccess().getArrayModifierArrayModifierParserRuleCall_3_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__StringProperty__DescriptionAssignment_4_0_1
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getStringPropertyAccess().getDescriptionEStringParserRuleCall_4_0_1_0()); }
		ruleEString
		{ after(grammarAccess.getStringPropertyAccess().getDescriptionEStringParserRuleCall_4_0_1_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__StringProperty__DefaultValueAssignment_4_1_1
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getStringPropertyAccess().getDefaultValueEStringParserRuleCall_4_1_1_0()); }
		ruleEString
		{ after(grammarAccess.getStringPropertyAccess().getDefaultValueEStringParserRuleCall_4_1_1_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__BooleanProperty__NameAssignment_1
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getBooleanPropertyAccess().getNameIDTerminalRuleCall_1_0()); }
		RULE_ID
		{ after(grammarAccess.getBooleanPropertyAccess().getNameIDTerminalRuleCall_1_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__BooleanProperty__ArrayModifierAssignment_2
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getBooleanPropertyAccess().getArrayModifierArrayModifierParserRuleCall_2_0()); }
		ruleArrayModifier
		{ after(grammarAccess.getBooleanPropertyAccess().getArrayModifierArrayModifierParserRuleCall_2_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__BooleanProperty__DescriptionAssignment_3_0_1
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getBooleanPropertyAccess().getDescriptionEStringParserRuleCall_3_0_1_0()); }
		ruleEString
		{ after(grammarAccess.getBooleanPropertyAccess().getDescriptionEStringParserRuleCall_3_0_1_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__BooleanProperty__DefaultValueAssignment_3_1_1
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getBooleanPropertyAccess().getDefaultValueBooleanLiteralStringParserRuleCall_3_1_1_0()); }
		ruleBooleanLiteralString
		{ after(grammarAccess.getBooleanPropertyAccess().getDefaultValueBooleanLiteralStringParserRuleCall_3_1_1_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__EnumProperty__NameAssignment_1
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getEnumPropertyAccess().getNameIDTerminalRuleCall_1_0()); }
		RULE_ID
		{ after(grammarAccess.getEnumPropertyAccess().getNameIDTerminalRuleCall_1_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__EnumProperty__ArrayModifierAssignment_2
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getEnumPropertyAccess().getArrayModifierArrayModifierParserRuleCall_2_0()); }
		ruleArrayModifier
		{ after(grammarAccess.getEnumPropertyAccess().getArrayModifierArrayModifierParserRuleCall_2_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__EnumProperty__DescriptionAssignment_3_0_1
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getEnumPropertyAccess().getDescriptionEStringParserRuleCall_3_0_1_0()); }
		ruleEString
		{ after(grammarAccess.getEnumPropertyAccess().getDescriptionEStringParserRuleCall_3_0_1_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__EnumProperty__QuantityKindNameAssignment_3_1_1
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getEnumPropertyAccess().getQuantityKindNameEStringParserRuleCall_3_1_1_0()); }
		ruleEString
		{ after(grammarAccess.getEnumPropertyAccess().getQuantityKindNameEStringParserRuleCall_3_1_1_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__EnumProperty__UnitNameAssignment_3_2_1
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getEnumPropertyAccess().getUnitNameEStringParserRuleCall_3_2_1_0()); }
		ruleEString
		{ after(grammarAccess.getEnumPropertyAccess().getUnitNameEStringParserRuleCall_3_2_1_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__EnumProperty__ValuesAssignment_3_3_2
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getEnumPropertyAccess().getValuesEnumValueDefinitionParserRuleCall_3_3_2_0()); }
		ruleEnumValueDefinition
		{ after(grammarAccess.getEnumPropertyAccess().getValuesEnumValueDefinitionParserRuleCall_3_3_2_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__EnumProperty__ValuesAssignment_3_3_3_1
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getEnumPropertyAccess().getValuesEnumValueDefinitionParserRuleCall_3_3_3_1_0()); }
		ruleEnumValueDefinition
		{ after(grammarAccess.getEnumPropertyAccess().getValuesEnumValueDefinitionParserRuleCall_3_3_3_1_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__EnumProperty__DefaultValueAssignment_3_4_1
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getEnumPropertyAccess().getDefaultValueEnumValueDefinitionCrossReference_3_4_1_0()); }
		(
			{ before(grammarAccess.getEnumPropertyAccess().getDefaultValueEnumValueDefinitionIDTerminalRuleCall_3_4_1_0_1()); }
			RULE_ID
			{ after(grammarAccess.getEnumPropertyAccess().getDefaultValueEnumValueDefinitionIDTerminalRuleCall_3_4_1_0_1()); }
		)
		{ after(grammarAccess.getEnumPropertyAccess().getDefaultValueEnumValueDefinitionCrossReference_3_4_1_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__EnumValueDefinition__NameAssignment_0
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getEnumValueDefinitionAccess().getNameIDTerminalRuleCall_0_0()); }
		RULE_ID
		{ after(grammarAccess.getEnumValueDefinitionAccess().getNameIDTerminalRuleCall_0_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__EnumValueDefinition__ValueAssignment_2
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getEnumValueDefinitionAccess().getValueAlternatives_2_0()); }
		(rule__EnumValueDefinition__ValueAlternatives_2_0)
		{ after(grammarAccess.getEnumValueDefinitionAccess().getValueAlternatives_2_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__ReferenceProperty__NameAssignment_1
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getReferencePropertyAccess().getNameIDTerminalRuleCall_1_0()); }
		RULE_ID
		{ after(grammarAccess.getReferencePropertyAccess().getNameIDTerminalRuleCall_1_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__ReferenceProperty__ArrayModifierAssignment_2
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getReferencePropertyAccess().getArrayModifierArrayModifierParserRuleCall_2_0()); }
		ruleArrayModifier
		{ after(grammarAccess.getReferencePropertyAccess().getArrayModifierArrayModifierParserRuleCall_2_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__ReferenceProperty__ReferenceTypeAssignment_5
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getReferencePropertyAccess().getReferenceTypeATypeDefinitionCrossReference_5_0()); }
		(
			{ before(grammarAccess.getReferencePropertyAccess().getReferenceTypeATypeDefinitionQualifiedNameParserRuleCall_5_0_1()); }
			ruleQualifiedName
			{ after(grammarAccess.getReferencePropertyAccess().getReferenceTypeATypeDefinitionQualifiedNameParserRuleCall_5_0_1()); }
		)
		{ after(grammarAccess.getReferencePropertyAccess().getReferenceTypeATypeDefinitionCrossReference_5_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__ReferenceProperty__DescriptionAssignment_6_1
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getReferencePropertyAccess().getDescriptionEStringParserRuleCall_6_1_0()); }
		ruleEString
		{ after(grammarAccess.getReferencePropertyAccess().getDescriptionEStringParserRuleCall_6_1_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__EReferenceProperty__NameAssignment_1
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getEReferencePropertyAccess().getNameIDTerminalRuleCall_1_0()); }
		RULE_ID
		{ after(grammarAccess.getEReferencePropertyAccess().getNameIDTerminalRuleCall_1_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__EReferenceProperty__ArrayModifierAssignment_2
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getEReferencePropertyAccess().getArrayModifierArrayModifierParserRuleCall_2_0()); }
		ruleArrayModifier
		{ after(grammarAccess.getEReferencePropertyAccess().getArrayModifierArrayModifierParserRuleCall_2_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__EReferenceProperty__ReferenceTypeAssignment_5
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getEReferencePropertyAccess().getReferenceTypeEClassCrossReference_5_0()); }
		(
			{ before(grammarAccess.getEReferencePropertyAccess().getReferenceTypeEClassQualifiedNameParserRuleCall_5_0_1()); }
			ruleQualifiedName
			{ after(grammarAccess.getEReferencePropertyAccess().getReferenceTypeEClassQualifiedNameParserRuleCall_5_0_1()); }
		)
		{ after(grammarAccess.getEReferencePropertyAccess().getReferenceTypeEClassCrossReference_5_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__EReferenceProperty__DescriptionAssignment_6_1
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getEReferencePropertyAccess().getDescriptionEStringParserRuleCall_6_1_0()); }
		ruleEString
		{ after(grammarAccess.getEReferencePropertyAccess().getDescriptionEStringParserRuleCall_6_1_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__ResourceProperty__NameAssignment_1
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getResourcePropertyAccess().getNameIDTerminalRuleCall_1_0()); }
		RULE_ID
		{ after(grammarAccess.getResourcePropertyAccess().getNameIDTerminalRuleCall_1_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__ResourceProperty__ArrayModifierAssignment_2
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getResourcePropertyAccess().getArrayModifierArrayModifierParserRuleCall_2_0()); }
		ruleArrayModifier
		{ after(grammarAccess.getResourcePropertyAccess().getArrayModifierArrayModifierParserRuleCall_2_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__ResourceProperty__DescriptionAssignment_3_1
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getResourcePropertyAccess().getDescriptionEStringParserRuleCall_3_1_0()); }
		ruleEString
		{ after(grammarAccess.getResourcePropertyAccess().getDescriptionEStringParserRuleCall_3_1_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__EquationDefinition__ResultAssignment_0
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getEquationDefinitionAccess().getResultEquationDefinitionResultParserRuleCall_0_0()); }
		ruleEquationDefinitionResult
		{ after(grammarAccess.getEquationDefinitionAccess().getResultEquationDefinitionResultParserRuleCall_0_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__EquationDefinition__ExpressionAssignment_2
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getEquationDefinitionAccess().getExpressionAdditionAndSubtractionParserRuleCall_2_0()); }
		ruleAdditionAndSubtraction
		{ after(grammarAccess.getEquationDefinitionAccess().getExpressionAdditionAndSubtractionParserRuleCall_2_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__TypeDefinitionResult__ReferenceAssignment_2
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getTypeDefinitionResultAccess().getReferenceATypeDefinitionCrossReference_2_0()); }
		(
			{ before(grammarAccess.getTypeDefinitionResultAccess().getReferenceATypeDefinitionQualifiedNameParserRuleCall_2_0_1()); }
			ruleQualifiedName
			{ after(grammarAccess.getTypeDefinitionResultAccess().getReferenceATypeDefinitionQualifiedNameParserRuleCall_2_0_1()); }
		)
		{ after(grammarAccess.getTypeDefinitionResultAccess().getReferenceATypeDefinitionCrossReference_2_0()); }
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

rule__ReferencedDefinitionInput__ReferenceAssignment
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getReferencedDefinitionInputAccess().getReferenceIEquationDefinitionInputCrossReference_0()); }
		(
			{ before(grammarAccess.getReferencedDefinitionInputAccess().getReferenceIEquationDefinitionInputQualifiedNameParserRuleCall_0_1()); }
			ruleQualifiedName
			{ after(grammarAccess.getReferencedDefinitionInputAccess().getReferenceIEquationDefinitionInputQualifiedNameParserRuleCall_0_1()); }
		)
		{ after(grammarAccess.getReferencedDefinitionInputAccess().getReferenceIEquationDefinitionInputCrossReference_0()); }
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
		{ before(grammarAccess.getNumberLiteralAccess().getValueFloatLiteralStringParserRuleCall_1_0()); }
		ruleFloatLiteralString
		{ after(grammarAccess.getNumberLiteralAccess().getValueFloatLiteralStringParserRuleCall_1_0()); }
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
