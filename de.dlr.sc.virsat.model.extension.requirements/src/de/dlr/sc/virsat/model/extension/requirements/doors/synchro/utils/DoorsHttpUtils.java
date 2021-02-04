package de.dlr.sc.virsat.model.extension.requirements.doors.synchro.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriBuilder;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.auth.InvalidCredentialsException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.params.HttpClientParams;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;
import org.apache.wink.client.ClientResponse;
import org.eclipse.lyo.client.OslcClient;
import org.eclipse.lyo.client.OslcClientFactory;
import org.eclipse.lyo.client.OslcOAuthClient;
import org.eclipse.lyo.client.OslcOAuthClientBuilder;
import org.eclipse.lyo.client.RootServicesHelper;
import org.glassfish.jersey.client.ClientConfig;

public class DoorsHttpUtils {
	static public boolean DEBUG = false;

	static String AUTHREQUIRED = "X-com-ibm-team-repository-web-auth-msg";

	/**
	 * Print out the HTTPResponse headers
	 */
	public static void printResponseHeaders(ClientResponse response) {
		MultivaluedMap<String, String> headers = response.getHeaders();
		for (String header : headers.keySet()) {
			System.out.println("\t- " + header + ": " + headers.getFirst(header));
		}
	}

	/**
	 * Print out the HTTP Response body
	 */
	public static void printResponseBody(ClientResponse response) {
		InputStream input = response.getEntity(InputStream.class);
		if (input == null)
			return;
		BufferedReader reader;
		try {
			reader = new BufferedReader(new InputStreamReader(input));
			String line = reader.readLine();
			while (line != null) {
				System.out.println(line);
				line = reader.readLine();
			}
			reader.close();
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void validateTokens(OslcClient rootServicesClient, String redirect, String user, String password,
			String authURL) throws IOException, InvalidCredentialsException {

		// First GET
//		HttpGet request2 = new HttpGet(redirect);
//		HttpClientParams.setRedirecting(request2.getParams(), false);
//		HttpResponse response = client.getHttpClient().execute(request2);
//		EntityUtils.consume(response.getEntity());

		HttpGet request = new HttpGet(redirect);
		DefaultHttpClient httpClient = new DefaultHttpClient();
		HttpResponse response = httpClient.execute(request);
		EntityUtils.consume(response.getEntity());

		
		// Second GET
//		Header location = response.getFirstHeader("Location");
//		HttpGet request3 = new HttpGet(location.getValue());
//		HttpClientParams.setRedirecting(request3.getParams(), false);
//		response = client.getHttpClient().execute(request3);
//		EntityUtils.consume(response.getEntity());

		Header location = response.getFirstHeader("Location");
		HttpGet request2 = new HttpGet(location.getValue());
		response = httpClient.execute(request2);
		EntityUtils.consume(response.getEntity());
		
		
		// POST to login form
		// The server requires an authentication: Create the login form
		// Following line should be like : "https://server:port/jts/j_security_check"
		HttpPost formPost = new HttpPost(authURL);
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("j_username", user));
		nvps.add(new BasicNameValuePair("j_password", password));
		formPost.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));

		HttpResponse formResponse = httpClient.execute(formPost);
		EntityUtils.consume(formResponse.getEntity());

		location = formResponse.getFirstHeader("Location");
		// Third GET
		HttpGet request4 = new HttpGet(location.getValue());
		response = httpClient.execute(request4);
		EntityUtils.consume(response.getEntity());

		location = response.getFirstHeader("Location");
		// Fourth GET
		HttpGet request5 = new HttpGet(location.getValue());
		response = httpClient.execute(request5);
		EntityUtils.consume(response.getEntity());

		Map<String, String> oAuthMap = getQueryMap(location.getValue());
		String oauthToken = oAuthMap.get("oauth_token");
		String oauthverifier = oAuthMap.get("oauth_verifier");

		// The server requires an authentication: Create the login form
		HttpPost formPost2 = new HttpPost(authURL);
		formPost2.addHeader("oauth_token", oauthToken);
		List<NameValuePair> nvps1 = new ArrayList<NameValuePair>();
		nvps1.add(new BasicNameValuePair("oauth_token", oauthToken));
		nvps1.add(new BasicNameValuePair("oauth_verifier", oauthverifier));
		nvps1.add(new BasicNameValuePair("authorize", "true"));
		formPost.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));
		formPost2.addHeader("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");

		formResponse = httpClient.execute(formPost2);
		EntityUtils.consume(formResponse.getEntity());

		Header header = formResponse.getFirstHeader("Content-Length");
		if ((header != null) && (!("0".equals(header.getValue())))) {
			// The login failed
			throw new InvalidCredentialsException("Authentication failed");
		} else {
			EntityUtils.consume(formResponse.getEntity());
		}
	}

	private static Map<String, String> getQueryMap(String query) {
		Map<String, String> map = new HashMap<String, String>();
		String[] params = query.split("&"); //$NON-NLS-1$

		for (String param : params) {
			String name = param.split("=")[0]; //$NON-NLS-1$
			String value = param.split("=")[1]; //$NON-NLS-1$
			map.put(name, value);
		}

		return map;
	}


}
