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
public class InternalEquationDSLParser extends AbstractInternalContentAssistParser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "RULE_ID", "RULE_INT", "RULE_STRING", "RULE_ML_COMMENT", "RULE_SL_COMMENT", "RULE_WS", "RULE_ANY_OTHER", "'+'", "'-'", "'*'", "'/'", "'^'", "'cos'", "'sin'", "'tan'", "'atan'", "'acos'", "'asin'", "'sqrt'", "'log'", "'ln'", "'exp'", "'ld'", "'Import:'", "';'", "'='", "'Ref:'", "'Calc:'", "'('", "')'", "','", "'{'", "'}'", "'.'", "'pi'", "'e'"
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



    // $ANTLR start "entryRuleEquationSection"
    // InternalEquationDSL.g:59:1: entryRuleEquationSection : ruleEquationSection EOF ;
    public final void entryRuleEquationSection() throws RecognitionException {
        try {
            // InternalEquationDSL.g:60:1: ( ruleEquationSection EOF )
            // InternalEquationDSL.g:61:1: ruleEquationSection EOF
            {
             before(grammarAccess.getEquationSectionRule()); 
            pushFollow(FOLLOW_1);
            ruleEquationSection();

            state._fsp--;

             after(grammarAccess.getEquationSectionRule()); 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleEquationSection"


    // $ANTLR start "ruleEquationSection"
    // InternalEquationDSL.g:68:1: ruleEquationSection : ( ( rule__EquationSection__Group__0 ) ) ;
    public final void ruleEquationSection() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalEquationDSL.g:72:2: ( ( ( rule__EquationSection__Group__0 ) ) )
            // InternalEquationDSL.g:73:2: ( ( rule__EquationSection__Group__0 ) )
            {
            // InternalEquationDSL.g:73:2: ( ( rule__EquationSection__Group__0 ) )
            // InternalEquationDSL.g:74:3: ( rule__EquationSection__Group__0 )
            {
             before(grammarAccess.getEquationSectionAccess().getGroup()); 
            // InternalEquationDSL.g:75:3: ( rule__EquationSection__Group__0 )
            // InternalEquationDSL.g:75:4: rule__EquationSection__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__EquationSection__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getEquationSectionAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleEquationSection"


    // $ANTLR start "entryRuleImport"
    // InternalEquationDSL.g:84:1: entryRuleImport : ruleImport EOF ;
    public final void entryRuleImport() throws RecognitionException {
        try {
            // InternalEquationDSL.g:85:1: ( ruleImport EOF )
            // InternalEquationDSL.g:86:1: ruleImport EOF
            {
             before(grammarAccess.getImportRule()); 
            pushFollow(FOLLOW_1);
            ruleImport();

            state._fsp--;

             after(grammarAccess.getImportRule()); 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleImport"


    // $ANTLR start "ruleImport"
    // InternalEquationDSL.g:93:1: ruleImport : ( ( rule__Import__Group__0 ) ) ;
    public final void ruleImport() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalEquationDSL.g:97:2: ( ( ( rule__Import__Group__0 ) ) )
            // InternalEquationDSL.g:98:2: ( ( rule__Import__Group__0 ) )
            {
            // InternalEquationDSL.g:98:2: ( ( rule__Import__Group__0 ) )
            // InternalEquationDSL.g:99:3: ( rule__Import__Group__0 )
            {
             before(grammarAccess.getImportAccess().getGroup()); 
            // InternalEquationDSL.g:100:3: ( rule__Import__Group__0 )
            // InternalEquationDSL.g:100:4: rule__Import__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__Import__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getImportAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleImport"


    // $ANTLR start "entryRuleEquation"
    // InternalEquationDSL.g:109:1: entryRuleEquation : ruleEquation EOF ;
    public final void entryRuleEquation() throws RecognitionException {
        try {
            // InternalEquationDSL.g:110:1: ( ruleEquation EOF )
            // InternalEquationDSL.g:111:1: ruleEquation EOF
            {
             before(grammarAccess.getEquationRule()); 
            pushFollow(FOLLOW_1);
            ruleEquation();

            state._fsp--;

             after(grammarAccess.getEquationRule()); 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleEquation"


    // $ANTLR start "ruleEquation"
    // InternalEquationDSL.g:118:1: ruleEquation : ( ( rule__Equation__Group__0 ) ) ;
    public final void ruleEquation() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalEquationDSL.g:122:2: ( ( ( rule__Equation__Group__0 ) ) )
            // InternalEquationDSL.g:123:2: ( ( rule__Equation__Group__0 ) )
            {
            // InternalEquationDSL.g:123:2: ( ( rule__Equation__Group__0 ) )
            // InternalEquationDSL.g:124:3: ( rule__Equation__Group__0 )
            {
             before(grammarAccess.getEquationAccess().getGroup()); 
            // InternalEquationDSL.g:125:3: ( rule__Equation__Group__0 )
            // InternalEquationDSL.g:125:4: rule__Equation__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__Equation__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getEquationAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleEquation"


    // $ANTLR start "entryRuleEquationResult"
    // InternalEquationDSL.g:134:1: entryRuleEquationResult : ruleEquationResult EOF ;
    public final void entryRuleEquationResult() throws RecognitionException {
        try {
            // InternalEquationDSL.g:135:1: ( ruleEquationResult EOF )
            // InternalEquationDSL.g:136:1: ruleEquationResult EOF
            {
             before(grammarAccess.getEquationResultRule()); 
            pushFollow(FOLLOW_1);
            ruleEquationResult();

            state._fsp--;

             after(grammarAccess.getEquationResultRule()); 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleEquationResult"


    // $ANTLR start "ruleEquationResult"
    // InternalEquationDSL.g:143:1: ruleEquationResult : ( ( rule__EquationResult__Alternatives ) ) ;
    public final void ruleEquationResult() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalEquationDSL.g:147:2: ( ( ( rule__EquationResult__Alternatives ) ) )
            // InternalEquationDSL.g:148:2: ( ( rule__EquationResult__Alternatives ) )
            {
            // InternalEquationDSL.g:148:2: ( ( rule__EquationResult__Alternatives ) )
            // InternalEquationDSL.g:149:3: ( rule__EquationResult__Alternatives )
            {
             before(grammarAccess.getEquationResultAccess().getAlternatives()); 
            // InternalEquationDSL.g:150:3: ( rule__EquationResult__Alternatives )
            // InternalEquationDSL.g:150:4: rule__EquationResult__Alternatives
            {
            pushFollow(FOLLOW_2);
            rule__EquationResult__Alternatives();

            state._fsp--;


            }

             after(grammarAccess.getEquationResultAccess().getAlternatives()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleEquationResult"


    // $ANTLR start "entryRuleTypeInstanceResult"
    // InternalEquationDSL.g:159:1: entryRuleTypeInstanceResult : ruleTypeInstanceResult EOF ;
    public final void entryRuleTypeInstanceResult() throws RecognitionException {
        try {
            // InternalEquationDSL.g:160:1: ( ruleTypeInstanceResult EOF )
            // InternalEquationDSL.g:161:1: ruleTypeInstanceResult EOF
            {
             before(grammarAccess.getTypeInstanceResultRule()); 
            pushFollow(FOLLOW_1);
            ruleTypeInstanceResult();

            state._fsp--;

             after(grammarAccess.getTypeInstanceResultRule()); 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleTypeInstanceResult"


    // $ANTLR start "ruleTypeInstanceResult"
    // InternalEquationDSL.g:168:1: ruleTypeInstanceResult : ( ( rule__TypeInstanceResult__Group__0 ) ) ;
    public final void ruleTypeInstanceResult() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalEquationDSL.g:172:2: ( ( ( rule__TypeInstanceResult__Group__0 ) ) )
            // InternalEquationDSL.g:173:2: ( ( rule__TypeInstanceResult__Group__0 ) )
            {
            // InternalEquationDSL.g:173:2: ( ( rule__TypeInstanceResult__Group__0 ) )
            // InternalEquationDSL.g:174:3: ( rule__TypeInstanceResult__Group__0 )
            {
             before(grammarAccess.getTypeInstanceResultAccess().getGroup()); 
            // InternalEquationDSL.g:175:3: ( rule__TypeInstanceResult__Group__0 )
            // InternalEquationDSL.g:175:4: rule__TypeInstanceResult__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__TypeInstanceResult__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getTypeInstanceResultAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleTypeInstanceResult"


    // $ANTLR start "entryRuleEquationIntermediateResult"
    // InternalEquationDSL.g:184:1: entryRuleEquationIntermediateResult : ruleEquationIntermediateResult EOF ;
    public final void entryRuleEquationIntermediateResult() throws RecognitionException {
        try {
            // InternalEquationDSL.g:185:1: ( ruleEquationIntermediateResult EOF )
            // InternalEquationDSL.g:186:1: ruleEquationIntermediateResult EOF
            {
             before(grammarAccess.getEquationIntermediateResultRule()); 
            pushFollow(FOLLOW_1);
            ruleEquationIntermediateResult();

            state._fsp--;

             after(grammarAccess.getEquationIntermediateResultRule()); 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleEquationIntermediateResult"


    // $ANTLR start "ruleEquationIntermediateResult"
    // InternalEquationDSL.g:193:1: ruleEquationIntermediateResult : ( ( rule__EquationIntermediateResult__Group__0 ) ) ;
    public final void ruleEquationIntermediateResult() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalEquationDSL.g:197:2: ( ( ( rule__EquationIntermediateResult__Group__0 ) ) )
            // InternalEquationDSL.g:198:2: ( ( rule__EquationIntermediateResult__Group__0 ) )
            {
            // InternalEquationDSL.g:198:2: ( ( rule__EquationIntermediateResult__Group__0 ) )
            // InternalEquationDSL.g:199:3: ( rule__EquationIntermediateResult__Group__0 )
            {
             before(grammarAccess.getEquationIntermediateResultAccess().getGroup()); 
            // InternalEquationDSL.g:200:3: ( rule__EquationIntermediateResult__Group__0 )
            // InternalEquationDSL.g:200:4: rule__EquationIntermediateResult__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__EquationIntermediateResult__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getEquationIntermediateResultAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleEquationIntermediateResult"


    // $ANTLR start "entryRuleAdditionAndSubtraction"
    // InternalEquationDSL.g:209:1: entryRuleAdditionAndSubtraction : ruleAdditionAndSubtraction EOF ;
    public final void entryRuleAdditionAndSubtraction() throws RecognitionException {
        try {
            // InternalEquationDSL.g:210:1: ( ruleAdditionAndSubtraction EOF )
            // InternalEquationDSL.g:211:1: ruleAdditionAndSubtraction EOF
            {
             before(grammarAccess.getAdditionAndSubtractionRule()); 
            pushFollow(FOLLOW_1);
            ruleAdditionAndSubtraction();

            state._fsp--;

             after(grammarAccess.getAdditionAndSubtractionRule()); 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleAdditionAndSubtraction"


    // $ANTLR start "ruleAdditionAndSubtraction"
    // InternalEquationDSL.g:218:1: ruleAdditionAndSubtraction : ( ( rule__AdditionAndSubtraction__Group__0 ) ) ;
    public final void ruleAdditionAndSubtraction() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalEquationDSL.g:222:2: ( ( ( rule__AdditionAndSubtraction__Group__0 ) ) )
            // InternalEquationDSL.g:223:2: ( ( rule__AdditionAndSubtraction__Group__0 ) )
            {
            // InternalEquationDSL.g:223:2: ( ( rule__AdditionAndSubtraction__Group__0 ) )
            // InternalEquationDSL.g:224:3: ( rule__AdditionAndSubtraction__Group__0 )
            {
             before(grammarAccess.getAdditionAndSubtractionAccess().getGroup()); 
            // InternalEquationDSL.g:225:3: ( rule__AdditionAndSubtraction__Group__0 )
            // InternalEquationDSL.g:225:4: rule__AdditionAndSubtraction__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__AdditionAndSubtraction__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getAdditionAndSubtractionAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleAdditionAndSubtraction"


    // $ANTLR start "entryRuleMultiplicationAndDivision"
    // InternalEquationDSL.g:234:1: entryRuleMultiplicationAndDivision : ruleMultiplicationAndDivision EOF ;
    public final void entryRuleMultiplicationAndDivision() throws RecognitionException {
        try {
            // InternalEquationDSL.g:235:1: ( ruleMultiplicationAndDivision EOF )
            // InternalEquationDSL.g:236:1: ruleMultiplicationAndDivision EOF
            {
             before(grammarAccess.getMultiplicationAndDivisionRule()); 
            pushFollow(FOLLOW_1);
            ruleMultiplicationAndDivision();

            state._fsp--;

             after(grammarAccess.getMultiplicationAndDivisionRule()); 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleMultiplicationAndDivision"


    // $ANTLR start "ruleMultiplicationAndDivision"
    // InternalEquationDSL.g:243:1: ruleMultiplicationAndDivision : ( ( rule__MultiplicationAndDivision__Group__0 ) ) ;
    public final void ruleMultiplicationAndDivision() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalEquationDSL.g:247:2: ( ( ( rule__MultiplicationAndDivision__Group__0 ) ) )
            // InternalEquationDSL.g:248:2: ( ( rule__MultiplicationAndDivision__Group__0 ) )
            {
            // InternalEquationDSL.g:248:2: ( ( rule__MultiplicationAndDivision__Group__0 ) )
            // InternalEquationDSL.g:249:3: ( rule__MultiplicationAndDivision__Group__0 )
            {
             before(grammarAccess.getMultiplicationAndDivisionAccess().getGroup()); 
            // InternalEquationDSL.g:250:3: ( rule__MultiplicationAndDivision__Group__0 )
            // InternalEquationDSL.g:250:4: rule__MultiplicationAndDivision__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__MultiplicationAndDivision__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getMultiplicationAndDivisionAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleMultiplicationAndDivision"


    // $ANTLR start "entryRulePowerFunction"
    // InternalEquationDSL.g:259:1: entryRulePowerFunction : rulePowerFunction EOF ;
    public final void entryRulePowerFunction() throws RecognitionException {
        try {
            // InternalEquationDSL.g:260:1: ( rulePowerFunction EOF )
            // InternalEquationDSL.g:261:1: rulePowerFunction EOF
            {
             before(grammarAccess.getPowerFunctionRule()); 
            pushFollow(FOLLOW_1);
            rulePowerFunction();

            state._fsp--;

             after(grammarAccess.getPowerFunctionRule()); 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRulePowerFunction"


    // $ANTLR start "rulePowerFunction"
    // InternalEquationDSL.g:268:1: rulePowerFunction : ( ( rule__PowerFunction__Group__0 ) ) ;
    public final void rulePowerFunction() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalEquationDSL.g:272:2: ( ( ( rule__PowerFunction__Group__0 ) ) )
            // InternalEquationDSL.g:273:2: ( ( rule__PowerFunction__Group__0 ) )
            {
            // InternalEquationDSL.g:273:2: ( ( rule__PowerFunction__Group__0 ) )
            // InternalEquationDSL.g:274:3: ( rule__PowerFunction__Group__0 )
            {
             before(grammarAccess.getPowerFunctionAccess().getGroup()); 
            // InternalEquationDSL.g:275:3: ( rule__PowerFunction__Group__0 )
            // InternalEquationDSL.g:275:4: rule__PowerFunction__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__PowerFunction__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getPowerFunctionAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rulePowerFunction"


    // $ANTLR start "entryRuleParenthesis"
    // InternalEquationDSL.g:284:1: entryRuleParenthesis : ruleParenthesis EOF ;
    public final void entryRuleParenthesis() throws RecognitionException {
        try {
            // InternalEquationDSL.g:285:1: ( ruleParenthesis EOF )
            // InternalEquationDSL.g:286:1: ruleParenthesis EOF
            {
             before(grammarAccess.getParenthesisRule()); 
            pushFollow(FOLLOW_1);
            ruleParenthesis();

            state._fsp--;

             after(grammarAccess.getParenthesisRule()); 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleParenthesis"


    // $ANTLR start "ruleParenthesis"
    // InternalEquationDSL.g:293:1: ruleParenthesis : ( ( rule__Parenthesis__Group__0 ) ) ;
    public final void ruleParenthesis() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalEquationDSL.g:297:2: ( ( ( rule__Parenthesis__Group__0 ) ) )
            // InternalEquationDSL.g:298:2: ( ( rule__Parenthesis__Group__0 ) )
            {
            // InternalEquationDSL.g:298:2: ( ( rule__Parenthesis__Group__0 ) )
            // InternalEquationDSL.g:299:3: ( rule__Parenthesis__Group__0 )
            {
             before(grammarAccess.getParenthesisAccess().getGroup()); 
            // InternalEquationDSL.g:300:3: ( rule__Parenthesis__Group__0 )
            // InternalEquationDSL.g:300:4: rule__Parenthesis__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__Parenthesis__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getParenthesisAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleParenthesis"


    // $ANTLR start "entryRuleAExpression"
    // InternalEquationDSL.g:309:1: entryRuleAExpression : ruleAExpression EOF ;
    public final void entryRuleAExpression() throws RecognitionException {
        try {
            // InternalEquationDSL.g:310:1: ( ruleAExpression EOF )
            // InternalEquationDSL.g:311:1: ruleAExpression EOF
            {
             before(grammarAccess.getAExpressionRule()); 
            pushFollow(FOLLOW_1);
            ruleAExpression();

            state._fsp--;

             after(grammarAccess.getAExpressionRule()); 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleAExpression"


    // $ANTLR start "ruleAExpression"
    // InternalEquationDSL.g:318:1: ruleAExpression : ( ( rule__AExpression__Alternatives ) ) ;
    public final void ruleAExpression() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalEquationDSL.g:322:2: ( ( ( rule__AExpression__Alternatives ) ) )
            // InternalEquationDSL.g:323:2: ( ( rule__AExpression__Alternatives ) )
            {
            // InternalEquationDSL.g:323:2: ( ( rule__AExpression__Alternatives ) )
            // InternalEquationDSL.g:324:3: ( rule__AExpression__Alternatives )
            {
             before(grammarAccess.getAExpressionAccess().getAlternatives()); 
            // InternalEquationDSL.g:325:3: ( rule__AExpression__Alternatives )
            // InternalEquationDSL.g:325:4: rule__AExpression__Alternatives
            {
            pushFollow(FOLLOW_2);
            rule__AExpression__Alternatives();

            state._fsp--;


            }

             after(grammarAccess.getAExpressionAccess().getAlternatives()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleAExpression"


    // $ANTLR start "entryRuleReferencedInput"
    // InternalEquationDSL.g:334:1: entryRuleReferencedInput : ruleReferencedInput EOF ;
    public final void entryRuleReferencedInput() throws RecognitionException {
        try {
            // InternalEquationDSL.g:335:1: ( ruleReferencedInput EOF )
            // InternalEquationDSL.g:336:1: ruleReferencedInput EOF
            {
             before(grammarAccess.getReferencedInputRule()); 
            pushFollow(FOLLOW_1);
            ruleReferencedInput();

            state._fsp--;

             after(grammarAccess.getReferencedInputRule()); 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleReferencedInput"


    // $ANTLR start "ruleReferencedInput"
    // InternalEquationDSL.g:343:1: ruleReferencedInput : ( ( rule__ReferencedInput__ReferenceAssignment ) ) ;
    public final void ruleReferencedInput() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalEquationDSL.g:347:2: ( ( ( rule__ReferencedInput__ReferenceAssignment ) ) )
            // InternalEquationDSL.g:348:2: ( ( rule__ReferencedInput__ReferenceAssignment ) )
            {
            // InternalEquationDSL.g:348:2: ( ( rule__ReferencedInput__ReferenceAssignment ) )
            // InternalEquationDSL.g:349:3: ( rule__ReferencedInput__ReferenceAssignment )
            {
             before(grammarAccess.getReferencedInputAccess().getReferenceAssignment()); 
            // InternalEquationDSL.g:350:3: ( rule__ReferencedInput__ReferenceAssignment )
            // InternalEquationDSL.g:350:4: rule__ReferencedInput__ReferenceAssignment
            {
            pushFollow(FOLLOW_2);
            rule__ReferencedInput__ReferenceAssignment();

            state._fsp--;


            }

             after(grammarAccess.getReferencedInputAccess().getReferenceAssignment()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleReferencedInput"


    // $ANTLR start "entryRuleALiteral"
    // InternalEquationDSL.g:359:1: entryRuleALiteral : ruleALiteral EOF ;
    public final void entryRuleALiteral() throws RecognitionException {
        try {
            // InternalEquationDSL.g:360:1: ( ruleALiteral EOF )
            // InternalEquationDSL.g:361:1: ruleALiteral EOF
            {
             before(grammarAccess.getALiteralRule()); 
            pushFollow(FOLLOW_1);
            ruleALiteral();

            state._fsp--;

             after(grammarAccess.getALiteralRule()); 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleALiteral"


    // $ANTLR start "ruleALiteral"
    // InternalEquationDSL.g:368:1: ruleALiteral : ( ( rule__ALiteral__Alternatives ) ) ;
    public final void ruleALiteral() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalEquationDSL.g:372:2: ( ( ( rule__ALiteral__Alternatives ) ) )
            // InternalEquationDSL.g:373:2: ( ( rule__ALiteral__Alternatives ) )
            {
            // InternalEquationDSL.g:373:2: ( ( rule__ALiteral__Alternatives ) )
            // InternalEquationDSL.g:374:3: ( rule__ALiteral__Alternatives )
            {
             before(grammarAccess.getALiteralAccess().getAlternatives()); 
            // InternalEquationDSL.g:375:3: ( rule__ALiteral__Alternatives )
            // InternalEquationDSL.g:375:4: rule__ALiteral__Alternatives
            {
            pushFollow(FOLLOW_2);
            rule__ALiteral__Alternatives();

            state._fsp--;


            }

             after(grammarAccess.getALiteralAccess().getAlternatives()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleALiteral"


    // $ANTLR start "entryRuleFunction"
    // InternalEquationDSL.g:384:1: entryRuleFunction : ruleFunction EOF ;
    public final void entryRuleFunction() throws RecognitionException {
        try {
            // InternalEquationDSL.g:385:1: ( ruleFunction EOF )
            // InternalEquationDSL.g:386:1: ruleFunction EOF
            {
             before(grammarAccess.getFunctionRule()); 
            pushFollow(FOLLOW_1);
            ruleFunction();

            state._fsp--;

             after(grammarAccess.getFunctionRule()); 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleFunction"


    // $ANTLR start "ruleFunction"
    // InternalEquationDSL.g:393:1: ruleFunction : ( ( rule__Function__Group__0 ) ) ;
    public final void ruleFunction() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalEquationDSL.g:397:2: ( ( ( rule__Function__Group__0 ) ) )
            // InternalEquationDSL.g:398:2: ( ( rule__Function__Group__0 ) )
            {
            // InternalEquationDSL.g:398:2: ( ( rule__Function__Group__0 ) )
            // InternalEquationDSL.g:399:3: ( rule__Function__Group__0 )
            {
             before(grammarAccess.getFunctionAccess().getGroup()); 
            // InternalEquationDSL.g:400:3: ( rule__Function__Group__0 )
            // InternalEquationDSL.g:400:4: rule__Function__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__Function__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getFunctionAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleFunction"


    // $ANTLR start "entryRuleAAdvancedFunction"
    // InternalEquationDSL.g:409:1: entryRuleAAdvancedFunction : ruleAAdvancedFunction EOF ;
    public final void entryRuleAAdvancedFunction() throws RecognitionException {
        try {
            // InternalEquationDSL.g:410:1: ( ruleAAdvancedFunction EOF )
            // InternalEquationDSL.g:411:1: ruleAAdvancedFunction EOF
            {
             before(grammarAccess.getAAdvancedFunctionRule()); 
            pushFollow(FOLLOW_1);
            ruleAAdvancedFunction();

            state._fsp--;

             after(grammarAccess.getAAdvancedFunctionRule()); 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleAAdvancedFunction"


    // $ANTLR start "ruleAAdvancedFunction"
    // InternalEquationDSL.g:418:1: ruleAAdvancedFunction : ( ( rule__AAdvancedFunction__Alternatives ) ) ;
    public final void ruleAAdvancedFunction() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalEquationDSL.g:422:2: ( ( ( rule__AAdvancedFunction__Alternatives ) ) )
            // InternalEquationDSL.g:423:2: ( ( rule__AAdvancedFunction__Alternatives ) )
            {
            // InternalEquationDSL.g:423:2: ( ( rule__AAdvancedFunction__Alternatives ) )
            // InternalEquationDSL.g:424:3: ( rule__AAdvancedFunction__Alternatives )
            {
             before(grammarAccess.getAAdvancedFunctionAccess().getAlternatives()); 
            // InternalEquationDSL.g:425:3: ( rule__AAdvancedFunction__Alternatives )
            // InternalEquationDSL.g:425:4: rule__AAdvancedFunction__Alternatives
            {
            pushFollow(FOLLOW_2);
            rule__AAdvancedFunction__Alternatives();

            state._fsp--;


            }

             after(grammarAccess.getAAdvancedFunctionAccess().getAlternatives()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleAAdvancedFunction"


    // $ANTLR start "entryRuleAdvancedFunction"
    // InternalEquationDSL.g:434:1: entryRuleAdvancedFunction : ruleAdvancedFunction EOF ;
    public final void entryRuleAdvancedFunction() throws RecognitionException {
        try {
            // InternalEquationDSL.g:435:1: ( ruleAdvancedFunction EOF )
            // InternalEquationDSL.g:436:1: ruleAdvancedFunction EOF
            {
             before(grammarAccess.getAdvancedFunctionRule()); 
            pushFollow(FOLLOW_1);
            ruleAdvancedFunction();

            state._fsp--;

             after(grammarAccess.getAdvancedFunctionRule()); 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleAdvancedFunction"


    // $ANTLR start "ruleAdvancedFunction"
    // InternalEquationDSL.g:443:1: ruleAdvancedFunction : ( ( rule__AdvancedFunction__Group__0 ) ) ;
    public final void ruleAdvancedFunction() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalEquationDSL.g:447:2: ( ( ( rule__AdvancedFunction__Group__0 ) ) )
            // InternalEquationDSL.g:448:2: ( ( rule__AdvancedFunction__Group__0 ) )
            {
            // InternalEquationDSL.g:448:2: ( ( rule__AdvancedFunction__Group__0 ) )
            // InternalEquationDSL.g:449:3: ( rule__AdvancedFunction__Group__0 )
            {
             before(grammarAccess.getAdvancedFunctionAccess().getGroup()); 
            // InternalEquationDSL.g:450:3: ( rule__AdvancedFunction__Group__0 )
            // InternalEquationDSL.g:450:4: rule__AdvancedFunction__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__AdvancedFunction__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getAdvancedFunctionAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleAdvancedFunction"


    // $ANTLR start "entryRuleSetFunction"
    // InternalEquationDSL.g:459:1: entryRuleSetFunction : ruleSetFunction EOF ;
    public final void entryRuleSetFunction() throws RecognitionException {
        try {
            // InternalEquationDSL.g:460:1: ( ruleSetFunction EOF )
            // InternalEquationDSL.g:461:1: ruleSetFunction EOF
            {
             before(grammarAccess.getSetFunctionRule()); 
            pushFollow(FOLLOW_1);
            ruleSetFunction();

            state._fsp--;

             after(grammarAccess.getSetFunctionRule()); 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleSetFunction"


    // $ANTLR start "ruleSetFunction"
    // InternalEquationDSL.g:468:1: ruleSetFunction : ( ( rule__SetFunction__Group__0 ) ) ;
    public final void ruleSetFunction() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalEquationDSL.g:472:2: ( ( ( rule__SetFunction__Group__0 ) ) )
            // InternalEquationDSL.g:473:2: ( ( rule__SetFunction__Group__0 ) )
            {
            // InternalEquationDSL.g:473:2: ( ( rule__SetFunction__Group__0 ) )
            // InternalEquationDSL.g:474:3: ( rule__SetFunction__Group__0 )
            {
             before(grammarAccess.getSetFunctionAccess().getGroup()); 
            // InternalEquationDSL.g:475:3: ( rule__SetFunction__Group__0 )
            // InternalEquationDSL.g:475:4: rule__SetFunction__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__SetFunction__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getSetFunctionAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleSetFunction"


    // $ANTLR start "entryRuleNumberLiteral"
    // InternalEquationDSL.g:484:1: entryRuleNumberLiteral : ruleNumberLiteral EOF ;
    public final void entryRuleNumberLiteral() throws RecognitionException {
        try {
            // InternalEquationDSL.g:485:1: ( ruleNumberLiteral EOF )
            // InternalEquationDSL.g:486:1: ruleNumberLiteral EOF
            {
             before(grammarAccess.getNumberLiteralRule()); 
            pushFollow(FOLLOW_1);
            ruleNumberLiteral();

            state._fsp--;

             after(grammarAccess.getNumberLiteralRule()); 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleNumberLiteral"


    // $ANTLR start "ruleNumberLiteral"
    // InternalEquationDSL.g:493:1: ruleNumberLiteral : ( ( rule__NumberLiteral__Group__0 ) ) ;
    public final void ruleNumberLiteral() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalEquationDSL.g:497:2: ( ( ( rule__NumberLiteral__Group__0 ) ) )
            // InternalEquationDSL.g:498:2: ( ( rule__NumberLiteral__Group__0 ) )
            {
            // InternalEquationDSL.g:498:2: ( ( rule__NumberLiteral__Group__0 ) )
            // InternalEquationDSL.g:499:3: ( rule__NumberLiteral__Group__0 )
            {
             before(grammarAccess.getNumberLiteralAccess().getGroup()); 
            // InternalEquationDSL.g:500:3: ( rule__NumberLiteral__Group__0 )
            // InternalEquationDSL.g:500:4: rule__NumberLiteral__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__NumberLiteral__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getNumberLiteralAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleNumberLiteral"


    // $ANTLR start "entryRuleQualifiedName"
    // InternalEquationDSL.g:509:1: entryRuleQualifiedName : ruleQualifiedName EOF ;
    public final void entryRuleQualifiedName() throws RecognitionException {
        try {
            // InternalEquationDSL.g:510:1: ( ruleQualifiedName EOF )
            // InternalEquationDSL.g:511:1: ruleQualifiedName EOF
            {
             before(grammarAccess.getQualifiedNameRule()); 
            pushFollow(FOLLOW_1);
            ruleQualifiedName();

            state._fsp--;

             after(grammarAccess.getQualifiedNameRule()); 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleQualifiedName"


    // $ANTLR start "ruleQualifiedName"
    // InternalEquationDSL.g:518:1: ruleQualifiedName : ( ( rule__QualifiedName__Group__0 ) ) ;
    public final void ruleQualifiedName() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalEquationDSL.g:522:2: ( ( ( rule__QualifiedName__Group__0 ) ) )
            // InternalEquationDSL.g:523:2: ( ( rule__QualifiedName__Group__0 ) )
            {
            // InternalEquationDSL.g:523:2: ( ( rule__QualifiedName__Group__0 ) )
            // InternalEquationDSL.g:524:3: ( rule__QualifiedName__Group__0 )
            {
             before(grammarAccess.getQualifiedNameAccess().getGroup()); 
            // InternalEquationDSL.g:525:3: ( rule__QualifiedName__Group__0 )
            // InternalEquationDSL.g:525:4: rule__QualifiedName__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__QualifiedName__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getQualifiedNameAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleQualifiedName"


    // $ANTLR start "entryRuleNumberLiteralString"
    // InternalEquationDSL.g:534:1: entryRuleNumberLiteralString : ruleNumberLiteralString EOF ;
    public final void entryRuleNumberLiteralString() throws RecognitionException {
        try {
            // InternalEquationDSL.g:535:1: ( ruleNumberLiteralString EOF )
            // InternalEquationDSL.g:536:1: ruleNumberLiteralString EOF
            {
             before(grammarAccess.getNumberLiteralStringRule()); 
            pushFollow(FOLLOW_1);
            ruleNumberLiteralString();

            state._fsp--;

             after(grammarAccess.getNumberLiteralStringRule()); 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleNumberLiteralString"


    // $ANTLR start "ruleNumberLiteralString"
    // InternalEquationDSL.g:543:1: ruleNumberLiteralString : ( ( rule__NumberLiteralString__Group__0 ) ) ;
    public final void ruleNumberLiteralString() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalEquationDSL.g:547:2: ( ( ( rule__NumberLiteralString__Group__0 ) ) )
            // InternalEquationDSL.g:548:2: ( ( rule__NumberLiteralString__Group__0 ) )
            {
            // InternalEquationDSL.g:548:2: ( ( rule__NumberLiteralString__Group__0 ) )
            // InternalEquationDSL.g:549:3: ( rule__NumberLiteralString__Group__0 )
            {
             before(grammarAccess.getNumberLiteralStringAccess().getGroup()); 
            // InternalEquationDSL.g:550:3: ( rule__NumberLiteralString__Group__0 )
            // InternalEquationDSL.g:550:4: rule__NumberLiteralString__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__NumberLiteralString__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getNumberLiteralStringAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleNumberLiteralString"


    // $ANTLR start "entryRuleValuePi"
    // InternalEquationDSL.g:559:1: entryRuleValuePi : ruleValuePi EOF ;
    public final void entryRuleValuePi() throws RecognitionException {
        try {
            // InternalEquationDSL.g:560:1: ( ruleValuePi EOF )
            // InternalEquationDSL.g:561:1: ruleValuePi EOF
            {
             before(grammarAccess.getValuePiRule()); 
            pushFollow(FOLLOW_1);
            ruleValuePi();

            state._fsp--;

             after(grammarAccess.getValuePiRule()); 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleValuePi"


    // $ANTLR start "ruleValuePi"
    // InternalEquationDSL.g:568:1: ruleValuePi : ( ( rule__ValuePi__Group__0 ) ) ;
    public final void ruleValuePi() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalEquationDSL.g:572:2: ( ( ( rule__ValuePi__Group__0 ) ) )
            // InternalEquationDSL.g:573:2: ( ( rule__ValuePi__Group__0 ) )
            {
            // InternalEquationDSL.g:573:2: ( ( rule__ValuePi__Group__0 ) )
            // InternalEquationDSL.g:574:3: ( rule__ValuePi__Group__0 )
            {
             before(grammarAccess.getValuePiAccess().getGroup()); 
            // InternalEquationDSL.g:575:3: ( rule__ValuePi__Group__0 )
            // InternalEquationDSL.g:575:4: rule__ValuePi__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__ValuePi__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getValuePiAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleValuePi"


    // $ANTLR start "entryRuleValueE"
    // InternalEquationDSL.g:584:1: entryRuleValueE : ruleValueE EOF ;
    public final void entryRuleValueE() throws RecognitionException {
        try {
            // InternalEquationDSL.g:585:1: ( ruleValueE EOF )
            // InternalEquationDSL.g:586:1: ruleValueE EOF
            {
             before(grammarAccess.getValueERule()); 
            pushFollow(FOLLOW_1);
            ruleValueE();

            state._fsp--;

             after(grammarAccess.getValueERule()); 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleValueE"


    // $ANTLR start "ruleValueE"
    // InternalEquationDSL.g:593:1: ruleValueE : ( ( rule__ValueE__Group__0 ) ) ;
    public final void ruleValueE() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalEquationDSL.g:597:2: ( ( ( rule__ValueE__Group__0 ) ) )
            // InternalEquationDSL.g:598:2: ( ( rule__ValueE__Group__0 ) )
            {
            // InternalEquationDSL.g:598:2: ( ( rule__ValueE__Group__0 ) )
            // InternalEquationDSL.g:599:3: ( rule__ValueE__Group__0 )
            {
             before(grammarAccess.getValueEAccess().getGroup()); 
            // InternalEquationDSL.g:600:3: ( rule__ValueE__Group__0 )
            // InternalEquationDSL.g:600:4: rule__ValueE__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__ValueE__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getValueEAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleValueE"


    // $ANTLR start "ruleOperatorPlus"
    // InternalEquationDSL.g:609:1: ruleOperatorPlus : ( ( '+' ) ) ;
    public final void ruleOperatorPlus() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalEquationDSL.g:613:1: ( ( ( '+' ) ) )
            // InternalEquationDSL.g:614:2: ( ( '+' ) )
            {
            // InternalEquationDSL.g:614:2: ( ( '+' ) )
            // InternalEquationDSL.g:615:3: ( '+' )
            {
             before(grammarAccess.getOperatorPlusAccess().getPLUSEnumLiteralDeclaration()); 
            // InternalEquationDSL.g:616:3: ( '+' )
            // InternalEquationDSL.g:616:4: '+'
            {
            match(input,11,FOLLOW_2); 

            }

             after(grammarAccess.getOperatorPlusAccess().getPLUSEnumLiteralDeclaration()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleOperatorPlus"


    // $ANTLR start "ruleOperatorMinus"
    // InternalEquationDSL.g:625:1: ruleOperatorMinus : ( ( '-' ) ) ;
    public final void ruleOperatorMinus() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalEquationDSL.g:629:1: ( ( ( '-' ) ) )
            // InternalEquationDSL.g:630:2: ( ( '-' ) )
            {
            // InternalEquationDSL.g:630:2: ( ( '-' ) )
            // InternalEquationDSL.g:631:3: ( '-' )
            {
             before(grammarAccess.getOperatorMinusAccess().getMINUSEnumLiteralDeclaration()); 
            // InternalEquationDSL.g:632:3: ( '-' )
            // InternalEquationDSL.g:632:4: '-'
            {
            match(input,12,FOLLOW_2); 

            }

             after(grammarAccess.getOperatorMinusAccess().getMINUSEnumLiteralDeclaration()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleOperatorMinus"


    // $ANTLR start "ruleOperatorMultiply"
    // InternalEquationDSL.g:641:1: ruleOperatorMultiply : ( ( '*' ) ) ;
    public final void ruleOperatorMultiply() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalEquationDSL.g:645:1: ( ( ( '*' ) ) )
            // InternalEquationDSL.g:646:2: ( ( '*' ) )
            {
            // InternalEquationDSL.g:646:2: ( ( '*' ) )
            // InternalEquationDSL.g:647:3: ( '*' )
            {
             before(grammarAccess.getOperatorMultiplyAccess().getMULTIPLYEnumLiteralDeclaration()); 
            // InternalEquationDSL.g:648:3: ( '*' )
            // InternalEquationDSL.g:648:4: '*'
            {
            match(input,13,FOLLOW_2); 

            }

             after(grammarAccess.getOperatorMultiplyAccess().getMULTIPLYEnumLiteralDeclaration()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleOperatorMultiply"


    // $ANTLR start "ruleOperatorDivide"
    // InternalEquationDSL.g:657:1: ruleOperatorDivide : ( ( '/' ) ) ;
    public final void ruleOperatorDivide() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalEquationDSL.g:661:1: ( ( ( '/' ) ) )
            // InternalEquationDSL.g:662:2: ( ( '/' ) )
            {
            // InternalEquationDSL.g:662:2: ( ( '/' ) )
            // InternalEquationDSL.g:663:3: ( '/' )
            {
             before(grammarAccess.getOperatorDivideAccess().getDIVIDEEnumLiteralDeclaration()); 
            // InternalEquationDSL.g:664:3: ( '/' )
            // InternalEquationDSL.g:664:4: '/'
            {
            match(input,14,FOLLOW_2); 

            }

             after(grammarAccess.getOperatorDivideAccess().getDIVIDEEnumLiteralDeclaration()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleOperatorDivide"


    // $ANTLR start "ruleOperatorPower"
    // InternalEquationDSL.g:673:1: ruleOperatorPower : ( ( '^' ) ) ;
    public final void ruleOperatorPower() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalEquationDSL.g:677:1: ( ( ( '^' ) ) )
            // InternalEquationDSL.g:678:2: ( ( '^' ) )
            {
            // InternalEquationDSL.g:678:2: ( ( '^' ) )
            // InternalEquationDSL.g:679:3: ( '^' )
            {
             before(grammarAccess.getOperatorPowerAccess().getPOWEREnumLiteralDeclaration()); 
            // InternalEquationDSL.g:680:3: ( '^' )
            // InternalEquationDSL.g:680:4: '^'
            {
            match(input,15,FOLLOW_2); 

            }

             after(grammarAccess.getOperatorPowerAccess().getPOWEREnumLiteralDeclaration()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleOperatorPower"


    // $ANTLR start "ruleOperatorCos"
    // InternalEquationDSL.g:689:1: ruleOperatorCos : ( ( 'cos' ) ) ;
    public final void ruleOperatorCos() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalEquationDSL.g:693:1: ( ( ( 'cos' ) ) )
            // InternalEquationDSL.g:694:2: ( ( 'cos' ) )
            {
            // InternalEquationDSL.g:694:2: ( ( 'cos' ) )
            // InternalEquationDSL.g:695:3: ( 'cos' )
            {
             before(grammarAccess.getOperatorCosAccess().getCOSEnumLiteralDeclaration()); 
            // InternalEquationDSL.g:696:3: ( 'cos' )
            // InternalEquationDSL.g:696:4: 'cos'
            {
            match(input,16,FOLLOW_2); 

            }

             after(grammarAccess.getOperatorCosAccess().getCOSEnumLiteralDeclaration()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleOperatorCos"


    // $ANTLR start "ruleOperatorSin"
    // InternalEquationDSL.g:705:1: ruleOperatorSin : ( ( 'sin' ) ) ;
    public final void ruleOperatorSin() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalEquationDSL.g:709:1: ( ( ( 'sin' ) ) )
            // InternalEquationDSL.g:710:2: ( ( 'sin' ) )
            {
            // InternalEquationDSL.g:710:2: ( ( 'sin' ) )
            // InternalEquationDSL.g:711:3: ( 'sin' )
            {
             before(grammarAccess.getOperatorSinAccess().getSINEnumLiteralDeclaration()); 
            // InternalEquationDSL.g:712:3: ( 'sin' )
            // InternalEquationDSL.g:712:4: 'sin'
            {
            match(input,17,FOLLOW_2); 

            }

             after(grammarAccess.getOperatorSinAccess().getSINEnumLiteralDeclaration()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleOperatorSin"


    // $ANTLR start "ruleOperatorTan"
    // InternalEquationDSL.g:721:1: ruleOperatorTan : ( ( 'tan' ) ) ;
    public final void ruleOperatorTan() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalEquationDSL.g:725:1: ( ( ( 'tan' ) ) )
            // InternalEquationDSL.g:726:2: ( ( 'tan' ) )
            {
            // InternalEquationDSL.g:726:2: ( ( 'tan' ) )
            // InternalEquationDSL.g:727:3: ( 'tan' )
            {
             before(grammarAccess.getOperatorTanAccess().getTANEnumLiteralDeclaration()); 
            // InternalEquationDSL.g:728:3: ( 'tan' )
            // InternalEquationDSL.g:728:4: 'tan'
            {
            match(input,18,FOLLOW_2); 

            }

             after(grammarAccess.getOperatorTanAccess().getTANEnumLiteralDeclaration()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleOperatorTan"


    // $ANTLR start "ruleOperatorAtan"
    // InternalEquationDSL.g:737:1: ruleOperatorAtan : ( ( 'atan' ) ) ;
    public final void ruleOperatorAtan() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalEquationDSL.g:741:1: ( ( ( 'atan' ) ) )
            // InternalEquationDSL.g:742:2: ( ( 'atan' ) )
            {
            // InternalEquationDSL.g:742:2: ( ( 'atan' ) )
            // InternalEquationDSL.g:743:3: ( 'atan' )
            {
             before(grammarAccess.getOperatorAtanAccess().getATANEnumLiteralDeclaration()); 
            // InternalEquationDSL.g:744:3: ( 'atan' )
            // InternalEquationDSL.g:744:4: 'atan'
            {
            match(input,19,FOLLOW_2); 

            }

             after(grammarAccess.getOperatorAtanAccess().getATANEnumLiteralDeclaration()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleOperatorAtan"


    // $ANTLR start "ruleOperatorAcos"
    // InternalEquationDSL.g:753:1: ruleOperatorAcos : ( ( 'acos' ) ) ;
    public final void ruleOperatorAcos() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalEquationDSL.g:757:1: ( ( ( 'acos' ) ) )
            // InternalEquationDSL.g:758:2: ( ( 'acos' ) )
            {
            // InternalEquationDSL.g:758:2: ( ( 'acos' ) )
            // InternalEquationDSL.g:759:3: ( 'acos' )
            {
             before(grammarAccess.getOperatorAcosAccess().getACOSEnumLiteralDeclaration()); 
            // InternalEquationDSL.g:760:3: ( 'acos' )
            // InternalEquationDSL.g:760:4: 'acos'
            {
            match(input,20,FOLLOW_2); 

            }

             after(grammarAccess.getOperatorAcosAccess().getACOSEnumLiteralDeclaration()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleOperatorAcos"


    // $ANTLR start "ruleOperatorAsin"
    // InternalEquationDSL.g:769:1: ruleOperatorAsin : ( ( 'asin' ) ) ;
    public final void ruleOperatorAsin() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalEquationDSL.g:773:1: ( ( ( 'asin' ) ) )
            // InternalEquationDSL.g:774:2: ( ( 'asin' ) )
            {
            // InternalEquationDSL.g:774:2: ( ( 'asin' ) )
            // InternalEquationDSL.g:775:3: ( 'asin' )
            {
             before(grammarAccess.getOperatorAsinAccess().getASINEnumLiteralDeclaration()); 
            // InternalEquationDSL.g:776:3: ( 'asin' )
            // InternalEquationDSL.g:776:4: 'asin'
            {
            match(input,21,FOLLOW_2); 

            }

             after(grammarAccess.getOperatorAsinAccess().getASINEnumLiteralDeclaration()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleOperatorAsin"


    // $ANTLR start "ruleOperatorSqrt"
    // InternalEquationDSL.g:785:1: ruleOperatorSqrt : ( ( 'sqrt' ) ) ;
    public final void ruleOperatorSqrt() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalEquationDSL.g:789:1: ( ( ( 'sqrt' ) ) )
            // InternalEquationDSL.g:790:2: ( ( 'sqrt' ) )
            {
            // InternalEquationDSL.g:790:2: ( ( 'sqrt' ) )
            // InternalEquationDSL.g:791:3: ( 'sqrt' )
            {
             before(grammarAccess.getOperatorSqrtAccess().getSQRTEnumLiteralDeclaration()); 
            // InternalEquationDSL.g:792:3: ( 'sqrt' )
            // InternalEquationDSL.g:792:4: 'sqrt'
            {
            match(input,22,FOLLOW_2); 

            }

             after(grammarAccess.getOperatorSqrtAccess().getSQRTEnumLiteralDeclaration()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleOperatorSqrt"


    // $ANTLR start "ruleOperatorLog"
    // InternalEquationDSL.g:801:1: ruleOperatorLog : ( ( 'log' ) ) ;
    public final void ruleOperatorLog() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalEquationDSL.g:805:1: ( ( ( 'log' ) ) )
            // InternalEquationDSL.g:806:2: ( ( 'log' ) )
            {
            // InternalEquationDSL.g:806:2: ( ( 'log' ) )
            // InternalEquationDSL.g:807:3: ( 'log' )
            {
             before(grammarAccess.getOperatorLogAccess().getLOGEnumLiteralDeclaration()); 
            // InternalEquationDSL.g:808:3: ( 'log' )
            // InternalEquationDSL.g:808:4: 'log'
            {
            match(input,23,FOLLOW_2); 

            }

             after(grammarAccess.getOperatorLogAccess().getLOGEnumLiteralDeclaration()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleOperatorLog"


    // $ANTLR start "ruleOperatorLn"
    // InternalEquationDSL.g:817:1: ruleOperatorLn : ( ( 'ln' ) ) ;
    public final void ruleOperatorLn() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalEquationDSL.g:821:1: ( ( ( 'ln' ) ) )
            // InternalEquationDSL.g:822:2: ( ( 'ln' ) )
            {
            // InternalEquationDSL.g:822:2: ( ( 'ln' ) )
            // InternalEquationDSL.g:823:3: ( 'ln' )
            {
             before(grammarAccess.getOperatorLnAccess().getLNEnumLiteralDeclaration()); 
            // InternalEquationDSL.g:824:3: ( 'ln' )
            // InternalEquationDSL.g:824:4: 'ln'
            {
            match(input,24,FOLLOW_2); 

            }

             after(grammarAccess.getOperatorLnAccess().getLNEnumLiteralDeclaration()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleOperatorLn"


    // $ANTLR start "ruleOperatorExp"
    // InternalEquationDSL.g:833:1: ruleOperatorExp : ( ( 'exp' ) ) ;
    public final void ruleOperatorExp() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalEquationDSL.g:837:1: ( ( ( 'exp' ) ) )
            // InternalEquationDSL.g:838:2: ( ( 'exp' ) )
            {
            // InternalEquationDSL.g:838:2: ( ( 'exp' ) )
            // InternalEquationDSL.g:839:3: ( 'exp' )
            {
             before(grammarAccess.getOperatorExpAccess().getEXPEnumLiteralDeclaration()); 
            // InternalEquationDSL.g:840:3: ( 'exp' )
            // InternalEquationDSL.g:840:4: 'exp'
            {
            match(input,25,FOLLOW_2); 

            }

             after(grammarAccess.getOperatorExpAccess().getEXPEnumLiteralDeclaration()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleOperatorExp"


    // $ANTLR start "ruleOperatorLd"
    // InternalEquationDSL.g:849:1: ruleOperatorLd : ( ( 'ld' ) ) ;
    public final void ruleOperatorLd() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalEquationDSL.g:853:1: ( ( ( 'ld' ) ) )
            // InternalEquationDSL.g:854:2: ( ( 'ld' ) )
            {
            // InternalEquationDSL.g:854:2: ( ( 'ld' ) )
            // InternalEquationDSL.g:855:3: ( 'ld' )
            {
             before(grammarAccess.getOperatorLdAccess().getLDEnumLiteralDeclaration()); 
            // InternalEquationDSL.g:856:3: ( 'ld' )
            // InternalEquationDSL.g:856:4: 'ld'
            {
            match(input,26,FOLLOW_2); 

            }

             after(grammarAccess.getOperatorLdAccess().getLDEnumLiteralDeclaration()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleOperatorLd"


    // $ANTLR start "rule__EquationResult__Alternatives"
    // InternalEquationDSL.g:864:1: rule__EquationResult__Alternatives : ( ( ruleTypeInstanceResult ) | ( ruleEquationIntermediateResult ) );
    public final void rule__EquationResult__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalEquationDSL.g:868:1: ( ( ruleTypeInstanceResult ) | ( ruleEquationIntermediateResult ) )
            int alt1=2;
            int LA1_0 = input.LA(1);

            if ( (LA1_0==30) ) {
                alt1=1;
            }
            else if ( (LA1_0==31) ) {
                alt1=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 1, 0, input);

                throw nvae;
            }
            switch (alt1) {
                case 1 :
                    // InternalEquationDSL.g:869:2: ( ruleTypeInstanceResult )
                    {
                    // InternalEquationDSL.g:869:2: ( ruleTypeInstanceResult )
                    // InternalEquationDSL.g:870:3: ruleTypeInstanceResult
                    {
                     before(grammarAccess.getEquationResultAccess().getTypeInstanceResultParserRuleCall_0()); 
                    pushFollow(FOLLOW_2);
                    ruleTypeInstanceResult();

                    state._fsp--;

                     after(grammarAccess.getEquationResultAccess().getTypeInstanceResultParserRuleCall_0()); 

                    }


                    }
                    break;
                case 2 :
                    // InternalEquationDSL.g:875:2: ( ruleEquationIntermediateResult )
                    {
                    // InternalEquationDSL.g:875:2: ( ruleEquationIntermediateResult )
                    // InternalEquationDSL.g:876:3: ruleEquationIntermediateResult
                    {
                     before(grammarAccess.getEquationResultAccess().getEquationIntermediateResultParserRuleCall_1()); 
                    pushFollow(FOLLOW_2);
                    ruleEquationIntermediateResult();

                    state._fsp--;

                     after(grammarAccess.getEquationResultAccess().getEquationIntermediateResultParserRuleCall_1()); 

                    }


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__EquationResult__Alternatives"


    // $ANTLR start "rule__AdditionAndSubtraction__OperatorAlternatives_1_1_0"
    // InternalEquationDSL.g:885:1: rule__AdditionAndSubtraction__OperatorAlternatives_1_1_0 : ( ( ruleOperatorPlus ) | ( ruleOperatorMinus ) );
    public final void rule__AdditionAndSubtraction__OperatorAlternatives_1_1_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalEquationDSL.g:889:1: ( ( ruleOperatorPlus ) | ( ruleOperatorMinus ) )
            int alt2=2;
            int LA2_0 = input.LA(1);

            if ( (LA2_0==11) ) {
                alt2=1;
            }
            else if ( (LA2_0==12) ) {
                alt2=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 2, 0, input);

                throw nvae;
            }
            switch (alt2) {
                case 1 :
                    // InternalEquationDSL.g:890:2: ( ruleOperatorPlus )
                    {
                    // InternalEquationDSL.g:890:2: ( ruleOperatorPlus )
                    // InternalEquationDSL.g:891:3: ruleOperatorPlus
                    {
                     before(grammarAccess.getAdditionAndSubtractionAccess().getOperatorOperatorPlusEnumRuleCall_1_1_0_0()); 
                    pushFollow(FOLLOW_2);
                    ruleOperatorPlus();

                    state._fsp--;

                     after(grammarAccess.getAdditionAndSubtractionAccess().getOperatorOperatorPlusEnumRuleCall_1_1_0_0()); 

                    }


                    }
                    break;
                case 2 :
                    // InternalEquationDSL.g:896:2: ( ruleOperatorMinus )
                    {
                    // InternalEquationDSL.g:896:2: ( ruleOperatorMinus )
                    // InternalEquationDSL.g:897:3: ruleOperatorMinus
                    {
                     before(grammarAccess.getAdditionAndSubtractionAccess().getOperatorOperatorMinusEnumRuleCall_1_1_0_1()); 
                    pushFollow(FOLLOW_2);
                    ruleOperatorMinus();

                    state._fsp--;

                     after(grammarAccess.getAdditionAndSubtractionAccess().getOperatorOperatorMinusEnumRuleCall_1_1_0_1()); 

                    }


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__AdditionAndSubtraction__OperatorAlternatives_1_1_0"


    // $ANTLR start "rule__MultiplicationAndDivision__OperatorAlternatives_1_1_0"
    // InternalEquationDSL.g:906:1: rule__MultiplicationAndDivision__OperatorAlternatives_1_1_0 : ( ( ruleOperatorMultiply ) | ( ruleOperatorDivide ) );
    public final void rule__MultiplicationAndDivision__OperatorAlternatives_1_1_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalEquationDSL.g:910:1: ( ( ruleOperatorMultiply ) | ( ruleOperatorDivide ) )
            int alt3=2;
            int LA3_0 = input.LA(1);

            if ( (LA3_0==13) ) {
                alt3=1;
            }
            else if ( (LA3_0==14) ) {
                alt3=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 3, 0, input);

                throw nvae;
            }
            switch (alt3) {
                case 1 :
                    // InternalEquationDSL.g:911:2: ( ruleOperatorMultiply )
                    {
                    // InternalEquationDSL.g:911:2: ( ruleOperatorMultiply )
                    // InternalEquationDSL.g:912:3: ruleOperatorMultiply
                    {
                     before(grammarAccess.getMultiplicationAndDivisionAccess().getOperatorOperatorMultiplyEnumRuleCall_1_1_0_0()); 
                    pushFollow(FOLLOW_2);
                    ruleOperatorMultiply();

                    state._fsp--;

                     after(grammarAccess.getMultiplicationAndDivisionAccess().getOperatorOperatorMultiplyEnumRuleCall_1_1_0_0()); 

                    }


                    }
                    break;
                case 2 :
                    // InternalEquationDSL.g:917:2: ( ruleOperatorDivide )
                    {
                    // InternalEquationDSL.g:917:2: ( ruleOperatorDivide )
                    // InternalEquationDSL.g:918:3: ruleOperatorDivide
                    {
                     before(grammarAccess.getMultiplicationAndDivisionAccess().getOperatorOperatorDivideEnumRuleCall_1_1_0_1()); 
                    pushFollow(FOLLOW_2);
                    ruleOperatorDivide();

                    state._fsp--;

                     after(grammarAccess.getMultiplicationAndDivisionAccess().getOperatorOperatorDivideEnumRuleCall_1_1_0_1()); 

                    }


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__MultiplicationAndDivision__OperatorAlternatives_1_1_0"


    // $ANTLR start "rule__AExpression__Alternatives"
    // InternalEquationDSL.g:927:1: rule__AExpression__Alternatives : ( ( ruleALiteral ) | ( ruleReferencedInput ) | ( ruleParenthesis ) | ( ruleFunction ) | ( ruleAAdvancedFunction ) );
    public final void rule__AExpression__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalEquationDSL.g:931:1: ( ( ruleALiteral ) | ( ruleReferencedInput ) | ( ruleParenthesis ) | ( ruleFunction ) | ( ruleAAdvancedFunction ) )
            int alt4=5;
            switch ( input.LA(1) ) {
            case 12:
                {
                int LA4_1 = input.LA(2);

                if ( (LA4_1==RULE_INT) ) {
                    alt4=1;
                }
                else if ( (LA4_1==32) ) {
                    alt4=3;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 4, 1, input);

                    throw nvae;
                }
                }
                break;
            case RULE_INT:
            case 38:
            case 39:
                {
                alt4=1;
                }
                break;
            case RULE_ID:
                {
                int LA4_3 = input.LA(2);

                if ( (LA4_3==32||LA4_3==35) ) {
                    alt4=5;
                }
                else if ( (LA4_3==EOF||(LA4_3>=11 && LA4_3<=15)||LA4_3==28||(LA4_3>=33 && LA4_3<=34)||LA4_3==37) ) {
                    alt4=2;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 4, 3, input);

                    throw nvae;
                }
                }
                break;
            case 32:
                {
                alt4=3;
                }
                break;
            case 16:
            case 17:
            case 18:
            case 19:
            case 20:
            case 21:
            case 22:
            case 23:
            case 24:
            case 25:
            case 26:
                {
                alt4=4;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 4, 0, input);

                throw nvae;
            }

            switch (alt4) {
                case 1 :
                    // InternalEquationDSL.g:932:2: ( ruleALiteral )
                    {
                    // InternalEquationDSL.g:932:2: ( ruleALiteral )
                    // InternalEquationDSL.g:933:3: ruleALiteral
                    {
                     before(grammarAccess.getAExpressionAccess().getALiteralParserRuleCall_0()); 
                    pushFollow(FOLLOW_2);
                    ruleALiteral();

                    state._fsp--;

                     after(grammarAccess.getAExpressionAccess().getALiteralParserRuleCall_0()); 

                    }


                    }
                    break;
                case 2 :
                    // InternalEquationDSL.g:938:2: ( ruleReferencedInput )
                    {
                    // InternalEquationDSL.g:938:2: ( ruleReferencedInput )
                    // InternalEquationDSL.g:939:3: ruleReferencedInput
                    {
                     before(grammarAccess.getAExpressionAccess().getReferencedInputParserRuleCall_1()); 
                    pushFollow(FOLLOW_2);
                    ruleReferencedInput();

                    state._fsp--;

                     after(grammarAccess.getAExpressionAccess().getReferencedInputParserRuleCall_1()); 

                    }


                    }
                    break;
                case 3 :
                    // InternalEquationDSL.g:944:2: ( ruleParenthesis )
                    {
                    // InternalEquationDSL.g:944:2: ( ruleParenthesis )
                    // InternalEquationDSL.g:945:3: ruleParenthesis
                    {
                     before(grammarAccess.getAExpressionAccess().getParenthesisParserRuleCall_2()); 
                    pushFollow(FOLLOW_2);
                    ruleParenthesis();

                    state._fsp--;

                     after(grammarAccess.getAExpressionAccess().getParenthesisParserRuleCall_2()); 

                    }


                    }
                    break;
                case 4 :
                    // InternalEquationDSL.g:950:2: ( ruleFunction )
                    {
                    // InternalEquationDSL.g:950:2: ( ruleFunction )
                    // InternalEquationDSL.g:951:3: ruleFunction
                    {
                     before(grammarAccess.getAExpressionAccess().getFunctionParserRuleCall_3()); 
                    pushFollow(FOLLOW_2);
                    ruleFunction();

                    state._fsp--;

                     after(grammarAccess.getAExpressionAccess().getFunctionParserRuleCall_3()); 

                    }


                    }
                    break;
                case 5 :
                    // InternalEquationDSL.g:956:2: ( ruleAAdvancedFunction )
                    {
                    // InternalEquationDSL.g:956:2: ( ruleAAdvancedFunction )
                    // InternalEquationDSL.g:957:3: ruleAAdvancedFunction
                    {
                     before(grammarAccess.getAExpressionAccess().getAAdvancedFunctionParserRuleCall_4()); 
                    pushFollow(FOLLOW_2);
                    ruleAAdvancedFunction();

                    state._fsp--;

                     after(grammarAccess.getAExpressionAccess().getAAdvancedFunctionParserRuleCall_4()); 

                    }


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__AExpression__Alternatives"


    // $ANTLR start "rule__ALiteral__Alternatives"
    // InternalEquationDSL.g:966:1: rule__ALiteral__Alternatives : ( ( ruleNumberLiteral ) | ( ruleValueE ) | ( ruleValuePi ) );
    public final void rule__ALiteral__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalEquationDSL.g:970:1: ( ( ruleNumberLiteral ) | ( ruleValueE ) | ( ruleValuePi ) )
            int alt5=3;
            switch ( input.LA(1) ) {
            case RULE_INT:
            case 12:
                {
                alt5=1;
                }
                break;
            case 39:
                {
                alt5=2;
                }
                break;
            case 38:
                {
                alt5=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 5, 0, input);

                throw nvae;
            }

            switch (alt5) {
                case 1 :
                    // InternalEquationDSL.g:971:2: ( ruleNumberLiteral )
                    {
                    // InternalEquationDSL.g:971:2: ( ruleNumberLiteral )
                    // InternalEquationDSL.g:972:3: ruleNumberLiteral
                    {
                     before(grammarAccess.getALiteralAccess().getNumberLiteralParserRuleCall_0()); 
                    pushFollow(FOLLOW_2);
                    ruleNumberLiteral();

                    state._fsp--;

                     after(grammarAccess.getALiteralAccess().getNumberLiteralParserRuleCall_0()); 

                    }


                    }
                    break;
                case 2 :
                    // InternalEquationDSL.g:977:2: ( ruleValueE )
                    {
                    // InternalEquationDSL.g:977:2: ( ruleValueE )
                    // InternalEquationDSL.g:978:3: ruleValueE
                    {
                     before(grammarAccess.getALiteralAccess().getValueEParserRuleCall_1()); 
                    pushFollow(FOLLOW_2);
                    ruleValueE();

                    state._fsp--;

                     after(grammarAccess.getALiteralAccess().getValueEParserRuleCall_1()); 

                    }


                    }
                    break;
                case 3 :
                    // InternalEquationDSL.g:983:2: ( ruleValuePi )
                    {
                    // InternalEquationDSL.g:983:2: ( ruleValuePi )
                    // InternalEquationDSL.g:984:3: ruleValuePi
                    {
                     before(grammarAccess.getALiteralAccess().getValuePiParserRuleCall_2()); 
                    pushFollow(FOLLOW_2);
                    ruleValuePi();

                    state._fsp--;

                     after(grammarAccess.getALiteralAccess().getValuePiParserRuleCall_2()); 

                    }


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ALiteral__Alternatives"


    // $ANTLR start "rule__Function__OperatorAlternatives_1_0"
    // InternalEquationDSL.g:993:1: rule__Function__OperatorAlternatives_1_0 : ( ( ruleOperatorCos ) | ( ruleOperatorSin ) | ( ruleOperatorTan ) | ( ruleOperatorAtan ) | ( ruleOperatorAcos ) | ( ruleOperatorAsin ) | ( ruleOperatorSqrt ) | ( ruleOperatorLog ) | ( ruleOperatorLn ) | ( ruleOperatorLd ) | ( ruleOperatorExp ) );
    public final void rule__Function__OperatorAlternatives_1_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalEquationDSL.g:997:1: ( ( ruleOperatorCos ) | ( ruleOperatorSin ) | ( ruleOperatorTan ) | ( ruleOperatorAtan ) | ( ruleOperatorAcos ) | ( ruleOperatorAsin ) | ( ruleOperatorSqrt ) | ( ruleOperatorLog ) | ( ruleOperatorLn ) | ( ruleOperatorLd ) | ( ruleOperatorExp ) )
            int alt6=11;
            switch ( input.LA(1) ) {
            case 16:
                {
                alt6=1;
                }
                break;
            case 17:
                {
                alt6=2;
                }
                break;
            case 18:
                {
                alt6=3;
                }
                break;
            case 19:
                {
                alt6=4;
                }
                break;
            case 20:
                {
                alt6=5;
                }
                break;
            case 21:
                {
                alt6=6;
                }
                break;
            case 22:
                {
                alt6=7;
                }
                break;
            case 23:
                {
                alt6=8;
                }
                break;
            case 24:
                {
                alt6=9;
                }
                break;
            case 26:
                {
                alt6=10;
                }
                break;
            case 25:
                {
                alt6=11;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 6, 0, input);

                throw nvae;
            }

            switch (alt6) {
                case 1 :
                    // InternalEquationDSL.g:998:2: ( ruleOperatorCos )
                    {
                    // InternalEquationDSL.g:998:2: ( ruleOperatorCos )
                    // InternalEquationDSL.g:999:3: ruleOperatorCos
                    {
                     before(grammarAccess.getFunctionAccess().getOperatorOperatorCosEnumRuleCall_1_0_0()); 
                    pushFollow(FOLLOW_2);
                    ruleOperatorCos();

                    state._fsp--;

                     after(grammarAccess.getFunctionAccess().getOperatorOperatorCosEnumRuleCall_1_0_0()); 

                    }


                    }
                    break;
                case 2 :
                    // InternalEquationDSL.g:1004:2: ( ruleOperatorSin )
                    {
                    // InternalEquationDSL.g:1004:2: ( ruleOperatorSin )
                    // InternalEquationDSL.g:1005:3: ruleOperatorSin
                    {
                     before(grammarAccess.getFunctionAccess().getOperatorOperatorSinEnumRuleCall_1_0_1()); 
                    pushFollow(FOLLOW_2);
                    ruleOperatorSin();

                    state._fsp--;

                     after(grammarAccess.getFunctionAccess().getOperatorOperatorSinEnumRuleCall_1_0_1()); 

                    }


                    }
                    break;
                case 3 :
                    // InternalEquationDSL.g:1010:2: ( ruleOperatorTan )
                    {
                    // InternalEquationDSL.g:1010:2: ( ruleOperatorTan )
                    // InternalEquationDSL.g:1011:3: ruleOperatorTan
                    {
                     before(grammarAccess.getFunctionAccess().getOperatorOperatorTanEnumRuleCall_1_0_2()); 
                    pushFollow(FOLLOW_2);
                    ruleOperatorTan();

                    state._fsp--;

                     after(grammarAccess.getFunctionAccess().getOperatorOperatorTanEnumRuleCall_1_0_2()); 

                    }


                    }
                    break;
                case 4 :
                    // InternalEquationDSL.g:1016:2: ( ruleOperatorAtan )
                    {
                    // InternalEquationDSL.g:1016:2: ( ruleOperatorAtan )
                    // InternalEquationDSL.g:1017:3: ruleOperatorAtan
                    {
                     before(grammarAccess.getFunctionAccess().getOperatorOperatorAtanEnumRuleCall_1_0_3()); 
                    pushFollow(FOLLOW_2);
                    ruleOperatorAtan();

                    state._fsp--;

                     after(grammarAccess.getFunctionAccess().getOperatorOperatorAtanEnumRuleCall_1_0_3()); 

                    }


                    }
                    break;
                case 5 :
                    // InternalEquationDSL.g:1022:2: ( ruleOperatorAcos )
                    {
                    // InternalEquationDSL.g:1022:2: ( ruleOperatorAcos )
                    // InternalEquationDSL.g:1023:3: ruleOperatorAcos
                    {
                     before(grammarAccess.getFunctionAccess().getOperatorOperatorAcosEnumRuleCall_1_0_4()); 
                    pushFollow(FOLLOW_2);
                    ruleOperatorAcos();

                    state._fsp--;

                     after(grammarAccess.getFunctionAccess().getOperatorOperatorAcosEnumRuleCall_1_0_4()); 

                    }


                    }
                    break;
                case 6 :
                    // InternalEquationDSL.g:1028:2: ( ruleOperatorAsin )
                    {
                    // InternalEquationDSL.g:1028:2: ( ruleOperatorAsin )
                    // InternalEquationDSL.g:1029:3: ruleOperatorAsin
                    {
                     before(grammarAccess.getFunctionAccess().getOperatorOperatorAsinEnumRuleCall_1_0_5()); 
                    pushFollow(FOLLOW_2);
                    ruleOperatorAsin();

                    state._fsp--;

                     after(grammarAccess.getFunctionAccess().getOperatorOperatorAsinEnumRuleCall_1_0_5()); 

                    }


                    }
                    break;
                case 7 :
                    // InternalEquationDSL.g:1034:2: ( ruleOperatorSqrt )
                    {
                    // InternalEquationDSL.g:1034:2: ( ruleOperatorSqrt )
                    // InternalEquationDSL.g:1035:3: ruleOperatorSqrt
                    {
                     before(grammarAccess.getFunctionAccess().getOperatorOperatorSqrtEnumRuleCall_1_0_6()); 
                    pushFollow(FOLLOW_2);
                    ruleOperatorSqrt();

                    state._fsp--;

                     after(grammarAccess.getFunctionAccess().getOperatorOperatorSqrtEnumRuleCall_1_0_6()); 

                    }


                    }
                    break;
                case 8 :
                    // InternalEquationDSL.g:1040:2: ( ruleOperatorLog )
                    {
                    // InternalEquationDSL.g:1040:2: ( ruleOperatorLog )
                    // InternalEquationDSL.g:1041:3: ruleOperatorLog
                    {
                     before(grammarAccess.getFunctionAccess().getOperatorOperatorLogEnumRuleCall_1_0_7()); 
                    pushFollow(FOLLOW_2);
                    ruleOperatorLog();

                    state._fsp--;

                     after(grammarAccess.getFunctionAccess().getOperatorOperatorLogEnumRuleCall_1_0_7()); 

                    }


                    }
                    break;
                case 9 :
                    // InternalEquationDSL.g:1046:2: ( ruleOperatorLn )
                    {
                    // InternalEquationDSL.g:1046:2: ( ruleOperatorLn )
                    // InternalEquationDSL.g:1047:3: ruleOperatorLn
                    {
                     before(grammarAccess.getFunctionAccess().getOperatorOperatorLnEnumRuleCall_1_0_8()); 
                    pushFollow(FOLLOW_2);
                    ruleOperatorLn();

                    state._fsp--;

                     after(grammarAccess.getFunctionAccess().getOperatorOperatorLnEnumRuleCall_1_0_8()); 

                    }


                    }
                    break;
                case 10 :
                    // InternalEquationDSL.g:1052:2: ( ruleOperatorLd )
                    {
                    // InternalEquationDSL.g:1052:2: ( ruleOperatorLd )
                    // InternalEquationDSL.g:1053:3: ruleOperatorLd
                    {
                     before(grammarAccess.getFunctionAccess().getOperatorOperatorLdEnumRuleCall_1_0_9()); 
                    pushFollow(FOLLOW_2);
                    ruleOperatorLd();

                    state._fsp--;

                     after(grammarAccess.getFunctionAccess().getOperatorOperatorLdEnumRuleCall_1_0_9()); 

                    }


                    }
                    break;
                case 11 :
                    // InternalEquationDSL.g:1058:2: ( ruleOperatorExp )
                    {
                    // InternalEquationDSL.g:1058:2: ( ruleOperatorExp )
                    // InternalEquationDSL.g:1059:3: ruleOperatorExp
                    {
                     before(grammarAccess.getFunctionAccess().getOperatorOperatorExpEnumRuleCall_1_0_10()); 
                    pushFollow(FOLLOW_2);
                    ruleOperatorExp();

                    state._fsp--;

                     after(grammarAccess.getFunctionAccess().getOperatorOperatorExpEnumRuleCall_1_0_10()); 

                    }


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Function__OperatorAlternatives_1_0"


    // $ANTLR start "rule__AAdvancedFunction__Alternatives"
    // InternalEquationDSL.g:1068:1: rule__AAdvancedFunction__Alternatives : ( ( ruleAdvancedFunction ) | ( ruleSetFunction ) );
    public final void rule__AAdvancedFunction__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalEquationDSL.g:1072:1: ( ( ruleAdvancedFunction ) | ( ruleSetFunction ) )
            int alt7=2;
            int LA7_0 = input.LA(1);

            if ( (LA7_0==RULE_ID) ) {
                int LA7_1 = input.LA(2);

                if ( (LA7_1==32) ) {
                    alt7=1;
                }
                else if ( (LA7_1==35) ) {
                    alt7=2;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 7, 1, input);

                    throw nvae;
                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 7, 0, input);

                throw nvae;
            }
            switch (alt7) {
                case 1 :
                    // InternalEquationDSL.g:1073:2: ( ruleAdvancedFunction )
                    {
                    // InternalEquationDSL.g:1073:2: ( ruleAdvancedFunction )
                    // InternalEquationDSL.g:1074:3: ruleAdvancedFunction
                    {
                     before(grammarAccess.getAAdvancedFunctionAccess().getAdvancedFunctionParserRuleCall_0()); 
                    pushFollow(FOLLOW_2);
                    ruleAdvancedFunction();

                    state._fsp--;

                     after(grammarAccess.getAAdvancedFunctionAccess().getAdvancedFunctionParserRuleCall_0()); 

                    }


                    }
                    break;
                case 2 :
                    // InternalEquationDSL.g:1079:2: ( ruleSetFunction )
                    {
                    // InternalEquationDSL.g:1079:2: ( ruleSetFunction )
                    // InternalEquationDSL.g:1080:3: ruleSetFunction
                    {
                     before(grammarAccess.getAAdvancedFunctionAccess().getSetFunctionParserRuleCall_1()); 
                    pushFollow(FOLLOW_2);
                    ruleSetFunction();

                    state._fsp--;

                     after(grammarAccess.getAAdvancedFunctionAccess().getSetFunctionParserRuleCall_1()); 

                    }


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__AAdvancedFunction__Alternatives"


    // $ANTLR start "rule__EquationSection__Group__0"
    // InternalEquationDSL.g:1089:1: rule__EquationSection__Group__0 : rule__EquationSection__Group__0__Impl rule__EquationSection__Group__1 ;
    public final void rule__EquationSection__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalEquationDSL.g:1093:1: ( rule__EquationSection__Group__0__Impl rule__EquationSection__Group__1 )
            // InternalEquationDSL.g:1094:2: rule__EquationSection__Group__0__Impl rule__EquationSection__Group__1
            {
            pushFollow(FOLLOW_3);
            rule__EquationSection__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__EquationSection__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__EquationSection__Group__0"


    // $ANTLR start "rule__EquationSection__Group__0__Impl"
    // InternalEquationDSL.g:1101:1: rule__EquationSection__Group__0__Impl : ( ( rule__EquationSection__ImportsAssignment_0 )* ) ;
    public final void rule__EquationSection__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalEquationDSL.g:1105:1: ( ( ( rule__EquationSection__ImportsAssignment_0 )* ) )
            // InternalEquationDSL.g:1106:1: ( ( rule__EquationSection__ImportsAssignment_0 )* )
            {
            // InternalEquationDSL.g:1106:1: ( ( rule__EquationSection__ImportsAssignment_0 )* )
            // InternalEquationDSL.g:1107:2: ( rule__EquationSection__ImportsAssignment_0 )*
            {
             before(grammarAccess.getEquationSectionAccess().getImportsAssignment_0()); 
            // InternalEquationDSL.g:1108:2: ( rule__EquationSection__ImportsAssignment_0 )*
            loop8:
            do {
                int alt8=2;
                int LA8_0 = input.LA(1);

                if ( (LA8_0==27) ) {
                    alt8=1;
                }


                switch (alt8) {
            	case 1 :
            	    // InternalEquationDSL.g:1108:3: rule__EquationSection__ImportsAssignment_0
            	    {
            	    pushFollow(FOLLOW_4);
            	    rule__EquationSection__ImportsAssignment_0();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop8;
                }
            } while (true);

             after(grammarAccess.getEquationSectionAccess().getImportsAssignment_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__EquationSection__Group__0__Impl"


    // $ANTLR start "rule__EquationSection__Group__1"
    // InternalEquationDSL.g:1116:1: rule__EquationSection__Group__1 : rule__EquationSection__Group__1__Impl ;
    public final void rule__EquationSection__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalEquationDSL.g:1120:1: ( rule__EquationSection__Group__1__Impl )
            // InternalEquationDSL.g:1121:2: rule__EquationSection__Group__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__EquationSection__Group__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__EquationSection__Group__1"


    // $ANTLR start "rule__EquationSection__Group__1__Impl"
    // InternalEquationDSL.g:1127:1: rule__EquationSection__Group__1__Impl : ( ( rule__EquationSection__EquationsAssignment_1 )* ) ;
    public final void rule__EquationSection__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalEquationDSL.g:1131:1: ( ( ( rule__EquationSection__EquationsAssignment_1 )* ) )
            // InternalEquationDSL.g:1132:1: ( ( rule__EquationSection__EquationsAssignment_1 )* )
            {
            // InternalEquationDSL.g:1132:1: ( ( rule__EquationSection__EquationsAssignment_1 )* )
            // InternalEquationDSL.g:1133:2: ( rule__EquationSection__EquationsAssignment_1 )*
            {
             before(grammarAccess.getEquationSectionAccess().getEquationsAssignment_1()); 
            // InternalEquationDSL.g:1134:2: ( rule__EquationSection__EquationsAssignment_1 )*
            loop9:
            do {
                int alt9=2;
                int LA9_0 = input.LA(1);

                if ( ((LA9_0>=30 && LA9_0<=31)) ) {
                    alt9=1;
                }


                switch (alt9) {
            	case 1 :
            	    // InternalEquationDSL.g:1134:3: rule__EquationSection__EquationsAssignment_1
            	    {
            	    pushFollow(FOLLOW_5);
            	    rule__EquationSection__EquationsAssignment_1();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop9;
                }
            } while (true);

             after(grammarAccess.getEquationSectionAccess().getEquationsAssignment_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__EquationSection__Group__1__Impl"


    // $ANTLR start "rule__Import__Group__0"
    // InternalEquationDSL.g:1143:1: rule__Import__Group__0 : rule__Import__Group__0__Impl rule__Import__Group__1 ;
    public final void rule__Import__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalEquationDSL.g:1147:1: ( rule__Import__Group__0__Impl rule__Import__Group__1 )
            // InternalEquationDSL.g:1148:2: rule__Import__Group__0__Impl rule__Import__Group__1
            {
            pushFollow(FOLLOW_6);
            rule__Import__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Import__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Import__Group__0"


    // $ANTLR start "rule__Import__Group__0__Impl"
    // InternalEquationDSL.g:1155:1: rule__Import__Group__0__Impl : ( 'Import:' ) ;
    public final void rule__Import__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalEquationDSL.g:1159:1: ( ( 'Import:' ) )
            // InternalEquationDSL.g:1160:1: ( 'Import:' )
            {
            // InternalEquationDSL.g:1160:1: ( 'Import:' )
            // InternalEquationDSL.g:1161:2: 'Import:'
            {
             before(grammarAccess.getImportAccess().getImportKeyword_0()); 
            match(input,27,FOLLOW_2); 
             after(grammarAccess.getImportAccess().getImportKeyword_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Import__Group__0__Impl"


    // $ANTLR start "rule__Import__Group__1"
    // InternalEquationDSL.g:1170:1: rule__Import__Group__1 : rule__Import__Group__1__Impl rule__Import__Group__2 ;
    public final void rule__Import__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalEquationDSL.g:1174:1: ( rule__Import__Group__1__Impl rule__Import__Group__2 )
            // InternalEquationDSL.g:1175:2: rule__Import__Group__1__Impl rule__Import__Group__2
            {
            pushFollow(FOLLOW_7);
            rule__Import__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Import__Group__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Import__Group__1"


    // $ANTLR start "rule__Import__Group__1__Impl"
    // InternalEquationDSL.g:1182:1: rule__Import__Group__1__Impl : ( ( rule__Import__ImportedNamespaceAssignment_1 ) ) ;
    public final void rule__Import__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalEquationDSL.g:1186:1: ( ( ( rule__Import__ImportedNamespaceAssignment_1 ) ) )
            // InternalEquationDSL.g:1187:1: ( ( rule__Import__ImportedNamespaceAssignment_1 ) )
            {
            // InternalEquationDSL.g:1187:1: ( ( rule__Import__ImportedNamespaceAssignment_1 ) )
            // InternalEquationDSL.g:1188:2: ( rule__Import__ImportedNamespaceAssignment_1 )
            {
             before(grammarAccess.getImportAccess().getImportedNamespaceAssignment_1()); 
            // InternalEquationDSL.g:1189:2: ( rule__Import__ImportedNamespaceAssignment_1 )
            // InternalEquationDSL.g:1189:3: rule__Import__ImportedNamespaceAssignment_1
            {
            pushFollow(FOLLOW_2);
            rule__Import__ImportedNamespaceAssignment_1();

            state._fsp--;


            }

             after(grammarAccess.getImportAccess().getImportedNamespaceAssignment_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Import__Group__1__Impl"


    // $ANTLR start "rule__Import__Group__2"
    // InternalEquationDSL.g:1197:1: rule__Import__Group__2 : rule__Import__Group__2__Impl ;
    public final void rule__Import__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalEquationDSL.g:1201:1: ( rule__Import__Group__2__Impl )
            // InternalEquationDSL.g:1202:2: rule__Import__Group__2__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Import__Group__2__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Import__Group__2"


    // $ANTLR start "rule__Import__Group__2__Impl"
    // InternalEquationDSL.g:1208:1: rule__Import__Group__2__Impl : ( ';' ) ;
    public final void rule__Import__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalEquationDSL.g:1212:1: ( ( ';' ) )
            // InternalEquationDSL.g:1213:1: ( ';' )
            {
            // InternalEquationDSL.g:1213:1: ( ';' )
            // InternalEquationDSL.g:1214:2: ';'
            {
             before(grammarAccess.getImportAccess().getSemicolonKeyword_2()); 
            match(input,28,FOLLOW_2); 
             after(grammarAccess.getImportAccess().getSemicolonKeyword_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Import__Group__2__Impl"


    // $ANTLR start "rule__Equation__Group__0"
    // InternalEquationDSL.g:1224:1: rule__Equation__Group__0 : rule__Equation__Group__0__Impl rule__Equation__Group__1 ;
    public final void rule__Equation__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalEquationDSL.g:1228:1: ( rule__Equation__Group__0__Impl rule__Equation__Group__1 )
            // InternalEquationDSL.g:1229:2: rule__Equation__Group__0__Impl rule__Equation__Group__1
            {
            pushFollow(FOLLOW_8);
            rule__Equation__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Equation__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Equation__Group__0"


    // $ANTLR start "rule__Equation__Group__0__Impl"
    // InternalEquationDSL.g:1236:1: rule__Equation__Group__0__Impl : ( ( rule__Equation__ResultAssignment_0 ) ) ;
    public final void rule__Equation__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalEquationDSL.g:1240:1: ( ( ( rule__Equation__ResultAssignment_0 ) ) )
            // InternalEquationDSL.g:1241:1: ( ( rule__Equation__ResultAssignment_0 ) )
            {
            // InternalEquationDSL.g:1241:1: ( ( rule__Equation__ResultAssignment_0 ) )
            // InternalEquationDSL.g:1242:2: ( rule__Equation__ResultAssignment_0 )
            {
             before(grammarAccess.getEquationAccess().getResultAssignment_0()); 
            // InternalEquationDSL.g:1243:2: ( rule__Equation__ResultAssignment_0 )
            // InternalEquationDSL.g:1243:3: rule__Equation__ResultAssignment_0
            {
            pushFollow(FOLLOW_2);
            rule__Equation__ResultAssignment_0();

            state._fsp--;


            }

             after(grammarAccess.getEquationAccess().getResultAssignment_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Equation__Group__0__Impl"


    // $ANTLR start "rule__Equation__Group__1"
    // InternalEquationDSL.g:1251:1: rule__Equation__Group__1 : rule__Equation__Group__1__Impl rule__Equation__Group__2 ;
    public final void rule__Equation__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalEquationDSL.g:1255:1: ( rule__Equation__Group__1__Impl rule__Equation__Group__2 )
            // InternalEquationDSL.g:1256:2: rule__Equation__Group__1__Impl rule__Equation__Group__2
            {
            pushFollow(FOLLOW_9);
            rule__Equation__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Equation__Group__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Equation__Group__1"


    // $ANTLR start "rule__Equation__Group__1__Impl"
    // InternalEquationDSL.g:1263:1: rule__Equation__Group__1__Impl : ( '=' ) ;
    public final void rule__Equation__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalEquationDSL.g:1267:1: ( ( '=' ) )
            // InternalEquationDSL.g:1268:1: ( '=' )
            {
            // InternalEquationDSL.g:1268:1: ( '=' )
            // InternalEquationDSL.g:1269:2: '='
            {
             before(grammarAccess.getEquationAccess().getEqualsSignKeyword_1()); 
            match(input,29,FOLLOW_2); 
             after(grammarAccess.getEquationAccess().getEqualsSignKeyword_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Equation__Group__1__Impl"


    // $ANTLR start "rule__Equation__Group__2"
    // InternalEquationDSL.g:1278:1: rule__Equation__Group__2 : rule__Equation__Group__2__Impl rule__Equation__Group__3 ;
    public final void rule__Equation__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalEquationDSL.g:1282:1: ( rule__Equation__Group__2__Impl rule__Equation__Group__3 )
            // InternalEquationDSL.g:1283:2: rule__Equation__Group__2__Impl rule__Equation__Group__3
            {
            pushFollow(FOLLOW_7);
            rule__Equation__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Equation__Group__3();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Equation__Group__2"


    // $ANTLR start "rule__Equation__Group__2__Impl"
    // InternalEquationDSL.g:1290:1: rule__Equation__Group__2__Impl : ( ( rule__Equation__ExpressionAssignment_2 ) ) ;
    public final void rule__Equation__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalEquationDSL.g:1294:1: ( ( ( rule__Equation__ExpressionAssignment_2 ) ) )
            // InternalEquationDSL.g:1295:1: ( ( rule__Equation__ExpressionAssignment_2 ) )
            {
            // InternalEquationDSL.g:1295:1: ( ( rule__Equation__ExpressionAssignment_2 ) )
            // InternalEquationDSL.g:1296:2: ( rule__Equation__ExpressionAssignment_2 )
            {
             before(grammarAccess.getEquationAccess().getExpressionAssignment_2()); 
            // InternalEquationDSL.g:1297:2: ( rule__Equation__ExpressionAssignment_2 )
            // InternalEquationDSL.g:1297:3: rule__Equation__ExpressionAssignment_2
            {
            pushFollow(FOLLOW_2);
            rule__Equation__ExpressionAssignment_2();

            state._fsp--;


            }

             after(grammarAccess.getEquationAccess().getExpressionAssignment_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Equation__Group__2__Impl"


    // $ANTLR start "rule__Equation__Group__3"
    // InternalEquationDSL.g:1305:1: rule__Equation__Group__3 : rule__Equation__Group__3__Impl ;
    public final void rule__Equation__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalEquationDSL.g:1309:1: ( rule__Equation__Group__3__Impl )
            // InternalEquationDSL.g:1310:2: rule__Equation__Group__3__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Equation__Group__3__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Equation__Group__3"


    // $ANTLR start "rule__Equation__Group__3__Impl"
    // InternalEquationDSL.g:1316:1: rule__Equation__Group__3__Impl : ( ';' ) ;
    public final void rule__Equation__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalEquationDSL.g:1320:1: ( ( ';' ) )
            // InternalEquationDSL.g:1321:1: ( ';' )
            {
            // InternalEquationDSL.g:1321:1: ( ';' )
            // InternalEquationDSL.g:1322:2: ';'
            {
             before(grammarAccess.getEquationAccess().getSemicolonKeyword_3()); 
            match(input,28,FOLLOW_2); 
             after(grammarAccess.getEquationAccess().getSemicolonKeyword_3()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Equation__Group__3__Impl"


    // $ANTLR start "rule__TypeInstanceResult__Group__0"
    // InternalEquationDSL.g:1332:1: rule__TypeInstanceResult__Group__0 : rule__TypeInstanceResult__Group__0__Impl rule__TypeInstanceResult__Group__1 ;
    public final void rule__TypeInstanceResult__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalEquationDSL.g:1336:1: ( rule__TypeInstanceResult__Group__0__Impl rule__TypeInstanceResult__Group__1 )
            // InternalEquationDSL.g:1337:2: rule__TypeInstanceResult__Group__0__Impl rule__TypeInstanceResult__Group__1
            {
            pushFollow(FOLLOW_10);
            rule__TypeInstanceResult__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__TypeInstanceResult__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__TypeInstanceResult__Group__0"


    // $ANTLR start "rule__TypeInstanceResult__Group__0__Impl"
    // InternalEquationDSL.g:1344:1: rule__TypeInstanceResult__Group__0__Impl : ( () ) ;
    public final void rule__TypeInstanceResult__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalEquationDSL.g:1348:1: ( ( () ) )
            // InternalEquationDSL.g:1349:1: ( () )
            {
            // InternalEquationDSL.g:1349:1: ( () )
            // InternalEquationDSL.g:1350:2: ()
            {
             before(grammarAccess.getTypeInstanceResultAccess().getTypeInstanceResultAction_0()); 
            // InternalEquationDSL.g:1351:2: ()
            // InternalEquationDSL.g:1351:3: 
            {
            }

             after(grammarAccess.getTypeInstanceResultAccess().getTypeInstanceResultAction_0()); 

            }


            }

        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__TypeInstanceResult__Group__0__Impl"


    // $ANTLR start "rule__TypeInstanceResult__Group__1"
    // InternalEquationDSL.g:1359:1: rule__TypeInstanceResult__Group__1 : rule__TypeInstanceResult__Group__1__Impl rule__TypeInstanceResult__Group__2 ;
    public final void rule__TypeInstanceResult__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalEquationDSL.g:1363:1: ( rule__TypeInstanceResult__Group__1__Impl rule__TypeInstanceResult__Group__2 )
            // InternalEquationDSL.g:1364:2: rule__TypeInstanceResult__Group__1__Impl rule__TypeInstanceResult__Group__2
            {
            pushFollow(FOLLOW_6);
            rule__TypeInstanceResult__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__TypeInstanceResult__Group__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__TypeInstanceResult__Group__1"


    // $ANTLR start "rule__TypeInstanceResult__Group__1__Impl"
    // InternalEquationDSL.g:1371:1: rule__TypeInstanceResult__Group__1__Impl : ( 'Ref:' ) ;
    public final void rule__TypeInstanceResult__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalEquationDSL.g:1375:1: ( ( 'Ref:' ) )
            // InternalEquationDSL.g:1376:1: ( 'Ref:' )
            {
            // InternalEquationDSL.g:1376:1: ( 'Ref:' )
            // InternalEquationDSL.g:1377:2: 'Ref:'
            {
             before(grammarAccess.getTypeInstanceResultAccess().getRefKeyword_1()); 
            match(input,30,FOLLOW_2); 
             after(grammarAccess.getTypeInstanceResultAccess().getRefKeyword_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__TypeInstanceResult__Group__1__Impl"


    // $ANTLR start "rule__TypeInstanceResult__Group__2"
    // InternalEquationDSL.g:1386:1: rule__TypeInstanceResult__Group__2 : rule__TypeInstanceResult__Group__2__Impl ;
    public final void rule__TypeInstanceResult__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalEquationDSL.g:1390:1: ( rule__TypeInstanceResult__Group__2__Impl )
            // InternalEquationDSL.g:1391:2: rule__TypeInstanceResult__Group__2__Impl
            {
            pushFollow(FOLLOW_2);
            rule__TypeInstanceResult__Group__2__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__TypeInstanceResult__Group__2"


    // $ANTLR start "rule__TypeInstanceResult__Group__2__Impl"
    // InternalEquationDSL.g:1397:1: rule__TypeInstanceResult__Group__2__Impl : ( ( rule__TypeInstanceResult__ReferenceAssignment_2 ) ) ;
    public final void rule__TypeInstanceResult__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalEquationDSL.g:1401:1: ( ( ( rule__TypeInstanceResult__ReferenceAssignment_2 ) ) )
            // InternalEquationDSL.g:1402:1: ( ( rule__TypeInstanceResult__ReferenceAssignment_2 ) )
            {
            // InternalEquationDSL.g:1402:1: ( ( rule__TypeInstanceResult__ReferenceAssignment_2 ) )
            // InternalEquationDSL.g:1403:2: ( rule__TypeInstanceResult__ReferenceAssignment_2 )
            {
             before(grammarAccess.getTypeInstanceResultAccess().getReferenceAssignment_2()); 
            // InternalEquationDSL.g:1404:2: ( rule__TypeInstanceResult__ReferenceAssignment_2 )
            // InternalEquationDSL.g:1404:3: rule__TypeInstanceResult__ReferenceAssignment_2
            {
            pushFollow(FOLLOW_2);
            rule__TypeInstanceResult__ReferenceAssignment_2();

            state._fsp--;


            }

             after(grammarAccess.getTypeInstanceResultAccess().getReferenceAssignment_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__TypeInstanceResult__Group__2__Impl"


    // $ANTLR start "rule__EquationIntermediateResult__Group__0"
    // InternalEquationDSL.g:1413:1: rule__EquationIntermediateResult__Group__0 : rule__EquationIntermediateResult__Group__0__Impl rule__EquationIntermediateResult__Group__1 ;
    public final void rule__EquationIntermediateResult__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalEquationDSL.g:1417:1: ( rule__EquationIntermediateResult__Group__0__Impl rule__EquationIntermediateResult__Group__1 )
            // InternalEquationDSL.g:1418:2: rule__EquationIntermediateResult__Group__0__Impl rule__EquationIntermediateResult__Group__1
            {
            pushFollow(FOLLOW_3);
            rule__EquationIntermediateResult__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__EquationIntermediateResult__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__EquationIntermediateResult__Group__0"


    // $ANTLR start "rule__EquationIntermediateResult__Group__0__Impl"
    // InternalEquationDSL.g:1425:1: rule__EquationIntermediateResult__Group__0__Impl : ( () ) ;
    public final void rule__EquationIntermediateResult__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalEquationDSL.g:1429:1: ( ( () ) )
            // InternalEquationDSL.g:1430:1: ( () )
            {
            // InternalEquationDSL.g:1430:1: ( () )
            // InternalEquationDSL.g:1431:2: ()
            {
             before(grammarAccess.getEquationIntermediateResultAccess().getEquationIntermediateResultAction_0()); 
            // InternalEquationDSL.g:1432:2: ()
            // InternalEquationDSL.g:1432:3: 
            {
            }

             after(grammarAccess.getEquationIntermediateResultAccess().getEquationIntermediateResultAction_0()); 

            }


            }

        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__EquationIntermediateResult__Group__0__Impl"


    // $ANTLR start "rule__EquationIntermediateResult__Group__1"
    // InternalEquationDSL.g:1440:1: rule__EquationIntermediateResult__Group__1 : rule__EquationIntermediateResult__Group__1__Impl rule__EquationIntermediateResult__Group__2 ;
    public final void rule__EquationIntermediateResult__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalEquationDSL.g:1444:1: ( rule__EquationIntermediateResult__Group__1__Impl rule__EquationIntermediateResult__Group__2 )
            // InternalEquationDSL.g:1445:2: rule__EquationIntermediateResult__Group__1__Impl rule__EquationIntermediateResult__Group__2
            {
            pushFollow(FOLLOW_6);
            rule__EquationIntermediateResult__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__EquationIntermediateResult__Group__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__EquationIntermediateResult__Group__1"


    // $ANTLR start "rule__EquationIntermediateResult__Group__1__Impl"
    // InternalEquationDSL.g:1452:1: rule__EquationIntermediateResult__Group__1__Impl : ( 'Calc:' ) ;
    public final void rule__EquationIntermediateResult__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalEquationDSL.g:1456:1: ( ( 'Calc:' ) )
            // InternalEquationDSL.g:1457:1: ( 'Calc:' )
            {
            // InternalEquationDSL.g:1457:1: ( 'Calc:' )
            // InternalEquationDSL.g:1458:2: 'Calc:'
            {
             before(grammarAccess.getEquationIntermediateResultAccess().getCalcKeyword_1()); 
            match(input,31,FOLLOW_2); 
             after(grammarAccess.getEquationIntermediateResultAccess().getCalcKeyword_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__EquationIntermediateResult__Group__1__Impl"


    // $ANTLR start "rule__EquationIntermediateResult__Group__2"
    // InternalEquationDSL.g:1467:1: rule__EquationIntermediateResult__Group__2 : rule__EquationIntermediateResult__Group__2__Impl ;
    public final void rule__EquationIntermediateResult__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalEquationDSL.g:1471:1: ( rule__EquationIntermediateResult__Group__2__Impl )
            // InternalEquationDSL.g:1472:2: rule__EquationIntermediateResult__Group__2__Impl
            {
            pushFollow(FOLLOW_2);
            rule__EquationIntermediateResult__Group__2__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__EquationIntermediateResult__Group__2"


    // $ANTLR start "rule__EquationIntermediateResult__Group__2__Impl"
    // InternalEquationDSL.g:1478:1: rule__EquationIntermediateResult__Group__2__Impl : ( ( rule__EquationIntermediateResult__NameAssignment_2 ) ) ;
    public final void rule__EquationIntermediateResult__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalEquationDSL.g:1482:1: ( ( ( rule__EquationIntermediateResult__NameAssignment_2 ) ) )
            // InternalEquationDSL.g:1483:1: ( ( rule__EquationIntermediateResult__NameAssignment_2 ) )
            {
            // InternalEquationDSL.g:1483:1: ( ( rule__EquationIntermediateResult__NameAssignment_2 ) )
            // InternalEquationDSL.g:1484:2: ( rule__EquationIntermediateResult__NameAssignment_2 )
            {
             before(grammarAccess.getEquationIntermediateResultAccess().getNameAssignment_2()); 
            // InternalEquationDSL.g:1485:2: ( rule__EquationIntermediateResult__NameAssignment_2 )
            // InternalEquationDSL.g:1485:3: rule__EquationIntermediateResult__NameAssignment_2
            {
            pushFollow(FOLLOW_2);
            rule__EquationIntermediateResult__NameAssignment_2();

            state._fsp--;


            }

             after(grammarAccess.getEquationIntermediateResultAccess().getNameAssignment_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__EquationIntermediateResult__Group__2__Impl"


    // $ANTLR start "rule__AdditionAndSubtraction__Group__0"
    // InternalEquationDSL.g:1494:1: rule__AdditionAndSubtraction__Group__0 : rule__AdditionAndSubtraction__Group__0__Impl rule__AdditionAndSubtraction__Group__1 ;
    public final void rule__AdditionAndSubtraction__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalEquationDSL.g:1498:1: ( rule__AdditionAndSubtraction__Group__0__Impl rule__AdditionAndSubtraction__Group__1 )
            // InternalEquationDSL.g:1499:2: rule__AdditionAndSubtraction__Group__0__Impl rule__AdditionAndSubtraction__Group__1
            {
            pushFollow(FOLLOW_11);
            rule__AdditionAndSubtraction__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__AdditionAndSubtraction__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__AdditionAndSubtraction__Group__0"


    // $ANTLR start "rule__AdditionAndSubtraction__Group__0__Impl"
    // InternalEquationDSL.g:1506:1: rule__AdditionAndSubtraction__Group__0__Impl : ( ruleMultiplicationAndDivision ) ;
    public final void rule__AdditionAndSubtraction__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalEquationDSL.g:1510:1: ( ( ruleMultiplicationAndDivision ) )
            // InternalEquationDSL.g:1511:1: ( ruleMultiplicationAndDivision )
            {
            // InternalEquationDSL.g:1511:1: ( ruleMultiplicationAndDivision )
            // InternalEquationDSL.g:1512:2: ruleMultiplicationAndDivision
            {
             before(grammarAccess.getAdditionAndSubtractionAccess().getMultiplicationAndDivisionParserRuleCall_0()); 
            pushFollow(FOLLOW_2);
            ruleMultiplicationAndDivision();

            state._fsp--;

             after(grammarAccess.getAdditionAndSubtractionAccess().getMultiplicationAndDivisionParserRuleCall_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__AdditionAndSubtraction__Group__0__Impl"


    // $ANTLR start "rule__AdditionAndSubtraction__Group__1"
    // InternalEquationDSL.g:1521:1: rule__AdditionAndSubtraction__Group__1 : rule__AdditionAndSubtraction__Group__1__Impl ;
    public final void rule__AdditionAndSubtraction__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalEquationDSL.g:1525:1: ( rule__AdditionAndSubtraction__Group__1__Impl )
            // InternalEquationDSL.g:1526:2: rule__AdditionAndSubtraction__Group__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__AdditionAndSubtraction__Group__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__AdditionAndSubtraction__Group__1"


    // $ANTLR start "rule__AdditionAndSubtraction__Group__1__Impl"
    // InternalEquationDSL.g:1532:1: rule__AdditionAndSubtraction__Group__1__Impl : ( ( rule__AdditionAndSubtraction__Group_1__0 )* ) ;
    public final void rule__AdditionAndSubtraction__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalEquationDSL.g:1536:1: ( ( ( rule__AdditionAndSubtraction__Group_1__0 )* ) )
            // InternalEquationDSL.g:1537:1: ( ( rule__AdditionAndSubtraction__Group_1__0 )* )
            {
            // InternalEquationDSL.g:1537:1: ( ( rule__AdditionAndSubtraction__Group_1__0 )* )
            // InternalEquationDSL.g:1538:2: ( rule__AdditionAndSubtraction__Group_1__0 )*
            {
             before(grammarAccess.getAdditionAndSubtractionAccess().getGroup_1()); 
            // InternalEquationDSL.g:1539:2: ( rule__AdditionAndSubtraction__Group_1__0 )*
            loop10:
            do {
                int alt10=2;
                int LA10_0 = input.LA(1);

                if ( ((LA10_0>=11 && LA10_0<=12)) ) {
                    alt10=1;
                }


                switch (alt10) {
            	case 1 :
            	    // InternalEquationDSL.g:1539:3: rule__AdditionAndSubtraction__Group_1__0
            	    {
            	    pushFollow(FOLLOW_12);
            	    rule__AdditionAndSubtraction__Group_1__0();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop10;
                }
            } while (true);

             after(grammarAccess.getAdditionAndSubtractionAccess().getGroup_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__AdditionAndSubtraction__Group__1__Impl"


    // $ANTLR start "rule__AdditionAndSubtraction__Group_1__0"
    // InternalEquationDSL.g:1548:1: rule__AdditionAndSubtraction__Group_1__0 : rule__AdditionAndSubtraction__Group_1__0__Impl rule__AdditionAndSubtraction__Group_1__1 ;
    public final void rule__AdditionAndSubtraction__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalEquationDSL.g:1552:1: ( rule__AdditionAndSubtraction__Group_1__0__Impl rule__AdditionAndSubtraction__Group_1__1 )
            // InternalEquationDSL.g:1553:2: rule__AdditionAndSubtraction__Group_1__0__Impl rule__AdditionAndSubtraction__Group_1__1
            {
            pushFollow(FOLLOW_11);
            rule__AdditionAndSubtraction__Group_1__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__AdditionAndSubtraction__Group_1__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__AdditionAndSubtraction__Group_1__0"


    // $ANTLR start "rule__AdditionAndSubtraction__Group_1__0__Impl"
    // InternalEquationDSL.g:1560:1: rule__AdditionAndSubtraction__Group_1__0__Impl : ( () ) ;
    public final void rule__AdditionAndSubtraction__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalEquationDSL.g:1564:1: ( ( () ) )
            // InternalEquationDSL.g:1565:1: ( () )
            {
            // InternalEquationDSL.g:1565:1: ( () )
            // InternalEquationDSL.g:1566:2: ()
            {
             before(grammarAccess.getAdditionAndSubtractionAccess().getAdditionAndSubtractionLeftAction_1_0()); 
            // InternalEquationDSL.g:1567:2: ()
            // InternalEquationDSL.g:1567:3: 
            {
            }

             after(grammarAccess.getAdditionAndSubtractionAccess().getAdditionAndSubtractionLeftAction_1_0()); 

            }


            }

        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__AdditionAndSubtraction__Group_1__0__Impl"


    // $ANTLR start "rule__AdditionAndSubtraction__Group_1__1"
    // InternalEquationDSL.g:1575:1: rule__AdditionAndSubtraction__Group_1__1 : rule__AdditionAndSubtraction__Group_1__1__Impl rule__AdditionAndSubtraction__Group_1__2 ;
    public final void rule__AdditionAndSubtraction__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalEquationDSL.g:1579:1: ( rule__AdditionAndSubtraction__Group_1__1__Impl rule__AdditionAndSubtraction__Group_1__2 )
            // InternalEquationDSL.g:1580:2: rule__AdditionAndSubtraction__Group_1__1__Impl rule__AdditionAndSubtraction__Group_1__2
            {
            pushFollow(FOLLOW_9);
            rule__AdditionAndSubtraction__Group_1__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__AdditionAndSubtraction__Group_1__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__AdditionAndSubtraction__Group_1__1"


    // $ANTLR start "rule__AdditionAndSubtraction__Group_1__1__Impl"
    // InternalEquationDSL.g:1587:1: rule__AdditionAndSubtraction__Group_1__1__Impl : ( ( rule__AdditionAndSubtraction__OperatorAssignment_1_1 ) ) ;
    public final void rule__AdditionAndSubtraction__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalEquationDSL.g:1591:1: ( ( ( rule__AdditionAndSubtraction__OperatorAssignment_1_1 ) ) )
            // InternalEquationDSL.g:1592:1: ( ( rule__AdditionAndSubtraction__OperatorAssignment_1_1 ) )
            {
            // InternalEquationDSL.g:1592:1: ( ( rule__AdditionAndSubtraction__OperatorAssignment_1_1 ) )
            // InternalEquationDSL.g:1593:2: ( rule__AdditionAndSubtraction__OperatorAssignment_1_1 )
            {
             before(grammarAccess.getAdditionAndSubtractionAccess().getOperatorAssignment_1_1()); 
            // InternalEquationDSL.g:1594:2: ( rule__AdditionAndSubtraction__OperatorAssignment_1_1 )
            // InternalEquationDSL.g:1594:3: rule__AdditionAndSubtraction__OperatorAssignment_1_1
            {
            pushFollow(FOLLOW_2);
            rule__AdditionAndSubtraction__OperatorAssignment_1_1();

            state._fsp--;


            }

             after(grammarAccess.getAdditionAndSubtractionAccess().getOperatorAssignment_1_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__AdditionAndSubtraction__Group_1__1__Impl"


    // $ANTLR start "rule__AdditionAndSubtraction__Group_1__2"
    // InternalEquationDSL.g:1602:1: rule__AdditionAndSubtraction__Group_1__2 : rule__AdditionAndSubtraction__Group_1__2__Impl ;
    public final void rule__AdditionAndSubtraction__Group_1__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalEquationDSL.g:1606:1: ( rule__AdditionAndSubtraction__Group_1__2__Impl )
            // InternalEquationDSL.g:1607:2: rule__AdditionAndSubtraction__Group_1__2__Impl
            {
            pushFollow(FOLLOW_2);
            rule__AdditionAndSubtraction__Group_1__2__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__AdditionAndSubtraction__Group_1__2"


    // $ANTLR start "rule__AdditionAndSubtraction__Group_1__2__Impl"
    // InternalEquationDSL.g:1613:1: rule__AdditionAndSubtraction__Group_1__2__Impl : ( ( rule__AdditionAndSubtraction__RightAssignment_1_2 ) ) ;
    public final void rule__AdditionAndSubtraction__Group_1__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalEquationDSL.g:1617:1: ( ( ( rule__AdditionAndSubtraction__RightAssignment_1_2 ) ) )
            // InternalEquationDSL.g:1618:1: ( ( rule__AdditionAndSubtraction__RightAssignment_1_2 ) )
            {
            // InternalEquationDSL.g:1618:1: ( ( rule__AdditionAndSubtraction__RightAssignment_1_2 ) )
            // InternalEquationDSL.g:1619:2: ( rule__AdditionAndSubtraction__RightAssignment_1_2 )
            {
             before(grammarAccess.getAdditionAndSubtractionAccess().getRightAssignment_1_2()); 
            // InternalEquationDSL.g:1620:2: ( rule__AdditionAndSubtraction__RightAssignment_1_2 )
            // InternalEquationDSL.g:1620:3: rule__AdditionAndSubtraction__RightAssignment_1_2
            {
            pushFollow(FOLLOW_2);
            rule__AdditionAndSubtraction__RightAssignment_1_2();

            state._fsp--;


            }

             after(grammarAccess.getAdditionAndSubtractionAccess().getRightAssignment_1_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__AdditionAndSubtraction__Group_1__2__Impl"


    // $ANTLR start "rule__MultiplicationAndDivision__Group__0"
    // InternalEquationDSL.g:1629:1: rule__MultiplicationAndDivision__Group__0 : rule__MultiplicationAndDivision__Group__0__Impl rule__MultiplicationAndDivision__Group__1 ;
    public final void rule__MultiplicationAndDivision__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalEquationDSL.g:1633:1: ( rule__MultiplicationAndDivision__Group__0__Impl rule__MultiplicationAndDivision__Group__1 )
            // InternalEquationDSL.g:1634:2: rule__MultiplicationAndDivision__Group__0__Impl rule__MultiplicationAndDivision__Group__1
            {
            pushFollow(FOLLOW_13);
            rule__MultiplicationAndDivision__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__MultiplicationAndDivision__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__MultiplicationAndDivision__Group__0"


    // $ANTLR start "rule__MultiplicationAndDivision__Group__0__Impl"
    // InternalEquationDSL.g:1641:1: rule__MultiplicationAndDivision__Group__0__Impl : ( rulePowerFunction ) ;
    public final void rule__MultiplicationAndDivision__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalEquationDSL.g:1645:1: ( ( rulePowerFunction ) )
            // InternalEquationDSL.g:1646:1: ( rulePowerFunction )
            {
            // InternalEquationDSL.g:1646:1: ( rulePowerFunction )
            // InternalEquationDSL.g:1647:2: rulePowerFunction
            {
             before(grammarAccess.getMultiplicationAndDivisionAccess().getPowerFunctionParserRuleCall_0()); 
            pushFollow(FOLLOW_2);
            rulePowerFunction();

            state._fsp--;

             after(grammarAccess.getMultiplicationAndDivisionAccess().getPowerFunctionParserRuleCall_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__MultiplicationAndDivision__Group__0__Impl"


    // $ANTLR start "rule__MultiplicationAndDivision__Group__1"
    // InternalEquationDSL.g:1656:1: rule__MultiplicationAndDivision__Group__1 : rule__MultiplicationAndDivision__Group__1__Impl ;
    public final void rule__MultiplicationAndDivision__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalEquationDSL.g:1660:1: ( rule__MultiplicationAndDivision__Group__1__Impl )
            // InternalEquationDSL.g:1661:2: rule__MultiplicationAndDivision__Group__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__MultiplicationAndDivision__Group__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__MultiplicationAndDivision__Group__1"


    // $ANTLR start "rule__MultiplicationAndDivision__Group__1__Impl"
    // InternalEquationDSL.g:1667:1: rule__MultiplicationAndDivision__Group__1__Impl : ( ( rule__MultiplicationAndDivision__Group_1__0 )* ) ;
    public final void rule__MultiplicationAndDivision__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalEquationDSL.g:1671:1: ( ( ( rule__MultiplicationAndDivision__Group_1__0 )* ) )
            // InternalEquationDSL.g:1672:1: ( ( rule__MultiplicationAndDivision__Group_1__0 )* )
            {
            // InternalEquationDSL.g:1672:1: ( ( rule__MultiplicationAndDivision__Group_1__0 )* )
            // InternalEquationDSL.g:1673:2: ( rule__MultiplicationAndDivision__Group_1__0 )*
            {
             before(grammarAccess.getMultiplicationAndDivisionAccess().getGroup_1()); 
            // InternalEquationDSL.g:1674:2: ( rule__MultiplicationAndDivision__Group_1__0 )*
            loop11:
            do {
                int alt11=2;
                int LA11_0 = input.LA(1);

                if ( ((LA11_0>=13 && LA11_0<=14)) ) {
                    alt11=1;
                }


                switch (alt11) {
            	case 1 :
            	    // InternalEquationDSL.g:1674:3: rule__MultiplicationAndDivision__Group_1__0
            	    {
            	    pushFollow(FOLLOW_14);
            	    rule__MultiplicationAndDivision__Group_1__0();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop11;
                }
            } while (true);

             after(grammarAccess.getMultiplicationAndDivisionAccess().getGroup_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__MultiplicationAndDivision__Group__1__Impl"


    // $ANTLR start "rule__MultiplicationAndDivision__Group_1__0"
    // InternalEquationDSL.g:1683:1: rule__MultiplicationAndDivision__Group_1__0 : rule__MultiplicationAndDivision__Group_1__0__Impl rule__MultiplicationAndDivision__Group_1__1 ;
    public final void rule__MultiplicationAndDivision__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalEquationDSL.g:1687:1: ( rule__MultiplicationAndDivision__Group_1__0__Impl rule__MultiplicationAndDivision__Group_1__1 )
            // InternalEquationDSL.g:1688:2: rule__MultiplicationAndDivision__Group_1__0__Impl rule__MultiplicationAndDivision__Group_1__1
            {
            pushFollow(FOLLOW_13);
            rule__MultiplicationAndDivision__Group_1__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__MultiplicationAndDivision__Group_1__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__MultiplicationAndDivision__Group_1__0"


    // $ANTLR start "rule__MultiplicationAndDivision__Group_1__0__Impl"
    // InternalEquationDSL.g:1695:1: rule__MultiplicationAndDivision__Group_1__0__Impl : ( () ) ;
    public final void rule__MultiplicationAndDivision__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalEquationDSL.g:1699:1: ( ( () ) )
            // InternalEquationDSL.g:1700:1: ( () )
            {
            // InternalEquationDSL.g:1700:1: ( () )
            // InternalEquationDSL.g:1701:2: ()
            {
             before(grammarAccess.getMultiplicationAndDivisionAccess().getMultiplicationAndDivisionLeftAction_1_0()); 
            // InternalEquationDSL.g:1702:2: ()
            // InternalEquationDSL.g:1702:3: 
            {
            }

             after(grammarAccess.getMultiplicationAndDivisionAccess().getMultiplicationAndDivisionLeftAction_1_0()); 

            }


            }

        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__MultiplicationAndDivision__Group_1__0__Impl"


    // $ANTLR start "rule__MultiplicationAndDivision__Group_1__1"
    // InternalEquationDSL.g:1710:1: rule__MultiplicationAndDivision__Group_1__1 : rule__MultiplicationAndDivision__Group_1__1__Impl rule__MultiplicationAndDivision__Group_1__2 ;
    public final void rule__MultiplicationAndDivision__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalEquationDSL.g:1714:1: ( rule__MultiplicationAndDivision__Group_1__1__Impl rule__MultiplicationAndDivision__Group_1__2 )
            // InternalEquationDSL.g:1715:2: rule__MultiplicationAndDivision__Group_1__1__Impl rule__MultiplicationAndDivision__Group_1__2
            {
            pushFollow(FOLLOW_9);
            rule__MultiplicationAndDivision__Group_1__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__MultiplicationAndDivision__Group_1__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__MultiplicationAndDivision__Group_1__1"


    // $ANTLR start "rule__MultiplicationAndDivision__Group_1__1__Impl"
    // InternalEquationDSL.g:1722:1: rule__MultiplicationAndDivision__Group_1__1__Impl : ( ( rule__MultiplicationAndDivision__OperatorAssignment_1_1 ) ) ;
    public final void rule__MultiplicationAndDivision__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalEquationDSL.g:1726:1: ( ( ( rule__MultiplicationAndDivision__OperatorAssignment_1_1 ) ) )
            // InternalEquationDSL.g:1727:1: ( ( rule__MultiplicationAndDivision__OperatorAssignment_1_1 ) )
            {
            // InternalEquationDSL.g:1727:1: ( ( rule__MultiplicationAndDivision__OperatorAssignment_1_1 ) )
            // InternalEquationDSL.g:1728:2: ( rule__MultiplicationAndDivision__OperatorAssignment_1_1 )
            {
             before(grammarAccess.getMultiplicationAndDivisionAccess().getOperatorAssignment_1_1()); 
            // InternalEquationDSL.g:1729:2: ( rule__MultiplicationAndDivision__OperatorAssignment_1_1 )
            // InternalEquationDSL.g:1729:3: rule__MultiplicationAndDivision__OperatorAssignment_1_1
            {
            pushFollow(FOLLOW_2);
            rule__MultiplicationAndDivision__OperatorAssignment_1_1();

            state._fsp--;


            }

             after(grammarAccess.getMultiplicationAndDivisionAccess().getOperatorAssignment_1_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__MultiplicationAndDivision__Group_1__1__Impl"


    // $ANTLR start "rule__MultiplicationAndDivision__Group_1__2"
    // InternalEquationDSL.g:1737:1: rule__MultiplicationAndDivision__Group_1__2 : rule__MultiplicationAndDivision__Group_1__2__Impl ;
    public final void rule__MultiplicationAndDivision__Group_1__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalEquationDSL.g:1741:1: ( rule__MultiplicationAndDivision__Group_1__2__Impl )
            // InternalEquationDSL.g:1742:2: rule__MultiplicationAndDivision__Group_1__2__Impl
            {
            pushFollow(FOLLOW_2);
            rule__MultiplicationAndDivision__Group_1__2__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__MultiplicationAndDivision__Group_1__2"


    // $ANTLR start "rule__MultiplicationAndDivision__Group_1__2__Impl"
    // InternalEquationDSL.g:1748:1: rule__MultiplicationAndDivision__Group_1__2__Impl : ( ( rule__MultiplicationAndDivision__RightAssignment_1_2 ) ) ;
    public final void rule__MultiplicationAndDivision__Group_1__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalEquationDSL.g:1752:1: ( ( ( rule__MultiplicationAndDivision__RightAssignment_1_2 ) ) )
            // InternalEquationDSL.g:1753:1: ( ( rule__MultiplicationAndDivision__RightAssignment_1_2 ) )
            {
            // InternalEquationDSL.g:1753:1: ( ( rule__MultiplicationAndDivision__RightAssignment_1_2 ) )
            // InternalEquationDSL.g:1754:2: ( rule__MultiplicationAndDivision__RightAssignment_1_2 )
            {
             before(grammarAccess.getMultiplicationAndDivisionAccess().getRightAssignment_1_2()); 
            // InternalEquationDSL.g:1755:2: ( rule__MultiplicationAndDivision__RightAssignment_1_2 )
            // InternalEquationDSL.g:1755:3: rule__MultiplicationAndDivision__RightAssignment_1_2
            {
            pushFollow(FOLLOW_2);
            rule__MultiplicationAndDivision__RightAssignment_1_2();

            state._fsp--;


            }

             after(grammarAccess.getMultiplicationAndDivisionAccess().getRightAssignment_1_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__MultiplicationAndDivision__Group_1__2__Impl"


    // $ANTLR start "rule__PowerFunction__Group__0"
    // InternalEquationDSL.g:1764:1: rule__PowerFunction__Group__0 : rule__PowerFunction__Group__0__Impl rule__PowerFunction__Group__1 ;
    public final void rule__PowerFunction__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalEquationDSL.g:1768:1: ( rule__PowerFunction__Group__0__Impl rule__PowerFunction__Group__1 )
            // InternalEquationDSL.g:1769:2: rule__PowerFunction__Group__0__Impl rule__PowerFunction__Group__1
            {
            pushFollow(FOLLOW_15);
            rule__PowerFunction__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__PowerFunction__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__PowerFunction__Group__0"


    // $ANTLR start "rule__PowerFunction__Group__0__Impl"
    // InternalEquationDSL.g:1776:1: rule__PowerFunction__Group__0__Impl : ( ruleAExpression ) ;
    public final void rule__PowerFunction__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalEquationDSL.g:1780:1: ( ( ruleAExpression ) )
            // InternalEquationDSL.g:1781:1: ( ruleAExpression )
            {
            // InternalEquationDSL.g:1781:1: ( ruleAExpression )
            // InternalEquationDSL.g:1782:2: ruleAExpression
            {
             before(grammarAccess.getPowerFunctionAccess().getAExpressionParserRuleCall_0()); 
            pushFollow(FOLLOW_2);
            ruleAExpression();

            state._fsp--;

             after(grammarAccess.getPowerFunctionAccess().getAExpressionParserRuleCall_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__PowerFunction__Group__0__Impl"


    // $ANTLR start "rule__PowerFunction__Group__1"
    // InternalEquationDSL.g:1791:1: rule__PowerFunction__Group__1 : rule__PowerFunction__Group__1__Impl ;
    public final void rule__PowerFunction__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalEquationDSL.g:1795:1: ( rule__PowerFunction__Group__1__Impl )
            // InternalEquationDSL.g:1796:2: rule__PowerFunction__Group__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__PowerFunction__Group__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__PowerFunction__Group__1"


    // $ANTLR start "rule__PowerFunction__Group__1__Impl"
    // InternalEquationDSL.g:1802:1: rule__PowerFunction__Group__1__Impl : ( ( rule__PowerFunction__Group_1__0 )* ) ;
    public final void rule__PowerFunction__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalEquationDSL.g:1806:1: ( ( ( rule__PowerFunction__Group_1__0 )* ) )
            // InternalEquationDSL.g:1807:1: ( ( rule__PowerFunction__Group_1__0 )* )
            {
            // InternalEquationDSL.g:1807:1: ( ( rule__PowerFunction__Group_1__0 )* )
            // InternalEquationDSL.g:1808:2: ( rule__PowerFunction__Group_1__0 )*
            {
             before(grammarAccess.getPowerFunctionAccess().getGroup_1()); 
            // InternalEquationDSL.g:1809:2: ( rule__PowerFunction__Group_1__0 )*
            loop12:
            do {
                int alt12=2;
                int LA12_0 = input.LA(1);

                if ( (LA12_0==15) ) {
                    alt12=1;
                }


                switch (alt12) {
            	case 1 :
            	    // InternalEquationDSL.g:1809:3: rule__PowerFunction__Group_1__0
            	    {
            	    pushFollow(FOLLOW_16);
            	    rule__PowerFunction__Group_1__0();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop12;
                }
            } while (true);

             after(grammarAccess.getPowerFunctionAccess().getGroup_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__PowerFunction__Group__1__Impl"


    // $ANTLR start "rule__PowerFunction__Group_1__0"
    // InternalEquationDSL.g:1818:1: rule__PowerFunction__Group_1__0 : rule__PowerFunction__Group_1__0__Impl rule__PowerFunction__Group_1__1 ;
    public final void rule__PowerFunction__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalEquationDSL.g:1822:1: ( rule__PowerFunction__Group_1__0__Impl rule__PowerFunction__Group_1__1 )
            // InternalEquationDSL.g:1823:2: rule__PowerFunction__Group_1__0__Impl rule__PowerFunction__Group_1__1
            {
            pushFollow(FOLLOW_15);
            rule__PowerFunction__Group_1__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__PowerFunction__Group_1__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__PowerFunction__Group_1__0"


    // $ANTLR start "rule__PowerFunction__Group_1__0__Impl"
    // InternalEquationDSL.g:1830:1: rule__PowerFunction__Group_1__0__Impl : ( () ) ;
    public final void rule__PowerFunction__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalEquationDSL.g:1834:1: ( ( () ) )
            // InternalEquationDSL.g:1835:1: ( () )
            {
            // InternalEquationDSL.g:1835:1: ( () )
            // InternalEquationDSL.g:1836:2: ()
            {
             before(grammarAccess.getPowerFunctionAccess().getPowerFunctionLeftAction_1_0()); 
            // InternalEquationDSL.g:1837:2: ()
            // InternalEquationDSL.g:1837:3: 
            {
            }

             after(grammarAccess.getPowerFunctionAccess().getPowerFunctionLeftAction_1_0()); 

            }


            }

        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__PowerFunction__Group_1__0__Impl"


    // $ANTLR start "rule__PowerFunction__Group_1__1"
    // InternalEquationDSL.g:1845:1: rule__PowerFunction__Group_1__1 : rule__PowerFunction__Group_1__1__Impl rule__PowerFunction__Group_1__2 ;
    public final void rule__PowerFunction__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalEquationDSL.g:1849:1: ( rule__PowerFunction__Group_1__1__Impl rule__PowerFunction__Group_1__2 )
            // InternalEquationDSL.g:1850:2: rule__PowerFunction__Group_1__1__Impl rule__PowerFunction__Group_1__2
            {
            pushFollow(FOLLOW_9);
            rule__PowerFunction__Group_1__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__PowerFunction__Group_1__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__PowerFunction__Group_1__1"


    // $ANTLR start "rule__PowerFunction__Group_1__1__Impl"
    // InternalEquationDSL.g:1857:1: rule__PowerFunction__Group_1__1__Impl : ( ( rule__PowerFunction__OperatorAssignment_1_1 ) ) ;
    public final void rule__PowerFunction__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalEquationDSL.g:1861:1: ( ( ( rule__PowerFunction__OperatorAssignment_1_1 ) ) )
            // InternalEquationDSL.g:1862:1: ( ( rule__PowerFunction__OperatorAssignment_1_1 ) )
            {
            // InternalEquationDSL.g:1862:1: ( ( rule__PowerFunction__OperatorAssignment_1_1 ) )
            // InternalEquationDSL.g:1863:2: ( rule__PowerFunction__OperatorAssignment_1_1 )
            {
             before(grammarAccess.getPowerFunctionAccess().getOperatorAssignment_1_1()); 
            // InternalEquationDSL.g:1864:2: ( rule__PowerFunction__OperatorAssignment_1_1 )
            // InternalEquationDSL.g:1864:3: rule__PowerFunction__OperatorAssignment_1_1
            {
            pushFollow(FOLLOW_2);
            rule__PowerFunction__OperatorAssignment_1_1();

            state._fsp--;


            }

             after(grammarAccess.getPowerFunctionAccess().getOperatorAssignment_1_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__PowerFunction__Group_1__1__Impl"


    // $ANTLR start "rule__PowerFunction__Group_1__2"
    // InternalEquationDSL.g:1872:1: rule__PowerFunction__Group_1__2 : rule__PowerFunction__Group_1__2__Impl ;
    public final void rule__PowerFunction__Group_1__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalEquationDSL.g:1876:1: ( rule__PowerFunction__Group_1__2__Impl )
            // InternalEquationDSL.g:1877:2: rule__PowerFunction__Group_1__2__Impl
            {
            pushFollow(FOLLOW_2);
            rule__PowerFunction__Group_1__2__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__PowerFunction__Group_1__2"


    // $ANTLR start "rule__PowerFunction__Group_1__2__Impl"
    // InternalEquationDSL.g:1883:1: rule__PowerFunction__Group_1__2__Impl : ( ( rule__PowerFunction__RightAssignment_1_2 ) ) ;
    public final void rule__PowerFunction__Group_1__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalEquationDSL.g:1887:1: ( ( ( rule__PowerFunction__RightAssignment_1_2 ) ) )
            // InternalEquationDSL.g:1888:1: ( ( rule__PowerFunction__RightAssignment_1_2 ) )
            {
            // InternalEquationDSL.g:1888:1: ( ( rule__PowerFunction__RightAssignment_1_2 ) )
            // InternalEquationDSL.g:1889:2: ( rule__PowerFunction__RightAssignment_1_2 )
            {
             before(grammarAccess.getPowerFunctionAccess().getRightAssignment_1_2()); 
            // InternalEquationDSL.g:1890:2: ( rule__PowerFunction__RightAssignment_1_2 )
            // InternalEquationDSL.g:1890:3: rule__PowerFunction__RightAssignment_1_2
            {
            pushFollow(FOLLOW_2);
            rule__PowerFunction__RightAssignment_1_2();

            state._fsp--;


            }

             after(grammarAccess.getPowerFunctionAccess().getRightAssignment_1_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__PowerFunction__Group_1__2__Impl"


    // $ANTLR start "rule__Parenthesis__Group__0"
    // InternalEquationDSL.g:1899:1: rule__Parenthesis__Group__0 : rule__Parenthesis__Group__0__Impl rule__Parenthesis__Group__1 ;
    public final void rule__Parenthesis__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalEquationDSL.g:1903:1: ( rule__Parenthesis__Group__0__Impl rule__Parenthesis__Group__1 )
            // InternalEquationDSL.g:1904:2: rule__Parenthesis__Group__0__Impl rule__Parenthesis__Group__1
            {
            pushFollow(FOLLOW_17);
            rule__Parenthesis__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Parenthesis__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Parenthesis__Group__0"


    // $ANTLR start "rule__Parenthesis__Group__0__Impl"
    // InternalEquationDSL.g:1911:1: rule__Parenthesis__Group__0__Impl : ( () ) ;
    public final void rule__Parenthesis__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalEquationDSL.g:1915:1: ( ( () ) )
            // InternalEquationDSL.g:1916:1: ( () )
            {
            // InternalEquationDSL.g:1916:1: ( () )
            // InternalEquationDSL.g:1917:2: ()
            {
             before(grammarAccess.getParenthesisAccess().getParenthesisAction_0()); 
            // InternalEquationDSL.g:1918:2: ()
            // InternalEquationDSL.g:1918:3: 
            {
            }

             after(grammarAccess.getParenthesisAccess().getParenthesisAction_0()); 

            }


            }

        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Parenthesis__Group__0__Impl"


    // $ANTLR start "rule__Parenthesis__Group__1"
    // InternalEquationDSL.g:1926:1: rule__Parenthesis__Group__1 : rule__Parenthesis__Group__1__Impl rule__Parenthesis__Group__2 ;
    public final void rule__Parenthesis__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalEquationDSL.g:1930:1: ( rule__Parenthesis__Group__1__Impl rule__Parenthesis__Group__2 )
            // InternalEquationDSL.g:1931:2: rule__Parenthesis__Group__1__Impl rule__Parenthesis__Group__2
            {
            pushFollow(FOLLOW_17);
            rule__Parenthesis__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Parenthesis__Group__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Parenthesis__Group__1"


    // $ANTLR start "rule__Parenthesis__Group__1__Impl"
    // InternalEquationDSL.g:1938:1: rule__Parenthesis__Group__1__Impl : ( ( rule__Parenthesis__OperatorAssignment_1 )? ) ;
    public final void rule__Parenthesis__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalEquationDSL.g:1942:1: ( ( ( rule__Parenthesis__OperatorAssignment_1 )? ) )
            // InternalEquationDSL.g:1943:1: ( ( rule__Parenthesis__OperatorAssignment_1 )? )
            {
            // InternalEquationDSL.g:1943:1: ( ( rule__Parenthesis__OperatorAssignment_1 )? )
            // InternalEquationDSL.g:1944:2: ( rule__Parenthesis__OperatorAssignment_1 )?
            {
             before(grammarAccess.getParenthesisAccess().getOperatorAssignment_1()); 
            // InternalEquationDSL.g:1945:2: ( rule__Parenthesis__OperatorAssignment_1 )?
            int alt13=2;
            int LA13_0 = input.LA(1);

            if ( (LA13_0==12) ) {
                alt13=1;
            }
            switch (alt13) {
                case 1 :
                    // InternalEquationDSL.g:1945:3: rule__Parenthesis__OperatorAssignment_1
                    {
                    pushFollow(FOLLOW_2);
                    rule__Parenthesis__OperatorAssignment_1();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getParenthesisAccess().getOperatorAssignment_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Parenthesis__Group__1__Impl"


    // $ANTLR start "rule__Parenthesis__Group__2"
    // InternalEquationDSL.g:1953:1: rule__Parenthesis__Group__2 : rule__Parenthesis__Group__2__Impl rule__Parenthesis__Group__3 ;
    public final void rule__Parenthesis__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalEquationDSL.g:1957:1: ( rule__Parenthesis__Group__2__Impl rule__Parenthesis__Group__3 )
            // InternalEquationDSL.g:1958:2: rule__Parenthesis__Group__2__Impl rule__Parenthesis__Group__3
            {
            pushFollow(FOLLOW_9);
            rule__Parenthesis__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Parenthesis__Group__3();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Parenthesis__Group__2"


    // $ANTLR start "rule__Parenthesis__Group__2__Impl"
    // InternalEquationDSL.g:1965:1: rule__Parenthesis__Group__2__Impl : ( '(' ) ;
    public final void rule__Parenthesis__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalEquationDSL.g:1969:1: ( ( '(' ) )
            // InternalEquationDSL.g:1970:1: ( '(' )
            {
            // InternalEquationDSL.g:1970:1: ( '(' )
            // InternalEquationDSL.g:1971:2: '('
            {
             before(grammarAccess.getParenthesisAccess().getLeftParenthesisKeyword_2()); 
            match(input,32,FOLLOW_2); 
             after(grammarAccess.getParenthesisAccess().getLeftParenthesisKeyword_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Parenthesis__Group__2__Impl"


    // $ANTLR start "rule__Parenthesis__Group__3"
    // InternalEquationDSL.g:1980:1: rule__Parenthesis__Group__3 : rule__Parenthesis__Group__3__Impl rule__Parenthesis__Group__4 ;
    public final void rule__Parenthesis__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalEquationDSL.g:1984:1: ( rule__Parenthesis__Group__3__Impl rule__Parenthesis__Group__4 )
            // InternalEquationDSL.g:1985:2: rule__Parenthesis__Group__3__Impl rule__Parenthesis__Group__4
            {
            pushFollow(FOLLOW_18);
            rule__Parenthesis__Group__3__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Parenthesis__Group__4();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Parenthesis__Group__3"


    // $ANTLR start "rule__Parenthesis__Group__3__Impl"
    // InternalEquationDSL.g:1992:1: rule__Parenthesis__Group__3__Impl : ( ( rule__Parenthesis__RightAssignment_3 ) ) ;
    public final void rule__Parenthesis__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalEquationDSL.g:1996:1: ( ( ( rule__Parenthesis__RightAssignment_3 ) ) )
            // InternalEquationDSL.g:1997:1: ( ( rule__Parenthesis__RightAssignment_3 ) )
            {
            // InternalEquationDSL.g:1997:1: ( ( rule__Parenthesis__RightAssignment_3 ) )
            // InternalEquationDSL.g:1998:2: ( rule__Parenthesis__RightAssignment_3 )
            {
             before(grammarAccess.getParenthesisAccess().getRightAssignment_3()); 
            // InternalEquationDSL.g:1999:2: ( rule__Parenthesis__RightAssignment_3 )
            // InternalEquationDSL.g:1999:3: rule__Parenthesis__RightAssignment_3
            {
            pushFollow(FOLLOW_2);
            rule__Parenthesis__RightAssignment_3();

            state._fsp--;


            }

             after(grammarAccess.getParenthesisAccess().getRightAssignment_3()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Parenthesis__Group__3__Impl"


    // $ANTLR start "rule__Parenthesis__Group__4"
    // InternalEquationDSL.g:2007:1: rule__Parenthesis__Group__4 : rule__Parenthesis__Group__4__Impl ;
    public final void rule__Parenthesis__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalEquationDSL.g:2011:1: ( rule__Parenthesis__Group__4__Impl )
            // InternalEquationDSL.g:2012:2: rule__Parenthesis__Group__4__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Parenthesis__Group__4__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Parenthesis__Group__4"


    // $ANTLR start "rule__Parenthesis__Group__4__Impl"
    // InternalEquationDSL.g:2018:1: rule__Parenthesis__Group__4__Impl : ( ')' ) ;
    public final void rule__Parenthesis__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalEquationDSL.g:2022:1: ( ( ')' ) )
            // InternalEquationDSL.g:2023:1: ( ')' )
            {
            // InternalEquationDSL.g:2023:1: ( ')' )
            // InternalEquationDSL.g:2024:2: ')'
            {
             before(grammarAccess.getParenthesisAccess().getRightParenthesisKeyword_4()); 
            match(input,33,FOLLOW_2); 
             after(grammarAccess.getParenthesisAccess().getRightParenthesisKeyword_4()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Parenthesis__Group__4__Impl"


    // $ANTLR start "rule__Function__Group__0"
    // InternalEquationDSL.g:2034:1: rule__Function__Group__0 : rule__Function__Group__0__Impl rule__Function__Group__1 ;
    public final void rule__Function__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalEquationDSL.g:2038:1: ( rule__Function__Group__0__Impl rule__Function__Group__1 )
            // InternalEquationDSL.g:2039:2: rule__Function__Group__0__Impl rule__Function__Group__1
            {
            pushFollow(FOLLOW_19);
            rule__Function__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Function__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Function__Group__0"


    // $ANTLR start "rule__Function__Group__0__Impl"
    // InternalEquationDSL.g:2046:1: rule__Function__Group__0__Impl : ( () ) ;
    public final void rule__Function__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalEquationDSL.g:2050:1: ( ( () ) )
            // InternalEquationDSL.g:2051:1: ( () )
            {
            // InternalEquationDSL.g:2051:1: ( () )
            // InternalEquationDSL.g:2052:2: ()
            {
             before(grammarAccess.getFunctionAccess().getFunctionAction_0()); 
            // InternalEquationDSL.g:2053:2: ()
            // InternalEquationDSL.g:2053:3: 
            {
            }

             after(grammarAccess.getFunctionAccess().getFunctionAction_0()); 

            }


            }

        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Function__Group__0__Impl"


    // $ANTLR start "rule__Function__Group__1"
    // InternalEquationDSL.g:2061:1: rule__Function__Group__1 : rule__Function__Group__1__Impl rule__Function__Group__2 ;
    public final void rule__Function__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalEquationDSL.g:2065:1: ( rule__Function__Group__1__Impl rule__Function__Group__2 )
            // InternalEquationDSL.g:2066:2: rule__Function__Group__1__Impl rule__Function__Group__2
            {
            pushFollow(FOLLOW_20);
            rule__Function__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Function__Group__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Function__Group__1"


    // $ANTLR start "rule__Function__Group__1__Impl"
    // InternalEquationDSL.g:2073:1: rule__Function__Group__1__Impl : ( ( rule__Function__OperatorAssignment_1 ) ) ;
    public final void rule__Function__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalEquationDSL.g:2077:1: ( ( ( rule__Function__OperatorAssignment_1 ) ) )
            // InternalEquationDSL.g:2078:1: ( ( rule__Function__OperatorAssignment_1 ) )
            {
            // InternalEquationDSL.g:2078:1: ( ( rule__Function__OperatorAssignment_1 ) )
            // InternalEquationDSL.g:2079:2: ( rule__Function__OperatorAssignment_1 )
            {
             before(grammarAccess.getFunctionAccess().getOperatorAssignment_1()); 
            // InternalEquationDSL.g:2080:2: ( rule__Function__OperatorAssignment_1 )
            // InternalEquationDSL.g:2080:3: rule__Function__OperatorAssignment_1
            {
            pushFollow(FOLLOW_2);
            rule__Function__OperatorAssignment_1();

            state._fsp--;


            }

             after(grammarAccess.getFunctionAccess().getOperatorAssignment_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Function__Group__1__Impl"


    // $ANTLR start "rule__Function__Group__2"
    // InternalEquationDSL.g:2088:1: rule__Function__Group__2 : rule__Function__Group__2__Impl rule__Function__Group__3 ;
    public final void rule__Function__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalEquationDSL.g:2092:1: ( rule__Function__Group__2__Impl rule__Function__Group__3 )
            // InternalEquationDSL.g:2093:2: rule__Function__Group__2__Impl rule__Function__Group__3
            {
            pushFollow(FOLLOW_9);
            rule__Function__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Function__Group__3();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Function__Group__2"


    // $ANTLR start "rule__Function__Group__2__Impl"
    // InternalEquationDSL.g:2100:1: rule__Function__Group__2__Impl : ( '(' ) ;
    public final void rule__Function__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalEquationDSL.g:2104:1: ( ( '(' ) )
            // InternalEquationDSL.g:2105:1: ( '(' )
            {
            // InternalEquationDSL.g:2105:1: ( '(' )
            // InternalEquationDSL.g:2106:2: '('
            {
             before(grammarAccess.getFunctionAccess().getLeftParenthesisKeyword_2()); 
            match(input,32,FOLLOW_2); 
             after(grammarAccess.getFunctionAccess().getLeftParenthesisKeyword_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Function__Group__2__Impl"


    // $ANTLR start "rule__Function__Group__3"
    // InternalEquationDSL.g:2115:1: rule__Function__Group__3 : rule__Function__Group__3__Impl rule__Function__Group__4 ;
    public final void rule__Function__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalEquationDSL.g:2119:1: ( rule__Function__Group__3__Impl rule__Function__Group__4 )
            // InternalEquationDSL.g:2120:2: rule__Function__Group__3__Impl rule__Function__Group__4
            {
            pushFollow(FOLLOW_18);
            rule__Function__Group__3__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Function__Group__4();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Function__Group__3"


    // $ANTLR start "rule__Function__Group__3__Impl"
    // InternalEquationDSL.g:2127:1: rule__Function__Group__3__Impl : ( ( rule__Function__RightAssignment_3 ) ) ;
    public final void rule__Function__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalEquationDSL.g:2131:1: ( ( ( rule__Function__RightAssignment_3 ) ) )
            // InternalEquationDSL.g:2132:1: ( ( rule__Function__RightAssignment_3 ) )
            {
            // InternalEquationDSL.g:2132:1: ( ( rule__Function__RightAssignment_3 ) )
            // InternalEquationDSL.g:2133:2: ( rule__Function__RightAssignment_3 )
            {
             before(grammarAccess.getFunctionAccess().getRightAssignment_3()); 
            // InternalEquationDSL.g:2134:2: ( rule__Function__RightAssignment_3 )
            // InternalEquationDSL.g:2134:3: rule__Function__RightAssignment_3
            {
            pushFollow(FOLLOW_2);
            rule__Function__RightAssignment_3();

            state._fsp--;


            }

             after(grammarAccess.getFunctionAccess().getRightAssignment_3()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Function__Group__3__Impl"


    // $ANTLR start "rule__Function__Group__4"
    // InternalEquationDSL.g:2142:1: rule__Function__Group__4 : rule__Function__Group__4__Impl ;
    public final void rule__Function__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalEquationDSL.g:2146:1: ( rule__Function__Group__4__Impl )
            // InternalEquationDSL.g:2147:2: rule__Function__Group__4__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Function__Group__4__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Function__Group__4"


    // $ANTLR start "rule__Function__Group__4__Impl"
    // InternalEquationDSL.g:2153:1: rule__Function__Group__4__Impl : ( ')' ) ;
    public final void rule__Function__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalEquationDSL.g:2157:1: ( ( ')' ) )
            // InternalEquationDSL.g:2158:1: ( ')' )
            {
            // InternalEquationDSL.g:2158:1: ( ')' )
            // InternalEquationDSL.g:2159:2: ')'
            {
             before(grammarAccess.getFunctionAccess().getRightParenthesisKeyword_4()); 
            match(input,33,FOLLOW_2); 
             after(grammarAccess.getFunctionAccess().getRightParenthesisKeyword_4()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Function__Group__4__Impl"


    // $ANTLR start "rule__AdvancedFunction__Group__0"
    // InternalEquationDSL.g:2169:1: rule__AdvancedFunction__Group__0 : rule__AdvancedFunction__Group__0__Impl rule__AdvancedFunction__Group__1 ;
    public final void rule__AdvancedFunction__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalEquationDSL.g:2173:1: ( rule__AdvancedFunction__Group__0__Impl rule__AdvancedFunction__Group__1 )
            // InternalEquationDSL.g:2174:2: rule__AdvancedFunction__Group__0__Impl rule__AdvancedFunction__Group__1
            {
            pushFollow(FOLLOW_6);
            rule__AdvancedFunction__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__AdvancedFunction__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__AdvancedFunction__Group__0"


    // $ANTLR start "rule__AdvancedFunction__Group__0__Impl"
    // InternalEquationDSL.g:2181:1: rule__AdvancedFunction__Group__0__Impl : ( () ) ;
    public final void rule__AdvancedFunction__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalEquationDSL.g:2185:1: ( ( () ) )
            // InternalEquationDSL.g:2186:1: ( () )
            {
            // InternalEquationDSL.g:2186:1: ( () )
            // InternalEquationDSL.g:2187:2: ()
            {
             before(grammarAccess.getAdvancedFunctionAccess().getAdvancedFunctionAction_0()); 
            // InternalEquationDSL.g:2188:2: ()
            // InternalEquationDSL.g:2188:3: 
            {
            }

             after(grammarAccess.getAdvancedFunctionAccess().getAdvancedFunctionAction_0()); 

            }


            }

        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__AdvancedFunction__Group__0__Impl"


    // $ANTLR start "rule__AdvancedFunction__Group__1"
    // InternalEquationDSL.g:2196:1: rule__AdvancedFunction__Group__1 : rule__AdvancedFunction__Group__1__Impl rule__AdvancedFunction__Group__2 ;
    public final void rule__AdvancedFunction__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalEquationDSL.g:2200:1: ( rule__AdvancedFunction__Group__1__Impl rule__AdvancedFunction__Group__2 )
            // InternalEquationDSL.g:2201:2: rule__AdvancedFunction__Group__1__Impl rule__AdvancedFunction__Group__2
            {
            pushFollow(FOLLOW_20);
            rule__AdvancedFunction__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__AdvancedFunction__Group__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__AdvancedFunction__Group__1"


    // $ANTLR start "rule__AdvancedFunction__Group__1__Impl"
    // InternalEquationDSL.g:2208:1: rule__AdvancedFunction__Group__1__Impl : ( ( rule__AdvancedFunction__OperatorAssignment_1 ) ) ;
    public final void rule__AdvancedFunction__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalEquationDSL.g:2212:1: ( ( ( rule__AdvancedFunction__OperatorAssignment_1 ) ) )
            // InternalEquationDSL.g:2213:1: ( ( rule__AdvancedFunction__OperatorAssignment_1 ) )
            {
            // InternalEquationDSL.g:2213:1: ( ( rule__AdvancedFunction__OperatorAssignment_1 ) )
            // InternalEquationDSL.g:2214:2: ( rule__AdvancedFunction__OperatorAssignment_1 )
            {
             before(grammarAccess.getAdvancedFunctionAccess().getOperatorAssignment_1()); 
            // InternalEquationDSL.g:2215:2: ( rule__AdvancedFunction__OperatorAssignment_1 )
            // InternalEquationDSL.g:2215:3: rule__AdvancedFunction__OperatorAssignment_1
            {
            pushFollow(FOLLOW_2);
            rule__AdvancedFunction__OperatorAssignment_1();

            state._fsp--;


            }

             after(grammarAccess.getAdvancedFunctionAccess().getOperatorAssignment_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__AdvancedFunction__Group__1__Impl"


    // $ANTLR start "rule__AdvancedFunction__Group__2"
    // InternalEquationDSL.g:2223:1: rule__AdvancedFunction__Group__2 : rule__AdvancedFunction__Group__2__Impl rule__AdvancedFunction__Group__3 ;
    public final void rule__AdvancedFunction__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalEquationDSL.g:2227:1: ( rule__AdvancedFunction__Group__2__Impl rule__AdvancedFunction__Group__3 )
            // InternalEquationDSL.g:2228:2: rule__AdvancedFunction__Group__2__Impl rule__AdvancedFunction__Group__3
            {
            pushFollow(FOLLOW_9);
            rule__AdvancedFunction__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__AdvancedFunction__Group__3();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__AdvancedFunction__Group__2"


    // $ANTLR start "rule__AdvancedFunction__Group__2__Impl"
    // InternalEquationDSL.g:2235:1: rule__AdvancedFunction__Group__2__Impl : ( '(' ) ;
    public final void rule__AdvancedFunction__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalEquationDSL.g:2239:1: ( ( '(' ) )
            // InternalEquationDSL.g:2240:1: ( '(' )
            {
            // InternalEquationDSL.g:2240:1: ( '(' )
            // InternalEquationDSL.g:2241:2: '('
            {
             before(grammarAccess.getAdvancedFunctionAccess().getLeftParenthesisKeyword_2()); 
            match(input,32,FOLLOW_2); 
             after(grammarAccess.getAdvancedFunctionAccess().getLeftParenthesisKeyword_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__AdvancedFunction__Group__2__Impl"


    // $ANTLR start "rule__AdvancedFunction__Group__3"
    // InternalEquationDSL.g:2250:1: rule__AdvancedFunction__Group__3 : rule__AdvancedFunction__Group__3__Impl rule__AdvancedFunction__Group__4 ;
    public final void rule__AdvancedFunction__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalEquationDSL.g:2254:1: ( rule__AdvancedFunction__Group__3__Impl rule__AdvancedFunction__Group__4 )
            // InternalEquationDSL.g:2255:2: rule__AdvancedFunction__Group__3__Impl rule__AdvancedFunction__Group__4
            {
            pushFollow(FOLLOW_21);
            rule__AdvancedFunction__Group__3__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__AdvancedFunction__Group__4();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__AdvancedFunction__Group__3"


    // $ANTLR start "rule__AdvancedFunction__Group__3__Impl"
    // InternalEquationDSL.g:2262:1: rule__AdvancedFunction__Group__3__Impl : ( ( rule__AdvancedFunction__InputsAssignment_3 ) ) ;
    public final void rule__AdvancedFunction__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalEquationDSL.g:2266:1: ( ( ( rule__AdvancedFunction__InputsAssignment_3 ) ) )
            // InternalEquationDSL.g:2267:1: ( ( rule__AdvancedFunction__InputsAssignment_3 ) )
            {
            // InternalEquationDSL.g:2267:1: ( ( rule__AdvancedFunction__InputsAssignment_3 ) )
            // InternalEquationDSL.g:2268:2: ( rule__AdvancedFunction__InputsAssignment_3 )
            {
             before(grammarAccess.getAdvancedFunctionAccess().getInputsAssignment_3()); 
            // InternalEquationDSL.g:2269:2: ( rule__AdvancedFunction__InputsAssignment_3 )
            // InternalEquationDSL.g:2269:3: rule__AdvancedFunction__InputsAssignment_3
            {
            pushFollow(FOLLOW_2);
            rule__AdvancedFunction__InputsAssignment_3();

            state._fsp--;


            }

             after(grammarAccess.getAdvancedFunctionAccess().getInputsAssignment_3()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__AdvancedFunction__Group__3__Impl"


    // $ANTLR start "rule__AdvancedFunction__Group__4"
    // InternalEquationDSL.g:2277:1: rule__AdvancedFunction__Group__4 : rule__AdvancedFunction__Group__4__Impl rule__AdvancedFunction__Group__5 ;
    public final void rule__AdvancedFunction__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalEquationDSL.g:2281:1: ( rule__AdvancedFunction__Group__4__Impl rule__AdvancedFunction__Group__5 )
            // InternalEquationDSL.g:2282:2: rule__AdvancedFunction__Group__4__Impl rule__AdvancedFunction__Group__5
            {
            pushFollow(FOLLOW_21);
            rule__AdvancedFunction__Group__4__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__AdvancedFunction__Group__5();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__AdvancedFunction__Group__4"


    // $ANTLR start "rule__AdvancedFunction__Group__4__Impl"
    // InternalEquationDSL.g:2289:1: rule__AdvancedFunction__Group__4__Impl : ( ( rule__AdvancedFunction__Group_4__0 )* ) ;
    public final void rule__AdvancedFunction__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalEquationDSL.g:2293:1: ( ( ( rule__AdvancedFunction__Group_4__0 )* ) )
            // InternalEquationDSL.g:2294:1: ( ( rule__AdvancedFunction__Group_4__0 )* )
            {
            // InternalEquationDSL.g:2294:1: ( ( rule__AdvancedFunction__Group_4__0 )* )
            // InternalEquationDSL.g:2295:2: ( rule__AdvancedFunction__Group_4__0 )*
            {
             before(grammarAccess.getAdvancedFunctionAccess().getGroup_4()); 
            // InternalEquationDSL.g:2296:2: ( rule__AdvancedFunction__Group_4__0 )*
            loop14:
            do {
                int alt14=2;
                int LA14_0 = input.LA(1);

                if ( (LA14_0==34) ) {
                    alt14=1;
                }


                switch (alt14) {
            	case 1 :
            	    // InternalEquationDSL.g:2296:3: rule__AdvancedFunction__Group_4__0
            	    {
            	    pushFollow(FOLLOW_22);
            	    rule__AdvancedFunction__Group_4__0();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop14;
                }
            } while (true);

             after(grammarAccess.getAdvancedFunctionAccess().getGroup_4()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__AdvancedFunction__Group__4__Impl"


    // $ANTLR start "rule__AdvancedFunction__Group__5"
    // InternalEquationDSL.g:2304:1: rule__AdvancedFunction__Group__5 : rule__AdvancedFunction__Group__5__Impl ;
    public final void rule__AdvancedFunction__Group__5() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalEquationDSL.g:2308:1: ( rule__AdvancedFunction__Group__5__Impl )
            // InternalEquationDSL.g:2309:2: rule__AdvancedFunction__Group__5__Impl
            {
            pushFollow(FOLLOW_2);
            rule__AdvancedFunction__Group__5__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__AdvancedFunction__Group__5"


    // $ANTLR start "rule__AdvancedFunction__Group__5__Impl"
    // InternalEquationDSL.g:2315:1: rule__AdvancedFunction__Group__5__Impl : ( ')' ) ;
    public final void rule__AdvancedFunction__Group__5__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalEquationDSL.g:2319:1: ( ( ')' ) )
            // InternalEquationDSL.g:2320:1: ( ')' )
            {
            // InternalEquationDSL.g:2320:1: ( ')' )
            // InternalEquationDSL.g:2321:2: ')'
            {
             before(grammarAccess.getAdvancedFunctionAccess().getRightParenthesisKeyword_5()); 
            match(input,33,FOLLOW_2); 
             after(grammarAccess.getAdvancedFunctionAccess().getRightParenthesisKeyword_5()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__AdvancedFunction__Group__5__Impl"


    // $ANTLR start "rule__AdvancedFunction__Group_4__0"
    // InternalEquationDSL.g:2331:1: rule__AdvancedFunction__Group_4__0 : rule__AdvancedFunction__Group_4__0__Impl rule__AdvancedFunction__Group_4__1 ;
    public final void rule__AdvancedFunction__Group_4__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalEquationDSL.g:2335:1: ( rule__AdvancedFunction__Group_4__0__Impl rule__AdvancedFunction__Group_4__1 )
            // InternalEquationDSL.g:2336:2: rule__AdvancedFunction__Group_4__0__Impl rule__AdvancedFunction__Group_4__1
            {
            pushFollow(FOLLOW_9);
            rule__AdvancedFunction__Group_4__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__AdvancedFunction__Group_4__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__AdvancedFunction__Group_4__0"


    // $ANTLR start "rule__AdvancedFunction__Group_4__0__Impl"
    // InternalEquationDSL.g:2343:1: rule__AdvancedFunction__Group_4__0__Impl : ( ',' ) ;
    public final void rule__AdvancedFunction__Group_4__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalEquationDSL.g:2347:1: ( ( ',' ) )
            // InternalEquationDSL.g:2348:1: ( ',' )
            {
            // InternalEquationDSL.g:2348:1: ( ',' )
            // InternalEquationDSL.g:2349:2: ','
            {
             before(grammarAccess.getAdvancedFunctionAccess().getCommaKeyword_4_0()); 
            match(input,34,FOLLOW_2); 
             after(grammarAccess.getAdvancedFunctionAccess().getCommaKeyword_4_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__AdvancedFunction__Group_4__0__Impl"


    // $ANTLR start "rule__AdvancedFunction__Group_4__1"
    // InternalEquationDSL.g:2358:1: rule__AdvancedFunction__Group_4__1 : rule__AdvancedFunction__Group_4__1__Impl ;
    public final void rule__AdvancedFunction__Group_4__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalEquationDSL.g:2362:1: ( rule__AdvancedFunction__Group_4__1__Impl )
            // InternalEquationDSL.g:2363:2: rule__AdvancedFunction__Group_4__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__AdvancedFunction__Group_4__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__AdvancedFunction__Group_4__1"


    // $ANTLR start "rule__AdvancedFunction__Group_4__1__Impl"
    // InternalEquationDSL.g:2369:1: rule__AdvancedFunction__Group_4__1__Impl : ( ( rule__AdvancedFunction__InputsAssignment_4_1 ) ) ;
    public final void rule__AdvancedFunction__Group_4__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalEquationDSL.g:2373:1: ( ( ( rule__AdvancedFunction__InputsAssignment_4_1 ) ) )
            // InternalEquationDSL.g:2374:1: ( ( rule__AdvancedFunction__InputsAssignment_4_1 ) )
            {
            // InternalEquationDSL.g:2374:1: ( ( rule__AdvancedFunction__InputsAssignment_4_1 ) )
            // InternalEquationDSL.g:2375:2: ( rule__AdvancedFunction__InputsAssignment_4_1 )
            {
             before(grammarAccess.getAdvancedFunctionAccess().getInputsAssignment_4_1()); 
            // InternalEquationDSL.g:2376:2: ( rule__AdvancedFunction__InputsAssignment_4_1 )
            // InternalEquationDSL.g:2376:3: rule__AdvancedFunction__InputsAssignment_4_1
            {
            pushFollow(FOLLOW_2);
            rule__AdvancedFunction__InputsAssignment_4_1();

            state._fsp--;


            }

             after(grammarAccess.getAdvancedFunctionAccess().getInputsAssignment_4_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__AdvancedFunction__Group_4__1__Impl"


    // $ANTLR start "rule__SetFunction__Group__0"
    // InternalEquationDSL.g:2385:1: rule__SetFunction__Group__0 : rule__SetFunction__Group__0__Impl rule__SetFunction__Group__1 ;
    public final void rule__SetFunction__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalEquationDSL.g:2389:1: ( rule__SetFunction__Group__0__Impl rule__SetFunction__Group__1 )
            // InternalEquationDSL.g:2390:2: rule__SetFunction__Group__0__Impl rule__SetFunction__Group__1
            {
            pushFollow(FOLLOW_9);
            rule__SetFunction__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__SetFunction__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__SetFunction__Group__0"


    // $ANTLR start "rule__SetFunction__Group__0__Impl"
    // InternalEquationDSL.g:2397:1: rule__SetFunction__Group__0__Impl : ( () ) ;
    public final void rule__SetFunction__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalEquationDSL.g:2401:1: ( ( () ) )
            // InternalEquationDSL.g:2402:1: ( () )
            {
            // InternalEquationDSL.g:2402:1: ( () )
            // InternalEquationDSL.g:2403:2: ()
            {
             before(grammarAccess.getSetFunctionAccess().getSetFunctionAction_0()); 
            // InternalEquationDSL.g:2404:2: ()
            // InternalEquationDSL.g:2404:3: 
            {
            }

             after(grammarAccess.getSetFunctionAccess().getSetFunctionAction_0()); 

            }


            }

        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__SetFunction__Group__0__Impl"


    // $ANTLR start "rule__SetFunction__Group__1"
    // InternalEquationDSL.g:2412:1: rule__SetFunction__Group__1 : rule__SetFunction__Group__1__Impl rule__SetFunction__Group__2 ;
    public final void rule__SetFunction__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalEquationDSL.g:2416:1: ( rule__SetFunction__Group__1__Impl rule__SetFunction__Group__2 )
            // InternalEquationDSL.g:2417:2: rule__SetFunction__Group__1__Impl rule__SetFunction__Group__2
            {
            pushFollow(FOLLOW_23);
            rule__SetFunction__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__SetFunction__Group__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__SetFunction__Group__1"


    // $ANTLR start "rule__SetFunction__Group__1__Impl"
    // InternalEquationDSL.g:2424:1: rule__SetFunction__Group__1__Impl : ( ( rule__SetFunction__OperatorAssignment_1 ) ) ;
    public final void rule__SetFunction__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalEquationDSL.g:2428:1: ( ( ( rule__SetFunction__OperatorAssignment_1 ) ) )
            // InternalEquationDSL.g:2429:1: ( ( rule__SetFunction__OperatorAssignment_1 ) )
            {
            // InternalEquationDSL.g:2429:1: ( ( rule__SetFunction__OperatorAssignment_1 ) )
            // InternalEquationDSL.g:2430:2: ( rule__SetFunction__OperatorAssignment_1 )
            {
             before(grammarAccess.getSetFunctionAccess().getOperatorAssignment_1()); 
            // InternalEquationDSL.g:2431:2: ( rule__SetFunction__OperatorAssignment_1 )
            // InternalEquationDSL.g:2431:3: rule__SetFunction__OperatorAssignment_1
            {
            pushFollow(FOLLOW_2);
            rule__SetFunction__OperatorAssignment_1();

            state._fsp--;


            }

             after(grammarAccess.getSetFunctionAccess().getOperatorAssignment_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__SetFunction__Group__1__Impl"


    // $ANTLR start "rule__SetFunction__Group__2"
    // InternalEquationDSL.g:2439:1: rule__SetFunction__Group__2 : rule__SetFunction__Group__2__Impl rule__SetFunction__Group__3 ;
    public final void rule__SetFunction__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalEquationDSL.g:2443:1: ( rule__SetFunction__Group__2__Impl rule__SetFunction__Group__3 )
            // InternalEquationDSL.g:2444:2: rule__SetFunction__Group__2__Impl rule__SetFunction__Group__3
            {
            pushFollow(FOLLOW_6);
            rule__SetFunction__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__SetFunction__Group__3();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__SetFunction__Group__2"


    // $ANTLR start "rule__SetFunction__Group__2__Impl"
    // InternalEquationDSL.g:2451:1: rule__SetFunction__Group__2__Impl : ( '{' ) ;
    public final void rule__SetFunction__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalEquationDSL.g:2455:1: ( ( '{' ) )
            // InternalEquationDSL.g:2456:1: ( '{' )
            {
            // InternalEquationDSL.g:2456:1: ( '{' )
            // InternalEquationDSL.g:2457:2: '{'
            {
             before(grammarAccess.getSetFunctionAccess().getLeftCurlyBracketKeyword_2()); 
            match(input,35,FOLLOW_2); 
             after(grammarAccess.getSetFunctionAccess().getLeftCurlyBracketKeyword_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__SetFunction__Group__2__Impl"


    // $ANTLR start "rule__SetFunction__Group__3"
    // InternalEquationDSL.g:2466:1: rule__SetFunction__Group__3 : rule__SetFunction__Group__3__Impl rule__SetFunction__Group__4 ;
    public final void rule__SetFunction__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalEquationDSL.g:2470:1: ( rule__SetFunction__Group__3__Impl rule__SetFunction__Group__4 )
            // InternalEquationDSL.g:2471:2: rule__SetFunction__Group__3__Impl rule__SetFunction__Group__4
            {
            pushFollow(FOLLOW_24);
            rule__SetFunction__Group__3__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__SetFunction__Group__4();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__SetFunction__Group__3"


    // $ANTLR start "rule__SetFunction__Group__3__Impl"
    // InternalEquationDSL.g:2478:1: rule__SetFunction__Group__3__Impl : ( ( rule__SetFunction__TypeDefinitionAssignment_3 ) ) ;
    public final void rule__SetFunction__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalEquationDSL.g:2482:1: ( ( ( rule__SetFunction__TypeDefinitionAssignment_3 ) ) )
            // InternalEquationDSL.g:2483:1: ( ( rule__SetFunction__TypeDefinitionAssignment_3 ) )
            {
            // InternalEquationDSL.g:2483:1: ( ( rule__SetFunction__TypeDefinitionAssignment_3 ) )
            // InternalEquationDSL.g:2484:2: ( rule__SetFunction__TypeDefinitionAssignment_3 )
            {
             before(grammarAccess.getSetFunctionAccess().getTypeDefinitionAssignment_3()); 
            // InternalEquationDSL.g:2485:2: ( rule__SetFunction__TypeDefinitionAssignment_3 )
            // InternalEquationDSL.g:2485:3: rule__SetFunction__TypeDefinitionAssignment_3
            {
            pushFollow(FOLLOW_2);
            rule__SetFunction__TypeDefinitionAssignment_3();

            state._fsp--;


            }

             after(grammarAccess.getSetFunctionAccess().getTypeDefinitionAssignment_3()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__SetFunction__Group__3__Impl"


    // $ANTLR start "rule__SetFunction__Group__4"
    // InternalEquationDSL.g:2493:1: rule__SetFunction__Group__4 : rule__SetFunction__Group__4__Impl rule__SetFunction__Group__5 ;
    public final void rule__SetFunction__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalEquationDSL.g:2497:1: ( rule__SetFunction__Group__4__Impl rule__SetFunction__Group__5 )
            // InternalEquationDSL.g:2498:2: rule__SetFunction__Group__4__Impl rule__SetFunction__Group__5
            {
            pushFollow(FOLLOW_24);
            rule__SetFunction__Group__4__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__SetFunction__Group__5();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__SetFunction__Group__4"


    // $ANTLR start "rule__SetFunction__Group__4__Impl"
    // InternalEquationDSL.g:2505:1: rule__SetFunction__Group__4__Impl : ( ( rule__SetFunction__Group_4__0 )? ) ;
    public final void rule__SetFunction__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalEquationDSL.g:2509:1: ( ( ( rule__SetFunction__Group_4__0 )? ) )
            // InternalEquationDSL.g:2510:1: ( ( rule__SetFunction__Group_4__0 )? )
            {
            // InternalEquationDSL.g:2510:1: ( ( rule__SetFunction__Group_4__0 )? )
            // InternalEquationDSL.g:2511:2: ( rule__SetFunction__Group_4__0 )?
            {
             before(grammarAccess.getSetFunctionAccess().getGroup_4()); 
            // InternalEquationDSL.g:2512:2: ( rule__SetFunction__Group_4__0 )?
            int alt15=2;
            int LA15_0 = input.LA(1);

            if ( (LA15_0==34) ) {
                int LA15_1 = input.LA(2);

                if ( (LA15_1==RULE_INT) ) {
                    alt15=1;
                }
            }
            switch (alt15) {
                case 1 :
                    // InternalEquationDSL.g:2512:3: rule__SetFunction__Group_4__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__SetFunction__Group_4__0();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getSetFunctionAccess().getGroup_4()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__SetFunction__Group__4__Impl"


    // $ANTLR start "rule__SetFunction__Group__5"
    // InternalEquationDSL.g:2520:1: rule__SetFunction__Group__5 : rule__SetFunction__Group__5__Impl rule__SetFunction__Group__6 ;
    public final void rule__SetFunction__Group__5() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalEquationDSL.g:2524:1: ( rule__SetFunction__Group__5__Impl rule__SetFunction__Group__6 )
            // InternalEquationDSL.g:2525:2: rule__SetFunction__Group__5__Impl rule__SetFunction__Group__6
            {
            pushFollow(FOLLOW_24);
            rule__SetFunction__Group__5__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__SetFunction__Group__6();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__SetFunction__Group__5"


    // $ANTLR start "rule__SetFunction__Group__5__Impl"
    // InternalEquationDSL.g:2532:1: rule__SetFunction__Group__5__Impl : ( ( rule__SetFunction__Group_5__0 )? ) ;
    public final void rule__SetFunction__Group__5__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalEquationDSL.g:2536:1: ( ( ( rule__SetFunction__Group_5__0 )? ) )
            // InternalEquationDSL.g:2537:1: ( ( rule__SetFunction__Group_5__0 )? )
            {
            // InternalEquationDSL.g:2537:1: ( ( rule__SetFunction__Group_5__0 )? )
            // InternalEquationDSL.g:2538:2: ( rule__SetFunction__Group_5__0 )?
            {
             before(grammarAccess.getSetFunctionAccess().getGroup_5()); 
            // InternalEquationDSL.g:2539:2: ( rule__SetFunction__Group_5__0 )?
            int alt16=2;
            int LA16_0 = input.LA(1);

            if ( (LA16_0==34) ) {
                alt16=1;
            }
            switch (alt16) {
                case 1 :
                    // InternalEquationDSL.g:2539:3: rule__SetFunction__Group_5__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__SetFunction__Group_5__0();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getSetFunctionAccess().getGroup_5()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__SetFunction__Group__5__Impl"


    // $ANTLR start "rule__SetFunction__Group__6"
    // InternalEquationDSL.g:2547:1: rule__SetFunction__Group__6 : rule__SetFunction__Group__6__Impl ;
    public final void rule__SetFunction__Group__6() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalEquationDSL.g:2551:1: ( rule__SetFunction__Group__6__Impl )
            // InternalEquationDSL.g:2552:2: rule__SetFunction__Group__6__Impl
            {
            pushFollow(FOLLOW_2);
            rule__SetFunction__Group__6__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__SetFunction__Group__6"


    // $ANTLR start "rule__SetFunction__Group__6__Impl"
    // InternalEquationDSL.g:2558:1: rule__SetFunction__Group__6__Impl : ( '}' ) ;
    public final void rule__SetFunction__Group__6__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalEquationDSL.g:2562:1: ( ( '}' ) )
            // InternalEquationDSL.g:2563:1: ( '}' )
            {
            // InternalEquationDSL.g:2563:1: ( '}' )
            // InternalEquationDSL.g:2564:2: '}'
            {
             before(grammarAccess.getSetFunctionAccess().getRightCurlyBracketKeyword_6()); 
            match(input,36,FOLLOW_2); 
             after(grammarAccess.getSetFunctionAccess().getRightCurlyBracketKeyword_6()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__SetFunction__Group__6__Impl"


    // $ANTLR start "rule__SetFunction__Group_4__0"
    // InternalEquationDSL.g:2574:1: rule__SetFunction__Group_4__0 : rule__SetFunction__Group_4__0__Impl rule__SetFunction__Group_4__1 ;
    public final void rule__SetFunction__Group_4__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalEquationDSL.g:2578:1: ( rule__SetFunction__Group_4__0__Impl rule__SetFunction__Group_4__1 )
            // InternalEquationDSL.g:2579:2: rule__SetFunction__Group_4__0__Impl rule__SetFunction__Group_4__1
            {
            pushFollow(FOLLOW_25);
            rule__SetFunction__Group_4__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__SetFunction__Group_4__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__SetFunction__Group_4__0"


    // $ANTLR start "rule__SetFunction__Group_4__0__Impl"
    // InternalEquationDSL.g:2586:1: rule__SetFunction__Group_4__0__Impl : ( ',' ) ;
    public final void rule__SetFunction__Group_4__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalEquationDSL.g:2590:1: ( ( ',' ) )
            // InternalEquationDSL.g:2591:1: ( ',' )
            {
            // InternalEquationDSL.g:2591:1: ( ',' )
            // InternalEquationDSL.g:2592:2: ','
            {
             before(grammarAccess.getSetFunctionAccess().getCommaKeyword_4_0()); 
            match(input,34,FOLLOW_2); 
             after(grammarAccess.getSetFunctionAccess().getCommaKeyword_4_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__SetFunction__Group_4__0__Impl"


    // $ANTLR start "rule__SetFunction__Group_4__1"
    // InternalEquationDSL.g:2601:1: rule__SetFunction__Group_4__1 : rule__SetFunction__Group_4__1__Impl ;
    public final void rule__SetFunction__Group_4__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalEquationDSL.g:2605:1: ( rule__SetFunction__Group_4__1__Impl )
            // InternalEquationDSL.g:2606:2: rule__SetFunction__Group_4__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__SetFunction__Group_4__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__SetFunction__Group_4__1"


    // $ANTLR start "rule__SetFunction__Group_4__1__Impl"
    // InternalEquationDSL.g:2612:1: rule__SetFunction__Group_4__1__Impl : ( ( rule__SetFunction__DepthAssignment_4_1 ) ) ;
    public final void rule__SetFunction__Group_4__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalEquationDSL.g:2616:1: ( ( ( rule__SetFunction__DepthAssignment_4_1 ) ) )
            // InternalEquationDSL.g:2617:1: ( ( rule__SetFunction__DepthAssignment_4_1 ) )
            {
            // InternalEquationDSL.g:2617:1: ( ( rule__SetFunction__DepthAssignment_4_1 ) )
            // InternalEquationDSL.g:2618:2: ( rule__SetFunction__DepthAssignment_4_1 )
            {
             before(grammarAccess.getSetFunctionAccess().getDepthAssignment_4_1()); 
            // InternalEquationDSL.g:2619:2: ( rule__SetFunction__DepthAssignment_4_1 )
            // InternalEquationDSL.g:2619:3: rule__SetFunction__DepthAssignment_4_1
            {
            pushFollow(FOLLOW_2);
            rule__SetFunction__DepthAssignment_4_1();

            state._fsp--;


            }

             after(grammarAccess.getSetFunctionAccess().getDepthAssignment_4_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__SetFunction__Group_4__1__Impl"


    // $ANTLR start "rule__SetFunction__Group_5__0"
    // InternalEquationDSL.g:2628:1: rule__SetFunction__Group_5__0 : rule__SetFunction__Group_5__0__Impl rule__SetFunction__Group_5__1 ;
    public final void rule__SetFunction__Group_5__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalEquationDSL.g:2632:1: ( rule__SetFunction__Group_5__0__Impl rule__SetFunction__Group_5__1 )
            // InternalEquationDSL.g:2633:2: rule__SetFunction__Group_5__0__Impl rule__SetFunction__Group_5__1
            {
            pushFollow(FOLLOW_6);
            rule__SetFunction__Group_5__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__SetFunction__Group_5__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__SetFunction__Group_5__0"


    // $ANTLR start "rule__SetFunction__Group_5__0__Impl"
    // InternalEquationDSL.g:2640:1: rule__SetFunction__Group_5__0__Impl : ( ',' ) ;
    public final void rule__SetFunction__Group_5__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalEquationDSL.g:2644:1: ( ( ',' ) )
            // InternalEquationDSL.g:2645:1: ( ',' )
            {
            // InternalEquationDSL.g:2645:1: ( ',' )
            // InternalEquationDSL.g:2646:2: ','
            {
             before(grammarAccess.getSetFunctionAccess().getCommaKeyword_5_0()); 
            match(input,34,FOLLOW_2); 
             after(grammarAccess.getSetFunctionAccess().getCommaKeyword_5_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__SetFunction__Group_5__0__Impl"


    // $ANTLR start "rule__SetFunction__Group_5__1"
    // InternalEquationDSL.g:2655:1: rule__SetFunction__Group_5__1 : rule__SetFunction__Group_5__1__Impl ;
    public final void rule__SetFunction__Group_5__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalEquationDSL.g:2659:1: ( rule__SetFunction__Group_5__1__Impl )
            // InternalEquationDSL.g:2660:2: rule__SetFunction__Group_5__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__SetFunction__Group_5__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__SetFunction__Group_5__1"


    // $ANTLR start "rule__SetFunction__Group_5__1__Impl"
    // InternalEquationDSL.g:2666:1: rule__SetFunction__Group_5__1__Impl : ( ( rule__SetFunction__FilterNameAssignment_5_1 ) ) ;
    public final void rule__SetFunction__Group_5__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalEquationDSL.g:2670:1: ( ( ( rule__SetFunction__FilterNameAssignment_5_1 ) ) )
            // InternalEquationDSL.g:2671:1: ( ( rule__SetFunction__FilterNameAssignment_5_1 ) )
            {
            // InternalEquationDSL.g:2671:1: ( ( rule__SetFunction__FilterNameAssignment_5_1 ) )
            // InternalEquationDSL.g:2672:2: ( rule__SetFunction__FilterNameAssignment_5_1 )
            {
             before(grammarAccess.getSetFunctionAccess().getFilterNameAssignment_5_1()); 
            // InternalEquationDSL.g:2673:2: ( rule__SetFunction__FilterNameAssignment_5_1 )
            // InternalEquationDSL.g:2673:3: rule__SetFunction__FilterNameAssignment_5_1
            {
            pushFollow(FOLLOW_2);
            rule__SetFunction__FilterNameAssignment_5_1();

            state._fsp--;


            }

             after(grammarAccess.getSetFunctionAccess().getFilterNameAssignment_5_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__SetFunction__Group_5__1__Impl"


    // $ANTLR start "rule__NumberLiteral__Group__0"
    // InternalEquationDSL.g:2682:1: rule__NumberLiteral__Group__0 : rule__NumberLiteral__Group__0__Impl rule__NumberLiteral__Group__1 ;
    public final void rule__NumberLiteral__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalEquationDSL.g:2686:1: ( rule__NumberLiteral__Group__0__Impl rule__NumberLiteral__Group__1 )
            // InternalEquationDSL.g:2687:2: rule__NumberLiteral__Group__0__Impl rule__NumberLiteral__Group__1
            {
            pushFollow(FOLLOW_26);
            rule__NumberLiteral__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__NumberLiteral__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__NumberLiteral__Group__0"


    // $ANTLR start "rule__NumberLiteral__Group__0__Impl"
    // InternalEquationDSL.g:2694:1: rule__NumberLiteral__Group__0__Impl : ( () ) ;
    public final void rule__NumberLiteral__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalEquationDSL.g:2698:1: ( ( () ) )
            // InternalEquationDSL.g:2699:1: ( () )
            {
            // InternalEquationDSL.g:2699:1: ( () )
            // InternalEquationDSL.g:2700:2: ()
            {
             before(grammarAccess.getNumberLiteralAccess().getNumberLiteralAction_0()); 
            // InternalEquationDSL.g:2701:2: ()
            // InternalEquationDSL.g:2701:3: 
            {
            }

             after(grammarAccess.getNumberLiteralAccess().getNumberLiteralAction_0()); 

            }


            }

        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__NumberLiteral__Group__0__Impl"


    // $ANTLR start "rule__NumberLiteral__Group__1"
    // InternalEquationDSL.g:2709:1: rule__NumberLiteral__Group__1 : rule__NumberLiteral__Group__1__Impl ;
    public final void rule__NumberLiteral__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalEquationDSL.g:2713:1: ( rule__NumberLiteral__Group__1__Impl )
            // InternalEquationDSL.g:2714:2: rule__NumberLiteral__Group__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__NumberLiteral__Group__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__NumberLiteral__Group__1"


    // $ANTLR start "rule__NumberLiteral__Group__1__Impl"
    // InternalEquationDSL.g:2720:1: rule__NumberLiteral__Group__1__Impl : ( ( rule__NumberLiteral__ValueAssignment_1 ) ) ;
    public final void rule__NumberLiteral__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalEquationDSL.g:2724:1: ( ( ( rule__NumberLiteral__ValueAssignment_1 ) ) )
            // InternalEquationDSL.g:2725:1: ( ( rule__NumberLiteral__ValueAssignment_1 ) )
            {
            // InternalEquationDSL.g:2725:1: ( ( rule__NumberLiteral__ValueAssignment_1 ) )
            // InternalEquationDSL.g:2726:2: ( rule__NumberLiteral__ValueAssignment_1 )
            {
             before(grammarAccess.getNumberLiteralAccess().getValueAssignment_1()); 
            // InternalEquationDSL.g:2727:2: ( rule__NumberLiteral__ValueAssignment_1 )
            // InternalEquationDSL.g:2727:3: rule__NumberLiteral__ValueAssignment_1
            {
            pushFollow(FOLLOW_2);
            rule__NumberLiteral__ValueAssignment_1();

            state._fsp--;


            }

             after(grammarAccess.getNumberLiteralAccess().getValueAssignment_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__NumberLiteral__Group__1__Impl"


    // $ANTLR start "rule__QualifiedName__Group__0"
    // InternalEquationDSL.g:2736:1: rule__QualifiedName__Group__0 : rule__QualifiedName__Group__0__Impl rule__QualifiedName__Group__1 ;
    public final void rule__QualifiedName__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalEquationDSL.g:2740:1: ( rule__QualifiedName__Group__0__Impl rule__QualifiedName__Group__1 )
            // InternalEquationDSL.g:2741:2: rule__QualifiedName__Group__0__Impl rule__QualifiedName__Group__1
            {
            pushFollow(FOLLOW_27);
            rule__QualifiedName__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__QualifiedName__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__QualifiedName__Group__0"


    // $ANTLR start "rule__QualifiedName__Group__0__Impl"
    // InternalEquationDSL.g:2748:1: rule__QualifiedName__Group__0__Impl : ( RULE_ID ) ;
    public final void rule__QualifiedName__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalEquationDSL.g:2752:1: ( ( RULE_ID ) )
            // InternalEquationDSL.g:2753:1: ( RULE_ID )
            {
            // InternalEquationDSL.g:2753:1: ( RULE_ID )
            // InternalEquationDSL.g:2754:2: RULE_ID
            {
             before(grammarAccess.getQualifiedNameAccess().getIDTerminalRuleCall_0()); 
            match(input,RULE_ID,FOLLOW_2); 
             after(grammarAccess.getQualifiedNameAccess().getIDTerminalRuleCall_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__QualifiedName__Group__0__Impl"


    // $ANTLR start "rule__QualifiedName__Group__1"
    // InternalEquationDSL.g:2763:1: rule__QualifiedName__Group__1 : rule__QualifiedName__Group__1__Impl ;
    public final void rule__QualifiedName__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalEquationDSL.g:2767:1: ( rule__QualifiedName__Group__1__Impl )
            // InternalEquationDSL.g:2768:2: rule__QualifiedName__Group__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__QualifiedName__Group__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__QualifiedName__Group__1"


    // $ANTLR start "rule__QualifiedName__Group__1__Impl"
    // InternalEquationDSL.g:2774:1: rule__QualifiedName__Group__1__Impl : ( ( rule__QualifiedName__Group_1__0 )* ) ;
    public final void rule__QualifiedName__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalEquationDSL.g:2778:1: ( ( ( rule__QualifiedName__Group_1__0 )* ) )
            // InternalEquationDSL.g:2779:1: ( ( rule__QualifiedName__Group_1__0 )* )
            {
            // InternalEquationDSL.g:2779:1: ( ( rule__QualifiedName__Group_1__0 )* )
            // InternalEquationDSL.g:2780:2: ( rule__QualifiedName__Group_1__0 )*
            {
             before(grammarAccess.getQualifiedNameAccess().getGroup_1()); 
            // InternalEquationDSL.g:2781:2: ( rule__QualifiedName__Group_1__0 )*
            loop17:
            do {
                int alt17=2;
                int LA17_0 = input.LA(1);

                if ( (LA17_0==37) ) {
                    alt17=1;
                }


                switch (alt17) {
            	case 1 :
            	    // InternalEquationDSL.g:2781:3: rule__QualifiedName__Group_1__0
            	    {
            	    pushFollow(FOLLOW_28);
            	    rule__QualifiedName__Group_1__0();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop17;
                }
            } while (true);

             after(grammarAccess.getQualifiedNameAccess().getGroup_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__QualifiedName__Group__1__Impl"


    // $ANTLR start "rule__QualifiedName__Group_1__0"
    // InternalEquationDSL.g:2790:1: rule__QualifiedName__Group_1__0 : rule__QualifiedName__Group_1__0__Impl rule__QualifiedName__Group_1__1 ;
    public final void rule__QualifiedName__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalEquationDSL.g:2794:1: ( rule__QualifiedName__Group_1__0__Impl rule__QualifiedName__Group_1__1 )
            // InternalEquationDSL.g:2795:2: rule__QualifiedName__Group_1__0__Impl rule__QualifiedName__Group_1__1
            {
            pushFollow(FOLLOW_6);
            rule__QualifiedName__Group_1__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__QualifiedName__Group_1__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__QualifiedName__Group_1__0"


    // $ANTLR start "rule__QualifiedName__Group_1__0__Impl"
    // InternalEquationDSL.g:2802:1: rule__QualifiedName__Group_1__0__Impl : ( '.' ) ;
    public final void rule__QualifiedName__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalEquationDSL.g:2806:1: ( ( '.' ) )
            // InternalEquationDSL.g:2807:1: ( '.' )
            {
            // InternalEquationDSL.g:2807:1: ( '.' )
            // InternalEquationDSL.g:2808:2: '.'
            {
             before(grammarAccess.getQualifiedNameAccess().getFullStopKeyword_1_0()); 
            match(input,37,FOLLOW_2); 
             after(grammarAccess.getQualifiedNameAccess().getFullStopKeyword_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__QualifiedName__Group_1__0__Impl"


    // $ANTLR start "rule__QualifiedName__Group_1__1"
    // InternalEquationDSL.g:2817:1: rule__QualifiedName__Group_1__1 : rule__QualifiedName__Group_1__1__Impl ;
    public final void rule__QualifiedName__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalEquationDSL.g:2821:1: ( rule__QualifiedName__Group_1__1__Impl )
            // InternalEquationDSL.g:2822:2: rule__QualifiedName__Group_1__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__QualifiedName__Group_1__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__QualifiedName__Group_1__1"


    // $ANTLR start "rule__QualifiedName__Group_1__1__Impl"
    // InternalEquationDSL.g:2828:1: rule__QualifiedName__Group_1__1__Impl : ( RULE_ID ) ;
    public final void rule__QualifiedName__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalEquationDSL.g:2832:1: ( ( RULE_ID ) )
            // InternalEquationDSL.g:2833:1: ( RULE_ID )
            {
            // InternalEquationDSL.g:2833:1: ( RULE_ID )
            // InternalEquationDSL.g:2834:2: RULE_ID
            {
             before(grammarAccess.getQualifiedNameAccess().getIDTerminalRuleCall_1_1()); 
            match(input,RULE_ID,FOLLOW_2); 
             after(grammarAccess.getQualifiedNameAccess().getIDTerminalRuleCall_1_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__QualifiedName__Group_1__1__Impl"


    // $ANTLR start "rule__NumberLiteralString__Group__0"
    // InternalEquationDSL.g:2844:1: rule__NumberLiteralString__Group__0 : rule__NumberLiteralString__Group__0__Impl rule__NumberLiteralString__Group__1 ;
    public final void rule__NumberLiteralString__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalEquationDSL.g:2848:1: ( rule__NumberLiteralString__Group__0__Impl rule__NumberLiteralString__Group__1 )
            // InternalEquationDSL.g:2849:2: rule__NumberLiteralString__Group__0__Impl rule__NumberLiteralString__Group__1
            {
            pushFollow(FOLLOW_26);
            rule__NumberLiteralString__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__NumberLiteralString__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__NumberLiteralString__Group__0"


    // $ANTLR start "rule__NumberLiteralString__Group__0__Impl"
    // InternalEquationDSL.g:2856:1: rule__NumberLiteralString__Group__0__Impl : ( ( '-' )? ) ;
    public final void rule__NumberLiteralString__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalEquationDSL.g:2860:1: ( ( ( '-' )? ) )
            // InternalEquationDSL.g:2861:1: ( ( '-' )? )
            {
            // InternalEquationDSL.g:2861:1: ( ( '-' )? )
            // InternalEquationDSL.g:2862:2: ( '-' )?
            {
             before(grammarAccess.getNumberLiteralStringAccess().getHyphenMinusKeyword_0()); 
            // InternalEquationDSL.g:2863:2: ( '-' )?
            int alt18=2;
            int LA18_0 = input.LA(1);

            if ( (LA18_0==12) ) {
                alt18=1;
            }
            switch (alt18) {
                case 1 :
                    // InternalEquationDSL.g:2863:3: '-'
                    {
                    match(input,12,FOLLOW_2); 

                    }
                    break;

            }

             after(grammarAccess.getNumberLiteralStringAccess().getHyphenMinusKeyword_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__NumberLiteralString__Group__0__Impl"


    // $ANTLR start "rule__NumberLiteralString__Group__1"
    // InternalEquationDSL.g:2871:1: rule__NumberLiteralString__Group__1 : rule__NumberLiteralString__Group__1__Impl rule__NumberLiteralString__Group__2 ;
    public final void rule__NumberLiteralString__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalEquationDSL.g:2875:1: ( rule__NumberLiteralString__Group__1__Impl rule__NumberLiteralString__Group__2 )
            // InternalEquationDSL.g:2876:2: rule__NumberLiteralString__Group__1__Impl rule__NumberLiteralString__Group__2
            {
            pushFollow(FOLLOW_27);
            rule__NumberLiteralString__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__NumberLiteralString__Group__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__NumberLiteralString__Group__1"


    // $ANTLR start "rule__NumberLiteralString__Group__1__Impl"
    // InternalEquationDSL.g:2883:1: rule__NumberLiteralString__Group__1__Impl : ( RULE_INT ) ;
    public final void rule__NumberLiteralString__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalEquationDSL.g:2887:1: ( ( RULE_INT ) )
            // InternalEquationDSL.g:2888:1: ( RULE_INT )
            {
            // InternalEquationDSL.g:2888:1: ( RULE_INT )
            // InternalEquationDSL.g:2889:2: RULE_INT
            {
             before(grammarAccess.getNumberLiteralStringAccess().getINTTerminalRuleCall_1()); 
            match(input,RULE_INT,FOLLOW_2); 
             after(grammarAccess.getNumberLiteralStringAccess().getINTTerminalRuleCall_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__NumberLiteralString__Group__1__Impl"


    // $ANTLR start "rule__NumberLiteralString__Group__2"
    // InternalEquationDSL.g:2898:1: rule__NumberLiteralString__Group__2 : rule__NumberLiteralString__Group__2__Impl ;
    public final void rule__NumberLiteralString__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalEquationDSL.g:2902:1: ( rule__NumberLiteralString__Group__2__Impl )
            // InternalEquationDSL.g:2903:2: rule__NumberLiteralString__Group__2__Impl
            {
            pushFollow(FOLLOW_2);
            rule__NumberLiteralString__Group__2__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__NumberLiteralString__Group__2"


    // $ANTLR start "rule__NumberLiteralString__Group__2__Impl"
    // InternalEquationDSL.g:2909:1: rule__NumberLiteralString__Group__2__Impl : ( ( rule__NumberLiteralString__Group_2__0 )? ) ;
    public final void rule__NumberLiteralString__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalEquationDSL.g:2913:1: ( ( ( rule__NumberLiteralString__Group_2__0 )? ) )
            // InternalEquationDSL.g:2914:1: ( ( rule__NumberLiteralString__Group_2__0 )? )
            {
            // InternalEquationDSL.g:2914:1: ( ( rule__NumberLiteralString__Group_2__0 )? )
            // InternalEquationDSL.g:2915:2: ( rule__NumberLiteralString__Group_2__0 )?
            {
             before(grammarAccess.getNumberLiteralStringAccess().getGroup_2()); 
            // InternalEquationDSL.g:2916:2: ( rule__NumberLiteralString__Group_2__0 )?
            int alt19=2;
            int LA19_0 = input.LA(1);

            if ( (LA19_0==37) ) {
                alt19=1;
            }
            switch (alt19) {
                case 1 :
                    // InternalEquationDSL.g:2916:3: rule__NumberLiteralString__Group_2__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__NumberLiteralString__Group_2__0();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getNumberLiteralStringAccess().getGroup_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__NumberLiteralString__Group__2__Impl"


    // $ANTLR start "rule__NumberLiteralString__Group_2__0"
    // InternalEquationDSL.g:2925:1: rule__NumberLiteralString__Group_2__0 : rule__NumberLiteralString__Group_2__0__Impl rule__NumberLiteralString__Group_2__1 ;
    public final void rule__NumberLiteralString__Group_2__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalEquationDSL.g:2929:1: ( rule__NumberLiteralString__Group_2__0__Impl rule__NumberLiteralString__Group_2__1 )
            // InternalEquationDSL.g:2930:2: rule__NumberLiteralString__Group_2__0__Impl rule__NumberLiteralString__Group_2__1
            {
            pushFollow(FOLLOW_25);
            rule__NumberLiteralString__Group_2__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__NumberLiteralString__Group_2__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__NumberLiteralString__Group_2__0"


    // $ANTLR start "rule__NumberLiteralString__Group_2__0__Impl"
    // InternalEquationDSL.g:2937:1: rule__NumberLiteralString__Group_2__0__Impl : ( '.' ) ;
    public final void rule__NumberLiteralString__Group_2__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalEquationDSL.g:2941:1: ( ( '.' ) )
            // InternalEquationDSL.g:2942:1: ( '.' )
            {
            // InternalEquationDSL.g:2942:1: ( '.' )
            // InternalEquationDSL.g:2943:2: '.'
            {
             before(grammarAccess.getNumberLiteralStringAccess().getFullStopKeyword_2_0()); 
            match(input,37,FOLLOW_2); 
             after(grammarAccess.getNumberLiteralStringAccess().getFullStopKeyword_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__NumberLiteralString__Group_2__0__Impl"


    // $ANTLR start "rule__NumberLiteralString__Group_2__1"
    // InternalEquationDSL.g:2952:1: rule__NumberLiteralString__Group_2__1 : rule__NumberLiteralString__Group_2__1__Impl ;
    public final void rule__NumberLiteralString__Group_2__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalEquationDSL.g:2956:1: ( rule__NumberLiteralString__Group_2__1__Impl )
            // InternalEquationDSL.g:2957:2: rule__NumberLiteralString__Group_2__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__NumberLiteralString__Group_2__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__NumberLiteralString__Group_2__1"


    // $ANTLR start "rule__NumberLiteralString__Group_2__1__Impl"
    // InternalEquationDSL.g:2963:1: rule__NumberLiteralString__Group_2__1__Impl : ( RULE_INT ) ;
    public final void rule__NumberLiteralString__Group_2__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalEquationDSL.g:2967:1: ( ( RULE_INT ) )
            // InternalEquationDSL.g:2968:1: ( RULE_INT )
            {
            // InternalEquationDSL.g:2968:1: ( RULE_INT )
            // InternalEquationDSL.g:2969:2: RULE_INT
            {
             before(grammarAccess.getNumberLiteralStringAccess().getINTTerminalRuleCall_2_1()); 
            match(input,RULE_INT,FOLLOW_2); 
             after(grammarAccess.getNumberLiteralStringAccess().getINTTerminalRuleCall_2_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__NumberLiteralString__Group_2__1__Impl"


    // $ANTLR start "rule__ValuePi__Group__0"
    // InternalEquationDSL.g:2979:1: rule__ValuePi__Group__0 : rule__ValuePi__Group__0__Impl rule__ValuePi__Group__1 ;
    public final void rule__ValuePi__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalEquationDSL.g:2983:1: ( rule__ValuePi__Group__0__Impl rule__ValuePi__Group__1 )
            // InternalEquationDSL.g:2984:2: rule__ValuePi__Group__0__Impl rule__ValuePi__Group__1
            {
            pushFollow(FOLLOW_29);
            rule__ValuePi__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ValuePi__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ValuePi__Group__0"


    // $ANTLR start "rule__ValuePi__Group__0__Impl"
    // InternalEquationDSL.g:2991:1: rule__ValuePi__Group__0__Impl : ( () ) ;
    public final void rule__ValuePi__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalEquationDSL.g:2995:1: ( ( () ) )
            // InternalEquationDSL.g:2996:1: ( () )
            {
            // InternalEquationDSL.g:2996:1: ( () )
            // InternalEquationDSL.g:2997:2: ()
            {
             before(grammarAccess.getValuePiAccess().getValuePiAction_0()); 
            // InternalEquationDSL.g:2998:2: ()
            // InternalEquationDSL.g:2998:3: 
            {
            }

             after(grammarAccess.getValuePiAccess().getValuePiAction_0()); 

            }


            }

        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ValuePi__Group__0__Impl"


    // $ANTLR start "rule__ValuePi__Group__1"
    // InternalEquationDSL.g:3006:1: rule__ValuePi__Group__1 : rule__ValuePi__Group__1__Impl ;
    public final void rule__ValuePi__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalEquationDSL.g:3010:1: ( rule__ValuePi__Group__1__Impl )
            // InternalEquationDSL.g:3011:2: rule__ValuePi__Group__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__ValuePi__Group__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ValuePi__Group__1"


    // $ANTLR start "rule__ValuePi__Group__1__Impl"
    // InternalEquationDSL.g:3017:1: rule__ValuePi__Group__1__Impl : ( 'pi' ) ;
    public final void rule__ValuePi__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalEquationDSL.g:3021:1: ( ( 'pi' ) )
            // InternalEquationDSL.g:3022:1: ( 'pi' )
            {
            // InternalEquationDSL.g:3022:1: ( 'pi' )
            // InternalEquationDSL.g:3023:2: 'pi'
            {
             before(grammarAccess.getValuePiAccess().getPiKeyword_1()); 
            match(input,38,FOLLOW_2); 
             after(grammarAccess.getValuePiAccess().getPiKeyword_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ValuePi__Group__1__Impl"


    // $ANTLR start "rule__ValueE__Group__0"
    // InternalEquationDSL.g:3033:1: rule__ValueE__Group__0 : rule__ValueE__Group__0__Impl rule__ValueE__Group__1 ;
    public final void rule__ValueE__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalEquationDSL.g:3037:1: ( rule__ValueE__Group__0__Impl rule__ValueE__Group__1 )
            // InternalEquationDSL.g:3038:2: rule__ValueE__Group__0__Impl rule__ValueE__Group__1
            {
            pushFollow(FOLLOW_30);
            rule__ValueE__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ValueE__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ValueE__Group__0"


    // $ANTLR start "rule__ValueE__Group__0__Impl"
    // InternalEquationDSL.g:3045:1: rule__ValueE__Group__0__Impl : ( () ) ;
    public final void rule__ValueE__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalEquationDSL.g:3049:1: ( ( () ) )
            // InternalEquationDSL.g:3050:1: ( () )
            {
            // InternalEquationDSL.g:3050:1: ( () )
            // InternalEquationDSL.g:3051:2: ()
            {
             before(grammarAccess.getValueEAccess().getValueEAction_0()); 
            // InternalEquationDSL.g:3052:2: ()
            // InternalEquationDSL.g:3052:3: 
            {
            }

             after(grammarAccess.getValueEAccess().getValueEAction_0()); 

            }


            }

        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ValueE__Group__0__Impl"


    // $ANTLR start "rule__ValueE__Group__1"
    // InternalEquationDSL.g:3060:1: rule__ValueE__Group__1 : rule__ValueE__Group__1__Impl ;
    public final void rule__ValueE__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalEquationDSL.g:3064:1: ( rule__ValueE__Group__1__Impl )
            // InternalEquationDSL.g:3065:2: rule__ValueE__Group__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__ValueE__Group__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ValueE__Group__1"


    // $ANTLR start "rule__ValueE__Group__1__Impl"
    // InternalEquationDSL.g:3071:1: rule__ValueE__Group__1__Impl : ( 'e' ) ;
    public final void rule__ValueE__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalEquationDSL.g:3075:1: ( ( 'e' ) )
            // InternalEquationDSL.g:3076:1: ( 'e' )
            {
            // InternalEquationDSL.g:3076:1: ( 'e' )
            // InternalEquationDSL.g:3077:2: 'e'
            {
             before(grammarAccess.getValueEAccess().getEKeyword_1()); 
            match(input,39,FOLLOW_2); 
             after(grammarAccess.getValueEAccess().getEKeyword_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ValueE__Group__1__Impl"


    // $ANTLR start "rule__EquationSection__ImportsAssignment_0"
    // InternalEquationDSL.g:3087:1: rule__EquationSection__ImportsAssignment_0 : ( ruleImport ) ;
    public final void rule__EquationSection__ImportsAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalEquationDSL.g:3091:1: ( ( ruleImport ) )
            // InternalEquationDSL.g:3092:2: ( ruleImport )
            {
            // InternalEquationDSL.g:3092:2: ( ruleImport )
            // InternalEquationDSL.g:3093:3: ruleImport
            {
             before(grammarAccess.getEquationSectionAccess().getImportsImportParserRuleCall_0_0()); 
            pushFollow(FOLLOW_2);
            ruleImport();

            state._fsp--;

             after(grammarAccess.getEquationSectionAccess().getImportsImportParserRuleCall_0_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__EquationSection__ImportsAssignment_0"


    // $ANTLR start "rule__EquationSection__EquationsAssignment_1"
    // InternalEquationDSL.g:3102:1: rule__EquationSection__EquationsAssignment_1 : ( ruleEquation ) ;
    public final void rule__EquationSection__EquationsAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalEquationDSL.g:3106:1: ( ( ruleEquation ) )
            // InternalEquationDSL.g:3107:2: ( ruleEquation )
            {
            // InternalEquationDSL.g:3107:2: ( ruleEquation )
            // InternalEquationDSL.g:3108:3: ruleEquation
            {
             before(grammarAccess.getEquationSectionAccess().getEquationsEquationParserRuleCall_1_0()); 
            pushFollow(FOLLOW_2);
            ruleEquation();

            state._fsp--;

             after(grammarAccess.getEquationSectionAccess().getEquationsEquationParserRuleCall_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__EquationSection__EquationsAssignment_1"


    // $ANTLR start "rule__Import__ImportedNamespaceAssignment_1"
    // InternalEquationDSL.g:3117:1: rule__Import__ImportedNamespaceAssignment_1 : ( ( ruleQualifiedName ) ) ;
    public final void rule__Import__ImportedNamespaceAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalEquationDSL.g:3121:1: ( ( ( ruleQualifiedName ) ) )
            // InternalEquationDSL.g:3122:2: ( ( ruleQualifiedName ) )
            {
            // InternalEquationDSL.g:3122:2: ( ( ruleQualifiedName ) )
            // InternalEquationDSL.g:3123:3: ( ruleQualifiedName )
            {
             before(grammarAccess.getImportAccess().getImportedNamespaceIInstanceCrossReference_1_0()); 
            // InternalEquationDSL.g:3124:3: ( ruleQualifiedName )
            // InternalEquationDSL.g:3125:4: ruleQualifiedName
            {
             before(grammarAccess.getImportAccess().getImportedNamespaceIInstanceQualifiedNameParserRuleCall_1_0_1()); 
            pushFollow(FOLLOW_2);
            ruleQualifiedName();

            state._fsp--;

             after(grammarAccess.getImportAccess().getImportedNamespaceIInstanceQualifiedNameParserRuleCall_1_0_1()); 

            }

             after(grammarAccess.getImportAccess().getImportedNamespaceIInstanceCrossReference_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Import__ImportedNamespaceAssignment_1"


    // $ANTLR start "rule__Equation__ResultAssignment_0"
    // InternalEquationDSL.g:3136:1: rule__Equation__ResultAssignment_0 : ( ruleEquationResult ) ;
    public final void rule__Equation__ResultAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalEquationDSL.g:3140:1: ( ( ruleEquationResult ) )
            // InternalEquationDSL.g:3141:2: ( ruleEquationResult )
            {
            // InternalEquationDSL.g:3141:2: ( ruleEquationResult )
            // InternalEquationDSL.g:3142:3: ruleEquationResult
            {
             before(grammarAccess.getEquationAccess().getResultEquationResultParserRuleCall_0_0()); 
            pushFollow(FOLLOW_2);
            ruleEquationResult();

            state._fsp--;

             after(grammarAccess.getEquationAccess().getResultEquationResultParserRuleCall_0_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Equation__ResultAssignment_0"


    // $ANTLR start "rule__Equation__ExpressionAssignment_2"
    // InternalEquationDSL.g:3151:1: rule__Equation__ExpressionAssignment_2 : ( ruleAdditionAndSubtraction ) ;
    public final void rule__Equation__ExpressionAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalEquationDSL.g:3155:1: ( ( ruleAdditionAndSubtraction ) )
            // InternalEquationDSL.g:3156:2: ( ruleAdditionAndSubtraction )
            {
            // InternalEquationDSL.g:3156:2: ( ruleAdditionAndSubtraction )
            // InternalEquationDSL.g:3157:3: ruleAdditionAndSubtraction
            {
             before(grammarAccess.getEquationAccess().getExpressionAdditionAndSubtractionParserRuleCall_2_0()); 
            pushFollow(FOLLOW_2);
            ruleAdditionAndSubtraction();

            state._fsp--;

             after(grammarAccess.getEquationAccess().getExpressionAdditionAndSubtractionParserRuleCall_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Equation__ExpressionAssignment_2"


    // $ANTLR start "rule__TypeInstanceResult__ReferenceAssignment_2"
    // InternalEquationDSL.g:3166:1: rule__TypeInstanceResult__ReferenceAssignment_2 : ( ( ruleQualifiedName ) ) ;
    public final void rule__TypeInstanceResult__ReferenceAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalEquationDSL.g:3170:1: ( ( ( ruleQualifiedName ) ) )
            // InternalEquationDSL.g:3171:2: ( ( ruleQualifiedName ) )
            {
            // InternalEquationDSL.g:3171:2: ( ( ruleQualifiedName ) )
            // InternalEquationDSL.g:3172:3: ( ruleQualifiedName )
            {
             before(grammarAccess.getTypeInstanceResultAccess().getReferenceATypeInstanceCrossReference_2_0()); 
            // InternalEquationDSL.g:3173:3: ( ruleQualifiedName )
            // InternalEquationDSL.g:3174:4: ruleQualifiedName
            {
             before(grammarAccess.getTypeInstanceResultAccess().getReferenceATypeInstanceQualifiedNameParserRuleCall_2_0_1()); 
            pushFollow(FOLLOW_2);
            ruleQualifiedName();

            state._fsp--;

             after(grammarAccess.getTypeInstanceResultAccess().getReferenceATypeInstanceQualifiedNameParserRuleCall_2_0_1()); 

            }

             after(grammarAccess.getTypeInstanceResultAccess().getReferenceATypeInstanceCrossReference_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__TypeInstanceResult__ReferenceAssignment_2"


    // $ANTLR start "rule__EquationIntermediateResult__NameAssignment_2"
    // InternalEquationDSL.g:3185:1: rule__EquationIntermediateResult__NameAssignment_2 : ( RULE_ID ) ;
    public final void rule__EquationIntermediateResult__NameAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalEquationDSL.g:3189:1: ( ( RULE_ID ) )
            // InternalEquationDSL.g:3190:2: ( RULE_ID )
            {
            // InternalEquationDSL.g:3190:2: ( RULE_ID )
            // InternalEquationDSL.g:3191:3: RULE_ID
            {
             before(grammarAccess.getEquationIntermediateResultAccess().getNameIDTerminalRuleCall_2_0()); 
            match(input,RULE_ID,FOLLOW_2); 
             after(grammarAccess.getEquationIntermediateResultAccess().getNameIDTerminalRuleCall_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__EquationIntermediateResult__NameAssignment_2"


    // $ANTLR start "rule__AdditionAndSubtraction__OperatorAssignment_1_1"
    // InternalEquationDSL.g:3200:1: rule__AdditionAndSubtraction__OperatorAssignment_1_1 : ( ( rule__AdditionAndSubtraction__OperatorAlternatives_1_1_0 ) ) ;
    public final void rule__AdditionAndSubtraction__OperatorAssignment_1_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalEquationDSL.g:3204:1: ( ( ( rule__AdditionAndSubtraction__OperatorAlternatives_1_1_0 ) ) )
            // InternalEquationDSL.g:3205:2: ( ( rule__AdditionAndSubtraction__OperatorAlternatives_1_1_0 ) )
            {
            // InternalEquationDSL.g:3205:2: ( ( rule__AdditionAndSubtraction__OperatorAlternatives_1_1_0 ) )
            // InternalEquationDSL.g:3206:3: ( rule__AdditionAndSubtraction__OperatorAlternatives_1_1_0 )
            {
             before(grammarAccess.getAdditionAndSubtractionAccess().getOperatorAlternatives_1_1_0()); 
            // InternalEquationDSL.g:3207:3: ( rule__AdditionAndSubtraction__OperatorAlternatives_1_1_0 )
            // InternalEquationDSL.g:3207:4: rule__AdditionAndSubtraction__OperatorAlternatives_1_1_0
            {
            pushFollow(FOLLOW_2);
            rule__AdditionAndSubtraction__OperatorAlternatives_1_1_0();

            state._fsp--;


            }

             after(grammarAccess.getAdditionAndSubtractionAccess().getOperatorAlternatives_1_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__AdditionAndSubtraction__OperatorAssignment_1_1"


    // $ANTLR start "rule__AdditionAndSubtraction__RightAssignment_1_2"
    // InternalEquationDSL.g:3215:1: rule__AdditionAndSubtraction__RightAssignment_1_2 : ( ruleMultiplicationAndDivision ) ;
    public final void rule__AdditionAndSubtraction__RightAssignment_1_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalEquationDSL.g:3219:1: ( ( ruleMultiplicationAndDivision ) )
            // InternalEquationDSL.g:3220:2: ( ruleMultiplicationAndDivision )
            {
            // InternalEquationDSL.g:3220:2: ( ruleMultiplicationAndDivision )
            // InternalEquationDSL.g:3221:3: ruleMultiplicationAndDivision
            {
             before(grammarAccess.getAdditionAndSubtractionAccess().getRightMultiplicationAndDivisionParserRuleCall_1_2_0()); 
            pushFollow(FOLLOW_2);
            ruleMultiplicationAndDivision();

            state._fsp--;

             after(grammarAccess.getAdditionAndSubtractionAccess().getRightMultiplicationAndDivisionParserRuleCall_1_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__AdditionAndSubtraction__RightAssignment_1_2"


    // $ANTLR start "rule__MultiplicationAndDivision__OperatorAssignment_1_1"
    // InternalEquationDSL.g:3230:1: rule__MultiplicationAndDivision__OperatorAssignment_1_1 : ( ( rule__MultiplicationAndDivision__OperatorAlternatives_1_1_0 ) ) ;
    public final void rule__MultiplicationAndDivision__OperatorAssignment_1_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalEquationDSL.g:3234:1: ( ( ( rule__MultiplicationAndDivision__OperatorAlternatives_1_1_0 ) ) )
            // InternalEquationDSL.g:3235:2: ( ( rule__MultiplicationAndDivision__OperatorAlternatives_1_1_0 ) )
            {
            // InternalEquationDSL.g:3235:2: ( ( rule__MultiplicationAndDivision__OperatorAlternatives_1_1_0 ) )
            // InternalEquationDSL.g:3236:3: ( rule__MultiplicationAndDivision__OperatorAlternatives_1_1_0 )
            {
             before(grammarAccess.getMultiplicationAndDivisionAccess().getOperatorAlternatives_1_1_0()); 
            // InternalEquationDSL.g:3237:3: ( rule__MultiplicationAndDivision__OperatorAlternatives_1_1_0 )
            // InternalEquationDSL.g:3237:4: rule__MultiplicationAndDivision__OperatorAlternatives_1_1_0
            {
            pushFollow(FOLLOW_2);
            rule__MultiplicationAndDivision__OperatorAlternatives_1_1_0();

            state._fsp--;


            }

             after(grammarAccess.getMultiplicationAndDivisionAccess().getOperatorAlternatives_1_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__MultiplicationAndDivision__OperatorAssignment_1_1"


    // $ANTLR start "rule__MultiplicationAndDivision__RightAssignment_1_2"
    // InternalEquationDSL.g:3245:1: rule__MultiplicationAndDivision__RightAssignment_1_2 : ( rulePowerFunction ) ;
    public final void rule__MultiplicationAndDivision__RightAssignment_1_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalEquationDSL.g:3249:1: ( ( rulePowerFunction ) )
            // InternalEquationDSL.g:3250:2: ( rulePowerFunction )
            {
            // InternalEquationDSL.g:3250:2: ( rulePowerFunction )
            // InternalEquationDSL.g:3251:3: rulePowerFunction
            {
             before(grammarAccess.getMultiplicationAndDivisionAccess().getRightPowerFunctionParserRuleCall_1_2_0()); 
            pushFollow(FOLLOW_2);
            rulePowerFunction();

            state._fsp--;

             after(grammarAccess.getMultiplicationAndDivisionAccess().getRightPowerFunctionParserRuleCall_1_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__MultiplicationAndDivision__RightAssignment_1_2"


    // $ANTLR start "rule__PowerFunction__OperatorAssignment_1_1"
    // InternalEquationDSL.g:3260:1: rule__PowerFunction__OperatorAssignment_1_1 : ( ruleOperatorPower ) ;
    public final void rule__PowerFunction__OperatorAssignment_1_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalEquationDSL.g:3264:1: ( ( ruleOperatorPower ) )
            // InternalEquationDSL.g:3265:2: ( ruleOperatorPower )
            {
            // InternalEquationDSL.g:3265:2: ( ruleOperatorPower )
            // InternalEquationDSL.g:3266:3: ruleOperatorPower
            {
             before(grammarAccess.getPowerFunctionAccess().getOperatorOperatorPowerEnumRuleCall_1_1_0()); 
            pushFollow(FOLLOW_2);
            ruleOperatorPower();

            state._fsp--;

             after(grammarAccess.getPowerFunctionAccess().getOperatorOperatorPowerEnumRuleCall_1_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__PowerFunction__OperatorAssignment_1_1"


    // $ANTLR start "rule__PowerFunction__RightAssignment_1_2"
    // InternalEquationDSL.g:3275:1: rule__PowerFunction__RightAssignment_1_2 : ( ruleAExpression ) ;
    public final void rule__PowerFunction__RightAssignment_1_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalEquationDSL.g:3279:1: ( ( ruleAExpression ) )
            // InternalEquationDSL.g:3280:2: ( ruleAExpression )
            {
            // InternalEquationDSL.g:3280:2: ( ruleAExpression )
            // InternalEquationDSL.g:3281:3: ruleAExpression
            {
             before(grammarAccess.getPowerFunctionAccess().getRightAExpressionParserRuleCall_1_2_0()); 
            pushFollow(FOLLOW_2);
            ruleAExpression();

            state._fsp--;

             after(grammarAccess.getPowerFunctionAccess().getRightAExpressionParserRuleCall_1_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__PowerFunction__RightAssignment_1_2"


    // $ANTLR start "rule__Parenthesis__OperatorAssignment_1"
    // InternalEquationDSL.g:3290:1: rule__Parenthesis__OperatorAssignment_1 : ( ruleOperatorMinus ) ;
    public final void rule__Parenthesis__OperatorAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalEquationDSL.g:3294:1: ( ( ruleOperatorMinus ) )
            // InternalEquationDSL.g:3295:2: ( ruleOperatorMinus )
            {
            // InternalEquationDSL.g:3295:2: ( ruleOperatorMinus )
            // InternalEquationDSL.g:3296:3: ruleOperatorMinus
            {
             before(grammarAccess.getParenthesisAccess().getOperatorOperatorMinusEnumRuleCall_1_0()); 
            pushFollow(FOLLOW_2);
            ruleOperatorMinus();

            state._fsp--;

             after(grammarAccess.getParenthesisAccess().getOperatorOperatorMinusEnumRuleCall_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Parenthesis__OperatorAssignment_1"


    // $ANTLR start "rule__Parenthesis__RightAssignment_3"
    // InternalEquationDSL.g:3305:1: rule__Parenthesis__RightAssignment_3 : ( ruleAdditionAndSubtraction ) ;
    public final void rule__Parenthesis__RightAssignment_3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalEquationDSL.g:3309:1: ( ( ruleAdditionAndSubtraction ) )
            // InternalEquationDSL.g:3310:2: ( ruleAdditionAndSubtraction )
            {
            // InternalEquationDSL.g:3310:2: ( ruleAdditionAndSubtraction )
            // InternalEquationDSL.g:3311:3: ruleAdditionAndSubtraction
            {
             before(grammarAccess.getParenthesisAccess().getRightAdditionAndSubtractionParserRuleCall_3_0()); 
            pushFollow(FOLLOW_2);
            ruleAdditionAndSubtraction();

            state._fsp--;

             after(grammarAccess.getParenthesisAccess().getRightAdditionAndSubtractionParserRuleCall_3_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Parenthesis__RightAssignment_3"


    // $ANTLR start "rule__ReferencedInput__ReferenceAssignment"
    // InternalEquationDSL.g:3320:1: rule__ReferencedInput__ReferenceAssignment : ( ( ruleQualifiedName ) ) ;
    public final void rule__ReferencedInput__ReferenceAssignment() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalEquationDSL.g:3324:1: ( ( ( ruleQualifiedName ) ) )
            // InternalEquationDSL.g:3325:2: ( ( ruleQualifiedName ) )
            {
            // InternalEquationDSL.g:3325:2: ( ( ruleQualifiedName ) )
            // InternalEquationDSL.g:3326:3: ( ruleQualifiedName )
            {
             before(grammarAccess.getReferencedInputAccess().getReferenceIEquationInputCrossReference_0()); 
            // InternalEquationDSL.g:3327:3: ( ruleQualifiedName )
            // InternalEquationDSL.g:3328:4: ruleQualifiedName
            {
             before(grammarAccess.getReferencedInputAccess().getReferenceIEquationInputQualifiedNameParserRuleCall_0_1()); 
            pushFollow(FOLLOW_2);
            ruleQualifiedName();

            state._fsp--;

             after(grammarAccess.getReferencedInputAccess().getReferenceIEquationInputQualifiedNameParserRuleCall_0_1()); 

            }

             after(grammarAccess.getReferencedInputAccess().getReferenceIEquationInputCrossReference_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ReferencedInput__ReferenceAssignment"


    // $ANTLR start "rule__Function__OperatorAssignment_1"
    // InternalEquationDSL.g:3339:1: rule__Function__OperatorAssignment_1 : ( ( rule__Function__OperatorAlternatives_1_0 ) ) ;
    public final void rule__Function__OperatorAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalEquationDSL.g:3343:1: ( ( ( rule__Function__OperatorAlternatives_1_0 ) ) )
            // InternalEquationDSL.g:3344:2: ( ( rule__Function__OperatorAlternatives_1_0 ) )
            {
            // InternalEquationDSL.g:3344:2: ( ( rule__Function__OperatorAlternatives_1_0 ) )
            // InternalEquationDSL.g:3345:3: ( rule__Function__OperatorAlternatives_1_0 )
            {
             before(grammarAccess.getFunctionAccess().getOperatorAlternatives_1_0()); 
            // InternalEquationDSL.g:3346:3: ( rule__Function__OperatorAlternatives_1_0 )
            // InternalEquationDSL.g:3346:4: rule__Function__OperatorAlternatives_1_0
            {
            pushFollow(FOLLOW_2);
            rule__Function__OperatorAlternatives_1_0();

            state._fsp--;


            }

             after(grammarAccess.getFunctionAccess().getOperatorAlternatives_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Function__OperatorAssignment_1"


    // $ANTLR start "rule__Function__RightAssignment_3"
    // InternalEquationDSL.g:3354:1: rule__Function__RightAssignment_3 : ( ruleAdditionAndSubtraction ) ;
    public final void rule__Function__RightAssignment_3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalEquationDSL.g:3358:1: ( ( ruleAdditionAndSubtraction ) )
            // InternalEquationDSL.g:3359:2: ( ruleAdditionAndSubtraction )
            {
            // InternalEquationDSL.g:3359:2: ( ruleAdditionAndSubtraction )
            // InternalEquationDSL.g:3360:3: ruleAdditionAndSubtraction
            {
             before(grammarAccess.getFunctionAccess().getRightAdditionAndSubtractionParserRuleCall_3_0()); 
            pushFollow(FOLLOW_2);
            ruleAdditionAndSubtraction();

            state._fsp--;

             after(grammarAccess.getFunctionAccess().getRightAdditionAndSubtractionParserRuleCall_3_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Function__RightAssignment_3"


    // $ANTLR start "rule__AdvancedFunction__OperatorAssignment_1"
    // InternalEquationDSL.g:3369:1: rule__AdvancedFunction__OperatorAssignment_1 : ( RULE_ID ) ;
    public final void rule__AdvancedFunction__OperatorAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalEquationDSL.g:3373:1: ( ( RULE_ID ) )
            // InternalEquationDSL.g:3374:2: ( RULE_ID )
            {
            // InternalEquationDSL.g:3374:2: ( RULE_ID )
            // InternalEquationDSL.g:3375:3: RULE_ID
            {
             before(grammarAccess.getAdvancedFunctionAccess().getOperatorIDTerminalRuleCall_1_0()); 
            match(input,RULE_ID,FOLLOW_2); 
             after(grammarAccess.getAdvancedFunctionAccess().getOperatorIDTerminalRuleCall_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__AdvancedFunction__OperatorAssignment_1"


    // $ANTLR start "rule__AdvancedFunction__InputsAssignment_3"
    // InternalEquationDSL.g:3384:1: rule__AdvancedFunction__InputsAssignment_3 : ( ruleAdditionAndSubtraction ) ;
    public final void rule__AdvancedFunction__InputsAssignment_3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalEquationDSL.g:3388:1: ( ( ruleAdditionAndSubtraction ) )
            // InternalEquationDSL.g:3389:2: ( ruleAdditionAndSubtraction )
            {
            // InternalEquationDSL.g:3389:2: ( ruleAdditionAndSubtraction )
            // InternalEquationDSL.g:3390:3: ruleAdditionAndSubtraction
            {
             before(grammarAccess.getAdvancedFunctionAccess().getInputsAdditionAndSubtractionParserRuleCall_3_0()); 
            pushFollow(FOLLOW_2);
            ruleAdditionAndSubtraction();

            state._fsp--;

             after(grammarAccess.getAdvancedFunctionAccess().getInputsAdditionAndSubtractionParserRuleCall_3_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__AdvancedFunction__InputsAssignment_3"


    // $ANTLR start "rule__AdvancedFunction__InputsAssignment_4_1"
    // InternalEquationDSL.g:3399:1: rule__AdvancedFunction__InputsAssignment_4_1 : ( ruleAdditionAndSubtraction ) ;
    public final void rule__AdvancedFunction__InputsAssignment_4_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalEquationDSL.g:3403:1: ( ( ruleAdditionAndSubtraction ) )
            // InternalEquationDSL.g:3404:2: ( ruleAdditionAndSubtraction )
            {
            // InternalEquationDSL.g:3404:2: ( ruleAdditionAndSubtraction )
            // InternalEquationDSL.g:3405:3: ruleAdditionAndSubtraction
            {
             before(grammarAccess.getAdvancedFunctionAccess().getInputsAdditionAndSubtractionParserRuleCall_4_1_0()); 
            pushFollow(FOLLOW_2);
            ruleAdditionAndSubtraction();

            state._fsp--;

             after(grammarAccess.getAdvancedFunctionAccess().getInputsAdditionAndSubtractionParserRuleCall_4_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__AdvancedFunction__InputsAssignment_4_1"


    // $ANTLR start "rule__SetFunction__OperatorAssignment_1"
    // InternalEquationDSL.g:3414:1: rule__SetFunction__OperatorAssignment_1 : ( RULE_ID ) ;
    public final void rule__SetFunction__OperatorAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalEquationDSL.g:3418:1: ( ( RULE_ID ) )
            // InternalEquationDSL.g:3419:2: ( RULE_ID )
            {
            // InternalEquationDSL.g:3419:2: ( RULE_ID )
            // InternalEquationDSL.g:3420:3: RULE_ID
            {
             before(grammarAccess.getSetFunctionAccess().getOperatorIDTerminalRuleCall_1_0()); 
            match(input,RULE_ID,FOLLOW_2); 
             after(grammarAccess.getSetFunctionAccess().getOperatorIDTerminalRuleCall_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__SetFunction__OperatorAssignment_1"


    // $ANTLR start "rule__SetFunction__TypeDefinitionAssignment_3"
    // InternalEquationDSL.g:3429:1: rule__SetFunction__TypeDefinitionAssignment_3 : ( ( ruleQualifiedName ) ) ;
    public final void rule__SetFunction__TypeDefinitionAssignment_3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalEquationDSL.g:3433:1: ( ( ( ruleQualifiedName ) ) )
            // InternalEquationDSL.g:3434:2: ( ( ruleQualifiedName ) )
            {
            // InternalEquationDSL.g:3434:2: ( ( ruleQualifiedName ) )
            // InternalEquationDSL.g:3435:3: ( ruleQualifiedName )
            {
             before(grammarAccess.getSetFunctionAccess().getTypeDefinitionATypeDefinitionCrossReference_3_0()); 
            // InternalEquationDSL.g:3436:3: ( ruleQualifiedName )
            // InternalEquationDSL.g:3437:4: ruleQualifiedName
            {
             before(grammarAccess.getSetFunctionAccess().getTypeDefinitionATypeDefinitionQualifiedNameParserRuleCall_3_0_1()); 
            pushFollow(FOLLOW_2);
            ruleQualifiedName();

            state._fsp--;

             after(grammarAccess.getSetFunctionAccess().getTypeDefinitionATypeDefinitionQualifiedNameParserRuleCall_3_0_1()); 

            }

             after(grammarAccess.getSetFunctionAccess().getTypeDefinitionATypeDefinitionCrossReference_3_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__SetFunction__TypeDefinitionAssignment_3"


    // $ANTLR start "rule__SetFunction__DepthAssignment_4_1"
    // InternalEquationDSL.g:3448:1: rule__SetFunction__DepthAssignment_4_1 : ( RULE_INT ) ;
    public final void rule__SetFunction__DepthAssignment_4_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalEquationDSL.g:3452:1: ( ( RULE_INT ) )
            // InternalEquationDSL.g:3453:2: ( RULE_INT )
            {
            // InternalEquationDSL.g:3453:2: ( RULE_INT )
            // InternalEquationDSL.g:3454:3: RULE_INT
            {
             before(grammarAccess.getSetFunctionAccess().getDepthINTTerminalRuleCall_4_1_0()); 
            match(input,RULE_INT,FOLLOW_2); 
             after(grammarAccess.getSetFunctionAccess().getDepthINTTerminalRuleCall_4_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__SetFunction__DepthAssignment_4_1"


    // $ANTLR start "rule__SetFunction__FilterNameAssignment_5_1"
    // InternalEquationDSL.g:3463:1: rule__SetFunction__FilterNameAssignment_5_1 : ( RULE_ID ) ;
    public final void rule__SetFunction__FilterNameAssignment_5_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalEquationDSL.g:3467:1: ( ( RULE_ID ) )
            // InternalEquationDSL.g:3468:2: ( RULE_ID )
            {
            // InternalEquationDSL.g:3468:2: ( RULE_ID )
            // InternalEquationDSL.g:3469:3: RULE_ID
            {
             before(grammarAccess.getSetFunctionAccess().getFilterNameIDTerminalRuleCall_5_1_0()); 
            match(input,RULE_ID,FOLLOW_2); 
             after(grammarAccess.getSetFunctionAccess().getFilterNameIDTerminalRuleCall_5_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__SetFunction__FilterNameAssignment_5_1"


    // $ANTLR start "rule__NumberLiteral__ValueAssignment_1"
    // InternalEquationDSL.g:3478:1: rule__NumberLiteral__ValueAssignment_1 : ( ruleNumberLiteralString ) ;
    public final void rule__NumberLiteral__ValueAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalEquationDSL.g:3482:1: ( ( ruleNumberLiteralString ) )
            // InternalEquationDSL.g:3483:2: ( ruleNumberLiteralString )
            {
            // InternalEquationDSL.g:3483:2: ( ruleNumberLiteralString )
            // InternalEquationDSL.g:3484:3: ruleNumberLiteralString
            {
             before(grammarAccess.getNumberLiteralAccess().getValueNumberLiteralStringParserRuleCall_1_0()); 
            pushFollow(FOLLOW_2);
            ruleNumberLiteralString();

            state._fsp--;

             after(grammarAccess.getNumberLiteralAccess().getValueNumberLiteralStringParserRuleCall_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__NumberLiteral__ValueAssignment_1"

    // Delegated rules


 

    public static final BitSet FOLLOW_1 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_2 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_3 = new BitSet(new long[]{0x00000000C0000000L});
    public static final BitSet FOLLOW_4 = new BitSet(new long[]{0x0000000008000002L});
    public static final BitSet FOLLOW_5 = new BitSet(new long[]{0x00000000C0000002L});
    public static final BitSet FOLLOW_6 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_7 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_8 = new BitSet(new long[]{0x0000000020000000L});
    public static final BitSet FOLLOW_9 = new BitSet(new long[]{0x000000C107FF1030L});
    public static final BitSet FOLLOW_10 = new BitSet(new long[]{0x0000000040000000L});
    public static final BitSet FOLLOW_11 = new BitSet(new long[]{0x0000000000001800L});
    public static final BitSet FOLLOW_12 = new BitSet(new long[]{0x0000000000001802L});
    public static final BitSet FOLLOW_13 = new BitSet(new long[]{0x0000000000006000L});
    public static final BitSet FOLLOW_14 = new BitSet(new long[]{0x0000000000006002L});
    public static final BitSet FOLLOW_15 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_16 = new BitSet(new long[]{0x0000000000008002L});
    public static final BitSet FOLLOW_17 = new BitSet(new long[]{0x0000000100001000L});
    public static final BitSet FOLLOW_18 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_19 = new BitSet(new long[]{0x0000000007FF0000L});
    public static final BitSet FOLLOW_20 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_21 = new BitSet(new long[]{0x0000000600000000L});
    public static final BitSet FOLLOW_22 = new BitSet(new long[]{0x0000000400000002L});
    public static final BitSet FOLLOW_23 = new BitSet(new long[]{0x0000000800000000L});
    public static final BitSet FOLLOW_24 = new BitSet(new long[]{0x0000001400000000L});
    public static final BitSet FOLLOW_25 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_26 = new BitSet(new long[]{0x0000000000001020L});
    public static final BitSet FOLLOW_27 = new BitSet(new long[]{0x0000002000000000L});
    public static final BitSet FOLLOW_28 = new BitSet(new long[]{0x0000002000000002L});
    public static final BitSet FOLLOW_29 = new BitSet(new long[]{0x000000C000001020L});
    public static final BitSet FOLLOW_30 = new BitSet(new long[]{0x0000008000000000L});

}