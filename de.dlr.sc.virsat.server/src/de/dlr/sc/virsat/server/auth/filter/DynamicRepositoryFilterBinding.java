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

import de.dlr.sc.virsat.server.resources.modelaccess.RepositoryAccessResource;
import jakarta.ws.rs.container.DynamicFeature;
import jakarta.ws.rs.container.ResourceInfo;
import jakarta.ws.rs.core.FeatureContext;

/**
 * Binds the RepositoryFilter which is used to check whether
 * a user has access to a given repository
 */
public class DynamicRepositoryFilterBinding implements DynamicFeature {

	@Override
	public void configure(ResourceInfo resourceInfo, FeatureContext context) {
		if (RepositoryAccessResource.RESOURCE_CLASSES.contains(resourceInfo.getResourceClass())) {
			context.register(RepositoryFilter.class);
		}
	}

}
