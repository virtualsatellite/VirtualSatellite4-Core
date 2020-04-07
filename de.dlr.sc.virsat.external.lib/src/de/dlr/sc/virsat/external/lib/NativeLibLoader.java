/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.external.lib;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class for loading a set of libraries with interdependencies
 */
public class NativeLibLoader {
	
	public static final String LIB_STATUS_UNKNOWN = "Unknown";
	public static final String LIB_STATUS_LOADED = "Loaded";
	public static final String LIB_STATUS_FAILED = "Failed";
	
	private List<String> libNames;
	private boolean loadFailed = false;
	private boolean useAbsolutePath = false;

	private Map<String, String> libraryLoadResult = new HashMap<>();
	
	private NativeLibPlugin nativeLibPlugin;
	
	/**
	 * Constructor for the Library Loader. Initialize with a list of libraries.
	 * The list may contain library names or their full path starting from the
	 * Plugins root. In case Plugin names are used make sure they are well
	 * registered in the manifest of the Plugin as native code contribution. 
	 * The list may look like: <br> 
	 * [/native_lib/vtk/vtk.so;/native_lib/vtk/vtkCore.so.1] <br> or <br> [vtk;vtkCore]
	 * @param nativeLibPlugin The nativeLibPlugin for callbacks to define the runtime
	 * @param libNames The list of library names 
	 * @param loadFromAbsolutePath to be set to true if the list contains the full path. set to false if it contains lib names only
	 */
	public NativeLibLoader(NativeLibPlugin nativeLibPlugin, List<String> libNames, boolean loadFromAbsolutePath) {
		this.libNames = libNames;
		libNames.forEach((name) -> libraryLoadResult.put(name, LIB_STATUS_UNKNOWN));
		useAbsolutePath = loadFromAbsolutePath;
		this.nativeLibPlugin = nativeLibPlugin;
	}
	
	/**
	 * Try to load all the libraries. This method iterates over all libraries until there is no
	 * progress in loading the libraries. The status of the load is recorded. 
	 * @return true if all the libraries were loaded successfully or false otherwise
	 */
	public boolean loadLibraries() {
		List<String> failedLibNames = new ArrayList<>();

		// To avoid dependency issues when loading the libraries, this
		// loop tries to load the libraries again and again. It stops as soon
		// as it realizes that there is no progress in loading the libraries or
		// when the list of libraries to be loaded is completely empty.
		while (!libNames.isEmpty()) {
			
			// Try to load library by Library. In case one can not be loaded. Remember it for the next run.
			for (String libName : libNames) {
				try {
					if (useAbsolutePath) {
						nativeLibPlugin.loadLibraryByAbsolutePath(libName);
					} else {
						nativeLibPlugin.loadLibraryByName(libName);
					}
					libraryLoadResult.put(libName, LIB_STATUS_LOADED);
				} catch (UnsatisfiedLinkError e) {
					failedLibNames.add(libName);
					libraryLoadResult.put(libName, LIB_STATUS_FAILED);
				}
			}
			
			// in case there are as many failed loads as libraries to be loaded, we can be sure we cannot fix the dependencies
			if (libNames.size() == failedLibNames.size()) {
				loadFailed = true;
				return false;
			}
			
			// Prepare the next run by trying to reload the libraries that failed.
			libNames = failedLibNames;
			failedLibNames = new ArrayList<>();
		}
		
		// If we reach this point, all libraries were successfully loaded.
		return true;
	}

	/**
	 * This method hands back a nicely formated table of which libs got loaded and which ones failed
	 * @param reportName a name of the report 
	 * @param absolutePluginPath an empty string or a path that should be cut away from the libnames for better readability of the table
	 * @return the table as string
	 */
	public String getLoadResult(String reportName, String absolutePluginPath) {
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("---------------------------------------------\n");
		sBuilder.append("Report Loading Libraries for: " + reportName + "\n");
		sBuilder.append("---------------------------------------------\n");
		libraryLoadResult.forEach((libName, status) -> {
			String shortLibName = libName.replaceFirst(absolutePluginPath, " ");
			sBuilder.append((String.format("%-60s", shortLibName) +  String.format("%10s%n", status)).replace(' ', '.'));
		});
		sBuilder.append("---------------------------------------------\n");
		sBuilder.append("End of Report\n");
		sBuilder.append("---------------------------------------------\n");
		sBuilder.append("\n");
		return sBuilder.toString();
	}
	
	/**
	 * Returns a list of library names that failed to load
	 * @return list of library names that failed to load
	 */
	public List<String> getFailedLibraries() {
		if (loadFailed) {
			return libNames;
		} else {
			return Collections.emptyList();
		}
	}
}
