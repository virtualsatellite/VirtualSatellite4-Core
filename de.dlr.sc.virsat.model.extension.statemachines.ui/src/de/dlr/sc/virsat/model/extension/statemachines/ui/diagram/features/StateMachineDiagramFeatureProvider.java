/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.statemachines.ui.diagram.features;

import org.eclipse.graphiti.dt.IDiagramTypeProvider;
import org.eclipse.graphiti.features.IAddFeature;
import org.eclipse.graphiti.features.ICopyFeature;
import org.eclipse.graphiti.features.ICreateConnectionFeature;
import org.eclipse.graphiti.features.ICreateFeature;
import org.eclipse.graphiti.features.IDeleteFeature;
import org.eclipse.graphiti.features.IDirectEditingFeature;
import org.eclipse.graphiti.features.IPasteFeature;
import org.eclipse.graphiti.features.IReconnectionFeature;
import org.eclipse.graphiti.features.IRemoveFeature;
import org.eclipse.graphiti.features.IResizeShapeFeature;
import org.eclipse.graphiti.features.IUpdateFeature;
import org.eclipse.graphiti.features.context.IAddContext;
import org.eclipse.graphiti.features.context.ICopyContext;
import org.eclipse.graphiti.features.context.ICustomContext;
import org.eclipse.graphiti.features.context.IDeleteContext;
import org.eclipse.graphiti.features.context.IDirectEditingContext;
import org.eclipse.graphiti.features.context.IPasteContext;
import org.eclipse.graphiti.features.context.IReconnectionContext;
import org.eclipse.graphiti.features.context.IRemoveContext;
import org.eclipse.graphiti.features.context.IResizeShapeContext;
import org.eclipse.graphiti.features.context.IUpdateContext;
import org.eclipse.graphiti.features.custom.ICustomFeature;
import org.eclipse.graphiti.mm.algorithms.Text;
import org.eclipse.graphiti.mm.pictograms.Connection;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.ui.features.DefaultFeatureProvider;

import de.dlr.sc.virsat.graphiti.diagram.BeanIndependenceSolver;
import de.dlr.sc.virsat.graphiti.ui.diagram.feature.BeanDirectEditNameFeature;
import de.dlr.sc.virsat.graphiti.ui.diagram.feature.VirSatCategoryAssingmentCopyFeature;
import de.dlr.sc.virsat.graphiti.ui.diagram.feature.VirSatChangeColorFeature;
import de.dlr.sc.virsat.graphiti.ui.diagram.feature.VirsatCategoryAssignmentOpenEditorFeature;
import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;
import de.dlr.sc.virsat.model.extension.statemachines.model.AConstraint;
import de.dlr.sc.virsat.model.extension.statemachines.model.AllowsConstraint;
import de.dlr.sc.virsat.model.extension.statemachines.model.ForbidsConstraint;
import de.dlr.sc.virsat.model.extension.statemachines.model.State;
import de.dlr.sc.virsat.model.extension.statemachines.model.StateMachine;
import de.dlr.sc.virsat.model.extension.statemachines.model.Transition;
import de.dlr.sc.virsat.model.extension.statemachines.ui.diagram.features.constraints.AConstraintReconnectionFeature;
import de.dlr.sc.virsat.model.extension.statemachines.ui.diagram.features.constraints.AllowsConstraintAddFeature;
import de.dlr.sc.virsat.model.extension.statemachines.ui.diagram.features.constraints.AllowsConstraintCreateFeature;
import de.dlr.sc.virsat.model.extension.statemachines.ui.diagram.features.constraints.ForbidsConstraintAddFeature;
import de.dlr.sc.virsat.model.extension.statemachines.ui.diagram.features.constraints.ForbidsConstraintCreateFeature;
import de.dlr.sc.virsat.model.extension.statemachines.ui.diagram.features.stateMachines.StateMachineAddFeature;
import de.dlr.sc.virsat.model.extension.statemachines.ui.diagram.features.stateMachines.StateMachineResizeFeature;
import de.dlr.sc.virsat.model.extension.statemachines.ui.diagram.features.stateMachines.StateMachineUpdateFeature;
import de.dlr.sc.virsat.model.extension.statemachines.ui.diagram.features.states.StateAddFeature;
import de.dlr.sc.virsat.model.extension.statemachines.ui.diagram.features.states.StateChangeColorFeature;
import de.dlr.sc.virsat.model.extension.statemachines.ui.diagram.features.states.StateCreateFeature;
import de.dlr.sc.virsat.model.extension.statemachines.ui.diagram.features.states.StatePasteFeature;
import de.dlr.sc.virsat.model.extension.statemachines.ui.diagram.features.states.StateSetAsInitialStateFeature;
import de.dlr.sc.virsat.model.extension.statemachines.ui.diagram.features.states.StateUnsetAsInitialStateFeature;
import de.dlr.sc.virsat.model.extension.statemachines.ui.diagram.features.transitions.TransitionAddFeature;
import de.dlr.sc.virsat.model.extension.statemachines.ui.diagram.features.transitions.TransitionCreateFeature;
import de.dlr.sc.virsat.model.extension.statemachines.ui.diagram.features.transitions.TransitionReconnectionFeature;
/**
 * The State Machine feature provider provides all features for State Machine diagrams.
 * @author bell_er
 *
 */
public class StateMachineDiagramFeatureProvider extends DefaultFeatureProvider {
	private BeanIndependenceSolver beanIndependenceSolver;

