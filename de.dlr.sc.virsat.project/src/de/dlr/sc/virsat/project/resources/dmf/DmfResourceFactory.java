/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.project.resources.dmf;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;

/**
 * Resource factory for creating DMF Resources.
 * @author muel_s8
 *
 */

public class DmfResourceFactory implements Resource.Factory {

	@Override
	public Resource createResource(URI uri) {
		Resource dmfResource = new DmfResource();
		dmfResource.setURI(uri);
		return dmfResource;
	}

}
