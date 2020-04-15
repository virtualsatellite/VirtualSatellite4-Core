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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.SetCommand;
import org.junit.Test;


import de.dlr.sc.virsat.model.dvlm.roles.Discipline;
import de.dlr.sc.virsat.model.dvlm.roles.RoleManagement;
import de.dlr.sc.virsat.model.dvlm.roles.RolesFactory;
import de.dlr.sc.virsat.model.dvlm.roles.RolesPackage;
import de.dlr.sc.virsat.model.dvlm.roles.UserRegistry;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralFactory;
import de.dlr.sc.virsat.project.editingDomain.VirSatEditingDomainRegistry;
import de.dlr.sc.virsat.project.resources.VirSatResourceSet;
import de.dlr.sc.virsat.project.test.AProjectTestCase;

/**
 * Test case for testing the AssignDisciplineCommand.
 */
public class AssignDisciplineCommandTest extends AProjectTestCase {

	private StructuralElementInstance sei;
	private Discipline discipline;
	
	@Override
	public void setUp() throws CoreException {
		super.setUp();
		addEditingDomainAndRepository();
		
		discipline = RolesFactory.eINSTANCE.createDiscipline();
		sei = StructuralFactory.eINSTANCE.createStructuralElementInstance();

		Command cmd = rs.initializeModelsAndResourceSet(null, editingDomain);
		editingDomain.getCommandStack().execute(cmd);
		
		RoleManagement rm = rs.getRoleManagement();
		
		cmd = AddCommand.create(editingDomain, rm, RolesPackage.Literals.ROLE_MANAGEMENT__DISCIPLINES, discipline);
		editingDomain.getCommandStack().execute(cmd);
		
		cmd = new CreateSeiResourceAndFileCommand(rs, sei);
		editingDomain.getCommandStack().execute(cmd);
		
		editingDomain.saveAll();
	}

	@Override
	public void tearDown() throws CoreException {
		super.tearDown();
		
		VirSatResourceSet.clear();
		VirSatEditingDomainRegistry.INSTANCE.clear();
	}
	
	@Test
	public void testPrepare() {
		AssignDisciplineCommand cmd = new AssignDisciplineCommand(editingDomain, sei, discipline);
		assertTrue("Command can be prepared", cmd.prepare());
	}

	@Test
	public void testCanUndo() {
		AssignDisciplineCommand cmd = new AssignDisciplineCommand(editingDomain, sei, discipline);
		assertFalse("Command cannot be undone", cmd.canUndo());
	}

	@Test
	public void testExecute() {
		Resource seiResource = sei.eResource();
		
		AssignDisciplineCommand cmd = new AssignDisciplineCommand(editingDomain, sei, discipline);
		assertTrue("Command can be executed", cmd.canExecute());
		
		UserRegistry.getInstance().setSuperUser(true);
		editingDomain.getCommandStack().execute(cmd);
		assertEquals("Assigned Discipline is correct", discipline, sei.getAssignedDiscipline());
		assertFalse("Since we always save the resource in the AssignDisciplineCommand, it should not be dirty", editingDomain.isDirty(seiResource));
		UserRegistry.getInstance().setSuperUser(false);
		
		cmd = new AssignDisciplineCommand(editingDomain, sei, null);
		editingDomain.getCommandStack().execute(cmd);
		assertEquals("Assigned Discipline has not changed since we dont have any rights", discipline, sei.getAssignedDiscipline());
	}
	
	@Test
	public void testCanExecute() {
		UserRegistry.getInstance().setSuperUser(false);
		AssignDisciplineCommand cmd = new AssignDisciplineCommand(editingDomain, sei, discipline);
		assertFalse("There is no user assigned yet, only the super use can execute the command", cmd.canExecute());
		
		UserRegistry.getInstance().setSuperUser(true);
		assertTrue("The super user can always set the discipline", cmd.canExecute());
		editingDomain.getCommandStack().execute(cmd);
		
		Discipline someOtherDiscipline = RolesFactory.eINSTANCE.createDiscipline();
		AssignDisciplineCommand cmdSetOtherDiscipline = new AssignDisciplineCommand(editingDomain, sei, someOtherDiscipline);
		
		// now see if the command can be executed as some random user
		editingDomain.getCommandStack().execute(
			SetCommand.create(editingDomain, discipline, RolesPackage.eINSTANCE.getDiscipline_User(), "RandomUser")
		);
		
		UserRegistry.getInstance().setSuperUser(false);
		assertFalse("The command cannot be executed", cmdSetOtherDiscipline.canExecute());
		
		// now set the user to be the user from the registry
		String registeredUser = UserRegistry.getInstance().getUserName();
		editingDomain.getCommandStack().execute(
			SetCommand.create(editingDomain, discipline, RolesPackage.eINSTANCE.getDiscipline_User(), registeredUser)
		);
		assertTrue("With the correct user, the command can be executed", cmdSetOtherDiscipline.canExecute());
		
		// Now create the command without an editing domain which should use the standard user registration
		AssignDisciplineCommand cmdSetOtherNoEd = new AssignDisciplineCommand(null, sei, someOtherDiscipline);
		assertTrue("With the correct user, the command can be executed", cmdSetOtherNoEd.canExecute());
		
	}
}
