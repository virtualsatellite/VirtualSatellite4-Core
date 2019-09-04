/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.funcelectrical.ui.diagram;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.graphiti.datatypes.IDimension;
import org.eclipse.graphiti.dt.IDiagramTypeProvider;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IPictogramElementContext;
import org.eclipse.graphiti.internal.features.context.impl.base.PictogramElementContext;
import org.eclipse.graphiti.mm.algorithms.Rectangle;
import org.eclipse.graphiti.mm.algorithms.Text;
import org.eclipse.graphiti.mm.pictograms.ConnectionDecorator;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.platform.IPlatformImageConstants;
import org.eclipse.graphiti.tb.DefaultToolBehaviorProvider;
import org.eclipse.graphiti.tb.IContextButtonPadData;
import org.eclipse.graphiti.tb.IDecorator;
import org.eclipse.graphiti.tb.ImageDecorator;
import org.eclipse.graphiti.ui.services.GraphitiUi;
import de.dlr.sc.virsat.build.marker.ui.MarkerImageProvider;
import de.dlr.sc.virsat.model.concept.types.category.ABeanCategoryAssignment;
import de.dlr.sc.virsat.model.concept.types.structural.ABeanStructuralElementInstance;
import de.dlr.sc.virsat.model.extension.funcelectrical.model.Interface;
import de.dlr.sc.virsat.model.extension.funcelectrical.model.InterfaceEnd;
import de.dlr.sc.virsat.project.markers.VirSatProblemMarkerHelper;

/**
 * Provides the tool behavior provider for Interface diagrams.
 * @author muel_s8
 *
 */

@SuppressWarnings("restriction")
public class InterfaceDiagramToolBehaviorProvider extends DefaultToolBehaviorProvider {

	protected MarkerImageProvider mip;

	/**
	 * Standard constructor
	 * @param diagramTypeProvider the diagram type provider
	 */
	
	public InterfaceDiagramToolBehaviorProvider(IDiagramTypeProvider diagramTypeProvider) {
		super(diagramTypeProvider);
		mip = new MarkerImageProvider(new VirSatProblemMarkerHelper());
	}
	
	@Override
	public IDecorator[] getDecorators(PictogramElement pe) {
	    IFeatureProvider featureProvider = getFeatureProvider();
	    Object bo = featureProvider.getBusinessObjectForPictogramElement(pe);
	    
	    EObject eObject = null;
	    if (bo instanceof ABeanStructuralElementInstance) {
	    	eObject = ((ABeanStructuralElementInstance) bo).getStructuralElementInstance();
	    } else if (bo instanceof ABeanCategoryAssignment) {
	    	eObject = ((ABeanCategoryAssignment) bo).getTypeInstance();
	    }
	    
	    String tooltip = "";
	    if (eObject != null) {
	    	tooltip = mip.getProblemImageToolTipForEObject(eObject);
	    }
	    
	    if (!tooltip.equals("")) {
	    	
	    	// If we have some warnings, display them and place them by decorating the graphiti element 
	    	// with a little icon. The icon may need to be placed depending on the graphical element 
	    	// to be decorated.
	    	
	    	ImageDecorator imageRenderingDecorator = null;
	    	if (bo instanceof InterfaceEnd) {
	    		ContainerShape shape = (ContainerShape) pe;
	    		
	    		if (!shape.getAnchors().isEmpty()) {
		    		imageRenderingDecorator = new ImageDecorator(IPlatformImageConstants.IMG_ECLIPSE_WARNING_TSK);
		    		Rectangle rectangle = (Rectangle) shape.getGraphicsAlgorithm();
					imageRenderingDecorator.setX(rectangle.getLineWidth());
					imageRenderingDecorator.setY(rectangle.getLineWidth());
	    		}
	    	} else if (bo instanceof Interface) {
	    		if (pe instanceof ConnectionDecorator) {
	    			imageRenderingDecorator = new ImageDecorator(IPlatformImageConstants.IMG_ECLIPSE_WARNING_TSK);
	    			Text text = (Text) pe.getGraphicsAlgorithm();
	    			IDimension nameDimension = GraphitiUi.getUiLayoutService().calculateTextSize(text.getValue(), text.getFont());
	    			imageRenderingDecorator.setX(nameDimension.getWidth());
	    			imageRenderingDecorator.setY(0);
	    		}
	    	}
	    	
	    	if (imageRenderingDecorator != null) {
	    		imageRenderingDecorator.setMessage(tooltip);
	    		return new IDecorator[] { imageRenderingDecorator };
	    	}
	    }
	    
	    return super.getDecorators(pe);
	}
	
	@Override
	public PictogramElement getSelection(PictogramElement originalPe, PictogramElement[] oldSelection) {
		if (originalPe instanceof ContainerShape) {
			
			// If we select a shape with an anchor, then we go up by one level in the container hiearchy
			// since the anchor is grouped with that parent and just moving/deleting/etc the anchor holder should not be possible
			
			ContainerShape cs = (ContainerShape) originalPe;
			if (!cs.getAnchors().isEmpty()) {
				return cs.getContainer();
			}
		}
		return super.getSelection(originalPe, oldSelection);
	}
	
	@Override
	public IContextButtonPadData getContextButtonPad(IPictogramElementContext context) {
		// We dont want the context menu for holder shapes of anchors
		// Instead we want to have the context menu for the entire shape
		// So we need to modifify the context so that we get the parent
		// shape if we have an anchor holding shape
		
		PictogramElement originalPe = context.getPictogramElement();
		
		if (originalPe instanceof ContainerShape) {
			ContainerShape cs = (ContainerShape) originalPe;
			if (!cs.getAnchors().isEmpty()) {
				PictogramElementContext newContext = new PictogramElementContext(cs.getContainer());
				return super.getContextButtonPad(newContext);
			} 
		} 
		
		return super.getContextButtonPad(context);
	}

}
