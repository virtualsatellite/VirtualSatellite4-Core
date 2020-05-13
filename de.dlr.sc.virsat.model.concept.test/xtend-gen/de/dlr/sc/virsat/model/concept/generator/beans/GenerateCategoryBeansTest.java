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
import de.dlr.sc.virsat.model.concept.generator.beans.GenerateCategoryBeans;
import de.dlr.sc.virsat.model.concept.test.util.GeneratorJunitAssert;
import de.dlr.sc.virsat.model.dvlm.categories.CategoriesPackage;
import de.dlr.sc.virsat.model.dvlm.categories.Category;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.AProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.ComposedProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.PropertydefinitionsPackage;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.ReferenceProperty;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.dvlm.concepts.ConceptsPackage;
import de.dlr.sc.virsat.model.external.tests.ExternalModelTestHelper;
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
public class GenerateCategoryBeansTest {
  @Inject
  @Extension
  private ParseHelper<Concept> _parseHelper;
  
  protected ExternalModelTestHelper helper = new ExternalModelTestHelper();
  
  private Concept concept;
  
  private final String TEST_CONCEPT_NAME = "testConcept";
  
  private final String TEST_CATEGORY_NAME = "testCategory";
  
  private final GenerateCategoryBeans createAddCommandGenerator = new GenerateCategoryBeans();
  
