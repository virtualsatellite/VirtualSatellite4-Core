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

import de.dlr.sc.virsat.server.data.GitAccess;

@Path("/persistence")
public class WorkspaceAccessResource {

	@GET
	@Path("/clone")
	public String clone(@PathParam("uri") String uri) {
		return GitAccess.getInstance().cloneRepository(uri);
	}
}