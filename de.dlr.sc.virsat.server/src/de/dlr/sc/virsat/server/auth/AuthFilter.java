/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.server.auth;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.StringTokenizer;

import javax.annotation.security.DenyAll;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;

/*
 * Verifies clients are authenticated
 * And authorized
 */
public class AuthFilter implements ContainerRequestFilter {

	public static final String BASIC_SCHEME = "Basic";
	
	private static final String ACCESS_DENIED = "Access denied.";
	private static final String NO_AUTH_HEADER = "No authentification header found.";
	private static final String NOT_AUTHORIZED = "Not authorized.";
	
	@Context
	private ResourceInfo resourceInfo;
	
	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		filterAuthenticated(requestContext);
	}

	/** 
	 * Checks for authentication Annotations @PermitAll, @DenyAll and @RolesAllowed
	 * @param requestContext
	 * @throws IOException
	 */
	public void filterAuthenticated(ContainerRequestContext requestContext) throws IOException {
		Method resourceMethod = resourceInfo.getResourceMethod();
		
		// Resource denied for all users
		if (resourceMethod.isAnnotationPresent(DenyAll.class)) {
			requestContext.abortWith(Response.status(Response.Status.FORBIDDEN).entity(ACCESS_DENIED).build());
			return;
		}
		
		// No authentication required: even not authenticated users can access this resource
		if (resourceMethod.isAnnotationPresent(PermitAll.class)) {
			return;
		}
		
		// Check for a valid header
		String authHeader = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
		if (authHeader == null || authHeader.equals("")) {
			requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).entity(NO_AUTH_HEADER).build());
			return;
		}
			
		// Access credentials
		String encodedCredentials = authHeader.replace(BASIC_SCHEME + " ", "");
		
		String decodedCredentials = "";
		try {
			decodedCredentials = new String(Base64.getDecoder().decode(encodedCredentials));
		} catch (IllegalArgumentException e) {
			requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).entity(NOT_AUTHORIZED).build());
			return;
		}
		
		StringTokenizer tokenizer = new StringTokenizer(decodedCredentials, ":");
		String username = tokenizer.nextToken();
		String password = tokenizer.nextToken();
		
		// Check if roles are specified
		if (resourceMethod.isAnnotationPresent(RolesAllowed.class)) {
			
			// Check if the user is authorized
			RolesAllowed rolesAnnotation = resourceMethod.getAnnotation(RolesAllowed.class);
			List<String> roles = Arrays.asList(rolesAnnotation.value());
			
			if (!isAuthorized(username, password, roles)) {
				requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).entity(NOT_AUTHORIZED).build());
			}
			return;
		} 
		
		// Check if the user has any valid server role
		if (!isAuthorized(username, password, ServerRoles.getAllRoles())) {
			requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).entity(NOT_AUTHORIZED).build());
		}
		
	}
	
	public boolean isAuthorized(String username, String password, List<String> roles) {
		return true;
	}
	
}
