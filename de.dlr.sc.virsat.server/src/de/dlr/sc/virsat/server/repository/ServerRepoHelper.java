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

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UncheckedIOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import de.dlr.sc.virsat.server.configuration.RepositoryConfiguration;
import de.dlr.sc.virsat.server.configuration.ServerConfiguration;

/**
 * Helper class to save, load and register server repository configurations
 */
public class ServerRepoHelper {

	private ServerRepoHelper() { }
	
	/**
	 * Adds all known project configs to RepoRegistry
	 * @throws IOException 
	 */
	public static void initRepoRegistry() throws IOException {
		Path serverConfigurationDirectory = Paths.get(ServerConfiguration.getRepositoryConfigurationsDir());
		Files.walk(serverConfigurationDirectory)
			.filter(Files::isRegularFile)
			.forEach(potentialConfigFile -> {
				try {
					registerRepositoryConfiguration(potentialConfigFile);
				} catch (IOException e) {
					throw new UncheckedIOException(e);
				} catch (URISyntaxException e) {
					throw new RuntimeException(e);
				}
			});
	}
	
	/**
	 * Creates RepositoryConfiguration for the given configuration file, creates a corresponding ServerRepository
	 * and registers it in the RepoRegistry
	 * @param repositoryConfigurationFile properties file for a repository
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 * @throws URISyntaxException 
	 */
	private static void registerRepositoryConfiguration(Path repositoryConfigurationFile) throws IOException, URISyntaxException {
		RepositoryConfiguration config = new RepositoryConfiguration(Files.newInputStream(repositoryConfigurationFile));
		
		ServerRepository serverRepository = new ServerRepository(new File(ServerConfiguration.getProjectRepositoriesDir()), config);
		RepoRegistry.getInstance().addRepository(config.getProjectName(), serverRepository);
	}
	
	/**
	 * Saves the given repositoryConfiguration into a file into repository configuration dir
	 * with name like projectName.properties
	 * @throws IOException 
	 */
	public static void saveRepositoryConfiguration(RepositoryConfiguration repositoryConfiguration) throws IOException {
		String fileName = repositoryConfiguration.getProjectName() + ".properties";
		Path configFile = Paths.get(ServerConfiguration.getRepositoryConfigurationsDir(), fileName);

		try (OutputStream propertiesStream = Files.newOutputStream(configFile)) {
			repositoryConfiguration.saveProperties(propertiesStream);
		}
	}
}
