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
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;

import de.dlr.sc.virsat.model.extension.requirements.model.RequirementAttribute;
import de.dlr.sc.virsat.model.extension.requirements.model.RequirementType;

/**
 * A wizard page to review the requirement type which is used for imports
 *
 */
public class CsvTypeReviewPage extends WizardPage implements SelectionListener, ModifyListener {

	private static final String PAGE_TITLE = "Requirement Type Review";
	
	private static final String COLUMN_NAME_LABEL = "Column";
	private static final String COLUMN_TYPE_LABEL = "Type";

	private static final String TYPE_NAME_LABEL = "Type Name";
	private static final int WITH_TEXT = 400;

	private static final int COLUMN_NAME_WIDTH = 300;
	private static final int COLUMN_TYPE_WIDTH = 200;

	private static final List<String> ATTRIBUTE_TYPES = new ArrayList<String>(
			Arrays.asList(RequirementAttribute.TYPE_Boolean_NAME, RequirementAttribute.TYPE_Date_NAME,
					RequirementAttribute.TYPE_Enumeration_NAME, RequirementAttribute.TYPE_Identifier_NAME,
					RequirementAttribute.TYPE_Integer_NAME, RequirementAttribute.TYPE_Real_NAME,
					RequirementAttribute.TYPE_String_NAME));

	private static final int TYPE_COLUMN_INDEX = 1;
	private static final String TYPE_LABEL_REQUIREMENT_ATTRIBUTE_SEPARATOR = "::";

	private Text typeNameText;

	private Table table;
	private List<TableItem> tableItems = new ArrayList<TableItem>();
	private List<CCombo> editors = new ArrayList<CCombo>();

	private Map<Integer, RequirementAttribute> mapColumnIndexToAttributeType = new HashMap<>();
	private RequirementType requirementType;
	private boolean isNewType;

	/**
	 * Constructor
	 */
	protected CsvTypeReviewPage() {
		super(PAGE_TITLE);
		setTitle(PAGE_TITLE);
		setDescription("Check the selected import type for the requirements.");
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
		label.setText(TYPE_NAME_LABEL);

		typeNameText = new Text(typeNameComposite, SWT.SINGLE | SWT.BORDER);
		GridData dataText = new GridData(GridData.HORIZONTAL_ALIGN_FILL | GridData.GRAB_HORIZONTAL);
		data.horizontalAlignment = SWT.END;
		data.widthHint = WITH_TEXT;
		typeNameText.setLayoutData(dataText);
		typeNameText.addSelectionListener(this);
		typeNameText.addModifyListener(this);

		table = new Table(content, SWT.CHECK | SWT.BORDER | SWT.H_SCROLL);
		table.setLayout(new GridLayout());
		table.setLayoutData(new GridData(GridData.FILL_BOTH | GridData.GRAB_HORIZONTAL | GridData.GRAB_VERTICAL));
		table.setHeaderVisible(true);
		table.addSelectionListener(this);

		TableColumn columnColumnName = new TableColumn(table, SWT.NULL);
		columnColumnName.setWidth(COLUMN_NAME_WIDTH);
		columnColumnName.setText(COLUMN_NAME_LABEL);

		TableColumn columnType = new TableColumn(table, SWT.NONE | SWT.DROP_DOWN);
		columnType.setText(COLUMN_TYPE_LABEL);
		columnType.setWidth(COLUMN_TYPE_WIDTH);

	}

	/**
	 * Sets the input for the mapping table
	 * 
	 * @param columnNames
	 *            a list of columns in the file
	 * @param requirementType
	 *            an existing requirement type
	 */
	public void setInput(List<String> columnNames, RequirementType requirementType) {
		this.requirementType = requirementType;
		this.typeNameText.setText(requirementType.getName());
		isNewType = requirementType.getTypeInstance().eContainer() == null;
		typeNameText.setEditable(isNewType);

		table.clearAll();

		for (int index = 0; index < columnNames.size(); index++) {
			String column = columnNames.get(index);
			TableItem item;
			if (tableItems.size() <= index) {
				item = new TableItem(table, SWT.NULL);
				tableItems.add(item);
			} else {
				item = tableItems.get(index);
			}
			item.setText(0, column);
			if (index < requirementType.getAttributes().size()) {
				if (isNewType) {
					item.setText(TYPE_COLUMN_INDEX, requirementType.getAttributes().get(index).getType());
					item.setChecked(true);
					createDropDownEditor(item, ATTRIBUTE_TYPES);
					
				} else {
					item.setText(TYPE_COLUMN_INDEX, getAttributeTableLabel(requirementType, requirementType.getAttributes().get(index)));
					item.setChecked(true);
					
					// If we use an exiting type then just try to map them from the attribute index
					createDropDownEditor(item, requirementType.getAttributes().stream().map(
							att -> getAttributeTableLabel(requirementType, att))
							.collect(Collectors.toList()));
				}
			} else {
				item.setChecked(false);
				item.setText(TYPE_COLUMN_INDEX, "");
			}
		}
	}

