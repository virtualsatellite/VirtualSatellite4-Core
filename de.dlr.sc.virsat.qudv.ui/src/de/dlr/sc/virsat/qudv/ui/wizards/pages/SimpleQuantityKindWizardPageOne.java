/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.qudv.ui.wizards.pages;

import org.eclipse.swt.widgets.Composite;

import de.dlr.sc.virsat.model.dvlm.qudv.AQuantityKind;
import de.dlr.sc.virsat.model.dvlm.units.UnitManagement;

/**
 * first page of the wizard to define simple quantity kinds
 * @author scha_vo
 *
 */
public class SimpleQuantityKindWizardPageOne extends AQuantityKindWizardPage {
	
	/**
	 * public constructor
	 * @param quantityKind the quantity kind to work on
	 * @param um the unit management
	 * @param pageName the name of the page
	 */
	public SimpleQuantityKindWizardPageOne(AQuantityKind quantityKind, UnitManagement um, String pageName) {
		super(quantityKind, um, pageName);
		setTitle("Simple Quantity Kind Wizard First Page");
		setDescription("This wizard help to create and edit simple quantity kinds of the QUDV Model.");
	}

	@Override
	protected void updateWidgets() {
		setPageComplete(!name.getText().isEmpty());
	}
	
	@Override
	public void createControl(Composite parent) {
		super.createControl(parent);
		// Required to avoid an error in the system
		setControl(container);
		setPageComplete(false);
		initializeValues();
	}
	
	/**
	 * method to initialize the values in the wizard page. It reads from the object and fills out the widgets in the wizard
	 */
	protected void initializeValues() {
		if (quantityKind != null) {
			name.setText(quantityKind.getName());
			symbol.setText(quantityKind.getSymbol());
			description.setText(quantityKind.getDescription());
			definitionURI.setText(quantityKind.getDefinitionURI());
			setPageComplete(true);
		}
	}
}
