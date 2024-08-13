/*******************************************************************************
 * Copyright (c) 2020 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.server.controller;

import java.io.IOException;
import java.util.Set;

import de.dlr.sc.virsat.server.configuration.RepositoryConfiguration;
import de.dlr.sc.virsat.server.repository.RepoRegistry;
import de.dlr.sc.virsat.server.repository.ServerRepoHelper;
import de.dlr.sc.virsat.server.repository.ServerRepository;

public class RepoManagementController {
	
	public ServerRepository getRepository(String repoName) {
		return RepoRegistry.getInstance().getRepository(repoName);
	}
	
	public void addNewRepository(RepositoryConfiguration repoConfiguration) throws Exception {
		ServerRepoHelper.registerRepositoryConfiguration(repoConfiguration);
	}
	
	public void deleteRepository(String repoName) throws IOException {
		ServerRepoHelper.deleteRepositoryConfiguration(repoName);
	}
	
	public void updateRepository(RepositoryConfiguration repoConfiguration) throws IOException {
		ServerRepoHelper.updateRepositoryConfiguration(repoConfiguration);
	}

	public Set<String> getAllProjectNames() {
		return RepoRegistry.getInstance().getRepositories().keySet();
	}
	
	/**
	 * If a project with the name from configuration exists, it is updated, otherwise it is created.
	 * @throws Exception 
	 */
	public void addOrUpdateRepository(RepositoryConfiguration repoConfiguration) throws Exception {
		if (getAllProjectNames().contains(repoConfiguration.getProjectName())) {
			updateRepository(repoConfiguration);
		} else {
			addNewRepository(repoConfiguration);
		}
	}
}
