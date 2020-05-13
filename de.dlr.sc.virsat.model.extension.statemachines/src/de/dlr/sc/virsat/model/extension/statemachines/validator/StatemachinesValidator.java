/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.statemachines.validator;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.eclipse.core.resources.IMarker;

import de.dlr.sc.virsat.model.concept.types.category.ABeanCategoryAssignment;
import de.dlr.sc.virsat.model.concept.types.util.BeanCategoryAssignmentHelper;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ReferencePropertyInstance;
import de.dlr.sc.virsat.model.dvlm.general.IUuid;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import de.dlr.sc.virsat.model.dvlm.validator.IStructuralElementInstanceValidator;
import de.dlr.sc.virsat.model.extension.statemachines.marker.VirSatStateMachinesMarkerHelper;
import de.dlr.sc.virsat.model.extension.statemachines.model.AConstraint;
import de.dlr.sc.virsat.model.extension.statemachines.model.State;
import de.dlr.sc.virsat.model.extension.statemachines.model.StateMachine;
import de.dlr.sc.virsat.model.extension.statemachines.model.Transition;


// *****************************************************************
// * Class Declaration
// *****************************************************************

/**
 * Auto Generated Class inheriting from Generator Gap Class
 * 
 * This class is generated once, do your changes here
 * 
 * Concept for modelling State Diagrams
 * 
 */
public class StatemachinesValidator extends AStatemachinesValidator implements IStructuralElementInstanceValidator {
	public static final String WARNING_SAME_STATES = "Cannot constrain a state from same state machine ";
	public static final String WARNING_DUPLICATE_NAMES = "There are duplicate names in namespace ";
	public static final String WARNING_NO_CONSTRAINING_STATE = "The constraining state is not set for some constraint in ";
	public static final String WARNING_NO_INFLUENCED_STATE = "The influenced state is not set for some constraint in ";
	public static final String WARNING_NO_STATE_FROM = "The stateFrom is not set  ";
	public static final String WARNING_NO_STATE_TO = "The stateTo is not set ";
	public static final String WARNING_INITIAL_STATE_NOT_SET = "The initial state is not set ";
	
	private BeanCategoryAssignmentHelper bCaHelper = new BeanCategoryAssignmentHelper();
	private VirSatStateMachinesMarkerHelper vcmHelper = new VirSatStateMachinesMarkerHelper();
	
	@Override
	public boolean validate(StructuralElementInstance sei) {
		/**
		 * class to find duplicate state names
		 * 
		 */
		class StatemMachinesComparator implements Comparator<ABeanCategoryAssignment> {
			@Override
			public int compare(ABeanCategoryAssignment o1, ABeanCategoryAssignment o2) {
				return o1.getName().compareTo(o2.getName());
			}
		}
		
		List<StateMachine> stateMachines = bCaHelper.getAllBeanCategories(sei, StateMachine.class);
		boolean allInfoValid = true;
		
		for (StateMachine sm : stateMachines) {
			if (sm.getInitialState() == null) {
				String fqn = "\"" + sm.getName() + "\"";	
				vcmHelper.createSMValidationMarker(IMarker.SEVERITY_WARNING, WARNING_INITIAL_STATE_NOT_SET + fqn, sm.getTypeInstance());
				allInfoValid = false;
			}
			
			List<State> states = sm.getStates();
			List<State> duplicateStates = new ArrayList<State>();
			Set<State> stateSet = new TreeSet<State>(new StatemMachinesComparator());
			for (State s : states) {
			    if (!stateSet.add(s)) {
			        duplicateStates.add(s);
			    }
			}
			
			for (State s : duplicateStates) {
				String fqn = "\"" + s.getTypeInstance().getFullQualifiedInstanceName() + "\"";	
				vcmHelper.createSMValidationMarker(IMarker.SEVERITY_WARNING, WARNING_DUPLICATE_NAMES + fqn, s.getTypeInstance());
				allInfoValid = false;
			}
			
			List <Transition> transitions = sm.getTransitions();
			for (Transition t : transitions) {
				
				if (t.getStateFrom() == null) {
					String fqn = "\"" + t.getTypeInstance().getFullQualifiedInstanceName() + "\"";	
					vcmHelper.createSMValidationMarker(IMarker.SEVERITY_WARNING, WARNING_NO_STATE_FROM + fqn, t.getTypeInstance());
					allInfoValid = false;
				} 
				if (t.getStateTo() == null) {
					String fqn = "\"" + t.getTypeInstance().getFullQualifiedInstanceName() + "\"";	
					vcmHelper.createSMValidationMarker(IMarker.SEVERITY_WARNING, WARNING_NO_STATE_TO + fqn, t.getTypeInstance());
					allInfoValid = false;
				}
			}

			List<AConstraint> constraints = sm.getConstraints();
			for (AConstraint constraint : constraints) {
				if (constraint.getStateConstraining() == null) {
					StateMachine containerSM = constraint.getParentCaBeanOfClass(StateMachine.class);
					String fqn = "\"" + containerSM.getName() + "\"";	
					ReferencePropertyInstance rpiConst = constraint.getStateConstrainingReferenceProperty();
					IUuid iUuid = rpiConst;
					vcmHelper.createSMValidationMarker(IMarker.SEVERITY_WARNING, WARNING_NO_CONSTRAINING_STATE + fqn, iUuid);
					allInfoValid = false;
				} else if (constraint.getStateInfluenced() == null) {
					StateMachine containerSM = constraint.getParentCaBeanOfClass(StateMachine.class);
					String fqn = "\"" + containerSM.getName() + "\"";	
					ReferencePropertyInstance rpiConst = constraint.getStateInfluencedReferenceProperty();
					IUuid iUuid = rpiConst;
					vcmHelper.createSMValidationMarker(IMarker.SEVERITY_WARNING, WARNING_NO_INFLUENCED_STATE + fqn, iUuid);
					allInfoValid = false;
				} else {
					StateMachine smConstraining = constraint.getStateConstraining().getParentCaBeanOfClass(StateMachine.class);
					StateMachine smInfluenced = constraint.getStateInfluenced().getParentCaBeanOfClass(StateMachine.class);
					if (smConstraining.getUuid().equals(smInfluenced.getUuid())) {
						String fqn = "\"" + smConstraining.getName() + "\"";	
						ReferencePropertyInstance rpiConst = constraint.getStateInfluencedReferenceProperty();
						IUuid iUuid = rpiConst;
						vcmHelper.createSMValidationMarker(IMarker.SEVERITY_WARNING, WARNING_SAME_STATES + fqn, iUuid);
						allInfoValid = false;
					}
				}
				
			}

		}
		
		return super.validate(sei) && allInfoValid;
	}
}
