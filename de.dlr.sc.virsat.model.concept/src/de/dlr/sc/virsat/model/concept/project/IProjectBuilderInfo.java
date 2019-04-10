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

import java.util.Set;

/**
 * Interface for project builders. The project builders will be sued to
 * create the three projects when we create a new concept using the
 * concept IDE tool wizzard. This iwll create a feature a plugin and a ui plugin
 * @author fisc_ph
 *
 */
public interface IProjectBuilderInfo {

	/**
	 * Method to get the name of the project to be generated
	 * @return the name as string
	 */
	String getProjectName();

	/**
	 * method o get the nature IDs that should be associated with the newly generated project
	 * @return list of strings containing the IDs
	 */
	Set<String> getNatureIds();

	/**
	 * Method to get all builder IDs which should be associated with the generated project
	 * @return list of strings containing the builder IDs
	 */
	Set<String> getBuilderIds();

	/**
	 * Method to get a List of source folders that should be generated in the project
	 * @return List of strings with source folder names
	 */
	Set<String> getSourceFolders();
	
	/**
	 * Method to get a list of folder names that should be generated with the project
	 * @return List of strings giving the folder names
	 */
	Set<String> getFolders();

	/**
	 * Method to hand back an output folder name to the generated project e.g. "bin" or "target"
	 * @return a String having the name of the output folder
	 */
	String getOutputFolder();

	/**
	 * Method to hand back a list of containers that should be genearted in the project
	 * @return list of strings with container names
	 */
	Set<String> getContainers();

	/**
	 * Method to hand back a list of FileDescriptors declaring the files that should be generated
	 * @return a list of FileDescriptors
	 */	
	Set<IFileDescription> getFileDesciptors();
	
	/**
	 * This class describes the file descriptors that are used by the builders to
	 * directly place new / initial files into the newly generated projects
	 * @author fisc_ph
	 *
	 */
	public interface IFileDescription {
		
		/**
		 * The name of the file to be generated
		 * @return the name as string
		 */
		String getFileName();
		
		/**
		 * The folder where the file should be generated
		 * @return the folder as string
		 */
		String getFolderName();
		
		/**
		 * the content which will be sued to initialize the file
		 * @return the content as string
		 */
		String getFileContent();
	}
}