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

import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.widgets.Composite;

import de.dlr.sc.virsat.model.dvlm.qudv.AQuantityKind;
import de.dlr.sc.virsat.model.dvlm.qudv.AUnit;
import de.dlr.sc.virsat.model.dvlm.units.UnitManagement;

/**
 * First Page of the SimpleUnit Wizard for creating a unit in the library
 * @author scha_vo
 *
 */
public class SimpleUnitWizardPageOne extends AUnitWizardPage {
	
	/**
	 * public constructor
	 * @param unit the unit
	 * @param um the unit management
	 * @param pageName the name of the page
	 */
	public SimpleUnitWizardPageOne(AUnit unit, UnitManagement um, String pageName) {
		super(unit, um, pageName);
		setTitle("Simple Unit Wizard First Page");
		setDescription("This wizard help to create and edit simple units of the QUDV Model.");
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

	@Override
	protected void initializeValues() {
		if (unit != null) {
			name.setText(unit.getName());
			symbol.setText(unit.getSymbol());
			description.setText(unit.getDescription());
			definitionURI.setText(unit.getDefinitionURI());
			AQuantityKind quantityKind = unit.getQuantityKind();
			if (quantityKind == null) {
				comboViewer.setSelection(new StructuredSelection("no reference"));
			} else {
				comboViewer.setSelection(new StructuredSelection(quantityKind));
			}			
			setPageComplete(true);
		}	
	}
}