  @Before
  public void setUp() {
    ConceptsPackage.eINSTANCE.eClass();
    CategoriesPackage.eINSTANCE.eClass();
    PropertydefinitionsPackage.eINSTANCE.eClass();
    this.helper.loadExternalPackage();
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
      _builder.append("}");
      _builder.newLine();
      this.concept = this._parseHelper.parse(_builder);
      final Category category = this.concept.getCategories().get(0);
      final String concreteClassfileName = this.createAddCommandGenerator.createConcreteClassFileName(this.concept, category);
      final String abstractClassfileName = this.createAddCommandGenerator.createAbstractClassFileName(this.concept, category);
      String _firstUpper = StringExtensions.toFirstUpper(this.TEST_CATEGORY_NAME);
      String _plus = ((this.TEST_CONCEPT_NAME + "/model/") + _firstUpper);
      final String expectedConcreteClassFileName = (_plus + ".java");
      String _firstUpper_1 = StringExtensions.toFirstUpper(this.TEST_CATEGORY_NAME);
      String _plus_1 = ((this.TEST_CONCEPT_NAME + "/model/A") + _firstUpper_1);
      final String expectedAbstractClassFileName = (_plus_1 + ".java");
      Assert.assertEquals("Concrete file name for the generated create add command is correct", expectedConcreteClassFileName, concreteClassfileName);
      Assert.assertEquals("Concrete file name for the generated create add command is correct", expectedAbstractClassFileName, abstractClassfileName);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void testCreateForIntrinsicProperties() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("Concept ");
      _builder.append(this.TEST_CONCEPT_NAME);
      _builder.append("{");
      _builder.newLineIfNotEmpty();
      _builder.append("\t");
      _builder.append("Category ");
      _builder.append(this.TEST_CATEGORY_NAME, "\t");
      _builder.append(" {");
      _builder.newLineIfNotEmpty();
      _builder.append("\t\t");
      _builder.append("StringProperty tpSring;");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("IntProperty tpInt;");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("FloatProperty tpFloat;");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("BooleanProperty tpBoolean;");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("Resource tpResource;");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("EnumProperty tpEnum;");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("}");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      this.concept = this._parseHelper.parse(_builder);
      final Category category = this.concept.getCategories().get(0);
      final CharSequence concreteClassContents = this.createAddCommandGenerator.createConcreteClass(this.concept, category);
      final CharSequence abstractClassContents = this.createAddCommandGenerator.createAbstractClass(this.concept, category);
      GeneratorJunitAssert.assertEqualContent(concreteClassContents, "/resources/expectedOutputFilesForGenerators/CategoryBeanIntrinsicProperties.java");
      GeneratorJunitAssert.assertEqualContent(abstractClassContents, "/resources/expectedOutputFilesForGenerators/ACategoryBeanIntrinsicProperties.java");
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void testCreateForIntrinsicArrayProperties() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("Concept ");
      _builder.append(this.TEST_CONCEPT_NAME);
      _builder.append("{");
      _builder.newLineIfNotEmpty();
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
      _builder.append("EnumProperty tpEnumDynamich[];");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("EnumProperty tpEnumStatic[9];");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("}");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      this.concept = this._parseHelper.parse(_builder);
      final Category category = this.concept.getCategories().get(0);
      final CharSequence concreteClassContents = this.createAddCommandGenerator.createConcreteClass(this.concept, category);
      final CharSequence abstractClassContents = this.createAddCommandGenerator.createAbstractClass(this.concept, category);
      GeneratorJunitAssert.assertEqualContent(concreteClassContents, "/resources/expectedOutputFilesForGenerators/CategoryBeanIntrinsicArrayProperties.java");
      GeneratorJunitAssert.assertEqualContent(abstractClassContents, "/resources/expectedOutputFilesForGenerators/ACategoryBeanIntrinsicArrayProperties.java");
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void testCreateEnumPropertyValues() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("Concept testConcept{");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("Category TestCategoryA {");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("EnumProperty enumProperty  values [LITERAL1=1, LITERAL2=2, LITERAL3=3];");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("EnumProperty emptyEnumProperty;");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("}");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      this.concept = this._parseHelper.parse(_builder);
      final Category category = this.concept.getCategories().get(0);
      final CharSequence abstractClassContents = this.createAddCommandGenerator.createAbstractClass(this.concept, category);
      GeneratorJunitAssert.assertEqualContent(abstractClassContents, "/resources/expectedOutputFilesForGenerators/ACategoryBeanEnumProperties.java");
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void testCreateForTypesAndReferences() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("Concept testConcept{");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("Category TestCategoryA {");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("StringProperty tpSring;");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("}");
      _builder.newLine();
      _builder.append("\t");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("Category TestCategoryB {");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("Type testSubCategory of Category TestCategoryA;");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("Reference testRefCategory of Type TestCategoryA;");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("Reference testRefProperty of Type TestCategoryA.tpSring;");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("}");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      this.concept = this._parseHelper.parse(_builder);
      final Category referencingCategory = this.concept.getCategories().get(1);
      final Category referencedCategory = this.concept.getCategories().get(0);
      final AProperty referencedProperty = this.concept.getCategories().get(0).getProperties().get(0);
      final CharSequence concreteClassContents = this.createAddCommandGenerator.createConcreteClass(this.concept, referencingCategory);
      final CharSequence abstractClassContents = this.createAddCommandGenerator.createAbstractClass(this.concept, referencingCategory);
      AProperty _get = referencingCategory.getProperties().get(0);
      final ComposedProperty composedProperty = ((ComposedProperty) _get);
      AProperty _get_1 = referencingCategory.getProperties().get(1);
      final ReferenceProperty referenceProperty1 = ((ReferenceProperty) _get_1);
      AProperty _get_2 = referencingCategory.getProperties().get(2);
      final ReferenceProperty referenceProperty2 = ((ReferenceProperty) _get_2);
      Assert.assertEquals("Model is well linked", referencedCategory, composedProperty.getType());
      Assert.assertEquals("Model is well linked", referencedCategory, referenceProperty1.getReferenceType());
      Assert.assertEquals("Model is well linked", referencedProperty, referenceProperty2.getReferenceType());
      GeneratorJunitAssert.assertEqualContent(concreteClassContents, "/resources/expectedOutputFilesForGenerators/CategoryBeanTypesAndReferences.java");
      GeneratorJunitAssert.assertEqualContent(abstractClassContents, "/resources/expectedOutputFilesForGenerators/ACategoryBeanTypesAndReferences.java");
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void testCreateForExtension() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("Concept testConcept{");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("Category TestCategoryA {");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("}");
      _builder.newLine();
      _builder.append("\t");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("Category TestCategoryB extends TestCategoryA {");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("}");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      this.concept = this._parseHelper.parse(_builder);
      final Category extendingCategory = this.concept.getCategories().get(1);
      final Category extendedCategory = this.concept.getCategories().get(0);
      final CharSequence concreteClassContents = this.createAddCommandGenerator.createConcreteClass(this.concept, extendingCategory);
      final CharSequence abstractClassContents = this.createAddCommandGenerator.createAbstractClass(this.concept, extendingCategory);
      Assert.assertEquals("Model is well linked", extendedCategory, extendingCategory.getExtendsCategory());
      GeneratorJunitAssert.assertEqualContent(concreteClassContents, "/resources/expectedOutputFilesForGenerators/CategoryBeanExtension.java");
      GeneratorJunitAssert.assertEqualContent(abstractClassContents, "/resources/expectedOutputFilesForGenerators/ACategoryBeanExtension.java");
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void testCreateForTypesAndReferencesArrays() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("Concept testConcept{");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("Category TestCategoryA {");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("StringProperty tpSring;");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("}");
      _builder.newLine();
      _builder.append("\t");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("Category TestCategoryB {");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("Type testSubCategoryArrayDynamic[] of Category TestCategoryA;");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("Type testSubCategoryArrayStatic[2] of Category TestCategoryA;");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("Reference testRefCategoryArrayDynamic[] of Type TestCategoryA;");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("Reference testRefCategoryArrayStatic[4] of Type TestCategoryA;");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("Reference testRefPropertyArrayDynamic[] of Type TestCategoryA.tpSring;");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("Reference testRefPropertyArrayStatic[6] of Type TestCategoryA.tpSring;");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("}");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      this.concept = this._parseHelper.parse(_builder);
      final Category category = this.concept.getCategories().get(1);
      final CharSequence concreteClassContents = this.createAddCommandGenerator.createConcreteClass(this.concept, category);
      final CharSequence abstractClassContents = this.createAddCommandGenerator.createAbstractClass(this.concept, category);
      GeneratorJunitAssert.assertEqualContent(concreteClassContents, "/resources/expectedOutputFilesForGenerators/CategoryBeanTypesAndReferencesArrays.java");
      GeneratorJunitAssert.assertEqualContent(abstractClassContents, "/resources/expectedOutputFilesForGenerators/ACategoryBeanTypesAndReferencesArrays.java");
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void testCreateForEReferenceWithoutGenmodel() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("Concept testConcept{");
      _builder.newLine();
      _builder.append("\t");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("EImport \"http://www.virsat.sc.dlr.de/external/tests\";");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("Category TestCategory {");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("EReference testEReference of Type tests.ExternalTestType;");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("EReference testEReferenceArray[] of Type tests.ExternalTestType;");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("}");
      _builder.newLine();
      _builder.append("\t");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      this.concept = this._parseHelper.parse(_builder, this.helper.getResourceSet());
      final Category category = this.concept.getCategories().get(0);
      final CharSequence abstractClassContents = this.createAddCommandGenerator.createAbstractClass(this.concept, category);
      GeneratorJunitAssert.assertEqualContent(abstractClassContents, "/resources/expectedOutputFilesForGenerators/ACategoryBeanExternalEReference.java");
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void testCreateForEReferenceWithRegisteredGenmodel() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("Concept testConcept{");
      _builder.newLine();
      _builder.append("\t");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("EImport \"http://www.virsat.sc.dlr.de/external/tests\" genModel \"de.dlr.sc.virsat.model.external.tests/model/ExternalModel.genmodel\";");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("Category TestCategory {");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("EReference testEReference of Type tests.ExternalTestType;");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("EReference testEReferenceArray[] of Type tests.ExternalTestType;");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("}");
      _builder.newLine();
      _builder.append("\t");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      this.concept = this._parseHelper.parse(_builder, this.helper.getResourceSet());
      final Category category = this.concept.getCategories().get(0);
      final CharSequence abstractClassContents = this.createAddCommandGenerator.createAbstractClass(this.concept, category);
      GeneratorJunitAssert.assertEqualContent(abstractClassContents, "/resources/expectedOutputFilesForGenerators/ACategoryBeanExternalEReference.java");
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
}
