/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.excel.ui.wizards;

import org.eclipse.core.resources.IContainer;
import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;

import de.dlr.sc.virsat.model.dvlm.Repository;
import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import de.dlr.sc.virsat.project.editingDomain.VirSatEditingDomainRegistry;
import de.dlr.sc.virsat.project.editingDomain.VirSatTransactionalEditingDomain;
import de.dlr.sc.virsat.project.resources.VirSatProjectResource;
import de.dlr.sc.virsat.project.ui.contentProvider.VirSatComposedContentProvider;
import de.dlr.sc.virsat.project.ui.contentProvider.VirSatFilteredWrappedTreeContentProvider;
import de.dlr.sc.virsat.project.ui.labelProvider.VirSatComposedLabelProvider;
import de.dlr.sc.virsat.project.ui.navigator.contentProvider.VirSatProjectContentProvider;
import de.dlr.sc.virsat.project.ui.navigator.contentProvider.VirSatWorkspaceContentProvider;
import de.dlr.sc.virsat.project.ui.navigator.labelProvider.VirSatProjectLabelProvider;
import de.dlr.sc.virsat.project.ui.navigator.labelProvider.VirSatWorkspaceLabelProvider;

/**
 * Page to select the resource that is to be exported
 */
public class ExportPage extends WizardPage {
	private static final String DIALOG_TEXT = "Select target";
	private static final String DIALOG_DEFAULT_FILE_NAME = "file.xlsx";
	private static final String[] DIALOG_EXTENSIONS = { "*.xlsx" };

	private static final String BUTTON_TEXT = "Browse";

	private static final int ROWS = 3;

	// Keys for the dialog settings
	public static final String DESTINATION_FILE_KEY = "DESTINATION_FILE";
	private static final String USE_DEFAULT_TEMPLATE_KEY = "USE_DEFAULT_TEMPLATE";
	private static final String DEFAULT_TEMPLATE_KEY = "DEFAULT_TEMPLATE";
	private static final String DESCRIPTION = "Export the selected element to a folder";
	private static final String DESTINATION = "Select the export destination:";

	private IContainer model;
	private Object selection;
	private ISelection preSelect;
	private Composite content;
	private Combo destinationField;
	private Combo templateField;
	private Composite composite1;
	private Label label1;
	private Composite composite2;
	private Label label2;
	private Button btnCheckButton;
	private boolean destination;
	private boolean template;

	private IDialogSettings wizardSettings;
	private Label lblDestination;
	
	/**
	 * Create a new page for export
	 * @param model the root element for the selection on what to export
	 * @param preSelect the initial selection to be transferred into import/export window
	 */
	protected ExportPage(IContainer model, ISelection preSelect) {
		super("Select element and destination");
		setTitle("Select element and destination");
		setDescription(DESCRIPTION);
		this.model = model;
		this.preSelect = preSelect;
		setPageComplete(false);
	}

	@Override
	public void createControl(Composite parent) {
		wizardSettings = getDialogSettings();

		content = new Composite(parent, SWT.NONE);
		content.setLayout(new GridLayout());
		content.setLayoutData(new GridData(GridData.FILL_BOTH | GridData.GRAB_HORIZONTAL | GridData.GRAB_VERTICAL));

		createTreeUI();
		createFileDestinationUI();
		createSelectionUI();
		createTemplateUI();

		String isCheckedString = wizardSettings.get(USE_DEFAULT_TEMPLATE_KEY);
		if (isCheckedString != null) {
			boolean isChecked = Boolean.valueOf(isCheckedString);
			btnCheckButton.setSelection(isChecked);

			if (isChecked) {
				handleSelectedCheckButton();
			}
		}

		setControl(content);

		setPageComplete(isComplete());
	}

	/**
	 * Checks if the page has been sufficiently filled with user data
	 * @return true iff the page is complete
	 */
	public boolean isComplete() {
		return selection != null && destination && template;
	}

