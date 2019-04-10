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

import de.dlr.sc.virsat.model.concept.ConceptLanguageTestInjectorProvider;
import de.dlr.sc.virsat.model.concept.generator.handler.GenerateStructuralElementInstanceAddHandler;
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
public class GenerateStructuralElementInstanceAddHandlerTest {
  @Inject
  @Extension
  private ParseHelper<Concept> _parseHelper;
  
  private Concept concept;
  
  private StructuralElement StructuralElement;
  
  private final String testConceptName = "testConcept";
  
  private final String testStructuralElementName = "testStructuralElement";
  
  private final GenerateStructuralElementInstanceAddHandler structuralElementInstanceAddHandlerGenerator = new GenerateStructuralElementInstanceAddHandler();
  
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
      _builder.append("StructuralElement ");
      _builder.append(this.testStructuralElementName, "\t");
      _builder.append(" {}");
      _builder.newLineIfNotEmpty();
      _builder.append("}");
      _builder.newLine();
      this.concept = this._parseHelper.parse(_builder);
      this.StructuralElement = this.concept.getStructuralElements().get(0);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void testCreateConcreteClassFileName() {
    final String fileName = this.structuralElementInstanceAddHandlerGenerator.createConcreteClassFileName(this.concept, this.StructuralElement);
    String _firstUpper = StringExtensions.toFirstUpper(this.testStructuralElementName);
    String _plus = ((((("../../" + this.testConceptName) + ".ui/src/") + this.testConceptName) + "/ui/handler/AddStructuralElementInstance") + _firstUpper);
    final String expectedFileName = (_plus + "Handler.java");
    Assert.assertEquals("Concrete file name for the generated add handler is correct", expectedFileName, fileName);
  }
  
  @Test
  public void testCreateAbstractClassFileName() {
    final String fileName = this.structuralElementInstanceAddHandlerGenerator.createAbstractClassFileName(this.concept, this.StructuralElement);
    String _firstUpper = StringExtensions.toFirstUpper(this.testStructuralElementName);
    String _plus = ((((("../../" + this.testConceptName) + ".ui/src-gen/") + this.testConceptName) + "/ui/handler/AAddStructuralElementInstance") + _firstUpper);
    final String expectedFileName = (_plus + "Handler.java");
    Assert.assertEquals("Abstract file name for the generated abstract add handler is correct", expectedFileName, fileName);
  }
  
  @Test
  public void testCreateConcreteClass() {
    try {
      final CharSequence classContents = this.structuralElementInstanceAddHandlerGenerator.createConcreteClass(this.concept, this.StructuralElement);
      GeneratorJunitAssert.assertEqualContent(classContents, "/resources/expectedOutputFilesForGenerators/AddTestSEIHandler.java");
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void testCreateAbstractClass() {
    try {
      final CharSequence classContents = this.structuralElementInstanceAddHandlerGenerator.createAbstractClass(this.concept, this.StructuralElement);
      GeneratorJunitAssert.assertEqualContent(classContents, "/resources/expectedOutputFilesForGenerators/AAddTestSEIHandler.java");
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
}
