/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.concept.generator.commands

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
import de.dlr.sc.virsat.model.dvlm.categories.Category
import de.dlr.sc.virsat.model.concept.test.util.GeneratorJunitAssert

@RunWith(XtextRunner)
@InjectWith(ConceptLanguageTestInjectorProvider)
class GenerateCategoryCreateAddCommandTest {

	@Inject extension ParseHelper<Concept>

	Concept concept
	Category category
	val testConceptName = "testConcept"
	val testCategoryName = "testCategory"
	val createAddCommandGenerator = new GenerateCategoryCreateAddCommand

	@Before
	def void setUp() {
		ConceptsPackage.eINSTANCE.eClass
		
        concept = '''
        	Concept «testConceptName»{
        		Category «testCategoryName» {}
        	}
        '''.parse
        
        category = concept.categories.get(0)
	}
	
	@Test
    def void testCreateConcreteClassFileName() {
    	val fileName = createAddCommandGenerator.createConcreteClassFileName(concept, category)
    	val expectedFileName = "../../" + testConceptName + ".ui/src/" + testConceptName + "/ui/command/CreateAdd" + testCategoryName.toFirstUpper + "Command.java"
    	
    	Assert.assertEquals("Concrete file name for the generated create add command is correct", expectedFileName, fileName)
    }

	@Test
    def void testCreateAbstractClassFileName() {
    	val fileName = createAddCommandGenerator.createAbstractClassFileName(concept, category)
    	
    	val expectedFileName = "../../" + testConceptName + ".ui/src-gen/" + testConceptName + "/ui/command/ACreateAdd" + testCategoryName.toFirstUpper + "Command.java"
    	
    	Assert.assertEquals("Abstract file name for the generated abstract create add command is correct", expectedFileName, fileName)
    }

	@Test
    def void testCreateConcreteClass() {
    	val classContents = createAddCommandGenerator.createConcreteClass(concept, category)
		GeneratorJunitAssert.assertEqualContent(classContents, "/resources/expectedOutputFilesForGenerators/CreateAddTestCategoryCommand.java")
    }

	@Test
    def void testCreateAbstractClass() {
    	val classContents = createAddCommandGenerator.createAbstractClass(concept, category)
		GeneratorJunitAssert.assertEqualContent(classContents, "/resources/expectedOutputFilesForGenerators/ACreateAddTestCategoryCommand.java")
    } 
}
