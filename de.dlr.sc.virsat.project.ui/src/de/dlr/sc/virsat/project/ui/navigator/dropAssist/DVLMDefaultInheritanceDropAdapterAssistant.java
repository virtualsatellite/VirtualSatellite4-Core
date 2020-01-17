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

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.UnexecutableCommand;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.util.LocalSelectionTransfer;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.dnd.DND;

import de.dlr.sc.virsat.project.editingDomain.VirSatTransactionalEditingDomain;
import de.dlr.sc.virsat.project.editingDomain.commands.dnd.VirSatDragAndDropInheritanceCommandHelper;
import de.dlr.sc.virsat.project.editingDomain.commands.dnd.VirSatDragAndDropInheritanceCommandHelper.DndOperation;
import de.dlr.sc.virsat.project.ui.navigator.util.VirSatSelectionHelper;

/**
 * VirSat Drop Assistant to update inheritance with DND operations in the navigators
 *
 */
public class DVLMDefaultInheritanceDropAdapterAssistant extends ADVLMDropAdapaterAssistant {

	@Override
	protected Collection<Object> getSelectedDragObjects() {
		ISelection selection = LocalSelectionTransfer.getTransfer().getSelection();
		EObject firstEObject = new VirSatSelectionHelper(selection).getFirstEObject();
		return Collections.singleton(firstEObject);
	}
	
	@Override
	protected Command createDropCommand(VirSatTransactionalEditingDomain ed, Collection<Object> dragObjects, int operation, EObject dropObject) {
		if (operation == DND.DROP_COPY || operation == DND.DROP_MOVE) {
			return VirSatDragAndDropInheritanceCommandHelper.createDropCommand(
					ed,
					dragObjects,
					(operation == DND.DROP_COPY)
						? DndOperation.ADD_INHERITANCE
						: DndOperation.REPLACE_INHERITANCE,
					dropObject
			);
		} else {
			return UnexecutableCommand.INSTANCE;
		}
	}
}