	/**
	 * Create the check box button for using or not using default template
	 */
	private void createSelectionUI() {
		composite2 = new Composite(content, SWT.NONE);
		composite2.setLayout(new GridLayout(1, false));
		composite2.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_FILL | GridData.GRAB_HORIZONTAL));

		btnCheckButton = new Button(composite2, SWT.CHECK);
		btnCheckButton.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				wizardSettings.put(USE_DEFAULT_TEMPLATE_KEY, btnCheckButton.getSelection());
				handleSelectedCheckButton();
			}
		});
		btnCheckButton.setText("Use default template");
	}

	/**
	 * Handles the case of the user pressing the check button for using a default template or not
	 */
	private void handleSelectedCheckButton() {
		composite1.setEnabled(!composite1.getEnabled());

		if (composite1.getVisible()) {
			template = true;
		} else {
			template = !templateField.getText().equals("");
		}

		composite1.setVisible(!composite1.getVisible());
		setPageComplete(isComplete());
	}

	/**
	 * Create the dialog for selecting the template
	 */
	private void createTemplateUI() {
		template = false;
		composite1 = new Composite(content, SWT.FILL);
		composite1.setLayout(new GridLayout(ROWS, false));
		GridData data = new GridData(GridData.HORIZONTAL_ALIGN_FILL | GridData.GRAB_HORIZONTAL);
		composite1.setLayoutData(data);

		label2 = new Label(composite1, SWT.SHADOW_IN);
		label2.setText("Select the template ");
		new Label(composite1, SWT.NONE);
		new Label(composite1, SWT.NONE);

		label1 = new Label(composite1, SWT.NONE);
		label1.setText("Template");

		// destination name entry field
		templateField = new Combo(composite1, SWT.SINGLE | SWT.BORDER);
		data = new GridData(GridData.HORIZONTAL_ALIGN_FILL | GridData.GRAB_HORIZONTAL);
		final int widthHint = 250;
		data.widthHint = widthHint;
		templateField.setLayoutData(data);

		String defaultTemplate = wizardSettings.get(DEFAULT_TEMPLATE_KEY);
		if (defaultTemplate != null) {
			templateField.setText(defaultTemplate);
			template = true;
		}

		// destination browse button
		final Button destinationBrowseButton = new Button(composite1, SWT.PUSH);
		destinationBrowseButton.setText(BUTTON_TEXT);
		destinationBrowseButton.addListener(SWT.Selection, new Listener() {

			@Override
			public void handleEvent(Event event) {
				FileDialog dialog = new FileDialog(getContainer().getShell(), SWT.OPEN | SWT.SHEET);
				dialog.setText(DIALOG_TEXT);
				dialog.setFilterExtensions(DIALOG_EXTENSIONS);

				if (destinationField.getText().equals("")) {
					dialog.setFileName(DIALOG_DEFAULT_FILE_NAME);
				}

				String selectedDirectoryName = dialog.open();

				if (selectedDirectoryName != null) {
					template = true;
					setErrorMessage(null);
					templateField.setText(selectedDirectoryName);
					wizardSettings.put(DEFAULT_TEMPLATE_KEY, selectedDirectoryName);
					setPageComplete(isComplete());
				}
			}
		});
		setButtonLayoutData(destinationBrowseButton);
	}

	/**
	 * Create the Treeviewer
	 */

	private void createTreeUI() {

		TreeViewer treeViewer = new TreeViewer(content, SWT.BORDER);
		treeViewer.getTree().setLayoutData(new GridData(GridData.FILL_BOTH | GridData.GRAB_HORIZONTAL | GridData.GRAB_VERTICAL));

		VirSatComposedContentProvider cp = new VirSatComposedContentProvider();
		cp.registerSubContentProvider(new VirSatWorkspaceContentProvider());
		cp.registerSubContentProvider(new VirSatProjectContentProvider());

		VirSatComposedLabelProvider lp = new VirSatComposedLabelProvider();
		lp.registerSubLabelProvider(new VirSatWorkspaceLabelProvider());
		lp.registerSubLabelProvider(new VirSatProjectLabelProvider());

		VirSatFilteredWrappedTreeContentProvider filteredCP = new VirSatFilteredWrappedTreeContentProvider(cp);

		// Filter for elements that can be imported and exported together with their parents
		filteredCP.addClassFilter(StructuralElementInstance.class);
		filteredCP.addClassFilter(CategoryAssignment.class);
		filteredCP.addClassFilter(Repository.class);
		filteredCP.addClassFilter(VirSatProjectResource.class);

		treeViewer.setContentProvider(filteredCP);
		treeViewer.setLabelProvider(lp);

		if (null == preSelect) {
			treeViewer.setInput(model);
		} else {
			IStructuredSelection selection2 = (IStructuredSelection) preSelect;
			StructuralElementInstance sc = (StructuralElementInstance) selection2.getFirstElement();
			VirSatTransactionalEditingDomain ed = VirSatEditingDomainRegistry.INSTANCE.getEd(sc);
			Repository rep = ed.getResourceSet().getRepository();
			treeViewer.setInput(rep);
			treeViewer.setSelection((TreeSelection) preSelect);
			selection = treeViewer.getStructuredSelection().getFirstElement();
		}
		treeViewer.addSelectionChangedListener(new ISelectionChangedListener() {
			
			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				selection = treeViewer.getStructuredSelection().getFirstElement();
				setPageComplete(isComplete());
			}
		});
	}

	/**
	 * Create the dialog for selecting the file which will be exported to/imported from
	 */

	private void createFileDestinationUI() {
		Label label = new Label(content, SWT.FILL);
		label.setText(DESTINATION);

		Composite composite = new Composite(content, SWT.FILL);
		composite.setLayout(new GridLayout(ROWS, false));
		GridData data = new GridData(GridData.HORIZONTAL_ALIGN_FILL | GridData.GRAB_HORIZONTAL);
		composite.setLayoutData(data);

		lblDestination = new Label(composite, SWT.NONE);
		lblDestination.setText("Destination :");

		// destination name entry field
		destinationField = new Combo(composite, SWT.SINGLE | SWT.BORDER);
		data = new GridData(GridData.HORIZONTAL_ALIGN_FILL | GridData.GRAB_HORIZONTAL);
		final int widthHint = 250;
		data.widthHint = widthHint;
		destinationField.setLayoutData(data);
		destinationField.addModifyListener(new ModifyListener() {
			@Override
			public void modifyText(ModifyEvent e) {
				String selectedDirectoryName = destinationField.getText();
				if (selectedDirectoryName.equals("")) {
					destination = false;
				} else {
					destination = true;
					getDialogSettings().put(DESTINATION_FILE_KEY, selectedDirectoryName);
				}
				
				setPageComplete(isComplete());
			}
		});
		
		String defaultDestination = wizardSettings.get(DESTINATION_FILE_KEY);
		if (defaultDestination != null) {
			destinationField.setText(defaultDestination);
		}

		// destination browse button
		final Button destinationBrowseButton = new Button(composite, SWT.PUSH);
		destinationBrowseButton.setText(BUTTON_TEXT);
		destinationBrowseButton.addListener(SWT.Selection, new Listener() {

			@Override
			public void handleEvent(Event event) {
				DirectoryDialog dialog = new DirectoryDialog(getContainer().getShell(), SWT.SAVE | SWT.SHEET);
				dialog.setText(DIALOG_TEXT);

				String selectedDirectoryName = dialog.open();

				if (selectedDirectoryName != null) {
					setErrorMessage(null);
					destinationField.setText(selectedDirectoryName);
				}
			}
		});
		setButtonLayoutData(destinationBrowseButton);
	}

	/**
	 * Get the selected object
	 * @return the selected object
	 */
	public Object getSelection() {
		return selection;
	}

	/**
	 * Get the destination of the target file
	 * @return path to the target file
	 */
	public String getDestination() {
		return destinationField.getText();
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
	 * @return true iff the user wishes to use the default template
	 */
	public boolean getUseDefaultTemplate() {
		return btnCheckButton.getSelection();
	}
}
