/**
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 * 
 * SPDX-License-Identifier: EPL-2.0
 */
package de.dlr.sc.virsat.model.concept.generator.handler;

import com.google.inject.Inject;
import de.dlr.sc.virsat.model.concept.ConceptLanguageTestInjectorProvider;
import de.dlr.sc.virsat.model.concept.test.util.GeneratorJunitAssert;
import de.dlr.sc.virsat.model.dvlm.categories.Category;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.dvlm.concepts.ConceptsPackage;
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
public class GenerateCategoryAddHandlerTest {
  @Inject
  @Extension
  private ParseHelper<Concept> _parseHelper;

  private Concept concept;

  private Category category;

  private final String testConceptName = "testConcept";

  private final String testCategoryName = "testCategory";

  private final GenerateCategoryAddHandler categoryAddHandlerGenerator = new GenerateCategoryAddHandler();

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
    final String fileName = this.categoryAddHandlerGenerator.createConcreteClassFileName(this.concept, this.category);
    String _firstUpper = StringExtensions.toFirstUpper(this.testCategoryName);
    String _plus = ((((("../../" + this.testConceptName) + ".ui/src/") + this.testConceptName) + "/ui/handler/Add") + _firstUpper);
    final String expectedFileName = (_plus + "Handler.java");
    Assert.assertEquals("Concrete file name for the generated add handler is correct", expectedFileName, fileName);
  }

  @Test
  public void testCreateAbstractClassFileName() {
    final String fileName = this.categoryAddHandlerGenerator.createAbstractClassFileName(this.concept, this.category);
    String _firstUpper = StringExtensions.toFirstUpper(this.testCategoryName);
    String _plus = ((((("../../" + this.testConceptName) + ".ui/src-gen/") + this.testConceptName) + "/ui/handler/AAdd") + _firstUpper);
    final String expectedFileName = (_plus + "Handler.java");
    Assert.assertEquals("Abstract file name for the generated abstract add handler is correct", expectedFileName, fileName);
  }

  @Test
  public void testCreateConcreteClass() {
    try {
      final CharSequence classContents = this.categoryAddHandlerGenerator.createConcreteClass(this.concept, this.category);
      GeneratorJunitAssert.assertEqualContent(classContents, "/resources/expectedOutputFilesForGenerators/AddTestCategoryHandler.java");
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }

  @Test
  public void testCreateAbstractClass() {
    try {
      final CharSequence classContents = this.categoryAddHandlerGenerator.createAbstractClass(this.concept, this.category);
      GeneratorJunitAssert.assertEqualContent(classContents, "/resources/expectedOutputFilesForGenerators/AAddTestCategoryHandler.java");
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
}
