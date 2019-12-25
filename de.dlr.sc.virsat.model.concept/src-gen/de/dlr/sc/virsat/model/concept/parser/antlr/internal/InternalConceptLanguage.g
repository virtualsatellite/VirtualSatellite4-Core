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
	superClass=AbstractInternalAntlrParser;
}

@lexer::header {
package de.dlr.sc.virsat.model.concept.parser.antlr.internal;

// Hack: Use our own Lexer superclass by means of import. 
// Currently there is no other way to specify the superclass for the lexer.
import org.eclipse.xtext.parser.antlr.Lexer;
}

@parser::header {
package de.dlr.sc.virsat.model.concept.parser.antlr.internal;

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
import de.dlr.sc.virsat.model.concept.services.ConceptLanguageGrammarAccess;

}

@parser::members {

 	private ConceptLanguageGrammarAccess grammarAccess;

    public InternalConceptLanguageParser(TokenStream input, ConceptLanguageGrammarAccess grammarAccess) {
        this(input);
        this.grammarAccess = grammarAccess;
        registerRules(grammarAccess.getGrammar());
    }

    @Override
    protected String getFirstRuleName() {
    	return "Concept";
   	}

   	@Override
   	protected ConceptLanguageGrammarAccess getGrammarAccess() {
   		return grammarAccess;
   	}

}

@rulecatch {
    catch (RecognitionException re) {
        recover(input,re);
        appendSkippedTokens();
    }
}

// Entry rule entryRuleConcept
entryRuleConcept returns [EObject current=null]:
	{ newCompositeNode(grammarAccess.getConceptRule()); }
	iv_ruleConcept=ruleConcept
	{ $current=$iv_ruleConcept.current; }
	EOF;

// Rule Concept
ruleConcept returns [EObject current=null]
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
					grammarAccess.getConceptAccess().getConceptAction_0(),
					$current);
			}
		)
		otherlv_1='Concept'
		{
			newLeafNode(otherlv_1, grammarAccess.getConceptAccess().getConceptKeyword_1());
		}
		(
			(
				{
					newCompositeNode(grammarAccess.getConceptAccess().getNameQualifiedNameParserRuleCall_2_0());
				}
				lv_name_2_0=ruleQualifiedName
				{
					if ($current==null) {
						$current = createModelElementForParent(grammarAccess.getConceptRule());
					}
					set(
						$current,
						"name",
						lv_name_2_0,
						"de.dlr.sc.virsat.model.concept.ConceptLanguage.QualifiedName");
					afterParserOrEnumRuleCall();
				}
			)
		)
		(
			(
				{ 
				  getUnorderedGroupHelper().enter(grammarAccess.getConceptAccess().getUnorderedGroup_3());
				}
				(
					(
			(
				{getUnorderedGroupHelper().canSelect(grammarAccess.getConceptAccess().getUnorderedGroup_3(), 0)}?=>(
					{
						getUnorderedGroupHelper().select(grammarAccess.getConceptAccess().getUnorderedGroup_3(), 0);
					}
								({true}?=>(otherlv_4='displayname'
								{
									newLeafNode(otherlv_4, grammarAccess.getConceptAccess().getDisplaynameKeyword_3_0_0());
								}
								(
									(
										{
											newCompositeNode(grammarAccess.getConceptAccess().getDisplayNameEStringParserRuleCall_3_0_1_0());
										}
										lv_displayName_5_0=ruleEString
										{
											if ($current==null) {
												$current = createModelElementForParent(grammarAccess.getConceptRule());
											}
											set(
												$current,
												"displayName",
												lv_displayName_5_0,
												"de.dlr.sc.virsat.model.concept.ConceptLanguage.EString");
											afterParserOrEnumRuleCall();
										}
									)
								)
								))
					{ 
						getUnorderedGroupHelper().returnFromSelection(grammarAccess.getConceptAccess().getUnorderedGroup_3());
					}
				)
			)|
			(
				{getUnorderedGroupHelper().canSelect(grammarAccess.getConceptAccess().getUnorderedGroup_3(), 1)}?=>(
					{
						getUnorderedGroupHelper().select(grammarAccess.getConceptAccess().getUnorderedGroup_3(), 1);
					}
								({true}?=>(otherlv_6='version'
								{
									newLeafNode(otherlv_6, grammarAccess.getConceptAccess().getVersionKeyword_3_1_0());
								}
								(
									(
										{
											newCompositeNode(grammarAccess.getConceptAccess().getVersionVersionParserRuleCall_3_1_1_0());
										}
										lv_version_7_0=ruleVersion
										{
											if ($current==null) {
												$current = createModelElementForParent(grammarAccess.getConceptRule());
											}
											set(
												$current,
												"version",
												lv_version_7_0,
												"de.dlr.sc.virsat.model.concept.ConceptLanguage.Version");
											afterParserOrEnumRuleCall();
										}
									)
								)
								))
					{ 
						getUnorderedGroupHelper().returnFromSelection(grammarAccess.getConceptAccess().getUnorderedGroup_3());
					}
				)
			)|
			(
				{getUnorderedGroupHelper().canSelect(grammarAccess.getConceptAccess().getUnorderedGroup_3(), 2)}?=>(
					{
						getUnorderedGroupHelper().select(grammarAccess.getConceptAccess().getUnorderedGroup_3(), 2);
					}
								({true}?=>((
									lv_beta_8_0='beta'
									{
										newLeafNode(lv_beta_8_0, grammarAccess.getConceptAccess().getBetaBetaKeyword_3_2_0());
									}
									{
										if ($current==null) {
											$current = createModelElement(grammarAccess.getConceptRule());
										}
										setWithLastConsumed($current, "beta", true, "beta");
									}
								)
								))
					{ 
						getUnorderedGroupHelper().returnFromSelection(grammarAccess.getConceptAccess().getUnorderedGroup_3());
					}
				)
			)|
			(
				{getUnorderedGroupHelper().canSelect(grammarAccess.getConceptAccess().getUnorderedGroup_3(), 3)}?=>(
					{
						getUnorderedGroupHelper().select(grammarAccess.getConceptAccess().getUnorderedGroup_3(), 3);
					}
								({true}?=>(otherlv_9='description'
								{
									newLeafNode(otherlv_9, grammarAccess.getConceptAccess().getDescriptionKeyword_3_3_0());
								}
								(
									(
										{
											newCompositeNode(grammarAccess.getConceptAccess().getDescriptionEStringParserRuleCall_3_3_1_0());
										}
										lv_description_10_0=ruleEString
										{
											if ($current==null) {
												$current = createModelElementForParent(grammarAccess.getConceptRule());
											}
											set(
												$current,
												"description",
												lv_description_10_0,
												"de.dlr.sc.virsat.model.concept.ConceptLanguage.EString");
											afterParserOrEnumRuleCall();
										}
									)
								)
								))
					{ 
						getUnorderedGroupHelper().returnFromSelection(grammarAccess.getConceptAccess().getUnorderedGroup_3());
					}
				)
			)|
			(
				{getUnorderedGroupHelper().canSelect(grammarAccess.getConceptAccess().getUnorderedGroup_3(), 4)}?=>(
					{
						getUnorderedGroupHelper().select(grammarAccess.getConceptAccess().getUnorderedGroup_3(), 4);
					}
								({true}?=>((
									lv_DMF_11_0='hasDMF'
									{
										newLeafNode(lv_DMF_11_0, grammarAccess.getConceptAccess().getDMFHasDMFKeyword_3_4_0());
									}
									{
										if ($current==null) {
											$current = createModelElement(grammarAccess.getConceptRule());
										}
										setWithLastConsumed($current, "DMF", true, "hasDMF");
									}
								)
								))
					{ 
						getUnorderedGroupHelper().returnFromSelection(grammarAccess.getConceptAccess().getUnorderedGroup_3());
					}
				)
			)
					)*
				)
			)
				{ 
				  getUnorderedGroupHelper().leave(grammarAccess.getConceptAccess().getUnorderedGroup_3());
				}
		)
		otherlv_12='{'
		{
			newLeafNode(otherlv_12, grammarAccess.getConceptAccess().getLeftCurlyBracketKeyword_4());
		}
		(
			(
				{ 
				  getUnorderedGroupHelper().enter(grammarAccess.getConceptAccess().getUnorderedGroup_5());
				}
				(
					(
			(
				{getUnorderedGroupHelper().canSelect(grammarAccess.getConceptAccess().getUnorderedGroup_5(), 0)}?=>(
					{
						getUnorderedGroupHelper().select(grammarAccess.getConceptAccess().getUnorderedGroup_5(), 0);
					}
								({true}?=>((
									{
										newCompositeNode(grammarAccess.getConceptAccess().getImportsConceptImportParserRuleCall_5_0_0());
									}
									lv_imports_14_0=ruleConceptImport
									{
										if ($current==null) {
											$current = createModelElementForParent(grammarAccess.getConceptRule());
										}
										add(
											$current,
											"imports",
											lv_imports_14_0,
											"de.dlr.sc.virsat.model.concept.ConceptLanguage.ConceptImport");
										afterParserOrEnumRuleCall();
									}
								)
								))+
					{ 
						getUnorderedGroupHelper().returnFromSelection(grammarAccess.getConceptAccess().getUnorderedGroup_5());
					}
				)
			)|
			(
				{getUnorderedGroupHelper().canSelect(grammarAccess.getConceptAccess().getUnorderedGroup_5(), 1)}?=>(
					{
						getUnorderedGroupHelper().select(grammarAccess.getConceptAccess().getUnorderedGroup_5(), 1);
					}
								({true}?=>((
									{
										newCompositeNode(grammarAccess.getConceptAccess().getEcoreImportsEcoreImportParserRuleCall_5_1_0());
									}
									lv_ecoreImports_15_0=ruleEcoreImport
									{
										if ($current==null) {
											$current = createModelElementForParent(grammarAccess.getConceptRule());
										}
										add(
											$current,
											"ecoreImports",
											lv_ecoreImports_15_0,
											"de.dlr.sc.virsat.model.concept.ConceptLanguage.EcoreImport");
										afterParserOrEnumRuleCall();
									}
								)
								))+
					{ 
						getUnorderedGroupHelper().returnFromSelection(grammarAccess.getConceptAccess().getUnorderedGroup_5());
					}
				)
			)
					)*
				)
			)
				{ 
				  getUnorderedGroupHelper().leave(grammarAccess.getConceptAccess().getUnorderedGroup_5());
				}
		)
		(
			(
				{
					newCompositeNode(grammarAccess.getConceptAccess().getStructuralElementsStructuralElementParserRuleCall_6_0());
				}
				lv_structuralElements_16_0=ruleStructuralElement
				{
					if ($current==null) {
						$current = createModelElementForParent(grammarAccess.getConceptRule());
					}
					add(
						$current,
						"structuralElements",
						lv_structuralElements_16_0,
						"de.dlr.sc.virsat.model.concept.ConceptLanguage.StructuralElement");
					afterParserOrEnumRuleCall();
				}
			)
		)*
		(
			(
				{
					newCompositeNode(grammarAccess.getConceptAccess().getRelationsARelationParserRuleCall_7_0());
				}
				lv_relations_17_0=ruleARelation
				{
					if ($current==null) {
						$current = createModelElementForParent(grammarAccess.getConceptRule());
					}
					add(
						$current,
						"relations",
						lv_relations_17_0,
						"de.dlr.sc.virsat.model.concept.ConceptLanguage.ARelation");
					afterParserOrEnumRuleCall();
				}
			)
		)*
		(
			(
				{
					newCompositeNode(grammarAccess.getConceptAccess().getCategoriesCategoryParserRuleCall_8_0());
				}
				lv_categories_18_0=ruleCategory
				{
					if ($current==null) {
						$current = createModelElementForParent(grammarAccess.getConceptRule());
					}
					add(
						$current,
						"categories",
						lv_categories_18_0,
						"de.dlr.sc.virsat.model.concept.ConceptLanguage.Category");
					afterParserOrEnumRuleCall();
				}
			)
		)*
		otherlv_19='}'
		{
			newLeafNode(otherlv_19, grammarAccess.getConceptAccess().getRightCurlyBracketKeyword_9());
		}
	)
;

// Entry rule entryRuleStructuralElement
entryRuleStructuralElement returns [EObject current=null]:
	{ newCompositeNode(grammarAccess.getStructuralElementRule()); }
	iv_ruleStructuralElement=ruleStructuralElement
	{ $current=$iv_ruleStructuralElement.current; }
	EOF;

