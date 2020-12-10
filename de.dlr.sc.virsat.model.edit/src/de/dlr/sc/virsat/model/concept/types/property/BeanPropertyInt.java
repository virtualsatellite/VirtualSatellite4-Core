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

import javax.xml.bind.annotation.XmlElement;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.EditingDomain;

import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.PropertyinstancesPackage;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.UnitValuePropertyInstance;

/**
 * Class to wrap IntPropertyInstances
 *
 */
public class BeanPropertyInt extends ABeanUnitValueProperty<Long> {

	/**
	 * Standard Constructor
	 */
	public BeanPropertyInt() {
	}
	
	/**
	 * Constructor to directly set the type instance
	 * @param uvpi the type instance to be used
	 */
	public BeanPropertyInt(UnitValuePropertyInstance uvpi) {
		super(uvpi);
	}
	
	@Override
	public Command setValue(EditingDomain ed, Long value) {
		return SetCommand.create(ed, ti, PropertyinstancesPackage.Literals.VALUE_PROPERTY_INSTANCE__VALUE, Long.toString(value));
	}
	
	@Override
	public void setValue(Long value) {
		if (value == null) {
			ti.setValue(null);
		} else {
			ti.setValue(Long.toString(value));
		}
	}
	
	@Override
	@XmlElement(nillable = true)
	public Long getValue() throws NumberFormatException {
		if (isSet()) {
			return Long.parseLong(ti.getValue());
		}
		return null;
	}
	
	@Override
	public BeanPropertyType getPropertyType() {
		return BeanPropertyType.INT;
	}
}
