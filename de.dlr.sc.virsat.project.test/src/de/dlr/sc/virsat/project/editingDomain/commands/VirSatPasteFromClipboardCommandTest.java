/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.project.editingDomain.commands;


import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.command.AddCommand;
import org.junit.Test;


import de.dlr.sc.virsat.model.dvlm.categories.CategoriesFactory;
import de.dlr.sc.virsat.model.dvlm.categories.Category;
import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;
import de.dlr.sc.virsat.model.dvlm.roles.Discipline;
import de.dlr.sc.virsat.model.dvlm.roles.RoleManagement;
import de.dlr.sc.virsat.model.dvlm.roles.RolesFactory;
import de.dlr.sc.virsat.model.dvlm.roles.UserRegistry;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElement;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralFactory;
import de.dlr.sc.virsat.project.editingDomain.VirSatEditingDomainRegistry;
import de.dlr.sc.virsat.project.editingDomain.commands.VirSatEditingDomainClipBoard.ClipboardState;
import de.dlr.sc.virsat.project.resources.VirSatResourceSet;
import de.dlr.sc.virsat.project.test.AProjectTestCase;

/** 
 * Test Cases for the Paste COmmand
 */
public class VirSatPasteFromClipboardCommandTest extends AProjectTestCase {

	private StructuralElement se;
	private StructuralElementInstance parentSeiOld;
	private StructuralElementInstance parentSeiNew;
	private StructuralElementInstance childSei;
	private StructuralElementInstance rootSei;
	
	private Category c;
	private CategoryAssignment ca1;
	private CategoryAssignment ca2;
	
	private RoleManagement rm1;
	private RoleManagement rm2;
	
	private Discipline discipline1;
	private Discipline discipline2;
	
	@Override
	public void setUp() throws CoreException {
		super.setUp();
		addEditingDomainAndRepository();
		
		se = StructuralFactory.eINSTANCE.createStructuralElement();
		se.setIsApplicableForAll(true);
		se.setIsRootStructuralElement(true);
		
		parentSeiOld = StructuralFactory.eINSTANCE.createStructuralElementInstance();
		childSei = StructuralFactory.eINSTANCE.createStructuralElementInstance();
		parentSeiNew = StructuralFactory.eINSTANCE.createStructuralElementInstance();
		rootSei = StructuralFactory.eINSTANCE.createStructuralElementInstance();
		
		parentSeiNew.setType(se);
		parentSeiOld.setType(se);
		childSei.setType(se);
		rootSei.setType(se);
		
		parentSeiOld.getChildren().add(childSei);
		
		rootSei.setName("SC");
		parentSeiOld.setName("AOCS_A");
		parentSeiNew.setName("AOCS_B");
		childSei.setName("RW_1");
		
		c = CategoriesFactory.eINSTANCE.createCategory();
		c.setIsApplicableForAll(true);
		
		ca1 = CategoriesFactory.eINSTANCE.createCategoryAssignment();
		ca2 = CategoriesFactory.eINSTANCE.createCategoryAssignment();
		
		ca1.setType(c);
		ca2.setType(c);
		
		ca1.setName("CA_A");
		ca2.setName("CA_B");
		
		parentSeiOld.getCategoryAssignments().add(ca1);
		
		rm1 = RolesFactory.eINSTANCE.createRoleManagement();
		rm2 = RolesFactory.eINSTANCE.createRoleManagement();
		
		discipline1 = RolesFactory.eINSTANCE.createDiscipline();
		discipline2 = RolesFactory.eINSTANCE.createDiscipline();
		
		discipline1.setName("Disc_A");
		discipline2.setName("Disc_B");
		
		rm1.getDisciplines().add(discipline1);
		
		executeAsCommand(() -> repository.getRootEntities().add(rootSei));
	}

	@Override
	public void tearDown() throws CoreException {
		super.tearDown();
		UserRegistry.getInstance().setSuperUser(false);
		
		VirSatResourceSet.clear();
		VirSatEditingDomainRegistry.INSTANCE.clear();
	}
	