// Rule StructuralElement
ruleStructuralElement returns [EObject current=null]
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
					grammarAccess.getStructuralElementAccess().getStructuralElementAction_0(),
					$current);
			}
		)
		otherlv_1='StructuralElement'
		{
			newLeafNode(otherlv_1, grammarAccess.getStructuralElementAccess().getStructuralElementKeyword_1());
		}
		(
			(
				lv_name_2_0=RULE_ID
				{
					newLeafNode(lv_name_2_0, grammarAccess.getStructuralElementAccess().getNameIDTerminalRuleCall_2_0());
				}
				{
					if ($current==null) {
						$current = createModelElement(grammarAccess.getStructuralElementRule());
					}
					setWithLastConsumed(
						$current,
						"name",
						lv_name_2_0,
						"org.eclipse.xtext.common.Terminals.ID");
				}
			)
		)
		(
			(
				{ 
				  getUnorderedGroupHelper().enter(grammarAccess.getStructuralElementAccess().getUnorderedGroup_3());
				}
				(
					(
			(
				{getUnorderedGroupHelper().canSelect(grammarAccess.getStructuralElementAccess().getUnorderedGroup_3(), 0)}?=>(
					{
						getUnorderedGroupHelper().select(grammarAccess.getStructuralElementAccess().getUnorderedGroup_3(), 0);
					}
								({true}?=>(otherlv_4='shortname'
								{
									newLeafNode(otherlv_4, grammarAccess.getStructuralElementAccess().getShortnameKeyword_3_0_0());
								}
								(
									(
										lv_shortName_5_0=RULE_ID
										{
											newLeafNode(lv_shortName_5_0, grammarAccess.getStructuralElementAccess().getShortNameIDTerminalRuleCall_3_0_1_0());
										}
										{
											if ($current==null) {
												$current = createModelElement(grammarAccess.getStructuralElementRule());
											}
											setWithLastConsumed(
												$current,
												"shortName",
												lv_shortName_5_0,
												"org.eclipse.xtext.common.Terminals.ID");
										}
									)
								)
								))
					{ 
						getUnorderedGroupHelper().returnFromSelection(grammarAccess.getStructuralElementAccess().getUnorderedGroup_3());
					}
				)
			)|
			(
				{getUnorderedGroupHelper().canSelect(grammarAccess.getStructuralElementAccess().getUnorderedGroup_3(), 1)}?=>(
					{
						getUnorderedGroupHelper().select(grammarAccess.getStructuralElementAccess().getUnorderedGroup_3(), 1);
					}
								({true}?=>(otherlv_6='description'
								{
									newLeafNode(otherlv_6, grammarAccess.getStructuralElementAccess().getDescriptionKeyword_3_1_0());
								}
								(
									(
										{
											newCompositeNode(grammarAccess.getStructuralElementAccess().getDescriptionEStringParserRuleCall_3_1_1_0());
										}
										lv_description_7_0=ruleEString
										{
											if ($current==null) {
												$current = createModelElementForParent(grammarAccess.getStructuralElementRule());
											}
											set(
												$current,
												"description",
												lv_description_7_0,
												"de.dlr.sc.virsat.model.concept.ConceptLanguage.EString");
											afterParserOrEnumRuleCall();
										}
									)
								)
								))
					{ 
						getUnorderedGroupHelper().returnFromSelection(grammarAccess.getStructuralElementAccess().getUnorderedGroup_3());
					}
				)
			)
					)*
				)
			)
				{ 
				  getUnorderedGroupHelper().leave(grammarAccess.getStructuralElementAccess().getUnorderedGroup_3());
				}
		)
		otherlv_8='{'
		{
			newLeafNode(otherlv_8, grammarAccess.getStructuralElementAccess().getLeftCurlyBracketKeyword_4());
		}
		(
			(
				{ 
				  getUnorderedGroupHelper().enter(grammarAccess.getStructuralElementAccess().getUnorderedGroup_5());
				}
				(
					(
			(
				{getUnorderedGroupHelper().canSelect(grammarAccess.getStructuralElementAccess().getUnorderedGroup_5(), 0)}?=>(
					{
						getUnorderedGroupHelper().select(grammarAccess.getStructuralElementAccess().getUnorderedGroup_5(), 0);
					}
								({true}?=>((
									(
										lv_isRootStructuralElement_10_0='IsRootStructuralElement'
										{
											newLeafNode(lv_isRootStructuralElement_10_0, grammarAccess.getStructuralElementAccess().getIsRootStructuralElementIsRootStructuralElementKeyword_5_0_0_0());
										}
										{
											if ($current==null) {
												$current = createModelElement(grammarAccess.getStructuralElementRule());
											}
											setWithLastConsumed($current, "isRootStructuralElement", true, "IsRootStructuralElement");
										}
									)
								)
								otherlv_11=';'
								{
									newLeafNode(otherlv_11, grammarAccess.getStructuralElementAccess().getSemicolonKeyword_5_0_1());
								}
								))
					{ 
						getUnorderedGroupHelper().returnFromSelection(grammarAccess.getStructuralElementAccess().getUnorderedGroup_5());
					}
				)
			)|
			(
				{getUnorderedGroupHelper().canSelect(grammarAccess.getStructuralElementAccess().getUnorderedGroup_5(), 1)}?=>(
					{
						getUnorderedGroupHelper().select(grammarAccess.getStructuralElementAccess().getUnorderedGroup_5(), 1);
					}
								({true}?=>(otherlv_12='Inherits'
								{
									newLeafNode(otherlv_12, grammarAccess.getStructuralElementAccess().getInheritsKeyword_5_1_0());
								}
								otherlv_13='From'
								{
									newLeafNode(otherlv_13, grammarAccess.getStructuralElementAccess().getFromKeyword_5_1_1());
								}
								(
									(
										otherlv_14='['
										{
											newLeafNode(otherlv_14, grammarAccess.getStructuralElementAccess().getLeftSquareBracketKeyword_5_1_2_0_0());
										}
										(
											(
												{
													if ($current==null) {
														$current = createModelElement(grammarAccess.getStructuralElementRule());
													}
												}
												{
													newCompositeNode(grammarAccess.getStructuralElementAccess().getCanInheritFromStructuralElementCrossReference_5_1_2_0_1_0());
												}
												ruleQualifiedName
												{
													afterParserOrEnumRuleCall();
												}
											)
										)
										(
											otherlv_16=','
											{
												newLeafNode(otherlv_16, grammarAccess.getStructuralElementAccess().getCommaKeyword_5_1_2_0_2_0());
											}
											(
												(
													{
														if ($current==null) {
															$current = createModelElement(grammarAccess.getStructuralElementRule());
														}
													}
													{
														newCompositeNode(grammarAccess.getStructuralElementAccess().getCanInheritFromStructuralElementCrossReference_5_1_2_0_2_1_0());
													}
													ruleQualifiedName
													{
														afterParserOrEnumRuleCall();
													}
												)
											)
										)*
										otherlv_18=']'
										{
											newLeafNode(otherlv_18, grammarAccess.getStructuralElementAccess().getRightSquareBracketKeyword_5_1_2_0_3());
										}
									)
									    |
									(
										(
											lv_isCanInheritFromAll_19_0='All'
											{
												newLeafNode(lv_isCanInheritFromAll_19_0, grammarAccess.getStructuralElementAccess().getIsCanInheritFromAllAllKeyword_5_1_2_1_0());
											}
											{
												if ($current==null) {
													$current = createModelElement(grammarAccess.getStructuralElementRule());
												}
												setWithLastConsumed($current, "isCanInheritFromAll", true, "All");
											}
										)
									)
								)
								otherlv_20=';'
								{
									newLeafNode(otherlv_20, grammarAccess.getStructuralElementAccess().getSemicolonKeyword_5_1_3());
								}
								))
					{ 
						getUnorderedGroupHelper().returnFromSelection(grammarAccess.getStructuralElementAccess().getUnorderedGroup_5());
					}
				)
			)|
			(
				{getUnorderedGroupHelper().canSelect(grammarAccess.getStructuralElementAccess().getUnorderedGroup_5(), 2)}?=>(
					{
						getUnorderedGroupHelper().select(grammarAccess.getStructuralElementAccess().getUnorderedGroup_5(), 2);
					}
								({true}?=>(otherlv_21='Applicable'
								{
									newLeafNode(otherlv_21, grammarAccess.getStructuralElementAccess().getApplicableKeyword_5_2_0());
								}
								otherlv_22='For'
								{
									newLeafNode(otherlv_22, grammarAccess.getStructuralElementAccess().getForKeyword_5_2_1());
								}
								(
									(
										otherlv_23='['
										{
											newLeafNode(otherlv_23, grammarAccess.getStructuralElementAccess().getLeftSquareBracketKeyword_5_2_2_0_0());
										}
										(
											(
												{
													if ($current==null) {
														$current = createModelElement(grammarAccess.getStructuralElementRule());
													}
												}
												{
													newCompositeNode(grammarAccess.getStructuralElementAccess().getApplicableForStructuralElementCrossReference_5_2_2_0_1_0());
												}
												ruleQualifiedName
												{
													afterParserOrEnumRuleCall();
												}
											)
										)
										(
											otherlv_25=','
											{
												newLeafNode(otherlv_25, grammarAccess.getStructuralElementAccess().getCommaKeyword_5_2_2_0_2_0());
											}
											(
												(
													{
														if ($current==null) {
															$current = createModelElement(grammarAccess.getStructuralElementRule());
														}
													}
													{
														newCompositeNode(grammarAccess.getStructuralElementAccess().getApplicableForStructuralElementCrossReference_5_2_2_0_2_1_0());
													}
													ruleQualifiedName
													{
														afterParserOrEnumRuleCall();
													}
												)
											)
										)*
										otherlv_27=']'
										{
											newLeafNode(otherlv_27, grammarAccess.getStructuralElementAccess().getRightSquareBracketKeyword_5_2_2_0_3());
										}
									)
									    |
									(
										(
											lv_isApplicableForAll_28_0='All'
											{
												newLeafNode(lv_isApplicableForAll_28_0, grammarAccess.getStructuralElementAccess().getIsApplicableForAllAllKeyword_5_2_2_1_0());
											}
											{
												if ($current==null) {
													$current = createModelElement(grammarAccess.getStructuralElementRule());
												}
												setWithLastConsumed($current, "isApplicableForAll", true, "All");
											}
										)
									)
								)
								otherlv_29=';'
								{
									newLeafNode(otherlv_29, grammarAccess.getStructuralElementAccess().getSemicolonKeyword_5_2_3());
								}
								))
					{ 
						getUnorderedGroupHelper().returnFromSelection(grammarAccess.getStructuralElementAccess().getUnorderedGroup_5());
					}
				)
			)|
			(
				{getUnorderedGroupHelper().canSelect(grammarAccess.getStructuralElementAccess().getUnorderedGroup_5(), 3)}?=>(
					{
						getUnorderedGroupHelper().select(grammarAccess.getStructuralElementAccess().getUnorderedGroup_5(), 3);
					}
								({true}?=>(otherlv_30='Cardinality'
								{
									newLeafNode(otherlv_30, grammarAccess.getStructuralElementAccess().getCardinalityKeyword_5_3_0());
								}
								(
									(
										lv_cardinality_31_0=RULE_INT
										{
											newLeafNode(lv_cardinality_31_0, grammarAccess.getStructuralElementAccess().getCardinalityINTTerminalRuleCall_5_3_1_0());
										}
										{
											if ($current==null) {
												$current = createModelElement(grammarAccess.getStructuralElementRule());
											}
											setWithLastConsumed(
												$current,
												"cardinality",
												lv_cardinality_31_0,
												"org.eclipse.xtext.common.Terminals.INT");
										}
									)
								)
								otherlv_32=';'
								{
									newLeafNode(otherlv_32, grammarAccess.getStructuralElementAccess().getSemicolonKeyword_5_3_2());
								}
								))
					{ 
						getUnorderedGroupHelper().returnFromSelection(grammarAccess.getStructuralElementAccess().getUnorderedGroup_5());
					}
				)
			)
					)*
				)
			)
				{ 
				  getUnorderedGroupHelper().leave(grammarAccess.getStructuralElementAccess().getUnorderedGroup_5());
				}
		)
		otherlv_33='}'
		{
			newLeafNode(otherlv_33, grammarAccess.getStructuralElementAccess().getRightCurlyBracketKeyword_6());
		}
	)
;

// Entry rule entryRuleGeneralRelation
entryRuleGeneralRelation returns [EObject current=null]:
	{ newCompositeNode(grammarAccess.getGeneralRelationRule()); }
	iv_ruleGeneralRelation=ruleGeneralRelation
	{ $current=$iv_ruleGeneralRelation.current; }
	EOF;

// Rule GeneralRelation
ruleGeneralRelation returns [EObject current=null]
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
					grammarAccess.getGeneralRelationAccess().getGeneralRelationAction_0(),
					$current);
			}
		)
		otherlv_1='GeneralRelation'
		{
			newLeafNode(otherlv_1, grammarAccess.getGeneralRelationAccess().getGeneralRelationKeyword_1());
		}
		(
			(
				lv_name_2_0=RULE_ID
				{
					newLeafNode(lv_name_2_0, grammarAccess.getGeneralRelationAccess().getNameIDTerminalRuleCall_2_0());
				}
				{
					if ($current==null) {
						$current = createModelElement(grammarAccess.getGeneralRelationRule());
					}
					setWithLastConsumed(
						$current,
						"name",
						lv_name_2_0,
						"org.eclipse.xtext.common.Terminals.ID");
				}
			)
		)
		(
			otherlv_3='description'
			{
				newLeafNode(otherlv_3, grammarAccess.getGeneralRelationAccess().getDescriptionKeyword_3_0());
			}
			(
				(
					{
						newCompositeNode(grammarAccess.getGeneralRelationAccess().getDescriptionEStringParserRuleCall_3_1_0());
					}
					lv_description_4_0=ruleEString
					{
						if ($current==null) {
							$current = createModelElementForParent(grammarAccess.getGeneralRelationRule());
						}
						set(
							$current,
							"description",
							lv_description_4_0,
							"de.dlr.sc.virsat.model.concept.ConceptLanguage.EString");
						afterParserOrEnumRuleCall();
					}
				)
			)
		)?
		otherlv_5='{'
		{
			newLeafNode(otherlv_5, grammarAccess.getGeneralRelationAccess().getLeftCurlyBracketKeyword_4());
		}
		otherlv_6='Referenced'
		{
			newLeafNode(otherlv_6, grammarAccess.getGeneralRelationAccess().getReferencedKeyword_5());
		}
		otherlv_7='Type'
		{
			newLeafNode(otherlv_7, grammarAccess.getGeneralRelationAccess().getTypeKeyword_6());
		}
		(
			(
				{
					if ($current==null) {
						$current = createModelElement(grammarAccess.getGeneralRelationRule());
					}
				}
				{
					newCompositeNode(grammarAccess.getGeneralRelationAccess().getReferencedTypeStructuralElementCrossReference_7_0());
				}
				ruleQualifiedName
				{
					afterParserOrEnumRuleCall();
				}
			)
		)
		otherlv_9=';'
		{
			newLeafNode(otherlv_9, grammarAccess.getGeneralRelationAccess().getSemicolonKeyword_8());
		}
		(
			otherlv_10='Applicable'
			{
				newLeafNode(otherlv_10, grammarAccess.getGeneralRelationAccess().getApplicableKeyword_9_0());
			}
			otherlv_11='For'
			{
				newLeafNode(otherlv_11, grammarAccess.getGeneralRelationAccess().getForKeyword_9_1());
			}
			(
				(
					otherlv_12='['
					{
						newLeafNode(otherlv_12, grammarAccess.getGeneralRelationAccess().getLeftSquareBracketKeyword_9_2_0_0());
					}
					(
						(
							{
								if ($current==null) {
									$current = createModelElement(grammarAccess.getGeneralRelationRule());
								}
							}
							{
								newCompositeNode(grammarAccess.getGeneralRelationAccess().getApplicableForStructuralElementCrossReference_9_2_0_1_0());
							}
							ruleQualifiedName
							{
								afterParserOrEnumRuleCall();
							}
						)
					)
					(
						otherlv_14=','
						{
							newLeafNode(otherlv_14, grammarAccess.getGeneralRelationAccess().getCommaKeyword_9_2_0_2_0());
						}
						(
							(
								{
									if ($current==null) {
										$current = createModelElement(grammarAccess.getGeneralRelationRule());
									}
								}
								{
									newCompositeNode(grammarAccess.getGeneralRelationAccess().getApplicableForStructuralElementCrossReference_9_2_0_2_1_0());
								}
								ruleQualifiedName
								{
									afterParserOrEnumRuleCall();
								}
							)
						)
					)*
					otherlv_16=']'
					{
						newLeafNode(otherlv_16, grammarAccess.getGeneralRelationAccess().getRightSquareBracketKeyword_9_2_0_3());
					}
				)
				    |
				(
					(
						lv_isApplicableForAll_17_0='All'
						{
							newLeafNode(lv_isApplicableForAll_17_0, grammarAccess.getGeneralRelationAccess().getIsApplicableForAllAllKeyword_9_2_1_0());
						}
						{
							if ($current==null) {
								$current = createModelElement(grammarAccess.getGeneralRelationRule());
							}
							setWithLastConsumed($current, "isApplicableForAll", true, "All");
						}
					)
				)
			)
			otherlv_18=';'
			{
				newLeafNode(otherlv_18, grammarAccess.getGeneralRelationAccess().getSemicolonKeyword_9_3());
			}
		)?
		(
			otherlv_19='Cardinality'
			{
				newLeafNode(otherlv_19, grammarAccess.getGeneralRelationAccess().getCardinalityKeyword_10_0());
			}
			(
				(
					lv_cardinality_20_0=RULE_INT
					{
						newLeafNode(lv_cardinality_20_0, grammarAccess.getGeneralRelationAccess().getCardinalityINTTerminalRuleCall_10_1_0());
					}
					{
						if ($current==null) {
							$current = createModelElement(grammarAccess.getGeneralRelationRule());
						}
						setWithLastConsumed(
							$current,
							"cardinality",
							lv_cardinality_20_0,
							"org.eclipse.xtext.common.Terminals.INT");
					}
				)
			)
			otherlv_21=';'
			{
				newLeafNode(otherlv_21, grammarAccess.getGeneralRelationAccess().getSemicolonKeyword_10_2());
			}
		)?
		otherlv_22='}'
		{
			newLeafNode(otherlv_22, grammarAccess.getGeneralRelationAccess().getRightCurlyBracketKeyword_11());
		}
	)
