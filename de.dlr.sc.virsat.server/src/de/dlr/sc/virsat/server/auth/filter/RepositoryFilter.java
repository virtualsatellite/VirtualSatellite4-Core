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
import java.util.UUID;

import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;

import de.dlr.sc.virsat.server.auth.ServerQueryParams;
import de.dlr.sc.virsat.server.auth.ServerRoles;

/**
 * This filter checks if a user has access to the wanted repository resource.
 */
@Priority(Priorities.AUTHORIZATION)
public class RepositoryFilter implements ContainerRequestFilter {

	/**
	 * Uses the RepositorySecurityContext to check if the User can access the requested repository. 
	 * Administrators can access all repositories.
	 */
	@Override
	public void filter(ContainerRequestContext context) throws IOException {
		// Get RepositorySecurityContext set in AuthFilter
		RepositorySecurityContext sc = (RepositorySecurityContext) context.getSecurityContext();

		// Administrators have access to all repositories
		if (!sc.isUserInRole(ServerRoles.ADMIN)) {
			String uuid = context.getUriInfo().getQueryParameters().getFirst(ServerQueryParams.REPOSITORY_UUID);
			
			// No UUID query parameter provided
			if (uuid == null) {
				context.abortWith(Response.status(Response.Status.FORBIDDEN).entity(AuthFilter.ACCESS_DENIED).build());
				return;
			}
			
			if (!sc.canAccessRepository(UUID.fromString(uuid))) {
				context.abortWith(Response.status(Response.Status.FORBIDDEN).entity(AuthFilter.ACCESS_DENIED).build());
			}
		}
	}

}
