/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.dvlm.qudv.provider;

import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.eclipse.emf.common.notify.Adapter;
import org.junit.Test;

/**
 * This class tests the DVLMQuidvItemProviderAdapterFactory
 * @author muel_s8
 *
 */

public class DVLMQudvItemProviderAdapterFactoryTest {

	@Test
	public void testCreateCategoryAssignmentAdapter() {
		DVLMQudvItemProviderAdapterFactory dvlmQipaf = new DVLMQudvItemProviderAdapterFactory();
		Adapter createdAdapter = dvlmQipaf.createSystemOfQuantitiesAdapter();
		
		assertTrue("The received Adapter is the right one", createdAdapter instanceof DVLMSystemOfQuantitiesItemProvider);
		
		Adapter createdAdapter2 = dvlmQipaf.createSystemOfQuantitiesAdapter();
		
		assertSame("Did not create a new adapter, still the same", createdAdapter, createdAdapter2);
	}

}
