/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.server.test;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import org.apache.commons.io.FileUtils;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

import de.dlr.sc.virsat.server.VirSatJettyServer;

public abstract class AGitAndJettyServerTest {

	private static File pathToTempUpstreamRepository;
	
	@BeforeClass
	public static void setUpClass() throws IOException, IllegalStateException, GitAPIException {
		
		// Create an upstream repository within the temporary file area
		pathToTempUpstreamRepository = File.createTempFile("VirSatUpstreamRepo", "");
		Files.delete(pathToTempUpstreamRepository.toPath());

		Git git = Git.init().setDirectory(pathToTempUpstreamRepository).call();
		assertTrue(git.status().call().isClean());
	}

	private VirSatJettyServer server;
	
	@Before
	public void setUp() throws Exception {
		server = new VirSatJettyServer();
		server.start();
	}

	@After
	public void tearDown() throws Exception {
		server.stop();
	}
	
	@AfterClass
	public static void tearDownClass() throws IOException { 
		// Clean Up temporary Files
		FileUtils.deleteDirectory(pathToTempUpstreamRepository);
	}
}
