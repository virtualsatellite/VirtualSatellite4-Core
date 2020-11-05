/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.graphiti.ui.diagram.feature;

import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IDirectEditingContext;
import org.eclipse.graphiti.features.impl.AbstractDirectEditingFeature;
import org.eclipse.graphiti.mm.algorithms.GraphicsAlgorithm;
import org.eclipse.graphiti.mm.algorithms.Text;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;

import de.dlr.sc.virsat.graphiti.util.DiagramHelper;
import de.dlr.sc.virsat.model.concept.types.property.BeanPropertyFloat;

/**
 * Generic class for enabling editing of float properties.
 * @author muel_s8
 *
 */

public class BeanPropertyFloatDirectEditValueFeature extends AbstractDirectEditingFeature {
	/**
	 * Public constructor
	 * @param fp the feature provider
	 */
	public BeanPropertyFloatDirectEditValueFeature(IFeatureProvider fp) {
		super(fp);
	}

	@Override
	public int getEditingType() {
		return TYPE_TEXT;
	}
	
	@Override
	public boolean canDirectEdit(IDirectEditingContext context) {
		PictogramElement pe = context.getPictogramElement();
		Object bo = getBusinessObjectForPictogramElement(pe);
		GraphicsAlgorithm ga = context.getGraphicsAlgorithm();
		
		if (bo instanceof BeanPropertyFloat && ga instanceof Text) {
			return DiagramHelper.hasBothWritePermission(bo, pe);
		}
		
		return false;
	}

	@Override
	public String getInitialValue(IDirectEditingContext context) {
		PictogramElement pe = context.getPictogramElement();
		BeanPropertyFloat bean = (BeanPropertyFloat) getBusinessObjectForPictogramElement(pe);
		return String.valueOf(bean.getValue());
	}
	
	@Override
	public void setValue(String value, IDirectEditingContext context) {
		PictogramElement pe = context.getPictogramElement();
		BeanPropertyFloat bean = (BeanPropertyFloat) getBusinessObjectForPictogramElement(pe);
		
		bean.setValue(Double.parseDouble(value));	
		
		PictogramElement updateablePe = DiagramHelper.getUpdateableElement(getFeatureProvider(), pe);
		updatePictogramElement(updateablePe);
	}
}
