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
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.team.svn.core.IStateFilter;
import org.eclipse.team.svn.core.IStateFilter.OrStateFilter;
import org.eclipse.team.svn.core.SVNMessages;
import org.eclipse.team.svn.core.connector.ISVNConnector;
import org.eclipse.team.svn.core.connector.SVNConflictResolution.Choice;
import org.eclipse.team.svn.core.connector.SVNDepth;
import org.eclipse.team.svn.core.connector.SVNRevision;
import org.eclipse.team.svn.core.operation.AbstractActionOperation;
import org.eclipse.team.svn.core.operation.CompositeOperation;
import org.eclipse.team.svn.core.operation.SVNProgressMonitor;
import org.eclipse.team.svn.core.operation.file.AddToSVNOperation;
import org.eclipse.team.svn.core.operation.file.CheckoutAsOperation;
import org.eclipse.team.svn.core.operation.file.CommitOperation;
import org.eclipse.team.svn.core.operation.file.SVNFileStorage;
import org.eclipse.team.svn.core.operation.local.NotifyProjectStatesChangedOperation;
import org.eclipse.team.svn.core.operation.local.management.ShareProjectOperation;
import org.eclipse.team.svn.core.operation.local.management.ShareProjectOperation.IFolderNameMapper;
import org.eclipse.team.svn.core.operation.remote.management.AddRepositoryLocationOperation;
import org.eclipse.team.svn.core.resource.IRepositoryLocation;
import org.eclipse.team.svn.core.resource.IRepositoryResource;
import org.eclipse.team.svn.core.resource.events.ProjectStatesChangedEvent;
import org.eclipse.team.svn.core.utility.FileUtility;
import org.eclipse.team.svn.core.utility.SVNUtility;

import de.dlr.sc.virsat.team.Activator;
import de.dlr.sc.virsat.team.IVirSatVersionControlBackend;

public class VirSatSvnVersionControlBackend implements IVirSatVersionControlBackend {

	public static final int PROGRESS_INDEX_COMMIT_UPDATE_STEPS = 1;
	public static final int PROGRESS_INDEX_COMMIT_CHECKIN_STEPS = 2;
	public static final int PROGRESS_INDEX_COMMIT_CHECKOUT_STEPS = 3;
	public static final int PROGRESS_INDEX_DO_COMMIT_STEPS = 1;
	
	@Override
	public void commit(IProject project, String message, IProgressMonitor monitor) throws Exception {
		SubMonitor commitMonitor = SubMonitor.convert(monitor, "Virtual Satellite svn commit", PROGRESS_INDEX_DO_COMMIT_STEPS);
	
		// Perform an add + commit operation
		commitMonitor.split(1).subTask("Commiting & adding files");
		File[] files = getFilesToCommit(project);
		CommitOperation commitOperation = new CommitOperation(files, message, true, false);
		CompositeOperation compositeOperation = new CompositeOperation(commitOperation.getId(), commitOperation.getMessagesClass());
		// SVN requires adding files to the repository before commiting them
		compositeOperation.add(new AddToSVNOperation(files, true));
		compositeOperation.add(commitOperation);
		
		compositeOperation.run(commitMonitor);
		
		checkStatus(compositeOperation);
	}

	@Override
	public IProject checkout(IProjectDescription projectDescription,  File pathRepoLocal, String remoteUri, IProgressMonitor monitor) throws Exception {
		SubMonitor checkoutMonitor = SubMonitor.convert(monitor, "Virtual Satellite svn checkout", PROGRESS_INDEX_COMMIT_CHECKOUT_STEPS);
		IRepositoryResource remoteRepo = SVNUtility.asRepositoryResource(remoteUri, true);

		checkoutMonitor.split(1).subTask("Checking out remote project");
		CheckoutAsOperation checkoutAsOperation = new CheckoutAsOperation(pathRepoLocal, remoteRepo, SVNDepth.INFINITY, true, true);
		checkoutAsOperation.run(checkoutMonitor);
		
		checkStatus(checkoutAsOperation);
		
		String projectName = projectDescription.getName();
		IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject(projectName);
		project.create(projectDescription, checkoutMonitor.split(1));
		project.open(checkoutMonitor.split(1));
		
		return project;
	}

