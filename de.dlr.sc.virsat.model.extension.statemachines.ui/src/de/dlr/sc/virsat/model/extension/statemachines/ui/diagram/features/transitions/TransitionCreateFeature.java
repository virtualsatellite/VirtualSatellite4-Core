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
import org.eclipse.graphiti.features.context.impl.AddConnectionContext;

import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.extension.statemachines.model.State;
import de.dlr.sc.virsat.model.extension.statemachines.model.StateMachine;
import de.dlr.sc.virsat.model.extension.statemachines.model.Transition;
import de.dlr.sc.virsat.model.extension.statemachines.ui.diagram.features.abstractConnection.AbstractConnectionCreateFeature;

/**
 * Feature for creating new transitions in an state machine diagram. 
 * @author bell_Er 
 * */
public class TransitionCreateFeature extends AbstractConnectionCreateFeature {


	/**	
	 * Default constructor.	 
	 * @param fp the feature provider.	
	 */	
	public TransitionCreateFeature(IFeatureProvider fp) {
		super(fp, "Transition", "Create Transition");
	}

	@Override
	public void addConnection(AddConnectionContext addContext, State source, State target, StateMachine stateMachine, Concept concept) {
		Transition i = new Transition(concept);
		i.setStateFrom(source); 
		i.setStateTo(target);
		stateMachine.getTransitions().add(i);
		addContext.setNewObject(i.getTypeInstance());
	}


	@Override
	public boolean canDraw(State state1, State state2) {
		return statesAreInSameStateMachine(state1, state2);
	}
	

}
