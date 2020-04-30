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

import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import de.dlr.sc.virsat.server.auth.ServerRoles;
import de.dlr.sc.virsat.server.resources.AuthTestResource;

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

		SecurityContext sc = context.getSecurityContext();

		// Administrators have access to all repositories
		if (!sc.isUserInRole(ServerRoles.ADMIN)) {
			String repositoryName = getRepositoryName(context);
			
			// Repository access is granted if the user has a role named after the repository
			if (!sc.isUserInRole(repositoryName)) {
				context.abortWith(Response.status(Response.Status.FORBIDDEN).entity("Forbidden").build());
			}
		}
	}

	/**
	 * Gets the repository from the contexts path
	 * Expects a path formed like "optional/repository/repositoryName/optional"
	 * @param context
	 * @return repositoryName
	 */
	private String getRepositoryName(ContainerRequestContext context) {
		String path = context.getUriInfo().getPath();
		String substring = path.substring(path.indexOf(AuthTestResource.REPOSITORY) + AuthTestResource.REPOSITORY.length() + 1);
		
		String repositoryName = substring;
		if (substring.indexOf("/") > 0) {
			repositoryName = substring.substring(0, substring.indexOf("/"));
		}
		return repositoryName;
	}

}
