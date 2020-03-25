/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.server.repository;

import org.eclipse.core.resources.IProject;
import org.eclipse.jgit.lib.Repository;

import de.dlr.sc.virsat.server.configuration.RepositoryConfiguration;

/**
 * Entry point to the eclipse project
 */
public class ServerRepository {
	private RepositoryConfiguration repositoryConfiguration;
	private IProject project;
	private Repository virSatRepository;

	
	public ServerRepository(RepositoryConfiguration repositoryConfiguration) {
		this.repositoryConfiguration = repositoryConfiguration;
		
		//checkout the project to workspace
	}
	
	
}
