/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.statemachines.simulator;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

import de.dlr.sc.virsat.model.concept.list.IBeanList;
import de.dlr.sc.virsat.model.extension.statemachines.model.AConstraint;
import de.dlr.sc.virsat.model.extension.statemachines.model.StateMachine;
import de.dlr.sc.virsat.model.extension.statemachines.model.Transition;

public class StateMachineSimulator {

	/**
	 * used to compute information for transition
	 * 
	 * @param a: name of the given state machine
	 * @param b: name of stateFrom
	 * @param c: name of stateTo
	 * @param d: name of gardStates
	 */

	private void updateMap(HashMap<String, HashMap<String, HashMap<String, String>>> map, String a, String b, String c, String d) {
		if (!map.containsKey(a)) {
			HashMap<String, HashMap<String, String>> m = new HashMap<String, HashMap<String, String>>();

			// Add here a hAshmap with strings 
			HashMap<String, String> strSet = new HashMap<String, String>();
			strSet.put(c, d);
			m.put(b, strSet);
			map.put(a, m);
		} else {
			if (!map.get(a).containsKey(b)) {
				HashMap<String, HashMap<String, String>> m1 = new HashMap<String, HashMap<String, String>>();

				m1 = map.get(a);

				HashMap<String, String> infStates = new HashMap<String, String>();
				infStates.put(c, d);

				m1.put(b, infStates);

				map.put(a, m1);
			} else {
				HashMap<String, HashMap<String, String>> m1 = new HashMap<String, HashMap<String, String>>();
				m1 = map.get(a);
				HashMap<String, String> infStates = new HashMap<String, String>();
				infStates = m1.get(b);
				infStates.put(c, d);
				m1.put(b, infStates);
				map.put(a, m1);

			}
		}
	}

	private HashMap<String, HashMap<String, HashMap<String, String>>> localEnabledTransitions = new HashMap<String, HashMap<String, HashMap<String, String>>>();
	private HashMap<String, HashMap<String, HashMap<String, String>>> stateConstraints = new HashMap<String, HashMap<String, HashMap<String, String>>>();
/**
 * // this function must be called before running POR, it is used to compute
	// enabled transitions for every local state
 * @param design
 */
	private void compLocalEnabledTrans(List<StateMachine> design) {

		for (StateMachine sm : design) {
			IBeanList<Transition> trans = sm.getTransitions();

			for (Transition transition : trans) {
				updateMap(localEnabledTransitions, sm.getName(), transition.getStateFrom().getName(),
						transition.getStateTo().getName(), transition.getTrigger().getName());
			}

		}
	}

/**
 * Calculates 
 * @param gState
 */
	private void getGlobalEnabledTrans(GlobalState gState) {
		for (Map.Entry<String, String> smState : gState.smStates.entrySet()) {

			HashMap<String, String> localEnabledStates = new HashMap<String, String>();

			if (localEnabledTransitions.containsKey(smState.getKey())
					&& localEnabledTransitions.get(smState.getKey()).containsKey(smState.getValue())) {

				localEnabledStates = localEnabledTransitions.get(smState.getKey()).get(smState.getValue());
			}

			Set<String> strGlobalState = gState.statesToString();

			if (!localEnabledStates.isEmpty()) {
				
				for (String str : localEnabledStates.keySet()) {

					Set<String> conflictStates;

					if (this.stateConstraints.containsKey(smState.getKey())
							&& this.stateConstraints.get(smState.getKey()).containsKey(str)) {

						conflictStates = this.stateConstraints.get(smState.getKey()).get(str).keySet();

						Boolean ok = true;
						for (String str1 : strGlobalState) {
							if (conflictStates.contains(str1)) {
								ok = false;
								break;
							}
						}

						if (ok) {
							Trans t = new Trans(smState.getKey(), smState.getValue(), str, localEnabledStates.get(str));
							gState.getGlobalEnabledTrans().add(t);

						}

					} else {
						Trans t = new Trans(smState.getKey(), smState.getValue(), str, localEnabledStates.get(str));
						gState.getGlobalEnabledTrans().add(t);
					}
				}
			}
		}
	}
/**
 * Computes all constraints
 * @param design
 */
	private void compConstraints(List<StateMachine> design) {
		for (StateMachine sm : design) {
			IBeanList<AConstraint> cons = sm.getConstraints();

			for (AConstraint cs : cons) {
				this.updateMap(stateConstraints, sm.getName(), cs.getStateConstraining().getName(),
						cs.getStateInfluenced().getParentCaBeanOfClass(StateMachine.class).getName() + "."
								+ cs.getStateInfluenced().getName(), "");
				this.updateMap(stateConstraints,
						cs.getStateInfluenced().getParentCaBeanOfClass(StateMachine.class).getName(),
						cs.getStateInfluenced().getName(), sm.getName() + "." + cs.getStateConstraining().getName(), "");
			}
		}

	}
/**
 * Gets the initial transition
 * @param design
 * @return
 */
	public GlobalState getGlobalInitialState(List<StateMachine> design) {
		GlobalState initialState = new GlobalState();

		for (StateMachine sm : design) {
			initialState.smStates.put(sm.getName(), sm.getInitialState().getName());
		}
		
		return initialState;
	}
/**
 * Executes a transition
 * @param gState
 * @param exTransition
 * @return
 */
	private GlobalState executeTran(GlobalState gState, Trans exTransition) {

		GlobalState nextGlobalState = new GlobalState();

		for (Map.Entry<String, String> state : gState.smStates.entrySet()) {
			nextGlobalState.smStates.put(state.getKey(), state.getValue());
		}
		nextGlobalState.smStates.put(exTransition.stateMachine, exTransition.destinationState);
		return nextGlobalState;
	}
/**
 * Checks if two transitions are dependent
 * @param t1
 * @param t2
 * @return
 */
	boolean isDependent(Trans t1, Trans t2) {

		return this.stateConstraints.containsKey(t1.stateMachine)
				&& this.stateConstraints.get(t1.stateMachine).containsKey(t1.destinationState)
				&& this.stateConstraints.get(t1.stateMachine).get(t1.destinationState).keySet()
						.contains(t2.stateMachine + "." + t2.destinationState);
	}

