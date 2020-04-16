/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.server.resources;

import static org.junit.Assert.assertEquals;

import java.net.URI;
import java.util.Base64;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import org.glassfish.jersey.client.ClientConfig;
import org.junit.Test;

import de.dlr.sc.virsat.server.test.AGitAndJettyServerTest;

public class AuthTestResourceTest extends AGitAndJettyServerTest {
	
	private static final String BASIC_SCHEME = "Basic";
	
	@Test
	public void testAuthentication() {
		ClientConfig config = new ClientConfig();
		Client client = ClientBuilder.newClient(config);
		
		URI uri = UriBuilder.fromUri("http://localhost:8000/").build();
		WebTarget target = client.target(uri);
		
		String serverResponse = target.
			path("/rest").
			path("/auth").
			path("/denied").
			request().
			get(Response.class)
			.toString();
		
		String expectedResponse = "InboundJaxrsResponse{context=ClientResponse{method=GET, uri=http://localhost:8000/rest/auth/denied, status=403, reason=Forbidden}}";
		
		assertEquals("Server response is correct", expectedResponse, serverResponse);
		
		String serverResponse2 = target.
			path("/rest").
			path("/auth").
			path("/permitted").
			request().
			get(Response.class)
			.toString();
		
		String serverString = target.
			path("/rest").
			path("/auth").
			path("/permitted").
			request().
			get(String.class);
		
		String expectedResponse2 = "InboundJaxrsResponse{context=ClientResponse{method=GET, uri=http://localhost:8000/rest/auth/permitted, status=200, reason=OK}}";
		
		assertEquals("Server response is correct", expectedResponse2, serverResponse2);
		assertEquals("Server String payload is correct", AuthTestResource.PERMITTED, serverString);
	}
	
	@Test
	public void testHttpAuthorization() {
		ClientConfig config = new ClientConfig();
		Client client = ClientBuilder.newClient(config);
		
		URI uri = UriBuilder.fromUri("http://localhost:8000/").build();
		WebTarget target = client.target(uri);
		
		String serverResponse = target.
			path("/rest").
			path("/auth").
			path("/all_users").
			request().
			get(Response.class)
			.toString();
		
		String expectedResponse = "InboundJaxrsResponse{context=ClientResponse{method=GET, uri=http://localhost:8000/rest/auth/all_users, status=403, reason=Forbidden}}";
		assertEquals("Unauthorized response because of missing header", expectedResponse, serverResponse);
	
		String serverResponse2 = target.
				path("/rest").
				path("/auth").
				path("/all_users").
				request().
				header(HttpHeaders.AUTHORIZATION, "").
				get(Response.class)
				.toString();
		
		assertEquals("Unauthorized response because of empty header", expectedResponse, serverResponse2);
		
		String serverResponse3 = target.
				path("/rest").
				path("/auth").
				path("/all_users").
				request().
				header(HttpHeaders.AUTHORIZATION, "username:password").
				get(Response.class)
				.toString();
		
		assertEquals("Unauthorized response because of not encoded header", expectedResponse, serverResponse3);
		
		String encoded = BASIC_SCHEME + " " + Base64.getEncoder().encodeToString("unknown:password".getBytes());
		String serverResponse4 = target.
				path("/rest").
				path("/auth").
				path("/all_users").
				request().
				header(HttpHeaders.AUTHORIZATION, encoded).
				get(Response.class)
				.toString();
		
		assertEquals("Unauthorized response because of unknown user", expectedResponse, serverResponse4);
		
		String serverResponse5 = target.
				path("/rest").
				path("/auth").
				path("/all_users").
				request().
				header(HttpHeaders.AUTHORIZATION, DEFAULT_AUTHORIZATION_HEADER).
				get(Response.class)
				.toString();
		
		String expectedResponse5 = "InboundJaxrsResponse{context=ClientResponse{method=GET, uri=http://localhost:8000/rest/auth/all_users, status=200, reason=OK}}";
		assertEquals("Correct server response: user correctly authenticated and authorized", expectedResponse5, serverResponse5);
	}

