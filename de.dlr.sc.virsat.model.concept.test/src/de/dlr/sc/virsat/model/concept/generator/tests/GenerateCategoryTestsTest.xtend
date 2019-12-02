/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.concept.generator.tests

import de.dlr.sc.virsat.model.concept.ConceptLanguageTestInjectorProvider
import de.dlr.sc.virsat.model.concept.test.util.GeneratorJunitAssert
import de.dlr.sc.virsat.model.dvlm.categories.CategoriesPackage
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.PropertydefinitionsPackage
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

@RunWith(XtextRunner)
@InjectWith(ConceptLanguageTestInjectorProvider)
class GenerateCategoryTestsTest {

	@Inject extension ParseHelper<Concept>

	Concept concept
	val TEST_CONCEPT_NAME = "testConcept"
	val TEST_CATEGORY_NAME = "testCategory"
	val generateCategoryTests = new GenerateCategoryTests();

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
				}
			}
	    '''.parse

		val category = concept.categories.get(0)
		val concreteClassfileName = generateCategoryTests.createConcreteClassFileName(concept, category)
		val abstractClassfileName = generateCategoryTests.createAbstractClassFileName(concept, category)
		val expectedConcreteClassFileName = "../../testConcept.test/src/testConcept/model/TestCategoryTest.java"
		val expectedAbstractClassFileName = "../../testConcept.test/src-gen/testConcept/model/ATestCategoryTest.java"

		Assert.assertEquals("Concrete file name for the generated create add command is correct", expectedConcreteClassFileName,	concreteClassfileName)
		Assert.assertEquals("Concrete file name for the generated create add command is correct", expectedAbstractClassFileName,	abstractClassfileName)
	}
	
	@Test
    def void testCreateForCategoryTests() {
    	concept = '''
			Concept «TEST_CONCEPT_NAME»{
				Category «TEST_CATEGORY_NAME» {
				}
			}
		'''.parse

		val category = concept.categories.get(0)
    	val concreteClassContents = generateCategoryTests.createConcreteClass(concept, category)
    	val abstractClassContents = generateCategoryTests.createAbstractClass(concept, category)
    	
		GeneratorJunitAssert.assertEqualContent(concreteClassContents, "/resources/expectedOutputFilesForGenerators/CategoryBeanTest.java")
		GeneratorJunitAssert.assertEqualContent(abstractClassContents, "/resources/expectedOutputFilesForGenerators/ACategoryBeanTest.java")
    }
   
}