;

// Entry rule entryRuleARelation
entryRuleARelation returns [EObject current=null]:
	{ newCompositeNode(grammarAccess.getARelationRule()); }
	iv_ruleARelation=ruleARelation
	{ $current=$iv_ruleARelation.current; }
	EOF;

// Rule ARelation
ruleARelation returns [EObject current=null]
@init {
	enterRule();
}
@after {
	leaveRule();
}:
	{
		newCompositeNode(grammarAccess.getARelationAccess().getGeneralRelationParserRuleCall());
	}
	this_GeneralRelation_0=ruleGeneralRelation
	{
		$current = $this_GeneralRelation_0.current;
		afterParserOrEnumRuleCall();
	}
;

// Entry rule entryRuleCategory
entryRuleCategory returns [EObject current=null]:
	{ newCompositeNode(grammarAccess.getCategoryRule()); }
	iv_ruleCategory=ruleCategory
	{ $current=$iv_ruleCategory.current; }
	EOF;

// Rule Category
ruleCategory returns [EObject current=null]
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
					grammarAccess.getCategoryAccess().getCategoryAction_0(),
					$current);
			}
		)
		otherlv_1='Category'
		{
			newLeafNode(otherlv_1, grammarAccess.getCategoryAccess().getCategoryKeyword_1());
		}
		(
			(
				lv_name_2_0=RULE_ID
				{
					newLeafNode(lv_name_2_0, grammarAccess.getCategoryAccess().getNameIDTerminalRuleCall_2_0());
				}
				{
					if ($current==null) {
						$current = createModelElement(grammarAccess.getCategoryRule());
					}
					setWithLastConsumed(
						$current,
						"name",
						lv_name_2_0,
						"org.eclipse.xtext.common.Terminals.ID");
				}
			)
		)
		(
			(
				{ 
				  getUnorderedGroupHelper().enter(grammarAccess.getCategoryAccess().getUnorderedGroup_3());
				}
				(
					(
			(
				{getUnorderedGroupHelper().canSelect(grammarAccess.getCategoryAccess().getUnorderedGroup_3(), 0)}?=>(
					{
						getUnorderedGroupHelper().select(grammarAccess.getCategoryAccess().getUnorderedGroup_3(), 0);
					}
								({true}?=>(otherlv_4='extends'
								{
									newLeafNode(otherlv_4, grammarAccess.getCategoryAccess().getExtendsKeyword_3_0_0());
								}
								(
									(
										{
											if ($current==null) {
												$current = createModelElement(grammarAccess.getCategoryRule());
											}
										}
										{
											newCompositeNode(grammarAccess.getCategoryAccess().getExtendsCategoryCategoryCrossReference_3_0_1_0());
										}
										ruleQualifiedName
										{
											afterParserOrEnumRuleCall();
										}
									)
								)
								))
					{ 
						getUnorderedGroupHelper().returnFromSelection(grammarAccess.getCategoryAccess().getUnorderedGroup_3());
					}
				)
			)|
			(
				{getUnorderedGroupHelper().canSelect(grammarAccess.getCategoryAccess().getUnorderedGroup_3(), 1)}?=>(
					{
						getUnorderedGroupHelper().select(grammarAccess.getCategoryAccess().getUnorderedGroup_3(), 1);
					}
								({true}?=>(otherlv_6='shortname'
								{
									newLeafNode(otherlv_6, grammarAccess.getCategoryAccess().getShortnameKeyword_3_1_0());
								}
								(
									(
										lv_shortName_7_0=RULE_ID
										{
											newLeafNode(lv_shortName_7_0, grammarAccess.getCategoryAccess().getShortNameIDTerminalRuleCall_3_1_1_0());
										}
										{
											if ($current==null) {
												$current = createModelElement(grammarAccess.getCategoryRule());
											}
											setWithLastConsumed(
												$current,
												"shortName",
												lv_shortName_7_0,
												"org.eclipse.xtext.common.Terminals.ID");
										}
									)
								)
								))
					{ 
						getUnorderedGroupHelper().returnFromSelection(grammarAccess.getCategoryAccess().getUnorderedGroup_3());
					}
				)
			)|
			(
				{getUnorderedGroupHelper().canSelect(grammarAccess.getCategoryAccess().getUnorderedGroup_3(), 2)}?=>(
					{
						getUnorderedGroupHelper().select(grammarAccess.getCategoryAccess().getUnorderedGroup_3(), 2);
					}
								({true}?=>(otherlv_8='description'
								{
									newLeafNode(otherlv_8, grammarAccess.getCategoryAccess().getDescriptionKeyword_3_2_0());
								}
								(
									(
										{
											newCompositeNode(grammarAccess.getCategoryAccess().getDescriptionEStringParserRuleCall_3_2_1_0());
										}
										lv_description_9_0=ruleEString
										{
											if ($current==null) {
												$current = createModelElementForParent(grammarAccess.getCategoryRule());
											}
											set(
												$current,
												"description",
												lv_description_9_0,
												"de.dlr.sc.virsat.model.concept.ConceptLanguage.EString");
											afterParserOrEnumRuleCall();
										}
									)
								)
								))
					{ 
						getUnorderedGroupHelper().returnFromSelection(grammarAccess.getCategoryAccess().getUnorderedGroup_3());
					}
				)
			)
					)*
				)
			)
				{ 
				  getUnorderedGroupHelper().leave(grammarAccess.getCategoryAccess().getUnorderedGroup_3());
				}
		)
		otherlv_10='{'
		{
			newLeafNode(otherlv_10, grammarAccess.getCategoryAccess().getLeftCurlyBracketKeyword_4());
		}
		(
			(
				{ 
				  getUnorderedGroupHelper().enter(grammarAccess.getCategoryAccess().getUnorderedGroup_5());
				}
				(
					(
			(
				{getUnorderedGroupHelper().canSelect(grammarAccess.getCategoryAccess().getUnorderedGroup_5(), 0)}?=>(
					{
						getUnorderedGroupHelper().select(grammarAccess.getCategoryAccess().getUnorderedGroup_5(), 0);
					}
								({true}?=>((
									(
										lv_isAbstract_12_0='IsAbstract'
										{
											newLeafNode(lv_isAbstract_12_0, grammarAccess.getCategoryAccess().getIsAbstractIsAbstractKeyword_5_0_0_0());
										}
										{
											if ($current==null) {
												$current = createModelElement(grammarAccess.getCategoryRule());
											}
											setWithLastConsumed($current, "isAbstract", true, "IsAbstract");
										}
									)
								)
								otherlv_13=';'
								{
									newLeafNode(otherlv_13, grammarAccess.getCategoryAccess().getSemicolonKeyword_5_0_1());
								}
								))
					{ 
						getUnorderedGroupHelper().returnFromSelection(grammarAccess.getCategoryAccess().getUnorderedGroup_5());
					}
				)
			)|
			(
				{getUnorderedGroupHelper().canSelect(grammarAccess.getCategoryAccess().getUnorderedGroup_5(), 1)}?=>(
					{
						getUnorderedGroupHelper().select(grammarAccess.getCategoryAccess().getUnorderedGroup_5(), 1);
					}
								({true}?=>(otherlv_14='Applicable'
								{
									newLeafNode(otherlv_14, grammarAccess.getCategoryAccess().getApplicableKeyword_5_1_0());
								}
								otherlv_15='For'
								{
									newLeafNode(otherlv_15, grammarAccess.getCategoryAccess().getForKeyword_5_1_1());
								}
								(
									(
										otherlv_16='['
										{
											newLeafNode(otherlv_16, grammarAccess.getCategoryAccess().getLeftSquareBracketKeyword_5_1_2_0_0());
										}
										(
											(
												{
													if ($current==null) {
														$current = createModelElement(grammarAccess.getCategoryRule());
													}
												}
												{
													newCompositeNode(grammarAccess.getCategoryAccess().getApplicableForStructuralElementCrossReference_5_1_2_0_1_0());
												}
												ruleQualifiedName
												{
													afterParserOrEnumRuleCall();
												}
											)
										)
										(
											otherlv_18=','
											{
												newLeafNode(otherlv_18, grammarAccess.getCategoryAccess().getCommaKeyword_5_1_2_0_2_0());
											}
											(
												(
													{
														if ($current==null) {
															$current = createModelElement(grammarAccess.getCategoryRule());
														}
													}
													{
														newCompositeNode(grammarAccess.getCategoryAccess().getApplicableForStructuralElementCrossReference_5_1_2_0_2_1_0());
													}
													ruleQualifiedName
													{
														afterParserOrEnumRuleCall();
													}
												)
											)
										)*
										otherlv_20=']'
										{
											newLeafNode(otherlv_20, grammarAccess.getCategoryAccess().getRightSquareBracketKeyword_5_1_2_0_3());
										}
									)
									    |
									(
										(
											lv_isApplicableForAll_21_0='All'
											{
												newLeafNode(lv_isApplicableForAll_21_0, grammarAccess.getCategoryAccess().getIsApplicableForAllAllKeyword_5_1_2_1_0());
											}
											{
												if ($current==null) {
													$current = createModelElement(grammarAccess.getCategoryRule());
												}
												setWithLastConsumed($current, "isApplicableForAll", true, "All");
											}
										)
									)
								)
								otherlv_22=';'
								{
									newLeafNode(otherlv_22, grammarAccess.getCategoryAccess().getSemicolonKeyword_5_1_3());
								}
								))
					{ 
						getUnorderedGroupHelper().returnFromSelection(grammarAccess.getCategoryAccess().getUnorderedGroup_5());
					}
				)
			)|
			(
				{getUnorderedGroupHelper().canSelect(grammarAccess.getCategoryAccess().getUnorderedGroup_5(), 2)}?=>(
					{
						getUnorderedGroupHelper().select(grammarAccess.getCategoryAccess().getUnorderedGroup_5(), 2);
					}
								({true}?=>(otherlv_23='Cardinality'
								{
									newLeafNode(otherlv_23, grammarAccess.getCategoryAccess().getCardinalityKeyword_5_2_0());
								}
								(
									(
										lv_cardinality_24_0=RULE_INT
										{
											newLeafNode(lv_cardinality_24_0, grammarAccess.getCategoryAccess().getCardinalityINTTerminalRuleCall_5_2_1_0());
										}
										{
											if ($current==null) {
												$current = createModelElement(grammarAccess.getCategoryRule());
											}
											setWithLastConsumed(
												$current,
												"cardinality",
												lv_cardinality_24_0,
												"org.eclipse.xtext.common.Terminals.INT");
										}
									)
								)
								otherlv_25=';'
								{
									newLeafNode(otherlv_25, grammarAccess.getCategoryAccess().getSemicolonKeyword_5_2_2());
								}
								))
					{ 
						getUnorderedGroupHelper().returnFromSelection(grammarAccess.getCategoryAccess().getUnorderedGroup_5());
					}
				)
			)
					)*
				)
			)
				{ 
				  getUnorderedGroupHelper().leave(grammarAccess.getCategoryAccess().getUnorderedGroup_5());
				}
		)
		(
			(
				{
					newCompositeNode(grammarAccess.getCategoryAccess().getPropertiesAPropertyParserRuleCall_6_0());
				}
				lv_properties_26_0=ruleAProperty
				{
					if ($current==null) {
						$current = createModelElementForParent(grammarAccess.getCategoryRule());
					}
					add(
						$current,
						"properties",
						lv_properties_26_0,
						"de.dlr.sc.virsat.model.concept.ConceptLanguage.AProperty");
					afterParserOrEnumRuleCall();
				}
			)
		)*
		(
			(
				{
					newCompositeNode(grammarAccess.getCategoryAccess().getEquationDefinitionsEquationDefinitionParserRuleCall_7_0());
				}
				lv_equationDefinitions_27_0=ruleEquationDefinition
				{
					if ($current==null) {
						$current = createModelElementForParent(grammarAccess.getCategoryRule());
					}
					add(
						$current,
						"equationDefinitions",
						lv_equationDefinitions_27_0,
						"de.dlr.sc.virsat.model.concept.ConceptLanguage.EquationDefinition");
					afterParserOrEnumRuleCall();
				}
			)
		)*
		otherlv_28='}'
		{
			newLeafNode(otherlv_28, grammarAccess.getCategoryAccess().getRightCurlyBracketKeyword_8());
		}
	)
;

// Entry rule entryRuleConceptImport
entryRuleConceptImport returns [EObject current=null]:
	{ newCompositeNode(grammarAccess.getConceptImportRule()); }
	iv_ruleConceptImport=ruleConceptImport
	{ $current=$iv_ruleConceptImport.current; }
	EOF;

// Rule ConceptImport
ruleConceptImport returns [EObject current=null]
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
					grammarAccess.getConceptImportAccess().getConceptImportAction_0(),
					$current);
			}
		)
		otherlv_1='Import'
		{
			newLeafNode(otherlv_1, grammarAccess.getConceptImportAccess().getImportKeyword_1());
		}
		(
			(
				{
					newCompositeNode(grammarAccess.getConceptImportAccess().getImportedNamespaceQualifiedNameWithWildcardParserRuleCall_2_0());
				}
				lv_importedNamespace_2_0=ruleQualifiedNameWithWildcard
				{
					if ($current==null) {
						$current = createModelElementForParent(grammarAccess.getConceptImportRule());
					}
					set(
						$current,
						"importedNamespace",
						lv_importedNamespace_2_0,
						"de.dlr.sc.virsat.model.concept.ConceptLanguage.QualifiedNameWithWildcard");
					afterParserOrEnumRuleCall();
				}
			)
		)
		otherlv_3=';'
		{
			newLeafNode(otherlv_3, grammarAccess.getConceptImportAccess().getSemicolonKeyword_3());
		}
	)
