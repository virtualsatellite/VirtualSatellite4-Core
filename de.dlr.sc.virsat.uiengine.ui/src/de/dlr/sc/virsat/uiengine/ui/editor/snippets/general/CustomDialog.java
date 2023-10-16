/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.uiengine.ui.editor.snippets.general;

import java.util.Arrays;

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
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Monitor;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

/**
 * CustomDialog is a custom dialog that extends the Dialog class, allowing user interaction to modify a list of users.
 * The dialog provides options to add, remove, update, and rearrange users in the list. It also notifies the parent application
 * of user changes using a callback mechanism.
 *
 * This dialog is primarily used to interact with and manage a list of features displayed to the user, enabling them to make necessary modifications.
 *
 * This dialog includes the following features:
 * - Adding a new feature to the list
 * - Removing an existing feature from the list
 * - Updating an existing feature in the list
 * - Moving a feature up or down in the list
 *
 * The dialog is configured to have a specified size and is centered on the screen for user convenience.
 *
 * Usage:
 * 1. Create an instance of CustomDialog, providing the parent shell, initial features to display, and a callback for feature updates.
 * 2. Open the dialog using the open() method.
 */

public class CustomDialog extends Dialog {

    // Fields
    private String[] features;  // Array of features
    private List featuresList;  // SWT List for displaying features
    private FeatureUpdateCallback featureUpdateCallback;  // Callback for updating features 

    /**
     * Constructor for CustomDialog.
     *
     * @param parentShell           Parent shell for the dialog
     * @param initialFeatures       Initial features to display
     * @param callback              Callback to handle feature updates
     */

