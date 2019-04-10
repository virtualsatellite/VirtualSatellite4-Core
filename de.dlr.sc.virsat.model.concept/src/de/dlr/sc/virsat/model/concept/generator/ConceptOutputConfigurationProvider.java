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

import java.util.HashSet;
import java.util.Set;

import org.eclipse.xtext.generator.IFileSystemAccess;
import org.eclipse.xtext.generator.IOutputConfigurationProvider;
import org.eclipse.xtext.generator.OutputConfiguration;

/**
 * Output Configuration provider that sets up output directories of XTend
 * generated output. For example this will define where to put the XMI
 * serializations of the textual Concept  model
 * 
 * @author fisc_ph
 *
 */
public class ConceptOutputConfigurationProvider implements IOutputConfigurationProvider {

	public static final String GENERATOR_OUTPUT_ID_SOURCE = "de.dlr.sc.virsat.model.xtend.output";
	public static final String GENERATOR_OUTPUT_FOLDER_SOURCE = "./src-gen";
	public static final String GENERATOR_OUTPUT_DESCRIPTION_SOURCE = "Xtend Generated Source Output";

	public static final String GENERATOR_OUTPUT_ID_CONCEPT = "de.dlr.sc.virsat.model.concept.output";
	public static final String GENERATOR_OUTPUT_DESCRIPTION_CONCEPT = "Xtend Concept Serialzation Output";
	public static final String GENERATOR_OUTPUT_FOLDER_CONCEPT = "./concept";

	public static final String GENERATOR_OUTPUT_ID_DEFAULT = IFileSystemAccess.DEFAULT_OUTPUT;
	public static final String GENERATOR_OUTPUT_FOLDER_DEFAULT = "./default-gen";
	public static final String GENERATOR_OUTPUT_DESCRIPTION_DEFAULT = "Xtend Default Output";
	
	public static final String GENERATOR_OUTPUT_ID_GENERATE_ONCE = "de.dlr.sc.virsat.model.xtend.generate.once";
	public static final String GENERATOR_OUTPUT_FOLDER_GENERATE_ONCE = "./src";
	public static final String GENERATOR_OUTPUT_DESCRIPTION_GENERATE_ONCE = "Xtend Generate Once src folder";

	/**
	 * @return a set of {@link OutputConfiguration} available for the generator
	 */
	public Set<OutputConfiguration> getOutputConfigurations() {
		Set<OutputConfiguration> outputConfigurations = new HashSet<>();
		
		OutputConfiguration conceptOutput = new OutputConfiguration(GENERATOR_OUTPUT_ID_CONCEPT);
		conceptOutput.setDescription(GENERATOR_OUTPUT_DESCRIPTION_CONCEPT);
		conceptOutput.setOutputDirectory(GENERATOR_OUTPUT_FOLDER_CONCEPT);
		conceptOutput.setOverrideExistingResources(true);
		conceptOutput.setCreateOutputDirectory(true);
		conceptOutput.setCleanUpDerivedResources(false);
		conceptOutput.setSetDerivedProperty(false);
		
		outputConfigurations.add(conceptOutput);
		
		OutputConfiguration sourceOutput = new OutputConfiguration(GENERATOR_OUTPUT_ID_SOURCE);
		sourceOutput.setDescription(GENERATOR_OUTPUT_DESCRIPTION_SOURCE);
		sourceOutput.setOutputDirectory(GENERATOR_OUTPUT_FOLDER_SOURCE);
		sourceOutput.setOverrideExistingResources(true);
		sourceOutput.setCreateOutputDirectory(true);
		sourceOutput.setCleanUpDerivedResources(false);
		sourceOutput.setSetDerivedProperty(false);
		
		outputConfigurations.add(sourceOutput);
		
		OutputConfiguration genOnceOutput = new OutputConfiguration(GENERATOR_OUTPUT_ID_GENERATE_ONCE);
		genOnceOutput.setDescription(GENERATOR_OUTPUT_DESCRIPTION_GENERATE_ONCE);
		genOnceOutput.setOutputDirectory(GENERATOR_OUTPUT_FOLDER_GENERATE_ONCE);
		genOnceOutput.setOverrideExistingResources(false);
		genOnceOutput.setCreateOutputDirectory(true);
		genOnceOutput.setCleanUpDerivedResources(false);
		genOnceOutput.setSetDerivedProperty(false);
		
		outputConfigurations.add(genOnceOutput);
		
		
		OutputConfiguration defaultOutput = new OutputConfiguration(GENERATOR_OUTPUT_ID_DEFAULT);
		defaultOutput.setDescription(GENERATOR_OUTPUT_DESCRIPTION_DEFAULT);
		defaultOutput.setOutputDirectory(GENERATOR_OUTPUT_FOLDER_DEFAULT);
		defaultOutput.setOverrideExistingResources(true);
		defaultOutput.setCreateOutputDirectory(true);
		defaultOutput.setCleanUpDerivedResources(true);
		defaultOutput.setSetDerivedProperty(true);

		outputConfigurations.add(defaultOutput);
		
		return outputConfigurations;
	}
}