	@Test
	public void testCutSeiToSei() {
		VirSatCutToClipboardCommand.create(editingDomain, parentSeiOld.getChildren()).execute();
		VirSatPasteFromClipboardCommand.create(editingDomain, parentSeiNew).execute();
		
		assertTrue("there are no children in the old Sei anymore", parentSeiOld.getChildren().isEmpty());
		assertThat("Child has moved to new parentSei", parentSeiNew.getChildren(), hasItems(childSei));

		assertNull("Clipboard is empty after cut command", editingDomain.getClipboard());
		assertEquals("Clipboard has empty state after cut and paste", ClipboardState.EMPTY, VirSatEditingDomainClipBoard.INSTANCE.getClipboardState(editingDomain));
	}
	
	@Test
	public void testCopySeiToSei() {
		editingDomain.getVirSatCommandStack().execute(VirSatCopyToClipboardCommand.create(editingDomain, parentSeiOld.getChildren()));
		editingDomain.getVirSatCommandStack().execute(VirSatPasteFromClipboardCommand.create(editingDomain, parentSeiOld));
		
		assertEquals("There are now two child SEIs", 2, parentSeiOld.getChildren().size());

		assertEquals("SEI has correct Name", "RW_1", parentSeiOld.getChildren().get(0).getName());
		assertEquals("SEI has correct Name", "RW_2", parentSeiOld.getChildren().get(1).getName());
		
		assertNotNull("Clipboard is not empty after copy command", editingDomain.getClipboard());
		assertEquals("Clipboard remians in copy state", ClipboardState.COPY, VirSatEditingDomainClipBoard.INSTANCE.getClipboardState(editingDomain));
	}

	@Test
	public void testCutSeiToRepository() {
		VirSatCutToClipboardCommand.create(editingDomain, parentSeiOld.getChildren()).execute();
		editingDomain.getCommandStack().execute(VirSatPasteFromClipboardCommand.create(editingDomain, repository));
		
		assertThat("Child has moved to new parentSei", repository.getRootEntities(), hasItems(childSei));
		assertTrue("there are no children in the old Sei anymore", parentSeiOld.getChildren().isEmpty());

		assertNull("Clipboard is empty after cut command", editingDomain.getClipboard());
		assertEquals("Clipboard has empty state after cut and paste", ClipboardState.EMPTY, VirSatEditingDomainClipBoard.INSTANCE.getClipboardState(editingDomain));
	}
	
	@Test
	public void testCopySeiToRepository() {
		rootSei.setAssignedDiscipline(discipline1);
		executeAsCommand(() -> repository.setAssignedDiscipline(discipline2));
		editingDomain.getVirSatCommandStack().execute(VirSatCopyToClipboardCommand.create(editingDomain, Arrays.asList(rootSei)));
		editingDomain.getVirSatCommandStack().execute(VirSatPasteFromClipboardCommand.create(editingDomain, repository));
		
		assertEquals("There are now two child SEIs", 2, repository.getRootEntities().size());

		assertEquals("SEI has correct Name", "SC",   repository.getRootEntities().get(0).getName());
		assertEquals("SEI has correct Name", "SC_2", repository.getRootEntities().get(1).getName());
		assertEquals("SEI has correct Discipline", discipline2, repository.getRootEntities().get(1).getAssignedDiscipline());
		assertNotNull("Clipboard is not empty after copy command", editingDomain.getClipboard());
		assertEquals("Clipboard remians in copy state", ClipboardState.COPY, VirSatEditingDomainClipBoard.INSTANCE.getClipboardState(editingDomain));
	}
	
