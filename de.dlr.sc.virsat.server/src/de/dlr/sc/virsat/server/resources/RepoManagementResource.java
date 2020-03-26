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

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

import de.dlr.sc.virsat.server.controller.RepoManagemantController;
import de.dlr.sc.virsat.server.dataaccess.VirSatGitAccess;
import de.dlr.sc.virsat.team.VersionControlSystem;

public class RepoManagementResource {

	public static final String PATH = "repository";
	public static final String PARAM_NAME = "name";
	public static final String PARAM_REMOTE_URL = "remoteURL";
	public static final String PARAM_USER = "user";
	public static final String PARAM_PASSWORD = "password";
	public static final String PARAM_BACKEND = "backend";

	private RepoManagemantController controller;

	public RepoManagementResource() {

	}

	@GET
	@Path(PATH)
	public String getRepository(@QueryParam(PARAM_NAME) String repoName) {
		String repoConfigJSON = ""; // Todo
		controller.getRepository(repoName).getRepositoryConfiguration();
		return repoConfigJSON;
	}

	@DELETE
	@Path(PATH)
	public String deleteRepository(@QueryParam(PARAM_NAME) String repoName) {
		String responseJSON = ""; // Todo
		controller.deleteRepository(repoName);
		return responseJSON;
	}

	@POST
	@Path(PATH)
	public String addRepository(@QueryParam(PARAM_NAME) String repoName, @QueryParam(PARAM_REMOTE_URL) String remoteURL,
			@QueryParam(PARAM_BACKEND) String backend, @QueryParam(PARAM_USER) String userName,
			@QueryParam(PARAM_PASSWORD) String password) {
		String responseJSON = ""; // Todo
		controller.addNewRepository(repoName, remoteURL, VersionControlSystem.valueOf(backend), userName, password);
		return responseJSON;
	}
	
	@PUT
	@Path(PATH)
	public String updateRepository(@QueryParam(PARAM_NAME) String repoName, @QueryParam(PARAM_REMOTE_URL) String remoteURL,
			@QueryParam(PARAM_BACKEND) String backend, @QueryParam(PARAM_USER) String userName,
			@QueryParam(PARAM_PASSWORD) String password) {
		String responseJSON = ""; // Todo
		controller.updateRepository(repoName, remoteURL, VersionControlSystem.valueOf(backend), userName, password);
		return responseJSON;
	}

}
