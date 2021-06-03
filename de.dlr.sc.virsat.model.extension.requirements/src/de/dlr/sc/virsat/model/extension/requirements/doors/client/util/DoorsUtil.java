/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.requirements.doors.client.util;

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

/**
 * Utility methods for Doors requirement handling
 *
 */
public class DoorsUtil {

	private DoorsUtil() {

	}
	
	private static final String SERVER_NAME = "https://gk-sl0002.intra.dlr.de:9443/rm/";
	
	public static OslcClient getClient(String password, String login, String server) throws KeyManagementException, UnrecoverableKeyException, NoSuchAlgorithmException, KeyStoreException, MalformedURLException, CertificateException, IOException {
		ClientBuilder clientBuilder = configureClientBuilder(password, login, server);
		OslcClient client = new OslcClient(clientBuilder);
		return client;
	}
	
	/**
	 * 
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws KeyStoreException
	 * @throws KeyManagementException
	 * @throws MalformedURLException
	 * @throws IOException
	 * @throws CertificateException
	 * @throws UnrecoverableKeyException
	 */
	private static ClientBuilder configureClientBuilder(String password, String login, String server)
			throws NoSuchAlgorithmException, KeyStoreException, KeyManagementException, MalformedURLException,
			IOException, CertificateException, UnrecoverableKeyException {

		ClientConfig clientConfig = new ClientConfig().connectorProvider(new ApacheConnectorProvider());
		ClientBuilder clientBuilder = ClientBuilder.newBuilder();
		clientBuilder.withConfig(clientConfig);

		// Setup SSL support to ignore self-assigned SSL certificates - for testing
		// only!!
		SSLContextBuilder sslContextBuilder = new SSLContextBuilder();
		sslContextBuilder.loadTrustMaterial(TrustSelfSignedStrategy.INSTANCE);
		clientBuilder.sslContext(sslContextBuilder.build());
		clientBuilder.hostnameVerifier(NoopHostnameVerifier.INSTANCE);

		// Authenticate
//		String loginPasswordFile = "../../Requirements/client_virsat/DoorsLogin.txt";
//		String login = Files.readAllLines(Paths.get(loginPasswordFile)).get(0);
//		String password = Files.readAllLines(Paths.get(loginPasswordFile)).get(1);
		JEEFormAuthenticator authenticator = new JEEFormAuthenticator(server, login, password);
		clientBuilder.register(authenticator);

		return clientBuilder;
	}
	
	
	
}
