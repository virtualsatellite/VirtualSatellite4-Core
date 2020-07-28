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

import de.dlr.sc.virsat.model.concept.types.ABeanObject;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.EnumProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.EnumPropertyHelper;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.EnumValueDefinition;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.EnumUnitPropertyInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.PropertyinstancesPackage;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.util.PropertyInstanceHelper;
import de.dlr.sc.virsat.model.dvlm.qudv.AUnit;
import de.dlr.sc.virsat.model.dvlm.qudv.util.QudvUnitHelper;

/**
 * Class to wrap FloatPropertyInstances
 * @author fisc_ph
 *
 */
public class BeanPropertyEnum extends ABeanObject<EnumUnitPropertyInstance> implements IBeanProperty<EnumUnitPropertyInstance, String>, IBeanUnitProperty {

	/**
	 * standard Constructor
	 */
	public BeanPropertyEnum() {
	}
	
	/**
	 * Constructor to directly set the type instance
	 * @param eupi the type instance to be used
	 */
	public BeanPropertyEnum(EnumUnitPropertyInstance eupi) {
		setTypeInstance(eupi);
	}
	
	@Override
	public Command setValue(EditingDomain ed, String value) {
		EnumProperty ep = (EnumProperty) getTypeInstance().getType();
		EnumValueDefinition evd = new EnumPropertyHelper().getEvdForName(ep, value);
		return SetCommand.create(ed, ti, PropertyinstancesPackage.Literals.ENUM_UNIT_PROPERTY_INSTANCE__VALUE, evd);
	}
	
	@Override
	@XmlElement(nillable = true)
	public void setValue(String value) {
		EnumProperty ep = (EnumProperty) getTypeInstance().getType();
		EnumValueDefinition evd = new EnumPropertyHelper().getEvdForName(ep, value);
		ti.setValue(evd);
	}

	@Override
	@XmlElement(nillable = true)
	public String getValue() {
		if (isSet()) {
			return getTypeInstance().getValue().getName();
		}
		return null;
	}
	
	@Override
	public boolean isSet() {
		return getTypeInstance().getValue() != null;
	}
	
	@Override
	public void unset() {
		getTypeInstance().setValue(null);
	}
	
	/**
	 * this method returns the bean property float value
	 * @return the bean property float value
	 */
	public double getEnumValue() {
		return Double.parseDouble(getTypeInstance().getValue().getValue());
	}
	
	/**
	 * Convenience Method to directly convert the value to its base unit
	 * @return the value in its base unit
	 */
	public double getEnumValueToBaseUnit() {
		double value = getEnumValue();
		AUnit unit = getTypeInstance().getUnit();
		if (unit != null) {
			value = QudvUnitHelper.getInstance().convertFromSourceUnitToBaseUnit(unit, value);
		}
				
		return value;
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
}
