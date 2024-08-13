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

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;
import static org.junit.Assert.assertEquals;
import java.util.HashMap;

import org.eclipse.emf.common.command.BasicCommandStack;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.ReflectiveItemProviderAdapterFactory;
import org.junit.Before;
import org.junit.Test;

import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.FloatProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.PropertydefinitionsFactory;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.provider.PropertydefinitionsItemProviderAdapterFactory;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.PropertyinstancesFactory;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.PropertyinstancesPackage;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.UnitValuePropertyInstance;
import de.dlr.sc.virsat.model.dvlm.categories.provider.DVLMCategoriesItemProviderAdapterFactory;
import de.dlr.sc.virsat.model.dvlm.command.SetValuePropertyInstanceCommand;
import de.dlr.sc.virsat.model.dvlm.concepts.provider.ConceptsItemProviderAdapterFactory;
import de.dlr.sc.virsat.model.dvlm.general.provider.GeneralItemProviderAdapterFactory;
import de.dlr.sc.virsat.model.dvlm.provider.DVLMDVLMItemProviderAdapterFactory;
import de.dlr.sc.virsat.model.dvlm.qudv.AUnit;
import de.dlr.sc.virsat.model.dvlm.qudv.QudvFactory;
import de.dlr.sc.virsat.model.dvlm.resource.provider.DVLMResourceItemProviderAdapterFactory;
import de.dlr.sc.virsat.model.dvlm.roles.RoleManagementCheckCommand;
import de.dlr.sc.virsat.model.dvlm.roles.UserRegistry;
import de.dlr.sc.virsat.model.dvlm.roles.provider.RolesItemProviderAdapterFactory;
import de.dlr.sc.virsat.model.dvlm.structural.provider.DVLMStructuralItemProviderAdapterFactory;
import de.dlr.sc.virsat.model.dvlm.units.provider.UnitsItemProviderAdapterFactory;

/**
 * This class tests the DVLMUnitValuePropertyInstanceItemProvider.
 * @author muel_s8
 *
 */

public class DVLMUnitValuePropertyInstanceItemProviderTest {

	private ComposedAdapterFactory adapterFactory;
	private EditingDomain ed;
	
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
		ed = new AdapterFactoryEditingDomain(adapterFactory, commandStack, new HashMap<Resource, Boolean>());

		UserRegistry.getInstance().setSuperUser(true);
	}
	
	@Test
	public void testGetText() {
		DVLMUnitValuePropertyInstanceItemProvider dvlmUviip = new DVLMUnitValuePropertyInstanceItemProvider(adapterFactory);
		
		FloatProperty fp = PropertydefinitionsFactory.eINSTANCE.createFloatProperty();
		fp.setName("FP");
		UnitValuePropertyInstance uvpi = PropertyinstancesFactory.eINSTANCE.createUnitValuePropertyInstance();
		uvpi.setType(fp);
		uvpi.setValue("5");
		AUnit unit = QudvFactory.eINSTANCE.createSimpleUnit();
		unit.setSymbol("A");
		uvpi.setUnit(unit);
		
		final String EXPECTED = "FP: 5A";
		String actual = dvlmUviip.getText(uvpi);
		assertEquals("The output is like expected", EXPECTED, actual);
	}

	@Test
	public void testCreateSetCommand() {
		FloatProperty fp = PropertydefinitionsFactory.eINSTANCE.createFloatProperty();
		UnitValuePropertyInstance vpi = PropertyinstancesFactory.eINSTANCE.createUnitValuePropertyInstance();
		vpi.setType(fp);

		CompoundCommand command = (CompoundCommand) SetCommand.create(ed, vpi, PropertyinstancesPackage.Literals.VALUE_PROPERTY_INSTANCE__VALUE, "55.5");
		
		Object[] objects = command.getCommandList().toArray();
		RoleManagementCheckCommand checkCommand2 = (RoleManagementCheckCommand) objects[1];
		SetCommand setCommand = (SetCommand) checkCommand2.getWrappedCommand();
		
		assertThat("Command is of correct Type", setCommand, instanceOf(SetValuePropertyInstanceCommand.class));
	}
}
