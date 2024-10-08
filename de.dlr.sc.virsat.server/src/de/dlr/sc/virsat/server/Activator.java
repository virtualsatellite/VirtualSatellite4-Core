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
import java.net.URISyntaxException;
import java.net.URL;

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
	// The CLI command line option for setting the port
	private static final String PORT_CLI_PARAM = "port";
	// The configuration file path and its default value
	private String propertiesFilePath = "resources/server.properties";
	// The default port for our server
	public static final int VIRSAT_JETTY_PORT = 8000;
	
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
	 * @throws IOException in case something goes wrong with the file access
	 * @throws FileNotFoundException in case something goes wrong with the file access
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
	
	/**
	 * Get the specified jetty server port or default
	 * @return the port as int
	 */
	public int getServerPort() {
		CommandLineManager cliManager = de.dlr.sc.virsat.external.lib.commons.cli.Activator.getCommandLineManager();
		boolean isCustomPathSet = cliManager.isCommandLineOptionSet(PORT_CLI_PARAM);
		if (isCustomPathSet) {
			return Integer.parseInt(cliManager.getCommandLineOptionParameter(PORT_CLI_PARAM));
		} else {
			return VIRSAT_JETTY_PORT;
		}
	}

	public String getPropertiesFilePath() {
		return propertiesFilePath;
	}
	
	/**
	 * This method extracts a resource from a bundle.
	 * it tries to resolve the resource by the given path.
	 * then it tries to copy it to a temporary cache.
	 * The file will be handed back by its file uri as a string
	 * @param path the path where the resource is within the bundle
	 * @return the extracted file in the temporary cache as file uri encoded string
	 */
	public String extractResourceFromBundle(String path) {
		Activator.getDefault().getLog().info("Extracting bundle resource: " + path);
		URL url = FileLocator.find(getBundle(), new Path(path));
		String uri;
		try {
			uri = FileLocator.toFileURL(url).toURI().toString();
			Activator.getDefault().getLog().info("Created temporary file url resource at: " + path);
			return uri;
		} catch (IOException | URISyntaxException e) {
			Activator.getDefault().getLog().error("Failed to extract bundle resource to temp.", e);
		}
		
		return null;
	}
	
}
