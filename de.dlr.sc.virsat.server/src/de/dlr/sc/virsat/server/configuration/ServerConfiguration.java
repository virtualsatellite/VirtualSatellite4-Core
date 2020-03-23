/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.server.configuration;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import de.dlr.sc.virsat.external.lib.commons.cli.Activator;

/**
 * Class for storing configuration properties for VirSat Server.
 * Properties should be loaded from a property file on startup, and can be then accessed.
 */
public class ServerConfiguration {
	

	private static final String CONFIG_FILE_CLI_PARAM = "configFile";
	private static Properties properties = null;
	private static String propertiesFilePath;

	
	private ServerConfiguration() { }
	
	/**
	 * Loads a properties file into memory
	 * @param filePath path to file containing properties in java properties format
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	public static void loadProperties() throws FileNotFoundException, IOException {
		propertiesFilePath = getPropertiesFilePath();
		properties = new Properties();
		properties.load(new BufferedReader(new FileReader(propertiesFilePath)));
	}
	
	
	public static Properties getProperties() {
		return properties;
	}


	/**
	 * Get the properties file path - uses path specified from CLI or default file 
	 * @return the configuration file path
	 */
	public static String getPropertiesFilePath() {
		boolean isCustomConfigFileSet = Activator.getCommandLineManager().isCommandLineOptionSet(CONFIG_FILE_CLI_PARAM);
		if (isCustomConfigFileSet) {
			propertiesFilePath = Activator.getCommandLineManager().getCommandLineOptionParameter(CONFIG_FILE_CLI_PARAM);
		} 
		return propertiesFilePath;
	}
	
	public static void setPropertiesFilePath(String filePath) {
		propertiesFilePath = filePath;
	}

}
