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

import de.dlr.sc.virsat.server.configuration.RepositoryConfiguration;
import de.dlr.sc.virsat.server.repository.RepoRegistry;
import de.dlr.sc.virsat.server.repository.ServerRepository;

public class RepoManagemantController {
	
	public RepoManagemantController() {
	}
	
	public ServerRepository getRepository(String repoName) {
		return RepoRegistry.getInstance().getRepository(repoName);
	}
	
	public void addNewRepository(RepositoryConfiguration repoConfiguration) {
		ServerRepository repository = new ServerRepository(repoConfiguration);
		RepoRegistry.getInstance().addRepository(repoConfiguration.getProjectName(), repository);
	}
	
	public void deleteRepository(String repoName) {
		RepoRegistry.getInstance().getRepositories().remove(repoName);
	}
	
	public void updateRepository(RepositoryConfiguration repoConfiguration) {
		ServerRepository repo = RepoRegistry.getInstance().getRepository(repoConfiguration.getProjectName());
		repo.getRepositoryConfiguration().update(repoConfiguration);
	}

}
