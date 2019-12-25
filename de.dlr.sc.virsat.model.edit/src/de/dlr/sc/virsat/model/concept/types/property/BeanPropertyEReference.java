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

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.EditingDomain;

import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.PropertyinstancesPackage;

/**
 *
 */
public class BeanPropertyEReference extends ABeanEObjectProperty {

	@Override
	public Command setValue(EditingDomain ed, EObject value) {
		return SetCommand.create(ed, ti, PropertyinstancesPackage.Literals.EREFERENCE_PROPERTY_INSTANCE__REFERENCE, value);
	}
	
	@Override
	public void setValue(EObject value) {
		ti.setReference(value);
	}
	
	@Override
	public EObject getValue() {
		return ti.getReference();
	}
}
