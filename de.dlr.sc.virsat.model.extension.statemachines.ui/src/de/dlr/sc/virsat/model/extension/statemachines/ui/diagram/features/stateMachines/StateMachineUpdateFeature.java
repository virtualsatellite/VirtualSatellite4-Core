/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.statemachines.ui.diagram.features.stateMachines;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.IReason;
import org.eclipse.graphiti.features.IRemoveFeature;
import org.eclipse.graphiti.features.context.IUpdateContext;
import org.eclipse.graphiti.features.context.impl.RemoveContext;
import org.eclipse.graphiti.features.impl.Reason;
import org.eclipse.graphiti.mm.algorithms.Ellipse;
import org.eclipse.graphiti.mm.algorithms.Image;
import org.eclipse.graphiti.mm.algorithms.Text;
import org.eclipse.graphiti.mm.pictograms.Anchor;
import org.eclipse.graphiti.mm.pictograms.Connection;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.mm.pictograms.Shape;
import de.dlr.sc.virsat.graphiti.ui.diagram.feature.VirSatUpdateFeature;
import de.dlr.sc.virsat.model.concept.list.IBeanList;
import de.dlr.sc.virsat.model.extension.statemachines.model.AConstraint;
import de.dlr.sc.virsat.model.extension.statemachines.model.State;
import de.dlr.sc.virsat.model.extension.statemachines.model.StateMachine;
import de.dlr.sc.virsat.model.extension.statemachines.model.Transition;
import de.dlr.sc.virsat.model.extension.statemachines.ui.diagram.features.states.StateAddFeature;
/** 
 * Update feature for updating state machine
 * @author bell_er
 */
public class StateMachineUpdateFeature extends VirSatUpdateFeature {
	/**
	 * Default constructor
	 * @param fp the diagram type provider
	 */
	public StateMachineUpdateFeature(IFeatureProvider fp) {
		super(fp);
	}

	@Override
	public IReason updateNeeded(IUpdateContext context) {
		// retrieve name from pictogram model
		String pictogramName = null;
		PictogramElement pictogramElement = context.getPictogramElement();
		StateMachine stateMachine =  (StateMachine) getBusinessObjectForPictogramElement(pictogramElement);

		if (stateMachine == null) {
			return Reason.createTrueReason("StateMachine is deleted");
		}
		
		State initialState = stateMachine.getInitialState();
		String businessName = stateMachine.getName();
		if (pictogramElement instanceof ContainerShape) {
			ContainerShape cs = (ContainerShape) pictogramElement;
			// Look for the state machine Name
			for (Shape shape : cs.getChildren()) {
				if (shape.getGraphicsAlgorithm() instanceof Text) {
					Text text = (Text) shape.getGraphicsAlgorithm();
					if (!text.getValue().contains("«")) {
						pictogramName = text.getValue();
						if (!pictogramName.equals(businessName)) {
							return Reason.createTrueReason("StateMachine name is changed");
						}
					}
				}
			}

			for (Shape shape : cs.getChildren()) {
				// Look for deleted states
				if (shape instanceof ContainerShape) {
					State state = (State) getBusinessObjectForPictogramElement(shape);

					if (state == null || !stateMachine.getStates().contains(state)) {
						return Reason.createTrueReason("A State is deleted");
					}
					String stateName = state.getName();
					//Look for the state names
					ContainerShape aStateShape = (ContainerShape) shape;
					for (Shape stateNameShape : aStateShape.getChildren()) {
						if (stateNameShape.getGraphicsAlgorithm() instanceof Image) {
							if (!stateNameShape.isVisible() && state.equals(initialState)) {
								return Reason.createTrueReason("Initial state is changed");
							}

						}

						if (stateNameShape.getGraphicsAlgorithm() instanceof Text) {
							Text text = (Text) stateNameShape.getGraphicsAlgorithm();
							pictogramName = text.getValue();
							if (!pictogramName.equals(stateName)) {
								return Reason.createTrueReason("State Name is out of date");
							}
						}
					}
				}
			}
			// compare connections
			if (!connectionsChanged(stateMachine)) {
				return Reason.createTrueReason("A connection is changed");
			}
		} 
		return Reason.createFalseReason();
	}
	
