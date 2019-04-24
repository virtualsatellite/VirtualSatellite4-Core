/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.concept.generator.handler

import de.dlr.sc.virsat.model.dvlm.concepts.Concept
import de.dlr.sc.virsat.model.dvlm.concepts.ConceptsPackage
import de.dlr.sc.virsat.model.concept.ConceptLanguageTestInjectorProvider
import javax.inject.Inject
import org.eclipse.xtext.testing.InjectWith
import org.eclipse.xtext.testing.XtextRunner
import org.eclipse.xtext.testing.util.ParseHelper
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import de.dlr.sc.virsat.model.concept.test.util.GeneratorJunitAssert
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElement

@RunWith(XtextRunner)
@InjectWith(ConceptLanguageTestInjectorProvider)
class GenerateStructuralElementInstanceAddHandlerTest {

	@Inject extension ParseHelper<Concept>

	Concept concept
	StructuralElement StructuralElement
	val testConceptName = "testConcept"
	val testStructuralElementName = "testStructuralElement"
	val structuralElementInstanceAddHandlerGenerator = new GenerateStructuralElementInstanceAddHandler

	@Before
	def void setUp() {
		ConceptsPackage.eINSTANCE.eClass
		
        concept = '''
        	Concept «testConceptName»{
        		StructuralElement «testStructuralElementName» {}
        	}
        '''.parse
        
        StructuralElement = concept.structuralElements.get(0)
	}
	
	@Test
    def void testCreateConcreteClassFileName() {
    	val fileName = structuralElementInstanceAddHandlerGenerator.createConcreteClassFileName(concept, StructuralElement)
    	val expectedFileName = "../../" + testConceptName + ".ui/src/" + testConceptName + "/ui/handler/AddStructuralElementInstance" + testStructuralElementName.toFirstUpper + "Handler.java"
    	
    	Assert.assertEquals("Concrete file name for the generated add handler is correct", expectedFileName, fileName)
    }

	@Test
    def void testCreateAbstractClassFileName() {
    	val fileName = structuralElementInstanceAddHandlerGenerator.createAbstractClassFileName(concept, StructuralElement)
    	
    	val expectedFileName = "../../" + testConceptName + ".ui/src-gen/" + testConceptName + "/ui/handler/AAddStructuralElementInstance" + testStructuralElementName.toFirstUpper + "Handler.java"
    	
    	Assert.assertEquals("Abstract file name for the generated abstract add handler is correct", expectedFileName, fileName)
    }

	@Test
    def void testCreateConcreteClass() {
    	val classContents = structuralElementInstanceAddHandlerGenerator.createConcreteClass(concept, StructuralElement)
		GeneratorJunitAssert.assertEqualContent(classContents, "/resources/expectedOutputFilesForGenerators/AddTestSEIHandler.java")
    }

	@Test
    def void testCreateAbstractClass() {
    	val classContents = structuralElementInstanceAddHandlerGenerator.createAbstractClass(concept, StructuralElement)
		GeneratorJunitAssert.assertEqualContent(classContents, "/resources/expectedOutputFilesForGenerators/AAddTestSEIHandler.java")
    } 
}
