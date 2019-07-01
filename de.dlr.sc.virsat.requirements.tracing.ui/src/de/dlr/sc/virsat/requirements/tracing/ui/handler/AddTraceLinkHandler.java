/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.requirements.tracing.ui.handler;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.handlers.HandlerUtil;

import de.dlr.sc.virsat.requirements.tracing.ui.wizard.NewTraceToExisitingArtifactWizard;

/**
 * @author Tobias Franz
	tobias.franz@dlr.de
 *
 */
public class AddTraceLinkHandler extends AbstractHandler implements IHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		ISelection selection = HandlerUtil.getCurrentSelectionChecked(event);		
		Shell shell = HandlerUtil.getActiveWorkbenchWindow(event).getShell();
		NewTraceToExisitingArtifactWizard wizard = new NewTraceToExisitingArtifactWizard();
		wizard.setSelection(selection);
		WizardDialog dialog = new WizardDialog(shell, wizard);
		dialog.open(); 			
		return null;
	}
}

