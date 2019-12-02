/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.funcelectrical.ui.diagram.features.elements;

import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IResizeShapeContext;
import org.eclipse.graphiti.mm.algorithms.styles.Font;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.services.IGaService;
import de.dlr.sc.virsat.graphiti.ui.diagram.feature.VirSatResizeShapeFeature;
import de.dlr.sc.virsat.model.concept.types.structural.ABeanStructuralElementInstance;
import de.dlr.sc.virsat.model.extension.funcelectrical.ui.diagram.utils.DiagramUtils;

/**
 * 
 * @author bell_er
 *
 */
public class ElementResizeFeature extends VirSatResizeShapeFeature {
	
	/**
	 * Default constructor
	 * @param fp the feature provider
	 */
	
	public ElementResizeFeature(IFeatureProvider fp) {
		super(fp);
	}
	
    @Override
    public boolean canResizeShape(IResizeShapeContext context) {
        boolean canResize = super.canResizeShape(context);
        
        if (!(context.getPictogramElement() instanceof ContainerShape)) {
        	return false;
        }
        
        ContainerShape containerShape = (ContainerShape) context.getPictogramElement();
        // calculate the minimum width
        int minimumWidth = 0;
        Object object = getBusinessObjectForPictogramElement(containerShape);
		if (object instanceof ABeanStructuralElementInstance) {
			IGaService gaService = Graphiti.getGaService();
			Font font = gaService.manageDefaultFont(getDiagram(), false, true);
			minimumWidth = DiagramUtils.neededWidth((ABeanStructuralElementInstance) object, font);
		} 
        
 		int contextWidth = context.getWidth();
		int contextHeight = context.getHeight();
		int requiredHeight =  DiagramUtils.getRequiredHeight(containerShape, getFeatureProvider());
		
		int direction = context.getDirection();
		
		if (direction != IResizeShapeContext.DIRECTION_UNSPECIFIED) {
			if (contextWidth < minimumWidth) {
				canResize = false;
			} else if (contextHeight < requiredHeight) {
				canResize = false;
			}
		}
		return canResize;
    }

}
