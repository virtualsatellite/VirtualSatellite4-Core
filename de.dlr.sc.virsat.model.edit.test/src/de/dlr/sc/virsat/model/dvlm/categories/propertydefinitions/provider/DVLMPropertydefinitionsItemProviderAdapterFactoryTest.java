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

import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.eclipse.emf.common.notify.Adapter;
import org.junit.Test;

/**
 * This class tests the PropertyinstanceItemProviderAdapterFactory
 * @author fisc_ph
 *
 */

public class DVLMPropertydefinitionsItemProviderAdapterFactoryTest {

	@Test
	public void testCreateEnumValueDefinitionAdapter() {
		DVLMPropertydefinitionsItemProviderAdapterFactory dvlmPiipaf = new  DVLMPropertydefinitionsItemProviderAdapterFactory();
		Adapter createdAdapter = dvlmPiipaf.createEnumValueDefinitionAdapter();
		
		assertTrue("The received Adapter is the right one", createdAdapter instanceof DVLMEnumValueDefinitionItemProvider);
		
		Adapter createdAdapter2 = dvlmPiipaf.createEnumValueDefinitionAdapter();
		
		assertSame("Did not create a new adapter, still the same", createdAdapter, createdAdapter2);
	}
}
