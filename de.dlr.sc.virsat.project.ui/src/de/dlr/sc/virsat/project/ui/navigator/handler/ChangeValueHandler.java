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
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;

import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.PropertyinstancesPackage;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ValuePropertyInstance;

/**
 * Handler for Renaming elements from the navigator tree
 *
 */
public class ChangeValueHandler extends AEMFCommandCommandHandler {

	public static final String SET_NAME_DIALOG_TITLE = "Change Value";
	public static final String SET_NAME_DIALOG_MSG = "New value:";
	
	private String newValue;
	
	@Override
	public void execute() {
		ValuePropertyInstance vpi = (ValuePropertyInstance) firstSelectedEObject;
		String currentValue = vpi.getValue();
		newValue = showChangeNameDialog(currentValue);
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
	public static String showStringInputDialog(String dialogTitle, String dialogMessage, String initialValue) {
		InputDialog inputDialog = new InputDialog(getShell(), dialogTitle, dialogMessage, initialValue, null);
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
	public static String showChangeNameDialog(String initialValue) {
		return showStringInputDialog(SET_NAME_DIALOG_TITLE, SET_NAME_DIALOG_MSG, initialValue);
	}
	
	/**
	 * Get the shell of the current workbench
	 * @return the shell
	 */
	public static Shell getShell() {
		IWorkbench workbench = PlatformUI.getWorkbench();
		IWorkbenchWindow workbenchWindow = workbench.getActiveWorkbenchWindow();
		return workbenchWindow.getShell();
	}
}
