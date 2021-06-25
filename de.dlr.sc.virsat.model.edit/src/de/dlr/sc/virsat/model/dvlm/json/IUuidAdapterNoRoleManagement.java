/*******************************************************************************
 * Copyright (c) 2021 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.dvlm.json;

import org.eclipse.emf.ecore.resource.ResourceSet;

/**
 * Adapter for a IUuid from/to a UUID that ignores the userContext
 */
public class IUuidAdapterNoRoleManagement extends IUuidAdapter {

	public IUuidAdapterNoRoleManagement() {
		super();
	}
	
	public IUuidAdapterNoRoleManagement(ResourceSet resourceSet) {
		super(resourceSet);
	}
}
