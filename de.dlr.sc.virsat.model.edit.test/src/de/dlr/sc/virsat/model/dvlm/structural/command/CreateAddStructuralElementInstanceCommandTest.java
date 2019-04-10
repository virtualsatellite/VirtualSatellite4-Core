/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.dvlm.structural.command;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.ArrayList;
import java.util.HashMap;

import org.eclipse.emf.common.command.BasicCommandStack;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.ReflectiveItemProviderAdapterFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import de.dlr.sc.virsat.model.dvlm.DVLMFactory;
import de.dlr.sc.virsat.model.dvlm.Repository;
import de.dlr.sc.virsat.model.dvlm.concepts.provider.ConceptsItemProviderAdapterFactory;
import de.dlr.sc.virsat.model.dvlm.roles.provider.RolesItemProviderAdapterFactory;
import de.dlr.sc.virsat.model.dvlm.units.provider.UnitsItemProviderAdapterFactory;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.provider.PropertydefinitionsItemProviderAdapterFactory;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.provider.PropertyinstancesItemProviderAdapterFactory;
import de.dlr.sc.virsat.model.dvlm.categories.provider.DVLMCategoriesItemProviderAdapterFactory;
import de.dlr.sc.virsat.model.dvlm.general.provider.GeneralItemProviderAdapterFactory;
import de.dlr.sc.virsat.model.dvlm.provider.DVLMDVLMItemProviderAdapterFactory;
import de.dlr.sc.virsat.model.dvlm.resource.provider.DVLMResourceItemProviderAdapterFactory;
import de.dlr.sc.virsat.model.dvlm.roles.UserRegistry;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElement;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralFactory;
import de.dlr.sc.virsat.model.dvlm.structural.provider.DVLMStructuralItemProviderAdapterFactory;
import de.dlr.sc.virsat.model.dvlm.structural.util.StructuralInstantiator;

/**
 * Unit tests for create add command to add a StructuralElementInstance
 * into Repository as a root element ot to another StructuralElementInstance
 * @author kova_an
 *
 */
public class CreateAddStructuralElementInstanceCommandTest {

	private EditingDomain ed;
	private UserRegistry ur;

	private StructuralElementInstance seiToAdd;
	private StructuralElementInstance seiToAdd2;
	private StructuralInstantiator si = new StructuralInstantiator();
	
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
		
		ur = UserRegistry.getInstance();
		ur.setSuperUser(true);

		StructuralElement se = StructuralFactory.eINSTANCE.createStructuralElement();
		se.setIsRootStructuralElement(true);
		se.setIsApplicableForAll(true);
		seiToAdd = si.generateInstance(se, "TEST_INSTANCE_NAME");
		seiToAdd2 = si.generateInstance(se, "TEST_INSTANCE_NAME2");
	}

	@After
	public void tearDown() throws Exception {
		ur.setSuperUser(false);
	}
	
	@Test
	public void testCreateAddInstanceIntoRepository() {
		Repository r = DVLMFactory.eINSTANCE.createRepository();
		
		Command command = CreateAddStructuralElementInstanceCommand.create(ed, r, seiToAdd);
		
		assertTrue(command.canExecute());
		command.execute();
		assertTrue("New SEI is well contained", r.getRootEntities().contains(seiToAdd));
	}

	@Test
	public void testCreateAddInstanceIntoRepositoryCollection() {
		Repository r = DVLMFactory.eINSTANCE.createRepository();
		
		ArrayList<StructuralElementInstance> seisToBeAdded = new ArrayList<>();
		seisToBeAdded.add(seiToAdd);
		seisToBeAdded.add(seiToAdd2);
		
		Command command = CreateAddStructuralElementInstanceCommand.create(ed, r, seisToBeAdded);
		
		assertTrue(command.canExecute());
		command.execute();
		assertThat("SEIs are well contained", r.getRootEntities(), hasItems(seiToAdd, seiToAdd2));
	}
	
	@Test
	public void testCreateAddInstanceIntoRepositoryUndo() {
		Repository r = DVLMFactory.eINSTANCE.createRepository();
		
		Command command = CreateAddStructuralElementInstanceCommand.create(ed, r, seiToAdd);
		
		assertTrue(command.canExecute());
		command.execute();
		assertTrue("New SEI is well contained", r.getRootEntities().contains(seiToAdd));
		
		// now execute the undo
		command.undo();
		assertFalse("SEI is not contained", r.getRootEntities().contains(seiToAdd));
	}

	@Test
	public void testCreateAddInstanceIntoParentInstanceCollection() {
		StructuralElement se = StructuralFactory.eINSTANCE.createStructuralElement();
		StructuralInstantiator si = new StructuralInstantiator();
		StructuralElementInstance parentSei = si.generateInstance(se, "TEST_INSTANCE_NAME");
	
		ArrayList<StructuralElementInstance> seisToBeAdded = new ArrayList<>();
		seisToBeAdded.add(seiToAdd);
		seisToBeAdded.add(seiToAdd2);
		
		Command command = CreateAddStructuralElementInstanceCommand.create(ed, parentSei, seisToBeAdded);
		
		assertTrue(command.canExecute());
		command.execute();
		assertThat("SEIs are well contained", parentSei.getChildren(), hasItems(seiToAdd, seiToAdd2));
	}

	@Test
	public void testCreateAddInstanceIntoParentInstance() {
		StructuralElement se = StructuralFactory.eINSTANCE.createStructuralElement();
		StructuralInstantiator si = new StructuralInstantiator();
		StructuralElementInstance parentSei = si.generateInstance(se, "TEST_INSTANCE_NAME");
		
		Command command = CreateAddStructuralElementInstanceCommand.create(ed, parentSei, seiToAdd);
		
		assertTrue(command.canExecute());
		command.execute();
		assertTrue("SEI is not contained", parentSei.getChildren().contains(seiToAdd));
	}
	
	@Test
	public void testCreateAddInstanceIntoParentInstanceUndo() {
		StructuralElement se = StructuralFactory.eINSTANCE.createStructuralElement();
		StructuralInstantiator si = new StructuralInstantiator();
		StructuralElementInstance parentSei = si.generateInstance(se, "TEST_INSTANCE_NAME");
		
		Command command = CreateAddStructuralElementInstanceCommand.create(ed, parentSei, seiToAdd);
		
		assertTrue(command.canExecute());
		command.execute();
		assertTrue("SEI is not contained", parentSei.getChildren().contains(seiToAdd));
		
		// now execute the undo
		command.undo();
		assertFalse("SEI is not contained", parentSei.getChildren().contains(seiToAdd));
	}

}
