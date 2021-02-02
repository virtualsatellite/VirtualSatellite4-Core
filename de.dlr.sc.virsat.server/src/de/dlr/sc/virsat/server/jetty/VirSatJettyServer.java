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

import org.eclipse.jetty.http.HttpScheme;
import org.eclipse.jetty.http.HttpVersion;
import org.eclipse.jetty.security.ConstraintSecurityHandler;
import org.eclipse.jetty.security.LoginService;
import org.eclipse.jetty.security.authentication.BasicAuthenticator;
import org.eclipse.jetty.server.HttpConfiguration;
import org.eclipse.jetty.server.HttpConnectionFactory;
import org.eclipse.jetty.server.SecureRequestCustomizer;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.server.SslConnectionFactory;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.util.ssl.SslContextFactory;

import de.dlr.sc.virsat.server.auth.LoginServiceFactory;
import de.dlr.sc.virsat.server.configuration.ServerConfiguration;
import de.dlr.sc.virsat.server.servlet.RepoManagementServlet;
import de.dlr.sc.virsat.server.servlet.StatusServlet;
import de.dlr.sc.virsat.server.servlet.VirSatModelAccessServlet;

/**
 * This class represents a Jetty Instance to run Virtual Satellite
 * Stand alone as server application. The class runs some Jersey Servelets
 * to provide a REST API to the outside world
 *
 */
public class VirSatJettyServer {

	public VirSatJettyServer() { }
	
	public static final int VIRSAT_JETTY_PORT = 8000;
	public static final int VIRSAT_JETTY_PORT_HTTPS = 8443;
//	public static final String HTTP_SCHEME = "http";
//	public static final String HTTPS_SCHEME = "https";
	public static final String PATH = "/rest";
	public static final String STATUS = "/status";
	private static final String SUFFIX = "/*";
	
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
		boolean httpsOnly = ServerConfiguration.getHttpsOnly();
		boolean httpsEnabled = ServerConfiguration.getHttpsEnabled();
		
		server = new Server();
		
		// Setup HTTP and/or HTTPS
		if (httpsEnabled) {
			setupHttps(server);
			if (!httpsOnly) {
				setupHttp(server, httpsEnabled);
			}
		} else {
			setupHttp(server, httpsEnabled);
		}

		ServletContextHandler servletContextHandler = new ServletContextHandler(NO_SESSIONS);
		servletContextHandler.setContextPath("/");
		servletContextHandler.addServlet(StatusServlet.class, "/status");
		servletContextHandler.addServlet(VirSatModelAccessServlet.class, PATH + VirSatModelAccessServlet.MODEL_API + SUFFIX);
		servletContextHandler.addServlet(RepoManagementServlet.class, PATH + RepoManagementServlet.MANAGEMENT_API + SUFFIX);
		
		setupSecurity(server, servletContextHandler);
	}
	
	/**
	 * Setup and add HTTP connector to the server
	 * @param server the Server
	 */
	private void setupHttps(Server server) {
		// Setup SSL keystore
		SslContextFactory.Server sslContextFactory = new SslContextFactory.Server();
		sslContextFactory.setKeyStorePath(ServerConfiguration.getHttpsKeystorePath());
		sslContextFactory.setKeyStorePassword(ServerConfiguration.getHttpsKeystorePassword());
		sslContextFactory.setKeyManagerPassword(ServerConfiguration.getHttpsKeystoreManagerPassword());

		// Setup HTTPS
		HttpConfiguration httpsConf = new HttpConfiguration();
		httpsConf.setSecureScheme(HttpScheme.HTTPS.asString());
		httpsConf.setSecurePort(VIRSAT_JETTY_PORT_HTTPS);
		// Customizer that extracts the attribute from an SSLContext and sets them on the request
		// So that servlets can see the encryption details
		httpsConf.addCustomizer(new SecureRequestCustomizer());

		// HTTPS connector using the HTTP protocol over an SSL connection
		ServerConnector httpsConnector = new ServerConnector(server,
			new SslConnectionFactory(sslContextFactory, HttpVersion.HTTP_1_1.asString()),
			new HttpConnectionFactory(httpsConf));
		httpsConnector.setPort(VIRSAT_JETTY_PORT_HTTPS);

		server.addConnector(httpsConnector);
	}

	/**
	 * Setup and add HTTPS connector to the server
	 * @param server the Server
	 * @param httpsEnabled if HTTPS is enabled
	 */
	private void setupHttp(Server server, boolean httpsEnabled) {
		final HttpConfiguration httpConfiguration = new HttpConfiguration();

		if (httpsEnabled) {
			// For confidential or integral redirections
			httpConfiguration.setSecureScheme(HttpScheme.HTTPS.asString());
			httpConfiguration.setSecurePort(VIRSAT_JETTY_PORT_HTTPS);
		}

		// Simple HTTP connector
		final ServerConnector http = new ServerConnector(server,
				new HttpConnectionFactory(httpConfiguration));
		http.setPort(VIRSAT_JETTY_PORT);
		server.addConnector(http);
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