	@Override
	public void checkin(IProject project, File pathRepoLocal, String remoteUri, IProgressMonitor monitor) throws Exception {
		SubMonitor checkInMonitor = SubMonitor.convert(monitor, "Virtual Satellite svn init", PROGRESS_INDEX_COMMIT_CHECKIN_STEPS);
		
		checkInMonitor.split(1).subTask("Moving project into local Repository");
		// Setting the project description lets eclipse know where we want to move the project
		IProjectDescription projectDescription = ResourcesPlugin.getWorkspace().newProjectDescription(project.getName());
		projectDescription.setLocationURI(pathRepoLocal.toURI());
		project.move(projectDescription, true, monitor);
		
		IRepositoryLocation remoteRepoLocation = SVNUtility.asRepositoryResource(remoteUri, true).getRepositoryLocation();
		
		// The signature requires the project to be passed as an array
		IProject[] projects = { project };
		// Use the local project name as the remote project name
		IFolderNameMapper folderNameMapper = (p -> p.getName());
		
		checkInMonitor.split(1).subTask("Checking in local project");
		ShareProjectOperation shareProjectOperation = new ShareProjectOperation(projects, remoteRepoLocation, 
				folderNameMapper, project.getName(), ShareProjectOperation.LAYOUT_SINGLE, false);
		CompositeOperation compositeOperation = new CompositeOperation(shareProjectOperation.getId(), shareProjectOperation.getMessagesClass());
		compositeOperation.add(new AddRepositoryLocationOperation(remoteRepoLocation));
		compositeOperation.add(shareProjectOperation);
		// Send notifications that the project is now shared
		compositeOperation.add(new NotifyProjectStatesChangedOperation(projects, ProjectStatesChangedEvent.ST_POST_SHARED));
		compositeOperation.run(checkInMonitor);
		
		checkStatus(compositeOperation);
	}

	@Override
	public void update(IProject project, IProgressMonitor monitor) throws Exception {
		SubMonitor updateMonitor = SubMonitor.convert(monitor, "Virtual Satellite svn update", PROGRESS_INDEX_COMMIT_UPDATE_STEPS);
		
		updateMonitor.split(1).subTask("Updating files");
		UpdateProjectOperation updateProjectOperation = new UpdateProjectOperation(project);
		
		updateProjectOperation.run(updateMonitor);
		
		checkStatus(updateProjectOperation);
	}
	
	// The default UpdateOperation provided by SubVersive was unstable and sometimes failed.
	// The following is basically a low level call to the svn update functionality
	private static class UpdateProjectOperation extends AbstractActionOperation {
		
		private IProject project;
		
		UpdateProjectOperation(IProject project) {
			super("Operation_UpdateProject", SVNMessages.class);
			this.project = project;
		}

		@Override
		protected void runImpl(IProgressMonitor monitor) throws Exception {
			File workingCopy = new File(FileUtility.getWorkingCopyPath(project));
			IRepositoryLocation location = SVNFileStorage.instance().asRepositoryResource(workingCopy, false).getRepositoryLocation();
			final ISVNConnector proxy = location.acquireSVNProxy();
			String[] paths = FileUtility.asPathArray(new File[] { workingCopy });
			
			try {
				// First update the local copy
				proxy.update(paths, SVNRevision.HEAD, SVNDepth.INFINITY, ISVNConnector.Options.ALLOW_UNVERSIONED_OBSTRUCTIONS, new SVNProgressMonitor(this, monitor, null));
				
				// Then resolve potential conflicts by taking the remote versions
				proxy.resolve(paths[0], Choice.CHOOSE_REMOTE, SVNDepth.INFINITY, new SVNProgressMonitor(this, monitor, null));
			} finally {
				location.releaseSVNProxy(proxy);
			}
		}
	}
	
	/**
	 * Gets the files that should be added & commited from the eclipse project
	 * @param project the project to commit
	 * @return the files to add & commit
	 * @throws CoreException
	 */
	protected File[] getFilesToCommit(IProject project) throws CoreException {
		List<File> filesList = new ArrayList<>();
		
		File projectWorkingCopy = new File(FileUtility.getWorkingCopyPath(project));
		
		// The subversive implementation sets a flag that requires the parent of a file that should be commited,
		// to be under version control as well. We check here if the parent is under version control,
		// and if so, then we can simply commit the project file. If not, we commit the commitable files under the
		// project. 
		if (SVNUtility.hasSVNFolderInOrAbove(projectWorkingCopy.getParentFile())) {
			filesList.add(projectWorkingCopy);
		} else {
			IStateFilter[] filters = { IStateFilter.SF_MODIFIED, IStateFilter.SF_UNVERSIONED };
			IResource[] resources = FileUtility.getResourcesRecursive(project.members(), new OrStateFilter(filters));
			for (IResource resource : resources) {
				filesList.add(new File(FileUtility.getWorkingCopyPath(resource)));
			}
		}
		
		return filesList.toArray(new File[0]);
	}
	
	/**
	 * The SVN implementation catches all problems internally and doesn't give any notices of errors.
	 * Checks the status of the operation and gives some approriate handling
	 * either by re-throwing a core exception or logging if the status is a warning
	 * @param operation the operation to check
	 * @throws Exception
	 */
	protected void checkStatus(AbstractActionOperation operation) throws CoreException {
		IStatus status = operation.getStatus();
		if (status.getSeverity() == IStatus.ERROR) {
			throw new CoreException(status);
		} else if (status.getSeverity() == IStatus.WARNING) {
			Activator.getDefault().getLog().log(status);
		}
	}
}

