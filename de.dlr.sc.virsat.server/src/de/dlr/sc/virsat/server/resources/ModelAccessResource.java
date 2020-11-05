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

import de.dlr.sc.virsat.model.concept.types.category.ABeanCategoryAssignment;
import de.dlr.sc.virsat.model.concept.types.factory.BeanCategoryAssignmentFactory;
import de.dlr.sc.virsat.model.concept.types.factory.BeanPropertyFactory;
import de.dlr.sc.virsat.model.concept.types.factory.BeanStructuralElementInstanceFactory;
import de.dlr.sc.virsat.model.concept.types.property.BeanPropertyBoolean;
import de.dlr.sc.virsat.model.concept.types.property.BeanPropertyComposed;
import de.dlr.sc.virsat.model.concept.types.property.BeanPropertyEnum;
import de.dlr.sc.virsat.model.concept.types.property.BeanPropertyFloat;
import de.dlr.sc.virsat.model.concept.types.property.BeanPropertyInt;
import de.dlr.sc.virsat.model.concept.types.property.BeanPropertyReference;
import de.dlr.sc.virsat.model.concept.types.property.BeanPropertyResource;
import de.dlr.sc.virsat.model.concept.types.property.BeanPropertyString;
import de.dlr.sc.virsat.model.concept.types.structural.ABeanStructuralElementInstance;
import de.dlr.sc.virsat.model.concept.types.structural.IBeanStructuralElementInstance;
import de.dlr.sc.virsat.model.dvlm.Repository;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import de.dlr.sc.virsat.project.editingDomain.VirSatTransactionalEditingDomain;
import de.dlr.sc.virsat.server.dataaccess.RepositoryUtility;
import de.dlr.sc.virsat.server.dataaccess.TransactionalJsonProvider;
import de.dlr.sc.virsat.server.repository.RepoRegistry;
import de.dlr.sc.virsat.server.repository.ServerRepository;

/**
 * The resource to access the VirSat data model of a server repository
 * Provides an endpoint to access a repository
 */
@Path(ModelAccessResource.PATH)
public class ModelAccessResource {

	TransactionalJsonProvider provider;
	
	public static final String PATH = "/repository";

	public static final String ROOT_SEIS = "seis";
	public static final String SEI = "sei";
	public static final String DISCIPLINES = "disciplines";
	public static final String CONCEPTS = "concepts";
	public static final String CA = "ca";
	public static final String CA_AND_PROPERTIES = "caAndProperties";
	public static final String PROPERTY = "property";
	
	public static final String BOOLEAN = "boolean";
	public static final String STRING = "string";
	public static final String INT = "int";
	public static final String FLOAT = "float";
	public static final String ENUM = "enum";
	public static final String RESOURCE = "resource";
	public static final String REFERENCE = "reference";
	public static final String COMPOSED = "composed";

	@Inject
	public ModelAccessResource(TransactionalJsonProvider provider) { 
		this.provider = provider;
	}
	
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
	public static class RepoModelAccessResource {
	
		private Repository repository;
		
		public RepoModelAccessResource(Repository repository) {
			this.repository = repository;
		}
		
		private Response createBadRequestResponse(String msg) {
			return Response.status(Response.Status.BAD_REQUEST).entity(msg).build();
		}
	
		@Path(PROPERTY)
		public PropertyResource accessProperty() {
			return new PropertyResource(repository);
		}
		
		/*
		 * A function for each property bean because 
		 * the generic definition with wildcards
		 * of a bean property (ABeanObject<? extends APropertyInstance)
		 * is not supported
		 * 
		 * If a new property should be supported 
		 * it has to be added here
		 */
		public static class PropertyResource {
			private Repository repository;
			
			public PropertyResource(Repository repository) {
				this.repository = repository;
			}
			
			@GET
			@Path("/{propertyUuid}")
			@Produces(MediaType.APPLICATION_JSON)
			public Response getProperty(@PathParam("propertyUuid") String propertyUuid) {
				return Response.status(Response.Status.OK).entity(
						new BeanPropertyFactory().getInstanceFor(
								RepositoryUtility.findProperty(propertyUuid, repository)
						)).build();
			}
			
			@PUT
			@Path(STRING)
			@Consumes(MediaType.APPLICATION_JSON)
			public Response putProperty(BeanPropertyString bean) {
				return Response.status(Response.Status.OK).build();
			}
			
			@PUT
			@Path(INT)
			@Consumes(MediaType.APPLICATION_JSON)
			public Response putProperty(BeanPropertyInt bean) {
				return Response.status(Response.Status.OK).build();
			}
			
			@PUT
			@Path(FLOAT)
			@Consumes(MediaType.APPLICATION_JSON)
			public Response putProperty(BeanPropertyFloat bean) {
				return Response.status(Response.Status.OK).build();
			}
			
			@PUT
			@Path(ENUM)
			@Consumes(MediaType.APPLICATION_JSON)
			public Response putProperty(BeanPropertyEnum bean) {
				return Response.status(Response.Status.OK).build();
			}
			
			@PUT
			@Path(RESOURCE)
			@Consumes(MediaType.APPLICATION_JSON)
			public Response putProperty(BeanPropertyResource bean) {
				return Response.status(Response.Status.OK).build();
			}
			
			@PUT
			@Path(BOOLEAN)
			@Consumes(MediaType.APPLICATION_JSON)
			public Response putProperty(BeanPropertyBoolean bean) {
				return Response.status(Response.Status.OK).build();
			}
			
			@PUT
			@Path(REFERENCE)
			@Consumes(MediaType.APPLICATION_JSON)
			public Response putProperty(@SuppressWarnings("rawtypes") BeanPropertyReference bean) {
				return Response.status(Response.Status.OK).build();
			}
			
			@PUT
			@Path(COMPOSED)
			@Consumes(MediaType.APPLICATION_JSON)
			public Response putProperty(@SuppressWarnings("rawtypes") BeanPropertyComposed bean) {
				return Response.status(Response.Status.OK).build();
			}
		}
		
		/**
		 * Returns a response with the category assignment with the caUuid
		 * @param caUuid uuid of the category assignment
		 * @return a server response
		 */
		@GET
		@Path(CA + "/{caUuid}")
		@Produces(MediaType.APPLICATION_JSON)
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
		
		@PUT
		@Path(CA)
		@Consumes(MediaType.APPLICATION_JSON)
		public Response putCa(ABeanCategoryAssignment bean) {
			return Response.status(Response.Status.OK).build();
		}
		
		/**
		 * Returns a response with a list of the root seis
		 * @return a server response
		 */
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
		@Path(SEI)
		@Consumes(MediaType.APPLICATION_JSON)
		public Response putSei(ABeanStructuralElementInstance bean) {
			return Response.status(Response.Status.OK).build();
		}
	
	}
}
