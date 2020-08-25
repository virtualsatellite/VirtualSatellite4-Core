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

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import de.dlr.sc.virsat.server.configuration.RepositoryConfiguration;
import de.dlr.sc.virsat.server.controller.RepoManagementController;
import de.dlr.sc.virsat.server.repository.ServerRepository;

@Path(ProjectManagementResource.PATH)
public class ProjectManagementResource {

	public static final String PATH = "/project";

	private RepoManagementController controller;

	public ProjectManagementResource() {
		controller = new RepoManagementController();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllProjects() {
		List<String> projects = new ArrayList<>(controller.getAllProjectNames());
		GenericEntity<List<String>> entity = new GenericEntity<List<String>>(projects) { };
		return Response.status(Response.Status.OK).entity(entity).build();
	}

	
	/**
	 * Gets the configuration for the given project name.
	 * If project does not exist returns status NOT_FOUND
	 */
	@GET
	@Path("/{projectName}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getProject(@PathParam("projectName") String projectName) {
		ServerRepository serverRepository = controller.getRepository(projectName);
		if (serverRepository != null) {
			RepositoryConfiguration configuration = serverRepository.getRepositoryConfiguration();
			return Response.status(Response.Status.OK).entity(configuration).build();
		} else {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
	}

	/**
	 * Deletes a project
	 * @param repoName name of the project to delete
	 */
	@DELETE
	@Path("/{projectName}")
	public Response deleteProject(@PathParam("projectName") String repoName) {
		try {
			controller.deleteRepository(repoName);
		} catch (IOException e) {
			return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
		}
		return Response.status(Response.Status.OK).build();
	}

	/**
	 * Creates or updates a project configuration on the project specified by the URL.
	 * URL project overrides project name in the passed configuration if they are different.
	 * @param configuration should contain URL and backend
	 */
	@PUT
	@Path("/{projectName}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createOrUpdateProject(@PathParam("projectName") String projectName, RepositoryConfiguration configuration) {
		configuration.setProjectName(projectName);
		if (!configuration.isValid()) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
		try {
			controller.addOrUpdateRepository(configuration);
		} catch (Exception e) {
			return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
		}
		return Response.status(Response.Status.OK).build();
	}
}
