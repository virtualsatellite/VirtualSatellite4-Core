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

import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.Properties;

import javax.net.ssl.SSLContext;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Configuration;

import org.apache.http.auth.InvalidCredentialsException;
import org.apache.http.client.HttpClient;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.ssl.SSLContextBuilder;
import org.eclipse.lyo.client.OSLCConstants;
import org.eclipse.lyo.client.OslcClient;
import org.eclipse.lyo.client.OslcClientFactory;
import org.eclipse.lyo.client.OslcOAuthClient;
import org.eclipse.lyo.client.OslcOAuthClientBuilder;
import org.eclipse.lyo.client.RootServicesHelper;
import org.eclipse.lyo.client.UnderlyingHttpClient;
import org.eclipse.lyo.client.exception.ResourceNotFoundException;
import org.eclipse.lyo.client.exception.RootServicesException;
import org.glassfish.jersey.apache.connector.ApacheClientProperties;
import org.glassfish.jersey.apache.connector.ApacheConnectorProvider;
import org.glassfish.jersey.client.ClientConfig;

import net.oauth.OAuthException;

public class DoorsSynchroClient {

	private static final String SERVER_NAME = "https://gk-sl0002.intra.dlr.de:9443/rm";
	private static final String CATALOG_URL = "https://gk-sl0002.intra.dlr.de:9443/rm/oslc_rm/catalog";
	private static final String CONSUMER = "";
	private static final String SECRET = "";

	public static void main(String[] args) throws ResourceNotFoundException, RootServicesException,
			InvalidCredentialsException, IOException, URISyntaxException, OAuthException, KeyManagementException,
			NoSuchAlgorithmException, KeyStoreException, CertificateException, UnrecoverableKeyException {

		ClientBuilder clientBuilder = configureClientBuilder(true);

		// Initialize a Jazz rootservices helper
		OslcClient rootServicesClient = new OslcClient(clientBuilder);
		RootServicesHelper helper = new RootServicesHelper(SERVER_NAME, OSLCConstants.OSLC_RM_V2, rootServicesClient);

		OslcOAuthClientBuilder oAuthClientBuilder = OslcClientFactory.oslcOAuthClientBuilder();
		oAuthClientBuilder.setFromRootService(helper);
		oAuthClientBuilder.setOAuthConsumer("", CONSUMER, SECRET);
		oAuthClientBuilder.setClientBuilder(clientBuilder);
		oAuthClientBuilder.setUnderlyingHttpClient(new UnderlyingHttpClient() {
			@Override
			public HttpClient get(Client client) {
				return ApacheConnectorProvider.getHttpClient(client);
			}
		});
		OslcOAuthClient oAuthClient = (OslcOAuthClient) oAuthClientBuilder.build();

		oAuthClient.performOAuthNegotiation(SERVER_NAME);
		oAuthClient.getResource(CATALOG_URL);
//		try {
//			oAuthClient.getResource(SERVER_NAME, OSLCConstants.CT_RDF);
//		} catch (OAuthRedirectException oauthE) {
//			DoorsHttpUtils.validateTokens(oAuthClient,
//					((OAuthRedirectException) oauthE).getRedirectURL() + "?oauth_token="
//							+ ((OAuthRedirectException) oauthE).getAccessor().requestToken,
//					LOGIN, PASSWORD, SERVER_NAME + "/j_security_check");
//			// Try to access again
//			Response response = oAuthClient.getResource(SERVER_NAME, OSLCConstants.CT_RDF);
//			response.readEntity(InputStream.class).close();
//		}
	}

	/**
	 * 
	 * @param selfAssignedSSL
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws KeyStoreException
	 * @throws KeyManagementException
	 * @throws MalformedURLException
	 * @throws IOException
	 * @throws CertificateException
	 * @throws UnrecoverableKeyException
	 */
	private static ClientBuilder configureClientBuilder(final boolean selfAssignedSSL)
			throws NoSuchAlgorithmException, KeyStoreException, KeyManagementException, MalformedURLException,
			IOException, CertificateException, UnrecoverableKeyException {
		// Use HttpClient instead of the default HttpUrlConnection
		ApacheConnectorProvider apacheConnectorProvider = new ApacheConnectorProvider();

		ClientConfig clientConfig = new ClientConfig().connectorProvider(apacheConnectorProvider)
				.property(ApacheClientProperties.DISABLE_COOKIES, false);
		ClientBuilder clientBuilder = ClientBuilder.newBuilder();
		clientBuilder.withConfig(clientConfig);

		KeyStore trustStore = KeyStore.getInstance("JKS");
		char[] pwdArray = "changeit".toCharArray();
		trustStore.load(new FileInputStream("C:\\Users\\gefk_an\\Requirements\\Certificates\\myTrustStore"), pwdArray);
		trustStore.getCertificate("JazzServerCert1");
		clientBuilder.trustStore(trustStore);
		clientBuilder.getConfiguration();

		SSLContextBuilder sslContextBuilder = SSLContextBuilder.create();
		sslContextBuilder.loadKeyMaterial(trustStore, pwdArray);
		sslContextBuilder.loadTrustMaterial(trustStore, null);
		SSLContext sslContext = sslContextBuilder.build();
		clientBuilder.hostnameVerifier(NoopHostnameVerifier.INSTANCE);
		clientBuilder.sslContext(sslContext);
		Configuration configuration = clientBuilder.getConfiguration();
		clientBuilder.withConfig(configuration);
		
		//set keystore containing self-signed certificate
		Properties systemProp = System.getProperties();
		systemProp.put("javax.net.ssl.keyStorePassword", "changeit");
		systemProp.put("javax.net.ssl.keyStore",
					"C:\\Users\\gefk_an\\Requirements\\Certificates\\keystore.jks");
		System.setProperties(systemProp);
		System.getProperty("javax.net.ssl.keyStore");

		return clientBuilder;
	}
}