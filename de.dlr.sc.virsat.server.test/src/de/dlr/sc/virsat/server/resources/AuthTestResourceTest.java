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

import javax.ws.rs.core.HttpHeaders;

import org.junit.Test;

import de.dlr.sc.virsat.server.test.AGitAndJettyServerTest;

public class AuthTestResourceTest extends AGitAndJettyServerTest {
	
	@Test
	public void testAuthentication() {
		
		String serverResponse = webTarget.path(AuthTestResource.AUTH).path("/denied")
				.request()
				.get()
				.toString();
		String expectedResponse = "InboundJaxrsResponse{context=ClientResponse{method=GET, uri=http://localhost:8000/rest/auth/denied, status=403, reason=Forbidden}}";
		assertEquals("Server response is correct", expectedResponse, serverResponse);
		
		String serverResponse2 = webTarget.path(AuthTestResource.AUTH).path("/permitted")
				.request()
				.get()
				.toString();
		String expectedResponse2 = "InboundJaxrsResponse{context=ClientResponse{method=GET, uri=http://localhost:8000/rest/auth/permitted, status=200, reason=OK}}";
		assertEquals("Server response is correct", expectedResponse2, serverResponse2);
	}
	
	@Test
	public void testHttpAuthorization() {
		
		String serverResponse = webTarget.path(AuthTestResource.AUTH).path("/all_users")
				.request()
				.get()
				.toString();
		String expectedResponse = "InboundJaxrsResponse{context=ClientResponse{method=GET, uri=http://localhost:8000/rest/auth/all_users, status=403, reason=Forbidden}}";
		assertEquals("Unauthorized response because of missing header", expectedResponse, serverResponse);
	
		String serverResponse2 = webTarget.path(AuthTestResource.AUTH).path("/all_users")
				.request()
				.header(HttpHeaders.AUTHORIZATION, "")
				.get()
				.toString();
		assertEquals("Unauthorized response because of empty header", expectedResponse, serverResponse2);
		
		String serverResponse3 = webTarget.path(AuthTestResource.AUTH).path("/all_users")
				.request()
				.header(HttpHeaders.AUTHORIZATION, "username:password")
				.get()
				.toString();
		assertEquals("Unauthorized response because of not encoded header", expectedResponse, serverResponse3);
		
		String encoded = getAuthHeader("unknown:password");
		String serverResponse4 = webTarget.path(AuthTestResource.AUTH).path("/all_users")
				.request()
				.header(HttpHeaders.AUTHORIZATION, encoded)
				.get()
				.toString();
		assertEquals("Unauthorized response because of unknown user", expectedResponse, serverResponse4);
		
		String serverResponse5 = webTarget.path(AuthTestResource.AUTH).path("/all_users")
				.request()
				.header(HttpHeaders.AUTHORIZATION, getAuthHeader(USER_NO_REPO))
				.get()
				.toString();
		String expectedResponse5 = "InboundJaxrsResponse{context=ClientResponse{method=GET, uri=http://localhost:8000/rest/auth/all_users, status=200, reason=OK}}";
		assertEquals("Correct server response: user correctly authenticated and authorized", expectedResponse5, serverResponse5);
	}

