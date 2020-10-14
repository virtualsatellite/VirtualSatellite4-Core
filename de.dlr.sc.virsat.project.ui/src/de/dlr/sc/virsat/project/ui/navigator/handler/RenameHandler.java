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
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.ISelectionService;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;

import de.dlr.sc.virsat.model.dvlm.general.GeneralPackage;
import de.dlr.sc.virsat.model.dvlm.general.IName;
import de.dlr.sc.virsat.project.ui.navigator.util.VirSatSelectionHelper;

/**
 * Handler for Renaming elements from the navigator tree
 *
 */
public class RenameHandler extends AEMFCommandCommandHandler {

	public static final String SET_NAME_DIALOG_TITLE = "Change Name";
	public static final String SET_NAME_DIALOG_MSG = "Change the Name of this object to:";
	
	
	@Override
	public void execute() {
		// Get the info of where to execute this handler
		ISelectionService selectionService = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
		ISelection selection = selectionService.getSelection();

		VirSatSelectionHelper selectionHelper = new VirSatSelectionHelper(selection);
		EObject selectedObject = selectionHelper.getFirstEObject();
		
		if (selectedObject instanceof IName) {
			IName namedObject = (IName) selectedObject;
			String currentName = namedObject.getName();
			String newName = showChangeNameDialog(currentName); //$NON-NLS-1$
			if (newName != null && !newName.equals(currentName)) {
				Command cmd = SetCommand.create(ed, namedObject, GeneralPackage.Literals.INAME__NAME, newName);
				ed.getCommandStack().execute(cmd);
			}
		}
	}

	@Override
	protected Command createCommand() {
		return UnexecutableCommand.INSTANCE;
	}

	@Override
	public boolean isEnabled() {
		// Get the info of where to execute this handler
		ISelectionService selectionService = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
		ISelection selection = selectionService.getSelection();

		VirSatSelectionHelper selectionHelper = new VirSatSelectionHelper(selection);

		// Selection is graph
		if (selectionHelper.getFirstEObject() instanceof IName) {
			return true;
		}
		return false;
	}
	
	/**
	 * Show dialog asking user for a string input.
	 * @param dialogTitle Dialog title
	 * @param dialogMessage Message
	 * @param initialValue Initial value that is printed in the field.
	 * @return New string that user has given
	 */
	public static String showStringInputDialog(String dialogTitle, String dialogMessage, String initialValue) {
		String ret = null;
		Shell shell = getShell();
		InputDialog inputDialog = new InputDialog(shell, dialogTitle, dialogMessage, initialValue, null);
		int retDialog = inputDialog.open();
		if (retDialog == Window.OK) {
			ret = inputDialog.getValue();
		}
		return ret;
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
