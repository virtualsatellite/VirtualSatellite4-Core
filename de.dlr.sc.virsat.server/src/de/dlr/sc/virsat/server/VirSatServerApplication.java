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

import java.util.Map.Entry;

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
	
	@Override
	public Object start(IApplicationContext context) throws Exception {
		System.out.println("Welcome to the Virtual Satellite 4 Headless Server");
		System.out.println("--------------------------------------------------");
		System.out.println("");
		System.out.println("Using configuration file: " + Activator.getDefault().getPropertiesFilePath());
		
		System.out.println("Initializing repositories from configurations in " + ServerConfiguration.getRepositoryConfigurationsDir());
		
		VirSatProjectCommons.setEnableWorkspaceBuilder(false);
		System.out.println("Deactivated automated execution fo Workspace Builder");
		
		try {
			ServerRepoHelper.initRepoRegistry();
		} catch (Exception e) {
			System.out.println("Failed loading project configurations");
			e.printStackTrace();
		}
		
		if (RepoRegistry.getInstance().getRepositories().isEmpty()) {
			System.out.println("No project configurations found");
		} else {
			for (Entry<String, ServerRepository> entry : RepoRegistry.getInstance().getRepositories().entrySet()) {
				String projectName = entry.getKey();
				System.out.println("Initializing project " + projectName);
				ServerRepository serverRepository = entry.getValue();
				try {
					serverRepository.updateOrCheckoutProject();
				} catch (Exception e) {
					System.out.println("Failed initializing project " + projectName);
					e.printStackTrace();
				}
			}
		}
		
		System.out.println("About to start the Jetty Server instance...");
		
		jettyServer = new VirSatJettyServer();
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
			System.out.println("Failed to shutdown Jetty instance");
			e.printStackTrace();
		}

		System.out.println("--------------------------------------------------");
	}
}
