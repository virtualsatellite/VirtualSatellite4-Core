/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.project.ui.editingDomain.action;

import org.eclipse.core.runtime.Status;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.ui.action.UndoAction;
import org.eclipse.ui.ISelectionService;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;

import de.dlr.sc.virsat.project.ui.Activator;
import de.dlr.sc.virsat.project.ui.navigator.util.VirSatSelectionHelper;

/**
 * The Undo Command implemented as old style Action.
 * The action is a directMenutItem and seems to behave differently than all other
 * menu items. Contributing to the org.eclipse.ui.edit.undo command via the command
 * framework only activates the command in the navigator but not in the
 * global Action/MenuBar. Using Actions together with an Action Provider, the functionality
 * is well populated.
 * @author fisc_ph
 *
 */
public class VirSatUndoAction extends UndoAction {

	/**
	 * Constructor to the Action which directly checks the enabled state
	 * and updates it accordingly if the current selection allows to do this
	 */
	public VirSatUndoAction() {
		super();
		update();
	}
	
	@Override
	public void run() {
		if (domain != null) {
			domain.getCommandStack().undo();
		}
	}

	@Override
	public void update() {
		IWorkbenchWindow workbenchWindow = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
		if (workbenchWindow == null) {
			setEnabled(false);
			return;
		}
		
		ISelectionService selectionService = workbenchWindow.getActivePage();
		
		try {
			// if selectionService is null, there will be a NullpointerException otherwise
			// this probably happens when closing the application hence we can disable undo
			if (selectionService == null || selectionService.getSelection() == null) {
				setEnabled(false);
				return;
			}
		} catch (NullPointerException npe) {
			Activator.getDefault().getLog().log(new Status(Status.WARNING, Activator.getPluginId(), "Failed to update VirSat Undo Actions due to NPE in Selection service: " + npe.getMessage()));
			setEnabled(false);
			return;
		}
			
		EditingDomain ed = VirSatSelectionHelper.getEditingDomainViaSlectionService(selectionService);
		setEditingDomain(ed);
		if (ed != null) {
			super.update();
		} else {
			// No domain, no commandStack thus no knowledge about undo/redo
			setEnabled(false);
		}
	}	
}
