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


import java.util.Arrays;
import java.util.List;

import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.SecurityContext;
import de.dlr.sc.virsat.model.dvlm.roles.IUserContext;
import de.dlr.sc.virsat.server.auth.ServerRoles;
import de.dlr.sc.virsat.server.auth.ServerUserContext;
import de.dlr.sc.virsat.server.dataaccess.TransactionalJsonProvider;
import de.dlr.sc.virsat.server.jetty.VirSatJettyServer;
import de.dlr.sc.virsat.server.repository.RepoRegistry;
import de.dlr.sc.virsat.server.repository.ServerRepository;
import de.dlr.sc.virsat.server.resources.model.CategoryAssignmentResource;
import de.dlr.sc.virsat.server.resources.model.DisciplineResource;
import de.dlr.sc.virsat.server.resources.model.PropertyResource;
import de.dlr.sc.virsat.server.resources.model.QudvResource;
import de.dlr.sc.virsat.server.resources.model.StructuralElementInstanceResource;
import de.dlr.sc.virsat.server.servlet.ModelAccessServletContainer;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;

/**
 * The resource to access the VirSat data model of a server repository
 * Provides an endpoint to access a repository
 */
@SecurityScheme(
	  type = SecuritySchemeType.HTTP,
	  name = "basicAuth",
	  scheme = "basic")
@SecurityRequirement(name = "basicAuth")
@OpenAPIDefinition(
	info = @Info(
		version = ModelAccessServletContainer.MODEL_API_VERSION,
		title = "The Model API",
		description = "API to access the Virtual Satellite repository and data model"
	),
	servers = {
		@Server(url = VirSatJettyServer.PATH + ModelAccessServletContainer.MODEL_API)
	}
)
@Path(RepositoryAccessResource.PATH)
@RolesAllowed({ServerRoles.ADMIN, ServerRoles.USER})
public class RepositoryAccessResource {

	@Inject
	TransactionalJsonProvider provider;
	
	public static final String PATH = "/repository";

	public static final String ROOT_SEIS = "seis";
	public static final String SEI = "sei";
	public static final String DISCIPLINES = "disciplines";
	public static final String DISCIPLINE = "discipline";
	public static final String ROLEMANAGEMENT = "rolemanagement";
	public static final String REPOSITORY = "modelRepository";
	public static final String CONCEPTS = "concepts";
	public static final String CA = "ca";
	public static final String CA_AND_PROPERTIES = "caAndProperties";
	public static final String PROPERTY = "property";
	public static final String FORCE_SYNC = "forceSync";
	public static final String QUDV = "qudv";
	
	public static final String QP_ONLY_ACTIVE_CONCEPTS = "onlyActiveConcepts";
	public static final String QP_FULL_QUALIFIED_NAME = "fullQualifiedName";
	public static final String QP_NAME = "name";
	public static final String QP_SYNC = "sync";
	public static final String QP_BUILD = "build";

	public static final String TAG_QUDV = "QUDV";
	public static final String TAG_SEI = "SEIs";
	public static final String TAG_CA = "CAs";
	public static final String TAG_PROPERTY = "Properties";
	public static final String TAG_DISCIPLINE = "Disciplines";

	// List of all resource classes used in this class
	// Used to register model specific filters
	public static final List<?> RESOURCE_CLASSES = Arrays.asList(
			ModelAccessResource.class,
			StructuralElementInstanceResource.class,
			CategoryAssignmentResource.class,
			PropertyResource.class,
			DisciplineResource.class,
			QudvResource.class);
	
	public RepositoryAccessResource() { }
	
	
	/**
	 * Get the ServerRepository corresponding to the repoName and create a new RepoModelAccessResource
	 * @param repoName of the repository to be accessed by the request
	 * @param synchronize if synchronization is requested
	 * @param build if building is requested
	 * @param sc the security context
	 * @return RepoModelAccessResource or null if the repo is not found
	 */
	@Path("{repoName}")
	public ModelAccessResource getConcreteResource(
			@PathParam("repoName") @Parameter(description = "Name of the repository", required = true) String repoName,
			
			@Parameter(description = "Synchronize with the repository on this request", required = false)
			@QueryParam(RepositoryAccessResource.QP_SYNC) @DefaultValue("true") boolean synchronize,
			
			@Parameter(description = "Build when synchronizing on this request", required = false)
			@QueryParam(RepositoryAccessResource.QP_BUILD) @DefaultValue("true") boolean build,
			@Context SecurityContext sc) {
		
		ServerRepository repo = RepoRegistry.getInstance().getRepository(repoName);
		if (repo != null) {
			provider.setServerRepository(repo);
			
			// Override current user with a custom user context
			IUserContext userContext = new ServerUserContext(sc);
			provider.setContext(userContext);
			
			return new ModelAccessResource(repo, synchronize, build, userContext);
		}
		
		return null;
	}
}