    public CustomDialog(Shell parentShell, String[] initialFeatures, FeatureUpdateCallback callback) {
        super(parentShell);
        this.features = Arrays.copyOf(initialFeatures, initialFeatures.length);
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
        
        final int width = 970;
        final int height = 400;
        
        // Adjust the width and height as needed
        newShell.setSize(width, height);  
        
        // Center the dialog on the screen
        centerShellOnScreen(newShell); 
        
        // Set the title of the dialog
        newShell.setText("User--Discipline");
    }
	@Override
    protected Control createDialogArea(Composite parent) {
	    final int numColumnsinTheGrid = 3;
	    final int horizontalIndentForValueLabel = 10;
	    final int verticalSpan = 6;
	    final int horizontalIndentForFeatureLabel = 10;    
		// Create the main container
	    Composite container = (Composite) super.createDialogArea(parent);
	    // Set up the layout for the container
	    GridLayout layout = new GridLayout(numColumnsinTheGrid, false);
	    container.setLayout(layout);
	    // Label and Text for Value
	    Label valueLabel = new Label(container, SWT.NONE);
	    valueLabel.setText("Value :");
	    valueLabel.setForeground(parent.getDisplay().getSystemColor(SWT.COLOR_BLUE));  // Set the foreground color
	    GridData valueLabelGridData = new GridData(SWT.FILL, SWT.CENTER, false, false);
	    valueLabelGridData.horizontalIndent = horizontalIndentForValueLabel;  // Adjust the indentation
	    valueLabel.setLayoutData(valueLabelGridData);
	    Text valueText = new Text(container, SWT.BORDER);
	    GridData valueTextGridData = new GridData(SWT.FILL, SWT.CENTER, true, false);
	    valueText.setLayoutData(valueTextGridData);
	    // Features Label and List
	    Composite featuresComposite = new Composite(container, SWT.NONE);
	    featuresComposite.setLayout(new GridLayout());	    
	    GridData featuresCompositeGridData = new GridData(SWT.FILL, SWT.FILL, true, true, 1, verticalSpan);
	    featuresComposite.setLayoutData(featuresCompositeGridData);
	    Label featuresLabel = new Label(featuresComposite, SWT.NONE);
	    featuresLabel.setText("List of users :");
	    featuresLabel.setForeground(parent.getDisplay().getSystemColor(SWT.COLOR_BLUE));  // Set the foreground color
	    GridData featuresLabelGridData = new GridData(SWT.FILL, SWT.CENTER, false, false);
	    
	    featuresLabelGridData.horizontalIndent = horizontalIndentForFeatureLabel;  // Adjust the indentation
	    featuresLabel.setLayoutData(featuresLabelGridData);
	    featuresList = new List(featuresComposite, SWT.BORDER | SWT.V_SCROLL);
	    GridData listGridData = new GridData(SWT.FILL, SWT.FILL, true, true);
	    featuresList.setLayoutData(listGridData);
	    featuresList.setItems(features);
	    // Buttons
	    Composite buttonsComposite = new Composite(container, SWT.NONE);
	    buttonsComposite.setLayout(new GridLayout());
	    GridData buttonsCompositeGridData = new GridData(SWT.FILL, SWT.FILL, false, false);
	    buttonsComposite.setLayoutData(buttonsCompositeGridData);
	    Button addButton = new Button(buttonsComposite, SWT.PUSH);
	    addButton.setText("Add");
	    GridData addButtonGridData = new GridData(SWT.FILL, SWT.CENTER, true, false);
	    addButton.setLayoutData(addButtonGridData);
	    
	    updateButton(buttonsComposite, valueText);
	    
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
        // Implementation of listeners
        downButton.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                moveItem(1);
            }
        });       
        //Up button
        upButton.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                moveItem(-1);
            }
        });       
        // Add button
        addButton.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                String value = valueText.getText();
                if (value.isEmpty()) {
                    // Show a message dialog indicating that the user should enter a value
                    MessageBox messageBox = new MessageBox(getShell(), SWT.ICON_INFORMATION | SWT.OK);
                    messageBox.setMessage("Please enter a value.");
                    messageBox.open();
                } else if (featureAlreadyExists(value)) {
                    // Show a message dialog indicating that the feature already exists
                    MessageBox messageBox = new MessageBox(getShell(), SWT.ICON_INFORMATION | SWT.OK);
                    messageBox.setMessage("User already exists in the list.");
                    messageBox.open();
                } else {
                    featuresList.add(value);
                    // Clear the valueText after adding a feature
                    valueText.setText("");
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
                if (index >= 0 && index < features.length) {
                    // Create a new array with a reduced size
                    String[] newFeatures = new String[features.length - 1];
                    int count = 0;
                    // Copy the elements except the one at the specified index
                    for (int i = 0; i < features.length; i++) {
                        if (i != index) {
                            newFeatures[count] = features[i];
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
      
        //Add modify button listener
        valueText.addModifyListener(new ModifyListener() {
            @Override
            public void modifyText(ModifyEvent e) {
                // Update the list based on the text in valueText
                updateFeaturesList(valueText.getText());
            }
        });       
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
     * Creates and configures the "Update" button within the specified composite.
     *
     * @param buttonsComposite The composite in which the button will be created.
     * @param valueText The Text widget containing the value to be updated.
     */
    private void updateButton(Composite buttonsComposite, Text valueText) {
    	Button updateButton = new Button(buttonsComposite, SWT.PUSH);
	    updateButton.setText("Rename");
	    GridData updateButtonGridData = new GridData(SWT.FILL, SWT.CENTER, true, false);
	    updateButton.setLayoutData(updateButtonGridData);
	    
	    // Update button listener
        updateButton.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                String selectedValue = valueText.getText().trim();

                if (selectedValue.isEmpty()) {
                    MessageBox messageBox = new MessageBox(getShell(), SWT.ICON_INFORMATION | SWT.OK);
                    messageBox.setMessage("Please select a feature to update.");
                    messageBox.open();
                    return;
                }

                int selectedIndex = featuresList.getSelectionIndex();

                // Check if the selectedValue already exists in the features list
                if (featureAlreadyExists(selectedValue)) {
                    MessageBox messageBox = new MessageBox(getShell(), SWT.ICON_INFORMATION | SWT.OK);
                    messageBox.setMessage("Feature already exists in the list.");
                    messageBox.open();
                    return;
                }

                if (selectedIndex != -1) {
                    featuresList.setItem(selectedIndex, selectedValue);
                    features[selectedIndex] = selectedValue;
                    valueText.setText("");
                }
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
        	if (feature.contains(value) && !featureAlreadyExists(feature)) {
                featuresList.add(feature);
            }
        }
    }
    
    /**
     * Checks if the given feature already exists in the features list.
     *
     * @param feature The feature to check for existence in the list
     * @return True if the feature already exists, false otherwise
     */
    private boolean featureAlreadyExists(String feature) {
        String[] items = featuresList.getItems();
        
        // Iterate through the items in the features list
        for (String item : items) {
            if (item.equals(feature)) {
                return true; // Feature already exists in the list
            }
        }
        return false; // Feature does not exist in the list
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