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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.jetty.http.HttpStatus;

import de.dlr.sc.virsat.model.concept.types.factory.BeanQuantityKindFactory;
import de.dlr.sc.virsat.model.concept.types.factory.BeanUnitFactory;
import de.dlr.sc.virsat.model.concept.types.qudv.ABeanQuantityKind;
import de.dlr.sc.virsat.model.concept.types.qudv.ABeanUnit;
import de.dlr.sc.virsat.model.concept.types.qudv.BeanFactorQuantityKind;
import de.dlr.sc.virsat.model.concept.types.qudv.BeanPrefix;
import de.dlr.sc.virsat.model.concept.types.qudv.BeanQuantityKindDerived;
import de.dlr.sc.virsat.model.concept.types.qudv.BeanQuantityKindSimple;
import de.dlr.sc.virsat.model.concept.types.qudv.BeanUnitAffineConversion;
import de.dlr.sc.virsat.model.concept.types.qudv.BeanUnitDerived;
import de.dlr.sc.virsat.model.concept.types.qudv.BeanUnitLinearConversion;
import de.dlr.sc.virsat.model.concept.types.qudv.BeanUnitPrefixed;
import de.dlr.sc.virsat.model.concept.types.qudv.BeanUnitSimple;
import de.dlr.sc.virsat.model.dvlm.qudv.AQuantityKind;
import de.dlr.sc.virsat.model.dvlm.qudv.AUnit;
import de.dlr.sc.virsat.model.dvlm.qudv.AffineConversionUnit;
import de.dlr.sc.virsat.model.dvlm.qudv.DerivedQuantityKind;
import de.dlr.sc.virsat.model.dvlm.qudv.DerivedUnit;
import de.dlr.sc.virsat.model.dvlm.qudv.LinearConversionUnit;
import de.dlr.sc.virsat.model.dvlm.qudv.Prefix;
import de.dlr.sc.virsat.model.dvlm.qudv.PrefixedUnit;
import de.dlr.sc.virsat.model.dvlm.qudv.QuantityKindFactor;
import de.dlr.sc.virsat.model.dvlm.qudv.QudvFactory;
import de.dlr.sc.virsat.model.dvlm.qudv.QudvPackage;
import de.dlr.sc.virsat.model.dvlm.qudv.SimpleQuantityKind;
import de.dlr.sc.virsat.model.dvlm.qudv.SimpleUnit;
import de.dlr.sc.virsat.model.dvlm.qudv.SystemOfQuantities;
import de.dlr.sc.virsat.model.dvlm.qudv.UnitFactor;
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

@Api(hidden = true, authorizations = {@Authorization(value = "basic")}, tags = {ModelAccessResource.TAG_QUDV})
public class QudvResource {

	public static final String PREFIXES = "prefixes";
	public static final String UNITS = "units";
	public static final String QUANTITY_KINDS = "quantityKinds";
	public static final String PREFIX = "prefix";
	public static final String UNIT = "unit";
	public static final String QUANTITY_KIND = "quantityKind";
	public static final String UNIT_FACTOR = "unitFactor";
	public static final String QUANTITY_KIND_FACTOR = "quantityKindFactor";
	public static final String NOT_DERIVED = "Not of derived type";
	
	private static final Map<String, String> BEAN_UNIT_TO_QUDV_CLASSES;
	private static final Map<String, String> BEAN_QUANTITYKIND_TO_QUDV_CLASSES;
	
