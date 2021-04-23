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
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.eclipse.jetty.http.HttpStatus;

import de.dlr.sc.virsat.model.concept.types.IBeanObject;
import de.dlr.sc.virsat.model.concept.types.factory.BeanPropertyFactory;
import de.dlr.sc.virsat.model.concept.types.property.ABeanProperty;
import de.dlr.sc.virsat.model.dvlm.Repository;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.APropertyInstance;
import de.dlr.sc.virsat.server.dataaccess.RepositoryUtility;
import de.dlr.sc.virsat.server.repository.ServerRepository;
import de.dlr.sc.virsat.server.resources.ApiErrorHelper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(hidden = true)
public class PropertyResource {
	
	private Repository repository;
	private ServerRepository serverRepository;
	
	public PropertyResource(ServerRepository serverRepository) {
		this.serverRepository = serverRepository;
		repository = serverRepository.getResourceSet().getRepository();
	}
	
	/** **/
	@GET
	@Path("/{propertyUuid}")
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(
			produces = "application/json",
			value = "Fetch Property",
			httpMethod = "GET",
			notes = "This service fetches a Property")
	@ApiResponses(value = { 
			@ApiResponse(
					code = HttpStatus.OK_200,
					response = ABeanProperty.class,
					message = ApiErrorHelper.SUCCESSFUL_OPERATION),
			@ApiResponse(
					code = HttpStatus.BAD_REQUEST_400, 
					message = ApiErrorHelper.COULD_NOT_FIND_REQUESTED_ELEMENT),
			@ApiResponse(
					code = HttpStatus.INTERNAL_SERVER_ERROR_500, 
					message = ApiErrorHelper.SYNC_ERROR)})
	public Response getProperty(@PathParam("propertyUuid") @ApiParam(value = "Uuid of the property", required = true) String propertyUuid) {
		try {
			serverRepository.syncRepository();
			
			APropertyInstance property = RepositoryUtility.findProperty(propertyUuid, repository);
			
			if (property == null) {
				return ApiErrorHelper.createNotFoundErrorResponse();
			}
			
			IBeanObject<? extends APropertyInstance> beanProperty = new BeanPropertyFactory().getInstanceFor(property);
			return Response.status(Response.Status.OK).entity(beanProperty).build();
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
			value = "Put Property",
			httpMethod = "PUT",
			notes = "This service updates an existing Property")
	@ApiResponses(value = { 
			@ApiResponse(
					code = HttpStatus.OK_200,
					message = ApiErrorHelper.SUCCESSFUL_OPERATION),
			@ApiResponse(
					code = HttpStatus.INTERNAL_SERVER_ERROR_500, 
					message = ApiErrorHelper.SYNC_ERROR)})
	public Response putProperty(@SuppressWarnings("rawtypes") @ApiParam(value = "Property to put", required = true) ABeanProperty bean) {
		try {
			serverRepository.syncRepository();
			return Response.status(Response.Status.OK).build();
		} catch (Exception e) {
			return ApiErrorHelper.createSyncErrorResponse(e.getMessage());
		}
	}

}
