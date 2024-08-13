/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.server.resources.project;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jakarta.annotation.security.RolesAllowed;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.GenericEntity;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import de.dlr.sc.virsat.server.auth.ServerRoles;
import de.dlr.sc.virsat.server.configuration.RepositoryConfiguration;
import de.dlr.sc.virsat.server.controller.RepoManagementController;
import de.dlr.sc.virsat.server.jetty.VirSatJettyServer;
import de.dlr.sc.virsat.server.repository.ServerRepository;
import de.dlr.sc.virsat.server.resources.ApiErrorHelper;
import de.dlr.sc.virsat.server.servlet.RepoManagementServletContainer;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;

@SecurityScheme(
		  type = SecuritySchemeType.HTTP,
		  name = "basicAuth",
		  scheme = "basic")
@SecurityRequirement(name = "basicAuth")
@OpenAPIDefinition(
	info = @Info(
		version = RepoManagementServletContainer.MANAGEMENT_API_VERSION,
		title = "The Server Management API",
		description = "API to manage the server configuration and setting up projects"
	),
	servers = {@Server(url = VirSatJettyServer.PATH + RepoManagementServletContainer.MANAGEMENT_API)}
)
@Path(ProjectManagementResource.PATH)
@RolesAllowed({ServerRoles.ADMIN})
public class ProjectManagementResource {

	public static final String PATH = "/project";

	private RepoManagementController controller;

	public ProjectManagementResource() {
		controller = new RepoManagementController();
	}
	
	private static final String PROJECT_NAME = "Name of the project";

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Operation(
			summary = "Fetch a list of Project names",
			method = "GET",
			description = "This service fetches all Project names",
			responses = {
				@ApiResponse(
					responseCode = ApiErrorHelper.SUCCESSFUL_OPERATION_CODE,
					description = ApiErrorHelper.SUCCESSFUL_OPERATION,
					content = @Content(
							mediaType = MediaType.APPLICATION_JSON,
							array = @ArraySchema(schema = @Schema(implementation = String.class)))
					)
			})
	public Response getAllProjects() {
		List<String> projects = new ArrayList<>(controller.getAllProjectNames());
		GenericEntity<List<String>> entity = new GenericEntity<List<String>>(projects) { };
		return Response.status(Response.Status.OK).entity(entity).build();
	}

	/**
	 * Method to get the project by name
	 * @param projectName to get
	 * @return the response to the call
	 */
	@GET
	@Path("/{projectName}")
	@Produces(MediaType.APPLICATION_JSON)
	@Operation(
			summary = "Fetch Project",
			method = "GET",
			description = "This service fetches the configuration for the given project name",
			responses = {
				@ApiResponse(
					responseCode = ApiErrorHelper.SUCCESSFUL_OPERATION_CODE,
					description = ApiErrorHelper.SUCCESSFUL_OPERATION,
					content = @Content(
						mediaType = MediaType.APPLICATION_JSON,
						schema = @Schema(implementation = RepositoryConfiguration.class))
						),
				@ApiResponse(
						responseCode = "400",
						description = "Project not found."
						)
			})
	public Response getProject(@PathParam("projectName") @Parameter(description = PROJECT_NAME, required = true) String projectName) {
		ServerRepository serverRepository = controller.getRepository(projectName);
		if (serverRepository != null) {
			RepositoryConfiguration configuration = serverRepository.getRepositoryConfiguration();
			return Response.status(Response.Status.OK).entity(configuration).build();
		} else {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
	}

	/**
	 * deletes a project repository by name
	 * @param repoName the name of the repository
	 * @return the response to the call
	 */
	@DELETE
	@Path("/{projectName}")
	@Operation(
			summary = "Delete Project",
			method = "DELETE",
			description = "This service deletes the configuration for the given project name.",
			responses = {
				@ApiResponse(
					responseCode = ApiErrorHelper.SUCCESSFUL_OPERATION_CODE,
					description = ApiErrorHelper.SUCCESSFUL_OPERATION
						),
				@ApiResponse(
						responseCode = "400",
						description = "Project could not be deleted."
						)
			})
	public Response deleteProject(@PathParam("projectName") @Parameter(description = PROJECT_NAME, required = true) String repoName) {
		try {
			controller.deleteRepository(repoName);
		} catch (IOException e) {
			return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
		}
		return Response.status(Response.Status.OK).build();
	}

	/**
	 * Creates or updates a project
	 * @param projectName the name of the project
	 * @param configuration the configuration for updating
	 * @return the response to the call
	 */
	@PUT
	@Path("/{projectName}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Operation(
			summary = "Put Project",
			method = "PUT",
			description = "This service creates or updates a project configuration on the project specified by the URL."
					+ " URL project overrides project name in the passed configuration if they are different.",
			responses = {
				@ApiResponse(
					responseCode = ApiErrorHelper.SUCCESSFUL_OPERATION_CODE,
					description = ApiErrorHelper.SUCCESSFUL_OPERATION
						),
				@ApiResponse(
					responseCode = "400",
					description = "An error occured, returns error message.",
					content = @Content(
							mediaType = MediaType.APPLICATION_JSON,
							schema = @Schema(implementation = String.class))
						)
			})
	public Response createOrUpdateProject(@PathParam("projectName") @Parameter(description = PROJECT_NAME, required = true) String projectName,
			@Parameter(description = "New Configuration", required = true) RepositoryConfiguration configuration) {
		configuration.setProjectName(projectName);
		if (!configuration.isValid()) {
			return Response.status(Response.Status.BAD_REQUEST).entity("Invalid configuration").build();
		}
		try {
			controller.addOrUpdateRepository(configuration);
		} catch (Exception e) {
			return Response.status(Response.Status.BAD_REQUEST).entity(e.toString()).build();
		}
		return Response.status(Response.Status.OK).build();
	}
}
