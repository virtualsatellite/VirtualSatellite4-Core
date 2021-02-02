/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.statemachines.ui.diagram.features.transitions;

import org.eclipse.graphiti.features.IFeatureProvider;

import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;
import de.dlr.sc.virsat.model.extension.statemachines.model.State;
import de.dlr.sc.virsat.model.extension.statemachines.model.Transition;
import de.dlr.sc.virsat.model.extension.statemachines.ui.diagram.features.abstractConnection.AbstractConnectionAddFeature;
/**
* Feature for adding transition in an state machine diagram. 
* */
public class TransitionAddFeature extends AbstractConnectionAddFeature {
	
	public static final String EXTENSION_POINT_ID_TRANSITION_LABEL_PROVIDER = "de.dlr.sc.virsat.model.extension.statemachines.transition.LabelProvider";
	
	/** 
	 * Default constructor.
	 * @param fp the feature provider.
	 */
	public TransitionAddFeature(IFeatureProvider fp) {
		super(fp);
	}
	
	@Override
	public State[] getStates(CategoryAssignment ca) {
		Transition t = new Transition(ca);
		State[] states = { t.getStateFrom(), t.getStateTo() };
		return states;
	}
	
	@Override
	protected String getTransitionLabel(CategoryAssignment ca) {
		Transition transition = new Transition(ca);
		return new LabelProviderInstantiator().getLabelProvider().getLabel(transition);
	}
}
