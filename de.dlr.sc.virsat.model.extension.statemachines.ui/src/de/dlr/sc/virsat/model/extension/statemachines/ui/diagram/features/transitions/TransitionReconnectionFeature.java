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

import org.eclipse.emf.common.command.Command;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IReconnectionContext;
import org.eclipse.graphiti.features.impl.DefaultReconnectionFeature;
import org.eclipse.graphiti.mm.pictograms.Anchor;
import org.eclipse.graphiti.mm.pictograms.Connection;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;

import de.dlr.sc.virsat.graphiti.util.DiagramHelper;
import de.dlr.sc.virsat.model.extension.statemachines.model.State;
import de.dlr.sc.virsat.model.extension.statemachines.model.StateMachine;
import de.dlr.sc.virsat.model.extension.statemachines.model.Transition;
import de.dlr.sc.virsat.project.editingDomain.VirSatEditingDomainRegistry;
import de.dlr.sc.virsat.project.editingDomain.VirSatTransactionalEditingDomain;

/**
 * Feature for transition reconnection in an state machine diagram.
 * @author bell_er
 *
 */
public class TransitionReconnectionFeature  extends DefaultReconnectionFeature {

	/**
	 * Default constructor.
	 * @param fp the feature provider.
	 */
	
	public TransitionReconnectionFeature(IFeatureProvider fp) {
		super(fp);
	}
	
	@Override
	public boolean canReconnect(IReconnectionContext context) {
		Connection connection = context.getConnection();
		Transition t = (Transition) getBusinessObjectForPictogramElement(connection);
			
		if (!DiagramHelper.hasBothWritePermission(t, connection)) {
			return false;
		}
		
		StateMachine sm =  t.getParentCaBeanOfClass(StateMachine.class);
		Anchor newAnchor = context.getNewAnchor();
		if (newAnchor != null) {
			State state = (State) getBusinessObjectForPictogramElement(newAnchor);
			StateMachine sm2 = state.getParentCaBeanOfClass(StateMachine.class);
			if (!sm.equals(sm2)) {
				return false;
			}
		}
		return super.canReconnect(context);
	}
	
	@Override
	public void preReconnect(IReconnectionContext context) {
		Connection connection = context.getConnection();
		Transition t = (Transition) getBusinessObjectForPictogramElement(connection);
		
		Anchor oldAnchor = context.getOldAnchor();
		Anchor newAnchor = context.getNewAnchor();
		
		if (newAnchor == null) {
			ContainerShape cs = (ContainerShape) context.getTargetPictogramElement();
			cs = (ContainerShape) cs.getChildren().get(0);
			newAnchor = cs.getAnchors().get(0);
		}
		
		State state = (State) getBusinessObjectForPictogramElement(newAnchor);
		
		VirSatTransactionalEditingDomain ed = VirSatEditingDomainRegistry.INSTANCE.getEd(t.getTypeInstance());
		
		Command command = null;
		if (oldAnchor == connection.getStart()) {
			command = t.setStateFrom(ed, state);
		} else if (oldAnchor == connection.getEnd()) {
			command = t.setStateFrom(ed, state);
		}
		
		ed.getCommandStack().execute(command);
	}
}
