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

import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.ILayoutFeature;
import org.eclipse.graphiti.features.context.IMoveShapeContext;
import org.eclipse.graphiti.features.context.impl.LayoutContext;
import org.eclipse.graphiti.mm.algorithms.GraphicsAlgorithm;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.services.IGaService;

import de.dlr.sc.virsat.graphiti.ui.diagram.feature.VirSatMoveShapeFeature;
import de.dlr.sc.virsat.model.extension.funcelectrical.model.InterfaceEnd;
import de.dlr.sc.virsat.model.extension.funcelectrical.ui.diagram.features.elements.ElementAddFeature;
import de.dlr.sc.virsat.model.extension.funcelectrical.ui.diagram.utils.DiagramUtils;

/**
 * Feature for handling the movement of interface ends.
 * @author muel_s8
 *
 */

public class InterfaceEndMoveFeature extends VirSatMoveShapeFeature {

	/**
	 * Default constructor.
	 * @param fp the feature provider.
	 */
	
	public InterfaceEndMoveFeature(IFeatureProvider fp) {
		super(fp);
	}
	
	@Override
	public boolean canMoveShape(IMoveShapeContext context) {
		if (context.getTargetContainer() != context.getSourceContainer()) {
			// Moving an InterfaceEnd from one element to another is currently not supported
			return false;
		}
		
		Object object = getBusinessObjectForPictogramElement(context.getShape());
		return object instanceof InterfaceEnd && super.canMoveShape(context);
	}
	
	@Override
	protected void postMoveShape(IMoveShapeContext context) {
        // Fix internal layouting of the interface end
    	LayoutContext layoutContext = new LayoutContext(context.getShape().getGraphicsAlgorithm().getPictogramElement());
    	ILayoutFeature feature = getFeatureProvider().getLayoutFeature(layoutContext);
    	feature.layout(layoutContext);
       
		ContainerShape containerShape = (ContainerShape) context.getTargetContainer();
		GraphicsAlgorithm containerGa = containerShape.getGraphicsAlgorithm();
		IGaService gaService = Graphiti.getGaService();
		
		int requiredHeight = DiagramUtils.getRequiredHeight(containerShape, getFeatureProvider());
    	if (containerGa.getHeight() < requiredHeight) {
			for (GraphicsAlgorithm gaChild : containerShape.getGraphicsAlgorithm().getGraphicsAlgorithmChildren()) {
				gaService.setHeight(gaChild, requiredHeight -  ElementAddFeature.PADDING_Y);
			}
			gaService.setHeight(containerGa, requiredHeight +  ElementAddFeature.PADDING_Y);
			
    	}
    	 // reposition all the anchors 
    	DiagramUtils.repositionInterfaceEndShapes(containerShape, getFeatureProvider());
	}
}