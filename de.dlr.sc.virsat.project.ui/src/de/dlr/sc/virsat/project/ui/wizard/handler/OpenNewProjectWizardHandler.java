/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.project.ui.wizard.handler;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchWizard;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.ui.wizards.IWizardDescriptor;

import de.dlr.sc.virsat.project.Activator;
import de.dlr.sc.virsat.project.ui.wizard.VirSatProjectWizard;

/**
 * Handler that opens the VirSat new Project Wizard
 * 
 * @author fisc_ph
 *
 */
public class OpenNewProjectWizardHandler extends AbstractHandler implements IHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		// Get the wizard descriptor from the Eclipse Platform
		IWizardDescriptor descriptor = PlatformUI.getWorkbench().getNewWizardRegistry().findWizard(VirSatProjectWizard.ID_NEW_VIRSAT_PROJECT_WIZARD);
	
		try {
			// Then if we have a wizard, open it.
			if (descriptor != null) {
				IWorkbench iWorkbench = PlatformUI.getWorkbench();
				IStructuredSelection iSelection = (IStructuredSelection) HandlerUtil.getCurrentSelection(event);
				
				IWorkbenchWizard wizard = descriptor.createWizard();
				wizard.init(iWorkbench, iSelection);
				WizardDialog wd = new WizardDialog(Display.getDefault().getActiveShell(), wizard);
				wd.setTitle(wizard.getWindowTitle());
				wd.open();
			}
		} catch (CoreException e) {
			Activator.getDefault().getLog().log(new Status(Status.ERROR, Activator.getPluginId(), "Failed to open new Project Wizard!", e));
		}
		return null;
	}
}
