/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.uiengine.ui.wizard;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;

/**
 * 
 * @author kuja_tj
 *
 */
public abstract class AImportExportPage extends ATreeViewerPage {

	private static final String BUTTON_TEXT = "Browse";
	private static final String DESTINATION_FILE_KEY = "DESTINATION_FILE";
	private static final String DESTINATION_TEXT = "Destination:";
	protected static final int ROWS = 3;
	protected static final int DESTINATION_WIDTH_HINT = 250;
	protected static final String DIALOG_TEXT = "File name";
	protected int dialogStyle = SWT.SAVE | SWT.SHEET;

	protected boolean isDestinationSelected;
	private Combo destinationField;
	private Composite composite;

	/**
	 * default constructor
	 * 
	 * @param pageName
	 *            Name of the Page
	 */
	protected AImportExportPage(String pageName) {
		super(pageName);
	}
	
	/**
	 * default constructor
	 * 
	 * @param pageName
	 *            Name of the Page
	 *            
	 * @param style
	 * 			  the SWT style of the page 
	 */
	protected AImportExportPage(String pageName, int style) {
		super(pageName);
		this.dialogStyle = style;
	}

	
	/**
	 * create the UI for browsing the File Destination
	 */
	public void createFileDestinationUI() {
		createDestinationField();
		final Button destinationBrowseButton = new Button(composite, SWT.PUSH);
		destinationBrowseButton.setText(BUTTON_TEXT);
		destinationBrowseButton.addListener(SWT.Selection, new Listener() {
			@Override
			public void handleEvent(Event event) {
				String selectedDirectoryName = openDialog();

				if (selectedDirectoryName != null) {
					setErrorMessage(null);
					destinationField.setText(selectedDirectoryName);
				}
			}
		});
		setButtonLayoutData(destinationBrowseButton);
	}

	/**
	 * @return dialog.open() the selected directory Name
	 */
	protected String openDialog() {
		FileDialog dialog = new FileDialog(getContainer().getShell(), dialogStyle);
		dialog.setText(DIALOG_TEXT);
		dialog.setFilterExtensions(getSupportedFileEndings());
		return dialog.open();
	}
	
	/**
	 * Returns a array of file endings that should be allowed in the selection area
	 * 
	 * Should be implemented in the subclass to specify which file types are desired
	 * 
	 * @return the array of file endings as string
	 */
	protected String[] getSupportedFileEndings() {
		return new String[0];
	}

	/**
	 * Creates the field and the default value of the field
	 */
	public void createDestinationField() {
		composite = new Composite((Composite) getControl(), SWT.FILL);
		composite.setLayout(new GridLayout(ROWS, false));
		GridData data = new GridData(GridData.HORIZONTAL_ALIGN_FILL | GridData.GRAB_HORIZONTAL);
		composite.setLayoutData(data);

		Label label = new Label(composite, SWT.NONE);
		label.setText(DESTINATION_TEXT);

		destinationField = new Combo(composite, SWT.SINGLE | SWT.BORDER);
		data = new GridData(GridData.HORIZONTAL_ALIGN_FILL | GridData.GRAB_HORIZONTAL);

		data.widthHint = DESTINATION_WIDTH_HINT;
		destinationField.setLayoutData(data);
		destinationField.addModifyListener(new ModifyListener() {
			@Override
			public void modifyText(ModifyEvent e) {
				String selectedDirectoryName = destinationField.getText();
				if (selectedDirectoryName.equals("")) {
					isDestinationSelected = false;
				} else {
					isDestinationSelected = true;
					getDialogSettings().put(DESTINATION_FILE_KEY, selectedDirectoryName);
				}
				
				setPageComplete(isComplete());
			}
		});
		
		String defaultDestination = getDialogSettings().get(DESTINATION_FILE_KEY);
		if (defaultDestination != null) {
			destinationField.setText(defaultDestination);
		}
	}


	/**
	 * Get the destination of the target file
	 * 
	 * @return path to the target file
	 */
	public String getDestination() {
		return destinationField.getText();
	}

	@Override
	public boolean isComplete() {
		return isDestinationSelected && super.isComplete();
	}
	
}
