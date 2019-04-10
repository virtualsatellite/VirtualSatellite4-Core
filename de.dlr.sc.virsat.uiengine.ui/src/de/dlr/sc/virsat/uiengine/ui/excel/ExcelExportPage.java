/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.uiengine.ui.excel;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;

import de.dlr.sc.virsat.uiengine.ui.wizard.AImportExportPage;

/**
 * Page to select the resource that is to be exported/will be used as a root for import.
 * @author bell_Er
 *
 */

public class ExcelExportPage extends AImportExportPage {
	
	private static final String DIALOG_TEXT = "Select target";
	private static final String DIALOG_DEFAULT_FILE_NAME = "file.xlsx";
	private static final String[] DIALOG_EXTENSIONS = { "*.xlsx" };
	
	private static final String BUTTON_TEXT = "Browse";
	
	// Keys for the dialog settings
	private static final String USE_DEFAULT_TEMPLATE_KEY = "USE_DEFAULT_TEMPLATE";
	private static final String DEFAULT_TEMPLATE_KEY = "DEFAULT_TEMPLATE";

	private Combo templateField;
	private Composite compositeTemplate;
	private Composite compositeSelection;
	private Button btnCheckButton;
	
	private boolean template;

	/**
	 * Create a new page for either import or export
	 */
	protected ExcelExportPage() {
		super("Select element and destination");
		setTitle("Select element and destination");
	}

	@Override
	public void createControl(Composite parent) {
		super.createControl(parent);
		
		createFileDestinationUI();
		createSelectionUI();
		createTemplateUI();
		
		String isCheckedString = getDialogSettings().get(USE_DEFAULT_TEMPLATE_KEY);
		if (isCheckedString != null) {
			boolean isChecked = Boolean.valueOf(isCheckedString);
			btnCheckButton.setSelection(isChecked);
			
			if (isChecked) {
				handleSelectedCheckButton();
			}
		}
	}
	
	/**
	 * Checks if the page has been sufficiently filled with user data
	 * @return true iff the page is complete
	 */
	public boolean isComplete() {
		return super.isComplete() && template;
	}
	
	/**
	 * Create the check box button for using or not using default template
	 */
	private void createSelectionUI() {
		compositeSelection = new Composite((Composite) getControl(), SWT.NONE);
		compositeSelection.setLayout(new GridLayout(1, false));
		compositeSelection.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_FILL | GridData.GRAB_HORIZONTAL));
		
		btnCheckButton = new Button(compositeSelection, SWT.CHECK);
		btnCheckButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				getDialogSettings().put(USE_DEFAULT_TEMPLATE_KEY, btnCheckButton.getSelection());
				handleSelectedCheckButton();
			}
		});
		btnCheckButton.setText("Use default template");
	}

	/**
	 * Handles the case of the user pressing the check button for using a default template or not
	 */
	private void handleSelectedCheckButton() {
		compositeTemplate.setEnabled(!compositeTemplate.getEnabled());
		
		if (compositeTemplate.getVisible()) {
			template = true;
		} else {
			if (templateField.getText().equals("")) {
				template = false;
			} else {
				template = true;
			}
		}
		
		compositeTemplate.setVisible(!compositeTemplate.getVisible());
		setPageComplete(isComplete());
	}
	
	/**
	 * Create the dialog for selecting the template
	 */
	private void createTemplateUI() {
		template = false;
		compositeTemplate = new Composite((Composite) getControl(), SWT.FILL);
		compositeTemplate.setLayout(new GridLayout(ROWS, false));
		GridData data = new GridData(GridData.HORIZONTAL_ALIGN_FILL | GridData.GRAB_HORIZONTAL);
		compositeTemplate.setLayoutData(data);
		
		Label label = new Label(compositeTemplate, SWT.NONE);
		label.setText("Template:");		
		
        // destination name entry field
		templateField = new Combo(compositeTemplate, SWT.SINGLE | SWT.BORDER | SWT.READ_ONLY);
        data = new GridData(GridData.HORIZONTAL_ALIGN_FILL | GridData.GRAB_HORIZONTAL);
        data.widthHint = DESTINATION_WIDTH_HINT;
        templateField.setLayoutData(data);

        String defaultTemplate = getDialogSettings().get(DEFAULT_TEMPLATE_KEY);
        if (defaultTemplate != null) {
        	templateField.setItems(defaultTemplate);
        	templateField.select(0);
        	template = true;
        }
        
        // destination browse button
        final Button destinationBrowseButton = new Button(compositeTemplate, SWT.PUSH);
        destinationBrowseButton.setText(BUTTON_TEXT);
        destinationBrowseButton.addListener(SWT.Selection, new Listener() {
			@Override
			public void handleEvent(Event event) {
		        
				String selectedDirectoryName = openDialogTemplate();
				
				if (selectedDirectoryName != null) {
					template = true;
					setErrorMessage(null);
					templateField.setItems(selectedDirectoryName);
		        	templateField.select(0);
		        	getDialogSettings().put(DEFAULT_TEMPLATE_KEY, selectedDirectoryName);
					setPageComplete(isComplete());
				} 

			}
        });
        setButtonLayoutData(destinationBrowseButton);
	}
	/**
	 * 
	 * @return the selected File Name
	 */
	protected String openDialogTemplate() {
		FileDialog dialogTemplate = new FileDialog(getContainer().getShell(), SWT.OPEN | SWT.SHEET);
        dialogTemplate.setText(DIALOG_TEXT);
        dialogTemplate.setFilterExtensions(DIALOG_EXTENSIONS);
        if (getDestination().equals("")) {
        	dialogTemplate.setFileName(DIALOG_DEFAULT_FILE_NAME);
        	isDestinationSelected = false;
        }
		return dialogTemplate.open();
	}
	
	@Override
	protected String openDialog() {
		FileDialog dialog = new FileDialog(getContainer().getShell(), SWT.SAVE | SWT.SHEET);
        dialog.setText(DIALOG_TEXT);
        dialog.setFilterExtensions(DIALOG_EXTENSIONS);
        if (getDestination().equals("")) {
        	dialog.setFileName(DIALOG_DEFAULT_FILE_NAME);
        }
		return dialog.open();
	}
	
	/**
	 * Get the destination of the template file
	 * @return path to the template file
	 */
	
	public String getTemplate() {
		return templateField.getText();
	}

	/**
	 * Whether the user chose to use the default template
	 * @return true iff the user wishes to use the default Template
	 */
	public boolean getUseDefaultTemplate() {
		return btnCheckButton.getSelection();
	}

}