	@Test
	public void testServerRoles() {

		String serverResponse = webTarget.path(AuthTestResource.AUTH).path("/user_only")
				.request()
				.header(HttpHeaders.AUTHORIZATION, getAuthHeader(USER_NO_REPO))
				.get()
				.toString();
		String expectedResponse = "InboundJaxrsResponse{context=ClientResponse{method=GET, uri=http://localhost:8000/rest/auth/user_only, status=200, reason=OK}}";
		assertEquals("User can access user only ressource", expectedResponse, serverResponse);
		
		String serverResponse2 = webTarget.path(AuthTestResource.AUTH).path("/admin_only")
				.request()
				.header(HttpHeaders.AUTHORIZATION, getAuthHeader(USER_NO_REPO))
				.get()
				.toString();
		String expectedResponse2 = "InboundJaxrsResponse{context=ClientResponse{method=GET, uri=http://localhost:8000/rest/auth/admin_only, status=403, reason=Forbidden}}";
		assertEquals("User can't access admin only ressource", expectedResponse2, serverResponse2);
		
		String encodedAdmin = getAuthHeader(ADMIN);
		String serverResponse3 = webTarget.path(AuthTestResource.AUTH).path("/admin_only")
				.request()
				.header(HttpHeaders.AUTHORIZATION, encodedAdmin)
				.get()
				.toString();
		String expectedResponse3 = "InboundJaxrsResponse{context=ClientResponse{method=GET, uri=http://localhost:8000/rest/auth/admin_only, status=200, reason=OK}}";
		assertEquals("Admin can access admin only ressource", expectedResponse3, serverResponse3);
		
		String serverResponse4 = webTarget.path(AuthTestResource.AUTH).path("/user_only")
				.request()
				.header(HttpHeaders.AUTHORIZATION, encodedAdmin)
				.get()
				.toString();
		String expectedResponse4 = "InboundJaxrsResponse{context=ClientResponse{method=GET, uri=http://localhost:8000/rest/auth/user_only, status=403, reason=Forbidden}}";
		assertEquals("Admin can't access user only ressource", expectedResponse4, serverResponse4);
	}
	
	@Test
	/**
	 * Ignore repositories for now
	 */
	public void testRepositoryAuthorization() {
		
		String encodedUserNoRepo = getAuthHeader(USER_NO_REPO);
		String serverResponse = webTarget.path(AuthTestResource.AUTH).path(AuthTestResource.REPOSITORY).path("/testRepo")
				.request()
				.header(HttpHeaders.AUTHORIZATION, encodedUserNoRepo)
				.get()
				.toString();
		String expectedResponse = ("InboundJaxrsResponse{context=ClientResponse{method=GET, uri=http://localhost:8000/rest/auth/repository/testRepo, status=403, reason=Forbidden}}");
		assertEquals("This user can't access the repository", expectedResponse, serverResponse);
		
		String encodedUserWithRepo = getAuthHeader(USER_WITH_REPO);
		String serverResponse2 = webTarget.path(AuthTestResource.AUTH).path(AuthTestResource.REPOSITORY).path("/testRepo")
				.request()
				.header(HttpHeaders.AUTHORIZATION, encodedUserWithRepo)
				.get()
				.toString();			
		String expectedResponse2 = ("InboundJaxrsResponse{context=ClientResponse{method=GET, uri=http://localhost:8000/rest/auth/repository/testRepo, status=200, reason=OK}}");
		assertEquals("This user can access the repository", expectedResponse2, serverResponse2);
		
		String encodedAdmin = getAuthHeader(ADMIN);
		String serverResponse3 = webTarget.path(AuthTestResource.AUTH).path(AuthTestResource.REPOSITORY).path("/testRepo")
				.request()
				.header(HttpHeaders.AUTHORIZATION, encodedAdmin)
				.get()
				.toString();			
		String expectedResponse3 = ("InboundJaxrsResponse{context=ClientResponse{method=GET, uri=http://localhost:8000/rest/auth/repository/testRepo, status=200, reason=OK}}");
		assertEquals("Admins can access all repositories", expectedResponse3, serverResponse3);
		
		String serverResponse4 = webTarget.path(AuthTestResource.AUTH).path(AuthTestResource.REPOSITORY).path("/testRepo").path("/property")
				.request()
				.header(HttpHeaders.AUTHORIZATION, encodedUserWithRepo)
				.get()
				.toString();			
		String expectedResponse4 = ("InboundJaxrsResponse{context=ClientResponse{method=GET, uri=http://localhost:8000/rest/auth/repository/testRepo/property, status=200, reason=OK}}");
		assertEquals("This user can access the repository property", expectedResponse4, serverResponse4);
	}
	
	@Test
	public void testFilterInteraction() {
		
		// The RepositoryFilter won't be called if the request got denied by jersey before
		// so this won't produce an Exception in RepositoryFilter"
		String encoded = getAuthHeader("unknown:password");
		webTarget.path(AuthTestResource.AUTH).path("/repository")
			.request()
			.header(HttpHeaders.AUTHORIZATION, encoded)
			.get();
		
	}
}
