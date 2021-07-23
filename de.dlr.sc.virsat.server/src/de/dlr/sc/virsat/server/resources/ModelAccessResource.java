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
import java.util.Arrays;
import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jetty.http.HttpStatus;

import de.dlr.sc.virsat.model.concept.types.factory.BeanStructuralElementInstanceFactory;
import de.dlr.sc.virsat.model.concept.types.roles.BeanDiscipline;
import de.dlr.sc.virsat.model.concept.types.structural.ABeanStructuralElementInstance;
import de.dlr.sc.virsat.model.dvlm.Repository;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.dvlm.concepts.registry.ActiveConceptConfigurationElement;
import de.dlr.sc.virsat.model.dvlm.concepts.util.ActiveConceptHelper;
import de.dlr.sc.virsat.model.dvlm.roles.Discipline;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElement;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import de.dlr.sc.virsat.model.dvlm.structural.util.StructuralInstantiator;
import de.dlr.sc.virsat.project.editingDomain.VirSatTransactionalEditingDomain;
import de.dlr.sc.virsat.project.structure.command.CreateAddSeiWithFileStructureCommand;
import de.dlr.sc.virsat.server.auth.ServerRoles;
import de.dlr.sc.virsat.server.dataaccess.ServerConcept;
import de.dlr.sc.virsat.server.dataaccess.TransactionalJsonProvider;
import de.dlr.sc.virsat.server.jetty.VirSatJettyServer;
import de.dlr.sc.virsat.server.repository.RepoRegistry;
import de.dlr.sc.virsat.server.repository.ServerRepository;
import de.dlr.sc.virsat.server.resources.modelaccess.CategoryAssignmentResource;
import de.dlr.sc.virsat.server.resources.modelaccess.DisciplineResource;
import de.dlr.sc.virsat.server.resources.modelaccess.PropertyResource;
import de.dlr.sc.virsat.server.resources.modelaccess.QudvResource;
import de.dlr.sc.virsat.server.resources.modelaccess.StructuralElementInstanceResource;
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
@Api(authorizations = {@Authorization(value = "basic")})
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
	public static final String DISCIPLINE = "discipline";
	public static final String ROLEMANAGEMENT = "rolemanagement";
	public static final String CONCEPTS = "concepts";
	public static final String CA = "ca";
	public static final String CA_AND_PROPERTIES = "caAndProperties";
	public static final String PROPERTY = "property";
	public static final String FORCE_SYNC = "forceSync";
	public static final String QUDV = "qudv";
	
	public static final String QP_ONLY_ACTIVE_CONCEPTS = "onlyActiveConcepts";
	public static final String QP_FULL_QUALIFIED_NAME = "fullQualifiedName";
	public static final String QP_SYNC = "sync";
	public static final String QP_BUILD = "build";

	// List of all resource classes used in this class
	// Used to register model specific filters
	public static final List<?> RESOURCE_CLASSES = Arrays.asList(
			RepoModelAccessResource.class,
			StructuralElementInstanceResource.class,
			CategoryAssignmentResource.class,
			PropertyResource.class);
	
	public ModelAccessResource() { }
	
	/**
	 * Get the ServerRepository corresponding to the repoName and create a new RepoModelAccessResource
	 * @param repoName of the repository to be accessed by the request
	 * @return RepoModelAccessResource or null if the repo is not found
	 */
	@Path("{repoName}")
	public RepoModelAccessResource getConcreteResource(
			@PathParam("repoName") @ApiParam(value = "Name of the repository", required = true) String repoName,
			
			@ApiParam(value = "Synchronize with the repository on this request", required = false)
			@QueryParam(ModelAccessResource.QP_SYNC) @DefaultValue("true") boolean synchronize,
			
			@ApiParam(value = "Build when synchronizing on this request", required = false)
			@QueryParam(ModelAccessResource.QP_BUILD) @DefaultValue("true") boolean build) {
		
		ServerRepository repo = RepoRegistry.getInstance().getRepository(repoName);
		if (repo != null) {
			provider.setServerRepository(repo);
			return new RepoModelAccessResource(repo, synchronize, build);
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
	@Api(hidden = true, authorizations = {@Authorization(value = "basic")})
	@RolesAllowed({ServerRoles.ADMIN, ServerRoles.USER})
	public static class RepoModelAccessResource {
		
		private Repository repository;
		private VirSatTransactionalEditingDomain ed;
		private ServerRepository serverRepository;
		private boolean synchronize;
		private boolean build;

		public RepoModelAccessResource(ServerRepository serverRepository, boolean synchronize, boolean build) {
			this.serverRepository = serverRepository;
			this.synchronize = synchronize;
			this.build = build;
			repository = serverRepository.getResourceSet().getRepository();
			ed = serverRepository.getEd();
		}

		/**
		 * Synchronize depending on the synchronize and build query parameter
		 * @throws Exception
		 */
		public void synchronize() throws Exception {
			if (synchronize) {
				serverRepository.syncRepository(build);
			}
		}

		// Public getters for subresources
		@ApiOperation(hidden = true, value = "")
		public Repository getRepository() {
			return repository;
		}
		
		@ApiOperation(hidden = true, value = "")
		public VirSatTransactionalEditingDomain getEd() {
			return ed;
		}

		// Subresources
		@Path(PROPERTY)
		public PropertyResource getPropertyResource() {
			return new PropertyResource(this);
		}

		@Path(CA)
		public CategoryAssignmentResource getCategoryAssignmentResource() {
			return new CategoryAssignmentResource(this);
		}
		
		@Path(SEI)
		public StructuralElementInstanceResource getStructuralElementInstanceResource() {
			return new StructuralElementInstanceResource(this);
		}
		
		@Path(DISCIPLINE)
		public DisciplineResource getDisciplineResource() {
			return new DisciplineResource(this);
		}
		
		@Path(QUDV)
		public QudvResource getQudvResource() {
			return new QudvResource(this);
		}
		
		// Actual resources
		/** **/
		@GET
		@Path(FORCE_SYNC)
		@ApiOperation(
				value = "Triggers synchronization with the backend",
				httpMethod = "GET",
				notes = "This service forces a synchronization with the backend.")
		@ApiResponses(value = { 
				@ApiResponse(
						code = HttpStatus.OK_200,
						message = ApiErrorHelper.SUCCESSFUL_OPERATION),
				@ApiResponse(
						code = HttpStatus.INTERNAL_SERVER_ERROR_500, 
						message = ApiErrorHelper.SYNC_ERROR)})
		public Response forceSynchronize() {
			try {
				serverRepository.syncRepository(build);
			} catch (Exception e) {
				return ApiErrorHelper.createSyncErrorResponse(e.getMessage());
			}
			return Response.ok().build();
		}
		
		/** **/
		@GET
		@Path(ROOT_SEIS)
		@Produces(MediaType.APPLICATION_JSON)
		@ApiOperation(
				produces = "application/json",
				value = "Fetch a list of root SEIs",
				httpMethod = "GET",
				notes = "This service fetches the root StructuralElementInstances. "
						+ "It can be used as an entry point into the data model.")
		@ApiResponses(value = { 
				@ApiResponse(
						code = HttpStatus.OK_200,
						response = ABeanStructuralElementInstance.class,
						responseContainer = "List",
						message = ApiErrorHelper.SUCCESSFUL_OPERATION),
				@ApiResponse(
						code = HttpStatus.INTERNAL_SERVER_ERROR_500, 
						message = ApiErrorHelper.SYNC_ERROR)})
		public Response getRootSeis() {
			try {
				synchronize();
				
				List<StructuralElementInstance> rootSeis = repository.getRootEntities();
				List<ABeanStructuralElementInstance> beans = new ArrayList<ABeanStructuralElementInstance>();
				
				for (StructuralElementInstance sei : rootSeis) {
					beans.add((ABeanStructuralElementInstance) new BeanStructuralElementInstanceFactory().getInstanceFor(sei));
				}
				
				GenericEntity<List<ABeanStructuralElementInstance>> genericEntityList =
						new GenericEntity<List<ABeanStructuralElementInstance>>(beans) { };
				
				return Response.ok(genericEntityList).build();
			} catch (Exception e) {
				return ApiErrorHelper.createSyncErrorResponse(e.getMessage());
			}
		}
		
		/** **/
		@POST
		@Path(ROOT_SEIS)
		@Consumes(MediaType.APPLICATION_JSON)
		@ApiOperation(
				consumes = "application/json",
				value = "Create root SEI",
				httpMethod = "POST",
				notes = "This service creates a new root StructuralElementInstance and returns its uuid")
		@ApiResponses(value = { 
				@ApiResponse(
						code = HttpStatus.OK_200,
						response = String.class,
						message = ApiErrorHelper.SUCCESSFUL_OPERATION),
				@ApiResponse(
						code = HttpStatus.INTERNAL_SERVER_ERROR_500, 
						message = ApiErrorHelper.SYNC_ERROR)})
		public Response createRootSei(@QueryParam(value = ModelAccessResource.QP_FULL_QUALIFIED_NAME) 
			@ApiParam(value = "Full qualified name of the SEI type", required = true) String fullQualifiedName) {
			try {
				serverRepository.syncRepository();
				
				String newSeiUuid = createSeiFromFqn(fullQualifiedName, repository, ed);
				
				serverRepository.syncRepository();
				return Response.ok(newSeiUuid).build();
			} catch (Exception e) {
				return ApiErrorHelper.createSyncErrorResponse(e.getMessage());
			}
		}

		/** **/
		@GET
		@Path(CONCEPTS)
		@Produces(MediaType.APPLICATION_JSON)
		@ApiOperation(
				produces = "application/json",
				value = "Fetch a list of active Concepts",
				httpMethod = "GET",
				notes = "This service fetches the active Concepts")
		@ApiResponses(value = { 
				@ApiResponse(
						code = HttpStatus.OK_200,
						response = ServerConcept.class,
						responseContainer = "List",
						message = ApiErrorHelper.SUCCESSFUL_OPERATION),
				@ApiResponse(
						code = HttpStatus.INTERNAL_SERVER_ERROR_500, 
						message = ApiErrorHelper.SYNC_ERROR)})
		public Response getConcepts(@DefaultValue("true") @QueryParam(QP_ONLY_ACTIVE_CONCEPTS) boolean onlyActiveConcepts) {
			try {
				synchronize();
				
				List<ServerConcept> pojos = new ArrayList<ServerConcept>();
				List<Concept> concepts;
				
				if (onlyActiveConcepts) {
					concepts = repository.getActiveConcepts();
				} else {
					// Get all concepts from the registry
					concepts = new ArrayList<Concept>();
					IExtensionRegistry registry = Platform.getExtensionRegistry();
					String extensionPoint = ActiveConceptConfigurationElement.EXTENSION_POINT_ID_CONCEPT;
					
					// Get the concept configuration elements
					IConfigurationElement[] configurationElementsArray = registry.getConfigurationElementsFor(extensionPoint);
					
					// For each configuration element load the concept
					for (IConfigurationElement configurationElement : configurationElementsArray) {
						ActiveConceptConfigurationElement acce = new ActiveConceptConfigurationElement(configurationElement);
						concepts.add(acce.loadConceptFromPlugin());
					}
				}
				
				for (Concept concept : concepts) {
					pojos.add(new ServerConcept(concept));
				}
				
				GenericEntity<List<ServerConcept>> entity = new GenericEntity<List<ServerConcept>>(pojos) { };
				
				return Response.ok(entity).build();
			} catch (Exception e) {
				return ApiErrorHelper.createSyncErrorResponse(e.getMessage());
			}
		}
		
		/** **/
		@GET
		@Path(DISCIPLINES)
		@Produces(MediaType.APPLICATION_JSON)
		@ApiOperation(
				produces = "application/json",
				value = "Fetch a list of all Disciplines",
				httpMethod = "GET",
				notes = "This service fetches the existing Disciplines")
		@ApiResponses(value = { 
				@ApiResponse(
						code = HttpStatus.OK_200,
						response = BeanDiscipline.class,
						responseContainer = "List",
						message = ApiErrorHelper.SUCCESSFUL_OPERATION),
				@ApiResponse(
						code = HttpStatus.INTERNAL_SERVER_ERROR_500, 
						message = ApiErrorHelper.SYNC_ERROR)})
		public Response getDisciplines() {
			try {
				synchronize();
				
				List<BeanDiscipline> pojos = new ArrayList<BeanDiscipline>();
				List<Discipline> disciplines = repository.getRoleManagement().getDisciplines();
				
				for (Discipline discipline : disciplines) {
					pojos.add(new BeanDiscipline(discipline));
				}
				
				GenericEntity<List<BeanDiscipline>> entity = new GenericEntity<List<BeanDiscipline>>(pojos) { };
				
				return Response.ok(entity).build();
			} catch (Exception e) {
				return ApiErrorHelper.createSyncErrorResponse(e.getMessage());
			}
		}
		
		/** **/
		@GET
		@Path(ROLEMANAGEMENT)
		@Produces(MediaType.APPLICATION_JSON)
		@ApiOperation(
				produces = "application/json",
				value = "Fetch discipline of the rolemanagement",
				httpMethod = "GET",
				notes = "This service fetches the discipline of the rolemanagement")
		@ApiResponses(value = { 
				@ApiResponse(
						code = HttpStatus.OK_200,
						response = BeanDiscipline.class,
						message = ApiErrorHelper.SUCCESSFUL_OPERATION),
				@ApiResponse(
						code = HttpStatus.INTERNAL_SERVER_ERROR_500, 
						message = ApiErrorHelper.SYNC_ERROR)})
		public Response getRolemanagementDiscipline() {
			try {
				synchronize();
				
				Discipline discipline = repository.getRoleManagement().getAssignedDiscipline();
				if (discipline == null) {
					return Response.ok(null).build();
				}
				
				return Response.ok(new BeanDiscipline(discipline)).build();
			} catch (Exception e) {
				return ApiErrorHelper.createSyncErrorResponse(e.getMessage());
			}
		}
	
	}
	
	/**
	 * Create a new Sei typed by the Se identified by the fullQualifiedName
	 * @param fullQualifiedName of the sei type (se)
	 * @param owner of the sei (either another sei or the repository for root seis)
	 * @param editingDomain
	 * @return uuid of the created sei
	 */
	public static String createSeiFromFqn(String fullQualifiedName, EObject owner, VirSatTransactionalEditingDomain editingDomain) {
		ActiveConceptHelper helper = new ActiveConceptHelper(editingDomain.getResourceSet().getRepository());
		StructuralElement se = helper.getStructuralElement(fullQualifiedName);
		StructuralElementInstance newSei = new StructuralInstantiator().generateInstance(se, null);
		
		Command createCommand = CreateAddSeiWithFileStructureCommand.create(editingDomain, owner, newSei);
		editingDomain.getCommandStack().execute(createCommand);
		
		return newSei.getUuid().toString();
	}
}
