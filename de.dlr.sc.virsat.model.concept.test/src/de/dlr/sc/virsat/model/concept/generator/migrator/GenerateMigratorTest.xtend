/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.concept.generator.migrator

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
class GenerateMigratorTest {

	@Inject extension ParseHelper<Concept>

	Concept concept
	val TEST_CONCEPT_NAME = "testConcept"
	val TEST_VERSION = "1.1"
	
	val migratorGenerator = new GenerateMigrator

	@Before
	def void setUp() {
		ConceptsPackage.eINSTANCE.eClass
		CategoriesPackage.eINSTANCE.eClass
		PropertydefinitionsPackage.eINSTANCE.eClass
	}
	
	@Test
    def void testCreateClassFileName() {
    	concept = '''
			Concept «TEST_CONCEPT_NAME» version «TEST_VERSION» {

			}
		'''.parse
		

		val concreteClassfileName = migratorGenerator.createConcreteClassFileName(concept, concept)
		val abstractClassfileName = migratorGenerator.createAbstractClassFileName(concept, concept)
		val expectedConcreteClassFileName = TEST_CONCEPT_NAME + "/migrator/Migrator1v1.java"
		val expectedAbstractClassFileName = TEST_CONCEPT_NAME + "/migrator/AMigrator1v1.java"

		Assert.assertEquals("Concrete file name for the generated create add command is correct", expectedConcreteClassFileName,	concreteClassfileName)
		Assert.assertEquals("Abstract file name for the generated create add command is correct", expectedAbstractClassFileName,	abstractClassfileName)
	}
	
	@Test
    def void testCreateMigrator() {
    	concept = '''
			Concept «TEST_CONCEPT_NAME» version «TEST_VERSION» {

			}
		'''.parse
		
		val expectedAbstract = migratorGenerator.createAbstractClass(concept, concept);
		val expectedConcrete = migratorGenerator.createConcreteClass(concept, concept);
		
		GeneratorJunitAssert.assertEqualContent(expectedAbstract, "/resources/expectedOutputFilesForGenerators/AMigrator1v1.java");
		GeneratorJunitAssert.assertEqualContent(expectedConcrete, "/resources/expectedOutputFilesForGenerators/Migrator1v1.java");
	}
}
