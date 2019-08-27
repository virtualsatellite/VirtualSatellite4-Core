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

import java.util.List;

import org.eclipse.graphiti.datatypes.IDimension;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.ILayoutFeature;
import org.eclipse.graphiti.features.context.IAddContext;
import org.eclipse.graphiti.features.context.impl.LayoutContext;
import org.eclipse.graphiti.mm.algorithms.GraphicsAlgorithm;
import org.eclipse.graphiti.mm.algorithms.Rectangle;
import org.eclipse.graphiti.mm.algorithms.Text;
import org.eclipse.graphiti.mm.algorithms.styles.Font;
import org.eclipse.graphiti.mm.algorithms.styles.Orientation;
import org.eclipse.graphiti.mm.pictograms.Anchor;
import org.eclipse.graphiti.mm.pictograms.ChopboxAnchor;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.mm.pictograms.Shape;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.services.IGaService;
import org.eclipse.graphiti.services.IPeCreateService;
import org.eclipse.graphiti.ui.services.GraphitiUi;
import org.eclipse.graphiti.util.ColorConstant;
import org.eclipse.graphiti.util.IColorConstant;

import de.dlr.sc.virsat.graphiti.ui.diagram.feature.VirSatAddShapeFeature;
import de.dlr.sc.virsat.model.concept.types.structural.ABeanStructuralElementInstance;
import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;
import de.dlr.sc.virsat.model.extension.funcelectrical.model.InterfaceEnd;
import de.dlr.sc.virsat.model.extension.funcelectrical.ui.diagram.features.elements.ElementAddFeature;
import de.dlr.sc.virsat.model.extension.funcelectrical.ui.diagram.utils.DiagramUtils;

/**
 * Add feature for interface ends
 * @author muel_s8
 *
 */

public class InterfaceEndAddFeature extends VirSatAddShapeFeature {

	private static final IColorConstant INTERFACE_END_TEXT_FOREGROUND =
			IColorConstant.BLACK;
	
	private static final IColorConstant INTERFACE_END_FOREGROUND =
			new ColorConstant(98, 131, 167);
	
	private static final IColorConstant INTERFACE_END_BACKGROUND =
			new ColorConstant(187, 218, 247);
	
	public static final int INTERFACE_END_WIDTH = 20;
	public static final int INTERFACE_END_HEIGHT = 20;
	
	/**
	 * Default Constructor 
	 * @param fp the feature provider
	 */
	
	public InterfaceEndAddFeature(IFeatureProvider fp) {
		super(fp);
	}

	@Override
	public boolean canAdd(IAddContext context) {
		Object object = context.getNewObject();
		if (object instanceof CategoryAssignment) {
			CategoryAssignment ca = (CategoryAssignment) object;
			if (ca.getType().getName().equals(InterfaceEnd.class.getSimpleName())) {
				// Check if this interface end is contained by the con
				Object target = getBusinessObjectForPictogramElement(context.getTargetContainer());
				if (target instanceof ABeanStructuralElementInstance) {
					ContainerShape s = context.getTargetContainer();
					if (!isInterfaceEndAlreadyAdded(s, ca) && isInterfaceEndContained(target, ca)) {
						return true;
					}
				}
			}
		}
		return false;
	}
	
	/**
	 * Checks if a pictogram element with this interface end already exists in the target
	 * container shape.
	 * @param s the container shape
	 * @param ca the interface end
	 * @return true iff there already is a pictogram element in the container shape linked to the interface end
	 */
	private boolean isInterfaceEndAlreadyAdded(ContainerShape s, CategoryAssignment ca) {
		for (Shape shape : s.getChildren()) {
			if (shape instanceof ContainerShape) {
				PictogramElement pe = shape.getGraphicsAlgorithm().getPictogramElement();
				InterfaceEnd ie = (InterfaceEnd) getBusinessObjectForPictogramElement(pe);
				if (ie != null && ie.getATypeInstance().equals(ca)) {
					return true;
				}
			}
		}
		
		return false;
	}
	
	/**
	 * Checks if the given interface end is owned by the object linked to the target container
	 * @param target the target object
	 * @param ca the interface end
	 * @return true iff the interface end is owned by the target
	 */
	private boolean isInterfaceEndContained(Object target, CategoryAssignment ca) {
		ABeanStructuralElementInstance targetBean = (ABeanStructuralElementInstance) target;
		List<InterfaceEnd> interfaceEnds = targetBean.getAll(InterfaceEnd.class);
		if (interfaceEnds.stream().anyMatch(ie -> ie.getTypeInstance() == ca)) {
			return true;
		}
		
		return false;
	}

