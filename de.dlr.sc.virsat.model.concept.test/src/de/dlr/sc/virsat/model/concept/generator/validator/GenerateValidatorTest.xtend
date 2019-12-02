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
	val testConceptName = "TestConcept"
	val testConceptNameLong = "de.dlr.sc.virsat.model.extension.testConcept"
	val validatorGenerator = new GenerateValidator

	@Before
	def void setUp() {
		ConceptsPackage.eINSTANCE.eClass
		
		concept = '''
			Concept «testConceptName»{}
		'''.parse
	}
	
	@Test 
	def void testGetValidatorName() {
		concept = '''
			Concept «testConceptNameLong»{}
		'''.parse

		val validatorName = GenerateValidator.getValidatorName(concept)

		val expectedValidatorName = testConceptName

		Assert.assertEquals("Validator name for the generated validator is correct", expectedValidatorName, validatorName)
	}
	
	@Test
	def void testCreateConcreteClassFileName() {
		val fileName = validatorGenerator.createConcreteClassFileName(concept, concept)
		
		val expectedFileName = testConceptName + "/validator/TestConceptValidator.java"
		
		Assert.assertEquals("Concrete file name for the generated validator is correct", expectedFileName, fileName)
	}
	
	@Test
	def void testCreateAbstractClassFileName() {
		val fileName = validatorGenerator.createAbstractClassFileName(concept, concept)
		
		val expectedFileName = testConceptName + "/validator/ATestConceptValidator.java"
		
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