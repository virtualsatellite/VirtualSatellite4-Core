/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.uieingine.ui.dnd;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.util.LocalSelectionTransfer;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DropTargetEvent;
import org.eclipse.swt.dnd.DropTargetListener;

/**
 * Absract class for Drop Listeners that listen to the drop of selections
 * @author muel_s8
 *
 */

public abstract class ADropSelectionTargetListener implements DropTargetListener {
	
	private EditingDomain editingDomain;
	
	/**
	 * Default constructor
	 * @param editingDomain the editing domain
	 */
	public ADropSelectionTargetListener(EditingDomain editingDomain) {
		this.editingDomain = editingDomain;
	}
	
	/**
	 * Implement this method to create the command to be executed on the drop
	 * @param selection the selection
	 * @return the drop command
	 */
	public abstract Command createDropCommand(StructuredSelection selection);
	
	@Override
	public void drop(DropTargetEvent event) {
		Object data = event.data;
		if (data instanceof StructuredSelection) {
			StructuredSelection selection = (StructuredSelection) data;
			Command dropCmd = createDropCommand(selection);
			editingDomain.getCommandStack().execute(dropCmd);
		}
	}
	
	@Override
	public void dragEnter(DropTargetEvent event) {
	}

	@Override
	public void dragLeave(DropTargetEvent event) {
	}

	@Override
	public void dragOperationChanged(DropTargetEvent event) {
	}

	@Override
	public void dragOver(DropTargetEvent event) {
		Object data = LocalSelectionTransfer.getTransfer().getSelection();
		if (!(data instanceof StructuredSelection)) {
			event.detail = DND.DROP_NONE;
		} else {
			StructuredSelection selection = (StructuredSelection) data;
			Command dropCmd = createDropCommand(selection);
			if (!dropCmd.canExecute()) {
				event.detail = DND.DROP_NONE;
			}
		}
	}

	@Override
	public void dropAccept(DropTargetEvent event) {
	}
}
