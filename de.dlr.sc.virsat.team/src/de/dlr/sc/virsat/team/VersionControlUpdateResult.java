/*******************************************************************************
 * Copyright (c) 2020 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.team;

import java.util.ArrayList;
import java.util.List;

/**
 * Result of a version control update that holds the discovered changes
 */
public class VersionControlUpdateResult {
	
	private List<VersionControlChange> changes = new ArrayList<VersionControlChange>();

	public void addChange(VersionControlChange change) {
		changes.add(change);
	}
	
	public List<VersionControlChange> getChanges() {
		return changes;
	}
	
	public boolean hasChanges() {
		return changes.size() > 0;
	}
}
