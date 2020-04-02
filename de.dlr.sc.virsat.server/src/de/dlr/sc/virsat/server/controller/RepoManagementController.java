/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.server.controller;

import java.io.File;
import java.net.URISyntaxException;
import java.util.Set;

import de.dlr.sc.virsat.server.configuration.RepositoryConfiguration;
import de.dlr.sc.virsat.server.configuration.ServerConfiguration;
import de.dlr.sc.virsat.server.repository.RepoRegistry;
import de.dlr.sc.virsat.server.repository.ServerRepository;

public class RepoManagementController {
	
	public ServerRepository getRepository(String repoName) {
		return RepoRegistry.getInstance().getRepository(repoName);
	}
	
	/**
	 * Adds and registers a new project
	 * @param repoConfiguration project configuration
	 * @throws URISyntaxException 
	 */
	public void addNewRepository(RepositoryConfiguration repoConfiguration) throws URISyntaxException {
		ServerRepository repository = new ServerRepository(new File(ServerConfiguration.getProjectRepositoriesDir()), repoConfiguration);
		RepoRegistry.getInstance().addRepository(repoConfiguration.getProjectName(), repository);
	}
	
	public void deleteRepository(String repoName) {
		RepoRegistry.getInstance().getRepositories().remove(repoName);
	}
	
	public void updateRepository(RepositoryConfiguration repoConfiguration) {
		ServerRepository repo = RepoRegistry.getInstance().getRepository(repoConfiguration.getProjectName());
		repo.getRepositoryConfiguration().update(repoConfiguration);
	}

	public Set<String> getAllProjectNames() {
		return RepoRegistry.getInstance().getRepositories().keySet();
	}
	
	/**
	 * If a project with the name from configuration exists, it is updated, otherwise it is created.
	 */
	public void addOrUpdateRepository(RepositoryConfiguration repoConfiguration) throws URISyntaxException {
		if (getAllProjectNames().contains(repoConfiguration.getProjectName())) {
			updateRepository(repoConfiguration);
		} else {
			addNewRepository(repoConfiguration);
		}
	}
}
