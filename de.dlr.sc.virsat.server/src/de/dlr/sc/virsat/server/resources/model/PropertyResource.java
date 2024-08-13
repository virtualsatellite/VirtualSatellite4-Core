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
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import org.eclipse.core.resources.IFile;
import de.dlr.sc.virsat.model.concept.types.IBeanObject;
import de.dlr.sc.virsat.model.concept.types.factory.BeanPropertyFactory;
import de.dlr.sc.virsat.model.concept.types.property.ABeanProperty;
import de.dlr.sc.virsat.model.concept.types.property.BeanPropertyResource;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.APropertyInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ResourcePropertyInstance;
import de.dlr.sc.virsat.server.dataaccess.RepositoryUtility;
import de.dlr.sc.virsat.server.resources.ApiErrorHelper;
import de.dlr.sc.virsat.server.resources.modelaccess.RepositoryAccessResource;
import de.dlr.sc.virsat.server.resources.modelaccess.ModelAccessResource;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = RepositoryAccessResource.TAG_PROPERTY)
@SecurityScheme(
		  type = SecuritySchemeType.HTTP,
		  name = "basicAuth",
		  scheme = "basic")
@SecurityRequirement(name = "basicAuth")
public class PropertyResource {
	
	public static final String PROPERTY_DOES_NOT_CONTAIN_A_SERVEABLE_RESOURCE = "Property does not contain a serveable resource";
	public static final String RESOURCE = "resource";

	private ModelAccessResource parentResource;
	
	public PropertyResource(ModelAccessResource parentResource) {
		this.parentResource = parentResource;
	}
	
	/** **/
	@GET
	@Path("/{propertyUuid}")
	@Produces(MediaType.APPLICATION_JSON)
	@Operation(
			summary = "Fetch Property",
			method = "GET",
			description = "This service fetches a Property.",
			responses = {
				@ApiResponse(
					responseCode = ApiErrorHelper.SUCCESSFUL_OPERATION_CODE,
					description = ApiErrorHelper.SUCCESSFUL_OPERATION,
					content = @Content(
							mediaType = MediaType.APPLICATION_JSON,
							schema = @Schema(implementation = ABeanProperty.class))
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
	public Response getProperty(@PathParam("propertyUuid") @Parameter(description = "Uuid of the property", required = true) String propertyUuid) {
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
	@Operation(
			summary = "Fetch property resource",
			method = "GET",
			description = "This service fetches the resource of a property.",
			responses = {
				@ApiResponse(
					responseCode = ApiErrorHelper.SUCCESSFUL_OPERATION_CODE,
					description = ApiErrorHelper.SUCCESSFUL_OPERATION,
					headers = {@Header(name = HttpHeaders.CONTENT_DISPOSITION,
							description = "attachment; filename=\"{name}\"",
							schema = @Schema(contentMediaType = "application/octet-stream", implementation = String.class))}
						),
				@ApiResponse(
					responseCode = ApiErrorHelper.COULD_NOT_FIND_REQUESTED_ELEMENT_CODE,
					description = ApiErrorHelper.COULD_NOT_FIND_REQUESTED_ELEMENT
						),
				@ApiResponse(
						responseCode = "400",
						description = PROPERTY_DOES_NOT_CONTAIN_A_SERVEABLE_RESOURCE
						),
				@ApiResponse(
						responseCode = ApiErrorHelper.INTERNAL_SERVER_ERROR_CODE,
						description = ApiErrorHelper.INTERNAL_SERVER_ERROR
						)
			})
	public Response getResource(@PathParam("propertyUuid") @Parameter(description = "Uuid of the property", required = true) String propertyUuid) {
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
	@Consumes(MediaType.APPLICATION_JSON)
	@Operation(
			summary = "Put Property",
			method = "PUT",
			description = "This service updates an existing Property.",
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
	public Response putProperty(@SuppressWarnings("rawtypes") @Parameter(description = "Property to put", required = true) ABeanProperty bean) {
		try {
			parentResource.synchronize();
			return Response.status(Response.Status.OK).build();
		} catch (Exception e) {
			return ApiErrorHelper.createInternalErrorResponse(e.getMessage());
		}
	}

}
