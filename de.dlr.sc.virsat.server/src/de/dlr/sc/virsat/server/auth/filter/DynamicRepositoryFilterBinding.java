/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.server.auth.filter;

import javax.ws.rs.container.DynamicFeature;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.FeatureContext;

import de.dlr.sc.virsat.server.resources.ModelAccessResource.RepoModelAccessResource;

/**
 * Binds the RepositoryFilter to specified classes and functions,
 * so the filter will only be used on those
 */
public class DynamicRepositoryFilterBinding implements DynamicFeature {

	@Override
	public void configure(ResourceInfo resourceInfo, FeatureContext context) {
		if (RepoModelAccessResource.class.equals(resourceInfo.getResourceClass())) {
			context.register(RepositoryFilter.class);
		}
	}

}
