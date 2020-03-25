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

import de.dlr.sc.virsat.server.configuration.ServerConfiguration;

/**
 * Helper class to save, load and register server repository configurations
 */
public class ServerRepoHelper {
	
	public static String REPOSITORY_CONFIGURATIONS_DIR_PROPERTY = "repository.configurations.dir";
	
	/**
	 * Crawls repository configuration directory from ServerConfiguration, creates projects and registers them in RepoRegistry
	 */
	public static void loadRepositoryConfigurations() {
	}
	
	/**
	 * Saves all configurations from RepoRegistry to property files
	 */
	public static void saveRepositoryConfigurations() {
		
	}

	private static String getRepositoryConfigurationDir() {
		return ServerConfiguration.getProperties().getProperty(REPOSITORY_CONFIGURATIONS_DIR_PROPERTY);
	}
}
