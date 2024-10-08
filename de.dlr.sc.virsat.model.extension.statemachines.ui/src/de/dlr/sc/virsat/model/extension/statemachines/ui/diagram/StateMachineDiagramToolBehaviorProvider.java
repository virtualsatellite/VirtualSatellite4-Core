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
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.tb.ColorDecorator;
import org.eclipse.graphiti.mm.algorithms.GraphicsAlgorithm;
import org.eclipse.graphiti.mm.algorithms.styles.Point;
import org.eclipse.graphiti.tb.DefaultToolBehaviorProvider;
import org.eclipse.graphiti.tb.IDecorator;
import org.eclipse.graphiti.tb.IToolBehaviorProvider;
import org.eclipse.graphiti.util.ColorConstant;
import org.eclipse.graphiti.util.IColorConstant;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.PlatformUI;

import de.dlr.sc.virsat.model.extension.statemachines.model.State;
import de.dlr.sc.virsat.model.extension.statemachines.model.Transition;
import de.dlr.sc.virsat.model.extension.statemachines.statespace.StateSpaceExplorer;
import de.dlr.sc.virsat.model.extension.statemachines.statespace.TraceState;
import de.dlr.sc.virsat.model.extension.statemachines.ui.diagram.features.StateMachineDoubleClickFeature;
import de.dlr.sc.virsat.model.extension.statemachines.ui.views.SimulatorView;

/**
 * Implements the tool behavior provider to catch double click events
 *
 */
public class StateMachineDiagramToolBehaviorProvider extends DefaultToolBehaviorProvider implements IToolBehaviorProvider {

	private static final IColorConstant SELECTED_STATE_BACKGROUND = new ColorConstant(255, 255, 255);
	private static final IColorConstant SELECTED_TRANSITION_FOREGROUND = new ColorConstant(255, 128, 0);
	
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

	@Override
	public IDecorator[] getDecorators(PictogramElement pe) {
		var activeWorkbenchWindow = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
		
		if (activeWorkbenchWindow.getActivePage().findView(SimulatorView.ID) != null) {
			var selectionService = activeWorkbenchWindow.getSelectionService();
			var selection = selectionService.getSelection(SimulatorView.ID);
			
			if (selection != null && selection instanceof IStructuredSelection) {		
				var featureProvider = getFeatureProvider();
				var bo = featureProvider.getBusinessObjectForPictogramElement(pe);
				
				for (var selectedElement : (IStructuredSelection) selection) {
					if (selectedElement instanceof TraceState && bo instanceof State) {
						var state = (State) bo;
						var localStates = ((TraceState) selectedElement).getSystemState().getLocalStates();

						if (localStates.contains(state)) {
							var decorator = new ColorDecorator();
							decorator.setBackgroundColor(SELECTED_STATE_BACKGROUND);
							return new IDecorator[] { decorator };
						}
					} else if (selectedElement instanceof StateSpaceExplorer.SystemTransition && bo instanceof Transition) {
						var transition = (Transition) bo;
						var localTransitions = ((StateSpaceExplorer.SystemTransition) selectedElement).getLocalTransitions();
						
						if (localTransitions.contains(transition)) {
							var decorator = new ColorDecorator();
							decorator.setForegroundColor(SELECTED_TRANSITION_FOREGROUND);
							return new IDecorator[] { decorator };
						}
					}
				}
			}
		}
		
		return super.getDecorators(pe);
	}
}
