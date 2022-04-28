/**
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 * 
 * SPDX-License-Identifier: EPL-2.0
 */
package de.dlr.sc.virsat.model.concept.generator.snippets;

import de.dlr.sc.virsat.model.concept.ConceptLanguageTestInjectorProvider;
import de.dlr.sc.virsat.model.concept.generator.snippets.GenerateRequirementsVerificationUiSnippet;
import de.dlr.sc.virsat.model.concept.test.util.GeneratorJunitAssert;
import de.dlr.sc.virsat.model.dvlm.categories.Category;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.dvlm.concepts.ConceptsPackage;
import javax.inject.Inject;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.testing.InjectWith;
import org.eclipse.xtext.testing.XtextRunner;
import org.eclipse.xtext.testing.util.ParseHelper;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.StringExtensions;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(XtextRunner.class)
@InjectWith(ConceptLanguageTestInjectorProvider.class)
@SuppressWarnings("all")
public class GenerateRequirementsVerificationUiSnippetTest {
  @Inject
  @Extension
  private ParseHelper<Concept> _parseHelper;
  
  private Concept concept;
  
  private Category category;
  
  private Category verification;
  
  private final String testConceptName = "testConcept";
  
  private final String testCategoryName = "testCategory";
  
  private final GenerateRequirementsVerificationUiSnippet generator = new GenerateRequirementsVerificationUiSnippet();
  
  @Before
  public void setUp() {
    try {
      ConceptsPackage.eINSTANCE.eClass();
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("Concept ");
      _builder.append(this.testConceptName);
      _builder.append("{");
      _builder.newLineIfNotEmpty();
      _builder.append("\t");
      _builder.append("Category ");
      _builder.append(this.testCategoryName, "\t");
      _builder.append(" {");
      _builder.newLineIfNotEmpty();
      _builder.append("\t\t");
      _builder.append("FloatProperty testProp verification VerificationTest;");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("}");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("Category VerificationTest {");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("IsVerification;");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("}");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      this.concept = this._parseHelper.parse(_builder);
      this.category = this.concept.getCategories().get(0);
      this.verification = this.concept.getCategories().get(1);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void testCreateConcreteClassFileName() {
    final String fileName = this.generator.createConcreteClassFileName(this.concept, this.category);
    String _name = this.concept.getName();
    String _plus = ("../../" + _name);
    String _plus_1 = (_plus + ".ui/src/");
    String _name_1 = this.concept.getName();
    String _replace = (_name_1 + ".ui.snippet").replace(".", "/");
    String _plus_2 = (_plus_1 + _replace);
    String _plus_3 = (_plus_2 + "/");
    String _plus_4 = (_plus_3 + "UiSnippetTableRequirementVerifications");
    String _firstUpper = StringExtensions.toFirstUpper(this.category.getName());
    String _plus_5 = (_plus_4 + _firstUpper);
    final String expectedFileName = (_plus_5 + ".java");
    Assert.assertEquals("Concrete file name for the generated snippet is correct", expectedFileName, fileName);
  }
  
  @Test
  public void testCreateAbstractClassFileName() {
    final String fileName = this.generator.createAbstractClassFileName(this.concept, this.category);
    String _name = this.concept.getName();
    String _plus = ("../../" + _name);
    String _plus_1 = (_plus + ".ui/src-gen/");
    String _name_1 = this.concept.getName();
    String _replace = (_name_1 + ".ui.snippet").replace(".", "/");
    String _plus_2 = (_plus_1 + _replace);
    String _plus_3 = (_plus_2 + "/");
    String _plus_4 = (_plus_3 + "AUiSnippetTableRequirementVerifications");
    String _firstUpper = StringExtensions.toFirstUpper(this.category.getName());
    String _plus_5 = (_plus_4 + _firstUpper);
    final String expectedFileName = (_plus_5 + ".java");
    Assert.assertEquals("Abstract file name for the generated abstract snippet is correct", expectedFileName, fileName);
  }
  
  @Test
  public void testCreateVerification() {
    try {
      final CharSequence classContents = this.generator.createAbstractClass(this.concept, this.verification);
      GeneratorJunitAssert.assertEqualContent(classContents, "/resources/expectedOutputFilesForGenerators/AUiSnippetTableVerification.java");
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
}
