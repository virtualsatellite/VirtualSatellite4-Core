/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.provider;

import org.eclipse.emf.common.notify.AdapterFactory;

import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.EnumUnitPropertyInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.util.PropertyInstanceValueSwitch;

/**
 * Overrides the getText method for value property instances
 * @author muel_s8
 *
 */
public class DVLMEnumUnitPropertyInstanceItemProvider extends EnumUnitPropertyInstanceItemProvider {

	/**
	 * this class constructor is an instance of the factory and notifier 
	 * @param adapterFactory 
	 */
	public DVLMEnumUnitPropertyInstanceItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}
	
	@Override
	public String getText(Object object) {
		
		if (object instanceof EnumUnitPropertyInstance) {
			EnumUnitPropertyInstance eupi = (EnumUnitPropertyInstance) object;
			String value = eupi.getValue() == null ? "" : PropertyInstanceValueSwitch.getEnumValueDefinitionString(eupi.getValue());
			return eupi.getType().getName() + ": " + value;
		}
		
		return super.getText(object);
	}

}
