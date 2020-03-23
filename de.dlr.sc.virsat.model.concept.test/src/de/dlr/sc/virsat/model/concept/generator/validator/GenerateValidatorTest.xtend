/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
 package de.dlr.sc.virsat.model.concept.generator.validator

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
class GenerateValidatorTest {

	@Inject extension ParseHelper<Concept>

	Concept concept
	val conceptID = "de.dlr.sc.virsat.model.extension.testConcept"
	val expectedConceptShortName = "TestConcept"
	val validatorGenerator = new GenerateValidator

	@Before
	def void setUp() {
		ConceptsPackage.eINSTANCE.eClass
		
		concept = '''
			Concept «conceptID»{}
		'''.parse
	}
	
	@Test 
	def void testGetValidatorName() {
		val validatorName = GenerateValidator.getValidatorName(concept)

		val expectedValidatorName = expectedConceptShortName + "Validator"

		Assert.assertEquals("Validator name for the generated validator is correct", expectedValidatorName, validatorName)
	}
	
	@Test
	def void testCreateConcreteClassFileName() {
		val fileName = validatorGenerator.createConcreteClassFileName(concept, concept)
		
		val expectedFileName = conceptID.replace(".","/") + "/" + "validator" + "/" + expectedConceptShortName + "Validator.java"
		
		Assert.assertEquals("Concrete file name for the generated validator is correct", expectedFileName, fileName)
	}
	
	@Test
	def void testCreateAbstractClassFileName() {
		val fileName = validatorGenerator.createAbstractClassFileName(concept, concept)
		
		val expectedFileName = conceptID.replace(".","/") + "/" + "validator" + "/A" + expectedConceptShortName + "Validator.java"
		
		Assert.assertEquals("Abstract file name for the generated validator is correct", expectedFileName, fileName)
	}

	@Test
	def void testCreateConcreteClass() {
		val classContents = validatorGenerator.createConcreteClass(concept, concept)
		GeneratorJunitAssert.assertEqualContent(classContents, "/resources/expectedOutputFilesForGenerators/TestConceptValidator.java")
	}

	@Test
	def void testCreateAbstractClass() {
		val classContents = validatorGenerator.createAbstractClass(concept, concept)
		GeneratorJunitAssert.assertEqualContent(classContents, "/resources/expectedOutputFilesForGenerators/ATestConceptValidator.java")
	}
}