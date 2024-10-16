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

import java.util.Objects;

import de.dlr.sc.virsat.model.extension.statemachines.statespace.StateSpaceExplorer.SystemState;

/**
 * A trace state is a system state with an attached time point.
 */
public class TraceState {
	private final int timepoint;
	private final StateSpaceExplorer.SystemState systemState;
	
	public TraceState(int timepoint, SystemState systemState) {
		this.timepoint = timepoint;
		this.systemState = systemState;
	}
	
	public int getTimepoint() {
		return timepoint;
	}
	
	public final StateSpaceExplorer.SystemState getSystemState() {
		return systemState;
	}
	
	@Override
	public final int hashCode() {
		return Objects.hash(systemState, timepoint);
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
		TraceState other = (TraceState) obj;
		return systemState.equals(other.systemState) && timepoint == other.timepoint;
	}
}
