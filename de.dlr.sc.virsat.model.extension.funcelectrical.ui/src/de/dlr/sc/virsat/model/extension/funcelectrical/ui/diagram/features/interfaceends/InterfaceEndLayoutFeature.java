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

import org.eclipse.graphiti.datatypes.IDimension;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.ILayoutContext;
import org.eclipse.graphiti.mm.algorithms.Rectangle;
import org.eclipse.graphiti.mm.algorithms.Text;
import org.eclipse.graphiti.mm.algorithms.styles.Orientation;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.ui.services.GraphitiUi;

import de.dlr.sc.virsat.graphiti.ui.diagram.feature.VirSatLayoutFeature;
import de.dlr.sc.virsat.model.extension.funcelectrical.model.InterfaceEnd;

/**
 * Feature for layouting interface ends.
 * @author muel_s8
 *
 */

public class InterfaceEndLayoutFeature extends VirSatLayoutFeature {
	
	/**
	 * Default constructor.
	 * @param fp the feature provider.
	 */
	
	public InterfaceEndLayoutFeature(IFeatureProvider fp) {
		super(fp);
	}
	
	@Override
	public boolean canLayout(ILayoutContext context) {
		// return true, if pictogram element is linked to an interface end
		PictogramElement pe = context.getPictogramElement();
		Object businessObject = getBusinessObjectForPictogramElement(pe);
		return businessObject instanceof InterfaceEnd && super.canLayout(context);
	}

	@Override
	public boolean layout(ILayoutContext context) {
		boolean anythingChanged = false;
		
		ContainerShape shape = (ContainerShape) context.getPictogramElement();
    	
		Text text = (Text) shape.getGraphicsAlgorithm().getGraphicsAlgorithmChildren().get(0);
		Rectangle rect = (Rectangle) shape.getChildren().get(0).getGraphicsAlgorithm();
        
        // Fix sizes
        IDimension dimension = GraphitiUi.getUiLayoutService().calculateTextSize(text.getValue(), text.getFont());
        int width = dimension.getWidth();
        int totalWidth = width + InterfaceEndAddFeature.INTERFACE_END_WIDTH;
        shape.getGraphicsAlgorithm().setWidth(totalWidth);
        text.setWidth(width);
        
        // Check if interface end is on the left or right side
        // And correctly layout elements accordingly
        int parentWidth = shape.getContainer().getGraphicsAlgorithm().getWidth();
        
        // Get the mid point of the anchor independently of whether its left or right oriented
        int anchorMidX = shape.getGraphicsAlgorithm().getX() + totalWidth / 2;
        
        if (anchorMidX < parentWidth / 2) {
        	shape.getGraphicsAlgorithm().setX(0);
            text.setHorizontalAlignment(Orientation.ALIGNMENT_LEFT);
            text.setX(InterfaceEndAddFeature.INTERFACE_END_WIDTH);
            rect.setX(0);
        } else {
        	shape.getGraphicsAlgorithm().setX(parentWidth - totalWidth);
            text.setHorizontalAlignment(Orientation.ALIGNMENT_RIGHT);
            text.setX(0);
            rect.setX(width);
        }
        
		return anythingChanged;
	}

}
