/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.dvlm.structural.provider;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;
import static org.junit.Assert.assertSame;
import org.eclipse.emf.common.notify.Adapter;
import org.junit.Test;

/**
 * Test Case for the AdapterFactory checking if the correct Adapter is created
 * @author lobe_el
 *
 */
public class DVLMStrcuturalItemProviderAdapterFactoryTest {

	@Test
	public void testCreateStructuralElementInstanceAdapter() {
		DVLMStructuralItemProviderAdapterFactory asipaf = new DVLMStructuralItemProviderAdapterFactory();
		Adapter createdAdapter = asipaf.createStructuralElementInstanceAdapter();
		
		assertThat("The received Adapter is the right one", createdAdapter, instanceOf(DVLMStructuralElementInstanceItemProvider.class));
		
		Adapter createdAdapter2 = asipaf.createStructuralElementInstanceAdapter();
		
		assertSame("Did not create a new adapter, still the same", createdAdapter, createdAdapter2);
	}

	@Test
	public void testCreateStructuralElementAdapter() {
		DVLMStructuralItemProviderAdapterFactory asipaf = new DVLMStructuralItemProviderAdapterFactory();
		Adapter createdAdapter = asipaf.createStructuralElementAdapter();
		
		assertThat("The received Adapter is the right one", createdAdapter, instanceOf(DVLMStructuralElementItemProvider.class));
		
		Adapter createdAdapter2 = asipaf.createStructuralElementAdapter();
		
		assertSame("Did not create a new adapter, still the same", createdAdapter, createdAdapter2);
	}
}
