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


import de.dlr.sc.virsat.model.dvlm.DVLMFactory;
import de.dlr.sc.virsat.model.dvlm.Repository;
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
import de.dlr.sc.virsat.project.editingDomain.VirSatTransactionalEditingDomain;
import de.dlr.sc.virsat.project.editingDomain.commands.VirSatEditingDomainClipBoard.ClipboardState;
import de.dlr.sc.virsat.project.resources.VirSatResourceSet;
import de.dlr.sc.virsat.project.structure.VirSatProjectCommons;
import de.dlr.sc.virsat.project.test.AProjectTestCase;

/** 
 * Test Cases for the Paste COmmand
 * @author fisc_ph
 *
 */
public class VirSatPasteFromClipboardCommandTest extends AProjectTestCase {

	private VirSatProjectCommons projectCommons;
	private VirSatTransactionalEditingDomain rsEd;
	
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
	
	private Repository repo;
	
	@Override
	public void setUp() throws CoreException {
		super.setUp();
		
		projectCommons = new VirSatProjectCommons(testProject);
		projectCommons.createProjectStructure(null);
		
		VirSatResourceSet.getResourceSet(testProject, false);
		rsEd = VirSatEditingDomainRegistry.INSTANCE.getEd(testProject);
		UserRegistry.getInstance().setSuperUser(true);
		
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
		
		repo = DVLMFactory.eINSTANCE.createRepository();
		repo.getRootEntities().add(rootSei);
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
		VirSatCutToClipboardCommand.create(rsEd, parentSeiOld.getChildren()).execute();
		VirSatPasteFromClipboardCommand.create(rsEd, parentSeiNew).execute();
		
		assertTrue("there are no children in the old Sei anymore", parentSeiOld.getChildren().isEmpty());
		assertThat("Child has moved to new parentSei", parentSeiNew.getChildren(), hasItems(childSei));

		assertNull("Clipboard is empty after cut command", rsEd.getClipboard());
		assertEquals("Clipboard has empty state after cut and paste", ClipboardState.EMPTY, VirSatEditingDomainClipBoard.INSTANCE.getClipboardState(rsEd));
	}
	
	@Test
	public void testCopySeiToSei() {
		rsEd.getVirSatCommandStack().execute(VirSatCopyToClipboardCommand.create(rsEd, parentSeiOld.getChildren()));
		rsEd.getVirSatCommandStack().execute(VirSatPasteFromClipboardCommand.create(rsEd, parentSeiOld));
		
		assertEquals("There are now two child SEIs", 2, parentSeiOld.getChildren().size());

		assertEquals("SEI has correct Name", "RW_1", parentSeiOld.getChildren().get(0).getName());
		assertEquals("SEI has correct Name", "RW_2", parentSeiOld.getChildren().get(1).getName());
		
		assertNotNull("Clipboard is not empty after copy command", rsEd.getClipboard());
		assertEquals("Clipboard remians in copy state", ClipboardState.COPY, VirSatEditingDomainClipBoard.INSTANCE.getClipboardState(rsEd));
	}

	@Test
	public void testCutSeiToRepository() {
		VirSatCutToClipboardCommand.create(rsEd, parentSeiOld.getChildren()).execute();
		VirSatPasteFromClipboardCommand.create(rsEd, repo).execute();
		
		assertThat("Child has moved to new parentSei", repo.getRootEntities(), hasItems(childSei));
		assertTrue("there are no children in the old Sei anymore", parentSeiOld.getChildren().isEmpty());

		assertNull("Clipboard is empty after cut command", rsEd.getClipboard());
		assertEquals("Clipboard has empty state after cut and paste", ClipboardState.EMPTY, VirSatEditingDomainClipBoard.INSTANCE.getClipboardState(rsEd));
	}
	
