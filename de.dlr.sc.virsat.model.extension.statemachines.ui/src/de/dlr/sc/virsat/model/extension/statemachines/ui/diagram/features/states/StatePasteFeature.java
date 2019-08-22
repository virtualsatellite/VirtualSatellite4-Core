/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.statemachines.ui.diagram.features.states;

import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IPasteContext;
import org.eclipse.graphiti.features.context.impl.AddContext;
import org.eclipse.graphiti.mm.algorithms.GraphicsAlgorithm;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.ui.features.AbstractPasteFeature;

import de.dlr.sc.virsat.graphiti.util.DiagramHelper;
import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;
import de.dlr.sc.virsat.model.dvlm.util.DVLMCopier;
import de.dlr.sc.virsat.model.extension.statemachines.model.State;
import de.dlr.sc.virsat.model.extension.statemachines.model.StateMachine;

/**
 * Feature for pasting states to the state machine diagram.
 * @author bell_er
 *
 */
public class StatePasteFeature extends AbstractPasteFeature  {
	/** 
	 * Default constructor.
	 * @param fp the feature provider.
	 */
	public StatePasteFeature(IFeatureProvider fp) {
		super(fp);
	}

	@Override
	public void paste(IPasteContext context) {
		// we already verified, that we paste directly in the diagram
		PictogramElement[] pes = context.getPictogramElements();
		for (PictogramElement pe: pes) {
			Object objectPe = getBusinessObjectForPictogramElement(pe);
				
			StateMachine stateMachine = ((StateMachine) objectPe);
			Object[] objects = getFromClipboard();			
			for (Object object : objects) {
				if (object instanceof CategoryAssignment) {
					CategoryAssignment copy = (CategoryAssignment) DVLMCopier.makeCopy((CategoryAssignment) object);		
					State s = new State(copy);
					stateMachine.getStates().add(s);
					AddContext ac = new AddContext();
					ac.setNewObject(objectPe);
					ac.setTargetContainer((ContainerShape) pe);
					GraphicsAlgorithm parent = pe.getGraphicsAlgorithm();
					ac.setLocation(context.getX() - parent.getX(), context.getY() - parent.getY()); 
					addGraphicalRepresentation(ac, copy);
				}
			}	
			
		}

	}

	@Override
	public boolean canPaste(IPasteContext context) {

		Object[] fromClipboard = getFromClipboard();
		if (fromClipboard == null || fromClipboard.length == 0) {
			return false;
		}
		
		for (PictogramElement pe : context.getPictogramElements()) {
			Object bo = getBusinessObjectForPictogramElement(pe);
			if (!DiagramHelper.hasBothWritePermission(bo, pe)) {
				return false;
			}
		}
		
		for (Object object : fromClipboard) {
			if (!(object instanceof CategoryAssignment)) {
				return false;
			}
		}
		return true;

	}
}