	@Test
	public void testCutRootSeiToSei() {
		Resource resource = editingDomain.getResourceSet().getRepositoryResource();
		Resource seiResource = editingDomain.getResourceSet().getStructuralElementInstanceResource(parentSeiNew);
		Resource childResource = editingDomain.getResourceSet().getStructuralElementInstanceResource(rootSei);
		editingDomain.getCommandStack().execute(AddCommand.create(editingDomain, resource, Resource.RESOURCE__CONTENTS, repository));
		editingDomain.getCommandStack().execute(AddCommand.create(editingDomain, seiResource, Resource.RESOURCE__CONTENTS, parentSeiNew));
		editingDomain.getCommandStack().execute(AddCommand.create(editingDomain, childResource, Resource.RESOURCE__CONTENTS, rootSei));
		
		// Added the Repo transactionally in order to be found by the paste command
		
		editingDomain.getCommandStack().execute(VirSatCutToClipboardCommand.create(editingDomain, repository.getRootEntities()));
		Command cmd = VirSatPasteFromClipboardCommand.create(editingDomain, parentSeiNew);
		editingDomain.getCommandStack().execute(cmd);
		
		assertThat("Root element has moved to new parentSei", parentSeiNew.getChildren(), hasItems(rootSei));
		assertTrue("there are no children in the repository anymore", repository.getRootEntities().isEmpty());

		assertNull("Clipboard is empty after cut command", editingDomain.getClipboard());
		assertEquals("Clipboard has empty state after cut and paste", ClipboardState.EMPTY, VirSatEditingDomainClipBoard.INSTANCE.getClipboardState(editingDomain));
	}
	
	@Test
	public void testCutSeiToCa() {
		VirSatCutToClipboardCommand.create(editingDomain, parentSeiOld.getChildren()).execute();
		
		boolean canExecute = VirSatPasteFromClipboardCommand.create(editingDomain, ca1).canExecute();
		assertFalse("Cannot execute paste on invalid owner", canExecute);

		assertNotNull("Clipboard is not empty after canExecute check", editingDomain.getClipboard());
		assertEquals("Clipboard is still in cut state", ClipboardState.CUT, VirSatEditingDomainClipBoard.INSTANCE.getClipboardState(editingDomain));
	}
	
	@Test
	public void testCutSeiToRoleManagement() {
		VirSatCutToClipboardCommand.create(editingDomain, parentSeiOld.getChildren()).execute();
		
		boolean canExecute = VirSatPasteFromClipboardCommand.create(editingDomain, rm1).canExecute();
		assertFalse("Cannot execute paste on invalid owner", canExecute);

		assertNotNull("Clipboard is not empty after canExecute check", editingDomain.getClipboard());
		assertEquals("Clipboard is still in cut state", ClipboardState.CUT, VirSatEditingDomainClipBoard.INSTANCE.getClipboardState(editingDomain));
	}

	@Test
	public void testCutDisciplineToRoleManagement() {
		VirSatCutToClipboardCommand.create(editingDomain, rm1.getDisciplines()).execute();
		VirSatPasteFromClipboardCommand.create(editingDomain, rm2).execute();
		
		assertThat("Discipline has moved to new role management", rm2.getDisciplines(), hasItems(discipline1));
		assertTrue("there are no discipline in the role management anymore", rm1.getDisciplines().isEmpty());

		assertNull("Clipboard is empty after cut command", editingDomain.getClipboard());
		assertEquals("Clipboard has empty state after cut and paste", ClipboardState.EMPTY, VirSatEditingDomainClipBoard.INSTANCE.getClipboardState(editingDomain));
	}
	
