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
class GenerateAllTestsTest {

	@Inject extension ParseHelper<Concept>

	Concept concept
	val TEST_CONCEPT_NAME = "de.dlr.sc.virsat.model.extension.testConcept"
	val TEST_CATEGORY_NAME = "testCategory"
	val TEST_STRUCTURAL_ELEMENT_NAME = "testStructuralElement"
	val generateAllTests = new GenerateAllTests();

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
				StructuralElement «TEST_STRUCTURAL_ELEMENT_NAME» {
				}
			}
	    '''.parse

		val category = concept.categories.get(0)
		val concreteClassfileName = generateAllTests.createConcreteClassFileName(concept, category)
		val abstractClassfileName = generateAllTests.createAbstractClassFileName(concept, category)
		val expectedConcreteClassFileName = "../../" + TEST_CONCEPT_NAME + ".test/src/" + TEST_CONCEPT_NAME.replace(".","/") + "/test/AllTests.java"
		val expectedAbstractClassFileName = "../../" + TEST_CONCEPT_NAME + ".test/src-gen/" + TEST_CONCEPT_NAME.replace(".","/") + "/test/AllTestsGen.java"

		Assert.assertEquals("Concrete file name for the generated create add command is correct", expectedConcreteClassFileName, concreteClassfileName)
		Assert.assertEquals("Abstract file name for the generated create add command is correct", expectedAbstractClassFileName, abstractClassfileName)
	}
	
	@Test
    def void testCreateForAllTestsClasses() {
    	concept = '''
			Concept «TEST_CONCEPT_NAME» {
				Category «TEST_CATEGORY_NAME» {
				}
				StructuralElement «TEST_STRUCTURAL_ELEMENT_NAME» {
				}
			}
		'''.parse

		val category = concept.categories.get(0)
    	val concreteClassContents = generateAllTests.createConcreteClass(concept, category)
    	val abstractClassContents = generateAllTests.createAbstractClass(concept, category)
    	
		GeneratorJunitAssert.assertEqualContent(concreteClassContents, "/resources/expectedOutputFilesForGenerators/AllTests.java")
		GeneratorJunitAssert.assertEqualContent(abstractClassContents, "/resources/expectedOutputFilesForGenerators/AllTestsGen.java")
    }
   
}
