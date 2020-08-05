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


import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import de.dlr.sc.virsat.model.concept.types.property.BeanPropertyString;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.PropertyinstancesFactory;
import de.dlr.sc.virsat.server.CustomJsonProvider;
import de.dlr.sc.virsat.server.repository.RepoRegistry;
import de.dlr.sc.virsat.server.repository.ServerRepository;

/**
 * The resource to access the VirSat data model of a server repository
 * Provides an endpoint to access a repository
 */
@Path(ModelAccessResource.PATH)
public class ModelAccessResource {

	CustomJsonProvider provider;
	
	public static final String PATH = "/repository";

	public static final String ROOT_SEIS = "seis";
	public static final String SEI = "sei";
	public static final String DISCIPLINES = "disciplines";
	public static final String CONCEPTS = "concepts";
	public static final String CA = "ca";
	public static final String CA_AND_PROPERTIES = "caAndProperties";
	public static final String PROPERTY = "property";

	@Inject
	public ModelAccessResource(CustomJsonProvider provider) { 
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
			provider.setResourceSet(repo.getResourceSet());
			return new RepoModelAccessResource();
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
	
		@GET
		@Path(PROPERTY)
		@Produces(MediaType.APPLICATION_JSON)
		public BeanPropertyString getString() {
			return new BeanPropertyString(PropertyinstancesFactory.eINSTANCE.createValuePropertyInstance());
		}
	
		@POST
		@Path(PROPERTY)
		@Consumes(MediaType.APPLICATION_JSON)
		public Response putString(BeanPropertyString bean) {
			return Response.status(Response.Status.OK).build();
		}
	
	}
}