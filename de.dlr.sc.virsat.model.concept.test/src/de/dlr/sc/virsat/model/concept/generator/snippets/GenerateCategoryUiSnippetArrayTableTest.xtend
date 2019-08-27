/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.concept.generator.snippets

import de.dlr.sc.virsat.model.dvlm.categories.CategoriesPackage
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.PropertydefinitionsPackage
import de.dlr.sc.virsat.model.dvlm.concepts.Concept
import de.dlr.sc.virsat.model.dvlm.concepts.ConceptsPackage
import de.dlr.sc.virsat.model.concept.ConceptLanguageTestInjectorProvider
import de.dlr.sc.virsat.model.concept.test.util.GeneratorJunitAssert
import javax.inject.Inject
import org.eclipse.xtext.testing.InjectWith
import org.eclipse.xtext.testing.XtextRunner
import org.eclipse.xtext.testing.util.ParseHelper
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(XtextRunner)
@InjectWith(ConceptLanguageTestInjectorProvider)
class GenerateCategoryUiSnippetArrayTableTest {

	@Inject extension ParseHelper<Concept>

	Concept concept
	val TEST_CONCEPT_NAME = "testConcept"
	val TEST_CATEGORY_NAME = "testCategory"
	val TEST_EXTENDING_CATEGORY_NAME = "testExtendingCategory"
	val TEST_PROPERTY_NAME = "testStringArray"
	val createAddCommandGenerator = new GenerateCategoryUiSnippetArrayTable

	@Before
	def void setUp() {
		ConceptsPackage.eINSTANCE.eClass
		CategoriesPackage.eINSTANCE.eClass
		PropertydefinitionsPackage.eINSTANCE.eClass
	}
	
	@Test
    def void testCreateConcreteClassFileName() {
      	concept = '''
			Concept «TEST_CONCEPT_NAME» {
				Category «TEST_CATEGORY_NAME» {
					StringProperty «TEST_PROPERTY_NAME»[];
				}
				
				Category «TEST_EXTENDING_CATEGORY_NAME» extends «TEST_CATEGORY_NAME»{
					
				}
			}
	    '''.parse

		val category = concept.categories.get(0)
		val extendingCategory = concept.categories.get(1)
		val property = category.properties.get(0)
		val concreteClassfileName = createAddCommandGenerator.createConcreteClassFileName(concept, property, category)
		val abstractClassfileName = createAddCommandGenerator.createAbstractClassFileName(concept, property, category)
		
		val expectedConcreteClassFileName = "../../" + TEST_CONCEPT_NAME + ".ui/src/" + TEST_CONCEPT_NAME + "/ui/snippet/UiSnippetTable" + TEST_CATEGORY_NAME.toFirstUpper + TEST_PROPERTY_NAME.toFirstUpper +  ".java"
		val expectedAbstractClassFileName = "../../" + TEST_CONCEPT_NAME + ".ui/src-gen/" + TEST_CONCEPT_NAME + "/ui/snippet/AUiSnippetTable" + TEST_CATEGORY_NAME.toFirstUpper + TEST_PROPERTY_NAME.toFirstUpper  + ".java"

		Assert.assertEquals("Concrete file name for the generated create add command is correct", expectedConcreteClassFileName,	concreteClassfileName)
		Assert.assertEquals("Concrete file name for the generated create add command is correct", expectedAbstractClassFileName,	abstractClassfileName)
	
		val concreteExtendedClassfileName = createAddCommandGenerator.createConcreteClassFileName(concept, property, category, extendingCategory)
		val abstractExtendedClassfileName = createAddCommandGenerator.createAbstractClassFileName(concept, property, category, extendingCategory)
		
		val expectedExtendedConcreteClassFileName = "../../" + TEST_CONCEPT_NAME + ".ui/src/" + TEST_CONCEPT_NAME + "/ui/snippet/UiSnippetTable" + TEST_CATEGORY_NAME.toFirstUpper + TEST_PROPERTY_NAME.toFirstUpper + TEST_EXTENDING_CATEGORY_NAME.toFirstUpper + ".java"
		val expectedExtendedAbstractClassFileName = "../../" + TEST_CONCEPT_NAME + ".ui/src-gen/" + TEST_CONCEPT_NAME + "/ui/snippet/AUiSnippetTable" + TEST_CATEGORY_NAME.toFirstUpper + TEST_PROPERTY_NAME.toFirstUpper  + TEST_EXTENDING_CATEGORY_NAME.toFirstUpper + ".java"

		Assert.assertEquals("Concrete file name for the generated create add command is correct", expectedExtendedConcreteClassFileName,	concreteExtendedClassfileName)
		Assert.assertEquals("Concrete file name for the generated create add command is correct", expectedExtendedAbstractClassFileName,	abstractExtendedClassfileName)
	
	}

