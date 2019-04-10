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



import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
@SuppressWarnings("all")
public class InternalConceptLanguageParser extends AbstractInternalAntlrParser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "RULE_ID", "RULE_INT", "RULE_STRING", "RULE_ML_COMMENT", "RULE_SL_COMMENT", "RULE_WS", "RULE_ANY_OTHER", "'Concept'", "'displayname'", "'version'", "'description'", "'hasDMF'", "'{'", "'}'", "'StructuralElement'", "'shortname'", "'IsRootStructuralElement'", "';'", "'Inherits'", "'From'", "'['", "','", "']'", "'All'", "'Applicable'", "'For'", "'Cardinality'", "'GeneralRelation'", "'Referenced'", "'Type'", "'Category'", "'extends'", "'IsAbstract'", "'Import'", "'of'", "'quantityKind'", "'unit'", "'IntProperty'", "'default'", "'FloatProperty'", "'StringProperty'", "'BooleanProperty'", "'EnumProperty'", "'values'", "'='", "'Reference'", "'Resource'", "'Ref:'", "'Calc:'", "'('", "')'", "'pi'", "'e'", "'.'", "'-'", "'true'", "'false'", "'.*'", "'+'", "'*'", "'/'", "'^'", "'cos'", "'sin'", "'tan'", "'atan'", "'acos'", "'asin'", "'sqrt'", "'log'", "'ln'", "'exp'", "'ld'"
    };
    public static final int T__50=50;
    public static final int T__19=19;
    public static final int T__15=15;
    public static final int T__59=59;
    public static final int T__16=16;
    public static final int T__17=17;
    public static final int T__18=18;
    public static final int T__11=11;
    public static final int T__55=55;
    public static final int T__12=12;
    public static final int T__56=56;
    public static final int T__13=13;
    public static final int T__57=57;
    public static final int T__14=14;
    public static final int T__58=58;
    public static final int T__51=51;
    public static final int T__52=52;
    public static final int T__53=53;
    public static final int T__54=54;
    public static final int T__60=60;
    public static final int T__61=61;
    public static final int RULE_ID=4;
    public static final int T__26=26;
    public static final int T__27=27;
    public static final int T__28=28;
    public static final int RULE_INT=5;
    public static final int T__29=29;
    public static final int T__22=22;
    public static final int T__66=66;
    public static final int RULE_ML_COMMENT=7;
    public static final int T__23=23;
    public static final int T__67=67;
    public static final int T__24=24;
    public static final int T__68=68;
    public static final int T__25=25;
    public static final int T__69=69;
    public static final int T__62=62;
    public static final int T__63=63;
    public static final int T__20=20;
    public static final int T__64=64;
    public static final int T__21=21;
    public static final int T__65=65;
    public static final int T__70=70;
    public static final int T__71=71;
    public static final int T__72=72;
    public static final int RULE_STRING=6;
    public static final int RULE_SL_COMMENT=8;
    public static final int T__37=37;
    public static final int T__38=38;
    public static final int T__39=39;
    public static final int T__33=33;
    public static final int T__34=34;
    public static final int T__35=35;
    public static final int T__36=36;
    public static final int T__73=73;
    public static final int EOF=-1;
    public static final int T__30=30;
    public static final int T__74=74;
    public static final int T__31=31;
    public static final int T__75=75;
    public static final int T__32=32;
    public static final int T__76=76;
    public static final int RULE_WS=9;
    public static final int RULE_ANY_OTHER=10;
    public static final int T__48=48;
    public static final int T__49=49;
    public static final int T__44=44;
    public static final int T__45=45;
    public static final int T__46=46;
    public static final int T__47=47;
    public static final int T__40=40;
    public static final int T__41=41;
    public static final int T__42=42;
    public static final int T__43=43;

    // delegates
    // delegators


        public InternalConceptLanguageParser(TokenStream input) {
            this(input, new RecognizerSharedState());
        }
        public InternalConceptLanguageParser(TokenStream input, RecognizerSharedState state) {
            super(input, state);
             
        }
        

    public String[] getTokenNames() { return InternalConceptLanguageParser.tokenNames; }
    public String getGrammarFileName() { return "InternalConceptLanguage.g"; }



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




    // $ANTLR start "entryRuleConcept"
    // InternalConceptLanguage.g:70:1: entryRuleConcept returns [EObject current=null] : iv_ruleConcept= ruleConcept EOF ;
    public final EObject entryRuleConcept() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleConcept = null;


        try {
            // InternalConceptLanguage.g:70:48: (iv_ruleConcept= ruleConcept EOF )
            // InternalConceptLanguage.g:71:2: iv_ruleConcept= ruleConcept EOF
            {
             newCompositeNode(grammarAccess.getConceptRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleConcept=ruleConcept();

            state._fsp--;

             current =iv_ruleConcept; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleConcept"


    // $ANTLR start "ruleConcept"
    // InternalConceptLanguage.g:77:1: ruleConcept returns [EObject current=null] : ( () otherlv_1= 'Concept' ( (lv_name_2_0= ruleQualifiedName ) ) ( ( ( ( ({...}? => ( ({...}? => (otherlv_4= 'displayname' ( (lv_displayName_5_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_6= 'version' ( (lv_version_7_0= ruleVersion ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_8= 'description' ( (lv_description_9_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_DMF_10_0= 'hasDMF' ) ) ) ) ) )* ) ) ) otherlv_11= '{' ( (lv_imports_12_0= ruleConceptImport ) )* ( (lv_structuralElements_13_0= ruleStructuralElement ) )* ( (lv_relations_14_0= ruleARelation ) )* ( (lv_categories_15_0= ruleCategory ) )* otherlv_16= '}' ) ;
    public final EObject ruleConcept() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_4=null;
        Token otherlv_6=null;
        Token otherlv_8=null;
        Token lv_DMF_10_0=null;
        Token otherlv_11=null;
        Token otherlv_16=null;
        AntlrDatatypeRuleToken lv_name_2_0 = null;

        AntlrDatatypeRuleToken lv_displayName_5_0 = null;

        AntlrDatatypeRuleToken lv_version_7_0 = null;

        AntlrDatatypeRuleToken lv_description_9_0 = null;

        EObject lv_imports_12_0 = null;

        EObject lv_structuralElements_13_0 = null;

        EObject lv_relations_14_0 = null;

        EObject lv_categories_15_0 = null;



        	enterRule();

        try {
            // InternalConceptLanguage.g:83:2: ( ( () otherlv_1= 'Concept' ( (lv_name_2_0= ruleQualifiedName ) ) ( ( ( ( ({...}? => ( ({...}? => (otherlv_4= 'displayname' ( (lv_displayName_5_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_6= 'version' ( (lv_version_7_0= ruleVersion ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_8= 'description' ( (lv_description_9_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_DMF_10_0= 'hasDMF' ) ) ) ) ) )* ) ) ) otherlv_11= '{' ( (lv_imports_12_0= ruleConceptImport ) )* ( (lv_structuralElements_13_0= ruleStructuralElement ) )* ( (lv_relations_14_0= ruleARelation ) )* ( (lv_categories_15_0= ruleCategory ) )* otherlv_16= '}' ) )
            // InternalConceptLanguage.g:84:2: ( () otherlv_1= 'Concept' ( (lv_name_2_0= ruleQualifiedName ) ) ( ( ( ( ({...}? => ( ({...}? => (otherlv_4= 'displayname' ( (lv_displayName_5_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_6= 'version' ( (lv_version_7_0= ruleVersion ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_8= 'description' ( (lv_description_9_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_DMF_10_0= 'hasDMF' ) ) ) ) ) )* ) ) ) otherlv_11= '{' ( (lv_imports_12_0= ruleConceptImport ) )* ( (lv_structuralElements_13_0= ruleStructuralElement ) )* ( (lv_relations_14_0= ruleARelation ) )* ( (lv_categories_15_0= ruleCategory ) )* otherlv_16= '}' )
            {
            // InternalConceptLanguage.g:84:2: ( () otherlv_1= 'Concept' ( (lv_name_2_0= ruleQualifiedName ) ) ( ( ( ( ({...}? => ( ({...}? => (otherlv_4= 'displayname' ( (lv_displayName_5_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_6= 'version' ( (lv_version_7_0= ruleVersion ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_8= 'description' ( (lv_description_9_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_DMF_10_0= 'hasDMF' ) ) ) ) ) )* ) ) ) otherlv_11= '{' ( (lv_imports_12_0= ruleConceptImport ) )* ( (lv_structuralElements_13_0= ruleStructuralElement ) )* ( (lv_relations_14_0= ruleARelation ) )* ( (lv_categories_15_0= ruleCategory ) )* otherlv_16= '}' )
            // InternalConceptLanguage.g:85:3: () otherlv_1= 'Concept' ( (lv_name_2_0= ruleQualifiedName ) ) ( ( ( ( ({...}? => ( ({...}? => (otherlv_4= 'displayname' ( (lv_displayName_5_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_6= 'version' ( (lv_version_7_0= ruleVersion ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_8= 'description' ( (lv_description_9_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_DMF_10_0= 'hasDMF' ) ) ) ) ) )* ) ) ) otherlv_11= '{' ( (lv_imports_12_0= ruleConceptImport ) )* ( (lv_structuralElements_13_0= ruleStructuralElement ) )* ( (lv_relations_14_0= ruleARelation ) )* ( (lv_categories_15_0= ruleCategory ) )* otherlv_16= '}'
            {
            // InternalConceptLanguage.g:85:3: ()
            // InternalConceptLanguage.g:86:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getConceptAccess().getConceptAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,11,FOLLOW_3); 

            			newLeafNode(otherlv_1, grammarAccess.getConceptAccess().getConceptKeyword_1());
            		
            // InternalConceptLanguage.g:96:3: ( (lv_name_2_0= ruleQualifiedName ) )
            // InternalConceptLanguage.g:97:4: (lv_name_2_0= ruleQualifiedName )
            {
            // InternalConceptLanguage.g:97:4: (lv_name_2_0= ruleQualifiedName )
            // InternalConceptLanguage.g:98:5: lv_name_2_0= ruleQualifiedName
            {

            					newCompositeNode(grammarAccess.getConceptAccess().getNameQualifiedNameParserRuleCall_2_0());
            				
            pushFollow(FOLLOW_4);
            lv_name_2_0=ruleQualifiedName();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getConceptRule());
            					}
            					set(
            						current,
            						"name",
            						lv_name_2_0,
            						"de.dlr.sc.virsat.model.concept.ConceptLanguage.QualifiedName");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            // InternalConceptLanguage.g:115:3: ( ( ( ( ({...}? => ( ({...}? => (otherlv_4= 'displayname' ( (lv_displayName_5_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_6= 'version' ( (lv_version_7_0= ruleVersion ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_8= 'description' ( (lv_description_9_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_DMF_10_0= 'hasDMF' ) ) ) ) ) )* ) ) )
            // InternalConceptLanguage.g:116:4: ( ( ( ({...}? => ( ({...}? => (otherlv_4= 'displayname' ( (lv_displayName_5_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_6= 'version' ( (lv_version_7_0= ruleVersion ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_8= 'description' ( (lv_description_9_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_DMF_10_0= 'hasDMF' ) ) ) ) ) )* ) )
            {
            // InternalConceptLanguage.g:116:4: ( ( ( ({...}? => ( ({...}? => (otherlv_4= 'displayname' ( (lv_displayName_5_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_6= 'version' ( (lv_version_7_0= ruleVersion ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_8= 'description' ( (lv_description_9_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_DMF_10_0= 'hasDMF' ) ) ) ) ) )* ) )
            // InternalConceptLanguage.g:117:5: ( ( ({...}? => ( ({...}? => (otherlv_4= 'displayname' ( (lv_displayName_5_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_6= 'version' ( (lv_version_7_0= ruleVersion ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_8= 'description' ( (lv_description_9_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_DMF_10_0= 'hasDMF' ) ) ) ) ) )* )
            {
             
            				  getUnorderedGroupHelper().enter(grammarAccess.getConceptAccess().getUnorderedGroup_3());
            				
            // InternalConceptLanguage.g:120:5: ( ( ({...}? => ( ({...}? => (otherlv_4= 'displayname' ( (lv_displayName_5_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_6= 'version' ( (lv_version_7_0= ruleVersion ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_8= 'description' ( (lv_description_9_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_DMF_10_0= 'hasDMF' ) ) ) ) ) )* )
            // InternalConceptLanguage.g:121:6: ( ({...}? => ( ({...}? => (otherlv_4= 'displayname' ( (lv_displayName_5_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_6= 'version' ( (lv_version_7_0= ruleVersion ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_8= 'description' ( (lv_description_9_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_DMF_10_0= 'hasDMF' ) ) ) ) ) )*
            {
            // InternalConceptLanguage.g:121:6: ( ({...}? => ( ({...}? => (otherlv_4= 'displayname' ( (lv_displayName_5_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_6= 'version' ( (lv_version_7_0= ruleVersion ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_8= 'description' ( (lv_description_9_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_DMF_10_0= 'hasDMF' ) ) ) ) ) )*
            loop1:
            do {
                int alt1=5;
                int LA1_0 = input.LA(1);

                if ( LA1_0 == 12 && getUnorderedGroupHelper().canSelect(grammarAccess.getConceptAccess().getUnorderedGroup_3(), 0) ) {
                    alt1=1;
                }
                else if ( LA1_0 == 13 && getUnorderedGroupHelper().canSelect(grammarAccess.getConceptAccess().getUnorderedGroup_3(), 1) ) {
                    alt1=2;
                }
                else if ( LA1_0 == 14 && getUnorderedGroupHelper().canSelect(grammarAccess.getConceptAccess().getUnorderedGroup_3(), 2) ) {
                    alt1=3;
                }
                else if ( LA1_0 == 15 && getUnorderedGroupHelper().canSelect(grammarAccess.getConceptAccess().getUnorderedGroup_3(), 3) ) {
                    alt1=4;
                }


                switch (alt1) {
            	case 1 :
            	    // InternalConceptLanguage.g:122:4: ({...}? => ( ({...}? => (otherlv_4= 'displayname' ( (lv_displayName_5_0= ruleEString ) ) ) ) ) )
            	    {
            	    // InternalConceptLanguage.g:122:4: ({...}? => ( ({...}? => (otherlv_4= 'displayname' ( (lv_displayName_5_0= ruleEString ) ) ) ) ) )
            	    // InternalConceptLanguage.g:123:5: {...}? => ( ({...}? => (otherlv_4= 'displayname' ( (lv_displayName_5_0= ruleEString ) ) ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getConceptAccess().getUnorderedGroup_3(), 0) ) {
            	        throw new FailedPredicateException(input, "ruleConcept", "getUnorderedGroupHelper().canSelect(grammarAccess.getConceptAccess().getUnorderedGroup_3(), 0)");
            	    }
            	    // InternalConceptLanguage.g:123:104: ( ({...}? => (otherlv_4= 'displayname' ( (lv_displayName_5_0= ruleEString ) ) ) ) )
            	    // InternalConceptLanguage.g:124:6: ({...}? => (otherlv_4= 'displayname' ( (lv_displayName_5_0= ruleEString ) ) ) )
            	    {

            	    						getUnorderedGroupHelper().select(grammarAccess.getConceptAccess().getUnorderedGroup_3(), 0);
            	    					
            	    // InternalConceptLanguage.g:127:9: ({...}? => (otherlv_4= 'displayname' ( (lv_displayName_5_0= ruleEString ) ) ) )
            	    // InternalConceptLanguage.g:127:10: {...}? => (otherlv_4= 'displayname' ( (lv_displayName_5_0= ruleEString ) ) )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleConcept", "true");
            	    }
            	    // InternalConceptLanguage.g:127:19: (otherlv_4= 'displayname' ( (lv_displayName_5_0= ruleEString ) ) )
            	    // InternalConceptLanguage.g:127:20: otherlv_4= 'displayname' ( (lv_displayName_5_0= ruleEString ) )
            	    {
            	    otherlv_4=(Token)match(input,12,FOLLOW_5); 

            	    									newLeafNode(otherlv_4, grammarAccess.getConceptAccess().getDisplaynameKeyword_3_0_0());
            	    								
            	    // InternalConceptLanguage.g:131:9: ( (lv_displayName_5_0= ruleEString ) )
            	    // InternalConceptLanguage.g:132:10: (lv_displayName_5_0= ruleEString )
            	    {
            	    // InternalConceptLanguage.g:132:10: (lv_displayName_5_0= ruleEString )
            	    // InternalConceptLanguage.g:133:11: lv_displayName_5_0= ruleEString
            	    {

            	    											newCompositeNode(grammarAccess.getConceptAccess().getDisplayNameEStringParserRuleCall_3_0_1_0());
            	    										
            	    pushFollow(FOLLOW_4);
            	    lv_displayName_5_0=ruleEString();

            	    state._fsp--;


            	    											if (current==null) {
            	    												current = createModelElementForParent(grammarAccess.getConceptRule());
            	    											}
            	    											set(
            	    												current,
            	    												"displayName",
            	    												lv_displayName_5_0,
            	    												"de.dlr.sc.virsat.model.concept.ConceptLanguage.EString");
            	    											afterParserOrEnumRuleCall();
            	    										

            	    }


            	    }


            	    }


            	    }

            	     
            	    						getUnorderedGroupHelper().returnFromSelection(grammarAccess.getConceptAccess().getUnorderedGroup_3());
            	    					

            	    }


            	    }


            	    }
            	    break;
            	case 2 :
            	    // InternalConceptLanguage.g:156:4: ({...}? => ( ({...}? => (otherlv_6= 'version' ( (lv_version_7_0= ruleVersion ) ) ) ) ) )
            	    {
            	    // InternalConceptLanguage.g:156:4: ({...}? => ( ({...}? => (otherlv_6= 'version' ( (lv_version_7_0= ruleVersion ) ) ) ) ) )
            	    // InternalConceptLanguage.g:157:5: {...}? => ( ({...}? => (otherlv_6= 'version' ( (lv_version_7_0= ruleVersion ) ) ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getConceptAccess().getUnorderedGroup_3(), 1) ) {
            	        throw new FailedPredicateException(input, "ruleConcept", "getUnorderedGroupHelper().canSelect(grammarAccess.getConceptAccess().getUnorderedGroup_3(), 1)");
            	    }
            	    // InternalConceptLanguage.g:157:104: ( ({...}? => (otherlv_6= 'version' ( (lv_version_7_0= ruleVersion ) ) ) ) )
            	    // InternalConceptLanguage.g:158:6: ({...}? => (otherlv_6= 'version' ( (lv_version_7_0= ruleVersion ) ) ) )
            	    {

            	    						getUnorderedGroupHelper().select(grammarAccess.getConceptAccess().getUnorderedGroup_3(), 1);
            	    					
            	    // InternalConceptLanguage.g:161:9: ({...}? => (otherlv_6= 'version' ( (lv_version_7_0= ruleVersion ) ) ) )
            	    // InternalConceptLanguage.g:161:10: {...}? => (otherlv_6= 'version' ( (lv_version_7_0= ruleVersion ) ) )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleConcept", "true");
            	    }
            	    // InternalConceptLanguage.g:161:19: (otherlv_6= 'version' ( (lv_version_7_0= ruleVersion ) ) )
            	    // InternalConceptLanguage.g:161:20: otherlv_6= 'version' ( (lv_version_7_0= ruleVersion ) )
            	    {
            	    otherlv_6=(Token)match(input,13,FOLLOW_6); 

            	    									newLeafNode(otherlv_6, grammarAccess.getConceptAccess().getVersionKeyword_3_1_0());
            	    								
            	    // InternalConceptLanguage.g:165:9: ( (lv_version_7_0= ruleVersion ) )
            	    // InternalConceptLanguage.g:166:10: (lv_version_7_0= ruleVersion )
            	    {
            	    // InternalConceptLanguage.g:166:10: (lv_version_7_0= ruleVersion )
            	    // InternalConceptLanguage.g:167:11: lv_version_7_0= ruleVersion
            	    {

            	    											newCompositeNode(grammarAccess.getConceptAccess().getVersionVersionParserRuleCall_3_1_1_0());
            	    										
            	    pushFollow(FOLLOW_4);
            	    lv_version_7_0=ruleVersion();

            	    state._fsp--;


            	    											if (current==null) {
            	    												current = createModelElementForParent(grammarAccess.getConceptRule());
            	    											}
            	    											set(
            	    												current,
            	    												"version",
            	    												lv_version_7_0,
            	    												"de.dlr.sc.virsat.model.concept.ConceptLanguage.Version");
            	    											afterParserOrEnumRuleCall();
            	    										

            	    }


            	    }


            	    }


            	    }

            	     
            	    						getUnorderedGroupHelper().returnFromSelection(grammarAccess.getConceptAccess().getUnorderedGroup_3());
            	    					

            	    }


            	    }


            	    }
            	    break;
            	case 3 :
            	    // InternalConceptLanguage.g:190:4: ({...}? => ( ({...}? => (otherlv_8= 'description' ( (lv_description_9_0= ruleEString ) ) ) ) ) )
            	    {
            	    // InternalConceptLanguage.g:190:4: ({...}? => ( ({...}? => (otherlv_8= 'description' ( (lv_description_9_0= ruleEString ) ) ) ) ) )
            	    // InternalConceptLanguage.g:191:5: {...}? => ( ({...}? => (otherlv_8= 'description' ( (lv_description_9_0= ruleEString ) ) ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getConceptAccess().getUnorderedGroup_3(), 2) ) {
            	        throw new FailedPredicateException(input, "ruleConcept", "getUnorderedGroupHelper().canSelect(grammarAccess.getConceptAccess().getUnorderedGroup_3(), 2)");
            	    }
            	    // InternalConceptLanguage.g:191:104: ( ({...}? => (otherlv_8= 'description' ( (lv_description_9_0= ruleEString ) ) ) ) )
            	    // InternalConceptLanguage.g:192:6: ({...}? => (otherlv_8= 'description' ( (lv_description_9_0= ruleEString ) ) ) )
            	    {

            	    						getUnorderedGroupHelper().select(grammarAccess.getConceptAccess().getUnorderedGroup_3(), 2);
            	    					
            	    // InternalConceptLanguage.g:195:9: ({...}? => (otherlv_8= 'description' ( (lv_description_9_0= ruleEString ) ) ) )
            	    // InternalConceptLanguage.g:195:10: {...}? => (otherlv_8= 'description' ( (lv_description_9_0= ruleEString ) ) )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleConcept", "true");
            	    }
            	    // InternalConceptLanguage.g:195:19: (otherlv_8= 'description' ( (lv_description_9_0= ruleEString ) ) )
            	    // InternalConceptLanguage.g:195:20: otherlv_8= 'description' ( (lv_description_9_0= ruleEString ) )
            	    {
            	    otherlv_8=(Token)match(input,14,FOLLOW_5); 

            	    									newLeafNode(otherlv_8, grammarAccess.getConceptAccess().getDescriptionKeyword_3_2_0());
            	    								
            	    // InternalConceptLanguage.g:199:9: ( (lv_description_9_0= ruleEString ) )
            	    // InternalConceptLanguage.g:200:10: (lv_description_9_0= ruleEString )
            	    {
            	    // InternalConceptLanguage.g:200:10: (lv_description_9_0= ruleEString )
            	    // InternalConceptLanguage.g:201:11: lv_description_9_0= ruleEString
            	    {

            	    											newCompositeNode(grammarAccess.getConceptAccess().getDescriptionEStringParserRuleCall_3_2_1_0());
            	    										
            	    pushFollow(FOLLOW_4);
            	    lv_description_9_0=ruleEString();

            	    state._fsp--;


            	    											if (current==null) {
            	    												current = createModelElementForParent(grammarAccess.getConceptRule());
            	    											}
            	    											set(
            	    												current,
            	    												"description",
            	    												lv_description_9_0,
            	    												"de.dlr.sc.virsat.model.concept.ConceptLanguage.EString");
            	    											afterParserOrEnumRuleCall();
            	    										

            	    }


            	    }


            	    }


            	    }

            	     
            	    						getUnorderedGroupHelper().returnFromSelection(grammarAccess.getConceptAccess().getUnorderedGroup_3());
            	    					

            	    }


            	    }


            	    }
            	    break;
            	case 4 :
            	    // InternalConceptLanguage.g:224:4: ({...}? => ( ({...}? => ( (lv_DMF_10_0= 'hasDMF' ) ) ) ) )
            	    {
            	    // InternalConceptLanguage.g:224:4: ({...}? => ( ({...}? => ( (lv_DMF_10_0= 'hasDMF' ) ) ) ) )
            	    // InternalConceptLanguage.g:225:5: {...}? => ( ({...}? => ( (lv_DMF_10_0= 'hasDMF' ) ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getConceptAccess().getUnorderedGroup_3(), 3) ) {
            	        throw new FailedPredicateException(input, "ruleConcept", "getUnorderedGroupHelper().canSelect(grammarAccess.getConceptAccess().getUnorderedGroup_3(), 3)");
            	    }
            	    // InternalConceptLanguage.g:225:104: ( ({...}? => ( (lv_DMF_10_0= 'hasDMF' ) ) ) )
            	    // InternalConceptLanguage.g:226:6: ({...}? => ( (lv_DMF_10_0= 'hasDMF' ) ) )
            	    {

            	    						getUnorderedGroupHelper().select(grammarAccess.getConceptAccess().getUnorderedGroup_3(), 3);
            	    					
            	    // InternalConceptLanguage.g:229:9: ({...}? => ( (lv_DMF_10_0= 'hasDMF' ) ) )
            	    // InternalConceptLanguage.g:229:10: {...}? => ( (lv_DMF_10_0= 'hasDMF' ) )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleConcept", "true");
            	    }
            	    // InternalConceptLanguage.g:229:19: ( (lv_DMF_10_0= 'hasDMF' ) )
            	    // InternalConceptLanguage.g:229:20: (lv_DMF_10_0= 'hasDMF' )
            	    {
            	    // InternalConceptLanguage.g:229:20: (lv_DMF_10_0= 'hasDMF' )
            	    // InternalConceptLanguage.g:230:10: lv_DMF_10_0= 'hasDMF'
            	    {
            	    lv_DMF_10_0=(Token)match(input,15,FOLLOW_4); 

            	    										newLeafNode(lv_DMF_10_0, grammarAccess.getConceptAccess().getDMFHasDMFKeyword_3_3_0());
            	    									

            	    										if (current==null) {
            	    											current = createModelElement(grammarAccess.getConceptRule());
            	    										}
            	    										setWithLastConsumed(current, "DMF", true, "hasDMF");
            	    									

            	    }


            	    }


            	    }

            	     
            	    						getUnorderedGroupHelper().returnFromSelection(grammarAccess.getConceptAccess().getUnorderedGroup_3());
            	    					

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop1;
                }
            } while (true);


            }


            }

             
            				  getUnorderedGroupHelper().leave(grammarAccess.getConceptAccess().getUnorderedGroup_3());
            				

            }

            otherlv_11=(Token)match(input,16,FOLLOW_7); 

            			newLeafNode(otherlv_11, grammarAccess.getConceptAccess().getLeftCurlyBracketKeyword_4());
            		
            // InternalConceptLanguage.g:258:3: ( (lv_imports_12_0= ruleConceptImport ) )*
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( (LA2_0==37) ) {
                    alt2=1;
                }


                switch (alt2) {
            	case 1 :
            	    // InternalConceptLanguage.g:259:4: (lv_imports_12_0= ruleConceptImport )
            	    {
            	    // InternalConceptLanguage.g:259:4: (lv_imports_12_0= ruleConceptImport )
            	    // InternalConceptLanguage.g:260:5: lv_imports_12_0= ruleConceptImport
            	    {

            	    					newCompositeNode(grammarAccess.getConceptAccess().getImportsConceptImportParserRuleCall_5_0());
            	    				
            	    pushFollow(FOLLOW_7);
            	    lv_imports_12_0=ruleConceptImport();

            	    state._fsp--;


            	    					if (current==null) {
            	    						current = createModelElementForParent(grammarAccess.getConceptRule());
            	    					}
            	    					add(
            	    						current,
            	    						"imports",
            	    						lv_imports_12_0,
            	    						"de.dlr.sc.virsat.model.concept.ConceptLanguage.ConceptImport");
            	    					afterParserOrEnumRuleCall();
            	    				

            	    }


            	    }
            	    break;

            	default :
            	    break loop2;
                }
            } while (true);

            // InternalConceptLanguage.g:277:3: ( (lv_structuralElements_13_0= ruleStructuralElement ) )*
            loop3:
            do {
                int alt3=2;
                int LA3_0 = input.LA(1);

                if ( (LA3_0==18) ) {
                    alt3=1;
                }


                switch (alt3) {
            	case 1 :
            	    // InternalConceptLanguage.g:278:4: (lv_structuralElements_13_0= ruleStructuralElement )
            	    {
            	    // InternalConceptLanguage.g:278:4: (lv_structuralElements_13_0= ruleStructuralElement )
            	    // InternalConceptLanguage.g:279:5: lv_structuralElements_13_0= ruleStructuralElement
            	    {

            	    					newCompositeNode(grammarAccess.getConceptAccess().getStructuralElementsStructuralElementParserRuleCall_6_0());
            	    				
            	    pushFollow(FOLLOW_8);
            	    lv_structuralElements_13_0=ruleStructuralElement();

            	    state._fsp--;


            	    					if (current==null) {
            	    						current = createModelElementForParent(grammarAccess.getConceptRule());
            	    					}
            	    					add(
            	    						current,
            	    						"structuralElements",
            	    						lv_structuralElements_13_0,
            	    						"de.dlr.sc.virsat.model.concept.ConceptLanguage.StructuralElement");
            	    					afterParserOrEnumRuleCall();
            	    				

            	    }


            	    }
            	    break;

            	default :
            	    break loop3;
                }
            } while (true);

            // InternalConceptLanguage.g:296:3: ( (lv_relations_14_0= ruleARelation ) )*
            loop4:
            do {
                int alt4=2;
                int LA4_0 = input.LA(1);

                if ( (LA4_0==31) ) {
                    alt4=1;
                }


                switch (alt4) {
            	case 1 :
            	    // InternalConceptLanguage.g:297:4: (lv_relations_14_0= ruleARelation )
            	    {
            	    // InternalConceptLanguage.g:297:4: (lv_relations_14_0= ruleARelation )
            	    // InternalConceptLanguage.g:298:5: lv_relations_14_0= ruleARelation
            	    {

            	    					newCompositeNode(grammarAccess.getConceptAccess().getRelationsARelationParserRuleCall_7_0());
            	    				
            	    pushFollow(FOLLOW_9);
            	    lv_relations_14_0=ruleARelation();

            	    state._fsp--;


            	    					if (current==null) {
            	    						current = createModelElementForParent(grammarAccess.getConceptRule());
            	    					}
            	    					add(
            	    						current,
            	    						"relations",
            	    						lv_relations_14_0,
            	    						"de.dlr.sc.virsat.model.concept.ConceptLanguage.ARelation");
            	    					afterParserOrEnumRuleCall();
            	    				

            	    }


            	    }
            	    break;

            	default :
            	    break loop4;
                }
            } while (true);

            // InternalConceptLanguage.g:315:3: ( (lv_categories_15_0= ruleCategory ) )*
            loop5:
            do {
                int alt5=2;
                int LA5_0 = input.LA(1);

                if ( (LA5_0==34) ) {
                    alt5=1;
                }


                switch (alt5) {
            	case 1 :
            	    // InternalConceptLanguage.g:316:4: (lv_categories_15_0= ruleCategory )
            	    {
            	    // InternalConceptLanguage.g:316:4: (lv_categories_15_0= ruleCategory )
            	    // InternalConceptLanguage.g:317:5: lv_categories_15_0= ruleCategory
            	    {

            	    					newCompositeNode(grammarAccess.getConceptAccess().getCategoriesCategoryParserRuleCall_8_0());
            	    				
            	    pushFollow(FOLLOW_10);
            	    lv_categories_15_0=ruleCategory();

            	    state._fsp--;


            	    					if (current==null) {
            	    						current = createModelElementForParent(grammarAccess.getConceptRule());
            	    					}
            	    					add(
            	    						current,
            	    						"categories",
            	    						lv_categories_15_0,
            	    						"de.dlr.sc.virsat.model.concept.ConceptLanguage.Category");
            	    					afterParserOrEnumRuleCall();
            	    				

            	    }


            	    }
            	    break;

            	default :
            	    break loop5;
                }
            } while (true);

            otherlv_16=(Token)match(input,17,FOLLOW_2); 

            			newLeafNode(otherlv_16, grammarAccess.getConceptAccess().getRightCurlyBracketKeyword_9());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleConcept"


    // $ANTLR start "entryRuleStructuralElement"
    // InternalConceptLanguage.g:342:1: entryRuleStructuralElement returns [EObject current=null] : iv_ruleStructuralElement= ruleStructuralElement EOF ;
    public final EObject entryRuleStructuralElement() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleStructuralElement = null;


        try {
            // InternalConceptLanguage.g:342:58: (iv_ruleStructuralElement= ruleStructuralElement EOF )
            // InternalConceptLanguage.g:343:2: iv_ruleStructuralElement= ruleStructuralElement EOF
            {
             newCompositeNode(grammarAccess.getStructuralElementRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleStructuralElement=ruleStructuralElement();

            state._fsp--;

             current =iv_ruleStructuralElement; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleStructuralElement"


    // $ANTLR start "ruleStructuralElement"
    // InternalConceptLanguage.g:349:1: ruleStructuralElement returns [EObject current=null] : ( () otherlv_1= 'StructuralElement' ( (lv_name_2_0= RULE_ID ) ) ( ( ( ( ({...}? => ( ({...}? => (otherlv_4= 'shortname' ( (lv_shortName_5_0= RULE_ID ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_6= 'description' ( (lv_description_7_0= ruleEString ) ) ) ) ) ) )* ) ) ) otherlv_8= '{' ( ( ( ( ({...}? => ( ({...}? => ( ( (lv_isRootStructuralElement_10_0= 'IsRootStructuralElement' ) ) otherlv_11= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_12= 'Inherits' otherlv_13= 'From' ( (otherlv_14= '[' ( ( ruleQualifiedName ) ) (otherlv_16= ',' ( ( ruleQualifiedName ) ) )* otherlv_18= ']' ) | ( (lv_isCanInheritFromAll_19_0= 'All' ) ) ) otherlv_20= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_21= 'Applicable' otherlv_22= 'For' ( (otherlv_23= '[' ( ( ruleQualifiedName ) ) (otherlv_25= ',' ( ( ruleQualifiedName ) ) )* otherlv_27= ']' ) | ( (lv_isApplicableForAll_28_0= 'All' ) ) ) otherlv_29= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_30= 'Cardinality' ( (lv_cardinality_31_0= RULE_INT ) ) otherlv_32= ';' ) ) ) ) )* ) ) ) otherlv_33= '}' ) ;
    public final EObject ruleStructuralElement() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token lv_name_2_0=null;
        Token otherlv_4=null;
        Token lv_shortName_5_0=null;
        Token otherlv_6=null;
        Token otherlv_8=null;
        Token lv_isRootStructuralElement_10_0=null;
        Token otherlv_11=null;
        Token otherlv_12=null;
        Token otherlv_13=null;
        Token otherlv_14=null;
        Token otherlv_16=null;
        Token otherlv_18=null;
        Token lv_isCanInheritFromAll_19_0=null;
        Token otherlv_20=null;
        Token otherlv_21=null;
        Token otherlv_22=null;
        Token otherlv_23=null;
        Token otherlv_25=null;
        Token otherlv_27=null;
        Token lv_isApplicableForAll_28_0=null;
        Token otherlv_29=null;
        Token otherlv_30=null;
        Token lv_cardinality_31_0=null;
        Token otherlv_32=null;
        Token otherlv_33=null;
        AntlrDatatypeRuleToken lv_description_7_0 = null;



        	enterRule();

        try {
            // InternalConceptLanguage.g:355:2: ( ( () otherlv_1= 'StructuralElement' ( (lv_name_2_0= RULE_ID ) ) ( ( ( ( ({...}? => ( ({...}? => (otherlv_4= 'shortname' ( (lv_shortName_5_0= RULE_ID ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_6= 'description' ( (lv_description_7_0= ruleEString ) ) ) ) ) ) )* ) ) ) otherlv_8= '{' ( ( ( ( ({...}? => ( ({...}? => ( ( (lv_isRootStructuralElement_10_0= 'IsRootStructuralElement' ) ) otherlv_11= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_12= 'Inherits' otherlv_13= 'From' ( (otherlv_14= '[' ( ( ruleQualifiedName ) ) (otherlv_16= ',' ( ( ruleQualifiedName ) ) )* otherlv_18= ']' ) | ( (lv_isCanInheritFromAll_19_0= 'All' ) ) ) otherlv_20= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_21= 'Applicable' otherlv_22= 'For' ( (otherlv_23= '[' ( ( ruleQualifiedName ) ) (otherlv_25= ',' ( ( ruleQualifiedName ) ) )* otherlv_27= ']' ) | ( (lv_isApplicableForAll_28_0= 'All' ) ) ) otherlv_29= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_30= 'Cardinality' ( (lv_cardinality_31_0= RULE_INT ) ) otherlv_32= ';' ) ) ) ) )* ) ) ) otherlv_33= '}' ) )
            // InternalConceptLanguage.g:356:2: ( () otherlv_1= 'StructuralElement' ( (lv_name_2_0= RULE_ID ) ) ( ( ( ( ({...}? => ( ({...}? => (otherlv_4= 'shortname' ( (lv_shortName_5_0= RULE_ID ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_6= 'description' ( (lv_description_7_0= ruleEString ) ) ) ) ) ) )* ) ) ) otherlv_8= '{' ( ( ( ( ({...}? => ( ({...}? => ( ( (lv_isRootStructuralElement_10_0= 'IsRootStructuralElement' ) ) otherlv_11= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_12= 'Inherits' otherlv_13= 'From' ( (otherlv_14= '[' ( ( ruleQualifiedName ) ) (otherlv_16= ',' ( ( ruleQualifiedName ) ) )* otherlv_18= ']' ) | ( (lv_isCanInheritFromAll_19_0= 'All' ) ) ) otherlv_20= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_21= 'Applicable' otherlv_22= 'For' ( (otherlv_23= '[' ( ( ruleQualifiedName ) ) (otherlv_25= ',' ( ( ruleQualifiedName ) ) )* otherlv_27= ']' ) | ( (lv_isApplicableForAll_28_0= 'All' ) ) ) otherlv_29= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_30= 'Cardinality' ( (lv_cardinality_31_0= RULE_INT ) ) otherlv_32= ';' ) ) ) ) )* ) ) ) otherlv_33= '}' )
            {
            // InternalConceptLanguage.g:356:2: ( () otherlv_1= 'StructuralElement' ( (lv_name_2_0= RULE_ID ) ) ( ( ( ( ({...}? => ( ({...}? => (otherlv_4= 'shortname' ( (lv_shortName_5_0= RULE_ID ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_6= 'description' ( (lv_description_7_0= ruleEString ) ) ) ) ) ) )* ) ) ) otherlv_8= '{' ( ( ( ( ({...}? => ( ({...}? => ( ( (lv_isRootStructuralElement_10_0= 'IsRootStructuralElement' ) ) otherlv_11= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_12= 'Inherits' otherlv_13= 'From' ( (otherlv_14= '[' ( ( ruleQualifiedName ) ) (otherlv_16= ',' ( ( ruleQualifiedName ) ) )* otherlv_18= ']' ) | ( (lv_isCanInheritFromAll_19_0= 'All' ) ) ) otherlv_20= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_21= 'Applicable' otherlv_22= 'For' ( (otherlv_23= '[' ( ( ruleQualifiedName ) ) (otherlv_25= ',' ( ( ruleQualifiedName ) ) )* otherlv_27= ']' ) | ( (lv_isApplicableForAll_28_0= 'All' ) ) ) otherlv_29= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_30= 'Cardinality' ( (lv_cardinality_31_0= RULE_INT ) ) otherlv_32= ';' ) ) ) ) )* ) ) ) otherlv_33= '}' )
            // InternalConceptLanguage.g:357:3: () otherlv_1= 'StructuralElement' ( (lv_name_2_0= RULE_ID ) ) ( ( ( ( ({...}? => ( ({...}? => (otherlv_4= 'shortname' ( (lv_shortName_5_0= RULE_ID ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_6= 'description' ( (lv_description_7_0= ruleEString ) ) ) ) ) ) )* ) ) ) otherlv_8= '{' ( ( ( ( ({...}? => ( ({...}? => ( ( (lv_isRootStructuralElement_10_0= 'IsRootStructuralElement' ) ) otherlv_11= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_12= 'Inherits' otherlv_13= 'From' ( (otherlv_14= '[' ( ( ruleQualifiedName ) ) (otherlv_16= ',' ( ( ruleQualifiedName ) ) )* otherlv_18= ']' ) | ( (lv_isCanInheritFromAll_19_0= 'All' ) ) ) otherlv_20= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_21= 'Applicable' otherlv_22= 'For' ( (otherlv_23= '[' ( ( ruleQualifiedName ) ) (otherlv_25= ',' ( ( ruleQualifiedName ) ) )* otherlv_27= ']' ) | ( (lv_isApplicableForAll_28_0= 'All' ) ) ) otherlv_29= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_30= 'Cardinality' ( (lv_cardinality_31_0= RULE_INT ) ) otherlv_32= ';' ) ) ) ) )* ) ) ) otherlv_33= '}'
            {
            // InternalConceptLanguage.g:357:3: ()
            // InternalConceptLanguage.g:358:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getStructuralElementAccess().getStructuralElementAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,18,FOLLOW_3); 

            			newLeafNode(otherlv_1, grammarAccess.getStructuralElementAccess().getStructuralElementKeyword_1());
            		
            // InternalConceptLanguage.g:368:3: ( (lv_name_2_0= RULE_ID ) )
            // InternalConceptLanguage.g:369:4: (lv_name_2_0= RULE_ID )
            {
            // InternalConceptLanguage.g:369:4: (lv_name_2_0= RULE_ID )
            // InternalConceptLanguage.g:370:5: lv_name_2_0= RULE_ID
            {
            lv_name_2_0=(Token)match(input,RULE_ID,FOLLOW_11); 

            					newLeafNode(lv_name_2_0, grammarAccess.getStructuralElementAccess().getNameIDTerminalRuleCall_2_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getStructuralElementRule());
            					}
            					setWithLastConsumed(
            						current,
            						"name",
            						lv_name_2_0,
            						"org.eclipse.xtext.common.Terminals.ID");
            				

            }


            }

            // InternalConceptLanguage.g:386:3: ( ( ( ( ({...}? => ( ({...}? => (otherlv_4= 'shortname' ( (lv_shortName_5_0= RULE_ID ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_6= 'description' ( (lv_description_7_0= ruleEString ) ) ) ) ) ) )* ) ) )
            // InternalConceptLanguage.g:387:4: ( ( ( ({...}? => ( ({...}? => (otherlv_4= 'shortname' ( (lv_shortName_5_0= RULE_ID ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_6= 'description' ( (lv_description_7_0= ruleEString ) ) ) ) ) ) )* ) )
            {
            // InternalConceptLanguage.g:387:4: ( ( ( ({...}? => ( ({...}? => (otherlv_4= 'shortname' ( (lv_shortName_5_0= RULE_ID ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_6= 'description' ( (lv_description_7_0= ruleEString ) ) ) ) ) ) )* ) )
            // InternalConceptLanguage.g:388:5: ( ( ({...}? => ( ({...}? => (otherlv_4= 'shortname' ( (lv_shortName_5_0= RULE_ID ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_6= 'description' ( (lv_description_7_0= ruleEString ) ) ) ) ) ) )* )
            {
             
            				  getUnorderedGroupHelper().enter(grammarAccess.getStructuralElementAccess().getUnorderedGroup_3());
            				
            // InternalConceptLanguage.g:391:5: ( ( ({...}? => ( ({...}? => (otherlv_4= 'shortname' ( (lv_shortName_5_0= RULE_ID ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_6= 'description' ( (lv_description_7_0= ruleEString ) ) ) ) ) ) )* )
            // InternalConceptLanguage.g:392:6: ( ({...}? => ( ({...}? => (otherlv_4= 'shortname' ( (lv_shortName_5_0= RULE_ID ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_6= 'description' ( (lv_description_7_0= ruleEString ) ) ) ) ) ) )*
            {
            // InternalConceptLanguage.g:392:6: ( ({...}? => ( ({...}? => (otherlv_4= 'shortname' ( (lv_shortName_5_0= RULE_ID ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_6= 'description' ( (lv_description_7_0= ruleEString ) ) ) ) ) ) )*
            loop6:
            do {
                int alt6=3;
                int LA6_0 = input.LA(1);

                if ( LA6_0 == 19 && getUnorderedGroupHelper().canSelect(grammarAccess.getStructuralElementAccess().getUnorderedGroup_3(), 0) ) {
                    alt6=1;
                }
                else if ( LA6_0 == 14 && getUnorderedGroupHelper().canSelect(grammarAccess.getStructuralElementAccess().getUnorderedGroup_3(), 1) ) {
                    alt6=2;
                }


                switch (alt6) {
            	case 1 :
            	    // InternalConceptLanguage.g:393:4: ({...}? => ( ({...}? => (otherlv_4= 'shortname' ( (lv_shortName_5_0= RULE_ID ) ) ) ) ) )
            	    {
            	    // InternalConceptLanguage.g:393:4: ({...}? => ( ({...}? => (otherlv_4= 'shortname' ( (lv_shortName_5_0= RULE_ID ) ) ) ) ) )
            	    // InternalConceptLanguage.g:394:5: {...}? => ( ({...}? => (otherlv_4= 'shortname' ( (lv_shortName_5_0= RULE_ID ) ) ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getStructuralElementAccess().getUnorderedGroup_3(), 0) ) {
            	        throw new FailedPredicateException(input, "ruleStructuralElement", "getUnorderedGroupHelper().canSelect(grammarAccess.getStructuralElementAccess().getUnorderedGroup_3(), 0)");
            	    }
            	    // InternalConceptLanguage.g:394:114: ( ({...}? => (otherlv_4= 'shortname' ( (lv_shortName_5_0= RULE_ID ) ) ) ) )
            	    // InternalConceptLanguage.g:395:6: ({...}? => (otherlv_4= 'shortname' ( (lv_shortName_5_0= RULE_ID ) ) ) )
            	    {

            	    						getUnorderedGroupHelper().select(grammarAccess.getStructuralElementAccess().getUnorderedGroup_3(), 0);
            	    					
            	    // InternalConceptLanguage.g:398:9: ({...}? => (otherlv_4= 'shortname' ( (lv_shortName_5_0= RULE_ID ) ) ) )
            	    // InternalConceptLanguage.g:398:10: {...}? => (otherlv_4= 'shortname' ( (lv_shortName_5_0= RULE_ID ) ) )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleStructuralElement", "true");
            	    }
            	    // InternalConceptLanguage.g:398:19: (otherlv_4= 'shortname' ( (lv_shortName_5_0= RULE_ID ) ) )
            	    // InternalConceptLanguage.g:398:20: otherlv_4= 'shortname' ( (lv_shortName_5_0= RULE_ID ) )
            	    {
            	    otherlv_4=(Token)match(input,19,FOLLOW_3); 

            	    									newLeafNode(otherlv_4, grammarAccess.getStructuralElementAccess().getShortnameKeyword_3_0_0());
            	    								
            	    // InternalConceptLanguage.g:402:9: ( (lv_shortName_5_0= RULE_ID ) )
            	    // InternalConceptLanguage.g:403:10: (lv_shortName_5_0= RULE_ID )
            	    {
            	    // InternalConceptLanguage.g:403:10: (lv_shortName_5_0= RULE_ID )
            	    // InternalConceptLanguage.g:404:11: lv_shortName_5_0= RULE_ID
            	    {
            	    lv_shortName_5_0=(Token)match(input,RULE_ID,FOLLOW_11); 

            	    											newLeafNode(lv_shortName_5_0, grammarAccess.getStructuralElementAccess().getShortNameIDTerminalRuleCall_3_0_1_0());
            	    										

            	    											if (current==null) {
            	    												current = createModelElement(grammarAccess.getStructuralElementRule());
            	    											}
            	    											setWithLastConsumed(
            	    												current,
            	    												"shortName",
            	    												lv_shortName_5_0,
            	    												"org.eclipse.xtext.common.Terminals.ID");
            	    										

            	    }


            	    }


            	    }


            	    }

            	     
            	    						getUnorderedGroupHelper().returnFromSelection(grammarAccess.getStructuralElementAccess().getUnorderedGroup_3());
            	    					

            	    }


            	    }


            	    }
            	    break;
            	case 2 :
            	    // InternalConceptLanguage.g:426:4: ({...}? => ( ({...}? => (otherlv_6= 'description' ( (lv_description_7_0= ruleEString ) ) ) ) ) )
            	    {
            	    // InternalConceptLanguage.g:426:4: ({...}? => ( ({...}? => (otherlv_6= 'description' ( (lv_description_7_0= ruleEString ) ) ) ) ) )
            	    // InternalConceptLanguage.g:427:5: {...}? => ( ({...}? => (otherlv_6= 'description' ( (lv_description_7_0= ruleEString ) ) ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getStructuralElementAccess().getUnorderedGroup_3(), 1) ) {
            	        throw new FailedPredicateException(input, "ruleStructuralElement", "getUnorderedGroupHelper().canSelect(grammarAccess.getStructuralElementAccess().getUnorderedGroup_3(), 1)");
            	    }
            	    // InternalConceptLanguage.g:427:114: ( ({...}? => (otherlv_6= 'description' ( (lv_description_7_0= ruleEString ) ) ) ) )
            	    // InternalConceptLanguage.g:428:6: ({...}? => (otherlv_6= 'description' ( (lv_description_7_0= ruleEString ) ) ) )
            	    {

            	    						getUnorderedGroupHelper().select(grammarAccess.getStructuralElementAccess().getUnorderedGroup_3(), 1);
            	    					
            	    // InternalConceptLanguage.g:431:9: ({...}? => (otherlv_6= 'description' ( (lv_description_7_0= ruleEString ) ) ) )
            	    // InternalConceptLanguage.g:431:10: {...}? => (otherlv_6= 'description' ( (lv_description_7_0= ruleEString ) ) )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleStructuralElement", "true");
            	    }
            	    // InternalConceptLanguage.g:431:19: (otherlv_6= 'description' ( (lv_description_7_0= ruleEString ) ) )
            	    // InternalConceptLanguage.g:431:20: otherlv_6= 'description' ( (lv_description_7_0= ruleEString ) )
            	    {
            	    otherlv_6=(Token)match(input,14,FOLLOW_5); 

            	    									newLeafNode(otherlv_6, grammarAccess.getStructuralElementAccess().getDescriptionKeyword_3_1_0());
            	    								
            	    // InternalConceptLanguage.g:435:9: ( (lv_description_7_0= ruleEString ) )
            	    // InternalConceptLanguage.g:436:10: (lv_description_7_0= ruleEString )
            	    {
            	    // InternalConceptLanguage.g:436:10: (lv_description_7_0= ruleEString )
            	    // InternalConceptLanguage.g:437:11: lv_description_7_0= ruleEString
            	    {

            	    											newCompositeNode(grammarAccess.getStructuralElementAccess().getDescriptionEStringParserRuleCall_3_1_1_0());
            	    										
            	    pushFollow(FOLLOW_11);
            	    lv_description_7_0=ruleEString();

            	    state._fsp--;


            	    											if (current==null) {
            	    												current = createModelElementForParent(grammarAccess.getStructuralElementRule());
            	    											}
            	    											set(
            	    												current,
            	    												"description",
            	    												lv_description_7_0,
            	    												"de.dlr.sc.virsat.model.concept.ConceptLanguage.EString");
            	    											afterParserOrEnumRuleCall();
            	    										

            	    }


            	    }


            	    }


            	    }

            	     
            	    						getUnorderedGroupHelper().returnFromSelection(grammarAccess.getStructuralElementAccess().getUnorderedGroup_3());
            	    					

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop6;
                }
            } while (true);


            }


            }

             
            				  getUnorderedGroupHelper().leave(grammarAccess.getStructuralElementAccess().getUnorderedGroup_3());
            				

            }

            otherlv_8=(Token)match(input,16,FOLLOW_12); 

            			newLeafNode(otherlv_8, grammarAccess.getStructuralElementAccess().getLeftCurlyBracketKeyword_4());
            		
            // InternalConceptLanguage.g:471:3: ( ( ( ( ({...}? => ( ({...}? => ( ( (lv_isRootStructuralElement_10_0= 'IsRootStructuralElement' ) ) otherlv_11= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_12= 'Inherits' otherlv_13= 'From' ( (otherlv_14= '[' ( ( ruleQualifiedName ) ) (otherlv_16= ',' ( ( ruleQualifiedName ) ) )* otherlv_18= ']' ) | ( (lv_isCanInheritFromAll_19_0= 'All' ) ) ) otherlv_20= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_21= 'Applicable' otherlv_22= 'For' ( (otherlv_23= '[' ( ( ruleQualifiedName ) ) (otherlv_25= ',' ( ( ruleQualifiedName ) ) )* otherlv_27= ']' ) | ( (lv_isApplicableForAll_28_0= 'All' ) ) ) otherlv_29= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_30= 'Cardinality' ( (lv_cardinality_31_0= RULE_INT ) ) otherlv_32= ';' ) ) ) ) )* ) ) )
            // InternalConceptLanguage.g:472:4: ( ( ( ({...}? => ( ({...}? => ( ( (lv_isRootStructuralElement_10_0= 'IsRootStructuralElement' ) ) otherlv_11= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_12= 'Inherits' otherlv_13= 'From' ( (otherlv_14= '[' ( ( ruleQualifiedName ) ) (otherlv_16= ',' ( ( ruleQualifiedName ) ) )* otherlv_18= ']' ) | ( (lv_isCanInheritFromAll_19_0= 'All' ) ) ) otherlv_20= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_21= 'Applicable' otherlv_22= 'For' ( (otherlv_23= '[' ( ( ruleQualifiedName ) ) (otherlv_25= ',' ( ( ruleQualifiedName ) ) )* otherlv_27= ']' ) | ( (lv_isApplicableForAll_28_0= 'All' ) ) ) otherlv_29= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_30= 'Cardinality' ( (lv_cardinality_31_0= RULE_INT ) ) otherlv_32= ';' ) ) ) ) )* ) )
            {
            // InternalConceptLanguage.g:472:4: ( ( ( ({...}? => ( ({...}? => ( ( (lv_isRootStructuralElement_10_0= 'IsRootStructuralElement' ) ) otherlv_11= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_12= 'Inherits' otherlv_13= 'From' ( (otherlv_14= '[' ( ( ruleQualifiedName ) ) (otherlv_16= ',' ( ( ruleQualifiedName ) ) )* otherlv_18= ']' ) | ( (lv_isCanInheritFromAll_19_0= 'All' ) ) ) otherlv_20= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_21= 'Applicable' otherlv_22= 'For' ( (otherlv_23= '[' ( ( ruleQualifiedName ) ) (otherlv_25= ',' ( ( ruleQualifiedName ) ) )* otherlv_27= ']' ) | ( (lv_isApplicableForAll_28_0= 'All' ) ) ) otherlv_29= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_30= 'Cardinality' ( (lv_cardinality_31_0= RULE_INT ) ) otherlv_32= ';' ) ) ) ) )* ) )
            // InternalConceptLanguage.g:473:5: ( ( ({...}? => ( ({...}? => ( ( (lv_isRootStructuralElement_10_0= 'IsRootStructuralElement' ) ) otherlv_11= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_12= 'Inherits' otherlv_13= 'From' ( (otherlv_14= '[' ( ( ruleQualifiedName ) ) (otherlv_16= ',' ( ( ruleQualifiedName ) ) )* otherlv_18= ']' ) | ( (lv_isCanInheritFromAll_19_0= 'All' ) ) ) otherlv_20= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_21= 'Applicable' otherlv_22= 'For' ( (otherlv_23= '[' ( ( ruleQualifiedName ) ) (otherlv_25= ',' ( ( ruleQualifiedName ) ) )* otherlv_27= ']' ) | ( (lv_isApplicableForAll_28_0= 'All' ) ) ) otherlv_29= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_30= 'Cardinality' ( (lv_cardinality_31_0= RULE_INT ) ) otherlv_32= ';' ) ) ) ) )* )
            {
             
            				  getUnorderedGroupHelper().enter(grammarAccess.getStructuralElementAccess().getUnorderedGroup_5());
            				
            // InternalConceptLanguage.g:476:5: ( ( ({...}? => ( ({...}? => ( ( (lv_isRootStructuralElement_10_0= 'IsRootStructuralElement' ) ) otherlv_11= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_12= 'Inherits' otherlv_13= 'From' ( (otherlv_14= '[' ( ( ruleQualifiedName ) ) (otherlv_16= ',' ( ( ruleQualifiedName ) ) )* otherlv_18= ']' ) | ( (lv_isCanInheritFromAll_19_0= 'All' ) ) ) otherlv_20= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_21= 'Applicable' otherlv_22= 'For' ( (otherlv_23= '[' ( ( ruleQualifiedName ) ) (otherlv_25= ',' ( ( ruleQualifiedName ) ) )* otherlv_27= ']' ) | ( (lv_isApplicableForAll_28_0= 'All' ) ) ) otherlv_29= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_30= 'Cardinality' ( (lv_cardinality_31_0= RULE_INT ) ) otherlv_32= ';' ) ) ) ) )* )
            // InternalConceptLanguage.g:477:6: ( ({...}? => ( ({...}? => ( ( (lv_isRootStructuralElement_10_0= 'IsRootStructuralElement' ) ) otherlv_11= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_12= 'Inherits' otherlv_13= 'From' ( (otherlv_14= '[' ( ( ruleQualifiedName ) ) (otherlv_16= ',' ( ( ruleQualifiedName ) ) )* otherlv_18= ']' ) | ( (lv_isCanInheritFromAll_19_0= 'All' ) ) ) otherlv_20= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_21= 'Applicable' otherlv_22= 'For' ( (otherlv_23= '[' ( ( ruleQualifiedName ) ) (otherlv_25= ',' ( ( ruleQualifiedName ) ) )* otherlv_27= ']' ) | ( (lv_isApplicableForAll_28_0= 'All' ) ) ) otherlv_29= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_30= 'Cardinality' ( (lv_cardinality_31_0= RULE_INT ) ) otherlv_32= ';' ) ) ) ) )*
            {
            // InternalConceptLanguage.g:477:6: ( ({...}? => ( ({...}? => ( ( (lv_isRootStructuralElement_10_0= 'IsRootStructuralElement' ) ) otherlv_11= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_12= 'Inherits' otherlv_13= 'From' ( (otherlv_14= '[' ( ( ruleQualifiedName ) ) (otherlv_16= ',' ( ( ruleQualifiedName ) ) )* otherlv_18= ']' ) | ( (lv_isCanInheritFromAll_19_0= 'All' ) ) ) otherlv_20= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_21= 'Applicable' otherlv_22= 'For' ( (otherlv_23= '[' ( ( ruleQualifiedName ) ) (otherlv_25= ',' ( ( ruleQualifiedName ) ) )* otherlv_27= ']' ) | ( (lv_isApplicableForAll_28_0= 'All' ) ) ) otherlv_29= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_30= 'Cardinality' ( (lv_cardinality_31_0= RULE_INT ) ) otherlv_32= ';' ) ) ) ) )*
            loop11:
            do {
                int alt11=5;
                int LA11_0 = input.LA(1);

                if ( LA11_0 == 20 && getUnorderedGroupHelper().canSelect(grammarAccess.getStructuralElementAccess().getUnorderedGroup_5(), 0) ) {
                    alt11=1;
                }
                else if ( LA11_0 == 22 && getUnorderedGroupHelper().canSelect(grammarAccess.getStructuralElementAccess().getUnorderedGroup_5(), 1) ) {
                    alt11=2;
                }
                else if ( LA11_0 == 28 && getUnorderedGroupHelper().canSelect(grammarAccess.getStructuralElementAccess().getUnorderedGroup_5(), 2) ) {
                    alt11=3;
                }
                else if ( LA11_0 == 30 && getUnorderedGroupHelper().canSelect(grammarAccess.getStructuralElementAccess().getUnorderedGroup_5(), 3) ) {
                    alt11=4;
                }


                switch (alt11) {
            	case 1 :
            	    // InternalConceptLanguage.g:478:4: ({...}? => ( ({...}? => ( ( (lv_isRootStructuralElement_10_0= 'IsRootStructuralElement' ) ) otherlv_11= ';' ) ) ) )
            	    {
            	    // InternalConceptLanguage.g:478:4: ({...}? => ( ({...}? => ( ( (lv_isRootStructuralElement_10_0= 'IsRootStructuralElement' ) ) otherlv_11= ';' ) ) ) )
            	    // InternalConceptLanguage.g:479:5: {...}? => ( ({...}? => ( ( (lv_isRootStructuralElement_10_0= 'IsRootStructuralElement' ) ) otherlv_11= ';' ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getStructuralElementAccess().getUnorderedGroup_5(), 0) ) {
            	        throw new FailedPredicateException(input, "ruleStructuralElement", "getUnorderedGroupHelper().canSelect(grammarAccess.getStructuralElementAccess().getUnorderedGroup_5(), 0)");
            	    }
            	    // InternalConceptLanguage.g:479:114: ( ({...}? => ( ( (lv_isRootStructuralElement_10_0= 'IsRootStructuralElement' ) ) otherlv_11= ';' ) ) )
            	    // InternalConceptLanguage.g:480:6: ({...}? => ( ( (lv_isRootStructuralElement_10_0= 'IsRootStructuralElement' ) ) otherlv_11= ';' ) )
            	    {

            	    						getUnorderedGroupHelper().select(grammarAccess.getStructuralElementAccess().getUnorderedGroup_5(), 0);
            	    					
            	    // InternalConceptLanguage.g:483:9: ({...}? => ( ( (lv_isRootStructuralElement_10_0= 'IsRootStructuralElement' ) ) otherlv_11= ';' ) )
            	    // InternalConceptLanguage.g:483:10: {...}? => ( ( (lv_isRootStructuralElement_10_0= 'IsRootStructuralElement' ) ) otherlv_11= ';' )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleStructuralElement", "true");
            	    }
            	    // InternalConceptLanguage.g:483:19: ( ( (lv_isRootStructuralElement_10_0= 'IsRootStructuralElement' ) ) otherlv_11= ';' )
            	    // InternalConceptLanguage.g:483:20: ( (lv_isRootStructuralElement_10_0= 'IsRootStructuralElement' ) ) otherlv_11= ';'
            	    {
            	    // InternalConceptLanguage.g:483:20: ( (lv_isRootStructuralElement_10_0= 'IsRootStructuralElement' ) )
            	    // InternalConceptLanguage.g:484:10: (lv_isRootStructuralElement_10_0= 'IsRootStructuralElement' )
            	    {
            	    // InternalConceptLanguage.g:484:10: (lv_isRootStructuralElement_10_0= 'IsRootStructuralElement' )
            	    // InternalConceptLanguage.g:485:11: lv_isRootStructuralElement_10_0= 'IsRootStructuralElement'
            	    {
            	    lv_isRootStructuralElement_10_0=(Token)match(input,20,FOLLOW_13); 

            	    											newLeafNode(lv_isRootStructuralElement_10_0, grammarAccess.getStructuralElementAccess().getIsRootStructuralElementIsRootStructuralElementKeyword_5_0_0_0());
            	    										

            	    											if (current==null) {
            	    												current = createModelElement(grammarAccess.getStructuralElementRule());
            	    											}
            	    											setWithLastConsumed(current, "isRootStructuralElement", true, "IsRootStructuralElement");
            	    										

            	    }


            	    }

            	    otherlv_11=(Token)match(input,21,FOLLOW_12); 

            	    									newLeafNode(otherlv_11, grammarAccess.getStructuralElementAccess().getSemicolonKeyword_5_0_1());
            	    								

            	    }


            	    }

            	     
            	    						getUnorderedGroupHelper().returnFromSelection(grammarAccess.getStructuralElementAccess().getUnorderedGroup_5());
            	    					

            	    }


            	    }


            	    }
            	    break;
            	case 2 :
            	    // InternalConceptLanguage.g:507:4: ({...}? => ( ({...}? => (otherlv_12= 'Inherits' otherlv_13= 'From' ( (otherlv_14= '[' ( ( ruleQualifiedName ) ) (otherlv_16= ',' ( ( ruleQualifiedName ) ) )* otherlv_18= ']' ) | ( (lv_isCanInheritFromAll_19_0= 'All' ) ) ) otherlv_20= ';' ) ) ) )
            	    {
            	    // InternalConceptLanguage.g:507:4: ({...}? => ( ({...}? => (otherlv_12= 'Inherits' otherlv_13= 'From' ( (otherlv_14= '[' ( ( ruleQualifiedName ) ) (otherlv_16= ',' ( ( ruleQualifiedName ) ) )* otherlv_18= ']' ) | ( (lv_isCanInheritFromAll_19_0= 'All' ) ) ) otherlv_20= ';' ) ) ) )
            	    // InternalConceptLanguage.g:508:5: {...}? => ( ({...}? => (otherlv_12= 'Inherits' otherlv_13= 'From' ( (otherlv_14= '[' ( ( ruleQualifiedName ) ) (otherlv_16= ',' ( ( ruleQualifiedName ) ) )* otherlv_18= ']' ) | ( (lv_isCanInheritFromAll_19_0= 'All' ) ) ) otherlv_20= ';' ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getStructuralElementAccess().getUnorderedGroup_5(), 1) ) {
            	        throw new FailedPredicateException(input, "ruleStructuralElement", "getUnorderedGroupHelper().canSelect(grammarAccess.getStructuralElementAccess().getUnorderedGroup_5(), 1)");
            	    }
            	    // InternalConceptLanguage.g:508:114: ( ({...}? => (otherlv_12= 'Inherits' otherlv_13= 'From' ( (otherlv_14= '[' ( ( ruleQualifiedName ) ) (otherlv_16= ',' ( ( ruleQualifiedName ) ) )* otherlv_18= ']' ) | ( (lv_isCanInheritFromAll_19_0= 'All' ) ) ) otherlv_20= ';' ) ) )
            	    // InternalConceptLanguage.g:509:6: ({...}? => (otherlv_12= 'Inherits' otherlv_13= 'From' ( (otherlv_14= '[' ( ( ruleQualifiedName ) ) (otherlv_16= ',' ( ( ruleQualifiedName ) ) )* otherlv_18= ']' ) | ( (lv_isCanInheritFromAll_19_0= 'All' ) ) ) otherlv_20= ';' ) )
            	    {

            	    						getUnorderedGroupHelper().select(grammarAccess.getStructuralElementAccess().getUnorderedGroup_5(), 1);
            	    					
            	    // InternalConceptLanguage.g:512:9: ({...}? => (otherlv_12= 'Inherits' otherlv_13= 'From' ( (otherlv_14= '[' ( ( ruleQualifiedName ) ) (otherlv_16= ',' ( ( ruleQualifiedName ) ) )* otherlv_18= ']' ) | ( (lv_isCanInheritFromAll_19_0= 'All' ) ) ) otherlv_20= ';' ) )
            	    // InternalConceptLanguage.g:512:10: {...}? => (otherlv_12= 'Inherits' otherlv_13= 'From' ( (otherlv_14= '[' ( ( ruleQualifiedName ) ) (otherlv_16= ',' ( ( ruleQualifiedName ) ) )* otherlv_18= ']' ) | ( (lv_isCanInheritFromAll_19_0= 'All' ) ) ) otherlv_20= ';' )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleStructuralElement", "true");
            	    }
            	    // InternalConceptLanguage.g:512:19: (otherlv_12= 'Inherits' otherlv_13= 'From' ( (otherlv_14= '[' ( ( ruleQualifiedName ) ) (otherlv_16= ',' ( ( ruleQualifiedName ) ) )* otherlv_18= ']' ) | ( (lv_isCanInheritFromAll_19_0= 'All' ) ) ) otherlv_20= ';' )
            	    // InternalConceptLanguage.g:512:20: otherlv_12= 'Inherits' otherlv_13= 'From' ( (otherlv_14= '[' ( ( ruleQualifiedName ) ) (otherlv_16= ',' ( ( ruleQualifiedName ) ) )* otherlv_18= ']' ) | ( (lv_isCanInheritFromAll_19_0= 'All' ) ) ) otherlv_20= ';'
            	    {
            	    otherlv_12=(Token)match(input,22,FOLLOW_14); 

            	    									newLeafNode(otherlv_12, grammarAccess.getStructuralElementAccess().getInheritsKeyword_5_1_0());
            	    								
            	    otherlv_13=(Token)match(input,23,FOLLOW_15); 

            	    									newLeafNode(otherlv_13, grammarAccess.getStructuralElementAccess().getFromKeyword_5_1_1());
            	    								
            	    // InternalConceptLanguage.g:520:9: ( (otherlv_14= '[' ( ( ruleQualifiedName ) ) (otherlv_16= ',' ( ( ruleQualifiedName ) ) )* otherlv_18= ']' ) | ( (lv_isCanInheritFromAll_19_0= 'All' ) ) )
            	    int alt8=2;
            	    int LA8_0 = input.LA(1);

            	    if ( (LA8_0==24) ) {
            	        alt8=1;
            	    }
            	    else if ( (LA8_0==27) ) {
            	        alt8=2;
            	    }
            	    else {
            	        NoViableAltException nvae =
            	            new NoViableAltException("", 8, 0, input);

            	        throw nvae;
            	    }
            	    switch (alt8) {
            	        case 1 :
            	            // InternalConceptLanguage.g:521:10: (otherlv_14= '[' ( ( ruleQualifiedName ) ) (otherlv_16= ',' ( ( ruleQualifiedName ) ) )* otherlv_18= ']' )
            	            {
            	            // InternalConceptLanguage.g:521:10: (otherlv_14= '[' ( ( ruleQualifiedName ) ) (otherlv_16= ',' ( ( ruleQualifiedName ) ) )* otherlv_18= ']' )
            	            // InternalConceptLanguage.g:522:11: otherlv_14= '[' ( ( ruleQualifiedName ) ) (otherlv_16= ',' ( ( ruleQualifiedName ) ) )* otherlv_18= ']'
            	            {
            	            otherlv_14=(Token)match(input,24,FOLLOW_3); 

            	            											newLeafNode(otherlv_14, grammarAccess.getStructuralElementAccess().getLeftSquareBracketKeyword_5_1_2_0_0());
            	            										
            	            // InternalConceptLanguage.g:526:11: ( ( ruleQualifiedName ) )
            	            // InternalConceptLanguage.g:527:12: ( ruleQualifiedName )
            	            {
            	            // InternalConceptLanguage.g:527:12: ( ruleQualifiedName )
            	            // InternalConceptLanguage.g:528:13: ruleQualifiedName
            	            {

            	            													if (current==null) {
            	            														current = createModelElement(grammarAccess.getStructuralElementRule());
            	            													}
            	            												

            	            													newCompositeNode(grammarAccess.getStructuralElementAccess().getCanInheritFromStructuralElementCrossReference_5_1_2_0_1_0());
            	            												
            	            pushFollow(FOLLOW_16);
            	            ruleQualifiedName();

            	            state._fsp--;


            	            													afterParserOrEnumRuleCall();
            	            												

            	            }


            	            }

            	            // InternalConceptLanguage.g:542:11: (otherlv_16= ',' ( ( ruleQualifiedName ) ) )*
            	            loop7:
            	            do {
            	                int alt7=2;
            	                int LA7_0 = input.LA(1);

            	                if ( (LA7_0==25) ) {
            	                    alt7=1;
            	                }


            	                switch (alt7) {
            	            	case 1 :
            	            	    // InternalConceptLanguage.g:543:12: otherlv_16= ',' ( ( ruleQualifiedName ) )
            	            	    {
            	            	    otherlv_16=(Token)match(input,25,FOLLOW_3); 

            	            	    												newLeafNode(otherlv_16, grammarAccess.getStructuralElementAccess().getCommaKeyword_5_1_2_0_2_0());
            	            	    											
            	            	    // InternalConceptLanguage.g:547:12: ( ( ruleQualifiedName ) )
            	            	    // InternalConceptLanguage.g:548:13: ( ruleQualifiedName )
            	            	    {
            	            	    // InternalConceptLanguage.g:548:13: ( ruleQualifiedName )
            	            	    // InternalConceptLanguage.g:549:14: ruleQualifiedName
            	            	    {

            	            	    														if (current==null) {
            	            	    															current = createModelElement(grammarAccess.getStructuralElementRule());
            	            	    														}
            	            	    													

            	            	    														newCompositeNode(grammarAccess.getStructuralElementAccess().getCanInheritFromStructuralElementCrossReference_5_1_2_0_2_1_0());
            	            	    													
            	            	    pushFollow(FOLLOW_16);
            	            	    ruleQualifiedName();

            	            	    state._fsp--;


            	            	    														afterParserOrEnumRuleCall();
            	            	    													

            	            	    }


            	            	    }


            	            	    }
            	            	    break;

            	            	default :
            	            	    break loop7;
            	                }
            	            } while (true);

            	            otherlv_18=(Token)match(input,26,FOLLOW_13); 

            	            											newLeafNode(otherlv_18, grammarAccess.getStructuralElementAccess().getRightSquareBracketKeyword_5_1_2_0_3());
            	            										

            	            }


            	            }
            	            break;
            	        case 2 :
            	            // InternalConceptLanguage.g:570:10: ( (lv_isCanInheritFromAll_19_0= 'All' ) )
            	            {
            	            // InternalConceptLanguage.g:570:10: ( (lv_isCanInheritFromAll_19_0= 'All' ) )
            	            // InternalConceptLanguage.g:571:11: (lv_isCanInheritFromAll_19_0= 'All' )
            	            {
            	            // InternalConceptLanguage.g:571:11: (lv_isCanInheritFromAll_19_0= 'All' )
            	            // InternalConceptLanguage.g:572:12: lv_isCanInheritFromAll_19_0= 'All'
            	            {
            	            lv_isCanInheritFromAll_19_0=(Token)match(input,27,FOLLOW_13); 

            	            												newLeafNode(lv_isCanInheritFromAll_19_0, grammarAccess.getStructuralElementAccess().getIsCanInheritFromAllAllKeyword_5_1_2_1_0());
            	            											

            	            												if (current==null) {
            	            													current = createModelElement(grammarAccess.getStructuralElementRule());
            	            												}
            	            												setWithLastConsumed(current, "isCanInheritFromAll", true, "All");
            	            											

            	            }


            	            }


            	            }
            	            break;

            	    }

            	    otherlv_20=(Token)match(input,21,FOLLOW_12); 

            	    									newLeafNode(otherlv_20, grammarAccess.getStructuralElementAccess().getSemicolonKeyword_5_1_3());
            	    								

            	    }


            	    }

            	     
            	    						getUnorderedGroupHelper().returnFromSelection(grammarAccess.getStructuralElementAccess().getUnorderedGroup_5());
            	    					

            	    }


            	    }


            	    }
            	    break;
            	case 3 :
            	    // InternalConceptLanguage.g:595:4: ({...}? => ( ({...}? => (otherlv_21= 'Applicable' otherlv_22= 'For' ( (otherlv_23= '[' ( ( ruleQualifiedName ) ) (otherlv_25= ',' ( ( ruleQualifiedName ) ) )* otherlv_27= ']' ) | ( (lv_isApplicableForAll_28_0= 'All' ) ) ) otherlv_29= ';' ) ) ) )
            	    {
            	    // InternalConceptLanguage.g:595:4: ({...}? => ( ({...}? => (otherlv_21= 'Applicable' otherlv_22= 'For' ( (otherlv_23= '[' ( ( ruleQualifiedName ) ) (otherlv_25= ',' ( ( ruleQualifiedName ) ) )* otherlv_27= ']' ) | ( (lv_isApplicableForAll_28_0= 'All' ) ) ) otherlv_29= ';' ) ) ) )
            	    // InternalConceptLanguage.g:596:5: {...}? => ( ({...}? => (otherlv_21= 'Applicable' otherlv_22= 'For' ( (otherlv_23= '[' ( ( ruleQualifiedName ) ) (otherlv_25= ',' ( ( ruleQualifiedName ) ) )* otherlv_27= ']' ) | ( (lv_isApplicableForAll_28_0= 'All' ) ) ) otherlv_29= ';' ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getStructuralElementAccess().getUnorderedGroup_5(), 2) ) {
            	        throw new FailedPredicateException(input, "ruleStructuralElement", "getUnorderedGroupHelper().canSelect(grammarAccess.getStructuralElementAccess().getUnorderedGroup_5(), 2)");
            	    }
            	    // InternalConceptLanguage.g:596:114: ( ({...}? => (otherlv_21= 'Applicable' otherlv_22= 'For' ( (otherlv_23= '[' ( ( ruleQualifiedName ) ) (otherlv_25= ',' ( ( ruleQualifiedName ) ) )* otherlv_27= ']' ) | ( (lv_isApplicableForAll_28_0= 'All' ) ) ) otherlv_29= ';' ) ) )
            	    // InternalConceptLanguage.g:597:6: ({...}? => (otherlv_21= 'Applicable' otherlv_22= 'For' ( (otherlv_23= '[' ( ( ruleQualifiedName ) ) (otherlv_25= ',' ( ( ruleQualifiedName ) ) )* otherlv_27= ']' ) | ( (lv_isApplicableForAll_28_0= 'All' ) ) ) otherlv_29= ';' ) )
            	    {

            	    						getUnorderedGroupHelper().select(grammarAccess.getStructuralElementAccess().getUnorderedGroup_5(), 2);
            	    					
            	    // InternalConceptLanguage.g:600:9: ({...}? => (otherlv_21= 'Applicable' otherlv_22= 'For' ( (otherlv_23= '[' ( ( ruleQualifiedName ) ) (otherlv_25= ',' ( ( ruleQualifiedName ) ) )* otherlv_27= ']' ) | ( (lv_isApplicableForAll_28_0= 'All' ) ) ) otherlv_29= ';' ) )
            	    // InternalConceptLanguage.g:600:10: {...}? => (otherlv_21= 'Applicable' otherlv_22= 'For' ( (otherlv_23= '[' ( ( ruleQualifiedName ) ) (otherlv_25= ',' ( ( ruleQualifiedName ) ) )* otherlv_27= ']' ) | ( (lv_isApplicableForAll_28_0= 'All' ) ) ) otherlv_29= ';' )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleStructuralElement", "true");
            	    }
            	    // InternalConceptLanguage.g:600:19: (otherlv_21= 'Applicable' otherlv_22= 'For' ( (otherlv_23= '[' ( ( ruleQualifiedName ) ) (otherlv_25= ',' ( ( ruleQualifiedName ) ) )* otherlv_27= ']' ) | ( (lv_isApplicableForAll_28_0= 'All' ) ) ) otherlv_29= ';' )
            	    // InternalConceptLanguage.g:600:20: otherlv_21= 'Applicable' otherlv_22= 'For' ( (otherlv_23= '[' ( ( ruleQualifiedName ) ) (otherlv_25= ',' ( ( ruleQualifiedName ) ) )* otherlv_27= ']' ) | ( (lv_isApplicableForAll_28_0= 'All' ) ) ) otherlv_29= ';'
            	    {
            	    otherlv_21=(Token)match(input,28,FOLLOW_17); 

            	    									newLeafNode(otherlv_21, grammarAccess.getStructuralElementAccess().getApplicableKeyword_5_2_0());
            	    								
            	    otherlv_22=(Token)match(input,29,FOLLOW_15); 

            	    									newLeafNode(otherlv_22, grammarAccess.getStructuralElementAccess().getForKeyword_5_2_1());
            	    								
            	    // InternalConceptLanguage.g:608:9: ( (otherlv_23= '[' ( ( ruleQualifiedName ) ) (otherlv_25= ',' ( ( ruleQualifiedName ) ) )* otherlv_27= ']' ) | ( (lv_isApplicableForAll_28_0= 'All' ) ) )
            	    int alt10=2;
            	    int LA10_0 = input.LA(1);

            	    if ( (LA10_0==24) ) {
            	        alt10=1;
            	    }
            	    else if ( (LA10_0==27) ) {
            	        alt10=2;
            	    }
            	    else {
            	        NoViableAltException nvae =
            	            new NoViableAltException("", 10, 0, input);

            	        throw nvae;
            	    }
            	    switch (alt10) {
            	        case 1 :
            	            // InternalConceptLanguage.g:609:10: (otherlv_23= '[' ( ( ruleQualifiedName ) ) (otherlv_25= ',' ( ( ruleQualifiedName ) ) )* otherlv_27= ']' )
            	            {
            	            // InternalConceptLanguage.g:609:10: (otherlv_23= '[' ( ( ruleQualifiedName ) ) (otherlv_25= ',' ( ( ruleQualifiedName ) ) )* otherlv_27= ']' )
            	            // InternalConceptLanguage.g:610:11: otherlv_23= '[' ( ( ruleQualifiedName ) ) (otherlv_25= ',' ( ( ruleQualifiedName ) ) )* otherlv_27= ']'
            	            {
            	            otherlv_23=(Token)match(input,24,FOLLOW_3); 

            	            											newLeafNode(otherlv_23, grammarAccess.getStructuralElementAccess().getLeftSquareBracketKeyword_5_2_2_0_0());
            	            										
            	            // InternalConceptLanguage.g:614:11: ( ( ruleQualifiedName ) )
            	            // InternalConceptLanguage.g:615:12: ( ruleQualifiedName )
            	            {
            	            // InternalConceptLanguage.g:615:12: ( ruleQualifiedName )
            	            // InternalConceptLanguage.g:616:13: ruleQualifiedName
            	            {

            	            													if (current==null) {
            	            														current = createModelElement(grammarAccess.getStructuralElementRule());
            	            													}
            	            												

            	            													newCompositeNode(grammarAccess.getStructuralElementAccess().getApplicableForStructuralElementCrossReference_5_2_2_0_1_0());
            	            												
            	            pushFollow(FOLLOW_16);
            	            ruleQualifiedName();

            	            state._fsp--;


            	            													afterParserOrEnumRuleCall();
            	            												

            	            }


            	            }

            	            // InternalConceptLanguage.g:630:11: (otherlv_25= ',' ( ( ruleQualifiedName ) ) )*
            	            loop9:
            	            do {
            	                int alt9=2;
            	                int LA9_0 = input.LA(1);

            	                if ( (LA9_0==25) ) {
            	                    alt9=1;
            	                }


            	                switch (alt9) {
            	            	case 1 :
            	            	    // InternalConceptLanguage.g:631:12: otherlv_25= ',' ( ( ruleQualifiedName ) )
            	            	    {
            	            	    otherlv_25=(Token)match(input,25,FOLLOW_3); 

            	            	    												newLeafNode(otherlv_25, grammarAccess.getStructuralElementAccess().getCommaKeyword_5_2_2_0_2_0());
            	            	    											
            	            	    // InternalConceptLanguage.g:635:12: ( ( ruleQualifiedName ) )
            	            	    // InternalConceptLanguage.g:636:13: ( ruleQualifiedName )
            	            	    {
            	            	    // InternalConceptLanguage.g:636:13: ( ruleQualifiedName )
            	            	    // InternalConceptLanguage.g:637:14: ruleQualifiedName
            	            	    {

            	            	    														if (current==null) {
            	            	    															current = createModelElement(grammarAccess.getStructuralElementRule());
            	            	    														}
            	            	    													

            	            	    														newCompositeNode(grammarAccess.getStructuralElementAccess().getApplicableForStructuralElementCrossReference_5_2_2_0_2_1_0());
            	            	    													
            	            	    pushFollow(FOLLOW_16);
            	            	    ruleQualifiedName();

            	            	    state._fsp--;


            	            	    														afterParserOrEnumRuleCall();
            	            	    													

            	            	    }


            	            	    }


            	            	    }
            	            	    break;

            	            	default :
            	            	    break loop9;
            	                }
            	            } while (true);

            	            otherlv_27=(Token)match(input,26,FOLLOW_13); 

            	            											newLeafNode(otherlv_27, grammarAccess.getStructuralElementAccess().getRightSquareBracketKeyword_5_2_2_0_3());
            	            										

            	            }


            	            }
            	            break;
            	        case 2 :
            	            // InternalConceptLanguage.g:658:10: ( (lv_isApplicableForAll_28_0= 'All' ) )
            	            {
            	            // InternalConceptLanguage.g:658:10: ( (lv_isApplicableForAll_28_0= 'All' ) )
            	            // InternalConceptLanguage.g:659:11: (lv_isApplicableForAll_28_0= 'All' )
            	            {
            	            // InternalConceptLanguage.g:659:11: (lv_isApplicableForAll_28_0= 'All' )
            	            // InternalConceptLanguage.g:660:12: lv_isApplicableForAll_28_0= 'All'
            	            {
            	            lv_isApplicableForAll_28_0=(Token)match(input,27,FOLLOW_13); 

            	            												newLeafNode(lv_isApplicableForAll_28_0, grammarAccess.getStructuralElementAccess().getIsApplicableForAllAllKeyword_5_2_2_1_0());
            	            											

            	            												if (current==null) {
            	            													current = createModelElement(grammarAccess.getStructuralElementRule());
            	            												}
            	            												setWithLastConsumed(current, "isApplicableForAll", true, "All");
            	            											

            	            }


            	            }


            	            }
            	            break;

            	    }

            	    otherlv_29=(Token)match(input,21,FOLLOW_12); 

            	    									newLeafNode(otherlv_29, grammarAccess.getStructuralElementAccess().getSemicolonKeyword_5_2_3());
            	    								

            	    }


            	    }

            	     
            	    						getUnorderedGroupHelper().returnFromSelection(grammarAccess.getStructuralElementAccess().getUnorderedGroup_5());
            	    					

            	    }


            	    }


            	    }
            	    break;
            	case 4 :
            	    // InternalConceptLanguage.g:683:4: ({...}? => ( ({...}? => (otherlv_30= 'Cardinality' ( (lv_cardinality_31_0= RULE_INT ) ) otherlv_32= ';' ) ) ) )
            	    {
            	    // InternalConceptLanguage.g:683:4: ({...}? => ( ({...}? => (otherlv_30= 'Cardinality' ( (lv_cardinality_31_0= RULE_INT ) ) otherlv_32= ';' ) ) ) )
            	    // InternalConceptLanguage.g:684:5: {...}? => ( ({...}? => (otherlv_30= 'Cardinality' ( (lv_cardinality_31_0= RULE_INT ) ) otherlv_32= ';' ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getStructuralElementAccess().getUnorderedGroup_5(), 3) ) {
            	        throw new FailedPredicateException(input, "ruleStructuralElement", "getUnorderedGroupHelper().canSelect(grammarAccess.getStructuralElementAccess().getUnorderedGroup_5(), 3)");
            	    }
            	    // InternalConceptLanguage.g:684:114: ( ({...}? => (otherlv_30= 'Cardinality' ( (lv_cardinality_31_0= RULE_INT ) ) otherlv_32= ';' ) ) )
            	    // InternalConceptLanguage.g:685:6: ({...}? => (otherlv_30= 'Cardinality' ( (lv_cardinality_31_0= RULE_INT ) ) otherlv_32= ';' ) )
            	    {

            	    						getUnorderedGroupHelper().select(grammarAccess.getStructuralElementAccess().getUnorderedGroup_5(), 3);
            	    					
            	    // InternalConceptLanguage.g:688:9: ({...}? => (otherlv_30= 'Cardinality' ( (lv_cardinality_31_0= RULE_INT ) ) otherlv_32= ';' ) )
            	    // InternalConceptLanguage.g:688:10: {...}? => (otherlv_30= 'Cardinality' ( (lv_cardinality_31_0= RULE_INT ) ) otherlv_32= ';' )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleStructuralElement", "true");
            	    }
            	    // InternalConceptLanguage.g:688:19: (otherlv_30= 'Cardinality' ( (lv_cardinality_31_0= RULE_INT ) ) otherlv_32= ';' )
            	    // InternalConceptLanguage.g:688:20: otherlv_30= 'Cardinality' ( (lv_cardinality_31_0= RULE_INT ) ) otherlv_32= ';'
            	    {
            	    otherlv_30=(Token)match(input,30,FOLLOW_6); 

            	    									newLeafNode(otherlv_30, grammarAccess.getStructuralElementAccess().getCardinalityKeyword_5_3_0());
            	    								
            	    // InternalConceptLanguage.g:692:9: ( (lv_cardinality_31_0= RULE_INT ) )
            	    // InternalConceptLanguage.g:693:10: (lv_cardinality_31_0= RULE_INT )
            	    {
            	    // InternalConceptLanguage.g:693:10: (lv_cardinality_31_0= RULE_INT )
            	    // InternalConceptLanguage.g:694:11: lv_cardinality_31_0= RULE_INT
            	    {
            	    lv_cardinality_31_0=(Token)match(input,RULE_INT,FOLLOW_13); 

            	    											newLeafNode(lv_cardinality_31_0, grammarAccess.getStructuralElementAccess().getCardinalityINTTerminalRuleCall_5_3_1_0());
            	    										

            	    											if (current==null) {
            	    												current = createModelElement(grammarAccess.getStructuralElementRule());
            	    											}
            	    											setWithLastConsumed(
            	    												current,
            	    												"cardinality",
            	    												lv_cardinality_31_0,
            	    												"org.eclipse.xtext.common.Terminals.INT");
            	    										

            	    }


            	    }

            	    otherlv_32=(Token)match(input,21,FOLLOW_12); 

            	    									newLeafNode(otherlv_32, grammarAccess.getStructuralElementAccess().getSemicolonKeyword_5_3_2());
            	    								

            	    }


            	    }

            	     
            	    						getUnorderedGroupHelper().returnFromSelection(grammarAccess.getStructuralElementAccess().getUnorderedGroup_5());
            	    					

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop11;
                }
            } while (true);


            }


            }

             
            				  getUnorderedGroupHelper().leave(grammarAccess.getStructuralElementAccess().getUnorderedGroup_5());
            				

            }

            otherlv_33=(Token)match(input,17,FOLLOW_2); 

            			newLeafNode(otherlv_33, grammarAccess.getStructuralElementAccess().getRightCurlyBracketKeyword_6());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleStructuralElement"


    // $ANTLR start "entryRuleGeneralRelation"
    // InternalConceptLanguage.g:735:1: entryRuleGeneralRelation returns [EObject current=null] : iv_ruleGeneralRelation= ruleGeneralRelation EOF ;
    public final EObject entryRuleGeneralRelation() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleGeneralRelation = null;


        try {
            // InternalConceptLanguage.g:735:56: (iv_ruleGeneralRelation= ruleGeneralRelation EOF )
            // InternalConceptLanguage.g:736:2: iv_ruleGeneralRelation= ruleGeneralRelation EOF
            {
             newCompositeNode(grammarAccess.getGeneralRelationRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleGeneralRelation=ruleGeneralRelation();

            state._fsp--;

             current =iv_ruleGeneralRelation; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleGeneralRelation"


    // $ANTLR start "ruleGeneralRelation"
    // InternalConceptLanguage.g:742:1: ruleGeneralRelation returns [EObject current=null] : ( () otherlv_1= 'GeneralRelation' ( (lv_name_2_0= RULE_ID ) ) (otherlv_3= 'description' ( (lv_description_4_0= ruleEString ) ) )? otherlv_5= '{' otherlv_6= 'Referenced' otherlv_7= 'Type' ( ( ruleQualifiedName ) ) otherlv_9= ';' (otherlv_10= 'Applicable' otherlv_11= 'For' ( (otherlv_12= '[' ( ( ruleQualifiedName ) ) (otherlv_14= ',' ( ( ruleQualifiedName ) ) )* otherlv_16= ']' ) | ( (lv_isApplicableForAll_17_0= 'All' ) ) ) otherlv_18= ';' )? (otherlv_19= 'Cardinality' ( (lv_cardinality_20_0= RULE_INT ) ) otherlv_21= ';' )? otherlv_22= '}' ) ;
    public final EObject ruleGeneralRelation() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token lv_name_2_0=null;
        Token otherlv_3=null;
        Token otherlv_5=null;
        Token otherlv_6=null;
        Token otherlv_7=null;
        Token otherlv_9=null;
        Token otherlv_10=null;
        Token otherlv_11=null;
        Token otherlv_12=null;
        Token otherlv_14=null;
        Token otherlv_16=null;
        Token lv_isApplicableForAll_17_0=null;
        Token otherlv_18=null;
        Token otherlv_19=null;
        Token lv_cardinality_20_0=null;
        Token otherlv_21=null;
        Token otherlv_22=null;
        AntlrDatatypeRuleToken lv_description_4_0 = null;



        	enterRule();

        try {
            // InternalConceptLanguage.g:748:2: ( ( () otherlv_1= 'GeneralRelation' ( (lv_name_2_0= RULE_ID ) ) (otherlv_3= 'description' ( (lv_description_4_0= ruleEString ) ) )? otherlv_5= '{' otherlv_6= 'Referenced' otherlv_7= 'Type' ( ( ruleQualifiedName ) ) otherlv_9= ';' (otherlv_10= 'Applicable' otherlv_11= 'For' ( (otherlv_12= '[' ( ( ruleQualifiedName ) ) (otherlv_14= ',' ( ( ruleQualifiedName ) ) )* otherlv_16= ']' ) | ( (lv_isApplicableForAll_17_0= 'All' ) ) ) otherlv_18= ';' )? (otherlv_19= 'Cardinality' ( (lv_cardinality_20_0= RULE_INT ) ) otherlv_21= ';' )? otherlv_22= '}' ) )
            // InternalConceptLanguage.g:749:2: ( () otherlv_1= 'GeneralRelation' ( (lv_name_2_0= RULE_ID ) ) (otherlv_3= 'description' ( (lv_description_4_0= ruleEString ) ) )? otherlv_5= '{' otherlv_6= 'Referenced' otherlv_7= 'Type' ( ( ruleQualifiedName ) ) otherlv_9= ';' (otherlv_10= 'Applicable' otherlv_11= 'For' ( (otherlv_12= '[' ( ( ruleQualifiedName ) ) (otherlv_14= ',' ( ( ruleQualifiedName ) ) )* otherlv_16= ']' ) | ( (lv_isApplicableForAll_17_0= 'All' ) ) ) otherlv_18= ';' )? (otherlv_19= 'Cardinality' ( (lv_cardinality_20_0= RULE_INT ) ) otherlv_21= ';' )? otherlv_22= '}' )
            {
            // InternalConceptLanguage.g:749:2: ( () otherlv_1= 'GeneralRelation' ( (lv_name_2_0= RULE_ID ) ) (otherlv_3= 'description' ( (lv_description_4_0= ruleEString ) ) )? otherlv_5= '{' otherlv_6= 'Referenced' otherlv_7= 'Type' ( ( ruleQualifiedName ) ) otherlv_9= ';' (otherlv_10= 'Applicable' otherlv_11= 'For' ( (otherlv_12= '[' ( ( ruleQualifiedName ) ) (otherlv_14= ',' ( ( ruleQualifiedName ) ) )* otherlv_16= ']' ) | ( (lv_isApplicableForAll_17_0= 'All' ) ) ) otherlv_18= ';' )? (otherlv_19= 'Cardinality' ( (lv_cardinality_20_0= RULE_INT ) ) otherlv_21= ';' )? otherlv_22= '}' )
            // InternalConceptLanguage.g:750:3: () otherlv_1= 'GeneralRelation' ( (lv_name_2_0= RULE_ID ) ) (otherlv_3= 'description' ( (lv_description_4_0= ruleEString ) ) )? otherlv_5= '{' otherlv_6= 'Referenced' otherlv_7= 'Type' ( ( ruleQualifiedName ) ) otherlv_9= ';' (otherlv_10= 'Applicable' otherlv_11= 'For' ( (otherlv_12= '[' ( ( ruleQualifiedName ) ) (otherlv_14= ',' ( ( ruleQualifiedName ) ) )* otherlv_16= ']' ) | ( (lv_isApplicableForAll_17_0= 'All' ) ) ) otherlv_18= ';' )? (otherlv_19= 'Cardinality' ( (lv_cardinality_20_0= RULE_INT ) ) otherlv_21= ';' )? otherlv_22= '}'
            {
            // InternalConceptLanguage.g:750:3: ()
            // InternalConceptLanguage.g:751:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getGeneralRelationAccess().getGeneralRelationAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,31,FOLLOW_3); 

            			newLeafNode(otherlv_1, grammarAccess.getGeneralRelationAccess().getGeneralRelationKeyword_1());
            		
            // InternalConceptLanguage.g:761:3: ( (lv_name_2_0= RULE_ID ) )
            // InternalConceptLanguage.g:762:4: (lv_name_2_0= RULE_ID )
            {
            // InternalConceptLanguage.g:762:4: (lv_name_2_0= RULE_ID )
            // InternalConceptLanguage.g:763:5: lv_name_2_0= RULE_ID
            {
            lv_name_2_0=(Token)match(input,RULE_ID,FOLLOW_18); 

            					newLeafNode(lv_name_2_0, grammarAccess.getGeneralRelationAccess().getNameIDTerminalRuleCall_2_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getGeneralRelationRule());
            					}
            					setWithLastConsumed(
            						current,
            						"name",
            						lv_name_2_0,
            						"org.eclipse.xtext.common.Terminals.ID");
            				

            }


            }

            // InternalConceptLanguage.g:779:3: (otherlv_3= 'description' ( (lv_description_4_0= ruleEString ) ) )?
            int alt12=2;
            int LA12_0 = input.LA(1);

            if ( (LA12_0==14) ) {
                alt12=1;
            }
            switch (alt12) {
                case 1 :
                    // InternalConceptLanguage.g:780:4: otherlv_3= 'description' ( (lv_description_4_0= ruleEString ) )
                    {
                    otherlv_3=(Token)match(input,14,FOLLOW_5); 

                    				newLeafNode(otherlv_3, grammarAccess.getGeneralRelationAccess().getDescriptionKeyword_3_0());
                    			
                    // InternalConceptLanguage.g:784:4: ( (lv_description_4_0= ruleEString ) )
                    // InternalConceptLanguage.g:785:5: (lv_description_4_0= ruleEString )
                    {
                    // InternalConceptLanguage.g:785:5: (lv_description_4_0= ruleEString )
                    // InternalConceptLanguage.g:786:6: lv_description_4_0= ruleEString
                    {

                    						newCompositeNode(grammarAccess.getGeneralRelationAccess().getDescriptionEStringParserRuleCall_3_1_0());
                    					
                    pushFollow(FOLLOW_19);
                    lv_description_4_0=ruleEString();

                    state._fsp--;


                    						if (current==null) {
                    							current = createModelElementForParent(grammarAccess.getGeneralRelationRule());
                    						}
                    						set(
                    							current,
                    							"description",
                    							lv_description_4_0,
                    							"de.dlr.sc.virsat.model.concept.ConceptLanguage.EString");
                    						afterParserOrEnumRuleCall();
                    					

                    }


                    }


                    }
                    break;

            }

            otherlv_5=(Token)match(input,16,FOLLOW_20); 

            			newLeafNode(otherlv_5, grammarAccess.getGeneralRelationAccess().getLeftCurlyBracketKeyword_4());
            		
            otherlv_6=(Token)match(input,32,FOLLOW_21); 

            			newLeafNode(otherlv_6, grammarAccess.getGeneralRelationAccess().getReferencedKeyword_5());
            		
            otherlv_7=(Token)match(input,33,FOLLOW_3); 

            			newLeafNode(otherlv_7, grammarAccess.getGeneralRelationAccess().getTypeKeyword_6());
            		
            // InternalConceptLanguage.g:816:3: ( ( ruleQualifiedName ) )
            // InternalConceptLanguage.g:817:4: ( ruleQualifiedName )
            {
            // InternalConceptLanguage.g:817:4: ( ruleQualifiedName )
            // InternalConceptLanguage.g:818:5: ruleQualifiedName
            {

            					if (current==null) {
            						current = createModelElement(grammarAccess.getGeneralRelationRule());
            					}
            				

            					newCompositeNode(grammarAccess.getGeneralRelationAccess().getReferencedTypeStructuralElementCrossReference_7_0());
            				
            pushFollow(FOLLOW_13);
            ruleQualifiedName();

            state._fsp--;


            					afterParserOrEnumRuleCall();
            				

            }


            }

            otherlv_9=(Token)match(input,21,FOLLOW_22); 

            			newLeafNode(otherlv_9, grammarAccess.getGeneralRelationAccess().getSemicolonKeyword_8());
            		
            // InternalConceptLanguage.g:836:3: (otherlv_10= 'Applicable' otherlv_11= 'For' ( (otherlv_12= '[' ( ( ruleQualifiedName ) ) (otherlv_14= ',' ( ( ruleQualifiedName ) ) )* otherlv_16= ']' ) | ( (lv_isApplicableForAll_17_0= 'All' ) ) ) otherlv_18= ';' )?
            int alt15=2;
            int LA15_0 = input.LA(1);

            if ( (LA15_0==28) ) {
                alt15=1;
            }
            switch (alt15) {
                case 1 :
                    // InternalConceptLanguage.g:837:4: otherlv_10= 'Applicable' otherlv_11= 'For' ( (otherlv_12= '[' ( ( ruleQualifiedName ) ) (otherlv_14= ',' ( ( ruleQualifiedName ) ) )* otherlv_16= ']' ) | ( (lv_isApplicableForAll_17_0= 'All' ) ) ) otherlv_18= ';'
                    {
                    otherlv_10=(Token)match(input,28,FOLLOW_17); 

                    				newLeafNode(otherlv_10, grammarAccess.getGeneralRelationAccess().getApplicableKeyword_9_0());
                    			
                    otherlv_11=(Token)match(input,29,FOLLOW_15); 

                    				newLeafNode(otherlv_11, grammarAccess.getGeneralRelationAccess().getForKeyword_9_1());
                    			
                    // InternalConceptLanguage.g:845:4: ( (otherlv_12= '[' ( ( ruleQualifiedName ) ) (otherlv_14= ',' ( ( ruleQualifiedName ) ) )* otherlv_16= ']' ) | ( (lv_isApplicableForAll_17_0= 'All' ) ) )
                    int alt14=2;
                    int LA14_0 = input.LA(1);

                    if ( (LA14_0==24) ) {
                        alt14=1;
                    }
                    else if ( (LA14_0==27) ) {
                        alt14=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 14, 0, input);

                        throw nvae;
                    }
                    switch (alt14) {
                        case 1 :
                            // InternalConceptLanguage.g:846:5: (otherlv_12= '[' ( ( ruleQualifiedName ) ) (otherlv_14= ',' ( ( ruleQualifiedName ) ) )* otherlv_16= ']' )
                            {
                            // InternalConceptLanguage.g:846:5: (otherlv_12= '[' ( ( ruleQualifiedName ) ) (otherlv_14= ',' ( ( ruleQualifiedName ) ) )* otherlv_16= ']' )
                            // InternalConceptLanguage.g:847:6: otherlv_12= '[' ( ( ruleQualifiedName ) ) (otherlv_14= ',' ( ( ruleQualifiedName ) ) )* otherlv_16= ']'
                            {
                            otherlv_12=(Token)match(input,24,FOLLOW_3); 

                            						newLeafNode(otherlv_12, grammarAccess.getGeneralRelationAccess().getLeftSquareBracketKeyword_9_2_0_0());
                            					
                            // InternalConceptLanguage.g:851:6: ( ( ruleQualifiedName ) )
                            // InternalConceptLanguage.g:852:7: ( ruleQualifiedName )
                            {
                            // InternalConceptLanguage.g:852:7: ( ruleQualifiedName )
                            // InternalConceptLanguage.g:853:8: ruleQualifiedName
                            {

                            								if (current==null) {
                            									current = createModelElement(grammarAccess.getGeneralRelationRule());
                            								}
                            							

                            								newCompositeNode(grammarAccess.getGeneralRelationAccess().getApplicableForStructuralElementCrossReference_9_2_0_1_0());
                            							
                            pushFollow(FOLLOW_16);
                            ruleQualifiedName();

                            state._fsp--;


                            								afterParserOrEnumRuleCall();
                            							

                            }


                            }

                            // InternalConceptLanguage.g:867:6: (otherlv_14= ',' ( ( ruleQualifiedName ) ) )*
                            loop13:
                            do {
                                int alt13=2;
                                int LA13_0 = input.LA(1);

                                if ( (LA13_0==25) ) {
                                    alt13=1;
                                }


                                switch (alt13) {
                            	case 1 :
                            	    // InternalConceptLanguage.g:868:7: otherlv_14= ',' ( ( ruleQualifiedName ) )
                            	    {
                            	    otherlv_14=(Token)match(input,25,FOLLOW_3); 

                            	    							newLeafNode(otherlv_14, grammarAccess.getGeneralRelationAccess().getCommaKeyword_9_2_0_2_0());
                            	    						
                            	    // InternalConceptLanguage.g:872:7: ( ( ruleQualifiedName ) )
                            	    // InternalConceptLanguage.g:873:8: ( ruleQualifiedName )
                            	    {
                            	    // InternalConceptLanguage.g:873:8: ( ruleQualifiedName )
                            	    // InternalConceptLanguage.g:874:9: ruleQualifiedName
                            	    {

                            	    									if (current==null) {
                            	    										current = createModelElement(grammarAccess.getGeneralRelationRule());
                            	    									}
                            	    								

                            	    									newCompositeNode(grammarAccess.getGeneralRelationAccess().getApplicableForStructuralElementCrossReference_9_2_0_2_1_0());
                            	    								
                            	    pushFollow(FOLLOW_16);
                            	    ruleQualifiedName();

                            	    state._fsp--;


                            	    									afterParserOrEnumRuleCall();
                            	    								

                            	    }


                            	    }


                            	    }
                            	    break;

                            	default :
                            	    break loop13;
                                }
                            } while (true);

                            otherlv_16=(Token)match(input,26,FOLLOW_13); 

                            						newLeafNode(otherlv_16, grammarAccess.getGeneralRelationAccess().getRightSquareBracketKeyword_9_2_0_3());
                            					

                            }


                            }
                            break;
                        case 2 :
                            // InternalConceptLanguage.g:895:5: ( (lv_isApplicableForAll_17_0= 'All' ) )
                            {
                            // InternalConceptLanguage.g:895:5: ( (lv_isApplicableForAll_17_0= 'All' ) )
                            // InternalConceptLanguage.g:896:6: (lv_isApplicableForAll_17_0= 'All' )
                            {
                            // InternalConceptLanguage.g:896:6: (lv_isApplicableForAll_17_0= 'All' )
                            // InternalConceptLanguage.g:897:7: lv_isApplicableForAll_17_0= 'All'
                            {
                            lv_isApplicableForAll_17_0=(Token)match(input,27,FOLLOW_13); 

                            							newLeafNode(lv_isApplicableForAll_17_0, grammarAccess.getGeneralRelationAccess().getIsApplicableForAllAllKeyword_9_2_1_0());
                            						

                            							if (current==null) {
                            								current = createModelElement(grammarAccess.getGeneralRelationRule());
                            							}
                            							setWithLastConsumed(current, "isApplicableForAll", true, "All");
                            						

                            }


                            }


                            }
                            break;

                    }

                    otherlv_18=(Token)match(input,21,FOLLOW_23); 

                    				newLeafNode(otherlv_18, grammarAccess.getGeneralRelationAccess().getSemicolonKeyword_9_3());
                    			

                    }
                    break;

            }

            // InternalConceptLanguage.g:915:3: (otherlv_19= 'Cardinality' ( (lv_cardinality_20_0= RULE_INT ) ) otherlv_21= ';' )?
            int alt16=2;
            int LA16_0 = input.LA(1);

            if ( (LA16_0==30) ) {
                alt16=1;
            }
            switch (alt16) {
                case 1 :
                    // InternalConceptLanguage.g:916:4: otherlv_19= 'Cardinality' ( (lv_cardinality_20_0= RULE_INT ) ) otherlv_21= ';'
                    {
                    otherlv_19=(Token)match(input,30,FOLLOW_6); 

                    				newLeafNode(otherlv_19, grammarAccess.getGeneralRelationAccess().getCardinalityKeyword_10_0());
                    			
                    // InternalConceptLanguage.g:920:4: ( (lv_cardinality_20_0= RULE_INT ) )
                    // InternalConceptLanguage.g:921:5: (lv_cardinality_20_0= RULE_INT )
                    {
                    // InternalConceptLanguage.g:921:5: (lv_cardinality_20_0= RULE_INT )
                    // InternalConceptLanguage.g:922:6: lv_cardinality_20_0= RULE_INT
                    {
                    lv_cardinality_20_0=(Token)match(input,RULE_INT,FOLLOW_13); 

                    						newLeafNode(lv_cardinality_20_0, grammarAccess.getGeneralRelationAccess().getCardinalityINTTerminalRuleCall_10_1_0());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getGeneralRelationRule());
                    						}
                    						setWithLastConsumed(
                    							current,
                    							"cardinality",
                    							lv_cardinality_20_0,
                    							"org.eclipse.xtext.common.Terminals.INT");
                    					

                    }


                    }

                    otherlv_21=(Token)match(input,21,FOLLOW_24); 

                    				newLeafNode(otherlv_21, grammarAccess.getGeneralRelationAccess().getSemicolonKeyword_10_2());
                    			

                    }
                    break;

            }

            otherlv_22=(Token)match(input,17,FOLLOW_2); 

            			newLeafNode(otherlv_22, grammarAccess.getGeneralRelationAccess().getRightCurlyBracketKeyword_11());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleGeneralRelation"


    // $ANTLR start "entryRuleARelation"
    // InternalConceptLanguage.g:951:1: entryRuleARelation returns [EObject current=null] : iv_ruleARelation= ruleARelation EOF ;
    public final EObject entryRuleARelation() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleARelation = null;


        try {
            // InternalConceptLanguage.g:951:50: (iv_ruleARelation= ruleARelation EOF )
            // InternalConceptLanguage.g:952:2: iv_ruleARelation= ruleARelation EOF
            {
             newCompositeNode(grammarAccess.getARelationRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleARelation=ruleARelation();

            state._fsp--;

             current =iv_ruleARelation; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleARelation"


    // $ANTLR start "ruleARelation"
    // InternalConceptLanguage.g:958:1: ruleARelation returns [EObject current=null] : this_GeneralRelation_0= ruleGeneralRelation ;
    public final EObject ruleARelation() throws RecognitionException {
        EObject current = null;

        EObject this_GeneralRelation_0 = null;



        	enterRule();

        try {
            // InternalConceptLanguage.g:964:2: (this_GeneralRelation_0= ruleGeneralRelation )
            // InternalConceptLanguage.g:965:2: this_GeneralRelation_0= ruleGeneralRelation
            {

            		newCompositeNode(grammarAccess.getARelationAccess().getGeneralRelationParserRuleCall());
            	
            pushFollow(FOLLOW_2);
            this_GeneralRelation_0=ruleGeneralRelation();

            state._fsp--;


            		current = this_GeneralRelation_0;
            		afterParserOrEnumRuleCall();
            	

            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleARelation"


    // $ANTLR start "entryRuleCategory"
    // InternalConceptLanguage.g:976:1: entryRuleCategory returns [EObject current=null] : iv_ruleCategory= ruleCategory EOF ;
    public final EObject entryRuleCategory() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleCategory = null;


        try {
            // InternalConceptLanguage.g:976:49: (iv_ruleCategory= ruleCategory EOF )
            // InternalConceptLanguage.g:977:2: iv_ruleCategory= ruleCategory EOF
            {
             newCompositeNode(grammarAccess.getCategoryRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleCategory=ruleCategory();

            state._fsp--;

             current =iv_ruleCategory; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleCategory"


    // $ANTLR start "ruleCategory"
    // InternalConceptLanguage.g:983:1: ruleCategory returns [EObject current=null] : ( () otherlv_1= 'Category' ( (lv_name_2_0= RULE_ID ) ) ( ( ( ( ({...}? => ( ({...}? => (otherlv_4= 'extends' ( ( ruleQualifiedName ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_6= 'shortname' ( (lv_shortName_7_0= RULE_ID ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_8= 'description' ( (lv_description_9_0= ruleEString ) ) ) ) ) ) )* ) ) ) otherlv_10= '{' ( ( ( ( ({...}? => ( ({...}? => ( ( (lv_isAbstract_12_0= 'IsAbstract' ) ) otherlv_13= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_14= 'Applicable' otherlv_15= 'For' ( (otherlv_16= '[' ( ( ruleQualifiedName ) ) (otherlv_18= ',' ( ( ruleQualifiedName ) ) )* otherlv_20= ']' ) | ( (lv_isApplicableForAll_21_0= 'All' ) ) ) otherlv_22= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_23= 'Cardinality' ( (lv_cardinality_24_0= RULE_INT ) ) otherlv_25= ';' ) ) ) ) )* ) ) ) ( (lv_properties_26_0= ruleAProperty ) )* ( (lv_equationDefinitions_27_0= ruleEquationDefinition ) )* otherlv_28= '}' ) ;
    public final EObject ruleCategory() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token lv_name_2_0=null;
        Token otherlv_4=null;
        Token otherlv_6=null;
        Token lv_shortName_7_0=null;
        Token otherlv_8=null;
        Token otherlv_10=null;
        Token lv_isAbstract_12_0=null;
        Token otherlv_13=null;
        Token otherlv_14=null;
        Token otherlv_15=null;
        Token otherlv_16=null;
        Token otherlv_18=null;
        Token otherlv_20=null;
        Token lv_isApplicableForAll_21_0=null;
        Token otherlv_22=null;
        Token otherlv_23=null;
        Token lv_cardinality_24_0=null;
        Token otherlv_25=null;
        Token otherlv_28=null;
        AntlrDatatypeRuleToken lv_description_9_0 = null;

        EObject lv_properties_26_0 = null;

        EObject lv_equationDefinitions_27_0 = null;



        	enterRule();

        try {
            // InternalConceptLanguage.g:989:2: ( ( () otherlv_1= 'Category' ( (lv_name_2_0= RULE_ID ) ) ( ( ( ( ({...}? => ( ({...}? => (otherlv_4= 'extends' ( ( ruleQualifiedName ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_6= 'shortname' ( (lv_shortName_7_0= RULE_ID ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_8= 'description' ( (lv_description_9_0= ruleEString ) ) ) ) ) ) )* ) ) ) otherlv_10= '{' ( ( ( ( ({...}? => ( ({...}? => ( ( (lv_isAbstract_12_0= 'IsAbstract' ) ) otherlv_13= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_14= 'Applicable' otherlv_15= 'For' ( (otherlv_16= '[' ( ( ruleQualifiedName ) ) (otherlv_18= ',' ( ( ruleQualifiedName ) ) )* otherlv_20= ']' ) | ( (lv_isApplicableForAll_21_0= 'All' ) ) ) otherlv_22= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_23= 'Cardinality' ( (lv_cardinality_24_0= RULE_INT ) ) otherlv_25= ';' ) ) ) ) )* ) ) ) ( (lv_properties_26_0= ruleAProperty ) )* ( (lv_equationDefinitions_27_0= ruleEquationDefinition ) )* otherlv_28= '}' ) )
            // InternalConceptLanguage.g:990:2: ( () otherlv_1= 'Category' ( (lv_name_2_0= RULE_ID ) ) ( ( ( ( ({...}? => ( ({...}? => (otherlv_4= 'extends' ( ( ruleQualifiedName ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_6= 'shortname' ( (lv_shortName_7_0= RULE_ID ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_8= 'description' ( (lv_description_9_0= ruleEString ) ) ) ) ) ) )* ) ) ) otherlv_10= '{' ( ( ( ( ({...}? => ( ({...}? => ( ( (lv_isAbstract_12_0= 'IsAbstract' ) ) otherlv_13= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_14= 'Applicable' otherlv_15= 'For' ( (otherlv_16= '[' ( ( ruleQualifiedName ) ) (otherlv_18= ',' ( ( ruleQualifiedName ) ) )* otherlv_20= ']' ) | ( (lv_isApplicableForAll_21_0= 'All' ) ) ) otherlv_22= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_23= 'Cardinality' ( (lv_cardinality_24_0= RULE_INT ) ) otherlv_25= ';' ) ) ) ) )* ) ) ) ( (lv_properties_26_0= ruleAProperty ) )* ( (lv_equationDefinitions_27_0= ruleEquationDefinition ) )* otherlv_28= '}' )
            {
            // InternalConceptLanguage.g:990:2: ( () otherlv_1= 'Category' ( (lv_name_2_0= RULE_ID ) ) ( ( ( ( ({...}? => ( ({...}? => (otherlv_4= 'extends' ( ( ruleQualifiedName ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_6= 'shortname' ( (lv_shortName_7_0= RULE_ID ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_8= 'description' ( (lv_description_9_0= ruleEString ) ) ) ) ) ) )* ) ) ) otherlv_10= '{' ( ( ( ( ({...}? => ( ({...}? => ( ( (lv_isAbstract_12_0= 'IsAbstract' ) ) otherlv_13= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_14= 'Applicable' otherlv_15= 'For' ( (otherlv_16= '[' ( ( ruleQualifiedName ) ) (otherlv_18= ',' ( ( ruleQualifiedName ) ) )* otherlv_20= ']' ) | ( (lv_isApplicableForAll_21_0= 'All' ) ) ) otherlv_22= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_23= 'Cardinality' ( (lv_cardinality_24_0= RULE_INT ) ) otherlv_25= ';' ) ) ) ) )* ) ) ) ( (lv_properties_26_0= ruleAProperty ) )* ( (lv_equationDefinitions_27_0= ruleEquationDefinition ) )* otherlv_28= '}' )
            // InternalConceptLanguage.g:991:3: () otherlv_1= 'Category' ( (lv_name_2_0= RULE_ID ) ) ( ( ( ( ({...}? => ( ({...}? => (otherlv_4= 'extends' ( ( ruleQualifiedName ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_6= 'shortname' ( (lv_shortName_7_0= RULE_ID ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_8= 'description' ( (lv_description_9_0= ruleEString ) ) ) ) ) ) )* ) ) ) otherlv_10= '{' ( ( ( ( ({...}? => ( ({...}? => ( ( (lv_isAbstract_12_0= 'IsAbstract' ) ) otherlv_13= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_14= 'Applicable' otherlv_15= 'For' ( (otherlv_16= '[' ( ( ruleQualifiedName ) ) (otherlv_18= ',' ( ( ruleQualifiedName ) ) )* otherlv_20= ']' ) | ( (lv_isApplicableForAll_21_0= 'All' ) ) ) otherlv_22= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_23= 'Cardinality' ( (lv_cardinality_24_0= RULE_INT ) ) otherlv_25= ';' ) ) ) ) )* ) ) ) ( (lv_properties_26_0= ruleAProperty ) )* ( (lv_equationDefinitions_27_0= ruleEquationDefinition ) )* otherlv_28= '}'
            {
            // InternalConceptLanguage.g:991:3: ()
            // InternalConceptLanguage.g:992:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getCategoryAccess().getCategoryAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,34,FOLLOW_3); 

            			newLeafNode(otherlv_1, grammarAccess.getCategoryAccess().getCategoryKeyword_1());
            		
            // InternalConceptLanguage.g:1002:3: ( (lv_name_2_0= RULE_ID ) )
            // InternalConceptLanguage.g:1003:4: (lv_name_2_0= RULE_ID )
            {
            // InternalConceptLanguage.g:1003:4: (lv_name_2_0= RULE_ID )
            // InternalConceptLanguage.g:1004:5: lv_name_2_0= RULE_ID
            {
            lv_name_2_0=(Token)match(input,RULE_ID,FOLLOW_25); 

            					newLeafNode(lv_name_2_0, grammarAccess.getCategoryAccess().getNameIDTerminalRuleCall_2_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getCategoryRule());
            					}
            					setWithLastConsumed(
            						current,
            						"name",
            						lv_name_2_0,
            						"org.eclipse.xtext.common.Terminals.ID");
            				

            }


            }

            // InternalConceptLanguage.g:1020:3: ( ( ( ( ({...}? => ( ({...}? => (otherlv_4= 'extends' ( ( ruleQualifiedName ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_6= 'shortname' ( (lv_shortName_7_0= RULE_ID ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_8= 'description' ( (lv_description_9_0= ruleEString ) ) ) ) ) ) )* ) ) )
            // InternalConceptLanguage.g:1021:4: ( ( ( ({...}? => ( ({...}? => (otherlv_4= 'extends' ( ( ruleQualifiedName ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_6= 'shortname' ( (lv_shortName_7_0= RULE_ID ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_8= 'description' ( (lv_description_9_0= ruleEString ) ) ) ) ) ) )* ) )
            {
            // InternalConceptLanguage.g:1021:4: ( ( ( ({...}? => ( ({...}? => (otherlv_4= 'extends' ( ( ruleQualifiedName ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_6= 'shortname' ( (lv_shortName_7_0= RULE_ID ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_8= 'description' ( (lv_description_9_0= ruleEString ) ) ) ) ) ) )* ) )
            // InternalConceptLanguage.g:1022:5: ( ( ({...}? => ( ({...}? => (otherlv_4= 'extends' ( ( ruleQualifiedName ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_6= 'shortname' ( (lv_shortName_7_0= RULE_ID ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_8= 'description' ( (lv_description_9_0= ruleEString ) ) ) ) ) ) )* )
            {
             
            				  getUnorderedGroupHelper().enter(grammarAccess.getCategoryAccess().getUnorderedGroup_3());
            				
            // InternalConceptLanguage.g:1025:5: ( ( ({...}? => ( ({...}? => (otherlv_4= 'extends' ( ( ruleQualifiedName ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_6= 'shortname' ( (lv_shortName_7_0= RULE_ID ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_8= 'description' ( (lv_description_9_0= ruleEString ) ) ) ) ) ) )* )
            // InternalConceptLanguage.g:1026:6: ( ({...}? => ( ({...}? => (otherlv_4= 'extends' ( ( ruleQualifiedName ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_6= 'shortname' ( (lv_shortName_7_0= RULE_ID ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_8= 'description' ( (lv_description_9_0= ruleEString ) ) ) ) ) ) )*
            {
            // InternalConceptLanguage.g:1026:6: ( ({...}? => ( ({...}? => (otherlv_4= 'extends' ( ( ruleQualifiedName ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_6= 'shortname' ( (lv_shortName_7_0= RULE_ID ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_8= 'description' ( (lv_description_9_0= ruleEString ) ) ) ) ) ) )*
            loop17:
            do {
                int alt17=4;
                int LA17_0 = input.LA(1);

                if ( LA17_0 == 35 && getUnorderedGroupHelper().canSelect(grammarAccess.getCategoryAccess().getUnorderedGroup_3(), 0) ) {
                    alt17=1;
                }
                else if ( LA17_0 == 19 && getUnorderedGroupHelper().canSelect(grammarAccess.getCategoryAccess().getUnorderedGroup_3(), 1) ) {
                    alt17=2;
                }
                else if ( LA17_0 == 14 && getUnorderedGroupHelper().canSelect(grammarAccess.getCategoryAccess().getUnorderedGroup_3(), 2) ) {
                    alt17=3;
                }


                switch (alt17) {
            	case 1 :
            	    // InternalConceptLanguage.g:1027:4: ({...}? => ( ({...}? => (otherlv_4= 'extends' ( ( ruleQualifiedName ) ) ) ) ) )
            	    {
            	    // InternalConceptLanguage.g:1027:4: ({...}? => ( ({...}? => (otherlv_4= 'extends' ( ( ruleQualifiedName ) ) ) ) ) )
            	    // InternalConceptLanguage.g:1028:5: {...}? => ( ({...}? => (otherlv_4= 'extends' ( ( ruleQualifiedName ) ) ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getCategoryAccess().getUnorderedGroup_3(), 0) ) {
            	        throw new FailedPredicateException(input, "ruleCategory", "getUnorderedGroupHelper().canSelect(grammarAccess.getCategoryAccess().getUnorderedGroup_3(), 0)");
            	    }
            	    // InternalConceptLanguage.g:1028:105: ( ({...}? => (otherlv_4= 'extends' ( ( ruleQualifiedName ) ) ) ) )
            	    // InternalConceptLanguage.g:1029:6: ({...}? => (otherlv_4= 'extends' ( ( ruleQualifiedName ) ) ) )
            	    {

            	    						getUnorderedGroupHelper().select(grammarAccess.getCategoryAccess().getUnorderedGroup_3(), 0);
            	    					
            	    // InternalConceptLanguage.g:1032:9: ({...}? => (otherlv_4= 'extends' ( ( ruleQualifiedName ) ) ) )
            	    // InternalConceptLanguage.g:1032:10: {...}? => (otherlv_4= 'extends' ( ( ruleQualifiedName ) ) )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleCategory", "true");
            	    }
            	    // InternalConceptLanguage.g:1032:19: (otherlv_4= 'extends' ( ( ruleQualifiedName ) ) )
            	    // InternalConceptLanguage.g:1032:20: otherlv_4= 'extends' ( ( ruleQualifiedName ) )
            	    {
            	    otherlv_4=(Token)match(input,35,FOLLOW_3); 

            	    									newLeafNode(otherlv_4, grammarAccess.getCategoryAccess().getExtendsKeyword_3_0_0());
            	    								
            	    // InternalConceptLanguage.g:1036:9: ( ( ruleQualifiedName ) )
            	    // InternalConceptLanguage.g:1037:10: ( ruleQualifiedName )
            	    {
            	    // InternalConceptLanguage.g:1037:10: ( ruleQualifiedName )
            	    // InternalConceptLanguage.g:1038:11: ruleQualifiedName
            	    {

            	    											if (current==null) {
            	    												current = createModelElement(grammarAccess.getCategoryRule());
            	    											}
            	    										

            	    											newCompositeNode(grammarAccess.getCategoryAccess().getExtendsCategoryCategoryCrossReference_3_0_1_0());
            	    										
            	    pushFollow(FOLLOW_25);
            	    ruleQualifiedName();

            	    state._fsp--;


            	    											afterParserOrEnumRuleCall();
            	    										

            	    }


            	    }


            	    }


            	    }

            	     
            	    						getUnorderedGroupHelper().returnFromSelection(grammarAccess.getCategoryAccess().getUnorderedGroup_3());
            	    					

            	    }


            	    }


            	    }
            	    break;
            	case 2 :
            	    // InternalConceptLanguage.g:1058:4: ({...}? => ( ({...}? => (otherlv_6= 'shortname' ( (lv_shortName_7_0= RULE_ID ) ) ) ) ) )
            	    {
            	    // InternalConceptLanguage.g:1058:4: ({...}? => ( ({...}? => (otherlv_6= 'shortname' ( (lv_shortName_7_0= RULE_ID ) ) ) ) ) )
            	    // InternalConceptLanguage.g:1059:5: {...}? => ( ({...}? => (otherlv_6= 'shortname' ( (lv_shortName_7_0= RULE_ID ) ) ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getCategoryAccess().getUnorderedGroup_3(), 1) ) {
            	        throw new FailedPredicateException(input, "ruleCategory", "getUnorderedGroupHelper().canSelect(grammarAccess.getCategoryAccess().getUnorderedGroup_3(), 1)");
            	    }
            	    // InternalConceptLanguage.g:1059:105: ( ({...}? => (otherlv_6= 'shortname' ( (lv_shortName_7_0= RULE_ID ) ) ) ) )
            	    // InternalConceptLanguage.g:1060:6: ({...}? => (otherlv_6= 'shortname' ( (lv_shortName_7_0= RULE_ID ) ) ) )
            	    {

            	    						getUnorderedGroupHelper().select(grammarAccess.getCategoryAccess().getUnorderedGroup_3(), 1);
            	    					
            	    // InternalConceptLanguage.g:1063:9: ({...}? => (otherlv_6= 'shortname' ( (lv_shortName_7_0= RULE_ID ) ) ) )
            	    // InternalConceptLanguage.g:1063:10: {...}? => (otherlv_6= 'shortname' ( (lv_shortName_7_0= RULE_ID ) ) )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleCategory", "true");
            	    }
            	    // InternalConceptLanguage.g:1063:19: (otherlv_6= 'shortname' ( (lv_shortName_7_0= RULE_ID ) ) )
            	    // InternalConceptLanguage.g:1063:20: otherlv_6= 'shortname' ( (lv_shortName_7_0= RULE_ID ) )
            	    {
            	    otherlv_6=(Token)match(input,19,FOLLOW_3); 

            	    									newLeafNode(otherlv_6, grammarAccess.getCategoryAccess().getShortnameKeyword_3_1_0());
            	    								
            	    // InternalConceptLanguage.g:1067:9: ( (lv_shortName_7_0= RULE_ID ) )
            	    // InternalConceptLanguage.g:1068:10: (lv_shortName_7_0= RULE_ID )
            	    {
            	    // InternalConceptLanguage.g:1068:10: (lv_shortName_7_0= RULE_ID )
            	    // InternalConceptLanguage.g:1069:11: lv_shortName_7_0= RULE_ID
            	    {
            	    lv_shortName_7_0=(Token)match(input,RULE_ID,FOLLOW_25); 

            	    											newLeafNode(lv_shortName_7_0, grammarAccess.getCategoryAccess().getShortNameIDTerminalRuleCall_3_1_1_0());
            	    										

            	    											if (current==null) {
            	    												current = createModelElement(grammarAccess.getCategoryRule());
            	    											}
            	    											setWithLastConsumed(
            	    												current,
            	    												"shortName",
            	    												lv_shortName_7_0,
            	    												"org.eclipse.xtext.common.Terminals.ID");
            	    										

            	    }


            	    }


            	    }


            	    }

            	     
            	    						getUnorderedGroupHelper().returnFromSelection(grammarAccess.getCategoryAccess().getUnorderedGroup_3());
            	    					

            	    }


            	    }


            	    }
            	    break;
            	case 3 :
            	    // InternalConceptLanguage.g:1091:4: ({...}? => ( ({...}? => (otherlv_8= 'description' ( (lv_description_9_0= ruleEString ) ) ) ) ) )
            	    {
            	    // InternalConceptLanguage.g:1091:4: ({...}? => ( ({...}? => (otherlv_8= 'description' ( (lv_description_9_0= ruleEString ) ) ) ) ) )
            	    // InternalConceptLanguage.g:1092:5: {...}? => ( ({...}? => (otherlv_8= 'description' ( (lv_description_9_0= ruleEString ) ) ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getCategoryAccess().getUnorderedGroup_3(), 2) ) {
            	        throw new FailedPredicateException(input, "ruleCategory", "getUnorderedGroupHelper().canSelect(grammarAccess.getCategoryAccess().getUnorderedGroup_3(), 2)");
            	    }
            	    // InternalConceptLanguage.g:1092:105: ( ({...}? => (otherlv_8= 'description' ( (lv_description_9_0= ruleEString ) ) ) ) )
            	    // InternalConceptLanguage.g:1093:6: ({...}? => (otherlv_8= 'description' ( (lv_description_9_0= ruleEString ) ) ) )
            	    {

            	    						getUnorderedGroupHelper().select(grammarAccess.getCategoryAccess().getUnorderedGroup_3(), 2);
            	    					
            	    // InternalConceptLanguage.g:1096:9: ({...}? => (otherlv_8= 'description' ( (lv_description_9_0= ruleEString ) ) ) )
            	    // InternalConceptLanguage.g:1096:10: {...}? => (otherlv_8= 'description' ( (lv_description_9_0= ruleEString ) ) )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleCategory", "true");
            	    }
            	    // InternalConceptLanguage.g:1096:19: (otherlv_8= 'description' ( (lv_description_9_0= ruleEString ) ) )
            	    // InternalConceptLanguage.g:1096:20: otherlv_8= 'description' ( (lv_description_9_0= ruleEString ) )
            	    {
            	    otherlv_8=(Token)match(input,14,FOLLOW_5); 

            	    									newLeafNode(otherlv_8, grammarAccess.getCategoryAccess().getDescriptionKeyword_3_2_0());
            	    								
            	    // InternalConceptLanguage.g:1100:9: ( (lv_description_9_0= ruleEString ) )
            	    // InternalConceptLanguage.g:1101:10: (lv_description_9_0= ruleEString )
            	    {
            	    // InternalConceptLanguage.g:1101:10: (lv_description_9_0= ruleEString )
            	    // InternalConceptLanguage.g:1102:11: lv_description_9_0= ruleEString
            	    {

            	    											newCompositeNode(grammarAccess.getCategoryAccess().getDescriptionEStringParserRuleCall_3_2_1_0());
            	    										
            	    pushFollow(FOLLOW_25);
            	    lv_description_9_0=ruleEString();

            	    state._fsp--;


            	    											if (current==null) {
            	    												current = createModelElementForParent(grammarAccess.getCategoryRule());
            	    											}
            	    											set(
            	    												current,
            	    												"description",
            	    												lv_description_9_0,
            	    												"de.dlr.sc.virsat.model.concept.ConceptLanguage.EString");
            	    											afterParserOrEnumRuleCall();
            	    										

            	    }


            	    }


            	    }


            	    }

            	     
            	    						getUnorderedGroupHelper().returnFromSelection(grammarAccess.getCategoryAccess().getUnorderedGroup_3());
            	    					

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop17;
                }
            } while (true);


            }


            }

             
            				  getUnorderedGroupHelper().leave(grammarAccess.getCategoryAccess().getUnorderedGroup_3());
            				

            }

            otherlv_10=(Token)match(input,16,FOLLOW_26); 

            			newLeafNode(otherlv_10, grammarAccess.getCategoryAccess().getLeftCurlyBracketKeyword_4());
            		
            // InternalConceptLanguage.g:1136:3: ( ( ( ( ({...}? => ( ({...}? => ( ( (lv_isAbstract_12_0= 'IsAbstract' ) ) otherlv_13= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_14= 'Applicable' otherlv_15= 'For' ( (otherlv_16= '[' ( ( ruleQualifiedName ) ) (otherlv_18= ',' ( ( ruleQualifiedName ) ) )* otherlv_20= ']' ) | ( (lv_isApplicableForAll_21_0= 'All' ) ) ) otherlv_22= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_23= 'Cardinality' ( (lv_cardinality_24_0= RULE_INT ) ) otherlv_25= ';' ) ) ) ) )* ) ) )
            // InternalConceptLanguage.g:1137:4: ( ( ( ({...}? => ( ({...}? => ( ( (lv_isAbstract_12_0= 'IsAbstract' ) ) otherlv_13= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_14= 'Applicable' otherlv_15= 'For' ( (otherlv_16= '[' ( ( ruleQualifiedName ) ) (otherlv_18= ',' ( ( ruleQualifiedName ) ) )* otherlv_20= ']' ) | ( (lv_isApplicableForAll_21_0= 'All' ) ) ) otherlv_22= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_23= 'Cardinality' ( (lv_cardinality_24_0= RULE_INT ) ) otherlv_25= ';' ) ) ) ) )* ) )
            {
            // InternalConceptLanguage.g:1137:4: ( ( ( ({...}? => ( ({...}? => ( ( (lv_isAbstract_12_0= 'IsAbstract' ) ) otherlv_13= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_14= 'Applicable' otherlv_15= 'For' ( (otherlv_16= '[' ( ( ruleQualifiedName ) ) (otherlv_18= ',' ( ( ruleQualifiedName ) ) )* otherlv_20= ']' ) | ( (lv_isApplicableForAll_21_0= 'All' ) ) ) otherlv_22= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_23= 'Cardinality' ( (lv_cardinality_24_0= RULE_INT ) ) otherlv_25= ';' ) ) ) ) )* ) )
            // InternalConceptLanguage.g:1138:5: ( ( ({...}? => ( ({...}? => ( ( (lv_isAbstract_12_0= 'IsAbstract' ) ) otherlv_13= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_14= 'Applicable' otherlv_15= 'For' ( (otherlv_16= '[' ( ( ruleQualifiedName ) ) (otherlv_18= ',' ( ( ruleQualifiedName ) ) )* otherlv_20= ']' ) | ( (lv_isApplicableForAll_21_0= 'All' ) ) ) otherlv_22= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_23= 'Cardinality' ( (lv_cardinality_24_0= RULE_INT ) ) otherlv_25= ';' ) ) ) ) )* )
            {
             
            				  getUnorderedGroupHelper().enter(grammarAccess.getCategoryAccess().getUnorderedGroup_5());
            				
            // InternalConceptLanguage.g:1141:5: ( ( ({...}? => ( ({...}? => ( ( (lv_isAbstract_12_0= 'IsAbstract' ) ) otherlv_13= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_14= 'Applicable' otherlv_15= 'For' ( (otherlv_16= '[' ( ( ruleQualifiedName ) ) (otherlv_18= ',' ( ( ruleQualifiedName ) ) )* otherlv_20= ']' ) | ( (lv_isApplicableForAll_21_0= 'All' ) ) ) otherlv_22= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_23= 'Cardinality' ( (lv_cardinality_24_0= RULE_INT ) ) otherlv_25= ';' ) ) ) ) )* )
            // InternalConceptLanguage.g:1142:6: ( ({...}? => ( ({...}? => ( ( (lv_isAbstract_12_0= 'IsAbstract' ) ) otherlv_13= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_14= 'Applicable' otherlv_15= 'For' ( (otherlv_16= '[' ( ( ruleQualifiedName ) ) (otherlv_18= ',' ( ( ruleQualifiedName ) ) )* otherlv_20= ']' ) | ( (lv_isApplicableForAll_21_0= 'All' ) ) ) otherlv_22= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_23= 'Cardinality' ( (lv_cardinality_24_0= RULE_INT ) ) otherlv_25= ';' ) ) ) ) )*
            {
            // InternalConceptLanguage.g:1142:6: ( ({...}? => ( ({...}? => ( ( (lv_isAbstract_12_0= 'IsAbstract' ) ) otherlv_13= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_14= 'Applicable' otherlv_15= 'For' ( (otherlv_16= '[' ( ( ruleQualifiedName ) ) (otherlv_18= ',' ( ( ruleQualifiedName ) ) )* otherlv_20= ']' ) | ( (lv_isApplicableForAll_21_0= 'All' ) ) ) otherlv_22= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_23= 'Cardinality' ( (lv_cardinality_24_0= RULE_INT ) ) otherlv_25= ';' ) ) ) ) )*
            loop20:
            do {
                int alt20=4;
                int LA20_0 = input.LA(1);

                if ( LA20_0 == 36 && getUnorderedGroupHelper().canSelect(grammarAccess.getCategoryAccess().getUnorderedGroup_5(), 0) ) {
                    alt20=1;
                }
                else if ( LA20_0 == 28 && getUnorderedGroupHelper().canSelect(grammarAccess.getCategoryAccess().getUnorderedGroup_5(), 1) ) {
                    alt20=2;
                }
                else if ( LA20_0 == 30 && getUnorderedGroupHelper().canSelect(grammarAccess.getCategoryAccess().getUnorderedGroup_5(), 2) ) {
                    alt20=3;
                }


                switch (alt20) {
            	case 1 :
            	    // InternalConceptLanguage.g:1143:4: ({...}? => ( ({...}? => ( ( (lv_isAbstract_12_0= 'IsAbstract' ) ) otherlv_13= ';' ) ) ) )
            	    {
            	    // InternalConceptLanguage.g:1143:4: ({...}? => ( ({...}? => ( ( (lv_isAbstract_12_0= 'IsAbstract' ) ) otherlv_13= ';' ) ) ) )
            	    // InternalConceptLanguage.g:1144:5: {...}? => ( ({...}? => ( ( (lv_isAbstract_12_0= 'IsAbstract' ) ) otherlv_13= ';' ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getCategoryAccess().getUnorderedGroup_5(), 0) ) {
            	        throw new FailedPredicateException(input, "ruleCategory", "getUnorderedGroupHelper().canSelect(grammarAccess.getCategoryAccess().getUnorderedGroup_5(), 0)");
            	    }
            	    // InternalConceptLanguage.g:1144:105: ( ({...}? => ( ( (lv_isAbstract_12_0= 'IsAbstract' ) ) otherlv_13= ';' ) ) )
            	    // InternalConceptLanguage.g:1145:6: ({...}? => ( ( (lv_isAbstract_12_0= 'IsAbstract' ) ) otherlv_13= ';' ) )
            	    {

            	    						getUnorderedGroupHelper().select(grammarAccess.getCategoryAccess().getUnorderedGroup_5(), 0);
            	    					
            	    // InternalConceptLanguage.g:1148:9: ({...}? => ( ( (lv_isAbstract_12_0= 'IsAbstract' ) ) otherlv_13= ';' ) )
            	    // InternalConceptLanguage.g:1148:10: {...}? => ( ( (lv_isAbstract_12_0= 'IsAbstract' ) ) otherlv_13= ';' )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleCategory", "true");
            	    }
            	    // InternalConceptLanguage.g:1148:19: ( ( (lv_isAbstract_12_0= 'IsAbstract' ) ) otherlv_13= ';' )
            	    // InternalConceptLanguage.g:1148:20: ( (lv_isAbstract_12_0= 'IsAbstract' ) ) otherlv_13= ';'
            	    {
            	    // InternalConceptLanguage.g:1148:20: ( (lv_isAbstract_12_0= 'IsAbstract' ) )
            	    // InternalConceptLanguage.g:1149:10: (lv_isAbstract_12_0= 'IsAbstract' )
            	    {
            	    // InternalConceptLanguage.g:1149:10: (lv_isAbstract_12_0= 'IsAbstract' )
            	    // InternalConceptLanguage.g:1150:11: lv_isAbstract_12_0= 'IsAbstract'
            	    {
            	    lv_isAbstract_12_0=(Token)match(input,36,FOLLOW_13); 

            	    											newLeafNode(lv_isAbstract_12_0, grammarAccess.getCategoryAccess().getIsAbstractIsAbstractKeyword_5_0_0_0());
            	    										

            	    											if (current==null) {
            	    												current = createModelElement(grammarAccess.getCategoryRule());
            	    											}
            	    											setWithLastConsumed(current, "isAbstract", true, "IsAbstract");
            	    										

            	    }


            	    }

            	    otherlv_13=(Token)match(input,21,FOLLOW_26); 

            	    									newLeafNode(otherlv_13, grammarAccess.getCategoryAccess().getSemicolonKeyword_5_0_1());
            	    								

            	    }


            	    }

            	     
            	    						getUnorderedGroupHelper().returnFromSelection(grammarAccess.getCategoryAccess().getUnorderedGroup_5());
            	    					

            	    }


            	    }


            	    }
            	    break;
            	case 2 :
            	    // InternalConceptLanguage.g:1172:4: ({...}? => ( ({...}? => (otherlv_14= 'Applicable' otherlv_15= 'For' ( (otherlv_16= '[' ( ( ruleQualifiedName ) ) (otherlv_18= ',' ( ( ruleQualifiedName ) ) )* otherlv_20= ']' ) | ( (lv_isApplicableForAll_21_0= 'All' ) ) ) otherlv_22= ';' ) ) ) )
            	    {
            	    // InternalConceptLanguage.g:1172:4: ({...}? => ( ({...}? => (otherlv_14= 'Applicable' otherlv_15= 'For' ( (otherlv_16= '[' ( ( ruleQualifiedName ) ) (otherlv_18= ',' ( ( ruleQualifiedName ) ) )* otherlv_20= ']' ) | ( (lv_isApplicableForAll_21_0= 'All' ) ) ) otherlv_22= ';' ) ) ) )
            	    // InternalConceptLanguage.g:1173:5: {...}? => ( ({...}? => (otherlv_14= 'Applicable' otherlv_15= 'For' ( (otherlv_16= '[' ( ( ruleQualifiedName ) ) (otherlv_18= ',' ( ( ruleQualifiedName ) ) )* otherlv_20= ']' ) | ( (lv_isApplicableForAll_21_0= 'All' ) ) ) otherlv_22= ';' ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getCategoryAccess().getUnorderedGroup_5(), 1) ) {
            	        throw new FailedPredicateException(input, "ruleCategory", "getUnorderedGroupHelper().canSelect(grammarAccess.getCategoryAccess().getUnorderedGroup_5(), 1)");
            	    }
            	    // InternalConceptLanguage.g:1173:105: ( ({...}? => (otherlv_14= 'Applicable' otherlv_15= 'For' ( (otherlv_16= '[' ( ( ruleQualifiedName ) ) (otherlv_18= ',' ( ( ruleQualifiedName ) ) )* otherlv_20= ']' ) | ( (lv_isApplicableForAll_21_0= 'All' ) ) ) otherlv_22= ';' ) ) )
            	    // InternalConceptLanguage.g:1174:6: ({...}? => (otherlv_14= 'Applicable' otherlv_15= 'For' ( (otherlv_16= '[' ( ( ruleQualifiedName ) ) (otherlv_18= ',' ( ( ruleQualifiedName ) ) )* otherlv_20= ']' ) | ( (lv_isApplicableForAll_21_0= 'All' ) ) ) otherlv_22= ';' ) )
            	    {

            	    						getUnorderedGroupHelper().select(grammarAccess.getCategoryAccess().getUnorderedGroup_5(), 1);
            	    					
            	    // InternalConceptLanguage.g:1177:9: ({...}? => (otherlv_14= 'Applicable' otherlv_15= 'For' ( (otherlv_16= '[' ( ( ruleQualifiedName ) ) (otherlv_18= ',' ( ( ruleQualifiedName ) ) )* otherlv_20= ']' ) | ( (lv_isApplicableForAll_21_0= 'All' ) ) ) otherlv_22= ';' ) )
            	    // InternalConceptLanguage.g:1177:10: {...}? => (otherlv_14= 'Applicable' otherlv_15= 'For' ( (otherlv_16= '[' ( ( ruleQualifiedName ) ) (otherlv_18= ',' ( ( ruleQualifiedName ) ) )* otherlv_20= ']' ) | ( (lv_isApplicableForAll_21_0= 'All' ) ) ) otherlv_22= ';' )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleCategory", "true");
            	    }
            	    // InternalConceptLanguage.g:1177:19: (otherlv_14= 'Applicable' otherlv_15= 'For' ( (otherlv_16= '[' ( ( ruleQualifiedName ) ) (otherlv_18= ',' ( ( ruleQualifiedName ) ) )* otherlv_20= ']' ) | ( (lv_isApplicableForAll_21_0= 'All' ) ) ) otherlv_22= ';' )
            	    // InternalConceptLanguage.g:1177:20: otherlv_14= 'Applicable' otherlv_15= 'For' ( (otherlv_16= '[' ( ( ruleQualifiedName ) ) (otherlv_18= ',' ( ( ruleQualifiedName ) ) )* otherlv_20= ']' ) | ( (lv_isApplicableForAll_21_0= 'All' ) ) ) otherlv_22= ';'
            	    {
            	    otherlv_14=(Token)match(input,28,FOLLOW_17); 

            	    									newLeafNode(otherlv_14, grammarAccess.getCategoryAccess().getApplicableKeyword_5_1_0());
            	    								
            	    otherlv_15=(Token)match(input,29,FOLLOW_15); 

            	    									newLeafNode(otherlv_15, grammarAccess.getCategoryAccess().getForKeyword_5_1_1());
            	    								
            	    // InternalConceptLanguage.g:1185:9: ( (otherlv_16= '[' ( ( ruleQualifiedName ) ) (otherlv_18= ',' ( ( ruleQualifiedName ) ) )* otherlv_20= ']' ) | ( (lv_isApplicableForAll_21_0= 'All' ) ) )
            	    int alt19=2;
            	    int LA19_0 = input.LA(1);

            	    if ( (LA19_0==24) ) {
            	        alt19=1;
            	    }
            	    else if ( (LA19_0==27) ) {
            	        alt19=2;
            	    }
            	    else {
            	        NoViableAltException nvae =
            	            new NoViableAltException("", 19, 0, input);

            	        throw nvae;
            	    }
            	    switch (alt19) {
            	        case 1 :
            	            // InternalConceptLanguage.g:1186:10: (otherlv_16= '[' ( ( ruleQualifiedName ) ) (otherlv_18= ',' ( ( ruleQualifiedName ) ) )* otherlv_20= ']' )
            	            {
            	            // InternalConceptLanguage.g:1186:10: (otherlv_16= '[' ( ( ruleQualifiedName ) ) (otherlv_18= ',' ( ( ruleQualifiedName ) ) )* otherlv_20= ']' )
            	            // InternalConceptLanguage.g:1187:11: otherlv_16= '[' ( ( ruleQualifiedName ) ) (otherlv_18= ',' ( ( ruleQualifiedName ) ) )* otherlv_20= ']'
            	            {
            	            otherlv_16=(Token)match(input,24,FOLLOW_3); 

            	            											newLeafNode(otherlv_16, grammarAccess.getCategoryAccess().getLeftSquareBracketKeyword_5_1_2_0_0());
            	            										
            	            // InternalConceptLanguage.g:1191:11: ( ( ruleQualifiedName ) )
            	            // InternalConceptLanguage.g:1192:12: ( ruleQualifiedName )
            	            {
            	            // InternalConceptLanguage.g:1192:12: ( ruleQualifiedName )
            	            // InternalConceptLanguage.g:1193:13: ruleQualifiedName
            	            {

            	            													if (current==null) {
            	            														current = createModelElement(grammarAccess.getCategoryRule());
            	            													}
            	            												

            	            													newCompositeNode(grammarAccess.getCategoryAccess().getApplicableForStructuralElementCrossReference_5_1_2_0_1_0());
            	            												
            	            pushFollow(FOLLOW_16);
            	            ruleQualifiedName();

            	            state._fsp--;


            	            													afterParserOrEnumRuleCall();
            	            												

            	            }


            	            }

            	            // InternalConceptLanguage.g:1207:11: (otherlv_18= ',' ( ( ruleQualifiedName ) ) )*
            	            loop18:
            	            do {
            	                int alt18=2;
            	                int LA18_0 = input.LA(1);

            	                if ( (LA18_0==25) ) {
            	                    alt18=1;
            	                }


            	                switch (alt18) {
            	            	case 1 :
            	            	    // InternalConceptLanguage.g:1208:12: otherlv_18= ',' ( ( ruleQualifiedName ) )
            	            	    {
            	            	    otherlv_18=(Token)match(input,25,FOLLOW_3); 

            	            	    												newLeafNode(otherlv_18, grammarAccess.getCategoryAccess().getCommaKeyword_5_1_2_0_2_0());
            	            	    											
            	            	    // InternalConceptLanguage.g:1212:12: ( ( ruleQualifiedName ) )
            	            	    // InternalConceptLanguage.g:1213:13: ( ruleQualifiedName )
            	            	    {
            	            	    // InternalConceptLanguage.g:1213:13: ( ruleQualifiedName )
            	            	    // InternalConceptLanguage.g:1214:14: ruleQualifiedName
            	            	    {

            	            	    														if (current==null) {
            	            	    															current = createModelElement(grammarAccess.getCategoryRule());
            	            	    														}
            	            	    													

            	            	    														newCompositeNode(grammarAccess.getCategoryAccess().getApplicableForStructuralElementCrossReference_5_1_2_0_2_1_0());
            	            	    													
            	            	    pushFollow(FOLLOW_16);
            	            	    ruleQualifiedName();

            	            	    state._fsp--;


            	            	    														afterParserOrEnumRuleCall();
            	            	    													

            	            	    }


            	            	    }


            	            	    }
            	            	    break;

            	            	default :
            	            	    break loop18;
            	                }
            	            } while (true);

            	            otherlv_20=(Token)match(input,26,FOLLOW_13); 

            	            											newLeafNode(otherlv_20, grammarAccess.getCategoryAccess().getRightSquareBracketKeyword_5_1_2_0_3());
            	            										

            	            }


            	            }
            	            break;
            	        case 2 :
            	            // InternalConceptLanguage.g:1235:10: ( (lv_isApplicableForAll_21_0= 'All' ) )
            	            {
            	            // InternalConceptLanguage.g:1235:10: ( (lv_isApplicableForAll_21_0= 'All' ) )
            	            // InternalConceptLanguage.g:1236:11: (lv_isApplicableForAll_21_0= 'All' )
            	            {
            	            // InternalConceptLanguage.g:1236:11: (lv_isApplicableForAll_21_0= 'All' )
            	            // InternalConceptLanguage.g:1237:12: lv_isApplicableForAll_21_0= 'All'
            	            {
            	            lv_isApplicableForAll_21_0=(Token)match(input,27,FOLLOW_13); 

            	            												newLeafNode(lv_isApplicableForAll_21_0, grammarAccess.getCategoryAccess().getIsApplicableForAllAllKeyword_5_1_2_1_0());
            	            											

            	            												if (current==null) {
            	            													current = createModelElement(grammarAccess.getCategoryRule());
            	            												}
            	            												setWithLastConsumed(current, "isApplicableForAll", true, "All");
            	            											

            	            }


            	            }


            	            }
            	            break;

            	    }

            	    otherlv_22=(Token)match(input,21,FOLLOW_26); 

            	    									newLeafNode(otherlv_22, grammarAccess.getCategoryAccess().getSemicolonKeyword_5_1_3());
            	    								

            	    }


            	    }

            	     
            	    						getUnorderedGroupHelper().returnFromSelection(grammarAccess.getCategoryAccess().getUnorderedGroup_5());
            	    					

            	    }


            	    }


            	    }
            	    break;
            	case 3 :
            	    // InternalConceptLanguage.g:1260:4: ({...}? => ( ({...}? => (otherlv_23= 'Cardinality' ( (lv_cardinality_24_0= RULE_INT ) ) otherlv_25= ';' ) ) ) )
            	    {
            	    // InternalConceptLanguage.g:1260:4: ({...}? => ( ({...}? => (otherlv_23= 'Cardinality' ( (lv_cardinality_24_0= RULE_INT ) ) otherlv_25= ';' ) ) ) )
            	    // InternalConceptLanguage.g:1261:5: {...}? => ( ({...}? => (otherlv_23= 'Cardinality' ( (lv_cardinality_24_0= RULE_INT ) ) otherlv_25= ';' ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getCategoryAccess().getUnorderedGroup_5(), 2) ) {
            	        throw new FailedPredicateException(input, "ruleCategory", "getUnorderedGroupHelper().canSelect(grammarAccess.getCategoryAccess().getUnorderedGroup_5(), 2)");
            	    }
            	    // InternalConceptLanguage.g:1261:105: ( ({...}? => (otherlv_23= 'Cardinality' ( (lv_cardinality_24_0= RULE_INT ) ) otherlv_25= ';' ) ) )
            	    // InternalConceptLanguage.g:1262:6: ({...}? => (otherlv_23= 'Cardinality' ( (lv_cardinality_24_0= RULE_INT ) ) otherlv_25= ';' ) )
            	    {

            	    						getUnorderedGroupHelper().select(grammarAccess.getCategoryAccess().getUnorderedGroup_5(), 2);
            	    					
            	    // InternalConceptLanguage.g:1265:9: ({...}? => (otherlv_23= 'Cardinality' ( (lv_cardinality_24_0= RULE_INT ) ) otherlv_25= ';' ) )
            	    // InternalConceptLanguage.g:1265:10: {...}? => (otherlv_23= 'Cardinality' ( (lv_cardinality_24_0= RULE_INT ) ) otherlv_25= ';' )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleCategory", "true");
            	    }
            	    // InternalConceptLanguage.g:1265:19: (otherlv_23= 'Cardinality' ( (lv_cardinality_24_0= RULE_INT ) ) otherlv_25= ';' )
            	    // InternalConceptLanguage.g:1265:20: otherlv_23= 'Cardinality' ( (lv_cardinality_24_0= RULE_INT ) ) otherlv_25= ';'
            	    {
            	    otherlv_23=(Token)match(input,30,FOLLOW_6); 

            	    									newLeafNode(otherlv_23, grammarAccess.getCategoryAccess().getCardinalityKeyword_5_2_0());
            	    								
            	    // InternalConceptLanguage.g:1269:9: ( (lv_cardinality_24_0= RULE_INT ) )
            	    // InternalConceptLanguage.g:1270:10: (lv_cardinality_24_0= RULE_INT )
            	    {
            	    // InternalConceptLanguage.g:1270:10: (lv_cardinality_24_0= RULE_INT )
            	    // InternalConceptLanguage.g:1271:11: lv_cardinality_24_0= RULE_INT
            	    {
            	    lv_cardinality_24_0=(Token)match(input,RULE_INT,FOLLOW_13); 

            	    											newLeafNode(lv_cardinality_24_0, grammarAccess.getCategoryAccess().getCardinalityINTTerminalRuleCall_5_2_1_0());
            	    										

            	    											if (current==null) {
            	    												current = createModelElement(grammarAccess.getCategoryRule());
            	    											}
            	    											setWithLastConsumed(
            	    												current,
            	    												"cardinality",
            	    												lv_cardinality_24_0,
            	    												"org.eclipse.xtext.common.Terminals.INT");
            	    										

            	    }


            	    }

            	    otherlv_25=(Token)match(input,21,FOLLOW_26); 

            	    									newLeafNode(otherlv_25, grammarAccess.getCategoryAccess().getSemicolonKeyword_5_2_2());
            	    								

            	    }


            	    }

            	     
            	    						getUnorderedGroupHelper().returnFromSelection(grammarAccess.getCategoryAccess().getUnorderedGroup_5());
            	    					

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop20;
                }
            } while (true);


            }


            }

             
            				  getUnorderedGroupHelper().leave(grammarAccess.getCategoryAccess().getUnorderedGroup_5());
            				

            }

            // InternalConceptLanguage.g:1304:3: ( (lv_properties_26_0= ruleAProperty ) )*
            loop21:
            do {
                int alt21=2;
                int LA21_0 = input.LA(1);

                if ( (LA21_0==33||LA21_0==41||(LA21_0>=43 && LA21_0<=46)||(LA21_0>=49 && LA21_0<=50)) ) {
                    alt21=1;
                }


                switch (alt21) {
            	case 1 :
            	    // InternalConceptLanguage.g:1305:4: (lv_properties_26_0= ruleAProperty )
            	    {
            	    // InternalConceptLanguage.g:1305:4: (lv_properties_26_0= ruleAProperty )
            	    // InternalConceptLanguage.g:1306:5: lv_properties_26_0= ruleAProperty
            	    {

            	    					newCompositeNode(grammarAccess.getCategoryAccess().getPropertiesAPropertyParserRuleCall_6_0());
            	    				
            	    pushFollow(FOLLOW_27);
            	    lv_properties_26_0=ruleAProperty();

            	    state._fsp--;


            	    					if (current==null) {
            	    						current = createModelElementForParent(grammarAccess.getCategoryRule());
            	    					}
            	    					add(
            	    						current,
            	    						"properties",
            	    						lv_properties_26_0,
            	    						"de.dlr.sc.virsat.model.concept.ConceptLanguage.AProperty");
            	    					afterParserOrEnumRuleCall();
            	    				

            	    }


            	    }
            	    break;

            	default :
            	    break loop21;
                }
            } while (true);

            // InternalConceptLanguage.g:1323:3: ( (lv_equationDefinitions_27_0= ruleEquationDefinition ) )*
            loop22:
            do {
                int alt22=2;
                int LA22_0 = input.LA(1);

                if ( ((LA22_0>=51 && LA22_0<=52)) ) {
                    alt22=1;
                }


                switch (alt22) {
            	case 1 :
            	    // InternalConceptLanguage.g:1324:4: (lv_equationDefinitions_27_0= ruleEquationDefinition )
            	    {
            	    // InternalConceptLanguage.g:1324:4: (lv_equationDefinitions_27_0= ruleEquationDefinition )
            	    // InternalConceptLanguage.g:1325:5: lv_equationDefinitions_27_0= ruleEquationDefinition
            	    {

            	    					newCompositeNode(grammarAccess.getCategoryAccess().getEquationDefinitionsEquationDefinitionParserRuleCall_7_0());
            	    				
            	    pushFollow(FOLLOW_28);
            	    lv_equationDefinitions_27_0=ruleEquationDefinition();

            	    state._fsp--;


            	    					if (current==null) {
            	    						current = createModelElementForParent(grammarAccess.getCategoryRule());
            	    					}
            	    					add(
            	    						current,
            	    						"equationDefinitions",
            	    						lv_equationDefinitions_27_0,
            	    						"de.dlr.sc.virsat.model.concept.ConceptLanguage.EquationDefinition");
            	    					afterParserOrEnumRuleCall();
            	    				

            	    }


            	    }
            	    break;

            	default :
            	    break loop22;
                }
            } while (true);

            otherlv_28=(Token)match(input,17,FOLLOW_2); 

            			newLeafNode(otherlv_28, grammarAccess.getCategoryAccess().getRightCurlyBracketKeyword_8());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleCategory"


    // $ANTLR start "entryRuleConceptImport"
    // InternalConceptLanguage.g:1350:1: entryRuleConceptImport returns [EObject current=null] : iv_ruleConceptImport= ruleConceptImport EOF ;
    public final EObject entryRuleConceptImport() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleConceptImport = null;


        try {
            // InternalConceptLanguage.g:1350:54: (iv_ruleConceptImport= ruleConceptImport EOF )
            // InternalConceptLanguage.g:1351:2: iv_ruleConceptImport= ruleConceptImport EOF
            {
             newCompositeNode(grammarAccess.getConceptImportRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleConceptImport=ruleConceptImport();

            state._fsp--;

             current =iv_ruleConceptImport; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleConceptImport"


    // $ANTLR start "ruleConceptImport"
    // InternalConceptLanguage.g:1357:1: ruleConceptImport returns [EObject current=null] : ( () otherlv_1= 'Import' ( (lv_importedNamespace_2_0= ruleQualifiedNameWithWildcard ) ) otherlv_3= ';' ) ;
    public final EObject ruleConceptImport() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_3=null;
        AntlrDatatypeRuleToken lv_importedNamespace_2_0 = null;



        	enterRule();

        try {
            // InternalConceptLanguage.g:1363:2: ( ( () otherlv_1= 'Import' ( (lv_importedNamespace_2_0= ruleQualifiedNameWithWildcard ) ) otherlv_3= ';' ) )
            // InternalConceptLanguage.g:1364:2: ( () otherlv_1= 'Import' ( (lv_importedNamespace_2_0= ruleQualifiedNameWithWildcard ) ) otherlv_3= ';' )
            {
            // InternalConceptLanguage.g:1364:2: ( () otherlv_1= 'Import' ( (lv_importedNamespace_2_0= ruleQualifiedNameWithWildcard ) ) otherlv_3= ';' )
            // InternalConceptLanguage.g:1365:3: () otherlv_1= 'Import' ( (lv_importedNamespace_2_0= ruleQualifiedNameWithWildcard ) ) otherlv_3= ';'
            {
            // InternalConceptLanguage.g:1365:3: ()
            // InternalConceptLanguage.g:1366:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getConceptImportAccess().getConceptImportAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,37,FOLLOW_3); 

            			newLeafNode(otherlv_1, grammarAccess.getConceptImportAccess().getImportKeyword_1());
            		
            // InternalConceptLanguage.g:1376:3: ( (lv_importedNamespace_2_0= ruleQualifiedNameWithWildcard ) )
            // InternalConceptLanguage.g:1377:4: (lv_importedNamespace_2_0= ruleQualifiedNameWithWildcard )
            {
            // InternalConceptLanguage.g:1377:4: (lv_importedNamespace_2_0= ruleQualifiedNameWithWildcard )
            // InternalConceptLanguage.g:1378:5: lv_importedNamespace_2_0= ruleQualifiedNameWithWildcard
            {

            					newCompositeNode(grammarAccess.getConceptImportAccess().getImportedNamespaceQualifiedNameWithWildcardParserRuleCall_2_0());
            				
            pushFollow(FOLLOW_13);
            lv_importedNamespace_2_0=ruleQualifiedNameWithWildcard();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getConceptImportRule());
            					}
            					set(
            						current,
            						"importedNamespace",
            						lv_importedNamespace_2_0,
            						"de.dlr.sc.virsat.model.concept.ConceptLanguage.QualifiedNameWithWildcard");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            otherlv_3=(Token)match(input,21,FOLLOW_2); 

            			newLeafNode(otherlv_3, grammarAccess.getConceptImportAccess().getSemicolonKeyword_3());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleConceptImport"


    // $ANTLR start "entryRuleAProperty"
    // InternalConceptLanguage.g:1403:1: entryRuleAProperty returns [EObject current=null] : iv_ruleAProperty= ruleAProperty EOF ;
    public final EObject entryRuleAProperty() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleAProperty = null;


        try {
            // InternalConceptLanguage.g:1403:50: (iv_ruleAProperty= ruleAProperty EOF )
            // InternalConceptLanguage.g:1404:2: iv_ruleAProperty= ruleAProperty EOF
            {
             newCompositeNode(grammarAccess.getAPropertyRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleAProperty=ruleAProperty();

            state._fsp--;

             current =iv_ruleAProperty; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleAProperty"


    // $ANTLR start "ruleAProperty"
    // InternalConceptLanguage.g:1410:1: ruleAProperty returns [EObject current=null] : (this_ComposedProperty_0= ruleComposedProperty | this_IntProperty_1= ruleIntProperty | this_FloatProperty_2= ruleFloatProperty | this_StringProperty_3= ruleStringProperty | this_BooleanProperty_4= ruleBooleanProperty | this_EnumProperty_5= ruleEnumProperty | this_ReferenceProperty_6= ruleReferenceProperty | this_ResourceProperty_7= ruleResourceProperty ) ;
    public final EObject ruleAProperty() throws RecognitionException {
        EObject current = null;

        EObject this_ComposedProperty_0 = null;

        EObject this_IntProperty_1 = null;

        EObject this_FloatProperty_2 = null;

        EObject this_StringProperty_3 = null;

        EObject this_BooleanProperty_4 = null;

        EObject this_EnumProperty_5 = null;

        EObject this_ReferenceProperty_6 = null;

        EObject this_ResourceProperty_7 = null;



        	enterRule();

        try {
            // InternalConceptLanguage.g:1416:2: ( (this_ComposedProperty_0= ruleComposedProperty | this_IntProperty_1= ruleIntProperty | this_FloatProperty_2= ruleFloatProperty | this_StringProperty_3= ruleStringProperty | this_BooleanProperty_4= ruleBooleanProperty | this_EnumProperty_5= ruleEnumProperty | this_ReferenceProperty_6= ruleReferenceProperty | this_ResourceProperty_7= ruleResourceProperty ) )
            // InternalConceptLanguage.g:1417:2: (this_ComposedProperty_0= ruleComposedProperty | this_IntProperty_1= ruleIntProperty | this_FloatProperty_2= ruleFloatProperty | this_StringProperty_3= ruleStringProperty | this_BooleanProperty_4= ruleBooleanProperty | this_EnumProperty_5= ruleEnumProperty | this_ReferenceProperty_6= ruleReferenceProperty | this_ResourceProperty_7= ruleResourceProperty )
            {
            // InternalConceptLanguage.g:1417:2: (this_ComposedProperty_0= ruleComposedProperty | this_IntProperty_1= ruleIntProperty | this_FloatProperty_2= ruleFloatProperty | this_StringProperty_3= ruleStringProperty | this_BooleanProperty_4= ruleBooleanProperty | this_EnumProperty_5= ruleEnumProperty | this_ReferenceProperty_6= ruleReferenceProperty | this_ResourceProperty_7= ruleResourceProperty )
            int alt23=8;
            switch ( input.LA(1) ) {
            case 33:
                {
                alt23=1;
                }
                break;
            case 41:
                {
                alt23=2;
                }
                break;
            case 43:
                {
                alt23=3;
                }
                break;
            case 44:
                {
                alt23=4;
                }
                break;
            case 45:
                {
                alt23=5;
                }
                break;
            case 46:
                {
                alt23=6;
                }
                break;
            case 49:
                {
                alt23=7;
                }
                break;
            case 50:
                {
                alt23=8;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 23, 0, input);

                throw nvae;
            }

            switch (alt23) {
                case 1 :
                    // InternalConceptLanguage.g:1418:3: this_ComposedProperty_0= ruleComposedProperty
                    {

                    			newCompositeNode(grammarAccess.getAPropertyAccess().getComposedPropertyParserRuleCall_0());
                    		
                    pushFollow(FOLLOW_2);
                    this_ComposedProperty_0=ruleComposedProperty();

                    state._fsp--;


                    			current = this_ComposedProperty_0;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 2 :
                    // InternalConceptLanguage.g:1427:3: this_IntProperty_1= ruleIntProperty
                    {

                    			newCompositeNode(grammarAccess.getAPropertyAccess().getIntPropertyParserRuleCall_1());
                    		
                    pushFollow(FOLLOW_2);
                    this_IntProperty_1=ruleIntProperty();

                    state._fsp--;


                    			current = this_IntProperty_1;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 3 :
                    // InternalConceptLanguage.g:1436:3: this_FloatProperty_2= ruleFloatProperty
                    {

                    			newCompositeNode(grammarAccess.getAPropertyAccess().getFloatPropertyParserRuleCall_2());
                    		
                    pushFollow(FOLLOW_2);
                    this_FloatProperty_2=ruleFloatProperty();

                    state._fsp--;


                    			current = this_FloatProperty_2;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 4 :
                    // InternalConceptLanguage.g:1445:3: this_StringProperty_3= ruleStringProperty
                    {

                    			newCompositeNode(grammarAccess.getAPropertyAccess().getStringPropertyParserRuleCall_3());
                    		
                    pushFollow(FOLLOW_2);
                    this_StringProperty_3=ruleStringProperty();

                    state._fsp--;


                    			current = this_StringProperty_3;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 5 :
                    // InternalConceptLanguage.g:1454:3: this_BooleanProperty_4= ruleBooleanProperty
                    {

                    			newCompositeNode(grammarAccess.getAPropertyAccess().getBooleanPropertyParserRuleCall_4());
                    		
                    pushFollow(FOLLOW_2);
                    this_BooleanProperty_4=ruleBooleanProperty();

                    state._fsp--;


                    			current = this_BooleanProperty_4;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 6 :
                    // InternalConceptLanguage.g:1463:3: this_EnumProperty_5= ruleEnumProperty
                    {

                    			newCompositeNode(grammarAccess.getAPropertyAccess().getEnumPropertyParserRuleCall_5());
                    		
                    pushFollow(FOLLOW_2);
                    this_EnumProperty_5=ruleEnumProperty();

                    state._fsp--;


                    			current = this_EnumProperty_5;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 7 :
                    // InternalConceptLanguage.g:1472:3: this_ReferenceProperty_6= ruleReferenceProperty
                    {

                    			newCompositeNode(grammarAccess.getAPropertyAccess().getReferencePropertyParserRuleCall_6());
                    		
                    pushFollow(FOLLOW_2);
                    this_ReferenceProperty_6=ruleReferenceProperty();

                    state._fsp--;


                    			current = this_ReferenceProperty_6;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 8 :
                    // InternalConceptLanguage.g:1481:3: this_ResourceProperty_7= ruleResourceProperty
                    {

                    			newCompositeNode(grammarAccess.getAPropertyAccess().getResourcePropertyParserRuleCall_7());
                    		
                    pushFollow(FOLLOW_2);
                    this_ResourceProperty_7=ruleResourceProperty();

                    state._fsp--;


                    			current = this_ResourceProperty_7;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleAProperty"


    // $ANTLR start "entryRuleArrayModifier"
    // InternalConceptLanguage.g:1493:1: entryRuleArrayModifier returns [EObject current=null] : iv_ruleArrayModifier= ruleArrayModifier EOF ;
    public final EObject entryRuleArrayModifier() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleArrayModifier = null;


        try {
            // InternalConceptLanguage.g:1493:54: (iv_ruleArrayModifier= ruleArrayModifier EOF )
            // InternalConceptLanguage.g:1494:2: iv_ruleArrayModifier= ruleArrayModifier EOF
            {
             newCompositeNode(grammarAccess.getArrayModifierRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleArrayModifier=ruleArrayModifier();

            state._fsp--;

             current =iv_ruleArrayModifier; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleArrayModifier"


    // $ANTLR start "ruleArrayModifier"
    // InternalConceptLanguage.g:1500:1: ruleArrayModifier returns [EObject current=null] : (this_DynmaicArrayModifier_0= ruleDynmaicArrayModifier | this_StaticArrayModifier_1= ruleStaticArrayModifier ) ;
    public final EObject ruleArrayModifier() throws RecognitionException {
        EObject current = null;

        EObject this_DynmaicArrayModifier_0 = null;

        EObject this_StaticArrayModifier_1 = null;



        	enterRule();

        try {
            // InternalConceptLanguage.g:1506:2: ( (this_DynmaicArrayModifier_0= ruleDynmaicArrayModifier | this_StaticArrayModifier_1= ruleStaticArrayModifier ) )
            // InternalConceptLanguage.g:1507:2: (this_DynmaicArrayModifier_0= ruleDynmaicArrayModifier | this_StaticArrayModifier_1= ruleStaticArrayModifier )
            {
            // InternalConceptLanguage.g:1507:2: (this_DynmaicArrayModifier_0= ruleDynmaicArrayModifier | this_StaticArrayModifier_1= ruleStaticArrayModifier )
            int alt24=2;
            int LA24_0 = input.LA(1);

            if ( (LA24_0==24) ) {
                int LA24_1 = input.LA(2);

                if ( (LA24_1==RULE_INT) ) {
                    alt24=2;
                }
                else if ( (LA24_1==26) ) {
                    alt24=1;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 24, 1, input);

                    throw nvae;
                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 24, 0, input);

                throw nvae;
            }
            switch (alt24) {
                case 1 :
                    // InternalConceptLanguage.g:1508:3: this_DynmaicArrayModifier_0= ruleDynmaicArrayModifier
                    {

                    			newCompositeNode(grammarAccess.getArrayModifierAccess().getDynmaicArrayModifierParserRuleCall_0());
                    		
                    pushFollow(FOLLOW_2);
                    this_DynmaicArrayModifier_0=ruleDynmaicArrayModifier();

                    state._fsp--;


                    			current = this_DynmaicArrayModifier_0;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 2 :
                    // InternalConceptLanguage.g:1517:3: this_StaticArrayModifier_1= ruleStaticArrayModifier
                    {

                    			newCompositeNode(grammarAccess.getArrayModifierAccess().getStaticArrayModifierParserRuleCall_1());
                    		
                    pushFollow(FOLLOW_2);
                    this_StaticArrayModifier_1=ruleStaticArrayModifier();

                    state._fsp--;


                    			current = this_StaticArrayModifier_1;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleArrayModifier"


    // $ANTLR start "entryRuleDynmaicArrayModifier"
    // InternalConceptLanguage.g:1529:1: entryRuleDynmaicArrayModifier returns [EObject current=null] : iv_ruleDynmaicArrayModifier= ruleDynmaicArrayModifier EOF ;
    public final EObject entryRuleDynmaicArrayModifier() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleDynmaicArrayModifier = null;


        try {
            // InternalConceptLanguage.g:1529:61: (iv_ruleDynmaicArrayModifier= ruleDynmaicArrayModifier EOF )
            // InternalConceptLanguage.g:1530:2: iv_ruleDynmaicArrayModifier= ruleDynmaicArrayModifier EOF
            {
             newCompositeNode(grammarAccess.getDynmaicArrayModifierRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleDynmaicArrayModifier=ruleDynmaicArrayModifier();

            state._fsp--;

             current =iv_ruleDynmaicArrayModifier; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleDynmaicArrayModifier"


    // $ANTLR start "ruleDynmaicArrayModifier"
    // InternalConceptLanguage.g:1536:1: ruleDynmaicArrayModifier returns [EObject current=null] : ( () otherlv_1= '[' otherlv_2= ']' ) ;
    public final EObject ruleDynmaicArrayModifier() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_2=null;


        	enterRule();

        try {
            // InternalConceptLanguage.g:1542:2: ( ( () otherlv_1= '[' otherlv_2= ']' ) )
            // InternalConceptLanguage.g:1543:2: ( () otherlv_1= '[' otherlv_2= ']' )
            {
            // InternalConceptLanguage.g:1543:2: ( () otherlv_1= '[' otherlv_2= ']' )
            // InternalConceptLanguage.g:1544:3: () otherlv_1= '[' otherlv_2= ']'
            {
            // InternalConceptLanguage.g:1544:3: ()
            // InternalConceptLanguage.g:1545:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getDynmaicArrayModifierAccess().getDynamicArrayModifierAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,24,FOLLOW_29); 

            			newLeafNode(otherlv_1, grammarAccess.getDynmaicArrayModifierAccess().getLeftSquareBracketKeyword_1());
            		
            otherlv_2=(Token)match(input,26,FOLLOW_2); 

            			newLeafNode(otherlv_2, grammarAccess.getDynmaicArrayModifierAccess().getRightSquareBracketKeyword_2());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleDynmaicArrayModifier"


    // $ANTLR start "entryRuleStaticArrayModifier"
    // InternalConceptLanguage.g:1563:1: entryRuleStaticArrayModifier returns [EObject current=null] : iv_ruleStaticArrayModifier= ruleStaticArrayModifier EOF ;
    public final EObject entryRuleStaticArrayModifier() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleStaticArrayModifier = null;


        try {
            // InternalConceptLanguage.g:1563:60: (iv_ruleStaticArrayModifier= ruleStaticArrayModifier EOF )
            // InternalConceptLanguage.g:1564:2: iv_ruleStaticArrayModifier= ruleStaticArrayModifier EOF
            {
             newCompositeNode(grammarAccess.getStaticArrayModifierRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleStaticArrayModifier=ruleStaticArrayModifier();

            state._fsp--;

             current =iv_ruleStaticArrayModifier; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleStaticArrayModifier"


    // $ANTLR start "ruleStaticArrayModifier"
    // InternalConceptLanguage.g:1570:1: ruleStaticArrayModifier returns [EObject current=null] : (otherlv_0= '[' ( (lv_arraySize_1_0= RULE_INT ) ) otherlv_2= ']' ) ;
    public final EObject ruleStaticArrayModifier() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token lv_arraySize_1_0=null;
        Token otherlv_2=null;


        	enterRule();

        try {
            // InternalConceptLanguage.g:1576:2: ( (otherlv_0= '[' ( (lv_arraySize_1_0= RULE_INT ) ) otherlv_2= ']' ) )
            // InternalConceptLanguage.g:1577:2: (otherlv_0= '[' ( (lv_arraySize_1_0= RULE_INT ) ) otherlv_2= ']' )
            {
            // InternalConceptLanguage.g:1577:2: (otherlv_0= '[' ( (lv_arraySize_1_0= RULE_INT ) ) otherlv_2= ']' )
            // InternalConceptLanguage.g:1578:3: otherlv_0= '[' ( (lv_arraySize_1_0= RULE_INT ) ) otherlv_2= ']'
            {
            otherlv_0=(Token)match(input,24,FOLLOW_6); 

            			newLeafNode(otherlv_0, grammarAccess.getStaticArrayModifierAccess().getLeftSquareBracketKeyword_0());
            		
            // InternalConceptLanguage.g:1582:3: ( (lv_arraySize_1_0= RULE_INT ) )
            // InternalConceptLanguage.g:1583:4: (lv_arraySize_1_0= RULE_INT )
            {
            // InternalConceptLanguage.g:1583:4: (lv_arraySize_1_0= RULE_INT )
            // InternalConceptLanguage.g:1584:5: lv_arraySize_1_0= RULE_INT
            {
            lv_arraySize_1_0=(Token)match(input,RULE_INT,FOLLOW_29); 

            					newLeafNode(lv_arraySize_1_0, grammarAccess.getStaticArrayModifierAccess().getArraySizeINTTerminalRuleCall_1_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getStaticArrayModifierRule());
            					}
            					setWithLastConsumed(
            						current,
            						"arraySize",
            						lv_arraySize_1_0,
            						"org.eclipse.xtext.common.Terminals.INT");
            				

            }


            }

            otherlv_2=(Token)match(input,26,FOLLOW_2); 

            			newLeafNode(otherlv_2, grammarAccess.getStaticArrayModifierAccess().getRightSquareBracketKeyword_2());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleStaticArrayModifier"


    // $ANTLR start "entryRuleComposedProperty"
    // InternalConceptLanguage.g:1608:1: entryRuleComposedProperty returns [EObject current=null] : iv_ruleComposedProperty= ruleComposedProperty EOF ;
    public final EObject entryRuleComposedProperty() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleComposedProperty = null;


        try {
            // InternalConceptLanguage.g:1608:57: (iv_ruleComposedProperty= ruleComposedProperty EOF )
            // InternalConceptLanguage.g:1609:2: iv_ruleComposedProperty= ruleComposedProperty EOF
            {
             newCompositeNode(grammarAccess.getComposedPropertyRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleComposedProperty=ruleComposedProperty();

            state._fsp--;

             current =iv_ruleComposedProperty; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleComposedProperty"


    // $ANTLR start "ruleComposedProperty"
    // InternalConceptLanguage.g:1615:1: ruleComposedProperty returns [EObject current=null] : ( () otherlv_1= 'Type' ( (lv_name_2_0= RULE_ID ) ) ( (lv_arrayModifier_3_0= ruleArrayModifier ) )? otherlv_4= 'of' otherlv_5= 'Category' ( ( ruleQualifiedName ) ) ( ( ( ( ({...}? => ( ({...}? => (otherlv_8= 'description' ( (lv_description_9_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_10= 'quantityKind' ( (lv_quantityKindName_11_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_12= 'unit' ( (lv_unitName_13_0= ruleEString ) ) ) ) ) ) )* ) ) ) otherlv_14= ';' ) ;
    public final EObject ruleComposedProperty() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token lv_name_2_0=null;
        Token otherlv_4=null;
        Token otherlv_5=null;
        Token otherlv_8=null;
        Token otherlv_10=null;
        Token otherlv_12=null;
        Token otherlv_14=null;
        EObject lv_arrayModifier_3_0 = null;

        AntlrDatatypeRuleToken lv_description_9_0 = null;

        AntlrDatatypeRuleToken lv_quantityKindName_11_0 = null;

        AntlrDatatypeRuleToken lv_unitName_13_0 = null;



        	enterRule();

        try {
            // InternalConceptLanguage.g:1621:2: ( ( () otherlv_1= 'Type' ( (lv_name_2_0= RULE_ID ) ) ( (lv_arrayModifier_3_0= ruleArrayModifier ) )? otherlv_4= 'of' otherlv_5= 'Category' ( ( ruleQualifiedName ) ) ( ( ( ( ({...}? => ( ({...}? => (otherlv_8= 'description' ( (lv_description_9_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_10= 'quantityKind' ( (lv_quantityKindName_11_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_12= 'unit' ( (lv_unitName_13_0= ruleEString ) ) ) ) ) ) )* ) ) ) otherlv_14= ';' ) )
            // InternalConceptLanguage.g:1622:2: ( () otherlv_1= 'Type' ( (lv_name_2_0= RULE_ID ) ) ( (lv_arrayModifier_3_0= ruleArrayModifier ) )? otherlv_4= 'of' otherlv_5= 'Category' ( ( ruleQualifiedName ) ) ( ( ( ( ({...}? => ( ({...}? => (otherlv_8= 'description' ( (lv_description_9_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_10= 'quantityKind' ( (lv_quantityKindName_11_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_12= 'unit' ( (lv_unitName_13_0= ruleEString ) ) ) ) ) ) )* ) ) ) otherlv_14= ';' )
            {
            // InternalConceptLanguage.g:1622:2: ( () otherlv_1= 'Type' ( (lv_name_2_0= RULE_ID ) ) ( (lv_arrayModifier_3_0= ruleArrayModifier ) )? otherlv_4= 'of' otherlv_5= 'Category' ( ( ruleQualifiedName ) ) ( ( ( ( ({...}? => ( ({...}? => (otherlv_8= 'description' ( (lv_description_9_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_10= 'quantityKind' ( (lv_quantityKindName_11_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_12= 'unit' ( (lv_unitName_13_0= ruleEString ) ) ) ) ) ) )* ) ) ) otherlv_14= ';' )
            // InternalConceptLanguage.g:1623:3: () otherlv_1= 'Type' ( (lv_name_2_0= RULE_ID ) ) ( (lv_arrayModifier_3_0= ruleArrayModifier ) )? otherlv_4= 'of' otherlv_5= 'Category' ( ( ruleQualifiedName ) ) ( ( ( ( ({...}? => ( ({...}? => (otherlv_8= 'description' ( (lv_description_9_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_10= 'quantityKind' ( (lv_quantityKindName_11_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_12= 'unit' ( (lv_unitName_13_0= ruleEString ) ) ) ) ) ) )* ) ) ) otherlv_14= ';'
            {
            // InternalConceptLanguage.g:1623:3: ()
            // InternalConceptLanguage.g:1624:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getComposedPropertyAccess().getComposedPropertyAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,33,FOLLOW_3); 

            			newLeafNode(otherlv_1, grammarAccess.getComposedPropertyAccess().getTypeKeyword_1());
            		
            // InternalConceptLanguage.g:1634:3: ( (lv_name_2_0= RULE_ID ) )
            // InternalConceptLanguage.g:1635:4: (lv_name_2_0= RULE_ID )
            {
            // InternalConceptLanguage.g:1635:4: (lv_name_2_0= RULE_ID )
            // InternalConceptLanguage.g:1636:5: lv_name_2_0= RULE_ID
            {
            lv_name_2_0=(Token)match(input,RULE_ID,FOLLOW_30); 

            					newLeafNode(lv_name_2_0, grammarAccess.getComposedPropertyAccess().getNameIDTerminalRuleCall_2_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getComposedPropertyRule());
            					}
            					setWithLastConsumed(
            						current,
            						"name",
            						lv_name_2_0,
            						"org.eclipse.xtext.common.Terminals.ID");
            				

            }


            }

            // InternalConceptLanguage.g:1652:3: ( (lv_arrayModifier_3_0= ruleArrayModifier ) )?
            int alt25=2;
            int LA25_0 = input.LA(1);

            if ( (LA25_0==24) ) {
                alt25=1;
            }
            switch (alt25) {
                case 1 :
                    // InternalConceptLanguage.g:1653:4: (lv_arrayModifier_3_0= ruleArrayModifier )
                    {
                    // InternalConceptLanguage.g:1653:4: (lv_arrayModifier_3_0= ruleArrayModifier )
                    // InternalConceptLanguage.g:1654:5: lv_arrayModifier_3_0= ruleArrayModifier
                    {

                    					newCompositeNode(grammarAccess.getComposedPropertyAccess().getArrayModifierArrayModifierParserRuleCall_3_0());
                    				
                    pushFollow(FOLLOW_31);
                    lv_arrayModifier_3_0=ruleArrayModifier();

                    state._fsp--;


                    					if (current==null) {
                    						current = createModelElementForParent(grammarAccess.getComposedPropertyRule());
                    					}
                    					set(
                    						current,
                    						"arrayModifier",
                    						lv_arrayModifier_3_0,
                    						"de.dlr.sc.virsat.model.concept.ConceptLanguage.ArrayModifier");
                    					afterParserOrEnumRuleCall();
                    				

                    }


                    }
                    break;

            }

            otherlv_4=(Token)match(input,38,FOLLOW_32); 

            			newLeafNode(otherlv_4, grammarAccess.getComposedPropertyAccess().getOfKeyword_4());
            		
            otherlv_5=(Token)match(input,34,FOLLOW_3); 

            			newLeafNode(otherlv_5, grammarAccess.getComposedPropertyAccess().getCategoryKeyword_5());
            		
            // InternalConceptLanguage.g:1679:3: ( ( ruleQualifiedName ) )
            // InternalConceptLanguage.g:1680:4: ( ruleQualifiedName )
            {
            // InternalConceptLanguage.g:1680:4: ( ruleQualifiedName )
            // InternalConceptLanguage.g:1681:5: ruleQualifiedName
            {

            					if (current==null) {
            						current = createModelElement(grammarAccess.getComposedPropertyRule());
            					}
            				

            					newCompositeNode(grammarAccess.getComposedPropertyAccess().getTypeCategoryCrossReference_6_0());
            				
            pushFollow(FOLLOW_33);
            ruleQualifiedName();

            state._fsp--;


            					afterParserOrEnumRuleCall();
            				

            }


            }

            // InternalConceptLanguage.g:1695:3: ( ( ( ( ({...}? => ( ({...}? => (otherlv_8= 'description' ( (lv_description_9_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_10= 'quantityKind' ( (lv_quantityKindName_11_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_12= 'unit' ( (lv_unitName_13_0= ruleEString ) ) ) ) ) ) )* ) ) )
            // InternalConceptLanguage.g:1696:4: ( ( ( ({...}? => ( ({...}? => (otherlv_8= 'description' ( (lv_description_9_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_10= 'quantityKind' ( (lv_quantityKindName_11_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_12= 'unit' ( (lv_unitName_13_0= ruleEString ) ) ) ) ) ) )* ) )
            {
            // InternalConceptLanguage.g:1696:4: ( ( ( ({...}? => ( ({...}? => (otherlv_8= 'description' ( (lv_description_9_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_10= 'quantityKind' ( (lv_quantityKindName_11_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_12= 'unit' ( (lv_unitName_13_0= ruleEString ) ) ) ) ) ) )* ) )
            // InternalConceptLanguage.g:1697:5: ( ( ({...}? => ( ({...}? => (otherlv_8= 'description' ( (lv_description_9_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_10= 'quantityKind' ( (lv_quantityKindName_11_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_12= 'unit' ( (lv_unitName_13_0= ruleEString ) ) ) ) ) ) )* )
            {
             
            				  getUnorderedGroupHelper().enter(grammarAccess.getComposedPropertyAccess().getUnorderedGroup_7());
            				
            // InternalConceptLanguage.g:1700:5: ( ( ({...}? => ( ({...}? => (otherlv_8= 'description' ( (lv_description_9_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_10= 'quantityKind' ( (lv_quantityKindName_11_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_12= 'unit' ( (lv_unitName_13_0= ruleEString ) ) ) ) ) ) )* )
            // InternalConceptLanguage.g:1701:6: ( ({...}? => ( ({...}? => (otherlv_8= 'description' ( (lv_description_9_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_10= 'quantityKind' ( (lv_quantityKindName_11_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_12= 'unit' ( (lv_unitName_13_0= ruleEString ) ) ) ) ) ) )*
            {
            // InternalConceptLanguage.g:1701:6: ( ({...}? => ( ({...}? => (otherlv_8= 'description' ( (lv_description_9_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_10= 'quantityKind' ( (lv_quantityKindName_11_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_12= 'unit' ( (lv_unitName_13_0= ruleEString ) ) ) ) ) ) )*
            loop26:
            do {
                int alt26=4;
                int LA26_0 = input.LA(1);

                if ( LA26_0 == 14 && getUnorderedGroupHelper().canSelect(grammarAccess.getComposedPropertyAccess().getUnorderedGroup_7(), 0) ) {
                    alt26=1;
                }
                else if ( LA26_0 == 39 && getUnorderedGroupHelper().canSelect(grammarAccess.getComposedPropertyAccess().getUnorderedGroup_7(), 1) ) {
                    alt26=2;
                }
                else if ( LA26_0 == 40 && getUnorderedGroupHelper().canSelect(grammarAccess.getComposedPropertyAccess().getUnorderedGroup_7(), 2) ) {
                    alt26=3;
                }


                switch (alt26) {
            	case 1 :
            	    // InternalConceptLanguage.g:1702:4: ({...}? => ( ({...}? => (otherlv_8= 'description' ( (lv_description_9_0= ruleEString ) ) ) ) ) )
            	    {
            	    // InternalConceptLanguage.g:1702:4: ({...}? => ( ({...}? => (otherlv_8= 'description' ( (lv_description_9_0= ruleEString ) ) ) ) ) )
            	    // InternalConceptLanguage.g:1703:5: {...}? => ( ({...}? => (otherlv_8= 'description' ( (lv_description_9_0= ruleEString ) ) ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getComposedPropertyAccess().getUnorderedGroup_7(), 0) ) {
            	        throw new FailedPredicateException(input, "ruleComposedProperty", "getUnorderedGroupHelper().canSelect(grammarAccess.getComposedPropertyAccess().getUnorderedGroup_7(), 0)");
            	    }
            	    // InternalConceptLanguage.g:1703:113: ( ({...}? => (otherlv_8= 'description' ( (lv_description_9_0= ruleEString ) ) ) ) )
            	    // InternalConceptLanguage.g:1704:6: ({...}? => (otherlv_8= 'description' ( (lv_description_9_0= ruleEString ) ) ) )
            	    {

            	    						getUnorderedGroupHelper().select(grammarAccess.getComposedPropertyAccess().getUnorderedGroup_7(), 0);
            	    					
            	    // InternalConceptLanguage.g:1707:9: ({...}? => (otherlv_8= 'description' ( (lv_description_9_0= ruleEString ) ) ) )
            	    // InternalConceptLanguage.g:1707:10: {...}? => (otherlv_8= 'description' ( (lv_description_9_0= ruleEString ) ) )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleComposedProperty", "true");
            	    }
            	    // InternalConceptLanguage.g:1707:19: (otherlv_8= 'description' ( (lv_description_9_0= ruleEString ) ) )
            	    // InternalConceptLanguage.g:1707:20: otherlv_8= 'description' ( (lv_description_9_0= ruleEString ) )
            	    {
            	    otherlv_8=(Token)match(input,14,FOLLOW_5); 

            	    									newLeafNode(otherlv_8, grammarAccess.getComposedPropertyAccess().getDescriptionKeyword_7_0_0());
            	    								
            	    // InternalConceptLanguage.g:1711:9: ( (lv_description_9_0= ruleEString ) )
            	    // InternalConceptLanguage.g:1712:10: (lv_description_9_0= ruleEString )
            	    {
            	    // InternalConceptLanguage.g:1712:10: (lv_description_9_0= ruleEString )
            	    // InternalConceptLanguage.g:1713:11: lv_description_9_0= ruleEString
            	    {

            	    											newCompositeNode(grammarAccess.getComposedPropertyAccess().getDescriptionEStringParserRuleCall_7_0_1_0());
            	    										
            	    pushFollow(FOLLOW_33);
            	    lv_description_9_0=ruleEString();

            	    state._fsp--;


            	    											if (current==null) {
            	    												current = createModelElementForParent(grammarAccess.getComposedPropertyRule());
            	    											}
            	    											set(
            	    												current,
            	    												"description",
            	    												lv_description_9_0,
            	    												"de.dlr.sc.virsat.model.concept.ConceptLanguage.EString");
            	    											afterParserOrEnumRuleCall();
            	    										

            	    }


            	    }


            	    }


            	    }

            	     
            	    						getUnorderedGroupHelper().returnFromSelection(grammarAccess.getComposedPropertyAccess().getUnorderedGroup_7());
            	    					

            	    }


            	    }


            	    }
            	    break;
            	case 2 :
            	    // InternalConceptLanguage.g:1736:4: ({...}? => ( ({...}? => (otherlv_10= 'quantityKind' ( (lv_quantityKindName_11_0= ruleEString ) ) ) ) ) )
            	    {
            	    // InternalConceptLanguage.g:1736:4: ({...}? => ( ({...}? => (otherlv_10= 'quantityKind' ( (lv_quantityKindName_11_0= ruleEString ) ) ) ) ) )
            	    // InternalConceptLanguage.g:1737:5: {...}? => ( ({...}? => (otherlv_10= 'quantityKind' ( (lv_quantityKindName_11_0= ruleEString ) ) ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getComposedPropertyAccess().getUnorderedGroup_7(), 1) ) {
            	        throw new FailedPredicateException(input, "ruleComposedProperty", "getUnorderedGroupHelper().canSelect(grammarAccess.getComposedPropertyAccess().getUnorderedGroup_7(), 1)");
            	    }
            	    // InternalConceptLanguage.g:1737:113: ( ({...}? => (otherlv_10= 'quantityKind' ( (lv_quantityKindName_11_0= ruleEString ) ) ) ) )
            	    // InternalConceptLanguage.g:1738:6: ({...}? => (otherlv_10= 'quantityKind' ( (lv_quantityKindName_11_0= ruleEString ) ) ) )
            	    {

            	    						getUnorderedGroupHelper().select(grammarAccess.getComposedPropertyAccess().getUnorderedGroup_7(), 1);
            	    					
            	    // InternalConceptLanguage.g:1741:9: ({...}? => (otherlv_10= 'quantityKind' ( (lv_quantityKindName_11_0= ruleEString ) ) ) )
            	    // InternalConceptLanguage.g:1741:10: {...}? => (otherlv_10= 'quantityKind' ( (lv_quantityKindName_11_0= ruleEString ) ) )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleComposedProperty", "true");
            	    }
            	    // InternalConceptLanguage.g:1741:19: (otherlv_10= 'quantityKind' ( (lv_quantityKindName_11_0= ruleEString ) ) )
            	    // InternalConceptLanguage.g:1741:20: otherlv_10= 'quantityKind' ( (lv_quantityKindName_11_0= ruleEString ) )
            	    {
            	    otherlv_10=(Token)match(input,39,FOLLOW_5); 

            	    									newLeafNode(otherlv_10, grammarAccess.getComposedPropertyAccess().getQuantityKindKeyword_7_1_0());
            	    								
            	    // InternalConceptLanguage.g:1745:9: ( (lv_quantityKindName_11_0= ruleEString ) )
            	    // InternalConceptLanguage.g:1746:10: (lv_quantityKindName_11_0= ruleEString )
            	    {
            	    // InternalConceptLanguage.g:1746:10: (lv_quantityKindName_11_0= ruleEString )
            	    // InternalConceptLanguage.g:1747:11: lv_quantityKindName_11_0= ruleEString
            	    {

            	    											newCompositeNode(grammarAccess.getComposedPropertyAccess().getQuantityKindNameEStringParserRuleCall_7_1_1_0());
            	    										
            	    pushFollow(FOLLOW_33);
            	    lv_quantityKindName_11_0=ruleEString();

            	    state._fsp--;


            	    											if (current==null) {
            	    												current = createModelElementForParent(grammarAccess.getComposedPropertyRule());
            	    											}
            	    											set(
            	    												current,
            	    												"quantityKindName",
            	    												lv_quantityKindName_11_0,
            	    												"de.dlr.sc.virsat.model.concept.ConceptLanguage.EString");
            	    											afterParserOrEnumRuleCall();
            	    										

            	    }


            	    }


            	    }


            	    }

            	     
            	    						getUnorderedGroupHelper().returnFromSelection(grammarAccess.getComposedPropertyAccess().getUnorderedGroup_7());
            	    					

            	    }


            	    }


            	    }
            	    break;
            	case 3 :
            	    // InternalConceptLanguage.g:1770:4: ({...}? => ( ({...}? => (otherlv_12= 'unit' ( (lv_unitName_13_0= ruleEString ) ) ) ) ) )
            	    {
            	    // InternalConceptLanguage.g:1770:4: ({...}? => ( ({...}? => (otherlv_12= 'unit' ( (lv_unitName_13_0= ruleEString ) ) ) ) ) )
            	    // InternalConceptLanguage.g:1771:5: {...}? => ( ({...}? => (otherlv_12= 'unit' ( (lv_unitName_13_0= ruleEString ) ) ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getComposedPropertyAccess().getUnorderedGroup_7(), 2) ) {
            	        throw new FailedPredicateException(input, "ruleComposedProperty", "getUnorderedGroupHelper().canSelect(grammarAccess.getComposedPropertyAccess().getUnorderedGroup_7(), 2)");
            	    }
            	    // InternalConceptLanguage.g:1771:113: ( ({...}? => (otherlv_12= 'unit' ( (lv_unitName_13_0= ruleEString ) ) ) ) )
            	    // InternalConceptLanguage.g:1772:6: ({...}? => (otherlv_12= 'unit' ( (lv_unitName_13_0= ruleEString ) ) ) )
            	    {

            	    						getUnorderedGroupHelper().select(grammarAccess.getComposedPropertyAccess().getUnorderedGroup_7(), 2);
            	    					
            	    // InternalConceptLanguage.g:1775:9: ({...}? => (otherlv_12= 'unit' ( (lv_unitName_13_0= ruleEString ) ) ) )
            	    // InternalConceptLanguage.g:1775:10: {...}? => (otherlv_12= 'unit' ( (lv_unitName_13_0= ruleEString ) ) )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleComposedProperty", "true");
            	    }
            	    // InternalConceptLanguage.g:1775:19: (otherlv_12= 'unit' ( (lv_unitName_13_0= ruleEString ) ) )
            	    // InternalConceptLanguage.g:1775:20: otherlv_12= 'unit' ( (lv_unitName_13_0= ruleEString ) )
            	    {
            	    otherlv_12=(Token)match(input,40,FOLLOW_5); 

            	    									newLeafNode(otherlv_12, grammarAccess.getComposedPropertyAccess().getUnitKeyword_7_2_0());
            	    								
            	    // InternalConceptLanguage.g:1779:9: ( (lv_unitName_13_0= ruleEString ) )
            	    // InternalConceptLanguage.g:1780:10: (lv_unitName_13_0= ruleEString )
            	    {
            	    // InternalConceptLanguage.g:1780:10: (lv_unitName_13_0= ruleEString )
            	    // InternalConceptLanguage.g:1781:11: lv_unitName_13_0= ruleEString
            	    {

            	    											newCompositeNode(grammarAccess.getComposedPropertyAccess().getUnitNameEStringParserRuleCall_7_2_1_0());
            	    										
            	    pushFollow(FOLLOW_33);
            	    lv_unitName_13_0=ruleEString();

            	    state._fsp--;


            	    											if (current==null) {
            	    												current = createModelElementForParent(grammarAccess.getComposedPropertyRule());
            	    											}
            	    											set(
            	    												current,
            	    												"unitName",
            	    												lv_unitName_13_0,
            	    												"de.dlr.sc.virsat.model.concept.ConceptLanguage.EString");
            	    											afterParserOrEnumRuleCall();
            	    										

            	    }


            	    }


            	    }


            	    }

            	     
            	    						getUnorderedGroupHelper().returnFromSelection(grammarAccess.getComposedPropertyAccess().getUnorderedGroup_7());
            	    					

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop26;
                }
            } while (true);


            }


            }

             
            				  getUnorderedGroupHelper().leave(grammarAccess.getComposedPropertyAccess().getUnorderedGroup_7());
            				

            }

            otherlv_14=(Token)match(input,21,FOLLOW_2); 

            			newLeafNode(otherlv_14, grammarAccess.getComposedPropertyAccess().getSemicolonKeyword_8());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleComposedProperty"


    // $ANTLR start "entryRuleIntProperty"
    // InternalConceptLanguage.g:1819:1: entryRuleIntProperty returns [EObject current=null] : iv_ruleIntProperty= ruleIntProperty EOF ;
    public final EObject entryRuleIntProperty() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleIntProperty = null;


        try {
            // InternalConceptLanguage.g:1819:52: (iv_ruleIntProperty= ruleIntProperty EOF )
            // InternalConceptLanguage.g:1820:2: iv_ruleIntProperty= ruleIntProperty EOF
            {
             newCompositeNode(grammarAccess.getIntPropertyRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleIntProperty=ruleIntProperty();

            state._fsp--;

             current =iv_ruleIntProperty; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleIntProperty"


    // $ANTLR start "ruleIntProperty"
    // InternalConceptLanguage.g:1826:1: ruleIntProperty returns [EObject current=null] : ( () otherlv_1= 'IntProperty' ( (lv_name_2_0= RULE_ID ) ) ( (lv_arrayModifier_3_0= ruleArrayModifier ) )? ( ( ( ( ({...}? => ( ({...}? => (otherlv_5= 'description' ( (lv_description_6_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'default' ( (lv_defaultValue_8_0= ruleIntLiteralString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_9= 'quantityKind' ( (lv_quantityKindName_10_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_11= 'unit' ( (lv_unitName_12_0= ruleEString ) ) ) ) ) ) )* ) ) ) otherlv_13= ';' ) ;
    public final EObject ruleIntProperty() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token lv_name_2_0=null;
        Token otherlv_5=null;
        Token otherlv_7=null;
        Token otherlv_9=null;
        Token otherlv_11=null;
        Token otherlv_13=null;
        EObject lv_arrayModifier_3_0 = null;

        AntlrDatatypeRuleToken lv_description_6_0 = null;

        AntlrDatatypeRuleToken lv_defaultValue_8_0 = null;

        AntlrDatatypeRuleToken lv_quantityKindName_10_0 = null;

        AntlrDatatypeRuleToken lv_unitName_12_0 = null;



        	enterRule();

        try {
            // InternalConceptLanguage.g:1832:2: ( ( () otherlv_1= 'IntProperty' ( (lv_name_2_0= RULE_ID ) ) ( (lv_arrayModifier_3_0= ruleArrayModifier ) )? ( ( ( ( ({...}? => ( ({...}? => (otherlv_5= 'description' ( (lv_description_6_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'default' ( (lv_defaultValue_8_0= ruleIntLiteralString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_9= 'quantityKind' ( (lv_quantityKindName_10_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_11= 'unit' ( (lv_unitName_12_0= ruleEString ) ) ) ) ) ) )* ) ) ) otherlv_13= ';' ) )
            // InternalConceptLanguage.g:1833:2: ( () otherlv_1= 'IntProperty' ( (lv_name_2_0= RULE_ID ) ) ( (lv_arrayModifier_3_0= ruleArrayModifier ) )? ( ( ( ( ({...}? => ( ({...}? => (otherlv_5= 'description' ( (lv_description_6_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'default' ( (lv_defaultValue_8_0= ruleIntLiteralString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_9= 'quantityKind' ( (lv_quantityKindName_10_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_11= 'unit' ( (lv_unitName_12_0= ruleEString ) ) ) ) ) ) )* ) ) ) otherlv_13= ';' )
            {
            // InternalConceptLanguage.g:1833:2: ( () otherlv_1= 'IntProperty' ( (lv_name_2_0= RULE_ID ) ) ( (lv_arrayModifier_3_0= ruleArrayModifier ) )? ( ( ( ( ({...}? => ( ({...}? => (otherlv_5= 'description' ( (lv_description_6_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'default' ( (lv_defaultValue_8_0= ruleIntLiteralString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_9= 'quantityKind' ( (lv_quantityKindName_10_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_11= 'unit' ( (lv_unitName_12_0= ruleEString ) ) ) ) ) ) )* ) ) ) otherlv_13= ';' )
            // InternalConceptLanguage.g:1834:3: () otherlv_1= 'IntProperty' ( (lv_name_2_0= RULE_ID ) ) ( (lv_arrayModifier_3_0= ruleArrayModifier ) )? ( ( ( ( ({...}? => ( ({...}? => (otherlv_5= 'description' ( (lv_description_6_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'default' ( (lv_defaultValue_8_0= ruleIntLiteralString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_9= 'quantityKind' ( (lv_quantityKindName_10_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_11= 'unit' ( (lv_unitName_12_0= ruleEString ) ) ) ) ) ) )* ) ) ) otherlv_13= ';'
            {
            // InternalConceptLanguage.g:1834:3: ()
            // InternalConceptLanguage.g:1835:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getIntPropertyAccess().getIntPropertyAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,41,FOLLOW_3); 

            			newLeafNode(otherlv_1, grammarAccess.getIntPropertyAccess().getIntPropertyKeyword_1());
            		
            // InternalConceptLanguage.g:1845:3: ( (lv_name_2_0= RULE_ID ) )
            // InternalConceptLanguage.g:1846:4: (lv_name_2_0= RULE_ID )
            {
            // InternalConceptLanguage.g:1846:4: (lv_name_2_0= RULE_ID )
            // InternalConceptLanguage.g:1847:5: lv_name_2_0= RULE_ID
            {
            lv_name_2_0=(Token)match(input,RULE_ID,FOLLOW_34); 

            					newLeafNode(lv_name_2_0, grammarAccess.getIntPropertyAccess().getNameIDTerminalRuleCall_2_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getIntPropertyRule());
            					}
            					setWithLastConsumed(
            						current,
            						"name",
            						lv_name_2_0,
            						"org.eclipse.xtext.common.Terminals.ID");
            				

            }


            }

            // InternalConceptLanguage.g:1863:3: ( (lv_arrayModifier_3_0= ruleArrayModifier ) )?
            int alt27=2;
            int LA27_0 = input.LA(1);

            if ( (LA27_0==24) ) {
                alt27=1;
            }
            switch (alt27) {
                case 1 :
                    // InternalConceptLanguage.g:1864:4: (lv_arrayModifier_3_0= ruleArrayModifier )
                    {
                    // InternalConceptLanguage.g:1864:4: (lv_arrayModifier_3_0= ruleArrayModifier )
                    // InternalConceptLanguage.g:1865:5: lv_arrayModifier_3_0= ruleArrayModifier
                    {

                    					newCompositeNode(grammarAccess.getIntPropertyAccess().getArrayModifierArrayModifierParserRuleCall_3_0());
                    				
                    pushFollow(FOLLOW_35);
                    lv_arrayModifier_3_0=ruleArrayModifier();

                    state._fsp--;


                    					if (current==null) {
                    						current = createModelElementForParent(grammarAccess.getIntPropertyRule());
                    					}
                    					set(
                    						current,
                    						"arrayModifier",
                    						lv_arrayModifier_3_0,
                    						"de.dlr.sc.virsat.model.concept.ConceptLanguage.ArrayModifier");
                    					afterParserOrEnumRuleCall();
                    				

                    }


                    }
                    break;

            }

            // InternalConceptLanguage.g:1882:3: ( ( ( ( ({...}? => ( ({...}? => (otherlv_5= 'description' ( (lv_description_6_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'default' ( (lv_defaultValue_8_0= ruleIntLiteralString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_9= 'quantityKind' ( (lv_quantityKindName_10_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_11= 'unit' ( (lv_unitName_12_0= ruleEString ) ) ) ) ) ) )* ) ) )
            // InternalConceptLanguage.g:1883:4: ( ( ( ({...}? => ( ({...}? => (otherlv_5= 'description' ( (lv_description_6_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'default' ( (lv_defaultValue_8_0= ruleIntLiteralString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_9= 'quantityKind' ( (lv_quantityKindName_10_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_11= 'unit' ( (lv_unitName_12_0= ruleEString ) ) ) ) ) ) )* ) )
            {
            // InternalConceptLanguage.g:1883:4: ( ( ( ({...}? => ( ({...}? => (otherlv_5= 'description' ( (lv_description_6_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'default' ( (lv_defaultValue_8_0= ruleIntLiteralString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_9= 'quantityKind' ( (lv_quantityKindName_10_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_11= 'unit' ( (lv_unitName_12_0= ruleEString ) ) ) ) ) ) )* ) )
            // InternalConceptLanguage.g:1884:5: ( ( ({...}? => ( ({...}? => (otherlv_5= 'description' ( (lv_description_6_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'default' ( (lv_defaultValue_8_0= ruleIntLiteralString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_9= 'quantityKind' ( (lv_quantityKindName_10_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_11= 'unit' ( (lv_unitName_12_0= ruleEString ) ) ) ) ) ) )* )
            {
             
            				  getUnorderedGroupHelper().enter(grammarAccess.getIntPropertyAccess().getUnorderedGroup_4());
            				
            // InternalConceptLanguage.g:1887:5: ( ( ({...}? => ( ({...}? => (otherlv_5= 'description' ( (lv_description_6_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'default' ( (lv_defaultValue_8_0= ruleIntLiteralString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_9= 'quantityKind' ( (lv_quantityKindName_10_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_11= 'unit' ( (lv_unitName_12_0= ruleEString ) ) ) ) ) ) )* )
            // InternalConceptLanguage.g:1888:6: ( ({...}? => ( ({...}? => (otherlv_5= 'description' ( (lv_description_6_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'default' ( (lv_defaultValue_8_0= ruleIntLiteralString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_9= 'quantityKind' ( (lv_quantityKindName_10_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_11= 'unit' ( (lv_unitName_12_0= ruleEString ) ) ) ) ) ) )*
            {
            // InternalConceptLanguage.g:1888:6: ( ({...}? => ( ({...}? => (otherlv_5= 'description' ( (lv_description_6_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'default' ( (lv_defaultValue_8_0= ruleIntLiteralString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_9= 'quantityKind' ( (lv_quantityKindName_10_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_11= 'unit' ( (lv_unitName_12_0= ruleEString ) ) ) ) ) ) )*
            loop28:
            do {
                int alt28=5;
                int LA28_0 = input.LA(1);

                if ( LA28_0 == 14 && getUnorderedGroupHelper().canSelect(grammarAccess.getIntPropertyAccess().getUnorderedGroup_4(), 0) ) {
                    alt28=1;
                }
                else if ( LA28_0 == 42 && getUnorderedGroupHelper().canSelect(grammarAccess.getIntPropertyAccess().getUnorderedGroup_4(), 1) ) {
                    alt28=2;
                }
                else if ( LA28_0 == 39 && getUnorderedGroupHelper().canSelect(grammarAccess.getIntPropertyAccess().getUnorderedGroup_4(), 2) ) {
                    alt28=3;
                }
                else if ( LA28_0 == 40 && getUnorderedGroupHelper().canSelect(grammarAccess.getIntPropertyAccess().getUnorderedGroup_4(), 3) ) {
                    alt28=4;
                }


                switch (alt28) {
            	case 1 :
            	    // InternalConceptLanguage.g:1889:4: ({...}? => ( ({...}? => (otherlv_5= 'description' ( (lv_description_6_0= ruleEString ) ) ) ) ) )
            	    {
            	    // InternalConceptLanguage.g:1889:4: ({...}? => ( ({...}? => (otherlv_5= 'description' ( (lv_description_6_0= ruleEString ) ) ) ) ) )
            	    // InternalConceptLanguage.g:1890:5: {...}? => ( ({...}? => (otherlv_5= 'description' ( (lv_description_6_0= ruleEString ) ) ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getIntPropertyAccess().getUnorderedGroup_4(), 0) ) {
            	        throw new FailedPredicateException(input, "ruleIntProperty", "getUnorderedGroupHelper().canSelect(grammarAccess.getIntPropertyAccess().getUnorderedGroup_4(), 0)");
            	    }
            	    // InternalConceptLanguage.g:1890:108: ( ({...}? => (otherlv_5= 'description' ( (lv_description_6_0= ruleEString ) ) ) ) )
            	    // InternalConceptLanguage.g:1891:6: ({...}? => (otherlv_5= 'description' ( (lv_description_6_0= ruleEString ) ) ) )
            	    {

            	    						getUnorderedGroupHelper().select(grammarAccess.getIntPropertyAccess().getUnorderedGroup_4(), 0);
            	    					
            	    // InternalConceptLanguage.g:1894:9: ({...}? => (otherlv_5= 'description' ( (lv_description_6_0= ruleEString ) ) ) )
            	    // InternalConceptLanguage.g:1894:10: {...}? => (otherlv_5= 'description' ( (lv_description_6_0= ruleEString ) ) )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleIntProperty", "true");
            	    }
            	    // InternalConceptLanguage.g:1894:19: (otherlv_5= 'description' ( (lv_description_6_0= ruleEString ) ) )
            	    // InternalConceptLanguage.g:1894:20: otherlv_5= 'description' ( (lv_description_6_0= ruleEString ) )
            	    {
            	    otherlv_5=(Token)match(input,14,FOLLOW_5); 

            	    									newLeafNode(otherlv_5, grammarAccess.getIntPropertyAccess().getDescriptionKeyword_4_0_0());
            	    								
            	    // InternalConceptLanguage.g:1898:9: ( (lv_description_6_0= ruleEString ) )
            	    // InternalConceptLanguage.g:1899:10: (lv_description_6_0= ruleEString )
            	    {
            	    // InternalConceptLanguage.g:1899:10: (lv_description_6_0= ruleEString )
            	    // InternalConceptLanguage.g:1900:11: lv_description_6_0= ruleEString
            	    {

            	    											newCompositeNode(grammarAccess.getIntPropertyAccess().getDescriptionEStringParserRuleCall_4_0_1_0());
            	    										
            	    pushFollow(FOLLOW_35);
            	    lv_description_6_0=ruleEString();

            	    state._fsp--;


            	    											if (current==null) {
            	    												current = createModelElementForParent(grammarAccess.getIntPropertyRule());
            	    											}
            	    											set(
            	    												current,
            	    												"description",
            	    												lv_description_6_0,
            	    												"de.dlr.sc.virsat.model.concept.ConceptLanguage.EString");
            	    											afterParserOrEnumRuleCall();
            	    										

            	    }


            	    }


            	    }


            	    }

            	     
            	    						getUnorderedGroupHelper().returnFromSelection(grammarAccess.getIntPropertyAccess().getUnorderedGroup_4());
            	    					

            	    }


            	    }


            	    }
            	    break;
            	case 2 :
            	    // InternalConceptLanguage.g:1923:4: ({...}? => ( ({...}? => (otherlv_7= 'default' ( (lv_defaultValue_8_0= ruleIntLiteralString ) ) ) ) ) )
            	    {
            	    // InternalConceptLanguage.g:1923:4: ({...}? => ( ({...}? => (otherlv_7= 'default' ( (lv_defaultValue_8_0= ruleIntLiteralString ) ) ) ) ) )
            	    // InternalConceptLanguage.g:1924:5: {...}? => ( ({...}? => (otherlv_7= 'default' ( (lv_defaultValue_8_0= ruleIntLiteralString ) ) ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getIntPropertyAccess().getUnorderedGroup_4(), 1) ) {
            	        throw new FailedPredicateException(input, "ruleIntProperty", "getUnorderedGroupHelper().canSelect(grammarAccess.getIntPropertyAccess().getUnorderedGroup_4(), 1)");
            	    }
            	    // InternalConceptLanguage.g:1924:108: ( ({...}? => (otherlv_7= 'default' ( (lv_defaultValue_8_0= ruleIntLiteralString ) ) ) ) )
            	    // InternalConceptLanguage.g:1925:6: ({...}? => (otherlv_7= 'default' ( (lv_defaultValue_8_0= ruleIntLiteralString ) ) ) )
            	    {

            	    						getUnorderedGroupHelper().select(grammarAccess.getIntPropertyAccess().getUnorderedGroup_4(), 1);
            	    					
            	    // InternalConceptLanguage.g:1928:9: ({...}? => (otherlv_7= 'default' ( (lv_defaultValue_8_0= ruleIntLiteralString ) ) ) )
            	    // InternalConceptLanguage.g:1928:10: {...}? => (otherlv_7= 'default' ( (lv_defaultValue_8_0= ruleIntLiteralString ) ) )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleIntProperty", "true");
            	    }
            	    // InternalConceptLanguage.g:1928:19: (otherlv_7= 'default' ( (lv_defaultValue_8_0= ruleIntLiteralString ) ) )
            	    // InternalConceptLanguage.g:1928:20: otherlv_7= 'default' ( (lv_defaultValue_8_0= ruleIntLiteralString ) )
            	    {
            	    otherlv_7=(Token)match(input,42,FOLLOW_36); 

            	    									newLeafNode(otherlv_7, grammarAccess.getIntPropertyAccess().getDefaultKeyword_4_1_0());
            	    								
            	    // InternalConceptLanguage.g:1932:9: ( (lv_defaultValue_8_0= ruleIntLiteralString ) )
            	    // InternalConceptLanguage.g:1933:10: (lv_defaultValue_8_0= ruleIntLiteralString )
            	    {
            	    // InternalConceptLanguage.g:1933:10: (lv_defaultValue_8_0= ruleIntLiteralString )
            	    // InternalConceptLanguage.g:1934:11: lv_defaultValue_8_0= ruleIntLiteralString
            	    {

            	    											newCompositeNode(grammarAccess.getIntPropertyAccess().getDefaultValueIntLiteralStringParserRuleCall_4_1_1_0());
            	    										
            	    pushFollow(FOLLOW_35);
            	    lv_defaultValue_8_0=ruleIntLiteralString();

            	    state._fsp--;


            	    											if (current==null) {
            	    												current = createModelElementForParent(grammarAccess.getIntPropertyRule());
            	    											}
            	    											set(
            	    												current,
            	    												"defaultValue",
            	    												lv_defaultValue_8_0,
            	    												"de.dlr.sc.virsat.model.concept.ConceptLanguage.IntLiteralString");
            	    											afterParserOrEnumRuleCall();
            	    										

            	    }


            	    }


            	    }


            	    }

            	     
            	    						getUnorderedGroupHelper().returnFromSelection(grammarAccess.getIntPropertyAccess().getUnorderedGroup_4());
            	    					

            	    }


            	    }


            	    }
            	    break;
            	case 3 :
            	    // InternalConceptLanguage.g:1957:4: ({...}? => ( ({...}? => (otherlv_9= 'quantityKind' ( (lv_quantityKindName_10_0= ruleEString ) ) ) ) ) )
            	    {
            	    // InternalConceptLanguage.g:1957:4: ({...}? => ( ({...}? => (otherlv_9= 'quantityKind' ( (lv_quantityKindName_10_0= ruleEString ) ) ) ) ) )
            	    // InternalConceptLanguage.g:1958:5: {...}? => ( ({...}? => (otherlv_9= 'quantityKind' ( (lv_quantityKindName_10_0= ruleEString ) ) ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getIntPropertyAccess().getUnorderedGroup_4(), 2) ) {
            	        throw new FailedPredicateException(input, "ruleIntProperty", "getUnorderedGroupHelper().canSelect(grammarAccess.getIntPropertyAccess().getUnorderedGroup_4(), 2)");
            	    }
            	    // InternalConceptLanguage.g:1958:108: ( ({...}? => (otherlv_9= 'quantityKind' ( (lv_quantityKindName_10_0= ruleEString ) ) ) ) )
            	    // InternalConceptLanguage.g:1959:6: ({...}? => (otherlv_9= 'quantityKind' ( (lv_quantityKindName_10_0= ruleEString ) ) ) )
            	    {

            	    						getUnorderedGroupHelper().select(grammarAccess.getIntPropertyAccess().getUnorderedGroup_4(), 2);
            	    					
            	    // InternalConceptLanguage.g:1962:9: ({...}? => (otherlv_9= 'quantityKind' ( (lv_quantityKindName_10_0= ruleEString ) ) ) )
            	    // InternalConceptLanguage.g:1962:10: {...}? => (otherlv_9= 'quantityKind' ( (lv_quantityKindName_10_0= ruleEString ) ) )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleIntProperty", "true");
            	    }
            	    // InternalConceptLanguage.g:1962:19: (otherlv_9= 'quantityKind' ( (lv_quantityKindName_10_0= ruleEString ) ) )
            	    // InternalConceptLanguage.g:1962:20: otherlv_9= 'quantityKind' ( (lv_quantityKindName_10_0= ruleEString ) )
            	    {
            	    otherlv_9=(Token)match(input,39,FOLLOW_5); 

            	    									newLeafNode(otherlv_9, grammarAccess.getIntPropertyAccess().getQuantityKindKeyword_4_2_0());
            	    								
            	    // InternalConceptLanguage.g:1966:9: ( (lv_quantityKindName_10_0= ruleEString ) )
            	    // InternalConceptLanguage.g:1967:10: (lv_quantityKindName_10_0= ruleEString )
            	    {
            	    // InternalConceptLanguage.g:1967:10: (lv_quantityKindName_10_0= ruleEString )
            	    // InternalConceptLanguage.g:1968:11: lv_quantityKindName_10_0= ruleEString
            	    {

            	    											newCompositeNode(grammarAccess.getIntPropertyAccess().getQuantityKindNameEStringParserRuleCall_4_2_1_0());
            	    										
            	    pushFollow(FOLLOW_35);
            	    lv_quantityKindName_10_0=ruleEString();

            	    state._fsp--;


            	    											if (current==null) {
            	    												current = createModelElementForParent(grammarAccess.getIntPropertyRule());
            	    											}
            	    											set(
            	    												current,
            	    												"quantityKindName",
            	    												lv_quantityKindName_10_0,
            	    												"de.dlr.sc.virsat.model.concept.ConceptLanguage.EString");
            	    											afterParserOrEnumRuleCall();
            	    										

            	    }


            	    }


            	    }


            	    }

            	     
            	    						getUnorderedGroupHelper().returnFromSelection(grammarAccess.getIntPropertyAccess().getUnorderedGroup_4());
            	    					

            	    }


            	    }


            	    }
            	    break;
            	case 4 :
            	    // InternalConceptLanguage.g:1991:4: ({...}? => ( ({...}? => (otherlv_11= 'unit' ( (lv_unitName_12_0= ruleEString ) ) ) ) ) )
            	    {
            	    // InternalConceptLanguage.g:1991:4: ({...}? => ( ({...}? => (otherlv_11= 'unit' ( (lv_unitName_12_0= ruleEString ) ) ) ) ) )
            	    // InternalConceptLanguage.g:1992:5: {...}? => ( ({...}? => (otherlv_11= 'unit' ( (lv_unitName_12_0= ruleEString ) ) ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getIntPropertyAccess().getUnorderedGroup_4(), 3) ) {
            	        throw new FailedPredicateException(input, "ruleIntProperty", "getUnorderedGroupHelper().canSelect(grammarAccess.getIntPropertyAccess().getUnorderedGroup_4(), 3)");
            	    }
            	    // InternalConceptLanguage.g:1992:108: ( ({...}? => (otherlv_11= 'unit' ( (lv_unitName_12_0= ruleEString ) ) ) ) )
            	    // InternalConceptLanguage.g:1993:6: ({...}? => (otherlv_11= 'unit' ( (lv_unitName_12_0= ruleEString ) ) ) )
            	    {

            	    						getUnorderedGroupHelper().select(grammarAccess.getIntPropertyAccess().getUnorderedGroup_4(), 3);
            	    					
            	    // InternalConceptLanguage.g:1996:9: ({...}? => (otherlv_11= 'unit' ( (lv_unitName_12_0= ruleEString ) ) ) )
            	    // InternalConceptLanguage.g:1996:10: {...}? => (otherlv_11= 'unit' ( (lv_unitName_12_0= ruleEString ) ) )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleIntProperty", "true");
            	    }
            	    // InternalConceptLanguage.g:1996:19: (otherlv_11= 'unit' ( (lv_unitName_12_0= ruleEString ) ) )
            	    // InternalConceptLanguage.g:1996:20: otherlv_11= 'unit' ( (lv_unitName_12_0= ruleEString ) )
            	    {
            	    otherlv_11=(Token)match(input,40,FOLLOW_5); 

            	    									newLeafNode(otherlv_11, grammarAccess.getIntPropertyAccess().getUnitKeyword_4_3_0());
            	    								
            	    // InternalConceptLanguage.g:2000:9: ( (lv_unitName_12_0= ruleEString ) )
            	    // InternalConceptLanguage.g:2001:10: (lv_unitName_12_0= ruleEString )
            	    {
            	    // InternalConceptLanguage.g:2001:10: (lv_unitName_12_0= ruleEString )
            	    // InternalConceptLanguage.g:2002:11: lv_unitName_12_0= ruleEString
            	    {

            	    											newCompositeNode(grammarAccess.getIntPropertyAccess().getUnitNameEStringParserRuleCall_4_3_1_0());
            	    										
            	    pushFollow(FOLLOW_35);
            	    lv_unitName_12_0=ruleEString();

            	    state._fsp--;


            	    											if (current==null) {
            	    												current = createModelElementForParent(grammarAccess.getIntPropertyRule());
            	    											}
            	    											set(
            	    												current,
            	    												"unitName",
            	    												lv_unitName_12_0,
            	    												"de.dlr.sc.virsat.model.concept.ConceptLanguage.EString");
            	    											afterParserOrEnumRuleCall();
            	    										

            	    }


            	    }


            	    }


            	    }

            	     
            	    						getUnorderedGroupHelper().returnFromSelection(grammarAccess.getIntPropertyAccess().getUnorderedGroup_4());
            	    					

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop28;
                }
            } while (true);


            }


            }

             
            				  getUnorderedGroupHelper().leave(grammarAccess.getIntPropertyAccess().getUnorderedGroup_4());
            				

            }

            otherlv_13=(Token)match(input,21,FOLLOW_2); 

            			newLeafNode(otherlv_13, grammarAccess.getIntPropertyAccess().getSemicolonKeyword_5());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleIntProperty"


    // $ANTLR start "entryRuleFloatProperty"
    // InternalConceptLanguage.g:2040:1: entryRuleFloatProperty returns [EObject current=null] : iv_ruleFloatProperty= ruleFloatProperty EOF ;
    public final EObject entryRuleFloatProperty() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleFloatProperty = null;


        try {
            // InternalConceptLanguage.g:2040:54: (iv_ruleFloatProperty= ruleFloatProperty EOF )
            // InternalConceptLanguage.g:2041:2: iv_ruleFloatProperty= ruleFloatProperty EOF
            {
             newCompositeNode(grammarAccess.getFloatPropertyRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleFloatProperty=ruleFloatProperty();

            state._fsp--;

             current =iv_ruleFloatProperty; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleFloatProperty"


    // $ANTLR start "ruleFloatProperty"
    // InternalConceptLanguage.g:2047:1: ruleFloatProperty returns [EObject current=null] : ( () otherlv_1= 'FloatProperty' ( (lv_name_2_0= RULE_ID ) ) ( (lv_arrayModifier_3_0= ruleArrayModifier ) )? ( ( ( ( ({...}? => ( ({...}? => (otherlv_5= 'description' ( (lv_description_6_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'default' ( (lv_defaultValue_8_0= ruleFloatLiteralString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_9= 'quantityKind' ( (lv_quantityKindName_10_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_11= 'unit' ( (lv_unitName_12_0= ruleEString ) ) ) ) ) ) )* ) ) ) otherlv_13= ';' ) ;
    public final EObject ruleFloatProperty() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token lv_name_2_0=null;
        Token otherlv_5=null;
        Token otherlv_7=null;
        Token otherlv_9=null;
        Token otherlv_11=null;
        Token otherlv_13=null;
        EObject lv_arrayModifier_3_0 = null;

        AntlrDatatypeRuleToken lv_description_6_0 = null;

        AntlrDatatypeRuleToken lv_defaultValue_8_0 = null;

        AntlrDatatypeRuleToken lv_quantityKindName_10_0 = null;

        AntlrDatatypeRuleToken lv_unitName_12_0 = null;



        	enterRule();

        try {
            // InternalConceptLanguage.g:2053:2: ( ( () otherlv_1= 'FloatProperty' ( (lv_name_2_0= RULE_ID ) ) ( (lv_arrayModifier_3_0= ruleArrayModifier ) )? ( ( ( ( ({...}? => ( ({...}? => (otherlv_5= 'description' ( (lv_description_6_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'default' ( (lv_defaultValue_8_0= ruleFloatLiteralString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_9= 'quantityKind' ( (lv_quantityKindName_10_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_11= 'unit' ( (lv_unitName_12_0= ruleEString ) ) ) ) ) ) )* ) ) ) otherlv_13= ';' ) )
            // InternalConceptLanguage.g:2054:2: ( () otherlv_1= 'FloatProperty' ( (lv_name_2_0= RULE_ID ) ) ( (lv_arrayModifier_3_0= ruleArrayModifier ) )? ( ( ( ( ({...}? => ( ({...}? => (otherlv_5= 'description' ( (lv_description_6_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'default' ( (lv_defaultValue_8_0= ruleFloatLiteralString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_9= 'quantityKind' ( (lv_quantityKindName_10_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_11= 'unit' ( (lv_unitName_12_0= ruleEString ) ) ) ) ) ) )* ) ) ) otherlv_13= ';' )
            {
            // InternalConceptLanguage.g:2054:2: ( () otherlv_1= 'FloatProperty' ( (lv_name_2_0= RULE_ID ) ) ( (lv_arrayModifier_3_0= ruleArrayModifier ) )? ( ( ( ( ({...}? => ( ({...}? => (otherlv_5= 'description' ( (lv_description_6_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'default' ( (lv_defaultValue_8_0= ruleFloatLiteralString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_9= 'quantityKind' ( (lv_quantityKindName_10_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_11= 'unit' ( (lv_unitName_12_0= ruleEString ) ) ) ) ) ) )* ) ) ) otherlv_13= ';' )
            // InternalConceptLanguage.g:2055:3: () otherlv_1= 'FloatProperty' ( (lv_name_2_0= RULE_ID ) ) ( (lv_arrayModifier_3_0= ruleArrayModifier ) )? ( ( ( ( ({...}? => ( ({...}? => (otherlv_5= 'description' ( (lv_description_6_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'default' ( (lv_defaultValue_8_0= ruleFloatLiteralString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_9= 'quantityKind' ( (lv_quantityKindName_10_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_11= 'unit' ( (lv_unitName_12_0= ruleEString ) ) ) ) ) ) )* ) ) ) otherlv_13= ';'
            {
            // InternalConceptLanguage.g:2055:3: ()
            // InternalConceptLanguage.g:2056:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getFloatPropertyAccess().getFloatPropertyAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,43,FOLLOW_3); 

            			newLeafNode(otherlv_1, grammarAccess.getFloatPropertyAccess().getFloatPropertyKeyword_1());
            		
            // InternalConceptLanguage.g:2066:3: ( (lv_name_2_0= RULE_ID ) )
            // InternalConceptLanguage.g:2067:4: (lv_name_2_0= RULE_ID )
            {
            // InternalConceptLanguage.g:2067:4: (lv_name_2_0= RULE_ID )
            // InternalConceptLanguage.g:2068:5: lv_name_2_0= RULE_ID
            {
            lv_name_2_0=(Token)match(input,RULE_ID,FOLLOW_34); 

            					newLeafNode(lv_name_2_0, grammarAccess.getFloatPropertyAccess().getNameIDTerminalRuleCall_2_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getFloatPropertyRule());
            					}
            					setWithLastConsumed(
            						current,
            						"name",
            						lv_name_2_0,
            						"org.eclipse.xtext.common.Terminals.ID");
            				

            }


            }

            // InternalConceptLanguage.g:2084:3: ( (lv_arrayModifier_3_0= ruleArrayModifier ) )?
            int alt29=2;
            int LA29_0 = input.LA(1);

            if ( (LA29_0==24) ) {
                alt29=1;
            }
            switch (alt29) {
                case 1 :
                    // InternalConceptLanguage.g:2085:4: (lv_arrayModifier_3_0= ruleArrayModifier )
                    {
                    // InternalConceptLanguage.g:2085:4: (lv_arrayModifier_3_0= ruleArrayModifier )
                    // InternalConceptLanguage.g:2086:5: lv_arrayModifier_3_0= ruleArrayModifier
                    {

                    					newCompositeNode(grammarAccess.getFloatPropertyAccess().getArrayModifierArrayModifierParserRuleCall_3_0());
                    				
                    pushFollow(FOLLOW_35);
                    lv_arrayModifier_3_0=ruleArrayModifier();

                    state._fsp--;


                    					if (current==null) {
                    						current = createModelElementForParent(grammarAccess.getFloatPropertyRule());
                    					}
                    					set(
                    						current,
                    						"arrayModifier",
                    						lv_arrayModifier_3_0,
                    						"de.dlr.sc.virsat.model.concept.ConceptLanguage.ArrayModifier");
                    					afterParserOrEnumRuleCall();
                    				

                    }


                    }
                    break;

            }

            // InternalConceptLanguage.g:2103:3: ( ( ( ( ({...}? => ( ({...}? => (otherlv_5= 'description' ( (lv_description_6_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'default' ( (lv_defaultValue_8_0= ruleFloatLiteralString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_9= 'quantityKind' ( (lv_quantityKindName_10_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_11= 'unit' ( (lv_unitName_12_0= ruleEString ) ) ) ) ) ) )* ) ) )
            // InternalConceptLanguage.g:2104:4: ( ( ( ({...}? => ( ({...}? => (otherlv_5= 'description' ( (lv_description_6_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'default' ( (lv_defaultValue_8_0= ruleFloatLiteralString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_9= 'quantityKind' ( (lv_quantityKindName_10_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_11= 'unit' ( (lv_unitName_12_0= ruleEString ) ) ) ) ) ) )* ) )
            {
            // InternalConceptLanguage.g:2104:4: ( ( ( ({...}? => ( ({...}? => (otherlv_5= 'description' ( (lv_description_6_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'default' ( (lv_defaultValue_8_0= ruleFloatLiteralString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_9= 'quantityKind' ( (lv_quantityKindName_10_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_11= 'unit' ( (lv_unitName_12_0= ruleEString ) ) ) ) ) ) )* ) )
            // InternalConceptLanguage.g:2105:5: ( ( ({...}? => ( ({...}? => (otherlv_5= 'description' ( (lv_description_6_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'default' ( (lv_defaultValue_8_0= ruleFloatLiteralString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_9= 'quantityKind' ( (lv_quantityKindName_10_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_11= 'unit' ( (lv_unitName_12_0= ruleEString ) ) ) ) ) ) )* )
            {
             
            				  getUnorderedGroupHelper().enter(grammarAccess.getFloatPropertyAccess().getUnorderedGroup_4());
            				
            // InternalConceptLanguage.g:2108:5: ( ( ({...}? => ( ({...}? => (otherlv_5= 'description' ( (lv_description_6_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'default' ( (lv_defaultValue_8_0= ruleFloatLiteralString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_9= 'quantityKind' ( (lv_quantityKindName_10_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_11= 'unit' ( (lv_unitName_12_0= ruleEString ) ) ) ) ) ) )* )
            // InternalConceptLanguage.g:2109:6: ( ({...}? => ( ({...}? => (otherlv_5= 'description' ( (lv_description_6_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'default' ( (lv_defaultValue_8_0= ruleFloatLiteralString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_9= 'quantityKind' ( (lv_quantityKindName_10_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_11= 'unit' ( (lv_unitName_12_0= ruleEString ) ) ) ) ) ) )*
            {
            // InternalConceptLanguage.g:2109:6: ( ({...}? => ( ({...}? => (otherlv_5= 'description' ( (lv_description_6_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'default' ( (lv_defaultValue_8_0= ruleFloatLiteralString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_9= 'quantityKind' ( (lv_quantityKindName_10_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_11= 'unit' ( (lv_unitName_12_0= ruleEString ) ) ) ) ) ) )*
            loop30:
            do {
                int alt30=5;
                int LA30_0 = input.LA(1);

                if ( LA30_0 == 14 && getUnorderedGroupHelper().canSelect(grammarAccess.getFloatPropertyAccess().getUnorderedGroup_4(), 0) ) {
                    alt30=1;
                }
                else if ( LA30_0 == 42 && getUnorderedGroupHelper().canSelect(grammarAccess.getFloatPropertyAccess().getUnorderedGroup_4(), 1) ) {
                    alt30=2;
                }
                else if ( LA30_0 == 39 && getUnorderedGroupHelper().canSelect(grammarAccess.getFloatPropertyAccess().getUnorderedGroup_4(), 2) ) {
                    alt30=3;
                }
                else if ( LA30_0 == 40 && getUnorderedGroupHelper().canSelect(grammarAccess.getFloatPropertyAccess().getUnorderedGroup_4(), 3) ) {
                    alt30=4;
                }


                switch (alt30) {
            	case 1 :
            	    // InternalConceptLanguage.g:2110:4: ({...}? => ( ({...}? => (otherlv_5= 'description' ( (lv_description_6_0= ruleEString ) ) ) ) ) )
            	    {
            	    // InternalConceptLanguage.g:2110:4: ({...}? => ( ({...}? => (otherlv_5= 'description' ( (lv_description_6_0= ruleEString ) ) ) ) ) )
            	    // InternalConceptLanguage.g:2111:5: {...}? => ( ({...}? => (otherlv_5= 'description' ( (lv_description_6_0= ruleEString ) ) ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getFloatPropertyAccess().getUnorderedGroup_4(), 0) ) {
            	        throw new FailedPredicateException(input, "ruleFloatProperty", "getUnorderedGroupHelper().canSelect(grammarAccess.getFloatPropertyAccess().getUnorderedGroup_4(), 0)");
            	    }
            	    // InternalConceptLanguage.g:2111:110: ( ({...}? => (otherlv_5= 'description' ( (lv_description_6_0= ruleEString ) ) ) ) )
            	    // InternalConceptLanguage.g:2112:6: ({...}? => (otherlv_5= 'description' ( (lv_description_6_0= ruleEString ) ) ) )
            	    {

            	    						getUnorderedGroupHelper().select(grammarAccess.getFloatPropertyAccess().getUnorderedGroup_4(), 0);
            	    					
            	    // InternalConceptLanguage.g:2115:9: ({...}? => (otherlv_5= 'description' ( (lv_description_6_0= ruleEString ) ) ) )
            	    // InternalConceptLanguage.g:2115:10: {...}? => (otherlv_5= 'description' ( (lv_description_6_0= ruleEString ) ) )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleFloatProperty", "true");
            	    }
            	    // InternalConceptLanguage.g:2115:19: (otherlv_5= 'description' ( (lv_description_6_0= ruleEString ) ) )
            	    // InternalConceptLanguage.g:2115:20: otherlv_5= 'description' ( (lv_description_6_0= ruleEString ) )
            	    {
            	    otherlv_5=(Token)match(input,14,FOLLOW_5); 

            	    									newLeafNode(otherlv_5, grammarAccess.getFloatPropertyAccess().getDescriptionKeyword_4_0_0());
            	    								
            	    // InternalConceptLanguage.g:2119:9: ( (lv_description_6_0= ruleEString ) )
            	    // InternalConceptLanguage.g:2120:10: (lv_description_6_0= ruleEString )
            	    {
            	    // InternalConceptLanguage.g:2120:10: (lv_description_6_0= ruleEString )
            	    // InternalConceptLanguage.g:2121:11: lv_description_6_0= ruleEString
            	    {

            	    											newCompositeNode(grammarAccess.getFloatPropertyAccess().getDescriptionEStringParserRuleCall_4_0_1_0());
            	    										
            	    pushFollow(FOLLOW_35);
            	    lv_description_6_0=ruleEString();

            	    state._fsp--;


            	    											if (current==null) {
            	    												current = createModelElementForParent(grammarAccess.getFloatPropertyRule());
            	    											}
            	    											set(
            	    												current,
            	    												"description",
            	    												lv_description_6_0,
            	    												"de.dlr.sc.virsat.model.concept.ConceptLanguage.EString");
            	    											afterParserOrEnumRuleCall();
            	    										

            	    }


            	    }


            	    }


            	    }

            	     
            	    						getUnorderedGroupHelper().returnFromSelection(grammarAccess.getFloatPropertyAccess().getUnorderedGroup_4());
            	    					

            	    }


            	    }


            	    }
            	    break;
            	case 2 :
            	    // InternalConceptLanguage.g:2144:4: ({...}? => ( ({...}? => (otherlv_7= 'default' ( (lv_defaultValue_8_0= ruleFloatLiteralString ) ) ) ) ) )
            	    {
            	    // InternalConceptLanguage.g:2144:4: ({...}? => ( ({...}? => (otherlv_7= 'default' ( (lv_defaultValue_8_0= ruleFloatLiteralString ) ) ) ) ) )
            	    // InternalConceptLanguage.g:2145:5: {...}? => ( ({...}? => (otherlv_7= 'default' ( (lv_defaultValue_8_0= ruleFloatLiteralString ) ) ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getFloatPropertyAccess().getUnorderedGroup_4(), 1) ) {
            	        throw new FailedPredicateException(input, "ruleFloatProperty", "getUnorderedGroupHelper().canSelect(grammarAccess.getFloatPropertyAccess().getUnorderedGroup_4(), 1)");
            	    }
            	    // InternalConceptLanguage.g:2145:110: ( ({...}? => (otherlv_7= 'default' ( (lv_defaultValue_8_0= ruleFloatLiteralString ) ) ) ) )
            	    // InternalConceptLanguage.g:2146:6: ({...}? => (otherlv_7= 'default' ( (lv_defaultValue_8_0= ruleFloatLiteralString ) ) ) )
            	    {

            	    						getUnorderedGroupHelper().select(grammarAccess.getFloatPropertyAccess().getUnorderedGroup_4(), 1);
            	    					
            	    // InternalConceptLanguage.g:2149:9: ({...}? => (otherlv_7= 'default' ( (lv_defaultValue_8_0= ruleFloatLiteralString ) ) ) )
            	    // InternalConceptLanguage.g:2149:10: {...}? => (otherlv_7= 'default' ( (lv_defaultValue_8_0= ruleFloatLiteralString ) ) )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleFloatProperty", "true");
            	    }
            	    // InternalConceptLanguage.g:2149:19: (otherlv_7= 'default' ( (lv_defaultValue_8_0= ruleFloatLiteralString ) ) )
            	    // InternalConceptLanguage.g:2149:20: otherlv_7= 'default' ( (lv_defaultValue_8_0= ruleFloatLiteralString ) )
            	    {
            	    otherlv_7=(Token)match(input,42,FOLLOW_37); 

            	    									newLeafNode(otherlv_7, grammarAccess.getFloatPropertyAccess().getDefaultKeyword_4_1_0());
            	    								
            	    // InternalConceptLanguage.g:2153:9: ( (lv_defaultValue_8_0= ruleFloatLiteralString ) )
            	    // InternalConceptLanguage.g:2154:10: (lv_defaultValue_8_0= ruleFloatLiteralString )
            	    {
            	    // InternalConceptLanguage.g:2154:10: (lv_defaultValue_8_0= ruleFloatLiteralString )
            	    // InternalConceptLanguage.g:2155:11: lv_defaultValue_8_0= ruleFloatLiteralString
            	    {

            	    											newCompositeNode(grammarAccess.getFloatPropertyAccess().getDefaultValueFloatLiteralStringParserRuleCall_4_1_1_0());
            	    										
            	    pushFollow(FOLLOW_35);
            	    lv_defaultValue_8_0=ruleFloatLiteralString();

            	    state._fsp--;


            	    											if (current==null) {
            	    												current = createModelElementForParent(grammarAccess.getFloatPropertyRule());
            	    											}
            	    											set(
            	    												current,
            	    												"defaultValue",
            	    												lv_defaultValue_8_0,
            	    												"de.dlr.sc.virsat.model.concept.ConceptLanguage.FloatLiteralString");
            	    											afterParserOrEnumRuleCall();
            	    										

            	    }


            	    }


            	    }


            	    }

            	     
            	    						getUnorderedGroupHelper().returnFromSelection(grammarAccess.getFloatPropertyAccess().getUnorderedGroup_4());
            	    					

            	    }


            	    }


            	    }
            	    break;
            	case 3 :
            	    // InternalConceptLanguage.g:2178:4: ({...}? => ( ({...}? => (otherlv_9= 'quantityKind' ( (lv_quantityKindName_10_0= ruleEString ) ) ) ) ) )
            	    {
            	    // InternalConceptLanguage.g:2178:4: ({...}? => ( ({...}? => (otherlv_9= 'quantityKind' ( (lv_quantityKindName_10_0= ruleEString ) ) ) ) ) )
            	    // InternalConceptLanguage.g:2179:5: {...}? => ( ({...}? => (otherlv_9= 'quantityKind' ( (lv_quantityKindName_10_0= ruleEString ) ) ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getFloatPropertyAccess().getUnorderedGroup_4(), 2) ) {
            	        throw new FailedPredicateException(input, "ruleFloatProperty", "getUnorderedGroupHelper().canSelect(grammarAccess.getFloatPropertyAccess().getUnorderedGroup_4(), 2)");
            	    }
            	    // InternalConceptLanguage.g:2179:110: ( ({...}? => (otherlv_9= 'quantityKind' ( (lv_quantityKindName_10_0= ruleEString ) ) ) ) )
            	    // InternalConceptLanguage.g:2180:6: ({...}? => (otherlv_9= 'quantityKind' ( (lv_quantityKindName_10_0= ruleEString ) ) ) )
            	    {

            	    						getUnorderedGroupHelper().select(grammarAccess.getFloatPropertyAccess().getUnorderedGroup_4(), 2);
            	    					
            	    // InternalConceptLanguage.g:2183:9: ({...}? => (otherlv_9= 'quantityKind' ( (lv_quantityKindName_10_0= ruleEString ) ) ) )
            	    // InternalConceptLanguage.g:2183:10: {...}? => (otherlv_9= 'quantityKind' ( (lv_quantityKindName_10_0= ruleEString ) ) )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleFloatProperty", "true");
            	    }
            	    // InternalConceptLanguage.g:2183:19: (otherlv_9= 'quantityKind' ( (lv_quantityKindName_10_0= ruleEString ) ) )
            	    // InternalConceptLanguage.g:2183:20: otherlv_9= 'quantityKind' ( (lv_quantityKindName_10_0= ruleEString ) )
            	    {
            	    otherlv_9=(Token)match(input,39,FOLLOW_5); 

            	    									newLeafNode(otherlv_9, grammarAccess.getFloatPropertyAccess().getQuantityKindKeyword_4_2_0());
            	    								
            	    // InternalConceptLanguage.g:2187:9: ( (lv_quantityKindName_10_0= ruleEString ) )
            	    // InternalConceptLanguage.g:2188:10: (lv_quantityKindName_10_0= ruleEString )
            	    {
            	    // InternalConceptLanguage.g:2188:10: (lv_quantityKindName_10_0= ruleEString )
            	    // InternalConceptLanguage.g:2189:11: lv_quantityKindName_10_0= ruleEString
            	    {

            	    											newCompositeNode(grammarAccess.getFloatPropertyAccess().getQuantityKindNameEStringParserRuleCall_4_2_1_0());
            	    										
            	    pushFollow(FOLLOW_35);
            	    lv_quantityKindName_10_0=ruleEString();

            	    state._fsp--;


            	    											if (current==null) {
            	    												current = createModelElementForParent(grammarAccess.getFloatPropertyRule());
            	    											}
            	    											set(
            	    												current,
            	    												"quantityKindName",
            	    												lv_quantityKindName_10_0,
            	    												"de.dlr.sc.virsat.model.concept.ConceptLanguage.EString");
            	    											afterParserOrEnumRuleCall();
            	    										

            	    }


            	    }


            	    }


            	    }

            	     
            	    						getUnorderedGroupHelper().returnFromSelection(grammarAccess.getFloatPropertyAccess().getUnorderedGroup_4());
            	    					

            	    }


            	    }


            	    }
            	    break;
            	case 4 :
            	    // InternalConceptLanguage.g:2212:4: ({...}? => ( ({...}? => (otherlv_11= 'unit' ( (lv_unitName_12_0= ruleEString ) ) ) ) ) )
            	    {
            	    // InternalConceptLanguage.g:2212:4: ({...}? => ( ({...}? => (otherlv_11= 'unit' ( (lv_unitName_12_0= ruleEString ) ) ) ) ) )
            	    // InternalConceptLanguage.g:2213:5: {...}? => ( ({...}? => (otherlv_11= 'unit' ( (lv_unitName_12_0= ruleEString ) ) ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getFloatPropertyAccess().getUnorderedGroup_4(), 3) ) {
            	        throw new FailedPredicateException(input, "ruleFloatProperty", "getUnorderedGroupHelper().canSelect(grammarAccess.getFloatPropertyAccess().getUnorderedGroup_4(), 3)");
            	    }
            	    // InternalConceptLanguage.g:2213:110: ( ({...}? => (otherlv_11= 'unit' ( (lv_unitName_12_0= ruleEString ) ) ) ) )
            	    // InternalConceptLanguage.g:2214:6: ({...}? => (otherlv_11= 'unit' ( (lv_unitName_12_0= ruleEString ) ) ) )
            	    {

            	    						getUnorderedGroupHelper().select(grammarAccess.getFloatPropertyAccess().getUnorderedGroup_4(), 3);
            	    					
            	    // InternalConceptLanguage.g:2217:9: ({...}? => (otherlv_11= 'unit' ( (lv_unitName_12_0= ruleEString ) ) ) )
            	    // InternalConceptLanguage.g:2217:10: {...}? => (otherlv_11= 'unit' ( (lv_unitName_12_0= ruleEString ) ) )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleFloatProperty", "true");
            	    }
            	    // InternalConceptLanguage.g:2217:19: (otherlv_11= 'unit' ( (lv_unitName_12_0= ruleEString ) ) )
            	    // InternalConceptLanguage.g:2217:20: otherlv_11= 'unit' ( (lv_unitName_12_0= ruleEString ) )
            	    {
            	    otherlv_11=(Token)match(input,40,FOLLOW_5); 

            	    									newLeafNode(otherlv_11, grammarAccess.getFloatPropertyAccess().getUnitKeyword_4_3_0());
            	    								
            	    // InternalConceptLanguage.g:2221:9: ( (lv_unitName_12_0= ruleEString ) )
            	    // InternalConceptLanguage.g:2222:10: (lv_unitName_12_0= ruleEString )
            	    {
            	    // InternalConceptLanguage.g:2222:10: (lv_unitName_12_0= ruleEString )
            	    // InternalConceptLanguage.g:2223:11: lv_unitName_12_0= ruleEString
            	    {

            	    											newCompositeNode(grammarAccess.getFloatPropertyAccess().getUnitNameEStringParserRuleCall_4_3_1_0());
            	    										
            	    pushFollow(FOLLOW_35);
            	    lv_unitName_12_0=ruleEString();

            	    state._fsp--;


            	    											if (current==null) {
            	    												current = createModelElementForParent(grammarAccess.getFloatPropertyRule());
            	    											}
            	    											set(
            	    												current,
            	    												"unitName",
            	    												lv_unitName_12_0,
            	    												"de.dlr.sc.virsat.model.concept.ConceptLanguage.EString");
            	    											afterParserOrEnumRuleCall();
            	    										

            	    }


            	    }


            	    }


            	    }

            	     
            	    						getUnorderedGroupHelper().returnFromSelection(grammarAccess.getFloatPropertyAccess().getUnorderedGroup_4());
            	    					

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop30;
                }
            } while (true);


            }


            }

             
            				  getUnorderedGroupHelper().leave(grammarAccess.getFloatPropertyAccess().getUnorderedGroup_4());
            				

            }

            otherlv_13=(Token)match(input,21,FOLLOW_2); 

            			newLeafNode(otherlv_13, grammarAccess.getFloatPropertyAccess().getSemicolonKeyword_5());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleFloatProperty"


    // $ANTLR start "entryRuleStringProperty"
    // InternalConceptLanguage.g:2261:1: entryRuleStringProperty returns [EObject current=null] : iv_ruleStringProperty= ruleStringProperty EOF ;
    public final EObject entryRuleStringProperty() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleStringProperty = null;


        try {
            // InternalConceptLanguage.g:2261:55: (iv_ruleStringProperty= ruleStringProperty EOF )
            // InternalConceptLanguage.g:2262:2: iv_ruleStringProperty= ruleStringProperty EOF
            {
             newCompositeNode(grammarAccess.getStringPropertyRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleStringProperty=ruleStringProperty();

            state._fsp--;

             current =iv_ruleStringProperty; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleStringProperty"


    // $ANTLR start "ruleStringProperty"
    // InternalConceptLanguage.g:2268:1: ruleStringProperty returns [EObject current=null] : ( () otherlv_1= 'StringProperty' ( (lv_name_2_0= RULE_ID ) ) ( (lv_arrayModifier_3_0= ruleArrayModifier ) )? ( ( ( ( ({...}? => ( ({...}? => (otherlv_5= 'description' ( (lv_description_6_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'default' ( (lv_defaultValue_8_0= ruleEString ) ) ) ) ) ) )* ) ) ) otherlv_9= ';' ) ;
    public final EObject ruleStringProperty() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token lv_name_2_0=null;
        Token otherlv_5=null;
        Token otherlv_7=null;
        Token otherlv_9=null;
        EObject lv_arrayModifier_3_0 = null;

        AntlrDatatypeRuleToken lv_description_6_0 = null;

        AntlrDatatypeRuleToken lv_defaultValue_8_0 = null;



        	enterRule();

        try {
            // InternalConceptLanguage.g:2274:2: ( ( () otherlv_1= 'StringProperty' ( (lv_name_2_0= RULE_ID ) ) ( (lv_arrayModifier_3_0= ruleArrayModifier ) )? ( ( ( ( ({...}? => ( ({...}? => (otherlv_5= 'description' ( (lv_description_6_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'default' ( (lv_defaultValue_8_0= ruleEString ) ) ) ) ) ) )* ) ) ) otherlv_9= ';' ) )
            // InternalConceptLanguage.g:2275:2: ( () otherlv_1= 'StringProperty' ( (lv_name_2_0= RULE_ID ) ) ( (lv_arrayModifier_3_0= ruleArrayModifier ) )? ( ( ( ( ({...}? => ( ({...}? => (otherlv_5= 'description' ( (lv_description_6_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'default' ( (lv_defaultValue_8_0= ruleEString ) ) ) ) ) ) )* ) ) ) otherlv_9= ';' )
            {
            // InternalConceptLanguage.g:2275:2: ( () otherlv_1= 'StringProperty' ( (lv_name_2_0= RULE_ID ) ) ( (lv_arrayModifier_3_0= ruleArrayModifier ) )? ( ( ( ( ({...}? => ( ({...}? => (otherlv_5= 'description' ( (lv_description_6_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'default' ( (lv_defaultValue_8_0= ruleEString ) ) ) ) ) ) )* ) ) ) otherlv_9= ';' )
            // InternalConceptLanguage.g:2276:3: () otherlv_1= 'StringProperty' ( (lv_name_2_0= RULE_ID ) ) ( (lv_arrayModifier_3_0= ruleArrayModifier ) )? ( ( ( ( ({...}? => ( ({...}? => (otherlv_5= 'description' ( (lv_description_6_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'default' ( (lv_defaultValue_8_0= ruleEString ) ) ) ) ) ) )* ) ) ) otherlv_9= ';'
            {
            // InternalConceptLanguage.g:2276:3: ()
            // InternalConceptLanguage.g:2277:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getStringPropertyAccess().getStringPropertyAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,44,FOLLOW_3); 

            			newLeafNode(otherlv_1, grammarAccess.getStringPropertyAccess().getStringPropertyKeyword_1());
            		
            // InternalConceptLanguage.g:2287:3: ( (lv_name_2_0= RULE_ID ) )
            // InternalConceptLanguage.g:2288:4: (lv_name_2_0= RULE_ID )
            {
            // InternalConceptLanguage.g:2288:4: (lv_name_2_0= RULE_ID )
            // InternalConceptLanguage.g:2289:5: lv_name_2_0= RULE_ID
            {
            lv_name_2_0=(Token)match(input,RULE_ID,FOLLOW_38); 

            					newLeafNode(lv_name_2_0, grammarAccess.getStringPropertyAccess().getNameIDTerminalRuleCall_2_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getStringPropertyRule());
            					}
            					setWithLastConsumed(
            						current,
            						"name",
            						lv_name_2_0,
            						"org.eclipse.xtext.common.Terminals.ID");
            				

            }


            }

            // InternalConceptLanguage.g:2305:3: ( (lv_arrayModifier_3_0= ruleArrayModifier ) )?
            int alt31=2;
            int LA31_0 = input.LA(1);

            if ( (LA31_0==24) ) {
                alt31=1;
            }
            switch (alt31) {
                case 1 :
                    // InternalConceptLanguage.g:2306:4: (lv_arrayModifier_3_0= ruleArrayModifier )
                    {
                    // InternalConceptLanguage.g:2306:4: (lv_arrayModifier_3_0= ruleArrayModifier )
                    // InternalConceptLanguage.g:2307:5: lv_arrayModifier_3_0= ruleArrayModifier
                    {

                    					newCompositeNode(grammarAccess.getStringPropertyAccess().getArrayModifierArrayModifierParserRuleCall_3_0());
                    				
                    pushFollow(FOLLOW_39);
                    lv_arrayModifier_3_0=ruleArrayModifier();

                    state._fsp--;


                    					if (current==null) {
                    						current = createModelElementForParent(grammarAccess.getStringPropertyRule());
                    					}
                    					set(
                    						current,
                    						"arrayModifier",
                    						lv_arrayModifier_3_0,
                    						"de.dlr.sc.virsat.model.concept.ConceptLanguage.ArrayModifier");
                    					afterParserOrEnumRuleCall();
                    				

                    }


                    }
                    break;

            }

            // InternalConceptLanguage.g:2324:3: ( ( ( ( ({...}? => ( ({...}? => (otherlv_5= 'description' ( (lv_description_6_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'default' ( (lv_defaultValue_8_0= ruleEString ) ) ) ) ) ) )* ) ) )
            // InternalConceptLanguage.g:2325:4: ( ( ( ({...}? => ( ({...}? => (otherlv_5= 'description' ( (lv_description_6_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'default' ( (lv_defaultValue_8_0= ruleEString ) ) ) ) ) ) )* ) )
            {
            // InternalConceptLanguage.g:2325:4: ( ( ( ({...}? => ( ({...}? => (otherlv_5= 'description' ( (lv_description_6_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'default' ( (lv_defaultValue_8_0= ruleEString ) ) ) ) ) ) )* ) )
            // InternalConceptLanguage.g:2326:5: ( ( ({...}? => ( ({...}? => (otherlv_5= 'description' ( (lv_description_6_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'default' ( (lv_defaultValue_8_0= ruleEString ) ) ) ) ) ) )* )
            {
             
            				  getUnorderedGroupHelper().enter(grammarAccess.getStringPropertyAccess().getUnorderedGroup_4());
            				
            // InternalConceptLanguage.g:2329:5: ( ( ({...}? => ( ({...}? => (otherlv_5= 'description' ( (lv_description_6_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'default' ( (lv_defaultValue_8_0= ruleEString ) ) ) ) ) ) )* )
            // InternalConceptLanguage.g:2330:6: ( ({...}? => ( ({...}? => (otherlv_5= 'description' ( (lv_description_6_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'default' ( (lv_defaultValue_8_0= ruleEString ) ) ) ) ) ) )*
            {
            // InternalConceptLanguage.g:2330:6: ( ({...}? => ( ({...}? => (otherlv_5= 'description' ( (lv_description_6_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'default' ( (lv_defaultValue_8_0= ruleEString ) ) ) ) ) ) )*
            loop32:
            do {
                int alt32=3;
                int LA32_0 = input.LA(1);

                if ( LA32_0 == 14 && getUnorderedGroupHelper().canSelect(grammarAccess.getStringPropertyAccess().getUnorderedGroup_4(), 0) ) {
                    alt32=1;
                }
                else if ( LA32_0 == 42 && getUnorderedGroupHelper().canSelect(grammarAccess.getStringPropertyAccess().getUnorderedGroup_4(), 1) ) {
                    alt32=2;
                }


                switch (alt32) {
            	case 1 :
            	    // InternalConceptLanguage.g:2331:4: ({...}? => ( ({...}? => (otherlv_5= 'description' ( (lv_description_6_0= ruleEString ) ) ) ) ) )
            	    {
            	    // InternalConceptLanguage.g:2331:4: ({...}? => ( ({...}? => (otherlv_5= 'description' ( (lv_description_6_0= ruleEString ) ) ) ) ) )
            	    // InternalConceptLanguage.g:2332:5: {...}? => ( ({...}? => (otherlv_5= 'description' ( (lv_description_6_0= ruleEString ) ) ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getStringPropertyAccess().getUnorderedGroup_4(), 0) ) {
            	        throw new FailedPredicateException(input, "ruleStringProperty", "getUnorderedGroupHelper().canSelect(grammarAccess.getStringPropertyAccess().getUnorderedGroup_4(), 0)");
            	    }
            	    // InternalConceptLanguage.g:2332:111: ( ({...}? => (otherlv_5= 'description' ( (lv_description_6_0= ruleEString ) ) ) ) )
            	    // InternalConceptLanguage.g:2333:6: ({...}? => (otherlv_5= 'description' ( (lv_description_6_0= ruleEString ) ) ) )
            	    {

            	    						getUnorderedGroupHelper().select(grammarAccess.getStringPropertyAccess().getUnorderedGroup_4(), 0);
            	    					
            	    // InternalConceptLanguage.g:2336:9: ({...}? => (otherlv_5= 'description' ( (lv_description_6_0= ruleEString ) ) ) )
            	    // InternalConceptLanguage.g:2336:10: {...}? => (otherlv_5= 'description' ( (lv_description_6_0= ruleEString ) ) )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleStringProperty", "true");
            	    }
            	    // InternalConceptLanguage.g:2336:19: (otherlv_5= 'description' ( (lv_description_6_0= ruleEString ) ) )
            	    // InternalConceptLanguage.g:2336:20: otherlv_5= 'description' ( (lv_description_6_0= ruleEString ) )
            	    {
            	    otherlv_5=(Token)match(input,14,FOLLOW_5); 

            	    									newLeafNode(otherlv_5, grammarAccess.getStringPropertyAccess().getDescriptionKeyword_4_0_0());
            	    								
            	    // InternalConceptLanguage.g:2340:9: ( (lv_description_6_0= ruleEString ) )
            	    // InternalConceptLanguage.g:2341:10: (lv_description_6_0= ruleEString )
            	    {
            	    // InternalConceptLanguage.g:2341:10: (lv_description_6_0= ruleEString )
            	    // InternalConceptLanguage.g:2342:11: lv_description_6_0= ruleEString
            	    {

            	    											newCompositeNode(grammarAccess.getStringPropertyAccess().getDescriptionEStringParserRuleCall_4_0_1_0());
            	    										
            	    pushFollow(FOLLOW_39);
            	    lv_description_6_0=ruleEString();

            	    state._fsp--;


            	    											if (current==null) {
            	    												current = createModelElementForParent(grammarAccess.getStringPropertyRule());
            	    											}
            	    											set(
            	    												current,
            	    												"description",
            	    												lv_description_6_0,
            	    												"de.dlr.sc.virsat.model.concept.ConceptLanguage.EString");
            	    											afterParserOrEnumRuleCall();
            	    										

            	    }


            	    }


            	    }


            	    }

            	     
            	    						getUnorderedGroupHelper().returnFromSelection(grammarAccess.getStringPropertyAccess().getUnorderedGroup_4());
            	    					

            	    }


            	    }


            	    }
            	    break;
            	case 2 :
            	    // InternalConceptLanguage.g:2365:4: ({...}? => ( ({...}? => (otherlv_7= 'default' ( (lv_defaultValue_8_0= ruleEString ) ) ) ) ) )
            	    {
            	    // InternalConceptLanguage.g:2365:4: ({...}? => ( ({...}? => (otherlv_7= 'default' ( (lv_defaultValue_8_0= ruleEString ) ) ) ) ) )
            	    // InternalConceptLanguage.g:2366:5: {...}? => ( ({...}? => (otherlv_7= 'default' ( (lv_defaultValue_8_0= ruleEString ) ) ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getStringPropertyAccess().getUnorderedGroup_4(), 1) ) {
            	        throw new FailedPredicateException(input, "ruleStringProperty", "getUnorderedGroupHelper().canSelect(grammarAccess.getStringPropertyAccess().getUnorderedGroup_4(), 1)");
            	    }
            	    // InternalConceptLanguage.g:2366:111: ( ({...}? => (otherlv_7= 'default' ( (lv_defaultValue_8_0= ruleEString ) ) ) ) )
            	    // InternalConceptLanguage.g:2367:6: ({...}? => (otherlv_7= 'default' ( (lv_defaultValue_8_0= ruleEString ) ) ) )
            	    {

            	    						getUnorderedGroupHelper().select(grammarAccess.getStringPropertyAccess().getUnorderedGroup_4(), 1);
            	    					
            	    // InternalConceptLanguage.g:2370:9: ({...}? => (otherlv_7= 'default' ( (lv_defaultValue_8_0= ruleEString ) ) ) )
            	    // InternalConceptLanguage.g:2370:10: {...}? => (otherlv_7= 'default' ( (lv_defaultValue_8_0= ruleEString ) ) )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleStringProperty", "true");
            	    }
            	    // InternalConceptLanguage.g:2370:19: (otherlv_7= 'default' ( (lv_defaultValue_8_0= ruleEString ) ) )
            	    // InternalConceptLanguage.g:2370:20: otherlv_7= 'default' ( (lv_defaultValue_8_0= ruleEString ) )
            	    {
            	    otherlv_7=(Token)match(input,42,FOLLOW_5); 

            	    									newLeafNode(otherlv_7, grammarAccess.getStringPropertyAccess().getDefaultKeyword_4_1_0());
            	    								
            	    // InternalConceptLanguage.g:2374:9: ( (lv_defaultValue_8_0= ruleEString ) )
            	    // InternalConceptLanguage.g:2375:10: (lv_defaultValue_8_0= ruleEString )
            	    {
            	    // InternalConceptLanguage.g:2375:10: (lv_defaultValue_8_0= ruleEString )
            	    // InternalConceptLanguage.g:2376:11: lv_defaultValue_8_0= ruleEString
            	    {

            	    											newCompositeNode(grammarAccess.getStringPropertyAccess().getDefaultValueEStringParserRuleCall_4_1_1_0());
            	    										
            	    pushFollow(FOLLOW_39);
            	    lv_defaultValue_8_0=ruleEString();

            	    state._fsp--;


            	    											if (current==null) {
            	    												current = createModelElementForParent(grammarAccess.getStringPropertyRule());
            	    											}
            	    											set(
            	    												current,
            	    												"defaultValue",
            	    												lv_defaultValue_8_0,
            	    												"de.dlr.sc.virsat.model.concept.ConceptLanguage.EString");
            	    											afterParserOrEnumRuleCall();
            	    										

            	    }


            	    }


            	    }


            	    }

            	     
            	    						getUnorderedGroupHelper().returnFromSelection(grammarAccess.getStringPropertyAccess().getUnorderedGroup_4());
            	    					

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop32;
                }
            } while (true);


            }


            }

             
            				  getUnorderedGroupHelper().leave(grammarAccess.getStringPropertyAccess().getUnorderedGroup_4());
            				

            }

            otherlv_9=(Token)match(input,21,FOLLOW_2); 

            			newLeafNode(otherlv_9, grammarAccess.getStringPropertyAccess().getSemicolonKeyword_5());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleStringProperty"


    // $ANTLR start "entryRuleBooleanProperty"
    // InternalConceptLanguage.g:2414:1: entryRuleBooleanProperty returns [EObject current=null] : iv_ruleBooleanProperty= ruleBooleanProperty EOF ;
    public final EObject entryRuleBooleanProperty() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleBooleanProperty = null;


        try {
            // InternalConceptLanguage.g:2414:56: (iv_ruleBooleanProperty= ruleBooleanProperty EOF )
            // InternalConceptLanguage.g:2415:2: iv_ruleBooleanProperty= ruleBooleanProperty EOF
            {
             newCompositeNode(grammarAccess.getBooleanPropertyRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleBooleanProperty=ruleBooleanProperty();

            state._fsp--;

             current =iv_ruleBooleanProperty; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleBooleanProperty"


    // $ANTLR start "ruleBooleanProperty"
    // InternalConceptLanguage.g:2421:1: ruleBooleanProperty returns [EObject current=null] : (otherlv_0= 'BooleanProperty' ( (lv_name_1_0= RULE_ID ) ) ( (lv_arrayModifier_2_0= ruleArrayModifier ) )? ( ( ( ( ({...}? => ( ({...}? => (otherlv_4= 'description' ( (lv_description_5_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_6= 'default' ( (lv_defaultValue_7_0= ruleBooleanLiteralString ) ) ) ) ) ) )* ) ) ) otherlv_8= ';' ) ;
    public final EObject ruleBooleanProperty() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token lv_name_1_0=null;
        Token otherlv_4=null;
        Token otherlv_6=null;
        Token otherlv_8=null;
        EObject lv_arrayModifier_2_0 = null;

        AntlrDatatypeRuleToken lv_description_5_0 = null;

        AntlrDatatypeRuleToken lv_defaultValue_7_0 = null;



        	enterRule();

        try {
            // InternalConceptLanguage.g:2427:2: ( (otherlv_0= 'BooleanProperty' ( (lv_name_1_0= RULE_ID ) ) ( (lv_arrayModifier_2_0= ruleArrayModifier ) )? ( ( ( ( ({...}? => ( ({...}? => (otherlv_4= 'description' ( (lv_description_5_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_6= 'default' ( (lv_defaultValue_7_0= ruleBooleanLiteralString ) ) ) ) ) ) )* ) ) ) otherlv_8= ';' ) )
            // InternalConceptLanguage.g:2428:2: (otherlv_0= 'BooleanProperty' ( (lv_name_1_0= RULE_ID ) ) ( (lv_arrayModifier_2_0= ruleArrayModifier ) )? ( ( ( ( ({...}? => ( ({...}? => (otherlv_4= 'description' ( (lv_description_5_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_6= 'default' ( (lv_defaultValue_7_0= ruleBooleanLiteralString ) ) ) ) ) ) )* ) ) ) otherlv_8= ';' )
            {
            // InternalConceptLanguage.g:2428:2: (otherlv_0= 'BooleanProperty' ( (lv_name_1_0= RULE_ID ) ) ( (lv_arrayModifier_2_0= ruleArrayModifier ) )? ( ( ( ( ({...}? => ( ({...}? => (otherlv_4= 'description' ( (lv_description_5_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_6= 'default' ( (lv_defaultValue_7_0= ruleBooleanLiteralString ) ) ) ) ) ) )* ) ) ) otherlv_8= ';' )
            // InternalConceptLanguage.g:2429:3: otherlv_0= 'BooleanProperty' ( (lv_name_1_0= RULE_ID ) ) ( (lv_arrayModifier_2_0= ruleArrayModifier ) )? ( ( ( ( ({...}? => ( ({...}? => (otherlv_4= 'description' ( (lv_description_5_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_6= 'default' ( (lv_defaultValue_7_0= ruleBooleanLiteralString ) ) ) ) ) ) )* ) ) ) otherlv_8= ';'
            {
            otherlv_0=(Token)match(input,45,FOLLOW_3); 

            			newLeafNode(otherlv_0, grammarAccess.getBooleanPropertyAccess().getBooleanPropertyKeyword_0());
            		
            // InternalConceptLanguage.g:2433:3: ( (lv_name_1_0= RULE_ID ) )
            // InternalConceptLanguage.g:2434:4: (lv_name_1_0= RULE_ID )
            {
            // InternalConceptLanguage.g:2434:4: (lv_name_1_0= RULE_ID )
            // InternalConceptLanguage.g:2435:5: lv_name_1_0= RULE_ID
            {
            lv_name_1_0=(Token)match(input,RULE_ID,FOLLOW_38); 

            					newLeafNode(lv_name_1_0, grammarAccess.getBooleanPropertyAccess().getNameIDTerminalRuleCall_1_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getBooleanPropertyRule());
            					}
            					setWithLastConsumed(
            						current,
            						"name",
            						lv_name_1_0,
            						"org.eclipse.xtext.common.Terminals.ID");
            				

            }


            }

            // InternalConceptLanguage.g:2451:3: ( (lv_arrayModifier_2_0= ruleArrayModifier ) )?
            int alt33=2;
            int LA33_0 = input.LA(1);

            if ( (LA33_0==24) ) {
                alt33=1;
            }
            switch (alt33) {
                case 1 :
                    // InternalConceptLanguage.g:2452:4: (lv_arrayModifier_2_0= ruleArrayModifier )
                    {
                    // InternalConceptLanguage.g:2452:4: (lv_arrayModifier_2_0= ruleArrayModifier )
                    // InternalConceptLanguage.g:2453:5: lv_arrayModifier_2_0= ruleArrayModifier
                    {

                    					newCompositeNode(grammarAccess.getBooleanPropertyAccess().getArrayModifierArrayModifierParserRuleCall_2_0());
                    				
                    pushFollow(FOLLOW_39);
                    lv_arrayModifier_2_0=ruleArrayModifier();

                    state._fsp--;


                    					if (current==null) {
                    						current = createModelElementForParent(grammarAccess.getBooleanPropertyRule());
                    					}
                    					set(
                    						current,
                    						"arrayModifier",
                    						lv_arrayModifier_2_0,
                    						"de.dlr.sc.virsat.model.concept.ConceptLanguage.ArrayModifier");
                    					afterParserOrEnumRuleCall();
                    				

                    }


                    }
                    break;

            }

            // InternalConceptLanguage.g:2470:3: ( ( ( ( ({...}? => ( ({...}? => (otherlv_4= 'description' ( (lv_description_5_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_6= 'default' ( (lv_defaultValue_7_0= ruleBooleanLiteralString ) ) ) ) ) ) )* ) ) )
            // InternalConceptLanguage.g:2471:4: ( ( ( ({...}? => ( ({...}? => (otherlv_4= 'description' ( (lv_description_5_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_6= 'default' ( (lv_defaultValue_7_0= ruleBooleanLiteralString ) ) ) ) ) ) )* ) )
            {
            // InternalConceptLanguage.g:2471:4: ( ( ( ({...}? => ( ({...}? => (otherlv_4= 'description' ( (lv_description_5_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_6= 'default' ( (lv_defaultValue_7_0= ruleBooleanLiteralString ) ) ) ) ) ) )* ) )
            // InternalConceptLanguage.g:2472:5: ( ( ({...}? => ( ({...}? => (otherlv_4= 'description' ( (lv_description_5_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_6= 'default' ( (lv_defaultValue_7_0= ruleBooleanLiteralString ) ) ) ) ) ) )* )
            {
             
            				  getUnorderedGroupHelper().enter(grammarAccess.getBooleanPropertyAccess().getUnorderedGroup_3());
            				
            // InternalConceptLanguage.g:2475:5: ( ( ({...}? => ( ({...}? => (otherlv_4= 'description' ( (lv_description_5_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_6= 'default' ( (lv_defaultValue_7_0= ruleBooleanLiteralString ) ) ) ) ) ) )* )
            // InternalConceptLanguage.g:2476:6: ( ({...}? => ( ({...}? => (otherlv_4= 'description' ( (lv_description_5_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_6= 'default' ( (lv_defaultValue_7_0= ruleBooleanLiteralString ) ) ) ) ) ) )*
            {
            // InternalConceptLanguage.g:2476:6: ( ({...}? => ( ({...}? => (otherlv_4= 'description' ( (lv_description_5_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_6= 'default' ( (lv_defaultValue_7_0= ruleBooleanLiteralString ) ) ) ) ) ) )*
            loop34:
            do {
                int alt34=3;
                int LA34_0 = input.LA(1);

                if ( LA34_0 == 14 && getUnorderedGroupHelper().canSelect(grammarAccess.getBooleanPropertyAccess().getUnorderedGroup_3(), 0) ) {
                    alt34=1;
                }
                else if ( LA34_0 == 42 && getUnorderedGroupHelper().canSelect(grammarAccess.getBooleanPropertyAccess().getUnorderedGroup_3(), 1) ) {
                    alt34=2;
                }


                switch (alt34) {
            	case 1 :
            	    // InternalConceptLanguage.g:2477:4: ({...}? => ( ({...}? => (otherlv_4= 'description' ( (lv_description_5_0= ruleEString ) ) ) ) ) )
            	    {
            	    // InternalConceptLanguage.g:2477:4: ({...}? => ( ({...}? => (otherlv_4= 'description' ( (lv_description_5_0= ruleEString ) ) ) ) ) )
            	    // InternalConceptLanguage.g:2478:5: {...}? => ( ({...}? => (otherlv_4= 'description' ( (lv_description_5_0= ruleEString ) ) ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getBooleanPropertyAccess().getUnorderedGroup_3(), 0) ) {
            	        throw new FailedPredicateException(input, "ruleBooleanProperty", "getUnorderedGroupHelper().canSelect(grammarAccess.getBooleanPropertyAccess().getUnorderedGroup_3(), 0)");
            	    }
            	    // InternalConceptLanguage.g:2478:112: ( ({...}? => (otherlv_4= 'description' ( (lv_description_5_0= ruleEString ) ) ) ) )
            	    // InternalConceptLanguage.g:2479:6: ({...}? => (otherlv_4= 'description' ( (lv_description_5_0= ruleEString ) ) ) )
            	    {

            	    						getUnorderedGroupHelper().select(grammarAccess.getBooleanPropertyAccess().getUnorderedGroup_3(), 0);
            	    					
            	    // InternalConceptLanguage.g:2482:9: ({...}? => (otherlv_4= 'description' ( (lv_description_5_0= ruleEString ) ) ) )
            	    // InternalConceptLanguage.g:2482:10: {...}? => (otherlv_4= 'description' ( (lv_description_5_0= ruleEString ) ) )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleBooleanProperty", "true");
            	    }
            	    // InternalConceptLanguage.g:2482:19: (otherlv_4= 'description' ( (lv_description_5_0= ruleEString ) ) )
            	    // InternalConceptLanguage.g:2482:20: otherlv_4= 'description' ( (lv_description_5_0= ruleEString ) )
            	    {
            	    otherlv_4=(Token)match(input,14,FOLLOW_5); 

            	    									newLeafNode(otherlv_4, grammarAccess.getBooleanPropertyAccess().getDescriptionKeyword_3_0_0());
            	    								
            	    // InternalConceptLanguage.g:2486:9: ( (lv_description_5_0= ruleEString ) )
            	    // InternalConceptLanguage.g:2487:10: (lv_description_5_0= ruleEString )
            	    {
            	    // InternalConceptLanguage.g:2487:10: (lv_description_5_0= ruleEString )
            	    // InternalConceptLanguage.g:2488:11: lv_description_5_0= ruleEString
            	    {

            	    											newCompositeNode(grammarAccess.getBooleanPropertyAccess().getDescriptionEStringParserRuleCall_3_0_1_0());
            	    										
            	    pushFollow(FOLLOW_39);
            	    lv_description_5_0=ruleEString();

            	    state._fsp--;


            	    											if (current==null) {
            	    												current = createModelElementForParent(grammarAccess.getBooleanPropertyRule());
            	    											}
            	    											set(
            	    												current,
            	    												"description",
            	    												lv_description_5_0,
            	    												"de.dlr.sc.virsat.model.concept.ConceptLanguage.EString");
            	    											afterParserOrEnumRuleCall();
            	    										

            	    }


            	    }


            	    }


            	    }

            	     
            	    						getUnorderedGroupHelper().returnFromSelection(grammarAccess.getBooleanPropertyAccess().getUnorderedGroup_3());
            	    					

            	    }


            	    }


            	    }
            	    break;
            	case 2 :
            	    // InternalConceptLanguage.g:2511:4: ({...}? => ( ({...}? => (otherlv_6= 'default' ( (lv_defaultValue_7_0= ruleBooleanLiteralString ) ) ) ) ) )
            	    {
            	    // InternalConceptLanguage.g:2511:4: ({...}? => ( ({...}? => (otherlv_6= 'default' ( (lv_defaultValue_7_0= ruleBooleanLiteralString ) ) ) ) ) )
            	    // InternalConceptLanguage.g:2512:5: {...}? => ( ({...}? => (otherlv_6= 'default' ( (lv_defaultValue_7_0= ruleBooleanLiteralString ) ) ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getBooleanPropertyAccess().getUnorderedGroup_3(), 1) ) {
            	        throw new FailedPredicateException(input, "ruleBooleanProperty", "getUnorderedGroupHelper().canSelect(grammarAccess.getBooleanPropertyAccess().getUnorderedGroup_3(), 1)");
            	    }
            	    // InternalConceptLanguage.g:2512:112: ( ({...}? => (otherlv_6= 'default' ( (lv_defaultValue_7_0= ruleBooleanLiteralString ) ) ) ) )
            	    // InternalConceptLanguage.g:2513:6: ({...}? => (otherlv_6= 'default' ( (lv_defaultValue_7_0= ruleBooleanLiteralString ) ) ) )
            	    {

            	    						getUnorderedGroupHelper().select(grammarAccess.getBooleanPropertyAccess().getUnorderedGroup_3(), 1);
            	    					
            	    // InternalConceptLanguage.g:2516:9: ({...}? => (otherlv_6= 'default' ( (lv_defaultValue_7_0= ruleBooleanLiteralString ) ) ) )
            	    // InternalConceptLanguage.g:2516:10: {...}? => (otherlv_6= 'default' ( (lv_defaultValue_7_0= ruleBooleanLiteralString ) ) )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleBooleanProperty", "true");
            	    }
            	    // InternalConceptLanguage.g:2516:19: (otherlv_6= 'default' ( (lv_defaultValue_7_0= ruleBooleanLiteralString ) ) )
            	    // InternalConceptLanguage.g:2516:20: otherlv_6= 'default' ( (lv_defaultValue_7_0= ruleBooleanLiteralString ) )
            	    {
            	    otherlv_6=(Token)match(input,42,FOLLOW_40); 

            	    									newLeafNode(otherlv_6, grammarAccess.getBooleanPropertyAccess().getDefaultKeyword_3_1_0());
            	    								
            	    // InternalConceptLanguage.g:2520:9: ( (lv_defaultValue_7_0= ruleBooleanLiteralString ) )
            	    // InternalConceptLanguage.g:2521:10: (lv_defaultValue_7_0= ruleBooleanLiteralString )
            	    {
            	    // InternalConceptLanguage.g:2521:10: (lv_defaultValue_7_0= ruleBooleanLiteralString )
            	    // InternalConceptLanguage.g:2522:11: lv_defaultValue_7_0= ruleBooleanLiteralString
            	    {

            	    											newCompositeNode(grammarAccess.getBooleanPropertyAccess().getDefaultValueBooleanLiteralStringParserRuleCall_3_1_1_0());
            	    										
            	    pushFollow(FOLLOW_39);
            	    lv_defaultValue_7_0=ruleBooleanLiteralString();

            	    state._fsp--;


            	    											if (current==null) {
            	    												current = createModelElementForParent(grammarAccess.getBooleanPropertyRule());
            	    											}
            	    											set(
            	    												current,
            	    												"defaultValue",
            	    												lv_defaultValue_7_0,
            	    												"de.dlr.sc.virsat.model.concept.ConceptLanguage.BooleanLiteralString");
            	    											afterParserOrEnumRuleCall();
            	    										

            	    }


            	    }


            	    }


            	    }

            	     
            	    						getUnorderedGroupHelper().returnFromSelection(grammarAccess.getBooleanPropertyAccess().getUnorderedGroup_3());
            	    					

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop34;
                }
            } while (true);


            }


            }

             
            				  getUnorderedGroupHelper().leave(grammarAccess.getBooleanPropertyAccess().getUnorderedGroup_3());
            				

            }

            otherlv_8=(Token)match(input,21,FOLLOW_2); 

            			newLeafNode(otherlv_8, grammarAccess.getBooleanPropertyAccess().getSemicolonKeyword_4());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleBooleanProperty"


    // $ANTLR start "entryRuleEnumProperty"
    // InternalConceptLanguage.g:2560:1: entryRuleEnumProperty returns [EObject current=null] : iv_ruleEnumProperty= ruleEnumProperty EOF ;
    public final EObject entryRuleEnumProperty() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleEnumProperty = null;


        try {
            // InternalConceptLanguage.g:2560:53: (iv_ruleEnumProperty= ruleEnumProperty EOF )
            // InternalConceptLanguage.g:2561:2: iv_ruleEnumProperty= ruleEnumProperty EOF
            {
             newCompositeNode(grammarAccess.getEnumPropertyRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleEnumProperty=ruleEnumProperty();

            state._fsp--;

             current =iv_ruleEnumProperty; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleEnumProperty"


    // $ANTLR start "ruleEnumProperty"
    // InternalConceptLanguage.g:2567:1: ruleEnumProperty returns [EObject current=null] : (otherlv_0= 'EnumProperty' ( (lv_name_1_0= RULE_ID ) ) ( (lv_arrayModifier_2_0= ruleArrayModifier ) )? ( ( ( ( ({...}? => ( ({...}? => (otherlv_4= 'description' ( (lv_description_5_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_6= 'quantityKind' ( (lv_quantityKindName_7_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_8= 'unit' ( (lv_unitName_9_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_10= 'values' otherlv_11= '[' ( (lv_values_12_0= ruleEnumValueDefinition ) ) (otherlv_13= ',' ( (lv_values_14_0= ruleEnumValueDefinition ) ) )* otherlv_15= ']' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_16= 'default' ( (otherlv_17= RULE_ID ) ) ) ) ) ) )+ {...}?) ) ) otherlv_18= ';' ) ;
    public final EObject ruleEnumProperty() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token lv_name_1_0=null;
        Token otherlv_4=null;
        Token otherlv_6=null;
        Token otherlv_8=null;
        Token otherlv_10=null;
        Token otherlv_11=null;
        Token otherlv_13=null;
        Token otherlv_15=null;
        Token otherlv_16=null;
        Token otherlv_17=null;
        Token otherlv_18=null;
        EObject lv_arrayModifier_2_0 = null;

        AntlrDatatypeRuleToken lv_description_5_0 = null;

        AntlrDatatypeRuleToken lv_quantityKindName_7_0 = null;

        AntlrDatatypeRuleToken lv_unitName_9_0 = null;

        EObject lv_values_12_0 = null;

        EObject lv_values_14_0 = null;



        	enterRule();

        try {
            // InternalConceptLanguage.g:2573:2: ( (otherlv_0= 'EnumProperty' ( (lv_name_1_0= RULE_ID ) ) ( (lv_arrayModifier_2_0= ruleArrayModifier ) )? ( ( ( ( ({...}? => ( ({...}? => (otherlv_4= 'description' ( (lv_description_5_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_6= 'quantityKind' ( (lv_quantityKindName_7_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_8= 'unit' ( (lv_unitName_9_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_10= 'values' otherlv_11= '[' ( (lv_values_12_0= ruleEnumValueDefinition ) ) (otherlv_13= ',' ( (lv_values_14_0= ruleEnumValueDefinition ) ) )* otherlv_15= ']' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_16= 'default' ( (otherlv_17= RULE_ID ) ) ) ) ) ) )+ {...}?) ) ) otherlv_18= ';' ) )
            // InternalConceptLanguage.g:2574:2: (otherlv_0= 'EnumProperty' ( (lv_name_1_0= RULE_ID ) ) ( (lv_arrayModifier_2_0= ruleArrayModifier ) )? ( ( ( ( ({...}? => ( ({...}? => (otherlv_4= 'description' ( (lv_description_5_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_6= 'quantityKind' ( (lv_quantityKindName_7_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_8= 'unit' ( (lv_unitName_9_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_10= 'values' otherlv_11= '[' ( (lv_values_12_0= ruleEnumValueDefinition ) ) (otherlv_13= ',' ( (lv_values_14_0= ruleEnumValueDefinition ) ) )* otherlv_15= ']' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_16= 'default' ( (otherlv_17= RULE_ID ) ) ) ) ) ) )+ {...}?) ) ) otherlv_18= ';' )
            {
            // InternalConceptLanguage.g:2574:2: (otherlv_0= 'EnumProperty' ( (lv_name_1_0= RULE_ID ) ) ( (lv_arrayModifier_2_0= ruleArrayModifier ) )? ( ( ( ( ({...}? => ( ({...}? => (otherlv_4= 'description' ( (lv_description_5_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_6= 'quantityKind' ( (lv_quantityKindName_7_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_8= 'unit' ( (lv_unitName_9_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_10= 'values' otherlv_11= '[' ( (lv_values_12_0= ruleEnumValueDefinition ) ) (otherlv_13= ',' ( (lv_values_14_0= ruleEnumValueDefinition ) ) )* otherlv_15= ']' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_16= 'default' ( (otherlv_17= RULE_ID ) ) ) ) ) ) )+ {...}?) ) ) otherlv_18= ';' )
            // InternalConceptLanguage.g:2575:3: otherlv_0= 'EnumProperty' ( (lv_name_1_0= RULE_ID ) ) ( (lv_arrayModifier_2_0= ruleArrayModifier ) )? ( ( ( ( ({...}? => ( ({...}? => (otherlv_4= 'description' ( (lv_description_5_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_6= 'quantityKind' ( (lv_quantityKindName_7_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_8= 'unit' ( (lv_unitName_9_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_10= 'values' otherlv_11= '[' ( (lv_values_12_0= ruleEnumValueDefinition ) ) (otherlv_13= ',' ( (lv_values_14_0= ruleEnumValueDefinition ) ) )* otherlv_15= ']' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_16= 'default' ( (otherlv_17= RULE_ID ) ) ) ) ) ) )+ {...}?) ) ) otherlv_18= ';'
            {
            otherlv_0=(Token)match(input,46,FOLLOW_3); 

            			newLeafNode(otherlv_0, grammarAccess.getEnumPropertyAccess().getEnumPropertyKeyword_0());
            		
            // InternalConceptLanguage.g:2579:3: ( (lv_name_1_0= RULE_ID ) )
            // InternalConceptLanguage.g:2580:4: (lv_name_1_0= RULE_ID )
            {
            // InternalConceptLanguage.g:2580:4: (lv_name_1_0= RULE_ID )
            // InternalConceptLanguage.g:2581:5: lv_name_1_0= RULE_ID
            {
            lv_name_1_0=(Token)match(input,RULE_ID,FOLLOW_41); 

            					newLeafNode(lv_name_1_0, grammarAccess.getEnumPropertyAccess().getNameIDTerminalRuleCall_1_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getEnumPropertyRule());
            					}
            					setWithLastConsumed(
            						current,
            						"name",
            						lv_name_1_0,
            						"org.eclipse.xtext.common.Terminals.ID");
            				

            }


            }

            // InternalConceptLanguage.g:2597:3: ( (lv_arrayModifier_2_0= ruleArrayModifier ) )?
            int alt35=2;
            int LA35_0 = input.LA(1);

            if ( (LA35_0==24) ) {
                alt35=1;
            }
            switch (alt35) {
                case 1 :
                    // InternalConceptLanguage.g:2598:4: (lv_arrayModifier_2_0= ruleArrayModifier )
                    {
                    // InternalConceptLanguage.g:2598:4: (lv_arrayModifier_2_0= ruleArrayModifier )
                    // InternalConceptLanguage.g:2599:5: lv_arrayModifier_2_0= ruleArrayModifier
                    {

                    					newCompositeNode(grammarAccess.getEnumPropertyAccess().getArrayModifierArrayModifierParserRuleCall_2_0());
                    				
                    pushFollow(FOLLOW_42);
                    lv_arrayModifier_2_0=ruleArrayModifier();

                    state._fsp--;


                    					if (current==null) {
                    						current = createModelElementForParent(grammarAccess.getEnumPropertyRule());
                    					}
                    					set(
                    						current,
                    						"arrayModifier",
                    						lv_arrayModifier_2_0,
                    						"de.dlr.sc.virsat.model.concept.ConceptLanguage.ArrayModifier");
                    					afterParserOrEnumRuleCall();
                    				

                    }


                    }
                    break;

            }

            // InternalConceptLanguage.g:2616:3: ( ( ( ( ({...}? => ( ({...}? => (otherlv_4= 'description' ( (lv_description_5_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_6= 'quantityKind' ( (lv_quantityKindName_7_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_8= 'unit' ( (lv_unitName_9_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_10= 'values' otherlv_11= '[' ( (lv_values_12_0= ruleEnumValueDefinition ) ) (otherlv_13= ',' ( (lv_values_14_0= ruleEnumValueDefinition ) ) )* otherlv_15= ']' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_16= 'default' ( (otherlv_17= RULE_ID ) ) ) ) ) ) )+ {...}?) ) )
            // InternalConceptLanguage.g:2617:4: ( ( ( ({...}? => ( ({...}? => (otherlv_4= 'description' ( (lv_description_5_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_6= 'quantityKind' ( (lv_quantityKindName_7_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_8= 'unit' ( (lv_unitName_9_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_10= 'values' otherlv_11= '[' ( (lv_values_12_0= ruleEnumValueDefinition ) ) (otherlv_13= ',' ( (lv_values_14_0= ruleEnumValueDefinition ) ) )* otherlv_15= ']' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_16= 'default' ( (otherlv_17= RULE_ID ) ) ) ) ) ) )+ {...}?) )
            {
            // InternalConceptLanguage.g:2617:4: ( ( ( ({...}? => ( ({...}? => (otherlv_4= 'description' ( (lv_description_5_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_6= 'quantityKind' ( (lv_quantityKindName_7_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_8= 'unit' ( (lv_unitName_9_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_10= 'values' otherlv_11= '[' ( (lv_values_12_0= ruleEnumValueDefinition ) ) (otherlv_13= ',' ( (lv_values_14_0= ruleEnumValueDefinition ) ) )* otherlv_15= ']' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_16= 'default' ( (otherlv_17= RULE_ID ) ) ) ) ) ) )+ {...}?) )
            // InternalConceptLanguage.g:2618:5: ( ( ({...}? => ( ({...}? => (otherlv_4= 'description' ( (lv_description_5_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_6= 'quantityKind' ( (lv_quantityKindName_7_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_8= 'unit' ( (lv_unitName_9_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_10= 'values' otherlv_11= '[' ( (lv_values_12_0= ruleEnumValueDefinition ) ) (otherlv_13= ',' ( (lv_values_14_0= ruleEnumValueDefinition ) ) )* otherlv_15= ']' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_16= 'default' ( (otherlv_17= RULE_ID ) ) ) ) ) ) )+ {...}?)
            {
             
            				  getUnorderedGroupHelper().enter(grammarAccess.getEnumPropertyAccess().getUnorderedGroup_3());
            				
            // InternalConceptLanguage.g:2621:5: ( ( ({...}? => ( ({...}? => (otherlv_4= 'description' ( (lv_description_5_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_6= 'quantityKind' ( (lv_quantityKindName_7_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_8= 'unit' ( (lv_unitName_9_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_10= 'values' otherlv_11= '[' ( (lv_values_12_0= ruleEnumValueDefinition ) ) (otherlv_13= ',' ( (lv_values_14_0= ruleEnumValueDefinition ) ) )* otherlv_15= ']' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_16= 'default' ( (otherlv_17= RULE_ID ) ) ) ) ) ) )+ {...}?)
            // InternalConceptLanguage.g:2622:6: ( ({...}? => ( ({...}? => (otherlv_4= 'description' ( (lv_description_5_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_6= 'quantityKind' ( (lv_quantityKindName_7_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_8= 'unit' ( (lv_unitName_9_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_10= 'values' otherlv_11= '[' ( (lv_values_12_0= ruleEnumValueDefinition ) ) (otherlv_13= ',' ( (lv_values_14_0= ruleEnumValueDefinition ) ) )* otherlv_15= ']' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_16= 'default' ( (otherlv_17= RULE_ID ) ) ) ) ) ) )+ {...}?
            {
            // InternalConceptLanguage.g:2622:6: ( ({...}? => ( ({...}? => (otherlv_4= 'description' ( (lv_description_5_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_6= 'quantityKind' ( (lv_quantityKindName_7_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_8= 'unit' ( (lv_unitName_9_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_10= 'values' otherlv_11= '[' ( (lv_values_12_0= ruleEnumValueDefinition ) ) (otherlv_13= ',' ( (lv_values_14_0= ruleEnumValueDefinition ) ) )* otherlv_15= ']' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_16= 'default' ( (otherlv_17= RULE_ID ) ) ) ) ) ) )+
            int cnt37=0;
            loop37:
            do {
                int alt37=6;
                int LA37_0 = input.LA(1);

                if ( LA37_0 == 14 && getUnorderedGroupHelper().canSelect(grammarAccess.getEnumPropertyAccess().getUnorderedGroup_3(), 0) ) {
                    alt37=1;
                }
                else if ( LA37_0 == 39 && getUnorderedGroupHelper().canSelect(grammarAccess.getEnumPropertyAccess().getUnorderedGroup_3(), 1) ) {
                    alt37=2;
                }
                else if ( LA37_0 == 40 && getUnorderedGroupHelper().canSelect(grammarAccess.getEnumPropertyAccess().getUnorderedGroup_3(), 2) ) {
                    alt37=3;
                }
                else if ( LA37_0 == 47 && getUnorderedGroupHelper().canSelect(grammarAccess.getEnumPropertyAccess().getUnorderedGroup_3(), 3) ) {
                    alt37=4;
                }
                else if ( LA37_0 == 42 && getUnorderedGroupHelper().canSelect(grammarAccess.getEnumPropertyAccess().getUnorderedGroup_3(), 4) ) {
                    alt37=5;
                }


                switch (alt37) {
            	case 1 :
            	    // InternalConceptLanguage.g:2623:4: ({...}? => ( ({...}? => (otherlv_4= 'description' ( (lv_description_5_0= ruleEString ) ) ) ) ) )
            	    {
            	    // InternalConceptLanguage.g:2623:4: ({...}? => ( ({...}? => (otherlv_4= 'description' ( (lv_description_5_0= ruleEString ) ) ) ) ) )
            	    // InternalConceptLanguage.g:2624:5: {...}? => ( ({...}? => (otherlv_4= 'description' ( (lv_description_5_0= ruleEString ) ) ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getEnumPropertyAccess().getUnorderedGroup_3(), 0) ) {
            	        throw new FailedPredicateException(input, "ruleEnumProperty", "getUnorderedGroupHelper().canSelect(grammarAccess.getEnumPropertyAccess().getUnorderedGroup_3(), 0)");
            	    }
            	    // InternalConceptLanguage.g:2624:109: ( ({...}? => (otherlv_4= 'description' ( (lv_description_5_0= ruleEString ) ) ) ) )
            	    // InternalConceptLanguage.g:2625:6: ({...}? => (otherlv_4= 'description' ( (lv_description_5_0= ruleEString ) ) ) )
            	    {

            	    						getUnorderedGroupHelper().select(grammarAccess.getEnumPropertyAccess().getUnorderedGroup_3(), 0);
            	    					
            	    // InternalConceptLanguage.g:2628:9: ({...}? => (otherlv_4= 'description' ( (lv_description_5_0= ruleEString ) ) ) )
            	    // InternalConceptLanguage.g:2628:10: {...}? => (otherlv_4= 'description' ( (lv_description_5_0= ruleEString ) ) )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleEnumProperty", "true");
            	    }
            	    // InternalConceptLanguage.g:2628:19: (otherlv_4= 'description' ( (lv_description_5_0= ruleEString ) ) )
            	    // InternalConceptLanguage.g:2628:20: otherlv_4= 'description' ( (lv_description_5_0= ruleEString ) )
            	    {
            	    otherlv_4=(Token)match(input,14,FOLLOW_5); 

            	    									newLeafNode(otherlv_4, grammarAccess.getEnumPropertyAccess().getDescriptionKeyword_3_0_0());
            	    								
            	    // InternalConceptLanguage.g:2632:9: ( (lv_description_5_0= ruleEString ) )
            	    // InternalConceptLanguage.g:2633:10: (lv_description_5_0= ruleEString )
            	    {
            	    // InternalConceptLanguage.g:2633:10: (lv_description_5_0= ruleEString )
            	    // InternalConceptLanguage.g:2634:11: lv_description_5_0= ruleEString
            	    {

            	    											newCompositeNode(grammarAccess.getEnumPropertyAccess().getDescriptionEStringParserRuleCall_3_0_1_0());
            	    										
            	    pushFollow(FOLLOW_43);
            	    lv_description_5_0=ruleEString();

            	    state._fsp--;


            	    											if (current==null) {
            	    												current = createModelElementForParent(grammarAccess.getEnumPropertyRule());
            	    											}
            	    											set(
            	    												current,
            	    												"description",
            	    												lv_description_5_0,
            	    												"de.dlr.sc.virsat.model.concept.ConceptLanguage.EString");
            	    											afterParserOrEnumRuleCall();
            	    										

            	    }


            	    }


            	    }


            	    }

            	     
            	    						getUnorderedGroupHelper().returnFromSelection(grammarAccess.getEnumPropertyAccess().getUnorderedGroup_3());
            	    					

            	    }


            	    }


            	    }
            	    break;
            	case 2 :
            	    // InternalConceptLanguage.g:2657:4: ({...}? => ( ({...}? => (otherlv_6= 'quantityKind' ( (lv_quantityKindName_7_0= ruleEString ) ) ) ) ) )
            	    {
            	    // InternalConceptLanguage.g:2657:4: ({...}? => ( ({...}? => (otherlv_6= 'quantityKind' ( (lv_quantityKindName_7_0= ruleEString ) ) ) ) ) )
            	    // InternalConceptLanguage.g:2658:5: {...}? => ( ({...}? => (otherlv_6= 'quantityKind' ( (lv_quantityKindName_7_0= ruleEString ) ) ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getEnumPropertyAccess().getUnorderedGroup_3(), 1) ) {
            	        throw new FailedPredicateException(input, "ruleEnumProperty", "getUnorderedGroupHelper().canSelect(grammarAccess.getEnumPropertyAccess().getUnorderedGroup_3(), 1)");
            	    }
            	    // InternalConceptLanguage.g:2658:109: ( ({...}? => (otherlv_6= 'quantityKind' ( (lv_quantityKindName_7_0= ruleEString ) ) ) ) )
            	    // InternalConceptLanguage.g:2659:6: ({...}? => (otherlv_6= 'quantityKind' ( (lv_quantityKindName_7_0= ruleEString ) ) ) )
            	    {

            	    						getUnorderedGroupHelper().select(grammarAccess.getEnumPropertyAccess().getUnorderedGroup_3(), 1);
            	    					
            	    // InternalConceptLanguage.g:2662:9: ({...}? => (otherlv_6= 'quantityKind' ( (lv_quantityKindName_7_0= ruleEString ) ) ) )
            	    // InternalConceptLanguage.g:2662:10: {...}? => (otherlv_6= 'quantityKind' ( (lv_quantityKindName_7_0= ruleEString ) ) )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleEnumProperty", "true");
            	    }
            	    // InternalConceptLanguage.g:2662:19: (otherlv_6= 'quantityKind' ( (lv_quantityKindName_7_0= ruleEString ) ) )
            	    // InternalConceptLanguage.g:2662:20: otherlv_6= 'quantityKind' ( (lv_quantityKindName_7_0= ruleEString ) )
            	    {
            	    otherlv_6=(Token)match(input,39,FOLLOW_5); 

            	    									newLeafNode(otherlv_6, grammarAccess.getEnumPropertyAccess().getQuantityKindKeyword_3_1_0());
            	    								
            	    // InternalConceptLanguage.g:2666:9: ( (lv_quantityKindName_7_0= ruleEString ) )
            	    // InternalConceptLanguage.g:2667:10: (lv_quantityKindName_7_0= ruleEString )
            	    {
            	    // InternalConceptLanguage.g:2667:10: (lv_quantityKindName_7_0= ruleEString )
            	    // InternalConceptLanguage.g:2668:11: lv_quantityKindName_7_0= ruleEString
            	    {

            	    											newCompositeNode(grammarAccess.getEnumPropertyAccess().getQuantityKindNameEStringParserRuleCall_3_1_1_0());
            	    										
            	    pushFollow(FOLLOW_43);
            	    lv_quantityKindName_7_0=ruleEString();

            	    state._fsp--;


            	    											if (current==null) {
            	    												current = createModelElementForParent(grammarAccess.getEnumPropertyRule());
            	    											}
            	    											set(
            	    												current,
            	    												"quantityKindName",
            	    												lv_quantityKindName_7_0,
            	    												"de.dlr.sc.virsat.model.concept.ConceptLanguage.EString");
            	    											afterParserOrEnumRuleCall();
            	    										

            	    }


            	    }


            	    }


            	    }

            	     
            	    						getUnorderedGroupHelper().returnFromSelection(grammarAccess.getEnumPropertyAccess().getUnorderedGroup_3());
            	    					

            	    }


            	    }


            	    }
            	    break;
            	case 3 :
            	    // InternalConceptLanguage.g:2691:4: ({...}? => ( ({...}? => (otherlv_8= 'unit' ( (lv_unitName_9_0= ruleEString ) ) ) ) ) )
            	    {
            	    // InternalConceptLanguage.g:2691:4: ({...}? => ( ({...}? => (otherlv_8= 'unit' ( (lv_unitName_9_0= ruleEString ) ) ) ) ) )
            	    // InternalConceptLanguage.g:2692:5: {...}? => ( ({...}? => (otherlv_8= 'unit' ( (lv_unitName_9_0= ruleEString ) ) ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getEnumPropertyAccess().getUnorderedGroup_3(), 2) ) {
            	        throw new FailedPredicateException(input, "ruleEnumProperty", "getUnorderedGroupHelper().canSelect(grammarAccess.getEnumPropertyAccess().getUnorderedGroup_3(), 2)");
            	    }
            	    // InternalConceptLanguage.g:2692:109: ( ({...}? => (otherlv_8= 'unit' ( (lv_unitName_9_0= ruleEString ) ) ) ) )
            	    // InternalConceptLanguage.g:2693:6: ({...}? => (otherlv_8= 'unit' ( (lv_unitName_9_0= ruleEString ) ) ) )
            	    {

            	    						getUnorderedGroupHelper().select(grammarAccess.getEnumPropertyAccess().getUnorderedGroup_3(), 2);
            	    					
            	    // InternalConceptLanguage.g:2696:9: ({...}? => (otherlv_8= 'unit' ( (lv_unitName_9_0= ruleEString ) ) ) )
            	    // InternalConceptLanguage.g:2696:10: {...}? => (otherlv_8= 'unit' ( (lv_unitName_9_0= ruleEString ) ) )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleEnumProperty", "true");
            	    }
            	    // InternalConceptLanguage.g:2696:19: (otherlv_8= 'unit' ( (lv_unitName_9_0= ruleEString ) ) )
            	    // InternalConceptLanguage.g:2696:20: otherlv_8= 'unit' ( (lv_unitName_9_0= ruleEString ) )
            	    {
            	    otherlv_8=(Token)match(input,40,FOLLOW_5); 

            	    									newLeafNode(otherlv_8, grammarAccess.getEnumPropertyAccess().getUnitKeyword_3_2_0());
            	    								
            	    // InternalConceptLanguage.g:2700:9: ( (lv_unitName_9_0= ruleEString ) )
            	    // InternalConceptLanguage.g:2701:10: (lv_unitName_9_0= ruleEString )
            	    {
            	    // InternalConceptLanguage.g:2701:10: (lv_unitName_9_0= ruleEString )
            	    // InternalConceptLanguage.g:2702:11: lv_unitName_9_0= ruleEString
            	    {

            	    											newCompositeNode(grammarAccess.getEnumPropertyAccess().getUnitNameEStringParserRuleCall_3_2_1_0());
            	    										
            	    pushFollow(FOLLOW_43);
            	    lv_unitName_9_0=ruleEString();

            	    state._fsp--;


            	    											if (current==null) {
            	    												current = createModelElementForParent(grammarAccess.getEnumPropertyRule());
            	    											}
            	    											set(
            	    												current,
            	    												"unitName",
            	    												lv_unitName_9_0,
            	    												"de.dlr.sc.virsat.model.concept.ConceptLanguage.EString");
            	    											afterParserOrEnumRuleCall();
            	    										

            	    }


            	    }


            	    }


            	    }

            	     
            	    						getUnorderedGroupHelper().returnFromSelection(grammarAccess.getEnumPropertyAccess().getUnorderedGroup_3());
            	    					

            	    }


            	    }


            	    }
            	    break;
            	case 4 :
            	    // InternalConceptLanguage.g:2725:4: ({...}? => ( ({...}? => (otherlv_10= 'values' otherlv_11= '[' ( (lv_values_12_0= ruleEnumValueDefinition ) ) (otherlv_13= ',' ( (lv_values_14_0= ruleEnumValueDefinition ) ) )* otherlv_15= ']' ) ) ) )
            	    {
            	    // InternalConceptLanguage.g:2725:4: ({...}? => ( ({...}? => (otherlv_10= 'values' otherlv_11= '[' ( (lv_values_12_0= ruleEnumValueDefinition ) ) (otherlv_13= ',' ( (lv_values_14_0= ruleEnumValueDefinition ) ) )* otherlv_15= ']' ) ) ) )
            	    // InternalConceptLanguage.g:2726:5: {...}? => ( ({...}? => (otherlv_10= 'values' otherlv_11= '[' ( (lv_values_12_0= ruleEnumValueDefinition ) ) (otherlv_13= ',' ( (lv_values_14_0= ruleEnumValueDefinition ) ) )* otherlv_15= ']' ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getEnumPropertyAccess().getUnorderedGroup_3(), 3) ) {
            	        throw new FailedPredicateException(input, "ruleEnumProperty", "getUnorderedGroupHelper().canSelect(grammarAccess.getEnumPropertyAccess().getUnorderedGroup_3(), 3)");
            	    }
            	    // InternalConceptLanguage.g:2726:109: ( ({...}? => (otherlv_10= 'values' otherlv_11= '[' ( (lv_values_12_0= ruleEnumValueDefinition ) ) (otherlv_13= ',' ( (lv_values_14_0= ruleEnumValueDefinition ) ) )* otherlv_15= ']' ) ) )
            	    // InternalConceptLanguage.g:2727:6: ({...}? => (otherlv_10= 'values' otherlv_11= '[' ( (lv_values_12_0= ruleEnumValueDefinition ) ) (otherlv_13= ',' ( (lv_values_14_0= ruleEnumValueDefinition ) ) )* otherlv_15= ']' ) )
            	    {

            	    						getUnorderedGroupHelper().select(grammarAccess.getEnumPropertyAccess().getUnorderedGroup_3(), 3);
            	    					
            	    // InternalConceptLanguage.g:2730:9: ({...}? => (otherlv_10= 'values' otherlv_11= '[' ( (lv_values_12_0= ruleEnumValueDefinition ) ) (otherlv_13= ',' ( (lv_values_14_0= ruleEnumValueDefinition ) ) )* otherlv_15= ']' ) )
            	    // InternalConceptLanguage.g:2730:10: {...}? => (otherlv_10= 'values' otherlv_11= '[' ( (lv_values_12_0= ruleEnumValueDefinition ) ) (otherlv_13= ',' ( (lv_values_14_0= ruleEnumValueDefinition ) ) )* otherlv_15= ']' )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleEnumProperty", "true");
            	    }
            	    // InternalConceptLanguage.g:2730:19: (otherlv_10= 'values' otherlv_11= '[' ( (lv_values_12_0= ruleEnumValueDefinition ) ) (otherlv_13= ',' ( (lv_values_14_0= ruleEnumValueDefinition ) ) )* otherlv_15= ']' )
            	    // InternalConceptLanguage.g:2730:20: otherlv_10= 'values' otherlv_11= '[' ( (lv_values_12_0= ruleEnumValueDefinition ) ) (otherlv_13= ',' ( (lv_values_14_0= ruleEnumValueDefinition ) ) )* otherlv_15= ']'
            	    {
            	    otherlv_10=(Token)match(input,47,FOLLOW_44); 

            	    									newLeafNode(otherlv_10, grammarAccess.getEnumPropertyAccess().getValuesKeyword_3_3_0());
            	    								
            	    otherlv_11=(Token)match(input,24,FOLLOW_3); 

            	    									newLeafNode(otherlv_11, grammarAccess.getEnumPropertyAccess().getLeftSquareBracketKeyword_3_3_1());
            	    								
            	    // InternalConceptLanguage.g:2738:9: ( (lv_values_12_0= ruleEnumValueDefinition ) )
            	    // InternalConceptLanguage.g:2739:10: (lv_values_12_0= ruleEnumValueDefinition )
            	    {
            	    // InternalConceptLanguage.g:2739:10: (lv_values_12_0= ruleEnumValueDefinition )
            	    // InternalConceptLanguage.g:2740:11: lv_values_12_0= ruleEnumValueDefinition
            	    {

            	    											newCompositeNode(grammarAccess.getEnumPropertyAccess().getValuesEnumValueDefinitionParserRuleCall_3_3_2_0());
            	    										
            	    pushFollow(FOLLOW_16);
            	    lv_values_12_0=ruleEnumValueDefinition();

            	    state._fsp--;


            	    											if (current==null) {
            	    												current = createModelElementForParent(grammarAccess.getEnumPropertyRule());
            	    											}
            	    											add(
            	    												current,
            	    												"values",
            	    												lv_values_12_0,
            	    												"de.dlr.sc.virsat.model.concept.ConceptLanguage.EnumValueDefinition");
            	    											afterParserOrEnumRuleCall();
            	    										

            	    }


            	    }

            	    // InternalConceptLanguage.g:2757:9: (otherlv_13= ',' ( (lv_values_14_0= ruleEnumValueDefinition ) ) )*
            	    loop36:
            	    do {
            	        int alt36=2;
            	        int LA36_0 = input.LA(1);

            	        if ( (LA36_0==25) ) {
            	            alt36=1;
            	        }


            	        switch (alt36) {
            	    	case 1 :
            	    	    // InternalConceptLanguage.g:2758:10: otherlv_13= ',' ( (lv_values_14_0= ruleEnumValueDefinition ) )
            	    	    {
            	    	    otherlv_13=(Token)match(input,25,FOLLOW_3); 

            	    	    										newLeafNode(otherlv_13, grammarAccess.getEnumPropertyAccess().getCommaKeyword_3_3_3_0());
            	    	    									
            	    	    // InternalConceptLanguage.g:2762:10: ( (lv_values_14_0= ruleEnumValueDefinition ) )
            	    	    // InternalConceptLanguage.g:2763:11: (lv_values_14_0= ruleEnumValueDefinition )
            	    	    {
            	    	    // InternalConceptLanguage.g:2763:11: (lv_values_14_0= ruleEnumValueDefinition )
            	    	    // InternalConceptLanguage.g:2764:12: lv_values_14_0= ruleEnumValueDefinition
            	    	    {

            	    	    												newCompositeNode(grammarAccess.getEnumPropertyAccess().getValuesEnumValueDefinitionParserRuleCall_3_3_3_1_0());
            	    	    											
            	    	    pushFollow(FOLLOW_16);
            	    	    lv_values_14_0=ruleEnumValueDefinition();

            	    	    state._fsp--;


            	    	    												if (current==null) {
            	    	    													current = createModelElementForParent(grammarAccess.getEnumPropertyRule());
            	    	    												}
            	    	    												add(
            	    	    													current,
            	    	    													"values",
            	    	    													lv_values_14_0,
            	    	    													"de.dlr.sc.virsat.model.concept.ConceptLanguage.EnumValueDefinition");
            	    	    												afterParserOrEnumRuleCall();
            	    	    											

            	    	    }


            	    	    }


            	    	    }
            	    	    break;

            	    	default :
            	    	    break loop36;
            	        }
            	    } while (true);

            	    otherlv_15=(Token)match(input,26,FOLLOW_43); 

            	    									newLeafNode(otherlv_15, grammarAccess.getEnumPropertyAccess().getRightSquareBracketKeyword_3_3_4());
            	    								

            	    }


            	    }

            	     
            	    						getUnorderedGroupHelper().returnFromSelection(grammarAccess.getEnumPropertyAccess().getUnorderedGroup_3());
            	    					

            	    }


            	    }


            	    }
            	    break;
            	case 5 :
            	    // InternalConceptLanguage.g:2792:4: ({...}? => ( ({...}? => (otherlv_16= 'default' ( (otherlv_17= RULE_ID ) ) ) ) ) )
            	    {
            	    // InternalConceptLanguage.g:2792:4: ({...}? => ( ({...}? => (otherlv_16= 'default' ( (otherlv_17= RULE_ID ) ) ) ) ) )
            	    // InternalConceptLanguage.g:2793:5: {...}? => ( ({...}? => (otherlv_16= 'default' ( (otherlv_17= RULE_ID ) ) ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getEnumPropertyAccess().getUnorderedGroup_3(), 4) ) {
            	        throw new FailedPredicateException(input, "ruleEnumProperty", "getUnorderedGroupHelper().canSelect(grammarAccess.getEnumPropertyAccess().getUnorderedGroup_3(), 4)");
            	    }
            	    // InternalConceptLanguage.g:2793:109: ( ({...}? => (otherlv_16= 'default' ( (otherlv_17= RULE_ID ) ) ) ) )
            	    // InternalConceptLanguage.g:2794:6: ({...}? => (otherlv_16= 'default' ( (otherlv_17= RULE_ID ) ) ) )
            	    {

            	    						getUnorderedGroupHelper().select(grammarAccess.getEnumPropertyAccess().getUnorderedGroup_3(), 4);
            	    					
            	    // InternalConceptLanguage.g:2797:9: ({...}? => (otherlv_16= 'default' ( (otherlv_17= RULE_ID ) ) ) )
            	    // InternalConceptLanguage.g:2797:10: {...}? => (otherlv_16= 'default' ( (otherlv_17= RULE_ID ) ) )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleEnumProperty", "true");
            	    }
            	    // InternalConceptLanguage.g:2797:19: (otherlv_16= 'default' ( (otherlv_17= RULE_ID ) ) )
            	    // InternalConceptLanguage.g:2797:20: otherlv_16= 'default' ( (otherlv_17= RULE_ID ) )
            	    {
            	    otherlv_16=(Token)match(input,42,FOLLOW_3); 

            	    									newLeafNode(otherlv_16, grammarAccess.getEnumPropertyAccess().getDefaultKeyword_3_4_0());
            	    								
            	    // InternalConceptLanguage.g:2801:9: ( (otherlv_17= RULE_ID ) )
            	    // InternalConceptLanguage.g:2802:10: (otherlv_17= RULE_ID )
            	    {
            	    // InternalConceptLanguage.g:2802:10: (otherlv_17= RULE_ID )
            	    // InternalConceptLanguage.g:2803:11: otherlv_17= RULE_ID
            	    {

            	    											if (current==null) {
            	    												current = createModelElement(grammarAccess.getEnumPropertyRule());
            	    											}
            	    										
            	    otherlv_17=(Token)match(input,RULE_ID,FOLLOW_43); 

            	    											newLeafNode(otherlv_17, grammarAccess.getEnumPropertyAccess().getDefaultValueEnumValueDefinitionCrossReference_3_4_1_0());
            	    										

            	    }


            	    }


            	    }


            	    }

            	     
            	    						getUnorderedGroupHelper().returnFromSelection(grammarAccess.getEnumPropertyAccess().getUnorderedGroup_3());
            	    					

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    if ( cnt37 >= 1 ) break loop37;
                        EarlyExitException eee =
                            new EarlyExitException(37, input);
                        throw eee;
                }
                cnt37++;
            } while (true);

            if ( ! getUnorderedGroupHelper().canLeave(grammarAccess.getEnumPropertyAccess().getUnorderedGroup_3()) ) {
                throw new FailedPredicateException(input, "ruleEnumProperty", "getUnorderedGroupHelper().canLeave(grammarAccess.getEnumPropertyAccess().getUnorderedGroup_3())");
            }

            }


            }

             
            				  getUnorderedGroupHelper().leave(grammarAccess.getEnumPropertyAccess().getUnorderedGroup_3());
            				

            }

            otherlv_18=(Token)match(input,21,FOLLOW_2); 

            			newLeafNode(otherlv_18, grammarAccess.getEnumPropertyAccess().getSemicolonKeyword_4());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleEnumProperty"


    // $ANTLR start "entryRuleEnumValueDefinition"
    // InternalConceptLanguage.g:2836:1: entryRuleEnumValueDefinition returns [EObject current=null] : iv_ruleEnumValueDefinition= ruleEnumValueDefinition EOF ;
    public final EObject entryRuleEnumValueDefinition() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleEnumValueDefinition = null;


        try {
            // InternalConceptLanguage.g:2836:60: (iv_ruleEnumValueDefinition= ruleEnumValueDefinition EOF )
            // InternalConceptLanguage.g:2837:2: iv_ruleEnumValueDefinition= ruleEnumValueDefinition EOF
            {
             newCompositeNode(grammarAccess.getEnumValueDefinitionRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleEnumValueDefinition=ruleEnumValueDefinition();

            state._fsp--;

             current =iv_ruleEnumValueDefinition; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleEnumValueDefinition"


    // $ANTLR start "ruleEnumValueDefinition"
    // InternalConceptLanguage.g:2843:1: ruleEnumValueDefinition returns [EObject current=null] : ( ( (lv_name_0_0= RULE_ID ) ) otherlv_1= '=' ( ( (lv_value_2_1= ruleEString | lv_value_2_2= ruleFloatLiteralString ) ) ) ) ;
    public final EObject ruleEnumValueDefinition() throws RecognitionException {
        EObject current = null;

        Token lv_name_0_0=null;
        Token otherlv_1=null;
        AntlrDatatypeRuleToken lv_value_2_1 = null;

        AntlrDatatypeRuleToken lv_value_2_2 = null;



        	enterRule();

        try {
            // InternalConceptLanguage.g:2849:2: ( ( ( (lv_name_0_0= RULE_ID ) ) otherlv_1= '=' ( ( (lv_value_2_1= ruleEString | lv_value_2_2= ruleFloatLiteralString ) ) ) ) )
            // InternalConceptLanguage.g:2850:2: ( ( (lv_name_0_0= RULE_ID ) ) otherlv_1= '=' ( ( (lv_value_2_1= ruleEString | lv_value_2_2= ruleFloatLiteralString ) ) ) )
            {
            // InternalConceptLanguage.g:2850:2: ( ( (lv_name_0_0= RULE_ID ) ) otherlv_1= '=' ( ( (lv_value_2_1= ruleEString | lv_value_2_2= ruleFloatLiteralString ) ) ) )
            // InternalConceptLanguage.g:2851:3: ( (lv_name_0_0= RULE_ID ) ) otherlv_1= '=' ( ( (lv_value_2_1= ruleEString | lv_value_2_2= ruleFloatLiteralString ) ) )
            {
            // InternalConceptLanguage.g:2851:3: ( (lv_name_0_0= RULE_ID ) )
            // InternalConceptLanguage.g:2852:4: (lv_name_0_0= RULE_ID )
            {
            // InternalConceptLanguage.g:2852:4: (lv_name_0_0= RULE_ID )
            // InternalConceptLanguage.g:2853:5: lv_name_0_0= RULE_ID
            {
            lv_name_0_0=(Token)match(input,RULE_ID,FOLLOW_45); 

            					newLeafNode(lv_name_0_0, grammarAccess.getEnumValueDefinitionAccess().getNameIDTerminalRuleCall_0_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getEnumValueDefinitionRule());
            					}
            					setWithLastConsumed(
            						current,
            						"name",
            						lv_name_0_0,
            						"org.eclipse.xtext.common.Terminals.ID");
            				

            }


            }

            otherlv_1=(Token)match(input,48,FOLLOW_46); 

            			newLeafNode(otherlv_1, grammarAccess.getEnumValueDefinitionAccess().getEqualsSignKeyword_1());
            		
            // InternalConceptLanguage.g:2873:3: ( ( (lv_value_2_1= ruleEString | lv_value_2_2= ruleFloatLiteralString ) ) )
            // InternalConceptLanguage.g:2874:4: ( (lv_value_2_1= ruleEString | lv_value_2_2= ruleFloatLiteralString ) )
            {
            // InternalConceptLanguage.g:2874:4: ( (lv_value_2_1= ruleEString | lv_value_2_2= ruleFloatLiteralString ) )
            // InternalConceptLanguage.g:2875:5: (lv_value_2_1= ruleEString | lv_value_2_2= ruleFloatLiteralString )
            {
            // InternalConceptLanguage.g:2875:5: (lv_value_2_1= ruleEString | lv_value_2_2= ruleFloatLiteralString )
            int alt38=2;
            int LA38_0 = input.LA(1);

            if ( (LA38_0==RULE_STRING) ) {
                alt38=1;
            }
            else if ( (LA38_0==RULE_INT||LA38_0==58) ) {
                alt38=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 38, 0, input);

                throw nvae;
            }
            switch (alt38) {
                case 1 :
                    // InternalConceptLanguage.g:2876:6: lv_value_2_1= ruleEString
                    {

                    						newCompositeNode(grammarAccess.getEnumValueDefinitionAccess().getValueEStringParserRuleCall_2_0_0());
                    					
                    pushFollow(FOLLOW_2);
                    lv_value_2_1=ruleEString();

                    state._fsp--;


                    						if (current==null) {
                    							current = createModelElementForParent(grammarAccess.getEnumValueDefinitionRule());
                    						}
                    						set(
                    							current,
                    							"value",
                    							lv_value_2_1,
                    							"de.dlr.sc.virsat.model.concept.ConceptLanguage.EString");
                    						afterParserOrEnumRuleCall();
                    					

                    }
                    break;
                case 2 :
                    // InternalConceptLanguage.g:2892:6: lv_value_2_2= ruleFloatLiteralString
                    {

                    						newCompositeNode(grammarAccess.getEnumValueDefinitionAccess().getValueFloatLiteralStringParserRuleCall_2_0_1());
                    					
                    pushFollow(FOLLOW_2);
                    lv_value_2_2=ruleFloatLiteralString();

                    state._fsp--;


                    						if (current==null) {
                    							current = createModelElementForParent(grammarAccess.getEnumValueDefinitionRule());
                    						}
                    						set(
                    							current,
                    							"value",
                    							lv_value_2_2,
                    							"de.dlr.sc.virsat.model.concept.ConceptLanguage.FloatLiteralString");
                    						afterParserOrEnumRuleCall();
                    					

                    }
                    break;

            }


            }


            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleEnumValueDefinition"


    // $ANTLR start "entryRuleReferenceProperty"
    // InternalConceptLanguage.g:2914:1: entryRuleReferenceProperty returns [EObject current=null] : iv_ruleReferenceProperty= ruleReferenceProperty EOF ;
    public final EObject entryRuleReferenceProperty() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleReferenceProperty = null;


        try {
            // InternalConceptLanguage.g:2914:58: (iv_ruleReferenceProperty= ruleReferenceProperty EOF )
            // InternalConceptLanguage.g:2915:2: iv_ruleReferenceProperty= ruleReferenceProperty EOF
            {
             newCompositeNode(grammarAccess.getReferencePropertyRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleReferenceProperty=ruleReferenceProperty();

            state._fsp--;

             current =iv_ruleReferenceProperty; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleReferenceProperty"


    // $ANTLR start "ruleReferenceProperty"
    // InternalConceptLanguage.g:2921:1: ruleReferenceProperty returns [EObject current=null] : (otherlv_0= 'Reference' ( (lv_name_1_0= RULE_ID ) ) ( (lv_arrayModifier_2_0= ruleArrayModifier ) )? otherlv_3= 'of' otherlv_4= 'Type' ( ( ruleQualifiedName ) ) (otherlv_6= 'description' ( (lv_description_7_0= ruleEString ) ) )? otherlv_8= ';' ) ;
    public final EObject ruleReferenceProperty() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token lv_name_1_0=null;
        Token otherlv_3=null;
        Token otherlv_4=null;
        Token otherlv_6=null;
        Token otherlv_8=null;
        EObject lv_arrayModifier_2_0 = null;

        AntlrDatatypeRuleToken lv_description_7_0 = null;



        	enterRule();

        try {
            // InternalConceptLanguage.g:2927:2: ( (otherlv_0= 'Reference' ( (lv_name_1_0= RULE_ID ) ) ( (lv_arrayModifier_2_0= ruleArrayModifier ) )? otherlv_3= 'of' otherlv_4= 'Type' ( ( ruleQualifiedName ) ) (otherlv_6= 'description' ( (lv_description_7_0= ruleEString ) ) )? otherlv_8= ';' ) )
            // InternalConceptLanguage.g:2928:2: (otherlv_0= 'Reference' ( (lv_name_1_0= RULE_ID ) ) ( (lv_arrayModifier_2_0= ruleArrayModifier ) )? otherlv_3= 'of' otherlv_4= 'Type' ( ( ruleQualifiedName ) ) (otherlv_6= 'description' ( (lv_description_7_0= ruleEString ) ) )? otherlv_8= ';' )
            {
            // InternalConceptLanguage.g:2928:2: (otherlv_0= 'Reference' ( (lv_name_1_0= RULE_ID ) ) ( (lv_arrayModifier_2_0= ruleArrayModifier ) )? otherlv_3= 'of' otherlv_4= 'Type' ( ( ruleQualifiedName ) ) (otherlv_6= 'description' ( (lv_description_7_0= ruleEString ) ) )? otherlv_8= ';' )
            // InternalConceptLanguage.g:2929:3: otherlv_0= 'Reference' ( (lv_name_1_0= RULE_ID ) ) ( (lv_arrayModifier_2_0= ruleArrayModifier ) )? otherlv_3= 'of' otherlv_4= 'Type' ( ( ruleQualifiedName ) ) (otherlv_6= 'description' ( (lv_description_7_0= ruleEString ) ) )? otherlv_8= ';'
            {
            otherlv_0=(Token)match(input,49,FOLLOW_3); 

            			newLeafNode(otherlv_0, grammarAccess.getReferencePropertyAccess().getReferenceKeyword_0());
            		
            // InternalConceptLanguage.g:2933:3: ( (lv_name_1_0= RULE_ID ) )
            // InternalConceptLanguage.g:2934:4: (lv_name_1_0= RULE_ID )
            {
            // InternalConceptLanguage.g:2934:4: (lv_name_1_0= RULE_ID )
            // InternalConceptLanguage.g:2935:5: lv_name_1_0= RULE_ID
            {
            lv_name_1_0=(Token)match(input,RULE_ID,FOLLOW_30); 

            					newLeafNode(lv_name_1_0, grammarAccess.getReferencePropertyAccess().getNameIDTerminalRuleCall_1_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getReferencePropertyRule());
            					}
            					setWithLastConsumed(
            						current,
            						"name",
            						lv_name_1_0,
            						"org.eclipse.xtext.common.Terminals.ID");
            				

            }


            }

            // InternalConceptLanguage.g:2951:3: ( (lv_arrayModifier_2_0= ruleArrayModifier ) )?
            int alt39=2;
            int LA39_0 = input.LA(1);

            if ( (LA39_0==24) ) {
                alt39=1;
            }
            switch (alt39) {
                case 1 :
                    // InternalConceptLanguage.g:2952:4: (lv_arrayModifier_2_0= ruleArrayModifier )
                    {
                    // InternalConceptLanguage.g:2952:4: (lv_arrayModifier_2_0= ruleArrayModifier )
                    // InternalConceptLanguage.g:2953:5: lv_arrayModifier_2_0= ruleArrayModifier
                    {

                    					newCompositeNode(grammarAccess.getReferencePropertyAccess().getArrayModifierArrayModifierParserRuleCall_2_0());
                    				
                    pushFollow(FOLLOW_31);
                    lv_arrayModifier_2_0=ruleArrayModifier();

                    state._fsp--;


                    					if (current==null) {
                    						current = createModelElementForParent(grammarAccess.getReferencePropertyRule());
                    					}
                    					set(
                    						current,
                    						"arrayModifier",
                    						lv_arrayModifier_2_0,
                    						"de.dlr.sc.virsat.model.concept.ConceptLanguage.ArrayModifier");
                    					afterParserOrEnumRuleCall();
                    				

                    }


                    }
                    break;

            }

            otherlv_3=(Token)match(input,38,FOLLOW_21); 

            			newLeafNode(otherlv_3, grammarAccess.getReferencePropertyAccess().getOfKeyword_3());
            		
            otherlv_4=(Token)match(input,33,FOLLOW_3); 

            			newLeafNode(otherlv_4, grammarAccess.getReferencePropertyAccess().getTypeKeyword_4());
            		
            // InternalConceptLanguage.g:2978:3: ( ( ruleQualifiedName ) )
            // InternalConceptLanguage.g:2979:4: ( ruleQualifiedName )
            {
            // InternalConceptLanguage.g:2979:4: ( ruleQualifiedName )
            // InternalConceptLanguage.g:2980:5: ruleQualifiedName
            {

            					if (current==null) {
            						current = createModelElement(grammarAccess.getReferencePropertyRule());
            					}
            				

            					newCompositeNode(grammarAccess.getReferencePropertyAccess().getReferenceTypeATypeDefinitionCrossReference_5_0());
            				
            pushFollow(FOLLOW_47);
            ruleQualifiedName();

            state._fsp--;


            					afterParserOrEnumRuleCall();
            				

            }


            }

            // InternalConceptLanguage.g:2994:3: (otherlv_6= 'description' ( (lv_description_7_0= ruleEString ) ) )?
            int alt40=2;
            int LA40_0 = input.LA(1);

            if ( (LA40_0==14) ) {
                alt40=1;
            }
            switch (alt40) {
                case 1 :
                    // InternalConceptLanguage.g:2995:4: otherlv_6= 'description' ( (lv_description_7_0= ruleEString ) )
                    {
                    otherlv_6=(Token)match(input,14,FOLLOW_5); 

                    				newLeafNode(otherlv_6, grammarAccess.getReferencePropertyAccess().getDescriptionKeyword_6_0());
                    			
                    // InternalConceptLanguage.g:2999:4: ( (lv_description_7_0= ruleEString ) )
                    // InternalConceptLanguage.g:3000:5: (lv_description_7_0= ruleEString )
                    {
                    // InternalConceptLanguage.g:3000:5: (lv_description_7_0= ruleEString )
                    // InternalConceptLanguage.g:3001:6: lv_description_7_0= ruleEString
                    {

                    						newCompositeNode(grammarAccess.getReferencePropertyAccess().getDescriptionEStringParserRuleCall_6_1_0());
                    					
                    pushFollow(FOLLOW_13);
                    lv_description_7_0=ruleEString();

                    state._fsp--;


                    						if (current==null) {
                    							current = createModelElementForParent(grammarAccess.getReferencePropertyRule());
                    						}
                    						set(
                    							current,
                    							"description",
                    							lv_description_7_0,
                    							"de.dlr.sc.virsat.model.concept.ConceptLanguage.EString");
                    						afterParserOrEnumRuleCall();
                    					

                    }


                    }


                    }
                    break;

            }

            otherlv_8=(Token)match(input,21,FOLLOW_2); 

            			newLeafNode(otherlv_8, grammarAccess.getReferencePropertyAccess().getSemicolonKeyword_7());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleReferenceProperty"


    // $ANTLR start "entryRuleResourceProperty"
    // InternalConceptLanguage.g:3027:1: entryRuleResourceProperty returns [EObject current=null] : iv_ruleResourceProperty= ruleResourceProperty EOF ;
    public final EObject entryRuleResourceProperty() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleResourceProperty = null;


        try {
            // InternalConceptLanguage.g:3027:57: (iv_ruleResourceProperty= ruleResourceProperty EOF )
            // InternalConceptLanguage.g:3028:2: iv_ruleResourceProperty= ruleResourceProperty EOF
            {
             newCompositeNode(grammarAccess.getResourcePropertyRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleResourceProperty=ruleResourceProperty();

            state._fsp--;

             current =iv_ruleResourceProperty; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleResourceProperty"


    // $ANTLR start "ruleResourceProperty"
    // InternalConceptLanguage.g:3034:1: ruleResourceProperty returns [EObject current=null] : (otherlv_0= 'Resource' ( (lv_name_1_0= RULE_ID ) ) ( (lv_arrayModifier_2_0= ruleArrayModifier ) )? (otherlv_3= 'description' ( (lv_description_4_0= ruleEString ) ) )? otherlv_5= ';' ) ;
    public final EObject ruleResourceProperty() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token lv_name_1_0=null;
        Token otherlv_3=null;
        Token otherlv_5=null;
        EObject lv_arrayModifier_2_0 = null;

        AntlrDatatypeRuleToken lv_description_4_0 = null;



        	enterRule();

        try {
            // InternalConceptLanguage.g:3040:2: ( (otherlv_0= 'Resource' ( (lv_name_1_0= RULE_ID ) ) ( (lv_arrayModifier_2_0= ruleArrayModifier ) )? (otherlv_3= 'description' ( (lv_description_4_0= ruleEString ) ) )? otherlv_5= ';' ) )
            // InternalConceptLanguage.g:3041:2: (otherlv_0= 'Resource' ( (lv_name_1_0= RULE_ID ) ) ( (lv_arrayModifier_2_0= ruleArrayModifier ) )? (otherlv_3= 'description' ( (lv_description_4_0= ruleEString ) ) )? otherlv_5= ';' )
            {
            // InternalConceptLanguage.g:3041:2: (otherlv_0= 'Resource' ( (lv_name_1_0= RULE_ID ) ) ( (lv_arrayModifier_2_0= ruleArrayModifier ) )? (otherlv_3= 'description' ( (lv_description_4_0= ruleEString ) ) )? otherlv_5= ';' )
            // InternalConceptLanguage.g:3042:3: otherlv_0= 'Resource' ( (lv_name_1_0= RULE_ID ) ) ( (lv_arrayModifier_2_0= ruleArrayModifier ) )? (otherlv_3= 'description' ( (lv_description_4_0= ruleEString ) ) )? otherlv_5= ';'
            {
            otherlv_0=(Token)match(input,50,FOLLOW_3); 

            			newLeafNode(otherlv_0, grammarAccess.getResourcePropertyAccess().getResourceKeyword_0());
            		
            // InternalConceptLanguage.g:3046:3: ( (lv_name_1_0= RULE_ID ) )
            // InternalConceptLanguage.g:3047:4: (lv_name_1_0= RULE_ID )
            {
            // InternalConceptLanguage.g:3047:4: (lv_name_1_0= RULE_ID )
            // InternalConceptLanguage.g:3048:5: lv_name_1_0= RULE_ID
            {
            lv_name_1_0=(Token)match(input,RULE_ID,FOLLOW_48); 

            					newLeafNode(lv_name_1_0, grammarAccess.getResourcePropertyAccess().getNameIDTerminalRuleCall_1_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getResourcePropertyRule());
            					}
            					setWithLastConsumed(
            						current,
            						"name",
            						lv_name_1_0,
            						"org.eclipse.xtext.common.Terminals.ID");
            				

            }


            }

            // InternalConceptLanguage.g:3064:3: ( (lv_arrayModifier_2_0= ruleArrayModifier ) )?
            int alt41=2;
            int LA41_0 = input.LA(1);

            if ( (LA41_0==24) ) {
                alt41=1;
            }
            switch (alt41) {
                case 1 :
                    // InternalConceptLanguage.g:3065:4: (lv_arrayModifier_2_0= ruleArrayModifier )
                    {
                    // InternalConceptLanguage.g:3065:4: (lv_arrayModifier_2_0= ruleArrayModifier )
                    // InternalConceptLanguage.g:3066:5: lv_arrayModifier_2_0= ruleArrayModifier
                    {

                    					newCompositeNode(grammarAccess.getResourcePropertyAccess().getArrayModifierArrayModifierParserRuleCall_2_0());
                    				
                    pushFollow(FOLLOW_47);
                    lv_arrayModifier_2_0=ruleArrayModifier();

                    state._fsp--;


                    					if (current==null) {
                    						current = createModelElementForParent(grammarAccess.getResourcePropertyRule());
                    					}
                    					set(
                    						current,
                    						"arrayModifier",
                    						lv_arrayModifier_2_0,
                    						"de.dlr.sc.virsat.model.concept.ConceptLanguage.ArrayModifier");
                    					afterParserOrEnumRuleCall();
                    				

                    }


                    }
                    break;

            }

            // InternalConceptLanguage.g:3083:3: (otherlv_3= 'description' ( (lv_description_4_0= ruleEString ) ) )?
            int alt42=2;
            int LA42_0 = input.LA(1);

            if ( (LA42_0==14) ) {
                alt42=1;
            }
            switch (alt42) {
                case 1 :
                    // InternalConceptLanguage.g:3084:4: otherlv_3= 'description' ( (lv_description_4_0= ruleEString ) )
                    {
                    otherlv_3=(Token)match(input,14,FOLLOW_5); 

                    				newLeafNode(otherlv_3, grammarAccess.getResourcePropertyAccess().getDescriptionKeyword_3_0());
                    			
                    // InternalConceptLanguage.g:3088:4: ( (lv_description_4_0= ruleEString ) )
                    // InternalConceptLanguage.g:3089:5: (lv_description_4_0= ruleEString )
                    {
                    // InternalConceptLanguage.g:3089:5: (lv_description_4_0= ruleEString )
                    // InternalConceptLanguage.g:3090:6: lv_description_4_0= ruleEString
                    {

                    						newCompositeNode(grammarAccess.getResourcePropertyAccess().getDescriptionEStringParserRuleCall_3_1_0());
                    					
                    pushFollow(FOLLOW_13);
                    lv_description_4_0=ruleEString();

                    state._fsp--;


                    						if (current==null) {
                    							current = createModelElementForParent(grammarAccess.getResourcePropertyRule());
                    						}
                    						set(
                    							current,
                    							"description",
                    							lv_description_4_0,
                    							"de.dlr.sc.virsat.model.concept.ConceptLanguage.EString");
                    						afterParserOrEnumRuleCall();
                    					

                    }


                    }


                    }
                    break;

            }

            otherlv_5=(Token)match(input,21,FOLLOW_2); 

            			newLeafNode(otherlv_5, grammarAccess.getResourcePropertyAccess().getSemicolonKeyword_4());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleResourceProperty"


    // $ANTLR start "entryRuleEquationDefinition"
    // InternalConceptLanguage.g:3116:1: entryRuleEquationDefinition returns [EObject current=null] : iv_ruleEquationDefinition= ruleEquationDefinition EOF ;
    public final EObject entryRuleEquationDefinition() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleEquationDefinition = null;


        try {
            // InternalConceptLanguage.g:3116:59: (iv_ruleEquationDefinition= ruleEquationDefinition EOF )
            // InternalConceptLanguage.g:3117:2: iv_ruleEquationDefinition= ruleEquationDefinition EOF
            {
             newCompositeNode(grammarAccess.getEquationDefinitionRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleEquationDefinition=ruleEquationDefinition();

            state._fsp--;

             current =iv_ruleEquationDefinition; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleEquationDefinition"


    // $ANTLR start "ruleEquationDefinition"
    // InternalConceptLanguage.g:3123:1: ruleEquationDefinition returns [EObject current=null] : ( ( (lv_result_0_0= ruleEquationDefinitionResult ) ) otherlv_1= '=' ( (lv_expression_2_0= ruleAdditionAndSubtraction ) ) otherlv_3= ';' ) ;
    public final EObject ruleEquationDefinition() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_3=null;
        EObject lv_result_0_0 = null;

        EObject lv_expression_2_0 = null;



        	enterRule();

        try {
            // InternalConceptLanguage.g:3129:2: ( ( ( (lv_result_0_0= ruleEquationDefinitionResult ) ) otherlv_1= '=' ( (lv_expression_2_0= ruleAdditionAndSubtraction ) ) otherlv_3= ';' ) )
            // InternalConceptLanguage.g:3130:2: ( ( (lv_result_0_0= ruleEquationDefinitionResult ) ) otherlv_1= '=' ( (lv_expression_2_0= ruleAdditionAndSubtraction ) ) otherlv_3= ';' )
            {
            // InternalConceptLanguage.g:3130:2: ( ( (lv_result_0_0= ruleEquationDefinitionResult ) ) otherlv_1= '=' ( (lv_expression_2_0= ruleAdditionAndSubtraction ) ) otherlv_3= ';' )
            // InternalConceptLanguage.g:3131:3: ( (lv_result_0_0= ruleEquationDefinitionResult ) ) otherlv_1= '=' ( (lv_expression_2_0= ruleAdditionAndSubtraction ) ) otherlv_3= ';'
            {
            // InternalConceptLanguage.g:3131:3: ( (lv_result_0_0= ruleEquationDefinitionResult ) )
            // InternalConceptLanguage.g:3132:4: (lv_result_0_0= ruleEquationDefinitionResult )
            {
            // InternalConceptLanguage.g:3132:4: (lv_result_0_0= ruleEquationDefinitionResult )
            // InternalConceptLanguage.g:3133:5: lv_result_0_0= ruleEquationDefinitionResult
            {

            					newCompositeNode(grammarAccess.getEquationDefinitionAccess().getResultEquationDefinitionResultParserRuleCall_0_0());
            				
            pushFollow(FOLLOW_45);
            lv_result_0_0=ruleEquationDefinitionResult();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getEquationDefinitionRule());
            					}
            					set(
            						current,
            						"result",
            						lv_result_0_0,
            						"de.dlr.sc.virsat.model.concept.ConceptLanguage.EquationDefinitionResult");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            otherlv_1=(Token)match(input,48,FOLLOW_49); 

            			newLeafNode(otherlv_1, grammarAccess.getEquationDefinitionAccess().getEqualsSignKeyword_1());
            		
            // InternalConceptLanguage.g:3154:3: ( (lv_expression_2_0= ruleAdditionAndSubtraction ) )
            // InternalConceptLanguage.g:3155:4: (lv_expression_2_0= ruleAdditionAndSubtraction )
            {
            // InternalConceptLanguage.g:3155:4: (lv_expression_2_0= ruleAdditionAndSubtraction )
            // InternalConceptLanguage.g:3156:5: lv_expression_2_0= ruleAdditionAndSubtraction
            {

            					newCompositeNode(grammarAccess.getEquationDefinitionAccess().getExpressionAdditionAndSubtractionParserRuleCall_2_0());
            				
            pushFollow(FOLLOW_13);
            lv_expression_2_0=ruleAdditionAndSubtraction();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getEquationDefinitionRule());
            					}
            					set(
            						current,
            						"expression",
            						lv_expression_2_0,
            						"de.dlr.sc.virsat.model.concept.ConceptLanguage.AdditionAndSubtraction");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            otherlv_3=(Token)match(input,21,FOLLOW_2); 

            			newLeafNode(otherlv_3, grammarAccess.getEquationDefinitionAccess().getSemicolonKeyword_3());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleEquationDefinition"


    // $ANTLR start "entryRuleEquationDefinitionResult"
    // InternalConceptLanguage.g:3181:1: entryRuleEquationDefinitionResult returns [EObject current=null] : iv_ruleEquationDefinitionResult= ruleEquationDefinitionResult EOF ;
    public final EObject entryRuleEquationDefinitionResult() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleEquationDefinitionResult = null;


        try {
            // InternalConceptLanguage.g:3181:65: (iv_ruleEquationDefinitionResult= ruleEquationDefinitionResult EOF )
            // InternalConceptLanguage.g:3182:2: iv_ruleEquationDefinitionResult= ruleEquationDefinitionResult EOF
            {
             newCompositeNode(grammarAccess.getEquationDefinitionResultRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleEquationDefinitionResult=ruleEquationDefinitionResult();

            state._fsp--;

             current =iv_ruleEquationDefinitionResult; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleEquationDefinitionResult"


    // $ANTLR start "ruleEquationDefinitionResult"
    // InternalConceptLanguage.g:3188:1: ruleEquationDefinitionResult returns [EObject current=null] : (this_TypeDefinitionResult_0= ruleTypeDefinitionResult | this_EquationIntermediateResult_1= ruleEquationIntermediateResult ) ;
    public final EObject ruleEquationDefinitionResult() throws RecognitionException {
        EObject current = null;

        EObject this_TypeDefinitionResult_0 = null;

        EObject this_EquationIntermediateResult_1 = null;



        	enterRule();

        try {
            // InternalConceptLanguage.g:3194:2: ( (this_TypeDefinitionResult_0= ruleTypeDefinitionResult | this_EquationIntermediateResult_1= ruleEquationIntermediateResult ) )
            // InternalConceptLanguage.g:3195:2: (this_TypeDefinitionResult_0= ruleTypeDefinitionResult | this_EquationIntermediateResult_1= ruleEquationIntermediateResult )
            {
            // InternalConceptLanguage.g:3195:2: (this_TypeDefinitionResult_0= ruleTypeDefinitionResult | this_EquationIntermediateResult_1= ruleEquationIntermediateResult )
            int alt43=2;
            int LA43_0 = input.LA(1);

            if ( (LA43_0==51) ) {
                alt43=1;
            }
            else if ( (LA43_0==52) ) {
                alt43=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 43, 0, input);

                throw nvae;
            }
            switch (alt43) {
                case 1 :
                    // InternalConceptLanguage.g:3196:3: this_TypeDefinitionResult_0= ruleTypeDefinitionResult
                    {

                    			newCompositeNode(grammarAccess.getEquationDefinitionResultAccess().getTypeDefinitionResultParserRuleCall_0());
                    		
                    pushFollow(FOLLOW_2);
                    this_TypeDefinitionResult_0=ruleTypeDefinitionResult();

                    state._fsp--;


                    			current = this_TypeDefinitionResult_0;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 2 :
                    // InternalConceptLanguage.g:3205:3: this_EquationIntermediateResult_1= ruleEquationIntermediateResult
                    {

                    			newCompositeNode(grammarAccess.getEquationDefinitionResultAccess().getEquationIntermediateResultParserRuleCall_1());
                    		
                    pushFollow(FOLLOW_2);
                    this_EquationIntermediateResult_1=ruleEquationIntermediateResult();

                    state._fsp--;


                    			current = this_EquationIntermediateResult_1;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleEquationDefinitionResult"


    // $ANTLR start "entryRuleTypeDefinitionResult"
    // InternalConceptLanguage.g:3217:1: entryRuleTypeDefinitionResult returns [EObject current=null] : iv_ruleTypeDefinitionResult= ruleTypeDefinitionResult EOF ;
    public final EObject entryRuleTypeDefinitionResult() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleTypeDefinitionResult = null;


        try {
            // InternalConceptLanguage.g:3217:61: (iv_ruleTypeDefinitionResult= ruleTypeDefinitionResult EOF )
            // InternalConceptLanguage.g:3218:2: iv_ruleTypeDefinitionResult= ruleTypeDefinitionResult EOF
            {
             newCompositeNode(grammarAccess.getTypeDefinitionResultRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleTypeDefinitionResult=ruleTypeDefinitionResult();

            state._fsp--;

             current =iv_ruleTypeDefinitionResult; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleTypeDefinitionResult"


    // $ANTLR start "ruleTypeDefinitionResult"
    // InternalConceptLanguage.g:3224:1: ruleTypeDefinitionResult returns [EObject current=null] : ( () otherlv_1= 'Ref:' ( ( ruleQualifiedName ) ) ) ;
    public final EObject ruleTypeDefinitionResult() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;


        	enterRule();

        try {
            // InternalConceptLanguage.g:3230:2: ( ( () otherlv_1= 'Ref:' ( ( ruleQualifiedName ) ) ) )
            // InternalConceptLanguage.g:3231:2: ( () otherlv_1= 'Ref:' ( ( ruleQualifiedName ) ) )
            {
            // InternalConceptLanguage.g:3231:2: ( () otherlv_1= 'Ref:' ( ( ruleQualifiedName ) ) )
            // InternalConceptLanguage.g:3232:3: () otherlv_1= 'Ref:' ( ( ruleQualifiedName ) )
            {
            // InternalConceptLanguage.g:3232:3: ()
            // InternalConceptLanguage.g:3233:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getTypeDefinitionResultAccess().getTypeDefinitionResultAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,51,FOLLOW_3); 

            			newLeafNode(otherlv_1, grammarAccess.getTypeDefinitionResultAccess().getRefKeyword_1());
            		
            // InternalConceptLanguage.g:3243:3: ( ( ruleQualifiedName ) )
            // InternalConceptLanguage.g:3244:4: ( ruleQualifiedName )
            {
            // InternalConceptLanguage.g:3244:4: ( ruleQualifiedName )
            // InternalConceptLanguage.g:3245:5: ruleQualifiedName
            {

            					if (current==null) {
            						current = createModelElement(grammarAccess.getTypeDefinitionResultRule());
            					}
            				

            					newCompositeNode(grammarAccess.getTypeDefinitionResultAccess().getReferenceATypeDefinitionCrossReference_2_0());
            				
            pushFollow(FOLLOW_2);
            ruleQualifiedName();

            state._fsp--;


            					afterParserOrEnumRuleCall();
            				

            }


            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleTypeDefinitionResult"


    // $ANTLR start "entryRuleEquationIntermediateResult"
    // InternalConceptLanguage.g:3263:1: entryRuleEquationIntermediateResult returns [EObject current=null] : iv_ruleEquationIntermediateResult= ruleEquationIntermediateResult EOF ;
    public final EObject entryRuleEquationIntermediateResult() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleEquationIntermediateResult = null;


        try {
            // InternalConceptLanguage.g:3263:67: (iv_ruleEquationIntermediateResult= ruleEquationIntermediateResult EOF )
            // InternalConceptLanguage.g:3264:2: iv_ruleEquationIntermediateResult= ruleEquationIntermediateResult EOF
            {
             newCompositeNode(grammarAccess.getEquationIntermediateResultRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleEquationIntermediateResult=ruleEquationIntermediateResult();

            state._fsp--;

             current =iv_ruleEquationIntermediateResult; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleEquationIntermediateResult"


    // $ANTLR start "ruleEquationIntermediateResult"
    // InternalConceptLanguage.g:3270:1: ruleEquationIntermediateResult returns [EObject current=null] : ( () otherlv_1= 'Calc:' ( (lv_name_2_0= RULE_ID ) ) ) ;
    public final EObject ruleEquationIntermediateResult() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token lv_name_2_0=null;


        	enterRule();

        try {
            // InternalConceptLanguage.g:3276:2: ( ( () otherlv_1= 'Calc:' ( (lv_name_2_0= RULE_ID ) ) ) )
            // InternalConceptLanguage.g:3277:2: ( () otherlv_1= 'Calc:' ( (lv_name_2_0= RULE_ID ) ) )
            {
            // InternalConceptLanguage.g:3277:2: ( () otherlv_1= 'Calc:' ( (lv_name_2_0= RULE_ID ) ) )
            // InternalConceptLanguage.g:3278:3: () otherlv_1= 'Calc:' ( (lv_name_2_0= RULE_ID ) )
            {
            // InternalConceptLanguage.g:3278:3: ()
            // InternalConceptLanguage.g:3279:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getEquationIntermediateResultAccess().getEquationIntermediateResultAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,52,FOLLOW_3); 

            			newLeafNode(otherlv_1, grammarAccess.getEquationIntermediateResultAccess().getCalcKeyword_1());
            		
            // InternalConceptLanguage.g:3289:3: ( (lv_name_2_0= RULE_ID ) )
            // InternalConceptLanguage.g:3290:4: (lv_name_2_0= RULE_ID )
            {
            // InternalConceptLanguage.g:3290:4: (lv_name_2_0= RULE_ID )
            // InternalConceptLanguage.g:3291:5: lv_name_2_0= RULE_ID
            {
            lv_name_2_0=(Token)match(input,RULE_ID,FOLLOW_2); 

            					newLeafNode(lv_name_2_0, grammarAccess.getEquationIntermediateResultAccess().getNameIDTerminalRuleCall_2_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getEquationIntermediateResultRule());
            					}
            					setWithLastConsumed(
            						current,
            						"name",
            						lv_name_2_0,
            						"org.eclipse.xtext.common.Terminals.ID");
            				

            }


            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleEquationIntermediateResult"


    // $ANTLR start "entryRuleAdditionAndSubtraction"
    // InternalConceptLanguage.g:3311:1: entryRuleAdditionAndSubtraction returns [EObject current=null] : iv_ruleAdditionAndSubtraction= ruleAdditionAndSubtraction EOF ;
    public final EObject entryRuleAdditionAndSubtraction() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleAdditionAndSubtraction = null;


        try {
            // InternalConceptLanguage.g:3311:63: (iv_ruleAdditionAndSubtraction= ruleAdditionAndSubtraction EOF )
            // InternalConceptLanguage.g:3312:2: iv_ruleAdditionAndSubtraction= ruleAdditionAndSubtraction EOF
            {
             newCompositeNode(grammarAccess.getAdditionAndSubtractionRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleAdditionAndSubtraction=ruleAdditionAndSubtraction();

            state._fsp--;

             current =iv_ruleAdditionAndSubtraction; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleAdditionAndSubtraction"


    // $ANTLR start "ruleAdditionAndSubtraction"
    // InternalConceptLanguage.g:3318:1: ruleAdditionAndSubtraction returns [EObject current=null] : (this_MultiplicationAndDivision_0= ruleMultiplicationAndDivision ( () ( ( (lv_operator_2_1= ruleOperatorPlus | lv_operator_2_2= ruleOperatorMinus ) ) ) ( (lv_right_3_0= ruleMultiplicationAndDivision ) ) )* ) ;
    public final EObject ruleAdditionAndSubtraction() throws RecognitionException {
        EObject current = null;

        EObject this_MultiplicationAndDivision_0 = null;

        Enumerator lv_operator_2_1 = null;

        Enumerator lv_operator_2_2 = null;

        EObject lv_right_3_0 = null;



        	enterRule();

        try {
            // InternalConceptLanguage.g:3324:2: ( (this_MultiplicationAndDivision_0= ruleMultiplicationAndDivision ( () ( ( (lv_operator_2_1= ruleOperatorPlus | lv_operator_2_2= ruleOperatorMinus ) ) ) ( (lv_right_3_0= ruleMultiplicationAndDivision ) ) )* ) )
            // InternalConceptLanguage.g:3325:2: (this_MultiplicationAndDivision_0= ruleMultiplicationAndDivision ( () ( ( (lv_operator_2_1= ruleOperatorPlus | lv_operator_2_2= ruleOperatorMinus ) ) ) ( (lv_right_3_0= ruleMultiplicationAndDivision ) ) )* )
            {
            // InternalConceptLanguage.g:3325:2: (this_MultiplicationAndDivision_0= ruleMultiplicationAndDivision ( () ( ( (lv_operator_2_1= ruleOperatorPlus | lv_operator_2_2= ruleOperatorMinus ) ) ) ( (lv_right_3_0= ruleMultiplicationAndDivision ) ) )* )
            // InternalConceptLanguage.g:3326:3: this_MultiplicationAndDivision_0= ruleMultiplicationAndDivision ( () ( ( (lv_operator_2_1= ruleOperatorPlus | lv_operator_2_2= ruleOperatorMinus ) ) ) ( (lv_right_3_0= ruleMultiplicationAndDivision ) ) )*
            {

            			newCompositeNode(grammarAccess.getAdditionAndSubtractionAccess().getMultiplicationAndDivisionParserRuleCall_0());
            		
            pushFollow(FOLLOW_50);
            this_MultiplicationAndDivision_0=ruleMultiplicationAndDivision();

            state._fsp--;


            			current = this_MultiplicationAndDivision_0;
            			afterParserOrEnumRuleCall();
            		
            // InternalConceptLanguage.g:3334:3: ( () ( ( (lv_operator_2_1= ruleOperatorPlus | lv_operator_2_2= ruleOperatorMinus ) ) ) ( (lv_right_3_0= ruleMultiplicationAndDivision ) ) )*
            loop45:
            do {
                int alt45=2;
                int LA45_0 = input.LA(1);

                if ( (LA45_0==58||LA45_0==62) ) {
                    alt45=1;
                }


                switch (alt45) {
            	case 1 :
            	    // InternalConceptLanguage.g:3335:4: () ( ( (lv_operator_2_1= ruleOperatorPlus | lv_operator_2_2= ruleOperatorMinus ) ) ) ( (lv_right_3_0= ruleMultiplicationAndDivision ) )
            	    {
            	    // InternalConceptLanguage.g:3335:4: ()
            	    // InternalConceptLanguage.g:3336:5: 
            	    {

            	    					current = forceCreateModelElementAndSet(
            	    						grammarAccess.getAdditionAndSubtractionAccess().getAdditionAndSubtractionLeftAction_1_0(),
            	    						current);
            	    				

            	    }

            	    // InternalConceptLanguage.g:3342:4: ( ( (lv_operator_2_1= ruleOperatorPlus | lv_operator_2_2= ruleOperatorMinus ) ) )
            	    // InternalConceptLanguage.g:3343:5: ( (lv_operator_2_1= ruleOperatorPlus | lv_operator_2_2= ruleOperatorMinus ) )
            	    {
            	    // InternalConceptLanguage.g:3343:5: ( (lv_operator_2_1= ruleOperatorPlus | lv_operator_2_2= ruleOperatorMinus ) )
            	    // InternalConceptLanguage.g:3344:6: (lv_operator_2_1= ruleOperatorPlus | lv_operator_2_2= ruleOperatorMinus )
            	    {
            	    // InternalConceptLanguage.g:3344:6: (lv_operator_2_1= ruleOperatorPlus | lv_operator_2_2= ruleOperatorMinus )
            	    int alt44=2;
            	    int LA44_0 = input.LA(1);

            	    if ( (LA44_0==62) ) {
            	        alt44=1;
            	    }
            	    else if ( (LA44_0==58) ) {
            	        alt44=2;
            	    }
            	    else {
            	        NoViableAltException nvae =
            	            new NoViableAltException("", 44, 0, input);

            	        throw nvae;
            	    }
            	    switch (alt44) {
            	        case 1 :
            	            // InternalConceptLanguage.g:3345:7: lv_operator_2_1= ruleOperatorPlus
            	            {

            	            							newCompositeNode(grammarAccess.getAdditionAndSubtractionAccess().getOperatorOperatorPlusEnumRuleCall_1_1_0_0());
            	            						
            	            pushFollow(FOLLOW_49);
            	            lv_operator_2_1=ruleOperatorPlus();

            	            state._fsp--;


            	            							if (current==null) {
            	            								current = createModelElementForParent(grammarAccess.getAdditionAndSubtractionRule());
            	            							}
            	            							set(
            	            								current,
            	            								"operator",
            	            								lv_operator_2_1,
            	            								"de.dlr.sc.virsat.model.concept.ConceptLanguage.OperatorPlus");
            	            							afterParserOrEnumRuleCall();
            	            						

            	            }
            	            break;
            	        case 2 :
            	            // InternalConceptLanguage.g:3361:7: lv_operator_2_2= ruleOperatorMinus
            	            {

            	            							newCompositeNode(grammarAccess.getAdditionAndSubtractionAccess().getOperatorOperatorMinusEnumRuleCall_1_1_0_1());
            	            						
            	            pushFollow(FOLLOW_49);
            	            lv_operator_2_2=ruleOperatorMinus();

            	            state._fsp--;


            	            							if (current==null) {
            	            								current = createModelElementForParent(grammarAccess.getAdditionAndSubtractionRule());
            	            							}
            	            							set(
            	            								current,
            	            								"operator",
            	            								lv_operator_2_2,
            	            								"de.dlr.sc.virsat.model.concept.ConceptLanguage.OperatorMinus");
            	            							afterParserOrEnumRuleCall();
            	            						

            	            }
            	            break;

            	    }


            	    }


            	    }

            	    // InternalConceptLanguage.g:3379:4: ( (lv_right_3_0= ruleMultiplicationAndDivision ) )
            	    // InternalConceptLanguage.g:3380:5: (lv_right_3_0= ruleMultiplicationAndDivision )
            	    {
            	    // InternalConceptLanguage.g:3380:5: (lv_right_3_0= ruleMultiplicationAndDivision )
            	    // InternalConceptLanguage.g:3381:6: lv_right_3_0= ruleMultiplicationAndDivision
            	    {

            	    						newCompositeNode(grammarAccess.getAdditionAndSubtractionAccess().getRightMultiplicationAndDivisionParserRuleCall_1_2_0());
            	    					
            	    pushFollow(FOLLOW_50);
            	    lv_right_3_0=ruleMultiplicationAndDivision();

            	    state._fsp--;


            	    						if (current==null) {
            	    							current = createModelElementForParent(grammarAccess.getAdditionAndSubtractionRule());
            	    						}
            	    						set(
            	    							current,
            	    							"right",
            	    							lv_right_3_0,
            	    							"de.dlr.sc.virsat.model.concept.ConceptLanguage.MultiplicationAndDivision");
            	    						afterParserOrEnumRuleCall();
            	    					

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop45;
                }
            } while (true);


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleAdditionAndSubtraction"


    // $ANTLR start "entryRuleMultiplicationAndDivision"
    // InternalConceptLanguage.g:3403:1: entryRuleMultiplicationAndDivision returns [EObject current=null] : iv_ruleMultiplicationAndDivision= ruleMultiplicationAndDivision EOF ;
    public final EObject entryRuleMultiplicationAndDivision() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleMultiplicationAndDivision = null;


        try {
            // InternalConceptLanguage.g:3403:66: (iv_ruleMultiplicationAndDivision= ruleMultiplicationAndDivision EOF )
            // InternalConceptLanguage.g:3404:2: iv_ruleMultiplicationAndDivision= ruleMultiplicationAndDivision EOF
            {
             newCompositeNode(grammarAccess.getMultiplicationAndDivisionRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleMultiplicationAndDivision=ruleMultiplicationAndDivision();

            state._fsp--;

             current =iv_ruleMultiplicationAndDivision; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleMultiplicationAndDivision"


    // $ANTLR start "ruleMultiplicationAndDivision"
    // InternalConceptLanguage.g:3410:1: ruleMultiplicationAndDivision returns [EObject current=null] : (this_PowerFunction_0= rulePowerFunction ( () ( ( (lv_operator_2_1= ruleOperatorMultiply | lv_operator_2_2= ruleOperatorDivide ) ) ) ( (lv_right_3_0= rulePowerFunction ) ) )* ) ;
    public final EObject ruleMultiplicationAndDivision() throws RecognitionException {
        EObject current = null;

        EObject this_PowerFunction_0 = null;

        Enumerator lv_operator_2_1 = null;

        Enumerator lv_operator_2_2 = null;

        EObject lv_right_3_0 = null;



        	enterRule();

        try {
            // InternalConceptLanguage.g:3416:2: ( (this_PowerFunction_0= rulePowerFunction ( () ( ( (lv_operator_2_1= ruleOperatorMultiply | lv_operator_2_2= ruleOperatorDivide ) ) ) ( (lv_right_3_0= rulePowerFunction ) ) )* ) )
            // InternalConceptLanguage.g:3417:2: (this_PowerFunction_0= rulePowerFunction ( () ( ( (lv_operator_2_1= ruleOperatorMultiply | lv_operator_2_2= ruleOperatorDivide ) ) ) ( (lv_right_3_0= rulePowerFunction ) ) )* )
            {
            // InternalConceptLanguage.g:3417:2: (this_PowerFunction_0= rulePowerFunction ( () ( ( (lv_operator_2_1= ruleOperatorMultiply | lv_operator_2_2= ruleOperatorDivide ) ) ) ( (lv_right_3_0= rulePowerFunction ) ) )* )
            // InternalConceptLanguage.g:3418:3: this_PowerFunction_0= rulePowerFunction ( () ( ( (lv_operator_2_1= ruleOperatorMultiply | lv_operator_2_2= ruleOperatorDivide ) ) ) ( (lv_right_3_0= rulePowerFunction ) ) )*
            {

            			newCompositeNode(grammarAccess.getMultiplicationAndDivisionAccess().getPowerFunctionParserRuleCall_0());
            		
            pushFollow(FOLLOW_51);
            this_PowerFunction_0=rulePowerFunction();

            state._fsp--;


            			current = this_PowerFunction_0;
            			afterParserOrEnumRuleCall();
            		
            // InternalConceptLanguage.g:3426:3: ( () ( ( (lv_operator_2_1= ruleOperatorMultiply | lv_operator_2_2= ruleOperatorDivide ) ) ) ( (lv_right_3_0= rulePowerFunction ) ) )*
            loop47:
            do {
                int alt47=2;
                int LA47_0 = input.LA(1);

                if ( ((LA47_0>=63 && LA47_0<=64)) ) {
                    alt47=1;
                }


                switch (alt47) {
            	case 1 :
            	    // InternalConceptLanguage.g:3427:4: () ( ( (lv_operator_2_1= ruleOperatorMultiply | lv_operator_2_2= ruleOperatorDivide ) ) ) ( (lv_right_3_0= rulePowerFunction ) )
            	    {
            	    // InternalConceptLanguage.g:3427:4: ()
            	    // InternalConceptLanguage.g:3428:5: 
            	    {

            	    					current = forceCreateModelElementAndSet(
            	    						grammarAccess.getMultiplicationAndDivisionAccess().getMultiplicationAndDivisionLeftAction_1_0(),
            	    						current);
            	    				

            	    }

            	    // InternalConceptLanguage.g:3434:4: ( ( (lv_operator_2_1= ruleOperatorMultiply | lv_operator_2_2= ruleOperatorDivide ) ) )
            	    // InternalConceptLanguage.g:3435:5: ( (lv_operator_2_1= ruleOperatorMultiply | lv_operator_2_2= ruleOperatorDivide ) )
            	    {
            	    // InternalConceptLanguage.g:3435:5: ( (lv_operator_2_1= ruleOperatorMultiply | lv_operator_2_2= ruleOperatorDivide ) )
            	    // InternalConceptLanguage.g:3436:6: (lv_operator_2_1= ruleOperatorMultiply | lv_operator_2_2= ruleOperatorDivide )
            	    {
            	    // InternalConceptLanguage.g:3436:6: (lv_operator_2_1= ruleOperatorMultiply | lv_operator_2_2= ruleOperatorDivide )
            	    int alt46=2;
            	    int LA46_0 = input.LA(1);

            	    if ( (LA46_0==63) ) {
            	        alt46=1;
            	    }
            	    else if ( (LA46_0==64) ) {
            	        alt46=2;
            	    }
            	    else {
            	        NoViableAltException nvae =
            	            new NoViableAltException("", 46, 0, input);

            	        throw nvae;
            	    }
            	    switch (alt46) {
            	        case 1 :
            	            // InternalConceptLanguage.g:3437:7: lv_operator_2_1= ruleOperatorMultiply
            	            {

            	            							newCompositeNode(grammarAccess.getMultiplicationAndDivisionAccess().getOperatorOperatorMultiplyEnumRuleCall_1_1_0_0());
            	            						
            	            pushFollow(FOLLOW_49);
            	            lv_operator_2_1=ruleOperatorMultiply();

            	            state._fsp--;


            	            							if (current==null) {
            	            								current = createModelElementForParent(grammarAccess.getMultiplicationAndDivisionRule());
            	            							}
            	            							set(
            	            								current,
            	            								"operator",
            	            								lv_operator_2_1,
            	            								"de.dlr.sc.virsat.model.concept.ConceptLanguage.OperatorMultiply");
            	            							afterParserOrEnumRuleCall();
            	            						

            	            }
            	            break;
            	        case 2 :
            	            // InternalConceptLanguage.g:3453:7: lv_operator_2_2= ruleOperatorDivide
            	            {

            	            							newCompositeNode(grammarAccess.getMultiplicationAndDivisionAccess().getOperatorOperatorDivideEnumRuleCall_1_1_0_1());
            	            						
            	            pushFollow(FOLLOW_49);
            	            lv_operator_2_2=ruleOperatorDivide();

            	            state._fsp--;


            	            							if (current==null) {
            	            								current = createModelElementForParent(grammarAccess.getMultiplicationAndDivisionRule());
            	            							}
            	            							set(
            	            								current,
            	            								"operator",
            	            								lv_operator_2_2,
            	            								"de.dlr.sc.virsat.model.concept.ConceptLanguage.OperatorDivide");
            	            							afterParserOrEnumRuleCall();
            	            						

            	            }
            	            break;

            	    }


            	    }


            	    }

            	    // InternalConceptLanguage.g:3471:4: ( (lv_right_3_0= rulePowerFunction ) )
            	    // InternalConceptLanguage.g:3472:5: (lv_right_3_0= rulePowerFunction )
            	    {
            	    // InternalConceptLanguage.g:3472:5: (lv_right_3_0= rulePowerFunction )
            	    // InternalConceptLanguage.g:3473:6: lv_right_3_0= rulePowerFunction
            	    {

            	    						newCompositeNode(grammarAccess.getMultiplicationAndDivisionAccess().getRightPowerFunctionParserRuleCall_1_2_0());
            	    					
            	    pushFollow(FOLLOW_51);
            	    lv_right_3_0=rulePowerFunction();

            	    state._fsp--;


            	    						if (current==null) {
            	    							current = createModelElementForParent(grammarAccess.getMultiplicationAndDivisionRule());
            	    						}
            	    						set(
            	    							current,
            	    							"right",
            	    							lv_right_3_0,
            	    							"de.dlr.sc.virsat.model.concept.ConceptLanguage.PowerFunction");
            	    						afterParserOrEnumRuleCall();
            	    					

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop47;
                }
            } while (true);


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleMultiplicationAndDivision"


    // $ANTLR start "entryRulePowerFunction"
    // InternalConceptLanguage.g:3495:1: entryRulePowerFunction returns [EObject current=null] : iv_rulePowerFunction= rulePowerFunction EOF ;
    public final EObject entryRulePowerFunction() throws RecognitionException {
        EObject current = null;

        EObject iv_rulePowerFunction = null;


        try {
            // InternalConceptLanguage.g:3495:54: (iv_rulePowerFunction= rulePowerFunction EOF )
            // InternalConceptLanguage.g:3496:2: iv_rulePowerFunction= rulePowerFunction EOF
            {
             newCompositeNode(grammarAccess.getPowerFunctionRule()); 
            pushFollow(FOLLOW_1);
            iv_rulePowerFunction=rulePowerFunction();

            state._fsp--;

             current =iv_rulePowerFunction; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRulePowerFunction"


    // $ANTLR start "rulePowerFunction"
    // InternalConceptLanguage.g:3502:1: rulePowerFunction returns [EObject current=null] : (this_AExpression_0= ruleAExpression ( () ( (lv_operator_2_0= ruleOperatorPower ) ) ( (lv_right_3_0= ruleAExpression ) ) )* ) ;
    public final EObject rulePowerFunction() throws RecognitionException {
        EObject current = null;

        EObject this_AExpression_0 = null;

        Enumerator lv_operator_2_0 = null;

        EObject lv_right_3_0 = null;



        	enterRule();

        try {
            // InternalConceptLanguage.g:3508:2: ( (this_AExpression_0= ruleAExpression ( () ( (lv_operator_2_0= ruleOperatorPower ) ) ( (lv_right_3_0= ruleAExpression ) ) )* ) )
            // InternalConceptLanguage.g:3509:2: (this_AExpression_0= ruleAExpression ( () ( (lv_operator_2_0= ruleOperatorPower ) ) ( (lv_right_3_0= ruleAExpression ) ) )* )
            {
            // InternalConceptLanguage.g:3509:2: (this_AExpression_0= ruleAExpression ( () ( (lv_operator_2_0= ruleOperatorPower ) ) ( (lv_right_3_0= ruleAExpression ) ) )* )
            // InternalConceptLanguage.g:3510:3: this_AExpression_0= ruleAExpression ( () ( (lv_operator_2_0= ruleOperatorPower ) ) ( (lv_right_3_0= ruleAExpression ) ) )*
            {

            			newCompositeNode(grammarAccess.getPowerFunctionAccess().getAExpressionParserRuleCall_0());
            		
            pushFollow(FOLLOW_52);
            this_AExpression_0=ruleAExpression();

            state._fsp--;


            			current = this_AExpression_0;
            			afterParserOrEnumRuleCall();
            		
            // InternalConceptLanguage.g:3518:3: ( () ( (lv_operator_2_0= ruleOperatorPower ) ) ( (lv_right_3_0= ruleAExpression ) ) )*
            loop48:
            do {
                int alt48=2;
                int LA48_0 = input.LA(1);

                if ( (LA48_0==65) ) {
                    alt48=1;
                }


                switch (alt48) {
            	case 1 :
            	    // InternalConceptLanguage.g:3519:4: () ( (lv_operator_2_0= ruleOperatorPower ) ) ( (lv_right_3_0= ruleAExpression ) )
            	    {
            	    // InternalConceptLanguage.g:3519:4: ()
            	    // InternalConceptLanguage.g:3520:5: 
            	    {

            	    					current = forceCreateModelElementAndSet(
            	    						grammarAccess.getPowerFunctionAccess().getPowerFunctionLeftAction_1_0(),
            	    						current);
            	    				

            	    }

            	    // InternalConceptLanguage.g:3526:4: ( (lv_operator_2_0= ruleOperatorPower ) )
            	    // InternalConceptLanguage.g:3527:5: (lv_operator_2_0= ruleOperatorPower )
            	    {
            	    // InternalConceptLanguage.g:3527:5: (lv_operator_2_0= ruleOperatorPower )
            	    // InternalConceptLanguage.g:3528:6: lv_operator_2_0= ruleOperatorPower
            	    {

            	    						newCompositeNode(grammarAccess.getPowerFunctionAccess().getOperatorOperatorPowerEnumRuleCall_1_1_0());
            	    					
            	    pushFollow(FOLLOW_49);
            	    lv_operator_2_0=ruleOperatorPower();

            	    state._fsp--;


            	    						if (current==null) {
            	    							current = createModelElementForParent(grammarAccess.getPowerFunctionRule());
            	    						}
            	    						set(
            	    							current,
            	    							"operator",
            	    							lv_operator_2_0,
            	    							"de.dlr.sc.virsat.model.concept.ConceptLanguage.OperatorPower");
            	    						afterParserOrEnumRuleCall();
            	    					

            	    }


            	    }

            	    // InternalConceptLanguage.g:3545:4: ( (lv_right_3_0= ruleAExpression ) )
            	    // InternalConceptLanguage.g:3546:5: (lv_right_3_0= ruleAExpression )
            	    {
            	    // InternalConceptLanguage.g:3546:5: (lv_right_3_0= ruleAExpression )
            	    // InternalConceptLanguage.g:3547:6: lv_right_3_0= ruleAExpression
            	    {

            	    						newCompositeNode(grammarAccess.getPowerFunctionAccess().getRightAExpressionParserRuleCall_1_2_0());
            	    					
            	    pushFollow(FOLLOW_52);
            	    lv_right_3_0=ruleAExpression();

            	    state._fsp--;


            	    						if (current==null) {
            	    							current = createModelElementForParent(grammarAccess.getPowerFunctionRule());
            	    						}
            	    						set(
            	    							current,
            	    							"right",
            	    							lv_right_3_0,
            	    							"de.dlr.sc.virsat.model.concept.ConceptLanguage.AExpression");
            	    						afterParserOrEnumRuleCall();
            	    					

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop48;
                }
            } while (true);


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "rulePowerFunction"


    // $ANTLR start "entryRuleParenthesis"
    // InternalConceptLanguage.g:3569:1: entryRuleParenthesis returns [EObject current=null] : iv_ruleParenthesis= ruleParenthesis EOF ;
    public final EObject entryRuleParenthesis() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleParenthesis = null;


        try {
            // InternalConceptLanguage.g:3569:52: (iv_ruleParenthesis= ruleParenthesis EOF )
            // InternalConceptLanguage.g:3570:2: iv_ruleParenthesis= ruleParenthesis EOF
            {
             newCompositeNode(grammarAccess.getParenthesisRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleParenthesis=ruleParenthesis();

            state._fsp--;

             current =iv_ruleParenthesis; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleParenthesis"


    // $ANTLR start "ruleParenthesis"
    // InternalConceptLanguage.g:3576:1: ruleParenthesis returns [EObject current=null] : ( () ( (lv_operator_1_0= ruleOperatorMinus ) )? otherlv_2= '(' ( (lv_right_3_0= ruleAdditionAndSubtraction ) ) otherlv_4= ')' ) ;
    public final EObject ruleParenthesis() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        Token otherlv_4=null;
        Enumerator lv_operator_1_0 = null;

        EObject lv_right_3_0 = null;



        	enterRule();

        try {
            // InternalConceptLanguage.g:3582:2: ( ( () ( (lv_operator_1_0= ruleOperatorMinus ) )? otherlv_2= '(' ( (lv_right_3_0= ruleAdditionAndSubtraction ) ) otherlv_4= ')' ) )
            // InternalConceptLanguage.g:3583:2: ( () ( (lv_operator_1_0= ruleOperatorMinus ) )? otherlv_2= '(' ( (lv_right_3_0= ruleAdditionAndSubtraction ) ) otherlv_4= ')' )
            {
            // InternalConceptLanguage.g:3583:2: ( () ( (lv_operator_1_0= ruleOperatorMinus ) )? otherlv_2= '(' ( (lv_right_3_0= ruleAdditionAndSubtraction ) ) otherlv_4= ')' )
            // InternalConceptLanguage.g:3584:3: () ( (lv_operator_1_0= ruleOperatorMinus ) )? otherlv_2= '(' ( (lv_right_3_0= ruleAdditionAndSubtraction ) ) otherlv_4= ')'
            {
            // InternalConceptLanguage.g:3584:3: ()
            // InternalConceptLanguage.g:3585:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getParenthesisAccess().getParenthesisAction_0(),
            					current);
            			

            }

            // InternalConceptLanguage.g:3591:3: ( (lv_operator_1_0= ruleOperatorMinus ) )?
            int alt49=2;
            int LA49_0 = input.LA(1);

            if ( (LA49_0==58) ) {
                alt49=1;
            }
            switch (alt49) {
                case 1 :
                    // InternalConceptLanguage.g:3592:4: (lv_operator_1_0= ruleOperatorMinus )
                    {
                    // InternalConceptLanguage.g:3592:4: (lv_operator_1_0= ruleOperatorMinus )
                    // InternalConceptLanguage.g:3593:5: lv_operator_1_0= ruleOperatorMinus
                    {

                    					newCompositeNode(grammarAccess.getParenthesisAccess().getOperatorOperatorMinusEnumRuleCall_1_0());
                    				
                    pushFollow(FOLLOW_53);
                    lv_operator_1_0=ruleOperatorMinus();

                    state._fsp--;


                    					if (current==null) {
                    						current = createModelElementForParent(grammarAccess.getParenthesisRule());
                    					}
                    					set(
                    						current,
                    						"operator",
                    						lv_operator_1_0,
                    						"de.dlr.sc.virsat.model.concept.ConceptLanguage.OperatorMinus");
                    					afterParserOrEnumRuleCall();
                    				

                    }


                    }
                    break;

            }

            otherlv_2=(Token)match(input,53,FOLLOW_49); 

            			newLeafNode(otherlv_2, grammarAccess.getParenthesisAccess().getLeftParenthesisKeyword_2());
            		
            // InternalConceptLanguage.g:3614:3: ( (lv_right_3_0= ruleAdditionAndSubtraction ) )
            // InternalConceptLanguage.g:3615:4: (lv_right_3_0= ruleAdditionAndSubtraction )
            {
            // InternalConceptLanguage.g:3615:4: (lv_right_3_0= ruleAdditionAndSubtraction )
            // InternalConceptLanguage.g:3616:5: lv_right_3_0= ruleAdditionAndSubtraction
            {

            					newCompositeNode(grammarAccess.getParenthesisAccess().getRightAdditionAndSubtractionParserRuleCall_3_0());
            				
            pushFollow(FOLLOW_54);
            lv_right_3_0=ruleAdditionAndSubtraction();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getParenthesisRule());
            					}
            					set(
            						current,
            						"right",
            						lv_right_3_0,
            						"de.dlr.sc.virsat.model.concept.ConceptLanguage.AdditionAndSubtraction");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            otherlv_4=(Token)match(input,54,FOLLOW_2); 

            			newLeafNode(otherlv_4, grammarAccess.getParenthesisAccess().getRightParenthesisKeyword_4());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleParenthesis"


    // $ANTLR start "entryRuleAExpression"
    // InternalConceptLanguage.g:3641:1: entryRuleAExpression returns [EObject current=null] : iv_ruleAExpression= ruleAExpression EOF ;
    public final EObject entryRuleAExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleAExpression = null;


        try {
            // InternalConceptLanguage.g:3641:52: (iv_ruleAExpression= ruleAExpression EOF )
            // InternalConceptLanguage.g:3642:2: iv_ruleAExpression= ruleAExpression EOF
            {
             newCompositeNode(grammarAccess.getAExpressionRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleAExpression=ruleAExpression();

            state._fsp--;

             current =iv_ruleAExpression; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleAExpression"


    // $ANTLR start "ruleAExpression"
    // InternalConceptLanguage.g:3648:1: ruleAExpression returns [EObject current=null] : (this_ALiteral_0= ruleALiteral | this_ReferencedDefinitionInput_1= ruleReferencedDefinitionInput | this_Parenthesis_2= ruleParenthesis | this_Function_3= ruleFunction | this_AAdvancedFunction_4= ruleAAdvancedFunction ) ;
    public final EObject ruleAExpression() throws RecognitionException {
        EObject current = null;

        EObject this_ALiteral_0 = null;

        EObject this_ReferencedDefinitionInput_1 = null;

        EObject this_Parenthesis_2 = null;

        EObject this_Function_3 = null;

        EObject this_AAdvancedFunction_4 = null;



        	enterRule();

        try {
            // InternalConceptLanguage.g:3654:2: ( (this_ALiteral_0= ruleALiteral | this_ReferencedDefinitionInput_1= ruleReferencedDefinitionInput | this_Parenthesis_2= ruleParenthesis | this_Function_3= ruleFunction | this_AAdvancedFunction_4= ruleAAdvancedFunction ) )
            // InternalConceptLanguage.g:3655:2: (this_ALiteral_0= ruleALiteral | this_ReferencedDefinitionInput_1= ruleReferencedDefinitionInput | this_Parenthesis_2= ruleParenthesis | this_Function_3= ruleFunction | this_AAdvancedFunction_4= ruleAAdvancedFunction )
            {
            // InternalConceptLanguage.g:3655:2: (this_ALiteral_0= ruleALiteral | this_ReferencedDefinitionInput_1= ruleReferencedDefinitionInput | this_Parenthesis_2= ruleParenthesis | this_Function_3= ruleFunction | this_AAdvancedFunction_4= ruleAAdvancedFunction )
            int alt50=5;
            switch ( input.LA(1) ) {
            case 58:
                {
                int LA50_1 = input.LA(2);

                if ( (LA50_1==RULE_INT) ) {
                    alt50=1;
                }
                else if ( (LA50_1==53) ) {
                    alt50=3;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 50, 1, input);

                    throw nvae;
                }
                }
                break;
            case RULE_INT:
            case 55:
            case 56:
                {
                alt50=1;
                }
                break;
            case RULE_ID:
                {
                int LA50_3 = input.LA(2);

                if ( (LA50_3==16||LA50_3==53) ) {
                    alt50=5;
                }
                else if ( (LA50_3==EOF||LA50_3==21||LA50_3==25||LA50_3==54||(LA50_3>=57 && LA50_3<=58)||(LA50_3>=62 && LA50_3<=65)) ) {
                    alt50=2;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 50, 3, input);

                    throw nvae;
                }
                }
                break;
            case 53:
                {
                alt50=3;
                }
                break;
            case 66:
            case 67:
            case 68:
            case 69:
            case 70:
            case 71:
            case 72:
            case 73:
            case 74:
            case 75:
            case 76:
                {
                alt50=4;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 50, 0, input);

                throw nvae;
            }

            switch (alt50) {
                case 1 :
                    // InternalConceptLanguage.g:3656:3: this_ALiteral_0= ruleALiteral
                    {

                    			newCompositeNode(grammarAccess.getAExpressionAccess().getALiteralParserRuleCall_0());
                    		
                    pushFollow(FOLLOW_2);
                    this_ALiteral_0=ruleALiteral();

                    state._fsp--;


                    			current = this_ALiteral_0;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 2 :
                    // InternalConceptLanguage.g:3665:3: this_ReferencedDefinitionInput_1= ruleReferencedDefinitionInput
                    {

                    			newCompositeNode(grammarAccess.getAExpressionAccess().getReferencedDefinitionInputParserRuleCall_1());
                    		
                    pushFollow(FOLLOW_2);
                    this_ReferencedDefinitionInput_1=ruleReferencedDefinitionInput();

                    state._fsp--;


                    			current = this_ReferencedDefinitionInput_1;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 3 :
                    // InternalConceptLanguage.g:3674:3: this_Parenthesis_2= ruleParenthesis
                    {

                    			newCompositeNode(grammarAccess.getAExpressionAccess().getParenthesisParserRuleCall_2());
                    		
                    pushFollow(FOLLOW_2);
                    this_Parenthesis_2=ruleParenthesis();

                    state._fsp--;


                    			current = this_Parenthesis_2;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 4 :
                    // InternalConceptLanguage.g:3683:3: this_Function_3= ruleFunction
                    {

                    			newCompositeNode(grammarAccess.getAExpressionAccess().getFunctionParserRuleCall_3());
                    		
                    pushFollow(FOLLOW_2);
                    this_Function_3=ruleFunction();

                    state._fsp--;


                    			current = this_Function_3;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 5 :
                    // InternalConceptLanguage.g:3692:3: this_AAdvancedFunction_4= ruleAAdvancedFunction
                    {

                    			newCompositeNode(grammarAccess.getAExpressionAccess().getAAdvancedFunctionParserRuleCall_4());
                    		
                    pushFollow(FOLLOW_2);
                    this_AAdvancedFunction_4=ruleAAdvancedFunction();

                    state._fsp--;


                    			current = this_AAdvancedFunction_4;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleAExpression"


    // $ANTLR start "entryRuleReferencedDefinitionInput"
    // InternalConceptLanguage.g:3704:1: entryRuleReferencedDefinitionInput returns [EObject current=null] : iv_ruleReferencedDefinitionInput= ruleReferencedDefinitionInput EOF ;
    public final EObject entryRuleReferencedDefinitionInput() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleReferencedDefinitionInput = null;


        try {
            // InternalConceptLanguage.g:3704:66: (iv_ruleReferencedDefinitionInput= ruleReferencedDefinitionInput EOF )
            // InternalConceptLanguage.g:3705:2: iv_ruleReferencedDefinitionInput= ruleReferencedDefinitionInput EOF
            {
             newCompositeNode(grammarAccess.getReferencedDefinitionInputRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleReferencedDefinitionInput=ruleReferencedDefinitionInput();

            state._fsp--;

             current =iv_ruleReferencedDefinitionInput; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleReferencedDefinitionInput"


    // $ANTLR start "ruleReferencedDefinitionInput"
    // InternalConceptLanguage.g:3711:1: ruleReferencedDefinitionInput returns [EObject current=null] : ( ( ruleQualifiedName ) ) ;
    public final EObject ruleReferencedDefinitionInput() throws RecognitionException {
        EObject current = null;


        	enterRule();

        try {
            // InternalConceptLanguage.g:3717:2: ( ( ( ruleQualifiedName ) ) )
            // InternalConceptLanguage.g:3718:2: ( ( ruleQualifiedName ) )
            {
            // InternalConceptLanguage.g:3718:2: ( ( ruleQualifiedName ) )
            // InternalConceptLanguage.g:3719:3: ( ruleQualifiedName )
            {
            // InternalConceptLanguage.g:3719:3: ( ruleQualifiedName )
            // InternalConceptLanguage.g:3720:4: ruleQualifiedName
            {

            				if (current==null) {
            					current = createModelElement(grammarAccess.getReferencedDefinitionInputRule());
            				}
            			

            				newCompositeNode(grammarAccess.getReferencedDefinitionInputAccess().getReferenceIEquationDefinitionInputCrossReference_0());
            			
            pushFollow(FOLLOW_2);
            ruleQualifiedName();

            state._fsp--;


            				afterParserOrEnumRuleCall();
            			

            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleReferencedDefinitionInput"


    // $ANTLR start "entryRuleALiteral"
    // InternalConceptLanguage.g:3737:1: entryRuleALiteral returns [EObject current=null] : iv_ruleALiteral= ruleALiteral EOF ;
    public final EObject entryRuleALiteral() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleALiteral = null;


        try {
            // InternalConceptLanguage.g:3737:49: (iv_ruleALiteral= ruleALiteral EOF )
            // InternalConceptLanguage.g:3738:2: iv_ruleALiteral= ruleALiteral EOF
            {
             newCompositeNode(grammarAccess.getALiteralRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleALiteral=ruleALiteral();

            state._fsp--;

             current =iv_ruleALiteral; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleALiteral"


    // $ANTLR start "ruleALiteral"
    // InternalConceptLanguage.g:3744:1: ruleALiteral returns [EObject current=null] : (this_NumberLiteral_0= ruleNumberLiteral | this_ValueE_1= ruleValueE | this_ValuePi_2= ruleValuePi ) ;
    public final EObject ruleALiteral() throws RecognitionException {
        EObject current = null;

        EObject this_NumberLiteral_0 = null;

        EObject this_ValueE_1 = null;

        EObject this_ValuePi_2 = null;



        	enterRule();

        try {
            // InternalConceptLanguage.g:3750:2: ( (this_NumberLiteral_0= ruleNumberLiteral | this_ValueE_1= ruleValueE | this_ValuePi_2= ruleValuePi ) )
            // InternalConceptLanguage.g:3751:2: (this_NumberLiteral_0= ruleNumberLiteral | this_ValueE_1= ruleValueE | this_ValuePi_2= ruleValuePi )
            {
            // InternalConceptLanguage.g:3751:2: (this_NumberLiteral_0= ruleNumberLiteral | this_ValueE_1= ruleValueE | this_ValuePi_2= ruleValuePi )
            int alt51=3;
            switch ( input.LA(1) ) {
            case RULE_INT:
            case 58:
                {
                alt51=1;
                }
                break;
            case 56:
                {
                alt51=2;
                }
                break;
            case 55:
                {
                alt51=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 51, 0, input);

                throw nvae;
            }

            switch (alt51) {
                case 1 :
                    // InternalConceptLanguage.g:3752:3: this_NumberLiteral_0= ruleNumberLiteral
                    {

                    			newCompositeNode(grammarAccess.getALiteralAccess().getNumberLiteralParserRuleCall_0());
                    		
                    pushFollow(FOLLOW_2);
                    this_NumberLiteral_0=ruleNumberLiteral();

                    state._fsp--;


                    			current = this_NumberLiteral_0;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 2 :
                    // InternalConceptLanguage.g:3761:3: this_ValueE_1= ruleValueE
                    {

                    			newCompositeNode(grammarAccess.getALiteralAccess().getValueEParserRuleCall_1());
                    		
                    pushFollow(FOLLOW_2);
                    this_ValueE_1=ruleValueE();

                    state._fsp--;


                    			current = this_ValueE_1;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 3 :
                    // InternalConceptLanguage.g:3770:3: this_ValuePi_2= ruleValuePi
                    {

                    			newCompositeNode(grammarAccess.getALiteralAccess().getValuePiParserRuleCall_2());
                    		
                    pushFollow(FOLLOW_2);
                    this_ValuePi_2=ruleValuePi();

                    state._fsp--;


                    			current = this_ValuePi_2;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleALiteral"


    // $ANTLR start "entryRuleFunction"
    // InternalConceptLanguage.g:3782:1: entryRuleFunction returns [EObject current=null] : iv_ruleFunction= ruleFunction EOF ;
    public final EObject entryRuleFunction() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleFunction = null;


        try {
            // InternalConceptLanguage.g:3782:49: (iv_ruleFunction= ruleFunction EOF )
            // InternalConceptLanguage.g:3783:2: iv_ruleFunction= ruleFunction EOF
            {
             newCompositeNode(grammarAccess.getFunctionRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleFunction=ruleFunction();

            state._fsp--;

             current =iv_ruleFunction; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleFunction"


    // $ANTLR start "ruleFunction"
    // InternalConceptLanguage.g:3789:1: ruleFunction returns [EObject current=null] : ( () ( ( (lv_operator_1_1= ruleOperatorCos | lv_operator_1_2= ruleOperatorSin | lv_operator_1_3= ruleOperatorTan | lv_operator_1_4= ruleOperatorAtan | lv_operator_1_5= ruleOperatorAcos | lv_operator_1_6= ruleOperatorAsin | lv_operator_1_7= ruleOperatorSqrt | lv_operator_1_8= ruleOperatorLog | lv_operator_1_9= ruleOperatorLn | lv_operator_1_10= ruleOperatorLd | lv_operator_1_11= ruleOperatorExp ) ) ) otherlv_2= '(' ( (lv_right_3_0= ruleAdditionAndSubtraction ) ) otherlv_4= ')' ) ;
    public final EObject ruleFunction() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        Token otherlv_4=null;
        Enumerator lv_operator_1_1 = null;

        Enumerator lv_operator_1_2 = null;

        Enumerator lv_operator_1_3 = null;

        Enumerator lv_operator_1_4 = null;

        Enumerator lv_operator_1_5 = null;

        Enumerator lv_operator_1_6 = null;

        Enumerator lv_operator_1_7 = null;

        Enumerator lv_operator_1_8 = null;

        Enumerator lv_operator_1_9 = null;

        Enumerator lv_operator_1_10 = null;

        Enumerator lv_operator_1_11 = null;

        EObject lv_right_3_0 = null;



        	enterRule();

        try {
            // InternalConceptLanguage.g:3795:2: ( ( () ( ( (lv_operator_1_1= ruleOperatorCos | lv_operator_1_2= ruleOperatorSin | lv_operator_1_3= ruleOperatorTan | lv_operator_1_4= ruleOperatorAtan | lv_operator_1_5= ruleOperatorAcos | lv_operator_1_6= ruleOperatorAsin | lv_operator_1_7= ruleOperatorSqrt | lv_operator_1_8= ruleOperatorLog | lv_operator_1_9= ruleOperatorLn | lv_operator_1_10= ruleOperatorLd | lv_operator_1_11= ruleOperatorExp ) ) ) otherlv_2= '(' ( (lv_right_3_0= ruleAdditionAndSubtraction ) ) otherlv_4= ')' ) )
            // InternalConceptLanguage.g:3796:2: ( () ( ( (lv_operator_1_1= ruleOperatorCos | lv_operator_1_2= ruleOperatorSin | lv_operator_1_3= ruleOperatorTan | lv_operator_1_4= ruleOperatorAtan | lv_operator_1_5= ruleOperatorAcos | lv_operator_1_6= ruleOperatorAsin | lv_operator_1_7= ruleOperatorSqrt | lv_operator_1_8= ruleOperatorLog | lv_operator_1_9= ruleOperatorLn | lv_operator_1_10= ruleOperatorLd | lv_operator_1_11= ruleOperatorExp ) ) ) otherlv_2= '(' ( (lv_right_3_0= ruleAdditionAndSubtraction ) ) otherlv_4= ')' )
            {
            // InternalConceptLanguage.g:3796:2: ( () ( ( (lv_operator_1_1= ruleOperatorCos | lv_operator_1_2= ruleOperatorSin | lv_operator_1_3= ruleOperatorTan | lv_operator_1_4= ruleOperatorAtan | lv_operator_1_5= ruleOperatorAcos | lv_operator_1_6= ruleOperatorAsin | lv_operator_1_7= ruleOperatorSqrt | lv_operator_1_8= ruleOperatorLog | lv_operator_1_9= ruleOperatorLn | lv_operator_1_10= ruleOperatorLd | lv_operator_1_11= ruleOperatorExp ) ) ) otherlv_2= '(' ( (lv_right_3_0= ruleAdditionAndSubtraction ) ) otherlv_4= ')' )
            // InternalConceptLanguage.g:3797:3: () ( ( (lv_operator_1_1= ruleOperatorCos | lv_operator_1_2= ruleOperatorSin | lv_operator_1_3= ruleOperatorTan | lv_operator_1_4= ruleOperatorAtan | lv_operator_1_5= ruleOperatorAcos | lv_operator_1_6= ruleOperatorAsin | lv_operator_1_7= ruleOperatorSqrt | lv_operator_1_8= ruleOperatorLog | lv_operator_1_9= ruleOperatorLn | lv_operator_1_10= ruleOperatorLd | lv_operator_1_11= ruleOperatorExp ) ) ) otherlv_2= '(' ( (lv_right_3_0= ruleAdditionAndSubtraction ) ) otherlv_4= ')'
            {
            // InternalConceptLanguage.g:3797:3: ()
            // InternalConceptLanguage.g:3798:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getFunctionAccess().getFunctionAction_0(),
            					current);
            			

            }

            // InternalConceptLanguage.g:3804:3: ( ( (lv_operator_1_1= ruleOperatorCos | lv_operator_1_2= ruleOperatorSin | lv_operator_1_3= ruleOperatorTan | lv_operator_1_4= ruleOperatorAtan | lv_operator_1_5= ruleOperatorAcos | lv_operator_1_6= ruleOperatorAsin | lv_operator_1_7= ruleOperatorSqrt | lv_operator_1_8= ruleOperatorLog | lv_operator_1_9= ruleOperatorLn | lv_operator_1_10= ruleOperatorLd | lv_operator_1_11= ruleOperatorExp ) ) )
            // InternalConceptLanguage.g:3805:4: ( (lv_operator_1_1= ruleOperatorCos | lv_operator_1_2= ruleOperatorSin | lv_operator_1_3= ruleOperatorTan | lv_operator_1_4= ruleOperatorAtan | lv_operator_1_5= ruleOperatorAcos | lv_operator_1_6= ruleOperatorAsin | lv_operator_1_7= ruleOperatorSqrt | lv_operator_1_8= ruleOperatorLog | lv_operator_1_9= ruleOperatorLn | lv_operator_1_10= ruleOperatorLd | lv_operator_1_11= ruleOperatorExp ) )
            {
            // InternalConceptLanguage.g:3805:4: ( (lv_operator_1_1= ruleOperatorCos | lv_operator_1_2= ruleOperatorSin | lv_operator_1_3= ruleOperatorTan | lv_operator_1_4= ruleOperatorAtan | lv_operator_1_5= ruleOperatorAcos | lv_operator_1_6= ruleOperatorAsin | lv_operator_1_7= ruleOperatorSqrt | lv_operator_1_8= ruleOperatorLog | lv_operator_1_9= ruleOperatorLn | lv_operator_1_10= ruleOperatorLd | lv_operator_1_11= ruleOperatorExp ) )
            // InternalConceptLanguage.g:3806:5: (lv_operator_1_1= ruleOperatorCos | lv_operator_1_2= ruleOperatorSin | lv_operator_1_3= ruleOperatorTan | lv_operator_1_4= ruleOperatorAtan | lv_operator_1_5= ruleOperatorAcos | lv_operator_1_6= ruleOperatorAsin | lv_operator_1_7= ruleOperatorSqrt | lv_operator_1_8= ruleOperatorLog | lv_operator_1_9= ruleOperatorLn | lv_operator_1_10= ruleOperatorLd | lv_operator_1_11= ruleOperatorExp )
            {
            // InternalConceptLanguage.g:3806:5: (lv_operator_1_1= ruleOperatorCos | lv_operator_1_2= ruleOperatorSin | lv_operator_1_3= ruleOperatorTan | lv_operator_1_4= ruleOperatorAtan | lv_operator_1_5= ruleOperatorAcos | lv_operator_1_6= ruleOperatorAsin | lv_operator_1_7= ruleOperatorSqrt | lv_operator_1_8= ruleOperatorLog | lv_operator_1_9= ruleOperatorLn | lv_operator_1_10= ruleOperatorLd | lv_operator_1_11= ruleOperatorExp )
            int alt52=11;
            switch ( input.LA(1) ) {
            case 66:
                {
                alt52=1;
                }
                break;
            case 67:
                {
                alt52=2;
                }
                break;
            case 68:
                {
                alt52=3;
                }
                break;
            case 69:
                {
                alt52=4;
                }
                break;
            case 70:
                {
                alt52=5;
                }
                break;
            case 71:
                {
                alt52=6;
                }
                break;
            case 72:
                {
                alt52=7;
                }
                break;
            case 73:
                {
                alt52=8;
                }
                break;
            case 74:
                {
                alt52=9;
                }
                break;
            case 76:
                {
                alt52=10;
                }
                break;
            case 75:
                {
                alt52=11;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 52, 0, input);

                throw nvae;
            }

            switch (alt52) {
                case 1 :
                    // InternalConceptLanguage.g:3807:6: lv_operator_1_1= ruleOperatorCos
                    {

                    						newCompositeNode(grammarAccess.getFunctionAccess().getOperatorOperatorCosEnumRuleCall_1_0_0());
                    					
                    pushFollow(FOLLOW_53);
                    lv_operator_1_1=ruleOperatorCos();

                    state._fsp--;


                    						if (current==null) {
                    							current = createModelElementForParent(grammarAccess.getFunctionRule());
                    						}
                    						set(
                    							current,
                    							"operator",
                    							lv_operator_1_1,
                    							"de.dlr.sc.virsat.model.concept.ConceptLanguage.OperatorCos");
                    						afterParserOrEnumRuleCall();
                    					

                    }
                    break;
                case 2 :
                    // InternalConceptLanguage.g:3823:6: lv_operator_1_2= ruleOperatorSin
                    {

                    						newCompositeNode(grammarAccess.getFunctionAccess().getOperatorOperatorSinEnumRuleCall_1_0_1());
                    					
                    pushFollow(FOLLOW_53);
                    lv_operator_1_2=ruleOperatorSin();

                    state._fsp--;


                    						if (current==null) {
                    							current = createModelElementForParent(grammarAccess.getFunctionRule());
                    						}
                    						set(
                    							current,
                    							"operator",
                    							lv_operator_1_2,
                    							"de.dlr.sc.virsat.model.concept.ConceptLanguage.OperatorSin");
                    						afterParserOrEnumRuleCall();
                    					

                    }
                    break;
                case 3 :
                    // InternalConceptLanguage.g:3839:6: lv_operator_1_3= ruleOperatorTan
                    {

                    						newCompositeNode(grammarAccess.getFunctionAccess().getOperatorOperatorTanEnumRuleCall_1_0_2());
                    					
                    pushFollow(FOLLOW_53);
                    lv_operator_1_3=ruleOperatorTan();

                    state._fsp--;


                    						if (current==null) {
                    							current = createModelElementForParent(grammarAccess.getFunctionRule());
                    						}
                    						set(
                    							current,
                    							"operator",
                    							lv_operator_1_3,
                    							"de.dlr.sc.virsat.model.concept.ConceptLanguage.OperatorTan");
                    						afterParserOrEnumRuleCall();
                    					

                    }
                    break;
                case 4 :
                    // InternalConceptLanguage.g:3855:6: lv_operator_1_4= ruleOperatorAtan
                    {

                    						newCompositeNode(grammarAccess.getFunctionAccess().getOperatorOperatorAtanEnumRuleCall_1_0_3());
                    					
                    pushFollow(FOLLOW_53);
                    lv_operator_1_4=ruleOperatorAtan();

                    state._fsp--;


                    						if (current==null) {
                    							current = createModelElementForParent(grammarAccess.getFunctionRule());
                    						}
                    						set(
                    							current,
                    							"operator",
                    							lv_operator_1_4,
                    							"de.dlr.sc.virsat.model.concept.ConceptLanguage.OperatorAtan");
                    						afterParserOrEnumRuleCall();
                    					

                    }
                    break;
                case 5 :
                    // InternalConceptLanguage.g:3871:6: lv_operator_1_5= ruleOperatorAcos
                    {

                    						newCompositeNode(grammarAccess.getFunctionAccess().getOperatorOperatorAcosEnumRuleCall_1_0_4());
                    					
                    pushFollow(FOLLOW_53);
                    lv_operator_1_5=ruleOperatorAcos();

                    state._fsp--;


                    						if (current==null) {
                    							current = createModelElementForParent(grammarAccess.getFunctionRule());
                    						}
                    						set(
                    							current,
                    							"operator",
                    							lv_operator_1_5,
                    							"de.dlr.sc.virsat.model.concept.ConceptLanguage.OperatorAcos");
                    						afterParserOrEnumRuleCall();
                    					

                    }
                    break;
                case 6 :
                    // InternalConceptLanguage.g:3887:6: lv_operator_1_6= ruleOperatorAsin
                    {

                    						newCompositeNode(grammarAccess.getFunctionAccess().getOperatorOperatorAsinEnumRuleCall_1_0_5());
                    					
                    pushFollow(FOLLOW_53);
                    lv_operator_1_6=ruleOperatorAsin();

                    state._fsp--;


                    						if (current==null) {
                    							current = createModelElementForParent(grammarAccess.getFunctionRule());
                    						}
                    						set(
                    							current,
                    							"operator",
                    							lv_operator_1_6,
                    							"de.dlr.sc.virsat.model.concept.ConceptLanguage.OperatorAsin");
                    						afterParserOrEnumRuleCall();
                    					

                    }
                    break;
                case 7 :
                    // InternalConceptLanguage.g:3903:6: lv_operator_1_7= ruleOperatorSqrt
                    {

                    						newCompositeNode(grammarAccess.getFunctionAccess().getOperatorOperatorSqrtEnumRuleCall_1_0_6());
                    					
                    pushFollow(FOLLOW_53);
                    lv_operator_1_7=ruleOperatorSqrt();

                    state._fsp--;


                    						if (current==null) {
                    							current = createModelElementForParent(grammarAccess.getFunctionRule());
                    						}
                    						set(
                    							current,
                    							"operator",
                    							lv_operator_1_7,
                    							"de.dlr.sc.virsat.model.concept.ConceptLanguage.OperatorSqrt");
                    						afterParserOrEnumRuleCall();
                    					

                    }
                    break;
                case 8 :
                    // InternalConceptLanguage.g:3919:6: lv_operator_1_8= ruleOperatorLog
                    {

                    						newCompositeNode(grammarAccess.getFunctionAccess().getOperatorOperatorLogEnumRuleCall_1_0_7());
                    					
                    pushFollow(FOLLOW_53);
                    lv_operator_1_8=ruleOperatorLog();

                    state._fsp--;


                    						if (current==null) {
                    							current = createModelElementForParent(grammarAccess.getFunctionRule());
                    						}
                    						set(
                    							current,
                    							"operator",
                    							lv_operator_1_8,
                    							"de.dlr.sc.virsat.model.concept.ConceptLanguage.OperatorLog");
                    						afterParserOrEnumRuleCall();
                    					

                    }
                    break;
                case 9 :
                    // InternalConceptLanguage.g:3935:6: lv_operator_1_9= ruleOperatorLn
                    {

                    						newCompositeNode(grammarAccess.getFunctionAccess().getOperatorOperatorLnEnumRuleCall_1_0_8());
                    					
                    pushFollow(FOLLOW_53);
                    lv_operator_1_9=ruleOperatorLn();

                    state._fsp--;


                    						if (current==null) {
                    							current = createModelElementForParent(grammarAccess.getFunctionRule());
                    						}
                    						set(
                    							current,
                    							"operator",
                    							lv_operator_1_9,
                    							"de.dlr.sc.virsat.model.concept.ConceptLanguage.OperatorLn");
                    						afterParserOrEnumRuleCall();
                    					

                    }
                    break;
                case 10 :
                    // InternalConceptLanguage.g:3951:6: lv_operator_1_10= ruleOperatorLd
                    {

                    						newCompositeNode(grammarAccess.getFunctionAccess().getOperatorOperatorLdEnumRuleCall_1_0_9());
                    					
                    pushFollow(FOLLOW_53);
                    lv_operator_1_10=ruleOperatorLd();

                    state._fsp--;


                    						if (current==null) {
                    							current = createModelElementForParent(grammarAccess.getFunctionRule());
                    						}
                    						set(
                    							current,
                    							"operator",
                    							lv_operator_1_10,
                    							"de.dlr.sc.virsat.model.concept.ConceptLanguage.OperatorLd");
                    						afterParserOrEnumRuleCall();
                    					

                    }
                    break;
                case 11 :
                    // InternalConceptLanguage.g:3967:6: lv_operator_1_11= ruleOperatorExp
                    {

                    						newCompositeNode(grammarAccess.getFunctionAccess().getOperatorOperatorExpEnumRuleCall_1_0_10());
                    					
                    pushFollow(FOLLOW_53);
                    lv_operator_1_11=ruleOperatorExp();

                    state._fsp--;


                    						if (current==null) {
                    							current = createModelElementForParent(grammarAccess.getFunctionRule());
                    						}
                    						set(
                    							current,
                    							"operator",
                    							lv_operator_1_11,
                    							"de.dlr.sc.virsat.model.concept.ConceptLanguage.OperatorExp");
                    						afterParserOrEnumRuleCall();
                    					

                    }
                    break;

            }


            }


            }

            otherlv_2=(Token)match(input,53,FOLLOW_49); 

            			newLeafNode(otherlv_2, grammarAccess.getFunctionAccess().getLeftParenthesisKeyword_2());
            		
            // InternalConceptLanguage.g:3989:3: ( (lv_right_3_0= ruleAdditionAndSubtraction ) )
            // InternalConceptLanguage.g:3990:4: (lv_right_3_0= ruleAdditionAndSubtraction )
            {
            // InternalConceptLanguage.g:3990:4: (lv_right_3_0= ruleAdditionAndSubtraction )
            // InternalConceptLanguage.g:3991:5: lv_right_3_0= ruleAdditionAndSubtraction
            {

            					newCompositeNode(grammarAccess.getFunctionAccess().getRightAdditionAndSubtractionParserRuleCall_3_0());
            				
            pushFollow(FOLLOW_54);
            lv_right_3_0=ruleAdditionAndSubtraction();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getFunctionRule());
            					}
            					set(
            						current,
            						"right",
            						lv_right_3_0,
            						"de.dlr.sc.virsat.model.concept.ConceptLanguage.AdditionAndSubtraction");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            otherlv_4=(Token)match(input,54,FOLLOW_2); 

            			newLeafNode(otherlv_4, grammarAccess.getFunctionAccess().getRightParenthesisKeyword_4());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleFunction"


    // $ANTLR start "entryRuleAAdvancedFunction"
    // InternalConceptLanguage.g:4016:1: entryRuleAAdvancedFunction returns [EObject current=null] : iv_ruleAAdvancedFunction= ruleAAdvancedFunction EOF ;
    public final EObject entryRuleAAdvancedFunction() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleAAdvancedFunction = null;


        try {
            // InternalConceptLanguage.g:4016:58: (iv_ruleAAdvancedFunction= ruleAAdvancedFunction EOF )
            // InternalConceptLanguage.g:4017:2: iv_ruleAAdvancedFunction= ruleAAdvancedFunction EOF
            {
             newCompositeNode(grammarAccess.getAAdvancedFunctionRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleAAdvancedFunction=ruleAAdvancedFunction();

            state._fsp--;

             current =iv_ruleAAdvancedFunction; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleAAdvancedFunction"


    // $ANTLR start "ruleAAdvancedFunction"
    // InternalConceptLanguage.g:4023:1: ruleAAdvancedFunction returns [EObject current=null] : (this_AdvancedFunction_0= ruleAdvancedFunction | this_SetFunction_1= ruleSetFunction ) ;
    public final EObject ruleAAdvancedFunction() throws RecognitionException {
        EObject current = null;

        EObject this_AdvancedFunction_0 = null;

        EObject this_SetFunction_1 = null;



        	enterRule();

        try {
            // InternalConceptLanguage.g:4029:2: ( (this_AdvancedFunction_0= ruleAdvancedFunction | this_SetFunction_1= ruleSetFunction ) )
            // InternalConceptLanguage.g:4030:2: (this_AdvancedFunction_0= ruleAdvancedFunction | this_SetFunction_1= ruleSetFunction )
            {
            // InternalConceptLanguage.g:4030:2: (this_AdvancedFunction_0= ruleAdvancedFunction | this_SetFunction_1= ruleSetFunction )
            int alt53=2;
            int LA53_0 = input.LA(1);

            if ( (LA53_0==RULE_ID) ) {
                int LA53_1 = input.LA(2);

                if ( (LA53_1==53) ) {
                    alt53=1;
                }
                else if ( (LA53_1==16) ) {
                    alt53=2;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 53, 1, input);

                    throw nvae;
                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 53, 0, input);

                throw nvae;
            }
            switch (alt53) {
                case 1 :
                    // InternalConceptLanguage.g:4031:3: this_AdvancedFunction_0= ruleAdvancedFunction
                    {

                    			newCompositeNode(grammarAccess.getAAdvancedFunctionAccess().getAdvancedFunctionParserRuleCall_0());
                    		
                    pushFollow(FOLLOW_2);
                    this_AdvancedFunction_0=ruleAdvancedFunction();

                    state._fsp--;


                    			current = this_AdvancedFunction_0;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 2 :
                    // InternalConceptLanguage.g:4040:3: this_SetFunction_1= ruleSetFunction
                    {

                    			newCompositeNode(grammarAccess.getAAdvancedFunctionAccess().getSetFunctionParserRuleCall_1());
                    		
                    pushFollow(FOLLOW_2);
                    this_SetFunction_1=ruleSetFunction();

                    state._fsp--;


                    			current = this_SetFunction_1;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleAAdvancedFunction"


    // $ANTLR start "entryRuleAdvancedFunction"
    // InternalConceptLanguage.g:4052:1: entryRuleAdvancedFunction returns [EObject current=null] : iv_ruleAdvancedFunction= ruleAdvancedFunction EOF ;
    public final EObject entryRuleAdvancedFunction() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleAdvancedFunction = null;


        try {
            // InternalConceptLanguage.g:4052:57: (iv_ruleAdvancedFunction= ruleAdvancedFunction EOF )
            // InternalConceptLanguage.g:4053:2: iv_ruleAdvancedFunction= ruleAdvancedFunction EOF
            {
             newCompositeNode(grammarAccess.getAdvancedFunctionRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleAdvancedFunction=ruleAdvancedFunction();

            state._fsp--;

             current =iv_ruleAdvancedFunction; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleAdvancedFunction"


    // $ANTLR start "ruleAdvancedFunction"
    // InternalConceptLanguage.g:4059:1: ruleAdvancedFunction returns [EObject current=null] : ( () ( (lv_operator_1_0= RULE_ID ) ) otherlv_2= '(' ( (lv_inputs_3_0= ruleAdditionAndSubtraction ) ) (otherlv_4= ',' ( (lv_inputs_5_0= ruleAdditionAndSubtraction ) ) )* otherlv_6= ')' ) ;
    public final EObject ruleAdvancedFunction() throws RecognitionException {
        EObject current = null;

        Token lv_operator_1_0=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        Token otherlv_6=null;
        EObject lv_inputs_3_0 = null;

        EObject lv_inputs_5_0 = null;



        	enterRule();

        try {
            // InternalConceptLanguage.g:4065:2: ( ( () ( (lv_operator_1_0= RULE_ID ) ) otherlv_2= '(' ( (lv_inputs_3_0= ruleAdditionAndSubtraction ) ) (otherlv_4= ',' ( (lv_inputs_5_0= ruleAdditionAndSubtraction ) ) )* otherlv_6= ')' ) )
            // InternalConceptLanguage.g:4066:2: ( () ( (lv_operator_1_0= RULE_ID ) ) otherlv_2= '(' ( (lv_inputs_3_0= ruleAdditionAndSubtraction ) ) (otherlv_4= ',' ( (lv_inputs_5_0= ruleAdditionAndSubtraction ) ) )* otherlv_6= ')' )
            {
            // InternalConceptLanguage.g:4066:2: ( () ( (lv_operator_1_0= RULE_ID ) ) otherlv_2= '(' ( (lv_inputs_3_0= ruleAdditionAndSubtraction ) ) (otherlv_4= ',' ( (lv_inputs_5_0= ruleAdditionAndSubtraction ) ) )* otherlv_6= ')' )
            // InternalConceptLanguage.g:4067:3: () ( (lv_operator_1_0= RULE_ID ) ) otherlv_2= '(' ( (lv_inputs_3_0= ruleAdditionAndSubtraction ) ) (otherlv_4= ',' ( (lv_inputs_5_0= ruleAdditionAndSubtraction ) ) )* otherlv_6= ')'
            {
            // InternalConceptLanguage.g:4067:3: ()
            // InternalConceptLanguage.g:4068:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getAdvancedFunctionAccess().getAdvancedFunctionAction_0(),
            					current);
            			

            }

            // InternalConceptLanguage.g:4074:3: ( (lv_operator_1_0= RULE_ID ) )
            // InternalConceptLanguage.g:4075:4: (lv_operator_1_0= RULE_ID )
            {
            // InternalConceptLanguage.g:4075:4: (lv_operator_1_0= RULE_ID )
            // InternalConceptLanguage.g:4076:5: lv_operator_1_0= RULE_ID
            {
            lv_operator_1_0=(Token)match(input,RULE_ID,FOLLOW_53); 

            					newLeafNode(lv_operator_1_0, grammarAccess.getAdvancedFunctionAccess().getOperatorIDTerminalRuleCall_1_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getAdvancedFunctionRule());
            					}
            					setWithLastConsumed(
            						current,
            						"operator",
            						lv_operator_1_0,
            						"org.eclipse.xtext.common.Terminals.ID");
            				

            }


            }

            otherlv_2=(Token)match(input,53,FOLLOW_49); 

            			newLeafNode(otherlv_2, grammarAccess.getAdvancedFunctionAccess().getLeftParenthesisKeyword_2());
            		
            // InternalConceptLanguage.g:4096:3: ( (lv_inputs_3_0= ruleAdditionAndSubtraction ) )
            // InternalConceptLanguage.g:4097:4: (lv_inputs_3_0= ruleAdditionAndSubtraction )
            {
            // InternalConceptLanguage.g:4097:4: (lv_inputs_3_0= ruleAdditionAndSubtraction )
            // InternalConceptLanguage.g:4098:5: lv_inputs_3_0= ruleAdditionAndSubtraction
            {

            					newCompositeNode(grammarAccess.getAdvancedFunctionAccess().getInputsAdditionAndSubtractionParserRuleCall_3_0());
            				
            pushFollow(FOLLOW_55);
            lv_inputs_3_0=ruleAdditionAndSubtraction();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getAdvancedFunctionRule());
            					}
            					add(
            						current,
            						"inputs",
            						lv_inputs_3_0,
            						"de.dlr.sc.virsat.model.concept.ConceptLanguage.AdditionAndSubtraction");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            // InternalConceptLanguage.g:4115:3: (otherlv_4= ',' ( (lv_inputs_5_0= ruleAdditionAndSubtraction ) ) )*
            loop54:
            do {
                int alt54=2;
                int LA54_0 = input.LA(1);

                if ( (LA54_0==25) ) {
                    alt54=1;
                }


                switch (alt54) {
            	case 1 :
            	    // InternalConceptLanguage.g:4116:4: otherlv_4= ',' ( (lv_inputs_5_0= ruleAdditionAndSubtraction ) )
            	    {
            	    otherlv_4=(Token)match(input,25,FOLLOW_49); 

            	    				newLeafNode(otherlv_4, grammarAccess.getAdvancedFunctionAccess().getCommaKeyword_4_0());
            	    			
            	    // InternalConceptLanguage.g:4120:4: ( (lv_inputs_5_0= ruleAdditionAndSubtraction ) )
            	    // InternalConceptLanguage.g:4121:5: (lv_inputs_5_0= ruleAdditionAndSubtraction )
            	    {
            	    // InternalConceptLanguage.g:4121:5: (lv_inputs_5_0= ruleAdditionAndSubtraction )
            	    // InternalConceptLanguage.g:4122:6: lv_inputs_5_0= ruleAdditionAndSubtraction
            	    {

            	    						newCompositeNode(grammarAccess.getAdvancedFunctionAccess().getInputsAdditionAndSubtractionParserRuleCall_4_1_0());
            	    					
            	    pushFollow(FOLLOW_55);
            	    lv_inputs_5_0=ruleAdditionAndSubtraction();

            	    state._fsp--;


            	    						if (current==null) {
            	    							current = createModelElementForParent(grammarAccess.getAdvancedFunctionRule());
            	    						}
            	    						add(
            	    							current,
            	    							"inputs",
            	    							lv_inputs_5_0,
            	    							"de.dlr.sc.virsat.model.concept.ConceptLanguage.AdditionAndSubtraction");
            	    						afterParserOrEnumRuleCall();
            	    					

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop54;
                }
            } while (true);

            otherlv_6=(Token)match(input,54,FOLLOW_2); 

            			newLeafNode(otherlv_6, grammarAccess.getAdvancedFunctionAccess().getRightParenthesisKeyword_5());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleAdvancedFunction"


    // $ANTLR start "entryRuleSetFunction"
    // InternalConceptLanguage.g:4148:1: entryRuleSetFunction returns [EObject current=null] : iv_ruleSetFunction= ruleSetFunction EOF ;
    public final EObject entryRuleSetFunction() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleSetFunction = null;


        try {
            // InternalConceptLanguage.g:4148:52: (iv_ruleSetFunction= ruleSetFunction EOF )
            // InternalConceptLanguage.g:4149:2: iv_ruleSetFunction= ruleSetFunction EOF
            {
             newCompositeNode(grammarAccess.getSetFunctionRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleSetFunction=ruleSetFunction();

            state._fsp--;

             current =iv_ruleSetFunction; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleSetFunction"


    // $ANTLR start "ruleSetFunction"
    // InternalConceptLanguage.g:4155:1: ruleSetFunction returns [EObject current=null] : ( () ( (lv_operator_1_0= RULE_ID ) ) otherlv_2= '{' ( ( ruleQualifiedName ) ) (otherlv_4= ',' ( (lv_depth_5_0= RULE_INT ) ) )? (otherlv_6= ',' ( (lv_filterName_7_0= RULE_ID ) ) )? otherlv_8= '}' ) ;
    public final EObject ruleSetFunction() throws RecognitionException {
        EObject current = null;

        Token lv_operator_1_0=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        Token lv_depth_5_0=null;
        Token otherlv_6=null;
        Token lv_filterName_7_0=null;
        Token otherlv_8=null;


        	enterRule();

        try {
            // InternalConceptLanguage.g:4161:2: ( ( () ( (lv_operator_1_0= RULE_ID ) ) otherlv_2= '{' ( ( ruleQualifiedName ) ) (otherlv_4= ',' ( (lv_depth_5_0= RULE_INT ) ) )? (otherlv_6= ',' ( (lv_filterName_7_0= RULE_ID ) ) )? otherlv_8= '}' ) )
            // InternalConceptLanguage.g:4162:2: ( () ( (lv_operator_1_0= RULE_ID ) ) otherlv_2= '{' ( ( ruleQualifiedName ) ) (otherlv_4= ',' ( (lv_depth_5_0= RULE_INT ) ) )? (otherlv_6= ',' ( (lv_filterName_7_0= RULE_ID ) ) )? otherlv_8= '}' )
            {
            // InternalConceptLanguage.g:4162:2: ( () ( (lv_operator_1_0= RULE_ID ) ) otherlv_2= '{' ( ( ruleQualifiedName ) ) (otherlv_4= ',' ( (lv_depth_5_0= RULE_INT ) ) )? (otherlv_6= ',' ( (lv_filterName_7_0= RULE_ID ) ) )? otherlv_8= '}' )
            // InternalConceptLanguage.g:4163:3: () ( (lv_operator_1_0= RULE_ID ) ) otherlv_2= '{' ( ( ruleQualifiedName ) ) (otherlv_4= ',' ( (lv_depth_5_0= RULE_INT ) ) )? (otherlv_6= ',' ( (lv_filterName_7_0= RULE_ID ) ) )? otherlv_8= '}'
            {
            // InternalConceptLanguage.g:4163:3: ()
            // InternalConceptLanguage.g:4164:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getSetFunctionAccess().getSetFunctionAction_0(),
            					current);
            			

            }

            // InternalConceptLanguage.g:4170:3: ( (lv_operator_1_0= RULE_ID ) )
            // InternalConceptLanguage.g:4171:4: (lv_operator_1_0= RULE_ID )
            {
            // InternalConceptLanguage.g:4171:4: (lv_operator_1_0= RULE_ID )
            // InternalConceptLanguage.g:4172:5: lv_operator_1_0= RULE_ID
            {
            lv_operator_1_0=(Token)match(input,RULE_ID,FOLLOW_19); 

            					newLeafNode(lv_operator_1_0, grammarAccess.getSetFunctionAccess().getOperatorIDTerminalRuleCall_1_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getSetFunctionRule());
            					}
            					setWithLastConsumed(
            						current,
            						"operator",
            						lv_operator_1_0,
            						"org.eclipse.xtext.common.Terminals.ID");
            				

            }


            }

            otherlv_2=(Token)match(input,16,FOLLOW_3); 

            			newLeafNode(otherlv_2, grammarAccess.getSetFunctionAccess().getLeftCurlyBracketKeyword_2());
            		
            // InternalConceptLanguage.g:4192:3: ( ( ruleQualifiedName ) )
            // InternalConceptLanguage.g:4193:4: ( ruleQualifiedName )
            {
            // InternalConceptLanguage.g:4193:4: ( ruleQualifiedName )
            // InternalConceptLanguage.g:4194:5: ruleQualifiedName
            {

            					if (current==null) {
            						current = createModelElement(grammarAccess.getSetFunctionRule());
            					}
            				

            					newCompositeNode(grammarAccess.getSetFunctionAccess().getTypeDefinitionATypeDefinitionCrossReference_3_0());
            				
            pushFollow(FOLLOW_56);
            ruleQualifiedName();

            state._fsp--;


            					afterParserOrEnumRuleCall();
            				

            }


            }

            // InternalConceptLanguage.g:4208:3: (otherlv_4= ',' ( (lv_depth_5_0= RULE_INT ) ) )?
            int alt55=2;
            int LA55_0 = input.LA(1);

            if ( (LA55_0==25) ) {
                int LA55_1 = input.LA(2);

                if ( (LA55_1==RULE_INT) ) {
                    alt55=1;
                }
            }
            switch (alt55) {
                case 1 :
                    // InternalConceptLanguage.g:4209:4: otherlv_4= ',' ( (lv_depth_5_0= RULE_INT ) )
                    {
                    otherlv_4=(Token)match(input,25,FOLLOW_6); 

                    				newLeafNode(otherlv_4, grammarAccess.getSetFunctionAccess().getCommaKeyword_4_0());
                    			
                    // InternalConceptLanguage.g:4213:4: ( (lv_depth_5_0= RULE_INT ) )
                    // InternalConceptLanguage.g:4214:5: (lv_depth_5_0= RULE_INT )
                    {
                    // InternalConceptLanguage.g:4214:5: (lv_depth_5_0= RULE_INT )
                    // InternalConceptLanguage.g:4215:6: lv_depth_5_0= RULE_INT
                    {
                    lv_depth_5_0=(Token)match(input,RULE_INT,FOLLOW_56); 

                    						newLeafNode(lv_depth_5_0, grammarAccess.getSetFunctionAccess().getDepthINTTerminalRuleCall_4_1_0());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getSetFunctionRule());
                    						}
                    						setWithLastConsumed(
                    							current,
                    							"depth",
                    							lv_depth_5_0,
                    							"org.eclipse.xtext.common.Terminals.INT");
                    					

                    }


                    }


                    }
                    break;

            }

            // InternalConceptLanguage.g:4232:3: (otherlv_6= ',' ( (lv_filterName_7_0= RULE_ID ) ) )?
            int alt56=2;
            int LA56_0 = input.LA(1);

            if ( (LA56_0==25) ) {
                alt56=1;
            }
            switch (alt56) {
                case 1 :
                    // InternalConceptLanguage.g:4233:4: otherlv_6= ',' ( (lv_filterName_7_0= RULE_ID ) )
                    {
                    otherlv_6=(Token)match(input,25,FOLLOW_3); 

                    				newLeafNode(otherlv_6, grammarAccess.getSetFunctionAccess().getCommaKeyword_5_0());
                    			
                    // InternalConceptLanguage.g:4237:4: ( (lv_filterName_7_0= RULE_ID ) )
                    // InternalConceptLanguage.g:4238:5: (lv_filterName_7_0= RULE_ID )
                    {
                    // InternalConceptLanguage.g:4238:5: (lv_filterName_7_0= RULE_ID )
                    // InternalConceptLanguage.g:4239:6: lv_filterName_7_0= RULE_ID
                    {
                    lv_filterName_7_0=(Token)match(input,RULE_ID,FOLLOW_24); 

                    						newLeafNode(lv_filterName_7_0, grammarAccess.getSetFunctionAccess().getFilterNameIDTerminalRuleCall_5_1_0());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getSetFunctionRule());
                    						}
                    						setWithLastConsumed(
                    							current,
                    							"filterName",
                    							lv_filterName_7_0,
                    							"org.eclipse.xtext.common.Terminals.ID");
                    					

                    }


                    }


                    }
                    break;

            }

            otherlv_8=(Token)match(input,17,FOLLOW_2); 

            			newLeafNode(otherlv_8, grammarAccess.getSetFunctionAccess().getRightCurlyBracketKeyword_6());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleSetFunction"


    // $ANTLR start "entryRuleNumberLiteral"
    // InternalConceptLanguage.g:4264:1: entryRuleNumberLiteral returns [EObject current=null] : iv_ruleNumberLiteral= ruleNumberLiteral EOF ;
    public final EObject entryRuleNumberLiteral() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleNumberLiteral = null;


        try {
            // InternalConceptLanguage.g:4264:54: (iv_ruleNumberLiteral= ruleNumberLiteral EOF )
            // InternalConceptLanguage.g:4265:2: iv_ruleNumberLiteral= ruleNumberLiteral EOF
            {
             newCompositeNode(grammarAccess.getNumberLiteralRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleNumberLiteral=ruleNumberLiteral();

            state._fsp--;

             current =iv_ruleNumberLiteral; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleNumberLiteral"


    // $ANTLR start "ruleNumberLiteral"
    // InternalConceptLanguage.g:4271:1: ruleNumberLiteral returns [EObject current=null] : ( () ( (lv_value_1_0= ruleFloatLiteralString ) ) ) ;
    public final EObject ruleNumberLiteral() throws RecognitionException {
        EObject current = null;

        AntlrDatatypeRuleToken lv_value_1_0 = null;



        	enterRule();

        try {
            // InternalConceptLanguage.g:4277:2: ( ( () ( (lv_value_1_0= ruleFloatLiteralString ) ) ) )
            // InternalConceptLanguage.g:4278:2: ( () ( (lv_value_1_0= ruleFloatLiteralString ) ) )
            {
            // InternalConceptLanguage.g:4278:2: ( () ( (lv_value_1_0= ruleFloatLiteralString ) ) )
            // InternalConceptLanguage.g:4279:3: () ( (lv_value_1_0= ruleFloatLiteralString ) )
            {
            // InternalConceptLanguage.g:4279:3: ()
            // InternalConceptLanguage.g:4280:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getNumberLiteralAccess().getNumberLiteralAction_0(),
            					current);
            			

            }

            // InternalConceptLanguage.g:4286:3: ( (lv_value_1_0= ruleFloatLiteralString ) )
            // InternalConceptLanguage.g:4287:4: (lv_value_1_0= ruleFloatLiteralString )
            {
            // InternalConceptLanguage.g:4287:4: (lv_value_1_0= ruleFloatLiteralString )
            // InternalConceptLanguage.g:4288:5: lv_value_1_0= ruleFloatLiteralString
            {

            					newCompositeNode(grammarAccess.getNumberLiteralAccess().getValueFloatLiteralStringParserRuleCall_1_0());
            				
            pushFollow(FOLLOW_2);
            lv_value_1_0=ruleFloatLiteralString();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getNumberLiteralRule());
            					}
            					set(
            						current,
            						"value",
            						lv_value_1_0,
            						"de.dlr.sc.virsat.model.concept.ConceptLanguage.FloatLiteralString");
            					afterParserOrEnumRuleCall();
            				

            }


            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleNumberLiteral"


    // $ANTLR start "entryRuleValuePi"
    // InternalConceptLanguage.g:4309:1: entryRuleValuePi returns [EObject current=null] : iv_ruleValuePi= ruleValuePi EOF ;
    public final EObject entryRuleValuePi() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleValuePi = null;


        try {
            // InternalConceptLanguage.g:4309:48: (iv_ruleValuePi= ruleValuePi EOF )
            // InternalConceptLanguage.g:4310:2: iv_ruleValuePi= ruleValuePi EOF
            {
             newCompositeNode(grammarAccess.getValuePiRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleValuePi=ruleValuePi();

            state._fsp--;

             current =iv_ruleValuePi; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleValuePi"


    // $ANTLR start "ruleValuePi"
    // InternalConceptLanguage.g:4316:1: ruleValuePi returns [EObject current=null] : ( () otherlv_1= 'pi' ) ;
    public final EObject ruleValuePi() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;


        	enterRule();

        try {
            // InternalConceptLanguage.g:4322:2: ( ( () otherlv_1= 'pi' ) )
            // InternalConceptLanguage.g:4323:2: ( () otherlv_1= 'pi' )
            {
            // InternalConceptLanguage.g:4323:2: ( () otherlv_1= 'pi' )
            // InternalConceptLanguage.g:4324:3: () otherlv_1= 'pi'
            {
            // InternalConceptLanguage.g:4324:3: ()
            // InternalConceptLanguage.g:4325:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getValuePiAccess().getValuePiAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,55,FOLLOW_2); 

            			newLeafNode(otherlv_1, grammarAccess.getValuePiAccess().getPiKeyword_1());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleValuePi"


    // $ANTLR start "entryRuleValueE"
    // InternalConceptLanguage.g:4339:1: entryRuleValueE returns [EObject current=null] : iv_ruleValueE= ruleValueE EOF ;
    public final EObject entryRuleValueE() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleValueE = null;


        try {
            // InternalConceptLanguage.g:4339:47: (iv_ruleValueE= ruleValueE EOF )
            // InternalConceptLanguage.g:4340:2: iv_ruleValueE= ruleValueE EOF
            {
             newCompositeNode(grammarAccess.getValueERule()); 
            pushFollow(FOLLOW_1);
            iv_ruleValueE=ruleValueE();

            state._fsp--;

             current =iv_ruleValueE; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleValueE"


    // $ANTLR start "ruleValueE"
    // InternalConceptLanguage.g:4346:1: ruleValueE returns [EObject current=null] : ( () otherlv_1= 'e' ) ;
    public final EObject ruleValueE() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;


        	enterRule();

        try {
            // InternalConceptLanguage.g:4352:2: ( ( () otherlv_1= 'e' ) )
            // InternalConceptLanguage.g:4353:2: ( () otherlv_1= 'e' )
            {
            // InternalConceptLanguage.g:4353:2: ( () otherlv_1= 'e' )
            // InternalConceptLanguage.g:4354:3: () otherlv_1= 'e'
            {
            // InternalConceptLanguage.g:4354:3: ()
            // InternalConceptLanguage.g:4355:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getValueEAccess().getValueEAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,56,FOLLOW_2); 

            			newLeafNode(otherlv_1, grammarAccess.getValueEAccess().getEKeyword_1());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleValueE"


    // $ANTLR start "entryRuleVersion"
    // InternalConceptLanguage.g:4369:1: entryRuleVersion returns [String current=null] : iv_ruleVersion= ruleVersion EOF ;
    public final String entryRuleVersion() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleVersion = null;


        try {
            // InternalConceptLanguage.g:4369:47: (iv_ruleVersion= ruleVersion EOF )
            // InternalConceptLanguage.g:4370:2: iv_ruleVersion= ruleVersion EOF
            {
             newCompositeNode(grammarAccess.getVersionRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleVersion=ruleVersion();

            state._fsp--;

             current =iv_ruleVersion.getText(); 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleVersion"


    // $ANTLR start "ruleVersion"
    // InternalConceptLanguage.g:4376:1: ruleVersion returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (this_INT_0= RULE_INT (kw= '.' this_INT_2= RULE_INT )* ) ;
    public final AntlrDatatypeRuleToken ruleVersion() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_INT_0=null;
        Token kw=null;
        Token this_INT_2=null;


        	enterRule();

        try {
            // InternalConceptLanguage.g:4382:2: ( (this_INT_0= RULE_INT (kw= '.' this_INT_2= RULE_INT )* ) )
            // InternalConceptLanguage.g:4383:2: (this_INT_0= RULE_INT (kw= '.' this_INT_2= RULE_INT )* )
            {
            // InternalConceptLanguage.g:4383:2: (this_INT_0= RULE_INT (kw= '.' this_INT_2= RULE_INT )* )
            // InternalConceptLanguage.g:4384:3: this_INT_0= RULE_INT (kw= '.' this_INT_2= RULE_INT )*
            {
            this_INT_0=(Token)match(input,RULE_INT,FOLLOW_57); 

            			current.merge(this_INT_0);
            		

            			newLeafNode(this_INT_0, grammarAccess.getVersionAccess().getINTTerminalRuleCall_0());
            		
            // InternalConceptLanguage.g:4391:3: (kw= '.' this_INT_2= RULE_INT )*
            loop57:
            do {
                int alt57=2;
                int LA57_0 = input.LA(1);

                if ( (LA57_0==57) ) {
                    alt57=1;
                }


                switch (alt57) {
            	case 1 :
            	    // InternalConceptLanguage.g:4392:4: kw= '.' this_INT_2= RULE_INT
            	    {
            	    kw=(Token)match(input,57,FOLLOW_6); 

            	    				current.merge(kw);
            	    				newLeafNode(kw, grammarAccess.getVersionAccess().getFullStopKeyword_1_0());
            	    			
            	    this_INT_2=(Token)match(input,RULE_INT,FOLLOW_57); 

            	    				current.merge(this_INT_2);
            	    			

            	    				newLeafNode(this_INT_2, grammarAccess.getVersionAccess().getINTTerminalRuleCall_1_1());
            	    			

            	    }
            	    break;

            	default :
            	    break loop57;
                }
            } while (true);


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleVersion"


    // $ANTLR start "entryRuleIntLiteralString"
    // InternalConceptLanguage.g:4409:1: entryRuleIntLiteralString returns [String current=null] : iv_ruleIntLiteralString= ruleIntLiteralString EOF ;
    public final String entryRuleIntLiteralString() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleIntLiteralString = null;


        try {
            // InternalConceptLanguage.g:4409:56: (iv_ruleIntLiteralString= ruleIntLiteralString EOF )
            // InternalConceptLanguage.g:4410:2: iv_ruleIntLiteralString= ruleIntLiteralString EOF
            {
             newCompositeNode(grammarAccess.getIntLiteralStringRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleIntLiteralString=ruleIntLiteralString();

            state._fsp--;

             current =iv_ruleIntLiteralString.getText(); 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleIntLiteralString"


    // $ANTLR start "ruleIntLiteralString"
    // InternalConceptLanguage.g:4416:1: ruleIntLiteralString returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : ( (kw= '-' )? (this_INT_1= RULE_INT )? ) ;
    public final AntlrDatatypeRuleToken ruleIntLiteralString() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;
        Token this_INT_1=null;


        	enterRule();

        try {
            // InternalConceptLanguage.g:4422:2: ( ( (kw= '-' )? (this_INT_1= RULE_INT )? ) )
            // InternalConceptLanguage.g:4423:2: ( (kw= '-' )? (this_INT_1= RULE_INT )? )
            {
            // InternalConceptLanguage.g:4423:2: ( (kw= '-' )? (this_INT_1= RULE_INT )? )
            // InternalConceptLanguage.g:4424:3: (kw= '-' )? (this_INT_1= RULE_INT )?
            {
            // InternalConceptLanguage.g:4424:3: (kw= '-' )?
            int alt58=2;
            int LA58_0 = input.LA(1);

            if ( (LA58_0==58) ) {
                alt58=1;
            }
            switch (alt58) {
                case 1 :
                    // InternalConceptLanguage.g:4425:4: kw= '-'
                    {
                    kw=(Token)match(input,58,FOLLOW_58); 

                    				current.merge(kw);
                    				newLeafNode(kw, grammarAccess.getIntLiteralStringAccess().getHyphenMinusKeyword_0());
                    			

                    }
                    break;

            }

            // InternalConceptLanguage.g:4431:3: (this_INT_1= RULE_INT )?
            int alt59=2;
            int LA59_0 = input.LA(1);

            if ( (LA59_0==RULE_INT) ) {
                alt59=1;
            }
            switch (alt59) {
                case 1 :
                    // InternalConceptLanguage.g:4432:4: this_INT_1= RULE_INT
                    {
                    this_INT_1=(Token)match(input,RULE_INT,FOLLOW_2); 

                    				current.merge(this_INT_1);
                    			

                    				newLeafNode(this_INT_1, grammarAccess.getIntLiteralStringAccess().getINTTerminalRuleCall_1());
                    			

                    }
                    break;

            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleIntLiteralString"


    // $ANTLR start "entryRuleFloatLiteralString"
    // InternalConceptLanguage.g:4444:1: entryRuleFloatLiteralString returns [String current=null] : iv_ruleFloatLiteralString= ruleFloatLiteralString EOF ;
    public final String entryRuleFloatLiteralString() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleFloatLiteralString = null;


        try {
            // InternalConceptLanguage.g:4444:58: (iv_ruleFloatLiteralString= ruleFloatLiteralString EOF )
            // InternalConceptLanguage.g:4445:2: iv_ruleFloatLiteralString= ruleFloatLiteralString EOF
            {
             newCompositeNode(grammarAccess.getFloatLiteralStringRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleFloatLiteralString=ruleFloatLiteralString();

            state._fsp--;

             current =iv_ruleFloatLiteralString.getText(); 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleFloatLiteralString"


    // $ANTLR start "ruleFloatLiteralString"
    // InternalConceptLanguage.g:4451:1: ruleFloatLiteralString returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : ( (kw= '-' )? this_INT_1= RULE_INT (kw= '.' this_INT_3= RULE_INT )? ) ;
    public final AntlrDatatypeRuleToken ruleFloatLiteralString() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;
        Token this_INT_1=null;
        Token this_INT_3=null;


        	enterRule();

        try {
            // InternalConceptLanguage.g:4457:2: ( ( (kw= '-' )? this_INT_1= RULE_INT (kw= '.' this_INT_3= RULE_INT )? ) )
            // InternalConceptLanguage.g:4458:2: ( (kw= '-' )? this_INT_1= RULE_INT (kw= '.' this_INT_3= RULE_INT )? )
            {
            // InternalConceptLanguage.g:4458:2: ( (kw= '-' )? this_INT_1= RULE_INT (kw= '.' this_INT_3= RULE_INT )? )
            // InternalConceptLanguage.g:4459:3: (kw= '-' )? this_INT_1= RULE_INT (kw= '.' this_INT_3= RULE_INT )?
            {
            // InternalConceptLanguage.g:4459:3: (kw= '-' )?
            int alt60=2;
            int LA60_0 = input.LA(1);

            if ( (LA60_0==58) ) {
                alt60=1;
            }
            switch (alt60) {
                case 1 :
                    // InternalConceptLanguage.g:4460:4: kw= '-'
                    {
                    kw=(Token)match(input,58,FOLLOW_6); 

                    				current.merge(kw);
                    				newLeafNode(kw, grammarAccess.getFloatLiteralStringAccess().getHyphenMinusKeyword_0());
                    			

                    }
                    break;

            }

            this_INT_1=(Token)match(input,RULE_INT,FOLLOW_57); 

            			current.merge(this_INT_1);
            		

            			newLeafNode(this_INT_1, grammarAccess.getFloatLiteralStringAccess().getINTTerminalRuleCall_1());
            		
            // InternalConceptLanguage.g:4473:3: (kw= '.' this_INT_3= RULE_INT )?
            int alt61=2;
            int LA61_0 = input.LA(1);

            if ( (LA61_0==57) ) {
                alt61=1;
            }
            switch (alt61) {
                case 1 :
                    // InternalConceptLanguage.g:4474:4: kw= '.' this_INT_3= RULE_INT
                    {
                    kw=(Token)match(input,57,FOLLOW_6); 

                    				current.merge(kw);
                    				newLeafNode(kw, grammarAccess.getFloatLiteralStringAccess().getFullStopKeyword_2_0());
                    			
                    this_INT_3=(Token)match(input,RULE_INT,FOLLOW_2); 

                    				current.merge(this_INT_3);
                    			

                    				newLeafNode(this_INT_3, grammarAccess.getFloatLiteralStringAccess().getINTTerminalRuleCall_2_1());
                    			

                    }
                    break;

            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleFloatLiteralString"


    // $ANTLR start "entryRuleBooleanLiteralString"
    // InternalConceptLanguage.g:4491:1: entryRuleBooleanLiteralString returns [String current=null] : iv_ruleBooleanLiteralString= ruleBooleanLiteralString EOF ;
    public final String entryRuleBooleanLiteralString() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleBooleanLiteralString = null;


        try {
            // InternalConceptLanguage.g:4491:60: (iv_ruleBooleanLiteralString= ruleBooleanLiteralString EOF )
            // InternalConceptLanguage.g:4492:2: iv_ruleBooleanLiteralString= ruleBooleanLiteralString EOF
            {
             newCompositeNode(grammarAccess.getBooleanLiteralStringRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleBooleanLiteralString=ruleBooleanLiteralString();

            state._fsp--;

             current =iv_ruleBooleanLiteralString.getText(); 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleBooleanLiteralString"


    // $ANTLR start "ruleBooleanLiteralString"
    // InternalConceptLanguage.g:4498:1: ruleBooleanLiteralString returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (kw= 'true' | kw= 'false' ) ;
    public final AntlrDatatypeRuleToken ruleBooleanLiteralString() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;


        	enterRule();

        try {
            // InternalConceptLanguage.g:4504:2: ( (kw= 'true' | kw= 'false' ) )
            // InternalConceptLanguage.g:4505:2: (kw= 'true' | kw= 'false' )
            {
            // InternalConceptLanguage.g:4505:2: (kw= 'true' | kw= 'false' )
            int alt62=2;
            int LA62_0 = input.LA(1);

            if ( (LA62_0==59) ) {
                alt62=1;
            }
            else if ( (LA62_0==60) ) {
                alt62=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 62, 0, input);

                throw nvae;
            }
            switch (alt62) {
                case 1 :
                    // InternalConceptLanguage.g:4506:3: kw= 'true'
                    {
                    kw=(Token)match(input,59,FOLLOW_2); 

                    			current.merge(kw);
                    			newLeafNode(kw, grammarAccess.getBooleanLiteralStringAccess().getTrueKeyword_0());
                    		

                    }
                    break;
                case 2 :
                    // InternalConceptLanguage.g:4512:3: kw= 'false'
                    {
                    kw=(Token)match(input,60,FOLLOW_2); 

                    			current.merge(kw);
                    			newLeafNode(kw, grammarAccess.getBooleanLiteralStringAccess().getFalseKeyword_1());
                    		

                    }
                    break;

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleBooleanLiteralString"


    // $ANTLR start "entryRuleEString"
    // InternalConceptLanguage.g:4521:1: entryRuleEString returns [String current=null] : iv_ruleEString= ruleEString EOF ;
    public final String entryRuleEString() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleEString = null;


        try {
            // InternalConceptLanguage.g:4521:47: (iv_ruleEString= ruleEString EOF )
            // InternalConceptLanguage.g:4522:2: iv_ruleEString= ruleEString EOF
            {
             newCompositeNode(grammarAccess.getEStringRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleEString=ruleEString();

            state._fsp--;

             current =iv_ruleEString.getText(); 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleEString"


    // $ANTLR start "ruleEString"
    // InternalConceptLanguage.g:4528:1: ruleEString returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : this_STRING_0= RULE_STRING ;
    public final AntlrDatatypeRuleToken ruleEString() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_STRING_0=null;


        	enterRule();

        try {
            // InternalConceptLanguage.g:4534:2: (this_STRING_0= RULE_STRING )
            // InternalConceptLanguage.g:4535:2: this_STRING_0= RULE_STRING
            {
            this_STRING_0=(Token)match(input,RULE_STRING,FOLLOW_2); 

            		current.merge(this_STRING_0);
            	

            		newLeafNode(this_STRING_0, grammarAccess.getEStringAccess().getSTRINGTerminalRuleCall());
            	

            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleEString"


    // $ANTLR start "entryRuleQualifiedNameWithWildcard"
    // InternalConceptLanguage.g:4545:1: entryRuleQualifiedNameWithWildcard returns [String current=null] : iv_ruleQualifiedNameWithWildcard= ruleQualifiedNameWithWildcard EOF ;
    public final String entryRuleQualifiedNameWithWildcard() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleQualifiedNameWithWildcard = null;


        try {
            // InternalConceptLanguage.g:4545:65: (iv_ruleQualifiedNameWithWildcard= ruleQualifiedNameWithWildcard EOF )
            // InternalConceptLanguage.g:4546:2: iv_ruleQualifiedNameWithWildcard= ruleQualifiedNameWithWildcard EOF
            {
             newCompositeNode(grammarAccess.getQualifiedNameWithWildcardRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleQualifiedNameWithWildcard=ruleQualifiedNameWithWildcard();

            state._fsp--;

             current =iv_ruleQualifiedNameWithWildcard.getText(); 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleQualifiedNameWithWildcard"


    // $ANTLR start "ruleQualifiedNameWithWildcard"
    // InternalConceptLanguage.g:4552:1: ruleQualifiedNameWithWildcard returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (this_QualifiedName_0= ruleQualifiedName (kw= '.*' )? ) ;
    public final AntlrDatatypeRuleToken ruleQualifiedNameWithWildcard() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;
        AntlrDatatypeRuleToken this_QualifiedName_0 = null;



        	enterRule();

        try {
            // InternalConceptLanguage.g:4558:2: ( (this_QualifiedName_0= ruleQualifiedName (kw= '.*' )? ) )
            // InternalConceptLanguage.g:4559:2: (this_QualifiedName_0= ruleQualifiedName (kw= '.*' )? )
            {
            // InternalConceptLanguage.g:4559:2: (this_QualifiedName_0= ruleQualifiedName (kw= '.*' )? )
            // InternalConceptLanguage.g:4560:3: this_QualifiedName_0= ruleQualifiedName (kw= '.*' )?
            {

            			newCompositeNode(grammarAccess.getQualifiedNameWithWildcardAccess().getQualifiedNameParserRuleCall_0());
            		
            pushFollow(FOLLOW_59);
            this_QualifiedName_0=ruleQualifiedName();

            state._fsp--;


            			current.merge(this_QualifiedName_0);
            		

            			afterParserOrEnumRuleCall();
            		
            // InternalConceptLanguage.g:4570:3: (kw= '.*' )?
            int alt63=2;
            int LA63_0 = input.LA(1);

            if ( (LA63_0==61) ) {
                alt63=1;
            }
            switch (alt63) {
                case 1 :
                    // InternalConceptLanguage.g:4571:4: kw= '.*'
                    {
                    kw=(Token)match(input,61,FOLLOW_2); 

                    				current.merge(kw);
                    				newLeafNode(kw, grammarAccess.getQualifiedNameWithWildcardAccess().getFullStopAsteriskKeyword_1());
                    			

                    }
                    break;

            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleQualifiedNameWithWildcard"


    // $ANTLR start "entryRuleQualifiedName"
    // InternalConceptLanguage.g:4581:1: entryRuleQualifiedName returns [String current=null] : iv_ruleQualifiedName= ruleQualifiedName EOF ;
    public final String entryRuleQualifiedName() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleQualifiedName = null;


        try {
            // InternalConceptLanguage.g:4581:53: (iv_ruleQualifiedName= ruleQualifiedName EOF )
            // InternalConceptLanguage.g:4582:2: iv_ruleQualifiedName= ruleQualifiedName EOF
            {
             newCompositeNode(grammarAccess.getQualifiedNameRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleQualifiedName=ruleQualifiedName();

            state._fsp--;

             current =iv_ruleQualifiedName.getText(); 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleQualifiedName"


    // $ANTLR start "ruleQualifiedName"
    // InternalConceptLanguage.g:4588:1: ruleQualifiedName returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (this_ID_0= RULE_ID (kw= '.' this_ID_2= RULE_ID )* ) ;
    public final AntlrDatatypeRuleToken ruleQualifiedName() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_ID_0=null;
        Token kw=null;
        Token this_ID_2=null;


        	enterRule();

        try {
            // InternalConceptLanguage.g:4594:2: ( (this_ID_0= RULE_ID (kw= '.' this_ID_2= RULE_ID )* ) )
            // InternalConceptLanguage.g:4595:2: (this_ID_0= RULE_ID (kw= '.' this_ID_2= RULE_ID )* )
            {
            // InternalConceptLanguage.g:4595:2: (this_ID_0= RULE_ID (kw= '.' this_ID_2= RULE_ID )* )
            // InternalConceptLanguage.g:4596:3: this_ID_0= RULE_ID (kw= '.' this_ID_2= RULE_ID )*
            {
            this_ID_0=(Token)match(input,RULE_ID,FOLLOW_57); 

            			current.merge(this_ID_0);
            		

            			newLeafNode(this_ID_0, grammarAccess.getQualifiedNameAccess().getIDTerminalRuleCall_0());
            		
            // InternalConceptLanguage.g:4603:3: (kw= '.' this_ID_2= RULE_ID )*
            loop64:
            do {
                int alt64=2;
                int LA64_0 = input.LA(1);

                if ( (LA64_0==57) ) {
                    alt64=1;
                }


                switch (alt64) {
            	case 1 :
            	    // InternalConceptLanguage.g:4604:4: kw= '.' this_ID_2= RULE_ID
            	    {
            	    kw=(Token)match(input,57,FOLLOW_3); 

            	    				current.merge(kw);
            	    				newLeafNode(kw, grammarAccess.getQualifiedNameAccess().getFullStopKeyword_1_0());
            	    			
            	    this_ID_2=(Token)match(input,RULE_ID,FOLLOW_57); 

            	    				current.merge(this_ID_2);
            	    			

            	    				newLeafNode(this_ID_2, grammarAccess.getQualifiedNameAccess().getIDTerminalRuleCall_1_1());
            	    			

            	    }
            	    break;

            	default :
            	    break loop64;
                }
            } while (true);


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleQualifiedName"


    // $ANTLR start "ruleOperatorPlus"
    // InternalConceptLanguage.g:4621:1: ruleOperatorPlus returns [Enumerator current=null] : (enumLiteral_0= '+' ) ;
    public final Enumerator ruleOperatorPlus() throws RecognitionException {
        Enumerator current = null;

        Token enumLiteral_0=null;


        	enterRule();

        try {
            // InternalConceptLanguage.g:4627:2: ( (enumLiteral_0= '+' ) )
            // InternalConceptLanguage.g:4628:2: (enumLiteral_0= '+' )
            {
            // InternalConceptLanguage.g:4628:2: (enumLiteral_0= '+' )
            // InternalConceptLanguage.g:4629:3: enumLiteral_0= '+'
            {
            enumLiteral_0=(Token)match(input,62,FOLLOW_2); 

            			current = grammarAccess.getOperatorPlusAccess().getPLUSEnumLiteralDeclaration().getEnumLiteral().getInstance();
            			newLeafNode(enumLiteral_0, grammarAccess.getOperatorPlusAccess().getPLUSEnumLiteralDeclaration());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleOperatorPlus"


    // $ANTLR start "ruleOperatorMinus"
    // InternalConceptLanguage.g:4638:1: ruleOperatorMinus returns [Enumerator current=null] : (enumLiteral_0= '-' ) ;
    public final Enumerator ruleOperatorMinus() throws RecognitionException {
        Enumerator current = null;

        Token enumLiteral_0=null;


        	enterRule();

        try {
            // InternalConceptLanguage.g:4644:2: ( (enumLiteral_0= '-' ) )
            // InternalConceptLanguage.g:4645:2: (enumLiteral_0= '-' )
            {
            // InternalConceptLanguage.g:4645:2: (enumLiteral_0= '-' )
            // InternalConceptLanguage.g:4646:3: enumLiteral_0= '-'
            {
            enumLiteral_0=(Token)match(input,58,FOLLOW_2); 

            			current = grammarAccess.getOperatorMinusAccess().getMINUSEnumLiteralDeclaration().getEnumLiteral().getInstance();
            			newLeafNode(enumLiteral_0, grammarAccess.getOperatorMinusAccess().getMINUSEnumLiteralDeclaration());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleOperatorMinus"


    // $ANTLR start "ruleOperatorMultiply"
    // InternalConceptLanguage.g:4655:1: ruleOperatorMultiply returns [Enumerator current=null] : (enumLiteral_0= '*' ) ;
    public final Enumerator ruleOperatorMultiply() throws RecognitionException {
        Enumerator current = null;

        Token enumLiteral_0=null;


        	enterRule();

        try {
            // InternalConceptLanguage.g:4661:2: ( (enumLiteral_0= '*' ) )
            // InternalConceptLanguage.g:4662:2: (enumLiteral_0= '*' )
            {
            // InternalConceptLanguage.g:4662:2: (enumLiteral_0= '*' )
            // InternalConceptLanguage.g:4663:3: enumLiteral_0= '*'
            {
            enumLiteral_0=(Token)match(input,63,FOLLOW_2); 

            			current = grammarAccess.getOperatorMultiplyAccess().getMULTIPLYEnumLiteralDeclaration().getEnumLiteral().getInstance();
            			newLeafNode(enumLiteral_0, grammarAccess.getOperatorMultiplyAccess().getMULTIPLYEnumLiteralDeclaration());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleOperatorMultiply"


    // $ANTLR start "ruleOperatorDivide"
    // InternalConceptLanguage.g:4672:1: ruleOperatorDivide returns [Enumerator current=null] : (enumLiteral_0= '/' ) ;
    public final Enumerator ruleOperatorDivide() throws RecognitionException {
        Enumerator current = null;

        Token enumLiteral_0=null;


        	enterRule();

        try {
            // InternalConceptLanguage.g:4678:2: ( (enumLiteral_0= '/' ) )
            // InternalConceptLanguage.g:4679:2: (enumLiteral_0= '/' )
            {
            // InternalConceptLanguage.g:4679:2: (enumLiteral_0= '/' )
            // InternalConceptLanguage.g:4680:3: enumLiteral_0= '/'
            {
            enumLiteral_0=(Token)match(input,64,FOLLOW_2); 

            			current = grammarAccess.getOperatorDivideAccess().getDIVIDEEnumLiteralDeclaration().getEnumLiteral().getInstance();
            			newLeafNode(enumLiteral_0, grammarAccess.getOperatorDivideAccess().getDIVIDEEnumLiteralDeclaration());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleOperatorDivide"


    // $ANTLR start "ruleOperatorPower"
    // InternalConceptLanguage.g:4689:1: ruleOperatorPower returns [Enumerator current=null] : (enumLiteral_0= '^' ) ;
    public final Enumerator ruleOperatorPower() throws RecognitionException {
        Enumerator current = null;

        Token enumLiteral_0=null;


        	enterRule();

        try {
            // InternalConceptLanguage.g:4695:2: ( (enumLiteral_0= '^' ) )
            // InternalConceptLanguage.g:4696:2: (enumLiteral_0= '^' )
            {
            // InternalConceptLanguage.g:4696:2: (enumLiteral_0= '^' )
            // InternalConceptLanguage.g:4697:3: enumLiteral_0= '^'
            {
            enumLiteral_0=(Token)match(input,65,FOLLOW_2); 

            			current = grammarAccess.getOperatorPowerAccess().getPOWEREnumLiteralDeclaration().getEnumLiteral().getInstance();
            			newLeafNode(enumLiteral_0, grammarAccess.getOperatorPowerAccess().getPOWEREnumLiteralDeclaration());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleOperatorPower"


    // $ANTLR start "ruleOperatorCos"
    // InternalConceptLanguage.g:4706:1: ruleOperatorCos returns [Enumerator current=null] : (enumLiteral_0= 'cos' ) ;
    public final Enumerator ruleOperatorCos() throws RecognitionException {
        Enumerator current = null;

        Token enumLiteral_0=null;


        	enterRule();

        try {
            // InternalConceptLanguage.g:4712:2: ( (enumLiteral_0= 'cos' ) )
            // InternalConceptLanguage.g:4713:2: (enumLiteral_0= 'cos' )
            {
            // InternalConceptLanguage.g:4713:2: (enumLiteral_0= 'cos' )
            // InternalConceptLanguage.g:4714:3: enumLiteral_0= 'cos'
            {
            enumLiteral_0=(Token)match(input,66,FOLLOW_2); 

            			current = grammarAccess.getOperatorCosAccess().getCOSEnumLiteralDeclaration().getEnumLiteral().getInstance();
            			newLeafNode(enumLiteral_0, grammarAccess.getOperatorCosAccess().getCOSEnumLiteralDeclaration());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleOperatorCos"


    // $ANTLR start "ruleOperatorSin"
    // InternalConceptLanguage.g:4723:1: ruleOperatorSin returns [Enumerator current=null] : (enumLiteral_0= 'sin' ) ;
    public final Enumerator ruleOperatorSin() throws RecognitionException {
        Enumerator current = null;

        Token enumLiteral_0=null;


        	enterRule();

        try {
            // InternalConceptLanguage.g:4729:2: ( (enumLiteral_0= 'sin' ) )
            // InternalConceptLanguage.g:4730:2: (enumLiteral_0= 'sin' )
            {
            // InternalConceptLanguage.g:4730:2: (enumLiteral_0= 'sin' )
            // InternalConceptLanguage.g:4731:3: enumLiteral_0= 'sin'
            {
            enumLiteral_0=(Token)match(input,67,FOLLOW_2); 

            			current = grammarAccess.getOperatorSinAccess().getSINEnumLiteralDeclaration().getEnumLiteral().getInstance();
            			newLeafNode(enumLiteral_0, grammarAccess.getOperatorSinAccess().getSINEnumLiteralDeclaration());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleOperatorSin"


    // $ANTLR start "ruleOperatorTan"
    // InternalConceptLanguage.g:4740:1: ruleOperatorTan returns [Enumerator current=null] : (enumLiteral_0= 'tan' ) ;
    public final Enumerator ruleOperatorTan() throws RecognitionException {
        Enumerator current = null;

        Token enumLiteral_0=null;


        	enterRule();

        try {
            // InternalConceptLanguage.g:4746:2: ( (enumLiteral_0= 'tan' ) )
            // InternalConceptLanguage.g:4747:2: (enumLiteral_0= 'tan' )
            {
            // InternalConceptLanguage.g:4747:2: (enumLiteral_0= 'tan' )
            // InternalConceptLanguage.g:4748:3: enumLiteral_0= 'tan'
            {
            enumLiteral_0=(Token)match(input,68,FOLLOW_2); 

            			current = grammarAccess.getOperatorTanAccess().getTANEnumLiteralDeclaration().getEnumLiteral().getInstance();
            			newLeafNode(enumLiteral_0, grammarAccess.getOperatorTanAccess().getTANEnumLiteralDeclaration());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleOperatorTan"


    // $ANTLR start "ruleOperatorAtan"
    // InternalConceptLanguage.g:4757:1: ruleOperatorAtan returns [Enumerator current=null] : (enumLiteral_0= 'atan' ) ;
    public final Enumerator ruleOperatorAtan() throws RecognitionException {
        Enumerator current = null;

        Token enumLiteral_0=null;


        	enterRule();

        try {
            // InternalConceptLanguage.g:4763:2: ( (enumLiteral_0= 'atan' ) )
            // InternalConceptLanguage.g:4764:2: (enumLiteral_0= 'atan' )
            {
            // InternalConceptLanguage.g:4764:2: (enumLiteral_0= 'atan' )
            // InternalConceptLanguage.g:4765:3: enumLiteral_0= 'atan'
            {
            enumLiteral_0=(Token)match(input,69,FOLLOW_2); 

            			current = grammarAccess.getOperatorAtanAccess().getATANEnumLiteralDeclaration().getEnumLiteral().getInstance();
            			newLeafNode(enumLiteral_0, grammarAccess.getOperatorAtanAccess().getATANEnumLiteralDeclaration());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleOperatorAtan"


    // $ANTLR start "ruleOperatorAcos"
    // InternalConceptLanguage.g:4774:1: ruleOperatorAcos returns [Enumerator current=null] : (enumLiteral_0= 'acos' ) ;
    public final Enumerator ruleOperatorAcos() throws RecognitionException {
        Enumerator current = null;

        Token enumLiteral_0=null;


        	enterRule();

        try {
            // InternalConceptLanguage.g:4780:2: ( (enumLiteral_0= 'acos' ) )
            // InternalConceptLanguage.g:4781:2: (enumLiteral_0= 'acos' )
            {
            // InternalConceptLanguage.g:4781:2: (enumLiteral_0= 'acos' )
            // InternalConceptLanguage.g:4782:3: enumLiteral_0= 'acos'
            {
            enumLiteral_0=(Token)match(input,70,FOLLOW_2); 

            			current = grammarAccess.getOperatorAcosAccess().getACOSEnumLiteralDeclaration().getEnumLiteral().getInstance();
            			newLeafNode(enumLiteral_0, grammarAccess.getOperatorAcosAccess().getACOSEnumLiteralDeclaration());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleOperatorAcos"


    // $ANTLR start "ruleOperatorAsin"
    // InternalConceptLanguage.g:4791:1: ruleOperatorAsin returns [Enumerator current=null] : (enumLiteral_0= 'asin' ) ;
    public final Enumerator ruleOperatorAsin() throws RecognitionException {
        Enumerator current = null;

        Token enumLiteral_0=null;


        	enterRule();

        try {
            // InternalConceptLanguage.g:4797:2: ( (enumLiteral_0= 'asin' ) )
            // InternalConceptLanguage.g:4798:2: (enumLiteral_0= 'asin' )
            {
            // InternalConceptLanguage.g:4798:2: (enumLiteral_0= 'asin' )
            // InternalConceptLanguage.g:4799:3: enumLiteral_0= 'asin'
            {
            enumLiteral_0=(Token)match(input,71,FOLLOW_2); 

            			current = grammarAccess.getOperatorAsinAccess().getASINEnumLiteralDeclaration().getEnumLiteral().getInstance();
            			newLeafNode(enumLiteral_0, grammarAccess.getOperatorAsinAccess().getASINEnumLiteralDeclaration());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleOperatorAsin"


    // $ANTLR start "ruleOperatorSqrt"
    // InternalConceptLanguage.g:4808:1: ruleOperatorSqrt returns [Enumerator current=null] : (enumLiteral_0= 'sqrt' ) ;
    public final Enumerator ruleOperatorSqrt() throws RecognitionException {
        Enumerator current = null;

        Token enumLiteral_0=null;


        	enterRule();

        try {
            // InternalConceptLanguage.g:4814:2: ( (enumLiteral_0= 'sqrt' ) )
            // InternalConceptLanguage.g:4815:2: (enumLiteral_0= 'sqrt' )
            {
            // InternalConceptLanguage.g:4815:2: (enumLiteral_0= 'sqrt' )
            // InternalConceptLanguage.g:4816:3: enumLiteral_0= 'sqrt'
            {
            enumLiteral_0=(Token)match(input,72,FOLLOW_2); 

            			current = grammarAccess.getOperatorSqrtAccess().getSQRTEnumLiteralDeclaration().getEnumLiteral().getInstance();
            			newLeafNode(enumLiteral_0, grammarAccess.getOperatorSqrtAccess().getSQRTEnumLiteralDeclaration());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleOperatorSqrt"


    // $ANTLR start "ruleOperatorLog"
    // InternalConceptLanguage.g:4825:1: ruleOperatorLog returns [Enumerator current=null] : (enumLiteral_0= 'log' ) ;
    public final Enumerator ruleOperatorLog() throws RecognitionException {
        Enumerator current = null;

        Token enumLiteral_0=null;


        	enterRule();

        try {
            // InternalConceptLanguage.g:4831:2: ( (enumLiteral_0= 'log' ) )
            // InternalConceptLanguage.g:4832:2: (enumLiteral_0= 'log' )
            {
            // InternalConceptLanguage.g:4832:2: (enumLiteral_0= 'log' )
            // InternalConceptLanguage.g:4833:3: enumLiteral_0= 'log'
            {
            enumLiteral_0=(Token)match(input,73,FOLLOW_2); 

            			current = grammarAccess.getOperatorLogAccess().getLOGEnumLiteralDeclaration().getEnumLiteral().getInstance();
            			newLeafNode(enumLiteral_0, grammarAccess.getOperatorLogAccess().getLOGEnumLiteralDeclaration());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleOperatorLog"


    // $ANTLR start "ruleOperatorLn"
    // InternalConceptLanguage.g:4842:1: ruleOperatorLn returns [Enumerator current=null] : (enumLiteral_0= 'ln' ) ;
    public final Enumerator ruleOperatorLn() throws RecognitionException {
        Enumerator current = null;

        Token enumLiteral_0=null;


        	enterRule();

        try {
            // InternalConceptLanguage.g:4848:2: ( (enumLiteral_0= 'ln' ) )
            // InternalConceptLanguage.g:4849:2: (enumLiteral_0= 'ln' )
            {
            // InternalConceptLanguage.g:4849:2: (enumLiteral_0= 'ln' )
            // InternalConceptLanguage.g:4850:3: enumLiteral_0= 'ln'
            {
            enumLiteral_0=(Token)match(input,74,FOLLOW_2); 

            			current = grammarAccess.getOperatorLnAccess().getLNEnumLiteralDeclaration().getEnumLiteral().getInstance();
            			newLeafNode(enumLiteral_0, grammarAccess.getOperatorLnAccess().getLNEnumLiteralDeclaration());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleOperatorLn"


    // $ANTLR start "ruleOperatorExp"
    // InternalConceptLanguage.g:4859:1: ruleOperatorExp returns [Enumerator current=null] : (enumLiteral_0= 'exp' ) ;
    public final Enumerator ruleOperatorExp() throws RecognitionException {
        Enumerator current = null;

        Token enumLiteral_0=null;


        	enterRule();

        try {
            // InternalConceptLanguage.g:4865:2: ( (enumLiteral_0= 'exp' ) )
            // InternalConceptLanguage.g:4866:2: (enumLiteral_0= 'exp' )
            {
            // InternalConceptLanguage.g:4866:2: (enumLiteral_0= 'exp' )
            // InternalConceptLanguage.g:4867:3: enumLiteral_0= 'exp'
            {
            enumLiteral_0=(Token)match(input,75,FOLLOW_2); 

            			current = grammarAccess.getOperatorExpAccess().getEXPEnumLiteralDeclaration().getEnumLiteral().getInstance();
            			newLeafNode(enumLiteral_0, grammarAccess.getOperatorExpAccess().getEXPEnumLiteralDeclaration());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleOperatorExp"


    // $ANTLR start "ruleOperatorLd"
    // InternalConceptLanguage.g:4876:1: ruleOperatorLd returns [Enumerator current=null] : (enumLiteral_0= 'ld' ) ;
    public final Enumerator ruleOperatorLd() throws RecognitionException {
        Enumerator current = null;

        Token enumLiteral_0=null;


        	enterRule();

        try {
            // InternalConceptLanguage.g:4882:2: ( (enumLiteral_0= 'ld' ) )
            // InternalConceptLanguage.g:4883:2: (enumLiteral_0= 'ld' )
            {
            // InternalConceptLanguage.g:4883:2: (enumLiteral_0= 'ld' )
            // InternalConceptLanguage.g:4884:3: enumLiteral_0= 'ld'
            {
            enumLiteral_0=(Token)match(input,76,FOLLOW_2); 

            			current = grammarAccess.getOperatorLdAccess().getLDEnumLiteralDeclaration().getEnumLiteral().getInstance();
            			newLeafNode(enumLiteral_0, grammarAccess.getOperatorLdAccess().getLDEnumLiteralDeclaration());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleOperatorLd"

    // Delegated rules


 

    public static final BitSet FOLLOW_1 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_2 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_3 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_4 = new BitSet(new long[]{0x000000000001F000L});
    public static final BitSet FOLLOW_5 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_6 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_7 = new BitSet(new long[]{0x0000002480060000L});
    public static final BitSet FOLLOW_8 = new BitSet(new long[]{0x0000000480060000L});
    public static final BitSet FOLLOW_9 = new BitSet(new long[]{0x0000000480020000L});
    public static final BitSet FOLLOW_10 = new BitSet(new long[]{0x0000000400020000L});
    public static final BitSet FOLLOW_11 = new BitSet(new long[]{0x0000000000094000L});
    public static final BitSet FOLLOW_12 = new BitSet(new long[]{0x0000000050520000L});
    public static final BitSet FOLLOW_13 = new BitSet(new long[]{0x0000000000200000L});
    public static final BitSet FOLLOW_14 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_15 = new BitSet(new long[]{0x0000000009000000L});
    public static final BitSet FOLLOW_16 = new BitSet(new long[]{0x0000000006000000L});
    public static final BitSet FOLLOW_17 = new BitSet(new long[]{0x0000000020000000L});
    public static final BitSet FOLLOW_18 = new BitSet(new long[]{0x0000000000014000L});
    public static final BitSet FOLLOW_19 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_20 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_21 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_22 = new BitSet(new long[]{0x0000000050020000L});
    public static final BitSet FOLLOW_23 = new BitSet(new long[]{0x0000000040020000L});
    public static final BitSet FOLLOW_24 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_25 = new BitSet(new long[]{0x0000000800094000L});
    public static final BitSet FOLLOW_26 = new BitSet(new long[]{0x001E7A1250020000L});
    public static final BitSet FOLLOW_27 = new BitSet(new long[]{0x001E7A0200020000L});
    public static final BitSet FOLLOW_28 = new BitSet(new long[]{0x0018000000020000L});
    public static final BitSet FOLLOW_29 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_30 = new BitSet(new long[]{0x0000004001000000L});
    public static final BitSet FOLLOW_31 = new BitSet(new long[]{0x0000004000000000L});
    public static final BitSet FOLLOW_32 = new BitSet(new long[]{0x0000000400000000L});
    public static final BitSet FOLLOW_33 = new BitSet(new long[]{0x0000018000204000L});
    public static final BitSet FOLLOW_34 = new BitSet(new long[]{0x0000058001204000L});
    public static final BitSet FOLLOW_35 = new BitSet(new long[]{0x0000058000204000L});
    public static final BitSet FOLLOW_36 = new BitSet(new long[]{0x0400058000204020L});
    public static final BitSet FOLLOW_37 = new BitSet(new long[]{0x0400000000000020L});
    public static final BitSet FOLLOW_38 = new BitSet(new long[]{0x0000040001204000L});
    public static final BitSet FOLLOW_39 = new BitSet(new long[]{0x0000040000204000L});
    public static final BitSet FOLLOW_40 = new BitSet(new long[]{0x1800000000000000L});
    public static final BitSet FOLLOW_41 = new BitSet(new long[]{0x0000858001004000L});
    public static final BitSet FOLLOW_42 = new BitSet(new long[]{0x0000858000004000L});
    public static final BitSet FOLLOW_43 = new BitSet(new long[]{0x0000858000204000L});
    public static final BitSet FOLLOW_44 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_45 = new BitSet(new long[]{0x0001000000000000L});
    public static final BitSet FOLLOW_46 = new BitSet(new long[]{0x0400000000000060L});
    public static final BitSet FOLLOW_47 = new BitSet(new long[]{0x0000000000204000L});
    public static final BitSet FOLLOW_48 = new BitSet(new long[]{0x0000000001204000L});
    public static final BitSet FOLLOW_49 = new BitSet(new long[]{0x05A0000000000030L,0x0000000000001FFCL});
    public static final BitSet FOLLOW_50 = new BitSet(new long[]{0x4400000000000002L});
    public static final BitSet FOLLOW_51 = new BitSet(new long[]{0x8000000000000002L,0x0000000000000001L});
    public static final BitSet FOLLOW_52 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000002L});
    public static final BitSet FOLLOW_53 = new BitSet(new long[]{0x0020000000000000L});
    public static final BitSet FOLLOW_54 = new BitSet(new long[]{0x0040000000000000L});
    public static final BitSet FOLLOW_55 = new BitSet(new long[]{0x0040000002000000L});
    public static final BitSet FOLLOW_56 = new BitSet(new long[]{0x0000000002020000L});
    public static final BitSet FOLLOW_57 = new BitSet(new long[]{0x0200000000000002L});
    public static final BitSet FOLLOW_58 = new BitSet(new long[]{0x0000000000000022L});
    public static final BitSet FOLLOW_59 = new BitSet(new long[]{0x2000000000000002L});

}
