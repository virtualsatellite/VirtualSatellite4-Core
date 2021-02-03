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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.EList;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.rmf.reqif10.Specification;
import org.eclipse.rmf.reqif10.impl.ReqIFImpl;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

import de.dlr.sc.virsat.model.dvlm.Repository;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;

/**
 * A wizard page to review the requirement type which is used for imports
 *
 */
public class ReqIfMappingPage extends WizardPage implements SelectionListener {

	private static final String PAGE_TITLE = "ReqIF Mapping";
	
	private static final String COLUMN_SPECIFICATION_LABEL = "Specification";
	private static final String COLUMN_CONTAINER_LABEL = "Container";
	
	private static final int COLUMN_NAME_WIDTH = 300;
	private static final int COLUMN_TYPE_WIDTH = 300;


	private static final int SPEC_COLUMN_INDEX = 1;

	private Table table;
	private List<TableItem> tableItems = new ArrayList<TableItem>();
	private List<CCombo> editors = new ArrayList<CCombo>();
	
	private ReqIFImpl reqIfContent;
	private Map<StructuralElementInstance, Specification> mapSeiToSpec = new HashMap<StructuralElementInstance, Specification>();

	/**
	 * Constructor
	 */
	protected ReqIfMappingPage() {
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

		table = new Table(content, SWT.CHECK | SWT.BORDER | SWT.H_SCROLL);
		table.setLayout(new GridLayout());
		table.setLayoutData(new GridData(GridData.FILL_BOTH | GridData.GRAB_HORIZONTAL | GridData.GRAB_VERTICAL));
		table.setHeaderVisible(true);
		table.addSelectionListener(this);

		TableColumn columnColumnName = new TableColumn(table, SWT.NULL);
		columnColumnName.setWidth(COLUMN_NAME_WIDTH);
		columnColumnName.setText(COLUMN_SPECIFICATION_LABEL);

		TableColumn columnType = new TableColumn(table, SWT.NONE | SWT.DROP_DOWN);
		columnType.setText(COLUMN_CONTAINER_LABEL);
		columnType.setWidth(COLUMN_TYPE_WIDTH);

	}

	/**
	 * Hand over reqIF content to this page
	 * @param reqIFImpl the reqIF model
	 * @param projectRepo the project's repository to get potential SEI containers from
	 */
	public void setInput(ReqIFImpl reqIFImpl, Repository projectRepo) {
		this.reqIfContent = reqIFImpl;
		cleanTable();
		EList<Specification> specList = reqIFImpl.getCoreContent().getSpecifications();
		for (Specification spec : specList) {
			TableItem item;
			int index = specList.indexOf(spec);
			if (tableItems.size() <= index) {
				item = new TableItem(table, SWT.NULL);
				tableItems.add(item);
			} else {
				item = tableItems.get(index);
			}
			item.setText(spec.getLongName());
			List<String> containerNamerList = new ArrayList<String>();
			for (StructuralElementInstance rootSei : projectRepo.getRootEntities()) {
				containerNamerList.add(rootSei.getName());
				rootSei.getDeepChildren().stream().
					forEach(child -> containerNamerList.add(child.getFullQualifiedInstanceName())
				);
			}
			createDropDownEditor(item, containerNamerList);
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
		typeDropdown.setText(item.getText(SPEC_COLUMN_INDEX));
		for (String option : options) {
			typeDropdown.add(option);
		}
		final TableEditor editor = new TableEditor(table);
		editor.grabHorizontal = true;
		editor.setEditor(typeDropdown, item, SPEC_COLUMN_INDEX);
	}
	
	@Override
	public boolean isPageComplete() {
		setPageComplete(false);
		for (TableItem item : tableItems) {
			if (item.getChecked() && item.getText(1).equals("")) {
				return false;
			}
		}
		return true;
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


}
