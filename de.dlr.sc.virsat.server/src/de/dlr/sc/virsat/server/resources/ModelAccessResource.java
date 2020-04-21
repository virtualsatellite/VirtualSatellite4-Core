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


import java.io.IOException;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.eclipse.core.runtime.CoreException;

import de.dlr.sc.virsat.server.controller.RepoModelAccessController;
import de.dlr.sc.virsat.server.dataaccess.FlattenedStructuralElementInstance;
import de.dlr.sc.virsat.server.repository.RepoRegistry;
import de.dlr.sc.virsat.server.repository.ServerRepository;

@Path(ModelAccessResource.PATH)
public class ModelAccessResource {
	
	public static final String PATH = "/repository";
	
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
			return new RepoModelAccessResource(repoName, repo);
		}
		
		return null;
	}
	
	public static final String ROOT_SEIS = "seis";
	public static final String SEI = "sei";
	public static final String DISCIPLINES = "disciplines";
	public static final String CONCEPTS = "concepts";
	public static final String CA = "ca";
	
	public class RepoModelAccessResource {
		private RepoModelAccessController controller;
		
		public RepoModelAccessResource(String repoName, ServerRepository serverRepository) {
			controller = new RepoModelAccessController(serverRepository.getEd());
		}

		
		@GET
		@Path(ROOT_SEIS)
		@Produces(MediaType.APPLICATION_JSON)
		public Response getRootSeis() {
			return Response.status(Response.Status.OK).entity(controller.getRootSeis()).build();
		}
		
		@GET
		@Path(SEI + "/{seiUuid}")
		@Produces(MediaType.APPLICATION_JSON)
		public Response getRootSeis(@PathParam("seiUuid") String seiUuid) {
			try {
				return Response.status(Response.Status.OK).entity(controller.getSei(seiUuid)).build();
			} catch (CoreException e) {
				return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
			}
		}
		
		/**
		 * Resource to post a sei
		 * @param flatSei FlattenedStructuralElementInstance
		 * @return Response containing the uuid of the new sei
		 */
		@POST
		@Path(SEI)
		@Produces(MediaType.APPLICATION_JSON)
		@Consumes(MediaType.APPLICATION_JSON)
		public Response postSei(FlattenedStructuralElementInstance flatSei) {
			try {
				String uuid = controller.postSei(flatSei);
				return Response.status(Response.Status.OK).entity(uuid).build();
			} catch (InstantiationException | IllegalAccessException | CoreException | IOException e) {
				return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
			}
		}
		
		@GET
		@Path(DISCIPLINES)
		@Produces(MediaType.APPLICATION_JSON)
		public Response getDisciplines() {
			return Response.status(Response.Status.OK).entity(controller.getDisciplines()).build();
		}
		
		@GET
		@Path(CONCEPTS)
		@Produces(MediaType.APPLICATION_JSON)
		public Response getConcepts() {
			return Response.status(Response.Status.OK).entity(controller.getActiveConcepts()).build();
		}
		
		@GET
		@Path(CA  + "/{caUuid}")
		@Produces(MediaType.APPLICATION_JSON)
		public Response getCa(@PathParam("caUuid") String caUuid) {
			try {
				return Response.status(Response.Status.OK).entity(controller.getCa(caUuid)).build();
			} catch (CoreException e) {
				return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
			}
		}
	}
}