	/**
	 * Method to update connections
	 * @param stateMachine the stateMachine
	 * @param diagramStates the states displayed on the diagram
	 */
	private void updateConnections(StateMachine stateMachine, List<State> diagramStates) {
		// select the deleted ones
		List<Connection> allConnections = getDiagram().getConnections();
		List<Connection> toRemove = new ArrayList<Connection>();
		for (Connection c : allConnections) {
			Object obj = getBusinessObjectForPictogramElement(c.getStart());
			if (obj instanceof State) {
				State state = (State) obj;
				StateMachine sm = state.getParentCaBeanOfClass(StateMachine.class);
				if (sm.equals(stateMachine)) {
					obj = getBusinessObjectForPictogramElement(c);
					if (obj == null) {
						toRemove.add(c);
					}
				}
			}
		}

		// update the changed connections
		for (Connection c : allConnections) {
			Object connectionObj = getBusinessObjectForPictogramElement(c);
			IBeanList<State> states = stateMachine.getStates();
			final PictogramElement[] pes = getFeatureProvider().getDiagramTypeProvider().getNotificationService().calculateRelatedPictogramElements(states.toArray());
			if (connectionObj instanceof Transition) {
				Transition diagramTransition = (Transition) connectionObj;
				for (Transition t : stateMachine.getTransitions()) {
					if (t.getUuid().equals(diagramTransition.getUuid())) {
						if (diagramStates.contains(t.getStateFrom()) && diagramStates.contains(t.getStateTo())) {
							findAndSetTheStartEndState(pes,  c,  t);
						} else {
							toRemove.add(c);
						}
					}
				}
			} else if (connectionObj instanceof AConstraint) {
				AConstraint diagramConstraint = (AConstraint) connectionObj;
				for (AConstraint constraint : stateMachine.getConstraints()) {
					if (diagramConstraint.getUuid().equals(constraint.getUuid())) {
						PictogramElement influencedPe = getInfluencedState(diagramConstraint);
						if (diagramStates.contains(constraint.getStateConstraining()) && (influencedPe != null)) {
							c.setEnd((Anchor) influencedPe);
							findAndSetTheStartState(pes, c, constraint);
						} else {
							toRemove.add(c);
						}
					}
				}
			}
		}
		// remove the deleted connections and the connections without start or end states
		for (Iterator<Connection> iterator = toRemove.iterator(); iterator.hasNext();) {
			RemoveContext removeContext = new RemoveContext(iterator.next());
			IRemoveFeature feature = getFeatureProvider().getRemoveFeature(removeContext);
			feature.remove(removeContext);

		}


		// update the changed names
		for (Connection c : allConnections) {
			Object connectionObj = getBusinessObjectForPictogramElement(c);
			if (connectionObj instanceof Transition) {
				Transition diagramTransaction = (Transition) connectionObj;
				for (Transition t : stateMachine.getTransitions()) {
					if (t.getUuid().equals(diagramTransaction.getUuid())) {
						Text text = (Text) c.getConnectionDecorators().get(0).getGraphicsAlgorithm();
						text.setValue(t.getName());		
					}
				}
			}
		}

	}

	/**
	 * Method to find the relevant state and set it as start state
	 * @param pes pictogram elements in the diagram (possible candidates for the relevant state)
	 * @param c the connection to be modified
	 * @param t the transaction to be modified 
	 */
	private void findAndSetTheStartEndState(PictogramElement[] pes, Connection c, Transition t) {
		for (PictogramElement pe : pes) {
			if (pe instanceof Anchor) {
				State state = (State) getBusinessObjectForPictogramElement(pe);
				if (t.getStateFrom().equals(state)) {
					c.setStart((Anchor) pe);
				}
				if (t.getStateTo().equals(state)) {
					c.setEnd((Anchor) pe);
				}
			}
		}
	}

