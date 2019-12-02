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

import org.eclipse.graphiti.datatypes.IDimension;
import org.eclipse.graphiti.features.IAddFeature;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IAddContext;
import org.eclipse.graphiti.features.context.impl.AddContext;
import org.eclipse.graphiti.mm.algorithms.Polyline;
import org.eclipse.graphiti.mm.algorithms.RoundedRectangle;
import org.eclipse.graphiti.mm.algorithms.Text;
import org.eclipse.graphiti.mm.algorithms.styles.Font;
import org.eclipse.graphiti.mm.algorithms.styles.Orientation;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.mm.pictograms.Shape;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.services.IGaService;
import org.eclipse.graphiti.services.IPeCreateService;
import org.eclipse.graphiti.ui.services.GraphitiUi;
import org.eclipse.graphiti.util.ColorConstant;
import org.eclipse.graphiti.util.IColorConstant;

import de.dlr.sc.virsat.graphiti.ui.diagram.feature.VirSatAddShapeFeature;
import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;
import de.dlr.sc.virsat.model.extension.statemachines.model.State;
import de.dlr.sc.virsat.model.extension.statemachines.model.StateMachine;
import de.dlr.sc.virsat.model.extension.statemachines.model.Transition;
import de.dlr.sc.virsat.model.extension.statemachines.ui.diagram.features.states.StateAddFeature;

/**
 * Feature for adding state machines on the  diagram.
 * @author bell_er
 *
 */
public class StateMachineAddFeature extends VirSatAddShapeFeature {
	
	public static final int DEFAULT_WIDTH = 100;	
	public static final int DEFAULT_HEIGHT = 200;	
	public static final int CORNER_WIDTH = 0;	
	public static final int CORNER_HEIGHT = 0;		
	public static final int LINE_WIDTH = 2;  		
	public static final int PADDING_X = 10;
	public static final int PADDING_Y = 10;
	public static final int STATEPADDING = 50;
	
	public static final IColorConstant ELEMENT_TEXT_FOREGROUND = IColorConstant.BLACK;
	public static final IColorConstant ELEMENT_FOREGROUND =	new ColorConstant(98, 131, 167);	
	public static final IColorConstant ELEMENT_BACKGROUND =	new ColorConstant(187, 218, 247);
	
	/** 
	 * Default constructor.
	 * @param fp the feature provider.
	 */
	public StateMachineAddFeature(IFeatureProvider fp) {
		super(fp);
	}

	@Override
	public boolean canAdd(IAddContext context) {
		return super.canAdd(context) && context.getTargetContainer() instanceof Diagram;
	}

