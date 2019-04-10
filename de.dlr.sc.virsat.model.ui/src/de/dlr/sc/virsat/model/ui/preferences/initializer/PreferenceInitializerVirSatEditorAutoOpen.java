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

import de.dlr.sc.virsat.model.ui.Activator;
import de.dlr.sc.virsat.model.ui.preferences.PreferencesEditorAutoOpen;

/**
 * Implements the initialization values for VirSat Auto-Opening of Editors.
 * @author fisc_ph
 *
 */
public class PreferenceInitializerVirSatEditorAutoOpen extends AbstractPreferenceInitializer {

	public static final boolean PREFERENCE_FIELD_EDITOR_AUTO_OPEN_NEW_SEI = false;
	public static final boolean PREFERENCE_FIELD_EDITOR_AUTO_OPEN_NEW_CA = false;
	
	@Override
	public void initializeDefaultPreferences() {
		IEclipsePreferences node = DefaultScope.INSTANCE.getNode(Activator.getPluginId());
		node.putBoolean(PreferencesEditorAutoOpen.PREFERENCE_FIELD_EDITOR_AUTO_OPEN_NEW_SEI, PREFERENCE_FIELD_EDITOR_AUTO_OPEN_NEW_SEI);
		node.putBoolean(PreferencesEditorAutoOpen.PREFERENCE_FIELD_EDITOR_AUTO_OPEN_NEW_CA, PREFERENCE_FIELD_EDITOR_AUTO_OPEN_NEW_CA);
	}
}
