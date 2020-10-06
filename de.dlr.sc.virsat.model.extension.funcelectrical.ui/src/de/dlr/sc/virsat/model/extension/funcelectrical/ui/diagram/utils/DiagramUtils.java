/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.funcelectrical.ui.diagram.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.eclipse.graphiti.datatypes.IDimension;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.mm.algorithms.Text;
import org.eclipse.graphiti.mm.algorithms.styles.Font;
import org.eclipse.graphiti.mm.algorithms.styles.Orientation;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.Shape;
import org.eclipse.graphiti.ui.services.GraphitiUi;

import de.dlr.sc.virsat.model.concept.types.structural.ABeanStructuralElementInstance;
import de.dlr.sc.virsat.model.concept.types.util.BeanCategoryAssignmentHelper;
import de.dlr.sc.virsat.model.extension.funcelectrical.model.InterfaceEnd;
import de.dlr.sc.virsat.model.extension.funcelectrical.ui.diagram.features.elements.ElementAddFeature;
import de.dlr.sc.virsat.model.extension.funcelectrical.ui.diagram.features.interfaceends.InterfaceEndAddFeature;

/**
 * The functionalities for layouting and calcualtions
 * @author bell_er
 *
 */
public class DiagramUtils {
	/**
	 * The functionalities for layouting and calcualtions
	 * @author bell_er
	 *
	 */
	private  DiagramUtils() {
		
	}
	
	/**
	 * The function to calculate the desired height of an element
	 * @return desired height
	 * @param containerShape the element
	 * @param featureProvider the feature provider
	 *
	 */
	public static int getRequiredHeight(ContainerShape containerShape, IFeatureProvider featureProvider) {
		ArrayList<Shape> achorLeft = new ArrayList<Shape>();
		ArrayList<Shape> achorRight = new ArrayList<Shape>();
		for (Shape shape : containerShape.getChildren()) {
			Object bo = featureProvider.getBusinessObjectForPictogramElement(shape);
			if (bo instanceof InterfaceEnd) {
				if (shape.getGraphicsAlgorithm().getX() == 0) {
					achorLeft.add(shape);
				} else {
					achorRight.add(shape);
				}
			}
		}
		int maxSideInterfaceEndCount = Math.max(achorLeft.size(), achorRight.size());
		int headerHeight = 2 * InterfaceEndAddFeature.INTERFACE_END_HEIGHT;
		int maxHeight = headerHeight + maxSideInterfaceEndCount * InterfaceEndAddFeature.INTERFACE_END_HEIGHT  + (maxSideInterfaceEndCount + 2) * ElementAddFeature.PADDING_Y;
		return maxHeight;
		
	}
	
	/**
	 * The function to reposition interface ends
	 * @param containerShape the element
	 * @param featureProvider the feature provider
	 *
	 */
	public static void repositionInterfaceEndShapes(ContainerShape containerShape, IFeatureProvider featureProvider) {
		List<Shape> ieLeft = new ArrayList<>();
		List<Shape> ieRight = new ArrayList<>();
		getLeftRightInterfaceEndShapes(containerShape, featureProvider, ieLeft, ieRight);
		repositionInterfaceEndShapes(ieLeft);
		repositionInterfaceEndShapes(ieRight);
	}
	
	/**
	 * The function to reposition interface ends
	 * @param containerShape the element
	 * @param featureProvider the feature provider
	 *
	 */
	public static void repositionInterfaceEndShapes(List<Shape> shapes) {
		int i = 2;
 		for (Shape shape : shapes) {
 			int height = (ElementAddFeature.PADDING_Y + InterfaceEndAddFeature.INTERFACE_END_HEIGHT) * i;
 			shape.getGraphicsAlgorithm().setY(height);
 			i++;
        }
	}
	
	private static final Comparator<Shape> Y_POSITION_COMPARATOR = (Shape o1, Shape o2) -> {
		Shape shape1 = (Shape) o1;
		Shape shape2 = (Shape) o2;
		double diff = shape1.getGraphicsAlgorithm().getY() - shape2.getGraphicsAlgorithm().getY();
		return Double.compare(diff, 0);
	};
	
	/**
	 * Checks if a given interface end is positioned on the right side of an element or not
	 * @param interfaceEndShape the diagram shape of the interface end
	 * @return true iff the the interface end is positione on the right side. False implies that it is on the left side.
	 */
	private static boolean isRightInterfaceEnd(ContainerShape interfaceEndShape) {
		Text text = (Text) interfaceEndShape.getGraphicsAlgorithm().getGraphicsAlgorithmChildren().get(0);
		return text.getHorizontalAlignment() == Orientation.ALIGNMENT_RIGHT;
	}
	
	/**
	 * The function to return the left right anchors of an element
	 * @return right anchors
	 * @param containerShape the element
	 * @param featureProvider the feature provider
	 * @param ieLeft interface ends on the left
	 * @param ieRight interface ends on the right
	 *
	 */
	public static void getLeftRightInterfaceEndShapes(ContainerShape containerShape, IFeatureProvider featureProvider, List<Shape> ieLeft, List<Shape> ieRight) {
		for (Shape shape : containerShape.getChildren()) {			
			if (shape instanceof ContainerShape) {
				ContainerShape childShape = (ContainerShape) shape;
				Object bo = featureProvider.getBusinessObjectForPictogramElement(shape);
				if (bo instanceof InterfaceEnd) {
					if (isRightInterfaceEnd(childShape)) {
						ieRight.add(shape);
					} else {
						ieLeft.add(shape);
					}
				}			
			}			
		}
		
		Collections.sort(ieRight, Y_POSITION_COMPARATOR);
		Collections.sort(ieLeft, Y_POSITION_COMPARATOR);
	}
	
	/**
	 * The function to calculate the minimum width to fit interfaceEnds
	 * @return width the needed width
	 * @param addedElement sei to be represented
	 * @param font font of the texts
	 *
	 */
	public static int neededWidth(ABeanStructuralElementInstance addedElement, Font font) {
		BeanCategoryAssignmentHelper bCaHelper = new BeanCategoryAssignmentHelper();
		List<InterfaceEnd> seiInterfaceEnds = bCaHelper.getAllBeanCategories(addedElement.getStructuralElementInstance(), InterfaceEnd.class);
		
		int maxWidth = 0;
		for (InterfaceEnd ie : seiInterfaceEnds) {
			String interfaceEndText;
			if (ie.getType() == null) {
				interfaceEndText = ie.getName();
			} else {
				interfaceEndText = ie.getName() + ie.getType().getName();
			}
			IDimension labelDimension = GraphitiUi.getUiLayoutService().calculateTextSize(interfaceEndText, font);
			int width = labelDimension.getWidth();
			int totalWidth = width + InterfaceEndAddFeature.INTERFACE_END_WIDTH;
			maxWidth = Math.max(totalWidth, maxWidth);
		}
		
		return maxWidth * 2;
	}
}
