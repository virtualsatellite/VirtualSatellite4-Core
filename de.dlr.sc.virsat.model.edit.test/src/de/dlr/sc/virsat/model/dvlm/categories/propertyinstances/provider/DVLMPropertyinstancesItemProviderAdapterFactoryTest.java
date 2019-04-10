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

import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.eclipse.emf.common.notify.Adapter;
import org.junit.Test;

/**
 * This class tests the PropertyinstanceItemProviderAdapterFactory
 * @author muel_s8
 *
 */

public class DVLMPropertyinstancesItemProviderAdapterFactoryTest {

	@Test
	public void testCreateArrayInstanceAdapter() {
		DVLMPropertyinstancesItemProviderAdapterFactory dvlmPiipaf = new  DVLMPropertyinstancesItemProviderAdapterFactory();
		Adapter createdAdapter = dvlmPiipaf.createValuePropertyInstanceAdapter();
		
		assertTrue("The received Adapter is the right one", createdAdapter instanceof DVLMValuePropertyInstanceItemProvider);
		
		Adapter createdAdapter2 = dvlmPiipaf.createValuePropertyInstanceAdapter();
		
		assertSame("Did not create a new adapter, still the same", createdAdapter, createdAdapter2);
	}
	
	@Test
	public void testCreateComposedPropertyInstanceAdapter() {
		DVLMPropertyinstancesItemProviderAdapterFactory dvlmPiipaf = new  DVLMPropertyinstancesItemProviderAdapterFactory();
		Adapter createdAdapter = dvlmPiipaf.createComposedPropertyInstanceAdapter();
		
		assertTrue("The received Adapter is the right one", createdAdapter instanceof DVLMComposedPropertyInstanceItemProvider);
		
		Adapter createdAdapter2 = dvlmPiipaf.createComposedPropertyInstanceAdapter();
		
		assertSame("Did not create a new adapter, still the same", createdAdapter, createdAdapter2);
	}

	@Test
	public void testCreateReferencePropertyInstanceAdapter() {
		DVLMPropertyinstancesItemProviderAdapterFactory dvlmPiipaf = new  DVLMPropertyinstancesItemProviderAdapterFactory();
		Adapter createdAdapter = dvlmPiipaf.createReferencePropertyInstanceAdapter();
		
		assertTrue("The received Adapter is the right one", createdAdapter instanceof DVLMRefererencePropertyInstanceItemProvider);
		
		Adapter createdAdapter2 = dvlmPiipaf.createReferencePropertyInstanceAdapter();
		
		assertSame("Did not create a new adapter, still the same", createdAdapter, createdAdapter2);
	}
	
	@Test
	public void testCreateResourcePropertyInstanceAdapter() {
		DVLMPropertyinstancesItemProviderAdapterFactory dvlmPiipaf = new  DVLMPropertyinstancesItemProviderAdapterFactory();
		Adapter createdAdapter = dvlmPiipaf.createResourcePropertyInstanceAdapter();
		
		assertTrue("The received Adapter is the right one", createdAdapter instanceof DVLMResourcePropertyInstanceItemProvider);
		
		Adapter createdAdapter2 = dvlmPiipaf.createResourcePropertyInstanceAdapter();
		
		assertSame("Did not create a new adapter, still the same", createdAdapter, createdAdapter2);
	}
	
	@Test
	public void testCreateUnitValueInstanceAdapter() {
		DVLMPropertyinstancesItemProviderAdapterFactory dvlmPiipaf = new  DVLMPropertyinstancesItemProviderAdapterFactory();
		Adapter createdAdapter = dvlmPiipaf.createUnitValuePropertyInstanceAdapter();
		
		assertTrue("The received Adapter is the right one", createdAdapter instanceof DVLMUnitValuePropertyInstanceItemProvider);
		
		Adapter createdAdapter2 = dvlmPiipaf.createUnitValuePropertyInstanceAdapter();
		
		assertSame("Did not create a new adapter, still the same", createdAdapter, createdAdapter2);
	}
	
	@Test
	public void testCreateValuePropertyInstanceAdapter() {
		DVLMPropertyinstancesItemProviderAdapterFactory dvlmPiipaf = new  DVLMPropertyinstancesItemProviderAdapterFactory();
		Adapter createdAdapter = dvlmPiipaf.createValuePropertyInstanceAdapter();
		
		assertTrue("The received Adapter is the right one", createdAdapter instanceof DVLMValuePropertyInstanceItemProvider);
		
		Adapter createdAdapter2 = dvlmPiipaf.createValuePropertyInstanceAdapter();
		
		assertSame("Did not create a new adapter, still the same", createdAdapter, createdAdapter2);
	}
	
	@Test
	public void testCreateEnumUnitPropertyInstanceAdapter() {
		DVLMPropertyinstancesItemProviderAdapterFactory dvlmPiipaf = new  DVLMPropertyinstancesItemProviderAdapterFactory();
		Adapter createdAdapter = dvlmPiipaf.createEnumUnitPropertyInstanceAdapter();
		
		assertTrue("The received Adapter is the right one", createdAdapter instanceof DVLMEnumUnitPropertyInstanceItemProvider);
		
		Adapter createdAdapter2 = dvlmPiipaf.createEnumUnitPropertyInstanceAdapter();
		
		assertSame("Did not create a new adapter, still the same", createdAdapter, createdAdapter2);
	}
}
