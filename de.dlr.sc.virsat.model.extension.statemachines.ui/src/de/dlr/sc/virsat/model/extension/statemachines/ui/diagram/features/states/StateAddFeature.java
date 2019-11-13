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
import org.eclipse.graphiti.mm.algorithms.Ellipse;
import org.eclipse.graphiti.mm.algorithms.Image;
import org.eclipse.graphiti.mm.algorithms.Rectangle;
import org.eclipse.graphiti.mm.algorithms.Text;
import org.eclipse.graphiti.mm.algorithms.styles.Font;
import org.eclipse.graphiti.mm.algorithms.styles.Orientation;
import org.eclipse.graphiti.mm.pictograms.ChopboxAnchor;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.mm.pictograms.Shape;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.services.IGaService;
import org.eclipse.graphiti.services.IPeCreateService;
import org.eclipse.graphiti.util.IColorConstant;

import de.dlr.sc.virsat.graphiti.ui.diagram.feature.VirSatAddShapeFeature;
import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;
import de.dlr.sc.virsat.model.extension.statemachines.model.State;
import de.dlr.sc.virsat.model.extension.statemachines.model.StateMachine;
/**
 * Feature for adding states to the state machine diagram.
 * @author bell_er
 *
 */

public class StateAddFeature extends VirSatAddShapeFeature {
	public static  final int RADIUS = 50;
	
	public static final int DEFAULT_WIDTH = 65;	
	public static final int DEFAULT_HEIGHT = 50;	
	public static final int IMAGE_WIDTH = 15;	
	
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
				return sm.getStates().contains(addedState) && super.canAdd(context);
			}		
		}	

		return false;	
	}
	
	@Override
	public PictogramElement add(IAddContext context) {
		State s = new State((CategoryAssignment) context.getNewObject());
		ContainerShape targetContainer = context.getTargetContainer();	
		StateMachine stateMachine = s.getParentCaBeanOfClass(StateMachine.class);
		State initialState = stateMachine.getInitialState();
		
		boolean isInitial = s.equals(initialState);
		IPeCreateService peCreateService = Graphiti.getPeCreateService();
        
		ContainerShape containerShape = peCreateService.createContainerShape(targetContainer, true);
        link(containerShape, s);
        IGaService gaService = Graphiti.getGaService();
        
        
        Rectangle invisibleRectangle = gaService.createInvisibleRectangle(containerShape);
		int width = getRectangleWidth(isInitial);
		int height = DEFAULT_HEIGHT;
		int x = context.getX() - width;
		gaService.setLocationAndSize(invisibleRectangle, x, context.getY(), width, height);
        
        
        ContainerShape ellipseShape = peCreateService.createContainerShape(containerShape, true);
        
        Ellipse ellipse = gaService.createEllipse(ellipseShape);
        ellipse.setBackground(manageColor(IColorConstant.RED));
        ellipse.setLineVisible(false);
        ellipseShape.setActive(false);
        gaService.setLocationAndSize(ellipse, getEllipsePlacement(isInitial), 0, RADIUS, RADIUS);

        ChopboxAnchor boxAnchor = peCreateService.createChopboxAnchor(containerShape);
        boxAnchor.setReferencedGraphicsAlgorithm(ellipse);
    	link(boxAnchor, s);			
    	
    	Shape imageShape =  peCreateService.createShape(containerShape, false);	
    	Image image  = gaService.createImage(imageShape, StateMachine.PROPERTY_INITIALSTATE);
    	gaService.setLocationAndSize(image, 0, 0, IMAGE_WIDTH, DEFAULT_HEIGHT);	
    	imageShape.setVisible(isInitial);
    	link(imageShape, s);
    	Shape nameShape = peCreateService.createShape(containerShape, false);	
    	Text nameText = gaService.createText(nameShape, s.getName());		
    	nameText.setForeground(manageColor(IColorConstant.BLACK));	
    	nameText.setHorizontalAlignment(Orientation.ALIGNMENT_CENTER);	
    	nameText.setVerticalAlignment(Orientation.ALIGNMENT_CENTER);
    	Font font = gaService.manageDefaultFont(getDiagram(), false, true);		
    	nameText.setFont(font);			
    	gaService.setLocationAndSize(nameText, getEllipsePlacement(isInitial), 0, ellipse.getWidth(), ellipse.getHeight());	
    	link(nameShape, s);
    	IDirectEditingInfo directEditingInfo = getFeatureProvider().getDirectEditingInfo();
    	// set container shape for direct editing after object creation
    	directEditingInfo.setMainPictogramElement(containerShape);
    	// set shape and graphics algorithm where the editor for
    	// direct editing shall be opened after object creation
    	directEditingInfo.setPictogramElement(containerShape);
    	directEditingInfo.setGraphicsAlgorithm(nameText);
    	
    	return null;
	}
	
	/** 
	 * if it is an initial state first the image is drawn then the ellipse
	 * @param isInitial true if the state is initial
	 * @return the point where ellipse starts
	 */
	private int getEllipsePlacement(boolean isInitial) {
		if (isInitial) {
			return IMAGE_WIDTH;
		} else {
			return 0; 
		}
	}
	
	/** 
	 * if it is an initial state the rectangle is bigger because it contains an image
	 * @param isInitial true if the state is initial
	 * @return the width of the rectangle
	 */
	private int getRectangleWidth(boolean isInitial) {
		if (isInitial) {
			return DEFAULT_WIDTH;
		} else {
			return DEFAULT_WIDTH - IMAGE_WIDTH; 
		}
	}
}
