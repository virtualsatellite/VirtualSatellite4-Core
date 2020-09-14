/*******************************************************************************
 * Copyright (c) 2020 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
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
		
		registerRepositoryConfiguration(config);
	}
	
	public static void registerRepositoryConfiguration(RepositoryConfiguration repositoryConfiguration) throws URISyntaxException, IOException {
		ServerRepository serverRepository = new ServerRepository(new File(ServerConfiguration.getProjectRepositoriesDir()), repositoryConfiguration);
		RepoRegistry.getInstance().addRepository(repositoryConfiguration.getProjectName(), serverRepository);
		
		saveRepositoryConfiguration(repositoryConfiguration);
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
	
	/**
	 * Delete the .properties file of the project and remove it from the repo registry
	 * @param repoName name of the project to be deleted
	 * @throws IOException
	 */
	public static void deleteRepositoryConfiguration(String repoName) throws IOException {
		String fileName = repoName + ".properties";
		Path configFile = Paths.get(ServerConfiguration.getRepositoryConfigurationsDir(), fileName);
		
		Files.delete(configFile);
		
		RepoRegistry.getInstance().getRepositories().remove(repoName);
	}
	
	/**
	 * Update a RepositoryConfiguration and the corresponding ServerRepository
	 * @param repositoryConfiguration
	 * @throws IOException
	 */
	public static void updateRepositoryConfiguration(RepositoryConfiguration repositoryConfiguration) throws IOException {
		ServerRepository serverRepository = RepoRegistry.getInstance().getRepository(repositoryConfiguration.getProjectName());
		
		RepositoryConfiguration oldConfig = serverRepository.getRepositoryConfiguration();
		
		// Update the configuration and save it
		if (!oldConfig.equals(repositoryConfiguration)) {
			oldConfig.update(repositoryConfiguration);
			saveRepositoryConfiguration(oldConfig);
		}
	}
}
