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
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.eclipse.emf.common.command.Command;
import org.eclipse.jetty.http.HttpStatus;

import de.dlr.sc.virsat.model.concept.types.factory.BeanStructuralElementInstanceFactory;
import de.dlr.sc.virsat.model.concept.types.structural.ABeanStructuralElementInstance;
import de.dlr.sc.virsat.model.concept.types.structural.IBeanStructuralElementInstance;
import de.dlr.sc.virsat.model.dvlm.Repository;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import de.dlr.sc.virsat.project.editingDomain.VirSatTransactionalEditingDomain;
import de.dlr.sc.virsat.project.structure.command.CreateRemoveSeiWithFileStructureCommand;
import de.dlr.sc.virsat.project.structure.command.RemoveFileStructureCommand;
import de.dlr.sc.virsat.server.dataaccess.RepositoryUtility;
import de.dlr.sc.virsat.server.repository.ServerRepository;
import de.dlr.sc.virsat.server.resources.ApiErrorHelper;
import de.dlr.sc.virsat.server.resources.ModelAccessResource;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(hidden = true)
public class StructuralElementInstanceResource {
	
	public static final String COULD_NOT_FIND_REQUESTED_SEI = "Could not find requested SEI";
	
	private Repository repository;
	private ServerRepository serverRepository;
	private VirSatTransactionalEditingDomain ed;
	
	public StructuralElementInstanceResource(ServerRepository serverRepository) {
		this.serverRepository = serverRepository;
		ed = serverRepository.getEd();
		repository = serverRepository.getResourceSet().getRepository();
	}

	/** **/
	@GET
	@Path("/{seiUuid}")
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(
			produces = "application/json",
			value = "Fetch SEI",
			httpMethod = "GET",
			notes = "This service fetches a StructuralElementInstance.")
	@ApiResponses(value = { 
			@ApiResponse(
					code = HttpStatus.OK_200,
					response = ABeanStructuralElementInstance.class,
					message = ApiErrorHelper.SUCCESSFUL_OPERATION),
			@ApiResponse(
					code = HttpStatus.BAD_REQUEST_400, 
					message = COULD_NOT_FIND_REQUESTED_SEI),
			@ApiResponse(
					code = HttpStatus.INTERNAL_SERVER_ERROR_500, 
					message = ApiErrorHelper.SYNC_ERROR)})
	public Response getSei(@PathParam("seiUuid") @ApiParam(value = "Uuid of the SEI", required = true) String seiUuid,
			@DefaultValue("true") @QueryParam(ModelAccessResource.QP_SYNC) boolean synchronize) {
		try {
			if (synchronize) {
				serverRepository.syncRepository();
			}
			
			StructuralElementInstance sei = RepositoryUtility.findSei(seiUuid, repository);
			
			if (sei == null) {
				return ApiErrorHelper.createBadRequestResponse(COULD_NOT_FIND_REQUESTED_SEI);
			}
			
			IBeanStructuralElementInstance beanSei = new BeanStructuralElementInstanceFactory().getInstanceFor(sei);
			return Response.ok(beanSei).build();
		} catch (Exception e) {
			return ApiErrorHelper.createSyncErrorResponse(e.getMessage());
		}
	}

	/** **/
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@ApiOperation(
			consumes = "application/json",
			value = "Put SEI",
			httpMethod = "PUT",
			notes = "This service updates an existing StructuralElementInstance")
	@ApiResponses(value = { 
			@ApiResponse(
					code = HttpStatus.OK_200,
					message = ApiErrorHelper.SUCCESSFUL_OPERATION),
			@ApiResponse(
					code = HttpStatus.INTERNAL_SERVER_ERROR_500, 
					message = ApiErrorHelper.SYNC_ERROR)})
	public Response putSei(@ApiParam(value = "SEI to put", required = true) ABeanStructuralElementInstance bean) {
		try {
			serverRepository.syncRepository();
			return Response.status(Response.Status.OK).build();
		} catch (Exception e) {
			return ApiErrorHelper.createSyncErrorResponse(e.getMessage());
		}
	}
		
	/** **/
	@DELETE
	@Path("/{seiUuid}")
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(
			produces = "application/json",
			value = "Delete SEI",
			httpMethod = "DELETE",
			notes = "This service deletes a StructuralElementInstance.")
	@ApiResponses(value = { 
			@ApiResponse(
					code = HttpStatus.OK_200,
					message = ApiErrorHelper.SUCCESSFUL_OPERATION),
			@ApiResponse(
					code = HttpStatus.BAD_REQUEST_400,
					message = COULD_NOT_FIND_REQUESTED_SEI),
			@ApiResponse(
					code = HttpStatus.INTERNAL_SERVER_ERROR_500, 
					message = ApiErrorHelper.SYNC_ERROR)})
	public Response deleteSei(@PathParam("seiUuid") @ApiParam(value = "Uuid of the SEI", required = true)  String seiUuid) {
		try {
			// Sync before delete
			serverRepository.syncRepository();
			
			// Delete Sei
			StructuralElementInstance sei = RepositoryUtility.findSei(seiUuid, repository);
			if (sei == null) {
				return ApiErrorHelper.createBadRequestResponse(COULD_NOT_FIND_REQUESTED_SEI);
			}
			
			Command deleteCommand = CreateRemoveSeiWithFileStructureCommand.create(sei, RemoveFileStructureCommand.DELETE_RESOURCE_OPERATION_FUNCTION);
			ed.getCommandStack().execute(deleteCommand);
			
			// Sync after delete
			serverRepository.syncRepository();
			return Response.ok().build();
		} catch (Exception e) {
			return ApiErrorHelper.createSyncErrorResponse(e.getMessage());
		}
	}
	
}
