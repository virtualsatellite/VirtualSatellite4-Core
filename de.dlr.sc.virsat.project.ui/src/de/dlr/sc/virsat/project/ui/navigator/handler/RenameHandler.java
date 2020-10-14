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
import org.eclipse.emf.common.command.UnexecutableCommand;
import org.eclipse.emf.edit.command.SetCommand;

import de.dlr.sc.virsat.model.dvlm.general.GeneralPackage;
import de.dlr.sc.virsat.model.dvlm.general.IName;

/**
 * Handler for Renaming an element from the navigator tree
 *
 */
public class RenameHandler extends ChangeValueHandler {

	public static final String SET_NAME_DIALOG_TITLE = "Rename Element";
	public static final String SET_NAME_DIALOG_MSG = "New name:";

	@Override
	protected String getCurrentValue() {
		IName namedObject = (IName) firstSelectedEObject;
		return namedObject.getName();
	}
	
	@Override
	protected Command createCommand() {
		if (firstSelectedEObject instanceof IName) {
			return SetCommand.create(ed, firstSelectedEObject, GeneralPackage.Literals.INAME__NAME, newValue);
		}
		
		return UnexecutableCommand.INSTANCE;
	}
	
	@Override
	public String showChangeValueDialog(String initialValue) {
		return showStringInputDialog(SET_NAME_DIALOG_TITLE, SET_NAME_DIALOG_MSG, initialValue);
	}
}
