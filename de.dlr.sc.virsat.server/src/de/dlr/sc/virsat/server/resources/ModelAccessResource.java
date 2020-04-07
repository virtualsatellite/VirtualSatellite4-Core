/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.server.resources;


import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import de.dlr.sc.virsat.server.controller.RepoModelAccessController;
import de.dlr.sc.virsat.server.repository.RepoRegistry;
import de.dlr.sc.virsat.server.repository.ServerRepository;

@Path(ModelAccessResource.PATH)
public class ModelAccessResource {
	
	public static final String PATH = "/repository";
	
	private RepoModelAccessController controller;
	
//	public ModelAccessResource(String repoName, ServerRepository serverRepository) {
//		// TODO: catch not checked out repo (no ed or no virsat project)
//		controller = new RepoModelAccessController(serverRepository.getEd());
//	}
	
	public ModelAccessResource() { }
	
	@Path("{repoName}")
	public ConcreteRepoAccessResource getConcreteResource(@PathParam("repoName") String repoName) {
		ServerRepository repo = RepoRegistry.getInstance().getRepository(repoName);
		if (repo != null) {
			return new ConcreteRepoAccessResource(repoName, repo);
		}
		
		return null;
	}
	
	
	
	public class ConcreteRepoAccessResource {
		private RepoModelAccessController controller;
		private String name;
		
		public ConcreteRepoAccessResource(String repoName, ServerRepository serverRepository) {
			// TODO: catch not checked out repo (no ed or no virsat project)
			name = repoName;
			controller = new RepoModelAccessController(serverRepository.getEd());
		}
		
		@GET
		@Produces(MediaType.APPLICATION_JSON)
		public String hello() {
			return name;
		}
	}
}