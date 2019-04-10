/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.ui.propertyinstance.util;

import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.util.PropertyInstanceValueSwitch;
import de.dlr.sc.virsat.model.ui.preferences.PreferencesRounding;

/**
 * Implements a factory for the property instance switch which uses the preference store to set
 * up the decimal rounding of float values
 * @author fisc_ph
 *
 */
public class PreferencedPropertyInstanceValueSwitchFactory extends PropertyInstanceValueSwitch {

	/**
	 * This method hands back a PropertyinstanceValueSwitch with the decimal rounding set to the value
	 * specified in the preference store of the UI plugin
	 * @return the switch well set up and happy
	 */
	public static PropertyInstanceValueSwitch createInstance() {
		int roundDecimals = PreferencesRounding.getPreferedRoundingDecimals();
		return new PropertyInstanceValueSwitch(roundDecimals);
	}
}
