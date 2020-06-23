/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.graphiti.ui.diagram.feature;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IDeleteContext;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.ui.features.DefaultDeleteFeature;

import de.dlr.sc.virsat.graphiti.util.DiagramHelper;
import de.dlr.sc.virsat.model.concept.types.IBeanDelete;
import de.dlr.sc.virsat.model.concept.types.structural.IBeanStructuralElementInstance;
import de.dlr.sc.virsat.project.structure.command.CreateRemoveSeiWithFileStructureCommand;
import de.dlr.sc.virsat.project.ui.navigator.handler.DeleteStructuralElementInstanceHandler;
/**
 * Feature handling the deletion of beans
 * @author bell_er
 *
 */
public class BeanDeleteFeature extends DefaultDeleteFeature {
	/**
	 * Default constructor.
	 * @param fp the feature provider.
	 */
	
	public BeanDeleteFeature(IFeatureProvider fp) {
		super(fp);
	}

	@Override
	public boolean canDelete(IDeleteContext context) {
		PictogramElement pe = context.getPictogramElement();
		Object bo = getBusinessObjectForPictogramElement(pe);
		if (bo instanceof IBeanDelete) {
			EditingDomain ed = getDiagramBehavior().getEditingDomain();
			Command deleteCommand = createDeleteCommand(ed, bo);
			return DiagramHelper.hasDiagramWritePermission(pe) && deleteCommand.canExecute();
		}
		
		return false;
	}
	
	@Override
	protected void deleteBusinessObject(Object bo) {
		EditingDomain ed = getDiagramBehavior().getEditingDomain();

		Command deleteCommand = createDeleteCommand(ed, bo);
		ed.getCommandStack().execute(deleteCommand);
	}
	
	/**
	 * Creates the delete command command for a given business object
	 * @param ed the editing domain
	 * @param bo the business object
	 * @return the created delete command
	 */
	protected Command createDeleteCommand(EditingDomain ed, Object bo) {
		if (bo instanceof IBeanStructuralElementInstance) {
			// For Structural Element Instances we can't just delete them from the model
			// we also have to remove the files so we have to create the proper command!
			IBeanStructuralElementInstance beanSei = (IBeanStructuralElementInstance) bo;
			return CreateRemoveSeiWithFileStructureCommand
				.create(beanSei.getStructuralElementInstance(), DeleteStructuralElementInstanceHandler.DELETE_RESOURCE_OPERATION_FUNCTION);
		} else {
			// This feature can only be applied to IBeanDelete so we can guarantee the type
			IBeanDelete bean = (IBeanDelete) bo;
			return bean.delete(ed);	
		}
	}
}
