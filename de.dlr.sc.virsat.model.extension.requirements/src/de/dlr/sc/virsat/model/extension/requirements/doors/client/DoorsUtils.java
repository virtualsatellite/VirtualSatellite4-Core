/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.requirements.doors.client;

import java.io.IOException;
import java.net.MalformedURLException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import javax.ws.rs.client.ClientBuilder;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.ssl.SSLContextBuilder;
import org.eclipse.lyo.client.JEEFormAuthenticator;
import org.eclipse.lyo.client.OslcClient;
import org.glassfish.jersey.apache.connector.ApacheConnectorProvider;
import org.glassfish.jersey.client.ClientConfig;

import de.dlr.sc.virsat.model.extension.requirements.Activator;

/**
 * Utility methods for Doors requirement handling
 *
 */
public class DoorsUtils {

	protected String server;
	protected String user;
	protected String password;
	
	public DoorsUtils(String doorsServer, String doorsUser, String doorsPassword) {
		this.server = doorsServer;
		this.user = doorsUser;
		this.password = doorsPassword;
	}
	
	/**
	 * Get OslcClient to communicate with Doors server
	 * @return the client
	 * @throws UnrecoverableKeyException
	 * @throws MalformedURLException
	 * @throws CertificateException
	 * @throws IOException
	 */
	protected OslcClient getClient() throws UnrecoverableKeyException, MalformedURLException, CertificateException, IOException {
		ClientBuilder clientBuilder = configureClientBuilder();
		OslcClient client = new OslcClient(clientBuilder);
		return client;
	}
	
	/**
	 * Configure clientBuilder to authenticate to the Doors server
	 * @return the clientBuilder
	 */
	protected ClientBuilder configureClientBuilder() {
		ClientConfig clientConfig = new ClientConfig().connectorProvider(new ApacheConnectorProvider());
		ClientBuilder clientBuilder = ClientBuilder.newBuilder();
		clientBuilder.withConfig(clientConfig);

		// Setup SSL support to ignore self-assigned SSL certificates - for testing
		// only!!
		SSLContextBuilder sslContextBuilder = new SSLContextBuilder();
		try {
			sslContextBuilder.loadTrustMaterial(TrustSelfSignedStrategy.INSTANCE);
			clientBuilder.sslContext(sslContextBuilder.build());
		} catch (NoSuchAlgorithmException | KeyStoreException | KeyManagementException e) {
			Activator.getDefault().getLog().error(e.getMessage());
		}
		clientBuilder.hostnameVerifier(NoopHostnameVerifier.INSTANCE);

		// Authenticate
		JEEFormAuthenticator authenticator = new JEEFormAuthenticator(server, user, password);
		clientBuilder.register(authenticator);
		return clientBuilder;
	}
}