package de.dlr.sc.virsat.server.jetty;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.ConnectException;
import java.net.URI;
import java.net.URL;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.ws.rs.ProcessingException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.eclipse.jetty.http.HttpStatus;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.server.ServerProperties;
import org.junit.After;
import org.junit.Test;

import de.dlr.sc.virsat.server.Activator;
import de.dlr.sc.virsat.server.configuration.ServerConfiguration;
import de.dlr.sc.virsat.server.servlet.StatusServlet;

public class VirSatJettyServerTest {
	private VirSatJettyServer server;
	private WebTarget httpTarget;
	private WebTarget httpsTarget;
	
	private class InsecureTrustManager implements X509TrustManager {
		@Override
		public void checkClientTrusted(final X509Certificate[] chain, final String authType) throws CertificateException { }

		@Override
		public void checkServerTrusted(final X509Certificate[] chain, final String authType) throws CertificateException { }

		@Override
		public X509Certificate[] getAcceptedIssuers() {
			return new X509Certificate[0];
		}
	}
	
	private void initServerAndClient() throws Exception {
		server = new VirSatJettyServer();
		server.init();
		server.start();
		
		ClientConfig config = new ClientConfig();
		Client httpClient = ClientBuilder.newClient(config);
		
		TrustManager[] trustAllCerts = { new InsecureTrustManager() };
		SSLContext ctx = SSLContext.getInstance("SSL");
		ctx.init(null, trustAllCerts, null);
//		config.getProperties().put(HTTPSProperties.PROPERTY_HTTPS_PROPERTIES, new HTTPSProperties(hostnameVerifier, ctx));
		Client httpsClient = ClientBuilder.newClient(config);
		
		// TODO: use port variable
		URI uriHttp = UriBuilder.fromUri("http://localhost:8000/").build();
		httpTarget = httpClient.target(uriHttp).path("/status");
		
		URI uriHttps = UriBuilder.fromUri("https://localhost:8001/").build();
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
