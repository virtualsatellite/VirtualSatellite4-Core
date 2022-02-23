/**
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 * 
 * SPDX-License-Identifier: EPL-2.0
 */
package de.dlr.sc.virsat.model.concept.generator.propertyTester;

import de.dlr.sc.virsat.model.concept.ConceptLanguageTestInjectorProvider;
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
public class GenerateConceptEnabledTesterTest {
  @Inject
  @Extension
  private ParseHelper<Concept> _parseHelper;
  
  private Concept concept;
  
  private final String testConceptName = "testConcept";
  
  private final GenerateConceptEnabledTester conceptEnabledPropertyTesterGenerator = new GenerateConceptEnabledTester();
  
  @Before
  public void setUp() {
    try {
      ConceptsPackage.eINSTANCE.eClass();
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("Concept ");
      _builder.append(this.testConceptName);
      _builder.append("{}");
      _builder.newLineIfNotEmpty();
      this.concept = this._parseHelper.parse(_builder);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void testCreateConcreteClassFileName() {
    final String fileName = this.conceptEnabledPropertyTesterGenerator.createConcreteClassFileName(this.concept, this.concept);
    final String expectedFileName = (((("../../" + this.testConceptName) + ".ui/src/") + this.testConceptName) + "/propertyTester/ConceptEnabledTester.java");
    Assert.assertEquals("Concrete file name for the generated ConceptEnabledTester is correct", expectedFileName, fileName);
  }
  
  @Test
  public void testCreateAbstractClassFileName() {
    final String fileName = this.conceptEnabledPropertyTesterGenerator.createAbstractClassFileName(this.concept, this.concept);
    final String expectedFileName = (((("../../" + this.testConceptName) + ".ui/src-gen/") + this.testConceptName) + "/propertyTester/AConceptEnabledTester.java");
    Assert.assertEquals("Abstract file name for the generated ConceptEnabledTester is correct", expectedFileName, fileName);
  }
  
  @Test
  public void testCreateConcreteClass() {
    try {
      final CharSequence classContents = this.conceptEnabledPropertyTesterGenerator.createConcreteClass(this.concept, this.concept);
      GeneratorJunitAssert.assertEqualContent(classContents, "/resources/expectedOutputFilesForGenerators/ConceptEnabledTester.java");
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void testCreateAbstractClass() {
    try {
      final CharSequence classContents = this.conceptEnabledPropertyTesterGenerator.createAbstractClass(this.concept, this.concept);
      GeneratorJunitAssert.assertEqualContent(classContents, "/resources/expectedOutputFilesForGenerators/AConceptEnabledTester.java");
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
}
