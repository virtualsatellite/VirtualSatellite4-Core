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

import org.eclipse.emf.common.notify.Adapter;

/**
 * Extension to the generic property instances item provider adapter factory
 * @author fisc_ph
 *
 */

public class DVLMPropertydefinitionsItemProviderAdapterFactory extends PropertydefinitionsItemProviderAdapterFactory {

	@Override
	public Adapter createEnumValueDefinitionAdapter() {
		if (enumValueDefinitionItemProvider == null) {
			enumValueDefinitionItemProvider = new DVLMEnumValueDefinitionItemProvider(this);
		}
		return enumValueDefinitionItemProvider;
	}
}