	@Test
	public void testCopySeiToRepository() {
		rootSei.setAssignedDiscipline(discipline1);
		repo.setAssignedDiscipline(discipline2);
		rsEd.getVirSatCommandStack().execute(VirSatCopyToClipboardCommand.create(rsEd, Arrays.asList(rootSei)));
		rsEd.getVirSatCommandStack().execute(VirSatPasteFromClipboardCommand.create(rsEd, repo));
		
		assertEquals("There are now two child SEIs", 2, repo.getRootEntities().size());

		assertEquals("SEI has correct Name", "SC",   repo.getRootEntities().get(0).getName());
		assertEquals("SEI has correct Name", "SC_2", repo.getRootEntities().get(1).getName());
		assertEquals("SEI has correct Discipline", discipline2, repo.getRootEntities().get(1).getAssignedDiscipline());
		assertNotNull("Clipboard is not empty after copy command", rsEd.getClipboard());
		assertEquals("Clipboard remians in copy state", ClipboardState.COPY, VirSatEditingDomainClipBoard.INSTANCE.getClipboardState(rsEd));
	}
	
	@Test
	public void testCutRootSeiToSei() {
		Resource resource = rsEd.getResourceSet().getRepositoryResource();
		Resource seiResource = rsEd.getResourceSet().getStructuralElementInstanceResource(parentSeiNew);
		Resource childResource = rsEd.getResourceSet().getStructuralElementInstanceResource(rootSei);
		rsEd.getCommandStack().execute(AddCommand.create(rsEd, resource, Resource.RESOURCE__CONTENTS, repo));
		rsEd.getCommandStack().execute(AddCommand.create(rsEd, seiResource, Resource.RESOURCE__CONTENTS, parentSeiNew));
		rsEd.getCommandStack().execute(AddCommand.create(rsEd, childResource, Resource.RESOURCE__CONTENTS, rootSei));
		
		// Added the Repo transactionally in order to be found by the paste command
		
		rsEd.getCommandStack().execute(VirSatCutToClipboardCommand.create(rsEd, repo.getRootEntities()));
		Command cmd = VirSatPasteFromClipboardCommand.create(rsEd, parentSeiNew);
		rsEd.getCommandStack().execute(cmd);
		
		assertThat("Root element has moved to new parentSei", parentSeiNew.getChildren(), hasItems(rootSei));
		assertTrue("there are no children in the repository anymore", repo.getRootEntities().isEmpty());

		assertNull("Clipboard is empty after cut command", rsEd.getClipboard());
		assertEquals("Clipboard has empty state after cut and paste", ClipboardState.EMPTY, VirSatEditingDomainClipBoard.INSTANCE.getClipboardState(rsEd));
	}
	
	@Test
	public void testCutSeiToCa() {
		VirSatCutToClipboardCommand.create(rsEd, parentSeiOld.getChildren()).execute();
		
		boolean canExecute = VirSatPasteFromClipboardCommand.create(rsEd, ca1).canExecute();
		assertFalse("Cannot execute paste on invalid owner", canExecute);

		assertNotNull("Clipboard is not empty after canExecute check", rsEd.getClipboard());
		assertEquals("Clipboard is still in cut state", ClipboardState.CUT, VirSatEditingDomainClipBoard.INSTANCE.getClipboardState(rsEd));
	}
	
	@Test
	public void testCutSeiToRoleManagement() {
		VirSatCutToClipboardCommand.create(rsEd, parentSeiOld.getChildren()).execute();
		
		boolean canExecute = VirSatPasteFromClipboardCommand.create(rsEd, rm1).canExecute();
		assertFalse("Cannot execute paste on invalid owner", canExecute);

		assertNotNull("Clipboard is not empty after canExecute check", rsEd.getClipboard());
		assertEquals("Clipboard is still in cut state", ClipboardState.CUT, VirSatEditingDomainClipBoard.INSTANCE.getClipboardState(rsEd));
	}

	@Test
	public void testCutDisciplineToRoleManagement() {
		VirSatCutToClipboardCommand.create(rsEd, rm1.getDisciplines()).execute();
		VirSatPasteFromClipboardCommand.create(rsEd, rm2).execute();
		
		assertThat("Discipline has moved to new role management", rm2.getDisciplines(), hasItems(discipline1));
		assertTrue("there are no discipline in the role management anymore", rm1.getDisciplines().isEmpty());

		assertNull("Clipboard is empty after cut command", rsEd.getClipboard());
		assertEquals("Clipboard has empty state after cut and paste", ClipboardState.EMPTY, VirSatEditingDomainClipBoard.INSTANCE.getClipboardState(rsEd));
	}
	
