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

import java.util.ArrayList;
import java.util.List;

import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.ILayoutFeature;
import org.eclipse.graphiti.features.IReason;
import org.eclipse.graphiti.features.IRemoveFeature;
import org.eclipse.graphiti.features.context.IUpdateContext;
import org.eclipse.graphiti.features.context.impl.LayoutContext;
import org.eclipse.graphiti.features.context.impl.RemoveContext;
import org.eclipse.graphiti.features.impl.Reason;
import org.eclipse.graphiti.mm.algorithms.GraphicsAlgorithm;
import org.eclipse.graphiti.mm.algorithms.Text;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.mm.pictograms.Shape;

import com.google.common.base.Objects;

import de.dlr.sc.virsat.graphiti.ui.diagram.feature.VirSatUpdateFeature;
import de.dlr.sc.virsat.model.concept.types.structural.ABeanStructuralElementInstance;
import de.dlr.sc.virsat.model.extension.funcelectrical.model.InterfaceEnd;

/**
 * Update feature for updating elements in an interface diagram.
 * @author muel_s8
 *
 */

public class ElementUpdateFeature extends VirSatUpdateFeature {

	/**
	 * Default public constructor.
	 * @param fp the feature provider
	 */
	
	public ElementUpdateFeature(IFeatureProvider fp) {
		super(fp);
	}

	@Override
	public boolean canUpdate(IUpdateContext context) {
		Object object = getBusinessObjectForPictogramElement(context.getPictogramElement());
		return (object instanceof ABeanStructuralElementInstance || object == null) && super.canUpdate(context);
	}

	@Override
	public IReason updateNeeded(IUpdateContext context) {
		PictogramElement pictogramElement = context.getPictogramElement();
		
		ABeanStructuralElementInstance bean = (ABeanStructuralElementInstance) getBusinessObjectForPictogramElement(pictogramElement);
		
		if (bean == null) {
			return Reason.createTrueReason("Element no longer exists");
		}
		
        // retrieve name from business model
        String businessName = bean.getName();
		boolean updateNeeded = false;
        
        if (pictogramElement instanceof ContainerShape) {
            ContainerShape cs = (ContainerShape) pictogramElement;
            for (Shape shape : cs.getChildren()) {
                if (shape.getGraphicsAlgorithm() instanceof Text && getBusinessObjectForPictogramElement(shape) == bean) {
                    Text text = (Text) shape.getGraphicsAlgorithm();
                    String pictogramName = text.getValue();
                    updateNeeded = !Objects.equal(pictogramName, businessName);
                }
            }
            
            for (Shape shape : cs.getChildren()) {
            	if (shape instanceof ContainerShape) {
	            	InterfaceEnd ie = (InterfaceEnd) getBusinessObjectForPictogramElement(shape);
	            	
	            	if (!bean.getAll(InterfaceEnd.class).contains(ie)) {
	            		updateNeeded = true;
	            		continue;
	            	}
	            	
	        		String typeLabel = ie.getType() != null ? (" : " + ie.getType().getName()) : "";
	        		String label = ie.getName() + typeLabel;
	            	
	                for (GraphicsAlgorithm ga : shape.getGraphicsAlgorithm().getGraphicsAlgorithmChildren()) {
	                    if (ga instanceof Text) {
	                        Text text = (Text) ga;
	                        String pictogramName = text.getValue();
	                    	updateNeeded |= !Objects.equal(pictogramName, label);
	                    }
	                }
            	}
            }
            
        } else if (pictogramElement.getGraphicsAlgorithm() instanceof Text) {
            Text text = (Text) pictogramElement.getGraphicsAlgorithm();
            String pictogramName = text.getValue();
            updateNeeded = !Objects.equal(pictogramName, businessName);
        }
 
        // update needed, if changes have been found
        
        if (updateNeeded) {
            return Reason.createTrueReason("Out of date");
        } else {
            return Reason.createFalseReason();
        }
	}

	@Override
	public boolean update(IUpdateContext context) {
        // retrieve name from business model
        PictogramElement pictogramElement = context.getPictogramElement();
        ABeanStructuralElementInstance bean = (ABeanStructuralElementInstance) getBusinessObjectForPictogramElement(pictogramElement);
        
        // check if the element still exists if not then we also remove the pictogram element
 		if (bean == null) {
         	RemoveContext removeContext = new RemoveContext(pictogramElement);
 			IRemoveFeature feature = getFeatureProvider().getRemoveFeature(removeContext);
 			feature.remove(removeContext);
 			return true;
 		}
        
        String businessName = bean.getName();
        
        boolean changeDuringUpdate = false;
        
        // Set name in pictogram model
        if (pictogramElement instanceof ContainerShape) {
            ContainerShape cs = (ContainerShape) pictogramElement;
            for (Shape shape : cs.getChildren()) {
                if (shape.getGraphicsAlgorithm() instanceof Text && getBusinessObjectForPictogramElement(shape) == bean) {
                    Text text = (Text) shape.getGraphicsAlgorithm();
                    text.setValue(businessName);
                    changeDuringUpdate = true;
                }
            }
            
            List<PictogramElement> toRemove = new ArrayList<>();
            
            // Next we update the interface ends, note that the interface ends
            // are all represented by ContainerShapes within the Element shape
            
            for (Shape shape : cs.getChildren()) {      	
            	if (shape instanceof ContainerShape) {
            		Object bo = getBusinessObjectForPictogramElement(shape);
	        		if (!bean.getAll(InterfaceEnd.class).contains(bo)) {
	        			// This interface end does no longer exist has been removed outside of the diagram modelling domain
	        			toRemove.add(shape);
	        			continue;
	        		}
            		
            		InterfaceEnd ie = (InterfaceEnd) bo;

	        		String typeLabel = ie.getType() != null ? (" : " + ie.getType().getName()) : "";
	        		String label = ie.getName() + typeLabel;
	        		
	                for (GraphicsAlgorithm ga : shape.getGraphicsAlgorithm().getGraphicsAlgorithmChildren()) {
	                    // Correct labelling and fix size and position
	                	if (ga instanceof Text) {
	                        Text text = (Text) ga;
	                        text.setValue(label);
	                    } 
	                }

	                changeDuringUpdate = true;
            	}
            }
            
            for (PictogramElement pe : toRemove) {
            	RemoveContext removeContext = new RemoveContext(pe);
    			IRemoveFeature feature = getFeatureProvider().getRemoveFeature(removeContext);
    			feature.remove(removeContext);
            }
        }
        
        if (changeDuringUpdate) {
        	LayoutContext layoutContext = new LayoutContext(pictogramElement);
        	ILayoutFeature feature = getFeatureProvider().getLayoutFeature(layoutContext);
        	feature.layout(layoutContext);
        }
        
		return changeDuringUpdate;
	}

}