	Set<String> stateMachines = new HashSet<String>();
	Stack<GlobalState> globalStateStack = new Stack<GlobalState>();

	 
	/**
	 * get all the next transitions of the state machines at s
	 * @param s
	 * @return
	 */
	public Set<Trans> getNextTransitions(GlobalState s) {
		Set<Trans> nextTrans = new HashSet<Trans>();
		for (String sm : stateMachines) {
			String localState = s.smStates.get(sm);
			Set<String> nextStates = this.stateConstraints.get(sm).get(localState).keySet();
			for (String str : nextStates) {
				Trans t = new Trans(sm, localState, str, " ");
				nextTrans.add(t);
			}
		}

		return nextTrans;
	}

	void printS() {

		for (GlobalState itrState : globalStateStack) {
			itrState.exeTran.prinTran();
		}
	}
/**
 * Checks two global states for equality
 * @param gS1
 * @param gS2
 * @return
 */
	boolean eqCheck(GlobalState gS1, GlobalState gS2) {
		for (String str : gS1.smStates.values()) {
			if (!gS2.smStates.values().contains(str)) {
				return false;
			}
		}
		return true;
	}
/**
 * Checks if a set contains a transition
 * @param tranSet
 * @param tr
 * @return
 */
	boolean contain(Set<Trans> tranSet, Trans tr) {

		for (Trans it : tranSet) {
			if (tr.equals(it)) {
				return true;
			}
		}
		return false;
	}

	int reducedTrace = 0;
/**
 * Exploartion algorithm
 * @param design
 * @return 
 * @return
 */
	public GlobalState initialSimulationComputation(List<StateMachine> design) {
		this.compLocalEnabledTrans(design);
		this.compConstraints(design);
		GlobalState iniState = this.getGlobalInitialState(design);
		globalStateStack.push(iniState);
		GlobalState s = globalStateStack.peek();
		this.getGlobalEnabledTrans(s);	
		return s;
	}

	int exTraces = 0;
/**
 * Exhaustive exploration
 * @param design
 * @return 
 */
	public GlobalState nextTransition(GlobalState s, Trans t) {
		s.setExecutedTran(t);
		GlobalState gs = executeTran(s, s.exeTran);
		this.getGlobalEnabledTrans(gs);
		return gs;

	}

}