	/**
	 * Method to find the relevant state and set it as start state
	 * @param pes pictogram elements in the diagram (possible candidates for the relevant state)
	 * @param c the connection to be modified
	 * @param constraint the constraint to be modified 
	 */
	private void findAndSetTheStartState(PictogramElement[] pes, Connection c, AConstraint constraint) {
		for (PictogramElement pe : pes) {
			if (pe instanceof Anchor) {
				State state = (State) getBusinessObjectForPictogramElement(pe);
				if (constraint.getStateConstraining().equals(state)) {
					c.setStart((Anchor) pe);
				}
			}
		}
	}

	/**
	 * Method to find the influenced state from another state machine
	 * @param constraint the constraint which influences the state
	 * @return null if the state is not in the diagram, otherwise return the pictogram element of the state
	 */
	private PictogramElement getInfluencedState(AConstraint constraint) {
		State stateInfluenced = constraint.getStateInfluenced();
		StateMachine stateMachine = stateInfluenced.getParentCaBeanOfClass(StateMachine.class);	
		final PictogramElement[] pes = getFeatureProvider().getDiagramTypeProvider().getNotificationService().calculateRelatedPictogramElements(stateMachine.getStates().toArray());
		for (PictogramElement pe : pes) {
			if (pe instanceof Anchor) {
				State state = (State) getBusinessObjectForPictogramElement(pe);
				if (stateInfluenced.equals(state)) {
					return pe;
				} 
			}
		}
		return null;

	}

	/**
	 * Method to check if connections are changed
	 * @param stateMachine the stateMachine
	 * @return true if they are changed
	 */
	private boolean connectionsChanged(StateMachine stateMachine) {

		// get the graphical information
		List<Connection> allConnections = getDiagram().getConnections();
		for (Connection c : allConnections) {
			Object connectionObj = getBusinessObjectForPictogramElement(c);
			if (connectionObj == null) {
				return false;
			}
			if (connectionObj instanceof Transition) {
				Transition diagramTransition = (Transition) connectionObj;
				for (Transition t : stateMachine.getTransitions()) {
					if (t.getUuid().equals(diagramTransition.getUuid())) {
						Text text = (Text) c.getConnectionDecorators().get(0).getGraphicsAlgorithm();
						if (!t.getName().equals(text.getValue())) {
							return false;
						}
						if (!t.getStateFrom().equals((State) getBusinessObjectForPictogramElement(c.getStart()))) {
							return false;
						}
						if (!t.getStateTo().equals((State) getBusinessObjectForPictogramElement(c.getEnd()))) {
							return false;
						}
					}
				}
			} else if (connectionObj instanceof AConstraint) {
				AConstraint diagramConstraint = (AConstraint) connectionObj;
				for (AConstraint constraint : stateMachine.getConstraints()) {
					if (constraint.getUuid().equals(diagramConstraint.getUuid())) {
						if (!constraint.getStateConstraining().equals((State) getBusinessObjectForPictogramElement(c.getStart()))) {
							return false;
						}
						if (!constraint.getStateInfluenced().equals((State) getBusinessObjectForPictogramElement(c.getEnd()))) {
							return false;
						}
					}
				}
			}
		}
		return true;

	}

