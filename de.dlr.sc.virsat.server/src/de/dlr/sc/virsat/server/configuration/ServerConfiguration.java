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

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

import org.eclipse.core.runtime.Status;

import de.dlr.sc.virsat.server.Activator;

/**
 * Class for storing configuration properties for VirSat Server.
 * Properties should be loaded from a property file on startup, and can be then accessed.
 */
public class ServerConfiguration {
	
	public static final String REPOSITORY_CONFIGURATIONS_DIR_KEY = "repository.configurations.dir";
	public static final String PROJECT_REPOSITORIES_DIR_KEY = "project.repositories.dir";
	public static final String LOGIN_SERIVE_CLASS_KEY = "login.service.class";
	public static final String AUTH_PROPERTIES_FILE_KEY = "auth.propierties.file";
	
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
	
	public static String getLoginServiceClass() {
		return properties.getProperty(LOGIN_SERIVE_CLASS_KEY);
	}

	public static void setLoginServiceClass(String loginServiceClass) {
		properties.setProperty(LOGIN_SERIVE_CLASS_KEY, loginServiceClass);
	}
	
	public static String getAuthPropertiesFile() {
		return getConfigFilePath(AUTH_PROPERTIES_FILE_KEY);
	}
	
	public static void setAuthPropertiesFile(String authPropertiesFile) {
		properties.setProperty(AUTH_PROPERTIES_FILE_KEY, authPropertiesFile);
	}
	
	/**
	 * Get the path of property or try to resolve it
	 * @param key of the property
	 * @return valid path to the property or null
	 */
	private static String getConfigFilePath(String key) {
		String filePath = null;
		String filePathFromKey = properties.getProperty(key);
		
		// Check if the file exists, else try to resolve it in the bundle
		if (new File(filePathFromKey).exists()) {
			filePath = filePathFromKey;
		} else {
			Activator.getDefault().getLog().log(new Status(Status.WARNING, Activator.getPluginId(), Status.WARNING, "No valid " + key + " provided, trying to resolve it in the bundle", null));
			filePath = getResolvedFile(filePathFromKey);
		}
		return filePath;
	}
	
	/**
	 * Try to resolve the file path in the bundle
	 * @return the file path in the bundle or null
	 */
	private static String getResolvedFile(String path) {
		try {
			return Activator.getDefault().resolveBundlePath(path);
		} catch (IOException e) {
			Activator.getDefault().getLog().log(new Status(Status.ERROR, Activator.getPluginId(), Status.ERROR, "Could not resolve " + path, e));
		}
		return null;
	}

}
