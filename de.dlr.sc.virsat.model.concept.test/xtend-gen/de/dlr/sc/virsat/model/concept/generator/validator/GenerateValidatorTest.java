/**
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 * 
 * SPDX-License-Identifier: EPL-2.0
 */
package de.dlr.sc.virsat.model.concept.generator.validator;

import de.dlr.sc.virsat.model.concept.ConceptLanguageTestInjectorProvider;
import de.dlr.sc.virsat.model.concept.generator.validator.GenerateValidator;
import de.dlr.sc.virsat.model.concept.test.util.GeneratorJunitAssert;
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
public class GenerateValidatorTest {
  @Inject
  @Extension
  private ParseHelper<Concept> _parseHelper;
  
  private Concept concept;
  
  private final String conceptID = "de.dlr.sc.virsat.model.extension.testConcept";
  
  private final String expectedConceptShortName = "TestConcept";
  
  private final GenerateValidator validatorGenerator = new GenerateValidator();
  
  @Before
  public void setUp() {
    try {
      ConceptsPackage.eINSTANCE.eClass();
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("Concept ");
      _builder.append(this.conceptID);
      _builder.append("{}");
      _builder.newLineIfNotEmpty();
      this.concept = this._parseHelper.parse(_builder);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void testGetValidatorName() {
    final String validatorName = GenerateValidator.getValidatorName(this.concept);
    final String expectedValidatorName = (this.expectedConceptShortName + "Validator");
    Assert.assertEquals("Validator name for the generated validator is correct", expectedValidatorName, validatorName);
  }
  
  @Test
  public void testCreateConcreteClassFileName() {
    final String fileName = this.validatorGenerator.createConcreteClassFileName(this.concept, this.concept);
    String _replace = this.conceptID.replace(".", "/");
    String _plus = (_replace + "/");
    String _plus_1 = (_plus + "validator");
    String _plus_2 = (_plus_1 + "/");
    String _plus_3 = (_plus_2 + this.expectedConceptShortName);
    final String expectedFileName = (_plus_3 + "Validator.java");
    Assert.assertEquals("Concrete file name for the generated validator is correct", expectedFileName, fileName);
  }
  
  @Test
  public void testCreateAbstractClassFileName() {
    final String fileName = this.validatorGenerator.createAbstractClassFileName(this.concept, this.concept);
    String _replace = this.conceptID.replace(".", "/");
    String _plus = (_replace + "/");
    String _plus_1 = (_plus + "validator");
    String _plus_2 = (_plus_1 + "/A");
    String _plus_3 = (_plus_2 + this.expectedConceptShortName);
    final String expectedFileName = (_plus_3 + "Validator.java");
    Assert.assertEquals("Abstract file name for the generated validator is correct", expectedFileName, fileName);
  }
  
  @Test
  public void testCreateConcreteClass() {
    try {
      final CharSequence classContents = this.validatorGenerator.createConcreteClass(this.concept, this.concept);
      GeneratorJunitAssert.assertEqualContent(classContents, "/resources/expectedOutputFilesForGenerators/TestConceptValidator.java");
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void testCreateAbstractClass() {
    try {
      final CharSequence classContents = this.validatorGenerator.createAbstractClass(this.concept, this.concept);
      GeneratorJunitAssert.assertEqualContent(classContents, "/resources/expectedOutputFilesForGenerators/ATestConceptValidator.java");
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
}
