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

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.util.LocalSelectionTransfer;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DropTargetEvent;
import org.eclipse.swt.dnd.TransferData;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.navigator.CommonDropAdapter;
import org.eclipse.ui.navigator.CommonDropAdapterAssistant;
import org.eclipse.ui.navigator.CommonViewer;

import de.dlr.sc.virsat.project.editingDomain.VirSatEditingDomainRegistry;
import de.dlr.sc.virsat.project.editingDomain.VirSatTransactionalEditingDomain;
import de.dlr.sc.virsat.project.editingDomain.commands.VirSatEditingDomainClipBoard.ClipboardState;
import de.dlr.sc.virsat.project.editingDomain.commands.VirSatPasteFromClipboardCommand;
import de.dlr.sc.virsat.project.ui.navigator.VirSatNavigator;

/**
 * VirSat Drop Assistant to copy and paste SEIs and CAs in the Navigator
 *
 */
public class DVLMDefaultCopyAndPasteDropAdapterAssistant extends CommonDropAdapterAssistant {

	/**
	 * Method to create a Drop Command based on the Copy Paste functionality
	 * @param target The object to witch to drop to
	 * @param operation the type of DND operation
	 * @return the ccommand if it is creatable or null in case it could not be created
	 */
	private Command createDropCommand(Object target, int operation) {
		Command dropCommand = null;
		
		if (target instanceof EObject) {
			EObject eObject = (EObject) target;
			VirSatTransactionalEditingDomain virSatEd = VirSatEditingDomainRegistry.INSTANCE.getEd(eObject);
			ISelection selection = LocalSelectionTransfer.getTransfer().getSelection();
			
			if (selection instanceof IStructuredSelection) {
				IStructuredSelection structerdSelection = (IStructuredSelection) selection;
				
				@SuppressWarnings("unchecked")
				Collection<Object> dndObjects = structerdSelection.toList();
					
				if (operation == DND.DROP_COPY) {
					dropCommand = VirSatPasteFromClipboardCommand.create(virSatEd, eObject, dndObjects, ClipboardState.COPY);
				} else if (operation == DND.DROP_MOVE) {
					dropCommand = VirSatPasteFromClipboardCommand.create(virSatEd, eObject, dndObjects, ClipboardState.CUT);
				}
			}
		}
		return dropCommand;
	}
	
	@Override
	public IStatus validateDrop(Object target, int operation, TransferData transferType) {		
		Command dropCommand = createDropCommand(target, operation);
		if ((dropCommand != null) && (dropCommand.canExecute())) {
			return Status.OK_STATUS;
		} else {
			return Status.CANCEL_STATUS;
		}
	}

	@Override
	public IStatus handleDrop(CommonDropAdapter aDropAdapter, DropTargetEvent aDropTargetEvent, Object aTarget) {
		Command dropCommand = createDropCommand(aTarget, aDropTargetEvent.detail);
		if (aTarget instanceof EObject) {
			EObject eObject = (EObject) aTarget;
			VirSatTransactionalEditingDomain virSatEd = VirSatEditingDomainRegistry.INSTANCE.getEd(eObject);
			if (virSatEd != null) {
				virSatEd.getCommandStack().execute(dropCommand);
				
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
		}
		
		return Status.CANCEL_STATUS;
	}
}
