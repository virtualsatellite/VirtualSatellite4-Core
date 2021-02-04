/*******************************************************************************
 * Copyright (c) 2020 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.server.resources;


import java.util.ArrayList;
import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.jetty.http.HttpStatus;

import de.dlr.sc.virsat.model.concept.types.category.ABeanCategoryAssignment;
import de.dlr.sc.virsat.model.concept.types.factory.BeanCategoryAssignmentFactory;
import de.dlr.sc.virsat.model.concept.types.factory.BeanPropertyFactory;
import de.dlr.sc.virsat.model.concept.types.factory.BeanStructuralElementInstanceFactory;
import de.dlr.sc.virsat.model.concept.types.property.ABeanProperty;
import de.dlr.sc.virsat.model.concept.types.structural.ABeanStructuralElementInstance;
import de.dlr.sc.virsat.model.concept.types.structural.IBeanStructuralElementInstance;
import de.dlr.sc.virsat.model.dvlm.Repository;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import de.dlr.sc.virsat.project.editingDomain.VirSatTransactionalEditingDomain;
import de.dlr.sc.virsat.server.auth.ServerRoles;
import de.dlr.sc.virsat.server.dataaccess.RepositoryUtility;
import de.dlr.sc.virsat.server.dataaccess.TransactionalJsonProvider;
import de.dlr.sc.virsat.server.jetty.VirSatJettyServer;
import de.dlr.sc.virsat.server.repository.RepoRegistry;
import de.dlr.sc.virsat.server.repository.ServerRepository;
import de.dlr.sc.virsat.server.servlet.VirSatModelAccessServlet;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Authorization;
import io.swagger.annotations.Info;
import io.swagger.annotations.SwaggerDefinition;

/**
 * The resource to access the VirSat data model of a server repository
 * Provides an endpoint to access a repository
 */
@Api
@SwaggerDefinition(
	info = @Info(
		version = VirSatModelAccessServlet.MODEL_API_VERSION,
		title = "The Model API",
		description = "API to access the Virtual Satellite data model"
	),
	basePath = VirSatJettyServer.PATH + VirSatModelAccessServlet.MODEL_API
)
@Path(ModelAccessResource.PATH)
public class ModelAccessResource {

	@Inject
	TransactionalJsonProvider provider;
	
	public static final String PATH = "/repository";

	public static final String ROOT_SEIS = "seis";
	public static final String SEI = "sei";
	public static final String DISCIPLINES = "disciplines";
	public static final String CONCEPTS = "concepts";
	public static final String CA = "ca";
	public static final String CA_AND_PROPERTIES = "caAndProperties";
	public static final String PROPERTY = "property";

	public ModelAccessResource() { }
	
	/**
	 * Get the ServerRepository corresponding to the repoName and create a new RepoModelAccessResource
	 * @param repoName of the repository to be accessed by the request
	 * @return RepoModelAccessResource or null if the repo is not found
	 */
	@Path("{repoName}")
	public RepoModelAccessResource getConcreteResource(@PathParam("repoName") @ApiParam(value = "Name of the repository", required = true) String repoName) {
		ServerRepository repo = RepoRegistry.getInstance().getRepository(repoName);
		if (repo != null) {
			VirSatTransactionalEditingDomain ed = repo.getEd();
			provider.setServerRepository(repo);
			return new RepoModelAccessResource(ed.getResourceSet().getRepository());
		}

		return null;
	}
	
	/**
	 * The resource to access the VirSat data model of a specific server repository
	 * Provides the following endpoints:
	 *   - Get roots seis
	 *   - Get and update sei by uuid
	 *   - Get disciplines
	 *   - Get concepts
	 *   - Get and update ca (with property uuids) by uuid
	 *   - Get and update ca with properties by uuid
	 *   - Get and update properties by uuid
	 */
	@Api(hidden = true,
		authorizations = {
			@Authorization(value = "basic")
		}
	)
	@RolesAllowed({ServerRoles.ADMIN, ServerRoles.USER})
	public static class RepoModelAccessResource {
	
		private static final String SUCCESSFUL_OPERATION = "Successful operation";
		private Repository repository;
		
		public RepoModelAccessResource(Repository repository) {
			this.repository = repository;
		}
		
		private Response createBadRequestResponse(String msg) {
			return Response.status(Response.Status.BAD_REQUEST).entity(msg).build();
		}

