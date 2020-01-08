/**
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 * 
 * SPDX-License-Identifier: EPL-2.0
 */
package de.dlr.sc.virsat.model.concept.generator.snippets;

import de.dlr.sc.virsat.model.concept.ConceptLanguageTestInjectorProvider;
import de.dlr.sc.virsat.model.concept.generator.snippets.GenerateCategoryUiSnippetArrayTable;
import de.dlr.sc.virsat.model.concept.test.util.GeneratorJunitAssert;
import de.dlr.sc.virsat.model.dvlm.categories.CategoriesPackage;
import de.dlr.sc.virsat.model.dvlm.categories.Category;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.AProperty;
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
import org.eclipse.xtext.xbase.lib.StringExtensions;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(XtextRunner.class)
@InjectWith(ConceptLanguageTestInjectorProvider.class)
@SuppressWarnings("all")
public class GenerateCategoryUiSnippetArrayTableTest {
  @Inject
  @Extension
  private ParseHelper<Concept> _parseHelper;
  
  private Concept concept;
  
  private final String TEST_CONCEPT_NAME = "testConcept";
  
  private final String TEST_CATEGORY_NAME = "testCategory";
  
  private final String TEST_EXTENDING_CATEGORY_NAME = "testExtendingCategory";
  
  private final String TEST_PROPERTY_NAME = "testStringArray";
  
