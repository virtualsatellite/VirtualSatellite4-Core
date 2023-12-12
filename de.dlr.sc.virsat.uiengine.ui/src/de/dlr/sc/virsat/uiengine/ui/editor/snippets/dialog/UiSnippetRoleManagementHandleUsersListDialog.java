/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.uiengine.ui.editor.snippets.dialog;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Monitor;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

/**
 * UiSnippetRoleManagementHandleUsersListDialog is a custom dialog that extends
 * the Dialog class, allowing user interaction to modify a list of users. The
 * dialog provides options to add, remove, update, and rearrange users in the
 * list. It also notifies the parent application of user changes using a
 * callback mechanism.
 *
 * This dialog is primarily used to interact with and manage a list of features
 * displayed to the user, enabling them to make necessary modifications.
 *
 * This dialog includes the following features: - Adding a new feature to the
 * list - Removing an existing feature from the list - Updating an existing
 * feature in the list - Moving a feature up or down in the list
 *
 * The dialog is configured to have a specified size and is centered on the
 * screen for user convenience.
 *
 * Usage: 1. Create an instance of
 * UiSnippetRoleManagementHandleUsersListDialog, providing the parent shell,
 * initial features to display, and a callback for feature updates. 2. Open the
 * dialog using the open() method.
 */

public class UiSnippetRoleManagementHandleUsersListDialog extends Dialog {

	// Fields
	private List<String> features;  // Array of features
	private org.eclipse.swt.widgets.List featuresList;  // SWT List for displaying features
	private IFeatureUpdateCallback featureUpdateCallback;  // Callback for updating features 
	static final int NUM_COLUMNS_IN_THE_GRID = 3;
	static final int HORIZONTAL_INDENT_FOR_VALUE_LABEL = 10;
	static final int VERTICAL_SPAN = 6;
	static final int HORIZONTAL_INDENT_FOR_FEATURE_LABEL = 10;   

	public static final String USER_ALREADY_EXIST = "User already exist";
	public static final String USER_NAME_LABEL = "Username";  // Define a constant for the label text
	public static final String LIST_OF_USERS_LABEL = "List of users";
	public static final String EMPTY_USER = "EMPTY USER";
	/**
	 * Constructor for UiSnippetRoleManagementHandleUsersListDialog.
	 *
	 * @param parentShell           Parent shell for the dialog
	 * @param initialFeatures       Initial features to display
	 * @param callback              Callback to handle feature updates
	 */

