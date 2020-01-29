/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.project.editingDomain;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.command.AbstractCommand;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.DeleteCommand;
import org.junit.Before;
import org.junit.Test;


import de.dlr.sc.virsat.model.dvlm.roles.Discipline;
import de.dlr.sc.virsat.model.dvlm.roles.RoleManagement;
import de.dlr.sc.virsat.model.dvlm.roles.RolesFactory;
import de.dlr.sc.virsat.model.dvlm.roles.RolesPackage;
import de.dlr.sc.virsat.project.test.AProjectTestCase;

/**
 * test Cases for Workspace Command Stack
 *
 */
public class VirSatWorkspaceCommandStackTest extends AProjectTestCase {

	@Before
	public void setUp() throws CoreException {
		super.setUp();
		addEditingDomainAndRepository();
	}
	
	@Test
	public void testExecuteNoUndoWithDeleteCommands() {
		Resource rmResource = rs.getRoleManagementResource();
		RoleManagement rm = rs.getRoleManagement();
		editingDomain.saveResource(rmResource);

		Discipline discipline = RolesFactory.eINSTANCE.createDiscipline();
		discipline.setName("Me");
		Discipline discipline2 = RolesFactory.eINSTANCE.createDiscipline();
		discipline2.setName("You");

		Command cmd = AddCommand.create(editingDomain, rm, RolesPackage.Literals.ROLE_MANAGEMENT__DISCIPLINES, discipline);
		Command cmd2 = AddCommand.create(editingDomain, rm, RolesPackage.Literals.ROLE_MANAGEMENT__DISCIPLINES, discipline2);
		editingDomain.getCommandStack().execute(cmd);
		editingDomain.getCommandStack().execute(cmd2);

		assertThat("There are two disciplines now", rm.getDisciplines(), hasItems(discipline, discipline2));
		
		cmd = DeleteCommand.create(editingDomain, discipline);
		editingDomain.getCommandStack().execute(cmd);

		assertTrue("Can Undo", editingDomain.getCommandStack().canUndo());

		cmd2 = DeleteCommand.create(editingDomain, discipline2);
		editingDomain.getVirSatCommandStack().executeNoUndo(cmd2);

		assertTrue("Can Undo", editingDomain.getCommandStack().canUndo());

		editingDomain.getCommandStack().undo();

		assertEquals("Just one discipline left but also the one from initialization", 2, rm.getDisciplines().size());
		assertThat("There are two disciplines now", rm.getDisciplines(), hasItems(discipline));
	}
	
	@Test
	public void testExecuteNoUndoWithAddCommands() {
		// This was the first Test Case for the undo but failed, since the original EMF ADD
		// Command always takes away the last added objects using the index. Now VirSat is implementing
		// its very own add command and can therefore support this test case.
		Resource rmResource = rs.getRoleManagementResource();
		RoleManagement rm = rs.getRoleManagement();
		editingDomain.saveResource(rmResource);

		Discipline discipline0 = rm.getDisciplines().get(0);
		
		Discipline discipline = RolesFactory.eINSTANCE.createDiscipline();
		discipline.setName("Me");
		Discipline discipline2 = RolesFactory.eINSTANCE.createDiscipline();
		discipline2.setName("You");
		Discipline discipline3 = RolesFactory.eINSTANCE.createDiscipline();
		discipline2.setName("SomeOne");


		Command cmd = AddCommand.create(editingDomain, rm, RolesPackage.Literals.ROLE_MANAGEMENT__DISCIPLINES, discipline);
		Command cmd2 = AddCommand.create(editingDomain, rm, RolesPackage.Literals.ROLE_MANAGEMENT__DISCIPLINES, discipline2);
		Command cmd3 = AddCommand.create(editingDomain, rm, RolesPackage.Literals.ROLE_MANAGEMENT__DISCIPLINES, discipline3);
		editingDomain.getCommandStack().execute(cmd);
		editingDomain.getCommandStack().execute(cmd2);
		editingDomain.getVirSatCommandStack().executeNoUndo(cmd3);

		//CHECKSTYLE:OFF
		assertEquals("There is the correct amount of disciplines", 4, rm.getDisciplines().size());
		assertThat("There are two disciplines now", rm.getDisciplines(), hasItems(discipline0, discipline, discipline2, discipline3));

		editingDomain.getCommandStack().undo();

		assertEquals("There is the correct amount of disciplines", 3, rm.getDisciplines().size());
		assertThat("There are two disciplines now", rm.getDisciplines(), hasItems(discipline0, discipline, discipline3));

		editingDomain.getCommandStack().redo();

		assertEquals("There is the correct amount of disciplines", 4, rm.getDisciplines().size());
		assertThat("There are two disciplines now", rm.getDisciplines(), hasItems(discipline0, discipline, discipline2, discipline3));
		//CHECKSTYLE:ON
	}
	
	@Test
	public void testGetActiveCommand() {
		VirSatWorkspaceCommandStack cmdStack = editingDomain.getVirSatCommandStack();
		
		AbstractCommand cmd = new AbstractCommand() {

			@Override
			public void execute() {
				assertEquals("Correct active command", cmdStack.getActiveCommand(), this);
			}

			@Override
			public void redo() {
			}
			
		};
		
		cmdStack.execute(cmd);
	}
}
