/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.funcelectrical.ui.diagram.features.interfaceends;

import org.eclipse.emf.common.command.Command;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.ICreateContext;

import de.dlr.sc.virsat.graphiti.ui.diagram.feature.VirSatCreateFeature;
import de.dlr.sc.virsat.graphiti.util.DiagramHelper;
import de.dlr.sc.virsat.model.concept.types.structural.ABeanStructuralElementInstance;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.extension.funcelectrical.model.InterfaceEnd;
import de.dlr.sc.virsat.model.extension.funcelectrical.ui.diagram.InterfaceDiagramTypeProvider;
import de.dlr.sc.virsat.project.editingDomain.VirSatTransactionalEditingDomain;

/**
 * Feature for creating new interface ends in an interface diagram.
 * @author muel_s8
 *
 */

public class InterfaceEndCreateFeature extends VirSatCreateFeature {
	
    /**
     * Default Constructor.
     * @param fp the feature provider.
     */
    
	public InterfaceEndCreateFeature(IFeatureProvider fp) {
		super(fp, "InterfaceEnd", "Create a new InterfaceEnd");
	}

	@Override
	public boolean canCreate(ICreateContext context) {
		Object object = getBusinessObjectForPictogramElement(context.getTargetContainer());
		
		if (object instanceof ABeanStructuralElementInstance) {
			return super.canCreate(context);
		}
		
		return false;
	}
	
	@Override
	protected Command createCreateCommand(VirSatTransactionalEditingDomain ed, ICreateContext context) {
		ABeanStructuralElementInstance bean = (ABeanStructuralElementInstance) getBusinessObjectForPictogramElement(context.getTargetContainer());
		Concept concept = DiagramHelper.getConcept(ed, InterfaceDiagramTypeProvider.CONCEPT_ID);
		
		InterfaceEnd ie = new InterfaceEnd(concept);
		return bean.add(ed, ie);
	}
	
	@Override
	public String getCreateImageId() {
		return InterfaceEnd.FULL_QUALIFIED_CATEGORY_NAME;
	}
}