	@Override
	public PictogramElement add(IAddContext context) {
		InterfaceEnd addedInterfaceEnd = new InterfaceEnd((CategoryAssignment) context.getNewObject());
		ContainerShape targetContainer = context.getTargetContainer();	
		IGaService gaService = Graphiti.getGaService();

		IPeCreateService peCreateService = Graphiti.getPeCreateService();
		// Get the required size
		Font font = gaService.manageDefaultFont(getDiagram(), false, true);
		String typeLabel = addedInterfaceEnd.getType() != null ? (" : " + addedInterfaceEnd.getType().getName()) : "";
		String label = addedInterfaceEnd.getName() + typeLabel;
		IDimension labelDimension = GraphitiUi.getUiLayoutService().calculateTextSize(label, font);
		
		ContainerShape containerShape = peCreateService.createContainerShape(targetContainer, true);
		link(containerShape, addedInterfaceEnd);
		
		// Create the invisible bigger rectangle
		Rectangle invisibleRectangle = gaService.createInvisibleRectangle(containerShape);
		int width = labelDimension.getWidth() + INTERFACE_END_WIDTH;
		int height = INTERFACE_END_HEIGHT;
		int x = context.getX() - width;
		gaService.setLocationAndSize(invisibleRectangle, x, context.getY(), width, height);
		
		// Assign a rectangle graphics algorithm for the interface end box
		ContainerShape rectangleShape = peCreateService.createContainerShape(containerShape, true);
		Rectangle rectangle = gaService.createPlainRectangle(rectangleShape);
		rectangle.setForeground(manageColor(INTERFACE_END_FOREGROUND));
		rectangle.setBackground(manageColor(INTERFACE_END_BACKGROUND));
		rectangle.setLineWidth(2);
		link(rectangleShape, addedInterfaceEnd);
		gaService.setLocationAndSize(rectangle, labelDimension.getWidth(), 0, INTERFACE_END_WIDTH, INTERFACE_END_HEIGHT);
		
		// Create an anchor
		ChopboxAnchor boxAnchor = peCreateService.createChopboxAnchor(rectangleShape);
		boxAnchor.setReferencedGraphicsAlgorithm(rectangle);
		link(boxAnchor, addedInterfaceEnd);	
		
		// Create Shape with Name Label
		Text labelText = gaService.createText(invisibleRectangle, label);
		labelText.setForeground(manageColor(INTERFACE_END_TEXT_FOREGROUND));
		labelText.setHorizontalAlignment(Orientation.ALIGNMENT_RIGHT);
		labelText.setFont(font);
		gaService.setLocationAndSize(labelText, 0, 0, labelDimension.getWidth(), INTERFACE_END_HEIGHT);
		
    	LayoutContext layoutContext = new LayoutContext(invisibleRectangle.getPictogramElement());
    	ILayoutFeature feature = getFeatureProvider().getLayoutFeature(layoutContext);
    	feature.layout(layoutContext);
    	for (Anchor anchor : targetContainer.getAnchors()) {
			PictogramElement pe2 = anchor.getGraphicsAlgorithm().getPictogramElement();
        	LayoutContext layoutContext2 = new LayoutContext(pe2);
        	ILayoutFeature feature2 = getFeatureProvider().getLayoutFeature(layoutContext2);
        	feature2.layout(layoutContext2);
        }
    	
    	// expand the target container so that every interfaceend can fit in
    	int requiredHeight = DiagramUtils.getRequiredHeight(targetContainer, getFeatureProvider());
    	GraphicsAlgorithm containerGa = targetContainer.getGraphicsAlgorithm();
    	if (containerGa.getHeight() < requiredHeight) {
			for (GraphicsAlgorithm gaChild : targetContainer.getGraphicsAlgorithm().getGraphicsAlgorithmChildren()) {
				gaService.setHeight(gaChild, requiredHeight -  ElementAddFeature.PADDING_Y);
			}
			gaService.setHeight(containerGa, requiredHeight +  ElementAddFeature.PADDING_Y);
			
    	} // reposition all the anchors 
    	
    	 // reposition all the anchors 
    	DiagramUtils.repositionShapes(targetContainer, getFeatureProvider());
        
		return boxAnchor;
	}

}
