/*******************************************************************************
 * Copyright (c) 2020 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.server.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import de.dlr.sc.virsat.project.test.AProjectTestCase;
import de.dlr.sc.virsat.server.configuration.RepositoryConfiguration;
import de.dlr.sc.virsat.team.VersionControlSystem;

public class ServerRepositoryTest extends AProjectTestCase {

	private RepositoryConfiguration testRepoConfig;
	
	@Before
	public void setUp() throws CoreException {
		super.setUp();
		
		testRepoConfig = new RepositoryConfiguration("", VersionControlSystem.GIT, "", "", getProjectName()); 
	}

	@After
	public void tearDown() throws CoreException {
		super.tearDown();
	}

	@Test
	public void testUpdateOrCheckoutProject() {
		fail("Not yet implemented");
	}

	@Test
	public void testRetrieveProjectFromConfiguration() {
		ServerRepository testServerRepository = new ServerRepository(testRepoConfig);
		
		assertNull("Project is not yet correctly detected", testServerRepository.getProject());
		
		testServerRepository.retrieveProjectFromConfiguration();
		
		assertEquals("Found correct project", testProject, testServerRepository.getProject());
	}

	@Test
	public void testRetrieveEdAndResurceSetFromConfiguration() {
		ServerRepository testServerRepository = new ServerRepository(testRepoConfig);
		
		assertNull("EditingDomain is not yet correctly detected", testServerRepository.getEd());
		assertNull("ResourceSet is not yet correctly detected", testServerRepository.getResourceSet());
		
		testServerRepository.retrieveEdAndResurceSetFromConfiguration();
		
		// Check that EditingDomain and ResourceSet get well created and registered
		EditingDomain createdEd = testServerRepository.getEd();
		ResourceSet createdResourceSet = testServerRepository.getResourceSet();
		
		assertNotNull("An Editing domain got created", createdEd);
		assertNotNull("A ResourceSet got created", createdResourceSet);
		
		// Now create a second repository on the same configuration.
		// The EditingDomain and ResourceSet have to be the same as they are created and
		// handed over by the central management in virsat.project
		ServerRepository testServerRepository2 = new ServerRepository(testRepoConfig);
		testServerRepository2.retrieveEdAndResurceSetFromConfiguration();
		
		assertEquals("Found correct Editing Domain", createdEd, testServerRepository2.getEd());
		assertEquals("Found correct ResourceSet", createdResourceSet, testServerRepository2.getResourceSet());
	}

	@Test
	public void testCreateNewProjectAndCheckIn() {
		fail("Not yet implemented");
	}

	@Test
	public void testCreateNewProjectByCheckout() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdateProject() {
		fail("Not yet implemented");
	}

	@Test
	public void testRemoveProject() {
		ServerRepository testServerRepository2 = new ServerRepository(testRepoConfig);
		testServerRepository2.retrieveProjectFromConfiguration();
		
		assertTrue("Project still exsists", testProject.exists());
		
		testServerRepository2.removeProject();
		
		assertFalse("Project got deleted", testProject.exists());
	}
}
