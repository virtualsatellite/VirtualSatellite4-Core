/*******************************************************************************
 * Copyright (c) 2021 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.server.resources.modelaccess;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.jetty.http.HttpStatus;

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
import de.dlr.sc.virsat.server.resources.ModelAccessResource;
import de.dlr.sc.virsat.server.resources.ModelAccessResource.RepoModelAccessResource;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Authorization;

@Api(hidden = true, authorizations = {@Authorization(value = "basic")})
// TODO: rolemanagement check
public class CategoryAssignmentResource {
	
	private RepoModelAccessResource parentResource;
	
	public CategoryAssignmentResource(RepoModelAccessResource parentResource) {
		this.parentResource = parentResource;
	}
	
	/** **/
	@GET
	@Path("/{caUuid}")
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(
			produces = "application/json",
			value = "Fetch CA",
			httpMethod = "GET",
			notes = "This service fetches a CategoryAssignment")
	@ApiResponses(value = { 
			@ApiResponse(
					code = HttpStatus.OK_200,
					response = ABeanCategoryAssignment.class,
					message = ApiErrorHelper.SUCCESSFUL_OPERATION),
			@ApiResponse(
					code = HttpStatus.BAD_REQUEST_400, 
					message = ApiErrorHelper.COULD_NOT_FIND_REQUESTED_ELEMENT),
			@ApiResponse(
					code = HttpStatus.INTERNAL_SERVER_ERROR_500, 
					message = ApiErrorHelper.SYNC_ERROR)})
	public Response getCa(@PathParam("caUuid") @ApiParam(value = "Uuid of the CA", required = true) String caUuid) {
		try {
			parentResource.synchronize();
			
			CategoryAssignment ca = RepositoryUtility.findCa(caUuid, parentResource.getRepository());
			
			if (ca == null) {
				return ApiErrorHelper.createNotFoundErrorResponse();
			}
			
			IBeanCategoryAssignment beanCa = new BeanCategoryAssignmentFactory().getInstanceFor(ca);
			return Response.status(Response.Status.OK).entity(beanCa).build();
		} catch (Exception e) {
			return ApiErrorHelper.createSyncErrorResponse(e.getMessage());
		}
	}

	/** **/
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@ApiOperation(
			produces = "application/json",
			value = "Put CA",
			httpMethod = "PUT",
			notes = "This service updates an existing CategoryAssignment")
	@ApiResponses(value = { 
			@ApiResponse(
					code = HttpStatus.OK_200,
					message = ApiErrorHelper.SUCCESSFUL_OPERATION),
			@ApiResponse(
					code = HttpStatus.INTERNAL_SERVER_ERROR_500, 
					message = ApiErrorHelper.SYNC_ERROR)})
	public Response putCa(@ApiParam(value = "CA to put", required = true) ABeanCategoryAssignment bean) {
		try {
			parentResource.synchronize();
			return Response.status(Response.Status.OK).build();
		} catch (Exception e) {
			return ApiErrorHelper.createSyncErrorResponse(e.getMessage());
		}
	}
	
	/** **/
	@POST
	@Path("/{parentUuid}")
	@Consumes(MediaType.APPLICATION_JSON)
	@ApiOperation(
			consumes = "application/json",
			value = "Create CA",
			httpMethod = "POST",
			notes = "This service creates a new CategoryAssignment and returns it")
	@ApiResponses(value = { 
			@ApiResponse(
					code = HttpStatus.OK_200,
					response = String.class,
					message = ApiErrorHelper.SUCCESSFUL_OPERATION),
			@ApiResponse(
					code = HttpStatus.BAD_REQUEST_400, 
					message = ApiErrorHelper.COULD_NOT_FIND_REQUESTED_ELEMENT),
			@ApiResponse(
					code = HttpStatus.INTERNAL_SERVER_ERROR_500, 
					message = ApiErrorHelper.SYNC_ERROR)})
	public Response createCa(@PathParam("parentUuid") @ApiParam(value = "parent uuid", required = true) String parentUuid,
			@QueryParam(value = ModelAccessResource.QP_FULL_QUALIFIED_NAME)
			@ApiParam(value = "Full qualified name of the CA type", required = true) String fullQualifiedName) {
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
			parentResource.getEd().getCommandStack().execute(createCommand);
			
			parentResource.synchronize();
			return Response.ok(newCa.getUuid().toString()).build();
		} catch (Exception e) {
			return ApiErrorHelper.createSyncErrorResponse(e.getMessage());
		}
	}
	
	/** **/
	@DELETE
	@Path("/{caUuid}")
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(
			produces = "application/json",
			value = "Delete CA",
			httpMethod = "DELETE",
			notes = "This service deletes a CategoryAssignment.")
	@ApiResponses(value = { 
			@ApiResponse(
					code = HttpStatus.OK_200,
					message = ApiErrorHelper.SUCCESSFUL_OPERATION),
			@ApiResponse(
					code = HttpStatus.BAD_REQUEST_400,
					message = ApiErrorHelper.COULD_NOT_FIND_REQUESTED_ELEMENT),
			@ApiResponse(
					code = HttpStatus.INTERNAL_SERVER_ERROR_500, 
					message = ApiErrorHelper.SYNC_ERROR)})
	public Response deleteCa(@PathParam("caUuid") @ApiParam(value = "Uuid of the CA", required = true)  String caUuid) {
		try {
			// Sync before delete
			parentResource.synchronize();
			
			// Delete CA
			CategoryAssignment ca = RepositoryUtility.findCa(caUuid, parentResource.getRepository());
			if (ca == null) {
				return ApiErrorHelper.createNotFoundErrorResponse();
			}
			
			Command deleteCommand = new BeanCategoryAssignmentFactory().getInstanceFor(ca).delete(parentResource.getEd());
			parentResource.getEd().getCommandStack().execute(deleteCommand);
			
			// Sync after delete
			parentResource.synchronize();
			return Response.ok().build();
		} catch (Exception e) {
			return ApiErrorHelper.createSyncErrorResponse(e.getMessage());
		}
	}
	
}
