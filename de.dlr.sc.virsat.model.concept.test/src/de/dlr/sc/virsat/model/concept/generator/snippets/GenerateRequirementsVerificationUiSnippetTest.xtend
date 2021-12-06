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
class GenerateRequirementsVerificationUiSnippetTest {

	@Inject extension ParseHelper<Concept>

	Concept concept
	Category category
	Category verification
	val testConceptName = "testConcept"
	val testCategoryName = "testCategory"
	val generator = new GenerateRequirementsVerificationUiSnippet

	@Before
	def void setUp() {
		ConceptsPackage.eINSTANCE.eClass
		
        concept = '''
        	Concept «testConceptName»{
        		Category «testCategoryName» {
        			FloatProperty testProp verification VerificationTest;
        		}
        		Category VerificationTest {
        			IsVerification;
        		}
        	}
        '''.parse
        
        category = concept.categories.get(0)
        verification = concept.categories.get(1)
	}
	
	@Test
    def void testCreateConcreteClassFileName() {
    	val fileName = generator.createConcreteClassFileName(concept, category)
    	val expectedFileName = "../../" + concept.name + ".ui/src/" + (concept.name + ".ui.snippet").replace(".","/") + "/" 
    	+ "UiSnippetTableRequirementVerifications" + category.name.toFirstUpper + ".java"
    	
    	Assert.assertEquals("Concrete file name for the generated snippet is correct", expectedFileName, fileName)
    }

	@Test
    def void testCreateAbstractClassFileName() {
    	val fileName = generator.createAbstractClassFileName(concept, category)
    	
    	val expectedFileName = "../../" + concept.name + ".ui/src-gen/" + (concept.name + ".ui.snippet").replace(".","/") + "/" 
    	+ "AUiSnippetTableRequirementVerifications" + category.name.toFirstUpper + ".java"
    	
    	Assert.assertEquals("Abstract file name for the generated abstract snippet is correct", expectedFileName, fileName)
    }
    
    @Test
    def void testCreateVerification() {
    	val classContents = generator.createAbstractClass(concept, verification)
		GeneratorJunitAssert.assertEqualContent(classContents, "/resources/expectedOutputFilesForGenerators/AUiSnippetTableVerification.java")
    } 
}