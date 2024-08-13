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
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import org.eclipse.emf.common.command.Command;
import de.dlr.sc.virsat.model.concept.types.factory.BeanStructuralElementInstanceFactory;
import de.dlr.sc.virsat.model.concept.types.structural.ABeanStructuralElementInstance;
import de.dlr.sc.virsat.model.concept.types.structural.BeanStructuralElementInstance;
import de.dlr.sc.virsat.model.concept.types.structural.IBeanStructuralElementInstance;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import de.dlr.sc.virsat.project.structure.command.CreateRemoveSeiWithFileStructureCommand;
import de.dlr.sc.virsat.project.structure.command.RemoveFileStructureCommand;
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

@Tag(name = RepositoryAccessResource.TAG_SEI)
@SecurityScheme(
		  type = SecuritySchemeType.HTTP,
		  name = "basicAuth",
		  scheme = "basic")
@SecurityRequirement(name = "basicAuth")
public class StructuralElementInstanceResource {
	
	private ModelAccessResource parentResource;
	
	public StructuralElementInstanceResource(ModelAccessResource parentResource) {
		this.parentResource = parentResource;
	}

	/** **/
	@GET
	@Path("/{seiUuid}")
	@Produces(MediaType.APPLICATION_JSON)
	@Operation(
			summary = "Fetch SEI",
			method = "GET",
			description = "This service fetches a Structural Element Instance.",
			responses = {
				@ApiResponse(
					responseCode = ApiErrorHelper.SUCCESSFUL_OPERATION_CODE,
					description = ApiErrorHelper.SUCCESSFUL_OPERATION,
					content = @Content(
							mediaType = MediaType.APPLICATION_JSON,
							schema = @Schema(implementation = ABeanStructuralElementInstance.class))
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

	public Response getSei(@PathParam("seiUuid") @Parameter(description = "Uuid of the SEI", required = true) String seiUuid) {
		try {
			parentResource.synchronize();
			
			StructuralElementInstance sei = RepositoryUtility.findSei(seiUuid, parentResource.getRepository());
			
			if (sei == null) {
				return ApiErrorHelper.createNotFoundErrorResponse();
			}
			
			IBeanStructuralElementInstance beanSei = new BeanStructuralElementInstanceFactory().getInstanceFor(sei);
			return Response.ok(beanSei).build();
		} catch (Exception e) {
			return ApiErrorHelper.createInternalErrorResponse(e.getMessage());
		}
	}

	/** **/
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Operation(
			summary = "Put SEI",
			method = "PUT",
			description = "This service updates an existing Structural Element Instance.",
			responses = {
				@ApiResponse(
					responseCode = ApiErrorHelper.SUCCESSFUL_OPERATION_CODE,
					description = ApiErrorHelper.SUCCESSFUL_OPERATION
						),
				@ApiResponse(
						responseCode = ApiErrorHelper.INTERNAL_SERVER_ERROR_CODE,
						description = ApiErrorHelper.INTERNAL_SERVER_ERROR
						)
			})
	public Response putSei(@Parameter(description = "SEI to put", required = true) ABeanStructuralElementInstance bean) {
		try {
			parentResource.synchronize();
			return Response.status(Response.Status.OK).build();
		} catch (Exception e) {
			return ApiErrorHelper.createInternalErrorResponse(e.getMessage());
		}
	}
	
	/** **/
	@POST
	@Path("/{seiUuid}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Operation(
			summary = "Create SEI",
			method = "POST",
			description = "This service creates a new Structural Element Instance and returns it.",
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
	public Response createSei(@PathParam("seiUuid") @Parameter(description = "parent uuid", required = true) String parentUuid,
			@QueryParam(value = RepositoryAccessResource.QP_FULL_QUALIFIED_NAME)
			@Parameter(description = "Full qualified name of the SEI type", required = true) String fullQualifiedName) {
		try {
			parentResource.synchronize();
			
			StructuralElementInstance parentSei = RepositoryUtility.findSei(parentUuid, parentResource.getRepository());
			
			if (parentSei == null) {
				return ApiErrorHelper.createNotFoundErrorResponse();
			}
			BeanStructuralElementInstance newSeiBean = ModelAccessResource.createSeiFromFqn(fullQualifiedName, parentSei, parentResource.getEd(), parentResource.getUser());
			
			parentResource.synchronize();
			return Response.ok(newSeiBean).build();
		} catch (IllegalArgumentException e) {
			return Response.status(Response.Status.FORBIDDEN).entity("Forbidden").build();
		} catch (Exception e) {
			return ApiErrorHelper.createInternalErrorResponse(e.getMessage());
		}
	}
	
	/** **/
	@DELETE
	@Path("/{seiUuid}")
	@Produces(MediaType.APPLICATION_JSON)
	@Operation(
			summary = "Delete SEI",
			method = "DELETE",
			description = "This service deletes a Structural Element Instance.",
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
	public Response deleteSei(@PathParam("seiUuid") @Parameter(description = "Uuid of the SEI", required = true)  String seiUuid) {
		try {
			// Sync before delete
			parentResource.synchronize();
			
			// Delete Sei
			StructuralElementInstance sei = RepositoryUtility.findSei(seiUuid, parentResource.getRepository());
			if (sei == null) {
				return ApiErrorHelper.createNotFoundErrorResponse();
			}
			
			Command deleteCommand = CreateRemoveSeiWithFileStructureCommand.create(sei, RemoveFileStructureCommand.DELETE_RESOURCE_OPERATION_FUNCTION);
			ApiErrorHelper.executeCommandIffCanExecute(deleteCommand, parentResource.getEd(), parentResource.getUser());
			
			// Sync after delete
			parentResource.synchronize();
			return Response.ok().build();
		} catch (IllegalArgumentException e) {
			return Response.status(Response.Status.FORBIDDEN).entity("Forbidden").build();
		} catch (Exception e) {
			return ApiErrorHelper.createInternalErrorResponse(e.getMessage());
		}
	}
	
}
