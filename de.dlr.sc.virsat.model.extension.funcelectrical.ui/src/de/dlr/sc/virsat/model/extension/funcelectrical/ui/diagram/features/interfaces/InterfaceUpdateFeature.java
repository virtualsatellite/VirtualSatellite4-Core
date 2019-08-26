/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.funcelectrical.ui.diagram.features.interfaces;

import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.IReason;
import org.eclipse.graphiti.features.IRemoveFeature;
import org.eclipse.graphiti.features.context.IUpdateContext;
import org.eclipse.graphiti.features.context.impl.RemoveContext;
import org.eclipse.graphiti.features.impl.Reason;
import org.eclipse.graphiti.mm.algorithms.Text;
import org.eclipse.graphiti.mm.pictograms.Anchor;
import org.eclipse.graphiti.mm.pictograms.FreeFormConnection;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;

import com.google.common.base.Objects;

import de.dlr.sc.virsat.graphiti.ui.diagram.feature.VirSatUpdateFeature;
import de.dlr.sc.virsat.model.extension.funcelectrical.model.Interface;
import de.dlr.sc.virsat.model.extension.funcelectrical.model.InterfaceEnd;

/**
 * Update feature for updating elements in an interface diagram.
 * @author muel_s8
 *
 */

public class InterfaceUpdateFeature extends VirSatUpdateFeature {

	/**
	 * Default public constructor.
	 * @param fp the feature provider
	 */
	
	public InterfaceUpdateFeature(IFeatureProvider fp) {
		super(fp);
	}

	@Override
	public boolean canUpdate(IUpdateContext context) {
		Object object = getBusinessObjectForPictogramElement(context.getPictogramElement());
		return (object instanceof Interface || object == null) && super.canUpdate(context);
	}

	@Override
	public IReason updateNeeded(IUpdateContext context) {
		FreeFormConnection pictogramElement = (FreeFormConnection) context.getPictogramElement();
		
		Interface bean = (Interface) getBusinessObjectForPictogramElement(pictogramElement);
		
		if (bean == null) {
			return Reason.createTrueReason("Interface no longer exists");
		}
		
        // retrieve name from business model
        String businessName = bean.getName();
		
        
		Text text = (Text) pictogramElement.getConnectionDecorators().get(0).getGraphicsAlgorithm();
        String pictogramName = text.getValue();
        boolean nameUpdateNeeded = !Objects.equal(pictogramName, businessName);    
        if (nameUpdateNeeded) {
            return Reason.createTrueReason("Name out of date");
        }
           
        InterfaceEnd ieFrom = (InterfaceEnd) getBusinessObjectForPictogramElement(pictogramElement.getStart());
        boolean ieFromUpdateNeeded = !Objects.equal(ieFrom, bean.getInterfaceEndFrom());     
        if (ieFromUpdateNeeded) {
            return Reason.createTrueReason("Starting Interface End out of date");
        }
           
        InterfaceEnd ieTo = (InterfaceEnd) getBusinessObjectForPictogramElement(pictogramElement.getEnd());
        boolean ieToUpdateNeeded = !Objects.equal(ieTo, bean.getInterfaceEndTo());     
        if (ieToUpdateNeeded) {
            return Reason.createTrueReason("Ending Interface End out of date");
        }
        
        return Reason.createFalseReason();
        
	}

	@Override
	public boolean update(IUpdateContext context) {
        // retrieve name from business model
		FreeFormConnection pictogramElement = (FreeFormConnection) context.getPictogramElement();
        Interface bean = (Interface) getBusinessObjectForPictogramElement(pictogramElement);
        
        // check if the interface still exists if not then we also remove the pictogram element
		if (bean == null) {
        	RemoveContext removeContext = new RemoveContext(pictogramElement);
			IRemoveFeature feature = getFeatureProvider().getRemoveFeature(removeContext);
			feature.remove(removeContext);
			return true;
		}
        
        String businessName = bean.getName();
        Text text = (Text) pictogramElement.getConnectionDecorators().get(0).getGraphicsAlgorithm();
        text.setValue(businessName);
        
        
        // Now update the start and end anchors
        InterfaceEnd ieFrom = bean.getInterfaceEndFrom();
        InterfaceEnd ieTo = bean.getInterfaceEndTo();
		InterfaceEnd[] interfaceEnds = { ieFrom, ieTo };
		
		final PictogramElement[] pes = getFeatureProvider().getDiagramTypeProvider().getNotificationService().calculateRelatedPictogramElements(interfaceEnds);
		
		boolean foundStart = false;
		boolean foundEnd = false;
		
		for (PictogramElement pe : pes) {
			if (pe instanceof Anchor) {
				InterfaceEnd ie = (InterfaceEnd) getBusinessObjectForPictogramElement(pe);
				if (Objects.equal(ie, ieFrom)) {
					pictogramElement.setStart((Anchor) pe);
					foundStart = true;
				}
				
				if (Objects.equal(ie, ieTo)) {
					pictogramElement.setEnd((Anchor) pe);
					foundEnd = true;
				}
			}
		}
		
		if (!foundStart || !foundEnd) {
			// We have not found any anchors for the start or the ending location
			// This means that the Interface has been changed from the outside in a way
			// Such that it no longer points to an interface end that is currently on the diagram
			// As such we now remove this Interface from the diagram as well
        	RemoveContext removeContext = new RemoveContext(pictogramElement);
			IRemoveFeature feature = getFeatureProvider().getRemoveFeature(removeContext);
			feature.remove(removeContext);
		}
        
		return true;
	}

}
