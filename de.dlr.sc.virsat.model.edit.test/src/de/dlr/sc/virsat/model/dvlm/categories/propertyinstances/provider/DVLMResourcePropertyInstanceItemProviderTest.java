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

import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.PropertydefinitionsFactory;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.ResourceProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.PropertyinstancesFactory;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ResourcePropertyInstance;

/**
 * This class tests the DVLMResourcePropertyInstanceItemProvider
 * @author muel_s8
 *
 */

public class DVLMResourcePropertyInstanceItemProviderTest {

	private ComposedAdapterFactory adapterFactory;
	
	@Before
	public void setup() {
		adapterFactory = new ComposedAdapterFactory(ComposedAdapterFactory.Descriptor.Registry.INSTANCE);
	}
	
	@Test
	public void testGetText() {
		DVLMResourcePropertyInstanceItemProvider dvlmRpiip = new DVLMResourcePropertyInstanceItemProvider(adapterFactory);
		
		ResourceProperty rp = PropertydefinitionsFactory.eINSTANCE.createResourceProperty();
		rp.setName("RP");
		ResourcePropertyInstance rpi = PropertyinstancesFactory.eINSTANCE.createResourcePropertyInstance();
		rpi.setType(rp);
		rpi.setResourceUri("TEST-URI");
		
		final String EXPECTED = "RP: TEST-URI";
		String actual = dvlmRpiip.getText(rpi);
		assertEquals("The output is like expected", EXPECTED, actual);
	}
	
}
