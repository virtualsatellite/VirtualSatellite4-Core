/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.uiengine.ui.handler;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.handlers.HandlerUtil;

import de.dlr.sc.virsat.model.ui.editor.input.VirSatUriEditorInput;
import de.dlr.sc.virsat.project.ui.navigator.util.VirSatSelectionHelper;

/**
 * this class handle the open custom editor and implements the interface handler
 * @author leps_je
 *
 */
public class OpenCustomEditorHandler extends AbstractHandler implements IHandler {
	
	public static final String COMMAND_HANDLER_ID = "de.dlr.sc.virsat.model.editor.command.openCustomEditor";
	
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		
		// get file from selection via the event
		ISelection selection = HandlerUtil.getCurrentSelection(event);
		VirSatSelectionHelper selectionHelper = new VirSatSelectionHelper(selection);
		
		EObject eObject = selectionHelper.getFirstEObject();
		
		VirSatUriEditorInput.openDrillDownEditor(eObject);
		
		return null;
	}
}
