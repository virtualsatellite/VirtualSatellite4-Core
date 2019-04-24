/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.provider;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;

import org.eclipse.emf.common.command.BasicCommandStack;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.resource.Resource;
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

import de.dlr.sc.virsat.model.dvlm.categories.ATypeDefinition;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.DynamicArrayModifier;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.PropertydefinitionsFactory;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.StaticArrayModifier;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.StringProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.provider.PropertydefinitionsItemProviderAdapterFactory;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ArrayInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ComposedPropertyInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.PropertyinstancesFactory;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.PropertyinstancesPackage;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ValuePropertyInstance;
import de.dlr.sc.virsat.model.dvlm.categories.provider.DVLMCategoriesItemProviderAdapterFactory;
import de.dlr.sc.virsat.model.dvlm.concepts.provider.ConceptsItemProviderAdapterFactory;
import de.dlr.sc.virsat.model.dvlm.general.provider.GeneralItemProviderAdapterFactory;
import de.dlr.sc.virsat.model.dvlm.provider.DVLMDVLMItemProviderAdapterFactory;
import de.dlr.sc.virsat.model.dvlm.resource.provider.DVLMResourceItemProviderAdapterFactory;
import de.dlr.sc.virsat.model.dvlm.roles.UserRegistry;
import de.dlr.sc.virsat.model.dvlm.roles.provider.RolesItemProviderAdapterFactory;
import de.dlr.sc.virsat.model.dvlm.structural.provider.DVLMStructuralItemProviderAdapterFactory;
import de.dlr.sc.virsat.model.dvlm.units.provider.UnitsItemProviderAdapterFactory;

/**
 * This class tests the extra generated functionality of creating commands for the 
 * Array Instances. Some extra functionality is that removing or adding a new item into
 * a static array should create an UnexecutableCommand
 * @author fisc_ph
 *
 */
public class ArrayInstanceItemProviderTest {

	private EditingDomain editingDomain;
	private ComposedAdapterFactory adapterFactory;
	
	@Before
	public void setup() {
		adapterFactory = new ComposedAdapterFactory(ComposedAdapterFactory.Descriptor.Registry.INSTANCE);

		adapterFactory.addAdapterFactory(new DVLMResourceItemProviderAdapterFactory());
		adapterFactory.addAdapterFactory(new DVLMDVLMItemProviderAdapterFactory());
		adapterFactory.addAdapterFactory(new DVLMStructuralItemProviderAdapterFactory());
		adapterFactory.addAdapterFactory(new GeneralItemProviderAdapterFactory());
		adapterFactory.addAdapterFactory(new ConceptsItemProviderAdapterFactory());
		adapterFactory.addAdapterFactory(new RolesItemProviderAdapterFactory());
		adapterFactory.addAdapterFactory(new UnitsItemProviderAdapterFactory());
		adapterFactory.addAdapterFactory(new DVLMCategoriesItemProviderAdapterFactory());
		adapterFactory.addAdapterFactory(new PropertydefinitionsItemProviderAdapterFactory());
		adapterFactory.addAdapterFactory(new DVLMPropertyinstancesItemProviderAdapterFactory());
		adapterFactory.addAdapterFactory(new ReflectiveItemProviderAdapterFactory());
		
		BasicCommandStack commandStack = new BasicCommandStack();

		editingDomain = new AdapterFactoryEditingDomain(adapterFactory, commandStack, new HashMap<Resource, Boolean>());
		
		UserRegistry.getInstance().setSuperUser(true);
	}
	
	@After
	public void tearDown() {
		UserRegistry.getInstance().setSuperUser(false);
	}
	
	@Test
	public void testCreateCommandForArrayInstanceDynamic() {
		Command command;

		StringProperty property = PropertydefinitionsFactory.eINSTANCE.createStringProperty();
		DynamicArrayModifier arrayModifier = PropertydefinitionsFactory.eINSTANCE.createDynamicArrayModifier();
		property.setArrayModifier(arrayModifier);
		ArrayInstance arrayInstance = PropertyinstancesFactory.eINSTANCE.createArrayInstance();
		arrayInstance.setType(property);
		ComposedPropertyInstance cpiAdd = PropertyinstancesFactory.eINSTANCE.createComposedPropertyInstance();
		ComposedPropertyInstance cpiRemove = PropertyinstancesFactory.eINSTANCE.createComposedPropertyInstance();
		arrayInstance.getArrayInstances().add(cpiRemove);
		
		command = AddCommand.create(editingDomain, arrayInstance, PropertyinstancesPackage.Literals.ARRAY_INSTANCE__ARRAY_INSTANCES, cpiAdd);
		assertTrue("We can Execute the Command", command.canExecute());

		command = RemoveCommand.create(editingDomain, arrayInstance, PropertyinstancesPackage.Literals.ARRAY_INSTANCE__ARRAY_INSTANCES, cpiRemove);
		assertTrue("We can Execute the Command", command.canExecute());

		command = DeleteCommand.create(editingDomain, cpiRemove);
		assertTrue("We can Execute the Command", command.canExecute());
	}
	
	@Test
	public void testCreateCommandForArrayInstanceStatic() {
		Command command;

		StringProperty property = PropertydefinitionsFactory.eINSTANCE.createStringProperty();
		StaticArrayModifier arrayModifier = PropertydefinitionsFactory.eINSTANCE.createStaticArrayModifier();
		arrayModifier.setArraySize(2);
		property.setArrayModifier(arrayModifier);
		ArrayInstance arrayInstance = PropertyinstancesFactory.eINSTANCE.createArrayInstance();
		arrayInstance.setType(property);
		ComposedPropertyInstance cpi = PropertyinstancesFactory.eINSTANCE.createComposedPropertyInstance();
		ComposedPropertyInstance cpiAdd = PropertyinstancesFactory.eINSTANCE.createComposedPropertyInstance();
		ComposedPropertyInstance cpiRemove = PropertyinstancesFactory.eINSTANCE.createComposedPropertyInstance();
		arrayInstance.getArrayInstances().add(cpiRemove);
		arrayInstance.getArrayInstances().add(cpi);
		
		command = AddCommand.create(editingDomain, arrayInstance, PropertyinstancesPackage.Literals.ARRAY_INSTANCE__ARRAY_INSTANCES, cpiAdd);
		assertFalse("We can Execute the Command", command.canExecute());

		command = RemoveCommand.create(editingDomain, arrayInstance, PropertyinstancesPackage.Literals.ARRAY_INSTANCE__ARRAY_INSTANCES, cpiRemove);
		assertFalse("We can Execute the Command", command.canExecute());

		command = DeleteCommand.create(editingDomain, cpiRemove);
		assertFalse("We can Execute the Command", command.canExecute());
	}
	
	@Test
	public void testGetText() {
		DVLMArrayInstanceItemProvider dvlmAiip = new DVLMArrayInstanceItemProvider(adapterFactory);
		
		ATypeDefinition type = PropertydefinitionsFactory.eINSTANCE.createFloatProperty();
		type.setName("A");
		ArrayInstance ai = PropertyinstancesFactory.eINSTANCE.createArrayInstance();
		ai.setType(type);
		ValuePropertyInstance vpi = PropertyinstancesFactory.eINSTANCE.createValuePropertyInstance();
		vpi.setType(type);
		vpi.setValue("5");
		ai.getArrayInstances().add(vpi);
		
		final String EXPECTED = "A: {A: 5}";
		String actual = dvlmAiip.getText(ai);
		assertEquals("The output is like expected", EXPECTED, actual);
	}

}
