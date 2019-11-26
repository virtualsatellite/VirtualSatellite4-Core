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


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.UriBuilder;

import org.apache.commons.io.FileUtils;
import org.glassfish.jersey.client.ClientConfig;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import de.dlr.sc.virsat.server.dataaccess.VirSatGitAccess;
import de.dlr.sc.virsat.server.test.AGitAndJettyServerTest;

public class WorkspaceAccessResourceTest extends AGitAndJettyServerTest {

	private File pathToTempLocalRepository1;
	private File pathToTempLocalRepository2;

	@Before
	public void setUp() throws Exception {
		super.setUp();
		
		pathToTempLocalRepository1 = new File("VirSatLocalRepo1");
		pathToTempLocalRepository2 = new File("VirSatLocalRepo2");
		FileUtils.deleteQuietly(makeAbsolute(pathToTempLocalRepository1));
		FileUtils.deleteQuietly(makeAbsolute(pathToTempLocalRepository2));
		Files.createDirectory(makeAbsolute(pathToTempLocalRepository1).toPath());
		Files.createDirectory(makeAbsolute(pathToTempLocalRepository2).toPath());
	}
	
	@After
	public void tearDown() throws Exception {
		FileUtils.deleteQuietly(makeAbsolute(pathToTempLocalRepository1));
		FileUtils.deleteQuietly(makeAbsolute(pathToTempLocalRepository2));
		super.tearDown();
	}

	@Test
	public void testCloneCommitAndUpdateRepository() throws IOException {
		ClientConfig config = new ClientConfig();
		Client client = ClientBuilder.newClient(config);
		
		URI uri = UriBuilder.fromUri("http://localhost:8000/").build();
		WebTarget target = client.target(uri);
		
		// Clone to local repository 1
		String result = target.
			path("/rest").
			path(WorkspaceAccessResource.PATH_PERSISTENCE).
			path(WorkspaceAccessResource.PATH_CLONE).
			queryParam(WorkspaceAccessResource.PARAM_REMOTE, pathToTempUpstreamRepository).
			queryParam(WorkspaceAccessResource.PARAM_LOCAL, pathToTempLocalRepository1).
			request().
			get(String.class);
		
		assertEquals("Cloning local repository 1 succeeded", VirSatGitAccess.STATUS_OK, result);
		
		// Clone to local repository 2
		String result2 = target.
			path("/rest").
			path(WorkspaceAccessResource.PATH_PERSISTENCE).
			path(WorkspaceAccessResource.PATH_CLONE).
			queryParam(WorkspaceAccessResource.PARAM_REMOTE, pathToTempUpstreamRepository).
			queryParam(WorkspaceAccessResource.PARAM_LOCAL, pathToTempLocalRepository2).
			request().
			get(String.class);
		
		assertEquals("Cloning local repository 2 succeeded", VirSatGitAccess.STATUS_OK, result2);
				
		// Add a file to repo one and commit it
		// now add a file to the local repository
		File newFile = new File(pathToTempLocalRepository1.getAbsolutePath().toString() + "/test.dat");
		newFile.createNewFile();
		
		final String COMMIT_MESSAGE = "Commit a first file";
		
		// Commit new file of repository 1
		String result3 = target.
			path("/rest").
			path(WorkspaceAccessResource.PATH_PERSISTENCE).
			path(WorkspaceAccessResource.PATH_COMMIT).
			queryParam(WorkspaceAccessResource.PARAM_LOCAL, pathToTempLocalRepository1).
			queryParam(WorkspaceAccessResource.PARAM_MESSAGE, COMMIT_MESSAGE).
			request().
			get(String.class);
		
		assertEquals("File got correctly committed", VirSatGitAccess.STATUS_OK, result3);
		
		// now update repository 2 and make3 sure the file has appeared
		String result4 = target.
			path("/rest").
			path(WorkspaceAccessResource.PATH_PERSISTENCE).
			path(WorkspaceAccessResource.PATH_UPDATE).
			queryParam(WorkspaceAccessResource.PARAM_LOCAL, pathToTempLocalRepository2).
			request().
			get(String.class);
		
		assertEquals("File got correctly updated", VirSatGitAccess.STATUS_OK, result4);

		// Check the file arrived in repo 2
		File updatedFile = makeAbsolute(new File(pathToTempLocalRepository2.toString() + "/test.dat"));

		assertTrue("The file has arrived in repo 2", updatedFile.exists());
	}
}
