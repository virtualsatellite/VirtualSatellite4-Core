/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.statemachines.ui.diagram.features.stateMachines;

import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IResizeShapeContext;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.Shape;

import de.dlr.sc.virsat.graphiti.ui.diagram.feature.VirSatResizeShapeFeature;
import de.dlr.sc.virsat.model.extension.statemachines.ui.diagram.features.states.StateAddFeature;
/**
 * resize feature for statemachines
 * @author bell_er
 *
 */
public class StateMachineResizeFeature extends VirSatResizeShapeFeature  {
	/**
	 * Default constructor
	 * @param fp the diagram type provider
	 */
	public StateMachineResizeFeature(IFeatureProvider fp) {
		super(fp);
	}

	@Override
	public boolean canResizeShape(IResizeShapeContext context) {
		boolean canResize = super.canResizeShape(context);

		if (!(context.getPictogramElement() instanceof ContainerShape)) {
			return false;
		}

		ContainerShape containerShape = (ContainerShape) context.getPictogramElement();
		int contextWidth = context.getWidth();
		int contextHeight = context.getHeight();
		int requiredHeight =  StateMachineAddFeature.DEFAULT_HEIGHT;
		int requiredWidth = StateMachineAddFeature.DEFAULT_WIDTH;

		for (Shape shape : containerShape.getChildren()) {
			// Look for deleted states
			if (shape instanceof ContainerShape) {
				requiredHeight = Math.max(shape.getGraphicsAlgorithm().getY(), requiredHeight);
				requiredWidth = Math.max(shape.getGraphicsAlgorithm().getX(), requiredWidth);
			}
		}

		requiredHeight += StateAddFeature.RADIUS;
		requiredWidth += StateAddFeature.RADIUS;

		int direction = context.getDirection();
		if (direction == IResizeShapeContext.DIRECTION_NORTH || direction == IResizeShapeContext.DIRECTION_SOUTH) {
			if (contextHeight < requiredHeight) {
				canResize = false;
			}
		} else if (direction == IResizeShapeContext.DIRECTION_WEST || direction == IResizeShapeContext.DIRECTION_EAST) {
			if (contextWidth < requiredWidth) {
				canResize = false;
			}
		}
		return canResize;
	}
}
