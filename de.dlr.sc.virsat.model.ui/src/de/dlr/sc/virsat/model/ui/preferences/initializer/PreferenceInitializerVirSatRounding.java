/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.ui.preferences.initializer;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.core.runtime.preferences.DefaultScope;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;

import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.util.PropertyInstanceValueSwitch;
import de.dlr.sc.virsat.model.ui.Activator;
import de.dlr.sc.virsat.model.ui.preferences.PreferencesRounding;

/**
 * Implements the initialization values for VirSat Rounding of displayed floats for the preference store.
 * @author fisc_ph
 *
 */
public class PreferenceInitializerVirSatRounding extends AbstractPreferenceInitializer {

	public static final int PREFERENCE_FIELD_ROUNDING_INIT = PropertyInstanceValueSwitch.PREFERENCE_ROUNDING_INIT;
	
	@Override
	public void initializeDefaultPreferences() {
		IEclipsePreferences node = DefaultScope.INSTANCE.getNode(Activator.getPluginId());
		node.putInt(PreferencesRounding.PREFERENCE_FIELD_ROUNDING, PREFERENCE_FIELD_ROUNDING_INIT);
	}
}
