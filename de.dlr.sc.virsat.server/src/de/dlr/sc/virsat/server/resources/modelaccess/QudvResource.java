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

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.edit.command.DeleteCommand;
import org.eclipse.jetty.http.HttpStatus;

import de.dlr.sc.virsat.model.concept.types.factory.BeanQuantityKindFactory;
import de.dlr.sc.virsat.model.concept.types.factory.BeanUnitFactory;
import de.dlr.sc.virsat.model.concept.types.qudv.ABeanQuantityKind;
import de.dlr.sc.virsat.model.concept.types.qudv.ABeanUnit;
import de.dlr.sc.virsat.model.concept.types.qudv.BeanPrefix;
import de.dlr.sc.virsat.model.concept.types.qudv.BeanSystemOfQuantities;
import de.dlr.sc.virsat.model.dvlm.qudv.AQuantityKind;
import de.dlr.sc.virsat.model.dvlm.qudv.AUnit;
import de.dlr.sc.virsat.model.dvlm.qudv.Prefix;
import de.dlr.sc.virsat.model.dvlm.qudv.SystemOfQuantities;
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
public class QudvResource {

	public static final String PREFIXES = "prefixes";
	public static final String SYSTEMS_OF_QUANTITES = "systemsOfQuantites";
	public static final String UNITS = "units";
	public static final String QUANTITY_KINDS = "quantityKinds";
	public static final String UNIT = "unit";
	public static final String QUANTITY_KIND = "quantityKind";

	// TODO: general create and delete enpoint
	private RepoModelAccessResource parentResource;
	
	public QudvResource(RepoModelAccessResource parentResource) {
		this.parentResource = parentResource;
	}
	

	/** **/
	@GET
	@Path(PREFIXES)
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(
			produces = "application/json",
			value = "Fetch a list of prefixes",
			httpMethod = "GET",
			notes = "This service fetches the Prefixes")
	@ApiResponses(value = { 
			@ApiResponse(
					code = HttpStatus.OK_200,
					response = BeanPrefix.class,
					responseContainer = "List",
					message = ApiErrorHelper.SUCCESSFUL_OPERATION),
			@ApiResponse(
					code = HttpStatus.INTERNAL_SERVER_ERROR_500, 
					message = ApiErrorHelper.SYNC_ERROR)})
	public Response getPrefixes() {
		try {
			parentResource.synchronize();
			
			List<Prefix> prefixes = parentResource.getRepository().getUnitManagement().getSystemOfUnit().getPrefix();
			List<BeanPrefix> beanPrefixes = new ArrayList<BeanPrefix>();
			
			for (Prefix prefix : prefixes) {
				beanPrefixes.add(new BeanPrefix(prefix));
			}
			
			GenericEntity<List<BeanPrefix>> entity = new GenericEntity<List<BeanPrefix>>(beanPrefixes) { };
			
			return Response.ok(entity).build();
		} catch (Exception e) {
			return ApiErrorHelper.createSyncErrorResponse(e.getMessage());
		}
	}
	
	/** **/
	@GET
	@Path(SYSTEMS_OF_QUANTITES)
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(
			produces = "application/json",
			value = "Fetch a list of system of quantities",
			httpMethod = "GET",
			notes = "This service fetches the SystemOfQuantities")
	@ApiResponses(value = { 
			@ApiResponse(
					code = HttpStatus.OK_200,
					response = BeanSystemOfQuantities.class,
					responseContainer = "List",
					message = ApiErrorHelper.SUCCESSFUL_OPERATION),
			@ApiResponse(
					code = HttpStatus.INTERNAL_SERVER_ERROR_500, 
					message = ApiErrorHelper.SYNC_ERROR)})
	public Response getSystemsOfQuantites() {
		try {
			parentResource.synchronize();
			
			List<SystemOfQuantities> systemsOfQuantites = parentResource.getRepository().getUnitManagement().getSystemOfUnit().getSystemOfQuantities();
			List<BeanSystemOfQuantities> beanSystemsOfQuantites = new ArrayList<BeanSystemOfQuantities>();
			
			for (SystemOfQuantities systemOfQuantites : systemsOfQuantites) {
				beanSystemsOfQuantites.add(new BeanSystemOfQuantities(systemOfQuantites));
			}
			
			GenericEntity<List<BeanSystemOfQuantities>> entity = new GenericEntity<List<BeanSystemOfQuantities>>(beanSystemsOfQuantites) { };
			
			return Response.ok(entity).build();
		} catch (Exception e) {
			return ApiErrorHelper.createSyncErrorResponse(e.getMessage());
		}
	}
	
