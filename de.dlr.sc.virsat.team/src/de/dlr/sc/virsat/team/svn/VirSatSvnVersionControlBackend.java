/*******************************************************************************
 * Copyright (c) 2020 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.team.svn;

import java.io.File;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.team.svn.core.connector.SVNDepth;
import org.eclipse.team.svn.core.connector.SVNEntryInfo;
import org.eclipse.team.svn.core.operation.IConsoleStream;
import org.eclipse.team.svn.core.operation.local.CommitOperation;
import org.eclipse.team.svn.core.operation.local.UpdateOperation;
import org.eclipse.team.svn.core.operation.remote.CheckoutAsOperation;
import org.eclipse.team.svn.core.resource.IRepositoryResource;
import org.eclipse.team.svn.core.utility.SVNUtility;

import de.dlr.sc.virsat.team.IVirSatVersionControlBackend;

public class VirSatSvnVersionControlBackend implements IVirSatVersionControlBackend {

	@Override
	public void commit(IProject project, String message, IProgressMonitor monitor) throws Exception {
		SubMonitor commitMonitor = SubMonitor.convert(monitor, "Virtual Satellite svn commit", 1);
		
		// The signature requires the project to be passed as an array
		IResource[] projects = { project };
		CommitOperation commitOperation = new CommitOperation(projects, message, true, true);
		commitOperation.run(commitMonitor);
		
		File file = new File(project.getRawLocationURI().getRawPath());
		SVNEntryInfo test = SVNUtility.getSVNInfo(file);
		
		commitMonitor.split(1);
	}

	@Override
	public void checkout(IProjectDescription projectDescription, String remoteUri, IProgressMonitor monitor)
			throws Exception {
		SubMonitor checkoutMonitor = SubMonitor.convert(monitor, "Virtual Satellite svn checkout", 1);
		File pathRepoLocal = new File(projectDescription.getLocationURI());
		IRepositoryResource svnRepositoryResource = SVNUtility.asRepositoryResource(remoteUri, true);
		
		CheckoutAsOperation checkoutAsOperation = new CheckoutAsOperation(pathRepoLocal.getName(), 
				svnRepositoryResource, SVNDepth.INFINITY, true);
		checkoutAsOperation.run(checkoutMonitor);

		checkoutMonitor.split(1);
	}

	@Override
	public void checkin(IProject project, String remoteUri, IProgressMonitor monitor) throws Exception {
		// TODO Auto-generated method stub
	}

	@Override
	public void update(IProject project, IProgressMonitor monitor) throws Exception {
		SubMonitor updateMonitor = SubMonitor.convert(monitor, "Virtual Satellite svn update", 1);
		
		// The signature requires the project to be passed as an array
		IResource[] projects = { project };
		UpdateOperation updateOperation = new UpdateOperation(projects, true);
		updateOperation.run(updateMonitor);
		
		updateMonitor.split(1);
	}

}

