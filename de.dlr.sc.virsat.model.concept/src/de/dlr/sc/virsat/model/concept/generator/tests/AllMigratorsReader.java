/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.concept.generator.tests;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;

import de.dlr.sc.virsat.model.concept.migrator.ConceptMigrationHelper;

/**
 * This class finds the migrators of the given plugin
 * @author fisc_ph
 *
 */
public class AllMigratorsReader {

	protected List<String> migrators = new ArrayList<>();
	
	/**
	 * Constructor for the PluginXmlReader
	 */
	public AllMigratorsReader() {
	}
		
	/**
	 * Searches the migrators of given plugin. It starts reading the migrator folder of the project
	 * @param pluginId The plugin id
	 * @return this migrator reader after initialization 
	 */
	public AllMigratorsReader init(String pluginId) {
		IProject iProject = ResourcesPlugin.getWorkspace().getRoot().getProject(pluginId);
		if (iProject == null) {
			throw new RuntimeException("Could not find project " + pluginId + " for creatin AllTestsGen.java");
		}
		
		IFolder iFolder = iProject.getFolder("src/" + pluginId.replaceAll("\\.", "/") + "/migrator");
		if (iFolder == null) {
			throw new RuntimeException("Could not access migrator package in project " + pluginId);
		}
		
		if (iFolder.exists()) {
			try {
				iFolder.accept(resource -> {
					if (resource instanceof IFile) {
						String migrator = resource.getName().replaceAll("." + resource.getFileExtension(), "");
						migrators.add(migrator);
					}
					return true;
				});
			} catch (CoreException e1) {
				e1.printStackTrace();
			}
		}
		
		return this;
	}
	
	/**
	 * Gets the previous version using the migrators on the file system
	 * @param version some version number
	 * @return the previous version number according to the existing migrators, and simply returns the passed version if no previous version was found
	 */
	public String getPreviousVersion(String version) {
		List<String> versions = migrators.stream().map(migratorFileName -> migratorFileName.replace("Migrator", "").replaceAll("v", "\\.")).collect(Collectors.toList());
		
		Collections.sort(versions, new Comparator<String>() {
			@Override
			public int compare(String version1, String version2) {
				return ConceptMigrationHelper.compareVersions(version1, version2);
			}
		});
		
		for (int i = 0; i < versions.size() - 1; ++i) {
			if (versions.get(i + 1).equals(version)) {
				return versions.get(i);
			}
		}
		
		// There is no previous version
		return version;
	}
}
