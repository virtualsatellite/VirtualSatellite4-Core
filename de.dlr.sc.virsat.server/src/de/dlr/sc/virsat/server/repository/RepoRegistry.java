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

import java.util.HashMap;
import java.util.Map;

/**
 * Class that maintains a list of repositories mapped to their name
 *
 */
public class RepoRegistry {
	
	private Map<String, ServerRepository> repositories;
	private static RepoRegistry instance;
	
	public ServerRepository getRepository(String name) {
		return repositories.get(name);
	}
	
	public void addRepository(String name, ServerRepository repository) {
		repositories.put(name, repository);
	}
	
	public Map<String, ServerRepository> getRepositories() {
		return repositories;
	}

	/**
	 * Get the singelton instance of this registry
	 * @return the instance
	 */
	public static RepoRegistry getInstance() {
		if (instance == null) {
			instance = new RepoRegistry();
		}
		return instance;
	}
	
	private RepoRegistry() {
		repositories = new HashMap<String, ServerRepository>();
	}
	
}
