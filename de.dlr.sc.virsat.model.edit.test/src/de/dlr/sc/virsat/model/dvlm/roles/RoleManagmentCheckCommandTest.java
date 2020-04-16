/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.dvlm.roles;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;

import org.eclipse.emf.common.command.BasicCommandStack;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.command.CommandParameter;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.ReflectiveItemProviderAdapterFactory;
import org.eclipse.emf.edit.provider.resource.ResourceItemProviderAdapterFactory;
import org.junit.Before;
import org.junit.Test;

import de.dlr.sc.virsat.model.dvlm.qudv.QudvFactory;
import de.dlr.sc.virsat.model.dvlm.qudv.SystemOfUnits;
import de.dlr.sc.virsat.model.dvlm.roles.provider.RolesItemProviderAdapterFactory;
import de.dlr.sc.virsat.model.dvlm.units.UnitManagement;
import de.dlr.sc.virsat.model.dvlm.units.UnitsFactory;
import de.dlr.sc.virsat.model.dvlm.units.provider.UnitsItemProviderAdapterFactory;
import de.dlr.sc.virsat.model.dvlm.categories.CategoriesFactory;
import de.dlr.sc.virsat.model.dvlm.categories.Category;
import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.PropertyinstancesFactory;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.PropertyinstancesPackage;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ValuePropertyInstance;
import de.dlr.sc.virsat.model.dvlm.general.GeneralPackage;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElement;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralFactory;
import de.dlr.sc.virsat.model.dvlm.structural.provider.DVLMStructuralItemProviderAdapterFactory;

/**
 * test class to test our roles, permissions and disciplineAssignment 
 *
 */
public class RoleManagmentCheckCommandTest {

	EditingDomain ourEditingDomain;
	StructuralElementInstance sei;
	RoleManagement rm; 
	UnitManagement um;
	Discipline discipline1;
	Discipline discipline2;
	CategoryAssignment ca;
	ValuePropertyInstance vpi;
	
	@Before
	public void setUp() throws Exception {
		// Setting up a EditingDomain for our Test and a Basic Command Stack which is used to execute the commands on the model.
		//and thus invokes the RoleManagementChecker!
				
		ComposedAdapterFactory adapterFactory = new ComposedAdapterFactory(ComposedAdapterFactory.Descriptor.Registry.INSTANCE);

		adapterFactory.addAdapterFactory(new ResourceItemProviderAdapterFactory());
		adapterFactory.addAdapterFactory(new DVLMStructuralItemProviderAdapterFactory());
		adapterFactory.addAdapterFactory(new RolesItemProviderAdapterFactory());
		adapterFactory.addAdapterFactory(new UnitsItemProviderAdapterFactory());
		adapterFactory.addAdapterFactory(new ReflectiveItemProviderAdapterFactory());

		BasicCommandStack fulkaCommandStack = new BasicCommandStack();
		ourEditingDomain = new AdapterFactoryEditingDomain(adapterFactory, fulkaCommandStack, new HashMap<Resource, Boolean>());

		// also create a standard environment for the tests, a basic little example model
		// with the necessary elements create some dummy component
		Category c = CategoriesFactory.eINSTANCE.createCategory();
		c.setIsApplicableForAll(true);
		StructuralElement se = StructuralFactory.eINSTANCE.createStructuralElement();
		sei = StructuralFactory.eINSTANCE.createStructuralElementInstance();
		ca = CategoriesFactory.eINSTANCE.createCategoryAssignment();
		sei.setType(se);
		ca.setType(c);
		ca.setName("ca1");
		sei.getCategoryAssignments().add(ca);

		vpi = PropertyinstancesFactory.eINSTANCE.createValuePropertyInstance();
		vpi.setValue("vpi1");
		ca.getPropertyInstances().add(vpi);

		//create the roleManagement
		rm = RolesFactory.eINSTANCE.createRoleManagement();
		discipline1 = RolesFactory.eINSTANCE.createDiscipline();
		discipline1.setName("System");
		
		discipline2 = RolesFactory.eINSTANCE.createDiscipline();
		discipline1.setName("AOCS");

		discipline1.setUser("UserOne");
		discipline2.setUser("user2");
		
		rm.getDisciplines().add(discipline1);
		sei.setAssignedDiscipline(discipline1);
		
		//create the fundamentals needed for the unitManagment
		um = UnitsFactory.eINSTANCE.createUnitManagement();
	}
	
