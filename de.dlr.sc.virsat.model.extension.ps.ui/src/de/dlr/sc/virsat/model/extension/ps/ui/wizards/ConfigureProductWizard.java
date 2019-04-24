/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.ps.ui.wizards;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;

import de.dlr.sc.virsat.model.extension.ps.util.ProductStructureHelper;


/**
 * Wizard for Configuring elements
 * 
 * @author bell_er
 *
 */

public class ConfigureProductWizard extends Wizard implements INewWizard {

	private ConfigureProductPage configurePage;
	private ISelection selection;

	/**
	 * Create a new Generate Wizard 
	 */
	public ConfigureProductWizard() {
		super();
		setWindowTitle("Configure Product Wizard");
	}

	@Override
	public void init(IWorkbench workbench, IStructuredSelection selection) {

	}

	@Override
	public boolean performFinish() {
		ProductStructureHelper.createAndExecuteCompaundCommandForMerge(configurePage.getEditingDomain(), configurePage.getMainRootSc(), configurePage.getRootSc());
		
		return true;
	}

	/**
	 * Set the selection for the new window
	 * @param selection to be set
	 */
	public void setSelection(ISelection selection) {
		this.selection = selection;
	}

	@Override
	public void addPages() {
		super.addPages();
		configurePage = new ConfigureProductPage(selection);
		addPage(configurePage);
	}

	@Override
	public boolean performCancel() {
		return super.performCancel();
	}
}
