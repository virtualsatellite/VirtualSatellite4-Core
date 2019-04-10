/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.dvlm.resource.provider;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.common.command.BasicCommandStack;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.DeleteCommand;
import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.ReflectiveItemProviderAdapterFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import de.dlr.sc.virsat.model.dvlm.concepts.provider.ConceptsItemProviderAdapterFactory;
import de.dlr.sc.virsat.model.dvlm.roles.provider.RolesItemProviderAdapterFactory;
import de.dlr.sc.virsat.model.dvlm.units.provider.UnitsItemProviderAdapterFactory;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.provider.PropertydefinitionsItemProviderAdapterFactory;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.provider.PropertyinstancesItemProviderAdapterFactory;
import de.dlr.sc.virsat.model.dvlm.categories.provider.DVLMCategoriesItemProviderAdapterFactory;
import de.dlr.sc.virsat.model.dvlm.general.provider.GeneralItemProviderAdapterFactory;
import de.dlr.sc.virsat.model.dvlm.provider.DVLMDVLMItemProviderAdapterFactory;
import de.dlr.sc.virsat.model.dvlm.roles.UserRegistry;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElement;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralFactory;
import de.dlr.sc.virsat.model.dvlm.structural.provider.DVLMStructuralItemProviderAdapterFactory;

/**
 * test class to test the resource item provider
 *
 */
public class DVLMResourceItemProviderTest {

	private EditingDomain editingDomain;
	
	@Before
	public void setup() {
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
		
		BasicCommandStack commandStack = new BasicCommandStack();

		editingDomain = new AdapterFactoryEditingDomain(adapterFactory, commandStack, new HashMap<Resource, Boolean>());
	}
	
	@After
	public void tearDown() {
		UserRegistry.getInstance().setSuperUser(false);
	}
	
	public static final String TEST_MODEL_EXTENSION = "modeltest";
	
	@Test
	public void testRemovelAndDeleteOfResourceRootObjects() {
		Resource.Factory.Registry reg = Resource.Factory.Registry.INSTANCE;
	    Map<String, Object> m = reg.getExtensionToFactoryMap();
	    m.put(TEST_MODEL_EXTENSION, new XMIResourceFactoryImpl());
	    ResourceSet resSet = editingDomain.getResourceSet();
	    Resource resource1 = resSet.createResource(URI.createURI("modeltest/Component1." + TEST_MODEL_EXTENSION));
	    Resource resource2 = resSet.createResource(URI.createURI("modeltest/Component2." + TEST_MODEL_EXTENSION));

	    StructuralElement se = StructuralFactory.eINSTANCE.createStructuralElement();
	    se.setIsApplicableForAll(true);
	    
	    StructuralElementInstance sc1 = StructuralFactory.eINSTANCE.createStructuralElementInstance();
	    StructuralElementInstance sc2 = StructuralFactory.eINSTANCE.createStructuralElementInstance();
	    sc1.setType(se);
	    sc2.setType(se);
	    
	    sc1.getChildren().add(sc2);
	    
	    resource1.getContents().add(sc1);
	    resource2.getContents().add(sc2);

	    UserRegistry.getInstance().setSuperUser(true);
	    
	    assertEquals("Make sure the StructuralElement is well contained in the resource", resource1, sc1.eResource());
	    assertEquals("Make sure the StructuralElement is well contained in the resource", resource2, sc2.eResource());
	    assertEquals("Make sure that the StructuralElements are well linked", sc2, sc1.getChildren().get(0));
	    
	    // Now call the command to delete the structural element sc2
	    Command cmd = DeleteCommand.create(editingDomain, sc2);
	    editingDomain.getCommandStack().execute(cmd);
	    
	    assertTrue("The robject is not contained anymore", sc1.getRelationInstances().isEmpty());
	    
	    // now check the undo
	    editingDomain.getCommandStack().undo();
	    
	    assertEquals("Make sure the StructuralElement is well contained in the resource", resource1, sc1.eResource());
	    assertEquals("Make sure the StructuralElement is well contained in the resource", resource2, sc2.eResource());
	    assertEquals("Make sure that the StructuralElements are well linked", sc2, sc1.getChildren().get(0));
	}
	
