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
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;


/**
 * This class handles a context for user calls to the server.
 * the idea is that one user can only issue one action on the server
 * per time.
 *
 */
public class WorkspaceUserContext {
	
	private String user;
	private String localRepoPath;
	
	private static Map<String, Object> workspaceContexts = Collections.synchronizedMap(new HashMap<>());
	
	/**
	 * Constructor for the Context
	 * @param user the user for the context
	 * @param localRepoPath the local path where the user is working on
	 */
	public WorkspaceUserContext(String user, String localRepoPath) {
		this.user = user;
		this.localRepoPath = localRepoPath;
	}
	
	/**
	 * This method returns the combined path for the user and local path
	 * @return the combined path starting with the user
	 */
	public String getRepoPath() {
		return getRepoPathFile().toString();
	}
	
	/**
	 * This method returns the combined path for the user and local path
	 * @return the combined path starting with the user
	 */
	public File getRepoPathFile() {
		File userPath = new File(this.user);
		File combinedPath = new File(userPath, localRepoPath);
		return combinedPath;
	}
	
	/**
	 * This method hands back the context object for locking
	 * @return an Object for locking
	 */
	public synchronized Object getContext() {
		Object context = workspaceContexts.get(user);
		if (context == null) {
			context = new Object();
			workspaceContexts.put(user, context);
		}
		return context;
	}
	
	/**
	 * Call this method to execute code within the context
	 * @param workspaceExecution AN implementation to be called within the context
	 * @return A String as the result of the code executed in the context
	 */
	public String runInContext(IWorkspaceUserContextExecution workspaceExecution) {
		Object context = getContext();
		
		String result = null;
		synchronized (context) {
			result = workspaceExecution.run(context, this);
		}

		return result;
	}
	
	/**
	 * Interface for implementing code that should be executed in the
	 * context.
	 *
	 */
	interface IWorkspaceUserContextExecution {
		/**
		 * Method that gets called in the locked context
		 * @param context the context that was used for locking
		 * @param workspaceUserContext The current Workspace User Context.
		 * @return A String from executing the within the context
		 */
		String run(Object context, WorkspaceUserContext workspaceUserContext);
	};
}