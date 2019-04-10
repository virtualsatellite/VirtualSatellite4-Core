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

import java.util.Collection;

import org.eclipse.jface.preference.IPreferenceStore;

import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import de.dlr.sc.virsat.model.ui.Activator;
import de.dlr.sc.virsat.model.ui.editor.input.VirSatUriEditorInput;

/**
 * This class stores and handles the preferences which tell us
 * if an editor should be opened with a new SEI or CA. This class also
 * t
 * @author fisc_ph
 *
 */
public class PreferencesEditorAutoOpen {

	/**
	 * Private Constructor due to Utility Class
	 */
	private PreferencesEditorAutoOpen() {
	}
	
	public static final String PREFERENCE_FIELD_EDITOR_AUTO_OPEN_NEW_CA = "de.dlr.virsat.preference.field.autoOpen.newCa";
	public static final String PREFERENCE_FIELD_EDITOR_AUTO_OPEN_NEW_SEI = "de.dlr.virsat.preference.field.autoOpen.newSei";
	/**
	 * This method tells, if it is preferred to open a new editor when creating a new CA
	 * @return true in case it is preferred
	 */
	public static boolean isPreferedEditorAutoOpenForNewCa() {
		IPreferenceStore prefStore = Activator.getDefault().getPreferenceStore();
		boolean booleanIsAutoOpen = prefStore.getBoolean(PREFERENCE_FIELD_EDITOR_AUTO_OPEN_NEW_CA);
		return booleanIsAutoOpen;
	}
	/**
	 * This method tells, if it is preferred to open a new editor when creating a new SEI
	 * @return true in case it is preferred
	 */
	public static boolean isPreferedEditorAutoOpenForNewSei() {
		IPreferenceStore prefStore = Activator.getDefault().getPreferenceStore();
		boolean booleanIsAutoOpen = prefStore.getBoolean(PREFERENCE_FIELD_EDITOR_AUTO_OPEN_NEW_SEI);
		return booleanIsAutoOpen;
	}
	/**
	 * Call this method to try to open editors for a collection of objects. The method will check
	 * if the collection contains SEIs or CAs and tries to open the VirSat Editor for it
	 * @param objects a collection of objects with potential SEIs and CAs to be opened in the Virtual Satellite Editor
	 */
	public static void openEditorIfPreferredForCollection(Collection<?> objects) {
		// Open all SEI editors if required
		if (isPreferedEditorAutoOpenForNewSei()) {
			objects.stream().filter((obj) -> obj instanceof StructuralElementInstance).forEach((obj) -> VirSatUriEditorInput.openDrillDownEditor(obj));
		}
		
		// Open all CA Editors if required
		if (isPreferedEditorAutoOpenForNewCa()) {
			objects.stream().filter((obj) -> obj instanceof CategoryAssignment).forEach((obj) -> VirSatUriEditorInput.openDrillDownEditor(obj));
		}
	}

}
