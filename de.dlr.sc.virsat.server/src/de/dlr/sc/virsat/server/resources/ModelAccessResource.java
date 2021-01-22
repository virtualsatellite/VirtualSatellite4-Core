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
import io.swagger.annotations.Info;
import io.swagger.annotations.SwaggerDefinition;

/**
 * The resource to access the VirSat data model of a server repository
 * Provides an endpoint to access a repository
 */
@Api(tags = {"Model"})
@SwaggerDefinition(
	info = @Info(
		version = VirSatModelAccessServlet.MODEL_API_VERSION,
		title = "The Model API"
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
	public RepoModelAccessResource getConcreteResource(@PathParam("repoName") String repoName) {
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
	@Api(hidden = true)
	@RolesAllowed({ServerRoles.ADMIN, ServerRoles.USER})
	public static class RepoModelAccessResource {
	
		private Repository repository;
		
		public RepoModelAccessResource(Repository repository) {
			this.repository = repository;
		}
		
		private Response createBadRequestResponse(String msg) {
			return Response.status(Response.Status.BAD_REQUEST).entity(msg).build();
		}

		@ApiOperation(hidden = true, value = "")
		@GET
		@Path(PROPERTY + "/{propertyUuid}")
		@Produces(MediaType.APPLICATION_JSON)
		public Response getProperty(@PathParam("propertyUuid") String propertyUuid) {
			return Response.status(Response.Status.OK).entity(
					new BeanPropertyFactory().getInstanceFor(
							RepositoryUtility.findProperty(propertyUuid, repository)
					)).build();
		}
		
		@ApiOperation(hidden = true, value = "")
		@PUT
		@Path(PROPERTY)
		@Consumes(MediaType.APPLICATION_JSON)
		public Response putProperty(ABeanProperty<?, ?> bean) {
			return Response.status(Response.Status.OK).build();
		}
		
		/**
		 * Returns a response with the category assignment with the caUuid
		 * @param caUuid uuid of the category assignment
		 * @return a server response
		 */
		@GET
		@Path(CA + "/{caUuid}")
		@Produces(MediaType.APPLICATION_JSON)

		@ApiOperation(
				produces = "application/json",
				value = "Fetch CA",
				httpMethod = "GET",
				notes = "This service fetches CAs")
		@ApiResponses(value = { 
				@ApiResponse(
						code = HttpStatus.OK_200,
						response = ABeanCategoryAssignment.class,
						message = "Successful operation"),
				@ApiResponse(
						code = HttpStatus.BAD_REQUEST_400, 
						message = "Bad Request")})
		public Response getCa(@PathParam("caUuid") String caUuid) {
			try {
				return Response.status(Response.Status.OK).entity(
						new BeanCategoryAssignmentFactory().getInstanceFor(
								RepositoryUtility.findCa(caUuid, repository)
						)).build();
			} catch (CoreException e) {
				return createBadRequestResponse(e.getMessage());
			}
		}
		
		@ApiOperation(
				produces = "application/json",
				value = "Put CA",
				httpMethod = "PUT",
				notes = "This service puts CAs")
		@ApiResponse(
				code = HttpStatus.OK_200,
				message = "Successful operation")
		@PUT
		@Path(CA)
		@Consumes(MediaType.APPLICATION_JSON)
		public Response putCa(@ApiParam(value = "CA to put", required = true) ABeanCategoryAssignment bean) {
			return Response.status(Response.Status.OK).build();
		}
		
		/**
		 * Returns a response with a list of the root seis
		 * @return a server response
		 */
		@ApiOperation(hidden = true, value = "")
		@GET
		@Path(ROOT_SEIS)
		@Produces(MediaType.APPLICATION_JSON)
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
		
		/**
		 * Returns a response with the sei with the seiUuid
		 * @param seiUuid uuid of the sei
		 * @return a server response
		 */
		@ApiOperation(hidden = true, value = "")
		@GET
		@Path(SEI + "/{seiUuid}")
		@Produces(MediaType.APPLICATION_JSON)
		public Response getSei(@PathParam("seiUuid") String seiUuid) {
			try {
				StructuralElementInstance sei = RepositoryUtility.findSei(seiUuid, repository);
				IBeanStructuralElementInstance beanSei = new BeanStructuralElementInstanceFactory().getInstanceFor(sei);
				return Response.ok(beanSei).build();
			} catch (CoreException e) {
				return createBadRequestResponse(e.getMessage());
			}
		}
		
		@PUT
		@ApiOperation(hidden = true, value = "")
		@Path(SEI)
		@Consumes(MediaType.APPLICATION_JSON)
		public Response putSei(ABeanStructuralElementInstance bean) {
			return Response.status(Response.Status.OK).build();
		}
	
	}
}
