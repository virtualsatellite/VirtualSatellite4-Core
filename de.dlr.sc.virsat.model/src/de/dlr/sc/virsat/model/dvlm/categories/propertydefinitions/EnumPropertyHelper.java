/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions;

import java.util.ArrayList;
import java.util.List;

import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.util.PropertyInstanceValueSwitch;

//import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.util.PropertyInstanceValueSwitch;

/**
 * This class contains method to simplify access
 * to ENUMS and their values
 * @author fisc_ph
 *
 */
public class EnumPropertyHelper {
	
	/**
	 * This method hands back all textual values belonging to a given ENUM property
	 * @param ep the EnumProperty of which to get all possible values from
	 * @param showEmptyValue 
	 * @return a List of string with all possible values
	 */
	public List<String> getEnumValueDefinitionStrings(EnumProperty ep, boolean showEmptyValue) {
		List<String> values = new ArrayList<>();
		
		if (showEmptyValue) {
			values.add("");
		}
		
		ep.getValues().forEach((evd) -> {
			values.add(PropertyInstanceValueSwitch.getEnumValueDefinitionString(evd));
		});
		
		return values;
	}
	
	/**
	 * This method hands back all textual values belonging to a given ENUM property
	 * @param ep the EnumProperty of which to get all possible values from
	 * @param showEmptyValue 
	 * @return a List of string with all possible values
	 */
	public List<String> getEnumValues(EnumProperty ep, boolean showEmptyValue) {
		List<String> values = new ArrayList<>();
		
		if (showEmptyValue) {
			values.add("");
		}
		
		ep.getValues().forEach((evd) -> {
			values.add(evd.getValue());
		});
		
		return values;
	}
	
	/**
	 * This method hands back all textual names belonging to a given ENUM property
	 * @param ep the EnumProperty of which to get all possible names from
	 * @param showEmptyValue 
	 * @return a List of string with all possible names
	 */
	public List<String> getEnumNames(EnumProperty ep, boolean showEmptyValue) {
		List<String> values = new ArrayList<>();
		
		if (showEmptyValue) {
			values.add("");
		}
		
		ep.getValues().forEach((evd) -> {
			values.add(evd.getName());
		});
		
		return values;
	}
	
	/**
	 * This method hands back the EVD  for a given Name
	 * @param ep The EnumProeprty in which to look for the given name
	 * @param name the name of the ENUM Value to look for
	 * @return The EnumValueDefinition with the name searched for or null in case it could not be found
	 */
	public EnumValueDefinition getEvdForName(EnumProperty ep, String name) {
		for (EnumValueDefinition evd : ep.getValues()) {
			if (evd.getName().equalsIgnoreCase(name)) {
				return evd;
			}
		}
		return null;
	}
	
	private static final String EENUM_NAME_PREFIX = "Enum";

	/**
	 * Use this method to get the Enum Name in a DMF for an Enum property
	 * @param ep The EnumProperty for which to get the EEnum Name
	 * @return the Name of the EEnum
	 */
	public String getDmfEEnumName(EnumProperty ep) {
		String epName = ep.getName();
		String epNameFirstLetter = epName.substring(0, 1).toUpperCase();
		String epNameFirstUpper = epNameFirstLetter + epName.substring(1);
		return EENUM_NAME_PREFIX + epNameFirstUpper;
	}
}
