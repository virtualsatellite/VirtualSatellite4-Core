/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.project.ui.properties.page;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.Adapters;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.jface.preference.BooleanFieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.ui.IWorkbenchPropertyPage;

import de.dlr.sc.virsat.project.ui.properties.PreferenceStoreProvider;
import de.dlr.sc.virsat.project.ui.properties.PropertyRoleManagement;

/**
 * Implements the property page for project specific settings
 */
public class VirSatProjectPropertyPage extends FieldEditorPreferencePage implements IWorkbenchPropertyPage {
	
	static final int PROPERTY_GROUP_HORIZONTAL_INTENT = 5;
	static final int PROPERTY_GROUP_VERTICAL_INTENT = 5;
	
	static final String PROPERTY_GROUP_ROLE_MANAGEMENT = "Role Management Settings";
	
	static final String PROPERTY_FIELD_ROLE_MANAGEMENT_ENABLED = "Enable Role Management";

	private IProject project;
	
	/**
	 * Constructor of the page
	 */
	public VirSatProjectPropertyPage() {
		super(GRID);
	}
	
	@Override
	public IAdaptable getElement() {
		return project;
	}

	@Override
	public void setElement(IAdaptable element) {
		project = Adapters.adapt(element, IProject.class);
	}
	
	@Override
	protected void createFieldEditors() {
		createRoleManagementFieldEditor();
	}
	
	@Override
	protected IPreferenceStore doGetPreferenceStore() {
		return PreferenceStoreProvider.getScopedPreferenceStore();
	}
	
	/**
	 * Create field editors for role management settings
	 */
	private void createRoleManagementFieldEditor() {		
		Composite parent = createGroup(getFieldEditorParent(), PROPERTY_GROUP_ROLE_MANAGEMENT);
		addField(new BooleanFieldEditor(PropertyRoleManagement.PROPERTY_FIELD_ROLE_MANAGEMENT_ENABLED, PROPERTY_FIELD_ROLE_MANAGEMENT_ENABLED, parent));
	}
	
	/**
	 * Create a group composite
	 * @param parent Parent composite
	 * @param label Label for the group
	 * @return Group composite
	 */
	private Group createGroup(Composite parent, String label) {
		Group group = new Group(parent, SWT.NONE);
		group.setFont(parent.getFont());
		if (label != null) {
			group.setText(label);
		}
		
		GridLayout layout = new GridLayout();
		group.setLayout(layout);
		
		GridData data = new GridData(GridData.FILL, GridData.BEGINNING, true, false);
		data.horizontalIndent = PROPERTY_GROUP_HORIZONTAL_INTENT;
		data.verticalIndent = PROPERTY_GROUP_VERTICAL_INTENT;
		group.setLayoutData(data);

		return group;
	}
}

