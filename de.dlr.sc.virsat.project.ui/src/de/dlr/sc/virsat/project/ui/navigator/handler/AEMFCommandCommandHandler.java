/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.project.ui.navigator.handler;

import org.eclipse.emf.common.command.Command;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.ISelectionService;
import org.eclipse.ui.PlatformUI;

import de.dlr.sc.virsat.project.ui.editingDomain.handler.AEditingDomainCommandHandler;

/**
 * Abstract Command for the implementation of all Cut Copy Paste Handlers
 * @author fisc_ph
 *
 */
public abstract class AEMFCommandCommandHandler extends AEditingDomainCommandHandler {
	
	@Override
	public void execute() {
		Command cmd = createCommand();
		ed.getCommandStack().execute(cmd);
	}
	
	/**
	 * Abstract method that gets called from the can execute and execute method
	 * Method  should hand back the actual EMF Command
	 * @return The EMF Command executing the command cut paste operation
	 */
	protected abstract Command createCommand();
	
	@Override
	public boolean isEnabled() {
		// Get the info of where to execute this handler
		ISelectionService  selectionService = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
		ISelection selection = selectionService.getSelection();

		initializeFieldsFromSelection(selection);
			
		Command command = createCommand();
		
		return command.canExecute();
	}
}
