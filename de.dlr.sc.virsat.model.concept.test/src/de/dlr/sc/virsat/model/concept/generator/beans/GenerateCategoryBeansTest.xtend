/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.concept.generator.beans

import de.dlr.sc.virsat.model.concept.ConceptLanguageTestInjectorProvider
import de.dlr.sc.virsat.model.concept.test.util.GeneratorJunitAssert
import de.dlr.sc.virsat.model.dvlm.categories.CategoriesPackage
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.ComposedProperty
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.PropertydefinitionsPackage
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.ReferenceProperty
import de.dlr.sc.virsat.model.dvlm.concepts.Concept
import de.dlr.sc.virsat.model.dvlm.concepts.ConceptsPackage
import javax.inject.Inject
import org.eclipse.xtext.testing.InjectWith
import org.eclipse.xtext.testing.XtextRunner
import org.eclipse.xtext.testing.util.ParseHelper
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import de.dlr.sc.virsat.model.external.tests.ExternalModelTestHelper

@RunWith(XtextRunner)
@InjectWith(ConceptLanguageTestInjectorProvider)
class GenerateCategoryBeansTest {

	@Inject extension ParseHelper<Concept>
	protected ExternalModelTestHelper helper = new ExternalModelTestHelper;

	Concept concept
	val TEST_CONCEPT_NAME = "testConcept"
	val TEST_CATEGORY_NAME = "testCategory"
	val createAddCommandGenerator = new GenerateCategoryBeans();

	@Before
	def void setUp() {
		ConceptsPackage.eINSTANCE.eClass
		CategoriesPackage.eINSTANCE.eClass
		PropertydefinitionsPackage.eINSTANCE.eClass
		helper.loadExternalPackage
	}
	
	@Test
    def void testCreateConcreteClassFileName() {
      	concept = '''
			Concept «TEST_CONCEPT_NAME» {
				Category «TEST_CATEGORY_NAME» {
				}
			}
	    '''.parse

		val category = concept.categories.get(0)
		val concreteClassfileName = createAddCommandGenerator.createConcreteClassFileName(concept, category)
		val abstractClassfileName = createAddCommandGenerator.createAbstractClassFileName(concept, category)
		val expectedConcreteClassFileName = TEST_CONCEPT_NAME + "/model/" +	TEST_CATEGORY_NAME.toFirstUpper + ".java"
		val expectedAbstractClassFileName = TEST_CONCEPT_NAME + "/model/A" + TEST_CATEGORY_NAME.toFirstUpper + ".java"

		Assert.assertEquals("Concrete file name for the generated create add command is correct", expectedConcreteClassFileName,	concreteClassfileName)
		Assert.assertEquals("Concrete file name for the generated create add command is correct", expectedAbstractClassFileName,	abstractClassfileName)
	}
	
	@Test
    def void testCreateForIntrinsicProperties() {
    	concept = '''
			Concept «TEST_CONCEPT_NAME»{
				Category «TEST_CATEGORY_NAME» {
					StringProperty tpSring;
					IntProperty tpInt;
					FloatProperty tpFloat;
					BooleanProperty tpBoolean;
					Resource tpResource;
					EnumProperty tpEnum;
				}
			}
		'''.parse

		val category = concept.categories.get(0)
    	val concreteClassContents = createAddCommandGenerator.createConcreteClass(concept, category)
    	val abstractClassContents = createAddCommandGenerator.createAbstractClass(concept, category)
    	
		GeneratorJunitAssert.assertEqualContent(concreteClassContents, "/resources/expectedOutputFilesForGenerators/CategoryBeanIntrinsicProperties.java")
		GeneratorJunitAssert.assertEqualContent(abstractClassContents, "/resources/expectedOutputFilesForGenerators/ACategoryBeanIntrinsicProperties.java")
    }
    
    @Test
    def void testCreateForIntrinsicArrayProperties() {
    	concept = '''
			Concept «TEST_CONCEPT_NAME»{
				Category «TEST_CATEGORY_NAME» {
					StringProperty tpSringArrayDynamic[];
					StringProperty tpSringArrayStatic[5];
					IntProperty tpIntArrayDynamic[];
					IntProperty tpIntArrayStatic[6];
					FloatProperty tpFloatArrayDynamic[];
					FloatProperty tpFloatArrayStatic[7];
					BooleanProperty tpBooleanArrayDynamic[];
					BooleanProperty tpBooleanArrayStatic[8];
					Resource tpResourceDynamich[];
					Resource tpResourceStatic[9];
					EnumProperty tpEnumDynamich[];
					EnumProperty tpEnumStatic[9];
				}
			}
		'''.parse

		val category = concept.categories.get(0)
    	val concreteClassContents = createAddCommandGenerator.createConcreteClass(concept, category)
    	val abstractClassContents = createAddCommandGenerator.createAbstractClass(concept, category)
    	
		GeneratorJunitAssert.assertEqualContent(concreteClassContents, "/resources/expectedOutputFilesForGenerators/CategoryBeanIntrinsicArrayProperties.java")
		GeneratorJunitAssert.assertEqualContent(abstractClassContents, "/resources/expectedOutputFilesForGenerators/ACategoryBeanIntrinsicArrayProperties.java")
    }
    
     @Test
    def void testCreateEnumPropertyValues(){
    	concept = '''
			Concept testConcept{
				Category TestCategoryA {
					EnumProperty enumProperty  values [LITERAL1=1, LITERAL2=2, LITERAL3=3];
					EnumProperty emptyEnumProperty;
				}
			}
		'''.parse
    	val category = concept.categories.get(0)
    	val abstractClassContents = createAddCommandGenerator.createAbstractClass(concept, category)
    	
		GeneratorJunitAssert.assertEqualContent(abstractClassContents, "/resources/expectedOutputFilesForGenerators/ACategoryBeanEnumProperties.java")
    }
    
