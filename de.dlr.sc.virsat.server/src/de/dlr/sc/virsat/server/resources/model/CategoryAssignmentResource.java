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
import org.eclipse.emf.edit.command.AddCommand;
import de.dlr.sc.virsat.model.concept.types.category.ABeanCategoryAssignment;
import de.dlr.sc.virsat.model.concept.types.category.IBeanCategoryAssignment;
import de.dlr.sc.virsat.model.concept.types.factory.BeanCategoryAssignmentFactory;
import de.dlr.sc.virsat.model.dvlm.categories.CategoriesPackage;
import de.dlr.sc.virsat.model.dvlm.categories.Category;
import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;
import de.dlr.sc.virsat.model.dvlm.categories.util.CategoryInstantiator;
import de.dlr.sc.virsat.model.dvlm.concepts.util.ActiveConceptHelper;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
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


@Tag(name = RepositoryAccessResource.TAG_CA)
@SecurityScheme(
		  type = SecuritySchemeType.HTTP,
		  name = "basicAuth",
		  scheme = "basic")
@SecurityRequirement(name = "basicAuth")
public class CategoryAssignmentResource {
	
	private ModelAccessResource parentResource;
	
	public CategoryAssignmentResource(ModelAccessResource parentResource) {
		this.parentResource = parentResource;
	}
	
	/** **/
	@GET
	@Path("/{caUuid}")
	@Produces(MediaType.APPLICATION_JSON)
	@Operation(
			summary = "Fetch CA",
			method = "GET",
			description = "This service fetches a CategoryAssignment.",
			responses = {
				@ApiResponse(
					responseCode = ApiErrorHelper.SUCCESSFUL_OPERATION_CODE,
					description = ApiErrorHelper.SUCCESSFUL_OPERATION,
					content = @Content(
							mediaType = MediaType.APPLICATION_JSON,
							schema = @Schema(implementation = String.class))
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
	public Response getCa(@PathParam("caUuid") @Parameter(description = "Uuid of the CA", required = true) String caUuid) {
		try {
			parentResource.synchronize();
			
			CategoryAssignment ca = RepositoryUtility.findCa(caUuid, parentResource.getRepository());
			
			if (ca == null) {
				return ApiErrorHelper.createNotFoundErrorResponse();
			}
			
			IBeanCategoryAssignment beanCa = new BeanCategoryAssignmentFactory().getInstanceFor(ca);
			return Response.status(Response.Status.OK).entity(beanCa).build();
		} catch (Exception e) {
			return ApiErrorHelper.createInternalErrorResponse(e.getMessage());
		}
	}

	/** **/
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Operation(
			summary = "Put CA",
			method = "PUT",
			description = "This service updates an existing CategoryAssignment.",
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
	public Response putCa(@Parameter(description = "CA to put", required = true) ABeanCategoryAssignment bean) {
		try {
			parentResource.synchronize();
			return Response.status(Response.Status.OK).build();
		} catch (Exception e) {
			return ApiErrorHelper.createInternalErrorResponse(e.getMessage());
		}
	}
	
	/** **/
	@POST
	@Path("/{caUuid}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Operation(
			summary = "Create CA",
			method = "POST",
			description = "This service creates a new CategoryAssignment and returns it.",
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
	public Response createCa(@PathParam("caUuid") @Parameter(description = "parent uuid", required = true) String parentUuid,
			@QueryParam(value = RepositoryAccessResource.QP_FULL_QUALIFIED_NAME)
			@Parameter(description = "Full qualified name of the CA type", required = true) String fullQualifiedName) {
		try {
			parentResource.synchronize();
			
			StructuralElementInstance parentSei = RepositoryUtility.findSei(parentUuid, parentResource.getRepository());
			
			if (parentSei == null) {
				return ApiErrorHelper.createNotFoundErrorResponse();
			}
			
			ActiveConceptHelper helper = new ActiveConceptHelper(parentResource.getEd().getResourceSet().getRepository());
			Category category = helper.getCategory(fullQualifiedName);
			CategoryAssignment newCa = new CategoryInstantiator().generateInstance(category, null);
			
			Command createCommand = AddCommand.create(parentResource.getEd(), parentSei, CategoriesPackage.Literals.ICATEGORY_ASSIGNMENT_CONTAINER__CATEGORY_ASSIGNMENTS, newCa);
			ApiErrorHelper.executeCommandIffCanExecute(createCommand, parentResource.getEd(), parentResource.getUser());
			
			parentResource.synchronize();
			return Response.ok(newCa.getUuid().toString()).build();
		} catch (Exception e) {
			return ApiErrorHelper.createInternalErrorResponse(e.getMessage());
		}
	}
	
	/** **/
	@DELETE
	@Path("/{caUuid}")
	@Produces(MediaType.APPLICATION_JSON)
	@Operation(
			summary = "Delete CA",
			method = "DELETE",
			description = "This service deletes a CategoryAssignment.",
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
	public Response deleteCa(@PathParam("caUuid") @Parameter(description = "Uuid of the CA", required = true)  String caUuid) {
		try {
			// Sync before delete
			parentResource.synchronize();
			
			// Delete CA
			CategoryAssignment ca = RepositoryUtility.findCa(caUuid, parentResource.getRepository());
			if (ca == null) {
				return ApiErrorHelper.createNotFoundErrorResponse();
			}
			
			Command deleteCommand = new BeanCategoryAssignmentFactory().getInstanceFor(ca).delete(parentResource.getEd());
			ApiErrorHelper.executeCommandIffCanExecute(deleteCommand, parentResource.getEd(), parentResource.getUser());
			
			// Sync after delete
			parentResource.synchronize();
			return Response.ok().build();
		} catch (Exception e) {
			return ApiErrorHelper.createInternalErrorResponse(e.getMessage());
		}
	}
	
}
