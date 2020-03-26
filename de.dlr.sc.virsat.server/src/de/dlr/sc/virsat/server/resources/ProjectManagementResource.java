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

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import de.dlr.sc.virsat.server.configuration.RepositoryConfiguration;
import de.dlr.sc.virsat.server.configuration.SimpleConf;
import de.dlr.sc.virsat.server.controller.RepoManagemantController;
import de.dlr.sc.virsat.server.repository.RepoRegistry;
import de.dlr.sc.virsat.server.repository.ServerRepository;
import de.dlr.sc.virsat.team.VersionControlSystem;

@Path(ProjectManagementResource.PATH)
public class ProjectManagementResource {

	public static final String PATH = "/project";
	public static final String PATH_ALL_REPOSITORIES = "repositories";
	public static final String PARAM_NAME = "name";
	public static final String PARAM_REMOTE_URL = "remoteURL";
	public static final String PARAM_USER = "user";
	public static final String PARAM_PASSWORD = "password";
	public static final String PARAM_BACKEND = "backend";

	private RepoManagemantController controller;

	public ProjectManagementResource() {
		controller = new RepoManagemantController();
	}

	@GET
	public Response getAllRepositories() {
		List<RepositoryConfiguration> configurations = new ArrayList<RepositoryConfiguration>();
		for (Entry<String, ServerRepository> entry : RepoRegistry.getInstance().getRepositories().entrySet()) {
			configurations.add(entry.getValue().getRepositoryConfiguration());
		}
		return Response.status(Response.Status.OK).entity(configurations).build();
	}

	
	@GET
	@Path("/{projectName}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getRepository(@PathParam("projectName") String projectName) {
		if (controller.getRepository(projectName) != null) {
			RepositoryConfiguration configuration = controller.getRepository(projectName).getRepositoryConfiguration();
			return Response.status(Response.Status.OK).entity(configuration).build();
		} else {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
	}

	@DELETE
	@Path("/{projectName}")
	public Response deleteRepository(@PathParam("projectName") String repoName) {
		controller.deleteRepository(repoName);
		return Response.status(Response.Status.OK).build();
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addRepository(RepositoryConfiguration configuration) {
		controller.addNewRepository(configuration);
		return Response.status(Response.Status.OK).build();
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateRepository(RepositoryConfiguration configuration) {
		controller.updateRepository(configuration);
		return Response.status(Response.Status.OK).build();
	}

}
