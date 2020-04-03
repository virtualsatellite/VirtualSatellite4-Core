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

import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.edit.command.AddCommand;
import org.junit.Before;
import org.junit.Test;

import de.dlr.sc.virsat.model.concept.types.structural.ABeanStructuralElementInstance;
import de.dlr.sc.virsat.model.concept.types.structural.IBeanStructuralElementInstance;
import de.dlr.sc.virsat.model.dvlm.DVLMPackage;
import de.dlr.sc.virsat.model.dvlm.Repository;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.dvlm.concepts.ConceptsFactory;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElement;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralFactory;
import de.dlr.sc.virsat.project.test.AProjectTestCase;

public class RepoModelAccessControllerTest extends AProjectTestCase {
	

	private RepoModelAccessController repoModelAccessController;
	
	private StructuralElement se;
	
	@Before
	public void setUp() throws CoreException {
		super.setUp();
		
		
		super.addEditingDomainAndRepository();
		
		se = StructuralFactory.eINSTANCE.createStructuralElement();
		se.setName("TestRootComponent");
		se.setIsRootStructuralElement(true);
		
		// TODO: extend ATestConceptTestCase?
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
		
		// Save all changes
		rs.saveAllResources(new NullProgressMonitor());

		// Create the controller with the modelapi instance
		repoModelAccessController = new RepoModelAccessController(editingDomain);
	}
	
	@Test
	public void testGetRootSeis() {
		// TODO: Needs a valid concept to map the bean with the function of the modelApi
		List<IBeanStructuralElementInstance> seis = repoModelAccessController.getRootSeis();
		seis.get(0);
	}
}