	@Test
    def void testCreateForUiSnippetIntrinsicPropertyArray() {
    	concept = '''
			Concept testConcept{
				Category TestCategoryB {
					IntProperty arrayStatic[2];
					IntProperty arrayDynamic[];
				}
			}
		'''.parse

		val category = concept.categories.get(0)
		val propertyArrayStatic = category.properties.get(0)
		val propertyArrayDynamic = category.properties.get(1)
		
    	val concreteClassContentsStatic = createAddCommandGenerator.createConcreteClass(concept, propertyArrayStatic, category)
    	val abstractClassContentsStatic = createAddCommandGenerator.createAbstractClass(concept, propertyArrayStatic)
    	
		GeneratorJunitAssert.assertEqualContent(concreteClassContentsStatic, "/resources/expectedOutputFilesForGenerators/UiSnippetIntrinsicPropertyArrayStatic.java")
		GeneratorJunitAssert.assertEqualContent(abstractClassContentsStatic, "/resources/expectedOutputFilesForGenerators/AUiSnippetIntrinsicPropertyArrayStatic.java")

    	val concreteClassContentsDynamic = createAddCommandGenerator.createConcreteClass(concept, propertyArrayDynamic, category)
    	val abstractClassContentsDynamic = createAddCommandGenerator.createAbstractClass(concept, propertyArrayDynamic)
    	
		GeneratorJunitAssert.assertEqualContent(concreteClassContentsDynamic, "/resources/expectedOutputFilesForGenerators/UiSnippetIntrinsicPropertyArrayDynamic.java")
		GeneratorJunitAssert.assertEqualContent(abstractClassContentsDynamic, "/resources/expectedOutputFilesForGenerators/AUiSnippetIntrinsicPropertyArrayDynamic.java")
    }
    
   	@Test
    def void testCreateForUiSnippetComposedPropertyArray() {
    	concept = '''			
			Concept testConcept{
				Category TestCategoryA {
					StringProperty tpSring;
				}
				
				Category TestCategoryB {
					Type testSubCategoryArrayStatic[2] of Category TestCategoryA;
					Type testSubCategoryArrayDynamic[] of Category TestCategoryA;
				}
			}
		'''.parse

		val referencingCategory = concept.categories.get(1)
		val composedPropertyArrayStatic = referencingCategory.properties.get(0)
		val composedPropertyArrayDynamic = referencingCategory.properties.get(1)
		
    	val concreteClassContentsStatic = createAddCommandGenerator.createConcreteClass(concept, composedPropertyArrayStatic, referencingCategory)
    	val abstractClassContentsStatic = createAddCommandGenerator.createAbstractClass(concept, composedPropertyArrayStatic)
    	
		GeneratorJunitAssert.assertEqualContent(concreteClassContentsStatic, "/resources/expectedOutputFilesForGenerators/UiSnippetComposedPropertyArrayStatic.java")
		GeneratorJunitAssert.assertEqualContent(abstractClassContentsStatic, "/resources/expectedOutputFilesForGenerators/AUiSnippetComposedPropertyArrayStatic.java")

    	val concreteClassContentsDynamic = createAddCommandGenerator.createConcreteClass(concept, composedPropertyArrayDynamic, referencingCategory)
    	val abstractClassContentsDynamic = createAddCommandGenerator.createAbstractClass(concept, composedPropertyArrayDynamic)
    	
		GeneratorJunitAssert.assertEqualContent(concreteClassContentsDynamic, "/resources/expectedOutputFilesForGenerators/UiSnippetComposedPropertyArrayDynamic.java")
		GeneratorJunitAssert.assertEqualContent(abstractClassContentsDynamic, "/resources/expectedOutputFilesForGenerators/AUiSnippetComposedPropertyArrayDynamic.java")
    }

   	@Test
    def void testCreateForUiSnippetReferencesPropertyArray() {
    	concept = '''			
			Concept testConcept{
				Category TestCategoryA {
					StringProperty tpSring;
				}
				
				Category TestCategoryB {
					Reference testRefCategoryArrayStatic[2] of Type TestCategoryA;
					Reference testRefCategoryArrayDynamic[] of Type TestCategoryA;
				}
			}
		'''.parse

		val referencingCategory = concept.categories.get(1)
		val referencePropertyArrayStatic = referencingCategory.properties.get(0)
		val referencePropertyArrayDynamic = referencingCategory.properties.get(1)
		
    	val concreteClassContentsStatic = createAddCommandGenerator.createConcreteClass(concept, referencePropertyArrayStatic, referencingCategory)
    	val abstractClassContentsStatic = createAddCommandGenerator.createAbstractClass(concept, referencePropertyArrayStatic)
    	
		GeneratorJunitAssert.assertEqualContent(concreteClassContentsStatic, "/resources/expectedOutputFilesForGenerators/UiSnippetReferencesPropertyArrayStatic.java")
		GeneratorJunitAssert.assertEqualContent(abstractClassContentsStatic, "/resources/expectedOutputFilesForGenerators/AUiSnippetReferencesPropertyArrayStatic.java")

    	val concreteClassContentsDynamic = createAddCommandGenerator.createConcreteClass(concept, referencePropertyArrayDynamic, referencingCategory)
    	val abstractClassContentsDynamic = createAddCommandGenerator.createAbstractClass(concept, referencePropertyArrayDynamic)
    	
		GeneratorJunitAssert.assertEqualContent(concreteClassContentsDynamic, "/resources/expectedOutputFilesForGenerators/UiSnippetReferencesPropertyArrayDynamic.java")
		GeneratorJunitAssert.assertEqualContent(abstractClassContentsDynamic, "/resources/expectedOutputFilesForGenerators/AUiSnippetReferencesPropertyArrayDynamic.java")
    }
    