;

// Entry rule entryRuleEcoreImport
entryRuleEcoreImport returns [EObject current=null]:
	{ newCompositeNode(grammarAccess.getEcoreImportRule()); }
	iv_ruleEcoreImport=ruleEcoreImport
	{ $current=$iv_ruleEcoreImport.current; }
	EOF;

// Rule EcoreImport
ruleEcoreImport returns [EObject current=null]
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
					grammarAccess.getEcoreImportAccess().getEcoreImportAction_0(),
					$current);
			}
		)
		otherlv_1='EImport'
		{
			newLeafNode(otherlv_1, grammarAccess.getEcoreImportAccess().getEImportKeyword_1());
		}
		(
			(
				lv_importedNsURI_2_0=RULE_STRING
				{
					newLeafNode(lv_importedNsURI_2_0, grammarAccess.getEcoreImportAccess().getImportedNsURISTRINGTerminalRuleCall_2_0());
				}
				{
					if ($current==null) {
						$current = createModelElement(grammarAccess.getEcoreImportRule());
					}
					setWithLastConsumed(
						$current,
						"importedNsURI",
						lv_importedNsURI_2_0,
						"org.eclipse.xtext.common.Terminals.STRING");
				}
			)
		)
		(
			otherlv_3='genModel'
			{
				newLeafNode(otherlv_3, grammarAccess.getEcoreImportAccess().getGenModelKeyword_3_0());
			}
			(
				(
					lv_importedGenModel_4_0=RULE_STRING
					{
						newLeafNode(lv_importedGenModel_4_0, grammarAccess.getEcoreImportAccess().getImportedGenModelSTRINGTerminalRuleCall_3_1_0());
					}
					{
						if ($current==null) {
							$current = createModelElement(grammarAccess.getEcoreImportRule());
						}
						setWithLastConsumed(
							$current,
							"importedGenModel",
							lv_importedGenModel_4_0,
							"org.eclipse.xtext.common.Terminals.STRING");
					}
				)
			)
		)?
		otherlv_5=';'
		{
			newLeafNode(otherlv_5, grammarAccess.getEcoreImportAccess().getSemicolonKeyword_4());
		}
	)
;

// Entry rule entryRuleAProperty
entryRuleAProperty returns [EObject current=null]:
	{ newCompositeNode(grammarAccess.getAPropertyRule()); }
	iv_ruleAProperty=ruleAProperty
	{ $current=$iv_ruleAProperty.current; }
	EOF;

// Rule AProperty
ruleAProperty returns [EObject current=null]
@init {
	enterRule();
}
@after {
	leaveRule();
}:
	(
		{
			newCompositeNode(grammarAccess.getAPropertyAccess().getComposedPropertyParserRuleCall_0());
		}
		this_ComposedProperty_0=ruleComposedProperty
		{
			$current = $this_ComposedProperty_0.current;
			afterParserOrEnumRuleCall();
		}
		    |
		{
			newCompositeNode(grammarAccess.getAPropertyAccess().getIntPropertyParserRuleCall_1());
		}
		this_IntProperty_1=ruleIntProperty
		{
			$current = $this_IntProperty_1.current;
			afterParserOrEnumRuleCall();
		}
		    |
		{
			newCompositeNode(grammarAccess.getAPropertyAccess().getFloatPropertyParserRuleCall_2());
		}
		this_FloatProperty_2=ruleFloatProperty
		{
			$current = $this_FloatProperty_2.current;
			afterParserOrEnumRuleCall();
		}
		    |
		{
			newCompositeNode(grammarAccess.getAPropertyAccess().getStringPropertyParserRuleCall_3());
		}
		this_StringProperty_3=ruleStringProperty
		{
			$current = $this_StringProperty_3.current;
			afterParserOrEnumRuleCall();
		}
		    |
		{
			newCompositeNode(grammarAccess.getAPropertyAccess().getBooleanPropertyParserRuleCall_4());
		}
		this_BooleanProperty_4=ruleBooleanProperty
		{
			$current = $this_BooleanProperty_4.current;
			afterParserOrEnumRuleCall();
		}
		    |
		{
			newCompositeNode(grammarAccess.getAPropertyAccess().getEnumPropertyParserRuleCall_5());
		}
		this_EnumProperty_5=ruleEnumProperty
		{
			$current = $this_EnumProperty_5.current;
			afterParserOrEnumRuleCall();
		}
		    |
		{
			newCompositeNode(grammarAccess.getAPropertyAccess().getReferencePropertyParserRuleCall_6());
		}
		this_ReferenceProperty_6=ruleReferenceProperty
		{
			$current = $this_ReferenceProperty_6.current;
			afterParserOrEnumRuleCall();
		}
		    |
		{
			newCompositeNode(grammarAccess.getAPropertyAccess().getEReferencePropertyParserRuleCall_7());
		}
		this_EReferenceProperty_7=ruleEReferenceProperty
		{
			$current = $this_EReferenceProperty_7.current;
			afterParserOrEnumRuleCall();
		}
		    |
		{
			newCompositeNode(grammarAccess.getAPropertyAccess().getResourcePropertyParserRuleCall_8());
		}
		this_ResourceProperty_8=ruleResourceProperty
		{
			$current = $this_ResourceProperty_8.current;
			afterParserOrEnumRuleCall();
		}
	)
;

// Entry rule entryRuleArrayModifier
entryRuleArrayModifier returns [EObject current=null]:
	{ newCompositeNode(grammarAccess.getArrayModifierRule()); }
	iv_ruleArrayModifier=ruleArrayModifier
	{ $current=$iv_ruleArrayModifier.current; }
	EOF;

// Rule ArrayModifier
ruleArrayModifier returns [EObject current=null]
@init {
	enterRule();
}
@after {
	leaveRule();
}:
	(
		{
			newCompositeNode(grammarAccess.getArrayModifierAccess().getDynmaicArrayModifierParserRuleCall_0());
		}
		this_DynmaicArrayModifier_0=ruleDynmaicArrayModifier
		{
			$current = $this_DynmaicArrayModifier_0.current;
			afterParserOrEnumRuleCall();
		}
		    |
		{
			newCompositeNode(grammarAccess.getArrayModifierAccess().getStaticArrayModifierParserRuleCall_1());
		}
		this_StaticArrayModifier_1=ruleStaticArrayModifier
		{
			$current = $this_StaticArrayModifier_1.current;
			afterParserOrEnumRuleCall();
		}
	)
;

// Entry rule entryRuleDynmaicArrayModifier
entryRuleDynmaicArrayModifier returns [EObject current=null]:
	{ newCompositeNode(grammarAccess.getDynmaicArrayModifierRule()); }
	iv_ruleDynmaicArrayModifier=ruleDynmaicArrayModifier
	{ $current=$iv_ruleDynmaicArrayModifier.current; }
	EOF;

// Rule DynmaicArrayModifier
ruleDynmaicArrayModifier returns [EObject current=null]
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
					grammarAccess.getDynmaicArrayModifierAccess().getDynamicArrayModifierAction_0(),
					$current);
			}
		)
		otherlv_1='['
		{
			newLeafNode(otherlv_1, grammarAccess.getDynmaicArrayModifierAccess().getLeftSquareBracketKeyword_1());
		}
		otherlv_2=']'
		{
			newLeafNode(otherlv_2, grammarAccess.getDynmaicArrayModifierAccess().getRightSquareBracketKeyword_2());
		}
	)
;

// Entry rule entryRuleStaticArrayModifier
entryRuleStaticArrayModifier returns [EObject current=null]:
	{ newCompositeNode(grammarAccess.getStaticArrayModifierRule()); }
	iv_ruleStaticArrayModifier=ruleStaticArrayModifier
	{ $current=$iv_ruleStaticArrayModifier.current; }
	EOF;

// Rule StaticArrayModifier
ruleStaticArrayModifier returns [EObject current=null]
@init {
	enterRule();
}
@after {
	leaveRule();
}:
	(
		otherlv_0='['
		{
			newLeafNode(otherlv_0, grammarAccess.getStaticArrayModifierAccess().getLeftSquareBracketKeyword_0());
		}
		(
			(
				lv_arraySize_1_0=RULE_INT
				{
					newLeafNode(lv_arraySize_1_0, grammarAccess.getStaticArrayModifierAccess().getArraySizeINTTerminalRuleCall_1_0());
				}
				{
					if ($current==null) {
						$current = createModelElement(grammarAccess.getStaticArrayModifierRule());
					}
					setWithLastConsumed(
						$current,
						"arraySize",
						lv_arraySize_1_0,
						"org.eclipse.xtext.common.Terminals.INT");
				}
			)
		)
		otherlv_2=']'
		{
			newLeafNode(otherlv_2, grammarAccess.getStaticArrayModifierAccess().getRightSquareBracketKeyword_2());
		}
	)
;

// Entry rule entryRuleComposedProperty
entryRuleComposedProperty returns [EObject current=null]:
	{ newCompositeNode(grammarAccess.getComposedPropertyRule()); }
	iv_ruleComposedProperty=ruleComposedProperty
	{ $current=$iv_ruleComposedProperty.current; }
	EOF;

// Rule ComposedProperty
ruleComposedProperty returns [EObject current=null]
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
					grammarAccess.getComposedPropertyAccess().getComposedPropertyAction_0(),
					$current);
			}
		)
		otherlv_1='Type'
		{
			newLeafNode(otherlv_1, grammarAccess.getComposedPropertyAccess().getTypeKeyword_1());
		}
		(
			(
				lv_name_2_0=RULE_ID
				{
					newLeafNode(lv_name_2_0, grammarAccess.getComposedPropertyAccess().getNameIDTerminalRuleCall_2_0());
				}
				{
					if ($current==null) {
						$current = createModelElement(grammarAccess.getComposedPropertyRule());
					}
					setWithLastConsumed(
						$current,
						"name",
						lv_name_2_0,
						"org.eclipse.xtext.common.Terminals.ID");
				}
			)
		)
		(
			(
				{
					newCompositeNode(grammarAccess.getComposedPropertyAccess().getArrayModifierArrayModifierParserRuleCall_3_0());
				}
				lv_arrayModifier_3_0=ruleArrayModifier
				{
					if ($current==null) {
						$current = createModelElementForParent(grammarAccess.getComposedPropertyRule());
					}
					set(
						$current,
						"arrayModifier",
						lv_arrayModifier_3_0,
						"de.dlr.sc.virsat.model.concept.ConceptLanguage.ArrayModifier");
					afterParserOrEnumRuleCall();
				}
			)
		)?
		otherlv_4='of'
		{
			newLeafNode(otherlv_4, grammarAccess.getComposedPropertyAccess().getOfKeyword_4());
		}
		otherlv_5='Category'
		{
			newLeafNode(otherlv_5, grammarAccess.getComposedPropertyAccess().getCategoryKeyword_5());
		}
		(
			(
				{
					if ($current==null) {
						$current = createModelElement(grammarAccess.getComposedPropertyRule());
					}
				}
				{
					newCompositeNode(grammarAccess.getComposedPropertyAccess().getTypeCategoryCrossReference_6_0());
				}
				ruleQualifiedName
				{
					afterParserOrEnumRuleCall();
				}
			)
		)
		(
			(
				{ 
				  getUnorderedGroupHelper().enter(grammarAccess.getComposedPropertyAccess().getUnorderedGroup_7());
				}
				(
					(
			(
				{getUnorderedGroupHelper().canSelect(grammarAccess.getComposedPropertyAccess().getUnorderedGroup_7(), 0)}?=>(
					{
						getUnorderedGroupHelper().select(grammarAccess.getComposedPropertyAccess().getUnorderedGroup_7(), 0);
					}
								({true}?=>(otherlv_8='description'
								{
									newLeafNode(otherlv_8, grammarAccess.getComposedPropertyAccess().getDescriptionKeyword_7_0_0());
								}
								(
									(
										{
											newCompositeNode(grammarAccess.getComposedPropertyAccess().getDescriptionEStringParserRuleCall_7_0_1_0());
										}
										lv_description_9_0=ruleEString
										{
											if ($current==null) {
												$current = createModelElementForParent(grammarAccess.getComposedPropertyRule());
											}
											set(
												$current,
												"description",
												lv_description_9_0,
												"de.dlr.sc.virsat.model.concept.ConceptLanguage.EString");
											afterParserOrEnumRuleCall();
										}
									)
								)
								))
					{ 
						getUnorderedGroupHelper().returnFromSelection(grammarAccess.getComposedPropertyAccess().getUnorderedGroup_7());
					}
				)
			)|
			(
				{getUnorderedGroupHelper().canSelect(grammarAccess.getComposedPropertyAccess().getUnorderedGroup_7(), 1)}?=>(
					{
						getUnorderedGroupHelper().select(grammarAccess.getComposedPropertyAccess().getUnorderedGroup_7(), 1);
					}
								({true}?=>(otherlv_10='quantityKind'
								{
									newLeafNode(otherlv_10, grammarAccess.getComposedPropertyAccess().getQuantityKindKeyword_7_1_0());
								}
								(
									(
										{
											newCompositeNode(grammarAccess.getComposedPropertyAccess().getQuantityKindNameEStringParserRuleCall_7_1_1_0());
										}
										lv_quantityKindName_11_0=ruleEString
										{
											if ($current==null) {
												$current = createModelElementForParent(grammarAccess.getComposedPropertyRule());
											}
											set(
												$current,
												"quantityKindName",
												lv_quantityKindName_11_0,
												"de.dlr.sc.virsat.model.concept.ConceptLanguage.EString");
											afterParserOrEnumRuleCall();
										}
									)
								)
								))
					{ 
						getUnorderedGroupHelper().returnFromSelection(grammarAccess.getComposedPropertyAccess().getUnorderedGroup_7());
					}
				)
			)|
			(
				{getUnorderedGroupHelper().canSelect(grammarAccess.getComposedPropertyAccess().getUnorderedGroup_7(), 2)}?=>(
					{
						getUnorderedGroupHelper().select(grammarAccess.getComposedPropertyAccess().getUnorderedGroup_7(), 2);
					}
								({true}?=>(otherlv_12='unit'
								{
									newLeafNode(otherlv_12, grammarAccess.getComposedPropertyAccess().getUnitKeyword_7_2_0());
								}
								(
									(
										{
											newCompositeNode(grammarAccess.getComposedPropertyAccess().getUnitNameEStringParserRuleCall_7_2_1_0());
										}
										lv_unitName_13_0=ruleEString
										{
											if ($current==null) {
												$current = createModelElementForParent(grammarAccess.getComposedPropertyRule());
											}
											set(
												$current,
												"unitName",
												lv_unitName_13_0,
												"de.dlr.sc.virsat.model.concept.ConceptLanguage.EString");
											afterParserOrEnumRuleCall();
										}
									)
								)
								))
					{ 
						getUnorderedGroupHelper().returnFromSelection(grammarAccess.getComposedPropertyAccess().getUnorderedGroup_7());
					}
				)
			)
					)*
				)
			)
				{ 
				  getUnorderedGroupHelper().leave(grammarAccess.getComposedPropertyAccess().getUnorderedGroup_7());
				}
		)
		otherlv_14=';'
		{
			newLeafNode(otherlv_14, grammarAccess.getComposedPropertyAccess().getSemicolonKeyword_8());
		}
	)
