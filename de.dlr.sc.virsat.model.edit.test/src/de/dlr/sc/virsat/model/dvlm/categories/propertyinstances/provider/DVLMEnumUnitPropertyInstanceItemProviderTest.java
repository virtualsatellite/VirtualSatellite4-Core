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

import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.junit.Before;
import org.junit.Test;

import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.EnumProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.EnumValueDefinition;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.PropertydefinitionsFactory;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.EnumUnitPropertyInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.PropertyinstancesFactory;

/**
 * This class tests the DVLMUnitValuePropertyInstanceItemProvider.
 * @author fisc_ph
 *
 */
public class DVLMEnumUnitPropertyInstanceItemProviderTest {

	private ComposedAdapterFactory adapterFactory;
	
	@Before
	public void setup() {
		adapterFactory = new ComposedAdapterFactory(ComposedAdapterFactory.Descriptor.Registry.INSTANCE);
	}
	
	@Test
	public void testGetText() {
		DVLMEnumUnitPropertyInstanceItemProvider dvlmUviip = new DVLMEnumUnitPropertyInstanceItemProvider(adapterFactory);
		
		EnumValueDefinition evd1 = PropertydefinitionsFactory.eINSTANCE.createEnumValueDefinition();
		
		evd1.setName("HIGH");
		evd1.setValue("12");
		
		EnumProperty enumProperty = PropertydefinitionsFactory.eINSTANCE.createEnumProperty();
		enumProperty.setName("maturity");
		enumProperty.getValues().add(evd1);
		enumProperty.setDefaultValue(evd1);
	
		EnumUnitPropertyInstance eupi = PropertyinstancesFactory.eINSTANCE.createEnumUnitPropertyInstance();
		eupi.setType(enumProperty);
		eupi.setValue(evd1);
		
		final String EXPECTED = "maturity: HIGH=12";
		String actual = dvlmUviip.getText(eupi);
		assertEquals("The output is like expected", EXPECTED, actual);
	}

}
