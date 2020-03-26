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

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

import de.dlr.sc.virsat.server.configuration.RepositoryConfiguration;
import de.dlr.sc.virsat.server.configuration.ServerConfiguration;

/**
 * Helper class to save, load and register server repository configurations
 */
public class ServerRepoHelper {
	
	public static final String REPOSITORY_CONFIGURATIONS_DIR_PROPERTY = "repository.configurations.dir";
	
	private ServerRepoHelper() { }
	
	/**
	 * Adds all known project configs to RepoRegistry
	 * @throws IOException 
	 */
	public static void initRepoRegistry() throws IOException {
		try (Stream<Path> paths = Files.walk(Paths.get(getRepositoryConfigurationDir()))) {
		    paths
		        .filter(Files::isRegularFile)
		        .forEach(t -> {
					try {
						registerRepositoryConfiguration(t);
					} catch (IOException e) {
						throw new UncheckedIOException(e);
					}
				});
		} 
	}
	
	/**
	 * Creates RepositoryConfiguration for the given configuration file, creates a corresponding ServerRepository
	 * and registers it in the RepoRegistry
	 * @param repositoryConfigurationFile properties file for a repository
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	private static void registerRepositoryConfiguration(Path repositoryConfigurationFile) throws FileNotFoundException, IOException {
		RepositoryConfiguration config = new RepositoryConfiguration(Files.newInputStream(repositoryConfigurationFile));
		ServerRepository serverRepository = new ServerRepository(config);
		RepoRegistry.getInstance().addRepository(config.getProjectName(), serverRepository);
	}
	
	/**
	 * Saves the given repositoryConfiguration into a file into repository configuration dir
	 * with name like projectName.properties
	 * @throws IOException 
	 */
	public static void saveRepositoryConfiguration(RepositoryConfiguration repositoryConfiguration) throws IOException {
		String fileName = repositoryConfiguration.getProjectName() + ".properties";
		Path configFile = Paths.get(getRepositoryConfigurationDir(), fileName);

		try (OutputStream propertiesStream = Files.newOutputStream(configFile)) {
			repositoryConfiguration.saveProperties(propertiesStream);
		}
	}

	private static String getRepositoryConfigurationDir() {
		return ServerConfiguration.getProperties().getProperty(REPOSITORY_CONFIGURATIONS_DIR_PROPERTY);
	}
}