    @Test
    def void testCreateForTypesAndReferences() {
    	concept = '''
			Concept testConcept{
				Category TestCategoryA {
					StringProperty tpSring;
				}
				
				Category TestCategoryB {
					Type testSubCategory of Category TestCategoryA;
					Reference testRefCategory of Type TestCategoryA;
					Reference testRefProperty of Type TestCategoryA.tpSring;
				}
			}
		'''.parse

		val referencingCategory = concept.categories.get(1)
    	val referencedCategory = concept.categories.get(0)
    	val referencedProperty = concept.categories.get(0).properties.get(0);
    	val concreteClassContents = createAddCommandGenerator.createConcreteClass(concept, referencingCategory)
    	val abstractClassContents = createAddCommandGenerator.createAbstractClass(concept, referencingCategory)
    	
    	val composedProperty = referencingCategory.properties.get(0) as ComposedProperty;
    	val referenceProperty1 = referencingCategory.properties.get(1) as ReferenceProperty;
    	val referenceProperty2 = referencingCategory.properties.get(2) as ReferenceProperty;
    	
    	Assert.assertEquals("Model is well linked", referencedCategory, composedProperty.type)
    	Assert.assertEquals("Model is well linked", referencedCategory, referenceProperty1.referenceType)
    	Assert.assertEquals("Model is well linked", referencedProperty, referenceProperty2.referenceType)
    	
		GeneratorJunitAssert.assertEqualContent(concreteClassContents, "/resources/expectedOutputFilesForGenerators/CategoryBeanTypesAndReferences.java")
		GeneratorJunitAssert.assertEqualContent(abstractClassContents, "/resources/expectedOutputFilesForGenerators/ACategoryBeanTypesAndReferences.java")
    }
    
    @Test
    def void testCreateForExtension() {
    	concept = '''
			Concept testConcept{
				Category TestCategoryA {
				}
				
				Category TestCategoryB extends TestCategoryA {
				}
			}
		'''.parse

		val extendingCategory = concept.categories.get(1)
    	val extendedCategory = concept.categories.get(0)
    	val concreteClassContents = createAddCommandGenerator.createConcreteClass(concept, extendingCategory)
    	val abstractClassContents = createAddCommandGenerator.createAbstractClass(concept, extendingCategory)
    	
    	Assert.assertEquals("Model is well linked", extendedCategory, extendingCategory.extendsCategory)
    	
		GeneratorJunitAssert.assertEqualContent(concreteClassContents, "/resources/expectedOutputFilesForGenerators/CategoryBeanExtension.java")
		GeneratorJunitAssert.assertEqualContent(abstractClassContents, "/resources/expectedOutputFilesForGenerators/ACategoryBeanExtension.java")
    }
    
    @Test
    def void testCreateForTypesAndReferencesArrays() {
    	concept = '''
			Concept testConcept{
				Category TestCategoryA {
					StringProperty tpSring;
				}
				
				Category TestCategoryB {
					Type testSubCategoryArrayDynamic[] of Category TestCategoryA;
					Type testSubCategoryArrayStatic[2] of Category TestCategoryA;
					Reference testRefCategoryArrayDynamic[] of Type TestCategoryA;
					Reference testRefCategoryArrayStatic[4] of Type TestCategoryA;
					Reference testRefPropertyArrayDynamic[] of Type TestCategoryA.tpSring;
					Reference testRefPropertyArrayStatic[6] of Type TestCategoryA.tpSring;
				}
			}
		'''.parse

		val category = concept.categories.get(1)
    	val concreteClassContents = createAddCommandGenerator.createConcreteClass(concept, category)
    	val abstractClassContents = createAddCommandGenerator.createAbstractClass(concept, category)
    	
		GeneratorJunitAssert.assertEqualContent(concreteClassContents, "/resources/expectedOutputFilesForGenerators/CategoryBeanTypesAndReferencesArrays.java")
		GeneratorJunitAssert.assertEqualContent(abstractClassContents, "/resources/expectedOutputFilesForGenerators/ACategoryBeanTypesAndReferencesArrays.java")
    }
    
    @Test
    def void testCreateForEReferenceWithoutGenmodel() {
    	concept = '''
			Concept testConcept{
				
				EImport "http://www.virsat.sc.dlr.de/external/tests";
					
				Category TestCategory {
					
					EReference testEReference of Type tests.ExternalTestType;
					
					EReference testEReferenceArray[] of Type tests.ExternalTestType;
					
				}
				
			}
		'''.parse(helper.resourceSet)
		
		val category = concept.categories.get(0)
		val abstractClassContents = createAddCommandGenerator.createAbstractClass(concept, category)
		
		GeneratorJunitAssert.assertEqualContent(abstractClassContents, "/resources/expectedOutputFilesForGenerators/ACategoryBeanExternalEReference.java")
    }
    
    @Test
    def void testCreateForEReferenceWithRegisteredGenmodel() {
    	concept = '''
			Concept testConcept{
				
				EImport "http://www.virsat.sc.dlr.de/external/tests" genModel "de.dlr.sc.virsat.model.external.tests/model/ExternalModel.genmodel";
					
				Category TestCategory {
					
					EReference testEReference of Type tests.ExternalTestType;
					
					EReference testEReferenceArray[] of Type tests.ExternalTestType;
					
				}
				
			}
		'''.parse(helper.resourceSet)
		
		val category = concept.categories.get(0)
		val abstractClassContents = createAddCommandGenerator.createAbstractClass(concept, category)
		
		GeneratorJunitAssert.assertEqualContent(abstractClassContents, "/resources/expectedOutputFilesForGenerators/ACategoryBeanExternalEReference.java")
    }
    
}
