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

import java.util.List;
import java.util.Map;
import java.util.function.Function;

import org.eclipse.core.commands.IHandler;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IResource;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.ide.undo.DeleteResourcesOperation;

import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import de.dlr.sc.virsat.model.ecore.VirSatEcoreUtil;
import de.dlr.sc.virsat.project.resources.VirSatResourceSet;
import de.dlr.sc.virsat.project.structure.command.CreateRemoveSeiWithFileStructureCommand;
import de.dlr.sc.virsat.project.ui.editingDomain.handler.AEditingDomainCommandHandler;
import de.dlr.sc.virsat.project.ui.structure.dialog.ReferencedDeleteDialog;

/**
 * Example CommandHandler to remove a selected SystemComponent and their children.
 * It is intended that this class will be replaced in the future together
 * with the new concepts regarding the structural and relational object type patterns
 */
public class DeleteStructuralElementInstanceHandler extends AEditingDomainCommandHandler implements IHandler {

	public static final Function<IFolder, DeleteResourcesOperation> DELETE_RESOURCE_OPERATION_FUNCTION = (seiFolder) -> new DeleteResourcesOperation(new IResource[] {seiFolder}, "Deleting SEI folder operation", true);
	
	@Override
	public void execute() {
		// Open the resource set and load all resources to start
		// searching for others referencing the deleted ones
		VirSatResourceSet virSatResourceSet = ed.getResourceSet();
		virSatResourceSet.loadAllResources();
		
		List<StructuralElementInstance> seisToBeDeleted = selectionHelper.getAllSelectedEObjectsOfType(StructuralElementInstance.class);
		
		// If there are references we need to ask if they should be deleted
		Map<EObject, List<EObject>> deleteObjectsWithExternalReferences = VirSatEcoreUtil.getReferencingObjectsForDelete(seisToBeDeleted, virSatResourceSet);
		if (!deleteObjectsWithExternalReferences.isEmpty()) {
			boolean confirmsDelete = ReferencedDeleteDialog.openQuestion(Display.getCurrent().getActiveShell(), deleteObjectsWithExternalReferences);
			if (!confirmsDelete) {
				return;
			}
		}
		
		Command deleteCommand = CreateRemoveSeiWithFileStructureCommand.create(seisToBeDeleted, DELETE_RESOURCE_OPERATION_FUNCTION);
		ed.getCommandStack().execute(deleteCommand);
	}
}