		@GET
		@Path(PROPERTY + "/{propertyUuid}")
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
						message = SUCCESSFUL_OPERATION),
				@ApiResponse(
						code = HttpStatus.BAD_REQUEST_400, 
						message = "Could not find requested Property")})
		public Response getProperty(@PathParam("propertyUuid") @ApiParam(value = "Uuid of the property", required = true) String propertyUuid) {
			return Response.status(Response.Status.OK).entity(
					new BeanPropertyFactory().getInstanceFor(
							RepositoryUtility.findProperty(propertyUuid, repository)
					)).build();
		}
		
		@PUT
		@Path(PROPERTY)
		@Consumes(MediaType.APPLICATION_JSON)
		@ApiOperation(
				consumes = "application/json",
				value = "Put Property",
				httpMethod = "PUT",
				notes = "This service updates an existing Property")
		@ApiResponse(
				code = HttpStatus.OK_200,
				message = SUCCESSFUL_OPERATION)
		public Response putProperty(@SuppressWarnings("rawtypes") @ApiParam(value = "Property to put", required = true) ABeanProperty bean) {
			return Response.status(Response.Status.OK).build();
		}
		

		/** **/
		@GET
		@Path(CA + "/{caUuid}")
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
						message = SUCCESSFUL_OPERATION),
				@ApiResponse(
						code = HttpStatus.BAD_REQUEST_400, 
						message = "Could not find requested CA")})
		public Response getCa(@PathParam("caUuid") @ApiParam(value = "Uuid of the CA", required = true) String caUuid) {
			try {
				return Response.status(Response.Status.OK).entity(
						new BeanCategoryAssignmentFactory().getInstanceFor(
								RepositoryUtility.findCa(caUuid, repository)
						)).build();
			} catch (CoreException e) {
				return createBadRequestResponse(e.getMessage());
			}
		}

		@PUT
		@Path(CA)
		@Consumes(MediaType.APPLICATION_JSON)
		@ApiOperation(
				produces = "application/json",
				value = "Put CA",
				httpMethod = "PUT",
				notes = "This service updates an existing CategoryAssignment")
		@ApiResponse(
				code = HttpStatus.OK_200,
				message = SUCCESSFUL_OPERATION)
		public Response putCa(@ApiParam(value = "CA to put", required = true) ABeanCategoryAssignment bean) {
			return Response.status(Response.Status.OK).build();
		}
		
		/** **/
		@GET
		@Path(ROOT_SEIS)
		@Produces(MediaType.APPLICATION_JSON)
		@ApiOperation(
				produces = "application/json",
				value = "Fetch a list of root SEIs",
				httpMethod = "GET",
				notes = "This service fetches the root StructuralElementInstances")
		@ApiResponses(value = { 
				@ApiResponse(
						code = HttpStatus.OK_200,
						response = ABeanStructuralElementInstance.class,
						responseContainer = "List",
						message = SUCCESSFUL_OPERATION),
				@ApiResponse(
						code = HttpStatus.BAD_REQUEST_400, 
						message = "Could not create bean for a root SEI")})
		public Response getRootSeis() {
			try {
				List<StructuralElementInstance> rootSeis = repository.getRootEntities();
				List<ABeanStructuralElementInstance> beans = new ArrayList<ABeanStructuralElementInstance>();
				
				for (StructuralElementInstance sei : rootSeis) {
					beans.add((ABeanStructuralElementInstance) new BeanStructuralElementInstanceFactory().getInstanceFor(sei));
				}
				
				GenericEntity<List<ABeanStructuralElementInstance>> genericEntityList =
						new GenericEntity<List<ABeanStructuralElementInstance>>(beans) { };

				return Response.ok(genericEntityList).build();
			} catch (CoreException e) {
				return createBadRequestResponse(e.getMessage());
			}
		}
		
		/** **/
		@GET
		@Path(SEI + "/{seiUuid}")
		@Produces(MediaType.APPLICATION_JSON)
		@ApiOperation(
				produces = "application/json",
				value = "Fetch SEI",
				httpMethod = "GET",
				notes = "This service fetches a StructuralElementInstance."
						+ "It can be used as an entry point into the data model.")
		@ApiResponses(value = { 
				@ApiResponse(
						code = HttpStatus.OK_200,
						response = ABeanStructuralElementInstance.class,
						message = SUCCESSFUL_OPERATION),
				@ApiResponse(
						code = HttpStatus.BAD_REQUEST_400, 
						message = "Could not find requested SEI")})
		public Response getSei(@PathParam("seiUuid") @ApiParam(value = "Uuid of the SEI", required = true)  String seiUuid) {
			try {
				StructuralElementInstance sei = RepositoryUtility.findSei(seiUuid, repository);
				IBeanStructuralElementInstance beanSei = new BeanStructuralElementInstanceFactory().getInstanceFor(sei);
				return Response.ok(beanSei).build();
			} catch (CoreException e) {
				return createBadRequestResponse(e.getMessage());
			}
		}
		
		@PUT
		@Path(SEI)
		@Consumes(MediaType.APPLICATION_JSON)
		@ApiOperation(
				consumes = "application/json",
				value = "Put SEI",
				httpMethod = "PUT",
				notes = "This service updates an existing StructuralElementInstance")
		@ApiResponse(
				code = HttpStatus.OK_200,
				message = SUCCESSFUL_OPERATION)
		public Response putSei(@ApiParam(value = "SEI to put", required = true) ABeanStructuralElementInstance bean) {
			return Response.status(Response.Status.OK).build();
		}
	
	}
}
