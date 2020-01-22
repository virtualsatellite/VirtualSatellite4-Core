/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.concept.generator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Set;

import org.eclipse.xtext.generator.IFileSystemAccess;
import org.eclipse.xtext.generator.OutputConfiguration;
import org.junit.Test;

/**
 * Test Cases for the ConceptOutputConfigurationProvider
 * @author lobe_el
 *
 */
public class ConceptOutputConfigurationProviderTest {
	
	@Test
	public void testGetOutputConfigurations() {
		final int NUMBER_OF_OUTPUTS = 4;

		final String GENERATOR_OUTPUT_ID_CONCEPT = "de.dlr.sc.virsat.model.concept.output";
		final String GENERATOR_OUTPUT_DESCRIPTION_CONCEPT = "Xtend Concept Serialzation Output";
		final String GENERATOR_OUTPUT_FOLDER_CONCEPT = "./concept";

		final String GENERATOR_OUTPUT_ID_SOURCE = "de.dlr.sc.virsat.model.xtend.output";
		final String GENERATOR_OUTPUT_FOLDER_SOURCE = "./src-gen";
		final String GENERATOR_OUTPUT_DESCRIPTION_SOURCE = "Xtend Generated Source Output";

		final String GENERATOR_OUTPUT_ID_DEFAULT = IFileSystemAccess.DEFAULT_OUTPUT;
		final String GENERATOR_OUTPUT_FOLDER_DEFAULT = "./default-gen";
		final String GENERATOR_OUTPUT_DESCRIPTION_DEFAULT = "Xtend Default Output";
		
		final String GENERATOR_OUTPUT_ID_GENERATE_ONCE = "de.dlr.sc.virsat.model.xtend.generate.once";
		final String GENERATOR_OUTPUT_FOLDER_GENERATE_ONCE = "./src";
		final String GENERATOR_OUTPUT_DESCRIPTION_GENERATE_ONCE = "Xtend Generate Once src folder";
		
		ConceptOutputConfigurationProvider cocp = new ConceptOutputConfigurationProvider();
		Set<OutputConfiguration> outputConfigurations = cocp.getOutputConfigurations();

		assertEquals("The number of Output Configurations is correct", NUMBER_OF_OUTPUTS, outputConfigurations.size()); 

		for (OutputConfiguration outputConf : outputConfigurations) {
			String confName = outputConf.getName();
			switch (confName) {
				case GENERATOR_OUTPUT_ID_CONCEPT: 
					assertEquals("Description is set correctly", GENERATOR_OUTPUT_DESCRIPTION_CONCEPT, outputConf.getDescription());
					assertEquals("Folder is set correctly", GENERATOR_OUTPUT_FOLDER_CONCEPT, outputConf.getOutputDirectory());
					assertTrue("OverrideExistingResources is set correctly", outputConf.isOverrideExistingResources());
					assertTrue("CreateOutputDirectory is set correctly", outputConf.isCreateOutputDirectory());
					assertFalse("CleanUpDerivedResources is set correctly", outputConf.isCleanUpDerivedResources());
					assertFalse("SetDerivedProperty is set correctly", outputConf.isSetDerivedProperty());
					break;
				case GENERATOR_OUTPUT_ID_SOURCE:
					assertEquals("Description is set correctly", GENERATOR_OUTPUT_DESCRIPTION_SOURCE, outputConf.getDescription());
					assertEquals("Folder is set correctly", GENERATOR_OUTPUT_FOLDER_SOURCE, outputConf.getOutputDirectory());
					assertTrue("OverrideExistingResources is set correctly", outputConf.isOverrideExistingResources());
					assertTrue("CreateOutputDirectory is set correctly", outputConf.isCreateOutputDirectory());
					assertFalse("CleanUpDerivedResources is set correctly", outputConf.isCleanUpDerivedResources());
					assertFalse("SetDerivedProperty is set correctly", outputConf.isSetDerivedProperty());
					break;
				case GENERATOR_OUTPUT_ID_DEFAULT:
					assertEquals("Description is set correctly", GENERATOR_OUTPUT_DESCRIPTION_DEFAULT, outputConf.getDescription());
					assertEquals("Folder is set correctly", GENERATOR_OUTPUT_FOLDER_DEFAULT, outputConf.getOutputDirectory());
					assertTrue("OverrideExistingResources is set correctly", outputConf.isOverrideExistingResources());
					assertTrue("CreateOutputDirectory is set correctly", outputConf.isCreateOutputDirectory());
					assertTrue("CleanUpDerivedResources is set correctly", outputConf.isCleanUpDerivedResources());
					assertTrue("SetDerivedProperty is set correctly", outputConf.isSetDerivedProperty());
					break;
				case GENERATOR_OUTPUT_ID_GENERATE_ONCE:
					assertEquals("Description is set correctly", GENERATOR_OUTPUT_DESCRIPTION_GENERATE_ONCE, outputConf.getDescription());
					assertEquals("Folder is set correctly", GENERATOR_OUTPUT_FOLDER_GENERATE_ONCE, outputConf.getOutputDirectory());
					assertFalse("OverrideExistingResources is set correctly", outputConf.isOverrideExistingResources());
					assertTrue("CreateOutputDirectory is set correctly", outputConf.isCreateOutputDirectory());
					assertFalse("CleanUpDerivedResources is set correctly", outputConf.isCleanUpDerivedResources());
					assertFalse("SetDerivedProperty is set correctly", outputConf.isSetDerivedProperty());
					break;
				default:
					fail("Unexpected configuration name");
					break;
			}
		}
	}
	
}
