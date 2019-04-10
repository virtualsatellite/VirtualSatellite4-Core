/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.graphiti.ui.wizards;


import java.util.Arrays;
import java.util.List;
import java.util.Vector;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.graphiti.dt.IDiagramType;
import org.eclipse.graphiti.ui.services.GraphitiUi;
import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;

import de.dlr.sc.virsat.project.ui.Activator;


/**
 * The Class DiagramWizardPage.
 */
public class DiagramWizardPage extends WizardPage {


	private static final String DEFAULT_TYPE = "selectedtype";  
	private static final String SELECTED_TYPE = "selectedtype";  
	private static final int SIZING_TEXT_FIELD_WIDTH = 250;
	Combo comboBox;
	Text textField;

	private Listener nameModifyListener = new Listener() {
		public void handleEvent(Event e) {
			boolean valid = validatePage();
			setPageComplete(valid);

		}
	};
	/**
	 * The Class DiagramWizardPage Constructor
	 * @param pageName page name
	 * @param title title
	 * @param titleImage titleImage
	 */
	public DiagramWizardPage(String pageName, String title, ImageDescriptor titleImage) {
		super(pageName, title, titleImage);
	}


	/**
	 * The Class DiagramWizardPage Constructor
	 * @param pageName page name
	 */
	protected DiagramWizardPage(String pageName) {
		super(pageName);
		setTitle("Diagram");
		setDescription("Diagram");
	}

	@Override
	public void createControl(Composite parent) {
		Composite composite = new Composite(parent, SWT.NULL);
		composite.setFont(parent.getFont());

		initializeDialogUnits(parent);

		composite.setLayout(new GridLayout());
		composite.setLayoutData(new GridData(GridData.FILL_BOTH));

		createWizardContents(composite);
		setPageComplete(validatePage());

		// Show description on opening
		setErrorMessage(null);
		setMessage(null);
		setControl(composite);
	}
	/**
	 * Returns the name of the diagram
	 * @return the name of the diagram
	 */
	public String getText() {
		if (textField == null) {
			return getInitialTextFieldValue();
		}

		return getTextFieldValue();
	}
	/**
	 * validates the pages
	 * @return true or false
	 */
	private boolean validatePage() {
		if ("".equals(getType())) {
			return false;
		}
		String text = getTextFieldValue();
		if (text.equals("")) {  
			setErrorMessage(null);
			setMessage("Please enter diagram name");
			return false;
		}

		IWorkspace workspace = ResourcesPlugin.getWorkspace();
		IStatus status = doWorkspaceValidation(workspace, text);
		if (!status.isOK()) {
			setErrorMessage(status.getMessage());
			return false;
		}

		setErrorMessage(null);
		setMessage(null);
		return true;
	}
	/**
	 * Creates the wizard contents
	 * @param parent the Composite parent
	 */
	private void createWizardContents(Composite parent) {
		createProjectNameGroup(parent);
		createTypeContents(parent);
	}
	/**
	 * Creates the Project Name Group
	 * @param parent the Composite parent
	 */
	private void createProjectNameGroup(Composite parent) {
		// project specification group
		Composite projectGroup = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout();
		layout.numColumns = 2;
		projectGroup.setLayout(layout);
		projectGroup.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		// new project label
		Label projectLabel = new Label(projectGroup, SWT.NONE);
		projectLabel.setText("Please enter diagram name");
		projectLabel.setFont(parent.getFont());

		// new project name entry field
		textField = new Text(projectGroup, SWT.BORDER);
		GridData data = new GridData(GridData.FILL_HORIZONTAL);
		data.widthHint = SIZING_TEXT_FIELD_WIDTH;
		textField.setLayoutData(data);
		textField.setFont(parent.getFont());

		// Set the initial value first before listener
		// to avoid handling an event during the creation.
		if (getInitialTextFieldValue() != null) {
			textField.setText(getInitialTextFieldValue());
		}
		textField.addListener(SWT.Modify, nameModifyListener);
	}
	/**
	 * Creates the Type Selection
	 * @param parent the Composite parent
	 */
	private void createTypeContents(Composite parent) {
		// project specification group
		Composite projectGroup = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout();
		layout.numColumns = 2;
		projectGroup.setLayout(layout);
		projectGroup.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		// new project label
		Label projectLabel = new Label(projectGroup, SWT.NONE);
		projectLabel.setFont(parent.getFont());
		projectLabel.setText("Diagram Type");

		// new project name entry field
		comboBox = new Combo(projectGroup, SWT.READ_ONLY | SWT.BORDER);
		GridData data = new GridData(GridData.FILL_HORIZONTAL);
		data.widthHint = SIZING_TEXT_FIELD_WIDTH;
		comboBox.setLayoutData(data);
		comboBox.setFont(parent.getFont());
		//comboBox.setVisibleItemCount(12);
		comboBox.addSelectionListener(new SelectionAdapter() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				IDialogSettings dialogSettings = Activator.getDefault().getDialogSettings();
				dialogSettings.put(SELECTED_TYPE, comboBox.getText());
				setPageComplete(validatePage());
			}
		});

		// set the contents of the Combo-widget
		comboBox.setItems(getAllAvailableDiagramTypes());
		if (getInitialValue() != null) {
			comboBox.setText(getInitialValue());
		}
	}

	/**
	 * Gets the available Diagram Types
	 * @return available diagram types
	 */
	private String[] getAllAvailableDiagramTypes() {
		Vector<String> diagramIds = new Vector<String>();
		for (IDiagramType diagramType : GraphitiUi.getExtensionManager().getDiagramTypes()) {
			diagramIds.add(diagramType.getId());
		}

		return diagramIds.toArray(new String[] {});
	}
	/**
	 * Gets the Initial Value
	 * @return first value for diagram types
	 */
	private String getInitialValue() {
		// Get last choice
		IDialogSettings dialogSettings = Activator.getDefault().getDialogSettings();
		String selType = dialogSettings.get(SELECTED_TYPE);
		List<String> asList = Arrays.asList(comboBox.getItems());
		if (asList.contains(selType)) {
			return selType;
		} else if (asList.contains(DEFAULT_TYPE)) {
			return DEFAULT_TYPE;
		}
		return null;
	}
	/**
	 * gets the text field value
	 * @return newDiagram
	 */
	private String getTextFieldValue() {
		if (textField == null) {
			return "";  
		}

		return textField.getText().trim();
	}
	/**
	 * default name for diagram
	 * @return newDiagram
	 */
	private String getInitialTextFieldValue() {
		return "newDiagram";  
	}
	/**
	 * validates the workspace
	 * @param workspace the workspace
	 * @param text  the name
	 * @return status
	 */
	private IStatus doWorkspaceValidation(IWorkspace workspace, String text) {
		IStatus ret = workspace.validateName(text, IResource.FILE);
		return ret;
	}

	@Override
	public void setVisible(boolean visible) {
		super.setVisible(visible);
		if (visible) {
			textField.setFocus();
			textField.selectAll();
		}
	}
	/**
	 * Returns the diagram type
	 * @return diagram type
	 */
	public String getType() {
		return comboBox.getText();
	}
	@Override
	public boolean isPageComplete() {
		return validatePage();
	}
}
