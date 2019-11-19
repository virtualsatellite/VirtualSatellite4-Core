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

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;

public abstract class AJettyServerTest {

	@BeforeClass
	public void setUpClass() throws IOException, IllegalStateException, GitAPIException {
		// Create an upstream repository
		File pathToUpstreamRepository = File.createTempFile("VirSatUpstreamRepo", "");
        Files.delete(pathToUpstreamRepository.toPath());
		
		Git git = Git.init().setDirectory(pathToUpstreamRepository).call();
		assertTrue(git.status().call().isClean());
	}
	
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}
	
}