	@Test
	public void testAddResourceRootObjects() {
		Resource.Factory.Registry reg = Resource.Factory.Registry.INSTANCE;
	    Map<String, Object> m = reg.getExtensionToFactoryMap();
	    m.put(TEST_MODEL_EXTENSION, new XMIResourceFactoryImpl());
	    ResourceSet resSet = editingDomain.getResourceSet();
	    Resource resource1 = resSet.createResource(URI.createURI("modeltest/Component1." + TEST_MODEL_EXTENSION));
	    Resource resource2 = resSet.createResource(URI.createURI("modeltest/Component2." + TEST_MODEL_EXTENSION));

	    StructuralElement se = StructuralFactory.eINSTANCE.createStructuralElement();
	    se.setIsApplicableForAll(true);
	    
	    StructuralElementInstance sc1 = StructuralFactory.eINSTANCE.createStructuralElementInstance();
	    StructuralElementInstance sc2 = StructuralFactory.eINSTANCE.createStructuralElementInstance();
	    sc1.setType(se);
	    sc2.setType(se);
	    
	    sc1.getChildren().add(sc2);

	    Command addSc1 = AddCommand.create(editingDomain, resource1, Resource.RESOURCE__CONTENTS, sc1);
	    Command addSc2 = AddCommand.create(editingDomain, resource2, Resource.RESOURCE__CONTENTS, sc2);

	    UserRegistry.getInstance().setSuperUser(true);
	    addSc1.execute();
	    addSc2.execute();
	    
	    assertEquals("Make sure the StructuralElement is well contained in the resource", resource1, sc1.eResource());
	    assertEquals("Make sure the StructuralElement is well contained in the resource", resource2, sc2.eResource());
	    assertEquals("Make sure that the StructuralElements are well linked", sc2, sc1.getChildren().get(0));
	}
	
	@Test
	public void testAddResourceRootObjectsWithRights() {
		Resource.Factory.Registry reg = Resource.Factory.Registry.INSTANCE;
	    Map<String, Object> m = reg.getExtensionToFactoryMap();
	    m.put(TEST_MODEL_EXTENSION, new XMIResourceFactoryImpl());
	    ResourceSet resSet = editingDomain.getResourceSet();
	    Resource resource1 = resSet.createResource(URI.createURI("modeltest/Component1." + TEST_MODEL_EXTENSION));
	    Resource resource2 = resSet.createResource(URI.createURI("modeltest/Component2." + TEST_MODEL_EXTENSION));

	    StructuralElement se = StructuralFactory.eINSTANCE.createStructuralElement();
	    se.setIsApplicableForAll(true);
	    
	    StructuralElementInstance sc1 = StructuralFactory.eINSTANCE.createStructuralElementInstance();
	    StructuralElementInstance sc2 = StructuralFactory.eINSTANCE.createStructuralElementInstance();
	    sc1.setType(se);
	    sc2.setType(se);
	    
	    resource1.getContents().add(sc1);
	    resource2.getContents().add(sc2);

	    Command remSc1 = RemoveCommand.create(editingDomain, resource1, Resource.RESOURCE__CONTENTS, sc1);
	    Command remSc2 = RemoveCommand.create(editingDomain, resource2, Resource.RESOURCE__CONTENTS, sc2);

	    UserRegistry.getInstance().setSuperUser(false);
	    assertFalse("Rights as Expected", remSc1.canExecute());
	    assertFalse("Rights as Expected", remSc2.canExecute());

	    UserRegistry.getInstance().setSuperUser(true);
	    assertTrue("Rights as Expected", remSc1.canExecute());
	    assertTrue("Rights as Expected", remSc2.canExecute());
	}
}