;

// Entry rule entryRuleIntProperty
entryRuleIntProperty returns [EObject current=null]:
	{ newCompositeNode(grammarAccess.getIntPropertyRule()); }
	iv_ruleIntProperty=ruleIntProperty
	{ $current=$iv_ruleIntProperty.current; }
	EOF;

// Rule IntProperty
ruleIntProperty returns [EObject current=null]
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
					grammarAccess.getIntPropertyAccess().getIntPropertyAction_0(),
					$current);
			}
		)
		otherlv_1='IntProperty'
		{
			newLeafNode(otherlv_1, grammarAccess.getIntPropertyAccess().getIntPropertyKeyword_1());
		}
		(
			(
				lv_name_2_0=RULE_ID
				{
					newLeafNode(lv_name_2_0, grammarAccess.getIntPropertyAccess().getNameIDTerminalRuleCall_2_0());
				}
				{
					if ($current==null) {
						$current = createModelElement(grammarAccess.getIntPropertyRule());
					}
					setWithLastConsumed(
						$current,
						"name",
						lv_name_2_0,
						"org.eclipse.xtext.common.Terminals.ID");
				}
			)
		)
		(
			(
				{
					newCompositeNode(grammarAccess.getIntPropertyAccess().getArrayModifierArrayModifierParserRuleCall_3_0());
				}
				lv_arrayModifier_3_0=ruleArrayModifier
				{
					if ($current==null) {
						$current = createModelElementForParent(grammarAccess.getIntPropertyRule());
					}
					set(
						$current,
						"arrayModifier",
						lv_arrayModifier_3_0,
						"de.dlr.sc.virsat.model.concept.ConceptLanguage.ArrayModifier");
					afterParserOrEnumRuleCall();
				}
			)
		)?
		(
			(
				{ 
				  getUnorderedGroupHelper().enter(grammarAccess.getIntPropertyAccess().getUnorderedGroup_4());
				}
				(
					(
			(
				{getUnorderedGroupHelper().canSelect(grammarAccess.getIntPropertyAccess().getUnorderedGroup_4(), 0)}?=>(
					{
						getUnorderedGroupHelper().select(grammarAccess.getIntPropertyAccess().getUnorderedGroup_4(), 0);
					}
								({true}?=>(otherlv_5='description'
								{
									newLeafNode(otherlv_5, grammarAccess.getIntPropertyAccess().getDescriptionKeyword_4_0_0());
								}
								(
									(
										{
											newCompositeNode(grammarAccess.getIntPropertyAccess().getDescriptionEStringParserRuleCall_4_0_1_0());
										}
										lv_description_6_0=ruleEString
										{
											if ($current==null) {
												$current = createModelElementForParent(grammarAccess.getIntPropertyRule());
											}
											set(
												$current,
												"description",
												lv_description_6_0,
												"de.dlr.sc.virsat.model.concept.ConceptLanguage.EString");
											afterParserOrEnumRuleCall();
										}
									)
								)
								))
					{ 
						getUnorderedGroupHelper().returnFromSelection(grammarAccess.getIntPropertyAccess().getUnorderedGroup_4());
					}
				)
			)|
			(
				{getUnorderedGroupHelper().canSelect(grammarAccess.getIntPropertyAccess().getUnorderedGroup_4(), 1)}?=>(
					{
						getUnorderedGroupHelper().select(grammarAccess.getIntPropertyAccess().getUnorderedGroup_4(), 1);
					}
								({true}?=>(otherlv_7='default'
								{
									newLeafNode(otherlv_7, grammarAccess.getIntPropertyAccess().getDefaultKeyword_4_1_0());
								}
								(
									(
										{
											newCompositeNode(grammarAccess.getIntPropertyAccess().getDefaultValueIntLiteralStringParserRuleCall_4_1_1_0());
										}
										lv_defaultValue_8_0=ruleIntLiteralString
										{
											if ($current==null) {
												$current = createModelElementForParent(grammarAccess.getIntPropertyRule());
											}
											set(
												$current,
												"defaultValue",
												lv_defaultValue_8_0,
												"de.dlr.sc.virsat.model.concept.ConceptLanguage.IntLiteralString");
											afterParserOrEnumRuleCall();
										}
									)
								)
								))
					{ 
						getUnorderedGroupHelper().returnFromSelection(grammarAccess.getIntPropertyAccess().getUnorderedGroup_4());
					}
				)
			)|
			(
				{getUnorderedGroupHelper().canSelect(grammarAccess.getIntPropertyAccess().getUnorderedGroup_4(), 2)}?=>(
					{
						getUnorderedGroupHelper().select(grammarAccess.getIntPropertyAccess().getUnorderedGroup_4(), 2);
					}
								({true}?=>(otherlv_9='quantityKind'
								{
									newLeafNode(otherlv_9, grammarAccess.getIntPropertyAccess().getQuantityKindKeyword_4_2_0());
								}
								(
									(
										{
											newCompositeNode(grammarAccess.getIntPropertyAccess().getQuantityKindNameEStringParserRuleCall_4_2_1_0());
										}
										lv_quantityKindName_10_0=ruleEString
										{
											if ($current==null) {
												$current = createModelElementForParent(grammarAccess.getIntPropertyRule());
											}
											set(
												$current,
												"quantityKindName",
												lv_quantityKindName_10_0,
												"de.dlr.sc.virsat.model.concept.ConceptLanguage.EString");
											afterParserOrEnumRuleCall();
										}
									)
								)
								))
					{ 
						getUnorderedGroupHelper().returnFromSelection(grammarAccess.getIntPropertyAccess().getUnorderedGroup_4());
					}
				)
			)|
			(
				{getUnorderedGroupHelper().canSelect(grammarAccess.getIntPropertyAccess().getUnorderedGroup_4(), 3)}?=>(
					{
						getUnorderedGroupHelper().select(grammarAccess.getIntPropertyAccess().getUnorderedGroup_4(), 3);
					}
								({true}?=>(otherlv_11='unit'
								{
									newLeafNode(otherlv_11, grammarAccess.getIntPropertyAccess().getUnitKeyword_4_3_0());
								}
								(
									(
										{
											newCompositeNode(grammarAccess.getIntPropertyAccess().getUnitNameEStringParserRuleCall_4_3_1_0());
										}
										lv_unitName_12_0=ruleEString
										{
											if ($current==null) {
												$current = createModelElementForParent(grammarAccess.getIntPropertyRule());
											}
											set(
												$current,
												"unitName",
												lv_unitName_12_0,
												"de.dlr.sc.virsat.model.concept.ConceptLanguage.EString");
											afterParserOrEnumRuleCall();
										}
									)
								)
								))
					{ 
						getUnorderedGroupHelper().returnFromSelection(grammarAccess.getIntPropertyAccess().getUnorderedGroup_4());
					}
				)
			)
					)*
				)
			)
				{ 
				  getUnorderedGroupHelper().leave(grammarAccess.getIntPropertyAccess().getUnorderedGroup_4());
				}
		)
		otherlv_13=';'
		{
			newLeafNode(otherlv_13, grammarAccess.getIntPropertyAccess().getSemicolonKeyword_5());
		}
	)
;

// Entry rule entryRuleFloatProperty
entryRuleFloatProperty returns [EObject current=null]:
	{ newCompositeNode(grammarAccess.getFloatPropertyRule()); }
	iv_ruleFloatProperty=ruleFloatProperty
	{ $current=$iv_ruleFloatProperty.current; }
	EOF;

// Rule FloatProperty
ruleFloatProperty returns [EObject current=null]
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
					grammarAccess.getFloatPropertyAccess().getFloatPropertyAction_0(),
					$current);
			}
		)
		otherlv_1='FloatProperty'
		{
			newLeafNode(otherlv_1, grammarAccess.getFloatPropertyAccess().getFloatPropertyKeyword_1());
		}
		(
			(
				lv_name_2_0=RULE_ID
				{
					newLeafNode(lv_name_2_0, grammarAccess.getFloatPropertyAccess().getNameIDTerminalRuleCall_2_0());
				}
				{
					if ($current==null) {
						$current = createModelElement(grammarAccess.getFloatPropertyRule());
					}
					setWithLastConsumed(
						$current,
						"name",
						lv_name_2_0,
						"org.eclipse.xtext.common.Terminals.ID");
				}
			)
		)
		(
			(
				{
					newCompositeNode(grammarAccess.getFloatPropertyAccess().getArrayModifierArrayModifierParserRuleCall_3_0());
				}
				lv_arrayModifier_3_0=ruleArrayModifier
				{
					if ($current==null) {
						$current = createModelElementForParent(grammarAccess.getFloatPropertyRule());
					}
					set(
						$current,
						"arrayModifier",
						lv_arrayModifier_3_0,
						"de.dlr.sc.virsat.model.concept.ConceptLanguage.ArrayModifier");
					afterParserOrEnumRuleCall();
				}
			)
		)?
		(
			(
				{ 
				  getUnorderedGroupHelper().enter(grammarAccess.getFloatPropertyAccess().getUnorderedGroup_4());
				}
				(
					(
			(
				{getUnorderedGroupHelper().canSelect(grammarAccess.getFloatPropertyAccess().getUnorderedGroup_4(), 0)}?=>(
					{
						getUnorderedGroupHelper().select(grammarAccess.getFloatPropertyAccess().getUnorderedGroup_4(), 0);
					}
								({true}?=>(otherlv_5='description'
								{
									newLeafNode(otherlv_5, grammarAccess.getFloatPropertyAccess().getDescriptionKeyword_4_0_0());
								}
								(
									(
										{
											newCompositeNode(grammarAccess.getFloatPropertyAccess().getDescriptionEStringParserRuleCall_4_0_1_0());
										}
										lv_description_6_0=ruleEString
										{
											if ($current==null) {
												$current = createModelElementForParent(grammarAccess.getFloatPropertyRule());
											}
											set(
												$current,
												"description",
												lv_description_6_0,
												"de.dlr.sc.virsat.model.concept.ConceptLanguage.EString");
											afterParserOrEnumRuleCall();
										}
									)
								)
								))
					{ 
						getUnorderedGroupHelper().returnFromSelection(grammarAccess.getFloatPropertyAccess().getUnorderedGroup_4());
					}
				)
			)|
			(
				{getUnorderedGroupHelper().canSelect(grammarAccess.getFloatPropertyAccess().getUnorderedGroup_4(), 1)}?=>(
					{
						getUnorderedGroupHelper().select(grammarAccess.getFloatPropertyAccess().getUnorderedGroup_4(), 1);
					}
								({true}?=>(otherlv_7='default'
								{
									newLeafNode(otherlv_7, grammarAccess.getFloatPropertyAccess().getDefaultKeyword_4_1_0());
								}
								(
									(
										{
											newCompositeNode(grammarAccess.getFloatPropertyAccess().getDefaultValueFloatLiteralStringParserRuleCall_4_1_1_0());
										}
										lv_defaultValue_8_0=ruleFloatLiteralString
										{
											if ($current==null) {
												$current = createModelElementForParent(grammarAccess.getFloatPropertyRule());
											}
											set(
												$current,
												"defaultValue",
												lv_defaultValue_8_0,
												"de.dlr.sc.virsat.model.concept.ConceptLanguage.FloatLiteralString");
											afterParserOrEnumRuleCall();
										}
									)
								)
								))
					{ 
						getUnorderedGroupHelper().returnFromSelection(grammarAccess.getFloatPropertyAccess().getUnorderedGroup_4());
					}
				)
			)|
			(
				{getUnorderedGroupHelper().canSelect(grammarAccess.getFloatPropertyAccess().getUnorderedGroup_4(), 2)}?=>(
					{
						getUnorderedGroupHelper().select(grammarAccess.getFloatPropertyAccess().getUnorderedGroup_4(), 2);
					}
								({true}?=>(otherlv_9='quantityKind'
								{
									newLeafNode(otherlv_9, grammarAccess.getFloatPropertyAccess().getQuantityKindKeyword_4_2_0());
								}
								(
									(
										{
											newCompositeNode(grammarAccess.getFloatPropertyAccess().getQuantityKindNameEStringParserRuleCall_4_2_1_0());
										}
										lv_quantityKindName_10_0=ruleEString
										{
											if ($current==null) {
												$current = createModelElementForParent(grammarAccess.getFloatPropertyRule());
											}
											set(
												$current,
												"quantityKindName",
												lv_quantityKindName_10_0,
												"de.dlr.sc.virsat.model.concept.ConceptLanguage.EString");
											afterParserOrEnumRuleCall();
										}
									)
								)
								))
					{ 
						getUnorderedGroupHelper().returnFromSelection(grammarAccess.getFloatPropertyAccess().getUnorderedGroup_4());
					}
				)
			)|
			(
				{getUnorderedGroupHelper().canSelect(grammarAccess.getFloatPropertyAccess().getUnorderedGroup_4(), 3)}?=>(
					{
						getUnorderedGroupHelper().select(grammarAccess.getFloatPropertyAccess().getUnorderedGroup_4(), 3);
					}
								({true}?=>(otherlv_11='unit'
								{
									newLeafNode(otherlv_11, grammarAccess.getFloatPropertyAccess().getUnitKeyword_4_3_0());
								}
								(
									(
										{
											newCompositeNode(grammarAccess.getFloatPropertyAccess().getUnitNameEStringParserRuleCall_4_3_1_0());
										}
										lv_unitName_12_0=ruleEString
										{
											if ($current==null) {
												$current = createModelElementForParent(grammarAccess.getFloatPropertyRule());
											}
											set(
												$current,
												"unitName",
												lv_unitName_12_0,
												"de.dlr.sc.virsat.model.concept.ConceptLanguage.EString");
											afterParserOrEnumRuleCall();
										}
									)
								)
								))
					{ 
						getUnorderedGroupHelper().returnFromSelection(grammarAccess.getFloatPropertyAccess().getUnorderedGroup_4());
					}
				)
			)
					)*
				)
			)
				{ 
				  getUnorderedGroupHelper().leave(grammarAccess.getFloatPropertyAccess().getUnorderedGroup_4());
				}
		)
		otherlv_13=';'
		{
			newLeafNode(otherlv_13, grammarAccess.getFloatPropertyAccess().getSemicolonKeyword_5());
		}
	)
