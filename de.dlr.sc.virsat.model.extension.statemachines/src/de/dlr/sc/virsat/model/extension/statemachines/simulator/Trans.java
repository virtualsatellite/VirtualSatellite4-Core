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

public class Trans {
	String stateMachine;
	String startState;
	String destinationState;
	boolean isBacktracking = false;
	boolean explored = false;
	private String transitionTrigger;

	Trans(String sm, String sState, String dState, String transitionTrigger) {
		this.stateMachine = sm;
		this.startState = sState;
		this.destinationState = dState;
		this.transitionTrigger = transitionTrigger;
		explored = false;
	}

	public Trans() {
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}

		Trans otherTran = (Trans) o;
		return this.stateMachine.equals(otherTran.stateMachine) && this.startState.equals(otherTran.startState)
				&& this.destinationState.equals(otherTran.destinationState);
	}

	public String prinTran() {
		String str = this.stateMachine + ": " + this.transitionTrigger + " " + this.startState + " -> " + this.destinationState;
		return str;

	}


}