	/** **/
	@GET
	@Path(SYSTEMS_OF_QUANTITES + "/{systemOfQuantitesUuid}/" + QUANTITY_KINDS)
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(
			produces = "application/json",
			value = "Fetch a list of quantity kinds",
			httpMethod = "GET",
			notes = "This service fetches the QuantityKinds")
	@ApiResponses(value = { 
			@ApiResponse(
					code = HttpStatus.OK_200,
					response = ABeanQuantityKind.class,
					responseContainer = "List",
					message = ApiErrorHelper.SUCCESSFUL_OPERATION),
			@ApiResponse(
					code = HttpStatus.INTERNAL_SERVER_ERROR_500, 
					message = ApiErrorHelper.SYNC_ERROR)})
	@SuppressWarnings("rawtypes")
	public Response getQuantityKinds(@PathParam("systemOfQuantitesUuid") @ApiParam(value = "Uuid of the systemOfQuantities", required = true) String systemOfQuantitesUuid) {
		try {
			parentResource.synchronize();
			
			SystemOfQuantities systemOfQuantites = RepositoryUtility.findSystemOfQuantites(systemOfQuantitesUuid, parentResource.getRepository());
			
			if (systemOfQuantites == null) {
				return ApiErrorHelper.createNotFoundErrorResponse();
			}
			
			List<ABeanQuantityKind> beanQuantityKinds = new ArrayList<>();
			
			for (AQuantityKind quantityKind : systemOfQuantites.getQuantityKind()) {
				ABeanQuantityKind<? extends AQuantityKind> beanQuantityKind = (ABeanQuantityKind<? extends AQuantityKind>) new BeanQuantityKindFactory().getInstanceFor(quantityKind);
				beanQuantityKinds.add(beanQuantityKind);
			}
			
			GenericEntity<List<ABeanQuantityKind>> entity = new GenericEntity<List<ABeanQuantityKind>>(beanQuantityKinds) { };
			
			return Response.ok(entity).build();
		} catch (Exception e) {
			return ApiErrorHelper.createSyncErrorResponse(e.getMessage());
		}
	}
	
	
	/** **/
	@GET
	@Path(UNITS)
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(
			produces = "application/json",
			value = "Fetch a list of units",
			httpMethod = "GET",
			notes = "This service fetches the Units")
	@ApiResponses(value = { 
			@ApiResponse(
					code = HttpStatus.OK_200,
					response = ABeanUnit.class,
					responseContainer = "List",
					message = ApiErrorHelper.SUCCESSFUL_OPERATION),
			@ApiResponse(
					code = HttpStatus.INTERNAL_SERVER_ERROR_500, 
					message = ApiErrorHelper.SYNC_ERROR)})
	@SuppressWarnings("rawtypes")
	public Response getUnits() {
		try {
			parentResource.synchronize();
			
			List<AUnit> units = parentResource.getRepository().getUnitManagement().getSystemOfUnit().getUnit();
			List<ABeanUnit> beanUnits = new ArrayList<>();
			
			for (AUnit unit : units) {
				ABeanUnit<? extends AUnit> beanUnit = (ABeanUnit<? extends AUnit>) new BeanUnitFactory().getInstanceFor(unit);
				beanUnits.add(beanUnit);
			}
			
			GenericEntity<List<ABeanUnit>> entity = new GenericEntity<List<ABeanUnit>>(beanUnits) { };
			
			return Response.ok(entity).build();
		} catch (Exception e) {
			return ApiErrorHelper.createSyncErrorResponse(e.getMessage());
		}
	}
	
