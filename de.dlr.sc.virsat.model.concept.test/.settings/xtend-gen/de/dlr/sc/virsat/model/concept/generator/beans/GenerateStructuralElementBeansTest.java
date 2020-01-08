/**
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 * 
 * SPDX-License-Identifier: EPL-2.0
 */
package de.dlr.sc.virsat.model.concept.generator.beans;

import de.dlr.sc.virsat.model.concept.ConceptLanguageTestInjectorProvider;
import de.dlr.sc.virsat.model.concept.generator.beans.GenerateStructuralElementBeans;
import de.dlr.sc.virsat.model.concept.test.util.GeneratorJunitAssert;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.dvlm.concepts.ConceptsPackage;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElement;
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
public class GenerateStructuralElementBeansTest {
  @Inject
  @Extension
  private ParseHelper<Concept> _parseHelper;
  
  private Concept concept;
  
  private static final String TEST_CONCEPT_NAME = "testConcept";
  
  private static final String TEST_STRUCTURAL_ELEMENT_NAME = "testStructuralElement";
  
  private final GenerateStructuralElementBeans createAddCommandGenerator = new GenerateStructuralElementBeans();
  
  @Before
  public void setUp() {
    ConceptsPackage.eINSTANCE.eClass();
  }
  
  @Test
  public void testCreateConcreteClassFileName() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("Concept ");
      _builder.append(GenerateStructuralElementBeansTest.TEST_CONCEPT_NAME);
      _builder.append(" {");
      _builder.newLineIfNotEmpty();
      _builder.append("\t");
      _builder.append("StructuralElement ");
      _builder.append(GenerateStructuralElementBeansTest.TEST_STRUCTURAL_ELEMENT_NAME, "\t");
      _builder.append(" {");
      _builder.newLineIfNotEmpty();
      _builder.append("\t");
      _builder.append("}");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      this.concept = this._parseHelper.parse(_builder);
      final StructuralElement structuralElement = this.concept.getStructuralElements().get(0);
      final String concreteClassfileName = this.createAddCommandGenerator.createConcreteClassFileName(this.concept, structuralElement);
      final String abstractClassfileName = this.createAddCommandGenerator.createAbstractClassFileName(this.concept, structuralElement);
      String _firstUpper = StringExtensions.toFirstUpper(GenerateStructuralElementBeansTest.TEST_STRUCTURAL_ELEMENT_NAME);
      String _plus = ((GenerateStructuralElementBeansTest.TEST_CONCEPT_NAME + "/model/") + _firstUpper);
      final String expectedConcreteClassFileName = (_plus + ".java");
      String _firstUpper_1 = StringExtensions.toFirstUpper(GenerateStructuralElementBeansTest.TEST_STRUCTURAL_ELEMENT_NAME);
      String _plus_1 = ((GenerateStructuralElementBeansTest.TEST_CONCEPT_NAME + "/model/A") + _firstUpper_1);
      final String expectedAbstractClassFileName = (_plus_1 + ".java");
      Assert.assertEquals("Concrete file name for the generated create add command is correct", expectedConcreteClassFileName, concreteClassfileName);
      Assert.assertEquals("Concrete file name for the generated create add command is correct", expectedAbstractClassFileName, abstractClassfileName);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void testCreateStructuralElementClasses() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("Concept ");
      _builder.append(GenerateStructuralElementBeansTest.TEST_CONCEPT_NAME);
      _builder.append("{");
      _builder.newLineIfNotEmpty();
      _builder.append("\t");
      _builder.append("StructuralElement ");
      _builder.append(GenerateStructuralElementBeansTest.TEST_STRUCTURAL_ELEMENT_NAME, "\t");
      _builder.append(" {");
      _builder.newLineIfNotEmpty();
      _builder.append("\t");
      _builder.append("}");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      this.concept = this._parseHelper.parse(_builder);
      final StructuralElement structuralElement = this.concept.getStructuralElements().get(0);
      final CharSequence concreteClassContents = this.createAddCommandGenerator.createConcreteClass(this.concept, structuralElement);
      final CharSequence abstractClassContents = this.createAddCommandGenerator.createAbstractClass(this.concept, structuralElement);
      GeneratorJunitAssert.assertEqualContent(concreteClassContents, "/resources/expectedOutputFilesForGenerators/StructuralElementBean.java");
      GeneratorJunitAssert.assertEqualContent(abstractClassContents, "/resources/expectedOutputFilesForGenerators/AStructuralElementBean.java");
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
}
