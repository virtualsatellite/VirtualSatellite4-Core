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
	public static final String AUTH_PROPERTIES_FILE_KEY = "auth.properties.file";
	public static final String HTTPS_KEYSTORE_PATH = "https.keystore.path";
	public static final String HTTPS_KEYSTORE_PASSWORD = "https.keystore.password";
	public static final String HTTPS_KEYSTORE_MANAGER_PASSWORD = "https.keystore.manager.password";
	public static final String HTTPS_ONLY = "https.only";
	public static final String HTTPS_ENABLED = "https.enabled";
	public static final String HTTPS_SNI_REQUIRED = "https.sni.required";
	public static final String HTTPS_SNI_HOST_CHECK = "https.sni.host.check";
	
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
	
	public static String getAuthPropertiesFileUri() {
		return getConfigFileUri(AUTH_PROPERTIES_FILE_KEY);
	}
	
	public static void setAuthPropertiesFileUri(String authPropertiesFile) {
		properties.setProperty(AUTH_PROPERTIES_FILE_KEY, authPropertiesFile);
	}
	
	public static String getHttpsKeystoreFileUri() {
		return getConfigFileUri(HTTPS_KEYSTORE_PATH);
	}
	
	public static void setHttpsKeystoreFileUri(String httpsKeystorePath) {
		properties.setProperty(HTTPS_KEYSTORE_PATH, httpsKeystorePath);
	}
	
	public static boolean getHttpsEnabled() {
		return Boolean.valueOf(properties.getProperty(HTTPS_ENABLED));
	}
	
	public static void setHttpsEnabled(boolean httpsEnabled) {
		properties.setProperty(HTTPS_ENABLED, Boolean.toString(httpsEnabled));
	}
	
	public static boolean getHttpsOnly() {
		return Boolean.valueOf(properties.getProperty(HTTPS_ONLY));
	}
	
	public static void setHttpsOnly(boolean httpsOnly) {
		properties.setProperty(HTTPS_ONLY, Boolean.toString(httpsOnly));
	}
	
	public static String getHttpsKeystorePassword() {
		return properties.getProperty(HTTPS_KEYSTORE_PASSWORD);
	}

	public static void setHttpsKeystorePassword(String httpsKeystorePassword) {
		properties.setProperty(HTTPS_KEYSTORE_PASSWORD, httpsKeystorePassword);
	}
	
	public static String getHttpsKeystoreManagerPassword() {
		return properties.getProperty(HTTPS_KEYSTORE_MANAGER_PASSWORD);
	}
	
	public static void setHttpsKeystoreManagerPassword(String httpsKeystoreManagerPassword) {
		properties.setProperty(HTTPS_KEYSTORE_MANAGER_PASSWORD, httpsKeystoreManagerPassword);
	}

	public static void setHttpsSniRequired(boolean httpsSniRequired) {
		properties.setProperty(HTTPS_SNI_REQUIRED, Boolean.toString(httpsSniRequired));
	}

	public static void setHttpsSniHostCheck(boolean httpsSniHostCheck) {
		properties.setProperty(HTTPS_SNI_HOST_CHECK, Boolean.toString(httpsSniHostCheck));
	}
	
	public static boolean getHttpsSniRequired() {
		return Boolean.valueOf(properties.getProperty(HTTPS_SNI_REQUIRED));
	}

	public static boolean getHttpsSniHostCheck() {
		return Boolean.valueOf(properties.getProperty(HTTPS_SNI_HOST_CHECK));
	}


	/**
	 * Get a config file for a given configuration key.
	 * Tries to resolve the file first locally. If not possible,
	 * the file will be resolved from the bundle, copied to a temporary cache
	 * and then returned as a File Uri
	 * @param key of the property
	 * @return valid path to the property or null
	 */
	private static String getConfigFileUri(String key) {
		String filePath = null;
		String filePathFromKey = properties.getProperty(key);
		
		// Only resolve if the file name is not null or empty
		if (filePathFromKey != null && !filePathFromKey.isEmpty()) {
			// Check if the file exists, else try to resolve it in the bundle
			File configFile = new File(filePathFromKey);
			boolean fileExists = configFile.exists();
			if (fileExists) {
				filePath = configFile.toURI().toString();
			} else {
				Activator.getDefault().getLog().log(new Status(Status.WARNING, Activator.getPluginId(), Status.WARNING, "No valid " + key + " provided, trying to resolve it in the bundle", null));
				filePath = Activator.getDefault().extractResourceFromBundle(filePathFromKey);
			}
		} else {
			Activator.getDefault().getLog().log(new Status(Status.WARNING, Activator.getPluginId(), Status.WARNING, key + " is empty or null", null));
			filePath = filePathFromKey;
		}
		return filePath;
	}
	
}
