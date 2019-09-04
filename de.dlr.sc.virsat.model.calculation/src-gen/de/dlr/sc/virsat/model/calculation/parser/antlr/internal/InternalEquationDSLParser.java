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
public class InternalEquationDSLParser extends AbstractInternalAntlrParser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "RULE_ID", "RULE_INT", "RULE_STRING", "RULE_ML_COMMENT", "RULE_SL_COMMENT", "RULE_WS", "RULE_ANY_OTHER", "'Import:'", "';'", "'='", "'Ref:'", "'Calc:'", "'('", "')'", "','", "'{'", "'}'", "'.'", "'-'", "'pi'", "'e'", "'+'", "'*'", "'/'", "'^'", "'cos'", "'sin'", "'tan'", "'atan'", "'acos'", "'asin'", "'sqrt'", "'log'", "'ln'", "'exp'", "'ld'"
    };
    public static final int RULE_STRING=6;
    public static final int RULE_SL_COMMENT=8;
    public static final int T__19=19;
    public static final int T__15=15;
    public static final int T__37=37;
    public static final int T__16=16;
    public static final int T__38=38;
    public static final int T__17=17;
    public static final int T__39=39;
    public static final int T__18=18;
    public static final int T__11=11;
    public static final int T__33=33;
    public static final int T__12=12;
    public static final int T__34=34;
    public static final int T__13=13;
    public static final int T__35=35;
    public static final int T__14=14;
    public static final int T__36=36;
    public static final int EOF=-1;
    public static final int T__30=30;
    public static final int T__31=31;
    public static final int T__32=32;
    public static final int RULE_ID=4;
    public static final int RULE_WS=9;
    public static final int RULE_ANY_OTHER=10;
    public static final int T__26=26;
    public static final int T__27=27;
    public static final int T__28=28;
    public static final int RULE_INT=5;
    public static final int T__29=29;
    public static final int T__22=22;
    public static final int RULE_ML_COMMENT=7;
    public static final int T__23=23;
    public static final int T__24=24;
    public static final int T__25=25;
    public static final int T__20=20;
    public static final int T__21=21;

    // delegates
    // delegators


        public InternalEquationDSLParser(TokenStream input) {
            this(input, new RecognizerSharedState());
        }
        public InternalEquationDSLParser(TokenStream input, RecognizerSharedState state) {
            super(input, state);
             
        }
        

    public String[] getTokenNames() { return InternalEquationDSLParser.tokenNames; }
    public String getGrammarFileName() { return "InternalEquationDSL.g"; }



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




    // $ANTLR start "entryRuleEquationSection"
    // InternalEquationDSL.g:71:1: entryRuleEquationSection returns [EObject current=null] : iv_ruleEquationSection= ruleEquationSection EOF ;
    public final EObject entryRuleEquationSection() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleEquationSection = null;


        try {
            // InternalEquationDSL.g:71:56: (iv_ruleEquationSection= ruleEquationSection EOF )
            // InternalEquationDSL.g:72:2: iv_ruleEquationSection= ruleEquationSection EOF
            {
             newCompositeNode(grammarAccess.getEquationSectionRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleEquationSection=ruleEquationSection();

            state._fsp--;

             current =iv_ruleEquationSection; 
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
    // $ANTLR end "entryRuleEquationSection"


    // $ANTLR start "ruleEquationSection"
    // InternalEquationDSL.g:78:1: ruleEquationSection returns [EObject current=null] : ( ( (lv_imports_0_0= ruleImport ) )* ( (lv_equations_1_0= ruleEquation ) )* ) ;
    public final EObject ruleEquationSection() throws RecognitionException {
        EObject current = null;

        EObject lv_imports_0_0 = null;

        EObject lv_equations_1_0 = null;



        	enterRule();

        try {
            // InternalEquationDSL.g:84:2: ( ( ( (lv_imports_0_0= ruleImport ) )* ( (lv_equations_1_0= ruleEquation ) )* ) )
            // InternalEquationDSL.g:85:2: ( ( (lv_imports_0_0= ruleImport ) )* ( (lv_equations_1_0= ruleEquation ) )* )
            {
            // InternalEquationDSL.g:85:2: ( ( (lv_imports_0_0= ruleImport ) )* ( (lv_equations_1_0= ruleEquation ) )* )
            // InternalEquationDSL.g:86:3: ( (lv_imports_0_0= ruleImport ) )* ( (lv_equations_1_0= ruleEquation ) )*
            {
            // InternalEquationDSL.g:86:3: ( (lv_imports_0_0= ruleImport ) )*
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( (LA1_0==11) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // InternalEquationDSL.g:87:4: (lv_imports_0_0= ruleImport )
            	    {
            	    // InternalEquationDSL.g:87:4: (lv_imports_0_0= ruleImport )
            	    // InternalEquationDSL.g:88:5: lv_imports_0_0= ruleImport
            	    {

            	    					newCompositeNode(grammarAccess.getEquationSectionAccess().getImportsImportParserRuleCall_0_0());
            	    				
            	    pushFollow(FOLLOW_3);
            	    lv_imports_0_0=ruleImport();

            	    state._fsp--;


            	    					if (current==null) {
            	    						current = createModelElementForParent(grammarAccess.getEquationSectionRule());
            	    					}
            	    					add(
            	    						current,
            	    						"imports",
            	    						lv_imports_0_0,
            	    						"de.dlr.sc.virsat.model.calculation.EquationDSL.Import");
            	    					afterParserOrEnumRuleCall();
            	    				

            	    }


            	    }
            	    break;

            	default :
            	    break loop1;
                }
            } while (true);

            // InternalEquationDSL.g:105:3: ( (lv_equations_1_0= ruleEquation ) )*
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( ((LA2_0>=14 && LA2_0<=15)) ) {
                    alt2=1;
                }


                switch (alt2) {
            	case 1 :
            	    // InternalEquationDSL.g:106:4: (lv_equations_1_0= ruleEquation )
            	    {
            	    // InternalEquationDSL.g:106:4: (lv_equations_1_0= ruleEquation )
            	    // InternalEquationDSL.g:107:5: lv_equations_1_0= ruleEquation
            	    {

            	    					newCompositeNode(grammarAccess.getEquationSectionAccess().getEquationsEquationParserRuleCall_1_0());
            	    				
            	    pushFollow(FOLLOW_4);
            	    lv_equations_1_0=ruleEquation();

            	    state._fsp--;


            	    					if (current==null) {
            	    						current = createModelElementForParent(grammarAccess.getEquationSectionRule());
            	    					}
            	    					add(
            	    						current,
            	    						"equations",
            	    						lv_equations_1_0,
            	    						"de.dlr.sc.virsat.model.calculation.EquationDSL.Equation");
            	    					afterParserOrEnumRuleCall();
            	    				

            	    }


            	    }
            	    break;

            	default :
            	    break loop2;
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
    // $ANTLR end "ruleEquationSection"


    // $ANTLR start "entryRuleImport"
    // InternalEquationDSL.g:128:1: entryRuleImport returns [EObject current=null] : iv_ruleImport= ruleImport EOF ;
    public final EObject entryRuleImport() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleImport = null;


        try {
            // InternalEquationDSL.g:128:47: (iv_ruleImport= ruleImport EOF )
            // InternalEquationDSL.g:129:2: iv_ruleImport= ruleImport EOF
            {
             newCompositeNode(grammarAccess.getImportRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleImport=ruleImport();

            state._fsp--;

             current =iv_ruleImport; 
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
    // $ANTLR end "entryRuleImport"


    // $ANTLR start "ruleImport"
    // InternalEquationDSL.g:135:1: ruleImport returns [EObject current=null] : (otherlv_0= 'Import:' ( ( ruleQualifiedName ) ) otherlv_2= ';' ) ;
    public final EObject ruleImport() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_2=null;


        	enterRule();

        try {
            // InternalEquationDSL.g:141:2: ( (otherlv_0= 'Import:' ( ( ruleQualifiedName ) ) otherlv_2= ';' ) )
            // InternalEquationDSL.g:142:2: (otherlv_0= 'Import:' ( ( ruleQualifiedName ) ) otherlv_2= ';' )
            {
            // InternalEquationDSL.g:142:2: (otherlv_0= 'Import:' ( ( ruleQualifiedName ) ) otherlv_2= ';' )
            // InternalEquationDSL.g:143:3: otherlv_0= 'Import:' ( ( ruleQualifiedName ) ) otherlv_2= ';'
            {
            otherlv_0=(Token)match(input,11,FOLLOW_5); 

            			newLeafNode(otherlv_0, grammarAccess.getImportAccess().getImportKeyword_0());
            		
            // InternalEquationDSL.g:147:3: ( ( ruleQualifiedName ) )
            // InternalEquationDSL.g:148:4: ( ruleQualifiedName )
            {
            // InternalEquationDSL.g:148:4: ( ruleQualifiedName )
            // InternalEquationDSL.g:149:5: ruleQualifiedName
            {

            					if (current==null) {
            						current = createModelElement(grammarAccess.getImportRule());
            					}
            				

            					newCompositeNode(grammarAccess.getImportAccess().getImportedNamespaceIInstanceCrossReference_1_0());
            				
            pushFollow(FOLLOW_6);
            ruleQualifiedName();

            state._fsp--;


            					afterParserOrEnumRuleCall();
            				

            }


            }

            otherlv_2=(Token)match(input,12,FOLLOW_2); 

            			newLeafNode(otherlv_2, grammarAccess.getImportAccess().getSemicolonKeyword_2());
            		

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
    // $ANTLR end "ruleImport"


    // $ANTLR start "entryRuleEquation"
    // InternalEquationDSL.g:171:1: entryRuleEquation returns [EObject current=null] : iv_ruleEquation= ruleEquation EOF ;
    public final EObject entryRuleEquation() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleEquation = null;


        try {
            // InternalEquationDSL.g:171:49: (iv_ruleEquation= ruleEquation EOF )
            // InternalEquationDSL.g:172:2: iv_ruleEquation= ruleEquation EOF
            {
             newCompositeNode(grammarAccess.getEquationRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleEquation=ruleEquation();

            state._fsp--;

             current =iv_ruleEquation; 
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
    // $ANTLR end "entryRuleEquation"


    // $ANTLR start "ruleEquation"
    // InternalEquationDSL.g:178:1: ruleEquation returns [EObject current=null] : ( ( (lv_result_0_0= ruleEquationResult ) ) otherlv_1= '=' ( (lv_expression_2_0= ruleAdditionAndSubtraction ) ) otherlv_3= ';' ) ;
    public final EObject ruleEquation() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_3=null;
        EObject lv_result_0_0 = null;

        EObject lv_expression_2_0 = null;



        	enterRule();

        try {
            // InternalEquationDSL.g:184:2: ( ( ( (lv_result_0_0= ruleEquationResult ) ) otherlv_1= '=' ( (lv_expression_2_0= ruleAdditionAndSubtraction ) ) otherlv_3= ';' ) )
            // InternalEquationDSL.g:185:2: ( ( (lv_result_0_0= ruleEquationResult ) ) otherlv_1= '=' ( (lv_expression_2_0= ruleAdditionAndSubtraction ) ) otherlv_3= ';' )
            {
            // InternalEquationDSL.g:185:2: ( ( (lv_result_0_0= ruleEquationResult ) ) otherlv_1= '=' ( (lv_expression_2_0= ruleAdditionAndSubtraction ) ) otherlv_3= ';' )
            // InternalEquationDSL.g:186:3: ( (lv_result_0_0= ruleEquationResult ) ) otherlv_1= '=' ( (lv_expression_2_0= ruleAdditionAndSubtraction ) ) otherlv_3= ';'
            {
            // InternalEquationDSL.g:186:3: ( (lv_result_0_0= ruleEquationResult ) )
            // InternalEquationDSL.g:187:4: (lv_result_0_0= ruleEquationResult )
            {
            // InternalEquationDSL.g:187:4: (lv_result_0_0= ruleEquationResult )
            // InternalEquationDSL.g:188:5: lv_result_0_0= ruleEquationResult
            {

            					newCompositeNode(grammarAccess.getEquationAccess().getResultEquationResultParserRuleCall_0_0());
            				
            pushFollow(FOLLOW_7);
            lv_result_0_0=ruleEquationResult();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getEquationRule());
            					}
            					set(
            						current,
            						"result",
            						lv_result_0_0,
            						"de.dlr.sc.virsat.model.calculation.EquationDSL.EquationResult");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            otherlv_1=(Token)match(input,13,FOLLOW_8); 

            			newLeafNode(otherlv_1, grammarAccess.getEquationAccess().getEqualsSignKeyword_1());
            		
            // InternalEquationDSL.g:209:3: ( (lv_expression_2_0= ruleAdditionAndSubtraction ) )
            // InternalEquationDSL.g:210:4: (lv_expression_2_0= ruleAdditionAndSubtraction )
            {
            // InternalEquationDSL.g:210:4: (lv_expression_2_0= ruleAdditionAndSubtraction )
            // InternalEquationDSL.g:211:5: lv_expression_2_0= ruleAdditionAndSubtraction
            {

            					newCompositeNode(grammarAccess.getEquationAccess().getExpressionAdditionAndSubtractionParserRuleCall_2_0());
            				
            pushFollow(FOLLOW_6);
            lv_expression_2_0=ruleAdditionAndSubtraction();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getEquationRule());
            					}
            					set(
            						current,
            						"expression",
            						lv_expression_2_0,
            						"de.dlr.sc.virsat.model.calculation.EquationDSL.AdditionAndSubtraction");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            otherlv_3=(Token)match(input,12,FOLLOW_2); 

            			newLeafNode(otherlv_3, grammarAccess.getEquationAccess().getSemicolonKeyword_3());
            		

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
    // $ANTLR end "ruleEquation"


    // $ANTLR start "entryRuleEquationResult"
    // InternalEquationDSL.g:236:1: entryRuleEquationResult returns [EObject current=null] : iv_ruleEquationResult= ruleEquationResult EOF ;
    public final EObject entryRuleEquationResult() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleEquationResult = null;


        try {
            // InternalEquationDSL.g:236:55: (iv_ruleEquationResult= ruleEquationResult EOF )
            // InternalEquationDSL.g:237:2: iv_ruleEquationResult= ruleEquationResult EOF
            {
             newCompositeNode(grammarAccess.getEquationResultRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleEquationResult=ruleEquationResult();

            state._fsp--;

             current =iv_ruleEquationResult; 
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
    // $ANTLR end "entryRuleEquationResult"


    // $ANTLR start "ruleEquationResult"
    // InternalEquationDSL.g:243:1: ruleEquationResult returns [EObject current=null] : (this_TypeInstanceResult_0= ruleTypeInstanceResult | this_EquationIntermediateResult_1= ruleEquationIntermediateResult ) ;
    public final EObject ruleEquationResult() throws RecognitionException {
        EObject current = null;

        EObject this_TypeInstanceResult_0 = null;

        EObject this_EquationIntermediateResult_1 = null;



        	enterRule();

        try {
            // InternalEquationDSL.g:249:2: ( (this_TypeInstanceResult_0= ruleTypeInstanceResult | this_EquationIntermediateResult_1= ruleEquationIntermediateResult ) )
            // InternalEquationDSL.g:250:2: (this_TypeInstanceResult_0= ruleTypeInstanceResult | this_EquationIntermediateResult_1= ruleEquationIntermediateResult )
            {
            // InternalEquationDSL.g:250:2: (this_TypeInstanceResult_0= ruleTypeInstanceResult | this_EquationIntermediateResult_1= ruleEquationIntermediateResult )
            int alt3=2;
            int LA3_0 = input.LA(1);

            if ( (LA3_0==14) ) {
                alt3=1;
            }
            else if ( (LA3_0==15) ) {
                alt3=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 3, 0, input);

                throw nvae;
            }
            switch (alt3) {
                case 1 :
                    // InternalEquationDSL.g:251:3: this_TypeInstanceResult_0= ruleTypeInstanceResult
                    {

                    			newCompositeNode(grammarAccess.getEquationResultAccess().getTypeInstanceResultParserRuleCall_0());
                    		
                    pushFollow(FOLLOW_2);
                    this_TypeInstanceResult_0=ruleTypeInstanceResult();

                    state._fsp--;


                    			current = this_TypeInstanceResult_0;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 2 :
                    // InternalEquationDSL.g:260:3: this_EquationIntermediateResult_1= ruleEquationIntermediateResult
                    {

                    			newCompositeNode(grammarAccess.getEquationResultAccess().getEquationIntermediateResultParserRuleCall_1());
                    		
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
    // $ANTLR end "ruleEquationResult"


    // $ANTLR start "entryRuleTypeInstanceResult"
    // InternalEquationDSL.g:272:1: entryRuleTypeInstanceResult returns [EObject current=null] : iv_ruleTypeInstanceResult= ruleTypeInstanceResult EOF ;
    public final EObject entryRuleTypeInstanceResult() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleTypeInstanceResult = null;


        try {
            // InternalEquationDSL.g:272:59: (iv_ruleTypeInstanceResult= ruleTypeInstanceResult EOF )
            // InternalEquationDSL.g:273:2: iv_ruleTypeInstanceResult= ruleTypeInstanceResult EOF
            {
             newCompositeNode(grammarAccess.getTypeInstanceResultRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleTypeInstanceResult=ruleTypeInstanceResult();

            state._fsp--;

             current =iv_ruleTypeInstanceResult; 
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
    // $ANTLR end "entryRuleTypeInstanceResult"


    // $ANTLR start "ruleTypeInstanceResult"
    // InternalEquationDSL.g:279:1: ruleTypeInstanceResult returns [EObject current=null] : ( () otherlv_1= 'Ref:' ( ( ruleQualifiedName ) ) ) ;
    public final EObject ruleTypeInstanceResult() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;


        	enterRule();

        try {
            // InternalEquationDSL.g:285:2: ( ( () otherlv_1= 'Ref:' ( ( ruleQualifiedName ) ) ) )
            // InternalEquationDSL.g:286:2: ( () otherlv_1= 'Ref:' ( ( ruleQualifiedName ) ) )
            {
            // InternalEquationDSL.g:286:2: ( () otherlv_1= 'Ref:' ( ( ruleQualifiedName ) ) )
            // InternalEquationDSL.g:287:3: () otherlv_1= 'Ref:' ( ( ruleQualifiedName ) )
            {
            // InternalEquationDSL.g:287:3: ()
            // InternalEquationDSL.g:288:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getTypeInstanceResultAccess().getTypeInstanceResultAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,14,FOLLOW_5); 

            			newLeafNode(otherlv_1, grammarAccess.getTypeInstanceResultAccess().getRefKeyword_1());
            		
            // InternalEquationDSL.g:298:3: ( ( ruleQualifiedName ) )
            // InternalEquationDSL.g:299:4: ( ruleQualifiedName )
            {
            // InternalEquationDSL.g:299:4: ( ruleQualifiedName )
            // InternalEquationDSL.g:300:5: ruleQualifiedName
            {

            					if (current==null) {
            						current = createModelElement(grammarAccess.getTypeInstanceResultRule());
            					}
            				

            					newCompositeNode(grammarAccess.getTypeInstanceResultAccess().getReferenceATypeInstanceCrossReference_2_0());
            				
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
    // $ANTLR end "ruleTypeInstanceResult"


    // $ANTLR start "entryRuleEquationIntermediateResult"
    // InternalEquationDSL.g:318:1: entryRuleEquationIntermediateResult returns [EObject current=null] : iv_ruleEquationIntermediateResult= ruleEquationIntermediateResult EOF ;
    public final EObject entryRuleEquationIntermediateResult() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleEquationIntermediateResult = null;


        try {
            // InternalEquationDSL.g:318:67: (iv_ruleEquationIntermediateResult= ruleEquationIntermediateResult EOF )
            // InternalEquationDSL.g:319:2: iv_ruleEquationIntermediateResult= ruleEquationIntermediateResult EOF
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
    // InternalEquationDSL.g:325:1: ruleEquationIntermediateResult returns [EObject current=null] : ( () otherlv_1= 'Calc:' ( (lv_name_2_0= RULE_ID ) ) ) ;
    public final EObject ruleEquationIntermediateResult() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token lv_name_2_0=null;


        	enterRule();

        try {
            // InternalEquationDSL.g:331:2: ( ( () otherlv_1= 'Calc:' ( (lv_name_2_0= RULE_ID ) ) ) )
            // InternalEquationDSL.g:332:2: ( () otherlv_1= 'Calc:' ( (lv_name_2_0= RULE_ID ) ) )
            {
            // InternalEquationDSL.g:332:2: ( () otherlv_1= 'Calc:' ( (lv_name_2_0= RULE_ID ) ) )
            // InternalEquationDSL.g:333:3: () otherlv_1= 'Calc:' ( (lv_name_2_0= RULE_ID ) )
            {
            // InternalEquationDSL.g:333:3: ()
            // InternalEquationDSL.g:334:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getEquationIntermediateResultAccess().getEquationIntermediateResultAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,15,FOLLOW_5); 

            			newLeafNode(otherlv_1, grammarAccess.getEquationIntermediateResultAccess().getCalcKeyword_1());
            		
            // InternalEquationDSL.g:344:3: ( (lv_name_2_0= RULE_ID ) )
            // InternalEquationDSL.g:345:4: (lv_name_2_0= RULE_ID )
            {
            // InternalEquationDSL.g:345:4: (lv_name_2_0= RULE_ID )
            // InternalEquationDSL.g:346:5: lv_name_2_0= RULE_ID
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
    // InternalEquationDSL.g:366:1: entryRuleAdditionAndSubtraction returns [EObject current=null] : iv_ruleAdditionAndSubtraction= ruleAdditionAndSubtraction EOF ;
    public final EObject entryRuleAdditionAndSubtraction() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleAdditionAndSubtraction = null;


        try {
            // InternalEquationDSL.g:366:63: (iv_ruleAdditionAndSubtraction= ruleAdditionAndSubtraction EOF )
            // InternalEquationDSL.g:367:2: iv_ruleAdditionAndSubtraction= ruleAdditionAndSubtraction EOF
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
    // InternalEquationDSL.g:373:1: ruleAdditionAndSubtraction returns [EObject current=null] : (this_MultiplicationAndDivision_0= ruleMultiplicationAndDivision ( () ( ( (lv_operator_2_1= ruleOperatorPlus | lv_operator_2_2= ruleOperatorMinus ) ) ) ( (lv_right_3_0= ruleMultiplicationAndDivision ) ) )* ) ;
    public final EObject ruleAdditionAndSubtraction() throws RecognitionException {
        EObject current = null;

        EObject this_MultiplicationAndDivision_0 = null;

        Enumerator lv_operator_2_1 = null;

        Enumerator lv_operator_2_2 = null;

        EObject lv_right_3_0 = null;



        	enterRule();

        try {
            // InternalEquationDSL.g:379:2: ( (this_MultiplicationAndDivision_0= ruleMultiplicationAndDivision ( () ( ( (lv_operator_2_1= ruleOperatorPlus | lv_operator_2_2= ruleOperatorMinus ) ) ) ( (lv_right_3_0= ruleMultiplicationAndDivision ) ) )* ) )
            // InternalEquationDSL.g:380:2: (this_MultiplicationAndDivision_0= ruleMultiplicationAndDivision ( () ( ( (lv_operator_2_1= ruleOperatorPlus | lv_operator_2_2= ruleOperatorMinus ) ) ) ( (lv_right_3_0= ruleMultiplicationAndDivision ) ) )* )
            {
            // InternalEquationDSL.g:380:2: (this_MultiplicationAndDivision_0= ruleMultiplicationAndDivision ( () ( ( (lv_operator_2_1= ruleOperatorPlus | lv_operator_2_2= ruleOperatorMinus ) ) ) ( (lv_right_3_0= ruleMultiplicationAndDivision ) ) )* )
            // InternalEquationDSL.g:381:3: this_MultiplicationAndDivision_0= ruleMultiplicationAndDivision ( () ( ( (lv_operator_2_1= ruleOperatorPlus | lv_operator_2_2= ruleOperatorMinus ) ) ) ( (lv_right_3_0= ruleMultiplicationAndDivision ) ) )*
            {

            			newCompositeNode(grammarAccess.getAdditionAndSubtractionAccess().getMultiplicationAndDivisionParserRuleCall_0());
            		
            pushFollow(FOLLOW_9);
            this_MultiplicationAndDivision_0=ruleMultiplicationAndDivision();

            state._fsp--;


            			current = this_MultiplicationAndDivision_0;
            			afterParserOrEnumRuleCall();
            		
            // InternalEquationDSL.g:389:3: ( () ( ( (lv_operator_2_1= ruleOperatorPlus | lv_operator_2_2= ruleOperatorMinus ) ) ) ( (lv_right_3_0= ruleMultiplicationAndDivision ) ) )*
            loop5:
            do {
                int alt5=2;
                int LA5_0 = input.LA(1);

                if ( (LA5_0==22||LA5_0==25) ) {
                    alt5=1;
                }


                switch (alt5) {
            	case 1 :
            	    // InternalEquationDSL.g:390:4: () ( ( (lv_operator_2_1= ruleOperatorPlus | lv_operator_2_2= ruleOperatorMinus ) ) ) ( (lv_right_3_0= ruleMultiplicationAndDivision ) )
            	    {
            	    // InternalEquationDSL.g:390:4: ()
            	    // InternalEquationDSL.g:391:5: 
            	    {

            	    					current = forceCreateModelElementAndSet(
            	    						grammarAccess.getAdditionAndSubtractionAccess().getAdditionAndSubtractionLeftAction_1_0(),
            	    						current);
            	    				

            	    }

            	    // InternalEquationDSL.g:397:4: ( ( (lv_operator_2_1= ruleOperatorPlus | lv_operator_2_2= ruleOperatorMinus ) ) )
            	    // InternalEquationDSL.g:398:5: ( (lv_operator_2_1= ruleOperatorPlus | lv_operator_2_2= ruleOperatorMinus ) )
            	    {
            	    // InternalEquationDSL.g:398:5: ( (lv_operator_2_1= ruleOperatorPlus | lv_operator_2_2= ruleOperatorMinus ) )
            	    // InternalEquationDSL.g:399:6: (lv_operator_2_1= ruleOperatorPlus | lv_operator_2_2= ruleOperatorMinus )
            	    {
            	    // InternalEquationDSL.g:399:6: (lv_operator_2_1= ruleOperatorPlus | lv_operator_2_2= ruleOperatorMinus )
            	    int alt4=2;
            	    int LA4_0 = input.LA(1);

            	    if ( (LA4_0==25) ) {
            	        alt4=1;
            	    }
            	    else if ( (LA4_0==22) ) {
            	        alt4=2;
            	    }
            	    else {
            	        NoViableAltException nvae =
            	            new NoViableAltException("", 4, 0, input);

            	        throw nvae;
            	    }
            	    switch (alt4) {
            	        case 1 :
            	            // InternalEquationDSL.g:400:7: lv_operator_2_1= ruleOperatorPlus
            	            {

            	            							newCompositeNode(grammarAccess.getAdditionAndSubtractionAccess().getOperatorOperatorPlusEnumRuleCall_1_1_0_0());
            	            						
            	            pushFollow(FOLLOW_8);
            	            lv_operator_2_1=ruleOperatorPlus();

            	            state._fsp--;


            	            							if (current==null) {
            	            								current = createModelElementForParent(grammarAccess.getAdditionAndSubtractionRule());
            	            							}
            	            							set(
            	            								current,
            	            								"operator",
            	            								lv_operator_2_1,
            	            								"de.dlr.sc.virsat.model.calculation.EquationDSL.OperatorPlus");
            	            							afterParserOrEnumRuleCall();
            	            						

            	            }
            	            break;
            	        case 2 :
            	            // InternalEquationDSL.g:416:7: lv_operator_2_2= ruleOperatorMinus
            	            {

            	            							newCompositeNode(grammarAccess.getAdditionAndSubtractionAccess().getOperatorOperatorMinusEnumRuleCall_1_1_0_1());
            	            						
            	            pushFollow(FOLLOW_8);
            	            lv_operator_2_2=ruleOperatorMinus();

            	            state._fsp--;


            	            							if (current==null) {
            	            								current = createModelElementForParent(grammarAccess.getAdditionAndSubtractionRule());
            	            							}
            	            							set(
            	            								current,
            	            								"operator",
            	            								lv_operator_2_2,
            	            								"de.dlr.sc.virsat.model.calculation.EquationDSL.OperatorMinus");
            	            							afterParserOrEnumRuleCall();
            	            						

            	            }
            	            break;

            	    }


            	    }


            	    }

            	    // InternalEquationDSL.g:434:4: ( (lv_right_3_0= ruleMultiplicationAndDivision ) )
            	    // InternalEquationDSL.g:435:5: (lv_right_3_0= ruleMultiplicationAndDivision )
            	    {
            	    // InternalEquationDSL.g:435:5: (lv_right_3_0= ruleMultiplicationAndDivision )
            	    // InternalEquationDSL.g:436:6: lv_right_3_0= ruleMultiplicationAndDivision
            	    {

            	    						newCompositeNode(grammarAccess.getAdditionAndSubtractionAccess().getRightMultiplicationAndDivisionParserRuleCall_1_2_0());
            	    					
            	    pushFollow(FOLLOW_9);
            	    lv_right_3_0=ruleMultiplicationAndDivision();

            	    state._fsp--;


            	    						if (current==null) {
            	    							current = createModelElementForParent(grammarAccess.getAdditionAndSubtractionRule());
            	    						}
            	    						set(
            	    							current,
            	    							"right",
            	    							lv_right_3_0,
            	    							"de.dlr.sc.virsat.model.calculation.EquationDSL.MultiplicationAndDivision");
            	    						afterParserOrEnumRuleCall();
            	    					

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop5;
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
    // InternalEquationDSL.g:458:1: entryRuleMultiplicationAndDivision returns [EObject current=null] : iv_ruleMultiplicationAndDivision= ruleMultiplicationAndDivision EOF ;
    public final EObject entryRuleMultiplicationAndDivision() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleMultiplicationAndDivision = null;


        try {
            // InternalEquationDSL.g:458:66: (iv_ruleMultiplicationAndDivision= ruleMultiplicationAndDivision EOF )
            // InternalEquationDSL.g:459:2: iv_ruleMultiplicationAndDivision= ruleMultiplicationAndDivision EOF
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
    // InternalEquationDSL.g:465:1: ruleMultiplicationAndDivision returns [EObject current=null] : (this_PowerFunction_0= rulePowerFunction ( () ( ( (lv_operator_2_1= ruleOperatorMultiply | lv_operator_2_2= ruleOperatorDivide ) ) ) ( (lv_right_3_0= rulePowerFunction ) ) )* ) ;
    public final EObject ruleMultiplicationAndDivision() throws RecognitionException {
        EObject current = null;

        EObject this_PowerFunction_0 = null;

        Enumerator lv_operator_2_1 = null;

        Enumerator lv_operator_2_2 = null;

        EObject lv_right_3_0 = null;



        	enterRule();

        try {
            // InternalEquationDSL.g:471:2: ( (this_PowerFunction_0= rulePowerFunction ( () ( ( (lv_operator_2_1= ruleOperatorMultiply | lv_operator_2_2= ruleOperatorDivide ) ) ) ( (lv_right_3_0= rulePowerFunction ) ) )* ) )
            // InternalEquationDSL.g:472:2: (this_PowerFunction_0= rulePowerFunction ( () ( ( (lv_operator_2_1= ruleOperatorMultiply | lv_operator_2_2= ruleOperatorDivide ) ) ) ( (lv_right_3_0= rulePowerFunction ) ) )* )
            {
            // InternalEquationDSL.g:472:2: (this_PowerFunction_0= rulePowerFunction ( () ( ( (lv_operator_2_1= ruleOperatorMultiply | lv_operator_2_2= ruleOperatorDivide ) ) ) ( (lv_right_3_0= rulePowerFunction ) ) )* )
            // InternalEquationDSL.g:473:3: this_PowerFunction_0= rulePowerFunction ( () ( ( (lv_operator_2_1= ruleOperatorMultiply | lv_operator_2_2= ruleOperatorDivide ) ) ) ( (lv_right_3_0= rulePowerFunction ) ) )*
            {

            			newCompositeNode(grammarAccess.getMultiplicationAndDivisionAccess().getPowerFunctionParserRuleCall_0());
            		
            pushFollow(FOLLOW_10);
            this_PowerFunction_0=rulePowerFunction();

            state._fsp--;


            			current = this_PowerFunction_0;
            			afterParserOrEnumRuleCall();
            		
            // InternalEquationDSL.g:481:3: ( () ( ( (lv_operator_2_1= ruleOperatorMultiply | lv_operator_2_2= ruleOperatorDivide ) ) ) ( (lv_right_3_0= rulePowerFunction ) ) )*
            loop7:
            do {
                int alt7=2;
                int LA7_0 = input.LA(1);

                if ( ((LA7_0>=26 && LA7_0<=27)) ) {
                    alt7=1;
                }


                switch (alt7) {
            	case 1 :
            	    // InternalEquationDSL.g:482:4: () ( ( (lv_operator_2_1= ruleOperatorMultiply | lv_operator_2_2= ruleOperatorDivide ) ) ) ( (lv_right_3_0= rulePowerFunction ) )
            	    {
            	    // InternalEquationDSL.g:482:4: ()
            	    // InternalEquationDSL.g:483:5: 
            	    {

            	    					current = forceCreateModelElementAndSet(
            	    						grammarAccess.getMultiplicationAndDivisionAccess().getMultiplicationAndDivisionLeftAction_1_0(),
            	    						current);
            	    				

            	    }

            	    // InternalEquationDSL.g:489:4: ( ( (lv_operator_2_1= ruleOperatorMultiply | lv_operator_2_2= ruleOperatorDivide ) ) )
            	    // InternalEquationDSL.g:490:5: ( (lv_operator_2_1= ruleOperatorMultiply | lv_operator_2_2= ruleOperatorDivide ) )
            	    {
            	    // InternalEquationDSL.g:490:5: ( (lv_operator_2_1= ruleOperatorMultiply | lv_operator_2_2= ruleOperatorDivide ) )
            	    // InternalEquationDSL.g:491:6: (lv_operator_2_1= ruleOperatorMultiply | lv_operator_2_2= ruleOperatorDivide )
            	    {
            	    // InternalEquationDSL.g:491:6: (lv_operator_2_1= ruleOperatorMultiply | lv_operator_2_2= ruleOperatorDivide )
            	    int alt6=2;
            	    int LA6_0 = input.LA(1);

            	    if ( (LA6_0==26) ) {
            	        alt6=1;
            	    }
            	    else if ( (LA6_0==27) ) {
            	        alt6=2;
            	    }
            	    else {
            	        NoViableAltException nvae =
            	            new NoViableAltException("", 6, 0, input);

            	        throw nvae;
            	    }
            	    switch (alt6) {
            	        case 1 :
            	            // InternalEquationDSL.g:492:7: lv_operator_2_1= ruleOperatorMultiply
            	            {

            	            							newCompositeNode(grammarAccess.getMultiplicationAndDivisionAccess().getOperatorOperatorMultiplyEnumRuleCall_1_1_0_0());
            	            						
            	            pushFollow(FOLLOW_8);
            	            lv_operator_2_1=ruleOperatorMultiply();

            	            state._fsp--;


            	            							if (current==null) {
            	            								current = createModelElementForParent(grammarAccess.getMultiplicationAndDivisionRule());
            	            							}
            	            							set(
            	            								current,
            	            								"operator",
            	            								lv_operator_2_1,
            	            								"de.dlr.sc.virsat.model.calculation.EquationDSL.OperatorMultiply");
            	            							afterParserOrEnumRuleCall();
            	            						

            	            }
            	            break;
            	        case 2 :
            	            // InternalEquationDSL.g:508:7: lv_operator_2_2= ruleOperatorDivide
            	            {

            	            							newCompositeNode(grammarAccess.getMultiplicationAndDivisionAccess().getOperatorOperatorDivideEnumRuleCall_1_1_0_1());
            	            						
            	            pushFollow(FOLLOW_8);
            	            lv_operator_2_2=ruleOperatorDivide();

            	            state._fsp--;


            	            							if (current==null) {
            	            								current = createModelElementForParent(grammarAccess.getMultiplicationAndDivisionRule());
            	            							}
            	            							set(
            	            								current,
            	            								"operator",
            	            								lv_operator_2_2,
            	            								"de.dlr.sc.virsat.model.calculation.EquationDSL.OperatorDivide");
            	            							afterParserOrEnumRuleCall();
            	            						

            	            }
            	            break;

            	    }


            	    }


            	    }

            	    // InternalEquationDSL.g:526:4: ( (lv_right_3_0= rulePowerFunction ) )
            	    // InternalEquationDSL.g:527:5: (lv_right_3_0= rulePowerFunction )
            	    {
            	    // InternalEquationDSL.g:527:5: (lv_right_3_0= rulePowerFunction )
            	    // InternalEquationDSL.g:528:6: lv_right_3_0= rulePowerFunction
            	    {

            	    						newCompositeNode(grammarAccess.getMultiplicationAndDivisionAccess().getRightPowerFunctionParserRuleCall_1_2_0());
            	    					
            	    pushFollow(FOLLOW_10);
            	    lv_right_3_0=rulePowerFunction();

            	    state._fsp--;


            	    						if (current==null) {
            	    							current = createModelElementForParent(grammarAccess.getMultiplicationAndDivisionRule());
            	    						}
            	    						set(
            	    							current,
            	    							"right",
            	    							lv_right_3_0,
            	    							"de.dlr.sc.virsat.model.calculation.EquationDSL.PowerFunction");
            	    						afterParserOrEnumRuleCall();
            	    					

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop7;
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
    // InternalEquationDSL.g:550:1: entryRulePowerFunction returns [EObject current=null] : iv_rulePowerFunction= rulePowerFunction EOF ;
    public final EObject entryRulePowerFunction() throws RecognitionException {
        EObject current = null;

        EObject iv_rulePowerFunction = null;


        try {
            // InternalEquationDSL.g:550:54: (iv_rulePowerFunction= rulePowerFunction EOF )
            // InternalEquationDSL.g:551:2: iv_rulePowerFunction= rulePowerFunction EOF
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
    // InternalEquationDSL.g:557:1: rulePowerFunction returns [EObject current=null] : (this_AExpression_0= ruleAExpression ( () ( (lv_operator_2_0= ruleOperatorPower ) ) ( (lv_right_3_0= ruleAExpression ) ) )* ) ;
    public final EObject rulePowerFunction() throws RecognitionException {
        EObject current = null;

        EObject this_AExpression_0 = null;

        Enumerator lv_operator_2_0 = null;

        EObject lv_right_3_0 = null;



        	enterRule();

        try {
            // InternalEquationDSL.g:563:2: ( (this_AExpression_0= ruleAExpression ( () ( (lv_operator_2_0= ruleOperatorPower ) ) ( (lv_right_3_0= ruleAExpression ) ) )* ) )
            // InternalEquationDSL.g:564:2: (this_AExpression_0= ruleAExpression ( () ( (lv_operator_2_0= ruleOperatorPower ) ) ( (lv_right_3_0= ruleAExpression ) ) )* )
            {
            // InternalEquationDSL.g:564:2: (this_AExpression_0= ruleAExpression ( () ( (lv_operator_2_0= ruleOperatorPower ) ) ( (lv_right_3_0= ruleAExpression ) ) )* )
            // InternalEquationDSL.g:565:3: this_AExpression_0= ruleAExpression ( () ( (lv_operator_2_0= ruleOperatorPower ) ) ( (lv_right_3_0= ruleAExpression ) ) )*
            {

            			newCompositeNode(grammarAccess.getPowerFunctionAccess().getAExpressionParserRuleCall_0());
            		
            pushFollow(FOLLOW_11);
            this_AExpression_0=ruleAExpression();

            state._fsp--;


            			current = this_AExpression_0;
            			afterParserOrEnumRuleCall();
            		
            // InternalEquationDSL.g:573:3: ( () ( (lv_operator_2_0= ruleOperatorPower ) ) ( (lv_right_3_0= ruleAExpression ) ) )*
            loop8:
            do {
                int alt8=2;
                int LA8_0 = input.LA(1);

                if ( (LA8_0==28) ) {
                    alt8=1;
                }


                switch (alt8) {
            	case 1 :
            	    // InternalEquationDSL.g:574:4: () ( (lv_operator_2_0= ruleOperatorPower ) ) ( (lv_right_3_0= ruleAExpression ) )
            	    {
            	    // InternalEquationDSL.g:574:4: ()
            	    // InternalEquationDSL.g:575:5: 
            	    {

            	    					current = forceCreateModelElementAndSet(
            	    						grammarAccess.getPowerFunctionAccess().getPowerFunctionLeftAction_1_0(),
            	    						current);
            	    				

            	    }

            	    // InternalEquationDSL.g:581:4: ( (lv_operator_2_0= ruleOperatorPower ) )
            	    // InternalEquationDSL.g:582:5: (lv_operator_2_0= ruleOperatorPower )
            	    {
            	    // InternalEquationDSL.g:582:5: (lv_operator_2_0= ruleOperatorPower )
            	    // InternalEquationDSL.g:583:6: lv_operator_2_0= ruleOperatorPower
            	    {

            	    						newCompositeNode(grammarAccess.getPowerFunctionAccess().getOperatorOperatorPowerEnumRuleCall_1_1_0());
            	    					
            	    pushFollow(FOLLOW_8);
            	    lv_operator_2_0=ruleOperatorPower();

            	    state._fsp--;


            	    						if (current==null) {
            	    							current = createModelElementForParent(grammarAccess.getPowerFunctionRule());
            	    						}
            	    						set(
            	    							current,
            	    							"operator",
            	    							lv_operator_2_0,
            	    							"de.dlr.sc.virsat.model.calculation.EquationDSL.OperatorPower");
            	    						afterParserOrEnumRuleCall();
            	    					

            	    }


            	    }

            	    // InternalEquationDSL.g:600:4: ( (lv_right_3_0= ruleAExpression ) )
            	    // InternalEquationDSL.g:601:5: (lv_right_3_0= ruleAExpression )
            	    {
            	    // InternalEquationDSL.g:601:5: (lv_right_3_0= ruleAExpression )
            	    // InternalEquationDSL.g:602:6: lv_right_3_0= ruleAExpression
            	    {

            	    						newCompositeNode(grammarAccess.getPowerFunctionAccess().getRightAExpressionParserRuleCall_1_2_0());
            	    					
            	    pushFollow(FOLLOW_11);
            	    lv_right_3_0=ruleAExpression();

            	    state._fsp--;


            	    						if (current==null) {
            	    							current = createModelElementForParent(grammarAccess.getPowerFunctionRule());
            	    						}
            	    						set(
            	    							current,
            	    							"right",
            	    							lv_right_3_0,
            	    							"de.dlr.sc.virsat.model.calculation.EquationDSL.AExpression");
            	    						afterParserOrEnumRuleCall();
            	    					

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
    // InternalEquationDSL.g:624:1: entryRuleParenthesis returns [EObject current=null] : iv_ruleParenthesis= ruleParenthesis EOF ;
    public final EObject entryRuleParenthesis() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleParenthesis = null;


        try {
            // InternalEquationDSL.g:624:52: (iv_ruleParenthesis= ruleParenthesis EOF )
            // InternalEquationDSL.g:625:2: iv_ruleParenthesis= ruleParenthesis EOF
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
    // InternalEquationDSL.g:631:1: ruleParenthesis returns [EObject current=null] : ( () ( (lv_operator_1_0= ruleOperatorMinus ) )? otherlv_2= '(' ( (lv_right_3_0= ruleAdditionAndSubtraction ) ) otherlv_4= ')' ) ;
    public final EObject ruleParenthesis() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        Token otherlv_4=null;
        Enumerator lv_operator_1_0 = null;

        EObject lv_right_3_0 = null;



        	enterRule();

        try {
            // InternalEquationDSL.g:637:2: ( ( () ( (lv_operator_1_0= ruleOperatorMinus ) )? otherlv_2= '(' ( (lv_right_3_0= ruleAdditionAndSubtraction ) ) otherlv_4= ')' ) )
            // InternalEquationDSL.g:638:2: ( () ( (lv_operator_1_0= ruleOperatorMinus ) )? otherlv_2= '(' ( (lv_right_3_0= ruleAdditionAndSubtraction ) ) otherlv_4= ')' )
            {
            // InternalEquationDSL.g:638:2: ( () ( (lv_operator_1_0= ruleOperatorMinus ) )? otherlv_2= '(' ( (lv_right_3_0= ruleAdditionAndSubtraction ) ) otherlv_4= ')' )
            // InternalEquationDSL.g:639:3: () ( (lv_operator_1_0= ruleOperatorMinus ) )? otherlv_2= '(' ( (lv_right_3_0= ruleAdditionAndSubtraction ) ) otherlv_4= ')'
            {
            // InternalEquationDSL.g:639:3: ()
            // InternalEquationDSL.g:640:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getParenthesisAccess().getParenthesisAction_0(),
            					current);
            			

            }

            // InternalEquationDSL.g:646:3: ( (lv_operator_1_0= ruleOperatorMinus ) )?
            int alt9=2;
            int LA9_0 = input.LA(1);

            if ( (LA9_0==22) ) {
                alt9=1;
            }
            switch (alt9) {
                case 1 :
                    // InternalEquationDSL.g:647:4: (lv_operator_1_0= ruleOperatorMinus )
                    {
                    // InternalEquationDSL.g:647:4: (lv_operator_1_0= ruleOperatorMinus )
                    // InternalEquationDSL.g:648:5: lv_operator_1_0= ruleOperatorMinus
                    {

                    					newCompositeNode(grammarAccess.getParenthesisAccess().getOperatorOperatorMinusEnumRuleCall_1_0());
                    				
                    pushFollow(FOLLOW_12);
                    lv_operator_1_0=ruleOperatorMinus();

                    state._fsp--;


                    					if (current==null) {
                    						current = createModelElementForParent(grammarAccess.getParenthesisRule());
                    					}
                    					set(
                    						current,
                    						"operator",
                    						lv_operator_1_0,
                    						"de.dlr.sc.virsat.model.calculation.EquationDSL.OperatorMinus");
                    					afterParserOrEnumRuleCall();
                    				

                    }


                    }
                    break;

            }

            otherlv_2=(Token)match(input,16,FOLLOW_8); 

            			newLeafNode(otherlv_2, grammarAccess.getParenthesisAccess().getLeftParenthesisKeyword_2());
            		
            // InternalEquationDSL.g:669:3: ( (lv_right_3_0= ruleAdditionAndSubtraction ) )
            // InternalEquationDSL.g:670:4: (lv_right_3_0= ruleAdditionAndSubtraction )
            {
            // InternalEquationDSL.g:670:4: (lv_right_3_0= ruleAdditionAndSubtraction )
            // InternalEquationDSL.g:671:5: lv_right_3_0= ruleAdditionAndSubtraction
            {

            					newCompositeNode(grammarAccess.getParenthesisAccess().getRightAdditionAndSubtractionParserRuleCall_3_0());
            				
            pushFollow(FOLLOW_13);
            lv_right_3_0=ruleAdditionAndSubtraction();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getParenthesisRule());
            					}
            					set(
            						current,
            						"right",
            						lv_right_3_0,
            						"de.dlr.sc.virsat.model.calculation.EquationDSL.AdditionAndSubtraction");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            otherlv_4=(Token)match(input,17,FOLLOW_2); 

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
    // InternalEquationDSL.g:696:1: entryRuleAExpression returns [EObject current=null] : iv_ruleAExpression= ruleAExpression EOF ;
    public final EObject entryRuleAExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleAExpression = null;


        try {
            // InternalEquationDSL.g:696:52: (iv_ruleAExpression= ruleAExpression EOF )
            // InternalEquationDSL.g:697:2: iv_ruleAExpression= ruleAExpression EOF
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
    // InternalEquationDSL.g:703:1: ruleAExpression returns [EObject current=null] : (this_ALiteral_0= ruleALiteral | this_ReferencedInput_1= ruleReferencedInput | this_Parenthesis_2= ruleParenthesis | this_Function_3= ruleFunction | this_AAdvancedFunction_4= ruleAAdvancedFunction ) ;
    public final EObject ruleAExpression() throws RecognitionException {
        EObject current = null;

        EObject this_ALiteral_0 = null;

        EObject this_ReferencedInput_1 = null;

        EObject this_Parenthesis_2 = null;

        EObject this_Function_3 = null;

        EObject this_AAdvancedFunction_4 = null;



        	enterRule();

        try {
            // InternalEquationDSL.g:709:2: ( (this_ALiteral_0= ruleALiteral | this_ReferencedInput_1= ruleReferencedInput | this_Parenthesis_2= ruleParenthesis | this_Function_3= ruleFunction | this_AAdvancedFunction_4= ruleAAdvancedFunction ) )
            // InternalEquationDSL.g:710:2: (this_ALiteral_0= ruleALiteral | this_ReferencedInput_1= ruleReferencedInput | this_Parenthesis_2= ruleParenthesis | this_Function_3= ruleFunction | this_AAdvancedFunction_4= ruleAAdvancedFunction )
            {
            // InternalEquationDSL.g:710:2: (this_ALiteral_0= ruleALiteral | this_ReferencedInput_1= ruleReferencedInput | this_Parenthesis_2= ruleParenthesis | this_Function_3= ruleFunction | this_AAdvancedFunction_4= ruleAAdvancedFunction )
            int alt10=5;
            switch ( input.LA(1) ) {
            case 22:
                {
                int LA10_1 = input.LA(2);

                if ( (LA10_1==RULE_INT) ) {
                    alt10=1;
                }
                else if ( (LA10_1==16) ) {
                    alt10=3;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 10, 1, input);

                    throw nvae;
                }
                }
                break;
            case RULE_INT:
            case 23:
            case 24:
                {
                alt10=1;
                }
                break;
            case RULE_ID:
                {
                int LA10_3 = input.LA(2);

                if ( (LA10_3==16||LA10_3==19) ) {
                    alt10=5;
                }
                else if ( (LA10_3==EOF||LA10_3==12||(LA10_3>=17 && LA10_3<=18)||(LA10_3>=21 && LA10_3<=22)||(LA10_3>=25 && LA10_3<=28)) ) {
                    alt10=2;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 10, 3, input);

                    throw nvae;
                }
                }
                break;
            case 16:
                {
                alt10=3;
                }
                break;
            case 29:
            case 30:
            case 31:
            case 32:
            case 33:
            case 34:
            case 35:
            case 36:
            case 37:
            case 38:
            case 39:
                {
                alt10=4;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 10, 0, input);

                throw nvae;
            }

            switch (alt10) {
                case 1 :
                    // InternalEquationDSL.g:711:3: this_ALiteral_0= ruleALiteral
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
                    // InternalEquationDSL.g:720:3: this_ReferencedInput_1= ruleReferencedInput
                    {

                    			newCompositeNode(grammarAccess.getAExpressionAccess().getReferencedInputParserRuleCall_1());
                    		
                    pushFollow(FOLLOW_2);
                    this_ReferencedInput_1=ruleReferencedInput();

                    state._fsp--;


                    			current = this_ReferencedInput_1;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 3 :
                    // InternalEquationDSL.g:729:3: this_Parenthesis_2= ruleParenthesis
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
                    // InternalEquationDSL.g:738:3: this_Function_3= ruleFunction
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
                    // InternalEquationDSL.g:747:3: this_AAdvancedFunction_4= ruleAAdvancedFunction
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


    // $ANTLR start "entryRuleReferencedInput"
    // InternalEquationDSL.g:759:1: entryRuleReferencedInput returns [EObject current=null] : iv_ruleReferencedInput= ruleReferencedInput EOF ;
    public final EObject entryRuleReferencedInput() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleReferencedInput = null;


        try {
            // InternalEquationDSL.g:759:56: (iv_ruleReferencedInput= ruleReferencedInput EOF )
            // InternalEquationDSL.g:760:2: iv_ruleReferencedInput= ruleReferencedInput EOF
            {
             newCompositeNode(grammarAccess.getReferencedInputRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleReferencedInput=ruleReferencedInput();

            state._fsp--;

             current =iv_ruleReferencedInput; 
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
    // $ANTLR end "entryRuleReferencedInput"


    // $ANTLR start "ruleReferencedInput"
    // InternalEquationDSL.g:766:1: ruleReferencedInput returns [EObject current=null] : ( ( ruleQualifiedName ) ) ;
    public final EObject ruleReferencedInput() throws RecognitionException {
        EObject current = null;


        	enterRule();

        try {
            // InternalEquationDSL.g:772:2: ( ( ( ruleQualifiedName ) ) )
            // InternalEquationDSL.g:773:2: ( ( ruleQualifiedName ) )
            {
            // InternalEquationDSL.g:773:2: ( ( ruleQualifiedName ) )
            // InternalEquationDSL.g:774:3: ( ruleQualifiedName )
            {
            // InternalEquationDSL.g:774:3: ( ruleQualifiedName )
            // InternalEquationDSL.g:775:4: ruleQualifiedName
            {

            				if (current==null) {
            					current = createModelElement(grammarAccess.getReferencedInputRule());
            				}
            			

            				newCompositeNode(grammarAccess.getReferencedInputAccess().getReferenceIEquationInputCrossReference_0());
            			
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
    // $ANTLR end "ruleReferencedInput"


    // $ANTLR start "entryRuleALiteral"
    // InternalEquationDSL.g:792:1: entryRuleALiteral returns [EObject current=null] : iv_ruleALiteral= ruleALiteral EOF ;
    public final EObject entryRuleALiteral() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleALiteral = null;


        try {
            // InternalEquationDSL.g:792:49: (iv_ruleALiteral= ruleALiteral EOF )
            // InternalEquationDSL.g:793:2: iv_ruleALiteral= ruleALiteral EOF
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
    // InternalEquationDSL.g:799:1: ruleALiteral returns [EObject current=null] : (this_NumberLiteral_0= ruleNumberLiteral | this_ValueE_1= ruleValueE | this_ValuePi_2= ruleValuePi ) ;
    public final EObject ruleALiteral() throws RecognitionException {
        EObject current = null;

        EObject this_NumberLiteral_0 = null;

        EObject this_ValueE_1 = null;

        EObject this_ValuePi_2 = null;



        	enterRule();

        try {
            // InternalEquationDSL.g:805:2: ( (this_NumberLiteral_0= ruleNumberLiteral | this_ValueE_1= ruleValueE | this_ValuePi_2= ruleValuePi ) )
            // InternalEquationDSL.g:806:2: (this_NumberLiteral_0= ruleNumberLiteral | this_ValueE_1= ruleValueE | this_ValuePi_2= ruleValuePi )
            {
            // InternalEquationDSL.g:806:2: (this_NumberLiteral_0= ruleNumberLiteral | this_ValueE_1= ruleValueE | this_ValuePi_2= ruleValuePi )
            int alt11=3;
            switch ( input.LA(1) ) {
            case RULE_INT:
            case 22:
                {
                alt11=1;
                }
                break;
            case 24:
                {
                alt11=2;
                }
                break;
            case 23:
                {
                alt11=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 11, 0, input);

                throw nvae;
            }

            switch (alt11) {
                case 1 :
                    // InternalEquationDSL.g:807:3: this_NumberLiteral_0= ruleNumberLiteral
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
                    // InternalEquationDSL.g:816:3: this_ValueE_1= ruleValueE
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
                    // InternalEquationDSL.g:825:3: this_ValuePi_2= ruleValuePi
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
    // InternalEquationDSL.g:837:1: entryRuleFunction returns [EObject current=null] : iv_ruleFunction= ruleFunction EOF ;
    public final EObject entryRuleFunction() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleFunction = null;


        try {
            // InternalEquationDSL.g:837:49: (iv_ruleFunction= ruleFunction EOF )
            // InternalEquationDSL.g:838:2: iv_ruleFunction= ruleFunction EOF
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
    // InternalEquationDSL.g:844:1: ruleFunction returns [EObject current=null] : ( () ( ( (lv_operator_1_1= ruleOperatorCos | lv_operator_1_2= ruleOperatorSin | lv_operator_1_3= ruleOperatorTan | lv_operator_1_4= ruleOperatorAtan | lv_operator_1_5= ruleOperatorAcos | lv_operator_1_6= ruleOperatorAsin | lv_operator_1_7= ruleOperatorSqrt | lv_operator_1_8= ruleOperatorLog | lv_operator_1_9= ruleOperatorLn | lv_operator_1_10= ruleOperatorLd | lv_operator_1_11= ruleOperatorExp ) ) ) otherlv_2= '(' ( (lv_right_3_0= ruleAdditionAndSubtraction ) ) otherlv_4= ')' ) ;
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
            // InternalEquationDSL.g:850:2: ( ( () ( ( (lv_operator_1_1= ruleOperatorCos | lv_operator_1_2= ruleOperatorSin | lv_operator_1_3= ruleOperatorTan | lv_operator_1_4= ruleOperatorAtan | lv_operator_1_5= ruleOperatorAcos | lv_operator_1_6= ruleOperatorAsin | lv_operator_1_7= ruleOperatorSqrt | lv_operator_1_8= ruleOperatorLog | lv_operator_1_9= ruleOperatorLn | lv_operator_1_10= ruleOperatorLd | lv_operator_1_11= ruleOperatorExp ) ) ) otherlv_2= '(' ( (lv_right_3_0= ruleAdditionAndSubtraction ) ) otherlv_4= ')' ) )
            // InternalEquationDSL.g:851:2: ( () ( ( (lv_operator_1_1= ruleOperatorCos | lv_operator_1_2= ruleOperatorSin | lv_operator_1_3= ruleOperatorTan | lv_operator_1_4= ruleOperatorAtan | lv_operator_1_5= ruleOperatorAcos | lv_operator_1_6= ruleOperatorAsin | lv_operator_1_7= ruleOperatorSqrt | lv_operator_1_8= ruleOperatorLog | lv_operator_1_9= ruleOperatorLn | lv_operator_1_10= ruleOperatorLd | lv_operator_1_11= ruleOperatorExp ) ) ) otherlv_2= '(' ( (lv_right_3_0= ruleAdditionAndSubtraction ) ) otherlv_4= ')' )
            {
            // InternalEquationDSL.g:851:2: ( () ( ( (lv_operator_1_1= ruleOperatorCos | lv_operator_1_2= ruleOperatorSin | lv_operator_1_3= ruleOperatorTan | lv_operator_1_4= ruleOperatorAtan | lv_operator_1_5= ruleOperatorAcos | lv_operator_1_6= ruleOperatorAsin | lv_operator_1_7= ruleOperatorSqrt | lv_operator_1_8= ruleOperatorLog | lv_operator_1_9= ruleOperatorLn | lv_operator_1_10= ruleOperatorLd | lv_operator_1_11= ruleOperatorExp ) ) ) otherlv_2= '(' ( (lv_right_3_0= ruleAdditionAndSubtraction ) ) otherlv_4= ')' )
            // InternalEquationDSL.g:852:3: () ( ( (lv_operator_1_1= ruleOperatorCos | lv_operator_1_2= ruleOperatorSin | lv_operator_1_3= ruleOperatorTan | lv_operator_1_4= ruleOperatorAtan | lv_operator_1_5= ruleOperatorAcos | lv_operator_1_6= ruleOperatorAsin | lv_operator_1_7= ruleOperatorSqrt | lv_operator_1_8= ruleOperatorLog | lv_operator_1_9= ruleOperatorLn | lv_operator_1_10= ruleOperatorLd | lv_operator_1_11= ruleOperatorExp ) ) ) otherlv_2= '(' ( (lv_right_3_0= ruleAdditionAndSubtraction ) ) otherlv_4= ')'
            {
            // InternalEquationDSL.g:852:3: ()
            // InternalEquationDSL.g:853:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getFunctionAccess().getFunctionAction_0(),
            					current);
            			

            }

            // InternalEquationDSL.g:859:3: ( ( (lv_operator_1_1= ruleOperatorCos | lv_operator_1_2= ruleOperatorSin | lv_operator_1_3= ruleOperatorTan | lv_operator_1_4= ruleOperatorAtan | lv_operator_1_5= ruleOperatorAcos | lv_operator_1_6= ruleOperatorAsin | lv_operator_1_7= ruleOperatorSqrt | lv_operator_1_8= ruleOperatorLog | lv_operator_1_9= ruleOperatorLn | lv_operator_1_10= ruleOperatorLd | lv_operator_1_11= ruleOperatorExp ) ) )
            // InternalEquationDSL.g:860:4: ( (lv_operator_1_1= ruleOperatorCos | lv_operator_1_2= ruleOperatorSin | lv_operator_1_3= ruleOperatorTan | lv_operator_1_4= ruleOperatorAtan | lv_operator_1_5= ruleOperatorAcos | lv_operator_1_6= ruleOperatorAsin | lv_operator_1_7= ruleOperatorSqrt | lv_operator_1_8= ruleOperatorLog | lv_operator_1_9= ruleOperatorLn | lv_operator_1_10= ruleOperatorLd | lv_operator_1_11= ruleOperatorExp ) )
            {
            // InternalEquationDSL.g:860:4: ( (lv_operator_1_1= ruleOperatorCos | lv_operator_1_2= ruleOperatorSin | lv_operator_1_3= ruleOperatorTan | lv_operator_1_4= ruleOperatorAtan | lv_operator_1_5= ruleOperatorAcos | lv_operator_1_6= ruleOperatorAsin | lv_operator_1_7= ruleOperatorSqrt | lv_operator_1_8= ruleOperatorLog | lv_operator_1_9= ruleOperatorLn | lv_operator_1_10= ruleOperatorLd | lv_operator_1_11= ruleOperatorExp ) )
            // InternalEquationDSL.g:861:5: (lv_operator_1_1= ruleOperatorCos | lv_operator_1_2= ruleOperatorSin | lv_operator_1_3= ruleOperatorTan | lv_operator_1_4= ruleOperatorAtan | lv_operator_1_5= ruleOperatorAcos | lv_operator_1_6= ruleOperatorAsin | lv_operator_1_7= ruleOperatorSqrt | lv_operator_1_8= ruleOperatorLog | lv_operator_1_9= ruleOperatorLn | lv_operator_1_10= ruleOperatorLd | lv_operator_1_11= ruleOperatorExp )
            {
            // InternalEquationDSL.g:861:5: (lv_operator_1_1= ruleOperatorCos | lv_operator_1_2= ruleOperatorSin | lv_operator_1_3= ruleOperatorTan | lv_operator_1_4= ruleOperatorAtan | lv_operator_1_5= ruleOperatorAcos | lv_operator_1_6= ruleOperatorAsin | lv_operator_1_7= ruleOperatorSqrt | lv_operator_1_8= ruleOperatorLog | lv_operator_1_9= ruleOperatorLn | lv_operator_1_10= ruleOperatorLd | lv_operator_1_11= ruleOperatorExp )
            int alt12=11;
            switch ( input.LA(1) ) {
            case 29:
                {
                alt12=1;
                }
                break;
            case 30:
                {
                alt12=2;
                }
                break;
            case 31:
                {
                alt12=3;
                }
                break;
            case 32:
                {
                alt12=4;
                }
                break;
            case 33:
                {
                alt12=5;
                }
                break;
            case 34:
                {
                alt12=6;
                }
                break;
            case 35:
                {
                alt12=7;
                }
                break;
            case 36:
                {
                alt12=8;
                }
                break;
            case 37:
                {
                alt12=9;
                }
                break;
            case 39:
                {
                alt12=10;
                }
                break;
            case 38:
                {
                alt12=11;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 12, 0, input);

                throw nvae;
            }

            switch (alt12) {
                case 1 :
                    // InternalEquationDSL.g:862:6: lv_operator_1_1= ruleOperatorCos
                    {

                    						newCompositeNode(grammarAccess.getFunctionAccess().getOperatorOperatorCosEnumRuleCall_1_0_0());
                    					
                    pushFollow(FOLLOW_12);
                    lv_operator_1_1=ruleOperatorCos();

                    state._fsp--;


                    						if (current==null) {
                    							current = createModelElementForParent(grammarAccess.getFunctionRule());
                    						}
                    						set(
                    							current,
                    							"operator",
                    							lv_operator_1_1,
                    							"de.dlr.sc.virsat.model.calculation.EquationDSL.OperatorCos");
                    						afterParserOrEnumRuleCall();
                    					

                    }
                    break;
                case 2 :
                    // InternalEquationDSL.g:878:6: lv_operator_1_2= ruleOperatorSin
                    {

                    						newCompositeNode(grammarAccess.getFunctionAccess().getOperatorOperatorSinEnumRuleCall_1_0_1());
                    					
                    pushFollow(FOLLOW_12);
                    lv_operator_1_2=ruleOperatorSin();

                    state._fsp--;


                    						if (current==null) {
                    							current = createModelElementForParent(grammarAccess.getFunctionRule());
                    						}
                    						set(
                    							current,
                    							"operator",
                    							lv_operator_1_2,
                    							"de.dlr.sc.virsat.model.calculation.EquationDSL.OperatorSin");
                    						afterParserOrEnumRuleCall();
                    					

                    }
                    break;
                case 3 :
                    // InternalEquationDSL.g:894:6: lv_operator_1_3= ruleOperatorTan
                    {

                    						newCompositeNode(grammarAccess.getFunctionAccess().getOperatorOperatorTanEnumRuleCall_1_0_2());
                    					
                    pushFollow(FOLLOW_12);
                    lv_operator_1_3=ruleOperatorTan();

                    state._fsp--;


                    						if (current==null) {
                    							current = createModelElementForParent(grammarAccess.getFunctionRule());
                    						}
                    						set(
                    							current,
                    							"operator",
                    							lv_operator_1_3,
                    							"de.dlr.sc.virsat.model.calculation.EquationDSL.OperatorTan");
                    						afterParserOrEnumRuleCall();
                    					

                    }
                    break;
                case 4 :
                    // InternalEquationDSL.g:910:6: lv_operator_1_4= ruleOperatorAtan
                    {

                    						newCompositeNode(grammarAccess.getFunctionAccess().getOperatorOperatorAtanEnumRuleCall_1_0_3());
                    					
                    pushFollow(FOLLOW_12);
                    lv_operator_1_4=ruleOperatorAtan();

                    state._fsp--;


                    						if (current==null) {
                    							current = createModelElementForParent(grammarAccess.getFunctionRule());
                    						}
                    						set(
                    							current,
                    							"operator",
                    							lv_operator_1_4,
                    							"de.dlr.sc.virsat.model.calculation.EquationDSL.OperatorAtan");
                    						afterParserOrEnumRuleCall();
                    					

                    }
                    break;
                case 5 :
                    // InternalEquationDSL.g:926:6: lv_operator_1_5= ruleOperatorAcos
                    {

                    						newCompositeNode(grammarAccess.getFunctionAccess().getOperatorOperatorAcosEnumRuleCall_1_0_4());
                    					
                    pushFollow(FOLLOW_12);
                    lv_operator_1_5=ruleOperatorAcos();

                    state._fsp--;


                    						if (current==null) {
                    							current = createModelElementForParent(grammarAccess.getFunctionRule());
                    						}
                    						set(
                    							current,
                    							"operator",
                    							lv_operator_1_5,
                    							"de.dlr.sc.virsat.model.calculation.EquationDSL.OperatorAcos");
                    						afterParserOrEnumRuleCall();
                    					

                    }
                    break;
                case 6 :
                    // InternalEquationDSL.g:942:6: lv_operator_1_6= ruleOperatorAsin
                    {

                    						newCompositeNode(grammarAccess.getFunctionAccess().getOperatorOperatorAsinEnumRuleCall_1_0_5());
                    					
                    pushFollow(FOLLOW_12);
                    lv_operator_1_6=ruleOperatorAsin();

                    state._fsp--;


                    						if (current==null) {
                    							current = createModelElementForParent(grammarAccess.getFunctionRule());
                    						}
                    						set(
                    							current,
                    							"operator",
                    							lv_operator_1_6,
                    							"de.dlr.sc.virsat.model.calculation.EquationDSL.OperatorAsin");
                    						afterParserOrEnumRuleCall();
                    					

                    }
                    break;
                case 7 :
                    // InternalEquationDSL.g:958:6: lv_operator_1_7= ruleOperatorSqrt
                    {

                    						newCompositeNode(grammarAccess.getFunctionAccess().getOperatorOperatorSqrtEnumRuleCall_1_0_6());
                    					
                    pushFollow(FOLLOW_12);
                    lv_operator_1_7=ruleOperatorSqrt();

                    state._fsp--;


                    						if (current==null) {
                    							current = createModelElementForParent(grammarAccess.getFunctionRule());
                    						}
                    						set(
                    							current,
                    							"operator",
                    							lv_operator_1_7,
                    							"de.dlr.sc.virsat.model.calculation.EquationDSL.OperatorSqrt");
                    						afterParserOrEnumRuleCall();
                    					

                    }
                    break;
                case 8 :
                    // InternalEquationDSL.g:974:6: lv_operator_1_8= ruleOperatorLog
                    {

                    						newCompositeNode(grammarAccess.getFunctionAccess().getOperatorOperatorLogEnumRuleCall_1_0_7());
                    					
                    pushFollow(FOLLOW_12);
                    lv_operator_1_8=ruleOperatorLog();

                    state._fsp--;


                    						if (current==null) {
                    							current = createModelElementForParent(grammarAccess.getFunctionRule());
                    						}
                    						set(
                    							current,
                    							"operator",
                    							lv_operator_1_8,
                    							"de.dlr.sc.virsat.model.calculation.EquationDSL.OperatorLog");
                    						afterParserOrEnumRuleCall();
                    					

                    }
                    break;
                case 9 :
                    // InternalEquationDSL.g:990:6: lv_operator_1_9= ruleOperatorLn
                    {

                    						newCompositeNode(grammarAccess.getFunctionAccess().getOperatorOperatorLnEnumRuleCall_1_0_8());
                    					
                    pushFollow(FOLLOW_12);
                    lv_operator_1_9=ruleOperatorLn();

                    state._fsp--;


                    						if (current==null) {
                    							current = createModelElementForParent(grammarAccess.getFunctionRule());
                    						}
                    						set(
                    							current,
                    							"operator",
                    							lv_operator_1_9,
                    							"de.dlr.sc.virsat.model.calculation.EquationDSL.OperatorLn");
                    						afterParserOrEnumRuleCall();
                    					

                    }
                    break;
                case 10 :
                    // InternalEquationDSL.g:1006:6: lv_operator_1_10= ruleOperatorLd
                    {

                    						newCompositeNode(grammarAccess.getFunctionAccess().getOperatorOperatorLdEnumRuleCall_1_0_9());
                    					
                    pushFollow(FOLLOW_12);
                    lv_operator_1_10=ruleOperatorLd();

                    state._fsp--;


                    						if (current==null) {
                    							current = createModelElementForParent(grammarAccess.getFunctionRule());
                    						}
                    						set(
                    							current,
                    							"operator",
                    							lv_operator_1_10,
                    							"de.dlr.sc.virsat.model.calculation.EquationDSL.OperatorLd");
                    						afterParserOrEnumRuleCall();
                    					

                    }
                    break;
                case 11 :
                    // InternalEquationDSL.g:1022:6: lv_operator_1_11= ruleOperatorExp
                    {

                    						newCompositeNode(grammarAccess.getFunctionAccess().getOperatorOperatorExpEnumRuleCall_1_0_10());
                    					
                    pushFollow(FOLLOW_12);
                    lv_operator_1_11=ruleOperatorExp();

                    state._fsp--;


                    						if (current==null) {
                    							current = createModelElementForParent(grammarAccess.getFunctionRule());
                    						}
                    						set(
                    							current,
                    							"operator",
                    							lv_operator_1_11,
                    							"de.dlr.sc.virsat.model.calculation.EquationDSL.OperatorExp");
                    						afterParserOrEnumRuleCall();
                    					

                    }
                    break;

            }


            }


            }

            otherlv_2=(Token)match(input,16,FOLLOW_8); 

            			newLeafNode(otherlv_2, grammarAccess.getFunctionAccess().getLeftParenthesisKeyword_2());
            		
            // InternalEquationDSL.g:1044:3: ( (lv_right_3_0= ruleAdditionAndSubtraction ) )
            // InternalEquationDSL.g:1045:4: (lv_right_3_0= ruleAdditionAndSubtraction )
            {
            // InternalEquationDSL.g:1045:4: (lv_right_3_0= ruleAdditionAndSubtraction )
            // InternalEquationDSL.g:1046:5: lv_right_3_0= ruleAdditionAndSubtraction
            {

            					newCompositeNode(grammarAccess.getFunctionAccess().getRightAdditionAndSubtractionParserRuleCall_3_0());
            				
            pushFollow(FOLLOW_13);
            lv_right_3_0=ruleAdditionAndSubtraction();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getFunctionRule());
            					}
            					set(
            						current,
            						"right",
            						lv_right_3_0,
            						"de.dlr.sc.virsat.model.calculation.EquationDSL.AdditionAndSubtraction");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            otherlv_4=(Token)match(input,17,FOLLOW_2); 

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
    // InternalEquationDSL.g:1071:1: entryRuleAAdvancedFunction returns [EObject current=null] : iv_ruleAAdvancedFunction= ruleAAdvancedFunction EOF ;
    public final EObject entryRuleAAdvancedFunction() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleAAdvancedFunction = null;


        try {
            // InternalEquationDSL.g:1071:58: (iv_ruleAAdvancedFunction= ruleAAdvancedFunction EOF )
            // InternalEquationDSL.g:1072:2: iv_ruleAAdvancedFunction= ruleAAdvancedFunction EOF
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
    // InternalEquationDSL.g:1078:1: ruleAAdvancedFunction returns [EObject current=null] : (this_AdvancedFunction_0= ruleAdvancedFunction | this_SetFunction_1= ruleSetFunction ) ;
    public final EObject ruleAAdvancedFunction() throws RecognitionException {
        EObject current = null;

        EObject this_AdvancedFunction_0 = null;

        EObject this_SetFunction_1 = null;



        	enterRule();

        try {
            // InternalEquationDSL.g:1084:2: ( (this_AdvancedFunction_0= ruleAdvancedFunction | this_SetFunction_1= ruleSetFunction ) )
            // InternalEquationDSL.g:1085:2: (this_AdvancedFunction_0= ruleAdvancedFunction | this_SetFunction_1= ruleSetFunction )
            {
            // InternalEquationDSL.g:1085:2: (this_AdvancedFunction_0= ruleAdvancedFunction | this_SetFunction_1= ruleSetFunction )
            int alt13=2;
            int LA13_0 = input.LA(1);

            if ( (LA13_0==RULE_ID) ) {
                int LA13_1 = input.LA(2);

                if ( (LA13_1==16) ) {
                    alt13=1;
                }
                else if ( (LA13_1==19) ) {
                    alt13=2;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 13, 1, input);

                    throw nvae;
                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 13, 0, input);

                throw nvae;
            }
            switch (alt13) {
                case 1 :
                    // InternalEquationDSL.g:1086:3: this_AdvancedFunction_0= ruleAdvancedFunction
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
                    // InternalEquationDSL.g:1095:3: this_SetFunction_1= ruleSetFunction
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
    // InternalEquationDSL.g:1107:1: entryRuleAdvancedFunction returns [EObject current=null] : iv_ruleAdvancedFunction= ruleAdvancedFunction EOF ;
    public final EObject entryRuleAdvancedFunction() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleAdvancedFunction = null;


        try {
            // InternalEquationDSL.g:1107:57: (iv_ruleAdvancedFunction= ruleAdvancedFunction EOF )
            // InternalEquationDSL.g:1108:2: iv_ruleAdvancedFunction= ruleAdvancedFunction EOF
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
    // InternalEquationDSL.g:1114:1: ruleAdvancedFunction returns [EObject current=null] : ( () ( (lv_operator_1_0= RULE_ID ) ) otherlv_2= '(' ( (lv_inputs_3_0= ruleAdditionAndSubtraction ) ) (otherlv_4= ',' ( (lv_inputs_5_0= ruleAdditionAndSubtraction ) ) )* otherlv_6= ')' ) ;
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
            // InternalEquationDSL.g:1120:2: ( ( () ( (lv_operator_1_0= RULE_ID ) ) otherlv_2= '(' ( (lv_inputs_3_0= ruleAdditionAndSubtraction ) ) (otherlv_4= ',' ( (lv_inputs_5_0= ruleAdditionAndSubtraction ) ) )* otherlv_6= ')' ) )
            // InternalEquationDSL.g:1121:2: ( () ( (lv_operator_1_0= RULE_ID ) ) otherlv_2= '(' ( (lv_inputs_3_0= ruleAdditionAndSubtraction ) ) (otherlv_4= ',' ( (lv_inputs_5_0= ruleAdditionAndSubtraction ) ) )* otherlv_6= ')' )
            {
            // InternalEquationDSL.g:1121:2: ( () ( (lv_operator_1_0= RULE_ID ) ) otherlv_2= '(' ( (lv_inputs_3_0= ruleAdditionAndSubtraction ) ) (otherlv_4= ',' ( (lv_inputs_5_0= ruleAdditionAndSubtraction ) ) )* otherlv_6= ')' )
            // InternalEquationDSL.g:1122:3: () ( (lv_operator_1_0= RULE_ID ) ) otherlv_2= '(' ( (lv_inputs_3_0= ruleAdditionAndSubtraction ) ) (otherlv_4= ',' ( (lv_inputs_5_0= ruleAdditionAndSubtraction ) ) )* otherlv_6= ')'
            {
            // InternalEquationDSL.g:1122:3: ()
            // InternalEquationDSL.g:1123:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getAdvancedFunctionAccess().getAdvancedFunctionAction_0(),
            					current);
            			

            }

            // InternalEquationDSL.g:1129:3: ( (lv_operator_1_0= RULE_ID ) )
            // InternalEquationDSL.g:1130:4: (lv_operator_1_0= RULE_ID )
            {
            // InternalEquationDSL.g:1130:4: (lv_operator_1_0= RULE_ID )
            // InternalEquationDSL.g:1131:5: lv_operator_1_0= RULE_ID
            {
            lv_operator_1_0=(Token)match(input,RULE_ID,FOLLOW_12); 

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

            otherlv_2=(Token)match(input,16,FOLLOW_8); 

            			newLeafNode(otherlv_2, grammarAccess.getAdvancedFunctionAccess().getLeftParenthesisKeyword_2());
            		
            // InternalEquationDSL.g:1151:3: ( (lv_inputs_3_0= ruleAdditionAndSubtraction ) )
            // InternalEquationDSL.g:1152:4: (lv_inputs_3_0= ruleAdditionAndSubtraction )
            {
            // InternalEquationDSL.g:1152:4: (lv_inputs_3_0= ruleAdditionAndSubtraction )
            // InternalEquationDSL.g:1153:5: lv_inputs_3_0= ruleAdditionAndSubtraction
            {

            					newCompositeNode(grammarAccess.getAdvancedFunctionAccess().getInputsAdditionAndSubtractionParserRuleCall_3_0());
            				
            pushFollow(FOLLOW_14);
            lv_inputs_3_0=ruleAdditionAndSubtraction();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getAdvancedFunctionRule());
            					}
            					add(
            						current,
            						"inputs",
            						lv_inputs_3_0,
            						"de.dlr.sc.virsat.model.calculation.EquationDSL.AdditionAndSubtraction");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            // InternalEquationDSL.g:1170:3: (otherlv_4= ',' ( (lv_inputs_5_0= ruleAdditionAndSubtraction ) ) )*
            loop14:
            do {
                int alt14=2;
                int LA14_0 = input.LA(1);

                if ( (LA14_0==18) ) {
                    alt14=1;
                }


                switch (alt14) {
            	case 1 :
            	    // InternalEquationDSL.g:1171:4: otherlv_4= ',' ( (lv_inputs_5_0= ruleAdditionAndSubtraction ) )
            	    {
            	    otherlv_4=(Token)match(input,18,FOLLOW_8); 

            	    				newLeafNode(otherlv_4, grammarAccess.getAdvancedFunctionAccess().getCommaKeyword_4_0());
            	    			
            	    // InternalEquationDSL.g:1175:4: ( (lv_inputs_5_0= ruleAdditionAndSubtraction ) )
            	    // InternalEquationDSL.g:1176:5: (lv_inputs_5_0= ruleAdditionAndSubtraction )
            	    {
            	    // InternalEquationDSL.g:1176:5: (lv_inputs_5_0= ruleAdditionAndSubtraction )
            	    // InternalEquationDSL.g:1177:6: lv_inputs_5_0= ruleAdditionAndSubtraction
            	    {

            	    						newCompositeNode(grammarAccess.getAdvancedFunctionAccess().getInputsAdditionAndSubtractionParserRuleCall_4_1_0());
            	    					
            	    pushFollow(FOLLOW_14);
            	    lv_inputs_5_0=ruleAdditionAndSubtraction();

            	    state._fsp--;


            	    						if (current==null) {
            	    							current = createModelElementForParent(grammarAccess.getAdvancedFunctionRule());
            	    						}
            	    						add(
            	    							current,
            	    							"inputs",
            	    							lv_inputs_5_0,
            	    							"de.dlr.sc.virsat.model.calculation.EquationDSL.AdditionAndSubtraction");
            	    						afterParserOrEnumRuleCall();
            	    					

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop14;
                }
            } while (true);

            otherlv_6=(Token)match(input,17,FOLLOW_2); 

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
    // InternalEquationDSL.g:1203:1: entryRuleSetFunction returns [EObject current=null] : iv_ruleSetFunction= ruleSetFunction EOF ;
    public final EObject entryRuleSetFunction() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleSetFunction = null;


        try {
            // InternalEquationDSL.g:1203:52: (iv_ruleSetFunction= ruleSetFunction EOF )
            // InternalEquationDSL.g:1204:2: iv_ruleSetFunction= ruleSetFunction EOF
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
    // InternalEquationDSL.g:1210:1: ruleSetFunction returns [EObject current=null] : ( () ( (lv_operator_1_0= RULE_ID ) ) otherlv_2= '{' ( ( ruleQualifiedName ) ) (otherlv_4= ',' ( (lv_depth_5_0= RULE_INT ) ) )? (otherlv_6= ',' ( (lv_filterName_7_0= RULE_ID ) ) )? otherlv_8= '}' ) ;
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
            // InternalEquationDSL.g:1216:2: ( ( () ( (lv_operator_1_0= RULE_ID ) ) otherlv_2= '{' ( ( ruleQualifiedName ) ) (otherlv_4= ',' ( (lv_depth_5_0= RULE_INT ) ) )? (otherlv_6= ',' ( (lv_filterName_7_0= RULE_ID ) ) )? otherlv_8= '}' ) )
            // InternalEquationDSL.g:1217:2: ( () ( (lv_operator_1_0= RULE_ID ) ) otherlv_2= '{' ( ( ruleQualifiedName ) ) (otherlv_4= ',' ( (lv_depth_5_0= RULE_INT ) ) )? (otherlv_6= ',' ( (lv_filterName_7_0= RULE_ID ) ) )? otherlv_8= '}' )
            {
            // InternalEquationDSL.g:1217:2: ( () ( (lv_operator_1_0= RULE_ID ) ) otherlv_2= '{' ( ( ruleQualifiedName ) ) (otherlv_4= ',' ( (lv_depth_5_0= RULE_INT ) ) )? (otherlv_6= ',' ( (lv_filterName_7_0= RULE_ID ) ) )? otherlv_8= '}' )
            // InternalEquationDSL.g:1218:3: () ( (lv_operator_1_0= RULE_ID ) ) otherlv_2= '{' ( ( ruleQualifiedName ) ) (otherlv_4= ',' ( (lv_depth_5_0= RULE_INT ) ) )? (otherlv_6= ',' ( (lv_filterName_7_0= RULE_ID ) ) )? otherlv_8= '}'
            {
            // InternalEquationDSL.g:1218:3: ()
            // InternalEquationDSL.g:1219:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getSetFunctionAccess().getSetFunctionAction_0(),
            					current);
            			

            }

            // InternalEquationDSL.g:1225:3: ( (lv_operator_1_0= RULE_ID ) )
            // InternalEquationDSL.g:1226:4: (lv_operator_1_0= RULE_ID )
            {
            // InternalEquationDSL.g:1226:4: (lv_operator_1_0= RULE_ID )
            // InternalEquationDSL.g:1227:5: lv_operator_1_0= RULE_ID
            {
            lv_operator_1_0=(Token)match(input,RULE_ID,FOLLOW_15); 

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

            otherlv_2=(Token)match(input,19,FOLLOW_5); 

            			newLeafNode(otherlv_2, grammarAccess.getSetFunctionAccess().getLeftCurlyBracketKeyword_2());
            		
            // InternalEquationDSL.g:1247:3: ( ( ruleQualifiedName ) )
            // InternalEquationDSL.g:1248:4: ( ruleQualifiedName )
            {
            // InternalEquationDSL.g:1248:4: ( ruleQualifiedName )
            // InternalEquationDSL.g:1249:5: ruleQualifiedName
            {

            					if (current==null) {
            						current = createModelElement(grammarAccess.getSetFunctionRule());
            					}
            				

            					newCompositeNode(grammarAccess.getSetFunctionAccess().getTypeDefinitionATypeDefinitionCrossReference_3_0());
            				
            pushFollow(FOLLOW_16);
            ruleQualifiedName();

            state._fsp--;


            					afterParserOrEnumRuleCall();
            				

            }


            }

            // InternalEquationDSL.g:1263:3: (otherlv_4= ',' ( (lv_depth_5_0= RULE_INT ) ) )?
            int alt15=2;
            int LA15_0 = input.LA(1);

            if ( (LA15_0==18) ) {
                int LA15_1 = input.LA(2);

                if ( (LA15_1==RULE_INT) ) {
                    alt15=1;
                }
            }
            switch (alt15) {
                case 1 :
                    // InternalEquationDSL.g:1264:4: otherlv_4= ',' ( (lv_depth_5_0= RULE_INT ) )
                    {
                    otherlv_4=(Token)match(input,18,FOLLOW_17); 

                    				newLeafNode(otherlv_4, grammarAccess.getSetFunctionAccess().getCommaKeyword_4_0());
                    			
                    // InternalEquationDSL.g:1268:4: ( (lv_depth_5_0= RULE_INT ) )
                    // InternalEquationDSL.g:1269:5: (lv_depth_5_0= RULE_INT )
                    {
                    // InternalEquationDSL.g:1269:5: (lv_depth_5_0= RULE_INT )
                    // InternalEquationDSL.g:1270:6: lv_depth_5_0= RULE_INT
                    {
                    lv_depth_5_0=(Token)match(input,RULE_INT,FOLLOW_16); 

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

            // InternalEquationDSL.g:1287:3: (otherlv_6= ',' ( (lv_filterName_7_0= RULE_ID ) ) )?
            int alt16=2;
            int LA16_0 = input.LA(1);

            if ( (LA16_0==18) ) {
                alt16=1;
            }
            switch (alt16) {
                case 1 :
                    // InternalEquationDSL.g:1288:4: otherlv_6= ',' ( (lv_filterName_7_0= RULE_ID ) )
                    {
                    otherlv_6=(Token)match(input,18,FOLLOW_5); 

                    				newLeafNode(otherlv_6, grammarAccess.getSetFunctionAccess().getCommaKeyword_5_0());
                    			
                    // InternalEquationDSL.g:1292:4: ( (lv_filterName_7_0= RULE_ID ) )
                    // InternalEquationDSL.g:1293:5: (lv_filterName_7_0= RULE_ID )
                    {
                    // InternalEquationDSL.g:1293:5: (lv_filterName_7_0= RULE_ID )
                    // InternalEquationDSL.g:1294:6: lv_filterName_7_0= RULE_ID
                    {
                    lv_filterName_7_0=(Token)match(input,RULE_ID,FOLLOW_18); 

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

            otherlv_8=(Token)match(input,20,FOLLOW_2); 

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
    // InternalEquationDSL.g:1319:1: entryRuleNumberLiteral returns [EObject current=null] : iv_ruleNumberLiteral= ruleNumberLiteral EOF ;
    public final EObject entryRuleNumberLiteral() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleNumberLiteral = null;


        try {
            // InternalEquationDSL.g:1319:54: (iv_ruleNumberLiteral= ruleNumberLiteral EOF )
            // InternalEquationDSL.g:1320:2: iv_ruleNumberLiteral= ruleNumberLiteral EOF
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
    // InternalEquationDSL.g:1326:1: ruleNumberLiteral returns [EObject current=null] : ( () ( (lv_value_1_0= ruleNumberLiteralString ) ) ) ;
    public final EObject ruleNumberLiteral() throws RecognitionException {
        EObject current = null;

        AntlrDatatypeRuleToken lv_value_1_0 = null;



        	enterRule();

        try {
            // InternalEquationDSL.g:1332:2: ( ( () ( (lv_value_1_0= ruleNumberLiteralString ) ) ) )
            // InternalEquationDSL.g:1333:2: ( () ( (lv_value_1_0= ruleNumberLiteralString ) ) )
            {
            // InternalEquationDSL.g:1333:2: ( () ( (lv_value_1_0= ruleNumberLiteralString ) ) )
            // InternalEquationDSL.g:1334:3: () ( (lv_value_1_0= ruleNumberLiteralString ) )
            {
            // InternalEquationDSL.g:1334:3: ()
            // InternalEquationDSL.g:1335:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getNumberLiteralAccess().getNumberLiteralAction_0(),
            					current);
            			

            }

            // InternalEquationDSL.g:1341:3: ( (lv_value_1_0= ruleNumberLiteralString ) )
            // InternalEquationDSL.g:1342:4: (lv_value_1_0= ruleNumberLiteralString )
            {
            // InternalEquationDSL.g:1342:4: (lv_value_1_0= ruleNumberLiteralString )
            // InternalEquationDSL.g:1343:5: lv_value_1_0= ruleNumberLiteralString
            {

            					newCompositeNode(grammarAccess.getNumberLiteralAccess().getValueNumberLiteralStringParserRuleCall_1_0());
            				
            pushFollow(FOLLOW_2);
            lv_value_1_0=ruleNumberLiteralString();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getNumberLiteralRule());
            					}
            					set(
            						current,
            						"value",
            						lv_value_1_0,
            						"de.dlr.sc.virsat.model.calculation.EquationDSL.NumberLiteralString");
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


    // $ANTLR start "entryRuleQualifiedName"
    // InternalEquationDSL.g:1364:1: entryRuleQualifiedName returns [String current=null] : iv_ruleQualifiedName= ruleQualifiedName EOF ;
    public final String entryRuleQualifiedName() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleQualifiedName = null;


        try {
            // InternalEquationDSL.g:1364:53: (iv_ruleQualifiedName= ruleQualifiedName EOF )
            // InternalEquationDSL.g:1365:2: iv_ruleQualifiedName= ruleQualifiedName EOF
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
    // InternalEquationDSL.g:1371:1: ruleQualifiedName returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (this_ID_0= RULE_ID (kw= '.' this_ID_2= RULE_ID )* ) ;
    public final AntlrDatatypeRuleToken ruleQualifiedName() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_ID_0=null;
        Token kw=null;
        Token this_ID_2=null;


        	enterRule();

        try {
            // InternalEquationDSL.g:1377:2: ( (this_ID_0= RULE_ID (kw= '.' this_ID_2= RULE_ID )* ) )
            // InternalEquationDSL.g:1378:2: (this_ID_0= RULE_ID (kw= '.' this_ID_2= RULE_ID )* )
            {
            // InternalEquationDSL.g:1378:2: (this_ID_0= RULE_ID (kw= '.' this_ID_2= RULE_ID )* )
            // InternalEquationDSL.g:1379:3: this_ID_0= RULE_ID (kw= '.' this_ID_2= RULE_ID )*
            {
            this_ID_0=(Token)match(input,RULE_ID,FOLLOW_19); 

            			current.merge(this_ID_0);
            		

            			newLeafNode(this_ID_0, grammarAccess.getQualifiedNameAccess().getIDTerminalRuleCall_0());
            		
            // InternalEquationDSL.g:1386:3: (kw= '.' this_ID_2= RULE_ID )*
            loop17:
            do {
                int alt17=2;
                int LA17_0 = input.LA(1);

                if ( (LA17_0==21) ) {
                    alt17=1;
                }


                switch (alt17) {
            	case 1 :
            	    // InternalEquationDSL.g:1387:4: kw= '.' this_ID_2= RULE_ID
            	    {
            	    kw=(Token)match(input,21,FOLLOW_5); 

            	    				current.merge(kw);
            	    				newLeafNode(kw, grammarAccess.getQualifiedNameAccess().getFullStopKeyword_1_0());
            	    			
            	    this_ID_2=(Token)match(input,RULE_ID,FOLLOW_19); 

            	    				current.merge(this_ID_2);
            	    			

            	    				newLeafNode(this_ID_2, grammarAccess.getQualifiedNameAccess().getIDTerminalRuleCall_1_1());
            	    			

            	    }
            	    break;

            	default :
            	    break loop17;
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


    // $ANTLR start "entryRuleNumberLiteralString"
    // InternalEquationDSL.g:1404:1: entryRuleNumberLiteralString returns [String current=null] : iv_ruleNumberLiteralString= ruleNumberLiteralString EOF ;
    public final String entryRuleNumberLiteralString() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleNumberLiteralString = null;


        try {
            // InternalEquationDSL.g:1404:59: (iv_ruleNumberLiteralString= ruleNumberLiteralString EOF )
            // InternalEquationDSL.g:1405:2: iv_ruleNumberLiteralString= ruleNumberLiteralString EOF
            {
             newCompositeNode(grammarAccess.getNumberLiteralStringRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleNumberLiteralString=ruleNumberLiteralString();

            state._fsp--;

             current =iv_ruleNumberLiteralString.getText(); 
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
    // $ANTLR end "entryRuleNumberLiteralString"


    // $ANTLR start "ruleNumberLiteralString"
    // InternalEquationDSL.g:1411:1: ruleNumberLiteralString returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : ( (kw= '-' )? this_INT_1= RULE_INT (kw= '.' this_INT_3= RULE_INT )? ) ;
    public final AntlrDatatypeRuleToken ruleNumberLiteralString() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;
        Token this_INT_1=null;
        Token this_INT_3=null;


        	enterRule();

        try {
            // InternalEquationDSL.g:1417:2: ( ( (kw= '-' )? this_INT_1= RULE_INT (kw= '.' this_INT_3= RULE_INT )? ) )
            // InternalEquationDSL.g:1418:2: ( (kw= '-' )? this_INT_1= RULE_INT (kw= '.' this_INT_3= RULE_INT )? )
            {
            // InternalEquationDSL.g:1418:2: ( (kw= '-' )? this_INT_1= RULE_INT (kw= '.' this_INT_3= RULE_INT )? )
            // InternalEquationDSL.g:1419:3: (kw= '-' )? this_INT_1= RULE_INT (kw= '.' this_INT_3= RULE_INT )?
            {
            // InternalEquationDSL.g:1419:3: (kw= '-' )?
            int alt18=2;
            int LA18_0 = input.LA(1);

            if ( (LA18_0==22) ) {
                alt18=1;
            }
            switch (alt18) {
                case 1 :
                    // InternalEquationDSL.g:1420:4: kw= '-'
                    {
                    kw=(Token)match(input,22,FOLLOW_17); 

                    				current.merge(kw);
                    				newLeafNode(kw, grammarAccess.getNumberLiteralStringAccess().getHyphenMinusKeyword_0());
                    			

                    }
                    break;

            }

            this_INT_1=(Token)match(input,RULE_INT,FOLLOW_19); 

            			current.merge(this_INT_1);
            		

            			newLeafNode(this_INT_1, grammarAccess.getNumberLiteralStringAccess().getINTTerminalRuleCall_1());
            		
            // InternalEquationDSL.g:1433:3: (kw= '.' this_INT_3= RULE_INT )?
            int alt19=2;
            int LA19_0 = input.LA(1);

            if ( (LA19_0==21) ) {
                alt19=1;
            }
            switch (alt19) {
                case 1 :
                    // InternalEquationDSL.g:1434:4: kw= '.' this_INT_3= RULE_INT
                    {
                    kw=(Token)match(input,21,FOLLOW_17); 

                    				current.merge(kw);
                    				newLeafNode(kw, grammarAccess.getNumberLiteralStringAccess().getFullStopKeyword_2_0());
                    			
                    this_INT_3=(Token)match(input,RULE_INT,FOLLOW_2); 

                    				current.merge(this_INT_3);
                    			

                    				newLeafNode(this_INT_3, grammarAccess.getNumberLiteralStringAccess().getINTTerminalRuleCall_2_1());
                    			

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
    // $ANTLR end "ruleNumberLiteralString"


    // $ANTLR start "entryRuleValuePi"
    // InternalEquationDSL.g:1451:1: entryRuleValuePi returns [EObject current=null] : iv_ruleValuePi= ruleValuePi EOF ;
    public final EObject entryRuleValuePi() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleValuePi = null;


        try {
            // InternalEquationDSL.g:1451:48: (iv_ruleValuePi= ruleValuePi EOF )
            // InternalEquationDSL.g:1452:2: iv_ruleValuePi= ruleValuePi EOF
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
    // InternalEquationDSL.g:1458:1: ruleValuePi returns [EObject current=null] : ( () otherlv_1= 'pi' ) ;
    public final EObject ruleValuePi() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;


        	enterRule();

        try {
            // InternalEquationDSL.g:1464:2: ( ( () otherlv_1= 'pi' ) )
            // InternalEquationDSL.g:1465:2: ( () otherlv_1= 'pi' )
            {
            // InternalEquationDSL.g:1465:2: ( () otherlv_1= 'pi' )
            // InternalEquationDSL.g:1466:3: () otherlv_1= 'pi'
            {
            // InternalEquationDSL.g:1466:3: ()
            // InternalEquationDSL.g:1467:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getValuePiAccess().getValuePiAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,23,FOLLOW_2); 

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
    // InternalEquationDSL.g:1481:1: entryRuleValueE returns [EObject current=null] : iv_ruleValueE= ruleValueE EOF ;
    public final EObject entryRuleValueE() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleValueE = null;


        try {
            // InternalEquationDSL.g:1481:47: (iv_ruleValueE= ruleValueE EOF )
            // InternalEquationDSL.g:1482:2: iv_ruleValueE= ruleValueE EOF
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
    // InternalEquationDSL.g:1488:1: ruleValueE returns [EObject current=null] : ( () otherlv_1= 'e' ) ;
    public final EObject ruleValueE() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;


        	enterRule();

        try {
            // InternalEquationDSL.g:1494:2: ( ( () otherlv_1= 'e' ) )
            // InternalEquationDSL.g:1495:2: ( () otherlv_1= 'e' )
            {
            // InternalEquationDSL.g:1495:2: ( () otherlv_1= 'e' )
            // InternalEquationDSL.g:1496:3: () otherlv_1= 'e'
            {
            // InternalEquationDSL.g:1496:3: ()
            // InternalEquationDSL.g:1497:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getValueEAccess().getValueEAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,24,FOLLOW_2); 

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


    // $ANTLR start "ruleOperatorPlus"
    // InternalEquationDSL.g:1511:1: ruleOperatorPlus returns [Enumerator current=null] : (enumLiteral_0= '+' ) ;
    public final Enumerator ruleOperatorPlus() throws RecognitionException {
        Enumerator current = null;

        Token enumLiteral_0=null;


        	enterRule();

        try {
            // InternalEquationDSL.g:1517:2: ( (enumLiteral_0= '+' ) )
            // InternalEquationDSL.g:1518:2: (enumLiteral_0= '+' )
            {
            // InternalEquationDSL.g:1518:2: (enumLiteral_0= '+' )
            // InternalEquationDSL.g:1519:3: enumLiteral_0= '+'
            {
            enumLiteral_0=(Token)match(input,25,FOLLOW_2); 

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
    // InternalEquationDSL.g:1528:1: ruleOperatorMinus returns [Enumerator current=null] : (enumLiteral_0= '-' ) ;
    public final Enumerator ruleOperatorMinus() throws RecognitionException {
        Enumerator current = null;

        Token enumLiteral_0=null;


        	enterRule();

        try {
            // InternalEquationDSL.g:1534:2: ( (enumLiteral_0= '-' ) )
            // InternalEquationDSL.g:1535:2: (enumLiteral_0= '-' )
            {
            // InternalEquationDSL.g:1535:2: (enumLiteral_0= '-' )
            // InternalEquationDSL.g:1536:3: enumLiteral_0= '-'
            {
            enumLiteral_0=(Token)match(input,22,FOLLOW_2); 

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
    // InternalEquationDSL.g:1545:1: ruleOperatorMultiply returns [Enumerator current=null] : (enumLiteral_0= '*' ) ;
    public final Enumerator ruleOperatorMultiply() throws RecognitionException {
        Enumerator current = null;

        Token enumLiteral_0=null;


        	enterRule();

        try {
            // InternalEquationDSL.g:1551:2: ( (enumLiteral_0= '*' ) )
            // InternalEquationDSL.g:1552:2: (enumLiteral_0= '*' )
            {
            // InternalEquationDSL.g:1552:2: (enumLiteral_0= '*' )
            // InternalEquationDSL.g:1553:3: enumLiteral_0= '*'
            {
            enumLiteral_0=(Token)match(input,26,FOLLOW_2); 

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
    // InternalEquationDSL.g:1562:1: ruleOperatorDivide returns [Enumerator current=null] : (enumLiteral_0= '/' ) ;
    public final Enumerator ruleOperatorDivide() throws RecognitionException {
        Enumerator current = null;

        Token enumLiteral_0=null;


        	enterRule();

        try {
            // InternalEquationDSL.g:1568:2: ( (enumLiteral_0= '/' ) )
            // InternalEquationDSL.g:1569:2: (enumLiteral_0= '/' )
            {
            // InternalEquationDSL.g:1569:2: (enumLiteral_0= '/' )
            // InternalEquationDSL.g:1570:3: enumLiteral_0= '/'
            {
            enumLiteral_0=(Token)match(input,27,FOLLOW_2); 

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
    // InternalEquationDSL.g:1579:1: ruleOperatorPower returns [Enumerator current=null] : (enumLiteral_0= '^' ) ;
    public final Enumerator ruleOperatorPower() throws RecognitionException {
        Enumerator current = null;

        Token enumLiteral_0=null;


        	enterRule();

        try {
            // InternalEquationDSL.g:1585:2: ( (enumLiteral_0= '^' ) )
            // InternalEquationDSL.g:1586:2: (enumLiteral_0= '^' )
            {
            // InternalEquationDSL.g:1586:2: (enumLiteral_0= '^' )
            // InternalEquationDSL.g:1587:3: enumLiteral_0= '^'
            {
            enumLiteral_0=(Token)match(input,28,FOLLOW_2); 

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
    // InternalEquationDSL.g:1596:1: ruleOperatorCos returns [Enumerator current=null] : (enumLiteral_0= 'cos' ) ;
    public final Enumerator ruleOperatorCos() throws RecognitionException {
        Enumerator current = null;

        Token enumLiteral_0=null;


        	enterRule();

        try {
            // InternalEquationDSL.g:1602:2: ( (enumLiteral_0= 'cos' ) )
            // InternalEquationDSL.g:1603:2: (enumLiteral_0= 'cos' )
            {
            // InternalEquationDSL.g:1603:2: (enumLiteral_0= 'cos' )
            // InternalEquationDSL.g:1604:3: enumLiteral_0= 'cos'
            {
            enumLiteral_0=(Token)match(input,29,FOLLOW_2); 

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
    // InternalEquationDSL.g:1613:1: ruleOperatorSin returns [Enumerator current=null] : (enumLiteral_0= 'sin' ) ;
    public final Enumerator ruleOperatorSin() throws RecognitionException {
        Enumerator current = null;

        Token enumLiteral_0=null;


        	enterRule();

        try {
            // InternalEquationDSL.g:1619:2: ( (enumLiteral_0= 'sin' ) )
            // InternalEquationDSL.g:1620:2: (enumLiteral_0= 'sin' )
            {
            // InternalEquationDSL.g:1620:2: (enumLiteral_0= 'sin' )
            // InternalEquationDSL.g:1621:3: enumLiteral_0= 'sin'
            {
            enumLiteral_0=(Token)match(input,30,FOLLOW_2); 

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
    // InternalEquationDSL.g:1630:1: ruleOperatorTan returns [Enumerator current=null] : (enumLiteral_0= 'tan' ) ;
    public final Enumerator ruleOperatorTan() throws RecognitionException {
        Enumerator current = null;

        Token enumLiteral_0=null;


        	enterRule();

        try {
            // InternalEquationDSL.g:1636:2: ( (enumLiteral_0= 'tan' ) )
            // InternalEquationDSL.g:1637:2: (enumLiteral_0= 'tan' )
            {
            // InternalEquationDSL.g:1637:2: (enumLiteral_0= 'tan' )
            // InternalEquationDSL.g:1638:3: enumLiteral_0= 'tan'
            {
            enumLiteral_0=(Token)match(input,31,FOLLOW_2); 

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
    // InternalEquationDSL.g:1647:1: ruleOperatorAtan returns [Enumerator current=null] : (enumLiteral_0= 'atan' ) ;
    public final Enumerator ruleOperatorAtan() throws RecognitionException {
        Enumerator current = null;

        Token enumLiteral_0=null;


        	enterRule();

        try {
            // InternalEquationDSL.g:1653:2: ( (enumLiteral_0= 'atan' ) )
            // InternalEquationDSL.g:1654:2: (enumLiteral_0= 'atan' )
            {
            // InternalEquationDSL.g:1654:2: (enumLiteral_0= 'atan' )
            // InternalEquationDSL.g:1655:3: enumLiteral_0= 'atan'
            {
            enumLiteral_0=(Token)match(input,32,FOLLOW_2); 

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
    // InternalEquationDSL.g:1664:1: ruleOperatorAcos returns [Enumerator current=null] : (enumLiteral_0= 'acos' ) ;
    public final Enumerator ruleOperatorAcos() throws RecognitionException {
        Enumerator current = null;

        Token enumLiteral_0=null;


        	enterRule();

        try {
            // InternalEquationDSL.g:1670:2: ( (enumLiteral_0= 'acos' ) )
            // InternalEquationDSL.g:1671:2: (enumLiteral_0= 'acos' )
            {
            // InternalEquationDSL.g:1671:2: (enumLiteral_0= 'acos' )
            // InternalEquationDSL.g:1672:3: enumLiteral_0= 'acos'
            {
            enumLiteral_0=(Token)match(input,33,FOLLOW_2); 

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
    // InternalEquationDSL.g:1681:1: ruleOperatorAsin returns [Enumerator current=null] : (enumLiteral_0= 'asin' ) ;
    public final Enumerator ruleOperatorAsin() throws RecognitionException {
        Enumerator current = null;

        Token enumLiteral_0=null;


        	enterRule();

        try {
            // InternalEquationDSL.g:1687:2: ( (enumLiteral_0= 'asin' ) )
            // InternalEquationDSL.g:1688:2: (enumLiteral_0= 'asin' )
            {
            // InternalEquationDSL.g:1688:2: (enumLiteral_0= 'asin' )
            // InternalEquationDSL.g:1689:3: enumLiteral_0= 'asin'
            {
            enumLiteral_0=(Token)match(input,34,FOLLOW_2); 

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
    // InternalEquationDSL.g:1698:1: ruleOperatorSqrt returns [Enumerator current=null] : (enumLiteral_0= 'sqrt' ) ;
    public final Enumerator ruleOperatorSqrt() throws RecognitionException {
        Enumerator current = null;

        Token enumLiteral_0=null;


        	enterRule();

        try {
            // InternalEquationDSL.g:1704:2: ( (enumLiteral_0= 'sqrt' ) )
            // InternalEquationDSL.g:1705:2: (enumLiteral_0= 'sqrt' )
            {
            // InternalEquationDSL.g:1705:2: (enumLiteral_0= 'sqrt' )
            // InternalEquationDSL.g:1706:3: enumLiteral_0= 'sqrt'
            {
            enumLiteral_0=(Token)match(input,35,FOLLOW_2); 

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
    // InternalEquationDSL.g:1715:1: ruleOperatorLog returns [Enumerator current=null] : (enumLiteral_0= 'log' ) ;
    public final Enumerator ruleOperatorLog() throws RecognitionException {
        Enumerator current = null;

        Token enumLiteral_0=null;


        	enterRule();

        try {
            // InternalEquationDSL.g:1721:2: ( (enumLiteral_0= 'log' ) )
            // InternalEquationDSL.g:1722:2: (enumLiteral_0= 'log' )
            {
            // InternalEquationDSL.g:1722:2: (enumLiteral_0= 'log' )
            // InternalEquationDSL.g:1723:3: enumLiteral_0= 'log'
            {
            enumLiteral_0=(Token)match(input,36,FOLLOW_2); 

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
    // InternalEquationDSL.g:1732:1: ruleOperatorLn returns [Enumerator current=null] : (enumLiteral_0= 'ln' ) ;
    public final Enumerator ruleOperatorLn() throws RecognitionException {
        Enumerator current = null;

        Token enumLiteral_0=null;


        	enterRule();

        try {
            // InternalEquationDSL.g:1738:2: ( (enumLiteral_0= 'ln' ) )
            // InternalEquationDSL.g:1739:2: (enumLiteral_0= 'ln' )
            {
            // InternalEquationDSL.g:1739:2: (enumLiteral_0= 'ln' )
            // InternalEquationDSL.g:1740:3: enumLiteral_0= 'ln'
            {
            enumLiteral_0=(Token)match(input,37,FOLLOW_2); 

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
    // InternalEquationDSL.g:1749:1: ruleOperatorExp returns [Enumerator current=null] : (enumLiteral_0= 'exp' ) ;
    public final Enumerator ruleOperatorExp() throws RecognitionException {
        Enumerator current = null;

        Token enumLiteral_0=null;


        	enterRule();

        try {
            // InternalEquationDSL.g:1755:2: ( (enumLiteral_0= 'exp' ) )
            // InternalEquationDSL.g:1756:2: (enumLiteral_0= 'exp' )
            {
            // InternalEquationDSL.g:1756:2: (enumLiteral_0= 'exp' )
            // InternalEquationDSL.g:1757:3: enumLiteral_0= 'exp'
            {
            enumLiteral_0=(Token)match(input,38,FOLLOW_2); 

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
    // InternalEquationDSL.g:1766:1: ruleOperatorLd returns [Enumerator current=null] : (enumLiteral_0= 'ld' ) ;
    public final Enumerator ruleOperatorLd() throws RecognitionException {
        Enumerator current = null;

        Token enumLiteral_0=null;


        	enterRule();

        try {
            // InternalEquationDSL.g:1772:2: ( (enumLiteral_0= 'ld' ) )
            // InternalEquationDSL.g:1773:2: (enumLiteral_0= 'ld' )
            {
            // InternalEquationDSL.g:1773:2: (enumLiteral_0= 'ld' )
            // InternalEquationDSL.g:1774:3: enumLiteral_0= 'ld'
            {
            enumLiteral_0=(Token)match(input,39,FOLLOW_2); 

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
    public static final BitSet FOLLOW_3 = new BitSet(new long[]{0x000000000000C802L});
    public static final BitSet FOLLOW_4 = new BitSet(new long[]{0x000000000000C002L});
    public static final BitSet FOLLOW_5 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_6 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_7 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_8 = new BitSet(new long[]{0x000000FFE1C10030L});
    public static final BitSet FOLLOW_9 = new BitSet(new long[]{0x0000000002400002L});
    public static final BitSet FOLLOW_10 = new BitSet(new long[]{0x000000000C000002L});
    public static final BitSet FOLLOW_11 = new BitSet(new long[]{0x0000000010000002L});
    public static final BitSet FOLLOW_12 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_13 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_14 = new BitSet(new long[]{0x0000000000060000L});
    public static final BitSet FOLLOW_15 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_16 = new BitSet(new long[]{0x0000000000140000L});
    public static final BitSet FOLLOW_17 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_18 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_19 = new BitSet(new long[]{0x0000000000200002L});

}