;

// Entry rule entryRuleStringProperty
entryRuleStringProperty returns [EObject current=null]:
	{ newCompositeNode(grammarAccess.getStringPropertyRule()); }
	iv_ruleStringProperty=ruleStringProperty
	{ $current=$iv_ruleStringProperty.current; }
	EOF;

// Rule StringProperty
ruleStringProperty returns [EObject current=null]
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
					grammarAccess.getStringPropertyAccess().getStringPropertyAction_0(),
					$current);
			}
		)
		otherlv_1='StringProperty'
		{
			newLeafNode(otherlv_1, grammarAccess.getStringPropertyAccess().getStringPropertyKeyword_1());
		}
		(
			(
				lv_name_2_0=RULE_ID
				{
					newLeafNode(lv_name_2_0, grammarAccess.getStringPropertyAccess().getNameIDTerminalRuleCall_2_0());
				}
				{
					if ($current==null) {
						$current = createModelElement(grammarAccess.getStringPropertyRule());
					}
					setWithLastConsumed(
						$current,
						"name",
						lv_name_2_0,
						"org.eclipse.xtext.common.Terminals.ID");
				}
			)
		)
		(
			(
				{
					newCompositeNode(grammarAccess.getStringPropertyAccess().getArrayModifierArrayModifierParserRuleCall_3_0());
				}
				lv_arrayModifier_3_0=ruleArrayModifier
				{
					if ($current==null) {
						$current = createModelElementForParent(grammarAccess.getStringPropertyRule());
					}
					set(
						$current,
						"arrayModifier",
						lv_arrayModifier_3_0,
						"de.dlr.sc.virsat.model.concept.ConceptLanguage.ArrayModifier");
					afterParserOrEnumRuleCall();
				}
			)
		)?
		(
			(
				{ 
				  getUnorderedGroupHelper().enter(grammarAccess.getStringPropertyAccess().getUnorderedGroup_4());
				}
				(
					(
			(
				{getUnorderedGroupHelper().canSelect(grammarAccess.getStringPropertyAccess().getUnorderedGroup_4(), 0)}?=>(
					{
						getUnorderedGroupHelper().select(grammarAccess.getStringPropertyAccess().getUnorderedGroup_4(), 0);
					}
								({true}?=>(otherlv_5='description'
								{
									newLeafNode(otherlv_5, grammarAccess.getStringPropertyAccess().getDescriptionKeyword_4_0_0());
								}
								(
									(
										{
											newCompositeNode(grammarAccess.getStringPropertyAccess().getDescriptionEStringParserRuleCall_4_0_1_0());
										}
										lv_description_6_0=ruleEString
										{
											if ($current==null) {
												$current = createModelElementForParent(grammarAccess.getStringPropertyRule());
											}
											set(
												$current,
												"description",
												lv_description_6_0,
												"de.dlr.sc.virsat.model.concept.ConceptLanguage.EString");
											afterParserOrEnumRuleCall();
										}
									)
								)
								))
					{ 
						getUnorderedGroupHelper().returnFromSelection(grammarAccess.getStringPropertyAccess().getUnorderedGroup_4());
					}
				)
			)|
			(
				{getUnorderedGroupHelper().canSelect(grammarAccess.getStringPropertyAccess().getUnorderedGroup_4(), 1)}?=>(
					{
						getUnorderedGroupHelper().select(grammarAccess.getStringPropertyAccess().getUnorderedGroup_4(), 1);
					}
								({true}?=>(otherlv_7='default'
								{
									newLeafNode(otherlv_7, grammarAccess.getStringPropertyAccess().getDefaultKeyword_4_1_0());
								}
								(
									(
										{
											newCompositeNode(grammarAccess.getStringPropertyAccess().getDefaultValueEStringParserRuleCall_4_1_1_0());
										}
										lv_defaultValue_8_0=ruleEString
										{
											if ($current==null) {
												$current = createModelElementForParent(grammarAccess.getStringPropertyRule());
											}
											set(
												$current,
												"defaultValue",
												lv_defaultValue_8_0,
												"de.dlr.sc.virsat.model.concept.ConceptLanguage.EString");
											afterParserOrEnumRuleCall();
										}
									)
								)
								))
					{ 
						getUnorderedGroupHelper().returnFromSelection(grammarAccess.getStringPropertyAccess().getUnorderedGroup_4());
					}
				)
			)
					)*
				)
			)
				{ 
				  getUnorderedGroupHelper().leave(grammarAccess.getStringPropertyAccess().getUnorderedGroup_4());
				}
		)
		otherlv_9=';'
		{
			newLeafNode(otherlv_9, grammarAccess.getStringPropertyAccess().getSemicolonKeyword_5());
		}
	)
;

// Entry rule entryRuleBooleanProperty
entryRuleBooleanProperty returns [EObject current=null]:
	{ newCompositeNode(grammarAccess.getBooleanPropertyRule()); }
	iv_ruleBooleanProperty=ruleBooleanProperty
	{ $current=$iv_ruleBooleanProperty.current; }
	EOF;

// Rule BooleanProperty
ruleBooleanProperty returns [EObject current=null]
@init {
	enterRule();
}
@after {
	leaveRule();
}:
	(
		otherlv_0='BooleanProperty'
		{
			newLeafNode(otherlv_0, grammarAccess.getBooleanPropertyAccess().getBooleanPropertyKeyword_0());
		}
		(
			(
				lv_name_1_0=RULE_ID
				{
					newLeafNode(lv_name_1_0, grammarAccess.getBooleanPropertyAccess().getNameIDTerminalRuleCall_1_0());
				}
				{
					if ($current==null) {
						$current = createModelElement(grammarAccess.getBooleanPropertyRule());
					}
					setWithLastConsumed(
						$current,
						"name",
						lv_name_1_0,
						"org.eclipse.xtext.common.Terminals.ID");
				}
			)
		)
		(
			(
				{
					newCompositeNode(grammarAccess.getBooleanPropertyAccess().getArrayModifierArrayModifierParserRuleCall_2_0());
				}
				lv_arrayModifier_2_0=ruleArrayModifier
				{
					if ($current==null) {
						$current = createModelElementForParent(grammarAccess.getBooleanPropertyRule());
					}
					set(
						$current,
						"arrayModifier",
						lv_arrayModifier_2_0,
						"de.dlr.sc.virsat.model.concept.ConceptLanguage.ArrayModifier");
					afterParserOrEnumRuleCall();
				}
			)
		)?
		(
			(
				{ 
				  getUnorderedGroupHelper().enter(grammarAccess.getBooleanPropertyAccess().getUnorderedGroup_3());
				}
				(
					(
			(
				{getUnorderedGroupHelper().canSelect(grammarAccess.getBooleanPropertyAccess().getUnorderedGroup_3(), 0)}?=>(
					{
						getUnorderedGroupHelper().select(grammarAccess.getBooleanPropertyAccess().getUnorderedGroup_3(), 0);
					}
								({true}?=>(otherlv_4='description'
								{
									newLeafNode(otherlv_4, grammarAccess.getBooleanPropertyAccess().getDescriptionKeyword_3_0_0());
								}
								(
									(
										{
											newCompositeNode(grammarAccess.getBooleanPropertyAccess().getDescriptionEStringParserRuleCall_3_0_1_0());
										}
										lv_description_5_0=ruleEString
										{
											if ($current==null) {
												$current = createModelElementForParent(grammarAccess.getBooleanPropertyRule());
											}
											set(
												$current,
												"description",
												lv_description_5_0,
												"de.dlr.sc.virsat.model.concept.ConceptLanguage.EString");
											afterParserOrEnumRuleCall();
										}
									)
								)
								))
					{ 
						getUnorderedGroupHelper().returnFromSelection(grammarAccess.getBooleanPropertyAccess().getUnorderedGroup_3());
					}
				)
			)|
			(
				{getUnorderedGroupHelper().canSelect(grammarAccess.getBooleanPropertyAccess().getUnorderedGroup_3(), 1)}?=>(
					{
						getUnorderedGroupHelper().select(grammarAccess.getBooleanPropertyAccess().getUnorderedGroup_3(), 1);
					}
								({true}?=>(otherlv_6='default'
								{
									newLeafNode(otherlv_6, grammarAccess.getBooleanPropertyAccess().getDefaultKeyword_3_1_0());
								}
								(
									(
										{
											newCompositeNode(grammarAccess.getBooleanPropertyAccess().getDefaultValueBooleanLiteralStringParserRuleCall_3_1_1_0());
										}
										lv_defaultValue_7_0=ruleBooleanLiteralString
										{
											if ($current==null) {
												$current = createModelElementForParent(grammarAccess.getBooleanPropertyRule());
											}
											set(
												$current,
												"defaultValue",
												lv_defaultValue_7_0,
												"de.dlr.sc.virsat.model.concept.ConceptLanguage.BooleanLiteralString");
											afterParserOrEnumRuleCall();
										}
									)
								)
								))
					{ 
						getUnorderedGroupHelper().returnFromSelection(grammarAccess.getBooleanPropertyAccess().getUnorderedGroup_3());
					}
				)
			)
					)*
				)
			)
				{ 
				  getUnorderedGroupHelper().leave(grammarAccess.getBooleanPropertyAccess().getUnorderedGroup_3());
				}
		)
		otherlv_8=';'
		{
			newLeafNode(otherlv_8, grammarAccess.getBooleanPropertyAccess().getSemicolonKeyword_4());
		}
	)
;

// Entry rule entryRuleEnumProperty
entryRuleEnumProperty returns [EObject current=null]:
	{ newCompositeNode(grammarAccess.getEnumPropertyRule()); }
	iv_ruleEnumProperty=ruleEnumProperty
	{ $current=$iv_ruleEnumProperty.current; }
	EOF;

// Rule EnumProperty
ruleEnumProperty returns [EObject current=null]
@init {
	enterRule();
}
@after {
	leaveRule();
}:
	(
		otherlv_0='EnumProperty'
		{
			newLeafNode(otherlv_0, grammarAccess.getEnumPropertyAccess().getEnumPropertyKeyword_0());
		}
		(
			(
				lv_name_1_0=RULE_ID
				{
					newLeafNode(lv_name_1_0, grammarAccess.getEnumPropertyAccess().getNameIDTerminalRuleCall_1_0());
				}
				{
					if ($current==null) {
						$current = createModelElement(grammarAccess.getEnumPropertyRule());
					}
					setWithLastConsumed(
						$current,
						"name",
						lv_name_1_0,
						"org.eclipse.xtext.common.Terminals.ID");
				}
			)
		)
		(
			(
				{
					newCompositeNode(grammarAccess.getEnumPropertyAccess().getArrayModifierArrayModifierParserRuleCall_2_0());
				}
				lv_arrayModifier_2_0=ruleArrayModifier
				{
					if ($current==null) {
						$current = createModelElementForParent(grammarAccess.getEnumPropertyRule());
					}
					set(
						$current,
						"arrayModifier",
						lv_arrayModifier_2_0,
						"de.dlr.sc.virsat.model.concept.ConceptLanguage.ArrayModifier");
					afterParserOrEnumRuleCall();
				}
			)
		)?
		(
			(
				{ 
				  getUnorderedGroupHelper().enter(grammarAccess.getEnumPropertyAccess().getUnorderedGroup_3());
				}
				(
					(
			(
				{getUnorderedGroupHelper().canSelect(grammarAccess.getEnumPropertyAccess().getUnorderedGroup_3(), 0)}?=>(
					{
						getUnorderedGroupHelper().select(grammarAccess.getEnumPropertyAccess().getUnorderedGroup_3(), 0);
					}
								({true}?=>(otherlv_4='description'
								{
									newLeafNode(otherlv_4, grammarAccess.getEnumPropertyAccess().getDescriptionKeyword_3_0_0());
								}
								(
									(
										{
											newCompositeNode(grammarAccess.getEnumPropertyAccess().getDescriptionEStringParserRuleCall_3_0_1_0());
										}
										lv_description_5_0=ruleEString
										{
											if ($current==null) {
												$current = createModelElementForParent(grammarAccess.getEnumPropertyRule());
											}
											set(
												$current,
												"description",
												lv_description_5_0,
												"de.dlr.sc.virsat.model.concept.ConceptLanguage.EString");
											afterParserOrEnumRuleCall();
										}
									)
								)
								))
					{ 
						getUnorderedGroupHelper().returnFromSelection(grammarAccess.getEnumPropertyAccess().getUnorderedGroup_3());
					}
				)
			)|
			(
				{getUnorderedGroupHelper().canSelect(grammarAccess.getEnumPropertyAccess().getUnorderedGroup_3(), 1)}?=>(
					{
						getUnorderedGroupHelper().select(grammarAccess.getEnumPropertyAccess().getUnorderedGroup_3(), 1);
					}
								({true}?=>(otherlv_6='quantityKind'
								{
									newLeafNode(otherlv_6, grammarAccess.getEnumPropertyAccess().getQuantityKindKeyword_3_1_0());
								}
								(
									(
										{
											newCompositeNode(grammarAccess.getEnumPropertyAccess().getQuantityKindNameEStringParserRuleCall_3_1_1_0());
										}
										lv_quantityKindName_7_0=ruleEString
										{
											if ($current==null) {
												$current = createModelElementForParent(grammarAccess.getEnumPropertyRule());
											}
											set(
												$current,
												"quantityKindName",
												lv_quantityKindName_7_0,
												"de.dlr.sc.virsat.model.concept.ConceptLanguage.EString");
											afterParserOrEnumRuleCall();
										}
									)
								)
								))
					{ 
						getUnorderedGroupHelper().returnFromSelection(grammarAccess.getEnumPropertyAccess().getUnorderedGroup_3());
					}
				)
			)|
			(
				{getUnorderedGroupHelper().canSelect(grammarAccess.getEnumPropertyAccess().getUnorderedGroup_3(), 2)}?=>(
					{
						getUnorderedGroupHelper().select(grammarAccess.getEnumPropertyAccess().getUnorderedGroup_3(), 2);
					}
								({true}?=>(otherlv_8='unit'
								{
									newLeafNode(otherlv_8, grammarAccess.getEnumPropertyAccess().getUnitKeyword_3_2_0());
								}
								(
									(
										{
											newCompositeNode(grammarAccess.getEnumPropertyAccess().getUnitNameEStringParserRuleCall_3_2_1_0());
										}
										lv_unitName_9_0=ruleEString
										{
											if ($current==null) {
												$current = createModelElementForParent(grammarAccess.getEnumPropertyRule());
											}
											set(
												$current,
												"unitName",
												lv_unitName_9_0,
												"de.dlr.sc.virsat.model.concept.ConceptLanguage.EString");
											afterParserOrEnumRuleCall();
										}
									)
								)
								))
					{ 
						getUnorderedGroupHelper().returnFromSelection(grammarAccess.getEnumPropertyAccess().getUnorderedGroup_3());
					}
				)
			)|
			(
				{getUnorderedGroupHelper().canSelect(grammarAccess.getEnumPropertyAccess().getUnorderedGroup_3(), 3)}?=>(
					{
						getUnorderedGroupHelper().select(grammarAccess.getEnumPropertyAccess().getUnorderedGroup_3(), 3);
					}
								({true}?=>(otherlv_10='values'
								{
									newLeafNode(otherlv_10, grammarAccess.getEnumPropertyAccess().getValuesKeyword_3_3_0());
								}
								otherlv_11='['
								{
									newLeafNode(otherlv_11, grammarAccess.getEnumPropertyAccess().getLeftSquareBracketKeyword_3_3_1());
								}
								(
									(
										{
											newCompositeNode(grammarAccess.getEnumPropertyAccess().getValuesEnumValueDefinitionParserRuleCall_3_3_2_0());
										}
										lv_values_12_0=ruleEnumValueDefinition
										{
											if ($current==null) {
												$current = createModelElementForParent(grammarAccess.getEnumPropertyRule());
											}
											add(
												$current,
												"values",
												lv_values_12_0,
												"de.dlr.sc.virsat.model.concept.ConceptLanguage.EnumValueDefinition");
											afterParserOrEnumRuleCall();
										}
									)
								)
								(
									otherlv_13=','
									{
										newLeafNode(otherlv_13, grammarAccess.getEnumPropertyAccess().getCommaKeyword_3_3_3_0());
									}
									(
										(
											{
												newCompositeNode(grammarAccess.getEnumPropertyAccess().getValuesEnumValueDefinitionParserRuleCall_3_3_3_1_0());
											}
											lv_values_14_0=ruleEnumValueDefinition
											{
												if ($current==null) {
													$current = createModelElementForParent(grammarAccess.getEnumPropertyRule());
												}
												add(
													$current,
													"values",
													lv_values_14_0,
													"de.dlr.sc.virsat.model.concept.ConceptLanguage.EnumValueDefinition");
												afterParserOrEnumRuleCall();
											}
										)
									)
								)*
								otherlv_15=']'
								{
									newLeafNode(otherlv_15, grammarAccess.getEnumPropertyAccess().getRightSquareBracketKeyword_3_3_4());
								}
								))
					{ 
						getUnorderedGroupHelper().returnFromSelection(grammarAccess.getEnumPropertyAccess().getUnorderedGroup_3());
					}
				)
			)|
			(
				{getUnorderedGroupHelper().canSelect(grammarAccess.getEnumPropertyAccess().getUnorderedGroup_3(), 4)}?=>(
					{
						getUnorderedGroupHelper().select(grammarAccess.getEnumPropertyAccess().getUnorderedGroup_3(), 4);
					}
								({true}?=>(otherlv_16='default'
								{
									newLeafNode(otherlv_16, grammarAccess.getEnumPropertyAccess().getDefaultKeyword_3_4_0());
								}
								(
									(
										{
											if ($current==null) {
												$current = createModelElement(grammarAccess.getEnumPropertyRule());
											}
										}
										otherlv_17=RULE_ID
										{
											newLeafNode(otherlv_17, grammarAccess.getEnumPropertyAccess().getDefaultValueEnumValueDefinitionCrossReference_3_4_1_0());
										}
									)
								)
								))
					{ 
						getUnorderedGroupHelper().returnFromSelection(grammarAccess.getEnumPropertyAccess().getUnorderedGroup_3());
					}
				)
			)
					)+
					{getUnorderedGroupHelper().canLeave(grammarAccess.getEnumPropertyAccess().getUnorderedGroup_3())}?
				)
			)
				{ 
				  getUnorderedGroupHelper().leave(grammarAccess.getEnumPropertyAccess().getUnorderedGroup_3());
				}
		)
		otherlv_18=';'
		{
			newLeafNode(otherlv_18, grammarAccess.getEnumPropertyAccess().getSemicolonKeyword_4());
		}
	)
