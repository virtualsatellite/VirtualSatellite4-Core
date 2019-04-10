/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.provider;

import org.eclipse.emf.common.notify.AdapterFactory;

import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.EnumValueDefinition;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.util.PropertyInstanceValueSwitch;

/**
 * Overrides the getText method for value property instances
 * @author muel_s8
 *
 */
public class DVLMEnumValueDefinitionItemProvider extends EnumValueDefinitionItemProvider {

	/**
	 * this class constructor is an instance of the factory and notifier 
	 * @param adapterFactory 
	 */
	public DVLMEnumValueDefinitionItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}
	
	
	@Override
	public String getText(Object object) {
		if (object instanceof EnumValueDefinition) {
			EnumValueDefinition evd = (EnumValueDefinition) object;
			return  PropertyInstanceValueSwitch.getEnumValueDefinitionString(evd);
		}
		return super.getText(object);
	}

}
