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

public class ServerRoles {

	public static final String ADMIN = "ADMIN";
	public static final String USER = "USER";

	private ServerRoles() { }
	
	public static String[] getAllRoles() {
		return new String[]{ADMIN, USER};
	}
}
