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

import java.util.HashSet;
import java.util.Set;

/**
 * Describes the Builder Information that is shared across the Concept Plugins
 * @author fisc_ph
 *
 */
public abstract class AConceptPluginBuilderInfo implements IProjectBuilderInfo {

	protected String projectName;
	
	/**
	 * Class Constructor of the concept builder
	 * @param projectName The name of the project in which to build a concept
	 */
	public AConceptPluginBuilderInfo(String projectName) {
		this.projectName = projectName;
	}

	@Override
	public String getOutputFolder() {
		return "target/classes";
	}

	@Override
	public Set<String> getContainers() {
		Set<String> classpathEntries = new HashSet<>();
		classpathEntries.add("org.eclipse.jdt.launching.JRE_CONTAINER/org.eclipse.jdt.internal.debug.ui.launcher.StandardVMType/JavaSE-1.8");
		classpathEntries.add("org.eclipse.pde.core.requiredPlugins");
		return classpathEntries;
	}
	
	/**
	 * Anonymous class to describe files and their contents for the generation process
	 * @author fisc_ph
	 *
	 */
	protected static class FileDescriptor implements IFileDescription {

		/**
		 * Constructor to set the file its content and path
		 * @param fileName the name of the file
		 * @param folderName the folder in which the file should be generated
		 * @param fileContent the content of the file
		 */
		public FileDescriptor(String fileName, String folderName, String fileContent) {
			this.fileName = fileName;
			this.folderName = folderName;
			this.fileContent = fileContent;
		}

		private String fileName;
		private String folderName;
		private String fileContent;
		
		@Override
		public String getFileName() {
			return fileName;
		}

		@Override
		public String getFolderName() {
			return folderName;
		}

		@Override
		public String getFileContent() {
			return fileContent;
		}
	}
}