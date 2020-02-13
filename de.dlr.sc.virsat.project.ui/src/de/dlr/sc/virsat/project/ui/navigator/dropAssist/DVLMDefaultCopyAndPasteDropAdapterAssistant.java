/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.project.ui.navigator.dropAssist;

import java.util.Collection;
import java.util.Collections;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.UnexecutableCommand;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.util.LocalSelectionTransfer;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DropTargetEvent;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.navigator.CommonDropAdapter;
import org.eclipse.ui.navigator.CommonViewer;

import de.dlr.sc.virsat.project.editingDomain.commands.VirSatEditingDomainClipBoard.ClipboardState;
import de.dlr.sc.virsat.project.editingDomain.VirSatTransactionalEditingDomain;
import de.dlr.sc.virsat.project.editingDomain.commands.VirSatPasteFromClipboardCommand;
import de.dlr.sc.virsat.project.ui.navigator.VirSatNavigator;

/**
 * VirSat Drop Assistant to copy and paste SEIs and CAs in the Navigator
 *
 */
public class DVLMDefaultCopyAndPasteDropAdapterAssistant extends ADVLMDropAdapaterAssistant {
	
	@Override
	public IStatus handleDrop(CommonDropAdapter aDropAdapter, DropTargetEvent aDropTargetEvent, Object aTarget) {
		IStatus superStatus = super.handleDrop(aDropAdapter, aDropTargetEvent, aTarget);
		
		if (superStatus.isOK()) {
			// Trying to set the selection in the Navigator to the target of the newly dropped object
			VirSatNavigator navigator = (VirSatNavigator) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().findView(VirSatNavigator.VIRSAT_NAVIGATOR_ID);
			if (navigator != null) {
				CommonViewer commonViewer = navigator.getCommonViewer();
				if (commonViewer != null) {
					commonViewer.setSelection(new StructuredSelection(aTarget));
					return Status.OK_STATUS;
				}
			}
		}
		
		return Status.CANCEL_STATUS;
	}

	@Override
	protected Command createDropCommand(VirSatTransactionalEditingDomain ed, Collection<Object> dragObjects, int operation, EObject dropObject) {
		Command dropCommand = UnexecutableCommand.INSTANCE;
		if (operation == DND.DROP_COPY) {
			dropCommand = VirSatPasteFromClipboardCommand.create(ed, dropObject, dragObjects, ClipboardState.COPY);
		} else if (operation == DND.DROP_MOVE) {
			dropCommand = VirSatPasteFromClipboardCommand.create(ed, dropObject, dragObjects, ClipboardState.CUT);
		}
		return dropCommand;
	}

	@SuppressWarnings("unchecked")
	@Override
	protected Collection<Object> getSelectedDragObjects() {
		ISelection selection = LocalSelectionTransfer.getTransfer().getSelection();
		if (selection instanceof IStructuredSelection) {
			IStructuredSelection structuredSelection = (IStructuredSelection) selection;
			return structuredSelection.toList();
		}
		return Collections.EMPTY_LIST;
	}
}
