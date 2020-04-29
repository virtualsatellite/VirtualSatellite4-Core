/*******************************************************************************
 * Copyright (c) 2020 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.project.structure.command;

import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.LinkedList;
import java.util.List;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.transaction.RecordingCommand;
import org.junit.Before;
import org.junit.Test;

import de.dlr.sc.virsat.model.dvlm.Repository;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElement;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralFactory;
import de.dlr.sc.virsat.project.test.AProjectTestCase;

public class CreateRemoveSeiWithFileStructureCommandTest extends AProjectTestCase {
	
	StructuralElement se;
	
	StructuralElementInstance sei1;
	StructuralElementInstance sei2;
	StructuralElementInstance sei3;
	
	IFolder seiFolder1;
	IFolder seiFolder2;
	IFolder seiFolder3;
	
	Resource resourceSei1;
	Resource resourceSei2;
	Resource resourceSei3;
	
	@Before
	public void setUp() throws CoreException {
		super.setUp();
		addEditingDomainAndRepository();

		editingDomain.getCommandStack().execute(new RecordingCommand(editingDomain) {
			@Override
			protected void doExecute() {
				se = StructuralFactory.eINSTANCE.createStructuralElement();
				se.setIsApplicableForAll(true);
				se.setIsRootStructuralElement(true);
				
				// Now create a SEI for testing and embed it into the TransactionalEditingDomain 
				sei1 = StructuralFactory.eINSTANCE.createStructuralElementInstance();
				sei2 = StructuralFactory.eINSTANCE.createStructuralElementInstance();
				sei3 = StructuralFactory.eINSTANCE.createStructuralElementInstance();
				
				sei1.setType(se);
				sei2.setType(se);
				sei3.setType(se);
				
				repository.getRootEntities().add(sei1);
				sei1.getChildren().add(sei2);
				sei2.getChildren().add(sei3);
				
				projectCommons.createFolderStructure(sei1, null);
				projectCommons.createFolderStructure(sei2, null);
				projectCommons.createFolderStructure(sei3, null);
				
				seiFolder1 = projectCommons.getStructuralElemntInstanceFolder(sei1);
				seiFolder2 = projectCommons.getStructuralElemntInstanceFolder(sei2);
				seiFolder3 = projectCommons.getStructuralElemntInstanceFolder(sei3);
				
				resourceSei1 = rs.getStructuralElementInstanceResource(sei1);
				resourceSei2 = rs.getStructuralElementInstanceResource(sei2);
				resourceSei3 = rs.getStructuralElementInstanceResource(sei3);
				
				resourceSei1.getContents().add(sei1);
				resourceSei1.getContents().add(sei2);
				resourceSei1.getContents().add(sei3);
			}
		});
	}

	@Test
	public void testCreateWithSingleSelection() {
		Command deleteSei2 = CreateRemoveSeiWithFileStructureCommand.create(sei2, RemoveFileStructureCommand.DELETE_RESOURCE_OPERATION_FUNCTION);
		editingDomain.getVirSatCommandStack().execute(deleteSei2);

		// Make sure files are removed from Workspace
		assertTrue("Folder for sei1 does still exist", seiFolder1.exists());
		assertFalse("Folder for sei2 does not exist anymore", seiFolder2.exists());
		assertFalse("Folder for sei3 does not exist anymore", seiFolder3.exists());
		
		// Make sure resources are well removed from the resourceSet
		assertThat("Resource 1 is still part of the ResourceSet", rs.getResources(), hasItem(resourceSei1));
		assertThat("Resource 2 and 3 are not part anymore", rs.getResources(), not(hasItems(resourceSei2, resourceSei3)));
	}
	
	@Test
	public void testCreateWithSingleRootSelection() {
		Command deleteSei1 = CreateRemoveSeiWithFileStructureCommand.create(sei1, RemoveFileStructureCommand.DELETE_RESOURCE_OPERATION_FUNCTION);
		editingDomain.getVirSatCommandStack().execute(deleteSei1);
		
		assertThat("SEI1 removed from repository", repository.getRootEntities(), not(hasItem(sei1)));
	}
	
	@Test
	public void testCreateSingleSelectionWithoutResource() {
		// Create a 4th SEI which is not embedded into a resource, but which has a file structure
		StructuralElementInstance sei4 = StructuralFactory.eINSTANCE.createStructuralElementInstance();
		
		executeAsCommand(() -> {
			repository.getRootEntities().remove(sei1);
			sei4.setType(se);
			projectCommons.createFolderStructure(sei4, null);
			repository.getRootEntities().add(sei4);
		});
		
		assertNull("There is no resource with SEI4", sei4.eResource());
		assertThat("SEI4 is part of repository", repository.getRootEntities(), hasItem(sei4));
		assertTrue("The folder for the SEI4 exsists", projectCommons.getStructuralElemntInstanceFolder(sei4).exists());
		
		Command deleteSei4 = CreateRemoveSeiWithFileStructureCommand.create(sei4, RemoveFileStructureCommand.DELETE_RESOURCE_OPERATION_FUNCTION);
		editingDomain.getVirSatCommandStack().execute(deleteSei4);
		
		assertNull("There is no resource with SEI4", sei4.eResource());
		assertThat("There is no ED with SEI4 thus it could not be removed", repository.getRootEntities(), hasItem(sei4));
		assertTrue("Since there is no ED the folders cannot be removed as well", projectCommons.getStructuralElemntInstanceFolder(sei4).exists());
		
		// Now save and reload all, sei4 disappeared but the folder still exists
		editingDomain.saveAll();
		editingDomain.reloadAll();
		
		Repository reloadRepo = editingDomain.getResourceSet().getRepository();
		assertThat("There is no root entity anymore", reloadRepo.getRootEntities(), empty());
	}

	@Test
	public void testCreateWithMultiSelection() {
		// Create the multi selection
		List<StructuralElementInstance> selection = new LinkedList<>();
		selection.add(sei1);
		selection.add(sei3);
		
		Command deleteSei2 = CreateRemoveSeiWithFileStructureCommand.create(selection, RemoveFileStructureCommand.DELETE_RESOURCE_OPERATION_FUNCTION);
		editingDomain.getVirSatCommandStack().execute(deleteSei2);

		// Make sure files are removed from Workspace
		assertFalse("Folder for sei1 does not exist anymore", seiFolder1.exists());
		assertFalse("Folder for sei2 does not exist anymore", seiFolder2.exists());
		assertFalse("Folder for sei3 does not exist anymore", seiFolder3.exists());
		
		// Make sure resources are well removed from the resourceSet
		assertThat("Resource 2 and 3 are not part anymore", rs.getResources(), not(hasItems(resourceSei1, resourceSei2, resourceSei3)));
	}
}