	@Test
	public void testCopyDisciplineToRoleManagement() {
		editingDomain.getVirSatCommandStack().execute(VirSatCopyToClipboardCommand.create(editingDomain, rm1.getDisciplines()));
		editingDomain.getVirSatCommandStack().execute(VirSatPasteFromClipboardCommand.create(editingDomain, rm2));
		editingDomain.getVirSatCommandStack().execute(VirSatPasteFromClipboardCommand.create(editingDomain, rm2));
		editingDomain.getVirSatCommandStack().execute(VirSatPasteFromClipboardCommand.create(editingDomain, rm2));
		
		//CHECKSTYLE:OFF
		assertEquals("Correct Amount of Disciplines", 1, rm1.getDisciplines().size());
		assertEquals("Correct Amount of Disciplines", 3, rm2.getDisciplines().size());
		//CHECKSTYLE:ON

		assertEquals("Discipline has correct name", "Disc_A",   rm2.getDisciplines().get(0).getName());
		assertEquals("Discipline has correct name", "Disc_A_2", rm2.getDisciplines().get(1).getName());
		assertEquals("Discipline has correct name", "Disc_A_3", rm2.getDisciplines().get(2).getName());

		assertNotNull("Clipboard is not empty after copy command", editingDomain.getClipboard());
		assertEquals("Clipboard remians in copy state", ClipboardState.COPY, VirSatEditingDomainClipBoard.INSTANCE.getClipboardState(editingDomain));
	}
	
	@Test
	public void testCutDisciplineToDiscipline() {
		VirSatCutToClipboardCommand.create(editingDomain, rm1.getDisciplines()).execute();
		
		boolean canExecute = VirSatPasteFromClipboardCommand.create(editingDomain, discipline2).canExecute();
		assertFalse("Cannot execute paste on invalid owner", canExecute);

		assertNotNull("Clipboard is not empty after canExecute check", editingDomain.getClipboard());
		assertEquals("Clipboard is still in cut state", ClipboardState.CUT, VirSatEditingDomainClipBoard.INSTANCE.getClipboardState(editingDomain));
	}
	
	@Test
	public void testCutDisciplineToSei() {
		VirSatCutToClipboardCommand.create(editingDomain, rm1.getDisciplines()).execute();
		
		boolean canExecute = VirSatPasteFromClipboardCommand.create(editingDomain, childSei).canExecute();
		assertFalse("Cannot execute paste on invalid owner", canExecute);

		assertNotNull("Clipboard is not empty after canExecute check", editingDomain.getClipboard());
		assertEquals("Clipboard is still in cut state", ClipboardState.CUT, VirSatEditingDomainClipBoard.INSTANCE.getClipboardState(editingDomain));
	}
	
	@Test
	public void testCutDisciplineToRepository() {
		VirSatCutToClipboardCommand.create(editingDomain, rm1.getDisciplines()).execute();
		
		boolean canExecute = VirSatPasteFromClipboardCommand.create(editingDomain, repository).canExecute();
		assertFalse("Cannot execute paste on invalid owner", canExecute);

		assertNotNull("Clipboard is not empty after canExecute check", editingDomain.getClipboard());
		assertEquals("Clipboard is still in cut state", ClipboardState.CUT, VirSatEditingDomainClipBoard.INSTANCE.getClipboardState(editingDomain));
	}
	
	@Test
	public void testCutDisciplineToCa() {
		VirSatCutToClipboardCommand.create(editingDomain, rm1.getDisciplines()).execute();
		
		boolean canExecute = VirSatPasteFromClipboardCommand.create(editingDomain, ca1).canExecute();
		assertFalse("Cannot execute paste on invalid owner", canExecute);

		assertNotNull("Clipboard is not empty after canExecute check", editingDomain.getClipboard());
		assertEquals("Clipboard is still in cut state", ClipboardState.CUT, VirSatEditingDomainClipBoard.INSTANCE.getClipboardState(editingDomain));
	}
	
	@Test
	public void testCutCaToSei() {
		VirSatCutToClipboardCommand.create(editingDomain, parentSeiOld.getCategoryAssignments()).execute();
		VirSatPasteFromClipboardCommand.create(editingDomain, parentSeiNew).execute();
		
		assertThat("Category assignment has moved to new sei", parentSeiNew.getCategoryAssignments(), hasItems(ca1));
		assertTrue("There are no category assignments in the old sei anymore", parentSeiOld.getCategoryAssignments().isEmpty());

		assertNull("Clipboard is empty after cut command", editingDomain.getClipboard());
		assertEquals("Clipboard has empty state after cut and paste", ClipboardState.EMPTY, VirSatEditingDomainClipBoard.INSTANCE.getClipboardState(editingDomain));
	}
	
