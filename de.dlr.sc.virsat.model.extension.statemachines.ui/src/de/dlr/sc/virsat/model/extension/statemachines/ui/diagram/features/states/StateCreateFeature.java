/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.statemachines.ui.diagram.features.states;

import org.eclipse.emf.common.command.Command;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.ICreateContext;

import de.dlr.sc.virsat.graphiti.ui.diagram.feature.VirSatCreateFeature;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.extension.statemachines.model.State;
import de.dlr.sc.virsat.model.extension.statemachines.model.StateMachine;
import de.dlr.sc.virsat.project.editingDomain.VirSatTransactionalEditingDomain;

/**
 * Feature for creating new States in StateMachine Diagram
 * @author bell_Er
 *
 */

public class StateCreateFeature extends VirSatCreateFeature  {

	 /**
     * Default Constructor.
     * @param fp the feature provider.
     */
	public StateCreateFeature(IFeatureProvider fp) {
		super(fp, "State", "Create a new State");

	}

	@Override
	public boolean canCreate(ICreateContext context) {
		Object object = getBusinessObjectForPictogramElement(context.getTargetContainer());
		
		if (object instanceof StateMachine) {
			return super.canCreate(context);
		}
		
		return false;
	}
	
	@Override
	protected Command createCreateCommand(VirSatTransactionalEditingDomain ed, ICreateContext context) {
		StateMachine stateMachine = (StateMachine) getBusinessObjectForPictogramElement(context.getTargetContainer());	
		Concept concept = stateMachine.getConcept();
		State state = new State(concept);
		return stateMachine.getStates().add(ed, state);
	}
	
	@Override
	public String getCreateImageId() {
		return State.FULL_QUALIFIED_CATEGORY_NAME;
	}
}
