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
class GenerateStructuralElementTestsTest {

	@Inject extension ParseHelper<Concept>

	Concept concept
	static val TEST_CONCEPT_NAME = "testConcept"
	static val TEST_STRUCTURAL_ELEMENT_NAME = "testStructuralElement"
	val generateStructuralElementTests = new GenerateStructuralElementTests();

	@Before
	def void setUp() {
		ConceptsPackage.eINSTANCE.eClass
	}
	
	@Test
    def void testCreateConcreteClassFileName() {
      	concept = '''
			Concept «TEST_CONCEPT_NAME» {
				StructuralElement «TEST_STRUCTURAL_ELEMENT_NAME» {
				}
			}
	    '''.parse

		val structuralElement = concept.structuralElements.get(0);
		val concreteClassfileName = generateStructuralElementTests.createConcreteClassFileName(concept, structuralElement)
		val abstractClassfileName = generateStructuralElementTests.createAbstractClassFileName(concept, structuralElement)
		val expectedConcreteClassFileName = "../../testConcept.test/src/testConcept/model/TestStructuralElementTest.java"
		val expectedAbstractClassFileName = "../../testConcept.test/src-gen/testConcept/model/ATestStructuralElementTest.java"

		Assert.assertEquals("Concrete file name for the generated create add command is correct", expectedConcreteClassFileName,	concreteClassfileName)
		Assert.assertEquals("Concrete file name for the generated create add command is correct", expectedAbstractClassFileName,	abstractClassfileName)
	}
	
	@Test
    def void testCreateStructuralElementTestClasses() {
    	concept = '''
			Concept «TEST_CONCEPT_NAME»{
				StructuralElement «TEST_STRUCTURAL_ELEMENT_NAME» {
				}
			}
		'''.parse

		val structuralElement = concept.structuralElements.get(0);
		val concreteClassContents = generateStructuralElementTests.createConcreteClass(concept, structuralElement)
    	val abstractClassContents = generateStructuralElementTests.createAbstractClass(concept, structuralElement)
    	
		GeneratorJunitAssert.assertEqualContent(concreteClassContents, "/resources/expectedOutputFilesForGenerators/StructuralElementTest.java")
		GeneratorJunitAssert.assertEqualContent(abstractClassContents, "/resources/expectedOutputFilesForGenerators/AStructuralElementTest.java")
    }
}
