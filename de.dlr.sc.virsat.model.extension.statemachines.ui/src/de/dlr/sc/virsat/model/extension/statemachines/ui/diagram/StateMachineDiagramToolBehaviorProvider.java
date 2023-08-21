/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.statemachines.ui.diagram;

import org.eclipse.graphiti.datatypes.ILocation;
import org.eclipse.graphiti.dt.IDiagramTypeProvider;
import org.eclipse.graphiti.features.context.IDoubleClickContext;
import org.eclipse.graphiti.features.custom.ICustomFeature;
import org.eclipse.graphiti.mm.algorithms.GraphicsAlgorithm;
import org.eclipse.graphiti.mm.algorithms.styles.Point;
import org.eclipse.graphiti.tb.DefaultToolBehaviorProvider;
import org.eclipse.graphiti.tb.IToolBehaviorProvider;

import de.dlr.sc.virsat.model.extension.statemachines.ui.diagram.features.StateMachineDoubleClickFeature;

/**
 * Implements the tool behavior provider to catch double click events
 *
 */
public class StateMachineDiagramToolBehaviorProvider extends DefaultToolBehaviorProvider implements IToolBehaviorProvider  {

	/**
	 * @param diagramTypeProvider
	 */
	public StateMachineDiagramToolBehaviorProvider(IDiagramTypeProvider diagramTypeProvider) {
		super(diagramTypeProvider);
	}
	
	@Override
	public ICustomFeature getDoubleClickFeature(IDoubleClickContext context) {
		ICustomFeature customFeature = new StateMachineDoubleClickFeature(getFeatureProvider());
		if (customFeature.canExecute(context)) {
			return customFeature;
		}
		return super.getDoubleClickFeature(context);
	}
	
	/**
	 * Transform a relative point to an absolute point by placing it relative to the
	 * given element.
	 * @param point
	 * @param ga
	 */
	public void placeRelativeTo(Point point, GraphicsAlgorithm ga) {
		ILocation peLocation = getAbsoluteLocation(ga);
		
		int relX = point.getX();
		int relY = point.getY();
		
		point.setX(peLocation.getX() + relX);
		point.setY(peLocation.getY() + relY);
	}

}
