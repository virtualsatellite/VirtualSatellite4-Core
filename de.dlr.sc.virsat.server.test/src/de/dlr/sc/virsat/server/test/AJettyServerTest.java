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
import java.net.URL;
import java.util.Base64;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.UriBuilder;

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.eclipse.jetty.security.HashLoginService;
import org.glassfish.jersey.client.ClientConfig;
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

	private static final String ALLOW_HEADERS = "sun.net.http.allowRestrictedHeaders";
	
	// Test users
	public static final String ADMIN = "admin:password";
	public static final String USER_NO_REPO = "user:password";
	public static final String USER_WITH_REPO = "user2:password";
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
		Client client = ClientBuilder.newClient(config);
		
		URI uri = UriBuilder.fromUri(VirSatJettyServer.HTTP_SCHEME + "://localhost:" + VirSatJettyServer.VIRSAT_JETTY_PORT).build();
		webTarget = client.target(uri).path("/rest");
	}

	private static void setUpTestUsers() throws IOException {
		HashLoginService loginService = (HashLoginService) server.getLoginService();
		
		String realmResourceName = "resources/test_users.properties";
		URL realmProps = FileLocator.resolve(FileLocator.find(Activator.getDefault().getBundle(), new Path(realmResourceName)));
		loginService.setConfig(realmProps.toString());
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
