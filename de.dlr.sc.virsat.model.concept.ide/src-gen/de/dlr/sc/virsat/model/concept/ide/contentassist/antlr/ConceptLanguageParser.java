/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.concept.ide.contentassist.antlr;

import com.google.inject.Inject;
import de.dlr.sc.virsat.model.concept.ide.contentassist.antlr.internal.InternalConceptLanguageParser;
import de.dlr.sc.virsat.model.concept.services.ConceptLanguageGrammarAccess;
import java.util.HashMap;
import java.util.Map;
import org.eclipse.xtext.AbstractElement;
import org.eclipse.xtext.ide.editor.contentassist.antlr.AbstractContentAssistParser;

public class ConceptLanguageParser extends AbstractContentAssistParser {

	@Inject
	private ConceptLanguageGrammarAccess grammarAccess;

	private Map<AbstractElement, String> nameMappings;

	@Override
	protected InternalConceptLanguageParser createParser() {
		InternalConceptLanguageParser result = new InternalConceptLanguageParser(null);
		result.setGrammarAccess(grammarAccess);
		return result;
	}

	@Override
	protected String getRuleName(AbstractElement element) {
		if (nameMappings == null) {
			nameMappings = new HashMap<AbstractElement, String>() {
				private static final long serialVersionUID = 1L;
				{
					put(grammarAccess.getStructuralElementAccess().getAlternatives_5_1_2(), "rule__StructuralElement__Alternatives_5_1_2");
					put(grammarAccess.getStructuralElementAccess().getAlternatives_5_2_2(), "rule__StructuralElement__Alternatives_5_2_2");
					put(grammarAccess.getGeneralRelationAccess().getAlternatives_9_2(), "rule__GeneralRelation__Alternatives_9_2");
					put(grammarAccess.getCategoryAccess().getAlternatives_5_1_2(), "rule__Category__Alternatives_5_1_2");
					put(grammarAccess.getAPropertyAccess().getAlternatives(), "rule__AProperty__Alternatives");
					put(grammarAccess.getArrayModifierAccess().getAlternatives(), "rule__ArrayModifier__Alternatives");
					put(grammarAccess.getEnumValueDefinitionAccess().getValueAlternatives_2_0(), "rule__EnumValueDefinition__ValueAlternatives_2_0");
					put(grammarAccess.getEquationDefinitionResultAccess().getAlternatives(), "rule__EquationDefinitionResult__Alternatives");
					put(grammarAccess.getAdditionAndSubtractionAccess().getOperatorAlternatives_1_1_0(), "rule__AdditionAndSubtraction__OperatorAlternatives_1_1_0");
					put(grammarAccess.getMultiplicationAndDivisionAccess().getOperatorAlternatives_1_1_0(), "rule__MultiplicationAndDivision__OperatorAlternatives_1_1_0");
					put(grammarAccess.getAExpressionAccess().getAlternatives(), "rule__AExpression__Alternatives");
					put(grammarAccess.getALiteralAccess().getAlternatives(), "rule__ALiteral__Alternatives");
					put(grammarAccess.getFunctionAccess().getOperatorAlternatives_1_0(), "rule__Function__OperatorAlternatives_1_0");
					put(grammarAccess.getAAdvancedFunctionAccess().getAlternatives(), "rule__AAdvancedFunction__Alternatives");
					put(grammarAccess.getBooleanLiteralStringAccess().getAlternatives(), "rule__BooleanLiteralString__Alternatives");
					put(grammarAccess.getConceptAccess().getGroup(), "rule__Concept__Group__0");
					put(grammarAccess.getConceptAccess().getGroup_3_0(), "rule__Concept__Group_3_0__0");
					put(grammarAccess.getConceptAccess().getGroup_3_1(), "rule__Concept__Group_3_1__0");
					put(grammarAccess.getConceptAccess().getGroup_3_3(), "rule__Concept__Group_3_3__0");
					put(grammarAccess.getStructuralElementAccess().getGroup(), "rule__StructuralElement__Group__0");
					put(grammarAccess.getStructuralElementAccess().getGroup_3_0(), "rule__StructuralElement__Group_3_0__0");
					put(grammarAccess.getStructuralElementAccess().getGroup_3_1(), "rule__StructuralElement__Group_3_1__0");
					put(grammarAccess.getStructuralElementAccess().getGroup_5_0(), "rule__StructuralElement__Group_5_0__0");
					put(grammarAccess.getStructuralElementAccess().getGroup_5_1(), "rule__StructuralElement__Group_5_1__0");
					put(grammarAccess.getStructuralElementAccess().getGroup_5_1_2_0(), "rule__StructuralElement__Group_5_1_2_0__0");
					put(grammarAccess.getStructuralElementAccess().getGroup_5_1_2_0_2(), "rule__StructuralElement__Group_5_1_2_0_2__0");
					put(grammarAccess.getStructuralElementAccess().getGroup_5_2(), "rule__StructuralElement__Group_5_2__0");
					put(grammarAccess.getStructuralElementAccess().getGroup_5_2_2_0(), "rule__StructuralElement__Group_5_2_2_0__0");
					put(grammarAccess.getStructuralElementAccess().getGroup_5_2_2_0_2(), "rule__StructuralElement__Group_5_2_2_0_2__0");
					put(grammarAccess.getStructuralElementAccess().getGroup_5_3(), "rule__StructuralElement__Group_5_3__0");
					put(grammarAccess.getGeneralRelationAccess().getGroup(), "rule__GeneralRelation__Group__0");
					put(grammarAccess.getGeneralRelationAccess().getGroup_3(), "rule__GeneralRelation__Group_3__0");
					put(grammarAccess.getGeneralRelationAccess().getGroup_9(), "rule__GeneralRelation__Group_9__0");
					put(grammarAccess.getGeneralRelationAccess().getGroup_9_2_0(), "rule__GeneralRelation__Group_9_2_0__0");
					put(grammarAccess.getGeneralRelationAccess().getGroup_9_2_0_2(), "rule__GeneralRelation__Group_9_2_0_2__0");
					put(grammarAccess.getGeneralRelationAccess().getGroup_10(), "rule__GeneralRelation__Group_10__0");
					put(grammarAccess.getCategoryAccess().getGroup(), "rule__Category__Group__0");
					put(grammarAccess.getCategoryAccess().getGroup_3_0(), "rule__Category__Group_3_0__0");
					put(grammarAccess.getCategoryAccess().getGroup_3_1(), "rule__Category__Group_3_1__0");
					put(grammarAccess.getCategoryAccess().getGroup_3_2(), "rule__Category__Group_3_2__0");
					put(grammarAccess.getCategoryAccess().getGroup_5_0(), "rule__Category__Group_5_0__0");
					put(grammarAccess.getCategoryAccess().getGroup_5_1(), "rule__Category__Group_5_1__0");
					put(grammarAccess.getCategoryAccess().getGroup_5_1_2_0(), "rule__Category__Group_5_1_2_0__0");
					put(grammarAccess.getCategoryAccess().getGroup_5_1_2_0_2(), "rule__Category__Group_5_1_2_0_2__0");
					put(grammarAccess.getCategoryAccess().getGroup_5_2(), "rule__Category__Group_5_2__0");
					put(grammarAccess.getConceptImportAccess().getGroup(), "rule__ConceptImport__Group__0");
					put(grammarAccess.getDynmaicArrayModifierAccess().getGroup(), "rule__DynmaicArrayModifier__Group__0");
					put(grammarAccess.getStaticArrayModifierAccess().getGroup(), "rule__StaticArrayModifier__Group__0");
					put(grammarAccess.getComposedPropertyAccess().getGroup(), "rule__ComposedProperty__Group__0");
					put(grammarAccess.getComposedPropertyAccess().getGroup_7_0(), "rule__ComposedProperty__Group_7_0__0");
					put(grammarAccess.getComposedPropertyAccess().getGroup_7_1(), "rule__ComposedProperty__Group_7_1__0");
					put(grammarAccess.getComposedPropertyAccess().getGroup_7_2(), "rule__ComposedProperty__Group_7_2__0");
					put(grammarAccess.getIntPropertyAccess().getGroup(), "rule__IntProperty__Group__0");
					put(grammarAccess.getIntPropertyAccess().getGroup_4_0(), "rule__IntProperty__Group_4_0__0");
					put(grammarAccess.getIntPropertyAccess().getGroup_4_1(), "rule__IntProperty__Group_4_1__0");
					put(grammarAccess.getIntPropertyAccess().getGroup_4_2(), "rule__IntProperty__Group_4_2__0");
					put(grammarAccess.getIntPropertyAccess().getGroup_4_3(), "rule__IntProperty__Group_4_3__0");
					put(grammarAccess.getFloatPropertyAccess().getGroup(), "rule__FloatProperty__Group__0");
					put(grammarAccess.getFloatPropertyAccess().getGroup_4_0(), "rule__FloatProperty__Group_4_0__0");
					put(grammarAccess.getFloatPropertyAccess().getGroup_4_1(), "rule__FloatProperty__Group_4_1__0");
					put(grammarAccess.getFloatPropertyAccess().getGroup_4_2(), "rule__FloatProperty__Group_4_2__0");
					put(grammarAccess.getFloatPropertyAccess().getGroup_4_3(), "rule__FloatProperty__Group_4_3__0");
					put(grammarAccess.getStringPropertyAccess().getGroup(), "rule__StringProperty__Group__0");
					put(grammarAccess.getStringPropertyAccess().getGroup_4_0(), "rule__StringProperty__Group_4_0__0");
					put(grammarAccess.getStringPropertyAccess().getGroup_4_1(), "rule__StringProperty__Group_4_1__0");
					put(grammarAccess.getBooleanPropertyAccess().getGroup(), "rule__BooleanProperty__Group__0");
					put(grammarAccess.getBooleanPropertyAccess().getGroup_3_0(), "rule__BooleanProperty__Group_3_0__0");
					put(grammarAccess.getBooleanPropertyAccess().getGroup_3_1(), "rule__BooleanProperty__Group_3_1__0");
					put(grammarAccess.getEnumPropertyAccess().getGroup(), "rule__EnumProperty__Group__0");
					put(grammarAccess.getEnumPropertyAccess().getGroup_3_0(), "rule__EnumProperty__Group_3_0__0");
					put(grammarAccess.getEnumPropertyAccess().getGroup_3_1(), "rule__EnumProperty__Group_3_1__0");
					put(grammarAccess.getEnumPropertyAccess().getGroup_3_2(), "rule__EnumProperty__Group_3_2__0");
					put(grammarAccess.getEnumPropertyAccess().getGroup_3_3(), "rule__EnumProperty__Group_3_3__0");
					put(grammarAccess.getEnumPropertyAccess().getGroup_3_3_3(), "rule__EnumProperty__Group_3_3_3__0");
					put(grammarAccess.getEnumPropertyAccess().getGroup_3_4(), "rule__EnumProperty__Group_3_4__0");
					put(grammarAccess.getEnumValueDefinitionAccess().getGroup(), "rule__EnumValueDefinition__Group__0");
					put(grammarAccess.getReferencePropertyAccess().getGroup(), "rule__ReferenceProperty__Group__0");
					put(grammarAccess.getReferencePropertyAccess().getGroup_6(), "rule__ReferenceProperty__Group_6__0");
					put(grammarAccess.getResourcePropertyAccess().getGroup(), "rule__ResourceProperty__Group__0");
					put(grammarAccess.getResourcePropertyAccess().getGroup_3(), "rule__ResourceProperty__Group_3__0");
					put(grammarAccess.getEquationDefinitionAccess().getGroup(), "rule__EquationDefinition__Group__0");
					put(grammarAccess.getTypeDefinitionResultAccess().getGroup(), "rule__TypeDefinitionResult__Group__0");
					put(grammarAccess.getEquationIntermediateResultAccess().getGroup(), "rule__EquationIntermediateResult__Group__0");
					put(grammarAccess.getAdditionAndSubtractionAccess().getGroup(), "rule__AdditionAndSubtraction__Group__0");
					put(grammarAccess.getAdditionAndSubtractionAccess().getGroup_1(), "rule__AdditionAndSubtraction__Group_1__0");
					put(grammarAccess.getMultiplicationAndDivisionAccess().getGroup(), "rule__MultiplicationAndDivision__Group__0");
					put(grammarAccess.getMultiplicationAndDivisionAccess().getGroup_1(), "rule__MultiplicationAndDivision__Group_1__0");
					put(grammarAccess.getPowerFunctionAccess().getGroup(), "rule__PowerFunction__Group__0");
					put(grammarAccess.getPowerFunctionAccess().getGroup_1(), "rule__PowerFunction__Group_1__0");
					put(grammarAccess.getParenthesisAccess().getGroup(), "rule__Parenthesis__Group__0");
					put(grammarAccess.getFunctionAccess().getGroup(), "rule__Function__Group__0");
					put(grammarAccess.getAdvancedFunctionAccess().getGroup(), "rule__AdvancedFunction__Group__0");
					put(grammarAccess.getAdvancedFunctionAccess().getGroup_4(), "rule__AdvancedFunction__Group_4__0");
					put(grammarAccess.getSetFunctionAccess().getGroup(), "rule__SetFunction__Group__0");
					put(grammarAccess.getSetFunctionAccess().getGroup_4(), "rule__SetFunction__Group_4__0");
					put(grammarAccess.getSetFunctionAccess().getGroup_5(), "rule__SetFunction__Group_5__0");
					put(grammarAccess.getNumberLiteralAccess().getGroup(), "rule__NumberLiteral__Group__0");
					put(grammarAccess.getValuePiAccess().getGroup(), "rule__ValuePi__Group__0");
					put(grammarAccess.getValueEAccess().getGroup(), "rule__ValueE__Group__0");
					put(grammarAccess.getVersionAccess().getGroup(), "rule__Version__Group__0");
					put(grammarAccess.getVersionAccess().getGroup_1(), "rule__Version__Group_1__0");
					put(grammarAccess.getEIntAccess().getGroup(), "rule__EInt__Group__0");
					put(grammarAccess.getIntLiteralStringAccess().getGroup(), "rule__IntLiteralString__Group__0");
					put(grammarAccess.getFloatLiteralStringAccess().getGroup(), "rule__FloatLiteralString__Group__0");
					put(grammarAccess.getFloatLiteralStringAccess().getGroup_2(), "rule__FloatLiteralString__Group_2__0");
					put(grammarAccess.getQualifiedNameWithWildcardAccess().getGroup(), "rule__QualifiedNameWithWildcard__Group__0");
					put(grammarAccess.getQualifiedNameAccess().getGroup(), "rule__QualifiedName__Group__0");
					put(grammarAccess.getQualifiedNameAccess().getGroup_1(), "rule__QualifiedName__Group_1__0");
					put(grammarAccess.getConceptAccess().getNameAssignment_2(), "rule__Concept__NameAssignment_2");
					put(grammarAccess.getConceptAccess().getDisplayNameAssignment_3_0_1(), "rule__Concept__DisplayNameAssignment_3_0_1");
					put(grammarAccess.getConceptAccess().getVersionAssignment_3_1_1(), "rule__Concept__VersionAssignment_3_1_1");
					put(grammarAccess.getConceptAccess().getBetaAssignment_3_2(), "rule__Concept__BetaAssignment_3_2");
					put(grammarAccess.getConceptAccess().getDescriptionAssignment_3_3_1(), "rule__Concept__DescriptionAssignment_3_3_1");
					put(grammarAccess.getConceptAccess().getDMFAssignment_3_4(), "rule__Concept__DMFAssignment_3_4");
					put(grammarAccess.getConceptAccess().getImportsAssignment_5(), "rule__Concept__ImportsAssignment_5");
					put(grammarAccess.getConceptAccess().getStructuralElementsAssignment_6(), "rule__Concept__StructuralElementsAssignment_6");
					put(grammarAccess.getConceptAccess().getRelationsAssignment_7(), "rule__Concept__RelationsAssignment_7");
					put(grammarAccess.getConceptAccess().getCategoriesAssignment_8(), "rule__Concept__CategoriesAssignment_8");
					put(grammarAccess.getStructuralElementAccess().getNameAssignment_2(), "rule__StructuralElement__NameAssignment_2");
					put(grammarAccess.getStructuralElementAccess().getShortNameAssignment_3_0_1(), "rule__StructuralElement__ShortNameAssignment_3_0_1");
					put(grammarAccess.getStructuralElementAccess().getDescriptionAssignment_3_1_1(), "rule__StructuralElement__DescriptionAssignment_3_1_1");
					put(grammarAccess.getStructuralElementAccess().getIsRootStructuralElementAssignment_5_0_0(), "rule__StructuralElement__IsRootStructuralElementAssignment_5_0_0");
					put(grammarAccess.getStructuralElementAccess().getCanInheritFromAssignment_5_1_2_0_1(), "rule__StructuralElement__CanInheritFromAssignment_5_1_2_0_1");
					put(grammarAccess.getStructuralElementAccess().getCanInheritFromAssignment_5_1_2_0_2_1(), "rule__StructuralElement__CanInheritFromAssignment_5_1_2_0_2_1");
					put(grammarAccess.getStructuralElementAccess().getIsCanInheritFromAllAssignment_5_1_2_1(), "rule__StructuralElement__IsCanInheritFromAllAssignment_5_1_2_1");
					put(grammarAccess.getStructuralElementAccess().getApplicableForAssignment_5_2_2_0_1(), "rule__StructuralElement__ApplicableForAssignment_5_2_2_0_1");
					put(grammarAccess.getStructuralElementAccess().getApplicableForAssignment_5_2_2_0_2_1(), "rule__StructuralElement__ApplicableForAssignment_5_2_2_0_2_1");
					put(grammarAccess.getStructuralElementAccess().getIsApplicableForAllAssignment_5_2_2_1(), "rule__StructuralElement__IsApplicableForAllAssignment_5_2_2_1");
					put(grammarAccess.getStructuralElementAccess().getCardinalityAssignment_5_3_1(), "rule__StructuralElement__CardinalityAssignment_5_3_1");
					put(grammarAccess.getGeneralRelationAccess().getNameAssignment_2(), "rule__GeneralRelation__NameAssignment_2");
					put(grammarAccess.getGeneralRelationAccess().getDescriptionAssignment_3_1(), "rule__GeneralRelation__DescriptionAssignment_3_1");
					put(grammarAccess.getGeneralRelationAccess().getReferencedTypeAssignment_7(), "rule__GeneralRelation__ReferencedTypeAssignment_7");
					put(grammarAccess.getGeneralRelationAccess().getApplicableForAssignment_9_2_0_1(), "rule__GeneralRelation__ApplicableForAssignment_9_2_0_1");
					put(grammarAccess.getGeneralRelationAccess().getApplicableForAssignment_9_2_0_2_1(), "rule__GeneralRelation__ApplicableForAssignment_9_2_0_2_1");
					put(grammarAccess.getGeneralRelationAccess().getIsApplicableForAllAssignment_9_2_1(), "rule__GeneralRelation__IsApplicableForAllAssignment_9_2_1");
					put(grammarAccess.getGeneralRelationAccess().getCardinalityAssignment_10_1(), "rule__GeneralRelation__CardinalityAssignment_10_1");
					put(grammarAccess.getCategoryAccess().getNameAssignment_2(), "rule__Category__NameAssignment_2");
					put(grammarAccess.getCategoryAccess().getExtendsCategoryAssignment_3_0_1(), "rule__Category__ExtendsCategoryAssignment_3_0_1");
					put(grammarAccess.getCategoryAccess().getShortNameAssignment_3_1_1(), "rule__Category__ShortNameAssignment_3_1_1");
					put(grammarAccess.getCategoryAccess().getDescriptionAssignment_3_2_1(), "rule__Category__DescriptionAssignment_3_2_1");
					put(grammarAccess.getCategoryAccess().getIsAbstractAssignment_5_0_0(), "rule__Category__IsAbstractAssignment_5_0_0");
					put(grammarAccess.getCategoryAccess().getApplicableForAssignment_5_1_2_0_1(), "rule__Category__ApplicableForAssignment_5_1_2_0_1");
					put(grammarAccess.getCategoryAccess().getApplicableForAssignment_5_1_2_0_2_1(), "rule__Category__ApplicableForAssignment_5_1_2_0_2_1");
					put(grammarAccess.getCategoryAccess().getIsApplicableForAllAssignment_5_1_2_1(), "rule__Category__IsApplicableForAllAssignment_5_1_2_1");
					put(grammarAccess.getCategoryAccess().getCardinalityAssignment_5_2_1(), "rule__Category__CardinalityAssignment_5_2_1");
					put(grammarAccess.getCategoryAccess().getPropertiesAssignment_6(), "rule__Category__PropertiesAssignment_6");
					put(grammarAccess.getCategoryAccess().getEquationDefinitionsAssignment_7(), "rule__Category__EquationDefinitionsAssignment_7");
					put(grammarAccess.getConceptImportAccess().getImportedNamespaceAssignment_2(), "rule__ConceptImport__ImportedNamespaceAssignment_2");
					put(grammarAccess.getStaticArrayModifierAccess().getArraySizeAssignment_1(), "rule__StaticArrayModifier__ArraySizeAssignment_1");
					put(grammarAccess.getComposedPropertyAccess().getNameAssignment_2(), "rule__ComposedProperty__NameAssignment_2");
					put(grammarAccess.getComposedPropertyAccess().getArrayModifierAssignment_3(), "rule__ComposedProperty__ArrayModifierAssignment_3");
					put(grammarAccess.getComposedPropertyAccess().getTypeAssignment_6(), "rule__ComposedProperty__TypeAssignment_6");
					put(grammarAccess.getComposedPropertyAccess().getDescriptionAssignment_7_0_1(), "rule__ComposedProperty__DescriptionAssignment_7_0_1");
					put(grammarAccess.getComposedPropertyAccess().getQuantityKindNameAssignment_7_1_1(), "rule__ComposedProperty__QuantityKindNameAssignment_7_1_1");
					put(grammarAccess.getComposedPropertyAccess().getUnitNameAssignment_7_2_1(), "rule__ComposedProperty__UnitNameAssignment_7_2_1");
					put(grammarAccess.getIntPropertyAccess().getNameAssignment_2(), "rule__IntProperty__NameAssignment_2");
					put(grammarAccess.getIntPropertyAccess().getArrayModifierAssignment_3(), "rule__IntProperty__ArrayModifierAssignment_3");
					put(grammarAccess.getIntPropertyAccess().getDescriptionAssignment_4_0_1(), "rule__IntProperty__DescriptionAssignment_4_0_1");
					put(grammarAccess.getIntPropertyAccess().getDefaultValueAssignment_4_1_1(), "rule__IntProperty__DefaultValueAssignment_4_1_1");
					put(grammarAccess.getIntPropertyAccess().getQuantityKindNameAssignment_4_2_1(), "rule__IntProperty__QuantityKindNameAssignment_4_2_1");
					put(grammarAccess.getIntPropertyAccess().getUnitNameAssignment_4_3_1(), "rule__IntProperty__UnitNameAssignment_4_3_1");
					put(grammarAccess.getFloatPropertyAccess().getNameAssignment_2(), "rule__FloatProperty__NameAssignment_2");
					put(grammarAccess.getFloatPropertyAccess().getArrayModifierAssignment_3(), "rule__FloatProperty__ArrayModifierAssignment_3");
					put(grammarAccess.getFloatPropertyAccess().getDescriptionAssignment_4_0_1(), "rule__FloatProperty__DescriptionAssignment_4_0_1");
					put(grammarAccess.getFloatPropertyAccess().getDefaultValueAssignment_4_1_1(), "rule__FloatProperty__DefaultValueAssignment_4_1_1");
					put(grammarAccess.getFloatPropertyAccess().getQuantityKindNameAssignment_4_2_1(), "rule__FloatProperty__QuantityKindNameAssignment_4_2_1");
					put(grammarAccess.getFloatPropertyAccess().getUnitNameAssignment_4_3_1(), "rule__FloatProperty__UnitNameAssignment_4_3_1");
					put(grammarAccess.getStringPropertyAccess().getNameAssignment_2(), "rule__StringProperty__NameAssignment_2");
					put(grammarAccess.getStringPropertyAccess().getArrayModifierAssignment_3(), "rule__StringProperty__ArrayModifierAssignment_3");
					put(grammarAccess.getStringPropertyAccess().getDescriptionAssignment_4_0_1(), "rule__StringProperty__DescriptionAssignment_4_0_1");
					put(grammarAccess.getStringPropertyAccess().getDefaultValueAssignment_4_1_1(), "rule__StringProperty__DefaultValueAssignment_4_1_1");
					put(grammarAccess.getBooleanPropertyAccess().getNameAssignment_1(), "rule__BooleanProperty__NameAssignment_1");
					put(grammarAccess.getBooleanPropertyAccess().getArrayModifierAssignment_2(), "rule__BooleanProperty__ArrayModifierAssignment_2");
					put(grammarAccess.getBooleanPropertyAccess().getDescriptionAssignment_3_0_1(), "rule__BooleanProperty__DescriptionAssignment_3_0_1");
					put(grammarAccess.getBooleanPropertyAccess().getDefaultValueAssignment_3_1_1(), "rule__BooleanProperty__DefaultValueAssignment_3_1_1");
					put(grammarAccess.getEnumPropertyAccess().getNameAssignment_1(), "rule__EnumProperty__NameAssignment_1");
					put(grammarAccess.getEnumPropertyAccess().getArrayModifierAssignment_2(), "rule__EnumProperty__ArrayModifierAssignment_2");
					put(grammarAccess.getEnumPropertyAccess().getDescriptionAssignment_3_0_1(), "rule__EnumProperty__DescriptionAssignment_3_0_1");
					put(grammarAccess.getEnumPropertyAccess().getQuantityKindNameAssignment_3_1_1(), "rule__EnumProperty__QuantityKindNameAssignment_3_1_1");
					put(grammarAccess.getEnumPropertyAccess().getUnitNameAssignment_3_2_1(), "rule__EnumProperty__UnitNameAssignment_3_2_1");
					put(grammarAccess.getEnumPropertyAccess().getValuesAssignment_3_3_2(), "rule__EnumProperty__ValuesAssignment_3_3_2");
					put(grammarAccess.getEnumPropertyAccess().getValuesAssignment_3_3_3_1(), "rule__EnumProperty__ValuesAssignment_3_3_3_1");
					put(grammarAccess.getEnumPropertyAccess().getDefaultValueAssignment_3_4_1(), "rule__EnumProperty__DefaultValueAssignment_3_4_1");
					put(grammarAccess.getEnumValueDefinitionAccess().getNameAssignment_0(), "rule__EnumValueDefinition__NameAssignment_0");
					put(grammarAccess.getEnumValueDefinitionAccess().getValueAssignment_2(), "rule__EnumValueDefinition__ValueAssignment_2");
					put(grammarAccess.getReferencePropertyAccess().getNameAssignment_1(), "rule__ReferenceProperty__NameAssignment_1");
					put(grammarAccess.getReferencePropertyAccess().getArrayModifierAssignment_2(), "rule__ReferenceProperty__ArrayModifierAssignment_2");
					put(grammarAccess.getReferencePropertyAccess().getReferenceTypeAssignment_5(), "rule__ReferenceProperty__ReferenceTypeAssignment_5");
					put(grammarAccess.getReferencePropertyAccess().getDescriptionAssignment_6_1(), "rule__ReferenceProperty__DescriptionAssignment_6_1");
					put(grammarAccess.getResourcePropertyAccess().getNameAssignment_1(), "rule__ResourceProperty__NameAssignment_1");
					put(grammarAccess.getResourcePropertyAccess().getArrayModifierAssignment_2(), "rule__ResourceProperty__ArrayModifierAssignment_2");
					put(grammarAccess.getResourcePropertyAccess().getDescriptionAssignment_3_1(), "rule__ResourceProperty__DescriptionAssignment_3_1");
					put(grammarAccess.getEquationDefinitionAccess().getResultAssignment_0(), "rule__EquationDefinition__ResultAssignment_0");
					put(grammarAccess.getEquationDefinitionAccess().getExpressionAssignment_2(), "rule__EquationDefinition__ExpressionAssignment_2");
					put(grammarAccess.getTypeDefinitionResultAccess().getReferenceAssignment_2(), "rule__TypeDefinitionResult__ReferenceAssignment_2");
					put(grammarAccess.getEquationIntermediateResultAccess().getNameAssignment_2(), "rule__EquationIntermediateResult__NameAssignment_2");
					put(grammarAccess.getAdditionAndSubtractionAccess().getOperatorAssignment_1_1(), "rule__AdditionAndSubtraction__OperatorAssignment_1_1");
					put(grammarAccess.getAdditionAndSubtractionAccess().getRightAssignment_1_2(), "rule__AdditionAndSubtraction__RightAssignment_1_2");
					put(grammarAccess.getMultiplicationAndDivisionAccess().getOperatorAssignment_1_1(), "rule__MultiplicationAndDivision__OperatorAssignment_1_1");
					put(grammarAccess.getMultiplicationAndDivisionAccess().getRightAssignment_1_2(), "rule__MultiplicationAndDivision__RightAssignment_1_2");
					put(grammarAccess.getPowerFunctionAccess().getOperatorAssignment_1_1(), "rule__PowerFunction__OperatorAssignment_1_1");
					put(grammarAccess.getPowerFunctionAccess().getRightAssignment_1_2(), "rule__PowerFunction__RightAssignment_1_2");
					put(grammarAccess.getParenthesisAccess().getOperatorAssignment_1(), "rule__Parenthesis__OperatorAssignment_1");
					put(grammarAccess.getParenthesisAccess().getRightAssignment_3(), "rule__Parenthesis__RightAssignment_3");
					put(grammarAccess.getReferencedDefinitionInputAccess().getReferenceAssignment(), "rule__ReferencedDefinitionInput__ReferenceAssignment");
					put(grammarAccess.getFunctionAccess().getOperatorAssignment_1(), "rule__Function__OperatorAssignment_1");
					put(grammarAccess.getFunctionAccess().getRightAssignment_3(), "rule__Function__RightAssignment_3");
					put(grammarAccess.getAdvancedFunctionAccess().getOperatorAssignment_1(), "rule__AdvancedFunction__OperatorAssignment_1");
					put(grammarAccess.getAdvancedFunctionAccess().getInputsAssignment_3(), "rule__AdvancedFunction__InputsAssignment_3");
					put(grammarAccess.getAdvancedFunctionAccess().getInputsAssignment_4_1(), "rule__AdvancedFunction__InputsAssignment_4_1");
					put(grammarAccess.getSetFunctionAccess().getOperatorAssignment_1(), "rule__SetFunction__OperatorAssignment_1");
					put(grammarAccess.getSetFunctionAccess().getTypeDefinitionAssignment_3(), "rule__SetFunction__TypeDefinitionAssignment_3");
					put(grammarAccess.getSetFunctionAccess().getDepthAssignment_4_1(), "rule__SetFunction__DepthAssignment_4_1");
					put(grammarAccess.getSetFunctionAccess().getFilterNameAssignment_5_1(), "rule__SetFunction__FilterNameAssignment_5_1");
					put(grammarAccess.getNumberLiteralAccess().getValueAssignment_1(), "rule__NumberLiteral__ValueAssignment_1");
					put(grammarAccess.getConceptAccess().getUnorderedGroup_3(), "rule__Concept__UnorderedGroup_3");
					put(grammarAccess.getStructuralElementAccess().getUnorderedGroup_3(), "rule__StructuralElement__UnorderedGroup_3");
					put(grammarAccess.getStructuralElementAccess().getUnorderedGroup_5(), "rule__StructuralElement__UnorderedGroup_5");
					put(grammarAccess.getCategoryAccess().getUnorderedGroup_3(), "rule__Category__UnorderedGroup_3");
					put(grammarAccess.getCategoryAccess().getUnorderedGroup_5(), "rule__Category__UnorderedGroup_5");
					put(grammarAccess.getComposedPropertyAccess().getUnorderedGroup_7(), "rule__ComposedProperty__UnorderedGroup_7");
					put(grammarAccess.getIntPropertyAccess().getUnorderedGroup_4(), "rule__IntProperty__UnorderedGroup_4");
					put(grammarAccess.getFloatPropertyAccess().getUnorderedGroup_4(), "rule__FloatProperty__UnorderedGroup_4");
					put(grammarAccess.getStringPropertyAccess().getUnorderedGroup_4(), "rule__StringProperty__UnorderedGroup_4");
					put(grammarAccess.getBooleanPropertyAccess().getUnorderedGroup_3(), "rule__BooleanProperty__UnorderedGroup_3");
					put(grammarAccess.getEnumPropertyAccess().getUnorderedGroup_3(), "rule__EnumProperty__UnorderedGroup_3");
				}
			};
		}
		return nameMappings.get(element);
	}
			
	@Override
	protected String[] getInitialHiddenTokens() {
		return new String[] { "RULE_WS", "RULE_ML_COMMENT", "RULE_SL_COMMENT" };
	}

	public ConceptLanguageGrammarAccess getGrammarAccess() {
		return this.grammarAccess;
	}

	public void setGrammarAccess(ConceptLanguageGrammarAccess grammarAccess) {
		this.grammarAccess = grammarAccess;
	}
}
