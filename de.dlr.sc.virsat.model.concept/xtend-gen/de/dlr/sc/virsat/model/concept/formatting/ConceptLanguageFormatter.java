/**
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 * 
 * SPDX-License-Identifier: EPL-2.0
 */
package de.dlr.sc.virsat.model.concept.formatting;

import com.google.inject.Inject;
import de.dlr.sc.virsat.model.concept.services.ConceptLanguageGrammarAccess;
import java.util.List;
import org.eclipse.xtext.Keyword;
import org.eclipse.xtext.formatting.impl.AbstractDeclarativeFormatter;
import org.eclipse.xtext.formatting.impl.FormattingConfig;
import org.eclipse.xtext.util.Pair;
import org.eclipse.xtext.xbase.lib.Extension;

/**
 * This class contains custom formatting declarations.
 * 
 * See https://www.eclipse.org/Xtext/documentation/303_runtime_concepts.html#formatting
 * on how and when to use it.
 * 
 * Also see org.eclipse.xtext.xtext.XtextFormattingTokenSerializer as an example
 */
@SuppressWarnings("all")
public class ConceptLanguageFormatter extends AbstractDeclarativeFormatter {
  @Inject
  @Extension
  private ConceptLanguageGrammarAccess _conceptLanguageGrammarAccess;

  @Override
  protected void configureFormatting(final FormattingConfig c) {
    List<Pair<Keyword, Keyword>> _findKeywordPairs = this._conceptLanguageGrammarAccess.findKeywordPairs("{", "}");
    for (final Pair<Keyword, Keyword> pair : _findKeywordPairs) {
      {
        c.setIndentation(pair.getFirst(), pair.getSecond());
        c.setLinewrap(1).after(pair.getFirst());
        c.setLinewrap(1).before(pair.getSecond());
        c.setLinewrap(1).after(pair.getSecond());
      }
    }
    List<Keyword> _findKeywords = this._conceptLanguageGrammarAccess.findKeywords(",");
    for (final Keyword comma : _findKeywords) {
      {
        c.setNoLinewrap().before(comma);
        c.setNoSpace().before(comma);
        c.setLinewrap().after(comma);
      }
    }
    c.setLinewrap(0, 1, 2).before(this._conceptLanguageGrammarAccess.getSL_COMMENTRule());
    c.setLinewrap(0, 1, 2).before(this._conceptLanguageGrammarAccess.getML_COMMENTRule());
    c.setLinewrap(0, 1, 1).after(this._conceptLanguageGrammarAccess.getML_COMMENTRule());
  }
}
