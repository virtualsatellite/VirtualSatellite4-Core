/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.server;

import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.util.Map.Entry;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.equinox.app.IApplication;
import org.eclipse.equinox.app.IApplicationContext;

import de.dlr.sc.virsat.project.structure.VirSatProjectCommons;
import de.dlr.sc.virsat.server.configuration.ServerConfiguration;
import de.dlr.sc.virsat.server.jetty.VirSatJettyServer;
import de.dlr.sc.virsat.server.repository.RepoRegistry;
import de.dlr.sc.virsat.server.repository.ServerRepoHelper;
import de.dlr.sc.virsat.server.repository.ServerRepository;

public class VirSatServerApplication implements IApplication {

	private VirSatJettyServer jettyServer;
	
	/**
	 * This method cleans up projects which seem to be incorrectly
	 * connected to the underlying resource system.
	 */
	public void cleanProjects() {
		IProject[] projects = ResourcesPlugin.getWorkspace().getRoot().getProjects();
		for (IProject project : projects) {
			var projectMetaFilePath = project.getRawLocation().toOSString() + "\\.project";
			var projectMetaFileMissing = !Files.exists(Path.of(projectMetaFilePath), LinkOption.NOFOLLOW_LINKS);
			var projectIsMissing = !project.exists();
			Activator.getDefault().getLog().info("Checking Project: " + project.getName() + " isMissing: " + projectIsMissing);
			Activator.getDefault().getLog().info("Checking Project Meta File: " + projectMetaFilePath + " isMissing: " + projectMetaFileMissing);
			if (projectIsMissing || projectMetaFileMissing) {
				try {
					Activator.getDefault().getLog().info("Project seems to be incorrectly configured, thus deleting it: " + project.getName());
					project.delete(true, true, new NullProgressMonitor());
				} catch (CoreException e) {
					Activator.getDefault().getLog().error("Failed to delete project: " + project.getName(), e);
					e.printStackTrace();
				}
			}
		}
		
	}
	
	@Override
	public Object start(IApplicationContext context) throws Exception {
		System.out.println("Welcome to the Virtual Satellite 4 Headless Server");
		System.out.println("--------------------------------------------------");
		System.out.println("");
		System.out.println("Using configuration file: " + Activator.getDefault().getPropertiesFilePath());
		
		Activator.getDefault().getLog().info("Initializing repositories from configurations in " + ServerConfiguration.getRepositoryConfigurationsDir());
		
		cleanProjects();
		VirSatProjectCommons.setEnableWorkspaceBuilder(false);
		Activator.getDefault().getLog().info("Deactivated automated execution fo Workspace Builder");
		
		try {
			ServerRepoHelper.initRepoRegistry();
		} catch (Exception e) {
			Activator.getDefault().getLog().error("Failed loading project configurations", e);
			e.printStackTrace();
		}
		
		if (RepoRegistry.getInstance().getRepositories().isEmpty()) {
			Activator.getDefault().getLog().info("No project configurations found");
		} else {
			for (Entry<String, ServerRepository> entry : RepoRegistry.getInstance().getRepositories().entrySet()) {
				String projectName = entry.getKey();
				System.out.println("Initializing project " + projectName);
				ServerRepository serverRepository = entry.getValue();
				try {
					serverRepository.updateOrCheckoutProject();
				} catch (Exception e) {
					Activator.getDefault().getLog().error("Failed initializing project " + projectName, e);
					e.printStackTrace();
				}
			}
		}
		
		Activator.getDefault().getLog().info("About to start the Jetty Server instance...");
		System.out.println("About to start the Jetty Server instance...");
		int port = Activator.getDefault().getServerPort();
		System.out.println("Using port: " + String.format("%04d", port));
		
		jettyServer = new VirSatJettyServer();
		jettyServer.setServerPort(port);
		jettyServer.init();
		jettyServer.start().join();
		
		System.out.println("--------------------------------------------------");
		System.out.println("The server stopped, I am about to shut down.");
		System.out.println("Bye Bye, see you on the launch pad...");
		System.out.println("--------------------------------------------------");
		return IApplication.EXIT_OK;
	}

	@Override
	public void stop() {
		System.out.println("--------------------------------------------------");
		System.out.println("Received a signal to stop");
		System.out.println("Trying to signal the Jetty Server instance");
		try {
			jettyServer.stop();
		} catch (Exception e) {
			Activator.getDefault().getLog().error("Failed to shutdown Jetty instance", e);
			e.printStackTrace();
		}

		System.out.println("--------------------------------------------------");
	}
}
