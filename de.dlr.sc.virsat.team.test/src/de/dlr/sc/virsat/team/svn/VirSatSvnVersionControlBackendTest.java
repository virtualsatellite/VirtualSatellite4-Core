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

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Status;
import org.eclipse.team.svn.core.connector.SVNDepth;
import org.eclipse.team.svn.core.operation.remote.CheckoutAsOperation;
import org.eclipse.team.svn.core.resource.IRepositoryResource;
import org.eclipse.team.svn.core.utility.SVNUtility;
import org.junit.Before;

import de.dlr.sc.virsat.team.Activator;
import de.dlr.sc.virsat.team.test.AVirSatVersionControlBackendTest;

public class VirSatSvnVersionControlBackendTest extends AVirSatVersionControlBackendTest {
	
	@Before
	public void setUp() throws CoreException {
		
		try {
			pathRepoRemote = Files.createTempDirectory("VirtualSatelliteSvnRemote_");
			File fileSvnRemoteRepo = pathRepoRemote.toFile();
			fileSvnRemoteRepo.mkdir();
			URI uriToRemoteRepoPath = pathRepoRemote.toUri();
			String remoteRepoRawPath = uriToRemoteRepoPath.toString();
			IRepositoryResource remoteRepo = SVNUtility.asRepositoryResource(remoteRepoRawPath, true);
			
			pathRepoLocal1 = Files.createTempDirectory("VirtualSatelliteSvnLocal1_");
			File filePathToProject = pathRepoLocal1.toFile();
			CheckoutAsOperation checkoutAsOperation1 = new CheckoutAsOperation(filePathToProject.getName(), remoteRepo, SVNDepth.INFINITY, true);
			checkoutAsOperation1.run(null);
			
			uriToRemoteRepoPath = pathRepoRemote.toUri();
			pathRepoLocal2 = Files.createTempDirectory("VirtualSatelliteSvnLocal2_");
			filePathToProject = pathRepoLocal2.toFile();
			CheckoutAsOperation checkoutAsOperation2 = new CheckoutAsOperation(filePathToProject.getName(), remoteRepo, SVNDepth.INFINITY, true);
			checkoutAsOperation2.run(null);
		} catch (IOException e) {
			Activator.getDefault().getLog().log(new Status(Status.ERROR, Activator.getPluginId(),
					"Error during temp remote directory creation", e));
		}
		
		super.setUp();

		backend = new VirSatSvnVersionControlBackend();
	}
}
