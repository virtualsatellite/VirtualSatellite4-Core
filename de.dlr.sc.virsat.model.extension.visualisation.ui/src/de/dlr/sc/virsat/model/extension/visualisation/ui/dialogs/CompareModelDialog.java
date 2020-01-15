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

import org.eclipse.core.runtime.Status;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;

import de.dlr.sc.virsat.model.extension.visualisation.ui.Activator;
import de.dlr.sc.virsat.project.resources.VirSatProjectResource;

/**
 * Simple Dialog to select another project for comparison
 * @author fisc_ph
 *
 */
public class CompareModelDialog extends TitleAreaDialog {

	protected VirSatProjectResource baseProject;
	private ProjectSelectionPart projectSelectionPart;
	
	/**
	 * Constructor for the simple comparison dialog
	 * @param parentShell the parent shell in which to create the dialog
	 * @param vsProject The virSatProject which is the baseline project for the comparison.
	 */
	public CompareModelDialog(Shell parentShell, VirSatProjectResource vsProject) {
		super(parentShell);
		this.baseProject = vsProject;
	}

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
		
		projectSelectionPart = new ProjectSelectionPart(area) {
			@Override
			public void validateProject() {
				validateInput();
			}
		};
		
		return area;
	}
	/**
	 * This method validates the inputs
	 * @return returns true in case inputs are valid
	 */
	protected boolean validateInput() {
		boolean inputsValid = false;
		if (getComparisonProjectResource() != null) {
			inputsValid = true;
			if (getComparisonProjectResource().getWrappedProject() == baseProject.getWrappedProject()) {
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

	@Override
	protected boolean isResizable() {
		return true;
	}

	/**
	 * Call this method to get the project that was selected for comparison
	 * @return the selected project as VirSatProjectResource
	 */
	public VirSatProjectResource getComparisonProjectResource() {
		return projectSelectionPart.getProjectResource();
	}
}