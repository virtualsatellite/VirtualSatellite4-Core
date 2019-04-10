/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.project.ui.comparer;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jface.viewers.IElementComparer;

import de.dlr.sc.virsat.model.dvlm.general.IUuid;
import de.dlr.sc.virsat.model.dvlm.types.impl.VirSatUuid;

/**
 * Compares two objects using their URIs. A URI is a string like "platform:/resource/VirtualSatellite/data/Repository.dvlm#8115d1c4-67e6-48cb-9dc6-1fff7ab66f39"
 * which contains UUID. Will be different for objects with same UUID in different projects
 */

public class VirSatComparer implements IElementComparer {
	
	@Override
	public boolean equals(Object elementA, Object elementB) {
		if ((elementA instanceof EObject) && (elementB instanceof EObject)) {
			URI uriA = EcoreUtil.getURI((EObject) elementA);
			URI uriB = EcoreUtil.getURI((EObject) elementB);
			
			return uriA.equals(uriB);
		}
		
		return elementA.equals(elementB);
	}

	@Override
	public int hashCode(Object element) {
		if (element instanceof IUuid) {
			
			// Here we need to check for the UUID instead of the URI
			// This is because a URI of an object can change, for example 
			// by being moved from one resource to another or it it is deleted
			// the the URI will lose the prefix and just have the fragment id
			
			// But objects such as hashMaps as used in the Navigator require
			// the contract that the same object should always have the same hashCode
			// otherwise operations such as removal fail, leading to invalid widgets
			// populating the workspace.
			
			// Objects from different projects having the same hashCode is not a problem
			// Since equals() can deal with the finer checks
			
			VirSatUuid uuid = ((IUuid) element).getUuid();
			return uuid.toString().hashCode();
		}
		
		return element.hashCode();
	}
}