	@Override
	public PictogramElement add(IAddContext context) {
		StateMachine sm = new StateMachine((CategoryAssignment) context.getNewObject());
		int stateCount = sm.getStates().size();
		int horizontalStateCount = (int) Math.ceil(Math.sqrt(stateCount));
		Diagram targetDiagram = (Diagram) context.getTargetContainer();	
		
		// Create a container shape with a rounded rectangle		
		IPeCreateService peCreateService = Graphiti.getPeCreateService();		
		ContainerShape containerShape = peCreateService.createContainerShape(targetDiagram, true);				
		IGaService gaService = Graphiti.getGaService();				
		String name = sm.getName();		
		String typeName = "\u00ab" + sm.getTypeInstance().getType().getName() + "\u00bb";		
		Font font = gaService.manageDefaultFont(getDiagram(), false, true);		
		IDimension nameDimension = GraphitiUi.getUiLayoutService().calculateTextSize(name, font);	
		IDimension typeNameDimension = GraphitiUi.getUiLayoutService().calculateTextSize("   " + typeName + "   ", font);			
		int linePosY = nameDimension.getHeight() + typeNameDimension.getHeight();
		int linePosX = Math.max(nameDimension.getWidth(), typeNameDimension.getWidth());
		int width = Math.max(Math.max(linePosX, DEFAULT_WIDTH), horizontalStateCount * (StateAddFeature.RADIUS * 2) + STATEPADDING);
		int height = Math.max(DEFAULT_HEIGHT, linePosY + horizontalStateCount * (StateAddFeature.RADIUS * 2) + STATEPADDING);
		
		// Create and set graphics algorithm		
		RoundedRectangle roundedRectangle = gaService.createRoundedRectangle(containerShape, CORNER_WIDTH, CORNER_HEIGHT);		
		roundedRectangle.setLineWidth(LINE_WIDTH);		
		roundedRectangle.setForeground(manageColor(ELEMENT_FOREGROUND));
		roundedRectangle.setBackground(manageColor(ELEMENT_BACKGROUND));	
		gaService.setLocationAndSize(roundedRectangle, context.getX(), context.getY(), width, height);		
		link(containerShape, sm);
		
		// Create Shape with line
		Shape lineShape = peCreateService.createShape(containerShape, false);		
		Polyline polyline = gaService.createPolyline(lineShape, new int[] { 0, linePosY + PADDING_Y, linePosX + PADDING_X + 2, linePosY + PADDING_Y });		
		polyline.setLineWidth(LINE_WIDTH);
		
		// Create Shape with line2
		Shape lineShape2 = peCreateService.createShape(containerShape, false);		
		Polyline polyline2 = gaService.createPolyline(lineShape2, new int[] { linePosX + PADDING_X, 0, linePosX + PADDING_X,  linePosY + PADDING_Y + 2 });		
		polyline2.setLineWidth(LINE_WIDTH);
		
		// Create Shape with Type Label
		Shape typeShape = peCreateService.createShape(containerShape, false);		
		Text typeText = gaService.createText(typeShape, typeName);		
		typeText.setForeground(manageColor(ELEMENT_TEXT_FOREGROUND));		
		typeText.setHorizontalAlignment(Orientation.ALIGNMENT_CENTER);		
		typeText.setFont(font);		
		gaService.setLocationAndSize(typeText, 0, PADDING_Y, linePosX, typeNameDimension.getHeight());	
		
		// Create Shape with Name Label
		Shape nameShape = peCreateService.createShape(containerShape, false);	
		Text nameText = gaService.createText(nameShape, name);		
		nameText.setForeground(manageColor(ELEMENT_TEXT_FOREGROUND));	
		nameText.setHorizontalAlignment(Orientation.ALIGNMENT_CENTER);		
		nameText.setFont(font);		
		gaService.setLocationAndSize(nameText, 0, PADDING_Y + typeNameDimension.getHeight(), linePosX, nameDimension.getHeight());		
		link(nameShape, sm);
		
		// add states
		int counter = 0;
		int xPadding = StateAddFeature.RADIUS + StateAddFeature.IMAGE_WIDTH;
		int yPadding = StateAddFeature.RADIUS;
		for (State s : sm.getStates()) {
			AddContext ieContext = new AddContext(context, s.getTypeInstance());			
			ieContext.setTargetContainer(containerShape);	
			ieContext.setX(xPadding);
			ieContext.setY(yPadding);
			IAddFeature feature = getFeatureProvider().getAddFeature(ieContext);	
			feature.add(ieContext);
			xPadding = xPadding + StateAddFeature.RADIUS * 2; 
			counter++;
			if (counter >= horizontalStateCount) {
				counter = 0;
				yPadding = yPadding + StateAddFeature.RADIUS * 2;
				xPadding = StateAddFeature.RADIUS + StateAddFeature.IMAGE_WIDTH;
			}
		}
		
		// add the transitions
		for (Transition t : sm.getTransitions()) {	
			AddContext iContext = new AddContext(context, t.getTypeInstance());		
			IAddFeature feature = getFeatureProvider().getAddFeature(iContext);		
			feature.add(iContext);		
		}		
		return null;
	}
}