	/**
	 * Method to create a drop down editor for the type column
	 * 
	 * @param item
	 *            the item for which the editor should be created
	 * @param options
	 *            the input options
	 */
	private void createDropDownEditor(TableItem item, List<String> options) {
		int index = tableItems.indexOf(item);
		CCombo typeDropdown;
		if (editors.size() > index) {
			typeDropdown = editors.get(index);
			typeDropdown.removeAll();
		} else {
			typeDropdown = new CCombo(table, SWT.NONE);
			editors.add(typeDropdown);
		}
		typeDropdown.setText(item.getText(TYPE_COLUMN_INDEX));
		for (String option : options) {
			typeDropdown.add(option);
		}
		final TableEditor editor = new TableEditor(table);
		editor.grabHorizontal = true;
		editor.setEditor(typeDropdown, item, TYPE_COLUMN_INDEX);
	}

	/**
	 * Get the mapping of columns to their attribute in specified requirement type
	 * 
	 * @return a map of the column index to their attribute definition type
	 */
	public Map<Integer, RequirementAttribute> getAttributeMapping() {

		for (int index = 0; index < tableItems.size(); index++) {
			TableItem item = tableItems.get(index);
			if (item.getChecked()) {
				if (isNewType) {
					String selectedType = editors.get(index).getText();

					RequirementAttribute correspondingAtt = requirementType.getAttributes().get(index);

					// Check if type is new and valid and then add it to the new requirement type
					if (ATTRIBUTE_TYPES.contains(selectedType) && !selectedType.equals(correspondingAtt.getType())) {
						correspondingAtt.setType(selectedType);
					}

					// Add corresponding attribute to mapping
					mapColumnIndexToAttributeType.put(index, correspondingAtt);

				} else {
					String inputString = editors.get(index).getText();
					
					RequirementAttribute selectedAttribute = getSelectedAttribute(inputString);
					if (selectedAttribute != null) {
						mapColumnIndexToAttributeType.put(index, selectedAttribute);
					}

				}
			} 

		}

		return mapColumnIndexToAttributeType;
	}

	/**
	 * Get the configured requirement type for the import
	 * 
	 * @return the requirement type
	 */
	public RequirementType getRequirementType() {
		if (isNewType) {
			for (RequirementAttribute att : requirementType.getAttributes()) {
				if (!mapColumnIndexToAttributeType.containsValue(att)) {
					requirementType.getAttributes().remove(att);
				}
			}
		}
		return requirementType;
	}

	/**
	 * Parse the attribute from the editor input string
	 * 
	 * @param inputString
	 *            the editor input string
	 * @return the attibute
	 */
	private RequirementAttribute getSelectedAttribute(String inputString) {

		// Remove context indication from attribute
		inputString = inputString.replace(requirementType.getName() + TYPE_LABEL_REQUIREMENT_ATTRIBUTE_SEPARATOR, "");

		for (RequirementAttribute att : requirementType.getAttributes()) {

			if (att.getName().equals(inputString)) {
				return att;
			}

		}

		return null;
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

	@Override
	public void modifyText(ModifyEvent e) {
		if (isNewType && e.getSource().equals(typeNameText)) {
			requirementType.setName(typeNameText.getText());
		} 
	}
	
	protected String getAttributeTableLabel(RequirementType reqType, RequirementAttribute attribute) {
		return reqType.getName() + TYPE_LABEL_REQUIREMENT_ATTRIBUTE_SEPARATOR + attribute.getName();
	}

}
