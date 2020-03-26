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

import de.dlr.sc.virsat.team.VersionControlSystem;


public class RepositoryConfiguration {

	// Infrastructure
	private Properties properties;
	
	// Properties key
	public static final String PROJECT_NAME = "project.name";
	public static final String BACKEND_KEY = "repository.backend";
	public static final String REMOTE_URL_KEY = "repository.remoteURI";
	public static final String FUNCTIONAL_ACCOUNT_NAME_KEY = "repository.credentials.username";
	public static final String FUNCTIONAL_ACCOUNT_PASSWORD_KEY = "repository.credentials.password";
	
	/**
	 * 
	 * @param remoteUri
	 * @param backend
	 * @param functionalAccountName
	 * @param functionalAccountPassword
	 */
	public RepositoryConfiguration(String remoteUri, VersionControlSystem backend, String functionalAccountName, String functionalAccountPassword, String projectName) {
		properties = new Properties();
		setRemoteUri(remoteUri);
		setFunctionalAccountName(functionalAccountName);
		setFunctionalAccountPassword(functionalAccountPassword);
		setBackend(backend);
		setProjectName(projectName);
	}
	
	public RepositoryConfiguration(InputStream repoConfInputStream) throws FileNotFoundException, IOException {
		properties = new Properties();
		loadProperties(repoConfInputStream);
	}

	public void update(RepositoryConfiguration repository) {
		setRemoteUri(repository.getRemoteUri());
		setFunctionalAccountName(repository.getFunctionalAccountName());
		setFunctionalAccountPassword(repository.getFunctionalAccountPassword());
		setProjectName(repository.getProjectName());
	}
	
	/**
	 * Loads a properties file into memory
	 * @param configFileInputStream input stream for the file containing properties in java properties format
	 */
	public void loadProperties(InputStream configFileInputStream) throws FileNotFoundException, IOException {
		properties.load(configFileInputStream);
	}
	
	/**
	 * Saves properties from memory to file
	 * @param configFileOutputStream the file output stream
	 */
	public void saveProperties(OutputStream configFileOutputStream) throws IOException {
		properties.store(configFileOutputStream, "");
	}

	public String getRemoteUri() {
		return properties.getProperty(REMOTE_URL_KEY);
	}

	public void setRemoteUri(String remoteUri) {
		properties.setProperty(REMOTE_URL_KEY, remoteUri);
	}

	public VersionControlSystem getBackend() {
		return VersionControlSystem.valueOf(properties.getProperty(BACKEND_KEY));
	}
	
	public void setBackend(String backend) {
		properties.setProperty(BACKEND_KEY, backend);
	}
	
	public void setBackend(VersionControlSystem backend) {
		properties.setProperty(BACKEND_KEY, backend.name());
	}

	public String getFunctionalAccountName() {
		return properties.getProperty(FUNCTIONAL_ACCOUNT_NAME_KEY);
	}

	public void setFunctionalAccountName(String functionalAccountName) {
		properties.setProperty(FUNCTIONAL_ACCOUNT_NAME_KEY, functionalAccountName);
	}

	public String getFunctionalAccountPassword() {
		return properties.getProperty(FUNCTIONAL_ACCOUNT_PASSWORD_KEY);
	}

	public void setFunctionalAccountPassword(String functionalAccountPassword) {
		properties.setProperty(FUNCTIONAL_ACCOUNT_PASSWORD_KEY, functionalAccountPassword);
	}
	
	public String getProjectName() {
		return properties.getProperty(PROJECT_NAME);
	}

	public void setProjectName(String projectName) {
		properties.setProperty(PROJECT_NAME, projectName);
	}
}
