/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.requirements.ui.wizard;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

import de.dlr.sc.virsat.model.dvlm.Repository;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import de.dlr.sc.virsat.model.extension.requirements.model.RequirementsConfiguration;
import de.dlr.sc.virsat.model.extension.requirements.model.RequirementsConfigurationCollection;

/**
 * A wizard page to review the requirement type which is used for imports
 *
 */
public class DoorsMappingPage extends WizardPage implements SelectionListener {

	private static final String PAGE_TITLE = "Doors Mapping";
	private static final String COLUMN_SPECIFICATION_LABEL = "Specification";
	private static final String COLUMN_CONTAINER_LABEL = "Container";
	private static final int COMBO_TYPE_WIDTH = 500;
	private static final int COLUMN_SPECIFICATION_WIDTH = 300;
	private static final int COLUMN_CONTAINER_WIDTH = 300;
	private static final String TYPE_CONTAINER_LABEL = "Requirement Type Container:";
	private static final String TYPE_CONTAINER_DEFAULT_TEXT = "Create New Container";

	private Table table;
	private List<TableItem> tableItems = new ArrayList<TableItem>();
	private List<CCombo> editors = new ArrayList<CCombo>();
	private Combo combo;
	private Repository repository;

	/**
	 * Constructor
	 */
	protected DoorsMappingPage() {
		super(PAGE_TITLE);
		setTitle(PAGE_TITLE);
		setDescription("Check the specification elements to import and their container element in the system model.");
	}

	@Override
	public void createControl(Composite parent) {
		Composite content = new Composite(parent, SWT.NONE);
		content.setLayout(new GridLayout());
		content.setLayoutData(new GridData(GridData.FILL_BOTH | GridData.GRAB_HORIZONTAL | GridData.GRAB_HORIZONTAL));
		setControl(content);
		
		Composite typeNameComposite = new Composite(content, SWT.NONE);
		typeNameComposite.setLayout(new GridLayout(2, false));
		GridData data = new GridData(GridData.HORIZONTAL_ALIGN_FILL | GridData.GRAB_HORIZONTAL);
		typeNameComposite.setLayoutData(data);

		Label label = new Label(typeNameComposite, SWT.NONE);
		label.setText(TYPE_CONTAINER_LABEL);
		
		combo = new Combo(typeNameComposite, SWT.NONE);
		GridData comboData = new GridData(GridData.HORIZONTAL_ALIGN_FILL | GridData.GRAB_HORIZONTAL);
		comboData.horizontalAlignment = SWT.END;
		comboData.minimumWidth = COMBO_TYPE_WIDTH;
		combo.setLayoutData(comboData);
		combo.setText(TYPE_CONTAINER_DEFAULT_TEXT);

		table = new Table(content, SWT.CHECK | SWT.BORDER | SWT.H_SCROLL);
		table.setLayout(new GridLayout());
		table.setLayoutData(new GridData(GridData.FILL_BOTH | GridData.GRAB_HORIZONTAL | GridData.GRAB_VERTICAL));
		table.setHeaderVisible(true);
		table.addSelectionListener(this);

		TableColumn columnSpecification = new TableColumn(table, SWT.NULL);
		columnSpecification.setWidth(COLUMN_SPECIFICATION_WIDTH);
		columnSpecification.setText(COLUMN_SPECIFICATION_LABEL);

		TableColumn columnContainer = new TableColumn(table, SWT.NONE | SWT.DROP_DOWN);
		columnContainer.setText(COLUMN_CONTAINER_LABEL);
		columnContainer.setWidth(COLUMN_CONTAINER_WIDTH);
	}
	
	@Override
	public boolean isPageComplete() {
		for (TableItem item : tableItems) {
			if (item.getChecked() && item.getText(1).equals("")) {
				return false;
			}
		}
		return isCurrentPage();
	}
	
	/**
	 * Clean table items if they have been used already
	 */
	protected void cleanTable() {
		for (TableItem item : tableItems) {
			item.setText("");
			item.setText(1, "");
			item.setChecked(false);
		}
	}
	
	@Override
	public void widgetSelected(SelectionEvent e) {
		if (e.getSource().equals(table) && e.detail == SWT.CHECK) {
			TableItem item = (TableItem) e.item;
			CCombo editor = editors.get(tableItems.indexOf(item));
			editor.setEditable(item.getChecked());
		}
	}

	@Override
	public void widgetDefaultSelected(SelectionEvent e) {
		
	}

	/**
	 * Get the selected requirements type container or null if a new one should be created
	 * @return the configuration element or null
	 */
	public RequirementsConfiguration getRequirementTypeContainer() {
		for (StructuralElementInstance rootSei : repository.getRootEntities()) {
			if (rootSei.getType().getFullQualifiedName().equals(RequirementsConfigurationCollection.FULL_QUALIFIED_STRUCTURAL_ELEMENT_NAME)) {
				RequirementsConfigurationCollection rcc = new RequirementsConfigurationCollection(rootSei);
				for (RequirementsConfiguration config : rcc.getAll(RequirementsConfiguration.class)) {
					if (config.getATypeInstance().getFullQualifiedInstanceName().equals(combo.getText())) {
						return config;
					}
				}
			}
		}
		return null;
	}
}