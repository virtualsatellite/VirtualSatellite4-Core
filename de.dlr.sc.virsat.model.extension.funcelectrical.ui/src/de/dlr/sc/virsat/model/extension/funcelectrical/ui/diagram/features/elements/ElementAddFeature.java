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

import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Status;
import org.eclipse.graphiti.datatypes.IDimension;
import org.eclipse.graphiti.features.IAddFeature;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IAddContext;
import org.eclipse.graphiti.features.context.impl.AddContext;
import org.eclipse.graphiti.mm.algorithms.Polyline;
import org.eclipse.graphiti.mm.algorithms.Rectangle;
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
import de.dlr.sc.virsat.model.concept.types.factory.BeanStructuralElementInstanceFactory;
import de.dlr.sc.virsat.model.concept.types.structural.ABeanStructuralElementInstance;
import de.dlr.sc.virsat.model.dvlm.provider.DVLMEditPlugin;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import de.dlr.sc.virsat.model.extension.funcelectrical.model.Interface;
import de.dlr.sc.virsat.model.extension.funcelectrical.model.InterfaceEnd;
import de.dlr.sc.virsat.model.extension.funcelectrical.ui.Activator;
import de.dlr.sc.virsat.model.extension.funcelectrical.ui.diagram.features.interfaceends.InterfaceEndAddFeature;
import de.dlr.sc.virsat.model.extension.funcelectrical.ui.diagram.utils.DiagramUtils;

/**
 * 
 * @author muel_s8
 *
 */

public class ElementAddFeature extends VirSatAddShapeFeature {
	
	public static final IColorConstant ELEMENT_TEXT_FOREGROUND =
			IColorConstant.BLACK;
	
	public static final IColorConstant ELEMENT_FOREGROUND =
			new ColorConstant(98, 131, 167);
	
	public static final IColorConstant ELEMENT_BACKGROUND =
			new ColorConstant(187, 218, 247);
	
	public static final int DEFAULT_WIDTH = 200;
	public static final int DEFAULT_HEIGHT = 70;
	
	public static final int CORNER_WIDTH = 5;
	public static final int CORNER_HEIGHT = 5;
	
	public static final int LINE_WIDTH = 2;  
	
	public static final int MIDDLE_LINE = 650;
	
	public static final int PADDING_X = InterfaceEndAddFeature.INTERFACE_END_WIDTH / 2;
	public static final int PADDING_Y = InterfaceEndAddFeature.INTERFACE_END_HEIGHT / 2;
	public static final int LINE_PADDING_Y = 5;

	
	/**
	 * Default consturctor
	 * @param fp the feature provider
	 */
	
	public ElementAddFeature(IFeatureProvider fp) {
		super(fp);
	}

	@Override
	public boolean canAdd(IAddContext context) {
		boolean isStructuralelementInstance = context.getNewObject() instanceof StructuralElementInstance;
		
		BeanStructuralElementInstanceFactory bsf = new BeanStructuralElementInstanceFactory();
		ABeanStructuralElementInstance addedElement = null; 
		try {
			addedElement = (ABeanStructuralElementInstance) bsf.getInstanceFor((StructuralElementInstance) context.getNewObject());
		} catch (CoreException e) {
			Status status = new Status(Status.ERROR, Activator.getPluginId(), "Failed to perform an operation! ", e);
			DVLMEditPlugin.getPlugin().getLog().log(status);

		}
		boolean isSupportedObject = addedElement != null;
		boolean isDiagramContainer = context.getTargetContainer() instanceof Diagram;
		return super.canAdd(context) && isStructuralelementInstance && isSupportedObject && isDiagramContainer;
	}

