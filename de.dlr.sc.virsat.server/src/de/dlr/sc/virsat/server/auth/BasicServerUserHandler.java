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

import java.util.ArrayList;
import java.util.List;

public class BasicServerUserHandler {

	private static BasicServerUserHandler instance = null;
	
	private List<ServerUser> users = new ArrayList<ServerUser>();
	
	private BasicServerUserHandler() { 
		// demo users
		ServerUser user1 = new ServerUser("admin", "password", ServerRoles.ADMIN);
		ServerUser user2 = new ServerUser("user", "password", ServerRoles.USER);
		users.add(user1);
		users.add(user2);
	}
	
	public static BasicServerUserHandler getInstance() {
		if (instance == null) {
			instance = new BasicServerUserHandler();
		}
		return instance;
	}
	
	/**
	 * @param username of the user
	 * @param password of the user
	 * @return the role of the user if the user exists, null otherwise
	 */
	public String getUserRole(String username, String password) {
		for (ServerUser user : users) {
			if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
				return user.getServerRole();
			}
		}
		return null;
	}
}
