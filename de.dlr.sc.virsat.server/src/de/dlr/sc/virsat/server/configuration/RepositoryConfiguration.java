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
import java.util.Properties;

public class RepositoryConfiguration {

	// Infrastructure
	private Properties properties;
	
	// Data
	private String remoteUri;
	private String backend; //check if we have an enum for git/svn
	private String functionalAccountName;
	private String functionalAccountPassword;
	
	// Properties key
	private static final String REMOTE_URL_KEY = "repository.remoteURI";
	private static final String FUNCTIONAL_ACCOUNT_NAME_KEY = "repository.credentials.username";
	private static final String FUNCTIONAL_ACCOUNT_PASSWORD_KEY = "repository.credentials.password";
	
	public RepositoryConfiguration(String remoteUri, String backend, String functionalAccountName, String functionalAccountPassword) {
		setRemoteUri(remoteUri);
		setBackend(backend);
		setFunctionalAccountName(functionalAccountName);
		setFunctionalAccountPassword(functionalAccountPassword);
	}
	
	public RepositoryConfiguration(InputStream repoConfInputStream) throws FileNotFoundException, IOException {
		loadProperties(repoConfInputStream);
	}

	public void update(RepositoryConfiguration repository) {
		setRemoteUri(repository.getRemoteUri());
		setBackend(repository.getBackend());
		setFunctionalAccountName(repository.getFunctionalAccountName());
		setFunctionalAccountPassword(repository.getFunctionalAccountPassword());
	}
	
	/**
	 * Loads a properties file into memory
	 * @param configFileInputStream input stream for the file containing properties in java properties format
	 */
	public void loadProperties(InputStream configFileInputStream) throws FileNotFoundException, IOException {
		properties = new Properties();
		properties.load(configFileInputStream);
		remoteUri = properties.getProperty(REMOTE_URL_KEY);
		functionalAccountName = properties.getProperty(FUNCTIONAL_ACCOUNT_NAME_KEY);
		functionalAccountPassword = properties.getProperty(FUNCTIONAL_ACCOUNT_PASSWORD_KEY);
	}

	public String getRemoteUri() {
		return remoteUri;
	}

	public void setRemoteUri(String remoteUri) {
		this.remoteUri = remoteUri;
	}

	public String getBackend() {
		return backend;
	}

	public void setBackend(String backend) {
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
}
