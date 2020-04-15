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

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

/**
 * Class for storing configuration properties for VirSat Server.
 * Properties should be loaded from a property file on startup, and can be then accessed.
 */
public class ServerConfiguration {
	
	public static final String REPOSITORY_CONFIGURATIONS_DIR_KEY = "repository.configurations.dir";
	public static final String PROJECT_REPOSITORIES_DIR_KEY = "project.repositories.dir";
	public static final String USER_HANDLER_CLASS_KEY = "user.handler.class";
	
	private static Properties properties = new Properties();
	
	private ServerConfiguration() { }
	
	/**
	 * Loads a properties file into memory
	 * @param configFileInputStream input stream for the file containing properties in java properties format
	 */
	public static void loadProperties(InputStream configFileInputStream) throws FileNotFoundException, IOException {
		properties.load(configFileInputStream);
	}
	
	/**
	 * Saves properties from memory to file
	 * @param configFileOutputStream the file output stream
	 */
	public static void saveProperties(OutputStream configFileOutputStream) throws IOException {
		properties.store(configFileOutputStream, "");
	}
	
	public static Properties getProperties() {
		return properties;
	}
	
	public static String getRepositoryConfigurationsDir() {
		return properties.getProperty(REPOSITORY_CONFIGURATIONS_DIR_KEY);
	}
	
	public static void setRepositoryConfigurationsDir(String repositoryConfigurationsDir) {
		properties.setProperty(REPOSITORY_CONFIGURATIONS_DIR_KEY, repositoryConfigurationsDir);
	}

	public static String getProjectRepositoriesDir() {
		return properties.getProperty(PROJECT_REPOSITORIES_DIR_KEY);
	}

	public static void setProjectRepositoriesDir(String projectRepositoriesDir) {
		properties.setProperty(PROJECT_REPOSITORIES_DIR_KEY, projectRepositoriesDir);
	}
	
	public static String getUserHandlerClass() {
		return properties.getProperty(USER_HANDLER_CLASS_KEY);
	}

	public static void setUserHandlerClass(String userHandlerClass) {
		properties.setProperty(USER_HANDLER_CLASS_KEY, userHandlerClass);
	}
}
