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
import java.util.stream.Collectors;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.rmf.reqif10.SpecObjectType;
import org.eclipse.rmf.reqif10.SpecType;
import org.eclipse.rmf.reqif10.impl.ReqIFImpl;
import org.eclipse.swt.SWT;
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

public class ReqIfTypeSelectionPage extends WizardPage implements SelectionListener {
	
	private static final String PAGE_TITLE = "Requirement Type Selection";
	
	private static final String COLUMN_REQUIREMENT_TYPES_LABEL = "Requirement Types";
	private static final String TYPE_CONTAINER_LABEL = "Requirement Type Container:";
	private static final String TYPE_CONTAINER_DEFAULT_TEXT = "Create New Container";
	private static final String DESCRIPTION_LABEL = "Check the requirement types to be imported and their container element in the system model.";
	
	private Combo combo;
	private static final int COMBO_TYPE_WIDTH = 300;
	
	private static final int COLUMN_WIDTH = 700;
	
	private Table table;
	private List<TableItem> tableItems = new ArrayList<TableItem>();
	
	private ReqIFImpl reqIfContent;
	private Repository repository;
	
	
	public ReqIfTypeSelectionPage() {
		super(PAGE_TITLE);
		setTitle(PAGE_TITLE);
		setDescription(DESCRIPTION_LABEL);
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
		columnSpecification.setWidth(COLUMN_WIDTH);
		columnSpecification.setText(COLUMN_REQUIREMENT_TYPES_LABEL);

	}
	
	/**
	 * Hand over reqIF content to this page
	 * @param reqIFImpl the reqIF model
	 * @param projectRepo the project's repository to get potential SEI containers from
	 */
	public void setInput(ReqIFImpl reqIFImpl, Repository projectRepo) {
		this.reqIfContent = reqIFImpl;
		this.repository = projectRepo;
		
		table.removeAll();
		tableItems.clear();
		
		for (SpecType type : reqIfContent.getCoreContent().getSpecTypes().stream().
					filter(type -> type instanceof SpecObjectType).collect(Collectors.toList())) {
			String typeName = type.getLongName();
			TableItem item = new TableItem(table, SWT.NULL);
			tableItems.add(item);
			item.setText(typeName);
		}
		
		for (StructuralElementInstance rootSei : projectRepo.getRootEntities()) {
			if (rootSei.getType().getFullQualifiedName().equals(RequirementsConfigurationCollection.FULL_QUALIFIED_STRUCTURAL_ELEMENT_NAME)) {
				RequirementsConfigurationCollection rcc = new RequirementsConfigurationCollection(rootSei);
				for (RequirementsConfiguration config : rcc.getAll(RequirementsConfiguration.class)) {
					combo.add(config.getATypeInstance().getFullQualifiedInstanceName());
				}
			}
		}
		updateCompleteState();
	}
	
	/**
	 * Update complete status of this page
	 */
	public void updateCompleteState() {
		boolean atLeastOneSelected = false;
		for (TableItem item : tableItems) {
			if (item.getChecked()) {
				atLeastOneSelected = true;
			}
		}
		setPageComplete(atLeastOneSelected);
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
	
	/**
	 * Return the list of selected requirement types for import
	 * @return the list of identifiers of the requirement types
	 */
	public List<String> getListOfRequirementTypeKeys() {
		List<String> typeList = new ArrayList<String>();
		for (TableItem item : tableItems) {
			if (item.getChecked()) {
				typeList.add(item.getText());
			}
		}
		return typeList;
	}

	@Override
	public void widgetSelected(SelectionEvent e) {
		updateCompleteState();
	}

	@Override
	public void widgetDefaultSelected(SelectionEvent e) {
	}

}
