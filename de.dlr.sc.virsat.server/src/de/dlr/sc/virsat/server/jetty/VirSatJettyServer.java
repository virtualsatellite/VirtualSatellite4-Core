/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.server.jetty;

import static org.eclipse.jetty.servlet.ServletContextHandler.NO_SESSIONS;

import org.eclipse.jetty.security.ConstraintSecurityHandler;
import org.eclipse.jetty.security.LoginService;
import org.eclipse.jetty.security.authentication.BasicAuthenticator;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;

import de.dlr.sc.virsat.server.auth.LoginServiceFactory;
import de.dlr.sc.virsat.server.servlet.RepoManagementServlet;
import de.dlr.sc.virsat.server.servlet.VirSatModelAccessServlet;

/**
 * This class represents a Jetty Instance to run Virtual Satellite
 * Stand alone as server application. The class runs some Jersey Servelets
 * to provide a REST API to the outside world
 *
 */
public class VirSatJettyServer {

	public VirSatJettyServer() { }
	
	private static final int VIRSAT_JETTY_PORT = 8000; 
	private LoginService loginService = null;

	/**
	 * Main entry point for Virtual Satellite Jetty Server
	 * @param args Not used currently
	 */
	public static void main(String[] args) {
		try {
			new VirSatJettyServer().start().join();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private Server server;
	
	/**
	 *  Call this method to start the Virtual Satellite specific Jetty Server.
	 * @throws Exception
	 * @throws InterruptedException
	 */
	public VirSatJettyServer start() throws Exception {	

		server.start();
		return this;
	}
	
	/**
	 * Call this method to setup the server
	 */
	public void init() {
		server = new Server(VIRSAT_JETTY_PORT);

		ServletContextHandler servletContextHandler = new ServletContextHandler(NO_SESSIONS);
		servletContextHandler.setContextPath("/");
		servletContextHandler.addServlet(VirSatModelAccessServlet.class, "/rest/*");
		servletContextHandler.addServlet(RepoManagementServlet.class, "/rest/management/*");
		
		setupSecurity(server, servletContextHandler);
	}
	
	/**
	 * Sets up the server security
	 * @param server the Server
	 * @param servletContextHandler the context handler to be handled by the security handler
	 */
	private void setupSecurity(Server server, ServletContextHandler servletContextHandler) {
		
		loginService = new LoginServiceFactory().getLoginService();
		server.addBean(loginService);

		ConstraintSecurityHandler security = new ConstraintSecurityHandler();
		server.setHandler(security);

		/**
		 *  For top down security constraints with roles can be created here
		 */

		security.setAuthenticator(new BasicAuthenticator());
		security.setLoginService(loginService);

		security.setHandler(servletContextHandler);
	}
	
	public VirSatJettyServer join() throws InterruptedException {
		if (server != null) {
			server.join();
		}
		return this;
	}
	
	/**
	 * Call this method to stop the server
	 * @throws Exception
	 */
	public void stop() throws Exception {
		if (server != null) {
			server.stop();
		}
	}
	
	public LoginService getLoginService() {
		return loginService;
	}
}