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

import javax.annotation.security.RolesAllowed;
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

import org.eclipse.jetty.http.HttpStatus;

import de.dlr.sc.virsat.server.auth.ServerRoles;
import de.dlr.sc.virsat.server.configuration.RepositoryConfiguration;
import de.dlr.sc.virsat.server.controller.RepoManagementController;
import de.dlr.sc.virsat.server.jetty.VirSatJettyServer;
import de.dlr.sc.virsat.server.repository.ServerRepository;
import de.dlr.sc.virsat.server.servlet.RepoManagementServlet;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Authorization;
import io.swagger.annotations.Info;
import io.swagger.annotations.SwaggerDefinition;

@Api(authorizations = {
		@Authorization(value = "basic")
})
@SwaggerDefinition(
	info = @Info(
		version = RepoManagementServlet.MANAGEMENT_API_VERSION,
		title = "The Management API",
		description = "API to manage the Server Configuration"
	),
	basePath = VirSatJettyServer.PATH + RepoManagementServlet.MANAGEMENT_API
)
@Path(ProjectManagementResource.PATH)
@RolesAllowed(ServerRoles.ADMIN)
public class ProjectManagementResource {

	private static final String SUCCESSFUL_OPERATION = "Successful operation";

	public static final String PATH = "/project";

	private RepoManagementController controller;

	public ProjectManagementResource() {
		controller = new RepoManagementController();
	}
	
	private static final String PROJECT_NAME = "Name of the project";

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(
			produces = "application/json",
			value = "Fetch a list of Project names",
			httpMethod = "GET",
			notes = "This service fetches all Project names")
	@ApiResponse(
			code = HttpStatus.OK_200,
			response = String.class,
			responseContainer = "List",
			message = SUCCESSFUL_OPERATION)
	public Response getAllProjects() {
		List<String> projects = new ArrayList<>(controller.getAllProjectNames());
		GenericEntity<List<String>> entity = new GenericEntity<List<String>>(projects) { };
		return Response.status(Response.Status.OK).entity(entity).build();
	}

	/** **/
	@GET
	@Path("/{projectName}")
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(
			produces = "application/json",
			value = "Fetch Project",
			httpMethod = "GET",
			notes = "This service fetches the configuration for the given project name")
	@ApiResponses(value = { 
			@ApiResponse(
					code = HttpStatus.OK_200,
					response = RepositoryConfiguration.class,
					message = SUCCESSFUL_OPERATION),
			@ApiResponse(
					code = HttpStatus.NOT_FOUND_404,
					message = "Project not found")})
	public Response getProject(@PathParam("projectName") @ApiParam(value = PROJECT_NAME, required = true) String projectName) {
		ServerRepository serverRepository = controller.getRepository(projectName);
		if (serverRepository != null) {
			RepositoryConfiguration configuration = serverRepository.getRepositoryConfiguration();
			return Response.status(Response.Status.OK).entity(configuration).build();
		} else {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
	}

	/** **/
	@DELETE
	@Path("/{projectName}")
	@ApiOperation(
			produces = "application/json",
			value = "Delete Project",
			httpMethod = "DELETE",
			notes = "This service deletes the configuration for the given project name")
	@ApiResponses(value = { 
			@ApiResponse(
					code = HttpStatus.OK_200,
					message = SUCCESSFUL_OPERATION),
			@ApiResponse(
					code = HttpStatus.BAD_REQUEST_400,
					message = "Project could not be deleted")})
	public Response deleteProject(@PathParam("projectName") @ApiParam(value = PROJECT_NAME, required = true) String repoName) {
		try {
			controller.deleteRepository(repoName);
		} catch (IOException e) {
			return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
		}
		return Response.status(Response.Status.OK).build();
	}

	/** **/
	@PUT
	@Path("/{projectName}")
	@Consumes(MediaType.APPLICATION_JSON)
	@ApiOperation(
			produces = "application/json",
			value = "PUT Project",
			httpMethod = "PUT",
			notes = "This service creates or updates a project configuration on the project specified by the URL."
					+ " URL project overrides project name in the passed configuration if they are different.")
	@ApiResponses(value = { 
			@ApiResponse(
					code = HttpStatus.OK_200,
					message = SUCCESSFUL_OPERATION),
			@ApiResponse(
					code = HttpStatus.BAD_REQUEST_400,
					message = "An error occured, returns error message",
					response = String.class)})
	public Response createOrUpdateProject(@PathParam("projectName") @ApiParam(value = PROJECT_NAME, required = true) String projectName,
			@ApiParam(value = "New Configuration", required = true) RepositoryConfiguration configuration) {
		configuration.setProjectName(projectName);
		if (!configuration.isValid()) {
			return Response.status(Response.Status.BAD_REQUEST).entity("Invalid configuration").build();
		}
		try {
			controller.addOrUpdateRepository(configuration);
		} catch (Exception e) {
			return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
		}
		return Response.status(Response.Status.OK).build();
	}
}
