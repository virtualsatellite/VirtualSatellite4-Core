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

import java.io.File;
import java.nio.file.Files;

import org.apache.commons.io.FileUtils;
import org.eclipse.core.runtime.Status;
import org.eclipse.jgit.api.Git;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

import de.dlr.sc.virsat.server.Activator;
import de.dlr.sc.virsat.server.VirSatJettyServer;

public abstract class AGitAndJettyServerTest {

	protected File pathToTempUpstreamRepository;
	private static VirSatJettyServer server;
	
	@BeforeClass
	public static void setUpClass() throws InterruptedException, Exception {
		server = new VirSatJettyServer();
		server.start();
	}

	
	@Before
	public void setUp() throws Exception {
		// Create an upstream repository within the temporary file area
		pathToTempUpstreamRepository = File.createTempFile("VirSatUpstreamRepo", "");
		Files.delete(pathToTempUpstreamRepository.toPath());
		
		Git.init().setDirectory(pathToTempUpstreamRepository).call();
	}

	@After
	public void tearDown() throws Exception {
		try {
			FileUtils.deleteDirectory(pathToTempUpstreamRepository);
		} catch (Exception e) {
			Activator.getDefault().getLog().log(new Status(Status.INFO, Activator.getPluginId(), "Failed to remove temp directory in test" + e.getMessage()));
		}
	}
	
	@AfterClass
	public static void tearDownClass() throws Exception { 
		server.stop();
		server.join();
	}
}
