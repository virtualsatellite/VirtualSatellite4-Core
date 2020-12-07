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
import javax.ws.rs.core.Response;

import org.eclipse.jetty.http.HttpStatus;
import org.junit.Test;

import de.dlr.sc.virsat.server.servlet.VirSatModelAccessServlet;
import de.dlr.sc.virsat.server.test.AJettyServerTest;

public class AuthTestResourceTest extends AJettyServerTest {
	
	@Test
	public void testAuthentication() {
		Response serverResponse = getRequest("/denied");
		assertEquals("Server response is correct", HttpStatus.FORBIDDEN_403, serverResponse.getStatus());
		
		Response serverResponse2 = getRequest("/permitted");
		assertEquals("Server response is correct", HttpStatus.OK_200, serverResponse2.getStatus());
	}
	
	@Test
	public void testHttpAuthorization() {
		Response serverResponse = getRequest("/all_users");
		assertEquals("Unauthorized response because of missing header", HttpStatus.FORBIDDEN_403, serverResponse.getStatus());
	
		Response serverResponse2 = getRequest("/all_users", "");
		assertEquals("Unauthorized response because of empty header", HttpStatus.FORBIDDEN_403, serverResponse2.getStatus());
		
		Response serverResponse3 = getRequest("/all_users", "username:password");
		assertEquals("Unauthorized response because of not encoded header", HttpStatus.FORBIDDEN_403, serverResponse3.getStatus());
		
		Response serverResponse4 = getRequest("/all_users", getAuthHeader("unknown:password"));
		assertEquals("Unauthorized response because of unknown user", HttpStatus.FORBIDDEN_403, serverResponse4.getStatus());
		
		Response serverResponse5 = getRequest("/all_users", getAuthHeader(USER_NO_REPO));
		assertEquals("Correct server response: user correctly authenticated and authorized", HttpStatus.OK_200, serverResponse5.getStatus());
	}

	@Test
	public void testServerRoles() {
		Response serverResponse = getRequest("/user_only", getAuthHeader(USER_NO_REPO));
		assertEquals("User can access user only ressource", HttpStatus.OK_200, serverResponse.getStatus());
		
		Response serverResponse2 = getRequest("/admin_only", getAuthHeader(USER_NO_REPO));
		assertEquals("User can't access admin only ressource", HttpStatus.FORBIDDEN_403, serverResponse2.getStatus());
		
		String encodedAdmin = getAuthHeader(ADMIN);
		Response serverResponse3 = getRequest("/admin_only", encodedAdmin);
		assertEquals("Admin can access admin only ressource", HttpStatus.OK_200, serverResponse3.getStatus());
		
		Response serverResponse4 = getRequest("/user_only", encodedAdmin);
		assertEquals("Admin can't access user only ressource", HttpStatus.FORBIDDEN_403, serverResponse4.getStatus());
	}
	
	@Test
	/**
	 * Ignore repositories for now
	 */
	public void testRepositoryAuthorization() {
		final String REPO_PATH = AuthTestResource.REPOSITORY + "/testRepo";
		
		String encodedUserNoRepo = getAuthHeader(USER_NO_REPO);
		Response serverResponse = getRequest(REPO_PATH, encodedUserNoRepo);
		assertEquals("This user can't access the repository", HttpStatus.FORBIDDEN_403, serverResponse.getStatus());
		
		String encodedUserWithRepo = getAuthHeader(USER_WITH_REPO);
		Response serverResponse2 = getRequest(REPO_PATH, encodedUserWithRepo);
		assertEquals("This user can access the repository", HttpStatus.OK_200, serverResponse2.getStatus());
		
		String encodedAdmin = getAuthHeader(ADMIN);
		Response serverResponse3 = getRequest(REPO_PATH, encodedAdmin);
		assertEquals("Admins can access all repositories", HttpStatus.OK_200, serverResponse3.getStatus());
		
		Response serverResponse4 = getRequest(REPO_PATH, encodedUserWithRepo);
		assertEquals("This user can access the repository property", HttpStatus.OK_200, serverResponse4.getStatus());
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
	
	/**
	 * Sends a get request
	 * @param path concrete resource path
	 * @return server Response
	 */
	private Response getRequest(String path) {
		return webTarget
				.path(VirSatModelAccessServlet.MODEL_API)
				.path(AuthTestResource.AUTH)
				.path(path)
				.request()
				.get();
	}
	
	/**
	 * Sends a get request
	 * @param path concrete resource path
	 * @param user to be put into the header
	 * @return server Response
	 */
	private Response getRequest(String path, String user) {
		return webTarget
			.path(VirSatModelAccessServlet.MODEL_API)
			.path(AuthTestResource.AUTH)
			.path(path)
			.request()
			.header(HttpHeaders.AUTHORIZATION, user)
			.get();
	}
}
