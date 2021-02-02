/*******************************************************************************
 * Copyright (c) 2021 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.server.jetty;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.net.URL;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.X509TrustManager;
import javax.ws.rs.ProcessingException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.eclipse.jetty.http.HttpScheme;
import org.eclipse.jetty.http.HttpStatus;
import org.eclipse.jetty.util.ssl.SslContextFactory.X509ExtendedTrustManagerWrapper;
import org.glassfish.jersey.client.ClientConfig;
import org.junit.After;
import org.junit.Test;

import de.dlr.sc.virsat.server.Activator;
import de.dlr.sc.virsat.server.configuration.ServerConfiguration;
import de.dlr.sc.virsat.server.servlet.StatusServlet;

public class VirSatJettyServerTest {
	private VirSatJettyServer server;
	private WebTarget httpTarget;
	private WebTarget httpsTarget;

	private static class TrustAllHostNameVerifier implements HostnameVerifier {
		@Override
		public boolean verify(String hostname, SSLSession session) {
			return true;
		}
	}

	/**
	 * Creates the server and client targets for HTTP and HTTPS
	 * @throws Exception
	 */
	private void initServerAndClient() throws Exception {
		server = new VirSatJettyServer();
		server.init();
		server.start();
		
		ClientConfig config = new ClientConfig();
		Client httpClient = ClientBuilder.newClient(config);
		
		// Mock an SSL client that accepts everything
		SSLContext ctx = SSLContext.getInstance("SSL");
		ctx.init(null, new X509TrustManager[]{new X509ExtendedTrustManagerWrapper(null)}, null);
		Client httpsClient = ClientBuilder.newBuilder()
				.withConfig(config)
				.hostnameVerifier(new TrustAllHostNameVerifier())
				.sslContext(ctx)
				.build();
		
		URI uriHttp = UriBuilder.fromUri(HttpScheme.HTTP.asString() + "://localhost:" + VirSatJettyServer.VIRSAT_JETTY_PORT).build();
		httpTarget = httpClient.target(uriHttp).path("/status");
		
		URI uriHttps = UriBuilder.fromUri(HttpScheme.HTTPS.asString() + "://localhost:" + VirSatJettyServer.VIRSAT_JETTY_PORT_HTTPS).build();
		httpsTarget = httpsClient.target(uriHttps).path("/status");
	}
	
	@After
	public void tearDown() throws Exception {
		if (server != null) {
			server.stop();
			server.join();
		}
	}
	
	private void assertCorrectStatus(WebTarget target) {
		Response response = target.request().get();
		assertEquals(HttpStatus.OK_200, response.getStatus());
		assertTrue(response.readEntity(String.class).contains(StatusServlet.OK_STATUS_JSON));
	}
	
	private void assertCantConnect(WebTarget target) {
		assertThrows(ProcessingException.class, () -> {
			target.request().get();
		});
	}
	
	@Test
	public void testDefaultConfig() throws Exception {
		initServerAndClient();
		
		assertCorrectStatus(httpTarget);
		assertCantConnect(httpsTarget);
	}
	
	private void loadHttpsProperties() throws FileNotFoundException, IOException {
		String realmResourceName = "resources/https.properties";
		URL realmProps = FileLocator.resolve(FileLocator.find(Activator.getDefault().getBundle(), new Path(realmResourceName)));
		ServerConfiguration.loadProperties(realmProps.openStream());
	}
	
	@Test
	public void testHttps() throws Exception {
		loadHttpsProperties();
		initServerAndClient();
		
		assertCorrectStatus(httpTarget);
		assertCorrectStatus(httpsTarget);
	}
	
	@Test
	public void testHttpsOnly() throws Exception {
		loadHttpsProperties();
		ServerConfiguration.setHttpsOnly(true);
		initServerAndClient();
		
		assertCantConnect(httpTarget);
		assertCorrectStatus(httpsTarget);
	}
}
