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
import java.net.URI;
import java.nio.file.Files;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.team.svn.core.SVNMessages;
import org.eclipse.team.svn.core.connector.ISVNManager;
import org.eclipse.team.svn.core.connector.SVNDepth;
import org.eclipse.team.svn.core.extension.CoreExtensionsManager;
import org.eclipse.team.svn.core.operation.AbstractActionOperation;
import org.eclipse.team.svn.core.operation.SVNProgressMonitor;
import org.eclipse.team.svn.core.operation.file.CheckoutAsOperation;
import org.eclipse.team.svn.core.resource.IRepositoryResource;
import org.eclipse.team.svn.core.utility.SVNUtility;
import org.junit.Before;

import de.dlr.sc.virsat.team.Activator;
import de.dlr.sc.virsat.team.test.AVirSatVersionControlBackendTest;

public class VirSatSvnVersionControlBackendTest extends AVirSatVersionControlBackendTest {
	
	private IRepositoryResource remoteRepo;
	
	@Before
	public void setUp() throws CoreException {
		
		try {
			pathRepoRemote = Files.createTempDirectory("VirtualSatelliteSvnRemote_");
			URI uriToRemoteRepoPath = pathRepoRemote.toUri();
			String remoteRepoFilePath = pathRepoRemote.toString();
			
			remoteRepo = SVNUtility.asRepositoryResource(uriToRemoteRepoPath.toString(), true);
			
			// There is no subersive API for creating a repository, have to manually build the svn repo
			AbstractActionOperation createRemoteRepoOp = new AbstractActionOperation("Operation_CreateRepository", SVNMessages.class) {
				protected void runImpl(IProgressMonitor monitor) throws Exception {
					ISVNManager proxy = CoreExtensionsManager.instance().getSVNConnectorFactory().createManager();
					
					try {
						proxy.create(remoteRepoFilePath, ISVNManager.RepositoryKind.FSFS, null, ISVNManager.Options.NONE, new SVNProgressMonitor(this, monitor, null));
					} finally {
						proxy.dispose();
					}
				}			
			};
			
			createRemoteRepoOp.run(new NullProgressMonitor());
			if (!createRemoteRepoOp.getStatus().isOK()) {
				throw createRemoteRepoOp.getStatus().getException();
			}
			
			
			pathRepoLocal1 = Files.createTempDirectory("VirtualSatelliteSvnLocal1_");
			File filePathToProject = pathRepoLocal1.toFile();
			CheckoutAsOperation checkoutAsOperation1 = new CheckoutAsOperation(filePathToProject, remoteRepo, SVNDepth.INFINITY, true, true);
			checkoutAsOperation1.run(new NullProgressMonitor());
			
			pathRepoLocal2 = Files.createTempDirectory("VirtualSatelliteSvnLocal2_");
			filePathToProject = pathRepoLocal2.toFile();
			CheckoutAsOperation checkoutAsOperation2 = new CheckoutAsOperation(filePathToProject, remoteRepo, SVNDepth.INFINITY, true, true);
			checkoutAsOperation2.run(new NullProgressMonitor());
		} catch (Throwable e) {
			throw new CoreException(new Status(Status.ERROR, Activator.getPluginId(),
					"Error during temp remote directory creation", e));
		}
		
		super.setUp();

		backend = new VirSatSvnVersionControlBackend();
	}
}
