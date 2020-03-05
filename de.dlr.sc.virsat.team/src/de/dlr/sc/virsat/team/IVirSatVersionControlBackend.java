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
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.runtime.IProgressMonitor;

public interface IVirSatVersionControlBackend {

	void commit(IProject project, String message, IProgressMonitor monitor) throws Exception;
	
	void checkout(IWorkspaceRoot wsRoot, String uri,  IProgressMonitor monitor) throws Exception;
	
	void checkin(IProject project, String uri,  IProgressMonitor monitor) throws Exception;

	void update(IProject project, IProgressMonitor monitor) throws Exception;

}
