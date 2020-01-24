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


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.UnexecutableCommand;
import org.eclipse.emf.ecore.EObject;
import org.junit.Test;


import de.dlr.sc.virsat.model.dvlm.DVLMFactory;
import de.dlr.sc.virsat.model.dvlm.Repository;
import de.dlr.sc.virsat.model.dvlm.categories.CategoriesFactory;
import de.dlr.sc.virsat.model.dvlm.categories.Category;
import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;
import de.dlr.sc.virsat.model.dvlm.roles.RoleManagement;
import de.dlr.sc.virsat.model.dvlm.roles.RolesFactory;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElement;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralFactory;
import de.dlr.sc.virsat.project.editingDomain.VirSatTransactionalEditingDomain;
import de.dlr.sc.virsat.project.editingDomain.commands.VirSatEditingDomainClipBoard.ClipboardState;
import de.dlr.sc.virsat.project.test.AProjectTestCase;

/**
 * Tests for the Copy Clipboard Command
 *
 */
public class VirSatCutToClipboardCommandTest extends AProjectTestCase {

	@Override
	public void setUp() throws CoreException {
		super.setUp();
		addEditingDomainAndRepository();
	}

	@Test
	public void testCanUndo() {
		List<EObject> cas = new ArrayList<>();
		cas.add(CategoriesFactory.eINSTANCE.createCategoryAssignment());

		Command one = VirSatCutToClipboardCommand.create((VirSatTransactionalEditingDomain) editingDomain, cas);
		
		assertTrue("Command can be undone", one.canUndo());
	}
	
	@Test
	public void testCanExecute() {
		StructuralElement se = StructuralFactory.eINSTANCE.createStructuralElement();
		StructuralElementInstance sei = StructuralFactory.eINSTANCE.createStructuralElementInstance();
		sei.setType(se);
		
		Category c = CategoriesFactory.eINSTANCE.createCategory();
		c.setIsApplicableForAll(true);
		CategoryAssignment ca = CategoriesFactory.eINSTANCE.createCategoryAssignment();
		ca.setType(c);

		sei.getCategoryAssignments().add(ca);

		Command one = VirSatCutToClipboardCommand.create((VirSatTransactionalEditingDomain) editingDomain, sei.getCategoryAssignments());
		
		assertTrue("Command can be executed", one.canExecute());
		
		CategoryAssignment superCa = CategoriesFactory.eINSTANCE.createCategoryAssignment();
		superCa.setType(c);
		ca.getSuperTis().add(superCa);

		Command two = VirSatCutToClipboardCommand.create((VirSatTransactionalEditingDomain) editingDomain, sei.getCategoryAssignments());
		
		assertFalse("Command can not be executed", two.canExecute());
	}

	@Test
	public void testCreate() {
		List<EObject> seis = new ArrayList<>();
		seis.add(StructuralFactory.eINSTANCE.createStructuralElementInstance());
		seis.add(StructuralFactory.eINSTANCE.createStructuralElementInstance());
		seis.add(StructuralFactory.eINSTANCE.createStructuralElementInstance());

		List<EObject> disciplines = new ArrayList<>();
		disciplines.add(RolesFactory.eINSTANCE.createDiscipline());
		disciplines.add(RolesFactory.eINSTANCE.createDiscipline());
		disciplines.add(RolesFactory.eINSTANCE.createDiscipline());
		disciplines.add(StructuralFactory.eINSTANCE.createStructuralElementInstance());

		Command one = VirSatCutToClipboardCommand.create((VirSatTransactionalEditingDomain) editingDomain, seis);
		Command two = VirSatCutToClipboardCommand.create((VirSatTransactionalEditingDomain) editingDomain, disciplines);
		
		assertTrue("Correct Command", one instanceof VirSatCutToClipboardCommand);
		assertTrue("Correct Command", two instanceof UnexecutableCommand);
	}

	@Test
	public void testExecute() {
		List<EObject> seis = new ArrayList<>();
		seis.add(StructuralFactory.eINSTANCE.createStructuralElementInstance());

		Command one = VirSatCutToClipboardCommand.create((VirSatTransactionalEditingDomain) editingDomain, seis);
		one.execute();
		
		assertEquals("Correct Objects in ED", editingDomain.getClipboard(), seis);
		assertEquals("Correct Clipboard State", VirSatEditingDomainClipBoard.INSTANCE.getClipboardState(editingDomain), ClipboardState.CUT);
	}

	@Test
	public void testUndo() {
		List<EObject> seis = new ArrayList<>();
		seis.add(StructuralFactory.eINSTANCE.createStructuralElementInstance());

		Command one = VirSatCutToClipboardCommand.create((VirSatTransactionalEditingDomain) editingDomain, seis);
		one.undo();
		
		assertNull("Correct Objects in ED", editingDomain.getClipboard());
		assertEquals("Correct Clipboard State", VirSatEditingDomainClipBoard.INSTANCE.getClipboardState(editingDomain), ClipboardState.EMPTY);
	}

	@Test
	public void testRedo() {
		List<EObject> seis = new ArrayList<>();
		seis.add(StructuralFactory.eINSTANCE.createStructuralElementInstance());

		Command one = VirSatCutToClipboardCommand.create((VirSatTransactionalEditingDomain) editingDomain, seis);
		one.undo();
		one.redo();
		
		assertEquals("Correct Objects in ED", editingDomain.getClipboard(), seis);
		assertEquals("Correct Clipboard State", VirSatEditingDomainClipBoard.INSTANCE.getClipboardState(editingDomain), ClipboardState.CUT);
	}
	
	@Test
	public void testCutRepo() {
		assertNull("Clipboard is empty", editingDomain.getClipboard());
		assertEquals("Clipboard has empty state", ClipboardState.EMPTY, VirSatEditingDomainClipBoard.INSTANCE.getClipboardState(editingDomain));
		
		Repository repo = DVLMFactory.eINSTANCE.createRepository();
		boolean canExecute = VirSatCutToClipboardCommand.create(editingDomain, Collections.singleton(repo)).canExecute();
		
		assertFalse("Cannot cut repository", canExecute);
		assertNull("Clipboard is still empty", editingDomain.getClipboard());
		assertEquals("Clipboard still has empty state", ClipboardState.EMPTY, VirSatEditingDomainClipBoard.INSTANCE.getClipboardState(editingDomain));
	}
	
	@Test
	public void testCutRoleManagement() {
		assertNull("Clipboard is empty", editingDomain.getClipboard());
		assertEquals("Clipboard has empty state", ClipboardState.EMPTY, VirSatEditingDomainClipBoard.INSTANCE.getClipboardState(editingDomain));
		
		RoleManagement rm = RolesFactory.eINSTANCE.createRoleManagement();
		boolean canExecute = VirSatCutToClipboardCommand.create(editingDomain, Collections.singleton(rm)).canExecute();
		
		assertFalse("Cannot cut role management", canExecute);
		assertNull("Clipboard is still emptyd", editingDomain.getClipboard());
		assertEquals("Clipboard still has empty state", ClipboardState.EMPTY, VirSatEditingDomainClipBoard.INSTANCE.getClipboardState(editingDomain));
	}
}
