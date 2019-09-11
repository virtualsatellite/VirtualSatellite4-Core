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

import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.IUpdateFeature;
import org.eclipse.graphiti.features.context.ICustomContext;
import org.eclipse.graphiti.features.context.impl.UpdateContext;
import org.eclipse.graphiti.features.custom.AbstractCustomFeature;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;

import de.dlr.sc.virsat.graphiti.util.DiagramHelper;
import de.dlr.sc.virsat.model.extension.statemachines.model.State;
import de.dlr.sc.virsat.model.extension.statemachines.model.StateMachine;

/** 
 * Sets the state as initial state
 * @author bell_Er 
 * */
public class StateSetAsInitialStateFeature extends AbstractCustomFeature {	
	/**	
	 * Standard constructor	 
	 * @param fp the feature provider	
	 **/
	public StateSetAsInitialStateFeature(IFeatureProvider fp) {
		super(fp);
	}
	
	@Override
	public String getName() {
		return "Initial State";
	}
	
	@Override
	public String getDescription() {
		return "Sets State as Initial State ";
	}
	
	@Override
	public void execute(ICustomContext context) {
		PictogramElement pe = context.getInnerPictogramElement();
		
		Object o = getBusinessObjectForPictogramElement(pe);
		if (o instanceof State) {
			State s = (State) o;
			StateMachine stateMachine = s.getParentCaBeanOfClass(StateMachine.class);
			stateMachine.setInitialState(s);						
		}
		// update the stateMachine 
		PictogramElement stateMachine = (PictogramElement) pe.eContainer().eContainer();
		UpdateContext updateContext = new UpdateContext(stateMachine);
		IUpdateFeature feature = getFeatureProvider().getUpdateFeature(updateContext);
		feature.update(updateContext);
	}
	
	@Override
	public boolean canExecute(ICustomContext context) {
		for (PictogramElement pe : context.getPictogramElements()) {
			Object bo = getBusinessObjectForPictogramElement(pe);
			if (!DiagramHelper.hasBothWritePermission(bo, pe)) {
				return false;
			}
			if (!(bo instanceof State)) {
				return false;
			}
		}
		return true;
	}
	
	@Override
	public String getImageId() {
		return "Unchecked";
	}
}
