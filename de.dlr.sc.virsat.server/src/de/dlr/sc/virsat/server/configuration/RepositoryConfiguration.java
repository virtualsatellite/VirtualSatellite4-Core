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

import de.dlr.sc.virsat.team.VersionControlSystem;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class RepositoryConfiguration {

	// Infrastructure
	private Properties properties;
	
	// Data
	private String remoteUri;
	private VersionControlSystem backend;
	private String functionalAccountName;
	private String functionalAccountPassword;
	private String projectName;
	
	// Properties key
	public static final String PROJECT_NAME = "project.name";
	public static final String REMOTE_URL_KEY = "repository.remoteURI";
	public static final String BACKEND_KEY = "repository.backend";
	public static final String FUNCTIONAL_ACCOUNT_NAME_KEY = "repository.credentials.username";
	public static final String FUNCTIONAL_ACCOUNT_PASSWORD_KEY = "repository.credentials.password";
	
	public RepositoryConfiguration(String remoteUri, VersionControlSystem backend, String functionalAccountName, String functionalAccountPassword, String projectName) {
		setRemoteUri(remoteUri);
		setBackend(backend);
		setFunctionalAccountName(functionalAccountName);
		setFunctionalAccountPassword(functionalAccountPassword);
		setProjectName(projectName);
	}
	
	public RepositoryConfiguration(InputStream repoConfInputStream) throws FileNotFoundException, IOException {
		loadProperties(repoConfInputStream);
	}

	public void update(RepositoryConfiguration repository) {
		setRemoteUri(repository.getRemoteUri());
		setBackend(repository.getBackend());
		setFunctionalAccountName(repository.getFunctionalAccountName());
		setFunctionalAccountPassword(repository.getFunctionalAccountPassword());
		setProjectName(repository.getProjectName());
	}
	
	/**
	 * Loads a properties file into memory
	 * @param configFileInputStream input stream for the file containing properties in java properties format
	 */
	public void loadProperties(InputStream configFileInputStream) throws FileNotFoundException, IOException {
		properties = new Properties();
		properties.load(configFileInputStream);
		remoteUri = properties.getProperty(REMOTE_URL_KEY);
		backend = VersionControlSystem.valueOf(properties.getProperty(BACKEND_KEY));
		functionalAccountName = properties.getProperty(FUNCTIONAL_ACCOUNT_NAME_KEY);
		functionalAccountPassword = properties.getProperty(FUNCTIONAL_ACCOUNT_PASSWORD_KEY);
		projectName = properties.getProperty(PROJECT_NAME);
	}

	public String getRemoteUri() {
		return remoteUri;
	}

	public void setRemoteUri(String remoteUri) {
		this.remoteUri = remoteUri;
	}

	public VersionControlSystem getBackend() {
		return backend;
	}

	public void setBackend(VersionControlSystem backend) {
		this.backend = backend;
	}

	public String getFunctionalAccountName() {
		return functionalAccountName;
	}

	public void setFunctionalAccountName(String functionalAccountName) {
		this.functionalAccountName = functionalAccountName;
	}

	public String getFunctionalAccountPassword() {
		return functionalAccountPassword;
	}

	public void setFunctionalAccountPassword(String functionalAccountPassword) {
		this.functionalAccountPassword = functionalAccountPassword;
	}
	
	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
}
