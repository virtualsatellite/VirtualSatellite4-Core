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
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.edit.domain.EditingDomain;

import de.dlr.sc.virsat.model.concept.types.ABeanObject;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.UnitValuePropertyInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.util.PropertyInstanceHelper;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.util.PropertyInstanceValueSwitch;

/**
 * Abstract implementation to the interface dealing with Attributes with QUDV unit
 * @author fisc_ph
 *
 * @param <V_TYPE> The value type of this bean
 */
public abstract class ABeanUnitProperty<V_TYPE> extends ABeanObject<UnitValuePropertyInstance> implements IBeanProperty<UnitValuePropertyInstance, V_TYPE>, IBeanUnitProperty {	
	
	@Override
	public boolean isSet() {
		return ti.getValue() != null && !ti.getValue().equals("");
	}
	
	@Override
	public void unset() {
		ti.setValue(null);
	}
	
	/**
	 * Hands back the Value together with the given Unit
	 * @return the value in its base unit
	 */
	public String getValueWithUnit() {
		return new PropertyInstanceValueSwitch().getValueString(ti);
	}
	
	/**
	 * Returns value without the unit formatted as string
	 * @return formatted value without unit
	 */
	public String getFormattedValueWithoutUnit() {
		PropertyInstanceValueSwitch valueSwitch = new PropertyInstanceValueSwitch();
		valueSwitch.setShowUnitForUnitValues(false);
		return valueSwitch.getValueString(ti);
	}
	
	@Override
	@XmlElement(nillable = true)
	public String getUnit() {
		return new PropertyInstanceHelper().getUnit(ti);
	}
	
	@Override
	public boolean setUnit(String unitName) {
		return new PropertyInstanceHelper().setUnit(ti, unitName);
	}
	
	@Override
	public Command setUnit(EditingDomain ed, String unitName) {
		return new PropertyInstanceHelper().setUnit(ed, ti, unitName);
	}
	
	/**
	 * Creates a command that sets the value and unit of this bean to the value and
	 * unit to the other passed bean
	 * @param ed the editing domain
	 * @param other another bean of the same type
	 * @return a command to set value + unit to the value + unit of the other bean
	 */
	public Command setValueWithUnit(EditingDomain ed, ABeanUnitProperty<V_TYPE> other) {
		CompoundCommand cmdSetValueAndUnit = new CompoundCommand();
		Command cmdSetValue = setValue(ed, other.getValue());
		Command cmdSetUnit = setUnit(ed, other.getUnit());
		
		cmdSetValueAndUnit.append(cmdSetValue);
		cmdSetValueAndUnit.append(cmdSetUnit);
		
		return cmdSetValueAndUnit;
	}
	
	/**
	 * Sets the value and unit of this bean to the value and unit of the other bean
	 * @param other another bean of the same type
	 */
	public void setValueWithUnit(ABeanUnitProperty<V_TYPE> other) {
		setValue(other.getValue());
		setUnit(other.getUnit());
	}
}
