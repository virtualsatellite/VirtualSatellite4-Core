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

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ServerUser implements Principal {

	private String username;
	private String password;
	private String serverRole;
	private List<UUID> repositories;
	
	public ServerUser(String username, String password, String serverRole, ArrayList<UUID>repositories) {
		this.username = username;
		this.password = password;
		this.serverRole = serverRole;
		this.repositories = repositories;
	}
	
	public String getPassword() {
		return password;
	}
	
	public String getServerRole() {
		return serverRole;
	}

	@Override
	public String getName() {
		return username;
	}

	public List<UUID> getRepositories() {
		return repositories;
	}
}
