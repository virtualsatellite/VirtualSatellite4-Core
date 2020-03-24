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

import org.eclipse.equinox.app.IApplication;
import org.eclipse.equinox.app.IApplicationContext;

import de.dlr.sc.virsat.server.jetty.VirSatJettyServer;

public class VirSatServerApplication implements IApplication {

	private VirSatJettyServer jettyServer;
	
	@Override
	public Object start(IApplicationContext context) throws Exception {
		System.out.println("Welcome to the Virtual Satellite 4 Headless Server");
		System.out.println("--------------------------------------------------");
		System.out.println("");
		System.out.println("Using configuration file: " + Activator.getDefault().getPropertiesFilePath());
		
		//TODO load all repo configs from config files, check them out into a local workspace, initialize RepoRegistry
		
		System.out.println("About to start the Jetty Server instance...");
		
		jettyServer = new VirSatJettyServer();
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
		
		//TODO save all repo configs from RepoRegistry to config files

		System.out.println("--------------------------------------------------");
	}
}
