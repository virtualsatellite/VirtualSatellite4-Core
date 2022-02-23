/**
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 * 
 * SPDX-License-Identifier: EPL-2.0
 */
package de.dlr.sc.virsat.model.concept.generator.plugin;

import de.dlr.sc.virsat.model.concept.ConceptLanguageTestInjectorProvider;
import de.dlr.sc.virsat.model.concept.generator.plugin.GenerateUiPluginXml;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import javax.inject.Inject;
import org.eclipse.xtext.testing.InjectWith;
import org.eclipse.xtext.testing.XtextRunner;
import org.eclipse.xtext.testing.util.ParseHelper;
import org.eclipse.xtext.xbase.lib.Extension;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(XtextRunner.class)
@InjectWith(ConceptLanguageTestInjectorProvider.class)
@SuppressWarnings("all")
public class GenerateConceptUiPluginXmlTest {
  @Inject
  @Extension
  private ParseHelper<Concept> _parseHelper;
  
  private Concept concept;
  
  private final String TEST_CONCEPT_NAME = "testConcept";
  
  private final String TEST_CATEGORY_NAME = "testCategory";
  
  private final String TEST_STRUCTURAL_1_NAME = "testStructural1";
  
  private final String TEST_STRUCTURAL_2_NAME = "testStructural2";
  
  private final String TEST_EXTRACT_PROTECTED_REGION = "testExtractProtectedRegion";
  
  private final GenerateUiPluginXml pluginGenerator = new GenerateUiPluginXml();
  
  @Before
  public void setUp() {
    throw new Error("Unresolved compilation problems:"
      + "\nThe field pluginXmlReader is not visible");
  }
  
  @Test
  public void testCreateUiXml() {
    throw new Error("Unresolved compilation problems:"
      + "\nThe field pluginXmlReader is not visible");
  }
  
  @Test
  public void testCreateUiXmlVerifications() {
    throw new Error("Unresolved compilation problems:"
      + "\nThe field pluginXmlReader is not visible");
  }
}