	@Test
	public void testRoleManagementCheckCommandNoUserContext() {
		UserRegistry.getInstance().setSuperUser(false);
		UserRegistry.getInstance().setUser("UserOne", TWO_THRITY_THREE);
		
		Command setCommand = new SetCommand(ourEditingDomain, sei, GeneralPackage.eINSTANCE.getIName_Name(), "newName");
		RoleManagementCheckCommand rmcCommand = new RoleManagementCheckCommand(setCommand, new CommandParameter(sei), null);
		
		// A null user context will make the RightsHelper fail in the command. thus it cannot be executed.
		assertFalse("Command is not executable", rmcCommand.canExecute());
		assertFalse("Command is also not undoable", rmcCommand.canUndo());
	}
	
	/**
	 * Helper class to have a simple way of changing a user context within a test case
	 */
	private static class TestUserContext implements IUserContext {

		TestUserContext(String userName, boolean su) {
			this.userName = userName;
			this.su = su;
		}
		
		String userName;
		boolean su;
		
		@Override
		public boolean isSuperUser() {
			return su;
		}

		@Override
		public String getUserName() {
			return userName;
		}
	}
	
	@Test
	public void testRoleManagementCheckCommandWithUserContext() {
		TestUserContext userContext = new TestUserContext("UserBad", false);
		Command setCommand = new SetCommand(ourEditingDomain, sei, GeneralPackage.eINSTANCE.getIName_Name(), "newName");
		RoleManagementCheckCommand rmcCommand = new RoleManagementCheckCommand(setCommand, new CommandParameter(sei), userContext);
		
		// Check who can execute the command when changing the context
		assertFalse("UserBad cannot execute the command", rmcCommand.canExecute());
		userContext.userName = "UserOne";
		assertTrue("UserOne can execute the command", rmcCommand.canExecute());
	
		rmcCommand.execute();
		
		// Check who could undo the command when changing the context.
		assertTrue("UserOne would be able to undo the command", rmcCommand.canUndo());
		userContext.userName = "UserBad";
		assertFalse("UserBad cannot undo the command", rmcCommand.canUndo());
	}
	
	@Test
	public void testRoleManagementCheckCommandWithSuperUserContext() {
		TestUserContext userContext = new TestUserContext("UserBad", false);
		Command setCommand = new SetCommand(ourEditingDomain, sei, GeneralPackage.eINSTANCE.getIName_Name(), "newName");
		RoleManagementCheckCommand rmcCommand = new RoleManagementCheckCommand(setCommand,  new CommandParameter(sei), userContext);
		
		// Check who can execute the command when changing the context
		assertFalse("UserBad cannot execute the command", rmcCommand.canExecute());
		userContext.su = true;
		assertTrue("UserBad as SU can execute the command", rmcCommand.canExecute());
	
		rmcCommand.execute();
		
		// Check who could undo the command when changing the context.
		assertTrue("UserBad as SU would be able to undo the command", rmcCommand.canUndo());
		userContext.su = false;
		assertFalse("UserBad cannot undo the command", rmcCommand.canUndo());
	}
	
	@Test
	public void testCanExecuteCommandAsSuperUser() {
		UserRegistry.getInstance().setSuperUser(true);
		UserRegistry.getInstance().setUser("user-with-no-priviledges", TWO_THRITY_THREE);
		
		// we make some changes to the systemComponent
		// the changes should go through the EditingDomain and Commands
		SetCommand.create(ourEditingDomain, sei, GeneralPackage.eINSTANCE.getIName_Name(), "newName").execute();
		
		// if the Role Management permits the change, check true
		assertEquals("Checking superUser changed name to newName", "newName", sei.getName());
	}

	@Test(expected = UserHasNoRightsException.class)
	public void testCannotExecuteCommandAsNonSuperUser() {
		UserRegistry.getInstance().setSuperUser(false);
		UserRegistry.getInstance().setUser("user-with-no-priviledges", TWO_THRITY_THREE);

		Command setNameCommand = SetCommand.create(ourEditingDomain, sei, GeneralPackage.eINSTANCE.getIName_Name(), "newName");
		assertFalse("Non Super User should not have permissions to execute this command", setNameCommand.canExecute());
		setNameCommand.execute();
	}	
	
	@Test(expected = UserHasNoRightsException.class)
	public void testRoleManagementDiscipline() {
		
		UserRegistry.getInstance().setSuperUser(false);
		UserRegistry.getInstance().setUser("UserOne", TWO_THRITY_THREE);
		
		// we make some changes to the systemComponent through a proper command
		SetCommand.create(ourEditingDomain, sei, GeneralPackage.eINSTANCE.getIName_Name(), "newName").execute();		
		
		// if the Role Management permits the change, check for the newName
		assertEquals("Checking assignedUSer changed name to newName", "newName", sei.getName());
		
		// now change the user and do a rename again
		UserRegistry.getInstance().setUser("user_with_no_privileges", TWO_FIFTY_FIVE);
				
		Command setNameCommand = SetCommand.create(ourEditingDomain, sei, GeneralPackage.eINSTANCE.getIName_Name(), "newName2");
		assertFalse("User should not have permissions to execute this command", setNameCommand.canExecute());
		setNameCommand.execute();
	}
	
