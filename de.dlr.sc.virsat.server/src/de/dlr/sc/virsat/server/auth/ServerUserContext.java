/*******************************************************************************
 * Copyright (c) 2021 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.server.auth;

import java.security.Principal;

import javax.ws.rs.core.SecurityContext;

import de.dlr.sc.virsat.model.dvlm.roles.IUserContext;

public class ServerUserContext implements IUserContext {
	
	private boolean superUser;
	private String userName;
	
	public ServerUserContext(SecurityContext sc) {
		superUser = sc.isUserInRole(ServerRoles.ADMIN);
		Principal user = sc.getUserPrincipal();
		if (user != null) {
			userName = user.getName();
		}
	}
	
	@Override
	public boolean isSuperUser() {
		return superUser;
	}
	
	@Override
	public String getUserName() {
		return userName;
	}
}
