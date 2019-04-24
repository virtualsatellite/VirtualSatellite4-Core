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
class GenerateStructuralElementBeansTest {

	@Inject extension ParseHelper<Concept>

	Concept concept
	static val TEST_CONCEPT_NAME = "testConcept"
	static val TEST_STRUCTURAL_ELEMENT_NAME = "testStructuralElement"
	val createAddCommandGenerator = new GenerateStructuralElementBeans();

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
		val concreteClassfileName = createAddCommandGenerator.createConcreteClassFileName(concept, structuralElement)
		val abstractClassfileName = createAddCommandGenerator.createAbstractClassFileName(concept, structuralElement)
		val expectedConcreteClassFileName = TEST_CONCEPT_NAME + "/model/" +	TEST_STRUCTURAL_ELEMENT_NAME.toFirstUpper + ".java"
		val expectedAbstractClassFileName = TEST_CONCEPT_NAME + "/model/A" + TEST_STRUCTURAL_ELEMENT_NAME.toFirstUpper + ".java"

		Assert.assertEquals("Concrete file name for the generated create add command is correct", expectedConcreteClassFileName,	concreteClassfileName)
		Assert.assertEquals("Concrete file name for the generated create add command is correct", expectedAbstractClassFileName,	abstractClassfileName)
	}
	
	@Test
    def void testCreateStructuralElementClasses() {
    	concept = '''
			Concept «TEST_CONCEPT_NAME»{
				StructuralElement «TEST_STRUCTURAL_ELEMENT_NAME» {
				}
			}
		'''.parse

		val structuralElement = concept.structuralElements.get(0);
		val concreteClassContents = createAddCommandGenerator.createConcreteClass(concept, structuralElement)
    	val abstractClassContents = createAddCommandGenerator.createAbstractClass(concept, structuralElement)
    	
		GeneratorJunitAssert.assertEqualContent(concreteClassContents, "/resources/expectedOutputFilesForGenerators/StructuralElementBean.java")
		GeneratorJunitAssert.assertEqualContent(abstractClassContents, "/resources/expectedOutputFilesForGenerators/AStructuralElementBean.java")
    }
    
}
