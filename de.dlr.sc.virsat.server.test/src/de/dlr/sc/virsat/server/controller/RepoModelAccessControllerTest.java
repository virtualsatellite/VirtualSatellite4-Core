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

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.samePropertyValuesAs;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.command.Command;
import org.junit.Before;
import org.junit.Test;

import de.dlr.sc.virsat.model.concept.types.category.IBeanCategoryAssignment;
import de.dlr.sc.virsat.model.concept.types.structural.FlattenedStructuralElementInstance;
import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import de.dlr.sc.virsat.model.extension.tests.model.TestCategoryAllProperty;
import de.dlr.sc.virsat.model.extension.tests.model.TestStructuralElement;
import de.dlr.sc.virsat.model.extension.tests.test.ATestConceptTestCase;
import de.dlr.sc.virsat.project.structure.command.CreateAddSeiWithFileStructureCommand;

public class RepoModelAccessControllerTest extends ATestConceptTestCase {
	
	private RepoModelAccessController repoModelAccessController;
	
	@Before
	public void setUp() throws CoreException {
		super.setUp();
		
		addEditingDomainAndRepository();

		// Load the test concepts
		executeAsCommand(() -> loadTestConcept());
		
		// Save all changes
		rs.saveAllResources(new NullProgressMonitor());

		// Create the controller with the ModelAPI instance
		repoModelAccessController = new RepoModelAccessController(editingDomain);
	}
	
	/**
	 * Creates a sei and adds it to the repository
	 * @param name name of the sei
	 * @return the created TestStructuralElement
	 */
	private TestStructuralElement createRootSei(String name) {
		// Create a new TestStructuralElement with a StructuralElementInstance
		TestStructuralElement tsei = new TestStructuralElement(testConcept);
		StructuralElementInstance sei = tsei.getStructuralElementInstance();
		sei.setName(name);
		
		// Now add the new SEI to the Repository
		Command createAddSei = CreateAddSeiWithFileStructureCommand.create(editingDomain, repository, sei);
		editingDomain.getCommandStack().execute(createAddSei);
		
		return tsei;
	}
	
	@Test
	public void testSeis() throws CoreException, IOException, InstantiationException, IllegalAccessException {
		TestStructuralElement testSei1 = createRootSei("RootSei1");
		TestStructuralElement testSei2 = createRootSei("RootSei2");
		rs.saveAllResources(new NullProgressMonitor());
		
		FlattenedStructuralElementInstance flatTestSei1 = new FlattenedStructuralElementInstance(testSei1.getStructuralElementInstance());
		FlattenedStructuralElementInstance flatTestSei2 = new FlattenedStructuralElementInstance(testSei2.getStructuralElementInstance());
		
		// Get the root seis
		List<FlattenedStructuralElementInstance> seis = repoModelAccessController.getRootSeis();
		assertEquals("Two root seis found", 2, seis.size());
		assertThat("Correct sei found", flatTestSei1, is(samePropertyValuesAs(seis.get(0))));
		assertThat("Correct sei found", flatTestSei2, is(samePropertyValuesAs(seis.get(1))));
		
		// Get one sei by uuid
		String uuid1 = testSei1.getStructuralElementInstance().getUuid().toString();
		FlattenedStructuralElementInstance seiByUuid = repoModelAccessController.getSei(uuid1);
		assertThat("Right sei found", flatTestSei1, is(samePropertyValuesAs(seiByUuid)));
		
		// Delete one sei
		repoModelAccessController.deleteSei(uuid1);
		seis = repoModelAccessController.getRootSeis();
		assertEquals("Only one sei left", 1, repoModelAccessController.getRootSeis().size());	
		
		// Post sei
		String newUuid1 = repoModelAccessController.postSei(flatTestSei1);
		assertNotEquals("Uuid changed", newUuid1, uuid1);
		assertEquals("Sei got posted", 2, repoModelAccessController.getRootSeis().size());
		
		// Put (update) sei
		String newName = "Updated Sei";
		flatTestSei2.setName(newName);
		repoModelAccessController.putSei(flatTestSei2);
		assertEquals("Name changed but same uuid", newName, repoModelAccessController.getSei(
					flatTestSei2.getUuid().toString()
				).unflatten().getName());
		
		// Put (new) sei
		repoModelAccessController.deleteSei(newUuid1);
		assertEquals("Only one sei left", 1, repoModelAccessController.getRootSeis().size());
		repoModelAccessController.putSei(flatTestSei1);
		assertEquals("Sei got posted", 2, repoModelAccessController.getRootSeis().size());
	}
	
	@Test
	public void testCas() throws CoreException {
		// Create a new TestStructuralElement with a StructuralElementInstance
		TestStructuralElement tsei = new TestStructuralElement(testConcept);
		StructuralElementInstance sei = tsei.getStructuralElementInstance();
		sei.setName("RootSei");
		
		// Now add the new SEI to the Repository
		Command createAddSei = CreateAddSeiWithFileStructureCommand.create(editingDomain, repository, sei);
		editingDomain.getCommandStack().execute(createAddSei);
		
		// Create CA and add it to SEI
		TestCategoryAllProperty testCa = new TestCategoryAllProperty(testConcept);
		executeAsCommand(() -> tsei.add(testCa));

		String uuid = testCa.getUuid();
		CategoryAssignment caByUuid = repoModelAccessController.getCa(uuid);
		assertEquals("Right ca found", testCa, caByUuid);
	}
}
