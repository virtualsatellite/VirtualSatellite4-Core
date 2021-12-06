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
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.eclipse.core.resources.IFile;
import org.eclipse.jetty.http.HttpStatus;

import de.dlr.sc.virsat.model.concept.types.IBeanObject;
import de.dlr.sc.virsat.model.concept.types.factory.BeanPropertyFactory;
import de.dlr.sc.virsat.model.concept.types.property.ABeanProperty;
import de.dlr.sc.virsat.model.concept.types.property.BeanPropertyResource;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.APropertyInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ResourcePropertyInstance;
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
import io.swagger.annotations.ResponseHeader;

@Api(hidden = true, authorizations = {@Authorization(value = "basic")}, tags = {ModelAccessResource.TAG_PROPERTY})
public class PropertyResource {
	
	public static final String PROPERTY_DOES_NOT_CONTAIN_A_SERVEABLE_RESOURCE = "Property does not contain a serveable resource";
	public static final String RESOURCE = "resource";

	private RepoModelAccessResource parentResource;
	
	public PropertyResource(RepoModelAccessResource parentResource) {
		this.parentResource = parentResource;
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
					message = ApiErrorHelper.INTERNAL_SERVER_ERROR)})
	public Response getProperty(@PathParam("propertyUuid") @ApiParam(value = "Uuid of the property", required = true) String propertyUuid) {
		try {
			parentResource.synchronize();
			
			APropertyInstance property = RepositoryUtility.findProperty(propertyUuid, parentResource.getRepository());
			
			if (property == null) {
				return ApiErrorHelper.createNotFoundErrorResponse();
			}
			
			IBeanObject<? extends APropertyInstance> beanProperty = new BeanPropertyFactory().getInstanceFor(property);
			return Response.status(Response.Status.OK).entity(beanProperty).build();
		} catch (Exception e) {
			return ApiErrorHelper.createInternalErrorResponse(e.getMessage());
		}
	}

	/** **/
	@GET
	@Path("/{propertyUuid}/" + RESOURCE)
	@Produces(MediaType.APPLICATION_OCTET_STREAM)
	@ApiOperation(
			produces = "application/octet-stream",
			value = "Fetch property resource",
			httpMethod = "GET",
			notes = "This service fetches the resource of a property")
	@ApiResponses(value = { 
			@ApiResponse(
					code = HttpStatus.OK_200,
					responseHeaders = {
							@ResponseHeader(
									name = HttpHeaders.CONTENT_DISPOSITION,
									description = "attachment; filename=\"{name}\"",
									response = String.class)
					},
					message = ApiErrorHelper.SUCCESSFUL_OPERATION),
			@ApiResponse(
					code = HttpStatus.BAD_REQUEST_400, 
					message = ApiErrorHelper.COULD_NOT_FIND_REQUESTED_ELEMENT),
			@ApiResponse(
					code = HttpStatus.BAD_REQUEST_400, 
					message = PROPERTY_DOES_NOT_CONTAIN_A_SERVEABLE_RESOURCE),
			@ApiResponse(
					code = HttpStatus.INTERNAL_SERVER_ERROR_500, 
					message = ApiErrorHelper.INTERNAL_SERVER_ERROR)})
	public Response getResource(@PathParam("propertyUuid") @ApiParam(value = "Uuid of the property", required = true) String propertyUuid) {
		try {
			parentResource.synchronize();
		
			APropertyInstance property = RepositoryUtility.findProperty(propertyUuid, parentResource.getRepository());
			
			if (property == null) {
				return ApiErrorHelper.createNotFoundErrorResponse();
			}
			
			IFile file = null;
			
			if (property instanceof ResourcePropertyInstance) {
				BeanPropertyResource beanPropertyResource = new BeanPropertyResource((ResourcePropertyInstance) property);
				file = beanPropertyResource.getFile();
			}
			
			if (file != null) {
				return Response.ok(file.getContents(), MediaType.APPLICATION_OCTET_STREAM)
						.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getName() + "\"")
						.build();
			} else {
				return ApiErrorHelper.createBadRequestResponse(PROPERTY_DOES_NOT_CONTAIN_A_SERVEABLE_RESOURCE);
			}
		} catch (Exception e) {
			return ApiErrorHelper.createInternalErrorResponse(e.getMessage());
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
					message = ApiErrorHelper.INTERNAL_SERVER_ERROR)})
	public Response putProperty(@SuppressWarnings("rawtypes") @ApiParam(value = "Property to put", required = true) ABeanProperty bean) {
		try {
			parentResource.synchronize();
			return Response.status(Response.Status.OK).build();
		} catch (Exception e) {
			return ApiErrorHelper.createInternalErrorResponse(e.getMessage());
		}
	}

}
