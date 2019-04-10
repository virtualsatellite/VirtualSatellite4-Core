/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.dvlm.categories.provider;

import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.eclipse.emf.common.notify.Adapter;
import org.junit.Test;

/**
 * Test Case for the AdapterFactory checking if the correct Adapter is created
 * @author lobe_el
 *
 */
public class DVLMCategoriesItemProviderAdapterFactoryTest {

	@Test
	public void testCreateCategoryAssignmentAdapter() {
		DVLMCategoriesItemProviderAdapterFactory asipaf = new DVLMCategoriesItemProviderAdapterFactory();
		Adapter createdAdapter = asipaf.createCategoryAssignmentAdapter();
		
		assertTrue("The received Adapter is the right one", createdAdapter instanceof DVLMCategoryAssignmentItemProvider);
		
		Adapter createdAdapter2 = asipaf.createCategoryAssignmentAdapter();
		
		assertSame("Did not create a new adapter, still the same", createdAdapter, createdAdapter2);
	}
	
	@Test
	public void testCreateCategoryAdapter() {
		DVLMCategoriesItemProviderAdapterFactory asipaf = new DVLMCategoriesItemProviderAdapterFactory();
		Adapter createdAdapter = asipaf.createCategoryAdapter();
		
		assertTrue("The received Adapter is the right one", createdAdapter instanceof DVLMCategoryItemProvider);
		
		Adapter createdAdapter2 = asipaf.createCategoryAdapter();
		
		assertSame("Did not create a new adapter, still the same", createdAdapter, createdAdapter2);
	}
}