	static {
		BEAN_UNIT_TO_QUDV_CLASSES = new HashMap<String, String>();
		BEAN_UNIT_TO_QUDV_CLASSES.put(BeanUnitSimple.class.getSimpleName(), SimpleUnit.class.getSimpleName());
		BEAN_UNIT_TO_QUDV_CLASSES.put(BeanUnitPrefixed.class.getSimpleName(), PrefixedUnit.class.getSimpleName());
		BEAN_UNIT_TO_QUDV_CLASSES.put(BeanUnitDerived.class.getSimpleName(), DerivedUnit.class.getSimpleName());
		BEAN_UNIT_TO_QUDV_CLASSES.put(BeanUnitAffineConversion.class.getSimpleName(), AffineConversionUnit.class.getSimpleName());
		BEAN_UNIT_TO_QUDV_CLASSES.put(BeanUnitLinearConversion.class.getSimpleName(), LinearConversionUnit.class.getSimpleName());
		BEAN_UNIT_TO_QUDV_CLASSES.put(BeanFactorQuantityKind.class.getSimpleName(), QuantityKindFactor.class.getSimpleName());
		
		BEAN_QUANTITYKIND_TO_QUDV_CLASSES = new HashMap<String, String>();
		BEAN_QUANTITYKIND_TO_QUDV_CLASSES.put(BeanQuantityKindSimple.class.getSimpleName(), SimpleQuantityKind.class.getSimpleName());
		BEAN_QUANTITYKIND_TO_QUDV_CLASSES.put(BeanQuantityKindDerived.class.getSimpleName(), DerivedQuantityKind.class.getSimpleName());
	}

	private RepoModelAccessResource parentResource;
	
	public QudvResource(RepoModelAccessResource parentResource) {
		this.parentResource = parentResource;
	}
	

