/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.server.repository;

import org.junit.Assert;
import org.junit.Test;

import de.dlr.sc.virsat.server.configuration.RepositoryConfiguration;

public class RepoRegistryTest {
	
	private static final String REPO_NAME = "TestRepository";
	
	@Test
	public void testBasicAddGet() {
		
		//Create basic test repsoitory 
		final ServerRepository TEST_REPO = new ServerRepository(new RepositoryConfiguration("", "", "", "", ""));
		
		RepoRegistry.getInstance().addRepository(REPO_NAME, TEST_REPO);
		
		ServerRepository returnedFromRegistry = RepoRegistry.getInstance().getRepository(REPO_NAME);
		
		Assert.assertEquals("Object should be the same as the one added", TEST_REPO, returnedFromRegistry);
	}
	
	@Test
	public void testListAddGet() {
		
		//Create basic test repsoitory 
		final ServerRepository TEST_REPO = new ServerRepository(new RepositoryConfiguration("", "", "", "", ""));
		
		RepoRegistry.getInstance().getRepositories().put(REPO_NAME, TEST_REPO);
		
		ServerRepository returnedFromRegistry = RepoRegistry.getInstance().getRepositories().get(REPO_NAME);
		
		Assert.assertEquals("Object should be the same as the one added", TEST_REPO, returnedFromRegistry);
	}

}
