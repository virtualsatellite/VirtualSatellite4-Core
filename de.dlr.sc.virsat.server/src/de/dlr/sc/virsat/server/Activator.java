/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.server;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Plugin;
import org.osgi.framework.BundleContext;

import de.dlr.sc.virsat.external.lib.commons.commandLine.CommandLineManager;
import de.dlr.sc.virsat.server.configuration.ServerConfiguration;

/**
 * Activator for the Server Plugin
 *
 */
public class Activator extends Plugin {

	// The plug in ID
	private static String pluginId;
	// The shared instance
	private static Activator plugin;
	// The CLI command line option for specifying a configuration file
	private static final String CONFIG_FILE_CLI_PARAM = "configFile";
	// The configuration file path and its default value
	private String propertiesFilePath = "resources/server.properties";
	
	@Override
	public void start(BundleContext context) throws Exception {
		plugin = this;
		pluginId = context.getBundle().getSymbolicName();
		ServerConfiguration.loadProperties(getPropertiesFileInputStream());
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		plugin = null;
	}

	public static Activator getDefault() {
		return plugin;
	}

	public static String getPluginId() {
		return pluginId;
	}
	
	/**
	 * Get the properties file input stream - uses path specified from CLI or default config file
	 * @return input stream of the properties file
	 */
	public InputStream getPropertiesFileInputStream() throws IOException, FileNotFoundException {
		CommandLineManager cliManager = de.dlr.sc.virsat.external.lib.commons.cli.Activator.getCommandLineManager();
		boolean isCustomConfigFileSet = cliManager.isCommandLineOptionSet(CONFIG_FILE_CLI_PARAM);
		if (isCustomConfigFileSet) {
			propertiesFilePath = cliManager.getCommandLineOptionParameter(CONFIG_FILE_CLI_PARAM);
			return new FileInputStream(propertiesFilePath);
		} else {
			return FileLocator.openStream(getBundle(), new Path(propertiesFilePath), false); 
		}
	}

	public String getPropertiesFilePath() {
		return propertiesFilePath;
	}
	
	public String resolveBundlePath(String path) throws IOException {
		return FileLocator.resolve(FileLocator.find(getBundle(), new Path(path))).toString();
	}
	
}