	@Override
	public boolean update(IUpdateContext context) {

		// retrieve name from business model
		PictogramElement pictogramElement = context.getPictogramElement();
		Object obj = getBusinessObjectForPictogramElement(pictogramElement);
		if (obj == null) {
			RemoveContext removeContext = new RemoveContext(pictogramElement);
			IRemoveFeature feature = getFeatureProvider().getRemoveFeature(removeContext);
			feature.remove(removeContext);
			return true;
		}

		StateMachine stateMachine = null;
		String businessName = "";
		if (obj instanceof StateMachine) {
			stateMachine = (StateMachine) obj;
			businessName = stateMachine.getName();
		}

		boolean changeDuringUpdate = false;


		// this list will hold the states on the diagram
		List<State> diagramStates = new ArrayList<>();  

		// Set name in pictogram model
		if (pictogramElement instanceof ContainerShape) {
			ContainerShape cs = (ContainerShape) pictogramElement;
			for (Shape shape : cs.getChildren()) {
				if (shape.getGraphicsAlgorithm() instanceof Text) {
					Text text = (Text) shape.getGraphicsAlgorithm();
					if (!text.getValue().contains("«")) {
						text.setValue(businessName);
						changeDuringUpdate = true;
					}
				}
			}

			List<PictogramElement> toRemove = new ArrayList<>();      
			for (Shape shape : cs.getChildren()) {      	
				if (shape instanceof ContainerShape) {
					State state = (State) getBusinessObjectForPictogramElement(shape);
					if (state == null || !stateMachine.getStates().contains(state)) {
						toRemove.add(shape);
						continue;
					} else {
						diagramStates.add(state);
					}
					String stateName = state.getName();
					//Look for the state names
					ContainerShape aStateShape = (ContainerShape) shape;

					if (state.equals(stateMachine.getInitialState())) {
						setInitialState(aStateShape);
					} else {
						setNormalState(aStateShape);
					}

					for (Shape stateNameShape : aStateShape.getChildren()) {
						
						if (stateNameShape.getGraphicsAlgorithm() instanceof Text) {
							Text text = (Text) stateNameShape.getGraphicsAlgorithm();
							String pictogramName = text.getValue();
							if (!pictogramName.equals(stateName)) {
								text.setValue(stateName);
							}
						}
					}
					changeDuringUpdate = true;
				}
			}

			for (PictogramElement pe : toRemove) {
				RemoveContext removeContext = new RemoveContext(pe);
				IRemoveFeature feature = getFeatureProvider().getRemoveFeature(removeContext);
				feature.remove(removeContext);
			}
		}


		// update connections
		updateConnections(stateMachine, diagramStates);

		return changeDuringUpdate;
	}

	/**
	 * Initial states are drawn with an arrow image and they are wider
	 * @param aStateShape the big rectangle
	 */
	private void setInitialState(ContainerShape aStateShape) {
		for (Shape stateNameShape : aStateShape.getChildren()) {
			if (stateNameShape.getGraphicsAlgorithm() instanceof Ellipse) {
				stateNameShape.getGraphicsAlgorithm().setX(StateAddFeature.IMAGE_WIDTH);
			}
			if (stateNameShape.getGraphicsAlgorithm() instanceof Image) {
				stateNameShape.setVisible(true);
			}
			if (stateNameShape.getGraphicsAlgorithm() instanceof Text) {
				stateNameShape.getGraphicsAlgorithm().setX(StateAddFeature.IMAGE_WIDTH);
			}
			aStateShape.getGraphicsAlgorithm().setWidth(StateAddFeature.DEFAULT_WIDTH);
		}
	}



	/**
	 * Normal states do not have an arrow image and they are narrower
	 * @param aStateShape the big rectangle
	 */
	private void setNormalState(ContainerShape aStateShape) {
		for (Shape stateNameShape : aStateShape.getChildren()) {
			if (stateNameShape.getGraphicsAlgorithm() instanceof Ellipse) {
				stateNameShape.getGraphicsAlgorithm().setX(0);
			}
			if (stateNameShape.getGraphicsAlgorithm() instanceof Image) {
				stateNameShape.setVisible(false);
			}
			if (stateNameShape.getGraphicsAlgorithm() instanceof Text) {
				stateNameShape.getGraphicsAlgorithm().setX(0);
			}
			aStateShape.getGraphicsAlgorithm().setWidth(StateAddFeature.DEFAULT_WIDTH - StateAddFeature.IMAGE_WIDTH);
		}
	}

	@Override
	public boolean canUpdate(IUpdateContext context) {
		Object object = getBusinessObjectForPictogramElement(context.getPictogramElement());
		return (object instanceof StateMachine || object == null) && super.canUpdate(context);
	}
}
