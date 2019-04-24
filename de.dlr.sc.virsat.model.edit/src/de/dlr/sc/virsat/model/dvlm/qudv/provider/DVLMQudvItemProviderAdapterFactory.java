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

import org.eclipse.emf.common.notify.Adapter;

/**
 * DVLM implementation of the QudvItemProviderAdapterFactory to provide
 * custom DVLM implementations of the adapters.
 * @author muel_s8
 *
 */

public class DVLMQudvItemProviderAdapterFactory extends QudvItemProviderAdapterFactory {
	@Override
	public Adapter createSystemOfQuantitiesAdapter() {
		if (systemOfQuantitiesItemProvider == null) {
			systemOfQuantitiesItemProvider = new DVLMSystemOfQuantitiesItemProvider(this);
		}

		return systemOfQuantitiesItemProvider;
	}
}
