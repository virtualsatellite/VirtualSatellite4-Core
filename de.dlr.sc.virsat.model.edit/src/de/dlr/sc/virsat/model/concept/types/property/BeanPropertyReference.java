/*******************************************************************************
 * Copyright (c) 2020 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.concept.types.property;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.EditingDomain;

import de.dlr.sc.virsat.model.concept.types.ABeanObject;
import de.dlr.sc.virsat.model.dvlm.categories.ATypeInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.PropertyinstancesPackage;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ReferencePropertyInstance;

public class BeanPropertyReference<Type extends ATypeInstance> extends ABeanObject<ReferencePropertyInstance> implements IBeanProperty<ReferencePropertyInstance, Type> {

	/**
	 * Standard Constructor
	 */
	public BeanPropertyReference() {
	}
	
	/**
	 * Constructor to directly set the type instance
	 * @param rpi the type instance to be used
	 */
	public BeanPropertyReference(ReferencePropertyInstance rpi) {
		super(rpi);
	}
	
	@Override
	public void setValue(Type value) {
		ti.setReference(value);
	}

	@Override
	public Command setValue(EditingDomain ed, Type value) {
		return SetCommand.create(ed, ti, PropertyinstancesPackage.Literals.REFERENCE_PROPERTY_INSTANCE__REFERENCE, value);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Type getValue() {
		return (Type) ti.getReference();
	}

	@Override
	public boolean isSet() {
		return ti.getReference() != null;
	}

	@Override
	public void unset() {
		ti.setReference(null);
	}

}
