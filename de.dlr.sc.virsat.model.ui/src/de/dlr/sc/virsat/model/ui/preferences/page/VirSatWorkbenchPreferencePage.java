/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.ui.preferences.page;

import org.eclipse.jface.preference.BooleanFieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.IntegerFieldEditor;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import de.dlr.sc.virsat.model.ui.Activator;
import de.dlr.sc.virsat.model.ui.preferences.PreferencesEditorAutoOpen;
import de.dlr.sc.virsat.model.ui.preferences.PreferencesRounding;

/**
 * Implements the preference page for common VirSat settings such as the rounding of displayed floats
 * @author fisc_ph
 *
 */
public class VirSatWorkbenchPreferencePage extends FieldEditorPreferencePage implements IWorkbenchPreferencePage {

	public static final int PREFERENCE_FIELD_ROUNDING_LIMIT = 2;
	public static final String PREFERENCE_FIELD_ROUNDING_NAME = "Displayed Value Rounding (decimals) :";
	public static final String PREFERENCE_FIELD_EDITOR_AUTO_OPEN_NEW_CA_NAME = "Automatically open Editor when creating a CategoryAssignment (CA).";
	public static final String PREFERENCE_FIELD_EDITOR_AUTO_OPEN_NEW_SEI_NAME = "Automatically open Editor when creating a StructuraylElementInstance (SEI).";

	/**
	 * Constructor of the page
	 */
	public VirSatWorkbenchPreferencePage() {
		super(GRID);
	}
	
	@Override
	public void init(IWorkbench workbench) {
		setPreferenceStore(Activator.getDefault().getPreferenceStore());
	}

	@Override
	protected void createFieldEditors() {
		addField(new BooleanFieldEditor(PreferencesEditorAutoOpen.PREFERENCE_FIELD_EDITOR_AUTO_OPEN_NEW_SEI, PREFERENCE_FIELD_EDITOR_AUTO_OPEN_NEW_SEI_NAME, getFieldEditorParent()));
		addField(new BooleanFieldEditor(PreferencesEditorAutoOpen.PREFERENCE_FIELD_EDITOR_AUTO_OPEN_NEW_CA, PREFERENCE_FIELD_EDITOR_AUTO_OPEN_NEW_CA_NAME, getFieldEditorParent()));
		addField(new IntegerFieldEditor(PreferencesRounding.PREFERENCE_FIELD_ROUNDING, PREFERENCE_FIELD_ROUNDING_NAME, getFieldEditorParent(), PREFERENCE_FIELD_ROUNDING_LIMIT));
	}
}
