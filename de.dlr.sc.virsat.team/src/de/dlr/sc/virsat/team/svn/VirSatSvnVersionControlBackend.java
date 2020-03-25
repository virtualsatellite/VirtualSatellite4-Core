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
import org.eclipse.team.svn.core.IStateFilter;
import org.eclipse.team.svn.core.connector.SVNDepth;
import org.eclipse.team.svn.core.operation.CompositeOperation;
import org.eclipse.team.svn.core.operation.file.CheckoutAsOperation;
import org.eclipse.team.svn.core.operation.local.AddToSVNOperation;
import org.eclipse.team.svn.core.operation.local.CommitOperation;
import org.eclipse.team.svn.core.operation.local.NotifyProjectStatesChangedOperation;
import org.eclipse.team.svn.core.operation.local.RefreshResourcesOperation;
import org.eclipse.team.svn.core.operation.local.UpdateOperation;
import org.eclipse.team.svn.core.operation.local.management.ShareProjectOperation;
import org.eclipse.team.svn.core.operation.local.management.ShareProjectOperation.IFolderNameMapper;
import org.eclipse.team.svn.core.resource.IRepositoryLocation;
import org.eclipse.team.svn.core.resource.IRepositoryResource;
import org.eclipse.team.svn.core.resource.IResourceProvider;
import org.eclipse.team.svn.core.resource.events.ProjectStatesChangedEvent;
import org.eclipse.team.svn.core.utility.FileUtility;
import org.eclipse.team.svn.core.utility.SVNUtility;

import de.dlr.sc.virsat.team.IVirSatVersionControlBackend;

public class VirSatSvnVersionControlBackend implements IVirSatVersionControlBackend {

	@Override
	public void commit(IProject project, String message, IProgressMonitor monitor) throws Exception {
		SubMonitor commitMonitor = SubMonitor.convert(monitor, "Virtual Satellite svn commit", 1);
		
		// The SVN commit operation requires the parent file to be under version control
		// Therefore we have to commit all the commtable files/folder directly under the project
		// We only get the files directly under the project and let SVN take care of recursively searching
		// for the contained files.
		IResource[] files = FileUtility.filterResources(project.members(IProject.NONE), IStateFilter.SF_UNVERSIONED);
		CommitOperation commitOperation = new CommitOperation(files, message, true, true);
		CompositeOperation compositeOperation = new CompositeOperation(commitOperation.getId(), commitOperation.getMessagesClass());
		// SVN requires adding files to the repository before commiting them
		compositeOperation.add(new AddToSVNOperation(files, true));
		compositeOperation.add(commitOperation);
		
		compositeOperation.run(commitMonitor);
		
		commitMonitor.split(1);
	}

	@Override
	public void checkout(IProjectDescription projectDescription, String remoteUri, IProgressMonitor monitor)
			throws Exception {
		SubMonitor checkoutMonitor = SubMonitor.convert(monitor, "Virtual Satellite svn checkout", 1);
		File pathRepoLocal = new File(projectDescription.getLocationURI());
		IRepositoryResource remoteRepo = SVNUtility.asRepositoryResource(remoteUri, true);

		CheckoutAsOperation checkoutAsOperation = new CheckoutAsOperation(pathRepoLocal, remoteRepo, SVNDepth.INFINITY, true, true);
		CompositeOperation compositeOperation = new CompositeOperation(checkoutAsOperation.getId(), checkoutAsOperation.getMessagesClass());
		compositeOperation.add(checkoutAsOperation);
		compositeOperation.add(new RefreshResourcesOperation(new IResourceProvider() {
			@Override
			public IResource[] getResources() {
				// Makes the SVN refresh operation update the meta information on the newly checked out project
				IProject newProject = ResourcesPlugin.getWorkspace().getRoot().getProject(projectDescription.getName());
				return new IResource[] { newProject };
			}
		}));
		
		compositeOperation.run(checkoutMonitor);
		
		checkoutMonitor.split(1);
	}

	@Override
	public void checkin(IProject project, String remoteUri, IProgressMonitor monitor) throws Exception {
		SubMonitor checkInMonitor = SubMonitor.convert(monitor, "Virtual Satellite svn init", 1);
		
		// Gets the last segment of the remote uri and maps so we can map
		// the local project name to the remote project name
		File remoteFile = new File(remoteUri);
		String remoteRepoProjectName = remoteFile.getName();
		
		IRepositoryLocation remoteRepoLocation = SVNUtility.asRepositoryResource(remoteUri, true).getRepositoryLocation();
		
		// The signature requires the project to be passed as an array
		IProject[] projects = { project };
		IFolderNameMapper folderNameMapper = (p -> remoteRepoProjectName);
		ShareProjectOperation shareProjectOperation = new ShareProjectOperation(projects, remoteRepoLocation, 
				folderNameMapper, remoteRepoProjectName, ShareProjectOperation.LAYOUT_SINGLE, false);
		CompositeOperation compositeOperation = new CompositeOperation(shareProjectOperation.getId(), shareProjectOperation.getMessagesClass());
		compositeOperation.add(shareProjectOperation);
		// Send notifications that the project is now shared
		compositeOperation.add(new NotifyProjectStatesChangedOperation(projects, ProjectStatesChangedEvent.ST_POST_SHARED));
		compositeOperation.run(checkInMonitor);
		
		checkInMonitor.split(1);
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