;

// Entry rule entryRuleEnumValueDefinition
entryRuleEnumValueDefinition returns [EObject current=null]:
	{ newCompositeNode(grammarAccess.getEnumValueDefinitionRule()); }
	iv_ruleEnumValueDefinition=ruleEnumValueDefinition
	{ $current=$iv_ruleEnumValueDefinition.current; }
	EOF;

// Rule EnumValueDefinition
ruleEnumValueDefinition returns [EObject current=null]
@init {
	enterRule();
}
@after {
	leaveRule();
}:
	(
		(
			(
				lv_name_0_0=RULE_ID
				{
					newLeafNode(lv_name_0_0, grammarAccess.getEnumValueDefinitionAccess().getNameIDTerminalRuleCall_0_0());
				}
				{
					if ($current==null) {
						$current = createModelElement(grammarAccess.getEnumValueDefinitionRule());
					}
					setWithLastConsumed(
						$current,
						"name",
						lv_name_0_0,
						"org.eclipse.xtext.common.Terminals.ID");
				}
			)
		)
		otherlv_1='='
		{
			newLeafNode(otherlv_1, grammarAccess.getEnumValueDefinitionAccess().getEqualsSignKeyword_1());
		}
		(
			(
				(
					{
						newCompositeNode(grammarAccess.getEnumValueDefinitionAccess().getValueEStringParserRuleCall_2_0_0());
					}
					lv_value_2_1=ruleEString
					{
						if ($current==null) {
							$current = createModelElementForParent(grammarAccess.getEnumValueDefinitionRule());
						}
						set(
							$current,
							"value",
							lv_value_2_1,
							"de.dlr.sc.virsat.model.concept.ConceptLanguage.EString");
						afterParserOrEnumRuleCall();
					}
					    |
					{
						newCompositeNode(grammarAccess.getEnumValueDefinitionAccess().getValueFloatLiteralStringParserRuleCall_2_0_1());
					}
					lv_value_2_2=ruleFloatLiteralString
					{
						if ($current==null) {
							$current = createModelElementForParent(grammarAccess.getEnumValueDefinitionRule());
						}
						set(
							$current,
							"value",
							lv_value_2_2,
							"de.dlr.sc.virsat.model.concept.ConceptLanguage.FloatLiteralString");
						afterParserOrEnumRuleCall();
					}
				)
			)
		)
	)
;

// Entry rule entryRuleReferenceProperty
entryRuleReferenceProperty returns [EObject current=null]:
	{ newCompositeNode(grammarAccess.getReferencePropertyRule()); }
	iv_ruleReferenceProperty=ruleReferenceProperty
	{ $current=$iv_ruleReferenceProperty.current; }
	EOF;

// Rule ReferenceProperty
ruleReferenceProperty returns [EObject current=null]
@init {
	enterRule();
}
@after {
	leaveRule();
}:
	(
		otherlv_0='Reference'
		{
			newLeafNode(otherlv_0, grammarAccess.getReferencePropertyAccess().getReferenceKeyword_0());
		}
		(
			(
				lv_name_1_0=RULE_ID
				{
					newLeafNode(lv_name_1_0, grammarAccess.getReferencePropertyAccess().getNameIDTerminalRuleCall_1_0());
				}
				{
					if ($current==null) {
						$current = createModelElement(grammarAccess.getReferencePropertyRule());
					}
					setWithLastConsumed(
						$current,
						"name",
						lv_name_1_0,
						"org.eclipse.xtext.common.Terminals.ID");
				}
			)
		)
		(
			(
				{
					newCompositeNode(grammarAccess.getReferencePropertyAccess().getArrayModifierArrayModifierParserRuleCall_2_0());
				}
				lv_arrayModifier_2_0=ruleArrayModifier
				{
					if ($current==null) {
						$current = createModelElementForParent(grammarAccess.getReferencePropertyRule());
					}
					set(
						$current,
						"arrayModifier",
						lv_arrayModifier_2_0,
						"de.dlr.sc.virsat.model.concept.ConceptLanguage.ArrayModifier");
					afterParserOrEnumRuleCall();
				}
			)
		)?
		otherlv_3='of'
		{
			newLeafNode(otherlv_3, grammarAccess.getReferencePropertyAccess().getOfKeyword_3());
		}
		otherlv_4='Type'
		{
			newLeafNode(otherlv_4, grammarAccess.getReferencePropertyAccess().getTypeKeyword_4());
		}
		(
			(
				{
					if ($current==null) {
						$current = createModelElement(grammarAccess.getReferencePropertyRule());
					}
				}
				{
					newCompositeNode(grammarAccess.getReferencePropertyAccess().getReferenceTypeATypeDefinitionCrossReference_5_0());
				}
				ruleQualifiedName
				{
					afterParserOrEnumRuleCall();
				}
			)
		)
		(
			otherlv_6='description'
			{
				newLeafNode(otherlv_6, grammarAccess.getReferencePropertyAccess().getDescriptionKeyword_6_0());
			}
			(
				(
					{
						newCompositeNode(grammarAccess.getReferencePropertyAccess().getDescriptionEStringParserRuleCall_6_1_0());
					}
					lv_description_7_0=ruleEString
					{
						if ($current==null) {
							$current = createModelElementForParent(grammarAccess.getReferencePropertyRule());
						}
						set(
							$current,
							"description",
							lv_description_7_0,
							"de.dlr.sc.virsat.model.concept.ConceptLanguage.EString");
						afterParserOrEnumRuleCall();
					}
				)
			)
		)?
		otherlv_8=';'
		{
			newLeafNode(otherlv_8, grammarAccess.getReferencePropertyAccess().getSemicolonKeyword_7());
		}
	)
;

// Entry rule entryRuleEReferenceProperty
entryRuleEReferenceProperty returns [EObject current=null]:
	{ newCompositeNode(grammarAccess.getEReferencePropertyRule()); }
	iv_ruleEReferenceProperty=ruleEReferenceProperty
	{ $current=$iv_ruleEReferenceProperty.current; }
	EOF;

// Rule EReferenceProperty
ruleEReferenceProperty returns [EObject current=null]
@init {
	enterRule();
}
@after {
	leaveRule();
}:
	(
		otherlv_0='EReference'
		{
			newLeafNode(otherlv_0, grammarAccess.getEReferencePropertyAccess().getEReferenceKeyword_0());
		}
		(
			(
				lv_name_1_0=RULE_ID
				{
					newLeafNode(lv_name_1_0, grammarAccess.getEReferencePropertyAccess().getNameIDTerminalRuleCall_1_0());
				}
				{
					if ($current==null) {
						$current = createModelElement(grammarAccess.getEReferencePropertyRule());
					}
					setWithLastConsumed(
						$current,
						"name",
						lv_name_1_0,
						"org.eclipse.xtext.common.Terminals.ID");
				}
			)
		)
		(
			(
				{
					newCompositeNode(grammarAccess.getEReferencePropertyAccess().getArrayModifierArrayModifierParserRuleCall_2_0());
				}
				lv_arrayModifier_2_0=ruleArrayModifier
				{
					if ($current==null) {
						$current = createModelElementForParent(grammarAccess.getEReferencePropertyRule());
					}
					set(
						$current,
						"arrayModifier",
						lv_arrayModifier_2_0,
						"de.dlr.sc.virsat.model.concept.ConceptLanguage.ArrayModifier");
					afterParserOrEnumRuleCall();
				}
			)
		)?
		otherlv_3='of'
		{
			newLeafNode(otherlv_3, grammarAccess.getEReferencePropertyAccess().getOfKeyword_3());
		}
		otherlv_4='Type'
		{
			newLeafNode(otherlv_4, grammarAccess.getEReferencePropertyAccess().getTypeKeyword_4());
		}
		(
			(
				{
					if ($current==null) {
						$current = createModelElement(grammarAccess.getEReferencePropertyRule());
					}
				}
				{
					newCompositeNode(grammarAccess.getEReferencePropertyAccess().getReferenceTypeEClassCrossReference_5_0());
				}
				ruleQualifiedName
				{
					afterParserOrEnumRuleCall();
				}
			)
		)
		(
			otherlv_6='description'
			{
				newLeafNode(otherlv_6, grammarAccess.getEReferencePropertyAccess().getDescriptionKeyword_6_0());
			}
			(
				(
					{
						newCompositeNode(grammarAccess.getEReferencePropertyAccess().getDescriptionEStringParserRuleCall_6_1_0());
					}
					lv_description_7_0=ruleEString
					{
						if ($current==null) {
							$current = createModelElementForParent(grammarAccess.getEReferencePropertyRule());
						}
						set(
							$current,
							"description",
							lv_description_7_0,
							"de.dlr.sc.virsat.model.concept.ConceptLanguage.EString");
						afterParserOrEnumRuleCall();
					}
				)
			)
		)?
		otherlv_8=';'
		{
			newLeafNode(otherlv_8, grammarAccess.getEReferencePropertyAccess().getSemicolonKeyword_7());
		}
	)
;

// Entry rule entryRuleResourceProperty
entryRuleResourceProperty returns [EObject current=null]:
	{ newCompositeNode(grammarAccess.getResourcePropertyRule()); }
	iv_ruleResourceProperty=ruleResourceProperty
	{ $current=$iv_ruleResourceProperty.current; }
	EOF;

// Rule ResourceProperty
ruleResourceProperty returns [EObject current=null]
@init {
	enterRule();
}
@after {
	leaveRule();
}:
	(
		otherlv_0='Resource'
		{
			newLeafNode(otherlv_0, grammarAccess.getResourcePropertyAccess().getResourceKeyword_0());
		}
		(
			(
				lv_name_1_0=RULE_ID
				{
					newLeafNode(lv_name_1_0, grammarAccess.getResourcePropertyAccess().getNameIDTerminalRuleCall_1_0());
				}
				{
					if ($current==null) {
						$current = createModelElement(grammarAccess.getResourcePropertyRule());
					}
					setWithLastConsumed(
						$current,
						"name",
						lv_name_1_0,
						"org.eclipse.xtext.common.Terminals.ID");
				}
			)
		)
		(
			(
				{
					newCompositeNode(grammarAccess.getResourcePropertyAccess().getArrayModifierArrayModifierParserRuleCall_2_0());
				}
				lv_arrayModifier_2_0=ruleArrayModifier
				{
					if ($current==null) {
						$current = createModelElementForParent(grammarAccess.getResourcePropertyRule());
					}
					set(
						$current,
						"arrayModifier",
						lv_arrayModifier_2_0,
						"de.dlr.sc.virsat.model.concept.ConceptLanguage.ArrayModifier");
					afterParserOrEnumRuleCall();
				}
			)
		)?
		(
			otherlv_3='description'
			{
				newLeafNode(otherlv_3, grammarAccess.getResourcePropertyAccess().getDescriptionKeyword_3_0());
			}
			(
				(
					{
						newCompositeNode(grammarAccess.getResourcePropertyAccess().getDescriptionEStringParserRuleCall_3_1_0());
					}
					lv_description_4_0=ruleEString
					{
						if ($current==null) {
							$current = createModelElementForParent(grammarAccess.getResourcePropertyRule());
						}
						set(
							$current,
							"description",
							lv_description_4_0,
							"de.dlr.sc.virsat.model.concept.ConceptLanguage.EString");
						afterParserOrEnumRuleCall();
					}
				)
			)
		)?
		otherlv_5=';'
		{
			newLeafNode(otherlv_5, grammarAccess.getResourcePropertyAccess().getSemicolonKeyword_4());
		}
	)
