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

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.NoHeadException;
import org.eclipse.jgit.revwalk.RevCommit;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import de.dlr.sc.virsat.project.test.AProjectTestCase;
import de.dlr.sc.virsat.server.configuration.RepositoryConfiguration;
import de.dlr.sc.virsat.team.Activator;
import de.dlr.sc.virsat.team.VersionControlSystem;
import de.dlr.sc.virsat.team.git.VirSatGitVersionControlBackend;

@SuppressWarnings("restriction")
public class ServerRepositoryTest extends AProjectTestCase {

	private RepositoryConfiguration testRepoConfig;
	private Path pathRepoRemote;
	private File localRepoHome;
	
	private static final String TEST_PROJECT_NAME = "LokalVirSatProject";
	
	@Before
	public void setUp() throws CoreException {
		super.setUp();
		try {
			pathRepoRemote = Files.createTempDirectory("VirtualSatelliteGitRemote_");
			localRepoHome = Files.createTempDirectory("VirtualSatelliteLocalRepoHome_").toFile();
			File fileGitRemoteRepo = pathRepoRemote.toFile();
			Git.init().setDirectory(fileGitRemoteRepo).setBare(true).call();
		} catch (IOException | IllegalStateException | GitAPIException e) {
			Activator.getDefault().getLog().log(new Status(Status.ERROR, Activator.getPluginId(),
					"Error during repository setup", e));
		}
		
		testRepoConfig = new RepositoryConfiguration(
				TEST_PROJECT_NAME,
				new File(""),
				pathRepoRemote.toUri(),
				VersionControlSystem.GIT,
				"",
				""
		); 
	}

	@After
	public void tearDown() throws CoreException {
		try {
			Files.walk(pathRepoRemote).sorted(Comparator.reverseOrder()).map(Path::toFile).forEach(File::delete);
			Files.walk(localRepoHome.toPath()).sorted(Comparator.reverseOrder()).map(Path::toFile).forEach(File::delete);
		} catch (IOException e) {
			Activator.getDefault().getLog().log(new Status(Status.ERROR, Activator.getPluginId(),
					"Error during temp remote directory creation", e));
		}
		super.tearDown();
	}

	@Test
	public void testRetrieveProjectFromConfiguration() {
		ServerRepository testServerRepository = new ServerRepository(localRepoHome, testRepoConfig);
		
		assertNull("Project is not yet correctly detected", testServerRepository.getProject());
		
		testServerRepository.retrieveProjectFromConfiguration();
		
		assertEquals("Found correct project", testProject, testServerRepository.getProject());
	}

	@Test
	public void testRetrieveEdAndResurceSetFromConfiguration() {
		ServerRepository testServerRepository = new ServerRepository(localRepoHome, testRepoConfig);
		
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
		ServerRepository testServerRepository2 = new ServerRepository(localRepoHome, testRepoConfig);
		testServerRepository2.retrieveEdAndResurceSetFromConfiguration();
		
		assertEquals("Found correct Editing Domain", createdEd, testServerRepository2.getEd());
		assertEquals("Found correct ResourceSet", createdResourceSet, testServerRepository2.getResourceSet());
	}

	@Test
	public void testRemoveProject() {
		ServerRepository testServerRepository2 = new ServerRepository(localRepoHome, testRepoConfig);
		testServerRepository2.retrieveProjectFromConfiguration();
		
		assertTrue("Project still exsists", testProject.exists());
		
		testServerRepository2.removeProject();
		
		assertFalse("Project got deleted", testProject.exists());
	}
}