  private final GenerateCategoryUiSnippetArrayTable createAddCommandGenerator = new GenerateCategoryUiSnippetArrayTable();
  
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
      _builder.append("\t\t");
      _builder.append("StringProperty ");
      _builder.append(this.TEST_PROPERTY_NAME, "\t\t");
      _builder.append("[];");
      _builder.newLineIfNotEmpty();
      _builder.append("\t");
      _builder.append("}");
      _builder.newLine();
      _builder.append("\t");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("Category ");
      _builder.append(this.TEST_EXTENDING_CATEGORY_NAME, "\t");
      _builder.append(" extends ");
      _builder.append(this.TEST_CATEGORY_NAME, "\t");
      _builder.append("{");
      _builder.newLineIfNotEmpty();
      _builder.append("\t\t");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("}");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      this.concept = this._parseHelper.parse(_builder);
      final Category category = this.concept.getCategories().get(0);
      final Category extendingCategory = this.concept.getCategories().get(1);
      final AProperty property = category.getProperties().get(0);
      final String concreteClassfileName = this.createAddCommandGenerator.createConcreteClassFileName(this.concept, property, category);
      final String abstractClassfileName = this.createAddCommandGenerator.createAbstractClassFileName(this.concept, property, category);
      String _firstUpper = StringExtensions.toFirstUpper(this.TEST_CATEGORY_NAME);
      String _plus = ((((("../../" + this.TEST_CONCEPT_NAME) + ".ui/src/") + this.TEST_CONCEPT_NAME) + "/ui/snippet/UiSnippetTable") + _firstUpper);
      String _firstUpper_1 = StringExtensions.toFirstUpper(this.TEST_PROPERTY_NAME);
      String _plus_1 = (_plus + _firstUpper_1);
      final String expectedConcreteClassFileName = (_plus_1 + ".java");
      String _firstUpper_2 = StringExtensions.toFirstUpper(this.TEST_CATEGORY_NAME);
      String _plus_2 = ((((("../../" + this.TEST_CONCEPT_NAME) + ".ui/src-gen/") + this.TEST_CONCEPT_NAME) + "/ui/snippet/AUiSnippetTable") + _firstUpper_2);
      String _firstUpper_3 = StringExtensions.toFirstUpper(this.TEST_PROPERTY_NAME);
      String _plus_3 = (_plus_2 + _firstUpper_3);
      final String expectedAbstractClassFileName = (_plus_3 + ".java");
      Assert.assertEquals("Concrete file name for the generated create add command is correct", expectedConcreteClassFileName, concreteClassfileName);
      Assert.assertEquals("Concrete file name for the generated create add command is correct", expectedAbstractClassFileName, abstractClassfileName);
      final String concreteExtendedClassfileName = this.createAddCommandGenerator.createConcreteClassFileName(this.concept, property, category, extendingCategory);
      final String abstractExtendedClassfileName = this.createAddCommandGenerator.createAbstractClassFileName(this.concept, property, category, extendingCategory);
      String _firstUpper_4 = StringExtensions.toFirstUpper(this.TEST_CATEGORY_NAME);
      String _plus_4 = ((((("../../" + this.TEST_CONCEPT_NAME) + ".ui/src/") + this.TEST_CONCEPT_NAME) + "/ui/snippet/UiSnippetTable") + _firstUpper_4);
      String _firstUpper_5 = StringExtensions.toFirstUpper(this.TEST_PROPERTY_NAME);
      String _plus_5 = (_plus_4 + _firstUpper_5);
      String _firstUpper_6 = StringExtensions.toFirstUpper(this.TEST_EXTENDING_CATEGORY_NAME);
      String _plus_6 = (_plus_5 + _firstUpper_6);
      final String expectedExtendedConcreteClassFileName = (_plus_6 + ".java");
      String _firstUpper_7 = StringExtensions.toFirstUpper(this.TEST_CATEGORY_NAME);
      String _plus_7 = ((((("../../" + this.TEST_CONCEPT_NAME) + ".ui/src-gen/") + this.TEST_CONCEPT_NAME) + "/ui/snippet/AUiSnippetTable") + _firstUpper_7);
      String _firstUpper_8 = StringExtensions.toFirstUpper(this.TEST_PROPERTY_NAME);
      String _plus_8 = (_plus_7 + _firstUpper_8);
      String _firstUpper_9 = StringExtensions.toFirstUpper(this.TEST_EXTENDING_CATEGORY_NAME);
      String _plus_9 = (_plus_8 + _firstUpper_9);
      final String expectedExtendedAbstractClassFileName = (_plus_9 + ".java");
      Assert.assertEquals("Concrete file name for the generated create add command is correct", expectedExtendedConcreteClassFileName, concreteExtendedClassfileName);
      Assert.assertEquals("Concrete file name for the generated create add command is correct", expectedExtendedAbstractClassFileName, abstractExtendedClassfileName);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void testCreateForUiSnippetIntrinsicPropertyArray() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("Concept testConcept{");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("Category TestCategoryB {");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("IntProperty arrayStatic[2];");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("IntProperty arrayDynamic[];");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("}");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      this.concept = this._parseHelper.parse(_builder);
      final Category category = this.concept.getCategories().get(0);
      final AProperty propertyArrayStatic = category.getProperties().get(0);
      final AProperty propertyArrayDynamic = category.getProperties().get(1);
      final String concreteClassContentsStatic = this.createAddCommandGenerator.createConcreteClass(this.concept, propertyArrayStatic, category);
      final CharSequence abstractClassContentsStatic = this.createAddCommandGenerator.createAbstractClass(this.concept, propertyArrayStatic);
      GeneratorJunitAssert.assertEqualContent(concreteClassContentsStatic, "/resources/expectedOutputFilesForGenerators/UiSnippetIntrinsicPropertyArrayStatic.java");
      GeneratorJunitAssert.assertEqualContent(abstractClassContentsStatic, "/resources/expectedOutputFilesForGenerators/AUiSnippetIntrinsicPropertyArrayStatic.java");
      final String concreteClassContentsDynamic = this.createAddCommandGenerator.createConcreteClass(this.concept, propertyArrayDynamic, category);
      final CharSequence abstractClassContentsDynamic = this.createAddCommandGenerator.createAbstractClass(this.concept, propertyArrayDynamic);
      GeneratorJunitAssert.assertEqualContent(concreteClassContentsDynamic, "/resources/expectedOutputFilesForGenerators/UiSnippetIntrinsicPropertyArrayDynamic.java");
      GeneratorJunitAssert.assertEqualContent(abstractClassContentsDynamic, "/resources/expectedOutputFilesForGenerators/AUiSnippetIntrinsicPropertyArrayDynamic.java");
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void testCreateForUiSnippetComposedPropertyArray() {
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
      _builder.append("Type testSubCategoryArrayStatic[2] of Category TestCategoryA;");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("Type testSubCategoryArrayDynamic[] of Category TestCategoryA;");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("}");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      this.concept = this._parseHelper.parse(_builder);
      final Category referencingCategory = this.concept.getCategories().get(1);
      final AProperty composedPropertyArrayStatic = referencingCategory.getProperties().get(0);
      final AProperty composedPropertyArrayDynamic = referencingCategory.getProperties().get(1);
      final String concreteClassContentsStatic = this.createAddCommandGenerator.createConcreteClass(this.concept, composedPropertyArrayStatic, referencingCategory);
      final CharSequence abstractClassContentsStatic = this.createAddCommandGenerator.createAbstractClass(this.concept, composedPropertyArrayStatic);
      GeneratorJunitAssert.assertEqualContent(concreteClassContentsStatic, "/resources/expectedOutputFilesForGenerators/UiSnippetComposedPropertyArrayStatic.java");
      GeneratorJunitAssert.assertEqualContent(abstractClassContentsStatic, "/resources/expectedOutputFilesForGenerators/AUiSnippetComposedPropertyArrayStatic.java");
      final String concreteClassContentsDynamic = this.createAddCommandGenerator.createConcreteClass(this.concept, composedPropertyArrayDynamic, referencingCategory);
      final CharSequence abstractClassContentsDynamic = this.createAddCommandGenerator.createAbstractClass(this.concept, composedPropertyArrayDynamic);
      GeneratorJunitAssert.assertEqualContent(concreteClassContentsDynamic, "/resources/expectedOutputFilesForGenerators/UiSnippetComposedPropertyArrayDynamic.java");
      GeneratorJunitAssert.assertEqualContent(abstractClassContentsDynamic, "/resources/expectedOutputFilesForGenerators/AUiSnippetComposedPropertyArrayDynamic.java");
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void testCreateForUiSnippetReferencesPropertyArray() {
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
      _builder.append("Reference testRefCategoryArrayStatic[2] of Type TestCategoryA;");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("Reference testRefCategoryArrayDynamic[] of Type TestCategoryA;");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("}");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      this.concept = this._parseHelper.parse(_builder);
      final Category referencingCategory = this.concept.getCategories().get(1);
      final AProperty referencePropertyArrayStatic = referencingCategory.getProperties().get(0);
      final AProperty referencePropertyArrayDynamic = referencingCategory.getProperties().get(1);
      final String concreteClassContentsStatic = this.createAddCommandGenerator.createConcreteClass(this.concept, referencePropertyArrayStatic, referencingCategory);
      final CharSequence abstractClassContentsStatic = this.createAddCommandGenerator.createAbstractClass(this.concept, referencePropertyArrayStatic);
      GeneratorJunitAssert.assertEqualContent(concreteClassContentsStatic, "/resources/expectedOutputFilesForGenerators/UiSnippetReferencesPropertyArrayStatic.java");
      GeneratorJunitAssert.assertEqualContent(abstractClassContentsStatic, "/resources/expectedOutputFilesForGenerators/AUiSnippetReferencesPropertyArrayStatic.java");
      final String concreteClassContentsDynamic = this.createAddCommandGenerator.createConcreteClass(this.concept, referencePropertyArrayDynamic, referencingCategory);
      final CharSequence abstractClassContentsDynamic = this.createAddCommandGenerator.createAbstractClass(this.concept, referencePropertyArrayDynamic);
      GeneratorJunitAssert.assertEqualContent(concreteClassContentsDynamic, "/resources/expectedOutputFilesForGenerators/UiSnippetReferencesPropertyArrayDynamic.java");
      GeneratorJunitAssert.assertEqualContent(abstractClassContentsDynamic, "/resources/expectedOutputFilesForGenerators/AUiSnippetReferencesPropertyArrayDynamic.java");
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void testCreateForUiSnippetReferencesPropertyToPropertyArray() {
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
      final Category referencingCategory = this.concept.getCategories().get(1);
      final AProperty referencePropertytoPropertyArrayStatic = referencingCategory.getProperties().get(0);
      final AProperty referencePropertytoPropertyArrayDynamic = referencingCategory.getProperties().get(1);
      final String concreteClassContentsStatic = this.createAddCommandGenerator.createConcreteClass(this.concept, referencePropertytoPropertyArrayStatic, referencingCategory);
      final CharSequence abstractClassContentsStatic = this.createAddCommandGenerator.createAbstractClass(this.concept, referencePropertytoPropertyArrayStatic);
      GeneratorJunitAssert.assertEqualContent(concreteClassContentsStatic, "/resources/expectedOutputFilesForGenerators/UiSnippetReferencesPropertyToPropertyArrayStatic.java");
      GeneratorJunitAssert.assertEqualContent(abstractClassContentsStatic, "/resources/expectedOutputFilesForGenerators/AUiSnippetReferencesPropertyToPropertyArrayStatic.java");
      final String concreteClassContentsDynamic = this.createAddCommandGenerator.createConcreteClass(this.concept, referencePropertytoPropertyArrayDynamic, referencingCategory);
      final CharSequence abstractClassContentsDynamic = this.createAddCommandGenerator.createAbstractClass(this.concept, referencePropertytoPropertyArrayDynamic);
      GeneratorJunitAssert.assertEqualContent(concreteClassContentsDynamic, "/resources/expectedOutputFilesForGenerators/UiSnippetReferencesPropertyToPropertyArrayDynamic.java");
      GeneratorJunitAssert.assertEqualContent(abstractClassContentsDynamic, "/resources/expectedOutputFilesForGenerators/AUiSnippetReferencesPropertyToPropertyArrayDynamic.java");
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void testCreateForUiSnippetInheritedIntrinsicPropertyArray() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("Concept testConcept{");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("Category TestCategoryA {");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("IntProperty testPropertyArrayDynamic[];");
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
      final Category baseCategory = this.concept.getCategories().get(0);
      final Category extendingCategory = this.concept.getCategories().get(1);
      final AProperty inheritedIntrinsicPropertyArray = baseCategory.getProperties().get(0);
      final String concreteClassContentsDynamic = this.createAddCommandGenerator.createConcreteClass(this.concept, inheritedIntrinsicPropertyArray, extendingCategory);
      final String abstractClassContentsDynamic = this.createAddCommandGenerator.createAbstractClass(this.concept, inheritedIntrinsicPropertyArray, extendingCategory, null);
      GeneratorJunitAssert.assertEqualContent(concreteClassContentsDynamic, "/resources/expectedOutputFilesForGenerators/UiSnippetInheritedIntrinsicPropertyArrayDynamic.java");
      GeneratorJunitAssert.assertEqualContent(abstractClassContentsDynamic, "/resources/expectedOutputFilesForGenerators/AUiSnippetInheritedIntrinsicPropertyArrayDynamic.java");
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void testCreateForUiSnippetComposedPropertyWithExtenedTypeArray() {
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
      _builder.append("\t");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("Category TestCategoryC {");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("Type testPropertyArrayDynamic[] of Category TestCategoryA;");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("}");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      this.concept = this._parseHelper.parse(_builder);
      final Category extendingCategory = this.concept.getCategories().get(1);
      final Category arrayCategory = this.concept.getCategories().get(2);
      final AProperty inheritedIntrinsicPropertyArray = arrayCategory.getProperties().get(0);
      final String concreteClassContentsDynamic = this.createAddCommandGenerator.createConcreteClass(this.concept, inheritedIntrinsicPropertyArray, arrayCategory, extendingCategory);
      final String abstractClassContentsDynamic = this.createAddCommandGenerator.createAbstractClass(this.concept, inheritedIntrinsicPropertyArray, arrayCategory, extendingCategory);
      GeneratorJunitAssert.assertEqualContent(concreteClassContentsDynamic, "/resources/expectedOutputFilesForGenerators/UiSnippetComposedPropertyWithExtenedTypeArrayDynamic.java");
      GeneratorJunitAssert.assertEqualContent(abstractClassContentsDynamic, "/resources/expectedOutputFilesForGenerators/AUiSnippetComposedPropertyWithExtenedTypeArrayDynamic.java");
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
}
