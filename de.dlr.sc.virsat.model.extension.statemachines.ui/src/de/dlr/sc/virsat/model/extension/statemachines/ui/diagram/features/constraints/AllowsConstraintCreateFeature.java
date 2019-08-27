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
import org.eclipse.graphiti.features.context.impl.AddConnectionContext;

import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.extension.statemachines.model.AllowsConstraint;
import de.dlr.sc.virsat.model.extension.statemachines.model.State;
import de.dlr.sc.virsat.model.extension.statemachines.model.StateMachine;
import de.dlr.sc.virsat.model.extension.statemachines.ui.diagram.features.abstractConnection.AbstractConnectionCreateFeature;

/**
 * Feature for adding allows constraint in an state machine diagram. 
 * @author bell_Er 
 * */

public class AllowsConstraintCreateFeature extends AbstractConnectionCreateFeature {
	/**	
	 * Default constructor.	 
	 * @param fp the feature provider.	
	 */	
	public AllowsConstraintCreateFeature(IFeatureProvider fp) {
		super(fp, "AllowsConstraint", "Create AllowsConstraint");
	}

	@Override
	public boolean canDraw(State state1, State state2) {
		return !statesAreInSameStateMachine(state1, state2);
	}

	@Override
	public void addConnection(AddConnectionContext addContext, State source, State target, StateMachine stateMachine, Concept concept) {
		AllowsConstraint ac = new AllowsConstraint(concept);
		ac.setStateInfluenced(target);
		ac.setStateConstraining(source);
		stateMachine.getConstraints().add(ac);
		addContext.setNewObject(ac.getTypeInstance());	
	}
	
	@Override
	public String getCreateImageId() {
		return AllowsConstraint.FULL_QUALIFIED_CATEGORY_NAME;
	}
}
