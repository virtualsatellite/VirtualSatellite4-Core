/**
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 * 
 * SPDX-License-Identifier: EPL-2.0
 */
package de.dlr.sc.virsat.model.concept.generator.plugin;

import de.dlr.sc.virsat.model.concept.ConceptLanguageTestInjectorProvider;
import de.dlr.sc.virsat.model.concept.generator.plugin.GeneratePluginXml;
import de.dlr.sc.virsat.model.concept.generator.plugin.PluginXmlReader;
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
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(XtextRunner.class)
@InjectWith(ConceptLanguageTestInjectorProvider.class)
@SuppressWarnings("all")
public class GenerateConceptPluginXmlTest {
  @Inject
  @Extension
  private ParseHelper<Concept> _parseHelper;
  
  private Concept concept;
  
  private final String TEST_CONCEPT_NAME = "testConcept";
  
  private final String TEST_CATEGORY_NAME = "testCategory";
  
  private final String TEST_STRUCTURAL_1_NAME = "testStructural1";
  
  private final String TEST_STRUCTURAL_2_NAME = "testStructural2";
  
  private final String TEST_EXTRACT_PROTECTED_REGION = "testExtractProtectedRegion";
  
  private final String TEST_VERSION = "1.1";
  
  private final GeneratePluginXml pluginGenerator = new GeneratePluginXml();
  
  @Before
  public void setUp() {
    ConceptsPackage.eINSTANCE.eClass();
    CategoriesPackage.eINSTANCE.eClass();
    PropertydefinitionsPackage.eINSTANCE.eClass();
    this.pluginGenerator.setPluginXmlReader(new PluginXmlReader() {
      @Override
      public PluginXmlReader init(final String pluginId) {
        return this;
      }
      
      @Override
      public String extractProtectedRegion(final int regionID) {
        return GenerateConceptPluginXmlTest.this.TEST_EXTRACT_PROTECTED_REGION;
      }
    });
  }
  
  @Test
  public void testCreateXml() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("Concept ");
      _builder.append(this.TEST_CONCEPT_NAME);
      _builder.append(" version ");
      _builder.append(this.TEST_VERSION);
      _builder.append(" {");
      _builder.newLineIfNotEmpty();
      _builder.append("\t");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("StructuralElement ");
      _builder.append(this.TEST_STRUCTURAL_1_NAME, "\t");
      _builder.append(" {");
      _builder.newLineIfNotEmpty();
      _builder.append("\t\t");
      _builder.append("IsRootStructuralElement;");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("}");
      _builder.newLine();
      _builder.append("\t");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("StructuralElement ");
      _builder.append(this.TEST_STRUCTURAL_2_NAME, "\t");
      _builder.append(" {");
      _builder.newLineIfNotEmpty();
      _builder.append("\t\t");
      _builder.append("Applicable For [");
      _builder.append(this.TEST_STRUCTURAL_1_NAME, "\t\t");
      _builder.append("];");
      _builder.newLineIfNotEmpty();
      _builder.append("\t");
      _builder.append("}\t\t\t\t");
      _builder.newLine();
      _builder.append("\t");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("Category ");
      _builder.append(this.TEST_CATEGORY_NAME, "\t");
      _builder.append(" {");
      _builder.newLineIfNotEmpty();
      _builder.append("\t\t");
      _builder.append("StringProperty tpSringArrayDynamic[];");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("StringProperty tpSringArrayStatic[5];");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("IntProperty tpIntArrayDynamic[];");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("IntProperty tpIntArrayStatic[6];");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("FloatProperty tpFloatArrayDynamic[];");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("FloatProperty tpFloatArrayStatic[7];");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("BooleanProperty tpBooleanArrayDynamic[];");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("BooleanProperty tpBooleanArrayStatic[8];");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("Resource tpResourceDynamich[];");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("Resource tpResourceStatic[9];");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("Applicable For All;");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("}");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      this.concept = this._parseHelper.parse(_builder);
      final CharSequence expected = this.pluginGenerator.createXml(this.concept, this.concept.getName());
      GeneratorJunitAssert.assertEqualContent(expected, "/resources/expectedOutputFilesForGenerators/ConceptPlugin.xml");
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void createXmlDeprecatedValidator() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("Concept ");
      _builder.append(this.TEST_CONCEPT_NAME);
      _builder.append(" version ");
      _builder.append(this.TEST_VERSION);
      _builder.append(" {");
      _builder.newLineIfNotEmpty();
      _builder.append("\t");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("StructuralElement ");
      _builder.append(this.TEST_STRUCTURAL_1_NAME, "\t");
      _builder.append(" {");
      _builder.newLineIfNotEmpty();
      _builder.append("\t\t");
      _builder.append("IsRootStructuralElement;");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("}");
      _builder.newLine();
      _builder.append("\t");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("StructuralElement ");
      _builder.append(this.TEST_STRUCTURAL_2_NAME, "\t");
      _builder.append(" {");
      _builder.newLineIfNotEmpty();
      _builder.append("\t\t");
      _builder.append("Applicable For [");
      _builder.append(this.TEST_STRUCTURAL_1_NAME, "\t\t");
      _builder.append("];");
      _builder.newLineIfNotEmpty();
      _builder.append("\t");
      _builder.append("}\t\t\t\t");
      _builder.newLine();
      _builder.append("\t");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("Category ");
      _builder.append(this.TEST_CATEGORY_NAME, "\t");
      _builder.append(" {");
      _builder.newLineIfNotEmpty();
      _builder.append("\t\t");
      _builder.append("StringProperty tpSringArrayDynamic[];");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("StringProperty tpSringArrayStatic[5];");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("IntProperty tpIntArrayDynamic[];");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("IntProperty tpIntArrayStatic[6];");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("FloatProperty tpFloatArrayDynamic[];");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("FloatProperty tpFloatArrayStatic[7];");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("BooleanProperty tpBooleanArrayDynamic[];");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("BooleanProperty tpBooleanArrayStatic[8];");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("Resource tpResourceDynamich[];");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("Resource tpResourceStatic[9];");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("Applicable For All;");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("}");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      this.concept = this._parseHelper.parse(_builder);
      final CharSequence expected = this.pluginGenerator.createXmlDeprecatedValidator(this.concept, this.concept.getName());
      GeneratorJunitAssert.assertEqualContent(expected, "/resources/expectedOutputFilesForGenerators/ConceptPluginDeprecatedValidator.xml");
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
}
