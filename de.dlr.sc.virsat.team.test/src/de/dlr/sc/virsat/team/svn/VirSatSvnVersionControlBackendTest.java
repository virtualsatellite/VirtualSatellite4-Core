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
import java.io.IOException;
import java.net.URI;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.team.svn.core.connector.SVNDepth;
import org.eclipse.team.svn.core.operation.file.AddToSVNOperation;
import org.eclipse.team.svn.core.operation.file.CheckoutAsOperation;
import org.eclipse.team.svn.core.resource.IRepositoryResource;
import org.eclipse.team.svn.core.utility.FileUtility;
import org.eclipse.team.svn.core.utility.SVNUtility;
import org.junit.Before;

import de.dlr.sc.virsat.commons.file.VirSatFileUtils;
import de.dlr.sc.virsat.team.AVirSatVersionControlBackendTest;
import de.dlr.sc.virsat.team.Activator;
import de.dlr.sc.virsat.team.test.CreateSvnServerOperation;

public class VirSatSvnVersionControlBackendTest extends AVirSatVersionControlBackendTest {
	
	private IRepositoryResource remoteRepo;
	
	@Before
	public void setUp() throws CoreException {
		
		try {
			pathRepoRemote = VirSatFileUtils.createAutoDeleteTempDirectory("VirtualSatelliteSvnRemote_");
			URI uriToRemoteRepoPath = pathRepoRemote.toUri();
			String remoteRepoFilePath = pathRepoRemote.toString();
			
			remoteRepo = SVNUtility.asRepositoryResource(uriToRemoteRepoPath.toString(), true);
			
			CreateSvnServerOperation createRemoteRepoOp = new CreateSvnServerOperation(remoteRepoFilePath);
			createRemoteRepoOp.runWithExceptionChecking(new NullProgressMonitor());
			
			pathRepoLocal1 = VirSatFileUtils.createAutoDeleteTempDirectory("VirtualSatelliteSvnLocal1_");
			File filePathToProject = pathRepoLocal1.toFile();
			CheckoutAsOperation checkoutAsOperation1 = new CheckoutAsOperation(filePathToProject, remoteRepo, SVNDepth.INFINITY, true, true);
			checkoutAsOperation1.run(new NullProgressMonitor());
			
			pathRepoLocal2 = VirSatFileUtils.createAutoDeleteTempDirectory("VirtualSatelliteSvnLocal2_");
			filePathToProject = pathRepoLocal2.toFile();
			CheckoutAsOperation checkoutAsOperation2 = new CheckoutAsOperation(filePathToProject, remoteRepo, SVNDepth.INFINITY, true, true);
			checkoutAsOperation2.run(new NullProgressMonitor());
		} catch (IOException e) {
			throw new CoreException(new Status(Status.ERROR, Activator.getPluginId(),
					"Error during temp directory creation", e));
		}
		
		super.setUp();

		backend = new VirSatSvnVersionControlBackend();
	}
	
	@Override
	protected void ensureFileCanConflict(IFile file) {
		// SVN requires files to be added or else it will not detect conflicts on the files
		// Therefore we perform the add operation to ensure that SVN recognizes that local changes
		// to a file can conflict with remote changes
		
		File workingFile = new File(FileUtility.getWorkingCopyPath(file));
		File[] files = { workingFile };
		AddToSVNOperation addToSVNOperation = new AddToSVNOperation(files, true);
		addToSVNOperation.run(new NullProgressMonitor());
	}
}
