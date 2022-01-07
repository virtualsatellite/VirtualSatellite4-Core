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
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;


public class GlobalState {

	HashMap<String, String> smStates = new HashMap<String, String>();

	HashMap<String, Boolean> updated = new HashMap<String, Boolean>();

	Set<Trans> globalEnabledTrans = new HashSet<Trans>();

	GlobalState() {
	};

	// the transition will be fired to get the next global state.
	Trans exeTran = new Trans();
	// Set<String> backtrack = new HashSet<String>();

	Set<Trans> backtrackingSet = new HashSet<Trans>();
/**
 * States 
 * @return
 */
	protected Set<String> statesToString() {

		Set<String> strSet = new HashSet<String>();

		for (Map.Entry<String, String> sm : smStates.entrySet()) {
			strSet.add(sm.getKey() + "." + sm.getValue()); 
		}
		return strSet;
	}

	protected void setExecutedTran(Trans t) {
		this.exeTran.stateMachine = t.stateMachine;
		this.exeTran.startState = t.startState;
		this.exeTran.destinationState = t.destinationState;

	}

	void printState() {

		for (Entry<String, String> sm : smStates.entrySet()) {
			System.out.println(sm.getKey() + "--" + sm.getValue());
		}
	}
}
