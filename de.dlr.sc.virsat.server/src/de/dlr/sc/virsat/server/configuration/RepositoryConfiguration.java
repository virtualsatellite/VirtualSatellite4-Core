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
import java.util.Objects;
import java.util.Properties;

import de.dlr.sc.virsat.team.VersionControlSystem;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class RepositoryConfiguration {

	// Infrastructure
	private Properties properties = new Properties();
	
	// Properties key
	public static final String PROJECT_NAME_KEY = "project.name";
	public static final String BACKEND_KEY = "repository.backend";
	public static final String LOCAL_PATH_KEY = "repository.localPath";
	public static final String REMOTE_URL_KEY = "repository.remoteURI";
	public static final String FUNCTIONAL_ACCOUNT_NAME_KEY = "repository.credentials.username";
	public static final String FUNCTIONAL_ACCOUNT_PASSWORD_KEY = "repository.credentials.password";
	
	public RepositoryConfiguration() { }
	
	/**
	 * Sets up a property file describing a project repository relation
	 * @param projectName Name of the project
	 * @param relativePath of the project in the local repository
	 * @param remoteUri remote Uri pointing to the remote repository
	 * @param backend Backend to be used for the repository, usually GIT or SVN
	 * @param functionalAccountName a user for the functional account to communicate with the backend
	 * @param functionalAccountPassword a password for the functional account
	 */
	public RepositoryConfiguration(
			String projectName,
			String localPath,
			String remoteUri,
			VersionControlSystem backend,
			String functionalAccountName,
			String functionalAccountPassword) {
		setProjectName(projectName);
		setLocalPath(localPath);
		setRemoteUri(remoteUri);
		setBackend(backend);
		setFunctionalAccountName(functionalAccountName);
		setFunctionalAccountPassword(functionalAccountPassword);
	}
	
	public RepositoryConfiguration(RepositoryConfiguration repositoryConfiguration) {
		update(repositoryConfiguration);
	}
	
	public RepositoryConfiguration(InputStream repoConfInputStream) throws FileNotFoundException, IOException {
		loadProperties(repoConfInputStream);
	}

	/**
	 * Method to update a repository configuration with new values
	 * @param repositoryBackend the repositoryConfiguration to be used to update the current one
	 */
	public void update(RepositoryConfiguration repositoryBackend) {
		this.properties.putAll(repositoryBackend.properties);
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

	@ApiModelProperty(value = "URI to the remote (can also be local on your machine)",
			example = "some/path.git")
	public String getRemoteUri() {
		return properties.getProperty(REMOTE_URL_KEY);
	}

	public void setRemoteUri(String remoteUri) {
		properties.setProperty(REMOTE_URL_KEY, remoteUri);
	}

	@ApiModelProperty(value = "Backend kind",
			example = "GIT")
	public VersionControlSystem getBackend() {
		return VersionControlSystem.valueOf(properties.getProperty(BACKEND_KEY));
	}
	
	public void setBackend(VersionControlSystem backend) {
		properties.setProperty(BACKEND_KEY, backend.name());
	}

	@ApiModelProperty(value = "Name used for the functional account,"
			+ " that the server uses to access the project backend.")
	public String getFunctionalAccountName() {
		return properties.getProperty(FUNCTIONAL_ACCOUNT_NAME_KEY);
	}

	public void setFunctionalAccountName(String functionalAccountName) {
		properties.setProperty(FUNCTIONAL_ACCOUNT_NAME_KEY, functionalAccountName);
	}

	@ApiModelProperty(value = "Password used for the functional account")
	public String getFunctionalAccountPassword() {
		return properties.getProperty(FUNCTIONAL_ACCOUNT_PASSWORD_KEY);
	}

	public void setFunctionalAccountPassword(String functionalAccountPassword) {
		properties.setProperty(FUNCTIONAL_ACCOUNT_PASSWORD_KEY, functionalAccountPassword);
	}

	@ApiModelProperty(value = "The name used via the API. Should be unique!")
	public String getProjectName() {
		return properties.getProperty(PROJECT_NAME_KEY);
	}

	public void setProjectName(String projectName) {
		properties.setProperty(PROJECT_NAME_KEY, projectName);
	}

	@ApiModelProperty(value = "Local path of the project folder in the repository."
			+ " This allows to have multiple projects in one repository.",
			example = "projectName")
	public String getLocalPath() {
		return properties.getProperty(LOCAL_PATH_KEY);
	}

	public void setLocalPath(String localPath) {
		properties.setProperty(LOCAL_PATH_KEY, localPath);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((properties == null) ? 0 : properties.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		RepositoryConfiguration other = (RepositoryConfiguration) obj;
		return Objects.equals(properties, other.properties);
	}
	
	@ApiModelProperty(hidden = true)
	public boolean isValid() {
		return getProjectName() != null
				&& !getProjectName().isEmpty()
				&& getRemoteUri() != null
				&& !getRemoteUri().isEmpty()
				&& getBackend() != null;
	}
}
