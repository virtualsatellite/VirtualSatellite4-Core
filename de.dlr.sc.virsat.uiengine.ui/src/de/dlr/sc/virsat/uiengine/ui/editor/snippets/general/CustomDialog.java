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

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

/**
 * this class extends the Dialog
 * @author ngat_di
 *
 */
public class CustomDialog extends Dialog {

    private String[] features;
    private List featuresList;
    private FeatureUpdateCallback featureUpdateCallback; // Add this field

    public CustomDialog(Shell parentShell, String[] initialFeatures, FeatureUpdateCallback callback) {
        super(parentShell);
        features = initialFeatures;
        featureUpdateCallback = callback; // Assign the callback
    }

    @Override
    protected void configureShell(Shell newShell) {
        super.configureShell(newShell);
        final int width = 500;
        final int height = 400;
        newShell.setSize(width, height);  // Adjust the width and height as needed
    }

	@Override
    protected Control createDialogArea(Composite parent) {
	    Composite container = (Composite) super.createDialogArea(parent);
	    final int numColumnsinTheGrid = 3;
	    // Set up the layout for the container
	    GridLayout layout = new GridLayout(numColumnsinTheGrid, false);
	    container.setLayout(layout);
	    // Label and Text for Value
	    Label valueLabel = new Label(container, SWT.NONE);
	    valueLabel.setText("Value:");
	    GridData valueLabelGridData = new GridData(SWT.FILL, SWT.CENTER, false, false);
	    final int horizontalIndentForValueLabel = 10;
	    valueLabelGridData.horizontalIndent = horizontalIndentForValueLabel;  // Adjust the indentation
	    valueLabel.setLayoutData(valueLabelGridData);

	    Text valueText = new Text(container, SWT.BORDER);
	    GridData valueTextGridData = new GridData(SWT.FILL, SWT.CENTER, true, false);
	    valueText.setLayoutData(valueTextGridData);
	    // Features Label and List
	    Composite featuresComposite = new Composite(container, SWT.NONE);
	    featuresComposite.setLayout(new GridLayout());
	    final int verticalSpan = 6;
	    GridData featuresCompositeGridData = new GridData(SWT.FILL, SWT.FILL, true, true, 1, verticalSpan);
	    featuresComposite.setLayoutData(featuresCompositeGridData);
	    Label featuresLabel = new Label(featuresComposite, SWT.NONE);
	    featuresLabel.setText("Features:");
	    GridData featuresLabelGridData = new GridData(SWT.FILL, SWT.CENTER, false, false);
	    final int horizontalIndentForFeatureLabel = 10;
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
                String selectedFeature = featuresList.getSelection()[0];  // Get the selected feature
                valueText.setText(selectedFeature);  // Set the selected feature as the text in valueText
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

    /**
     * updateFeatures list method
     *
     */
    private void updateFeaturesList(String value) {
        // Update the features list based on the input value
        // For simplicity, we'll just filter the features that contain the input value
        //featuresList.removeAll();

        for (String feature : features) {
        	if (feature.contains(value) && !featureAlreadyExists(feature)) {
                featuresList.add(feature);
            }
        }
    }
    /**
     * featureAlreadyExists list method
     */
    private boolean featureAlreadyExists(String feature) {
    	String[] items = featuresList.getItems();
        for (String item : items) {
            if (item.equals(feature)) {
                return true;
            }
        }
        return false;
	}

    
	/**
     * moveItem method
     *
     */
    private void moveItem(int direction) {
        int selectedIndex = featuresList.getSelectionIndex();
        if (selectedIndex != -1) {
            int newIndex = selectedIndex + direction;
            if (newIndex >= 0 && newIndex < featuresList.getItemCount()) {
                String selectedFeature = featuresList.getItem(selectedIndex);
                featuresList.remove(selectedIndex);
                featuresList.add(selectedFeature, newIndex);
                featuresList.setSelection(newIndex);
            }
        }
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
     * notifyChanges method to notify the main application about changes
     */
    private void notifyChanges(String[] updatedFeatures) {
        if (featureUpdateCallback != null) {
        	
            featureUpdateCallback.onFeaturesChanged(updatedFeatures);
        }
        close();
    }
	
}