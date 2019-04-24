/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.ui.preferences;

import org.eclipse.jface.preference.IPreferenceStore;

import de.dlr.sc.virsat.model.ui.Activator;

/**
 * This class stores and handles the preferences which tell us
 * how we want to have rounding to be displayed, e.g the amount of 
 * digits after the comma.
 * @author fisc_ph
 *
 */
public class PreferencesRounding {

	/**
	 * Private Constructor due to Utility Class
	 */
	private PreferencesRounding() {
	}
		
	public static final String PREFERENCE_FIELD_ROUNDING = "de.dlr.virsat.preference.field.rounding";

	/**
	 * This method tells how many decimals are set in the rounding for Virtual Satellite
	 * @return an int value defining the number of digits behind the comma
	 */
	public static int getPreferedRoundingDecimals() {
		IPreferenceStore prefStore = Activator.getDefault().getPreferenceStore();
		int roundDecimals = prefStore.getInt(PREFERENCE_FIELD_ROUNDING);
		return roundDecimals;
	}


}
