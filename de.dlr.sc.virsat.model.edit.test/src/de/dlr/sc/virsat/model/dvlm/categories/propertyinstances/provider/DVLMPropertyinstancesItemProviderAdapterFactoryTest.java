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
import static org.junit.Assert.assertSame;
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
		
		assertThat("The received Adapter is the right one", createdAdapter, instanceOf(DVLMValuePropertyInstanceItemProvider.class));
		
		Adapter createdAdapter2 = dvlmPiipaf.createValuePropertyInstanceAdapter();
		
		assertSame("Did not create a new adapter, still the same", createdAdapter, createdAdapter2);
	}
	
	@Test
	public void testCreateComposedPropertyInstanceAdapter() {
		DVLMPropertyinstancesItemProviderAdapterFactory dvlmPiipaf = new  DVLMPropertyinstancesItemProviderAdapterFactory();
		Adapter createdAdapter = dvlmPiipaf.createComposedPropertyInstanceAdapter();
		
		assertThat("The received Adapter is the right one", createdAdapter, instanceOf(DVLMComposedPropertyInstanceItemProvider.class));
		
		Adapter createdAdapter2 = dvlmPiipaf.createComposedPropertyInstanceAdapter();
		
		assertSame("Did not create a new adapter, still the same", createdAdapter, createdAdapter2);
	}

	@Test
	public void testCreateReferencePropertyInstanceAdapter() {
		DVLMPropertyinstancesItemProviderAdapterFactory dvlmPiipaf = new  DVLMPropertyinstancesItemProviderAdapterFactory();
		Adapter createdAdapter = dvlmPiipaf.createReferencePropertyInstanceAdapter();
		
		assertThat("The received Adapter is the right one", createdAdapter, instanceOf(DVLMRefererencePropertyInstanceItemProvider.class));
		
		Adapter createdAdapter2 = dvlmPiipaf.createReferencePropertyInstanceAdapter();
		
		assertSame("Did not create a new adapter, still the same", createdAdapter, createdAdapter2);
	}
	
	@Test
	public void testCreateResourcePropertyInstanceAdapter() {
		DVLMPropertyinstancesItemProviderAdapterFactory dvlmPiipaf = new  DVLMPropertyinstancesItemProviderAdapterFactory();
		Adapter createdAdapter = dvlmPiipaf.createResourcePropertyInstanceAdapter();
		
		assertThat("The received Adapter is the right one", createdAdapter, instanceOf(DVLMResourcePropertyInstanceItemProvider.class));
		
		Adapter createdAdapter2 = dvlmPiipaf.createResourcePropertyInstanceAdapter();
		
		assertSame("Did not create a new adapter, still the same", createdAdapter, createdAdapter2);
	}
	
	@Test
	public void testCreateUnitValueInstanceAdapter() {
		DVLMPropertyinstancesItemProviderAdapterFactory dvlmPiipaf = new  DVLMPropertyinstancesItemProviderAdapterFactory();
		Adapter createdAdapter = dvlmPiipaf.createUnitValuePropertyInstanceAdapter();
		
		assertThat("The received Adapter is the right one", createdAdapter, instanceOf(DVLMUnitValuePropertyInstanceItemProvider.class));
		
		Adapter createdAdapter2 = dvlmPiipaf.createUnitValuePropertyInstanceAdapter();
		
		assertSame("Did not create a new adapter, still the same", createdAdapter, createdAdapter2);
	}
	
	@Test
	public void testCreateValuePropertyInstanceAdapter() {
		DVLMPropertyinstancesItemProviderAdapterFactory dvlmPiipaf = new  DVLMPropertyinstancesItemProviderAdapterFactory();
		Adapter createdAdapter = dvlmPiipaf.createValuePropertyInstanceAdapter();
		
		assertThat("The received Adapter is the right one", createdAdapter, instanceOf(DVLMValuePropertyInstanceItemProvider.class));
		
		Adapter createdAdapter2 = dvlmPiipaf.createValuePropertyInstanceAdapter();
		
		assertSame("Did not create a new adapter, still the same", createdAdapter, createdAdapter2);
	}
	
	@Test
	public void testCreateEnumUnitPropertyInstanceAdapter() {
		DVLMPropertyinstancesItemProviderAdapterFactory dvlmPiipaf = new  DVLMPropertyinstancesItemProviderAdapterFactory();
		Adapter createdAdapter = dvlmPiipaf.createEnumUnitPropertyInstanceAdapter();
		
		assertThat("The received Adapter is the right one", createdAdapter, instanceOf(DVLMEnumUnitPropertyInstanceItemProvider.class));
		
		Adapter createdAdapter2 = dvlmPiipaf.createEnumUnitPropertyInstanceAdapter();
		
		assertSame("Did not create a new adapter, still the same", createdAdapter, createdAdapter2);
	}
}
