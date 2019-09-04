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

import org.eclipse.graphiti.datatypes.IDimension;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.ILayoutFeature;
import org.eclipse.graphiti.features.context.ILayoutContext;
import org.eclipse.graphiti.features.context.impl.LayoutContext;
import org.eclipse.graphiti.mm.algorithms.GraphicsAlgorithm;
import org.eclipse.graphiti.mm.algorithms.Polyline;
import org.eclipse.graphiti.mm.algorithms.styles.Point;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.mm.pictograms.Shape;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.services.IGaService;

import de.dlr.sc.virsat.graphiti.ui.diagram.feature.VirSatLayoutFeature;
import de.dlr.sc.virsat.model.concept.types.structural.ABeanStructuralElementInstance;
import de.dlr.sc.virsat.model.extension.funcelectrical.model.InterfaceEnd;
import de.dlr.sc.virsat.model.extension.funcelectrical.ui.diagram.utils.DiagramUtils;

/**
 * Feature for layouting elements
 * @author muel_s8
 *
 */

public class ElementLayoutFeature extends VirSatLayoutFeature {

	/**
	 * Default Constructor
	 * @param fp the feature provider
	 */
	
	public ElementLayoutFeature(IFeatureProvider fp) {
		super(fp);
	}

	@Override
	public boolean canLayout(ILayoutContext context) {
		// return true, if pictogram element is linked to an element
		PictogramElement pe = context.getPictogramElement();
		if (!(pe instanceof ContainerShape)) {
			return false;
		}
		
		Object bo = getBusinessObjectForPictogramElement(pe);
		return bo instanceof ABeanStructuralElementInstance && super.canLayout(context);
	}

	@Override
	public boolean layout(ILayoutContext context) {
		boolean anythingChanged = false;
		
		ContainerShape containerShape = (ContainerShape) context.getPictogramElement();
		GraphicsAlgorithm containerGa = containerShape.getGraphicsAlgorithm();
		
		int containerWidth = containerGa.getWidth();
		int containerHeight = containerGa.getHeight();
		IGaService gaService = Graphiti.getGaService();
		// Adjust the size of the attached shapes
		for (Shape shape : containerShape.getChildren()) {
			GraphicsAlgorithm graphicsAlgorithm = shape.getGraphicsAlgorithm();
			
			IDimension size = gaService.calculateSize(graphicsAlgorithm);
			if (containerWidth + ElementAddFeature.PADDING_X * 2 != size.getWidth()) {
				if (graphicsAlgorithm instanceof Polyline) {
					Polyline polyline = (Polyline) graphicsAlgorithm;
					Point secondPoint = polyline.getPoints().get(1);
					Point newSecondPoint = gaService.createPoint(containerWidth - ElementAddFeature.PADDING_X, secondPoint.getY());
					polyline.getPoints().set(1, newSecondPoint);
					anythingChanged = true;
				} else {
					Object bo = getBusinessObjectForPictogramElement(shape);
					
					if (!(bo instanceof InterfaceEnd)) {
						gaService.setWidth(graphicsAlgorithm, containerWidth - ElementAddFeature.PADDING_X * 2);
						anythingChanged = true;
					} else {
			        	LayoutContext layoutContext = new LayoutContext(shape);
			        	ILayoutFeature feature = getFeatureProvider().getLayoutFeature(layoutContext);
			        	feature.layout(layoutContext);
					}
				}
			}
		}	

		// Adjust the size for the attached graphics algorithms
		for (GraphicsAlgorithm gaChild : containerShape.getGraphicsAlgorithm().getGraphicsAlgorithmChildren()) {
			IDimension size = gaService.calculateSize(gaChild);
			int supposedWith = containerWidth - ElementAddFeature.PADDING_X * 2;
			if (supposedWith != size.getWidth()) {
				gaService.setWidth(gaChild, supposedWith);
				anythingChanged = true;
			}
			
			int supposedHeight = containerHeight - ElementAddFeature.PADDING_Y * 2;
			if (supposedHeight != size.getHeight()) {
				gaService.setHeight(gaChild, supposedHeight);
				anythingChanged = true;
			}
		}
        // reposition all the anchors 
		DiagramUtils.repositionShapes(containerShape, getFeatureProvider());
		return anythingChanged;
	}

}
