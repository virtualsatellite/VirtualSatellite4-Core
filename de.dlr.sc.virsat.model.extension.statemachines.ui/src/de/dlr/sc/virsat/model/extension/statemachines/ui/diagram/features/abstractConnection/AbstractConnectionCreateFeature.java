/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.statemachines.ui.diagram.features.abstractConnection;

import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.ICreateConnectionContext;
import org.eclipse.graphiti.features.context.impl.AddConnectionContext;
import org.eclipse.graphiti.features.impl.AbstractCreateConnectionFeature;
import org.eclipse.graphiti.mm.pictograms.Anchor;
import org.eclipse.graphiti.mm.pictograms.Connection;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;

import de.dlr.sc.virsat.graphiti.util.DiagramHelper;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.extension.statemachines.model.State;
import de.dlr.sc.virsat.model.extension.statemachines.model.StateMachine;
import de.dlr.sc.virsat.model.extension.statemachines.model.Transition;
/**
 * Feature for creating connections in an state machine diagram. 
 * @author bell_Er 
 * */
public abstract class AbstractConnectionCreateFeature  extends AbstractCreateConnectionFeature {
	
	/**	
	 * Default constructor.	 
	 * @param fp the feature provider.	
	 * @param name the name
	 * @param description the description
	 */
	public AbstractConnectionCreateFeature(IFeatureProvider fp, String name, String description) {
		super(fp, name, description);
	}

	@Override
	public boolean canCreate(ICreateConnectionContext context) {
		// Check if the desired anchors are properly linked

		Anchor sourceAnchor = context.getSourceAnchor();
		Anchor targetAnchor = context.getTargetAnchor();
		
		PictogramElement sourcePictogramElement = context.getSourcePictogramElement();
		PictogramElement targetPictogramElement = context.getTargetPictogramElement();
		
		if (sourceAnchor == null && sourcePictogramElement instanceof ContainerShape) {
			ContainerShape cs = (ContainerShape) sourcePictogramElement;
			if (!cs.getChildren().isEmpty() && cs.getChildren().get(0) instanceof ContainerShape) {
				cs = (ContainerShape) cs.getChildren().get(0);
				if (!cs.getAnchors().isEmpty()) {
					sourceAnchor = cs.getAnchors().get(0);
				}
			}
		}

		if (targetAnchor == null && targetPictogramElement instanceof ContainerShape) {
			ContainerShape cs = (ContainerShape) targetPictogramElement;
			if (!cs.getChildren().isEmpty() && cs.getChildren().get(0) instanceof ContainerShape) {
				cs = (ContainerShape) cs.getChildren().get(0);
				if (!cs.getAnchors().isEmpty()) {
					targetAnchor = cs.getAnchors().get(0);
				}
			}
		}

		Object source = getBusinessObjectForPictogramElement(sourceAnchor);
		Object target = getBusinessObjectForPictogramElement(targetAnchor);

		State state1 = (State) source;
		State state2 = (State) target;
		if (state1 == null || state2 == null) {
			return false;
		}
		boolean candDraw = canDraw(state1, state2);
		if (source instanceof State && target instanceof State && source != target && candDraw) {	
			State s = (State) source;
			return DiagramHelper.hasBothWritePermission(s.getTypeInstance(), sourceAnchor);
		}
		return false;
	}

	/**	
	 * if a connection can be made between that two states
	 * @param state1 first state
	 * @param state2 second state
	 * @return can draw
	 */
	public abstract boolean canDraw(State state1, State state2);
	
	@Override
	public String getCreateImageId() {
		return Transition.FULL_QUALIFIED_CATEGORY_NAME;
	}
	
	@Override
	public Connection create(ICreateConnectionContext context) {
		Connection newConnection = null;
		
		Anchor sourceAnchor = context.getSourceAnchor();
		Anchor targetAnchor = context.getTargetAnchor();

		if (sourceAnchor == null) {
			ContainerShape cs = (ContainerShape) context.getSourcePictogramElement();
			cs = (ContainerShape) cs.getChildren().get(0);
			sourceAnchor = cs.getAnchors().get(0);
		}

		if (targetAnchor == null) {
			ContainerShape cs = (ContainerShape) context.getTargetPictogramElement();
			cs = (ContainerShape) cs.getChildren().get(0);
			targetAnchor = cs.getAnchors().get(0);
		}

		State source = (State) getBusinessObjectForPictogramElement(sourceAnchor);
		State target = (State) getBusinessObjectForPictogramElement(targetAnchor);

		if (source != null && target != null) {
			StateMachine stateMachine = source.getParentCaBeanOfClass(StateMachine.class);
			Concept concept = stateMachine.getConcept();
			AddConnectionContext addContext = new AddConnectionContext(sourceAnchor, targetAnchor);
			addConnection(addContext, source, target, stateMachine, concept);
			newConnection = (Connection) getFeatureProvider().addIfPossible(addContext);
		}

		return newConnection;

	}

	@Override
	public boolean canStartConnection(ICreateConnectionContext context) {
		// Check if start anchor is properly linked
		Anchor anchor = context.getSourceAnchor();
		PictogramElement pe = context.getSourcePictogramElement();
		Object sourceAnchor = getBusinessObjectForPictogramElement(anchor);
		Object sourcePe = getBusinessObjectForPictogramElement(pe);
		if (sourceAnchor instanceof State || sourcePe instanceof State) {
			return DiagramHelper.hasDiagramWritePermission(anchor);
		}

		return false;
	}
	
	/**	
	 * if the two states are in the same state machine
	 * @param state1 first state
	 * @param state2 second state
	 * @return true if they are
	 */
	public boolean statesAreInSameStateMachine(State state1, State state2) {
		StateMachine stateMachine1 = state1.getParentCaBeanOfClass(StateMachine.class);
		StateMachine stateMachine2 = state2.getParentCaBeanOfClass(StateMachine.class);	
		if (stateMachine1.equals(stateMachine2)) {			
			return true;
		}
		return false;
	}
	
	/**	
	 * Creating a connection
	 * @param addContext first state
	 * @param source the source anchor
	 * @param target the target anchor
	 * @param stateMachine the connection will be added in this state machine
	 * @param concept the concept
	 */
	public abstract void addConnection(AddConnectionContext addContext, State source, State target, StateMachine stateMachine, Concept concept);

}
