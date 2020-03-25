/*******************************************************************************
 * Copyright (c) 2020 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.team;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.runtime.IProgressMonitor;

/**
 * Interface for managing a version control backend.
 * The baseline level for manipulating the version control backend are projects.
 *
 */

public interface IVirSatVersionControlBackend {

	/**
	 * Commits any changes in a project to the version control system.
	 * @param project the project to update
	 * @param message the commit message
	 * @param monitor an update monitor
	 * @throws Exception
	 */
	void commit(IProject project, String message, IProgressMonitor monitor) throws Exception;
	
	/**
	 * Creates a local checkout of a project from the version control system
	 * @param projectDescription the project metadata
	 * @param remoteUri the uri of the version control system
	 * @param monitor an update monitor
	 * @throws Exception
	 */
	void checkout(IProjectDescription projectDescription, String remoteUri, IProgressMonitor monitor) throws Exception;
	
	/**
	 * Uploads a project to the version control system
	 * @param project the project to upload
	 * @param remoteUri the uri of the version control system
	 * @param monitor an update monitor
	 * @throws Exception
	 */
	void checkin(IProject project, String remoteUri, IProgressMonitor monitor) throws Exception;

	/**
	 * Gets all remote changes for a project from the version control system
	 * @param project the project to update
	 * @param monitor an update monitor
	 * @throws Exception
	 */
	void update(IProject project, IProgressMonitor monitor) throws Exception;

}
