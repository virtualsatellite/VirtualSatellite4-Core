/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.dvlm.util;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;

import org.eclipse.emf.common.command.BasicCommandStack;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.ReflectiveItemProviderAdapterFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import de.dlr.sc.virsat.model.dvlm.DVLMFactory;
import de.dlr.sc.virsat.model.dvlm.DVLMPackage;
import de.dlr.sc.virsat.model.dvlm.Repository;
import de.dlr.sc.virsat.model.dvlm.concepts.provider.ConceptsItemProviderAdapterFactory;
import de.dlr.sc.virsat.model.dvlm.roles.provider.RolesItemProviderAdapterFactory;
import de.dlr.sc.virsat.model.dvlm.units.provider.UnitsItemProviderAdapterFactory;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.provider.PropertydefinitionsItemProviderAdapterFactory;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.provider.PropertyinstancesItemProviderAdapterFactory;
import de.dlr.sc.virsat.model.dvlm.categories.provider.DVLMCategoriesItemProviderAdapterFactory;
import de.dlr.sc.virsat.model.dvlm.general.provider.GeneralItemProviderAdapterFactory;
import de.dlr.sc.virsat.model.dvlm.inheritance.InheritancePackage;
import de.dlr.sc.virsat.model.dvlm.provider.DVLMDVLMItemProviderAdapterFactory;
import de.dlr.sc.virsat.model.dvlm.resource.provider.DVLMResourceItemProviderAdapterFactory;
import de.dlr.sc.virsat.model.dvlm.roles.UserRegistry;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElement;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralFactory;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralPackage;
import de.dlr.sc.virsat.model.dvlm.structural.provider.DVLMStructuralItemProviderAdapterFactory;
import de.dlr.sc.virsat.model.dvlm.structural.util.StructuralInstantiator;

/**
 * This test handles the ApplicableFor Rules as they are called from
 * the EMF Edit Providers. The EMF Code Generator has been adapted to call
 * these methods on Repository and StructuralElementInstance
 * @author fisc_ph
 *
 */
public class DVLMCommandParameterApplicableForCheckTest {

	private EditingDomain ed;
	private UserRegistry ur;
	
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
	}

	@After
	public void tearDown() throws Exception {
		ur.setSuperUser(false);
	}

	@Test
	public void testIsValidCommandParameterAddToRepository() {
		StructuralElement seForAccept = StructuralFactory.eINSTANCE.createStructuralElement();
		StructuralElement seForFail = StructuralFactory.eINSTANCE.createStructuralElement();
		
		seForAccept.setIsRootStructuralElement(true);
		seForFail.setIsRootStructuralElement(false);
		
		StructuralElementInstance seiForAccept = new StructuralInstantiator().generateInstance(seForAccept, "RelAccept");
		StructuralElementInstance seiForFail = new StructuralInstantiator().generateInstance(seForFail, "relFail");
	
		Repository testRepo = DVLMFactory.eINSTANCE.createRepository();

		Command failCommand = AddCommand.create(ed, testRepo, DVLMPackage.Literals.REPOSITORY__ROOT_ENTITIES, seiForFail);
		assertFalse("Cannot execute the command", failCommand.canExecute());
		
		Command acceptCommand = AddCommand.create(ed, testRepo, DVLMPackage.Literals.REPOSITORY__ROOT_ENTITIES, seiForAccept);
		assertTrue("Can execute the command", acceptCommand.canExecute());
	}

	@Test
	public void testIsValidCommandParameterAddChildrenToStructuralElementInstance() {
		StructuralElement seAccept = StructuralFactory.eINSTANCE.createStructuralElement();
		StructuralElement seFail = StructuralFactory.eINSTANCE.createStructuralElement();
		
		seAccept.getApplicableFor().add(seAccept);
		seFail.getApplicableFor().add(seFail);
		
		StructuralElementInstance seiRoot = new StructuralInstantiator().generateInstance(seAccept, "Se_Root");
		
		StructuralElement seForAccept = StructuralFactory.eINSTANCE.createStructuralElement();
		StructuralElement seForFail = StructuralFactory.eINSTANCE.createStructuralElement();
		StructuralElement seForAll = StructuralFactory.eINSTANCE.createStructuralElement();
		
		seForAll.setIsApplicableForAll(true);
		seForAccept.getApplicableFor().add(seAccept);
		seForFail.getApplicableFor().add(seFail);
		
		StructuralElementInstance seiForAccept = new StructuralInstantiator().generateInstance(seForAccept, "RelAccept");
		StructuralElementInstance seiForFail = new StructuralInstantiator().generateInstance(seForFail, "relFail");
		StructuralElementInstance seiForAll = new StructuralInstantiator().generateInstance(seForAll, "RelAcceptAll");

		// Try adding children to the root sei
		Command failCommand = AddCommand.create(ed, seiRoot, StructuralPackage.Literals.STRUCTURAL_ELEMENT_INSTANCE__CHILDREN, seiForFail);
		assertFalse("Cannot execute the command", failCommand.canExecute());
		
		Command acceptCommand = AddCommand.create(ed, seiRoot, StructuralPackage.Literals.STRUCTURAL_ELEMENT_INSTANCE__CHILDREN, seiForAccept);
		assertTrue("Can execute the command", acceptCommand.canExecute());

		Command allCommand = AddCommand.create(ed, seiRoot, StructuralPackage.Literals.STRUCTURAL_ELEMENT_INSTANCE__CHILDREN, seiForAll);
		assertTrue("Can execute the command", allCommand.canExecute());
	}
	
	@Test
	public void testIsValidCommandParameterAddSuperSeisToStructuralElementInstance() {
		StructuralElement seAccept = StructuralFactory.eINSTANCE.createStructuralElement();
		StructuralElement seFail = StructuralFactory.eINSTANCE.createStructuralElement();
		
		seAccept.getCanInheritFrom().add(seAccept);
		seFail.getCanInheritFrom().add(seFail);
		
		StructuralElementInstance seiSuper = new StructuralInstantiator().generateInstance(seAccept, "Se_Super");
		
		StructuralElement seForAccept = StructuralFactory.eINSTANCE.createStructuralElement();
		StructuralElement seForFail = StructuralFactory.eINSTANCE.createStructuralElement();
		StructuralElement seForAll = StructuralFactory.eINSTANCE.createStructuralElement();
		
		seForAll.setIsCanInheritFromAll(true);
		seForAccept.getCanInheritFrom().add(seAccept);
		seForFail.getCanInheritFrom().add(seFail);
		
		StructuralElementInstance seiForAccept = new StructuralInstantiator().generateInstance(seForAccept, "RelAccept");
		StructuralElementInstance seiForFail = new StructuralInstantiator().generateInstance(seForFail, "relFail");
		StructuralElementInstance seiForAll = new StructuralInstantiator().generateInstance(seForAll, "RelAcceptAll");

		// Try inheriting from the super sei
		Command failCommand = AddCommand.create(ed, seiForFail, InheritancePackage.Literals.IINHERITS_FROM__SUPER_SEIS, seiSuper);
		assertFalse("Cannot execute the command", failCommand.canExecute());
		
		Command acceptCommand = AddCommand.create(ed, seiForAccept, InheritancePackage.Literals.IINHERITS_FROM__SUPER_SEIS, seiSuper);
		assertTrue("Can execute the command", acceptCommand.canExecute());

		Command allCommand = AddCommand.create(ed, seiForAll, InheritancePackage.Literals.IINHERITS_FROM__SUPER_SEIS, seiSuper);
		assertTrue("Can execute the command", allCommand.canExecute());
	}
}
