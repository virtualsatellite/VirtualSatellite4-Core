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

public class RepositoryConfiguration {

	private String remoteUri;
	private String backend; //check if we have an enum for git/svn
	private String functionalAccountName;
	private String functionalAccountPassword;
	
	public RepositoryConfiguration(String remoteUri, String backend, String functionalAccountName, String functionalAccountPassword) {
		setRemoteUri(remoteUri);
		setBackend(backend);
		setFunctionalAccountName(functionalAccountName);
		setFunctionalAccountPassword(functionalAccountPassword);
	}

	public void update(RepositoryConfiguration repository) {
		setRemoteUri(repository.getRemoteUri());
		setBackend(repository.getBackend());
		setFunctionalAccountName(repository.getFunctionalAccountName());
		setFunctionalAccountPassword(repository.getFunctionalAccountPassword());
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