	// TODO: test
	
	/** **/
	@SuppressWarnings("rawtypes")
	@GET
	@Path(UNIT + "/{unitUuid}")
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(
			produces = "application/json",
			value = "Fetch Unit",
			httpMethod = "GET",
			notes = "This service fetches a Unit")
	@ApiResponses(value = { 
			@ApiResponse(
					code = HttpStatus.OK_200,
					response = ABeanUnit.class,
					message = ApiErrorHelper.SUCCESSFUL_OPERATION),
			@ApiResponse(
					code = HttpStatus.BAD_REQUEST_400, 
					message = ApiErrorHelper.COULD_NOT_FIND_REQUESTED_ELEMENT),
			@ApiResponse(
					code = HttpStatus.INTERNAL_SERVER_ERROR_500, 
					message = ApiErrorHelper.SYNC_ERROR)})
	public Response getUnit(@PathParam("unitUuid") @ApiParam(value = "Uuid of the Unit", required = true) String unitUuid) {
		try {
			parentResource.synchronize();
			
			AUnit unit = RepositoryUtility.findUnit(unitUuid, parentResource.getRepository());
			
			if (unit == null) {
				return ApiErrorHelper.createNotFoundErrorResponse();
			}
			
			ABeanUnit beanUnit = (ABeanUnit) new BeanUnitFactory().getInstanceFor(unit);
			return Response.status(Response.Status.OK).entity(beanUnit).build();
		} catch (Exception e) {
			return ApiErrorHelper.createSyncErrorResponse(e.getMessage());
		}
	}

	/** **/
	@PUT
	@Path(UNIT)
	@Consumes(MediaType.APPLICATION_JSON)
	@ApiOperation(
			produces = "application/json",
			value = "Put Unit",
			httpMethod = "PUT",
			notes = "This service updates an existing Unit")
	@ApiResponses(value = { 
			@ApiResponse(
					code = HttpStatus.OK_200,
					message = ApiErrorHelper.SUCCESSFUL_OPERATION),
			@ApiResponse(
					code = HttpStatus.INTERNAL_SERVER_ERROR_500, 
					message = ApiErrorHelper.SYNC_ERROR)})
	public Response putUnit(@SuppressWarnings("rawtypes") @ApiParam(value = "Unit to put", required = true) ABeanUnit bean) {
		try {
			parentResource.synchronize();
			return Response.status(Response.Status.OK).build();
		} catch (Exception e) {
			return ApiErrorHelper.createSyncErrorResponse(e.getMessage());
		}
	}
	
	/** **/
	@SuppressWarnings("rawtypes")
	@GET
	@Path(QUANTITY_KIND + "/{quantityKindUuid}")
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(
			produces = "application/json",
			value = "Fetch QuantityKind",
			httpMethod = "GET",
			notes = "This service fetches a QuantityKind")
	@ApiResponses(value = { 
			@ApiResponse(
					code = HttpStatus.OK_200,
					response = ABeanQuantityKind.class,
					message = ApiErrorHelper.SUCCESSFUL_OPERATION),
			@ApiResponse(
					code = HttpStatus.BAD_REQUEST_400, 
					message = ApiErrorHelper.COULD_NOT_FIND_REQUESTED_ELEMENT),
			@ApiResponse(
					code = HttpStatus.INTERNAL_SERVER_ERROR_500, 
					message = ApiErrorHelper.SYNC_ERROR)})
	public Response getQuantityKind(@PathParam("quantityKindUuid") @ApiParam(value = "Uuid of the QuantityKind", required = true) String quantityKindUuid) {
		try {
			parentResource.synchronize();
			
			AQuantityKind quantityKind = RepositoryUtility.findQuantityKind(quantityKindUuid, parentResource.getRepository());
			
			if (quantityKind == null) {
				return ApiErrorHelper.createNotFoundErrorResponse();
			}
			
			ABeanQuantityKind beanQuantityKind = (ABeanQuantityKind) new BeanQuantityKindFactory().getInstanceFor(quantityKind);
			return Response.status(Response.Status.OK).entity(beanQuantityKind).build();
		} catch (Exception e) {
			return ApiErrorHelper.createSyncErrorResponse(e.getMessage());
		}
	}

