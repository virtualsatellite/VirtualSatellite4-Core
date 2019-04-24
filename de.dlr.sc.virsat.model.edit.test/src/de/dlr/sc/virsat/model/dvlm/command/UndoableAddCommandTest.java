/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.dvlm.command;


import java.util.HashMap;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;

import org.eclipse.emf.common.command.BasicCommandStack;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.ReflectiveItemProviderAdapterFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.provider.PropertydefinitionsItemProviderAdapterFactory;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.provider.PropertyinstancesItemProviderAdapterFactory;
import de.dlr.sc.virsat.model.dvlm.categories.provider.DVLMCategoriesItemProviderAdapterFactory;
import de.dlr.sc.virsat.model.dvlm.concepts.provider.ConceptsItemProviderAdapterFactory;
import de.dlr.sc.virsat.model.dvlm.general.provider.GeneralItemProviderAdapterFactory;
import de.dlr.sc.virsat.model.dvlm.provider.DVLMDVLMItemProviderAdapterFactory;
import de.dlr.sc.virsat.model.dvlm.resource.provider.DVLMResourceItemProviderAdapterFactory;
import de.dlr.sc.virsat.model.dvlm.roles.Discipline;
import de.dlr.sc.virsat.model.dvlm.roles.RoleManagement;
import de.dlr.sc.virsat.model.dvlm.roles.RolesFactory;
import de.dlr.sc.virsat.model.dvlm.roles.RolesPackage;
import de.dlr.sc.virsat.model.dvlm.roles.UserRegistry;
import de.dlr.sc.virsat.model.dvlm.roles.provider.RolesItemProviderAdapterFactory;
import de.dlr.sc.virsat.model.dvlm.structural.provider.DVLMStructuralItemProviderAdapterFactory;
import de.dlr.sc.virsat.model.dvlm.units.provider.UnitsItemProviderAdapterFactory;

/**
 * Test cases for the modified EMF ADD command
 * @author fisc_ph
 *
 */
public class UndoableAddCommandTest {

	private EditingDomain ed;
	
	@Before
	public void setUp() throws Exception {
		ComposedAdapterFactory adapterFactory = new ComposedAdapterFactory(ComposedAdapterFactory.Descriptor.Registry.INSTANCE);

		adapterFactory.addAdapterFactory(new DVLMResourceItemProviderAdapterFactory());
		adapterFactory.addAdapterFactory(new DVLMDVLMItemProviderAdapterFactory());
		adapterFactory.addAdapterFactory(new DVLMStructuralItemProviderAdapterFactory());
		adapterFactory.addAdapterFactory(new GeneralItemProviderAdapterFactory());
		adapterFactory.addAdapterFactory(new ConceptsItemProviderAdapterFactory());
		adapterFactory.addAdapterFactory(new RolesItemProviderAdapterFactory());
		adapterFactory.addAdapterFactory(new UnitsItemProviderAdapterFactory());
		adapterFactory.addAdapterFactory(new DVLMCategoriesItemProviderAdapterFactory());
		adapterFactory.addAdapterFactory(new PropertydefinitionsItemProviderAdapterFactory());
		adapterFactory.addAdapterFactory(new PropertyinstancesItemProviderAdapterFactory());
		adapterFactory.addAdapterFactory(new ReflectiveItemProviderAdapterFactory());

		BasicCommandStack fulkaCommandStack = new BasicCommandStack();
		ed = new AdapterFactoryEditingDomain(adapterFactory, fulkaCommandStack, new HashMap<Resource, Boolean>());

		UserRegistry.getInstance().setSuperUser(true);
	}

	@After
	public void tearDown() throws Exception {
		UserRegistry.getInstance().setSuperUser(false);
	}

	@Test
	public void testDoUndo() {
		Discipline disc1 = RolesFactory.eINSTANCE.createDiscipline();
		Discipline disc2 = RolesFactory.eINSTANCE.createDiscipline();
		Discipline disc3 = RolesFactory.eINSTANCE.createDiscipline();
		
		RoleManagement rm = RolesFactory.eINSTANCE.createRoleManagement();
	
		ed.getCommandStack().execute(AddCommand.create(ed, rm, RolesPackage.Literals.ROLE_MANAGEMENT__DISCIPLINES, disc1));
		ed.getCommandStack().execute(AddCommand.create(ed, rm, RolesPackage.Literals.ROLE_MANAGEMENT__DISCIPLINES, disc2));
		rm.getDisciplines().add(disc3);
		
		//CHECKSTYLE:OFF
		assertEquals("There are exactly 3 discplines stored", 3, rm.getDisciplines().size());
		assertThat("all disciplines are present", rm.getDisciplines(), hasItems(disc1, disc2, disc3));
		//CHECKSTYLE:ON

		// Now undo the command stack and the second discipline should dissapear
		ed.getCommandStack().undo();
		
		//CHECKSTYLE:OFF
		assertEquals("There are exactly 2 discplines stored", 2, rm.getDisciplines().size());
		assertThat("all disciplines are present", rm.getDisciplines(), hasItems(disc1, disc3));
		//CHECKSTYLE:ON

		// Now undo the command stack and the second discipline should dissapear
		ed.getCommandStack().undo();
		
		//CHECKSTYLE:OFF
		assertEquals("There are exactly 2 discplines stored", 1, rm.getDisciplines().size());
		assertThat("all disciplines are present", rm.getDisciplines(), hasItems(disc3));
		//CHECKSTYLE:ON

		// redoing should bring back the objects
		ed.getCommandStack().redo();
		ed.getCommandStack().redo();

		//CHECKSTYLE:OFF
		assertEquals("There are exactly 3 discplines stored", 3, rm.getDisciplines().size());
		assertThat("all disciplines are present", rm.getDisciplines(), hasItems(disc1, disc2, disc3));
		//CHECKSTYLE:ON
	}
}