	@Test
	public void testCopyDisciplineToRoleManagement() {
		rsEd.getVirSatCommandStack().execute(VirSatCopyToClipboardCommand.create(rsEd, rm1.getDisciplines()));
		rsEd.getVirSatCommandStack().execute(VirSatPasteFromClipboardCommand.create(rsEd, rm2));
		rsEd.getVirSatCommandStack().execute(VirSatPasteFromClipboardCommand.create(rsEd, rm2));
		rsEd.getVirSatCommandStack().execute(VirSatPasteFromClipboardCommand.create(rsEd, rm2));
		
		//CHECKSTYLE:OFF
		assertEquals("Correct Amount of Disciplines", 1, rm1.getDisciplines().size());
		assertEquals("Correct Amount of Disciplines", 3, rm2.getDisciplines().size());
		//CHECKSTYLE:ON

		assertEquals("Discipline has correct name", "Disc_A",   rm2.getDisciplines().get(0).getName());
		assertEquals("Discipline has correct name", "Disc_A_2", rm2.getDisciplines().get(1).getName());
		assertEquals("Discipline has correct name", "Disc_A_3", rm2.getDisciplines().get(2).getName());

		assertNotNull("Clipboard is not empty after copy command", rsEd.getClipboard());
		assertEquals("Clipboard remians in copy state", ClipboardState.COPY, VirSatEditingDomainClipBoard.INSTANCE.getClipboardState(rsEd));
	}
	
	@Test
	public void testCutDisciplineToDiscipline() {
		VirSatCutToClipboardCommand.create(rsEd, rm1.getDisciplines()).execute();
		
		boolean canExecute = VirSatPasteFromClipboardCommand.create(rsEd, discipline2).canExecute();
		assertFalse("Cannot execute paste on invalid owner", canExecute);

		assertNotNull("Clipboard is not empty after canExecute check", rsEd.getClipboard());
		assertEquals("Clipboard is still in cut state", ClipboardState.CUT, VirSatEditingDomainClipBoard.INSTANCE.getClipboardState(rsEd));
	}
	
	@Test
	public void testCutDisciplineToSei() {
		VirSatCutToClipboardCommand.create(rsEd, rm1.getDisciplines()).execute();
		
		boolean canExecute = VirSatPasteFromClipboardCommand.create(rsEd, childSei).canExecute();
		assertFalse("Cannot execute paste on invalid owner", canExecute);

		assertNotNull("Clipboard is not empty after canExecute check", rsEd.getClipboard());
		assertEquals("Clipboard is still in cut state", ClipboardState.CUT, VirSatEditingDomainClipBoard.INSTANCE.getClipboardState(rsEd));
	}
	
	@Test
	public void testCutDisciplineToRepository() {
		VirSatCutToClipboardCommand.create(rsEd, rm1.getDisciplines()).execute();
		
		boolean canExecute = VirSatPasteFromClipboardCommand.create(rsEd, repo).canExecute();
		assertFalse("Cannot execute paste on invalid owner", canExecute);

		assertNotNull("Clipboard is not empty after canExecute check", rsEd.getClipboard());
		assertEquals("Clipboard is still in cut state", ClipboardState.CUT, VirSatEditingDomainClipBoard.INSTANCE.getClipboardState(rsEd));
	}
	
	@Test
	public void testCutDisciplineToCa() {
		VirSatCutToClipboardCommand.create(rsEd, rm1.getDisciplines()).execute();
		
		boolean canExecute = VirSatPasteFromClipboardCommand.create(rsEd, ca1).canExecute();
		assertFalse("Cannot execute paste on invalid owner", canExecute);

		assertNotNull("Clipboard is not empty after canExecute check", rsEd.getClipboard());
		assertEquals("Clipboard is still in cut state", ClipboardState.CUT, VirSatEditingDomainClipBoard.INSTANCE.getClipboardState(rsEd));
	}
	