   	@Test
    def void testCreateForUiSnippetReferencesPropertyToPropertyArray() {
    	concept = '''			
			Concept testConcept{
				Category TestCategoryA {
					StringProperty tpSring;
				}
				
				Category TestCategoryB {
					Reference testRefPropertyArrayDynamic[] of Type TestCategoryA.tpSring;
					Reference testRefPropertyArrayStatic[6] of Type TestCategoryA.tpSring;
				}
			}
		'''.parse

		val referencingCategory = concept.categories.get(1)
		val referencePropertytoPropertyArrayStatic = referencingCategory.properties.get(0)
		val referencePropertytoPropertyArrayDynamic = referencingCategory.properties.get(1)
		
    	val concreteClassContentsStatic = createAddCommandGenerator.createConcreteClass(concept, referencePropertytoPropertyArrayStatic, referencingCategory)
    	val abstractClassContentsStatic = createAddCommandGenerator.createAbstractClass(concept, referencePropertytoPropertyArrayStatic)
    	
		GeneratorJunitAssert.assertEqualContent(concreteClassContentsStatic, "/resources/expectedOutputFilesForGenerators/UiSnippetReferencesPropertyToPropertyArrayStatic.java")
		GeneratorJunitAssert.assertEqualContent(abstractClassContentsStatic, "/resources/expectedOutputFilesForGenerators/AUiSnippetReferencesPropertyToPropertyArrayStatic.java")

    	val concreteClassContentsDynamic = createAddCommandGenerator.createConcreteClass(concept, referencePropertytoPropertyArrayDynamic, referencingCategory)
    	val abstractClassContentsDynamic = createAddCommandGenerator.createAbstractClass(concept, referencePropertytoPropertyArrayDynamic)
    	
		GeneratorJunitAssert.assertEqualContent(concreteClassContentsDynamic, "/resources/expectedOutputFilesForGenerators/UiSnippetReferencesPropertyToPropertyArrayDynamic.java")
		GeneratorJunitAssert.assertEqualContent(abstractClassContentsDynamic, "/resources/expectedOutputFilesForGenerators/AUiSnippetReferencesPropertyToPropertyArrayDynamic.java")
    }
    
   	@Test
    def void testCreateForUiSnippetInheritedIntrinsicPropertyArray() {
    	concept = '''			
			Concept testConcept{
				Category TestCategoryA {
					IntProperty testPropertyArrayDynamic[];
				}
				
				Category TestCategoryB extends TestCategoryA {
				}
			}
		'''.parse
	
		val baseCategory = concept.categories.get(0)
		val extendingCategory = concept.categories.get(1)
		val inheritedIntrinsicPropertyArray = baseCategory.properties.get(0)

    	val concreteClassContentsDynamic = createAddCommandGenerator.createConcreteClass(concept, inheritedIntrinsicPropertyArray, extendingCategory)
    	val abstractClassContentsDynamic = createAddCommandGenerator.createAbstractClass(concept, inheritedIntrinsicPropertyArray, extendingCategory, null)
    	
		GeneratorJunitAssert.assertEqualContent(concreteClassContentsDynamic, "/resources/expectedOutputFilesForGenerators/UiSnippetInheritedIntrinsicPropertyArrayDynamic.java")
		GeneratorJunitAssert.assertEqualContent(abstractClassContentsDynamic, "/resources/expectedOutputFilesForGenerators/AUiSnippetInheritedIntrinsicPropertyArrayDynamic.java")
    }
    
    @Test
    def void testCreateForUiSnippetComposedPropertyWithExtenedTypeArray() {
    	concept = '''			
			Concept testConcept{
				Category TestCategoryA {
				}
				
				Category TestCategoryB extends TestCategoryA {
				}
				
				Category TestCategoryC {
					Type testPropertyArrayDynamic[] of Category TestCategoryA;
				}
			}
		'''.parse
	
		val extendingCategory = concept.categories.get(1)
		val arrayCategory = concept.categories.get(2)
		val inheritedIntrinsicPropertyArray = arrayCategory.properties.get(0)

    	val concreteClassContentsDynamic = createAddCommandGenerator.createConcreteClass(concept, inheritedIntrinsicPropertyArray, arrayCategory, extendingCategory)
    	val abstractClassContentsDynamic = createAddCommandGenerator.createAbstractClass(concept, inheritedIntrinsicPropertyArray, arrayCategory, extendingCategory)
    	
		GeneratorJunitAssert.assertEqualContent(concreteClassContentsDynamic, "/resources/expectedOutputFilesForGenerators/UiSnippetComposedPropertyWithExtenedTypeArrayDynamic.java")
		GeneratorJunitAssert.assertEqualContent(abstractClassContentsDynamic, "/resources/expectedOutputFilesForGenerators/AUiSnippetComposedPropertyWithExtenedTypeArrayDynamic.java")
    }

}