	static final int TWO_THRITY_THREE = 233;
	static final int TWO_FIFTY_FIVE = 255;
	
	@Test(expected = UserHasNoRightsException.class)
	public void testRoleManagmentOverTwoHierarchies() {
		
		UserRegistry.getInstance().setSuperUser(false);
		UserRegistry.getInstance().setUser("UserOne", TWO_THRITY_THREE);
		
		SetCommand.create(ourEditingDomain, ca, GeneralPackage.eINSTANCE.getIName_Name(), "ca_newName").execute();
		assertEquals("Checking user changed name of category assignment to ca_newName", "ca_newName", ca.getName());
		
		// now change the user and do a rename again
		UserRegistry.getInstance().setUser("user_with_no_privileges", TWO_FIFTY_FIVE);
		Command setNameCommand = SetCommand.create(ourEditingDomain, ca, GeneralPackage.eINSTANCE.getIName_Name(), "ca_newName2");

		assertFalse("User should not have permissions to execute this command", setNameCommand.canExecute());
		setNameCommand.execute();
	}
	
	@Test
	public void testRoleManagmentOverTwoHierarchiesWithSuperUser() {
		
		UserRegistry.getInstance().setSuperUser(true);
		UserRegistry.getInstance().setUser("user_with_no_privileges", TWO_FIFTY_FIVE);

		SetCommand.create(ourEditingDomain, ca, GeneralPackage.eINSTANCE.getIName_Name(), "ca_newName").execute();
		assertEquals("Checking user changed name of category assignment to ca_newName", "ca_newName", ca.getName());
		
		// now change the user and do a rename again
		SetCommand.create(ourEditingDomain, ca, GeneralPackage.eINSTANCE.getIName_Name(), "ca_newName2").execute();
	
		assertEquals("Checking user cannot change name of category assignment to ca_newName2", "ca_newName2", ca.getName());
	}
	
	@Test(expected = UserHasNoRightsException.class)
	public void testRoleManagmentOverThreeHierarchies() {
		
		UserRegistry.getInstance().setSuperUser(false);
		UserRegistry.getInstance().setUser("UserOne", TWO_THRITY_THREE);

		SetCommand.create(ourEditingDomain, vpi, PropertyinstancesPackage.eINSTANCE.getValuePropertyInstance_Value(), "vpi_newValue").execute();
		assertEquals("Checking user changed name of value property instance to vpi_newValue", "vpi_newValue", vpi.getValue());

		UserRegistry.getInstance().setUser("user_with_no_privileges", TWO_FIFTY_FIVE);
		// now change the user and do a rename again
		Command setValueCommand = SetCommand.create(ourEditingDomain, vpi, PropertyinstancesPackage.eINSTANCE.getValuePropertyInstance_Value(), "vpi_newValue2");
		assertFalse("User should not have permissions to execute this command", setValueCommand.canExecute());
		setValueCommand.execute();
	}
	
	@Test
	public void testRoleManagmentOverThreeHierarchiesWithSuperUser() {
		
		UserRegistry.getInstance().setSuperUser(true);
		UserRegistry.getInstance().setUser("user_with_no_privileges", TWO_FIFTY_FIVE);

		SetCommand.create(ourEditingDomain, vpi, PropertyinstancesPackage.eINSTANCE.getValuePropertyInstance_Value(), "vpi_newValue").execute();
		assertEquals("Checking user changed name of value property instance to vpi_newValue", "vpi_newValue", vpi.getValue());

		// now change the user and do a rename again
		SetCommand.create(ourEditingDomain, vpi, PropertyinstancesPackage.eINSTANCE.getValuePropertyInstance_Value(), "vpi_newValue2").execute();
		assertEquals("Checking user cannot change name of value property instance to vpi_newValue2", "vpi_newValue2", vpi.getValue());
	}
	
	@Test(expected = UserHasNoRightsException.class)
	public void testRoleManagementwithDifferentDisciplines() {
		
		UserRegistry.getInstance().setSuperUser(false);
		UserRegistry.getInstance().setUser("UserOne", TWO_FIFTY_FIVE);
		
		//set new name
		SetCommand.create(ourEditingDomain, sei, GeneralPackage.eINSTANCE.getIName_Name(), "newName").execute();		
		
		// if the Role Management permits the change, check true
		assertEquals("Checking assigned discipline can change name to newName", "newName", sei.getName());

		//now assign different discipline to our systemComponent and check if name change is false
		sei.setAssignedDiscipline(discipline2);

		Command setNameCommand = SetCommand.create(ourEditingDomain, sei, GeneralPackage.eINSTANCE.getIName_Name(), "newName2");
		
		assertFalse("User should not have permissions to execute this command", setNameCommand.canExecute());
		setNameCommand.execute();
	}

