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

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;

import de.dlr.sc.virsat.project.resources.VirSatProjectResource;

/**
 * Standard dialog to compare a model with another one and selecting a parameter
 * for comparison
 * 
 * @author fisc_ph
 *
 */
public class CompareModelPropertyDialog extends CompareModelDialog {

	private QudvPropertySelectionPart propertySelectionPart;
	
	/**
	 * Constructor for the simple comparison dialog
	 * 
	 * @param parentShell
	 *            the parent shell in which to create the dialog
	 * @param vsProject
	 *            The virSatProject which is the baseline project for the
	 *            comparison.
	 */
	public CompareModelPropertyDialog(Shell parentShell, VirSatProjectResource vsProject) {
		super(parentShell, vsProject);
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		Composite area = (Composite) super.createDialogArea(parent);

		propertySelectionPart = new QudvPropertySelectionPart(area, baseProject) {
			@Override
			public void validateProperty() {
				validateInput();
			}
		};

		return area;
	}
	
	/**
	 * This method validates the inputs
	 * 
	 * @return returns true in case inputs are valid
	 */
	@Override
	protected boolean validateInput() {
		boolean inputsValid = super.validateInput();
		if (inputsValid && getComparisonProjectPropertyFQN() != null) {
			inputsValid = true;
		} else {
			setMessage("Select the Model to compare to and select the property to be used for the heat map!",
					IMessageProvider.ERROR);
		}

		getButton(IDialogConstants.OK_ID).setEnabled(inputsValid);
		return inputsValid;
	}

	/**
	 * The FQN of the property that was selected by the user
	 * 
	 * @return null in case no property was selected
	 */
	public String getComparisonProjectPropertyFQN() {
		return propertySelectionPart.getComparisonProjectPropertyFQN();
	}
}
