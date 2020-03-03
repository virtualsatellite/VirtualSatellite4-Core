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

import de.dlr.sc.virsat.model.concept.types.ABeanObject;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.EReferencePropertyInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.PropertyinstancesPackage;

/**
 * Bean class to warp EObject values of EReferencePropertyInstances. Supports getting and setting values via command.
 * @param <Type> the reference type
 */
public class BeanPropertyEReference<Type extends EObject> extends ABeanObject<EReferencePropertyInstance> implements IBeanProperty<EReferencePropertyInstance, Type> {

	/**
	 * Standard Constructor
	 */
	public BeanPropertyEReference() {
	}
	
	/**
	 * Constructor to directly set the type instance
	 * @param erpi the type instance to be used
	 */
	public BeanPropertyEReference(EReferencePropertyInstance erpi) {
		super(erpi);
	}
	
	public Command setValue(EditingDomain ed, EObject value) {
		return SetCommand.create(ed, ti, PropertyinstancesPackage.Literals.EREFERENCE_PROPERTY_INSTANCE__REFERENCE, value);
	}

	public boolean isSet() {
		return ti.getReference() != null;
	}
	
	public void unset() {
		ti.setReference(null);
	}
	
	public void setValue(Type value) {
		ti.setReference(value);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Type getValue() {
		return (Type) ti.getReference();
	}
}
