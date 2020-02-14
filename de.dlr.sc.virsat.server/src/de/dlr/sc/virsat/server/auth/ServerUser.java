/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.server.auth;

public class ServerUser {

	private String username;
	private String password;
	private String serverRole;
	
	public ServerUser(String username, String password, String serverRole) {
		this.username = username;
		this.password = password;
		this.serverRole = serverRole;
	}
	
	public String getUsername() {
		return username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public String getServerRole() {
		return serverRole;
	}
}
