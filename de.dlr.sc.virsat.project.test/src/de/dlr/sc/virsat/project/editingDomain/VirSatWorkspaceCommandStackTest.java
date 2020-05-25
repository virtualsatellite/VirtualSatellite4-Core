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
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.command.AbstractCommand;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.DeleteCommand;
import org.eclipse.emf.edit.command.SetCommand;
import org.junit.Before;
import org.junit.Test;
import de.dlr.sc.virsat.model.dvlm.DVLMPackage;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.dvlm.concepts.ConceptsFactory;
import de.dlr.sc.virsat.model.dvlm.general.GeneralPackage;
import de.dlr.sc.virsat.model.dvlm.roles.Discipline;
import de.dlr.sc.virsat.model.dvlm.roles.IUserContext;
import de.dlr.sc.virsat.model.dvlm.roles.RoleManagement;
import de.dlr.sc.virsat.model.dvlm.roles.RolesFactory;
import de.dlr.sc.virsat.model.dvlm.roles.RolesPackage;
import de.dlr.sc.virsat.model.dvlm.roles.UserRegistry;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElement;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralFactory;
import de.dlr.sc.virsat.project.structure.VirSatProjectCommons;
import de.dlr.sc.virsat.project.test.AProjectTestCase;
import de.dlr.sc.virsat.project.test.TestInvocationCheckBuilder;
import de.dlr.sc.virsat.project.test.TestNature;

/**
 * test Cases for Workspace Command Stack
 *
 */
public class VirSatWorkspaceCommandStackTest extends AProjectTestCase {

	@Before
	public void setUp() throws CoreException {
		super.setUp();
		addEditingDomainAndRepository();
		projectCommons.attachProjectNature();
		projectCommons.attachProjectNature(TestNature.NATURE_ID);
	}
	
