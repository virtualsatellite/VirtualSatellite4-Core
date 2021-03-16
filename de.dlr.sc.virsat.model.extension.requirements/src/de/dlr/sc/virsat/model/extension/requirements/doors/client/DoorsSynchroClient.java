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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Response;

import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.ssl.SSLContextBuilder;
import org.eclipse.lyo.client.JEEFormAuthenticator;
import org.eclipse.lyo.client.OSLCConstants;
import org.eclipse.lyo.client.OslcClient;
import org.eclipse.lyo.client.exception.ResourceNotFoundException;
import org.eclipse.lyo.client.query.OslcQuery;
import org.eclipse.lyo.client.query.OslcQueryParameters;
import org.eclipse.lyo.client.query.OslcQueryResult;
import org.glassfish.jersey.apache.connector.ApacheConnectorProvider;
import org.glassfish.jersey.client.ClientConfig;

public class DoorsSynchroClient {

	private DoorsSynchroClient() {
		
	}
	
	private static final String SERVER_NAME = "https://gk-sl0002.intra.dlr.de:9443/rm/";
	private static final String CATALOG_URL = "https://gk-sl0002.intra.dlr.de:9443/rm/oslc_rm/catalog";
	private static final String PROJECT_NAME = "MBSE4GK Testprojekt";

	/**
	 * 
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		ClientBuilder clientBuilder = configureClientBuilder();
		OslcClient doorsClient = new OslcClient(clientBuilder);

		String serviceProviderUrl = lookUpServiceProviderUrl(doorsClient, PROJECT_NAME);
		String queryCapabilityUrl = lookUpQueryCapability(doorsClient, serviceProviderUrl);

		queryRequirements(doorsClient, queryCapabilityUrl);
	}

	private static String lookUpServiceProviderUrl(OslcClient client, String projectName) throws ResourceNotFoundException, IOException, URISyntaxException {
		String serviceProviderUrl = client.lookupServiceProviderUrl(CATALOG_URL, projectName);
		return serviceProviderUrl;
	}
	
	private static String lookUpQueryCapability(OslcClient client, String serviceProvider) throws ResourceNotFoundException, IOException, URISyntaxException {
		String queryCapabilty = client.lookupQueryCapability(serviceProvider, OSLCConstants.OSLC_RM_V2, OSLCConstants.RM_REQUIREMENT_TYPE);
		return queryCapabilty;
	}
	
	/**
	 * 
	 * @param client
	 * @param queryCapability
	 * @throws IOException
	 */
	private static void queryRequirements(OslcClient client, String queryCapability) throws IOException {
		OslcQueryParameters queryParams = new OslcQueryParameters();
		queryParams.setSelect("*");
//		queryParams.setWhere("");
		OslcQuery query = new OslcQuery(client, queryCapability, queryParams);
		OslcQueryResult result = query.submit();
		processRawResponse(result.getRawResponse());
	}
	
	/**
	 * 
	 * @param response
	 * @throws IOException
	 */
	private static void processRawResponse(Response response) throws IOException {
		InputStream is = response.readEntity(InputStream.class);
		BufferedReader in = new BufferedReader(new InputStreamReader(is));

		String line = null;
		while ((line = in.readLine()) != null) {
			System.out.println(line);
		}
		System.out.println();
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
	private static ClientBuilder configureClientBuilder()
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
		String loginPasswordFile = "../../../Requirements/client_virsat/DoorsLogin.txt";
		String login = Files.readAllLines(Paths.get(loginPasswordFile)).get(0);
		String password = Files.readAllLines(Paths.get(loginPasswordFile)).get(1);
		JEEFormAuthenticator authenticator = new JEEFormAuthenticator(SERVER_NAME, login, password);
		clientBuilder.register(authenticator);

		return clientBuilder;
	}
}