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

import org.eclipse.emf.common.command.Command;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;

import de.dlr.sc.virsat.project.structure.command.CreateAddSeiWithFileStructureCommand;



/**
 * Wizard for Generating
 * 
 * @author bell_er
 *
 */

public class GenerateProductWizard extends Wizard implements INewWizard {

	private GenerateProductPage generate;
	private ISelection selection;

	/**
	 * Create a new Generate Wizard 
	 */

	public GenerateProductWizard() {
		super();
		setWindowTitle("Generate Product Wizard");
	}
	/**
	 * Set the selection for the new window
	 * @param selection to be set
	 */
	public void setSelection(ISelection selection) {
		this.selection = selection;
	}
	/**
	 * Perform Finish
	 * @return return true
	 */

	public boolean performFinish() {

		generate.getSei().setName(generate.getSeiName());
		Command commandAddStructuralElementInstance = CreateAddSeiWithFileStructureCommand.create(generate.getEd(), generate.getRep(), generate.getSei());
		generate.getEd().getCommandStack().execute(commandAddStructuralElementInstance);
		return true;
	}
	@Override
	public void addPages() {
		generate = new GenerateProductPage(selection);
		addPage(generate);
	}
	@Override
	public boolean canFinish() {
		return generate.isPageComplete();
	}

	@Override
	public void init(IWorkbench workbench, IStructuredSelection selection) {
	}	
}
