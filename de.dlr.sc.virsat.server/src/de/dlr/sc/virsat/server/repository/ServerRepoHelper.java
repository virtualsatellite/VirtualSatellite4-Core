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
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.eclipse.core.runtime.CoreException;

import de.dlr.sc.virsat.server.Activator;
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
		Activator.getDefault().getLog().info("Starting to initialize repositories...");
		Path serverConfigurationDirectory = Paths.get(ServerConfiguration.getRepositoryConfigurationsDir());

		if (Files.notExists(serverConfigurationDirectory, LinkOption.NOFOLLOW_LINKS)) {
			Activator.getDefault().getLog().info("Repository Location does not yet exists. Creating: " + serverConfigurationDirectory);
			Files.createDirectories(serverConfigurationDirectory);
		}
		
		Activator.getDefault().getLog().info("Searching for repositories in: " + serverConfigurationDirectory);
		Files.walk(serverConfigurationDirectory)
			.filter(Files::isRegularFile)
			.forEach(potentialConfigFile -> {
				try {
					Activator.getDefault().getLog().info("Found a potential repository: " + potentialConfigFile);
					registerRepositoryConfiguration(potentialConfigFile);
				} catch (IOException e) {
					throw new UncheckedIOException(e);
				} catch (URISyntaxException e) {
					throw new RuntimeException(e);
				} catch (Exception e) {
					throw new RuntimeException(e);
				}
			});
		Activator.getDefault().getLog().info("Finished repository initialization");
	}
	
	/**
	 * Creates RepositoryConfiguration for the given configuration file, creates a corresponding ServerRepository
	 * and registers it in the RepoRegistry
	 * @param repositoryConfigurationFile properties file for a repository
	 * @throws Exception 
	 * @throws FileNotFoundException 
	 */
	private static void registerRepositoryConfiguration(Path repositoryConfigurationFile) throws Exception {
		RepositoryConfiguration config = new RepositoryConfiguration(Files.newInputStream(repositoryConfigurationFile));
		
		registerRepositoryConfiguration(config);
	}
	
	/**
	 * MEthod to register a new project repository
	 * @param repositoryConfiguration
	 * @throws Exception 
	 */
	public static void registerRepositoryConfiguration(RepositoryConfiguration repositoryConfiguration) throws Exception {
		ServerRepository serverRepository = new ServerRepository(new File(ServerConfiguration.getProjectRepositoriesDir()), repositoryConfiguration);
		saveRepositoryConfiguration(repositoryConfiguration);
		
		// After saving try to run a sync with the upstream repository
		//serverRepository.updateOrCheckoutProject();

		// Register the repo only if saving the configuration did not throw an error
		RepoRegistry.getInstance().addRepository(repositoryConfiguration.getProjectName(), serverRepository);
	}
	
	public static void checkoutRepositoryConfiguration(RepositoryConfiguration repositoryConfiguration) throws Exception {
		ServerRepository serverRepository = new ServerRepository(new File(ServerConfiguration.getProjectRepositoriesDir()), repositoryConfiguration);
		serverRepository.updateOrCheckoutProject();
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
		
		try {
			Files.delete(configFile);
			RepoRegistry.getInstance().getRepository(repoName).removeRepository();
		} catch (CoreException | IOException e) {
			Activator.getDefault().getLog().error("Failed to delete project on file system", e);
			e.printStackTrace();
		}
		
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
