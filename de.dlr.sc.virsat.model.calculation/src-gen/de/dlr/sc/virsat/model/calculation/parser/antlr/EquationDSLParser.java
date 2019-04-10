/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.calculation.parser.antlr;

import com.google.inject.Inject;
import de.dlr.sc.virsat.model.calculation.parser.antlr.internal.InternalEquationDSLParser;
import de.dlr.sc.virsat.model.calculation.services.EquationDSLGrammarAccess;
import org.eclipse.xtext.parser.antlr.AbstractAntlrParser;
import org.eclipse.xtext.parser.antlr.XtextTokenStream;

public class EquationDSLParser extends AbstractAntlrParser {

	@Inject
	private EquationDSLGrammarAccess grammarAccess;

	@Override
	protected void setInitialHiddenTokens(XtextTokenStream tokenStream) {
		tokenStream.setInitialHiddenTokens("RULE_WS", "RULE_ML_COMMENT", "RULE_SL_COMMENT");
	}
	

	@Override
	protected InternalEquationDSLParser createParser(XtextTokenStream stream) {
		return new InternalEquationDSLParser(stream, getGrammarAccess());
	}

	@Override 
	protected String getDefaultRuleName() {
		return "EquationSection";
	}

	public EquationDSLGrammarAccess getGrammarAccess() {
		return this.grammarAccess;
	}

	public void setGrammarAccess(EquationDSLGrammarAccess grammarAccess) {
		this.grammarAccess = grammarAccess;
	}
}
