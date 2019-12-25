/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.concept.services;

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
import org.eclipse.xtext.UnorderedGroup;
import org.eclipse.xtext.common.services.TerminalsGrammarAccess;
import org.eclipse.xtext.service.AbstractElementFinder.AbstractEnumRuleElementFinder;
import org.eclipse.xtext.service.AbstractElementFinder.AbstractGrammarElementFinder;
import org.eclipse.xtext.service.GrammarProvider;

@Singleton
public class ConceptLanguageGrammarAccess extends AbstractGrammarElementFinder {
	
	public class ConceptElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "de.dlr.sc.virsat.model.concept.ConceptLanguage.Concept");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final Action cConceptAction_0 = (Action)cGroup.eContents().get(0);
		private final Keyword cConceptKeyword_1 = (Keyword)cGroup.eContents().get(1);
		private final Assignment cNameAssignment_2 = (Assignment)cGroup.eContents().get(2);
		private final RuleCall cNameQualifiedNameParserRuleCall_2_0 = (RuleCall)cNameAssignment_2.eContents().get(0);
		private final UnorderedGroup cUnorderedGroup_3 = (UnorderedGroup)cGroup.eContents().get(3);
		private final Group cGroup_3_0 = (Group)cUnorderedGroup_3.eContents().get(0);
		private final Keyword cDisplaynameKeyword_3_0_0 = (Keyword)cGroup_3_0.eContents().get(0);
		private final Assignment cDisplayNameAssignment_3_0_1 = (Assignment)cGroup_3_0.eContents().get(1);
		private final RuleCall cDisplayNameEStringParserRuleCall_3_0_1_0 = (RuleCall)cDisplayNameAssignment_3_0_1.eContents().get(0);
		private final Group cGroup_3_1 = (Group)cUnorderedGroup_3.eContents().get(1);
		private final Keyword cVersionKeyword_3_1_0 = (Keyword)cGroup_3_1.eContents().get(0);
		private final Assignment cVersionAssignment_3_1_1 = (Assignment)cGroup_3_1.eContents().get(1);
		private final RuleCall cVersionVersionParserRuleCall_3_1_1_0 = (RuleCall)cVersionAssignment_3_1_1.eContents().get(0);
		private final Assignment cBetaAssignment_3_2 = (Assignment)cUnorderedGroup_3.eContents().get(2);
		private final Keyword cBetaBetaKeyword_3_2_0 = (Keyword)cBetaAssignment_3_2.eContents().get(0);
		private final Group cGroup_3_3 = (Group)cUnorderedGroup_3.eContents().get(3);
		private final Keyword cDescriptionKeyword_3_3_0 = (Keyword)cGroup_3_3.eContents().get(0);
		private final Assignment cDescriptionAssignment_3_3_1 = (Assignment)cGroup_3_3.eContents().get(1);
		private final RuleCall cDescriptionEStringParserRuleCall_3_3_1_0 = (RuleCall)cDescriptionAssignment_3_3_1.eContents().get(0);
		private final Assignment cDMFAssignment_3_4 = (Assignment)cUnorderedGroup_3.eContents().get(4);
		private final Keyword cDMFHasDMFKeyword_3_4_0 = (Keyword)cDMFAssignment_3_4.eContents().get(0);
		private final Keyword cLeftCurlyBracketKeyword_4 = (Keyword)cGroup.eContents().get(4);
		private final UnorderedGroup cUnorderedGroup_5 = (UnorderedGroup)cGroup.eContents().get(5);
		private final Assignment cImportsAssignment_5_0 = (Assignment)cUnorderedGroup_5.eContents().get(0);
		private final RuleCall cImportsConceptImportParserRuleCall_5_0_0 = (RuleCall)cImportsAssignment_5_0.eContents().get(0);
		private final Assignment cEcoreImportsAssignment_5_1 = (Assignment)cUnorderedGroup_5.eContents().get(1);
		private final RuleCall cEcoreImportsEcoreImportParserRuleCall_5_1_0 = (RuleCall)cEcoreImportsAssignment_5_1.eContents().get(0);
		private final Assignment cStructuralElementsAssignment_6 = (Assignment)cGroup.eContents().get(6);
		private final RuleCall cStructuralElementsStructuralElementParserRuleCall_6_0 = (RuleCall)cStructuralElementsAssignment_6.eContents().get(0);
		private final Assignment cRelationsAssignment_7 = (Assignment)cGroup.eContents().get(7);
		private final RuleCall cRelationsARelationParserRuleCall_7_0 = (RuleCall)cRelationsAssignment_7.eContents().get(0);
		private final Assignment cCategoriesAssignment_8 = (Assignment)cGroup.eContents().get(8);
		private final RuleCall cCategoriesCategoryParserRuleCall_8_0 = (RuleCall)cCategoriesAssignment_8.eContents().get(0);
		private final Keyword cRightCurlyBracketKeyword_9 = (Keyword)cGroup.eContents().get(9);
		
		//Concept Concepts::Concept:
		//	{Concepts::Concept}
		//	'Concept' name=QualifiedName (('displayname' displayName=EString)? & ('version' version=Version)? & beta?='beta'? &
		//	('description' description=EString)? & DMF?='hasDMF'?)
		//	'{' (imports+=ConceptImport* & ecoreImports+=EcoreImport*) structuralElements+=StructuralElement*
		//	relations+=ARelation*
		//	categories+=Category*
		//	'}';
		@Override public ParserRule getRule() { return rule; }
		
		//{Concepts::Concept} 'Concept' name=QualifiedName (('displayname' displayName=EString)? & ('version' version=Version)? &
		//beta?='beta'? & ('description' description=EString)? & DMF?='hasDMF'?) '{' (imports+=ConceptImport* &
		//ecoreImports+=EcoreImport*) structuralElements+=StructuralElement* relations+=ARelation* categories+=Category* '}'
		public Group getGroup() { return cGroup; }
		
		//{Concepts::Concept}
		public Action getConceptAction_0() { return cConceptAction_0; }
		
		//'Concept'
		public Keyword getConceptKeyword_1() { return cConceptKeyword_1; }
		
		//name=QualifiedName
		public Assignment getNameAssignment_2() { return cNameAssignment_2; }
		
		//QualifiedName
		public RuleCall getNameQualifiedNameParserRuleCall_2_0() { return cNameQualifiedNameParserRuleCall_2_0; }
		
		//('displayname' displayName=EString)? & ('version' version=Version)? & beta?='beta'? & ('description'
		//description=EString)? & DMF?='hasDMF'?
		public UnorderedGroup getUnorderedGroup_3() { return cUnorderedGroup_3; }
		
		//('displayname' displayName=EString)?
		public Group getGroup_3_0() { return cGroup_3_0; }
		
		//'displayname'
		public Keyword getDisplaynameKeyword_3_0_0() { return cDisplaynameKeyword_3_0_0; }
		
		//displayName=EString
		public Assignment getDisplayNameAssignment_3_0_1() { return cDisplayNameAssignment_3_0_1; }
		
		//EString
		public RuleCall getDisplayNameEStringParserRuleCall_3_0_1_0() { return cDisplayNameEStringParserRuleCall_3_0_1_0; }
		
		//('version' version=Version)?
		public Group getGroup_3_1() { return cGroup_3_1; }
		
		//'version'
		public Keyword getVersionKeyword_3_1_0() { return cVersionKeyword_3_1_0; }
		
		//version=Version
		public Assignment getVersionAssignment_3_1_1() { return cVersionAssignment_3_1_1; }
		
		//Version
		public RuleCall getVersionVersionParserRuleCall_3_1_1_0() { return cVersionVersionParserRuleCall_3_1_1_0; }
		
		//beta?='beta'?
		public Assignment getBetaAssignment_3_2() { return cBetaAssignment_3_2; }
		
		//'beta'
		public Keyword getBetaBetaKeyword_3_2_0() { return cBetaBetaKeyword_3_2_0; }
		
		//('description' description=EString)?
		public Group getGroup_3_3() { return cGroup_3_3; }
		
		//'description'
		public Keyword getDescriptionKeyword_3_3_0() { return cDescriptionKeyword_3_3_0; }
		
		//description=EString
		public Assignment getDescriptionAssignment_3_3_1() { return cDescriptionAssignment_3_3_1; }
		
		//EString
		public RuleCall getDescriptionEStringParserRuleCall_3_3_1_0() { return cDescriptionEStringParserRuleCall_3_3_1_0; }
		
		//DMF?='hasDMF'?
		public Assignment getDMFAssignment_3_4() { return cDMFAssignment_3_4; }
		
		//'hasDMF'
		public Keyword getDMFHasDMFKeyword_3_4_0() { return cDMFHasDMFKeyword_3_4_0; }
		
		//'{'
		public Keyword getLeftCurlyBracketKeyword_4() { return cLeftCurlyBracketKeyword_4; }
		
		//imports+=ConceptImport* & ecoreImports+=EcoreImport*
		public UnorderedGroup getUnorderedGroup_5() { return cUnorderedGroup_5; }
		
		//imports+=ConceptImport*
		public Assignment getImportsAssignment_5_0() { return cImportsAssignment_5_0; }
		
		//ConceptImport
		public RuleCall getImportsConceptImportParserRuleCall_5_0_0() { return cImportsConceptImportParserRuleCall_5_0_0; }
		
		//ecoreImports+=EcoreImport*
		public Assignment getEcoreImportsAssignment_5_1() { return cEcoreImportsAssignment_5_1; }
		
		//EcoreImport
		public RuleCall getEcoreImportsEcoreImportParserRuleCall_5_1_0() { return cEcoreImportsEcoreImportParserRuleCall_5_1_0; }
		
		//structuralElements+=StructuralElement*
		public Assignment getStructuralElementsAssignment_6() { return cStructuralElementsAssignment_6; }
		
		//StructuralElement
		public RuleCall getStructuralElementsStructuralElementParserRuleCall_6_0() { return cStructuralElementsStructuralElementParserRuleCall_6_0; }
		
		//relations+=ARelation*
		public Assignment getRelationsAssignment_7() { return cRelationsAssignment_7; }
		
		//ARelation
		public RuleCall getRelationsARelationParserRuleCall_7_0() { return cRelationsARelationParserRuleCall_7_0; }
		
		//categories+=Category*
		public Assignment getCategoriesAssignment_8() { return cCategoriesAssignment_8; }
		
		//Category
		public RuleCall getCategoriesCategoryParserRuleCall_8_0() { return cCategoriesCategoryParserRuleCall_8_0; }
		
		//'}'
		public Keyword getRightCurlyBracketKeyword_9() { return cRightCurlyBracketKeyword_9; }
	}
	public class StructuralElementElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "de.dlr.sc.virsat.model.concept.ConceptLanguage.StructuralElement");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final Action cStructuralElementAction_0 = (Action)cGroup.eContents().get(0);
		private final Keyword cStructuralElementKeyword_1 = (Keyword)cGroup.eContents().get(1);
		private final Assignment cNameAssignment_2 = (Assignment)cGroup.eContents().get(2);
		private final RuleCall cNameIDTerminalRuleCall_2_0 = (RuleCall)cNameAssignment_2.eContents().get(0);
		private final UnorderedGroup cUnorderedGroup_3 = (UnorderedGroup)cGroup.eContents().get(3);
		private final Group cGroup_3_0 = (Group)cUnorderedGroup_3.eContents().get(0);
		private final Keyword cShortnameKeyword_3_0_0 = (Keyword)cGroup_3_0.eContents().get(0);
		private final Assignment cShortNameAssignment_3_0_1 = (Assignment)cGroup_3_0.eContents().get(1);
		private final RuleCall cShortNameIDTerminalRuleCall_3_0_1_0 = (RuleCall)cShortNameAssignment_3_0_1.eContents().get(0);
		private final Group cGroup_3_1 = (Group)cUnorderedGroup_3.eContents().get(1);
		private final Keyword cDescriptionKeyword_3_1_0 = (Keyword)cGroup_3_1.eContents().get(0);
		private final Assignment cDescriptionAssignment_3_1_1 = (Assignment)cGroup_3_1.eContents().get(1);
		private final RuleCall cDescriptionEStringParserRuleCall_3_1_1_0 = (RuleCall)cDescriptionAssignment_3_1_1.eContents().get(0);
		private final Keyword cLeftCurlyBracketKeyword_4 = (Keyword)cGroup.eContents().get(4);
		private final UnorderedGroup cUnorderedGroup_5 = (UnorderedGroup)cGroup.eContents().get(5);
		private final Group cGroup_5_0 = (Group)cUnorderedGroup_5.eContents().get(0);
		private final Assignment cIsRootStructuralElementAssignment_5_0_0 = (Assignment)cGroup_5_0.eContents().get(0);
		private final Keyword cIsRootStructuralElementIsRootStructuralElementKeyword_5_0_0_0 = (Keyword)cIsRootStructuralElementAssignment_5_0_0.eContents().get(0);
		private final Keyword cSemicolonKeyword_5_0_1 = (Keyword)cGroup_5_0.eContents().get(1);
		private final Group cGroup_5_1 = (Group)cUnorderedGroup_5.eContents().get(1);
		private final Keyword cInheritsKeyword_5_1_0 = (Keyword)cGroup_5_1.eContents().get(0);
		private final Keyword cFromKeyword_5_1_1 = (Keyword)cGroup_5_1.eContents().get(1);
		private final Alternatives cAlternatives_5_1_2 = (Alternatives)cGroup_5_1.eContents().get(2);
		private final Group cGroup_5_1_2_0 = (Group)cAlternatives_5_1_2.eContents().get(0);
		private final Keyword cLeftSquareBracketKeyword_5_1_2_0_0 = (Keyword)cGroup_5_1_2_0.eContents().get(0);
		private final Assignment cCanInheritFromAssignment_5_1_2_0_1 = (Assignment)cGroup_5_1_2_0.eContents().get(1);
		private final CrossReference cCanInheritFromStructuralElementCrossReference_5_1_2_0_1_0 = (CrossReference)cCanInheritFromAssignment_5_1_2_0_1.eContents().get(0);
		private final RuleCall cCanInheritFromStructuralElementQualifiedNameParserRuleCall_5_1_2_0_1_0_1 = (RuleCall)cCanInheritFromStructuralElementCrossReference_5_1_2_0_1_0.eContents().get(1);
		private final Group cGroup_5_1_2_0_2 = (Group)cGroup_5_1_2_0.eContents().get(2);
		private final Keyword cCommaKeyword_5_1_2_0_2_0 = (Keyword)cGroup_5_1_2_0_2.eContents().get(0);
		private final Assignment cCanInheritFromAssignment_5_1_2_0_2_1 = (Assignment)cGroup_5_1_2_0_2.eContents().get(1);
		private final CrossReference cCanInheritFromStructuralElementCrossReference_5_1_2_0_2_1_0 = (CrossReference)cCanInheritFromAssignment_5_1_2_0_2_1.eContents().get(0);
		private final RuleCall cCanInheritFromStructuralElementQualifiedNameParserRuleCall_5_1_2_0_2_1_0_1 = (RuleCall)cCanInheritFromStructuralElementCrossReference_5_1_2_0_2_1_0.eContents().get(1);
		private final Keyword cRightSquareBracketKeyword_5_1_2_0_3 = (Keyword)cGroup_5_1_2_0.eContents().get(3);
		private final Assignment cIsCanInheritFromAllAssignment_5_1_2_1 = (Assignment)cAlternatives_5_1_2.eContents().get(1);
		private final Keyword cIsCanInheritFromAllAllKeyword_5_1_2_1_0 = (Keyword)cIsCanInheritFromAllAssignment_5_1_2_1.eContents().get(0);
		private final Keyword cSemicolonKeyword_5_1_3 = (Keyword)cGroup_5_1.eContents().get(3);
		private final Group cGroup_5_2 = (Group)cUnorderedGroup_5.eContents().get(2);
		private final Keyword cApplicableKeyword_5_2_0 = (Keyword)cGroup_5_2.eContents().get(0);
		private final Keyword cForKeyword_5_2_1 = (Keyword)cGroup_5_2.eContents().get(1);
		private final Alternatives cAlternatives_5_2_2 = (Alternatives)cGroup_5_2.eContents().get(2);
		private final Group cGroup_5_2_2_0 = (Group)cAlternatives_5_2_2.eContents().get(0);
		private final Keyword cLeftSquareBracketKeyword_5_2_2_0_0 = (Keyword)cGroup_5_2_2_0.eContents().get(0);
		private final Assignment cApplicableForAssignment_5_2_2_0_1 = (Assignment)cGroup_5_2_2_0.eContents().get(1);
		private final CrossReference cApplicableForStructuralElementCrossReference_5_2_2_0_1_0 = (CrossReference)cApplicableForAssignment_5_2_2_0_1.eContents().get(0);
		private final RuleCall cApplicableForStructuralElementQualifiedNameParserRuleCall_5_2_2_0_1_0_1 = (RuleCall)cApplicableForStructuralElementCrossReference_5_2_2_0_1_0.eContents().get(1);
		private final Group cGroup_5_2_2_0_2 = (Group)cGroup_5_2_2_0.eContents().get(2);
		private final Keyword cCommaKeyword_5_2_2_0_2_0 = (Keyword)cGroup_5_2_2_0_2.eContents().get(0);
		private final Assignment cApplicableForAssignment_5_2_2_0_2_1 = (Assignment)cGroup_5_2_2_0_2.eContents().get(1);
		private final CrossReference cApplicableForStructuralElementCrossReference_5_2_2_0_2_1_0 = (CrossReference)cApplicableForAssignment_5_2_2_0_2_1.eContents().get(0);
		private final RuleCall cApplicableForStructuralElementQualifiedNameParserRuleCall_5_2_2_0_2_1_0_1 = (RuleCall)cApplicableForStructuralElementCrossReference_5_2_2_0_2_1_0.eContents().get(1);
		private final Keyword cRightSquareBracketKeyword_5_2_2_0_3 = (Keyword)cGroup_5_2_2_0.eContents().get(3);
		private final Assignment cIsApplicableForAllAssignment_5_2_2_1 = (Assignment)cAlternatives_5_2_2.eContents().get(1);
		private final Keyword cIsApplicableForAllAllKeyword_5_2_2_1_0 = (Keyword)cIsApplicableForAllAssignment_5_2_2_1.eContents().get(0);
		private final Keyword cSemicolonKeyword_5_2_3 = (Keyword)cGroup_5_2.eContents().get(3);
		private final Group cGroup_5_3 = (Group)cUnorderedGroup_5.eContents().get(3);
		private final Keyword cCardinalityKeyword_5_3_0 = (Keyword)cGroup_5_3.eContents().get(0);
		private final Assignment cCardinalityAssignment_5_3_1 = (Assignment)cGroup_5_3.eContents().get(1);
		private final RuleCall cCardinalityINTTerminalRuleCall_5_3_1_0 = (RuleCall)cCardinalityAssignment_5_3_1.eContents().get(0);
		private final Keyword cSemicolonKeyword_5_3_2 = (Keyword)cGroup_5_3.eContents().get(2);
		private final Keyword cRightCurlyBracketKeyword_6 = (Keyword)cGroup.eContents().get(6);
		
		//// ***************************************************************************************
		//// Structural Elements and Relations
		//// ***************************************************************************************
		//StructuralElement Structural::StructuralElement:
		//	{Structural::StructuralElement}
		//	'StructuralElement' name=ID (('shortname' shortName=ID)? & ('description' description=EString)?)
		//	'{' ((isRootStructuralElement?='IsRootStructuralElement' ';')? & ('Inherits' 'From' ('['
		//	canInheritFrom+=[Structural::StructuralElement|QualifiedName] (","
		//	canInheritFrom+=[Structural::StructuralElement|QualifiedName])* ']' | isCanInheritFromAll?='All') ';')? &
		//	('Applicable' 'For' ('[' applicableFor+=[Structural::StructuralElement|QualifiedName] (","
		//	applicableFor+=[Structural::StructuralElement|QualifiedName])* ']' | isApplicableForAll?='All') ';')? &
		//	('Cardinality' cardinality=INT ';')?)
		//	'}';
		@Override public ParserRule getRule() { return rule; }
		
		//{Structural::StructuralElement} 'StructuralElement' name=ID (('shortname' shortName=ID)? & ('description'
		//description=EString)?) '{' ((isRootStructuralElement?='IsRootStructuralElement' ';')? & ('Inherits' 'From' ('['
		//canInheritFrom+=[Structural::StructuralElement|QualifiedName] (","
		//canInheritFrom+=[Structural::StructuralElement|QualifiedName])* ']' | isCanInheritFromAll?='All') ';')? &
		//('Applicable' 'For' ('[' applicableFor+=[Structural::StructuralElement|QualifiedName] (","
		//applicableFor+=[Structural::StructuralElement|QualifiedName])* ']' | isApplicableForAll?='All') ';')? & ('Cardinality'
		//cardinality=INT ';')?) '}'
		public Group getGroup() { return cGroup; }
		
		//{Structural::StructuralElement}
		public Action getStructuralElementAction_0() { return cStructuralElementAction_0; }
		
		//'StructuralElement'
		public Keyword getStructuralElementKeyword_1() { return cStructuralElementKeyword_1; }
		
		//name=ID
		public Assignment getNameAssignment_2() { return cNameAssignment_2; }
		
		//ID
		public RuleCall getNameIDTerminalRuleCall_2_0() { return cNameIDTerminalRuleCall_2_0; }
		
		//('shortname' shortName=ID)? & ('description' description=EString)?
		public UnorderedGroup getUnorderedGroup_3() { return cUnorderedGroup_3; }
		
		//('shortname' shortName=ID)?
		public Group getGroup_3_0() { return cGroup_3_0; }
		
		//'shortname'
		public Keyword getShortnameKeyword_3_0_0() { return cShortnameKeyword_3_0_0; }
		
		//shortName=ID
		public Assignment getShortNameAssignment_3_0_1() { return cShortNameAssignment_3_0_1; }
		
		//ID
		public RuleCall getShortNameIDTerminalRuleCall_3_0_1_0() { return cShortNameIDTerminalRuleCall_3_0_1_0; }
		
		//('description' description=EString)?
		public Group getGroup_3_1() { return cGroup_3_1; }
		
		//'description'
		public Keyword getDescriptionKeyword_3_1_0() { return cDescriptionKeyword_3_1_0; }
		
		//description=EString
		public Assignment getDescriptionAssignment_3_1_1() { return cDescriptionAssignment_3_1_1; }
		
		//EString
		public RuleCall getDescriptionEStringParserRuleCall_3_1_1_0() { return cDescriptionEStringParserRuleCall_3_1_1_0; }
		
		//'{'
		public Keyword getLeftCurlyBracketKeyword_4() { return cLeftCurlyBracketKeyword_4; }
		
		//(isRootStructuralElement?='IsRootStructuralElement' ';')? & ('Inherits' 'From' ('['
		//canInheritFrom+=[Structural::StructuralElement|QualifiedName] (","
		//canInheritFrom+=[Structural::StructuralElement|QualifiedName])* ']' | isCanInheritFromAll?='All') ';')? &
		//('Applicable' 'For' ('[' applicableFor+=[Structural::StructuralElement|QualifiedName] (","
		//applicableFor+=[Structural::StructuralElement|QualifiedName])* ']' | isApplicableForAll?='All') ';')? & ('Cardinality'
		//cardinality=INT ';')?
		public UnorderedGroup getUnorderedGroup_5() { return cUnorderedGroup_5; }
		
		//(isRootStructuralElement?='IsRootStructuralElement' ';')?
		public Group getGroup_5_0() { return cGroup_5_0; }
		
		//isRootStructuralElement?='IsRootStructuralElement'
		public Assignment getIsRootStructuralElementAssignment_5_0_0() { return cIsRootStructuralElementAssignment_5_0_0; }
		
		//'IsRootStructuralElement'
		public Keyword getIsRootStructuralElementIsRootStructuralElementKeyword_5_0_0_0() { return cIsRootStructuralElementIsRootStructuralElementKeyword_5_0_0_0; }
		
		//';'
		public Keyword getSemicolonKeyword_5_0_1() { return cSemicolonKeyword_5_0_1; }
		
		//('Inherits' 'From' ('[' canInheritFrom+=[Structural::StructuralElement|QualifiedName] (","
		//canInheritFrom+=[Structural::StructuralElement|QualifiedName])* ']' | isCanInheritFromAll?='All') ';')?
		public Group getGroup_5_1() { return cGroup_5_1; }
		
		//'Inherits'
		public Keyword getInheritsKeyword_5_1_0() { return cInheritsKeyword_5_1_0; }
		
		//'From'
		public Keyword getFromKeyword_5_1_1() { return cFromKeyword_5_1_1; }
		
		//'[' canInheritFrom+=[Structural::StructuralElement|QualifiedName] (","
		//canInheritFrom+=[Structural::StructuralElement|QualifiedName])* ']' | isCanInheritFromAll?='All'
		public Alternatives getAlternatives_5_1_2() { return cAlternatives_5_1_2; }
		
		//'[' canInheritFrom+=[Structural::StructuralElement|QualifiedName] (","
		//canInheritFrom+=[Structural::StructuralElement|QualifiedName])* ']'
		public Group getGroup_5_1_2_0() { return cGroup_5_1_2_0; }
		
		//'['
		public Keyword getLeftSquareBracketKeyword_5_1_2_0_0() { return cLeftSquareBracketKeyword_5_1_2_0_0; }
		
		//canInheritFrom+=[Structural::StructuralElement|QualifiedName]
		public Assignment getCanInheritFromAssignment_5_1_2_0_1() { return cCanInheritFromAssignment_5_1_2_0_1; }
		
		//[Structural::StructuralElement|QualifiedName]
		public CrossReference getCanInheritFromStructuralElementCrossReference_5_1_2_0_1_0() { return cCanInheritFromStructuralElementCrossReference_5_1_2_0_1_0; }
		
		//QualifiedName
		public RuleCall getCanInheritFromStructuralElementQualifiedNameParserRuleCall_5_1_2_0_1_0_1() { return cCanInheritFromStructuralElementQualifiedNameParserRuleCall_5_1_2_0_1_0_1; }
		
		//("," canInheritFrom+=[Structural::StructuralElement|QualifiedName])*
		public Group getGroup_5_1_2_0_2() { return cGroup_5_1_2_0_2; }
		
		//","
		public Keyword getCommaKeyword_5_1_2_0_2_0() { return cCommaKeyword_5_1_2_0_2_0; }
		
		//canInheritFrom+=[Structural::StructuralElement|QualifiedName]
		public Assignment getCanInheritFromAssignment_5_1_2_0_2_1() { return cCanInheritFromAssignment_5_1_2_0_2_1; }
		
		//[Structural::StructuralElement|QualifiedName]
		public CrossReference getCanInheritFromStructuralElementCrossReference_5_1_2_0_2_1_0() { return cCanInheritFromStructuralElementCrossReference_5_1_2_0_2_1_0; }
		
		//QualifiedName
		public RuleCall getCanInheritFromStructuralElementQualifiedNameParserRuleCall_5_1_2_0_2_1_0_1() { return cCanInheritFromStructuralElementQualifiedNameParserRuleCall_5_1_2_0_2_1_0_1; }
		
		//']'
		public Keyword getRightSquareBracketKeyword_5_1_2_0_3() { return cRightSquareBracketKeyword_5_1_2_0_3; }
		
		//isCanInheritFromAll?='All'
		public Assignment getIsCanInheritFromAllAssignment_5_1_2_1() { return cIsCanInheritFromAllAssignment_5_1_2_1; }
		
		//'All'
		public Keyword getIsCanInheritFromAllAllKeyword_5_1_2_1_0() { return cIsCanInheritFromAllAllKeyword_5_1_2_1_0; }
		
		//';'
		public Keyword getSemicolonKeyword_5_1_3() { return cSemicolonKeyword_5_1_3; }
		
		//('Applicable' 'For' ('[' applicableFor+=[Structural::StructuralElement|QualifiedName] (","
		//applicableFor+=[Structural::StructuralElement|QualifiedName])* ']' | isApplicableForAll?='All') ';')?
		public Group getGroup_5_2() { return cGroup_5_2; }
		
		//'Applicable'
		public Keyword getApplicableKeyword_5_2_0() { return cApplicableKeyword_5_2_0; }
		
		//'For'
		public Keyword getForKeyword_5_2_1() { return cForKeyword_5_2_1; }
		
		//'[' applicableFor+=[Structural::StructuralElement|QualifiedName] (","
		//applicableFor+=[Structural::StructuralElement|QualifiedName])* ']' | isApplicableForAll?='All'
		public Alternatives getAlternatives_5_2_2() { return cAlternatives_5_2_2; }
		
		//'[' applicableFor+=[Structural::StructuralElement|QualifiedName] (","
		//applicableFor+=[Structural::StructuralElement|QualifiedName])* ']'
		public Group getGroup_5_2_2_0() { return cGroup_5_2_2_0; }
		
		//'['
		public Keyword getLeftSquareBracketKeyword_5_2_2_0_0() { return cLeftSquareBracketKeyword_5_2_2_0_0; }
		
		//applicableFor+=[Structural::StructuralElement|QualifiedName]
		public Assignment getApplicableForAssignment_5_2_2_0_1() { return cApplicableForAssignment_5_2_2_0_1; }
		
		//[Structural::StructuralElement|QualifiedName]
		public CrossReference getApplicableForStructuralElementCrossReference_5_2_2_0_1_0() { return cApplicableForStructuralElementCrossReference_5_2_2_0_1_0; }
		
		//QualifiedName
		public RuleCall getApplicableForStructuralElementQualifiedNameParserRuleCall_5_2_2_0_1_0_1() { return cApplicableForStructuralElementQualifiedNameParserRuleCall_5_2_2_0_1_0_1; }
		
		//("," applicableFor+=[Structural::StructuralElement|QualifiedName])*
		public Group getGroup_5_2_2_0_2() { return cGroup_5_2_2_0_2; }
		
		//","
		public Keyword getCommaKeyword_5_2_2_0_2_0() { return cCommaKeyword_5_2_2_0_2_0; }
		
		//applicableFor+=[Structural::StructuralElement|QualifiedName]
		public Assignment getApplicableForAssignment_5_2_2_0_2_1() { return cApplicableForAssignment_5_2_2_0_2_1; }
		
		//[Structural::StructuralElement|QualifiedName]
		public CrossReference getApplicableForStructuralElementCrossReference_5_2_2_0_2_1_0() { return cApplicableForStructuralElementCrossReference_5_2_2_0_2_1_0; }
		
		//QualifiedName
		public RuleCall getApplicableForStructuralElementQualifiedNameParserRuleCall_5_2_2_0_2_1_0_1() { return cApplicableForStructuralElementQualifiedNameParserRuleCall_5_2_2_0_2_1_0_1; }
		
		//']'
		public Keyword getRightSquareBracketKeyword_5_2_2_0_3() { return cRightSquareBracketKeyword_5_2_2_0_3; }
		
		//isApplicableForAll?='All'
		public Assignment getIsApplicableForAllAssignment_5_2_2_1() { return cIsApplicableForAllAssignment_5_2_2_1; }
		
		//'All'
		public Keyword getIsApplicableForAllAllKeyword_5_2_2_1_0() { return cIsApplicableForAllAllKeyword_5_2_2_1_0; }
		
		//';'
		public Keyword getSemicolonKeyword_5_2_3() { return cSemicolonKeyword_5_2_3; }
		
		//('Cardinality' cardinality=INT ';')?
		public Group getGroup_5_3() { return cGroup_5_3; }
		
		//'Cardinality'
		public Keyword getCardinalityKeyword_5_3_0() { return cCardinalityKeyword_5_3_0; }
		
		//cardinality=INT
		public Assignment getCardinalityAssignment_5_3_1() { return cCardinalityAssignment_5_3_1; }
		
		//INT
		public RuleCall getCardinalityINTTerminalRuleCall_5_3_1_0() { return cCardinalityINTTerminalRuleCall_5_3_1_0; }
		
		//';'
		public Keyword getSemicolonKeyword_5_3_2() { return cSemicolonKeyword_5_3_2; }
		
		//'}'
		public Keyword getRightCurlyBracketKeyword_6() { return cRightCurlyBracketKeyword_6; }
	}
	public class GeneralRelationElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "de.dlr.sc.virsat.model.concept.ConceptLanguage.GeneralRelation");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final Action cGeneralRelationAction_0 = (Action)cGroup.eContents().get(0);
		private final Keyword cGeneralRelationKeyword_1 = (Keyword)cGroup.eContents().get(1);
		private final Assignment cNameAssignment_2 = (Assignment)cGroup.eContents().get(2);
		private final RuleCall cNameIDTerminalRuleCall_2_0 = (RuleCall)cNameAssignment_2.eContents().get(0);
		private final Group cGroup_3 = (Group)cGroup.eContents().get(3);
		private final Keyword cDescriptionKeyword_3_0 = (Keyword)cGroup_3.eContents().get(0);
		private final Assignment cDescriptionAssignment_3_1 = (Assignment)cGroup_3.eContents().get(1);
		private final RuleCall cDescriptionEStringParserRuleCall_3_1_0 = (RuleCall)cDescriptionAssignment_3_1.eContents().get(0);
		private final Keyword cLeftCurlyBracketKeyword_4 = (Keyword)cGroup.eContents().get(4);
		private final Keyword cReferencedKeyword_5 = (Keyword)cGroup.eContents().get(5);
		private final Keyword cTypeKeyword_6 = (Keyword)cGroup.eContents().get(6);
		private final Assignment cReferencedTypeAssignment_7 = (Assignment)cGroup.eContents().get(7);
		private final CrossReference cReferencedTypeStructuralElementCrossReference_7_0 = (CrossReference)cReferencedTypeAssignment_7.eContents().get(0);
		private final RuleCall cReferencedTypeStructuralElementQualifiedNameParserRuleCall_7_0_1 = (RuleCall)cReferencedTypeStructuralElementCrossReference_7_0.eContents().get(1);
		private final Keyword cSemicolonKeyword_8 = (Keyword)cGroup.eContents().get(8);
		private final Group cGroup_9 = (Group)cGroup.eContents().get(9);
		private final Keyword cApplicableKeyword_9_0 = (Keyword)cGroup_9.eContents().get(0);
		private final Keyword cForKeyword_9_1 = (Keyword)cGroup_9.eContents().get(1);
		private final Alternatives cAlternatives_9_2 = (Alternatives)cGroup_9.eContents().get(2);
		private final Group cGroup_9_2_0 = (Group)cAlternatives_9_2.eContents().get(0);
		private final Keyword cLeftSquareBracketKeyword_9_2_0_0 = (Keyword)cGroup_9_2_0.eContents().get(0);
		private final Assignment cApplicableForAssignment_9_2_0_1 = (Assignment)cGroup_9_2_0.eContents().get(1);
		private final CrossReference cApplicableForStructuralElementCrossReference_9_2_0_1_0 = (CrossReference)cApplicableForAssignment_9_2_0_1.eContents().get(0);
		private final RuleCall cApplicableForStructuralElementQualifiedNameParserRuleCall_9_2_0_1_0_1 = (RuleCall)cApplicableForStructuralElementCrossReference_9_2_0_1_0.eContents().get(1);
		private final Group cGroup_9_2_0_2 = (Group)cGroup_9_2_0.eContents().get(2);
		private final Keyword cCommaKeyword_9_2_0_2_0 = (Keyword)cGroup_9_2_0_2.eContents().get(0);
		private final Assignment cApplicableForAssignment_9_2_0_2_1 = (Assignment)cGroup_9_2_0_2.eContents().get(1);
		private final CrossReference cApplicableForStructuralElementCrossReference_9_2_0_2_1_0 = (CrossReference)cApplicableForAssignment_9_2_0_2_1.eContents().get(0);
		private final RuleCall cApplicableForStructuralElementQualifiedNameParserRuleCall_9_2_0_2_1_0_1 = (RuleCall)cApplicableForStructuralElementCrossReference_9_2_0_2_1_0.eContents().get(1);
		private final Keyword cRightSquareBracketKeyword_9_2_0_3 = (Keyword)cGroup_9_2_0.eContents().get(3);
		private final Assignment cIsApplicableForAllAssignment_9_2_1 = (Assignment)cAlternatives_9_2.eContents().get(1);
		private final Keyword cIsApplicableForAllAllKeyword_9_2_1_0 = (Keyword)cIsApplicableForAllAssignment_9_2_1.eContents().get(0);
		private final Keyword cSemicolonKeyword_9_3 = (Keyword)cGroup_9.eContents().get(3);
		private final Group cGroup_10 = (Group)cGroup.eContents().get(10);
		private final Keyword cCardinalityKeyword_10_0 = (Keyword)cGroup_10.eContents().get(0);
		private final Assignment cCardinalityAssignment_10_1 = (Assignment)cGroup_10.eContents().get(1);
		private final RuleCall cCardinalityINTTerminalRuleCall_10_1_0 = (RuleCall)cCardinalityAssignment_10_1.eContents().get(0);
		private final Keyword cSemicolonKeyword_10_2 = (Keyword)cGroup_10.eContents().get(2);
		private final Keyword cRightCurlyBracketKeyword_11 = (Keyword)cGroup.eContents().get(11);
		
		//GeneralRelation Structural::GeneralRelation:
		//	{Structural::GeneralRelation}
		//	'GeneralRelation' name=ID ('description' description=EString)?
		//	'{'
		//	'Referenced' 'Type' referencedType=[Structural::StructuralElement|QualifiedName] ';' ('Applicable' 'For' ('['
		//	applicableFor+=[Structural::StructuralElement|QualifiedName] (","
		//	applicableFor+=[Structural::StructuralElement|QualifiedName])* ']' | isApplicableForAll?='All') ';')? ('Cardinality'
		//	cardinality=INT ';')?
		//	'}';
		@Override public ParserRule getRule() { return rule; }
		
		//{Structural::GeneralRelation} 'GeneralRelation' name=ID ('description' description=EString)? '{' 'Referenced' 'Type'
		//referencedType=[Structural::StructuralElement|QualifiedName] ';' ('Applicable' 'For' ('['
		//applicableFor+=[Structural::StructuralElement|QualifiedName] (","
		//applicableFor+=[Structural::StructuralElement|QualifiedName])* ']' | isApplicableForAll?='All') ';')? ('Cardinality'
		//cardinality=INT ';')? '}'
		public Group getGroup() { return cGroup; }
		
		//{Structural::GeneralRelation}
		public Action getGeneralRelationAction_0() { return cGeneralRelationAction_0; }
		
		//'GeneralRelation'
		public Keyword getGeneralRelationKeyword_1() { return cGeneralRelationKeyword_1; }
		
		//name=ID
		public Assignment getNameAssignment_2() { return cNameAssignment_2; }
		
		//ID
		public RuleCall getNameIDTerminalRuleCall_2_0() { return cNameIDTerminalRuleCall_2_0; }
		
		//('description' description=EString)?
		public Group getGroup_3() { return cGroup_3; }
		
		//'description'
		public Keyword getDescriptionKeyword_3_0() { return cDescriptionKeyword_3_0; }
		
		//description=EString
		public Assignment getDescriptionAssignment_3_1() { return cDescriptionAssignment_3_1; }
		
		//EString
		public RuleCall getDescriptionEStringParserRuleCall_3_1_0() { return cDescriptionEStringParserRuleCall_3_1_0; }
		
		//'{'
		public Keyword getLeftCurlyBracketKeyword_4() { return cLeftCurlyBracketKeyword_4; }
		
		//'Referenced'
		public Keyword getReferencedKeyword_5() { return cReferencedKeyword_5; }
		
		//'Type'
		public Keyword getTypeKeyword_6() { return cTypeKeyword_6; }
		
		//referencedType=[Structural::StructuralElement|QualifiedName]
		public Assignment getReferencedTypeAssignment_7() { return cReferencedTypeAssignment_7; }
		
		//[Structural::StructuralElement|QualifiedName]
		public CrossReference getReferencedTypeStructuralElementCrossReference_7_0() { return cReferencedTypeStructuralElementCrossReference_7_0; }
		
		//QualifiedName
		public RuleCall getReferencedTypeStructuralElementQualifiedNameParserRuleCall_7_0_1() { return cReferencedTypeStructuralElementQualifiedNameParserRuleCall_7_0_1; }
		
		//';'
		public Keyword getSemicolonKeyword_8() { return cSemicolonKeyword_8; }
		
		//('Applicable' 'For' ('[' applicableFor+=[Structural::StructuralElement|QualifiedName] (","
		//applicableFor+=[Structural::StructuralElement|QualifiedName])* ']' | isApplicableForAll?='All') ';')?
		public Group getGroup_9() { return cGroup_9; }
		
		//'Applicable'
		public Keyword getApplicableKeyword_9_0() { return cApplicableKeyword_9_0; }
		
		//'For'
		public Keyword getForKeyword_9_1() { return cForKeyword_9_1; }
		
		//'[' applicableFor+=[Structural::StructuralElement|QualifiedName] (","
		//applicableFor+=[Structural::StructuralElement|QualifiedName])* ']' | isApplicableForAll?='All'
		public Alternatives getAlternatives_9_2() { return cAlternatives_9_2; }
		
		//'[' applicableFor+=[Structural::StructuralElement|QualifiedName] (","
		//applicableFor+=[Structural::StructuralElement|QualifiedName])* ']'
		public Group getGroup_9_2_0() { return cGroup_9_2_0; }
		
		//'['
		public Keyword getLeftSquareBracketKeyword_9_2_0_0() { return cLeftSquareBracketKeyword_9_2_0_0; }
		
		//applicableFor+=[Structural::StructuralElement|QualifiedName]
		public Assignment getApplicableForAssignment_9_2_0_1() { return cApplicableForAssignment_9_2_0_1; }
		
		//[Structural::StructuralElement|QualifiedName]
		public CrossReference getApplicableForStructuralElementCrossReference_9_2_0_1_0() { return cApplicableForStructuralElementCrossReference_9_2_0_1_0; }
		
		//QualifiedName
		public RuleCall getApplicableForStructuralElementQualifiedNameParserRuleCall_9_2_0_1_0_1() { return cApplicableForStructuralElementQualifiedNameParserRuleCall_9_2_0_1_0_1; }
		
		//("," applicableFor+=[Structural::StructuralElement|QualifiedName])*
		public Group getGroup_9_2_0_2() { return cGroup_9_2_0_2; }
		
		//","
		public Keyword getCommaKeyword_9_2_0_2_0() { return cCommaKeyword_9_2_0_2_0; }
		
		//applicableFor+=[Structural::StructuralElement|QualifiedName]
		public Assignment getApplicableForAssignment_9_2_0_2_1() { return cApplicableForAssignment_9_2_0_2_1; }
		
		//[Structural::StructuralElement|QualifiedName]
		public CrossReference getApplicableForStructuralElementCrossReference_9_2_0_2_1_0() { return cApplicableForStructuralElementCrossReference_9_2_0_2_1_0; }
		
		//QualifiedName
		public RuleCall getApplicableForStructuralElementQualifiedNameParserRuleCall_9_2_0_2_1_0_1() { return cApplicableForStructuralElementQualifiedNameParserRuleCall_9_2_0_2_1_0_1; }
		
		//']'
		public Keyword getRightSquareBracketKeyword_9_2_0_3() { return cRightSquareBracketKeyword_9_2_0_3; }
		
		//isApplicableForAll?='All'
		public Assignment getIsApplicableForAllAssignment_9_2_1() { return cIsApplicableForAllAssignment_9_2_1; }
		
		//'All'
		public Keyword getIsApplicableForAllAllKeyword_9_2_1_0() { return cIsApplicableForAllAllKeyword_9_2_1_0; }
		
		//';'
		public Keyword getSemicolonKeyword_9_3() { return cSemicolonKeyword_9_3; }
		
		//('Cardinality' cardinality=INT ';')?
		public Group getGroup_10() { return cGroup_10; }
		
		//'Cardinality'
		public Keyword getCardinalityKeyword_10_0() { return cCardinalityKeyword_10_0; }
		
		//cardinality=INT
		public Assignment getCardinalityAssignment_10_1() { return cCardinalityAssignment_10_1; }
		
		//INT
		public RuleCall getCardinalityINTTerminalRuleCall_10_1_0() { return cCardinalityINTTerminalRuleCall_10_1_0; }
		
		//';'
		public Keyword getSemicolonKeyword_10_2() { return cSemicolonKeyword_10_2; }
		
		//'}'
		public Keyword getRightCurlyBracketKeyword_11() { return cRightCurlyBracketKeyword_11; }
	}
	public class ARelationElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "de.dlr.sc.virsat.model.concept.ConceptLanguage.ARelation");
		private final RuleCall cGeneralRelationParserRuleCall = (RuleCall)rule.eContents().get(1);
		
		//ARelation Structural::GeneralRelation:
		//	GeneralRelation;
		@Override public ParserRule getRule() { return rule; }
		
		//GeneralRelation
		public RuleCall getGeneralRelationParserRuleCall() { return cGeneralRelationParserRuleCall; }
	}
	public class CategoryElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "de.dlr.sc.virsat.model.concept.ConceptLanguage.Category");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final Action cCategoryAction_0 = (Action)cGroup.eContents().get(0);
		private final Keyword cCategoryKeyword_1 = (Keyword)cGroup.eContents().get(1);
		private final Assignment cNameAssignment_2 = (Assignment)cGroup.eContents().get(2);
		private final RuleCall cNameIDTerminalRuleCall_2_0 = (RuleCall)cNameAssignment_2.eContents().get(0);
		private final UnorderedGroup cUnorderedGroup_3 = (UnorderedGroup)cGroup.eContents().get(3);
		private final Group cGroup_3_0 = (Group)cUnorderedGroup_3.eContents().get(0);
		private final Keyword cExtendsKeyword_3_0_0 = (Keyword)cGroup_3_0.eContents().get(0);
		private final Assignment cExtendsCategoryAssignment_3_0_1 = (Assignment)cGroup_3_0.eContents().get(1);
		private final CrossReference cExtendsCategoryCategoryCrossReference_3_0_1_0 = (CrossReference)cExtendsCategoryAssignment_3_0_1.eContents().get(0);
		private final RuleCall cExtendsCategoryCategoryQualifiedNameParserRuleCall_3_0_1_0_1 = (RuleCall)cExtendsCategoryCategoryCrossReference_3_0_1_0.eContents().get(1);
		private final Group cGroup_3_1 = (Group)cUnorderedGroup_3.eContents().get(1);
		private final Keyword cShortnameKeyword_3_1_0 = (Keyword)cGroup_3_1.eContents().get(0);
		private final Assignment cShortNameAssignment_3_1_1 = (Assignment)cGroup_3_1.eContents().get(1);
		private final RuleCall cShortNameIDTerminalRuleCall_3_1_1_0 = (RuleCall)cShortNameAssignment_3_1_1.eContents().get(0);
		private final Group cGroup_3_2 = (Group)cUnorderedGroup_3.eContents().get(2);
		private final Keyword cDescriptionKeyword_3_2_0 = (Keyword)cGroup_3_2.eContents().get(0);
		private final Assignment cDescriptionAssignment_3_2_1 = (Assignment)cGroup_3_2.eContents().get(1);
		private final RuleCall cDescriptionEStringParserRuleCall_3_2_1_0 = (RuleCall)cDescriptionAssignment_3_2_1.eContents().get(0);
		private final Keyword cLeftCurlyBracketKeyword_4 = (Keyword)cGroup.eContents().get(4);
		private final UnorderedGroup cUnorderedGroup_5 = (UnorderedGroup)cGroup.eContents().get(5);
		private final Group cGroup_5_0 = (Group)cUnorderedGroup_5.eContents().get(0);
		private final Assignment cIsAbstractAssignment_5_0_0 = (Assignment)cGroup_5_0.eContents().get(0);
		private final Keyword cIsAbstractIsAbstractKeyword_5_0_0_0 = (Keyword)cIsAbstractAssignment_5_0_0.eContents().get(0);
		private final Keyword cSemicolonKeyword_5_0_1 = (Keyword)cGroup_5_0.eContents().get(1);
		private final Group cGroup_5_1 = (Group)cUnorderedGroup_5.eContents().get(1);
		private final Keyword cApplicableKeyword_5_1_0 = (Keyword)cGroup_5_1.eContents().get(0);
		private final Keyword cForKeyword_5_1_1 = (Keyword)cGroup_5_1.eContents().get(1);
		private final Alternatives cAlternatives_5_1_2 = (Alternatives)cGroup_5_1.eContents().get(2);
		private final Group cGroup_5_1_2_0 = (Group)cAlternatives_5_1_2.eContents().get(0);
		private final Keyword cLeftSquareBracketKeyword_5_1_2_0_0 = (Keyword)cGroup_5_1_2_0.eContents().get(0);
		private final Assignment cApplicableForAssignment_5_1_2_0_1 = (Assignment)cGroup_5_1_2_0.eContents().get(1);
		private final CrossReference cApplicableForStructuralElementCrossReference_5_1_2_0_1_0 = (CrossReference)cApplicableForAssignment_5_1_2_0_1.eContents().get(0);
		private final RuleCall cApplicableForStructuralElementQualifiedNameParserRuleCall_5_1_2_0_1_0_1 = (RuleCall)cApplicableForStructuralElementCrossReference_5_1_2_0_1_0.eContents().get(1);
		private final Group cGroup_5_1_2_0_2 = (Group)cGroup_5_1_2_0.eContents().get(2);
		private final Keyword cCommaKeyword_5_1_2_0_2_0 = (Keyword)cGroup_5_1_2_0_2.eContents().get(0);
		private final Assignment cApplicableForAssignment_5_1_2_0_2_1 = (Assignment)cGroup_5_1_2_0_2.eContents().get(1);
		private final CrossReference cApplicableForStructuralElementCrossReference_5_1_2_0_2_1_0 = (CrossReference)cApplicableForAssignment_5_1_2_0_2_1.eContents().get(0);
		private final RuleCall cApplicableForStructuralElementQualifiedNameParserRuleCall_5_1_2_0_2_1_0_1 = (RuleCall)cApplicableForStructuralElementCrossReference_5_1_2_0_2_1_0.eContents().get(1);
		private final Keyword cRightSquareBracketKeyword_5_1_2_0_3 = (Keyword)cGroup_5_1_2_0.eContents().get(3);
		private final Assignment cIsApplicableForAllAssignment_5_1_2_1 = (Assignment)cAlternatives_5_1_2.eContents().get(1);
		private final Keyword cIsApplicableForAllAllKeyword_5_1_2_1_0 = (Keyword)cIsApplicableForAllAssignment_5_1_2_1.eContents().get(0);
		private final Keyword cSemicolonKeyword_5_1_3 = (Keyword)cGroup_5_1.eContents().get(3);
		private final Group cGroup_5_2 = (Group)cUnorderedGroup_5.eContents().get(2);
		private final Keyword cCardinalityKeyword_5_2_0 = (Keyword)cGroup_5_2.eContents().get(0);
		private final Assignment cCardinalityAssignment_5_2_1 = (Assignment)cGroup_5_2.eContents().get(1);
		private final RuleCall cCardinalityINTTerminalRuleCall_5_2_1_0 = (RuleCall)cCardinalityAssignment_5_2_1.eContents().get(0);
		private final Keyword cSemicolonKeyword_5_2_2 = (Keyword)cGroup_5_2.eContents().get(2);
		private final Assignment cPropertiesAssignment_6 = (Assignment)cGroup.eContents().get(6);
		private final RuleCall cPropertiesAPropertyParserRuleCall_6_0 = (RuleCall)cPropertiesAssignment_6.eContents().get(0);
		private final Assignment cEquationDefinitionsAssignment_7 = (Assignment)cGroup.eContents().get(7);
		private final RuleCall cEquationDefinitionsEquationDefinitionParserRuleCall_7_0 = (RuleCall)cEquationDefinitionsAssignment_7.eContents().get(0);
		private final Keyword cRightCurlyBracketKeyword_8 = (Keyword)cGroup.eContents().get(8);
		
		//// ***************************************************************************************
		//// The Properties
		//// ***************************************************************************************
		//Category Categories::Category:
		//	{Categories::Category}
		//	'Category' name=ID (('extends' extendsCategory=[Categories::Category|QualifiedName])? & ('shortname' shortName=ID)? &
		//	('description' description=EString)?)
		//	'{' ((isAbstract?='IsAbstract' ';')? & ('Applicable' 'For' ('['
		//	applicableFor+=[Structural::StructuralElement|QualifiedName] (","
		//	applicableFor+=[Structural::StructuralElement|QualifiedName])* ']' | isApplicableForAll?='All') ';')? &
		//	('Cardinality' cardinality=INT ';')?) properties+=AProperty*
		//	equationDefinitions+=EquationDefinition*
		//	'}';
		@Override public ParserRule getRule() { return rule; }
		
		//{Categories::Category} 'Category' name=ID (('extends' extendsCategory=[Categories::Category|QualifiedName])? &
		//('shortname' shortName=ID)? & ('description' description=EString)?) '{' ((isAbstract?='IsAbstract' ';')? &
		//('Applicable' 'For' ('[' applicableFor+=[Structural::StructuralElement|QualifiedName] (","
		//applicableFor+=[Structural::StructuralElement|QualifiedName])* ']' | isApplicableForAll?='All') ';')? & ('Cardinality'
		//cardinality=INT ';')?) properties+=AProperty* equationDefinitions+=EquationDefinition* '}'
		public Group getGroup() { return cGroup; }
		
		//{Categories::Category}
		public Action getCategoryAction_0() { return cCategoryAction_0; }
		
		//'Category'
		public Keyword getCategoryKeyword_1() { return cCategoryKeyword_1; }
		
		//name=ID
		public Assignment getNameAssignment_2() { return cNameAssignment_2; }
		
		//ID
		public RuleCall getNameIDTerminalRuleCall_2_0() { return cNameIDTerminalRuleCall_2_0; }
		
		//('extends' extendsCategory=[Categories::Category|QualifiedName])? & ('shortname' shortName=ID)? & ('description'
		//description=EString)?
		public UnorderedGroup getUnorderedGroup_3() { return cUnorderedGroup_3; }
		
		//('extends' extendsCategory=[Categories::Category|QualifiedName])?
		public Group getGroup_3_0() { return cGroup_3_0; }
		
		//'extends'
		public Keyword getExtendsKeyword_3_0_0() { return cExtendsKeyword_3_0_0; }
		
		//extendsCategory=[Categories::Category|QualifiedName]
		public Assignment getExtendsCategoryAssignment_3_0_1() { return cExtendsCategoryAssignment_3_0_1; }
		
		//[Categories::Category|QualifiedName]
		public CrossReference getExtendsCategoryCategoryCrossReference_3_0_1_0() { return cExtendsCategoryCategoryCrossReference_3_0_1_0; }
		
		//QualifiedName
		public RuleCall getExtendsCategoryCategoryQualifiedNameParserRuleCall_3_0_1_0_1() { return cExtendsCategoryCategoryQualifiedNameParserRuleCall_3_0_1_0_1; }
		
		//('shortname' shortName=ID)?
		public Group getGroup_3_1() { return cGroup_3_1; }
		
		//'shortname'
		public Keyword getShortnameKeyword_3_1_0() { return cShortnameKeyword_3_1_0; }
		
		//shortName=ID
		public Assignment getShortNameAssignment_3_1_1() { return cShortNameAssignment_3_1_1; }
		
		//ID
		public RuleCall getShortNameIDTerminalRuleCall_3_1_1_0() { return cShortNameIDTerminalRuleCall_3_1_1_0; }
		
		//('description' description=EString)?
		public Group getGroup_3_2() { return cGroup_3_2; }
		
		//'description'
		public Keyword getDescriptionKeyword_3_2_0() { return cDescriptionKeyword_3_2_0; }
		
		//description=EString
		public Assignment getDescriptionAssignment_3_2_1() { return cDescriptionAssignment_3_2_1; }
		
		//EString
		public RuleCall getDescriptionEStringParserRuleCall_3_2_1_0() { return cDescriptionEStringParserRuleCall_3_2_1_0; }
		
		//'{'
		public Keyword getLeftCurlyBracketKeyword_4() { return cLeftCurlyBracketKeyword_4; }
		
		//(isAbstract?='IsAbstract' ';')? & ('Applicable' 'For' ('[' applicableFor+=[Structural::StructuralElement|QualifiedName]
		//("," applicableFor+=[Structural::StructuralElement|QualifiedName])* ']' | isApplicableForAll?='All') ';')? &
		//('Cardinality' cardinality=INT ';')?
		public UnorderedGroup getUnorderedGroup_5() { return cUnorderedGroup_5; }
		
		//(isAbstract?='IsAbstract' ';')?
		public Group getGroup_5_0() { return cGroup_5_0; }
		
		//isAbstract?='IsAbstract'
		public Assignment getIsAbstractAssignment_5_0_0() { return cIsAbstractAssignment_5_0_0; }
		
		//'IsAbstract'
		public Keyword getIsAbstractIsAbstractKeyword_5_0_0_0() { return cIsAbstractIsAbstractKeyword_5_0_0_0; }
		
		//';'
		public Keyword getSemicolonKeyword_5_0_1() { return cSemicolonKeyword_5_0_1; }
		
		//('Applicable' 'For' ('[' applicableFor+=[Structural::StructuralElement|QualifiedName] (","
		//applicableFor+=[Structural::StructuralElement|QualifiedName])* ']' | isApplicableForAll?='All') ';')?
		public Group getGroup_5_1() { return cGroup_5_1; }
		
		//'Applicable'
		public Keyword getApplicableKeyword_5_1_0() { return cApplicableKeyword_5_1_0; }
		
		//'For'
		public Keyword getForKeyword_5_1_1() { return cForKeyword_5_1_1; }
		
		//'[' applicableFor+=[Structural::StructuralElement|QualifiedName] (","
		//applicableFor+=[Structural::StructuralElement|QualifiedName])* ']' | isApplicableForAll?='All'
		public Alternatives getAlternatives_5_1_2() { return cAlternatives_5_1_2; }
		
		//'[' applicableFor+=[Structural::StructuralElement|QualifiedName] (","
		//applicableFor+=[Structural::StructuralElement|QualifiedName])* ']'
		public Group getGroup_5_1_2_0() { return cGroup_5_1_2_0; }
		
		//'['
		public Keyword getLeftSquareBracketKeyword_5_1_2_0_0() { return cLeftSquareBracketKeyword_5_1_2_0_0; }
		
		//applicableFor+=[Structural::StructuralElement|QualifiedName]
		public Assignment getApplicableForAssignment_5_1_2_0_1() { return cApplicableForAssignment_5_1_2_0_1; }
		
		//[Structural::StructuralElement|QualifiedName]
		public CrossReference getApplicableForStructuralElementCrossReference_5_1_2_0_1_0() { return cApplicableForStructuralElementCrossReference_5_1_2_0_1_0; }
		
		//QualifiedName
		public RuleCall getApplicableForStructuralElementQualifiedNameParserRuleCall_5_1_2_0_1_0_1() { return cApplicableForStructuralElementQualifiedNameParserRuleCall_5_1_2_0_1_0_1; }
		
		//("," applicableFor+=[Structural::StructuralElement|QualifiedName])*
		public Group getGroup_5_1_2_0_2() { return cGroup_5_1_2_0_2; }
		
		//","
		public Keyword getCommaKeyword_5_1_2_0_2_0() { return cCommaKeyword_5_1_2_0_2_0; }
		
		//applicableFor+=[Structural::StructuralElement|QualifiedName]
		public Assignment getApplicableForAssignment_5_1_2_0_2_1() { return cApplicableForAssignment_5_1_2_0_2_1; }
		
		//[Structural::StructuralElement|QualifiedName]
		public CrossReference getApplicableForStructuralElementCrossReference_5_1_2_0_2_1_0() { return cApplicableForStructuralElementCrossReference_5_1_2_0_2_1_0; }
		
		//QualifiedName
		public RuleCall getApplicableForStructuralElementQualifiedNameParserRuleCall_5_1_2_0_2_1_0_1() { return cApplicableForStructuralElementQualifiedNameParserRuleCall_5_1_2_0_2_1_0_1; }
		
		//']'
		public Keyword getRightSquareBracketKeyword_5_1_2_0_3() { return cRightSquareBracketKeyword_5_1_2_0_3; }
		
		//isApplicableForAll?='All'
		public Assignment getIsApplicableForAllAssignment_5_1_2_1() { return cIsApplicableForAllAssignment_5_1_2_1; }
		
		//'All'
		public Keyword getIsApplicableForAllAllKeyword_5_1_2_1_0() { return cIsApplicableForAllAllKeyword_5_1_2_1_0; }
		
		//';'
		public Keyword getSemicolonKeyword_5_1_3() { return cSemicolonKeyword_5_1_3; }
		
		//('Cardinality' cardinality=INT ';')?
		public Group getGroup_5_2() { return cGroup_5_2; }
		
		//'Cardinality'
		public Keyword getCardinalityKeyword_5_2_0() { return cCardinalityKeyword_5_2_0; }
		
		//cardinality=INT
		public Assignment getCardinalityAssignment_5_2_1() { return cCardinalityAssignment_5_2_1; }
		
		//INT
		public RuleCall getCardinalityINTTerminalRuleCall_5_2_1_0() { return cCardinalityINTTerminalRuleCall_5_2_1_0; }
		
		//';'
		public Keyword getSemicolonKeyword_5_2_2() { return cSemicolonKeyword_5_2_2; }
		
		//properties+=AProperty*
		public Assignment getPropertiesAssignment_6() { return cPropertiesAssignment_6; }
		
		//AProperty
		public RuleCall getPropertiesAPropertyParserRuleCall_6_0() { return cPropertiesAPropertyParserRuleCall_6_0; }
		
		//equationDefinitions+=EquationDefinition*
		public Assignment getEquationDefinitionsAssignment_7() { return cEquationDefinitionsAssignment_7; }
		
		//EquationDefinition
		public RuleCall getEquationDefinitionsEquationDefinitionParserRuleCall_7_0() { return cEquationDefinitionsEquationDefinitionParserRuleCall_7_0; }
		
		//'}'
		public Keyword getRightCurlyBracketKeyword_8() { return cRightCurlyBracketKeyword_8; }
	}
	public class ConceptImportElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "de.dlr.sc.virsat.model.concept.ConceptLanguage.ConceptImport");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final Action cConceptImportAction_0 = (Action)cGroup.eContents().get(0);
		private final Keyword cImportKeyword_1 = (Keyword)cGroup.eContents().get(1);
		private final Assignment cImportedNamespaceAssignment_2 = (Assignment)cGroup.eContents().get(2);
		private final RuleCall cImportedNamespaceQualifiedNameWithWildcardParserRuleCall_2_0 = (RuleCall)cImportedNamespaceAssignment_2.eContents().get(0);
		private final Keyword cSemicolonKeyword_3 = (Keyword)cGroup.eContents().get(3);
		
		//ConceptImport Concepts::ConceptImport:
		//	{Concepts::ConceptImport}
		//	'Import' importedNamespace=QualifiedNameWithWildcard
		//	';';
		@Override public ParserRule getRule() { return rule; }
		
		//{Concepts::ConceptImport} 'Import' importedNamespace=QualifiedNameWithWildcard ';'
		public Group getGroup() { return cGroup; }
		
		//{Concepts::ConceptImport}
		public Action getConceptImportAction_0() { return cConceptImportAction_0; }
		
		//'Import'
		public Keyword getImportKeyword_1() { return cImportKeyword_1; }
		
		//importedNamespace=QualifiedNameWithWildcard
		public Assignment getImportedNamespaceAssignment_2() { return cImportedNamespaceAssignment_2; }
		
		//QualifiedNameWithWildcard
		public RuleCall getImportedNamespaceQualifiedNameWithWildcardParserRuleCall_2_0() { return cImportedNamespaceQualifiedNameWithWildcardParserRuleCall_2_0; }
		
		//';'
		public Keyword getSemicolonKeyword_3() { return cSemicolonKeyword_3; }
	}
	public class EcoreImportElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "de.dlr.sc.virsat.model.concept.ConceptLanguage.EcoreImport");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final Action cEcoreImportAction_0 = (Action)cGroup.eContents().get(0);
		private final Keyword cEImportKeyword_1 = (Keyword)cGroup.eContents().get(1);
		private final Assignment cImportedNsURIAssignment_2 = (Assignment)cGroup.eContents().get(2);
		private final RuleCall cImportedNsURISTRINGTerminalRuleCall_2_0 = (RuleCall)cImportedNsURIAssignment_2.eContents().get(0);
		private final Group cGroup_3 = (Group)cGroup.eContents().get(3);
		private final Keyword cGenModelKeyword_3_0 = (Keyword)cGroup_3.eContents().get(0);
		private final Assignment cImportedGenModelAssignment_3_1 = (Assignment)cGroup_3.eContents().get(1);
		private final RuleCall cImportedGenModelSTRINGTerminalRuleCall_3_1_0 = (RuleCall)cImportedGenModelAssignment_3_1.eContents().get(0);
		private final Keyword cSemicolonKeyword_4 = (Keyword)cGroup.eContents().get(4);
		
		//EcoreImport Concepts::EcoreImport:
		//	{Concepts::EcoreImport}
		//	'EImport' importedNsURI=STRING ('genModel' importedGenModel=STRING)?
		//	';';
		@Override public ParserRule getRule() { return rule; }
		
		//{Concepts::EcoreImport} 'EImport' importedNsURI=STRING ('genModel' importedGenModel=STRING)? ';'
		public Group getGroup() { return cGroup; }
		
		//{Concepts::EcoreImport}
		public Action getEcoreImportAction_0() { return cEcoreImportAction_0; }
		
		//'EImport'
		public Keyword getEImportKeyword_1() { return cEImportKeyword_1; }
		
		//importedNsURI=STRING
		public Assignment getImportedNsURIAssignment_2() { return cImportedNsURIAssignment_2; }
		
		//STRING
		public RuleCall getImportedNsURISTRINGTerminalRuleCall_2_0() { return cImportedNsURISTRINGTerminalRuleCall_2_0; }
		
		//('genModel' importedGenModel=STRING)?
		public Group getGroup_3() { return cGroup_3; }
		
		//'genModel'
		public Keyword getGenModelKeyword_3_0() { return cGenModelKeyword_3_0; }
		
		//importedGenModel=STRING
		public Assignment getImportedGenModelAssignment_3_1() { return cImportedGenModelAssignment_3_1; }
		
		//STRING
		public RuleCall getImportedGenModelSTRINGTerminalRuleCall_3_1_0() { return cImportedGenModelSTRINGTerminalRuleCall_3_1_0; }
		
		//';'
		public Keyword getSemicolonKeyword_4() { return cSemicolonKeyword_4; }
	}
	public class APropertyElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "de.dlr.sc.virsat.model.concept.ConceptLanguage.AProperty");
		private final Alternatives cAlternatives = (Alternatives)rule.eContents().get(1);
		private final RuleCall cComposedPropertyParserRuleCall_0 = (RuleCall)cAlternatives.eContents().get(0);
		private final RuleCall cIntPropertyParserRuleCall_1 = (RuleCall)cAlternatives.eContents().get(1);
		private final RuleCall cFloatPropertyParserRuleCall_2 = (RuleCall)cAlternatives.eContents().get(2);
		private final RuleCall cStringPropertyParserRuleCall_3 = (RuleCall)cAlternatives.eContents().get(3);
		private final RuleCall cBooleanPropertyParserRuleCall_4 = (RuleCall)cAlternatives.eContents().get(4);
		private final RuleCall cEnumPropertyParserRuleCall_5 = (RuleCall)cAlternatives.eContents().get(5);
		private final RuleCall cReferencePropertyParserRuleCall_6 = (RuleCall)cAlternatives.eContents().get(6);
		private final RuleCall cEReferencePropertyParserRuleCall_7 = (RuleCall)cAlternatives.eContents().get(7);
		private final RuleCall cResourcePropertyParserRuleCall_8 = (RuleCall)cAlternatives.eContents().get(8);
		
		//// ***************************************************************************************
		//// The Properties
		//// ***************************************************************************************
		//AProperty PropertyDefinitions::AProperty:
		//	ComposedProperty | IntProperty | FloatProperty | StringProperty | BooleanProperty | EnumProperty | ReferenceProperty
		//	| EReferenceProperty | ResourceProperty;
		@Override public ParserRule getRule() { return rule; }
		
		//ComposedProperty | IntProperty | FloatProperty | StringProperty | BooleanProperty | EnumProperty | ReferenceProperty |
		//EReferenceProperty | ResourceProperty
		public Alternatives getAlternatives() { return cAlternatives; }
		
		//ComposedProperty
		public RuleCall getComposedPropertyParserRuleCall_0() { return cComposedPropertyParserRuleCall_0; }
		
		//IntProperty
		public RuleCall getIntPropertyParserRuleCall_1() { return cIntPropertyParserRuleCall_1; }
		
		//FloatProperty
		public RuleCall getFloatPropertyParserRuleCall_2() { return cFloatPropertyParserRuleCall_2; }
		
		//StringProperty
		public RuleCall getStringPropertyParserRuleCall_3() { return cStringPropertyParserRuleCall_3; }
		
		//BooleanProperty
		public RuleCall getBooleanPropertyParserRuleCall_4() { return cBooleanPropertyParserRuleCall_4; }
		
		//EnumProperty
		public RuleCall getEnumPropertyParserRuleCall_5() { return cEnumPropertyParserRuleCall_5; }
		
		//ReferenceProperty
		public RuleCall getReferencePropertyParserRuleCall_6() { return cReferencePropertyParserRuleCall_6; }
		
		//EReferenceProperty
		public RuleCall getEReferencePropertyParserRuleCall_7() { return cEReferencePropertyParserRuleCall_7; }
		
		//ResourceProperty
		public RuleCall getResourcePropertyParserRuleCall_8() { return cResourcePropertyParserRuleCall_8; }
	}
	public class ArrayModifierElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "de.dlr.sc.virsat.model.concept.ConceptLanguage.ArrayModifier");
		private final Alternatives cAlternatives = (Alternatives)rule.eContents().get(1);
		private final RuleCall cDynmaicArrayModifierParserRuleCall_0 = (RuleCall)cAlternatives.eContents().get(0);
		private final RuleCall cStaticArrayModifierParserRuleCall_1 = (RuleCall)cAlternatives.eContents().get(1);
		
		//ArrayModifier PropertyDefinitions::IArrayModifier:
		//	DynmaicArrayModifier | StaticArrayModifier;
		@Override public ParserRule getRule() { return rule; }
		
		//DynmaicArrayModifier | StaticArrayModifier
		public Alternatives getAlternatives() { return cAlternatives; }
		
		//DynmaicArrayModifier
		public RuleCall getDynmaicArrayModifierParserRuleCall_0() { return cDynmaicArrayModifierParserRuleCall_0; }
		
		//StaticArrayModifier
		public RuleCall getStaticArrayModifierParserRuleCall_1() { return cStaticArrayModifierParserRuleCall_1; }
	}
	public class DynmaicArrayModifierElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "de.dlr.sc.virsat.model.concept.ConceptLanguage.DynmaicArrayModifier");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final Action cDynamicArrayModifierAction_0 = (Action)cGroup.eContents().get(0);
		private final Keyword cLeftSquareBracketKeyword_1 = (Keyword)cGroup.eContents().get(1);
		private final Keyword cRightSquareBracketKeyword_2 = (Keyword)cGroup.eContents().get(2);
		
		//DynmaicArrayModifier PropertyDefinitions::DynamicArrayModifier:
		//	{PropertyDefinitions::DynamicArrayModifier}
		//	'[' ']';
		@Override public ParserRule getRule() { return rule; }
		
		//{PropertyDefinitions::DynamicArrayModifier} '[' ']'
		public Group getGroup() { return cGroup; }
		
		//{PropertyDefinitions::DynamicArrayModifier}
		public Action getDynamicArrayModifierAction_0() { return cDynamicArrayModifierAction_0; }
		
		//'['
		public Keyword getLeftSquareBracketKeyword_1() { return cLeftSquareBracketKeyword_1; }
		
		//']'
		public Keyword getRightSquareBracketKeyword_2() { return cRightSquareBracketKeyword_2; }
	}
	public class StaticArrayModifierElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "de.dlr.sc.virsat.model.concept.ConceptLanguage.StaticArrayModifier");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final Keyword cLeftSquareBracketKeyword_0 = (Keyword)cGroup.eContents().get(0);
		private final Assignment cArraySizeAssignment_1 = (Assignment)cGroup.eContents().get(1);
		private final RuleCall cArraySizeINTTerminalRuleCall_1_0 = (RuleCall)cArraySizeAssignment_1.eContents().get(0);
		private final Keyword cRightSquareBracketKeyword_2 = (Keyword)cGroup.eContents().get(2);
		
		//StaticArrayModifier PropertyDefinitions::StaticArrayModifier:
		//	'[' arraySize=INT ']';
		@Override public ParserRule getRule() { return rule; }
		
		//'[' arraySize=INT ']'
		public Group getGroup() { return cGroup; }
		
		//'['
		public Keyword getLeftSquareBracketKeyword_0() { return cLeftSquareBracketKeyword_0; }
		
		//arraySize=INT
		public Assignment getArraySizeAssignment_1() { return cArraySizeAssignment_1; }
		
		//INT
		public RuleCall getArraySizeINTTerminalRuleCall_1_0() { return cArraySizeINTTerminalRuleCall_1_0; }
		
		//']'
		public Keyword getRightSquareBracketKeyword_2() { return cRightSquareBracketKeyword_2; }
	}
	public class ComposedPropertyElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "de.dlr.sc.virsat.model.concept.ConceptLanguage.ComposedProperty");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final Action cComposedPropertyAction_0 = (Action)cGroup.eContents().get(0);
		private final Keyword cTypeKeyword_1 = (Keyword)cGroup.eContents().get(1);
		private final Assignment cNameAssignment_2 = (Assignment)cGroup.eContents().get(2);
		private final RuleCall cNameIDTerminalRuleCall_2_0 = (RuleCall)cNameAssignment_2.eContents().get(0);
		private final Assignment cArrayModifierAssignment_3 = (Assignment)cGroup.eContents().get(3);
		private final RuleCall cArrayModifierArrayModifierParserRuleCall_3_0 = (RuleCall)cArrayModifierAssignment_3.eContents().get(0);
		private final Keyword cOfKeyword_4 = (Keyword)cGroup.eContents().get(4);
		private final Keyword cCategoryKeyword_5 = (Keyword)cGroup.eContents().get(5);
		private final Assignment cTypeAssignment_6 = (Assignment)cGroup.eContents().get(6);
		private final CrossReference cTypeCategoryCrossReference_6_0 = (CrossReference)cTypeAssignment_6.eContents().get(0);
		private final RuleCall cTypeCategoryQualifiedNameParserRuleCall_6_0_1 = (RuleCall)cTypeCategoryCrossReference_6_0.eContents().get(1);
		private final UnorderedGroup cUnorderedGroup_7 = (UnorderedGroup)cGroup.eContents().get(7);
		private final Group cGroup_7_0 = (Group)cUnorderedGroup_7.eContents().get(0);
		private final Keyword cDescriptionKeyword_7_0_0 = (Keyword)cGroup_7_0.eContents().get(0);
		private final Assignment cDescriptionAssignment_7_0_1 = (Assignment)cGroup_7_0.eContents().get(1);
		private final RuleCall cDescriptionEStringParserRuleCall_7_0_1_0 = (RuleCall)cDescriptionAssignment_7_0_1.eContents().get(0);
		private final Group cGroup_7_1 = (Group)cUnorderedGroup_7.eContents().get(1);
		private final Keyword cQuantityKindKeyword_7_1_0 = (Keyword)cGroup_7_1.eContents().get(0);
		private final Assignment cQuantityKindNameAssignment_7_1_1 = (Assignment)cGroup_7_1.eContents().get(1);
		private final RuleCall cQuantityKindNameEStringParserRuleCall_7_1_1_0 = (RuleCall)cQuantityKindNameAssignment_7_1_1.eContents().get(0);
		private final Group cGroup_7_2 = (Group)cUnorderedGroup_7.eContents().get(2);
		private final Keyword cUnitKeyword_7_2_0 = (Keyword)cGroup_7_2.eContents().get(0);
		private final Assignment cUnitNameAssignment_7_2_1 = (Assignment)cGroup_7_2.eContents().get(1);
		private final RuleCall cUnitNameEStringParserRuleCall_7_2_1_0 = (RuleCall)cUnitNameAssignment_7_2_1.eContents().get(0);
		private final Keyword cSemicolonKeyword_8 = (Keyword)cGroup.eContents().get(8);
		
		//ComposedProperty PropertyDefinitions::ComposedProperty:
		//	{PropertyDefinitions::ComposedProperty}
		//	'Type' name=ID arrayModifier=ArrayModifier?
		//	'of' 'Category' type=[Categories::Category|QualifiedName] (('description' description=EString)? & ('quantityKind'
		//	quantityKindName=EString)? & ('unit' unitName=EString)?)
		//	';';
		@Override public ParserRule getRule() { return rule; }
		
		//{PropertyDefinitions::ComposedProperty} 'Type' name=ID arrayModifier=ArrayModifier? 'of' 'Category'
		//type=[Categories::Category|QualifiedName] (('description' description=EString)? & ('quantityKind'
		//quantityKindName=EString)? & ('unit' unitName=EString)?) ';'
		public Group getGroup() { return cGroup; }
		
		//{PropertyDefinitions::ComposedProperty}
		public Action getComposedPropertyAction_0() { return cComposedPropertyAction_0; }
		
		//'Type'
		public Keyword getTypeKeyword_1() { return cTypeKeyword_1; }
		
		//name=ID
		public Assignment getNameAssignment_2() { return cNameAssignment_2; }
		
		//ID
		public RuleCall getNameIDTerminalRuleCall_2_0() { return cNameIDTerminalRuleCall_2_0; }
		
		//arrayModifier=ArrayModifier?
		public Assignment getArrayModifierAssignment_3() { return cArrayModifierAssignment_3; }
		
		//ArrayModifier
		public RuleCall getArrayModifierArrayModifierParserRuleCall_3_0() { return cArrayModifierArrayModifierParserRuleCall_3_0; }
		
		//'of'
		public Keyword getOfKeyword_4() { return cOfKeyword_4; }
		
		//'Category'
		public Keyword getCategoryKeyword_5() { return cCategoryKeyword_5; }
		
		//type=[Categories::Category|QualifiedName]
		public Assignment getTypeAssignment_6() { return cTypeAssignment_6; }
		
		//[Categories::Category|QualifiedName]
		public CrossReference getTypeCategoryCrossReference_6_0() { return cTypeCategoryCrossReference_6_0; }
		
		//QualifiedName
		public RuleCall getTypeCategoryQualifiedNameParserRuleCall_6_0_1() { return cTypeCategoryQualifiedNameParserRuleCall_6_0_1; }
		
		//('description' description=EString)? & ('quantityKind' quantityKindName=EString)? & ('unit' unitName=EString)?
		public UnorderedGroup getUnorderedGroup_7() { return cUnorderedGroup_7; }
		
		//('description' description=EString)?
		public Group getGroup_7_0() { return cGroup_7_0; }
		
		//'description'
		public Keyword getDescriptionKeyword_7_0_0() { return cDescriptionKeyword_7_0_0; }
		
		//description=EString
		public Assignment getDescriptionAssignment_7_0_1() { return cDescriptionAssignment_7_0_1; }
		
		//EString
		public RuleCall getDescriptionEStringParserRuleCall_7_0_1_0() { return cDescriptionEStringParserRuleCall_7_0_1_0; }
		
		//('quantityKind' quantityKindName=EString)?
		public Group getGroup_7_1() { return cGroup_7_1; }
		
		//'quantityKind'
		public Keyword getQuantityKindKeyword_7_1_0() { return cQuantityKindKeyword_7_1_0; }
		
		//quantityKindName=EString
		public Assignment getQuantityKindNameAssignment_7_1_1() { return cQuantityKindNameAssignment_7_1_1; }
		
		//EString
		public RuleCall getQuantityKindNameEStringParserRuleCall_7_1_1_0() { return cQuantityKindNameEStringParserRuleCall_7_1_1_0; }
		
		//('unit' unitName=EString)?
		public Group getGroup_7_2() { return cGroup_7_2; }
		
		//'unit'
		public Keyword getUnitKeyword_7_2_0() { return cUnitKeyword_7_2_0; }
		
		//unitName=EString
		public Assignment getUnitNameAssignment_7_2_1() { return cUnitNameAssignment_7_2_1; }
		
		//EString
		public RuleCall getUnitNameEStringParserRuleCall_7_2_1_0() { return cUnitNameEStringParserRuleCall_7_2_1_0; }
		
		//';'
		public Keyword getSemicolonKeyword_8() { return cSemicolonKeyword_8; }
	}
	public class IntPropertyElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "de.dlr.sc.virsat.model.concept.ConceptLanguage.IntProperty");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final Action cIntPropertyAction_0 = (Action)cGroup.eContents().get(0);
		private final Keyword cIntPropertyKeyword_1 = (Keyword)cGroup.eContents().get(1);
		private final Assignment cNameAssignment_2 = (Assignment)cGroup.eContents().get(2);
		private final RuleCall cNameIDTerminalRuleCall_2_0 = (RuleCall)cNameAssignment_2.eContents().get(0);
		private final Assignment cArrayModifierAssignment_3 = (Assignment)cGroup.eContents().get(3);
		private final RuleCall cArrayModifierArrayModifierParserRuleCall_3_0 = (RuleCall)cArrayModifierAssignment_3.eContents().get(0);
		private final UnorderedGroup cUnorderedGroup_4 = (UnorderedGroup)cGroup.eContents().get(4);
		private final Group cGroup_4_0 = (Group)cUnorderedGroup_4.eContents().get(0);
		private final Keyword cDescriptionKeyword_4_0_0 = (Keyword)cGroup_4_0.eContents().get(0);
		private final Assignment cDescriptionAssignment_4_0_1 = (Assignment)cGroup_4_0.eContents().get(1);
		private final RuleCall cDescriptionEStringParserRuleCall_4_0_1_0 = (RuleCall)cDescriptionAssignment_4_0_1.eContents().get(0);
		private final Group cGroup_4_1 = (Group)cUnorderedGroup_4.eContents().get(1);
		private final Keyword cDefaultKeyword_4_1_0 = (Keyword)cGroup_4_1.eContents().get(0);
		private final Assignment cDefaultValueAssignment_4_1_1 = (Assignment)cGroup_4_1.eContents().get(1);
		private final RuleCall cDefaultValueIntLiteralStringParserRuleCall_4_1_1_0 = (RuleCall)cDefaultValueAssignment_4_1_1.eContents().get(0);
		private final Group cGroup_4_2 = (Group)cUnorderedGroup_4.eContents().get(2);
		private final Keyword cQuantityKindKeyword_4_2_0 = (Keyword)cGroup_4_2.eContents().get(0);
		private final Assignment cQuantityKindNameAssignment_4_2_1 = (Assignment)cGroup_4_2.eContents().get(1);
		private final RuleCall cQuantityKindNameEStringParserRuleCall_4_2_1_0 = (RuleCall)cQuantityKindNameAssignment_4_2_1.eContents().get(0);
		private final Group cGroup_4_3 = (Group)cUnorderedGroup_4.eContents().get(3);
		private final Keyword cUnitKeyword_4_3_0 = (Keyword)cGroup_4_3.eContents().get(0);
		private final Assignment cUnitNameAssignment_4_3_1 = (Assignment)cGroup_4_3.eContents().get(1);
		private final RuleCall cUnitNameEStringParserRuleCall_4_3_1_0 = (RuleCall)cUnitNameAssignment_4_3_1.eContents().get(0);
		private final Keyword cSemicolonKeyword_5 = (Keyword)cGroup.eContents().get(5);
		
		//IntProperty PropertyDefinitions::IntProperty:
		//	{PropertyDefinitions::IntProperty}
		//	'IntProperty' name=ID arrayModifier=ArrayModifier? (('description' description=EString)? & ('default'
		//	defaultValue=IntLiteralString)? & ('quantityKind' quantityKindName=EString)? & ('unit' unitName=EString)?)
		//	';';
		@Override public ParserRule getRule() { return rule; }
		
		//{PropertyDefinitions::IntProperty} 'IntProperty' name=ID arrayModifier=ArrayModifier? (('description'
		//description=EString)? & ('default' defaultValue=IntLiteralString)? & ('quantityKind' quantityKindName=EString)? &
		//('unit' unitName=EString)?) ';'
		public Group getGroup() { return cGroup; }
		
		//{PropertyDefinitions::IntProperty}
		public Action getIntPropertyAction_0() { return cIntPropertyAction_0; }
		
		//'IntProperty'
		public Keyword getIntPropertyKeyword_1() { return cIntPropertyKeyword_1; }
		
		//name=ID
		public Assignment getNameAssignment_2() { return cNameAssignment_2; }
		
		//ID
		public RuleCall getNameIDTerminalRuleCall_2_0() { return cNameIDTerminalRuleCall_2_0; }
		
		//arrayModifier=ArrayModifier?
		public Assignment getArrayModifierAssignment_3() { return cArrayModifierAssignment_3; }
		
		//ArrayModifier
		public RuleCall getArrayModifierArrayModifierParserRuleCall_3_0() { return cArrayModifierArrayModifierParserRuleCall_3_0; }
		
		//('description' description=EString)? & ('default' defaultValue=IntLiteralString)? & ('quantityKind'
		//quantityKindName=EString)? & ('unit' unitName=EString)?
		public UnorderedGroup getUnorderedGroup_4() { return cUnorderedGroup_4; }
		
		//('description' description=EString)?
		public Group getGroup_4_0() { return cGroup_4_0; }
		
		//'description'
		public Keyword getDescriptionKeyword_4_0_0() { return cDescriptionKeyword_4_0_0; }
		
		//description=EString
		public Assignment getDescriptionAssignment_4_0_1() { return cDescriptionAssignment_4_0_1; }
		
		//EString
		public RuleCall getDescriptionEStringParserRuleCall_4_0_1_0() { return cDescriptionEStringParserRuleCall_4_0_1_0; }
		
		//('default' defaultValue=IntLiteralString)?
		public Group getGroup_4_1() { return cGroup_4_1; }
		
		//'default'
		public Keyword getDefaultKeyword_4_1_0() { return cDefaultKeyword_4_1_0; }
		
		//defaultValue=IntLiteralString
		public Assignment getDefaultValueAssignment_4_1_1() { return cDefaultValueAssignment_4_1_1; }
		
		//IntLiteralString
		public RuleCall getDefaultValueIntLiteralStringParserRuleCall_4_1_1_0() { return cDefaultValueIntLiteralStringParserRuleCall_4_1_1_0; }
		
		//('quantityKind' quantityKindName=EString)?
		public Group getGroup_4_2() { return cGroup_4_2; }
		
		//'quantityKind'
		public Keyword getQuantityKindKeyword_4_2_0() { return cQuantityKindKeyword_4_2_0; }
		
		//quantityKindName=EString
		public Assignment getQuantityKindNameAssignment_4_2_1() { return cQuantityKindNameAssignment_4_2_1; }
		
		//EString
		public RuleCall getQuantityKindNameEStringParserRuleCall_4_2_1_0() { return cQuantityKindNameEStringParserRuleCall_4_2_1_0; }
		
		//('unit' unitName=EString)?
		public Group getGroup_4_3() { return cGroup_4_3; }
		
		//'unit'
		public Keyword getUnitKeyword_4_3_0() { return cUnitKeyword_4_3_0; }
		
		//unitName=EString
		public Assignment getUnitNameAssignment_4_3_1() { return cUnitNameAssignment_4_3_1; }
		
		//EString
		public RuleCall getUnitNameEStringParserRuleCall_4_3_1_0() { return cUnitNameEStringParserRuleCall_4_3_1_0; }
		
		//';'
		public Keyword getSemicolonKeyword_5() { return cSemicolonKeyword_5; }
	}
	public class FloatPropertyElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "de.dlr.sc.virsat.model.concept.ConceptLanguage.FloatProperty");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final Action cFloatPropertyAction_0 = (Action)cGroup.eContents().get(0);
		private final Keyword cFloatPropertyKeyword_1 = (Keyword)cGroup.eContents().get(1);
		private final Assignment cNameAssignment_2 = (Assignment)cGroup.eContents().get(2);
		private final RuleCall cNameIDTerminalRuleCall_2_0 = (RuleCall)cNameAssignment_2.eContents().get(0);
		private final Assignment cArrayModifierAssignment_3 = (Assignment)cGroup.eContents().get(3);
		private final RuleCall cArrayModifierArrayModifierParserRuleCall_3_0 = (RuleCall)cArrayModifierAssignment_3.eContents().get(0);
		private final UnorderedGroup cUnorderedGroup_4 = (UnorderedGroup)cGroup.eContents().get(4);
		private final Group cGroup_4_0 = (Group)cUnorderedGroup_4.eContents().get(0);
		private final Keyword cDescriptionKeyword_4_0_0 = (Keyword)cGroup_4_0.eContents().get(0);
		private final Assignment cDescriptionAssignment_4_0_1 = (Assignment)cGroup_4_0.eContents().get(1);
		private final RuleCall cDescriptionEStringParserRuleCall_4_0_1_0 = (RuleCall)cDescriptionAssignment_4_0_1.eContents().get(0);
		private final Group cGroup_4_1 = (Group)cUnorderedGroup_4.eContents().get(1);
		private final Keyword cDefaultKeyword_4_1_0 = (Keyword)cGroup_4_1.eContents().get(0);
		private final Assignment cDefaultValueAssignment_4_1_1 = (Assignment)cGroup_4_1.eContents().get(1);
		private final RuleCall cDefaultValueFloatLiteralStringParserRuleCall_4_1_1_0 = (RuleCall)cDefaultValueAssignment_4_1_1.eContents().get(0);
		private final Group cGroup_4_2 = (Group)cUnorderedGroup_4.eContents().get(2);
		private final Keyword cQuantityKindKeyword_4_2_0 = (Keyword)cGroup_4_2.eContents().get(0);
		private final Assignment cQuantityKindNameAssignment_4_2_1 = (Assignment)cGroup_4_2.eContents().get(1);
		private final RuleCall cQuantityKindNameEStringParserRuleCall_4_2_1_0 = (RuleCall)cQuantityKindNameAssignment_4_2_1.eContents().get(0);
		private final Group cGroup_4_3 = (Group)cUnorderedGroup_4.eContents().get(3);
		private final Keyword cUnitKeyword_4_3_0 = (Keyword)cGroup_4_3.eContents().get(0);
		private final Assignment cUnitNameAssignment_4_3_1 = (Assignment)cGroup_4_3.eContents().get(1);
		private final RuleCall cUnitNameEStringParserRuleCall_4_3_1_0 = (RuleCall)cUnitNameAssignment_4_3_1.eContents().get(0);
		private final Keyword cSemicolonKeyword_5 = (Keyword)cGroup.eContents().get(5);
		
		//FloatProperty PropertyDefinitions::FloatProperty:
		//	{PropertyDefinitions::FloatProperty}
		//	'FloatProperty' name=ID arrayModifier=ArrayModifier? (('description' description=EString)? & ('default'
		//	defaultValue=FloatLiteralString)? & ('quantityKind' quantityKindName=EString)? & ('unit' unitName=EString)?)
		//	';';
		@Override public ParserRule getRule() { return rule; }
		
		//{PropertyDefinitions::FloatProperty} 'FloatProperty' name=ID arrayModifier=ArrayModifier? (('description'
		//description=EString)? & ('default' defaultValue=FloatLiteralString)? & ('quantityKind' quantityKindName=EString)? &
		//('unit' unitName=EString)?) ';'
		public Group getGroup() { return cGroup; }
		
		//{PropertyDefinitions::FloatProperty}
		public Action getFloatPropertyAction_0() { return cFloatPropertyAction_0; }
		
		//'FloatProperty'
		public Keyword getFloatPropertyKeyword_1() { return cFloatPropertyKeyword_1; }
		
		//name=ID
		public Assignment getNameAssignment_2() { return cNameAssignment_2; }
		
		//ID
		public RuleCall getNameIDTerminalRuleCall_2_0() { return cNameIDTerminalRuleCall_2_0; }
		
		//arrayModifier=ArrayModifier?
		public Assignment getArrayModifierAssignment_3() { return cArrayModifierAssignment_3; }
		
		//ArrayModifier
		public RuleCall getArrayModifierArrayModifierParserRuleCall_3_0() { return cArrayModifierArrayModifierParserRuleCall_3_0; }
		
		//('description' description=EString)? & ('default' defaultValue=FloatLiteralString)? & ('quantityKind'
		//quantityKindName=EString)? & ('unit' unitName=EString)?
		public UnorderedGroup getUnorderedGroup_4() { return cUnorderedGroup_4; }
		
		//('description' description=EString)?
		public Group getGroup_4_0() { return cGroup_4_0; }
		
		//'description'
		public Keyword getDescriptionKeyword_4_0_0() { return cDescriptionKeyword_4_0_0; }
		
		//description=EString
		public Assignment getDescriptionAssignment_4_0_1() { return cDescriptionAssignment_4_0_1; }
		
		//EString
		public RuleCall getDescriptionEStringParserRuleCall_4_0_1_0() { return cDescriptionEStringParserRuleCall_4_0_1_0; }
		
		//('default' defaultValue=FloatLiteralString)?
		public Group getGroup_4_1() { return cGroup_4_1; }
		
		//'default'
		public Keyword getDefaultKeyword_4_1_0() { return cDefaultKeyword_4_1_0; }
		
		//defaultValue=FloatLiteralString
		public Assignment getDefaultValueAssignment_4_1_1() { return cDefaultValueAssignment_4_1_1; }
		
		//FloatLiteralString
		public RuleCall getDefaultValueFloatLiteralStringParserRuleCall_4_1_1_0() { return cDefaultValueFloatLiteralStringParserRuleCall_4_1_1_0; }
		
		//('quantityKind' quantityKindName=EString)?
		public Group getGroup_4_2() { return cGroup_4_2; }
		
		//'quantityKind'
		public Keyword getQuantityKindKeyword_4_2_0() { return cQuantityKindKeyword_4_2_0; }
		
		//quantityKindName=EString
		public Assignment getQuantityKindNameAssignment_4_2_1() { return cQuantityKindNameAssignment_4_2_1; }
		
		//EString
		public RuleCall getQuantityKindNameEStringParserRuleCall_4_2_1_0() { return cQuantityKindNameEStringParserRuleCall_4_2_1_0; }
		
		//('unit' unitName=EString)?
		public Group getGroup_4_3() { return cGroup_4_3; }
		
		//'unit'
		public Keyword getUnitKeyword_4_3_0() { return cUnitKeyword_4_3_0; }
		
		//unitName=EString
		public Assignment getUnitNameAssignment_4_3_1() { return cUnitNameAssignment_4_3_1; }
		
		//EString
		public RuleCall getUnitNameEStringParserRuleCall_4_3_1_0() { return cUnitNameEStringParserRuleCall_4_3_1_0; }
		
		//';'
		public Keyword getSemicolonKeyword_5() { return cSemicolonKeyword_5; }
	}
	public class StringPropertyElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "de.dlr.sc.virsat.model.concept.ConceptLanguage.StringProperty");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final Action cStringPropertyAction_0 = (Action)cGroup.eContents().get(0);
		private final Keyword cStringPropertyKeyword_1 = (Keyword)cGroup.eContents().get(1);
		private final Assignment cNameAssignment_2 = (Assignment)cGroup.eContents().get(2);
		private final RuleCall cNameIDTerminalRuleCall_2_0 = (RuleCall)cNameAssignment_2.eContents().get(0);
		private final Assignment cArrayModifierAssignment_3 = (Assignment)cGroup.eContents().get(3);
		private final RuleCall cArrayModifierArrayModifierParserRuleCall_3_0 = (RuleCall)cArrayModifierAssignment_3.eContents().get(0);
		private final UnorderedGroup cUnorderedGroup_4 = (UnorderedGroup)cGroup.eContents().get(4);
		private final Group cGroup_4_0 = (Group)cUnorderedGroup_4.eContents().get(0);
		private final Keyword cDescriptionKeyword_4_0_0 = (Keyword)cGroup_4_0.eContents().get(0);
		private final Assignment cDescriptionAssignment_4_0_1 = (Assignment)cGroup_4_0.eContents().get(1);
		private final RuleCall cDescriptionEStringParserRuleCall_4_0_1_0 = (RuleCall)cDescriptionAssignment_4_0_1.eContents().get(0);
		private final Group cGroup_4_1 = (Group)cUnorderedGroup_4.eContents().get(1);
		private final Keyword cDefaultKeyword_4_1_0 = (Keyword)cGroup_4_1.eContents().get(0);
		private final Assignment cDefaultValueAssignment_4_1_1 = (Assignment)cGroup_4_1.eContents().get(1);
		private final RuleCall cDefaultValueEStringParserRuleCall_4_1_1_0 = (RuleCall)cDefaultValueAssignment_4_1_1.eContents().get(0);
		private final Keyword cSemicolonKeyword_5 = (Keyword)cGroup.eContents().get(5);
		
		//StringProperty PropertyDefinitions::StringProperty:
		//	{PropertyDefinitions::StringProperty}
		//	'StringProperty' name=ID arrayModifier=ArrayModifier? (('description' description=EString)? & ('default'
		//	defaultValue=EString)?)
		//	';';
		@Override public ParserRule getRule() { return rule; }
		
		//{PropertyDefinitions::StringProperty} 'StringProperty' name=ID arrayModifier=ArrayModifier? (('description'
		//description=EString)? & ('default' defaultValue=EString)?) ';'
		public Group getGroup() { return cGroup; }
		
		//{PropertyDefinitions::StringProperty}
		public Action getStringPropertyAction_0() { return cStringPropertyAction_0; }
		
		//'StringProperty'
		public Keyword getStringPropertyKeyword_1() { return cStringPropertyKeyword_1; }
		
		//name=ID
		public Assignment getNameAssignment_2() { return cNameAssignment_2; }
		
		//ID
		public RuleCall getNameIDTerminalRuleCall_2_0() { return cNameIDTerminalRuleCall_2_0; }
		
		//arrayModifier=ArrayModifier?
		public Assignment getArrayModifierAssignment_3() { return cArrayModifierAssignment_3; }
		
		//ArrayModifier
		public RuleCall getArrayModifierArrayModifierParserRuleCall_3_0() { return cArrayModifierArrayModifierParserRuleCall_3_0; }
		
		//('description' description=EString)? & ('default' defaultValue=EString)?
		public UnorderedGroup getUnorderedGroup_4() { return cUnorderedGroup_4; }
		
		//('description' description=EString)?
		public Group getGroup_4_0() { return cGroup_4_0; }
		
		//'description'
		public Keyword getDescriptionKeyword_4_0_0() { return cDescriptionKeyword_4_0_0; }
		
		//description=EString
		public Assignment getDescriptionAssignment_4_0_1() { return cDescriptionAssignment_4_0_1; }
		
		//EString
		public RuleCall getDescriptionEStringParserRuleCall_4_0_1_0() { return cDescriptionEStringParserRuleCall_4_0_1_0; }
		
		//('default' defaultValue=EString)?
		public Group getGroup_4_1() { return cGroup_4_1; }
		
		//'default'
		public Keyword getDefaultKeyword_4_1_0() { return cDefaultKeyword_4_1_0; }
		
		//defaultValue=EString
		public Assignment getDefaultValueAssignment_4_1_1() { return cDefaultValueAssignment_4_1_1; }
		
		//EString
		public RuleCall getDefaultValueEStringParserRuleCall_4_1_1_0() { return cDefaultValueEStringParserRuleCall_4_1_1_0; }
		
		//';'
		public Keyword getSemicolonKeyword_5() { return cSemicolonKeyword_5; }
	}
	public class BooleanPropertyElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "de.dlr.sc.virsat.model.concept.ConceptLanguage.BooleanProperty");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final Keyword cBooleanPropertyKeyword_0 = (Keyword)cGroup.eContents().get(0);
		private final Assignment cNameAssignment_1 = (Assignment)cGroup.eContents().get(1);
		private final RuleCall cNameIDTerminalRuleCall_1_0 = (RuleCall)cNameAssignment_1.eContents().get(0);
		private final Assignment cArrayModifierAssignment_2 = (Assignment)cGroup.eContents().get(2);
		private final RuleCall cArrayModifierArrayModifierParserRuleCall_2_0 = (RuleCall)cArrayModifierAssignment_2.eContents().get(0);
		private final UnorderedGroup cUnorderedGroup_3 = (UnorderedGroup)cGroup.eContents().get(3);
		private final Group cGroup_3_0 = (Group)cUnorderedGroup_3.eContents().get(0);
		private final Keyword cDescriptionKeyword_3_0_0 = (Keyword)cGroup_3_0.eContents().get(0);
		private final Assignment cDescriptionAssignment_3_0_1 = (Assignment)cGroup_3_0.eContents().get(1);
		private final RuleCall cDescriptionEStringParserRuleCall_3_0_1_0 = (RuleCall)cDescriptionAssignment_3_0_1.eContents().get(0);
		private final Group cGroup_3_1 = (Group)cUnorderedGroup_3.eContents().get(1);
		private final Keyword cDefaultKeyword_3_1_0 = (Keyword)cGroup_3_1.eContents().get(0);
		private final Assignment cDefaultValueAssignment_3_1_1 = (Assignment)cGroup_3_1.eContents().get(1);
		private final RuleCall cDefaultValueBooleanLiteralStringParserRuleCall_3_1_1_0 = (RuleCall)cDefaultValueAssignment_3_1_1.eContents().get(0);
		private final Keyword cSemicolonKeyword_4 = (Keyword)cGroup.eContents().get(4);
		
		//BooleanProperty PropertyDefinitions::BooleanProperty:
		//	'BooleanProperty' name=ID arrayModifier=ArrayModifier? (('description' description=EString)? & ('default'
		//	defaultValue=BooleanLiteralString)?)
		//	';';
		@Override public ParserRule getRule() { return rule; }
		
		//'BooleanProperty' name=ID arrayModifier=ArrayModifier? (('description' description=EString)? & ('default'
		//defaultValue=BooleanLiteralString)?) ';'
		public Group getGroup() { return cGroup; }
		
		//'BooleanProperty'
		public Keyword getBooleanPropertyKeyword_0() { return cBooleanPropertyKeyword_0; }
		
		//name=ID
		public Assignment getNameAssignment_1() { return cNameAssignment_1; }
		
		//ID
		public RuleCall getNameIDTerminalRuleCall_1_0() { return cNameIDTerminalRuleCall_1_0; }
		
		//arrayModifier=ArrayModifier?
		public Assignment getArrayModifierAssignment_2() { return cArrayModifierAssignment_2; }
		
		//ArrayModifier
		public RuleCall getArrayModifierArrayModifierParserRuleCall_2_0() { return cArrayModifierArrayModifierParserRuleCall_2_0; }
		
		//('description' description=EString)? & ('default' defaultValue=BooleanLiteralString)?
		public UnorderedGroup getUnorderedGroup_3() { return cUnorderedGroup_3; }
		
		//('description' description=EString)?
		public Group getGroup_3_0() { return cGroup_3_0; }
		
		//'description'
		public Keyword getDescriptionKeyword_3_0_0() { return cDescriptionKeyword_3_0_0; }
		
		//description=EString
		public Assignment getDescriptionAssignment_3_0_1() { return cDescriptionAssignment_3_0_1; }
		
		//EString
		public RuleCall getDescriptionEStringParserRuleCall_3_0_1_0() { return cDescriptionEStringParserRuleCall_3_0_1_0; }
		
		//('default' defaultValue=BooleanLiteralString)?
		public Group getGroup_3_1() { return cGroup_3_1; }
		
		//'default'
		public Keyword getDefaultKeyword_3_1_0() { return cDefaultKeyword_3_1_0; }
		
		//defaultValue=BooleanLiteralString
		public Assignment getDefaultValueAssignment_3_1_1() { return cDefaultValueAssignment_3_1_1; }
		
		//BooleanLiteralString
		public RuleCall getDefaultValueBooleanLiteralStringParserRuleCall_3_1_1_0() { return cDefaultValueBooleanLiteralStringParserRuleCall_3_1_1_0; }
		
		//';'
		public Keyword getSemicolonKeyword_4() { return cSemicolonKeyword_4; }
	}
	public class EnumPropertyElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "de.dlr.sc.virsat.model.concept.ConceptLanguage.EnumProperty");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final Keyword cEnumPropertyKeyword_0 = (Keyword)cGroup.eContents().get(0);
		private final Assignment cNameAssignment_1 = (Assignment)cGroup.eContents().get(1);
		private final RuleCall cNameIDTerminalRuleCall_1_0 = (RuleCall)cNameAssignment_1.eContents().get(0);
		private final Assignment cArrayModifierAssignment_2 = (Assignment)cGroup.eContents().get(2);
		private final RuleCall cArrayModifierArrayModifierParserRuleCall_2_0 = (RuleCall)cArrayModifierAssignment_2.eContents().get(0);
		private final UnorderedGroup cUnorderedGroup_3 = (UnorderedGroup)cGroup.eContents().get(3);
		private final Group cGroup_3_0 = (Group)cUnorderedGroup_3.eContents().get(0);
		private final Keyword cDescriptionKeyword_3_0_0 = (Keyword)cGroup_3_0.eContents().get(0);
		private final Assignment cDescriptionAssignment_3_0_1 = (Assignment)cGroup_3_0.eContents().get(1);
		private final RuleCall cDescriptionEStringParserRuleCall_3_0_1_0 = (RuleCall)cDescriptionAssignment_3_0_1.eContents().get(0);
		private final Group cGroup_3_1 = (Group)cUnorderedGroup_3.eContents().get(1);
		private final Keyword cQuantityKindKeyword_3_1_0 = (Keyword)cGroup_3_1.eContents().get(0);
		private final Assignment cQuantityKindNameAssignment_3_1_1 = (Assignment)cGroup_3_1.eContents().get(1);
		private final RuleCall cQuantityKindNameEStringParserRuleCall_3_1_1_0 = (RuleCall)cQuantityKindNameAssignment_3_1_1.eContents().get(0);
		private final Group cGroup_3_2 = (Group)cUnorderedGroup_3.eContents().get(2);
		private final Keyword cUnitKeyword_3_2_0 = (Keyword)cGroup_3_2.eContents().get(0);
		private final Assignment cUnitNameAssignment_3_2_1 = (Assignment)cGroup_3_2.eContents().get(1);
		private final RuleCall cUnitNameEStringParserRuleCall_3_2_1_0 = (RuleCall)cUnitNameAssignment_3_2_1.eContents().get(0);
		private final Group cGroup_3_3 = (Group)cUnorderedGroup_3.eContents().get(3);
		private final Keyword cValuesKeyword_3_3_0 = (Keyword)cGroup_3_3.eContents().get(0);
		private final Keyword cLeftSquareBracketKeyword_3_3_1 = (Keyword)cGroup_3_3.eContents().get(1);
		private final Assignment cValuesAssignment_3_3_2 = (Assignment)cGroup_3_3.eContents().get(2);
		private final RuleCall cValuesEnumValueDefinitionParserRuleCall_3_3_2_0 = (RuleCall)cValuesAssignment_3_3_2.eContents().get(0);
		private final Group cGroup_3_3_3 = (Group)cGroup_3_3.eContents().get(3);
		private final Keyword cCommaKeyword_3_3_3_0 = (Keyword)cGroup_3_3_3.eContents().get(0);
		private final Assignment cValuesAssignment_3_3_3_1 = (Assignment)cGroup_3_3_3.eContents().get(1);
		private final RuleCall cValuesEnumValueDefinitionParserRuleCall_3_3_3_1_0 = (RuleCall)cValuesAssignment_3_3_3_1.eContents().get(0);
		private final Keyword cRightSquareBracketKeyword_3_3_4 = (Keyword)cGroup_3_3.eContents().get(4);
		private final Group cGroup_3_4 = (Group)cUnorderedGroup_3.eContents().get(4);
		private final Keyword cDefaultKeyword_3_4_0 = (Keyword)cGroup_3_4.eContents().get(0);
		private final Assignment cDefaultValueAssignment_3_4_1 = (Assignment)cGroup_3_4.eContents().get(1);
		private final CrossReference cDefaultValueEnumValueDefinitionCrossReference_3_4_1_0 = (CrossReference)cDefaultValueAssignment_3_4_1.eContents().get(0);
		private final RuleCall cDefaultValueEnumValueDefinitionIDTerminalRuleCall_3_4_1_0_1 = (RuleCall)cDefaultValueEnumValueDefinitionCrossReference_3_4_1_0.eContents().get(1);
		private final Keyword cSemicolonKeyword_4 = (Keyword)cGroup.eContents().get(4);
		
		//EnumProperty PropertyDefinitions::EnumProperty:
		//	'EnumProperty' name=ID arrayModifier=ArrayModifier? (('description' description=EString)? & ('quantityKind'
		//	quantityKindName=EString)? & ('unit' unitName=EString)? & 'values' '[' values+=EnumValueDefinition (','
		//	values+=EnumValueDefinition)* ']' & ('default' defaultValue=[PropertyDefinitions::EnumValueDefinition])?)
		//	';';
		@Override public ParserRule getRule() { return rule; }
		
		//'EnumProperty' name=ID arrayModifier=ArrayModifier? (('description' description=EString)? & ('quantityKind'
		//quantityKindName=EString)? & ('unit' unitName=EString)? & 'values' '[' values+=EnumValueDefinition (','
		//values+=EnumValueDefinition)* ']' & ('default' defaultValue=[PropertyDefinitions::EnumValueDefinition])?) ';'
		public Group getGroup() { return cGroup; }
		
		//'EnumProperty'
		public Keyword getEnumPropertyKeyword_0() { return cEnumPropertyKeyword_0; }
		
		//name=ID
		public Assignment getNameAssignment_1() { return cNameAssignment_1; }
		
		//ID
		public RuleCall getNameIDTerminalRuleCall_1_0() { return cNameIDTerminalRuleCall_1_0; }
		
		//arrayModifier=ArrayModifier?
		public Assignment getArrayModifierAssignment_2() { return cArrayModifierAssignment_2; }
		
		//ArrayModifier
		public RuleCall getArrayModifierArrayModifierParserRuleCall_2_0() { return cArrayModifierArrayModifierParserRuleCall_2_0; }
		
		//('description' description=EString)? & ('quantityKind' quantityKindName=EString)? & ('unit' unitName=EString)? &
		//'values' '[' values+=EnumValueDefinition (',' values+=EnumValueDefinition)* ']' & ('default'
		//defaultValue=[PropertyDefinitions::EnumValueDefinition])?
		public UnorderedGroup getUnorderedGroup_3() { return cUnorderedGroup_3; }
		
		//('description' description=EString)?
		public Group getGroup_3_0() { return cGroup_3_0; }
		
		//'description'
		public Keyword getDescriptionKeyword_3_0_0() { return cDescriptionKeyword_3_0_0; }
		
		//description=EString
		public Assignment getDescriptionAssignment_3_0_1() { return cDescriptionAssignment_3_0_1; }
		
		//EString
		public RuleCall getDescriptionEStringParserRuleCall_3_0_1_0() { return cDescriptionEStringParserRuleCall_3_0_1_0; }
		
		//('quantityKind' quantityKindName=EString)?
		public Group getGroup_3_1() { return cGroup_3_1; }
		
		//'quantityKind'
		public Keyword getQuantityKindKeyword_3_1_0() { return cQuantityKindKeyword_3_1_0; }
		
		//quantityKindName=EString
		public Assignment getQuantityKindNameAssignment_3_1_1() { return cQuantityKindNameAssignment_3_1_1; }
		
		//EString
		public RuleCall getQuantityKindNameEStringParserRuleCall_3_1_1_0() { return cQuantityKindNameEStringParserRuleCall_3_1_1_0; }
		
		//('unit' unitName=EString)?
		public Group getGroup_3_2() { return cGroup_3_2; }
		
		//'unit'
		public Keyword getUnitKeyword_3_2_0() { return cUnitKeyword_3_2_0; }
		
		//unitName=EString
		public Assignment getUnitNameAssignment_3_2_1() { return cUnitNameAssignment_3_2_1; }
		
		//EString
		public RuleCall getUnitNameEStringParserRuleCall_3_2_1_0() { return cUnitNameEStringParserRuleCall_3_2_1_0; }
		
		//'values' '[' values+=EnumValueDefinition (',' values+=EnumValueDefinition)* ']'
		public Group getGroup_3_3() { return cGroup_3_3; }
		
		//'values'
		public Keyword getValuesKeyword_3_3_0() { return cValuesKeyword_3_3_0; }
		
		//'['
		public Keyword getLeftSquareBracketKeyword_3_3_1() { return cLeftSquareBracketKeyword_3_3_1; }
		
		//values+=EnumValueDefinition
		public Assignment getValuesAssignment_3_3_2() { return cValuesAssignment_3_3_2; }
		
		//EnumValueDefinition
		public RuleCall getValuesEnumValueDefinitionParserRuleCall_3_3_2_0() { return cValuesEnumValueDefinitionParserRuleCall_3_3_2_0; }
		
		//(',' values+=EnumValueDefinition)*
		public Group getGroup_3_3_3() { return cGroup_3_3_3; }
		
		//','
		public Keyword getCommaKeyword_3_3_3_0() { return cCommaKeyword_3_3_3_0; }
		
		//values+=EnumValueDefinition
		public Assignment getValuesAssignment_3_3_3_1() { return cValuesAssignment_3_3_3_1; }
		
		//EnumValueDefinition
		public RuleCall getValuesEnumValueDefinitionParserRuleCall_3_3_3_1_0() { return cValuesEnumValueDefinitionParserRuleCall_3_3_3_1_0; }
		
		//']'
		public Keyword getRightSquareBracketKeyword_3_3_4() { return cRightSquareBracketKeyword_3_3_4; }
		
		//('default' defaultValue=[PropertyDefinitions::EnumValueDefinition])?
		public Group getGroup_3_4() { return cGroup_3_4; }
		
		//'default'
		public Keyword getDefaultKeyword_3_4_0() { return cDefaultKeyword_3_4_0; }
		
		//defaultValue=[PropertyDefinitions::EnumValueDefinition]
		public Assignment getDefaultValueAssignment_3_4_1() { return cDefaultValueAssignment_3_4_1; }
		
		//[PropertyDefinitions::EnumValueDefinition]
		public CrossReference getDefaultValueEnumValueDefinitionCrossReference_3_4_1_0() { return cDefaultValueEnumValueDefinitionCrossReference_3_4_1_0; }
		
		//ID
		public RuleCall getDefaultValueEnumValueDefinitionIDTerminalRuleCall_3_4_1_0_1() { return cDefaultValueEnumValueDefinitionIDTerminalRuleCall_3_4_1_0_1; }
		
		//';'
		public Keyword getSemicolonKeyword_4() { return cSemicolonKeyword_4; }
	}
	public class EnumValueDefinitionElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "de.dlr.sc.virsat.model.concept.ConceptLanguage.EnumValueDefinition");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final Assignment cNameAssignment_0 = (Assignment)cGroup.eContents().get(0);
		private final RuleCall cNameIDTerminalRuleCall_0_0 = (RuleCall)cNameAssignment_0.eContents().get(0);
		private final Keyword cEqualsSignKeyword_1 = (Keyword)cGroup.eContents().get(1);
		private final Assignment cValueAssignment_2 = (Assignment)cGroup.eContents().get(2);
		private final Alternatives cValueAlternatives_2_0 = (Alternatives)cValueAssignment_2.eContents().get(0);
		private final RuleCall cValueEStringParserRuleCall_2_0_0 = (RuleCall)cValueAlternatives_2_0.eContents().get(0);
		private final RuleCall cValueFloatLiteralStringParserRuleCall_2_0_1 = (RuleCall)cValueAlternatives_2_0.eContents().get(1);
		
		//EnumValueDefinition PropertyDefinitions::EnumValueDefinition:
		//	name=ID '=' value=(EString | FloatLiteralString);
		@Override public ParserRule getRule() { return rule; }
		
		//name=ID '=' value=(EString | FloatLiteralString)
		public Group getGroup() { return cGroup; }
		
		//name=ID
		public Assignment getNameAssignment_0() { return cNameAssignment_0; }
		
		//ID
		public RuleCall getNameIDTerminalRuleCall_0_0() { return cNameIDTerminalRuleCall_0_0; }
		
		//'='
		public Keyword getEqualsSignKeyword_1() { return cEqualsSignKeyword_1; }
		
		//value=(EString | FloatLiteralString)
		public Assignment getValueAssignment_2() { return cValueAssignment_2; }
		
		//(EString | FloatLiteralString)
		public Alternatives getValueAlternatives_2_0() { return cValueAlternatives_2_0; }
		
		//EString
		public RuleCall getValueEStringParserRuleCall_2_0_0() { return cValueEStringParserRuleCall_2_0_0; }
		
		//FloatLiteralString
		public RuleCall getValueFloatLiteralStringParserRuleCall_2_0_1() { return cValueFloatLiteralStringParserRuleCall_2_0_1; }
	}
	public class ReferencePropertyElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "de.dlr.sc.virsat.model.concept.ConceptLanguage.ReferenceProperty");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final Keyword cReferenceKeyword_0 = (Keyword)cGroup.eContents().get(0);
		private final Assignment cNameAssignment_1 = (Assignment)cGroup.eContents().get(1);
		private final RuleCall cNameIDTerminalRuleCall_1_0 = (RuleCall)cNameAssignment_1.eContents().get(0);
		private final Assignment cArrayModifierAssignment_2 = (Assignment)cGroup.eContents().get(2);
		private final RuleCall cArrayModifierArrayModifierParserRuleCall_2_0 = (RuleCall)cArrayModifierAssignment_2.eContents().get(0);
		private final Keyword cOfKeyword_3 = (Keyword)cGroup.eContents().get(3);
		private final Keyword cTypeKeyword_4 = (Keyword)cGroup.eContents().get(4);
		private final Assignment cReferenceTypeAssignment_5 = (Assignment)cGroup.eContents().get(5);
		private final CrossReference cReferenceTypeATypeDefinitionCrossReference_5_0 = (CrossReference)cReferenceTypeAssignment_5.eContents().get(0);
		private final RuleCall cReferenceTypeATypeDefinitionQualifiedNameParserRuleCall_5_0_1 = (RuleCall)cReferenceTypeATypeDefinitionCrossReference_5_0.eContents().get(1);
		private final Group cGroup_6 = (Group)cGroup.eContents().get(6);
		private final Keyword cDescriptionKeyword_6_0 = (Keyword)cGroup_6.eContents().get(0);
		private final Assignment cDescriptionAssignment_6_1 = (Assignment)cGroup_6.eContents().get(1);
		private final RuleCall cDescriptionEStringParserRuleCall_6_1_0 = (RuleCall)cDescriptionAssignment_6_1.eContents().get(0);
		private final Keyword cSemicolonKeyword_7 = (Keyword)cGroup.eContents().get(7);
		
		//ReferenceProperty PropertyDefinitions::ReferenceProperty:
		//	'Reference' name=ID arrayModifier=ArrayModifier?
		//	'of' 'Type' referenceType=[Categories::ATypeDefinition|QualifiedName] ('description' description=EString)?
		//	';';
		@Override public ParserRule getRule() { return rule; }
		
		//'Reference' name=ID arrayModifier=ArrayModifier? 'of' 'Type' referenceType=[Categories::ATypeDefinition|QualifiedName]
		//('description' description=EString)? ';'
		public Group getGroup() { return cGroup; }
		
		//'Reference'
		public Keyword getReferenceKeyword_0() { return cReferenceKeyword_0; }
		
		//name=ID
		public Assignment getNameAssignment_1() { return cNameAssignment_1; }
		
		//ID
		public RuleCall getNameIDTerminalRuleCall_1_0() { return cNameIDTerminalRuleCall_1_0; }
		
		//arrayModifier=ArrayModifier?
		public Assignment getArrayModifierAssignment_2() { return cArrayModifierAssignment_2; }
		
		//ArrayModifier
		public RuleCall getArrayModifierArrayModifierParserRuleCall_2_0() { return cArrayModifierArrayModifierParserRuleCall_2_0; }
		
		//'of'
		public Keyword getOfKeyword_3() { return cOfKeyword_3; }
		
		//'Type'
		public Keyword getTypeKeyword_4() { return cTypeKeyword_4; }
		
		//referenceType=[Categories::ATypeDefinition|QualifiedName]
		public Assignment getReferenceTypeAssignment_5() { return cReferenceTypeAssignment_5; }
		
		//[Categories::ATypeDefinition|QualifiedName]
		public CrossReference getReferenceTypeATypeDefinitionCrossReference_5_0() { return cReferenceTypeATypeDefinitionCrossReference_5_0; }
		
		//QualifiedName
		public RuleCall getReferenceTypeATypeDefinitionQualifiedNameParserRuleCall_5_0_1() { return cReferenceTypeATypeDefinitionQualifiedNameParserRuleCall_5_0_1; }
		
		//('description' description=EString)?
		public Group getGroup_6() { return cGroup_6; }
		
		//'description'
		public Keyword getDescriptionKeyword_6_0() { return cDescriptionKeyword_6_0; }
		
		//description=EString
		public Assignment getDescriptionAssignment_6_1() { return cDescriptionAssignment_6_1; }
		
		//EString
		public RuleCall getDescriptionEStringParserRuleCall_6_1_0() { return cDescriptionEStringParserRuleCall_6_1_0; }
		
		//';'
		public Keyword getSemicolonKeyword_7() { return cSemicolonKeyword_7; }
	}
	public class EReferencePropertyElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "de.dlr.sc.virsat.model.concept.ConceptLanguage.EReferenceProperty");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final Keyword cEReferenceKeyword_0 = (Keyword)cGroup.eContents().get(0);
		private final Assignment cNameAssignment_1 = (Assignment)cGroup.eContents().get(1);
		private final RuleCall cNameIDTerminalRuleCall_1_0 = (RuleCall)cNameAssignment_1.eContents().get(0);
		private final Assignment cArrayModifierAssignment_2 = (Assignment)cGroup.eContents().get(2);
		private final RuleCall cArrayModifierArrayModifierParserRuleCall_2_0 = (RuleCall)cArrayModifierAssignment_2.eContents().get(0);
		private final Keyword cOfKeyword_3 = (Keyword)cGroup.eContents().get(3);
		private final Keyword cTypeKeyword_4 = (Keyword)cGroup.eContents().get(4);
		private final Assignment cReferenceTypeAssignment_5 = (Assignment)cGroup.eContents().get(5);
		private final CrossReference cReferenceTypeEClassCrossReference_5_0 = (CrossReference)cReferenceTypeAssignment_5.eContents().get(0);
		private final RuleCall cReferenceTypeEClassQualifiedNameParserRuleCall_5_0_1 = (RuleCall)cReferenceTypeEClassCrossReference_5_0.eContents().get(1);
		private final Group cGroup_6 = (Group)cGroup.eContents().get(6);
		private final Keyword cDescriptionKeyword_6_0 = (Keyword)cGroup_6.eContents().get(0);
		private final Assignment cDescriptionAssignment_6_1 = (Assignment)cGroup_6.eContents().get(1);
		private final RuleCall cDescriptionEStringParserRuleCall_6_1_0 = (RuleCall)cDescriptionAssignment_6_1.eContents().get(0);
		private final Keyword cSemicolonKeyword_7 = (Keyword)cGroup.eContents().get(7);
		
		//EReferenceProperty PropertyDefinitions::EReferenceProperty:
		//	'EReference' name=ID arrayModifier=ArrayModifier?
		//	'of' 'Type' referenceType=[ecore::EClass|QualifiedName] ('description' description=EString)?
		//	';';
		@Override public ParserRule getRule() { return rule; }
		
		//'EReference' name=ID arrayModifier=ArrayModifier? 'of' 'Type' referenceType=[ecore::EClass|QualifiedName] ('description'
		//description=EString)? ';'
		public Group getGroup() { return cGroup; }
		
		//'EReference'
		public Keyword getEReferenceKeyword_0() { return cEReferenceKeyword_0; }
		
		//name=ID
		public Assignment getNameAssignment_1() { return cNameAssignment_1; }
		
		//ID
		public RuleCall getNameIDTerminalRuleCall_1_0() { return cNameIDTerminalRuleCall_1_0; }
		
		//arrayModifier=ArrayModifier?
		public Assignment getArrayModifierAssignment_2() { return cArrayModifierAssignment_2; }
		
		//ArrayModifier
		public RuleCall getArrayModifierArrayModifierParserRuleCall_2_0() { return cArrayModifierArrayModifierParserRuleCall_2_0; }
		
		//'of'
		public Keyword getOfKeyword_3() { return cOfKeyword_3; }
		
		//'Type'
		public Keyword getTypeKeyword_4() { return cTypeKeyword_4; }
		
		//referenceType=[ecore::EClass|QualifiedName]
		public Assignment getReferenceTypeAssignment_5() { return cReferenceTypeAssignment_5; }
		
		//[ecore::EClass|QualifiedName]
		public CrossReference getReferenceTypeEClassCrossReference_5_0() { return cReferenceTypeEClassCrossReference_5_0; }
		
		//QualifiedName
		public RuleCall getReferenceTypeEClassQualifiedNameParserRuleCall_5_0_1() { return cReferenceTypeEClassQualifiedNameParserRuleCall_5_0_1; }
		
		//('description' description=EString)?
		public Group getGroup_6() { return cGroup_6; }
		
		//'description'
		public Keyword getDescriptionKeyword_6_0() { return cDescriptionKeyword_6_0; }
		
		//description=EString
		public Assignment getDescriptionAssignment_6_1() { return cDescriptionAssignment_6_1; }
		
		//EString
		public RuleCall getDescriptionEStringParserRuleCall_6_1_0() { return cDescriptionEStringParserRuleCall_6_1_0; }
		
		//';'
		public Keyword getSemicolonKeyword_7() { return cSemicolonKeyword_7; }
	}
	public class ResourcePropertyElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "de.dlr.sc.virsat.model.concept.ConceptLanguage.ResourceProperty");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final Keyword cResourceKeyword_0 = (Keyword)cGroup.eContents().get(0);
		private final Assignment cNameAssignment_1 = (Assignment)cGroup.eContents().get(1);
		private final RuleCall cNameIDTerminalRuleCall_1_0 = (RuleCall)cNameAssignment_1.eContents().get(0);
		private final Assignment cArrayModifierAssignment_2 = (Assignment)cGroup.eContents().get(2);
		private final RuleCall cArrayModifierArrayModifierParserRuleCall_2_0 = (RuleCall)cArrayModifierAssignment_2.eContents().get(0);
		private final Group cGroup_3 = (Group)cGroup.eContents().get(3);
		private final Keyword cDescriptionKeyword_3_0 = (Keyword)cGroup_3.eContents().get(0);
		private final Assignment cDescriptionAssignment_3_1 = (Assignment)cGroup_3.eContents().get(1);
		private final RuleCall cDescriptionEStringParserRuleCall_3_1_0 = (RuleCall)cDescriptionAssignment_3_1.eContents().get(0);
		private final Keyword cSemicolonKeyword_4 = (Keyword)cGroup.eContents().get(4);
		
		//ResourceProperty PropertyDefinitions::ResourceProperty:
		//	'Resource' name=ID arrayModifier=ArrayModifier? ('description' description=EString)?
		//	';';
		@Override public ParserRule getRule() { return rule; }
		
		//'Resource' name=ID arrayModifier=ArrayModifier? ('description' description=EString)? ';'
		public Group getGroup() { return cGroup; }
		
		//'Resource'
		public Keyword getResourceKeyword_0() { return cResourceKeyword_0; }
		
		//name=ID
		public Assignment getNameAssignment_1() { return cNameAssignment_1; }
		
		//ID
		public RuleCall getNameIDTerminalRuleCall_1_0() { return cNameIDTerminalRuleCall_1_0; }
		
		//arrayModifier=ArrayModifier?
		public Assignment getArrayModifierAssignment_2() { return cArrayModifierAssignment_2; }
		
		//ArrayModifier
		public RuleCall getArrayModifierArrayModifierParserRuleCall_2_0() { return cArrayModifierArrayModifierParserRuleCall_2_0; }
		
		//('description' description=EString)?
		public Group getGroup_3() { return cGroup_3; }
		
		//'description'
		public Keyword getDescriptionKeyword_3_0() { return cDescriptionKeyword_3_0; }
		
		//description=EString
		public Assignment getDescriptionAssignment_3_1() { return cDescriptionAssignment_3_1; }
		
		//EString
		public RuleCall getDescriptionEStringParserRuleCall_3_1_0() { return cDescriptionEStringParserRuleCall_3_1_0; }
		
		//';'
		public Keyword getSemicolonKeyword_4() { return cSemicolonKeyword_4; }
	}
	public class EquationDefinitionElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "de.dlr.sc.virsat.model.concept.ConceptLanguage.EquationDefinition");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final Assignment cResultAssignment_0 = (Assignment)cGroup.eContents().get(0);
		private final RuleCall cResultEquationDefinitionResultParserRuleCall_0_0 = (RuleCall)cResultAssignment_0.eContents().get(0);
		private final Keyword cEqualsSignKeyword_1 = (Keyword)cGroup.eContents().get(1);
		private final Assignment cExpressionAssignment_2 = (Assignment)cGroup.eContents().get(2);
		private final RuleCall cExpressionAdditionAndSubtractionParserRuleCall_2_0 = (RuleCall)cExpressionAssignment_2.eContents().get(0);
		private final Keyword cSemicolonKeyword_3 = (Keyword)cGroup.eContents().get(3);
		
		//// ***************************************************************************************
		//// Calculation
		//// ***************************************************************************************
		//EquationDefinition Calc::EquationDefinition:
		//	result=EquationDefinitionResult '=' expression=AdditionAndSubtraction ';';
		@Override public ParserRule getRule() { return rule; }
		
		//result=EquationDefinitionResult '=' expression=AdditionAndSubtraction ';'
		public Group getGroup() { return cGroup; }
		
		//result=EquationDefinitionResult
		public Assignment getResultAssignment_0() { return cResultAssignment_0; }
		
		//EquationDefinitionResult
		public RuleCall getResultEquationDefinitionResultParserRuleCall_0_0() { return cResultEquationDefinitionResultParserRuleCall_0_0; }
		
		//'='
		public Keyword getEqualsSignKeyword_1() { return cEqualsSignKeyword_1; }
		
		//expression=AdditionAndSubtraction
		public Assignment getExpressionAssignment_2() { return cExpressionAssignment_2; }
		
		//AdditionAndSubtraction
		public RuleCall getExpressionAdditionAndSubtractionParserRuleCall_2_0() { return cExpressionAdditionAndSubtractionParserRuleCall_2_0; }
		
		//';'
		public Keyword getSemicolonKeyword_3() { return cSemicolonKeyword_3; }
	}
	public class EquationDefinitionResultElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "de.dlr.sc.virsat.model.concept.ConceptLanguage.EquationDefinitionResult");
		private final Alternatives cAlternatives = (Alternatives)rule.eContents().get(1);
		private final RuleCall cTypeDefinitionResultParserRuleCall_0 = (RuleCall)cAlternatives.eContents().get(0);
		private final RuleCall cEquationIntermediateResultParserRuleCall_1 = (RuleCall)cAlternatives.eContents().get(1);
		
		//EquationDefinitionResult Calc::IEquationDefinitionResult:
		//	TypeDefinitionResult | EquationIntermediateResult;
		@Override public ParserRule getRule() { return rule; }
		
		//TypeDefinitionResult | EquationIntermediateResult
		public Alternatives getAlternatives() { return cAlternatives; }
		
		//TypeDefinitionResult
		public RuleCall getTypeDefinitionResultParserRuleCall_0() { return cTypeDefinitionResultParserRuleCall_0; }
		
		//EquationIntermediateResult
		public RuleCall getEquationIntermediateResultParserRuleCall_1() { return cEquationIntermediateResultParserRuleCall_1; }
	}
	public class TypeDefinitionResultElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "de.dlr.sc.virsat.model.concept.ConceptLanguage.TypeDefinitionResult");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final Action cTypeDefinitionResultAction_0 = (Action)cGroup.eContents().get(0);
		private final Keyword cRefKeyword_1 = (Keyword)cGroup.eContents().get(1);
		private final Assignment cReferenceAssignment_2 = (Assignment)cGroup.eContents().get(2);
		private final CrossReference cReferenceATypeDefinitionCrossReference_2_0 = (CrossReference)cReferenceAssignment_2.eContents().get(0);
		private final RuleCall cReferenceATypeDefinitionQualifiedNameParserRuleCall_2_0_1 = (RuleCall)cReferenceATypeDefinitionCrossReference_2_0.eContents().get(1);
		
		//TypeDefinitionResult Calc::IEquationDefinitionResult:
		//	{Calc::TypeDefinitionResult}
		//	'Ref:' reference=[Categories::ATypeDefinition|QualifiedName];
		@Override public ParserRule getRule() { return rule; }
		
		//{Calc::TypeDefinitionResult} 'Ref:' reference=[Categories::ATypeDefinition|QualifiedName]
		public Group getGroup() { return cGroup; }
		
		//{Calc::TypeDefinitionResult}
		public Action getTypeDefinitionResultAction_0() { return cTypeDefinitionResultAction_0; }
		
		//'Ref:'
		public Keyword getRefKeyword_1() { return cRefKeyword_1; }
		
		//reference=[Categories::ATypeDefinition|QualifiedName]
		public Assignment getReferenceAssignment_2() { return cReferenceAssignment_2; }
		
		//[Categories::ATypeDefinition|QualifiedName]
		public CrossReference getReferenceATypeDefinitionCrossReference_2_0() { return cReferenceATypeDefinitionCrossReference_2_0; }
		
		//QualifiedName
		public RuleCall getReferenceATypeDefinitionQualifiedNameParserRuleCall_2_0_1() { return cReferenceATypeDefinitionQualifiedNameParserRuleCall_2_0_1; }
	}
	public class EquationIntermediateResultElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "de.dlr.sc.virsat.model.concept.ConceptLanguage.EquationIntermediateResult");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final Action cEquationIntermediateResultAction_0 = (Action)cGroup.eContents().get(0);
		private final Keyword cCalcKeyword_1 = (Keyword)cGroup.eContents().get(1);
		private final Assignment cNameAssignment_2 = (Assignment)cGroup.eContents().get(2);
		private final RuleCall cNameIDTerminalRuleCall_2_0 = (RuleCall)cNameAssignment_2.eContents().get(0);
		
		//EquationIntermediateResult Calc::IEquationDefinitionResult:
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
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "de.dlr.sc.virsat.model.concept.ConceptLanguage.AdditionAndSubtraction");
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
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "de.dlr.sc.virsat.model.concept.ConceptLanguage.MultiplicationAndDivision");
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
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "de.dlr.sc.virsat.model.concept.ConceptLanguage.PowerFunction");
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
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "de.dlr.sc.virsat.model.concept.ConceptLanguage.Parenthesis");
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
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "de.dlr.sc.virsat.model.concept.ConceptLanguage.AExpression");
		private final Alternatives cAlternatives = (Alternatives)rule.eContents().get(1);
		private final RuleCall cALiteralParserRuleCall_0 = (RuleCall)cAlternatives.eContents().get(0);
		private final RuleCall cReferencedDefinitionInputParserRuleCall_1 = (RuleCall)cAlternatives.eContents().get(1);
		private final RuleCall cParenthesisParserRuleCall_2 = (RuleCall)cAlternatives.eContents().get(2);
		private final RuleCall cFunctionParserRuleCall_3 = (RuleCall)cAlternatives.eContents().get(3);
		private final RuleCall cAAdvancedFunctionParserRuleCall_4 = (RuleCall)cAlternatives.eContents().get(4);
		
		//AExpression Calc::AExpression:
		//	ALiteral | ReferencedDefinitionInput | Parenthesis | Function | AAdvancedFunction;
		@Override public ParserRule getRule() { return rule; }
		
		//ALiteral | ReferencedDefinitionInput | Parenthesis | Function | AAdvancedFunction
		public Alternatives getAlternatives() { return cAlternatives; }
		
		//ALiteral
		public RuleCall getALiteralParserRuleCall_0() { return cALiteralParserRuleCall_0; }
		
		//ReferencedDefinitionInput
		public RuleCall getReferencedDefinitionInputParserRuleCall_1() { return cReferencedDefinitionInputParserRuleCall_1; }
		
		//Parenthesis
		public RuleCall getParenthesisParserRuleCall_2() { return cParenthesisParserRuleCall_2; }
		
		//Function
		public RuleCall getFunctionParserRuleCall_3() { return cFunctionParserRuleCall_3; }
		
		//AAdvancedFunction
		public RuleCall getAAdvancedFunctionParserRuleCall_4() { return cAAdvancedFunctionParserRuleCall_4; }
	}
	public class ReferencedDefinitionInputElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "de.dlr.sc.virsat.model.concept.ConceptLanguage.ReferencedDefinitionInput");
		private final Assignment cReferenceAssignment = (Assignment)rule.eContents().get(1);
		private final CrossReference cReferenceIEquationDefinitionInputCrossReference_0 = (CrossReference)cReferenceAssignment.eContents().get(0);
		private final RuleCall cReferenceIEquationDefinitionInputQualifiedNameParserRuleCall_0_1 = (RuleCall)cReferenceIEquationDefinitionInputCrossReference_0.eContents().get(1);
		
		//ReferencedDefinitionInput Calc::ReferencedDefinitionInput:
		//	reference=[Calc::IEquationDefinitionInput|QualifiedName];
		@Override public ParserRule getRule() { return rule; }
		
		//reference=[Calc::IEquationDefinitionInput|QualifiedName]
		public Assignment getReferenceAssignment() { return cReferenceAssignment; }
		
		//[Calc::IEquationDefinitionInput|QualifiedName]
		public CrossReference getReferenceIEquationDefinitionInputCrossReference_0() { return cReferenceIEquationDefinitionInputCrossReference_0; }
		
		//QualifiedName
		public RuleCall getReferenceIEquationDefinitionInputQualifiedNameParserRuleCall_0_1() { return cReferenceIEquationDefinitionInputQualifiedNameParserRuleCall_0_1; }
	}
	public class ALiteralElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "de.dlr.sc.virsat.model.concept.ConceptLanguage.ALiteral");
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
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "de.dlr.sc.virsat.model.concept.ConceptLanguage.Function");
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
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "de.dlr.sc.virsat.model.concept.ConceptLanguage.AAdvancedFunction");
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
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "de.dlr.sc.virsat.model.concept.ConceptLanguage.AdvancedFunction");
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
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "de.dlr.sc.virsat.model.concept.ConceptLanguage.SetFunction");
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
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "de.dlr.sc.virsat.model.concept.ConceptLanguage.NumberLiteral");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final Action cNumberLiteralAction_0 = (Action)cGroup.eContents().get(0);
		private final Assignment cValueAssignment_1 = (Assignment)cGroup.eContents().get(1);
		private final RuleCall cValueFloatLiteralStringParserRuleCall_1_0 = (RuleCall)cValueAssignment_1.eContents().get(0);
		
		//NumberLiteral Calc::NumberLiteral:
		//	{Calc::NumberLiteral} value=FloatLiteralString;
		@Override public ParserRule getRule() { return rule; }
		
		//{Calc::NumberLiteral} value=FloatLiteralString
		public Group getGroup() { return cGroup; }
		
		//{Calc::NumberLiteral}
		public Action getNumberLiteralAction_0() { return cNumberLiteralAction_0; }
		
		//value=FloatLiteralString
		public Assignment getValueAssignment_1() { return cValueAssignment_1; }
		
		//FloatLiteralString
		public RuleCall getValueFloatLiteralStringParserRuleCall_1_0() { return cValueFloatLiteralStringParserRuleCall_1_0; }
	}
	public class ValuePiElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "de.dlr.sc.virsat.model.concept.ConceptLanguage.ValuePi");
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
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "de.dlr.sc.virsat.model.concept.ConceptLanguage.ValueE");
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
	public class VersionElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "de.dlr.sc.virsat.model.concept.ConceptLanguage.Version");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final RuleCall cINTTerminalRuleCall_0 = (RuleCall)cGroup.eContents().get(0);
		private final Group cGroup_1 = (Group)cGroup.eContents().get(1);
		private final Keyword cFullStopKeyword_1_0 = (Keyword)cGroup_1.eContents().get(0);
		private final RuleCall cINTTerminalRuleCall_1_1 = (RuleCall)cGroup_1.eContents().get(1);
		
		//// ***************************************************************************************
		//// General Expressions
		//// ***************************************************************************************
		//Version:
		//	INT ('.' INT)*;
		@Override public ParserRule getRule() { return rule; }
		
		//INT ('.' INT)*
		public Group getGroup() { return cGroup; }
		
		//INT
		public RuleCall getINTTerminalRuleCall_0() { return cINTTerminalRuleCall_0; }
		
		//('.' INT)*
		public Group getGroup_1() { return cGroup_1; }
		
		//'.'
		public Keyword getFullStopKeyword_1_0() { return cFullStopKeyword_1_0; }
		
		//INT
		public RuleCall getINTTerminalRuleCall_1_1() { return cINTTerminalRuleCall_1_1; }
	}
	public class EIntElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "de.dlr.sc.virsat.model.concept.ConceptLanguage.EInt");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final Keyword cHyphenMinusKeyword_0 = (Keyword)cGroup.eContents().get(0);
		private final RuleCall cINTTerminalRuleCall_1 = (RuleCall)cGroup.eContents().get(1);
		
		//EInt ecore::EInt:
		//	'-'? INT;
		@Override public ParserRule getRule() { return rule; }
		
		//'-'? INT
		public Group getGroup() { return cGroup; }
		
		//'-'?
		public Keyword getHyphenMinusKeyword_0() { return cHyphenMinusKeyword_0; }
		
		//INT
		public RuleCall getINTTerminalRuleCall_1() { return cINTTerminalRuleCall_1; }
	}
	public class IntLiteralStringElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "de.dlr.sc.virsat.model.concept.ConceptLanguage.IntLiteralString");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final Keyword cHyphenMinusKeyword_0 = (Keyword)cGroup.eContents().get(0);
		private final RuleCall cINTTerminalRuleCall_1 = (RuleCall)cGroup.eContents().get(1);
		
		//IntLiteralString:
		//	'-'? INT?;
		@Override public ParserRule getRule() { return rule; }
		
		//'-'? INT?
		public Group getGroup() { return cGroup; }
		
		//'-'?
		public Keyword getHyphenMinusKeyword_0() { return cHyphenMinusKeyword_0; }
		
		//INT?
		public RuleCall getINTTerminalRuleCall_1() { return cINTTerminalRuleCall_1; }
	}
	public class FloatLiteralStringElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "de.dlr.sc.virsat.model.concept.ConceptLanguage.FloatLiteralString");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final Keyword cHyphenMinusKeyword_0 = (Keyword)cGroup.eContents().get(0);
		private final RuleCall cINTTerminalRuleCall_1 = (RuleCall)cGroup.eContents().get(1);
		private final Group cGroup_2 = (Group)cGroup.eContents().get(2);
		private final Keyword cFullStopKeyword_2_0 = (Keyword)cGroup_2.eContents().get(0);
		private final RuleCall cINTTerminalRuleCall_2_1 = (RuleCall)cGroup_2.eContents().get(1);
		
		//FloatLiteralString:
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
	public class BooleanLiteralStringElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "de.dlr.sc.virsat.model.concept.ConceptLanguage.BooleanLiteralString");
		private final Alternatives cAlternatives = (Alternatives)rule.eContents().get(1);
		private final Keyword cTrueKeyword_0 = (Keyword)cAlternatives.eContents().get(0);
		private final Keyword cFalseKeyword_1 = (Keyword)cAlternatives.eContents().get(1);
		
		//BooleanLiteralString:
		//	'true' | 'false';
		@Override public ParserRule getRule() { return rule; }
		
		//'true' | 'false'
		public Alternatives getAlternatives() { return cAlternatives; }
		
		//'true'
		public Keyword getTrueKeyword_0() { return cTrueKeyword_0; }
		
		//'false'
		public Keyword getFalseKeyword_1() { return cFalseKeyword_1; }
	}
	public class EStringElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "de.dlr.sc.virsat.model.concept.ConceptLanguage.EString");
		private final RuleCall cSTRINGTerminalRuleCall = (RuleCall)rule.eContents().get(1);
		
		//EString:
		//	STRING;
		@Override public ParserRule getRule() { return rule; }
		
		//STRING
		public RuleCall getSTRINGTerminalRuleCall() { return cSTRINGTerminalRuleCall; }
	}
	public class QualifiedNameWithWildcardElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "de.dlr.sc.virsat.model.concept.ConceptLanguage.QualifiedNameWithWildcard");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final RuleCall cQualifiedNameParserRuleCall_0 = (RuleCall)cGroup.eContents().get(0);
		private final Keyword cFullStopAsteriskKeyword_1 = (Keyword)cGroup.eContents().get(1);
		
		//QualifiedNameWithWildcard:
		//	QualifiedName '.*'?;
		@Override public ParserRule getRule() { return rule; }
		
		//QualifiedName '.*'?
		public Group getGroup() { return cGroup; }
		
		//QualifiedName
		public RuleCall getQualifiedNameParserRuleCall_0() { return cQualifiedNameParserRuleCall_0; }
		
		//'.*'?
		public Keyword getFullStopAsteriskKeyword_1() { return cFullStopAsteriskKeyword_1; }
	}
	public class QualifiedNameElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "de.dlr.sc.virsat.model.concept.ConceptLanguage.QualifiedName");
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
	
	public class OperatorPlusElements extends AbstractEnumRuleElementFinder {
		private final EnumRule rule = (EnumRule) GrammarUtil.findRuleForName(getGrammar(), "de.dlr.sc.virsat.model.concept.ConceptLanguage.OperatorPlus");
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
	public class OperatorMinusElements extends AbstractEnumRuleElementFinder {
		private final EnumRule rule = (EnumRule) GrammarUtil.findRuleForName(getGrammar(), "de.dlr.sc.virsat.model.concept.ConceptLanguage.OperatorMinus");
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
	public class OperatorMultiplyElements extends AbstractEnumRuleElementFinder {
		private final EnumRule rule = (EnumRule) GrammarUtil.findRuleForName(getGrammar(), "de.dlr.sc.virsat.model.concept.ConceptLanguage.OperatorMultiply");
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
	public class OperatorDivideElements extends AbstractEnumRuleElementFinder {
		private final EnumRule rule = (EnumRule) GrammarUtil.findRuleForName(getGrammar(), "de.dlr.sc.virsat.model.concept.ConceptLanguage.OperatorDivide");
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
	public class OperatorPowerElements extends AbstractEnumRuleElementFinder {
		private final EnumRule rule = (EnumRule) GrammarUtil.findRuleForName(getGrammar(), "de.dlr.sc.virsat.model.concept.ConceptLanguage.OperatorPower");
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
	public class OperatorCosElements extends AbstractEnumRuleElementFinder {
		private final EnumRule rule = (EnumRule) GrammarUtil.findRuleForName(getGrammar(), "de.dlr.sc.virsat.model.concept.ConceptLanguage.OperatorCos");
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
	public class OperatorSinElements extends AbstractEnumRuleElementFinder {
		private final EnumRule rule = (EnumRule) GrammarUtil.findRuleForName(getGrammar(), "de.dlr.sc.virsat.model.concept.ConceptLanguage.OperatorSin");
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
	public class OperatorTanElements extends AbstractEnumRuleElementFinder {
		private final EnumRule rule = (EnumRule) GrammarUtil.findRuleForName(getGrammar(), "de.dlr.sc.virsat.model.concept.ConceptLanguage.OperatorTan");
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
	public class OperatorAtanElements extends AbstractEnumRuleElementFinder {
		private final EnumRule rule = (EnumRule) GrammarUtil.findRuleForName(getGrammar(), "de.dlr.sc.virsat.model.concept.ConceptLanguage.OperatorAtan");
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
	public class OperatorAcosElements extends AbstractEnumRuleElementFinder {
		private final EnumRule rule = (EnumRule) GrammarUtil.findRuleForName(getGrammar(), "de.dlr.sc.virsat.model.concept.ConceptLanguage.OperatorAcos");
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
	public class OperatorAsinElements extends AbstractEnumRuleElementFinder {
		private final EnumRule rule = (EnumRule) GrammarUtil.findRuleForName(getGrammar(), "de.dlr.sc.virsat.model.concept.ConceptLanguage.OperatorAsin");
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
	public class OperatorSqrtElements extends AbstractEnumRuleElementFinder {
		private final EnumRule rule = (EnumRule) GrammarUtil.findRuleForName(getGrammar(), "de.dlr.sc.virsat.model.concept.ConceptLanguage.OperatorSqrt");
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
	public class OperatorLogElements extends AbstractEnumRuleElementFinder {
		private final EnumRule rule = (EnumRule) GrammarUtil.findRuleForName(getGrammar(), "de.dlr.sc.virsat.model.concept.ConceptLanguage.OperatorLog");
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
	public class OperatorLnElements extends AbstractEnumRuleElementFinder {
		private final EnumRule rule = (EnumRule) GrammarUtil.findRuleForName(getGrammar(), "de.dlr.sc.virsat.model.concept.ConceptLanguage.OperatorLn");
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
	public class OperatorExpElements extends AbstractEnumRuleElementFinder {
		private final EnumRule rule = (EnumRule) GrammarUtil.findRuleForName(getGrammar(), "de.dlr.sc.virsat.model.concept.ConceptLanguage.OperatorExp");
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
	public class OperatorLdElements extends AbstractEnumRuleElementFinder {
		private final EnumRule rule = (EnumRule) GrammarUtil.findRuleForName(getGrammar(), "de.dlr.sc.virsat.model.concept.ConceptLanguage.OperatorLd");
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
	
	private final ConceptElements pConcept;
	private final StructuralElementElements pStructuralElement;
	private final GeneralRelationElements pGeneralRelation;
	private final ARelationElements pARelation;
	private final CategoryElements pCategory;
	private final ConceptImportElements pConceptImport;
	private final EcoreImportElements pEcoreImport;
	private final APropertyElements pAProperty;
	private final ArrayModifierElements pArrayModifier;
	private final DynmaicArrayModifierElements pDynmaicArrayModifier;
	private final StaticArrayModifierElements pStaticArrayModifier;
	private final ComposedPropertyElements pComposedProperty;
	private final IntPropertyElements pIntProperty;
	private final FloatPropertyElements pFloatProperty;
	private final StringPropertyElements pStringProperty;
	private final BooleanPropertyElements pBooleanProperty;
	private final EnumPropertyElements pEnumProperty;
	private final EnumValueDefinitionElements pEnumValueDefinition;
	private final ReferencePropertyElements pReferenceProperty;
	private final EReferencePropertyElements pEReferenceProperty;
	private final ResourcePropertyElements pResourceProperty;
	private final EquationDefinitionElements pEquationDefinition;
	private final EquationDefinitionResultElements pEquationDefinitionResult;
	private final TypeDefinitionResultElements pTypeDefinitionResult;
	private final EquationIntermediateResultElements pEquationIntermediateResult;
	private final AdditionAndSubtractionElements pAdditionAndSubtraction;
	private final MultiplicationAndDivisionElements pMultiplicationAndDivision;
	private final PowerFunctionElements pPowerFunction;
	private final ParenthesisElements pParenthesis;
	private final AExpressionElements pAExpression;
	private final ReferencedDefinitionInputElements pReferencedDefinitionInput;
	private final ALiteralElements pALiteral;
	private final FunctionElements pFunction;
	private final AAdvancedFunctionElements pAAdvancedFunction;
	private final AdvancedFunctionElements pAdvancedFunction;
	private final SetFunctionElements pSetFunction;
	private final NumberLiteralElements pNumberLiteral;
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
	private final VersionElements pVersion;
	private final EIntElements pEInt;
	private final IntLiteralStringElements pIntLiteralString;
	private final FloatLiteralStringElements pFloatLiteralString;
	private final BooleanLiteralStringElements pBooleanLiteralString;
	private final EStringElements pEString;
	private final QualifiedNameWithWildcardElements pQualifiedNameWithWildcard;
	private final QualifiedNameElements pQualifiedName;
	
	private final Grammar grammar;
	
	private final TerminalsGrammarAccess gaTerminals;

	@Inject
	public ConceptLanguageGrammarAccess(GrammarProvider grammarProvider,
			TerminalsGrammarAccess gaTerminals) {
		this.grammar = internalFindGrammar(grammarProvider);
		this.gaTerminals = gaTerminals;
		this.pConcept = new ConceptElements();
		this.pStructuralElement = new StructuralElementElements();
		this.pGeneralRelation = new GeneralRelationElements();
		this.pARelation = new ARelationElements();
		this.pCategory = new CategoryElements();
		this.pConceptImport = new ConceptImportElements();
		this.pEcoreImport = new EcoreImportElements();
		this.pAProperty = new APropertyElements();
		this.pArrayModifier = new ArrayModifierElements();
		this.pDynmaicArrayModifier = new DynmaicArrayModifierElements();
		this.pStaticArrayModifier = new StaticArrayModifierElements();
		this.pComposedProperty = new ComposedPropertyElements();
		this.pIntProperty = new IntPropertyElements();
		this.pFloatProperty = new FloatPropertyElements();
		this.pStringProperty = new StringPropertyElements();
		this.pBooleanProperty = new BooleanPropertyElements();
		this.pEnumProperty = new EnumPropertyElements();
		this.pEnumValueDefinition = new EnumValueDefinitionElements();
		this.pReferenceProperty = new ReferencePropertyElements();
		this.pEReferenceProperty = new EReferencePropertyElements();
		this.pResourceProperty = new ResourcePropertyElements();
		this.pEquationDefinition = new EquationDefinitionElements();
		this.pEquationDefinitionResult = new EquationDefinitionResultElements();
		this.pTypeDefinitionResult = new TypeDefinitionResultElements();
		this.pEquationIntermediateResult = new EquationIntermediateResultElements();
		this.pAdditionAndSubtraction = new AdditionAndSubtractionElements();
		this.pMultiplicationAndDivision = new MultiplicationAndDivisionElements();
		this.pPowerFunction = new PowerFunctionElements();
		this.pParenthesis = new ParenthesisElements();
		this.pAExpression = new AExpressionElements();
		this.pReferencedDefinitionInput = new ReferencedDefinitionInputElements();
		this.pALiteral = new ALiteralElements();
		this.pFunction = new FunctionElements();
		this.pAAdvancedFunction = new AAdvancedFunctionElements();
		this.pAdvancedFunction = new AdvancedFunctionElements();
		this.pSetFunction = new SetFunctionElements();
		this.pNumberLiteral = new NumberLiteralElements();
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
		this.pVersion = new VersionElements();
		this.pEInt = new EIntElements();
		this.pIntLiteralString = new IntLiteralStringElements();
		this.pFloatLiteralString = new FloatLiteralStringElements();
		this.pBooleanLiteralString = new BooleanLiteralStringElements();
		this.pEString = new EStringElements();
		this.pQualifiedNameWithWildcard = new QualifiedNameWithWildcardElements();
		this.pQualifiedName = new QualifiedNameElements();
	}
	
	protected Grammar internalFindGrammar(GrammarProvider grammarProvider) {
		Grammar grammar = grammarProvider.getGrammar(this);
		while (grammar != null) {
			if ("de.dlr.sc.virsat.model.concept.ConceptLanguage".equals(grammar.getName())) {
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

	
	//Concept Concepts::Concept:
	//	{Concepts::Concept}
	//	'Concept' name=QualifiedName (('displayname' displayName=EString)? & ('version' version=Version)? & beta?='beta'? &
	//	('description' description=EString)? & DMF?='hasDMF'?)
	//	'{' (imports+=ConceptImport* & ecoreImports+=EcoreImport*) structuralElements+=StructuralElement*
	//	relations+=ARelation*
	//	categories+=Category*
	//	'}';
	public ConceptElements getConceptAccess() {
		return pConcept;
	}
	
	public ParserRule getConceptRule() {
		return getConceptAccess().getRule();
	}
	
	//// ***************************************************************************************
	//// Structural Elements and Relations
	//// ***************************************************************************************
	//StructuralElement Structural::StructuralElement:
	//	{Structural::StructuralElement}
	//	'StructuralElement' name=ID (('shortname' shortName=ID)? & ('description' description=EString)?)
	//	'{' ((isRootStructuralElement?='IsRootStructuralElement' ';')? & ('Inherits' 'From' ('['
	//	canInheritFrom+=[Structural::StructuralElement|QualifiedName] (","
	//	canInheritFrom+=[Structural::StructuralElement|QualifiedName])* ']' | isCanInheritFromAll?='All') ';')? &
	//	('Applicable' 'For' ('[' applicableFor+=[Structural::StructuralElement|QualifiedName] (","
	//	applicableFor+=[Structural::StructuralElement|QualifiedName])* ']' | isApplicableForAll?='All') ';')? &
	//	('Cardinality' cardinality=INT ';')?)
	//	'}';
	public StructuralElementElements getStructuralElementAccess() {
		return pStructuralElement;
	}
	
	public ParserRule getStructuralElementRule() {
		return getStructuralElementAccess().getRule();
	}
	
	//GeneralRelation Structural::GeneralRelation:
	//	{Structural::GeneralRelation}
	//	'GeneralRelation' name=ID ('description' description=EString)?
	//	'{'
	//	'Referenced' 'Type' referencedType=[Structural::StructuralElement|QualifiedName] ';' ('Applicable' 'For' ('['
	//	applicableFor+=[Structural::StructuralElement|QualifiedName] (","
	//	applicableFor+=[Structural::StructuralElement|QualifiedName])* ']' | isApplicableForAll?='All') ';')? ('Cardinality'
	//	cardinality=INT ';')?
	//	'}';
	public GeneralRelationElements getGeneralRelationAccess() {
		return pGeneralRelation;
	}
	
	public ParserRule getGeneralRelationRule() {
		return getGeneralRelationAccess().getRule();
	}
	
	//ARelation Structural::GeneralRelation:
	//	GeneralRelation;
	public ARelationElements getARelationAccess() {
		return pARelation;
	}
	
	public ParserRule getARelationRule() {
		return getARelationAccess().getRule();
	}
	
	//// ***************************************************************************************
	//// The Properties
	//// ***************************************************************************************
	//Category Categories::Category:
	//	{Categories::Category}
	//	'Category' name=ID (('extends' extendsCategory=[Categories::Category|QualifiedName])? & ('shortname' shortName=ID)? &
	//	('description' description=EString)?)
	//	'{' ((isAbstract?='IsAbstract' ';')? & ('Applicable' 'For' ('['
	//	applicableFor+=[Structural::StructuralElement|QualifiedName] (","
	//	applicableFor+=[Structural::StructuralElement|QualifiedName])* ']' | isApplicableForAll?='All') ';')? &
	//	('Cardinality' cardinality=INT ';')?) properties+=AProperty*
	//	equationDefinitions+=EquationDefinition*
	//	'}';
	public CategoryElements getCategoryAccess() {
		return pCategory;
	}
	
	public ParserRule getCategoryRule() {
		return getCategoryAccess().getRule();
	}
	
	//ConceptImport Concepts::ConceptImport:
	//	{Concepts::ConceptImport}
	//	'Import' importedNamespace=QualifiedNameWithWildcard
	//	';';
	public ConceptImportElements getConceptImportAccess() {
		return pConceptImport;
	}
	
	public ParserRule getConceptImportRule() {
		return getConceptImportAccess().getRule();
	}
	
	//EcoreImport Concepts::EcoreImport:
	//	{Concepts::EcoreImport}
	//	'EImport' importedNsURI=STRING ('genModel' importedGenModel=STRING)?
	//	';';
	public EcoreImportElements getEcoreImportAccess() {
		return pEcoreImport;
	}
	
	public ParserRule getEcoreImportRule() {
		return getEcoreImportAccess().getRule();
	}
	
	//// ***************************************************************************************
	//// The Properties
	//// ***************************************************************************************
	//AProperty PropertyDefinitions::AProperty:
	//	ComposedProperty | IntProperty | FloatProperty | StringProperty | BooleanProperty | EnumProperty | ReferenceProperty
	//	| EReferenceProperty | ResourceProperty;
	public APropertyElements getAPropertyAccess() {
		return pAProperty;
	}
	
	public ParserRule getAPropertyRule() {
		return getAPropertyAccess().getRule();
	}
	
	//ArrayModifier PropertyDefinitions::IArrayModifier:
	//	DynmaicArrayModifier | StaticArrayModifier;
	public ArrayModifierElements getArrayModifierAccess() {
		return pArrayModifier;
	}
	
	public ParserRule getArrayModifierRule() {
		return getArrayModifierAccess().getRule();
	}
	
	//DynmaicArrayModifier PropertyDefinitions::DynamicArrayModifier:
	//	{PropertyDefinitions::DynamicArrayModifier}
	//	'[' ']';
	public DynmaicArrayModifierElements getDynmaicArrayModifierAccess() {
		return pDynmaicArrayModifier;
	}
	
	public ParserRule getDynmaicArrayModifierRule() {
		return getDynmaicArrayModifierAccess().getRule();
	}
	
	//StaticArrayModifier PropertyDefinitions::StaticArrayModifier:
	//	'[' arraySize=INT ']';
	public StaticArrayModifierElements getStaticArrayModifierAccess() {
		return pStaticArrayModifier;
	}
	
	public ParserRule getStaticArrayModifierRule() {
		return getStaticArrayModifierAccess().getRule();
	}
	
	//ComposedProperty PropertyDefinitions::ComposedProperty:
	//	{PropertyDefinitions::ComposedProperty}
	//	'Type' name=ID arrayModifier=ArrayModifier?
	//	'of' 'Category' type=[Categories::Category|QualifiedName] (('description' description=EString)? & ('quantityKind'
	//	quantityKindName=EString)? & ('unit' unitName=EString)?)
	//	';';
	public ComposedPropertyElements getComposedPropertyAccess() {
		return pComposedProperty;
	}
	
	public ParserRule getComposedPropertyRule() {
		return getComposedPropertyAccess().getRule();
	}
	
	//IntProperty PropertyDefinitions::IntProperty:
	//	{PropertyDefinitions::IntProperty}
	//	'IntProperty' name=ID arrayModifier=ArrayModifier? (('description' description=EString)? & ('default'
	//	defaultValue=IntLiteralString)? & ('quantityKind' quantityKindName=EString)? & ('unit' unitName=EString)?)
	//	';';
	public IntPropertyElements getIntPropertyAccess() {
		return pIntProperty;
	}
	
	public ParserRule getIntPropertyRule() {
		return getIntPropertyAccess().getRule();
	}
	
	//FloatProperty PropertyDefinitions::FloatProperty:
	//	{PropertyDefinitions::FloatProperty}
	//	'FloatProperty' name=ID arrayModifier=ArrayModifier? (('description' description=EString)? & ('default'
	//	defaultValue=FloatLiteralString)? & ('quantityKind' quantityKindName=EString)? & ('unit' unitName=EString)?)
	//	';';
	public FloatPropertyElements getFloatPropertyAccess() {
		return pFloatProperty;
	}
	
	public ParserRule getFloatPropertyRule() {
		return getFloatPropertyAccess().getRule();
	}
	
	//StringProperty PropertyDefinitions::StringProperty:
	//	{PropertyDefinitions::StringProperty}
	//	'StringProperty' name=ID arrayModifier=ArrayModifier? (('description' description=EString)? & ('default'
	//	defaultValue=EString)?)
	//	';';
	public StringPropertyElements getStringPropertyAccess() {
		return pStringProperty;
	}
	
	public ParserRule getStringPropertyRule() {
		return getStringPropertyAccess().getRule();
	}
	
	//BooleanProperty PropertyDefinitions::BooleanProperty:
	//	'BooleanProperty' name=ID arrayModifier=ArrayModifier? (('description' description=EString)? & ('default'
	//	defaultValue=BooleanLiteralString)?)
	//	';';
	public BooleanPropertyElements getBooleanPropertyAccess() {
		return pBooleanProperty;
	}
	
	public ParserRule getBooleanPropertyRule() {
		return getBooleanPropertyAccess().getRule();
	}
	
	//EnumProperty PropertyDefinitions::EnumProperty:
	//	'EnumProperty' name=ID arrayModifier=ArrayModifier? (('description' description=EString)? & ('quantityKind'
	//	quantityKindName=EString)? & ('unit' unitName=EString)? & 'values' '[' values+=EnumValueDefinition (','
	//	values+=EnumValueDefinition)* ']' & ('default' defaultValue=[PropertyDefinitions::EnumValueDefinition])?)
	//	';';
	public EnumPropertyElements getEnumPropertyAccess() {
		return pEnumProperty;
	}
	
	public ParserRule getEnumPropertyRule() {
		return getEnumPropertyAccess().getRule();
	}
	
	//EnumValueDefinition PropertyDefinitions::EnumValueDefinition:
	//	name=ID '=' value=(EString | FloatLiteralString);
	public EnumValueDefinitionElements getEnumValueDefinitionAccess() {
		return pEnumValueDefinition;
	}
	
	public ParserRule getEnumValueDefinitionRule() {
		return getEnumValueDefinitionAccess().getRule();
	}
	
	//ReferenceProperty PropertyDefinitions::ReferenceProperty:
	//	'Reference' name=ID arrayModifier=ArrayModifier?
	//	'of' 'Type' referenceType=[Categories::ATypeDefinition|QualifiedName] ('description' description=EString)?
	//	';';
	public ReferencePropertyElements getReferencePropertyAccess() {
		return pReferenceProperty;
	}
	
	public ParserRule getReferencePropertyRule() {
		return getReferencePropertyAccess().getRule();
	}
	
	//EReferenceProperty PropertyDefinitions::EReferenceProperty:
	//	'EReference' name=ID arrayModifier=ArrayModifier?
	//	'of' 'Type' referenceType=[ecore::EClass|QualifiedName] ('description' description=EString)?
	//	';';
	public EReferencePropertyElements getEReferencePropertyAccess() {
		return pEReferenceProperty;
	}
	
	public ParserRule getEReferencePropertyRule() {
		return getEReferencePropertyAccess().getRule();
	}
	
	//ResourceProperty PropertyDefinitions::ResourceProperty:
	//	'Resource' name=ID arrayModifier=ArrayModifier? ('description' description=EString)?
	//	';';
	public ResourcePropertyElements getResourcePropertyAccess() {
		return pResourceProperty;
	}
	
	public ParserRule getResourcePropertyRule() {
		return getResourcePropertyAccess().getRule();
	}
	
	//// ***************************************************************************************
	//// Calculation
	//// ***************************************************************************************
	//EquationDefinition Calc::EquationDefinition:
	//	result=EquationDefinitionResult '=' expression=AdditionAndSubtraction ';';
	public EquationDefinitionElements getEquationDefinitionAccess() {
		return pEquationDefinition;
	}
	
	public ParserRule getEquationDefinitionRule() {
		return getEquationDefinitionAccess().getRule();
	}
	
	//EquationDefinitionResult Calc::IEquationDefinitionResult:
	//	TypeDefinitionResult | EquationIntermediateResult;
	public EquationDefinitionResultElements getEquationDefinitionResultAccess() {
		return pEquationDefinitionResult;
	}
	
	public ParserRule getEquationDefinitionResultRule() {
		return getEquationDefinitionResultAccess().getRule();
	}
	
	//TypeDefinitionResult Calc::IEquationDefinitionResult:
	//	{Calc::TypeDefinitionResult}
	//	'Ref:' reference=[Categories::ATypeDefinition|QualifiedName];
	public TypeDefinitionResultElements getTypeDefinitionResultAccess() {
		return pTypeDefinitionResult;
	}
	
	public ParserRule getTypeDefinitionResultRule() {
		return getTypeDefinitionResultAccess().getRule();
	}
	
	//EquationIntermediateResult Calc::IEquationDefinitionResult:
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
	//	ALiteral | ReferencedDefinitionInput | Parenthesis | Function | AAdvancedFunction;
	public AExpressionElements getAExpressionAccess() {
		return pAExpression;
	}
	
	public ParserRule getAExpressionRule() {
		return getAExpressionAccess().getRule();
	}
	
	//ReferencedDefinitionInput Calc::ReferencedDefinitionInput:
	//	reference=[Calc::IEquationDefinitionInput|QualifiedName];
	public ReferencedDefinitionInputElements getReferencedDefinitionInputAccess() {
		return pReferencedDefinitionInput;
	}
	
	public ParserRule getReferencedDefinitionInputRule() {
		return getReferencedDefinitionInputAccess().getRule();
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
	//	{Calc::NumberLiteral} value=FloatLiteralString;
	public NumberLiteralElements getNumberLiteralAccess() {
		return pNumberLiteral;
	}
	
	public ParserRule getNumberLiteralRule() {
		return getNumberLiteralAccess().getRule();
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
	
	//// ***************************************************************************************
	//// General Expressions
	//// ***************************************************************************************
	//Version:
	//	INT ('.' INT)*;
	public VersionElements getVersionAccess() {
		return pVersion;
	}
	
	public ParserRule getVersionRule() {
		return getVersionAccess().getRule();
	}
	
	//EInt ecore::EInt:
	//	'-'? INT;
	public EIntElements getEIntAccess() {
		return pEInt;
	}
	
	public ParserRule getEIntRule() {
		return getEIntAccess().getRule();
	}
	
	//IntLiteralString:
	//	'-'? INT?;
	public IntLiteralStringElements getIntLiteralStringAccess() {
		return pIntLiteralString;
	}
	
	public ParserRule getIntLiteralStringRule() {
		return getIntLiteralStringAccess().getRule();
	}
	
	//FloatLiteralString:
	//	'-'? INT ('.' INT)?;
	public FloatLiteralStringElements getFloatLiteralStringAccess() {
		return pFloatLiteralString;
	}
	
	public ParserRule getFloatLiteralStringRule() {
		return getFloatLiteralStringAccess().getRule();
	}
	
	//BooleanLiteralString:
	//	'true' | 'false';
	public BooleanLiteralStringElements getBooleanLiteralStringAccess() {
		return pBooleanLiteralString;
	}
	
	public ParserRule getBooleanLiteralStringRule() {
		return getBooleanLiteralStringAccess().getRule();
	}
	
	//EString:
	//	STRING;
	public EStringElements getEStringAccess() {
		return pEString;
	}
	
	public ParserRule getEStringRule() {
		return getEStringAccess().getRule();
	}
	
	//QualifiedNameWithWildcard:
	//	QualifiedName '.*'?;
	public QualifiedNameWithWildcardElements getQualifiedNameWithWildcardAccess() {
		return pQualifiedNameWithWildcard;
	}
	
	public ParserRule getQualifiedNameWithWildcardRule() {
		return getQualifiedNameWithWildcardAccess().getRule();
	}
	
	//QualifiedName:
	//	ID ('.' ID)*;
	public QualifiedNameElements getQualifiedNameAccess() {
		return pQualifiedName;
	}
	
	public ParserRule getQualifiedNameRule() {
		return getQualifiedNameAccess().getRule();
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
	//	'"' ('\\' . | !('\\' | '"'))* '"' | "'" ('\\' . | !('\\' | "'"))* "'";
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
