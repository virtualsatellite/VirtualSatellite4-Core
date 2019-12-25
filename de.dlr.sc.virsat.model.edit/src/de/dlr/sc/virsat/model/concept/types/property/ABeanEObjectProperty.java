/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.concept.types.property;

import org.eclipse.emf.ecore.EObject;

import de.dlr.sc.virsat.model.concept.types.ABeanObject;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.EReferencePropertyInstance;

/**
 * Abstract implementation to the interface dealing with EReference properties 
 
 */
public abstract class ABeanEObjectProperty extends ABeanObject<EReferencePropertyInstance> implements IBeanProperty<EReferencePropertyInstance, EObject> {	
	
	@Override
	public boolean isSet() {
		return ti.getReference() != null;
	}
	
	@Override
	public void unset() {
		ti.setReference(null);
	}
	
}
