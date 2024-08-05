/*******************************************************************************
 * Copyright (c) 2020 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
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

import jakarta.annotation.security.RolesAllowed;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.GenericEntity;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.EObject;
import de.dlr.sc.virsat.model.concept.types.factory.BeanStructuralElementInstanceFactory;
import de.dlr.sc.virsat.model.concept.types.roles.BeanDiscipline;
import de.dlr.sc.virsat.model.concept.types.structural.ABeanStructuralElementInstance;
import de.dlr.sc.virsat.model.dvlm.Repository;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.dvlm.concepts.registry.ActiveConceptConfigurationElement;
import de.dlr.sc.virsat.model.dvlm.concepts.util.ActiveConceptHelper;
import de.dlr.sc.virsat.model.dvlm.general.IAssignedDiscipline;
import de.dlr.sc.virsat.model.dvlm.roles.Discipline;
import de.dlr.sc.virsat.model.dvlm.roles.IUserContext;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElement;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import de.dlr.sc.virsat.model.dvlm.structural.util.StructuralInstantiator;
import de.dlr.sc.virsat.project.editingDomain.VirSatTransactionalEditingDomain;
import de.dlr.sc.virsat.project.structure.command.CreateAddSeiWithFileStructureCommand;
import de.dlr.sc.virsat.server.auth.ServerRoles;
import de.dlr.sc.virsat.server.dataaccess.ServerConcept;
import de.dlr.sc.virsat.server.repository.ServerRepository;
import de.dlr.sc.virsat.server.resources.ApiErrorHelper;
import de.dlr.sc.virsat.server.resources.model.CategoryAssignmentResource;
import de.dlr.sc.virsat.server.resources.model.DisciplineResource;
import de.dlr.sc.virsat.server.resources.model.PropertyResource;
import de.dlr.sc.virsat.server.resources.model.QudvResource;
import de.dlr.sc.virsat.server.resources.model.StructuralElementInstanceResource;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;

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
@SecurityScheme(
		  type = SecuritySchemeType.HTTP,
		  name = "basicAuth",
		  scheme = "basic")
@SecurityRequirement(name = "basicAuth")
//	@OpenAPIDefinition(
//		info = @Info(
//			version = VirSatModelAccessServlet.MODEL_API_VERSION,
//			title = "The Repo Model API",
//			description = "API to access a repository in the Virtual Satellite data model"
//		)
//	)
	//@Api(hidden = true, authorizations = {@Authorization(value = "basic")})
@RolesAllowed({ServerRoles.ADMIN, ServerRoles.USER})
public class ModelAccessResource {
		
	private Repository repository;
	private VirSatTransactionalEditingDomain ed;
	private ServerRepository serverRepository;
	private IUserContext userContext;
	private boolean synchronize;
	private boolean build;

	/**
	 * Constructor that holds all information of the request
	 * @param serverRepository of this concrete resource
	 * @param synchronize if synchronization is requested
	 * @param build if building is requested
	 * @param userContext the authenticated user
	 */
	public ModelAccessResource(ServerRepository serverRepository, boolean synchronize, boolean build, IUserContext userContext) {
		this.serverRepository = serverRepository;
		this.synchronize = synchronize;
		this.build = build;
		this.userContext = userContext;
		repository = serverRepository.getResourceSet().getRepository();
		ed = serverRepository.getEd();
	}

	/**
	 * Synchronize depending on the synchronize and build query parameter
	 * @throws Exception thrown if the synchronization fails
	 */
	public void synchronize() throws Exception {
		if (synchronize) {
			serverRepository.syncRepository(build);
			repository = serverRepository.getResourceSet().getRepository();
		}
	}

	// Public getters for subresources
//		@GET
//		@Operation(hidden = true, description = "")
	public Repository getRepository() {
		return repository;
	}
	
//		@GET
//		@Operation(hidden = true, description = "")
	public VirSatTransactionalEditingDomain getEd() {
		return ed;
	}
	