	/** **/
	@PUT
	@Path(QUANTITY_KIND)
	@Consumes(MediaType.APPLICATION_JSON)
	@ApiOperation(
			produces = "application/json",
			value = "Put QuantityKind",
			httpMethod = "PUT",
			notes = "This service updates an existing QuantityKind")
	@ApiResponses(value = { 
			@ApiResponse(
					code = HttpStatus.OK_200,
					message = ApiErrorHelper.SUCCESSFUL_OPERATION),
			@ApiResponse(
					code = HttpStatus.INTERNAL_SERVER_ERROR_500, 
					message = ApiErrorHelper.SYNC_ERROR)})
	public Response putUnit(@SuppressWarnings("rawtypes") @ApiParam(value = "QuantityKind to put", required = true) ABeanQuantityKind bean) {
		try {
			parentResource.synchronize();
			return Response.status(Response.Status.OK).build();
		} catch (Exception e) {
			return ApiErrorHelper.createSyncErrorResponse(e.getMessage());
		}
	}
	

	// TODO: general create and delete endpoint
	
	/** **/
	@POST
	@Path("/{unitUuid}")
	@Consumes(MediaType.APPLICATION_JSON)
	@ApiOperation(
			consumes = "application/json",
			value = "Create Unit",
			httpMethod = "POST",
			notes = "This service creates a new Unit and returns it")
	@ApiResponses(value = { 
			@ApiResponse(
					code = HttpStatus.OK_200,
					response = String.class,
					message = ApiErrorHelper.SUCCESSFUL_OPERATION),
			@ApiResponse(
					code = HttpStatus.INTERNAL_SERVER_ERROR_500, 
					message = ApiErrorHelper.SYNC_ERROR)})
	public Response createUnit(@QueryParam(value = ModelAccessResource.QP_FULL_QUALIFIED_NAME)
			@ApiParam(value = "Full qualified name of the Unit type", required = true) String fullQualifiedName) {
		// TODO
//		try {
//			parentResource.synchronize();
//			
//			EClass eClass = (EClass) QudvFactory.eINSTANCE.getEPackage().getEClassifier("MyClass");
//			QudvFactory.eINSTANCE.create(eClass)
//			
//			Command createCommand = AddCommand.create(parentResource.getEd(), parentSei, UnittegoriesPackage.Literals.IUnitTEGORY_ASSIGNMENT_CONTAINER__UnitTEGORY_ASSIGNMENTS, newUnit);
//			parentResource.getEd().getCommandStack().execute(createCommand);
//			
//			parentResource.synchronize();
//			return Response.ok(newUnit.getUuid().toString()).build();
//		} catch (Exception e) {
//			return ApiErrorHelper.createSyncErrorResponse(e.getMessage());
//		}
		return null;
	}
	
	/** **/
	@DELETE
	@Path("/{unitUuid}")
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(
			produces = "application/json",
			value = "Delete Unit",
			httpMethod = "DELETE",
			notes = "This service deletes a Unit.")
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
	public Response deleteUnit(@PathParam("unitUuid") @ApiParam(value = "Uuid of the Unit", required = true)  String unitUuid) {
		try {
//			// Sync before delete
//			parentResource.synchronize();
//			
//			// Delete Unit
//			AUnit unit = RepositoryUtility.findUnit(unitUuid, parentResource.getRepository());
//			if (unit == null) {
//				return ApiErrorHelper.createNotFoundErrorResponse();
//			}
//			
//			Command deleteCommand = DeleteCommand.create(parentResource.getEd(), unit);
//			parentResource.getEd().getCommandStack().execute(deleteCommand);
//			
//			// Sync after delete
//			parentResource.synchronize();
			return Response.ok().build();
		} catch (Exception e) {
			return ApiErrorHelper.createSyncErrorResponse(e.getMessage());
		}
	}
}
