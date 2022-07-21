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
public class GenerateCategoryUiSnippetPropertySectionTest {
  @Inject
  @Extension
  private ParseHelper<Concept> _parseHelper;
  
  private Concept concept;
  
  private Category category;
  
  private final String testConceptName = "testConcept";
  
  private final String testCategoryName = "testCategory";
  
  private final GenerateCategoryUiSnippetPropertySection generator = new GenerateCategoryUiSnippetPropertySection();
  
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
      _builder.append(" {}");
      _builder.newLineIfNotEmpty();
      _builder.append("}");
      _builder.newLine();
      this.concept = this._parseHelper.parse(_builder);
      this.category = this.concept.getCategories().get(0);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void testCreateConcreteClassFileName() {
    final String fileName = this.generator.createConcreteClassFileName(this.concept, this.category);
    String _firstUpper = StringExtensions.toFirstUpper(this.testCategoryName);
    String _plus = ((((("../../" + this.testConceptName) + ".ui/src/") + this.testConceptName) + "/ui/snippet/UiSnippetSection") + _firstUpper);
    final String expectedFileName = (_plus + ".java");
    Assert.assertEquals("Concrete file name for the generated snippet is correct", expectedFileName, fileName);
  }
  
  @Test
  public void testCreateAbstractClassFileName() {
    final String fileName = this.generator.createAbstractClassFileName(this.concept, this.category);
    String _firstUpper = StringExtensions.toFirstUpper(this.testCategoryName);
    String _plus = ((((("../../" + this.testConceptName) + ".ui/src-gen/") + this.testConceptName) + "/ui/snippet/AUiSnippetSection") + _firstUpper);
    final String expectedFileName = (_plus + ".java");
    Assert.assertEquals("Abstract file name for the generated abstract snippet is correct", expectedFileName, fileName);
  }
  
  @Test
  public void testCreateConcreteClass() {
    try {
      final CharSequence classContents = this.generator.createConcreteClass(this.concept, this.category);
      GeneratorJunitAssert.assertEqualContent(classContents, "/resources/expectedOutputFilesForGenerators/UiSnippetPropertySection.java");
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void testCreateAbstractClass() {
    try {
      final CharSequence classContents = this.generator.createAbstractClass(this.concept, this.category);
      GeneratorJunitAssert.assertEqualContent(classContents, "/resources/expectedOutputFilesForGenerators/AUiSnippetPropertySection.java");
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
}
