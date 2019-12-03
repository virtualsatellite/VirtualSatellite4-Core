/**
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 * 
 * SPDX-License-Identifier: EPL-2.0
 */
package de.dlr.sc.virsat.model.concept.generator.tests;

import de.dlr.sc.virsat.model.concept.ConceptLanguageTestInjectorProvider;
import de.dlr.sc.virsat.model.concept.generator.tests.GenerateAllTests;
import de.dlr.sc.virsat.model.concept.test.util.GeneratorJunitAssert;
import de.dlr.sc.virsat.model.dvlm.categories.CategoriesPackage;
import de.dlr.sc.virsat.model.dvlm.categories.Category;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.PropertydefinitionsPackage;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.dvlm.concepts.ConceptsPackage;
import javax.inject.Inject;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.testing.InjectWith;
import org.eclipse.xtext.testing.XtextRunner;
import org.eclipse.xtext.testing.util.ParseHelper;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.Extension;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(XtextRunner.class)
@InjectWith(ConceptLanguageTestInjectorProvider.class)
@SuppressWarnings("all")
public class GenerateAllTestsTest {
  @Inject
  @Extension
  private ParseHelper<Concept> _parseHelper;
  
  private Concept concept;
  
  private final String TEST_CONCEPT_NAME = "de.dlr.sc.virsat.model.extension.testConcept";
  
  private final String TEST_CATEGORY_NAME = "testCategory";
  
  private final String TEST_STRUCTURAL_ELEMENT_NAME = "testStructuralElement";
  
  private final GenerateAllTests generateAllTests = new GenerateAllTests();
  
  @Before
  public void setUp() {
    ConceptsPackage.eINSTANCE.eClass();
    CategoriesPackage.eINSTANCE.eClass();
    PropertydefinitionsPackage.eINSTANCE.eClass();
  }
  
  @Test
  public void testCreateConcreteClassFileName() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("Concept ");
      _builder.append(this.TEST_CONCEPT_NAME);
      _builder.append(" {");
      _builder.newLineIfNotEmpty();
      _builder.append("\t");
      _builder.append("Category ");
      _builder.append(this.TEST_CATEGORY_NAME, "\t");
      _builder.append(" {");
      _builder.newLineIfNotEmpty();
      _builder.append("\t");
      _builder.append("}");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("StructuralElement ");
      _builder.append(this.TEST_STRUCTURAL_ELEMENT_NAME, "\t");
      _builder.append(" {");
      _builder.newLineIfNotEmpty();
      _builder.append("\t");
      _builder.append("}");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      this.concept = this._parseHelper.parse(_builder);
      final Category category = this.concept.getCategories().get(0);
      final String concreteClassfileName = this.generateAllTests.createConcreteClassFileName(this.concept, category);
      final String abstractClassfileName = this.generateAllTests.createAbstractClassFileName(this.concept, category);
      String _replace = this.TEST_CONCEPT_NAME.replace(".", "/");
      String _plus = ((("../../" + this.TEST_CONCEPT_NAME) + ".test/src/") + _replace);
      final String expectedConcreteClassFileName = (_plus + "/test/AllTests.java");
      String _replace_1 = this.TEST_CONCEPT_NAME.replace(".", "/");
      String _plus_1 = ((("../../" + this.TEST_CONCEPT_NAME) + ".test/src-gen/") + _replace_1);
      final String expectedAbstractClassFileName = (_plus_1 + "/test/AllTestsGen.java");
      Assert.assertEquals("Concrete file name for the generated create add command is correct", expectedConcreteClassFileName, concreteClassfileName);
      Assert.assertEquals("Abstract file name for the generated create add command is correct", expectedAbstractClassFileName, abstractClassfileName);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void testCreateForAllTestsClasses() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("Concept ");
      _builder.append(this.TEST_CONCEPT_NAME);
      _builder.append(" {");
      _builder.newLineIfNotEmpty();
      _builder.append("\t");
      _builder.append("Category ");
      _builder.append(this.TEST_CATEGORY_NAME, "\t");
      _builder.append(" {");
      _builder.newLineIfNotEmpty();
      _builder.append("\t");
      _builder.append("}");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("StructuralElement ");
      _builder.append(this.TEST_STRUCTURAL_ELEMENT_NAME, "\t");
      _builder.append(" {");
      _builder.newLineIfNotEmpty();
      _builder.append("\t");
      _builder.append("}");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      this.concept = this._parseHelper.parse(_builder);
      final Category category = this.concept.getCategories().get(0);
      final CharSequence concreteClassContents = this.generateAllTests.createConcreteClass(this.concept, category);
      final CharSequence abstractClassContents = this.generateAllTests.createAbstractClass(this.concept, category);
      GeneratorJunitAssert.assertEqualContent(concreteClassContents, "/resources/expectedOutputFilesForGenerators/AllTests.java");
      GeneratorJunitAssert.assertEqualContent(abstractClassContents, "/resources/expectedOutputFilesForGenerators/AllTestsGen.java");
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
}
