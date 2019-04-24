/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.qudv.ui.wizards;

import org.eclipse.jface.wizard.Wizard;

import de.dlr.sc.virsat.model.dvlm.units.UnitManagement;
import de.dlr.sc.virsat.qudv.ui.wizards.pages.QudvUnitSetupWizardSelectionPage;

/**
 * setup wizard for QUDV
 * @author scha_vo
 *
 */
public class QudvUnitSetupWizard extends Wizard {

	private QudvUnitSetupWizardSelectionPage wizardSelectionPage;

	private UnitManagement um = null;
	
	/**
	 * public constructor
	 * @param um the unit management
	 */
	public QudvUnitSetupWizard(UnitManagement um) {
		this.um = um;
		setForcePreviousAndNextButtons(true);
	}

	@Override
	public boolean performFinish() {
		// Do what you have to do to finish the wizard
		return true;
	}

	@Override
	public void addPages() {
		wizardSelectionPage = new QudvUnitSetupWizardSelectionPage("Select a wizard", um);
		addPage(wizardSelectionPage);

	}
}
