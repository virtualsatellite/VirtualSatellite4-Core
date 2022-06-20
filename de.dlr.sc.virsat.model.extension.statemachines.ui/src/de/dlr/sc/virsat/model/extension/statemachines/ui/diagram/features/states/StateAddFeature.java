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

import org.eclipse.graphiti.features.IDirectEditingInfo;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IAddContext;
import org.eclipse.graphiti.mm.algorithms.AbstractText;
import org.eclipse.graphiti.mm.algorithms.Ellipse;
import org.eclipse.graphiti.mm.algorithms.Image;
import org.eclipse.graphiti.mm.algorithms.Rectangle;
import org.eclipse.graphiti.mm.algorithms.styles.Font;
import org.eclipse.graphiti.mm.algorithms.styles.Orientation;
import org.eclipse.graphiti.mm.pictograms.ChopboxAnchor;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.mm.pictograms.Shape;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.services.IGaService;
import org.eclipse.graphiti.services.IPeCreateService;
import org.eclipse.graphiti.util.ColorConstant;
import org.eclipse.graphiti.util.IColorConstant;

import de.dlr.sc.virsat.graphiti.label.MultilineLabelFormatter;
import de.dlr.sc.virsat.graphiti.util.DiagramHelper;
import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;
import de.dlr.sc.virsat.model.extension.statemachines.model.State;
import de.dlr.sc.virsat.model.extension.statemachines.model.StateMachine;
import de.dlr.sc.virsat.model.extension.statemachines.ui.diagram.features.stateMachines.StateMachineAddFeature;
/**
 * Feature for adding states to the state machine diagram.
 * @author bell_er
 *
 */

public class StateAddFeature extends StateMachineAddFeature {
	public static  final int RADIUS = 65;
	
	public static final int INDEX_ELLIPSE = 0;
	public static final int INDEX_INITIAL_ARROW = 1;
	public static final int INDEX_TEXT = 2;
	
	public static final int INITIAL_ARROW_SIZE = 15;
	public static final int CONTAINER_SIZE = RADIUS + INITIAL_ARROW_SIZE * 2;
	
	public static final IColorConstant ELEMENT_BACKGROUND =	new ColorConstant(232, 94, 74);
	
	/** 
	 * Default constructor.
	 * @param fp the feature provider.
	 */
	public StateAddFeature(IFeatureProvider fp) {
		super(fp);
	}
	
	@Override
	public boolean canAdd(IAddContext context) {
		Object object = context.getNewObject();
		if (object instanceof CategoryAssignment) {	
			CategoryAssignment ca = (CategoryAssignment) object;
			if (!ca.getType().getName().equals(State.class.getSimpleName())) {	
				return false;
			}
			
			State addedState = new State(ca);
			
			// Check if this state is contained by the StateMachine
			Object target = getBusinessObjectForPictogramElement(context.getTargetContainer());	
			if (target instanceof StateMachine) {
				ContainerShape s = context.getTargetContainer();
				for (Shape shape : s.getChildren()) {
					if (shape instanceof ContainerShape) {
						PictogramElement pe = shape.getGraphicsAlgorithm().getPictogramElement();
						State state = (State) getBusinessObjectForPictogramElement(pe);
						if (state != null && state.equals(addedState)) {
							return false;
						}
					}
				}	
				
				StateMachine sm = (StateMachine) target;
				return sm.getStates().contains(addedState) && DiagramHelper.hasDiagramWritePermission(context.getTargetContainer());
			}		
		}	

		return super.canAdd(context);	
	}
	
	@Override
	public PictogramElement add(IAddContext context) {
		// Check if this state is contained by the StateMachine
		ContainerShape targetContainer = context.getTargetContainer();	
		Object target = getBusinessObjectForPictogramElement(targetContainer);	
		if (target instanceof StateMachine) {
			State state = new State((CategoryAssignment) context.getNewObject());
			
			StateMachine stateMachine = state.getParentCaBeanOfClass(StateMachine.class);
			State initialState = stateMachine.getInitialState();
			
			boolean isInitial = state.equals(initialState);
			IPeCreateService peCreateService = Graphiti.getPeCreateService();
	        
			ContainerShape containerShape = peCreateService.createContainerShape(targetContainer, true);
	        link(containerShape, state);
	        IGaService gaService = Graphiti.getGaService();
	        
	        
	        Rectangle invisibleRectangle = gaService.createInvisibleRectangle(containerShape);
			gaService.setLocationAndSize(invisibleRectangle, context.getX(), context.getY(), CONTAINER_SIZE, CONTAINER_SIZE);
	        
	        ContainerShape ellipseShape = peCreateService.createContainerShape(containerShape, true);
	        
	        Ellipse ellipse = gaService.createEllipse(ellipseShape);
	        ellipse.setBackground(manageColor(ELEMENT_BACKGROUND));
	        ellipse.setLineVisible(false);
	        ellipseShape.setActive(false);
	
	        ChopboxAnchor boxAnchor = peCreateService.createChopboxAnchor(containerShape);
	        boxAnchor.setReferencedGraphicsAlgorithm(ellipse);
	    	link(boxAnchor, state);			
	    	
	    	Shape imageShape =  peCreateService.createShape(containerShape, false);	
	    	Image image  = gaService.createImage(imageShape, StateMachine.PROPERTY_INITIALSTATE);
	    	gaService.setLocationAndSize(image, 0, 0, INITIAL_ARROW_SIZE, INITIAL_ARROW_SIZE);	
	    	imageShape.setVisible(isInitial);
	    	link(imageShape, state);
	    	
	    	Shape nameShape = peCreateService.createShape(containerShape, false);	
	    	String formattedText = new MultilineLabelFormatter().getLabel(state.getName());
	    	AbstractText nameText = gaService.createMultiText(nameShape, formattedText);		
	    	nameText.setForeground(manageColor(IColorConstant.BLACK));	
	    	nameText.setHorizontalAlignment(Orientation.ALIGNMENT_CENTER);	
	    	nameText.setVerticalAlignment(Orientation.ALIGNMENT_CENTER);
	    	Font font = gaService.manageDefaultFont(getDiagram(), false, true);		
	    	nameText.setFont(font);			
	    	link(nameShape, state);
	    	
	    	IDirectEditingInfo directEditingInfo = getFeatureProvider().getDirectEditingInfo();
	    	// set container shape for direct editing after object creation
	    	directEditingInfo.setMainPictogramElement(containerShape);
	    	// set shape and graphics algorithm where the editor for
	    	// direct editing shall be opened after object creation
	    	directEditingInfo.setPictogramElement(containerShape);
	    	directEditingInfo.setGraphicsAlgorithm(nameText);
	    	
	    	layoutPictogramElement(containerShape);
    	
	    	return containerShape;
		}
		
		return super.add(context);
	}
}
