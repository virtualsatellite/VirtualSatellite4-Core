/*******************************************************************************
 * Copyright (c) 2020 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.server.controller;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Status;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.junit.Before;
import org.junit.Test;

import de.dlr.sc.virsat.commons.file.VirSatFileUtils;
import de.dlr.sc.virsat.model.dvlm.Repository;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralFactory;
import de.dlr.sc.virsat.project.resources.VirSatResourceSet;
import de.dlr.sc.virsat.project.structure.VirSatProjectCommons;
import de.dlr.sc.virsat.project.test.AProjectTestCase;
import de.dlr.sc.virsat.server.configuration.RepositoryConfiguration;
import de.dlr.sc.virsat.server.repository.RepoRegistry;
import de.dlr.sc.virsat.server.repository.ServerRepository;
import de.dlr.sc.virsat.team.Activator;
import de.dlr.sc.virsat.team.VersionControlSystem;

public class RepoModelAccessControllerTest extends AProjectTestCase {
	

	private ServerRepository testServerRepository;
	private Path pathRepoRemote;
	private File localRepoHome;
	private RepoModelAccessController repoModelAccessController;
	
	@Before
	public void setUp() throws CoreException {
		super.setUp();
		
		
		// Setup a complete working server project with a backend
		// TODO: delete backend if possible
		try {
			pathRepoRemote = VirSatFileUtils.createAutoDeleteTempDirectory("VirtualSatelliteGitRemote_");
			localRepoHome = VirSatFileUtils.createAutoDeleteTempDirectory("VirtualSatelliteLocalRepoHome_").toFile();
			File fileGitRemoteRepo = pathRepoRemote.toFile();
			Git.init().setDirectory(fileGitRemoteRepo).setBare(true).call();
		} catch (IOException | IllegalStateException | GitAPIException e) {
			Activator.getDefault().getLog().log(
				new Status(
					Status.ERROR,
					Activator.getPluginId(),
					"Error during repository setup",
					e
				)
			);
		}
		
		RepositoryConfiguration testRepoConfig = new RepositoryConfiguration(
			TEST_PROJECT_NAME,
			"",
			pathRepoRemote.toUri().toString(),
			VersionControlSystem.GIT,
			"",
			""
		); 
		try {
			testServerRepository = new ServerRepository(localRepoHome, testRepoConfig);
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		
		try {
			testServerRepository.checkoutRepository();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
//		IProject testProject = testServerRepository.getProject();
		
		// Register Repo
		RepoRegistry.getInstance().addRepository(testRepoConfig.getProjectName(), testServerRepository);
		
		// Add a SEI
//		projectCommons = new VirSatProjectCommons(testProject);
		
//		rs =  VirSatResourceSet.createUnmanagedResourceSet(testProject);
		rs =  testServerRepository.getResourceSet();
//		rs.initializeModelsAndResourceSet();
		Repository repo = rs.getRepository();
		StructuralElementInstance sei1 = StructuralFactory.eINSTANCE.createStructuralElementInstance();
		rs.getStructuralElementInstanceResource(sei1);
//		IFile seiFile = projectCommons.getStructuralElementInstanceFile(sei1);
		
		
		// Create the controller with the modelapi instance
		repoModelAccessController = new RepoModelAccessController(TEST_PROJECT_NAME);
	}
	
	@Test
	public void testGetRootSeis() {
		repoModelAccessController.getRootSeis();
	}
}
