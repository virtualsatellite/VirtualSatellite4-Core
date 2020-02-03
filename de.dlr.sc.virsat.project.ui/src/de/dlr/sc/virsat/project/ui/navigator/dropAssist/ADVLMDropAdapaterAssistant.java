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
import org.eclipse.swt.dnd.DropTargetEvent;
import org.eclipse.swt.dnd.TransferData;
import org.eclipse.ui.navigator.CommonDropAdapter;
import org.eclipse.ui.navigator.CommonDropAdapterAssistant;

import de.dlr.sc.virsat.project.editingDomain.VirSatEditingDomainRegistry;
import de.dlr.sc.virsat.project.editingDomain.VirSatTransactionalEditingDomain;

/**
 * Generic functionality to implement a DVLMDropAdapterAssitant
 *
 */
public abstract class ADVLMDropAdapaterAssistant extends CommonDropAdapterAssistant {

	protected VirSatTransactionalEditingDomain ed;
	protected Command validatedDropCommand;
	protected EObject dropEObjects;
	protected Collection<Object> dragObjects;

	/**
	 * Method to create the command which will be used for the validate and handle drop
	 * @param ed The editing domain for which to create the command
	 * @param dragObjects the objects which are dragged
	 * @param operation the type of operation complying with DND
	 * @param dropObject the object to which the drag operation is supposed to be dropped
	 * @return the command for executing the drag and drop operation
	 */
	protected abstract Command createDropCommand(VirSatTransactionalEditingDomain ed, Collection<Object> dragObjects, int operation, EObject dropObject);

	/**
	 * Method to determine which objects have been selected by the drag and drop operation
	 * @return a Collection containing the objects which are dragged
	 */
	protected abstract Collection<Object> getSelectedDragObjects();

	@Override
	public IStatus validateDrop(Object dropObject, int operation, TransferData transferType) {
		if (dropObject instanceof EObject) {
			dragObjects = getSelectedDragObjects();
			dropEObjects = (EObject) dropObject;
			ed = VirSatEditingDomainRegistry.INSTANCE.getEd(dropEObjects);
			validatedDropCommand = createDropCommand(
				ed,
				dragObjects,
				operation,
				dropEObjects
			);
			
			
			return validatedDropCommand.canExecute() ? Status.OK_STATUS : Status.CANCEL_STATUS;
		}
		return Status.CANCEL_STATUS;
	}

	@Override
	public IStatus handleDrop(CommonDropAdapter aDropAdapter, DropTargetEvent aDropTargetEvent, Object handleDropObject) {
		Collection<Object> handleDragObjects = getSelectedDragObjects();
		if (handleDropObject == dropEObjects && handleDragObjects.equals(dragObjects)) {
			ed.getVirSatCommandStack().execute(validatedDropCommand);
			return Status.OK_STATUS;
		}
		
		return Status.CANCEL_STATUS;
	}
}