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

public class VersionControlChange {
	
	private String path;
	private String status;
//	private VersionControlSystem system;
	
	public VersionControlChange(String path, String status) {
		this.path = path;
		this.status = status;
	}

	public String getPath() {
		return path;
	}

	public String getStatus() {
		return status;
	}	
}