	@Test
	public void testCutCaToSei() {
		VirSatCutToClipboardCommand.create(rsEd, parentSeiOld.getCategoryAssignments()).execute();
		VirSatPasteFromClipboardCommand.create(rsEd, parentSeiNew).execute();
		
		assertThat("Category assignment has moved to new sei", parentSeiNew.getCategoryAssignments(), hasItems(ca1));
		assertTrue("There are no category assignments in the old sei anymore", parentSeiOld.getCategoryAssignments().isEmpty());

		assertNull("Clipboard is empty after cut command", rsEd.getClipboard());
		assertEquals("Clipboard has empty state after cut and paste", ClipboardState.EMPTY, VirSatEditingDomainClipBoard.INSTANCE.getClipboardState(rsEd));
	}
	
	@Test
	public void testCopyCaToSei() {
		rsEd.getVirSatCommandStack().execute(VirSatCopyToClipboardCommand.create(rsEd, parentSeiOld.getCategoryAssignments()));
		rsEd.getVirSatCommandStack().execute(VirSatPasteFromClipboardCommand.create(rsEd, parentSeiOld));
		rsEd.getVirSatCommandStack().execute(VirSatPasteFromClipboardCommand.create(rsEd, parentSeiOld));
		
		assertEquals("SEI has correct CA", "CA_A",   parentSeiOld.getCategoryAssignments().get(0).getName());
		assertEquals("SEI has correct CA", "CA_A_2",   parentSeiOld.getCategoryAssignments().get(1).getName());
		assertEquals("SEI has correct CA", "CA_A_3",   parentSeiOld.getCategoryAssignments().get(2).getName());

		assertNotNull("Clipboard is not empty after copy command", rsEd.getClipboard());
		assertEquals("Clipboard remians in copy state", ClipboardState.COPY, VirSatEditingDomainClipBoard.INSTANCE.getClipboardState(rsEd));
	}
	
	@Test
	public void testCaToCa() {
		VirSatCutToClipboardCommand.create(rsEd, parentSeiOld.getCategoryAssignments()).execute();
		
		boolean canExecute = VirSatPasteFromClipboardCommand.create(rsEd, ca2).canExecute();
		assertFalse("Cannot execute paste on invalid owner", canExecute);

		assertNotNull("Clipboard is not empty after canExecute check", rsEd.getClipboard());
		assertEquals("Clipboard is still in cut state", ClipboardState.CUT, VirSatEditingDomainClipBoard.INSTANCE.getClipboardState(rsEd));
	}
	
	@Test
	public void testCaToRepository() {
		VirSatCutToClipboardCommand.create(rsEd, parentSeiOld.getCategoryAssignments()).execute();
		
		boolean canExecute = VirSatPasteFromClipboardCommand.create(rsEd, repo).canExecute();
		assertFalse("Cannot execute paste on invalid owner", canExecute);

		assertNotNull("Clipboard is not empty after canExecute check", rsEd.getClipboard());
		assertEquals("Clipboard is still in cut state", ClipboardState.CUT, VirSatEditingDomainClipBoard.INSTANCE.getClipboardState(rsEd));
	}
	
	@Test
	public void testCaToRoleManagement() {
		VirSatCutToClipboardCommand.create(rsEd, parentSeiOld.getCategoryAssignments()).execute();
		
		boolean canExecute = VirSatPasteFromClipboardCommand.create(rsEd, rm1).canExecute();
		assertFalse("Cannot execute paste on invalid owner", canExecute);

		assertNotNull("Clipboard is not empty after canExecute check", rsEd.getClipboard());
		assertEquals("Clipboard is still in cut state", ClipboardState.CUT, VirSatEditingDomainClipBoard.INSTANCE.getClipboardState(rsEd));
	}
	
	@Test
	public void testCaToDiscipline() {
		VirSatCutToClipboardCommand.create(rsEd, parentSeiOld.getCategoryAssignments()).execute();
		
		boolean canExecute = VirSatPasteFromClipboardCommand.create(rsEd, discipline1).canExecute();
		assertFalse("Cannot execute paste on invalid owner", canExecute);

		assertNotNull("Clipboard is not empty after canExecute check", rsEd.getClipboard());
		assertEquals("Clipboard is still in cut state", ClipboardState.CUT, VirSatEditingDomainClipBoard.INSTANCE.getClipboardState(rsEd));
	}
}
