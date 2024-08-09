/*******************************************************************************
 * Copyright (c) 2021 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.server.resources.model;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.RemoveCommand;
import de.dlr.sc.virsat.model.concept.types.roles.BeanDiscipline;
import de.dlr.sc.virsat.model.dvlm.roles.Discipline;
import de.dlr.sc.virsat.model.dvlm.roles.RolesFactory;
import de.dlr.sc.virsat.model.dvlm.roles.RolesPackage;
import de.dlr.sc.virsat.server.dataaccess.RepositoryUtility;
import de.dlr.sc.virsat.server.resources.ApiErrorHelper;
import de.dlr.sc.virsat.server.resources.modelaccess.RepositoryAccessResource;
import de.dlr.sc.virsat.server.resources.modelaccess.ModelAccessResource;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = RepositoryAccessResource.TAG_DISCIPLINE)
@SecurityScheme(
		  type = SecuritySchemeType.HTTP,
		  name = "basicAuth",
		  scheme = "basic")
@SecurityRequirement(name = "basicAuth")
public class DisciplineResource {
	
	private ModelAccessResource parentResource;
	
	public DisciplineResource(ModelAccessResource parentResource) {
		this.parentResource = parentResource;
	}
	
	/** **/
	@GET
	@Path("/{disciplineUuid}")
	@Produces(MediaType.APPLICATION_JSON)
	@Operation(
			summary = "Fetch Discipline",
			method = "GET",
			description = "This service fetches a Discipline.",
			responses = {
				@ApiResponse(
					responseCode = ApiErrorHelper.SUCCESSFUL_OPERATION_CODE,
					description = ApiErrorHelper.SUCCESSFUL_OPERATION,
					content = @Content(
							mediaType = MediaType.APPLICATION_JSON,
							schema = @Schema(implementation = BeanDiscipline.class))
						),
				@ApiResponse(
					responseCode = ApiErrorHelper.COULD_NOT_FIND_REQUESTED_ELEMENT_CODE,
					description = ApiErrorHelper.COULD_NOT_FIND_REQUESTED_ELEMENT
					),
				@ApiResponse(
						responseCode = ApiErrorHelper.INTERNAL_SERVER_ERROR_CODE,
						description = ApiErrorHelper.INTERNAL_SERVER_ERROR
						)
			})
	public Response getDiscipline(@PathParam("disciplineUuid") @Parameter(description = "Uuid of the discipline", required = true) String disciplineUuid) {
		try {
			parentResource.synchronize();
			
			Discipline discipline = RepositoryUtility.findDiscipline(disciplineUuid, parentResource.getRepository());
			
			if (discipline == null) {
				return ApiErrorHelper.createNotFoundErrorResponse();
			}
			
			BeanDiscipline beanDiscipline = new BeanDiscipline(discipline);
			return Response.status(Response.Status.OK).entity(beanDiscipline).build();
		} catch (Exception e) {
			return ApiErrorHelper.createInternalErrorResponse(e.getMessage());
		}
	}

	/** **/
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Operation(
			summary = "Put Discipline",
			method = "PUT",
			description = "This service updates an existing Discipline.",
			responses = {
				@ApiResponse(
					responseCode = ApiErrorHelper.SUCCESSFUL_OPERATION_CODE,
					description = ApiErrorHelper.SUCCESSFUL_OPERATION
						),
				@ApiResponse(
						responseCode = ApiErrorHelper.COULD_NOT_FIND_REQUESTED_ELEMENT_CODE,
						description = ApiErrorHelper.COULD_NOT_FIND_REQUESTED_ELEMENT
						),
				@ApiResponse(
						responseCode = ApiErrorHelper.INTERNAL_SERVER_ERROR_CODE,
						description = ApiErrorHelper.INTERNAL_SERVER_ERROR
						)
			})
	public Response putDiscipline(@Parameter(description = "Discipline to put", required = true) BeanDiscipline bean) {
		try {
			parentResource.synchronize();
			return Response.status(Response.Status.OK).build();
		} catch (Exception e) {
			return ApiErrorHelper.createInternalErrorResponse(e.getMessage());
		}
	}
	
	/** **/
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Operation(
			summary = "Create Discipline",
			method = "POST",
			description = "This service creates a new Discipline and returns it.",
			responses = {
				@ApiResponse(
					responseCode = ApiErrorHelper.SUCCESSFUL_OPERATION_CODE,
					description = ApiErrorHelper.SUCCESSFUL_OPERATION,
					content = @Content(
							mediaType = MediaType.APPLICATION_JSON,
							schema = @Schema(implementation = String.class))
						),
				@ApiResponse(
						responseCode = ApiErrorHelper.NOT_EXECUTEABLE_CODE,
						description = ApiErrorHelper.NOT_EXECUTEABLE
						),
				@ApiResponse(
						responseCode = ApiErrorHelper.INTERNAL_SERVER_ERROR_CODE,
						description = ApiErrorHelper.INTERNAL_SERVER_ERROR
						)
			})
	public Response createDiscipline() {
		try {
			parentResource.synchronize();
			
			Discipline newDiscipline = RolesFactory.eINSTANCE.createDiscipline();
			
			Command addCommand = AddCommand.create(parentResource.getEd(), parentResource.getRepository().getRoleManagement(), RolesPackage.ROLE_MANAGEMENT__DISCIPLINES, newDiscipline);
			ApiErrorHelper.executeCommandIffCanExecute(addCommand, parentResource.getEd(), parentResource.getUser());
			
			parentResource.synchronize();
			return Response.ok(newDiscipline.getUuid().toString()).build();
		} catch (Exception e) {
			return ApiErrorHelper.createInternalErrorResponse(e.getMessage());
		}
	}
	
	/** **/
	@DELETE
	@Path("/{disciplineUuid}")
	@Produces(MediaType.APPLICATION_JSON)
	@Operation(
			summary = "Delete Discipline",
			method = "DELETE",
			description = "This service deletes a Discipline.",
			responses = {
				@ApiResponse(
					responseCode = ApiErrorHelper.SUCCESSFUL_OPERATION_CODE,
					description = ApiErrorHelper.SUCCESSFUL_OPERATION
						),
				@ApiResponse(
					responseCode = ApiErrorHelper.COULD_NOT_FIND_REQUESTED_ELEMENT_CODE,
					description = ApiErrorHelper.COULD_NOT_FIND_REQUESTED_ELEMENT
						),
				@ApiResponse(
						responseCode = ApiErrorHelper.NOT_EXECUTEABLE_CODE,
						description = ApiErrorHelper.NOT_EXECUTEABLE
						),
				@ApiResponse(
						responseCode = ApiErrorHelper.INTERNAL_SERVER_ERROR_CODE,
						description = ApiErrorHelper.INTERNAL_SERVER_ERROR
						)
			})
	public Response deleteDiscipline(@PathParam("disciplineUuid") @Parameter(description = "Uuid of the Discipline", required = true) String disciplineUuid) {
		try {
			// Sync before delete
			parentResource.synchronize();
			
			Discipline discipline = RepositoryUtility.findDiscipline(disciplineUuid, parentResource.getRepository());
			if (discipline == null) {
				return ApiErrorHelper.createNotFoundErrorResponse();
			}
			
			// For delete we just remove it from the rolemanagement
			Command removeCommand = RemoveCommand.create(parentResource.getEd(), parentResource.getRepository().getRoleManagement(), RolesPackage.ROLE_MANAGEMENT__DISCIPLINES, discipline);
			ApiErrorHelper.executeCommandIffCanExecute(removeCommand, parentResource.getEd(), parentResource.getUser());
			
			// Sync after delete
			parentResource.synchronize();
			return Response.ok().build();
		} catch (Exception e) {
			return ApiErrorHelper.createInternalErrorResponse(e.getMessage());
		}
	}
}
