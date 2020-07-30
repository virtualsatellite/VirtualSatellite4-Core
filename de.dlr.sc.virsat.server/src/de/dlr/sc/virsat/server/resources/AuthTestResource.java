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

import javax.annotation.security.DenyAll;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

import de.dlr.sc.virsat.server.auth.ServerRoles;

@Path(AuthTestResource.AUTH)
public class AuthTestResource {
	
	public static final String REPOSITORY = "/repository";
	public static final String AUTH = "/auth";
	
	public static final String PERMITTED = "Permitted";
	public static final String ADMIN_DATA = "Admin";
	public static final String USER_DATA = "User";
	public static final String GENERAL_DATA = "General";
	public static final String REPOSITORY_DATA = "Repository";
	
	@GET
	@Path("/denied")
	@DenyAll
	public void getDenied() { }
	
	@GET
	@Path("/permitted")
	@PermitAll
	public String getPermitted() {
		return PERMITTED;
	}
	
	@GET
	@Path("/admin_only")
	@RolesAllowed(ServerRoles.ADMIN)
	public String getAdminData() {
		return ADMIN_DATA;
	}
	
	@GET
	@Path("/user_only")
	@RolesAllowed(ServerRoles.USER)
	public String getUserData() {
		return USER_DATA;
	}
	
	@GET
	@Path("/all_users")
	@RolesAllowed({ServerRoles.ADMIN, ServerRoles.USER})
	public String getGeneralData() {
		return GENERAL_DATA;
	}
	
	@GET
	@Path(REPOSITORY + "/{name}")
	public String getRepository() {
		return REPOSITORY_DATA;
	}
	
	@GET
	@Path(REPOSITORY + "/{name}/property")
	public String getRepositoryProperty() {
		return REPOSITORY_DATA;
	}
}
