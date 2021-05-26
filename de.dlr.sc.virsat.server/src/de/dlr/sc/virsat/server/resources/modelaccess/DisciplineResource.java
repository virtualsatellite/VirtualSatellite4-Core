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
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.jetty.http.HttpStatus;

import de.dlr.sc.virsat.model.concept.types.roles.BeanDiscipline;
import de.dlr.sc.virsat.model.dvlm.roles.Discipline;
import de.dlr.sc.virsat.model.dvlm.roles.RolesFactory;
import de.dlr.sc.virsat.model.dvlm.roles.RolesPackage;
import de.dlr.sc.virsat.server.dataaccess.RepositoryUtility;
import de.dlr.sc.virsat.server.resources.ApiErrorHelper;
import de.dlr.sc.virsat.server.resources.ModelAccessResource.RepoModelAccessResource;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Authorization;

@Api(hidden = true, authorizations = {@Authorization(value = "basic")})
public class DisciplineResource {
	
	private RepoModelAccessResource parentResource;
	
	public DisciplineResource(RepoModelAccessResource parentResource) {
		this.parentResource = parentResource;
	}
	
	/** **/
	@GET
	@Path("/{disciplineUuid}")
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(
			produces = "application/json",
			value = "Fetch Discipline",
			httpMethod = "GET",
			notes = "This service fetches a Discipline")
	@ApiResponses(value = { 
			@ApiResponse(
					code = HttpStatus.OK_200,
					response = BeanDiscipline.class,
					message = ApiErrorHelper.SUCCESSFUL_OPERATION),
			@ApiResponse(
					code = HttpStatus.BAD_REQUEST_400, 
					message = ApiErrorHelper.COULD_NOT_FIND_REQUESTED_ELEMENT),
			@ApiResponse(
					code = HttpStatus.INTERNAL_SERVER_ERROR_500, 
					message = ApiErrorHelper.INTERNAL_SERVER_ERROR)})
	public Response getDiscipline(@PathParam("disciplineUuid") @ApiParam(value = "Uuid of the discipline", required = true) String disciplineUuid) {
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
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@ApiOperation(
			consumes = "application/json",
			value = "Put Discipline",
			httpMethod = "PUT",
			notes = "This service updates an existing Discipline")
	@ApiResponses(value = { 
			@ApiResponse(
					code = HttpStatus.OK_200,
					message = ApiErrorHelper.SUCCESSFUL_OPERATION),
			@ApiResponse(
					code = HttpStatus.INTERNAL_SERVER_ERROR_500, 
					message = ApiErrorHelper.INTERNAL_SERVER_ERROR)})
	public Response putDiscipline(@ApiParam(value = "Discipline to put", required = true) BeanDiscipline bean) {
		try {
			parentResource.synchronize();
			return Response.status(Response.Status.OK).build();
		} catch (Exception e) {
			return ApiErrorHelper.createInternalErrorResponse(e.getMessage());
		}
	}
	
	/** **/
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@ApiOperation(
			consumes = "application/json",
			value = "Create Discipline",
			httpMethod = "POST",
			notes = "This service creates a new Discipline and returns the uuid")
	@ApiResponses(value = { 
			@ApiResponse(
					code = HttpStatus.OK_200,
					response = String.class,
					message = ApiErrorHelper.SUCCESSFUL_OPERATION),
			@ApiResponse(
					code = HttpStatus.INTERNAL_SERVER_ERROR_500, 
					message = ApiErrorHelper.INTERNAL_SERVER_ERROR)})
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
	@ApiOperation(
			produces = "application/json",
			value = "Delete Discipline",
			httpMethod = "DELETE",
			notes = "This service deletes a Discipline")
	@ApiResponses(value = { 
			@ApiResponse(
					code = HttpStatus.OK_200,
					message = ApiErrorHelper.SUCCESSFUL_OPERATION),
			@ApiResponse(
					code = HttpStatus.BAD_REQUEST_400,
					message = ApiErrorHelper.COULD_NOT_FIND_REQUESTED_ELEMENT),
			@ApiResponse(
					code = HttpStatus.INTERNAL_SERVER_ERROR_500, 
					message = ApiErrorHelper.INTERNAL_SERVER_ERROR)})
	public Response deleteDiscipline(@PathParam("disciplineUuid") @ApiParam(value = "Uuid of the Discipline", required = true) String disciplineUuid) {
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
