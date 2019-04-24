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

import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.ui.action.RedoAction;
import org.eclipse.ui.ISelectionService;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;

import de.dlr.sc.virsat.project.ui.navigator.util.VirSatSelectionHelper;

/**
 * The UNDO/REDO Command implemented as old style Action.
 * The action is a directMenutItem and seems to behave differently than all other
 * menu items. Contributing to the org.eclipse.ui.edit.undo/redo command via the command
 * framework only activates the command in the navigator but not in the
 * global Action/MenuBar. Using Actions together with an Action Provider, the functionality
 * is well populated.
 * @author fisc_ph
 *
 */
public class VirSatRedoAction extends RedoAction {

	/**
	 * Constructor to the Action which directly checks the enabled state
	 * and updates it accordingly if the current selection allows to do this
	 */
	public VirSatRedoAction() {
		super();
		update();
	}
	
	@Override
	public void run() {
		if (domain != null) {
			domain.getCommandStack().redo();
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
