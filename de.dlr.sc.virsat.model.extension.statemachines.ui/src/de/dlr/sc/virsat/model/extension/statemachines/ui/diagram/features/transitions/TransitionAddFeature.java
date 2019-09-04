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
* @author bell_Er 
* */
public class TransitionAddFeature extends AbstractConnectionAddFeature {
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
}
