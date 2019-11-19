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
import javax.ws.rs.QueryParam;

import de.dlr.sc.virsat.server.data.GitAccess;

@Path("/persistence")
public class WorkspaceAccessResource {

	@GET
	@Path("/clone")
	public String clone(@QueryParam("uri") String uri,
						@QueryParam("username") String username,
						@QueryParam("password") String password) {
		return GitAccess.getInstance().cloneRepository(uri, username, password);
	}
	
	@GET
	@Path("/commit")
	public String commit() {
		// In terms of Virtual Satellite a commit on a git Repository is both,
		// adding all files to the stage, committing them and pushing the changes
		// to the upstream repository
		
		return "N/A";
	}

	@GET
	@Path("/update")
	public String update() {
		// In terms of Virtual Satellite update means pulling first
		// and then fetching all the changes into the local branch
		return "N/A";
	}
}