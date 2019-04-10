/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.provider;

import static org.junit.Assert.assertEquals;

import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.junit.Before;
import org.junit.Test;

import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.EnumValueDefinition;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.PropertydefinitionsFactory;

/**
 * This class tests the Enum Value Definition provider
 * @author fisc_ph
 *
 */

public class DVLMEnumValueDefinitionItemProviderTest {

	private ComposedAdapterFactory adapterFactory;
	
	@Before
	public void setup() {
		adapterFactory = new ComposedAdapterFactory(ComposedAdapterFactory.Descriptor.Registry.INSTANCE);

		adapterFactory.addAdapterFactory(new DVLMPropertydefinitionsItemProviderAdapterFactory());
	}
	
	@Test
	public void testGetText() {
		DVLMEnumValueDefinitionItemProvider dvlmEpIp = new DVLMEnumValueDefinitionItemProvider(adapterFactory);
		
		EnumValueDefinition evd = PropertydefinitionsFactory.eINSTANCE.createEnumValueDefinition();
		evd.setName("HIGH");
		evd.setValue("20");
		
		
		final String EXPECTED = "HIGH=20";
		String actual = dvlmEpIp.getText(evd);
		assertEquals("The output is like expected", EXPECTED, actual);
	}
}
