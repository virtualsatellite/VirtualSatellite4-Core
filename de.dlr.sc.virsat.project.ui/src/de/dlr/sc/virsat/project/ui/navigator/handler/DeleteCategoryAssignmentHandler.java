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
import java.util.Set;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.command.DeleteCommand;
import org.eclipse.swt.widgets.Display;

import de.dlr.sc.virsat.model.concept.types.category.IBeanCategoryAssignment;
import de.dlr.sc.virsat.model.concept.types.factory.BeanCategoryAssignmentFactory;
import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;
import de.dlr.sc.virsat.model.ecore.VirSatEcoreUtil;
import de.dlr.sc.virsat.project.resources.VirSatResourceSet;
import de.dlr.sc.virsat.project.ui.structure.dialog.ReferencedDeleteDialog;

/**
 * Handler to delete selected category assignments
 */
public class DeleteCategoryAssignmentHandler extends AEMFCommandCommandHandler {

	private BeanCategoryAssignmentFactory beanCaFactory = new BeanCategoryAssignmentFactory();
	
	@Override
	protected Command createCommand() {
		
		List<CategoryAssignment> selectedCas = selectionHelper.getAllSelectedEObjectsOfType(CategoryAssignment.class);
		
		CompoundCommand deleteCommands = new CompoundCommand();
		
		for (CategoryAssignment ca : selectedCas) {
			try {
				IBeanCategoryAssignment beanCa = beanCaFactory.getInstanceFor(ca);
				Command deleteCommand = beanCa.delete(ed);
				deleteCommands.append(deleteCommand);
			} catch (CoreException e) {
				Command deleteCommand = DeleteCommand.create(ed, ca);
				deleteCommands.append(deleteCommand);
			}
		}
		
		return deleteCommands;
	}
	
	@Override
	public void execute() {
		// Open the resource set and load all resources to start
		// searching for others referencing the deleted ones
		VirSatResourceSet virSatResourceSet = ed.getResourceSet();
		virSatResourceSet.loadAllResources();
		
		Map<EObject, Set<EObject>> deleteObjectsWithExternalReferences = VirSatEcoreUtil.getReferencingObjectsForDelete(selectionHelper.getAllSelectedEObjects(), virSatResourceSet);
		
		// If there are references we need to ask if they should be deleted
		if (!deleteObjectsWithExternalReferences.isEmpty()) {
			boolean confirmsDelete = ReferencedDeleteDialog.openQuestion(Display.getCurrent().getActiveShell(), deleteObjectsWithExternalReferences);
			if (!confirmsDelete) {
				return;
			}
		}
		
		super.execute();
	}
}
