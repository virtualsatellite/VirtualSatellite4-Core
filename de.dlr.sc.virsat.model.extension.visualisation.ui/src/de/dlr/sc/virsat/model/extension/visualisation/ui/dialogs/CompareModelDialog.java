/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.visualisation.ui.dialogs;

import java.io.IOException;

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

import de.dlr.sc.virsat.project.resources.VirSatProjectResource;
import de.dlr.sc.virsat.project.ui.navigator.contentProvider.VirSatWorkspaceContentProvider;
import de.dlr.sc.virsat.project.ui.navigator.labelProvider.VirSatWorkspaceLabelProvider;
import de.dlr.sc.virsat.model.extension.visualisation.ui.Activator;

/**
 * Simple Dialog to select another project for comparison
 * @author fisc_ph
 *
 */
public class CompareModelDialog extends TitleAreaDialog {

	/**
	 * Constructor for the simple comparison dialog
	 * @param parentShell the parent shell in which to create the dialog
	 * @param vsProject The virSatProject which is the baseline project for the comparison.
	 */
	public CompareModelDialog(Shell parentShell, VirSatProjectResource vsProject) {
		super(parentShell);
		this.baseProject = vsProject;
	}
	
	protected VirSatProjectResource baseProject;
	private TableViewer viewerProject;

	@Override
	public void create() {
		super.create();
		setTitle("Compare Two Satellite Models");
		setMessage("Select the Model to compare to and select the property to heat map!", IMessageProvider.INFORMATION);
		getButton(IDialogConstants.OK_ID).setEnabled(false);
		try {
			setTitleImage(new Image(null, Activator.getFileFromPlugin("/resources/icons/VirSat64.gif").openStream()));
		} catch (IOException e) {
			Activator.getDefault().getLog().log(new Status(Status.ERROR, Activator.getPluginId(), "CompareModelPropertyDialog: Failed to load image", e));
		}	
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		Composite area = (Composite) super.createDialogArea(parent);
		Composite container = new Composite(area, SWT.NONE);
		container.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		GridLayout layout = new GridLayout(1, false);
		container.setLayout(layout);
	
		createProjectSelection(container);
		
		return area;
	}

	protected static final int VIEWER_HEIGHT = 300;

	/**
	 * Creates the dialog area where to select the project
	 * @param container the main container where to create the dialog area
	 */
	private void createProjectSelection(Composite container) {
		Label labelProjectCompareTo = new Label(container, SWT.NONE);
		labelProjectCompareTo.setText("Compare project to:");
	
		viewerProject = new TableViewer(container);
		viewerProject.setContentProvider(new VirSatWorkspaceContentProvider());
		viewerProject.setLabelProvider(new VirSatWorkspaceLabelProvider());
		
		viewerProject.setInput(ResourcesPlugin.getWorkspace().getRoot());
	
		GridData gridDataViewer = new GridData();
		gridDataViewer.grabExcessHorizontalSpace = true;
		gridDataViewer.grabExcessVerticalSpace = true;
		gridDataViewer.horizontalAlignment = GridData.FILL;
		gridDataViewer.verticalAlignment = GridData.FILL;
		gridDataViewer.heightHint = VIEWER_HEIGHT;
		
		viewerProject.getControl().setLayoutData(gridDataViewer);
		viewerProject.addSelectionChangedListener((obj) -> {
			extractUserSelection();
			validateInput();
		});
	}

	/**
	 * This method validates the inputs
	 * @return returns true in case inputs are valid
	 */
	protected boolean validateInput() {
		boolean inputsValid = false;
		if (selectedProject != null) {
			inputsValid = true;
			if (selectedProject.getWrappedProject() == baseProject.getWrappedProject()) {
				setMessage("You should not compare to the baseline project!", IMessageProvider.WARNING);
			} else {
				setMessage("Selections are all fine!", IMessageProvider.INFORMATION);
			}
		} else {
			setMessage("Select the Model to compare to and select the property to heat map!", IMessageProvider.ERROR);
		}
	
		getButton(IDialogConstants.OK_ID).setEnabled(inputsValid);
		return inputsValid;
	}

	private VirSatProjectResource selectedProject;

	@Override
	protected boolean isResizable() {
		return true;
	}

	@Override
	protected void okPressed() {
		extractUserSelection();
		super.okPressed();
	}

	/**
	 * This method takes the suer input and stores it in class internal variables
	 */
	protected void extractUserSelection() {
		// First store the content that was selected
		// Store the project that was selected
		IStructuredSelection selection = (IStructuredSelection) viewerProject.getSelection();
		if (!selection.isEmpty()) {
			selectedProject = (VirSatProjectResource) selection.getFirstElement();
		}
	}

	/**
	 * Call this method to get the project that was selected for comparison
	 * @return the selected project as VirSatProjectResource
	 */
	public VirSatProjectResource getComparisonProjectResource() {
		return selectedProject;
	}
}