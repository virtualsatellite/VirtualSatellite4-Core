/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.server.auth.filter;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.StringTokenizer;

import javax.annotation.Priority;
import javax.annotation.security.DenyAll;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;

import de.dlr.sc.virsat.server.auth.ServerRoles;
import de.dlr.sc.virsat.server.auth.ServerUser;
import de.dlr.sc.virsat.server.auth.userhandler.IServerUserHandler;
import de.dlr.sc.virsat.server.auth.userhandler.ServerUserHandlerFactory;

/**
 * This filter verifies clients are authenticated and authorized (with Server Role)
 */
@Priority(Priorities.AUTHENTICATION)
public class AuthFilter implements ContainerRequestFilter {

	public static final String BASIC_SCHEME = "Basic";
	
	public static final String ACCESS_DENIED = "Access denied.";
	public static final String NO_AUTH_HEADER = "No authentification header found.";
	public static final String NOT_AUTHORIZED = "Not authorized.";
	
	@Context
	private ResourceInfo resourceInfo;
	
	private IServerUserHandler userHandler;
	
	public AuthFilter() {
		userHandler = new ServerUserHandlerFactory().getServerUserHandler();
	}
	
	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		filterAuthenticated(requestContext);
	}

	/** 
	 * First checks for Authentication Annotations @PermitAll, @DenyAll
	 * Then get's user data from request Header and checks if the user is known
	 * Next checks for the @RolesAllowed Annotation
	 * Checks if the has the requested role OR by default if the user has any valid server role
	 * 
	 * If the user is known, then a RepositorySecurityContext will be created
	 * 
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
		
		ServerUser user = userHandler.getUser(username, password);
		
		// Check if the user is known
		if (user == null) {
			requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).entity(NOT_AUTHORIZED).build());
			return;
		}
		
		// Set our custom security context that we can access in our resources and later filters
		requestContext.setSecurityContext(new RepositorySecurityContext(user, BASIC_SCHEME));

		// Check if roles are specified
		if (resourceMethod.isAnnotationPresent(RolesAllowed.class)) {
			
			// Check if the user is authorized
			RolesAllowed rolesAnnotation = resourceMethod.getAnnotation(RolesAllowed.class);
			List<String> roles = Arrays.asList(rolesAnnotation.value());
			
			if (!isAuthorized(user, roles)) {
				requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).entity(NOT_AUTHORIZED).build());
			}
			return;
		} 
		
		// Check if the user has any valid server role
		if (!isAuthorized(user, ServerRoles.getAllRoles())) {
			requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).entity(NOT_AUTHORIZED).build());
		}
	}
	
	
	/**
	 * @param user the user to be authorized
	 * @param roles permitted for the requested resource
	 * @return boolean if the user is authorized
	 */
	public boolean isAuthorized(ServerUser user, List<String> roles) {
		
		return roles.contains(user.getServerRole());
	}
	
}