	/**
	 * Default constructor
	 * @param dtp the diagram type provider
	 */

	public StateMachineDiagramFeatureProvider(IDiagramTypeProvider dtp) {
		super(dtp);
		beanIndependenceSolver = new BeanIndependenceSolver(dtp);
		setIndependenceSolver(beanIndependenceSolver);
	}
	
	@Override
	public IAddFeature getAddFeature(IAddContext context) {
		Object newObject = context.getNewObject();
		if (newObject instanceof CategoryAssignment) {		
			CategoryAssignment ca = (CategoryAssignment) newObject;
			if (ca.getType().getName().equals(State.class.getSimpleName())) {
				return new StateAddFeature(this);
			}	
			if (ca.getType().getName().equals(StateMachine.class.getSimpleName())) {
				return new StateMachineAddFeature(this);
			}
			if (ca.getType().getName().equals(Transition.class.getSimpleName())) {
				return new TransitionAddFeature(this);
			}
			if (ca.getType().getName().equals(AllowsConstraint.class.getSimpleName())) {
				return new AllowsConstraintAddFeature(this);
			}
			if (ca.getType().getName().equals(ForbidsConstraint.class.getSimpleName())) {
				return new ForbidsConstraintAddFeature(this);
			}
		}
		return super.getAddFeature(context);
	}
	
	@Override
	public ICustomFeature[] getCustomFeatures(ICustomContext context) {
		PictogramElement[] pe = context.getPictogramElements();
		Object object = getBusinessObjectForPictogramElement(pe[0]);
		if (object instanceof State) {
			State state = (State) object;
			StateMachine stateMachine = state.getParentCaBeanOfClass(StateMachine.class);
			State initialState = stateMachine.getInitialState();
			if (initialState == null ||  !(initialState.equals(state))) {
				return new ICustomFeature[] { new VirsatCategoryAssignmentOpenEditorFeature(this), new StateChangeColorFeature(this), new StateSetAsInitialStateFeature(this)};
			} else {
				return new ICustomFeature[] { new VirsatCategoryAssignmentOpenEditorFeature(this), new StateChangeColorFeature(this), new StateUnsetAsInitialStateFeature(this)};
			}
		}
		
		return new ICustomFeature[] { new VirsatCategoryAssignmentOpenEditorFeature(this), new VirSatChangeColorFeature(this)};
	} 
	
	@Override
	public IDeleteFeature getDeleteFeature(IDeleteContext context) {
		PictogramElement pe = context.getPictogramElement();
		if (pe.getGraphicsAlgorithm() instanceof Text) {
			return null;
		}
		
		Object newObject = getBusinessObjectForPictogramElement(pe);
		
		if (newObject == null) {
			return null;
		}
		
		return super.getDeleteFeature(context);
	}
	
	@Override	
	public ICreateFeature[] getCreateFeatures() {		
		return new ICreateFeature[] { new StateCreateFeature(this) };	
	}
	
	@Override	
	public ICreateConnectionFeature[] getCreateConnectionFeatures() {		
		return new ICreateConnectionFeature[] { new TransitionCreateFeature(this), new AllowsConstraintCreateFeature(this), new ForbidsConstraintCreateFeature(this), };	
	}

	@Override
	public IUpdateFeature getUpdateFeature(IUpdateContext context) {
		PictogramElement pictogramElement = context.getPictogramElement();
		if (pictogramElement instanceof Diagram) {
			return null;
		}
		
		Object object = getBusinessObjectForPictogramElement(pictogramElement);
		if ((object instanceof StateMachine || object == null) && pictogramElement instanceof ContainerShape) {
			return new StateMachineUpdateFeature(this);
		}
		return super.getUpdateFeature(context);
	}
	
	@Override
	public IReconnectionFeature getReconnectionFeature(IReconnectionContext context) {
		Connection connection = context.getConnection();
		Object object = getBusinessObjectForPictogramElement(connection);
		if (object instanceof Transition) {
			return new TransitionReconnectionFeature(this);
		}
		if (object instanceof AConstraint) {
			return new AConstraintReconnectionFeature(this);
		}
		return super.getReconnectionFeature(context);
	}
	
	@Override
	public IDirectEditingFeature getDirectEditingFeature(IDirectEditingContext context) {
		Object object = getBusinessObjectForPictogramElement(context.getPictogramElement());
		if (object instanceof StateMachine || object instanceof State || object instanceof Transition) {
			return new BeanDirectEditNameFeature(this);
		}
		return super.getDirectEditingFeature(context);
	}
	
	@Override
	public ICopyFeature getCopyFeature(ICopyContext context) {
		return new VirSatCategoryAssingmentCopyFeature(this);
	}

	@Override
	public IPasteFeature getPasteFeature(IPasteContext context) {
		return new StatePasteFeature(this);
	}
	
	@Override	
	public IResizeShapeFeature getResizeShapeFeature(IResizeShapeContext context) {		
		Object object = getBusinessObjectForPictogramElement(context.getPictogramElement());
		if (object instanceof StateMachine) {
			return new StateMachineResizeFeature(this);
		}
		return null;	
	}
	
	@Override
	public IRemoveFeature getRemoveFeature(IRemoveContext context) {
		PictogramElement pe = context.getPictogramElement();
		if (pe.getGraphicsAlgorithm() instanceof Text) {
			return null;
		}
		
		return super.getRemoveFeature(context);
	}
}


