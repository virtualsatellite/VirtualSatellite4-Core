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

import java.security.Principal;
import java.util.UUID;

import javax.ws.rs.core.SecurityContext;

import de.dlr.sc.virsat.server.auth.ServerUser;

public class RepositorySecurityContext implements SecurityContext {

	private ServerUser user;
	private String scheme;
	
	public RepositorySecurityContext(ServerUser user, String scheme) {
		this.user = user;
		this.scheme = scheme;
	}
	
	@Override
	public String getAuthenticationScheme() {
		return scheme;
	}

	@Override
	public Principal getUserPrincipal() {
		return user;
	}

	@Override
	public boolean isSecure() {
		// Here we could check for HTTPS
		return false;
	}

	@Override
	public boolean isUserInRole(String role) {
		return user.getServerRole() == role;
	}
	
	public boolean canAccessRepository(UUID repositoryUUID) {
		return user.getRepositories().contains(repositoryUUID);
	}
}
