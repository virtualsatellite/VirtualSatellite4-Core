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

public class RepositoryConfiguration {

	// Infrastructure
	private Properties properties;
	
	// Properties key
	protected static final String REMOTE_URL_KEY = "repository.remoteURI";
	protected static final String FUNCTIONAL_ACCOUNT_NAME_KEY = "repository.credentials.username";
	protected static final String FUNCTIONAL_ACCOUNT_PASSWORD_KEY = "repository.credentials.password";
	
	public RepositoryConfiguration(String remoteUri, String backend, String functionalAccountName, String functionalAccountPassword) {
		properties = new Properties();
		setRemoteUri(remoteUri);
		setBackend(backend);
		setFunctionalAccountName(functionalAccountName);
		setFunctionalAccountPassword(functionalAccountPassword);
	}
	
	public RepositoryConfiguration(InputStream repoConfInputStream) throws FileNotFoundException, IOException {
		properties = new Properties();
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

	public String getBackend() {
		return "";
	}

	public void setBackend(String backend) {
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
}
