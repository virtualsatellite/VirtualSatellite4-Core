/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.concept.project;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import de.dlr.sc.virsat.model.concept.project.filecontent.ConceptFeatureXmlGenerator;
import de.dlr.sc.virsat.model.concept.project.filecontent.ConceptPluginUiBuildPropertiesGenerator;

/**
 * Describes the builder information for the Concept Feature
 * @author fisc_ph
 *
 */
public class ConceptFeatureBuilderInfo extends AConceptPluginBuilderInfo {

	public static final String PROJECT_NAME_FEATURE_EXTENSION = ".feature";
	
	/**
	 * Class Constructor of the concept builder
	 * @param projectName The name of the project in which to build a concept
	 */
	public ConceptFeatureBuilderInfo(String projectName) {
		super(projectName);
	}

 	@Override
	public String getProjectName() {
		return projectName + PROJECT_NAME_FEATURE_EXTENSION;
	}

	@Override
	public Set<String> getNatureIds() {
		Set<String> natureIds = new HashSet<>();
		natureIds.add("org.eclipse.pde.FeatureNature");
		return natureIds;
	}

	@Override
	public Set<String> getBuilderIds() {
		Set<String> builderIds = new HashSet<>();
		builderIds.add("org.eclipse.pde.FeatureBuilder");
		return builderIds;
	}
	
	@Override
	public Set<String> getSourceFolders() {
		return null;
	}

	@Override
	public Set<IFileDescription> getFileDesciptors() {
		Set<IFileDescription> fileDescriptors = new HashSet<>();
		fileDescriptors.add(new FileDescriptor("feature.xml", null, new ConceptFeatureXmlGenerator().generateContent(this)));
		fileDescriptors.add(new FileDescriptor("build.properties", null, new ConceptPluginUiBuildPropertiesGenerator().generateContent(this)));
		return fileDescriptors;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Set<String> getFolders() {
		return Collections.EMPTY_SET;
	}
}
