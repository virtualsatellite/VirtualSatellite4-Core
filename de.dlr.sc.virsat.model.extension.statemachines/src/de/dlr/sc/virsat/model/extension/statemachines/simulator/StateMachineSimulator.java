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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
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

	private void updateMap(HashMap<String, HashMap<String, Set<String>>> map, String a, String b, String c) {
		if (!map.containsKey(a)) {
			HashMap<String, Set<String>> m = new HashMap<String, Set<String>>();

			Set<String> strSet = new HashSet<>(Arrays.asList(c));
			m.put(b, strSet);
			map.put(a, m);
		} else {
			if (!map.get(a).containsKey(b)) {
				HashMap<String, Set<String>> m1 = new HashMap<String, Set<String>>();

				m1 = map.get(a);

				Set<String> infStates = new HashSet<String>();
				infStates.add(c);

				m1.put(b, infStates);

				map.put(a, m1);
			} else {
				HashMap<String, Set<String>> m1 = new HashMap<String, Set<String>>();
				m1 = map.get(a);
				Set<String> infStates = new HashSet<String>();
				infStates = m1.get(b);
				infStates.add(c);
				m1.put(b, infStates);
				map.put(a, m1);

			}
		}
	}

	private HashMap<String, HashMap<String, Set<String>>> localEnabledTransitions = new HashMap<String, HashMap<String, Set<String>>>();
	private HashMap<String, HashMap<String, Set<String>>> stateConstraints = new HashMap<String, HashMap<String, Set<String>>>();
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
						transition.getStateTo().getName());
			}

		}
	}

	
	/**
	 * compute local enabled transitions at a given state gS
	 * @param gS
	 * @return
	 */
	private Set<Trans> getLocalEnabled(GlobalState gS) {
		Set<Trans> tSet = new HashSet<Trans>();

		for (Map.Entry<String, String> smState : gS.smStates.entrySet()) {
			String sm = smState.getKey();
			String state = smState.getValue();
			if (localEnabledTransitions.get(sm).containsKey(state)) {
				for (String str : localEnabledTransitions.get(sm).get(state)) {
					Trans t = new Trans(sm, state, str);
					tSet.add(t);
				}
			}
		}

		return tSet;
	}
/**
 * Calculates 
 * @param gState
 */
	private void getGlobalEnabledTrans(GlobalState gState) {
		for (Map.Entry<String, String> smState : gState.smStates.entrySet()) {

			Set<String> localEnabledStates = new HashSet<String>();

			if (localEnabledTransitions.containsKey(smState.getKey())
					&& localEnabledTransitions.get(smState.getKey()).containsKey(smState.getValue())) {

				localEnabledStates = localEnabledTransitions.get(smState.getKey()).get(smState.getValue());
			}

			Set<String> strGlobalState = gState.statesToString();

			if (!localEnabledStates.isEmpty()) {
				for (String str : localEnabledStates) {

					Set<String> conflictStates;

					if (this.stateConstraints.containsKey(smState.getKey())
							&& this.stateConstraints.get(smState.getKey()).containsKey(str)) {

						conflictStates = this.stateConstraints.get(smState.getKey()).get(str);

						Boolean ok = true;
						for (String str1 : strGlobalState) {
							if (conflictStates.contains(str1)) {
								ok = false;
								break;
							}
						}

						if (ok) {
							Trans t = new Trans(smState.getKey(), smState.getValue(), str);
							gState.globalEnabledTrans.add(t);

						}

					} else {
						Trans t = new Trans(smState.getKey(), smState.getValue(), str);
						gState.globalEnabledTrans.add(t);
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
								+ cs.getStateInfluenced().getName());
				this.updateMap(stateConstraints,
						cs.getStateInfluenced().getParentCaBeanOfClass(StateMachine.class).getName(),
						cs.getStateInfluenced().getName(), sm.getName() + "." + cs.getStateConstraining().getName());
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
				&& this.stateConstraints.get(t1.stateMachine).get(t1.destinationState)
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
			Set<String> nextStates = this.stateConstraints.get(sm).get(localState);
			for (String str : nextStates) {
				Trans t = new Trans(sm, localState, str);
				nextTrans.add(t);
			}
		}

		return nextTrans;
	}

	void printS() {

		for (GlobalState itrState : globalStateStack) {
			// System.out.print(itrState.smStates + " ");
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
 */
	public void initialSimulationComputation(List<StateMachine> design) {
		this.compLocalEnabledTrans(design);
		this.compConstraints(design);
		GlobalState iniState = this.getGlobalInitialState(design);
		globalStateStack.push(iniState);
		this.exhautiveExploration(design);
	}

	int exTraces = 0;
/**
 * Exhaustive exploration
 * @param design
 */
	public void exhautiveExploration(List<StateMachine> design) {
		GlobalState s = globalStateStack.peek();

		this.getGlobalEnabledTrans(s);

		if (s.globalEnabledTrans.isEmpty()) {
			exTraces++;
			this.printS();
		} else {
			for (Trans t : s.globalEnabledTrans) {
				s.backtrackingSet.add(t);
			}

			Set<Trans> done = new HashSet<Trans>();
			while (!s.backtrackingSet.isEmpty()) {

				Trans t = s.backtrackingSet.iterator().next();
				// String localNextState = s.enabledTransitions.get(sm).iterator().next();
				s.setExecutedTran(t);

				GlobalState nextState = executeTran(s, s.exeTran);

				// state equality checking
				boolean eqChk = false;
				Iterator<GlobalState> itr = globalStateStack.iterator();

				while (itr.hasNext()) {
					if (this.eqCheck(nextState, itr.next())) {
						eqChk = true;
						// System.out.println("State equality detected ");
						exTraces++;
						this.printS();
						// System.out.println("State " + nextState.smStates + " has been visited");
						break;
					}
				}

				if (!eqChk) {

					globalStateStack.push(nextState);
					exhautiveExploration(design);

				}
				done.add(t);
				s.backtrackingSet.removeAll(done);

			}

		}

		globalStateStack.pop();

	}

}

