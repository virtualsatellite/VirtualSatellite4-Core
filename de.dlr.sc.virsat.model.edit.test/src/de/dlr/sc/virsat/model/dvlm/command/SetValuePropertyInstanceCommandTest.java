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


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

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

import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.FloatProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.IntProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.PropertydefinitionsFactory;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.StringProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.provider.PropertydefinitionsItemProviderAdapterFactory;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.PropertyinstancesFactory;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.PropertyinstancesPackage;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.UnitValuePropertyInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ValuePropertyInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.provider.PropertyinstancesItemProviderAdapterFactory;
import de.dlr.sc.virsat.model.dvlm.categories.provider.DVLMCategoriesItemProviderAdapterFactory;
import de.dlr.sc.virsat.model.dvlm.concepts.provider.ConceptsItemProviderAdapterFactory;
import de.dlr.sc.virsat.model.dvlm.general.provider.GeneralItemProviderAdapterFactory;
import de.dlr.sc.virsat.model.dvlm.provider.DVLMDVLMItemProviderAdapterFactory;
import de.dlr.sc.virsat.model.dvlm.qudv.AUnit;
import de.dlr.sc.virsat.model.dvlm.qudv.QudvFactory;
import de.dlr.sc.virsat.model.dvlm.resource.provider.DVLMResourceItemProviderAdapterFactory;
import de.dlr.sc.virsat.model.dvlm.roles.UserRegistry;
import de.dlr.sc.virsat.model.dvlm.roles.provider.RolesItemProviderAdapterFactory;
import de.dlr.sc.virsat.model.dvlm.structural.provider.DVLMStructuralItemProviderAdapterFactory;
import de.dlr.sc.virsat.model.dvlm.units.provider.UnitsItemProviderAdapterFactory;

/**
 * test cases for the command setting a value to a UVPI or VPI.
 * @author fisc_ph
 *
 */
public class SetValuePropertyInstanceCommandTest {

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
	public void testSetInteger() {
		IntProperty intProperty = PropertydefinitionsFactory.eINSTANCE.createIntProperty();
		UnitValuePropertyInstance uv = PropertyinstancesFactory.eINSTANCE.createUnitValuePropertyInstance();
		
		uv.setType(intProperty);
	
		Command commandCorrect = new SetValuePropertyInstanceCommand(ed, uv, PropertyinstancesPackage.Literals.VALUE_PROPERTY_INSTANCE__VALUE, "5");
		Command commandInCorrectFloat = new SetValuePropertyInstanceCommand(ed, uv, PropertyinstancesPackage.Literals.VALUE_PROPERTY_INSTANCE__VALUE, "0.5");
		Command commandInCorrectString = new SetValuePropertyInstanceCommand(ed, uv, PropertyinstancesPackage.Literals.VALUE_PROPERTY_INSTANCE__VALUE, "TEST");
		
		assertTrue("Command with correct value format", commandCorrect.canExecute());
		assertFalse("Command with incorrect value format", commandInCorrectFloat.canExecute());
		assertFalse("Command with incorrect value format", commandInCorrectString.canExecute());
		
		commandCorrect.execute();
		assertEquals("Value jhas been set as exepcted", "5", uv.getValue());
	}
	
	@Test
	public void testSetEmpty() {
		IntProperty intProperty = PropertydefinitionsFactory.eINSTANCE.createIntProperty();
		UnitValuePropertyInstance uv = PropertyinstancesFactory.eINSTANCE.createUnitValuePropertyInstance();
		
		uv.setType(intProperty);
	
		Command commandCorrect = new SetValuePropertyInstanceCommand(ed, uv, PropertyinstancesPackage.Literals.VALUE_PROPERTY_INSTANCE__VALUE, "");
		
		assertTrue("Command with correct value format", commandCorrect.canExecute());
		
		commandCorrect.execute();
		assertEquals("Value jhas been set as exepcted", "", uv.getValue());
	}

	@Test
	public void testSetUnit() {
		IntProperty intProperty = PropertydefinitionsFactory.eINSTANCE.createIntProperty();
		UnitValuePropertyInstance uv = PropertyinstancesFactory.eINSTANCE.createUnitValuePropertyInstance();
		AUnit aUnit = QudvFactory.eINSTANCE.createSimpleUnit();
		
		uv.setType(intProperty);
		
		// now set something different such as the Unit to see that the original functionality of the
		// Set command has not been compromised
		Command commandSetUnit = new SetValuePropertyInstanceCommand(ed, uv, PropertyinstancesPackage.Literals.IUNIT_PROPERTY_INSTANCE__UNIT, aUnit);
		
		assertTrue("Command can set a unit", commandSetUnit.canExecute());
		
		commandSetUnit.execute();
		
		assertEquals("The unit has been set as expected", aUnit, uv.getUnit());
	}
	
	@Test
	public void testSetFloat() {
		FloatProperty floatProperty = PropertydefinitionsFactory.eINSTANCE.createFloatProperty();
		UnitValuePropertyInstance uv = PropertyinstancesFactory.eINSTANCE.createUnitValuePropertyInstance();
		
		uv.setType(floatProperty);
		
		Command commandCorrect = new SetValuePropertyInstanceCommand(ed, uv, PropertyinstancesPackage.Literals.VALUE_PROPERTY_INSTANCE__VALUE, "0.5");
		Command commandInCorrectInt = new SetValuePropertyInstanceCommand(ed, uv, PropertyinstancesPackage.Literals.VALUE_PROPERTY_INSTANCE__VALUE, "5");
		Command commandInCorrectString = new SetValuePropertyInstanceCommand(ed, uv, PropertyinstancesPackage.Literals.VALUE_PROPERTY_INSTANCE__VALUE, "TEST");
		
		assertTrue("Command with correct value format", commandCorrect.canExecute());
		assertTrue("Command with int value format", commandInCorrectInt.canExecute());
		assertFalse("Command with incorrect value format", commandInCorrectString.canExecute());
	}

	@Test
	public void testSetString() {
		StringProperty stringProperty = PropertydefinitionsFactory.eINSTANCE.createStringProperty();
		ValuePropertyInstance v = PropertyinstancesFactory.eINSTANCE.createValuePropertyInstance();
		
		v.setType(stringProperty);
		
		Command commandCorrect = new SetValuePropertyInstanceCommand(ed, v, PropertyinstancesPackage.Literals.VALUE_PROPERTY_INSTANCE__VALUE, "TEST");
		
		assertTrue("command with correct value format", commandCorrect.canExecute());
	}
}
