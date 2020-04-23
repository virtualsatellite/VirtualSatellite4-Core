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

import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.LinkedList;
import java.util.List;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.transaction.RecordingCommand;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import de.dlr.sc.virsat.model.dvlm.structural.StructuralElement;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralFactory;
import de.dlr.sc.virsat.project.test.AProjectTestCase;

public class CreateRemoveSeiWithFileStructureCommandTest extends AProjectTestCase {

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
				StructuralElement se = StructuralFactory.eINSTANCE.createStructuralElement();
				se.setIsApplicableForAll(true);
				
				// Now create a SEI for testing and embed it into the TransactionalEditingDomain 
				sei1 = StructuralFactory.eINSTANCE.createStructuralElementInstance();
				sei2 = StructuralFactory.eINSTANCE.createStructuralElementInstance();
				sei3 = StructuralFactory.eINSTANCE.createStructuralElementInstance();
				
				sei1.setType(se);
				sei2.setType(se);
				sei3.setType(se);
				
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

	@After
	public void tearDown() throws CoreException {
		super.tearDown();
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
	public void testCreateWithMultiSelection() {

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
