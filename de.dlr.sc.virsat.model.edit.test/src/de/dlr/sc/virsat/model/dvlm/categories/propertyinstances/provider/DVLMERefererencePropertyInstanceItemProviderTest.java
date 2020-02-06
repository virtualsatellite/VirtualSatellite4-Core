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

import org.junit.Test;

import de.dlr.sc.virsat.model.dvlm.categories.CategoriesFactory;
import de.dlr.sc.virsat.model.dvlm.categories.Category;
import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.EReferenceProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.PropertydefinitionsFactory;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.EReferencePropertyInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.PropertyinstancesFactory;
import de.dlr.sc.virsat.model.dvlm.categories.util.CategoryInstantiator;

/**
 * This class tests the DVLMEReferencePropertyInstanceItemProvider
 *
 */

public class DVLMERefererencePropertyInstanceItemProviderTest {
	
	@Test
	public void testGetText() {
		PropertyinstancesItemProviderAdapterFactory adapterFactory = new DVLMPropertyinstancesItemProviderAdapterFactory();
		DVLMERefererencePropertyInstanceItemProvider dvlmRpiip = (DVLMERefererencePropertyInstanceItemProvider) adapterFactory.createEReferencePropertyInstanceAdapter();
		EReferenceProperty rp = PropertydefinitionsFactory.eINSTANCE.createEReferenceProperty();
		rp.setName("ERP");
		EReferencePropertyInstance rpi = PropertyinstancesFactory.eINSTANCE.createEReferencePropertyInstance();
		rpi.setType(rp);
		
		final String EXPECTED_UNSET = "ERP -> ";
		String actual = dvlmRpiip.getText(rpi);
		assertEquals("The output is like expected", EXPECTED_UNSET, actual);
		
		Category cat = CategoriesFactory.eINSTANCE.createCategory();
		CategoryInstantiator instantiator = new CategoryInstantiator();
		CategoryAssignment ca = instantiator.generateInstance(cat, "CA");
		
		rp.setReferenceType(ca.eClass());
		rpi.setReference(ca);
		
		final String EXPECTED = "ERP -> CA";
		actual = dvlmRpiip.getText(rpi);
		assertEquals("The output is like expected", EXPECTED, actual);
	}

}