	@Override
	public PictogramElement add(IAddContext context) {
		BeanStructuralElementInstanceFactory bsf = new BeanStructuralElementInstanceFactory();
		ABeanStructuralElementInstance addedElement = null; 
		String name = "";
		String typeName = "";
		try {
			addedElement = (ABeanStructuralElementInstance) bsf.getInstanceFor((StructuralElementInstance) context.getNewObject());
			name = addedElement.getName();
			typeName = "\u00ab" + addedElement.getStructuralElementInstance().getType().getName() + "\u00bb";
			
		} catch (CoreException e) {
			Status status = new Status(Status.ERROR, Activator.getPluginId(), "Failed to perform an operation! ", e);
			DVLMEditPlugin.getPlugin().getLog().log(status);
			return null;

		}
		Diagram targetDiagram = (Diagram) context.getTargetContainer();

		// Create a container shape with a rounded rectangle
		IPeCreateService peCreateService = Graphiti.getPeCreateService();
		ContainerShape containerShape = peCreateService.createContainerShape(targetDiagram, true);
		
		IGaService gaService = Graphiti.getGaService();

		Font font = gaService.manageDefaultFont(getDiagram(), false, true);
		IDimension nameDimension = GraphitiUi.getUiLayoutService().calculateTextSize(name, font);
		IDimension typeNameDimension = GraphitiUi.getUiLayoutService().calculateTextSize("   " + typeName + "   ", font);
		
		int linePosY = nameDimension.getHeight() + typeNameDimension.getHeight() + LINE_PADDING_Y;
		
		// Get the interface ends of this element
		// Note that we use the interfaces to determine the initial height of the element rectangle
		List<InterfaceEnd> interfaceEnds = addedElement.getAll(InterfaceEnd.class);
		
		// Make it so that we have enough width to display the sub elements such as name, interfaceends, etc
		int neededWidth = Math.max(Math.max(nameDimension.getWidth(), typeNameDimension.getWidth()), DiagramUtils.neededWidth(addedElement, font));
		
		int defaultWidth = DEFAULT_WIDTH >= neededWidth ? DEFAULT_WIDTH : neededWidth;
		// Check if the context has a default size
		// If not, define it
		int width = context.getWidth() <= 0 ? defaultWidth : context.getWidth();
		
		int totalWidth = width + PADDING_X * 2;
		
		// Create the invisible bigger rectangle
		Rectangle invisibleRectangle = gaService.createInvisibleRectangle(containerShape);
		gaService.setLocationAndSize(invisibleRectangle, context.getX() - PADDING_X, context.getY() - PADDING_Y, totalWidth, DEFAULT_HEIGHT);
		
		// Create and set graphics algorithm
		RoundedRectangle roundedRectangle = gaService.createRoundedRectangle(invisibleRectangle, CORNER_WIDTH, CORNER_HEIGHT);
		roundedRectangle.setForeground(manageColor(ELEMENT_FOREGROUND));
		roundedRectangle.setBackground(manageColor(ELEMENT_BACKGROUND));
		roundedRectangle.setLineWidth(LINE_WIDTH);
		roundedRectangle.setParentGraphicsAlgorithm(invisibleRectangle);
		gaService.setLocationAndSize(roundedRectangle, PADDING_X, PADDING_Y, width, DEFAULT_HEIGHT - InterfaceEndAddFeature.INTERFACE_END_HEIGHT);
		link(containerShape, addedElement);
		
		// Create Shape with line
		Shape lineShape = peCreateService.createShape(containerShape, false);
		Polyline polyline = gaService.createPolyline(lineShape, new int[] { PADDING_X, linePosY + PADDING_Y, width + PADDING_X, linePosY + PADDING_Y });
		polyline.setLineWidth(LINE_WIDTH);
		
		// Create Shape with Type Label
		Shape typeShape = peCreateService.createShape(containerShape, false);
		Text typeText = gaService.createText(typeShape, typeName);
		typeText.setForeground(manageColor(ELEMENT_TEXT_FOREGROUND));
		typeText.setHorizontalAlignment(Orientation.ALIGNMENT_CENTER);
		typeText.setFont(font);
		gaService.setLocationAndSize(typeText, PADDING_X, PADDING_Y, width, typeNameDimension.getHeight());
		
		// Create Shape with Name Label
		Shape nameShape = peCreateService.createShape(containerShape, false);
		Text nameText = gaService.createText(nameShape, name);
		nameText.setForeground(manageColor(ELEMENT_TEXT_FOREGROUND));
		nameText.setHorizontalAlignment(Orientation.ALIGNMENT_CENTER);
		nameText.setFont(font);
		gaService.setLocationAndSize(nameText, PADDING_X, PADDING_Y + typeNameDimension.getHeight(), width, nameDimension.getHeight());
		link(nameShape, addedElement);
		
		// Add interface ends
		if (interfaceEnds.size() > 0) {
			int iePadding = (DEFAULT_HEIGHT - linePosY) / (interfaceEnds.size() + 1);
			int yLeft = iePadding + linePosY;
			int yRight = iePadding + linePosY;
			
			for (InterfaceEnd ie : interfaceEnds) {
				AddContext ieContext = new AddContext(context, ie.getTypeInstance());
				ieContext.setTargetContainer(containerShape);
				ieContext.setWidth(width);				
				if (context.getX() < MIDDLE_LINE) {
					ieContext.setX(width + InterfaceEndAddFeature.INTERFACE_END_WIDTH);
					ieContext.setY(yRight);
					yRight += iePadding;
				} else {
					ieContext.setX(0);
					ieContext.setY(yLeft);
					yLeft += iePadding;
				}
				IAddFeature feature = getFeatureProvider().getAddFeature(ieContext);
				feature.add(ieContext);
			}
		}
		
		// Add the interfaces
		List<Interface> interfaces = addedElement.getAll(Interface.class);
		for (Interface i : interfaces) {
			AddContext iContext = new AddContext(context, i.getTypeInstance());
			IAddFeature feature = getFeatureProvider().getAddFeature(iContext);
			feature.add(iContext);
		}
		
		return containerShape;
	}
}
