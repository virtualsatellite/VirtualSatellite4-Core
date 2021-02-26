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
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.command.Command;
import org.eclipse.jetty.http.HttpStatus;

import de.dlr.sc.virsat.model.concept.types.category.ABeanCategoryAssignment;
import de.dlr.sc.virsat.model.concept.types.factory.BeanCategoryAssignmentFactory;
import de.dlr.sc.virsat.model.dvlm.Repository;
import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;
import de.dlr.sc.virsat.project.editingDomain.VirSatTransactionalEditingDomain;
import de.dlr.sc.virsat.server.dataaccess.RepositoryUtility;
import de.dlr.sc.virsat.server.repository.ServerRepository;
import de.dlr.sc.virsat.server.resources.ApiErrorHelper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(hidden = true)
public class CategoryAssignmentResource {
	
	public static final String COULD_NOT_FIND_REQUESTED_CA = "Could not find requested CA";

	private Repository repository;
	private ServerRepository serverRepository;
	private VirSatTransactionalEditingDomain ed;
	
	public CategoryAssignmentResource(ServerRepository serverRepository) {
		this.serverRepository = serverRepository;
		ed = serverRepository.getEd();
		repository = serverRepository.getResourceSet().getRepository();
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
					message = COULD_NOT_FIND_REQUESTED_CA),
			@ApiResponse(
					code = HttpStatus.INTERNAL_SERVER_ERROR_500, 
					message = ApiErrorHelper.SYNC_ERROR)})
	public Response getCa(@PathParam("caUuid") @ApiParam(value = "Uuid of the CA", required = true) String caUuid) {
		try {
			serverRepository.syncRepository();
			return Response.status(Response.Status.OK).entity(
					new BeanCategoryAssignmentFactory().getInstanceFor(
							RepositoryUtility.findCa(caUuid, repository)
					)).build();
		} catch (CoreException e) {
			return ApiErrorHelper.createBadRequestResponse(e.getMessage());
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
			serverRepository.syncRepository();
			return Response.status(Response.Status.OK).build();
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
					message = COULD_NOT_FIND_REQUESTED_CA),
			@ApiResponse(
					code = HttpStatus.INTERNAL_SERVER_ERROR_500, 
					message = ApiErrorHelper.SYNC_ERROR)})
	public Response deleteCa(@PathParam("caUuid") @ApiParam(value = "Uuid of the CA", required = true)  String caUuid) {
		try {
			// Sync before delete
			serverRepository.syncRepository();
			
			// Delete CA
			CategoryAssignment ca = RepositoryUtility.findCa(caUuid, repository);
			Command deleteCommand = new BeanCategoryAssignmentFactory().getInstanceFor(ca).delete(ed);
			ed.getCommandStack().execute(deleteCommand);
			
			// Sync after delete
			serverRepository.syncRepository();
			return Response.ok().build();
		} catch (CoreException e) {
			return ApiErrorHelper.createBadRequestResponse(e.getMessage());
		} catch (Exception e) {
			return ApiErrorHelper.createSyncErrorResponse(e.getMessage());
		}
	}
	
}