//		@GET
//		@Operation(hidden = true, description = "")
	public IUserContext getUser() {
		return userContext;
	}

	// Subresources
	@Path(RepositoryAccessResource.PROPERTY)
	public PropertyResource getPropertyResource() {
		return new PropertyResource(this);
	}

	@Path(RepositoryAccessResource.CA)
	public CategoryAssignmentResource getCategoryAssignmentResource() {
		return new CategoryAssignmentResource(this);
	}
	
	@Path(RepositoryAccessResource.SEI)
	public StructuralElementInstanceResource getStructuralElementInstanceResource() {
		return new StructuralElementInstanceResource(this);
	}
	
	@Path(RepositoryAccessResource.DISCIPLINE)
	public DisciplineResource getDisciplineResource() {
		return new DisciplineResource(this);
	}
	
	@Path(RepositoryAccessResource.QUDV)
	public QudvResource getQudvResource() {
		return new QudvResource(this);
	}
	
	// Actual resources
	/**
	 * This service forces a synchronization with the backend.
	 * @return ok iff no exception occurred during synchronization
	 */
	@GET
	@Path(RepositoryAccessResource.FORCE_SYNC)
	@Operation(
		summary = "Trigger synchronization with the backend",
		method = "GET",
		description = "This service forces a synchronization with the backend.",
		responses = {
			@ApiResponse(
				responseCode = ApiErrorHelper.SUCCESSFUL_OPERATION_CODE,
				description = ApiErrorHelper.SUCCESSFUL_OPERATION),
			@ApiResponse(
				responseCode = ApiErrorHelper.INTERNAL_SERVER_ERROR_CODE, 
				description = ApiErrorHelper.INTERNAL_SERVER_ERROR)
		})
	public Response forceSynchronize() {
		try {
			serverRepository.syncRepository(build);
		} catch (Exception e) {
			return ApiErrorHelper.createInternalErrorResponse(e.getMessage());
		}
		return Response.ok().build();
	}
	
	/**
	 * This service fetches the root StructuralElementInstances.
	 * It can be used as an entry point into the data model.
	 * @return the fetched root structural element instances
	 */
	@GET
	@Path(RepositoryAccessResource.ROOT_SEIS)
	@Produces(MediaType.APPLICATION_JSON)
	@Operation(
			summary = "Fetch a list of root SEIs",
			method = "GET",
			description = "This service fetches the root StructuralElementInstances. "
					+ "It can be used as an entry point into the data model.",
			responses = {
				@ApiResponse(
					responseCode = ApiErrorHelper.SUCCESSFUL_OPERATION_CODE,
					content = {
				        @Content(
					          mediaType = MediaType.APPLICATION_JSON,
					          array = @ArraySchema(schema = @Schema(implementation = ABeanStructuralElementInstance.class))
				        		)},
					description = ApiErrorHelper.SUCCESSFUL_OPERATION),
				@ApiResponse(
					responseCode = ApiErrorHelper.INTERNAL_SERVER_ERROR_CODE, 
					description = ApiErrorHelper.INTERNAL_SERVER_ERROR)
			})
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
			return ApiErrorHelper.createInternalErrorResponse(e.getMessage());
		}
	}
	
	/**
	 * This service creates a new root StructuralElementInstance and returns its uuid
	 * @param fullQualifiedName the full qualified name of the SEI type
	 * @return the created root sei
	 */
	@POST
	@Path(RepositoryAccessResource.ROOT_SEIS)
	@Consumes(MediaType.APPLICATION_JSON)
	@Operation(
			summary = "Create root SEI",
			method = "POST",
			description = "This service creates a new root StructuralElementInstance and returns its uuid",
			responses = {
				@ApiResponse(
					responseCode = ApiErrorHelper.SUCCESSFUL_OPERATION_CODE,
					content = 
				        @Content(
					          mediaType = MediaType.APPLICATION_JSON,
					          schema = @Schema(implementation = String.class)
					    ),
					description = ApiErrorHelper.SUCCESSFUL_OPERATION),
				@ApiResponse(
					responseCode = ApiErrorHelper.NOT_EXECUTEABLE_CODE, 
					description = ApiErrorHelper.NOT_EXECUTEABLE),
				@ApiResponse(
					responseCode = ApiErrorHelper.INTERNAL_SERVER_ERROR_CODE, 
					description = ApiErrorHelper.INTERNAL_SERVER_ERROR)
			})
	public Response createRootSei(@QueryParam(value = RepositoryAccessResource.QP_FULL_QUALIFIED_NAME) 
		@Parameter(description = "Full qualified name of the SEI type", required = true) String fullQualifiedName) {
		try {
			serverRepository.syncRepository();
			
			String newSeiUuid = createSeiFromFqn(fullQualifiedName, repository, ed, userContext);
			
			serverRepository.syncRepository();
			return Response.ok(newSeiUuid).build();
		} catch (Exception e) {
			return ApiErrorHelper.createInternalErrorResponse(e.getMessage());
		}
	}

	/**
	 * This service fetches the active Concepts
	 * @param onlyActiveConcepts only fetch the active concepts
	 * @return fetched list of concepts
	 */
	@GET
	@Path(RepositoryAccessResource.CONCEPTS)
	@Produces(MediaType.APPLICATION_JSON)
	@Operation(
		summary = "Fetch a list of active Concepts",
		method = "GET",
		description = "This service fetches the active Concepts.",
		responses = {
			@ApiResponse(
				responseCode = ApiErrorHelper.SUCCESSFUL_OPERATION_CODE,
				content = {
			        @Content(
				          mediaType = MediaType.APPLICATION_JSON,
				          array = @ArraySchema(schema = @Schema(implementation = ServerConcept.class))
			        		)},
				description = ApiErrorHelper.SUCCESSFUL_OPERATION),
			@ApiResponse(
				responseCode = ApiErrorHelper.INTERNAL_SERVER_ERROR_CODE, 
				description = ApiErrorHelper.INTERNAL_SERVER_ERROR)
		})
	public Response getConcepts(@DefaultValue("true") @QueryParam(RepositoryAccessResource.QP_ONLY_ACTIVE_CONCEPTS) boolean onlyActiveConcepts) {
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
			return ApiErrorHelper.createInternalErrorResponse(e.getMessage());
		}
	}
	
	/**
	 * This service fetches the existing Disciplines
	 * @return the fetched disciplines
	 */
	@GET
	@Path(RepositoryAccessResource.DISCIPLINES)
	@Produces(MediaType.APPLICATION_JSON)
	@Operation(
		summary = "Fetch a list of of all Disciplines",
		method = "GET",
		description = "This service fetches the existing Disciplines.",
		responses = {
			@ApiResponse(
				responseCode = ApiErrorHelper.SUCCESSFUL_OPERATION_CODE,
				content = {
			        @Content(
				          mediaType = MediaType.APPLICATION_JSON,
				          array = @ArraySchema(schema = @Schema(implementation = BeanDiscipline.class))
			        		)},
				description = ApiErrorHelper.SUCCESSFUL_OPERATION),
			@ApiResponse(
				responseCode = ApiErrorHelper.INTERNAL_SERVER_ERROR_CODE, 
				description = ApiErrorHelper.INTERNAL_SERVER_ERROR)
		})
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
			return ApiErrorHelper.createInternalErrorResponse(e.getMessage());
		}
	}
	
	/**
	 * This service fetches the discipline of the rolemanagement
	 * @return the fetched discipline
	 */
	@GET
	@Path(RepositoryAccessResource.ROLEMANAGEMENT)
	@Produces(MediaType.APPLICATION_JSON)
	@Operation(
		summary = "Fetch discipline of the rolemanagement",
		method = "GET",
		description = "This service fetches the discipline of the rolemanagement.",
		responses = {
			@ApiResponse(
				responseCode = ApiErrorHelper.SUCCESSFUL_OPERATION_CODE,
				content = {
			        @Content(
				          mediaType = MediaType.APPLICATION_JSON,
				          schema = @Schema(implementation = BeanDiscipline.class)
			        		)},
				description = ApiErrorHelper.SUCCESSFUL_OPERATION),
			@ApiResponse(
				responseCode = ApiErrorHelper.INTERNAL_SERVER_ERROR_CODE, 
				description = ApiErrorHelper.INTERNAL_SERVER_ERROR)
		})
	public Response getRolemanagementDiscipline() {
		try {
			synchronize();
			
			Discipline discipline = repository.getRoleManagement().getAssignedDiscipline();
			if (discipline == null) {
				return Response.ok(null).build();
			}
			
			return Response.ok(new BeanDiscipline(discipline)).build();
		} catch (Exception e) {
			return ApiErrorHelper.createInternalErrorResponse(e.getMessage());
		}
	}
	
	/**
	 * This service fetches the discipline of the repository
	 * @return the fetched discipline
	 */
	@GET
	@Path(RepositoryAccessResource.REPOSITORY)
	@Produces(MediaType.APPLICATION_JSON)
	@Operation(
			summary = "Fetch discipline of the repository",
			method = "GET",
			description = "This service fetches the discipline of the repository.",
			responses = {
				@ApiResponse(
					responseCode = ApiErrorHelper.SUCCESSFUL_OPERATION_CODE,
					content = {
				        @Content(
					          mediaType = MediaType.APPLICATION_JSON,
					          schema = @Schema(implementation = BeanDiscipline.class)
				        		)},
					description = ApiErrorHelper.SUCCESSFUL_OPERATION),
				@ApiResponse(
					responseCode = ApiErrorHelper.INTERNAL_SERVER_ERROR_CODE, 
					description = ApiErrorHelper.INTERNAL_SERVER_ERROR)
			})
	public Response getRepositpryDiscipline() {
		try {
			synchronize();
			
			Discipline discipline = repository.getAssignedDiscipline();
			if (discipline == null) {
				return Response.ok(null).build();
			}
			
			return Response.ok(new BeanDiscipline(discipline)).build();
		} catch (Exception e) {
			return ApiErrorHelper.createInternalErrorResponse(e.getMessage());
		}
	}

	/**
	 * Create a new Sei typed by the Se identified by the fullQualifiedName
	 * @param fullQualifiedName of the sei type (se)
	 * @param owner of the sei (either another sei or the repository for root seis)
	 * @param editingDomain the editing domain
	 * @param iUserContext the user context
	 * @return uuid of the created sei
	 */
	public static String createSeiFromFqn(String fullQualifiedName, EObject owner, VirSatTransactionalEditingDomain editingDomain, IUserContext iUserContext) {
		ActiveConceptHelper helper = new ActiveConceptHelper(editingDomain.getResourceSet().getRepository());
		StructuralElement se = helper.getStructuralElement(fullQualifiedName);
		StructuralElementInstance newSei = new StructuralInstantiator().generateInstance(se, null);
		
		if (owner instanceof IAssignedDiscipline) {
			Discipline parentDiscipline = ((IAssignedDiscipline) owner).getAssignedDiscipline();
			newSei.setAssignedDiscipline(parentDiscipline);
		}
		
		Command createCommand = CreateAddSeiWithFileStructureCommand.create(editingDomain, owner, newSei);
		ApiErrorHelper.executeCommandIffCanExecute(createCommand, editingDomain, iUserContext);
		
		return newSei.getUuid().toString();
	}

}