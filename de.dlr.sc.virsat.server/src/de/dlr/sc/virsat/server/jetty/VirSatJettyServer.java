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

import static org.eclipse.jetty.ee10.servlet.ServletContextHandler.NO_SESSIONS;

import java.io.IOException;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.jetty.ee10.servlet.DefaultServlet;
import org.eclipse.jetty.ee10.servlet.ServletContextHandler;
import org.eclipse.jetty.ee10.servlet.ServletHolder;
import org.eclipse.jetty.ee10.servlet.security.ConstraintSecurityHandler;
import org.eclipse.jetty.http.HttpScheme;
import org.eclipse.jetty.http.HttpVersion;
import org.eclipse.jetty.security.LoginService;
import org.eclipse.jetty.security.authentication.BasicAuthenticator;
import org.eclipse.jetty.server.HttpConfiguration;
import org.eclipse.jetty.server.HttpConnectionFactory;
import org.eclipse.jetty.server.SecureRequestCustomizer;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.server.SslConnectionFactory;
import org.eclipse.jetty.util.ssl.SslContextFactory;
import org.glassfish.jersey.server.ServerProperties;

import de.dlr.sc.virsat.server.Activator;
import de.dlr.sc.virsat.server.auth.LoginServiceFactory;
import de.dlr.sc.virsat.server.configuration.ServerConfiguration;
import de.dlr.sc.virsat.server.servlet.RepoManagementServletContainer;
import de.dlr.sc.virsat.server.servlet.StatusServlet;
import jakarta.servlet.GenericServlet;
import de.dlr.sc.virsat.server.servlet.ModelAccessServletContainer;

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
	public static final String PATH = "/rest";
	public static final String STATUS = "/status";
	public static final String SWAGGER = "/ui";
	private static final String SUFFIX = "/*";
	
	private LoginService loginService = null;
	private int serverPort = -1;

	private Server server;
	
	/**
	 *  Call this method to start the Virtual Satellite specific Jetty Server.
	 * @return the started virsat jetty instance
	 * @throws Exception in case server fails to start correctly
	 */
	public VirSatJettyServer start() throws Exception {
		Activator.getDefault().getLog().info("About to start Virtual Satellite Server Thread");
		server.start();
		return this;
	}
	
	/**
	 * Call this method to setup the server
	 */
	public void init() {
		boolean httpsOnly = ServerConfiguration.getHttpsOnly();
		boolean httpsEnabled = ServerConfiguration.getHttpsEnabled();

		Activator.getDefault().getLog().info("Initializing server with hhtpsOnly(" + httpsOnly + ") and httpsEnabled(" + httpsEnabled + ")");

		// Setup the classloader, otherwise problems may occur when running tests on maven tycho. 
		ClassLoader equinoxClassLoader = this.getClass().getClassLoader();
		ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
		try {
			Thread.currentThread().setContextClassLoader(equinoxClassLoader);
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
	
			Activator.getDefault().getLog().info("Setting up Servlets");
			ServletContextHandler servletContextHandler = new ServletContextHandler(NO_SESSIONS);
			
			servletContextHandler.setContextPath("/");
			
			// Uncomment this servlet for activating the persons example
			// it is a good example for debugging the connection between jersey and swagger and openAPI
			//servletContextHandler.addServlet(PersonServletContainer.class, "/rest/persons/*");
			
			registerServlet(servletContextHandler, StatusServlet.class, "Server Status", "/status");
			registerServlet(servletContextHandler, ModelAccessServletContainer.class, "Model API", PATH + ModelAccessServletContainer.MODEL_API + SUFFIX);
			registerServlet(servletContextHandler, RepoManagementServletContainer.class, "Model API", PATH + RepoManagementServletContainer.MANAGEMENT_API + SUFFIX);
			
			// provide swagger UI at base path
			Activator.getDefault().getLog().info("Setting up Servlet for: Swagger UI");
			var resourceUrl = de.dlr.sc.virsat.server.swagger.ui.Activator.getDefault().getSwaggerUiResourceUrl();
	        String resourceBasePath = "";
			try {
				resourceBasePath = FileLocator.toFileURL(resourceUrl).toExternalForm();
				registerServlet(servletContextHandler, DefaultServlet.class, "Swagger UI", SWAGGER + SUFFIX);
		        servletContextHandler.setWelcomeFiles(new String[] {"index.html"});
		        servletContextHandler.setBaseResourceAsString(resourceBasePath);
			} catch (IOException e) {
				Activator.getDefault().getLog().error("Failed to provide Servlet with Swagger UI", e);
			}
			
			server.setHandler(servletContextHandler);
			
			Activator.getDefault().getLog().info("Setting up Security");
			setupSecurity(server, servletContextHandler);
		} finally {
			Thread.currentThread().setContextClassLoader(contextClassLoader);
		}
	}
	
	
	private void registerServlet(ServletContextHandler contextHandler,  Class<? extends GenericServlet> servlet, String purpose, String apiEndPoint) {
		Activator.getDefault().getLog().info("Initializing Servlet for <" + purpose + "> @ localhost:" + VIRSAT_JETTY_PORT + apiEndPoint);
		ServletHolder holder = contextHandler.addServlet(servlet, apiEndPoint);
		holder.setInitParameter(ServerProperties.WADL_FEATURE_DISABLE, "true");
	}
	
	public void setServerPort(int port) {
		this.serverPort = port;
	}
	
	/**
	 * Setup and add HTTP connector to the server
	 * @param server the Server
	 */
	private void setupHttps(Server server) {
		// Setup SSL keystore
		SslContextFactory.Server sslContextFactory = new SslContextFactory.Server();
		sslContextFactory.setKeyStorePath(ServerConfiguration.getHttpsKeystoreFileUri());
		sslContextFactory.setKeyStorePassword(ServerConfiguration.getHttpsKeystorePassword());
		sslContextFactory.setKeyManagerPassword(ServerConfiguration.getHttpsKeystoreManagerPassword());

		// Setup HTTPS
		HttpConfiguration httpsConf = new HttpConfiguration();
		httpsConf.setSecureScheme(HttpScheme.HTTPS.asString());
		httpsConf.setSecurePort(VIRSAT_JETTY_PORT_HTTPS);
		// Customizer that extracts the attribute from an SSLContext and sets them on the request
		// So that servlets can see the encryption details
		boolean sniRequired = ServerConfiguration.getHttpsSniRequired();
		boolean sniHostCheck = ServerConfiguration.getHttpsSniHostCheck();
		
		SecureRequestCustomizer srCustomizer = new SecureRequestCustomizer();
		srCustomizer.setSniRequired(sniRequired);
		srCustomizer.setSniHostCheck(sniHostCheck);
		httpsConf.addCustomizer(srCustomizer);

		// HTTPS connector using the HTTP protocol over an SSL connection
		ServerConnector httpsConnector = new ServerConnector(server,
			new SslConnectionFactory(sslContextFactory, HttpVersion.HTTP_1_1.asString()),
			new HttpConnectionFactory(httpsConf));
		if (serverPort != -1) {
			httpsConnector.setPort(serverPort);
		} else {
			httpsConnector.setPort(VIRSAT_JETTY_PORT_HTTPS);
		}
		
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
		if (serverPort != -1) {
			http.setPort(serverPort);
		} else {
			http.setPort(VIRSAT_JETTY_PORT);
		}
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
		servletContextHandler.setSecurityHandler(security);

		/**
		 *  For top down security constraints with roles can be created here
		 */

		security.setAuthenticator(new BasicAuthenticator());
		security.setLoginService(loginService);

		//security.setHandler(servletContextHandler);
	}
	
	public VirSatJettyServer join() throws InterruptedException {
		if (server != null) {
			Activator.getDefault().getLog().info("Waiting for Server thread to shut down");
			server.join();
		}
		return this;
	}
	
	/**
	 * Call this method to stop the server
	 * @throws Exception in case the server cannot be stopped correctly
	 */
	public void stop() throws Exception {
		if (server != null) {
			Activator.getDefault().getLog().info("About to stop Server Thread");
			server.stop();
		}
	}
	
	public LoginService getLoginService() {
		return loginService;
	}
}