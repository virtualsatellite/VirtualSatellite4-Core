/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.dvlm.resource.provider;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.edit.provider.resource.ResourceItemProviderAdapterFactory;

/**
 * An extension to the resource item provider factory since we changed the provider for creating
 * commands allowing to remove and delete contents form a resource.
 * @author fisc_ph
 *
 */
public class DVLMResourceItemProviderAdapterFactory extends ResourceItemProviderAdapterFactory {

	@Override
	public Adapter createResourceAdapter() {
		return new DVLMResourceItemProvider(this);
	}
}
