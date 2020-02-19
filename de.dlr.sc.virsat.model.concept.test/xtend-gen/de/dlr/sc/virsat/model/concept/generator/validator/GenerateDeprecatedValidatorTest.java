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
import de.dlr.sc.virsat.model.concept.generator.validator.GenerateDeprecatedValidator;
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
public class GenerateDeprecatedValidatorTest {
  @Inject
  @Extension
  private ParseHelper<Concept> _parseHelper;
  
  private Concept concept;
  
  private final String conceptID = "de.dlr.sc.virsat.model.extension.testConcept";
  
  private final GenerateDeprecatedValidator deprecatedValidatorGenerator = new GenerateDeprecatedValidator();
  
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
  public void testCreateAbstractClassFileName() {
    final String fileName = this.deprecatedValidatorGenerator.createAbstractClassFileName(this.concept, this.concept);
    String _replace = this.conceptID.replace(".", "/");
    String _plus = (_replace + "/");
    final String expectedFileName = (_plus + "validator/AStructuralElementInstanceValidator.java");
    Assert.assertEquals("Abstract file name for the generated validator is correct", expectedFileName, fileName);
  }
  
  @Test
  public void testCreateAbstractClass() {
    try {
      final CharSequence classContents = this.deprecatedValidatorGenerator.createAbstractClass(this.concept, this.concept);
      GeneratorJunitAssert.assertEqualContent(classContents, "/resources/expectedOutputFilesForGenerators/AStructuralElementInstanceValidator.java");
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
}