	@Test
	public void testServerRoles() {
		ClientConfig config = new ClientConfig();
		Client client = ClientBuilder.newClient(config);
		
		URI uri = UriBuilder.fromUri("http://localhost:8000/").build();
		WebTarget target = client.target(uri);
		
		String serverResponse = target.
				path("/rest").
				path("/auth").
				path("/user_only").
				request().
				header(HttpHeaders.AUTHORIZATION, DEFAULT_AUTHORIZATION_HEADER).
				get(Response.class)
				.toString();
			
		String expectedResponse = "InboundJaxrsResponse{context=ClientResponse{method=GET, uri=http://localhost:8000/rest/auth/user_only, status=200, reason=OK}}";
		assertEquals("User can access user only ressource", expectedResponse, serverResponse);
		
		String serverResponse2 = target.
				path("/rest").
				path("/auth").
				path("/admin_only").
				request().
				header(HttpHeaders.AUTHORIZATION, DEFAULT_AUTHORIZATION_HEADER).
				get(Response.class)
				.toString();
			
		String expectedResponse2 = "InboundJaxrsResponse{context=ClientResponse{method=GET, uri=http://localhost:8000/rest/auth/admin_only, status=403, reason=Forbidden}}";
		assertEquals("User can't access admin only ressource", expectedResponse2, serverResponse2);
		
		String encodedAdmin = BASIC_SCHEME + " " + Base64.getEncoder().encodeToString(ADMIN.getBytes());
		String serverResponse3 = target.
				path("/rest").
				path("/auth").
				path("/admin_only").
				request().
				header(HttpHeaders.AUTHORIZATION, encodedAdmin).
				get(Response.class)
				.toString();
			
		String expectedResponse3 = "InboundJaxrsResponse{context=ClientResponse{method=GET, uri=http://localhost:8000/rest/auth/admin_only, status=200, reason=OK}}";
		assertEquals("Admin can access admin only ressource", expectedResponse3, serverResponse3);
		
		String serverResponse4 = target.
				path("/rest").
				path("/auth").
				path("/user_only").
				request().
				header(HttpHeaders.AUTHORIZATION, encodedAdmin).
				get(Response.class)
				.toString();
			
		String expectedResponse4 = "InboundJaxrsResponse{context=ClientResponse{method=GET, uri=http://localhost:8000/rest/auth/user_only, status=403, reason=Forbidden}}";
		assertEquals("Admin can't access user only ressource", expectedResponse4, serverResponse4);
	}
	
	// @Ignore
	@Test
	/**
	 * Ignore repositories for now
	 */
	public void testRepositoryAuthorization() {
		ClientConfig config = new ClientConfig();
		Client client = ClientBuilder.newClient(config);
		
		URI uri = UriBuilder.fromUri("http://localhost:8000/").build();
		WebTarget target = client.target(uri);
		
		String encodedUserNoRepo = DEFAULT_AUTHORIZATION_HEADER;
		String serverResponse = target.
				path("/rest").
				path("/auth").
				path("/repository").
				path("/testRepo").
				request().
				header(HttpHeaders.AUTHORIZATION, encodedUserNoRepo).
				get(Response.class)
				.toString();
			
		String expectedResponse = ("InboundJaxrsResponse{context=ClientResponse{method=GET, uri=http://localhost:8000/rest/auth/repository/testRepo, status=403, reason=Forbidden}}");
		assertEquals("This user can't access the repository", expectedResponse, serverResponse);
		
		String encodedUserWithRepo = BASIC_SCHEME + " " + Base64.getEncoder().encodeToString(USER_WITH_REPO.getBytes());
		String serverResponse2 = target.
				path("/rest").
				path("/auth").
				path("/repository").
				path("/testRepo").
				request().
				header(HttpHeaders.AUTHORIZATION, encodedUserWithRepo).
				get(Response.class)
				.toString();
			
		String expectedResponse2 = ("InboundJaxrsResponse{context=ClientResponse{method=GET, uri=http://localhost:8000/rest/auth/repository/testRepo, status=200, reason=OK}}");
		assertEquals("This user can access the repository", expectedResponse2, serverResponse2);
		
		String encodedAdmin = BASIC_SCHEME + " " + Base64.getEncoder().encodeToString(ADMIN.getBytes());
		String serverResponse3 = target.
				path("/rest").
				path("/auth").
				path("/repository").
				path("/testRepo").
				request().
				header(HttpHeaders.AUTHORIZATION, encodedAdmin).
				get(Response.class)
				.toString();
			
		String expectedResponse3 = ("InboundJaxrsResponse{context=ClientResponse{method=GET, uri=http://localhost:8000/rest/auth/repository/testRepo, status=200, reason=OK}}");
		assertEquals("Admins can access all repositories", expectedResponse3, serverResponse3);
	}
	
	@Test(expected = Test.None.class /* No Exception expected */)
	public void testFilterInteraction() {
		ClientConfig config = new ClientConfig();
		Client client = ClientBuilder.newClient(config);
		
		URI uri = UriBuilder.fromUri("http://localhost:8000/").build();
		WebTarget target = client.target(uri);
		
		// The RepositoryFilter won't be called if the request got denied by jersey before
		// so this won't produce an Exception in RepositoryFilter"
		String encoded = BASIC_SCHEME + " " + Base64.getEncoder().encodeToString("unknown:password".getBytes());
		target.
			path("/rest").
			path("/auth").
			path("/repository").
			request().
			header(HttpHeaders.AUTHORIZATION, encoded).
			get(Response.class);
		
	}
}