	@Override
	public void tearDown() throws CoreException {
		VirSatProjectCommons.setEnableWorkspaceBuilder(true);
		super.tearDown();
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
	
	@Test
	public void executeNoUndoIUserContext() {
		// Create a Sei and try to change its name with various different user contexts
		UserRegistry.getInstance().setSuperUser(true);
		Resource rmResource = rs.getRoleManagementResource();
		RoleManagement rm = rs.getRoleManagement();
		editingDomain.saveResource(rmResource);

		// Create a user
		Discipline discipline = RolesFactory.eINSTANCE.createDiscipline();
		discipline.setName("Me");
		discipline.setUser("Me_user");
		Command cmd = AddCommand.create(editingDomain, rm, RolesPackage.Literals.ROLE_MANAGEMENT__DISCIPLINES, discipline);
		editingDomain.getCommandStack().execute(cmd);
		
		// Create an SE for typing and place it into a concept
		StructuralElement se = StructuralFactory.eINSTANCE.createStructuralElement();
		se.setIsCanInheritFromAll(true);
		se.setIsApplicableForAll(true);
		se.setIsRootStructuralElement(true);
		
		Concept concept = ConceptsFactory.eINSTANCE.createConcept();
		concept.getStructuralElements().add(se);
		
		Command addConceptCommand = AddCommand.create(editingDomain, repository, DVLMPackage.eINSTANCE.getRepository_ActiveConcepts(), concept);
		editingDomain.getCommandStack().execute(addConceptCommand);
		
		// Now create a SEI, type it with the SE, assign the discipline
		StructuralElementInstance sei = StructuralFactory.eINSTANCE.createStructuralElementInstance();
		sei.setAssignedDiscipline(discipline);
		sei.setType(se);
		
		Command addSeiCommand = AddCommand.create(editingDomain, repository, DVLMPackage.eINSTANCE.getRepository_RootEntities(), sei);
		editingDomain.getCommandStack().execute(addSeiCommand);
		UserRegistry.getInstance().setSuperUser(false);

		// Now try to change the name of the sei, and use the system user context
		Command setSeiName = SetCommand.create(editingDomain, sei, GeneralPackage.eINSTANCE.getIName_Name(), "newName");
		editingDomain.getVirSatCommandStack().executeNoUndo(setSeiName, UserRegistry.getInstance(), false);
		
		assertNull("The name has not been changed", sei.getName());
		
		// Try to hange the name again with the correct context, and the SEI name should be changed.
		editingDomain.getVirSatCommandStack().executeNoUndo(setSeiName, new IUserContext() {
			
			@Override
			public boolean isSuperUser() {
				return false;
			}
			
			@Override
			public String getUserName() {
				return "Me_user";
			}
		}, false);
		
		assertEquals("Sei Name has been changed", "newName", sei.getName());
	}
	
	@Test
	public void executeNoUndoAutoBuild() throws CoreException {
		// Create a Sei that inherits from another one. The builder should be called with the suer context
		VirSatProjectCommons.setEnableWorkspaceBuilder(false);
		UserRegistry.getInstance().setSuperUser(true);
		Resource rmResource = rs.getRoleManagementResource();
		RoleManagement rm = rs.getRoleManagement();
		editingDomain.saveResource(rmResource);

		// Create a user
		Discipline discipline = RolesFactory.eINSTANCE.createDiscipline();
		discipline.setName("Me");
		discipline.setUser("Me_user");
		Command cmd = AddCommand.create(editingDomain, rm, RolesPackage.Literals.ROLE_MANAGEMENT__DISCIPLINES, discipline);
		editingDomain.getCommandStack().execute(cmd);
		
		// Create an SE for typing and place it into a concept
		StructuralElement se = StructuralFactory.eINSTANCE.createStructuralElement();
		se.setIsCanInheritFromAll(true);
		se.setIsApplicableForAll(true);
		se.setIsRootStructuralElement(true);
		
		// Create the concept and add it to the repository
		Concept concept = ConceptsFactory.eINSTANCE.createConcept();
		concept.getStructuralElements().add(se);
		
		Command addConceptCommand = AddCommand.create(editingDomain, repository, DVLMPackage.eINSTANCE.getRepository_ActiveConcepts(), concept);
		editingDomain.getCommandStack().execute(addConceptCommand);
		
		// Now create a SEI, type it with the SE, assign the discipline and a CA to it which should be inherited
		StructuralElementInstance sei1 = StructuralFactory.eINSTANCE.createStructuralElementInstance();
		sei1.setAssignedDiscipline(discipline);
		sei1.setType(se);
		sei1.setName("SomeSEI");
		rs.getStructuralElementInstanceResource(sei1);
		
		Command addSei1Command = AddCommand.create(editingDomain, repository, DVLMPackage.eINSTANCE.getRepository_RootEntities(), sei1);
		editingDomain.getCommandStack().execute(addSei1Command);
		UserRegistry.getInstance().setSuperUser(false);

		// Try to set the name which should fail for both, because the user context does not match
		int builderCountBeforeSettingNames = TestInvocationCheckBuilder.getInvocations();
		Command setSeiName = SetCommand.create(editingDomain, sei1, GeneralPackage.eINSTANCE.getIName_Name(), "SuperSEI");
		editingDomain.getVirSatCommandStack().executeNoUndo(setSeiName, UserRegistry.getInstance(), true);
		
		assertEquals("The name has not been changed", "SomeSEI", sei1.getName());
		assertEquals("The builder has not been triggered", builderCountBeforeSettingNames, TestInvocationCheckBuilder.getInvocations());
	
		// Try to change the name with the correct context but don't call the builder
		editingDomain.getVirSatCommandStack().executeNoUndo(setSeiName, new IUserContext() {
			
			@Override
			public boolean isSuperUser() {
				return false;
			}
			
			@Override
			public String getUserName() {
				return "Me_user";
			}
		}, false);
		
		assertEquals("The name has been changed", "SuperSEI", sei1.getName());
		assertEquals("The builder has not been triggered", builderCountBeforeSettingNames, TestInvocationCheckBuilder.getInvocations());
		
		// now change the name again and call the builder
		Command setSeiName2 = SetCommand.create(editingDomain, sei1, GeneralPackage.eINSTANCE.getIName_Name(), "SuperSEI2");
		editingDomain.getVirSatCommandStack().executeNoUndo(setSeiName2, new IUserContext() {
			
			@Override
			public boolean isSuperUser() {
				return false;
			}
			
			@Override
			public String getUserName() {
				return "Me_user";
			}
		}, true);
		
		assertEquals("The name has been changed", "SuperSEI2", sei1.getName());
		assertEquals("The builder has not been triggered", builderCountBeforeSettingNames + 1, TestInvocationCheckBuilder.getInvocations());
	}
}
