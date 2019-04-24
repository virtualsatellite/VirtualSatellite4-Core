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

import de.dlr.sc.virsat.model.concept.project.filecontent.ConceptFragmentTestBuildPropertiesGenerator;
import de.dlr.sc.virsat.model.concept.project.filecontent.ConceptFragmentTestManifestGenerator;

/**
 * Describes the builder information for a Concept Plugin Test Fragment
 * @author fisc_ph
 *
 */
public class ConceptFragmentTestBuilderInfo extends AConceptPluginBuilderInfo {

	/**
	 * Class Constructor of the concept builder
	 * @param projectName The name of the project in which to build a concept
	 */
	public ConceptFragmentTestBuilderInfo(String projectName) {
		super(projectName);
	}

 	@Override
	public String getProjectName() {
		return projectName + ".test";
	}


	@Override
	public Set<String> getNatureIds() {
		Set<String> natureIds = new HashSet<>();
		natureIds.add("org.eclipse.pde.PluginNature");
		natureIds.add("org.eclipse.jdt.core.javanature");
		natureIds.add("org.eclipse.xtext.ui.shared.xtextNature");
		return natureIds;
	}

	@Override
	public Set<String> getBuilderIds() {
		Set<String> builderIds = new HashSet<>();
		builderIds.add("org.eclipse.jdt.core.javabuilder");
		builderIds.add("org.eclipse.pde.ManifestBuilder");
		builderIds.add("org.eclipse.pde.SchemaBuilder");
		builderIds.add("org.eclipse.xtext.ui.shared.xtextBuilder");
		return builderIds;
	}

	@Override
	public Set<String> getSourceFolders() {
		Set<String> classpathEntries = new HashSet<>();
		classpathEntries.add("src");
		classpathEntries.add("src-gen");
		classpathEntries.add("xtend-gen");
		return classpathEntries;
	}

	@Override
	public Set<IFileDescription> getFileDesciptors() {
		Set<IFileDescription> fileDescriptors = new HashSet<>();
		fileDescriptors.add(new FileDescriptor("MANIFEST.MF", "META-INF", new ConceptFragmentTestManifestGenerator().generateContent(this)));
		fileDescriptors.add(new FileDescriptor("build.properties", null, new ConceptFragmentTestBuildPropertiesGenerator().generateContent(this)));
		return fileDescriptors;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Set<String> getFolders() {
		return Collections.EMPTY_SET;
	}
}
