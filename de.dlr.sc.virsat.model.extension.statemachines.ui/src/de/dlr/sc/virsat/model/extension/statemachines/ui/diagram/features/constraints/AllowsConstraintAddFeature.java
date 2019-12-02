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
import de.dlr.sc.virsat.model.extension.statemachines.model.AllowsConstraint;
import de.dlr.sc.virsat.model.extension.statemachines.model.State;
import de.dlr.sc.virsat.model.extension.statemachines.ui.diagram.features.abstractConnection.AbstractConnectionAddFeature;

/**
 * Feature for adding allows constraint in an state machine diagram. 
 * @author bell_Er 
 * */
public class AllowsConstraintAddFeature extends AbstractConnectionAddFeature {
	private static final IColorConstant CONNECTION_FOREGROUND = new ColorConstant(0, 255, 0);
	
	/** 
	 * Default constructor.
	 * @param fp the feature provider.
	 */
	public AllowsConstraintAddFeature(IFeatureProvider fp) {
		super(fp);
	}

	@Override
	protected IColorConstant getLineColor() {		
		return CONNECTION_FOREGROUND;
	}
	
	@Override
	public State[] getStates(CategoryAssignment ca) {
		AllowsConstraint ac = new AllowsConstraint(ca);
		State[] states = { ac.getStateConstraining(), ac.getStateInfluenced()};
		return states;
	}
	
	@Override
	protected boolean setTextDecorator() {
		return false;
	}
}
