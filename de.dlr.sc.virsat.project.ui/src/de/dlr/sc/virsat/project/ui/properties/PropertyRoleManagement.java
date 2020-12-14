/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.project.ui.properties;

import org.eclipse.jface.preference.IPreferenceStore;

/**
 * This class stores and handles the preferences regarding the role management
 *
 */
public class PropertyRoleManagement {

	/**
	 * Private Constructor due to Utility Class
	 */
	private PropertyRoleManagement() {
	}
	
	public static final String PROPERTY_FIELD_ROLE_MANAGEMENT_ENABLED = "de.dlr.virsat.preference.role.management.enabled";
	
	/**
	 * This method tells, if the role management is enabled in the context
	 * @param context Context object
	 * @return True, if role management is enabled
	 */
	public static boolean isManagingRoles() {
		IPreferenceStore prefStore = PreferenceStoreProvider.getScopedPreferenceStore();
		return prefStore.getBoolean(PROPERTY_FIELD_ROLE_MANAGEMENT_ENABLED);
	}	

}
