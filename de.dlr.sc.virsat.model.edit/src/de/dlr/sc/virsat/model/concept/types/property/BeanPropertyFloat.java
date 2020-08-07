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
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.EditingDomain;

import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.PropertyinstancesPackage;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.UnitValuePropertyInstance;
import de.dlr.sc.virsat.model.dvlm.json.DoubleAdapter;
import de.dlr.sc.virsat.model.dvlm.qudv.AUnit;
import de.dlr.sc.virsat.model.dvlm.qudv.util.QudvUnitHelper;

/**
 * Class to wrap FloatPropertyInstances
 * @author fisc_ph
 *
 */
public class BeanPropertyFloat extends ABeanUnitProperty<Double> {

	/**
	 * Standard Constructor
	 */
	public BeanPropertyFloat() {
	}
	
	/**
	 * Constructor to directly set the type instance
	 * @param uvpi the type instance to be used
	 */
	public BeanPropertyFloat(UnitValuePropertyInstance uvpi) {
		setTypeInstance(uvpi);
	}
	
	@Override
	public Command setValue(EditingDomain ed, Double value) {
		return SetCommand.create(ed, ti, PropertyinstancesPackage.Literals.VALUE_PROPERTY_INSTANCE__VALUE, Double.toString(value));
	}
	
	@Override
	public void setValue(Double value) {
		if (value == null) {
			ti.setValue(null);
		} else {
			ti.setValue(Double.toString(value));
		}
	}
	
	@XmlJavaTypeAdapter(DoubleAdapter.class)
	@XmlElement(nillable = true)
	@Override
	public Double getValue() {
		try {
			String stringValue = isSet() ? ti.getValue() : "";
			return Double.parseDouble(stringValue);
		} catch (NumberFormatException e) {
			return Double.NaN;
		}
	}
	
	/**
	 * Convenience Method to directly convert the value to its base unit
	 * @return the value in its base unit
	 */
	public double getValueToBaseUnit() {
		return ti.getValueToBaseUnit();
	}

	/**
	 * Convenience method to easily set a value in the frame of base unit and have it
	 * converted to the currently set unit.
	 * @param inputValue the value to be set in base unit frame
	 */
	public void setValueAsBaseUnit(double inputValue) {
		ti.setValueAsBaseUnit(inputValue);		
	}
	
	/**
	 * Convenience method to easily set a value in the frame of base unit and have it
	 * converted to the currently set unit.
	 * @param ed the editing domain in which to execute the command
	 * @param inputValue the value to be set in base unit frame
	 * @return Returns a command to set the value
	 */
	public Command setValueAsBaseUnit(EditingDomain ed, double inputValue) {
		AUnit unit = ti.getUnit();
		if (unit != null) {
			double convertedValue = QudvUnitHelper.getInstance().convertFromBaseUnitToTargetUnit(unit, inputValue);
			return setValue(ed, convertedValue);		
		}
		return setValue(ed, inputValue);		
	}
}