	@Test(expected = UserHasNoRightsException.class)
	public void testUserCannotModifyObjectAfterChangingDiscipline() {
		UserRegistry.getInstance().setSuperUser(false);
		UserRegistry.getInstance().setUser("UserOne", TWO_FIFTY_FIVE);
		
		//set new name
		SetCommand.create(ourEditingDomain, sei, GeneralPackage.eINSTANCE.getIName_Name(), "newName").execute();
		SetCommand.create(ourEditingDomain, sei, GeneralPackage.eINSTANCE.getIAssignedDiscipline_AssignedDiscipline(), discipline2).execute();		
		Command setNameCommand = SetCommand.create(ourEditingDomain, sei, GeneralPackage.eINSTANCE.getIName_Name(), "newName2");
		
		assertFalse("User should not have permissions to execute this command", setNameCommand.canExecute());
		setNameCommand.execute();
	}

	@Test(expected = UserHasNoRightsException.class)
	public void testUserCannotSetDisciplineBackAfterChangingDiscipline() {
		UserRegistry.getInstance().setSuperUser(false);
		UserRegistry.getInstance().setUser("UserOne", TWO_FIFTY_FIVE);
		
		//set new name
		SetCommand.create(ourEditingDomain, sei, GeneralPackage.eINSTANCE.getIName_Name(), "newName").execute();
		SetCommand.create(ourEditingDomain, sei, GeneralPackage.eINSTANCE.getIAssignedDiscipline_AssignedDiscipline(), discipline2).execute();		
		Command setDisciplineCommand = SetCommand.create(ourEditingDomain, sei, GeneralPackage.eINSTANCE.getIAssignedDiscipline_AssignedDiscipline(), discipline1);

		assertFalse("User should not have permissions to execute this command", setDisciplineCommand.canExecute());
		setDisciplineCommand.execute();
	}	
	
	@Test
	public void testRoleManagementGiveAwayDisciplineAssignmentSuperUser() {
		
		UserRegistry.getInstance().setSuperUser(true);
		UserRegistry.getInstance().setUser("user-with-no-priviledges", TWO_FIFTY_FIVE);
		
		//set new name
		SetCommand.create(ourEditingDomain, sei, GeneralPackage.eINSTANCE.getIName_Name(), "newName").execute();
		SetCommand.create(ourEditingDomain, sei, GeneralPackage.eINSTANCE.getIAssignedDiscipline_AssignedDiscipline(), discipline2).execute();		
		SetCommand.create(ourEditingDomain, sei, GeneralPackage.eINSTANCE.getIName_Name(), "newName2").execute();
		
		// if the Role Management permits the change, check false
		assertEquals("Checking discipline not allowed to change name", "newName2", sei.getName());
		
		//now do a command that wants to assign back discipline
		SetCommand.create(ourEditingDomain, sei, GeneralPackage.eINSTANCE.getIAssignedDiscipline_AssignedDiscipline(), discipline1).execute();
		SetCommand.create(ourEditingDomain, sei, GeneralPackage.eINSTANCE.getIName_Name(), "newName").execute();
		
		// if the Role Management permits the change, check true
		assertEquals("Checking assigned discipline can change name to newName", "newName", sei.getName());

		//now do some more checks with super user rights
	}
	
	@Test
	public void testUnitManagementSuperUser() {
		
		UserRegistry.getInstance().setSuperUser(false);
		
		SystemOfUnits sou = QudvFactory.eINSTANCE.createSystemOfUnits();
		sou.setName("name1");
		
		//directly setting it through EMF should be possible!
		um.setSystemOfUnit(sou);
		
		//changing the name of the SystemOfUnits through a proper set command should be blocked by the RoleManagement
		Command setNameCommand = SetCommand.create(ourEditingDomain, sou, GeneralPackage.eINSTANCE.getIName_Name(), "newName2");
		assertFalse("User should not have permissions to execute this command", setNameCommand.canExecute());
		
		UserRegistry.getInstance().setSuperUser(true);
		SetCommand.create(ourEditingDomain, sou, GeneralPackage.eINSTANCE.getIName_Name(), "newName3").execute();

		assertEquals("Checking assigned discipline can change name to newName3", "newName3", sou.getName());
	}
}
