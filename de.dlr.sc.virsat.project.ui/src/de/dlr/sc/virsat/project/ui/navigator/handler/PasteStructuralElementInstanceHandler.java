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
import org.eclipse.emf.edit.command.PasteFromClipboardCommand;

/**
 * Handler for Paste StructuralElementInstance command
 */
public class PasteStructuralElementInstanceHandler extends AEMFCommandCommandHandler {

	@Override
	public void execute() {
		Command pasteCommand = createCommand();
		ed.getCommandStack().execute(pasteCommand);
	}
	
	@Override
	protected Command createCommand() {
		return PasteFromClipboardCommand.create(ed, firstSelectedEObject, null);
	}
}