	public UiSnippetRoleManagementHandleUsersListDialog(Shell parentShell, List<String> initialFeatures, IFeatureUpdateCallback callback) {
		super(parentShell);
		this.features = initialFeatures;
		this.featureUpdateCallback = callback; // Assign the callback
	}
	/**
	 * Configures the shell by setting its size.
	 *
	 * @param newShell The shell to configure
	 */
	@Override
	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);

		final int width = 700;
		final int height = 500;

		// Adjust the width and height as needed
		newShell.setSize(width, height);  

		// Center the dialog on the screen
		centerShellOnScreen(newShell); 

		// Set the title of the dialog
		newShell.setText("USERS-DISCIPLINE");
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		// Create the main container
		Composite container = (Composite) super.createDialogArea(parent);

		// Set up the layout for the container
		GridLayout layout = new GridLayout(NUM_COLUMNS_IN_THE_GRID, false);
		container.setLayout(layout);

		// Label and Text for Value
		Label valueLabel = new Label(container, SWT.NONE);
		valueLabel.setText(USER_NAME_LABEL);
		valueLabel.setForeground(parent.getDisplay().getSystemColor(SWT.COLOR_BLUE));  // Set the foreground color
		GridData valueLabelGridData = new GridData(SWT.FILL, SWT.CENTER, false, false);
		valueLabelGridData.horizontalIndent = HORIZONTAL_INDENT_FOR_FEATURE_LABEL;  // Adjust the indentation
		valueLabel.setLayoutData(valueLabelGridData);
		Text valueText = new Text(container, SWT.BORDER);
		GridData valueTextGridData = new GridData(SWT.FILL, SWT.CENTER, true, false);
		valueText.setLayoutData(valueTextGridData);

		// Features Label and List
		Composite featuresComposite = new Composite(container, SWT.NONE);
		featuresComposite.setLayout(new GridLayout());	    
		GridData featuresCompositeGridData = new GridData(SWT.FILL, SWT.FILL, true, true, 1, VERTICAL_SPAN);
		featuresComposite.setLayoutData(featuresCompositeGridData);

		Label featuresLabel = new Label(featuresComposite, SWT.NONE);
		featuresLabel.setText(LIST_OF_USERS_LABEL); // Capitalize "Users"
		featuresLabel.setForeground(parent.getDisplay().getSystemColor(SWT.COLOR_BLUE));  // Set the foreground color
		GridData featuresLabelGridData = new GridData(SWT.FILL, SWT.CENTER, false, false);
		featuresLabelGridData.horizontalIndent = HORIZONTAL_INDENT_FOR_FEATURE_LABEL;  // Adjust the indentation
		featuresLabel.setLayoutData(featuresLabelGridData);

		featuresList = new org.eclipse.swt.widgets.List(featuresComposite, SWT.BORDER | SWT.V_SCROLL);
		GridData listGridData = new GridData(SWT.FILL, SWT.FILL, true, true);
		featuresList.setLayoutData(listGridData);
		featuresList.setItems(features.toArray(new String[features.size()]));

		// Buttons
		Composite buttonsComposite = new Composite(container, SWT.NONE);
		buttonsComposite.setLayout(new GridLayout());
		GridData buttonsCompositeGridData = new GridData(SWT.FILL, SWT.FILL, false, false);
		buttonsComposite.setLayoutData(buttonsCompositeGridData);

		Button addButton = new Button(buttonsComposite, SWT.PUSH);
		addButton.setText("Add");
		GridData addButtonGridData = new GridData(SWT.FILL, SWT.CENTER, true, false);
		addButton.setLayoutData(addButtonGridData);
		// Set focus on the "Add" button for usability
		addButton.setFocus();
		
		Button updateButton = new Button(buttonsComposite, SWT.PUSH);
		updateButton.setText("Rename");
		GridData updateButtonGridData = new GridData(SWT.FILL, SWT.CENTER, true, false);
		updateButton.setLayoutData(updateButtonGridData);

		Button removeButton = new Button(buttonsComposite, SWT.PUSH);
		removeButton.setText("Remove");
		GridData removeButtonGridData = new GridData(SWT.FILL, SWT.CENTER, true, false);
		removeButton.setLayoutData(removeButtonGridData);

		Button upButton = new Button(buttonsComposite, SWT.PUSH);
		upButton.setText("Up");
		GridData upButtonGridData = new GridData(SWT.FILL, SWT.CENTER, true, false);
		upButton.setLayoutData(upButtonGridData);

		Button downButton = new Button(buttonsComposite, SWT.PUSH);
		downButton.setText("Down");
		GridData downButtonGridData = new GridData(SWT.FILL, SWT.CENTER, true, false);
		downButton.setLayoutData(downButtonGridData);

		// Add event listeners to the widgets
		addListeners(valueText, addButton, updateButton, removeButton, upButton, downButton);

        // Set focus on the Add button when the dialog is opened
        addButton.setFocus();
        
		return container;
	}
	
	@Override
	protected void buttonPressed(int buttonId) {
		if (IDialogConstants.OK_ID == buttonId) {
			String[] updatedFeatures = featuresList.getItems();
			notifyChanges(updatedFeatures);  // Notify with the updated features
			okPressed();
			close();
		} else if (IDialogConstants.CANCEL_ID == buttonId) {
			cancelPressed();
		}
	}

	/**
	 * Add event listeners to various widgets in the dialog.
	 */
	private void addListeners(Text valueText, Button addButton, Button updateButton, Button removeButton, Button upButton, Button downButton) {
		// Down button listener
		downButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				moveItem(1);
			}
		});

		// Up button listener
		upButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				moveItem(-1);
			}
		});

		// Add button listener
		addButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String value = valueText.getText();
				if (value.isEmpty()) {
					// Show a message dialog indicating that the user should enter a value
					MessageBox messageBox = new MessageBox(getShell(), SWT.ICON_ERROR | SWT.OK);
					messageBox.setText(EMPTY_USER);
					messageBox.setMessage("Empty field! Please enter a value.");
					messageBox.open();
				} else if (features.contains(value)) {
					// Show a message dialog indicating that the feature already exists
					MessageBox messageBox = new MessageBox(getShell(), SWT.ICON_ERROR | SWT.OK);
					messageBox.setText(USER_ALREADY_EXIST);
					messageBox.setMessage("User already exists in the list.");
					messageBox.open();
				} else {
					featuresList.add(value);
					// Clear the valueText after adding a feature
					valueText.setText("");
				}
			}
		});

		/**
		 * Creates and configures the "Update" button within the specified composite.
		 *
		 * @param buttonsComposite The composite in which the button will be created.
		 * @param valueText The Text widget containing the value to be updated.
		 */	
		// Update button listener
		updateButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String selectedValue = valueText.getText().trim();

				if (selectedValue.isEmpty()) {
					MessageBox messageBox = new MessageBox(getShell(), SWT.ICON_INFORMATION | SWT.OK);
					messageBox.setMessage("Please select a user to update.");
					messageBox.open();
					return;
				}

				int selectedIndex = featuresList.getSelectionIndex();

				// Check if the selectedValue already exists in the features list
				if (features.contains(selectedValue)) {
					MessageBox messageBox = new MessageBox(getShell(), SWT.ICON_INFORMATION | SWT.OK);
					messageBox.setMessage("The entered name is not different from the existing one.");
					messageBox.open();
					return;
				}

				if (selectedIndex >= 0 && selectedIndex < features.size()) {
					featuresList.setItem(selectedIndex, selectedValue);
					features.set(selectedIndex, selectedValue);
					valueText.setText(""); // Clear the input field
				} else {
					System.out.println();
				}
			}
		});
		
		// Remove button listener
		removeButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				int selectedIndex = featuresList.getSelectionIndex();
				if (selectedIndex != -1) {
					// Remove the element from the list and the array
					featuresList.remove(selectedIndex);
					removeFeatureFromListAndArray(selectedIndex);
					// Clear the valueText after removing a feature
					valueText.setText("");
				}
			}

			private void removeFeatureFromListAndArray(int index) {
				// Check if the index is valid
				if (index >= 0 && index < features.size()) {
					// Create a new array with a reduced size
					List<String> newFeatures = new ArrayList<String>();
					int count = 0;
					// Copy the elements except the one at the specified index
					for (int i = 0; i < features.size(); i++) {
						if (i != index) {
							newFeatures.set(count, features.get(i));
							count++;
						}
					}
					// Update the features array
					features = newFeatures;
				}
			}
		});

		// Add a SelectionListener to the featuresList
		featuresList.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String[] selectedFeatures = featuresList.getSelection();
				if (selectedFeatures.length > 0) {
					String selectedFeature = selectedFeatures[0];
					valueText.setText(selectedFeature);
				}
			}
		});

		// Add modify button listener
		valueText.addModifyListener(new ModifyListener() {
			@Override
			public void modifyText(ModifyEvent e) {
				// Update the list based on the text in valueText
				updateFeaturesList(valueText.getText());
			}
		});
	}

	/**
	 * Update the features list based on the input value
	 * 
	 *@param filter the features that contain the input value
	 */
	private void updateFeaturesList(String value) {
	    for (String feature : features) {
	        if (feature.contains(value)) {
	            // Check if the feature is already in the list
	            boolean alreadyInList = false;
	            for (String item : featuresList.getItems()) {
	                if (item.equals(feature)) {
	                    alreadyInList = true;
	                    break;
	                }
	            }
	            if (!alreadyInList) {
	                featuresList.add(feature);
	            }
	        }
	    }
	}
	/**
	 * Moves the selected item in the features list up or down.
	 *
	 * @param direction The direction to move the item: 1 for down, -1 for up
	 */

	private void moveItem(int direction) {
		int selectedIndex = featuresList.getSelectionIndex();

		// Check if a feature is selected
		if (selectedIndex != -1) {
			int newIndex = selectedIndex + direction;

			// Check if the new index is valid
			if (newIndex >= 0 && newIndex < featuresList.getItemCount()) {
				String selectedFeature = featuresList.getItem(selectedIndex);
				featuresList.remove(selectedIndex);
				featuresList.add(selectedFeature, newIndex);
				featuresList.setSelection(newIndex);
			}
		}
	}

	/**
	 * notifyChanges method to notify the main application about changes
	 */
	private void notifyChanges(String[] updatedFeatures) {
		if (featureUpdateCallback != null) {

			featureUpdateCallback.onFeaturesChanged(updatedFeatures);
		}
		close();
	}
	/**
	 * Center the shell on the screen.
	 *
	 * @param shell The shell to be centered
	 */
	private void centerShellOnScreen(Shell shell) {
		Monitor primary = shell.getDisplay().getPrimaryMonitor();
		Rectangle bounds = primary.getBounds();
		Rectangle rect = shell.getBounds();
		int x = bounds.x + (bounds.width - rect.width) / 2;
		int y = bounds.y + (bounds.height - rect.height) / 2;
		shell.setLocation(x, y);
	}

}