/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.server.auth.userhandler;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import de.dlr.sc.virsat.server.auth.ServerRoles;
import de.dlr.sc.virsat.server.auth.ServerUser;

public class TestServerUserHandler implements IServerUserHandler {

	public static final String ADMIN = "admin:password";
	public static final String USER_NO_REPO = "user:password";
	public static final String USER_WITH_REPO = "user2:password";
	public static final UUID TEST_REPOSITORY_UUID = UUID.randomUUID();
	
	private List<ServerUser> users = new ArrayList<ServerUser>();
	
	/**
	 * Creates test users
	 */
	public TestServerUserHandler() {
		
		ArrayList<UUID> repositories = new ArrayList<UUID>();
		repositories.add(TEST_REPOSITORY_UUID);
		
		// Test users
		ServerUser user1 = new ServerUser("admin", "password", ServerRoles.ADMIN, new ArrayList<UUID>());
		ServerUser user2 = new ServerUser("user", "password", ServerRoles.USER, new ArrayList<UUID>());
		ServerUser user3 = new ServerUser("user2", "password", ServerRoles.USER, repositories);
		users.add(user1);
		users.add(user2);
		users.add(user3);
	}
	
	@Override
	public ServerUser getUser(String username, String password) {
		for (ServerUser user : users) {
			if (user.getName().equals(username) && user.getPassword().equals(password)) {
				return user;
			}
		}
		return null;
	}

}
