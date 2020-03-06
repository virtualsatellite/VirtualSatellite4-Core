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
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.EditingDomain;

import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.PropertyinstancesPackage;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ValuePropertyInstance;

/**
 * Class to wrap IntPropertyInstances
 * @author fisc_ph
 *
 */
public class BeanPropertyString extends ABeanProperty<String> {

	/**
	 * Standard Constructor
	 */
	public BeanPropertyString() {
	}
	
	/**
	 * Constructor to directly set the type instance
	 * @param vpi the type instance to be used
	 */
	public BeanPropertyString(ValuePropertyInstance vpi) {
		setTypeInstance(vpi);
	}
	
	@Override
	public Command setValue(EditingDomain ed, String value) {
		return SetCommand.create(ed, ti, PropertyinstancesPackage.Literals.VALUE_PROPERTY_INSTANCE__VALUE, value);
	}
	
	@Override
	public void setValue(String value) {
		ti.setValue(value);
	}
	
	@Override
	public String getValue() {
		return ti.getValue();
	}
}
