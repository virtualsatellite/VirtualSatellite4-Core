/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.concept.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import de.dlr.sc.virsat.model.concept.ConceptLanguageTest;
import de.dlr.sc.virsat.model.concept.builder.resources.ResourceAccessBuilderTest;
import de.dlr.sc.virsat.model.concept.generator.ConceptOutputConfigurationProviderTest;
import de.dlr.sc.virsat.model.concept.generator.ConceptPreprocessorTest;
import de.dlr.sc.virsat.model.concept.generator.ImportManagerTest;
import de.dlr.sc.virsat.model.concept.generator.beans.GenerateCategoryBeansTest;
import de.dlr.sc.virsat.model.concept.generator.beans.GenerateStructuralElementBeansTest;
import de.dlr.sc.virsat.model.concept.generator.commands.GenerateArrayCreateAddCommandTest;
import de.dlr.sc.virsat.model.concept.generator.commands.GenerateCategoryCreateAddCommandTest;
import de.dlr.sc.virsat.model.concept.generator.dmf.GenerateDmfCategoriesTest;
import de.dlr.sc.virsat.model.concept.generator.handler.GenerateCategoryAddHandlerTest;
import de.dlr.sc.virsat.model.concept.generator.handler.GenerateStructuralElementInstanceAddHandlerTest;
import de.dlr.sc.virsat.model.concept.generator.migrator.GenerateMigratorTest;
import de.dlr.sc.virsat.model.concept.generator.plugin.GenerateConceptPluginXmlTest;
import de.dlr.sc.virsat.model.concept.generator.plugin.GenerateConceptUiPluginXmlTest;
import de.dlr.sc.virsat.model.concept.generator.propertyTester.GenerateConceptEnabledTesterTest;
import de.dlr.sc.virsat.model.concept.generator.snippets.GenerateCategoryUiSnippetArrayTableTest;
import de.dlr.sc.virsat.model.concept.generator.snippets.GenerateCategoryUiSnippetPropertySectionTest;
import de.dlr.sc.virsat.model.concept.generator.snippets.GenerateCategoryUiSnippetTableTest;
import de.dlr.sc.virsat.model.concept.generator.tests.GenerateAllTestsTest;
import de.dlr.sc.virsat.model.concept.generator.tests.GenerateCategoryTestsTest;
import de.dlr.sc.virsat.model.concept.generator.tests.GenerateMigratorTestsTest;
import de.dlr.sc.virsat.model.concept.generator.tests.GenerateStructuralElementTestsTest;
import de.dlr.sc.virsat.model.concept.generator.tests.GenerateValidatorTestsTest;
import de.dlr.sc.virsat.model.concept.generator.validator.GenerateValidatorTest;
import de.dlr.sc.virsat.model.concept.project.ConceptProjectGenerationRunnableTest;
import de.dlr.sc.virsat.model.concept.project.filecontent.ConceptFeatureBuildPropertiesGeneratorTest;
import de.dlr.sc.virsat.model.concept.project.filecontent.ConceptFeatureXmlGeneratorTest;
import de.dlr.sc.virsat.model.concept.project.filecontent.ConceptFragmentTestBuildPropertiesGeneratorTest;
import de.dlr.sc.virsat.model.concept.project.filecontent.ConceptFragmentTestManifestGeneratorTest;
import de.dlr.sc.virsat.model.concept.project.filecontent.ConceptPluginBuildPropertiesGeneratorTest;
import de.dlr.sc.virsat.model.concept.project.filecontent.ConceptPluginExampleConceptGeneratorTest;
import de.dlr.sc.virsat.model.concept.project.filecontent.ConceptPluginManifestGeneratorTest;
import de.dlr.sc.virsat.model.concept.project.filecontent.ConceptPluginUiBuildPropertiesGeneratorTest;
import de.dlr.sc.virsat.model.concept.project.filecontent.ConceptPluginUiManifestGeneratorTest;
import junit.framework.JUnit4TestAdapter;

/**
 * 
 */
@RunWith(Suite.class)

@SuiteClasses({
				ConceptLanguageTest.class,
				GenerateDmfCategoriesTest.class,
				GenerateStructuralElementBeansTest.class,
				GenerateCategoryBeansTest.class,
				GenerateValidatorTest.class,
				GenerateArrayCreateAddCommandTest.class,
				GenerateCategoryCreateAddCommandTest.class,
				GenerateCategoryAddHandlerTest.class,
				GenerateCategoryTestsTest.class,
				GenerateStructuralElementTestsTest.class,
				GenerateStructuralElementInstanceAddHandlerTest.class,
				GenerateCategoryUiSnippetArrayTableTest.class,
				GenerateCategoryUiSnippetPropertySectionTest.class,
				GenerateCategoryUiSnippetTableTest.class,
				ConceptFeatureBuildPropertiesGeneratorTest.class,
				ConceptFeatureXmlGeneratorTest.class,
				ConceptPluginBuildPropertiesGeneratorTest.class,
				ConceptPluginUiBuildPropertiesGeneratorTest.class,
				ConceptPluginManifestGeneratorTest.class,
				ConceptPluginUiManifestGeneratorTest.class,
				ConceptPluginExampleConceptGeneratorTest.class,
				ConceptProjectGenerationRunnableTest.class,
				GenerateMigratorTest.class,
				GenerateConceptUiPluginXmlTest.class,
				GenerateConceptPluginXmlTest.class,
				ConceptOutputConfigurationProviderTest.class,
				GenerateConceptEnabledTesterTest.class,
				ImportManagerTest.class,
				ConceptFragmentTestBuildPropertiesGeneratorTest.class,
				ConceptFragmentTestManifestGeneratorTest.class,
				GenerateAllTestsTest.class,
				GenerateMigratorTestsTest.class,
				GenerateValidatorTestsTest.class,
				ResourceAccessBuilderTest.class,
				ConceptPreprocessorTest.class
				})

/**
 * 
 * Test Collection
 *
 */
public class AllTests {

	/**
	 * Constructor
	 */
	private AllTests() {
	}
	
	/**
	 * Test Adapter
	 * @return Executable JUnit Tests
	 */
	public static junit.framework.Test suite() {
		return new JUnit4TestAdapter(AllTests.class);
	}	
}