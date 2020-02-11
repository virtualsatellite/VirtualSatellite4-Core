/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.statemachines.ui.wizard;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;

import de.dlr.sc.virsat.model.dvlm.provider.DVLMEditPlugin;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import de.dlr.sc.virsat.model.extension.statemachines.Activator;
import de.dlr.sc.virsat.model.extension.statemachines.util.ConstraintTableHelper;


/**
 * Wizard for managing constraints
 * 
 * @author bell_er
 *
 */
public class ConstraintTableWizard extends Wizard implements INewWizard {
	private ConstraintTablePage constraintTablePage;
	private ISelection selection;

	/**
	 * Create a new constraint table
	 */
	public ConstraintTableWizard() {
		super();
		setWindowTitle("Constraint Table (Beta)");
	}

	@Override
	public void init(IWorkbench workbench, IStructuredSelection selection) {
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
		constraintTablePage = new ConstraintTablePage(selection);
		addPage(constraintTablePage);
	}

	@Override
	public boolean canFinish() {
		return true;
	}

	@Override
	public boolean performFinish() {
		StructuralElementInstance mainRootSc = (StructuralElementInstance) ((IStructuredSelection) selection).getFirstElement();
		try {
			ConstraintTableHelper.createAndExecuteCompaundCommandForMerge(mainRootSc, constraintTablePage.getStateMachines());
		} catch (CoreException e) {
			Status status = new Status(Status.ERROR, Activator.getPluginId(), "Failed to perform an operation! ", e);
			DVLMEditPlugin.getPlugin().getLog().log(status);
		}
		return true;
	}
}

