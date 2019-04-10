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

import de.dlr.sc.virsat.model.dvlm.categories.CategoriesFactory;
import de.dlr.sc.virsat.model.dvlm.categories.Category;
import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.ComposedProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.FloatProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.PropertydefinitionsFactory;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ComposedPropertyInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.PropertyinstancesFactory;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ValuePropertyInstance;
import de.dlr.sc.virsat.model.dvlm.categories.provider.DVLMCategoriesItemProviderAdapterFactory;
import de.dlr.sc.virsat.model.dvlm.categories.util.CategoryInstantiator;

/**
 * This class tests the DVLMComposedPropertyInstanceItemProvider class.
 * @author muel_s8
 *
 */

public class DVLMComposedPropertyInstanceItemProviderTest {

	private ComposedAdapterFactory adapterFactory;
	
	@Before
	public void setup() {
		adapterFactory = new ComposedAdapterFactory(ComposedAdapterFactory.Descriptor.Registry.INSTANCE);

		adapterFactory.addAdapterFactory(new DVLMCategoriesItemProviderAdapterFactory());
		adapterFactory.addAdapterFactory(new DVLMPropertyinstancesItemProviderAdapterFactory());
	}
	
	@Test
	public void testGetText() {
		DVLMComposedPropertyInstanceItemProvider dvlmCpip = new DVLMComposedPropertyInstanceItemProvider(adapterFactory);
		
		ComposedProperty cp = PropertydefinitionsFactory.eINSTANCE.createComposedProperty();
		cp.setName("C");
		ComposedPropertyInstance cpi = PropertyinstancesFactory.eINSTANCE.createComposedPropertyInstance();
		cpi.setType(cp);
		
		Category cat = CategoriesFactory.eINSTANCE.createCategory();
		cp.setType(cat);
		FloatProperty float1 = PropertydefinitionsFactory.eINSTANCE.createFloatProperty();
		float1.setName("A");
		cat.getProperties().add(float1);
		FloatProperty float2 = PropertydefinitionsFactory.eINSTANCE.createFloatProperty();
		float2.setName("B");
		cat.getProperties().add(float2);
		
		CategoryInstantiator instantiator = new CategoryInstantiator();
		CategoryAssignment ca = instantiator.generateInstance(cat, "CA");
		cpi.setTypeInstance(ca);
		
		ValuePropertyInstance vpi1 = (ValuePropertyInstance) ca.getPropertyInstances().get(0);
		vpi1.setValue("5");
		
		ValuePropertyInstance vpi2 = (ValuePropertyInstance) ca.getPropertyInstances().get(1);
		vpi2.setValue("10");
		
		final String EXPECTED = "CA: A: 5, B: 10";
		String actual = dvlmCpip.getText(cpi);
		assertEquals("The output is like expected", EXPECTED, actual);
	}
}
