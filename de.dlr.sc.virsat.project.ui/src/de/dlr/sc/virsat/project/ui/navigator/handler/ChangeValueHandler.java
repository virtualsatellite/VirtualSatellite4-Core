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
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.PropertyinstancesPackage;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ValuePropertyInstance;

/**
 * Handler for changing the value of an element from the navigator tree
 *
 */
public class ChangeValueHandler extends AEMFCommandCommandHandler {

	public static final String SET_VALUE_DIALOG_TITLE = "Change Value";
	public static final String SET_VALUE_DIALOG_MSG = "New value:";
	
	protected String newValue;
	
	protected String getCurrentValue() {
		ValuePropertyInstance vpi = (ValuePropertyInstance) firstSelectedEObject;
		return vpi.getValue();
	}
	
	@Override
	public void execute() {
		String currentValue = getCurrentValue();
		newValue = showChangeValueDialog(currentValue);
		if (newValue != null && !newValue.equals(currentValue)) {
			super.execute();
		}
	}

	@Override
	protected Command createCommand() {
		if (firstSelectedEObject instanceof ValuePropertyInstance) {
			return SetCommand.create(ed, firstSelectedEObject, PropertyinstancesPackage.Literals.VALUE_PROPERTY_INSTANCE__VALUE, newValue);
		}
		
		return UnexecutableCommand.INSTANCE;
	}

	@Override
	public boolean isEnabled() {
		// Use some dummy value to check for executability of command
		newValue = "";
		return super.isEnabled();
	}
	
	/**
	 * Show dialog asking user for a string input.
	 * @param dialogTitle Dialog title
	 * @param dialogMessage Message
	 * @param initialValue Initial value that is printed in the field.
	 * @return New string that user has given
	 */
	public String showStringInputDialog(String dialogTitle, String dialogMessage, String initialValue) {
		Shell shell = Display.getCurrent().getActiveShell();
		InputDialog inputDialog = new InputDialog(shell, dialogTitle, dialogMessage, initialValue, null);
		int retDialog = inputDialog.open();
		if (retDialog == Window.OK) {
			return inputDialog.getValue();
		}
		
		return null;
	}
	
	/**
	 * Show dialog asking user for a name.
	 * @param initialValue Initial value to print in the field, e.g. current name
	 * @return Given name.
	 */
	public String showChangeValueDialog(String initialValue) {
		return showStringInputDialog(SET_VALUE_DIALOG_TITLE, SET_VALUE_DIALOG_MSG, initialValue);
	}
}
