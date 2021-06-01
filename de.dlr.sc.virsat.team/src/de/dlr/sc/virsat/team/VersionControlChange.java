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

/**
 * POJO for a change in the version control
 */
public class VersionControlChange {
	
	private String pathOld;
	private String pathNew;
	private VersionControlChangeType changeType;
	
	public VersionControlChange(String pathOld, String pathNew, VersionControlChangeType changeType) {
		this.pathOld = pathOld;
		this.pathNew = pathNew;
		this.changeType = changeType;
	}

	public VersionControlChangeType getChangeType() {
		return changeType;
	}

	public String getPathOld() {
		return pathOld;
	}

	public String getPathNew() {
		return pathNew;
	}	
}