;

// Entry rule entryRuleEquationDefinition
entryRuleEquationDefinition returns [EObject current=null]:
	{ newCompositeNode(grammarAccess.getEquationDefinitionRule()); }
	iv_ruleEquationDefinition=ruleEquationDefinition
	{ $current=$iv_ruleEquationDefinition.current; }
	EOF;

// Rule EquationDefinition
ruleEquationDefinition returns [EObject current=null]
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
					newCompositeNode(grammarAccess.getEquationDefinitionAccess().getResultEquationDefinitionResultParserRuleCall_0_0());
				}
				lv_result_0_0=ruleEquationDefinitionResult
				{
					if ($current==null) {
						$current = createModelElementForParent(grammarAccess.getEquationDefinitionRule());
					}
					set(
						$current,
						"result",
						lv_result_0_0,
						"de.dlr.sc.virsat.model.concept.ConceptLanguage.EquationDefinitionResult");
					afterParserOrEnumRuleCall();
				}
			)
		)
		otherlv_1='='
		{
			newLeafNode(otherlv_1, grammarAccess.getEquationDefinitionAccess().getEqualsSignKeyword_1());
		}
		(
			(
				{
					newCompositeNode(grammarAccess.getEquationDefinitionAccess().getExpressionAdditionAndSubtractionParserRuleCall_2_0());
				}
				lv_expression_2_0=ruleAdditionAndSubtraction
				{
					if ($current==null) {
						$current = createModelElementForParent(grammarAccess.getEquationDefinitionRule());
					}
					set(
						$current,
						"expression",
						lv_expression_2_0,
						"de.dlr.sc.virsat.model.concept.ConceptLanguage.AdditionAndSubtraction");
					afterParserOrEnumRuleCall();
				}
			)
		)
		otherlv_3=';'
		{
			newLeafNode(otherlv_3, grammarAccess.getEquationDefinitionAccess().getSemicolonKeyword_3());
		}
	)
;

// Entry rule entryRuleEquationDefinitionResult
entryRuleEquationDefinitionResult returns [EObject current=null]:
	{ newCompositeNode(grammarAccess.getEquationDefinitionResultRule()); }
	iv_ruleEquationDefinitionResult=ruleEquationDefinitionResult
	{ $current=$iv_ruleEquationDefinitionResult.current; }
	EOF;

// Rule EquationDefinitionResult
ruleEquationDefinitionResult returns [EObject current=null]
@init {
	enterRule();
}
@after {
	leaveRule();
}:
	(
		{
			newCompositeNode(grammarAccess.getEquationDefinitionResultAccess().getTypeDefinitionResultParserRuleCall_0());
		}
		this_TypeDefinitionResult_0=ruleTypeDefinitionResult
		{
			$current = $this_TypeDefinitionResult_0.current;
			afterParserOrEnumRuleCall();
		}
		    |
		{
			newCompositeNode(grammarAccess.getEquationDefinitionResultAccess().getEquationIntermediateResultParserRuleCall_1());
		}
		this_EquationIntermediateResult_1=ruleEquationIntermediateResult
		{
			$current = $this_EquationIntermediateResult_1.current;
			afterParserOrEnumRuleCall();
		}
	)
;

// Entry rule entryRuleTypeDefinitionResult
entryRuleTypeDefinitionResult returns [EObject current=null]:
	{ newCompositeNode(grammarAccess.getTypeDefinitionResultRule()); }
	iv_ruleTypeDefinitionResult=ruleTypeDefinitionResult
	{ $current=$iv_ruleTypeDefinitionResult.current; }
	EOF;

// Rule TypeDefinitionResult
ruleTypeDefinitionResult returns [EObject current=null]
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
					grammarAccess.getTypeDefinitionResultAccess().getTypeDefinitionResultAction_0(),
					$current);
			}
		)
		otherlv_1='Ref:'
		{
			newLeafNode(otherlv_1, grammarAccess.getTypeDefinitionResultAccess().getRefKeyword_1());
		}
		(
			(
				{
					if ($current==null) {
						$current = createModelElement(grammarAccess.getTypeDefinitionResultRule());
					}
				}
				{
					newCompositeNode(grammarAccess.getTypeDefinitionResultAccess().getReferenceATypeDefinitionCrossReference_2_0());
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
								"de.dlr.sc.virsat.model.concept.ConceptLanguage.OperatorPlus");
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
								"de.dlr.sc.virsat.model.concept.ConceptLanguage.OperatorMinus");
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
							"de.dlr.sc.virsat.model.concept.ConceptLanguage.MultiplicationAndDivision");
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
								"de.dlr.sc.virsat.model.concept.ConceptLanguage.OperatorMultiply");
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
								"de.dlr.sc.virsat.model.concept.ConceptLanguage.OperatorDivide");
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
							"de.dlr.sc.virsat.model.concept.ConceptLanguage.PowerFunction");
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
							"de.dlr.sc.virsat.model.concept.ConceptLanguage.OperatorPower");
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
							"de.dlr.sc.virsat.model.concept.ConceptLanguage.AExpression");
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
						"de.dlr.sc.virsat.model.concept.ConceptLanguage.OperatorMinus");
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
						"de.dlr.sc.virsat.model.concept.ConceptLanguage.AdditionAndSubtraction");
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
			newCompositeNode(grammarAccess.getAExpressionAccess().getReferencedDefinitionInputParserRuleCall_1());
		}
		this_ReferencedDefinitionInput_1=ruleReferencedDefinitionInput
		{
			$current = $this_ReferencedDefinitionInput_1.current;
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

// Entry rule entryRuleReferencedDefinitionInput
entryRuleReferencedDefinitionInput returns [EObject current=null]:
	{ newCompositeNode(grammarAccess.getReferencedDefinitionInputRule()); }
	iv_ruleReferencedDefinitionInput=ruleReferencedDefinitionInput
	{ $current=$iv_ruleReferencedDefinitionInput.current; }
	EOF;

// Rule ReferencedDefinitionInput
ruleReferencedDefinitionInput returns [EObject current=null]
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
					$current = createModelElement(grammarAccess.getReferencedDefinitionInputRule());
				}
			}
			{
				newCompositeNode(grammarAccess.getReferencedDefinitionInputAccess().getReferenceIEquationDefinitionInputCrossReference_0());
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
							"de.dlr.sc.virsat.model.concept.ConceptLanguage.OperatorCos");
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
							"de.dlr.sc.virsat.model.concept.ConceptLanguage.OperatorSin");
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
							"de.dlr.sc.virsat.model.concept.ConceptLanguage.OperatorTan");
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
							"de.dlr.sc.virsat.model.concept.ConceptLanguage.OperatorAtan");
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
							"de.dlr.sc.virsat.model.concept.ConceptLanguage.OperatorAcos");
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
							"de.dlr.sc.virsat.model.concept.ConceptLanguage.OperatorAsin");
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
							"de.dlr.sc.virsat.model.concept.ConceptLanguage.OperatorSqrt");
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
							"de.dlr.sc.virsat.model.concept.ConceptLanguage.OperatorLog");
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
							"de.dlr.sc.virsat.model.concept.ConceptLanguage.OperatorLn");
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
							"de.dlr.sc.virsat.model.concept.ConceptLanguage.OperatorLd");
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
							"de.dlr.sc.virsat.model.concept.ConceptLanguage.OperatorExp");
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
						"de.dlr.sc.virsat.model.concept.ConceptLanguage.AdditionAndSubtraction");
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
						"de.dlr.sc.virsat.model.concept.ConceptLanguage.AdditionAndSubtraction");
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
							"de.dlr.sc.virsat.model.concept.ConceptLanguage.AdditionAndSubtraction");
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
					newCompositeNode(grammarAccess.getNumberLiteralAccess().getValueFloatLiteralStringParserRuleCall_1_0());
				}
				lv_value_1_0=ruleFloatLiteralString
				{
					if ($current==null) {
						$current = createModelElementForParent(grammarAccess.getNumberLiteralRule());
					}
					set(
						$current,
						"value",
						lv_value_1_0,
						"de.dlr.sc.virsat.model.concept.ConceptLanguage.FloatLiteralString");
					afterParserOrEnumRuleCall();
				}
			)
		)
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

// Entry rule entryRuleVersion
entryRuleVersion returns [String current=null]:
	{ newCompositeNode(grammarAccess.getVersionRule()); }
	iv_ruleVersion=ruleVersion
	{ $current=$iv_ruleVersion.current.getText(); }
	EOF;

// Rule Version
ruleVersion returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()]
@init {
	enterRule();
}
@after {
	leaveRule();
}:
	(
		this_INT_0=RULE_INT
		{
			$current.merge(this_INT_0);
		}
		{
			newLeafNode(this_INT_0, grammarAccess.getVersionAccess().getINTTerminalRuleCall_0());
		}
		(
			kw='.'
			{
				$current.merge(kw);
				newLeafNode(kw, grammarAccess.getVersionAccess().getFullStopKeyword_1_0());
			}
			this_INT_2=RULE_INT
			{
				$current.merge(this_INT_2);
			}
			{
				newLeafNode(this_INT_2, grammarAccess.getVersionAccess().getINTTerminalRuleCall_1_1());
			}
		)*
	)
;

// Entry rule entryRuleIntLiteralString
entryRuleIntLiteralString returns [String current=null]:
	{ newCompositeNode(grammarAccess.getIntLiteralStringRule()); }
	iv_ruleIntLiteralString=ruleIntLiteralString
	{ $current=$iv_ruleIntLiteralString.current.getText(); }
	EOF;

// Rule IntLiteralString
ruleIntLiteralString returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()]
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
				newLeafNode(kw, grammarAccess.getIntLiteralStringAccess().getHyphenMinusKeyword_0());
			}
		)?
		(
			this_INT_1=RULE_INT
			{
				$current.merge(this_INT_1);
			}
			{
				newLeafNode(this_INT_1, grammarAccess.getIntLiteralStringAccess().getINTTerminalRuleCall_1());
			}
		)?
	)
;

// Entry rule entryRuleFloatLiteralString
entryRuleFloatLiteralString returns [String current=null]:
	{ newCompositeNode(grammarAccess.getFloatLiteralStringRule()); }
	iv_ruleFloatLiteralString=ruleFloatLiteralString
	{ $current=$iv_ruleFloatLiteralString.current.getText(); }
	EOF;

// Rule FloatLiteralString
ruleFloatLiteralString returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()]
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
				newLeafNode(kw, grammarAccess.getFloatLiteralStringAccess().getHyphenMinusKeyword_0());
			}
		)?
		this_INT_1=RULE_INT
		{
			$current.merge(this_INT_1);
		}
		{
			newLeafNode(this_INT_1, grammarAccess.getFloatLiteralStringAccess().getINTTerminalRuleCall_1());
		}
		(
			kw='.'
			{
				$current.merge(kw);
				newLeafNode(kw, grammarAccess.getFloatLiteralStringAccess().getFullStopKeyword_2_0());
			}
			this_INT_3=RULE_INT
			{
				$current.merge(this_INT_3);
			}
			{
				newLeafNode(this_INT_3, grammarAccess.getFloatLiteralStringAccess().getINTTerminalRuleCall_2_1());
			}
		)?
	)
;

// Entry rule entryRuleBooleanLiteralString
entryRuleBooleanLiteralString returns [String current=null]:
	{ newCompositeNode(grammarAccess.getBooleanLiteralStringRule()); }
	iv_ruleBooleanLiteralString=ruleBooleanLiteralString
	{ $current=$iv_ruleBooleanLiteralString.current.getText(); }
	EOF;

// Rule BooleanLiteralString
ruleBooleanLiteralString returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()]
@init {
	enterRule();
}
@after {
	leaveRule();
}:
	(
		kw='true'
		{
			$current.merge(kw);
			newLeafNode(kw, grammarAccess.getBooleanLiteralStringAccess().getTrueKeyword_0());
		}
		    |
		kw='false'
		{
			$current.merge(kw);
			newLeafNode(kw, grammarAccess.getBooleanLiteralStringAccess().getFalseKeyword_1());
		}
	)
;

// Entry rule entryRuleEString
entryRuleEString returns [String current=null]:
	{ newCompositeNode(grammarAccess.getEStringRule()); }
	iv_ruleEString=ruleEString
	{ $current=$iv_ruleEString.current.getText(); }
	EOF;

// Rule EString
ruleEString returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()]
@init {
	enterRule();
}
@after {
	leaveRule();
}:
	this_STRING_0=RULE_STRING
	{
		$current.merge(this_STRING_0);
	}
	{
		newLeafNode(this_STRING_0, grammarAccess.getEStringAccess().getSTRINGTerminalRuleCall());
	}
;

// Entry rule entryRuleQualifiedNameWithWildcard
entryRuleQualifiedNameWithWildcard returns [String current=null]:
	{ newCompositeNode(grammarAccess.getQualifiedNameWithWildcardRule()); }
	iv_ruleQualifiedNameWithWildcard=ruleQualifiedNameWithWildcard
	{ $current=$iv_ruleQualifiedNameWithWildcard.current.getText(); }
	EOF;

// Rule QualifiedNameWithWildcard
ruleQualifiedNameWithWildcard returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()]
@init {
	enterRule();
}
@after {
	leaveRule();
}:
	(
		{
			newCompositeNode(grammarAccess.getQualifiedNameWithWildcardAccess().getQualifiedNameParserRuleCall_0());
		}
		this_QualifiedName_0=ruleQualifiedName
		{
			$current.merge(this_QualifiedName_0);
		}
		{
			afterParserOrEnumRuleCall();
		}
		(
			kw='.*'
			{
				$current.merge(kw);
				newLeafNode(kw, grammarAccess.getQualifiedNameWithWildcardAccess().getFullStopAsteriskKeyword_1());
			}
		)?
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
