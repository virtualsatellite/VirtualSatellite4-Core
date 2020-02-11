/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.project.resources.command;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.resource.Resource;
import org.junit.Test;


import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralFactory;
import de.dlr.sc.virsat.project.editingDomain.VirSatEditingDomainRegistry;
import de.dlr.sc.virsat.project.resources.VirSatResourceSet;
import de.dlr.sc.virsat.project.structure.VirSatProjectCommons;
import de.dlr.sc.virsat.project.test.AProjectTestCase;

/**
 * Tests the Creation of resources and files for a structural element instance
 */
public class CreateSeiResourceAndFileCommandTest extends AProjectTestCase {

	private StructuralElementInstance sei1;
	
	@Override
	public void setUp() throws CoreException {
		super.setUp();
		addEditingDomainAndRepository();
		sei1 = StructuralFactory.eINSTANCE.createStructuralElementInstance();
	}
	
	@Override
	public void tearDown() throws CoreException {
		super.tearDown();
		
		VirSatResourceSet.clear();
		VirSatEditingDomainRegistry.INSTANCE.clear();
	}
	
	@Test
	public void testCanExecute() {
		Command cmd = new CreateSeiResourceAndFileCommand(rs, sei1);
		assertTrue("Command can always execute", cmd.canExecute());
	}

	@Test
	public void testCanUndo() {
		Command cmd = new CreateSeiResourceAndFileCommand(rs, sei1);
		assertTrue("Command can always undo", cmd.canUndo());
	}

	private static final int AMOUNT_PROJECT_RESOURCE_WITH_SEI = 4;
	private static final int AMOUNT_PROJECT_RESOURCES_NO_SEI = 3;
	
	@Test
	public void testUndo() {
		Command cmd = new CreateSeiResourceAndFileCommand(rs, sei1);
		editingDomain.getCommandStack().execute(cmd);

		IFile file = projectCommons.getStructuralElementInstanceFile(sei1);
		IFolder folder = projectCommons.getStructuralElemntInstanceFolder(sei1);
		
		// Check that the necessary files have been created
		assertTrue("File got correctly created", file.exists());
		assertTrue("Folder got correctly creatred", folder.exists());
		assertEquals("ResourceSet has a new resource", AMOUNT_PROJECT_RESOURCE_WITH_SEI, rs.getResources().size());

		editingDomain.getCommandStack().undo();

		// Check that all files got deleted and that the resource has been removed from the resource set
		assertFalse("File got correctly created", file.exists());
		assertFalse("Folder got correctly creatred", folder.exists());
		assertEquals("ResourceSet is now empty", AMOUNT_PROJECT_RESOURCES_NO_SEI, rs.getResources().size());
	}

	@Test
	public void testExecute() {
		Command cmd = new CreateSeiResourceAndFileCommand(rs, sei1);
		editingDomain.getCommandStack().execute(cmd);
		
		IFile file = projectCommons.getStructuralElementInstanceFile(sei1);
		IFolder folder = projectCommons.getStructuralElemntInstanceFolder(sei1);		
		IFolder folderDocs = VirSatProjectCommons.getDocumentFolder(sei1);
		
		// Check that the necessary files have been created
		assertTrue("File got correctly created", file.exists());
		assertTrue("Folder got correctly creatred", folder.exists());
		assertTrue("Folder got correctly creatred", folderDocs.exists());

		// Make sure the resource contained in the resource set is the newly created one
		assertEquals("ResourceSet has a new resource", AMOUNT_PROJECT_RESOURCE_WITH_SEI, rs.getResources().size());
		Resource resourceO = rs.getStructuralElementInstanceResource(sei1);
		assertThat("Contains correct resource", rs.getResources(), hasItems(resourceO));
	}

	@Test
	public void testRedo() {
		Command cmd = new CreateSeiResourceAndFileCommand(rs, sei1);
		editingDomain.getCommandStack().execute(cmd);

		IFile file = projectCommons.getStructuralElementInstanceFile(sei1);
		IFolder folder = projectCommons.getStructuralElemntInstanceFolder(sei1);
		
		// Check that the necessary files have been created
		assertTrue("File got correctly created", file.exists());
		assertTrue("Folder got correctly creatred", folder.exists());
		assertEquals("ResourceSet has a new resource", AMOUNT_PROJECT_RESOURCE_WITH_SEI, rs.getResources().size());

		editingDomain.getCommandStack().undo();

		// Check that all files got deleted and that the resource has been removed from the resource set
		assertFalse("File got correctly created", file.exists());
		assertFalse("Folder got correctly creatred", folder.exists());
		assertEquals("ResourceSet is now empty", AMOUNT_PROJECT_RESOURCES_NO_SEI, rs.getResources().size());
		
		editingDomain.getCommandStack().redo();
		
		// Check that the necessary files have been recreated
		assertTrue("File got correctly created", file.exists());
		assertTrue("Folder got correctly creatred", folder.exists());
		assertEquals("ResourceSet has a new resource", AMOUNT_PROJECT_RESOURCE_WITH_SEI, rs.getResources().size());
	}
}
