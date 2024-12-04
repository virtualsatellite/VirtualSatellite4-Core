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

import jakarta.xml.bind.annotation.XmlElement;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.EditingDomain;

import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.PropertyinstancesPackage;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.UnitValuePropertyInstance;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.AccessMode;


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
	@Schema(description = "Long")
	public Long getValue() throws NumberFormatException {
		if (isSet()) {
			return Long.parseLong(ti.getValue());
		}
		return null;
	}
	
	
	@XmlElement(nillable = true)
	@Schema(description = "Long")
	/**
	 * Convenience Method to directly convert the value to its base unit
	 * @return the value in its base unit
	 */
	public int getValueInBaseUnit() {
		return getValueToBaseUnit();
	}
	
	/**
	 * Convenience method to easily set a value in the frame of base unit and have it
	 * converted to the currently set unit.
	 * @param inputValue the value to be set in base unit frame
	 */
	public void setValueInBaseUnit(int inputValue) {
		setValueAsBaseUnit(inputValue);
	}

	/**
	 * Convenience Method to directly convert the value to its base unit
	 * @return the value in its base unit
	 */
	public int getValueToBaseUnit() {
		return (int) ti.getValueToBaseUnit();
	}

	/**
	 * Convenience method to easily set a value in the frame of base unit and have it
	 * converted to the currently set unit.
	 * @param inputValue the value to be set in base unit frame
	 */
	public void setValueAsBaseUnit(int inputValue) {
		ti.setValueAsBaseUnit(inputValue);		
	}
	
	@Schema(
			description = "Always returns constant: \"int\"", 
			example = "int",
			accessMode = AccessMode.READ_ONLY)
	@Override
	public BeanPropertyType getPropertyType() {
		return BeanPropertyType.INT;
	}
}
