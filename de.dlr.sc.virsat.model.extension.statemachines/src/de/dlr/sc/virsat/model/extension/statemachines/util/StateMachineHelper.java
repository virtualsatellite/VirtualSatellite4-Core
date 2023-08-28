/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.statemachines.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import de.dlr.sc.virsat.model.extension.statemachines.model.AConstraint;
import de.dlr.sc.virsat.model.extension.statemachines.model.StateMachine;

/**
 * Helpers for state machines.
 * @author chrs_ph
 *
 */
public class StateMachineHelper {
	private StateMachineHelper() { }
	
	/**
	 * Extends the given set of state machines such that all state machines referenced by constraints are contained.
	 * @param stateMachines
	 * The set of state machines that must be closed under constraints.
	 * @return
	 * An extended set of state machines that does not contain dangling constraints.
	 */
	public static Set<StateMachine> transitiveClosureOverConstraints(Set<StateMachine> stateMachines) {
		var result = new HashSet<StateMachine>(stateMachines);

		Set<StateMachine> extension;
		do {
			extension = result.stream()
				.flatMap(sm -> sm.getConstraints().stream().flatMap(c -> referencedStateMachines(c).stream()))
				.filter(sm -> !result.contains(sm))
				.collect(Collectors.toSet());
			
			result.addAll(extension);
		} while (!extension.isEmpty());
		
		return result;
	}
	
	/**
	 * Returns the list of state machines that are connected by the given constraint.
	 */
	private static List<StateMachine> referencedStateMachines(AConstraint constraint) {
		var result = new ArrayList<StateMachine>(2);
		
		result.add(constraint.getStateConstraining().getParentCaBeanOfClass(StateMachine.class));
		result.add(constraint.getStateInfluenced().getParentCaBeanOfClass(StateMachine.class));
		
		return result;
	}
}