	// Lists of qudv resources
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
	@Path(QUANTITY_KINDS)
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
	public Response getQuantityKinds() {
		try {
			parentResource.synchronize();
			
			SystemOfQuantities systemOfQuantites = parentResource.getRepository().getUnitManagement().getSystemOfUnit().getSystemOfQuantities().get(0);
			
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

	// Unit resources
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
	@POST
	@Path(UNIT)
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
					code = HttpStatus.BAD_REQUEST_400, 
					message = ApiErrorHelper.INVALID_TYPE_ERROR),
			@ApiResponse(
					code = HttpStatus.INTERNAL_SERVER_ERROR_500, 
					message = ApiErrorHelper.SYNC_ERROR)})
	public Response createUnit(@ApiParam(value = "Unit bean type to create an instance from", required = true) @QueryParam(ModelAccessResource.QP_NAME) String unitType) {
		try {
			parentResource.synchronize();
			
			if (!BEAN_UNIT_TO_QUDV_CLASSES.containsKey(unitType)) {
				return ApiErrorHelper.createInvalidTypeErrorResponse(unitType);
			}
			
			EClass eClass = (EClass) QudvPackage.eINSTANCE.getEClassifier(BEAN_UNIT_TO_QUDV_CLASSES.get(unitType));
			AUnit createdObject = (AUnit) QudvFactory.eINSTANCE.create(eClass);
			
			Command addCommand = AddCommand.create(parentResource.getEd(), parentResource.getRepository().getUnitManagement().getSystemOfUnit(),
					QudvPackage.SYSTEM_OF_UNITS__UNIT, createdObject);
			parentResource.getEd().getCommandStack().execute(addCommand);
			
			parentResource.synchronize();
			return Response.ok(createdObject.getUuid().toString()).build();
		} catch (Exception e) {
			return ApiErrorHelper.createSyncErrorResponse(e.getMessage());
		}
	}
	
	/** **/
	@DELETE
	@Path(UNIT + "/{unitUuid}")
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
	public Response deleteUnit(@PathParam("unitUuid") @ApiParam(value = "Uuid of the Unit", required = true) String unitUuid) {
		try {
			parentResource.synchronize();
			
			AUnit unit = RepositoryUtility.findUnit(unitUuid, parentResource.getRepository());
			if (unit == null) {
				return ApiErrorHelper.createNotFoundErrorResponse();
			}
			
			Command removeCommand = RemoveCommand.create(parentResource.getEd(), parentResource.getRepository().getUnitManagement().getSystemOfUnit(), QudvPackage.SYSTEM_OF_UNITS__UNIT, unit);
			parentResource.getEd().getCommandStack().execute(removeCommand);
			
			parentResource.synchronize();
			return Response.ok().build();
		} catch (Exception e) {
			return ApiErrorHelper.createSyncErrorResponse(e.getMessage());
		}
	}
	
	// Quantity kind resources
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
	public Response putQuantityKind(@SuppressWarnings("rawtypes") @ApiParam(value = "QuantityKind to put", required = true) ABeanQuantityKind bean) {
		try {
			parentResource.synchronize();
			return Response.status(Response.Status.OK).build();
		} catch (Exception e) {
			return ApiErrorHelper.createSyncErrorResponse(e.getMessage());
		}
	}
	
	/** **/
	@POST
	@Path(QUANTITY_KIND)
	@Consumes(MediaType.APPLICATION_JSON)
	@ApiOperation(
			consumes = "application/json",
			value = "Create QuantityKind",
			httpMethod = "POST",
			notes = "This service creates a new QuantityKind and returns it")
	@ApiResponses(value = { 
			@ApiResponse(
					code = HttpStatus.OK_200,
					response = String.class,
					message = ApiErrorHelper.SUCCESSFUL_OPERATION),
			@ApiResponse(
					code = HttpStatus.BAD_REQUEST_400, 
					message = ApiErrorHelper.INVALID_TYPE_ERROR),
			@ApiResponse(
					code = HttpStatus.INTERNAL_SERVER_ERROR_500, 
					message = ApiErrorHelper.SYNC_ERROR)})
	public Response createQuantityKind(@ApiParam(value = "Quantity kind bean type to create an instance from", required = true) @QueryParam(ModelAccessResource.QP_NAME) String quantityKindType) {
		try {
			parentResource.synchronize();
			
			if (!BEAN_QUANTITYKIND_TO_QUDV_CLASSES.containsKey(quantityKindType)) {
				return ApiErrorHelper.createInvalidTypeErrorResponse(quantityKindType);
			}
			
			EClass eClass = (EClass) QudvPackage.eINSTANCE.getEClassifier(BEAN_QUANTITYKIND_TO_QUDV_CLASSES.get(quantityKindType));
			AQuantityKind createdObject = (AQuantityKind) QudvFactory.eINSTANCE.create(eClass);
			
			Command addCommand = AddCommand.create(parentResource.getEd(), parentResource.getRepository().getUnitManagement().getSystemOfUnit().getSystemOfQuantities().get(0),
					QudvPackage.SYSTEM_OF_QUANTITIES__QUANTITY_KIND, createdObject);
			parentResource.getEd().getCommandStack().execute(addCommand);
			
			parentResource.synchronize();
			return Response.ok(createdObject.getUuid().toString()).build();
		} catch (Exception e) {
			return ApiErrorHelper.createSyncErrorResponse(e.getMessage());
		}
	}
	
	/** **/
	@DELETE
	@Path(QUANTITY_KIND + "/{quantityKindUuid}")
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(
			produces = "application/json",
			value = "Delete QuantityKind",
			httpMethod = "DELETE",
			notes = "This service deletes a QuantityKind.")
	@ApiResponses(value = { 
			@ApiResponse(
					code = HttpStatus.OK_200,
					message = ApiErrorHelper.SUCCESSFUL_OPERATION),
			@ApiResponse(
					code = HttpStatus.BAD_REQUEST_400,
					message = ApiErrorHelper.COULD_NOT_FIND_REQUESTED_ELEMENT),
			@ApiResponse(
					code = HttpStatus.INTERNAL_SERVER_ERROR_500, 
					message = ApiErrorHelper.NOT_EXECUTEABLE),
			@ApiResponse(
					code = HttpStatus.INTERNAL_SERVER_ERROR_500, 
					message = ApiErrorHelper.SYNC_ERROR)})
	public Response deleteQuantityKind(@PathParam("quantityKindUuid") @ApiParam(value = "Uuid of the QuantityKind", required = true) String quantityKindUuid) {
		try {
			parentResource.synchronize();
			
			AQuantityKind qk = RepositoryUtility.findQuantityKind(quantityKindUuid, parentResource.getRepository());
			if (qk == null) {
				return ApiErrorHelper.createNotFoundErrorResponse();
			}
			
			Command removeCommand = RemoveCommand.create(parentResource.getEd(), parentResource.getRepository().getUnitManagement().getSystemOfUnit().getSystemOfQuantities().get(0),
					QudvPackage.SYSTEM_OF_QUANTITIES__QUANTITY_KIND, qk);
			
			// Not executable if there exists an reference where this qk is used (e.g. in a unit)
			if (!removeCommand.canExecute()) {
				return ApiErrorHelper.createNotExecuteableErrorResponse();
			}
			parentResource.getEd().getCommandStack().execute(removeCommand);
			
			parentResource.synchronize();
			return Response.ok().build();
		} catch (Exception e) {
			return ApiErrorHelper.createSyncErrorResponse(e.getMessage());
		}
	}
	
	// Prefix resources
	/** **/
	@GET
	@Path(PREFIX + "/{prefixUuid}")
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(
			produces = "application/json",
			value = "Fetch Prefix",
			httpMethod = "GET",
			notes = "This service fetches a Prefix")
	@ApiResponses(value = { 
			@ApiResponse(
					code = HttpStatus.OK_200,
					response = BeanPrefix.class,
					message = ApiErrorHelper.SUCCESSFUL_OPERATION),
			@ApiResponse(
					code = HttpStatus.BAD_REQUEST_400, 
					message = ApiErrorHelper.COULD_NOT_FIND_REQUESTED_ELEMENT),
			@ApiResponse(
					code = HttpStatus.INTERNAL_SERVER_ERROR_500, 
					message = ApiErrorHelper.SYNC_ERROR)})
	public Response getPrefix(@PathParam("prefixUuid") @ApiParam(value = "Uuid of the Prefix", required = true) String prefixUuid) {
		try {
			parentResource.synchronize();
			
			Prefix prefix = RepositoryUtility.findPrefix(prefixUuid, parentResource.getRepository());
			
			if (prefix == null) {
				return ApiErrorHelper.createNotFoundErrorResponse();
			}
			
			BeanPrefix beanPrefix = new BeanPrefix(prefix);
			return Response.status(Response.Status.OK).entity(beanPrefix).build();
		} catch (Exception e) {
			return ApiErrorHelper.createSyncErrorResponse(e.getMessage());
		}
	}

	/** **/
	@PUT
	@Path(PREFIX)
	@Consumes(MediaType.APPLICATION_JSON)
	@ApiOperation(
			produces = "application/json",
			value = "Put Prefix",
			httpMethod = "PUT",
			notes = "This service updates an existing Prefix")
	@ApiResponses(value = { 
			@ApiResponse(
					code = HttpStatus.OK_200,
					message = ApiErrorHelper.SUCCESSFUL_OPERATION),
			@ApiResponse(
					code = HttpStatus.INTERNAL_SERVER_ERROR_500,
					message = ApiErrorHelper.SYNC_ERROR)})
	public Response putPrefixKind(@ApiParam(value = "Prefix to put", required = true) BeanPrefix bean) {
		try {
			parentResource.synchronize();
			return Response.status(Response.Status.OK).build();
		} catch (Exception e) {
			return ApiErrorHelper.createSyncErrorResponse(e.getMessage());
		}
	}
	
	/** **/
	@POST
	@Path(PREFIX)
	@Consumes(MediaType.APPLICATION_JSON)
	@ApiOperation(
			consumes = "application/json",
			value = "Create Prefix",
			httpMethod = "POST",
			notes = "This service creates a new Prefix and returns it")
	@ApiResponses(value = { 
			@ApiResponse(
					code = HttpStatus.OK_200,
					response = String.class,
					message = ApiErrorHelper.SUCCESSFUL_OPERATION),
			@ApiResponse(
					code = HttpStatus.INTERNAL_SERVER_ERROR_500, 
					message = ApiErrorHelper.SYNC_ERROR)})
	public Response createPrefix() {
		try {
			parentResource.synchronize();
			
			Prefix prefix = QudvFactory.eINSTANCE.createPrefix();
			Command addCommand = AddCommand.create(parentResource.getEd(), parentResource.getRepository().getUnitManagement().getSystemOfUnit(),
					QudvPackage.SYSTEM_OF_UNITS__PREFIX, prefix);
			parentResource.getEd().getCommandStack().execute(addCommand);
			
			parentResource.synchronize();
			return Response.ok(prefix.getUuid().toString()).build();
		} catch (Exception e) {
			return ApiErrorHelper.createSyncErrorResponse(e.getMessage());
		}
	}
	
	/** **/
	@DELETE
	@Path(PREFIX + "/{prefixUuid}")
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(
			produces = "application/json",
			value = "Delete Prefix",
			httpMethod = "DELETE",
			notes = "This service deletes a Prefix.")
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
	public Response deletePrefix(@PathParam("prefixUuid") @ApiParam(value = "Uuid of the Prefix", required = true) String prefixUuid) {
		try {
			parentResource.synchronize();
			
			Prefix prefix = RepositoryUtility.findPrefix(prefixUuid, parentResource.getRepository());
			if (prefix == null) {
				return ApiErrorHelper.createNotFoundErrorResponse();
			}
			
			Command removeCommand = RemoveCommand.create(parentResource.getEd(), parentResource.getRepository().getUnitManagement().getSystemOfUnit(),
					QudvPackage.SYSTEM_OF_UNITS__PREFIX, prefix);
			parentResource.getEd().getCommandStack().execute(removeCommand);
			
			parentResource.synchronize();
			return Response.ok().build();
		} catch (Exception e) {
			return ApiErrorHelper.createSyncErrorResponse(e.getMessage());
		}
	}
	
	// Factor resources
	// As factors are contained they can be changed via the containers endpoint,
	// so we only provide create and delete endpoints for factors
	/** **/
	@POST
	@Path(UNIT_FACTOR + "/{uuid}")
	@Consumes(MediaType.APPLICATION_JSON)
	@ApiOperation(
			consumes = "application/json",
			value = "Create UnitFactor",
			httpMethod = "POST",
			notes = "This service creates a new UnitFactor and returns it")
	@ApiResponses(value = { 
			@ApiResponse(
					code = HttpStatus.OK_200,
					response = String.class,
					message = ApiErrorHelper.SUCCESSFUL_OPERATION),
			@ApiResponse(
					code = HttpStatus.BAD_REQUEST_400, 
					message = ApiErrorHelper.COULD_NOT_FIND_REQUESTED_ELEMENT),
			@ApiResponse(
					code = HttpStatus.BAD_REQUEST_400, 
					message = ApiErrorHelper.INVALID_TYPE_ERROR),
			@ApiResponse(
					code = HttpStatus.INTERNAL_SERVER_ERROR_500, 
					message = ApiErrorHelper.SYNC_ERROR)})
	public Response createUnitFactor(@ApiParam(value = "Parent unit of the unit factor", required = true) @PathParam("uuid") String unitFactorParentUuid) {
		try {
			parentResource.synchronize();
			
			AUnit unit = RepositoryUtility.findUnit(unitFactorParentUuid, parentResource.getRepository());
			
			if (unit == null) {
				return ApiErrorHelper.createNotFoundErrorResponse();
			}
			
			// Only derived units can have a factor
			if (!(unit instanceof DerivedUnit)) {
				return ApiErrorHelper.createInvalidTypeErrorResponse(NOT_DERIVED);
			}
			
			UnitFactor factor = QudvFactory.eINSTANCE.createUnitFactor();
			Command addCommand = AddCommand.create(parentResource.getEd(), unit,
					QudvPackage.DERIVED_UNIT__FACTOR, factor);
			parentResource.getEd().getCommandStack().execute(addCommand);
			
			parentResource.synchronize();
			return Response.ok(factor.getUuid().toString()).build();
		} catch (Exception e) {
			return ApiErrorHelper.createSyncErrorResponse(e.getMessage());
		}
	}
	
	/** **/
	@DELETE
	@Path(UNIT_FACTOR + "/{uuid}")
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(
			produces = "application/json",
			value = "Delete UnitFactor",
			httpMethod = "DELETE",
			notes = "This service deletes a UnitFactor.")
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
	public Response deleteUnitFactor(@PathParam("uuid") @ApiParam(value = "Uuid of the unit factor", required = true) String unitFactorUuid) {
		try {
			parentResource.synchronize();
			
			UnitFactor factor = RepositoryUtility.findUnitFactor(unitFactorUuid, parentResource.getRepository());
			if (factor == null) {
				return ApiErrorHelper.createNotFoundErrorResponse();
			}
			
			Command removeCommand = RemoveCommand.create(parentResource.getEd(), factor.eContainer(), QudvPackage.DERIVED_UNIT__FACTOR, factor);
			parentResource.getEd().getCommandStack().execute(removeCommand);
			
			parentResource.synchronize();
			return Response.ok().build();
		} catch (Exception e) {
			return ApiErrorHelper.createSyncErrorResponse(e.getMessage());
		}
	}
	
	/** **/
	@POST
	@Path(QUANTITY_KIND_FACTOR + "/{uuid}")
	@Consumes(MediaType.APPLICATION_JSON)
	@ApiOperation(
			consumes = "application/json",
			value = "Create QuantityKindFactor",
			httpMethod = "POST",
			notes = "This service creates a new QuantityKindFactor and returns it")
	@ApiResponses(value = { 
			@ApiResponse(
					code = HttpStatus.OK_200,
					response = String.class,
					message = ApiErrorHelper.SUCCESSFUL_OPERATION),
			@ApiResponse(
					code = HttpStatus.BAD_REQUEST_400, 
					message = ApiErrorHelper.COULD_NOT_FIND_REQUESTED_ELEMENT),
			@ApiResponse(
					code = HttpStatus.BAD_REQUEST_400, 
					message = ApiErrorHelper.INVALID_TYPE_ERROR),
			@ApiResponse(
					code = HttpStatus.INTERNAL_SERVER_ERROR_500, 
					message = ApiErrorHelper.SYNC_ERROR)})
	public Response createQuantityKindFactor(@ApiParam(value = "Parent quantity kind of the quantity kind factor", required = true) @PathParam("uuid") String quantityKindFactorParentUuid) {
		try {
			parentResource.synchronize();
			
			AQuantityKind quantityKind = RepositoryUtility.findQuantityKind(quantityKindFactorParentUuid, parentResource.getRepository());
			
			if (quantityKind == null) {
				return ApiErrorHelper.createNotFoundErrorResponse();
			}
			
			// Only derived quantityKinds can have a factor
			if (!(quantityKind instanceof DerivedQuantityKind)) {
				return ApiErrorHelper.createInvalidTypeErrorResponse(NOT_DERIVED);
			}
			
			QuantityKindFactor factor = QudvFactory.eINSTANCE.createQuantityKindFactor();
			Command addCommand = AddCommand.create(parentResource.getEd(), quantityKind,
					QudvPackage.DERIVED_QUANTITY_KIND, factor);
			parentResource.getEd().getCommandStack().execute(addCommand);
			
			parentResource.synchronize();
			return Response.ok(factor.getUuid().toString()).build();
		} catch (Exception e) {
			return ApiErrorHelper.createSyncErrorResponse(e.getMessage());
		}
	}
	
	/** **/
	@DELETE
	@Path(QUANTITY_KIND_FACTOR + "/{uuid}")
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(
			produces = "application/json",
			value = "Delete QuantityKindFactor",
			httpMethod = "DELETE",
			notes = "This service deletes a QuantityKindFactor.")
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
	public Response deleteQuantityKindFactor(@PathParam("uuid") @ApiParam(value = "Uuid of the quantity kind factor", required = true) String quantityKindFactorUuid) {
		try {
			parentResource.synchronize();
			
			QuantityKindFactor factor = RepositoryUtility.findQuantityKindFactor(quantityKindFactorUuid, parentResource.getRepository());
			if (factor == null) {
				return ApiErrorHelper.createNotFoundErrorResponse();
			}
			
			Command removeCommand = RemoveCommand.create(parentResource.getEd(), factor.eContainer(), QudvPackage.DERIVED_QUANTITY_KIND, factor);
			parentResource.getEd().getCommandStack().execute(removeCommand);
			
			parentResource.synchronize();
			return Response.ok().build();
		} catch (Exception e) {
			return ApiErrorHelper.createSyncErrorResponse(e.getMessage());
		}
	}

}
