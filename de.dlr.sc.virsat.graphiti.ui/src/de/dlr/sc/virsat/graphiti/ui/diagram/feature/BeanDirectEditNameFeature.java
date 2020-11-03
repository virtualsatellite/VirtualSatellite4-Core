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

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IDirectEditingContext;
import org.eclipse.graphiti.features.impl.AbstractDirectEditingFeature;
import org.eclipse.graphiti.mm.algorithms.GraphicsAlgorithm;
import org.eclipse.graphiti.mm.algorithms.Text;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;

import de.dlr.sc.virsat.graphiti.util.DiagramHelper;
import de.dlr.sc.virsat.model.concept.types.IBeanName;

/**
 * Editing feature for editing the name of a bean within a diagram.
 * @author muel_s8
 *
 */
public class BeanDirectEditNameFeature extends AbstractDirectEditingFeature {

	/**
	 * Public constructor
	 * @param fp the feature provider
	 */
	public BeanDirectEditNameFeature(IFeatureProvider fp) {
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
		
		if (bo instanceof IBeanName && ga instanceof Text) {
			return DiagramHelper.hasBothWritePermission(bo, pe);
		}
		
		return false;
	}

	@Override
	public String getInitialValue(IDirectEditingContext context) {
		PictogramElement pe = context.getPictogramElement();
		IBeanName bean = (IBeanName) getBusinessObjectForPictogramElement(pe);
		return bean.getName();
	}
	
	@Override
	public void setValue(String value, IDirectEditingContext context) {
		PictogramElement pe = context.getPictogramElement();
		IBeanName bean = (IBeanName) getBusinessObjectForPictogramElement(pe);
		
		TransactionalEditingDomain ed = getDiagramBehavior().getEditingDomain();
		Command setName = bean.setName(ed, value);
		ed.getCommandStack().execute(setName);
		
		PictogramElement updateablePe = DiagramHelper.getUpdateableElement(getFeatureProvider(), pe);
		updatePictogramElement(updateablePe);
	}

}
