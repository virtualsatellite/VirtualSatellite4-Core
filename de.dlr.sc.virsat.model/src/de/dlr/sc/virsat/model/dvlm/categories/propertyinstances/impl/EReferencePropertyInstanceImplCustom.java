/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.EReferenceProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.EReferencePropertyHelper;

public class EReferencePropertyInstanceImplCustom extends EReferencePropertyInstanceImpl {
	
	@Override
	public void setReference(EObject newReference) {
		if (isValueTypeValid(newReference)) {
			super.setReference(newReference);
		}
	}
	
	protected boolean isValueTypeValid(EObject newValue) {
		EClass type = new EReferencePropertyHelper().getResolvedEClassType((EReferenceProperty) this.getType());
		return newValue.eClass().equals(type) || type.getEAllSuperTypes().contains(type.eClass());
	}

}
