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

import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.io.IOException;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.command.Command;
import org.junit.Before;
import org.junit.Test;

import de.dlr.sc.virsat.model.concept.types.category.IBeanCategoryAssignment;
import de.dlr.sc.virsat.model.concept.types.structural.IBeanStructuralElementInstance;
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
	public void testSeis() throws CoreException, IOException {
		TestStructuralElement testSei1 = createRootSei("RootSei1");
		TestStructuralElement testSei2 = createRootSei("RootSei2");
		rs.saveAllResources(new NullProgressMonitor());
		
		// Get the root seis
		List<IBeanStructuralElementInstance> seis = repoModelAccessController.getRootSeis();
		assertEquals("Two root seis found", 2, seis.size());
		assertThat("Correct elements", seis, hasItems(testSei1, testSei2));
		
		// Get one sei by uuid
		String uuid = testSei1.getStructuralElementInstance().getUuid().toString();
		IBeanStructuralElementInstance seiByUuid = repoModelAccessController.getSei(uuid);
		assertEquals("Right sei found", testSei1, seiByUuid);
		
		// Delete one sei
		// TODO: remove command and handle transactions in the controller/modelapi
		executeAsCommand(() -> {
			try {
				repoModelAccessController.deleteSei(uuid);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		seis = repoModelAccessController.getRootSeis();
		assertEquals("Only one sei left", 1, repoModelAccessController.getRootSeis().size());
		
		// Post sei
		String oldUuid = testSei1.getStructuralElementInstance().getUuid().toString();
		// TODO: remove command and handle transactions in the controller/modelapi
		String newUuid = executeAsCommand(() -> {
			try {
				return repoModelAccessController.postSei(testSei1);
			} catch (CoreException | InstantiationException | IllegalAccessException e) {
				e.printStackTrace();
			}
			return null;
		});
		assertNotEquals("Uuid changed", newUuid, oldUuid);
		assertEquals("Sei got posted", 2, repoModelAccessController.getRootSeis().size());
		
		// Put (update) sei
		String newName = "Updated Sei";
		// TODO: remove command and handle transactions in the controller/modelapi
		executeAsCommand(() -> {
			try {
				testSei1.setName(newName);
				repoModelAccessController.putSei(testSei1);
			} catch (CoreException | IOException e) {
				e.printStackTrace();
			}
		});
		assertEquals("Name changed but same uuid", newName, repoModelAccessController.getSei(newUuid).getName());
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
		IBeanCategoryAssignment caByUuid = repoModelAccessController.getCa(uuid);
		assertEquals("Right ca found", testCa, caByUuid);
	}
}
