/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.statemachines.statespace;

import static java.util.stream.Collectors.flatMapping;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import java.util.function.Function;
import java.util.stream.IntStream;

import de.dlr.sc.virsat.model.extension.statemachines.model.AConstraint;
import de.dlr.sc.virsat.model.extension.statemachines.model.AllowsConstraint;
import de.dlr.sc.virsat.model.extension.statemachines.model.ForbidsConstraint;
import de.dlr.sc.virsat.model.extension.statemachines.model.State;
import de.dlr.sc.virsat.model.extension.statemachines.model.StateMachine;
import de.dlr.sc.virsat.model.extension.statemachines.model.Transition;

/**
 * A state space explorer allows enumerating the system states of a system composed of multiple state machines.
 * @author chrs_ph
 */
public class StateSpaceExplorer {
	
	public class SystemState {
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + Arrays.hashCode(localStates);
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj) {
				return true;
			}
			if (obj == null) {
				return false;
			}
			if (getClass() != obj.getClass()) {
				return false;
			}
			SystemState other = (SystemState) obj;
			return Arrays.equals(localStates, other.localStates);
		}

		private State[] localStates;
		
		private SystemState(State[] localStates) {
			this.localStates = localStates;
		}
		
		private SystemState createUpdatedState(int stateMachine, State newState) {
			var updatedLocalStates = Arrays.copyOf(localStates, localStates.length);
			updatedLocalStates[stateMachine] = newState;
			
			return new SystemState(updatedLocalStates);
		}
		
		public List<State> getLocalStates() {
			return Arrays.asList(localStates);
		}
		
		private StateSpaceExplorer getEnclosingInstance() {
			return StateSpaceExplorer.this;
		}
		
		@Override
		public String toString() {
			return Arrays.stream(localStates).map(State::getName).collect(joining(", "));
		}
	}
	
	public class SystemTransition {
		private SystemState from;
		private SystemState to;
		private List<Transition> localTransitions;
		
		private SystemTransition(SystemState from, List<Transition> localTransitions, SystemState to) {
			this.from = from;
			this.to = to;
			this.localTransitions = localTransitions;
		}
		
		public SystemState getFrom() {
			return from;
		}
		
		public SystemState getTo() {
			return to;
		}
		
		public List<Transition> getLocalTransitions() {
			return localTransitions;
		}
	}
	
	/**
	 * For the requiring state to be active, at least one of the required states must be active.
	 */
	private class RequiresAnyConstraint {
		private State requiring;
		private Set<State> required;
		
		RequiresAnyConstraint(State requiring, Set<State> required) {
			this.requiring = requiring;
			this.required = required;
		}
	}
	
	private StateMachine[] stateMachines;
	private List<Map<State, List<Transition>>> localSucc;
	private List<AConstraint> forbidsConstraints;
	private List<RequiresAnyConstraint> disjunctiveRequiresConstraints;
	
	public StateSpaceExplorer(List<StateMachine> stateMachines) {
		this.stateMachines = stateMachines.toArray(new StateMachine[0]);
		
		buildLocalSuccessorMaps();
		collectForbidsConstraints();
		collectRequiresConstraints();
	}
	
	/**
	 * Compute the list of all initial system states. In case none of the initial states satisfies the constraints, the list of initial system states is empty.
	 */
	public List<SystemState> getInitialStates() {
		// As of right now, each state machine can only have a single initial state. Thus, a cross-product is not necessary here.
		var initialStates = new State[stateMachines.length];
		
		IntStream.range(0, stateMachines.length).forEachOrdered(i -> initialStates[i] = stateMachines[i].getInitialState());
		var initialState = new SystemState(initialStates);
		
		if (isValidSystemState(initialState)) {
			return Collections.singletonList(initialState);
		} else {
			return Collections.emptyList();
		}
	}
	
	/**
	 * Get the successor system states of a given system state.
	 */
	public List<SystemTransition> getSuccessors(SystemState from) {
		if (from.getEnclosingInstance() != this) {
			throw new IllegalArgumentException("The given system state must not have been generated by another StateSpaceExplorer instance.");
		}
		
		return IntStream.range(0, stateMachines.length).mapToObj(i -> getLocalSuccessors(from, i).stream().map(t -> {
			SystemState to = from.createUpdatedState(i, t.getStateTo());
			return new SystemTransition(from, Collections.singletonList(t), to);
		}).filter(t -> isValidSystemState(t.getTo()))).collect(flatMapping(Function.identity(), toList()));
	}
	
	/**
	 * Returns a set of all reachable system states.
	 */
	public Set<SystemState> getReachableStates() {
		var stack = new Stack<Iterator<SystemState>>();
		var visited = new HashSet<SystemState>();
		
		stack.push(getInitialStates().iterator());
		
		while (!stack.isEmpty()) {
			var it = stack.peek();
			
			if (!it.hasNext()) {
				stack.pop();
			} else {
				var s = it.next();
				
				if (visited.contains(s)) {
					continue;
				} else {
					visited.add(s);
					stack.push(getSuccessors(s).stream().map(SystemTransition::getTo).iterator());
				}
			}
		}
		
		return visited;
	}
	
	private List<Transition> getLocalSuccessors(SystemState state, int stateMachine) {
		return localSucc.get(stateMachine).getOrDefault(state.localStates[stateMachine], Collections.emptyList());
	}
	
	private boolean isValidSystemState(SystemState state) {
		var localStates = Set.of(state.localStates);
		return !violatesForbidsConstraint(localStates) && !violatesRequiresConstraint(localStates);
	}
	
	private boolean violatesForbidsConstraint(Set<State> localStates) {
		return forbidsConstraints.stream().anyMatch(c -> localStates.contains(c.getStateConstraining()) && localStates.contains(c.getStateInfluenced()));
	}
	
	private boolean violatesRequiresConstraint(Set<State> localStates) {
		return disjunctiveRequiresConstraints.stream().anyMatch(c -> localStates.contains(c.requiring) && intersect(localStates, c.required).isEmpty());
	}
	
	private static <T> Set<T> intersect(Set<T> x, Set<T> y) {
		var result = new HashSet<T>(x);
		result.retainAll(y);
		return result;
	}
	
	/**
	 * For faster lookup of local enabled transitions, a map from local state to outgoing transitions is created for each state machine.
	 */
	private void buildLocalSuccessorMaps() {
		localSucc = new ArrayList<>(stateMachines.length);
		
		IntStream.range(0, stateMachines.length).forEachOrdered(i -> {
			localSucc.add(stateMachines[i].getTransitions().stream().collect(groupingBy(Transition::getStateFrom)));
		});
	}
	
	/**
	 * Collect the forbids constraints from all state machines.
	 */
	private void collectForbidsConstraints() {
		forbidsConstraints = Arrays.stream(stateMachines)
				.flatMap(sm -> sm.getConstraints().stream())
				.filter(c -> c instanceof ForbidsConstraint)
				.collect(toList());
	}
	
	/**
	 * Collect the requires constraints from all state machines.
	 */
	private void collectRequiresConstraints() {
		disjunctiveRequiresConstraints = new ArrayList<>();
		
		// group all constraints together that originate from the same state
		var groupedByFrom = Arrays.stream(stateMachines)
				.flatMap(sm -> sm.getConstraints().stream())
				.filter(c -> c instanceof AllowsConstraint)
				.collect(groupingBy(AConstraint::getStateConstraining));
		
		groupedByFrom.entrySet().stream().forEach(entry -> {
			// partition those groups again w.r.t. the targeted state machine
			var groupedByStateMachine = entry.getValue().stream().collect(groupingBy(c -> getContainingStateMachine(c.getStateInfluenced())));
			groupedByStateMachine.entrySet().forEach(innerEntry -> {
				// from all target states that are in the same state machine, only at least one has to be active
				var required = innerEntry.getValue().stream().map(AConstraint::getStateInfluenced).collect(toSet());
				disjunctiveRequiresConstraints.add(new RequiresAnyConstraint(entry.getKey(), required));
			});
		});
	}
	
	private static StateMachine getContainingStateMachine(State state) {
		return state.getParentCaBeanOfClass(StateMachine.class);
	}
}
