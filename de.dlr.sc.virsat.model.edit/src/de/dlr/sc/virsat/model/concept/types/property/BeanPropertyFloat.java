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

import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.AProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.AQudvTypeProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.PropertyinstancesPackage;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.UnitValuePropertyInstance;
import de.dlr.sc.virsat.model.dvlm.categories.util.CategoryAssignmentHelper;
import de.dlr.sc.virsat.model.dvlm.json.DoubleAdapter;
import de.dlr.sc.virsat.model.dvlm.qudv.AUnit;
import de.dlr.sc.virsat.model.dvlm.qudv.SystemOfUnits;
import de.dlr.sc.virsat.model.dvlm.qudv.util.QudvUnitHelper;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModelProperty.AccessMode;

/**
 * Class to wrap FloatPropertyInstances
 *
 */
public class BeanPropertyFloat extends ABeanUnitValueProperty<Double> {

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
		super(uvpi);
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
	@ApiModelProperty(value = "Double")
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
	
	/**
	 * Convenience method to easily get a value in the default unit,
	 * that is the unit specified in the concept file.
	 * @return Returns a double value in the default unit
	 */
	public double getValueInDefaultUnit() {
		QudvUnitHelper unitHelper = QudvUnitHelper.getInstance();
		SystemOfUnits sou = CategoryAssignmentHelper.getSystemOfUnits(ti);

		double sourceValue = getValue();
		AUnit sourceUnit = ti.getUnit();
		String targetUnitName = ((AQudvTypeProperty) ((AProperty) ti.getType())).getUnitName();
		AUnit targetUnit = unitHelper.getUnitByName(sou, targetUnitName);
		
		return unitHelper.convertFromSourceToTargetUnit(sourceUnit, sourceValue, targetUnit);
	}

	/**
	 * Convenience method to easily get a value in a specified unit.
	 * @param targetUnit the unit the return value should be expressed in
	 * @return Returns a double value in the specified unit
	 */
	public double getValueInUnit(String targetUnitName) {
		QudvUnitHelper unitHelper = QudvUnitHelper.getInstance();
		SystemOfUnits sou = CategoryAssignmentHelper.getSystemOfUnits(ti);
		
		double sourceValue = getValue();
		AUnit sourceUnit = ti.getUnit();
		AUnit targetUnit = unitHelper.getUnitByName(sou, targetUnitName);
		
		return unitHelper.convertFromSourceToTargetUnit(sourceUnit, sourceValue, targetUnit);
	}
	
	@ApiModelProperty(
			value = "Always returns constant: \"float\"", 
			example = "float",
			accessMode = AccessMode.READ_ONLY)
	@Override
	public BeanPropertyType getPropertyType() {
		return BeanPropertyType.FLOAT;
	}
}
