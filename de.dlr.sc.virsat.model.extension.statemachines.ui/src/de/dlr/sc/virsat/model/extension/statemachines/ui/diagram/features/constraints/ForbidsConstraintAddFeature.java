/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.statemachines.ui.diagram.features.constraints;

import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.util.ColorConstant;
import org.eclipse.graphiti.util.IColorConstant;

import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;
import de.dlr.sc.virsat.model.extension.statemachines.model.ForbidsConstraint;
import de.dlr.sc.virsat.model.extension.statemachines.model.State;
import de.dlr.sc.virsat.model.extension.statemachines.ui.diagram.features.abstractConnection.AbstractConnectionAddFeature;

/**
 * Feature for adding forbids constraint in an state machine diagram. 
 * @author bell_Er 
 * */
public class ForbidsConstraintAddFeature extends AbstractConnectionAddFeature {
	private static final IColorConstant CONNECTION_FOREGROUND = new ColorConstant(255, 0, 0);
	
	/** 
	 * Default constructor.
	 * @param fp the feature provider.
	 */
	public ForbidsConstraintAddFeature(IFeatureProvider fp) {
		super(fp);
	}
	
	@Override
	protected IColorConstant getLineColor() {
		return CONNECTION_FOREGROUND;
	}
	
	@Override
	public State[] getStates(CategoryAssignment ca) {
		ForbidsConstraint fc = new ForbidsConstraint(ca);
		State[] states = {  fc.getStateConstraining(), fc.getStateInfluenced() };
		return states;
	}
	
	@Override
	protected boolean setTextDecorator() {
		return false;
	}
}