	@Test
	public void testCopyCaToSei() {
		editingDomain.getVirSatCommandStack().execute(VirSatCopyToClipboardCommand.create(editingDomain, parentSeiOld.getCategoryAssignments()));
		editingDomain.getVirSatCommandStack().execute(VirSatPasteFromClipboardCommand.create(editingDomain, parentSeiOld));
		editingDomain.getVirSatCommandStack().execute(VirSatPasteFromClipboardCommand.create(editingDomain, parentSeiOld));
		
		assertEquals("SEI has correct CA", "CA_A",   parentSeiOld.getCategoryAssignments().get(0).getName());
		assertEquals("SEI has correct CA", "CA_A_2",   parentSeiOld.getCategoryAssignments().get(1).getName());
		assertEquals("SEI has correct CA", "CA_A_3",   parentSeiOld.getCategoryAssignments().get(2).getName());

		assertNotNull("Clipboard is not empty after copy command", editingDomain.getClipboard());
		assertEquals("Clipboard remians in copy state", ClipboardState.COPY, VirSatEditingDomainClipBoard.INSTANCE.getClipboardState(editingDomain));
	}
	
	@Test
	public void testCaToCa() {
		VirSatCutToClipboardCommand.create(editingDomain, parentSeiOld.getCategoryAssignments()).execute();
		
		boolean canExecute = VirSatPasteFromClipboardCommand.create(editingDomain, ca2).canExecute();
		assertFalse("Cannot execute paste on invalid owner", canExecute);

		assertNotNull("Clipboard is not empty after canExecute check", editingDomain.getClipboard());
		assertEquals("Clipboard is still in cut state", ClipboardState.CUT, VirSatEditingDomainClipBoard.INSTANCE.getClipboardState(editingDomain));
	}
	
	@Test
	public void testCaToRepository() {
		VirSatCutToClipboardCommand.create(editingDomain, parentSeiOld.getCategoryAssignments()).execute();
		
		boolean canExecute = VirSatPasteFromClipboardCommand.create(editingDomain, repository).canExecute();
		assertFalse("Cannot execute paste on invalid owner", canExecute);

		assertNotNull("Clipboard is not empty after canExecute check", editingDomain.getClipboard());
		assertEquals("Clipboard is still in cut state", ClipboardState.CUT, VirSatEditingDomainClipBoard.INSTANCE.getClipboardState(editingDomain));
	}
	
	@Test
	public void testCaToRoleManagement() {
		VirSatCutToClipboardCommand.create(editingDomain, parentSeiOld.getCategoryAssignments()).execute();
		
		boolean canExecute = VirSatPasteFromClipboardCommand.create(editingDomain, rm1).canExecute();
		assertFalse("Cannot execute paste on invalid owner", canExecute);

		assertNotNull("Clipboard is not empty after canExecute check", editingDomain.getClipboard());
		assertEquals("Clipboard is still in cut state", ClipboardState.CUT, VirSatEditingDomainClipBoard.INSTANCE.getClipboardState(editingDomain));
	}
	
	@Test
	public void testCaToDiscipline() {
		VirSatCutToClipboardCommand.create(editingDomain, parentSeiOld.getCategoryAssignments()).execute();
		
		boolean canExecute = VirSatPasteFromClipboardCommand.create(editingDomain, discipline1).canExecute();
		assertFalse("Cannot execute paste on invalid owner", canExecute);

		assertNotNull("Clipboard is not empty after canExecute check", editingDomain.getClipboard());
		assertEquals("Clipboard is still in cut state", ClipboardState.CUT, VirSatEditingDomainClipBoard.INSTANCE.getClipboardState(editingDomain));
	}
}
