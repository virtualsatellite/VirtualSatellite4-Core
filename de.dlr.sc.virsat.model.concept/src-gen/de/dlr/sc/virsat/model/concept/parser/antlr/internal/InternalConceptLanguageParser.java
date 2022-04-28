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
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "RULE_ID", "RULE_INT", "RULE_STRING", "RULE_ML_COMMENT", "RULE_SL_COMMENT", "RULE_WS", "RULE_ANY_OTHER", "'Concept'", "'displayname'", "'version'", "'beta'", "'description'", "'hasDMF'", "'{'", "'}'", "'StructuralElement'", "'shortname'", "'IsRootStructuralElement'", "';'", "'Inherits'", "'From'", "'['", "','", "']'", "'All'", "'Applicable'", "'For'", "'Cardinality'", "'GeneralRelation'", "'Referenced'", "'Type'", "'Category'", "'extends'", "'IsAbstract'", "'IsVerification'", "'Import'", "'EImport'", "'genModel'", "'of'", "'quantityKind'", "'verification'", "'unit'", "'IntProperty'", "'default'", "'FloatProperty'", "'StringProperty'", "'BooleanProperty'", "'EnumProperty'", "'values'", "'='", "'Reference'", "'EReference'", "'Resource'", "'Ref:'", "'Calc:'", "'('", "')'", "'pi'", "'e'", "'.'", "'-'", "'true'", "'false'", "'.*'", "'+'", "'*'", "'/'", "'^'", "'cos'", "'sin'", "'tan'", "'atan'", "'acos'", "'asin'", "'sqrt'", "'log'", "'ln'", "'exp'", "'ld'"
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
    public static final int T__77=77;
    public static final int T__34=34;
    public static final int T__78=78;
    public static final int T__35=35;
    public static final int T__79=79;
    public static final int T__36=36;
    public static final int T__73=73;
    public static final int EOF=-1;
    public static final int T__30=30;
    public static final int T__74=74;
    public static final int T__31=31;
    public static final int T__75=75;
    public static final int T__32=32;
    public static final int T__76=76;
    public static final int T__80=80;
    public static final int T__81=81;
    public static final int T__82=82;
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
    // InternalConceptLanguage.g:71:1: entryRuleConcept returns [EObject current=null] : iv_ruleConcept= ruleConcept EOF ;
    public final EObject entryRuleConcept() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleConcept = null;


        try {
            // InternalConceptLanguage.g:71:48: (iv_ruleConcept= ruleConcept EOF )
            // InternalConceptLanguage.g:72:2: iv_ruleConcept= ruleConcept EOF
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
    // InternalConceptLanguage.g:78:1: ruleConcept returns [EObject current=null] : ( () otherlv_1= 'Concept' ( (lv_name_2_0= ruleQualifiedName ) ) ( ( ( ( ({...}? => ( ({...}? => (otherlv_4= 'displayname' ( (lv_displayName_5_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_6= 'version' ( (lv_version_7_0= ruleVersion ) ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_beta_8_0= 'beta' ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_9= 'description' ( (lv_description_10_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_DMF_11_0= 'hasDMF' ) ) ) ) ) )* ) ) ) otherlv_12= '{' ( ( ( ( ({...}? => ( ({...}? => ( (lv_imports_14_0= ruleConceptImport ) ) )+ ) ) | ({...}? => ( ({...}? => ( (lv_ecoreImports_15_0= ruleEcoreImport ) ) )+ ) ) )* ) ) ) ( (lv_structuralElements_16_0= ruleStructuralElement ) )* ( (lv_relations_17_0= ruleARelation ) )* ( (lv_categories_18_0= ruleCategory ) )* otherlv_19= '}' ) ;
    public final EObject ruleConcept() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_4=null;
        Token otherlv_6=null;
        Token lv_beta_8_0=null;
        Token otherlv_9=null;
        Token lv_DMF_11_0=null;
        Token otherlv_12=null;
        Token otherlv_19=null;
        AntlrDatatypeRuleToken lv_name_2_0 = null;

        AntlrDatatypeRuleToken lv_displayName_5_0 = null;

        AntlrDatatypeRuleToken lv_version_7_0 = null;

        AntlrDatatypeRuleToken lv_description_10_0 = null;

        EObject lv_imports_14_0 = null;

        EObject lv_ecoreImports_15_0 = null;

        EObject lv_structuralElements_16_0 = null;

        EObject lv_relations_17_0 = null;

        EObject lv_categories_18_0 = null;



        	enterRule();

        try {
            // InternalConceptLanguage.g:84:2: ( ( () otherlv_1= 'Concept' ( (lv_name_2_0= ruleQualifiedName ) ) ( ( ( ( ({...}? => ( ({...}? => (otherlv_4= 'displayname' ( (lv_displayName_5_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_6= 'version' ( (lv_version_7_0= ruleVersion ) ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_beta_8_0= 'beta' ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_9= 'description' ( (lv_description_10_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_DMF_11_0= 'hasDMF' ) ) ) ) ) )* ) ) ) otherlv_12= '{' ( ( ( ( ({...}? => ( ({...}? => ( (lv_imports_14_0= ruleConceptImport ) ) )+ ) ) | ({...}? => ( ({...}? => ( (lv_ecoreImports_15_0= ruleEcoreImport ) ) )+ ) ) )* ) ) ) ( (lv_structuralElements_16_0= ruleStructuralElement ) )* ( (lv_relations_17_0= ruleARelation ) )* ( (lv_categories_18_0= ruleCategory ) )* otherlv_19= '}' ) )
            // InternalConceptLanguage.g:85:2: ( () otherlv_1= 'Concept' ( (lv_name_2_0= ruleQualifiedName ) ) ( ( ( ( ({...}? => ( ({...}? => (otherlv_4= 'displayname' ( (lv_displayName_5_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_6= 'version' ( (lv_version_7_0= ruleVersion ) ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_beta_8_0= 'beta' ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_9= 'description' ( (lv_description_10_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_DMF_11_0= 'hasDMF' ) ) ) ) ) )* ) ) ) otherlv_12= '{' ( ( ( ( ({...}? => ( ({...}? => ( (lv_imports_14_0= ruleConceptImport ) ) )+ ) ) | ({...}? => ( ({...}? => ( (lv_ecoreImports_15_0= ruleEcoreImport ) ) )+ ) ) )* ) ) ) ( (lv_structuralElements_16_0= ruleStructuralElement ) )* ( (lv_relations_17_0= ruleARelation ) )* ( (lv_categories_18_0= ruleCategory ) )* otherlv_19= '}' )
            {
            // InternalConceptLanguage.g:85:2: ( () otherlv_1= 'Concept' ( (lv_name_2_0= ruleQualifiedName ) ) ( ( ( ( ({...}? => ( ({...}? => (otherlv_4= 'displayname' ( (lv_displayName_5_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_6= 'version' ( (lv_version_7_0= ruleVersion ) ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_beta_8_0= 'beta' ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_9= 'description' ( (lv_description_10_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_DMF_11_0= 'hasDMF' ) ) ) ) ) )* ) ) ) otherlv_12= '{' ( ( ( ( ({...}? => ( ({...}? => ( (lv_imports_14_0= ruleConceptImport ) ) )+ ) ) | ({...}? => ( ({...}? => ( (lv_ecoreImports_15_0= ruleEcoreImport ) ) )+ ) ) )* ) ) ) ( (lv_structuralElements_16_0= ruleStructuralElement ) )* ( (lv_relations_17_0= ruleARelation ) )* ( (lv_categories_18_0= ruleCategory ) )* otherlv_19= '}' )
            // InternalConceptLanguage.g:86:3: () otherlv_1= 'Concept' ( (lv_name_2_0= ruleQualifiedName ) ) ( ( ( ( ({...}? => ( ({...}? => (otherlv_4= 'displayname' ( (lv_displayName_5_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_6= 'version' ( (lv_version_7_0= ruleVersion ) ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_beta_8_0= 'beta' ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_9= 'description' ( (lv_description_10_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_DMF_11_0= 'hasDMF' ) ) ) ) ) )* ) ) ) otherlv_12= '{' ( ( ( ( ({...}? => ( ({...}? => ( (lv_imports_14_0= ruleConceptImport ) ) )+ ) ) | ({...}? => ( ({...}? => ( (lv_ecoreImports_15_0= ruleEcoreImport ) ) )+ ) ) )* ) ) ) ( (lv_structuralElements_16_0= ruleStructuralElement ) )* ( (lv_relations_17_0= ruleARelation ) )* ( (lv_categories_18_0= ruleCategory ) )* otherlv_19= '}'
            {
            // InternalConceptLanguage.g:86:3: ()
            // InternalConceptLanguage.g:87:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getConceptAccess().getConceptAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,11,FOLLOW_3); 

            			newLeafNode(otherlv_1, grammarAccess.getConceptAccess().getConceptKeyword_1());
            		
            // InternalConceptLanguage.g:97:3: ( (lv_name_2_0= ruleQualifiedName ) )
            // InternalConceptLanguage.g:98:4: (lv_name_2_0= ruleQualifiedName )
            {
            // InternalConceptLanguage.g:98:4: (lv_name_2_0= ruleQualifiedName )
            // InternalConceptLanguage.g:99:5: lv_name_2_0= ruleQualifiedName
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

            // InternalConceptLanguage.g:116:3: ( ( ( ( ({...}? => ( ({...}? => (otherlv_4= 'displayname' ( (lv_displayName_5_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_6= 'version' ( (lv_version_7_0= ruleVersion ) ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_beta_8_0= 'beta' ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_9= 'description' ( (lv_description_10_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_DMF_11_0= 'hasDMF' ) ) ) ) ) )* ) ) )
            // InternalConceptLanguage.g:117:4: ( ( ( ({...}? => ( ({...}? => (otherlv_4= 'displayname' ( (lv_displayName_5_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_6= 'version' ( (lv_version_7_0= ruleVersion ) ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_beta_8_0= 'beta' ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_9= 'description' ( (lv_description_10_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_DMF_11_0= 'hasDMF' ) ) ) ) ) )* ) )
            {
            // InternalConceptLanguage.g:117:4: ( ( ( ({...}? => ( ({...}? => (otherlv_4= 'displayname' ( (lv_displayName_5_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_6= 'version' ( (lv_version_7_0= ruleVersion ) ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_beta_8_0= 'beta' ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_9= 'description' ( (lv_description_10_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_DMF_11_0= 'hasDMF' ) ) ) ) ) )* ) )
            // InternalConceptLanguage.g:118:5: ( ( ({...}? => ( ({...}? => (otherlv_4= 'displayname' ( (lv_displayName_5_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_6= 'version' ( (lv_version_7_0= ruleVersion ) ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_beta_8_0= 'beta' ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_9= 'description' ( (lv_description_10_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_DMF_11_0= 'hasDMF' ) ) ) ) ) )* )
            {
             
            				  getUnorderedGroupHelper().enter(grammarAccess.getConceptAccess().getUnorderedGroup_3());
            				
            // InternalConceptLanguage.g:121:5: ( ( ({...}? => ( ({...}? => (otherlv_4= 'displayname' ( (lv_displayName_5_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_6= 'version' ( (lv_version_7_0= ruleVersion ) ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_beta_8_0= 'beta' ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_9= 'description' ( (lv_description_10_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_DMF_11_0= 'hasDMF' ) ) ) ) ) )* )
            // InternalConceptLanguage.g:122:6: ( ({...}? => ( ({...}? => (otherlv_4= 'displayname' ( (lv_displayName_5_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_6= 'version' ( (lv_version_7_0= ruleVersion ) ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_beta_8_0= 'beta' ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_9= 'description' ( (lv_description_10_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_DMF_11_0= 'hasDMF' ) ) ) ) ) )*
            {
            // InternalConceptLanguage.g:122:6: ( ({...}? => ( ({...}? => (otherlv_4= 'displayname' ( (lv_displayName_5_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_6= 'version' ( (lv_version_7_0= ruleVersion ) ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_beta_8_0= 'beta' ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_9= 'description' ( (lv_description_10_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_DMF_11_0= 'hasDMF' ) ) ) ) ) )*
            loop1:
            do {
                int alt1=6;
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
                else if ( LA1_0 == 16 && getUnorderedGroupHelper().canSelect(grammarAccess.getConceptAccess().getUnorderedGroup_3(), 4) ) {
                    alt1=5;
                }


                switch (alt1) {
            	case 1 :
            	    // InternalConceptLanguage.g:123:4: ({...}? => ( ({...}? => (otherlv_4= 'displayname' ( (lv_displayName_5_0= ruleEString ) ) ) ) ) )
            	    {
            	    // InternalConceptLanguage.g:123:4: ({...}? => ( ({...}? => (otherlv_4= 'displayname' ( (lv_displayName_5_0= ruleEString ) ) ) ) ) )
            	    // InternalConceptLanguage.g:124:5: {...}? => ( ({...}? => (otherlv_4= 'displayname' ( (lv_displayName_5_0= ruleEString ) ) ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getConceptAccess().getUnorderedGroup_3(), 0) ) {
            	        throw new FailedPredicateException(input, "ruleConcept", "getUnorderedGroupHelper().canSelect(grammarAccess.getConceptAccess().getUnorderedGroup_3(), 0)");
            	    }
            	    // InternalConceptLanguage.g:124:104: ( ({...}? => (otherlv_4= 'displayname' ( (lv_displayName_5_0= ruleEString ) ) ) ) )
            	    // InternalConceptLanguage.g:125:6: ({...}? => (otherlv_4= 'displayname' ( (lv_displayName_5_0= ruleEString ) ) ) )
            	    {

            	    						getUnorderedGroupHelper().select(grammarAccess.getConceptAccess().getUnorderedGroup_3(), 0);
            	    					
            	    // InternalConceptLanguage.g:128:9: ({...}? => (otherlv_4= 'displayname' ( (lv_displayName_5_0= ruleEString ) ) ) )
            	    // InternalConceptLanguage.g:128:10: {...}? => (otherlv_4= 'displayname' ( (lv_displayName_5_0= ruleEString ) ) )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleConcept", "true");
            	    }
            	    // InternalConceptLanguage.g:128:19: (otherlv_4= 'displayname' ( (lv_displayName_5_0= ruleEString ) ) )
            	    // InternalConceptLanguage.g:128:20: otherlv_4= 'displayname' ( (lv_displayName_5_0= ruleEString ) )
            	    {
            	    otherlv_4=(Token)match(input,12,FOLLOW_5); 

            	    									newLeafNode(otherlv_4, grammarAccess.getConceptAccess().getDisplaynameKeyword_3_0_0());
            	    								
            	    // InternalConceptLanguage.g:132:9: ( (lv_displayName_5_0= ruleEString ) )
            	    // InternalConceptLanguage.g:133:10: (lv_displayName_5_0= ruleEString )
            	    {
            	    // InternalConceptLanguage.g:133:10: (lv_displayName_5_0= ruleEString )
            	    // InternalConceptLanguage.g:134:11: lv_displayName_5_0= ruleEString
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
            	    // InternalConceptLanguage.g:157:4: ({...}? => ( ({...}? => (otherlv_6= 'version' ( (lv_version_7_0= ruleVersion ) ) ) ) ) )
            	    {
            	    // InternalConceptLanguage.g:157:4: ({...}? => ( ({...}? => (otherlv_6= 'version' ( (lv_version_7_0= ruleVersion ) ) ) ) ) )
            	    // InternalConceptLanguage.g:158:5: {...}? => ( ({...}? => (otherlv_6= 'version' ( (lv_version_7_0= ruleVersion ) ) ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getConceptAccess().getUnorderedGroup_3(), 1) ) {
            	        throw new FailedPredicateException(input, "ruleConcept", "getUnorderedGroupHelper().canSelect(grammarAccess.getConceptAccess().getUnorderedGroup_3(), 1)");
            	    }
            	    // InternalConceptLanguage.g:158:104: ( ({...}? => (otherlv_6= 'version' ( (lv_version_7_0= ruleVersion ) ) ) ) )
            	    // InternalConceptLanguage.g:159:6: ({...}? => (otherlv_6= 'version' ( (lv_version_7_0= ruleVersion ) ) ) )
            	    {

            	    						getUnorderedGroupHelper().select(grammarAccess.getConceptAccess().getUnorderedGroup_3(), 1);
            	    					
            	    // InternalConceptLanguage.g:162:9: ({...}? => (otherlv_6= 'version' ( (lv_version_7_0= ruleVersion ) ) ) )
            	    // InternalConceptLanguage.g:162:10: {...}? => (otherlv_6= 'version' ( (lv_version_7_0= ruleVersion ) ) )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleConcept", "true");
            	    }
            	    // InternalConceptLanguage.g:162:19: (otherlv_6= 'version' ( (lv_version_7_0= ruleVersion ) ) )
            	    // InternalConceptLanguage.g:162:20: otherlv_6= 'version' ( (lv_version_7_0= ruleVersion ) )
            	    {
            	    otherlv_6=(Token)match(input,13,FOLLOW_6); 

            	    									newLeafNode(otherlv_6, grammarAccess.getConceptAccess().getVersionKeyword_3_1_0());
            	    								
            	    // InternalConceptLanguage.g:166:9: ( (lv_version_7_0= ruleVersion ) )
            	    // InternalConceptLanguage.g:167:10: (lv_version_7_0= ruleVersion )
            	    {
            	    // InternalConceptLanguage.g:167:10: (lv_version_7_0= ruleVersion )
            	    // InternalConceptLanguage.g:168:11: lv_version_7_0= ruleVersion
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
            	    // InternalConceptLanguage.g:191:4: ({...}? => ( ({...}? => ( (lv_beta_8_0= 'beta' ) ) ) ) )
            	    {
            	    // InternalConceptLanguage.g:191:4: ({...}? => ( ({...}? => ( (lv_beta_8_0= 'beta' ) ) ) ) )
            	    // InternalConceptLanguage.g:192:5: {...}? => ( ({...}? => ( (lv_beta_8_0= 'beta' ) ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getConceptAccess().getUnorderedGroup_3(), 2) ) {
            	        throw new FailedPredicateException(input, "ruleConcept", "getUnorderedGroupHelper().canSelect(grammarAccess.getConceptAccess().getUnorderedGroup_3(), 2)");
            	    }
            	    // InternalConceptLanguage.g:192:104: ( ({...}? => ( (lv_beta_8_0= 'beta' ) ) ) )
            	    // InternalConceptLanguage.g:193:6: ({...}? => ( (lv_beta_8_0= 'beta' ) ) )
            	    {

            	    						getUnorderedGroupHelper().select(grammarAccess.getConceptAccess().getUnorderedGroup_3(), 2);
            	    					
            	    // InternalConceptLanguage.g:196:9: ({...}? => ( (lv_beta_8_0= 'beta' ) ) )
            	    // InternalConceptLanguage.g:196:10: {...}? => ( (lv_beta_8_0= 'beta' ) )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleConcept", "true");
            	    }
            	    // InternalConceptLanguage.g:196:19: ( (lv_beta_8_0= 'beta' ) )
            	    // InternalConceptLanguage.g:196:20: (lv_beta_8_0= 'beta' )
            	    {
            	    // InternalConceptLanguage.g:196:20: (lv_beta_8_0= 'beta' )
            	    // InternalConceptLanguage.g:197:10: lv_beta_8_0= 'beta'
            	    {
            	    lv_beta_8_0=(Token)match(input,14,FOLLOW_4); 

            	    										newLeafNode(lv_beta_8_0, grammarAccess.getConceptAccess().getBetaBetaKeyword_3_2_0());
            	    									

            	    										if (current==null) {
            	    											current = createModelElement(grammarAccess.getConceptRule());
            	    										}
            	    										setWithLastConsumed(current, "beta", lv_beta_8_0 != null, "beta");
            	    									

            	    }


            	    }


            	    }

            	     
            	    						getUnorderedGroupHelper().returnFromSelection(grammarAccess.getConceptAccess().getUnorderedGroup_3());
            	    					

            	    }


            	    }


            	    }
            	    break;
            	case 4 :
            	    // InternalConceptLanguage.g:214:4: ({...}? => ( ({...}? => (otherlv_9= 'description' ( (lv_description_10_0= ruleEString ) ) ) ) ) )
            	    {
            	    // InternalConceptLanguage.g:214:4: ({...}? => ( ({...}? => (otherlv_9= 'description' ( (lv_description_10_0= ruleEString ) ) ) ) ) )
            	    // InternalConceptLanguage.g:215:5: {...}? => ( ({...}? => (otherlv_9= 'description' ( (lv_description_10_0= ruleEString ) ) ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getConceptAccess().getUnorderedGroup_3(), 3) ) {
            	        throw new FailedPredicateException(input, "ruleConcept", "getUnorderedGroupHelper().canSelect(grammarAccess.getConceptAccess().getUnorderedGroup_3(), 3)");
            	    }
            	    // InternalConceptLanguage.g:215:104: ( ({...}? => (otherlv_9= 'description' ( (lv_description_10_0= ruleEString ) ) ) ) )
            	    // InternalConceptLanguage.g:216:6: ({...}? => (otherlv_9= 'description' ( (lv_description_10_0= ruleEString ) ) ) )
            	    {

            	    						getUnorderedGroupHelper().select(grammarAccess.getConceptAccess().getUnorderedGroup_3(), 3);
            	    					
            	    // InternalConceptLanguage.g:219:9: ({...}? => (otherlv_9= 'description' ( (lv_description_10_0= ruleEString ) ) ) )
            	    // InternalConceptLanguage.g:219:10: {...}? => (otherlv_9= 'description' ( (lv_description_10_0= ruleEString ) ) )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleConcept", "true");
            	    }
            	    // InternalConceptLanguage.g:219:19: (otherlv_9= 'description' ( (lv_description_10_0= ruleEString ) ) )
            	    // InternalConceptLanguage.g:219:20: otherlv_9= 'description' ( (lv_description_10_0= ruleEString ) )
            	    {
            	    otherlv_9=(Token)match(input,15,FOLLOW_5); 

            	    									newLeafNode(otherlv_9, grammarAccess.getConceptAccess().getDescriptionKeyword_3_3_0());
            	    								
            	    // InternalConceptLanguage.g:223:9: ( (lv_description_10_0= ruleEString ) )
            	    // InternalConceptLanguage.g:224:10: (lv_description_10_0= ruleEString )
            	    {
            	    // InternalConceptLanguage.g:224:10: (lv_description_10_0= ruleEString )
            	    // InternalConceptLanguage.g:225:11: lv_description_10_0= ruleEString
            	    {

            	    											newCompositeNode(grammarAccess.getConceptAccess().getDescriptionEStringParserRuleCall_3_3_1_0());
            	    										
            	    pushFollow(FOLLOW_4);
            	    lv_description_10_0=ruleEString();

            	    state._fsp--;


            	    											if (current==null) {
            	    												current = createModelElementForParent(grammarAccess.getConceptRule());
            	    											}
            	    											set(
            	    												current,
            	    												"description",
            	    												lv_description_10_0,
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
            	case 5 :
            	    // InternalConceptLanguage.g:248:4: ({...}? => ( ({...}? => ( (lv_DMF_11_0= 'hasDMF' ) ) ) ) )
            	    {
            	    // InternalConceptLanguage.g:248:4: ({...}? => ( ({...}? => ( (lv_DMF_11_0= 'hasDMF' ) ) ) ) )
            	    // InternalConceptLanguage.g:249:5: {...}? => ( ({...}? => ( (lv_DMF_11_0= 'hasDMF' ) ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getConceptAccess().getUnorderedGroup_3(), 4) ) {
            	        throw new FailedPredicateException(input, "ruleConcept", "getUnorderedGroupHelper().canSelect(grammarAccess.getConceptAccess().getUnorderedGroup_3(), 4)");
            	    }
            	    // InternalConceptLanguage.g:249:104: ( ({...}? => ( (lv_DMF_11_0= 'hasDMF' ) ) ) )
            	    // InternalConceptLanguage.g:250:6: ({...}? => ( (lv_DMF_11_0= 'hasDMF' ) ) )
            	    {

            	    						getUnorderedGroupHelper().select(grammarAccess.getConceptAccess().getUnorderedGroup_3(), 4);
            	    					
            	    // InternalConceptLanguage.g:253:9: ({...}? => ( (lv_DMF_11_0= 'hasDMF' ) ) )
            	    // InternalConceptLanguage.g:253:10: {...}? => ( (lv_DMF_11_0= 'hasDMF' ) )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleConcept", "true");
            	    }
            	    // InternalConceptLanguage.g:253:19: ( (lv_DMF_11_0= 'hasDMF' ) )
            	    // InternalConceptLanguage.g:253:20: (lv_DMF_11_0= 'hasDMF' )
            	    {
            	    // InternalConceptLanguage.g:253:20: (lv_DMF_11_0= 'hasDMF' )
            	    // InternalConceptLanguage.g:254:10: lv_DMF_11_0= 'hasDMF'
            	    {
            	    lv_DMF_11_0=(Token)match(input,16,FOLLOW_4); 

            	    										newLeafNode(lv_DMF_11_0, grammarAccess.getConceptAccess().getDMFHasDMFKeyword_3_4_0());
            	    									

            	    										if (current==null) {
            	    											current = createModelElement(grammarAccess.getConceptRule());
            	    										}
            	    										setWithLastConsumed(current, "DMF", lv_DMF_11_0 != null, "hasDMF");
            	    									

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

            otherlv_12=(Token)match(input,17,FOLLOW_7); 

            			newLeafNode(otherlv_12, grammarAccess.getConceptAccess().getLeftCurlyBracketKeyword_4());
            		
            // InternalConceptLanguage.g:282:3: ( ( ( ( ({...}? => ( ({...}? => ( (lv_imports_14_0= ruleConceptImport ) ) )+ ) ) | ({...}? => ( ({...}? => ( (lv_ecoreImports_15_0= ruleEcoreImport ) ) )+ ) ) )* ) ) )
            // InternalConceptLanguage.g:283:4: ( ( ( ({...}? => ( ({...}? => ( (lv_imports_14_0= ruleConceptImport ) ) )+ ) ) | ({...}? => ( ({...}? => ( (lv_ecoreImports_15_0= ruleEcoreImport ) ) )+ ) ) )* ) )
            {
            // InternalConceptLanguage.g:283:4: ( ( ( ({...}? => ( ({...}? => ( (lv_imports_14_0= ruleConceptImport ) ) )+ ) ) | ({...}? => ( ({...}? => ( (lv_ecoreImports_15_0= ruleEcoreImport ) ) )+ ) ) )* ) )
            // InternalConceptLanguage.g:284:5: ( ( ({...}? => ( ({...}? => ( (lv_imports_14_0= ruleConceptImport ) ) )+ ) ) | ({...}? => ( ({...}? => ( (lv_ecoreImports_15_0= ruleEcoreImport ) ) )+ ) ) )* )
            {
             
            				  getUnorderedGroupHelper().enter(grammarAccess.getConceptAccess().getUnorderedGroup_5());
            				
            // InternalConceptLanguage.g:287:5: ( ( ({...}? => ( ({...}? => ( (lv_imports_14_0= ruleConceptImport ) ) )+ ) ) | ({...}? => ( ({...}? => ( (lv_ecoreImports_15_0= ruleEcoreImport ) ) )+ ) ) )* )
            // InternalConceptLanguage.g:288:6: ( ({...}? => ( ({...}? => ( (lv_imports_14_0= ruleConceptImport ) ) )+ ) ) | ({...}? => ( ({...}? => ( (lv_ecoreImports_15_0= ruleEcoreImport ) ) )+ ) ) )*
            {
            // InternalConceptLanguage.g:288:6: ( ({...}? => ( ({...}? => ( (lv_imports_14_0= ruleConceptImport ) ) )+ ) ) | ({...}? => ( ({...}? => ( (lv_ecoreImports_15_0= ruleEcoreImport ) ) )+ ) ) )*
            loop4:
            do {
                int alt4=3;
                int LA4_0 = input.LA(1);

                if ( LA4_0 == 39 && getUnorderedGroupHelper().canSelect(grammarAccess.getConceptAccess().getUnorderedGroup_5(), 0) ) {
                    alt4=1;
                }
                else if ( LA4_0 == 40 && getUnorderedGroupHelper().canSelect(grammarAccess.getConceptAccess().getUnorderedGroup_5(), 1) ) {
                    alt4=2;
                }


                switch (alt4) {
            	case 1 :
            	    // InternalConceptLanguage.g:289:4: ({...}? => ( ({...}? => ( (lv_imports_14_0= ruleConceptImport ) ) )+ ) )
            	    {
            	    // InternalConceptLanguage.g:289:4: ({...}? => ( ({...}? => ( (lv_imports_14_0= ruleConceptImport ) ) )+ ) )
            	    // InternalConceptLanguage.g:290:5: {...}? => ( ({...}? => ( (lv_imports_14_0= ruleConceptImport ) ) )+ )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getConceptAccess().getUnorderedGroup_5(), 0) ) {
            	        throw new FailedPredicateException(input, "ruleConcept", "getUnorderedGroupHelper().canSelect(grammarAccess.getConceptAccess().getUnorderedGroup_5(), 0)");
            	    }
            	    // InternalConceptLanguage.g:290:104: ( ({...}? => ( (lv_imports_14_0= ruleConceptImport ) ) )+ )
            	    // InternalConceptLanguage.g:291:6: ({...}? => ( (lv_imports_14_0= ruleConceptImport ) ) )+
            	    {

            	    						getUnorderedGroupHelper().select(grammarAccess.getConceptAccess().getUnorderedGroup_5(), 0);
            	    					
            	    // InternalConceptLanguage.g:294:9: ({...}? => ( (lv_imports_14_0= ruleConceptImport ) ) )+
            	    int cnt2=0;
            	    loop2:
            	    do {
            	        int alt2=2;
            	        int LA2_0 = input.LA(1);

            	        if ( (LA2_0==39) ) {
            	            int LA2_2 = input.LA(2);

            	            if ( ((true)) ) {
            	                alt2=1;
            	            }


            	        }


            	        switch (alt2) {
            	    	case 1 :
            	    	    // InternalConceptLanguage.g:294:10: {...}? => ( (lv_imports_14_0= ruleConceptImport ) )
            	    	    {
            	    	    if ( !((true)) ) {
            	    	        throw new FailedPredicateException(input, "ruleConcept", "true");
            	    	    }
            	    	    // InternalConceptLanguage.g:294:19: ( (lv_imports_14_0= ruleConceptImport ) )
            	    	    // InternalConceptLanguage.g:294:20: (lv_imports_14_0= ruleConceptImport )
            	    	    {
            	    	    // InternalConceptLanguage.g:294:20: (lv_imports_14_0= ruleConceptImport )
            	    	    // InternalConceptLanguage.g:295:10: lv_imports_14_0= ruleConceptImport
            	    	    {

            	    	    										newCompositeNode(grammarAccess.getConceptAccess().getImportsConceptImportParserRuleCall_5_0_0());
            	    	    									
            	    	    pushFollow(FOLLOW_7);
            	    	    lv_imports_14_0=ruleConceptImport();

            	    	    state._fsp--;


            	    	    										if (current==null) {
            	    	    											current = createModelElementForParent(grammarAccess.getConceptRule());
            	    	    										}
            	    	    										add(
            	    	    											current,
            	    	    											"imports",
            	    	    											lv_imports_14_0,
            	    	    											"de.dlr.sc.virsat.model.concept.ConceptLanguage.ConceptImport");
            	    	    										afterParserOrEnumRuleCall();
            	    	    									

            	    	    }


            	    	    }


            	    	    }
            	    	    break;

            	    	default :
            	    	    if ( cnt2 >= 1 ) break loop2;
            	                EarlyExitException eee =
            	                    new EarlyExitException(2, input);
            	                throw eee;
            	        }
            	        cnt2++;
            	    } while (true);

            	     
            	    						getUnorderedGroupHelper().returnFromSelection(grammarAccess.getConceptAccess().getUnorderedGroup_5());
            	    					

            	    }


            	    }


            	    }
            	    break;
            	case 2 :
            	    // InternalConceptLanguage.g:317:4: ({...}? => ( ({...}? => ( (lv_ecoreImports_15_0= ruleEcoreImport ) ) )+ ) )
            	    {
            	    // InternalConceptLanguage.g:317:4: ({...}? => ( ({...}? => ( (lv_ecoreImports_15_0= ruleEcoreImport ) ) )+ ) )
            	    // InternalConceptLanguage.g:318:5: {...}? => ( ({...}? => ( (lv_ecoreImports_15_0= ruleEcoreImport ) ) )+ )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getConceptAccess().getUnorderedGroup_5(), 1) ) {
            	        throw new FailedPredicateException(input, "ruleConcept", "getUnorderedGroupHelper().canSelect(grammarAccess.getConceptAccess().getUnorderedGroup_5(), 1)");
            	    }
            	    // InternalConceptLanguage.g:318:104: ( ({...}? => ( (lv_ecoreImports_15_0= ruleEcoreImport ) ) )+ )
            	    // InternalConceptLanguage.g:319:6: ({...}? => ( (lv_ecoreImports_15_0= ruleEcoreImport ) ) )+
            	    {

            	    						getUnorderedGroupHelper().select(grammarAccess.getConceptAccess().getUnorderedGroup_5(), 1);
            	    					
            	    // InternalConceptLanguage.g:322:9: ({...}? => ( (lv_ecoreImports_15_0= ruleEcoreImport ) ) )+
            	    int cnt3=0;
            	    loop3:
            	    do {
            	        int alt3=2;
            	        int LA3_0 = input.LA(1);

            	        if ( (LA3_0==40) ) {
            	            int LA3_2 = input.LA(2);

            	            if ( ((true)) ) {
            	                alt3=1;
            	            }


            	        }


            	        switch (alt3) {
            	    	case 1 :
            	    	    // InternalConceptLanguage.g:322:10: {...}? => ( (lv_ecoreImports_15_0= ruleEcoreImport ) )
            	    	    {
            	    	    if ( !((true)) ) {
            	    	        throw new FailedPredicateException(input, "ruleConcept", "true");
            	    	    }
            	    	    // InternalConceptLanguage.g:322:19: ( (lv_ecoreImports_15_0= ruleEcoreImport ) )
            	    	    // InternalConceptLanguage.g:322:20: (lv_ecoreImports_15_0= ruleEcoreImport )
            	    	    {
            	    	    // InternalConceptLanguage.g:322:20: (lv_ecoreImports_15_0= ruleEcoreImport )
            	    	    // InternalConceptLanguage.g:323:10: lv_ecoreImports_15_0= ruleEcoreImport
            	    	    {

            	    	    										newCompositeNode(grammarAccess.getConceptAccess().getEcoreImportsEcoreImportParserRuleCall_5_1_0());
            	    	    									
            	    	    pushFollow(FOLLOW_7);
            	    	    lv_ecoreImports_15_0=ruleEcoreImport();

            	    	    state._fsp--;


            	    	    										if (current==null) {
            	    	    											current = createModelElementForParent(grammarAccess.getConceptRule());
            	    	    										}
            	    	    										add(
            	    	    											current,
            	    	    											"ecoreImports",
            	    	    											lv_ecoreImports_15_0,
            	    	    											"de.dlr.sc.virsat.model.concept.ConceptLanguage.EcoreImport");
            	    	    										afterParserOrEnumRuleCall();
            	    	    									

            	    	    }


            	    	    }


            	    	    }
            	    	    break;

            	    	default :
            	    	    if ( cnt3 >= 1 ) break loop3;
            	                EarlyExitException eee =
            	                    new EarlyExitException(3, input);
            	                throw eee;
            	        }
            	        cnt3++;
            	    } while (true);

            	     
            	    						getUnorderedGroupHelper().returnFromSelection(grammarAccess.getConceptAccess().getUnorderedGroup_5());
            	    					

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop4;
                }
            } while (true);


            }


            }

             
            				  getUnorderedGroupHelper().leave(grammarAccess.getConceptAccess().getUnorderedGroup_5());
            				

            }

            // InternalConceptLanguage.g:352:3: ( (lv_structuralElements_16_0= ruleStructuralElement ) )*
            loop5:
            do {
                int alt5=2;
                int LA5_0 = input.LA(1);

                if ( (LA5_0==19) ) {
                    alt5=1;
                }


                switch (alt5) {
            	case 1 :
            	    // InternalConceptLanguage.g:353:4: (lv_structuralElements_16_0= ruleStructuralElement )
            	    {
            	    // InternalConceptLanguage.g:353:4: (lv_structuralElements_16_0= ruleStructuralElement )
            	    // InternalConceptLanguage.g:354:5: lv_structuralElements_16_0= ruleStructuralElement
            	    {

            	    					newCompositeNode(grammarAccess.getConceptAccess().getStructuralElementsStructuralElementParserRuleCall_6_0());
            	    				
            	    pushFollow(FOLLOW_8);
            	    lv_structuralElements_16_0=ruleStructuralElement();

            	    state._fsp--;


            	    					if (current==null) {
            	    						current = createModelElementForParent(grammarAccess.getConceptRule());
            	    					}
            	    					add(
            	    						current,
            	    						"structuralElements",
            	    						lv_structuralElements_16_0,
            	    						"de.dlr.sc.virsat.model.concept.ConceptLanguage.StructuralElement");
            	    					afterParserOrEnumRuleCall();
            	    				

            	    }


            	    }
            	    break;

            	default :
            	    break loop5;
                }
            } while (true);

            // InternalConceptLanguage.g:371:3: ( (lv_relations_17_0= ruleARelation ) )*
            loop6:
            do {
                int alt6=2;
                int LA6_0 = input.LA(1);

                if ( (LA6_0==32) ) {
                    alt6=1;
                }


                switch (alt6) {
            	case 1 :
            	    // InternalConceptLanguage.g:372:4: (lv_relations_17_0= ruleARelation )
            	    {
            	    // InternalConceptLanguage.g:372:4: (lv_relations_17_0= ruleARelation )
            	    // InternalConceptLanguage.g:373:5: lv_relations_17_0= ruleARelation
            	    {

            	    					newCompositeNode(grammarAccess.getConceptAccess().getRelationsARelationParserRuleCall_7_0());
            	    				
            	    pushFollow(FOLLOW_9);
            	    lv_relations_17_0=ruleARelation();

            	    state._fsp--;


            	    					if (current==null) {
            	    						current = createModelElementForParent(grammarAccess.getConceptRule());
            	    					}
            	    					add(
            	    						current,
            	    						"relations",
            	    						lv_relations_17_0,
            	    						"de.dlr.sc.virsat.model.concept.ConceptLanguage.ARelation");
            	    					afterParserOrEnumRuleCall();
            	    				

            	    }


            	    }
            	    break;

            	default :
            	    break loop6;
                }
            } while (true);

            // InternalConceptLanguage.g:390:3: ( (lv_categories_18_0= ruleCategory ) )*
            loop7:
            do {
                int alt7=2;
                int LA7_0 = input.LA(1);

                if ( (LA7_0==35) ) {
                    alt7=1;
                }


                switch (alt7) {
            	case 1 :
            	    // InternalConceptLanguage.g:391:4: (lv_categories_18_0= ruleCategory )
            	    {
            	    // InternalConceptLanguage.g:391:4: (lv_categories_18_0= ruleCategory )
            	    // InternalConceptLanguage.g:392:5: lv_categories_18_0= ruleCategory
            	    {

            	    					newCompositeNode(grammarAccess.getConceptAccess().getCategoriesCategoryParserRuleCall_8_0());
            	    				
            	    pushFollow(FOLLOW_10);
            	    lv_categories_18_0=ruleCategory();

            	    state._fsp--;


            	    					if (current==null) {
            	    						current = createModelElementForParent(grammarAccess.getConceptRule());
            	    					}
            	    					add(
            	    						current,
            	    						"categories",
            	    						lv_categories_18_0,
            	    						"de.dlr.sc.virsat.model.concept.ConceptLanguage.Category");
            	    					afterParserOrEnumRuleCall();
            	    				

            	    }


            	    }
            	    break;

            	default :
            	    break loop7;
                }
            } while (true);

            otherlv_19=(Token)match(input,18,FOLLOW_2); 

            			newLeafNode(otherlv_19, grammarAccess.getConceptAccess().getRightCurlyBracketKeyword_9());
            		

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
    // InternalConceptLanguage.g:417:1: entryRuleStructuralElement returns [EObject current=null] : iv_ruleStructuralElement= ruleStructuralElement EOF ;
    public final EObject entryRuleStructuralElement() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleStructuralElement = null;


        try {
            // InternalConceptLanguage.g:417:58: (iv_ruleStructuralElement= ruleStructuralElement EOF )
            // InternalConceptLanguage.g:418:2: iv_ruleStructuralElement= ruleStructuralElement EOF
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
    // InternalConceptLanguage.g:424:1: ruleStructuralElement returns [EObject current=null] : ( () otherlv_1= 'StructuralElement' ( (lv_name_2_0= RULE_ID ) ) ( ( ( ( ({...}? => ( ({...}? => (otherlv_4= 'shortname' ( (lv_shortName_5_0= RULE_ID ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_6= 'description' ( (lv_description_7_0= ruleEString ) ) ) ) ) ) )* ) ) ) otherlv_8= '{' ( ( ( ( ({...}? => ( ({...}? => ( ( (lv_isRootStructuralElement_10_0= 'IsRootStructuralElement' ) ) otherlv_11= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_12= 'Inherits' otherlv_13= 'From' ( (otherlv_14= '[' ( ( ruleQualifiedName ) ) (otherlv_16= ',' ( ( ruleQualifiedName ) ) )* otherlv_18= ']' ) | ( (lv_isCanInheritFromAll_19_0= 'All' ) ) ) otherlv_20= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_21= 'Applicable' otherlv_22= 'For' ( (otherlv_23= '[' ( ( ruleQualifiedName ) ) (otherlv_25= ',' ( ( ruleQualifiedName ) ) )* otherlv_27= ']' ) | ( (lv_isApplicableForAll_28_0= 'All' ) ) ) otherlv_29= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_30= 'Cardinality' ( (lv_cardinality_31_0= RULE_INT ) ) otherlv_32= ';' ) ) ) ) )* ) ) ) otherlv_33= '}' ) ;
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
            // InternalConceptLanguage.g:430:2: ( ( () otherlv_1= 'StructuralElement' ( (lv_name_2_0= RULE_ID ) ) ( ( ( ( ({...}? => ( ({...}? => (otherlv_4= 'shortname' ( (lv_shortName_5_0= RULE_ID ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_6= 'description' ( (lv_description_7_0= ruleEString ) ) ) ) ) ) )* ) ) ) otherlv_8= '{' ( ( ( ( ({...}? => ( ({...}? => ( ( (lv_isRootStructuralElement_10_0= 'IsRootStructuralElement' ) ) otherlv_11= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_12= 'Inherits' otherlv_13= 'From' ( (otherlv_14= '[' ( ( ruleQualifiedName ) ) (otherlv_16= ',' ( ( ruleQualifiedName ) ) )* otherlv_18= ']' ) | ( (lv_isCanInheritFromAll_19_0= 'All' ) ) ) otherlv_20= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_21= 'Applicable' otherlv_22= 'For' ( (otherlv_23= '[' ( ( ruleQualifiedName ) ) (otherlv_25= ',' ( ( ruleQualifiedName ) ) )* otherlv_27= ']' ) | ( (lv_isApplicableForAll_28_0= 'All' ) ) ) otherlv_29= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_30= 'Cardinality' ( (lv_cardinality_31_0= RULE_INT ) ) otherlv_32= ';' ) ) ) ) )* ) ) ) otherlv_33= '}' ) )
            // InternalConceptLanguage.g:431:2: ( () otherlv_1= 'StructuralElement' ( (lv_name_2_0= RULE_ID ) ) ( ( ( ( ({...}? => ( ({...}? => (otherlv_4= 'shortname' ( (lv_shortName_5_0= RULE_ID ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_6= 'description' ( (lv_description_7_0= ruleEString ) ) ) ) ) ) )* ) ) ) otherlv_8= '{' ( ( ( ( ({...}? => ( ({...}? => ( ( (lv_isRootStructuralElement_10_0= 'IsRootStructuralElement' ) ) otherlv_11= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_12= 'Inherits' otherlv_13= 'From' ( (otherlv_14= '[' ( ( ruleQualifiedName ) ) (otherlv_16= ',' ( ( ruleQualifiedName ) ) )* otherlv_18= ']' ) | ( (lv_isCanInheritFromAll_19_0= 'All' ) ) ) otherlv_20= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_21= 'Applicable' otherlv_22= 'For' ( (otherlv_23= '[' ( ( ruleQualifiedName ) ) (otherlv_25= ',' ( ( ruleQualifiedName ) ) )* otherlv_27= ']' ) | ( (lv_isApplicableForAll_28_0= 'All' ) ) ) otherlv_29= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_30= 'Cardinality' ( (lv_cardinality_31_0= RULE_INT ) ) otherlv_32= ';' ) ) ) ) )* ) ) ) otherlv_33= '}' )
            {
            // InternalConceptLanguage.g:431:2: ( () otherlv_1= 'StructuralElement' ( (lv_name_2_0= RULE_ID ) ) ( ( ( ( ({...}? => ( ({...}? => (otherlv_4= 'shortname' ( (lv_shortName_5_0= RULE_ID ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_6= 'description' ( (lv_description_7_0= ruleEString ) ) ) ) ) ) )* ) ) ) otherlv_8= '{' ( ( ( ( ({...}? => ( ({...}? => ( ( (lv_isRootStructuralElement_10_0= 'IsRootStructuralElement' ) ) otherlv_11= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_12= 'Inherits' otherlv_13= 'From' ( (otherlv_14= '[' ( ( ruleQualifiedName ) ) (otherlv_16= ',' ( ( ruleQualifiedName ) ) )* otherlv_18= ']' ) | ( (lv_isCanInheritFromAll_19_0= 'All' ) ) ) otherlv_20= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_21= 'Applicable' otherlv_22= 'For' ( (otherlv_23= '[' ( ( ruleQualifiedName ) ) (otherlv_25= ',' ( ( ruleQualifiedName ) ) )* otherlv_27= ']' ) | ( (lv_isApplicableForAll_28_0= 'All' ) ) ) otherlv_29= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_30= 'Cardinality' ( (lv_cardinality_31_0= RULE_INT ) ) otherlv_32= ';' ) ) ) ) )* ) ) ) otherlv_33= '}' )
            // InternalConceptLanguage.g:432:3: () otherlv_1= 'StructuralElement' ( (lv_name_2_0= RULE_ID ) ) ( ( ( ( ({...}? => ( ({...}? => (otherlv_4= 'shortname' ( (lv_shortName_5_0= RULE_ID ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_6= 'description' ( (lv_description_7_0= ruleEString ) ) ) ) ) ) )* ) ) ) otherlv_8= '{' ( ( ( ( ({...}? => ( ({...}? => ( ( (lv_isRootStructuralElement_10_0= 'IsRootStructuralElement' ) ) otherlv_11= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_12= 'Inherits' otherlv_13= 'From' ( (otherlv_14= '[' ( ( ruleQualifiedName ) ) (otherlv_16= ',' ( ( ruleQualifiedName ) ) )* otherlv_18= ']' ) | ( (lv_isCanInheritFromAll_19_0= 'All' ) ) ) otherlv_20= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_21= 'Applicable' otherlv_22= 'For' ( (otherlv_23= '[' ( ( ruleQualifiedName ) ) (otherlv_25= ',' ( ( ruleQualifiedName ) ) )* otherlv_27= ']' ) | ( (lv_isApplicableForAll_28_0= 'All' ) ) ) otherlv_29= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_30= 'Cardinality' ( (lv_cardinality_31_0= RULE_INT ) ) otherlv_32= ';' ) ) ) ) )* ) ) ) otherlv_33= '}'
            {
            // InternalConceptLanguage.g:432:3: ()
            // InternalConceptLanguage.g:433:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getStructuralElementAccess().getStructuralElementAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,19,FOLLOW_3); 

            			newLeafNode(otherlv_1, grammarAccess.getStructuralElementAccess().getStructuralElementKeyword_1());
            		
            // InternalConceptLanguage.g:443:3: ( (lv_name_2_0= RULE_ID ) )
            // InternalConceptLanguage.g:444:4: (lv_name_2_0= RULE_ID )
            {
            // InternalConceptLanguage.g:444:4: (lv_name_2_0= RULE_ID )
            // InternalConceptLanguage.g:445:5: lv_name_2_0= RULE_ID
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

            // InternalConceptLanguage.g:461:3: ( ( ( ( ({...}? => ( ({...}? => (otherlv_4= 'shortname' ( (lv_shortName_5_0= RULE_ID ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_6= 'description' ( (lv_description_7_0= ruleEString ) ) ) ) ) ) )* ) ) )
            // InternalConceptLanguage.g:462:4: ( ( ( ({...}? => ( ({...}? => (otherlv_4= 'shortname' ( (lv_shortName_5_0= RULE_ID ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_6= 'description' ( (lv_description_7_0= ruleEString ) ) ) ) ) ) )* ) )
            {
            // InternalConceptLanguage.g:462:4: ( ( ( ({...}? => ( ({...}? => (otherlv_4= 'shortname' ( (lv_shortName_5_0= RULE_ID ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_6= 'description' ( (lv_description_7_0= ruleEString ) ) ) ) ) ) )* ) )
            // InternalConceptLanguage.g:463:5: ( ( ({...}? => ( ({...}? => (otherlv_4= 'shortname' ( (lv_shortName_5_0= RULE_ID ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_6= 'description' ( (lv_description_7_0= ruleEString ) ) ) ) ) ) )* )
            {
             
            				  getUnorderedGroupHelper().enter(grammarAccess.getStructuralElementAccess().getUnorderedGroup_3());
            				
            // InternalConceptLanguage.g:466:5: ( ( ({...}? => ( ({...}? => (otherlv_4= 'shortname' ( (lv_shortName_5_0= RULE_ID ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_6= 'description' ( (lv_description_7_0= ruleEString ) ) ) ) ) ) )* )
            // InternalConceptLanguage.g:467:6: ( ({...}? => ( ({...}? => (otherlv_4= 'shortname' ( (lv_shortName_5_0= RULE_ID ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_6= 'description' ( (lv_description_7_0= ruleEString ) ) ) ) ) ) )*
            {
            // InternalConceptLanguage.g:467:6: ( ({...}? => ( ({...}? => (otherlv_4= 'shortname' ( (lv_shortName_5_0= RULE_ID ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_6= 'description' ( (lv_description_7_0= ruleEString ) ) ) ) ) ) )*
            loop8:
            do {
                int alt8=3;
                int LA8_0 = input.LA(1);

                if ( LA8_0 == 20 && getUnorderedGroupHelper().canSelect(grammarAccess.getStructuralElementAccess().getUnorderedGroup_3(), 0) ) {
                    alt8=1;
                }
                else if ( LA8_0 == 15 && getUnorderedGroupHelper().canSelect(grammarAccess.getStructuralElementAccess().getUnorderedGroup_3(), 1) ) {
                    alt8=2;
                }


                switch (alt8) {
            	case 1 :
            	    // InternalConceptLanguage.g:468:4: ({...}? => ( ({...}? => (otherlv_4= 'shortname' ( (lv_shortName_5_0= RULE_ID ) ) ) ) ) )
            	    {
            	    // InternalConceptLanguage.g:468:4: ({...}? => ( ({...}? => (otherlv_4= 'shortname' ( (lv_shortName_5_0= RULE_ID ) ) ) ) ) )
            	    // InternalConceptLanguage.g:469:5: {...}? => ( ({...}? => (otherlv_4= 'shortname' ( (lv_shortName_5_0= RULE_ID ) ) ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getStructuralElementAccess().getUnorderedGroup_3(), 0) ) {
            	        throw new FailedPredicateException(input, "ruleStructuralElement", "getUnorderedGroupHelper().canSelect(grammarAccess.getStructuralElementAccess().getUnorderedGroup_3(), 0)");
            	    }
            	    // InternalConceptLanguage.g:469:114: ( ({...}? => (otherlv_4= 'shortname' ( (lv_shortName_5_0= RULE_ID ) ) ) ) )
            	    // InternalConceptLanguage.g:470:6: ({...}? => (otherlv_4= 'shortname' ( (lv_shortName_5_0= RULE_ID ) ) ) )
            	    {

            	    						getUnorderedGroupHelper().select(grammarAccess.getStructuralElementAccess().getUnorderedGroup_3(), 0);
            	    					
            	    // InternalConceptLanguage.g:473:9: ({...}? => (otherlv_4= 'shortname' ( (lv_shortName_5_0= RULE_ID ) ) ) )
            	    // InternalConceptLanguage.g:473:10: {...}? => (otherlv_4= 'shortname' ( (lv_shortName_5_0= RULE_ID ) ) )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleStructuralElement", "true");
            	    }
            	    // InternalConceptLanguage.g:473:19: (otherlv_4= 'shortname' ( (lv_shortName_5_0= RULE_ID ) ) )
            	    // InternalConceptLanguage.g:473:20: otherlv_4= 'shortname' ( (lv_shortName_5_0= RULE_ID ) )
            	    {
            	    otherlv_4=(Token)match(input,20,FOLLOW_3); 

            	    									newLeafNode(otherlv_4, grammarAccess.getStructuralElementAccess().getShortnameKeyword_3_0_0());
            	    								
            	    // InternalConceptLanguage.g:477:9: ( (lv_shortName_5_0= RULE_ID ) )
            	    // InternalConceptLanguage.g:478:10: (lv_shortName_5_0= RULE_ID )
            	    {
            	    // InternalConceptLanguage.g:478:10: (lv_shortName_5_0= RULE_ID )
            	    // InternalConceptLanguage.g:479:11: lv_shortName_5_0= RULE_ID
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
            	    // InternalConceptLanguage.g:501:4: ({...}? => ( ({...}? => (otherlv_6= 'description' ( (lv_description_7_0= ruleEString ) ) ) ) ) )
            	    {
            	    // InternalConceptLanguage.g:501:4: ({...}? => ( ({...}? => (otherlv_6= 'description' ( (lv_description_7_0= ruleEString ) ) ) ) ) )
            	    // InternalConceptLanguage.g:502:5: {...}? => ( ({...}? => (otherlv_6= 'description' ( (lv_description_7_0= ruleEString ) ) ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getStructuralElementAccess().getUnorderedGroup_3(), 1) ) {
            	        throw new FailedPredicateException(input, "ruleStructuralElement", "getUnorderedGroupHelper().canSelect(grammarAccess.getStructuralElementAccess().getUnorderedGroup_3(), 1)");
            	    }
            	    // InternalConceptLanguage.g:502:114: ( ({...}? => (otherlv_6= 'description' ( (lv_description_7_0= ruleEString ) ) ) ) )
            	    // InternalConceptLanguage.g:503:6: ({...}? => (otherlv_6= 'description' ( (lv_description_7_0= ruleEString ) ) ) )
            	    {

            	    						getUnorderedGroupHelper().select(grammarAccess.getStructuralElementAccess().getUnorderedGroup_3(), 1);
            	    					
            	    // InternalConceptLanguage.g:506:9: ({...}? => (otherlv_6= 'description' ( (lv_description_7_0= ruleEString ) ) ) )
            	    // InternalConceptLanguage.g:506:10: {...}? => (otherlv_6= 'description' ( (lv_description_7_0= ruleEString ) ) )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleStructuralElement", "true");
            	    }
            	    // InternalConceptLanguage.g:506:19: (otherlv_6= 'description' ( (lv_description_7_0= ruleEString ) ) )
            	    // InternalConceptLanguage.g:506:20: otherlv_6= 'description' ( (lv_description_7_0= ruleEString ) )
            	    {
            	    otherlv_6=(Token)match(input,15,FOLLOW_5); 

            	    									newLeafNode(otherlv_6, grammarAccess.getStructuralElementAccess().getDescriptionKeyword_3_1_0());
            	    								
            	    // InternalConceptLanguage.g:510:9: ( (lv_description_7_0= ruleEString ) )
            	    // InternalConceptLanguage.g:511:10: (lv_description_7_0= ruleEString )
            	    {
            	    // InternalConceptLanguage.g:511:10: (lv_description_7_0= ruleEString )
            	    // InternalConceptLanguage.g:512:11: lv_description_7_0= ruleEString
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
            	    break loop8;
                }
            } while (true);


            }


            }

             
            				  getUnorderedGroupHelper().leave(grammarAccess.getStructuralElementAccess().getUnorderedGroup_3());
            				

            }

            otherlv_8=(Token)match(input,17,FOLLOW_12); 

            			newLeafNode(otherlv_8, grammarAccess.getStructuralElementAccess().getLeftCurlyBracketKeyword_4());
            		
            // InternalConceptLanguage.g:546:3: ( ( ( ( ({...}? => ( ({...}? => ( ( (lv_isRootStructuralElement_10_0= 'IsRootStructuralElement' ) ) otherlv_11= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_12= 'Inherits' otherlv_13= 'From' ( (otherlv_14= '[' ( ( ruleQualifiedName ) ) (otherlv_16= ',' ( ( ruleQualifiedName ) ) )* otherlv_18= ']' ) | ( (lv_isCanInheritFromAll_19_0= 'All' ) ) ) otherlv_20= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_21= 'Applicable' otherlv_22= 'For' ( (otherlv_23= '[' ( ( ruleQualifiedName ) ) (otherlv_25= ',' ( ( ruleQualifiedName ) ) )* otherlv_27= ']' ) | ( (lv_isApplicableForAll_28_0= 'All' ) ) ) otherlv_29= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_30= 'Cardinality' ( (lv_cardinality_31_0= RULE_INT ) ) otherlv_32= ';' ) ) ) ) )* ) ) )
            // InternalConceptLanguage.g:547:4: ( ( ( ({...}? => ( ({...}? => ( ( (lv_isRootStructuralElement_10_0= 'IsRootStructuralElement' ) ) otherlv_11= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_12= 'Inherits' otherlv_13= 'From' ( (otherlv_14= '[' ( ( ruleQualifiedName ) ) (otherlv_16= ',' ( ( ruleQualifiedName ) ) )* otherlv_18= ']' ) | ( (lv_isCanInheritFromAll_19_0= 'All' ) ) ) otherlv_20= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_21= 'Applicable' otherlv_22= 'For' ( (otherlv_23= '[' ( ( ruleQualifiedName ) ) (otherlv_25= ',' ( ( ruleQualifiedName ) ) )* otherlv_27= ']' ) | ( (lv_isApplicableForAll_28_0= 'All' ) ) ) otherlv_29= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_30= 'Cardinality' ( (lv_cardinality_31_0= RULE_INT ) ) otherlv_32= ';' ) ) ) ) )* ) )
            {
            // InternalConceptLanguage.g:547:4: ( ( ( ({...}? => ( ({...}? => ( ( (lv_isRootStructuralElement_10_0= 'IsRootStructuralElement' ) ) otherlv_11= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_12= 'Inherits' otherlv_13= 'From' ( (otherlv_14= '[' ( ( ruleQualifiedName ) ) (otherlv_16= ',' ( ( ruleQualifiedName ) ) )* otherlv_18= ']' ) | ( (lv_isCanInheritFromAll_19_0= 'All' ) ) ) otherlv_20= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_21= 'Applicable' otherlv_22= 'For' ( (otherlv_23= '[' ( ( ruleQualifiedName ) ) (otherlv_25= ',' ( ( ruleQualifiedName ) ) )* otherlv_27= ']' ) | ( (lv_isApplicableForAll_28_0= 'All' ) ) ) otherlv_29= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_30= 'Cardinality' ( (lv_cardinality_31_0= RULE_INT ) ) otherlv_32= ';' ) ) ) ) )* ) )
            // InternalConceptLanguage.g:548:5: ( ( ({...}? => ( ({...}? => ( ( (lv_isRootStructuralElement_10_0= 'IsRootStructuralElement' ) ) otherlv_11= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_12= 'Inherits' otherlv_13= 'From' ( (otherlv_14= '[' ( ( ruleQualifiedName ) ) (otherlv_16= ',' ( ( ruleQualifiedName ) ) )* otherlv_18= ']' ) | ( (lv_isCanInheritFromAll_19_0= 'All' ) ) ) otherlv_20= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_21= 'Applicable' otherlv_22= 'For' ( (otherlv_23= '[' ( ( ruleQualifiedName ) ) (otherlv_25= ',' ( ( ruleQualifiedName ) ) )* otherlv_27= ']' ) | ( (lv_isApplicableForAll_28_0= 'All' ) ) ) otherlv_29= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_30= 'Cardinality' ( (lv_cardinality_31_0= RULE_INT ) ) otherlv_32= ';' ) ) ) ) )* )
            {
             
            				  getUnorderedGroupHelper().enter(grammarAccess.getStructuralElementAccess().getUnorderedGroup_5());
            				
            // InternalConceptLanguage.g:551:5: ( ( ({...}? => ( ({...}? => ( ( (lv_isRootStructuralElement_10_0= 'IsRootStructuralElement' ) ) otherlv_11= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_12= 'Inherits' otherlv_13= 'From' ( (otherlv_14= '[' ( ( ruleQualifiedName ) ) (otherlv_16= ',' ( ( ruleQualifiedName ) ) )* otherlv_18= ']' ) | ( (lv_isCanInheritFromAll_19_0= 'All' ) ) ) otherlv_20= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_21= 'Applicable' otherlv_22= 'For' ( (otherlv_23= '[' ( ( ruleQualifiedName ) ) (otherlv_25= ',' ( ( ruleQualifiedName ) ) )* otherlv_27= ']' ) | ( (lv_isApplicableForAll_28_0= 'All' ) ) ) otherlv_29= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_30= 'Cardinality' ( (lv_cardinality_31_0= RULE_INT ) ) otherlv_32= ';' ) ) ) ) )* )
            // InternalConceptLanguage.g:552:6: ( ({...}? => ( ({...}? => ( ( (lv_isRootStructuralElement_10_0= 'IsRootStructuralElement' ) ) otherlv_11= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_12= 'Inherits' otherlv_13= 'From' ( (otherlv_14= '[' ( ( ruleQualifiedName ) ) (otherlv_16= ',' ( ( ruleQualifiedName ) ) )* otherlv_18= ']' ) | ( (lv_isCanInheritFromAll_19_0= 'All' ) ) ) otherlv_20= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_21= 'Applicable' otherlv_22= 'For' ( (otherlv_23= '[' ( ( ruleQualifiedName ) ) (otherlv_25= ',' ( ( ruleQualifiedName ) ) )* otherlv_27= ']' ) | ( (lv_isApplicableForAll_28_0= 'All' ) ) ) otherlv_29= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_30= 'Cardinality' ( (lv_cardinality_31_0= RULE_INT ) ) otherlv_32= ';' ) ) ) ) )*
            {
            // InternalConceptLanguage.g:552:6: ( ({...}? => ( ({...}? => ( ( (lv_isRootStructuralElement_10_0= 'IsRootStructuralElement' ) ) otherlv_11= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_12= 'Inherits' otherlv_13= 'From' ( (otherlv_14= '[' ( ( ruleQualifiedName ) ) (otherlv_16= ',' ( ( ruleQualifiedName ) ) )* otherlv_18= ']' ) | ( (lv_isCanInheritFromAll_19_0= 'All' ) ) ) otherlv_20= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_21= 'Applicable' otherlv_22= 'For' ( (otherlv_23= '[' ( ( ruleQualifiedName ) ) (otherlv_25= ',' ( ( ruleQualifiedName ) ) )* otherlv_27= ']' ) | ( (lv_isApplicableForAll_28_0= 'All' ) ) ) otherlv_29= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_30= 'Cardinality' ( (lv_cardinality_31_0= RULE_INT ) ) otherlv_32= ';' ) ) ) ) )*
            loop13:
            do {
                int alt13=5;
                int LA13_0 = input.LA(1);

                if ( LA13_0 == 21 && getUnorderedGroupHelper().canSelect(grammarAccess.getStructuralElementAccess().getUnorderedGroup_5(), 0) ) {
                    alt13=1;
                }
                else if ( LA13_0 == 23 && getUnorderedGroupHelper().canSelect(grammarAccess.getStructuralElementAccess().getUnorderedGroup_5(), 1) ) {
                    alt13=2;
                }
                else if ( LA13_0 == 29 && getUnorderedGroupHelper().canSelect(grammarAccess.getStructuralElementAccess().getUnorderedGroup_5(), 2) ) {
                    alt13=3;
                }
                else if ( LA13_0 == 31 && getUnorderedGroupHelper().canSelect(grammarAccess.getStructuralElementAccess().getUnorderedGroup_5(), 3) ) {
                    alt13=4;
                }


                switch (alt13) {
            	case 1 :
            	    // InternalConceptLanguage.g:553:4: ({...}? => ( ({...}? => ( ( (lv_isRootStructuralElement_10_0= 'IsRootStructuralElement' ) ) otherlv_11= ';' ) ) ) )
            	    {
            	    // InternalConceptLanguage.g:553:4: ({...}? => ( ({...}? => ( ( (lv_isRootStructuralElement_10_0= 'IsRootStructuralElement' ) ) otherlv_11= ';' ) ) ) )
            	    // InternalConceptLanguage.g:554:5: {...}? => ( ({...}? => ( ( (lv_isRootStructuralElement_10_0= 'IsRootStructuralElement' ) ) otherlv_11= ';' ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getStructuralElementAccess().getUnorderedGroup_5(), 0) ) {
            	        throw new FailedPredicateException(input, "ruleStructuralElement", "getUnorderedGroupHelper().canSelect(grammarAccess.getStructuralElementAccess().getUnorderedGroup_5(), 0)");
            	    }
            	    // InternalConceptLanguage.g:554:114: ( ({...}? => ( ( (lv_isRootStructuralElement_10_0= 'IsRootStructuralElement' ) ) otherlv_11= ';' ) ) )
            	    // InternalConceptLanguage.g:555:6: ({...}? => ( ( (lv_isRootStructuralElement_10_0= 'IsRootStructuralElement' ) ) otherlv_11= ';' ) )
            	    {

            	    						getUnorderedGroupHelper().select(grammarAccess.getStructuralElementAccess().getUnorderedGroup_5(), 0);
            	    					
            	    // InternalConceptLanguage.g:558:9: ({...}? => ( ( (lv_isRootStructuralElement_10_0= 'IsRootStructuralElement' ) ) otherlv_11= ';' ) )
            	    // InternalConceptLanguage.g:558:10: {...}? => ( ( (lv_isRootStructuralElement_10_0= 'IsRootStructuralElement' ) ) otherlv_11= ';' )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleStructuralElement", "true");
            	    }
            	    // InternalConceptLanguage.g:558:19: ( ( (lv_isRootStructuralElement_10_0= 'IsRootStructuralElement' ) ) otherlv_11= ';' )
            	    // InternalConceptLanguage.g:558:20: ( (lv_isRootStructuralElement_10_0= 'IsRootStructuralElement' ) ) otherlv_11= ';'
            	    {
            	    // InternalConceptLanguage.g:558:20: ( (lv_isRootStructuralElement_10_0= 'IsRootStructuralElement' ) )
            	    // InternalConceptLanguage.g:559:10: (lv_isRootStructuralElement_10_0= 'IsRootStructuralElement' )
            	    {
            	    // InternalConceptLanguage.g:559:10: (lv_isRootStructuralElement_10_0= 'IsRootStructuralElement' )
            	    // InternalConceptLanguage.g:560:11: lv_isRootStructuralElement_10_0= 'IsRootStructuralElement'
            	    {
            	    lv_isRootStructuralElement_10_0=(Token)match(input,21,FOLLOW_13); 

            	    											newLeafNode(lv_isRootStructuralElement_10_0, grammarAccess.getStructuralElementAccess().getIsRootStructuralElementIsRootStructuralElementKeyword_5_0_0_0());
            	    										

            	    											if (current==null) {
            	    												current = createModelElement(grammarAccess.getStructuralElementRule());
            	    											}
            	    											setWithLastConsumed(current, "isRootStructuralElement", lv_isRootStructuralElement_10_0 != null, "IsRootStructuralElement");
            	    										

            	    }


            	    }

            	    otherlv_11=(Token)match(input,22,FOLLOW_12); 

            	    									newLeafNode(otherlv_11, grammarAccess.getStructuralElementAccess().getSemicolonKeyword_5_0_1());
            	    								

            	    }


            	    }

            	     
            	    						getUnorderedGroupHelper().returnFromSelection(grammarAccess.getStructuralElementAccess().getUnorderedGroup_5());
            	    					

            	    }


            	    }


            	    }
            	    break;
            	case 2 :
            	    // InternalConceptLanguage.g:582:4: ({...}? => ( ({...}? => (otherlv_12= 'Inherits' otherlv_13= 'From' ( (otherlv_14= '[' ( ( ruleQualifiedName ) ) (otherlv_16= ',' ( ( ruleQualifiedName ) ) )* otherlv_18= ']' ) | ( (lv_isCanInheritFromAll_19_0= 'All' ) ) ) otherlv_20= ';' ) ) ) )
            	    {
            	    // InternalConceptLanguage.g:582:4: ({...}? => ( ({...}? => (otherlv_12= 'Inherits' otherlv_13= 'From' ( (otherlv_14= '[' ( ( ruleQualifiedName ) ) (otherlv_16= ',' ( ( ruleQualifiedName ) ) )* otherlv_18= ']' ) | ( (lv_isCanInheritFromAll_19_0= 'All' ) ) ) otherlv_20= ';' ) ) ) )
            	    // InternalConceptLanguage.g:583:5: {...}? => ( ({...}? => (otherlv_12= 'Inherits' otherlv_13= 'From' ( (otherlv_14= '[' ( ( ruleQualifiedName ) ) (otherlv_16= ',' ( ( ruleQualifiedName ) ) )* otherlv_18= ']' ) | ( (lv_isCanInheritFromAll_19_0= 'All' ) ) ) otherlv_20= ';' ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getStructuralElementAccess().getUnorderedGroup_5(), 1) ) {
            	        throw new FailedPredicateException(input, "ruleStructuralElement", "getUnorderedGroupHelper().canSelect(grammarAccess.getStructuralElementAccess().getUnorderedGroup_5(), 1)");
            	    }
            	    // InternalConceptLanguage.g:583:114: ( ({...}? => (otherlv_12= 'Inherits' otherlv_13= 'From' ( (otherlv_14= '[' ( ( ruleQualifiedName ) ) (otherlv_16= ',' ( ( ruleQualifiedName ) ) )* otherlv_18= ']' ) | ( (lv_isCanInheritFromAll_19_0= 'All' ) ) ) otherlv_20= ';' ) ) )
            	    // InternalConceptLanguage.g:584:6: ({...}? => (otherlv_12= 'Inherits' otherlv_13= 'From' ( (otherlv_14= '[' ( ( ruleQualifiedName ) ) (otherlv_16= ',' ( ( ruleQualifiedName ) ) )* otherlv_18= ']' ) | ( (lv_isCanInheritFromAll_19_0= 'All' ) ) ) otherlv_20= ';' ) )
            	    {

            	    						getUnorderedGroupHelper().select(grammarAccess.getStructuralElementAccess().getUnorderedGroup_5(), 1);
            	    					
            	    // InternalConceptLanguage.g:587:9: ({...}? => (otherlv_12= 'Inherits' otherlv_13= 'From' ( (otherlv_14= '[' ( ( ruleQualifiedName ) ) (otherlv_16= ',' ( ( ruleQualifiedName ) ) )* otherlv_18= ']' ) | ( (lv_isCanInheritFromAll_19_0= 'All' ) ) ) otherlv_20= ';' ) )
            	    // InternalConceptLanguage.g:587:10: {...}? => (otherlv_12= 'Inherits' otherlv_13= 'From' ( (otherlv_14= '[' ( ( ruleQualifiedName ) ) (otherlv_16= ',' ( ( ruleQualifiedName ) ) )* otherlv_18= ']' ) | ( (lv_isCanInheritFromAll_19_0= 'All' ) ) ) otherlv_20= ';' )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleStructuralElement", "true");
            	    }
            	    // InternalConceptLanguage.g:587:19: (otherlv_12= 'Inherits' otherlv_13= 'From' ( (otherlv_14= '[' ( ( ruleQualifiedName ) ) (otherlv_16= ',' ( ( ruleQualifiedName ) ) )* otherlv_18= ']' ) | ( (lv_isCanInheritFromAll_19_0= 'All' ) ) ) otherlv_20= ';' )
            	    // InternalConceptLanguage.g:587:20: otherlv_12= 'Inherits' otherlv_13= 'From' ( (otherlv_14= '[' ( ( ruleQualifiedName ) ) (otherlv_16= ',' ( ( ruleQualifiedName ) ) )* otherlv_18= ']' ) | ( (lv_isCanInheritFromAll_19_0= 'All' ) ) ) otherlv_20= ';'
            	    {
            	    otherlv_12=(Token)match(input,23,FOLLOW_14); 

            	    									newLeafNode(otherlv_12, grammarAccess.getStructuralElementAccess().getInheritsKeyword_5_1_0());
            	    								
            	    otherlv_13=(Token)match(input,24,FOLLOW_15); 

            	    									newLeafNode(otherlv_13, grammarAccess.getStructuralElementAccess().getFromKeyword_5_1_1());
            	    								
            	    // InternalConceptLanguage.g:595:9: ( (otherlv_14= '[' ( ( ruleQualifiedName ) ) (otherlv_16= ',' ( ( ruleQualifiedName ) ) )* otherlv_18= ']' ) | ( (lv_isCanInheritFromAll_19_0= 'All' ) ) )
            	    int alt10=2;
            	    int LA10_0 = input.LA(1);

            	    if ( (LA10_0==25) ) {
            	        alt10=1;
            	    }
            	    else if ( (LA10_0==28) ) {
            	        alt10=2;
            	    }
            	    else {
            	        NoViableAltException nvae =
            	            new NoViableAltException("", 10, 0, input);

            	        throw nvae;
            	    }
            	    switch (alt10) {
            	        case 1 :
            	            // InternalConceptLanguage.g:596:10: (otherlv_14= '[' ( ( ruleQualifiedName ) ) (otherlv_16= ',' ( ( ruleQualifiedName ) ) )* otherlv_18= ']' )
            	            {
            	            // InternalConceptLanguage.g:596:10: (otherlv_14= '[' ( ( ruleQualifiedName ) ) (otherlv_16= ',' ( ( ruleQualifiedName ) ) )* otherlv_18= ']' )
            	            // InternalConceptLanguage.g:597:11: otherlv_14= '[' ( ( ruleQualifiedName ) ) (otherlv_16= ',' ( ( ruleQualifiedName ) ) )* otherlv_18= ']'
            	            {
            	            otherlv_14=(Token)match(input,25,FOLLOW_3); 

            	            											newLeafNode(otherlv_14, grammarAccess.getStructuralElementAccess().getLeftSquareBracketKeyword_5_1_2_0_0());
            	            										
            	            // InternalConceptLanguage.g:601:11: ( ( ruleQualifiedName ) )
            	            // InternalConceptLanguage.g:602:12: ( ruleQualifiedName )
            	            {
            	            // InternalConceptLanguage.g:602:12: ( ruleQualifiedName )
            	            // InternalConceptLanguage.g:603:13: ruleQualifiedName
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

            	            // InternalConceptLanguage.g:617:11: (otherlv_16= ',' ( ( ruleQualifiedName ) ) )*
            	            loop9:
            	            do {
            	                int alt9=2;
            	                int LA9_0 = input.LA(1);

            	                if ( (LA9_0==26) ) {
            	                    alt9=1;
            	                }


            	                switch (alt9) {
            	            	case 1 :
            	            	    // InternalConceptLanguage.g:618:12: otherlv_16= ',' ( ( ruleQualifiedName ) )
            	            	    {
            	            	    otherlv_16=(Token)match(input,26,FOLLOW_3); 

            	            	    												newLeafNode(otherlv_16, grammarAccess.getStructuralElementAccess().getCommaKeyword_5_1_2_0_2_0());
            	            	    											
            	            	    // InternalConceptLanguage.g:622:12: ( ( ruleQualifiedName ) )
            	            	    // InternalConceptLanguage.g:623:13: ( ruleQualifiedName )
            	            	    {
            	            	    // InternalConceptLanguage.g:623:13: ( ruleQualifiedName )
            	            	    // InternalConceptLanguage.g:624:14: ruleQualifiedName
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
            	            	    break loop9;
            	                }
            	            } while (true);

            	            otherlv_18=(Token)match(input,27,FOLLOW_13); 

            	            											newLeafNode(otherlv_18, grammarAccess.getStructuralElementAccess().getRightSquareBracketKeyword_5_1_2_0_3());
            	            										

            	            }


            	            }
            	            break;
            	        case 2 :
            	            // InternalConceptLanguage.g:645:10: ( (lv_isCanInheritFromAll_19_0= 'All' ) )
            	            {
            	            // InternalConceptLanguage.g:645:10: ( (lv_isCanInheritFromAll_19_0= 'All' ) )
            	            // InternalConceptLanguage.g:646:11: (lv_isCanInheritFromAll_19_0= 'All' )
            	            {
            	            // InternalConceptLanguage.g:646:11: (lv_isCanInheritFromAll_19_0= 'All' )
            	            // InternalConceptLanguage.g:647:12: lv_isCanInheritFromAll_19_0= 'All'
            	            {
            	            lv_isCanInheritFromAll_19_0=(Token)match(input,28,FOLLOW_13); 

            	            												newLeafNode(lv_isCanInheritFromAll_19_0, grammarAccess.getStructuralElementAccess().getIsCanInheritFromAllAllKeyword_5_1_2_1_0());
            	            											

            	            												if (current==null) {
            	            													current = createModelElement(grammarAccess.getStructuralElementRule());
            	            												}
            	            												setWithLastConsumed(current, "isCanInheritFromAll", lv_isCanInheritFromAll_19_0 != null, "All");
            	            											

            	            }


            	            }


            	            }
            	            break;

            	    }

            	    otherlv_20=(Token)match(input,22,FOLLOW_12); 

            	    									newLeafNode(otherlv_20, grammarAccess.getStructuralElementAccess().getSemicolonKeyword_5_1_3());
            	    								

            	    }


            	    }

            	     
            	    						getUnorderedGroupHelper().returnFromSelection(grammarAccess.getStructuralElementAccess().getUnorderedGroup_5());
            	    					

            	    }


            	    }


            	    }
            	    break;
            	case 3 :
            	    // InternalConceptLanguage.g:670:4: ({...}? => ( ({...}? => (otherlv_21= 'Applicable' otherlv_22= 'For' ( (otherlv_23= '[' ( ( ruleQualifiedName ) ) (otherlv_25= ',' ( ( ruleQualifiedName ) ) )* otherlv_27= ']' ) | ( (lv_isApplicableForAll_28_0= 'All' ) ) ) otherlv_29= ';' ) ) ) )
            	    {
            	    // InternalConceptLanguage.g:670:4: ({...}? => ( ({...}? => (otherlv_21= 'Applicable' otherlv_22= 'For' ( (otherlv_23= '[' ( ( ruleQualifiedName ) ) (otherlv_25= ',' ( ( ruleQualifiedName ) ) )* otherlv_27= ']' ) | ( (lv_isApplicableForAll_28_0= 'All' ) ) ) otherlv_29= ';' ) ) ) )
            	    // InternalConceptLanguage.g:671:5: {...}? => ( ({...}? => (otherlv_21= 'Applicable' otherlv_22= 'For' ( (otherlv_23= '[' ( ( ruleQualifiedName ) ) (otherlv_25= ',' ( ( ruleQualifiedName ) ) )* otherlv_27= ']' ) | ( (lv_isApplicableForAll_28_0= 'All' ) ) ) otherlv_29= ';' ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getStructuralElementAccess().getUnorderedGroup_5(), 2) ) {
            	        throw new FailedPredicateException(input, "ruleStructuralElement", "getUnorderedGroupHelper().canSelect(grammarAccess.getStructuralElementAccess().getUnorderedGroup_5(), 2)");
            	    }
            	    // InternalConceptLanguage.g:671:114: ( ({...}? => (otherlv_21= 'Applicable' otherlv_22= 'For' ( (otherlv_23= '[' ( ( ruleQualifiedName ) ) (otherlv_25= ',' ( ( ruleQualifiedName ) ) )* otherlv_27= ']' ) | ( (lv_isApplicableForAll_28_0= 'All' ) ) ) otherlv_29= ';' ) ) )
            	    // InternalConceptLanguage.g:672:6: ({...}? => (otherlv_21= 'Applicable' otherlv_22= 'For' ( (otherlv_23= '[' ( ( ruleQualifiedName ) ) (otherlv_25= ',' ( ( ruleQualifiedName ) ) )* otherlv_27= ']' ) | ( (lv_isApplicableForAll_28_0= 'All' ) ) ) otherlv_29= ';' ) )
            	    {

            	    						getUnorderedGroupHelper().select(grammarAccess.getStructuralElementAccess().getUnorderedGroup_5(), 2);
            	    					
            	    // InternalConceptLanguage.g:675:9: ({...}? => (otherlv_21= 'Applicable' otherlv_22= 'For' ( (otherlv_23= '[' ( ( ruleQualifiedName ) ) (otherlv_25= ',' ( ( ruleQualifiedName ) ) )* otherlv_27= ']' ) | ( (lv_isApplicableForAll_28_0= 'All' ) ) ) otherlv_29= ';' ) )
            	    // InternalConceptLanguage.g:675:10: {...}? => (otherlv_21= 'Applicable' otherlv_22= 'For' ( (otherlv_23= '[' ( ( ruleQualifiedName ) ) (otherlv_25= ',' ( ( ruleQualifiedName ) ) )* otherlv_27= ']' ) | ( (lv_isApplicableForAll_28_0= 'All' ) ) ) otherlv_29= ';' )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleStructuralElement", "true");
            	    }
            	    // InternalConceptLanguage.g:675:19: (otherlv_21= 'Applicable' otherlv_22= 'For' ( (otherlv_23= '[' ( ( ruleQualifiedName ) ) (otherlv_25= ',' ( ( ruleQualifiedName ) ) )* otherlv_27= ']' ) | ( (lv_isApplicableForAll_28_0= 'All' ) ) ) otherlv_29= ';' )
            	    // InternalConceptLanguage.g:675:20: otherlv_21= 'Applicable' otherlv_22= 'For' ( (otherlv_23= '[' ( ( ruleQualifiedName ) ) (otherlv_25= ',' ( ( ruleQualifiedName ) ) )* otherlv_27= ']' ) | ( (lv_isApplicableForAll_28_0= 'All' ) ) ) otherlv_29= ';'
            	    {
            	    otherlv_21=(Token)match(input,29,FOLLOW_17); 

            	    									newLeafNode(otherlv_21, grammarAccess.getStructuralElementAccess().getApplicableKeyword_5_2_0());
            	    								
            	    otherlv_22=(Token)match(input,30,FOLLOW_15); 

            	    									newLeafNode(otherlv_22, grammarAccess.getStructuralElementAccess().getForKeyword_5_2_1());
            	    								
            	    // InternalConceptLanguage.g:683:9: ( (otherlv_23= '[' ( ( ruleQualifiedName ) ) (otherlv_25= ',' ( ( ruleQualifiedName ) ) )* otherlv_27= ']' ) | ( (lv_isApplicableForAll_28_0= 'All' ) ) )
            	    int alt12=2;
            	    int LA12_0 = input.LA(1);

            	    if ( (LA12_0==25) ) {
            	        alt12=1;
            	    }
            	    else if ( (LA12_0==28) ) {
            	        alt12=2;
            	    }
            	    else {
            	        NoViableAltException nvae =
            	            new NoViableAltException("", 12, 0, input);

            	        throw nvae;
            	    }
            	    switch (alt12) {
            	        case 1 :
            	            // InternalConceptLanguage.g:684:10: (otherlv_23= '[' ( ( ruleQualifiedName ) ) (otherlv_25= ',' ( ( ruleQualifiedName ) ) )* otherlv_27= ']' )
            	            {
            	            // InternalConceptLanguage.g:684:10: (otherlv_23= '[' ( ( ruleQualifiedName ) ) (otherlv_25= ',' ( ( ruleQualifiedName ) ) )* otherlv_27= ']' )
            	            // InternalConceptLanguage.g:685:11: otherlv_23= '[' ( ( ruleQualifiedName ) ) (otherlv_25= ',' ( ( ruleQualifiedName ) ) )* otherlv_27= ']'
            	            {
            	            otherlv_23=(Token)match(input,25,FOLLOW_3); 

            	            											newLeafNode(otherlv_23, grammarAccess.getStructuralElementAccess().getLeftSquareBracketKeyword_5_2_2_0_0());
            	            										
            	            // InternalConceptLanguage.g:689:11: ( ( ruleQualifiedName ) )
            	            // InternalConceptLanguage.g:690:12: ( ruleQualifiedName )
            	            {
            	            // InternalConceptLanguage.g:690:12: ( ruleQualifiedName )
            	            // InternalConceptLanguage.g:691:13: ruleQualifiedName
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

            	            // InternalConceptLanguage.g:705:11: (otherlv_25= ',' ( ( ruleQualifiedName ) ) )*
            	            loop11:
            	            do {
            	                int alt11=2;
            	                int LA11_0 = input.LA(1);

            	                if ( (LA11_0==26) ) {
            	                    alt11=1;
            	                }


            	                switch (alt11) {
            	            	case 1 :
            	            	    // InternalConceptLanguage.g:706:12: otherlv_25= ',' ( ( ruleQualifiedName ) )
            	            	    {
            	            	    otherlv_25=(Token)match(input,26,FOLLOW_3); 

            	            	    												newLeafNode(otherlv_25, grammarAccess.getStructuralElementAccess().getCommaKeyword_5_2_2_0_2_0());
            	            	    											
            	            	    // InternalConceptLanguage.g:710:12: ( ( ruleQualifiedName ) )
            	            	    // InternalConceptLanguage.g:711:13: ( ruleQualifiedName )
            	            	    {
            	            	    // InternalConceptLanguage.g:711:13: ( ruleQualifiedName )
            	            	    // InternalConceptLanguage.g:712:14: ruleQualifiedName
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
            	            	    break loop11;
            	                }
            	            } while (true);

            	            otherlv_27=(Token)match(input,27,FOLLOW_13); 

            	            											newLeafNode(otherlv_27, grammarAccess.getStructuralElementAccess().getRightSquareBracketKeyword_5_2_2_0_3());
            	            										

            	            }


            	            }
            	            break;
            	        case 2 :
            	            // InternalConceptLanguage.g:733:10: ( (lv_isApplicableForAll_28_0= 'All' ) )
            	            {
            	            // InternalConceptLanguage.g:733:10: ( (lv_isApplicableForAll_28_0= 'All' ) )
            	            // InternalConceptLanguage.g:734:11: (lv_isApplicableForAll_28_0= 'All' )
            	            {
            	            // InternalConceptLanguage.g:734:11: (lv_isApplicableForAll_28_0= 'All' )
            	            // InternalConceptLanguage.g:735:12: lv_isApplicableForAll_28_0= 'All'
            	            {
            	            lv_isApplicableForAll_28_0=(Token)match(input,28,FOLLOW_13); 

            	            												newLeafNode(lv_isApplicableForAll_28_0, grammarAccess.getStructuralElementAccess().getIsApplicableForAllAllKeyword_5_2_2_1_0());
            	            											

            	            												if (current==null) {
            	            													current = createModelElement(grammarAccess.getStructuralElementRule());
            	            												}
            	            												setWithLastConsumed(current, "isApplicableForAll", lv_isApplicableForAll_28_0 != null, "All");
            	            											

            	            }


            	            }


            	            }
            	            break;

            	    }

            	    otherlv_29=(Token)match(input,22,FOLLOW_12); 

            	    									newLeafNode(otherlv_29, grammarAccess.getStructuralElementAccess().getSemicolonKeyword_5_2_3());
            	    								

            	    }


            	    }

            	     
            	    						getUnorderedGroupHelper().returnFromSelection(grammarAccess.getStructuralElementAccess().getUnorderedGroup_5());
            	    					

            	    }


            	    }


            	    }
            	    break;
            	case 4 :
            	    // InternalConceptLanguage.g:758:4: ({...}? => ( ({...}? => (otherlv_30= 'Cardinality' ( (lv_cardinality_31_0= RULE_INT ) ) otherlv_32= ';' ) ) ) )
            	    {
            	    // InternalConceptLanguage.g:758:4: ({...}? => ( ({...}? => (otherlv_30= 'Cardinality' ( (lv_cardinality_31_0= RULE_INT ) ) otherlv_32= ';' ) ) ) )
            	    // InternalConceptLanguage.g:759:5: {...}? => ( ({...}? => (otherlv_30= 'Cardinality' ( (lv_cardinality_31_0= RULE_INT ) ) otherlv_32= ';' ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getStructuralElementAccess().getUnorderedGroup_5(), 3) ) {
            	        throw new FailedPredicateException(input, "ruleStructuralElement", "getUnorderedGroupHelper().canSelect(grammarAccess.getStructuralElementAccess().getUnorderedGroup_5(), 3)");
            	    }
            	    // InternalConceptLanguage.g:759:114: ( ({...}? => (otherlv_30= 'Cardinality' ( (lv_cardinality_31_0= RULE_INT ) ) otherlv_32= ';' ) ) )
            	    // InternalConceptLanguage.g:760:6: ({...}? => (otherlv_30= 'Cardinality' ( (lv_cardinality_31_0= RULE_INT ) ) otherlv_32= ';' ) )
            	    {

            	    						getUnorderedGroupHelper().select(grammarAccess.getStructuralElementAccess().getUnorderedGroup_5(), 3);
            	    					
            	    // InternalConceptLanguage.g:763:9: ({...}? => (otherlv_30= 'Cardinality' ( (lv_cardinality_31_0= RULE_INT ) ) otherlv_32= ';' ) )
            	    // InternalConceptLanguage.g:763:10: {...}? => (otherlv_30= 'Cardinality' ( (lv_cardinality_31_0= RULE_INT ) ) otherlv_32= ';' )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleStructuralElement", "true");
            	    }
            	    // InternalConceptLanguage.g:763:19: (otherlv_30= 'Cardinality' ( (lv_cardinality_31_0= RULE_INT ) ) otherlv_32= ';' )
            	    // InternalConceptLanguage.g:763:20: otherlv_30= 'Cardinality' ( (lv_cardinality_31_0= RULE_INT ) ) otherlv_32= ';'
            	    {
            	    otherlv_30=(Token)match(input,31,FOLLOW_6); 

            	    									newLeafNode(otherlv_30, grammarAccess.getStructuralElementAccess().getCardinalityKeyword_5_3_0());
            	    								
            	    // InternalConceptLanguage.g:767:9: ( (lv_cardinality_31_0= RULE_INT ) )
            	    // InternalConceptLanguage.g:768:10: (lv_cardinality_31_0= RULE_INT )
            	    {
            	    // InternalConceptLanguage.g:768:10: (lv_cardinality_31_0= RULE_INT )
            	    // InternalConceptLanguage.g:769:11: lv_cardinality_31_0= RULE_INT
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

            	    otherlv_32=(Token)match(input,22,FOLLOW_12); 

            	    									newLeafNode(otherlv_32, grammarAccess.getStructuralElementAccess().getSemicolonKeyword_5_3_2());
            	    								

            	    }


            	    }

            	     
            	    						getUnorderedGroupHelper().returnFromSelection(grammarAccess.getStructuralElementAccess().getUnorderedGroup_5());
            	    					

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop13;
                }
            } while (true);


            }


            }

             
            				  getUnorderedGroupHelper().leave(grammarAccess.getStructuralElementAccess().getUnorderedGroup_5());
            				

            }

            otherlv_33=(Token)match(input,18,FOLLOW_2); 

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
    // InternalConceptLanguage.g:810:1: entryRuleGeneralRelation returns [EObject current=null] : iv_ruleGeneralRelation= ruleGeneralRelation EOF ;
    public final EObject entryRuleGeneralRelation() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleGeneralRelation = null;


        try {
            // InternalConceptLanguage.g:810:56: (iv_ruleGeneralRelation= ruleGeneralRelation EOF )
            // InternalConceptLanguage.g:811:2: iv_ruleGeneralRelation= ruleGeneralRelation EOF
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
    // InternalConceptLanguage.g:817:1: ruleGeneralRelation returns [EObject current=null] : ( () otherlv_1= 'GeneralRelation' ( (lv_name_2_0= RULE_ID ) ) (otherlv_3= 'description' ( (lv_description_4_0= ruleEString ) ) )? otherlv_5= '{' otherlv_6= 'Referenced' otherlv_7= 'Type' ( ( ruleQualifiedName ) ) otherlv_9= ';' (otherlv_10= 'Applicable' otherlv_11= 'For' ( (otherlv_12= '[' ( ( ruleQualifiedName ) ) (otherlv_14= ',' ( ( ruleQualifiedName ) ) )* otherlv_16= ']' ) | ( (lv_isApplicableForAll_17_0= 'All' ) ) ) otherlv_18= ';' )? (otherlv_19= 'Cardinality' ( (lv_cardinality_20_0= RULE_INT ) ) otherlv_21= ';' )? otherlv_22= '}' ) ;
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
            // InternalConceptLanguage.g:823:2: ( ( () otherlv_1= 'GeneralRelation' ( (lv_name_2_0= RULE_ID ) ) (otherlv_3= 'description' ( (lv_description_4_0= ruleEString ) ) )? otherlv_5= '{' otherlv_6= 'Referenced' otherlv_7= 'Type' ( ( ruleQualifiedName ) ) otherlv_9= ';' (otherlv_10= 'Applicable' otherlv_11= 'For' ( (otherlv_12= '[' ( ( ruleQualifiedName ) ) (otherlv_14= ',' ( ( ruleQualifiedName ) ) )* otherlv_16= ']' ) | ( (lv_isApplicableForAll_17_0= 'All' ) ) ) otherlv_18= ';' )? (otherlv_19= 'Cardinality' ( (lv_cardinality_20_0= RULE_INT ) ) otherlv_21= ';' )? otherlv_22= '}' ) )
            // InternalConceptLanguage.g:824:2: ( () otherlv_1= 'GeneralRelation' ( (lv_name_2_0= RULE_ID ) ) (otherlv_3= 'description' ( (lv_description_4_0= ruleEString ) ) )? otherlv_5= '{' otherlv_6= 'Referenced' otherlv_7= 'Type' ( ( ruleQualifiedName ) ) otherlv_9= ';' (otherlv_10= 'Applicable' otherlv_11= 'For' ( (otherlv_12= '[' ( ( ruleQualifiedName ) ) (otherlv_14= ',' ( ( ruleQualifiedName ) ) )* otherlv_16= ']' ) | ( (lv_isApplicableForAll_17_0= 'All' ) ) ) otherlv_18= ';' )? (otherlv_19= 'Cardinality' ( (lv_cardinality_20_0= RULE_INT ) ) otherlv_21= ';' )? otherlv_22= '}' )
            {
            // InternalConceptLanguage.g:824:2: ( () otherlv_1= 'GeneralRelation' ( (lv_name_2_0= RULE_ID ) ) (otherlv_3= 'description' ( (lv_description_4_0= ruleEString ) ) )? otherlv_5= '{' otherlv_6= 'Referenced' otherlv_7= 'Type' ( ( ruleQualifiedName ) ) otherlv_9= ';' (otherlv_10= 'Applicable' otherlv_11= 'For' ( (otherlv_12= '[' ( ( ruleQualifiedName ) ) (otherlv_14= ',' ( ( ruleQualifiedName ) ) )* otherlv_16= ']' ) | ( (lv_isApplicableForAll_17_0= 'All' ) ) ) otherlv_18= ';' )? (otherlv_19= 'Cardinality' ( (lv_cardinality_20_0= RULE_INT ) ) otherlv_21= ';' )? otherlv_22= '}' )
            // InternalConceptLanguage.g:825:3: () otherlv_1= 'GeneralRelation' ( (lv_name_2_0= RULE_ID ) ) (otherlv_3= 'description' ( (lv_description_4_0= ruleEString ) ) )? otherlv_5= '{' otherlv_6= 'Referenced' otherlv_7= 'Type' ( ( ruleQualifiedName ) ) otherlv_9= ';' (otherlv_10= 'Applicable' otherlv_11= 'For' ( (otherlv_12= '[' ( ( ruleQualifiedName ) ) (otherlv_14= ',' ( ( ruleQualifiedName ) ) )* otherlv_16= ']' ) | ( (lv_isApplicableForAll_17_0= 'All' ) ) ) otherlv_18= ';' )? (otherlv_19= 'Cardinality' ( (lv_cardinality_20_0= RULE_INT ) ) otherlv_21= ';' )? otherlv_22= '}'
            {
            // InternalConceptLanguage.g:825:3: ()
            // InternalConceptLanguage.g:826:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getGeneralRelationAccess().getGeneralRelationAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,32,FOLLOW_3); 

            			newLeafNode(otherlv_1, grammarAccess.getGeneralRelationAccess().getGeneralRelationKeyword_1());
            		
            // InternalConceptLanguage.g:836:3: ( (lv_name_2_0= RULE_ID ) )
            // InternalConceptLanguage.g:837:4: (lv_name_2_0= RULE_ID )
            {
            // InternalConceptLanguage.g:837:4: (lv_name_2_0= RULE_ID )
            // InternalConceptLanguage.g:838:5: lv_name_2_0= RULE_ID
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

            // InternalConceptLanguage.g:854:3: (otherlv_3= 'description' ( (lv_description_4_0= ruleEString ) ) )?
            int alt14=2;
            int LA14_0 = input.LA(1);

            if ( (LA14_0==15) ) {
                alt14=1;
            }
            switch (alt14) {
                case 1 :
                    // InternalConceptLanguage.g:855:4: otherlv_3= 'description' ( (lv_description_4_0= ruleEString ) )
                    {
                    otherlv_3=(Token)match(input,15,FOLLOW_5); 

                    				newLeafNode(otherlv_3, grammarAccess.getGeneralRelationAccess().getDescriptionKeyword_3_0());
                    			
                    // InternalConceptLanguage.g:859:4: ( (lv_description_4_0= ruleEString ) )
                    // InternalConceptLanguage.g:860:5: (lv_description_4_0= ruleEString )
                    {
                    // InternalConceptLanguage.g:860:5: (lv_description_4_0= ruleEString )
                    // InternalConceptLanguage.g:861:6: lv_description_4_0= ruleEString
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

            otherlv_5=(Token)match(input,17,FOLLOW_20); 

            			newLeafNode(otherlv_5, grammarAccess.getGeneralRelationAccess().getLeftCurlyBracketKeyword_4());
            		
            otherlv_6=(Token)match(input,33,FOLLOW_21); 

            			newLeafNode(otherlv_6, grammarAccess.getGeneralRelationAccess().getReferencedKeyword_5());
            		
            otherlv_7=(Token)match(input,34,FOLLOW_3); 

            			newLeafNode(otherlv_7, grammarAccess.getGeneralRelationAccess().getTypeKeyword_6());
            		
            // InternalConceptLanguage.g:891:3: ( ( ruleQualifiedName ) )
            // InternalConceptLanguage.g:892:4: ( ruleQualifiedName )
            {
            // InternalConceptLanguage.g:892:4: ( ruleQualifiedName )
            // InternalConceptLanguage.g:893:5: ruleQualifiedName
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

            otherlv_9=(Token)match(input,22,FOLLOW_22); 

            			newLeafNode(otherlv_9, grammarAccess.getGeneralRelationAccess().getSemicolonKeyword_8());
            		
            // InternalConceptLanguage.g:911:3: (otherlv_10= 'Applicable' otherlv_11= 'For' ( (otherlv_12= '[' ( ( ruleQualifiedName ) ) (otherlv_14= ',' ( ( ruleQualifiedName ) ) )* otherlv_16= ']' ) | ( (lv_isApplicableForAll_17_0= 'All' ) ) ) otherlv_18= ';' )?
            int alt17=2;
            int LA17_0 = input.LA(1);

            if ( (LA17_0==29) ) {
                alt17=1;
            }
            switch (alt17) {
                case 1 :
                    // InternalConceptLanguage.g:912:4: otherlv_10= 'Applicable' otherlv_11= 'For' ( (otherlv_12= '[' ( ( ruleQualifiedName ) ) (otherlv_14= ',' ( ( ruleQualifiedName ) ) )* otherlv_16= ']' ) | ( (lv_isApplicableForAll_17_0= 'All' ) ) ) otherlv_18= ';'
                    {
                    otherlv_10=(Token)match(input,29,FOLLOW_17); 

                    				newLeafNode(otherlv_10, grammarAccess.getGeneralRelationAccess().getApplicableKeyword_9_0());
                    			
                    otherlv_11=(Token)match(input,30,FOLLOW_15); 

                    				newLeafNode(otherlv_11, grammarAccess.getGeneralRelationAccess().getForKeyword_9_1());
                    			
                    // InternalConceptLanguage.g:920:4: ( (otherlv_12= '[' ( ( ruleQualifiedName ) ) (otherlv_14= ',' ( ( ruleQualifiedName ) ) )* otherlv_16= ']' ) | ( (lv_isApplicableForAll_17_0= 'All' ) ) )
                    int alt16=2;
                    int LA16_0 = input.LA(1);

                    if ( (LA16_0==25) ) {
                        alt16=1;
                    }
                    else if ( (LA16_0==28) ) {
                        alt16=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 16, 0, input);

                        throw nvae;
                    }
                    switch (alt16) {
                        case 1 :
                            // InternalConceptLanguage.g:921:5: (otherlv_12= '[' ( ( ruleQualifiedName ) ) (otherlv_14= ',' ( ( ruleQualifiedName ) ) )* otherlv_16= ']' )
                            {
                            // InternalConceptLanguage.g:921:5: (otherlv_12= '[' ( ( ruleQualifiedName ) ) (otherlv_14= ',' ( ( ruleQualifiedName ) ) )* otherlv_16= ']' )
                            // InternalConceptLanguage.g:922:6: otherlv_12= '[' ( ( ruleQualifiedName ) ) (otherlv_14= ',' ( ( ruleQualifiedName ) ) )* otherlv_16= ']'
                            {
                            otherlv_12=(Token)match(input,25,FOLLOW_3); 

                            						newLeafNode(otherlv_12, grammarAccess.getGeneralRelationAccess().getLeftSquareBracketKeyword_9_2_0_0());
                            					
                            // InternalConceptLanguage.g:926:6: ( ( ruleQualifiedName ) )
                            // InternalConceptLanguage.g:927:7: ( ruleQualifiedName )
                            {
                            // InternalConceptLanguage.g:927:7: ( ruleQualifiedName )
                            // InternalConceptLanguage.g:928:8: ruleQualifiedName
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

                            // InternalConceptLanguage.g:942:6: (otherlv_14= ',' ( ( ruleQualifiedName ) ) )*
                            loop15:
                            do {
                                int alt15=2;
                                int LA15_0 = input.LA(1);

                                if ( (LA15_0==26) ) {
                                    alt15=1;
                                }


                                switch (alt15) {
                            	case 1 :
                            	    // InternalConceptLanguage.g:943:7: otherlv_14= ',' ( ( ruleQualifiedName ) )
                            	    {
                            	    otherlv_14=(Token)match(input,26,FOLLOW_3); 

                            	    							newLeafNode(otherlv_14, grammarAccess.getGeneralRelationAccess().getCommaKeyword_9_2_0_2_0());
                            	    						
                            	    // InternalConceptLanguage.g:947:7: ( ( ruleQualifiedName ) )
                            	    // InternalConceptLanguage.g:948:8: ( ruleQualifiedName )
                            	    {
                            	    // InternalConceptLanguage.g:948:8: ( ruleQualifiedName )
                            	    // InternalConceptLanguage.g:949:9: ruleQualifiedName
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
                            	    break loop15;
                                }
                            } while (true);

                            otherlv_16=(Token)match(input,27,FOLLOW_13); 

                            						newLeafNode(otherlv_16, grammarAccess.getGeneralRelationAccess().getRightSquareBracketKeyword_9_2_0_3());
                            					

                            }


                            }
                            break;
                        case 2 :
                            // InternalConceptLanguage.g:970:5: ( (lv_isApplicableForAll_17_0= 'All' ) )
                            {
                            // InternalConceptLanguage.g:970:5: ( (lv_isApplicableForAll_17_0= 'All' ) )
                            // InternalConceptLanguage.g:971:6: (lv_isApplicableForAll_17_0= 'All' )
                            {
                            // InternalConceptLanguage.g:971:6: (lv_isApplicableForAll_17_0= 'All' )
                            // InternalConceptLanguage.g:972:7: lv_isApplicableForAll_17_0= 'All'
                            {
                            lv_isApplicableForAll_17_0=(Token)match(input,28,FOLLOW_13); 

                            							newLeafNode(lv_isApplicableForAll_17_0, grammarAccess.getGeneralRelationAccess().getIsApplicableForAllAllKeyword_9_2_1_0());
                            						

                            							if (current==null) {
                            								current = createModelElement(grammarAccess.getGeneralRelationRule());
                            							}
                            							setWithLastConsumed(current, "isApplicableForAll", lv_isApplicableForAll_17_0 != null, "All");
                            						

                            }


                            }


                            }
                            break;

                    }

                    otherlv_18=(Token)match(input,22,FOLLOW_23); 

                    				newLeafNode(otherlv_18, grammarAccess.getGeneralRelationAccess().getSemicolonKeyword_9_3());
                    			

                    }
                    break;

            }

            // InternalConceptLanguage.g:990:3: (otherlv_19= 'Cardinality' ( (lv_cardinality_20_0= RULE_INT ) ) otherlv_21= ';' )?
            int alt18=2;
            int LA18_0 = input.LA(1);

            if ( (LA18_0==31) ) {
                alt18=1;
            }
            switch (alt18) {
                case 1 :
                    // InternalConceptLanguage.g:991:4: otherlv_19= 'Cardinality' ( (lv_cardinality_20_0= RULE_INT ) ) otherlv_21= ';'
                    {
                    otherlv_19=(Token)match(input,31,FOLLOW_6); 

                    				newLeafNode(otherlv_19, grammarAccess.getGeneralRelationAccess().getCardinalityKeyword_10_0());
                    			
                    // InternalConceptLanguage.g:995:4: ( (lv_cardinality_20_0= RULE_INT ) )
                    // InternalConceptLanguage.g:996:5: (lv_cardinality_20_0= RULE_INT )
                    {
                    // InternalConceptLanguage.g:996:5: (lv_cardinality_20_0= RULE_INT )
                    // InternalConceptLanguage.g:997:6: lv_cardinality_20_0= RULE_INT
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

                    otherlv_21=(Token)match(input,22,FOLLOW_24); 

                    				newLeafNode(otherlv_21, grammarAccess.getGeneralRelationAccess().getSemicolonKeyword_10_2());
                    			

                    }
                    break;

            }

            otherlv_22=(Token)match(input,18,FOLLOW_2); 

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
    // InternalConceptLanguage.g:1026:1: entryRuleARelation returns [EObject current=null] : iv_ruleARelation= ruleARelation EOF ;
    public final EObject entryRuleARelation() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleARelation = null;


        try {
            // InternalConceptLanguage.g:1026:50: (iv_ruleARelation= ruleARelation EOF )
            // InternalConceptLanguage.g:1027:2: iv_ruleARelation= ruleARelation EOF
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
    // InternalConceptLanguage.g:1033:1: ruleARelation returns [EObject current=null] : this_GeneralRelation_0= ruleGeneralRelation ;
    public final EObject ruleARelation() throws RecognitionException {
        EObject current = null;

        EObject this_GeneralRelation_0 = null;



        	enterRule();

        try {
            // InternalConceptLanguage.g:1039:2: (this_GeneralRelation_0= ruleGeneralRelation )
            // InternalConceptLanguage.g:1040:2: this_GeneralRelation_0= ruleGeneralRelation
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
    // InternalConceptLanguage.g:1051:1: entryRuleCategory returns [EObject current=null] : iv_ruleCategory= ruleCategory EOF ;
    public final EObject entryRuleCategory() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleCategory = null;


        try {
            // InternalConceptLanguage.g:1051:49: (iv_ruleCategory= ruleCategory EOF )
            // InternalConceptLanguage.g:1052:2: iv_ruleCategory= ruleCategory EOF
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
    // InternalConceptLanguage.g:1058:1: ruleCategory returns [EObject current=null] : ( () otherlv_1= 'Category' ( (lv_name_2_0= RULE_ID ) ) ( ( ( ( ({...}? => ( ({...}? => (otherlv_4= 'extends' ( ( ruleQualifiedName ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_6= 'shortname' ( (lv_shortName_7_0= RULE_ID ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_8= 'description' ( (lv_description_9_0= ruleEString ) ) ) ) ) ) )* ) ) ) otherlv_10= '{' ( ( ( ( ({...}? => ( ({...}? => ( ( (lv_isAbstract_12_0= 'IsAbstract' ) ) otherlv_13= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_isVerification_14_0= 'IsVerification' ) ) otherlv_15= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_16= 'Applicable' otherlv_17= 'For' ( (otherlv_18= '[' ( ( ruleQualifiedName ) ) (otherlv_20= ',' ( ( ruleQualifiedName ) ) )* otherlv_22= ']' ) | ( (lv_isApplicableForAll_23_0= 'All' ) ) ) otherlv_24= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_25= 'Cardinality' ( (lv_cardinality_26_0= RULE_INT ) ) otherlv_27= ';' ) ) ) ) )* ) ) ) ( (lv_properties_28_0= ruleAProperty ) )* ( (lv_equationDefinitions_29_0= ruleEquationDefinition ) )* otherlv_30= '}' ) ;
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
        Token lv_isVerification_14_0=null;
        Token otherlv_15=null;
        Token otherlv_16=null;
        Token otherlv_17=null;
        Token otherlv_18=null;
        Token otherlv_20=null;
        Token otherlv_22=null;
        Token lv_isApplicableForAll_23_0=null;
        Token otherlv_24=null;
        Token otherlv_25=null;
        Token lv_cardinality_26_0=null;
        Token otherlv_27=null;
        Token otherlv_30=null;
        AntlrDatatypeRuleToken lv_description_9_0 = null;

        EObject lv_properties_28_0 = null;

        EObject lv_equationDefinitions_29_0 = null;



        	enterRule();

        try {
            // InternalConceptLanguage.g:1064:2: ( ( () otherlv_1= 'Category' ( (lv_name_2_0= RULE_ID ) ) ( ( ( ( ({...}? => ( ({...}? => (otherlv_4= 'extends' ( ( ruleQualifiedName ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_6= 'shortname' ( (lv_shortName_7_0= RULE_ID ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_8= 'description' ( (lv_description_9_0= ruleEString ) ) ) ) ) ) )* ) ) ) otherlv_10= '{' ( ( ( ( ({...}? => ( ({...}? => ( ( (lv_isAbstract_12_0= 'IsAbstract' ) ) otherlv_13= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_isVerification_14_0= 'IsVerification' ) ) otherlv_15= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_16= 'Applicable' otherlv_17= 'For' ( (otherlv_18= '[' ( ( ruleQualifiedName ) ) (otherlv_20= ',' ( ( ruleQualifiedName ) ) )* otherlv_22= ']' ) | ( (lv_isApplicableForAll_23_0= 'All' ) ) ) otherlv_24= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_25= 'Cardinality' ( (lv_cardinality_26_0= RULE_INT ) ) otherlv_27= ';' ) ) ) ) )* ) ) ) ( (lv_properties_28_0= ruleAProperty ) )* ( (lv_equationDefinitions_29_0= ruleEquationDefinition ) )* otherlv_30= '}' ) )
            // InternalConceptLanguage.g:1065:2: ( () otherlv_1= 'Category' ( (lv_name_2_0= RULE_ID ) ) ( ( ( ( ({...}? => ( ({...}? => (otherlv_4= 'extends' ( ( ruleQualifiedName ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_6= 'shortname' ( (lv_shortName_7_0= RULE_ID ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_8= 'description' ( (lv_description_9_0= ruleEString ) ) ) ) ) ) )* ) ) ) otherlv_10= '{' ( ( ( ( ({...}? => ( ({...}? => ( ( (lv_isAbstract_12_0= 'IsAbstract' ) ) otherlv_13= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_isVerification_14_0= 'IsVerification' ) ) otherlv_15= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_16= 'Applicable' otherlv_17= 'For' ( (otherlv_18= '[' ( ( ruleQualifiedName ) ) (otherlv_20= ',' ( ( ruleQualifiedName ) ) )* otherlv_22= ']' ) | ( (lv_isApplicableForAll_23_0= 'All' ) ) ) otherlv_24= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_25= 'Cardinality' ( (lv_cardinality_26_0= RULE_INT ) ) otherlv_27= ';' ) ) ) ) )* ) ) ) ( (lv_properties_28_0= ruleAProperty ) )* ( (lv_equationDefinitions_29_0= ruleEquationDefinition ) )* otherlv_30= '}' )
            {
            // InternalConceptLanguage.g:1065:2: ( () otherlv_1= 'Category' ( (lv_name_2_0= RULE_ID ) ) ( ( ( ( ({...}? => ( ({...}? => (otherlv_4= 'extends' ( ( ruleQualifiedName ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_6= 'shortname' ( (lv_shortName_7_0= RULE_ID ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_8= 'description' ( (lv_description_9_0= ruleEString ) ) ) ) ) ) )* ) ) ) otherlv_10= '{' ( ( ( ( ({...}? => ( ({...}? => ( ( (lv_isAbstract_12_0= 'IsAbstract' ) ) otherlv_13= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_isVerification_14_0= 'IsVerification' ) ) otherlv_15= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_16= 'Applicable' otherlv_17= 'For' ( (otherlv_18= '[' ( ( ruleQualifiedName ) ) (otherlv_20= ',' ( ( ruleQualifiedName ) ) )* otherlv_22= ']' ) | ( (lv_isApplicableForAll_23_0= 'All' ) ) ) otherlv_24= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_25= 'Cardinality' ( (lv_cardinality_26_0= RULE_INT ) ) otherlv_27= ';' ) ) ) ) )* ) ) ) ( (lv_properties_28_0= ruleAProperty ) )* ( (lv_equationDefinitions_29_0= ruleEquationDefinition ) )* otherlv_30= '}' )
            // InternalConceptLanguage.g:1066:3: () otherlv_1= 'Category' ( (lv_name_2_0= RULE_ID ) ) ( ( ( ( ({...}? => ( ({...}? => (otherlv_4= 'extends' ( ( ruleQualifiedName ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_6= 'shortname' ( (lv_shortName_7_0= RULE_ID ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_8= 'description' ( (lv_description_9_0= ruleEString ) ) ) ) ) ) )* ) ) ) otherlv_10= '{' ( ( ( ( ({...}? => ( ({...}? => ( ( (lv_isAbstract_12_0= 'IsAbstract' ) ) otherlv_13= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_isVerification_14_0= 'IsVerification' ) ) otherlv_15= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_16= 'Applicable' otherlv_17= 'For' ( (otherlv_18= '[' ( ( ruleQualifiedName ) ) (otherlv_20= ',' ( ( ruleQualifiedName ) ) )* otherlv_22= ']' ) | ( (lv_isApplicableForAll_23_0= 'All' ) ) ) otherlv_24= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_25= 'Cardinality' ( (lv_cardinality_26_0= RULE_INT ) ) otherlv_27= ';' ) ) ) ) )* ) ) ) ( (lv_properties_28_0= ruleAProperty ) )* ( (lv_equationDefinitions_29_0= ruleEquationDefinition ) )* otherlv_30= '}'
            {
            // InternalConceptLanguage.g:1066:3: ()
            // InternalConceptLanguage.g:1067:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getCategoryAccess().getCategoryAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,35,FOLLOW_3); 

            			newLeafNode(otherlv_1, grammarAccess.getCategoryAccess().getCategoryKeyword_1());
            		
            // InternalConceptLanguage.g:1077:3: ( (lv_name_2_0= RULE_ID ) )
            // InternalConceptLanguage.g:1078:4: (lv_name_2_0= RULE_ID )
            {
            // InternalConceptLanguage.g:1078:4: (lv_name_2_0= RULE_ID )
            // InternalConceptLanguage.g:1079:5: lv_name_2_0= RULE_ID
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

            // InternalConceptLanguage.g:1095:3: ( ( ( ( ({...}? => ( ({...}? => (otherlv_4= 'extends' ( ( ruleQualifiedName ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_6= 'shortname' ( (lv_shortName_7_0= RULE_ID ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_8= 'description' ( (lv_description_9_0= ruleEString ) ) ) ) ) ) )* ) ) )
            // InternalConceptLanguage.g:1096:4: ( ( ( ({...}? => ( ({...}? => (otherlv_4= 'extends' ( ( ruleQualifiedName ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_6= 'shortname' ( (lv_shortName_7_0= RULE_ID ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_8= 'description' ( (lv_description_9_0= ruleEString ) ) ) ) ) ) )* ) )
            {
            // InternalConceptLanguage.g:1096:4: ( ( ( ({...}? => ( ({...}? => (otherlv_4= 'extends' ( ( ruleQualifiedName ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_6= 'shortname' ( (lv_shortName_7_0= RULE_ID ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_8= 'description' ( (lv_description_9_0= ruleEString ) ) ) ) ) ) )* ) )
            // InternalConceptLanguage.g:1097:5: ( ( ({...}? => ( ({...}? => (otherlv_4= 'extends' ( ( ruleQualifiedName ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_6= 'shortname' ( (lv_shortName_7_0= RULE_ID ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_8= 'description' ( (lv_description_9_0= ruleEString ) ) ) ) ) ) )* )
            {
             
            				  getUnorderedGroupHelper().enter(grammarAccess.getCategoryAccess().getUnorderedGroup_3());
            				
            // InternalConceptLanguage.g:1100:5: ( ( ({...}? => ( ({...}? => (otherlv_4= 'extends' ( ( ruleQualifiedName ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_6= 'shortname' ( (lv_shortName_7_0= RULE_ID ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_8= 'description' ( (lv_description_9_0= ruleEString ) ) ) ) ) ) )* )
            // InternalConceptLanguage.g:1101:6: ( ({...}? => ( ({...}? => (otherlv_4= 'extends' ( ( ruleQualifiedName ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_6= 'shortname' ( (lv_shortName_7_0= RULE_ID ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_8= 'description' ( (lv_description_9_0= ruleEString ) ) ) ) ) ) )*
            {
            // InternalConceptLanguage.g:1101:6: ( ({...}? => ( ({...}? => (otherlv_4= 'extends' ( ( ruleQualifiedName ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_6= 'shortname' ( (lv_shortName_7_0= RULE_ID ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_8= 'description' ( (lv_description_9_0= ruleEString ) ) ) ) ) ) )*
            loop19:
            do {
                int alt19=4;
                int LA19_0 = input.LA(1);

                if ( LA19_0 == 36 && getUnorderedGroupHelper().canSelect(grammarAccess.getCategoryAccess().getUnorderedGroup_3(), 0) ) {
                    alt19=1;
                }
                else if ( LA19_0 == 20 && getUnorderedGroupHelper().canSelect(grammarAccess.getCategoryAccess().getUnorderedGroup_3(), 1) ) {
                    alt19=2;
                }
                else if ( LA19_0 == 15 && getUnorderedGroupHelper().canSelect(grammarAccess.getCategoryAccess().getUnorderedGroup_3(), 2) ) {
                    alt19=3;
                }


                switch (alt19) {
            	case 1 :
            	    // InternalConceptLanguage.g:1102:4: ({...}? => ( ({...}? => (otherlv_4= 'extends' ( ( ruleQualifiedName ) ) ) ) ) )
            	    {
            	    // InternalConceptLanguage.g:1102:4: ({...}? => ( ({...}? => (otherlv_4= 'extends' ( ( ruleQualifiedName ) ) ) ) ) )
            	    // InternalConceptLanguage.g:1103:5: {...}? => ( ({...}? => (otherlv_4= 'extends' ( ( ruleQualifiedName ) ) ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getCategoryAccess().getUnorderedGroup_3(), 0) ) {
            	        throw new FailedPredicateException(input, "ruleCategory", "getUnorderedGroupHelper().canSelect(grammarAccess.getCategoryAccess().getUnorderedGroup_3(), 0)");
            	    }
            	    // InternalConceptLanguage.g:1103:105: ( ({...}? => (otherlv_4= 'extends' ( ( ruleQualifiedName ) ) ) ) )
            	    // InternalConceptLanguage.g:1104:6: ({...}? => (otherlv_4= 'extends' ( ( ruleQualifiedName ) ) ) )
            	    {

            	    						getUnorderedGroupHelper().select(grammarAccess.getCategoryAccess().getUnorderedGroup_3(), 0);
            	    					
            	    // InternalConceptLanguage.g:1107:9: ({...}? => (otherlv_4= 'extends' ( ( ruleQualifiedName ) ) ) )
            	    // InternalConceptLanguage.g:1107:10: {...}? => (otherlv_4= 'extends' ( ( ruleQualifiedName ) ) )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleCategory", "true");
            	    }
            	    // InternalConceptLanguage.g:1107:19: (otherlv_4= 'extends' ( ( ruleQualifiedName ) ) )
            	    // InternalConceptLanguage.g:1107:20: otherlv_4= 'extends' ( ( ruleQualifiedName ) )
            	    {
            	    otherlv_4=(Token)match(input,36,FOLLOW_3); 

            	    									newLeafNode(otherlv_4, grammarAccess.getCategoryAccess().getExtendsKeyword_3_0_0());
            	    								
            	    // InternalConceptLanguage.g:1111:9: ( ( ruleQualifiedName ) )
            	    // InternalConceptLanguage.g:1112:10: ( ruleQualifiedName )
            	    {
            	    // InternalConceptLanguage.g:1112:10: ( ruleQualifiedName )
            	    // InternalConceptLanguage.g:1113:11: ruleQualifiedName
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
            	    // InternalConceptLanguage.g:1133:4: ({...}? => ( ({...}? => (otherlv_6= 'shortname' ( (lv_shortName_7_0= RULE_ID ) ) ) ) ) )
            	    {
            	    // InternalConceptLanguage.g:1133:4: ({...}? => ( ({...}? => (otherlv_6= 'shortname' ( (lv_shortName_7_0= RULE_ID ) ) ) ) ) )
            	    // InternalConceptLanguage.g:1134:5: {...}? => ( ({...}? => (otherlv_6= 'shortname' ( (lv_shortName_7_0= RULE_ID ) ) ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getCategoryAccess().getUnorderedGroup_3(), 1) ) {
            	        throw new FailedPredicateException(input, "ruleCategory", "getUnorderedGroupHelper().canSelect(grammarAccess.getCategoryAccess().getUnorderedGroup_3(), 1)");
            	    }
            	    // InternalConceptLanguage.g:1134:105: ( ({...}? => (otherlv_6= 'shortname' ( (lv_shortName_7_0= RULE_ID ) ) ) ) )
            	    // InternalConceptLanguage.g:1135:6: ({...}? => (otherlv_6= 'shortname' ( (lv_shortName_7_0= RULE_ID ) ) ) )
            	    {

            	    						getUnorderedGroupHelper().select(grammarAccess.getCategoryAccess().getUnorderedGroup_3(), 1);
            	    					
            	    // InternalConceptLanguage.g:1138:9: ({...}? => (otherlv_6= 'shortname' ( (lv_shortName_7_0= RULE_ID ) ) ) )
            	    // InternalConceptLanguage.g:1138:10: {...}? => (otherlv_6= 'shortname' ( (lv_shortName_7_0= RULE_ID ) ) )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleCategory", "true");
            	    }
            	    // InternalConceptLanguage.g:1138:19: (otherlv_6= 'shortname' ( (lv_shortName_7_0= RULE_ID ) ) )
            	    // InternalConceptLanguage.g:1138:20: otherlv_6= 'shortname' ( (lv_shortName_7_0= RULE_ID ) )
            	    {
            	    otherlv_6=(Token)match(input,20,FOLLOW_3); 

            	    									newLeafNode(otherlv_6, grammarAccess.getCategoryAccess().getShortnameKeyword_3_1_0());
            	    								
            	    // InternalConceptLanguage.g:1142:9: ( (lv_shortName_7_0= RULE_ID ) )
            	    // InternalConceptLanguage.g:1143:10: (lv_shortName_7_0= RULE_ID )
            	    {
            	    // InternalConceptLanguage.g:1143:10: (lv_shortName_7_0= RULE_ID )
            	    // InternalConceptLanguage.g:1144:11: lv_shortName_7_0= RULE_ID
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
            	    // InternalConceptLanguage.g:1166:4: ({...}? => ( ({...}? => (otherlv_8= 'description' ( (lv_description_9_0= ruleEString ) ) ) ) ) )
            	    {
            	    // InternalConceptLanguage.g:1166:4: ({...}? => ( ({...}? => (otherlv_8= 'description' ( (lv_description_9_0= ruleEString ) ) ) ) ) )
            	    // InternalConceptLanguage.g:1167:5: {...}? => ( ({...}? => (otherlv_8= 'description' ( (lv_description_9_0= ruleEString ) ) ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getCategoryAccess().getUnorderedGroup_3(), 2) ) {
            	        throw new FailedPredicateException(input, "ruleCategory", "getUnorderedGroupHelper().canSelect(grammarAccess.getCategoryAccess().getUnorderedGroup_3(), 2)");
            	    }
            	    // InternalConceptLanguage.g:1167:105: ( ({...}? => (otherlv_8= 'description' ( (lv_description_9_0= ruleEString ) ) ) ) )
            	    // InternalConceptLanguage.g:1168:6: ({...}? => (otherlv_8= 'description' ( (lv_description_9_0= ruleEString ) ) ) )
            	    {

            	    						getUnorderedGroupHelper().select(grammarAccess.getCategoryAccess().getUnorderedGroup_3(), 2);
            	    					
            	    // InternalConceptLanguage.g:1171:9: ({...}? => (otherlv_8= 'description' ( (lv_description_9_0= ruleEString ) ) ) )
            	    // InternalConceptLanguage.g:1171:10: {...}? => (otherlv_8= 'description' ( (lv_description_9_0= ruleEString ) ) )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleCategory", "true");
            	    }
            	    // InternalConceptLanguage.g:1171:19: (otherlv_8= 'description' ( (lv_description_9_0= ruleEString ) ) )
            	    // InternalConceptLanguage.g:1171:20: otherlv_8= 'description' ( (lv_description_9_0= ruleEString ) )
            	    {
            	    otherlv_8=(Token)match(input,15,FOLLOW_5); 

            	    									newLeafNode(otherlv_8, grammarAccess.getCategoryAccess().getDescriptionKeyword_3_2_0());
            	    								
            	    // InternalConceptLanguage.g:1175:9: ( (lv_description_9_0= ruleEString ) )
            	    // InternalConceptLanguage.g:1176:10: (lv_description_9_0= ruleEString )
            	    {
            	    // InternalConceptLanguage.g:1176:10: (lv_description_9_0= ruleEString )
            	    // InternalConceptLanguage.g:1177:11: lv_description_9_0= ruleEString
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
            	    break loop19;
                }
            } while (true);


            }


            }

             
            				  getUnorderedGroupHelper().leave(grammarAccess.getCategoryAccess().getUnorderedGroup_3());
            				

            }

            otherlv_10=(Token)match(input,17,FOLLOW_26); 

            			newLeafNode(otherlv_10, grammarAccess.getCategoryAccess().getLeftCurlyBracketKeyword_4());
            		
            // InternalConceptLanguage.g:1211:3: ( ( ( ( ({...}? => ( ({...}? => ( ( (lv_isAbstract_12_0= 'IsAbstract' ) ) otherlv_13= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_isVerification_14_0= 'IsVerification' ) ) otherlv_15= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_16= 'Applicable' otherlv_17= 'For' ( (otherlv_18= '[' ( ( ruleQualifiedName ) ) (otherlv_20= ',' ( ( ruleQualifiedName ) ) )* otherlv_22= ']' ) | ( (lv_isApplicableForAll_23_0= 'All' ) ) ) otherlv_24= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_25= 'Cardinality' ( (lv_cardinality_26_0= RULE_INT ) ) otherlv_27= ';' ) ) ) ) )* ) ) )
            // InternalConceptLanguage.g:1212:4: ( ( ( ({...}? => ( ({...}? => ( ( (lv_isAbstract_12_0= 'IsAbstract' ) ) otherlv_13= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_isVerification_14_0= 'IsVerification' ) ) otherlv_15= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_16= 'Applicable' otherlv_17= 'For' ( (otherlv_18= '[' ( ( ruleQualifiedName ) ) (otherlv_20= ',' ( ( ruleQualifiedName ) ) )* otherlv_22= ']' ) | ( (lv_isApplicableForAll_23_0= 'All' ) ) ) otherlv_24= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_25= 'Cardinality' ( (lv_cardinality_26_0= RULE_INT ) ) otherlv_27= ';' ) ) ) ) )* ) )
            {
            // InternalConceptLanguage.g:1212:4: ( ( ( ({...}? => ( ({...}? => ( ( (lv_isAbstract_12_0= 'IsAbstract' ) ) otherlv_13= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_isVerification_14_0= 'IsVerification' ) ) otherlv_15= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_16= 'Applicable' otherlv_17= 'For' ( (otherlv_18= '[' ( ( ruleQualifiedName ) ) (otherlv_20= ',' ( ( ruleQualifiedName ) ) )* otherlv_22= ']' ) | ( (lv_isApplicableForAll_23_0= 'All' ) ) ) otherlv_24= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_25= 'Cardinality' ( (lv_cardinality_26_0= RULE_INT ) ) otherlv_27= ';' ) ) ) ) )* ) )
            // InternalConceptLanguage.g:1213:5: ( ( ({...}? => ( ({...}? => ( ( (lv_isAbstract_12_0= 'IsAbstract' ) ) otherlv_13= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_isVerification_14_0= 'IsVerification' ) ) otherlv_15= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_16= 'Applicable' otherlv_17= 'For' ( (otherlv_18= '[' ( ( ruleQualifiedName ) ) (otherlv_20= ',' ( ( ruleQualifiedName ) ) )* otherlv_22= ']' ) | ( (lv_isApplicableForAll_23_0= 'All' ) ) ) otherlv_24= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_25= 'Cardinality' ( (lv_cardinality_26_0= RULE_INT ) ) otherlv_27= ';' ) ) ) ) )* )
            {
             
            				  getUnorderedGroupHelper().enter(grammarAccess.getCategoryAccess().getUnorderedGroup_5());
            				
            // InternalConceptLanguage.g:1216:5: ( ( ({...}? => ( ({...}? => ( ( (lv_isAbstract_12_0= 'IsAbstract' ) ) otherlv_13= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_isVerification_14_0= 'IsVerification' ) ) otherlv_15= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_16= 'Applicable' otherlv_17= 'For' ( (otherlv_18= '[' ( ( ruleQualifiedName ) ) (otherlv_20= ',' ( ( ruleQualifiedName ) ) )* otherlv_22= ']' ) | ( (lv_isApplicableForAll_23_0= 'All' ) ) ) otherlv_24= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_25= 'Cardinality' ( (lv_cardinality_26_0= RULE_INT ) ) otherlv_27= ';' ) ) ) ) )* )
            // InternalConceptLanguage.g:1217:6: ( ({...}? => ( ({...}? => ( ( (lv_isAbstract_12_0= 'IsAbstract' ) ) otherlv_13= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_isVerification_14_0= 'IsVerification' ) ) otherlv_15= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_16= 'Applicable' otherlv_17= 'For' ( (otherlv_18= '[' ( ( ruleQualifiedName ) ) (otherlv_20= ',' ( ( ruleQualifiedName ) ) )* otherlv_22= ']' ) | ( (lv_isApplicableForAll_23_0= 'All' ) ) ) otherlv_24= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_25= 'Cardinality' ( (lv_cardinality_26_0= RULE_INT ) ) otherlv_27= ';' ) ) ) ) )*
            {
            // InternalConceptLanguage.g:1217:6: ( ({...}? => ( ({...}? => ( ( (lv_isAbstract_12_0= 'IsAbstract' ) ) otherlv_13= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_isVerification_14_0= 'IsVerification' ) ) otherlv_15= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_16= 'Applicable' otherlv_17= 'For' ( (otherlv_18= '[' ( ( ruleQualifiedName ) ) (otherlv_20= ',' ( ( ruleQualifiedName ) ) )* otherlv_22= ']' ) | ( (lv_isApplicableForAll_23_0= 'All' ) ) ) otherlv_24= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_25= 'Cardinality' ( (lv_cardinality_26_0= RULE_INT ) ) otherlv_27= ';' ) ) ) ) )*
            loop22:
            do {
                int alt22=5;
                int LA22_0 = input.LA(1);

                if ( LA22_0 == 37 && getUnorderedGroupHelper().canSelect(grammarAccess.getCategoryAccess().getUnorderedGroup_5(), 0) ) {
                    alt22=1;
                }
                else if ( LA22_0 == 38 && getUnorderedGroupHelper().canSelect(grammarAccess.getCategoryAccess().getUnorderedGroup_5(), 1) ) {
                    alt22=2;
                }
                else if ( LA22_0 == 29 && getUnorderedGroupHelper().canSelect(grammarAccess.getCategoryAccess().getUnorderedGroup_5(), 2) ) {
                    alt22=3;
                }
                else if ( LA22_0 == 31 && getUnorderedGroupHelper().canSelect(grammarAccess.getCategoryAccess().getUnorderedGroup_5(), 3) ) {
                    alt22=4;
                }


                switch (alt22) {
            	case 1 :
            	    // InternalConceptLanguage.g:1218:4: ({...}? => ( ({...}? => ( ( (lv_isAbstract_12_0= 'IsAbstract' ) ) otherlv_13= ';' ) ) ) )
            	    {
            	    // InternalConceptLanguage.g:1218:4: ({...}? => ( ({...}? => ( ( (lv_isAbstract_12_0= 'IsAbstract' ) ) otherlv_13= ';' ) ) ) )
            	    // InternalConceptLanguage.g:1219:5: {...}? => ( ({...}? => ( ( (lv_isAbstract_12_0= 'IsAbstract' ) ) otherlv_13= ';' ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getCategoryAccess().getUnorderedGroup_5(), 0) ) {
            	        throw new FailedPredicateException(input, "ruleCategory", "getUnorderedGroupHelper().canSelect(grammarAccess.getCategoryAccess().getUnorderedGroup_5(), 0)");
            	    }
            	    // InternalConceptLanguage.g:1219:105: ( ({...}? => ( ( (lv_isAbstract_12_0= 'IsAbstract' ) ) otherlv_13= ';' ) ) )
            	    // InternalConceptLanguage.g:1220:6: ({...}? => ( ( (lv_isAbstract_12_0= 'IsAbstract' ) ) otherlv_13= ';' ) )
            	    {

            	    						getUnorderedGroupHelper().select(grammarAccess.getCategoryAccess().getUnorderedGroup_5(), 0);
            	    					
            	    // InternalConceptLanguage.g:1223:9: ({...}? => ( ( (lv_isAbstract_12_0= 'IsAbstract' ) ) otherlv_13= ';' ) )
            	    // InternalConceptLanguage.g:1223:10: {...}? => ( ( (lv_isAbstract_12_0= 'IsAbstract' ) ) otherlv_13= ';' )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleCategory", "true");
            	    }
            	    // InternalConceptLanguage.g:1223:19: ( ( (lv_isAbstract_12_0= 'IsAbstract' ) ) otherlv_13= ';' )
            	    // InternalConceptLanguage.g:1223:20: ( (lv_isAbstract_12_0= 'IsAbstract' ) ) otherlv_13= ';'
            	    {
            	    // InternalConceptLanguage.g:1223:20: ( (lv_isAbstract_12_0= 'IsAbstract' ) )
            	    // InternalConceptLanguage.g:1224:10: (lv_isAbstract_12_0= 'IsAbstract' )
            	    {
            	    // InternalConceptLanguage.g:1224:10: (lv_isAbstract_12_0= 'IsAbstract' )
            	    // InternalConceptLanguage.g:1225:11: lv_isAbstract_12_0= 'IsAbstract'
            	    {
            	    lv_isAbstract_12_0=(Token)match(input,37,FOLLOW_13); 

            	    											newLeafNode(lv_isAbstract_12_0, grammarAccess.getCategoryAccess().getIsAbstractIsAbstractKeyword_5_0_0_0());
            	    										

            	    											if (current==null) {
            	    												current = createModelElement(grammarAccess.getCategoryRule());
            	    											}
            	    											setWithLastConsumed(current, "isAbstract", lv_isAbstract_12_0 != null, "IsAbstract");
            	    										

            	    }


            	    }

            	    otherlv_13=(Token)match(input,22,FOLLOW_26); 

            	    									newLeafNode(otherlv_13, grammarAccess.getCategoryAccess().getSemicolonKeyword_5_0_1());
            	    								

            	    }


            	    }

            	     
            	    						getUnorderedGroupHelper().returnFromSelection(grammarAccess.getCategoryAccess().getUnorderedGroup_5());
            	    					

            	    }


            	    }


            	    }
            	    break;
            	case 2 :
            	    // InternalConceptLanguage.g:1247:4: ({...}? => ( ({...}? => ( ( (lv_isVerification_14_0= 'IsVerification' ) ) otherlv_15= ';' ) ) ) )
            	    {
            	    // InternalConceptLanguage.g:1247:4: ({...}? => ( ({...}? => ( ( (lv_isVerification_14_0= 'IsVerification' ) ) otherlv_15= ';' ) ) ) )
            	    // InternalConceptLanguage.g:1248:5: {...}? => ( ({...}? => ( ( (lv_isVerification_14_0= 'IsVerification' ) ) otherlv_15= ';' ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getCategoryAccess().getUnorderedGroup_5(), 1) ) {
            	        throw new FailedPredicateException(input, "ruleCategory", "getUnorderedGroupHelper().canSelect(grammarAccess.getCategoryAccess().getUnorderedGroup_5(), 1)");
            	    }
            	    // InternalConceptLanguage.g:1248:105: ( ({...}? => ( ( (lv_isVerification_14_0= 'IsVerification' ) ) otherlv_15= ';' ) ) )
            	    // InternalConceptLanguage.g:1249:6: ({...}? => ( ( (lv_isVerification_14_0= 'IsVerification' ) ) otherlv_15= ';' ) )
            	    {

            	    						getUnorderedGroupHelper().select(grammarAccess.getCategoryAccess().getUnorderedGroup_5(), 1);
            	    					
            	    // InternalConceptLanguage.g:1252:9: ({...}? => ( ( (lv_isVerification_14_0= 'IsVerification' ) ) otherlv_15= ';' ) )
            	    // InternalConceptLanguage.g:1252:10: {...}? => ( ( (lv_isVerification_14_0= 'IsVerification' ) ) otherlv_15= ';' )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleCategory", "true");
            	    }
            	    // InternalConceptLanguage.g:1252:19: ( ( (lv_isVerification_14_0= 'IsVerification' ) ) otherlv_15= ';' )
            	    // InternalConceptLanguage.g:1252:20: ( (lv_isVerification_14_0= 'IsVerification' ) ) otherlv_15= ';'
            	    {
            	    // InternalConceptLanguage.g:1252:20: ( (lv_isVerification_14_0= 'IsVerification' ) )
            	    // InternalConceptLanguage.g:1253:10: (lv_isVerification_14_0= 'IsVerification' )
            	    {
            	    // InternalConceptLanguage.g:1253:10: (lv_isVerification_14_0= 'IsVerification' )
            	    // InternalConceptLanguage.g:1254:11: lv_isVerification_14_0= 'IsVerification'
            	    {
            	    lv_isVerification_14_0=(Token)match(input,38,FOLLOW_13); 

            	    											newLeafNode(lv_isVerification_14_0, grammarAccess.getCategoryAccess().getIsVerificationIsVerificationKeyword_5_1_0_0());
            	    										

            	    											if (current==null) {
            	    												current = createModelElement(grammarAccess.getCategoryRule());
            	    											}
            	    											setWithLastConsumed(current, "isVerification", lv_isVerification_14_0 != null, "IsVerification");
            	    										

            	    }


            	    }

            	    otherlv_15=(Token)match(input,22,FOLLOW_26); 

            	    									newLeafNode(otherlv_15, grammarAccess.getCategoryAccess().getSemicolonKeyword_5_1_1());
            	    								

            	    }


            	    }

            	     
            	    						getUnorderedGroupHelper().returnFromSelection(grammarAccess.getCategoryAccess().getUnorderedGroup_5());
            	    					

            	    }


            	    }


            	    }
            	    break;
            	case 3 :
            	    // InternalConceptLanguage.g:1276:4: ({...}? => ( ({...}? => (otherlv_16= 'Applicable' otherlv_17= 'For' ( (otherlv_18= '[' ( ( ruleQualifiedName ) ) (otherlv_20= ',' ( ( ruleQualifiedName ) ) )* otherlv_22= ']' ) | ( (lv_isApplicableForAll_23_0= 'All' ) ) ) otherlv_24= ';' ) ) ) )
            	    {
            	    // InternalConceptLanguage.g:1276:4: ({...}? => ( ({...}? => (otherlv_16= 'Applicable' otherlv_17= 'For' ( (otherlv_18= '[' ( ( ruleQualifiedName ) ) (otherlv_20= ',' ( ( ruleQualifiedName ) ) )* otherlv_22= ']' ) | ( (lv_isApplicableForAll_23_0= 'All' ) ) ) otherlv_24= ';' ) ) ) )
            	    // InternalConceptLanguage.g:1277:5: {...}? => ( ({...}? => (otherlv_16= 'Applicable' otherlv_17= 'For' ( (otherlv_18= '[' ( ( ruleQualifiedName ) ) (otherlv_20= ',' ( ( ruleQualifiedName ) ) )* otherlv_22= ']' ) | ( (lv_isApplicableForAll_23_0= 'All' ) ) ) otherlv_24= ';' ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getCategoryAccess().getUnorderedGroup_5(), 2) ) {
            	        throw new FailedPredicateException(input, "ruleCategory", "getUnorderedGroupHelper().canSelect(grammarAccess.getCategoryAccess().getUnorderedGroup_5(), 2)");
            	    }
            	    // InternalConceptLanguage.g:1277:105: ( ({...}? => (otherlv_16= 'Applicable' otherlv_17= 'For' ( (otherlv_18= '[' ( ( ruleQualifiedName ) ) (otherlv_20= ',' ( ( ruleQualifiedName ) ) )* otherlv_22= ']' ) | ( (lv_isApplicableForAll_23_0= 'All' ) ) ) otherlv_24= ';' ) ) )
            	    // InternalConceptLanguage.g:1278:6: ({...}? => (otherlv_16= 'Applicable' otherlv_17= 'For' ( (otherlv_18= '[' ( ( ruleQualifiedName ) ) (otherlv_20= ',' ( ( ruleQualifiedName ) ) )* otherlv_22= ']' ) | ( (lv_isApplicableForAll_23_0= 'All' ) ) ) otherlv_24= ';' ) )
            	    {

            	    						getUnorderedGroupHelper().select(grammarAccess.getCategoryAccess().getUnorderedGroup_5(), 2);
            	    					
            	    // InternalConceptLanguage.g:1281:9: ({...}? => (otherlv_16= 'Applicable' otherlv_17= 'For' ( (otherlv_18= '[' ( ( ruleQualifiedName ) ) (otherlv_20= ',' ( ( ruleQualifiedName ) ) )* otherlv_22= ']' ) | ( (lv_isApplicableForAll_23_0= 'All' ) ) ) otherlv_24= ';' ) )
            	    // InternalConceptLanguage.g:1281:10: {...}? => (otherlv_16= 'Applicable' otherlv_17= 'For' ( (otherlv_18= '[' ( ( ruleQualifiedName ) ) (otherlv_20= ',' ( ( ruleQualifiedName ) ) )* otherlv_22= ']' ) | ( (lv_isApplicableForAll_23_0= 'All' ) ) ) otherlv_24= ';' )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleCategory", "true");
            	    }
            	    // InternalConceptLanguage.g:1281:19: (otherlv_16= 'Applicable' otherlv_17= 'For' ( (otherlv_18= '[' ( ( ruleQualifiedName ) ) (otherlv_20= ',' ( ( ruleQualifiedName ) ) )* otherlv_22= ']' ) | ( (lv_isApplicableForAll_23_0= 'All' ) ) ) otherlv_24= ';' )
            	    // InternalConceptLanguage.g:1281:20: otherlv_16= 'Applicable' otherlv_17= 'For' ( (otherlv_18= '[' ( ( ruleQualifiedName ) ) (otherlv_20= ',' ( ( ruleQualifiedName ) ) )* otherlv_22= ']' ) | ( (lv_isApplicableForAll_23_0= 'All' ) ) ) otherlv_24= ';'
            	    {
            	    otherlv_16=(Token)match(input,29,FOLLOW_17); 

            	    									newLeafNode(otherlv_16, grammarAccess.getCategoryAccess().getApplicableKeyword_5_2_0());
            	    								
            	    otherlv_17=(Token)match(input,30,FOLLOW_15); 

            	    									newLeafNode(otherlv_17, grammarAccess.getCategoryAccess().getForKeyword_5_2_1());
            	    								
            	    // InternalConceptLanguage.g:1289:9: ( (otherlv_18= '[' ( ( ruleQualifiedName ) ) (otherlv_20= ',' ( ( ruleQualifiedName ) ) )* otherlv_22= ']' ) | ( (lv_isApplicableForAll_23_0= 'All' ) ) )
            	    int alt21=2;
            	    int LA21_0 = input.LA(1);

            	    if ( (LA21_0==25) ) {
            	        alt21=1;
            	    }
            	    else if ( (LA21_0==28) ) {
            	        alt21=2;
            	    }
            	    else {
            	        NoViableAltException nvae =
            	            new NoViableAltException("", 21, 0, input);

            	        throw nvae;
            	    }
            	    switch (alt21) {
            	        case 1 :
            	            // InternalConceptLanguage.g:1290:10: (otherlv_18= '[' ( ( ruleQualifiedName ) ) (otherlv_20= ',' ( ( ruleQualifiedName ) ) )* otherlv_22= ']' )
            	            {
            	            // InternalConceptLanguage.g:1290:10: (otherlv_18= '[' ( ( ruleQualifiedName ) ) (otherlv_20= ',' ( ( ruleQualifiedName ) ) )* otherlv_22= ']' )
            	            // InternalConceptLanguage.g:1291:11: otherlv_18= '[' ( ( ruleQualifiedName ) ) (otherlv_20= ',' ( ( ruleQualifiedName ) ) )* otherlv_22= ']'
            	            {
            	            otherlv_18=(Token)match(input,25,FOLLOW_3); 

            	            											newLeafNode(otherlv_18, grammarAccess.getCategoryAccess().getLeftSquareBracketKeyword_5_2_2_0_0());
            	            										
            	            // InternalConceptLanguage.g:1295:11: ( ( ruleQualifiedName ) )
            	            // InternalConceptLanguage.g:1296:12: ( ruleQualifiedName )
            	            {
            	            // InternalConceptLanguage.g:1296:12: ( ruleQualifiedName )
            	            // InternalConceptLanguage.g:1297:13: ruleQualifiedName
            	            {

            	            													if (current==null) {
            	            														current = createModelElement(grammarAccess.getCategoryRule());
            	            													}
            	            												

            	            													newCompositeNode(grammarAccess.getCategoryAccess().getApplicableForStructuralElementCrossReference_5_2_2_0_1_0());
            	            												
            	            pushFollow(FOLLOW_16);
            	            ruleQualifiedName();

            	            state._fsp--;


            	            													afterParserOrEnumRuleCall();
            	            												

            	            }


            	            }

            	            // InternalConceptLanguage.g:1311:11: (otherlv_20= ',' ( ( ruleQualifiedName ) ) )*
            	            loop20:
            	            do {
            	                int alt20=2;
            	                int LA20_0 = input.LA(1);

            	                if ( (LA20_0==26) ) {
            	                    alt20=1;
            	                }


            	                switch (alt20) {
            	            	case 1 :
            	            	    // InternalConceptLanguage.g:1312:12: otherlv_20= ',' ( ( ruleQualifiedName ) )
            	            	    {
            	            	    otherlv_20=(Token)match(input,26,FOLLOW_3); 

            	            	    												newLeafNode(otherlv_20, grammarAccess.getCategoryAccess().getCommaKeyword_5_2_2_0_2_0());
            	            	    											
            	            	    // InternalConceptLanguage.g:1316:12: ( ( ruleQualifiedName ) )
            	            	    // InternalConceptLanguage.g:1317:13: ( ruleQualifiedName )
            	            	    {
            	            	    // InternalConceptLanguage.g:1317:13: ( ruleQualifiedName )
            	            	    // InternalConceptLanguage.g:1318:14: ruleQualifiedName
            	            	    {

            	            	    														if (current==null) {
            	            	    															current = createModelElement(grammarAccess.getCategoryRule());
            	            	    														}
            	            	    													

            	            	    														newCompositeNode(grammarAccess.getCategoryAccess().getApplicableForStructuralElementCrossReference_5_2_2_0_2_1_0());
            	            	    													
            	            	    pushFollow(FOLLOW_16);
            	            	    ruleQualifiedName();

            	            	    state._fsp--;


            	            	    														afterParserOrEnumRuleCall();
            	            	    													

            	            	    }


            	            	    }


            	            	    }
            	            	    break;

            	            	default :
            	            	    break loop20;
            	                }
            	            } while (true);

            	            otherlv_22=(Token)match(input,27,FOLLOW_13); 

            	            											newLeafNode(otherlv_22, grammarAccess.getCategoryAccess().getRightSquareBracketKeyword_5_2_2_0_3());
            	            										

            	            }


            	            }
            	            break;
            	        case 2 :
            	            // InternalConceptLanguage.g:1339:10: ( (lv_isApplicableForAll_23_0= 'All' ) )
            	            {
            	            // InternalConceptLanguage.g:1339:10: ( (lv_isApplicableForAll_23_0= 'All' ) )
            	            // InternalConceptLanguage.g:1340:11: (lv_isApplicableForAll_23_0= 'All' )
            	            {
            	            // InternalConceptLanguage.g:1340:11: (lv_isApplicableForAll_23_0= 'All' )
            	            // InternalConceptLanguage.g:1341:12: lv_isApplicableForAll_23_0= 'All'
            	            {
            	            lv_isApplicableForAll_23_0=(Token)match(input,28,FOLLOW_13); 

            	            												newLeafNode(lv_isApplicableForAll_23_0, grammarAccess.getCategoryAccess().getIsApplicableForAllAllKeyword_5_2_2_1_0());
            	            											

            	            												if (current==null) {
            	            													current = createModelElement(grammarAccess.getCategoryRule());
            	            												}
            	            												setWithLastConsumed(current, "isApplicableForAll", lv_isApplicableForAll_23_0 != null, "All");
            	            											

            	            }


            	            }


            	            }
            	            break;

            	    }

            	    otherlv_24=(Token)match(input,22,FOLLOW_26); 

            	    									newLeafNode(otherlv_24, grammarAccess.getCategoryAccess().getSemicolonKeyword_5_2_3());
            	    								

            	    }


            	    }

            	     
            	    						getUnorderedGroupHelper().returnFromSelection(grammarAccess.getCategoryAccess().getUnorderedGroup_5());
            	    					

            	    }


            	    }


            	    }
            	    break;
            	case 4 :
            	    // InternalConceptLanguage.g:1364:4: ({...}? => ( ({...}? => (otherlv_25= 'Cardinality' ( (lv_cardinality_26_0= RULE_INT ) ) otherlv_27= ';' ) ) ) )
            	    {
            	    // InternalConceptLanguage.g:1364:4: ({...}? => ( ({...}? => (otherlv_25= 'Cardinality' ( (lv_cardinality_26_0= RULE_INT ) ) otherlv_27= ';' ) ) ) )
            	    // InternalConceptLanguage.g:1365:5: {...}? => ( ({...}? => (otherlv_25= 'Cardinality' ( (lv_cardinality_26_0= RULE_INT ) ) otherlv_27= ';' ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getCategoryAccess().getUnorderedGroup_5(), 3) ) {
            	        throw new FailedPredicateException(input, "ruleCategory", "getUnorderedGroupHelper().canSelect(grammarAccess.getCategoryAccess().getUnorderedGroup_5(), 3)");
            	    }
            	    // InternalConceptLanguage.g:1365:105: ( ({...}? => (otherlv_25= 'Cardinality' ( (lv_cardinality_26_0= RULE_INT ) ) otherlv_27= ';' ) ) )
            	    // InternalConceptLanguage.g:1366:6: ({...}? => (otherlv_25= 'Cardinality' ( (lv_cardinality_26_0= RULE_INT ) ) otherlv_27= ';' ) )
            	    {

            	    						getUnorderedGroupHelper().select(grammarAccess.getCategoryAccess().getUnorderedGroup_5(), 3);
            	    					
            	    // InternalConceptLanguage.g:1369:9: ({...}? => (otherlv_25= 'Cardinality' ( (lv_cardinality_26_0= RULE_INT ) ) otherlv_27= ';' ) )
            	    // InternalConceptLanguage.g:1369:10: {...}? => (otherlv_25= 'Cardinality' ( (lv_cardinality_26_0= RULE_INT ) ) otherlv_27= ';' )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleCategory", "true");
            	    }
            	    // InternalConceptLanguage.g:1369:19: (otherlv_25= 'Cardinality' ( (lv_cardinality_26_0= RULE_INT ) ) otherlv_27= ';' )
            	    // InternalConceptLanguage.g:1369:20: otherlv_25= 'Cardinality' ( (lv_cardinality_26_0= RULE_INT ) ) otherlv_27= ';'
            	    {
            	    otherlv_25=(Token)match(input,31,FOLLOW_6); 

            	    									newLeafNode(otherlv_25, grammarAccess.getCategoryAccess().getCardinalityKeyword_5_3_0());
            	    								
            	    // InternalConceptLanguage.g:1373:9: ( (lv_cardinality_26_0= RULE_INT ) )
            	    // InternalConceptLanguage.g:1374:10: (lv_cardinality_26_0= RULE_INT )
            	    {
            	    // InternalConceptLanguage.g:1374:10: (lv_cardinality_26_0= RULE_INT )
            	    // InternalConceptLanguage.g:1375:11: lv_cardinality_26_0= RULE_INT
            	    {
            	    lv_cardinality_26_0=(Token)match(input,RULE_INT,FOLLOW_13); 

            	    											newLeafNode(lv_cardinality_26_0, grammarAccess.getCategoryAccess().getCardinalityINTTerminalRuleCall_5_3_1_0());
            	    										

            	    											if (current==null) {
            	    												current = createModelElement(grammarAccess.getCategoryRule());
            	    											}
            	    											setWithLastConsumed(
            	    												current,
            	    												"cardinality",
            	    												lv_cardinality_26_0,
            	    												"org.eclipse.xtext.common.Terminals.INT");
            	    										

            	    }


            	    }

            	    otherlv_27=(Token)match(input,22,FOLLOW_26); 

            	    									newLeafNode(otherlv_27, grammarAccess.getCategoryAccess().getSemicolonKeyword_5_3_2());
            	    								

            	    }


            	    }

            	     
            	    						getUnorderedGroupHelper().returnFromSelection(grammarAccess.getCategoryAccess().getUnorderedGroup_5());
            	    					

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop22;
                }
            } while (true);


            }


            }

             
            				  getUnorderedGroupHelper().leave(grammarAccess.getCategoryAccess().getUnorderedGroup_5());
            				

            }

            // InternalConceptLanguage.g:1408:3: ( (lv_properties_28_0= ruleAProperty ) )*
            loop23:
            do {
                int alt23=2;
                int LA23_0 = input.LA(1);

                if ( (LA23_0==34||LA23_0==46||(LA23_0>=48 && LA23_0<=51)||(LA23_0>=54 && LA23_0<=56)) ) {
                    alt23=1;
                }


                switch (alt23) {
            	case 1 :
            	    // InternalConceptLanguage.g:1409:4: (lv_properties_28_0= ruleAProperty )
            	    {
            	    // InternalConceptLanguage.g:1409:4: (lv_properties_28_0= ruleAProperty )
            	    // InternalConceptLanguage.g:1410:5: lv_properties_28_0= ruleAProperty
            	    {

            	    					newCompositeNode(grammarAccess.getCategoryAccess().getPropertiesAPropertyParserRuleCall_6_0());
            	    				
            	    pushFollow(FOLLOW_27);
            	    lv_properties_28_0=ruleAProperty();

            	    state._fsp--;


            	    					if (current==null) {
            	    						current = createModelElementForParent(grammarAccess.getCategoryRule());
            	    					}
            	    					add(
            	    						current,
            	    						"properties",
            	    						lv_properties_28_0,
            	    						"de.dlr.sc.virsat.model.concept.ConceptLanguage.AProperty");
            	    					afterParserOrEnumRuleCall();
            	    				

            	    }


            	    }
            	    break;

            	default :
            	    break loop23;
                }
            } while (true);

            // InternalConceptLanguage.g:1427:3: ( (lv_equationDefinitions_29_0= ruleEquationDefinition ) )*
            loop24:
            do {
                int alt24=2;
                int LA24_0 = input.LA(1);

                if ( ((LA24_0>=57 && LA24_0<=58)) ) {
                    alt24=1;
                }


                switch (alt24) {
            	case 1 :
            	    // InternalConceptLanguage.g:1428:4: (lv_equationDefinitions_29_0= ruleEquationDefinition )
            	    {
            	    // InternalConceptLanguage.g:1428:4: (lv_equationDefinitions_29_0= ruleEquationDefinition )
            	    // InternalConceptLanguage.g:1429:5: lv_equationDefinitions_29_0= ruleEquationDefinition
            	    {

            	    					newCompositeNode(grammarAccess.getCategoryAccess().getEquationDefinitionsEquationDefinitionParserRuleCall_7_0());
            	    				
            	    pushFollow(FOLLOW_28);
            	    lv_equationDefinitions_29_0=ruleEquationDefinition();

            	    state._fsp--;


            	    					if (current==null) {
            	    						current = createModelElementForParent(grammarAccess.getCategoryRule());
            	    					}
            	    					add(
            	    						current,
            	    						"equationDefinitions",
            	    						lv_equationDefinitions_29_0,
            	    						"de.dlr.sc.virsat.model.concept.ConceptLanguage.EquationDefinition");
            	    					afterParserOrEnumRuleCall();
            	    				

            	    }


            	    }
            	    break;

            	default :
            	    break loop24;
                }
            } while (true);

            otherlv_30=(Token)match(input,18,FOLLOW_2); 

            			newLeafNode(otherlv_30, grammarAccess.getCategoryAccess().getRightCurlyBracketKeyword_8());
            		

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
    // InternalConceptLanguage.g:1454:1: entryRuleConceptImport returns [EObject current=null] : iv_ruleConceptImport= ruleConceptImport EOF ;
    public final EObject entryRuleConceptImport() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleConceptImport = null;


        try {
            // InternalConceptLanguage.g:1454:54: (iv_ruleConceptImport= ruleConceptImport EOF )
            // InternalConceptLanguage.g:1455:2: iv_ruleConceptImport= ruleConceptImport EOF
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
    // InternalConceptLanguage.g:1461:1: ruleConceptImport returns [EObject current=null] : ( () otherlv_1= 'Import' ( (lv_importedNamespace_2_0= ruleQualifiedNameWithWildcard ) ) otherlv_3= ';' ) ;
    public final EObject ruleConceptImport() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_3=null;
        AntlrDatatypeRuleToken lv_importedNamespace_2_0 = null;



        	enterRule();

        try {
            // InternalConceptLanguage.g:1467:2: ( ( () otherlv_1= 'Import' ( (lv_importedNamespace_2_0= ruleQualifiedNameWithWildcard ) ) otherlv_3= ';' ) )
            // InternalConceptLanguage.g:1468:2: ( () otherlv_1= 'Import' ( (lv_importedNamespace_2_0= ruleQualifiedNameWithWildcard ) ) otherlv_3= ';' )
            {
            // InternalConceptLanguage.g:1468:2: ( () otherlv_1= 'Import' ( (lv_importedNamespace_2_0= ruleQualifiedNameWithWildcard ) ) otherlv_3= ';' )
            // InternalConceptLanguage.g:1469:3: () otherlv_1= 'Import' ( (lv_importedNamespace_2_0= ruleQualifiedNameWithWildcard ) ) otherlv_3= ';'
            {
            // InternalConceptLanguage.g:1469:3: ()
            // InternalConceptLanguage.g:1470:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getConceptImportAccess().getConceptImportAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,39,FOLLOW_3); 

            			newLeafNode(otherlv_1, grammarAccess.getConceptImportAccess().getImportKeyword_1());
            		
            // InternalConceptLanguage.g:1480:3: ( (lv_importedNamespace_2_0= ruleQualifiedNameWithWildcard ) )
            // InternalConceptLanguage.g:1481:4: (lv_importedNamespace_2_0= ruleQualifiedNameWithWildcard )
            {
            // InternalConceptLanguage.g:1481:4: (lv_importedNamespace_2_0= ruleQualifiedNameWithWildcard )
            // InternalConceptLanguage.g:1482:5: lv_importedNamespace_2_0= ruleQualifiedNameWithWildcard
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

            otherlv_3=(Token)match(input,22,FOLLOW_2); 

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


    // $ANTLR start "entryRuleEcoreImport"
    // InternalConceptLanguage.g:1507:1: entryRuleEcoreImport returns [EObject current=null] : iv_ruleEcoreImport= ruleEcoreImport EOF ;
    public final EObject entryRuleEcoreImport() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleEcoreImport = null;


        try {
            // InternalConceptLanguage.g:1507:52: (iv_ruleEcoreImport= ruleEcoreImport EOF )
            // InternalConceptLanguage.g:1508:2: iv_ruleEcoreImport= ruleEcoreImport EOF
            {
             newCompositeNode(grammarAccess.getEcoreImportRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleEcoreImport=ruleEcoreImport();

            state._fsp--;

             current =iv_ruleEcoreImport; 
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
    // $ANTLR end "entryRuleEcoreImport"


    // $ANTLR start "ruleEcoreImport"
    // InternalConceptLanguage.g:1514:1: ruleEcoreImport returns [EObject current=null] : ( () otherlv_1= 'EImport' ( (lv_importedNsURI_2_0= RULE_STRING ) ) (otherlv_3= 'genModel' ( (lv_importedGenModel_4_0= RULE_STRING ) ) )? otherlv_5= ';' ) ;
    public final EObject ruleEcoreImport() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token lv_importedNsURI_2_0=null;
        Token otherlv_3=null;
        Token lv_importedGenModel_4_0=null;
        Token otherlv_5=null;


        	enterRule();

        try {
            // InternalConceptLanguage.g:1520:2: ( ( () otherlv_1= 'EImport' ( (lv_importedNsURI_2_0= RULE_STRING ) ) (otherlv_3= 'genModel' ( (lv_importedGenModel_4_0= RULE_STRING ) ) )? otherlv_5= ';' ) )
            // InternalConceptLanguage.g:1521:2: ( () otherlv_1= 'EImport' ( (lv_importedNsURI_2_0= RULE_STRING ) ) (otherlv_3= 'genModel' ( (lv_importedGenModel_4_0= RULE_STRING ) ) )? otherlv_5= ';' )
            {
            // InternalConceptLanguage.g:1521:2: ( () otherlv_1= 'EImport' ( (lv_importedNsURI_2_0= RULE_STRING ) ) (otherlv_3= 'genModel' ( (lv_importedGenModel_4_0= RULE_STRING ) ) )? otherlv_5= ';' )
            // InternalConceptLanguage.g:1522:3: () otherlv_1= 'EImport' ( (lv_importedNsURI_2_0= RULE_STRING ) ) (otherlv_3= 'genModel' ( (lv_importedGenModel_4_0= RULE_STRING ) ) )? otherlv_5= ';'
            {
            // InternalConceptLanguage.g:1522:3: ()
            // InternalConceptLanguage.g:1523:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getEcoreImportAccess().getEcoreImportAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,40,FOLLOW_5); 

            			newLeafNode(otherlv_1, grammarAccess.getEcoreImportAccess().getEImportKeyword_1());
            		
            // InternalConceptLanguage.g:1533:3: ( (lv_importedNsURI_2_0= RULE_STRING ) )
            // InternalConceptLanguage.g:1534:4: (lv_importedNsURI_2_0= RULE_STRING )
            {
            // InternalConceptLanguage.g:1534:4: (lv_importedNsURI_2_0= RULE_STRING )
            // InternalConceptLanguage.g:1535:5: lv_importedNsURI_2_0= RULE_STRING
            {
            lv_importedNsURI_2_0=(Token)match(input,RULE_STRING,FOLLOW_29); 

            					newLeafNode(lv_importedNsURI_2_0, grammarAccess.getEcoreImportAccess().getImportedNsURISTRINGTerminalRuleCall_2_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getEcoreImportRule());
            					}
            					setWithLastConsumed(
            						current,
            						"importedNsURI",
            						lv_importedNsURI_2_0,
            						"org.eclipse.xtext.common.Terminals.STRING");
            				

            }


            }

            // InternalConceptLanguage.g:1551:3: (otherlv_3= 'genModel' ( (lv_importedGenModel_4_0= RULE_STRING ) ) )?
            int alt25=2;
            int LA25_0 = input.LA(1);

            if ( (LA25_0==41) ) {
                alt25=1;
            }
            switch (alt25) {
                case 1 :
                    // InternalConceptLanguage.g:1552:4: otherlv_3= 'genModel' ( (lv_importedGenModel_4_0= RULE_STRING ) )
                    {
                    otherlv_3=(Token)match(input,41,FOLLOW_5); 

                    				newLeafNode(otherlv_3, grammarAccess.getEcoreImportAccess().getGenModelKeyword_3_0());
                    			
                    // InternalConceptLanguage.g:1556:4: ( (lv_importedGenModel_4_0= RULE_STRING ) )
                    // InternalConceptLanguage.g:1557:5: (lv_importedGenModel_4_0= RULE_STRING )
                    {
                    // InternalConceptLanguage.g:1557:5: (lv_importedGenModel_4_0= RULE_STRING )
                    // InternalConceptLanguage.g:1558:6: lv_importedGenModel_4_0= RULE_STRING
                    {
                    lv_importedGenModel_4_0=(Token)match(input,RULE_STRING,FOLLOW_13); 

                    						newLeafNode(lv_importedGenModel_4_0, grammarAccess.getEcoreImportAccess().getImportedGenModelSTRINGTerminalRuleCall_3_1_0());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getEcoreImportRule());
                    						}
                    						setWithLastConsumed(
                    							current,
                    							"importedGenModel",
                    							lv_importedGenModel_4_0,
                    							"org.eclipse.xtext.common.Terminals.STRING");
                    					

                    }


                    }


                    }
                    break;

            }

            otherlv_5=(Token)match(input,22,FOLLOW_2); 

            			newLeafNode(otherlv_5, grammarAccess.getEcoreImportAccess().getSemicolonKeyword_4());
            		

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
    // $ANTLR end "ruleEcoreImport"


    // $ANTLR start "entryRuleAProperty"
    // InternalConceptLanguage.g:1583:1: entryRuleAProperty returns [EObject current=null] : iv_ruleAProperty= ruleAProperty EOF ;
    public final EObject entryRuleAProperty() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleAProperty = null;


        try {
            // InternalConceptLanguage.g:1583:50: (iv_ruleAProperty= ruleAProperty EOF )
            // InternalConceptLanguage.g:1584:2: iv_ruleAProperty= ruleAProperty EOF
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
    // InternalConceptLanguage.g:1590:1: ruleAProperty returns [EObject current=null] : (this_ComposedProperty_0= ruleComposedProperty | this_IntProperty_1= ruleIntProperty | this_FloatProperty_2= ruleFloatProperty | this_StringProperty_3= ruleStringProperty | this_BooleanProperty_4= ruleBooleanProperty | this_EnumProperty_5= ruleEnumProperty | this_ReferenceProperty_6= ruleReferenceProperty | this_EReferenceProperty_7= ruleEReferenceProperty | this_ResourceProperty_8= ruleResourceProperty ) ;
    public final EObject ruleAProperty() throws RecognitionException {
        EObject current = null;

        EObject this_ComposedProperty_0 = null;

        EObject this_IntProperty_1 = null;

        EObject this_FloatProperty_2 = null;

        EObject this_StringProperty_3 = null;

        EObject this_BooleanProperty_4 = null;

        EObject this_EnumProperty_5 = null;

        EObject this_ReferenceProperty_6 = null;

        EObject this_EReferenceProperty_7 = null;

        EObject this_ResourceProperty_8 = null;



        	enterRule();

        try {
            // InternalConceptLanguage.g:1596:2: ( (this_ComposedProperty_0= ruleComposedProperty | this_IntProperty_1= ruleIntProperty | this_FloatProperty_2= ruleFloatProperty | this_StringProperty_3= ruleStringProperty | this_BooleanProperty_4= ruleBooleanProperty | this_EnumProperty_5= ruleEnumProperty | this_ReferenceProperty_6= ruleReferenceProperty | this_EReferenceProperty_7= ruleEReferenceProperty | this_ResourceProperty_8= ruleResourceProperty ) )
            // InternalConceptLanguage.g:1597:2: (this_ComposedProperty_0= ruleComposedProperty | this_IntProperty_1= ruleIntProperty | this_FloatProperty_2= ruleFloatProperty | this_StringProperty_3= ruleStringProperty | this_BooleanProperty_4= ruleBooleanProperty | this_EnumProperty_5= ruleEnumProperty | this_ReferenceProperty_6= ruleReferenceProperty | this_EReferenceProperty_7= ruleEReferenceProperty | this_ResourceProperty_8= ruleResourceProperty )
            {
            // InternalConceptLanguage.g:1597:2: (this_ComposedProperty_0= ruleComposedProperty | this_IntProperty_1= ruleIntProperty | this_FloatProperty_2= ruleFloatProperty | this_StringProperty_3= ruleStringProperty | this_BooleanProperty_4= ruleBooleanProperty | this_EnumProperty_5= ruleEnumProperty | this_ReferenceProperty_6= ruleReferenceProperty | this_EReferenceProperty_7= ruleEReferenceProperty | this_ResourceProperty_8= ruleResourceProperty )
            int alt26=9;
            switch ( input.LA(1) ) {
            case 34:
                {
                alt26=1;
                }
                break;
            case 46:
                {
                alt26=2;
                }
                break;
            case 48:
                {
                alt26=3;
                }
                break;
            case 49:
                {
                alt26=4;
                }
                break;
            case 50:
                {
                alt26=5;
                }
                break;
            case 51:
                {
                alt26=6;
                }
                break;
            case 54:
                {
                alt26=7;
                }
                break;
            case 55:
                {
                alt26=8;
                }
                break;
            case 56:
                {
                alt26=9;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 26, 0, input);

                throw nvae;
            }

            switch (alt26) {
                case 1 :
                    // InternalConceptLanguage.g:1598:3: this_ComposedProperty_0= ruleComposedProperty
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
                    // InternalConceptLanguage.g:1607:3: this_IntProperty_1= ruleIntProperty
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
                    // InternalConceptLanguage.g:1616:3: this_FloatProperty_2= ruleFloatProperty
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
                    // InternalConceptLanguage.g:1625:3: this_StringProperty_3= ruleStringProperty
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
                    // InternalConceptLanguage.g:1634:3: this_BooleanProperty_4= ruleBooleanProperty
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
                    // InternalConceptLanguage.g:1643:3: this_EnumProperty_5= ruleEnumProperty
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
                    // InternalConceptLanguage.g:1652:3: this_ReferenceProperty_6= ruleReferenceProperty
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
                    // InternalConceptLanguage.g:1661:3: this_EReferenceProperty_7= ruleEReferenceProperty
                    {

                    			newCompositeNode(grammarAccess.getAPropertyAccess().getEReferencePropertyParserRuleCall_7());
                    		
                    pushFollow(FOLLOW_2);
                    this_EReferenceProperty_7=ruleEReferenceProperty();

                    state._fsp--;


                    			current = this_EReferenceProperty_7;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 9 :
                    // InternalConceptLanguage.g:1670:3: this_ResourceProperty_8= ruleResourceProperty
                    {

                    			newCompositeNode(grammarAccess.getAPropertyAccess().getResourcePropertyParserRuleCall_8());
                    		
                    pushFollow(FOLLOW_2);
                    this_ResourceProperty_8=ruleResourceProperty();

                    state._fsp--;


                    			current = this_ResourceProperty_8;
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
    // InternalConceptLanguage.g:1682:1: entryRuleArrayModifier returns [EObject current=null] : iv_ruleArrayModifier= ruleArrayModifier EOF ;
    public final EObject entryRuleArrayModifier() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleArrayModifier = null;


        try {
            // InternalConceptLanguage.g:1682:54: (iv_ruleArrayModifier= ruleArrayModifier EOF )
            // InternalConceptLanguage.g:1683:2: iv_ruleArrayModifier= ruleArrayModifier EOF
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
    // InternalConceptLanguage.g:1689:1: ruleArrayModifier returns [EObject current=null] : (this_DynmaicArrayModifier_0= ruleDynmaicArrayModifier | this_StaticArrayModifier_1= ruleStaticArrayModifier ) ;
    public final EObject ruleArrayModifier() throws RecognitionException {
        EObject current = null;

        EObject this_DynmaicArrayModifier_0 = null;

        EObject this_StaticArrayModifier_1 = null;



        	enterRule();

        try {
            // InternalConceptLanguage.g:1695:2: ( (this_DynmaicArrayModifier_0= ruleDynmaicArrayModifier | this_StaticArrayModifier_1= ruleStaticArrayModifier ) )
            // InternalConceptLanguage.g:1696:2: (this_DynmaicArrayModifier_0= ruleDynmaicArrayModifier | this_StaticArrayModifier_1= ruleStaticArrayModifier )
            {
            // InternalConceptLanguage.g:1696:2: (this_DynmaicArrayModifier_0= ruleDynmaicArrayModifier | this_StaticArrayModifier_1= ruleStaticArrayModifier )
            int alt27=2;
            int LA27_0 = input.LA(1);

            if ( (LA27_0==25) ) {
                int LA27_1 = input.LA(2);

                if ( (LA27_1==RULE_INT) ) {
                    alt27=2;
                }
                else if ( (LA27_1==27) ) {
                    alt27=1;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 27, 1, input);

                    throw nvae;
                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 27, 0, input);

                throw nvae;
            }
            switch (alt27) {
                case 1 :
                    // InternalConceptLanguage.g:1697:3: this_DynmaicArrayModifier_0= ruleDynmaicArrayModifier
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
                    // InternalConceptLanguage.g:1706:3: this_StaticArrayModifier_1= ruleStaticArrayModifier
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
    // InternalConceptLanguage.g:1718:1: entryRuleDynmaicArrayModifier returns [EObject current=null] : iv_ruleDynmaicArrayModifier= ruleDynmaicArrayModifier EOF ;
    public final EObject entryRuleDynmaicArrayModifier() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleDynmaicArrayModifier = null;


        try {
            // InternalConceptLanguage.g:1718:61: (iv_ruleDynmaicArrayModifier= ruleDynmaicArrayModifier EOF )
            // InternalConceptLanguage.g:1719:2: iv_ruleDynmaicArrayModifier= ruleDynmaicArrayModifier EOF
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
    // InternalConceptLanguage.g:1725:1: ruleDynmaicArrayModifier returns [EObject current=null] : ( () otherlv_1= '[' otherlv_2= ']' ) ;
    public final EObject ruleDynmaicArrayModifier() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_2=null;


        	enterRule();

        try {
            // InternalConceptLanguage.g:1731:2: ( ( () otherlv_1= '[' otherlv_2= ']' ) )
            // InternalConceptLanguage.g:1732:2: ( () otherlv_1= '[' otherlv_2= ']' )
            {
            // InternalConceptLanguage.g:1732:2: ( () otherlv_1= '[' otherlv_2= ']' )
            // InternalConceptLanguage.g:1733:3: () otherlv_1= '[' otherlv_2= ']'
            {
            // InternalConceptLanguage.g:1733:3: ()
            // InternalConceptLanguage.g:1734:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getDynmaicArrayModifierAccess().getDynamicArrayModifierAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,25,FOLLOW_30); 

            			newLeafNode(otherlv_1, grammarAccess.getDynmaicArrayModifierAccess().getLeftSquareBracketKeyword_1());
            		
            otherlv_2=(Token)match(input,27,FOLLOW_2); 

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
    // InternalConceptLanguage.g:1752:1: entryRuleStaticArrayModifier returns [EObject current=null] : iv_ruleStaticArrayModifier= ruleStaticArrayModifier EOF ;
    public final EObject entryRuleStaticArrayModifier() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleStaticArrayModifier = null;


        try {
            // InternalConceptLanguage.g:1752:60: (iv_ruleStaticArrayModifier= ruleStaticArrayModifier EOF )
            // InternalConceptLanguage.g:1753:2: iv_ruleStaticArrayModifier= ruleStaticArrayModifier EOF
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
    // InternalConceptLanguage.g:1759:1: ruleStaticArrayModifier returns [EObject current=null] : (otherlv_0= '[' ( (lv_arraySize_1_0= RULE_INT ) ) otherlv_2= ']' ) ;
    public final EObject ruleStaticArrayModifier() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token lv_arraySize_1_0=null;
        Token otherlv_2=null;


        	enterRule();

        try {
            // InternalConceptLanguage.g:1765:2: ( (otherlv_0= '[' ( (lv_arraySize_1_0= RULE_INT ) ) otherlv_2= ']' ) )
            // InternalConceptLanguage.g:1766:2: (otherlv_0= '[' ( (lv_arraySize_1_0= RULE_INT ) ) otherlv_2= ']' )
            {
            // InternalConceptLanguage.g:1766:2: (otherlv_0= '[' ( (lv_arraySize_1_0= RULE_INT ) ) otherlv_2= ']' )
            // InternalConceptLanguage.g:1767:3: otherlv_0= '[' ( (lv_arraySize_1_0= RULE_INT ) ) otherlv_2= ']'
            {
            otherlv_0=(Token)match(input,25,FOLLOW_6); 

            			newLeafNode(otherlv_0, grammarAccess.getStaticArrayModifierAccess().getLeftSquareBracketKeyword_0());
            		
            // InternalConceptLanguage.g:1771:3: ( (lv_arraySize_1_0= RULE_INT ) )
            // InternalConceptLanguage.g:1772:4: (lv_arraySize_1_0= RULE_INT )
            {
            // InternalConceptLanguage.g:1772:4: (lv_arraySize_1_0= RULE_INT )
            // InternalConceptLanguage.g:1773:5: lv_arraySize_1_0= RULE_INT
            {
            lv_arraySize_1_0=(Token)match(input,RULE_INT,FOLLOW_30); 

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

            otherlv_2=(Token)match(input,27,FOLLOW_2); 

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


    // $ANTLR start "entryRuleVerificationTypeSpecification"
    // InternalConceptLanguage.g:1797:1: entryRuleVerificationTypeSpecification returns [EObject current=null] : iv_ruleVerificationTypeSpecification= ruleVerificationTypeSpecification EOF ;
    public final EObject entryRuleVerificationTypeSpecification() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleVerificationTypeSpecification = null;


        try {
            // InternalConceptLanguage.g:1797:70: (iv_ruleVerificationTypeSpecification= ruleVerificationTypeSpecification EOF )
            // InternalConceptLanguage.g:1798:2: iv_ruleVerificationTypeSpecification= ruleVerificationTypeSpecification EOF
            {
             newCompositeNode(grammarAccess.getVerificationTypeSpecificationRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleVerificationTypeSpecification=ruleVerificationTypeSpecification();

            state._fsp--;

             current =iv_ruleVerificationTypeSpecification; 
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
    // $ANTLR end "entryRuleVerificationTypeSpecification"


    // $ANTLR start "ruleVerificationTypeSpecification"
    // InternalConceptLanguage.g:1804:1: ruleVerificationTypeSpecification returns [EObject current=null] : ( ( ruleQualifiedName ) ) ;
    public final EObject ruleVerificationTypeSpecification() throws RecognitionException {
        EObject current = null;


        	enterRule();

        try {
            // InternalConceptLanguage.g:1810:2: ( ( ( ruleQualifiedName ) ) )
            // InternalConceptLanguage.g:1811:2: ( ( ruleQualifiedName ) )
            {
            // InternalConceptLanguage.g:1811:2: ( ( ruleQualifiedName ) )
            // InternalConceptLanguage.g:1812:3: ( ruleQualifiedName )
            {
            // InternalConceptLanguage.g:1812:3: ( ruleQualifiedName )
            // InternalConceptLanguage.g:1813:4: ruleQualifiedName
            {

            				if (current==null) {
            					current = createModelElement(grammarAccess.getVerificationTypeSpecificationRule());
            				}
            			

            				newCompositeNode(grammarAccess.getVerificationTypeSpecificationAccess().getVerificationTypeATypeDefinitionCrossReference_0());
            			
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
    // $ANTLR end "ruleVerificationTypeSpecification"


    // $ANTLR start "entryRuleComposedProperty"
    // InternalConceptLanguage.g:1830:1: entryRuleComposedProperty returns [EObject current=null] : iv_ruleComposedProperty= ruleComposedProperty EOF ;
    public final EObject entryRuleComposedProperty() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleComposedProperty = null;


        try {
            // InternalConceptLanguage.g:1830:57: (iv_ruleComposedProperty= ruleComposedProperty EOF )
            // InternalConceptLanguage.g:1831:2: iv_ruleComposedProperty= ruleComposedProperty EOF
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
    // InternalConceptLanguage.g:1837:1: ruleComposedProperty returns [EObject current=null] : ( () otherlv_1= 'Type' ( (lv_name_2_0= RULE_ID ) ) ( (lv_arrayModifier_3_0= ruleArrayModifier ) )? otherlv_4= 'of' otherlv_5= 'Category' ( ( ruleQualifiedName ) ) ( ( ( ( ({...}? => ( ({...}? => (otherlv_8= 'description' ( (lv_description_9_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_10= 'quantityKind' ( (lv_quantityKindName_11_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_12= 'verification' ( (lv_verification_13_0= ruleVerificationTypeSpecification ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_14= 'unit' ( (lv_unitName_15_0= ruleEString ) ) ) ) ) ) )* ) ) ) otherlv_16= ';' ) ;
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
        Token otherlv_16=null;
        EObject lv_arrayModifier_3_0 = null;

        AntlrDatatypeRuleToken lv_description_9_0 = null;

        AntlrDatatypeRuleToken lv_quantityKindName_11_0 = null;

        EObject lv_verification_13_0 = null;

        AntlrDatatypeRuleToken lv_unitName_15_0 = null;



        	enterRule();

        try {
            // InternalConceptLanguage.g:1843:2: ( ( () otherlv_1= 'Type' ( (lv_name_2_0= RULE_ID ) ) ( (lv_arrayModifier_3_0= ruleArrayModifier ) )? otherlv_4= 'of' otherlv_5= 'Category' ( ( ruleQualifiedName ) ) ( ( ( ( ({...}? => ( ({...}? => (otherlv_8= 'description' ( (lv_description_9_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_10= 'quantityKind' ( (lv_quantityKindName_11_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_12= 'verification' ( (lv_verification_13_0= ruleVerificationTypeSpecification ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_14= 'unit' ( (lv_unitName_15_0= ruleEString ) ) ) ) ) ) )* ) ) ) otherlv_16= ';' ) )
            // InternalConceptLanguage.g:1844:2: ( () otherlv_1= 'Type' ( (lv_name_2_0= RULE_ID ) ) ( (lv_arrayModifier_3_0= ruleArrayModifier ) )? otherlv_4= 'of' otherlv_5= 'Category' ( ( ruleQualifiedName ) ) ( ( ( ( ({...}? => ( ({...}? => (otherlv_8= 'description' ( (lv_description_9_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_10= 'quantityKind' ( (lv_quantityKindName_11_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_12= 'verification' ( (lv_verification_13_0= ruleVerificationTypeSpecification ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_14= 'unit' ( (lv_unitName_15_0= ruleEString ) ) ) ) ) ) )* ) ) ) otherlv_16= ';' )
            {
            // InternalConceptLanguage.g:1844:2: ( () otherlv_1= 'Type' ( (lv_name_2_0= RULE_ID ) ) ( (lv_arrayModifier_3_0= ruleArrayModifier ) )? otherlv_4= 'of' otherlv_5= 'Category' ( ( ruleQualifiedName ) ) ( ( ( ( ({...}? => ( ({...}? => (otherlv_8= 'description' ( (lv_description_9_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_10= 'quantityKind' ( (lv_quantityKindName_11_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_12= 'verification' ( (lv_verification_13_0= ruleVerificationTypeSpecification ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_14= 'unit' ( (lv_unitName_15_0= ruleEString ) ) ) ) ) ) )* ) ) ) otherlv_16= ';' )
            // InternalConceptLanguage.g:1845:3: () otherlv_1= 'Type' ( (lv_name_2_0= RULE_ID ) ) ( (lv_arrayModifier_3_0= ruleArrayModifier ) )? otherlv_4= 'of' otherlv_5= 'Category' ( ( ruleQualifiedName ) ) ( ( ( ( ({...}? => ( ({...}? => (otherlv_8= 'description' ( (lv_description_9_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_10= 'quantityKind' ( (lv_quantityKindName_11_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_12= 'verification' ( (lv_verification_13_0= ruleVerificationTypeSpecification ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_14= 'unit' ( (lv_unitName_15_0= ruleEString ) ) ) ) ) ) )* ) ) ) otherlv_16= ';'
            {
            // InternalConceptLanguage.g:1845:3: ()
            // InternalConceptLanguage.g:1846:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getComposedPropertyAccess().getComposedPropertyAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,34,FOLLOW_3); 

            			newLeafNode(otherlv_1, grammarAccess.getComposedPropertyAccess().getTypeKeyword_1());
            		
            // InternalConceptLanguage.g:1856:3: ( (lv_name_2_0= RULE_ID ) )
            // InternalConceptLanguage.g:1857:4: (lv_name_2_0= RULE_ID )
            {
            // InternalConceptLanguage.g:1857:4: (lv_name_2_0= RULE_ID )
            // InternalConceptLanguage.g:1858:5: lv_name_2_0= RULE_ID
            {
            lv_name_2_0=(Token)match(input,RULE_ID,FOLLOW_31); 

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

            // InternalConceptLanguage.g:1874:3: ( (lv_arrayModifier_3_0= ruleArrayModifier ) )?
            int alt28=2;
            int LA28_0 = input.LA(1);

            if ( (LA28_0==25) ) {
                alt28=1;
            }
            switch (alt28) {
                case 1 :
                    // InternalConceptLanguage.g:1875:4: (lv_arrayModifier_3_0= ruleArrayModifier )
                    {
                    // InternalConceptLanguage.g:1875:4: (lv_arrayModifier_3_0= ruleArrayModifier )
                    // InternalConceptLanguage.g:1876:5: lv_arrayModifier_3_0= ruleArrayModifier
                    {

                    					newCompositeNode(grammarAccess.getComposedPropertyAccess().getArrayModifierArrayModifierParserRuleCall_3_0());
                    				
                    pushFollow(FOLLOW_32);
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

            otherlv_4=(Token)match(input,42,FOLLOW_33); 

            			newLeafNode(otherlv_4, grammarAccess.getComposedPropertyAccess().getOfKeyword_4());
            		
            otherlv_5=(Token)match(input,35,FOLLOW_3); 

            			newLeafNode(otherlv_5, grammarAccess.getComposedPropertyAccess().getCategoryKeyword_5());
            		
            // InternalConceptLanguage.g:1901:3: ( ( ruleQualifiedName ) )
            // InternalConceptLanguage.g:1902:4: ( ruleQualifiedName )
            {
            // InternalConceptLanguage.g:1902:4: ( ruleQualifiedName )
            // InternalConceptLanguage.g:1903:5: ruleQualifiedName
            {

            					if (current==null) {
            						current = createModelElement(grammarAccess.getComposedPropertyRule());
            					}
            				

            					newCompositeNode(grammarAccess.getComposedPropertyAccess().getTypeCategoryCrossReference_6_0());
            				
            pushFollow(FOLLOW_34);
            ruleQualifiedName();

            state._fsp--;


            					afterParserOrEnumRuleCall();
            				

            }


            }

            // InternalConceptLanguage.g:1917:3: ( ( ( ( ({...}? => ( ({...}? => (otherlv_8= 'description' ( (lv_description_9_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_10= 'quantityKind' ( (lv_quantityKindName_11_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_12= 'verification' ( (lv_verification_13_0= ruleVerificationTypeSpecification ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_14= 'unit' ( (lv_unitName_15_0= ruleEString ) ) ) ) ) ) )* ) ) )
            // InternalConceptLanguage.g:1918:4: ( ( ( ({...}? => ( ({...}? => (otherlv_8= 'description' ( (lv_description_9_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_10= 'quantityKind' ( (lv_quantityKindName_11_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_12= 'verification' ( (lv_verification_13_0= ruleVerificationTypeSpecification ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_14= 'unit' ( (lv_unitName_15_0= ruleEString ) ) ) ) ) ) )* ) )
            {
            // InternalConceptLanguage.g:1918:4: ( ( ( ({...}? => ( ({...}? => (otherlv_8= 'description' ( (lv_description_9_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_10= 'quantityKind' ( (lv_quantityKindName_11_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_12= 'verification' ( (lv_verification_13_0= ruleVerificationTypeSpecification ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_14= 'unit' ( (lv_unitName_15_0= ruleEString ) ) ) ) ) ) )* ) )
            // InternalConceptLanguage.g:1919:5: ( ( ({...}? => ( ({...}? => (otherlv_8= 'description' ( (lv_description_9_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_10= 'quantityKind' ( (lv_quantityKindName_11_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_12= 'verification' ( (lv_verification_13_0= ruleVerificationTypeSpecification ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_14= 'unit' ( (lv_unitName_15_0= ruleEString ) ) ) ) ) ) )* )
            {
             
            				  getUnorderedGroupHelper().enter(grammarAccess.getComposedPropertyAccess().getUnorderedGroup_7());
            				
            // InternalConceptLanguage.g:1922:5: ( ( ({...}? => ( ({...}? => (otherlv_8= 'description' ( (lv_description_9_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_10= 'quantityKind' ( (lv_quantityKindName_11_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_12= 'verification' ( (lv_verification_13_0= ruleVerificationTypeSpecification ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_14= 'unit' ( (lv_unitName_15_0= ruleEString ) ) ) ) ) ) )* )
            // InternalConceptLanguage.g:1923:6: ( ({...}? => ( ({...}? => (otherlv_8= 'description' ( (lv_description_9_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_10= 'quantityKind' ( (lv_quantityKindName_11_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_12= 'verification' ( (lv_verification_13_0= ruleVerificationTypeSpecification ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_14= 'unit' ( (lv_unitName_15_0= ruleEString ) ) ) ) ) ) )*
            {
            // InternalConceptLanguage.g:1923:6: ( ({...}? => ( ({...}? => (otherlv_8= 'description' ( (lv_description_9_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_10= 'quantityKind' ( (lv_quantityKindName_11_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_12= 'verification' ( (lv_verification_13_0= ruleVerificationTypeSpecification ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_14= 'unit' ( (lv_unitName_15_0= ruleEString ) ) ) ) ) ) )*
            loop29:
            do {
                int alt29=5;
                int LA29_0 = input.LA(1);

                if ( LA29_0 == 15 && getUnorderedGroupHelper().canSelect(grammarAccess.getComposedPropertyAccess().getUnorderedGroup_7(), 0) ) {
                    alt29=1;
                }
                else if ( LA29_0 == 43 && getUnorderedGroupHelper().canSelect(grammarAccess.getComposedPropertyAccess().getUnorderedGroup_7(), 1) ) {
                    alt29=2;
                }
                else if ( LA29_0 == 44 && getUnorderedGroupHelper().canSelect(grammarAccess.getComposedPropertyAccess().getUnorderedGroup_7(), 2) ) {
                    alt29=3;
                }
                else if ( LA29_0 == 45 && getUnorderedGroupHelper().canSelect(grammarAccess.getComposedPropertyAccess().getUnorderedGroup_7(), 3) ) {
                    alt29=4;
                }


                switch (alt29) {
            	case 1 :
            	    // InternalConceptLanguage.g:1924:4: ({...}? => ( ({...}? => (otherlv_8= 'description' ( (lv_description_9_0= ruleEString ) ) ) ) ) )
            	    {
            	    // InternalConceptLanguage.g:1924:4: ({...}? => ( ({...}? => (otherlv_8= 'description' ( (lv_description_9_0= ruleEString ) ) ) ) ) )
            	    // InternalConceptLanguage.g:1925:5: {...}? => ( ({...}? => (otherlv_8= 'description' ( (lv_description_9_0= ruleEString ) ) ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getComposedPropertyAccess().getUnorderedGroup_7(), 0) ) {
            	        throw new FailedPredicateException(input, "ruleComposedProperty", "getUnorderedGroupHelper().canSelect(grammarAccess.getComposedPropertyAccess().getUnorderedGroup_7(), 0)");
            	    }
            	    // InternalConceptLanguage.g:1925:113: ( ({...}? => (otherlv_8= 'description' ( (lv_description_9_0= ruleEString ) ) ) ) )
            	    // InternalConceptLanguage.g:1926:6: ({...}? => (otherlv_8= 'description' ( (lv_description_9_0= ruleEString ) ) ) )
            	    {

            	    						getUnorderedGroupHelper().select(grammarAccess.getComposedPropertyAccess().getUnorderedGroup_7(), 0);
            	    					
            	    // InternalConceptLanguage.g:1929:9: ({...}? => (otherlv_8= 'description' ( (lv_description_9_0= ruleEString ) ) ) )
            	    // InternalConceptLanguage.g:1929:10: {...}? => (otherlv_8= 'description' ( (lv_description_9_0= ruleEString ) ) )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleComposedProperty", "true");
            	    }
            	    // InternalConceptLanguage.g:1929:19: (otherlv_8= 'description' ( (lv_description_9_0= ruleEString ) ) )
            	    // InternalConceptLanguage.g:1929:20: otherlv_8= 'description' ( (lv_description_9_0= ruleEString ) )
            	    {
            	    otherlv_8=(Token)match(input,15,FOLLOW_5); 

            	    									newLeafNode(otherlv_8, grammarAccess.getComposedPropertyAccess().getDescriptionKeyword_7_0_0());
            	    								
            	    // InternalConceptLanguage.g:1933:9: ( (lv_description_9_0= ruleEString ) )
            	    // InternalConceptLanguage.g:1934:10: (lv_description_9_0= ruleEString )
            	    {
            	    // InternalConceptLanguage.g:1934:10: (lv_description_9_0= ruleEString )
            	    // InternalConceptLanguage.g:1935:11: lv_description_9_0= ruleEString
            	    {

            	    											newCompositeNode(grammarAccess.getComposedPropertyAccess().getDescriptionEStringParserRuleCall_7_0_1_0());
            	    										
            	    pushFollow(FOLLOW_34);
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
            	    // InternalConceptLanguage.g:1958:4: ({...}? => ( ({...}? => (otherlv_10= 'quantityKind' ( (lv_quantityKindName_11_0= ruleEString ) ) ) ) ) )
            	    {
            	    // InternalConceptLanguage.g:1958:4: ({...}? => ( ({...}? => (otherlv_10= 'quantityKind' ( (lv_quantityKindName_11_0= ruleEString ) ) ) ) ) )
            	    // InternalConceptLanguage.g:1959:5: {...}? => ( ({...}? => (otherlv_10= 'quantityKind' ( (lv_quantityKindName_11_0= ruleEString ) ) ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getComposedPropertyAccess().getUnorderedGroup_7(), 1) ) {
            	        throw new FailedPredicateException(input, "ruleComposedProperty", "getUnorderedGroupHelper().canSelect(grammarAccess.getComposedPropertyAccess().getUnorderedGroup_7(), 1)");
            	    }
            	    // InternalConceptLanguage.g:1959:113: ( ({...}? => (otherlv_10= 'quantityKind' ( (lv_quantityKindName_11_0= ruleEString ) ) ) ) )
            	    // InternalConceptLanguage.g:1960:6: ({...}? => (otherlv_10= 'quantityKind' ( (lv_quantityKindName_11_0= ruleEString ) ) ) )
            	    {

            	    						getUnorderedGroupHelper().select(grammarAccess.getComposedPropertyAccess().getUnorderedGroup_7(), 1);
            	    					
            	    // InternalConceptLanguage.g:1963:9: ({...}? => (otherlv_10= 'quantityKind' ( (lv_quantityKindName_11_0= ruleEString ) ) ) )
            	    // InternalConceptLanguage.g:1963:10: {...}? => (otherlv_10= 'quantityKind' ( (lv_quantityKindName_11_0= ruleEString ) ) )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleComposedProperty", "true");
            	    }
            	    // InternalConceptLanguage.g:1963:19: (otherlv_10= 'quantityKind' ( (lv_quantityKindName_11_0= ruleEString ) ) )
            	    // InternalConceptLanguage.g:1963:20: otherlv_10= 'quantityKind' ( (lv_quantityKindName_11_0= ruleEString ) )
            	    {
            	    otherlv_10=(Token)match(input,43,FOLLOW_5); 

            	    									newLeafNode(otherlv_10, grammarAccess.getComposedPropertyAccess().getQuantityKindKeyword_7_1_0());
            	    								
            	    // InternalConceptLanguage.g:1967:9: ( (lv_quantityKindName_11_0= ruleEString ) )
            	    // InternalConceptLanguage.g:1968:10: (lv_quantityKindName_11_0= ruleEString )
            	    {
            	    // InternalConceptLanguage.g:1968:10: (lv_quantityKindName_11_0= ruleEString )
            	    // InternalConceptLanguage.g:1969:11: lv_quantityKindName_11_0= ruleEString
            	    {

            	    											newCompositeNode(grammarAccess.getComposedPropertyAccess().getQuantityKindNameEStringParserRuleCall_7_1_1_0());
            	    										
            	    pushFollow(FOLLOW_34);
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
            	    // InternalConceptLanguage.g:1992:4: ({...}? => ( ({...}? => (otherlv_12= 'verification' ( (lv_verification_13_0= ruleVerificationTypeSpecification ) ) ) ) ) )
            	    {
            	    // InternalConceptLanguage.g:1992:4: ({...}? => ( ({...}? => (otherlv_12= 'verification' ( (lv_verification_13_0= ruleVerificationTypeSpecification ) ) ) ) ) )
            	    // InternalConceptLanguage.g:1993:5: {...}? => ( ({...}? => (otherlv_12= 'verification' ( (lv_verification_13_0= ruleVerificationTypeSpecification ) ) ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getComposedPropertyAccess().getUnorderedGroup_7(), 2) ) {
            	        throw new FailedPredicateException(input, "ruleComposedProperty", "getUnorderedGroupHelper().canSelect(grammarAccess.getComposedPropertyAccess().getUnorderedGroup_7(), 2)");
            	    }
            	    // InternalConceptLanguage.g:1993:113: ( ({...}? => (otherlv_12= 'verification' ( (lv_verification_13_0= ruleVerificationTypeSpecification ) ) ) ) )
            	    // InternalConceptLanguage.g:1994:6: ({...}? => (otherlv_12= 'verification' ( (lv_verification_13_0= ruleVerificationTypeSpecification ) ) ) )
            	    {

            	    						getUnorderedGroupHelper().select(grammarAccess.getComposedPropertyAccess().getUnorderedGroup_7(), 2);
            	    					
            	    // InternalConceptLanguage.g:1997:9: ({...}? => (otherlv_12= 'verification' ( (lv_verification_13_0= ruleVerificationTypeSpecification ) ) ) )
            	    // InternalConceptLanguage.g:1997:10: {...}? => (otherlv_12= 'verification' ( (lv_verification_13_0= ruleVerificationTypeSpecification ) ) )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleComposedProperty", "true");
            	    }
            	    // InternalConceptLanguage.g:1997:19: (otherlv_12= 'verification' ( (lv_verification_13_0= ruleVerificationTypeSpecification ) ) )
            	    // InternalConceptLanguage.g:1997:20: otherlv_12= 'verification' ( (lv_verification_13_0= ruleVerificationTypeSpecification ) )
            	    {
            	    otherlv_12=(Token)match(input,44,FOLLOW_3); 

            	    									newLeafNode(otherlv_12, grammarAccess.getComposedPropertyAccess().getVerificationKeyword_7_2_0());
            	    								
            	    // InternalConceptLanguage.g:2001:9: ( (lv_verification_13_0= ruleVerificationTypeSpecification ) )
            	    // InternalConceptLanguage.g:2002:10: (lv_verification_13_0= ruleVerificationTypeSpecification )
            	    {
            	    // InternalConceptLanguage.g:2002:10: (lv_verification_13_0= ruleVerificationTypeSpecification )
            	    // InternalConceptLanguage.g:2003:11: lv_verification_13_0= ruleVerificationTypeSpecification
            	    {

            	    											newCompositeNode(grammarAccess.getComposedPropertyAccess().getVerificationVerificationTypeSpecificationParserRuleCall_7_2_1_0());
            	    										
            	    pushFollow(FOLLOW_34);
            	    lv_verification_13_0=ruleVerificationTypeSpecification();

            	    state._fsp--;


            	    											if (current==null) {
            	    												current = createModelElementForParent(grammarAccess.getComposedPropertyRule());
            	    											}
            	    											set(
            	    												current,
            	    												"verification",
            	    												lv_verification_13_0,
            	    												"de.dlr.sc.virsat.model.concept.ConceptLanguage.VerificationTypeSpecification");
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
            	case 4 :
            	    // InternalConceptLanguage.g:2026:4: ({...}? => ( ({...}? => (otherlv_14= 'unit' ( (lv_unitName_15_0= ruleEString ) ) ) ) ) )
            	    {
            	    // InternalConceptLanguage.g:2026:4: ({...}? => ( ({...}? => (otherlv_14= 'unit' ( (lv_unitName_15_0= ruleEString ) ) ) ) ) )
            	    // InternalConceptLanguage.g:2027:5: {...}? => ( ({...}? => (otherlv_14= 'unit' ( (lv_unitName_15_0= ruleEString ) ) ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getComposedPropertyAccess().getUnorderedGroup_7(), 3) ) {
            	        throw new FailedPredicateException(input, "ruleComposedProperty", "getUnorderedGroupHelper().canSelect(grammarAccess.getComposedPropertyAccess().getUnorderedGroup_7(), 3)");
            	    }
            	    // InternalConceptLanguage.g:2027:113: ( ({...}? => (otherlv_14= 'unit' ( (lv_unitName_15_0= ruleEString ) ) ) ) )
            	    // InternalConceptLanguage.g:2028:6: ({...}? => (otherlv_14= 'unit' ( (lv_unitName_15_0= ruleEString ) ) ) )
            	    {

            	    						getUnorderedGroupHelper().select(grammarAccess.getComposedPropertyAccess().getUnorderedGroup_7(), 3);
            	    					
            	    // InternalConceptLanguage.g:2031:9: ({...}? => (otherlv_14= 'unit' ( (lv_unitName_15_0= ruleEString ) ) ) )
            	    // InternalConceptLanguage.g:2031:10: {...}? => (otherlv_14= 'unit' ( (lv_unitName_15_0= ruleEString ) ) )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleComposedProperty", "true");
            	    }
            	    // InternalConceptLanguage.g:2031:19: (otherlv_14= 'unit' ( (lv_unitName_15_0= ruleEString ) ) )
            	    // InternalConceptLanguage.g:2031:20: otherlv_14= 'unit' ( (lv_unitName_15_0= ruleEString ) )
            	    {
            	    otherlv_14=(Token)match(input,45,FOLLOW_5); 

            	    									newLeafNode(otherlv_14, grammarAccess.getComposedPropertyAccess().getUnitKeyword_7_3_0());
            	    								
            	    // InternalConceptLanguage.g:2035:9: ( (lv_unitName_15_0= ruleEString ) )
            	    // InternalConceptLanguage.g:2036:10: (lv_unitName_15_0= ruleEString )
            	    {
            	    // InternalConceptLanguage.g:2036:10: (lv_unitName_15_0= ruleEString )
            	    // InternalConceptLanguage.g:2037:11: lv_unitName_15_0= ruleEString
            	    {

            	    											newCompositeNode(grammarAccess.getComposedPropertyAccess().getUnitNameEStringParserRuleCall_7_3_1_0());
            	    										
            	    pushFollow(FOLLOW_34);
            	    lv_unitName_15_0=ruleEString();

            	    state._fsp--;


            	    											if (current==null) {
            	    												current = createModelElementForParent(grammarAccess.getComposedPropertyRule());
            	    											}
            	    											set(
            	    												current,
            	    												"unitName",
            	    												lv_unitName_15_0,
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
            	    break loop29;
                }
            } while (true);


            }


            }

             
            				  getUnorderedGroupHelper().leave(grammarAccess.getComposedPropertyAccess().getUnorderedGroup_7());
            				

            }

            otherlv_16=(Token)match(input,22,FOLLOW_2); 

            			newLeafNode(otherlv_16, grammarAccess.getComposedPropertyAccess().getSemicolonKeyword_8());
            		

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
    // InternalConceptLanguage.g:2075:1: entryRuleIntProperty returns [EObject current=null] : iv_ruleIntProperty= ruleIntProperty EOF ;
    public final EObject entryRuleIntProperty() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleIntProperty = null;


        try {
            // InternalConceptLanguage.g:2075:52: (iv_ruleIntProperty= ruleIntProperty EOF )
            // InternalConceptLanguage.g:2076:2: iv_ruleIntProperty= ruleIntProperty EOF
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
    // InternalConceptLanguage.g:2082:1: ruleIntProperty returns [EObject current=null] : ( () otherlv_1= 'IntProperty' ( (lv_name_2_0= RULE_ID ) ) ( (lv_arrayModifier_3_0= ruleArrayModifier ) )? ( ( ( ( ({...}? => ( ({...}? => (otherlv_5= 'description' ( (lv_description_6_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'default' ( (lv_defaultValue_8_0= ruleIntLiteralString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_9= 'quantityKind' ( (lv_quantityKindName_10_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_11= 'verification' ( (lv_verification_12_0= ruleVerificationTypeSpecification ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_13= 'unit' ( (lv_unitName_14_0= ruleEString ) ) ) ) ) ) )* ) ) ) otherlv_15= ';' ) ;
    public final EObject ruleIntProperty() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token lv_name_2_0=null;
        Token otherlv_5=null;
        Token otherlv_7=null;
        Token otherlv_9=null;
        Token otherlv_11=null;
        Token otherlv_13=null;
        Token otherlv_15=null;
        EObject lv_arrayModifier_3_0 = null;

        AntlrDatatypeRuleToken lv_description_6_0 = null;

        AntlrDatatypeRuleToken lv_defaultValue_8_0 = null;

        AntlrDatatypeRuleToken lv_quantityKindName_10_0 = null;

        EObject lv_verification_12_0 = null;

        AntlrDatatypeRuleToken lv_unitName_14_0 = null;



        	enterRule();

        try {
            // InternalConceptLanguage.g:2088:2: ( ( () otherlv_1= 'IntProperty' ( (lv_name_2_0= RULE_ID ) ) ( (lv_arrayModifier_3_0= ruleArrayModifier ) )? ( ( ( ( ({...}? => ( ({...}? => (otherlv_5= 'description' ( (lv_description_6_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'default' ( (lv_defaultValue_8_0= ruleIntLiteralString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_9= 'quantityKind' ( (lv_quantityKindName_10_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_11= 'verification' ( (lv_verification_12_0= ruleVerificationTypeSpecification ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_13= 'unit' ( (lv_unitName_14_0= ruleEString ) ) ) ) ) ) )* ) ) ) otherlv_15= ';' ) )
            // InternalConceptLanguage.g:2089:2: ( () otherlv_1= 'IntProperty' ( (lv_name_2_0= RULE_ID ) ) ( (lv_arrayModifier_3_0= ruleArrayModifier ) )? ( ( ( ( ({...}? => ( ({...}? => (otherlv_5= 'description' ( (lv_description_6_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'default' ( (lv_defaultValue_8_0= ruleIntLiteralString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_9= 'quantityKind' ( (lv_quantityKindName_10_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_11= 'verification' ( (lv_verification_12_0= ruleVerificationTypeSpecification ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_13= 'unit' ( (lv_unitName_14_0= ruleEString ) ) ) ) ) ) )* ) ) ) otherlv_15= ';' )
            {
            // InternalConceptLanguage.g:2089:2: ( () otherlv_1= 'IntProperty' ( (lv_name_2_0= RULE_ID ) ) ( (lv_arrayModifier_3_0= ruleArrayModifier ) )? ( ( ( ( ({...}? => ( ({...}? => (otherlv_5= 'description' ( (lv_description_6_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'default' ( (lv_defaultValue_8_0= ruleIntLiteralString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_9= 'quantityKind' ( (lv_quantityKindName_10_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_11= 'verification' ( (lv_verification_12_0= ruleVerificationTypeSpecification ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_13= 'unit' ( (lv_unitName_14_0= ruleEString ) ) ) ) ) ) )* ) ) ) otherlv_15= ';' )
            // InternalConceptLanguage.g:2090:3: () otherlv_1= 'IntProperty' ( (lv_name_2_0= RULE_ID ) ) ( (lv_arrayModifier_3_0= ruleArrayModifier ) )? ( ( ( ( ({...}? => ( ({...}? => (otherlv_5= 'description' ( (lv_description_6_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'default' ( (lv_defaultValue_8_0= ruleIntLiteralString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_9= 'quantityKind' ( (lv_quantityKindName_10_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_11= 'verification' ( (lv_verification_12_0= ruleVerificationTypeSpecification ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_13= 'unit' ( (lv_unitName_14_0= ruleEString ) ) ) ) ) ) )* ) ) ) otherlv_15= ';'
            {
            // InternalConceptLanguage.g:2090:3: ()
            // InternalConceptLanguage.g:2091:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getIntPropertyAccess().getIntPropertyAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,46,FOLLOW_3); 

            			newLeafNode(otherlv_1, grammarAccess.getIntPropertyAccess().getIntPropertyKeyword_1());
            		
            // InternalConceptLanguage.g:2101:3: ( (lv_name_2_0= RULE_ID ) )
            // InternalConceptLanguage.g:2102:4: (lv_name_2_0= RULE_ID )
            {
            // InternalConceptLanguage.g:2102:4: (lv_name_2_0= RULE_ID )
            // InternalConceptLanguage.g:2103:5: lv_name_2_0= RULE_ID
            {
            lv_name_2_0=(Token)match(input,RULE_ID,FOLLOW_35); 

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

            // InternalConceptLanguage.g:2119:3: ( (lv_arrayModifier_3_0= ruleArrayModifier ) )?
            int alt30=2;
            int LA30_0 = input.LA(1);

            if ( (LA30_0==25) ) {
                alt30=1;
            }
            switch (alt30) {
                case 1 :
                    // InternalConceptLanguage.g:2120:4: (lv_arrayModifier_3_0= ruleArrayModifier )
                    {
                    // InternalConceptLanguage.g:2120:4: (lv_arrayModifier_3_0= ruleArrayModifier )
                    // InternalConceptLanguage.g:2121:5: lv_arrayModifier_3_0= ruleArrayModifier
                    {

                    					newCompositeNode(grammarAccess.getIntPropertyAccess().getArrayModifierArrayModifierParserRuleCall_3_0());
                    				
                    pushFollow(FOLLOW_36);
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

            // InternalConceptLanguage.g:2138:3: ( ( ( ( ({...}? => ( ({...}? => (otherlv_5= 'description' ( (lv_description_6_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'default' ( (lv_defaultValue_8_0= ruleIntLiteralString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_9= 'quantityKind' ( (lv_quantityKindName_10_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_11= 'verification' ( (lv_verification_12_0= ruleVerificationTypeSpecification ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_13= 'unit' ( (lv_unitName_14_0= ruleEString ) ) ) ) ) ) )* ) ) )
            // InternalConceptLanguage.g:2139:4: ( ( ( ({...}? => ( ({...}? => (otherlv_5= 'description' ( (lv_description_6_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'default' ( (lv_defaultValue_8_0= ruleIntLiteralString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_9= 'quantityKind' ( (lv_quantityKindName_10_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_11= 'verification' ( (lv_verification_12_0= ruleVerificationTypeSpecification ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_13= 'unit' ( (lv_unitName_14_0= ruleEString ) ) ) ) ) ) )* ) )
            {
            // InternalConceptLanguage.g:2139:4: ( ( ( ({...}? => ( ({...}? => (otherlv_5= 'description' ( (lv_description_6_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'default' ( (lv_defaultValue_8_0= ruleIntLiteralString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_9= 'quantityKind' ( (lv_quantityKindName_10_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_11= 'verification' ( (lv_verification_12_0= ruleVerificationTypeSpecification ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_13= 'unit' ( (lv_unitName_14_0= ruleEString ) ) ) ) ) ) )* ) )
            // InternalConceptLanguage.g:2140:5: ( ( ({...}? => ( ({...}? => (otherlv_5= 'description' ( (lv_description_6_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'default' ( (lv_defaultValue_8_0= ruleIntLiteralString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_9= 'quantityKind' ( (lv_quantityKindName_10_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_11= 'verification' ( (lv_verification_12_0= ruleVerificationTypeSpecification ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_13= 'unit' ( (lv_unitName_14_0= ruleEString ) ) ) ) ) ) )* )
            {
             
            				  getUnorderedGroupHelper().enter(grammarAccess.getIntPropertyAccess().getUnorderedGroup_4());
            				
            // InternalConceptLanguage.g:2143:5: ( ( ({...}? => ( ({...}? => (otherlv_5= 'description' ( (lv_description_6_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'default' ( (lv_defaultValue_8_0= ruleIntLiteralString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_9= 'quantityKind' ( (lv_quantityKindName_10_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_11= 'verification' ( (lv_verification_12_0= ruleVerificationTypeSpecification ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_13= 'unit' ( (lv_unitName_14_0= ruleEString ) ) ) ) ) ) )* )
            // InternalConceptLanguage.g:2144:6: ( ({...}? => ( ({...}? => (otherlv_5= 'description' ( (lv_description_6_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'default' ( (lv_defaultValue_8_0= ruleIntLiteralString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_9= 'quantityKind' ( (lv_quantityKindName_10_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_11= 'verification' ( (lv_verification_12_0= ruleVerificationTypeSpecification ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_13= 'unit' ( (lv_unitName_14_0= ruleEString ) ) ) ) ) ) )*
            {
            // InternalConceptLanguage.g:2144:6: ( ({...}? => ( ({...}? => (otherlv_5= 'description' ( (lv_description_6_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'default' ( (lv_defaultValue_8_0= ruleIntLiteralString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_9= 'quantityKind' ( (lv_quantityKindName_10_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_11= 'verification' ( (lv_verification_12_0= ruleVerificationTypeSpecification ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_13= 'unit' ( (lv_unitName_14_0= ruleEString ) ) ) ) ) ) )*
            loop31:
            do {
                int alt31=6;
                int LA31_0 = input.LA(1);

                if ( LA31_0 == 15 && getUnorderedGroupHelper().canSelect(grammarAccess.getIntPropertyAccess().getUnorderedGroup_4(), 0) ) {
                    alt31=1;
                }
                else if ( LA31_0 == 47 && getUnorderedGroupHelper().canSelect(grammarAccess.getIntPropertyAccess().getUnorderedGroup_4(), 1) ) {
                    alt31=2;
                }
                else if ( LA31_0 == 43 && getUnorderedGroupHelper().canSelect(grammarAccess.getIntPropertyAccess().getUnorderedGroup_4(), 2) ) {
                    alt31=3;
                }
                else if ( LA31_0 == 44 && getUnorderedGroupHelper().canSelect(grammarAccess.getIntPropertyAccess().getUnorderedGroup_4(), 3) ) {
                    alt31=4;
                }
                else if ( LA31_0 == 45 && getUnorderedGroupHelper().canSelect(grammarAccess.getIntPropertyAccess().getUnorderedGroup_4(), 4) ) {
                    alt31=5;
                }


                switch (alt31) {
            	case 1 :
            	    // InternalConceptLanguage.g:2145:4: ({...}? => ( ({...}? => (otherlv_5= 'description' ( (lv_description_6_0= ruleEString ) ) ) ) ) )
            	    {
            	    // InternalConceptLanguage.g:2145:4: ({...}? => ( ({...}? => (otherlv_5= 'description' ( (lv_description_6_0= ruleEString ) ) ) ) ) )
            	    // InternalConceptLanguage.g:2146:5: {...}? => ( ({...}? => (otherlv_5= 'description' ( (lv_description_6_0= ruleEString ) ) ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getIntPropertyAccess().getUnorderedGroup_4(), 0) ) {
            	        throw new FailedPredicateException(input, "ruleIntProperty", "getUnorderedGroupHelper().canSelect(grammarAccess.getIntPropertyAccess().getUnorderedGroup_4(), 0)");
            	    }
            	    // InternalConceptLanguage.g:2146:108: ( ({...}? => (otherlv_5= 'description' ( (lv_description_6_0= ruleEString ) ) ) ) )
            	    // InternalConceptLanguage.g:2147:6: ({...}? => (otherlv_5= 'description' ( (lv_description_6_0= ruleEString ) ) ) )
            	    {

            	    						getUnorderedGroupHelper().select(grammarAccess.getIntPropertyAccess().getUnorderedGroup_4(), 0);
            	    					
            	    // InternalConceptLanguage.g:2150:9: ({...}? => (otherlv_5= 'description' ( (lv_description_6_0= ruleEString ) ) ) )
            	    // InternalConceptLanguage.g:2150:10: {...}? => (otherlv_5= 'description' ( (lv_description_6_0= ruleEString ) ) )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleIntProperty", "true");
            	    }
            	    // InternalConceptLanguage.g:2150:19: (otherlv_5= 'description' ( (lv_description_6_0= ruleEString ) ) )
            	    // InternalConceptLanguage.g:2150:20: otherlv_5= 'description' ( (lv_description_6_0= ruleEString ) )
            	    {
            	    otherlv_5=(Token)match(input,15,FOLLOW_5); 

            	    									newLeafNode(otherlv_5, grammarAccess.getIntPropertyAccess().getDescriptionKeyword_4_0_0());
            	    								
            	    // InternalConceptLanguage.g:2154:9: ( (lv_description_6_0= ruleEString ) )
            	    // InternalConceptLanguage.g:2155:10: (lv_description_6_0= ruleEString )
            	    {
            	    // InternalConceptLanguage.g:2155:10: (lv_description_6_0= ruleEString )
            	    // InternalConceptLanguage.g:2156:11: lv_description_6_0= ruleEString
            	    {

            	    											newCompositeNode(grammarAccess.getIntPropertyAccess().getDescriptionEStringParserRuleCall_4_0_1_0());
            	    										
            	    pushFollow(FOLLOW_36);
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
            	    // InternalConceptLanguage.g:2179:4: ({...}? => ( ({...}? => (otherlv_7= 'default' ( (lv_defaultValue_8_0= ruleIntLiteralString ) ) ) ) ) )
            	    {
            	    // InternalConceptLanguage.g:2179:4: ({...}? => ( ({...}? => (otherlv_7= 'default' ( (lv_defaultValue_8_0= ruleIntLiteralString ) ) ) ) ) )
            	    // InternalConceptLanguage.g:2180:5: {...}? => ( ({...}? => (otherlv_7= 'default' ( (lv_defaultValue_8_0= ruleIntLiteralString ) ) ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getIntPropertyAccess().getUnorderedGroup_4(), 1) ) {
            	        throw new FailedPredicateException(input, "ruleIntProperty", "getUnorderedGroupHelper().canSelect(grammarAccess.getIntPropertyAccess().getUnorderedGroup_4(), 1)");
            	    }
            	    // InternalConceptLanguage.g:2180:108: ( ({...}? => (otherlv_7= 'default' ( (lv_defaultValue_8_0= ruleIntLiteralString ) ) ) ) )
            	    // InternalConceptLanguage.g:2181:6: ({...}? => (otherlv_7= 'default' ( (lv_defaultValue_8_0= ruleIntLiteralString ) ) ) )
            	    {

            	    						getUnorderedGroupHelper().select(grammarAccess.getIntPropertyAccess().getUnorderedGroup_4(), 1);
            	    					
            	    // InternalConceptLanguage.g:2184:9: ({...}? => (otherlv_7= 'default' ( (lv_defaultValue_8_0= ruleIntLiteralString ) ) ) )
            	    // InternalConceptLanguage.g:2184:10: {...}? => (otherlv_7= 'default' ( (lv_defaultValue_8_0= ruleIntLiteralString ) ) )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleIntProperty", "true");
            	    }
            	    // InternalConceptLanguage.g:2184:19: (otherlv_7= 'default' ( (lv_defaultValue_8_0= ruleIntLiteralString ) ) )
            	    // InternalConceptLanguage.g:2184:20: otherlv_7= 'default' ( (lv_defaultValue_8_0= ruleIntLiteralString ) )
            	    {
            	    otherlv_7=(Token)match(input,47,FOLLOW_37); 

            	    									newLeafNode(otherlv_7, grammarAccess.getIntPropertyAccess().getDefaultKeyword_4_1_0());
            	    								
            	    // InternalConceptLanguage.g:2188:9: ( (lv_defaultValue_8_0= ruleIntLiteralString ) )
            	    // InternalConceptLanguage.g:2189:10: (lv_defaultValue_8_0= ruleIntLiteralString )
            	    {
            	    // InternalConceptLanguage.g:2189:10: (lv_defaultValue_8_0= ruleIntLiteralString )
            	    // InternalConceptLanguage.g:2190:11: lv_defaultValue_8_0= ruleIntLiteralString
            	    {

            	    											newCompositeNode(grammarAccess.getIntPropertyAccess().getDefaultValueIntLiteralStringParserRuleCall_4_1_1_0());
            	    										
            	    pushFollow(FOLLOW_36);
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
            	    // InternalConceptLanguage.g:2213:4: ({...}? => ( ({...}? => (otherlv_9= 'quantityKind' ( (lv_quantityKindName_10_0= ruleEString ) ) ) ) ) )
            	    {
            	    // InternalConceptLanguage.g:2213:4: ({...}? => ( ({...}? => (otherlv_9= 'quantityKind' ( (lv_quantityKindName_10_0= ruleEString ) ) ) ) ) )
            	    // InternalConceptLanguage.g:2214:5: {...}? => ( ({...}? => (otherlv_9= 'quantityKind' ( (lv_quantityKindName_10_0= ruleEString ) ) ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getIntPropertyAccess().getUnorderedGroup_4(), 2) ) {
            	        throw new FailedPredicateException(input, "ruleIntProperty", "getUnorderedGroupHelper().canSelect(grammarAccess.getIntPropertyAccess().getUnorderedGroup_4(), 2)");
            	    }
            	    // InternalConceptLanguage.g:2214:108: ( ({...}? => (otherlv_9= 'quantityKind' ( (lv_quantityKindName_10_0= ruleEString ) ) ) ) )
            	    // InternalConceptLanguage.g:2215:6: ({...}? => (otherlv_9= 'quantityKind' ( (lv_quantityKindName_10_0= ruleEString ) ) ) )
            	    {

            	    						getUnorderedGroupHelper().select(grammarAccess.getIntPropertyAccess().getUnorderedGroup_4(), 2);
            	    					
            	    // InternalConceptLanguage.g:2218:9: ({...}? => (otherlv_9= 'quantityKind' ( (lv_quantityKindName_10_0= ruleEString ) ) ) )
            	    // InternalConceptLanguage.g:2218:10: {...}? => (otherlv_9= 'quantityKind' ( (lv_quantityKindName_10_0= ruleEString ) ) )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleIntProperty", "true");
            	    }
            	    // InternalConceptLanguage.g:2218:19: (otherlv_9= 'quantityKind' ( (lv_quantityKindName_10_0= ruleEString ) ) )
            	    // InternalConceptLanguage.g:2218:20: otherlv_9= 'quantityKind' ( (lv_quantityKindName_10_0= ruleEString ) )
            	    {
            	    otherlv_9=(Token)match(input,43,FOLLOW_5); 

            	    									newLeafNode(otherlv_9, grammarAccess.getIntPropertyAccess().getQuantityKindKeyword_4_2_0());
            	    								
            	    // InternalConceptLanguage.g:2222:9: ( (lv_quantityKindName_10_0= ruleEString ) )
            	    // InternalConceptLanguage.g:2223:10: (lv_quantityKindName_10_0= ruleEString )
            	    {
            	    // InternalConceptLanguage.g:2223:10: (lv_quantityKindName_10_0= ruleEString )
            	    // InternalConceptLanguage.g:2224:11: lv_quantityKindName_10_0= ruleEString
            	    {

            	    											newCompositeNode(grammarAccess.getIntPropertyAccess().getQuantityKindNameEStringParserRuleCall_4_2_1_0());
            	    										
            	    pushFollow(FOLLOW_36);
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
            	    // InternalConceptLanguage.g:2247:4: ({...}? => ( ({...}? => (otherlv_11= 'verification' ( (lv_verification_12_0= ruleVerificationTypeSpecification ) ) ) ) ) )
            	    {
            	    // InternalConceptLanguage.g:2247:4: ({...}? => ( ({...}? => (otherlv_11= 'verification' ( (lv_verification_12_0= ruleVerificationTypeSpecification ) ) ) ) ) )
            	    // InternalConceptLanguage.g:2248:5: {...}? => ( ({...}? => (otherlv_11= 'verification' ( (lv_verification_12_0= ruleVerificationTypeSpecification ) ) ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getIntPropertyAccess().getUnorderedGroup_4(), 3) ) {
            	        throw new FailedPredicateException(input, "ruleIntProperty", "getUnorderedGroupHelper().canSelect(grammarAccess.getIntPropertyAccess().getUnorderedGroup_4(), 3)");
            	    }
            	    // InternalConceptLanguage.g:2248:108: ( ({...}? => (otherlv_11= 'verification' ( (lv_verification_12_0= ruleVerificationTypeSpecification ) ) ) ) )
            	    // InternalConceptLanguage.g:2249:6: ({...}? => (otherlv_11= 'verification' ( (lv_verification_12_0= ruleVerificationTypeSpecification ) ) ) )
            	    {

            	    						getUnorderedGroupHelper().select(grammarAccess.getIntPropertyAccess().getUnorderedGroup_4(), 3);
            	    					
            	    // InternalConceptLanguage.g:2252:9: ({...}? => (otherlv_11= 'verification' ( (lv_verification_12_0= ruleVerificationTypeSpecification ) ) ) )
            	    // InternalConceptLanguage.g:2252:10: {...}? => (otherlv_11= 'verification' ( (lv_verification_12_0= ruleVerificationTypeSpecification ) ) )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleIntProperty", "true");
            	    }
            	    // InternalConceptLanguage.g:2252:19: (otherlv_11= 'verification' ( (lv_verification_12_0= ruleVerificationTypeSpecification ) ) )
            	    // InternalConceptLanguage.g:2252:20: otherlv_11= 'verification' ( (lv_verification_12_0= ruleVerificationTypeSpecification ) )
            	    {
            	    otherlv_11=(Token)match(input,44,FOLLOW_3); 

            	    									newLeafNode(otherlv_11, grammarAccess.getIntPropertyAccess().getVerificationKeyword_4_3_0());
            	    								
            	    // InternalConceptLanguage.g:2256:9: ( (lv_verification_12_0= ruleVerificationTypeSpecification ) )
            	    // InternalConceptLanguage.g:2257:10: (lv_verification_12_0= ruleVerificationTypeSpecification )
            	    {
            	    // InternalConceptLanguage.g:2257:10: (lv_verification_12_0= ruleVerificationTypeSpecification )
            	    // InternalConceptLanguage.g:2258:11: lv_verification_12_0= ruleVerificationTypeSpecification
            	    {

            	    											newCompositeNode(grammarAccess.getIntPropertyAccess().getVerificationVerificationTypeSpecificationParserRuleCall_4_3_1_0());
            	    										
            	    pushFollow(FOLLOW_36);
            	    lv_verification_12_0=ruleVerificationTypeSpecification();

            	    state._fsp--;


            	    											if (current==null) {
            	    												current = createModelElementForParent(grammarAccess.getIntPropertyRule());
            	    											}
            	    											set(
            	    												current,
            	    												"verification",
            	    												lv_verification_12_0,
            	    												"de.dlr.sc.virsat.model.concept.ConceptLanguage.VerificationTypeSpecification");
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
            	case 5 :
            	    // InternalConceptLanguage.g:2281:4: ({...}? => ( ({...}? => (otherlv_13= 'unit' ( (lv_unitName_14_0= ruleEString ) ) ) ) ) )
            	    {
            	    // InternalConceptLanguage.g:2281:4: ({...}? => ( ({...}? => (otherlv_13= 'unit' ( (lv_unitName_14_0= ruleEString ) ) ) ) ) )
            	    // InternalConceptLanguage.g:2282:5: {...}? => ( ({...}? => (otherlv_13= 'unit' ( (lv_unitName_14_0= ruleEString ) ) ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getIntPropertyAccess().getUnorderedGroup_4(), 4) ) {
            	        throw new FailedPredicateException(input, "ruleIntProperty", "getUnorderedGroupHelper().canSelect(grammarAccess.getIntPropertyAccess().getUnorderedGroup_4(), 4)");
            	    }
            	    // InternalConceptLanguage.g:2282:108: ( ({...}? => (otherlv_13= 'unit' ( (lv_unitName_14_0= ruleEString ) ) ) ) )
            	    // InternalConceptLanguage.g:2283:6: ({...}? => (otherlv_13= 'unit' ( (lv_unitName_14_0= ruleEString ) ) ) )
            	    {

            	    						getUnorderedGroupHelper().select(grammarAccess.getIntPropertyAccess().getUnorderedGroup_4(), 4);
            	    					
            	    // InternalConceptLanguage.g:2286:9: ({...}? => (otherlv_13= 'unit' ( (lv_unitName_14_0= ruleEString ) ) ) )
            	    // InternalConceptLanguage.g:2286:10: {...}? => (otherlv_13= 'unit' ( (lv_unitName_14_0= ruleEString ) ) )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleIntProperty", "true");
            	    }
            	    // InternalConceptLanguage.g:2286:19: (otherlv_13= 'unit' ( (lv_unitName_14_0= ruleEString ) ) )
            	    // InternalConceptLanguage.g:2286:20: otherlv_13= 'unit' ( (lv_unitName_14_0= ruleEString ) )
            	    {
            	    otherlv_13=(Token)match(input,45,FOLLOW_5); 

            	    									newLeafNode(otherlv_13, grammarAccess.getIntPropertyAccess().getUnitKeyword_4_4_0());
            	    								
            	    // InternalConceptLanguage.g:2290:9: ( (lv_unitName_14_0= ruleEString ) )
            	    // InternalConceptLanguage.g:2291:10: (lv_unitName_14_0= ruleEString )
            	    {
            	    // InternalConceptLanguage.g:2291:10: (lv_unitName_14_0= ruleEString )
            	    // InternalConceptLanguage.g:2292:11: lv_unitName_14_0= ruleEString
            	    {

            	    											newCompositeNode(grammarAccess.getIntPropertyAccess().getUnitNameEStringParserRuleCall_4_4_1_0());
            	    										
            	    pushFollow(FOLLOW_36);
            	    lv_unitName_14_0=ruleEString();

            	    state._fsp--;


            	    											if (current==null) {
            	    												current = createModelElementForParent(grammarAccess.getIntPropertyRule());
            	    											}
            	    											set(
            	    												current,
            	    												"unitName",
            	    												lv_unitName_14_0,
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
            	    break loop31;
                }
            } while (true);


            }


            }

             
            				  getUnorderedGroupHelper().leave(grammarAccess.getIntPropertyAccess().getUnorderedGroup_4());
            				

            }

            otherlv_15=(Token)match(input,22,FOLLOW_2); 

            			newLeafNode(otherlv_15, grammarAccess.getIntPropertyAccess().getSemicolonKeyword_5());
            		

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
    // InternalConceptLanguage.g:2330:1: entryRuleFloatProperty returns [EObject current=null] : iv_ruleFloatProperty= ruleFloatProperty EOF ;
    public final EObject entryRuleFloatProperty() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleFloatProperty = null;


        try {
            // InternalConceptLanguage.g:2330:54: (iv_ruleFloatProperty= ruleFloatProperty EOF )
            // InternalConceptLanguage.g:2331:2: iv_ruleFloatProperty= ruleFloatProperty EOF
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
    // InternalConceptLanguage.g:2337:1: ruleFloatProperty returns [EObject current=null] : ( () otherlv_1= 'FloatProperty' ( (lv_name_2_0= RULE_ID ) ) ( (lv_arrayModifier_3_0= ruleArrayModifier ) )? ( ( ( ( ({...}? => ( ({...}? => (otherlv_5= 'description' ( (lv_description_6_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'default' ( (lv_defaultValue_8_0= ruleFloatLiteralString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_9= 'quantityKind' ( (lv_quantityKindName_10_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_11= 'verification' ( (lv_verification_12_0= ruleVerificationTypeSpecification ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_13= 'unit' ( (lv_unitName_14_0= ruleEString ) ) ) ) ) ) )* ) ) ) otherlv_15= ';' ) ;
    public final EObject ruleFloatProperty() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token lv_name_2_0=null;
        Token otherlv_5=null;
        Token otherlv_7=null;
        Token otherlv_9=null;
        Token otherlv_11=null;
        Token otherlv_13=null;
        Token otherlv_15=null;
        EObject lv_arrayModifier_3_0 = null;

        AntlrDatatypeRuleToken lv_description_6_0 = null;

        AntlrDatatypeRuleToken lv_defaultValue_8_0 = null;

        AntlrDatatypeRuleToken lv_quantityKindName_10_0 = null;

        EObject lv_verification_12_0 = null;

        AntlrDatatypeRuleToken lv_unitName_14_0 = null;



        	enterRule();

        try {
            // InternalConceptLanguage.g:2343:2: ( ( () otherlv_1= 'FloatProperty' ( (lv_name_2_0= RULE_ID ) ) ( (lv_arrayModifier_3_0= ruleArrayModifier ) )? ( ( ( ( ({...}? => ( ({...}? => (otherlv_5= 'description' ( (lv_description_6_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'default' ( (lv_defaultValue_8_0= ruleFloatLiteralString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_9= 'quantityKind' ( (lv_quantityKindName_10_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_11= 'verification' ( (lv_verification_12_0= ruleVerificationTypeSpecification ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_13= 'unit' ( (lv_unitName_14_0= ruleEString ) ) ) ) ) ) )* ) ) ) otherlv_15= ';' ) )
            // InternalConceptLanguage.g:2344:2: ( () otherlv_1= 'FloatProperty' ( (lv_name_2_0= RULE_ID ) ) ( (lv_arrayModifier_3_0= ruleArrayModifier ) )? ( ( ( ( ({...}? => ( ({...}? => (otherlv_5= 'description' ( (lv_description_6_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'default' ( (lv_defaultValue_8_0= ruleFloatLiteralString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_9= 'quantityKind' ( (lv_quantityKindName_10_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_11= 'verification' ( (lv_verification_12_0= ruleVerificationTypeSpecification ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_13= 'unit' ( (lv_unitName_14_0= ruleEString ) ) ) ) ) ) )* ) ) ) otherlv_15= ';' )
            {
            // InternalConceptLanguage.g:2344:2: ( () otherlv_1= 'FloatProperty' ( (lv_name_2_0= RULE_ID ) ) ( (lv_arrayModifier_3_0= ruleArrayModifier ) )? ( ( ( ( ({...}? => ( ({...}? => (otherlv_5= 'description' ( (lv_description_6_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'default' ( (lv_defaultValue_8_0= ruleFloatLiteralString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_9= 'quantityKind' ( (lv_quantityKindName_10_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_11= 'verification' ( (lv_verification_12_0= ruleVerificationTypeSpecification ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_13= 'unit' ( (lv_unitName_14_0= ruleEString ) ) ) ) ) ) )* ) ) ) otherlv_15= ';' )
            // InternalConceptLanguage.g:2345:3: () otherlv_1= 'FloatProperty' ( (lv_name_2_0= RULE_ID ) ) ( (lv_arrayModifier_3_0= ruleArrayModifier ) )? ( ( ( ( ({...}? => ( ({...}? => (otherlv_5= 'description' ( (lv_description_6_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'default' ( (lv_defaultValue_8_0= ruleFloatLiteralString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_9= 'quantityKind' ( (lv_quantityKindName_10_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_11= 'verification' ( (lv_verification_12_0= ruleVerificationTypeSpecification ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_13= 'unit' ( (lv_unitName_14_0= ruleEString ) ) ) ) ) ) )* ) ) ) otherlv_15= ';'
            {
            // InternalConceptLanguage.g:2345:3: ()
            // InternalConceptLanguage.g:2346:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getFloatPropertyAccess().getFloatPropertyAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,48,FOLLOW_3); 

            			newLeafNode(otherlv_1, grammarAccess.getFloatPropertyAccess().getFloatPropertyKeyword_1());
            		
            // InternalConceptLanguage.g:2356:3: ( (lv_name_2_0= RULE_ID ) )
            // InternalConceptLanguage.g:2357:4: (lv_name_2_0= RULE_ID )
            {
            // InternalConceptLanguage.g:2357:4: (lv_name_2_0= RULE_ID )
            // InternalConceptLanguage.g:2358:5: lv_name_2_0= RULE_ID
            {
            lv_name_2_0=(Token)match(input,RULE_ID,FOLLOW_35); 

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

            // InternalConceptLanguage.g:2374:3: ( (lv_arrayModifier_3_0= ruleArrayModifier ) )?
            int alt32=2;
            int LA32_0 = input.LA(1);

            if ( (LA32_0==25) ) {
                alt32=1;
            }
            switch (alt32) {
                case 1 :
                    // InternalConceptLanguage.g:2375:4: (lv_arrayModifier_3_0= ruleArrayModifier )
                    {
                    // InternalConceptLanguage.g:2375:4: (lv_arrayModifier_3_0= ruleArrayModifier )
                    // InternalConceptLanguage.g:2376:5: lv_arrayModifier_3_0= ruleArrayModifier
                    {

                    					newCompositeNode(grammarAccess.getFloatPropertyAccess().getArrayModifierArrayModifierParserRuleCall_3_0());
                    				
                    pushFollow(FOLLOW_36);
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

            // InternalConceptLanguage.g:2393:3: ( ( ( ( ({...}? => ( ({...}? => (otherlv_5= 'description' ( (lv_description_6_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'default' ( (lv_defaultValue_8_0= ruleFloatLiteralString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_9= 'quantityKind' ( (lv_quantityKindName_10_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_11= 'verification' ( (lv_verification_12_0= ruleVerificationTypeSpecification ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_13= 'unit' ( (lv_unitName_14_0= ruleEString ) ) ) ) ) ) )* ) ) )
            // InternalConceptLanguage.g:2394:4: ( ( ( ({...}? => ( ({...}? => (otherlv_5= 'description' ( (lv_description_6_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'default' ( (lv_defaultValue_8_0= ruleFloatLiteralString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_9= 'quantityKind' ( (lv_quantityKindName_10_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_11= 'verification' ( (lv_verification_12_0= ruleVerificationTypeSpecification ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_13= 'unit' ( (lv_unitName_14_0= ruleEString ) ) ) ) ) ) )* ) )
            {
            // InternalConceptLanguage.g:2394:4: ( ( ( ({...}? => ( ({...}? => (otherlv_5= 'description' ( (lv_description_6_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'default' ( (lv_defaultValue_8_0= ruleFloatLiteralString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_9= 'quantityKind' ( (lv_quantityKindName_10_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_11= 'verification' ( (lv_verification_12_0= ruleVerificationTypeSpecification ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_13= 'unit' ( (lv_unitName_14_0= ruleEString ) ) ) ) ) ) )* ) )
            // InternalConceptLanguage.g:2395:5: ( ( ({...}? => ( ({...}? => (otherlv_5= 'description' ( (lv_description_6_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'default' ( (lv_defaultValue_8_0= ruleFloatLiteralString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_9= 'quantityKind' ( (lv_quantityKindName_10_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_11= 'verification' ( (lv_verification_12_0= ruleVerificationTypeSpecification ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_13= 'unit' ( (lv_unitName_14_0= ruleEString ) ) ) ) ) ) )* )
            {
             
            				  getUnorderedGroupHelper().enter(grammarAccess.getFloatPropertyAccess().getUnorderedGroup_4());
            				
            // InternalConceptLanguage.g:2398:5: ( ( ({...}? => ( ({...}? => (otherlv_5= 'description' ( (lv_description_6_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'default' ( (lv_defaultValue_8_0= ruleFloatLiteralString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_9= 'quantityKind' ( (lv_quantityKindName_10_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_11= 'verification' ( (lv_verification_12_0= ruleVerificationTypeSpecification ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_13= 'unit' ( (lv_unitName_14_0= ruleEString ) ) ) ) ) ) )* )
            // InternalConceptLanguage.g:2399:6: ( ({...}? => ( ({...}? => (otherlv_5= 'description' ( (lv_description_6_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'default' ( (lv_defaultValue_8_0= ruleFloatLiteralString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_9= 'quantityKind' ( (lv_quantityKindName_10_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_11= 'verification' ( (lv_verification_12_0= ruleVerificationTypeSpecification ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_13= 'unit' ( (lv_unitName_14_0= ruleEString ) ) ) ) ) ) )*
            {
            // InternalConceptLanguage.g:2399:6: ( ({...}? => ( ({...}? => (otherlv_5= 'description' ( (lv_description_6_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'default' ( (lv_defaultValue_8_0= ruleFloatLiteralString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_9= 'quantityKind' ( (lv_quantityKindName_10_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_11= 'verification' ( (lv_verification_12_0= ruleVerificationTypeSpecification ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_13= 'unit' ( (lv_unitName_14_0= ruleEString ) ) ) ) ) ) )*
            loop33:
            do {
                int alt33=6;
                int LA33_0 = input.LA(1);

                if ( LA33_0 == 15 && getUnorderedGroupHelper().canSelect(grammarAccess.getFloatPropertyAccess().getUnorderedGroup_4(), 0) ) {
                    alt33=1;
                }
                else if ( LA33_0 == 47 && getUnorderedGroupHelper().canSelect(grammarAccess.getFloatPropertyAccess().getUnorderedGroup_4(), 1) ) {
                    alt33=2;
                }
                else if ( LA33_0 == 43 && getUnorderedGroupHelper().canSelect(grammarAccess.getFloatPropertyAccess().getUnorderedGroup_4(), 2) ) {
                    alt33=3;
                }
                else if ( LA33_0 == 44 && getUnorderedGroupHelper().canSelect(grammarAccess.getFloatPropertyAccess().getUnorderedGroup_4(), 3) ) {
                    alt33=4;
                }
                else if ( LA33_0 == 45 && getUnorderedGroupHelper().canSelect(grammarAccess.getFloatPropertyAccess().getUnorderedGroup_4(), 4) ) {
                    alt33=5;
                }


                switch (alt33) {
            	case 1 :
            	    // InternalConceptLanguage.g:2400:4: ({...}? => ( ({...}? => (otherlv_5= 'description' ( (lv_description_6_0= ruleEString ) ) ) ) ) )
            	    {
            	    // InternalConceptLanguage.g:2400:4: ({...}? => ( ({...}? => (otherlv_5= 'description' ( (lv_description_6_0= ruleEString ) ) ) ) ) )
            	    // InternalConceptLanguage.g:2401:5: {...}? => ( ({...}? => (otherlv_5= 'description' ( (lv_description_6_0= ruleEString ) ) ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getFloatPropertyAccess().getUnorderedGroup_4(), 0) ) {
            	        throw new FailedPredicateException(input, "ruleFloatProperty", "getUnorderedGroupHelper().canSelect(grammarAccess.getFloatPropertyAccess().getUnorderedGroup_4(), 0)");
            	    }
            	    // InternalConceptLanguage.g:2401:110: ( ({...}? => (otherlv_5= 'description' ( (lv_description_6_0= ruleEString ) ) ) ) )
            	    // InternalConceptLanguage.g:2402:6: ({...}? => (otherlv_5= 'description' ( (lv_description_6_0= ruleEString ) ) ) )
            	    {

            	    						getUnorderedGroupHelper().select(grammarAccess.getFloatPropertyAccess().getUnorderedGroup_4(), 0);
            	    					
            	    // InternalConceptLanguage.g:2405:9: ({...}? => (otherlv_5= 'description' ( (lv_description_6_0= ruleEString ) ) ) )
            	    // InternalConceptLanguage.g:2405:10: {...}? => (otherlv_5= 'description' ( (lv_description_6_0= ruleEString ) ) )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleFloatProperty", "true");
            	    }
            	    // InternalConceptLanguage.g:2405:19: (otherlv_5= 'description' ( (lv_description_6_0= ruleEString ) ) )
            	    // InternalConceptLanguage.g:2405:20: otherlv_5= 'description' ( (lv_description_6_0= ruleEString ) )
            	    {
            	    otherlv_5=(Token)match(input,15,FOLLOW_5); 

            	    									newLeafNode(otherlv_5, grammarAccess.getFloatPropertyAccess().getDescriptionKeyword_4_0_0());
            	    								
            	    // InternalConceptLanguage.g:2409:9: ( (lv_description_6_0= ruleEString ) )
            	    // InternalConceptLanguage.g:2410:10: (lv_description_6_0= ruleEString )
            	    {
            	    // InternalConceptLanguage.g:2410:10: (lv_description_6_0= ruleEString )
            	    // InternalConceptLanguage.g:2411:11: lv_description_6_0= ruleEString
            	    {

            	    											newCompositeNode(grammarAccess.getFloatPropertyAccess().getDescriptionEStringParserRuleCall_4_0_1_0());
            	    										
            	    pushFollow(FOLLOW_36);
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
            	    // InternalConceptLanguage.g:2434:4: ({...}? => ( ({...}? => (otherlv_7= 'default' ( (lv_defaultValue_8_0= ruleFloatLiteralString ) ) ) ) ) )
            	    {
            	    // InternalConceptLanguage.g:2434:4: ({...}? => ( ({...}? => (otherlv_7= 'default' ( (lv_defaultValue_8_0= ruleFloatLiteralString ) ) ) ) ) )
            	    // InternalConceptLanguage.g:2435:5: {...}? => ( ({...}? => (otherlv_7= 'default' ( (lv_defaultValue_8_0= ruleFloatLiteralString ) ) ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getFloatPropertyAccess().getUnorderedGroup_4(), 1) ) {
            	        throw new FailedPredicateException(input, "ruleFloatProperty", "getUnorderedGroupHelper().canSelect(grammarAccess.getFloatPropertyAccess().getUnorderedGroup_4(), 1)");
            	    }
            	    // InternalConceptLanguage.g:2435:110: ( ({...}? => (otherlv_7= 'default' ( (lv_defaultValue_8_0= ruleFloatLiteralString ) ) ) ) )
            	    // InternalConceptLanguage.g:2436:6: ({...}? => (otherlv_7= 'default' ( (lv_defaultValue_8_0= ruleFloatLiteralString ) ) ) )
            	    {

            	    						getUnorderedGroupHelper().select(grammarAccess.getFloatPropertyAccess().getUnorderedGroup_4(), 1);
            	    					
            	    // InternalConceptLanguage.g:2439:9: ({...}? => (otherlv_7= 'default' ( (lv_defaultValue_8_0= ruleFloatLiteralString ) ) ) )
            	    // InternalConceptLanguage.g:2439:10: {...}? => (otherlv_7= 'default' ( (lv_defaultValue_8_0= ruleFloatLiteralString ) ) )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleFloatProperty", "true");
            	    }
            	    // InternalConceptLanguage.g:2439:19: (otherlv_7= 'default' ( (lv_defaultValue_8_0= ruleFloatLiteralString ) ) )
            	    // InternalConceptLanguage.g:2439:20: otherlv_7= 'default' ( (lv_defaultValue_8_0= ruleFloatLiteralString ) )
            	    {
            	    otherlv_7=(Token)match(input,47,FOLLOW_38); 

            	    									newLeafNode(otherlv_7, grammarAccess.getFloatPropertyAccess().getDefaultKeyword_4_1_0());
            	    								
            	    // InternalConceptLanguage.g:2443:9: ( (lv_defaultValue_8_0= ruleFloatLiteralString ) )
            	    // InternalConceptLanguage.g:2444:10: (lv_defaultValue_8_0= ruleFloatLiteralString )
            	    {
            	    // InternalConceptLanguage.g:2444:10: (lv_defaultValue_8_0= ruleFloatLiteralString )
            	    // InternalConceptLanguage.g:2445:11: lv_defaultValue_8_0= ruleFloatLiteralString
            	    {

            	    											newCompositeNode(grammarAccess.getFloatPropertyAccess().getDefaultValueFloatLiteralStringParserRuleCall_4_1_1_0());
            	    										
            	    pushFollow(FOLLOW_36);
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
            	    // InternalConceptLanguage.g:2468:4: ({...}? => ( ({...}? => (otherlv_9= 'quantityKind' ( (lv_quantityKindName_10_0= ruleEString ) ) ) ) ) )
            	    {
            	    // InternalConceptLanguage.g:2468:4: ({...}? => ( ({...}? => (otherlv_9= 'quantityKind' ( (lv_quantityKindName_10_0= ruleEString ) ) ) ) ) )
            	    // InternalConceptLanguage.g:2469:5: {...}? => ( ({...}? => (otherlv_9= 'quantityKind' ( (lv_quantityKindName_10_0= ruleEString ) ) ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getFloatPropertyAccess().getUnorderedGroup_4(), 2) ) {
            	        throw new FailedPredicateException(input, "ruleFloatProperty", "getUnorderedGroupHelper().canSelect(grammarAccess.getFloatPropertyAccess().getUnorderedGroup_4(), 2)");
            	    }
            	    // InternalConceptLanguage.g:2469:110: ( ({...}? => (otherlv_9= 'quantityKind' ( (lv_quantityKindName_10_0= ruleEString ) ) ) ) )
            	    // InternalConceptLanguage.g:2470:6: ({...}? => (otherlv_9= 'quantityKind' ( (lv_quantityKindName_10_0= ruleEString ) ) ) )
            	    {

            	    						getUnorderedGroupHelper().select(grammarAccess.getFloatPropertyAccess().getUnorderedGroup_4(), 2);
            	    					
            	    // InternalConceptLanguage.g:2473:9: ({...}? => (otherlv_9= 'quantityKind' ( (lv_quantityKindName_10_0= ruleEString ) ) ) )
            	    // InternalConceptLanguage.g:2473:10: {...}? => (otherlv_9= 'quantityKind' ( (lv_quantityKindName_10_0= ruleEString ) ) )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleFloatProperty", "true");
            	    }
            	    // InternalConceptLanguage.g:2473:19: (otherlv_9= 'quantityKind' ( (lv_quantityKindName_10_0= ruleEString ) ) )
            	    // InternalConceptLanguage.g:2473:20: otherlv_9= 'quantityKind' ( (lv_quantityKindName_10_0= ruleEString ) )
            	    {
            	    otherlv_9=(Token)match(input,43,FOLLOW_5); 

            	    									newLeafNode(otherlv_9, grammarAccess.getFloatPropertyAccess().getQuantityKindKeyword_4_2_0());
            	    								
            	    // InternalConceptLanguage.g:2477:9: ( (lv_quantityKindName_10_0= ruleEString ) )
            	    // InternalConceptLanguage.g:2478:10: (lv_quantityKindName_10_0= ruleEString )
            	    {
            	    // InternalConceptLanguage.g:2478:10: (lv_quantityKindName_10_0= ruleEString )
            	    // InternalConceptLanguage.g:2479:11: lv_quantityKindName_10_0= ruleEString
            	    {

            	    											newCompositeNode(grammarAccess.getFloatPropertyAccess().getQuantityKindNameEStringParserRuleCall_4_2_1_0());
            	    										
            	    pushFollow(FOLLOW_36);
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
            	    // InternalConceptLanguage.g:2502:4: ({...}? => ( ({...}? => (otherlv_11= 'verification' ( (lv_verification_12_0= ruleVerificationTypeSpecification ) ) ) ) ) )
            	    {
            	    // InternalConceptLanguage.g:2502:4: ({...}? => ( ({...}? => (otherlv_11= 'verification' ( (lv_verification_12_0= ruleVerificationTypeSpecification ) ) ) ) ) )
            	    // InternalConceptLanguage.g:2503:5: {...}? => ( ({...}? => (otherlv_11= 'verification' ( (lv_verification_12_0= ruleVerificationTypeSpecification ) ) ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getFloatPropertyAccess().getUnorderedGroup_4(), 3) ) {
            	        throw new FailedPredicateException(input, "ruleFloatProperty", "getUnorderedGroupHelper().canSelect(grammarAccess.getFloatPropertyAccess().getUnorderedGroup_4(), 3)");
            	    }
            	    // InternalConceptLanguage.g:2503:110: ( ({...}? => (otherlv_11= 'verification' ( (lv_verification_12_0= ruleVerificationTypeSpecification ) ) ) ) )
            	    // InternalConceptLanguage.g:2504:6: ({...}? => (otherlv_11= 'verification' ( (lv_verification_12_0= ruleVerificationTypeSpecification ) ) ) )
            	    {

            	    						getUnorderedGroupHelper().select(grammarAccess.getFloatPropertyAccess().getUnorderedGroup_4(), 3);
            	    					
            	    // InternalConceptLanguage.g:2507:9: ({...}? => (otherlv_11= 'verification' ( (lv_verification_12_0= ruleVerificationTypeSpecification ) ) ) )
            	    // InternalConceptLanguage.g:2507:10: {...}? => (otherlv_11= 'verification' ( (lv_verification_12_0= ruleVerificationTypeSpecification ) ) )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleFloatProperty", "true");
            	    }
            	    // InternalConceptLanguage.g:2507:19: (otherlv_11= 'verification' ( (lv_verification_12_0= ruleVerificationTypeSpecification ) ) )
            	    // InternalConceptLanguage.g:2507:20: otherlv_11= 'verification' ( (lv_verification_12_0= ruleVerificationTypeSpecification ) )
            	    {
            	    otherlv_11=(Token)match(input,44,FOLLOW_3); 

            	    									newLeafNode(otherlv_11, grammarAccess.getFloatPropertyAccess().getVerificationKeyword_4_3_0());
            	    								
            	    // InternalConceptLanguage.g:2511:9: ( (lv_verification_12_0= ruleVerificationTypeSpecification ) )
            	    // InternalConceptLanguage.g:2512:10: (lv_verification_12_0= ruleVerificationTypeSpecification )
            	    {
            	    // InternalConceptLanguage.g:2512:10: (lv_verification_12_0= ruleVerificationTypeSpecification )
            	    // InternalConceptLanguage.g:2513:11: lv_verification_12_0= ruleVerificationTypeSpecification
            	    {

            	    											newCompositeNode(grammarAccess.getFloatPropertyAccess().getVerificationVerificationTypeSpecificationParserRuleCall_4_3_1_0());
            	    										
            	    pushFollow(FOLLOW_36);
            	    lv_verification_12_0=ruleVerificationTypeSpecification();

            	    state._fsp--;


            	    											if (current==null) {
            	    												current = createModelElementForParent(grammarAccess.getFloatPropertyRule());
            	    											}
            	    											set(
            	    												current,
            	    												"verification",
            	    												lv_verification_12_0,
            	    												"de.dlr.sc.virsat.model.concept.ConceptLanguage.VerificationTypeSpecification");
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
            	case 5 :
            	    // InternalConceptLanguage.g:2536:4: ({...}? => ( ({...}? => (otherlv_13= 'unit' ( (lv_unitName_14_0= ruleEString ) ) ) ) ) )
            	    {
            	    // InternalConceptLanguage.g:2536:4: ({...}? => ( ({...}? => (otherlv_13= 'unit' ( (lv_unitName_14_0= ruleEString ) ) ) ) ) )
            	    // InternalConceptLanguage.g:2537:5: {...}? => ( ({...}? => (otherlv_13= 'unit' ( (lv_unitName_14_0= ruleEString ) ) ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getFloatPropertyAccess().getUnorderedGroup_4(), 4) ) {
            	        throw new FailedPredicateException(input, "ruleFloatProperty", "getUnorderedGroupHelper().canSelect(grammarAccess.getFloatPropertyAccess().getUnorderedGroup_4(), 4)");
            	    }
            	    // InternalConceptLanguage.g:2537:110: ( ({...}? => (otherlv_13= 'unit' ( (lv_unitName_14_0= ruleEString ) ) ) ) )
            	    // InternalConceptLanguage.g:2538:6: ({...}? => (otherlv_13= 'unit' ( (lv_unitName_14_0= ruleEString ) ) ) )
            	    {

            	    						getUnorderedGroupHelper().select(grammarAccess.getFloatPropertyAccess().getUnorderedGroup_4(), 4);
            	    					
            	    // InternalConceptLanguage.g:2541:9: ({...}? => (otherlv_13= 'unit' ( (lv_unitName_14_0= ruleEString ) ) ) )
            	    // InternalConceptLanguage.g:2541:10: {...}? => (otherlv_13= 'unit' ( (lv_unitName_14_0= ruleEString ) ) )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleFloatProperty", "true");
            	    }
            	    // InternalConceptLanguage.g:2541:19: (otherlv_13= 'unit' ( (lv_unitName_14_0= ruleEString ) ) )
            	    // InternalConceptLanguage.g:2541:20: otherlv_13= 'unit' ( (lv_unitName_14_0= ruleEString ) )
            	    {
            	    otherlv_13=(Token)match(input,45,FOLLOW_5); 

            	    									newLeafNode(otherlv_13, grammarAccess.getFloatPropertyAccess().getUnitKeyword_4_4_0());
            	    								
            	    // InternalConceptLanguage.g:2545:9: ( (lv_unitName_14_0= ruleEString ) )
            	    // InternalConceptLanguage.g:2546:10: (lv_unitName_14_0= ruleEString )
            	    {
            	    // InternalConceptLanguage.g:2546:10: (lv_unitName_14_0= ruleEString )
            	    // InternalConceptLanguage.g:2547:11: lv_unitName_14_0= ruleEString
            	    {

            	    											newCompositeNode(grammarAccess.getFloatPropertyAccess().getUnitNameEStringParserRuleCall_4_4_1_0());
            	    										
            	    pushFollow(FOLLOW_36);
            	    lv_unitName_14_0=ruleEString();

            	    state._fsp--;


            	    											if (current==null) {
            	    												current = createModelElementForParent(grammarAccess.getFloatPropertyRule());
            	    											}
            	    											set(
            	    												current,
            	    												"unitName",
            	    												lv_unitName_14_0,
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
            	    break loop33;
                }
            } while (true);


            }


            }

             
            				  getUnorderedGroupHelper().leave(grammarAccess.getFloatPropertyAccess().getUnorderedGroup_4());
            				

            }

            otherlv_15=(Token)match(input,22,FOLLOW_2); 

            			newLeafNode(otherlv_15, grammarAccess.getFloatPropertyAccess().getSemicolonKeyword_5());
            		

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
    // InternalConceptLanguage.g:2585:1: entryRuleStringProperty returns [EObject current=null] : iv_ruleStringProperty= ruleStringProperty EOF ;
    public final EObject entryRuleStringProperty() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleStringProperty = null;


        try {
            // InternalConceptLanguage.g:2585:55: (iv_ruleStringProperty= ruleStringProperty EOF )
            // InternalConceptLanguage.g:2586:2: iv_ruleStringProperty= ruleStringProperty EOF
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
    // InternalConceptLanguage.g:2592:1: ruleStringProperty returns [EObject current=null] : ( () otherlv_1= 'StringProperty' ( (lv_name_2_0= RULE_ID ) ) ( (lv_arrayModifier_3_0= ruleArrayModifier ) )? ( ( ( ( ({...}? => ( ({...}? => (otherlv_5= 'description' ( (lv_description_6_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'verification' ( (lv_verification_8_0= ruleVerificationTypeSpecification ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_9= 'default' ( (lv_defaultValue_10_0= ruleEString ) ) ) ) ) ) )* ) ) ) otherlv_11= ';' ) ;
    public final EObject ruleStringProperty() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token lv_name_2_0=null;
        Token otherlv_5=null;
        Token otherlv_7=null;
        Token otherlv_9=null;
        Token otherlv_11=null;
        EObject lv_arrayModifier_3_0 = null;

        AntlrDatatypeRuleToken lv_description_6_0 = null;

        EObject lv_verification_8_0 = null;

        AntlrDatatypeRuleToken lv_defaultValue_10_0 = null;



        	enterRule();

        try {
            // InternalConceptLanguage.g:2598:2: ( ( () otherlv_1= 'StringProperty' ( (lv_name_2_0= RULE_ID ) ) ( (lv_arrayModifier_3_0= ruleArrayModifier ) )? ( ( ( ( ({...}? => ( ({...}? => (otherlv_5= 'description' ( (lv_description_6_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'verification' ( (lv_verification_8_0= ruleVerificationTypeSpecification ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_9= 'default' ( (lv_defaultValue_10_0= ruleEString ) ) ) ) ) ) )* ) ) ) otherlv_11= ';' ) )
            // InternalConceptLanguage.g:2599:2: ( () otherlv_1= 'StringProperty' ( (lv_name_2_0= RULE_ID ) ) ( (lv_arrayModifier_3_0= ruleArrayModifier ) )? ( ( ( ( ({...}? => ( ({...}? => (otherlv_5= 'description' ( (lv_description_6_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'verification' ( (lv_verification_8_0= ruleVerificationTypeSpecification ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_9= 'default' ( (lv_defaultValue_10_0= ruleEString ) ) ) ) ) ) )* ) ) ) otherlv_11= ';' )
            {
            // InternalConceptLanguage.g:2599:2: ( () otherlv_1= 'StringProperty' ( (lv_name_2_0= RULE_ID ) ) ( (lv_arrayModifier_3_0= ruleArrayModifier ) )? ( ( ( ( ({...}? => ( ({...}? => (otherlv_5= 'description' ( (lv_description_6_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'verification' ( (lv_verification_8_0= ruleVerificationTypeSpecification ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_9= 'default' ( (lv_defaultValue_10_0= ruleEString ) ) ) ) ) ) )* ) ) ) otherlv_11= ';' )
            // InternalConceptLanguage.g:2600:3: () otherlv_1= 'StringProperty' ( (lv_name_2_0= RULE_ID ) ) ( (lv_arrayModifier_3_0= ruleArrayModifier ) )? ( ( ( ( ({...}? => ( ({...}? => (otherlv_5= 'description' ( (lv_description_6_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'verification' ( (lv_verification_8_0= ruleVerificationTypeSpecification ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_9= 'default' ( (lv_defaultValue_10_0= ruleEString ) ) ) ) ) ) )* ) ) ) otherlv_11= ';'
            {
            // InternalConceptLanguage.g:2600:3: ()
            // InternalConceptLanguage.g:2601:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getStringPropertyAccess().getStringPropertyAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,49,FOLLOW_3); 

            			newLeafNode(otherlv_1, grammarAccess.getStringPropertyAccess().getStringPropertyKeyword_1());
            		
            // InternalConceptLanguage.g:2611:3: ( (lv_name_2_0= RULE_ID ) )
            // InternalConceptLanguage.g:2612:4: (lv_name_2_0= RULE_ID )
            {
            // InternalConceptLanguage.g:2612:4: (lv_name_2_0= RULE_ID )
            // InternalConceptLanguage.g:2613:5: lv_name_2_0= RULE_ID
            {
            lv_name_2_0=(Token)match(input,RULE_ID,FOLLOW_39); 

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

            // InternalConceptLanguage.g:2629:3: ( (lv_arrayModifier_3_0= ruleArrayModifier ) )?
            int alt34=2;
            int LA34_0 = input.LA(1);

            if ( (LA34_0==25) ) {
                alt34=1;
            }
            switch (alt34) {
                case 1 :
                    // InternalConceptLanguage.g:2630:4: (lv_arrayModifier_3_0= ruleArrayModifier )
                    {
                    // InternalConceptLanguage.g:2630:4: (lv_arrayModifier_3_0= ruleArrayModifier )
                    // InternalConceptLanguage.g:2631:5: lv_arrayModifier_3_0= ruleArrayModifier
                    {

                    					newCompositeNode(grammarAccess.getStringPropertyAccess().getArrayModifierArrayModifierParserRuleCall_3_0());
                    				
                    pushFollow(FOLLOW_40);
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

            // InternalConceptLanguage.g:2648:3: ( ( ( ( ({...}? => ( ({...}? => (otherlv_5= 'description' ( (lv_description_6_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'verification' ( (lv_verification_8_0= ruleVerificationTypeSpecification ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_9= 'default' ( (lv_defaultValue_10_0= ruleEString ) ) ) ) ) ) )* ) ) )
            // InternalConceptLanguage.g:2649:4: ( ( ( ({...}? => ( ({...}? => (otherlv_5= 'description' ( (lv_description_6_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'verification' ( (lv_verification_8_0= ruleVerificationTypeSpecification ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_9= 'default' ( (lv_defaultValue_10_0= ruleEString ) ) ) ) ) ) )* ) )
            {
            // InternalConceptLanguage.g:2649:4: ( ( ( ({...}? => ( ({...}? => (otherlv_5= 'description' ( (lv_description_6_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'verification' ( (lv_verification_8_0= ruleVerificationTypeSpecification ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_9= 'default' ( (lv_defaultValue_10_0= ruleEString ) ) ) ) ) ) )* ) )
            // InternalConceptLanguage.g:2650:5: ( ( ({...}? => ( ({...}? => (otherlv_5= 'description' ( (lv_description_6_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'verification' ( (lv_verification_8_0= ruleVerificationTypeSpecification ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_9= 'default' ( (lv_defaultValue_10_0= ruleEString ) ) ) ) ) ) )* )
            {
             
            				  getUnorderedGroupHelper().enter(grammarAccess.getStringPropertyAccess().getUnorderedGroup_4());
            				
            // InternalConceptLanguage.g:2653:5: ( ( ({...}? => ( ({...}? => (otherlv_5= 'description' ( (lv_description_6_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'verification' ( (lv_verification_8_0= ruleVerificationTypeSpecification ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_9= 'default' ( (lv_defaultValue_10_0= ruleEString ) ) ) ) ) ) )* )
            // InternalConceptLanguage.g:2654:6: ( ({...}? => ( ({...}? => (otherlv_5= 'description' ( (lv_description_6_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'verification' ( (lv_verification_8_0= ruleVerificationTypeSpecification ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_9= 'default' ( (lv_defaultValue_10_0= ruleEString ) ) ) ) ) ) )*
            {
            // InternalConceptLanguage.g:2654:6: ( ({...}? => ( ({...}? => (otherlv_5= 'description' ( (lv_description_6_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'verification' ( (lv_verification_8_0= ruleVerificationTypeSpecification ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_9= 'default' ( (lv_defaultValue_10_0= ruleEString ) ) ) ) ) ) )*
            loop35:
            do {
                int alt35=4;
                int LA35_0 = input.LA(1);

                if ( LA35_0 == 15 && getUnorderedGroupHelper().canSelect(grammarAccess.getStringPropertyAccess().getUnorderedGroup_4(), 0) ) {
                    alt35=1;
                }
                else if ( LA35_0 == 44 && getUnorderedGroupHelper().canSelect(grammarAccess.getStringPropertyAccess().getUnorderedGroup_4(), 1) ) {
                    alt35=2;
                }
                else if ( LA35_0 == 47 && getUnorderedGroupHelper().canSelect(grammarAccess.getStringPropertyAccess().getUnorderedGroup_4(), 2) ) {
                    alt35=3;
                }


                switch (alt35) {
            	case 1 :
            	    // InternalConceptLanguage.g:2655:4: ({...}? => ( ({...}? => (otherlv_5= 'description' ( (lv_description_6_0= ruleEString ) ) ) ) ) )
            	    {
            	    // InternalConceptLanguage.g:2655:4: ({...}? => ( ({...}? => (otherlv_5= 'description' ( (lv_description_6_0= ruleEString ) ) ) ) ) )
            	    // InternalConceptLanguage.g:2656:5: {...}? => ( ({...}? => (otherlv_5= 'description' ( (lv_description_6_0= ruleEString ) ) ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getStringPropertyAccess().getUnorderedGroup_4(), 0) ) {
            	        throw new FailedPredicateException(input, "ruleStringProperty", "getUnorderedGroupHelper().canSelect(grammarAccess.getStringPropertyAccess().getUnorderedGroup_4(), 0)");
            	    }
            	    // InternalConceptLanguage.g:2656:111: ( ({...}? => (otherlv_5= 'description' ( (lv_description_6_0= ruleEString ) ) ) ) )
            	    // InternalConceptLanguage.g:2657:6: ({...}? => (otherlv_5= 'description' ( (lv_description_6_0= ruleEString ) ) ) )
            	    {

            	    						getUnorderedGroupHelper().select(grammarAccess.getStringPropertyAccess().getUnorderedGroup_4(), 0);
            	    					
            	    // InternalConceptLanguage.g:2660:9: ({...}? => (otherlv_5= 'description' ( (lv_description_6_0= ruleEString ) ) ) )
            	    // InternalConceptLanguage.g:2660:10: {...}? => (otherlv_5= 'description' ( (lv_description_6_0= ruleEString ) ) )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleStringProperty", "true");
            	    }
            	    // InternalConceptLanguage.g:2660:19: (otherlv_5= 'description' ( (lv_description_6_0= ruleEString ) ) )
            	    // InternalConceptLanguage.g:2660:20: otherlv_5= 'description' ( (lv_description_6_0= ruleEString ) )
            	    {
            	    otherlv_5=(Token)match(input,15,FOLLOW_5); 

            	    									newLeafNode(otherlv_5, grammarAccess.getStringPropertyAccess().getDescriptionKeyword_4_0_0());
            	    								
            	    // InternalConceptLanguage.g:2664:9: ( (lv_description_6_0= ruleEString ) )
            	    // InternalConceptLanguage.g:2665:10: (lv_description_6_0= ruleEString )
            	    {
            	    // InternalConceptLanguage.g:2665:10: (lv_description_6_0= ruleEString )
            	    // InternalConceptLanguage.g:2666:11: lv_description_6_0= ruleEString
            	    {

            	    											newCompositeNode(grammarAccess.getStringPropertyAccess().getDescriptionEStringParserRuleCall_4_0_1_0());
            	    										
            	    pushFollow(FOLLOW_40);
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
            	    // InternalConceptLanguage.g:2689:4: ({...}? => ( ({...}? => (otherlv_7= 'verification' ( (lv_verification_8_0= ruleVerificationTypeSpecification ) ) ) ) ) )
            	    {
            	    // InternalConceptLanguage.g:2689:4: ({...}? => ( ({...}? => (otherlv_7= 'verification' ( (lv_verification_8_0= ruleVerificationTypeSpecification ) ) ) ) ) )
            	    // InternalConceptLanguage.g:2690:5: {...}? => ( ({...}? => (otherlv_7= 'verification' ( (lv_verification_8_0= ruleVerificationTypeSpecification ) ) ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getStringPropertyAccess().getUnorderedGroup_4(), 1) ) {
            	        throw new FailedPredicateException(input, "ruleStringProperty", "getUnorderedGroupHelper().canSelect(grammarAccess.getStringPropertyAccess().getUnorderedGroup_4(), 1)");
            	    }
            	    // InternalConceptLanguage.g:2690:111: ( ({...}? => (otherlv_7= 'verification' ( (lv_verification_8_0= ruleVerificationTypeSpecification ) ) ) ) )
            	    // InternalConceptLanguage.g:2691:6: ({...}? => (otherlv_7= 'verification' ( (lv_verification_8_0= ruleVerificationTypeSpecification ) ) ) )
            	    {

            	    						getUnorderedGroupHelper().select(grammarAccess.getStringPropertyAccess().getUnorderedGroup_4(), 1);
            	    					
            	    // InternalConceptLanguage.g:2694:9: ({...}? => (otherlv_7= 'verification' ( (lv_verification_8_0= ruleVerificationTypeSpecification ) ) ) )
            	    // InternalConceptLanguage.g:2694:10: {...}? => (otherlv_7= 'verification' ( (lv_verification_8_0= ruleVerificationTypeSpecification ) ) )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleStringProperty", "true");
            	    }
            	    // InternalConceptLanguage.g:2694:19: (otherlv_7= 'verification' ( (lv_verification_8_0= ruleVerificationTypeSpecification ) ) )
            	    // InternalConceptLanguage.g:2694:20: otherlv_7= 'verification' ( (lv_verification_8_0= ruleVerificationTypeSpecification ) )
            	    {
            	    otherlv_7=(Token)match(input,44,FOLLOW_3); 

            	    									newLeafNode(otherlv_7, grammarAccess.getStringPropertyAccess().getVerificationKeyword_4_1_0());
            	    								
            	    // InternalConceptLanguage.g:2698:9: ( (lv_verification_8_0= ruleVerificationTypeSpecification ) )
            	    // InternalConceptLanguage.g:2699:10: (lv_verification_8_0= ruleVerificationTypeSpecification )
            	    {
            	    // InternalConceptLanguage.g:2699:10: (lv_verification_8_0= ruleVerificationTypeSpecification )
            	    // InternalConceptLanguage.g:2700:11: lv_verification_8_0= ruleVerificationTypeSpecification
            	    {

            	    											newCompositeNode(grammarAccess.getStringPropertyAccess().getVerificationVerificationTypeSpecificationParserRuleCall_4_1_1_0());
            	    										
            	    pushFollow(FOLLOW_40);
            	    lv_verification_8_0=ruleVerificationTypeSpecification();

            	    state._fsp--;


            	    											if (current==null) {
            	    												current = createModelElementForParent(grammarAccess.getStringPropertyRule());
            	    											}
            	    											set(
            	    												current,
            	    												"verification",
            	    												lv_verification_8_0,
            	    												"de.dlr.sc.virsat.model.concept.ConceptLanguage.VerificationTypeSpecification");
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
            	case 3 :
            	    // InternalConceptLanguage.g:2723:4: ({...}? => ( ({...}? => (otherlv_9= 'default' ( (lv_defaultValue_10_0= ruleEString ) ) ) ) ) )
            	    {
            	    // InternalConceptLanguage.g:2723:4: ({...}? => ( ({...}? => (otherlv_9= 'default' ( (lv_defaultValue_10_0= ruleEString ) ) ) ) ) )
            	    // InternalConceptLanguage.g:2724:5: {...}? => ( ({...}? => (otherlv_9= 'default' ( (lv_defaultValue_10_0= ruleEString ) ) ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getStringPropertyAccess().getUnorderedGroup_4(), 2) ) {
            	        throw new FailedPredicateException(input, "ruleStringProperty", "getUnorderedGroupHelper().canSelect(grammarAccess.getStringPropertyAccess().getUnorderedGroup_4(), 2)");
            	    }
            	    // InternalConceptLanguage.g:2724:111: ( ({...}? => (otherlv_9= 'default' ( (lv_defaultValue_10_0= ruleEString ) ) ) ) )
            	    // InternalConceptLanguage.g:2725:6: ({...}? => (otherlv_9= 'default' ( (lv_defaultValue_10_0= ruleEString ) ) ) )
            	    {

            	    						getUnorderedGroupHelper().select(grammarAccess.getStringPropertyAccess().getUnorderedGroup_4(), 2);
            	    					
            	    // InternalConceptLanguage.g:2728:9: ({...}? => (otherlv_9= 'default' ( (lv_defaultValue_10_0= ruleEString ) ) ) )
            	    // InternalConceptLanguage.g:2728:10: {...}? => (otherlv_9= 'default' ( (lv_defaultValue_10_0= ruleEString ) ) )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleStringProperty", "true");
            	    }
            	    // InternalConceptLanguage.g:2728:19: (otherlv_9= 'default' ( (lv_defaultValue_10_0= ruleEString ) ) )
            	    // InternalConceptLanguage.g:2728:20: otherlv_9= 'default' ( (lv_defaultValue_10_0= ruleEString ) )
            	    {
            	    otherlv_9=(Token)match(input,47,FOLLOW_5); 

            	    									newLeafNode(otherlv_9, grammarAccess.getStringPropertyAccess().getDefaultKeyword_4_2_0());
            	    								
            	    // InternalConceptLanguage.g:2732:9: ( (lv_defaultValue_10_0= ruleEString ) )
            	    // InternalConceptLanguage.g:2733:10: (lv_defaultValue_10_0= ruleEString )
            	    {
            	    // InternalConceptLanguage.g:2733:10: (lv_defaultValue_10_0= ruleEString )
            	    // InternalConceptLanguage.g:2734:11: lv_defaultValue_10_0= ruleEString
            	    {

            	    											newCompositeNode(grammarAccess.getStringPropertyAccess().getDefaultValueEStringParserRuleCall_4_2_1_0());
            	    										
            	    pushFollow(FOLLOW_40);
            	    lv_defaultValue_10_0=ruleEString();

            	    state._fsp--;


            	    											if (current==null) {
            	    												current = createModelElementForParent(grammarAccess.getStringPropertyRule());
            	    											}
            	    											set(
            	    												current,
            	    												"defaultValue",
            	    												lv_defaultValue_10_0,
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
            	    break loop35;
                }
            } while (true);


            }


            }

             
            				  getUnorderedGroupHelper().leave(grammarAccess.getStringPropertyAccess().getUnorderedGroup_4());
            				

            }

            otherlv_11=(Token)match(input,22,FOLLOW_2); 

            			newLeafNode(otherlv_11, grammarAccess.getStringPropertyAccess().getSemicolonKeyword_5());
            		

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
    // InternalConceptLanguage.g:2772:1: entryRuleBooleanProperty returns [EObject current=null] : iv_ruleBooleanProperty= ruleBooleanProperty EOF ;
    public final EObject entryRuleBooleanProperty() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleBooleanProperty = null;


        try {
            // InternalConceptLanguage.g:2772:56: (iv_ruleBooleanProperty= ruleBooleanProperty EOF )
            // InternalConceptLanguage.g:2773:2: iv_ruleBooleanProperty= ruleBooleanProperty EOF
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
    // InternalConceptLanguage.g:2779:1: ruleBooleanProperty returns [EObject current=null] : (otherlv_0= 'BooleanProperty' ( (lv_name_1_0= RULE_ID ) ) ( (lv_arrayModifier_2_0= ruleArrayModifier ) )? ( ( ( ( ({...}? => ( ({...}? => (otherlv_4= 'description' ( (lv_description_5_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_6= 'verification' ( (lv_verification_7_0= ruleVerificationTypeSpecification ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_8= 'default' ( (lv_defaultValue_9_0= ruleBooleanLiteralString ) ) ) ) ) ) )* ) ) ) otherlv_10= ';' ) ;
    public final EObject ruleBooleanProperty() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token lv_name_1_0=null;
        Token otherlv_4=null;
        Token otherlv_6=null;
        Token otherlv_8=null;
        Token otherlv_10=null;
        EObject lv_arrayModifier_2_0 = null;

        AntlrDatatypeRuleToken lv_description_5_0 = null;

        EObject lv_verification_7_0 = null;

        AntlrDatatypeRuleToken lv_defaultValue_9_0 = null;



        	enterRule();

        try {
            // InternalConceptLanguage.g:2785:2: ( (otherlv_0= 'BooleanProperty' ( (lv_name_1_0= RULE_ID ) ) ( (lv_arrayModifier_2_0= ruleArrayModifier ) )? ( ( ( ( ({...}? => ( ({...}? => (otherlv_4= 'description' ( (lv_description_5_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_6= 'verification' ( (lv_verification_7_0= ruleVerificationTypeSpecification ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_8= 'default' ( (lv_defaultValue_9_0= ruleBooleanLiteralString ) ) ) ) ) ) )* ) ) ) otherlv_10= ';' ) )
            // InternalConceptLanguage.g:2786:2: (otherlv_0= 'BooleanProperty' ( (lv_name_1_0= RULE_ID ) ) ( (lv_arrayModifier_2_0= ruleArrayModifier ) )? ( ( ( ( ({...}? => ( ({...}? => (otherlv_4= 'description' ( (lv_description_5_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_6= 'verification' ( (lv_verification_7_0= ruleVerificationTypeSpecification ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_8= 'default' ( (lv_defaultValue_9_0= ruleBooleanLiteralString ) ) ) ) ) ) )* ) ) ) otherlv_10= ';' )
            {
            // InternalConceptLanguage.g:2786:2: (otherlv_0= 'BooleanProperty' ( (lv_name_1_0= RULE_ID ) ) ( (lv_arrayModifier_2_0= ruleArrayModifier ) )? ( ( ( ( ({...}? => ( ({...}? => (otherlv_4= 'description' ( (lv_description_5_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_6= 'verification' ( (lv_verification_7_0= ruleVerificationTypeSpecification ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_8= 'default' ( (lv_defaultValue_9_0= ruleBooleanLiteralString ) ) ) ) ) ) )* ) ) ) otherlv_10= ';' )
            // InternalConceptLanguage.g:2787:3: otherlv_0= 'BooleanProperty' ( (lv_name_1_0= RULE_ID ) ) ( (lv_arrayModifier_2_0= ruleArrayModifier ) )? ( ( ( ( ({...}? => ( ({...}? => (otherlv_4= 'description' ( (lv_description_5_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_6= 'verification' ( (lv_verification_7_0= ruleVerificationTypeSpecification ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_8= 'default' ( (lv_defaultValue_9_0= ruleBooleanLiteralString ) ) ) ) ) ) )* ) ) ) otherlv_10= ';'
            {
            otherlv_0=(Token)match(input,50,FOLLOW_3); 

            			newLeafNode(otherlv_0, grammarAccess.getBooleanPropertyAccess().getBooleanPropertyKeyword_0());
            		
            // InternalConceptLanguage.g:2791:3: ( (lv_name_1_0= RULE_ID ) )
            // InternalConceptLanguage.g:2792:4: (lv_name_1_0= RULE_ID )
            {
            // InternalConceptLanguage.g:2792:4: (lv_name_1_0= RULE_ID )
            // InternalConceptLanguage.g:2793:5: lv_name_1_0= RULE_ID
            {
            lv_name_1_0=(Token)match(input,RULE_ID,FOLLOW_39); 

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

            // InternalConceptLanguage.g:2809:3: ( (lv_arrayModifier_2_0= ruleArrayModifier ) )?
            int alt36=2;
            int LA36_0 = input.LA(1);

            if ( (LA36_0==25) ) {
                alt36=1;
            }
            switch (alt36) {
                case 1 :
                    // InternalConceptLanguage.g:2810:4: (lv_arrayModifier_2_0= ruleArrayModifier )
                    {
                    // InternalConceptLanguage.g:2810:4: (lv_arrayModifier_2_0= ruleArrayModifier )
                    // InternalConceptLanguage.g:2811:5: lv_arrayModifier_2_0= ruleArrayModifier
                    {

                    					newCompositeNode(grammarAccess.getBooleanPropertyAccess().getArrayModifierArrayModifierParserRuleCall_2_0());
                    				
                    pushFollow(FOLLOW_40);
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

            // InternalConceptLanguage.g:2828:3: ( ( ( ( ({...}? => ( ({...}? => (otherlv_4= 'description' ( (lv_description_5_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_6= 'verification' ( (lv_verification_7_0= ruleVerificationTypeSpecification ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_8= 'default' ( (lv_defaultValue_9_0= ruleBooleanLiteralString ) ) ) ) ) ) )* ) ) )
            // InternalConceptLanguage.g:2829:4: ( ( ( ({...}? => ( ({...}? => (otherlv_4= 'description' ( (lv_description_5_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_6= 'verification' ( (lv_verification_7_0= ruleVerificationTypeSpecification ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_8= 'default' ( (lv_defaultValue_9_0= ruleBooleanLiteralString ) ) ) ) ) ) )* ) )
            {
            // InternalConceptLanguage.g:2829:4: ( ( ( ({...}? => ( ({...}? => (otherlv_4= 'description' ( (lv_description_5_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_6= 'verification' ( (lv_verification_7_0= ruleVerificationTypeSpecification ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_8= 'default' ( (lv_defaultValue_9_0= ruleBooleanLiteralString ) ) ) ) ) ) )* ) )
            // InternalConceptLanguage.g:2830:5: ( ( ({...}? => ( ({...}? => (otherlv_4= 'description' ( (lv_description_5_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_6= 'verification' ( (lv_verification_7_0= ruleVerificationTypeSpecification ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_8= 'default' ( (lv_defaultValue_9_0= ruleBooleanLiteralString ) ) ) ) ) ) )* )
            {
             
            				  getUnorderedGroupHelper().enter(grammarAccess.getBooleanPropertyAccess().getUnorderedGroup_3());
            				
            // InternalConceptLanguage.g:2833:5: ( ( ({...}? => ( ({...}? => (otherlv_4= 'description' ( (lv_description_5_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_6= 'verification' ( (lv_verification_7_0= ruleVerificationTypeSpecification ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_8= 'default' ( (lv_defaultValue_9_0= ruleBooleanLiteralString ) ) ) ) ) ) )* )
            // InternalConceptLanguage.g:2834:6: ( ({...}? => ( ({...}? => (otherlv_4= 'description' ( (lv_description_5_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_6= 'verification' ( (lv_verification_7_0= ruleVerificationTypeSpecification ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_8= 'default' ( (lv_defaultValue_9_0= ruleBooleanLiteralString ) ) ) ) ) ) )*
            {
            // InternalConceptLanguage.g:2834:6: ( ({...}? => ( ({...}? => (otherlv_4= 'description' ( (lv_description_5_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_6= 'verification' ( (lv_verification_7_0= ruleVerificationTypeSpecification ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_8= 'default' ( (lv_defaultValue_9_0= ruleBooleanLiteralString ) ) ) ) ) ) )*
            loop37:
            do {
                int alt37=4;
                int LA37_0 = input.LA(1);

                if ( LA37_0 == 15 && getUnorderedGroupHelper().canSelect(grammarAccess.getBooleanPropertyAccess().getUnorderedGroup_3(), 0) ) {
                    alt37=1;
                }
                else if ( LA37_0 == 44 && getUnorderedGroupHelper().canSelect(grammarAccess.getBooleanPropertyAccess().getUnorderedGroup_3(), 1) ) {
                    alt37=2;
                }
                else if ( LA37_0 == 47 && getUnorderedGroupHelper().canSelect(grammarAccess.getBooleanPropertyAccess().getUnorderedGroup_3(), 2) ) {
                    alt37=3;
                }


                switch (alt37) {
            	case 1 :
            	    // InternalConceptLanguage.g:2835:4: ({...}? => ( ({...}? => (otherlv_4= 'description' ( (lv_description_5_0= ruleEString ) ) ) ) ) )
            	    {
            	    // InternalConceptLanguage.g:2835:4: ({...}? => ( ({...}? => (otherlv_4= 'description' ( (lv_description_5_0= ruleEString ) ) ) ) ) )
            	    // InternalConceptLanguage.g:2836:5: {...}? => ( ({...}? => (otherlv_4= 'description' ( (lv_description_5_0= ruleEString ) ) ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getBooleanPropertyAccess().getUnorderedGroup_3(), 0) ) {
            	        throw new FailedPredicateException(input, "ruleBooleanProperty", "getUnorderedGroupHelper().canSelect(grammarAccess.getBooleanPropertyAccess().getUnorderedGroup_3(), 0)");
            	    }
            	    // InternalConceptLanguage.g:2836:112: ( ({...}? => (otherlv_4= 'description' ( (lv_description_5_0= ruleEString ) ) ) ) )
            	    // InternalConceptLanguage.g:2837:6: ({...}? => (otherlv_4= 'description' ( (lv_description_5_0= ruleEString ) ) ) )
            	    {

            	    						getUnorderedGroupHelper().select(grammarAccess.getBooleanPropertyAccess().getUnorderedGroup_3(), 0);
            	    					
            	    // InternalConceptLanguage.g:2840:9: ({...}? => (otherlv_4= 'description' ( (lv_description_5_0= ruleEString ) ) ) )
            	    // InternalConceptLanguage.g:2840:10: {...}? => (otherlv_4= 'description' ( (lv_description_5_0= ruleEString ) ) )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleBooleanProperty", "true");
            	    }
            	    // InternalConceptLanguage.g:2840:19: (otherlv_4= 'description' ( (lv_description_5_0= ruleEString ) ) )
            	    // InternalConceptLanguage.g:2840:20: otherlv_4= 'description' ( (lv_description_5_0= ruleEString ) )
            	    {
            	    otherlv_4=(Token)match(input,15,FOLLOW_5); 

            	    									newLeafNode(otherlv_4, grammarAccess.getBooleanPropertyAccess().getDescriptionKeyword_3_0_0());
            	    								
            	    // InternalConceptLanguage.g:2844:9: ( (lv_description_5_0= ruleEString ) )
            	    // InternalConceptLanguage.g:2845:10: (lv_description_5_0= ruleEString )
            	    {
            	    // InternalConceptLanguage.g:2845:10: (lv_description_5_0= ruleEString )
            	    // InternalConceptLanguage.g:2846:11: lv_description_5_0= ruleEString
            	    {

            	    											newCompositeNode(grammarAccess.getBooleanPropertyAccess().getDescriptionEStringParserRuleCall_3_0_1_0());
            	    										
            	    pushFollow(FOLLOW_40);
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
            	    // InternalConceptLanguage.g:2869:4: ({...}? => ( ({...}? => (otherlv_6= 'verification' ( (lv_verification_7_0= ruleVerificationTypeSpecification ) ) ) ) ) )
            	    {
            	    // InternalConceptLanguage.g:2869:4: ({...}? => ( ({...}? => (otherlv_6= 'verification' ( (lv_verification_7_0= ruleVerificationTypeSpecification ) ) ) ) ) )
            	    // InternalConceptLanguage.g:2870:5: {...}? => ( ({...}? => (otherlv_6= 'verification' ( (lv_verification_7_0= ruleVerificationTypeSpecification ) ) ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getBooleanPropertyAccess().getUnorderedGroup_3(), 1) ) {
            	        throw new FailedPredicateException(input, "ruleBooleanProperty", "getUnorderedGroupHelper().canSelect(grammarAccess.getBooleanPropertyAccess().getUnorderedGroup_3(), 1)");
            	    }
            	    // InternalConceptLanguage.g:2870:112: ( ({...}? => (otherlv_6= 'verification' ( (lv_verification_7_0= ruleVerificationTypeSpecification ) ) ) ) )
            	    // InternalConceptLanguage.g:2871:6: ({...}? => (otherlv_6= 'verification' ( (lv_verification_7_0= ruleVerificationTypeSpecification ) ) ) )
            	    {

            	    						getUnorderedGroupHelper().select(grammarAccess.getBooleanPropertyAccess().getUnorderedGroup_3(), 1);
            	    					
            	    // InternalConceptLanguage.g:2874:9: ({...}? => (otherlv_6= 'verification' ( (lv_verification_7_0= ruleVerificationTypeSpecification ) ) ) )
            	    // InternalConceptLanguage.g:2874:10: {...}? => (otherlv_6= 'verification' ( (lv_verification_7_0= ruleVerificationTypeSpecification ) ) )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleBooleanProperty", "true");
            	    }
            	    // InternalConceptLanguage.g:2874:19: (otherlv_6= 'verification' ( (lv_verification_7_0= ruleVerificationTypeSpecification ) ) )
            	    // InternalConceptLanguage.g:2874:20: otherlv_6= 'verification' ( (lv_verification_7_0= ruleVerificationTypeSpecification ) )
            	    {
            	    otherlv_6=(Token)match(input,44,FOLLOW_3); 

            	    									newLeafNode(otherlv_6, grammarAccess.getBooleanPropertyAccess().getVerificationKeyword_3_1_0());
            	    								
            	    // InternalConceptLanguage.g:2878:9: ( (lv_verification_7_0= ruleVerificationTypeSpecification ) )
            	    // InternalConceptLanguage.g:2879:10: (lv_verification_7_0= ruleVerificationTypeSpecification )
            	    {
            	    // InternalConceptLanguage.g:2879:10: (lv_verification_7_0= ruleVerificationTypeSpecification )
            	    // InternalConceptLanguage.g:2880:11: lv_verification_7_0= ruleVerificationTypeSpecification
            	    {

            	    											newCompositeNode(grammarAccess.getBooleanPropertyAccess().getVerificationVerificationTypeSpecificationParserRuleCall_3_1_1_0());
            	    										
            	    pushFollow(FOLLOW_40);
            	    lv_verification_7_0=ruleVerificationTypeSpecification();

            	    state._fsp--;


            	    											if (current==null) {
            	    												current = createModelElementForParent(grammarAccess.getBooleanPropertyRule());
            	    											}
            	    											set(
            	    												current,
            	    												"verification",
            	    												lv_verification_7_0,
            	    												"de.dlr.sc.virsat.model.concept.ConceptLanguage.VerificationTypeSpecification");
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
            	case 3 :
            	    // InternalConceptLanguage.g:2903:4: ({...}? => ( ({...}? => (otherlv_8= 'default' ( (lv_defaultValue_9_0= ruleBooleanLiteralString ) ) ) ) ) )
            	    {
            	    // InternalConceptLanguage.g:2903:4: ({...}? => ( ({...}? => (otherlv_8= 'default' ( (lv_defaultValue_9_0= ruleBooleanLiteralString ) ) ) ) ) )
            	    // InternalConceptLanguage.g:2904:5: {...}? => ( ({...}? => (otherlv_8= 'default' ( (lv_defaultValue_9_0= ruleBooleanLiteralString ) ) ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getBooleanPropertyAccess().getUnorderedGroup_3(), 2) ) {
            	        throw new FailedPredicateException(input, "ruleBooleanProperty", "getUnorderedGroupHelper().canSelect(grammarAccess.getBooleanPropertyAccess().getUnorderedGroup_3(), 2)");
            	    }
            	    // InternalConceptLanguage.g:2904:112: ( ({...}? => (otherlv_8= 'default' ( (lv_defaultValue_9_0= ruleBooleanLiteralString ) ) ) ) )
            	    // InternalConceptLanguage.g:2905:6: ({...}? => (otherlv_8= 'default' ( (lv_defaultValue_9_0= ruleBooleanLiteralString ) ) ) )
            	    {

            	    						getUnorderedGroupHelper().select(grammarAccess.getBooleanPropertyAccess().getUnorderedGroup_3(), 2);
            	    					
            	    // InternalConceptLanguage.g:2908:9: ({...}? => (otherlv_8= 'default' ( (lv_defaultValue_9_0= ruleBooleanLiteralString ) ) ) )
            	    // InternalConceptLanguage.g:2908:10: {...}? => (otherlv_8= 'default' ( (lv_defaultValue_9_0= ruleBooleanLiteralString ) ) )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleBooleanProperty", "true");
            	    }
            	    // InternalConceptLanguage.g:2908:19: (otherlv_8= 'default' ( (lv_defaultValue_9_0= ruleBooleanLiteralString ) ) )
            	    // InternalConceptLanguage.g:2908:20: otherlv_8= 'default' ( (lv_defaultValue_9_0= ruleBooleanLiteralString ) )
            	    {
            	    otherlv_8=(Token)match(input,47,FOLLOW_41); 

            	    									newLeafNode(otherlv_8, grammarAccess.getBooleanPropertyAccess().getDefaultKeyword_3_2_0());
            	    								
            	    // InternalConceptLanguage.g:2912:9: ( (lv_defaultValue_9_0= ruleBooleanLiteralString ) )
            	    // InternalConceptLanguage.g:2913:10: (lv_defaultValue_9_0= ruleBooleanLiteralString )
            	    {
            	    // InternalConceptLanguage.g:2913:10: (lv_defaultValue_9_0= ruleBooleanLiteralString )
            	    // InternalConceptLanguage.g:2914:11: lv_defaultValue_9_0= ruleBooleanLiteralString
            	    {

            	    											newCompositeNode(grammarAccess.getBooleanPropertyAccess().getDefaultValueBooleanLiteralStringParserRuleCall_3_2_1_0());
            	    										
            	    pushFollow(FOLLOW_40);
            	    lv_defaultValue_9_0=ruleBooleanLiteralString();

            	    state._fsp--;


            	    											if (current==null) {
            	    												current = createModelElementForParent(grammarAccess.getBooleanPropertyRule());
            	    											}
            	    											set(
            	    												current,
            	    												"defaultValue",
            	    												lv_defaultValue_9_0,
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
            	    break loop37;
                }
            } while (true);


            }


            }

             
            				  getUnorderedGroupHelper().leave(grammarAccess.getBooleanPropertyAccess().getUnorderedGroup_3());
            				

            }

            otherlv_10=(Token)match(input,22,FOLLOW_2); 

            			newLeafNode(otherlv_10, grammarAccess.getBooleanPropertyAccess().getSemicolonKeyword_4());
            		

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
    // InternalConceptLanguage.g:2952:1: entryRuleEnumProperty returns [EObject current=null] : iv_ruleEnumProperty= ruleEnumProperty EOF ;
    public final EObject entryRuleEnumProperty() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleEnumProperty = null;


        try {
            // InternalConceptLanguage.g:2952:53: (iv_ruleEnumProperty= ruleEnumProperty EOF )
            // InternalConceptLanguage.g:2953:2: iv_ruleEnumProperty= ruleEnumProperty EOF
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
    // InternalConceptLanguage.g:2959:1: ruleEnumProperty returns [EObject current=null] : (otherlv_0= 'EnumProperty' ( (lv_name_1_0= RULE_ID ) ) ( (lv_arrayModifier_2_0= ruleArrayModifier ) )? ( ( ( ( ({...}? => ( ({...}? => (otherlv_4= 'description' ( (lv_description_5_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_6= 'quantityKind' ( (lv_quantityKindName_7_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_8= 'unit' ( (lv_unitName_9_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_10= 'values' otherlv_11= '[' ( (lv_values_12_0= ruleEnumValueDefinition ) ) (otherlv_13= ',' ( (lv_values_14_0= ruleEnumValueDefinition ) ) )* otherlv_15= ']' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_16= 'verification' ( (lv_verification_17_0= ruleVerificationTypeSpecification ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_18= 'default' ( (otherlv_19= RULE_ID ) ) ) ) ) ) )+ {...}?) ) ) otherlv_20= ';' ) ;
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
        Token otherlv_18=null;
        Token otherlv_19=null;
        Token otherlv_20=null;
        EObject lv_arrayModifier_2_0 = null;

        AntlrDatatypeRuleToken lv_description_5_0 = null;

        AntlrDatatypeRuleToken lv_quantityKindName_7_0 = null;

        AntlrDatatypeRuleToken lv_unitName_9_0 = null;

        EObject lv_values_12_0 = null;

        EObject lv_values_14_0 = null;

        EObject lv_verification_17_0 = null;



        	enterRule();

        try {
            // InternalConceptLanguage.g:2965:2: ( (otherlv_0= 'EnumProperty' ( (lv_name_1_0= RULE_ID ) ) ( (lv_arrayModifier_2_0= ruleArrayModifier ) )? ( ( ( ( ({...}? => ( ({...}? => (otherlv_4= 'description' ( (lv_description_5_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_6= 'quantityKind' ( (lv_quantityKindName_7_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_8= 'unit' ( (lv_unitName_9_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_10= 'values' otherlv_11= '[' ( (lv_values_12_0= ruleEnumValueDefinition ) ) (otherlv_13= ',' ( (lv_values_14_0= ruleEnumValueDefinition ) ) )* otherlv_15= ']' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_16= 'verification' ( (lv_verification_17_0= ruleVerificationTypeSpecification ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_18= 'default' ( (otherlv_19= RULE_ID ) ) ) ) ) ) )+ {...}?) ) ) otherlv_20= ';' ) )
            // InternalConceptLanguage.g:2966:2: (otherlv_0= 'EnumProperty' ( (lv_name_1_0= RULE_ID ) ) ( (lv_arrayModifier_2_0= ruleArrayModifier ) )? ( ( ( ( ({...}? => ( ({...}? => (otherlv_4= 'description' ( (lv_description_5_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_6= 'quantityKind' ( (lv_quantityKindName_7_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_8= 'unit' ( (lv_unitName_9_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_10= 'values' otherlv_11= '[' ( (lv_values_12_0= ruleEnumValueDefinition ) ) (otherlv_13= ',' ( (lv_values_14_0= ruleEnumValueDefinition ) ) )* otherlv_15= ']' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_16= 'verification' ( (lv_verification_17_0= ruleVerificationTypeSpecification ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_18= 'default' ( (otherlv_19= RULE_ID ) ) ) ) ) ) )+ {...}?) ) ) otherlv_20= ';' )
            {
            // InternalConceptLanguage.g:2966:2: (otherlv_0= 'EnumProperty' ( (lv_name_1_0= RULE_ID ) ) ( (lv_arrayModifier_2_0= ruleArrayModifier ) )? ( ( ( ( ({...}? => ( ({...}? => (otherlv_4= 'description' ( (lv_description_5_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_6= 'quantityKind' ( (lv_quantityKindName_7_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_8= 'unit' ( (lv_unitName_9_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_10= 'values' otherlv_11= '[' ( (lv_values_12_0= ruleEnumValueDefinition ) ) (otherlv_13= ',' ( (lv_values_14_0= ruleEnumValueDefinition ) ) )* otherlv_15= ']' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_16= 'verification' ( (lv_verification_17_0= ruleVerificationTypeSpecification ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_18= 'default' ( (otherlv_19= RULE_ID ) ) ) ) ) ) )+ {...}?) ) ) otherlv_20= ';' )
            // InternalConceptLanguage.g:2967:3: otherlv_0= 'EnumProperty' ( (lv_name_1_0= RULE_ID ) ) ( (lv_arrayModifier_2_0= ruleArrayModifier ) )? ( ( ( ( ({...}? => ( ({...}? => (otherlv_4= 'description' ( (lv_description_5_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_6= 'quantityKind' ( (lv_quantityKindName_7_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_8= 'unit' ( (lv_unitName_9_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_10= 'values' otherlv_11= '[' ( (lv_values_12_0= ruleEnumValueDefinition ) ) (otherlv_13= ',' ( (lv_values_14_0= ruleEnumValueDefinition ) ) )* otherlv_15= ']' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_16= 'verification' ( (lv_verification_17_0= ruleVerificationTypeSpecification ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_18= 'default' ( (otherlv_19= RULE_ID ) ) ) ) ) ) )+ {...}?) ) ) otherlv_20= ';'
            {
            otherlv_0=(Token)match(input,51,FOLLOW_3); 

            			newLeafNode(otherlv_0, grammarAccess.getEnumPropertyAccess().getEnumPropertyKeyword_0());
            		
            // InternalConceptLanguage.g:2971:3: ( (lv_name_1_0= RULE_ID ) )
            // InternalConceptLanguage.g:2972:4: (lv_name_1_0= RULE_ID )
            {
            // InternalConceptLanguage.g:2972:4: (lv_name_1_0= RULE_ID )
            // InternalConceptLanguage.g:2973:5: lv_name_1_0= RULE_ID
            {
            lv_name_1_0=(Token)match(input,RULE_ID,FOLLOW_42); 

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

            // InternalConceptLanguage.g:2989:3: ( (lv_arrayModifier_2_0= ruleArrayModifier ) )?
            int alt38=2;
            int LA38_0 = input.LA(1);

            if ( (LA38_0==25) ) {
                alt38=1;
            }
            switch (alt38) {
                case 1 :
                    // InternalConceptLanguage.g:2990:4: (lv_arrayModifier_2_0= ruleArrayModifier )
                    {
                    // InternalConceptLanguage.g:2990:4: (lv_arrayModifier_2_0= ruleArrayModifier )
                    // InternalConceptLanguage.g:2991:5: lv_arrayModifier_2_0= ruleArrayModifier
                    {

                    					newCompositeNode(grammarAccess.getEnumPropertyAccess().getArrayModifierArrayModifierParserRuleCall_2_0());
                    				
                    pushFollow(FOLLOW_43);
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

            // InternalConceptLanguage.g:3008:3: ( ( ( ( ({...}? => ( ({...}? => (otherlv_4= 'description' ( (lv_description_5_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_6= 'quantityKind' ( (lv_quantityKindName_7_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_8= 'unit' ( (lv_unitName_9_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_10= 'values' otherlv_11= '[' ( (lv_values_12_0= ruleEnumValueDefinition ) ) (otherlv_13= ',' ( (lv_values_14_0= ruleEnumValueDefinition ) ) )* otherlv_15= ']' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_16= 'verification' ( (lv_verification_17_0= ruleVerificationTypeSpecification ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_18= 'default' ( (otherlv_19= RULE_ID ) ) ) ) ) ) )+ {...}?) ) )
            // InternalConceptLanguage.g:3009:4: ( ( ( ({...}? => ( ({...}? => (otherlv_4= 'description' ( (lv_description_5_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_6= 'quantityKind' ( (lv_quantityKindName_7_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_8= 'unit' ( (lv_unitName_9_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_10= 'values' otherlv_11= '[' ( (lv_values_12_0= ruleEnumValueDefinition ) ) (otherlv_13= ',' ( (lv_values_14_0= ruleEnumValueDefinition ) ) )* otherlv_15= ']' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_16= 'verification' ( (lv_verification_17_0= ruleVerificationTypeSpecification ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_18= 'default' ( (otherlv_19= RULE_ID ) ) ) ) ) ) )+ {...}?) )
            {
            // InternalConceptLanguage.g:3009:4: ( ( ( ({...}? => ( ({...}? => (otherlv_4= 'description' ( (lv_description_5_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_6= 'quantityKind' ( (lv_quantityKindName_7_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_8= 'unit' ( (lv_unitName_9_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_10= 'values' otherlv_11= '[' ( (lv_values_12_0= ruleEnumValueDefinition ) ) (otherlv_13= ',' ( (lv_values_14_0= ruleEnumValueDefinition ) ) )* otherlv_15= ']' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_16= 'verification' ( (lv_verification_17_0= ruleVerificationTypeSpecification ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_18= 'default' ( (otherlv_19= RULE_ID ) ) ) ) ) ) )+ {...}?) )
            // InternalConceptLanguage.g:3010:5: ( ( ({...}? => ( ({...}? => (otherlv_4= 'description' ( (lv_description_5_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_6= 'quantityKind' ( (lv_quantityKindName_7_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_8= 'unit' ( (lv_unitName_9_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_10= 'values' otherlv_11= '[' ( (lv_values_12_0= ruleEnumValueDefinition ) ) (otherlv_13= ',' ( (lv_values_14_0= ruleEnumValueDefinition ) ) )* otherlv_15= ']' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_16= 'verification' ( (lv_verification_17_0= ruleVerificationTypeSpecification ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_18= 'default' ( (otherlv_19= RULE_ID ) ) ) ) ) ) )+ {...}?)
            {
             
            				  getUnorderedGroupHelper().enter(grammarAccess.getEnumPropertyAccess().getUnorderedGroup_3());
            				
            // InternalConceptLanguage.g:3013:5: ( ( ({...}? => ( ({...}? => (otherlv_4= 'description' ( (lv_description_5_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_6= 'quantityKind' ( (lv_quantityKindName_7_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_8= 'unit' ( (lv_unitName_9_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_10= 'values' otherlv_11= '[' ( (lv_values_12_0= ruleEnumValueDefinition ) ) (otherlv_13= ',' ( (lv_values_14_0= ruleEnumValueDefinition ) ) )* otherlv_15= ']' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_16= 'verification' ( (lv_verification_17_0= ruleVerificationTypeSpecification ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_18= 'default' ( (otherlv_19= RULE_ID ) ) ) ) ) ) )+ {...}?)
            // InternalConceptLanguage.g:3014:6: ( ({...}? => ( ({...}? => (otherlv_4= 'description' ( (lv_description_5_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_6= 'quantityKind' ( (lv_quantityKindName_7_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_8= 'unit' ( (lv_unitName_9_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_10= 'values' otherlv_11= '[' ( (lv_values_12_0= ruleEnumValueDefinition ) ) (otherlv_13= ',' ( (lv_values_14_0= ruleEnumValueDefinition ) ) )* otherlv_15= ']' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_16= 'verification' ( (lv_verification_17_0= ruleVerificationTypeSpecification ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_18= 'default' ( (otherlv_19= RULE_ID ) ) ) ) ) ) )+ {...}?
            {
            // InternalConceptLanguage.g:3014:6: ( ({...}? => ( ({...}? => (otherlv_4= 'description' ( (lv_description_5_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_6= 'quantityKind' ( (lv_quantityKindName_7_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_8= 'unit' ( (lv_unitName_9_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_10= 'values' otherlv_11= '[' ( (lv_values_12_0= ruleEnumValueDefinition ) ) (otherlv_13= ',' ( (lv_values_14_0= ruleEnumValueDefinition ) ) )* otherlv_15= ']' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_16= 'verification' ( (lv_verification_17_0= ruleVerificationTypeSpecification ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_18= 'default' ( (otherlv_19= RULE_ID ) ) ) ) ) ) )+
            int cnt40=0;
            loop40:
            do {
                int alt40=7;
                int LA40_0 = input.LA(1);

                if ( LA40_0 == 15 && getUnorderedGroupHelper().canSelect(grammarAccess.getEnumPropertyAccess().getUnorderedGroup_3(), 0) ) {
                    alt40=1;
                }
                else if ( LA40_0 == 43 && getUnorderedGroupHelper().canSelect(grammarAccess.getEnumPropertyAccess().getUnorderedGroup_3(), 1) ) {
                    alt40=2;
                }
                else if ( LA40_0 == 45 && getUnorderedGroupHelper().canSelect(grammarAccess.getEnumPropertyAccess().getUnorderedGroup_3(), 2) ) {
                    alt40=3;
                }
                else if ( LA40_0 == 52 && getUnorderedGroupHelper().canSelect(grammarAccess.getEnumPropertyAccess().getUnorderedGroup_3(), 3) ) {
                    alt40=4;
                }
                else if ( LA40_0 == 44 && getUnorderedGroupHelper().canSelect(grammarAccess.getEnumPropertyAccess().getUnorderedGroup_3(), 4) ) {
                    alt40=5;
                }
                else if ( LA40_0 == 47 && getUnorderedGroupHelper().canSelect(grammarAccess.getEnumPropertyAccess().getUnorderedGroup_3(), 5) ) {
                    alt40=6;
                }


                switch (alt40) {
            	case 1 :
            	    // InternalConceptLanguage.g:3015:4: ({...}? => ( ({...}? => (otherlv_4= 'description' ( (lv_description_5_0= ruleEString ) ) ) ) ) )
            	    {
            	    // InternalConceptLanguage.g:3015:4: ({...}? => ( ({...}? => (otherlv_4= 'description' ( (lv_description_5_0= ruleEString ) ) ) ) ) )
            	    // InternalConceptLanguage.g:3016:5: {...}? => ( ({...}? => (otherlv_4= 'description' ( (lv_description_5_0= ruleEString ) ) ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getEnumPropertyAccess().getUnorderedGroup_3(), 0) ) {
            	        throw new FailedPredicateException(input, "ruleEnumProperty", "getUnorderedGroupHelper().canSelect(grammarAccess.getEnumPropertyAccess().getUnorderedGroup_3(), 0)");
            	    }
            	    // InternalConceptLanguage.g:3016:109: ( ({...}? => (otherlv_4= 'description' ( (lv_description_5_0= ruleEString ) ) ) ) )
            	    // InternalConceptLanguage.g:3017:6: ({...}? => (otherlv_4= 'description' ( (lv_description_5_0= ruleEString ) ) ) )
            	    {

            	    						getUnorderedGroupHelper().select(grammarAccess.getEnumPropertyAccess().getUnorderedGroup_3(), 0);
            	    					
            	    // InternalConceptLanguage.g:3020:9: ({...}? => (otherlv_4= 'description' ( (lv_description_5_0= ruleEString ) ) ) )
            	    // InternalConceptLanguage.g:3020:10: {...}? => (otherlv_4= 'description' ( (lv_description_5_0= ruleEString ) ) )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleEnumProperty", "true");
            	    }
            	    // InternalConceptLanguage.g:3020:19: (otherlv_4= 'description' ( (lv_description_5_0= ruleEString ) ) )
            	    // InternalConceptLanguage.g:3020:20: otherlv_4= 'description' ( (lv_description_5_0= ruleEString ) )
            	    {
            	    otherlv_4=(Token)match(input,15,FOLLOW_5); 

            	    									newLeafNode(otherlv_4, grammarAccess.getEnumPropertyAccess().getDescriptionKeyword_3_0_0());
            	    								
            	    // InternalConceptLanguage.g:3024:9: ( (lv_description_5_0= ruleEString ) )
            	    // InternalConceptLanguage.g:3025:10: (lv_description_5_0= ruleEString )
            	    {
            	    // InternalConceptLanguage.g:3025:10: (lv_description_5_0= ruleEString )
            	    // InternalConceptLanguage.g:3026:11: lv_description_5_0= ruleEString
            	    {

            	    											newCompositeNode(grammarAccess.getEnumPropertyAccess().getDescriptionEStringParserRuleCall_3_0_1_0());
            	    										
            	    pushFollow(FOLLOW_44);
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
            	    // InternalConceptLanguage.g:3049:4: ({...}? => ( ({...}? => (otherlv_6= 'quantityKind' ( (lv_quantityKindName_7_0= ruleEString ) ) ) ) ) )
            	    {
            	    // InternalConceptLanguage.g:3049:4: ({...}? => ( ({...}? => (otherlv_6= 'quantityKind' ( (lv_quantityKindName_7_0= ruleEString ) ) ) ) ) )
            	    // InternalConceptLanguage.g:3050:5: {...}? => ( ({...}? => (otherlv_6= 'quantityKind' ( (lv_quantityKindName_7_0= ruleEString ) ) ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getEnumPropertyAccess().getUnorderedGroup_3(), 1) ) {
            	        throw new FailedPredicateException(input, "ruleEnumProperty", "getUnorderedGroupHelper().canSelect(grammarAccess.getEnumPropertyAccess().getUnorderedGroup_3(), 1)");
            	    }
            	    // InternalConceptLanguage.g:3050:109: ( ({...}? => (otherlv_6= 'quantityKind' ( (lv_quantityKindName_7_0= ruleEString ) ) ) ) )
            	    // InternalConceptLanguage.g:3051:6: ({...}? => (otherlv_6= 'quantityKind' ( (lv_quantityKindName_7_0= ruleEString ) ) ) )
            	    {

            	    						getUnorderedGroupHelper().select(grammarAccess.getEnumPropertyAccess().getUnorderedGroup_3(), 1);
            	    					
            	    // InternalConceptLanguage.g:3054:9: ({...}? => (otherlv_6= 'quantityKind' ( (lv_quantityKindName_7_0= ruleEString ) ) ) )
            	    // InternalConceptLanguage.g:3054:10: {...}? => (otherlv_6= 'quantityKind' ( (lv_quantityKindName_7_0= ruleEString ) ) )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleEnumProperty", "true");
            	    }
            	    // InternalConceptLanguage.g:3054:19: (otherlv_6= 'quantityKind' ( (lv_quantityKindName_7_0= ruleEString ) ) )
            	    // InternalConceptLanguage.g:3054:20: otherlv_6= 'quantityKind' ( (lv_quantityKindName_7_0= ruleEString ) )
            	    {
            	    otherlv_6=(Token)match(input,43,FOLLOW_5); 

            	    									newLeafNode(otherlv_6, grammarAccess.getEnumPropertyAccess().getQuantityKindKeyword_3_1_0());
            	    								
            	    // InternalConceptLanguage.g:3058:9: ( (lv_quantityKindName_7_0= ruleEString ) )
            	    // InternalConceptLanguage.g:3059:10: (lv_quantityKindName_7_0= ruleEString )
            	    {
            	    // InternalConceptLanguage.g:3059:10: (lv_quantityKindName_7_0= ruleEString )
            	    // InternalConceptLanguage.g:3060:11: lv_quantityKindName_7_0= ruleEString
            	    {

            	    											newCompositeNode(grammarAccess.getEnumPropertyAccess().getQuantityKindNameEStringParserRuleCall_3_1_1_0());
            	    										
            	    pushFollow(FOLLOW_44);
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
            	    // InternalConceptLanguage.g:3083:4: ({...}? => ( ({...}? => (otherlv_8= 'unit' ( (lv_unitName_9_0= ruleEString ) ) ) ) ) )
            	    {
            	    // InternalConceptLanguage.g:3083:4: ({...}? => ( ({...}? => (otherlv_8= 'unit' ( (lv_unitName_9_0= ruleEString ) ) ) ) ) )
            	    // InternalConceptLanguage.g:3084:5: {...}? => ( ({...}? => (otherlv_8= 'unit' ( (lv_unitName_9_0= ruleEString ) ) ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getEnumPropertyAccess().getUnorderedGroup_3(), 2) ) {
            	        throw new FailedPredicateException(input, "ruleEnumProperty", "getUnorderedGroupHelper().canSelect(grammarAccess.getEnumPropertyAccess().getUnorderedGroup_3(), 2)");
            	    }
            	    // InternalConceptLanguage.g:3084:109: ( ({...}? => (otherlv_8= 'unit' ( (lv_unitName_9_0= ruleEString ) ) ) ) )
            	    // InternalConceptLanguage.g:3085:6: ({...}? => (otherlv_8= 'unit' ( (lv_unitName_9_0= ruleEString ) ) ) )
            	    {

            	    						getUnorderedGroupHelper().select(grammarAccess.getEnumPropertyAccess().getUnorderedGroup_3(), 2);
            	    					
            	    // InternalConceptLanguage.g:3088:9: ({...}? => (otherlv_8= 'unit' ( (lv_unitName_9_0= ruleEString ) ) ) )
            	    // InternalConceptLanguage.g:3088:10: {...}? => (otherlv_8= 'unit' ( (lv_unitName_9_0= ruleEString ) ) )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleEnumProperty", "true");
            	    }
            	    // InternalConceptLanguage.g:3088:19: (otherlv_8= 'unit' ( (lv_unitName_9_0= ruleEString ) ) )
            	    // InternalConceptLanguage.g:3088:20: otherlv_8= 'unit' ( (lv_unitName_9_0= ruleEString ) )
            	    {
            	    otherlv_8=(Token)match(input,45,FOLLOW_5); 

            	    									newLeafNode(otherlv_8, grammarAccess.getEnumPropertyAccess().getUnitKeyword_3_2_0());
            	    								
            	    // InternalConceptLanguage.g:3092:9: ( (lv_unitName_9_0= ruleEString ) )
            	    // InternalConceptLanguage.g:3093:10: (lv_unitName_9_0= ruleEString )
            	    {
            	    // InternalConceptLanguage.g:3093:10: (lv_unitName_9_0= ruleEString )
            	    // InternalConceptLanguage.g:3094:11: lv_unitName_9_0= ruleEString
            	    {

            	    											newCompositeNode(grammarAccess.getEnumPropertyAccess().getUnitNameEStringParserRuleCall_3_2_1_0());
            	    										
            	    pushFollow(FOLLOW_44);
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
            	    // InternalConceptLanguage.g:3117:4: ({...}? => ( ({...}? => (otherlv_10= 'values' otherlv_11= '[' ( (lv_values_12_0= ruleEnumValueDefinition ) ) (otherlv_13= ',' ( (lv_values_14_0= ruleEnumValueDefinition ) ) )* otherlv_15= ']' ) ) ) )
            	    {
            	    // InternalConceptLanguage.g:3117:4: ({...}? => ( ({...}? => (otherlv_10= 'values' otherlv_11= '[' ( (lv_values_12_0= ruleEnumValueDefinition ) ) (otherlv_13= ',' ( (lv_values_14_0= ruleEnumValueDefinition ) ) )* otherlv_15= ']' ) ) ) )
            	    // InternalConceptLanguage.g:3118:5: {...}? => ( ({...}? => (otherlv_10= 'values' otherlv_11= '[' ( (lv_values_12_0= ruleEnumValueDefinition ) ) (otherlv_13= ',' ( (lv_values_14_0= ruleEnumValueDefinition ) ) )* otherlv_15= ']' ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getEnumPropertyAccess().getUnorderedGroup_3(), 3) ) {
            	        throw new FailedPredicateException(input, "ruleEnumProperty", "getUnorderedGroupHelper().canSelect(grammarAccess.getEnumPropertyAccess().getUnorderedGroup_3(), 3)");
            	    }
            	    // InternalConceptLanguage.g:3118:109: ( ({...}? => (otherlv_10= 'values' otherlv_11= '[' ( (lv_values_12_0= ruleEnumValueDefinition ) ) (otherlv_13= ',' ( (lv_values_14_0= ruleEnumValueDefinition ) ) )* otherlv_15= ']' ) ) )
            	    // InternalConceptLanguage.g:3119:6: ({...}? => (otherlv_10= 'values' otherlv_11= '[' ( (lv_values_12_0= ruleEnumValueDefinition ) ) (otherlv_13= ',' ( (lv_values_14_0= ruleEnumValueDefinition ) ) )* otherlv_15= ']' ) )
            	    {

            	    						getUnorderedGroupHelper().select(grammarAccess.getEnumPropertyAccess().getUnorderedGroup_3(), 3);
            	    					
            	    // InternalConceptLanguage.g:3122:9: ({...}? => (otherlv_10= 'values' otherlv_11= '[' ( (lv_values_12_0= ruleEnumValueDefinition ) ) (otherlv_13= ',' ( (lv_values_14_0= ruleEnumValueDefinition ) ) )* otherlv_15= ']' ) )
            	    // InternalConceptLanguage.g:3122:10: {...}? => (otherlv_10= 'values' otherlv_11= '[' ( (lv_values_12_0= ruleEnumValueDefinition ) ) (otherlv_13= ',' ( (lv_values_14_0= ruleEnumValueDefinition ) ) )* otherlv_15= ']' )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleEnumProperty", "true");
            	    }
            	    // InternalConceptLanguage.g:3122:19: (otherlv_10= 'values' otherlv_11= '[' ( (lv_values_12_0= ruleEnumValueDefinition ) ) (otherlv_13= ',' ( (lv_values_14_0= ruleEnumValueDefinition ) ) )* otherlv_15= ']' )
            	    // InternalConceptLanguage.g:3122:20: otherlv_10= 'values' otherlv_11= '[' ( (lv_values_12_0= ruleEnumValueDefinition ) ) (otherlv_13= ',' ( (lv_values_14_0= ruleEnumValueDefinition ) ) )* otherlv_15= ']'
            	    {
            	    otherlv_10=(Token)match(input,52,FOLLOW_45); 

            	    									newLeafNode(otherlv_10, grammarAccess.getEnumPropertyAccess().getValuesKeyword_3_3_0());
            	    								
            	    otherlv_11=(Token)match(input,25,FOLLOW_3); 

            	    									newLeafNode(otherlv_11, grammarAccess.getEnumPropertyAccess().getLeftSquareBracketKeyword_3_3_1());
            	    								
            	    // InternalConceptLanguage.g:3130:9: ( (lv_values_12_0= ruleEnumValueDefinition ) )
            	    // InternalConceptLanguage.g:3131:10: (lv_values_12_0= ruleEnumValueDefinition )
            	    {
            	    // InternalConceptLanguage.g:3131:10: (lv_values_12_0= ruleEnumValueDefinition )
            	    // InternalConceptLanguage.g:3132:11: lv_values_12_0= ruleEnumValueDefinition
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

            	    // InternalConceptLanguage.g:3149:9: (otherlv_13= ',' ( (lv_values_14_0= ruleEnumValueDefinition ) ) )*
            	    loop39:
            	    do {
            	        int alt39=2;
            	        int LA39_0 = input.LA(1);

            	        if ( (LA39_0==26) ) {
            	            alt39=1;
            	        }


            	        switch (alt39) {
            	    	case 1 :
            	    	    // InternalConceptLanguage.g:3150:10: otherlv_13= ',' ( (lv_values_14_0= ruleEnumValueDefinition ) )
            	    	    {
            	    	    otherlv_13=(Token)match(input,26,FOLLOW_3); 

            	    	    										newLeafNode(otherlv_13, grammarAccess.getEnumPropertyAccess().getCommaKeyword_3_3_3_0());
            	    	    									
            	    	    // InternalConceptLanguage.g:3154:10: ( (lv_values_14_0= ruleEnumValueDefinition ) )
            	    	    // InternalConceptLanguage.g:3155:11: (lv_values_14_0= ruleEnumValueDefinition )
            	    	    {
            	    	    // InternalConceptLanguage.g:3155:11: (lv_values_14_0= ruleEnumValueDefinition )
            	    	    // InternalConceptLanguage.g:3156:12: lv_values_14_0= ruleEnumValueDefinition
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
            	    	    break loop39;
            	        }
            	    } while (true);

            	    otherlv_15=(Token)match(input,27,FOLLOW_44); 

            	    									newLeafNode(otherlv_15, grammarAccess.getEnumPropertyAccess().getRightSquareBracketKeyword_3_3_4());
            	    								

            	    }


            	    }

            	     
            	    						getUnorderedGroupHelper().returnFromSelection(grammarAccess.getEnumPropertyAccess().getUnorderedGroup_3());
            	    					

            	    }


            	    }


            	    }
            	    break;
            	case 5 :
            	    // InternalConceptLanguage.g:3184:4: ({...}? => ( ({...}? => (otherlv_16= 'verification' ( (lv_verification_17_0= ruleVerificationTypeSpecification ) ) ) ) ) )
            	    {
            	    // InternalConceptLanguage.g:3184:4: ({...}? => ( ({...}? => (otherlv_16= 'verification' ( (lv_verification_17_0= ruleVerificationTypeSpecification ) ) ) ) ) )
            	    // InternalConceptLanguage.g:3185:5: {...}? => ( ({...}? => (otherlv_16= 'verification' ( (lv_verification_17_0= ruleVerificationTypeSpecification ) ) ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getEnumPropertyAccess().getUnorderedGroup_3(), 4) ) {
            	        throw new FailedPredicateException(input, "ruleEnumProperty", "getUnorderedGroupHelper().canSelect(grammarAccess.getEnumPropertyAccess().getUnorderedGroup_3(), 4)");
            	    }
            	    // InternalConceptLanguage.g:3185:109: ( ({...}? => (otherlv_16= 'verification' ( (lv_verification_17_0= ruleVerificationTypeSpecification ) ) ) ) )
            	    // InternalConceptLanguage.g:3186:6: ({...}? => (otherlv_16= 'verification' ( (lv_verification_17_0= ruleVerificationTypeSpecification ) ) ) )
            	    {

            	    						getUnorderedGroupHelper().select(grammarAccess.getEnumPropertyAccess().getUnorderedGroup_3(), 4);
            	    					
            	    // InternalConceptLanguage.g:3189:9: ({...}? => (otherlv_16= 'verification' ( (lv_verification_17_0= ruleVerificationTypeSpecification ) ) ) )
            	    // InternalConceptLanguage.g:3189:10: {...}? => (otherlv_16= 'verification' ( (lv_verification_17_0= ruleVerificationTypeSpecification ) ) )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleEnumProperty", "true");
            	    }
            	    // InternalConceptLanguage.g:3189:19: (otherlv_16= 'verification' ( (lv_verification_17_0= ruleVerificationTypeSpecification ) ) )
            	    // InternalConceptLanguage.g:3189:20: otherlv_16= 'verification' ( (lv_verification_17_0= ruleVerificationTypeSpecification ) )
            	    {
            	    otherlv_16=(Token)match(input,44,FOLLOW_3); 

            	    									newLeafNode(otherlv_16, grammarAccess.getEnumPropertyAccess().getVerificationKeyword_3_4_0());
            	    								
            	    // InternalConceptLanguage.g:3193:9: ( (lv_verification_17_0= ruleVerificationTypeSpecification ) )
            	    // InternalConceptLanguage.g:3194:10: (lv_verification_17_0= ruleVerificationTypeSpecification )
            	    {
            	    // InternalConceptLanguage.g:3194:10: (lv_verification_17_0= ruleVerificationTypeSpecification )
            	    // InternalConceptLanguage.g:3195:11: lv_verification_17_0= ruleVerificationTypeSpecification
            	    {

            	    											newCompositeNode(grammarAccess.getEnumPropertyAccess().getVerificationVerificationTypeSpecificationParserRuleCall_3_4_1_0());
            	    										
            	    pushFollow(FOLLOW_44);
            	    lv_verification_17_0=ruleVerificationTypeSpecification();

            	    state._fsp--;


            	    											if (current==null) {
            	    												current = createModelElementForParent(grammarAccess.getEnumPropertyRule());
            	    											}
            	    											set(
            	    												current,
            	    												"verification",
            	    												lv_verification_17_0,
            	    												"de.dlr.sc.virsat.model.concept.ConceptLanguage.VerificationTypeSpecification");
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
            	case 6 :
            	    // InternalConceptLanguage.g:3218:4: ({...}? => ( ({...}? => (otherlv_18= 'default' ( (otherlv_19= RULE_ID ) ) ) ) ) )
            	    {
            	    // InternalConceptLanguage.g:3218:4: ({...}? => ( ({...}? => (otherlv_18= 'default' ( (otherlv_19= RULE_ID ) ) ) ) ) )
            	    // InternalConceptLanguage.g:3219:5: {...}? => ( ({...}? => (otherlv_18= 'default' ( (otherlv_19= RULE_ID ) ) ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getEnumPropertyAccess().getUnorderedGroup_3(), 5) ) {
            	        throw new FailedPredicateException(input, "ruleEnumProperty", "getUnorderedGroupHelper().canSelect(grammarAccess.getEnumPropertyAccess().getUnorderedGroup_3(), 5)");
            	    }
            	    // InternalConceptLanguage.g:3219:109: ( ({...}? => (otherlv_18= 'default' ( (otherlv_19= RULE_ID ) ) ) ) )
            	    // InternalConceptLanguage.g:3220:6: ({...}? => (otherlv_18= 'default' ( (otherlv_19= RULE_ID ) ) ) )
            	    {

            	    						getUnorderedGroupHelper().select(grammarAccess.getEnumPropertyAccess().getUnorderedGroup_3(), 5);
            	    					
            	    // InternalConceptLanguage.g:3223:9: ({...}? => (otherlv_18= 'default' ( (otherlv_19= RULE_ID ) ) ) )
            	    // InternalConceptLanguage.g:3223:10: {...}? => (otherlv_18= 'default' ( (otherlv_19= RULE_ID ) ) )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleEnumProperty", "true");
            	    }
            	    // InternalConceptLanguage.g:3223:19: (otherlv_18= 'default' ( (otherlv_19= RULE_ID ) ) )
            	    // InternalConceptLanguage.g:3223:20: otherlv_18= 'default' ( (otherlv_19= RULE_ID ) )
            	    {
            	    otherlv_18=(Token)match(input,47,FOLLOW_3); 

            	    									newLeafNode(otherlv_18, grammarAccess.getEnumPropertyAccess().getDefaultKeyword_3_5_0());
            	    								
            	    // InternalConceptLanguage.g:3227:9: ( (otherlv_19= RULE_ID ) )
            	    // InternalConceptLanguage.g:3228:10: (otherlv_19= RULE_ID )
            	    {
            	    // InternalConceptLanguage.g:3228:10: (otherlv_19= RULE_ID )
            	    // InternalConceptLanguage.g:3229:11: otherlv_19= RULE_ID
            	    {

            	    											if (current==null) {
            	    												current = createModelElement(grammarAccess.getEnumPropertyRule());
            	    											}
            	    										
            	    otherlv_19=(Token)match(input,RULE_ID,FOLLOW_44); 

            	    											newLeafNode(otherlv_19, grammarAccess.getEnumPropertyAccess().getDefaultValueEnumValueDefinitionCrossReference_3_5_1_0());
            	    										

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
            	    if ( cnt40 >= 1 ) break loop40;
                        EarlyExitException eee =
                            new EarlyExitException(40, input);
                        throw eee;
                }
                cnt40++;
            } while (true);

            if ( ! getUnorderedGroupHelper().canLeave(grammarAccess.getEnumPropertyAccess().getUnorderedGroup_3()) ) {
                throw new FailedPredicateException(input, "ruleEnumProperty", "getUnorderedGroupHelper().canLeave(grammarAccess.getEnumPropertyAccess().getUnorderedGroup_3())");
            }

            }


            }

             
            				  getUnorderedGroupHelper().leave(grammarAccess.getEnumPropertyAccess().getUnorderedGroup_3());
            				

            }

            otherlv_20=(Token)match(input,22,FOLLOW_2); 

            			newLeafNode(otherlv_20, grammarAccess.getEnumPropertyAccess().getSemicolonKeyword_4());
            		

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
    // InternalConceptLanguage.g:3262:1: entryRuleEnumValueDefinition returns [EObject current=null] : iv_ruleEnumValueDefinition= ruleEnumValueDefinition EOF ;
    public final EObject entryRuleEnumValueDefinition() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleEnumValueDefinition = null;


        try {
            // InternalConceptLanguage.g:3262:60: (iv_ruleEnumValueDefinition= ruleEnumValueDefinition EOF )
            // InternalConceptLanguage.g:3263:2: iv_ruleEnumValueDefinition= ruleEnumValueDefinition EOF
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
    // InternalConceptLanguage.g:3269:1: ruleEnumValueDefinition returns [EObject current=null] : ( ( (lv_name_0_0= RULE_ID ) ) otherlv_1= '=' ( ( (lv_value_2_1= ruleEString | lv_value_2_2= ruleFloatLiteralString ) ) ) ) ;
    public final EObject ruleEnumValueDefinition() throws RecognitionException {
        EObject current = null;

        Token lv_name_0_0=null;
        Token otherlv_1=null;
        AntlrDatatypeRuleToken lv_value_2_1 = null;

        AntlrDatatypeRuleToken lv_value_2_2 = null;



        	enterRule();

        try {
            // InternalConceptLanguage.g:3275:2: ( ( ( (lv_name_0_0= RULE_ID ) ) otherlv_1= '=' ( ( (lv_value_2_1= ruleEString | lv_value_2_2= ruleFloatLiteralString ) ) ) ) )
            // InternalConceptLanguage.g:3276:2: ( ( (lv_name_0_0= RULE_ID ) ) otherlv_1= '=' ( ( (lv_value_2_1= ruleEString | lv_value_2_2= ruleFloatLiteralString ) ) ) )
            {
            // InternalConceptLanguage.g:3276:2: ( ( (lv_name_0_0= RULE_ID ) ) otherlv_1= '=' ( ( (lv_value_2_1= ruleEString | lv_value_2_2= ruleFloatLiteralString ) ) ) )
            // InternalConceptLanguage.g:3277:3: ( (lv_name_0_0= RULE_ID ) ) otherlv_1= '=' ( ( (lv_value_2_1= ruleEString | lv_value_2_2= ruleFloatLiteralString ) ) )
            {
            // InternalConceptLanguage.g:3277:3: ( (lv_name_0_0= RULE_ID ) )
            // InternalConceptLanguage.g:3278:4: (lv_name_0_0= RULE_ID )
            {
            // InternalConceptLanguage.g:3278:4: (lv_name_0_0= RULE_ID )
            // InternalConceptLanguage.g:3279:5: lv_name_0_0= RULE_ID
            {
            lv_name_0_0=(Token)match(input,RULE_ID,FOLLOW_46); 

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

            otherlv_1=(Token)match(input,53,FOLLOW_47); 

            			newLeafNode(otherlv_1, grammarAccess.getEnumValueDefinitionAccess().getEqualsSignKeyword_1());
            		
            // InternalConceptLanguage.g:3299:3: ( ( (lv_value_2_1= ruleEString | lv_value_2_2= ruleFloatLiteralString ) ) )
            // InternalConceptLanguage.g:3300:4: ( (lv_value_2_1= ruleEString | lv_value_2_2= ruleFloatLiteralString ) )
            {
            // InternalConceptLanguage.g:3300:4: ( (lv_value_2_1= ruleEString | lv_value_2_2= ruleFloatLiteralString ) )
            // InternalConceptLanguage.g:3301:5: (lv_value_2_1= ruleEString | lv_value_2_2= ruleFloatLiteralString )
            {
            // InternalConceptLanguage.g:3301:5: (lv_value_2_1= ruleEString | lv_value_2_2= ruleFloatLiteralString )
            int alt41=2;
            int LA41_0 = input.LA(1);

            if ( (LA41_0==RULE_STRING) ) {
                alt41=1;
            }
            else if ( (LA41_0==RULE_INT||LA41_0==64) ) {
                alt41=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 41, 0, input);

                throw nvae;
            }
            switch (alt41) {
                case 1 :
                    // InternalConceptLanguage.g:3302:6: lv_value_2_1= ruleEString
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
                    // InternalConceptLanguage.g:3318:6: lv_value_2_2= ruleFloatLiteralString
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
    // InternalConceptLanguage.g:3340:1: entryRuleReferenceProperty returns [EObject current=null] : iv_ruleReferenceProperty= ruleReferenceProperty EOF ;
    public final EObject entryRuleReferenceProperty() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleReferenceProperty = null;


        try {
            // InternalConceptLanguage.g:3340:58: (iv_ruleReferenceProperty= ruleReferenceProperty EOF )
            // InternalConceptLanguage.g:3341:2: iv_ruleReferenceProperty= ruleReferenceProperty EOF
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
    // InternalConceptLanguage.g:3347:1: ruleReferenceProperty returns [EObject current=null] : (otherlv_0= 'Reference' ( (lv_name_1_0= RULE_ID ) ) ( (lv_arrayModifier_2_0= ruleArrayModifier ) )? otherlv_3= 'of' otherlv_4= 'Type' ( ( ruleQualifiedName ) ) ( ( ( ( ({...}? => ( ({...}? => (otherlv_7= 'description' ( (lv_description_8_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_9= 'verification' ( (lv_verification_10_0= ruleVerificationTypeSpecification ) ) ) ) ) ) )* ) ) ) otherlv_11= ';' ) ;
    public final EObject ruleReferenceProperty() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token lv_name_1_0=null;
        Token otherlv_3=null;
        Token otherlv_4=null;
        Token otherlv_7=null;
        Token otherlv_9=null;
        Token otherlv_11=null;
        EObject lv_arrayModifier_2_0 = null;

        AntlrDatatypeRuleToken lv_description_8_0 = null;

        EObject lv_verification_10_0 = null;



        	enterRule();

        try {
            // InternalConceptLanguage.g:3353:2: ( (otherlv_0= 'Reference' ( (lv_name_1_0= RULE_ID ) ) ( (lv_arrayModifier_2_0= ruleArrayModifier ) )? otherlv_3= 'of' otherlv_4= 'Type' ( ( ruleQualifiedName ) ) ( ( ( ( ({...}? => ( ({...}? => (otherlv_7= 'description' ( (lv_description_8_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_9= 'verification' ( (lv_verification_10_0= ruleVerificationTypeSpecification ) ) ) ) ) ) )* ) ) ) otherlv_11= ';' ) )
            // InternalConceptLanguage.g:3354:2: (otherlv_0= 'Reference' ( (lv_name_1_0= RULE_ID ) ) ( (lv_arrayModifier_2_0= ruleArrayModifier ) )? otherlv_3= 'of' otherlv_4= 'Type' ( ( ruleQualifiedName ) ) ( ( ( ( ({...}? => ( ({...}? => (otherlv_7= 'description' ( (lv_description_8_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_9= 'verification' ( (lv_verification_10_0= ruleVerificationTypeSpecification ) ) ) ) ) ) )* ) ) ) otherlv_11= ';' )
            {
            // InternalConceptLanguage.g:3354:2: (otherlv_0= 'Reference' ( (lv_name_1_0= RULE_ID ) ) ( (lv_arrayModifier_2_0= ruleArrayModifier ) )? otherlv_3= 'of' otherlv_4= 'Type' ( ( ruleQualifiedName ) ) ( ( ( ( ({...}? => ( ({...}? => (otherlv_7= 'description' ( (lv_description_8_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_9= 'verification' ( (lv_verification_10_0= ruleVerificationTypeSpecification ) ) ) ) ) ) )* ) ) ) otherlv_11= ';' )
            // InternalConceptLanguage.g:3355:3: otherlv_0= 'Reference' ( (lv_name_1_0= RULE_ID ) ) ( (lv_arrayModifier_2_0= ruleArrayModifier ) )? otherlv_3= 'of' otherlv_4= 'Type' ( ( ruleQualifiedName ) ) ( ( ( ( ({...}? => ( ({...}? => (otherlv_7= 'description' ( (lv_description_8_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_9= 'verification' ( (lv_verification_10_0= ruleVerificationTypeSpecification ) ) ) ) ) ) )* ) ) ) otherlv_11= ';'
            {
            otherlv_0=(Token)match(input,54,FOLLOW_3); 

            			newLeafNode(otherlv_0, grammarAccess.getReferencePropertyAccess().getReferenceKeyword_0());
            		
            // InternalConceptLanguage.g:3359:3: ( (lv_name_1_0= RULE_ID ) )
            // InternalConceptLanguage.g:3360:4: (lv_name_1_0= RULE_ID )
            {
            // InternalConceptLanguage.g:3360:4: (lv_name_1_0= RULE_ID )
            // InternalConceptLanguage.g:3361:5: lv_name_1_0= RULE_ID
            {
            lv_name_1_0=(Token)match(input,RULE_ID,FOLLOW_31); 

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

            // InternalConceptLanguage.g:3377:3: ( (lv_arrayModifier_2_0= ruleArrayModifier ) )?
            int alt42=2;
            int LA42_0 = input.LA(1);

            if ( (LA42_0==25) ) {
                alt42=1;
            }
            switch (alt42) {
                case 1 :
                    // InternalConceptLanguage.g:3378:4: (lv_arrayModifier_2_0= ruleArrayModifier )
                    {
                    // InternalConceptLanguage.g:3378:4: (lv_arrayModifier_2_0= ruleArrayModifier )
                    // InternalConceptLanguage.g:3379:5: lv_arrayModifier_2_0= ruleArrayModifier
                    {

                    					newCompositeNode(grammarAccess.getReferencePropertyAccess().getArrayModifierArrayModifierParserRuleCall_2_0());
                    				
                    pushFollow(FOLLOW_32);
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

            otherlv_3=(Token)match(input,42,FOLLOW_21); 

            			newLeafNode(otherlv_3, grammarAccess.getReferencePropertyAccess().getOfKeyword_3());
            		
            otherlv_4=(Token)match(input,34,FOLLOW_3); 

            			newLeafNode(otherlv_4, grammarAccess.getReferencePropertyAccess().getTypeKeyword_4());
            		
            // InternalConceptLanguage.g:3404:3: ( ( ruleQualifiedName ) )
            // InternalConceptLanguage.g:3405:4: ( ruleQualifiedName )
            {
            // InternalConceptLanguage.g:3405:4: ( ruleQualifiedName )
            // InternalConceptLanguage.g:3406:5: ruleQualifiedName
            {

            					if (current==null) {
            						current = createModelElement(grammarAccess.getReferencePropertyRule());
            					}
            				

            					newCompositeNode(grammarAccess.getReferencePropertyAccess().getReferenceTypeATypeDefinitionCrossReference_5_0());
            				
            pushFollow(FOLLOW_48);
            ruleQualifiedName();

            state._fsp--;


            					afterParserOrEnumRuleCall();
            				

            }


            }

            // InternalConceptLanguage.g:3420:3: ( ( ( ( ({...}? => ( ({...}? => (otherlv_7= 'description' ( (lv_description_8_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_9= 'verification' ( (lv_verification_10_0= ruleVerificationTypeSpecification ) ) ) ) ) ) )* ) ) )
            // InternalConceptLanguage.g:3421:4: ( ( ( ({...}? => ( ({...}? => (otherlv_7= 'description' ( (lv_description_8_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_9= 'verification' ( (lv_verification_10_0= ruleVerificationTypeSpecification ) ) ) ) ) ) )* ) )
            {
            // InternalConceptLanguage.g:3421:4: ( ( ( ({...}? => ( ({...}? => (otherlv_7= 'description' ( (lv_description_8_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_9= 'verification' ( (lv_verification_10_0= ruleVerificationTypeSpecification ) ) ) ) ) ) )* ) )
            // InternalConceptLanguage.g:3422:5: ( ( ({...}? => ( ({...}? => (otherlv_7= 'description' ( (lv_description_8_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_9= 'verification' ( (lv_verification_10_0= ruleVerificationTypeSpecification ) ) ) ) ) ) )* )
            {
             
            				  getUnorderedGroupHelper().enter(grammarAccess.getReferencePropertyAccess().getUnorderedGroup_6());
            				
            // InternalConceptLanguage.g:3425:5: ( ( ({...}? => ( ({...}? => (otherlv_7= 'description' ( (lv_description_8_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_9= 'verification' ( (lv_verification_10_0= ruleVerificationTypeSpecification ) ) ) ) ) ) )* )
            // InternalConceptLanguage.g:3426:6: ( ({...}? => ( ({...}? => (otherlv_7= 'description' ( (lv_description_8_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_9= 'verification' ( (lv_verification_10_0= ruleVerificationTypeSpecification ) ) ) ) ) ) )*
            {
            // InternalConceptLanguage.g:3426:6: ( ({...}? => ( ({...}? => (otherlv_7= 'description' ( (lv_description_8_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_9= 'verification' ( (lv_verification_10_0= ruleVerificationTypeSpecification ) ) ) ) ) ) )*
            loop43:
            do {
                int alt43=3;
                int LA43_0 = input.LA(1);

                if ( LA43_0 == 15 && getUnorderedGroupHelper().canSelect(grammarAccess.getReferencePropertyAccess().getUnorderedGroup_6(), 0) ) {
                    alt43=1;
                }
                else if ( LA43_0 == 44 && getUnorderedGroupHelper().canSelect(grammarAccess.getReferencePropertyAccess().getUnorderedGroup_6(), 1) ) {
                    alt43=2;
                }


                switch (alt43) {
            	case 1 :
            	    // InternalConceptLanguage.g:3427:4: ({...}? => ( ({...}? => (otherlv_7= 'description' ( (lv_description_8_0= ruleEString ) ) ) ) ) )
            	    {
            	    // InternalConceptLanguage.g:3427:4: ({...}? => ( ({...}? => (otherlv_7= 'description' ( (lv_description_8_0= ruleEString ) ) ) ) ) )
            	    // InternalConceptLanguage.g:3428:5: {...}? => ( ({...}? => (otherlv_7= 'description' ( (lv_description_8_0= ruleEString ) ) ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getReferencePropertyAccess().getUnorderedGroup_6(), 0) ) {
            	        throw new FailedPredicateException(input, "ruleReferenceProperty", "getUnorderedGroupHelper().canSelect(grammarAccess.getReferencePropertyAccess().getUnorderedGroup_6(), 0)");
            	    }
            	    // InternalConceptLanguage.g:3428:114: ( ({...}? => (otherlv_7= 'description' ( (lv_description_8_0= ruleEString ) ) ) ) )
            	    // InternalConceptLanguage.g:3429:6: ({...}? => (otherlv_7= 'description' ( (lv_description_8_0= ruleEString ) ) ) )
            	    {

            	    						getUnorderedGroupHelper().select(grammarAccess.getReferencePropertyAccess().getUnorderedGroup_6(), 0);
            	    					
            	    // InternalConceptLanguage.g:3432:9: ({...}? => (otherlv_7= 'description' ( (lv_description_8_0= ruleEString ) ) ) )
            	    // InternalConceptLanguage.g:3432:10: {...}? => (otherlv_7= 'description' ( (lv_description_8_0= ruleEString ) ) )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleReferenceProperty", "true");
            	    }
            	    // InternalConceptLanguage.g:3432:19: (otherlv_7= 'description' ( (lv_description_8_0= ruleEString ) ) )
            	    // InternalConceptLanguage.g:3432:20: otherlv_7= 'description' ( (lv_description_8_0= ruleEString ) )
            	    {
            	    otherlv_7=(Token)match(input,15,FOLLOW_5); 

            	    									newLeafNode(otherlv_7, grammarAccess.getReferencePropertyAccess().getDescriptionKeyword_6_0_0());
            	    								
            	    // InternalConceptLanguage.g:3436:9: ( (lv_description_8_0= ruleEString ) )
            	    // InternalConceptLanguage.g:3437:10: (lv_description_8_0= ruleEString )
            	    {
            	    // InternalConceptLanguage.g:3437:10: (lv_description_8_0= ruleEString )
            	    // InternalConceptLanguage.g:3438:11: lv_description_8_0= ruleEString
            	    {

            	    											newCompositeNode(grammarAccess.getReferencePropertyAccess().getDescriptionEStringParserRuleCall_6_0_1_0());
            	    										
            	    pushFollow(FOLLOW_48);
            	    lv_description_8_0=ruleEString();

            	    state._fsp--;


            	    											if (current==null) {
            	    												current = createModelElementForParent(grammarAccess.getReferencePropertyRule());
            	    											}
            	    											set(
            	    												current,
            	    												"description",
            	    												lv_description_8_0,
            	    												"de.dlr.sc.virsat.model.concept.ConceptLanguage.EString");
            	    											afterParserOrEnumRuleCall();
            	    										

            	    }


            	    }


            	    }


            	    }

            	     
            	    						getUnorderedGroupHelper().returnFromSelection(grammarAccess.getReferencePropertyAccess().getUnorderedGroup_6());
            	    					

            	    }


            	    }


            	    }
            	    break;
            	case 2 :
            	    // InternalConceptLanguage.g:3461:4: ({...}? => ( ({...}? => (otherlv_9= 'verification' ( (lv_verification_10_0= ruleVerificationTypeSpecification ) ) ) ) ) )
            	    {
            	    // InternalConceptLanguage.g:3461:4: ({...}? => ( ({...}? => (otherlv_9= 'verification' ( (lv_verification_10_0= ruleVerificationTypeSpecification ) ) ) ) ) )
            	    // InternalConceptLanguage.g:3462:5: {...}? => ( ({...}? => (otherlv_9= 'verification' ( (lv_verification_10_0= ruleVerificationTypeSpecification ) ) ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getReferencePropertyAccess().getUnorderedGroup_6(), 1) ) {
            	        throw new FailedPredicateException(input, "ruleReferenceProperty", "getUnorderedGroupHelper().canSelect(grammarAccess.getReferencePropertyAccess().getUnorderedGroup_6(), 1)");
            	    }
            	    // InternalConceptLanguage.g:3462:114: ( ({...}? => (otherlv_9= 'verification' ( (lv_verification_10_0= ruleVerificationTypeSpecification ) ) ) ) )
            	    // InternalConceptLanguage.g:3463:6: ({...}? => (otherlv_9= 'verification' ( (lv_verification_10_0= ruleVerificationTypeSpecification ) ) ) )
            	    {

            	    						getUnorderedGroupHelper().select(grammarAccess.getReferencePropertyAccess().getUnorderedGroup_6(), 1);
            	    					
            	    // InternalConceptLanguage.g:3466:9: ({...}? => (otherlv_9= 'verification' ( (lv_verification_10_0= ruleVerificationTypeSpecification ) ) ) )
            	    // InternalConceptLanguage.g:3466:10: {...}? => (otherlv_9= 'verification' ( (lv_verification_10_0= ruleVerificationTypeSpecification ) ) )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleReferenceProperty", "true");
            	    }
            	    // InternalConceptLanguage.g:3466:19: (otherlv_9= 'verification' ( (lv_verification_10_0= ruleVerificationTypeSpecification ) ) )
            	    // InternalConceptLanguage.g:3466:20: otherlv_9= 'verification' ( (lv_verification_10_0= ruleVerificationTypeSpecification ) )
            	    {
            	    otherlv_9=(Token)match(input,44,FOLLOW_3); 

            	    									newLeafNode(otherlv_9, grammarAccess.getReferencePropertyAccess().getVerificationKeyword_6_1_0());
            	    								
            	    // InternalConceptLanguage.g:3470:9: ( (lv_verification_10_0= ruleVerificationTypeSpecification ) )
            	    // InternalConceptLanguage.g:3471:10: (lv_verification_10_0= ruleVerificationTypeSpecification )
            	    {
            	    // InternalConceptLanguage.g:3471:10: (lv_verification_10_0= ruleVerificationTypeSpecification )
            	    // InternalConceptLanguage.g:3472:11: lv_verification_10_0= ruleVerificationTypeSpecification
            	    {

            	    											newCompositeNode(grammarAccess.getReferencePropertyAccess().getVerificationVerificationTypeSpecificationParserRuleCall_6_1_1_0());
            	    										
            	    pushFollow(FOLLOW_48);
            	    lv_verification_10_0=ruleVerificationTypeSpecification();

            	    state._fsp--;


            	    											if (current==null) {
            	    												current = createModelElementForParent(grammarAccess.getReferencePropertyRule());
            	    											}
            	    											set(
            	    												current,
            	    												"verification",
            	    												lv_verification_10_0,
            	    												"de.dlr.sc.virsat.model.concept.ConceptLanguage.VerificationTypeSpecification");
            	    											afterParserOrEnumRuleCall();
            	    										

            	    }


            	    }


            	    }


            	    }

            	     
            	    						getUnorderedGroupHelper().returnFromSelection(grammarAccess.getReferencePropertyAccess().getUnorderedGroup_6());
            	    					

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop43;
                }
            } while (true);


            }


            }

             
            				  getUnorderedGroupHelper().leave(grammarAccess.getReferencePropertyAccess().getUnorderedGroup_6());
            				

            }

            otherlv_11=(Token)match(input,22,FOLLOW_2); 

            			newLeafNode(otherlv_11, grammarAccess.getReferencePropertyAccess().getSemicolonKeyword_7());
            		

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


    // $ANTLR start "entryRuleEReferenceProperty"
    // InternalConceptLanguage.g:3510:1: entryRuleEReferenceProperty returns [EObject current=null] : iv_ruleEReferenceProperty= ruleEReferenceProperty EOF ;
    public final EObject entryRuleEReferenceProperty() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleEReferenceProperty = null;


        try {
            // InternalConceptLanguage.g:3510:59: (iv_ruleEReferenceProperty= ruleEReferenceProperty EOF )
            // InternalConceptLanguage.g:3511:2: iv_ruleEReferenceProperty= ruleEReferenceProperty EOF
            {
             newCompositeNode(grammarAccess.getEReferencePropertyRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleEReferenceProperty=ruleEReferenceProperty();

            state._fsp--;

             current =iv_ruleEReferenceProperty; 
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
    // $ANTLR end "entryRuleEReferenceProperty"


    // $ANTLR start "ruleEReferenceProperty"
    // InternalConceptLanguage.g:3517:1: ruleEReferenceProperty returns [EObject current=null] : (otherlv_0= 'EReference' ( (lv_name_1_0= RULE_ID ) ) ( (lv_arrayModifier_2_0= ruleArrayModifier ) )? otherlv_3= 'of' otherlv_4= 'Type' ( ( ruleQualifiedName ) ) ( ( ( ( ({...}? => ( ({...}? => (otherlv_7= 'description' ( (lv_description_8_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_9= 'verification' ( (lv_verification_10_0= ruleVerificationTypeSpecification ) ) ) ) ) ) )* ) ) ) otherlv_11= ';' ) ;
    public final EObject ruleEReferenceProperty() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token lv_name_1_0=null;
        Token otherlv_3=null;
        Token otherlv_4=null;
        Token otherlv_7=null;
        Token otherlv_9=null;
        Token otherlv_11=null;
        EObject lv_arrayModifier_2_0 = null;

        AntlrDatatypeRuleToken lv_description_8_0 = null;

        EObject lv_verification_10_0 = null;



        	enterRule();

        try {
            // InternalConceptLanguage.g:3523:2: ( (otherlv_0= 'EReference' ( (lv_name_1_0= RULE_ID ) ) ( (lv_arrayModifier_2_0= ruleArrayModifier ) )? otherlv_3= 'of' otherlv_4= 'Type' ( ( ruleQualifiedName ) ) ( ( ( ( ({...}? => ( ({...}? => (otherlv_7= 'description' ( (lv_description_8_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_9= 'verification' ( (lv_verification_10_0= ruleVerificationTypeSpecification ) ) ) ) ) ) )* ) ) ) otherlv_11= ';' ) )
            // InternalConceptLanguage.g:3524:2: (otherlv_0= 'EReference' ( (lv_name_1_0= RULE_ID ) ) ( (lv_arrayModifier_2_0= ruleArrayModifier ) )? otherlv_3= 'of' otherlv_4= 'Type' ( ( ruleQualifiedName ) ) ( ( ( ( ({...}? => ( ({...}? => (otherlv_7= 'description' ( (lv_description_8_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_9= 'verification' ( (lv_verification_10_0= ruleVerificationTypeSpecification ) ) ) ) ) ) )* ) ) ) otherlv_11= ';' )
            {
            // InternalConceptLanguage.g:3524:2: (otherlv_0= 'EReference' ( (lv_name_1_0= RULE_ID ) ) ( (lv_arrayModifier_2_0= ruleArrayModifier ) )? otherlv_3= 'of' otherlv_4= 'Type' ( ( ruleQualifiedName ) ) ( ( ( ( ({...}? => ( ({...}? => (otherlv_7= 'description' ( (lv_description_8_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_9= 'verification' ( (lv_verification_10_0= ruleVerificationTypeSpecification ) ) ) ) ) ) )* ) ) ) otherlv_11= ';' )
            // InternalConceptLanguage.g:3525:3: otherlv_0= 'EReference' ( (lv_name_1_0= RULE_ID ) ) ( (lv_arrayModifier_2_0= ruleArrayModifier ) )? otherlv_3= 'of' otherlv_4= 'Type' ( ( ruleQualifiedName ) ) ( ( ( ( ({...}? => ( ({...}? => (otherlv_7= 'description' ( (lv_description_8_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_9= 'verification' ( (lv_verification_10_0= ruleVerificationTypeSpecification ) ) ) ) ) ) )* ) ) ) otherlv_11= ';'
            {
            otherlv_0=(Token)match(input,55,FOLLOW_3); 

            			newLeafNode(otherlv_0, grammarAccess.getEReferencePropertyAccess().getEReferenceKeyword_0());
            		
            // InternalConceptLanguage.g:3529:3: ( (lv_name_1_0= RULE_ID ) )
            // InternalConceptLanguage.g:3530:4: (lv_name_1_0= RULE_ID )
            {
            // InternalConceptLanguage.g:3530:4: (lv_name_1_0= RULE_ID )
            // InternalConceptLanguage.g:3531:5: lv_name_1_0= RULE_ID
            {
            lv_name_1_0=(Token)match(input,RULE_ID,FOLLOW_31); 

            					newLeafNode(lv_name_1_0, grammarAccess.getEReferencePropertyAccess().getNameIDTerminalRuleCall_1_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getEReferencePropertyRule());
            					}
            					setWithLastConsumed(
            						current,
            						"name",
            						lv_name_1_0,
            						"org.eclipse.xtext.common.Terminals.ID");
            				

            }


            }

            // InternalConceptLanguage.g:3547:3: ( (lv_arrayModifier_2_0= ruleArrayModifier ) )?
            int alt44=2;
            int LA44_0 = input.LA(1);

            if ( (LA44_0==25) ) {
                alt44=1;
            }
            switch (alt44) {
                case 1 :
                    // InternalConceptLanguage.g:3548:4: (lv_arrayModifier_2_0= ruleArrayModifier )
                    {
                    // InternalConceptLanguage.g:3548:4: (lv_arrayModifier_2_0= ruleArrayModifier )
                    // InternalConceptLanguage.g:3549:5: lv_arrayModifier_2_0= ruleArrayModifier
                    {

                    					newCompositeNode(grammarAccess.getEReferencePropertyAccess().getArrayModifierArrayModifierParserRuleCall_2_0());
                    				
                    pushFollow(FOLLOW_32);
                    lv_arrayModifier_2_0=ruleArrayModifier();

                    state._fsp--;


                    					if (current==null) {
                    						current = createModelElementForParent(grammarAccess.getEReferencePropertyRule());
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

            otherlv_3=(Token)match(input,42,FOLLOW_21); 

            			newLeafNode(otherlv_3, grammarAccess.getEReferencePropertyAccess().getOfKeyword_3());
            		
            otherlv_4=(Token)match(input,34,FOLLOW_3); 

            			newLeafNode(otherlv_4, grammarAccess.getEReferencePropertyAccess().getTypeKeyword_4());
            		
            // InternalConceptLanguage.g:3574:3: ( ( ruleQualifiedName ) )
            // InternalConceptLanguage.g:3575:4: ( ruleQualifiedName )
            {
            // InternalConceptLanguage.g:3575:4: ( ruleQualifiedName )
            // InternalConceptLanguage.g:3576:5: ruleQualifiedName
            {

            					if (current==null) {
            						current = createModelElement(grammarAccess.getEReferencePropertyRule());
            					}
            				

            					newCompositeNode(grammarAccess.getEReferencePropertyAccess().getReferenceTypeEClassCrossReference_5_0());
            				
            pushFollow(FOLLOW_48);
            ruleQualifiedName();

            state._fsp--;


            					afterParserOrEnumRuleCall();
            				

            }


            }

            // InternalConceptLanguage.g:3590:3: ( ( ( ( ({...}? => ( ({...}? => (otherlv_7= 'description' ( (lv_description_8_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_9= 'verification' ( (lv_verification_10_0= ruleVerificationTypeSpecification ) ) ) ) ) ) )* ) ) )
            // InternalConceptLanguage.g:3591:4: ( ( ( ({...}? => ( ({...}? => (otherlv_7= 'description' ( (lv_description_8_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_9= 'verification' ( (lv_verification_10_0= ruleVerificationTypeSpecification ) ) ) ) ) ) )* ) )
            {
            // InternalConceptLanguage.g:3591:4: ( ( ( ({...}? => ( ({...}? => (otherlv_7= 'description' ( (lv_description_8_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_9= 'verification' ( (lv_verification_10_0= ruleVerificationTypeSpecification ) ) ) ) ) ) )* ) )
            // InternalConceptLanguage.g:3592:5: ( ( ({...}? => ( ({...}? => (otherlv_7= 'description' ( (lv_description_8_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_9= 'verification' ( (lv_verification_10_0= ruleVerificationTypeSpecification ) ) ) ) ) ) )* )
            {
             
            				  getUnorderedGroupHelper().enter(grammarAccess.getEReferencePropertyAccess().getUnorderedGroup_6());
            				
            // InternalConceptLanguage.g:3595:5: ( ( ({...}? => ( ({...}? => (otherlv_7= 'description' ( (lv_description_8_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_9= 'verification' ( (lv_verification_10_0= ruleVerificationTypeSpecification ) ) ) ) ) ) )* )
            // InternalConceptLanguage.g:3596:6: ( ({...}? => ( ({...}? => (otherlv_7= 'description' ( (lv_description_8_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_9= 'verification' ( (lv_verification_10_0= ruleVerificationTypeSpecification ) ) ) ) ) ) )*
            {
            // InternalConceptLanguage.g:3596:6: ( ({...}? => ( ({...}? => (otherlv_7= 'description' ( (lv_description_8_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_9= 'verification' ( (lv_verification_10_0= ruleVerificationTypeSpecification ) ) ) ) ) ) )*
            loop45:
            do {
                int alt45=3;
                int LA45_0 = input.LA(1);

                if ( LA45_0 == 15 && getUnorderedGroupHelper().canSelect(grammarAccess.getEReferencePropertyAccess().getUnorderedGroup_6(), 0) ) {
                    alt45=1;
                }
                else if ( LA45_0 == 44 && getUnorderedGroupHelper().canSelect(grammarAccess.getEReferencePropertyAccess().getUnorderedGroup_6(), 1) ) {
                    alt45=2;
                }


                switch (alt45) {
            	case 1 :
            	    // InternalConceptLanguage.g:3597:4: ({...}? => ( ({...}? => (otherlv_7= 'description' ( (lv_description_8_0= ruleEString ) ) ) ) ) )
            	    {
            	    // InternalConceptLanguage.g:3597:4: ({...}? => ( ({...}? => (otherlv_7= 'description' ( (lv_description_8_0= ruleEString ) ) ) ) ) )
            	    // InternalConceptLanguage.g:3598:5: {...}? => ( ({...}? => (otherlv_7= 'description' ( (lv_description_8_0= ruleEString ) ) ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getEReferencePropertyAccess().getUnorderedGroup_6(), 0) ) {
            	        throw new FailedPredicateException(input, "ruleEReferenceProperty", "getUnorderedGroupHelper().canSelect(grammarAccess.getEReferencePropertyAccess().getUnorderedGroup_6(), 0)");
            	    }
            	    // InternalConceptLanguage.g:3598:115: ( ({...}? => (otherlv_7= 'description' ( (lv_description_8_0= ruleEString ) ) ) ) )
            	    // InternalConceptLanguage.g:3599:6: ({...}? => (otherlv_7= 'description' ( (lv_description_8_0= ruleEString ) ) ) )
            	    {

            	    						getUnorderedGroupHelper().select(grammarAccess.getEReferencePropertyAccess().getUnorderedGroup_6(), 0);
            	    					
            	    // InternalConceptLanguage.g:3602:9: ({...}? => (otherlv_7= 'description' ( (lv_description_8_0= ruleEString ) ) ) )
            	    // InternalConceptLanguage.g:3602:10: {...}? => (otherlv_7= 'description' ( (lv_description_8_0= ruleEString ) ) )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleEReferenceProperty", "true");
            	    }
            	    // InternalConceptLanguage.g:3602:19: (otherlv_7= 'description' ( (lv_description_8_0= ruleEString ) ) )
            	    // InternalConceptLanguage.g:3602:20: otherlv_7= 'description' ( (lv_description_8_0= ruleEString ) )
            	    {
            	    otherlv_7=(Token)match(input,15,FOLLOW_5); 

            	    									newLeafNode(otherlv_7, grammarAccess.getEReferencePropertyAccess().getDescriptionKeyword_6_0_0());
            	    								
            	    // InternalConceptLanguage.g:3606:9: ( (lv_description_8_0= ruleEString ) )
            	    // InternalConceptLanguage.g:3607:10: (lv_description_8_0= ruleEString )
            	    {
            	    // InternalConceptLanguage.g:3607:10: (lv_description_8_0= ruleEString )
            	    // InternalConceptLanguage.g:3608:11: lv_description_8_0= ruleEString
            	    {

            	    											newCompositeNode(grammarAccess.getEReferencePropertyAccess().getDescriptionEStringParserRuleCall_6_0_1_0());
            	    										
            	    pushFollow(FOLLOW_48);
            	    lv_description_8_0=ruleEString();

            	    state._fsp--;


            	    											if (current==null) {
            	    												current = createModelElementForParent(grammarAccess.getEReferencePropertyRule());
            	    											}
            	    											set(
            	    												current,
            	    												"description",
            	    												lv_description_8_0,
            	    												"de.dlr.sc.virsat.model.concept.ConceptLanguage.EString");
            	    											afterParserOrEnumRuleCall();
            	    										

            	    }


            	    }


            	    }


            	    }

            	     
            	    						getUnorderedGroupHelper().returnFromSelection(grammarAccess.getEReferencePropertyAccess().getUnorderedGroup_6());
            	    					

            	    }


            	    }


            	    }
            	    break;
            	case 2 :
            	    // InternalConceptLanguage.g:3631:4: ({...}? => ( ({...}? => (otherlv_9= 'verification' ( (lv_verification_10_0= ruleVerificationTypeSpecification ) ) ) ) ) )
            	    {
            	    // InternalConceptLanguage.g:3631:4: ({...}? => ( ({...}? => (otherlv_9= 'verification' ( (lv_verification_10_0= ruleVerificationTypeSpecification ) ) ) ) ) )
            	    // InternalConceptLanguage.g:3632:5: {...}? => ( ({...}? => (otherlv_9= 'verification' ( (lv_verification_10_0= ruleVerificationTypeSpecification ) ) ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getEReferencePropertyAccess().getUnorderedGroup_6(), 1) ) {
            	        throw new FailedPredicateException(input, "ruleEReferenceProperty", "getUnorderedGroupHelper().canSelect(grammarAccess.getEReferencePropertyAccess().getUnorderedGroup_6(), 1)");
            	    }
            	    // InternalConceptLanguage.g:3632:115: ( ({...}? => (otherlv_9= 'verification' ( (lv_verification_10_0= ruleVerificationTypeSpecification ) ) ) ) )
            	    // InternalConceptLanguage.g:3633:6: ({...}? => (otherlv_9= 'verification' ( (lv_verification_10_0= ruleVerificationTypeSpecification ) ) ) )
            	    {

            	    						getUnorderedGroupHelper().select(grammarAccess.getEReferencePropertyAccess().getUnorderedGroup_6(), 1);
            	    					
            	    // InternalConceptLanguage.g:3636:9: ({...}? => (otherlv_9= 'verification' ( (lv_verification_10_0= ruleVerificationTypeSpecification ) ) ) )
            	    // InternalConceptLanguage.g:3636:10: {...}? => (otherlv_9= 'verification' ( (lv_verification_10_0= ruleVerificationTypeSpecification ) ) )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleEReferenceProperty", "true");
            	    }
            	    // InternalConceptLanguage.g:3636:19: (otherlv_9= 'verification' ( (lv_verification_10_0= ruleVerificationTypeSpecification ) ) )
            	    // InternalConceptLanguage.g:3636:20: otherlv_9= 'verification' ( (lv_verification_10_0= ruleVerificationTypeSpecification ) )
            	    {
            	    otherlv_9=(Token)match(input,44,FOLLOW_3); 

            	    									newLeafNode(otherlv_9, grammarAccess.getEReferencePropertyAccess().getVerificationKeyword_6_1_0());
            	    								
            	    // InternalConceptLanguage.g:3640:9: ( (lv_verification_10_0= ruleVerificationTypeSpecification ) )
            	    // InternalConceptLanguage.g:3641:10: (lv_verification_10_0= ruleVerificationTypeSpecification )
            	    {
            	    // InternalConceptLanguage.g:3641:10: (lv_verification_10_0= ruleVerificationTypeSpecification )
            	    // InternalConceptLanguage.g:3642:11: lv_verification_10_0= ruleVerificationTypeSpecification
            	    {

            	    											newCompositeNode(grammarAccess.getEReferencePropertyAccess().getVerificationVerificationTypeSpecificationParserRuleCall_6_1_1_0());
            	    										
            	    pushFollow(FOLLOW_48);
            	    lv_verification_10_0=ruleVerificationTypeSpecification();

            	    state._fsp--;


            	    											if (current==null) {
            	    												current = createModelElementForParent(grammarAccess.getEReferencePropertyRule());
            	    											}
            	    											set(
            	    												current,
            	    												"verification",
            	    												lv_verification_10_0,
            	    												"de.dlr.sc.virsat.model.concept.ConceptLanguage.VerificationTypeSpecification");
            	    											afterParserOrEnumRuleCall();
            	    										

            	    }


            	    }


            	    }


            	    }

            	     
            	    						getUnorderedGroupHelper().returnFromSelection(grammarAccess.getEReferencePropertyAccess().getUnorderedGroup_6());
            	    					

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

             
            				  getUnorderedGroupHelper().leave(grammarAccess.getEReferencePropertyAccess().getUnorderedGroup_6());
            				

            }

            otherlv_11=(Token)match(input,22,FOLLOW_2); 

            			newLeafNode(otherlv_11, grammarAccess.getEReferencePropertyAccess().getSemicolonKeyword_7());
            		

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
    // $ANTLR end "ruleEReferenceProperty"


    // $ANTLR start "entryRuleResourceProperty"
    // InternalConceptLanguage.g:3680:1: entryRuleResourceProperty returns [EObject current=null] : iv_ruleResourceProperty= ruleResourceProperty EOF ;
    public final EObject entryRuleResourceProperty() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleResourceProperty = null;


        try {
            // InternalConceptLanguage.g:3680:57: (iv_ruleResourceProperty= ruleResourceProperty EOF )
            // InternalConceptLanguage.g:3681:2: iv_ruleResourceProperty= ruleResourceProperty EOF
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
    // InternalConceptLanguage.g:3687:1: ruleResourceProperty returns [EObject current=null] : (otherlv_0= 'Resource' ( (lv_name_1_0= RULE_ID ) ) ( (lv_arrayModifier_2_0= ruleArrayModifier ) )? ( ( ( ( ({...}? => ( ({...}? => (otherlv_4= 'description' ( (lv_description_5_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_6= 'verification' ( (lv_verification_7_0= ruleVerificationTypeSpecification ) ) ) ) ) ) )* ) ) ) otherlv_8= ';' ) ;
    public final EObject ruleResourceProperty() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token lv_name_1_0=null;
        Token otherlv_4=null;
        Token otherlv_6=null;
        Token otherlv_8=null;
        EObject lv_arrayModifier_2_0 = null;

        AntlrDatatypeRuleToken lv_description_5_0 = null;

        EObject lv_verification_7_0 = null;



        	enterRule();

        try {
            // InternalConceptLanguage.g:3693:2: ( (otherlv_0= 'Resource' ( (lv_name_1_0= RULE_ID ) ) ( (lv_arrayModifier_2_0= ruleArrayModifier ) )? ( ( ( ( ({...}? => ( ({...}? => (otherlv_4= 'description' ( (lv_description_5_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_6= 'verification' ( (lv_verification_7_0= ruleVerificationTypeSpecification ) ) ) ) ) ) )* ) ) ) otherlv_8= ';' ) )
            // InternalConceptLanguage.g:3694:2: (otherlv_0= 'Resource' ( (lv_name_1_0= RULE_ID ) ) ( (lv_arrayModifier_2_0= ruleArrayModifier ) )? ( ( ( ( ({...}? => ( ({...}? => (otherlv_4= 'description' ( (lv_description_5_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_6= 'verification' ( (lv_verification_7_0= ruleVerificationTypeSpecification ) ) ) ) ) ) )* ) ) ) otherlv_8= ';' )
            {
            // InternalConceptLanguage.g:3694:2: (otherlv_0= 'Resource' ( (lv_name_1_0= RULE_ID ) ) ( (lv_arrayModifier_2_0= ruleArrayModifier ) )? ( ( ( ( ({...}? => ( ({...}? => (otherlv_4= 'description' ( (lv_description_5_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_6= 'verification' ( (lv_verification_7_0= ruleVerificationTypeSpecification ) ) ) ) ) ) )* ) ) ) otherlv_8= ';' )
            // InternalConceptLanguage.g:3695:3: otherlv_0= 'Resource' ( (lv_name_1_0= RULE_ID ) ) ( (lv_arrayModifier_2_0= ruleArrayModifier ) )? ( ( ( ( ({...}? => ( ({...}? => (otherlv_4= 'description' ( (lv_description_5_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_6= 'verification' ( (lv_verification_7_0= ruleVerificationTypeSpecification ) ) ) ) ) ) )* ) ) ) otherlv_8= ';'
            {
            otherlv_0=(Token)match(input,56,FOLLOW_3); 

            			newLeafNode(otherlv_0, grammarAccess.getResourcePropertyAccess().getResourceKeyword_0());
            		
            // InternalConceptLanguage.g:3699:3: ( (lv_name_1_0= RULE_ID ) )
            // InternalConceptLanguage.g:3700:4: (lv_name_1_0= RULE_ID )
            {
            // InternalConceptLanguage.g:3700:4: (lv_name_1_0= RULE_ID )
            // InternalConceptLanguage.g:3701:5: lv_name_1_0= RULE_ID
            {
            lv_name_1_0=(Token)match(input,RULE_ID,FOLLOW_49); 

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

            // InternalConceptLanguage.g:3717:3: ( (lv_arrayModifier_2_0= ruleArrayModifier ) )?
            int alt46=2;
            int LA46_0 = input.LA(1);

            if ( (LA46_0==25) ) {
                alt46=1;
            }
            switch (alt46) {
                case 1 :
                    // InternalConceptLanguage.g:3718:4: (lv_arrayModifier_2_0= ruleArrayModifier )
                    {
                    // InternalConceptLanguage.g:3718:4: (lv_arrayModifier_2_0= ruleArrayModifier )
                    // InternalConceptLanguage.g:3719:5: lv_arrayModifier_2_0= ruleArrayModifier
                    {

                    					newCompositeNode(grammarAccess.getResourcePropertyAccess().getArrayModifierArrayModifierParserRuleCall_2_0());
                    				
                    pushFollow(FOLLOW_48);
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

            // InternalConceptLanguage.g:3736:3: ( ( ( ( ({...}? => ( ({...}? => (otherlv_4= 'description' ( (lv_description_5_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_6= 'verification' ( (lv_verification_7_0= ruleVerificationTypeSpecification ) ) ) ) ) ) )* ) ) )
            // InternalConceptLanguage.g:3737:4: ( ( ( ({...}? => ( ({...}? => (otherlv_4= 'description' ( (lv_description_5_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_6= 'verification' ( (lv_verification_7_0= ruleVerificationTypeSpecification ) ) ) ) ) ) )* ) )
            {
            // InternalConceptLanguage.g:3737:4: ( ( ( ({...}? => ( ({...}? => (otherlv_4= 'description' ( (lv_description_5_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_6= 'verification' ( (lv_verification_7_0= ruleVerificationTypeSpecification ) ) ) ) ) ) )* ) )
            // InternalConceptLanguage.g:3738:5: ( ( ({...}? => ( ({...}? => (otherlv_4= 'description' ( (lv_description_5_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_6= 'verification' ( (lv_verification_7_0= ruleVerificationTypeSpecification ) ) ) ) ) ) )* )
            {
             
            				  getUnorderedGroupHelper().enter(grammarAccess.getResourcePropertyAccess().getUnorderedGroup_3());
            				
            // InternalConceptLanguage.g:3741:5: ( ( ({...}? => ( ({...}? => (otherlv_4= 'description' ( (lv_description_5_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_6= 'verification' ( (lv_verification_7_0= ruleVerificationTypeSpecification ) ) ) ) ) ) )* )
            // InternalConceptLanguage.g:3742:6: ( ({...}? => ( ({...}? => (otherlv_4= 'description' ( (lv_description_5_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_6= 'verification' ( (lv_verification_7_0= ruleVerificationTypeSpecification ) ) ) ) ) ) )*
            {
            // InternalConceptLanguage.g:3742:6: ( ({...}? => ( ({...}? => (otherlv_4= 'description' ( (lv_description_5_0= ruleEString ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_6= 'verification' ( (lv_verification_7_0= ruleVerificationTypeSpecification ) ) ) ) ) ) )*
            loop47:
            do {
                int alt47=3;
                int LA47_0 = input.LA(1);

                if ( LA47_0 == 15 && getUnorderedGroupHelper().canSelect(grammarAccess.getResourcePropertyAccess().getUnorderedGroup_3(), 0) ) {
                    alt47=1;
                }
                else if ( LA47_0 == 44 && getUnorderedGroupHelper().canSelect(grammarAccess.getResourcePropertyAccess().getUnorderedGroup_3(), 1) ) {
                    alt47=2;
                }


                switch (alt47) {
            	case 1 :
            	    // InternalConceptLanguage.g:3743:4: ({...}? => ( ({...}? => (otherlv_4= 'description' ( (lv_description_5_0= ruleEString ) ) ) ) ) )
            	    {
            	    // InternalConceptLanguage.g:3743:4: ({...}? => ( ({...}? => (otherlv_4= 'description' ( (lv_description_5_0= ruleEString ) ) ) ) ) )
            	    // InternalConceptLanguage.g:3744:5: {...}? => ( ({...}? => (otherlv_4= 'description' ( (lv_description_5_0= ruleEString ) ) ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getResourcePropertyAccess().getUnorderedGroup_3(), 0) ) {
            	        throw new FailedPredicateException(input, "ruleResourceProperty", "getUnorderedGroupHelper().canSelect(grammarAccess.getResourcePropertyAccess().getUnorderedGroup_3(), 0)");
            	    }
            	    // InternalConceptLanguage.g:3744:113: ( ({...}? => (otherlv_4= 'description' ( (lv_description_5_0= ruleEString ) ) ) ) )
            	    // InternalConceptLanguage.g:3745:6: ({...}? => (otherlv_4= 'description' ( (lv_description_5_0= ruleEString ) ) ) )
            	    {

            	    						getUnorderedGroupHelper().select(grammarAccess.getResourcePropertyAccess().getUnorderedGroup_3(), 0);
            	    					
            	    // InternalConceptLanguage.g:3748:9: ({...}? => (otherlv_4= 'description' ( (lv_description_5_0= ruleEString ) ) ) )
            	    // InternalConceptLanguage.g:3748:10: {...}? => (otherlv_4= 'description' ( (lv_description_5_0= ruleEString ) ) )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleResourceProperty", "true");
            	    }
            	    // InternalConceptLanguage.g:3748:19: (otherlv_4= 'description' ( (lv_description_5_0= ruleEString ) ) )
            	    // InternalConceptLanguage.g:3748:20: otherlv_4= 'description' ( (lv_description_5_0= ruleEString ) )
            	    {
            	    otherlv_4=(Token)match(input,15,FOLLOW_5); 

            	    									newLeafNode(otherlv_4, grammarAccess.getResourcePropertyAccess().getDescriptionKeyword_3_0_0());
            	    								
            	    // InternalConceptLanguage.g:3752:9: ( (lv_description_5_0= ruleEString ) )
            	    // InternalConceptLanguage.g:3753:10: (lv_description_5_0= ruleEString )
            	    {
            	    // InternalConceptLanguage.g:3753:10: (lv_description_5_0= ruleEString )
            	    // InternalConceptLanguage.g:3754:11: lv_description_5_0= ruleEString
            	    {

            	    											newCompositeNode(grammarAccess.getResourcePropertyAccess().getDescriptionEStringParserRuleCall_3_0_1_0());
            	    										
            	    pushFollow(FOLLOW_48);
            	    lv_description_5_0=ruleEString();

            	    state._fsp--;


            	    											if (current==null) {
            	    												current = createModelElementForParent(grammarAccess.getResourcePropertyRule());
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

            	     
            	    						getUnorderedGroupHelper().returnFromSelection(grammarAccess.getResourcePropertyAccess().getUnorderedGroup_3());
            	    					

            	    }


            	    }


            	    }
            	    break;
            	case 2 :
            	    // InternalConceptLanguage.g:3777:4: ({...}? => ( ({...}? => (otherlv_6= 'verification' ( (lv_verification_7_0= ruleVerificationTypeSpecification ) ) ) ) ) )
            	    {
            	    // InternalConceptLanguage.g:3777:4: ({...}? => ( ({...}? => (otherlv_6= 'verification' ( (lv_verification_7_0= ruleVerificationTypeSpecification ) ) ) ) ) )
            	    // InternalConceptLanguage.g:3778:5: {...}? => ( ({...}? => (otherlv_6= 'verification' ( (lv_verification_7_0= ruleVerificationTypeSpecification ) ) ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getResourcePropertyAccess().getUnorderedGroup_3(), 1) ) {
            	        throw new FailedPredicateException(input, "ruleResourceProperty", "getUnorderedGroupHelper().canSelect(grammarAccess.getResourcePropertyAccess().getUnorderedGroup_3(), 1)");
            	    }
            	    // InternalConceptLanguage.g:3778:113: ( ({...}? => (otherlv_6= 'verification' ( (lv_verification_7_0= ruleVerificationTypeSpecification ) ) ) ) )
            	    // InternalConceptLanguage.g:3779:6: ({...}? => (otherlv_6= 'verification' ( (lv_verification_7_0= ruleVerificationTypeSpecification ) ) ) )
            	    {

            	    						getUnorderedGroupHelper().select(grammarAccess.getResourcePropertyAccess().getUnorderedGroup_3(), 1);
            	    					
            	    // InternalConceptLanguage.g:3782:9: ({...}? => (otherlv_6= 'verification' ( (lv_verification_7_0= ruleVerificationTypeSpecification ) ) ) )
            	    // InternalConceptLanguage.g:3782:10: {...}? => (otherlv_6= 'verification' ( (lv_verification_7_0= ruleVerificationTypeSpecification ) ) )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleResourceProperty", "true");
            	    }
            	    // InternalConceptLanguage.g:3782:19: (otherlv_6= 'verification' ( (lv_verification_7_0= ruleVerificationTypeSpecification ) ) )
            	    // InternalConceptLanguage.g:3782:20: otherlv_6= 'verification' ( (lv_verification_7_0= ruleVerificationTypeSpecification ) )
            	    {
            	    otherlv_6=(Token)match(input,44,FOLLOW_3); 

            	    									newLeafNode(otherlv_6, grammarAccess.getResourcePropertyAccess().getVerificationKeyword_3_1_0());
            	    								
            	    // InternalConceptLanguage.g:3786:9: ( (lv_verification_7_0= ruleVerificationTypeSpecification ) )
            	    // InternalConceptLanguage.g:3787:10: (lv_verification_7_0= ruleVerificationTypeSpecification )
            	    {
            	    // InternalConceptLanguage.g:3787:10: (lv_verification_7_0= ruleVerificationTypeSpecification )
            	    // InternalConceptLanguage.g:3788:11: lv_verification_7_0= ruleVerificationTypeSpecification
            	    {

            	    											newCompositeNode(grammarAccess.getResourcePropertyAccess().getVerificationVerificationTypeSpecificationParserRuleCall_3_1_1_0());
            	    										
            	    pushFollow(FOLLOW_48);
            	    lv_verification_7_0=ruleVerificationTypeSpecification();

            	    state._fsp--;


            	    											if (current==null) {
            	    												current = createModelElementForParent(grammarAccess.getResourcePropertyRule());
            	    											}
            	    											set(
            	    												current,
            	    												"verification",
            	    												lv_verification_7_0,
            	    												"de.dlr.sc.virsat.model.concept.ConceptLanguage.VerificationTypeSpecification");
            	    											afterParserOrEnumRuleCall();
            	    										

            	    }


            	    }


            	    }


            	    }

            	     
            	    						getUnorderedGroupHelper().returnFromSelection(grammarAccess.getResourcePropertyAccess().getUnorderedGroup_3());
            	    					

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

             
            				  getUnorderedGroupHelper().leave(grammarAccess.getResourcePropertyAccess().getUnorderedGroup_3());
            				

            }

            otherlv_8=(Token)match(input,22,FOLLOW_2); 

            			newLeafNode(otherlv_8, grammarAccess.getResourcePropertyAccess().getSemicolonKeyword_4());
            		

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
    // InternalConceptLanguage.g:3826:1: entryRuleEquationDefinition returns [EObject current=null] : iv_ruleEquationDefinition= ruleEquationDefinition EOF ;
    public final EObject entryRuleEquationDefinition() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleEquationDefinition = null;


        try {
            // InternalConceptLanguage.g:3826:59: (iv_ruleEquationDefinition= ruleEquationDefinition EOF )
            // InternalConceptLanguage.g:3827:2: iv_ruleEquationDefinition= ruleEquationDefinition EOF
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
    // InternalConceptLanguage.g:3833:1: ruleEquationDefinition returns [EObject current=null] : ( ( (lv_result_0_0= ruleEquationDefinitionResult ) ) otherlv_1= '=' ( (lv_expression_2_0= ruleAdditionAndSubtraction ) ) otherlv_3= ';' ) ;
    public final EObject ruleEquationDefinition() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_3=null;
        EObject lv_result_0_0 = null;

        EObject lv_expression_2_0 = null;



        	enterRule();

        try {
            // InternalConceptLanguage.g:3839:2: ( ( ( (lv_result_0_0= ruleEquationDefinitionResult ) ) otherlv_1= '=' ( (lv_expression_2_0= ruleAdditionAndSubtraction ) ) otherlv_3= ';' ) )
            // InternalConceptLanguage.g:3840:2: ( ( (lv_result_0_0= ruleEquationDefinitionResult ) ) otherlv_1= '=' ( (lv_expression_2_0= ruleAdditionAndSubtraction ) ) otherlv_3= ';' )
            {
            // InternalConceptLanguage.g:3840:2: ( ( (lv_result_0_0= ruleEquationDefinitionResult ) ) otherlv_1= '=' ( (lv_expression_2_0= ruleAdditionAndSubtraction ) ) otherlv_3= ';' )
            // InternalConceptLanguage.g:3841:3: ( (lv_result_0_0= ruleEquationDefinitionResult ) ) otherlv_1= '=' ( (lv_expression_2_0= ruleAdditionAndSubtraction ) ) otherlv_3= ';'
            {
            // InternalConceptLanguage.g:3841:3: ( (lv_result_0_0= ruleEquationDefinitionResult ) )
            // InternalConceptLanguage.g:3842:4: (lv_result_0_0= ruleEquationDefinitionResult )
            {
            // InternalConceptLanguage.g:3842:4: (lv_result_0_0= ruleEquationDefinitionResult )
            // InternalConceptLanguage.g:3843:5: lv_result_0_0= ruleEquationDefinitionResult
            {

            					newCompositeNode(grammarAccess.getEquationDefinitionAccess().getResultEquationDefinitionResultParserRuleCall_0_0());
            				
            pushFollow(FOLLOW_46);
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

            otherlv_1=(Token)match(input,53,FOLLOW_50); 

            			newLeafNode(otherlv_1, grammarAccess.getEquationDefinitionAccess().getEqualsSignKeyword_1());
            		
            // InternalConceptLanguage.g:3864:3: ( (lv_expression_2_0= ruleAdditionAndSubtraction ) )
            // InternalConceptLanguage.g:3865:4: (lv_expression_2_0= ruleAdditionAndSubtraction )
            {
            // InternalConceptLanguage.g:3865:4: (lv_expression_2_0= ruleAdditionAndSubtraction )
            // InternalConceptLanguage.g:3866:5: lv_expression_2_0= ruleAdditionAndSubtraction
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

            otherlv_3=(Token)match(input,22,FOLLOW_2); 

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
    // InternalConceptLanguage.g:3891:1: entryRuleEquationDefinitionResult returns [EObject current=null] : iv_ruleEquationDefinitionResult= ruleEquationDefinitionResult EOF ;
    public final EObject entryRuleEquationDefinitionResult() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleEquationDefinitionResult = null;


        try {
            // InternalConceptLanguage.g:3891:65: (iv_ruleEquationDefinitionResult= ruleEquationDefinitionResult EOF )
            // InternalConceptLanguage.g:3892:2: iv_ruleEquationDefinitionResult= ruleEquationDefinitionResult EOF
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
    // InternalConceptLanguage.g:3898:1: ruleEquationDefinitionResult returns [EObject current=null] : (this_TypeDefinitionResult_0= ruleTypeDefinitionResult | this_EquationIntermediateResult_1= ruleEquationIntermediateResult ) ;
    public final EObject ruleEquationDefinitionResult() throws RecognitionException {
        EObject current = null;

        EObject this_TypeDefinitionResult_0 = null;

        EObject this_EquationIntermediateResult_1 = null;



        	enterRule();

        try {
            // InternalConceptLanguage.g:3904:2: ( (this_TypeDefinitionResult_0= ruleTypeDefinitionResult | this_EquationIntermediateResult_1= ruleEquationIntermediateResult ) )
            // InternalConceptLanguage.g:3905:2: (this_TypeDefinitionResult_0= ruleTypeDefinitionResult | this_EquationIntermediateResult_1= ruleEquationIntermediateResult )
            {
            // InternalConceptLanguage.g:3905:2: (this_TypeDefinitionResult_0= ruleTypeDefinitionResult | this_EquationIntermediateResult_1= ruleEquationIntermediateResult )
            int alt48=2;
            int LA48_0 = input.LA(1);

            if ( (LA48_0==57) ) {
                alt48=1;
            }
            else if ( (LA48_0==58) ) {
                alt48=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 48, 0, input);

                throw nvae;
            }
            switch (alt48) {
                case 1 :
                    // InternalConceptLanguage.g:3906:3: this_TypeDefinitionResult_0= ruleTypeDefinitionResult
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
                    // InternalConceptLanguage.g:3915:3: this_EquationIntermediateResult_1= ruleEquationIntermediateResult
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
    // InternalConceptLanguage.g:3927:1: entryRuleTypeDefinitionResult returns [EObject current=null] : iv_ruleTypeDefinitionResult= ruleTypeDefinitionResult EOF ;
    public final EObject entryRuleTypeDefinitionResult() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleTypeDefinitionResult = null;


        try {
            // InternalConceptLanguage.g:3927:61: (iv_ruleTypeDefinitionResult= ruleTypeDefinitionResult EOF )
            // InternalConceptLanguage.g:3928:2: iv_ruleTypeDefinitionResult= ruleTypeDefinitionResult EOF
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
    // InternalConceptLanguage.g:3934:1: ruleTypeDefinitionResult returns [EObject current=null] : ( () otherlv_1= 'Ref:' ( ( ruleQualifiedName ) ) ) ;
    public final EObject ruleTypeDefinitionResult() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;


        	enterRule();

        try {
            // InternalConceptLanguage.g:3940:2: ( ( () otherlv_1= 'Ref:' ( ( ruleQualifiedName ) ) ) )
            // InternalConceptLanguage.g:3941:2: ( () otherlv_1= 'Ref:' ( ( ruleQualifiedName ) ) )
            {
            // InternalConceptLanguage.g:3941:2: ( () otherlv_1= 'Ref:' ( ( ruleQualifiedName ) ) )
            // InternalConceptLanguage.g:3942:3: () otherlv_1= 'Ref:' ( ( ruleQualifiedName ) )
            {
            // InternalConceptLanguage.g:3942:3: ()
            // InternalConceptLanguage.g:3943:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getTypeDefinitionResultAccess().getTypeDefinitionResultAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,57,FOLLOW_3); 

            			newLeafNode(otherlv_1, grammarAccess.getTypeDefinitionResultAccess().getRefKeyword_1());
            		
            // InternalConceptLanguage.g:3953:3: ( ( ruleQualifiedName ) )
            // InternalConceptLanguage.g:3954:4: ( ruleQualifiedName )
            {
            // InternalConceptLanguage.g:3954:4: ( ruleQualifiedName )
            // InternalConceptLanguage.g:3955:5: ruleQualifiedName
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
    // InternalConceptLanguage.g:3973:1: entryRuleEquationIntermediateResult returns [EObject current=null] : iv_ruleEquationIntermediateResult= ruleEquationIntermediateResult EOF ;
    public final EObject entryRuleEquationIntermediateResult() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleEquationIntermediateResult = null;


        try {
            // InternalConceptLanguage.g:3973:67: (iv_ruleEquationIntermediateResult= ruleEquationIntermediateResult EOF )
            // InternalConceptLanguage.g:3974:2: iv_ruleEquationIntermediateResult= ruleEquationIntermediateResult EOF
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
    // InternalConceptLanguage.g:3980:1: ruleEquationIntermediateResult returns [EObject current=null] : ( () otherlv_1= 'Calc:' ( (lv_name_2_0= RULE_ID ) ) ) ;
    public final EObject ruleEquationIntermediateResult() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token lv_name_2_0=null;


        	enterRule();

        try {
            // InternalConceptLanguage.g:3986:2: ( ( () otherlv_1= 'Calc:' ( (lv_name_2_0= RULE_ID ) ) ) )
            // InternalConceptLanguage.g:3987:2: ( () otherlv_1= 'Calc:' ( (lv_name_2_0= RULE_ID ) ) )
            {
            // InternalConceptLanguage.g:3987:2: ( () otherlv_1= 'Calc:' ( (lv_name_2_0= RULE_ID ) ) )
            // InternalConceptLanguage.g:3988:3: () otherlv_1= 'Calc:' ( (lv_name_2_0= RULE_ID ) )
            {
            // InternalConceptLanguage.g:3988:3: ()
            // InternalConceptLanguage.g:3989:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getEquationIntermediateResultAccess().getEquationIntermediateResultAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,58,FOLLOW_3); 

            			newLeafNode(otherlv_1, grammarAccess.getEquationIntermediateResultAccess().getCalcKeyword_1());
            		
            // InternalConceptLanguage.g:3999:3: ( (lv_name_2_0= RULE_ID ) )
            // InternalConceptLanguage.g:4000:4: (lv_name_2_0= RULE_ID )
            {
            // InternalConceptLanguage.g:4000:4: (lv_name_2_0= RULE_ID )
            // InternalConceptLanguage.g:4001:5: lv_name_2_0= RULE_ID
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
    // InternalConceptLanguage.g:4021:1: entryRuleAdditionAndSubtraction returns [EObject current=null] : iv_ruleAdditionAndSubtraction= ruleAdditionAndSubtraction EOF ;
    public final EObject entryRuleAdditionAndSubtraction() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleAdditionAndSubtraction = null;


        try {
            // InternalConceptLanguage.g:4021:63: (iv_ruleAdditionAndSubtraction= ruleAdditionAndSubtraction EOF )
            // InternalConceptLanguage.g:4022:2: iv_ruleAdditionAndSubtraction= ruleAdditionAndSubtraction EOF
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
    // InternalConceptLanguage.g:4028:1: ruleAdditionAndSubtraction returns [EObject current=null] : (this_MultiplicationAndDivision_0= ruleMultiplicationAndDivision ( () ( ( (lv_operator_2_1= ruleOperatorPlus | lv_operator_2_2= ruleOperatorMinus ) ) ) ( (lv_right_3_0= ruleMultiplicationAndDivision ) ) )* ) ;
    public final EObject ruleAdditionAndSubtraction() throws RecognitionException {
        EObject current = null;

        EObject this_MultiplicationAndDivision_0 = null;

        Enumerator lv_operator_2_1 = null;

        Enumerator lv_operator_2_2 = null;

        EObject lv_right_3_0 = null;



        	enterRule();

        try {
            // InternalConceptLanguage.g:4034:2: ( (this_MultiplicationAndDivision_0= ruleMultiplicationAndDivision ( () ( ( (lv_operator_2_1= ruleOperatorPlus | lv_operator_2_2= ruleOperatorMinus ) ) ) ( (lv_right_3_0= ruleMultiplicationAndDivision ) ) )* ) )
            // InternalConceptLanguage.g:4035:2: (this_MultiplicationAndDivision_0= ruleMultiplicationAndDivision ( () ( ( (lv_operator_2_1= ruleOperatorPlus | lv_operator_2_2= ruleOperatorMinus ) ) ) ( (lv_right_3_0= ruleMultiplicationAndDivision ) ) )* )
            {
            // InternalConceptLanguage.g:4035:2: (this_MultiplicationAndDivision_0= ruleMultiplicationAndDivision ( () ( ( (lv_operator_2_1= ruleOperatorPlus | lv_operator_2_2= ruleOperatorMinus ) ) ) ( (lv_right_3_0= ruleMultiplicationAndDivision ) ) )* )
            // InternalConceptLanguage.g:4036:3: this_MultiplicationAndDivision_0= ruleMultiplicationAndDivision ( () ( ( (lv_operator_2_1= ruleOperatorPlus | lv_operator_2_2= ruleOperatorMinus ) ) ) ( (lv_right_3_0= ruleMultiplicationAndDivision ) ) )*
            {

            			newCompositeNode(grammarAccess.getAdditionAndSubtractionAccess().getMultiplicationAndDivisionParserRuleCall_0());
            		
            pushFollow(FOLLOW_51);
            this_MultiplicationAndDivision_0=ruleMultiplicationAndDivision();

            state._fsp--;


            			current = this_MultiplicationAndDivision_0;
            			afterParserOrEnumRuleCall();
            		
            // InternalConceptLanguage.g:4044:3: ( () ( ( (lv_operator_2_1= ruleOperatorPlus | lv_operator_2_2= ruleOperatorMinus ) ) ) ( (lv_right_3_0= ruleMultiplicationAndDivision ) ) )*
            loop50:
            do {
                int alt50=2;
                int LA50_0 = input.LA(1);

                if ( (LA50_0==64||LA50_0==68) ) {
                    alt50=1;
                }


                switch (alt50) {
            	case 1 :
            	    // InternalConceptLanguage.g:4045:4: () ( ( (lv_operator_2_1= ruleOperatorPlus | lv_operator_2_2= ruleOperatorMinus ) ) ) ( (lv_right_3_0= ruleMultiplicationAndDivision ) )
            	    {
            	    // InternalConceptLanguage.g:4045:4: ()
            	    // InternalConceptLanguage.g:4046:5: 
            	    {

            	    					current = forceCreateModelElementAndSet(
            	    						grammarAccess.getAdditionAndSubtractionAccess().getAdditionAndSubtractionLeftAction_1_0(),
            	    						current);
            	    				

            	    }

            	    // InternalConceptLanguage.g:4052:4: ( ( (lv_operator_2_1= ruleOperatorPlus | lv_operator_2_2= ruleOperatorMinus ) ) )
            	    // InternalConceptLanguage.g:4053:5: ( (lv_operator_2_1= ruleOperatorPlus | lv_operator_2_2= ruleOperatorMinus ) )
            	    {
            	    // InternalConceptLanguage.g:4053:5: ( (lv_operator_2_1= ruleOperatorPlus | lv_operator_2_2= ruleOperatorMinus ) )
            	    // InternalConceptLanguage.g:4054:6: (lv_operator_2_1= ruleOperatorPlus | lv_operator_2_2= ruleOperatorMinus )
            	    {
            	    // InternalConceptLanguage.g:4054:6: (lv_operator_2_1= ruleOperatorPlus | lv_operator_2_2= ruleOperatorMinus )
            	    int alt49=2;
            	    int LA49_0 = input.LA(1);

            	    if ( (LA49_0==68) ) {
            	        alt49=1;
            	    }
            	    else if ( (LA49_0==64) ) {
            	        alt49=2;
            	    }
            	    else {
            	        NoViableAltException nvae =
            	            new NoViableAltException("", 49, 0, input);

            	        throw nvae;
            	    }
            	    switch (alt49) {
            	        case 1 :
            	            // InternalConceptLanguage.g:4055:7: lv_operator_2_1= ruleOperatorPlus
            	            {

            	            							newCompositeNode(grammarAccess.getAdditionAndSubtractionAccess().getOperatorOperatorPlusEnumRuleCall_1_1_0_0());
            	            						
            	            pushFollow(FOLLOW_50);
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
            	            // InternalConceptLanguage.g:4071:7: lv_operator_2_2= ruleOperatorMinus
            	            {

            	            							newCompositeNode(grammarAccess.getAdditionAndSubtractionAccess().getOperatorOperatorMinusEnumRuleCall_1_1_0_1());
            	            						
            	            pushFollow(FOLLOW_50);
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

            	    // InternalConceptLanguage.g:4089:4: ( (lv_right_3_0= ruleMultiplicationAndDivision ) )
            	    // InternalConceptLanguage.g:4090:5: (lv_right_3_0= ruleMultiplicationAndDivision )
            	    {
            	    // InternalConceptLanguage.g:4090:5: (lv_right_3_0= ruleMultiplicationAndDivision )
            	    // InternalConceptLanguage.g:4091:6: lv_right_3_0= ruleMultiplicationAndDivision
            	    {

            	    						newCompositeNode(grammarAccess.getAdditionAndSubtractionAccess().getRightMultiplicationAndDivisionParserRuleCall_1_2_0());
            	    					
            	    pushFollow(FOLLOW_51);
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
            	    break loop50;
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
    // InternalConceptLanguage.g:4113:1: entryRuleMultiplicationAndDivision returns [EObject current=null] : iv_ruleMultiplicationAndDivision= ruleMultiplicationAndDivision EOF ;
    public final EObject entryRuleMultiplicationAndDivision() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleMultiplicationAndDivision = null;


        try {
            // InternalConceptLanguage.g:4113:66: (iv_ruleMultiplicationAndDivision= ruleMultiplicationAndDivision EOF )
            // InternalConceptLanguage.g:4114:2: iv_ruleMultiplicationAndDivision= ruleMultiplicationAndDivision EOF
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
    // InternalConceptLanguage.g:4120:1: ruleMultiplicationAndDivision returns [EObject current=null] : (this_PowerFunction_0= rulePowerFunction ( () ( ( (lv_operator_2_1= ruleOperatorMultiply | lv_operator_2_2= ruleOperatorDivide ) ) ) ( (lv_right_3_0= rulePowerFunction ) ) )* ) ;
    public final EObject ruleMultiplicationAndDivision() throws RecognitionException {
        EObject current = null;

        EObject this_PowerFunction_0 = null;

        Enumerator lv_operator_2_1 = null;

        Enumerator lv_operator_2_2 = null;

        EObject lv_right_3_0 = null;



        	enterRule();

        try {
            // InternalConceptLanguage.g:4126:2: ( (this_PowerFunction_0= rulePowerFunction ( () ( ( (lv_operator_2_1= ruleOperatorMultiply | lv_operator_2_2= ruleOperatorDivide ) ) ) ( (lv_right_3_0= rulePowerFunction ) ) )* ) )
            // InternalConceptLanguage.g:4127:2: (this_PowerFunction_0= rulePowerFunction ( () ( ( (lv_operator_2_1= ruleOperatorMultiply | lv_operator_2_2= ruleOperatorDivide ) ) ) ( (lv_right_3_0= rulePowerFunction ) ) )* )
            {
            // InternalConceptLanguage.g:4127:2: (this_PowerFunction_0= rulePowerFunction ( () ( ( (lv_operator_2_1= ruleOperatorMultiply | lv_operator_2_2= ruleOperatorDivide ) ) ) ( (lv_right_3_0= rulePowerFunction ) ) )* )
            // InternalConceptLanguage.g:4128:3: this_PowerFunction_0= rulePowerFunction ( () ( ( (lv_operator_2_1= ruleOperatorMultiply | lv_operator_2_2= ruleOperatorDivide ) ) ) ( (lv_right_3_0= rulePowerFunction ) ) )*
            {

            			newCompositeNode(grammarAccess.getMultiplicationAndDivisionAccess().getPowerFunctionParserRuleCall_0());
            		
            pushFollow(FOLLOW_52);
            this_PowerFunction_0=rulePowerFunction();

            state._fsp--;


            			current = this_PowerFunction_0;
            			afterParserOrEnumRuleCall();
            		
            // InternalConceptLanguage.g:4136:3: ( () ( ( (lv_operator_2_1= ruleOperatorMultiply | lv_operator_2_2= ruleOperatorDivide ) ) ) ( (lv_right_3_0= rulePowerFunction ) ) )*
            loop52:
            do {
                int alt52=2;
                int LA52_0 = input.LA(1);

                if ( ((LA52_0>=69 && LA52_0<=70)) ) {
                    alt52=1;
                }


                switch (alt52) {
            	case 1 :
            	    // InternalConceptLanguage.g:4137:4: () ( ( (lv_operator_2_1= ruleOperatorMultiply | lv_operator_2_2= ruleOperatorDivide ) ) ) ( (lv_right_3_0= rulePowerFunction ) )
            	    {
            	    // InternalConceptLanguage.g:4137:4: ()
            	    // InternalConceptLanguage.g:4138:5: 
            	    {

            	    					current = forceCreateModelElementAndSet(
            	    						grammarAccess.getMultiplicationAndDivisionAccess().getMultiplicationAndDivisionLeftAction_1_0(),
            	    						current);
            	    				

            	    }

            	    // InternalConceptLanguage.g:4144:4: ( ( (lv_operator_2_1= ruleOperatorMultiply | lv_operator_2_2= ruleOperatorDivide ) ) )
            	    // InternalConceptLanguage.g:4145:5: ( (lv_operator_2_1= ruleOperatorMultiply | lv_operator_2_2= ruleOperatorDivide ) )
            	    {
            	    // InternalConceptLanguage.g:4145:5: ( (lv_operator_2_1= ruleOperatorMultiply | lv_operator_2_2= ruleOperatorDivide ) )
            	    // InternalConceptLanguage.g:4146:6: (lv_operator_2_1= ruleOperatorMultiply | lv_operator_2_2= ruleOperatorDivide )
            	    {
            	    // InternalConceptLanguage.g:4146:6: (lv_operator_2_1= ruleOperatorMultiply | lv_operator_2_2= ruleOperatorDivide )
            	    int alt51=2;
            	    int LA51_0 = input.LA(1);

            	    if ( (LA51_0==69) ) {
            	        alt51=1;
            	    }
            	    else if ( (LA51_0==70) ) {
            	        alt51=2;
            	    }
            	    else {
            	        NoViableAltException nvae =
            	            new NoViableAltException("", 51, 0, input);

            	        throw nvae;
            	    }
            	    switch (alt51) {
            	        case 1 :
            	            // InternalConceptLanguage.g:4147:7: lv_operator_2_1= ruleOperatorMultiply
            	            {

            	            							newCompositeNode(grammarAccess.getMultiplicationAndDivisionAccess().getOperatorOperatorMultiplyEnumRuleCall_1_1_0_0());
            	            						
            	            pushFollow(FOLLOW_50);
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
            	            // InternalConceptLanguage.g:4163:7: lv_operator_2_2= ruleOperatorDivide
            	            {

            	            							newCompositeNode(grammarAccess.getMultiplicationAndDivisionAccess().getOperatorOperatorDivideEnumRuleCall_1_1_0_1());
            	            						
            	            pushFollow(FOLLOW_50);
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

            	    // InternalConceptLanguage.g:4181:4: ( (lv_right_3_0= rulePowerFunction ) )
            	    // InternalConceptLanguage.g:4182:5: (lv_right_3_0= rulePowerFunction )
            	    {
            	    // InternalConceptLanguage.g:4182:5: (lv_right_3_0= rulePowerFunction )
            	    // InternalConceptLanguage.g:4183:6: lv_right_3_0= rulePowerFunction
            	    {

            	    						newCompositeNode(grammarAccess.getMultiplicationAndDivisionAccess().getRightPowerFunctionParserRuleCall_1_2_0());
            	    					
            	    pushFollow(FOLLOW_52);
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
            	    break loop52;
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
    // InternalConceptLanguage.g:4205:1: entryRulePowerFunction returns [EObject current=null] : iv_rulePowerFunction= rulePowerFunction EOF ;
    public final EObject entryRulePowerFunction() throws RecognitionException {
        EObject current = null;

        EObject iv_rulePowerFunction = null;


        try {
            // InternalConceptLanguage.g:4205:54: (iv_rulePowerFunction= rulePowerFunction EOF )
            // InternalConceptLanguage.g:4206:2: iv_rulePowerFunction= rulePowerFunction EOF
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
    // InternalConceptLanguage.g:4212:1: rulePowerFunction returns [EObject current=null] : (this_AExpression_0= ruleAExpression ( () ( (lv_operator_2_0= ruleOperatorPower ) ) ( (lv_right_3_0= ruleAExpression ) ) )* ) ;
    public final EObject rulePowerFunction() throws RecognitionException {
        EObject current = null;

        EObject this_AExpression_0 = null;

        Enumerator lv_operator_2_0 = null;

        EObject lv_right_3_0 = null;



        	enterRule();

        try {
            // InternalConceptLanguage.g:4218:2: ( (this_AExpression_0= ruleAExpression ( () ( (lv_operator_2_0= ruleOperatorPower ) ) ( (lv_right_3_0= ruleAExpression ) ) )* ) )
            // InternalConceptLanguage.g:4219:2: (this_AExpression_0= ruleAExpression ( () ( (lv_operator_2_0= ruleOperatorPower ) ) ( (lv_right_3_0= ruleAExpression ) ) )* )
            {
            // InternalConceptLanguage.g:4219:2: (this_AExpression_0= ruleAExpression ( () ( (lv_operator_2_0= ruleOperatorPower ) ) ( (lv_right_3_0= ruleAExpression ) ) )* )
            // InternalConceptLanguage.g:4220:3: this_AExpression_0= ruleAExpression ( () ( (lv_operator_2_0= ruleOperatorPower ) ) ( (lv_right_3_0= ruleAExpression ) ) )*
            {

            			newCompositeNode(grammarAccess.getPowerFunctionAccess().getAExpressionParserRuleCall_0());
            		
            pushFollow(FOLLOW_53);
            this_AExpression_0=ruleAExpression();

            state._fsp--;


            			current = this_AExpression_0;
            			afterParserOrEnumRuleCall();
            		
            // InternalConceptLanguage.g:4228:3: ( () ( (lv_operator_2_0= ruleOperatorPower ) ) ( (lv_right_3_0= ruleAExpression ) ) )*
            loop53:
            do {
                int alt53=2;
                int LA53_0 = input.LA(1);

                if ( (LA53_0==71) ) {
                    alt53=1;
                }


                switch (alt53) {
            	case 1 :
            	    // InternalConceptLanguage.g:4229:4: () ( (lv_operator_2_0= ruleOperatorPower ) ) ( (lv_right_3_0= ruleAExpression ) )
            	    {
            	    // InternalConceptLanguage.g:4229:4: ()
            	    // InternalConceptLanguage.g:4230:5: 
            	    {

            	    					current = forceCreateModelElementAndSet(
            	    						grammarAccess.getPowerFunctionAccess().getPowerFunctionLeftAction_1_0(),
            	    						current);
            	    				

            	    }

            	    // InternalConceptLanguage.g:4236:4: ( (lv_operator_2_0= ruleOperatorPower ) )
            	    // InternalConceptLanguage.g:4237:5: (lv_operator_2_0= ruleOperatorPower )
            	    {
            	    // InternalConceptLanguage.g:4237:5: (lv_operator_2_0= ruleOperatorPower )
            	    // InternalConceptLanguage.g:4238:6: lv_operator_2_0= ruleOperatorPower
            	    {

            	    						newCompositeNode(grammarAccess.getPowerFunctionAccess().getOperatorOperatorPowerEnumRuleCall_1_1_0());
            	    					
            	    pushFollow(FOLLOW_50);
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

            	    // InternalConceptLanguage.g:4255:4: ( (lv_right_3_0= ruleAExpression ) )
            	    // InternalConceptLanguage.g:4256:5: (lv_right_3_0= ruleAExpression )
            	    {
            	    // InternalConceptLanguage.g:4256:5: (lv_right_3_0= ruleAExpression )
            	    // InternalConceptLanguage.g:4257:6: lv_right_3_0= ruleAExpression
            	    {

            	    						newCompositeNode(grammarAccess.getPowerFunctionAccess().getRightAExpressionParserRuleCall_1_2_0());
            	    					
            	    pushFollow(FOLLOW_53);
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
            	    break loop53;
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
    // InternalConceptLanguage.g:4279:1: entryRuleParenthesis returns [EObject current=null] : iv_ruleParenthesis= ruleParenthesis EOF ;
    public final EObject entryRuleParenthesis() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleParenthesis = null;


        try {
            // InternalConceptLanguage.g:4279:52: (iv_ruleParenthesis= ruleParenthesis EOF )
            // InternalConceptLanguage.g:4280:2: iv_ruleParenthesis= ruleParenthesis EOF
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
    // InternalConceptLanguage.g:4286:1: ruleParenthesis returns [EObject current=null] : ( () ( (lv_operator_1_0= ruleOperatorMinus ) )? otherlv_2= '(' ( (lv_right_3_0= ruleAdditionAndSubtraction ) ) otherlv_4= ')' ) ;
    public final EObject ruleParenthesis() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        Token otherlv_4=null;
        Enumerator lv_operator_1_0 = null;

        EObject lv_right_3_0 = null;



        	enterRule();

        try {
            // InternalConceptLanguage.g:4292:2: ( ( () ( (lv_operator_1_0= ruleOperatorMinus ) )? otherlv_2= '(' ( (lv_right_3_0= ruleAdditionAndSubtraction ) ) otherlv_4= ')' ) )
            // InternalConceptLanguage.g:4293:2: ( () ( (lv_operator_1_0= ruleOperatorMinus ) )? otherlv_2= '(' ( (lv_right_3_0= ruleAdditionAndSubtraction ) ) otherlv_4= ')' )
            {
            // InternalConceptLanguage.g:4293:2: ( () ( (lv_operator_1_0= ruleOperatorMinus ) )? otherlv_2= '(' ( (lv_right_3_0= ruleAdditionAndSubtraction ) ) otherlv_4= ')' )
            // InternalConceptLanguage.g:4294:3: () ( (lv_operator_1_0= ruleOperatorMinus ) )? otherlv_2= '(' ( (lv_right_3_0= ruleAdditionAndSubtraction ) ) otherlv_4= ')'
            {
            // InternalConceptLanguage.g:4294:3: ()
            // InternalConceptLanguage.g:4295:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getParenthesisAccess().getParenthesisAction_0(),
            					current);
            			

            }

            // InternalConceptLanguage.g:4301:3: ( (lv_operator_1_0= ruleOperatorMinus ) )?
            int alt54=2;
            int LA54_0 = input.LA(1);

            if ( (LA54_0==64) ) {
                alt54=1;
            }
            switch (alt54) {
                case 1 :
                    // InternalConceptLanguage.g:4302:4: (lv_operator_1_0= ruleOperatorMinus )
                    {
                    // InternalConceptLanguage.g:4302:4: (lv_operator_1_0= ruleOperatorMinus )
                    // InternalConceptLanguage.g:4303:5: lv_operator_1_0= ruleOperatorMinus
                    {

                    					newCompositeNode(grammarAccess.getParenthesisAccess().getOperatorOperatorMinusEnumRuleCall_1_0());
                    				
                    pushFollow(FOLLOW_54);
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

            otherlv_2=(Token)match(input,59,FOLLOW_50); 

            			newLeafNode(otherlv_2, grammarAccess.getParenthesisAccess().getLeftParenthesisKeyword_2());
            		
            // InternalConceptLanguage.g:4324:3: ( (lv_right_3_0= ruleAdditionAndSubtraction ) )
            // InternalConceptLanguage.g:4325:4: (lv_right_3_0= ruleAdditionAndSubtraction )
            {
            // InternalConceptLanguage.g:4325:4: (lv_right_3_0= ruleAdditionAndSubtraction )
            // InternalConceptLanguage.g:4326:5: lv_right_3_0= ruleAdditionAndSubtraction
            {

            					newCompositeNode(grammarAccess.getParenthesisAccess().getRightAdditionAndSubtractionParserRuleCall_3_0());
            				
            pushFollow(FOLLOW_55);
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

            otherlv_4=(Token)match(input,60,FOLLOW_2); 

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
    // InternalConceptLanguage.g:4351:1: entryRuleAExpression returns [EObject current=null] : iv_ruleAExpression= ruleAExpression EOF ;
    public final EObject entryRuleAExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleAExpression = null;


        try {
            // InternalConceptLanguage.g:4351:52: (iv_ruleAExpression= ruleAExpression EOF )
            // InternalConceptLanguage.g:4352:2: iv_ruleAExpression= ruleAExpression EOF
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
    // InternalConceptLanguage.g:4358:1: ruleAExpression returns [EObject current=null] : (this_ALiteral_0= ruleALiteral | this_ReferencedDefinitionInput_1= ruleReferencedDefinitionInput | this_Parenthesis_2= ruleParenthesis | this_Function_3= ruleFunction | this_AAdvancedFunction_4= ruleAAdvancedFunction ) ;
    public final EObject ruleAExpression() throws RecognitionException {
        EObject current = null;

        EObject this_ALiteral_0 = null;

        EObject this_ReferencedDefinitionInput_1 = null;

        EObject this_Parenthesis_2 = null;

        EObject this_Function_3 = null;

        EObject this_AAdvancedFunction_4 = null;



        	enterRule();

        try {
            // InternalConceptLanguage.g:4364:2: ( (this_ALiteral_0= ruleALiteral | this_ReferencedDefinitionInput_1= ruleReferencedDefinitionInput | this_Parenthesis_2= ruleParenthesis | this_Function_3= ruleFunction | this_AAdvancedFunction_4= ruleAAdvancedFunction ) )
            // InternalConceptLanguage.g:4365:2: (this_ALiteral_0= ruleALiteral | this_ReferencedDefinitionInput_1= ruleReferencedDefinitionInput | this_Parenthesis_2= ruleParenthesis | this_Function_3= ruleFunction | this_AAdvancedFunction_4= ruleAAdvancedFunction )
            {
            // InternalConceptLanguage.g:4365:2: (this_ALiteral_0= ruleALiteral | this_ReferencedDefinitionInput_1= ruleReferencedDefinitionInput | this_Parenthesis_2= ruleParenthesis | this_Function_3= ruleFunction | this_AAdvancedFunction_4= ruleAAdvancedFunction )
            int alt55=5;
            switch ( input.LA(1) ) {
            case 64:
                {
                int LA55_1 = input.LA(2);

                if ( (LA55_1==RULE_INT) ) {
                    alt55=1;
                }
                else if ( (LA55_1==59) ) {
                    alt55=3;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 55, 1, input);

                    throw nvae;
                }
                }
                break;
            case RULE_INT:
            case 61:
            case 62:
                {
                alt55=1;
                }
                break;
            case RULE_ID:
                {
                int LA55_3 = input.LA(2);

                if ( (LA55_3==17||LA55_3==59) ) {
                    alt55=5;
                }
                else if ( (LA55_3==EOF||LA55_3==22||LA55_3==26||LA55_3==60||(LA55_3>=63 && LA55_3<=64)||(LA55_3>=68 && LA55_3<=71)) ) {
                    alt55=2;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 55, 3, input);

                    throw nvae;
                }
                }
                break;
            case 59:
                {
                alt55=3;
                }
                break;
            case 72:
            case 73:
            case 74:
            case 75:
            case 76:
            case 77:
            case 78:
            case 79:
            case 80:
            case 81:
            case 82:
                {
                alt55=4;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 55, 0, input);

                throw nvae;
            }

            switch (alt55) {
                case 1 :
                    // InternalConceptLanguage.g:4366:3: this_ALiteral_0= ruleALiteral
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
                    // InternalConceptLanguage.g:4375:3: this_ReferencedDefinitionInput_1= ruleReferencedDefinitionInput
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
                    // InternalConceptLanguage.g:4384:3: this_Parenthesis_2= ruleParenthesis
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
                    // InternalConceptLanguage.g:4393:3: this_Function_3= ruleFunction
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
                    // InternalConceptLanguage.g:4402:3: this_AAdvancedFunction_4= ruleAAdvancedFunction
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
    // InternalConceptLanguage.g:4414:1: entryRuleReferencedDefinitionInput returns [EObject current=null] : iv_ruleReferencedDefinitionInput= ruleReferencedDefinitionInput EOF ;
    public final EObject entryRuleReferencedDefinitionInput() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleReferencedDefinitionInput = null;


        try {
            // InternalConceptLanguage.g:4414:66: (iv_ruleReferencedDefinitionInput= ruleReferencedDefinitionInput EOF )
            // InternalConceptLanguage.g:4415:2: iv_ruleReferencedDefinitionInput= ruleReferencedDefinitionInput EOF
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
    // InternalConceptLanguage.g:4421:1: ruleReferencedDefinitionInput returns [EObject current=null] : ( ( ruleQualifiedName ) ) ;
    public final EObject ruleReferencedDefinitionInput() throws RecognitionException {
        EObject current = null;


        	enterRule();

        try {
            // InternalConceptLanguage.g:4427:2: ( ( ( ruleQualifiedName ) ) )
            // InternalConceptLanguage.g:4428:2: ( ( ruleQualifiedName ) )
            {
            // InternalConceptLanguage.g:4428:2: ( ( ruleQualifiedName ) )
            // InternalConceptLanguage.g:4429:3: ( ruleQualifiedName )
            {
            // InternalConceptLanguage.g:4429:3: ( ruleQualifiedName )
            // InternalConceptLanguage.g:4430:4: ruleQualifiedName
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
    // InternalConceptLanguage.g:4447:1: entryRuleALiteral returns [EObject current=null] : iv_ruleALiteral= ruleALiteral EOF ;
    public final EObject entryRuleALiteral() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleALiteral = null;


        try {
            // InternalConceptLanguage.g:4447:49: (iv_ruleALiteral= ruleALiteral EOF )
            // InternalConceptLanguage.g:4448:2: iv_ruleALiteral= ruleALiteral EOF
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
    // InternalConceptLanguage.g:4454:1: ruleALiteral returns [EObject current=null] : (this_NumberLiteral_0= ruleNumberLiteral | this_ValueE_1= ruleValueE | this_ValuePi_2= ruleValuePi ) ;
    public final EObject ruleALiteral() throws RecognitionException {
        EObject current = null;

        EObject this_NumberLiteral_0 = null;

        EObject this_ValueE_1 = null;

        EObject this_ValuePi_2 = null;



        	enterRule();

        try {
            // InternalConceptLanguage.g:4460:2: ( (this_NumberLiteral_0= ruleNumberLiteral | this_ValueE_1= ruleValueE | this_ValuePi_2= ruleValuePi ) )
            // InternalConceptLanguage.g:4461:2: (this_NumberLiteral_0= ruleNumberLiteral | this_ValueE_1= ruleValueE | this_ValuePi_2= ruleValuePi )
            {
            // InternalConceptLanguage.g:4461:2: (this_NumberLiteral_0= ruleNumberLiteral | this_ValueE_1= ruleValueE | this_ValuePi_2= ruleValuePi )
            int alt56=3;
            switch ( input.LA(1) ) {
            case RULE_INT:
            case 64:
                {
                alt56=1;
                }
                break;
            case 62:
                {
                alt56=2;
                }
                break;
            case 61:
                {
                alt56=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 56, 0, input);

                throw nvae;
            }

            switch (alt56) {
                case 1 :
                    // InternalConceptLanguage.g:4462:3: this_NumberLiteral_0= ruleNumberLiteral
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
                    // InternalConceptLanguage.g:4471:3: this_ValueE_1= ruleValueE
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
                    // InternalConceptLanguage.g:4480:3: this_ValuePi_2= ruleValuePi
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
    // InternalConceptLanguage.g:4492:1: entryRuleFunction returns [EObject current=null] : iv_ruleFunction= ruleFunction EOF ;
    public final EObject entryRuleFunction() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleFunction = null;


        try {
            // InternalConceptLanguage.g:4492:49: (iv_ruleFunction= ruleFunction EOF )
            // InternalConceptLanguage.g:4493:2: iv_ruleFunction= ruleFunction EOF
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
    // InternalConceptLanguage.g:4499:1: ruleFunction returns [EObject current=null] : ( () ( ( (lv_operator_1_1= ruleOperatorCos | lv_operator_1_2= ruleOperatorSin | lv_operator_1_3= ruleOperatorTan | lv_operator_1_4= ruleOperatorAtan | lv_operator_1_5= ruleOperatorAcos | lv_operator_1_6= ruleOperatorAsin | lv_operator_1_7= ruleOperatorSqrt | lv_operator_1_8= ruleOperatorLog | lv_operator_1_9= ruleOperatorLn | lv_operator_1_10= ruleOperatorLd | lv_operator_1_11= ruleOperatorExp ) ) ) otherlv_2= '(' ( (lv_right_3_0= ruleAdditionAndSubtraction ) ) otherlv_4= ')' ) ;
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
            // InternalConceptLanguage.g:4505:2: ( ( () ( ( (lv_operator_1_1= ruleOperatorCos | lv_operator_1_2= ruleOperatorSin | lv_operator_1_3= ruleOperatorTan | lv_operator_1_4= ruleOperatorAtan | lv_operator_1_5= ruleOperatorAcos | lv_operator_1_6= ruleOperatorAsin | lv_operator_1_7= ruleOperatorSqrt | lv_operator_1_8= ruleOperatorLog | lv_operator_1_9= ruleOperatorLn | lv_operator_1_10= ruleOperatorLd | lv_operator_1_11= ruleOperatorExp ) ) ) otherlv_2= '(' ( (lv_right_3_0= ruleAdditionAndSubtraction ) ) otherlv_4= ')' ) )
            // InternalConceptLanguage.g:4506:2: ( () ( ( (lv_operator_1_1= ruleOperatorCos | lv_operator_1_2= ruleOperatorSin | lv_operator_1_3= ruleOperatorTan | lv_operator_1_4= ruleOperatorAtan | lv_operator_1_5= ruleOperatorAcos | lv_operator_1_6= ruleOperatorAsin | lv_operator_1_7= ruleOperatorSqrt | lv_operator_1_8= ruleOperatorLog | lv_operator_1_9= ruleOperatorLn | lv_operator_1_10= ruleOperatorLd | lv_operator_1_11= ruleOperatorExp ) ) ) otherlv_2= '(' ( (lv_right_3_0= ruleAdditionAndSubtraction ) ) otherlv_4= ')' )
            {
            // InternalConceptLanguage.g:4506:2: ( () ( ( (lv_operator_1_1= ruleOperatorCos | lv_operator_1_2= ruleOperatorSin | lv_operator_1_3= ruleOperatorTan | lv_operator_1_4= ruleOperatorAtan | lv_operator_1_5= ruleOperatorAcos | lv_operator_1_6= ruleOperatorAsin | lv_operator_1_7= ruleOperatorSqrt | lv_operator_1_8= ruleOperatorLog | lv_operator_1_9= ruleOperatorLn | lv_operator_1_10= ruleOperatorLd | lv_operator_1_11= ruleOperatorExp ) ) ) otherlv_2= '(' ( (lv_right_3_0= ruleAdditionAndSubtraction ) ) otherlv_4= ')' )
            // InternalConceptLanguage.g:4507:3: () ( ( (lv_operator_1_1= ruleOperatorCos | lv_operator_1_2= ruleOperatorSin | lv_operator_1_3= ruleOperatorTan | lv_operator_1_4= ruleOperatorAtan | lv_operator_1_5= ruleOperatorAcos | lv_operator_1_6= ruleOperatorAsin | lv_operator_1_7= ruleOperatorSqrt | lv_operator_1_8= ruleOperatorLog | lv_operator_1_9= ruleOperatorLn | lv_operator_1_10= ruleOperatorLd | lv_operator_1_11= ruleOperatorExp ) ) ) otherlv_2= '(' ( (lv_right_3_0= ruleAdditionAndSubtraction ) ) otherlv_4= ')'
            {
            // InternalConceptLanguage.g:4507:3: ()
            // InternalConceptLanguage.g:4508:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getFunctionAccess().getFunctionAction_0(),
            					current);
            			

            }

            // InternalConceptLanguage.g:4514:3: ( ( (lv_operator_1_1= ruleOperatorCos | lv_operator_1_2= ruleOperatorSin | lv_operator_1_3= ruleOperatorTan | lv_operator_1_4= ruleOperatorAtan | lv_operator_1_5= ruleOperatorAcos | lv_operator_1_6= ruleOperatorAsin | lv_operator_1_7= ruleOperatorSqrt | lv_operator_1_8= ruleOperatorLog | lv_operator_1_9= ruleOperatorLn | lv_operator_1_10= ruleOperatorLd | lv_operator_1_11= ruleOperatorExp ) ) )
            // InternalConceptLanguage.g:4515:4: ( (lv_operator_1_1= ruleOperatorCos | lv_operator_1_2= ruleOperatorSin | lv_operator_1_3= ruleOperatorTan | lv_operator_1_4= ruleOperatorAtan | lv_operator_1_5= ruleOperatorAcos | lv_operator_1_6= ruleOperatorAsin | lv_operator_1_7= ruleOperatorSqrt | lv_operator_1_8= ruleOperatorLog | lv_operator_1_9= ruleOperatorLn | lv_operator_1_10= ruleOperatorLd | lv_operator_1_11= ruleOperatorExp ) )
            {
            // InternalConceptLanguage.g:4515:4: ( (lv_operator_1_1= ruleOperatorCos | lv_operator_1_2= ruleOperatorSin | lv_operator_1_3= ruleOperatorTan | lv_operator_1_4= ruleOperatorAtan | lv_operator_1_5= ruleOperatorAcos | lv_operator_1_6= ruleOperatorAsin | lv_operator_1_7= ruleOperatorSqrt | lv_operator_1_8= ruleOperatorLog | lv_operator_1_9= ruleOperatorLn | lv_operator_1_10= ruleOperatorLd | lv_operator_1_11= ruleOperatorExp ) )
            // InternalConceptLanguage.g:4516:5: (lv_operator_1_1= ruleOperatorCos | lv_operator_1_2= ruleOperatorSin | lv_operator_1_3= ruleOperatorTan | lv_operator_1_4= ruleOperatorAtan | lv_operator_1_5= ruleOperatorAcos | lv_operator_1_6= ruleOperatorAsin | lv_operator_1_7= ruleOperatorSqrt | lv_operator_1_8= ruleOperatorLog | lv_operator_1_9= ruleOperatorLn | lv_operator_1_10= ruleOperatorLd | lv_operator_1_11= ruleOperatorExp )
            {
            // InternalConceptLanguage.g:4516:5: (lv_operator_1_1= ruleOperatorCos | lv_operator_1_2= ruleOperatorSin | lv_operator_1_3= ruleOperatorTan | lv_operator_1_4= ruleOperatorAtan | lv_operator_1_5= ruleOperatorAcos | lv_operator_1_6= ruleOperatorAsin | lv_operator_1_7= ruleOperatorSqrt | lv_operator_1_8= ruleOperatorLog | lv_operator_1_9= ruleOperatorLn | lv_operator_1_10= ruleOperatorLd | lv_operator_1_11= ruleOperatorExp )
            int alt57=11;
            switch ( input.LA(1) ) {
            case 72:
                {
                alt57=1;
                }
                break;
            case 73:
                {
                alt57=2;
                }
                break;
            case 74:
                {
                alt57=3;
                }
                break;
            case 75:
                {
                alt57=4;
                }
                break;
            case 76:
                {
                alt57=5;
                }
                break;
            case 77:
                {
                alt57=6;
                }
                break;
            case 78:
                {
                alt57=7;
                }
                break;
            case 79:
                {
                alt57=8;
                }
                break;
            case 80:
                {
                alt57=9;
                }
                break;
            case 82:
                {
                alt57=10;
                }
                break;
            case 81:
                {
                alt57=11;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 57, 0, input);

                throw nvae;
            }

            switch (alt57) {
                case 1 :
                    // InternalConceptLanguage.g:4517:6: lv_operator_1_1= ruleOperatorCos
                    {

                    						newCompositeNode(grammarAccess.getFunctionAccess().getOperatorOperatorCosEnumRuleCall_1_0_0());
                    					
                    pushFollow(FOLLOW_54);
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
                    // InternalConceptLanguage.g:4533:6: lv_operator_1_2= ruleOperatorSin
                    {

                    						newCompositeNode(grammarAccess.getFunctionAccess().getOperatorOperatorSinEnumRuleCall_1_0_1());
                    					
                    pushFollow(FOLLOW_54);
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
                    // InternalConceptLanguage.g:4549:6: lv_operator_1_3= ruleOperatorTan
                    {

                    						newCompositeNode(grammarAccess.getFunctionAccess().getOperatorOperatorTanEnumRuleCall_1_0_2());
                    					
                    pushFollow(FOLLOW_54);
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
                    // InternalConceptLanguage.g:4565:6: lv_operator_1_4= ruleOperatorAtan
                    {

                    						newCompositeNode(grammarAccess.getFunctionAccess().getOperatorOperatorAtanEnumRuleCall_1_0_3());
                    					
                    pushFollow(FOLLOW_54);
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
                    // InternalConceptLanguage.g:4581:6: lv_operator_1_5= ruleOperatorAcos
                    {

                    						newCompositeNode(grammarAccess.getFunctionAccess().getOperatorOperatorAcosEnumRuleCall_1_0_4());
                    					
                    pushFollow(FOLLOW_54);
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
                    // InternalConceptLanguage.g:4597:6: lv_operator_1_6= ruleOperatorAsin
                    {

                    						newCompositeNode(grammarAccess.getFunctionAccess().getOperatorOperatorAsinEnumRuleCall_1_0_5());
                    					
                    pushFollow(FOLLOW_54);
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
                    // InternalConceptLanguage.g:4613:6: lv_operator_1_7= ruleOperatorSqrt
                    {

                    						newCompositeNode(grammarAccess.getFunctionAccess().getOperatorOperatorSqrtEnumRuleCall_1_0_6());
                    					
                    pushFollow(FOLLOW_54);
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
                    // InternalConceptLanguage.g:4629:6: lv_operator_1_8= ruleOperatorLog
                    {

                    						newCompositeNode(grammarAccess.getFunctionAccess().getOperatorOperatorLogEnumRuleCall_1_0_7());
                    					
                    pushFollow(FOLLOW_54);
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
                    // InternalConceptLanguage.g:4645:6: lv_operator_1_9= ruleOperatorLn
                    {

                    						newCompositeNode(grammarAccess.getFunctionAccess().getOperatorOperatorLnEnumRuleCall_1_0_8());
                    					
                    pushFollow(FOLLOW_54);
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
                    // InternalConceptLanguage.g:4661:6: lv_operator_1_10= ruleOperatorLd
                    {

                    						newCompositeNode(grammarAccess.getFunctionAccess().getOperatorOperatorLdEnumRuleCall_1_0_9());
                    					
                    pushFollow(FOLLOW_54);
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
                    // InternalConceptLanguage.g:4677:6: lv_operator_1_11= ruleOperatorExp
                    {

                    						newCompositeNode(grammarAccess.getFunctionAccess().getOperatorOperatorExpEnumRuleCall_1_0_10());
                    					
                    pushFollow(FOLLOW_54);
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

            otherlv_2=(Token)match(input,59,FOLLOW_50); 

            			newLeafNode(otherlv_2, grammarAccess.getFunctionAccess().getLeftParenthesisKeyword_2());
            		
            // InternalConceptLanguage.g:4699:3: ( (lv_right_3_0= ruleAdditionAndSubtraction ) )
            // InternalConceptLanguage.g:4700:4: (lv_right_3_0= ruleAdditionAndSubtraction )
            {
            // InternalConceptLanguage.g:4700:4: (lv_right_3_0= ruleAdditionAndSubtraction )
            // InternalConceptLanguage.g:4701:5: lv_right_3_0= ruleAdditionAndSubtraction
            {

            					newCompositeNode(grammarAccess.getFunctionAccess().getRightAdditionAndSubtractionParserRuleCall_3_0());
            				
            pushFollow(FOLLOW_55);
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

            otherlv_4=(Token)match(input,60,FOLLOW_2); 

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
    // InternalConceptLanguage.g:4726:1: entryRuleAAdvancedFunction returns [EObject current=null] : iv_ruleAAdvancedFunction= ruleAAdvancedFunction EOF ;
    public final EObject entryRuleAAdvancedFunction() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleAAdvancedFunction = null;


        try {
            // InternalConceptLanguage.g:4726:58: (iv_ruleAAdvancedFunction= ruleAAdvancedFunction EOF )
            // InternalConceptLanguage.g:4727:2: iv_ruleAAdvancedFunction= ruleAAdvancedFunction EOF
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
    // InternalConceptLanguage.g:4733:1: ruleAAdvancedFunction returns [EObject current=null] : (this_AdvancedFunction_0= ruleAdvancedFunction | this_SetFunction_1= ruleSetFunction ) ;
    public final EObject ruleAAdvancedFunction() throws RecognitionException {
        EObject current = null;

        EObject this_AdvancedFunction_0 = null;

        EObject this_SetFunction_1 = null;



        	enterRule();

        try {
            // InternalConceptLanguage.g:4739:2: ( (this_AdvancedFunction_0= ruleAdvancedFunction | this_SetFunction_1= ruleSetFunction ) )
            // InternalConceptLanguage.g:4740:2: (this_AdvancedFunction_0= ruleAdvancedFunction | this_SetFunction_1= ruleSetFunction )
            {
            // InternalConceptLanguage.g:4740:2: (this_AdvancedFunction_0= ruleAdvancedFunction | this_SetFunction_1= ruleSetFunction )
            int alt58=2;
            int LA58_0 = input.LA(1);

            if ( (LA58_0==RULE_ID) ) {
                int LA58_1 = input.LA(2);

                if ( (LA58_1==59) ) {
                    alt58=1;
                }
                else if ( (LA58_1==17) ) {
                    alt58=2;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 58, 1, input);

                    throw nvae;
                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 58, 0, input);

                throw nvae;
            }
            switch (alt58) {
                case 1 :
                    // InternalConceptLanguage.g:4741:3: this_AdvancedFunction_0= ruleAdvancedFunction
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
                    // InternalConceptLanguage.g:4750:3: this_SetFunction_1= ruleSetFunction
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
    // InternalConceptLanguage.g:4762:1: entryRuleAdvancedFunction returns [EObject current=null] : iv_ruleAdvancedFunction= ruleAdvancedFunction EOF ;
    public final EObject entryRuleAdvancedFunction() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleAdvancedFunction = null;


        try {
            // InternalConceptLanguage.g:4762:57: (iv_ruleAdvancedFunction= ruleAdvancedFunction EOF )
            // InternalConceptLanguage.g:4763:2: iv_ruleAdvancedFunction= ruleAdvancedFunction EOF
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
    // InternalConceptLanguage.g:4769:1: ruleAdvancedFunction returns [EObject current=null] : ( () ( (lv_operator_1_0= RULE_ID ) ) otherlv_2= '(' ( (lv_inputs_3_0= ruleAdditionAndSubtraction ) ) (otherlv_4= ',' ( (lv_inputs_5_0= ruleAdditionAndSubtraction ) ) )* otherlv_6= ')' ) ;
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
            // InternalConceptLanguage.g:4775:2: ( ( () ( (lv_operator_1_0= RULE_ID ) ) otherlv_2= '(' ( (lv_inputs_3_0= ruleAdditionAndSubtraction ) ) (otherlv_4= ',' ( (lv_inputs_5_0= ruleAdditionAndSubtraction ) ) )* otherlv_6= ')' ) )
            // InternalConceptLanguage.g:4776:2: ( () ( (lv_operator_1_0= RULE_ID ) ) otherlv_2= '(' ( (lv_inputs_3_0= ruleAdditionAndSubtraction ) ) (otherlv_4= ',' ( (lv_inputs_5_0= ruleAdditionAndSubtraction ) ) )* otherlv_6= ')' )
            {
            // InternalConceptLanguage.g:4776:2: ( () ( (lv_operator_1_0= RULE_ID ) ) otherlv_2= '(' ( (lv_inputs_3_0= ruleAdditionAndSubtraction ) ) (otherlv_4= ',' ( (lv_inputs_5_0= ruleAdditionAndSubtraction ) ) )* otherlv_6= ')' )
            // InternalConceptLanguage.g:4777:3: () ( (lv_operator_1_0= RULE_ID ) ) otherlv_2= '(' ( (lv_inputs_3_0= ruleAdditionAndSubtraction ) ) (otherlv_4= ',' ( (lv_inputs_5_0= ruleAdditionAndSubtraction ) ) )* otherlv_6= ')'
            {
            // InternalConceptLanguage.g:4777:3: ()
            // InternalConceptLanguage.g:4778:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getAdvancedFunctionAccess().getAdvancedFunctionAction_0(),
            					current);
            			

            }

            // InternalConceptLanguage.g:4784:3: ( (lv_operator_1_0= RULE_ID ) )
            // InternalConceptLanguage.g:4785:4: (lv_operator_1_0= RULE_ID )
            {
            // InternalConceptLanguage.g:4785:4: (lv_operator_1_0= RULE_ID )
            // InternalConceptLanguage.g:4786:5: lv_operator_1_0= RULE_ID
            {
            lv_operator_1_0=(Token)match(input,RULE_ID,FOLLOW_54); 

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

            otherlv_2=(Token)match(input,59,FOLLOW_50); 

            			newLeafNode(otherlv_2, grammarAccess.getAdvancedFunctionAccess().getLeftParenthesisKeyword_2());
            		
            // InternalConceptLanguage.g:4806:3: ( (lv_inputs_3_0= ruleAdditionAndSubtraction ) )
            // InternalConceptLanguage.g:4807:4: (lv_inputs_3_0= ruleAdditionAndSubtraction )
            {
            // InternalConceptLanguage.g:4807:4: (lv_inputs_3_0= ruleAdditionAndSubtraction )
            // InternalConceptLanguage.g:4808:5: lv_inputs_3_0= ruleAdditionAndSubtraction
            {

            					newCompositeNode(grammarAccess.getAdvancedFunctionAccess().getInputsAdditionAndSubtractionParserRuleCall_3_0());
            				
            pushFollow(FOLLOW_56);
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

            // InternalConceptLanguage.g:4825:3: (otherlv_4= ',' ( (lv_inputs_5_0= ruleAdditionAndSubtraction ) ) )*
            loop59:
            do {
                int alt59=2;
                int LA59_0 = input.LA(1);

                if ( (LA59_0==26) ) {
                    alt59=1;
                }


                switch (alt59) {
            	case 1 :
            	    // InternalConceptLanguage.g:4826:4: otherlv_4= ',' ( (lv_inputs_5_0= ruleAdditionAndSubtraction ) )
            	    {
            	    otherlv_4=(Token)match(input,26,FOLLOW_50); 

            	    				newLeafNode(otherlv_4, grammarAccess.getAdvancedFunctionAccess().getCommaKeyword_4_0());
            	    			
            	    // InternalConceptLanguage.g:4830:4: ( (lv_inputs_5_0= ruleAdditionAndSubtraction ) )
            	    // InternalConceptLanguage.g:4831:5: (lv_inputs_5_0= ruleAdditionAndSubtraction )
            	    {
            	    // InternalConceptLanguage.g:4831:5: (lv_inputs_5_0= ruleAdditionAndSubtraction )
            	    // InternalConceptLanguage.g:4832:6: lv_inputs_5_0= ruleAdditionAndSubtraction
            	    {

            	    						newCompositeNode(grammarAccess.getAdvancedFunctionAccess().getInputsAdditionAndSubtractionParserRuleCall_4_1_0());
            	    					
            	    pushFollow(FOLLOW_56);
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
            	    break loop59;
                }
            } while (true);

            otherlv_6=(Token)match(input,60,FOLLOW_2); 

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
    // InternalConceptLanguage.g:4858:1: entryRuleSetFunction returns [EObject current=null] : iv_ruleSetFunction= ruleSetFunction EOF ;
    public final EObject entryRuleSetFunction() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleSetFunction = null;


        try {
            // InternalConceptLanguage.g:4858:52: (iv_ruleSetFunction= ruleSetFunction EOF )
            // InternalConceptLanguage.g:4859:2: iv_ruleSetFunction= ruleSetFunction EOF
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
    // InternalConceptLanguage.g:4865:1: ruleSetFunction returns [EObject current=null] : ( () ( (lv_operator_1_0= RULE_ID ) ) otherlv_2= '{' ( ( ruleQualifiedName ) ) (otherlv_4= ',' ( (lv_depth_5_0= RULE_INT ) ) )? (otherlv_6= ',' ( (lv_filterName_7_0= RULE_ID ) ) )? otherlv_8= '}' ) ;
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
            // InternalConceptLanguage.g:4871:2: ( ( () ( (lv_operator_1_0= RULE_ID ) ) otherlv_2= '{' ( ( ruleQualifiedName ) ) (otherlv_4= ',' ( (lv_depth_5_0= RULE_INT ) ) )? (otherlv_6= ',' ( (lv_filterName_7_0= RULE_ID ) ) )? otherlv_8= '}' ) )
            // InternalConceptLanguage.g:4872:2: ( () ( (lv_operator_1_0= RULE_ID ) ) otherlv_2= '{' ( ( ruleQualifiedName ) ) (otherlv_4= ',' ( (lv_depth_5_0= RULE_INT ) ) )? (otherlv_6= ',' ( (lv_filterName_7_0= RULE_ID ) ) )? otherlv_8= '}' )
            {
            // InternalConceptLanguage.g:4872:2: ( () ( (lv_operator_1_0= RULE_ID ) ) otherlv_2= '{' ( ( ruleQualifiedName ) ) (otherlv_4= ',' ( (lv_depth_5_0= RULE_INT ) ) )? (otherlv_6= ',' ( (lv_filterName_7_0= RULE_ID ) ) )? otherlv_8= '}' )
            // InternalConceptLanguage.g:4873:3: () ( (lv_operator_1_0= RULE_ID ) ) otherlv_2= '{' ( ( ruleQualifiedName ) ) (otherlv_4= ',' ( (lv_depth_5_0= RULE_INT ) ) )? (otherlv_6= ',' ( (lv_filterName_7_0= RULE_ID ) ) )? otherlv_8= '}'
            {
            // InternalConceptLanguage.g:4873:3: ()
            // InternalConceptLanguage.g:4874:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getSetFunctionAccess().getSetFunctionAction_0(),
            					current);
            			

            }

            // InternalConceptLanguage.g:4880:3: ( (lv_operator_1_0= RULE_ID ) )
            // InternalConceptLanguage.g:4881:4: (lv_operator_1_0= RULE_ID )
            {
            // InternalConceptLanguage.g:4881:4: (lv_operator_1_0= RULE_ID )
            // InternalConceptLanguage.g:4882:5: lv_operator_1_0= RULE_ID
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

            otherlv_2=(Token)match(input,17,FOLLOW_3); 

            			newLeafNode(otherlv_2, grammarAccess.getSetFunctionAccess().getLeftCurlyBracketKeyword_2());
            		
            // InternalConceptLanguage.g:4902:3: ( ( ruleQualifiedName ) )
            // InternalConceptLanguage.g:4903:4: ( ruleQualifiedName )
            {
            // InternalConceptLanguage.g:4903:4: ( ruleQualifiedName )
            // InternalConceptLanguage.g:4904:5: ruleQualifiedName
            {

            					if (current==null) {
            						current = createModelElement(grammarAccess.getSetFunctionRule());
            					}
            				

            					newCompositeNode(grammarAccess.getSetFunctionAccess().getTypeDefinitionATypeDefinitionCrossReference_3_0());
            				
            pushFollow(FOLLOW_57);
            ruleQualifiedName();

            state._fsp--;


            					afterParserOrEnumRuleCall();
            				

            }


            }

            // InternalConceptLanguage.g:4918:3: (otherlv_4= ',' ( (lv_depth_5_0= RULE_INT ) ) )?
            int alt60=2;
            int LA60_0 = input.LA(1);

            if ( (LA60_0==26) ) {
                int LA60_1 = input.LA(2);

                if ( (LA60_1==RULE_INT) ) {
                    alt60=1;
                }
            }
            switch (alt60) {
                case 1 :
                    // InternalConceptLanguage.g:4919:4: otherlv_4= ',' ( (lv_depth_5_0= RULE_INT ) )
                    {
                    otherlv_4=(Token)match(input,26,FOLLOW_6); 

                    				newLeafNode(otherlv_4, grammarAccess.getSetFunctionAccess().getCommaKeyword_4_0());
                    			
                    // InternalConceptLanguage.g:4923:4: ( (lv_depth_5_0= RULE_INT ) )
                    // InternalConceptLanguage.g:4924:5: (lv_depth_5_0= RULE_INT )
                    {
                    // InternalConceptLanguage.g:4924:5: (lv_depth_5_0= RULE_INT )
                    // InternalConceptLanguage.g:4925:6: lv_depth_5_0= RULE_INT
                    {
                    lv_depth_5_0=(Token)match(input,RULE_INT,FOLLOW_57); 

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

            // InternalConceptLanguage.g:4942:3: (otherlv_6= ',' ( (lv_filterName_7_0= RULE_ID ) ) )?
            int alt61=2;
            int LA61_0 = input.LA(1);

            if ( (LA61_0==26) ) {
                alt61=1;
            }
            switch (alt61) {
                case 1 :
                    // InternalConceptLanguage.g:4943:4: otherlv_6= ',' ( (lv_filterName_7_0= RULE_ID ) )
                    {
                    otherlv_6=(Token)match(input,26,FOLLOW_3); 

                    				newLeafNode(otherlv_6, grammarAccess.getSetFunctionAccess().getCommaKeyword_5_0());
                    			
                    // InternalConceptLanguage.g:4947:4: ( (lv_filterName_7_0= RULE_ID ) )
                    // InternalConceptLanguage.g:4948:5: (lv_filterName_7_0= RULE_ID )
                    {
                    // InternalConceptLanguage.g:4948:5: (lv_filterName_7_0= RULE_ID )
                    // InternalConceptLanguage.g:4949:6: lv_filterName_7_0= RULE_ID
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

            otherlv_8=(Token)match(input,18,FOLLOW_2); 

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
    // InternalConceptLanguage.g:4974:1: entryRuleNumberLiteral returns [EObject current=null] : iv_ruleNumberLiteral= ruleNumberLiteral EOF ;
    public final EObject entryRuleNumberLiteral() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleNumberLiteral = null;


        try {
            // InternalConceptLanguage.g:4974:54: (iv_ruleNumberLiteral= ruleNumberLiteral EOF )
            // InternalConceptLanguage.g:4975:2: iv_ruleNumberLiteral= ruleNumberLiteral EOF
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
    // InternalConceptLanguage.g:4981:1: ruleNumberLiteral returns [EObject current=null] : ( () ( (lv_value_1_0= ruleFloatLiteralString ) ) ) ;
    public final EObject ruleNumberLiteral() throws RecognitionException {
        EObject current = null;

        AntlrDatatypeRuleToken lv_value_1_0 = null;



        	enterRule();

        try {
            // InternalConceptLanguage.g:4987:2: ( ( () ( (lv_value_1_0= ruleFloatLiteralString ) ) ) )
            // InternalConceptLanguage.g:4988:2: ( () ( (lv_value_1_0= ruleFloatLiteralString ) ) )
            {
            // InternalConceptLanguage.g:4988:2: ( () ( (lv_value_1_0= ruleFloatLiteralString ) ) )
            // InternalConceptLanguage.g:4989:3: () ( (lv_value_1_0= ruleFloatLiteralString ) )
            {
            // InternalConceptLanguage.g:4989:3: ()
            // InternalConceptLanguage.g:4990:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getNumberLiteralAccess().getNumberLiteralAction_0(),
            					current);
            			

            }

            // InternalConceptLanguage.g:4996:3: ( (lv_value_1_0= ruleFloatLiteralString ) )
            // InternalConceptLanguage.g:4997:4: (lv_value_1_0= ruleFloatLiteralString )
            {
            // InternalConceptLanguage.g:4997:4: (lv_value_1_0= ruleFloatLiteralString )
            // InternalConceptLanguage.g:4998:5: lv_value_1_0= ruleFloatLiteralString
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
    // InternalConceptLanguage.g:5019:1: entryRuleValuePi returns [EObject current=null] : iv_ruleValuePi= ruleValuePi EOF ;
    public final EObject entryRuleValuePi() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleValuePi = null;


        try {
            // InternalConceptLanguage.g:5019:48: (iv_ruleValuePi= ruleValuePi EOF )
            // InternalConceptLanguage.g:5020:2: iv_ruleValuePi= ruleValuePi EOF
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
    // InternalConceptLanguage.g:5026:1: ruleValuePi returns [EObject current=null] : ( () otherlv_1= 'pi' ) ;
    public final EObject ruleValuePi() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;


        	enterRule();

        try {
            // InternalConceptLanguage.g:5032:2: ( ( () otherlv_1= 'pi' ) )
            // InternalConceptLanguage.g:5033:2: ( () otherlv_1= 'pi' )
            {
            // InternalConceptLanguage.g:5033:2: ( () otherlv_1= 'pi' )
            // InternalConceptLanguage.g:5034:3: () otherlv_1= 'pi'
            {
            // InternalConceptLanguage.g:5034:3: ()
            // InternalConceptLanguage.g:5035:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getValuePiAccess().getValuePiAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,61,FOLLOW_2); 

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
    // InternalConceptLanguage.g:5049:1: entryRuleValueE returns [EObject current=null] : iv_ruleValueE= ruleValueE EOF ;
    public final EObject entryRuleValueE() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleValueE = null;


        try {
            // InternalConceptLanguage.g:5049:47: (iv_ruleValueE= ruleValueE EOF )
            // InternalConceptLanguage.g:5050:2: iv_ruleValueE= ruleValueE EOF
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
    // InternalConceptLanguage.g:5056:1: ruleValueE returns [EObject current=null] : ( () otherlv_1= 'e' ) ;
    public final EObject ruleValueE() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;


        	enterRule();

        try {
            // InternalConceptLanguage.g:5062:2: ( ( () otherlv_1= 'e' ) )
            // InternalConceptLanguage.g:5063:2: ( () otherlv_1= 'e' )
            {
            // InternalConceptLanguage.g:5063:2: ( () otherlv_1= 'e' )
            // InternalConceptLanguage.g:5064:3: () otherlv_1= 'e'
            {
            // InternalConceptLanguage.g:5064:3: ()
            // InternalConceptLanguage.g:5065:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getValueEAccess().getValueEAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,62,FOLLOW_2); 

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
    // InternalConceptLanguage.g:5079:1: entryRuleVersion returns [String current=null] : iv_ruleVersion= ruleVersion EOF ;
    public final String entryRuleVersion() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleVersion = null;


        try {
            // InternalConceptLanguage.g:5079:47: (iv_ruleVersion= ruleVersion EOF )
            // InternalConceptLanguage.g:5080:2: iv_ruleVersion= ruleVersion EOF
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
    // InternalConceptLanguage.g:5086:1: ruleVersion returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (this_INT_0= RULE_INT (kw= '.' this_INT_2= RULE_INT )* ) ;
    public final AntlrDatatypeRuleToken ruleVersion() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_INT_0=null;
        Token kw=null;
        Token this_INT_2=null;


        	enterRule();

        try {
            // InternalConceptLanguage.g:5092:2: ( (this_INT_0= RULE_INT (kw= '.' this_INT_2= RULE_INT )* ) )
            // InternalConceptLanguage.g:5093:2: (this_INT_0= RULE_INT (kw= '.' this_INT_2= RULE_INT )* )
            {
            // InternalConceptLanguage.g:5093:2: (this_INT_0= RULE_INT (kw= '.' this_INT_2= RULE_INT )* )
            // InternalConceptLanguage.g:5094:3: this_INT_0= RULE_INT (kw= '.' this_INT_2= RULE_INT )*
            {
            this_INT_0=(Token)match(input,RULE_INT,FOLLOW_58); 

            			current.merge(this_INT_0);
            		

            			newLeafNode(this_INT_0, grammarAccess.getVersionAccess().getINTTerminalRuleCall_0());
            		
            // InternalConceptLanguage.g:5101:3: (kw= '.' this_INT_2= RULE_INT )*
            loop62:
            do {
                int alt62=2;
                int LA62_0 = input.LA(1);

                if ( (LA62_0==63) ) {
                    alt62=1;
                }


                switch (alt62) {
            	case 1 :
            	    // InternalConceptLanguage.g:5102:4: kw= '.' this_INT_2= RULE_INT
            	    {
            	    kw=(Token)match(input,63,FOLLOW_6); 

            	    				current.merge(kw);
            	    				newLeafNode(kw, grammarAccess.getVersionAccess().getFullStopKeyword_1_0());
            	    			
            	    this_INT_2=(Token)match(input,RULE_INT,FOLLOW_58); 

            	    				current.merge(this_INT_2);
            	    			

            	    				newLeafNode(this_INT_2, grammarAccess.getVersionAccess().getINTTerminalRuleCall_1_1());
            	    			

            	    }
            	    break;

            	default :
            	    break loop62;
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
    // InternalConceptLanguage.g:5119:1: entryRuleIntLiteralString returns [String current=null] : iv_ruleIntLiteralString= ruleIntLiteralString EOF ;
    public final String entryRuleIntLiteralString() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleIntLiteralString = null;


        try {
            // InternalConceptLanguage.g:5119:56: (iv_ruleIntLiteralString= ruleIntLiteralString EOF )
            // InternalConceptLanguage.g:5120:2: iv_ruleIntLiteralString= ruleIntLiteralString EOF
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
    // InternalConceptLanguage.g:5126:1: ruleIntLiteralString returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : ( (kw= '-' )? (this_INT_1= RULE_INT )? ) ;
    public final AntlrDatatypeRuleToken ruleIntLiteralString() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;
        Token this_INT_1=null;


        	enterRule();

        try {
            // InternalConceptLanguage.g:5132:2: ( ( (kw= '-' )? (this_INT_1= RULE_INT )? ) )
            // InternalConceptLanguage.g:5133:2: ( (kw= '-' )? (this_INT_1= RULE_INT )? )
            {
            // InternalConceptLanguage.g:5133:2: ( (kw= '-' )? (this_INT_1= RULE_INT )? )
            // InternalConceptLanguage.g:5134:3: (kw= '-' )? (this_INT_1= RULE_INT )?
            {
            // InternalConceptLanguage.g:5134:3: (kw= '-' )?
            int alt63=2;
            int LA63_0 = input.LA(1);

            if ( (LA63_0==64) ) {
                alt63=1;
            }
            switch (alt63) {
                case 1 :
                    // InternalConceptLanguage.g:5135:4: kw= '-'
                    {
                    kw=(Token)match(input,64,FOLLOW_59); 

                    				current.merge(kw);
                    				newLeafNode(kw, grammarAccess.getIntLiteralStringAccess().getHyphenMinusKeyword_0());
                    			

                    }
                    break;

            }

            // InternalConceptLanguage.g:5141:3: (this_INT_1= RULE_INT )?
            int alt64=2;
            int LA64_0 = input.LA(1);

            if ( (LA64_0==RULE_INT) ) {
                alt64=1;
            }
            switch (alt64) {
                case 1 :
                    // InternalConceptLanguage.g:5142:4: this_INT_1= RULE_INT
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
    // InternalConceptLanguage.g:5154:1: entryRuleFloatLiteralString returns [String current=null] : iv_ruleFloatLiteralString= ruleFloatLiteralString EOF ;
    public final String entryRuleFloatLiteralString() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleFloatLiteralString = null;


        try {
            // InternalConceptLanguage.g:5154:58: (iv_ruleFloatLiteralString= ruleFloatLiteralString EOF )
            // InternalConceptLanguage.g:5155:2: iv_ruleFloatLiteralString= ruleFloatLiteralString EOF
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
    // InternalConceptLanguage.g:5161:1: ruleFloatLiteralString returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : ( (kw= '-' )? this_INT_1= RULE_INT (kw= '.' this_INT_3= RULE_INT )? ) ;
    public final AntlrDatatypeRuleToken ruleFloatLiteralString() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;
        Token this_INT_1=null;
        Token this_INT_3=null;


        	enterRule();

        try {
            // InternalConceptLanguage.g:5167:2: ( ( (kw= '-' )? this_INT_1= RULE_INT (kw= '.' this_INT_3= RULE_INT )? ) )
            // InternalConceptLanguage.g:5168:2: ( (kw= '-' )? this_INT_1= RULE_INT (kw= '.' this_INT_3= RULE_INT )? )
            {
            // InternalConceptLanguage.g:5168:2: ( (kw= '-' )? this_INT_1= RULE_INT (kw= '.' this_INT_3= RULE_INT )? )
            // InternalConceptLanguage.g:5169:3: (kw= '-' )? this_INT_1= RULE_INT (kw= '.' this_INT_3= RULE_INT )?
            {
            // InternalConceptLanguage.g:5169:3: (kw= '-' )?
            int alt65=2;
            int LA65_0 = input.LA(1);

            if ( (LA65_0==64) ) {
                alt65=1;
            }
            switch (alt65) {
                case 1 :
                    // InternalConceptLanguage.g:5170:4: kw= '-'
                    {
                    kw=(Token)match(input,64,FOLLOW_6); 

                    				current.merge(kw);
                    				newLeafNode(kw, grammarAccess.getFloatLiteralStringAccess().getHyphenMinusKeyword_0());
                    			

                    }
                    break;

            }

            this_INT_1=(Token)match(input,RULE_INT,FOLLOW_58); 

            			current.merge(this_INT_1);
            		

            			newLeafNode(this_INT_1, grammarAccess.getFloatLiteralStringAccess().getINTTerminalRuleCall_1());
            		
            // InternalConceptLanguage.g:5183:3: (kw= '.' this_INT_3= RULE_INT )?
            int alt66=2;
            int LA66_0 = input.LA(1);

            if ( (LA66_0==63) ) {
                alt66=1;
            }
            switch (alt66) {
                case 1 :
                    // InternalConceptLanguage.g:5184:4: kw= '.' this_INT_3= RULE_INT
                    {
                    kw=(Token)match(input,63,FOLLOW_6); 

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
    // InternalConceptLanguage.g:5201:1: entryRuleBooleanLiteralString returns [String current=null] : iv_ruleBooleanLiteralString= ruleBooleanLiteralString EOF ;
    public final String entryRuleBooleanLiteralString() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleBooleanLiteralString = null;


        try {
            // InternalConceptLanguage.g:5201:60: (iv_ruleBooleanLiteralString= ruleBooleanLiteralString EOF )
            // InternalConceptLanguage.g:5202:2: iv_ruleBooleanLiteralString= ruleBooleanLiteralString EOF
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
    // InternalConceptLanguage.g:5208:1: ruleBooleanLiteralString returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (kw= 'true' | kw= 'false' ) ;
    public final AntlrDatatypeRuleToken ruleBooleanLiteralString() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;


        	enterRule();

        try {
            // InternalConceptLanguage.g:5214:2: ( (kw= 'true' | kw= 'false' ) )
            // InternalConceptLanguage.g:5215:2: (kw= 'true' | kw= 'false' )
            {
            // InternalConceptLanguage.g:5215:2: (kw= 'true' | kw= 'false' )
            int alt67=2;
            int LA67_0 = input.LA(1);

            if ( (LA67_0==65) ) {
                alt67=1;
            }
            else if ( (LA67_0==66) ) {
                alt67=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 67, 0, input);

                throw nvae;
            }
            switch (alt67) {
                case 1 :
                    // InternalConceptLanguage.g:5216:3: kw= 'true'
                    {
                    kw=(Token)match(input,65,FOLLOW_2); 

                    			current.merge(kw);
                    			newLeafNode(kw, grammarAccess.getBooleanLiteralStringAccess().getTrueKeyword_0());
                    		

                    }
                    break;
                case 2 :
                    // InternalConceptLanguage.g:5222:3: kw= 'false'
                    {
                    kw=(Token)match(input,66,FOLLOW_2); 

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
    // InternalConceptLanguage.g:5231:1: entryRuleEString returns [String current=null] : iv_ruleEString= ruleEString EOF ;
    public final String entryRuleEString() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleEString = null;


        try {
            // InternalConceptLanguage.g:5231:47: (iv_ruleEString= ruleEString EOF )
            // InternalConceptLanguage.g:5232:2: iv_ruleEString= ruleEString EOF
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
    // InternalConceptLanguage.g:5238:1: ruleEString returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : this_STRING_0= RULE_STRING ;
    public final AntlrDatatypeRuleToken ruleEString() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_STRING_0=null;


        	enterRule();

        try {
            // InternalConceptLanguage.g:5244:2: (this_STRING_0= RULE_STRING )
            // InternalConceptLanguage.g:5245:2: this_STRING_0= RULE_STRING
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
    // InternalConceptLanguage.g:5255:1: entryRuleQualifiedNameWithWildcard returns [String current=null] : iv_ruleQualifiedNameWithWildcard= ruleQualifiedNameWithWildcard EOF ;
    public final String entryRuleQualifiedNameWithWildcard() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleQualifiedNameWithWildcard = null;


        try {
            // InternalConceptLanguage.g:5255:65: (iv_ruleQualifiedNameWithWildcard= ruleQualifiedNameWithWildcard EOF )
            // InternalConceptLanguage.g:5256:2: iv_ruleQualifiedNameWithWildcard= ruleQualifiedNameWithWildcard EOF
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
    // InternalConceptLanguage.g:5262:1: ruleQualifiedNameWithWildcard returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (this_QualifiedName_0= ruleQualifiedName (kw= '.*' )? ) ;
    public final AntlrDatatypeRuleToken ruleQualifiedNameWithWildcard() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;
        AntlrDatatypeRuleToken this_QualifiedName_0 = null;



        	enterRule();

        try {
            // InternalConceptLanguage.g:5268:2: ( (this_QualifiedName_0= ruleQualifiedName (kw= '.*' )? ) )
            // InternalConceptLanguage.g:5269:2: (this_QualifiedName_0= ruleQualifiedName (kw= '.*' )? )
            {
            // InternalConceptLanguage.g:5269:2: (this_QualifiedName_0= ruleQualifiedName (kw= '.*' )? )
            // InternalConceptLanguage.g:5270:3: this_QualifiedName_0= ruleQualifiedName (kw= '.*' )?
            {

            			newCompositeNode(grammarAccess.getQualifiedNameWithWildcardAccess().getQualifiedNameParserRuleCall_0());
            		
            pushFollow(FOLLOW_60);
            this_QualifiedName_0=ruleQualifiedName();

            state._fsp--;


            			current.merge(this_QualifiedName_0);
            		

            			afterParserOrEnumRuleCall();
            		
            // InternalConceptLanguage.g:5280:3: (kw= '.*' )?
            int alt68=2;
            int LA68_0 = input.LA(1);

            if ( (LA68_0==67) ) {
                alt68=1;
            }
            switch (alt68) {
                case 1 :
                    // InternalConceptLanguage.g:5281:4: kw= '.*'
                    {
                    kw=(Token)match(input,67,FOLLOW_2); 

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
    // InternalConceptLanguage.g:5291:1: entryRuleQualifiedName returns [String current=null] : iv_ruleQualifiedName= ruleQualifiedName EOF ;
    public final String entryRuleQualifiedName() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleQualifiedName = null;


        try {
            // InternalConceptLanguage.g:5291:53: (iv_ruleQualifiedName= ruleQualifiedName EOF )
            // InternalConceptLanguage.g:5292:2: iv_ruleQualifiedName= ruleQualifiedName EOF
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
    // InternalConceptLanguage.g:5298:1: ruleQualifiedName returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (this_ID_0= RULE_ID (kw= '.' this_ID_2= RULE_ID )* ) ;
    public final AntlrDatatypeRuleToken ruleQualifiedName() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_ID_0=null;
        Token kw=null;
        Token this_ID_2=null;


        	enterRule();

        try {
            // InternalConceptLanguage.g:5304:2: ( (this_ID_0= RULE_ID (kw= '.' this_ID_2= RULE_ID )* ) )
            // InternalConceptLanguage.g:5305:2: (this_ID_0= RULE_ID (kw= '.' this_ID_2= RULE_ID )* )
            {
            // InternalConceptLanguage.g:5305:2: (this_ID_0= RULE_ID (kw= '.' this_ID_2= RULE_ID )* )
            // InternalConceptLanguage.g:5306:3: this_ID_0= RULE_ID (kw= '.' this_ID_2= RULE_ID )*
            {
            this_ID_0=(Token)match(input,RULE_ID,FOLLOW_58); 

            			current.merge(this_ID_0);
            		

            			newLeafNode(this_ID_0, grammarAccess.getQualifiedNameAccess().getIDTerminalRuleCall_0());
            		
            // InternalConceptLanguage.g:5313:3: (kw= '.' this_ID_2= RULE_ID )*
            loop69:
            do {
                int alt69=2;
                int LA69_0 = input.LA(1);

                if ( (LA69_0==63) ) {
                    alt69=1;
                }


                switch (alt69) {
            	case 1 :
            	    // InternalConceptLanguage.g:5314:4: kw= '.' this_ID_2= RULE_ID
            	    {
            	    kw=(Token)match(input,63,FOLLOW_3); 

            	    				current.merge(kw);
            	    				newLeafNode(kw, grammarAccess.getQualifiedNameAccess().getFullStopKeyword_1_0());
            	    			
            	    this_ID_2=(Token)match(input,RULE_ID,FOLLOW_58); 

            	    				current.merge(this_ID_2);
            	    			

            	    				newLeafNode(this_ID_2, grammarAccess.getQualifiedNameAccess().getIDTerminalRuleCall_1_1());
            	    			

            	    }
            	    break;

            	default :
            	    break loop69;
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
    // InternalConceptLanguage.g:5331:1: ruleOperatorPlus returns [Enumerator current=null] : (enumLiteral_0= '+' ) ;
    public final Enumerator ruleOperatorPlus() throws RecognitionException {
        Enumerator current = null;

        Token enumLiteral_0=null;


        	enterRule();

        try {
            // InternalConceptLanguage.g:5337:2: ( (enumLiteral_0= '+' ) )
            // InternalConceptLanguage.g:5338:2: (enumLiteral_0= '+' )
            {
            // InternalConceptLanguage.g:5338:2: (enumLiteral_0= '+' )
            // InternalConceptLanguage.g:5339:3: enumLiteral_0= '+'
            {
            enumLiteral_0=(Token)match(input,68,FOLLOW_2); 

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
    // InternalConceptLanguage.g:5348:1: ruleOperatorMinus returns [Enumerator current=null] : (enumLiteral_0= '-' ) ;
    public final Enumerator ruleOperatorMinus() throws RecognitionException {
        Enumerator current = null;

        Token enumLiteral_0=null;


        	enterRule();

        try {
            // InternalConceptLanguage.g:5354:2: ( (enumLiteral_0= '-' ) )
            // InternalConceptLanguage.g:5355:2: (enumLiteral_0= '-' )
            {
            // InternalConceptLanguage.g:5355:2: (enumLiteral_0= '-' )
            // InternalConceptLanguage.g:5356:3: enumLiteral_0= '-'
            {
            enumLiteral_0=(Token)match(input,64,FOLLOW_2); 

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
    // InternalConceptLanguage.g:5365:1: ruleOperatorMultiply returns [Enumerator current=null] : (enumLiteral_0= '*' ) ;
    public final Enumerator ruleOperatorMultiply() throws RecognitionException {
        Enumerator current = null;

        Token enumLiteral_0=null;


        	enterRule();

        try {
            // InternalConceptLanguage.g:5371:2: ( (enumLiteral_0= '*' ) )
            // InternalConceptLanguage.g:5372:2: (enumLiteral_0= '*' )
            {
            // InternalConceptLanguage.g:5372:2: (enumLiteral_0= '*' )
            // InternalConceptLanguage.g:5373:3: enumLiteral_0= '*'
            {
            enumLiteral_0=(Token)match(input,69,FOLLOW_2); 

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
    // InternalConceptLanguage.g:5382:1: ruleOperatorDivide returns [Enumerator current=null] : (enumLiteral_0= '/' ) ;
    public final Enumerator ruleOperatorDivide() throws RecognitionException {
        Enumerator current = null;

        Token enumLiteral_0=null;


        	enterRule();

        try {
            // InternalConceptLanguage.g:5388:2: ( (enumLiteral_0= '/' ) )
            // InternalConceptLanguage.g:5389:2: (enumLiteral_0= '/' )
            {
            // InternalConceptLanguage.g:5389:2: (enumLiteral_0= '/' )
            // InternalConceptLanguage.g:5390:3: enumLiteral_0= '/'
            {
            enumLiteral_0=(Token)match(input,70,FOLLOW_2); 

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
    // InternalConceptLanguage.g:5399:1: ruleOperatorPower returns [Enumerator current=null] : (enumLiteral_0= '^' ) ;
    public final Enumerator ruleOperatorPower() throws RecognitionException {
        Enumerator current = null;

        Token enumLiteral_0=null;


        	enterRule();

        try {
            // InternalConceptLanguage.g:5405:2: ( (enumLiteral_0= '^' ) )
            // InternalConceptLanguage.g:5406:2: (enumLiteral_0= '^' )
            {
            // InternalConceptLanguage.g:5406:2: (enumLiteral_0= '^' )
            // InternalConceptLanguage.g:5407:3: enumLiteral_0= '^'
            {
            enumLiteral_0=(Token)match(input,71,FOLLOW_2); 

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
    // InternalConceptLanguage.g:5416:1: ruleOperatorCos returns [Enumerator current=null] : (enumLiteral_0= 'cos' ) ;
    public final Enumerator ruleOperatorCos() throws RecognitionException {
        Enumerator current = null;

        Token enumLiteral_0=null;


        	enterRule();

        try {
            // InternalConceptLanguage.g:5422:2: ( (enumLiteral_0= 'cos' ) )
            // InternalConceptLanguage.g:5423:2: (enumLiteral_0= 'cos' )
            {
            // InternalConceptLanguage.g:5423:2: (enumLiteral_0= 'cos' )
            // InternalConceptLanguage.g:5424:3: enumLiteral_0= 'cos'
            {
            enumLiteral_0=(Token)match(input,72,FOLLOW_2); 

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
    // InternalConceptLanguage.g:5433:1: ruleOperatorSin returns [Enumerator current=null] : (enumLiteral_0= 'sin' ) ;
    public final Enumerator ruleOperatorSin() throws RecognitionException {
        Enumerator current = null;

        Token enumLiteral_0=null;


        	enterRule();

        try {
            // InternalConceptLanguage.g:5439:2: ( (enumLiteral_0= 'sin' ) )
            // InternalConceptLanguage.g:5440:2: (enumLiteral_0= 'sin' )
            {
            // InternalConceptLanguage.g:5440:2: (enumLiteral_0= 'sin' )
            // InternalConceptLanguage.g:5441:3: enumLiteral_0= 'sin'
            {
            enumLiteral_0=(Token)match(input,73,FOLLOW_2); 

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
    // InternalConceptLanguage.g:5450:1: ruleOperatorTan returns [Enumerator current=null] : (enumLiteral_0= 'tan' ) ;
    public final Enumerator ruleOperatorTan() throws RecognitionException {
        Enumerator current = null;

        Token enumLiteral_0=null;


        	enterRule();

        try {
            // InternalConceptLanguage.g:5456:2: ( (enumLiteral_0= 'tan' ) )
            // InternalConceptLanguage.g:5457:2: (enumLiteral_0= 'tan' )
            {
            // InternalConceptLanguage.g:5457:2: (enumLiteral_0= 'tan' )
            // InternalConceptLanguage.g:5458:3: enumLiteral_0= 'tan'
            {
            enumLiteral_0=(Token)match(input,74,FOLLOW_2); 

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
    // InternalConceptLanguage.g:5467:1: ruleOperatorAtan returns [Enumerator current=null] : (enumLiteral_0= 'atan' ) ;
    public final Enumerator ruleOperatorAtan() throws RecognitionException {
        Enumerator current = null;

        Token enumLiteral_0=null;


        	enterRule();

        try {
            // InternalConceptLanguage.g:5473:2: ( (enumLiteral_0= 'atan' ) )
            // InternalConceptLanguage.g:5474:2: (enumLiteral_0= 'atan' )
            {
            // InternalConceptLanguage.g:5474:2: (enumLiteral_0= 'atan' )
            // InternalConceptLanguage.g:5475:3: enumLiteral_0= 'atan'
            {
            enumLiteral_0=(Token)match(input,75,FOLLOW_2); 

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
    // InternalConceptLanguage.g:5484:1: ruleOperatorAcos returns [Enumerator current=null] : (enumLiteral_0= 'acos' ) ;
    public final Enumerator ruleOperatorAcos() throws RecognitionException {
        Enumerator current = null;

        Token enumLiteral_0=null;


        	enterRule();

        try {
            // InternalConceptLanguage.g:5490:2: ( (enumLiteral_0= 'acos' ) )
            // InternalConceptLanguage.g:5491:2: (enumLiteral_0= 'acos' )
            {
            // InternalConceptLanguage.g:5491:2: (enumLiteral_0= 'acos' )
            // InternalConceptLanguage.g:5492:3: enumLiteral_0= 'acos'
            {
            enumLiteral_0=(Token)match(input,76,FOLLOW_2); 

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
    // InternalConceptLanguage.g:5501:1: ruleOperatorAsin returns [Enumerator current=null] : (enumLiteral_0= 'asin' ) ;
    public final Enumerator ruleOperatorAsin() throws RecognitionException {
        Enumerator current = null;

        Token enumLiteral_0=null;


        	enterRule();

        try {
            // InternalConceptLanguage.g:5507:2: ( (enumLiteral_0= 'asin' ) )
            // InternalConceptLanguage.g:5508:2: (enumLiteral_0= 'asin' )
            {
            // InternalConceptLanguage.g:5508:2: (enumLiteral_0= 'asin' )
            // InternalConceptLanguage.g:5509:3: enumLiteral_0= 'asin'
            {
            enumLiteral_0=(Token)match(input,77,FOLLOW_2); 

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
    // InternalConceptLanguage.g:5518:1: ruleOperatorSqrt returns [Enumerator current=null] : (enumLiteral_0= 'sqrt' ) ;
    public final Enumerator ruleOperatorSqrt() throws RecognitionException {
        Enumerator current = null;

        Token enumLiteral_0=null;


        	enterRule();

        try {
            // InternalConceptLanguage.g:5524:2: ( (enumLiteral_0= 'sqrt' ) )
            // InternalConceptLanguage.g:5525:2: (enumLiteral_0= 'sqrt' )
            {
            // InternalConceptLanguage.g:5525:2: (enumLiteral_0= 'sqrt' )
            // InternalConceptLanguage.g:5526:3: enumLiteral_0= 'sqrt'
            {
            enumLiteral_0=(Token)match(input,78,FOLLOW_2); 

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
    // InternalConceptLanguage.g:5535:1: ruleOperatorLog returns [Enumerator current=null] : (enumLiteral_0= 'log' ) ;
    public final Enumerator ruleOperatorLog() throws RecognitionException {
        Enumerator current = null;

        Token enumLiteral_0=null;


        	enterRule();

        try {
            // InternalConceptLanguage.g:5541:2: ( (enumLiteral_0= 'log' ) )
            // InternalConceptLanguage.g:5542:2: (enumLiteral_0= 'log' )
            {
            // InternalConceptLanguage.g:5542:2: (enumLiteral_0= 'log' )
            // InternalConceptLanguage.g:5543:3: enumLiteral_0= 'log'
            {
            enumLiteral_0=(Token)match(input,79,FOLLOW_2); 

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
    // InternalConceptLanguage.g:5552:1: ruleOperatorLn returns [Enumerator current=null] : (enumLiteral_0= 'ln' ) ;
    public final Enumerator ruleOperatorLn() throws RecognitionException {
        Enumerator current = null;

        Token enumLiteral_0=null;


        	enterRule();

        try {
            // InternalConceptLanguage.g:5558:2: ( (enumLiteral_0= 'ln' ) )
            // InternalConceptLanguage.g:5559:2: (enumLiteral_0= 'ln' )
            {
            // InternalConceptLanguage.g:5559:2: (enumLiteral_0= 'ln' )
            // InternalConceptLanguage.g:5560:3: enumLiteral_0= 'ln'
            {
            enumLiteral_0=(Token)match(input,80,FOLLOW_2); 

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
    // InternalConceptLanguage.g:5569:1: ruleOperatorExp returns [Enumerator current=null] : (enumLiteral_0= 'exp' ) ;
    public final Enumerator ruleOperatorExp() throws RecognitionException {
        Enumerator current = null;

        Token enumLiteral_0=null;


        	enterRule();

        try {
            // InternalConceptLanguage.g:5575:2: ( (enumLiteral_0= 'exp' ) )
            // InternalConceptLanguage.g:5576:2: (enumLiteral_0= 'exp' )
            {
            // InternalConceptLanguage.g:5576:2: (enumLiteral_0= 'exp' )
            // InternalConceptLanguage.g:5577:3: enumLiteral_0= 'exp'
            {
            enumLiteral_0=(Token)match(input,81,FOLLOW_2); 

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
    // InternalConceptLanguage.g:5586:1: ruleOperatorLd returns [Enumerator current=null] : (enumLiteral_0= 'ld' ) ;
    public final Enumerator ruleOperatorLd() throws RecognitionException {
        Enumerator current = null;

        Token enumLiteral_0=null;


        	enterRule();

        try {
            // InternalConceptLanguage.g:5592:2: ( (enumLiteral_0= 'ld' ) )
            // InternalConceptLanguage.g:5593:2: (enumLiteral_0= 'ld' )
            {
            // InternalConceptLanguage.g:5593:2: (enumLiteral_0= 'ld' )
            // InternalConceptLanguage.g:5594:3: enumLiteral_0= 'ld'
            {
            enumLiteral_0=(Token)match(input,82,FOLLOW_2); 

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
    public static final BitSet FOLLOW_4 = new BitSet(new long[]{0x000000000003F000L});
    public static final BitSet FOLLOW_5 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_6 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_7 = new BitSet(new long[]{0x00000189000C0000L});
    public static final BitSet FOLLOW_8 = new BitSet(new long[]{0x00000009000C0000L});
    public static final BitSet FOLLOW_9 = new BitSet(new long[]{0x0000000900040000L});
    public static final BitSet FOLLOW_10 = new BitSet(new long[]{0x0000000800040000L});
    public static final BitSet FOLLOW_11 = new BitSet(new long[]{0x0000000000128000L});
    public static final BitSet FOLLOW_12 = new BitSet(new long[]{0x00000000A0A40000L});
    public static final BitSet FOLLOW_13 = new BitSet(new long[]{0x0000000000400000L});
    public static final BitSet FOLLOW_14 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_15 = new BitSet(new long[]{0x0000000012000000L});
    public static final BitSet FOLLOW_16 = new BitSet(new long[]{0x000000000C000000L});
    public static final BitSet FOLLOW_17 = new BitSet(new long[]{0x0000000040000000L});
    public static final BitSet FOLLOW_18 = new BitSet(new long[]{0x0000000000028000L});
    public static final BitSet FOLLOW_19 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_20 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_21 = new BitSet(new long[]{0x0000000400000000L});
    public static final BitSet FOLLOW_22 = new BitSet(new long[]{0x00000000A0040000L});
    public static final BitSet FOLLOW_23 = new BitSet(new long[]{0x0000000080040000L});
    public static final BitSet FOLLOW_24 = new BitSet(new long[]{0x0000000000040000L});
    public static final BitSet FOLLOW_25 = new BitSet(new long[]{0x0000001000128000L});
    public static final BitSet FOLLOW_26 = new BitSet(new long[]{0x07CF4064A0040000L});
    public static final BitSet FOLLOW_27 = new BitSet(new long[]{0x07CF400400040000L});
    public static final BitSet FOLLOW_28 = new BitSet(new long[]{0x0600000000040000L});
    public static final BitSet FOLLOW_29 = new BitSet(new long[]{0x0000020000400000L});
    public static final BitSet FOLLOW_30 = new BitSet(new long[]{0x0000000008000000L});
    public static final BitSet FOLLOW_31 = new BitSet(new long[]{0x0000040002000000L});
    public static final BitSet FOLLOW_32 = new BitSet(new long[]{0x0000040000000000L});
    public static final BitSet FOLLOW_33 = new BitSet(new long[]{0x0000000800000000L});
    public static final BitSet FOLLOW_34 = new BitSet(new long[]{0x0000380000408000L});
    public static final BitSet FOLLOW_35 = new BitSet(new long[]{0x0000B80002408000L});
    public static final BitSet FOLLOW_36 = new BitSet(new long[]{0x0000B80000408000L});
    public static final BitSet FOLLOW_37 = new BitSet(new long[]{0x0000B80000408020L,0x0000000000000001L});
    public static final BitSet FOLLOW_38 = new BitSet(new long[]{0x0000000000000020L,0x0000000000000001L});
    public static final BitSet FOLLOW_39 = new BitSet(new long[]{0x0000900002408000L});
    public static final BitSet FOLLOW_40 = new BitSet(new long[]{0x0000900000408000L});
    public static final BitSet FOLLOW_41 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000006L});
    public static final BitSet FOLLOW_42 = new BitSet(new long[]{0x0010B80002008000L});
    public static final BitSet FOLLOW_43 = new BitSet(new long[]{0x0010B80000008000L});
    public static final BitSet FOLLOW_44 = new BitSet(new long[]{0x0010B80000408000L});
    public static final BitSet FOLLOW_45 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_46 = new BitSet(new long[]{0x0020000000000000L});
    public static final BitSet FOLLOW_47 = new BitSet(new long[]{0x0000000000000060L,0x0000000000000001L});
    public static final BitSet FOLLOW_48 = new BitSet(new long[]{0x0000100000408000L});
    public static final BitSet FOLLOW_49 = new BitSet(new long[]{0x0000100002408000L});
    public static final BitSet FOLLOW_50 = new BitSet(new long[]{0x6800000000000030L,0x000000000007FF01L});
    public static final BitSet FOLLOW_51 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000011L});
    public static final BitSet FOLLOW_52 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000060L});
    public static final BitSet FOLLOW_53 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000080L});
    public static final BitSet FOLLOW_54 = new BitSet(new long[]{0x0800000000000000L});
    public static final BitSet FOLLOW_55 = new BitSet(new long[]{0x1000000000000000L});
    public static final BitSet FOLLOW_56 = new BitSet(new long[]{0x1000000004000000L});
    public static final BitSet FOLLOW_57 = new BitSet(new long[]{0x0000000004040000L});
    public static final BitSet FOLLOW_58 = new BitSet(new long[]{0x8000000000000002L});
    public static final BitSet FOLLOW_59 = new BitSet(new long[]{0x0000000000000022L});
    public static final BitSet FOLLOW_60 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000008L});

}
