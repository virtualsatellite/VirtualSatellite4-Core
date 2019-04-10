/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.concept.parser.antlr;

import com.google.inject.Inject;
import de.dlr.sc.virsat.model.concept.parser.antlr.internal.InternalConceptLanguageParser;
import de.dlr.sc.virsat.model.concept.services.ConceptLanguageGrammarAccess;
import org.eclipse.xtext.parser.antlr.AbstractAntlrParser;
import org.eclipse.xtext.parser.antlr.XtextTokenStream;

public class ConceptLanguageParser extends AbstractAntlrParser {

	@Inject
	private ConceptLanguageGrammarAccess grammarAccess;

	@Override
	protected void setInitialHiddenTokens(XtextTokenStream tokenStream) {
		tokenStream.setInitialHiddenTokens("RULE_WS", "RULE_ML_COMMENT", "RULE_SL_COMMENT");
	}
	

	@Override
	protected InternalConceptLanguageParser createParser(XtextTokenStream stream) {
		return new InternalConceptLanguageParser(stream, getGrammarAccess());
	}

	@Override 
	protected String getDefaultRuleName() {
		return "Concept";
	}

	public ConceptLanguageGrammarAccess getGrammarAccess() {
		return this.grammarAccess;
	}

	public void setGrammarAccess(ConceptLanguageGrammarAccess grammarAccess) {
		this.grammarAccess = grammarAccess;
	}
}
