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
import java.nio.file.Files;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.osgi.util.NLS;
import org.eclipse.team.svn.core.SVNMessages;
import org.eclipse.team.svn.core.connector.ISVNManager;
import org.eclipse.team.svn.core.connector.ISVNManager.RepositoryKind;
import org.eclipse.team.svn.core.connector.SVNDepth;
import org.eclipse.team.svn.core.extension.CoreExtensionsManager;
import org.eclipse.team.svn.core.operation.AbstractActionOperation;
import org.eclipse.team.svn.core.operation.IConsoleStream;
import org.eclipse.team.svn.core.operation.SVNProgressMonitor;
import org.eclipse.team.svn.core.operation.file.CheckoutAsOperation;
import org.eclipse.team.svn.core.resource.IRepositoryResource;
import org.eclipse.team.svn.core.utility.FileUtility;
import org.eclipse.team.svn.core.utility.SVNUtility;
import org.junit.Before;

import de.dlr.sc.virsat.team.Activator;
import de.dlr.sc.virsat.team.test.AVirSatVersionControlBackendTest;

public class VirSatSvnVersionControlBackendTest extends AVirSatVersionControlBackendTest {
	
	@Before
	public void setUp() throws CoreException {
		
		try {
			pathRepoRemote = Files.createTempDirectory("VirtualSatelliteSvnRemote_");
			URI uriToRemoteRepoPath = pathRepoRemote.toUri();
			String remoteRepoFilePath = pathRepoRemote.toString();
			
			IRepositoryResource remoteRepo = SVNUtility.asRepositoryResource(uriToRemoteRepoPath.toString(), true);
			
			AbstractActionOperation createRemoteRepoOp = new AbstractActionOperation("Operation_CreateRepository", SVNMessages.class) {
				protected void runImpl(IProgressMonitor monitor) throws Exception {
					ISVNManager proxy = CoreExtensionsManager.instance().getSVNConnectorFactory().createManager();
					RepositoryKind repositoryType = ISVNManager.RepositoryKind.FSFS;
					
					try {					
						StringBuffer msg = new StringBuffer();
						msg.append("svnadmin create ");
						msg.append("--fs-type ").append(repositoryType.toString().toLowerCase()).append(" ");
						msg.append("\"").append(FileUtility.normalizePath(remoteRepoFilePath)).append("\"");
						msg.append("\n"); //$NON-NLS-1$
						this.writeToConsole(IConsoleStream.LEVEL_CMD, msg.toString());
						proxy.create(remoteRepoFilePath, repositoryType, null, ISVNManager.Options.NONE, new SVNProgressMonitor(this, monitor, null));
					} finally {
						proxy.dispose();
					}
				}			
			};
			
			createRemoteRepoOp.run(null);
			
			pathRepoLocal1 = Files.createTempDirectory("VirtualSatelliteSvnLocal1_");
			File filePathToProject = pathRepoLocal1.toFile();
			CheckoutAsOperation checkoutAsOperation1 = new CheckoutAsOperation(filePathToProject, remoteRepo, SVNDepth.INFINITY, true, true);
			checkoutAsOperation1.run(null);
			
			pathRepoLocal2 = Files.createTempDirectory("VirtualSatelliteSvnLocal2_");
			filePathToProject = pathRepoLocal2.toFile();
			CheckoutAsOperation checkoutAsOperation2 = new CheckoutAsOperation(filePathToProject, remoteRepo, SVNDepth.INFINITY, true, true);
			checkoutAsOperation2.run(null);
		} catch (IOException e) {
			Activator.getDefault().getLog().log(new Status(Status.ERROR, Activator.getPluginId(),
					"Error during temp remote directory creation", e));
		}
		
		super.setUp();

		backend = new VirSatSvnVersionControlBackend();
	}
	
	private static final int WAIT_FOR_REPO_DETECTION_TIMESPAN = 10;
	
	@Override
	protected void waitForProjectToRepoMapping(IProject project) throws CoreException {
		super.waitForProjectToRepoMapping(project);
		try {
			while (!FileUtility.isConnected(project)) {
				Thread.sleep(WAIT_FOR_REPO_DETECTION_TIMESPAN);
			}
		} catch (InterruptedException e) {
		}
	}
}
