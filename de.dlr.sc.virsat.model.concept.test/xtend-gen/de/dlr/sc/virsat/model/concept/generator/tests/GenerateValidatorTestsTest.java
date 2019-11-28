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
import de.dlr.sc.virsat.model.concept.generator.tests.GenerateValidatorTests;
import de.dlr.sc.virsat.model.concept.test.util.GeneratorJunitAssert;
import de.dlr.sc.virsat.model.dvlm.categories.CategoriesPackage;
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
public class GenerateValidatorTestsTest {
  @Inject
  @Extension
  private ParseHelper<Concept> _parseHelper;
  
  private Concept concept;
  
  private final String TEST_CONCEPT_NAME = "TestConcept";
  
  private final GenerateValidatorTests generateValidatorTests = new GenerateValidatorTests();
  
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
      _builder.append("}");
      _builder.newLine();
      this.concept = this._parseHelper.parse(_builder);
      final String concreteClassfileName = this.generateValidatorTests.createConcreteClassFileName(this.concept, this.concept);
      final String abstractClassfileName = this.generateValidatorTests.createAbstractClassFileName(this.concept, this.concept);
      final String expectedConcreteClassFileName = "../../TestConcept.test/src/TestConcept/validator/TestConceptValidatorTest.java";
      final String expectedAbstractClassFileName = "../../TestConcept.test/src-gen/TestConcept/validator/ATestConceptValidatorTest.java";
      Assert.assertEquals("Concrete file name for the generated create add command is correct", expectedConcreteClassFileName, concreteClassfileName);
      Assert.assertEquals("Concrete file name for the generated create add command is correct", expectedAbstractClassFileName, abstractClassfileName);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void testCreateForCategoryTests() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("Concept ");
      _builder.append(this.TEST_CONCEPT_NAME);
      _builder.append(" {");
      _builder.newLineIfNotEmpty();
      _builder.append("}");
      _builder.newLine();
      this.concept = this._parseHelper.parse(_builder);
      final CharSequence concreteClassContents = this.generateValidatorTests.createConcreteClass(this.concept, this.concept);
      final CharSequence abstractClassContents = this.generateValidatorTests.createAbstractClass(this.concept, this.concept);
      GeneratorJunitAssert.assertEqualContent(concreteClassContents, "/resources/expectedOutputFilesForGenerators/TestConceptValidatorTest.java");
      GeneratorJunitAssert.assertEqualContent(abstractClassContents, "/resources/expectedOutputFilesForGenerators/ATestConceptValidatorTest.java");
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
}
