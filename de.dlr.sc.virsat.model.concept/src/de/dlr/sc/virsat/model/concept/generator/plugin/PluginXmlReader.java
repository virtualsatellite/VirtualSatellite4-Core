/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.concept.generator.plugin;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;

/**
 * This class handles the plugin.xml file and in particular merging it between
 * generated and hand written content
 * @author fisc_ph
 *
 */
public class PluginXmlReader {

	protected List<String> lines;
	
	public static final String PR_START = "Plugin.XML Protected Region Start";
	public static final String PR_END = "Plugin.XML Protected Region End";
	
	
	/**
	 * Constructor for the PluginXmlReader
	 */
	public PluginXmlReader() {
	}
		
	/**
	 * Opens the plugin.xml of the given plugin. It starts reading the plugin.xml file
	 * @param pluginId The plugin id
	 * @return this plugin xml reader after initialization 
	 */
	public PluginXmlReader init(String pluginId) {
		IProject iProject = ResourcesPlugin.getWorkspace().getRoot().getProject(pluginId);
		if (iProject == null) {
			throw new RuntimeException("Could not find project " + pluginId + "for reading plugin.xml");
		}
		IFile iFile = iProject.getFile("plugin.xml");
		if (iFile == null) {
			throw new RuntimeException("Could not find plugin.xml in project " + pluginId);
		}
		String osPath = iFile.getRawLocation().toOSString();
		try {
			lines = Files.readAllLines(Paths.get(osPath));
		} catch (IOException e) {
			throw new RuntimeException("Could not read plugin.xml in project " + pluginId);
		}
		
		return this;
	}
	
	/**
	 * This method extracts the content in between the protected region
	 * @param regionID the index of the protected region
	 * @return a concatenated String of the content in the protected region
	 */
	public String extractProtectedRegion(int regionID) {
		ByteArrayOutputStream oStream = new ByteArrayOutputStream();
		PrintStream ps = new PrintStream(oStream);
		boolean foundStart = false;
		int index = 0;
		
		for (String line : lines) {
			if (line.contains(PR_START)) {
				if (index == regionID) {
					foundStart = true;
				} else {
					index++;
				}
			} else if (foundStart && line.contains(PR_END)) {
				break;
			} else if (foundStart) {
				ps.println(line);
			}
		}
		
		return oStream.toString();
	}
}
