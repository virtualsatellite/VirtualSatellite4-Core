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

import org.eclipse.emf.common.command.Command;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.junit.Before;
import org.junit.Test;

import de.dlr.sc.virsat.commons.file.VirSatFileUtils;
import de.dlr.sc.virsat.model.dvlm.DVLMPackage;
import de.dlr.sc.virsat.model.dvlm.Repository;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.dvlm.concepts.ConceptsFactory;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElement;
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
	

	private RepoModelAccessController repoModelAccessController;
	
	@Before
	public void setUp() throws CoreException {
		super.setUp();
		
		
		super.addEditingDomainAndRepository();
		
		StructuralElement se = StructuralFactory.eINSTANCE.createStructuralElement();
		se.setName("TestRootComponent");
		se.setIsRootStructuralElement(true);
		
		Concept concept = ConceptsFactory.eINSTANCE.createConcept();
		concept.getStructuralElements().add(se);
		
		// Now add the repository but the repo is already within the transactional editing domain
		// thus it needs to be added using the COmmand Framework and execute it in the editingdomain
		
		Command addConceptToRepo = AddCommand.create(editingDomain, repository, DVLMPackage.eINSTANCE.getRepository_ActiveConcepts(), concept);
		editingDomain.getCommandStack().execute(addConceptToRepo);
		
		// Create a new StructuralElementInstance
		StructuralElementInstance sei1 = StructuralFactory.eINSTANCE.createStructuralElementInstance();
		rs.getStructuralElementInstanceResource(sei1);
		
		sei1.setName("TestSEI");
		sei1.setType(se);
		
		// Now add the new SEI to the Rpeository
		
		Command addSeiToRepo = AddCommand.create(editingDomain, repository, DVLMPackage.eINSTANCE.getRepository_RootEntities(), sei1);
		editingDomain.getCommandStack().execute(addSeiToRepo);
		
		// Create the controller with the modelapi instance
		repoModelAccessController = new RepoModelAccessController(TEST_PROJECT_NAME);
	}
	
	@Test
	public void testGetRootSeis() {
		repoModelAccessController.getRootSeis();
	}
}
