/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.ecore.xmi.impl;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;

/**
 * ResourceFactory to be used with EMF ResourceSets to create DvlmXMIResources
 * @author fisc_ph
 *
 */
public class DvlmXMIResourceFactoryImpl extends XMIResourceFactoryImpl implements Resource.Factory {

	@Override
	public Resource createResource(URI uri) {
		return new DvlmXMIResourceImpl(uri);
	}
	
}
