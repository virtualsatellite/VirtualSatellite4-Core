/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.server.test;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Base64;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.UriBuilder;

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.eclipse.jetty.http.HttpScheme;
import org.eclipse.jetty.security.HashLoginService;
import org.eclipse.jetty.util.resource.Resource;
import org.eclipse.jetty.util.resource.ResourceFactory;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.JerseyClientBuilder;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;

import de.dlr.sc.virsat.concept.unittest.util.test.AConceptTestCase;
import de.dlr.sc.virsat.server.Activator;
import de.dlr.sc.virsat.server.jetty.VirSatJettyServer;
import de.dlr.sc.virsat.server.repository.RepoRegistry;

public abstract class AJettyServerTest extends AConceptTestCase {

	private static VirSatJettyServer server;
	private static final File WORKSPACE_ROOT = ResourcesPlugin.getWorkspace().getRoot().getLocation().toFile();

	public static final String ALLOW_HEADERS = "sun.net.http.allowRestrictedHeaders";
	
	// Test users
	public static final String ADMIN = "admin:password";
	public static final String USER_NO_REPO = "user:password";
	protected static final String USER_WITH_REPO_NAME = "user2";
	public static final String USER_WITH_REPO = USER_WITH_REPO_NAME + ":password";
	// Test user headers
	public static final String ADMIN_HEADER = getAuthHeader(ADMIN);
	public static final String USER_NO_REPO_HEADER = getAuthHeader(USER_NO_REPO);
	public static final String USER_WITH_REPO_HEADER = getAuthHeader(USER_WITH_REPO);
	
	protected static WebTarget webTarget;
	
	public static String getAuthHeader(String userAndPassword) {
		return "Basic " + Base64.getEncoder().encodeToString(userAndPassword.getBytes());
	}
	
	public static File makeAbsolute(File relativePath) throws IOException {
		return new File(WORKSPACE_ROOT, relativePath.toString());
	}
	
	@BeforeClass
	public static void setUpClass() throws InterruptedException, Exception {
		// System property to set headers
		System.setProperty(ALLOW_HEADERS, "true");
		
		server = new VirSatJettyServer();
		server.init();
		
		setUpTestUsers();
		
		server.start();
		
		ClientConfig config = new ClientConfig();
		Client client = JerseyClientBuilder.newClient(config);
		
		URI uri = UriBuilder.fromUri(HttpScheme.HTTP.asString() + "://localhost:" + Activator.VIRSAT_JETTY_PORT).build();
		webTarget = client.target(uri).path("/rest");
	}

	/**
	 * Setting up the test user from the test users properties file
	 * Setting it up means creating a PathResource from the TesourceFactory and
	 * Handing it over to the login service.
	 * @throws IOException
	 * @throws URISyntaxException
	 */
	private static void setUpTestUsers() throws IOException, URISyntaxException {
		HashLoginService loginService = (HashLoginService) server.getLoginService();
		
		String realmResourceName = "resources/test_users.properties";
		
		URL url = FileLocator.find(Activator.getDefault().getBundle(), new Path(realmResourceName));
		String uri = FileLocator.toFileURL(url).toURI().toString();
		
		try (ResourceFactory.Closeable resourceFactory = ResourceFactory.closeable()) {
			Resource authPropertiesFileResource = resourceFactory.newResource(uri);
			loginService.setConfig(authPropertiesFileResource);
		} catch (Exception e) {
			throw e;
		}
	}
	
	@After
	public void tearDown() throws Exception {
		RepoRegistry.getInstance().getRepositories().clear();
	}
	
	@AfterClass
	public static void tearDownClass() throws Exception { 
		server.stop();
		server.join();

		System.setProperty(ALLOW_HEADERS, "false");
	}
}
