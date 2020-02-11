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


import java.io.File;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import org.eclipse.core.resources.ResourcesPlugin;

import de.dlr.sc.virsat.server.dataaccess.VirSatGitAccess;


@Path(WorkspaceAccessResource.PATH_PERSISTENCE)
public class WorkspaceAccessResource {
	
	public static final String PATH_PERSISTENCE = "/persistence";
	public static final String PATH_CLONE = "/clone";
	public static final String PATH_COMMIT = "/commit";
	public static final String PATH_UPDATE = "/update";
	public static final String PARAM_REMOTE = "remote";
	public static final String PARAM_LOCAL = "local";
	public static final String PARAM_USER = "user";
	public static final String PARAM_MESSAGE = "message";

	private File workspaceRoot;
	
	public WorkspaceAccessResource() {
		workspaceRoot = ResourcesPlugin.getWorkspace().getRoot().getLocation().toFile();
	}
	
	@GET
	@Path(WorkspaceAccessResource.PATH_CLONE)
	public String clone(@QueryParam(WorkspaceAccessResource.PARAM_REMOTE) String uri,
						@QueryParam(WorkspaceAccessResource.PARAM_USER) String user,
						@QueryParam(WorkspaceAccessResource.PARAM_LOCAL) String localRoot) {
		return new WorkspaceUserContext(user, localRoot).runInContext((context, wuContext) -> 
				new VirSatGitAccess(workspaceRoot).cloneRepository(uri, wuContext.getRepoPath()));
	}
	
	@GET
	@Path(WorkspaceAccessResource.PATH_COMMIT)
	public String commit(@QueryParam(WorkspaceAccessResource.PARAM_USER) String user,
						 @QueryParam(WorkspaceAccessResource.PARAM_LOCAL) String localdirectory,
						 @QueryParam(WorkspaceAccessResource.PARAM_MESSAGE) String message) {
		// In terms of Virtual Satellite a commit on a git Repository is both,
		// adding all files to the stage, committing them and pushing the changes
		// to the upstream repository
		return new WorkspaceUserContext(user, localdirectory).runInContext((context, wuContext) -> 
				new VirSatGitAccess(workspaceRoot).commit(wuContext.getRepoPath(), message));
	}

	@GET
	@Path(WorkspaceAccessResource.PATH_UPDATE)
	public String update(@QueryParam(WorkspaceAccessResource.PARAM_USER) String user,
						 @QueryParam(WorkspaceAccessResource.PARAM_LOCAL) String localdirectory) {
		// In terms of Virtual Satellite update means pulling first
		// and then fetching all the changes into the local branch
		return new WorkspaceUserContext(user, localdirectory).runInContext((context, wuContext) -> 
			new VirSatGitAccess(workspaceRoot).update(wuContext.getRepoPath()));
	}
}